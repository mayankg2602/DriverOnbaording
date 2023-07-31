package org.example.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.dataModel.DriverDocumentEntity;
import org.example.model.enums.DocumentType;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DriverDocument {
    @JsonProperty("document_id")
    private Long documentId;

    @JsonProperty("document_type")
    private DocumentType documentType;

    @JsonProperty("document_url")
    private String documentUrl;

    public DriverDocument(DriverDocumentEntity driverDocumentEntity) {
        if (driverDocumentEntity != null) {
            this.documentId = driverDocumentEntity.getDocumentId();
            this.documentType = driverDocumentEntity.getDocumentType();
            this.documentUrl = driverDocumentEntity.getDocumentUrl();
        }
    }
}
