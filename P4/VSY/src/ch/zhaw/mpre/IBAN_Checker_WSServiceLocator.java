/**
 * IBAN_Checker_WSServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ch.zhaw.mpre;

public class IBAN_Checker_WSServiceLocator extends org.apache.axis.client.Service implements ch.zhaw.mpre.IBAN_Checker_WSService {

    public IBAN_Checker_WSServiceLocator() {
    }


    public IBAN_Checker_WSServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public IBAN_Checker_WSServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for IBAN_CheckerPort
    private java.lang.String IBAN_CheckerPort_address = "http://waikiki.zhaw.ch:8888/soaps";

    public java.lang.String getIBAN_CheckerPortAddress() {
        return IBAN_CheckerPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String IBAN_CheckerPortWSDDServiceName = "IBAN_CheckerPort";

    public java.lang.String getIBAN_CheckerPortWSDDServiceName() {
        return IBAN_CheckerPortWSDDServiceName;
    }

    public void setIBAN_CheckerPortWSDDServiceName(java.lang.String name) {
        IBAN_CheckerPortWSDDServiceName = name;
    }

    public ch.zhaw.mpre.IBAN_Checker getIBAN_CheckerPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(IBAN_CheckerPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getIBAN_CheckerPort(endpoint);
    }

    public ch.zhaw.mpre.IBAN_Checker getIBAN_CheckerPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            ch.zhaw.mpre.IBAN_CheckerPortBindingStub _stub = new ch.zhaw.mpre.IBAN_CheckerPortBindingStub(portAddress, this);
            _stub.setPortName(getIBAN_CheckerPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setIBAN_CheckerPortEndpointAddress(java.lang.String address) {
        IBAN_CheckerPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (ch.zhaw.mpre.IBAN_Checker.class.isAssignableFrom(serviceEndpointInterface)) {
                ch.zhaw.mpre.IBAN_CheckerPortBindingStub _stub = new ch.zhaw.mpre.IBAN_CheckerPortBindingStub(new java.net.URL(IBAN_CheckerPort_address), this);
                _stub.setPortName(getIBAN_CheckerPortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("IBAN_CheckerPort".equals(inputPortName)) {
            return getIBAN_CheckerPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://mpre.zhaw.ch/", "IBAN_Checker_WSService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://mpre.zhaw.ch/", "IBAN_CheckerPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("IBAN_CheckerPort".equals(portName)) {
            setIBAN_CheckerPortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
