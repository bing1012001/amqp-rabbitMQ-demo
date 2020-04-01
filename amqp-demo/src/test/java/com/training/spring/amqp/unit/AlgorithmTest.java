package com.training.spring.amqp.unit;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Slf4j
public class AlgorithmTest {

    @Test
    public void test1() {

        int[] arr = new int[] {5,7,9,11,24};

        for(Integer i : arr) {
            log.info(i.toString());
        }

    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode calculate(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0);
        ListNode curr = result;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int x  = ((l1 != null) ? l1.val : 0) + ((l2 != null) ? l2.val: 0) + carry;
            carry = x / 10;
            curr.next = new ListNode(x % 10);
            curr = curr.next;
            if(l1 != null) l1 = l1.next;
            if(l2 != null) l2 = l2.next;
        }
        if (carry > 0) curr.next = new ListNode(carry);
        curr = curr.next;

        return result.next;
    }

    @Test
    public void test2() {
        String s = "abcabcbb";
        findLongest(s);
    }

    public int findLongest(String s) {
        int length = 0;
        int i = 0, j = 0;
        Set<Character> set = new HashSet<>();
        while (i < s.length() && j < s.length()) {
            if(!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                length = Math.max(length, set.size());
            } else {
                log.info(set.toString());
                set.remove(s.charAt(i++));
            }
        }
        return length;
    }

    @Test
    public void test3() {
        String s =  "abba";
        String ss = longestPalindrome(s);
        log.info(ss);
    }

    int maxLen = 0;
    int lo = 0;
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2)
            return s;

        for (int i = 0; i < len-1; i++) {
            extendPalindrome(s, i, i);  //assume odd length, try to extend Palindrome as possible
            extendPalindrome(s, i, i+1); //assume even length.
        }
        return s.substring(lo, lo + maxLen);
    }

    private void extendPalindrome(String s, int j, int k) {
        while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
            j--;
            k++;
        }
        if (maxLen < k - j - 1) {
            lo = j + 1;
            maxLen = k - j - 1;
        }
    }

}
