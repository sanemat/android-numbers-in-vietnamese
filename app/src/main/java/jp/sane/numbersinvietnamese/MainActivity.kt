package jp.sane.numbersinvietnamese

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

        nextView.setOnClickListener {
            val index = random.nextInt(results.size)
            val key = results.keys.elementAt(index)
            val vietnameseWord = results[key]
            val keyView = findViewById<TextView>(R.id.numberText)
            keyView.text = key
            val wordView = findViewById<TextView>(R.id.vietnameseText)
            wordView.text = vietnameseWord
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
            val textToSpeech = object {
                val value: TextToSpeech get() = inner
                private val inner = TextToSpeech(
                    applicationContext,
                    {
                        value.setLanguage(Locale.UK)
                        value.speak("Hello World", TextToSpeech.QUEUE_FLUSH,null,null)
                    }
                )
            }.value
        }

        val index = random.nextInt(results.size)
        val key = results.keys.elementAt(index)
        val vietnameseWord = results[key]
        val keyView = findViewById<TextView>(R.id.numberText)
        keyView.text = key
        val wordView = findViewById<TextView>(R.id.vietnameseText)
        wordView.text = vietnameseWord
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
