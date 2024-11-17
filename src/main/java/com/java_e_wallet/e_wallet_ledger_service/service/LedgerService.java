package com.java_e_wallet.e_wallet_ledger_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java_e_wallet.e_wallet_ledger_service.model.Ledger;
import com.java_e_wallet.e_wallet_ledger_service.model.TransactionSummary;
import com.java_e_wallet.e_wallet_ledger_service.repository.LedgerRepository;

@Service
public class LedgerService {

    private final LedgerRepository ledgerRepository;

    @Autowired
    public LedgerService(LedgerRepository ledgerRepository) {
        this.ledgerRepository = ledgerRepository;
    }

    public void insertLedger(TransactionSummary transactionSummary) {
        Ledger senderLedger = new Ledger(transactionSummary.getSenderUserId(), transactionSummary.getRequestId(),
                transactionSummary.getAsset(), -1 * transactionSummary.getAmount(), transactionSummary.getCreatedAt());

        ledgerRepository.insertLedger(senderLedger);

        Ledger recipientLedger = new Ledger(transactionSummary.getRecipientUserId(), transactionSummary.getRequestId(),
        transactionSummary.getAsset(), transactionSummary.getAmount(), transactionSummary.getCreatedAt());

        ledgerRepository.insertLedger(recipientLedger);
    }

    public List<Ledger> getUserLedgers(Long userId) {
        return ledgerRepository.getUserLedgers(userId);
    }
}
