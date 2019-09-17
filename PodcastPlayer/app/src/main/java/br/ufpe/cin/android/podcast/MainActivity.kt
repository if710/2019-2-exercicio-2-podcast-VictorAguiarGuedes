package br.ufpe.cin.android.podcast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.jetbrains.anko.doAsync
import java.net.URL


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        doAsync {
            try {
                //Lendo o conteúdo do XML armazenado na URL
                val xmlText = URL("https://s3-us-west-1.amazonaws.com/podcasts.thepolyglotdeveloper.com/podcast.xml").readText()
                Log.e("Retorno",Parser.parse(xmlText).toString())
            } catch (e: InterruptedException) {
                Log.e("Erro","Erro ao ler XML")
            }
        }
    }

}
