package code.with.vanilson.common.car;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.http.HttpMethod.HEAD;

/**
 * CarController
 *
 * @author vamuhong
 * @version 1.0
 * @since 2024-09-14
 */
@RestController
@RequestMapping(path = "/api/cars")
@SuppressWarnings("unused")
@Slf4j
public class CarController {

    public static final String CARS = "cars";
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public ResponseEntity<List<CarResponse>> getAllCars() {
        log.info("Retrieving all cars");
        var carResponses = carService.findAllCars();

        // Add HATEOAS links to the CarResponse
        carResponses.forEach(carResponse -> {
            carResponse.add(linkTo(methodOn(CarController.class).getCarById(Integer.parseInt(
                    String.valueOf(carResponse.getId())))).withSelfRel());
            carResponse.add(linkTo(methodOn(CarController.class).getAllCars()).withRel(CARS));
        });

        return ResponseEntity
                .ok()
                .allow(HEAD)
                .body(carResponses);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CarResponse> getCarById(@PathVariable("id") int id) {
        log.info("Retrieving car with id {}", id);
        var carResponse = carService.findCarById(id);
        // Add HATEOAS links
        carResponse.add(linkTo(methodOn(CarController.class)
                .getCarById(Integer.parseInt(
                        String.valueOf(carResponse.getId()))))
                .withSelfRel());
        carResponse.add(linkTo(methodOn(CarController.class).getAllCars()).withRel(CARS));
        return ResponseEntity.ok(carResponse);
    }

    @PostMapping
    public ResponseEntity<CarResponse> addCar(@Valid @RequestBody CarRequest car,
                                              UriComponentsBuilder uriComponentsBuilder) {
        log.info("The car is {}", car);
        var carResponse = carService.createCar(car);

        // Add HATEOAS links
        carResponse.add(linkTo(methodOn(CarController.class)
                .getCarById(Integer.parseInt(
                        String.valueOf(carResponse.getId()))))
                .withSelfRel());
        carResponse.add(linkTo(methodOn(CarController.class).getAllCars()).withRel(CARS));

        URI locationURI = uriComponentsBuilder
                .path("/api/cars/" + carResponse.getRegistrationNumber())
                .buildAndExpand(uriComponentsBuilder.toUriString())
                .toUri();
        log.info("Created new car with id {}", carResponse.getRegistrationNumber());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .location(locationURI)
                .body(carResponse);
    }

    @PutMapping(value = "/update-car/{id}")
    public ResponseEntity<CarResponse> updateCar(@Valid @RequestBody CarRequest car,
                                                 @PathVariable("id") long id) {
        log.info("Updating car with id {}", id);
        var carResponseUpdate = carService.updateCar(id, car);
        // Add HATEOAS links
        carResponseUpdate.add(linkTo(methodOn(CarController.class)
                .getCarById(Integer.parseInt(
                        String.valueOf(carResponseUpdate.getId()))))
                .withSelfRel());

        carResponseUpdate.add(linkTo(methodOn(CarController.class)
                .getAllCars())
                .withRel(CARS));

        return ResponseEntity.ok()
                .body(carResponseUpdate);
    }

    @DeleteMapping(value = "/delete-car/{id}")
    public ResponseEntity<String> deleteAllCars(@PathVariable Long id) {
        carService.deleteACarById(id);
        log.info("Deleting car with id {}", id);
        // Add HATEOAS link to the list of all fines
        // Create a base URL for listing fines
        String allFinesUrl = linkTo(methodOn(CarController.class)
                .getAllCars())
                .toUri()
                .toString();

        return ResponseEntity
                .noContent()
                .header(HttpHeaders.LINK, allFinesUrl)
                .build();
    }

}