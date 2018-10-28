package com.electricsheeps.myreception.presentation.chat;

import com.arellomobile.mvp.InjectViewState;
import com.electricsheeps.myreception.App;
import com.electricsheeps.myreception.data.Answer;
import com.electricsheeps.myreception.data.Question;
import com.electricsheeps.myreception.data.SendState;
import com.electricsheeps.myreception.data.ServerAPI;
import com.electricsheeps.myreception.presentation.base.MvpBasePresenter;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


@InjectViewState
public class ChatPresenter extends MvpBasePresenter<ChatView> {
    @Inject
    ServerAPI serverAPI;
    private List<Question> list;

    public ChatPresenter() {
        App.INSTANCE.getAppComponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        addSub(serverAPI.getMessages("alex")
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(v -> getViewState().showLoading(true))
                .doOnEvent((messages, throwable) -> getViewState().showLoading(false))
                .toObservable()
                .observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        question -> {
                            list = question.questions;
                            getViewState().setAdapterDataset(list.get(0));
                        },
                        t -> getViewState().showError(t)));

        addSub(serverAPI.getState("alex")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(question -> getViewState().setState(question),
                        t -> getViewState().showError(t)));
    }


    public void onAnswerClick(Answer answer) {
        addSub(serverAPI.sendState(answer.qId, new SendState("alex", answer.count))
                .observeOn(AndroidSchedulers.mainThread())
                .map(q -> {
                    getViewState().setState(q);
                    getViewState().notifyChanges(answer);
                    return q;
                })
                .delay(2000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        question -> {
                            int changedIndex = 0;
                            for (int i = 0; i < list.size(); i++) {
                                if (list.get(i).id == answer.qId) {
                                    changedIndex = i;
                                    break;
                                }
                            }
                            if (changedIndex + 1 < list.size()) {
                                getViewState().setAdapterDataset(list.get(changedIndex + 1));
                            }
                        },
                        t -> getViewState().showError(t)));
    }
}

