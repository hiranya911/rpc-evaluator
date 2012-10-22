package edu.ucsb.cs.rpc.base;

import java.util.Map;

public interface Server {

    public void doNothing();

    public String echoString(String s);

    public int echoInt(int i);

    public int[] echoArray(int[] array);

    public DataObject echoObject(DataObject o);

    public Map<Integer,Integer> echoMap(Map<Integer,Integer> map);

    public byte[] echoBlob(byte[] blob);

}
