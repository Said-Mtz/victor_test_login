package com.example.testmacropay.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testmacropay.R
import com.example.testmacropay.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }
}