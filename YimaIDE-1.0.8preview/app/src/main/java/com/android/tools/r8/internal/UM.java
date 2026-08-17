package com.android.tools.r8.internal;

import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class UM extends Wh0 {
    public UM(Iterator it) {
        super(it);
    }

    @Override // com.android.tools.r8.internal.Wh0
    public final Object a(Object obj) {
        return ((Map.Entry) obj).getKey();
    }
}
