package study.springboot.restaurant.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RestaurantDto {

    private String title;
    private String category;
    private String address;
    private String readAddress;
    private String homePageLink;
    private String imageLink;
    private boolean isVisit;
    private int visitCount;
    private LocalDateTime lastVisitDate;
}
