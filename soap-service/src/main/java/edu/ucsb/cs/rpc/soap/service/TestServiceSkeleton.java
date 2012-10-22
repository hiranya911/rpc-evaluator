/**
 * TestServiceSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.0-SNAPSHOT  Built on : Oct 22, 2012 (01:35:57 GMT)
 */
package edu.ucsb.cs.rpc.soap.service;

import edu.ucsb.cs.rpc.soap.service.types.Entry1;
import edu.ucsb.cs.rpc.soap.service.types.Entry2;
import edu.ucsb.cs.rpc.soap.service.types.Map2;

/**
 *  TestServiceSkeleton java skeleton for the axisService
 */
public class TestServiceSkeleton {
    /**
     * Auto generated method signature
     *
     * @param echoObject
     * @return echoObjectResponse
     */
    public edu.ucsb.cs.rpc.soap.service.EchoObjectResponse echoObject(
        edu.ucsb.cs.rpc.soap.service.EchoObject echoObject) {
        EchoObjectResponse response = new EchoObjectResponse();
        response.set_return(echoObject.getArgs0());
        return response;
    }

    /**
     * Auto generated method signature
     *
     * @param echoBlob
     * @return echoBlobResponse
     */
    public edu.ucsb.cs.rpc.soap.service.EchoBlobResponse echoBlob(
        edu.ucsb.cs.rpc.soap.service.EchoBlob echoBlob) {
        EchoBlobResponse response = new EchoBlobResponse();
        response.set_return(echoBlob.getArgs0());
        return response;
    }

    /**
     * Auto generated method signature
     *
     * @param echoMap
     * @return echoMapResponse
     */
    public edu.ucsb.cs.rpc.soap.service.EchoMapResponse echoMap(
        edu.ucsb.cs.rpc.soap.service.EchoMap echoMap) {
        EchoMapResponse response = new EchoMapResponse();
        Entry1[] entries = echoMap.getArgs0().getEntry();
        Map2 map2 = new Map2();
        for (Entry1 entry : entries) {
            Entry2 entry2 = new Entry2();
            entry2.setKey(entry.getKey());
            entry2.setValue(entry.getValue());
            map2.addEntry(entry2);
        }
        response.set_return(map2);
        return response;
    }

    /**
     * Auto generated method signature
     *
     * @param echoInt
     * @return echoIntResponse
     */
    public edu.ucsb.cs.rpc.soap.service.EchoIntResponse echoInt(
        edu.ucsb.cs.rpc.soap.service.EchoInt echoInt) {
        EchoIntResponse response = new EchoIntResponse();
        response.set_return(echoInt.getArgs0());
        return response;
    }

    /**
     * Auto generated method signature
     *
     * @param echoArray
     * @return echoArrayResponse
     */
    public edu.ucsb.cs.rpc.soap.service.EchoArrayResponse echoArray(
        edu.ucsb.cs.rpc.soap.service.EchoArray echoArray) {
        EchoArrayResponse response = new EchoArrayResponse();
        response.set_return(echoArray.getArgs0());
        return response;
    }

    /**
     * Auto generated method signature
     *
     * @param echoString
     * @return echoStringResponse
     */
    public edu.ucsb.cs.rpc.soap.service.EchoStringResponse echoString(
        edu.ucsb.cs.rpc.soap.service.EchoString echoString) {
        EchoStringResponse response = new EchoStringResponse();
        response.set_return(echoString.getArgs0());
        return response;
    }

    /**
     * Auto generated method signature
     *
     * @param doNothing
     * @return
     */
    public void doNothing(edu.ucsb.cs.rpc.soap.service.DoNothing doNothing) {

    }
}
