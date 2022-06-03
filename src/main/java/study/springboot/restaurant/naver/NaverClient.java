package study.springboot.restaurant.naver;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import study.springboot.restaurant.naver.dto.SearchImageRequest;
import study.springboot.restaurant.naver.dto.SearchImageResponse;
import study.springboot.restaurant.naver.dto.SearchLocalRequest;
import study.springboot.restaurant.naver.dto.SearchLocalResponse;

import java.net.URI;

@Component
public class NaverClient {

    @Value("${naver.client.id}")
    private String naverClientId;

    @Value("${naver.client.secret}")
    private String naverClientSecret;

    @Value(("${naver.url.search.local}"))
    private String naverLocalSearchUrl;

    @Value(("${naver.url.search.image}"))
    private String naverImageSearchUrl;

    public SearchLocalResponse searchLocal(SearchLocalRequest request) {
        URI uri = UriComponentsBuilder
                .fromUriString(naverLocalSearchUrl)
                .queryParams(request.toMultiValueMap())
                .encode()
                .build()
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", naverClientId);
        headers.set("X-Naver-Client-Secret", naverClientSecret);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);
        ParameterizedTypeReference<SearchLocalResponse> responseType = new ParameterizedTypeReference<>() {};

        ResponseEntity<SearchLocalResponse> reponseEntity = new RestTemplate().exchange(uri, HttpMethod.GET, httpEntity, responseType);
        return reponseEntity.getBody();
    }

    public SearchImageResponse searchImage(SearchImageRequest request) {

        URI uri = UriComponentsBuilder
                .fromUriString(naverImageSearchUrl)
                .queryParams(request.toMultiValueMap())
                .encode()
                .build()
                .toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", naverClientId);
        headers.set("X-Naver-Client-Secret", naverClientSecret);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> httpEntity = new HttpEntity<>(headers);
        ParameterizedTypeReference<SearchImageResponse> responseType = new ParameterizedTypeReference<>() {};

        ResponseEntity<SearchImageResponse> reponseEntity = new RestTemplate().exchange(uri, HttpMethod.GET, httpEntity, responseType);
        return reponseEntity.getBody();
    }


}
