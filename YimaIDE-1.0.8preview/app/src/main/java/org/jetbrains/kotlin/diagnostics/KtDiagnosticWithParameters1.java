package org.jetbrains.kotlin.diagnostics;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\t\b\u0004¢\u0006\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00028\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0018\u0010\t\u001a\b\u0012\u0004\u0012\u00028\u00000\nX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f\u0082\u0001\u0003\r\u000e\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticWithParameters1;", "A", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticWithSource;", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticWithParameters1Marker;", "<init>", "()V", "a", "getA", "()Ljava/lang/Object;", "factory", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory1;", "getFactory", "()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory1;", "Lorg/jetbrains/kotlin/diagnostics/KtLightDiagnosticWithParameters1;", "Lorg/jetbrains/kotlin/diagnostics/KtOffsetsOnlyDiagnosticWithParameters1;", "Lorg/jetbrains/kotlin/diagnostics/KtPsiDiagnosticWithParameters1;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class KtDiagnosticWithParameters1<A> extends KtDiagnosticWithSource implements DiagnosticWithParameters1Marker<A> {
    private KtDiagnosticWithParameters1() {
        super(null);
    }

    @Override // org.jetbrains.kotlin.diagnostics.DiagnosticWithParameters1Marker
    public abstract A getA();

    @Override // org.jetbrains.kotlin.diagnostics.KtDiagnosticWithSource, org.jetbrains.kotlin.diagnostics.KtDiagnostic
    public abstract KtDiagnosticFactory1<A> getFactory();

    public /* synthetic */ KtDiagnosticWithParameters1(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
