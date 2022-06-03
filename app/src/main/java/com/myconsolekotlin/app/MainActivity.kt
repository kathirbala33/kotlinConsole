package com.myconsolekotlin.app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.myconsolekotlin.app.FragmentAdapter.SampleFragment
import com.myconsolekotlin.app.alert.AlertActivity
import com.myconsolekotlin.app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        actions();
//        commitFragment()
    }

    private fun actions() {
        binding?.fragmentAdapter?.setOnClickListener { commitFragment() }
        binding?.alertNotification?.setOnClickListener {
            val intent = Intent(this, AlertActivity::class.java)
            startActivity(intent)
        }
        binding?.basic?.setOnClickListener {
            val intent = Intent(this, BasicActivity::class.java)
            startActivity(intent)
        }
    }

    private fun commitFragment() {
        val fragment: Fragment? = SampleFragment()
        val manager = supportFragmentManager.beginTransaction()
        if (fragment != null) {
            manager.replace(
                R.id.mainFragment, fragment
            )
            manager.commit()
        }
    }
}