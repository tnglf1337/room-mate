package com.roommate.architecture;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;

import com.sun.tools.javac.Main;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@AnalyzeClasses(packagesOf = Main.class)
public class ArchitectureTests {

    @ArchTest
    static final ArchRule test_01 = classes()
            .that()
            .areAnnotatedWith(Repository.class)
            .should()
            .resideInAnyPackage("..database..");


    @ArchTest
    static final ArchRule test_02 = classes()
            .that()
            .areAnnotatedWith(Repository.class)
            .should()
            .haveSimpleNameNotEndingWith("Repository");

    @ArchTest
    static final ArchRule test_03 = classes()
            .that()
            .resideInAPackage("..applicationService..")
            .should()
            .beAnnotatedWith(Service.class);

    @ArchTest
    static final ArchRule test_04 = classes()
            .that()
            .areAnnotatedWith(Service.class)
            .should()
            .haveSimpleNameEndingWith("Service");

    @ArchTest
    static final ArchRule test_05 = fields()
            .that()
            .areDeclaredInClassesThat()
            .areAnnotatedWith(Controller.class)
            .should()
            .bePrivate();

    @ArchTest
    static final ArchRule test_06 = noClasses()
            .that()
            .areAnnotatedWith(Controller.class)
            .should()
            .dependOnClassesThat()
            .areAnnotatedWith(Controller.class);



}
