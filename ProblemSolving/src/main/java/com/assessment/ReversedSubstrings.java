package com.assessment;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReversedSubstrings {
    public String reverseSubstring(String inp) throws Exception {

        if (isInvalid(inp)){
            throw new Exception("Wrong format");
        }

        StringBuilder sb = new StringBuilder(inp);
        String startCh = "(";
        String endCh = ")";

        int sIndex = inp.indexOf(startCh);
        while (sIndex != -1) {
            int eIndex = inp.indexOf(endCh, sIndex + 1);
            String reversedStr = new StringBuilder(inp.substring(sIndex + 1, eIndex)).reverse().toString();
            sb.replace(sIndex + 1, eIndex, reversedStr);
            sIndex = inp.indexOf(startCh, sIndex + 1);
        }

        return sb.toString();
    }

    private boolean isInvalid(String inp){
        String invalidCh="[^a-z\\(\\)]";
        Matcher matcher = Pattern.compile(invalidCh).matcher(inp);
        return matcher.find();
    }
}
