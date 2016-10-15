/*
 * Copyright (c) 2016 咖枯 <kaku201313@163.com | 3772304@qq.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.example.admin.news.di.component;

import android.app.Activity;
import android.content.Context;

import com.example.admin.news.di.module.ActivityModule;
import com.example.admin.news.di.scope.ContextLife;
import com.example.admin.news.di.scope.PerActivity;
import com.example.admin.news.mvp.ui.activities.NewsActivity;
import com.example.admin.news.mvp.ui.activities.NewsChannelActivity;
import com.example.admin.news.mvp.ui.activities.NewsDetailActivity;
import com.example.admin.news.mvp.ui.activities.NewsPhotoDetailActivity;
import com.example.admin.news.mvp.ui.activities.PhotoActivity;
import com.example.admin.news.mvp.ui.activities.PhotoDetailActivity;

import dagger.Component;

/**
 * @author HPL
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    @ContextLife("Activity")
    Context getActivityContext();

    @ContextLife("Application")
    Context getApplicationContext();

    Activity getActivity();

    void inject(NewsActivity newsActivity);

    void inject(NewsDetailActivity newsDetailActivity);

    void inject(NewsChannelActivity newsChannelActivity);

    void inject(NewsPhotoDetailActivity newsPhotoDetailActivity);

    void inject(PhotoActivity photoActivity);

    void inject(PhotoDetailActivity photoDetailActivity);
}
