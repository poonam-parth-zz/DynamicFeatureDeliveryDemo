package com.example.dynamicfeature.di


import com.example.dynamicfeature.DynamicFeatureActivity
import com.example.dynamicfeaturedeliverydemo.di.MainComponent
import dagger.Component

@DynamicFeatureScope
@Component(
    modules =  [DynamicFeatureModule::class],
    dependencies = [MainComponent::class]
)
interface DynamicFeatureComponent {

    fun inject(activity: DynamicFeatureActivity)

    @Component.Factory
    interface Factory {
        fun create(appComponent: MainComponent): DynamicFeatureComponent
    }

}