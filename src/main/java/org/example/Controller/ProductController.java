package org.example.Controller;

import org.example.Exception.ProductInfoException;
import org.example.Model.ProductInfo;
import org.example.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class ProductController {

    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //endpoints for localhost:9002/product
    @GetMapping("/product")
    public ResponseEntity<List<ProductInfo>> getAllProductsEndpoint() {
        List<ProductInfo> p = productService.getAllProducts();
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @PostMapping("/product")
    public ResponseEntity<?> postProductsEndpoint(@RequestBody ProductInfo p) {
        try {
            // Log the incoming product for debugging purposes
            System.out.println("Received product for addition: " + p);

            // Saving the product.
            productService.addProduct(p);

            // Return the added ProductInfo object with HttpStatus.CREATED
            return new ResponseEntity<>(p, HttpStatus.CREATED);
        } catch (ProductInfoException e) {
            System.err.println("Error adding product: " + e.getMessage());

            // Specific error response, returning a string.
            String errorResponse = "Product not added: " + e.getMessage();
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            // Catch any other exceptions & return a generic error response
            System.err.println("Unexpected error: " + e.getMessage());

            String errorResponse = "An unexpected error occurred while adding the product.";
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/product/{id}")
    public ResponseEntity<?> deleteProductEndpoint(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            // Product successfully deleted
            return new ResponseEntity<>("Product successfully deleted.", HttpStatus.OK);
        } catch (ProductInfoException e) {
            // Custom exception handling for when the product doesn't exist or other errors
            System.err.println("Error deleting product: " + e.getMessage());
            return new ResponseEntity<>("Nothing to delete: " + e.getMessage(), HttpStatus.OK);
        } catch (Exception e) {
            // General exception handling for unexpected errors
            System.err.println("Unexpected error during deletion: " + e.getMessage());
            return new ResponseEntity<>("An unexpected error occurred while deleting the product.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<?> updateProductEndpoint(@PathVariable Long id, @RequestBody ProductInfo productInfo) {
        try {
            // Ensure the ID in the path matches the ID in the body
            productInfo.setId(id);

            productService.updateProduct(productInfo);

            // The product was successfully updated
            return new ResponseEntity<>(productInfo, HttpStatus.OK);
        } catch (ProductInfoException e) {
            // Log the exception and return an error response for unsuccessful update
            System.err.println("Error updating product: " + e.getMessage());

            // Specific error response, returning a string.
            String errorResponse = "Product not updated: " + e.getMessage();
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            // Catch any other exceptions & return a generic error response
            System.err.println("Unexpected error: " + e.getMessage());

            String errorResponse = "An unexpected error occurred while updating the product.";
            return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}


