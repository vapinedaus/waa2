/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbean;

import javax.inject.Named;
import javax.enterprise.context.Dependent;
import model.Product;
import database.Database;
import java.util.List;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author VictorPineda
 */
@Named(value = "productMB")
@RequestScoped
public class ProductMB {

    /**
     * Creates a new instance of ProductMB
     */
    public ProductMB() {
    }
    
    private Product product = new Product();

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
    
    public String add()    
    {
        product.setId(Database.products.size()+1);
        Database.products.add(product);
        
        return "view.jsf?faces-redirect=true";
    
    }
    
    public String edit()    
    {
        for (Product prod :Database.products){
	         if (prod.getId()==product.getId()) 
                 {
                 prod.setName(product.getName());
                 prod.setPrice(product.getPrice());
                 prod.setDescription(product.getDescription());
                 }
		}
        
        return "view.jsf?faces-redirect=true";
    
    }
    
     public String gotoEdit(Product product)    
    {
        setProduct(product);
        return "edit.jsf";
    
    }
    
    public List<Product> getProductList()
    {
      return Database.products;
    }
    
       public String removeAction(Product product) {
	    Database.products.remove(product);
		return null;
	}
    
    
    
}
