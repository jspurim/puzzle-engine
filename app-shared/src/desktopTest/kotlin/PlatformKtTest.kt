package com.github.jspurim.puzzleengine.app

import kotlin.test.Test
import kotlin.test.assertEquals

class PlatformKtTest {

    @Test
    fun `Platform name is correct`() {
        assertEquals("Desktop", getPlatformName())
    }
}