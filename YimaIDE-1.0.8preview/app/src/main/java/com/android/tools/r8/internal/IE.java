package com.android.tools.r8.internal;

import com.android.tools.r8.internal.FE;
import com.android.tools.r8.internal.HE;
import com.android.tools.r8.internal.IE;
import java.util.Collections;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class IE {
    public static final IE b = new IE(Collections.EMPTY_MAP);
    public static final /* synthetic */ boolean c = true;
    public final Map a;

    public IE(Map map) {
        if (c || map != null) {
            this.a = map;
        } else {
            x1f.a();
            throw null;
        }
    }

    public static /* synthetic */ String a(Map.Entry entry) {
        return entry.getKey() + "=" + entry.getValue();
    }

    public final String toString() {
        return "{" + ((String) this.a.entrySet().stream().map(new Function() { // from class: if6
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return IE.a((Map.Entry) obj);
            }
        }).collect(Collectors.joining(", ")));
    }

    public final void a(final BiConsumer biConsumer) {
        this.a.forEach(new BiConsumer() { // from class: jf6
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                IE.a(biConsumer, (HE) obj, (FE) obj2);
            }
        });
    }

    public static void a(BiConsumer biConsumer, HE he, FE fe) {
        biConsumer.accept(he, fe.a);
    }
}
