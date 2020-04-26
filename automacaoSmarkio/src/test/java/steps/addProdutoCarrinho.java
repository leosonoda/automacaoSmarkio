package steps;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class addProdutoCarrinho {

	private WebDriver driver;

	@Given("^estou na home page do site$")
	public void estou_na_home_page_do_site() throws Throwable {
		String exePath = "src/test/resources/chromeDriver/chromedriver.exe";

		System.setProperty("webdriver.chrome.driver", exePath);

		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		driver.get("https://www.netshoes.com.br/");

		driver.manage().window().maximize();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		wait.until(ExpectedConditions.elementToBeClickable(By.className("zipcode-content__block--text")));

	}

	@When("^pesquiso um produto na barra de busca$")
	public void pesquiso_um_produto_na_barra_de_busca() throws Throwable {

		WebElement barraBusca = driver.findElement(By.id("search-input"));

		barraBusca.sendKeys("raquete butterfly");

		barraBusca.sendKeys(Keys.ENTER);
	}

	@When("^valido que a pesquisa foi feita com sucesso$")
	public void valido_que_a_pesquisa_foi_feita_com_sucesso() throws Throwable {
		
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("search-query")));

		String resultadoBusca = driver.findElement(By.className("search-query")).getText();

		String buscaEsperada = "RESULTADOS DE BUSCA PARA \"RAQUETE BUTTERFLY\"";

		assertEquals(buscaEsperada, resultadoBusca);
	}

	@When("^clico em um produto$")
	public void clico_em_um_produto() throws Throwable {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Raquete Tênis De Mesa Butterfly A1000 Clássica')]")));
		
		WebElement botaoProduto = driver.findElement(By.xpath("//span[contains(text(),'Raquete Tênis De Mesa Butterfly A1000 Clássica')]"));

		botaoProduto.click();
		

	}

	@When("^adiciono o produto ao carrinho$")
	public void adiciono_o_produto_ao_carrinho() throws Throwable {
		
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='buy-button-now']//span[contains(text(),'Comprar')]")));

		WebElement botaoComprar = driver.findElement(By.xpath("//button[@id='buy-button-now']//span[contains(text(),'Comprar')]"));

		botaoComprar.click();
		

		

	}

	@Then("^valido que o produto foi adicionado com sucesso$")
	public void valido_que_o_produto_foi_adicionado_com_sucesso() throws Throwable {
		
		driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[@class='name']")));

		String resultadoCarrinho = driver.findElement(By.xpath("//h3[@class='name']")).getText();

		String carrinhoEsperado = "Raquete Tênis De Mesa Butterfly A1000 Clássica";

		assertEquals(carrinhoEsperado, resultadoCarrinho);

		driver.close();

	}

}
