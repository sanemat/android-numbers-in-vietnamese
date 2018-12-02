package jp.sane.numbersinvietnamese

import org.junit.Test

import org.junit.Assert.*

/**
 * Vietnamese numbers https://www.omniglot.com/language/numbers/vietnamese.htm
 * Counting numbers in Vietnamese https://www.colanguage.com/counting-numbers-vietnamese
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
