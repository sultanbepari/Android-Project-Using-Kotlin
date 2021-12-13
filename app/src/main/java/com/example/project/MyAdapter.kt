package com.example.project

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.media.Image
import android.net.Uri
import android.provider.AlarmClock
import android.provider.CalendarContract
import android.provider.ContactsContract
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import java.security.AccessControlContext
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.ContextCompat.startActivity
import android.content.ComponentName
import android.content.ActivityNotFoundException
import androidx.core.app.ActivityCompat

import androidx.core.content.ContextCompat.startActivity
import androidx.core.app.ActivityCompat.startActivityForResult

class MyAdapter(val list : ArrayList<ActList>, val context: Context):
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        fun binding(lists : ActList){
            val names = itemView.findViewById<TextView>(R.id.appTitle)
            val image: ImageView? = itemView.findViewById<ImageView>(R.id.appIcon)

            names.setText(lists.name)
            if (image != null) {
                image.setImageResource(lists.imageID)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val myview = LayoutInflater.from(parent.context).inflate(R.layout.items,parent,false)
        return ViewHolder(myview)
    }

    @SuppressLint("QueryPermissionsNeeded")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding(list[position])

        holder.itemView.setOnClickListener {
            if (position == 0){
                Toast.makeText(context, "Google Maps Opened", Toast.LENGTH_LONG).show()
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("geo:15.3647,75.1240")
                context.startActivity(intent)
            }
            if (position == 1){
                Toast.makeText(context, "Calculator Opened", Toast.LENGTH_LONG).show()
                val intent = Intent(context,Calculator::class.java)
                context.startActivity(intent)
            }
            if (position == 2){
                Toast.makeText(context, "Calender Opened", Toast.LENGTH_LONG).show()
                val intent = Intent(Intent.ACTION_INSERT)
                intent.data = CalendarContract.Events.CONTENT_URI
                intent.putExtra(CalendarContract.Events.TITLE, "L&T TS")
                intent.putExtra(CalendarContract.Events.EVENT_LOCATION, "Mysore")
                intent.putExtra(CalendarContract.Events.DESCRIPTION, "Trip")
                intent.putExtra(CalendarContract.Events.ALL_DAY, true)
                intent.putExtra(Intent.EXTRA_EMAIL, "test@yahoo.com")
                context.startActivity(intent);
            }
            if (position == 3){
                Toast.makeText(context, "Dial Pad Opened", Toast.LENGTH_LONG).show()
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:+91 8867127270")
                context.startActivity(intent)

            }
            if (position == 4){
                Toast.makeText(context, "Contacts Opened", Toast.LENGTH_LONG).show()
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = ContactsContract.Contacts.CONTENT_TYPE
                context.startActivity(intent)
            }
            if (position == 5){
                Toast.makeText(context, "Browser Opened", Toast.LENGTH_LONG).show()
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse("https://www.google.co.in/")
                context.startActivity(intent)
            }
            if (position == 6){
                Toast.makeText(context, "Camera Opened", Toast.LENGTH_LONG).show()
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                context.startActivity(intent)
            }
            if (position == 7){
                Toast.makeText(context, "Alarm Opened", Toast.LENGTH_LONG).show()
                val openClockIntent = Intent(AlarmClock.ACTION_SET_ALARM)
                openClockIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(openClockIntent)
            }
            if (position == 8){
                Toast.makeText(context, "Email Opened", Toast.LENGTH_LONG).show()
                val intent = Intent(Intent.ACTION_SENDTO)
                intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("sultanbepari2732000@gmail.com"))
                intent.putExtra(Intent.EXTRA_SUBJECT, "Test Mail")
                intent.putExtra(Intent.EXTRA_TEXT, "Hi, I am Sultan")
                intent.data = Uri.parse("mailto:")
                if (intent.resolveActivity(context.packageManager) != null) {
                    context.startActivity(intent)
                } else {
                    Toast.makeText(context, "There is no application that support this action", Toast.LENGTH_SHORT).show()
                }
            }
            if (position == 9){
                Toast.makeText(context, "Message Opened", Toast.LENGTH_LONG).show()
                val intent = Intent(Intent.ACTION_VIEW)

                intent.data = Uri.parse("smsto:")
                intent.type = "vnd.android-dir/mms-sms"
                intent.putExtra("address", "9740025645")
                intent.putExtra("sms_body", "Hi, I am Sultan")
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}