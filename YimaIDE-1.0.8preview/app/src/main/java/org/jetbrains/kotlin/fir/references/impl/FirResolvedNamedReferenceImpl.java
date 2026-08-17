package org.jetbrains.kotlin.fir.references.impl;

import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.references.FirResolvedNamedReference;
import org.jetbrains.kotlin.fir.resolve.FirResolvedSymbolOrigin;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B/\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bJ5\u0010\u0016\u001a\u00020\u0017\"\u0004\b\u0000\u0010\u0018\"\u0004\b\u0001\u0010\u00192\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u0002H\u0018\u0012\u0004\u0012\u0002H\u00190\u001b2\u0006\u0010\u001c\u001a\u0002H\u0019H\u0016¢\u0006\u0002\u0010\u001dJ)\u0010\u001e\u001a\u00020\u0000\"\u0004\b\u0000\u0010\u00192\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u0002H\u00190 2\u0006\u0010\u001c\u001a\u0002H\u0019H\u0016¢\u0006\u0002\u0010!J\u0012\u0010\"\u001a\u00020\u00172\b\u0010#\u001a\u0004\u0018\u00010\tH\u0016R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0018\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006$"}, d2 = {"Lorg/jetbrains/kotlin/fir/references/impl/FirResolvedNamedReferenceImpl;", "Lorg/jetbrains/kotlin/fir/references/FirResolvedNamedReference;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "resolvedSymbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "resolvedSymbolOrigin", "Lorg/jetbrains/kotlin/fir/resolve/FirResolvedSymbolOrigin;", "<init>", "(Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/name/Name;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;Lorg/jetbrains/kotlin/fir/resolve/FirResolvedSymbolOrigin;)V", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "getName", "()Lorg/jetbrains/kotlin/name/Name;", "getResolvedSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "getResolvedSymbolOrigin", "()Lorg/jetbrains/kotlin/fir/resolve/FirResolvedSymbolOrigin;", "setResolvedSymbolOrigin", "(Lorg/jetbrains/kotlin/fir/resolve/FirResolvedSymbolOrigin;)V", "acceptChildren", Argument.Delimiters.none, "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)V", "transformChildren", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/references/impl/FirResolvedNamedReferenceImpl;", "replaceResolvedSymbolOrigin", "newResolvedSymbolOrigin", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirResolvedNamedReferenceImpl extends FirResolvedNamedReference {
    private final Name name;
    private final FirBasedSymbol<?> resolvedSymbol;
    private FirResolvedSymbolOrigin resolvedSymbolOrigin;
    private final KtSourceElement source;

    public FirResolvedNamedReferenceImpl(KtSourceElement ktSourceElement, Name name, FirBasedSymbol<?> firBasedSymbol, FirResolvedSymbolOrigin firResolvedSymbolOrigin) {
        name.getClass();
        firBasedSymbol.getClass();
        this.source = ktSourceElement;
        this.name = name;
        this.resolvedSymbol = firBasedSymbol;
        this.resolvedSymbolOrigin = firResolvedSymbolOrigin;
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <R, D> void acceptChildren(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.references.FirResolvedNamedReference, org.jetbrains.kotlin.fir.references.FirNamedReference
    public Name getName() {
        return this.name;
    }

    @Override // org.jetbrains.kotlin.fir.references.FirResolvedNamedReference
    public FirBasedSymbol<?> getResolvedSymbol() {
        return this.resolvedSymbol;
    }

    @Override // org.jetbrains.kotlin.fir.references.FirResolvedNamedReference
    public FirResolvedSymbolOrigin getResolvedSymbolOrigin() {
        return this.resolvedSymbolOrigin;
    }

    @Override // org.jetbrains.kotlin.fir.references.FirResolvedNamedReference, org.jetbrains.kotlin.fir.references.FirNamedReference, org.jetbrains.kotlin.fir.references.FirReference, org.jetbrains.kotlin.fir.FirElement
    public KtSourceElement getSource() {
        return this.source;
    }

    @Override // org.jetbrains.kotlin.fir.references.FirResolvedNamedReference
    public void replaceResolvedSymbolOrigin(FirResolvedSymbolOrigin newResolvedSymbolOrigin) {
        setResolvedSymbolOrigin(newResolvedSymbolOrigin);
    }

    public void setResolvedSymbolOrigin(FirResolvedSymbolOrigin firResolvedSymbolOrigin) {
        this.resolvedSymbolOrigin = firResolvedSymbolOrigin;
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public /* bridge */ /* synthetic */ FirElement transformChildren(FirTransformer firTransformer, Object obj) {
        return transformChildren((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <D> FirResolvedNamedReferenceImpl transformChildren(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }
}
