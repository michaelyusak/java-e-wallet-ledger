package com.java_e_wallet.e_wallet_ledger_service.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.java_e_wallet.e_wallet_ledger_service.model.Ledger;

public class UserLedgersDTO {

    @JsonProperty("ledgers")
    private List<LedgerDTO> ledgers;

    public UserLedgersDTO(List<Ledger> ledgers) {
        this.ledgers = ledgers.stream()
                .map(LedgerDTO::new)
                .collect(Collectors.toList());
    }
}
