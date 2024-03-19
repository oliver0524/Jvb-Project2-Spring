package org.example.repository;
import org.example.Model.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**GpaRepository and CrudRepository are generic interfaces.
The first type argument (ProductInfo) indicates the entity class.
The second (Long) specifies the entity's primary key type.*/
public interface ProductRepository extends JpaRepository <ProductInfo, Long> {

    /** This method handles the call to a db with sql - select a record with a given name from PRODUCTS. Needed for validations */
    List<ProductInfo> getProductByName(String name);
    @Query("from PRODUCTS where product_name=:name")
    List<ProductInfo> getProductByName2(@Param("name") String name);
}
