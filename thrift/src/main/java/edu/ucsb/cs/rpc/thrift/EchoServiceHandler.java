package edu.ucsb.cs.rpc.thrift;

import org.apache.thrift.TException;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;

public class EchoServiceHandler implements EchoService.Iface {

    @Override
    public void doNothing() throws TException {

    }

    @Override
    public String echoString(String str) throws TException {
        return str;
    }

    @Override
    public int echoInt(int number) throws TException {
        return number;
    }

    @Override
    public List<Integer> echoArray(List<Integer> array) throws TException {
        return array;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public DataObject echoObject(DataObject obj) throws TException {
        return obj;
    }

    @Override
    public Map<Integer, Integer> echoMap(Map<Integer, Integer> m) throws TException {
        return m;
    }

    @Override
    public ByteBuffer echoBlob(ByteBuffer blob) throws TException {
        return blob;
    }
}
