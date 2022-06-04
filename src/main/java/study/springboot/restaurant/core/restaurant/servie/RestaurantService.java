package study.springboot.restaurant.core.restaurant.servie;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.springboot.restaurant.core.restaurant.domain.Restaurant;
import study.springboot.restaurant.core.restaurant.repository.RestaurantRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    /**
     * 식당 목록 검색
     */
    public List<Restaurant> findRestaurants() {
        return restaurantRepository.findAll();
    }

    /**
     * 식당 단건 검색
     */
    public Restaurant findRestaurant(Long restaurantId) {
        return restaurantRepository.findById(restaurantId).orElse(null);
    }

    /**
     * 식당 저장
     */
    @Transactional
    public Restaurant saveRestaurant(Restaurant restaurant) {
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);
        return savedRestaurant;
    }

    //TODO 수정

    /**
     * 식당 삭제
     */
    @Transactional
    public void deleteRestaurant(Long id) {
        Restaurant findRestaurant = restaurantRepository.findById(id).orElse(null);
        restaurantRepository.delete(findRestaurant);
    }

    /**
     * 식당 방문 카운트
     */
    @Transactional
    public void visitRestaurant(Long id) {
        Restaurant findRestaurant = restaurantRepository.findById(id).orElse(null);
        if (findRestaurant != null) {
            findRestaurant.visit(true);
        }
    }
}
