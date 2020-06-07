package io.ankitladdha.aitests.utils

import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.WebDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.edge.EdgeDriver
import org.openqa.selenium.firefox.FirefoxDriver

object DriverFactory {
    fun createInstance(browser: String) {
        val driver: WebDriver = when {
            browser.contains("chrome", ignoreCase = true) -> {
                WebDriverManager.chromedriver().setup()
                ChromeDriver()
            }
            browser.contains("firefox", ignoreCase = true) -> {
                WebDriverManager.firefoxdriver().setup()
                FirefoxDriver()
            }
            browser.contains("edge", ignoreCase = true) -> {
                WebDriverManager.edgedriver().setup()
                EdgeDriver()
            }
            else -> {
                throw IllegalArgumentException("Not Supported Browser <$browser>")
            }
        }
        DriverManager.webDriver = driver
    }
}
