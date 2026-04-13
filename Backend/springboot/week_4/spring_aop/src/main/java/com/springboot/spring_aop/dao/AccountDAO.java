package com.springboot.spring_aop.dao;

import java.util.List;

public interface AccountDAO {

    void addAccount();

    List<Account> findAccount();

    List<Account> findAccount(boolean trigger);
}
