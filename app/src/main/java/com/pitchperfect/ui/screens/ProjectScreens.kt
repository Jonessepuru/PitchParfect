
package com.pitchperfect.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pitchperfect.data.FirebaseRepository
import com.pitchperfect.model.Project
import kotlinx.coroutines.launch

@Composable
fun ProjectFeedScreen(nav: NavController) {
    val repo = remember { FirebaseRepository() }
    var projects by remember { mutableStateOf(listOf<Project>()) }
    var search by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("All") }
    val scope = rememberCoroutineScope()
    
    LaunchedEffect(Unit) {
        scope.launch { try { projects = repo.getProjects() } catch(_:Exception){} }
    }
    
    Column(Modifier.padding(16.dp)) {
        Text("Projects", style=MaterialTheme.typography.headlineSmall)
        OutlinedTextField(search, {search=it}, label={Text("Search projects...")}, modifier=Modifier.fillMaxWidth().padding(vertical=8.dp))
        Row(horizontalArrangement=Arrangement.spacedBy(8.dp)) {
            listOf("All","Tech","Health","Green","EdTech").forEach { cat ->
                FilterChip(category==cat, {category=cat}, label={Text(cat)})
            }
        }
        LazyColumn(verticalArrangement=Arrangement.spacedBy(12.dp), modifier=Modifier.padding(top=12.dp)) {
            items(projects.filter { (category=="All" || it.category==category) && (search.isEmpty() || it.title.contains(search, true)) }) { p ->
                Card(onClick={ nav.navigate("projectDetails/${p.projectId}") }, modifier=Modifier.fillMaxWidth()) {
                    Column(Modifier.padding(16.dp)) {
                        Row(horizontalArrangement=Arrangement.SpaceBetween, modifier=Modifier.fillMaxWidth()) {
                            AssistChip(onClick={}, label={Text(p.status)})
                            Text("${repo.calculateCompatibility(listOf(), p.requiredSkills).toInt()}% match", color=MaterialTheme.colorScheme.primary)
                        }
                        Text(p.title, style=MaterialTheme.typography.titleMedium, modifier=Modifier.padding(top=8.dp))
                        Text(p.description.take(100)+"...", style=MaterialTheme.typography.bodySmall)
                        Text("${p.members.size} members • ${p.category}", style=MaterialTheme.typography.labelSmall, modifier=Modifier.padding(top=8.dp))
                    }
                }
            }
        }
        FloatingActionButton(onClick={ /* open create */ }, modifier=Modifier.padding(top=16.dp)) { Text("+ New Project") }
    }
}

@Composable
fun ProjectDetailsScreen(nav: NavController) {
    Column(Modifier.padding(16.dp)) {
        Text("Project Details", style=MaterialTheme.typography.headlineSmall)
        // Full details + Join button + Tasks preview
    }
}

@Composable
fun ProjectManagementScreen(nav: NavController) {
    Column(Modifier.padding(16.dp)) {
        Text("Project Management", style=MaterialTheme.typography.headlineSmall)
        // Kanban Todo/Doing/Done
    }
}
