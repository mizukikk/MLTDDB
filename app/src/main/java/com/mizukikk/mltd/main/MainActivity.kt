package com.mizukikk.mltd.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.mizukikk.mltd.R
import com.mizukikk.mltd.databinding.ActivityMainBinding
import com.mizukikk.mltd.main.idol.IdolListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val TAG = MainActivity::class.java.simpleName
    private val idolListFragment by lazy { IdolListFragment.newInstance() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setHomeFragment()
    }


    private val currentFragment =
        supportFragmentManager.findFragmentById(R.id.container)


    fun Fragment.begineTransaction() {
        if (this.isAdded.not())
            supportFragmentManager.beginTransaction()
                .add(R.id.container, this)
                .commit()
    }

    fun Fragment.begineTransarcionStack() {
        if (this.isAdded.not())
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, this)
                .addToBackStack(this.javaClass.simpleName)
                .commit()
    }

    fun setHomeFragment() {
        idolListFragment.begineTransaction()
    }
}
