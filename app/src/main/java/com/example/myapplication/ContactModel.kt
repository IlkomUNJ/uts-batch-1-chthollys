package com.example.myapplication

import androidx.compose.runtime.toMutableStateList

data class ContactModel(
    val name: String,
    val address: String,
    val phone: String,
    val email: String
) {}

val contactSamples: List<ContactModel> =
    listOf(
        ContactModel(
            "Alpha",
            "Alpha Home Address",
            "081234567",
            "alpha@gmail.com"
        ),
        ContactModel(
            "Bravo",
            "Bravo Home Address",
            "082345678",
            "alpha@gmail.com"
        ),
        ContactModel(
            "Charlie",
            "Charlie Home Address",
            "083456789",
            "charlie@gmail.com"
        )
    )

class ContactRepository {
    private val _contacts = contactSamples.toMutableStateList()

    val contacts: MutableList<ContactModel> = _contacts

    fun addContact(contact: ContactModel) {
        _contacts.add(contact)
    }

    fun editContact(contact: ContactModel) {

    }
}