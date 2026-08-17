package org.jetbrains.kotlin.diagnostics;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u0002*\u0004\b\u0002\u0010\u0003*\u0004\b\u0003\u0010\u00042\u00020\u00052\u001a\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u0002H\u00040\u0006B\t\b\u0004¢\u0006\u0004\b\u0007\u0010\bR\u0012\u0010\t\u001a\u00028\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0012\u0010\f\u001a\u00028\u0001X¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000bR\u0012\u0010\u000e\u001a\u00028\u0002X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u000bR\u0012\u0010\u0010\u001a\u00028\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000bR*\u0010\u0012\u001a\u001a\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u0002\u0012\u0004\u0012\u00028\u00030\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015\u0082\u0001\u0003\u0016\u0017\u0018¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticWithParameters4;", "A", "B", "C", "D", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticWithSource;", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticWithParameters4Marker;", "<init>", "()V", "a", "getA", "()Ljava/lang/Object;", "b", "getB", "c", "getC", "d", "getD", "factory", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory4;", "getFactory", "()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory4;", "Lorg/jetbrains/kotlin/diagnostics/KtLightDiagnosticWithParameters4;", "Lorg/jetbrains/kotlin/diagnostics/KtOffsetsOnlyDiagnosticWithParameters4;", "Lorg/jetbrains/kotlin/diagnostics/KtPsiDiagnosticWithParameters4;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class KtDiagnosticWithParameters4<A, B, C, D> extends KtDiagnosticWithSource implements DiagnosticWithParameters4Marker<A, B, C, D> {
    private KtDiagnosticWithParameters4() {
        super(null);
    }

    @Override // org.jetbrains.kotlin.diagnostics.DiagnosticWithParameters4Marker
    public abstract A getA();

    @Override // org.jetbrains.kotlin.diagnostics.DiagnosticWithParameters4Marker
    public abstract B getB();

    @Override // org.jetbrains.kotlin.diagnostics.DiagnosticWithParameters4Marker
    public abstract C getC();

    @Override // org.jetbrains.kotlin.diagnostics.DiagnosticWithParameters4Marker
    public abstract D getD();

    @Override // org.jetbrains.kotlin.diagnostics.KtDiagnosticWithSource, org.jetbrains.kotlin.diagnostics.KtDiagnostic
    public abstract KtDiagnosticFactory4<A, B, C, D> getFactory();

    public /* synthetic */ KtDiagnosticWithParameters4(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
