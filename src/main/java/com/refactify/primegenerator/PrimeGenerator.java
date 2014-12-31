package com.refactify.primegenerator;

public class PrimeGenerator {

    private static boolean[] isCrossed;
    private static int[] result;


    public static int[] generatePrimes(final int maxValue) {
        if(maxValue < 2) {
            return new int[0];
        }
        else {
            initializeArrayOfIntegers(maxValue);
            crossOutMultiples();
            putUncrossedIntegersIntoResult();
            return result;
        }
    }

    private static void initializeArrayOfIntegers(final int maxValue) {
        isCrossed = new boolean[maxValue + 1];
        for (int i = 2; i < isCrossed.length; i++)
            isCrossed[i] = false;
    }

    private static void crossOutMultiples() {
        int maxPrimeFactor = calculateMaxPrimeFactor();
        for (int i = 2; i < maxPrimeFactor; i++) {
            if(notCrossed(i)) {
                crossOutMultiplesOf(i);
            }
        }
    }

    private static int calculateMaxPrimeFactor() {
        return (int)Math.sqrt(isCrossed.length) + 1;
    }

    private static boolean notCrossed(final int i) {
        return isCrossed[i] == false;
    }

    private static void crossOutMultiplesOf(final int i) {
        for (int multiple = 2 * i; multiple < isCrossed.length; multiple += i) {
            isCrossed[multiple] = true;
        }
    }

    private static void putUncrossedIntegersIntoResult() {
        result = new int[numberOfUncrossedIntegers()];
        for (int i = 2, j = 0; i < isCrossed.length; i++) {
            if(notCrossed(i)) {
                result[j++] = i;
            }
        }
    }

    private static int numberOfUncrossedIntegers() {
        int count = 0;
        for (int i = 2; i < isCrossed.length; i++) {
            if(notCrossed(i))
                count++;
        }
        return count;
    }
}
