package utils;

import controller.PlaceOrderController;
import entity.order.Order;

import java.util.logging.Logger;

public class Strategy2 implements ShippingFeeCalculator{

    private static Logger LOGGER = utils.Utils.getLogger(PlaceOrderController.class.getName());

    @Override
    public int calculateShippingFee(Order order) {
        int fees = (int) (order.getShippingFees() * order.getAmount());
        return 0;
    }
}
