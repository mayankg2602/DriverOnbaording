package org.example.service.interfaces;

/**
 * Interface for managing operations related to background checks.
 */
public interface IBackgroundCheckService {

    /**
     * Approves a document.
     *
     * @param documentId The ID of the document to be approved.
     * @throws Exception If an error occurs during approval.
     */
    void approveDocument(Long documentId) throws Exception;

    /**
     * Approves a driver.
     *
     * @param driverId The ID of the driver to be approved.
     * @throws Exception If an error occurs during approval.
     */
    void approveDriver(Long driverId) throws Exception;
}