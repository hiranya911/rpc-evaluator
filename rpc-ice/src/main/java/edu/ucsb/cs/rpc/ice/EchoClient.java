package edu.ucsb.cs.rpc.ice;

import java.util.Map;
import java.util.Properties;

import edu.ucsb.cs.rpc.base.Client;
import edu.ucsb.cs.rpc.base.DataObject;
import edu.ucsb.cs.rpc.base.InvocationResult;
import edu.ucsb.cs.rpc.base.RPCEvaluatorException;

public class EchoClient implements Client{

	public static final String ICE_ENDPOINT = "ice.Endpoint";
	private edu.ucsb.cs.rpc.ice.service.EchoPrx echoService;
	private Ice.Communicator ic = null;

	
	@Override
	public void init(Properties properties) throws RPCEvaluatorException {

		String endpoint = properties.getProperty(ICE_ENDPOINT);
        if (endpoint == null || "".equals(endpoint)) {
            throw new RPCEvaluatorException("ICE.Endpoint parameter not specified");
        }		
        
		ic = Ice.Util.initialize();
		Ice.ObjectPrx base = ic
				.stringToProxy("EchoService:default -p 9999 -h " + endpoint);
		echoService = edu.ucsb.cs.rpc.ice.service.EchoPrxHelper.checkedCast(base);
		if (echoService == null)
			throw new RPCEvaluatorException("Invalid proxy");
	}
/*
	public static void main(String[] args) {
		EchoClient client = new EchoClient();
		
		try {
			client.init(null);

		} catch (RPCEvaluatorException e) {
			e.printStackTrace();
		}
		
		client.destroy();
	}
*/	
	
	@Override
	public void destroy() {
		try {
			ic.destroy();
		} catch (Exception e) {
			System.err.println(e.getMessage());
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
	public InvocationResult doNothing() {			
			Throwable t = null;
			
			long start = System.currentTimeMillis();


			try {
				echoService.doNothing();
			} catch (Throwable e) {
				t = e;
			}
			long stop = System.currentTimeMillis();
			
			return report(start, stop, t);
	}

	@Override
	public InvocationResult echoString(String s) {
		Exception exc = null;
        String response = null;
        
        long start = System.currentTimeMillis();
        try {
            response = echoService.echoString(s);
        } catch (Exception e) {
            exc = e;
        }
        long end = System.currentTimeMillis();

        if ((response == null || !response.equals(s)) && exc == null) {
            exc = new RPCEvaluatorException("Invalid echo response");
        }
        return report(start, end, exc);		
		
	}

	@Override
	public InvocationResult echoInt(int i) {
		Exception exc = null;
        int response = 0;
        
        long start = System.currentTimeMillis();
        try {
            response = echoService.echoInt(i);
        } catch (Exception e) {
            exc = e;
        }
        long end = System.currentTimeMillis();

        if (response != i && exc == null) {
            exc = new RPCEvaluatorException("Invalid echo response");
        }
        return report(start, end, exc);	
	}

	@Override
	public InvocationResult echoArray(int[] array) {
		Exception exc = null;
        int[] response = null;
        
        long start = System.currentTimeMillis();
        try {
            response = echoService.echoArray(array);
        } catch (Exception e) {
            exc = e;
        }
        long end = System.currentTimeMillis();

        if (response == null && exc == null) { //TODO compare
            exc = new RPCEvaluatorException("Invalid echo response");
        }
        return report(start, end, exc);

	}

	@Override
	public InvocationResult echoObject(DataObject o) {
		Exception exc = null;
    	DataObject response = null;
        
        long start = System.currentTimeMillis();
        try {
            response = echoService.echoObject(o);
        } catch (Exception e) {
            exc = e;
        }
        long end = System.currentTimeMillis();

        if ((response == null || !response.equals(o)) && exc == null) {
            exc = new RPCEvaluatorException("Invalid echo response");
        }
        return report(start, end, exc);
   }

	@Override
	public InvocationResult echoMap(Map<Integer, Integer> map) {
		Exception exc = null;
    	Map<Integer, Integer> response = null;
        
        long start = System.currentTimeMillis();
        try {
            response = echoService.echoMap(map);
        } catch (Exception e) {
            exc = e;
        }
        long end = System.currentTimeMillis();

        if ((response == null || !response.equals(map)) && exc == null) {
            exc = new RPCEvaluatorException("Invalid echo response");
        }
        return report(start, end, exc);
    }

	@Override
	public InvocationResult echoBlob(byte[] blob) {
		Exception exc = null;
    	byte[] response = null;
        
        long start = System.currentTimeMillis();
        try {
            response = echoService.echoBlob(blob);
        } catch (Exception e) {
            exc = e;
        }
        long end = System.currentTimeMillis();

        if (response == null && exc == null) { //TODO compare
            exc = new RPCEvaluatorException("Invalid echo response");
        }
        return report(start, end, exc);
	}

	
	
}
