
package com.pitchperfect.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.pitchperfect.data.FirebaseRepository
import com.pitchperfect.model.User
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(nav: NavController) {
    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(1500)
        nav.navigate("login") { popUpTo("splash") { inclusive = true } }
    }
    Box(Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
        Column(horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally) {
            Text("P", style = MaterialTheme.typography.displayLarge, color = MaterialTheme.colorScheme.primary)
            Text("PitchPerfect", style = MaterialTheme.typography.headlineMedium)
            Text("Turn Ideas Into Reality", style = MaterialTheme.typography.bodySmall)
            CircularProgressIndicator(Modifier.padding(top=16.dp))
        }
    }
}

@Composable
fun LoginScreen(nav: NavController) {
    var email by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }
    val repo = remember { FirebaseRepository() }
    val scope = rememberCoroutineScope()
    Column(Modifier.padding(24.dp).fillMaxSize(), verticalArrangement = Arrangement.Center) {
        Text("Welcome Back", style = MaterialTheme.typography.headlineLarge)
        Text("Login with your university email", style = MaterialTheme.typography.bodyMedium)
        Spacer(Modifier.height(24.dp))
        OutlinedTextField(email, {email=it}, label={Text("University Email (.ac.za)")}, modifier=Modifier.fillMaxWidth())
        OutlinedTextField(pass, {pass=it}, label={Text("Password")}, modifier=Modifier.fillMaxWidth())
        if(error.isNotEmpty()) Text(error, color=MaterialTheme.colorScheme.error)
        Spacer(Modifier.height(16.dp))
        Button(onClick={
            if(!email.endsWith(".ac.za")) { error="Use .ac.za email"; return@Button }
            scope.launch {
                try { repo.login(email, pass); nav.navigate("home") } catch(e:Exception){ error=e.message?:"Login failed" }
            }
        }, modifier=Modifier.fillMaxWidth()) { Text("Login") }
        TextButton(onClick={ nav.navigate("register") }) { Text("Don't have account? Register") }
    }
}

@Composable
fun RegisterScreen(nav: NavController) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var uni by remember { mutableStateOf("University of Limpopo (UL)") }
    var pass by remember { mutableStateOf("") }
    var pass2 by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }
    val repo = remember { FirebaseRepository() }
    val scope = rememberCoroutineScope()
    Column(Modifier.padding(24.dp).fillMaxSize().verticalScroll(androidx.compose.foundation.rememberScrollState())) {
        Text("Create Account", style=MaterialTheme.typography.headlineLarge)
        Text("Join PitchPerfect +50 points", style=MaterialTheme.typography.bodyMedium)
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(name, {name=it}, label={Text("Full Name")}, modifier=Modifier.fillMaxWidth())
        OutlinedTextField(email, {email=it}, label={Text("University Email (.ac.za)")}, modifier=Modifier.fillMaxWidth())
        OutlinedTextField(pass, {pass=it}, label={Text("Password")}, modifier=Modifier.fillMaxWidth())
        OutlinedTextField(pass2, {pass2=it}, label={Text("Confirm Password")}, modifier=Modifier.fillMaxWidth())
        if(error.isNotEmpty()) Text(error, color=MaterialTheme.colorScheme.error)
        Spacer(Modifier.height(16.dp))
        Button(onClick={
            if(!email.endsWith(".ac.za")){ error="Use .ac.za"; return@Button }
            if(pass!=pass2){ error="Passwords don't match"; return@Button }
            scope.launch {
                try {
                    val user = User(name=name, email=email, university=uni, points=50)
                    repo.register(email, pass, user)
                    nav.navigate("home")
                } catch(e:Exception){ error=e.message?:"Failed" }
            }
        }, modifier=Modifier.fillMaxWidth()) { Text("Register +50 pts") }
    }
}
