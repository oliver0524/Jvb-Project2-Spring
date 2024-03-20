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
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class SellerService {

    /**Class SellerService handles the Main functionality for Sellers.
     * Methods here have functionality for the following actions: add, view all*/
    //Set<Seller> sellerSet;
    //SellerDAO sellerDAO;
    ProductRepository productRepository;
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
        } /*else if (sellerRepository.getSellerByName(s.getSellername()) != null) {
            Main.log.warn("Seller name is duplicate: " + s.getSellername());
            throw new SellerException("Seller name already exists ");
        }*/ else {
            sellerRepository.save(s);
        }
    }

    /**  This method handles the 'view' action and displays all Seller objects from the Seller Set */
    public List<Seller> getAllSellers() {
        List<Seller> sellers = sellerRepository.findAll();
        Main.log.info("Set of all Sellers in the collection: " + sellers);
        return sellers;
    }
//
//    /**  This method handles the 'view' action to retrieve a single seller by name */
//    public String getSellerByName(String name) throws SellerException {
//        Main.log.info("VIEW: Searching for a seller: " + name);
//        String s = sellerDAO.getSellerByName(name);
//        if (s == null) {
//            throw new SellerException("No Seller found ");
//        } else {
//            return s;
//        }
//    }

}



