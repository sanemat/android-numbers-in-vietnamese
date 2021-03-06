package jp.sane.numbersinvietnamese

import android.content.Context
import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

import jp.sane.numbertovietnamese.numberToVietnamese

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
            val vietnameseWord = try {
                numberToVietnamese(number)
            } catch (e: NotImplementedError) {
                e.message
            }
            val numberString = number.toString()
            numberText.text = numberString
            numberText.visibility = booleanToVisibility(revealNumber)
            vietnameseText.text = vietnameseWord
            vietnameseText.visibility = booleanToVisibility(revealVietnamese)
            if (!autoPlay) {
                return@setOnClickListener
            }

            speechVietnamese(numberString, applicationContext)
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
        val vietnameseWord = try {
            numberToVietnamese(number)
        } catch (e: NotImplementedError) {
            e.message
        }
        val numberString = number.toString()
        numberText.text = numberString
        numberText.visibility = booleanToVisibility(revealNumber)
        vietnameseText.text = vietnameseWord
        vietnameseText.visibility = booleanToVisibility(revealVietnamese)

        if (!autoPlay) {
            return
        }

        speechVietnamese(numberString, applicationContext)
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
    return when (Random().nextInt(99)) {
        in 0..39 -> Random().nextInt(9) // 0-9
        in 40..79 -> Random().nextInt(89) + 10 // 10-99
        in 80..89 -> Random().nextInt(899) + 100 // 100-999
        in 90..92 -> 103
        in 93..96 -> 1000
        in 97..99 -> 1000000
        else -> 0
    }
}

