package code.with.vanilson.common.car;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * CarRepository
 *
 * @author vamuhong
 * @version 1.0
 * @since 2024-09-14
 */
public interface CarRepository extends JpaRepository<Car, Integer> {
    Optional<Car> findCarById(long id);
}