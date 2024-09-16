package org.apiautomation;

public class BuilderPatternDesignPatternJava {

    // Change return type of each method as Class Type
    // "this" always points to current/calling object. Returning the same to have
    // same reference.

    public static void main(String[] args) {

        BuilderPatternDesignPatternJava bp = new BuilderPatternDesignPatternJava();
        bp.Floor1().Floor2("palak").Floor3();

    }

    public BuilderPatternDesignPatternJava Floor1() {
        System.out.println("Floor 1 is Done");
        return this;
    }

    public BuilderPatternDesignPatternJava Floor2(String param) {
        System.out.println("Floor 2 is Done");
        return this;
    }

    public BuilderPatternDesignPatternJava Floor3() {
        System.out.println("Floor 3 is Done");
        return this;
    }

}
