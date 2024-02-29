package it.unicam.cs.ids.LoyaltyHub.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Represents a request to join an activity within the loyalty platform.
 * Includes details like activity's name, address, email, phone, VAT code, and the request date.
 */
@Entity
@NoArgsConstructor
@Setter
@Getter
public class ActivityJoinRequest implements JoinRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activityGenerator")
    @SequenceGenerator(name = "activityGenerator", allocationSize = 1)
    @Column(name = "activity_request_id", nullable = false)
    private Long activityRequestId;

    private boolean validated = false;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private String activityEmail;
    private String activityName;
    private String vatCode;
    private String address;
    private String phone;

    /**
     * Constructs a new ActivityJoinRequest with specified details and sets the request date to the current time.
     *
     * @param activityName The name of the activity.
     * @param address      The physical address of the activity.
     * @param activityEmail The email address associated with the activity.
     * @param phone        The contact phone number for the activity.
     * @param vatCode      The VAT code of the activity.
     */
    public ActivityJoinRequest(String activityName, String address, String activityEmail, String phone, String vatCode) {
        this.activityName = activityName;
        this.address = address;
        this.activityEmail = activityEmail;
        this.phone = phone;
        this.vatCode = vatCode;
        this.date = new Date(); // Uses current date
    }

    /**
     * Marks this join request as validated.
     */
    public void validate() {
        this.validated = true;
    }

    /**
     * Retrieves the email address associated with the activity in this join request.
     *
     * @return The email address of the activity.
     */
    public String getActivityEmail() {
        return this.activityEmail;
    }

    /**
     * Retrieves the phone number associated with the activity in this join request.
     *
     * @return The phone number of the activity.
     */
    public String getPhone() {
        return this.phone;
    }

    /**
     * Retrieves the name associated with the activity in this join request.
     *
     * @return The name number of the activity.
     */
	public String getActivityName() {
		return activityName;
	}

	public String getVatCode() {
		return this.vatCode;
	}

	public String getAddress() {
		return this.address;
	}
}
