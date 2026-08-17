package org.jetbrains.kotlin.diagnostics;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u0002*\u0004\b\u0002\u0010\u00032\u00020\u00042\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0005B\t\b\u0004¢\u0006\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00028\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0012\u0010\u000b\u001a\u00028\u0001X¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\nR\u0012\u0010\r\u001a\u00028\u0002X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\nR$\u0010\u000f\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0010X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012\u0082\u0001\u0003\u0013\u0014\u0015¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticWithParameters3;", "A", "B", "C", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticWithSource;", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticWithParameters3Marker;", "<init>", "()V", "a", "getA", "()Ljava/lang/Object;", "b", "getB", "c", "getC", "factory", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory3;", "getFactory", "()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory3;", "Lorg/jetbrains/kotlin/diagnostics/KtLightDiagnosticWithParameters3;", "Lorg/jetbrains/kotlin/diagnostics/KtOffsetsOnlyDiagnosticWithParameters3;", "Lorg/jetbrains/kotlin/diagnostics/KtPsiDiagnosticWithParameters3;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class KtDiagnosticWithParameters3<A, B, C> extends KtDiagnosticWithSource implements DiagnosticWithParameters3Marker<A, B, C> {
    private KtDiagnosticWithParameters3() {
        super(null);
    }

    @Override // org.jetbrains.kotlin.diagnostics.DiagnosticWithParameters3Marker
    public abstract A getA();

    @Override // org.jetbrains.kotlin.diagnostics.DiagnosticWithParameters3Marker
    public abstract B getB();

    @Override // org.jetbrains.kotlin.diagnostics.DiagnosticWithParameters3Marker
    public abstract C getC();

    @Override // org.jetbrains.kotlin.diagnostics.KtDiagnosticWithSource, org.jetbrains.kotlin.diagnostics.KtDiagnostic
    public abstract KtDiagnosticFactory3<A, B, C> getFactory();

    public /* synthetic */ KtDiagnosticWithParameters3(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
