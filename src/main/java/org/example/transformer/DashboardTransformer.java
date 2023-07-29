package org.example.transformer;

import org.example.dataModel.DriverEntity;
import org.example.model.Dashboard;
import org.example.model.Driver;
import org.example.model.DriverDocuments;
import org.example.model.ShippingOrders;

public class DashboardTransformer {
    public static Dashboard transformEntityToResponseModel(DriverEntity driver) {
        Dashboard dashboard = new Dashboard();
        dashboard.setDriverInfo(new Driver(driver));
        dashboard.setDocuments(new DriverDocuments(driver.getDocuments()));
        dashboard.setShipments(new ShippingOrders(driver.getShipments()));
        return dashboard;
    }
}
