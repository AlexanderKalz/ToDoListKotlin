package de.drkalz.todolist

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DeleteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete)

        val tvItem = findViewById<TextView>(R.id.tvItem)
        val cbImp = findViewById<CheckBox>(R.id.cbImportant)
        val buDelete = findViewById<Button>(R.id.buDelete)

        val toDoItemID = intent.getStringExtra("item")


        val ref = FirebaseDatabase.getInstance().getReference("toDoItems").child(toDoItemID)
        val queryRef = ref.limitToFirst(1)
        queryRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onDataChange(p0: DataSnapshot?) {
                val children = p0!!.children
                children.forEach {
                    tvItem.text = it.child("item").value.toString()
                    cbImp.isChecked = it.child("important").value as Boolean
                }

            }
        })
    }
}
