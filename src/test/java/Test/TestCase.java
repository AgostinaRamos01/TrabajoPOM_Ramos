package Test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import pages.*;
import utils.DataDriven;
import utils.Propertiesdriven;
import java.util.ArrayList;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.lang.Boolean.parseBoolean;

public class TestCase {
    private WebDriver driver;
    private WebDriverWait wait;
    private ArrayList<String> data;

    private Home.HomePage homePage;
    private Login login;
    private Register register;
    private Phones phones;
    private Carrito carrito;

    private static String urlDriver = System.getProperty("user.dir")+"\\src\\test\\resources\\driver\\chromedriver.exe";
    private static String property = "webdriver.chrome.driver";
    private static String browser = Propertiesdriven.obtenerProperty("browser");


    @Test
    public void TC01_CrearCuentaOpenCart() {
        //Verificar que se pueda crear nuevo usuario
        data = DataDriven.getTestData("TC01_CrearCuentaOpenCart");
        homePage.irARegister();
        register.crearCuenta(data.get(1), data.get(2),data.get(3),data.get(4),data.get(5), data.get(6),Boolean.parseBoolean(data.get(7)),Boolean.parseBoolean(data.get(8)));
        String respuestaEsperada = "Continue";
        WebElement respuesta = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Continue']")));
        Assertions.assertEquals(respuestaEsperada ,respuesta.getText(), "No coincide");
    }
    @Test
    public void TC02_CrearCuentaEmailNoValido (){
        data = DataDriven.getTestData("TC02_CrearCuentaEmailNoValido");
        homePage.irARegister();
        register.crearCuenta(data.get(1), data.get(2),data.get(3),data.get(4),data.get(5), data.get(6),Boolean.parseBoolean(data.get(7)),Boolean.parseBoolean(data.get(8)));
        String respuestaEsperada = "E-Mail Address does not appear to be valid!";
        WebElement respuesta = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'text-danger')]")));
        Assertions.assertEquals(respuestaEsperada ,respuesta.getText(), "No coincide");
    }

    @Test
    public void TC03_Login(){
        data = DataDriven.getTestData("TC03_Login");
        homePage.irALogin();
        login.accederLogin(data.get(1), data.get(2));
        String urlEsperada = "https://opencart.abstracta.us/index.php?route=account/account";
        String urlObtenida = homePage.getDriver().getCurrentUrl();
        Assertions.assertEquals(urlObtenida, urlEsperada, "La URL no es la esperada.");
    }
    @Test
    public void TC04_AgregarProductoAlCarrito(){
        homePage.irAProducto();
        phones.seleccionarProducto();
        phones.agregarProducto();
        phones.esperarXSegundos(4000);
        String respuestaEsperada = "1 item(s)";
        WebElement respuesta = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("cart-total")));
        String items = respuesta.getText();
        System.out.println(items);
        Assertions.assertTrue(items.contains(respuestaEsperada));
    }
    @Test
    public void TC05_CrearCompra(){
        data = DataDriven.getTestData("TC05_CrearCompra");
        TC04_AgregarProductoAlCarrito();
        homePage.realizarCompra();
        carrito.continuarRegistro();
        carrito.finalizarCompra(data.get(1), data.get(2),data.get(3),data.get(4),data.get(5), data.get(6),data.get(7),data.get(8), data.get(9), Boolean.parseBoolean(data.get(10)),Boolean.parseBoolean(data.get(11)), Boolean.parseBoolean(data.get(12)));
        String urlEsperada = "https://opencart.abstracta.us/index.php?route=checkout/success";
        carrito.esperarXSegundos(2000);
        String urlObtenida = homePage.getDriver().getCurrentUrl();
        Assertions.assertEquals(urlEsperada, urlObtenida, "La URL no es la esperada.");
    }

    @BeforeEach
    public void preCondiciones(){
        data = new ArrayList<String>();
        homePage = new Home.HomePage(driver);
        homePage.conexionDriver(browser);
        wait = new WebDriverWait(homePage.getDriver(), 10);
        register = new Register (homePage.getDriver());
        login = new Login (homePage.getDriver());
        phones = new Phones(homePage.getDriver());
        carrito = new Carrito(homePage.getDriver());
        homePage.cargarSitio(Propertiesdriven.obtenerProperty("url"));
        homePage.maximizarBrowser();
    }
    @AfterEach
    public void posCondiciones(){
        homePage.cerrarBrowser();
    }
}


