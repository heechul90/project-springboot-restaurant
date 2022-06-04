package study.springboot.restaurant.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.springboot.restaurant.domain.RestaurantDto;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class RestaurantServiceTest {

    @Autowired
    RestaurantService restaurantService;

    @Test
    void searchTest() {
        //given
        String query = "갈비집";

        //when
        RestaurantDto restaurantDto = restaurantService.search(query);
        System.out.println("restaurantDto = " + restaurantDto);

        //then
        assertThat(restaurantDto).isNotNull();

    }
}