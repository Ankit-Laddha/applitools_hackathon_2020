package io.ankitladdha.aitests.tests.modern

import com.applitools.eyes.BatchInfo
import com.applitools.eyes.StdoutLogHandler
import com.applitools.eyes.selenium.BrowserType
import com.applitools.eyes.selenium.Configuration
import com.applitools.eyes.selenium.Eyes
import com.applitools.eyes.visualgrid.model.DeviceName
import com.applitools.eyes.visualgrid.model.ScreenOrientation
import com.applitools.eyes.visualgrid.services.VisualGridRunner
import com.google.common.base.Strings
import io.ankitladdha.aitests.utils.DriverFactory
import io.ankitladdha.aitests.utils.DriverManager
import io.ankitladdha.aitests.utils.baseUrl
import io.ankitladdha.aitests.utils.setUpBaseUrl
import io.github.bonigarcia.wdm.WebDriverManager
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import org.testng.annotations.BeforeSuite

open class ModernBaseTest {

    protected lateinit var eyes: Eyes

    @BeforeSuite(groups = ["modern"])
    fun suiteLevelOneTimeSetup() {

        println("[LOG]: one time setup for modern tests")
        val runner = VisualGridRunner(10)

        // Create Eyes object with the runner, meaning it'll be a Visual Grid eyes.
        eyes = Eyes(runner)
        setUpEyes(eyes)
        setUpBaseUrl()
    }

    private fun setUpEyes(eyes: Eyes) { // Initialize eyes Configuration
        val apiKey = System.getenv("APPLITOOLS_API_KEY")
        if (Strings.isNullOrEmpty(apiKey)) {
            throw RuntimeException("No API Key found; Please set environment variable 'APPLITOOLS_API_KEY'.")
        }
        eyes.apiKey = apiKey

        val config = Configuration()

        // create a new batch info instance and set it to the configuration

        config.batch = BatchInfo("UFG Hackathon [Batch]")

        // Add browsers with different viewports
        config.addBrowser(1200, 700, BrowserType.CHROME)
        config.addBrowser(1200, 700, BrowserType.FIREFOX)
        config.addBrowser(1200, 700, BrowserType.EDGE_CHROMIUM)
        config.addBrowser(768, 700, BrowserType.CHROME)
        config.addBrowser(768, 700, BrowserType.FIREFOX)
        config.addBrowser(768, 700, BrowserType.EDGE_CHROMIUM)
        // Add mobile emulation devices in Portrait mode
        config.addDeviceEmulation(
            DeviceName.Pixel_2_XL, ScreenOrientation.PORTRAIT
        )
        config.addDeviceEmulation(
            DeviceName.iPhone_X, ScreenOrientation.PORTRAIT
        )
        // Set the configuration object to eyes
        eyes.configuration = config
        eyes.logHandler = StdoutLogHandler(false)
    }

    @BeforeMethod(groups = ["modern"])
    fun setUpTests() {
        println("[LOG]: Before test method setup for modern tests")
        DriverFactory.createInstance("chrome")
        WebDriverManager.chromedriver().setup()
        DriverManager.webDriver.get(baseUrl)
    }

    @AfterMethod(groups = ["modern"])
    fun tearDown() {
        println("[LOG]: After test method setup for modern tests")
        eyes.abortIfNotClosed()
        DriverManager.quitDriver()
    }
}


