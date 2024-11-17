package com.java_e_wallet.e_wallet_ledger_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java_e_wallet.e_wallet_ledger_service.dto.ResponseDTO;
import com.java_e_wallet.e_wallet_ledger_service.dto.UserLedgersDTO;
import com.java_e_wallet.e_wallet_ledger_service.model.Ledger;
import com.java_e_wallet.e_wallet_ledger_service.service.LedgerService;

@RestController
@RequestMapping(value = "/ledgers")
public class LedgerController {

    private final LedgerService ledgerService;

    @Autowired
    public LedgerController(LedgerService ledgerService) {
        this.ledgerService = ledgerService;
    }

    @GetMapping(path = "/{user_id}")
    public ResponseDTO getUserLedgers(@PathVariable(name = "user_id") Long userId) {
        List<Ledger> ledgers = ledgerService.getUserLedgers(userId);

        return new ResponseDTO(HttpStatus.OK.value(), "ledger created", new UserLedgersDTO(ledgers));
    }
 }
