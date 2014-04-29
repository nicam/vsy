package ch.zhaw.mpre;

public class IBAN_CheckerProxy implements ch.zhaw.mpre.IBAN_Checker {
  private String _endpoint = null;
  private ch.zhaw.mpre.IBAN_Checker iBAN_Checker = null;
  
  public IBAN_CheckerProxy() {
    _initIBAN_CheckerProxy();
  }
  
  public IBAN_CheckerProxy(String endpoint) {
    _endpoint = endpoint;
    _initIBAN_CheckerProxy();
  }
  
  private void _initIBAN_CheckerProxy() {
    try {
      iBAN_Checker = (new ch.zhaw.mpre.IBAN_Checker_WSServiceLocator()).getIBAN_CheckerPort();
      if (iBAN_Checker != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)iBAN_Checker)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)iBAN_Checker)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (iBAN_Checker != null)
      ((javax.xml.rpc.Stub)iBAN_Checker)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public ch.zhaw.mpre.IBAN_Checker getIBAN_Checker() {
    if (iBAN_Checker == null)
      _initIBAN_CheckerProxy();
    return iBAN_Checker;
  }
  
  public java.lang.String IBAN_Checker(java.lang.String arg0) throws java.rmi.RemoteException{
    if (iBAN_Checker == null)
      _initIBAN_CheckerProxy();
    return iBAN_Checker.IBAN_Checker(arg0);
  }
  
  
}