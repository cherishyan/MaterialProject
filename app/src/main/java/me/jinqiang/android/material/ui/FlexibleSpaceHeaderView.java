/*
 * Copyright (c) 2016 Zhang Hai <Dreaming.in.Code.ZH@Gmail.com>
 * All Rights Reserved.
 */

package me.jinqiang.android.material.ui;

public interface FlexibleSpaceHeaderView {

    int getScroll();

    int getScrollExtent();

    void scrollTo(int scroll);

    void scrollBy(int delta);
}
