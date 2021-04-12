package org.hmcore.tests;

import org.hmcore.HMCore;
import org.hmcore.modules.Module;
import org.hmcore.registration.config.ObjectInfoConfigHandler;
import org.hmcore.tests.modules.impl.JavaCustomObjectInfo;
import org.hmcore.tests.modules.impl.JavaTestModule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SuppressWarnings("KotlinInternalInJava")
public class JavaTests {

    @Test
    public void testModuleFunctionality() {

        HMCore.modules.putIfAbsent("java_test", new JavaTestModule());

        JavaTestModule testModule = (JavaTestModule) HMCore.modules.get("java_test");

        //
        // Default registration tests
        //

        testModule.register("test1", 609);

        assertTrue(testModule.contains("test1"), "Module doesn't contain object after registration!");

        assertEquals(609, testModule.get("test1"), "Object has changed properties for some reason!");

        testModule.register("test2", 1256);

        assertTrue(testModule.contains("test2"), "Module doesn't contain object after registration!");

        assertEquals(1256, testModule.get("test2"), "Object has changed properties for some reason!");

        testModule.register("test3", 74232);

        assertTrue(testModule.contains("test3"), "Module doesn't contain object after registration!");

        assertEquals(74232, testModule.get("test3"), "Object has changed properties for some reason!");

        assertTrue(testModule.registerObjects(), "Some weird behavior happened..");

        testModule.addInfoToObject("test1", "opt1", new JavaCustomObjectInfo("aa", 18));

        testModule.addInfoToObject("test2", "opt2", new JavaCustomObjectInfo("AI", 69));
        testModule.addInfoToObject("test2", "opt3", new JavaCustomObjectInfo("drei", 420));

        testModule.addInfoToObject("test3", "opt4", new JavaCustomObjectInfo("addadaaa", 181));
        testModule.addInfoToObject("test3", "opt5", new JavaCustomObjectInfo("dada", 32833));
        testModule.addInfoToObject("test3", "opt6", new JavaCustomObjectInfo("e3312", 2130440));

        System.out.println(ObjectInfoConfigHandler.generateFreshJSON(HMCore.modules.values().toArray(new Module[0])));

    }

}
