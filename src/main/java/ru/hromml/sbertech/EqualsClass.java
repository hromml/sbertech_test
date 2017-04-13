package ru.hromml.sbertech;

import org.testng.Assert;

/**
 * Created by hramkov on 13.04.2017.
 */
public class EqualsClass {
    protected static boolean equalsTest(String string1, String string2) {  //метод проверяет на true результат метода checkingTwoStrings, которому передаем полученные строки
        try {
            Assert.assertTrue(checkingTwoStrings(string1, string2));
            return true;
        } catch (AssertionError e) {
            return false;
        }
    }

    private static boolean checkingTwoStrings(String checkingstring1, String checkingstring2) {  // сверяем строки и возвращаем результат
        return checkingstring1.equals(checkingstring2);
    }
}
