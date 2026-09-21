
package com.pitchperfect.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SkillChip(skill: String, onRemove: (() -> Unit)? = null) {
    AssistChip(onClick={ onRemove?.invoke() }, label={Text(skill)}, trailingIcon={ if(onRemove!=null) Text("×") })
}

@Composable
fun CompatibilityCircle(percent: Int) {
    Box {
        CircularProgressIndicator(progress=percent/100f)
        Text("$percent%", modifier=Modifier.padding(8.dp))
    }
}

@Composable
fun ProjectCard(title: String, category: String, compat: Int) {
    Card(Modifier.fillMaxWidth()) {
        Column(Modifier.padding(12.dp)) {
            Text(title, style=MaterialTheme.typography.titleSmall)
            Text(category, style=MaterialTheme.typography.labelSmall)
            Text("$compat% match", color=MaterialTheme.colorScheme.primary)
        }
    }
}
