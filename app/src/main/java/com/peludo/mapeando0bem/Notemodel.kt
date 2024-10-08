package com.peludo.mapeando0bem

import android.provider.ContactsContract

data class Notemodel (

    val notes: List<Data>
   // <ContactsContract.RawContacts.Data>


)
{

    data class Data ( val nick: String, val email: String,  val senha: String, val id: Int)

}
