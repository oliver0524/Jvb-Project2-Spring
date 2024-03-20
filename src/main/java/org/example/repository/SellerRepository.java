package org.example.repository;

import org.example.Model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.Set;


public interface SellerRepository extends JpaRepository<Seller, String> {

        //@Query("select s.sellername from Sellers s")  // JPQL query selecting Seller objects
        //Set<String> findAllSellers();
    }


//    public Set<String> findBySeller(String sellername) {
//        //JPQL query (java persistence query language)
//        @Query("from Sellers where sellername=:sellername")
//        Set<String> findBySeller2(@Param("sellername") String sellername);
//    }

//}