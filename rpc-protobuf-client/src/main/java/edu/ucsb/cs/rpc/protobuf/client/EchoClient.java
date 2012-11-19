package edu.ucsb.cs.rpc.protobuf.client;

import edu.ucsb.cs.rpc.base.Client;
import edu.ucsb.cs.rpc.base.DataObject;
import edu.ucsb.cs.rpc.base.InvocationResult;
import edu.ucsb.cs.rpc.base.RPCEvaluatorException;
import edu.ucsb.cs.rpc.protobuf.Echo.EchoArray;
import edu.ucsb.cs.rpc.protobuf.Echo.EchoBlob;
import edu.ucsb.cs.rpc.protobuf.Echo.EchoDataObject;
import edu.ucsb.cs.rpc.protobuf.Echo.EchoInt;
import edu.ucsb.cs.rpc.protobuf.Echo.EchoMap;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.concurrent.Executors;

import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.google.protobuf.ByteString;
import com.google.protobuf.RpcController;
import com.google.protobuf.ServiceException;
import com.googlecode.protobuf.pro.duplex.PeerInfo;
import com.googlecode.protobuf.pro.duplex.RpcClientChannel;
import com.googlecode.protobuf.pro.duplex.client.DuplexTcpClientBootstrap;
import com.googlecode.protobuf.pro.duplex.execute.SameThreadExecutor;
import com.googlecode.protobuf.pro.duplex.execute.ThreadPoolCallExecutor;

public class EchoClient implements Client {

    public static final String SERVICE_ENDPOINT = "ProtoBuf.ServerEndpoint";
    public static final String SERVICE_PORT = "ProtoBuf.ServerPort";
    public static final String CLIENT_ENDPOINT = "ProtoBuf.ClientEndpoint";
    public static final String CLIENT_COMPRESSION = "ProtoBuf.UseCompression";
    public static final String CLIENT_BUFFER = "ProtoBuf.ClientReceiveBufferSize";
    public static final String CLIENT_TCPNODELAY = "ProtoBuf.ClientTcpNoDelay";
    
    PeerInfo client;
    PeerInfo server;
    DuplexTcpClientBootstrap bootstrap;
    RpcClientChannel channel;
    BlockingInterface echoService;
    RpcController controller;
    
//    static {
//        System.setProperty("org.apache.commons.logging.Log",
//                           "org.apache.commons.logging.impl.NoOpLog");
//     }

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
    
    private static String getString(Properties properties, String name) throws RPCEvaluatorException
    {
    	String value = properties.getProperty(name);
        if (value == null || "".equals(value)) {
            throw new RPCEvaluatorException(name + " parameter not specified");
        }
        return value;
    }
    
    private static int getInt(Properties properties, String name) throws RPCEvaluatorException
    {
    	String valueString = getString(properties, name);
    	int value = 0;
    	try
		{
    		value = Integer.parseInt(valueString);
		}
		catch (NumberFormatException ex)
		{
			throw new RPCEvaluatorException("couldn't parse " + name + " parameter to int");
		}
    	return value;
    }
    
    private static boolean getBool(Properties properties, String name) throws RPCEvaluatorException
    {
    	String valueString = getString(properties, name);
    	if (valueString.compareToIgnoreCase("true") != 0 && valueString.compareToIgnoreCase("false") != 0)
    			throw new RPCEvaluatorException("couldn't parse " + name + " parameter to bool");
    	return Boolean.parseBoolean(valueString);
    }
	
