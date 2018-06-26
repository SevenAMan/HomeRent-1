package org.zc.homerent.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author FDws
 * Created on 2018/6/26 8:49
 */
@Entity
@Table
public class Bill {
    @Id
    private String id;
    @Column
    private String email;
    @Column
    private Long time;
    @Column
    private Long price;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", time=" + time +
                ", price=" + price +
                '}';
    }
}
