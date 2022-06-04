package study.springboot.restaurant.api.restaurant;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import study.springboot.restaurant.core.common.json.JsonResult;
import study.springboot.restaurant.core.naver.service.NaverService;
import study.springboot.restaurant.core.restaurant.domain.Restaurant;
import study.springboot.restaurant.core.restaurant.dto.RestaurantDto;
import study.springboot.restaurant.core.restaurant.servie.RestaurantService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/restaurants")
public class RestaurantController {

    private final NaverService naverService;
    private final RestaurantService restaurantService;

    /**
     * Naver api local 검색
     */
    @GetMapping(value = "/search")
    public JsonResult search(@RequestParam String query) {
        RestaurantDto result = naverService.search(query);
        return new JsonResult("OK", result);
    }

    /**
     * 식당 목록 검색
     */
    @GetMapping
    public JsonResult findRestaurants() {
        List<Restaurant> restaurants = restaurantService.findRestaurants();
        List<RestaurantDto> collect = restaurants.stream()
                .map(restaurant -> new RestaurantDto(
                        restaurant.getTitle(),
                        restaurant.getCategory(),
                        restaurant.getAddress(),
                        restaurant.getRoadAddress(),
                        restaurant.getHomePageLink(),
                        restaurant.getImageLink()
                ))
                .collect(Collectors.toList());
        return new JsonResult("OK", collect);
    }

    /**
     * 식당 단건 검색
     */
    @GetMapping(value = "/{id}")
    public JsonResult findRestaurant(@PathVariable("id") Long id) {
        Restaurant findRestaurant = restaurantService.findRestaurant(id);
        RestaurantDto restaurant = new RestaurantDto(
                findRestaurant.getTitle(),
                findRestaurant.getCategory(),
                findRestaurant.getAddress(),
                findRestaurant.getRoadAddress(),
                findRestaurant.getHomePageLink(),
                findRestaurant.getImageLink()
        );
        return new JsonResult("OK", restaurant);
    }

    /**
     * 식당 저장
     */
    @PostMapping
    public JsonResult saveRestaurant(@RequestBody RestaurantDto restaurantDto) {
        Restaurant restaurant = Restaurant.createRestaurant()
                .title(restaurantDto.getTitle())
                .category(restaurantDto.getCategory())
                .address(restaurantDto.getAddress())
                .roadAddress(restaurantDto.getRoadAddress())
                .homePageLink(restaurantDto.getHomePageLink())
                .imageLink(restaurantDto.getImageLink())
                .build();
        Long savedId = restaurantService.saveRestaurant(restaurant);
        return new JsonResult("OK", savedId);
    }



}
