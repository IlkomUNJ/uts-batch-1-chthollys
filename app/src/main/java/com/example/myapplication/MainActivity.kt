package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Surface {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    val contactRepository = remember { ContactRepository() }
    val contactViewModelFactory: ContactViewModelFactory = ContactViewModelFactory(contactRepository)
    val contactViewModel: ContactViewModel = viewModel(factory = contactViewModelFactory)
    Column {
        NavHost(navController = navController, startDestination = "dashboard") {
            composable("dashboard") {
                DashboardScreen(
                    contactViewModel,
                    navToContactFormScreen = { navController.navigate("contact-form") },
                )
            }
            composable("contact-form") {
                ContactFormScreen(
                    contactViewModel,
                    navToDashboard = { navController.navigate("dashboard") },
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
//    val contactViewModel: ContactViewModel = viewModel()
//    MyApplicationTheme {
//        MyApp(contactViewModel)
//    }
}
