package org.jetbrains.kotlin.fir.references.impl;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.references.FirResolvedCallableReference;
import org.jetbrains.kotlin.fir.resolve.FirResolvedSymbolOrigin;
import org.jetbrains.kotlin.fir.resolve.calls.ResolvedCallArgument;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001Ba\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\"\u0010\r\u001a\u001e\u0012\u0004\u0012\u00020\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000ej\b\u0012\u0004\u0012\u00020\u0011`\u0012¢\u0006\u0004\b\u0013\u0010\u0014J5\u0010#\u001a\u00020$\"\u0004\b\u0000\u0010%\"\u0004\b\u0001\u0010&2\u0012\u0010'\u001a\u000e\u0012\u0004\u0012\u0002H%\u0012\u0004\u0012\u0002H&0(2\u0006\u0010)\u001a\u0002H&H\u0016¢\u0006\u0002\u0010*J)\u0010+\u001a\u00020\u0000\"\u0004\b\u0000\u0010&2\f\u0010,\u001a\b\u0012\u0004\u0012\u0002H&0-2\u0006\u0010)\u001a\u0002H&H\u0016¢\u0006\u0002\u0010.J\u0012\u0010/\u001a\u00020$2\b\u00100\u001a\u0004\u0018\u00010\tH\u0016R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0018\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R0\u0010\r\u001a\u001e\u0012\u0004\u0012\u00020\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000ej\b\u0012\u0004\u0012\u00020\u0011`\u0012X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"¨\u00061"}, d2 = {"Lorg/jetbrains/kotlin/fir/references/impl/FirResolvedCallableReferenceImpl;", "Lorg/jetbrains/kotlin/fir/references/FirResolvedCallableReference;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "resolvedSymbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "resolvedSymbolOrigin", "Lorg/jetbrains/kotlin/fir/resolve/FirResolvedSymbolOrigin;", "inferredTypeArguments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "mappedArguments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolvedCallArgument;", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "Lorg/jetbrains/kotlin/fir/resolve/calls/CallableReferenceMappedArguments;", "<init>", "(Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/name/Name;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;Lorg/jetbrains/kotlin/fir/resolve/FirResolvedSymbolOrigin;Ljava/util/List;Ljava/util/Map;)V", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "getName", "()Lorg/jetbrains/kotlin/name/Name;", "getResolvedSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "getResolvedSymbolOrigin", "()Lorg/jetbrains/kotlin/fir/resolve/FirResolvedSymbolOrigin;", "setResolvedSymbolOrigin", "(Lorg/jetbrains/kotlin/fir/resolve/FirResolvedSymbolOrigin;)V", "getInferredTypeArguments", "()Ljava/util/List;", "getMappedArguments", "()Ljava/util/Map;", "acceptChildren", Argument.Delimiters.none, "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)V", "transformChildren", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/references/impl/FirResolvedCallableReferenceImpl;", "replaceResolvedSymbolOrigin", "newResolvedSymbolOrigin", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirResolvedCallableReferenceImpl extends FirResolvedCallableReference {
    private final List<ConeKotlinType> inferredTypeArguments;
    private final Map<FirValueParameter, ResolvedCallArgument<FirExpression>> mappedArguments;
    private final Name name;
    private final FirBasedSymbol<?> resolvedSymbol;
    private FirResolvedSymbolOrigin resolvedSymbolOrigin;
    private final KtSourceElement source;

    /* JADX WARN: Multi-variable type inference failed */
    public FirResolvedCallableReferenceImpl(KtSourceElement ktSourceElement, Name name, FirBasedSymbol<?> firBasedSymbol, FirResolvedSymbolOrigin firResolvedSymbolOrigin, List<ConeKotlinType> list, Map<FirValueParameter, ? extends ResolvedCallArgument<? extends FirExpression>> map) {
        name.getClass();
        firBasedSymbol.getClass();
        list.getClass();
        map.getClass();
        this.source = ktSourceElement;
        this.name = name;
        this.resolvedSymbol = firBasedSymbol;
        this.resolvedSymbolOrigin = firResolvedSymbolOrigin;
        this.inferredTypeArguments = list;
        this.mappedArguments = map;
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <R, D> void acceptChildren(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.references.FirResolvedCallableReference
    public List<ConeKotlinType> getInferredTypeArguments() {
        return this.inferredTypeArguments;
    }

    @Override // org.jetbrains.kotlin.fir.references.FirResolvedCallableReference
    public Map<FirValueParameter, ResolvedCallArgument<FirExpression>> getMappedArguments() {
        return this.mappedArguments;
    }

    @Override // org.jetbrains.kotlin.fir.references.FirResolvedCallableReference, org.jetbrains.kotlin.fir.references.FirResolvedNamedReference, org.jetbrains.kotlin.fir.references.FirNamedReference
    public Name getName() {
        return this.name;
    }

    @Override // org.jetbrains.kotlin.fir.references.FirResolvedCallableReference, org.jetbrains.kotlin.fir.references.FirResolvedNamedReference
    public FirBasedSymbol<?> getResolvedSymbol() {
        return this.resolvedSymbol;
    }

    @Override // org.jetbrains.kotlin.fir.references.FirResolvedCallableReference, org.jetbrains.kotlin.fir.references.FirResolvedNamedReference
    public FirResolvedSymbolOrigin getResolvedSymbolOrigin() {
        return this.resolvedSymbolOrigin;
    }

    @Override // org.jetbrains.kotlin.fir.references.FirResolvedCallableReference, org.jetbrains.kotlin.fir.references.FirResolvedNamedReference, org.jetbrains.kotlin.fir.references.FirNamedReference, org.jetbrains.kotlin.fir.references.FirReference, org.jetbrains.kotlin.fir.FirElement
    public KtSourceElement getSource() {
        return this.source;
    }

    @Override // org.jetbrains.kotlin.fir.references.FirResolvedCallableReference, org.jetbrains.kotlin.fir.references.FirResolvedNamedReference
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
    public <D> FirResolvedCallableReferenceImpl transformChildren(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }
}
