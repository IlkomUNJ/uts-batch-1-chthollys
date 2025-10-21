
package com.example.myapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun ContactFormScreen(contactViewModel: ContactViewModel, navToDashboard: () -> Unit) {

    var name by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var errorLog by remember { mutableStateOf("") }


    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ) {
        Text("Create new contact", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(24.dp))

        Text("Name")
        OutlinedTextField(value = name, onValueChange = { name = it })

        Text("Address")
        OutlinedTextField(value = address, onValueChange = { address = it })

        Text("Phone")
        OutlinedTextField(value = phone, onValueChange = { phone = it })

        Text("Email")
        OutlinedTextField(value = email, onValueChange = { email = it })

        Spacer(modifier = Modifier.height(16.dp))
        Text(errorLog, fontWeight = FontWeight.Bold, color = Color.Red)

        Spacer(modifier = Modifier.height(24.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = navToDashboard) {
                Text("Back to dashboard")
            }
            Spacer(modifier = Modifier.width(24.dp))
            Button(onClick = {
                if (address.isEmpty() || email.isEmpty() || name.isEmpty() || phone.isEmpty()) {
                    errorLog = "All field is required"
                    return@Button
                }

                if (address.trim().length < 5) {
                    errorLog = "Address must at least 5 characters"
                    return@Button
                }
                errorLog = ""
                val newContact = ContactModel(name, address, phone, email)
                contactViewModel.addContact(newContact)
                navToDashboard()
            }) {
                Text("Submit")
            }
        }

    }
}
