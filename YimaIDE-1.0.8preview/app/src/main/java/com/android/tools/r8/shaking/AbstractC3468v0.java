package com.android.tools.r8.shaking;

import com.android.tools.r8.graph.B5;
import com.android.tools.r8.graph.C0245l1;
import java.util.Objects;

/* JADX INFO: renamed from: com.android.tools.r8.shaking.v0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC3468v0 extends AbstractC3389f0 {
    public final C0245l1 a;
    public final B5 b;
    public final J c;

    public AbstractC3468v0(C0245l1 c0245l1, B5 b5, J j) {
        this.a = c0245l1;
        this.b = b5;
        this.c = j;
    }

    public final boolean a(AbstractC3468v0 abstractC3468v0) {
        return this.a == abstractC3468v0.a && this.b.b(abstractC3468v0.b) && this.c.equals(abstractC3468v0.c);
    }

    public int hashCode() {
        return Objects.hash(this.a, this.b.getReference(), this.c);
    }
}
