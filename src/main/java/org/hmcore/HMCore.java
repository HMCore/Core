package org.hmcore;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hmcore.modules.Module;
import org.hmcore.modules.ModuleLoader;
import org.hmcore.modules.ModuleManager;
import org.hmcore.modules.RegistryModule;

import java.util.HashMap;

public class HMCore {

    public static final HashMap<String, Module> modules = new HashMap<>();
    public static final Logger logger = LogManager.getLogger("HMCore");

    public static void main(String[] args) {



        ModuleManager.loadModules();
        ModuleManager.initModules();
        ModuleManager.hookModules();

        ModuleManager.disableModules();
        ModuleManager.unloadModules();
    }

}
