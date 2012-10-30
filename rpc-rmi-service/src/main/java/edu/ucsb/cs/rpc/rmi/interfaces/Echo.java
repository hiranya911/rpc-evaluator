package edu.ucsb.cs.rpc.rmi.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Map;

import edu.ucsb.cs.rpc.base.DataObject;

/**
 * 
 */

/**
 * @author mikeagun
 *
 */
public interface Echo extends Remote{
	String echoString(String s) throws RemoteException;
	int echoInt(int i) throws RemoteException;
	int[] echoArray(int[] array) throws RemoteException;
	DataObject echoObject(DataObject o) throws RemoteException;
	Map<Integer, Integer> echoMap(Map<Integer, Integer> map) throws RemoteException;
	byte[] echoBlob(byte[] blob) throws RemoteException;
	
	void doNothing() throws RemoteException;
}
