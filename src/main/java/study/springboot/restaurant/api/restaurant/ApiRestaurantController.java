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
public class ApiRestaurantController {

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
                        restaurant.getId(),
                        restaurant.getTitle(),
                        restaurant.getCategory(),
                        restaurant.getAddress(),
                        restaurant.getRoadAddress(),
                        restaurant.getHomePageLink(),
                        restaurant.getImageLink(),
                        restaurant.isVisit(),
                        restaurant.getVisitCount(),
                        restaurant.getLastVisitDate()
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
                findRestaurant.getId(),
                findRestaurant.getTitle(),
                findRestaurant.getCategory(),
                findRestaurant.getAddress(),
                findRestaurant.getRoadAddress(),
                findRestaurant.getHomePageLink(),
                findRestaurant.getImageLink(),
                findRestaurant.isVisit(),
                findRestaurant.getVisitCount(),
                findRestaurant.getLastVisitDate()
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
        Restaurant savedRestaurant = restaurantService.saveRestaurant(restaurant);
        RestaurantDto dto = new RestaurantDto(
                savedRestaurant.getId(),
                savedRestaurant.getTitle(),
                savedRestaurant.getCategory(),
                savedRestaurant.getAddress(),
                savedRestaurant.getRoadAddress(),
                savedRestaurant.getHomePageLink(),
                savedRestaurant.getImageLink(),
                savedRestaurant.isVisit(),
                savedRestaurant.getVisitCount(),
                savedRestaurant.getLastVisitDate()
        );

        return new JsonResult("OK", dto);
    }

    //TODO 식당 수정

    /**
     * 식장 삭제
     */
    @DeleteMapping(value = "/{id}")
    public JsonResult deleteRestaurant(@PathVariable("id") Long id) {
        restaurantService.deleteRestaurant(id);
        return new JsonResult("OK", null);
    }

    /**
     * 식당 방문 카운트
     */
    @PostMapping(value = "/{id}")
    public JsonResult visitRestaurant(@PathVariable("id") Long id) {
        restaurantService.visitRestaurant(id);
        return new JsonResult("OK", null);
    }
}
