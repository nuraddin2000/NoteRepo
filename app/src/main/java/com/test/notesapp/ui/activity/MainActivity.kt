package com.test.notesapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.notesapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun showLoading() {

    }
}