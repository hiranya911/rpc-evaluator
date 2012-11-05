package src.main.java.edu.ucsb.cs.rpc.json.interfaces;

import java.util.Map;

import edu.ucsb.cs.rpc.base.DataObject;
import edu.ucsb.cs.rpc.base.Server;


public class JsonServerImpl implements Server {

	public void doNothing() {
	}

	public String echoString(String s) {
		return s;
	}

	public int echoInt(int i) {
		return i;
	}

	public int[] echoArray(int[] array) {
		return array;
	}

	public DataObject echoObject(DataObject o) {
		return o;
	}

	public Map<Integer, Integer> echoMap(Map<Integer, Integer> map) {
		return map;
	}

	public byte[] echoBlob(byte[] blob) {
		return blob;
	}

}
