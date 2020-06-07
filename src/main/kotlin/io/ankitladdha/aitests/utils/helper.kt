package io.ankitladdha.aitests.utils

import io.ankitladdha.aitests.utils.AssertManager.softAssert
import org.openqa.selenium.By
import org.openqa.selenium.NoSuchElementException
import org.openqa.selenium.StaleElementReferenceException
import org.openqa.selenium.TimeoutException
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.io.BufferedWriter
import java.io.FileWriter

private const val baseUrlV1 = "https://demo.applitools.com/gridHackathonV1.html"
private const val baseUrlV2 = "https://demo.applitools.com/gridHackathonV2.html"
lateinit var baseUrl: String
lateinit var appVersion: String

fun setUpBaseUrl() {
    appVersion = if (System.getenv("APP_VERSION").isNullOrEmpty()) "v1" else System.getenv("APP_VERSION")
    baseUrl = when (appVersion) {
        "v1" -> baseUrlV1
        "v2" -> baseUrlV2
        else -> baseUrlV1
    }
}

fun reportText(
    text: String
) {
    try {
        BufferedWriter(FileWriter("Traditional-$appVersion-TestResults.txt", true)).use { writer ->
            writer.newLine()
            writer.write("**********************[$text]**********************")
            writer.newLine()
        }
    } catch (e: Exception) {
        println("[Log]: Error writing to report file")
        e.printStackTrace()
    }
}

fun reportStep(
    task: String,
    stepName: String,
    locator: String,
    comparisonResult: Boolean,
    viewport: String,
    browser: String,
    device: String
) {
    try {
        BufferedWriter(FileWriter("Traditional-$appVersion-TestResults.txt", true)).use { writer ->
            writer.write("------------------------------------------------------------------")
            writer.newLine()
            writer.write(
                "Task: $task \n\t Check Name: $stepName \n\t Locator: [$locator] \n\t Expecting: $comparisonResult" +
                    " \n\t Browser: $browser \n\t Viewport: $viewport \n\t Device: $device " +
                    "\nStatus: " + if (comparisonResult) "<Pass>" else "<Fail>"
            )
            writer.newLine()
            writer.write("------------------------------------------------------------------")
            writer.newLine()
        }
    } catch (e: Exception) {
        println("[Log]: Error writing to report file")
        e.printStackTrace()
    }

    softAssert.assertThat(comparisonResult).`as`("<$stepName> expectation failed").isTrue
}

fun waitFor(locatorBy: By): WebElement {
    return WebDriverWait(
        DriverManager.webDriver,
        5
    ).until(ExpectedConditions.visibilityOfElementLocated(locatorBy))
}

fun waitForAll(locatorBy: By): List<WebElement> {

    return WebDriverWait(
        DriverManager.webDriver,
        5
    ).until(
        ExpectedConditions
            .visibilityOfAllElements(DriverManager.webDriver.findElements(locatorBy))
    )
}

fun isElementDisplayed(locator: By): Boolean {
    return try {
        val element = waitFor(locator)
        element.isDisplayed // && !element.getCssValue("display").contains("none", true)
    } catch (e: Exception) {
        when (e) {
            is NoSuchElementException,
            is TimeoutException,
            is StaleElementReferenceException
            -> false
            else -> throw e
        }
    }
}

fun WebElement.isStrikeThrough() =
    this.getCssValue("text-decoration").contains("line-through", true)

