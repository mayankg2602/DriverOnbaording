package org.example.dataModel;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "availability_entity")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class AvailabilityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "availability_id")
    public Long availabilityId;

    @Column(name = "availability_status", nullable = false)
    public boolean availabilityStatus;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    public LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    public LocalDateTime updatedAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        AvailabilityEntity that = (AvailabilityEntity) o;
        return availabilityId != null && Objects.equals(availabilityId, that.availabilityId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
