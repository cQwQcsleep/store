package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C3048xh0;
import java.io.PrintStream;
import java.util.function.BiConsumer;

/* JADX INFO: renamed from: com.android.tools.r8.internal.yh0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3132yh0 extends C3048xh0 {
    public static final /* synthetic */ boolean k = true;
    public final /* synthetic */ int i;
    public final /* synthetic */ Ah0 j;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C3132yh0(Ah0 ah0, String str, boolean z, int i) {
        super(str, z);
        this.j = ah0;
        this.i = i;
    }

    @Override // com.android.tools.r8.internal.C3048xh0
    public final void a(final int i, C3048xh0 c3048xh0) {
        if (!k && a() < 0) {
            x1f.a();
            return;
        }
        C3048xh0.b(i);
        PrintStream printStream = System.out;
        printStream.print(toString());
        if (this.i <= 0) {
            printStream.println(" (unknown thread count)");
        } else {
            long jA = this.j.a.a();
            long jA2 = a();
            int i2 = this.i;
            printStream.println(", tasks: " + this.j.c + ", threads: " + i2 + ", utilization: " + Ch0.b(jA2 / ((long) i2), jA));
        }
        if (this.b) {
            a(i);
        }
        this.c.forEach(new BiConsumer() { // from class: fwi
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.a.a(i, (String) obj, (C3048xh0) obj2);
            }
        });
        C3048xh0 c3048xh1 = this.j.d;
        if (c3048xh1 == null || c3048xh1.d <= 0) {
            return;
        }
        C3048xh0.b(i);
        printStream.println("SLOWEST " + this.j.d.a(this));
        this.j.d.c.forEach(new BiConsumer() { // from class: gwi
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                this.a.b(i, (String) obj, (C3048xh0) obj2);
            }
        });
    }

    public final /* synthetic */ void b(int i, String str, C3048xh0 c3048xh0) {
        c3048xh0.a(i + 1, this);
    }

    @Override // com.android.tools.r8.internal.C3048xh0
    public final String toString() {
        return "MERGE " + super.toString();
    }

    public final /* synthetic */ void a(int i, String str, C3048xh0 c3048xh0) {
        c3048xh0.a(i + 1, this);
    }
}
