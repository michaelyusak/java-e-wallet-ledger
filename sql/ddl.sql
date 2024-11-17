CREATE DATABASE java_e_wallet_ledger_db;

\c java_e_wallet_ledger_db

DROP TABLE IF EXISTS ledgers;

CREATE TABLE ledgers (
    ledger_id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    request_id VARCHAR NOT NULL,
    asset VARCHAR NOT NULL,
    amount VARCHAR NOT NULL,
    created_at BIGINT NOT NULL,
    updated_at BIGINT NOT NULL,
    deleted_at BIGINT NOT NULL DEFAULT 0
);