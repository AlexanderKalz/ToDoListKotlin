package de.drkalz.todolist

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener {

            var addIntent = Intent(this, AddToDoActivity::class.java)
            startActivity(addIntent)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()

        val itemsList: MutableList<ToDoListItem> = mutableListOf()
        var lvItems = findViewById<ListView>(R.id.lvItems)

        var cAdapter: CustomAdapter
        cAdapter = CustomAdapter(applicationContext, itemsList)
        lvItems.adapter = cAdapter

        val ref = FirebaseDatabase.getInstance().getReference("toDoItems")
        val queryRef = ref.orderByKey()
        queryRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {}

            override fun onDataChange(p0: DataSnapshot?) {
                val children = p0!!.children
                children.forEach {
                    var item = ToDoListItem("", "", false)
                    item.itemId = it.child("itemId").value as String
                    item.item = it.child("item").value as String
                    item.important = it.child("important").value as Boolean
                    itemsList.add(item)
                }
                lvItems.invalidate()
                cAdapter.notifyDataSetChanged()
            }
        })

        lvItems.setOnItemClickListener { adapterView, view, i, l ->
            var selectedItem = itemsList[i]
            val deleteIntent = Intent(this, DeleteActivity::class.java)
            deleteIntent.putExtra("item", selectedItem.item)
            deleteIntent.putExtra("itemId", selectedItem.itemId)
            deleteIntent.putExtra("important", selectedItem.important)
            startActivity(deleteIntent)
        }
    }
}


