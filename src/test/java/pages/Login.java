package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.BaseClass;

public class Login extends BaseClass {
    By bytxtEmailAddress = By.id("input-email");
    By bytxtPassword = By.id("input-password");
    By bybtnLogin = By.xpath("//input[contains(@class, 'btn btn-primary')]");

    public void accederLogin(String emailAddress, String password) {
        agregarTexto(esperaExplicita(bytxtEmailAddress,10), emailAddress);
        agregarTexto(esperaExplicita(bytxtPassword,10), password);
        continuarLogin();
    }
    private void continuarLogin() {
        WebElement btnLogin = esperaExplicita(bybtnLogin, 10);
        Assertions.assertTrue(btnLogin.isEnabled(), "El boton login no esta disponible");
        click(bybtnLogin);
    }

    public Login(WebDriver driver) {
        super(driver);
    }
}
