package it.unicam.cs.ids.LoyaltyHub.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.util.Objects;

/**
 * Represents a wallet associated with a customer in the LoyaltyHub system.
 * This entity manages the fidelity card information for the customer's wallet.
 */
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "costumer_wallet")
public class CostumerWallet {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "costumer_wallet_seq")
    @SequenceGenerator(name = "costumer_wallet_seq", sequenceName = "costumer_wallet_seq", allocationSize = 1)
    @Column(name = "wallet_id", nullable = false)
    private Long walletId;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "fidelity_card_fidelity_card_id")
    private FidelityCard fidelityCard;

    /**
     * Creates a new CostumerWallet with the specified fidelity card.
     *
     * @param fidelityCard The fidelity card associated with this wallet.
     */
    public CostumerWallet(FidelityCard fidelityCard) {
        this.fidelityCard = fidelityCard;
    }

    /**
	 * @return the fidelityCard
	 */
	public FidelityCard getFidelityCard() {
		return fidelityCard;
	}

	/**
	 * @param fidelityCard the fidelityCard to set
	 */
	public void setFidelityCard(FidelityCard fidelityCard) {
		this.fidelityCard = fidelityCard;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CostumerWallet that = (CostumerWallet) o;
        return walletId != null && Objects.equals(walletId, that.walletId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}