package com.android.tools.r8.startup;

import com.android.tools.r8.Resource;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface StartupProfileProvider extends Resource {
    void getStartupProfile(StartupProfileBuilder startupProfileBuilder);
}
