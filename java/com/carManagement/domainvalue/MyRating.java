package com.carManagement.domainvalue;

import com.google.common.base.Preconditions;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class MyRating implements Comparable<MyRating> {

    private static final long serialVersionUID = -8181325721786382189L;

    private static final int MAX_VALUE = 5;

    private static final int MIN_VALUE = 0;

    @Column(name = "rating")
    private int rating = 0;

    public MyRating() {
    }

    public MyRating(int rating) {
        checkLegalRating(rating);
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        checkLegalRating(rating);
        this.rating = rating;
    }

    private void checkLegalRating(int rating) {
        Preconditions.checkArgument(rating <= MAX_VALUE, "rating is More than MAX_VALUE : " + MAX_VALUE);
        Preconditions.checkArgument(rating >= MIN_VALUE, "rating is less than MIN_VALUE : " + MIN_VALUE);
    }

    @Override
    public int compareTo(MyRating r) {
        return rating - r.rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyRating myRating1 = (MyRating) o;

        return rating == myRating1.rating;

    }

    @Override
    public int hashCode() {
        return rating;
    }
}