package com.java_e_wallet.e_wallet_ledger_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.java_e_wallet.e_wallet_ledger_service.model.Ledger;

public class LedgerDTO {

    @JsonProperty("ledger_id")
    private Long ledgerId;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("request_id")
    private String requestId;

    @JsonProperty("asset")
    private String asset;

    @JsonProperty("amount")
    private double amount;

    @JsonProperty("created_at")
    private Long createdAt;

    public LedgerDTO(Ledger ledger) {
        this.ledgerId = ledger.getLedgerId();
        this.userId = ledger.getUserId();
        this.requestId = ledger.getRequestId();
        this.asset = ledger.getAsset();
        this.amount = ledger.getAmount();
        this.createdAt = ledger.getCreatedAt();
    }
}
