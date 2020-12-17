package cl.tecnova.qa.pages;

import java.util.List;

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

public class TableroPage extends PageWeb {


	// atributos


	private By Tablero;
	private By newTablero;
	private By nombreTablero;
	private By botonCrearTablero;
	private By tableroExist;
	private By boardTablero;
	private By mostrarMenu;
	private By botonmas;
	private By cerrarTablero;
	private By closetTablero;
	private By confirCerrarTableroLocator;
	private By eliminarTablero;
	private By celiminarTablero;
	private By closeVent;
	private By home;
	private By validarTablero;




	//constructor

	public TableroPage(WebDriver driver, ExtentTest test, Boolean TAKE_SS, int seconds) {
		super(driver, test, TAKE_SS, seconds);
		// TODO Auto-generated constructor stub



		this.Tablero = By.cssSelector("[href='/terras12']");
		this.newTablero = By.cssSelector("[data-test-id='create-board-tile']");
		this.nombreTablero = By.cssSelector("[data-test-id='create-board-title-input']");
		this.botonCrearTablero = By.cssSelector("[data-test-id='create-board-submit-button']");
        this.tableroExist = By.cssSelector(".mod-no-sidebar a[href='/terras12']");
        this.boardTablero = By.xpath("//*[@id=\"content\"]/div[3]/div[3]/div[2]/div/div/div/div[2]/div[2]/ul/li[1]/a");
		this.mostrarMenu = By.cssSelector(".board-header-btn.mod-show-menu");
		this.botonmas = By.xpath("//a[contains(.,'Más')]");
		this.cerrarTablero = By.cssSelector(".board-menu-navigation-item-link.js-close-board");
		this.closetTablero = By.xpath("//a[contains(.,'Cerrar tablero...')]");
		this.confirCerrarTableroLocator = By.cssSelector(".js-confirm");
		this.eliminarTablero = By.cssSelector(".quiet.js-delete");
		this.celiminarTablero = By.cssSelector(".js-confirm.full");
		this.closeVent = By.cssSelector(".board-menu-header-close-button.icon-lg");
		this.home = By.cssSelector("[data-test-id='header-home-button']");
		this.validarTablero = By.cssSelector("[data-test-id='header-boards-menu-button']");





	}

	//metodos

	public void newTablero (String name, String subDir) {
		

		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(Tablero))).click();
		driver.findElement(newTablero).click();
		driver.findElement(nombreTablero).sendKeys(name);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(botonCrearTablero))).click();

		Helper.addEvidence(TAKE_SS, driver, test, "Crear Tablero", subDir, "newTablero_01");

		
		
		


	}

	
	
		
		
	public void viTablero () {
		wait.until(ExpectedConditions.elementToBeClickable(tableroExist));
		driver.findElement(tableroExist).click();
		Actions action = new Actions(driver);
		action.moveToElement(wait.until(ExpectedConditions.visibilityOf(driver.findElement(boardTablero)))).click().perform();
		
		

	}
	
	
	public void deletTablero (String subDir) {
		
		WebElement menu = driver.findElement(mostrarMenu);
		WebElement cerrar = driver.findElement(closeVent);
		
		
			if (menu.isDisplayed()) {
				wait.until(ExpectedConditions.elementToBeClickable(menu));
				menu.click();
				System.out.println (mostrarMenu + "menu");
			}else if (cerrar.isDisplayed())  {
				cerrar.click();
				wait.until(ExpectedConditions.elementToBeClickable(menu));
				menu.click();
				System.out.println ("se cierra la ventana ");
				
			}
		
		
		
		driver.findElement(botonmas).click();
		driver.findElement(closetTablero).click();
		driver.findElement(confirCerrarTableroLocator).click();
		driver.findElement(eliminarTablero).click();
		driver.findElement(celiminarTablero).click();
		Helper.addEvidence(TAKE_SS, driver, test, "deletTablero", subDir, "deletTablero_01");
		driver.findElement(home).click();
		
	

	}

	public void  validarTable (){


		WebElement validar = driver.findElement(validarTablero);
		Assert.assertTrue(validar.isDisplayed(), "Elemento no esta presente");
		System.out.println("  el elemento  Presente es: " + validar.getText()  );

	}



}