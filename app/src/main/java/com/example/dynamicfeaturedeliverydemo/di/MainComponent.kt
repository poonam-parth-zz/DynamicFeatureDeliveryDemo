package com.example.dynamicfeaturedeliverydemo.di

import android.app.Application
import com.example.dynamicfeaturedeliverydemo.MainApplication
import com.google.android.play.core.splitinstall.SplitInstallManager
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [MainModule::class,  AndroidInjectionModule::class,
    AndroidSupportInjectionModule::class])
interface MainComponent {

    fun splitInstallManager() : SplitInstallManager

    fun inject(app: MainApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun app(app: Application): Builder
        fun build(): MainComponent
    }


}