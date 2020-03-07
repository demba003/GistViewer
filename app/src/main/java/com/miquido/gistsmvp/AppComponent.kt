package com.miquido.gistsmvp

import android.content.Context
import com.miquido.gistsmvp.dagger.*
import com.miquido.gistsmvp.screen.gistdetails.DetailsActivity
import com.miquido.gistsmvp.screen.gistlist.ListActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidModule::class,
        DbModule::class,
        NetworkModule::class,
        PresenterModule::class,
        SchedulerModule::class
    ]
)
interface AppComponent {
    fun inject(detailsActivity: DetailsActivity)
    fun inject(listActivity: ListActivity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}
