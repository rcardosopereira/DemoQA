package com.demoqa.ui.runners;

import com.demoqa.ui.steps.DemoQAUITestSteps;

public class DemoQAUITestRunner {
    public static void main(String[] args) {
        DemoQAUITestSteps testSteps = new DemoQAUITestSteps();
        testSteps.runUITest();
    }
}