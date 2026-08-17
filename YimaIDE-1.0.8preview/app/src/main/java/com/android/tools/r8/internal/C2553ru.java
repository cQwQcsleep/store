package com.android.tools.r8.internal;

import com.android.tools.r8.Diagnostic;
import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.graph.InterfaceC0332x5;
import com.android.tools.r8.internal.C2553ru;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;
import java.util.function.Function;

/* JADX INFO: renamed from: com.android.tools.r8.internal.ru, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C2553ru implements Diagnostic {
    public final com.android.tools.r8.graph.F2 b;
    public final com.android.tools.r8.graph.B5 c;

    public C2553ru(InterfaceC0332x5 interfaceC0332x5, com.android.tools.r8.graph.B5 b5) {
        this.b = interfaceC0332x5.getReference();
        this.c = b5;
    }

    public static /* synthetic */ String a(com.android.tools.r8.graph.I2 i2) {
        return "class";
    }

    @Override // com.android.tools.r8.Diagnostic
    public final String getDiagnosticMessage() {
        return "Unexpected illegal access to non-public " + ((String) this.b.a(new Function() { // from class: r8i
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return C2553ru.a((I2) obj);
            }
        }, new Function() { // from class: s8i
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return C2553ru.a((C0245l1) obj);
            }
        }, new Function() { // from class: t8i
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return C2553ru.a((C0322w2) obj);
            }
        })) + " in another feature split (accessed: " + this.b.m0() + ", context: " + this.c.v() + ").";
    }

    @Override // com.android.tools.r8.Diagnostic
    public final Origin getOrigin() {
        return this.c.b.d;
    }

    @Override // com.android.tools.r8.Diagnostic
    public final Position getPosition() {
        return Position.UNKNOWN;
    }

    public static /* synthetic */ String a(C0245l1 c0245l1) {
        return "field";
    }

    public static /* synthetic */ String a(C0322w2 c0322w2) {
        return "method";
    }
}
