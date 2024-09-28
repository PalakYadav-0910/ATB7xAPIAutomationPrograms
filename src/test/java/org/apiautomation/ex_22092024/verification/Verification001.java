package org.apiautomation.ex_22092024.verification;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Verification001 {

    @Test
    public void test_verify(){

        String responseName = "Palak";
        Assert.assertEquals(responseName, "Palak");

    }
}
