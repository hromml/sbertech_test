package ru.hromml.sbertech;

import org.testng.Assert;
import org.testng.annotations.Test;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import java.util.ArrayList;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;



/**
 * Created by HROM on 12.04.2017.
 */
public class CucumberTest {
    private ArrayList<String> arraystring = new ArrayList<>();   //массив для строк из сценария
    private int n;  // счетчик количества строк из сценария



    @Given("^string \"([^\"]*)\"")
    public void givenString(String str){
        arraystring.add(str);  // добавляем в массив строку
        n++; // увеличиваем счетчик
    }

    @Then("^checking this strings with file's strings")
    public void then() {

        ArrayList<String> arrayfilestrings = new ArrayList<>();   //создаем массив для строк из файла
        try {
            arrayfilestrings = (ArrayList<String>) Files.readAllLines(Paths.get("C:\\1.txt"), StandardCharsets.UTF_8);   //считываем построчно из файла в массив
        } catch (IOException e) {
            e.printStackTrace();
        }


        if (arrayfilestrings.size() != n) {   // проверяем, что количество строк в файле и в сценарии совпадают
            System.out.println("counts of this arrays is different, scenarios strings array count is " + arrayfilestrings.size() + " and file's array count is " + n); // выводим ошибку,если количество не совпадает
        } else { // иначе цикл, от 0 до количества строк
            for (int arraycount = 0; arraycount < n; arraycount++) {
                if (!equalsTest(arrayfilestrings.get(arraycount), arraystring.get(arraycount))) {  //вызываем метод equalsTest, отправляя строки с индексом = arraycount
                    System.out.println("String " + arrayfilestrings.get(arraycount) + " is not such as " + arraystring.get(arraycount)); // выводим ошибку, если метод вернул false (строки не совпадают)
                }
            }
        }
    }

 //   @Test
 //   public static void testCheckingStrings() {
 //       String str1 = "11";
 //       String str2 = "11";
 //       String str3 = "22";//      if (!equalsTest(str1, str3)) {
 //           System.out.println(str1 + " is not " + str3);
 //       } else {
 //           System.out.println(str1 + " = " + str3);
 //       }
 //   }

    private static boolean equalsTest(String string1, String string2) {  //метод проверяет на true результат метода checkingTwoStrings, которому передаем полученные строки
        try {
            Assert.assertTrue(checkingTwoStrings(string1, string2));
            return true;
        } catch (java.lang.AssertionError e) {
            return false;
        }
    }

    private static boolean checkingTwoStrings(String checkingstring1, String checkingstring2) {  // сверяем строки и возвращаем результат
        return checkingstring1.equals(checkingstring2);
    }

}