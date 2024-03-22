package org.example.Service;

import org.example.Main;
//import org.example.DAO.SellerDAO;
import org.example.Exception.SellerException;
import org.example.Model.Seller;
import org.example.repository.ProductRepository;
import org.example.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.*;

@Service
public class SellerService {

    SellerRepository sellerRepository;

    @Autowired
    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    /** This method handles the Seller addition and throws the SellerException if it does not pass validations */

    public void addSeller(Seller s) throws SellerException {
        Main.log.info("Attempting to add a Seller:" + s);

        if (s.getSellername().isBlank()) {
            Main.log.warn("Seller name is missing: " + s.getSellername());
            throw new SellerException("Seller name cannot be blank");
        } else {
            // Check for existing seller with the same name
            Optional<Seller> existingSeller = Optional.ofNullable(sellerRepository.findBySellername(s.getSellername()));

            if (existingSeller.isPresent()) {
                Main.log.warn("Duplicate seller name: " + s.getSellername());
                throw new SellerException("Seller with this name already exists");
            } else {
                sellerRepository.save(s);
            }
        }
    }

    /**  This method handles the 'view' action and displays all Seller objects from the Seller Set */
    public List<Seller> getAllSellers() {
        List<Seller> sellers = sellerRepository.findAll();
        Main.log.info("Set of all Sellers in the collection: " + sellers);
        return sellers;
    }

}



