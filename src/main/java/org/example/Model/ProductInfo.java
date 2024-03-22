package org.example.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/** Class ProductInfo */
@Entity                                 //ProductInfo is a JPA entity mapped to a database table.
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private double price;

    //@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "sellername")
    //@JsonBackReference                      // Ignore the list of ProductInfo objects during serialization
    @JsonIgnoreProperties("products")
    private Seller sellername;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductInfo)) return false;
        ProductInfo that = (ProductInfo) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    /** This code is used to convert ProductInfo objects into a String (displayable format) */
    @Override
    public String toString() {
        return "ProductInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", sellername='" + sellername + '\'' +
                '}';
    }
}

