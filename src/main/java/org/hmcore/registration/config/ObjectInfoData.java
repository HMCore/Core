package org.hmcore.registration.config;

import org.jetbrains.annotations.Nullable;

public class ObjectInfoData {

    public final String objectName;
    public final String objectInfoChoosen;

    public ObjectInfoData(String objectName, @Nullable String objectInfoChoosen) {
        this.objectName = objectName;
        this.objectInfoChoosen = objectInfoChoosen == null ? "default" : objectInfoChoosen;
    }
}
