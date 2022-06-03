package study.springboot.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.springboot.restaurant.domain.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
