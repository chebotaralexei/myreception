package com.electricsheeps.myreception.presentation.base;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface LoadingView {
    @StateStrategyType(AddToEndSingleStrategy.class)
    void showLoading(boolean loading);
}
