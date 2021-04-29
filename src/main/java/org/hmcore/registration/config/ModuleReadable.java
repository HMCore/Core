package org.hmcore.registration.config;

public class ModuleReadable {

    final String classPath;
    final String version;
    final String creator;

    public ModuleReadable(String classPath, String version, String creator) {
        this.classPath = classPath;
        this.version = version;
        this.creator = creator;
    }

    public String getClassPath() {
        return classPath;
    }

    public String getVersion() {
        return version;
    }

    public String getCreator() {
        return creator;
    }
}
