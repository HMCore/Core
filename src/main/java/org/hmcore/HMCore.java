package org.hmcore;

import org.hmcore.modules.Module;
import org.hmcore.modules.ModuleManager;
import org.hmcore.modules.RegistryModule;

import java.util.HashMap;

public class HMCore {

    public static final HashMap<String, Module> modules = new HashMap<>();

    public static void main(String[] args) {
        ModuleManager.loadModules();
        ModuleManager.initModules();
        ModuleManager.hookModules();
    }

}
