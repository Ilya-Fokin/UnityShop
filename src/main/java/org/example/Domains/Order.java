package org.example.Domains;

import javax.persistence.*;

@Entity
@Table(name = "users_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "basket_id")
    private Long basketId;

    public Order() {}
    public Order(Long user_id, Long basket_id) {
        this.userId = user_id;
        this.basketId = basket_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long user_id) {
        this.userId = user_id;
    }

    public Long getBasketId() {
        return basketId;
    }

    public void setBasketId(Long basket_id) {
        this.basketId = basket_id;
    }
}
