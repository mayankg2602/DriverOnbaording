package org.example.repository.interfaces;

import org.example.dataModel.DriverEntity;

public interface IDriverRepository {
    DriverEntity save(DriverEntity driver);

    DriverEntity findById(Long driverId);
}
