package cl.tecnova.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;
import cl.tecnova.qa.helpers.PageWeb;



public class HomePrincipal extends PageWeb {
	
	
	/* atributos
	 *sesionPagelocator; elemento boton iniciar sesion 
	 * listaPageLocator; elemento menu usuario 
	 * cerrarPageLocator elemento boton cerrar session 
	 */
	

	private By sesionPagelocator;
	private By listaPageLocator;
	private By cerrarPageLocator;
    private By validar;


	//constructor
	public HomePrincipal(WebDriver driver, ExtentTest test, Boolean TAKE_SS, int seconds) {
		super(driver, test, TAKE_SS, seconds);


		this.sesionPagelocator = By.cssSelector("[href='/login']");
		this.listaPageLocator = By.cssSelector("[data-test-id='header-member-menu-button']");
		this.cerrarPageLocator = By.cssSelector("[data-test-id='header-member-menu-logout']");
		this.validar = By.cssSelector("[alt='Home']");



		

	}

	
	
	  public void clickSesionLogin() {
		  
		  wait.until(ExpectedConditions.visibilityOf(driver.findElement(sesionPagelocator))).click();
		
	
		
	}
	  
	  public void clickCerrarLogin() {
		  
		  wait.until(ExpectedConditions.visibilityOf(driver.findElement(listaPageLocator))).click();
		   driver.findElement(cerrarPageLocator).click();
		 
	  }
	  
	  
	  public void assertClose() {



		 	 WebElement validarAr = driver.findElement(validar);

		 	Assert.assertTrue((validarAr).isDisplayed(), "Elemento no esta presente");
			System.out.println("  el elemento  Presente es: " + (validarAr).getText()  );
	  }
	  
	  
	
	
	
}
