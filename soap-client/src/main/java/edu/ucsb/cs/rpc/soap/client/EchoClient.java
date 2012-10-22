package edu.ucsb.cs.rpc.soap.client;

import edu.ucsb.cs.rpc.base.Client;
import edu.ucsb.cs.rpc.base.DataObject;
import edu.ucsb.cs.rpc.base.InvocationResult;
import edu.ucsb.cs.rpc.base.RPCEvaluatorException;
import org.apache.axis2.AxisFault;

import java.rmi.RemoteException;
import java.util.Map;
import java.util.Properties;

public class EchoClient implements Client {

    public static final String SERVICE_ENDPOINT = "Service.Endpoint";

    private EchoServiceStub stub;

    @Override
    public void init(Properties properties) throws RPCEvaluatorException {
        String endpoint = properties.getProperty(SERVICE_ENDPOINT);
        if (endpoint == null || "".equals(endpoint)) {
            throw new RPCEvaluatorException("Service.Endpoint parameter not specified");
        }
        try {
            stub = new EchoServiceStub(endpoint);
        } catch (AxisFault axisFault) {
            throw new RPCEvaluatorException("Error while initializing SOAP client", axisFault);
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
        try {
            stub.cleanup();
        } catch (AxisFault axisFault) {
            System.err.println("Error while cleaning up SOAP client");
            axisFault.printStackTrace();
        }
    }

    @Override
    public InvocationResult doNothing() {
        EchoServiceStub.DoNothing doNothing = new EchoServiceStub.DoNothing();
        Throwable t = null;

        long start = System.currentTimeMillis();
        try {
            stub.doNothing(doNothing);
        } catch (RemoteException e) {
            t = e;
        }
        long end = System.currentTimeMillis();

        return report(start, end, t);
    }

    @Override
    public InvocationResult echoString(String s) {
        EchoServiceStub.EchoString echoString = new EchoServiceStub.EchoString();
        Throwable t = null;
        echoString.setArgs0(s);
        EchoServiceStub.EchoStringResponse response = null;

        long start = System.currentTimeMillis();
        try {
            response = stub.echoString(echoString);
        } catch (RemoteException e) {
            t = e;
        }
        long end = System.currentTimeMillis();

        if (response == null || !response.get_return().equals(s)) {
            t = new RPCEvaluatorException("Invalid echo response");
        }
        return report(start, end, t);
    }

    @Override
    public InvocationResult echoInt(int i) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public InvocationResult echoArray(int[] array) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public InvocationResult echoObject(DataObject o) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public InvocationResult echoMap(Map<Integer, Integer> map) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public InvocationResult echoBlob(byte[] blob) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
