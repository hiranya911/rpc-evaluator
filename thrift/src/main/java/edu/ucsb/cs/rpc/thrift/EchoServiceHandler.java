package edu.ucsb.cs.rpc.thrift;

import org.apache.thrift.TException;

public class EchoServiceHandler implements EchoService.Iface {

    @Override
    public void doNothing() throws TException {

    }

    @Override
    public String echoString(String str) throws TException {
        return str;
    }
}
