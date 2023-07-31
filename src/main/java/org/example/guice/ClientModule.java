package org.example.guice;

import com.google.inject.*;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.UnitOfWorkAwareProxyFactory;
import io.dropwizard.setup.Bootstrap;
import org.example.AppConfiguration;
import org.example.dataModel.*;
import org.example.service.*;
import org.example.service.interfaces.*;
import org.example.repository.DocumentRepository;
import org.example.repository.DriverRepository;
import org.example.repository.ShipmentRepository;
import org.example.repository.interfaces.IDocumentRepository;
import org.example.repository.interfaces.IDriverRepository;
import org.example.repository.interfaces.IShipmentRepository;
import org.example.validator.*;
import org.example.validator.interfaces.IDriverDocumentValidator;
import org.example.validator.interfaces.IDriverProfileValidator;
import org.example.validator.interfaces.IDriverValidator;
import org.example.validator.interfaces.IShipmentValidator;
import org.hibernate.SessionFactory;

public class ClientModule implements com.google.inject.Module {
    private final HibernateBundle<AppConfiguration> hibernate = new HibernateBundle<AppConfiguration>(DriverEntity.class,
            DriverProfileEntity.class,
            DriverDocumentEntity.class,
            ShipmentEntity.class,
            AvailabilityEntity.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(AppConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    public ClientModule(Bootstrap<AppConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
    }

    @Provides
    @Singleton
    public SessionFactory getSessionFactory() {
        return hibernate.getSessionFactory();
    }

    @Provides
    @Singleton
    public UnitOfWorkAwareProxyFactory getUnitOfWorkAwareProxyFactory() {
        return new UnitOfWorkAwareProxyFactory(hibernate);
    }

    @Provides
    @Singleton
    public DriverService getDriverRegistrationManager(DriverRepository driverRepository, DriverValidator driverValidator) {
        return new DriverService(driverRepository, driverValidator);
    }

    @Provides
    @Singleton
    public DriverDriverDocumentService getDocumentServiceManager(DriverDocumentValidator documentValidator, DriverRepository driverRepository) {
        return new DriverDriverDocumentService(documentValidator, driverRepository);
    }

    @Provides
    @Singleton
    public DriverProfileService getProfileManagementService(DriverRepository driverRepository, DriverProfileValidator driverProfileValidator) {
        return new DriverProfileService(driverRepository, driverProfileValidator);
    }

    @Provides
    @Singleton
    public ShipmentService getShipmentService(ShipmentRepository shipmentRepository, ShipmentValidator shipmentValidator, DriverRepository driverRepository) {
        return new ShipmentService(shipmentRepository, shipmentValidator, driverRepository);
    }

    @Provides
    @Singleton
    public DriverRepository getDriverRepositoryImpl(SessionFactory sessionFactory, UnitOfWorkAwareProxyFactory unitOfWorkAwareProxyFactory) {
        return unitOfWorkAwareProxyFactory.create(DriverRepository.class,
                new Class[]{SessionFactory.class}, new Object[]{sessionFactory});
    }

    @Provides
    @Singleton
    public ShipmentRepository getShipmentRepository(SessionFactory sessionFactory, UnitOfWorkAwareProxyFactory unitOfWorkAwareProxyFactory ) {
        return unitOfWorkAwareProxyFactory.create(ShipmentRepository.class,
                new Class[]{SessionFactory.class}, new Object[]{sessionFactory});
    }

    @Provides
    @Singleton
    public DocumentRepository getDocumentRepository(SessionFactory sessionFactory, UnitOfWorkAwareProxyFactory unitOfWorkAwareProxyFactory) {
        return unitOfWorkAwareProxyFactory.create(DocumentRepository.class,
                new Class[]{SessionFactory.class}, new Object[]{sessionFactory});
    }

    @Provides
    @Singleton
    public DriverValidator getDriverRegistrationValidator() {
        return new DriverValidator();
    }

    @Provides
    @Singleton
    public DriverProfileValidator getProfileManagementValidator() {
        return new DriverProfileValidator();
    }

    @Provides
    @Singleton
    public ShipmentValidator getShipmentValidator() {
        return new ShipmentValidator();
    }

    @Provides
    @Singleton
    public DriverDocumentValidator getDriverDocumentValidator() {
        return new DriverDocumentValidator();
    }

    /**
     * @param binder
     */
    @Override
    public void configure(Binder binder) {
        binder.bind(IDriverService.class).to(DriverService.class);
        binder.bind(IBackgroundCheckService.class).to(BackgroundCheckService.class);
        binder.bind(IDriverDocumentService.class).to(DriverDriverDocumentService.class);
        binder.bind(IDashboardService.class).to(DashboardService.class);
        binder.bind(IShipmentService.class).to(ShipmentService.class);
        binder.bind(IDriverProfileService.class).to(DriverProfileService.class);

        binder.bind(IDocumentRepository.class).to(DocumentRepository.class);
        binder.bind(IDriverRepository.class).to(DriverRepository.class);
        binder.bind(IShipmentRepository.class).to(ShipmentRepository.class);

        binder.bind(IDriverDocumentValidator.class).to(DriverDocumentValidator.class);
        binder.bind(IDriverProfileValidator.class).to(DriverProfileValidator.class);
        binder.bind(IDriverValidator.class).to(DriverValidator.class);
        binder.bind(IShipmentValidator.class).to(ShipmentValidator.class);
    }
}
