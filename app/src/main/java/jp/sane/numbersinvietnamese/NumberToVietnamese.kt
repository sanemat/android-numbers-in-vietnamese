package jp.sane.numbersinvietnamese

@Throws(NotImplementedError::class)
fun numberToVietnamese(num: Int) : String {
    return when (num) {
        5 -> "năm"
        6 -> "sáu"
        103 -> "một trăm lẻ ba"
        else -> throw NotImplementedError("unknown: $num")
    }
}
