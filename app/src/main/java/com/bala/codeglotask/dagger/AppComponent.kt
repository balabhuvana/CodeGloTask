package com.bala.codeglotask.dagger

import com.bala.codeglotask.ui.fragment.CodeGloFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(dependencies = [], modules = [AppModule::class, NetworkModule::class])
interface AppComponent {
    fun inject(loginFragment: CodeGloFragment?)
}