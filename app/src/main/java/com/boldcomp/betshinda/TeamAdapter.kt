package com.boldcomp.betshinda

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TeamAdapter(var teams: List<Teams>):
RecyclerView.Adapter<TeamAdapter.TeamViewHolder>(){
    inner class TeamViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val competitionTV: TextView = itemView.findViewById(R.id.competitionTextView)
        private val teamATV:TextView = itemView.findViewById(R.id.teamATextView)
        private val teamBTV:TextView = itemView.findViewById(R.id.teamBTextView)
        private val tipTV:TextView = itemView.findViewById(R.id.tipTextView)

        fun bind(teams: Teams){
            competitionTV.text = teams.competition
            teamATV.text = teams.teamA
            teamBTV.text = teams.teamB
            tipTV.text = teams.tip

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.teams_item, parent, false)
        return TeamViewHolder(view)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bind(teams[position])
    }

    override fun getItemCount(): Int {
        return teams.size
    }
}