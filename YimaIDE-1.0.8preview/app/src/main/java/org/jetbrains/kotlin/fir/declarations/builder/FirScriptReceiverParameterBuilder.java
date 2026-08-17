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
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder;
import org.jetbrains.kotlin.fir.builder.FirBuilderDsl;
import org.jetbrains.kotlin.fir.builder.FirBuilderDslKt;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationAttributes;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirScriptReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.impl.FirScriptReceiverParameterImpl;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirReceiverParameterSymbol;
import org.jetbrains.kotlin.fir.types.FirTypeRef;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@FirBuilderDsl
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010A\u001a\u00020BH\u0016R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0017X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R\u001a\u0010\"\u001a\u00020#X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u001e\u0010(\u001a\u0006\u0012\u0002\b\u00030)X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001a\u0010.\u001a\b\u0012\u0004\u0012\u0002000/X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b1\u00102R\u001a\u00103\u001a\u000204X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b5\u00106\"\u0004\b7\u00108R+\u0010;\u001a\u00020:2\u0006\u00109\u001a\u00020:8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b?\u0010@\u001a\u0004\b;\u0010<\"\u0004\b=\u0010>Ê\u0001\u0002\bD¨\u0006C"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/builder/FirScriptReceiverParameterBuilder;", "Lorg/jetbrains/kotlin/fir/builder/FirAnnotationContainerBuilder;", "<init>", "()V", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "setSource", "(Lorg/jetbrains/kotlin/KtSourceElement;)V", "resolvePhase", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "getResolvePhase", "()Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "setResolvePhase", "(Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;)V", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "getModuleData", "()Lorg/jetbrains/kotlin/fir/FirModuleData;", "setModuleData", "(Lorg/jetbrains/kotlin/fir/FirModuleData;)V", "origin", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "getOrigin", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "setOrigin", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;)V", "attributes", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;", "getAttributes", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;", "setAttributes", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;)V", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirReceiverParameterSymbol;", "getSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirReceiverParameterSymbol;", "setSymbol", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirReceiverParameterSymbol;)V", "containingDeclarationSymbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "getContainingDeclarationSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "setContainingDeclarationSymbol", "(Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;)V", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "getAnnotations", "()Ljava/util/List;", "typeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "getTypeRef", "()Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "setTypeRef", "(Lorg/jetbrains/kotlin/fir/types/FirTypeRef;)V", "<set-?>", Argument.Delimiters.none, "isBaseClassReceiver", "()Z", "setBaseClassReceiver", "(Z)V", "isBaseClassReceiver$delegate", "Lkotlin/properties/ReadWriteProperty;", "build", "Lorg/jetbrains/kotlin/fir/declarations/FirScriptReceiverParameter;", "org.jetbrains.kotlin:tree", "Lorg/jetbrains/kotlin/fir/builder/FirBuilderDsl;"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirScriptReceiverParameterBuilder implements FirAnnotationContainerBuilder {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new MutablePropertyReference1Impl<>(FirScriptReceiverParameterBuilder.class, "isBaseClassReceiver", "isBaseClassReceiver()Z", 0)};
    public FirBasedSymbol<?> containingDeclarationSymbol;
    public FirModuleData moduleData;
    public FirDeclarationOrigin origin;
    private KtSourceElement source;
    public FirReceiverParameterSymbol symbol;
    public FirTypeRef typeRef;
    private FirResolvePhase resolvePhase = FirResolvePhase.RAW_FIR;
    private FirDeclarationAttributes attributes = new FirDeclarationAttributes();
    private final List<FirAnnotation> annotations = new ArrayList();

    /* JADX INFO: renamed from: isBaseClassReceiver$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty isBaseClassReceiver = Delegates.INSTANCE.notNull();

    @Override // org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder
    public FirScriptReceiverParameter build() {
        return new FirScriptReceiverParameterImpl(this.source, this.resolvePhase, getModuleData(), getOrigin(), this.attributes, getSymbol(), getContainingDeclarationSymbol(), FirBuilderDslKt.toMutableOrEmpty(getAnnotations()), getTypeRef(), isBaseClassReceiver(), null);
    }

    @Override // org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public List<FirAnnotation> getAnnotations() {
        return this.annotations;
    }

    public final FirDeclarationAttributes getAttributes() {
        return this.attributes;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final FirBasedSymbol<?> getContainingDeclarationSymbol() throws UninitializedPropertyAccessException {
        FirBasedSymbol<?> firBasedSymbol = this.containingDeclarationSymbol;
        if (firBasedSymbol != null) {
            return firBasedSymbol;
        }
        Intrinsics.throwUninitializedPropertyAccessException("containingDeclarationSymbol");
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final FirModuleData getModuleData() throws UninitializedPropertyAccessException {
        FirModuleData firModuleData = this.moduleData;
        if (firModuleData != null) {
            return firModuleData;
        }
        Intrinsics.throwUninitializedPropertyAccessException("moduleData");
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final FirDeclarationOrigin getOrigin() throws UninitializedPropertyAccessException {
        FirDeclarationOrigin firDeclarationOrigin = this.origin;
        if (firDeclarationOrigin != null) {
            return firDeclarationOrigin;
        }
        Intrinsics.throwUninitializedPropertyAccessException("origin");
        return null;
    }

    public final FirResolvePhase getResolvePhase() {
        return this.resolvePhase;
    }

    public final KtSourceElement getSource() {
        return this.source;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final FirReceiverParameterSymbol getSymbol() throws UninitializedPropertyAccessException {
        FirReceiverParameterSymbol firReceiverParameterSymbol = this.symbol;
        if (firReceiverParameterSymbol != null) {
            return firReceiverParameterSymbol;
        }
        Intrinsics.throwUninitializedPropertyAccessException("symbol");
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final FirTypeRef getTypeRef() throws UninitializedPropertyAccessException {
        FirTypeRef firTypeRef = this.typeRef;
        if (firTypeRef != null) {
            return firTypeRef;
        }
        Intrinsics.throwUninitializedPropertyAccessException("typeRef");
        return null;
    }

    public final boolean isBaseClassReceiver() {
        return ((Boolean) this.isBaseClassReceiver.getValue(this, $$delegatedProperties[0])).booleanValue();
    }

    public final void setAttributes(FirDeclarationAttributes firDeclarationAttributes) {
        firDeclarationAttributes.getClass();
        this.attributes = firDeclarationAttributes;
    }

    public final void setBaseClassReceiver(boolean z) {
        this.isBaseClassReceiver.setValue(this, $$delegatedProperties[0], Boolean.valueOf(z));
    }

    public final void setContainingDeclarationSymbol(FirBasedSymbol<?> firBasedSymbol) {
        firBasedSymbol.getClass();
        this.containingDeclarationSymbol = firBasedSymbol;
    }

    public final void setModuleData(FirModuleData firModuleData) {
        firModuleData.getClass();
        this.moduleData = firModuleData;
    }

    public final void setOrigin(FirDeclarationOrigin firDeclarationOrigin) {
        firDeclarationOrigin.getClass();
        this.origin = firDeclarationOrigin;
    }

    public final void setResolvePhase(FirResolvePhase firResolvePhase) {
        firResolvePhase.getClass();
        this.resolvePhase = firResolvePhase;
    }

    public final void setSource(KtSourceElement ktSourceElement) {
        this.source = ktSourceElement;
    }

    public final void setSymbol(FirReceiverParameterSymbol firReceiverParameterSymbol) {
        firReceiverParameterSymbol.getClass();
        this.symbol = firReceiverParameterSymbol;
    }

    public final void setTypeRef(FirTypeRef firTypeRef) {
        firTypeRef.getClass();
        this.typeRef = firTypeRef;
    }
}
