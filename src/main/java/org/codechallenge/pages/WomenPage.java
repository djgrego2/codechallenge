package org.codechallenge.pages;

import org.codechallenge.utils.Helper;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Properties;

public class WomenPage {

    public WebDriver driver;
    Helper helper = new Helper();

    By WomenCategoryName = By.xpath("//span[@class='category-name'][contains(.,'Women')]");
    By WomenCollections = By.xpath("//*[@id=\"center_column\"]/ul/li[");
    By ProductsCount = By.xpath("//span[@class='heading-counter']");
    By AddToCart = By.xpath("//span[contains(.,'Add to cart')]");
    By ContinueShopping = By.xpath("(//span[contains(.,'Continue shopping')])[2]");


    public WomenPage(WebDriver driver) {
        this.driver = driver;
    }

    public void ValidateWomenPage(){
        String CategoryName = driver.findElement(WomenCategoryName).getText();
        Assert.assertEquals(CategoryName,"Women");
        System.out.println("Women Page Validate");
    }

    public int ProductTotal(){
        int TotalProduct = helper.GetTotalProduct(driver.findElement(ProductsCount).getText());
        return TotalProduct;
    }

    public void GetProducts(){
        for(int i=1; i <= ProductTotal(); i++)
        {
            WebElement linkElement = driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[" + i + "]"));
            System.out.println(linkElement.getText());
        }
    }

    public int AddWomenItemToCart(int Amount) throws InterruptedException {

        for(int i=1; i <= Amount; i++) {

            WebElement item = driver.findElement(By.xpath("//*[@id=\"center_column\"]/ul/li[" + i + "]"));
            helper.MouseOver(driver, item);
            Thread.sleep(500);
            WebElement addCartButton = driver.findElement(By.xpath("(//span[contains(.,'Add to cart')])[" + i + "]"));
            addCartButton.click();
            Thread.sleep(500);
            driver.findElement(ContinueShopping).click();
        }

        return Amount;
    }
}
