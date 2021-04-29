package org.hmcore.modules;

import org.hmcore.HMCore;
import org.hmcore.api.ModuleState;
import org.hmcore.api.exceptions.ModuleErroredException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class ModuleManager {

    static ModuleState overallState = ModuleState.QUEUED;

    public static void loadModules() {
        if(overallState != ModuleState.QUEUED) return;

        try {
            new ModuleLoader().loadModules();
        } catch (IOException | ClassNotFoundException | InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        overallState = ModuleState.LOADED;

    }

    public static void initModules() {
        if(overallState != ModuleState.LOADED) return;

        try {
            for (Module module:
                 HMCore.modules.values()) {
                if(!module.initialize()) throwStack(module.getName());
                module.setModuleState(ModuleState.INITIALIZED);
            }
        } catch (ModuleErroredException e) {
            e.printStackTrace();
            //TODO: Save worlds etc before exiting?
            System.exit(1);
        }

        overallState = ModuleState.INITIALIZED;

    }

    public static void hookModules() {
        if(overallState != ModuleState.INITIALIZED) return;

        try {
            for (Module module:
                    HMCore.modules.values()) {
                if(!module.hook()) throwStack(module.getName());
                module.setModuleState(ModuleState.HOOKED);
            }
        } catch (ModuleErroredException e) {
            e.printStackTrace();
            //TODO: Save worlds etc before exiting?
            System.exit(1);
        }

        overallState = ModuleState.HOOKED;

    }

    public static void disableModules() {
        if(overallState != ModuleState.HOOKED) return;

        try {
            for (Module module:
                    HMCore.modules.values()) {
                if(!module.disable()) throwStack(module.getName());
                module.setModuleState(ModuleState.DISABLED);
            }
        } catch (ModuleErroredException e) {
            e.printStackTrace();
            //TODO: Save worlds etc before exiting?
            System.exit(1);
        }

        overallState = ModuleState.DISABLED;

    }

    public static void unloadModules() {
        if(overallState != ModuleState.DISABLED) return;

        try {
            for (Module module:
                    HMCore.modules.values()) {
                if(!module.unload()) throwStack(module.getName());
                module.setModuleState(ModuleState.UNLOADED);
            }
        } catch (ModuleErroredException e) {
            e.printStackTrace();
            //TODO: Save worlds etc before exiting?
            System.exit(1);
        }

        overallState = ModuleState.UNLOADED;

    }

    private static void throwStack(String name) throws ModuleErroredException {
        String desc = "Module " + name + " has been found in a errored state while " + overallState.toString();
        overallState = ModuleState.ERRORED;
        throw new ModuleErroredException(desc);
    }
}
