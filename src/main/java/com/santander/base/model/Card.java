package com.santander.base.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table (name="card")
public class Card {

        private static final String MONEDA = "USD";
        private static final int ANIOS_VIGENCIA = 3;

        @Id
        private UUID CardId;

        @Column(nullable = false, Unique = true, lenght = 16)
        private String cardNumber;

        @ManyToOne(fetch = FechType.LAZY, optional = false)
        @JoinColumn(name = "product_id", nullable = false)
        private Product product;

        @Column(nullable = false, length = 100)
        private String holderName;

        @Column(nullable = false)
        private LocalDate issuedAt;

        @Column(nullable = false)
        private LocalDate espiresAt;

        @Enumerated(EnumType.STRING)
        @Column(nullable = false, length = 10)
        private CardStatus status;

        private OffsetDateTime blockedAt;

        @Column(length = 100)
        private String blockedReason;

        @Column(nullable = false, precision = 19, scale = 2)
        private BigDecimal balance;

        @Column (nullable = false, length = 3)
        private String currency;


        public static Card emitir(Product product, String holderName, String cardNumber, LocalDate fechaEmision){

            Card card = new Card();
            card.cardId = UUID.randomUUID();
            card.product = product;
            card.holderName = holderName;
            card.cardNumber = cardNumber;
            card.issuedAt = fechaEmision;
            card.expiresAt = fechaEmision.plusYears(ANIOS_VIGENCIA);
            card.status = CardStatus.ISSUED;
            card.balance = BigDecimal.ZERO.setScale(2, RoundingMode.UNNECESSARY );
            card.currency = MONEDA;

            return card;

        }

        protected  Card(){

        }

        public void activar(){
            if(status == CardStatus.BLOCKED){
                throw new TransaccionInvalidaEXception("La tarjeta" + CardId +" esta bloqueda y no se puede activar");
            }
            status = CardStatus.ACTIVE;
        }

        public void bloquear(String motivo, OffsetDateTime fecha){

            if (status == CardStatus.BLOCKED){
                return;
            }

            status = CardStatus.BLOCKED;
            blockedAt = fecha;
            blockedReason = motivo;

        }


        public UUID getCardId(){
         return CardId;
        }

        public String getCardNumber() {
            return cardNumber;
        }

        public Product getProduct() {
            return product;
        }

        public String getHolderName() {
            return holderName;
        }

        public LocalDate getIssuedAt() {
            return issuedAt;
        }

        public LocalDate getEspiresAt() {
            return espiresAt;
        }

        public CardStatus getStatus() {
            return status;
        }

        public OffsetDateTime getBlockedAt() {
            return blockedAt;
        }

        public String getBlockedReason() {
            return blockedReason;
        }

        public BigDecimal getBalance() {
            return balance;
        }

        public String getCurrency() {
            return currency;
        }

}
