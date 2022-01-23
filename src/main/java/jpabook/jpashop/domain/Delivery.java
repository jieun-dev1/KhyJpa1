package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
@Setter
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")

    private Long id;

    @OneToOne(mappedBy = "delivery", fetch= LAZY)
    private Order order;

    @Embedded
    private Address address;

    //Ordinal 은 1,2,3,4 숫자 Ready 면 1이 Comp면 2 (혹으 0,1) 이럴 경우, 중간에 다른 상태가 생기면 망함.
    //꼭 String 쓰세요. @Enumerated 도 쓰세요.
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status; //READY, COMP

}
