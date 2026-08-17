package com.android.tools.r8.internal;

import java.nio.file.Path;
import java.nio.file.Paths;

/* JADX INFO: renamed from: com.android.tools.r8.internal.wm, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2972wm {
    public static AbstractC2972wm a() {
        String property = System.getProperty("com.android.tools.r8.dumpinputtofile");
        if (property != null) {
            return b(Paths.get(property, new String[0]));
        }
        String property2 = System.getProperty("com.android.tools.r8.dumpinputtodirectory");
        return property2 != null ? a(Paths.get(property2, new String[0])) : new C2629sm();
    }

    public static AbstractC2972wm b(Path path) {
        return new C2715tm(path);
    }

    public abstract boolean a(C3057xm c3057xm);

    public abstract Path b();

    public abstract boolean c();

    public abstract boolean d();

    public static AbstractC2972wm a(Path path) {
        return new C2800um(path);
    }
}
