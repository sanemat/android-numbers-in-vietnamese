package jp.sane.numbersinvietnamese

@Throws(NotImplementedError::class)
fun numberToVietnamese(num: Int) : String {
    val nothingString = "không"
    val normalNumbers = arrayOf(
        "lẻ", "một", "hai", "ba", "bốn",
        "năm", "sáu", "bảy", "tám", "chín"
    )
    // north: linh, south: lẻ
    val ten = "mười"
    val specialTen = "mươi"
    val specialFive = "lăm"
    val specialOne = "mốt"
    val hundred = "trăm"
    val thousand = "nghìn"
    val million = "triệu"
    if ( 1000000000 <= num ) {
        throw NotImplementedError("unknown: $num")
    }

    return when {
        num == 0 -> nothingString
        num in 1000000..999999999 -> {
            val quot = num / 1000000
            val rem = num % 1000000
            if (rem == 0) {
                return "${normalNumbers[quot]} $million"
            }
            throw NotImplementedError("unknown: $num")
        }
        num in 1000..999999 -> {
            val quot = num / 1000
            val rem = num % 1000
            if (rem == 0) {
                return "${normalNumbers[quot]} $thousand"
            }
            throw NotImplementedError("unknown: $num")
        }
        num in 1..999 -> {
            val hundredsPosition = num / 100
            val tensPosition = (num - hundredsPosition * 100) / 10
            val onesPosition = num % 10
            return withoutPrefix(hundredsPosition, tensPosition, onesPosition)!!
        }
        else -> throw NotImplementedError("unknown: $num")
    }
}

fun withoutPrefix(hundredsPosition: Int, tensPosition: Int, onesPosition: Int) : String? {
    if (hundredsPosition == 0 && tensPosition == 0 && onesPosition == 0) {
        return null
    }
    val normalNumbers = arrayOf(
        "lẻ", "một", "hai", "ba", "bốn",
        "năm", "sáu", "bảy", "tám", "chín"
    )
    // north: linh, south: lẻ
    val ten = "mười"
    val specialTen = "mươi"
    val specialFive = "lăm"
    val specialOne = "mốt"
    val hundred = "trăm"
    val thousand = "nghìn"
    val million = "triệu"
    val nothingString = "không"
    val empty = "lẻ"

    val temp = mutableListOf<String?>()
    // hundred's position
    if (hundredsPosition != 0) {
        temp.add("${normalNumbers[hundredsPosition]} $hundred")
    }
    // ten's position
    if (hundredsPosition != 0 && tensPosition == 0 && onesPosition != 0) {
        temp.add(empty)
    } else if (tensPosition == 1) {
        temp.add(ten)
    } else if (tensPosition in 2..9) {
        temp.add("${normalNumbers[tensPosition]} $specialTen")
    }
    // one's position
    if (tensPosition in 1..9 && onesPosition == 5) {
        temp.add(specialFive)
    } else if (tensPosition in 2..9 && onesPosition == 1) {
        temp.add(specialOne)
    } else if (onesPosition in 1..9) {
        temp.add(normalNumbers[onesPosition])
    }
    return temp.joinToString(" ")
}
