package it.unicam.cs.ids.LoyaltyHub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.Objects;

/**
 * Represents a customer in the LoyaltyHub system.
 * This entity manages the personal details and wallet information of a customer.
 */
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "costumer")
public class Costumer implements User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "costumer_seq")
    @SequenceGenerator(name = "costumer_seq", sequenceName = "costumer_seq", allocationSize = 1)
    @Column(name = "costumer_id", nullable = false)
    private Long costumerId;

    private String name;
    private String surname;

    @Column(nullable = false, unique = true)
    @NotEmpty(message = "Inserire email")
    @Pattern(regexp = "[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\\\.\"\n"
    		+ "                + \"[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+)*@\"\n"
    		+ "                + \"(?:[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?\\\\.)+[A-Za-z0-9]\"\n"
    		+ "                + \"(?:[A-Za-z0-9-]*[A-Za-z0-9])?", message = "{invalid.email}")
    private String email;

    @Column(nullable = false, unique = true)
    @NotEmpty
    private String phone;

    private final UserType userType = UserType.COSTUMER;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "costumer_wallet_wallet_id")
    private CostumerWallet costumerWallet;

    /**
     * Creates a new Costumer with specified name, surname, email, and phone number.
     *
     * @param name    The first name of the customer.
     * @param surname The surname of the customer.
     * @param email   The email address of the customer.
     * @param phone   The phone number of the customer.
     */
    public Costumer(String name, String surname, String email, String phone) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
    }

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @return the costumerWallet
	 */
	public CostumerWallet getCostumerWallet() {
		return costumerWallet;
	}

	/**
	 * @param costumerWallet the costumerWallet to set
	 */
	public void setCostumerWallet(CostumerWallet costumerWallet) {
		this.costumerWallet = costumerWallet;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Costumer costumer = (Costumer) o;
        return costumerId != null && Objects.equals(costumerId, costumer.costumerId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}