package com.example.rickandmorty

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_character.*

class CharacterListActivity: AppCompatActivity(), CharacterListView {
    private val adapter : CharacterListAdapter = CharacterListAdapter()
    private val presenter : CharacterListPresenter = CharacterListPresenter()

    override fun onStart() {
        super.onStart()
        presenter.fetchCharacters(intent.getStringArrayExtra("urls"))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character)
        presenter.subscribe(this)

        character_main?.adapter = adapter
    }


    override fun updateList(data: Array<CharacterResult>) {
        adapter.updateList(data)
    }

    override fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    override fun onDestroy() {
        presenter.unsubscribe()
        super.onDestroy()
    }
}