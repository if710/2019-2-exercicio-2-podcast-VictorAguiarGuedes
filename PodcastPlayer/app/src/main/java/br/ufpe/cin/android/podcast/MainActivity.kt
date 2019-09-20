package br.ufpe.cin.android.podcast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import java.net.URL
import androidx.recyclerview.widget.LinearLayoutManager
import org.jetbrains.anko.uiThread


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        doAsync {
            try {
                //Lendo o conteúdo do XML armazenado na URL
                val xmlText = URL("https://s3-us-west-1.amazonaws.com/podcasts.thepolyglotdeveloper.com/podcast.xml").readText()
                var itemFeedList : List<ItemFeed>? = null
                itemFeedList = Parser.parse(xmlText)

                uiThread {
                    //Setando um LinearLayout para organizar a estrutura dos elementos do RecyclerView
                    listRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                    //Definindo o adapter
                    listRecyclerView.adapter = ItemFeedCustomAdapter(itemFeedList, this@MainActivity)
                    //Dividers entre os itens
                    listRecyclerView.addItemDecoration(DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL))
                }

            } catch (e: InterruptedException) {
                Log.e("Erro","Erro ao ler XML")
            }
        }
    }

}
