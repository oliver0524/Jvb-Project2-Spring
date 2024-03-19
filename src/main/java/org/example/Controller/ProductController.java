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
    public ResponseEntity<?> postProductsEndpoint(@RequestBody ProductInfo productInfo)
            throws ProductInfoException {
        try {
            productService.addProduct(productInfo);
            return new ResponseEntity<>(productInfo, HttpStatus.CREATED);       // Return the added ProductInfo object
        } catch (ProductInfoException e) {
            ProductInfoErrorDto errorResponse = new ProductInfoErrorDto();
            errorResponse.setMessage("Product not added: " + e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
        }


}


//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import io.javalin.Javalin;
//import io.javalin.http.Header;
//import org.example.Exception.ProductInfoException;
//import org.example.Exception.SellerException;
//import org.example.Model.ProductInfo;
//import org.example.Model.Seller;
//import org.example.Service.ProductService;
//import org.example.Service.SellerService;
//
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import static org.example.Service.ProductService.*;
//
//
//public class ProductController {
//
//    SellerService sellerService;
//    ProductService productService;
//
//    public ProductController(SellerService sellerService, ProductService productService){
//        this.sellerService = sellerService;
//        this.productService = productService;
//    }
//
//    /** Method to create & configure a Javalin api; start the service; define endpoints
//     * With the Create method we are referencing the Javalin class directly */
//    public Javalin getAPI(){
//        Javalin api = Javalin.create();
//
//        api.before (ctx -> {
//            ctx.header("Access-Control-Allow-Origin", "*");
//            ctx.header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
//            ctx.header("Access-Control-Allow-Headers", "*");
//        });
//
//        //Javalin to handle preflight requests (sent via OPTIONS)
//        api.options("/*", ctx -> {
//            ctx.header(Header.ACCESS_CONTROL_ALLOW_ORIGIN, "http://localhost:3000");
//            ctx.header(Header.ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, PUT, DELETE, OPTIONS");
//            ctx.header(Header.ACCESS_CONTROL_ALLOW_HEADERS, "Content-Type, Authorization");
//            ctx.status(200);
//        });
//
//        api.get("/health",
//                context ->
//                {
//                    context.result("the server is UP");
//                }
//        );
//
//        /** Endpoint to GET either all records or through a param NAME a search by a seller name */
//        api.get("/seller", context -> {
//            String sellerName = context.queryParam("name");
//            try {
//                if (sellerName != null && !sellerName.isEmpty()) {
//                    String s = sellerService.getSellerByName(sellerName);
//                    context.json(s);
//                    context.status(200);
//                } else {
//                    Set<String> sellerSet = sellerService.getAllSellers();
//                    context.json(sellerSet);
//                    context.status(200);
//                }
//            }catch(SellerException e){
//                context.result(e.getMessage());
//                context.status(400);
//            }
//        });
//
//        /** Endpoint to GET either all records from PRODUCTS or through a param LIKE a search by a partial product name */
//        api.get("/product", context -> {
//            String productName = context.queryParam("like");
//            try {
//                if (productName != null && !productName.isEmpty()) {
//                    List<ProductInfo> p = productService.getProductByPartialName(productName);
//                    context.json(p);
//                    context.status(200);
//                } else {
//                    List<ProductInfo> p = productService.getAllProducts();
//                    context.json(p);
//                    context.status(200);
//                }
//            }catch(ProductInfoException e){
//                context.result(e.getMessage());
//                context.status(400);
//        }
//        });
//
//        /** Endpoint to POST a new record to Sellers. If seller is added, return 200; otherwise 401 */
//        api.post("/seller", context -> {
//            try{
//                ObjectMapper om = new ObjectMapper();
//                Seller s = om.readValue(context.body(), Seller.class);
//                sellerService.addSeller(s);
//                context.status(201);
//            }catch(JsonProcessingException | SellerException e){
//                e.printStackTrace();
//                context.result(e.getMessage());
//                context.status(400);
//            }
//        });
//
//        /** Endpoint to POST a new record to Products. If product is added, return 201; otherwise 401 */
//        api.post("/product", context -> {
//            try{
//                ObjectMapper om = new ObjectMapper();
//                ProductInfo p = om.readValue(context.body(), ProductInfo.class);
//                ProductInfo newProduct = productService.addProduct(p);
//                context.status(201);
//                context.json(newProduct);
//            }catch(JsonProcessingException e){
//                //e.printStackTrace();
//                context.json(Map.of("Error", e.getMessage())); // Wrap message in a JSON object
//                context.status(400);
//            }catch(ProductInfoException e){
//                //e.printStackTrace();
//                context.json(Map.of("Error", e.getMessage())); // Wrap message in a JSON object
//                context.status(400);
//            }
//        });
//
//        /** If product id is found, respond with status 200
//         *  If product id is not found, respond with status 404 */
//        api.get("product/{id}", context -> {
//            try{
//                Integer id = Integer.valueOf((context.pathParam("id")));
//                ProductInfo p = productService.getProductById(id);
//                context.json(p);
//                context.status(200);
//            }catch(ProductInfoException e){
//                context.result(e.getMessage());
//                context.status(400);
//            }
//        });
//
//        /** If product id is found, delete and respond with status 200
//         *  If product id is not found, respond with status 200 anyway (this is a convention) */
//        api.delete("product/{id}", context -> {
//            Integer id = Integer.valueOf((context.pathParam("id")));
//            productService.deleteProductById(id);
//            context.status(200);
//        });
//
//        /** If product id is found, update and respond with status 200 */
//        api.put("product/{id}", context -> {
//            try{
//                Integer id = Integer.valueOf((context.pathParam("id")));
//                ObjectMapper om = new ObjectMapper();
//                ProductInfo p = om.readValue(context.body(), ProductInfo.class);
//                p.setId(id);
//                productService.updateProductById(p);
//                context.json(p);
//                context.status(200);
//            }catch(ProductInfoException e){
//                context.json(Map.of("Error", e.getMessage())); // Wrap message in a JSON object
//                context.status(400);
//            }
//        });
//
//        return api;
//    }
//
//}


