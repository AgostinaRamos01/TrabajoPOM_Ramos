package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.BaseClass;

public class Register extends BaseClass {
    By bytxtFirstname = By.id("input-firstname");
    By bytxtLastname = By.id("input-lastname");
    By bytxtEmail = By.id("input-email");
    By bytxtTelephone = By.id("input-telephone");
    By bytxtPassword = By.id("input-password");
    By bytxtConfirmPassword = By.id("input-confirm");
    By byradio = By.xpath("//input[@value='0']");
    By bycheck = By.xpath("//input[contains(@name, 'agree')]");
    By bybtnContinue = By.xpath("//input[@value='Continue']");

    public void crearCuenta(String name, String lastname, String email, String telephone, String password, String confirmPassword, boolean radio, boolean check) {
        agregarTexto(esperaExplicita(bytxtFirstname, 10), name);
        agregarTexto(esperaExplicita(bytxtLastname, 10), lastname);
        agregarTexto(esperaExplicita(bytxtEmail, 10), email);
        agregarTexto(esperaExplicita(bytxtTelephone, 10), String.valueOf(telephone));
        agregarTexto(esperaExplicita(bytxtPassword, 10), password);
        agregarTexto(esperaExplicita(bytxtConfirmPassword, 10), confirmPassword);

        if (radio) {
            click(buscarElementoWeb(byradio));
        }
        if (check) {
            click(buscarElementoWeb(bycheck));
        }
        continuarRegistro();
    }

    private void continuarRegistro() {
        WebElement btnContinue = esperaExplicita(bybtnContinue, 10);
        Assertions.assertTrue(btnContinue.isEnabled(), "El boton continuar no esta disponible");
        click(bybtnContinue);
    }


    public Register(WebDriver driver) {
        super(driver);
    }

}



