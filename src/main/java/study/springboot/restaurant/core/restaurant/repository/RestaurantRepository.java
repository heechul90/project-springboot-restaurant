package study.springboot.restaurant.core.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.springboot.restaurant.core.restaurant.domain.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
