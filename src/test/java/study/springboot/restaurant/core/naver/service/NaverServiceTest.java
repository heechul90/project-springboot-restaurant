package study.springboot.restaurant.core.naver.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.springboot.restaurant.core.naver.service.NaverService;
import study.springboot.restaurant.core.restaurant.dto.RestaurantDto;
import study.springboot.restaurant.core.restaurant.servie.RestaurantService;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class NaverServiceTest {

    @Autowired
    NaverService naverService;

    @Test
    void searchTest() {
        //given
        String query = "갈비집";

        //when
        RestaurantDto restaurantDto = naverService.search(query);
        System.out.println("restaurantDto = " + restaurantDto);

        //then
        assertThat(restaurantDto).isNotNull();

    }
}