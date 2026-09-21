
package com.pitchperfect.ui
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.navigation.compose.*
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = "splash") {
                    composable("splash") { SplashScreen(navController) }
                    composable("login") { LoginScreen(navController) }
                    composable("register") { RegisterScreen(navController) }
                    composable("home") { HomeDashboard(navController) }
                    composable("discover") { DiscoverScreen(navController) }
                    composable("projects") { ProjectFeedScreen(navController) }
                    composable("projectDetails/{id}") { ProjectDetailsScreen(navController) }
                    composable("messages") { MessagesScreen(navController) }
                    composable("mentors") { MentorSearchScreen(navController) }
                    composable("events") { EventsScreen(navController) }
                    composable("management/{id}") { ProjectManagementScreen(navController) }
                    composable("profile") { ProfileScreen(navController) }
                }
            }
        }
    }
}
