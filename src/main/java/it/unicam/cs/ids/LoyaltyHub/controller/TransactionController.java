package it.unicam.cs.ids.LoyaltyHub.controller;

import it.unicam.cs.ids.LoyaltyHub.exception.EntityNotFoundException;
import it.unicam.cs.ids.LoyaltyHub.exception.IdConflictException;
import it.unicam.cs.ids.LoyaltyHub.model.Transaction;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public interface TransactionController extends EntityController<Transaction, Long> {
	// A method that is called when a transaction is validated. It takes the card
	// and transaction id as parameters and
	// returns the total points of the card.
	@PostMapping("/validateTransaction/{cardId}/{transactionId}")
	int validateTransaction(@PathVariable Long cardId, @PathVariable Long transactionId)
			throws EntityNotFoundException, IdConflictException;
}