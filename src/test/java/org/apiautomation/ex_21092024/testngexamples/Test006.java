package org.apiautomation.ex_21092024.testngexamples;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Test006 {

    @Test(groups = {"sanity", "qa", "preprod"})
    public void sanityRun(){
        System.out.println("Sanity");
        System.out.println("QA");
        Assert.assertTrue(true);
    }

    @Test(groups = {"qa", "preprod", "reg"})
    public void regRun(){
        System.out.println("Reg");
        Assert.assertTrue(false);
    }

    @Test(groups = {"dev", "stage"})
    public void smokeRun(){
        System.out.println("Smoke");
        Assert.assertTrue(false);
    }

}
