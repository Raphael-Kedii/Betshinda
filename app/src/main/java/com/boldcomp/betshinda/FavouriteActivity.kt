package com.boldcomp.betshinda

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.*

class FavouriteActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var jackpotAdapter: JackpotAdapter
    private lateinit var database: DatabaseReference
    private lateinit var jackpotListener: ValueEventListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favourite)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        when (this) {
            is FavouriteActivity -> bottomNavigationView.selectedItemId = R.id.favourite
        }
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    startActivity(Intent(this,MainActivity::class.java))
                    true

                }
                R.id.top_tips -> {
                    startActivity(Intent(this,TopTipsActivity::class.java))
                    true

                }
                R.id.favourite -> {

                } else->false
            }
            true
        }
        recyclerView = findViewById(R.id.teamsRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        jackpotAdapter = JackpotAdapter(emptyList())
        recyclerView.adapter = jackpotAdapter


        database = FirebaseDatabase.getInstance().getReference("jackpots")

        jackpotListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val jackpots = mutableListOf<Jackpot>()

                for (jackpotsSnapshot in dataSnapshot.children) {
                    val team = jackpotsSnapshot.getValue(Jackpot::class.java)
                    team?.let { jackpots.add(it) }
                }

                jackpotAdapter.jackpot= jackpots
                jackpotAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w(ContentValues.TAG, "loadTasks:onCancelled", databaseError.toException())
            }
        }

        database.addValueEventListener(jackpotListener)


    }

}