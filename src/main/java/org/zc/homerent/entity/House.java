package org.zc.homerent.entity;

import javax.persistence.*;

/**
 * @author FDws
 * Created on 2018/6/26 8:11
 */
@Entity
@Table
public class House {
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String email;
    @Column
    private Integer area;
    @Column
    private Integer price;
    @Column
    private Integer bed;
    @Column
    private Integer living;
    @Column
    private String image;
    @Column
    private Integer type;
    @Column
    private String message;

    public static int ON_SALE = 0;
    public static int SELL_OUT = 1;
    public static int ALL = 2;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getBed() {
        return bed;
    }

    public void setBed(Integer bed) {
        this.bed = bed;
    }

    public Integer getLiving() {
        return living;
    }

    public void setLiving(Integer living) {
        this.living = living;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "House{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", area=" + area +
                ", price=" + price +
                ", bed=" + bed +
                ", living=" + living +
                ", image='" + image + '\'' +
                ", type=" + type +
                ", message='" + message + '\'' +
                '}';
    }
}
