package org.jetbrains.kotlin.diagnostics;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u00032\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0004B\t\b\u0004¢\u0006\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u00028\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00028\u0001X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\tR\u001e\u0010\f\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f\u0082\u0001\u0003\u0010\u0011\u0012¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticWithParameters2;", "A", "B", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticWithSource;", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticWithParameters2Marker;", "<init>", "()V", "a", "getA", "()Ljava/lang/Object;", "b", "getB", "factory", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory2;", "getFactory", "()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory2;", "Lorg/jetbrains/kotlin/diagnostics/KtLightDiagnosticWithParameters2;", "Lorg/jetbrains/kotlin/diagnostics/KtOffsetsOnlyDiagnosticWithParameters2;", "Lorg/jetbrains/kotlin/diagnostics/KtPsiDiagnosticWithParameters2;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class KtDiagnosticWithParameters2<A, B> extends KtDiagnosticWithSource implements DiagnosticWithParameters2Marker<A, B> {
    private KtDiagnosticWithParameters2() {
        super(null);
    }

    @Override // org.jetbrains.kotlin.diagnostics.DiagnosticWithParameters2Marker
    public abstract A getA();

    @Override // org.jetbrains.kotlin.diagnostics.DiagnosticWithParameters2Marker
    public abstract B getB();

    @Override // org.jetbrains.kotlin.diagnostics.KtDiagnosticWithSource, org.jetbrains.kotlin.diagnostics.KtDiagnostic
    public abstract KtDiagnosticFactory2<A, B> getFactory();

    public /* synthetic */ KtDiagnosticWithParameters2(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
