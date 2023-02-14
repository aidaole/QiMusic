package com.aidaole.aimusic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aidaole.aimusic.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val layout: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.root)
    }
}