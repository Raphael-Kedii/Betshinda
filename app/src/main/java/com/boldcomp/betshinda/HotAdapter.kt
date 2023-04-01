package com.boldcomp.betshinda

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HotAdapter(var hots: List<Hots>):
RecyclerView.Adapter<HotAdapter.HotViewHolder>(){
    inner class HotViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val competitionTV: TextView = itemView.findViewById(R.id.competitionTextView)
        private val teamATV: TextView = itemView.findViewById(R.id.teamATextView)
        private val teamBTV: TextView = itemView.findViewById(R.id.teamBTextView)
        private val tipTV: TextView = itemView.findViewById(R.id.tipTextView)

        fun bind(hots: Hots){
            competitionTV.text = hots.competition
            teamATV.text = hots.teamA
            teamBTV.text = hots.teamB
            tipTV.text = hots.tip
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotAdapter.HotViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.hots_items, parent, false)
        return HotViewHolder(view)
    }

    override fun onBindViewHolder(holder: HotAdapter.HotViewHolder, position: Int) {
        holder.bind(hots[position])
    }

    override fun getItemCount(): Int {
        return hots.size
    }
}