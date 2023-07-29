package org.example.dataModel;

import lombok.*;
import org.example.model.enums.Status;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "document_entity")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_id")
    public Long documentId;

    @Column(name = "document_type", nullable = false)
    public String documentType;

    @Column(name = "profile_picture")
    public String documentUrl;

    @Column(name = "verification_status", nullable = false)
    @Enumerated(EnumType.STRING)
    public Status verificationStatus;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    public LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    public LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    public DriverEntity driver;

}
