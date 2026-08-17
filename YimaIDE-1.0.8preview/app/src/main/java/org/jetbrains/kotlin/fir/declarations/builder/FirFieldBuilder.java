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
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.state.InlineClassManglingUtilsKt;
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
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.UnresolvedDeprecationProvider;
import org.jetbrains.kotlin.fir.declarations.impl.FirFieldImpl;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.symbols.impl.FirFieldSymbol;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerSource;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@FirBuilderDsl
@Metadata(d1 = {"\u0000Ä\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\b\u0017\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010]\u001a\u00020^H\u0016R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0012X\u0096.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0018X\u0096.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001d\u001a\u00020\u001eX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001a\u0010#\u001a\u00020$X\u0096.¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R+\u0010+\u001a\u00020*2\u0006\u0010)\u001a\u00020*8V@VX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b/\u00100\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u001a\u00101\u001a\u000202X\u0096.¢\u0006\u000e\n\u0000\u001a\u0004\b3\u00104\"\u0004\b5\u00106R\u001a\u00107\u001a\u000208X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<R\u001c\u0010=\u001a\u0004\u0018\u00010>X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010@\"\u0004\bA\u0010BR\u001a\u0010C\u001a\u00020DX\u0096.¢\u0006\u000e\n\u0000\u001a\u0004\bE\u0010F\"\u0004\bG\u0010HR\u001c\u0010I\u001a\u0004\u0018\u00010JX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bK\u0010L\"\u0004\bM\u0010NR+\u0010O\u001a\u00020*2\u0006\u0010)\u001a\u00020*8V@VX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\bQ\u00100\u001a\u0004\bO\u0010,\"\u0004\bP\u0010.R\u001a\u0010R\u001a\b\u0012\u0004\u0012\u00020T0SX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bU\u0010VR\u001a\u0010W\u001a\u00020XX\u0096.¢\u0006\u000e\n\u0000\u001a\u0004\bY\u0010Z\"\u0004\b[\u0010\\RH\u0010a\u001a\u0004\u0018\u00010`2\b\u0010_\u001a\u0004\u0018\u00010`8V@VX\u0097\u000er\u0018\bg\u0012\b\bh\u0012\u0004\b\b(i\u0012\n\bj\u0012\u0006\b\n0k8l¢\u0006\u0012\u0012\u0004\bb\u0010\u0004\u001a\u0004\bc\u0010d\"\u0004\be\u0010fRH\u0010n\u001a\u0004\u0018\u00010m2\b\u0010_\u001a\u0004\u0018\u00010m8V@VX\u0097\u000er\u0018\bg\u0012\b\bh\u0012\u0004\b\b(t\u0012\n\bj\u0012\u0006\b\n0k8l¢\u0006\u0012\u0012\u0004\bo\u0010\u0004\u001a\u0004\bp\u0010q\"\u0004\br\u0010sR<\u0010u\u001a\b\u0012\u0004\u0012\u00020v0S8\u0016X\u0097\u0004r\u0018\bg\u0012\b\bh\u0012\u0004\b\b(y\u0012\n\bj\u0012\u0006\b\n0k8l¢\u0006\u000e\n\u0000\u0012\u0004\bw\u0010\u0004\u001a\u0004\bx\u0010VRH\u0010z\u001a\u0004\u0018\u00010J2\b\u0010_\u001a\u0004\u0018\u00010J8V@VX\u0097\u000er\u0018\bg\u0012\b\bh\u0012\u0004\b\b(~\u0012\n\bj\u0012\u0006\b\n0k8l¢\u0006\u0012\u0012\u0004\b{\u0010\u0004\u001a\u0004\b|\u0010L\"\u0004\b}\u0010NRO\u0010\u0080\u0001\u001a\u0004\u0018\u00010\u007f2\b\u0010_\u001a\u0004\u0018\u00010\u007f8V@VX\u0097\u000er\u0019\bg\u0012\t\bh\u0012\u0005\b\b(\u0086\u0001\u0012\n\bj\u0012\u0006\b\n0k8l¢\u0006\u0017\u0012\u0005\b\u0081\u0001\u0010\u0004\u001a\u0006\b\u0082\u0001\u0010\u0083\u0001\"\u0006\b\u0084\u0001\u0010\u0085\u0001RO\u0010\u0087\u0001\u001a\u0004\u0018\u00010\u007f2\b\u0010_\u001a\u0004\u0018\u00010\u007f8V@VX\u0097\u000er\u0019\bg\u0012\t\bh\u0012\u0005\b\b(\u008b\u0001\u0012\n\bj\u0012\u0006\b\n0k8l¢\u0006\u0017\u0012\u0005\b\u0088\u0001\u0010\u0004\u001a\u0006\b\u0089\u0001\u0010\u0083\u0001\"\u0006\b\u008a\u0001\u0010\u0085\u0001RQ\u0010\u008d\u0001\u001a\u0005\u0018\u00010\u008c\u00012\t\u0010_\u001a\u0005\u0018\u00010\u008c\u00018V@VX\u0097\u000er\u0019\bg\u0012\t\bh\u0012\u0005\b\b(\u0093\u0001\u0012\n\bj\u0012\u0006\b\n0k8l¢\u0006\u0017\u0012\u0005\b\u008e\u0001\u0010\u0004\u001a\u0006\b\u008f\u0001\u0010\u0090\u0001\"\u0006\b\u0091\u0001\u0010\u0092\u0001Ê\u0001\u0003\b\u0095\u0001¨\u0006\u0094\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/builder/FirFieldBuilder;", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirVariableBuilder;", "Lorg/jetbrains/kotlin/fir/builder/FirAnnotationContainerBuilder;", "<init>", "()V", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "setSource", "(Lorg/jetbrains/kotlin/KtSourceElement;)V", "resolvePhase", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "getResolvePhase", "()Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "setResolvePhase", "(Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;)V", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "getModuleData", "()Lorg/jetbrains/kotlin/fir/FirModuleData;", "setModuleData", "(Lorg/jetbrains/kotlin/fir/FirModuleData;)V", "origin", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "getOrigin", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "setOrigin", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;)V", "attributes", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;", "getAttributes", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;", "setAttributes", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;)V", "status", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;", "getStatus", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;", "setStatus", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;)V", "<set-?>", Argument.Delimiters.none, "isLocal", "()Z", "setLocal", "(Z)V", "isLocal$delegate", "Lkotlin/properties/ReadWriteProperty;", "returnTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "getReturnTypeRef", "()Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "setReturnTypeRef", "(Lorg/jetbrains/kotlin/fir/types/FirTypeRef;)V", "deprecationsProvider", "Lorg/jetbrains/kotlin/fir/declarations/DeprecationsProvider;", "getDeprecationsProvider", "()Lorg/jetbrains/kotlin/fir/declarations/DeprecationsProvider;", "setDeprecationsProvider", "(Lorg/jetbrains/kotlin/fir/declarations/DeprecationsProvider;)V", "dispatchReceiverType", "Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;", "getDispatchReceiverType", "()Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;", "setDispatchReceiverType", "(Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;)V", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "getName", "()Lorg/jetbrains/kotlin/name/Name;", "setName", "(Lorg/jetbrains/kotlin/name/Name;)V", "initializer", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "getInitializer", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "setInitializer", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)V", "isVar", "setVar", "isVar$delegate", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "getAnnotations", "()Ljava/util/List;", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFieldSymbol;", "getSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirFieldSymbol;", "setSymbol", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirFieldSymbol;)V", "build", "Lorg/jetbrains/kotlin/fir/declarations/FirField;", InlineClassManglingUtilsKt.NOT_INLINE_CLASS_PARAMETER_PLACEHOLDER, "Lorg/jetbrains/kotlin/fir/declarations/FirReceiverParameter;", "receiverParameter", "getReceiverParameter$annotations", "getReceiverParameter", "()Lorg/jetbrains/kotlin/fir/declarations/FirReceiverParameter;", "setReceiverParameter", "(Lorg/jetbrains/kotlin/fir/declarations/FirReceiverParameter;)V", "Lkotlin/Deprecated;", "message", "Modification of 'receiverParameter' has no impact for FirFieldBuilder", "level", "Lkotlin/DeprecationLevel;", "HIDDEN", "Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerSource;", "containerSource", "getContainerSource$annotations", "getContainerSource", "()Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerSource;", "setContainerSource", "(Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerSource;)V", "Modification of 'containerSource' has no impact for FirFieldBuilder", "contextParameters", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "getContextParameters$annotations", "getContextParameters", "Modification of 'contextParameters' has no impact for FirFieldBuilder", "delegate", "getDelegate$annotations", "getDelegate", "setDelegate", "Modification of 'delegate' has no impact for FirFieldBuilder", "Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;", "getter", "getGetter$annotations", "getGetter", "()Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;", "setGetter", "(Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;)V", "Modification of 'getter' has no impact for FirFieldBuilder", "setter", "getSetter$annotations", "getSetter", "setSetter", "Modification of 'setter' has no impact for FirFieldBuilder", "Lorg/jetbrains/kotlin/fir/declarations/FirBackingField;", "backingField", "getBackingField$annotations", "getBackingField", "()Lorg/jetbrains/kotlin/fir/declarations/FirBackingField;", "setBackingField", "(Lorg/jetbrains/kotlin/fir/declarations/FirBackingField;)V", "Modification of 'backingField' has no impact for FirFieldBuilder", "org.jetbrains.kotlin:tree", "Lorg/jetbrains/kotlin/fir/builder/FirBuilderDsl;"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class FirFieldBuilder implements FirAnnotationContainerBuilder, FirVariableBuilder {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new MutablePropertyReference1Impl<>(FirFieldBuilder.class, "isLocal", "isLocal()Z", 0), new MutablePropertyReference1Impl<>(FirFieldBuilder.class, "isVar", "isVar()Z", 0)};
    private final List<FirAnnotation> annotations;
    private final List<FirValueParameter> contextParameters;
    private DeprecationsProvider deprecationsProvider;
    private ConeSimpleKotlinType dispatchReceiverType;
    private FirExpression initializer;

    /* JADX INFO: renamed from: isLocal$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty isLocal;

    /* JADX INFO: renamed from: isVar$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty isVar;
    public FirModuleData moduleData;
    public Name name;
    public FirDeclarationOrigin origin;
    public FirTypeRef returnTypeRef;
    private KtSourceElement source;
    public FirDeclarationStatus status;
    public FirFieldSymbol symbol;
    private FirResolvePhase resolvePhase = FirResolvePhase.RAW_FIR;
    private FirDeclarationAttributes attributes = new FirDeclarationAttributes();

    public FirFieldBuilder() {
        Delegates delegates = Delegates.INSTANCE;
        this.isLocal = delegates.notNull();
        this.deprecationsProvider = UnresolvedDeprecationProvider.INSTANCE;
        this.isVar = delegates.notNull();
        this.annotations = new ArrayList();
        this.contextParameters = new ArrayList();
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Modification of 'backingField' has no impact for FirFieldBuilder")
    public static /* synthetic */ void getBackingField$annotations() {
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Modification of 'containerSource' has no impact for FirFieldBuilder")
    public static /* synthetic */ void getContainerSource$annotations() {
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Modification of 'contextParameters' has no impact for FirFieldBuilder")
    public static /* synthetic */ void getContextParameters$annotations() {
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Modification of 'delegate' has no impact for FirFieldBuilder")
    public static /* synthetic */ void getDelegate$annotations() {
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Modification of 'getter' has no impact for FirFieldBuilder")
    public static /* synthetic */ void getGetter$annotations() {
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Modification of 'receiverParameter' has no impact for FirFieldBuilder")
    public static /* synthetic */ void getReceiverParameter$annotations() {
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Modification of 'setter' has no impact for FirFieldBuilder")
    public static /* synthetic */ void getSetter$annotations() {
    }

    @Override // org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder
    /* JADX INFO: renamed from: build */
    public FirField mo289build() {
        return new FirFieldImpl(getSource(), getResolvePhase(), getModuleData(), getOrigin(), getAttributes(), getStatus(), isLocal(), getReturnTypeRef(), getDeprecationsProvider(), getDispatchReceiverType(), getName(), getInitializer(), isVar(), FirBuilderDslKt.toMutableOrEmpty(getAnnotations()), getSymbol(), null);
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
    public /* synthetic */ FirBackingField getBackingField() {
        throw new IllegalStateException();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirVariableBuilder
    public /* synthetic */ DeserializedContainerSource getContainerSource() {
        throw new IllegalStateException();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirVariableBuilder
    public /* synthetic */ List getContextParameters() {
        return this.contextParameters;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirVariableBuilder
    public /* synthetic */ FirExpression getDelegate() {
        throw new IllegalStateException();
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
    public /* synthetic */ FirPropertyAccessor getGetter() {
        throw new IllegalStateException();
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
    public /* synthetic */ FirReceiverParameter getReceiverParameter() {
        throw new IllegalStateException();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirVariableBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public FirResolvePhase getResolvePhase() {
        return this.resolvePhase;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirVariableBuilder
    public FirTypeRef getReturnTypeRef() throws UninitializedPropertyAccessException {
        FirTypeRef firTypeRef = this.returnTypeRef;
        if (firTypeRef != null) {
            return firTypeRef;
        }
        Intrinsics.throwUninitializedPropertyAccessException("returnTypeRef");
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirVariableBuilder
    public /* synthetic */ FirPropertyAccessor getSetter() {
        throw new IllegalStateException();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirVariableBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
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
    public FirFieldSymbol getSymbol() throws UninitializedPropertyAccessException {
        FirFieldSymbol firFieldSymbol = this.symbol;
        if (firFieldSymbol != null) {
            return firFieldSymbol;
        }
        Intrinsics.throwUninitializedPropertyAccessException("symbol");
        return null;
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
    public /* synthetic */ void setBackingField(FirBackingField firBackingField) {
        throw new IllegalStateException();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirVariableBuilder
    public /* synthetic */ void setContainerSource(DeserializedContainerSource deserializedContainerSource) {
        throw new IllegalStateException();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirVariableBuilder
    public /* synthetic */ void setDelegate(FirExpression firExpression) {
        throw new IllegalStateException();
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
    public /* synthetic */ void setGetter(FirPropertyAccessor firPropertyAccessor) {
        throw new IllegalStateException();
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
    public /* synthetic */ void setReceiverParameter(FirReceiverParameter firReceiverParameter) {
        throw new IllegalStateException();
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
    public /* synthetic */ void setSetter(FirPropertyAccessor firPropertyAccessor) {
        throw new IllegalStateException();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirVariableBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public void setSource(KtSourceElement ktSourceElement) {
        this.source = ktSourceElement;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirVariableBuilder
    public void setStatus(FirDeclarationStatus firDeclarationStatus) {
        firDeclarationStatus.getClass();
        this.status = firDeclarationStatus;
    }

    public void setSymbol(FirFieldSymbol firFieldSymbol) {
        firFieldSymbol.getClass();
        this.symbol = firFieldSymbol;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirVariableBuilder
    public void setVar(boolean z) {
        this.isVar.setValue(this, $$delegatedProperties[1], Boolean.valueOf(z));
    }
}
