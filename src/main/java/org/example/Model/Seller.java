package org.example.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
//@ToString
public class Seller {
    @Id
    private String sellername;

    @OneToMany(mappedBy = "sellername", fetch = FetchType.EAGER)
    //@JsonManagedReference              // Serialize this side of the relationship
    public List<ProductInfo> products;


    /** This code is used to convert Seller objects into a String (displayable format) */
    @Override
    public String toString() {
    return "\n" +
            "Seller Name: " + sellername;
}
}