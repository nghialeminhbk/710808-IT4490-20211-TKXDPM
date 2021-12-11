package controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import utils.Configs;

/**
 * This class controls the flow of place rush order usecase in our AIMS project
 * @author Nghia_LM
 */

public class PlaceRushOrderController {

    public PlaceRushOrderController() {

    }

    /**
     * check media support rush order (Nghia_LM)
     * @param id id of media
     * @return
     */
    public boolean checkMediaSupport(int id) {
        //gia su co mot so media ho tro giao nhanh
        if (id == 10|| id == 11 || id == 12 || id == 13 || id == 14) return true;
        return false;
    }

    /**
     * method validate place rush order info (Nghia_LM)
     * @param info
     * @return value validate
     */
    public boolean validateRushOrderInfo(String info) {
        if (info == null || info.length() == 0) return false;
        for (char c: info.toCharArray()) {
            if (c ==' ' || c==',' || Character.isLetter(c) || c=='.') {
                continue;
            }
            else return false;
        }
        return true;
    }

    /**
     * method validate place rush order instruction (Nghia_LM)
     * @param instruction
     * @return value validate instruction
     */
    public boolean validateInstruction(String instruction) {
        if (instruction == null || instruction.length() == 0) return false;
        for (char c: instruction.toCharArray()) {
            if (c ==' ' || c==',' || Character.isLetter(c) || c=='.') {
                continue;
            }
            else return false;
        }
        return true;
    }

    /**
     * method validate time receive (Nghia_LM)
     * @param receiveTime
     * @return
     */
    public boolean validateReceiveTime(String receiveTime) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            LocalDateTime time = LocalDateTime.parse(receiveTime, formatter);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    /**
     * check location is supported place rush order (Nghia_LM)
     * @param location
     * @return boolean validate
     */
    public boolean checkLocationSupport(String location) {
        //check null or empty
        if (location == null || location.length() == 0 ) return false;

        // get provinces array support rush order
        String[] provinces_supported = Configs.PROVINCES_SUPPORT_RUSH_ORDER;

        //check support location
        for (int i=0 ;i <= provinces_supported.length - 1; i++) {
            if (provinces_supported[i] == location) return true;
        }
        return false;
    }
}