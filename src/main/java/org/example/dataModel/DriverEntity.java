package org.example.dataModel;

import lombok.*;
import org.example.model.enums.Status;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "driver_entity")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driver_id")
    public Long driverId;

    @Column(name = "name", nullable = false)
    public String name;

    @Column(name = "email", nullable = false, unique = true)
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

    @OneToOne( mappedBy = "driver", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public DriverProfileEntity profile;

    @OneToMany( mappedBy = "driver", cascade = CascadeType.ALL, orphanRemoval = true)
    public List<DocumentEntity> documents;

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<ShipmentEntity> shipments;

    @OneToOne(mappedBy = "driverEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public AvailabilityEntity availability;

    @Column(name = "created_at", updatable = false)
    public LocalDateTime createdAt;

    @Column(name = "updated_at")
    public LocalDateTime updatedAt;

}
