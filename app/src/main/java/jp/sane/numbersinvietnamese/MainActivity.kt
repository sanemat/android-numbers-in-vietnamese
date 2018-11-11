package jp.sane.numbersinvietnamese

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val results = mapOf("5" to "năm", "6" to "sáu", "103" to "một trăm lẻ ba")
        val englishWord = 103
        val key = englishWord.toString()
        val vietnameseWord = results[key]
        val keyView = findViewById<TextView>(R.id.key)
        keyView.text = key
        val wordView = findViewById<TextView>(R.id.word)
        wordView.text = vietnameseWord
    }
}
