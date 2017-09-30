package de.drkalz.todolist

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import com.google.firebase.database.FirebaseDatabase

class AddToDoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_to_do)

        title = "New To Do"

        val etItem = findViewById<EditText>(R.id.etToDoItem)
        val cbImportant = findViewById<CheckBox>(R.id.cbImportant)
        val buAdd = findViewById<Button>(R.id.buAdd)

        buAdd.setOnClickListener {

            if (etItem.text.isEmpty()) {
                etItem.error = "Please enter Item"
                return@setOnClickListener
            }

            val ref = FirebaseDatabase.getInstance().getReference("toDoItems")
            val newItem = ToDoListItem(ref.push().key, etItem.text.toString(), cbImportant.isChecked)

            ref.child(newItem.itemId).setValue(newItem).addOnCompleteListener {
                finish()
            }
        }

    }
}
