package edu.ucsb.cs.rpc.rmi.service;

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

import edu.ucsb.cs.rpc.base.DataObject;
import edu.ucsb.cs.rpc.rmi.interfaces.Echo;


public class RmiEchoServer implements Echo {
	
    public static final String RMI_REGISTRY_NAME = "Echo";
    public static final String POLICY_FILE_NAME = "allow_all.policy";
    
    
	/**
	 * Start up an RMI Echo server for testing rpc
	 * program runs until it is killed (Ctrl-C)
	 * @param
	 */
	public static void main(String[] args)
	{
		String name = "Echo";
		int port = 0; //12035 or 1099
		/*if (args.length > 0)
			name = args[0];
		if (args.length > 1)
			port = Integer.parseInt(args[1]);*/
		
		System.setProperty("java.rmi.server.codebase", Echo.class.getProtectionDomain().getCodeSource().getLocation().toString());
		try {
            File tempFile = File.createTempFile("rmi-base", ".policy");
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
        catch(IOException e) {
            throw new RuntimeException(e);
        }
		
		if (System.getSecurityManager() == null)
			System.setSecurityManager(new SecurityManager());
		try {
			Echo node = new RmiEchoServer();
			Echo stub = (Echo)UnicastRemoteObject.exportObject(node, port);
			Registry registry = LocateRegistry.getRegistry();
			registry.rebind(name, stub);
			System.out.println("EchoEngine Bound");
		}
		catch (Exception e) {
			System.err.println("EchoEngine exception");
			e.printStackTrace();
		}
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
