package com.android.tools.r8.retrace;

import com.android.tools.r8.retrace.PrepareMappingPartitionsCallback;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface PrepareMappingPartitionsCallback {
    public static final PrepareMappingPartitionsCallback EMPTY_INSTANCE = new PrepareMappingPartitionsCallback() { // from class: c7b
        @Override // com.android.tools.r8.retrace.PrepareMappingPartitionsCallback
        public final void prepare() {
            PrepareMappingPartitionsCallback.a();
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    static /* synthetic */ void a() {
    }

    static PrepareMappingPartitionsCallback empty() {
        return EMPTY_INSTANCE;
    }

    void prepare();
}
