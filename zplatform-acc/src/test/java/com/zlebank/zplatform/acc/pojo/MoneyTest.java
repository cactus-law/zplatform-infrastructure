/* 
 * MoneyTest.java  
 * 
 * version 1.0
 *
 * 2015年8月25日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * Class Description
 *
 * @author yangying
 * @version
 * @date 2015年8月25日 下午2:34:58
 * @since
 */
public class MoneyTest {

    @Test
    public void test() {
        List<Money> testMoneys = new ArrayList<Money>();

        Money money_a = Money.valueOf(103.5);
        Money money_b = Money.yuanValueOf(0.2);
        Money money_c = Money.yuanValueOf(0.11);
        Money money_d = Money.yuanValueOf(0.179);
        
        Money money_e = money_a.plus(money_b);
        Money money_f = money_c.plus(money_d);
        
        Money money_g = money_a.minus(money_b);
        Money money_h = money_c.minus(money_d);
        
        Money money_i = money_a.multiply(money_b);
        Money money_j = money_c.multiply(money_d);
        
        Money money_k = money_a.divis(money_b);
        Money money_l = money_c.divis(money_d);
        
        testMoneys.add(money_a);
        testMoneys.add(money_b);
        testMoneys.add(money_c);
        testMoneys.add(money_d);
        
        testMoneys.add(money_e);
        testMoneys.add(money_f);
           
        testMoneys.add(money_g);
        testMoneys.add(money_h);
        
        testMoneys.add(money_i);
        testMoneys.add(money_j);
        
        testMoneys.add(money_k);
        testMoneys.add(money_l); 
        
        for (Money money : testMoneys) {
            System.out.println(money.toString());
        }
        Money money_z = Money.yuanValueOf(0);
        System.out.println(money_z.equalsZero());
        
        Money money_m = Money.valueOf(200);
        Money money_n = Money.valueOf(-0);
        Money money_o = Money.valueOf(new BigDecimal(-1));
        System.out.println(money_o.toString());
        Money money_p = Money.valueOf(-200);
        Assert.assertTrue(money_m.compareTo(Money.ZERO)>0);
        Assert.assertTrue(money_n.compareTo(Money.ZERO)==0);
        Assert.assertTrue(money_o.compareTo(Money.ZERO)<0);
        Assert.assertTrue(money_p.compareTo(Money.ZERO)<0);
    }
}
