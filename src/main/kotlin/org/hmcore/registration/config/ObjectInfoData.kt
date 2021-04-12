package org.hmcore.registration.config

internal class ObjectInfoData @JvmOverloads constructor(
    @JvmField val objectInfoName: String,
    @JvmField val availableOptions: String,
    @JvmField val objectInfoChosen: String = "default",
)