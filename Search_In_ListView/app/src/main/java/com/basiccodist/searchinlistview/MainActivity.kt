package com.basiccodist.searchinlistview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.SearchView

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: ArrayAdapter<*>
    private lateinit var lv_listView: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,resources.getStringArray(R.array.product_array))
        lv_listView = findViewById(R.id.lv_ListView)
        lv_listView.adapter = adapter
        lv_listView.onItemClickListener =AdapterView.OnItemClickListener{parent, view, position, id -> val intent = Intent(parent.context,SecondActivity::class.java)
            //Use intent.putExtra to send data from this activity, Learn more about intent.putExtra on BasicCodist.wordpress.com
            parent.context.startActivity(intent)}
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu,menu)

        val search = menu?.findItem(R.id.nav_search)
        val searchView = search?.actionView as SearchView
        searchView.queryHint = "Search"

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }
}