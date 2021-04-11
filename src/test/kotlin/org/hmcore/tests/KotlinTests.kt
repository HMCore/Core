package org.hmcore.tests

import org.hmcore.HMCore
import org.junit.jupiter.api.Test

// The primary function of the Kotlin tests is to ensure Kotlin-specific features are preserved
// Meaning some tests might only make sure features work at compile time.

@Test
fun `Module should support index access operator`() {
    HMCore.modules["ores"]!!["other"]
}

@Test
fun `Module should support 'in' keyword`() {
    HMCore.modules["ores"]?.let { "copper" in it }
}