package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ContactViewModel(val contactRepository: ContactRepository): ViewModel() {
    private val _contacts = contactRepository.contacts

    fun getContacts() = _contacts

    fun addContact(contact: ContactModel) {
        contactRepository.addContact(contact)
    }
}

class ContactViewModelFactory(
    private val repository: ContactRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        // Check if the ViewModel being created is our ContactViewModel
        if (modelClass.isAssignableFrom(ContactViewModel::class.java)) {
            // If it is, return a new instance with the repository
            @Suppress("UNCHECKED_CAST")
            return ContactViewModel(repository) as T
        }
        // Otherwise, throw an error
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}