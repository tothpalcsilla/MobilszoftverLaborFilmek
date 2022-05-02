package com.example.mobilszoftverlabormovies.ui.details

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.mobilszoftverlabormovies.R
import com.example.mobilszoftverlabormovies.ui.list.ListActivity
import com.example.mobilszoftverlabormovies.ui.list.ListViewModel

class DetailActivity : AppCompatActivity() {

    private val detailViewModel: DetailsViewModel = DetailsViewModel(this.application, "asd123") //TODO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_detail)
        setSupportActionBar(findViewById(R.id.detail_toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (savedInstanceState == null) {
            val fragment = DetailFragment()
                .apply {
                arguments = Bundle().apply {
                    putString(
                        DetailFragment.ARG_ITEM_ID,
                        intent.getStringExtra(DetailFragment.ARG_ITEM_ID))
                }
            }
            supportFragmentManager.beginTransaction()
                .add(R.id.item_detail_container, fragment)
                .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            android.R.id.home -> {
                navigateUpTo(Intent(this, ListActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
}