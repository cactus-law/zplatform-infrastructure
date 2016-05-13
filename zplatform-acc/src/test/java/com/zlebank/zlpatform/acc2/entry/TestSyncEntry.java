package com.zlebank.zlpatform.acc2.entry;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

import com.zlebank.zplatform.acc.ApplicationContextUtil;
import com.zlebank.zplatform.acc.bean.TradeInfo;
import com.zlebank.zplatform.acc.bean.enums.TradeType;
import com.zlebank.zplatform.acc.service.AccEntryService;
import com.zlebank.zplatform.acc.service.entry.EntryEvent;
import com.zlebank.zplatform.commons.test.RandomUtil;

public class TestSyncEntry {

    private ApplicationContext context = ApplicationContextUtil.get();
    private AccEntryService accEntryService;
    private List<TradeInfoWrapper> tradeInfoSortedTestCases = new ArrayList<TradeInfoWrapper>();
    private final static String PAY_MEMBER_ID = "100000000000623";
    private final static String REC_MEMBER_ID = "200000000000624";
    private final static String COOP_INSTI_ID = "300000000000034";
    private final static String CHANNEL_ID = "61564773";
    private List<Map<TradeType, EntryEvent[]>> mapList = new ArrayList<Map<TradeType, EntryEvent[]>>();
    
    @Before
    public void init() {
        accEntryService = (AccEntryService) context
                .getBean("accEntryServiceImpl");
        fullFill();
    }

    @Test
    public void test() {
        for (Map<TradeType, EntryEvent[]> map : mapList) {
            tradeInfoSortedTestCases = createTestCaseOrder(map);
            String seqTxnNo = String.format("%1$ty%1$tm%1$td%1$tH%1$tM%1$tS",
                    new Date()) + RandomUtil.randomNumber(4);
            for (TradeInfoWrapper tradeInfoWrapper : tradeInfoSortedTestCases) {
                TradeInfo tradeInfo = newTrandeInfo(seqTxnNo,
                        tradeInfoWrapper.getTradeType(),
                        tradeInfoWrapper.getEntryEvent());
                try {
                    accEntryService.accEntryProcess(tradeInfo,
                            tradeInfoWrapper.getEntryEvent());
                }  catch (Exception e) {
                    e.printStackTrace();
                    Assert.fail();
                }
            }
        }
    }

    private class TradeInfoWrapper {
        private EntryEvent entryEvent;
        private TradeType tradeType;

        public TradeInfoWrapper(EntryEvent entryEvent, TradeType tradeType) {
            this.entryEvent = entryEvent;
            this.tradeType = tradeType;
        }

        public TradeType getTradeType() {
            return tradeType;
        }
        public EntryEvent getEntryEvent() {
            return entryEvent;
        } 
    }

    public TradeInfo newTrandeInfo(String txnSeqNo,
            TradeType tradeType,
            EntryEvent entryEvent) {
        TradeInfo tradeInfo = new TradeInfo();
        tradeInfo.setSplit(false);
        BigDecimal fee = new BigDecimal(0);
        BigDecimal channelFee = new BigDecimal(0);
        BigDecimal amount = new BigDecimal(0);

        BigDecimal feeRate = new BigDecimal(0.035);
        BigDecimal channelFeeRate = new BigDecimal(0.0045);
        switch (tradeType) {
            case RECHARGE :
                amount = new BigDecimal(400000);
                channelFee = amount.multiply(channelFeeRate);
                break;
            case WITHDRAW :
                amount = new BigDecimal(50000);
                fee = new BigDecimal(0);
                channelFee = amount.multiply(channelFeeRate);
                break;
            case BANKCARD_PAY :
                amount = new BigDecimal(23421);
                feeRate = new BigDecimal(0.05);
                fee = amount.multiply(feeRate);
                channelFee = amount.multiply(channelFeeRate);
                break;
            case ACCOUNT_PAY :
                amount = new BigDecimal(15957);
                feeRate = new BigDecimal(0.05);
                fee = amount.multiply(feeRate);
                channelFee = amount.multiply(channelFeeRate);
                break;
            case REFUND_ACCOUNT :
                amount = new BigDecimal(9340);
                feeRate = new BigDecimal(0.02);
                fee = amount.multiply(feeRate);
                channelFee = amount.multiply(channelFeeRate);
                break;
            case REFUND_BANKCARD :
                amount = new BigDecimal(9340);
                feeRate = new BigDecimal(0.02);
                fee = amount.multiply(feeRate);
                channelFee = amount.multiply(channelFeeRate);
                break;
            case INSTEADPAY:
                amount = new BigDecimal(76556);
                feeRate = new BigDecimal(0.03);
                fee = amount.multiply(feeRate);
                channelFee = amount.multiply(channelFeeRate);
                break;
            default :
                fee = new BigDecimal(0);
                break;
        }
        tradeInfo.setAmount(amount);
        tradeInfo.setPayMemberId(PAY_MEMBER_ID);
        tradeInfo.setPayToMemberId(REC_MEMBER_ID);
        tradeInfo.setBusiCode(tradeType.getCode());
        tradeInfo.setChannelId(CHANNEL_ID);
        tradeInfo.setTxnseqno(txnSeqNo);
        tradeInfo.setChannelFee(channelFee);

        tradeInfo.setCharge(fee);
        tradeInfo.setCoopInstCode(COOP_INSTI_ID);
        return tradeInfo;
    }

