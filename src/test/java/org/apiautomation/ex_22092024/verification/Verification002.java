package org.apiautomation.ex_22092024.verification;

import static io.qameta.allure.SeverityLevel.CRITICAL;
import static org.assertj.core.api.Assertions.*;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import java.io.File;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Verification002 {


    @Description("This test attempts to log into the website using a login and a password. Fails if any error happens.\n\nNote that this test does not test 2-Factor Authentication.")
    @Severity(CRITICAL)
    @Owner("John Doe")
    @Link(name = "Website", url = "https://dev.example.com/")
    @Issue("AUTH-123")
    @TmsLink("TMS-456")
    @Test
    public void test_verify_assertj(){

        String name = "Palak";
        assertThat(name).isEqualTo("Palak").isNotEmpty().isNotNull();

        List<String> names = Arrays.asList("John", "Jane", "Doe", "Nick");
        assertThat(names).hasSize(4).isNotNull();

        LocalDate date = LocalDate.now();
        System.out.println(date);

        assertThat(date)
                .isAfterOrEqualTo(LocalDate.of(2021, 7, 01))
                .isBeforeOrEqualTo(LocalDate.of(2024, 9, 28))
                .isBetween(

                        LocalDate.of(2022, 9, 12),
                        LocalDate.of(2024, 9, 28)
                );


        File file = new File("TestData.json");
        assertThat(file).exists().isFile().canRead();


        HashMap<String, Integer> ages = new HashMap<>();
        ages.put("John", 30);
        ages.put("Nick", 40);

        assertThat(ages).hasSize(2).containsEntry("John", 30)
                .doesNotContainValue(45);

    }
}
