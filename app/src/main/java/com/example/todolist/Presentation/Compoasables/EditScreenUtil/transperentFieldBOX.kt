package com.example.todolist.Presentation.Compoasables.EditScreenUtil

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged

@Composable
fun TransperentTitleFieldBox(
    text : String,
    isHintShown : Boolean = false,
    singleLine : Boolean = false,
    onContentChange: (String) ->Unit,
    onFocusChange: (FocusState) ->Unit,
    modifier: Modifier
){
    Box(modifier = modifier){
        BasicTextField(value = text, onValueChange = onContentChange,modifier = Modifier.fillMaxWidth().onFocusChanged {
            onFocusChange(it)
        }, textStyle = MaterialTheme.typography.headlineLarge
            , singleLine = singleLine
        )
        if(isHintShown){
            Text("Title")
        }
    }
}

@Composable
fun TransperentDescriptionFieldBox(
    text : String,
    isHintShown : Boolean = false,
    singleLine : Boolean = false,
    onContentChange: (String) ->Unit,
    onFocusChange: (FocusState) ->Unit,
    modifier: Modifier
){
    Box(modifier = modifier){
        BasicTextField(value = text, onValueChange = onContentChange,modifier = Modifier.fillMaxWidth().onFocusChanged {
            onFocusChange(it)
        }, textStyle = MaterialTheme.typography.headlineLarge
            , singleLine = singleLine
        )
        if(isHintShown){
            Text("Description")
        }
    }
}