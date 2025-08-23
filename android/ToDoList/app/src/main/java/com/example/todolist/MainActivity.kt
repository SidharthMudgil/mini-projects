package com.example.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.style.TextDecoration
import com.example.todolist.ui.theme.ToDoListTheme


data class TodoItem(
    //setting unique identifier for each item
    val id: Int,
    val task: String,
    val isCompleted: Boolean = false //Default to not completed
    //duedate, priority.
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToDoListTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TodoListApp()
                }
            }
        }
    }
}

@Composable
fun TodoListApp(modifier: Modifier = Modifier){
    //this is the state to hold the list of todo items
    //mutableStateListOf - used for lists where items will be added, removed and reordered
    //priority settings
    //and compose for observing these changes.
    val todoItems = remember{ mutableStateListOf<TodoItem>()}
    var nextId by remember {mutableStateOf(1)} //for generating unique IDs

    // State for the text input field
    var newTaskText by remember { mutableStateOf("")}

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {}
    ) {
            innerPadding -> Column(
        modifier = Modifier
            .padding(innerPadding)
            .padding(16.dp) //overall padding
    ) {
        //input field and add button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            OutlinedTextField(
                value = newTaskText,
                onValueChange = {newTaskText = it},
                label = {Text("New Task")},
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                if(newTaskText.isNotBlank()){
                    todoItems.add(TodoItem(id = nextId++, task = newTaskText))
                    newTaskText = ""
                }
            }) {
                Text("ADD")
            }
        }

        //listing to-do items
        if(todoItems.isEmpty()){
            Text("No task listed. Add Tasks!")
        }
        else{
            LazyColumn{
                items(items = todoItems, key = { it.id }) {
                        item -> TodoRow(
                    item = item,
                    onItemCheckedChange = {newItemState ->
                        val index = todoItems.indexOfFirst{ it.id == newItemState.id}
                        if (index != -1){
                            todoItems[index] = newItemState.copy(isCompleted = !newItemState.isCompleted)
                        }
                    },
                    onItemDelete = {
                            itemToDelete -> todoItems.remove(itemToDelete)
                    }
                )
                }
            }
        }
    }
    }
}

@Composable
fun TodoRow(
    item: TodoItem,
    onItemCheckedChange: (TodoItem) -> Unit,
    onItemDelete: (TodoItem) -> Unit,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier.fillMaxWidth().padding(vertical = 8.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = item.isCompleted,
            onCheckedChange = {onItemCheckedChange(item)}
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = item.task,
            modifier = Modifier.weight(1f),
            style = if(item.isCompleted){
                MaterialTheme.typography.bodyLarge.copy(
                    textDecoration = TextDecoration.LineThrough,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                )
            } else{
                MaterialTheme.typography.bodyLarge
            }
        )
        Spacer(
            modifier = Modifier.width(8.dp)
        )
        Button(onClick = {onItemDelete(item) }){
            Text("Remove")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TodoListAppPreview(){
    ToDoListTheme{
        TodoListApp()
    }
}

@Preview(showBackground = true)
@Composable
fun TodoRowPreview(){
    ToDoListTheme{
        val sampleItem = TodoItem(1, "Buy milk", false)
        TodoRow(item = sampleItem, onItemCheckedChange = {}, onItemDelete = {})
    }
}

@Preview(showBackground = true)
@Composable
fun TodoRowCompletedPreview(){
    ToDoListTheme{
        val sampleItem = TodoItem(1, "finish report", true)
        TodoRow(item = sampleItem, onItemCheckedChange = {}, onItemDelete = {})
    }
}