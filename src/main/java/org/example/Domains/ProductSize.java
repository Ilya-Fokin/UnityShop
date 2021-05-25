package org.example.Domains;

import javax.persistence.*;

@Entity
@Table(name = "product_size")
public class ProductSize {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "product_id")
    private Long productID;

    @Column(name = "size_id")
    private Long sizeID;

    @Column(name = "count")
    private int count;

    public ProductSize() {}
    public ProductSize(Long productID, Long sizeID, int count) {
        this.productID = productID;
        this.sizeID = sizeID;
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductID() {
        return productID;
    }

    public void setProductID(Long productID) {
        this.productID = productID;
    }

    public Long getSizeID() {
        return sizeID;
    }

    public void setSizeID(Long sizeID) {
        this.sizeID = sizeID;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
