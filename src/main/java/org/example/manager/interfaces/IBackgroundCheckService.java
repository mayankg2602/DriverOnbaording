package org.example.manager.interfaces;

public interface IBackgroundCheckService {
    void approveDocument(Long documentId) throws Exception;
    void approveDriver(Long driverId) throws Exception;
}
