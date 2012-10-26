package edu.ucsb.cs.rpc.thrift;

import edu.ucsb.cs.rpc.base.Client;
import edu.ucsb.cs.rpc.base.DataObject;
import edu.ucsb.cs.rpc.base.InvocationResult;
import edu.ucsb.cs.rpc.base.RPCEvaluatorException;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import java.util.Map;
import java.util.Properties;

public class EchoClient implements Client {

    public static final String THRIFT_HOST = "Thrift.Host";
    public static final String THRIFT_PORT = "Thrift.Port";

    private TTransport transport;
    private EchoService.Client client;

    @Override
    public void init(Properties properties) throws RPCEvaluatorException {
        String host = properties.getProperty(THRIFT_HOST);
        String port = properties.getProperty(THRIFT_PORT);
        if (host == null || port == null || "".equals(host) || "".equals(port)) {
            throw new RPCEvaluatorException("Required Thrift settings not provided");
        }
        try {
            transport = new TSocket("localhost", 9090);
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            client = new EchoService.Client(protocol);
        } catch (TException e) {
            throw new RPCEvaluatorException("Error while initializing Thrift client", e);
        }
    }

    @Override
    public void destroy() {
        transport.close();
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
            client.doNothing();
        } catch (TException e) {
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
            response = client.echoString(s);
        } catch (TException e) {
            t = e;
        }
        long end = System.currentTimeMillis();
        if (!s.equals(response)) {
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
