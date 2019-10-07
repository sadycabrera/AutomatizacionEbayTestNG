package test;

import java.util.ArrayList;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import page.PageBusquedaEbay;
import page.Producto;

public class TestBusquedaProducto {
	
	//VARIABLES
	private PageBusquedaEbay busquedaEbay;
	//String articulo="shoes";
	private String URL="https://www.ebay.com/";
	private List<Producto> listaProducto;
	private int cantidadProductos;
	//BUSCADOR DONDE SE INGRESA EL NOMBRE DEL PRODUCTO
	private By buscador=By.id("gh-ac");
	//BOTÓN BUSCADOR DONDE SE DA CLICK
	private By botonBuscador=By.id("gh-btn");
	

	
	
	@BeforeClass
	public void enter_to_Ebay_usando_la_URL() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    System.out.println("1) Enter to Ebay usando la URL:"+URL);
	    System.out.println("=================================================================");
		busquedaEbay= new PageBusquedaEbay();
		busquedaEbay.getDriver().manage().window().maximize();
		busquedaEbay.getDriver().get(URL);

	}

	@Test(priority=1)
	@Parameters(value={"articulo"})
	public void search_for(String articulo) throws Throwable {
		Thread.sleep(3000);
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("2) Search for :"+articulo);
		System.out.println("=================================================================");
		//BUSCA EL BUSCADOR 
		busquedaEbay.getBuscadorProducto(buscador);
		//ESCRIBE SOBRE EL BUSCADOR Y DA CLICK EL BOTÓN BUSCADOR
		busquedaEbay.buscarProducto(articulo, buscador, botonBuscador);
	}

	@Test(priority=2)
	@Parameters(value="brand")
	public void select_brand(String brand) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("3) Select brand: ");
		System.out.println("=================================================================");
		//NIKE=5; PUMA=6, ETC		
		By marca=busquedaEbay.getWebElementoMarca(brand);
		busquedaEbay.getBuscadorProducto(marca).click();

	}

	@Test(priority=3)
	@Parameters(value="size")
	public void select_size(String size) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		Thread.sleep(3000);
		System.out.println("4) Select size: ");
	    System.out.println("=================================================================");
	    //TALLA 10: 5
	    By talla=busquedaEbay.getWebElementoTalla(size);	    
	    busquedaEbay.getBuscadorProducto(talla).click();//SE LA PASA LA TALLA
	}

	@Test(priority=4)
	public void print_the_number_of_results() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("5) Print the number of results:");
	    System.out.println("=================================================================");
		By catidadResultados= busquedaEbay.getWebElementoResultado();
		Thread.sleep(3000);
		WebElement cantidad=busquedaEbay.getBuscadorProducto(catidadResultados);
		busquedaEbay.mostrarResultadoBusqueda(cantidad.getText());	

	}

	@Test(priority=5)
	
	@Parameters(value="tipoOrdenamiento")
	public void order_by_price(String tipoOrdenamiento) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		
		System.out.println("6) Order by price: ");
	    System.out.println("=================================================================");
		//6- Ordenar por precio ascendente	
		By ordenar=busquedaEbay.getWebElementoTipoOrdenamiento();
		//By tipoOrdenar=By.xpath();
		busquedaEbay.getBuscadorProducto(ordenar).click();
		busquedaEbay.getBuscadorProducto(ordenar).click();
	//EL MAS BAJO PRIMERO=4, EL MAS ALTO PRIMERO=5
			By tipoOrden=busquedaEbay.getWebElementoTipoOrdenElegido(tipoOrdenamiento);
					
			//SELECCIONAR EL TIPO DE ORDEN
			busquedaEbay.getBuscadorProducto(tipoOrden).click();

	}

	@Test(priority=6)
	@Parameters(value="cantidadProductos")
	public void assert_the_order_taking_the_first_results(int cantidadProductos) throws Throwable {
		this.cantidadProductos=cantidadProductos;
	    // Write code here that turns the phrase above into concrete actions
		Thread.sleep(3000);
		System.out.println("7) Assert the order taking the first 5 results");
	    System.out.println("=================================================================");
		
	    //ENLISTAR LOS 5 ELEMENTOS O MÁS
	    listaProducto=busquedaEbay.enlistarProducto(cantidadProductos);
	    
	    //VALIDAR LOS 5 PRIMEROS RESULTADOS
		Assert.assertTrue((listaProducto.get(0).getPrecio()<=listaProducto.get(1).getPrecio())&(listaProducto.get(1).getPrecio()<=listaProducto.get(2).getPrecio())&(listaProducto.get(2).getPrecio()<=listaProducto.get(3).getPrecio())&(listaProducto.get(3).getPrecio()<=listaProducto.get(4).getPrecio()));
		

	}

	@Test(priority=7)
	@Parameters(value="cantidadImprimir")
	public void the_first_products_with_their_prices_and_print_them_in_console(int cantidadImprimir) throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("8) the first 5 products with their prices and print them in console");
	    System.out.println("=================================================================");
		//8- Tome los primeros 5 productos con sus precios e imprímalos en la consola.
	    busquedaEbay.imprimirDescendentementePrecio(cantidadImprimir,listaProducto);

	}

	@Test(priority=8)
	public void order_and_print_the_products_by_name_ascendant() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("9) Order and print the products by name (ascendant)");
	    System.out.println("=================================================================");
		//9- Ordenar e imprimir los productos por nombre (ascendente)

	    busquedaEbay.imprimirAscendentementePrecio(cantidadProductos, listaProducto);
	}

	@Test(priority=9)
	public void order_and_print_the_products_by_price_in_descendant_mode() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("10) Order and print the products by price in descendant mode");
	    System.out.println("=================================================================");
		//10- Ordene e imprima los productos por precio en modo descendiente

	    busquedaEbay.imprimirDescendentementePrecio(cantidadProductos,listaProducto);
	}

		
	
	@AfterClass
	public void close()
	{
		busquedaEbay.getDriver().manage().deleteAllCookies();
		busquedaEbay.getDriver().quit();
	
	}

}
