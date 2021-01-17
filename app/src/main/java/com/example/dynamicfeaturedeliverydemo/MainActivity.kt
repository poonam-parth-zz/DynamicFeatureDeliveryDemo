package com.example.dynamicfeaturedeliverydemo

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.normalmodule.NormalActivity
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.SplitInstallSessionState
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import com.google.android.play.core.splitinstall.testing.FakeSplitInstallManager
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity(), SplitInstallStateUpdatedListener {

//    @Inject
//    lateinit var splitInstallManager: SplitInstallManager

    // for local testing
    @Inject
    lateinit var splitInstallManager: FakeSplitInstallManager

    private var sessionId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvDynamic.setOnClickListener {
            installFeatureModule()
        }

        tvNormal.setOnClickListener {
            startActivity(Intent(this, NormalActivity::class.java))
        }

    }

    private fun installFeatureModule() {

        if (splitInstallManager.installedModules.contains("dynamicfeature")) {
            startActivity(
                Intent(
                    this,
                    Class.forName("com.example.dynamicfeature.DynamicFeatureActivity")
                )
            )
            return
        }

        splitInstallManager.registerListener(this)
        splitInstallManager.startInstall(
            SplitInstallRequest.newBuilder().addModule("dynamicfeature").build()
        )
            .addOnSuccessListener {
                Timber.i("DFM : addOnSuccessListener $it")
                sessionId = it
            }
            .addOnFailureListener { ex -> Timber.i("DFM : addOnFailureListener $ex") }


    }

    override fun onStateUpdate(state: SplitInstallSessionState) {
        if (state.status() == SplitInstallSessionStatus.FAILED
        ) {
            return
        }
        if (state.sessionId() == sessionId) {
            when (state.status()) {
                SplitInstallSessionStatus.DOWNLOADING -> {
                    Toast.makeText(this, "Downloading...", Toast.LENGTH_SHORT).show()
                    Timber.i("DFM :  DOWNLOADING")
                }

                SplitInstallSessionStatus.DOWNLOADED -> {
                    Timber.i("DFM : DOWNLOADED")
                }
                SplitInstallSessionStatus.INSTALLED -> {
                    Timber.i("DFM : Installed")
                    Toast.makeText(this, "Installed", Toast.LENGTH_SHORT).show()

                    startActivity(
                        Intent(
                            this,
                            Class.forName("com.example.dynamicfeature.DynamicFeatureActivity")
                        )
                    )
                }
            }
        }

    }

    override fun onResume() {
        splitInstallManager.registerListener(this)
        super.onResume()
    }

    override fun onPause() {
        splitInstallManager.unregisterListener(this)
        super.onPause()
    }


}