package org.hmcore.tests.modules.impl;

import org.hmcore.modules.RegistryModule;
import org.hmcore.registration.config.ObjectInfoData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("KotlinInternalInJava")
public class JavaTestRegistryModule extends RegistryModule<Integer, JavaCustomObjectInfo> {

    public HashMap<String, Integer> objectMap = new HashMap<>();
    public HashMap<String, HashMap<String, JavaCustomObjectInfo>> infoMap = new HashMap<>();
    public HashMap<Integer, JavaCustomObjectInfo> forceMap = new HashMap<>();

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
    public String getName() {
        return "java_test";
    }

    @Override
    protected boolean initialize() {
        return true;
    }

    @Override
    protected boolean disable() {
        return true;
    }

    @Override
    protected boolean unload() {
        return true;
    }

    @Override
    public Integer[] getObjects() {
        return objectMap.values().toArray(new Integer[0]);
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

    @Override
    public ObjectInfoData[] getObjectInfoArray() {

        ObjectInfoData[] objectInfoData = new ObjectInfoData[objectMap.values().size()];

        int i = 0;
        
        for(Map.Entry<String, Integer> entry: objectMap.entrySet()) {
            
            String options = "";
            boolean first = true;

            for(Map.Entry<String, JavaCustomObjectInfo> entry2: infoMap.get(entry.getKey()).entrySet()) {
                
                if(first) {
                    options = entry2.getKey();
                    first = false;
                } else {
                    options += ", " + entry2.getKey();
                }
                
            }

            objectInfoData[i] = new ObjectInfoData(entry.getKey(), null, options);
            
            i++;

        }

        return objectInfoData;
    }

    @Override
    public void forceObjectInfoForObject(String object, String objectInfo) {
        forceMap.put(objectMap.get(object), infoMap.get(object).get(objectInfo));
    }

    @Override
    public boolean objectAndInfoExist(String object, String objectInfo) {
        return infoMap.containsKey(object) && infoMap.get(object).containsKey(objectInfo);
    }
}
