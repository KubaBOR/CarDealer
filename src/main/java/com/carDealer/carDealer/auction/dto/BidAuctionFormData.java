package com.carDealer.carDealer.auction.dto;

import com.carDealer.carDealer.bid.dto.Bid;
import com.carDealer.carDealer.user.dto.User;

public class BidAuctionFormData {

    private int amount;
    private User user;

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