    @Override
    public void init(Properties properties) throws RPCEvaluatorException {
    	String clientHost = getString(properties, CLIENT_ENDPOINT);
    	String serverHost = getString(properties, SERVICE_ENDPOINT);
    	int serverPort = getInt(properties, SERVICE_PORT);
    	boolean useCompression = getBool(properties, CLIENT_COMPRESSION);
    	int receiveBuffer = getInt(properties, CLIENT_BUFFER);
    	boolean tcpNoDelay = getBool(properties, CLIENT_TCPNODELAY);
    	
    	try {
	    	client = new PeerInfo(clientHost, 1234);
	        server = new PeerInfo(serverHost, serverPort);
	        SameThreadExecutor executor = new SameThreadExecutor();
	
	        bootstrap = new DuplexTcpClientBootstrap(
	                        client, 
	                        new NioClientSocketChannelFactory(
	                Executors.newCachedThreadPool(),
	                Executors.newCachedThreadPool()),
	                executor);
	        bootstrap.setCompression(useCompression);
	        bootstrap.setOption("connectTimeoutMillis",10000);
	        bootstrap.setOption("connectResponseTimeoutMillis",10000);
	        bootstrap.setOption("receiveBufferSize", receiveBuffer);
	        bootstrap.setOption("tcpNoDelay", tcpNoDelay);
	        
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

    	NothingMessage request = NothingMessage.newBuilder().build();
    	
        long start = System.currentTimeMillis();
        try {
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
        
    	EchoString request = EchoString.newBuilder().setData(s).build();
    	
        long start = System.currentTimeMillis();
        try {
			response = echoService.echoString(controller, request);
        } catch (ServiceException e) {
            t = e;
        }
	    catch (Exception e) {
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
        
        EchoInt request = EchoInt.newBuilder().setData(i).build();
        
        long start = System.currentTimeMillis();
        try {
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
        EchoArray response = null;
    	edu.ucsb.cs.rpc.protobuf.Echo.EchoArray.Builder requestBuilder = EchoArray.newBuilder();
    	for(int i = 0; i < array.length; i++)
    		requestBuilder.addData(array[i]);
    	EchoArray request = requestBuilder.build();
        long start = System.currentTimeMillis();
        try {
            response = echoService.echoArray(controller, request);
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
    	EchoDataObject response = null;
    	edu.ucsb.cs.rpc.protobuf.Echo.EchoDataObject.Builder requestBuilder = EchoDataObject.newBuilder();
    	requestBuilder.setChar(o.getCharacter());
    	requestBuilder.setDecimal(o.getDecimal());
    	requestBuilder.setInt(o.getInteger());
    	requestBuilder.setString(o.getString());
    	EchoDataObject request = requestBuilder.build();
        long start = System.currentTimeMillis();
        try {
        	response = echoService.echoDataObject(controller, request);
        } catch (Exception e) {
            t = e;
        }
        long end = System.currentTimeMillis();

        if (t == null && (response == null)) {
            // TODO: Fix object comparison
            t = new RPCEvaluatorException("Invalid echo response");
        }
        return report(start, end, t);
    }

    private boolean objectEquals(DataObject o1, DataObject o2) {
        return o1.getCharacter() == o2.getCharacter() &&
                o1.getDecimal() == o2.getDecimal() &&
                o1.getInteger() == o2.getInteger() &&
                o1.getString().equals(o2.getString());
    }

    @Override
    public InvocationResult echoMap(Map<Integer, Integer> map) {
    	Throwable t = null;
    	EchoMap	response = null;
    	edu.ucsb.cs.rpc.protobuf.Echo.EchoMap.Builder requestBuilder = EchoMap.newBuilder();
    	for (Entry<Integer, Integer> entry : map.entrySet()) {
    	    requestBuilder.addKeys(entry.getKey());
    	    requestBuilder.addValues(entry.getValue());
    	}
    	EchoMap request = requestBuilder.build();
        long start = System.currentTimeMillis();
        try {
        	response = echoService.echoMap(controller, request);
        } catch (Exception e) {
            t = e;
        }
        long end = System.currentTimeMillis();

        if (t == null && response == null) {
            // TODO: Fix map comparison logic
            t = new RPCEvaluatorException("Invalid echo response");
        }
        return report(start, end, t);
    }

    @Override
    public InvocationResult echoBlob(byte[] blob) {
    	Throwable t = null;
    	EchoBlob response = null;
    	
    	EchoBlob request = EchoBlob.newBuilder().setData(ByteString.copyFrom(blob)).build();
    	
        long start = System.currentTimeMillis();
        try {
			response = echoService.echoBlob(controller, request);
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
