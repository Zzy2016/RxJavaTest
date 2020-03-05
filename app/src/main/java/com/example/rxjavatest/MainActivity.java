package com.example.rxjavatest;

import androidx.appcompat.app.AppCompatActivity;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

/*
 * Observable 被观察者
 * Observer   观察者  Subscriber
 * subscribe  订阅
 *
 *
 * Schedulers.immediate()当前线程
 * Schedulers.newThread()新线程
 * Schedulers.io()       IO操作 行为模式和 newThread() 差不多，区别在于 io() 的内部实现是是用一个无数量上限的线程池，可以重用空闲的线程，因此多数情况下 io() 比 newThread() 更有效率。
 * Schedulers.computation()    计算所使用的 Scheduler。这个计算指的是 CPU 密集型计算，即不会被 I/O 等操作限制性能的操作，例如图形的计算。这个 Scheduler 使用的固定的线程池，大小为 CPU 核数。不要把 I/O 操作放在 computation() 中，否则 I/O 操作的等待时间会浪费 CPU。
AndroidSchedulers.mainThread()  主线程
 * */
public class MainActivity extends AppCompatActivity {

    String[] imageArray = new String[]{"image1", "image2", "image3", "image4", "image5", "image6"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        设置观察者和发布者代码所要运行的线程后注册观察者
        observable.subscribeOn(Schedulers.immediate()).     //设置观察者代码运行的线程
                observeOn(AndroidSchedulers.mainThread()).  //设置发布者代码运行的线程（被观察者）
                subscribe(subscriber);  //注册观察者


//        将传入的数组或者Iterable拆分成具体对象 依次发送出来
        Observable.from(imageArray).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.e("打印数组", s);
            }
        });
//     将传入的参数以此发送出来
        Observable.just("param1", "param2", "param3").subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.e("just----", s);
            }
        });
//     创造事件序列
        Observable.create(new Observable.OnSubscribe<Drawable>() {
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {

            }
        }).subscribe(new Observer<Drawable>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Drawable drawable) {

            }
        });


//        Observable.just("images/logo.png") // 输入类型 String
//                .map(new Func1<String, Bitmap>() {
//                    @Override
//                    public Bitmap call(String filePath) { // 参数类型 String
//                        return getBitmapFromPath(filePath); // 返回类型 Bitmap
//                    }
//                }).subscribe(new Action1<Bitmap>() {
//            @Override
//            public void call(Bitmap bitmap) { // 参数类型 Bitmap
//                showBitmap(bitmap);
//            }
//        });

    }


    //创建一个被观察者(发布者)
    Observable observable = Observable.create(new Observable.OnSubscribe<Integer>() {
        @Override
        public void call(Subscriber<? super Integer> subscriber) {
            subscriber.onNext(1001);
            subscriber.onNext(1002);
            subscriber.onNext(1003);
            subscriber.onCompleted();
        }
    });


    //    观察者
    Subscriber<Integer> subscriber = new Subscriber<Integer>() {
        @Override
        public void onCompleted() {
            Log.e("-------->", "onCompleted");
        }

        @Override
        public void onError(Throwable e) {
            Log.e("-------->", "onError " + e.toString());
        }

        @Override
        public void onNext(Integer integer) {
            Log.e("-------->", "onNext " + integer);
        }
    };


}
