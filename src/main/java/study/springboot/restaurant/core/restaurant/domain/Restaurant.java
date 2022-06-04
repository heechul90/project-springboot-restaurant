package study.springboot.restaurant.core.restaurant.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@SequenceGenerator(
        name = "restaurant_seq_generator",
        sequenceName = "restaurant_seq",
        initialValue = 1, allocationSize = 100
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restaurant_seq_generator")
    @Column(name = "restaurant_id")
    private Long id;

    private String title;
    private String category;
    private String address;
    private String roadAddress;
    private String homePageLink;
    private String imageLink;
    private boolean isVisit;
    private int visitCount;
    private LocalDateTime lastVisitDate;

    @Builder(builderMethodName = "createRestaurant")
    public Restaurant(String title, String category, String address, String roadAddress, String homePageLink, String imageLink, boolean isVisit, int visitCount, LocalDateTime lastVisitDate) {
        this.title = title;
        this.category = category;
        this.address = address;
        this.roadAddress = roadAddress;
        this.homePageLink = homePageLink;
        this.imageLink = imageLink;
        this.isVisit = isVisit;
        this.visitCount = visitCount;
        this.lastVisitDate = lastVisitDate;
    }

    public void changeTitle(String title) {
        this.title = title;
    }
}
