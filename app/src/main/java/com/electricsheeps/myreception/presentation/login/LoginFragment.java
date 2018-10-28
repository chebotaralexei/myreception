package com.electricsheeps.myreception.presentation.login;


import android.os.Bundle;
import android.view.View;

import com.electricsheeps.myreception.R;
import com.electricsheeps.myreception.presentation.base.MvpBaseFragment;
import com.electricsheeps.myreception.presentation.chat.ChatFragment;

import butterknife.BindView;

public class LoginFragment extends MvpBaseFragment {

    @BindView(R.id.button)
    View button;

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        Bundle arguments = new Bundle();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    protected int setLayoutRes() {
        return R.layout.fragment_login;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button.setOnClickListener(v -> getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, ChatFragment.newInstance())
                .addToBackStack(null)
                .commit());

    }
}
