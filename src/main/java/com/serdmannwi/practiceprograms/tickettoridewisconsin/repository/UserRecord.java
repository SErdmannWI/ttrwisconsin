package com.serdmannwi.practiceprograms.tickettoridewisconsin.repository;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class UserRecord {

    @Id
    private String id;
    private String userName;
    private int userNumber;

    public UserRecord() {}

    public UserRecord(String id, String userName, int userNumber) {
        this.id = id;
        this.userName = userName;
        this.userNumber = userNumber;
    }

    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public int getUserNumber() {
        return userNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRecord that = (UserRecord) o;
        return userNumber == that.userNumber && id.equals(that.id) && userName.equals(that.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, userNumber);
    }

    @Override
    public String toString() {
        return "UserRecord{" +
            "id='" + id + '\'' +
            ", userName='" + userName + '\'' +
            ", userNumber=" + userNumber +
            '}';
    }
}
