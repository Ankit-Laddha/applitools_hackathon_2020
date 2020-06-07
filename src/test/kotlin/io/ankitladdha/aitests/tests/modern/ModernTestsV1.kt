package io.ankitladdha.aitests.tests.modern

import com.applitools.eyes.RectangleSize
import com.applitools.eyes.selenium.fluent.Target
import io.ankitladdha.aitests.utils.DriverManager
import io.ankitladdha.aitests.utils.waitFor
import io.ankitladdha.aitests.utils.waitForAll
import org.openqa.selenium.By
import org.testng.annotations.Test

class ModernTestsV1 : ModernBaseTest() {

    private inline fun run(test: () -> Unit) {
        try {
            test()
            eyes.closeAsync()
        } finally {
            eyes.abortAsync()
        }
    }

    @Test(groups = ["modern"], enabled = true)
    fun crossDeviceElementsTest() {
        run {
            eyes.open(DriverManager.webDriver, "TASK_1", "Cross-Device-Elements-Test", RectangleSize(800, 600))
            eyes.check(Target.window().fully().withName("Homepage"))
        }
    }

    @Test(groups = ["modern"], enabled = true)
    fun filterResultTest() {
        run {
            eyes.open(DriverManager.webDriver, "TASK_2", "Filter-Results-Test", RectangleSize(800, 600))
            waitFor(By.cssSelector(".open_filters > .ti-filter")).click()
            waitFor(By.id("LABEL__containerc__104")).click()
            waitFor(By.id("filterBtn")).click()
            val element = waitFor(By.id("product_grid"))
            eyes.check(Target.region(element).fully().withName("Product Grid"))
        }
    }

    @Test(groups = ["modern"], enabled = true)
    fun goToDetailsPageTest() {
        run {
            eyes.open(DriverManager.webDriver, "TASK_3", "Product-Details-Test", RectangleSize(800, 600))
            waitFor(By.cssSelector(".open_filters > .ti-filter")).click()
            waitFor(By.id("LABEL__containerc__104")).click()
            waitFor(By.id("filterBtn")).click()
            waitForAll(By.cssSelector("#product_grid > div"))[0].click()
            eyes.check(Target.window().fully().withName("Product Details test"))
        }
    }
}
