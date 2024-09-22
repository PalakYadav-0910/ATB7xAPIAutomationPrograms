package org.apiautomation.ex_21092024.testngexamples;

import io.qameta.allure.AllureId;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNG001 {

    @AllureId("Test_001")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that true == true")
    @Test
    public void testcas01() {
        Assert.assertEquals(true, true);
    }

    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify that true == true")
    @Test
    public void testcase02() {
        Assert.assertEquals(true, false);
    }

}
