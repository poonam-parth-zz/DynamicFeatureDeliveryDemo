package com.example.dynamicfeaturedeliverydemo

import com.example.dynamicfeaturedeliverydemo.di.DaggerMainComponent
import com.example.dynamicfeaturedeliverydemo.di.MainComponent
import com.google.android.play.core.splitcompat.SplitCompatApplication
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import timber.log.Timber
import javax.inject.Inject

class MainApplication : SplitCompatApplication(),CoreComponentProvider, HasAndroidInjector {


    lateinit var appComponent : MainComponent

    override fun onCreate() {
        super.onCreate()

        initTimber()
        setupDependencyInjection()

    }

    private fun initTimber(){
        if(BuildConfig.DEBUG){
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun setupDependencyInjection() {
        appComponent = DaggerMainComponent
            .builder()
            .app(this)
            .build()
        appComponent.inject(this)
    }

    override fun provideAppComponent(): MainComponent {
      return appComponent
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector
}