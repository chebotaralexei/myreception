package com.electricsheeps.myreception.dagger;

import com.electricsheeps.myreception.dagger.module.ApplicationModule;
import com.electricsheeps.myreception.dagger.module.NetworkModule;
import com.electricsheeps.myreception.presentation.chat.ChatFragment;
import com.electricsheeps.myreception.presentation.chat.ChatPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        NetworkModule.class,
        ApplicationModule.class
})
public interface AppComponent {

    void inject(ChatFragment chatFragment);

    void inject(ChatPresenter chatPresenter);
}
