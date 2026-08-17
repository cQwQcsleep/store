package org.jetbrains.kotlin.fir.java.declarations;

import java.util.ArrayList;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.KotlinNothingValueException;
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
import org.jetbrains.kotlin.fir.declarations.DeprecationsProvider;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationAttributes;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.builder.FirFunctionBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirTypeParametersOwnerBuilder;
import org.jetbrains.kotlin.fir.declarations.impl.FirResolvedDeclarationStatusImpl;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.java.enhancement.FirEmptyJavaAnnotationList;
import org.jetbrains.kotlin.fir.java.enhancement.FirJavaAnnotationList;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerSource;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@FirBuilderDsl
@Metadata(d1 = {"\u0000È\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\u0087\u0001\u001a\u00030\u0088\u0001H\u0016R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0096.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0019X\u0096.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u001c\u0010#\u001a\u0004\u0018\u00010$X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u001a\u0010)\u001a\u00020*X\u0096.¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u001c\u0010/\u001a\u0004\u0018\u000100X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104R\u001a\u00105\u001a\u000206X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R\u001a\u0010;\u001a\u00020<X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@R\u001a\u0010A\u001a\b\u0012\u0004\u0012\u00020B0\u001f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bC\u0010\"R\u001a\u0010D\u001a\b\u0012\u0004\u0012\u00020E0\u001fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bF\u0010\"R\u001a\u0010G\u001a\u00020HX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bI\u0010J\"\u0004\bK\u0010LR+\u0010O\u001a\u00020N2\u0006\u0010M\u001a\u00020N8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\bS\u0010T\u001a\u0004\bO\u0010P\"\u0004\bQ\u0010RR\u001a\u0010U\u001a\u00020VX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bW\u0010X\"\u0004\bY\u0010ZR\u001e\u0010[\u001a\u0006\u0012\u0002\b\u00030\\X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b]\u0010^\"\u0004\b_\u0010`RD\u0010c\u001a\u00020b2\u0006\u0010a\u001a\u00020b8V@VX\u0097\u000er\u0018\bi\u0012\b\bj\u0012\u0004\b\b(k\u0012\n\bl\u0012\u0006\b\n0m8n¢\u0006\u0012\u0012\u0004\bd\u0010\u0005\u001a\u0004\be\u0010f\"\u0004\bg\u0010hRH\u0010p\u001a\u0004\u0018\u00010o2\b\u0010a\u001a\u0004\u0018\u00010o8V@VX\u0097\u000er\u0018\bi\u0012\b\bj\u0012\u0004\b\b(v\u0012\n\bl\u0012\u0006\b\n0m8n¢\u0006\u0012\u0012\u0004\bq\u0010\u0005\u001a\u0004\br\u0010s\"\u0004\bt\u0010uRD\u0010x\u001a\u00020w2\u0006\u0010a\u001a\u00020w8V@VX\u0097\u000er\u0018\bi\u0012\b\bj\u0012\u0004\b\b(~\u0012\n\bl\u0012\u0006\b\n0m8n¢\u0006\u0012\u0012\u0004\by\u0010\u0005\u001a\u0004\bz\u0010{\"\u0004\b|\u0010}R=\u0010\u007f\u001a\b\u0012\u0004\u0012\u00020 0\u001f8VX\u0097\u0004r\u0019\bi\u0012\t\bj\u0012\u0005\b\b(\u0082\u0001\u0012\n\bl\u0012\u0006\b\n0m8n¢\u0006\u000e\u0012\u0005\b\u0080\u0001\u0010\u0005\u001a\u0005\b\u0081\u0001\u0010\"RI\u0010\u0083\u0001\u001a\u00020N2\u0006\u0010a\u001a\u00020N8V@VX\u0097\u000er\u0019\bi\u0012\t\bj\u0012\u0005\b\b(\u0086\u0001\u0012\n\bl\u0012\u0006\b\n0m8n¢\u0006\u0015\u0012\u0005\b\u0084\u0001\u0010\u0005\u001a\u0005\b\u0083\u0001\u0010P\"\u0005\b\u0085\u0001\u0010RÊ\u0001\u0003\b\u008a\u0001¨\u0006\u0089\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaMethodBuilder;", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirFunctionBuilder;", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirTypeParametersOwnerBuilder;", "Lorg/jetbrains/kotlin/fir/builder/FirAnnotationContainerBuilder;", "<init>", "()V", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "setSource", "(Lorg/jetbrains/kotlin/KtSourceElement;)V", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "getModuleData", "()Lorg/jetbrains/kotlin/fir/FirModuleData;", "setModuleData", "(Lorg/jetbrains/kotlin/fir/FirModuleData;)V", "attributes", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;", "getAttributes", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;", "setAttributes", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;)V", "returnTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "getReturnTypeRef", "()Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "setReturnTypeRef", "(Lorg/jetbrains/kotlin/fir/types/FirTypeRef;)V", "valueParameters", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "getValueParameters", "()Ljava/util/List;", "body", "Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "getBody", "()Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "setBody", "(Lorg/jetbrains/kotlin/fir/expressions/FirBlock;)V", "status", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;", "getStatus", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;", "setStatus", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;)V", "dispatchReceiverType", "Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;", "getDispatchReceiverType", "()Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;", "setDispatchReceiverType", "(Lorg/jetbrains/kotlin/fir/types/ConeSimpleKotlinType;)V", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "getName", "()Lorg/jetbrains/kotlin/name/Name;", "setName", "(Lorg/jetbrains/kotlin/name/Name;)V", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "getSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "setSymbol", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;)V", "annotations", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "getAnnotations", "typeParameters", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;", "getTypeParameters", "resolvePhase", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "getResolvePhase", "()Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "setResolvePhase", "(Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;)V", "<set-?>", Argument.Delimiters.none, "isFromSource", "()Z", "setFromSource", "(Z)V", "isFromSource$delegate", "Lkotlin/properties/ReadWriteProperty;", "annotationList", "Lorg/jetbrains/kotlin/fir/java/enhancement/FirJavaAnnotationList;", "getAnnotationList", "()Lorg/jetbrains/kotlin/fir/java/enhancement/FirJavaAnnotationList;", "setAnnotationList", "(Lorg/jetbrains/kotlin/fir/java/enhancement/FirJavaAnnotationList;)V", "containingClassSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "getContainingClassSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "setContainingClassSymbol", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;)V", InlineClassManglingUtilsKt.NOT_INLINE_CLASS_PARAMETER_PLACEHOLDER, "Lorg/jetbrains/kotlin/fir/declarations/DeprecationsProvider;", "deprecationsProvider", "getDeprecationsProvider$annotations", "getDeprecationsProvider", "()Lorg/jetbrains/kotlin/fir/declarations/DeprecationsProvider;", "setDeprecationsProvider", "(Lorg/jetbrains/kotlin/fir/declarations/DeprecationsProvider;)V", "Lkotlin/Deprecated;", "message", "Modification of 'deprecation' has no impact for FirJavaFunctionBuilder", "level", "Lkotlin/DeprecationLevel;", "HIDDEN", "Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerSource;", "containerSource", "getContainerSource$annotations", "getContainerSource", "()Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerSource;", "setContainerSource", "(Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerSource;)V", "Modification of 'containerSource' has no impact for FirJavaFunctionBuilder", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "origin", "getOrigin$annotations", "getOrigin", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "setOrigin", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;)V", "Modification of 'origin' has no impact for FirJavaFunctionBuilder", "contextParameters", "getContextParameters$annotations", "getContextParameters", "Modification of 'contextParameters' has no impact for FirJavaFunctionBuilder", "isLocal", "isLocal$annotations", "setLocal", "Modification of 'isLocal' has no impact for FirJavaFunctionBuilder", "build", "Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaMethod;", "org.jetbrains.kotlin:fir-jvm", "Lorg/jetbrains/kotlin/fir/builder/FirBuilderDsl;"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJavaMethodBuilder implements FirAnnotationContainerBuilder, FirFunctionBuilder, FirTypeParametersOwnerBuilder {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new MutablePropertyReference1Impl<>(FirJavaMethodBuilder.class, "isFromSource", "isFromSource()Z", 0)};
    private FirBlock body;
    public FirClassSymbol<?> containingClassSymbol;
    private ConeSimpleKotlinType dispatchReceiverType;
    public FirModuleData moduleData;
    public Name name;
    public FirTypeRef returnTypeRef;
    private KtSourceElement source;
    public FirDeclarationStatus status;
    public FirNamedFunctionSymbol symbol;
    private FirDeclarationAttributes attributes = new FirDeclarationAttributes();
    private final List<FirValueParameter> valueParameters = new ArrayList();
    private final List<FirTypeParameter> typeParameters = new ArrayList();
    private FirResolvePhase resolvePhase = FirResolvePhase.INSTANCE.getANALYZED_DEPENDENCIES();

    /* JADX INFO: renamed from: isFromSource$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty isFromSource = Delegates.INSTANCE.notNull();
    private FirJavaAnnotationList annotationList = FirEmptyJavaAnnotationList.INSTANCE;

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Modification of 'containerSource' has no impact for FirJavaFunctionBuilder")
    public static /* synthetic */ void getContainerSource$annotations() {
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Modification of 'contextParameters' has no impact for FirJavaFunctionBuilder")
    public static /* synthetic */ void getContextParameters$annotations() {
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Modification of 'deprecation' has no impact for FirJavaFunctionBuilder")
    public static /* synthetic */ void getDeprecationsProvider$annotations() {
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Modification of 'origin' has no impact for FirJavaFunctionBuilder")
    public static /* synthetic */ void getOrigin$annotations() {
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Modification of 'isLocal' has no impact for FirJavaFunctionBuilder")
    public static /* synthetic */ void isLocal$annotations() {
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirTypeParametersOwnerBuilder
    /* JADX INFO: renamed from: build, reason: merged with bridge method [inline-methods] */
    public FirJavaMethod mo288build() {
        KtSourceElement source = getSource();
        FirModuleData moduleData = getModuleData();
        FirDeclarationOrigin.Java javaJavaOrigin = UtilsKt.javaOrigin(isFromSource());
        FirDeclarationAttributes attributes = getAttributes();
        FirTypeRef returnTypeRef = getReturnTypeRef();
        List<FirTypeParameter> typeParameters = getTypeParameters();
        List<FirValueParameter> valueParameters = getValueParameters();
        Name name = getName();
        FirDeclarationStatus status = getStatus();
        status.getClass();
        return new FirJavaMethod(source, moduleData, javaJavaOrigin, attributes, returnTypeRef, typeParameters, valueParameters, name, (FirResolvedDeclarationStatusImpl) status, getSymbol(), this.annotationList, getDispatchReceiverType(), getContainingClassSymbol());
    }

    public final FirJavaAnnotationList getAnnotationList() {
        return this.annotationList;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public List<FirAnnotation> getAnnotations() throws KotlinNothingValueException {
        AddToStdlibKt.shouldNotBeCalled$default((String) null, 1, (Object) null);
        throw new KotlinNothingValueException();
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

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final FirClassSymbol<?> getContainingClassSymbol() throws UninitializedPropertyAccessException {
        FirClassSymbol<?> firClassSymbol = this.containingClassSymbol;
        if (firClassSymbol != null) {
            return firClassSymbol;
        }
        Intrinsics.throwUninitializedPropertyAccessException("containingClassSymbol");
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirFunctionBuilder
    public /* synthetic */ List getContextParameters() {
        throw new IllegalStateException();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirFunctionBuilder
    public /* synthetic */ DeprecationsProvider getDeprecationsProvider() {
        throw new IllegalStateException();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirFunctionBuilder
    public ConeSimpleKotlinType getDispatchReceiverType() {
        return this.dispatchReceiverType;
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
    public final Name getName() throws UninitializedPropertyAccessException {
        Name name = this.name;
        if (name != null) {
            return name;
        }
        Intrinsics.throwUninitializedPropertyAccessException(ModuleXmlParser.NAME);
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirFunctionBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public /* synthetic */ FirDeclarationOrigin getOrigin() {
        throw new IllegalStateException();
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

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirFunctionBuilder
    public FirDeclarationStatus getStatus() throws UninitializedPropertyAccessException {
        FirDeclarationStatus firDeclarationStatus = this.status;
        if (firDeclarationStatus != null) {
            return firDeclarationStatus;
        }
        Intrinsics.throwUninitializedPropertyAccessException("status");
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final FirNamedFunctionSymbol getSymbol() throws UninitializedPropertyAccessException {
        FirNamedFunctionSymbol firNamedFunctionSymbol = this.symbol;
        if (firNamedFunctionSymbol != null) {
            return firNamedFunctionSymbol;
        }
        Intrinsics.throwUninitializedPropertyAccessException("symbol");
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirTypeParametersOwnerBuilder
    public List<FirTypeParameter> getTypeParameters() {
        return this.typeParameters;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirFunctionBuilder
    public List<FirValueParameter> getValueParameters() {
        return this.valueParameters;
    }

    public final boolean isFromSource() {
        return ((Boolean) this.isFromSource.getValue(this, $$delegatedProperties[0])).booleanValue();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirFunctionBuilder
    public /* synthetic */ boolean isLocal() {
        throw new IllegalStateException();
    }

    public final void setAnnotationList(FirJavaAnnotationList firJavaAnnotationList) {
        firJavaAnnotationList.getClass();
        this.annotationList = firJavaAnnotationList;
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

    public final void setContainingClassSymbol(FirClassSymbol<?> firClassSymbol) {
        firClassSymbol.getClass();
        this.containingClassSymbol = firClassSymbol;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirFunctionBuilder
    public /* synthetic */ void setDeprecationsProvider(DeprecationsProvider deprecationsProvider) {
        deprecationsProvider.getClass();
        throw new IllegalStateException();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirFunctionBuilder
    public void setDispatchReceiverType(ConeSimpleKotlinType coneSimpleKotlinType) {
        this.dispatchReceiverType = coneSimpleKotlinType;
    }

    public final void setFromSource(boolean z) {
        this.isFromSource.setValue(this, $$delegatedProperties[0], Boolean.valueOf(z));
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

    public final void setName(Name name) {
        name.getClass();
        this.name = name;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirFunctionBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public /* synthetic */ void setOrigin(FirDeclarationOrigin firDeclarationOrigin) {
        firDeclarationOrigin.getClass();
        throw new IllegalStateException();
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

    public final void setSymbol(FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        this.symbol = firNamedFunctionSymbol;
    }
}
