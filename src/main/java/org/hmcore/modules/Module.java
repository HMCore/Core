package org.hmcore.modules;

import org.hmcore.api.ModuleState;

/**
 * Represents a module with custom features
 */
public abstract class Module {

    /**
     * Used to be able to request the name the module is registered as.
     * @return The String the module is registered as. So that HMCore.modules.get(name).getName() equals name
     */
    public abstract String getName();

    ModuleState moduleState = ModuleState.QUEUED;

    public ModuleState getModuleState() {
        return moduleState;
    }

    protected void setModuleState(ModuleState moduleState) {
        this.moduleState = moduleState;
    }

    /**
     * Runs some preparation optionally supplied by the module before it can fully work.
     * This should only include stuff that isn't needed for calling functions like register(name, object); to a RegistryModule. This should be in the constructor.
     * It can be used as example for creating a namespace or hooking into some Hytale functionalities.
     * If you want to run stuff when the Module is loaded, use the constructor of your main class.
     * @return true when initialisation finished without a problem. false when there is a critical problem which won't allow the module to function properly.
     * When false is returned the server is safely shut down.
     */
    protected abstract boolean initialize();

    /**
     * Gets called when it is time for the module to hook into Hytale and register their stuff in the hytale apis.
     * @return true when hooking finished without a problem. false when there is a critical problem which won't allow the module to function properly.
     * When false is returned the server is safely shut down.
     */
    protected abstract boolean hook();

    /**
     * Gets called when the module should shut down. Can be used to save data a last time.
     * @return true when disabling finished without a problem. false when there is a critical problem which won't allow the module to disable properly.
     * When false is returned the server is safely shut down with a warning.
     */
    protected abstract boolean disable();

    /**
     * Gets called right before the module is unloaded. This will be the last call to the module before it is unloaded.
     * @return true when disabling finished without a problem. false when there is a critical problem which won't allow the module to disable properly.
     * When false is returned the server is safely shut down with a warning.
     */
    protected abstract boolean unload();

}
