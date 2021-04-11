package org.hmcore.tests.modules.impl;

import org.hmcore.registration.ObjectInfo;
import org.jetbrains.annotations.NotNull;

public class JavaCustomObjectInfo extends ObjectInfo {

    private final int beanCount;

    public JavaCustomObjectInfo(@NotNull String texturePath, int beanCount) {
        super(texturePath);
        this.beanCount = beanCount;
    }

    public int getBeanCount() {
        return beanCount;
    }
}
