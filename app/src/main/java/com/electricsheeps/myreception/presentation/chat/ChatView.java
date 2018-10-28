package com.electricsheeps.myreception.presentation.chat;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.electricsheeps.myreception.data.Answer;
import com.electricsheeps.myreception.data.Question;
import com.electricsheeps.myreception.data.State;
import com.electricsheeps.myreception.presentation.base.MvpBaseView;


@StateStrategyType(AddToEndStrategy.class)
public interface ChatView extends MvpView, MvpBaseView {

    void setAdapterDataset(Question mMessages);

    void showError(Throwable t);

    void setState(State question);

    void notifyChanges(Answer answer);
}
