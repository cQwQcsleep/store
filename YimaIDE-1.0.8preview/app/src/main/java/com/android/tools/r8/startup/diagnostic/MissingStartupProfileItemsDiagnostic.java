package com.android.tools.r8.startup.diagnostic;

import com.android.tools.r8.Diagnostic;
import com.android.tools.r8.graph.C0245l1;
import com.android.tools.r8.graph.C0322w2;
import com.android.tools.r8.graph.F2;
import com.android.tools.r8.graph.I2;
import com.android.tools.r8.graph.InterfaceC0189d1;
import com.android.tools.r8.internal.AbstractC2780ub0;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;
import com.android.tools.r8.startup.diagnostic.MissingStartupProfileItemsDiagnostic;
import defpackage.ozh;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class MissingStartupProfileItemsDiagnostic implements Diagnostic {
    static final /* synthetic */ boolean d = true;
    private final List b;
    private final Origin c;

    public static class a {
        public static final /* synthetic */ boolean d = true;
        public final InterfaceC0189d1 a;
        public final Set b = AbstractC2780ub0.c();
        public Origin c;

        public a(InterfaceC0189d1 interfaceC0189d1) {
            this.a = interfaceC0189d1;
        }

        public static a b() {
            return new a(null);
        }

        public final MissingStartupProfileItemsDiagnostic a() {
            if (!d && this.b.isEmpty()) {
                x1f.a();
                return null;
            }
            ArrayList arrayList = new ArrayList(this.b);
            arrayList.sort(new ozh());
            return new MissingStartupProfileItemsDiagnostic(arrayList, this.c);
        }
    }

    public MissingStartupProfileItemsDiagnostic(ArrayList arrayList, Origin origin) {
        if (!d && arrayList.isEmpty()) {
            x1f.a();
            throw null;
        }
        this.b = arrayList;
        this.c = origin;
    }

    private static void a(final StringBuilder sb, F2 f2) {
        f2.a(new Function() { // from class: p2a
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return MissingStartupProfileItemsDiagnostic.a(sb, (I2) obj);
            }
        }, new Function() { // from class: q2a
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return MissingStartupProfileItemsDiagnostic.a(sb, (C0245l1) obj);
            }
        }, new Function() { // from class: r2a
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return MissingStartupProfileItemsDiagnostic.a(sb, (C0322w2) obj);
            }
        });
        sb.append(f2.m0());
    }

    @Override // com.android.tools.r8.Diagnostic
    public String getDiagnosticMessage() {
        StringBuilder sb = new StringBuilder();
        Iterator it = this.b.iterator();
        a(sb, (F2) it.next());
        while (it.hasNext()) {
            sb.append(System.lineSeparator());
            a(sb, (F2) it.next());
        }
        return sb.toString();
    }

    @Override // com.android.tools.r8.Diagnostic
    public Origin getOrigin() {
        return this.c;
    }

    @Override // com.android.tools.r8.Diagnostic
    public Position getPosition() {
        return Position.UNKNOWN;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ StringBuilder a(StringBuilder sb, I2 i2) {
        sb.append("Startup class not found: ");
        return sb;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ StringBuilder a(StringBuilder sb, C0245l1 c0245l1) {
        sb.append("Startup field not found: ");
        return sb;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ StringBuilder a(StringBuilder sb, C0322w2 c0322w2) {
        sb.append("Startup method not found: ");
        return sb;
    }
}
