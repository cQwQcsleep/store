package org.jetbrains.kotlin.fir.declarations.builder;

import java.util.ArrayList;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.state.InlineClassManglingUtilsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder;
import org.jetbrains.kotlin.fir.builder.FirBuilderDsl;
import org.jetbrains.kotlin.fir.builder.FirBuilderDslKt;
import org.jetbrains.kotlin.fir.declarations.DeprecationsProvider;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationAttributes;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.UnresolvedDeprecationProvider;
import org.jetbrains.kotlin.fir.declarations.impl.FirRegularClassImpl;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.scopes.FirScopeProvider;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@FirBuilderDsl
@Metadata(d1 = {"\u0000º\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\b\u0017\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\\\u001a\u00020]H\u0016R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X\u0096.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0019X\u0096.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u001fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001a\u0010$\u001a\b\u0012\u0004\u0012\u00020&0%X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u001a\u0010)\u001a\u00020*X\u0096.¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u001a\u0010/\u001a\u000200X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104R\u001a\u00105\u001a\u000206X\u0096.¢\u0006\u000e\n\u0000\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R\u001a\u0010;\u001a\u00020<X\u0096.¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@R\u001a\u0010A\u001a\b\u0012\u0004\u0012\u00020B0%X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bC\u0010(R\u001a\u0010D\u001a\b\u0012\u0004\u0012\u00020E0%X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bF\u0010(R\u001a\u0010G\u001a\u00020HX\u0096.¢\u0006\u000e\n\u0000\u001a\u0004\bI\u0010J\"\u0004\bK\u0010LR\u001a\u0010M\u001a\u00020NX\u0096.¢\u0006\u000e\n\u0000\u001a\u0004\bO\u0010P\"\u0004\bQ\u0010RR\u001c\u0010S\u001a\u0004\u0018\u00010NX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bT\u0010P\"\u0004\bU\u0010RR\u001a\u0010V\u001a\b\u0012\u0004\u0012\u00020W0%X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bX\u0010(R\u001a\u0010Y\u001a\b\u0012\u0004\u0012\u00020Z0%X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b[\u0010(RD\u0010`\u001a\u00020_2\u0006\u0010^\u001a\u00020_8V@VX\u0097\u000er\u0018\be\u0012\b\bf\u0012\u0004\b\b(g\u0012\n\bh\u0012\u0006\b\n0i8j¢\u0006\u0012\u0012\u0004\ba\u0010\u0005\u001a\u0004\b`\u0010b\"\u0004\bc\u0010dÊ\u0001\u0002\bl¨\u0006k"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/builder/FirRegularClassBuilder;", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirClassBuilder;", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirTypeParameterRefsOwnerBuilder;", "Lorg/jetbrains/kotlin/fir/builder/FirAnnotationContainerBuilder;", "<init>", "()V", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "setSource", "(Lorg/jetbrains/kotlin/KtSourceElement;)V", "resolvePhase", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "getResolvePhase", "()Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "setResolvePhase", "(Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;)V", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "getModuleData", "()Lorg/jetbrains/kotlin/fir/FirModuleData;", "setModuleData", "(Lorg/jetbrains/kotlin/fir/FirModuleData;)V", "origin", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "getOrigin", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "setOrigin", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;)V", "attributes", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;", "getAttributes", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;", "setAttributes", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;)V", "typeParameters", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRef;", "getTypeParameters", "()Ljava/util/List;", "status", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;", "getStatus", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;", "setStatus", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;)V", "deprecationsProvider", "Lorg/jetbrains/kotlin/fir/declarations/DeprecationsProvider;", "getDeprecationsProvider", "()Lorg/jetbrains/kotlin/fir/declarations/DeprecationsProvider;", "setDeprecationsProvider", "(Lorg/jetbrains/kotlin/fir/declarations/DeprecationsProvider;)V", "scopeProvider", "Lorg/jetbrains/kotlin/fir/scopes/FirScopeProvider;", "getScopeProvider", "()Lorg/jetbrains/kotlin/fir/scopes/FirScopeProvider;", "setScopeProvider", "(Lorg/jetbrains/kotlin/fir/scopes/FirScopeProvider;)V", "classKind", "Lorg/jetbrains/kotlin/descriptors/ClassKind;", "getClassKind", "()Lorg/jetbrains/kotlin/descriptors/ClassKind;", "setClassKind", "(Lorg/jetbrains/kotlin/descriptors/ClassKind;)V", "declarations", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "getDeclarations", "annotations", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "getAnnotations", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "getName", "()Lorg/jetbrains/kotlin/name/Name;", "setName", "(Lorg/jetbrains/kotlin/name/Name;)V", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "getSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "setSymbol", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;)V", "companionObjectSymbol", "getCompanionObjectSymbol", "setCompanionObjectSymbol", "superTypeRefs", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "getSuperTypeRefs", "contextParameters", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "getContextParameters", "build", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", InlineClassManglingUtilsKt.NOT_INLINE_CLASS_PARAMETER_PLACEHOLDER, Argument.Delimiters.none, "isLocal", "isLocal$annotations", "()Z", "setLocal", "(Z)V", "Lkotlin/Deprecated;", "message", "Modification of 'isLocal' has no impact for FirRegularClassBuilder", "level", "Lkotlin/DeprecationLevel;", "HIDDEN", "org.jetbrains.kotlin:tree", "Lorg/jetbrains/kotlin/fir/builder/FirBuilderDsl;"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class FirRegularClassBuilder implements FirAnnotationContainerBuilder, FirClassBuilder, FirTypeParameterRefsOwnerBuilder {
    public ClassKind classKind;
    private FirRegularClassSymbol companionObjectSymbol;
    public FirModuleData moduleData;
    public Name name;
    public FirDeclarationOrigin origin;
    public FirScopeProvider scopeProvider;
    private KtSourceElement source;
    public FirDeclarationStatus status;
    public FirRegularClassSymbol symbol;
    private FirResolvePhase resolvePhase = FirResolvePhase.RAW_FIR;
    private FirDeclarationAttributes attributes = new FirDeclarationAttributes();
    private final List<FirTypeParameterRef> typeParameters = new ArrayList();
    private DeprecationsProvider deprecationsProvider = UnresolvedDeprecationProvider.INSTANCE;
    private final List<FirDeclaration> declarations = new ArrayList();
    private final List<FirAnnotation> annotations = new ArrayList();
    private final List<FirTypeRef> superTypeRefs = new ArrayList();
    private final List<FirValueParameter> contextParameters = new ArrayList();

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Modification of 'isLocal' has no impact for FirRegularClassBuilder")
    public static /* synthetic */ void isLocal$annotations() {
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirTypeParameterRefsOwnerBuilder
    /* JADX INFO: renamed from: build, reason: merged with bridge method [inline-methods] */
    public FirRegularClass mo289build() {
        return new FirRegularClassImpl(getSource(), getResolvePhase(), getModuleData(), getOrigin(), getAttributes(), getTypeParameters(), getStatus(), getDeprecationsProvider(), getScopeProvider(), getClassKind(), getDeclarations(), FirBuilderDslKt.toMutableOrEmpty(getAnnotations()), getName(), getSymbol(), getCompanionObjectSymbol(), getSuperTypeRefs(), FirBuilderDslKt.toMutableOrEmpty(getContextParameters()), null);
    }

    @Override // org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public List<FirAnnotation> getAnnotations() {
        return this.annotations;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirClassBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public FirDeclarationAttributes getAttributes() {
        return this.attributes;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirClassBuilder
    public ClassKind getClassKind() throws UninitializedPropertyAccessException {
        ClassKind classKind = this.classKind;
        if (classKind != null) {
            return classKind;
        }
        Intrinsics.throwUninitializedPropertyAccessException("classKind");
        return null;
    }

    public FirRegularClassSymbol getCompanionObjectSymbol() {
        return this.companionObjectSymbol;
    }

    public List<FirValueParameter> getContextParameters() {
        return this.contextParameters;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirClassBuilder
    public List<FirDeclaration> getDeclarations() {
        return this.declarations;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirClassBuilder
    public DeprecationsProvider getDeprecationsProvider() {
        return this.deprecationsProvider;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirClassBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public FirModuleData getModuleData() throws UninitializedPropertyAccessException {
        FirModuleData firModuleData = this.moduleData;
        if (firModuleData != null) {
            return firModuleData;
        }
        Intrinsics.throwUninitializedPropertyAccessException("moduleData");
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public Name getName() throws UninitializedPropertyAccessException {
        Name name = this.name;
        if (name != null) {
            return name;
        }
        Intrinsics.throwUninitializedPropertyAccessException(ModuleXmlParser.NAME);
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirClassBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public FirDeclarationOrigin getOrigin() throws UninitializedPropertyAccessException {
        FirDeclarationOrigin firDeclarationOrigin = this.origin;
        if (firDeclarationOrigin != null) {
            return firDeclarationOrigin;
        }
        Intrinsics.throwUninitializedPropertyAccessException("origin");
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirClassBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public FirResolvePhase getResolvePhase() {
        return this.resolvePhase;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirClassBuilder
    public FirScopeProvider getScopeProvider() throws UninitializedPropertyAccessException {
        FirScopeProvider firScopeProvider = this.scopeProvider;
        if (firScopeProvider != null) {
            return firScopeProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("scopeProvider");
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirClassBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public KtSourceElement getSource() {
        return this.source;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirClassBuilder
    public FirDeclarationStatus getStatus() throws UninitializedPropertyAccessException {
        FirDeclarationStatus firDeclarationStatus = this.status;
        if (firDeclarationStatus != null) {
            return firDeclarationStatus;
        }
        Intrinsics.throwUninitializedPropertyAccessException("status");
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirClassBuilder
    public List<FirTypeRef> getSuperTypeRefs() {
        return this.superTypeRefs;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public FirRegularClassSymbol getSymbol() throws UninitializedPropertyAccessException {
        FirRegularClassSymbol firRegularClassSymbol = this.symbol;
        if (firRegularClassSymbol != null) {
            return firRegularClassSymbol;
        }
        Intrinsics.throwUninitializedPropertyAccessException("symbol");
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirClassBuilder
    public List<FirTypeParameterRef> getTypeParameters() {
        return this.typeParameters;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirClassBuilder
    public /* synthetic */ boolean isLocal() {
        throw new IllegalStateException();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirClassBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public void setAttributes(FirDeclarationAttributes firDeclarationAttributes) {
        firDeclarationAttributes.getClass();
        this.attributes = firDeclarationAttributes;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirClassBuilder
    public void setClassKind(ClassKind classKind) {
        classKind.getClass();
        this.classKind = classKind;
    }

    public void setCompanionObjectSymbol(FirRegularClassSymbol firRegularClassSymbol) {
        this.companionObjectSymbol = firRegularClassSymbol;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirClassBuilder
    public void setDeprecationsProvider(DeprecationsProvider deprecationsProvider) {
        deprecationsProvider.getClass();
        this.deprecationsProvider = deprecationsProvider;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirClassBuilder
    public /* synthetic */ void setLocal(boolean z) {
        throw new IllegalStateException();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirClassBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public void setModuleData(FirModuleData firModuleData) {
        firModuleData.getClass();
        this.moduleData = firModuleData;
    }

    public void setName(Name name) {
        name.getClass();
        this.name = name;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirClassBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public void setOrigin(FirDeclarationOrigin firDeclarationOrigin) {
        firDeclarationOrigin.getClass();
        this.origin = firDeclarationOrigin;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirClassBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public void setResolvePhase(FirResolvePhase firResolvePhase) {
        firResolvePhase.getClass();
        this.resolvePhase = firResolvePhase;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirClassBuilder
    public void setScopeProvider(FirScopeProvider firScopeProvider) {
        firScopeProvider.getClass();
        this.scopeProvider = firScopeProvider;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirClassBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public void setSource(KtSourceElement ktSourceElement) {
        this.source = ktSourceElement;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirClassBuilder
    public void setStatus(FirDeclarationStatus firDeclarationStatus) {
        firDeclarationStatus.getClass();
        this.status = firDeclarationStatus;
    }

    public void setSymbol(FirRegularClassSymbol firRegularClassSymbol) {
        firRegularClassSymbol.getClass();
        this.symbol = firRegularClassSymbol;
    }
}
