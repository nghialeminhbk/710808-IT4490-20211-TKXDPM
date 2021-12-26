package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Logger;

import entity.cart.Cart;
import entity.cart.CartMedia;
import entity.invoice.Invoice;
import entity.order.Order;
import entity.order.OrderMedia;
import entity.order.RushInfo;

/**
 * This class controls the flow of place order usecase in our AIMS project
 * @author Nghia_LM
 */
public class PlaceOrderController extends BaseController{

    /**
     * Just for logging purpose
     */
    private static Logger LOGGER = utils.Utils.getLogger(PlaceOrderController.class.getName());

    /**
     * This method checks the avalibility of product when user click PlaceOrder button
     * @throws SQLException
     */
    public void placeOrder() throws SQLException{
        Cart.getCart().checkAvailabilityOfProduct();
    }

    /**
     * This method creates the new Order based on the Cart
     * @return Order
     * @throws SQLException
     */
    public Order createOrder() throws SQLException{
        Order order = new Order();
        for (Object object : Cart.getCart().getListMedia()) {
            CartMedia cartMedia = (CartMedia) object;
            OrderMedia orderMedia = new OrderMedia(cartMedia.getMedia(), 
                                                   cartMedia.getQuantity(), 
                                                   cartMedia.getPrice());    
            order.getlstOrderMedia().add(orderMedia);
        }
        return order;
    }

    /**
     * This method creates the new Invoice based on order
     * @param order
     * @return Invoice
     */
    public Invoice createInvoice(Order order) {
        return new Invoice(order);
    }

    /**
     * This method takes responsibility for processing the shipping info from user
     * @param info
     * @throws InterruptedException
     * @throws IOException
     */
    public void processDeliveryInfo(HashMap info) throws InterruptedException, IOException{
        LOGGER.info("Process Delivery Info");
        LOGGER.info(info.toString());
        validateDeliveryInfo(info);
    }
    
    /**
   * The method validates the info
   * @param info
   * @throws InterruptedException
   * @throws IOException
   */
    public void validateDeliveryInfo(HashMap<String, String> info) throws InterruptedException, IOException{
    	
    }
    /**
     * validate phoneNumber (Nghia_LM)
     * @param phoneNumber phoneNumber of user
     * @return result validate phoneNumber
     */
    public boolean validatePhoneNumber(String phoneNumber) {
        // Nghia_LM 20183960
        // check phoneNumber is null
        if (phoneNumber == null) return false;
        //check phoneNumber has 10 digits
        if (phoneNumber.length() != 10) return false;
        //check the phoneNumber start with 0
        if  (!phoneNumber.startsWith("0")) return false;
        //check phoneNumber contain only number
        try {
            Integer.parseInt(phoneNumber);
        }
        catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * validate name of customer (Nghia_LM)
     * @param name name of customer
     * @return result validate
     */
    public boolean validateName(String name) {
        // Nghia_LM
        //check emty name
        if (name == null || name.length() == 0) return false;

        //check name only have letter or space
        for (char c : name.toCharArray()) {
            if (c == ' ' || Character.isLetter(c)) {
                continue;
            }
            else return false;
        }
        return true;
    }

    /**
     * validate address of receive product (Nghia_LM)
     * @param address address of customer
     * @return
     */
    public boolean validateAddress(String address) {
        // Nghia_LM
        // check emty name
        if (address == null || address.length() == 0) return false;

        //check name only have letter or space or , or dot
        for (char c : address.toCharArray()) {
            if (c ==' ' || Character.isLetter(c) || c == ',' || c == '.') {
                continue;
            }
            else return false;
        }
        return true;
    }
    

    /**
     * This method calculates the shipping fees of order
     * @param order
     * @return shippingFee
     */
    public int calculateShippingFee(Order order){

        return new ShippingStrategyWithWeight().calculateShippingFee(order);
    }

    /**
     * add rush info to order
     *
     * @param order    order to rush
     * @param rushInfo rush info
     */
    public void addRushInfo(Order order, RushInfo rushInfo){
        order.setRushInfo(rushInfo);
    }
}
