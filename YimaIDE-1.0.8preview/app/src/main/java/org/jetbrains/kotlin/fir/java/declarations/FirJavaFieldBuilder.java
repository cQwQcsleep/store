package org.jetbrains.kotlin.fir.java.declarations;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Lazy;
import kotlin.LazyKt;
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
import org.jetbrains.kotlin.fir.declarations.builder.FirFieldBuilder;
import org.jetbrains.kotlin.fir.declarations.impl.FirResolvedDeclarationStatusImpl;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.java.enhancement.FirEmptyJavaAnnotationList;
import org.jetbrains.kotlin.fir.java.enhancement.FirJavaAnnotationList;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFieldSymbol;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@FirBuilderDsl
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\b\u0001\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\"\u001a\u00020#H\u0016R+\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\n\u0010\u000b\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R$\u0010\u0012\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0014\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R \u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00050\u0013X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0016\"\u0004\b\u001b\u0010\u0018R\u001e\u0010\u001c\u001a\u0006\u0012\u0002\b\u00030\u001dX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!RD\u0010&\u001a\u00020%2\u0006\u0010$\u001a\u00020%8V@VX\u0097\u000er\u0018\b,\u0012\b\b-\u0012\u0004\b\b(.\u0012\n\b/\u0012\u0006\b\n0081¢\u0006\u0012\u0012\u0004\b'\u0010\u0003\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+Ê\u0001\u0002\b3¨\u00062"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaFieldBuilder;", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirFieldBuilder;", "<init>", "()V", "<set-?>", Argument.Delimiters.none, "isFromSource", "()Z", "setFromSource", "(Z)V", "isFromSource$delegate", "Lkotlin/properties/ReadWriteProperty;", "annotationList", "Lorg/jetbrains/kotlin/fir/java/enhancement/FirJavaAnnotationList;", "getAnnotationList", "()Lorg/jetbrains/kotlin/fir/java/enhancement/FirJavaAnnotationList;", "setAnnotationList", "(Lorg/jetbrains/kotlin/fir/java/enhancement/FirJavaAnnotationList;)V", "lazyInitializer", "Lkotlin/Lazy;", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "getLazyInitializer", "()Lkotlin/Lazy;", "setLazyInitializer", "(Lkotlin/Lazy;)V", "lazyHasConstantInitializer", "getLazyHasConstantInitializer", "setLazyHasConstantInitializer", "containingClassSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "getContainingClassSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "setContainingClassSymbol", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;)V", "build", "Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaField;", "value", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "origin", "getOrigin$annotations", "getOrigin", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "setOrigin", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;)V", "Lkotlin/Deprecated;", "message", "Modification of 'origin' has no impact for FirJavaFieldBuilder", "level", "Lkotlin/DeprecationLevel;", "HIDDEN", "org.jetbrains.kotlin:fir-jvm", "Lorg/jetbrains/kotlin/fir/builder/FirBuilderDsl;"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJavaFieldBuilder extends FirFieldBuilder {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new MutablePropertyReference1Impl<>(FirJavaFieldBuilder.class, "isFromSource", "isFromSource()Z", 0)};
    public FirClassSymbol<?> containingClassSymbol;
    public Lazy<Boolean> lazyHasConstantInitializer;
    private Lazy<? extends FirExpression> lazyInitializer;

    /* JADX INFO: renamed from: isFromSource$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty isFromSource = Delegates.INSTANCE.notNull();
    private FirJavaAnnotationList annotationList = FirEmptyJavaAnnotationList.INSTANCE;

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Modification of 'origin' has no impact for FirJavaFieldBuilder")
    public static /* synthetic */ void getOrigin$annotations() {
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirFieldBuilder, org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder
    /* JADX INFO: renamed from: build */
    public FirJavaField mo288build() {
        KtSourceElement source = getSource();
        FirModuleData moduleData = getModuleData();
        FirDeclarationOrigin.Java javaJavaOrigin = UtilsKt.javaOrigin(isFromSource());
        FirFieldSymbol symbol = getSymbol();
        Name name = getName();
        FirTypeRef returnTypeRef = getReturnTypeRef();
        FirDeclarationStatus status = getStatus();
        status.getClass();
        FirResolvedDeclarationStatusImpl firResolvedDeclarationStatusImpl = (FirResolvedDeclarationStatusImpl) status;
        boolean zIsVar = isVar();
        FirJavaAnnotationList firJavaAnnotationList = this.annotationList;
        Lazy<? extends FirExpression> lazyLazyOf = this.lazyInitializer;
        if (lazyLazyOf == null) {
            lazyLazyOf = LazyKt.lazyOf(getInitializer());
        }
        return new FirJavaField(source, moduleData, javaJavaOrigin, symbol, name, returnTypeRef, firResolvedDeclarationStatusImpl, zIsVar, firJavaAnnotationList, lazyLazyOf, getLazyHasConstantInitializer(), getDispatchReceiverType(), getAttributes(), getContainingClassSymbol());
    }

    public final FirJavaAnnotationList getAnnotationList() {
        return this.annotationList;
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

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final Lazy<Boolean> getLazyHasConstantInitializer() throws UninitializedPropertyAccessException {
        Lazy<Boolean> lazy = this.lazyHasConstantInitializer;
        if (lazy != null) {
            return lazy;
        }
        Intrinsics.throwUninitializedPropertyAccessException("lazyHasConstantInitializer");
        return null;
    }

    public final Lazy<FirExpression> getLazyInitializer() {
        return this.lazyInitializer;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirFieldBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirVariableBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public /* synthetic */ FirDeclarationOrigin getOrigin() {
        throw new IllegalStateException();
    }

    public final boolean isFromSource() {
        return ((Boolean) this.isFromSource.getValue(this, $$delegatedProperties[0])).booleanValue();
    }

    public final void setAnnotationList(FirJavaAnnotationList firJavaAnnotationList) {
        firJavaAnnotationList.getClass();
        this.annotationList = firJavaAnnotationList;
    }

    public final void setContainingClassSymbol(FirClassSymbol<?> firClassSymbol) {
        firClassSymbol.getClass();
        this.containingClassSymbol = firClassSymbol;
    }

    public final void setFromSource(boolean z) {
        this.isFromSource.setValue(this, $$delegatedProperties[0], Boolean.valueOf(z));
    }

    public final void setLazyHasConstantInitializer(Lazy<Boolean> lazy) {
        lazy.getClass();
        this.lazyHasConstantInitializer = lazy;
    }

    public final void setLazyInitializer(Lazy<? extends FirExpression> lazy) {
        this.lazyInitializer = lazy;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirFieldBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirVariableBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public /* synthetic */ void setOrigin(FirDeclarationOrigin firDeclarationOrigin) {
        firDeclarationOrigin.getClass();
        throw new IllegalStateException();
    }
}
