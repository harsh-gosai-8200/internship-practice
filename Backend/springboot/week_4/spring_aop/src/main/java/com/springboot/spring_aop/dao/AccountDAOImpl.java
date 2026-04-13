package com.springboot.spring_aop.dao;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountDAOImpl implements AccountDAO{

    @Override
    public void addAccount() {

        System.out.println(getClass() + " : doing my db work-add account");
    }

    @Override
    public List<Account> findAccount() {
        List<Account> myAccount = new ArrayList<>();
        Account account1 = new Account("harsh", "gold");
        Account account2 = new Account("jeet", "silver");
        Account account3 = new Account("rudra", "bronze");
        myAccount.add(account1);
        myAccount.add(account2);
        myAccount.add(account3);
        return myAccount;
    }

    @Override
    public List<Account> findAccount(boolean trigger) {
        if(trigger){
            throw new RuntimeException("No sout for you !!");
        }
        List<Account> myAccount = new ArrayList<>();
        Account account1 = new Account("harsh", "gold");
        Account account2 = new Account("jeet", "silver");
        Account account3 = new Account("rudra", "bronze");
        myAccount.add(account1);
        myAccount.add(account2);
        myAccount.add(account3);
        return myAccount;
    }
}
