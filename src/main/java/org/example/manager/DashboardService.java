package org.example.manager;

import org.example.dataModel.DriverEntity;
import org.example.manager.interfaces.IDashboardService;
import org.example.model.Dashboard;
import org.example.repository.interfaces.IDriverRepository;
import org.example.transformer.DashboardTransformer;

import javax.inject.Inject;

public class DashboardService implements IDashboardService {

    private IDriverRepository IDriverRepository;

    @Inject
    public DashboardService(IDriverRepository IDriverRepository) {
        this.IDriverRepository = IDriverRepository;
    }

    @Override
    public Dashboard getDashboard(Long driverId) {
        DriverEntity driver = IDriverRepository.findById(driverId);
        return DashboardTransformer.transformEntityToResponseModel(driver);
    }

}
