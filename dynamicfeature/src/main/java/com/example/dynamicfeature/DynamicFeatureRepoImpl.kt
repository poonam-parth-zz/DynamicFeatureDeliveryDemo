package com.example.dynamicfeature

import javax.inject.Inject

class DynamicFeatureRepoImpl @Inject constructor(): DynamicFeatureRepo{
    override fun provideRandomString(): String {
        return "Hey this is provide impl string"
    }

}