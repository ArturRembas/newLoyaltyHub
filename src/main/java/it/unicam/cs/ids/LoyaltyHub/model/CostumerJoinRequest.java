package it.unicam.cs.ids.LoyaltyHub.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.util.Objects;

/**
 * Represents a join request made by a customer to participate in the loyalty platform.
 * Contains customer information and the request status.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class CostumerJoinRequest implements JoinRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "costumerGenerator")
    @SequenceGenerator(name = "costumerGenerator", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    private boolean validated = false;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private String costumerEmail;
    private String costumerName;
    private String costumerSurname;
    private String address;
    private String phone;

    /**
     * Constructs a new CostumerJoinRequest with the specified details.
     *
     * @param costumerName    The name of the customer.
     * @param costumerSurname The surname of the customer.
     * @param address         The address of the customer.
     * @param costumerEmail   The email of the customer.
     * @param phone           The phone number of the customer.
     */
    public CostumerJoinRequest(String costumerName, String costumerSurname, String address, String costumerEmail, String phone) {
        this.costumerEmail = costumerEmail;
        this.costumerName = costumerName;
        this.costumerSurname = costumerSurname;
        this.address = address;
        this.phone = phone;
        this.date = new Date(System.currentTimeMillis()); // Sets the current date
    }

    /**
     * Marks this join request as validated.
     */
    public void validate() {
        this.validated = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CostumerJoinRequest that = (CostumerJoinRequest) o;
        return Objects.equals(date, that.date) && Objects.equals(costumerEmail, that.costumerEmail) && Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, costumerEmail, phone);
    }

    /**
     * Retrieves the email address of the customer associated with this join request.
     *
     * @return The email address of the customer.
     */
    public String getCostumerEmail() {
        return this.costumerEmail;
    }

    /**
     * Retrieves the phone number of the customer associated with this join request.
     *
     * @return The phone number of the customer.
     */
    public String getPhone() {
        return this.phone;
    }

	public String getCostumerName() {
		return this.costumerName;
	}

	public String getCostumerSurname() {
		// TODO Auto-generated method stub
		return this.costumerSurname;
	}

	public String getAddress() {
		return this.address;
	}
}