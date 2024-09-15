package code.with.vanilson.common.car;

import code.with.vanilson.common.exceptions.ResourceBadRequestException;
import code.with.vanilson.common.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;

import static code.with.vanilson.common.utils.MessageProvider.getMessage;

/**
 * CarService
 *
 * @author vamuhong
 * @version 1.0
 * @since 2024-09-14
 */
@SuppressWarnings("unused")
@Service
@Slf4j
public class CarService {
    public static final String CAR_NOT_FOUND = "car.not_found";
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Transactional(readOnly = true)
    public List<CarResponse> findAllCars() {
        return carRepository
                .findAll()
                .stream()
                .map(CarMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public CarResponse findCarById(long id) {
        validateCarId(id);
        return carRepository
                .findCarById(id)
                .map(CarMapper::toResponse)
                .orElseThrow(() -> {
                    var errorMessage = MessageFormat.format(getMessage(CAR_NOT_FOUND), id);
                    return new ResourceNotFoundException(errorMessage);
                });

    }

    @Transactional
    @Modifying
    public CarResponse createCar(CarRequest request) {
        if (null == request) {
            log.error("Request is null");
            throw new ResourceBadRequestException("car.null_data");
        }
        var savedCar = CarMapper.toModel(request);
        var response = carRepository.save(savedCar);
        log.info("Saved car: {}", response);
        return CarMapper.toResponse(response);
    }

    @Transactional
    public CarResponse updateCar(long id, CarRequest request) {
        if (null == request) {
            log.error("request is null {}", (Object) null);
            throw new ResourceBadRequestException("car.null_data");
        }
        validateCarId(id);
        var existingCar = buildCarFromRequestAndId(id, request);
        var response = carRepository.save(existingCar);
        log.info("Updated car: {}", response);
        return CarMapper.toResponse(response);
    }

    @Transactional(readOnly = true)
    public void deleteACarById(long id) {
        validateCarId(id);
        var deletedCar = carRepository.findCarById(id);
        log.info("Deleting car: " + deletedCar);
        deletedCar.ifPresent(carRepository::delete);
        throw new ResourceNotFoundException(CAR_NOT_FOUND);

    }

    private static void validateCarId(long id) {
        if (id <= 0) {
            var errorMessage = getMessage("car.invalid_data", id);
            log.error("The Car id provide is less than or equal to zero {} ", id);
            throw new ResourceBadRequestException(errorMessage);
        }
    }

    private Car buildCarFromRequestAndId(long id, CarRequest request) {
        var existingCar = carRepository.findCarById(id)
                .orElseThrow(() -> new ResourceNotFoundException(CAR_NOT_FOUND));

        // Update the fields of the existing car object without creating a new one
        existingCar.setBrandName(request.getBrandName());
        existingCar.setModelName(request.getModelName());
        existingCar.setRegistrationNumber(request.getRegistrationNumber());
        existingCar.setCarType(request.getCarType());
        existingCar.setYear(request.getYear());
        existingCar.setKilometres(request.getKilometres());
        existingCar.setPrice(request.getPrice());

        log.info("Car updated: {}", existingCar);
        return existingCar; // return the updated existingCar object
    }

}