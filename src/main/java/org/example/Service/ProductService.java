package org.example.Service;

import org.example.Main;
import org.example.Model.ProductInfo;
import org.example.repository.ProductRepository;
import org.example.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
//import org.example.DAO.ProductDAO;
//import org.example.DAO.SellerDAO;
import org.example.Exception.ProductInfoException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/** Class ProductService handles the application functionality for Products
 * Methods here have functionality for the following actions: add, view, search, delete, exit */
@Service
public class ProductService {

    /** Dependency Injection */
    SellerService sellerService;
   List<ProductInfo> ProductinfoList;
   ProductRepository productRepository;
   SellerRepository sellerRepository;


   @Autowired
    public ProductService(
            SellerService sellerService, ProductRepository productRepository, SellerRepository sellerRepository){
        this.sellerService = sellerService;
        //this.ProductinfoList = new ArrayList<>();
        this.productRepository = productRepository;
        this.sellerRepository = sellerRepository;
    }

    //public List<ProductInfo> getProductinfo() {
    //    return ProductinfoList;
    //}

    /** This method handles the 'GET' action and displays all ProductInfo objects from the Productinfo list */
    public List<ProductInfo> getAllProducts(){
        Main.log.info("List of all Products in the collection: ");
        return productRepository.findAll();
    }

    /** This method handles the Product addition (POST) and throws the ProductInfoException if
     * validations are not passed  */
    public void addProduct(ProductInfo p) throws ProductInfoException {

        if (inputValuesValidated(p)) {
            this.productRepository.save(p);
            p.getId();                                  // get the newly created id
            Main.log.info("New Product added: " + p);
        }
        throw new ProductInfoException("Product is not added");
    }

    /** Method to validate all input values based on requirements */
    public boolean inputValuesValidated(ProductInfo p) throws ProductInfoException {
        if (p.getName().isBlank() || p.getSeller_name().getSellername().isBlank()){
                Main.log.warn("ADD: Product or Seller name are missing: "
                        + p.getName() + "| " + p.getSeller_name());
                throw new ProductInfoException("Product name or Seller name cannot be blank");
            } else if (p.getPrice() <= 0) {
                Main.log.warn("ADD: Price is <= 0: " + p.getPrice());
                throw new ProductInfoException("Product price should be greater than 0");
            } else if (productRepository.getProductByName(p.getName()) != null){
                Main.log.warn("ADD: Product name is duplicate: " + p.getName());
                throw new ProductInfoException("Product name already exists");
           } /**else if (sellertDAO.getSellerByName(p.getSellername()) == null){
                Main.log.warn("ADD: Non-existent seller name: " + p.getSellername());
                throw new ProductInfoException("Seller does not exist in the Seller set");
        } */else {
            return true;
        }
    }

//    /** This method handles the 'GET' by product_id. The product-id is obtained from a corresponding post request */
//    public ProductInfo getProductById(Integer id) throws ProductInfoException {
//        ProductInfo p = productDAO.getProductById(id);
//        if (p == null) {
//            throw new ProductInfoException("Product ID is not found");
//        } else {
//            return p;
//        }
//    }
//
//
//    /** This method handles the 'GET' by partial product_name.  */
//    public List<ProductInfo> getProductByPartialName(String name) throws ProductInfoException {
//        List<ProductInfo> productList = productDAO.getProductByPartialName(name);
//        if (productList.isEmpty()) {
//            throw new ProductInfoException("No products found");
//        } else {
//            return productList;
//        }
//    }
//
//      /** This method handles the 'GET' action and displays all ProductInfo objects from the Productinfo list */
//    public List<ProductInfo> getAllProducts(){
//        List<ProductInfo> productList = productDAO.getAllProducts();
//        Main.log.info("VIEW: List of all Products in the collection: "+ productList);
//        return productList;
//    }
//
//
//    /** This method handles the 'DELETE' action by product-id. The product-id is obtained from a corresponding post request */
//    public void deleteProductById(Integer id){
//        ProductInfo p = productDAO.getProductById(id);
//        if (p != null){
//            if (productDAO.deleteProductById(id)) {
//                Main.log.info("DELETE: Successful delete for Product: " + p);
//            }
//        }
//    }
//
//    /** This method handles the 'PUT' action by product-id. The product-id is obtained from a corresponding post request  */
//    public ProductInfo updateProductById(ProductInfo p) throws ProductInfoException {
//        if ((productDAO.getProductById(p.getId()) != null) && inputValuesValidated(p)) {
//            productDAO.updateProductById(p);
//            Main.log.info("UPDATE: product is modified: " + p);
//            return p;
//        } else {
//            throw new ProductInfoException("Product is not updated: " + p);
//        }
//        //return null;
//    }


}

