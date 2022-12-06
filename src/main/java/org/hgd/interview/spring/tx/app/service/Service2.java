package org.hgd.interview.spring.tx.app.service;

import org.hgd.interview.spring.tx.app.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * 业务方法内自己 try-catch 异常导致事务不能正确回滚
 * @author hgd
 */
@Service
public class Service2 {

    @Autowired
    private AccountMapper accountMapper;

    @Transactional(rollbackFor = Exception.class)
    public void transfer(int from, int to, int amount)  {
        try {
            int fromBalance = accountMapper.findBalanceBy(from);
            if (fromBalance - amount >= 0) {
                accountMapper.update(from, -1 * amount);
                new FileInputStream("aaa");
                accountMapper.update(to, amount);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            // 解法1: 抛出RuntimeException 异常
//            throw new RuntimeException(e);
            // 解法2: 手动回滚
//            TransactionInterceptor.currentTransactionStatus().setRollbackOnly();
        }
    }
}