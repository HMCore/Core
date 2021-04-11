package org.hmcore.modules;

import org.hmcore.registration.ObjectInfo;

/**
 * Represents a module that manages the objects that should get registered to Hytale.
 *
 * @param <T> The object that should get registered
 * @param <I> Object that contains information about the object. Like the texture.
 */
public abstract class Module<T, I extends ObjectInfo> {

    //
    // For registering Objects to Module and getting them
    // Made for mods to call them
    //

    /**
     * For checking if the Module has the module already in its list.
     *
     * @param name The name or registration key of the object to be checked
     * @return true when the object is already registered.
     */
    public abstract boolean contains(String name);

    /**
     * Gets the object for the registered name.
     *
     * @param name The name or key for the object that should be returned.
     * @return The object under the registered name or null if it doesn't exists.
     */
    public abstract T get(String name);

    /**
     * Registers the object to the module.
     *
     * @param name   The name or key for the object to get registered under.
     * @param object The object that should get registered.
     */
    public abstract void register(String name, T object);

    // TODO: If one option added stuff the other option doesn't had, (as example ore generation info for a block) it should be automatically added.
    // TODO: The admins should be able to choose a option for each feature individually. (Block behavior, ore generation, maybe force a specific texture for users, etc.)

    /**
     * Adds an option for information to the object.
     * There can be multiple options for information and the server administrators can choose which to use.
     * Per default the first registered option is used.
     *
     * @param name       The name or key for the object the information should be added to.
     * @param infoName   The name of the option. The name of the mod as example.
     * @param objectInfo The info object that supplies the module with the required information.
     */
    public abstract void addInfoToObject(String name, String infoName, I objectInfo);

    /**
     * Simple utility function that automatically checks if the object already exists.
     * If not, the object is registered. An easy boilerplate code prevention.
     *
     * @param name
     * @param object
     */
    public void registerIfNonExistent(String name, T object) {
        if (!contains(name)) register(name, object);
    }

    //
    // For registering the Objects to Hytale
    // Made for internal use ONLY
    //

    /**
     * Gets called when the phase of object registration to Hytale has come.
     *
     * @return true when every object has been registered successfully.
     */
    public abstract boolean registerObjects();
}
