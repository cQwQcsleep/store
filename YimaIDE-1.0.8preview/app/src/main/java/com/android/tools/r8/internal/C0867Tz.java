package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C0867Tz;
import com.android.tools.r8.utils.structural.A;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Tz, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0867Tz implements com.android.tools.r8.utils.structural.x {
    public static final /* synthetic */ boolean d = true;
    public final int[] b;
    public final List c;

    public C0867Tz(int[] iArr, AbstractC0551Hu abstractC0551Hu) {
        this.b = iArr;
        this.c = abstractC0551Hu;
        if (d || iArr.length == abstractC0551Hu.size()) {
            return;
        }
        x1f.a();
        throw null;
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.x R() {
        return this;
    }

    public final void a(BiConsumer biConsumer) {
        int i = 0;
        while (true) {
            int[] iArr = this.b;
            if (i >= iArr.length) {
                return;
            }
            biConsumer.accept(Integer.valueOf(iArr[i]), (com.android.tools.r8.utils.structural.x) this.c.get(i));
            i++;
        }
    }

    public final boolean equals(Object obj) {
        return (obj instanceof C0867Tz) && compareTo((C0867Tz) obj) == 0;
    }

    public final int hashCode() {
        return Objects.hash(Integer.valueOf(Arrays.hashCode(this.b)), this.c);
    }

    @Override // com.android.tools.r8.utils.structural.x
    public final com.android.tools.r8.utils.structural.y o() {
        return new com.android.tools.r8.utils.structural.y() { // from class: bxe
            @Override // com.android.tools.r8.utils.structural.y
            public final void a(A a) {
                C0867Tz.a(a);
            }
        };
    }

    public static void a(com.android.tools.r8.utils.structural.A a) {
        a.d(new Function() { // from class: cxe
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C0867Tz) obj).b;
            }
        }).h(new Function() { // from class: dxe
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((C0867Tz) obj).c;
            }
        });
    }
}
