package uni.pu.fmi.exceptions;

import java.util.UUID;

public class ErrorConstants {
    private static final String INVESTOR_NOT_FOUND = "Investor with id: %d was not found...";
    private static final String BROKER_NOT_FOUND = "Broker with id: %d was not found...";
    private static final String STOCK_NOT_FOUND = "Stock with id: %d was not found...";
    private static final String TRADE_NOT_FOUND = "Trade with id: %d was not found...";

    private ErrorConstants() {
    }

    public static String investorNotFound(UUID id){
        return String.format(INVESTOR_NOT_FOUND, id);
    }
    public static String brokerNotFound(UUID id){
        return String.format(BROKER_NOT_FOUND, id);
    }
    public static String stockNotFound(UUID id){
        return String.format(STOCK_NOT_FOUND, id);
    }
    public static String tradeNotFound(UUID id){
        return String.format(TRADE_NOT_FOUND, id);
    }

}
