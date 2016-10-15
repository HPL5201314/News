/*
 * Copyright (c) 2016 咖枯 <kaku201313@163.com>
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
package com.example.admin.news.mvp.presenter.impl;

import com.example.admin.news.common.Constants;
import com.example.admin.news.event.ChannelChangeEvent;
import com.example.admin.news.greendao.NewsChannelTable;
import com.example.admin.news.mvp.interactor.impl.NewsChannelInteractorImpl;
import com.example.admin.news.mvp.presenter.NewsChannelPresenter;
import com.example.admin.news.mvp.presenter.base.BasePresenterImpl;
import com.example.admin.news.mvp.view.NewsChannelView;
import com.example.admin.news.utils.RxBus;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * @author HPL
 */
public class NewsChannelPresenterImpl extends BasePresenterImpl<NewsChannelView,
        Map<Integer, List<NewsChannelTable>>> implements NewsChannelPresenter {

    private NewsChannelInteractorImpl mNewsChannelInteractor;
    private boolean mIsChannelChanged;

    @Inject
    public NewsChannelPresenterImpl(NewsChannelInteractorImpl newsChannelInteractor) {
        mNewsChannelInteractor = newsChannelInteractor;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mIsChannelChanged) {
            RxBus.getInstance().post(new ChannelChangeEvent());
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mNewsChannelInteractor.lodeNewsChannels(this);
    }

    @Override
    public void success(Map<Integer, List<NewsChannelTable>> data) {
        super.success(data);
        mView.initRecyclerViews(data.get(Constants.NEWS_CHANNEL_MINE), data.get(Constants.NEWS_CHANNEL_MORE));
    }

    @Override
    public void onItemSwap(int fromPosition, int toPosition) {
        mNewsChannelInteractor.swapDb(fromPosition, toPosition);
        mIsChannelChanged = true;
    }

    @Override
    public void onItemAddOrRemove(NewsChannelTable newsChannel, boolean isChannelMine) {
        mNewsChannelInteractor.updateDb(newsChannel, isChannelMine);
        mIsChannelChanged = true;
    }
}
