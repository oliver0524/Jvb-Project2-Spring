package org.example.Service;

import org.example.Main;
import org.example.Model.ProductInfo;
import org.example.Model.Seller;
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
    ProductRepository productRepository;
    SellerRepository sellerRepository;


    @Autowired
    public ProductService(
            SellerService sellerService, ProductRepository productRepository, SellerRepository sellerRepository){
        this.sellerService = sellerService;
        this.productRepository = productRepository;
        this.sellerRepository = sellerRepository;
    }


    /** This method handles the 'GET' action and displays all ProductInfo objects from the Productinfo list */
    public List<ProductInfo> getAllProducts(){
        Main.log.info("List of all Products in the collection: ");
        return productRepository.findAll();
    }

    /** This method handles the Product addition (POST) and throws the ProductInfoException if
     * validations are not passed  */
    public void addProduct(ProductInfo p) throws ProductInfoException {

        if (inputValuesValidated(p)) {
            // Add product either with an existing or a new seller
                this.productRepository.save(p);
                Main.log.info("New Product added: " + p);

            } else {
                throw new ProductInfoException("Product is not added");
            }
        }

    /** Method to validate all input values based on requirements */
    public boolean inputValuesValidated(ProductInfo p) throws ProductInfoException {
        // Check if the ProductInfo object itself is null
        if (p == null) {
            Main.log.warn("ADD: ProductInfo object is null");
            throw new ProductInfoException("ProductInfo cannot be null");
        } else

            // Check if the product name is null, empty, or only whitespace
            if (p.getName() == null || p.getName().isBlank()) {
                Main.log.warn("ADD: Product name is missing");
                throw new ProductInfoException("Product name cannot be blank");
            } else

                // Check if the seller_name is null before trying to access getSellername()
                if (p.getSellername() == null || p.getSellername().getSellername() == null || p.getSellername().getSellername().isBlank()) {
                    Main.log.warn("ADD: Seller name is missing for product: " + p.getName());
                    throw new ProductInfoException("Seller name cannot be blank");
                } else

                    // Validate the product price
                    if (p.getPrice() <= 0) {
                        Main.log.warn("ADD: Price is <= 0 for product: " + p.getName());
                        throw new ProductInfoException("Product price should be greater than 0");
                    } else

                        // Check if the product name already exists in the repository
                        if (productRepository.getProductByName(p.getName()).size() > 0) {
                            Main.log.warn("ADD: Product name is duplicate: " + p.getName());
                            throw new ProductInfoException("Product name already exists");
                        } else

                            // Check if the sellername already exists; if not -> create anew Seller
                            if (sellerRepository.findBySellername(p.getSellername().getSellername()) == null) {
                                Seller newSeller = new Seller();
                                newSeller.setSellername(p.getSellername().getSellername());
                                sellerRepository.save(newSeller);
                                // Set the seller for the product
                                p.setSellername(newSeller);
                                return true;
                            } else {

                                return true;
                            }
    }

    /**
     * Deletes a product by its ID.
     *
     * @param productId The ID of the product to delete.
     * @throws ProductInfoException if the product does not exist.
     */
    public void deleteProduct(Long productId) throws ProductInfoException {
        // Check if the product exists
        if (!productRepository.existsById(productId)) {
            Main.log.warn("DELETE: Product with ID " + productId + " does not exist.");
            throw new ProductInfoException("Product with ID " + productId + " does not exist.");
        }

        // If it exists, delete the product
        productRepository.deleteById(productId);
        Main.log.info("DELETE: Product with ID " + productId + " has been deleted.");
    }

    public void updateProduct(ProductInfo updatedProduct) throws ProductInfoException {
        // Validate input
        if (!inputValuesValidated(updatedProduct)) {
            throw new ProductInfoException("Validation failed for the product.");
        }

        // Check if the product exists
        if (!productRepository.existsById(updatedProduct.getId())) {
            throw new ProductInfoException("Product with ID " + updatedProduct.getId() + " does not exist.");
        }

        // Perform the update
        productRepository.save(updatedProduct);
        Main.log.info("UPDATE: Product updated: " + updatedProduct);
    }

}

