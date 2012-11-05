import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.googlecode.jsonrpc4j.ProxyUtil;

import edu.ucsb.cs.rpc.base.Client;
import edu.ucsb.cs.rpc.base.DataObject;
import edu.ucsb.cs.rpc.base.InvocationResult;
import edu.ucsb.cs.rpc.base.RPCEvaluatorException;
import edu.ucsb.cs.rpc.base.Server;


public class JsonClient implements Client {
	
	private JsonRpcHttpClient client;
	private Server serverService;

	public static void main(String[] args) {
		JsonClient jsonClient = new JsonClient();
		
		try {
			jsonClient.init(null);
			System.out.println(jsonClient.echoInt(100));
		} catch (RPCEvaluatorException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void init(Properties properties) throws RPCEvaluatorException {
		try {
			client = new JsonRpcHttpClient(new URL("http://localhost:8080/json-rpc-server-1.0"));
			serverService = ProxyUtil.createClientProxy(
				    getClass().getClassLoader(),
				    Server.class,
				    client);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
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
			serverService.doNothing();
		} catch (Throwable e) {
			t = e;
		}
		long stop = System.currentTimeMillis();
		
		return report(start, stop, t);
	}

	@Override
	public InvocationResult echoString(String s) {
        Throwable t = null;
        String response = null;
        
        long start = System.currentTimeMillis();
        try {
            response = serverService.echoString(s);
        } catch (Throwable e) {
            t = e;
        }
        long end = System.currentTimeMillis();

        
        System.out.println(response);
        if ((response == null || !response.equals(s)) && t == null) {
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
            response = serverService.echoInt(i);
        } catch (Throwable e) {
            t = e;
        }
        long end = System.currentTimeMillis();

        if (response != i && t == null) {
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
            response = serverService.echoArray(array);
        } catch (Throwable e) {
            t = e;
        }
        long end = System.currentTimeMillis();

        if (response == null && t == null) { //TODO compare
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
            response = serverService.echoObject(o);
        } catch (Throwable e) {
            t = e;
        }
        long end = System.currentTimeMillis();

        if ((response == null || !response.equals(o)) && t == null) {
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
            response = serverService.echoMap(map);
        } catch (Throwable e) {
            t = e;
        }
        long end = System.currentTimeMillis();

        if ((response == null || !response.equals(map)) && t == null) {
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
            response = serverService.echoBlob(blob);
        } catch (Throwable e) {
            t = e;
        }
        long end = System.currentTimeMillis();

        if (response == null && t == null) { //TODO compare
            t = new RPCEvaluatorException("Invalid echo response");
        }
        return report(start, end, t);
	}

}
