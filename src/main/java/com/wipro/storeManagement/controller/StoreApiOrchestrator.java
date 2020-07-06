package com.wipro.storeManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;


@RestController
public class StoreApiOrchestrator {

@Value("${message: Default Hello}")
private String message;

@Autowired
private EurekaClient eurekaClient;

@Autowired
RestTemplateBuilder restTemplateBuilder;


@RequestMapping("/message")
public String getMessage() {
	return this.message;
}

@RequestMapping("/productDetails/{id}")
public String productDetails(@PathVariable int id){
	System.out.println("Inside productDetails");
	String productDetails;
	RestTemplate restTemplate=restTemplateBuilder.build();
	InstanceInfo instanceInfo =eurekaClient.getNextServerFromEureka("zuul-gateway", false);
	String baseUrl=instanceInfo.getHomePageUrl();
	
	System.out.println("baseurl:"+baseUrl);
	String productUrl=baseUrl+ "api/product/"+id;
	System.out.println("Product Url:"+baseUrl);
	 String priceUrl=baseUrl+ "api/productPrice/"+id;
	 System.out.println("Price Url:"+baseUrl);
	 
	 
	 String productAPIResult= restTemplate.getForObject(productUrl, String.class);

	 String pricetAPIResult=restTemplate.getForObject(priceUrl, String.class);
	 
	  productDetails=productAPIResult+ "\t" + pricetAPIResult; 
	  return productDetails;
	 
}

}


/*
	 * 1. Call getProductDetails--> call getProduct/{id} and getproductPrice and
	 * return productDetails with price 
	 * 2. Call Create Product --> call createProduct and call savePrice

*/





/*
 * class ProductDetails{ int productId; String productName; String
 * productCategory; double currentPrice;
 * 
 * public ProductDetails() { super(); // TODO Auto-generated constructor stub }
 * 
 * public int getProductId() { return productId; }
 * 
 * public void setProductId(int productId) { this.productId = productId; }
 * 
 * public String getProductName() { return productName; }
 * 
 * public void setProductName(String productName) { this.productName =
 * productName; }
 * 
 * public String getProductCategory() { return productCategory; }
 * 
 * public void setProductCategory(String productCategory) { this.productCategory
 * = productCategory; }
 * 
 * public double getCurrentPrice() { return currentPrice; }
 * 
 * public void setCurrentPrice(double currentPrice) { this.currentPrice =
 * currentPrice; }
 * 
 * @Override public String toString() { return "ProductDetails [productId=" +
 * productId + ", productName=" + productName + ", productCategory=" +
 * productCategory + ", currentPrice=" + currentPrice + "]"; } }
 */
	