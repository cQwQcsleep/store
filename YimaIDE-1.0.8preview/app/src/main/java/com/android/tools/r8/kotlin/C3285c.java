package com.android.tools.r8.kotlin;

import com.android.tools.r8.graph.B1;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.internal.C1234cT;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/* JADX INFO: renamed from: com.android.tools.r8.kotlin.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3285c {
    public final I2 a;
    public final /* synthetic */ C3289g b;

    public C3285c(C3289g c3289g) {
        this.b = c3289g;
        new C1234cT((Map) IntStream.rangeClosed(0, 22).boxed().collect(Collectors.toMap(new Function() { // from class: eig
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return this.b.a((Integer) obj);
            }
        }, Function.identity())));
        I2 i2E = c3289g.a.e("Lkotlin/jvm/internal/Lambda;");
        this.a = i2E;
        B1 b1 = c3289g.a;
        b1.a(i2E, b1.a(b1.E1, b1.B1), c3289g.a.c1);
    }

    public final /* synthetic */ I2 a(Integer num) {
        return this.b.a.e("Lkotlin/jvm/functions/Function" + num + ";");
    }
}
