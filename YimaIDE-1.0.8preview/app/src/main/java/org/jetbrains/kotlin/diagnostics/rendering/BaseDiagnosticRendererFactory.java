package org.jetbrains.kotlin.diagnostics.rendering;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.KtDiagnostic;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactoryToRendererMap;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticRenderer;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0096\u0002R\u0012\u0010\b\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/diagnostics/rendering/BaseDiagnosticRendererFactory;", "Lorg/jetbrains/kotlin/diagnostics/rendering/DiagnosticRendererFactory;", "<init>", "()V", "invoke", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticRenderer;", "diagnostic", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnostic;", "MAP", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryToRendererMap;", "getMAP", "()Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactoryToRendererMap;", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class BaseDiagnosticRendererFactory implements DiagnosticRendererFactory {
    public abstract KtDiagnosticFactoryToRendererMap getMAP();

    @Override // org.jetbrains.kotlin.diagnostics.rendering.DiagnosticRendererFactory
    public KtDiagnosticRenderer invoke(KtDiagnostic diagnostic) {
        diagnostic.getClass();
        return getMAP().get(diagnostic.getFactory());
    }
}
