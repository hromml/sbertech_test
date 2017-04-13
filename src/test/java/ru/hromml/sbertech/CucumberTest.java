package ru.hromml.sbertech;

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
    private final EqualsClass equalsClass = new EqualsClass();
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
                if (!EqualsClass.equalsTest(arrayfilestrings.get(arraycount), arraystring.get(arraycount))) {  //вызываем метод equalsTest, отправляя строки с индексом = arraycount
                    System.out.println("String " + arrayfilestrings.get(arraycount) + " is not such as " + arraystring.get(arraycount)); // выводим ошибку, если метод вернул false (строки не совпадают)
                }
            }
        }
    }

    public EqualsClass getEqualsClass() {
        return equalsClass;
    }

}
