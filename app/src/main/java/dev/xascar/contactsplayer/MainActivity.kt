package dev.xascar.contactsplayer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

val logins: Map<String,String> = mapOf("test@mail.com" to "123456",
    "admin@mail.com" to "654321",
    "info@mail.com" to "Adm**",
    "user@mail.com" to "SomethingSomething"
)

const val INCOMPLETE_DATA = "Please provide a valid User and Password first"
const val FAILURE_LOGIN = "Incorrect user or password"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val user = findViewById<TextInputEditText>(R.id.tiet_user).text
        val password = findViewById<TextInputEditText>(R.id.tiet_password).text
        findViewById<MaterialButton>(R.id.mb_login).setOnClickListener {
            if (user.isNullOrEmpty() or password.isNullOrEmpty()) Toast.makeText(this,INCOMPLETE_DATA,Toast.LENGTH_LONG).show()
            else {
                if (logins.containsKey(user.toString()).run { logins[user.toString()].equals(password.toString())})
                    startActivity(Intent(this, MediaContacts::class.java)).also { finish() }
                else
                    Toast.makeText(this,FAILURE_LOGIN,Toast.LENGTH_LONG).show()
            }
        }
    }
}