package com.android.tools.r8.retrace;

import com.android.tools.r8.retrace.RegisterMappingPartitionCallback;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface RegisterMappingPartitionCallback {
    public static final RegisterMappingPartitionCallback EMPTY_INSTANCE = new RegisterMappingPartitionCallback() { // from class: mac
        @Override // com.android.tools.r8.retrace.RegisterMappingPartitionCallback
        public final void register(String str) {
            RegisterMappingPartitionCallback.a(str);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    static /* synthetic */ void a(String str) {
    }

    static RegisterMappingPartitionCallback empty() {
        return EMPTY_INSTANCE;
    }

    void register(String str);
}
