package uz.pdp.appapitask1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    @Column(nullable = false,unique = true)
    private String corpName;
    @Column(nullable = false)
    private String directorName;
    @OneToOne
    private Address address;

}
