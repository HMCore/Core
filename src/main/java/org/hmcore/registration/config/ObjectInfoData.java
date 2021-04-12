package org.hmcore.registration.config;

import org.jetbrains.annotations.Nullable;

public class ObjectInfoData {

    public final String objectName;
    public final String objectInfoChosen;
    public final String _availableOptions;

    public ObjectInfoData(String objectName, @Nullable String objectInfoChosen, String availableOptions) {
        this.objectName = objectName;
        this.objectInfoChosen = objectInfoChosen == null ? "default" : objectInfoChosen;
        _availableOptions = availableOptions;
    }
}
