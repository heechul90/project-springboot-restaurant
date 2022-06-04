package study.springboot.restaurant.core.restaurant.servie;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.springboot.restaurant.core.restaurant.domain.Restaurant;
import study.springboot.restaurant.core.restaurant.repository.RestaurantRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public List<Restaurant> findRestaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant findRestaurant(Long restaurantId) {
        return restaurantRepository.findById(restaurantId).orElse(null);
    }

    @Transactional
    public Long saveRestaurant(Restaurant restaurant) {
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);
        return savedRestaurant.getId();
    }
}
