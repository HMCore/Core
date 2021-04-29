package org.hmcore.registration.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hmcore.HMCore;
import org.hmcore.modules.Module;
import org.hmcore.modules.RegistryModule;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ObjectInfoConfigHandler {

    public boolean initialize() throws FileNotFoundException {

        new File("config").mkdir();

        File objectInfoConfigFile = new File("config/object-infos.json");
        if(objectInfoConfigFile.exists()) {

            StringBuilder builder = new StringBuilder();

            Scanner myReader = new Scanner(objectInfoConfigFile);
            while (myReader.hasNextLine()) {
                builder.append(myReader.nextLine());
            }
            myReader.close();

            String content = builder.toString();
            ObjectInfoConfig objectInfoConfig = isValid(content);

            if(objectInfoConfig != null) {

                for (ModuleInfo moduleInfo:
                objectInfoConfig.modules) {
                    Module module = HMCore.modules.get(moduleInfo.moduleName);
                    if(module instanceof RegistryModule) {
                        RegistryModule<?, ?> registryModule = (RegistryModule<?, ?>) module;
                        for (ObjectInfoData data:
                             moduleInfo.objects) {
                            if(data.objectInfoChosen.equalsIgnoreCase("default")) break;
                            if(registryModule.objectAndInfoExist(data.objectInfoName, data.objectInfoChosen)) {
                                registryModule.forceObjectInfoForObject(data.objectInfoName, data.objectInfoChosen);
                            } else {
                                System.out.println("[!] Either Object Info " + data.objectInfoChosen + " doesn't exist for " + data.objectInfoName + " or " + data.objectInfoName + " doesn't exist!\n" +
                                        "Please stop the Server, delete objetc-infos.json and let the server regenertate a new config [!]");
                                return false;
                            }
                        }
                    }
                }

            } else {
                writeNewConfig(objectInfoConfigFile);
            }
        } else {
            writeNewConfig(objectInfoConfigFile);
        }

        return true;

    }

    private void writeNewConfig(File objectInfoConfig) {
        try {
            FileWriter fileWriter = new FileWriter(objectInfoConfig,false);
            List<RegistryModule<?, ?>> registryModules = new ArrayList<>();
            Module[] modules = HMCore.modules.values().toArray(new Module[0]);
            for (Module module:
                 modules) {
                if(module instanceof RegistryModule) registryModules.add((RegistryModule<?, ?>) module);
            }
            fileWriter.write(generateFreshJSON(registryModules.toArray(new RegistryModule[0])));
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ObjectInfoConfig isValid(String content) {

        ObjectInfoConfig object;

        try {
            object = new GsonBuilder().create().fromJson(content, ObjectInfoConfig.class);
        } catch (Exception e) {
            System.out.println("[!] Object Info Config not valid!");
            return null;
        }

        return object;

    }

    public static String generateFreshJSON(RegistryModule<?,?>[] registryModules) {

        ModuleInfo[] moduleInfos = new ModuleInfo[registryModules.length];
        for (int i = 0; i < registryModules.length; i++) {
            RegistryModule<?,?> registryModule = registryModules[i];
            moduleInfos[i] = new ModuleInfo(registryModule.getName(),
                    registryModule.getObjectInfoArray());
        }

        ObjectInfoConfig objectInfoConfig = new ObjectInfoConfig(moduleInfos);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(objectInfoConfig);

    }

}
