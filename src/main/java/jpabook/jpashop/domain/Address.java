package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;


@Embeddable
@Getter
//값 자체가 immutable 하게 설계되어야함. 생성할 때만 값 제공하는 것. setter 를 제공안하는 것.
//이런 타입을 JPA 가 생성할 때, 리플렉션/프록시 같은 기술을 사용해야할 때가 많다. 기본 생성자가 없으면 그게 안되므로 만들어줘야 한다.
public class Address {

    private String city;
    private String street;
    private String zipcode;

    //함부로 new 로 생성하지 않도록.
    protected Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
