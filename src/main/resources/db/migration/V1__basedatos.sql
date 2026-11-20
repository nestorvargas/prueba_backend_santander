CREATE TABLE product(
    product_id VARCHAR(6) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    type VARCHAR(10) NOT NULL CHECK (type IN ('DEBIT', 'CREDIT'))
);

CREATE TABLE card(
    card_id UUID PRIMARY KEY,
    card_number VARCHAR(16) NOT NULL CHECK (card_number ~ '^[0-9]{16}$'),
    product_id VARCHAR(6) NOT NULL REFERENCES product(product_id),
    holder_name VARCHAR(100) NOT NULL,
    issued_at   DATE NOT NULL,
    expires_at DATE NOT NULL,
    status VARCHAR(10) NOT NULL,
    blocked_at TIMESTAMP WITH TIME ZONE,
    blocked_reason VARCHAR(100),
    balance NUMERIC(19, 2) NOT NULL DEFAULT 0.00,
    currency VARCHAR(3) NOT NULL DEFAULT 'USD' CHECK (currency = 'USD'),
    -- evitar peticiones simultaneas con una version
    version BIGINT NOT NULL DEFAULT 0,
    CONSTRAINT uk_card_number UNIQUE (card_number)
);

CREATE INDEX idx_card_product_id ON card(product_id);
