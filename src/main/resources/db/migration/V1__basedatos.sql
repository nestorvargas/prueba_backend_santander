
CREATE TABLE product(
    product_id VARCHAR(6) PRIMARY,
    name VARCHAR(100) NOT NULL,
    type VARCHAR(10) NOT NULL CHECK (type IN ('DEBIT', 'CREDIT'))
);

CREATE TABLE card(
    card_id INT PRIMARY KEY,
    card_number VARCHAR(16) NOT NULL CHECK(card_numer ~ '^[0-9]' ),
    product_id VARCHAR(16) NOT NULL,
    holder_name VARCHAR(100) NOT NULL,
    issued_at   DATE NOT NULL,
    expires_at DATE NOT NULL,
    status VARCHAR(10) NOT NULL,
    blocked_at TIMESTAMP WITH TIME ZONE,
    blocked_reason VARCHAR(100) NOT NULL,
    balance NUMERIC(19, 2) NOT NULL DEFAULT 0.00;
    currency VARCHAR(3) NOT NULL DEFAULT(USD) CHECK (currency = 'USD'),
    -- evitar peticiones simultaneas con una version
    version BIGINT NOT NULL DEFAULT 0,
    CONSTRAIN uk_card_number UNIQUE (card_number)
);

CREATE INDEX idx_card_product_id ON card(card_id);