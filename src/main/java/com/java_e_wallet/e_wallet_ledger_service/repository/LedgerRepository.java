package com.java_e_wallet.e_wallet_ledger_service.repository;

import java.time.Instant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.java_e_wallet.e_wallet_ledger_service.model.Ledger;

import jakarta.transaction.Transactional;

@Repository
public interface LedgerRepository extends JpaRepository<Ledger, Long> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO ledgers (user_id, request_id, asset, amount, created_at, updated_at) VALUES (?1, ?2, ?3, ?4, ?5, ?6)", nativeQuery = true)
    void insertLedger(Long userId, String requestId, String asset, double amount, Long createdAt, Long updatedAt);

    default void insertLedger(Ledger ledger) {
        insertLedger(ledger.getUserId(), ledger.getRequestId(), ledger.getAsset(), ledger.getAmount(),
                ledger.getCreatedAt(), Instant.now().toEpochMilli());
    }
}
