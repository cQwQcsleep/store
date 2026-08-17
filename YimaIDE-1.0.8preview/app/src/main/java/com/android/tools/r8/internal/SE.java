package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C2159nH;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class SE {
    public static final /* synthetic */ boolean b = true;
    public final List a;

    public SE(List list) {
        boolean z = b;
        if (!z && list == null) {
            x1f.a();
            throw null;
        }
        if (z || !list.isEmpty()) {
            this.a = list;
        } else {
            x1f.a();
            throw null;
        }
    }

    public final void a(Consumer consumer) {
        this.a.forEach(consumer);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || SE.class != obj.getClass()) {
            return false;
        }
        return this.a.equals(((SE) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return (String) this.a.stream().map(new Function() { // from class: hnc
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C2159nH) obj).toString();
            }
        }).collect(Collectors.joining(", "));
    }
}
