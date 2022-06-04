package study.springboot.restaurant.core.naver.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.springboot.restaurant.core.naver.dto.SearchImageRequest;
import study.springboot.restaurant.core.naver.dto.SearchImageResponse;
import study.springboot.restaurant.core.naver.dto.SearchLocalRequest;
import study.springboot.restaurant.core.naver.dto.SearchLocalResponse;
import study.springboot.restaurant.core.naver.client.NaverClient;

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