package dev.xascar.contactsplayer.ui.contacts

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import dev.xascar.contactsplayer.R
import dev.xascar.contactsplayer.databinding.FragmentContactsBinding

private const val TAG = "ContactsFragment"
private const val REQUEST_CODE_READ_CONTACTS = 1

class ContactsFragment : Fragment() {

    private var _binding: FragmentContactsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var contactNames: ListView
    private lateinit var mainActivity: AppCompatActivity


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentContactsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivity = activity as AppCompatActivity
        contactNames = binding.contactNames

        val hasReadContactsPermission = ContextCompat.checkSelfPermission(mainActivity,
            Manifest.permission.READ_CONTACTS
        )
        Log.d(TAG, "onCreate: checkSelfPermission returned $hasReadContactsPermission")

        /*if (hasReadContactsPermission == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "onCreate: permission granted")
            // readGranted = true
        } else {
            Log.d(TAG, "onCreate: requesting permission")
            ActivityCompat.requestPermissions(this, arrayOf(READ_CONTACTS), REQUEST_CODE_READ_CONTACTS)
        }*/

        if (hasReadContactsPermission == PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "onCreate: requesting permission")
            ActivityCompat.requestPermissions(mainActivity, arrayOf(Manifest.permission.READ_CONTACTS), REQUEST_CODE_READ_CONTACTS)
        }


            // if (readGranted) {
            if (ContextCompat.checkSelfPermission(mainActivity,
                    Manifest.permission.READ_CONTACTS
                ) == PackageManager.PERMISSION_GRANTED) {
                val projection = arrayOf(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY);

                val cursor = activity?.contentResolver?.query(
                    ContactsContract.Contacts.CONTENT_URI,
                    projection,
                    null,
                    null,
                    ContactsContract.Contacts.DISPLAY_NAME_PRIMARY)

                val contacts = ArrayList<String>()
                cursor?.use {
                    while (it.moveToNext()) {
                        contacts.add(it.getString(it.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME_PRIMARY)))
                    }
                }

                val adapter = ArrayAdapter<String>(mainActivity, R.layout.contact_detail, R.id.name, contacts)
                contactNames.adapter = adapter
            } else {
                Snackbar.make(view, "Please grant access to your Contacts", Snackbar.LENGTH_LONG).setAction("Grant Access") {
                    Log.d(TAG, "Snackbar onClick: starts")

                    if (ActivityCompat.shouldShowRequestPermissionRationale(mainActivity,
                            Manifest.permission.READ_CONTACTS
                        )) {
                        Log.d(TAG, "Snackbar onClick: calling request permission")
                        ActivityCompat.requestPermissions(
                            mainActivity,
                            arrayOf(Manifest.permission.READ_CONTACTS),
                            REQUEST_CODE_READ_CONTACTS
                        )
                    } else {
                        Log.d(TAG, "Snackbar onClick: calling request permission")
                        val intent = Intent()
                        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                        val uri = Uri.fromParts("package", mainActivity.packageName, null)
                        Log.d(TAG, "Snackbar onClick: Uri is $uri")
                        intent.data = uri
                        this.startActivity(intent)
                    }
                    Log.d(TAG, "Snackbar onClick: ends")

                    Toast.makeText(it.context, "Snackbar action clicked", Toast.LENGTH_SHORT).show()
                }.show()
            }

            Log.d(TAG, "Fab onClick: ends")

        Log.d(TAG, "onCreate: ends")

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}