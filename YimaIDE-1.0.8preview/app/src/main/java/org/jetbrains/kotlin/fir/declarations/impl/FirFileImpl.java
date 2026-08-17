package org.jetbrains.kotlin.fir.declarations.impl;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceFile;
import org.jetbrains.kotlin.KtSourceFileLinesMapping;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirPackageDirective;
import org.jetbrains.kotlin.fir.MutableOrEmptyList;
import org.jetbrains.kotlin.fir.builder.FirBuilderDslKt;
import org.jetbrains.kotlin.fir.declarations.DirectDeclarationsAccess;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationAttributes;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirImport;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirResolveStateKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.references.FirControlFlowGraphReference;
import org.jetbrains.kotlin.fir.symbols.impl.FirFileSymbol;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirTransformerUtilKt;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u0087\u0001\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012\u0012\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u0012\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019\u0012\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b\u0012\u0006\u0010\u001c\u001a\u00020\u001d¢\u0006\u0004\b\u001e\u0010\u001fJ5\u0010D\u001a\u00020E\"\u0004\b\u0000\u0010F\"\u0004\b\u0001\u0010G2\u0012\u0010H\u001a\u000e\u0012\u0004\u0012\u0002HF\u0012\u0004\u0012\u0002HG0I2\u0006\u0010J\u001a\u0002HGH\u0016¢\u0006\u0002\u0010KJ)\u0010L\u001a\u00020\u0000\"\u0004\b\u0000\u0010G2\f\u0010M\u001a\b\u0012\u0004\u0012\u0002HG0N2\u0006\u0010J\u001a\u0002HGH\u0016¢\u0006\u0002\u0010OJ)\u0010P\u001a\u00020\u0000\"\u0004\b\u0000\u0010G2\f\u0010M\u001a\b\u0012\u0004\u0012\u0002HG0N2\u0006\u0010J\u001a\u0002HGH\u0016¢\u0006\u0002\u0010OJ)\u0010Q\u001a\u00020\u0000\"\u0004\b\u0000\u0010G2\f\u0010M\u001a\b\u0012\u0004\u0012\u0002HG0N2\u0006\u0010J\u001a\u0002HGH\u0016¢\u0006\u0002\u0010OJ)\u0010R\u001a\u00020\u0000\"\u0004\b\u0000\u0010G2\f\u0010M\u001a\b\u0012\u0004\u0012\u0002HG0N2\u0006\u0010J\u001a\u0002HGH\u0016¢\u0006\u0002\u0010OJ\u0016\u0010S\u001a\u00020E2\f\u0010T\u001a\b\u0012\u0004\u0012\u00020\b0UH\u0016J\u0012\u0010V\u001a\u00020E2\b\u0010W\u001a\u0004\u0018\u00010?H\u0016J\u0016\u0010X\u001a\u00020E2\f\u0010Y\u001a\b\u0012\u0004\u0012\u00020\u00150UH\u0016R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\"\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0096\u000e¢\u0006\u0010\n\u0002\u0010&\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u0014\u0010\t\u001a\u00020\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0014\u0010\u000b\u001a\u00020\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0014\u0010\r\u001a\u00020\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u001a\u0010\u000f\u001a\u00020\u0010X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u001a\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b1\u0010#R&\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u00128\u0016X\u0097\u0004r\u0002\b5¢\u0006\u000e\n\u0000\u0012\u0004\b2\u00103\u001a\u0004\b4\u0010#R\u0014\u0010\u0016\u001a\u00020\u0017X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b6\u00107R\u0016\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b8\u00109R\u0016\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b:\u0010;R\u0014\u0010\u001c\u001a\u00020\u001dX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b<\u0010=R\u001c\u0010>\u001a\u0004\u0018\u00010?X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010A\"\u0004\bB\u0010C¨\u0006Z"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/impl/FirFileImpl;", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "resolvePhase", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "annotations", "Lorg/jetbrains/kotlin/fir/MutableOrEmptyList;", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "origin", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "attributes", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;", "packageDirective", "Lorg/jetbrains/kotlin/fir/FirPackageDirective;", "imports", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirImport;", "declarations", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", ModuleXmlParser.NAME, Argument.Delimiters.none, "sourceFile", "Lorg/jetbrains/kotlin/KtSourceFile;", "sourceFileLinesMapping", "Lorg/jetbrains/kotlin/KtSourceFileLinesMapping;", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFileSymbol;", "<init>", "(Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;Ljava/util/List;Lorg/jetbrains/kotlin/fir/FirModuleData;Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;Lorg/jetbrains/kotlin/fir/FirPackageDirective;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Lorg/jetbrains/kotlin/KtSourceFile;Lorg/jetbrains/kotlin/KtSourceFileLinesMapping;Lorg/jetbrains/kotlin/fir/symbols/impl/FirFileSymbol;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "getAnnotations-5e3fPpI", "()Ljava/util/List;", "setAnnotations-GqUYU-s", "(Ljava/util/List;)V", "Ljava/util/List;", "getModuleData", "()Lorg/jetbrains/kotlin/fir/FirModuleData;", "getOrigin", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "getAttributes", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;", "getPackageDirective", "()Lorg/jetbrains/kotlin/fir/FirPackageDirective;", "setPackageDirective", "(Lorg/jetbrains/kotlin/fir/FirPackageDirective;)V", "getImports", "getDeclarations$annotations", "()V", "getDeclarations", "Lorg/jetbrains/kotlin/fir/declarations/DirectDeclarationsAccess;", "getName", "()Ljava/lang/String;", "getSourceFile", "()Lorg/jetbrains/kotlin/KtSourceFile;", "getSourceFileLinesMapping", "()Lorg/jetbrains/kotlin/KtSourceFileLinesMapping;", "getSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirFileSymbol;", "controlFlowGraphReference", "Lorg/jetbrains/kotlin/fir/references/FirControlFlowGraphReference;", "getControlFlowGraphReference", "()Lorg/jetbrains/kotlin/fir/references/FirControlFlowGraphReference;", "setControlFlowGraphReference", "(Lorg/jetbrains/kotlin/fir/references/FirControlFlowGraphReference;)V", "acceptChildren", Argument.Delimiters.none, "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)V", "transformChildren", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/declarations/impl/FirFileImpl;", "transformAnnotations", "transformImports", "transformDeclarations", "replaceAnnotations", "newAnnotations", Argument.Delimiters.none, "replaceControlFlowGraphReference", "newControlFlowGraphReference", "replaceDeclarations", "newDeclarations", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirFileImpl extends FirFile {
    private List<FirAnnotation> annotations;
    private final FirDeclarationAttributes attributes;
    private FirControlFlowGraphReference controlFlowGraphReference;
    private final List<FirDeclaration> declarations;
    private final List<FirImport> imports;
    private final FirModuleData moduleData;
    private final String name;
    private final FirDeclarationOrigin origin;
    private FirPackageDirective packageDirective;
    private final KtSourceElement source;
    private final KtSourceFile sourceFile;
    private final KtSourceFileLinesMapping sourceFileLinesMapping;
    private final FirFileSymbol symbol;

    private FirFileImpl(KtSourceElement ktSourceElement, FirResolvePhase firResolvePhase, List<FirAnnotation> list, FirModuleData firModuleData, FirDeclarationOrigin firDeclarationOrigin, FirDeclarationAttributes firDeclarationAttributes, FirPackageDirective firPackageDirective, List<FirImport> list2, List<FirDeclaration> list3, String str, KtSourceFile ktSourceFile, KtSourceFileLinesMapping ktSourceFileLinesMapping, FirFileSymbol firFileSymbol) {
        firResolvePhase.getClass();
        firModuleData.getClass();
        firDeclarationOrigin.getClass();
        firDeclarationAttributes.getClass();
        firPackageDirective.getClass();
        list2.getClass();
        list3.getClass();
        str.getClass();
        firFileSymbol.getClass();
        this.source = ktSourceElement;
        this.annotations = list;
        this.moduleData = firModuleData;
        this.origin = firDeclarationOrigin;
        this.attributes = firDeclarationAttributes;
        this.packageDirective = firPackageDirective;
        this.imports = list2;
        this.declarations = list3;
        this.name = str;
        this.sourceFile = ktSourceFile;
        this.sourceFileLinesMapping = ktSourceFileLinesMapping;
        this.symbol = firFileSymbol;
        getSymbol().bind(this);
        setResolveState(FirResolveStateKt.asResolveState(firResolvePhase));
        if (getSource() == null && Intrinsics.areEqual(getOrigin(), FirDeclarationOrigin.Source.INSTANCE)) {
            z1f.a(Reflection.getOrCreateKotlinClass(FirFileImpl.class).getSimpleName(), " with Source origin was instantiated without a source element.");
            throw null;
        }
    }

    @DirectDeclarationsAccess
    public static /* synthetic */ void getDeclarations$annotations() {
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <R, D> void acceptChildren(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        Iterator<T> it = MutableOrEmptyList.m194boximpl(m328getAnnotations5e3fPpI()).iterator();
        while (it.hasNext()) {
            ((FirAnnotation) it.next()).accept(visitor, data);
        }
        FirControlFlowGraphReference controlFlowGraphReference = getControlFlowGraphReference();
        if (controlFlowGraphReference != null) {
            controlFlowGraphReference.accept(visitor, data);
        }
        getPackageDirective().accept(visitor, data);
        Iterator<T> it2 = getImports().iterator();
        while (it2.hasNext()) {
            ((FirImport) it2.next()).accept(visitor, data);
        }
        Iterator<T> it3 = getDeclarations().iterator();
        while (it3.hasNext()) {
            ((FirDeclaration) it3.next()).accept(visitor, data);
        }
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirFile, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ List getAnnotations() {
        return MutableOrEmptyList.m194boximpl(m328getAnnotations5e3fPpI());
    }

    /* JADX INFO: renamed from: getAnnotations-5e3fPpI, reason: not valid java name */
    public List<FirAnnotation> m328getAnnotations5e3fPpI() {
        return this.annotations;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirFile, org.jetbrains.kotlin.fir.declarations.FirDeclaration
    public FirDeclarationAttributes getAttributes() {
        return this.attributes;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirFile, org.jetbrains.kotlin.fir.declarations.FirControlFlowGraphOwner
    public FirControlFlowGraphReference getControlFlowGraphReference() {
        return this.controlFlowGraphReference;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirFile
    public List<FirDeclaration> getDeclarations() {
        return this.declarations;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirFile
    public List<FirImport> getImports() {
        return this.imports;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirFile, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirElementWithResolveState
    public FirModuleData getModuleData() {
        return this.moduleData;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirFile
    public String getName() {
        return this.name;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirFile, org.jetbrains.kotlin.fir.declarations.FirDeclaration
    public FirDeclarationOrigin getOrigin() {
        return this.origin;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirFile
    public FirPackageDirective getPackageDirective() {
        return this.packageDirective;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirFile, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirElementWithResolveState, org.jetbrains.kotlin.fir.FirElement
    public KtSourceElement getSource() {
        return this.source;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirFile
    public KtSourceFile getSourceFile() {
        return this.sourceFile;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirFile
    public KtSourceFileLinesMapping getSourceFileLinesMapping() {
        return this.sourceFileLinesMapping;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirFile, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public void replaceAnnotations(List<? extends FirAnnotation> newAnnotations) {
        newAnnotations.getClass();
        m329setAnnotationsGqUYUs(FirBuilderDslKt.toMutableOrEmptyForImmutable(newAnnotations));
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirFile, org.jetbrains.kotlin.fir.declarations.FirControlFlowGraphOwner
    public void replaceControlFlowGraphReference(FirControlFlowGraphReference newControlFlowGraphReference) {
        setControlFlowGraphReference(newControlFlowGraphReference);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirFile
    public void replaceDeclarations(List<? extends FirDeclaration> newDeclarations) {
        newDeclarations.getClass();
        if (getDeclarations() == newDeclarations) {
            return;
        }
        getDeclarations().clear();
        getDeclarations().addAll(newDeclarations);
    }

    /* JADX INFO: renamed from: setAnnotations-GqUYU-s, reason: not valid java name */
    public void m329setAnnotationsGqUYUs(List<FirAnnotation> list) {
        this.annotations = list;
    }

    public void setControlFlowGraphReference(FirControlFlowGraphReference firControlFlowGraphReference) {
        this.controlFlowGraphReference = firControlFlowGraphReference;
    }

    public void setPackageDirective(FirPackageDirective firPackageDirective) {
        firPackageDirective.getClass();
        this.packageDirective = firPackageDirective;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirFile, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public <D> FirFileImpl transformAnnotations(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirTransformerUtilKt.m709transformInplaceaLnlfrU(m328getAnnotations5e3fPpI(), transformer, data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <D> FirFileImpl transformChildren(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        transformAnnotations((FirTransformer) transformer, (Object) data);
        FirControlFlowGraphReference controlFlowGraphReference = getControlFlowGraphReference();
        setControlFlowGraphReference(controlFlowGraphReference != null ? (FirControlFlowGraphReference) controlFlowGraphReference.transform(transformer, data) : null);
        setPackageDirective((FirPackageDirective) getPackageDirective().transform(transformer, data));
        transformImports((FirTransformer) transformer, (Object) data);
        transformDeclarations((FirTransformer) transformer, (Object) data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirFile
    public <D> FirFileImpl transformDeclarations(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirTransformerUtilKt.transformInplace(getDeclarations(), transformer, data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirFile
    public <D> FirFileImpl transformImports(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirTransformerUtilKt.transformInplace(getImports(), transformer, data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirFile, org.jetbrains.kotlin.fir.declarations.FirDeclaration
    public FirFileSymbol getSymbol() {
        return this.symbol;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirFile, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirDeclaration transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirFile
    public /* bridge */ /* synthetic */ FirFile transformDeclarations(FirTransformer firTransformer, Object obj) {
        return transformDeclarations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirFile
    public /* bridge */ /* synthetic */ FirFile transformImports(FirTransformer firTransformer, Object obj) {
        return transformImports((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirFile, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirFile transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirFile, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirAnnotationContainer transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public /* bridge */ /* synthetic */ FirElement transformChildren(FirTransformer firTransformer, Object obj) {
        return transformChildren((FirTransformer<? super Object>) firTransformer, obj);
    }

    public /* synthetic */ FirFileImpl(KtSourceElement ktSourceElement, FirResolvePhase firResolvePhase, List list, FirModuleData firModuleData, FirDeclarationOrigin firDeclarationOrigin, FirDeclarationAttributes firDeclarationAttributes, FirPackageDirective firPackageDirective, List list2, List list3, String str, KtSourceFile ktSourceFile, KtSourceFileLinesMapping ktSourceFileLinesMapping, FirFileSymbol firFileSymbol, DefaultConstructorMarker defaultConstructorMarker) {
        this(ktSourceElement, firResolvePhase, list, firModuleData, firDeclarationOrigin, firDeclarationAttributes, firPackageDirective, list2, list3, str, ktSourceFile, ktSourceFileLinesMapping, firFileSymbol);
    }
}
