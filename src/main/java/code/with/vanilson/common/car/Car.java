package code.with.vanilson.common.car;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Car
 *
 * @author vamuhong
 * @version 1.0
 * @since 2024-09-14
 */
@Entity
@Table(name = "cars")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Car implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "brand_name")
    private String brandName;
    @Column(nullable = false, name = "model_name")
    private String modelName;
    @Column(nullable = false, name = "reg_no")
    private String registrationNumber;
    @Column(nullable = false, name = "car_type")
    private String carType;
    @Column(nullable = false, name = "yr")
    private int year;
    @Column(nullable = false, name = "kms")
    private int kilometres;
    @Column(nullable = false, precision = 6, scale = 2)
    private BigDecimal price;

    public Car(String brandName, String modelName, String registrationNumber, String carType, int year, int kilometres,
               BigDecimal price) {
        this.brandName = brandName;
        this.modelName = modelName;
        this.registrationNumber = registrationNumber;
        this.carType = carType;
        this.year = year;
        this.kilometres = kilometres;
        this.price = price;
    }
}