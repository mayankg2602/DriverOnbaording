package org.example;


import com.google.inject.Stage;
import com.hubspot.dropwizard.guicier.GuiceBundle;
import com.squarespace.jersey2.guice.JerseyGuiceUtils;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.example.guice.ClientModule;
import org.example.resource.*;

/**
 * Hello world!
 */
public class App extends Application<AppConfiguration> {
    GuiceBundle<AppConfiguration> guiceBundle;
    private static final String appName = "DriverOnboarding";

    @Override
    public String getName() {
        return appName;
    }

    public static void main(String[] args) throws Exception {
        new App().run(args);
        System.out.println("Driver Onboarding service is up");
    }


    /**
     * @param bootstrap
     */
    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {
        JerseyGuiceUtils.install(((s, serviceLocator) -> null));
        guiceBundle = GuiceBundle.defaultBuilder(AppConfiguration.class)
                .modules(new ClientModule(bootstrap))
                .stage(Stage.PRODUCTION)
                .build();
        bootstrap.addBundle(guiceBundle);
//        bootstrap.addBundle(new ConfigServiceB);
    }

    /**
     * @param appConfiguration
     * @param environment
     * @throws Exception
     */
    @Override
    public void run(AppConfiguration appConfiguration, Environment environment) throws Exception {
        System.out.println("Value from dev.yml is " + appConfiguration.getDatabase().getUser());

        environment.jersey().register(AvailabilityResource.class);
        environment.jersey().register(BackgroundCheckResource.class);
        environment.jersey().register(DashboardResource.class);
        environment.jersey().register(ShipmentResource.class);
        environment.jersey().register(DriverDocumentResource.class);
        environment.jersey().register(DriverProfileResource.class);
        environment.jersey().register(DriverResource.class);
    }

    @Override
    protected void bootstrapLogging() {
    }
}
