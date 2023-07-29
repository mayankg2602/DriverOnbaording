package org.example.dataModel;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "driver_profile_entity")
@NoArgsConstructor
@Getter
@Setter
public class DriverProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    public Long profileId;

    @Column(name = "profile_picture")
    public String profilePicture;

    @Column(name = "license_info")
    public String licenseInfo;

    @Column(name = "vehicle_info")
    public String vehicleInfo;

    @Column(name = "insurance_info")
    public String insuranceInfo;

    @Column(name = "other_info")
    public String otherInfo;

    @Column(name = "created_at", updatable = false)
    public LocalDateTime createdAt;

    @Column(name = "updated_at")
    public LocalDateTime updatedAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    public DriverEntity driver;
}
