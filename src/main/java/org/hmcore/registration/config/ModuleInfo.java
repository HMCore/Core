package org.hmcore.registration.config;

public class ModuleInfo {

    public final String moduleName;
    public final ObjectInfoData[] objects;

    public ModuleInfo(String moduleName, ObjectInfoData[] objects) {
        this.moduleName = moduleName;
        this.objects = objects;
    }
}
