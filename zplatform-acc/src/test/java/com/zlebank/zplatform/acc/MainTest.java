/* 
 * MainTest.java
 * 
 * version 1.0
 *
 * 2015年8月21日 
 * 
 * Copyright (c) 2015,zlebank.All rights reserved.
 * 
 */
package com.zlebank.zplatform.acc;


/**
 * 
 * 测试类入口
 *
 * @author Luxiaoshuai
 * @version
 * @date 2015年8月21日 上午10:52:09
 * @since
 */
public class MainTest {

    /**
     * @参数说明：args[]
     * @01：科目新增
     * @02：科目修改
     * @03：科目查询
     * @04：科目删除
     * @11：账户开户
     * @12：账户冻结
     * @13：账户解除冻结
     * @14：账户止入
     * @15：账户解除止入
     * @16：账户止出
     * @17：账户解除止出
     * @param args
     */
    public static void main(String args[]) {
        switch (Integer.valueOf(args[0])) {
            case 1:
                //new BaseTest().test();
                break;
            default :
                System.out.println("-------那啥，你输错了，找不着这个代码.-------");
                break;
        }

//        String exp  = "(c)";exp=exp.toUpperCase();
//        JEP jep = new JEP();
//        jep.addVariable("A", 1d);
//        jep.addVariable("B", 1d);
//        jep.addVariable("C", 3d);
//        jep.parseExpression(exp);
//        DoubleStack stack = new DoubleStack();
//        try {
//            System.out.println(jep.getValue(stack));
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
    }
}
