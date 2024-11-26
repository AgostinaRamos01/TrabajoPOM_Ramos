package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import utils.BaseClass;

import java.util.List;

public class Carrito extends BaseClass {
    By bybtnContinue = By.id("button-account");

    By bytxtFirtsName = By.id("input-payment-firstname");
    By bytxtLastName = By.id("input-payment-lastname");
    By bytxtEmail = By.id("input-payment-email");
    By bytxtTelephone = By.id("input-payment-telephone");
    By bytxtAddress = By.id("input-payment-address-1");
    By bytxtCity = By.id("input-payment-city");
    By bytxtPostCode = By.id("input-payment-postcode");
    By bytxtPassword = By.id("input-payment-password");
    By bytxtconfirmPassword = By.id("input-payment-confirm");
    By byCountry = By.id("input-payment-country");
    By byRegion = By.id("input-payment-zone");
    By bycheck = By.xpath("//input[@name='agree']");
    By bybtnSiguiente = By.id("button-register");
    By bybtnShip = By.id("button-shipping-address");
    By bybtnMethod= By.id("button-shipping-method");
    By bycheckTC = By.xpath("//input[@type='checkbox' and @name='agree' and @value='1']");
    By bybtnPayment = By.id("button-payment-method");
    By bybtnConfirmOrder = By.id("button-confirm");


    public void continuarRegistro() {
        esperarXSegundos(2000);
        WebElement btnContinue = esperaExplicita(bybtnContinue, 10);
        Assertions.assertTrue(btnContinue.isDisplayed());
        click(bybtnContinue);
    }

    public void finalizarCompra (String name, String lastname, String email, String telephone, String address, String city, String postCode, String password, String confirmPassword, boolean country, boolean region, boolean check){
        agregarTexto(esperaExplicita(bytxtFirtsName,10), name);
        agregarTexto(esperaExplicita(bytxtLastName,10), lastname);
        agregarTexto(esperaExplicita(bytxtEmail,10), email);
        agregarTexto(esperaExplicita(bytxtTelephone,10), telephone);
        agregarTexto(esperaExplicita(bytxtAddress,10), address);
        agregarTexto(esperaExplicita(bytxtCity,10), city);
        agregarTexto(esperaExplicita(bytxtPostCode,10), postCode);
        agregarTexto(esperaExplicita(bytxtPassword,10), password);
        agregarTexto(esperaExplicita(bytxtconfirmPassword,10), confirmPassword);

        if(country){
            List<WebElement> options = driver.findElements(byCountry);
            options.get(0).click();
        }
        if(region){
          WebElement dropdown = driver.findElement(byRegion);
          //instanciar select para poder interactuar con elementos select en el html
          Select select = new Select(dropdown);
          //busca la opcion que coincide
          select.selectByValue("3513");
        }
        if(check){
            click(buscarElementoWeb(bycheck));

        confirmarRegistro();
        }
    }

    private void confirmarRegistro() {
        WebElement btnSiguiente = esperaExplicita(bybtnSiguiente,10);
        Assertions.assertTrue(btnSiguiente.isEnabled());
        click(bybtnSiguiente);

        esperarXSegundos(2000);
        WebElement btnShip = esperaExplicita(bybtnShip,10);
        Assertions.assertTrue(btnShip.isDisplayed());
        click(bybtnShip);

        esperarXSegundos(2000);
        WebElement btnMethod = esperaExplicita(bybtnMethod,10);
        Assertions.assertTrue(btnMethod.isDisplayed());
        click(bybtnMethod);

        esperarXSegundos(2000);
        click(bycheckTC);
        WebElement btnPayment = esperaExplicita(bybtnPayment,10);
        Assertions.assertTrue(btnPayment.isDisplayed());
        click(bybtnPayment);

        esperarXSegundos(2000);
        WebElement btnConfirmOrder = esperaExplicita(bybtnConfirmOrder,10);
        Assertions.assertTrue(btnConfirmOrder.isDisplayed());
        click(bybtnConfirmOrder);

    }
    public Carrito(WebDriver driver) {
        super(driver);
    }
}

