package com.android.tools.r8.errors;

import com.android.tools.r8.Diagnostic;
import com.android.tools.r8.errors.StartupClassesNonStartupFractionDiagnostic;
import com.android.tools.r8.internal.C3153yx;
import com.android.tools.r8.internal.InterfaceC2300ox;
import com.android.tools.r8.internal.InterfaceC2386px;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.position.Position;
import java.util.function.BinaryOperator;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class StartupClassesNonStartupFractionDiagnostic implements Diagnostic {
    private final int b;
    private final int c;
    private final int d;
    private final InterfaceC2386px e;

    public StartupClassesNonStartupFractionDiagnostic(int i, int i2, int i3, C3153yx c3153yx) {
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.e = c3153yx;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Integer a(InterfaceC2300ox interfaceC2300ox) {
        return Integer.valueOf(interfaceC2300ox.a() > 10 ? interfaceC2300ox.getIntValue() : 0);
    }

    @Override // com.android.tools.r8.Diagnostic
    public String getDiagnosticMessage() {
        Integer numValueOf = Integer.valueOf(this.b);
        Integer numValueOf2 = Integer.valueOf(this.c + this.d);
        Integer numValueOf3 = Integer.valueOf(this.d);
        int i = this.d;
        return String.format("Startup DEX files contains %d classes and %d methods of which %d (%d%%) are non-startup methods. Distribution of classes by their number of startup methods:\n0: %d classes\n1: %d classes\n2-3: %d classes\n4-5: %d classes\n6-10: %d classes\n11+: %d classes\n", numValueOf, numValueOf2, numValueOf3, Long.valueOf(Math.round((((double) i) / ((double) (this.c + i))) * 100.0d)), Integer.valueOf(this.e.get(0)), Integer.valueOf(this.e.get(1)), Integer.valueOf(this.e.get(3) + this.e.get(2)), Integer.valueOf(this.e.get(5) + this.e.get(4)), Integer.valueOf(this.e.get(10) + this.e.get(9) + this.e.get(8) + this.e.get(7) + this.e.get(6)), this.e.h().stream().map(new Function() { // from class: amd
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return StartupClassesNonStartupFractionDiagnostic.a((InterfaceC2300ox) obj);
            }
        }).reduce(0, new BinaryOperator() { // from class: bmd
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return Integer.valueOf(Integer.sum(((Integer) obj).intValue(), ((Integer) obj2).intValue()));
            }
        }));
    }

    @Override // com.android.tools.r8.Diagnostic
    public Origin getOrigin() {
        return Origin.unknown();
    }

    @Override // com.android.tools.r8.Diagnostic
    public Position getPosition() {
        return Position.UNKNOWN;
    }
}
