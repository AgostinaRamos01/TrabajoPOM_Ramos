package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.BaseClass;

public class Home {
    public static class HomePage extends BaseClass {
        By byLocatorAcount = By.xpath("//span[text()='My Account']");
        By byLocatorRegistrarse = By.xpath("//a[text()='Register']");
        By byLocatorLogin = By.xpath("//a[text()='Login']");
        By byLocatorProducto = By.xpath("//a[contains(text(), 'Phones & PDAs')]");
        By byLocatorCarrito = By.id("cart-total");
        By byLocatorCheckout = By.xpath("//span[text()='Checkout']");

        public HomePage(WebDriver driver) {
            super(driver);
        }
        public void irARegister (){
            click(esperaExplicita(byLocatorAcount, 10));
            click(esperaExplicita(byLocatorRegistrarse,10));
        }
        public void irALogin(){
            click(esperaExplicita(byLocatorAcount, 10));
            click(esperaExplicita(byLocatorLogin,20));
        }
        public void irAProducto(){
            click(esperaExplicita(byLocatorProducto,10));
        }
        public void realizarCompra(){
            click(esperaExplicita(byLocatorCarrito,10));
            click(esperaExplicita(byLocatorCheckout,10));
        }

    }
}
