package cl.tecnova.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentTest;

import cl.tecnova.qa.helpers.Helper;
import cl.tecnova.qa.helpers.PageWeb;
import org.testng.thread.IThreadWorkerFactory;

public class TarjetaPage  extends PageWeb {
	


	private By addTarjetaLocator;
	private By nombreEtiqueta;
	private By botonTarjetaLocator;
	private By actTarjetaLocator;
	private By descTarjetaLocator;
	private By etiTarjetaLocator;
	private By colorTarjetaLocator;
	private By closetColorLocator;
	private By vencTarjetaLocator;
	private By fecTarjetaLocator;
	private By saveFecTarLocator;
	private By adjuntoPagLocator;
	private By archPagLocator;
	private By UploaAdjLocator;
	private By ComPageLocator;
	private By botonUpdLocator;
	private By closPageLocator;
	private By SourcePageLocator;
	private By targetPageLocator;
	private By validarTarjeta;
	private By validarUpdate;




	 
	 
	public TarjetaPage(WebDriver driver, ExtentTest test, Boolean TAKE_SS, int seconds) {
		super(driver, test, TAKE_SS, seconds);
		// TODO Auto-generated constructor stub
		
		this.addTarjetaLocator = By.xpath("//*[@id=\"board\"]/div[1]/div/div[3]/a");
		this.nombreEtiqueta = By.cssSelector("textarea.list-card-composer-textarea");
		this.botonTarjetaLocator = By.xpath("//*[@id=\"board\"]/div[1]/div/div[2]/div/div[2]/div[1]/input");
		this.actTarjetaLocator = By.xpath("//*[@id=\"board\"]/div[1]/div/div[2]/a");
		this.descTarjetaLocator = By.xpath("//*[@id=\"chrome-container\"]//textarea[@class=\"field field-autosave js-description-draft description card-description\"]");
		this.etiTarjetaLocator = By.cssSelector("[title=\"Etiquetas\"]");
		this.colorTarjetaLocator = By.cssSelector("[data-color=\"green\"]");
		this.closetColorLocator = 	By.cssSelector(".pop-over-header-close-btn");
		this.vencTarjetaLocator = By.cssSelector("[title=\"Vencimiento\"]");
		this.fecTarjetaLocator 	 = By.cssSelector("input[tabindex='101']");
		this.saveFecTarLocator  = By.cssSelector("[tabindex='103']");
		this.adjuntoPagLocator  = By.cssSelector(".button-link.js-attach");
		this.archPagLocator  = By.id ("addLink");
		this.UploaAdjLocator  = By.cssSelector(".js-add-attachment-url");
		this.ComPageLocator = By.xpath("//textarea[@placeholder='Escriba un comentario...']");
		this.botonUpdLocator = By.xpath("//input[@value='Guardar']");
		this.closPageLocator = By.cssSelector(".icon-md.icon-close");
		this.SourcePageLocator = By.cssSelector(".list-card.js-member-droppable");
		this.targetPageLocator = By.xpath("//*[@id=\"board\"]/div[2]/div/div[3]/a/span[2]");
		this.validarUpdate = By.cssSelector("[title='Adjuntos']");
		this.validarTarjeta = By.cssSelector("[data-test-id='header-member-menu-button']");



















	}
	
 
	/* Metodo para agregar la tarjeta */
	
	public void agregarTarjeta(String titulo, String subDir ) {
		
	wait.until(ExpectedConditions.visibilityOf(driver.findElement(addTarjetaLocator)));
	driver.findElement(addTarjetaLocator).click();
	driver.findElement(nombreEtiqueta).sendKeys(titulo);
	Helper.addEvidence(TAKE_SS, driver, test, "Agregar Tarjeta", subDir, "agregarTarjeta_01");
	driver.findElement(botonTarjetaLocator).click();
	
	
	
	}

	
	
	
	public void actualizarTarjeta(String subDir) {

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(actTarjetaLocator))).click();

		WebElement descripcion = driver.findElement(descTarjetaLocator);
		if (descripcion.isDisplayed()) {
			wait.until(ExpectedConditions.visibilityOf(descripcion)).click();
			descripcion.sendKeys("Prueba de automatizacion  que me tiene verde  ");

			System.out.println(descripcion.getText());

		}else
		{

			System.out.println(" Elemento no es visible");
		}



		driver.findElement(etiTarjetaLocator).click();
		driver.findElement(colorTarjetaLocator).click();
		driver.findElement(closetColorLocator).click();
		driver.findElement(vencTarjetaLocator).click();
		driver.findElement(fecTarjetaLocator).clear();
		driver.findElement(fecTarjetaLocator).sendKeys("30/12/2020");
		driver.findElement(saveFecTarLocator).click();
		wait.until(ExpectedConditions.elementToBeClickable(adjuntoPagLocator)).click();
		driver.findElement(archPagLocator).sendKeys("C:/Users/Usuario/Documents/Imagen1.jpg");
		driver.findElement(UploaAdjLocator).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(ComPageLocator)));
		driver.findElement(ComPageLocator).sendKeys("Pruebas automatizadas prueba final ");
		Helper.addEvidence(TAKE_SS, driver, test, "Actualizar  Tarjeta", subDir, "actualizarTarjeta_01");
		driver.findElement(botonUpdLocator).click();
		driver.findElement(closPageLocator).click();



	
	
		}

	
		/* Metodo para mover la tarjeta de la posicion: 
		 *  con el metodo Actions */
	
	public void movertarjeta(String subDir) {
		
		 WebElement source = driver.findElement(SourcePageLocator);
		 WebElement target = driver.findElement(targetPageLocator);
		(new Actions(driver)).dragAndDrop(source, target).perform();
		Helper.addEvidence(TAKE_SS, driver, test, "Mover Tarjeta", subDir, "movertarjeta_01");
		
	}
	
	public void assertUpdate() {
		
		
	   	WebElement validar = driver.findElement(validarUpdate);
		Assert.assertTrue(validar.isDisplayed(), "Elemento no esta presente");
		System.out.println("  el elemento  Presente es: " + validar.getText()  );
		
		
	}

	public void validarTarj(){



		WebElement validarT = driver.findElement(validarTarjeta);
		Assert.assertTrue(validarT.isDisplayed(), "Elemento no esta presente");
		System.out.println("  el elemento  Presente es: " + validarT.getText()  );
	}


	

	
	
}
