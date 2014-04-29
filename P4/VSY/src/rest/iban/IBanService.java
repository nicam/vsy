package rest.iban;

import ch.zhaw.mpre.IBAN_CheckerProxy;

public class IBanService {
	private static IBAN_CheckerProxy service = new IBAN_CheckerProxy();
	
	private IBanService(){}
	
	public static IBAN_CheckerProxy getInstance() {
		return IBanService.service;
	}
}
