package org.hmcore.modules;

import com.google.gson.GsonBuilder;
import org.hmcore.HMCore;
import org.hmcore.registration.config.ModuleReadable;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Objects;
import java.util.Scanner;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ModuleLoader {

    void loadModules() throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        File moduleDir = new File("hm/modules");
        if(!moduleDir.exists()) moduleDir.mkdirs();
        for (String path:
                Objects.requireNonNull(moduleDir.list())) {
            loadModule(path);
        }
    }

    private void loadModule(String path) throws IOException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        System.out.println("Loading module at: " + path);
        JarFile jarFile = new JarFile(path);
        JarEntry jarEntry = jarFile.getJarEntry("module.json");
        String content = getClassPath(jarFile.getInputStream(jarEntry));
        jarFile.close();
        ModuleReadable readable = new GsonBuilder().create().fromJson(content, ModuleReadable.class);
        URLClassLoader child = new URLClassLoader(
                new URL[] {new File(path).toURI().toURL()},
                this.getClass().getClassLoader()
        );
        Class<?> classToLoad = Class.forName(readable.getClassPath(), true, child);
        Module instance = (Module) classToLoad.getDeclaredConstructor().newInstance();
        HMCore.modules.put(instance.getName(), instance);
        System.out.println("Loaded " + instance.getName() + " v" + readable.getVersion() + " by " + readable.getCreator());
    }

    private String getClassPath(InputStream jarEntry) {
        StringBuilder builder = new StringBuilder();

        Scanner myReader = new Scanner(jarEntry);
        while (myReader.hasNextLine()) {
            builder.append(myReader.nextLine());
        }
        myReader.close();

        return builder.toString();
    }

}
