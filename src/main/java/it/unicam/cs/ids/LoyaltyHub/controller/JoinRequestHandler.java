package it.unicam.cs.ids.LoyaltyHub.controller;

import it.unicam.cs.ids.LoyaltyHub.exception.EntityNotFoundException;
import it.unicam.cs.ids.LoyaltyHub.exception.IdConflictException;
import it.unicam.cs.ids.LoyaltyHub.model.*;
import it.unicam.cs.ids.LoyaltyHub.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Handles join requests for activities, customers, and employees within the Loyalty Platform.
 * Provides endpoints for listing, checking, and validating join requests for various entities.
 */
@RestController
@RequestMapping("/api/join-requests")
public class JoinRequestHandler {

    @Autowired
    private ActivityJoinRequestManager activityRequestManager;
    @Autowired
    private CostumerJoinRequestManager costumerRequestManager;
    @Autowired
    private EmployeeJoinRequestManager employeeRequestManager;

    @Autowired
    private ActivityController activityController;
    @Autowired
    private CostumerController costumerController;
    @Autowired
    private EmployeeController employeeController;
    @Autowired
    private FidelityCardController fidelityCardController;

    /**
     * Lists all activity join requests.
     * @return Iterable collection of {@link ActivityJoinRequest}
     */
    @GetMapping("/activities")
    public Iterable<ActivityJoinRequest> listActivityRequests() {
        return activityRequestManager.listAllRequests();
    }

    /**
     * Lists all customer join requests.
     * @return Iterable collection of {@link CostumerJoinRequest}
     */
    @GetMapping("/customers")
    public Iterable<CostumerJoinRequest> listCustomerRequests() {
        return costumerRequestManager.listAllRequests();
    }

    /**
     * Lists all employee join requests.
     * @return Iterable collection of {@link EmployeeJoinRequest}
     */
    @GetMapping("/employees")
    public Iterable<EmployeeJoinRequest> listEmployeeRequests() {
        return employeeRequestManager.listAllRequests();
    }

    /**
     * Retrieves a specific activity join request by its ID.
     * @param id ID of the activity join request
     * @return {@link ActivityJoinRequest}
     * @throws EntityNotFoundException if the request with the given ID is not found
     */
    @GetMapping("/activity/{id}")
    public ActivityJoinRequest getActivityRequest(@PathVariable Long id) throws EntityNotFoundException {
        return activityRequestManager.getRequestById(id);
    }

    /**
     * Retrieves a specific customer join request by its ID.
     * @param id ID of the customer join request
     * @return {@link CostumerJoinRequest}
     * @throws EntityNotFoundException if the request with the given ID is not found
     */
    @GetMapping("/customer/{id}")
    public CostumerJoinRequest getCustomerRequest(@PathVariable Long id) throws EntityNotFoundException {
        return costumerRequestManager.getRequestById(id);
    }

    /**
     * Retrieves a specific employee join request by its ID.
     * @param id ID of the employee join request
     * @return {@link EmployeeJoinRequest}
     * @throws EntityNotFoundException if the request with the given ID is not found
     */
    @GetMapping("/employee/{id}")
    public EmployeeJoinRequest getEmployeeRequest(@PathVariable Long id) throws EntityNotFoundException {
        return employeeRequestManager.getRequestById(id);
    }

    /**
     * Validates and processes an activity join request, creating a new activity entity.
     * @param id ID of the activity join request
     * @return {@link Activity} created from the join request
     * @throws EntityNotFoundException if the request with the given ID is not found
     * @throws IdConflictException 
     */
    @PostMapping("/activity/validate/{id}")
    public Activity validateActivityRequest(@PathVariable Long id) throws EntityNotFoundException, IdConflictException {
        ActivityJoinRequest request = activityRequestManager.validateRequest(id);
        return activityController.create(new Activity(request.getActivityName(), request.getActivityEmail(), request.getVatCode(), request.getAddress(), request.getPhone()));
    }

    /**
     * Validates and processes a customer join request, creating a new customer entity and associating a fidelity card.
     * @param id ID of the customer join request
     * @return {@link Costumer} created from the join request
     * @throws EntityNotFoundException if the request with the given ID is not found
     * @throws IdConflictException 
     */
    @PostMapping("/customer/validate/{id}")
    public Costumer validateCustomerRequest(@PathVariable Long id) throws EntityNotFoundException, IdConflictException {
        CostumerJoinRequest request = costumerRequestManager.validateRequest(id);
        Costumer costumer = new Costumer(request.getCostumerName(), request.getCostumerSurname(), request.getAddress(), request.getCostumerEmail(), request.getPhone());
        costumer = costumerController.create(costumer);
        fidelityCardController.createWithLoyaltyProgram(costumer.getEmail(), null); // Assuming createWithLoyaltyProgram can handle null loyaltyProgramName
        return costumer;
    }

    /**
     * Validates and processes an employee join request, creating a new employee entity.
     * @param id ID of the employee join request
     * @return {@link Employee} created from the join request
     * @throws EntityNotFoundException if the request with the given ID is not found
     * @throws IdConflictException 
     */
    @PostMapping("/employee/validate/{id}")
    public Employee validateEmployeeRequest(@PathVariable Long id) throws EntityNotFoundException, IdConflictException {
        EmployeeJoinRequest request = employeeRequestManager.validateRequest(id);
        return employeeController.create(new Employee(request.getEmployeeEmail(), request.getEmployeeName(), request.getEmployeeSurname(), request.getAddress(), request.getPhone()));
    }
}