package com.horizonlabs.rebelfoods.data.local.entity;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Rajeev Ranjan -  ABPB on 19-07-2019.
 */
@Entity
public class UserEntity {

    /**
     * id : 1
     * name : Leanne Graham
     * username : Bret
     * email : Sincere@april.biz
     * address : {"street":"Kulas Light","suite":"Apt. 556","city":"Gwenborough","zipcode":"92998-3874","geo":{"lat":"-37.3159","lng":"81.1496"}}
     * phone : 1-770-736-8031 x56442
     * website : hildegard.org
     * company : {"name":"Romaguera-Crona","catchPhrase":"Multi-layered client-server neural-net","bs":"harness real-time e-markets"}
     */

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String username;
    private String email;
    @Embedded
    private AddressEntity address;
    private String phone;
    private String website;
    @Embedded
    private CompanyEntity company;

    private int isBookmarked = 0;

    public void setIsBookmarked(int isBookmarked) {
        this.isBookmarked = isBookmarked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public CompanyEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }

    public int getIsBookmarked() {
        return isBookmarked;
    }
}
