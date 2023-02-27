package my.edu.tarc.bmi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageViewBMI: ImageView = findViewById(R.id.imageViewBMI)
        val textViewBMI: TextView = findViewById(R.id.textViewBMI)
        val textViewStatus: TextView = findViewById(R.id.textViewStatus)
        val editTextWeight: EditText = findViewById(R.id.editTextWeight)
        val editTextHeight: EditText = findViewById(R.id.editTextHeight)
        val buttonCalculate: Button = findViewById(R.id.buttonCalculate)
        val buttonReset: Button = findViewById(R.id.buttonReset)

        buttonCalculate.setOnClickListener{
            //BMI = weight(kg)/height(m)^2
            if(editTextWeight.text.isBlank()){
                editTextWeight.setError(getString(R.string.value_required))
                return@setOnClickListener
            }

            if(editTextHeight.text.isBlank()){
                editTextWeight.setError(getString(R.string.value_required))
                return@setOnClickListener
            }

            if(editTextHeight.text.toString().toFloat()<100){
                editTextWeight.setError(getString(R.string.invalid_value))
                return@setOnClickListener
            }

            if(editTextWeight.text.toString().toFloat()<30){
                editTextWeight.setError(getString(R.string.invalid_value))
                return@setOnClickListener
            }


            //get the string then convert to float
            var weight = editTextWeight.text.toString().toFloat()
            var height = editTextHeight.text.toString().toFloat()
            var bmi = weight/(height/100).pow(2)

            if(bmi<18.5){
                imageViewBMI.setImageResource(R.drawable.under)
                textViewBMI.text = String.format("%s : %.2f",getString(R.string.bmi),bmi)
                textViewStatus.text=String.format("%s : %s",getString(R.string.status),getString(R.string.under))
            }
            else if(bmi>=18.5 && bmi<=24.9){
                imageViewBMI.setImageResource(R.drawable.normal)
                textViewBMI.text = String.format("%s : %.2f",getString(R.string.bmi),bmi)
                textViewStatus.text=String.format("%s : %s",getString(R.string.status),getString(R.string.normal))
            }
            else{
                imageViewBMI.setImageResource(R.drawable.over)
                textViewBMI.text = String.format("%s : %.2f",getString(R.string.bmi),bmi)
                textViewStatus.text=String.format("%s : %s",getString(R.string.status),getString(R.string.over))
            }
        }

        buttonReset.setOnClickListener{
            editTextWeight.text.clear()
            editTextHeight.text.clear()
            textViewBMI.text=""
            textViewStatus.text=""
            imageViewBMI.setImageResource(R.drawable.empty)
        }


    }
}