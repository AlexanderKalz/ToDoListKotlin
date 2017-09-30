package de.drkalz.todolist

/**
 * Created by Alex on 29.09.17.
 */

class ToDoListItem(var itemId: String, var item: String, var important: Boolean) {

    override fun toString(): String {
        return item
    }
}
