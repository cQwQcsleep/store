package org.jetbrains.kotlin.diagnostics;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\u0082\u0001\u0003\b\t\n¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/KtSimpleDiagnostic;", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticWithSource;", "<init>", "()V", "factory", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory0;", "getFactory", "()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory0;", "Lorg/jetbrains/kotlin/diagnostics/KtLightSimpleDiagnostic;", "Lorg/jetbrains/kotlin/diagnostics/KtOffsetsOnlySimpleDiagnostic;", "Lorg/jetbrains/kotlin/diagnostics/KtPsiSimpleDiagnostic;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class KtSimpleDiagnostic extends KtDiagnosticWithSource {
    private KtSimpleDiagnostic() {
        super(null);
    }

    @Override // org.jetbrains.kotlin.diagnostics.KtDiagnosticWithSource, org.jetbrains.kotlin.diagnostics.KtDiagnostic
    public abstract KtDiagnosticFactory0 getFactory();

    public /* synthetic */ KtSimpleDiagnostic(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
