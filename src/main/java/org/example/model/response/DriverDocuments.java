package org.example.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.dataModel.DriverDocumentEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A response object containing driver document details.
 */
@Getter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DriverDocuments {

    @JsonProperty("documents")
    private List<DriverDocument> documents = new ArrayList<>();

    /**
     * Construct a new DriverDocuments object from a list of DriverDocumentEntity objects.
     *
     * @param documentEntities the list of DriverDocumentEntity objects.
     */
    public DriverDocuments(List<DriverDocumentEntity> documentEntities) {
        if (documentEntities != null) {
            this.documents = documentEntities.stream()
                    .map(DriverDocument::new)
                    .collect(Collectors.toList());
        }
    }
}
