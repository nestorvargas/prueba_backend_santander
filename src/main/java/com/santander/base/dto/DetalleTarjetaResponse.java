package com.santander.base.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import com.santander.base.model.Card;
import com.santander.base.model.CardStatus;

public record DetalleTarjetaResponse (
        UUID CardId,
        String cardNumber,
        String holderName,
        CardStatus status,
        String BlockedAt,
        String blockeReason,
        LocalDate expiresAt,
        BigDecimal balance,
        String currency){

    public static DetalleTarjetaResponse desde(Card card) {

        // el contrato
        return new DetalleTarjetaResponse(
                card.getCardId(),
                card.getCardNumber(),
                card.getHolderName(),
                card.getStatus(),
                Objects.toString(card.getBlockedAt(),""),
                Objects.toString(card.getBlockedReason(),""),
                card.getEspiresAt(),
                card.getBalance(),
                card.getCurrency());
    }

}
