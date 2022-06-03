package study.springboot.restaurant.naver;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.springboot.restaurant.naver.dto.SearchImageRequest;
import study.springboot.restaurant.naver.dto.SearchImageResponse;
import study.springboot.restaurant.naver.dto.SearchLocalRequest;
import study.springboot.restaurant.naver.dto.SearchLocalResponse;

@SpringBootTest
class NaverClientTest {

    @Autowired
    NaverClient naverClient;

    @Test
    void localSearch() {

        SearchLocalRequest search = new SearchLocalRequest();
        search.setQuery("갈비집");

        SearchLocalResponse result = naverClient.searchLocal(search);
        System.out.println("result = " + result);

    }

    @Test
    void imageSearch() {

        SearchImageRequest search = new SearchImageRequest();
        search.setQuery("갈비집");

        SearchImageResponse result = naverClient.searchImage(search);
        System.out.println("result = " + result);
    }
}