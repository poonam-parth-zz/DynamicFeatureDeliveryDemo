package com.example.normalmodule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dynamicfeaturedeliverydemo.extensions.coreComponent
import dagger.android.AndroidInjection

class NormalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_normal)
    }

}