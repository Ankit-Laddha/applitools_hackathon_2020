package io.ankitladdha.aitests.utils

import org.openqa.selenium.WebDriver

object DriverManager {
    private val tlWebDriver =
        ThreadLocal<WebDriver?>()

    @get:Synchronized
    @set:Synchronized
    var webDriver: WebDriver
        get() = tlWebDriver.get()!!
        set(driver) {
            tlWebDriver.set(driver)
        }

    @Synchronized
    fun quitDriver() {
        val driver = tlWebDriver.get()
        if (driver != null) {
            driver.quit()
            tlWebDriver.set(null)
        }
    }
}
