package com.carDealer.carDealer.bid.dto;

import com.carDealer.carDealer.user.dto.User;

public class Bid {

    private int amount;
    private User user;

    public Bid() {
    }

    public Bid(int amount) {
        this.amount = amount;
    }

    public Bid(int amount, User user) {
        this.amount = amount;
        this.user = user;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

