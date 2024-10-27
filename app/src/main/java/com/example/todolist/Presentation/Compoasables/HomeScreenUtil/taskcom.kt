package com.example.todolist.Presentation.Compoasables.HomeScreenUtil

import android.view.RoundedCorner
import androidx.compose.animation.slideIn
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todolist.Data.Task
import com.google.android.gms.maps.model.Circle

@Composable
fun taskComp(
    task: Task,
    checked: Boolean,
    onCheckAction: (Boolean) -> Unit,
    enable : Boolean = true
){
    Box(modifier = Modifier.fillMaxWidth().padding(16.dp).clip(shape = RoundedCornerShape(30.dp)).border(1.dp, color = Color.Black, shape = RoundedCornerShape(30.dp))) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = onCheckAction,
                enabled = enable
            )
            Text(text = task.title)
        }
    }
}

@Composable
fun CircleCheckBox(selected: Boolean,enable: Boolean,onCheckAction: () -> Unit){
    val color = MaterialTheme.colorScheme
    val imageVector = if (selected) Icons.Filled.CheckCircle else TODO()
    val tint = if(selected) color.primary.copy(alpha = 0.8f) else Color.White
    val background = if(selected) Color.White else Color.Transparent

    IconButton(onClick = { onCheckAction() }, enabled = enable, modifier = Modifier.offset(x = 4.dp, y = 4.dp)) {
        Icon(imageVector = imageVector, tint = tint,
            modifier = Modifier.background(background, shape = CircleShape),
            contentDescription = "checkbox")
    }
}




@Preview
@Composable
fun taskCompPreview(

){
    val checked = remember { mutableStateOf(false) };
    taskComp(task = Task(
      title = " Noice",
        description = "VeryNoice", timeStamp = System.currentTimeMillis(), isChecked = checked.value
    ),
        checked = checked.value,
        onCheckAction = {
            checked.value = it
        },
        enable = true
    )
}