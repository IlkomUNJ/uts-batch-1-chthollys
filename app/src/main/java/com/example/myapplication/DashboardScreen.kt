package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun ContactTable(contacts: MutableList<ContactModel>) {
    val headers = listOf("name", "address", "phone", "email")
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)) {
        Row(
            Modifier
                .fillMaxWidth()
                .background(Color.Gray)
                .padding(8.dp)
        ) {
            Text(
                text = headers[0],
                modifier = Modifier.weight(0.2f),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = headers[1],
                modifier = Modifier.weight(0.3f),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = headers[2],
                modifier = Modifier.weight(0.25f),
                fontWeight = FontWeight.Bold
            )
            Text(
                text = headers[3],
                modifier = Modifier.weight(0.25f),
                fontWeight = FontWeight.Bold
            )
        }

        Column(modifier = Modifier.fillMaxWidth()) {
            contacts.forEach { contact ->
                ContactRow(contact)
            }
        }
    }
}

@Composable
fun ContactRow(contact: ContactModel) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        // These weights now match the header
        Text(text = contact.name, modifier = Modifier.weight(0.2f))
        Text(text = contact.address, modifier = Modifier.weight(0.3f))
        Text(text = contact.phone, modifier = Modifier.weight(0.25f))
        Text(text = contact.email, modifier = Modifier.weight(0.25f))
    }
}

@Composable
fun DashboardScreen(contactViewModel: ContactViewModel, navToContactFormScreen: () -> Unit) {
    val contacts = contactViewModel.getContacts()

    Scaffold (
        floatingActionButton = {
            Button(onClick = navToContactFormScreen) {
                Text("Add new Contact")
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { padding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Dashboard Contact", fontSize = 32.sp, fontWeight = FontWeight(600))
                Spacer(modifier = Modifier.height(16.dp))
            }

            Spacer(modifier = Modifier.height(16.dp)) // <-- Add spacer

            ContactTable(contacts = contacts)
        }
    }
}