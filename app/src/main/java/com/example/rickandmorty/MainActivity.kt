package com.example.rickandmorty

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), LocationListView, ClickListener{

    private val adapter : LocationListAdapter = LocationListAdapter(this)
    private val presenter : LocationListPresenter = LocationListPresenter()

    override fun onStart() {
        super.onStart()
        presenter.fetchLocations()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.subscribe(this)

        location_list?.layoutManager = LinearLayoutManager(this)
        location_list?.adapter = adapter
    }


    override fun updateList(data: Array<Location>) {
        adapter.updateList(data)
    }

    override fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        presenter.unsubscribe()
        super.onDestroy()
    }

    override fun onClickItem(urls: Array<String>) {
        val intent = Intent(this, CharacterListActivity::class.java)
        intent.putExtra("urls", urls)
        startActivity(intent)
    }
}