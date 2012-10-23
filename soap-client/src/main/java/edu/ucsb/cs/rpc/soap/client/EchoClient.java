package edu.ucsb.cs.rpc.soap.client;

import edu.ucsb.cs.rpc.base.Client;
import edu.ucsb.cs.rpc.base.DataObject;
import edu.ucsb.cs.rpc.base.InvocationResult;
import edu.ucsb.cs.rpc.base.RPCEvaluatorException;
import org.apache.axiom.attachments.ByteArrayDataSource;
import org.apache.axis2.AxisFault;
import org.apache.axis2.databinding.types.UnsignedShort;

import javax.activation.DataHandler;
import java.rmi.RemoteException;
import java.util.Arrays;
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
        EchoServiceStub.EchoInt echoInt = new EchoServiceStub.EchoInt();
        Throwable t = null;
        echoInt.setArgs0(i);
        EchoServiceStub.EchoIntResponse response = null;

        long start = System.currentTimeMillis();
        try {
            response = stub.echoInt(echoInt);
        } catch (RemoteException e) {
            t = e;
        }
        long end = System.currentTimeMillis();

        if (response == null || response.get_return() != i) {
            t = new RPCEvaluatorException("Invalid echo response");
        }
        return report(start, end, t);
    }

    @Override
    public InvocationResult echoArray(int[] array) {
        EchoServiceStub.EchoArray echoArray = new EchoServiceStub.EchoArray();
        Throwable t = null;
        echoArray.setArgs0(array);
        EchoServiceStub.EchoArrayResponse response = null;

        long start = System.currentTimeMillis();
        try {
            response = stub.echoArray(echoArray);
        } catch (RemoteException e) {
            t = e;
        }
        long end = System.currentTimeMillis();

        if (response == null || !Arrays.equals(response.get_return(), array)) {
            t = new RPCEvaluatorException("Invalid echo response");
        }
        return report(start, end, t);
    }

    @Override
    public InvocationResult echoObject(DataObject o) {
        EchoServiceStub.EchoObject echoObject = new EchoServiceStub.EchoObject();
        EchoServiceStub.DataObject dataObject = new EchoServiceStub.DataObject();
        Throwable t = null;
        dataObject.setCharacter(new UnsignedShort(o.getCharacter()));
        dataObject.setDecimal(o.getDecimal());
        dataObject.setString(o.getString());
        dataObject.setInteger(o.getInteger());
        echoObject.setArgs0(dataObject);
        EchoServiceStub.EchoObjectResponse response = null;

        long start = System.currentTimeMillis();
        try {
            response = stub.echoObject(echoObject);
        } catch (RemoteException e) {
            t = e;
        }
        long end = System.currentTimeMillis();

        if (response == null) {
            t = new RPCEvaluatorException("Invalid echo response");
        } else {
            EchoServiceStub.DataObject echo = response.get_return();
            if (!echo.getCharacter().equals(dataObject.getCharacter()) ||
                    echo.getDecimal() != dataObject.getDecimal() ||
                    echo.getInteger() != dataObject.getInteger() ||
                    !echo.getString().equals(dataObject.getString())) {
                t = new RPCEvaluatorException("Invalid echo response");
            }
        }
        return report(start, end, t);
    }

    @Override
    public InvocationResult echoMap(Map<Integer, Integer> map) {
        EchoServiceStub.EchoMap echoMap = new EchoServiceStub.EchoMap();
        EchoServiceStub.Map1 map1 = new EchoServiceStub.Map1();
        Throwable t = null;
        for (Map.Entry<Integer,Integer> entry : map.entrySet()) {
            EchoServiceStub.Entry1 entry1 = new EchoServiceStub.Entry1();
            entry1.setKey(entry.getKey());
            entry1.setValue(entry.getValue());
            map1.addEntry(entry1);
        }
        echoMap.setArgs0(map1);
        EchoServiceStub.EchoMapResponse response = null;

        long start = System.currentTimeMillis();
        try {
            response = stub.echoMap(echoMap);
        } catch (RemoteException e) {
            t = e;
        }
        long end = System.currentTimeMillis();

        if (response == null) {
            t = new RPCEvaluatorException("Invalid echo response");
        } else {
            EchoServiceStub.Entry2[] entry2 = response.get_return().getEntry();
            if (map.size() != entry2.length) {
                t = new RPCEvaluatorException("Invalid echo response");
            }
            for (EchoServiceStub.Entry2 e : entry2) {
                if (!map.containsKey(e.getKey()) || map.get(e.getKey()) != e.getValue()) {
                    t = new RPCEvaluatorException("Invalid echo response");
                    break;
                }
            }
        }
        return report(start, end, t);
    }

    @Override
    public InvocationResult echoBlob(byte[] blob) {
        EchoServiceStub.EchoBlob echoBlob = new EchoServiceStub.EchoBlob();
        Throwable t = null;
        ByteArrayDataSource dataSource = new ByteArrayDataSource(blob);
        echoBlob.setArgs0(new DataHandler(dataSource));
        EchoServiceStub.EchoBlobResponse response = null;

        long start = System.currentTimeMillis();
        try {
            response = stub.echoBlob(echoBlob);
        } catch (RemoteException e) {
            t = e;
        }
        long end = System.currentTimeMillis();

        if (response == null) {
            t = new RPCEvaluatorException("Invalid echo response");
        }
        return report(start, end, t);
    }
}
