package io.ankitladdha.aitests.tests.traditional

import com.applitools.eyes.selenium.BrowserType
import io.ankitladdha.aitests.utils.AssertManager
import io.ankitladdha.aitests.utils.DriverFactory
import io.ankitladdha.aitests.utils.DriverManager
import io.ankitladdha.aitests.utils.baseUrl
import io.ankitladdha.aitests.utils.isElementDisplayed
import io.ankitladdha.aitests.utils.isStrikeThrough
import io.ankitladdha.aitests.utils.reportStep
import io.ankitladdha.aitests.utils.reportText
import io.ankitladdha.aitests.utils.waitFor
import io.ankitladdha.aitests.utils.waitForAll
import org.openqa.selenium.By
import org.openqa.selenium.Dimension
import org.testng.annotations.DataProvider
import org.testng.annotations.Test

class TraditionalTestsV2 : TraditionalBaseTest() {

    private var result: Boolean = false

    @DataProvider(name = "browserConfig")
    private fun browserConfiguration(): MutableIterator<Array<Any>> {
        return arrayListOf(
            arrayOf(1200, 700, BrowserType.CHROME, "Laptop"),
            arrayOf(1200, 700, BrowserType.FIREFOX, "Laptop"),
            arrayOf(1200, 700, BrowserType.EDGE_CHROMIUM, "Laptop"),
            arrayOf(768, 700, BrowserType.CHROME, "Tablet"),
            arrayOf(768, 700, BrowserType.FIREFOX, "Tablet"),
            arrayOf(768, 700, BrowserType.EDGE_CHROMIUM, "Tablet"),
            arrayOf(411, 843, BrowserType.CHROME, "Mobile")
        ).iterator()
    }

    private fun openBrowser(type: BrowserType) {
        DriverFactory.createInstance(type.name)
        DriverManager.webDriver.get(baseUrl)
    }

