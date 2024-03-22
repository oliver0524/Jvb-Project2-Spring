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
    Seller findBySellername(String sellername);

    @Query(value = "SELECT s FROM Seller s WHERE s.sellername = :sellername")
    Seller searchBySellername(@Param("sellername") String sellername);
}
