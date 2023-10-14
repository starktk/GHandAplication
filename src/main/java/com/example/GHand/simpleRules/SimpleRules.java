package com.example.GHand.simpleRules;

public class SimpleRules {

    public Boolean verifyString(String stringToVerify) {
        return stringToVerify.isBlank();
    }

    public Boolean verifyNumber(Integer numberToVerify) {
         if (numberToVerify <= 0) return false;
         return true;
    }
}
