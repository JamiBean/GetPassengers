package example.getpassengers

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var listText: TextView

    // Registering for result from GetPassengers activity
    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { activityResult ->
            if (activityResult.resultCode == RESULT_OK) {
                val data = activityResult.data
                if (data != null) {
                    val count = (data.getStringExtra("COUNT") ?: "0").toInt()
                    val passengerList = StringBuilder("RETURNED PASSENGER LIST:\n")

                    for (i in 1..count) {
                        val passengerInfo = data.getStringExtra("PASS$i") ?: ""
                        passengerList.append("$passengerInfo\n")
                    }

                    listText.text = passengerList.toString()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listText = findViewById(R.id.show_list) // Fixed incorrect ID reference
        val getListButton: Button = findViewById(R.id.get_list_button)

        getListButton.setOnClickListener {
            val intent = Intent(this, GetPassengers::class.java)
            startForResult.launch(intent) // Launch GetPassengers activity
        }
    }
}