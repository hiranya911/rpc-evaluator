package edu.ucsb.cs.rpc.thrift;

import edu.ucsb.cs.rpc.base.Client;
import edu.ucsb.cs.rpc.base.DataObject;
import edu.ucsb.cs.rpc.base.InvocationResult;
import edu.ucsb.cs.rpc.base.RPCEvaluatorException;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.THttpClient;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import java.nio.ByteBuffer;
import java.util.*;

public class EchoClient implements Client {

    public static final String THRIFT_HOST = "Thrift.Host";
    public static final String THRIFT_PORT = "Thrift.Port";
    public static final String THRIFT_HTTP_URL = "Thrift.Http.URL";

    private TTransport transport;
    private EchoService.Client client;

    @Override
    public void init(Properties properties) throws RPCEvaluatorException {
        String host = properties.getProperty(THRIFT_HOST);
        String port = properties.getProperty(THRIFT_PORT);
        String url = properties.getProperty(THRIFT_HTTP_URL);

        try {
            if (host != null && port != null) {
                System.out.println("Initializing Thrift socket transport");
                transport = new TSocket(host, Integer.parseInt(port));
            } else if (url != null) {
                System.out.println("Initializing Thrift HTTP transport");
                HttpClient httpClient = new DefaultHttpClient();
                transport = new THttpClient(url, httpClient);
            } else {
                throw new RPCEvaluatorException("Required Thrift settings not provided");
            }

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
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        if (!s.equals(response)) {
            t = new RPCEvaluatorException("Invalid echo response");
        }
        return report(start, end, t);
    }

    @Override
    public InvocationResult echoInt(int i) {
        Throwable t = null;
        int response = -1;

        long start = System.currentTimeMillis();
        try {
            response = client.echoInt(i);
        } catch (TException e) {
            t = e;
        }
        long end = System.currentTimeMillis();
        if (i != response) {
            t = new RPCEvaluatorException("Invalid echo response");
        }
        return report(start, end, t);
    }

    @Override
    public InvocationResult echoArray(int[] array) {
        Throwable t = null;
        List<Integer> response = null;

        long start = System.currentTimeMillis();
        List<Integer> list = new ArrayList<Integer>();
        for (int i : array) {
            list.add(i);
        }
        try {
            response = client.echoArray(list);
        } catch (TException e) {
            t = e;
        }
        long end = System.currentTimeMillis();
        if (response == null || !Arrays.equals(list.toArray(new Integer[list.size()]),
                response.toArray(new Integer[response.size()]))) {
            t = new RPCEvaluatorException("Invalid echo response");
        }
        return report(start, end, t);
    }

    @Override
    public InvocationResult echoObject(DataObject o) {
        edu.ucsb.cs.rpc.thrift.DataObject obj = new edu.ucsb.cs.rpc.thrift.DataObject(
                o.getString(), o.getInteger(), o.getDecimal(), (byte) o.getCharacter());
        Throwable t = null;
        edu.ucsb.cs.rpc.thrift.DataObject response = null;

        long start = System.currentTimeMillis();
        try {
            response = client.echoObject(obj);
        } catch (TException e) {
            t = e;
        }
        long end = System.currentTimeMillis();
        if (response == null) {
            t = new RPCEvaluatorException("Invalid echo response");
        } else if (!response.getStr().equals(o.getString()) ||
                    response.getNumber() != o.getInteger() ||
                    response.getDecimal() != o.getDecimal() ||
                    response.getCharacter() != (byte) o.getCharacter()) {
                t = new RPCEvaluatorException("Invalid echo response");
        }
        return report(start, end, t);
    }

    @Override
    public InvocationResult echoMap(Map<Integer, Integer> map) {
        Throwable t = null;
        Map<Integer,Integer> response = null;

        long start = System.currentTimeMillis();
        try {
            response = client.echoMap(map);
        } catch (TException e) {
            t = e;
        }
        long end = System.currentTimeMillis();
        if (response == null || !equalMaps(map, response)) {
            t = new RPCEvaluatorException("Invalid echo response");
        }
        return report(start, end, t);
    }

    @Override
    public InvocationResult echoBlob(byte[] blob) {
        Throwable t = null;
        ByteBuffer response = null;

        long start = System.currentTimeMillis();
        try {
            response = client.echoBlob(ByteBuffer.wrap(blob));
        } catch (TException e) {
            t = e;
        }
        long end = System.currentTimeMillis();
        if (response == null || !Arrays.equals(blob, response.array())) {
            t = new RPCEvaluatorException("Invalid echo response");
        }
        return report(start, end, t);
    }

    boolean equalMaps(Map<Integer,Integer> m1, Map<Integer,Integer> m2) {
        if (m1.size() != m2.size()) {
            return false;
        }
        for (Map.Entry<Integer,Integer> entry : m1.entrySet()) {
            if (!m2.containsKey(entry.getKey()) ||
                    m2.get(entry.getKey()) != (int) entry.getValue()) {
                return false;
            }
        }
        return true;
    }
}
