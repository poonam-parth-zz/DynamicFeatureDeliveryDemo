package com.example.dynamicfeature

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dynamicfeature.di.DaggerDynamicFeatureComponent
import com.example.dynamicfeaturedeliverydemo.extensions.coreComponent
import com.google.android.play.core.splitcompat.SplitCompat
import com.google.android.play.core.splitinstall.testing.FakeSplitInstallManager
import kotlinx.android.synthetic.main.activity_dynamic_feature.*
import javax.inject.Inject

class DynamicFeatureActivity : AppCompatActivity() {

    @Inject
    lateinit var splitInstallManager: FakeSplitInstallManager

    @Inject
    lateinit var dynamicFeatureRepo: DynamicFeatureRepo

    override fun onCreate(savedInstanceState: Bundle?) {
        initDependencyInjection()
        super.onCreate(savedInstanceState)
        SplitCompat.install(this)

        setContentView(R.layout.activity_dynamic_feature)

        tvMain.text = dynamicFeatureRepo.provideRandomString()

        splitInstallManager.installedModules
    }

    private fun initDependencyInjection() =
        DaggerDynamicFeatureComponent.factory().create(coreComponent()).inject(this)

}