package org.hmcore.registration.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hmcore.modules.Module;

public class ObjectInfoConfigHandler {

    public static String generateFreshJSON(Module<?,?>[] modules) {

        ModuleInfo[] moduleInfos = new ModuleInfo[modules.length];

        for (int i = 0; i < modules.length; i++) {

            Module<?,?> module = modules[i];
            moduleInfos[i] = new ModuleInfo(module.getName(),
                    module.getObjectInfoArray());

        }

        ObjectInfoConfig objectInfoConfig = new ObjectInfoConfig(moduleInfos);

        GsonBuilder builder = new GsonBuilder().setPrettyPrinting();
        Gson gson = builder.create();

        return gson.toJson(objectInfoConfig);

    }

}
