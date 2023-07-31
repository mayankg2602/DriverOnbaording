package org.example.dataModel;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "driver_profile_entity")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class DriverProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    public Long profileId;

    @Column(name = "profile_picture")
    public String profilePicture = "";

    @Column(name = "license_info")
    public String licenseInfo = "";

    @Column(name = "vehicle_info")
    public String vehicleInfo = "";

    @Column(name = "insurance_info")
    public String insuranceInfo = "";

    @Column(name = "other_info")
    public String otherInfo = "";

    @Column(name = "created_at", updatable = false, nullable = false)
    public LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    public LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DriverProfileEntity that = (DriverProfileEntity) o;
        return profileId != null && Objects.equals(profileId, that.profileId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
