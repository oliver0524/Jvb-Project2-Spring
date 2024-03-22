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
            System.err.println("Error adding seller: " + e.getMessage());

            // Specific error response, returning a string.
            String errorResponse = "Seller not added: " + e.getMessage();
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }


}

