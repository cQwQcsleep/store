package com.android.tools.r8.threading.providers.blocking;

import com.android.tools.r8.internal.C1172bh0;
import com.android.tools.r8.threading.ThreadingModule;
import com.android.tools.r8.threading.ThreadingModuleProvider;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class ThreadingModuleBlockingProvider implements ThreadingModuleProvider {
    @Override // com.android.tools.r8.threading.ThreadingModuleProvider
    public final ThreadingModule create() {
        return new C1172bh0();
    }
}
