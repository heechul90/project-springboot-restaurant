package study.springboot.restaurant.naver.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SearchLocalResponse {

    private String lastBuildDate;
    private int total;
    private int start;
    private int display;
    private List<SearchLocalItem> items;


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SearchLocalItem {
        private String title;
        private String link;
        private String description;
        private String category;
        private String telephone;
        private String address;
        private String roadAddress;
        private int mapx;
        private int mapy;
    }
}
