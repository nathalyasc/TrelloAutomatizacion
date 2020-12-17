package cl.tecnova.qa.tests;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import cl.tecnova.qa.helpers.Helper;
import cl.tecnova.qa.pages.HomePrincipal;
import cl.tecnova.qa.pages.LoginPage;
import cl.tecnova.qa.pages.TableroPage;
import cl.tecnova.qa.pages.TarjetaPage;


public class TestTrello {
    private WebDriver driver;
    private ExtentReports extent;
    private ExtentTest test;
    private static String SUBDIR = "AmbienteTrello\\";
    private static Boolean TAKE_SS = true;
    private static int WAITING = 10;

    @BeforeSuite
    public void configExtentReports() {
        // ExtentReports config
        this.extent = new ExtentReports("ExtentReports/PruebaTrello.html", true);
        this.extent.addSystemInfo("Host Name", "Tecnova Soluciones Informaticas SA");
        this.extent.addSystemInfo("Enviroment", "Automation Testing");
        this.extent.addSystemInfo("User Name", "Nathalya Suarez Coronado");
        //llamada a objeto de configuracion de Extent report
        File conf = new File("src/test/resources/extentReports/" + "extent-config.xml");
        this.extent.loadConfig(conf);
    }

    @BeforeMethod
    @Parameters({"URL_PRINCIPAL"})
    public void configSelenium(String URL_PRINCIPAL) {
        // Selenium config
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("Empresa", "Tecnova");
        System.setProperty("webdriver.chrome.driver", "DRIVERS/chromedriver.exe");
        Helper.robotMoveMouse(2000, 2000);
        driver = new ChromeDriver();
        //Implicit Waits No usar si se estan usando explicit wait
        driver.manage().timeouts().implicitlyWait(WAITING, TimeUnit.SECONDS);
        //Maximizar Ventana
        driver.manage().window().maximize();
        //Navegar a url principal
        driver.navigate().to(URL_PRINCIPAL);

    }
    
  


    @Test
    @Parameters({"email", "contrasena"})
    public void loginUsuario(String email, String contrasena)  {
        String subDir = SUBDIR + Thread.currentThread().getStackTrace()[1].getMethodName();
        test = extent.startTest("Prueba inicio de sesion ", "Probando el inicio de sesion correcto");
        test.log(LogStatus.INFO, "Prueba inicial simulando el inicio de sesion ");
        HomePrincipal home = new HomePrincipal(driver, test, TAKE_SS, 30);
        home.clickSesionLogin();
        LoginPage inicio = new LoginPage(driver, test, TAKE_SS, 45);
        inicio.loginMetodo(email, contrasena, subDir );
        inicio.asserLogin();
        home.clickCerrarLogin();

    }
    
    @Test
    @Parameters({"email"})
    public void loginIncorrecto(String email)  {
        String subDir = SUBDIR + Thread.currentThread().getStackTrace()[1].getMethodName();
        test = extent.startTest("Prueba de login incorrecto ", "Probando el login incorrecto");
        test.log(LogStatus.INFO, "Prueba Login incorrecto ");
        HomePrincipal home = new HomePrincipal(driver, test, TAKE_SS, 20);
        home.clickSesionLogin();
        LoginPage inicio = new LoginPage(driver, test, TAKE_SS, 20);
        inicio.loginMetodo(email, "041413524", subDir);
        inicio.asserLoginInvalido("nathalyasc@gmail.com");

    }
    
    
    @Test

    @Parameters({"email", "contrasena"})
    public void crearTablero(String email, String contrasena) throws InterruptedException {
        String subDir = SUBDIR + Thread.currentThread().getStackTrace()[1].getMethodName();
        test = extent.startTest("Prueba crear tablero ", "Probando crear tablero nuevo");
        test.log(LogStatus.INFO, "Prueba crear tabler ");
        HomePrincipal home = new HomePrincipal(driver, test, TAKE_SS, 40);
        TableroPage tablero = new TableroPage(driver, test, TAKE_SS, 50);
        home.clickSesionLogin();
        LoginPage  inicio = new LoginPage(driver, test, TAKE_SS, 50);
        inicio.loginMetodo(email, contrasena, subDir );
        tablero.newTablero("Nathalya Suarez", subDir);
        tablero.validarTable();
        home.clickCerrarLogin();



    }
    
    @Test

