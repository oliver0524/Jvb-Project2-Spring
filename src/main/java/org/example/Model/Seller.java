package org.example.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
@ToString
public class Seller {
    @Id
    private String sellername;

    @OneToMany(mappedBy = "seller_name")
    @JsonIgnore
    public List<ProductInfo> products;

}

//    /** Constructor for class Seller */
//    public Seller(String sellername) {
//        this.sellername = sellername;
//    }
//
//    public Seller(){
//    }
//
//    /** Setters and getters for the Seller class variables. No usage annotations are ok */
//
//    public String getSellername() {
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
//        if (!(o instanceof Seller)) return false;
//        Seller seller = (Seller) o;
//        return Objects.equals(sellername, seller.sellername);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(sellername);
//    }
//
//    /** This code is used to convert Seller objects into a String (displayable format) */
//    @Override
//    public String toString() {
//    return "\n" +
//            "Seller Name: " + sellername;
//}
//}