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

class TopTipsActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var hotAdapter: HotAdapter
    private lateinit var database: DatabaseReference
    private lateinit var hotListener: ValueEventListener
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_tips)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        when (this) {
            is TopTipsActivity -> bottomNavigationView.selectedItemId = R.id.top_tips
        }
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    startActivity(Intent(this,MainActivity::class.java))
                    true

                }
                R.id.top_tips -> {


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

        hotAdapter = HotAdapter(emptyList())
        recyclerView.adapter = hotAdapter


        database = FirebaseDatabase.getInstance().getReference("hots")

        hotListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val hots = mutableListOf<Hots>()

                for (hotsSnapshot in dataSnapshot.children) {
                    val hot = hotsSnapshot.getValue(Hots::class.java)
                    hot?.let { hots.add(it) }
                }

                hotAdapter.hots = hots
                hotAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w(ContentValues.TAG, "loadTasks:onCancelled", databaseError.toException())
            }
        }

        database.addValueEventListener(hotListener)


    }

}