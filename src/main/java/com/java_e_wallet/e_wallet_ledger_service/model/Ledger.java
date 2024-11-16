package com.java_e_wallet.e_wallet_ledger_service.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "ledgers")
public class Ledger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ledger_id")
    private Long ledgerId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "request_id")
    private String requestId;

    @Column(name = "asset")
    private String asset;

    @Column(name = "amount")
    private double amount;

    @Column(name = "created_at")
    private Long createdAt;

    @Column(name = "updated_at")
    private Long updatedAt;

    @Column(name = "deleted_at")
    private Long deletedAt;

    public Ledger() {
    }

    public Ledger(
            Long userid,
            String requestId,
            String asset,
            double amount,
            Long createdAt) {
        this.userId = userid;
        this.requestId = requestId;
        this.asset = asset;
        this.amount = amount;
        this.createdAt = createdAt;
    }
}
