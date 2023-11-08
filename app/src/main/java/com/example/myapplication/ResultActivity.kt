package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ResultBinding

class ResultActivity : AppCompatActivity() {
    var binding: ResultBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.result)
        Glide.with(this).load(App.instance?.URL).into(binding?.image!!)
    }
}