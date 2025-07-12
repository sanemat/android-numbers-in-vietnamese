package jp.sane.numbersinvietnamese

import android.content.Context
import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import java.util.*

import jp.sane.numbertovietnamese.numberToVietnamese
import jp.sane.numbersinvietnamese.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var textToSpeech: TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = getSharedPreferences("num-in-v", Context.MODE_PRIVATE)

        binding.nextView.setOnClickListener {
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
            binding.numberText.text = numberString
            binding.numberText.visibility = booleanToVisibility(revealNumber)
            binding.vietnameseText.text = vietnameseWord
            binding.vietnameseText.visibility = booleanToVisibility(revealVietnamese)
            if (!autoPlay) {
                return@setOnClickListener
            }

            speechVietnamese(numberString, applicationContext)
        }

        binding.numberView.setOnClickListener {
            binding.numberText.visibility = toggledVisibility(binding.numberText.visibility)
        }

        binding.vietnameseView.setOnClickListener {
            binding.vietnameseText.visibility = toggledVisibility(binding.vietnameseText.visibility)
        }

        binding.saveToggle.setOnClickListener {
            val editor = pref.edit()
            editor.putBoolean("revealNumber", binding.numberText.visibility == View.VISIBLE)
            editor.putBoolean("revealVietnamese", binding.vietnameseText.visibility == View.VISIBLE)
            editor.apply()
        }

        val autoPlay = pref.getBoolean("autoPlay", false)
        binding.autoPlaySwitch.isChecked = autoPlay

        binding.autoPlaySwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                pref.edit().putBoolean("autoPlay", true).apply()
            } else {
                pref.edit().putBoolean("autoPlay", false).apply()
            }
        }

        binding.speechView.setOnClickListener {
            speechVietnamese(binding.numberText.text.toString(), applicationContext)
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
        binding.numberText.text = numberString
        binding.numberText.visibility = booleanToVisibility(revealNumber)
        binding.vietnameseText.text = vietnameseWord
        binding.vietnameseText.visibility = booleanToVisibility(revealVietnamese)

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

