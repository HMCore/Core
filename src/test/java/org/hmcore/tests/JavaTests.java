package org.hmcore.tests;

import org.hmcore.HMCore;
import org.hmcore.modules.RegistryModule;
import org.hmcore.registration.config.ObjectInfoConfigHandler;
import org.hmcore.tests.modules.impl.JavaCustomObjectInfo;
import org.hmcore.tests.modules.impl.JavaTestRegistryModule;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JavaTests {

    @Test
    public void testModuleFunctionality() {

        HMCore.modules.putIfAbsent("java_test", new JavaTestRegistryModule());

        JavaTestRegistryModule testModule = (JavaTestRegistryModule) HMCore.modules.get("java_test");

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

        assertEquals("{\n" +
                "  \"modules\": [\n" +
                "    {\n" +
                "      \"moduleName\": \"java_test\",\n" +
                "      \"objects\": [\n" +
                "        {\n" +
                "          \"objectName\": \"test2\",\n" +
                "          \"objectInfoChoosen\": \"default\",\n" +
                "          \"_availableOptions\": \"opt3, opt2\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"objectName\": \"test3\",\n" +
                "          \"objectInfoChoosen\": \"default\",\n" +
                "          \"_availableOptions\": \"opt4, opt5, opt6\"\n" +
                "        },\n" +
                "        {\n" +
                "          \"objectName\": \"test1\",\n" +
                "          \"objectInfoChoosen\": \"default\",\n" +
                "          \"_availableOptions\": \"opt1\"\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}", ObjectInfoConfigHandler.generateFreshJSON(HMCore.modules.values().toArray(new RegistryModule[0])), "JSON String generation working");

    }

    @Test
    public void testConfigCreation() throws FileNotFoundException {

        ObjectInfoConfigHandler objectInfoConfigHandler = new ObjectInfoConfigHandler();

        objectInfoConfigHandler.initialize();


    }

}
