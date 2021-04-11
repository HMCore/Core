package org.hmcore;

import org.hmcore.modules.Module;

import java.util.HashMap;

public class HMCore {

    public static HashMap<String, Module<?, ?>> modules = new HashMap<>();

    public static void callAllModuleRegistries() {
        modules.forEach((name, module) -> module.registerObjects());
    }

}
