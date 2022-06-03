package study.springboot.restaurant.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.springboot.restaurant.domain.Restaurant;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class RestaurantRepositoryTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    RestaurantRepository restaurantRepository;

    private Restaurant createRestaurant() {
        Restaurant restaurant = Restaurant.createRestaurant()
                .title("title")
                .category("category")
                .address("address")
                .readAddress("readAddress")
                .homePageLink("www")
                .imageLink("www")
                .isVisit(true)
                .visitCount(10)
                .lastVisitDate(LocalDateTime.now())
                .build();
        return restaurant;
    }

    @Test
    public void saveTest() throws Exception{
        //given
        Restaurant restaurant = createRestaurant();

        //when
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        //then
        Restaurant findRestaurant = restaurantRepository.findById(savedRestaurant.getId()).orElse(null);
        assertThat(findRestaurant).isEqualTo(restaurant);
        assertThat(findRestaurant.getTitle()).isEqualTo("title");
    }

    @Test
    public void updateTest() throws Exception{
        //given
        Restaurant restaurant = createRestaurant();
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);
        em.flush();
        em.clear();

        //when
        Restaurant findRestaurant = restaurantRepository.findById(savedRestaurant.getId()).orElse(null);
        findRestaurant.changeTitle("update title");
        em.flush();
        em.clear();

        //then
        Restaurant updateRestaurant = restaurantRepository.findById(savedRestaurant.getId()).orElse(null);
        assertThat(updateRestaurant.getTitle()).isEqualTo("update title");
    }

    @Test
    public void findAllTest() throws Exception{
        //given
        Restaurant restaurant1 = createRestaurant();
        Restaurant restaurant2 = createRestaurant();
        restaurantRepository.save(restaurant1);
        restaurantRepository.save(restaurant2);

        //when
        List<Restaurant> resultList = restaurantRepository.findAll();

        //then
        assertThat(resultList.size()).isEqualTo(2);
    }

    @Test
    public void deleteTest() throws Exception{
        //given
        Restaurant restaurant = createRestaurant();
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);

        //when
        restaurantRepository.delete(savedRestaurant);

        //then
        Restaurant findRestaurant = restaurantRepository.findById(savedRestaurant.getId()).orElse(null);
        assertThat(findRestaurant).isNull();
    }

}