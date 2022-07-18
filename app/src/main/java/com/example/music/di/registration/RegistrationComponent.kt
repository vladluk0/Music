package com.example.music.di.registration

import android.content.Context
import com.example.music.di.util.MultiViewModelFactory
import com.example.music.ui.authentification.AuthenticationActivity
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [RegistrationModule::class])
interface RegistrationComponent {

    fun inject(activity: AuthenticationActivity)

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun bindContext(context: Context): Builder
        fun build(): RegistrationComponent
    }

    val factory: MultiViewModelFactory
}