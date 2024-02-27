package it.unicam.cs.ids.LoyaltyHub.controller;

import it.unicam.cs.ids.LoyaltyHub.exception.EntityNotFoundException;
import it.unicam.cs.ids.LoyaltyHub.exception.IdConflictException;
import it.unicam.cs.ids.LoyaltyHub.model.Transaction;
import it.unicam.cs.ids.LoyaltyHub.model.rules.Rule;
import it.unicam.cs.ids.LoyaltyHub.repository.*;
import it.unicam.cs.ids.LoyaltyHub.service.FidelityCardManager;
import it.unicam.cs.ids.LoyaltyHub.service.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/Transaction")
public class SimpleTransactionController implements TransactionController {
	@Autowired
	private TransactionManager transactionManager;
	@Autowired
	private FidelityCardRepository fidelityCardRepository;
	@Autowired
	private FidelityCardManager cardManager;
	@Autowired
	private LoyaltyProgramRepository loyaltyProgramRepository;
	@Autowired
	private RuleRepository ruleRepository;
	@Autowired
	private TransactionRepository transactionRepository;
	@Autowired
	private ActivityRepository activityRepository;

	@GetMapping("/{id}")
	@Override
	public Transaction getInstance(@PathVariable Long id) throws EntityNotFoundException {
		return transactionManager.getInstance(id);
	}

	@PostMapping("/createNew")
	@Override
	public Transaction create(@RequestBody Transaction object) throws EntityNotFoundException, IdConflictException {
		this.checkCreationTransaction(object);
		return transactionManager.create(object);
	}

	@Override
	public Transaction update(Transaction object) throws EntityNotFoundException, IdConflictException {
		return null;
	}

	@DeleteMapping("/delete/{id}")
	@Override
	public boolean delete(@PathVariable Long id) throws IdConflictException, EntityNotFoundException {
		return transactionManager.delete(id);
	}

	@GetMapping("/exists/{id}")
	@Override
	public boolean exists(@PathVariable Long id) {
		return transactionManager.exists(id);
	}

	// A method that is called when a transaction is validated. It takes the card
	// and transaction id as parameters and
	// returns the total points of the card.
	@Override
	@PostMapping("/validateTransaction/{cardId}/{transactionId}")
	public int validateTransaction(@PathVariable Long cardId, @PathVariable Long transactionId)
			throws EntityNotFoundException, IdConflictException {
		Transaction transaction = transactionManager.getInstance(transactionId);
		checkTransactionStatus(transaction);
		applyRules(transaction);
		transaction.validate();
		return updateStatusCard(transactionId);
	}

	private int updateStatusCard(Long transactionId) throws EntityNotFoundException, IdConflictException {
		transactionManager.updateStatus(transactionId);
		return cardManager.updatePoints(transactionManager.getInstance(transactionId).getFidelityCard(),
				transactionManager.getInstance(transactionId).getFidelityCard().getTotalPoints());
	}

	/**
	 * This method takes all the rules with the same loyalty program id and saves
	 * them in a list Then it applies them to the transaction
	 * 
	 * @param transaction to validate
	 */
	private void applyRules(Transaction transaction) {
		List<Rule> currentRules = ruleRepository.findByLoyaltyPrograms_LoyaltyProgramId(
				transaction.getActivity().getLoyaltyProgram().getLoyaltyProgramId());
		int currentPoint = transaction.getFidelityCard().getTotalPoints();
		currentRules.forEach(s -> s.applyRule(transaction));
	}

	private void checkTransactionStatus(Transaction transaction) throws EntityNotFoundException {
		if (transaction.isValidated())
			throw new EntityNotFoundException("La transazione è già stata validata");
	}

	private void checkCreationTransaction(Transaction transaction) throws EntityNotFoundException, IdConflictException {
		if (!fidelityCardRepository.existsById(transaction.getFidelityCardId()))
			throw new EntityNotFoundException("Tessera non valida!");
		if (!activityRepository.existsById(transaction.getActivityId()))
			throw new EntityNotFoundException("Attività non valida!");
	}

}
