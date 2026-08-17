package com.android.tools.r8.profile.art;

import com.android.tools.r8.Resource;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface ArtProfileProvider extends Resource {
    void getArtProfile(ArtProfileBuilder artProfileBuilder);
}
