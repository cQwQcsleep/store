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
import org.jetbrains.kotlin.fir.declarations.DirectDeclarationsAccess;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationAttributes;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirResolveStateKt;
import org.jetbrains.kotlin.fir.declarations.FirScript;
import org.jetbrains.kotlin.fir.declarations.FirScriptReceiverParameter;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.references.FirControlFlowGraphReference;
import org.jetbrains.kotlin.fir.symbols.impl.FirScriptSymbol;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirTransformerUtilKt;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u0081\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u0010\u0012\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u0005\u0012\b\u0010\u001a\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\u001b\u0010\u001cJ5\u0010<\u001a\u00020=\"\u0004\b\u0000\u0010>\"\u0004\b\u0001\u0010?2\u0012\u0010@\u001a\u000e\u0012\u0004\u0012\u0002H>\u0012\u0004\u0012\u0002H?0A2\u0006\u0010B\u001a\u0002H?H\u0016¢\u0006\u0002\u0010CJ)\u0010D\u001a\u00020\u0000\"\u0004\b\u0000\u0010?2\f\u0010E\u001a\b\u0012\u0004\u0012\u0002H?0F2\u0006\u0010B\u001a\u0002H?H\u0016¢\u0006\u0002\u0010GJ)\u0010H\u001a\u00020\u0000\"\u0004\b\u0000\u0010?2\f\u0010E\u001a\b\u0012\u0004\u0012\u0002H?0F2\u0006\u0010B\u001a\u0002H?H\u0016¢\u0006\u0002\u0010GJ)\u0010I\u001a\u00020\u0000\"\u0004\b\u0000\u0010?2\f\u0010E\u001a\b\u0012\u0004\u0012\u0002H?0F2\u0006\u0010B\u001a\u0002H?H\u0016¢\u0006\u0002\u0010GJ)\u0010J\u001a\u00020\u0000\"\u0004\b\u0000\u0010?2\f\u0010E\u001a\b\u0012\u0004\u0012\u0002H?0F2\u0006\u0010B\u001a\u0002H?H\u0016¢\u0006\u0002\u0010GJ)\u0010K\u001a\u00020\u0000\"\u0004\b\u0000\u0010?2\f\u0010E\u001a\b\u0012\u0004\u0012\u0002H?0F2\u0006\u0010B\u001a\u0002H?H\u0016¢\u0006\u0002\u0010GJ\u0016\u0010L\u001a\u00020=2\f\u0010M\u001a\b\u0012\u0004\u0012\u00020\u00060NH\u0016J\u0012\u0010O\u001a\u00020=2\b\u0010P\u001a\u0004\u0018\u000107H\u0016J\u0016\u0010Q\u001a\u00020=2\f\u0010R\u001a\b\u0012\u0004\u0012\u00020\u00110NH\u0016R\"\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0096\u000e¢\u0006\u0010\n\u0002\u0010!\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0014\u0010\t\u001a\u00020\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0014\u0010\u000b\u001a\u00020\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u0014\u0010\r\u001a\u00020\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R&\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00108\u0016X\u0097\u0004r\u0002\b-¢\u0006\u000e\n\u0000\u0012\u0004\b*\u0010+\u001a\u0004\b,\u0010\u001eR\u0014\u0010\u0012\u001a\u00020\u0013X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/R\u0014\u0010\u0014\u001a\u00020\u0015X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b0\u00101R\u001a\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u0010X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\u001eR\"\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00190\u0005X\u0096\u000e¢\u0006\u0010\n\u0002\u0010!\u001a\u0004\b3\u0010\u001e\"\u0004\b4\u0010 R\u0016\u0010\u001a\u001a\u0004\u0018\u00010\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b5\u0010)R\u001c\u00106\u001a\u0004\u0018\u000107X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u00109\"\u0004\b:\u0010;¨\u0006S"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/impl/FirScriptImpl;", "Lorg/jetbrains/kotlin/fir/declarations/FirScript;", "resolvePhase", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "annotations", "Lorg/jetbrains/kotlin/fir/MutableOrEmptyList;", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "origin", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "attributes", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "declarations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirScriptSymbol;", "parameters", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "receivers", "Lorg/jetbrains/kotlin/fir/declarations/FirScriptReceiverParameter;", "resultPropertyName", "<init>", "(Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;Ljava/util/List;Lorg/jetbrains/kotlin/fir/FirModuleData;Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;Lorg/jetbrains/kotlin/name/Name;Ljava/util/List;Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/fir/symbols/impl/FirScriptSymbol;Ljava/util/List;Ljava/util/List;Lorg/jetbrains/kotlin/name/Name;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getAnnotations-5e3fPpI", "()Ljava/util/List;", "setAnnotations-GqUYU-s", "(Ljava/util/List;)V", "Ljava/util/List;", "getModuleData", "()Lorg/jetbrains/kotlin/fir/FirModuleData;", "getOrigin", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "getAttributes", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;", "getName", "()Lorg/jetbrains/kotlin/name/Name;", "getDeclarations$annotations", "()V", "getDeclarations", "Lorg/jetbrains/kotlin/fir/declarations/DirectDeclarationsAccess;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "getSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirScriptSymbol;", "getParameters", "getReceivers-5e3fPpI", "setReceivers-GqUYU-s", "getResultPropertyName", "controlFlowGraphReference", "Lorg/jetbrains/kotlin/fir/references/FirControlFlowGraphReference;", "getControlFlowGraphReference", "()Lorg/jetbrains/kotlin/fir/references/FirControlFlowGraphReference;", "setControlFlowGraphReference", "(Lorg/jetbrains/kotlin/fir/references/FirControlFlowGraphReference;)V", "acceptChildren", Argument.Delimiters.none, "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)V", "transformChildren", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/declarations/impl/FirScriptImpl;", "transformAnnotations", "transformDeclarations", "transformParameters", "transformReceivers", "replaceAnnotations", "newAnnotations", Argument.Delimiters.none, "replaceControlFlowGraphReference", "newControlFlowGraphReference", "replaceDeclarations", "newDeclarations", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirScriptImpl extends FirScript {
    private List<FirAnnotation> annotations;
    private final FirDeclarationAttributes attributes;
    private FirControlFlowGraphReference controlFlowGraphReference;
    private final List<FirDeclaration> declarations;
    private final FirModuleData moduleData;
    private final Name name;
    private final FirDeclarationOrigin origin;
    private final List<FirProperty> parameters;
    private List<FirScriptReceiverParameter> receivers;
    private final Name resultPropertyName;
    private final KtSourceElement source;
    private final FirScriptSymbol symbol;

    private FirScriptImpl(FirResolvePhase firResolvePhase, List<FirAnnotation> list, FirModuleData firModuleData, FirDeclarationOrigin firDeclarationOrigin, FirDeclarationAttributes firDeclarationAttributes, Name name, List<FirDeclaration> list2, KtSourceElement ktSourceElement, FirScriptSymbol firScriptSymbol, List<FirProperty> list3, List<FirScriptReceiverParameter> list4, Name name2) {
        firResolvePhase.getClass();
        firModuleData.getClass();
        firDeclarationOrigin.getClass();
        firDeclarationAttributes.getClass();
        name.getClass();
        list2.getClass();
        ktSourceElement.getClass();
        firScriptSymbol.getClass();
        list3.getClass();
        this.annotations = list;
        this.moduleData = firModuleData;
        this.origin = firDeclarationOrigin;
        this.attributes = firDeclarationAttributes;
        this.name = name;
        this.declarations = list2;
        this.source = ktSourceElement;
        this.symbol = firScriptSymbol;
        this.parameters = list3;
        this.receivers = list4;
        this.resultPropertyName = name2;
        getSymbol().bind(this);
        setResolveState(FirResolveStateKt.asResolveState(firResolvePhase));
    }

    @DirectDeclarationsAccess
    public static /* synthetic */ void getDeclarations$annotations() {
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <R, D> void acceptChildren(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        Iterator<T> it = MutableOrEmptyList.m194boximpl(m354getAnnotations5e3fPpI()).iterator();
        while (it.hasNext()) {
            ((FirAnnotation) it.next()).accept(visitor, data);
        }
        FirControlFlowGraphReference controlFlowGraphReference = getControlFlowGraphReference();
        if (controlFlowGraphReference != null) {
            controlFlowGraphReference.accept(visitor, data);
        }
        Iterator<T> it2 = getDeclarations().iterator();
        while (it2.hasNext()) {
            ((FirDeclaration) it2.next()).accept(visitor, data);
        }
        Iterator<T> it3 = getParameters().iterator();
        while (it3.hasNext()) {
            ((FirProperty) it3.next()).accept(visitor, data);
        }
        Iterator<T> it4 = MutableOrEmptyList.m194boximpl(m355getReceivers5e3fPpI()).iterator();
        while (it4.hasNext()) {
            ((FirScriptReceiverParameter) it4.next()).accept(visitor, data);
        }
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirScript, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ List getAnnotations() {
        return MutableOrEmptyList.m194boximpl(m354getAnnotations5e3fPpI());
    }

    /* JADX INFO: renamed from: getAnnotations-5e3fPpI, reason: not valid java name */
    public List<FirAnnotation> m354getAnnotations5e3fPpI() {
        return this.annotations;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirScript, org.jetbrains.kotlin.fir.declarations.FirDeclaration
    public FirDeclarationAttributes getAttributes() {
        return this.attributes;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirScript, org.jetbrains.kotlin.fir.declarations.FirControlFlowGraphOwner
    public FirControlFlowGraphReference getControlFlowGraphReference() {
        return this.controlFlowGraphReference;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirScript
    public List<FirDeclaration> getDeclarations() {
        return this.declarations;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirScript, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirElementWithResolveState
    public FirModuleData getModuleData() {
        return this.moduleData;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirScript
    public Name getName() {
        return this.name;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirScript, org.jetbrains.kotlin.fir.declarations.FirDeclaration
    public FirDeclarationOrigin getOrigin() {
        return this.origin;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirScript
    public List<FirProperty> getParameters() {
        return this.parameters;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirScript
    public /* bridge */ /* synthetic */ List getReceivers() {
        return MutableOrEmptyList.m194boximpl(m355getReceivers5e3fPpI());
    }

    /* JADX INFO: renamed from: getReceivers-5e3fPpI, reason: not valid java name */
    public List<FirScriptReceiverParameter> m355getReceivers5e3fPpI() {
        return this.receivers;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirScript
    public Name getResultPropertyName() {
        return this.resultPropertyName;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirScript, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirElementWithResolveState, org.jetbrains.kotlin.fir.FirElement
    public KtSourceElement getSource() {
        return this.source;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirScript, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public void replaceAnnotations(List<? extends FirAnnotation> newAnnotations) {
        newAnnotations.getClass();
        m356setAnnotationsGqUYUs(FirBuilderDslKt.toMutableOrEmptyForImmutable(newAnnotations));
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirScript, org.jetbrains.kotlin.fir.declarations.FirControlFlowGraphOwner
    public void replaceControlFlowGraphReference(FirControlFlowGraphReference newControlFlowGraphReference) {
        setControlFlowGraphReference(newControlFlowGraphReference);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirScript
    public void replaceDeclarations(List<? extends FirDeclaration> newDeclarations) {
        newDeclarations.getClass();
        if (getDeclarations() == newDeclarations) {
            return;
        }
        getDeclarations().clear();
        getDeclarations().addAll(newDeclarations);
    }

    /* JADX INFO: renamed from: setAnnotations-GqUYU-s, reason: not valid java name */
    public void m356setAnnotationsGqUYUs(List<FirAnnotation> list) {
        this.annotations = list;
    }

    public void setControlFlowGraphReference(FirControlFlowGraphReference firControlFlowGraphReference) {
        this.controlFlowGraphReference = firControlFlowGraphReference;
    }

    /* JADX INFO: renamed from: setReceivers-GqUYU-s, reason: not valid java name */
    public void m357setReceiversGqUYUs(List<FirScriptReceiverParameter> list) {
        this.receivers = list;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirScript, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public <D> FirScriptImpl transformAnnotations(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirTransformerUtilKt.m709transformInplaceaLnlfrU(m354getAnnotations5e3fPpI(), transformer, data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <D> FirScriptImpl transformChildren(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        transformAnnotations((FirTransformer) transformer, (Object) data);
        FirControlFlowGraphReference controlFlowGraphReference = getControlFlowGraphReference();
        setControlFlowGraphReference(controlFlowGraphReference != null ? (FirControlFlowGraphReference) controlFlowGraphReference.transform(transformer, data) : null);
        transformDeclarations((FirTransformer) transformer, (Object) data);
        transformParameters((FirTransformer) transformer, (Object) data);
        transformReceivers((FirTransformer) transformer, (Object) data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirScript
    public <D> FirScriptImpl transformDeclarations(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirTransformerUtilKt.transformInplace(getDeclarations(), transformer, data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirScript
    public <D> FirScriptImpl transformParameters(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirTransformerUtilKt.transformInplace(getParameters(), transformer, data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirScript
    public <D> FirScriptImpl transformReceivers(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirTransformerUtilKt.m709transformInplaceaLnlfrU(m355getReceivers5e3fPpI(), transformer, data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirScript, org.jetbrains.kotlin.fir.declarations.FirDeclaration
    public FirScriptSymbol getSymbol() {
        return this.symbol;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirScript, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirDeclaration transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirScript
    public /* bridge */ /* synthetic */ FirScript transformDeclarations(FirTransformer firTransformer, Object obj) {
        return transformDeclarations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirScript
    public /* bridge */ /* synthetic */ FirScript transformParameters(FirTransformer firTransformer, Object obj) {
        return transformParameters((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirScript
    public /* bridge */ /* synthetic */ FirScript transformReceivers(FirTransformer firTransformer, Object obj) {
        return transformReceivers((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirScript, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirScript transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirScript, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirAnnotationContainer transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public /* bridge */ /* synthetic */ FirElement transformChildren(FirTransformer firTransformer, Object obj) {
        return transformChildren((FirTransformer<? super Object>) firTransformer, obj);
    }

    public /* synthetic */ FirScriptImpl(FirResolvePhase firResolvePhase, List list, FirModuleData firModuleData, FirDeclarationOrigin firDeclarationOrigin, FirDeclarationAttributes firDeclarationAttributes, Name name, List list2, KtSourceElement ktSourceElement, FirScriptSymbol firScriptSymbol, List list3, List list4, Name name2, DefaultConstructorMarker defaultConstructorMarker) {
        this(firResolvePhase, list, firModuleData, firDeclarationOrigin, firDeclarationAttributes, name, list2, ktSourceElement, firScriptSymbol, list3, list4, name2);
    }
}
