
package com.pitchperfect.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pitchperfect.data.FirebaseRepository
import com.pitchperfect.model.User

@Composable
fun DiscoverScreen(nav: NavController) {
    val repo = remember { FirebaseRepository() }
    var users by remember { mutableStateOf(listOf<User>()) }
    var index by remember { mutableStateOf(0) }
    var filter by remember { mutableStateOf("All") }
    
    // Mock load - replace with Firestore query
    LaunchedEffect(Unit) {
        // users = repo.getRecommendedUsers()
    }
    
    Column(Modifier.padding(16.dp)) {
        Text("Discover Collaborators", style=MaterialTheme.typography.headlineSmall)
        Row(Modifier.padding(vertical=8.dp), horizontalArrangement=Arrangement.spacedBy(8.dp)) {
            listOf("All","Tech","Design","Business").forEach { cat ->
                FilterChip(filter==cat, {filter=cat}, label={Text(cat)})
            }
        }
        if(users.isEmpty()) {
            // Placeholder card
            Card(Modifier.fillMaxWidth().height(400.dp)) {
                Column(Modifier.padding(16.dp)) {
                    Text("Sarah Nkosi - University of Limpopo")
                    Text("UI/UX Designer - EdTech", style=MaterialTheme.typography.bodySmall)
                    Text("87% match", color=MaterialTheme.colorScheme.primary)
                    Spacer(Modifier.height(16.dp))
                    Row(horizontalArrangement=Arrangement.spacedBy(16.dp)) {
                        OutlinedButton(onClick={ index++ }) { Text("Pass ✕") }
                        Button(onClick={
                            // create match + points
                            index++
                        }) { Text("Like ♥") }
                    }
                }
            }
        }
    }
}
