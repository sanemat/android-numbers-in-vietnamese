package jp.sane.numbersinvietnamese

import android.content.Context
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {
    private var textToSpeech: TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pref = getSharedPreferences("num-in-v", Context.MODE_PRIVATE)

        nextView.setOnClickListener {
            val revealNumber = pref.getBoolean("revealNumber", true)
            val revealVietnamese = pref.getBoolean("revealVietnamese", true)
            val autoPlay = pref.getBoolean("autoPlay", false)
            val number = getNumber()
            val vietnameseWord = number2Letter(number)
            val vietnamese = number.toString()
            numberText.text = vietnamese
            numberText.visibility = booleanToVisibility(revealNumber)
            vietnameseText.text = vietnameseWord
            vietnameseText.visibility = booleanToVisibility(revealVietnamese)
            if (!autoPlay) {
                return@setOnClickListener
            }

            speechVietnamese(vietnamese, applicationContext)
        }

        numberView.setOnClickListener {
            numberText.visibility = toggledVisibility(numberText.visibility)
        }

        vietnameseView.setOnClickListener {
            vietnameseText.visibility = toggledVisibility(vietnameseText.visibility)
        }

        saveToggle.setOnClickListener {
            val editor = pref.edit()
            editor.putBoolean("revealNumber", numberText.visibility == View.VISIBLE)
            editor.putBoolean("revealVietnamese", vietnameseText.visibility == View.VISIBLE)
            editor.apply()
        }

        val autoPlay = pref.getBoolean("autoPlay", false)
        autoPlaySwitch.isChecked = autoPlay

        autoPlaySwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                pref.edit().putBoolean("autoPlay", true).apply()
            } else {
                pref.edit().putBoolean("autoPlay", false).apply()
            }
        }

        speechView.setOnClickListener {
            speechVietnamese(numberText.text.toString(), applicationContext)
        }

        val revealNumber = pref.getBoolean("revealNumber", true)
        val revealVietnamese = pref.getBoolean("revealVietnamese", true)
        val number = getNumber()
        val vietnameseWord = number2Letter(number)
        val vietnamese = number.toString()
        numberText.text = vietnamese
        numberText.visibility = booleanToVisibility(revealNumber)
        vietnameseText.text = vietnameseWord
        vietnameseText.visibility = booleanToVisibility(revealVietnamese)

        if (!autoPlay) {
            return
        }

        speechVietnamese(vietnamese, applicationContext)
    }

    private fun speechVietnamese(vietnamese: String, context: Context) {
        if (this.textToSpeech == null) {
            this.textToSpeech = object {
                val value: TextToSpeech get() = inner
                private val inner = TextToSpeech(
                    context
                ) {
                    value.language = Locale("vi")
                    value.speak(vietnamese, TextToSpeech.QUEUE_FLUSH,null,null)
                }
            }.value
        } else {
            this.textToSpeech?.speak(vietnamese, TextToSpeech.QUEUE_FLUSH,null,null)
        }
    }
}

// FIXME: :thinking_face: Int ??
fun toggledVisibility(visibility: Int): Int {
    return if (visibility == View.VISIBLE) {
        View.INVISIBLE
    } else {
        View.VISIBLE
    }
}

// FIXME: :thinking_face: Int ??
fun booleanToVisibility(bool: Boolean): Int {
    return if (bool) {
        View.VISIBLE
    } else {
        View.INVISIBLE
    }
}

fun getNumber() : Int {
    val array = arrayOf(5, 6, 103, 21)
    return array[Random().nextInt(array.size)]
}

fun number2Letter(num: Int) : String {
    return when (num) {
        5 -> "năm"
        6 -> "sáu"
        103 -> "một trăm lẻ ba"
        else -> "unknown"
    }
}
