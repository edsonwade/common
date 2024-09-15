package code.with.vanilson.common.car;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

/**
 * CarResponse
 *
 * @author vamuhong
 * @version 1.0
 * @since 2024-09-14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarResponse extends RepresentationModel<CarResponse> {
    private Long id;
    private String brandName;
    private String modelName;
    private String registrationNumber;
    private String carType;
    private int year;
    private int kilometres;
    private BigDecimal price;
}