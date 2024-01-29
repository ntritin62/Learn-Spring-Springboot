package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Before;

public class MyCloudLogAsyncAspect {

    @Before("forDaoPackageNoGetterSetter()")
    public void logToCloudAsync() {
        System.out.println("\n=====>>>> Logging to Cloud in async fashion");
    }
}
