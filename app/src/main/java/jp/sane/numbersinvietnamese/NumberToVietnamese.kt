package jp.sane.numbersinvietnamese

val zero = "không"
val normalNumbers = arrayOf(
    "linh", "một", "hai", "ba", "bốn",
    "năm", "sáu", "bảy", "tám", "chín"
)
val ten = "mười"
val specialTen = "mươi"
val specialFive = "lăm"
val specialOne = "mốt"
val hundred = "trăm"
val thousand = "nghìn"
val million = "triệu"

@Throws(NotImplementedError::class)
fun numberToVietnamese(num: Int) : String {
    return when (num) {
        0 -> zero
        10 -> "${normalNumbers[1]} $ten"
        100 -> "${normalNumbers[1]} $hundred"
        1000 -> "${normalNumbers[1]} $thousand"
        1000000 -> "${normalNumbers[1]} $million"
        in 1..9 -> normalNumbers[num]
        103 -> "một trăm lẻ ba"
        else -> throw NotImplementedError("unknown: $num")
    }
}
