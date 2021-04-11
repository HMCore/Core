package org.hmcore.tests.modules.impl;

import org.hmcore.modules.Module;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JavaTestModule extends Module<Integer, JavaCustomObjectInfo> {

    public HashMap<String, Integer> objectMap = new HashMap<>();
    public HashMap<String, HashMap<String, JavaCustomObjectInfo>> infoMap = new HashMap<>();

    public List<Object> endList = new ArrayList<>();

    @Override
    public boolean contains(String name) {
        return objectMap.containsKey(name);
    }

    @Override
    public Integer get(String name) {
        return objectMap.get(name);
    }

    @Override
    public void register(String name, Integer object) {
        objectMap.put(name, object);
    }

    @Override
    public void addInfoToObject(String name, String infoName, JavaCustomObjectInfo objectInfo) {
        if(!infoMap.containsKey(name)) infoMap.put(name, new HashMap<>());

        infoMap.get(name).putIfAbsent(infoName, objectInfo);

    }

    @Override
    public boolean registerObjects() {

        objectMap.forEach((string, integer) -> {
            endList.add(string);
            endList.add(integer);
        });

        infoMap.forEach((string, hashmap) -> {
            endList.add(string);
            hashmap.forEach((string2, objectinfo) -> {
                endList.add(string2);
                endList.add(objectinfo.getTexturePath());
                endList.add(objectinfo.getBeanCount());
            });
        });

        return true;
    }
}
