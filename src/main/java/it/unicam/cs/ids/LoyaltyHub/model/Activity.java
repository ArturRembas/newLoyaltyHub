package it.unicam.cs.ids.LoyaltyHub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

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
    @Email
    @NotEmpty
    @NotNull
    private String email;

    private String phone;

    @NotEmpty
    @Column(nullable = false, unique = true)
    private String adminEmail;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "activity_admin_activity_admin_id", unique = true)
    private ActivityAdmin activityAdmin;

    @ManyToOne
    @JoinColumn(name = "loyalty_program_loyalty_program_id")
    private LoyaltyProgram loyaltyProgram;



    public Activity(String name, String address, String vatCode, String email, String phone, ActivityAdmin activityAdmin, LoyaltyProgram loyaltyProgram) {
        this.name = name;
        this.address = address;
        this.vatCode = vatCode;
        this.email = email;
        this.phone = phone;
        this.activityAdmin = activityAdmin;
        this.loyaltyProgram = loyaltyProgram;
    }

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

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public void setActivityAdmin(ActivityAdmin findAdminByEmail) {
		// TODO Auto-generated method stub
		
	}

	public Long getActivityId() {
		return activityId;
	}

	public void setActivityId(Long activityId) {
		this.activityId = activityId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public LoyaltyProgram getLoyaltyProgram() {
		return loyaltyProgram;
	}

	public void setLoyaltyProgram(LoyaltyProgram loyaltyProgram) {
		this.loyaltyProgram = loyaltyProgram;
	}

	public ActivityAdmin getActivityAdmin() {
		return activityAdmin;
	}

	public String getVatCode() {
		return vatCode;
	}

	public void setVatCode(String vatCode) {
		this.vatCode = vatCode;
	}





}