package jp.sane.numbersinvietnamese

val zero = "không"
val normalNumbers = arrayOf(
    "linh", "một", "hai", "ba", "bốn",
    "năm", "sáu", "bảy", "tám", "chín"
)

@Throws(NotImplementedError::class)
fun numberToVietnamese(num: Int) : String {
    return when (num) {
        0 -> zero
        in 1..9 -> normalNumbers[num]
        103 -> "một trăm lẻ ba"
        else -> throw NotImplementedError("unknown: $num")
    }
}