    @Test(dataProvider = "browserConfig", groups = ["traditional"], enabled = false)
    fun crossDeviceElementsTest(width: Int, height: Int, type: BrowserType, device: String) {
        openBrowser(type)
        DriverManager.webDriver.manage().window().size = Dimension(width, height)

        reportText("TEST: crossDeviceElementsTest")

        val isSearchBoxDisplayed = isElementDisplayed(By.id("INPUTtext____42"))
        result = if (device.equals("mobile", true)) !isSearchBoxDisplayed else isSearchBoxDisplayed
        reportStep(
            "crossDeviceElementsTest"
            , "Search textbox is displayed ?"
            , "INPUTtext____42"
            , result
            , "$width X $height"
            , type.name
            , device
        )

        // Though the shopping cart is visible, but below traditional assertion do not catch the icon change
        result = isElementDisplayed(By.id("A__cartbt__49"))
        reportStep(
            "crossDeviceElementsTest"
            , "Shopping cart is displayed ?"
            , "A__cartbt__49"
            , result
            , "$width X $height"
            , type.name
            , device
        )

        val isSearchButtonDisplayed = isElementDisplayed(By.id("BUTTONsubmit____43"))
        result = if (device.equals("mobile", true)) !isSearchButtonDisplayed else isSearchButtonDisplayed
        //softAssert.assertThat(result).`as`("Search button").isTrue
        reportStep(
            "crossDeviceElementsTest"
            , "Search button is displayed ?"
            , "BUTTONsubmit____43"
            , result
            , "$width X $height"
            , type.name
            , device
        )

        val areFiltersShown = isElementDisplayed(By.id("filter_col"))
        result = if (device.equals("laptop", true)) areFiltersShown else !areFiltersShown
        //softAssert.assertThat(result).`as`("Left side Filters").isTrue
        reportStep(
            "crossDeviceElementsTest"
            , "Left side Filters are shown ?"
            , "filter_col"
            , result
            , "$width X $height"
            , type.name
            , device
        )

        val isWishlistDisplayed = isElementDisplayed(By.cssSelector(".wishlist"))
        result = if (device.equals("laptop", true)) isWishlistDisplayed else !isWishlistDisplayed

        reportStep(
            "crossDeviceElementsTest"
            , "Wishlist is visible ?"
            , ".wishlist"
            , result
            , "$width X $height"
            , type.name
            , device
        )

        val isGridViewOptionDisplayed = isElementDisplayed(By.cssSelector(".ti-view-grid"))
        result = if (device.equals("laptop", true)) isGridViewOptionDisplayed else !isGridViewOptionDisplayed
        //softAssert.assertThat(result).`as`("Grid view").isTrue

        reportStep(
            "crossDeviceElementsTest"
            , "Show product in <Grid view> option is visible ?"
            , "ti-view-grid"
            , result
            , "$width X $height"
            , type.name
            , device
        )

        val isListViewOptionDisplayed = isElementDisplayed(By.cssSelector(".ti-view-list"))
        result = if (device.equals("laptop", true)) isListViewOptionDisplayed else !isListViewOptionDisplayed

        reportStep(
            "crossDeviceElementsTest"
            , "Show product in <List view> option is visible ?"
            , ".ti-view-list"
            , result
            , "$width X $height"
            , type.name
            , device
        )

        val isFilterLabelDisplayed = isElementDisplayed(By.xpath("//*[@id='ti-filter']/following-sibling::span"))
        result = if (device.equals("tablet", true)) isFilterLabelDisplayed else !isFilterLabelDisplayed

        reportStep(
            "crossDeviceElementsTest"
            , "Filter label is visible ?"
            , "//*[@id='ti-filter']/following-sibling::span"
            , result
            , "$width X $height"
            , type.name
            , device
        )

        var isQuickLinksExpanded = isElementDisplayed(By.xpath("//h3[text()='Quick Links']/following-sibling::div/ul"))
        result = if (device.equals("mobile", true)) !isQuickLinksExpanded else isQuickLinksExpanded

        reportStep(
            "crossDeviceElementsTest"
            , "is QuickLinks Section Expanded ?"
            , "//h3[text()='Quick Links']/following-sibling::div/ul"
            , result
            , "$width X $height"
            , type.name
            , device
        )

        reportText("TEST-ENDs-HERE")
        AssertManager.softAssert.assertAll()
    }

    @Test(dataProvider = "browserConfig", groups = ["traditional"], enabled = false)
    fun filterResults(width: Int, height: Int, type: BrowserType, device: String) {
        openBrowser(type)
        DriverManager.webDriver.manage().window().size = Dimension(width, height)

        reportText("TEST: filterResults")

        // region Filter for shoes by Black Color
        if (!isElementDisplayed(By.id("filter_col"))) {
            waitFor(By.cssSelector(".open_filters > .ti-filter")).click()
        }

        waitFor(By.id("LABEL__containerc__104")).click()
        waitFor(By.id("filterBtn")).click()

        // endregion

        result = waitForAll(By.cssSelector("#product_grid > div")).size == 2

        reportStep(
            "filters Results"
            , "Filter Products = 2 ?"
            , "#product_grid > div"
            , result
            , "$width X $height"
            , type.name
            , device
        )

        val wishlistBy = By.cssSelector("#product_grid > div .ti-heart")
        val isWishlistOptDisplayedForEachProduct = isElementDisplayed(wishlistBy)
            && waitForAll(wishlistBy).size == 2

        result =
            if (device.equals(
                    "laptop",
                    true
                )
            ) !isWishlistOptDisplayedForEachProduct else isWishlistOptDisplayedForEachProduct

        reportStep(
            "filters Results"
            , "Each product has Add to Wishlist Option ?"
            , "#product_grid > div .ti-heart"
            , result
            , "$width X $height"
            , type.name
            , device
        )

        val compareBy = By.cssSelector("#product_grid > div .ti-control-shuffle")
        val isCompareOptionDisplayedForEachProduct =
            isElementDisplayed(compareBy)
                && waitForAll(compareBy).size == 2

        result = if (device.equals(
                "laptop",
                true
            )
        ) !isCompareOptionDisplayedForEachProduct else isCompareOptionDisplayedForEachProduct

        reportStep(
            "filters Results"
            , "Each product has Compare Option ?"
            , "#product_grid > div .ti-control-shuffle"
            , result
            , "$width X $height"
            , type.name
            , device
        )

        val cartBy = By.cssSelector("#product_grid > div .ti-shopping-cart")
        val isCartOptionDisplayedForEachProduct =
            isElementDisplayed(cartBy)
                && waitForAll(cartBy).size == 2

        result = if (device.equals(
                "laptop",
                true
            )
        ) !isCartOptionDisplayedForEachProduct else isCartOptionDisplayedForEachProduct

        reportStep(
            "filters Results"
            , "Each product has Cart Option ?"
            , "#product_grid > div .ti-shopping-cart"
            , result
            , "$width X $height"
            , type.name
            , device
        )
        reportText("TEST END HERE")
        AssertManager.softAssert.assertAll()
    }

