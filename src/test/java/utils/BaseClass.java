package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.List;

public class BaseClass {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BaseClass(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    //acciones genericas wrapper de la libreria de selenium
    public void cargarSitio(String url) {

        this.driver.get(url);
    }

    public WebElement buscarElementoWeb(By localizador) {

        return this.driver.findElement(localizador);
    }

    public List<WebElement> buscarElementosWeb(By localizador) {

        return this.driver.findElements(localizador);
    }

    public void click(By localizador) {

        this.driver.findElement(localizador).click();
    }

    public void click(WebElement elemento) {

        elemento.click();
    }

    public void esperarXSegundos(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public WebElement esperaExplicita(By localizador, int segundos) {
        wait = new WebDriverWait(this.driver, segundos);
        return wait.until(ExpectedConditions.presenceOfElementLocated(localizador));
    }

    public void agregarTexto(By localizador, String texto) {

        this.driver.findElement(localizador).sendKeys(texto);
    }


    public void agregarTexto(WebElement elemento, String texto) {
        elemento.sendKeys(texto);
    }

    public void cerrarBrowser() {
        this.driver.close();
    }

    public void maximizarBrowser() {
        this.driver.manage().window().maximize();
    }

    public void seleccionarDDlPorTexto(WebElement elemento, String texto) {
        Select select = new Select(elemento);
        select.selectByVisibleText(texto);
    }

    public void irAIframeXElementoWeb(WebElement elemento) {
        this.driver.switchTo().frame(elemento);
    }

    public WebDriver conexionDriver(String browser) {
        if (browser.equalsIgnoreCase("Chrome")) {
            //WebDriverManager.chromedriver().setup();
            //this.driver = new ChromeDriver();
            String rutaDriver = System.getProperty("user.dir")+"\\src\\test\\resources\\driver\\chromedriver.exe";
            System.out.println(rutaDriver);
            System.setProperty("webdriver.chrome.driver", rutaDriver);

            this.driver = new ChromeDriver();
        }
        if (browser.equalsIgnoreCase("Firefox")) {
            WebDriverManager.firefoxdriver().setup();
            this.driver = new FirefoxDriver();

        }
        if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            this.driver = new EdgeDriver();

        }

        if (browser.equalsIgnoreCase("explorer")) {
            WebDriverManager.iedriver().setup();
            this.driver = new InternetExplorerDriver();

        }
        return this.driver;

    }
}
