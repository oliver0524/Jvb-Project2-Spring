package org.example.Controller;

import org.example.Exception.SellerException;
import org.example.Model.Seller;
import org.example.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin
@RestController
public class SellerController {

    private final SellerService SellerService;
    @Autowired
    public SellerController(SellerService SellerService) {
        this.SellerService = SellerService;
    }

    //endpoints for localhost:9002/Seller
    @GetMapping("/seller")
    public ResponseEntity<List<Seller>> getAllSellersEndpoint() {
        List<Seller> sellers = SellerService.getAllSellers();
        return new ResponseEntity<>(sellers, HttpStatus.OK);
    }

    @PostMapping("/seller")
    public ResponseEntity<?> postSellerEndpoint(@RequestBody Seller s) throws SellerException {
        try {
            SellerService.addSeller(s);
            return new ResponseEntity<>(s, HttpStatus.CREATED);       // Return the added Seller object
        } catch (SellerException e) {
            return new ResponseEntity<>("Seller not added: ", HttpStatus.BAD_REQUEST);
        }
    }


}


//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import io.javalin.Javalin;
//import io.javalin.http.Header;
//import org.example.Exception.SellerException;
//import org.example.Exception.SellerException;
//import org.example.Model.Seller;
//import org.example.Model.Seller;
//import org.example.Service.SellerService;
//import org.example.Service.SellerService;
//
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import static org.example.Service.SellerService.*;
//
//
//public class SellerController {
//
//    SellerService sellerService;
//    SellerService SellerService;
//
//    public SellerController(SellerService sellerService, SellerService SellerService){
//        this.sellerService = sellerService;
//        this.SellerService = SellerService;
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
//        /** Endpoint to GET either all records from SellerS or through a param LIKE a search by a partial Seller name */
//        api.get("/Seller", context -> {
//            String SellerName = context.queryParam("like");
//            try {
//                if (SellerName != null && !SellerName.isEmpty()) {
//                    List<Seller> p = SellerService.getSellerByPartialName(SellerName);
//                    context.json(p);
//                    context.status(200);
//                } else {
//                    List<Seller> p = SellerService.getAllSellers();
//                    context.json(p);
//                    context.status(200);
//                }
//            }catch(SellerException e){
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
//        /** Endpoint to POST a new record to Sellers. If Seller is added, return 201; otherwise 401 */
//        api.post("/Seller", context -> {
//            try{
//                ObjectMapper om = new ObjectMapper();
//                Seller p = om.readValue(context.body(), Seller.class);
//                Seller newSeller = SellerService.addSeller(p);
//                context.status(201);
//                context.json(newSeller);
//            }catch(JsonProcessingException e){
//                //e.printStackTrace();
//                context.json(Map.of("Error", e.getMessage())); // Wrap message in a JSON object
//                context.status(400);
//            }catch(SellerException e){
//                //e.printStackTrace();
//                context.json(Map.of("Error", e.getMessage())); // Wrap message in a JSON object
//                context.status(400);
//            }
//        });
//
//        /** If Seller id is found, respond with status 200
//         *  If Seller id is not found, respond with status 404 */
//        api.get("Seller/{id}", context -> {
//            try{
//                Integer id = Integer.valueOf((context.pathParam("id")));
//                Seller p = SellerService.getSellerById(id);
//                context.json(p);
//                context.status(200);
//            }catch(SellerException e){
//                context.result(e.getMessage());
//                context.status(400);
//            }
//        });
//
//        /** If Seller id is found, delete and respond with status 200
//         *  If Seller id is not found, respond with status 200 anyway (this is a convention) */
//        api.delete("Seller/{id}", context -> {
//            Integer id = Integer.valueOf((context.pathParam("id")));
//            SellerService.deleteSellerById(id);
//            context.status(200);
//        });
//
//        /** If Seller id is found, update and respond with status 200 */
//        api.put("Seller/{id}", context -> {
//            try{
//                Integer id = Integer.valueOf((context.pathParam("id")));
//                ObjectMapper om = new ObjectMapper();
//                Seller p = om.readValue(context.body(), Seller.class);
//                p.setId(id);
//                SellerService.updateSellerById(p);
//                context.json(p);
//                context.status(200);
//            }catch(SellerException e){
//                context.json(Map.of("Error", e.getMessage())); // Wrap message in a JSON object
//                context.status(400);
//            }
//        });
//
//        return api;
//    }
//
//}


