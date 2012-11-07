package edu.ucsb.cs.rpc.protobuf.client;

import edu.ucsb.cs.rpc.base.Client;
import edu.ucsb.cs.rpc.base.DataObject;
import edu.ucsb.cs.rpc.base.InvocationResult;
import edu.ucsb.cs.rpc.base.RPCEvaluatorException;
import edu.ucsb.cs.rpc.protobuf.Echo.EchoInt;
import edu.ucsb.cs.rpc.protobuf.Echo.EchoService;
import edu.ucsb.cs.rpc.protobuf.Echo.EchoService.BlockingInterface;
import edu.ucsb.cs.rpc.protobuf.Echo.EchoString;
import edu.ucsb.cs.rpc.protobuf.Echo.NothingMessage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executors;

import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import com.google.protobuf.RpcController;
import com.google.protobuf.ServiceException;
import com.googlecode.protobuf.pro.duplex.PeerInfo;
import com.googlecode.protobuf.pro.duplex.RpcClientChannel;
import com.googlecode.protobuf.pro.duplex.client.DuplexTcpClientBootstrap;
import com.googlecode.protobuf.pro.duplex.execute.ThreadPoolCallExecutor;

public class EchoClient implements Client {

    public static final String SERVICE_ENDPOINT = "ProtoBuf.Endpoint";
    public static final String RMI_REGISTRY_NAME = "Echo";
    public static final String POLICY_FILE_NAME = "allow_all.policy";
    PeerInfo client;
    PeerInfo server;
    DuplexTcpClientBootstrap bootstrap;
    RpcClientChannel channel;
    BlockingInterface echoService;
    RpcController controller;
    

	/*public static void main(String[] args)
	{
		EchoClient client = new EchoClient();
		try {
			client.init(null);
			client.echoString("test");
		} catch (RPCEvaluatorException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
    @Override
    public void init(Properties properties) throws RPCEvaluatorException {
    	String clientHost = "localhost";
    	String serverHost = "localhost";
    	int serverPort = 8080;
    	
    	try {
	    	client = new PeerInfo(clientHost, 1234);
	        server = new PeerInfo(serverHost, serverPort);
	        ThreadPoolCallExecutor executor = new ThreadPoolCallExecutor(3, 10);
	
	        bootstrap = new DuplexTcpClientBootstrap(
	                        client, 
	                        new NioClientSocketChannelFactory(
	                Executors.newCachedThreadPool(),
	                Executors.newCachedThreadPool()),
	                executor);
	        bootstrap.setCompression(true);
	        bootstrap.setOption("connectTimeoutMillis",10000);
	        bootstrap.setOption("connectResponseTimeoutMillis",10000);
	        bootstrap.setOption("receiveBufferSize", 1048576);
	        bootstrap.setOption("tcpNoDelay", false);
	        
	        channel = bootstrap.peerWith(server);
	        
	        echoService = EchoService.newBlockingStub(channel);
	        controller = channel.newRpcController();
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        //bootstrap.getRpcServiceRegistry().registerService(new EchoService());
        
       
    }

    private InvocationResult report(long start, long end, Throwable t) {
        if (t != null) {
            return new InvocationResult(start, end, t);
        } else {
            return new InvocationResult(start, end);
        }
    }

    @Override
    public void destroy() {
    	if (channel != null)
		{
			try 
			{
				channel.close();
			}
			catch (Exception e)
			{
				
			}
		}
		if (bootstrap != null)
		{
			try 
			{
				bootstrap.releaseExternalResources();
			}
			catch (Exception e)
			{
				
			}
		}
    }

    @Override
    public InvocationResult doNothing() {
        Throwable t = null;

        long start = System.currentTimeMillis();
        try {
        	NothingMessage request = NothingMessage.newBuilder().build();
        	echoService.doNothing(controller, request);
        } catch (Exception e) {
            t = e;
        }
        long end = System.currentTimeMillis();

        return report(start, end, t);
    }

    @Override
    public InvocationResult echoString(String s) {
        Throwable t = null;
        EchoString response = null;
        
        long start = System.currentTimeMillis();
        try {
        	EchoString request = EchoString.newBuilder().setData(s).build();
			response = echoService.echoString(controller, request);
        } catch (ServiceException e) {
            t = e;
        }
        long end = System.currentTimeMillis();
        	
        if (t == null && (response == null || !response.getData().equals(s))) {
            t = new RPCEvaluatorException("Invalid echo response");
        }
        return report(start, end, t);
    }

    @Override
    public InvocationResult echoInt(int i) {
    	Throwable t = null;
        EchoInt response = null;
        
        long start = System.currentTimeMillis();
        try {
        	EchoInt request = EchoInt.newBuilder().setData(i).build();
			response = echoService.echoInt(controller, request);
        } catch (Exception e) {
            t = e;
        }
        long end = System.currentTimeMillis();

        if (t == null && (response == null || response.getData() != i)) {
            t = new RPCEvaluatorException("Invalid echo response");
        }
        return report(start, end, t);
    }

    @Override
    public InvocationResult echoArray(int[] array) {
    	Throwable t = null;
        int[] response = null;
        
        long start = System.currentTimeMillis();
        try {
            //response = echo.echoArray(array);
        } catch (Exception e) {
            t = e;
        }
        long end = System.currentTimeMillis();

        if (t == null && response == null) { //TODO compare
            t = new RPCEvaluatorException("Invalid echo response");
        }
        return report(start, end, t);
    }

    @Override
    public InvocationResult echoObject(DataObject o) {
    	Throwable t = null;
    	DataObject response = null;
        
        long start = System.currentTimeMillis();
        try {
            //response = echo.echoObject(o);
        } catch (Exception e) {
            t = e;
        }
        long end = System.currentTimeMillis();

        if (t == null && (response == null || !response.equals(o))) {
            t = new RPCEvaluatorException("Invalid echo response");
        }
        return report(start, end, t);
    }

    @Override
    public InvocationResult echoMap(Map<Integer, Integer> map) {
    	Throwable t = null;
    	Map<Integer, Integer> response = null;
        
        long start = System.currentTimeMillis();
        try {
            //response = echo.echoMap(map);
        } catch (Exception e) {
            t = e;
        }
        long end = System.currentTimeMillis();

        if (t == null && (response == null || !response.equals(map))) {
            t = new RPCEvaluatorException("Invalid echo response");
        }
        return report(start, end, t);
    }

    @Override
    public InvocationResult echoBlob(byte[] blob) {
    	Throwable t = null;
    	byte[] response = null;
        
        long start = System.currentTimeMillis();
        try {
            //response = echo.echoBlob(blob);
        } catch (Exception e) {
            t = e;
        }
        long end = System.currentTimeMillis();

        if (t == null && response == null) { //TODO compare
            t = new RPCEvaluatorException("Invalid echo response");
        }
        return report(start, end, t);
    }
}
