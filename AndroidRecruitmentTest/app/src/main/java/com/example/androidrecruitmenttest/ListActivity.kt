package com.example.androidrecruitmenttest

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import okhttp3.*
import java.io.IOException
import java.lang.reflect.Type


class ListActivity : AppCompatActivity() {

    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)


        val recyclerviewList = findViewById<RecyclerView>(R.id.recyclerView_list)

        recyclerviewList.layoutManager = LinearLayoutManager(this)
//        recyclerview_list.adapter = ListAdapter();

        // Get the Intent that started this activity and extract the string
        val parameter = intent.getStringExtra(EXTRA_MESSAGE).toString()

        Log.i("", parameter)

        fetchJson(parameter)

        // Edit actionbar
        val listActionbar = supportActionBar
//        listActionbar!!.title = "Events List"
        //set back button
        listActionbar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun fetchJson(parameter: String) {

        val recyclerviewList = findViewById<RecyclerView>(R.id.recyclerView_list)

//        val url = "https://pnny0h3cuf.execute-api.eu-west-1.amazonaws.com/dev/providers/1"
//        val authValue = "Basic cJmAc71jah17sgqi1jqaksvaksda="

        val url = "https://easygobandrecruitmentbackend.herokuapp.com/?sessionName=$parameter"

        val client = OkHttpClient()


        val request = Request.Builder()
            .url(url)
//            .addHeader("Authorization", authValue)
            .build()


        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()

                if (body == "[]") {
                    runOnUiThread {
                        findViewById<TextView>(R.id.ListTextViewError).visibility = View.VISIBLE
                    }
                } else {
                    val gson = GsonBuilder().create()

                    val listType: Type = object : TypeToken<List<Ticket?>?>() {}.type

                    val ticketFeed: List<Ticket> = gson.fromJson(body, listType)

                    runOnUiThread {
                        recyclerviewList.adapter = ListAdapter(ticketFeed)
                    }
                }
            };

            override fun onFailure(call: Call, e: IOException) {}
        })
    }


}

class Test(val test: List<TicketFeed>)

class TicketFeed(val tickets: List<Ticket>)

class Ticket(
    val access_group_id: Int,
    val access_group_name: String,
    val basic_product_id: Int,
    val event_id: Int,
    val id: Int,
    val modified: String,
    val name: String,
    val sessions: List<Session>,
    val structure_decode: Boolean,
    val total_uses: Int
)

class Session(
    val id: Int,
    val name: String
)

