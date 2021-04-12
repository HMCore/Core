package org.hmcore.registration.config;

import org.jetbrains.annotations.Nullable;

public class ObjectInfoData {

    public final String objectName;
    public final String objectInfoChoosen;
    public final String _availableOptions;

    public ObjectInfoData(String objectName, @Nullable String objectInfoChoosen, String availableOptions) {
        this.objectName = objectName;
        this.objectInfoChoosen = objectInfoChoosen == null ? "default" : objectInfoChoosen;
        _availableOptions = availableOptions;
    }
}
