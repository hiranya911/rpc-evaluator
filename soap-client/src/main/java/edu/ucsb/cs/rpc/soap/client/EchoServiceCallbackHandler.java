/**
 * EchoServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.0-SNAPSHOT  Built on : Oct 22, 2012 (01:35:57 GMT)
 */
package edu.ucsb.cs.rpc.soap.client;


/**
 *  EchoServiceCallbackHandler Callback class, Users can extend this class and implement
 *  their own receiveResult and receiveError methods.
 */
public abstract class EchoServiceCallbackHandler {
    protected Object clientData;

    /**
     * User can pass in any object that needs to be accessed once the NonBlocking
     * Web service call is finished and appropriate method of this CallBack is called.
     * @param clientData Object mechanism by which the user can pass in user data
     * that will be avilable at the time this callback is called.
     */
    public EchoServiceCallbackHandler(Object clientData) {
        this.clientData = clientData;
    }

    /**
     * Please use this constructor if you don't want to set any clientData
     */
    public EchoServiceCallbackHandler() {
        this.clientData = null;
    }

    /**
     * Get the client data
     */
    public Object getClientData() {
        return clientData;
    }

    /**
     * auto generated Axis2 call back method for echoObject method
     * override this method for handling normal response from echoObject operation
     */
    public void receiveResultechoObject(
        EchoServiceStub.EchoObjectResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from echoObject operation
     */
    public void receiveErrorechoObject(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for echoBlob method
     * override this method for handling normal response from echoBlob operation
     */
    public void receiveResultechoBlob(
        EchoServiceStub.EchoBlobResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from echoBlob operation
     */
    public void receiveErrorechoBlob(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for echoMap method
     * override this method for handling normal response from echoMap operation
     */
    public void receiveResultechoMap(
        EchoServiceStub.EchoMapResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from echoMap operation
     */
    public void receiveErrorechoMap(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for echoInt method
     * override this method for handling normal response from echoInt operation
     */
    public void receiveResultechoInt(
        EchoServiceStub.EchoIntResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from echoInt operation
     */
    public void receiveErrorechoInt(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for echoArray method
     * override this method for handling normal response from echoArray operation
     */
    public void receiveResultechoArray(
        EchoServiceStub.EchoArrayResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from echoArray operation
     */
    public void receiveErrorechoArray(java.lang.Exception e) {
    }

    /**
     * auto generated Axis2 call back method for echoString method
     * override this method for handling normal response from echoString operation
     */
    public void receiveResultechoString(
        EchoServiceStub.EchoStringResponse result) {
    }

    /**
     * auto generated Axis2 Error handler
     * override this method for handling error response from echoString operation
     */
    public void receiveErrorechoString(java.lang.Exception e) {
    }

    // No methods generated for meps other than in-out
}
