package it.unicam.cs.ids.LoyaltyHub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

/**
 * Represents a business activity in the LoyaltyHub system.
 * This entity manages the details of a business activity participating in the loyalty program.
 */
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "activity")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activity_seq")
    @SequenceGenerator(
            name = "activity_seq",
            sequenceName = "activity_seq",
            allocationSize = 1)
    @Column(name = "activity_id", nullable = false)
    private Long activityId;

    private String name;
    private String address;

    @Column(nullable = false, unique = true)
    @NotNull
    @NotEmpty
    private String vatCode;

    @Column(nullable = false, unique = true)
    @NotEmpty(message = "Inserire email")
    @Pattern(regexp = "[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
            + "[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
            + "(?:[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?\\.)+[A-Za-z0-9]"
            + "(?:[A-Za-z0-9-]*[A-Za-z0-9])?",
            message = "{invalid.email}")
    private String email;
    
    private String phone;
    
    @NotEmpty
    @Column(nullable = false, unique = true)
    private String adminEmail;
    
    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "activity_admin_activity_admin_id", unique = true)
    public ActivityAdmin activityAdmin;
    
    @ManyToOne
    @JoinColumn(name = "loyalty_program_loyalty_program_id")
    private LoyaltyProgram loyaltyProgram;

    /**
     * Constructor for Activity with detailed parameters.
     *
     * @param name           The name of the activity.
     * @param address        The address of the activity.
     * @param vatCode        The VAT code of the activity.
     * @param email          The email address of the activity.
     * @param phone          The phone number of the activity.
     * @param activityAdmin  The admin associated with the activity.
     * @param loyaltyProgram The loyalty program associated with the activity.
     */
    public Activity(String name, String address, String vatCode, String email, String phone, ActivityAdmin activityAdmin, LoyaltyProgram loyaltyProgram) {
        this.name = name;
        this.address = address;
        this.vatCode = vatCode;
        this.email = email;
        this.phone = phone;
        this.activityAdmin = activityAdmin;
        this.loyaltyProgram = loyaltyProgram;
    }

    /**
     * Constructor for Activity with essential parameters.
     *
     * @param name        The name of the activity.
     * @param address     The address of the activity.
     * @param vatCode     The VAT code of the activity.
     * @param email       The email address of the activity.
     * @param phone       The phone number of the activity.
     * @param adminEmail  The admin email of the activity.
     */
    public Activity(String name, String address, String vatCode, String email, String phone, String adminEmail) {
        this.name = name;
        this.address = address;
        this.vatCode = vatCode;
        this.email = email;
        this.phone = phone;
        this.adminEmail = adminEmail;
    }

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Activity activity = (Activity) o;
        return activityId != null && Objects.equals(activityId, activity.activityId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

	/**
	 * @return the vatCode
	 */
	public String getVatCode() {
		return vatCode;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the adminEmail
	 */
	public String getAdminEmail() {
		return adminEmail;
	}

	/**
	 * @return the activityAdmin
	 */
	public ActivityAdmin getActivityAdmin() {
		return activityAdmin;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param activityAdmin the activityAdmin to set
	 */
	public void setActivityAdmin(ActivityAdmin activityAdmin) {
		this.activityAdmin = activityAdmin;
	}

}