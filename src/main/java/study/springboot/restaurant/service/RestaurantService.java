package study.springboot.restaurant.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.springboot.restaurant.domain.RestaurantDto;
import study.springboot.restaurant.naver.NaverClient;
import study.springboot.restaurant.naver.dto.SearchImageRequest;
import study.springboot.restaurant.naver.dto.SearchImageResponse;
import study.springboot.restaurant.naver.dto.SearchLocalRequest;
import study.springboot.restaurant.naver.dto.SearchLocalResponse;
import study.springboot.restaurant.repository.RestaurantRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final NaverClient naverClient;

    public RestaurantDto search(String localQuery) {
        //지역검색
        SearchLocalRequest localRequest = new SearchLocalRequest();
        localRequest.setQuery(localQuery);

        SearchLocalResponse localResponse = naverClient.searchLocal(localRequest);

        if (localResponse.getTotal() > 0) {
            SearchLocalResponse.SearchLocalItem localItem = localResponse.getItems().stream().findFirst().get();

            String imageQuery = localItem.getTitle().replaceAll("<[^>]*>", "");
            SearchImageRequest imageRequest = new SearchImageRequest();
            imageRequest.setQuery(imageQuery);

            //이미지 검색
            SearchImageResponse imageResponse = naverClient.searchImage(imageRequest);
            if (imageResponse.getTotal() > 0) {
                SearchImageResponse.SearchImageItem imageItem = imageResponse.getItems().stream().findFirst().get();

                //결과 리턴
                RestaurantDto restaurantDto = new RestaurantDto();
                restaurantDto.setTitle(localItem.getTitle());
                restaurantDto.setCategory(localItem.getCategory());
                restaurantDto.setAddress(localItem.getAddress());
                restaurantDto.setReadAddress(localItem.getRoadAddress());
                restaurantDto.setHomePageLink(localItem.getLink());
                restaurantDto.setImageLink(imageItem.getLink());
                return restaurantDto;
            }
        }
        return new RestaurantDto();
    }

}
