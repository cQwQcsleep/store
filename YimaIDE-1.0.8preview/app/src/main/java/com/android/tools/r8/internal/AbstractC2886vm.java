package com.android.tools.r8.internal;

import java.util.Map;
import java.util.regex.Pattern;

/* JADX INFO: renamed from: com.android.tools.r8.internal.vm, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2886vm extends AbstractC2972wm {
    @Override // com.android.tools.r8.internal.AbstractC2972wm
    public final boolean a(C3057xm c3057xm) {
        for (Map.Entry entry : c3057xm.d().entrySet()) {
            String property = System.getProperty("com.android.tools.r8.dump.filter.buildproperty." + ((String) entry.getKey()));
            if (property != null && !Pattern.matches(property, (CharSequence) entry.getValue())) {
                return false;
            }
        }
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC2972wm
    public final boolean d() {
        return true;
    }
}
