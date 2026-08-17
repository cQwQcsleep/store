package org.jetbrains.kotlin.fir.declarations.builder;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.properties.Delegates;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder;
import org.jetbrains.kotlin.fir.builder.FirBuilderDsl;
import org.jetbrains.kotlin.fir.builder.FirBuilderDslKt;
import org.jetbrains.kotlin.fir.declarations.DeprecationsProvider;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationAttributes;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirPropertyBodyResolveState;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.UnresolvedDeprecationProvider;
import org.jetbrains.kotlin.fir.declarations.impl.FirPropertyImpl;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.symbols.impl.FirDelegateFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerSource;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@FirBuilderDsl
@Metadata(d1 = {"\u0000Ò\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\u008e\u0001\u001a\u00030\u008f\u0001H\u0016R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X\u0096.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0019X\u0096.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\u00020\u001fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001a\u0010$\u001a\u00020%X\u0096.¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R+\u0010,\u001a\u00020+2\u0006\u0010*\u001a\u00020+8V@VX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b0\u00101\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u001a\u00102\u001a\u000203X\u0096.¢\u0006\u000e\n\u0000\u001a\u0004\b4\u00105\"\u0004\b6\u00107R\u001c\u00108\u001a\u0004\u0018\u000109X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R\u001a\u0010>\u001a\u00020?X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010A\"\u0004\bB\u0010CR\u001c\u0010D\u001a\u0004\u0018\u00010EX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010G\"\u0004\bH\u0010IR\u001c\u0010J\u001a\u0004\u0018\u00010KX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010M\"\u0004\bN\u0010OR\u001a\u0010P\u001a\b\u0012\u0004\u0012\u00020R0QX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bS\u0010TR\u001a\u0010U\u001a\u00020VX\u0096.¢\u0006\u000e\n\u0000\u001a\u0004\bW\u0010X\"\u0004\bY\u0010ZR\u001c\u0010[\u001a\u0004\u0018\u00010\\X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b]\u0010^\"\u0004\b_\u0010`R\u001c\u0010a\u001a\u0004\u0018\u00010\\X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bb\u0010^\"\u0004\bc\u0010`R+\u0010d\u001a\u00020+2\u0006\u0010*\u001a\u00020+8V@VX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\bf\u00101\u001a\u0004\bd\u0010-\"\u0004\be\u0010/R\u001c\u0010g\u001a\u0004\u0018\u00010hX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bi\u0010j\"\u0004\bk\u0010lR\u001c\u0010m\u001a\u0004\u0018\u00010hX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bn\u0010j\"\u0004\bo\u0010lR\u001c\u0010p\u001a\u0004\u0018\u00010qX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\br\u0010s\"\u0004\bt\u0010uR\u001a\u0010v\u001a\b\u0012\u0004\u0012\u00020w0QX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bx\u0010TR\u001a\u0010y\u001a\u00020zX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b{\u0010|\"\u0004\b}\u0010~R!\u0010\u007f\u001a\u0005\u0018\u00010\u0080\u0001X\u0086\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u0081\u0001\u0010\u0082\u0001\"\u0006\b\u0083\u0001\u0010\u0084\u0001R \u0010\u0085\u0001\u001a\u00030\u0086\u0001X\u0086\u000e¢\u0006\u0012\n\u0000\u001a\u0006\b\u0087\u0001\u0010\u0088\u0001\"\u0006\b\u0089\u0001\u0010\u008a\u0001R\u001d\u0010\u008b\u0001\u001a\t\u0012\u0005\u0012\u00030\u008c\u00010QX\u0096\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u008d\u0001\u0010TÊ\u0001\u0003\b\u0091\u0001¨\u0006\u0090\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/builder/FirPropertyBuilder;", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirVariableBuilder;", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirTypeParametersOwnerBuilder;", "Lorg/jetbrains/kotlin/fir/builder/FirAnnotationContainerBuilder;", "<init>", "()V", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "setSource", "(Lorg/jetbrains/kotlin/KtSourceElement;)V", "resolvePhase", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "getResolvePhase", "()Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "setResolvePhase", "(Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;)V", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "getModuleData", "()Lorg/jetbrains/kotlin/fir/FirModuleData;", "setModuleData", "(Lorg/jetbrains/kotlin/fir/FirModuleData;)V", "origin", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "getOrigin", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "setOrigin", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;)V", "attributes", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;", "getAttributes", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;", "setAttributes", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;)V", "status", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;", "getStatus", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;", "setStatus", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;)V", "<set-?>", Argument.Delimiters.none, "isLocal", "()Z", "setLocal", "(Z)V", "isLocal$delegate", "Lkotlin/properties/ReadWriteProperty;", "returnTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "getReturnTypeRef", "()Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "setReturnTypeRef", "(Lorg/jetbrains/kotlin/fir/types/FirTypeRef;)V", "receiverParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirReceiverParameter;", "getReceiverParameter", "()Lorg/jetbrains/kotlin/fir/declarations/FirReceiverParameter;", "setReceiverParameter", "(Lorg/jetbrains/kotlin/fir/declarations/FirReceiverParameter;)V", "deprecationsProvider", "Lorg/jetbrains/kotlin/fir/declarations/DeprecationsProvider;", "getDeprecationsProvider", "()Lorg/jetbrains/kotlin/fir/declarations/DeprecationsProvider;", "setDeprecationsProvider", "(Lorg/jetbrains/kotlin/fir/declarations/DeprecationsProvider;)V", "containerSource", "Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerSource;", "getContainerSource", "()Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerSource;", "setContainerSource", "(Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerSource;)V", "dispatchReceiverType", "Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;", "getDispatchReceiverType", "()Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;", "setDispatchReceiverType", "(Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;)V", "contextParameters", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "getContextParameters", "()Ljava/util/List;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "getName", "()Lorg/jetbrains/kotlin/name/Name;", "setName", "(Lorg/jetbrains/kotlin/name/Name;)V", "initializer", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "getInitializer", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "setInitializer", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)V", "delegate", "getDelegate", "setDelegate", "isVar", "setVar", "isVar$delegate", "getter", "Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;", "getGetter", "()Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;", "setGetter", "(Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;)V", "setter", "getSetter", "setSetter", "backingField", "Lorg/jetbrains/kotlin/fir/declarations/FirBackingField;", "getBackingField", "()Lorg/jetbrains/kotlin/fir/declarations/FirBackingField;", "setBackingField", "(Lorg/jetbrains/kotlin/fir/declarations/FirBackingField;)V", "annotations", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "getAnnotations", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "getSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "setSymbol", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;)V", "delegateFieldSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirDelegateFieldSymbol;", "getDelegateFieldSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirDelegateFieldSymbol;", "setDelegateFieldSymbol", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirDelegateFieldSymbol;)V", "bodyResolveState", "Lorg/jetbrains/kotlin/fir/declarations/FirPropertyBodyResolveState;", "getBodyResolveState", "()Lorg/jetbrains/kotlin/fir/declarations/FirPropertyBodyResolveState;", "setBodyResolveState", "(Lorg/jetbrains/kotlin/fir/declarations/FirPropertyBodyResolveState;)V", "typeParameters", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;", "getTypeParameters", "build", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "org.jetbrains.kotlin:tree", "Lorg/jetbrains/kotlin/fir/builder/FirBuilderDsl;"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirPropertyBuilder implements FirAnnotationContainerBuilder, FirTypeParametersOwnerBuilder, FirVariableBuilder {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new MutablePropertyReference1Impl<>(FirPropertyBuilder.class, "isLocal", "isLocal()Z", 0), new MutablePropertyReference1Impl<>(FirPropertyBuilder.class, "isVar", "isVar()Z", 0)};
    private final List<FirAnnotation> annotations;
    private FirBackingField backingField;
    private FirPropertyBodyResolveState bodyResolveState;
    private DeserializedContainerSource containerSource;
    private final List<FirValueParameter> contextParameters;
    private FirExpression delegate;
    private FirDelegateFieldSymbol delegateFieldSymbol;
    private DeprecationsProvider deprecationsProvider;
    private ConeSimpleKotlinType dispatchReceiverType;
    private FirPropertyAccessor getter;
    private FirExpression initializer;

    /* JADX INFO: renamed from: isLocal$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty isLocal;

    /* JADX INFO: renamed from: isVar$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty isVar;
    public FirModuleData moduleData;
    public Name name;
    public FirDeclarationOrigin origin;
    private FirReceiverParameter receiverParameter;
    public FirTypeRef returnTypeRef;
    private FirPropertyAccessor setter;
    private KtSourceElement source;
    public FirDeclarationStatus status;
    public FirPropertySymbol symbol;
    private final List<FirTypeParameter> typeParameters;
    private FirResolvePhase resolvePhase = FirResolvePhase.RAW_FIR;
    private FirDeclarationAttributes attributes = new FirDeclarationAttributes();

    public FirPropertyBuilder() {
        Delegates delegates = Delegates.INSTANCE;
        this.isLocal = delegates.notNull();
        this.deprecationsProvider = UnresolvedDeprecationProvider.INSTANCE;
        this.contextParameters = new ArrayList();
        this.isVar = delegates.notNull();
        this.annotations = new ArrayList();
        this.bodyResolveState = FirPropertyBodyResolveState.NOTHING_RESOLVED;
        this.typeParameters = new ArrayList();
    }

    @Override // org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder
    /* JADX INFO: renamed from: build */
    public FirProperty mo288build() {
        return new FirPropertyImpl(getSource(), getResolvePhase(), getModuleData(), getOrigin(), getAttributes(), getStatus(), isLocal(), getReturnTypeRef(), getReceiverParameter(), getDeprecationsProvider(), getContainerSource(), getDispatchReceiverType(), FirBuilderDslKt.toMutableOrEmpty(getContextParameters()), getName(), getInitializer(), getDelegate(), isVar(), getGetter(), getSetter(), getBackingField(), FirBuilderDslKt.toMutableOrEmpty(getAnnotations()), getSymbol(), this.delegateFieldSymbol, this.bodyResolveState, getTypeParameters(), null);
    }

    @Override // org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public List<FirAnnotation> getAnnotations() {
        return this.annotations;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirVariableBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public FirDeclarationAttributes getAttributes() {
        return this.attributes;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirVariableBuilder
    public FirBackingField getBackingField() {
        return this.backingField;
    }

    public final FirPropertyBodyResolveState getBodyResolveState() {
        return this.bodyResolveState;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirVariableBuilder
    public DeserializedContainerSource getContainerSource() {
        return this.containerSource;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirVariableBuilder
    public List<FirValueParameter> getContextParameters() {
        return this.contextParameters;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirVariableBuilder
    public FirExpression getDelegate() {
        return this.delegate;
    }

    public final FirDelegateFieldSymbol getDelegateFieldSymbol() {
        return this.delegateFieldSymbol;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirVariableBuilder
    public DeprecationsProvider getDeprecationsProvider() {
        return this.deprecationsProvider;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirVariableBuilder
    public ConeSimpleKotlinType getDispatchReceiverType() {
        return this.dispatchReceiverType;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirVariableBuilder
    public FirPropertyAccessor getGetter() {
        return this.getter;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirVariableBuilder
    public FirExpression getInitializer() {
        return this.initializer;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirVariableBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public FirModuleData getModuleData() throws UninitializedPropertyAccessException {
        FirModuleData firModuleData = this.moduleData;
        if (firModuleData != null) {
            return firModuleData;
        }
        Intrinsics.throwUninitializedPropertyAccessException("moduleData");
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirVariableBuilder
    public Name getName() throws UninitializedPropertyAccessException {
        Name name = this.name;
        if (name != null) {
            return name;
        }
        Intrinsics.throwUninitializedPropertyAccessException(ModuleXmlParser.NAME);
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirVariableBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public FirDeclarationOrigin getOrigin() throws UninitializedPropertyAccessException {
        FirDeclarationOrigin firDeclarationOrigin = this.origin;
        if (firDeclarationOrigin != null) {
            return firDeclarationOrigin;
        }
        Intrinsics.throwUninitializedPropertyAccessException("origin");
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirVariableBuilder
    public FirReceiverParameter getReceiverParameter() {
        return this.receiverParameter;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirVariableBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public FirResolvePhase getResolvePhase() {
        return this.resolvePhase;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirVariableBuilder
    public FirTypeRef getReturnTypeRef() {
        FirTypeRef firTypeRef = this.returnTypeRef;
        if (firTypeRef != null) {
            return firTypeRef;
        }
        Intrinsics.throwUninitializedPropertyAccessException("returnTypeRef");
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirVariableBuilder
    public FirPropertyAccessor getSetter() {
        return this.setter;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirTypeParametersOwnerBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirVariableBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public KtSourceElement getSource() {
        return this.source;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirVariableBuilder
    public FirDeclarationStatus getStatus() throws UninitializedPropertyAccessException {
        FirDeclarationStatus firDeclarationStatus = this.status;
        if (firDeclarationStatus != null) {
            return firDeclarationStatus;
        }
        Intrinsics.throwUninitializedPropertyAccessException("status");
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final FirPropertySymbol getSymbol() throws UninitializedPropertyAccessException {
        FirPropertySymbol firPropertySymbol = this.symbol;
        if (firPropertySymbol != null) {
            return firPropertySymbol;
        }
        Intrinsics.throwUninitializedPropertyAccessException("symbol");
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirTypeParametersOwnerBuilder
    public List<FirTypeParameter> getTypeParameters() {
        return this.typeParameters;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirVariableBuilder
    public boolean isLocal() {
        return ((Boolean) this.isLocal.getValue(this, $$delegatedProperties[0])).booleanValue();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirVariableBuilder
    public boolean isVar() {
        return ((Boolean) this.isVar.getValue(this, $$delegatedProperties[1])).booleanValue();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirVariableBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public void setAttributes(FirDeclarationAttributes firDeclarationAttributes) {
        firDeclarationAttributes.getClass();
        this.attributes = firDeclarationAttributes;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirVariableBuilder
    public void setBackingField(FirBackingField firBackingField) {
        this.backingField = firBackingField;
    }

    public final void setBodyResolveState(FirPropertyBodyResolveState firPropertyBodyResolveState) {
        firPropertyBodyResolveState.getClass();
        this.bodyResolveState = firPropertyBodyResolveState;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirVariableBuilder
    public void setContainerSource(DeserializedContainerSource deserializedContainerSource) {
        this.containerSource = deserializedContainerSource;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirVariableBuilder
    public void setDelegate(FirExpression firExpression) {
        this.delegate = firExpression;
    }

    public final void setDelegateFieldSymbol(FirDelegateFieldSymbol firDelegateFieldSymbol) {
        this.delegateFieldSymbol = firDelegateFieldSymbol;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirVariableBuilder
    public void setDeprecationsProvider(DeprecationsProvider deprecationsProvider) {
        deprecationsProvider.getClass();
        this.deprecationsProvider = deprecationsProvider;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirVariableBuilder
    public void setDispatchReceiverType(ConeSimpleKotlinType coneSimpleKotlinType) {
        this.dispatchReceiverType = coneSimpleKotlinType;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirVariableBuilder
    public void setGetter(FirPropertyAccessor firPropertyAccessor) {
        this.getter = firPropertyAccessor;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirVariableBuilder
    public void setInitializer(FirExpression firExpression) {
        this.initializer = firExpression;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirVariableBuilder
    public void setLocal(boolean z) {
        this.isLocal.setValue(this, $$delegatedProperties[0], Boolean.valueOf(z));
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirVariableBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public void setModuleData(FirModuleData firModuleData) {
        firModuleData.getClass();
        this.moduleData = firModuleData;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirVariableBuilder
    public void setName(Name name) {
        name.getClass();
        this.name = name;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirVariableBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public void setOrigin(FirDeclarationOrigin firDeclarationOrigin) {
        firDeclarationOrigin.getClass();
        this.origin = firDeclarationOrigin;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirVariableBuilder
    public void setReceiverParameter(FirReceiverParameter firReceiverParameter) {
        this.receiverParameter = firReceiverParameter;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirVariableBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public void setResolvePhase(FirResolvePhase firResolvePhase) {
        firResolvePhase.getClass();
        this.resolvePhase = firResolvePhase;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirVariableBuilder
    public void setReturnTypeRef(FirTypeRef firTypeRef) {
        firTypeRef.getClass();
        this.returnTypeRef = firTypeRef;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirVariableBuilder
    public void setSetter(FirPropertyAccessor firPropertyAccessor) {
        this.setter = firPropertyAccessor;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirTypeParametersOwnerBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirVariableBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public void setSource(KtSourceElement ktSourceElement) {
        this.source = ktSourceElement;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirVariableBuilder
    public void setStatus(FirDeclarationStatus firDeclarationStatus) {
        firDeclarationStatus.getClass();
        this.status = firDeclarationStatus;
    }

    public final void setSymbol(FirPropertySymbol firPropertySymbol) {
        firPropertySymbol.getClass();
        this.symbol = firPropertySymbol;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirVariableBuilder
    public void setVar(boolean z) {
        this.isVar.setValue(this, $$delegatedProperties[1], Boolean.valueOf(z));
    }
}
