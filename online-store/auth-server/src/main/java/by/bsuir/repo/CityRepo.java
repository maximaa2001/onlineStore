package by.bsuir.repo;

import by.bsuir.entity.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepo extends JpaRepository<City, Integer> {
    Optional<City> findByCityName(String cityName);
}
