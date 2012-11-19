package edu.ucsb.cs.rpc.ice;

import java.util.Map;

import Ice.Current;
import edu.ucsb.cs.rpc.base.DataObject;

public class EchoI extends edu.ucsb.cs.rpc.ice.service._EchoDisp {

	@Override
	public void doNothing(Current __current) {
		
	}

	@Override
	public String echoString(String s, Current __current) {
		return s;
	}

	@Override
	public int echoInt(int i, Current __current) {
		return i;
	}

	@Override
	public int[] echoArray(int[] array, Current __current) {
		return array;
	}

	@Override
	public byte[] echoBlob(byte[] blob, Current __current) {
		return blob;
	}

	@Override
	public DataObject echoObject(DataObject obj, Current __current) {
		return obj;
	}

	@Override
	public Map<Integer, Integer> echoMap(Map<Integer, Integer> map,
			Current __current) {
		return map;
	}
}