package org.hmcore.api;

public enum ModuleState {

    QUEUED("queuing"),
    LOADED("loading"),
    INITIALIZED("initalizing"),
    HOOKED("hooking"),
    DISABLED("disabling"),
    UNLOADED("unloading"),
    ERRORED("");

    public String name;

    ModuleState(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
