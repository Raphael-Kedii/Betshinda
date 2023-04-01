package com.boldcomp.betshinda

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class JackpotAdapter(var jackpot: List<Jackpot>):
    RecyclerView.Adapter<JackpotAdapter.JackpotViewHolder>(){
    inner class JackpotViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val competitionTV: TextView = itemView.findViewById(R.id.competitionTextView)
        private val teamATV: TextView = itemView.findViewById(R.id.teamATextView)
        private val teamBTV: TextView = itemView.findViewById(R.id.teamBTextView)
        private val tipTV: TextView = itemView.findViewById(R.id.tipTextView)
        private val timeTv: TextView = itemView.findViewById(R.id.timeTextView)

        fun bind(jackpot: Jackpot){
            competitionTV.text = jackpot.competition
            teamATV.text = jackpot.teamA
            teamBTV.text = jackpot.teamB
            tipTV.text = jackpot.tip
            timeTv.text = jackpot.time

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JackpotViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.jackpot_teams, parent, false)
        return JackpotViewHolder(view)
    }

    override fun onBindViewHolder(holder: JackpotViewHolder, position: Int) {
        holder.bind(jackpot[position])
    }

    override fun getItemCount(): Int {
        return jackpot.size
    }
}