    private void fullFill() {
        
        Map<TradeType, EntryEvent[]> rechargeMap_1 = new LinkedHashMap<TradeType, EntryEvent[]>();
        rechargeMap_1.put(TradeType.RECHARGE, new EntryEvent[]{
                EntryEvent.TRADE_SUCCESS, EntryEvent.RECON_SUCCESS});
        mapList.add(rechargeMap_1);
         
        Map<TradeType, EntryEvent[]> withdrawMap_1 = new LinkedHashMap<TradeType, EntryEvent[]>();
        withdrawMap_1.put(TradeType.WITHDRAW, new EntryEvent[]{
                EntryEvent.AUDIT_APPLY, EntryEvent.TRADE_SUCCESS,
                EntryEvent.RECON_SUCCESS});
        mapList.add(withdrawMap_1);
        Map<TradeType, EntryEvent[]> withdrawMap_2 = new LinkedHashMap<TradeType, EntryEvent[]>();
        withdrawMap_2.put(TradeType.WITHDRAW, new EntryEvent[]{
                EntryEvent.AUDIT_APPLY, EntryEvent.TRADE_FAIL});
        mapList.add(withdrawMap_2);
       Map<TradeType, EntryEvent[]> withdrawMap_3 = new LinkedHashMap<TradeType, EntryEvent[]>();
        withdrawMap_3.put(TradeType.WITHDRAW, new EntryEvent[]{
                EntryEvent.AUDIT_APPLY, EntryEvent.AUDIT_REJECT});
        mapList.add(withdrawMap_3);

        
        Map<TradeType, EntryEvent[]> bankCardPayMap_1 = new LinkedHashMap<TradeType, EntryEvent[]>();
        bankCardPayMap_1.put(TradeType.BANKCARD_PAY, new EntryEvent[]{EntryEvent.TRADE_SUCCESS, EntryEvent.RECON_SUCCESS,
                EntryEvent.SETTED});
        mapList.add(bankCardPayMap_1);
        
        Map<TradeType, EntryEvent[]> accountPayMap_1 = new LinkedHashMap<TradeType, EntryEvent[]>();
        accountPayMap_1.put(TradeType.ACCOUNT_PAY, new EntryEvent[]{EntryEvent.TRADE_SUCCESS,EntryEvent.SETTED});
        mapList.add(accountPayMap_1);
        
        
        /*Map<TradeType, EntryEvent[]> refundAccount_1 = new LinkedHashMap<TradeType, EntryEvent[]>();
        refundAccount_1.put(TradeType.REFUND_ACCOUNT, new EntryEvent[]{EntryEvent.AUDIT_APPLY,EntryEvent.AUDIT_PASS});
        mapList.add(refundAccount_1);
        
        Map<TradeType, EntryEvent[]> refundAccount_2 = new LinkedHashMap<TradeType, EntryEvent[]>();
        refundAccount_2.put(TradeType.REFUND_ACCOUNT, new EntryEvent[]{EntryEvent.AUDIT_APPLY,EntryEvent.AUDIT_REJECT});
        mapList.add(refundAccount_2);
        
        
        Map<TradeType, EntryEvent[]> refundBankCard_1 = new LinkedHashMap<TradeType, EntryEvent[]>();
        refundBankCard_1.put(TradeType.REFUND_BANKCARD, new EntryEvent[]{EntryEvent.AUDIT_APPLY,EntryEvent.TRADE_SUCCESS,EntryEvent.RECON_SUCCESS});
        mapList.add(refundBankCard_1);
        
        Map<TradeType, EntryEvent[]> refundBankCard_2 = new LinkedHashMap<TradeType, EntryEvent[]>();
        refundBankCard_2.put(TradeType.REFUND_BANKCARD, new  EntryEvent[]{EntryEvent.AUDIT_APPLY,EntryEvent.TRADE_FAIL});
        mapList.add(refundBankCard_2);
        
        Map<TradeType, EntryEvent[]> refundBankCard_3 = new LinkedHashMap<TradeType, EntryEvent[]>();
        refundBankCard_3.put(TradeType.REFUND_BANKCARD, new EntryEvent[]{EntryEvent.AUDIT_APPLY,EntryEvent.AUDIT_REJECT});
        mapList.add(refundBankCard_3);*/
        
        Map<TradeType, EntryEvent[]> insteadPay_1 = new LinkedHashMap<TradeType, EntryEvent[]>();
        insteadPay_1.put(TradeType.INSTEADPAY, new EntryEvent[]{EntryEvent.AUDIT_APPLY,EntryEvent.TRADE_SUCCESS,EntryEvent.RECON_SUCCESS});
        mapList.add(insteadPay_1); 
        
        Map<TradeType, EntryEvent[]> insteadPay_2 = new LinkedHashMap<TradeType, EntryEvent[]>();
        insteadPay_2.put(TradeType.INSTEADPAY, new EntryEvent[]{EntryEvent.AUDIT_APPLY,EntryEvent.TRADE_FAIL});
        mapList.add(insteadPay_2);
        
        Map<TradeType, EntryEvent[]> insteadPay_3 = new LinkedHashMap<TradeType, EntryEvent[]>();
        insteadPay_3.put(TradeType.INSTEADPAY, new EntryEvent[]{EntryEvent.AUDIT_APPLY,EntryEvent.AUDIT_REJECT});
        mapList.add(insteadPay_3);
    }

    private List<TradeInfoWrapper> createTestCaseOrder(Map<TradeType, EntryEvent[]> map) {
        List<TradeInfoWrapper> TradeInfoSortedTestCases = new ArrayList<TestSyncEntry.TradeInfoWrapper>();
        for (TradeType tradeType : map.keySet()) {
            for (EntryEvent entryEvent : map.get(tradeType)) {
                TradeInfoWrapper chargeTradeSuccess = new TradeInfoWrapper(
                        entryEvent, tradeType);
                TradeInfoSortedTestCases.add(chargeTradeSuccess);
            }
        }
        return TradeInfoSortedTestCases;
    }
}
