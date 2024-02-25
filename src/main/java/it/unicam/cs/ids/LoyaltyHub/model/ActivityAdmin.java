package it.unicam.cs.ids.LoyaltyHub.model;

import java.util.Objects;

import org.hibernate.Hibernate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

/**
 * Represents an admin of an activity in the LoyaltyHub system.
 * This entity manages the administrative details for a business activity.
 */
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "activity_admin")
public class ActivityAdmin implements Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admin_sequence")
    @SequenceGenerator(name = "admin_sequence", sequenceName = "admin_sequence", allocationSize = 1)
    @Column(name = "activity_admin_id", nullable = false)
    private Long activityAdminId;

    private String name;
    private String surname;

    @Column(nullable = false, unique = true)
    @NotEmpty(message = "Inserire email")
    private String email;

    @Column(nullable = false, unique = true)
    private String phone;

    private final UserType userType = UserType.ACTIVITY_ADMIN;

    @OneToOne(mappedBy = "activityAdmin", orphanRemoval = true)
    private Activity activity;

    /**
     * Creates a new ActivityAdmin with specified details.
     *
     * @param name    The name of the admin.
     * @param surname The surname of the admin.
     * @param email   The email address of the admin.
     * @param phone   The phone number of the admin.
     */
    public ActivityAdmin(String name, String surname, String email, String phone) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ActivityAdmin that = (ActivityAdmin) o;
        return activityAdminId != null && Objects.equals(activityAdminId, that.activityAdminId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
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
	 * @return the activityAdminId
	 */
	public Long getActivityAdminId() {
		return activityAdminId;
	}

	/**
	 * @param activity the activity to set
	 */
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
}