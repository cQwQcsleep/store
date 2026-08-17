package org.jetbrains.kotlin.fir.declarations.impl;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.MutableOrEmptyList;
import org.jetbrains.kotlin.fir.builder.FirBuilderDslKt;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationAttributes;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirReplSnippet;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirResolveStateKt;
import org.jetbrains.kotlin.fir.declarations.FirScriptReceiverParameter;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirReplSnippetSymbol;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirTransformerUtilKt;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0000\b\u0000\u0018\u00002\u00020\u0001Bk\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u0005\u0012\u0006\u0010\u0015\u001a\u00020\u0016\u0012\u0006\u0010\u0017\u001a\u00020\u0018¢\u0006\u0004\b\u0019\u0010\u001aJ5\u00104\u001a\u000205\"\u0004\b\u0000\u00106\"\u0004\b\u0001\u001072\u0012\u00108\u001a\u000e\u0012\u0004\u0012\u0002H6\u0012\u0004\u0012\u0002H7092\u0006\u0010:\u001a\u0002H7H\u0016¢\u0006\u0002\u0010;J)\u0010<\u001a\u00020\u0000\"\u0004\b\u0000\u001072\f\u0010=\u001a\b\u0012\u0004\u0012\u0002H70>2\u0006\u0010:\u001a\u0002H7H\u0016¢\u0006\u0002\u0010?J)\u0010@\u001a\u00020\u0000\"\u0004\b\u0000\u001072\f\u0010=\u001a\b\u0012\u0004\u0012\u0002H70>2\u0006\u0010:\u001a\u0002H7H\u0016¢\u0006\u0002\u0010?J)\u0010A\u001a\u00020\u0000\"\u0004\b\u0000\u001072\f\u0010=\u001a\b\u0012\u0004\u0012\u0002H70>2\u0006\u0010:\u001a\u0002H7H\u0016¢\u0006\u0002\u0010?J)\u0010B\u001a\u00020\u0000\"\u0004\b\u0000\u001072\f\u0010=\u001a\b\u0012\u0004\u0012\u0002H70>2\u0006\u0010:\u001a\u0002H7H\u0016¢\u0006\u0002\u0010?J\u0016\u0010C\u001a\u0002052\f\u0010D\u001a\b\u0012\u0004\u0012\u00020\u00060EH\u0016R\"\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0096\u000e¢\u0006\u0010\n\u0002\u0010\u001f\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0014\u0010\t\u001a\u00020\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0014\u0010\u000b\u001a\u00020\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0014\u0010\r\u001a\u00020\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0014\u0010\u000f\u001a\u00020\u0010X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0014\u0010\u0011\u001a\u00020\u0012X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\"\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u0005X\u0096\u000e¢\u0006\u0010\n\u0002\u0010\u001f\u001a\u0004\b,\u0010\u001c\"\u0004\b-\u0010\u001eR\u001a\u0010\u0015\u001a\u00020\u0016X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u0014\u0010\u0017\u001a\u00020\u0018X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b2\u00103¨\u0006F"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/impl/FirReplSnippetImpl;", "Lorg/jetbrains/kotlin/fir/declarations/FirReplSnippet;", "resolvePhase", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "annotations", "Lorg/jetbrains/kotlin/fir/MutableOrEmptyList;", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "origin", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "attributes", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirReplSnippetSymbol;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "receivers", "Lorg/jetbrains/kotlin/fir/declarations/FirScriptReceiverParameter;", "snippetClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "evalFunctionSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "<init>", "(Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;Ljava/util/List;Lorg/jetbrains/kotlin/fir/FirModuleData;Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;Lorg/jetbrains/kotlin/name/Name;Lorg/jetbrains/kotlin/fir/symbols/impl/FirReplSnippetSymbol;Lorg/jetbrains/kotlin/KtSourceElement;Ljava/util/List;Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getAnnotations-5e3fPpI", "()Ljava/util/List;", "setAnnotations-GqUYU-s", "(Ljava/util/List;)V", "Ljava/util/List;", "getModuleData", "()Lorg/jetbrains/kotlin/fir/FirModuleData;", "getOrigin", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "getAttributes", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;", "getName", "()Lorg/jetbrains/kotlin/name/Name;", "getSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirReplSnippetSymbol;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "getReceivers-5e3fPpI", "setReceivers-GqUYU-s", "getSnippetClass", "()Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "setSnippetClass", "(Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;)V", "getEvalFunctionSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "acceptChildren", Argument.Delimiters.none, "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)V", "transformChildren", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/declarations/impl/FirReplSnippetImpl;", "transformAnnotations", "transformReceivers", "transformSnippetClass", "replaceAnnotations", "newAnnotations", Argument.Delimiters.none, "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirReplSnippetImpl extends FirReplSnippet {
    private List<FirAnnotation> annotations;
    private final FirDeclarationAttributes attributes;
    private final FirNamedFunctionSymbol evalFunctionSymbol;
    private final FirModuleData moduleData;
    private final Name name;
    private final FirDeclarationOrigin origin;
    private List<FirScriptReceiverParameter> receivers;
    private FirRegularClass snippetClass;
    private final KtSourceElement source;
    private final FirReplSnippetSymbol symbol;

    private FirReplSnippetImpl(FirResolvePhase firResolvePhase, List<FirAnnotation> list, FirModuleData firModuleData, FirDeclarationOrigin firDeclarationOrigin, FirDeclarationAttributes firDeclarationAttributes, Name name, FirReplSnippetSymbol firReplSnippetSymbol, KtSourceElement ktSourceElement, List<FirScriptReceiverParameter> list2, FirRegularClass firRegularClass, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firResolvePhase.getClass();
        firModuleData.getClass();
        firDeclarationOrigin.getClass();
        firDeclarationAttributes.getClass();
        name.getClass();
        firReplSnippetSymbol.getClass();
        ktSourceElement.getClass();
        firRegularClass.getClass();
        firNamedFunctionSymbol.getClass();
        this.annotations = list;
        this.moduleData = firModuleData;
        this.origin = firDeclarationOrigin;
        this.attributes = firDeclarationAttributes;
        this.name = name;
        this.symbol = firReplSnippetSymbol;
        this.source = ktSourceElement;
        this.receivers = list2;
        this.snippetClass = firRegularClass;
        this.evalFunctionSymbol = firNamedFunctionSymbol;
        getSymbol().bind(this);
        setResolveState(FirResolveStateKt.asResolveState(firResolvePhase));
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <R, D> void acceptChildren(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        Iterator<T> it = MutableOrEmptyList.m194boximpl(m350getAnnotations5e3fPpI()).iterator();
        while (it.hasNext()) {
            ((FirAnnotation) it.next()).accept(visitor, data);
        }
        Iterator<T> it2 = MutableOrEmptyList.m194boximpl(m351getReceivers5e3fPpI()).iterator();
        while (it2.hasNext()) {
            ((FirScriptReceiverParameter) it2.next()).accept(visitor, data);
        }
        getSnippetClass().accept(visitor, data);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirReplSnippet, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ List getAnnotations() {
        return MutableOrEmptyList.m194boximpl(m350getAnnotations5e3fPpI());
    }

    /* JADX INFO: renamed from: getAnnotations-5e3fPpI, reason: not valid java name */
    public List<FirAnnotation> m350getAnnotations5e3fPpI() {
        return this.annotations;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirReplSnippet, org.jetbrains.kotlin.fir.declarations.FirDeclaration
    public FirDeclarationAttributes getAttributes() {
        return this.attributes;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirReplSnippet
    public FirNamedFunctionSymbol getEvalFunctionSymbol() {
        return this.evalFunctionSymbol;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirReplSnippet, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirElementWithResolveState
    public FirModuleData getModuleData() {
        return this.moduleData;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirReplSnippet
    public Name getName() {
        return this.name;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirReplSnippet, org.jetbrains.kotlin.fir.declarations.FirDeclaration
    public FirDeclarationOrigin getOrigin() {
        return this.origin;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirReplSnippet
    public /* bridge */ /* synthetic */ List getReceivers() {
        return MutableOrEmptyList.m194boximpl(m351getReceivers5e3fPpI());
    }

    /* JADX INFO: renamed from: getReceivers-5e3fPpI, reason: not valid java name */
    public List<FirScriptReceiverParameter> m351getReceivers5e3fPpI() {
        return this.receivers;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirReplSnippet
    public FirRegularClass getSnippetClass() {
        return this.snippetClass;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirReplSnippet, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirElementWithResolveState, org.jetbrains.kotlin.fir.FirElement
    public KtSourceElement getSource() {
        return this.source;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirReplSnippet, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public void replaceAnnotations(List<? extends FirAnnotation> newAnnotations) {
        newAnnotations.getClass();
        m352setAnnotationsGqUYUs(FirBuilderDslKt.toMutableOrEmptyForImmutable(newAnnotations));
    }

    /* JADX INFO: renamed from: setAnnotations-GqUYU-s, reason: not valid java name */
    public void m352setAnnotationsGqUYUs(List<FirAnnotation> list) {
        this.annotations = list;
    }

    /* JADX INFO: renamed from: setReceivers-GqUYU-s, reason: not valid java name */
    public void m353setReceiversGqUYUs(List<FirScriptReceiverParameter> list) {
        this.receivers = list;
    }

    public void setSnippetClass(FirRegularClass firRegularClass) {
        firRegularClass.getClass();
        this.snippetClass = firRegularClass;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirReplSnippet, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public <D> FirReplSnippetImpl transformAnnotations(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirTransformerUtilKt.m709transformInplaceaLnlfrU(m350getAnnotations5e3fPpI(), transformer, data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <D> FirReplSnippetImpl transformChildren(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        transformAnnotations((FirTransformer) transformer, (Object) data);
        transformReceivers((FirTransformer) transformer, (Object) data);
        transformSnippetClass((FirTransformer) transformer, (Object) data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirReplSnippet
    public <D> FirReplSnippetImpl transformReceivers(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirTransformerUtilKt.m709transformInplaceaLnlfrU(m351getReceivers5e3fPpI(), transformer, data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirReplSnippet
    public <D> FirReplSnippetImpl transformSnippetClass(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        setSnippetClass((FirRegularClass) getSnippetClass().transform(transformer, data));
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirReplSnippet, org.jetbrains.kotlin.fir.declarations.FirDeclaration
    public FirReplSnippetSymbol getSymbol() {
        return this.symbol;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirReplSnippet, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirDeclaration transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirReplSnippet
    public /* bridge */ /* synthetic */ FirReplSnippet transformReceivers(FirTransformer firTransformer, Object obj) {
        return transformReceivers((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirReplSnippet, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirReplSnippet transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirReplSnippet, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirAnnotationContainer transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public /* bridge */ /* synthetic */ FirElement transformChildren(FirTransformer firTransformer, Object obj) {
        return transformChildren((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirReplSnippet
    public /* bridge */ /* synthetic */ FirReplSnippet transformSnippetClass(FirTransformer firTransformer, Object obj) {
        return transformSnippetClass((FirTransformer<? super Object>) firTransformer, obj);
    }

    public /* synthetic */ FirReplSnippetImpl(FirResolvePhase firResolvePhase, List list, FirModuleData firModuleData, FirDeclarationOrigin firDeclarationOrigin, FirDeclarationAttributes firDeclarationAttributes, Name name, FirReplSnippetSymbol firReplSnippetSymbol, KtSourceElement ktSourceElement, List list2, FirRegularClass firRegularClass, FirNamedFunctionSymbol firNamedFunctionSymbol, DefaultConstructorMarker defaultConstructorMarker) {
        this(firResolvePhase, list, firModuleData, firDeclarationOrigin, firDeclarationAttributes, name, firReplSnippetSymbol, ktSourceElement, list2, firRegularClass, firNamedFunctionSymbol);
    }
}
