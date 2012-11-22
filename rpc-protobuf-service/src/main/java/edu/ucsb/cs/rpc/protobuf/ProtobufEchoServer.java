package edu.ucsb.cs.rpc.protobuf;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executors;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import edu.ucsb.cs.rpc.base.RPCEvaluatorException;
import com.google.protobuf.RpcCallback;
import com.google.protobuf.RpcController;
import com.googlecode.protobuf.pro.duplex.PeerInfo;
import com.googlecode.protobuf.pro.duplex.execute.RpcServerCallExecutor;
import com.googlecode.protobuf.pro.duplex.execute.ThreadPoolCallExecutor;
import com.googlecode.protobuf.pro.duplex.server.DuplexTcpServerBootstrap;

import edu.ucsb.cs.rpc.protobuf.Echo.EchoArray;
import edu.ucsb.cs.rpc.protobuf.Echo.EchoBlob;
import edu.ucsb.cs.rpc.protobuf.Echo.EchoDataObject;
import edu.ucsb.cs.rpc.protobuf.Echo.EchoInt;
import edu.ucsb.cs.rpc.protobuf.Echo.EchoMap;
import edu.ucsb.cs.rpc.protobuf.Echo.EchoService;
import edu.ucsb.cs.rpc.protobuf.Echo.EchoString;
import edu.ucsb.cs.rpc.protobuf.Echo.NothingMessage;

public class ProtobufEchoServer extends EchoService {
	DuplexTcpServerBootstrap bootstrap;
	public static final String SERVICE_ENDPOINT = "ProtoBuf.ServerEndpoint";
    public static final String SERVICE_PORT = "ProtoBuf.ServerPort";
	
    static {
        System.setProperty("org.apache.commons.logging.Log",
                           "org.apache.commons.logging.impl.NoOpLog");
    }

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
    
	public ProtobufEchoServer(String hostName, int port)
	{
		PeerInfo serverInfo = new PeerInfo(hostName, port);
		 RpcServerCallExecutor executor = new ThreadPoolCallExecutor(10, 10);
	        
		 bootstrap = new DuplexTcpServerBootstrap(
	                        serverInfo,
	                new NioServerSocketChannelFactory(
	                        Executors.newCachedThreadPool(),
	                        Executors.newCachedThreadPool()),
	                executor);
		 bootstrap.getRpcServiceRegistry().registerService(this);
		 bootstrap.bind();
	        
	        Runtime.getRuntime().addShutdownHook(new Thread() {
    			public void run() {
    				if (bootstrap != null)
    				{
    					try 
    					{
    						System.out.println("Shutting down EchoService");
    						bootstrap.releaseExternalResources();
    					}
    					catch (Exception e)
    					{
    						
    					}
    				}
    			}
    		});
	}
	
	public static void main(String[] args)
	{
		
		Properties properties = new Properties();
		try {
            properties.load(new FileInputStream(args[0]));
        } catch (IOException e) {
            System.out.println("Error loading properties file");
            printUsage();
            return;
        }
		String serverHost;
		int serverPort;
		try {
			serverHost = getString(properties, SERVICE_ENDPOINT);
			serverPort = getInt(properties, SERVICE_PORT);
		} catch (RPCEvaluatorException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
			printUsage();
            return;
		}
    	
    	System.out.println("Starting EchoService on " + serverHost + ":" + serverPort);
		ProtobufEchoServer server = new ProtobufEchoServer(serverHost, serverPort);
		
	}
	
	private static void printUsage()
	{
		System.out.println("Usage: java edu.ucsb.cs.rpc.protobuf.ProtobufEchoServer popertiesfile");
	}
	
	/*public String echoString(String s) {
		return s;
	}

	public int echoInt(int i) {
		// TODO Auto-generated method stub
		return i;
	}*/

	/*public int[] echoArray(int[] array) {
		// TODO Auto-generated method stub
		return array;
	}*/

	/*public DataObject echoObject(DataObject o) {
		// TODO Auto-generated method stub
		return o;
	}*/

	public Map<Integer, Integer> echoMap(Map<Integer, Integer> map) {
		// TODO Auto-generated method stub
		return map;
	}

	/*public byte[] echoBlob(byte[] blob) {
		// TODO Auto-generated method stub
		return blob;
	}
    
	public void doNothing(){
		return;
	}*/

	@Override
	public void echoString(RpcController controller, EchoString request,
			RpcCallback<EchoString> done) {
		done.run(request);
	}

	@Override
	public void echoInt(RpcController controller, EchoInt request,
			RpcCallback<EchoInt> done) {
		done.run(request);
	}

	@Override
	public void doNothing(RpcController controller, NothingMessage request,
			RpcCallback<NothingMessage> done) {
		done.run(request);
		
	}

	@Override
	public void echoBlob(RpcController controller, EchoBlob request,
			RpcCallback<EchoBlob> done) {
		done.run(request);
	}

	@Override
	public void echoDataObject(RpcController controller,
			EchoDataObject request, RpcCallback<EchoDataObject> done) {
		done.run(request);
		
	}

	@Override
	public void echoArray(RpcController controller, EchoArray request,
			RpcCallback<EchoArray> done) {
		done.run(request);
	}

	@Override
	public void echoMap(RpcController controller, EchoMap request,
			RpcCallback<EchoMap> done) {
		done.run(request);
	}
}
