package com.github.jspurim.puzzleengine.app

import com.github.jspurim.puzzleengine.app.getPlatformName
import kotlin.test.Test
import kotlin.test.assertEquals

class PlatformKtTest {

    @Test
    fun `Platform name is correct`() {
        assertEquals("Android", getPlatformName())
    }
}