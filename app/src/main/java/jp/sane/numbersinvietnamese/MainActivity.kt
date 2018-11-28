package jp.sane.numbersinvietnamese

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import java.util.*
import android.speech.tts.TextToSpeech


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

        val speechView = findViewById<View>(R.id.speechView)
        speechView.setOnClickListener {
            val vietnameseTextView = findViewById<TextView>(R.id.vietnameseText)
            val vietnamese = vietnameseTextView.text.toString()
            // android - Unresolved reference inside anonymous Kotlin listener - Stack Overflow https://stackoverflow.com/questions/35049850/unresolved-reference-inside-anonymous-kotlin-listener
            val textToSpeech = object {
                val value: TextToSpeech get() = inner
                private val inner = TextToSpeech(
                    applicationContext,
                    {
                        value.setLanguage(Locale("vi"))
                        value.speak(vietnamese, TextToSpeech.QUEUE_FLUSH,null,null)
                    }
                )
            }.value
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
        if (numberText.visibility == View.VISIBLE) {
            numberText.visibility = View.INVISIBLE
        } else {
            numberText.visibility = View.VISIBLE
        }
    }

    fun toggleVietnameseVisibility() {
        val vietnameseText = findViewById<TextView>(R.id.vietnameseText)
        if (vietnameseText.visibility == View.VISIBLE) {
            vietnameseText.visibility = View.INVISIBLE
        } else {
            vietnameseText.visibility = View.VISIBLE
        }
    }
}
