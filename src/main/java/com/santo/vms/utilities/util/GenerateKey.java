package com.santo.vms.utilities.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class GenerateKey {
    private static final char[] symbols = new char[36];

    static {
        for (int idx = 0; idx < 10; ++idx)
            symbols[idx] = (char) ('0' + idx);
        for (int idx = 10; idx < 36; ++idx)
            symbols[idx] = (char) ('a' + idx - 10);
    }

    private static final Random random = new Random();

    private static String nextString(int length) {
        final char[] buf = new char[length];
        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = symbols[random.nextInt(symbols.length)];
        return new String(buf);
    }

    public static String generateEntityId() {
        Long l = System.currentTimeMillis();
        Integer randomNumber = (int) (Math.random() * 1000);
        String entityId = nextString(4) + l.toString() + randomNumber;

        return entityId.toUpperCase();
    }

    public static String generateSecurityCode() {
        Long randomNumber = 0L;
        while (randomNumber < 10000) {
            randomNumber = (long) (Math.random() * 1000000);
        }
        return randomNumber.toString();
    }

    public static String generateSecurityCodeForConfirmation() {
        int[] digits = new int[6];
        for (int i = 0; i < digits.length; i++) {
            digits[i] = ThreadLocalRandom.current().nextInt(0, 10);
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < digits.length; i++) {
            sb.append((char) ('0' + digits[i]));
            if(i == 2) {
                sb.append('-');
            }
        }

        return sb.toString();
    }

    public static String generateNumericPolicyNumber(long policyCount, String companyCode) {
        long requiredPolicyNumber = policyCount;
        final int GEN_CHARS = 6;
        final int BASE = 10;
        char[] chars = new char[GEN_CHARS];
        String year = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));

        for (int digitPosition = GEN_CHARS - 1; digitPosition >= 0; digitPosition--) {
            chars[digitPosition] = (char) ('0' + (requiredPolicyNumber % BASE));
            requiredPolicyNumber /= BASE;
        }
        String gen = String.copyValueOf(chars);
        final String finalString = String.format("%3s%2s%6s", companyCode, year, gen);

        return finalString;
    }

    public static String generateAlphaNumbericPolicyNumber(long policyCount, String companyCode) {
        long requiredPolicyNumber = policyCount;
        final int GEN_CHARS = 7;
        char policyPrefix = 'P';
        char[] chars = new char[GEN_CHARS];
        String year = LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));

        for (int digitPosition = GEN_CHARS - 1; digitPosition >= 0; digitPosition--) {
            chars[digitPosition] = (char) ('a' + (requiredPolicyNumber % 26));
            requiredPolicyNumber /= 26;
        }
        String gen = String.copyValueOf(chars);
        final String finalString = String.format("%c%s%s%s", policyPrefix, companyCode, year, gen);

        return finalString;
    }

    public static String generatePolicyCompanyCode(long currentNumberOfCompanies) {
        //use the 100 offset to avoid 0s when creating the code, ie, company policy code should never start with a 0 for
        //presentability reasons
        long companyCount = currentNumberOfCompanies + 100;
        if (companyCount > 999) {
            throw new RuntimeException("Exceeding current supported company compacity. Only 900 companies supported so far." +
                    " Failed to generate policy company code.");
        }
        final int GEN_CHARS = 3;
        final int BASE = 10;
        char[] chars = new char[GEN_CHARS];

        for (int digitPosition = GEN_CHARS - 1; digitPosition >= 0; digitPosition--) {
            chars[digitPosition] = (char) ('0' + (companyCount % BASE));
            companyCount /= BASE;
        }

        String gen = String.copyValueOf(chars);
        final String finalString = String.format("%s", gen);

        return finalString;
    }

    public static void main(String[] args) {
        final String generateReferenceNumber = generateReferenceNumber();
        System.out.println("Generated reference number is " + generateReferenceNumber);
        System.out.println("Generated reference number without a dot is " + generateReferenceNumber.substring(1));
    }

    public static String generateToken() {

        Long now = new Date().getTime();
        Double rand = Math.random();
        System.out.println("Random value: " + rand);
        String token = rand.toString().substring(4, 7) + now.toString();
        System.out.println("Token: " + token);

        return token;
    }

    public static String generateReferenceNumber() {
        Double rand = Math.random();
        String number = rand.toString().substring(1, 10);
        return number;
    }

    public static String generatePayoutClientRef() {
        Double rand = Math.random();
        String number = rand.toString().substring(1, 5);
        return "PAYOUT" + number;
    }

    public static String generateVoucher() {

        Long now = new Date().getTime();
        String token = now.toString();
        System.out.println("Token: " + token);

        return token;
    }

    public static String generateRef() {
        long dob = 1175584320000L; // 03 April 2007, 0912hrs.
        StringBuilder id = new StringBuilder();
        id.append(System.currentTimeMillis() - dob);
        return id.toString();
    }

    public static String generateIdentification(String batchCode) {
        long dob = 1175584320000L; // 03 April 2007, 0912hrs.
        StringBuilder id = new StringBuilder();
        id.append(System.currentTimeMillis() - dob);
        return batchCode + id.toString();
    }

    public static String generateAssignmentIdentification(String batchCode) {
        long dob = 1175584320000L; // 03 April 2007, 0912hrs.
        StringBuilder id = new StringBuilder();
        id.append(System.currentTimeMillis() - dob);
        return batchCode + id.toString();
    }

    public static String generateFileId() {
        long dob = 1175584320000L; // 03 April 2007, 0912hrs.
        StringBuilder id = new StringBuilder();
        id.append(System.currentTimeMillis() - dob);
        return id.toString().substring(2, 8);
    }

    public static String generateMobilePin() {
        Double rand = Math.random();
        String number = rand.toString().substring(2, 7);
        return number;
    }
}
