package com.sahaj.bankproject.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sahaj.bankproject.dao.BankDAO;
import com.sahaj.bankproject.model.Bank;

public class BankService {
	private static Logger log = LogManager.getLogger(BankService.class);
	private static BankDAO bd = new BankDAO();
	
	public Bank getBank(String IFSC) {
		//commenting as was asked to remove DB
		//Bank bank = bd.readBank(IFSC);
		Bank bank = new Bank(IFSC, "Sahaj Bank" , "Pune", "Maharashtra", "India", "022-123123",
				100000, 0, 50000, 500, 25000,
    			1000, 3, 3);
		if(bank != null) {
			log.trace("Bank fetched successfully.");
		}else {
			log.warn("Bank not fetched");
		}
		
		return bank;
	}


}