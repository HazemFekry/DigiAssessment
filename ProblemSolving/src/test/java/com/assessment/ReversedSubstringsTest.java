package com.assessment;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReversedSubstringsTest {
    ReversedSubstrings reversedSubstrings;

    @BeforeEach
    void setup() {
        reversedSubstrings = new ReversedSubstrings();
    }

    @Test
    void TestReverseStringFirstInput() throws Exception {
        
        String input ="abd(jnb)asdf";
        
        String result = reversedSubstrings.reverseSubstring(input);
        
        Assertions.assertEquals(result,"abd(bnj)asdf");

    }

    @Test
    void TestReverseStringSecondInput() throws Exception {
        
        String input ="abdjnbasdf";
        
        String result = reversedSubstrings.reverseSubstring(input);
        
        Assertions.assertEquals(result,"abdjnbasdf");

    }

    @Test
    void TestReverseStringThirdInput() throws Exception {
        
        String input ="dd(df)a(ghhh)";
        
        String result = reversedSubstrings.reverseSubstring(input);
        
        Assertions.assertEquals(result,"dd(fd)a(hhhg)");

    }

    @Test
    void TestReverseStringLongInput() throws Exception {

        String input ="dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)abd(jnb)asdfabd(jnb)asdfabd(jnb)asdfabd(jnb)asdfabdjnbasdfabdjnbasdfabdjnbasdfdd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)abd(jnb)asdfabd(jnb)asdfabd(jnb)asdfabd(jnb)asdfabdjnbasdfabdjnbasdfabdjnbasdfdd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)abd(jnb)asdfabd(jnb)asdfabd(jnb)asdfabd(jnb)asdfabdjnbasdfabdjnbasdfabdjnbasdfdd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)abd(jnb)asdfabd(jnb)asdfabd(jnb)asdfabd(jnb)asdfabdjnbasdfabdjnbasdfabdjnbasdfdd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)abd(jnb)asdfabd(jnb)asdfabd(jnb)asdfabd(jnb)asdfabdjnbasdfabdjnbasdfabdjnbasdfdd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)abd(jnb)asdfabd(jnb)asdfabd(jnb)asdfabd(jnb)asdfabdjnbasdfabdjnbasdfabdjnbasdfdd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)abd(jnb)asdfabd(jnb)asdfabd(jnb)asdfabd(jnb)asdfabdjnbasdfabdjnbasdfabdjnbasdfdd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)abd(jnb)asdfabd(jnb)asdfabd(jnb)asdfabd(jnb)asdfabdjnbasdfabdjnbasdfabdjnbasdfdd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)a(ghhh)dd(df)";

        String result = reversedSubstrings.reverseSubstring(input);
        
        String expected ="dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)abd(bnj)asdfabd(bnj)asdfabd(bnj)asdfabd(bnj)asdfabdjnbasdfabdjnbasdfabdjnbasdfdd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)abd(bnj)asdfabd(bnj)asdfabd(bnj)asdfabd(bnj)asdfabdjnbasdfabdjnbasdfabdjnbasdfdd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)abd(bnj)asdfabd(bnj)asdfabd(bnj)asdfabd(bnj)asdfabdjnbasdfabdjnbasdfabdjnbasdfdd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)abd(bnj)asdfabd(bnj)asdfabd(bnj)asdfabd(bnj)asdfabdjnbasdfabdjnbasdfabdjnbasdfdd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)abd(bnj)asdfabd(bnj)asdfabd(bnj)asdfabd(bnj)asdfabdjnbasdfabdjnbasdfabdjnbasdfdd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)abd(bnj)asdfabd(bnj)asdfabd(bnj)asdfabd(bnj)asdfabdjnbasdfabdjnbasdfabdjnbasdfdd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)abd(bnj)asdfabd(bnj)asdfabd(bnj)asdfabd(bnj)asdfabdjnbasdfabdjnbasdfabdjnbasdfdd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)abd(bnj)asdfabd(bnj)asdfabd(bnj)asdfabd(bnj)asdfabdjnbasdfabdjnbasdfabdjnbasdfdd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)a(hhhg)dd(fd)";
        Assertions.assertEquals(result,expected);
    }

    @Test
    void TestReverseStringInvalidInput(){
        String input ="ABCD";

        Assertions.assertThrows(Exception.class,()->{reversedSubstrings.reverseSubstring(input);},"wong input");
    }

    @Test
    void TestReverseStringEmptyInput() throws Exception {
        String input ="";
        String expected ="";


        String result = reversedSubstrings.reverseSubstring(input);

        Assertions.assertEquals(result,expected);    }
}
