package io.ankitladdha.aitests.listeners

import io.ankitladdha.aitests.utils.DriverManager
import io.qameta.allure.Attachment
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import org.openqa.selenium.WebDriver
import org.testng.ITestListener
import org.testng.ITestResult

class TestAllureListener : ITestListener {
    // Png attachments for Allure
    @Attachment(value = "Page screenshot", type = "image/png")
    fun saveScreenshotPNG(driver: WebDriver): ByteArray {
        return (driver as TakesScreenshot).getScreenshotAs(OutputType.BYTES)
    }

    override fun onTestFailure(iTestResult: ITestResult) {
        println("I am in onTestFailure method: `" + TestAllureListener.Companion.getTestMethodName(iTestResult) + "` failed")
        val driver: WebDriver = DriverManager.webDriver
        // Allure ScreenShotRobot
        if (driver is WebDriver) {
            println("Screenshot captured for test case:" + TestAllureListener.Companion.getTestMethodName(iTestResult))
            saveScreenshotPNG(driver)
        }
    }

    companion object {
        private fun getTestMethodName(iTestResult: ITestResult): String {
            return iTestResult.method.constructorOrMethod.name
        }
    }
}
