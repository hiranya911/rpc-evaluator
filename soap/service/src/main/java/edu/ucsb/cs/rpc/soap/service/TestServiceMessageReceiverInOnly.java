/**
 * TestServiceMessageReceiverInOnly.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.0-SNAPSHOT  Built on : Oct 22, 2012 (01:35:57 GMT)
 */
package edu.ucsb.cs.rpc.soap.service;


/**
 *  TestServiceMessageReceiverInOnly message receiver
 */
public class TestServiceMessageReceiverInOnly extends org.apache.axis2.receivers.AbstractMessageReceiver {
    public void invokeBusinessLogic(
        org.apache.axis2.context.MessageContext msgContext)
        throws org.apache.axis2.AxisFault {
        try {
            // get the implementation class for the Web Service
            Object obj = getTheImplementationObject(msgContext);

            TestServiceSkeleton skel = (TestServiceSkeleton) obj;

            //Out Envelop
            org.apache.axiom.soap.SOAPEnvelope envelope = null;

            //Find the axisOperation that has been set by the Dispatch phase.
            org.apache.axis2.description.AxisOperation op = msgContext.getOperationContext()
                                                                      .getAxisOperation();

            if (op == null) {
                throw new org.apache.axis2.AxisFault(
                    "Operation is not located, if this is doclit style the SOAP-ACTION should specified via the SOAP Action to use the RawXMLProvider");
            }

            java.lang.String methodName;

            if ((op.getName() != null) &&
                    ((methodName = org.apache.axis2.util.JavaUtils.xmlNameToJavaIdentifier(
                            op.getName().getLocalPart())) != null)) {
                if ("doNothing".equals(methodName)) {
                    //doc style
                    edu.ucsb.cs.rpc.soap.service.DoNothing wrappedParam = (edu.ucsb.cs.rpc.soap.service.DoNothing) fromOM(msgContext.getEnvelope()
                                                                                                                                    .getBody()
                                                                                                                                    .getFirstElement(),
                            edu.ucsb.cs.rpc.soap.service.DoNothing.class);

                    skel.doNothing(wrappedParam);

                    envelope = getSOAPFactory(msgContext).getDefaultEnvelope();
                } else {
                    throw new java.lang.RuntimeException("method not found");
                }
            }
        } catch (java.lang.Exception e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    //
    private org.apache.axiom.om.OMElement toOM(
        edu.ucsb.cs.rpc.soap.service.EchoObject param, boolean optimizeContent)
        throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(edu.ucsb.cs.rpc.soap.service.EchoObject.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        edu.ucsb.cs.rpc.soap.service.EchoObjectResponse param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(edu.ucsb.cs.rpc.soap.service.EchoObjectResponse.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        edu.ucsb.cs.rpc.soap.service.EchoBlob param, boolean optimizeContent)
        throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(edu.ucsb.cs.rpc.soap.service.EchoBlob.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        edu.ucsb.cs.rpc.soap.service.EchoBlobResponse param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(edu.ucsb.cs.rpc.soap.service.EchoBlobResponse.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        edu.ucsb.cs.rpc.soap.service.EchoMap param, boolean optimizeContent)
        throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(edu.ucsb.cs.rpc.soap.service.EchoMap.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        edu.ucsb.cs.rpc.soap.service.EchoMapResponse param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(edu.ucsb.cs.rpc.soap.service.EchoMapResponse.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        edu.ucsb.cs.rpc.soap.service.EchoInt param, boolean optimizeContent)
        throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(edu.ucsb.cs.rpc.soap.service.EchoInt.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        edu.ucsb.cs.rpc.soap.service.EchoIntResponse param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(edu.ucsb.cs.rpc.soap.service.EchoIntResponse.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        edu.ucsb.cs.rpc.soap.service.EchoArray param, boolean optimizeContent)
        throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(edu.ucsb.cs.rpc.soap.service.EchoArray.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        edu.ucsb.cs.rpc.soap.service.EchoArrayResponse param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(edu.ucsb.cs.rpc.soap.service.EchoArrayResponse.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        edu.ucsb.cs.rpc.soap.service.EchoString param, boolean optimizeContent)
        throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(edu.ucsb.cs.rpc.soap.service.EchoString.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        edu.ucsb.cs.rpc.soap.service.EchoStringResponse param,
        boolean optimizeContent) throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(edu.ucsb.cs.rpc.soap.service.EchoStringResponse.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.om.OMElement toOM(
        edu.ucsb.cs.rpc.soap.service.DoNothing param, boolean optimizeContent)
        throws org.apache.axis2.AxisFault {
        try {
            return param.getOMElement(edu.ucsb.cs.rpc.soap.service.DoNothing.MY_QNAME,
                org.apache.axiom.om.OMAbstractFactory.getOMFactory());
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory,
        edu.ucsb.cs.rpc.soap.service.EchoObjectResponse param,
        boolean optimizeContent, javax.xml.namespace.QName elementQName)
        throws org.apache.axis2.AxisFault {
        try {
            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();

            emptyEnvelope.getBody()
                         .addChild(param.getOMElement(
                    edu.ucsb.cs.rpc.soap.service.EchoObjectResponse.MY_QNAME,
                    factory));

            return emptyEnvelope;
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private edu.ucsb.cs.rpc.soap.service.EchoObjectResponse wrapechoObject() {
        edu.ucsb.cs.rpc.soap.service.EchoObjectResponse wrappedElement = new edu.ucsb.cs.rpc.soap.service.EchoObjectResponse();

        return wrappedElement;
    }

    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory,
        edu.ucsb.cs.rpc.soap.service.EchoBlobResponse param,
        boolean optimizeContent, javax.xml.namespace.QName elementQName)
        throws org.apache.axis2.AxisFault {
        try {
            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();

            emptyEnvelope.getBody()
                         .addChild(param.getOMElement(
                    edu.ucsb.cs.rpc.soap.service.EchoBlobResponse.MY_QNAME,
                    factory));

            return emptyEnvelope;
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private edu.ucsb.cs.rpc.soap.service.EchoBlobResponse wrapechoBlob() {
        edu.ucsb.cs.rpc.soap.service.EchoBlobResponse wrappedElement = new edu.ucsb.cs.rpc.soap.service.EchoBlobResponse();

        return wrappedElement;
    }

    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory,
        edu.ucsb.cs.rpc.soap.service.EchoMapResponse param,
        boolean optimizeContent, javax.xml.namespace.QName elementQName)
        throws org.apache.axis2.AxisFault {
        try {
            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();

            emptyEnvelope.getBody()
                         .addChild(param.getOMElement(
                    edu.ucsb.cs.rpc.soap.service.EchoMapResponse.MY_QNAME,
                    factory));

            return emptyEnvelope;
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private edu.ucsb.cs.rpc.soap.service.EchoMapResponse wrapechoMap() {
        edu.ucsb.cs.rpc.soap.service.EchoMapResponse wrappedElement = new edu.ucsb.cs.rpc.soap.service.EchoMapResponse();

        return wrappedElement;
    }

    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory,
        edu.ucsb.cs.rpc.soap.service.EchoIntResponse param,
        boolean optimizeContent, javax.xml.namespace.QName elementQName)
        throws org.apache.axis2.AxisFault {
        try {
            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();

            emptyEnvelope.getBody()
                         .addChild(param.getOMElement(
                    edu.ucsb.cs.rpc.soap.service.EchoIntResponse.MY_QNAME,
                    factory));

            return emptyEnvelope;
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private edu.ucsb.cs.rpc.soap.service.EchoIntResponse wrapechoInt() {
        edu.ucsb.cs.rpc.soap.service.EchoIntResponse wrappedElement = new edu.ucsb.cs.rpc.soap.service.EchoIntResponse();

        return wrappedElement;
    }

    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory,
        edu.ucsb.cs.rpc.soap.service.EchoArrayResponse param,
        boolean optimizeContent, javax.xml.namespace.QName elementQName)
        throws org.apache.axis2.AxisFault {
        try {
            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();

            emptyEnvelope.getBody()
                         .addChild(param.getOMElement(
                    edu.ucsb.cs.rpc.soap.service.EchoArrayResponse.MY_QNAME,
                    factory));

            return emptyEnvelope;
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private edu.ucsb.cs.rpc.soap.service.EchoArrayResponse wrapechoArray() {
        edu.ucsb.cs.rpc.soap.service.EchoArrayResponse wrappedElement = new edu.ucsb.cs.rpc.soap.service.EchoArrayResponse();

        return wrappedElement;
    }

    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory,
        edu.ucsb.cs.rpc.soap.service.EchoStringResponse param,
        boolean optimizeContent, javax.xml.namespace.QName elementQName)
        throws org.apache.axis2.AxisFault {
        try {
            org.apache.axiom.soap.SOAPEnvelope emptyEnvelope = factory.getDefaultEnvelope();

            emptyEnvelope.getBody()
                         .addChild(param.getOMElement(
                    edu.ucsb.cs.rpc.soap.service.EchoStringResponse.MY_QNAME,
                    factory));

            return emptyEnvelope;
        } catch (org.apache.axis2.databinding.ADBException e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }
    }

    private edu.ucsb.cs.rpc.soap.service.EchoStringResponse wrapechoString() {
        edu.ucsb.cs.rpc.soap.service.EchoStringResponse wrappedElement = new edu.ucsb.cs.rpc.soap.service.EchoStringResponse();

        return wrappedElement;
    }

    /**
     *  get the default envelope
     */
    private org.apache.axiom.soap.SOAPEnvelope toEnvelope(
        org.apache.axiom.soap.SOAPFactory factory) {
        return factory.getDefaultEnvelope();
    }

    private java.lang.Object fromOM(org.apache.axiom.om.OMElement param,
        java.lang.Class type) throws org.apache.axis2.AxisFault {
        try {
            if (edu.ucsb.cs.rpc.soap.service.DoNothing.class.equals(type)) {
                return edu.ucsb.cs.rpc.soap.service.DoNothing.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (edu.ucsb.cs.rpc.soap.service.EchoArray.class.equals(type)) {
                return edu.ucsb.cs.rpc.soap.service.EchoArray.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (edu.ucsb.cs.rpc.soap.service.EchoArrayResponse.class.equals(
                        type)) {
                return edu.ucsb.cs.rpc.soap.service.EchoArrayResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (edu.ucsb.cs.rpc.soap.service.EchoBlob.class.equals(type)) {
                return edu.ucsb.cs.rpc.soap.service.EchoBlob.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (edu.ucsb.cs.rpc.soap.service.EchoBlobResponse.class.equals(type)) {
                return edu.ucsb.cs.rpc.soap.service.EchoBlobResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (edu.ucsb.cs.rpc.soap.service.EchoInt.class.equals(type)) {
                return edu.ucsb.cs.rpc.soap.service.EchoInt.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (edu.ucsb.cs.rpc.soap.service.EchoIntResponse.class.equals(type)) {
                return edu.ucsb.cs.rpc.soap.service.EchoIntResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (edu.ucsb.cs.rpc.soap.service.EchoMap.class.equals(type)) {
                return edu.ucsb.cs.rpc.soap.service.EchoMap.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (edu.ucsb.cs.rpc.soap.service.EchoMapResponse.class.equals(type)) {
                return edu.ucsb.cs.rpc.soap.service.EchoMapResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (edu.ucsb.cs.rpc.soap.service.EchoObject.class.equals(type)) {
                return edu.ucsb.cs.rpc.soap.service.EchoObject.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (edu.ucsb.cs.rpc.soap.service.EchoObjectResponse.class.equals(
                        type)) {
                return edu.ucsb.cs.rpc.soap.service.EchoObjectResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (edu.ucsb.cs.rpc.soap.service.EchoString.class.equals(type)) {
                return edu.ucsb.cs.rpc.soap.service.EchoString.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }

            if (edu.ucsb.cs.rpc.soap.service.EchoStringResponse.class.equals(
                        type)) {
                return edu.ucsb.cs.rpc.soap.service.EchoStringResponse.Factory.parse(param.getXMLStreamReaderWithoutCaching());
            }
        } catch (java.lang.Exception e) {
            throw org.apache.axis2.AxisFault.makeFault(e);
        }

        return null;
    }

    private org.apache.axis2.AxisFault createAxisFault(java.lang.Exception e) {
        org.apache.axis2.AxisFault f;
        Throwable cause = e.getCause();

        if (cause != null) {
            f = new org.apache.axis2.AxisFault(e.getMessage(), cause);
        } else {
            f = new org.apache.axis2.AxisFault(e.getMessage());
        }

        return f;
    }
} //end of class
