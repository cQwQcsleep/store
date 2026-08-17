package org.jetbrains.kotlin.fir.references;

import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.resolve.FirResolvedSymbolOrigin;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J5\u0010\u0018\u001a\u0002H\u0019\"\u0004\b\u0000\u0010\u0019\"\u0004\b\u0001\u0010\u001a2\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u0002H\u0019\u0012\u0004\u0012\u0002H\u001a0\u001c2\u0006\u0010\u001d\u001a\u0002H\u001aH\u0016¢\u0006\u0002\u0010\u001eJ3\u0010\u001f\u001a\u0002H \"\b\b\u0000\u0010 *\u00020!\"\u0004\b\u0001\u0010\u001a2\f\u0010\"\u001a\b\u0012\u0004\u0012\u0002H\u001a0#2\u0006\u0010\u001d\u001a\u0002H\u001aH\u0016¢\u0006\u0002\u0010$J\u0012\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010\u0011H&R\u0014\u0010\u0004\u001a\u0004\u0018\u00010\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0012\u0010\b\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\f\u001a\u0006\u0012\u0002\b\u00030\rX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u0004\u0018\u00010\u0011X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0012\u0010\u0014\u001a\u00020\u0015X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017¨\u0006("}, d2 = {"Lorg/jetbrains/kotlin/fir/references/FirPropertyWithExplicitBackingFieldResolvedNamedReference;", "Lorg/jetbrains/kotlin/fir/references/FirResolvedNamedReference;", "<init>", "()V", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "getName", "()Lorg/jetbrains/kotlin/name/Name;", "resolvedSymbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "getResolvedSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "resolvedSymbolOrigin", "Lorg/jetbrains/kotlin/fir/resolve/FirResolvedSymbolOrigin;", "getResolvedSymbolOrigin", "()Lorg/jetbrains/kotlin/fir/resolve/FirResolvedSymbolOrigin;", "hasVisibleBackingField", Argument.Delimiters.none, "getHasVisibleBackingField", "()Z", "accept", "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "transform", "E", "Lorg/jetbrains/kotlin/fir/FirElement;", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/FirElement;", "replaceResolvedSymbolOrigin", Argument.Delimiters.none, "newResolvedSymbolOrigin", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirPropertyWithExplicitBackingFieldResolvedNamedReference extends FirResolvedNamedReference {
    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.references.FirResolvedNamedReference, org.jetbrains.kotlin.fir.references.FirNamedReference, org.jetbrains.kotlin.fir.references.FirReference, org.jetbrains.kotlin.fir.FirElement
    public <R, D> R accept(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        return visitor.visitPropertyWithExplicitBackingFieldResolvedNamedReference(this, data);
    }

    public abstract boolean getHasVisibleBackingField();

    @Override // org.jetbrains.kotlin.fir.references.FirResolvedNamedReference, org.jetbrains.kotlin.fir.references.FirNamedReference
    public abstract Name getName();

    @Override // org.jetbrains.kotlin.fir.references.FirResolvedNamedReference
    public abstract FirBasedSymbol<?> getResolvedSymbol();

    @Override // org.jetbrains.kotlin.fir.references.FirResolvedNamedReference
    public abstract FirResolvedSymbolOrigin getResolvedSymbolOrigin();

    @Override // org.jetbrains.kotlin.fir.references.FirResolvedNamedReference, org.jetbrains.kotlin.fir.references.FirNamedReference, org.jetbrains.kotlin.fir.references.FirReference, org.jetbrains.kotlin.fir.FirElement
    public abstract KtSourceElement getSource();

    @Override // org.jetbrains.kotlin.fir.references.FirResolvedNamedReference
    public abstract void replaceResolvedSymbolOrigin(FirResolvedSymbolOrigin newResolvedSymbolOrigin);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.references.FirResolvedNamedReference, org.jetbrains.kotlin.fir.references.FirNamedReference, org.jetbrains.kotlin.fir.references.FirReference, org.jetbrains.kotlin.fir.FirElement
    public <E extends FirElement, D> E transform(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirReference firReferenceTransformPropertyWithExplicitBackingFieldResolvedNamedReference = transformer.transformPropertyWithExplicitBackingFieldResolvedNamedReference(this, data);
        firReferenceTransformPropertyWithExplicitBackingFieldResolvedNamedReference.getClass();
        return firReferenceTransformPropertyWithExplicitBackingFieldResolvedNamedReference;
    }
}
