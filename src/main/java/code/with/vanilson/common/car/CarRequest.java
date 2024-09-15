package code.with.vanilson.common.car;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * CarRequest
 *
 * @author vamuhong
 * @version 1.0
 * @since 2024-09-14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarRequest {
    @NotBlank(message = "Brand name cannot be blank")
    private String brandName;

    @NotBlank(message = "Model name cannot be blank")
    private String modelName;

    @Pattern(regexp = "[A-Z]{2}-\\d{2}-[A-Z]{2}-\\d{4}", message = "Invalid registration number format")
    private String registrationNumber;

    @NotBlank(message = "Car type cannot be blank")
    private String carType;

    @Min(value = 1900, message = "Year must be greater than or equal to 1900")
    @Max(value = 2022, message = "Year must be less than or equal to 2022")
    private int year;

    @Min(value = 0, message = "Kilometres must be a positive number")
    private int kilometres;

    @DecimalMin(value = "0.0", message = "Price must be a positive number")
    private BigDecimal price;
}