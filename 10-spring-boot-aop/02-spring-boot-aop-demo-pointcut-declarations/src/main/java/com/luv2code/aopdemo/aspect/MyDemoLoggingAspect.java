package com.luv2code.aopdemo.aspect;

import com.luv2code.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    @AfterThrowing(
            pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing = "theExc"
    )
    public void afterThrowingFindAccountsAdvice(
            JoinPoint theJointPoint, Throwable theExc
    ) {
        String method = theJointPoint.getSignature().toShortString();
        System.out.println("\n=====>>>> Executing @AfterThrowing on method: " + method);

        System.out.println("\n=====>>>> The exception is: " + theExc);
    }

    @AfterReturning(
            pointcut = "execution(* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result"
    )
    public void afterReturningFindAccountsAdvice(JoinPoint theJointPoint, List<Account> result) {
        String method = theJointPoint.getSignature().toShortString();
        System.out.println("\n=====>>>> Executing @AfterReturning on method: " + method);

        System.out.println("\n=====>>>> Result: "+ result);


        convertAccountNamesToUpperCase(result);

        System.out.println("\n=====>>>> Result: "+ result);

    }

    private void convertAccountNamesToUpperCase(List<Account> result) {

        for(Account tempAccount: result) {
            String theUpperName = tempAccount.getName().toUpperCase();

            tempAccount.setName(theUpperName);
        }
    }

    @Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint theJointPoint) {

        System.out.println("\n=====>>>> Executing @Before advice on method");

        //display the method signature
        MethodSignature methodSignature = (MethodSignature) theJointPoint.getSignature();

        System.out.println("Method: " + methodSignature);
        //display method arguments

        //get args
        Object[] args = theJointPoint.getArgs();



        //loop thru args
        for(Object tempArg : args) {
            System.out.println(tempArg);
            if(tempArg instanceof Account) {

                Account theAccount = (Account) tempArg;

                System.out.println("account name: " + theAccount.getName());
                System.out.println("account level: " + theAccount.getLevel());
            }
        }
    }



}