    @Parameters({"email", "contrasena"})
    public void addTarjeta(String email,String contrasena)  {
        String subDir = SUBDIR + Thread.currentThread().getStackTrace()[1].getMethodName();
        test = extent.startTest("Agregar tarjeta", "Probando Agregar tarjeta");
        test.log(LogStatus.INFO, "Prueba agregar tarjeta ");
        HomePrincipal home = new HomePrincipal(driver, test, TAKE_SS, 40);
        TableroPage tablero = new TableroPage(driver, test, TAKE_SS, 30);
        TarjetaPage tarjeta = new TarjetaPage(driver, test, TAKE_SS, 30);
        home.clickSesionLogin();
        LoginPage  inicio = new LoginPage(driver, test, TAKE_SS, 30);
        inicio.loginMetodo(email, contrasena, subDir );
        tablero.viTablero();
        tarjeta.agregarTarjeta("Prueba Automatizada", subDir);
        tarjeta.validarTarj();
        home.clickCerrarLogin();

        


    }
    
    
    @Test

    @Parameters({"email", "contrasena"})
    public void updateTarjeta(String email, String contrasena)  {
        String subDir = SUBDIR + Thread.currentThread().getStackTrace()[1].getMethodName();
        test = extent.startTest("Prueba update tarjeta ", "Probando update tarjeta");
        test.log(LogStatus.INFO, "Prueba actualizando tarjeta ");
        HomePrincipal home = new HomePrincipal(driver, test, TAKE_SS, 30);
        TableroPage tablero = new  TableroPage(driver, test, TAKE_SS, 30);
        TarjetaPage tarjeta = new TarjetaPage(driver, test, TAKE_SS, 40);
        home.clickSesionLogin();
        LoginPage inicio = new LoginPage(driver, test, TAKE_SS, 30);
        inicio.loginMetodo(email, contrasena, subDir );
        tablero.viTablero();
        tarjeta.actualizarTarjeta(subDir);
        tarjeta.assertUpdate();
        home.clickCerrarLogin();


    }
    
    @Test

    @Parameters({"email", "contrasena"})
    public void moverTarjeta(String email, String contrasena)  {
        String subDir = SUBDIR + Thread.currentThread().getStackTrace()[1].getMethodName();
        test =  extent.startTest("Prueba mover tarjeta ", "mover tarjeta");
        test.log(LogStatus.INFO, "mover tarjeta ");
        HomePrincipal home = new HomePrincipal(driver, test, TAKE_SS, 30);
        LoginPage inicio = new LoginPage(driver, test, TAKE_SS, 30);
        TableroPage tablero = new TableroPage(driver, test, TAKE_SS, 30);
        TarjetaPage tarjeta = new TarjetaPage(driver, test, TAKE_SS, 30);
        home.clickSesionLogin();
        inicio.loginMetodo(email, contrasena, subDir );
        tablero.viTablero();
        tarjeta.movertarjeta(subDir);
        home.clickCerrarLogin();
        home.assertClose();


    }


    @Test

    @Parameters({"email", "contrasena"})
    public void elimTablero(String email, String contrasena)  {
        String subDir = SUBDIR + Thread.currentThread().getStackTrace()[1].getMethodName();
        test =  extent.startTest("Prueba Eliminar tablero ", "Eliminar Tablero");
        test.log(LogStatus.INFO, "Eliminar tablero definitivo ");
        HomePrincipal home = new HomePrincipal(driver, test, TAKE_SS, 30);
        LoginPage inicio = new LoginPage(driver, test, TAKE_SS, 30);
        TableroPage tablero = new TableroPage(driver, test, TAKE_SS, 30);
        home.clickSesionLogin();
        inicio.loginMetodo(email, contrasena, subDir );
        tablero.viTablero();
        tablero.deletTablero(subDir);
        home.clickCerrarLogin();
        home.assertClose();


    }


    @AfterMethod
    public void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(LogStatus.FAIL, "Test failed.- <br>" + result.getThrowable());
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(LogStatus.SKIP, "Test skipped.- <br>" + result.getThrowable());
        } else {
            test.log(LogStatus.PASS, "Test passed.-");
        }
      // driver.close();
        extent.endTest(test);
    }

    @AfterSuite
    public void closeExtentReports() {
        // Escribimos los datos al reporte.
        extent.flush();
    }
}
