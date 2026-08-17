package org.jetbrains.kotlin.fir.declarations.impl;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.MutableOrEmptyList;
import org.jetbrains.kotlin.fir.builder.FirBuilderDslKt;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationAttributes;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirResolveStateKt;
import org.jetbrains.kotlin.fir.declarations.FirScriptReceiverParameter;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirReceiverParameterSymbol;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirTransformerUtilKt;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0019\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001Bc\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u000f\u0012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\u0006\u0010\u0015\u001a\u00020\u0016¢\u0006\u0004\b\u0017\u0010\u0018J5\u0010/\u001a\u000200\"\u0004\b\u0000\u00101\"\u0004\b\u0001\u001022\u0012\u00103\u001a\u000e\u0012\u0004\u0012\u0002H1\u0012\u0004\u0012\u0002H2042\u0006\u00105\u001a\u0002H2H\u0016¢\u0006\u0002\u00106J)\u00107\u001a\u00020\u0000\"\u0004\b\u0000\u001022\f\u00108\u001a\b\u0012\u0004\u0012\u0002H2092\u0006\u00105\u001a\u0002H2H\u0016¢\u0006\u0002\u0010:J)\u0010;\u001a\u00020\u0000\"\u0004\b\u0000\u001022\f\u00108\u001a\b\u0012\u0004\u0012\u0002H2092\u0006\u00105\u001a\u0002H2H\u0016¢\u0006\u0002\u0010:J)\u0010<\u001a\u00020\u0000\"\u0004\b\u0000\u001022\f\u00108\u001a\b\u0012\u0004\u0012\u0002H2092\u0006\u00105\u001a\u0002H2H\u0016¢\u0006\u0002\u0010:J\u0016\u0010=\u001a\u0002002\f\u0010>\u001a\b\u0012\u0004\u0012\u00020\u00120?H\u0016J\u0010\u0010@\u001a\u0002002\u0006\u0010A\u001a\u00020\u0014H\u0016R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0014\u0010\f\u001a\u00020\rX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0018\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\"\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u0096\u000e¢\u0006\u0010\n\u0002\u0010)\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u001a\u0010\u0013\u001a\u00020\u0014X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u0014\u0010\u0015\u001a\u00020\u0016X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010.¨\u0006B"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/impl/FirScriptReceiverParameterImpl;", "Lorg/jetbrains/kotlin/fir/declarations/FirScriptReceiverParameter;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "resolvePhase", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "origin", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "attributes", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirReceiverParameterSymbol;", "containingDeclarationSymbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "annotations", "Lorg/jetbrains/kotlin/fir/MutableOrEmptyList;", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "typeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "isBaseClassReceiver", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;Lorg/jetbrains/kotlin/fir/FirModuleData;Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;Lorg/jetbrains/kotlin/fir/symbols/impl/FirReceiverParameterSymbol;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;Ljava/util/List;Lorg/jetbrains/kotlin/fir/types/FirTypeRef;ZLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "getModuleData", "()Lorg/jetbrains/kotlin/fir/FirModuleData;", "getOrigin", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "getAttributes", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;", "getSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirReceiverParameterSymbol;", "getContainingDeclarationSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "getAnnotations-5e3fPpI", "()Ljava/util/List;", "setAnnotations-GqUYU-s", "(Ljava/util/List;)V", "Ljava/util/List;", "getTypeRef", "()Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "setTypeRef", "(Lorg/jetbrains/kotlin/fir/types/FirTypeRef;)V", "()Z", "acceptChildren", Argument.Delimiters.none, "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)V", "transformChildren", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/declarations/impl/FirScriptReceiverParameterImpl;", "transformAnnotations", "transformTypeRef", "replaceAnnotations", "newAnnotations", Argument.Delimiters.none, "replaceTypeRef", "newTypeRef", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirScriptReceiverParameterImpl extends FirScriptReceiverParameter {
    private List<FirAnnotation> annotations;
    private final FirDeclarationAttributes attributes;
    private final FirBasedSymbol<?> containingDeclarationSymbol;
    private final boolean isBaseClassReceiver;
    private final FirModuleData moduleData;
    private final FirDeclarationOrigin origin;
    private final KtSourceElement source;
    private final FirReceiverParameterSymbol symbol;
    private FirTypeRef typeRef;

    private FirScriptReceiverParameterImpl(KtSourceElement ktSourceElement, FirResolvePhase firResolvePhase, FirModuleData firModuleData, FirDeclarationOrigin firDeclarationOrigin, FirDeclarationAttributes firDeclarationAttributes, FirReceiverParameterSymbol firReceiverParameterSymbol, FirBasedSymbol<?> firBasedSymbol, List<FirAnnotation> list, FirTypeRef firTypeRef, boolean z) {
        firResolvePhase.getClass();
        firModuleData.getClass();
        firDeclarationOrigin.getClass();
        firDeclarationAttributes.getClass();
        firReceiverParameterSymbol.getClass();
        firBasedSymbol.getClass();
        firTypeRef.getClass();
        this.source = ktSourceElement;
        this.moduleData = firModuleData;
        this.origin = firDeclarationOrigin;
        this.attributes = firDeclarationAttributes;
        this.symbol = firReceiverParameterSymbol;
        this.containingDeclarationSymbol = firBasedSymbol;
        this.annotations = list;
        this.typeRef = firTypeRef;
        this.isBaseClassReceiver = z;
        getSymbol().bind(this);
        setResolveState(FirResolveStateKt.asResolveState(firResolvePhase));
        if (getSource() == null && Intrinsics.areEqual(getOrigin(), FirDeclarationOrigin.Source.INSTANCE)) {
            z1f.a(Reflection.getOrCreateKotlinClass(FirScriptReceiverParameterImpl.class).getSimpleName(), " with Source origin was instantiated without a source element.");
            throw null;
        }
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <R, D> void acceptChildren(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        Iterator<T> it = MutableOrEmptyList.m194boximpl(m358getAnnotations5e3fPpI()).iterator();
        while (it.hasNext()) {
            ((FirAnnotation) it.next()).accept(visitor, data);
        }
        getTypeRef().accept(visitor, data);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirScriptReceiverParameter, org.jetbrains.kotlin.fir.declarations.FirReceiverParameter, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ List getAnnotations() {
        return MutableOrEmptyList.m194boximpl(m358getAnnotations5e3fPpI());
    }

    /* JADX INFO: renamed from: getAnnotations-5e3fPpI, reason: not valid java name */
    public List<FirAnnotation> m358getAnnotations5e3fPpI() {
        return this.annotations;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirScriptReceiverParameter, org.jetbrains.kotlin.fir.declarations.FirReceiverParameter, org.jetbrains.kotlin.fir.declarations.FirDeclaration
    public FirDeclarationAttributes getAttributes() {
        return this.attributes;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirScriptReceiverParameter, org.jetbrains.kotlin.fir.declarations.FirReceiverParameter
    public FirBasedSymbol<?> getContainingDeclarationSymbol() {
        return this.containingDeclarationSymbol;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirScriptReceiverParameter, org.jetbrains.kotlin.fir.declarations.FirReceiverParameter, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirElementWithResolveState
    public FirModuleData getModuleData() {
        return this.moduleData;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirScriptReceiverParameter, org.jetbrains.kotlin.fir.declarations.FirReceiverParameter, org.jetbrains.kotlin.fir.declarations.FirDeclaration
    public FirDeclarationOrigin getOrigin() {
        return this.origin;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirScriptReceiverParameter, org.jetbrains.kotlin.fir.declarations.FirReceiverParameter, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirElementWithResolveState, org.jetbrains.kotlin.fir.FirElement
    public KtSourceElement getSource() {
        return this.source;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirScriptReceiverParameter, org.jetbrains.kotlin.fir.declarations.FirReceiverParameter
    public FirTypeRef getTypeRef() {
        return this.typeRef;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirScriptReceiverParameter
    /* JADX INFO: renamed from: isBaseClassReceiver, reason: from getter */
    public boolean getIsBaseClassReceiver() {
        return this.isBaseClassReceiver;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirScriptReceiverParameter, org.jetbrains.kotlin.fir.declarations.FirReceiverParameter, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public void replaceAnnotations(List<? extends FirAnnotation> newAnnotations) {
        newAnnotations.getClass();
        m359setAnnotationsGqUYUs(FirBuilderDslKt.toMutableOrEmptyForImmutable(newAnnotations));
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirScriptReceiverParameter, org.jetbrains.kotlin.fir.declarations.FirReceiverParameter
    public void replaceTypeRef(FirTypeRef newTypeRef) {
        newTypeRef.getClass();
        setTypeRef(newTypeRef);
    }

    /* JADX INFO: renamed from: setAnnotations-GqUYU-s, reason: not valid java name */
    public void m359setAnnotationsGqUYUs(List<FirAnnotation> list) {
        this.annotations = list;
    }

    public void setTypeRef(FirTypeRef firTypeRef) {
        firTypeRef.getClass();
        this.typeRef = firTypeRef;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirScriptReceiverParameter, org.jetbrains.kotlin.fir.declarations.FirReceiverParameter, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public <D> FirScriptReceiverParameterImpl transformAnnotations(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirTransformerUtilKt.m709transformInplaceaLnlfrU(m358getAnnotations5e3fPpI(), transformer, data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <D> FirScriptReceiverParameterImpl transformChildren(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        transformAnnotations((FirTransformer) transformer, (Object) data);
        transformTypeRef((FirTransformer) transformer, (Object) data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirScriptReceiverParameter, org.jetbrains.kotlin.fir.declarations.FirReceiverParameter
    public <D> FirScriptReceiverParameterImpl transformTypeRef(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        setTypeRef((FirTypeRef) getTypeRef().transform(transformer, data));
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirScriptReceiverParameter, org.jetbrains.kotlin.fir.declarations.FirReceiverParameter, org.jetbrains.kotlin.fir.declarations.FirDeclaration
    public FirReceiverParameterSymbol getSymbol() {
        return this.symbol;
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public /* bridge */ /* synthetic */ FirElement transformChildren(FirTransformer firTransformer, Object obj) {
        return transformChildren((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirScriptReceiverParameter, org.jetbrains.kotlin.fir.declarations.FirReceiverParameter, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirDeclaration transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirScriptReceiverParameter, org.jetbrains.kotlin.fir.declarations.FirReceiverParameter, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirReceiverParameter transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirScriptReceiverParameter, org.jetbrains.kotlin.fir.declarations.FirReceiverParameter, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirScriptReceiverParameter transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirScriptReceiverParameter, org.jetbrains.kotlin.fir.declarations.FirReceiverParameter, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirAnnotationContainer transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirScriptReceiverParameter, org.jetbrains.kotlin.fir.declarations.FirReceiverParameter
    public /* bridge */ /* synthetic */ FirScriptReceiverParameter transformTypeRef(FirTransformer firTransformer, Object obj) {
        return transformTypeRef((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirScriptReceiverParameter, org.jetbrains.kotlin.fir.declarations.FirReceiverParameter
    public /* bridge */ /* synthetic */ FirReceiverParameter transformTypeRef(FirTransformer firTransformer, Object obj) {
        return transformTypeRef((FirTransformer<? super Object>) firTransformer, obj);
    }

    public /* synthetic */ FirScriptReceiverParameterImpl(KtSourceElement ktSourceElement, FirResolvePhase firResolvePhase, FirModuleData firModuleData, FirDeclarationOrigin firDeclarationOrigin, FirDeclarationAttributes firDeclarationAttributes, FirReceiverParameterSymbol firReceiverParameterSymbol, FirBasedSymbol firBasedSymbol, List list, FirTypeRef firTypeRef, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
        this(ktSourceElement, firResolvePhase, firModuleData, firDeclarationOrigin, firDeclarationAttributes, firReceiverParameterSymbol, firBasedSymbol, list, firTypeRef, z);
    }
}
