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
@Table(name = "costumer")
public class Costumer implements User{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "costumer_seq")
    @SequenceGenerator(
            name = "costumer_seq",
            sequenceName = "costumer_seq",
            allocationSize = 1)
    @Column(name = "costumer_id", nullable = false)
    private Long costumerId;

    private String name;

    private String surname;

    private String email;

    private String phone;


    private final UserType userType = UserType.COSTUMER;

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "costumer_wallet_wallet_id")
    private CostumerWallet costumerWallet;


    public Costumer(String name, String surname, String email, String phone) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
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