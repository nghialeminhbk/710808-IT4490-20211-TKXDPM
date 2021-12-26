package controller;

import java.util.Random;
import java.util.logging.Logger;

import entity.order.Order;

public class OriginalShippingStrategy implements ShippingFeeCalculator{

    private static Logger LOGGER = utils.Utils.getLogger(PlaceOrderController.class.getName());

    /**
     * Shipping fees (Nghia_LM)
     *
     * @param order order to be calculated
     * @return
     */
    @Override
    public int calculateShippingFee(Order order) {
        Random rand = new Random();
        int fees, extras = order.getRushInfo() == null ? 1 : 3;

        if(extras > 1){
            LOGGER.info("Plus rush extra to shipping fees");
        }

        fees = (int) (((rand.nextFloat() * 10) / 100) * order.getAmount() * extras);


        LOGGER.info("Order Amount: " + order.getAmount() + " -- Shipping Fees: " + fees);
        return fees;
    }
}
