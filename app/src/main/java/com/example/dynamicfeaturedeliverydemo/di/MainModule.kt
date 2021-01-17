package com.example.dynamicfeaturedeliverydemo.di

import android.app.Application
import android.content.Context
import com.example.dynamicfeaturedeliverydemo.MainActivity
import com.example.normalmodule.NormalActivity
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.testing.FakeSplitInstallManager
import com.google.android.play.core.splitinstall.testing.FakeSplitInstallManagerFactory
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module
abstract class MainModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun normalActivity(): NormalActivity

    @Module
    companion object {
        @JvmStatic
        @Provides
        fun applicationContext(app: Application): Context = app


//        @Singleton
//        @JvmStatic
//        @Provides
//        fun splitInstallManager(context: Context): SplitInstallManager =
//            SplitInstallManagerFactory.create(context)

        // Only for local testing purpose
        @JvmStatic
        @Provides
        fun splitInstallManager(context: Context): FakeSplitInstallManager =
            FakeSplitInstallManagerFactory.create(
                context,
                context.getExternalFilesDir("local_testing")
            )


    }
}