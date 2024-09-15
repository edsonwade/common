package code.with.vanilson.common.car;

import code.with.vanilson.common.exceptions.ResourceBadRequestException;
import lombok.extern.slf4j.Slf4j;

/**
 * CarMapper
 *
 * @author vamuhong
 * @version 1.0
 * @since 2024-09-14
 */
@SuppressWarnings("unused")
@Slf4j
public class CarMapper {

    private CarMapper() {
        // no constructor
    }

    // create a method called mapToResponse and has parameter Car object.
    protected static CarResponse toResponse(Car car) {
        if (null == car) {
            log.error("Car is null{}", car);
            throw new ResourceBadRequestException("car.null_data");
        }
        return CarResponse.builder()
                .id(car.getId())
                .brandName(car.getBrandName())
                .modelName(car.getModelName())
                .registrationNumber(car.getRegistrationNumber())
                .carType(car.getCarType())
                .year(car.getYear())
                .kilometres(car.getKilometres())
                .price(car.getPrice())
                .build();
    }

    protected static Car toModel(CarRequest request) {
        if (null == request) {
            log.error("request is null{}", request);
            throw new ResourceBadRequestException("car.null_data");
        }
        var car = new Car();
        car.setBrandName(request.getBrandName());
        car.setModelName(request.getModelName());
        car.setRegistrationNumber(request.getRegistrationNumber());
        car.setCarType(request.getCarType());
        car.setYear(request.getYear());
        car.setKilometres(request.getKilometres());
        car.setPrice(request.getPrice());
        log.info("Car created: {}", car);
        return car;

    }
}