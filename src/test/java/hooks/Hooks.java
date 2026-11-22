package hooks;

import com.microsoft.playwright.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import java.nio.file.Paths;

public class Hooks {
    private static Playwright playwright;
    private static Browser browser;
    private static BrowserContext context;
    private static Page page;

    public static Page getPage() { return page; }

    @Before
    public void setUp(Scenario scenario) {
    	System.out.println("Starting: " + scenario.getName());
        playwright = Playwright.create();
        String name = System.getProperty("browser", "chromium");
        boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
        BrowserType type = switch (name) {
            case "firefox" -> playwright.firefox();
            case "webkit" -> playwright.webkit();
            default -> playwright.chromium();
        };
        browser = type.launch(new BrowserType.LaunchOptions()
                .setHeadless(headless).setSlowMo(0));
       context = browser.newContext();
//        context = browser.newContext(new Browser.NewContextOptions()
//                .setViewportSize(390, 844).setIsMobile(true));
        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true).setSnapshots(true).setSources(true));
        page = context.newPage();
//        page.route("**/*.css", route -> route.abort());
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            String file = scenario.getName().replaceAll("[^a-zA-Z0-9]", "_");
            context.tracing().stop(new Tracing.StopOptions()
                    .setPath(Paths.get("target/traces/" + file + ".zip")));
        } else {
            context.tracing().stop();
        }
        context.close();
        browser.close();
        playwright.close();
    }
}