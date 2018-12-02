package jp.sane.numbersinvietnamese

import android.content.Context
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.util.*


class MainActivity : AppCompatActivity() {
    private var textToSpeech: TextToSpeech? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (this.textToSpeech == null) {
            this.textToSpeech = initTextToSpeech(applicationContext)
        }

        val results = mapOf("5" to "năm", "6" to "sáu", "103" to "một trăm lẻ ba")
        val random = Random()
        val nextView = findViewById<View>(R.id.nextView)

        val pref = getSharedPreferences("num-in-v", Context.MODE_PRIVATE)

        nextView.setOnClickListener {
            val revealNumber = pref.getBoolean("revealNumber", true)
            val revealVietnamese = pref.getBoolean("revealVietnamese", true)
            val autoPlay = pref.getBoolean("autoPlay", false)
            val index = random.nextInt(results.size)
            val key = results.keys.elementAt(index)
            val vietnameseWord = results[key]
            val keyView = findViewById<TextView>(R.id.numberText)
            keyView.text = key
            if (revealNumber) {
                keyView.visibility = View.VISIBLE
            } else {
                keyView.visibility = View.INVISIBLE
            }
            val wordView = findViewById<TextView>(R.id.vietnameseText)
            wordView.text = vietnameseWord
            if (revealVietnamese) {
                wordView.visibility = View.VISIBLE
            } else {
                wordView.visibility = View.INVISIBLE
            }
        }

        val numberView = findViewById<View>(R.id.numberView)

        numberView.setOnClickListener {
            toggleNumberVisibility()
        }

        val vietnameseView = findViewById<View>(R.id.vietnameseView)

        vietnameseView.setOnClickListener {
            toggleVietnameseVisibility()
        }

        val saveToggleButton = findViewById<Button>(R.id.saveToggle)

        saveToggleButton.setOnClickListener {
            val keyView = findViewById<TextView>(R.id.numberText)
            val wordView = findViewById<TextView>(R.id.vietnameseText)
            val editor = pref.edit()
            editor.putBoolean("revealNumber", keyView.visibility == View.VISIBLE)
            editor.putBoolean("revealVietnamese", wordView.visibility == View.VISIBLE)
            editor.apply()
        }

        val speechView = findViewById<View>(R.id.speechView)
        speechView.setOnClickListener {
            val numberTextView = findViewById<TextView>(R.id.numberText)
            val vietnamese = numberTextView.text.toString()
            if (this.textToSpeech == null) {
                this.textToSpeech = initTextToSpeech(applicationContext)
            }
            this.textToSpeech?.speak(vietnamese, TextToSpeech.QUEUE_FLUSH,null,null)
        }

        val revealNumber = pref.getBoolean("revealNumber", true)
        val revealVietnamese = pref.getBoolean("revealVietnamese", true)
        val autoPlay = pref.getBoolean("autoPlay", false)
        val index = random.nextInt(results.size)
        val key = results.keys.elementAt(index)
        val vietnameseWord = results[key]
        val keyView = findViewById<TextView>(R.id.numberText)
        keyView.text = key
        if (revealNumber) {
            keyView.visibility = View.VISIBLE
        } else {
            keyView.visibility = View.INVISIBLE
        }
        val wordView = findViewById<TextView>(R.id.vietnameseText)
        wordView.text = vietnameseWord
        if (revealVietnamese) {
            wordView.visibility = View.VISIBLE
        } else {
            wordView.visibility = View.INVISIBLE
        }
    }

    fun toggleNumberVisibility() {
        val numberText = findViewById<TextView>(R.id.numberText)
        numberText.visibility = toggledVisibility(numberText.visibility)
    }

    fun toggleVietnameseVisibility() {
        val vietnameseText = findViewById<TextView>(R.id.vietnameseText)
        vietnameseText.visibility = toggledVisibility(vietnameseText.visibility)
    }
}

fun initTextToSpeech(context: Context) : TextToSpeech {
    // android - Unresolved reference inside anonymous Kotlin listener - Stack Overflow
    // https://stackoverflow.com/questions/35049850/unresolved-reference-inside-anonymous-kotlin-listener
    return object {
        val value: TextToSpeech get() = inner
        private val inner = TextToSpeech(
            context
        ) {
            value.language = Locale("vi")
        }
    }.value
}

// FIXME: :thinking_face: Int ??
fun toggledVisibility(visibility: Int): Int {
    return if (visibility == View.VISIBLE) {
        View.INVISIBLE
    } else {
        View.VISIBLE
    }
}
