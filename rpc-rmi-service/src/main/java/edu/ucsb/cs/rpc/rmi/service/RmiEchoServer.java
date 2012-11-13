package edu.ucsb.cs.rpc.rmi.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Map;

import edu.ucsb.cs.rpc.base.DataObject;
import edu.ucsb.cs.rpc.rmi.interfaces.Echo;


public class RmiEchoServer implements Echo {
	
    public static final String RMI_REGISTRY_NAME = "Echo";
    public static final String POLICY_FILE_NAME = "allow_all.policy";
    File tempFile;
    String rmiName;
    
    public RmiEchoServer()
    {
    	tempFile = null;
    	rmiName = "Echo";
		int port = 0; //12035 or 1099
		/*if (args.length > 0)
			name = args[0];
		if (args.length > 1)
			port = Integer.parseInt(args[1]);*/

		if (System.getProperty("java.rmi.server.codebase") == null)
			System.setProperty("java.rmi.server.codebase", Echo.class.getProtectionDomain().getCodeSource().getLocation().toString() +
					" " + DataObject.class.getProtectionDomain().getCodeSource().getLocation().toString());
		try {
			if (System.getProperty("java.security.policy") == null)
			{
				tempFile = File.createTempFile("rmi-base", ".policy");
	            InputStream is = ClassLoader.getSystemResourceAsStream(POLICY_FILE_NAME);
	            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
	            int read = 0;
	            while((read = is.read()) != -1) {
	                writer.write(read);
	            }
	            writer.close();
	            tempFile.deleteOnExit();
	            System.setProperty("java.security.policy",tempFile.getAbsolutePath());
			}
        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }
		
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				try 
				{
					if (tempFile != null)
						tempFile.delete();
				}
				catch (Exception e)
				{
					
				}
				try
				{
					Registry registry = LocateRegistry.getRegistry();
					registry.unbind(rmiName);
				}
				catch (Exception e)
				{
				
				}
				System.out.println("EchoEngine terminated");
			}
		});
		
		if (System.getSecurityManager() == null)
			System.setSecurityManager(new SecurityManager());
		try {
			Echo stub = (Echo)UnicastRemoteObject.exportObject((Echo)this, port);
			Registry registry = LocateRegistry.getRegistry();
			registry.rebind(rmiName, stub);
			System.out.println("EchoEngine Bound");
		}
		catch (Exception e) {
			System.err.println("EchoEngine exception");
			e.printStackTrace();
		}
    }
    
	/**
	 * Start up an RMI Echo server for testing rpc
	 * program runs until it is killed (Ctrl-C)
	 * @param
	 */
	public static void main(String[] args)
	{
		RmiEchoServer node = new RmiEchoServer();
	}
	
	@Override
	public String echoString(String s) throws RemoteException {
		return s;
	}

	@Override
	public int echoInt(int i) throws RemoteException {
		// TODO Auto-generated method stub
		return i;
	}

	@Override
	public int[] echoArray(int[] array) throws RemoteException {
		// TODO Auto-generated method stub
		return array;
	}

	@Override
	public DataObject echoObject(DataObject o) throws RemoteException {
		// TODO Auto-generated method stub
		return o;
	}

	@Override
	public Map<Integer, Integer> echoMap(Map<Integer, Integer> map)
			throws RemoteException {
		// TODO Auto-generated method stub
		return map;
	}

	@Override
	public byte[] echoBlob(byte[] blob) throws RemoteException {
		// TODO Auto-generated method stub
		return blob;
	}
    
	@Override
	public void doNothing() throws RemoteException {
		return;
	}
}
