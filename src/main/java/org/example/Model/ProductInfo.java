package org.example.Model;

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

    @JsonIgnore                        //Prevents infinite recursion during JSON serialization
    @ManyToOne
    @JoinColumn(name = "sellername")   //the foreign key column
    private Seller seller_name;
}

    // Constructor for the ProductInfo class
//    public ProductInfo(int id, String name, double price, String sellername) {
//        this.id = id;
//        this.name = name;
//        this.price = price;
//        this.sellername = sellername;
//    }

//    public ProductInfo(){
//    }

//    /** Setters and getters for the ProductInfo class variables. No usage annotations are ok */
//    public int getId() {
//        return (int) id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setPrice(double price) {
//        this.price = price;
//    }
//
//    public double getPrice() {
//        return price;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Seller getSellername() {
//        return sellername;
//    }
//
//    public void setSellername(String sellername) {
//        this.sellername = sellername;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof ProductInfo)) return false;
//        ProductInfo that = (ProductInfo) o;
//        return name.equals(that.name);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(name);
//    }
//
//    /** This code is used to convert ProductInfo objects into a String (displayable format) */
//    @Override
//    public String toString() {
//        return "ProductInfo{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", price=" + price +
//                ", sellername='" + sellername + '\'' +
//                '}';
//    }
//}

