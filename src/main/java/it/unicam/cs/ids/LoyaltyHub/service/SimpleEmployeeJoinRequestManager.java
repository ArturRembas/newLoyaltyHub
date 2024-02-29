package it.unicam.cs.ids.LoyaltyHub.service;

import it.unicam.cs.ids.LoyaltyHub.exception.EntityNotFoundException;
import it.unicam.cs.ids.LoyaltyHub.exception.IdConflictException;
import it.unicam.cs.ids.LoyaltyHub.model.EmployeeJoinRequest;
import it.unicam.cs.ids.LoyaltyHub.repository.EmployeeJoinRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

@Validated
@Service
public class SimpleEmployeeJoinRequestManager implements EmployeeJoinRequestManager{

    @Autowired
    private EmployeeJoinRequestRepository employeeJoinRequestRepository;
    @Override
    public EmployeeJoinRequest getInstance(Long id) throws EntityNotFoundException {
        return employeeJoinRequestRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Richiesta adesione dipendente "+id+"non trovata!"));
    }

    @Override
    public EmployeeJoinRequest create(EmployeeJoinRequest object) throws EntityNotFoundException, IdConflictException {
        checkIfValuesNotNull(object);
        checkCreation(object);
        return employeeJoinRequestRepository.save(object);
    }

    @Override
    public EmployeeJoinRequest update(EmployeeJoinRequest object) throws EntityNotFoundException, IdConflictException {
        return null;
    }

    @Override
    public boolean delete(Long id) throws EntityNotFoundException, IdConflictException {
        if(!this.exists(id)) throw new EntityNotFoundException("Richiesta non presente");
        employeeJoinRequestRepository.deleteById(id);
        return !this.exists(id);
    }

    @Override
    public boolean exists(Long id) {
        return employeeJoinRequestRepository.existsById(id);
    }

    private void checkCreation(EmployeeJoinRequest employeeJoinRequest) throws EntityNotFoundException, IdConflictException {
        if(employeeJoinRequestRepository.existsByEmployeeEmail(employeeJoinRequest.getEmployeeEmail())) throw new IdConflictException("Richiesta già presente");
        if(employeeJoinRequestRepository.existsByPhone(employeeJoinRequest.getPhone())) throw new IdConflictException("Richiesta già presente");
    }

    private void checkIfValuesNotNull(EmployeeJoinRequest employeeJoinRequest) throws NullPointerException {
        Objects.requireNonNull(employeeJoinRequest.getEmployeeEmail(), "Email non inserita");
        Objects.requireNonNull(employeeJoinRequest.getPhone(), "Telefono non inserito");
    }
}