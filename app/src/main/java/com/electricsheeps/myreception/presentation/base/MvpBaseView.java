package com.electricsheeps.myreception.presentation.base;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by alex on 30.08.17.
 */

@StateStrategyType(AddToEndSingleStrategy.class)
public interface MvpBaseView extends LoadingView {
}
