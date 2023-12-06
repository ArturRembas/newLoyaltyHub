package it.unicam.cs.ids.LoyaltyHub.service;

import it.unicam.cs.ids.LoyaltyHub.exception.EntityNotFoundException;
import it.unicam.cs.ids.LoyaltyHub.exception.IdConflictException;
import it.unicam.cs.ids.LoyaltyHub.model.Costumer;
import it.unicam.cs.ids.LoyaltyHub.repository.CostumerRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
public class SimpleCostumerManager implements CostumerManager{
    @Autowired
    private CostumerRepository costumerRepository;
    @Override
    public Costumer getInstance(@Valid @NotNull @NotBlank Long id) throws EntityNotFoundException {
        return costumerRepository.findById(id).orElseThrow(()
                -> new EntityNotFoundException("Nessun cliente con l'Id inserito"+id));
    }

    @Override
    public Costumer create(Costumer object) throws EntityNotFoundException, IdConflictException{
        checkCostumer(object);
        return costumerRepository.save(object);
    }

    @Override
    public Costumer update(Costumer object) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public boolean exists(Long id) {
        return false;
    }

    private void checkCostumer(Costumer costumer) throws EntityNotFoundException, IdConflictException {
        if(costumerRepository.existsByEmail(costumer.getEmail())) {
            throw new IdConflictException("Non è possibile creare un utente senza email " +costumer.getEmail());
        }
        if(costumerRepository.existsByPhone(costumer.getPhone())) {
            throw new EntityNotFoundException("Nessun cliente trovato con il telefono "+costumer.getPhone());
        }
    }
}