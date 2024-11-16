package com.java_e_wallet.e_wallet_ledger_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.java_e_wallet.e_wallet_ledger_service.config.Config;

@SpringBootApplication
@EnableTransactionManagement
public class EWalletLedgerServiceApplication {

	public static void main(String[] args) {
		Config.Init();

		SpringApplication.run(EWalletLedgerServiceApplication.class, args);
	}

}
