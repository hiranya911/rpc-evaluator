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

    public static final String RMI_ENDPOINT = "RMI.Endpoint";
    public static final String RMI_REGISTRY_NAME = "Echo";
    public static final String POLICY_FILE_NAME = "allow_all.policy";

	private Echo echo;
	private String rmiName;
	File tempFile;
	
    @Override
    public void init(Properties properties) throws RPCEvaluatorException {
        //String endpoint = "localhost";
    	rmiName = RMI_REGISTRY_NAME;
    	tempFile = null;
    	String endpoint = properties.getProperty(RMI_ENDPOINT);
        if (endpoint == null || "".equals(endpoint)) {
            throw new RPCEvaluatorException("RMI.Endpoint parameter not specified");
        }
        try {
    		System.setProperty("java.rmi.server.codebase", Echo.class.getProtectionDomain().getCodeSource().getLocation().toString());
    		
    		if (System.getProperty("java.rmi.server.codebase") == null)
    			System.setProperty("java.rmi.server.codebase", Echo.class.getProtectionDomain().getCodeSource().getLocation().toString());
    		
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
    	            
    	            Runtime.getRuntime().addShutdownHook(new Thread() {
    	    			public void run() {
    	    				if (tempFile != null)
    	    				{
    	    					try 
    	    					{
    	    						if (tempFile != null)
    	    							tempFile.delete();
    	    					}
    	    					catch (Exception e)
    	    					{
    	    						
    	    					}
    	    					System.out.println("EchoEngine terminated");
    	    				}
    	    			}
    	    		});
    			}
            }
            catch(IOException e) {
                throw new RuntimeException(e);
            }
    		
    	
    		if (System.getSecurityManager() == null)
    			System.setSecurityManager(new SecurityManager());
    	
    			Registry registry = LocateRegistry.getRegistry(endpoint);
    			echo = (Echo)registry.lookup(rmiName);
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

        if (t == null && (response == null || !response.equals(s))) {
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

        if (t == null && (response != i)) {
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
            response = echo.echoObject(o);
        } catch (RemoteException e) {
            t = e;
        }
        long end = System.currentTimeMillis();

        if (t == null && (response == null || !objectEquals(o, response))) {
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
    	Map<Integer, Integer> response = null;
        
        long start = System.currentTimeMillis();
        try {
            response = echo.echoMap(map);
        } catch (RemoteException e) {
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
            response = echo.echoBlob(blob);
        } catch (RemoteException e) {
            t = e;
        }
        long end = System.currentTimeMillis();

        if (t == null && response == null) { //TODO compare
            t = new RPCEvaluatorException("Invalid echo response");
        }
        return report(start, end, t);
    }
}
