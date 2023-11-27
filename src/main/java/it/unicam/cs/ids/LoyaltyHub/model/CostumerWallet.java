package it.unicam.cs.ids.LoyaltyHub.model;

import jakarta.persistence.*;
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
@Table(name = "costumer_wallet")
public class CostumerWallet {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "costumer_wallet_seq")
    @SequenceGenerator
            (name = "costumer_wallet_seq",
                    sequenceName = "costumer_wallet_seq",
                    allocationSize = 1)
    @Column(name = "wallet_id", nullable = false)
    private Long walletId;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "fidelity_card_fidelity_card_id")
    private FidelityCard fidelityCard;


    public CostumerWallet(FidelityCard fidelityCard) {
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