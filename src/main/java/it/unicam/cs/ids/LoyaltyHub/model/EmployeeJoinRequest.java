package it.unicam.cs.ids.LoyaltyHub.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.util.Objects;

/**
 * Represents a request for an employee to join the loyalty platform.
 * This entity tracks the request's validation status and relevant employee details.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class EmployeeJoinRequest implements JoinRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employeeGenerator")
    @SequenceGenerator(name = "employeeGenerator", allocationSize = 1)
    private Long id;

    private boolean validated = false;

    @DateTimeFormat(pattern = "yyyy--MM--dd")
    private Date date;

    private String employeeEmail;
    private String employeeName;
    private String employeeSurname;
    private String address;
    private String phone;

    /**
     * Constructs a new employee join request with the given details.
     *
     * @param employeeName    The name of the employee.
     * @param employeeSurname The surname of the employee.
     * @param address         The address of the employee.
     * @param employeeEmail   The email of the employee.
     * @param phone           The phone number of the employee.
     */
    public EmployeeJoinRequest(String employeeName, String employeeSurname, String address, String employeeEmail, String phone) {
        this.employeeEmail = employeeEmail;
        this.employeeName = employeeName;
        this.employeeSurname = employeeSurname;
        this.address = address;
        this.phone = phone;
        this.date = new Date(System.currentTimeMillis());
    }

    /**
     * Marks the join request as validated.
     */
    public void validate() {
        this.validated = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeJoinRequest that = (EmployeeJoinRequest) o;
        return Objects.equals(date, that.date) && 
               Objects.equals(employeeEmail, that.employeeEmail) && 
               Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, employeeEmail, phone);
    }

    /**
     * Retrieves the email address of the employee associated with this join request.
     *
     * @return The email address of the employee.
     */
    public String getEmployeeEmail() {
        return this.employeeEmail;
    }

    /**
     * Retrieves the phone number of the employee associated with this join request.
     *
     * @return The phone number of the employee.
     */
    public String getPhone() {
        return this.phone;
    }

	public String getEmployeeName() {
		return this.employeeName;
	}

	public String getEmployeeSurname() {
		return this.employeeSurname;
	}

	public String getAddress() {
		return this.address;
	}
}
