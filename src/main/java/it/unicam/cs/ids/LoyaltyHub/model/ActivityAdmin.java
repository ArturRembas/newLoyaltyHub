package it.unicam.cs.ids.LoyaltyHub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name = "activity_admin")
public class ActivityAdmin implements Employee {
    @Id
    @SequenceGenerator(
            name = "admin_sequence",
            sequenceName = "admin_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "admin_sequence")
    @SequenceGenerator
            (name = "admin_sequence")
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

	public Long getActivityAdminId() {
		return activityAdminId;
	}

	public void setActivityAdminId(Long activityAdminId) {
		this.activityAdminId = activityAdminId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public UserType getUserType() {
		return userType;
	}


}