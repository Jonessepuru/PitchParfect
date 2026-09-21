
package com.pitchperfect.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeDashboard(nav: NavController) {
    Column(Modifier.padding(16.dp), verticalArrangement=Arrangement.spacedBy(16.dp)) {
        Text("Hi! 👋", style=MaterialTheme.typography.headlineMedium)
        Card(Modifier.fillMaxWidth()) {
            Column(Modifier.padding(16.dp)) {
                Text("🔥 Points & Level", style=MaterialTheme.typography.titleMedium)
                LinearProgressIndicator(progress=0.5f, modifier=Modifier.fillMaxWidth().padding(top=8.dp))
            }
        }
        Row(horizontalArrangement=Arrangement.spacedBy(12.dp)) {
            Card(Modifier.weight(1f)) { Column(Modifier.padding(16.dp)) { Text("3"); Text("Projects", style=MaterialTheme.typography.labelSmall) } }
            Card(Modifier.weight(1f)) { Column(Modifier.padding(16.dp)) { Text("5"); Text("Matches", style=MaterialTheme.typography.labelSmall) } }
            Card(Modifier.weight(1f)) { Column(Modifier.padding(16.dp)) { Text("125"); Text("Points", style=MaterialTheme.typography.labelSmall) } }
        }
    }
}

@Composable
fun MessagesScreen(nav: NavController) {
    Column(Modifier.padding(16.dp)) {
        Text("Messages", style=MaterialTheme.typography.headlineSmall)
        Text("Direct + Group chats from matches & teams", style=MaterialTheme.typography.bodySmall)
    }
}

@Composable
fun MentorSearchScreen(nav: NavController) {
    Column(Modifier.padding(16.dp)) {
        Text("Mentors", style=MaterialTheme.typography.headlineSmall)
        Text("Verified mentors filtered by expertise & industry", style=MaterialTheme.typography.bodySmall)
    }
}

@Composable
fun EventsScreen(nav: NavController) {
    Column(Modifier.padding(16.dp)) {
        Text("Events & Community", style=MaterialTheme.typography.headlineSmall)
        Text("Entrepreneurship events & groups", style=MaterialTheme.typography.bodySmall)
    }
}

@Composable
fun ProfileScreen(nav: NavController) {
    Column(Modifier.padding(16.dp)) {
        Text("Profile", style=MaterialTheme.typography.headlineSmall)
        Text("Edit skills, interests, points, badges", style=MaterialTheme.typography.bodySmall)
    }
}
