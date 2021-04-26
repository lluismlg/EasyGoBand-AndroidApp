package com.example.androidrecruitmenttest

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class ListAdapter(private val ticketFeed: List<Ticket>) : RecyclerView.Adapter<CustomViewHolder>() {


    // number of items in recycler view
    override fun getItemCount(): Int {
        return ticketFeed.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.ticket_row, parent, false)

        return CustomViewHolder((cellForRow))
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val ticket = ticketFeed[position]

        holder?.view?.findViewById<TextView>(R.id.Ticket_textView_name)?.text = ticket.name
        holder?.view?.findViewById<TextView>(R.id.Ticket_textView_accessGroupName)?.text =
            ticket.access_group_name
        holder?.view?.findViewById<TextView>(R.id.Ticket_textView_totalUsesNum)?.text =
            ticket.total_uses.toString()

        holder?.ticket = ticket
    }
}

class CustomViewHolder(val view: View, var ticket: Ticket? = null) : RecyclerView.ViewHolder(view) {
    companion object {
        const val TICKET_ACCESS_GROUP_NAME_KEY = "TICKET_ACCESS_GROUP_NAME"
        const val TICKET_TOTAL_USES_KEY = "TICKET_TOTAL_USES"
        const val TICKET_SESSION_NAME_KEY = "TICKET_SESSION_NAME"
        const val TICKET_NAME_KEY = "TICKET_NAME"
        const val TICKET_MODIFIED_KEY = "TICKET_MODIFIED"
    }

    init {
        view.setOnClickListener {
            val intent = Intent(view.context, DetailsActivity::class.java)

            intent.putExtra(TICKET_ACCESS_GROUP_NAME_KEY, ticket?.access_group_name)
            intent.putExtra(TICKET_TOTAL_USES_KEY, ticket?.total_uses)
            intent.putExtra(TICKET_SESSION_NAME_KEY, ticket?.sessions?.get(0)?.name)
            intent.putExtra(TICKET_NAME_KEY, ticket?.name)
            intent.putExtra(TICKET_MODIFIED_KEY, ticket?.modified)


            view.context.startActivity(intent)
        }
    }
}