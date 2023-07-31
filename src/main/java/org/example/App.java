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
 * Main application class for Driver Onboarding service.
 */
public class App extends Application<AppConfiguration> {
    private static final String APPLICATION_NAME = "DriverOnboarding";

    private GuiceBundle<AppConfiguration> guiceBundle;

    @Override
    public String getName() {
        return APPLICATION_NAME;
    }

    public static void main(String[] args) throws Exception {
        new App().run(args);
        System.out.println("Driver Onboarding App has started");
    }

    /**
     * Initialize the application bootstrap.
     *
     * @param bootstrap The bootstrap object.
     */
    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {
        // Install the JerseyGuiceUtils to use Guice in Jersey
        JerseyGuiceUtils.install(((s, serviceLocator) -> null));

        // Create Guice bundle with ClientModule
        guiceBundle = GuiceBundle.defaultBuilder(AppConfiguration.class)
                .modules(new ClientModule(bootstrap))
                .stage(Stage.PRODUCTION)
                .build();

        // Add Guice bundle to bootstrap
        bootstrap.addBundle(guiceBundle);
    }

    /**
     * Run the application.
     *
     * @param appConfiguration The application configuration.
     * @param environment      The application environment.
     * @throws Exception If an error occurs during the application run.
     */
    @Override
    public void run(AppConfiguration appConfiguration, Environment environment) throws Exception {
        // Log the value from dev.yml
        String databaseUser = appConfiguration.getDataSourceFactory().getUser();
        System.out.println("Value from dev.yml is " + databaseUser);

        // Register resources with Jersey
        registerResources(environment);
    }

    /**
     * Register all resources with Jersey.
     *
     * @param environment The application environment.
     */
    private void registerResources(Environment environment) {
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
        // Do nothing for bootstrap logging, since we are using SLF4J for logging.
    }
}
