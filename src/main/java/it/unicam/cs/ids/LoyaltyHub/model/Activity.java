package it.unicam.cs.ids.LoyaltyHub.model;

import jakarta.persistence.*;
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

    private String vatCode;

    private String email;

    private String phone;

    private String adminEmail;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "activity_admin_activity_admin_id")
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

}