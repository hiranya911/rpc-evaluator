/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.0-SNAPSHOT  Built on : Oct 22, 2012 (01:36:15 GMT)
 */
package edu.ucsb.cs.rpc.soap.service.types;


/**
 *  ExtensionMapper class
 */
@SuppressWarnings({"unchecked",
    "unused"
})
public class ExtensionMapper {
    public static java.lang.Object getTypeObject(
        java.lang.String namespaceURI, java.lang.String typeName,
        javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception {
        if ("http://ws.apache.org/namespaces/axis2/map".equals(namespaceURI) &&
                "map2".equals(typeName)) {
            return edu.ucsb.cs.rpc.soap.service.types.Map2.Factory.parse(reader);
        }

        if ("http://base.rpc.cs.ucsb.edu/xsd".equals(namespaceURI) &&
                "DataObject".equals(typeName)) {
            return edu.ucsb.cs.rpc.soap.service.types.DataObject.Factory.parse(reader);
        }

        if ("http://ws.apache.org/namespaces/axis2/map".equals(namespaceURI) &&
                "map1".equals(typeName)) {
            return edu.ucsb.cs.rpc.soap.service.types.Map1.Factory.parse(reader);
        }

        if ("http://ws.apache.org/namespaces/axis2/map".equals(namespaceURI) &&
                "entry1".equals(typeName)) {
            return edu.ucsb.cs.rpc.soap.service.types.Entry1.Factory.parse(reader);
        }

        if ("http://ws.apache.org/namespaces/axis2/map".equals(namespaceURI) &&
                "entry2".equals(typeName)) {
            return edu.ucsb.cs.rpc.soap.service.types.Entry2.Factory.parse(reader);
        }

        throw new org.apache.axis2.databinding.ADBException("Unsupported type " +
            namespaceURI + " " + typeName);
    }
}
