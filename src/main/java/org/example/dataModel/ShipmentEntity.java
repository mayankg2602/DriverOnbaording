package org.example.dataModel;

import lombok.*;
import org.example.model.enums.ShipmentStatus;
import org.example.model.enums.ShipmentType;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "shipment_entity")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class ShipmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipment_id")
    public Long shipmentId;

    @Column(name = "tracking_number", nullable = false)
    public String trackingNumber;

    @Column(name = "shipment_status", nullable = false)
    @Enumerated(EnumType.STRING)
    public ShipmentStatus shipmentStatus;

    @Column(name = "order_date", nullable = false)
    public LocalDateTime orderDate;

    @Column(name = "shipment_type", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    public ShipmentType shipmentType;

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
        ShipmentEntity that = (ShipmentEntity) o;
        return shipmentId != null && Objects.equals(shipmentId, that.shipmentId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
