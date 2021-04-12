@file:JvmName("ObjectInfoConfigHandler")

package org.hmcore.registration.config

import com.google.gson.GsonBuilder
import org.hmcore.modules.Module
import extensions.map

private val GSON = GsonBuilder().setPrettyPrinting().create()

fun Array<Module<*, *>>.generateFreshJSON(): String =
    GSON.toJson(ObjectInfoConfig(map { ModuleInfo(it.name, it.objectInfoArray) }))