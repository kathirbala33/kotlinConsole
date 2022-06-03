package com.myconsolekotlin.app.alert

import android.annotation.SuppressLint
import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.myconsolekotlin.app.R
import com.myconsolekotlin.app.databinding.ActivityAlertBinding
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class AlertActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {
    var binding: ActivityAlertBinding? = null
    val steing : String = "abcd";
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlertBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        alertSet();
    }

    private fun alertSet() {
        val calendar: Calendar = Calendar.getInstance()
        val houer = calendar.get(Calendar.HOUR)
        val min = calendar.get(Calendar.MINUTE)
        val sec = calendar.get(Calendar.SECOND)
        val timePickerDialog = TimePickerDialog(this, this, houer, min, false)
        timePickerDialog.show()
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

    }

    @SuppressLint("SimpleDateFormat")
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        val hour = hourOfDay;
        val minute = minute;
        val calendar: Calendar = Calendar.getInstance()
        calendar.set(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE), hourOfDay, minute, 0
        )
        var date: Date = calendar.time
        val simpleDateFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        simpleDateFormat.timeZone.hasSameRules(TimeZone.getTimeZone("GMT"))
        val dates = simpleDateFormat.format(date)
        val alertTime = simpleDateFormat.parse(dates)?.time
        Log.d("##TimeDAte", "--@$dates")
        Log.d("##Time", "--@$alertTime")
        val intent = Intent(this, LocalReceiver::class.java)
        val pendingIntent =
            PendingIntent.getBroadcast(
                baseContext, 1, intent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        val alramManager: AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        if (alertTime != null) {
            alramManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, alertTime, pendingIntent)
        }
    }

    class LocalReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Log.d("##TimeRece", "--@onReceive")
            setNotification(context)
        }

        private fun setNotification(context: Context?) {
            val notificationManager: NotificationManager =
                context?.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            val builder: NotificationCompat.Builder =
                NotificationCompat.Builder(context, "Channal_ID")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val notificationChannel1 = NotificationChannel(
                    "Channal_ID",
                    "Channal_name",
                    NotificationManager.IMPORTANCE_HIGH
                )
                notificationManager.createNotificationChannel(notificationChannel1)
                builder.setContentTitle("haii")
                builder.setSmallIcon(R.drawable.ic_launcher_background)
            } else {
                builder.setContentTitle("haii")
                builder.setSmallIcon(R.drawable.ic_launcher_background)
            }
            notificationManager.notify(123, builder.build())
        }

    }

}