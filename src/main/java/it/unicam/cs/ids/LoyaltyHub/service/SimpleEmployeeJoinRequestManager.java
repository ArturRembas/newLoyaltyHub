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

    /**
     * Retrieves all employee join requests.
     * 
     * @return An iterable collection of all {@link EmployeeJoinRequest} in the system.
     */
    @Override
    public Iterable<EmployeeJoinRequest> listAllRequests() {
        return employeeJoinRequestRepository.findAll();
    }

    /**
     * Retrieves an employee join request by its unique identifier.
     *
     * @param id The unique identifier of the employee join request.
     * @return The {@link EmployeeJoinRequest} with the specified ID.
     * @throws EntityNotFoundException if no request is found with the given ID.
     */
    @Override
    public EmployeeJoinRequest getRequestById(Long id) throws EntityNotFoundException {
        return employeeJoinRequestRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Employee join request with ID: " + id + " not found."));
    }

    /**
     * Validates an employee join request based on custom business rules.
     * 
     * @param id The ID of the employee join request to validate.
     * @return The validated {@link EmployeeJoinRequest}, potentially with updated status or information.
     * @throws EntityNotFoundException if the request with the specified ID does not exist.
     */
    @Override
    public EmployeeJoinRequest validateRequest(Long id) throws EntityNotFoundException {
        EmployeeJoinRequest request = getRequestById(id);
        return employeeJoinRequestRepository.save(request);
    }

}