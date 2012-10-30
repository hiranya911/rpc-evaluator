package edu.ucsb.cs.rpc.rmi.client;

import edu.ucsb.cs.rpc.base.Client;
import edu.ucsb.cs.rpc.base.DataObject;
import edu.ucsb.cs.rpc.base.InvocationResult;
import edu.ucsb.cs.rpc.base.RPCEvaluatorException;

import edu.ucsb.cs.rpc.rmi.interfaces.Echo;

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

public class EchoClient implements Client {

    public static final String SERVICE_ENDPOINT = "Service.Endpoint";
    public static final String RMI_REGISTRY_NAME = "Echo";
    public static final String POLICY_FILE_NAME = "allow_all.policy";

	private Echo echo;

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
        //String endpoint = "localhost";
    	String endpoint = properties.getProperty(SERVICE_ENDPOINT);
        if (endpoint == null || "".equals(endpoint)) {
            throw new RPCEvaluatorException("Service.Endpoint parameter not specified");
        }
        try {
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
    	
    			Registry registry = LocateRegistry.getRegistry(endpoint);
    			echo = (Echo)registry.lookup(RMI_REGISTRY_NAME);
        } catch (Exception ex) {
            throw new RPCEvaluatorException("Error while initializing RMI client", ex);
        }
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

    }

    @Override
    public InvocationResult doNothing() {
        Throwable t = null;

        long start = System.currentTimeMillis();
        try {
            echo.doNothing();
        } catch (RemoteException e) {
            t = e;
        }
        long end = System.currentTimeMillis();

        return report(start, end, t);
    }

    @Override
    public InvocationResult echoString(String s) {
        Throwable t = null;
        String response = null;
        
        long start = System.currentTimeMillis();
        try {
            response = echo.echoString(s);
        } catch (RemoteException e) {
            t = e;
        }
        long end = System.currentTimeMillis();

        if (response == null || !response.equals(s)) {
            t = new RPCEvaluatorException("Invalid echo response");
        }
        return report(start, end, t);
    }

    @Override
    public InvocationResult echoInt(int i) {
    	Throwable t = null;
        int response = 0;
        
        long start = System.currentTimeMillis();
        try {
            response = echo.echoInt(i);
        } catch (RemoteException e) {
            t = e;
        }
        long end = System.currentTimeMillis();

        if (response != i) {
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
            response = echo.echoArray(array);
        } catch (RemoteException e) {
            t = e;
        }
        long end = System.currentTimeMillis();

        if (response == null) { //TODO compare
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
            response = echo.echoObject(o);
        } catch (RemoteException e) {
            t = e;
        }
        long end = System.currentTimeMillis();

        if (response == null || !response.equals(o)) {
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
            response = echo.echoMap(map);
        } catch (RemoteException e) {
            t = e;
        }
        long end = System.currentTimeMillis();

        if (response == null || !response.equals(map)) {
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
            response = echo.echoBlob(blob);
        } catch (RemoteException e) {
            t = e;
        }
        long end = System.currentTimeMillis();

        if (response == null) { //TODO compare
            t = new RPCEvaluatorException("Invalid echo response");
        }
        return report(start, end, t);
    }
}
