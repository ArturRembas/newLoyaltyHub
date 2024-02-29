package it.unicam.cs.ids.LoyaltyHub.service;

import it.unicam.cs.ids.LoyaltyHub.exception.EntityNotFoundException;
import it.unicam.cs.ids.LoyaltyHub.exception.IdConflictException;
import it.unicam.cs.ids.LoyaltyHub.model.Employee;
import it.unicam.cs.ids.LoyaltyHub.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

/**
 * Service class for managing employee entities within the loyalty platform.
 * It handles creating, retrieving, and deleting employees, as well as basic validations.
 */
@Validated
@Service
public class SimpleEmployeeManager implements EmployeeManager {

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Retrieves an employee by its ID.
     *
     * @param id The ID of the employee.
     * @return The {@link Employee} object.
     * @throws EntityNotFoundException if the employee is not found.
     */
    @Override
    public Employee getInstance(Long id) throws EntityNotFoundException {
        return employeeRepository.findById(id).orElseThrow(() ->
                 new EntityNotFoundException("Employee with ID: " + id + " not found."));
    }

    /**
     * Creates a new employee after validating the provided information.
     *
     * @param object The {@link Employee} object to create.
     * @return The created {@link Employee} object.
     * @throws EntityNotFoundException if required information is missing.
     * @throws IdConflictException if the employee already exists.
     */
    @Override
    public Employee create(Employee object) throws EntityNotFoundException, IdConflictException {
        checkIfValuesNotNull(object);
        checkCreation(object);
        return employeeRepository.save(object);
    }

    /**
     * Updates an existing employee. Currently, this method is not implemented.
     *
     * @param object The {@link Employee} object to update.
     * @return null as this operation is not supported.
     * @throws UnsupportedOperationException when invoked.
     */
    @Override
    public Employee update(Employee object) {
        throw new UnsupportedOperationException("Employee update not supported.");
    }

    /**
     * Deletes an employee by its ID.
     *
     * @param id The ID of the employee to delete.
     * @return true if the deletion was successful, false otherwise.
     * @throws EntityNotFoundException if the employee does not exist.
     */
    @Override
    public boolean delete(Long id) throws EntityNotFoundException {
        if (!employeeRepository.existsById(id)) {
            throw new EntityNotFoundException("Employee with ID: " + id + " not found.");
        }
        employeeRepository.deleteById(id);
        return !employeeRepository.existsById(id);
    }

    /**
     * Checks if an employee exists by its ID.
     *
     * @param id The ID of the employee.
     * @return true if the employee exists, false otherwise.
     */
    @Override
    public boolean exists(Long id) {
        return employeeRepository.existsById(id);
    }

    /**
     * Validates the creation of an employee, ensuring no duplicate entries.
     *
     * @param employee The {@link Employee} to validate.
     * @throws IdConflictException if the employee already exists.
     */
    private void checkCreation(Employee employee) throws IdConflictException {
        if (employeeRepository.existsByEmail(employee.getEmail())) {
            throw new IdConflictException("Employee already exists with email: " + employee.getEmail());
        }
    }

    /**
     * Ensures all required employee information is provided.
     *
     * @param employee The {@link Employee} to validate.
     * @throws NullPointerException if essential information is missing.
     */
    private void checkIfValuesNotNull(Employee employee) {
        Objects.requireNonNull(employee.getEmail(), "Email is required.");
        Objects.requireNonNull(employee.getPhone(), "Phone number is required.");
    }
}
