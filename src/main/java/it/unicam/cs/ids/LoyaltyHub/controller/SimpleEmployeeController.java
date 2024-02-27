package it.unicam.cs.ids.LoyaltyHub.controller;

import it.unicam.cs.ids.LoyaltyHub.exception.EntityNotFoundException;
import it.unicam.cs.ids.LoyaltyHub.exception.IdConflictException;
import it.unicam.cs.ids.LoyaltyHub.model.Employee;
import it.unicam.cs.ids.LoyaltyHub.service.EmployeeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for managing employees in the Loyalty Platform.
 * Provides endpoints for CRUD operations on employees.
 */
@RestController
@RequestMapping("/api/Employee")
public class SimpleEmployeeController implements EmployeeController {

    private final EmployeeManager employeeManager;

    @Autowired
    public SimpleEmployeeController(EmployeeManager employeeManager) {
        this.employeeManager = employeeManager;
    }

    /**
     * Retrieves an employee by ID.
     *
     * @param id The ID of the employee to retrieve.
     * @return ResponseEntity containing the employee and HTTP status OK.
     * @throws EntityNotFoundException if the employee is not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getInstance(@PathVariable Long id) throws EntityNotFoundException {
        Employee employee = employeeManager.getInstance(id);
        return ResponseEntity.ok(employee);
    }

    /**
     * Creates a new employee.
     *
     * @param object The employee to create.
     * @return ResponseEntity containing the created employee and HTTP status CREATED.
     * @throws IdConflictException if there is an ID conflict.
     */
    @PostMapping("/createNew")
    public ResponseEntity<Employee> create(@RequestBody Employee object) throws IdConflictException {
        Employee createdEmployee = employeeManager.create(object);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
    }

    @Override
    public Employee update(Employee object) throws EntityNotFoundException, IdConflictException {
        return null;
    }
    
    /**
     * Deletes an employee by ID.
     *
     * @param id The ID of the employee to delete.
     * @return ResponseEntity with HTTP status OK if deletion is successful.
     * @throws IdConflictException if there is an ID conflict during deletion.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws IdConflictException {
        employeeManager.delete(id);
        return ResponseEntity.ok().build();
    }

    /**
     * Checks if an employee exists by ID.
     *
     * @param id The ID of the employee to check.
     * @return ResponseEntity containing a boolean indicating existence and HTTP status OK.
     */
    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> exists(@PathVariable Long id) {
        boolean exists = employeeManager.exists(id);
        return ResponseEntity.ok(exists);
    }
}
