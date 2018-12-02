package jp.sane.numbersinvietnamese

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class NumberToVietnameseTest {
    @Test
    fun num5() {
        assertEquals("năm", numberToVietnamese(5))
    }
    @Test
    fun num6() {
        assertEquals("sáu", numberToVietnamese(6))
    }
    @Test
    fun num103() {
        assertEquals("một trăm lẻ ba", numberToVietnamese(103))
    }
}
