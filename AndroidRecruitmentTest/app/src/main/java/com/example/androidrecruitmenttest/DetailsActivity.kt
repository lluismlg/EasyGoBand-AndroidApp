package com.example.androidrecruitmenttest

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.androidrecruitmenttest.CustomViewHolder.Companion.TICKET_ACCESS_GROUP_NAME_KEY
import com.example.androidrecruitmenttest.CustomViewHolder.Companion.TICKET_MODIFIED_KEY
import com.example.androidrecruitmenttest.CustomViewHolder.Companion.TICKET_NAME_KEY
import com.example.androidrecruitmenttest.CustomViewHolder.Companion.TICKET_SESSION_NAME_KEY
import com.example.androidrecruitmenttest.CustomViewHolder.Companion.TICKET_TOTAL_USES_KEY
import java.text.SimpleDateFormat

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

//      Get data from last activity
        val ticketAccessGroupName = intent.getStringExtra(TICKET_ACCESS_GROUP_NAME_KEY).toString()
        val ticketTotalUses = intent.getIntExtra(TICKET_TOTAL_USES_KEY, -1)
        val ticketSessionName = intent.getStringExtra(TICKET_SESSION_NAME_KEY).toString()
        val ticketName = intent.getStringExtra(TICKET_NAME_KEY).toString()
        val ticketModified = intent.getStringExtra(TICKET_MODIFIED_KEY).toString()


        println(ticketTotalUses)

//      Edit layout with ticket values
        findViewById<TextView>(R.id.Details_textView_accessGroupName)?.text = ticketAccessGroupName
        if (ticketTotalUses != -1) {
            findViewById<TextView>(R.id.Details_textView_totalUses_num)?.text =
                ticketTotalUses.toString()
        } else {
            findViewById<TextView>(R.id.Details_textView_totalUses_num)?.text = "Error"
        }
        findViewById<TextView>(R.id.Details_textView_totalUses_num)?.text =
            ticketTotalUses.toString()
        findViewById<TextView>(R.id.Details_textView_accessGroupName)?.text = ticketSessionName
        findViewById<TextView>(R.id.Details_textView_name)?.text = ticketName
        findViewById<TextView>(R.id.Details_textView_modified)?.text = formatDate(ticketModified)

//        findViewById<TextView>(R.id.Ticket_textView_name)?.text = ticketName
//        findViewById<TextView>(R.id.Ticket_textView_name)?.text = ticketName
//        findViewById<TextView>(R.id.Ticket_textView_name)?.text = ticketName
//        findViewById<TextView>(R.id.Ticket_textView_name)?.text = ticketName


    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun formatDate(date: String): CharSequence? {

        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val output = SimpleDateFormat("dd-MM-yyyy")
        val d = sdf.parse(date)

        return output.format(d)


    }
}