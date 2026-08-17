package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C1414ec0;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.fc0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1500fc0 extends AbstractC1465f8 {
    public C1500fc0(ConcurrentHashMap concurrentHashMap) {
        super(concurrentHashMap);
    }

    public final UY c() {
        UY uyC = UY.c();
        Set setC = AbstractC2780ub0.c();
        Iterator it = a().iterator();
        while (it.hasNext()) {
            C1414ec0 c1414ec0 = (C1414ec0) it.next();
            if (c1414ec0.d.isEmpty()) {
                uyC.add(c1414ec0.a());
                it.remove();
                setC.add(c1414ec0);
            }
        }
        setC.forEach(new Consumer() { // from class: qvg
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((C1414ec0) obj).e();
            }
        });
        return uyC;
    }
}
