package jp.sane.numbersinvietnamese

import org.junit.Test

import org.junit.Assert.*

/**
 * Vietnamese numbers https://www.omniglot.com/language/numbers/vietnamese.htm
 * Counting numbers in Vietnamese https://www.colanguage.com/counting-numbers-vietnamese
 */
class NumberToVietnameseTest {
    @Test
    fun num0() {
        assertEquals("không", numberToVietnamese(0))
    }
    @Test
    fun num5() {
        assertEquals("năm", numberToVietnamese(5))
    }
    @Test
    fun num6() {
        assertEquals("sáu", numberToVietnamese(6))
    }
    @Test
    fun num10() {
        assertEquals("một mười", numberToVietnamese(10))
    }
    @Test
    fun num100() {
        assertEquals("một trăm", numberToVietnamese(100))
    }
    @Test
    fun num103() {
        assertEquals("một trăm lẻ ba", numberToVietnamese(103))
    }
    @Test
    fun num1000() {
        assertEquals("một nghìn", numberToVietnamese(1000))
    }
    @Test
    fun num1000000() {
        assertEquals("một triệu", numberToVietnamese(1000000))
    }
}
