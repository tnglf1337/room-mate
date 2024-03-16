package com.roommate.architecture;

import static com.tngtech.archunit.library.Architectures.onionArchitecture;

import com.sun.tools.javac.Main;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

@AnalyzeClasses(packagesOf = Main.class)
public class OnionTest {

    @ArchTest
    static final ArchRule onionTest = onionArchitecture()
            .domainModels("roommate.domain.model..")
            .applicationServices("roommate.applicationService..")
            .adapter("web", "roommate.web")
            .adapter("database", "roommate.database");
}
