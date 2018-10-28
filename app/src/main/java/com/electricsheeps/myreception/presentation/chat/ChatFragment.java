package com.electricsheeps.myreception.presentation.chat;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.electricsheeps.myreception.App;
import com.electricsheeps.myreception.R;
import com.electricsheeps.myreception.data.Answer;
import com.electricsheeps.myreception.data.Question;
import com.electricsheeps.myreception.data.State;
import com.electricsheeps.myreception.presentation.base.MvpBaseFragment;

import butterknife.BindView;

public class ChatFragment extends MvpBaseFragment implements ChatView {
    @BindView(R.id.messages)
    RecyclerView recyclerViewMessages;

    @BindView(R.id.progressBack)
    View progressBack;
    @BindView(R.id.progressFront)
    View progressFront;
    @BindView(R.id.progressText)
    TextView progressText;
    @BindView(R.id.progressContainer)
    View progressContainer;

    private LinearLayoutManager linearLayoutManager;
    private MessageAdapter messageAdapter;

    @InjectPresenter
    ChatPresenter presenter;

    @ProvidePresenter
    ChatPresenter provideTutorialPresenter() {
        return new ChatPresenter();
    }

    public static ChatFragment newInstance() {
        ChatFragment fragment = new ChatFragment();
        Bundle arguments = new Bundle();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_chat;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        App.INSTANCE.getAppComponent().inject(this);
        messageAdapter = new MessageAdapter();
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setStackFromEnd(true);
        recyclerViewMessages.setLayoutManager(linearLayoutManager);
        recyclerViewMessages.setAdapter(messageAdapter);
        addSub(messageAdapter.click().subscribe(answer -> presenter.onAnswerClick(answer)));
        addSub(messageAdapter.keyBoardHide().subscribe(answer -> hideSoftKeyboard(getActivity())));
    }

    protected void hideSoftKeyboard(Activity activity) {
        if (activity == null) return;
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    @Override
    public void setAdapterDataset(Question mMessages) {
        messageAdapter.addItem(mMessages);
        recyclerViewMessages.scrollToPosition(messageAdapter.getItemCount() - 1);
    }

    @Override
    public void showError(Throwable t) {
        showToast(t.getMessage());
    }

    @Override
    public void setState(State question) {
        progressContainer.setVisibility(View.VISIBLE);
//        showToast("severity = "+question.severity);
        int color;
        if (question.severity < 10) {
            color = R.color.green;
        } else if (question.severity < 30) {
            color = R.color.yellow;
        } else {
            color = R.color.red;
        }
        progressBack.setBackgroundResource(color);
        progressFront.setBackgroundResource(color);
    }

    @Override
    public void notifyChanges(Answer answer) {
        messageAdapter.notifyChanges(answer);
    }
}
