package com.example.dynamicfeature.di

import com.example.dynamicfeature.DynamicFeatureRepo
import com.example.dynamicfeature.DynamicFeatureRepoImpl
import dagger.Binds
import dagger.Module


@Module
abstract class DynamicFeatureModule {

    @Binds
    abstract fun provideRepo(repo:DynamicFeatureRepoImpl) : DynamicFeatureRepo
}