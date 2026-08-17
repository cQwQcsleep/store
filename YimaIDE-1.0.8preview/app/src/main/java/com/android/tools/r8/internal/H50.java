package com.android.tools.r8.internal;

import com.android.tools.r8.internal.H50;
import java.util.HashMap;
import java.util.HashSet;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class H50 {
    public final Cb0 a;
    public final R50 b;
    public WS c;
    public final HashSet d;
    public boolean e;
    public boolean f;

    public H50() {
        C1062aR c1062aR = C1062aR.a;
        this.c = WS.c;
        this.d = new HashSet(AbstractC1739iN.a(300));
        new HashMap();
        this.e = false;
        this.f = false;
        this.a = c1062aR;
        this.b = new R50();
    }

    public final void b() {
        if (this.d.isEmpty() || !this.b.i) {
            return;
        }
        if (this.e || this.f) {
            Cb0 cb0 = this.a;
            InterfaceC1270cr interfaceC1270cr = new InterfaceC1270cr() { // from class: j36
                @Override // com.android.tools.r8.internal.InterfaceC1270cr
                public final Object a() {
                    return this.b.c();
                }
            };
            ((C1062aR) cb0).getClass();
            KB.c(interfaceC1270cr, "f");
            new C3031xX(this.a, this.b, this.d, this.f).a();
        }
    }

    public final /* synthetic */ String c() {
        return "android.content.res.Resources#getIdentifier present: " + this.e + "\nWeb content present: " + this.f + "\nReferenced Strings:\n" + ((String) this.d.stream().map(new Function() { // from class: k36
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((String) obj).trim().replace("\n", "\\n");
            }
        }).filter(new Predicate() { // from class: l36
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return H50.b((String) obj);
            }
        }).map(new Function() { // from class: m36
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return H50.c((String) obj);
            }
        }).collect(Collectors.joining("\n")));
    }

    public final R50 a() {
        return this.b;
    }

    public static /* synthetic */ boolean b(String str) {
        return !str.isEmpty();
    }

    public static /* synthetic */ String c(String str) {
        return str.length() > 40 ? str.substring(0, 37).concat("...") : str;
    }
}
