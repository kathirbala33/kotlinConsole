package com.myconsolekotlin.app

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.myconsolekotlin.app.databinding.ActivityBasicBinding

class BasicActivity : AppCompatActivity() {
    var binding: ActivityBasicBinding? = null
    lateinit var binding1: ActivityBasicBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBasicBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        displayToast()
        varvalConst()
        var sample = Constants.nameVar
        val sampleq = Constants.nameVal
        val dd = Constants.getValues()
        binding?.sample?.text = sample.plus(sampleq).plus(dd)
    }

    private fun varvalConst() {
        //var datatype is muttable(which means changeable one)
        var sampleVar = "name"
        sampleVar = "name1"

        sampleVar = getReturnValues() as String


        //val datatype is imuttable(which means not changeable one)
        val sampleVal = "valname"
//        sampleVal ="valNames"

        val sampl = getReturnValues()

        //const values used inside the companion object{}
        //its used to set the static values like Appconstnt

        //lateinit it used to avoid the initize the datatype varible
    }

    private fun getReturnValues(): Any? {

        return "dada";
    }

    private fun displayToast() {
//        toast
        Toast.makeText(this, "haii", Toast.LENGTH_LONG).show()
        val toast = Toast.makeText(baseContext, "top", Toast.LENGTH_LONG)
        toast.setGravity(Gravity.TOP, 0, 0)
        toast.show()
        val toast1 = Toast.makeText(baseContext, "right", Toast.LENGTH_LONG)
        toast1.setGravity(Gravity.END, 0, 0)
        toast1.show()

    }
}