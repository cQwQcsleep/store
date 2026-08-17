package com.android.tools.r8.internal;

import com.android.tools.r8.internal.Ud0;
import com.android.tools.r8.startup.StartupProfileProvider;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class Ud0 {
    public boolean a = Kg0.a("com.android.tools.r8.startup.minimalstartupdex", System.getProperty("com.android.tools.r8.startup.minimalstartupdex"), true);
    public boolean b = Kg0.a("com.android.tools.r8.startup.boundaryoptimizations", System.getProperty("com.android.tools.r8.startup.boundaryoptimizations"), false);
    public boolean c = Kg0.a("com.android.tools.r8.startup.completenesscheck", System.getProperty("com.android.tools.r8.startup.completenesscheck"), false);
    public boolean d = true;
    public final boolean e = Kg0.a("com.android.tools.r8.startup.layout", System.getProperty("com.android.tools.r8.startup.layout"), true);
    public final String f = System.getProperty("com.android.tools.r8.startup.multistartupdexdistribution", null);
    public Collection g = (Collection) Kg0.a(new Function() { // from class: wye
        @Override // java.util.function.Function
        public final Object apply(Object obj) {
            return Ud0.a((String) obj);
        }
    }, new Supplier() { // from class: xye
        @Override // java.util.function.Supplier
        public final Object get() {
            return Collections.EMPTY_LIST;
        }
    });

    public static List a(String str) {
        StartupProfileProvider startupProfileProviderA = C1249ce0.a(Paths.get(str, new String[0]));
        int i = AbstractC0551Hu.c;
        return new Bc0(startupProfileProviderA);
    }

    public Ud0 b() {
        return c(true);
    }

    public Ud0 c(boolean z) {
        this.c = z;
        return this;
    }

    public Ud0 b(boolean z) {
        this.b = z;
        return this;
    }

    public Ud0 a(boolean z) {
        this.a = z;
        return this;
    }

    public final String a() {
        return this.f;
    }
}
