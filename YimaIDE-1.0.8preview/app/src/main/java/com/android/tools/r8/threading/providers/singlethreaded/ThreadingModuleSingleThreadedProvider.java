package com.android.tools.r8.threading.providers.singlethreaded;

import com.android.tools.r8.internal.C1255ch0;
import com.android.tools.r8.threading.ThreadingModule;
import com.android.tools.r8.threading.ThreadingModuleProvider;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class ThreadingModuleSingleThreadedProvider implements ThreadingModuleProvider {
    @Override // com.android.tools.r8.threading.ThreadingModuleProvider
    public final ThreadingModule create() {
        return new C1255ch0();
    }
}
