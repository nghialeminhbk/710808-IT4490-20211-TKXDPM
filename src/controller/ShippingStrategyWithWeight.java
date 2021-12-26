package controller;

import entity.order.Order;

import java.util.Random;
import java.util.logging.Logger;

public class ShippingStrategyWithWeight implements ShippingFeeCalculator{
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

        // Random cac chi so
        int weight = (int) (((rand.nextFloat() * 10) / 100) * order.getAmount());
        int length = (int) (((rand.nextFloat() * 10) / 100) * order.getAmount());
        int width = (int) (((rand.nextFloat() * 10) / 100) * order.getAmount());
        int height = (int) (((rand.nextFloat() * 10) / 100) * order.getAmount());

        int alt = (length * width * height) / 6000;
        int fees = alt + weight;

        LOGGER.info("Order Amount: " + order.getAmount() + " -- Shipping Fees: " + fees);
        return fees;
    }
}
