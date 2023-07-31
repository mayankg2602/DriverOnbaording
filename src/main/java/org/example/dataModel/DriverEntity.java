package org.example.dataModel;

import lombok.*;
import org.example.model.enums.Status;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "driver_entity")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class DriverEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driver_id")
    public Long driverId;

    @Column(name = "name", nullable = false)
    public String name;

    @Column(name = "email", nullable = false, unique = true)
    @Email
    public String email;

    @Column(name = "phone_number", nullable = false)
    public String phoneNumber;

    @Column(name = "registration_date", nullable = false)
    public LocalDateTime registrationDate;

    @Column(name = "password", nullable = false)
    public String password;

    @Column(name = "address")
    public String address;

    @Column(name = "verification_status", nullable = false)
    @Enumerated(EnumType.STRING)
    public Status verificationStatus;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name="profile_id")
    public DriverProfileEntity profile;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @ToString.Exclude
    @JoinColumn(name="driver_id")
    public List<DriverDocumentEntity> documents;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @ToString.Exclude
    @JoinColumn(name="driver_id")
    public List<ShipmentEntity> shipments;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="availability_id")
    public AvailabilityEntity availability;

    @Column(name = "created_at", updatable = false)
    public LocalDateTime createdAt;

    @Column(name = "updated_at")
    public LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DriverEntity that = (DriverEntity) o;
        return driverId != null && Objects.equals(driverId, that.driverId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
