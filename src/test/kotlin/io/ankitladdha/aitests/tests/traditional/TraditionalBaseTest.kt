package io.ankitladdha.aitests.tests.traditional

import io.ankitladdha.aitests.utils.AssertManager
import io.ankitladdha.aitests.utils.DriverManager
import io.ankitladdha.aitests.utils.setUpBaseUrl
import org.assertj.core.api.SoftAssertions
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import org.testng.annotations.BeforeSuite

open class TraditionalBaseTest {

    @BeforeSuite(groups = ["traditional"])
    fun suiteLevelOneTimeSetup() {
        println("[LOG]: One time setup for traditional tests")
        setUpBaseUrl()
    }

    @BeforeMethod(groups = ["traditional"])
    fun setUpTests() {
        println("[LOG]: Before Test method setup for traditional tests")
        AssertManager.softAssert = SoftAssertions()
    }

    @AfterMethod(groups = ["traditional"])
    fun tearDown() {
        println("[LOG]: After Test method setup for traditional tests")
        DriverManager.quitDriver()
    }
}




