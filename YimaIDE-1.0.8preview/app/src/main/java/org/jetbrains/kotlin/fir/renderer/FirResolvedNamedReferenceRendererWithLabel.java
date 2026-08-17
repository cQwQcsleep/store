package org.jetbrains.kotlin.fir.renderer;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.references.FirResolvedNamedReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0010¢\u0006\u0002\b\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/fir/renderer/FirResolvedNamedReferenceRendererWithLabel;", "Lorg/jetbrains/kotlin/fir/renderer/FirResolvedNamedReferenceRenderer;", "<init>", "()V", "render", Argument.Delimiters.none, "resolvedNamedReference", "Lorg/jetbrains/kotlin/fir/references/FirResolvedNamedReference;", "render$org_jetbrains_kotlin_tree", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirResolvedNamedReferenceRendererWithLabel extends FirResolvedNamedReferenceRenderer {
    @Override // org.jetbrains.kotlin.fir.renderer.FirResolvedNamedReferenceRenderer
    public void render$org_jetbrains_kotlin_tree(FirResolvedNamedReference resolvedNamedReference) {
        resolvedNamedReference.getClass();
        getPrinter().print("R|");
        super.render$org_jetbrains_kotlin_tree(resolvedNamedReference);
        getPrinter().print("|");
    }
}
