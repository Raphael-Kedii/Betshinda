package com.boldcomp.betshinda

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.tasks.Task
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var teamAdapter: TeamAdapter
    private lateinit var database: DatabaseReference
    private lateinit var teamListener: ValueEventListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        when (this) {
            is MainActivity -> bottomNavigationView.selectedItemId = R.id.home
        }
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {

                }
                R.id.top_tips -> {
                    startActivity(Intent(this,TopTipsActivity::class.java))
                    true

                }
                R.id.favourite -> {
                    startActivity(Intent(this,FavouriteActivity::class.java))
                    true
                } else->false
            }
            true
        }
        recyclerView = findViewById(R.id.teamsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        teamAdapter = TeamAdapter(emptyList())
        recyclerView.adapter = teamAdapter


        database = FirebaseDatabase.getInstance().getReference("teams")

        teamListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val teams = mutableListOf<Teams>()

                for (teamsSnapshot in dataSnapshot.children) {
                    val team = teamsSnapshot.getValue(Teams::class.java)
                    team?.let { teams.add(it) }
                }

                teamAdapter.teams = teams
                teamAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w(TAG, "loadTasks:onCancelled", databaseError.toException())
            }
        }

        database.addValueEventListener(teamListener)


    }

}