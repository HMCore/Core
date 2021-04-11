package org.hmcore;

import org.hmcore.modules.Module;

import java.util.HashMap;

public class HMCore {

    public static final HashMap<String, Module<?, ?>> modules = new HashMap<>();

    public static void callAllModuleRegistries() {
        modules.forEach((name, module) -> module.registerObjects());
    }

}
