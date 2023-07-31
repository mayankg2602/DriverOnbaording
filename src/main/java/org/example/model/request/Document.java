package org.example.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.enums.DocumentType;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Document {

    @NotNull(message = "DocumentType cannot be null.")
    @JsonProperty("document_type")
    private DocumentType documentType;

    @NotNull(message = "Document URL cannot be null.")
    @JsonProperty("document_url")
    private String documentUrl;
}