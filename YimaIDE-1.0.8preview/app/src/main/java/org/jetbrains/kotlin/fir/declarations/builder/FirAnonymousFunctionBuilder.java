package org.jetbrains.kotlin.fir.declarations.builder;

import java.util.ArrayList;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.properties.Delegates;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.codegen.state.InlineClassManglingUtilsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.EventOccurrencesRange;
import org.jetbrains.kotlin.fir.FirLabel;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder;
import org.jetbrains.kotlin.fir.builder.FirBuilderDsl;
import org.jetbrains.kotlin.fir.builder.FirBuilderDslKt;
import org.jetbrains.kotlin.fir.contracts.FirContractDescription;
import org.jetbrains.kotlin.fir.declarations.DeprecationsProvider;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationAttributes;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.InlineStatus;
import org.jetbrains.kotlin.fir.declarations.UnresolvedDeprecationProvider;
import org.jetbrains.kotlin.fir.declarations.impl.FirAnonymousFunctionImpl;
import org.jetbrains.kotlin.fir.declarations.impl.FirResolvedDeclarationStatusImpl;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.references.FirControlFlowGraphReference;
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousFunctionSymbol;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.impl.FirImplicitTypeRefImplWithoutSource;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerSource;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@FirBuilderDsl
@Metadata(d1 = {"\u0000â\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\u0088\u0001\u001a\u00030\u0089\u0001H\u0016R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0018X\u0096.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001d\u001a\u00020\u001eX\u0096.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001a\u0010#\u001a\u00020$X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u001a\u0010)\u001a\u00020*X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u001a\u0010/\u001a\u000200X\u0096.¢\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104R\u001c\u00105\u001a\u0004\u0018\u000106X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R\u001a\u0010;\u001a\u00020<X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@R\u001c\u0010A\u001a\u0004\u0018\u00010BX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010D\"\u0004\bE\u0010FR\u001a\u0010G\u001a\b\u0012\u0004\u0012\u00020H0\u0013X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bI\u0010\u0016R\u001c\u0010J\u001a\u0004\u0018\u00010KX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010M\"\u0004\bN\u0010OR\u001a\u0010P\u001a\b\u0012\u0004\u0012\u00020H0\u0013X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bQ\u0010\u0016R\u001c\u0010R\u001a\u0004\u0018\u00010SX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bT\u0010U\"\u0004\bV\u0010WR\u001c\u0010X\u001a\u0004\u0018\u00010YX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bZ\u0010[\"\u0004\b\\\u0010]R\u001a\u0010^\u001a\u00020_X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b`\u0010a\"\u0004\bb\u0010cR\u001c\u0010d\u001a\u0004\u0018\u00010eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bf\u0010g\"\u0004\bh\u0010iR\u001c\u0010j\u001a\u0004\u0018\u00010kX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bl\u0010m\"\u0004\bn\u0010oR\u001a\u0010p\u001a\u00020qX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\br\u0010s\"\u0004\bt\u0010uR+\u0010x\u001a\u00020w2\u0006\u0010v\u001a\u00020w8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b|\u0010}\u001a\u0004\bx\u0010y\"\u0004\bz\u0010{R-\u0010~\u001a\u00020w2\u0006\u0010v\u001a\u00020w8F@FX\u0086\u008e\u0002¢\u0006\u0014\n\u0005\b\u0081\u0001\u0010}\u001a\u0004\b\u007f\u0010y\"\u0005\b\u0080\u0001\u0010{R\u001d\u0010\u0082\u0001\u001a\t\u0012\u0005\u0012\u00030\u0083\u00010\u0013X\u0096\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u0084\u0001\u0010\u0016R\u001d\u0010\u0085\u0001\u001a\u000200X\u0086\u000e¢\u0006\u0010\n\u0000\u001a\u0005\b\u0086\u0001\u00102\"\u0005\b\u0087\u0001\u00104RO\u0010\u008b\u0001\u001a\u00020w2\u0007\u0010\u008a\u0001\u001a\u00020w8V@VX\u0097\u000er\u001e\b\u008e\u0001\u0012\n\b\u008f\u0001\u0012\u0005\b\b(\u0090\u0001\u0012\r\b\u0091\u0001\u0012\b\b\n0\u0092\u00018\u0093\u0001¢\u0006\u0015\u0012\u0005\b\u008c\u0001\u0010\u0005\u001a\u0005\b\u008b\u0001\u0010y\"\u0005\b\u008d\u0001\u0010{RW\u0010\u0095\u0001\u001a\u0005\u0018\u00010\u0094\u00012\n\u0010\u008a\u0001\u001a\u0005\u0018\u00010\u0094\u00018V@VX\u0097\u000er\u001e\b\u008e\u0001\u0012\n\b\u008f\u0001\u0012\u0005\b\b(\u009b\u0001\u0012\r\b\u0091\u0001\u0012\b\b\n0\u0092\u00018\u0093\u0001¢\u0006\u0017\u0012\u0005\b\u0096\u0001\u0010\u0005\u001a\u0006\b\u0097\u0001\u0010\u0098\u0001\"\u0006\b\u0099\u0001\u0010\u009a\u0001Ê\u0001\u0003\b\u009d\u0001¨\u0006\u009c\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/builder/FirAnonymousFunctionBuilder;", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirFunctionBuilder;", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirTypeParametersOwnerBuilder;", "Lorg/jetbrains/kotlin/fir/builder/FirAnnotationContainerBuilder;", "<init>", "()V", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "setSource", "(Lorg/jetbrains/kotlin/KtSourceElement;)V", "resolvePhase", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "getResolvePhase", "()Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "setResolvePhase", "(Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;)V", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "getAnnotations", "()Ljava/util/List;", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "getModuleData", "()Lorg/jetbrains/kotlin/fir/FirModuleData;", "setModuleData", "(Lorg/jetbrains/kotlin/fir/FirModuleData;)V", "origin", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "getOrigin", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "setOrigin", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;)V", "attributes", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;", "getAttributes", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;", "setAttributes", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;)V", "status", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;", "getStatus", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;", "setStatus", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;)V", "returnTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "getReturnTypeRef", "()Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "setReturnTypeRef", "(Lorg/jetbrains/kotlin/fir/types/FirTypeRef;)V", "receiverParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirReceiverParameter;", "getReceiverParameter", "()Lorg/jetbrains/kotlin/fir/declarations/FirReceiverParameter;", "setReceiverParameter", "(Lorg/jetbrains/kotlin/fir/declarations/FirReceiverParameter;)V", "deprecationsProvider", "Lorg/jetbrains/kotlin/fir/declarations/DeprecationsProvider;", "getDeprecationsProvider", "()Lorg/jetbrains/kotlin/fir/declarations/DeprecationsProvider;", "setDeprecationsProvider", "(Lorg/jetbrains/kotlin/fir/declarations/DeprecationsProvider;)V", "dispatchReceiverType", "Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;", "getDispatchReceiverType", "()Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;", "setDispatchReceiverType", "(Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;)V", "contextParameters", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "getContextParameters", "controlFlowGraphReference", "Lorg/jetbrains/kotlin/fir/references/FirControlFlowGraphReference;", "getControlFlowGraphReference", "()Lorg/jetbrains/kotlin/fir/references/FirControlFlowGraphReference;", "setControlFlowGraphReference", "(Lorg/jetbrains/kotlin/fir/references/FirControlFlowGraphReference;)V", "valueParameters", "getValueParameters", "body", "Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "getBody", "()Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "setBody", "(Lorg/jetbrains/kotlin/fir/expressions/FirBlock;)V", "contractDescription", "Lorg/jetbrains/kotlin/fir/contracts/FirContractDescription;", "getContractDescription", "()Lorg/jetbrains/kotlin/fir/contracts/FirContractDescription;", "setContractDescription", "(Lorg/jetbrains/kotlin/fir/contracts/FirContractDescription;)V", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirAnonymousFunctionSymbol;", "getSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirAnonymousFunctionSymbol;", "setSymbol", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirAnonymousFunctionSymbol;)V", CoroutineCodegenUtilKt.COROUTINE_LABEL_FIELD_NAME, "Lorg/jetbrains/kotlin/fir/FirLabel;", "getLabel", "()Lorg/jetbrains/kotlin/fir/FirLabel;", "setLabel", "(Lorg/jetbrains/kotlin/fir/FirLabel;)V", "invocationKind", "Lorg/jetbrains/kotlin/contracts/description/EventOccurrencesRange;", "getInvocationKind", "()Lorg/jetbrains/kotlin/contracts/description/EventOccurrencesRange;", "setInvocationKind", "(Lorg/jetbrains/kotlin/contracts/description/EventOccurrencesRange;)V", "inlineStatus", "Lorg/jetbrains/kotlin/fir/declarations/InlineStatus;", "getInlineStatus", "()Lorg/jetbrains/kotlin/fir/declarations/InlineStatus;", "setInlineStatus", "(Lorg/jetbrains/kotlin/fir/declarations/InlineStatus;)V", "<set-?>", Argument.Delimiters.none, "isLambda", "()Z", "setLambda", "(Z)V", "isLambda$delegate", "Lkotlin/properties/ReadWriteProperty;", "hasExplicitParameterList", "getHasExplicitParameterList", "setHasExplicitParameterList", "hasExplicitParameterList$delegate", "typeParameters", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;", "getTypeParameters", "typeRef", "getTypeRef", "setTypeRef", "build", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", InlineClassManglingUtilsKt.NOT_INLINE_CLASS_PARAMETER_PLACEHOLDER, "isLocal", "isLocal$annotations", "setLocal", "Lkotlin/Deprecated;", "message", "Modification of 'isLocal' has no impact for FirAnonymousFunctionBuilder", "level", "Lkotlin/DeprecationLevel;", "HIDDEN", "Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerSource;", "containerSource", "getContainerSource$annotations", "getContainerSource", "()Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerSource;", "setContainerSource", "(Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerSource;)V", "Modification of 'containerSource' has no impact for FirAnonymousFunctionBuilder", "org.jetbrains.kotlin:tree", "Lorg/jetbrains/kotlin/fir/builder/FirBuilderDsl;"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirAnonymousFunctionBuilder implements FirAnnotationContainerBuilder, FirFunctionBuilder, FirTypeParametersOwnerBuilder {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new MutablePropertyReference1Impl<>(FirAnonymousFunctionBuilder.class, "isLambda", "isLambda()Z", 0), new MutablePropertyReference1Impl<>(FirAnonymousFunctionBuilder.class, "hasExplicitParameterList", "getHasExplicitParameterList()Z", 0)};
    private FirBlock body;
    private FirContractDescription contractDescription;
    private FirControlFlowGraphReference controlFlowGraphReference;
    private ConeSimpleKotlinType dispatchReceiverType;

    /* JADX INFO: renamed from: hasExplicitParameterList$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty hasExplicitParameterList;
    private EventOccurrencesRange invocationKind;

    /* JADX INFO: renamed from: isLambda$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty isLambda;
    private FirLabel label;
    public FirModuleData moduleData;
    public FirDeclarationOrigin origin;
    private FirReceiverParameter receiverParameter;
    public FirTypeRef returnTypeRef;
    private KtSourceElement source;
    public FirAnonymousFunctionSymbol symbol;
    private final List<FirTypeParameter> typeParameters;
    private FirTypeRef typeRef;
    private FirResolvePhase resolvePhase = FirResolvePhase.RAW_FIR;
    private final List<FirAnnotation> annotations = new ArrayList();
    private FirDeclarationAttributes attributes = new FirDeclarationAttributes();
    private FirDeclarationStatus status = FirResolvedDeclarationStatusImpl.INSTANCE.getDEFAULT_STATUS_FOR_STATUSLESS_DECLARATIONS();
    private DeprecationsProvider deprecationsProvider = UnresolvedDeprecationProvider.INSTANCE;
    private final List<FirValueParameter> contextParameters = new ArrayList();
    private final List<FirValueParameter> valueParameters = new ArrayList();
    private InlineStatus inlineStatus = InlineStatus.Unknown;

    public FirAnonymousFunctionBuilder() {
        Delegates delegates = Delegates.INSTANCE;
        this.isLambda = delegates.notNull();
        this.hasExplicitParameterList = delegates.notNull();
        this.typeParameters = new ArrayList();
        this.typeRef = FirImplicitTypeRefImplWithoutSource.INSTANCE;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Modification of 'containerSource' has no impact for FirAnonymousFunctionBuilder")
    public static /* synthetic */ void getContainerSource$annotations() {
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Modification of 'isLocal' has no impact for FirAnonymousFunctionBuilder")
    public static /* synthetic */ void isLocal$annotations() {
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirTypeParametersOwnerBuilder
    /* JADX INFO: renamed from: build */
    public FirAnonymousFunction mo288build() {
        return new FirAnonymousFunctionImpl(getSource(), getResolvePhase(), FirBuilderDslKt.toMutableOrEmpty(getAnnotations()), getModuleData(), getOrigin(), getAttributes(), getStatus(), getReturnTypeRef(), this.receiverParameter, getDeprecationsProvider(), getDispatchReceiverType(), FirBuilderDslKt.toMutableOrEmpty(getContextParameters()), this.controlFlowGraphReference, getValueParameters(), getBody(), this.contractDescription, getSymbol(), this.label, this.invocationKind, this.inlineStatus, isLambda(), getHasExplicitParameterList(), getTypeParameters(), this.typeRef, null);
    }

    @Override // org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public List<FirAnnotation> getAnnotations() {
        return this.annotations;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirFunctionBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public FirDeclarationAttributes getAttributes() {
        return this.attributes;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirFunctionBuilder
    public FirBlock getBody() {
        return this.body;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirFunctionBuilder
    public /* synthetic */ DeserializedContainerSource getContainerSource() {
        throw new IllegalStateException();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirFunctionBuilder
    public List<FirValueParameter> getContextParameters() {
        return this.contextParameters;
    }

    public final FirContractDescription getContractDescription() {
        return this.contractDescription;
    }

    public final FirControlFlowGraphReference getControlFlowGraphReference() {
        return this.controlFlowGraphReference;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirFunctionBuilder
    public DeprecationsProvider getDeprecationsProvider() {
        return this.deprecationsProvider;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirFunctionBuilder
    public ConeSimpleKotlinType getDispatchReceiverType() {
        return this.dispatchReceiverType;
    }

    public final boolean getHasExplicitParameterList() {
        return ((Boolean) this.hasExplicitParameterList.getValue(this, $$delegatedProperties[1])).booleanValue();
    }

    public final InlineStatus getInlineStatus() {
        return this.inlineStatus;
    }

    public final EventOccurrencesRange getInvocationKind() {
        return this.invocationKind;
    }

    public final FirLabel getLabel() {
        return this.label;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirFunctionBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public FirModuleData getModuleData() throws UninitializedPropertyAccessException {
        FirModuleData firModuleData = this.moduleData;
        if (firModuleData != null) {
            return firModuleData;
        }
        Intrinsics.throwUninitializedPropertyAccessException("moduleData");
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirFunctionBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public FirDeclarationOrigin getOrigin() throws UninitializedPropertyAccessException {
        FirDeclarationOrigin firDeclarationOrigin = this.origin;
        if (firDeclarationOrigin != null) {
            return firDeclarationOrigin;
        }
        Intrinsics.throwUninitializedPropertyAccessException("origin");
        return null;
    }

    public final FirReceiverParameter getReceiverParameter() {
        return this.receiverParameter;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirFunctionBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public FirResolvePhase getResolvePhase() {
        return this.resolvePhase;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirFunctionBuilder
    public FirTypeRef getReturnTypeRef() throws UninitializedPropertyAccessException {
        FirTypeRef firTypeRef = this.returnTypeRef;
        if (firTypeRef != null) {
            return firTypeRef;
        }
        Intrinsics.throwUninitializedPropertyAccessException("returnTypeRef");
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirFunctionBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public KtSourceElement getSource() {
        return this.source;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirFunctionBuilder
    public FirDeclarationStatus getStatus() {
        return this.status;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final FirAnonymousFunctionSymbol getSymbol() throws UninitializedPropertyAccessException {
        FirAnonymousFunctionSymbol firAnonymousFunctionSymbol = this.symbol;
        if (firAnonymousFunctionSymbol != null) {
            return firAnonymousFunctionSymbol;
        }
        Intrinsics.throwUninitializedPropertyAccessException("symbol");
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirTypeParametersOwnerBuilder
    public List<FirTypeParameter> getTypeParameters() {
        return this.typeParameters;
    }

    public final FirTypeRef getTypeRef() {
        return this.typeRef;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirFunctionBuilder
    public List<FirValueParameter> getValueParameters() {
        return this.valueParameters;
    }

    public final boolean isLambda() {
        return ((Boolean) this.isLambda.getValue(this, $$delegatedProperties[0])).booleanValue();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirFunctionBuilder
    public /* synthetic */ boolean isLocal() {
        throw new IllegalStateException();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirFunctionBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public void setAttributes(FirDeclarationAttributes firDeclarationAttributes) {
        firDeclarationAttributes.getClass();
        this.attributes = firDeclarationAttributes;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirFunctionBuilder
    public void setBody(FirBlock firBlock) {
        this.body = firBlock;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirFunctionBuilder
    public /* synthetic */ void setContainerSource(DeserializedContainerSource deserializedContainerSource) {
        throw new IllegalStateException();
    }

    public final void setContractDescription(FirContractDescription firContractDescription) {
        this.contractDescription = firContractDescription;
    }

    public final void setControlFlowGraphReference(FirControlFlowGraphReference firControlFlowGraphReference) {
        this.controlFlowGraphReference = firControlFlowGraphReference;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirFunctionBuilder
    public void setDeprecationsProvider(DeprecationsProvider deprecationsProvider) {
        deprecationsProvider.getClass();
        this.deprecationsProvider = deprecationsProvider;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirFunctionBuilder
    public void setDispatchReceiverType(ConeSimpleKotlinType coneSimpleKotlinType) {
        this.dispatchReceiverType = coneSimpleKotlinType;
    }

    public final void setHasExplicitParameterList(boolean z) {
        this.hasExplicitParameterList.setValue(this, $$delegatedProperties[1], Boolean.valueOf(z));
    }

    public final void setInlineStatus(InlineStatus inlineStatus) {
        inlineStatus.getClass();
        this.inlineStatus = inlineStatus;
    }

    public final void setInvocationKind(EventOccurrencesRange eventOccurrencesRange) {
        this.invocationKind = eventOccurrencesRange;
    }

    public final void setLabel(FirLabel firLabel) {
        this.label = firLabel;
    }

    public final void setLambda(boolean z) {
        this.isLambda.setValue(this, $$delegatedProperties[0], Boolean.valueOf(z));
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirFunctionBuilder
    public /* synthetic */ void setLocal(boolean z) {
        throw new IllegalStateException();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirFunctionBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public void setModuleData(FirModuleData firModuleData) {
        firModuleData.getClass();
        this.moduleData = firModuleData;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirFunctionBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public void setOrigin(FirDeclarationOrigin firDeclarationOrigin) {
        firDeclarationOrigin.getClass();
        this.origin = firDeclarationOrigin;
    }

    public final void setReceiverParameter(FirReceiverParameter firReceiverParameter) {
        this.receiverParameter = firReceiverParameter;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirFunctionBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public void setResolvePhase(FirResolvePhase firResolvePhase) {
        firResolvePhase.getClass();
        this.resolvePhase = firResolvePhase;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirFunctionBuilder
    public void setReturnTypeRef(FirTypeRef firTypeRef) {
        firTypeRef.getClass();
        this.returnTypeRef = firTypeRef;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirFunctionBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public void setSource(KtSourceElement ktSourceElement) {
        this.source = ktSourceElement;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirFunctionBuilder
    public void setStatus(FirDeclarationStatus firDeclarationStatus) {
        firDeclarationStatus.getClass();
        this.status = firDeclarationStatus;
    }

    public final void setSymbol(FirAnonymousFunctionSymbol firAnonymousFunctionSymbol) {
        firAnonymousFunctionSymbol.getClass();
        this.symbol = firAnonymousFunctionSymbol;
    }

    public final void setTypeRef(FirTypeRef firTypeRef) {
        firTypeRef.getClass();
        this.typeRef = firTypeRef;
    }
}
