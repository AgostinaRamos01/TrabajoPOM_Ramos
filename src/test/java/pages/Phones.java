package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.BaseClass;

   public class Phones extends BaseClass{
        By byIphone = By.xpath("//a[text()='iPhone']");
        By bybtnAddToCart = By.id("button-cart");

       public void seleccionarProducto() {
        click(buscarElementoWeb(byIphone));
    }

    public void agregarProducto(){
        WebElement btnAddToCart = esperaExplicita(bybtnAddToCart,10);
        Assertions.assertTrue(btnAddToCart.isEnabled(),"El boton add to cart no esta disponible");
        click(bybtnAddToCart);
    }
       public Phones(WebDriver driver) {
           super(driver);
       }

    }

