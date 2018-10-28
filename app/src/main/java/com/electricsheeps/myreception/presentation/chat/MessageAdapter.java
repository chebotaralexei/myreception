package com.electricsheeps.myreception.presentation.chat;

import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.electricsheeps.myreception.R;
import com.electricsheeps.myreception.data.Answer;
import com.electricsheeps.myreception.data.Question;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Question> mMessages = new ArrayList<>();

    public Observable<Answer> click() {
        return answerSubject;
    }
    public Observable<Boolean> keyBoardHide() {
        return subject;
    }

    PublishSubject<Answer> answerSubject = PublishSubject.create();
    PublishSubject<Boolean> subject = PublishSubject.create();


    public MessageAdapter() {
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case Question.TYPE_TEXT:
                return new TextViewHolder(LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.item_text_question, parent, false));
            case Question.TYPE_SMILE:
                return new SmileViewHolder(LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.item_smile_question, parent, false));
        }
        return new VariantViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_variant_question, parent, false));

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof SmileViewHolder)
            ((SmileViewHolder) viewHolder).bind(mMessages.get(position));
        else if (viewHolder instanceof TextViewHolder) {
            ((TextViewHolder) viewHolder).bind(mMessages.get(position));
        } else if (viewHolder instanceof VariantViewHolder) {
            ((VariantViewHolder) viewHolder).bind(mMessages.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mMessages.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mMessages.get(position).type;
    }

    public void addItem(Question question) {
        mMessages.add(question);
        notifyItemInserted(mMessages.indexOf(question));
    }

    public void notifyChanges(Answer answer) {
        int changedIndex = 0;
        for (int i = 0; i < mMessages.size(); i++) {
            if (mMessages.get(i).id == answer.qId) {
                changedIndex = i;
                break;
            }
        }
        mMessages.get(changedIndex).myAnswer = answer;
        notifyItemChanged(changedIndex);
    }

    public class SmileViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.message)
        TextView message;
        @BindView(R.id.answer_text)
        TextView answerText;
        @BindView(R.id.answer_list)
        View answerList;
        @BindView(R.id.answer)
        View answer;
        @BindView(R.id.happy)
        ImageView happy;
        @BindView(R.id.pokerface)
        ImageView pokerface;
        @BindView(R.id.sad)
        ImageView sad;

        public SmileViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Question question) {
            message.setText(question.text);

            if (question.myAnswer == null) {
                answerList.setVisibility(View.VISIBLE);
                answer.setVisibility(View.GONE);
                happy.setOnClickListener(v -> {
                    Answer myAnswer = question.answers.get(0);
                    myAnswer.qId = question.id;
                    answerSubject.onNext(myAnswer);
                });
                pokerface.setOnClickListener(v -> {
                    Answer myAnswer = question.answers.get(1);
                    myAnswer.qId = question.id;
                    answerSubject.onNext(myAnswer);
                });
                sad.setOnClickListener(v -> {
                    Answer myAnswer = question.answers.get(2);
                    myAnswer.qId = question.id;
                    answerSubject.onNext(myAnswer);
                });
            } else {
                answerList.setVisibility(View.GONE);
                answer.setVisibility(View.VISIBLE);
                answerText.setText(question.myAnswer.text);
            }
        }
    }

    public class VariantViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.message)
        TextView message;
        @BindView(R.id.answer_text)
        TextView answerText;
        @BindView(R.id.no)
        TextView no;
        @BindView(R.id.answer_list)
        View answerList;
        @BindView(R.id.yes)
        TextView yes;
        @BindView(R.id.answer)
        View answer;

        public VariantViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Question question) {
            message.setText(question.text);
            if (question.myAnswer == null) {
                answerList.setVisibility(View.VISIBLE);
                answer.setVisibility(View.GONE);
                yes.setText(question.answers.get(0).text);
                no.setText(question.answers.get(1).text);
                yes.setOnClickListener(v -> {
                    Answer myAnswer = question.answers.get(0);
                    myAnswer.qId = question.id;
                    answerSubject.onNext(myAnswer);
                });
                no.setOnClickListener(v -> {
                    Answer myAnswer = question.answers.get(1);
                    myAnswer.qId = question.id;
                    answerSubject.onNext(myAnswer);
                });
            } else {
                answerList.setVisibility(View.GONE);
                answer.setVisibility(View.VISIBLE);
                answerText.setText(question.myAnswer.text);
            }
        }
    }

    public class TextViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.message)
        TextView message;
        @BindView(R.id.answer_text)
        TextView answerText;

        @BindView(R.id.unit)
        TextView unit;
        @BindView(R.id.edittext)
        EditText edittext;
        @BindView(R.id.answer_list)
        View answerList;
        @BindView(R.id.answer)
        View answer;

        public TextViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Question question) {
            message.setText(question.text);

            if (question.myAnswer == null) {
                answer.setVisibility(View.GONE);
                answerList.setVisibility(View.VISIBLE);
                edittext.setOnKeyListener((v, keyCode, event) -> {
                    if ((keyCode == KeyEvent.KEYCODE_DPAD_CENTER ||
                            keyCode == KeyEvent.KEYCODE_SEARCH ||
                            keyCode == KeyEvent.KEYCODE_ENTER) &&
                            event.getAction() == KeyEvent.ACTION_DOWN) {
                        edittext.setFocusable(false);
                        Answer myAnswer = new Answer(edittext.getText().toString().trim()
                                + " " + unit.getText().toString().trim(),
                                Float.parseFloat(edittext.getText().toString().trim()));
                        myAnswer.qId = question.id;
                        answerSubject.onNext(myAnswer);
                        subject.onNext(true);
                        return true;
                    }
                    return false;
                });
            } else {
                answerList.setVisibility(View.GONE);
                answer.setVisibility(View.VISIBLE);
                answerText.setText(question.myAnswer.text);
            }
        }
    }
}