    @Test(dataProvider = "browserConfig", groups = ["traditional"], enabled = false)
    fun productDetailsTest(width: Int, height: Int, type: BrowserType, device: String) {
        openBrowser(type)
        DriverManager.webDriver.manage().window().size = Dimension(width, height)

        // region < Filter for shoes by Black Color and open first product >
        if (!isElementDisplayed(By.id("filter_col"))) {
            waitFor(By.cssSelector(".open_filters > .ti-filter")).click()
        }
        waitFor(By.id("LABEL__containerc__104")).click()
        waitFor(By.id("filterBtn")).click()
        waitForAll(By.cssSelector("#product_grid > div"))[0].click()

        // endregion

        reportText("TEST: productDetailsTest")

        // In traditional tests, this only catches if img element is present or not.
        // It doesn't catches if image is actually shown or is blank
        val isShoeImagePresent = isElementDisplayed(By.id("shoe_img"))
        reportStep(
            "productDetailsTest"
            , "Product Image is displayed ?"
            , "shoe_img"
            , isShoeImagePresent
            , "$width X $height"
            , type.name
            , device
        )

        val isProductSKUPresent = isElementDisplayed(By.id("SMALL____84"))
        reportStep(
            "productDetailsTest"
            , "Product SKU code is displayed ?"
            , "INPUTtext____42"
            , isProductSKUPresent
            , "$width X $height"
            , type.name
            , device
        )

        //For v2, the id is different : <EM__ratingcoun__82>, Hence this will fail for v2
        val isReviewDisplayed = isElementDisplayed(By.id("EM____82"))
        reportStep(
            "productDetailsTest"
            , "Product Review text is displayed ?"
            , "EM____82"
            , isReviewDisplayed
            , "$width X $height"
            , type.name
            , device
        )

        val isSizeSetToSmall = waitFor(By.cssSelector(".current")).text == "Small (S)"
        reportStep(
            "productDetailsTest"
            , "Size dropdown set to Small (S) ?"
            , ".current"
            , isSizeSetToSmall
            , "$width X $height"
            , type.name
            , device
        )

        val newPriceSetCorrectly = waitFor(By.id("new_price")).text == "$33.00"
        reportStep(
            "productDetailsTest"
            , "New price set to $33.00 ?"
            , "new_price"
            , newPriceSetCorrectly
            , "$width X $height"
            , type.name
            , device
        )

        val isOldPriceStrikeThrough = waitFor(By.id("old_price")).isStrikeThrough()
        reportStep(
            "productDetailsTest"
            , "Old Price Striked through ?"
            , "old_price"
            , isOldPriceStrikeThrough
            , "$width X $height"
            , type.name
            , device
        )

        // This assertion doesn't catches incorrect position of Add to cart button in version 2.
        val isAddToCartButtonDisplayed = isElementDisplayed(By.id("A__btn__114"))
        reportStep(
            "productDetailsTest"
            , "Add to cart button displayed ?"
            , "old_price"
            , isAddToCartButtonDisplayed
            , "$width X $height"
            , type.name
            , device
        )

        reportText("TEST END HERE")
        AssertManager.softAssert.assertAll()
    }
}

