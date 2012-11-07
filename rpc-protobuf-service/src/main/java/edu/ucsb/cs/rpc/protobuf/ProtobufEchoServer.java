package edu.ucsb.cs.rpc.protobuf;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;
import java.util.concurrent.Executors;

import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import edu.ucsb.cs.rpc.base.DataObject;
import com.google.protobuf.ByteString;
import com.google.protobuf.RpcCallback;
import com.google.protobuf.RpcController;
import com.googlecode.protobuf.pro.duplex.PeerInfo;
import com.googlecode.protobuf.pro.duplex.execute.RpcServerCallExecutor;
import com.googlecode.protobuf.pro.duplex.execute.ThreadPoolCallExecutor;
import com.googlecode.protobuf.pro.duplex.server.DuplexTcpServerBootstrap;

import edu.ucsb.cs.rpc.protobuf.Echo.EchoInt;
import edu.ucsb.cs.rpc.protobuf.Echo.EchoService;
import edu.ucsb.cs.rpc.protobuf.Echo.EchoString;
import edu.ucsb.cs.rpc.protobuf.Echo.NothingMessage;

public class ProtobufEchoServer extends EchoService {
	DuplexTcpServerBootstrap bootstrap;

	
	public ProtobufEchoServer()
	{
		String name = "localhost";
		int port = 8080;
		/*if (args.length > 0)
			name = args[0];
		if (args.length > 1)
			port = Integer.parseInt(args[1]);*/
		
		PeerInfo serverInfo = new PeerInfo(name, port);
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
		ProtobufEchoServer server = new ProtobufEchoServer();
		
	}
	
	/*public String echoString(String s) {
		return s;
	}

	public int echoInt(int i) {
		// TODO Auto-generated method stub
		return i;
	}*/

	public int[] echoArray(int[] array) {
		// TODO Auto-generated method stub
		return array;
	}

	public DataObject echoObject(DataObject o) {
		// TODO Auto-generated method stub
		return o;
	}

	public Map<Integer, Integer> echoMap(Map<Integer, Integer> map) {
		// TODO Auto-generated method stub
		return map;
	}

	public byte[] echoBlob(byte[] blob) {
		// TODO Auto-generated method stub
		return blob;
	}
    
	public void doNothing(){
		return;
	}

	@Override
	public void echoString(RpcController controller, EchoString request,
			RpcCallback<EchoString> done) {
		EchoString response = EchoString.newBuilder().setData(request.getData()).build();
		done.run(response);
	}

	@Override
	public void echoInt(RpcController controller, EchoInt request,
			RpcCallback<EchoInt> done) {
		EchoInt response = EchoInt.newBuilder().setData(request.getData()).build();
		done.run(response);
	}

	@Override
	public void doNothing(RpcController controller, NothingMessage request,
			RpcCallback<NothingMessage> done) {
		NothingMessage response = NothingMessage.newBuilder().build();
		done.run(response);
		
	}
}
