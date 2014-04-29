/**
 * IBAN_Checker_WSService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ch.zhaw.mpre;

public interface IBAN_Checker_WSService extends javax.xml.rpc.Service {
    public java.lang.String getIBAN_CheckerPortAddress();

    public ch.zhaw.mpre.IBAN_Checker getIBAN_CheckerPort() throws javax.xml.rpc.ServiceException;

    public ch.zhaw.mpre.IBAN_Checker getIBAN_CheckerPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
