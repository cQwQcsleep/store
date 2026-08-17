package org.jetbrains.kotlin.fir.java.declarations;

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
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.builder.FirBuilderDsl;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.builder.FirConstructorBuilder;
import org.jetbrains.kotlin.fir.declarations.impl.FirResolvedDeclarationStatusImpl;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall;
import org.jetbrains.kotlin.fir.java.enhancement.FirEmptyJavaAnnotationList;
import org.jetbrains.kotlin.fir.java.enhancement.FirJavaAnnotationList;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.types.FirTypeRef;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@FirBuilderDsl
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u001b\u001a\u00020\u001cH\u0016R+\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\n\u0010\u000b\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR+\u0010\f\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u000e\u0010\u000b\u001a\u0004\b\f\u0010\u0007\"\u0004\b\r\u0010\tR\u001a\u0010\u000f\u001a\u00020\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001e\u0010\u0015\u001a\u0006\u0012\u0002\b\u00030\u0016X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aRH\u0010\u001f\u001a\u0004\u0018\u00010\u001e2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e8V@VX\u0097\u000er\u0018\b%\u0012\b\b&\u0012\u0004\b\b('\u0012\n\b(\u0012\u0006\b\n0)8*¢\u0006\u0012\u0012\u0004\b \u0010\u0003\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$RH\u0010,\u001a\u0004\u0018\u00010+2\b\u0010\u001d\u001a\u0004\u0018\u00010+8V@VX\u0097\u000er\u0018\b%\u0012\b\b&\u0012\u0004\b\b(2\u0012\n\b(\u0012\u0006\b\n0)8*¢\u0006\u0012\u0012\u0004\b-\u0010\u0003\u001a\u0004\b.\u0010/\"\u0004\b0\u00101RD\u00104\u001a\u0002032\u0006\u0010\u001d\u001a\u0002038V@VX\u0097\u000er\u0018\b%\u0012\b\b&\u0012\u0004\b\b(:\u0012\n\b(\u0012\u0006\b\n0)8*¢\u0006\u0012\u0012\u0004\b5\u0010\u0003\u001a\u0004\b6\u00107\"\u0004\b8\u00109RH\u0010<\u001a\u0004\u0018\u00010;2\b\u0010\u001d\u001a\u0004\u0018\u00010;8V@VX\u0097\u000er\u0018\b%\u0012\b\b&\u0012\u0004\b\b(B\u0012\n\b(\u0012\u0006\b\n0)8*¢\u0006\u0012\u0012\u0004\b=\u0010\u0003\u001a\u0004\b>\u0010?\"\u0004\b@\u0010ARD\u0010D\u001a\u00020C2\u0006\u0010\u001d\u001a\u00020C8V@VX\u0097\u000er\u0018\b%\u0012\b\b&\u0012\u0004\b\b(J\u0012\n\b(\u0012\u0006\b\n0)8*¢\u0006\u0012\u0012\u0004\bE\u0010\u0003\u001a\u0004\bF\u0010G\"\u0004\bH\u0010IÊ\u0001\u0002\bL¨\u0006K"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaConstructorBuilder;", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirConstructorBuilder;", "<init>", "()V", "<set-?>", Argument.Delimiters.none, "isPrimary", "()Z", "setPrimary", "(Z)V", "isPrimary$delegate", "Lkotlin/properties/ReadWriteProperty;", "isFromSource", "setFromSource", "isFromSource$delegate", "annotationList", "Lorg/jetbrains/kotlin/fir/java/enhancement/FirJavaAnnotationList;", "getAnnotationList", "()Lorg/jetbrains/kotlin/fir/java/enhancement/FirJavaAnnotationList;", "setAnnotationList", "(Lorg/jetbrains/kotlin/fir/java/enhancement/FirJavaAnnotationList;)V", "containingClassSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "getContainingClassSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "setContainingClassSymbol", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;)V", "build", "Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaConstructor;", "value", "Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "body", "getBody$annotations", "getBody", "()Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "setBody", "(Lorg/jetbrains/kotlin/fir/expressions/FirBlock;)V", "Lkotlin/Deprecated;", "message", "Modification of 'body' has no impact for FirJavaConstructorBuilder", "level", "Lkotlin/DeprecationLevel;", "HIDDEN", "Lorg/jetbrains/kotlin/fir/expressions/FirDelegatedConstructorCall;", "delegatedConstructor", "getDelegatedConstructor$annotations", "getDelegatedConstructor", "()Lorg/jetbrains/kotlin/fir/expressions/FirDelegatedConstructorCall;", "setDelegatedConstructor", "(Lorg/jetbrains/kotlin/fir/expressions/FirDelegatedConstructorCall;)V", "Modification of 'delegatedConstructor' has no impact for FirJavaConstructorBuilder", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "resolvePhase", "getResolvePhase$annotations", "getResolvePhase", "()Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "setResolvePhase", "(Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;)V", "Modification of 'resolvePhase' has no impact for FirJavaConstructorBuilder", "Lorg/jetbrains/kotlin/fir/declarations/FirReceiverParameter;", "receiverParameter", "getReceiverParameter$annotations", "getReceiverParameter", "()Lorg/jetbrains/kotlin/fir/declarations/FirReceiverParameter;", "setReceiverParameter", "(Lorg/jetbrains/kotlin/fir/declarations/FirReceiverParameter;)V", "Modification of 'receiverParameter' has no impact for FirJavaConstructorBuilder", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "origin", "getOrigin$annotations", "getOrigin", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "setOrigin", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;)V", "Modification of 'origin' has no impact for FirJavaConstructorBuilder", "org.jetbrains.kotlin:fir-jvm", "Lorg/jetbrains/kotlin/fir/builder/FirBuilderDsl;"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJavaConstructorBuilder extends FirConstructorBuilder {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new MutablePropertyReference1Impl<>(FirJavaConstructorBuilder.class, "isPrimary", "isPrimary()Z", 0), new MutablePropertyReference1Impl<>(FirJavaConstructorBuilder.class, "isFromSource", "isFromSource()Z", 0)};
    private FirJavaAnnotationList annotationList;
    public FirClassSymbol<?> containingClassSymbol;

    /* JADX INFO: renamed from: isFromSource$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty isFromSource;

    /* JADX INFO: renamed from: isPrimary$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty isPrimary;

    public FirJavaConstructorBuilder() {
        Delegates delegates = Delegates.INSTANCE;
        this.isPrimary = delegates.notNull();
        this.isFromSource = delegates.notNull();
        this.annotationList = FirEmptyJavaAnnotationList.INSTANCE;
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Modification of 'body' has no impact for FirJavaConstructorBuilder")
    public static /* synthetic */ void getBody$annotations() {
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Modification of 'delegatedConstructor' has no impact for FirJavaConstructorBuilder")
    public static /* synthetic */ void getDelegatedConstructor$annotations() {
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Modification of 'origin' has no impact for FirJavaConstructorBuilder")
    public static /* synthetic */ void getOrigin$annotations() {
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Modification of 'receiverParameter' has no impact for FirJavaConstructorBuilder")
    public static /* synthetic */ void getReceiverParameter$annotations() {
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Modification of 'resolvePhase' has no impact for FirJavaConstructorBuilder")
    public static /* synthetic */ void getResolvePhase$annotations() {
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirConstructorBuilder, org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder
    /* JADX INFO: renamed from: build */
    public FirJavaConstructor mo288build() {
        KtSourceElement source = getSource();
        FirModuleData moduleData = getModuleData();
        FirConstructorSymbol symbol = getSymbol();
        FirDeclarationOrigin.Java javaJavaOrigin = UtilsKt.javaOrigin(isFromSource());
        boolean zIsPrimary = isPrimary();
        FirTypeRef returnTypeRef = getReturnTypeRef();
        List<FirValueParameter> valueParameters = getValueParameters();
        List<FirTypeParameterRef> typeParameters = getTypeParameters();
        FirJavaAnnotationList firJavaAnnotationList = this.annotationList;
        FirDeclarationStatus status = getStatus();
        status.getClass();
        return new FirJavaConstructor(source, moduleData, symbol, javaJavaOrigin, zIsPrimary, returnTypeRef, valueParameters, typeParameters, firJavaAnnotationList, (FirResolvedDeclarationStatusImpl) status, getDispatchReceiverType(), getContainingClassSymbol());
    }

    public final FirJavaAnnotationList getAnnotationList() {
        return this.annotationList;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirConstructorBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirAbstractConstructorBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirFunctionBuilder
    public /* synthetic */ FirBlock getBody() {
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

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirConstructorBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirAbstractConstructorBuilder
    public /* synthetic */ FirDelegatedConstructorCall getDelegatedConstructor() {
        throw new IllegalStateException();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirConstructorBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirAbstractConstructorBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirFunctionBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public /* synthetic */ FirDeclarationOrigin getOrigin() {
        throw new IllegalStateException();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirConstructorBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirAbstractConstructorBuilder
    public /* synthetic */ FirReceiverParameter getReceiverParameter() {
        throw new IllegalStateException();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirConstructorBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirAbstractConstructorBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirFunctionBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public /* synthetic */ FirResolvePhase getResolvePhase() {
        throw new IllegalStateException();
    }

    public final boolean isFromSource() {
        return ((Boolean) this.isFromSource.getValue(this, $$delegatedProperties[1])).booleanValue();
    }

    public final boolean isPrimary() {
        return ((Boolean) this.isPrimary.getValue(this, $$delegatedProperties[0])).booleanValue();
    }

    public final void setAnnotationList(FirJavaAnnotationList firJavaAnnotationList) {
        firJavaAnnotationList.getClass();
        this.annotationList = firJavaAnnotationList;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirConstructorBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirAbstractConstructorBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirFunctionBuilder
    public /* synthetic */ void setBody(FirBlock firBlock) {
        throw new IllegalStateException();
    }

    public final void setContainingClassSymbol(FirClassSymbol<?> firClassSymbol) {
        firClassSymbol.getClass();
        this.containingClassSymbol = firClassSymbol;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirConstructorBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirAbstractConstructorBuilder
    public /* synthetic */ void setDelegatedConstructor(FirDelegatedConstructorCall firDelegatedConstructorCall) {
        throw new IllegalStateException();
    }

    public final void setFromSource(boolean z) {
        this.isFromSource.setValue(this, $$delegatedProperties[1], Boolean.valueOf(z));
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirConstructorBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirAbstractConstructorBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirFunctionBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public /* synthetic */ void setOrigin(FirDeclarationOrigin firDeclarationOrigin) {
        firDeclarationOrigin.getClass();
        throw new IllegalStateException();
    }

    public final void setPrimary(boolean z) {
        this.isPrimary.setValue(this, $$delegatedProperties[0], Boolean.valueOf(z));
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirConstructorBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirAbstractConstructorBuilder
    public /* synthetic */ void setReceiverParameter(FirReceiverParameter firReceiverParameter) {
        throw new IllegalStateException();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirConstructorBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirAbstractConstructorBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirFunctionBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public /* synthetic */ void setResolvePhase(FirResolvePhase firResolvePhase) {
        firResolvePhase.getClass();
        throw new IllegalStateException();
    }
}
