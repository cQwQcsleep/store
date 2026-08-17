package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C2119mo;
import com.android.tools.r8.internal.QY;
import java.util.function.ObjIntConsumer;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class QY {
    public final InterfaceC1231cQ a;

    public QY(C2958wf c2958wf) {
        this.a = c2958wf;
    }

    public final void a(final ObjIntConsumer objIntConsumer) {
        this.a.a(new ObjIntConsumer() { // from class: c0c
            @Override // java.util.function.ObjIntConsumer
            public final void accept(Object obj, int i) {
                QY.a(objIntConsumer, (C2119mo) obj, i);
            }
        });
    }

    public final void a(final Predicate predicate) {
        this.a.removeIf(new Predicate() { // from class: b0c
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return QY.a(predicate, (C2119mo) obj);
            }
        });
    }

    public static void a(ObjIntConsumer objIntConsumer, C2119mo c2119mo, int i) {
        objIntConsumer.accept((com.android.tools.r8.graph.B5) c2119mo.c, i);
    }

    public static boolean a(Predicate predicate, C2119mo c2119mo) {
        return predicate.test((com.android.tools.r8.graph.B5) c2119mo.c);
    }
}
