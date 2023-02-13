package dev.xascar.contactsplayer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
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
        var user = findViewById<TextInputEditText>(R.id.tiet_user).text.toString()
        var password = findViewById<TextInputEditText>(R.id.tiet_password).text.toString()

        findViewById<Button>(R.id.mb_login).setOnClickListener {
            if (BuildConfig.DEBUG){
                user = "admin@mail.com"
                password = "654321"

            }
            if (user.isNullOrEmpty() or password.isNullOrEmpty()) Toast.makeText(this,INCOMPLETE_DATA,Toast.LENGTH_LONG).show()
            else {
                if (logins.containsKey(user).run { logins[user].equals(password)})
                    startActivity(Intent(this, MusicContacts::class.java)).also { finish() }
                else
                    Toast.makeText(this,FAILURE_LOGIN,Toast.LENGTH_LONG).show()
            }
        }
    }
}