package com.example.chapter3.homework;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class PlaceholderFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件
        //super.onCreateView(inflater,container,savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_placeholder, container, false);

        String[] item = {"1", "2", "3", "4", "5"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, item);
        ListView listView=(ListView)rootView.findViewById(R.id.listView);
        listView.setVisibility(ListView.INVISIBLE);
        listView.setAdapter(adapter);
        return rootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 这里会在 5s 后执行

                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入
                final LottieAnimationView lottieAnimationView=getView().findViewById(R.id.animation_view2);
                System.out.println(lottieAnimationView);
                lottieAnimationView.animate()
                        .alpha(0f)
                        .setDuration(1000)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animator) {
                                System.out.println("lottie is invisible");
                                lottieAnimationView.setVisibility(View.INVISIBLE);
                            }
                        });

                final ListView listView = getView().findViewById(R.id.listView);
                listView.setAlpha(0);
                listView.setVisibility(View.VISIBLE);
                listView.animate()
                        .alpha(1f)
                        .setDuration(1000)
                        .setListener(null);
            }
        }, 5000);
    }
}
