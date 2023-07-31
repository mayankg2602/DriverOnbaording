package org.example.dataModel;

import lombok.*;
import org.example.model.enums.DocumentType;
import org.example.model.enums.Status;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "document_entity")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class DriverDocumentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_id")
    public Long documentId;

    @Column(name = "document_type", nullable = false, unique = true)
    public DocumentType documentType;

    @Column(name = "document_url", nullable = false)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DriverDocumentEntity that = (DriverDocumentEntity) o;
        return documentId != null && Objects.equals(documentId, that.documentId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
