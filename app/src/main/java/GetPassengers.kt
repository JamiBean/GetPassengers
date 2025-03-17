package example.getpassengers

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

data class Passenger(val firstName: String, val lastName: String, val phoneNumber: String) {
    override fun toString(): String {
        return "$firstName $lastName - $phoneNumber"
    }
}

class GetPassengers : AppCompatActivity() {

    private lateinit var textFirst: EditText
    private lateinit var textLast: EditText
    private lateinit var textPhone: EditText
    private lateinit var textPut: TextView

    private val passList: MutableList<Passenger> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.getpassengers)

        textFirst = findViewById(R.id.first_name)
        textLast = findViewById(R.id.last_name)
        textPhone = findViewById(R.id.phone_number)
        textPut = findViewById(R.id.accum_list)

        val addButton: Button = findViewById(R.id.add_button)
        val backToMainButton: Button = findViewById(R.id.back_to_main)

        addButton.setOnClickListener { enterPassenger() }
        backToMainButton.setOnClickListener { backToMain() }
    }

    private fun enterPassenger() {
        val fName = textFirst.text.toString().trim()
        val lName = textLast.text.toString().trim()
        val phone = textPhone.text.toString().trim()

        if (fName.isNotEmpty() && lName.isNotEmpty() && phone.isNotEmpty()) {
            val newPass = Passenger(fName, lName, phone)
            passList.add(newPass)

            textPut.append(newPass.toString() + "\n")

            textFirst.text.clear()
            textLast.text.clear()
            textPhone.text.clear()
        }
    }

    private fun backToMain() {
        val intent = Intent()
        intent.putExtra("COUNT", passList.size.toString())

        for (i in passList.indices) {
            intent.putExtra("PASS${i + 1}", passList[i].toString())
        }

        setResult(RESULT_OK, intent)
        finish()
    }
}