package com.springboot.spring_aop.dao;

import org.springframework.stereotype.Repository;

@Repository
public class MembershipDAOImpl implements MembershipDAO{

    private int id;


    @Override
    public boolean addSillyMember() {

        System.out.println(getClass() + ":doing my db work: Membership account");
        return true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
