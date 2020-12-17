	package cl.tecnova.qa.pages;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.remote.internal.WebElementToJsonConverter;
	import org.openqa.selenium.support.FindBy;
	import org.openqa.selenium.support.PageFactory;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.testng.Assert;
	import org.testng.Assert.ThrowingRunnable;
	import com.relevantcodes.extentreports.ExtentTest;
	import cl.tecnova.qa.helpers.Helper;
	import cl.tecnova.qa.helpers.PageWeb;
	import java.util.ArrayList;
	import java.util.List;

	public class LoginPage extends PageWeb {



		private By EmailLocator;
		private By iniciarSesion;
		private By passwordslocator;
		private By loginSubmin;
		private By validarIngreso;
		private By validarLoginIncorrecto;





		public LoginPage(WebDriver driver, ExtentTest test, Boolean TAKE_SS, int seconds) {
			super(driver, test, TAKE_SS, seconds);

	

			this.EmailLocator = By.id("user");
			this.iniciarSesion = By.id("login");
			this.passwordslocator = By.id("password");
			this.loginSubmin = By.id("login-submit");
			this.validarIngreso = By.cssSelector("[data-test-id='header-info-button']");
			this.validarLoginIncorrecto = By.cssSelector(".sc-kTUwUJ.eevwkv[title='nathalyasc@gmail.com']");

		}




	public void loginMetodo(String email, String contrasena, String subDir) {
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(EmailLocator))).sendKeys(email);
		driver.findElement(iniciarSesion).click();
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(passwordslocator))).sendKeys(contrasena);
		driver.findElement(loginSubmin).click();
		Helper.waitSeconds(1);
		Helper.addEvidence(TAKE_SS, driver, test, "Ingreso de password", subDir, "Inicio de sesion");
		
		


	}



	public void asserLogin() {

		WebElement validar = driver.findElement(validarIngreso);
		Assert.assertTrue((validar).isDisplayed(), "Elemento no esta presente");
		System.out.println("  el elemento  esta  Presente  " + validar  );








	}


	public void asserLoginInvalido(String correo) {


		WebElement validarIncorrecto = driver.findElement(validarLoginIncorrecto);
		Assert.assertEquals(( validarIncorrecto).getText(), correo);
		System.out.println((validarIncorrecto).getText());

	}









	}
