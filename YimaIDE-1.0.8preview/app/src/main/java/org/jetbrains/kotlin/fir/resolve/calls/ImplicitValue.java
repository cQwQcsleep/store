package org.jetbrains.kotlin.fir.resolve.calls;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirThisReceiverExpression;
import org.jetbrains.kotlin.fir.expressions.builder.FirSmartCastExpressionBuilder;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.references.FirResolvedNamedReference;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.builder.FirResolvedTypeRefBuilder;
import org.jetbrains.kotlin.types.SmartcastStability;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\f\b\u0000\u0010\u0001*\u0006\u0012\u0002\b\u00030\u00022\u00020\u0003:\u0001$B!\b\u0004\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\b\u0010\u0014\u001a\u00020\u0015H$J\u0006\u0010\u001c\u001a\u00020\u0015J\u000e\u0010\u001d\u001a\u00020\b2\u0006\u0010\u001e\u001a\u00020\u0015J\u0014\u0010\u001f\u001a\u00020 2\u0006\u0010\u0004\u001a\u00020\u0005H\u0017b\u0002\b!J\u0016\u0010\"\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010#\u001a\u00020\bH&R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0007\u001a\u00020\bX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0012\u0010\u000f\u001a\u00028\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\fR\u001b\u0010\u0016\u001a\u00020\u00158DX\u0084\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0017\u0010\u0018R\u000e\u0010\u001b\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0001\u0002%&¨\u0006'"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/ImplicitValue;", "S", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", Argument.Delimiters.none, ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "originalType", "mutable", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Z)V", "getOriginalType", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getMutable", "()Z", "boundSymbol", "getBoundSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "value", "getType", "computeOriginalExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "originalExpression", "getOriginalExpression", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "originalExpression$delegate", "Lkotlin/Lazy;", "isSmartCasted", "computeExpression", "isSameImplicitReceiverInstance", "other", "updateTypeFromSmartcast", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/ImplicitValue$ImplicitValueInternals;", "createSnapshot", "keepMutable", "ImplicitValueInternals", "Lorg/jetbrains/kotlin/fir/resolve/calls/ImplicitContextParameterValue;", "Lorg/jetbrains/kotlin/fir/resolve/calls/ImplicitReceiverValue;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class ImplicitValue<S extends FirBasedSymbol<?>> {
    private boolean isSmartCasted;
    private final boolean mutable;

    /* JADX INFO: renamed from: originalExpression$delegate, reason: from kotlin metadata */
    private final Lazy originalExpression;
    private final ConeKotlinType originalType;
    private ConeKotlinType type;

    @Retention(RetentionPolicy.RUNTIME)
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\n\u0002\u0018\u0002\b\u0087\u0002\u0018\u00002\u00020\u0001B\u0000Ê\u0001\u0002\b\u0003¨\u0006\u0002"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/ImplicitValue$ImplicitValueInternals;", Argument.Delimiters.none, "org.jetbrains.kotlin:providers", "Lkotlin/RequiresOptIn;"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public @interface ImplicitValueInternals {
    }

    private ImplicitValue(ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2, boolean z) {
        this.originalType = coneKotlinType2;
        this.mutable = z;
        this.type = coneKotlinType;
        this.originalExpression = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new ImplicitValue$originalExpression$2(this));
        this.isSmartCasted = !Intrinsics.areEqual(coneKotlinType, coneKotlinType2);
    }

    public final FirExpression computeExpression() {
        if (!this.isSmartCasted) {
            return getOriginalExpression();
        }
        FirSmartCastExpressionBuilder firSmartCastExpressionBuilder = new FirSmartCastExpressionBuilder();
        firSmartCastExpressionBuilder.setOriginalExpression(getOriginalExpression());
        FirResolvedTypeRefBuilder firResolvedTypeRefBuilder = new FirResolvedTypeRefBuilder();
        KtSourceElement source = getOriginalExpression().getSource();
        firResolvedTypeRefBuilder.setSource(source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.SmartCastedTypeRef.INSTANCE, null, 2, null) : null);
        firResolvedTypeRefBuilder.setConeType(this.type);
        firSmartCastExpressionBuilder.setSmartcastType(firResolvedTypeRefBuilder.build());
        firSmartCastExpressionBuilder.setUpperTypesFromSmartCast(CollectionsKt.listOf(this.type));
        firSmartCastExpressionBuilder.setSmartcastStability(SmartcastStability.STABLE_VALUE);
        firSmartCastExpressionBuilder.setConeTypeOrNull(this.type);
        firSmartCastExpressionBuilder.setLowerTypesFromSmartCast(CollectionsKt.emptyList());
        return firSmartCastExpressionBuilder.build();
    }

    public abstract FirExpression computeOriginalExpression();

    public abstract ImplicitValue<S> createSnapshot(boolean keepMutable);

    public abstract S getBoundSymbol();

    public final boolean getMutable() {
        return this.mutable;
    }

    public final FirExpression getOriginalExpression() {
        return (FirExpression) this.originalExpression.getValue();
    }

    public final ConeKotlinType getOriginalType() {
        return this.originalType;
    }

    public final ConeKotlinType getType() {
        return this.type;
    }

    /* JADX WARN: Code duplicated, block: B:12:0x002f  */
    public final boolean isSameImplicitReceiverInstance(FirExpression other) {
        FirBasedSymbol<?> resolvedSymbol;
        other.getClass();
        FirExpression firExpressionUnwrapSmartcastExpression = FirExpressionUtilKt.unwrapSmartcastExpression(other);
        if (firExpressionUnwrapSmartcastExpression instanceof FirThisReceiverExpression) {
            resolvedSymbol = ((FirThisReceiverExpression) firExpressionUnwrapSmartcastExpression).getCalleeReference().getBoundSymbol();
        } else if (firExpressionUnwrapSmartcastExpression instanceof FirPropertyAccessExpression) {
            FirResolvedNamedReference resolved = FirReferenceUtilsKt.getResolved(((FirPropertyAccessExpression) firExpressionUnwrapSmartcastExpression).getCalleeReference());
            resolvedSymbol = resolved != null ? resolved.getResolvedSymbol() : null;
            if (resolvedSymbol == null) {
                resolvedSymbol = null;
            }
        } else {
            resolvedSymbol = null;
        }
        return getBoundSymbol() == resolvedSymbol;
    }

    @ImplicitValueInternals
    public void updateTypeFromSmartcast(ConeKotlinType type) {
        type.getClass();
        if (Intrinsics.areEqual(type, this.type)) {
            return;
        }
        if (!this.mutable) {
            k2d.a("Cannot mutate an immutable ImplicitReceiverValue");
        } else {
            this.type = type;
            this.isSmartCasted = !Intrinsics.areEqual(type, this.originalType);
        }
    }

    public /* synthetic */ ImplicitValue(ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
        this(coneKotlinType, coneKotlinType2, z);
    }
}
