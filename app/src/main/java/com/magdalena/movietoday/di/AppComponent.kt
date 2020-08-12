package com.magdalena.movietoday.di

import dagger.Component

import javax.inject.Singleton

@Singleton
@Component(modules = [ RoomModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        fun build(): AppComponent
    }

}