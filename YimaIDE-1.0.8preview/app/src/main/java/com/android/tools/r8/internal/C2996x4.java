package com.android.tools.r8.internal;

import com.android.tools.r8.AssertionsConfiguration;
import com.android.tools.r8.internal.C2996x4;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.x4, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2996x4 {
    public static final /* synthetic */ boolean d = true;
    public final AssertionsConfiguration a;
    public final List b;
    public final AbstractCollection c;

    public C2996x4(List list, AssertionsConfiguration assertionsConfiguration) {
        this.a = assertionsConfiguration;
        if (!d && list == null) {
            x1f.a();
            throw null;
        }
        this.b = list;
        this.c = (AbstractCollection) a();
    }

    public final List a() {
        if (!d && this.a.isAssertionHandler()) {
            x1f.a();
            return null;
        }
        if (this.b.isEmpty()) {
            int i = AbstractC0551Hu.c;
            return P40.e;
        }
        final ArrayList arrayList = new ArrayList();
        this.b.forEach(new Consumer() { // from class: oqi
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                C2996x4.a(arrayList, (AssertionsConfiguration) obj);
            }
        });
        return arrayList;
    }

    public static /* synthetic */ void a(List list, AssertionsConfiguration assertionsConfiguration) {
        if (!assertionsConfiguration.isAssertionHandler() || list.contains(assertionsConfiguration.getAssertionHandler())) {
            return;
        }
        list.add(assertionsConfiguration.getAssertionHandler());
    }
}
