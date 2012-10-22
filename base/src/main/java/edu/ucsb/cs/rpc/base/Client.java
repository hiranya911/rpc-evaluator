package edu.ucsb.cs.rpc.base;

import java.util.Map;
import java.util.Properties;

public interface Client {

    public void init(Properties properties) throws RPCEvaluatorException;

    public void destroy();

    public InvocationResult doNothing();

    public InvocationResult echoString(String s);

    public InvocationResult echoInt(int i);

    public InvocationResult echoArray(int[] array);

    public InvocationResult echoObject(DataObject o);

    public InvocationResult echoMap(Map<Integer,Integer> map);

    public InvocationResult echoBlob(byte[] blob);

}
