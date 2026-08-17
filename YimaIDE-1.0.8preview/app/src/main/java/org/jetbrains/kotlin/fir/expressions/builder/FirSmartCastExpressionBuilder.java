package org.jetbrains.kotlin.fir.expressions.builder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.state.InlineClassManglingUtilsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.DfaType;
import org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder;
import org.jetbrains.kotlin.fir.builder.FirBuilderDsl;
import org.jetbrains.kotlin.fir.builder.FirBuilderDslKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression;
import org.jetbrains.kotlin.fir.expressions.impl.FirSmartCastExpressionImpl;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.types.SmartcastStability;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@FirBuilderDsl
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u001e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010/\u001a\u000200H\u0016R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R \u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00060\u0017X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR \u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u0017X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0019\"\u0004\b\u001f\u0010\u001bR\u001a\u0010 \u001a\u00020!X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001c\u0010&\u001a\u0004\u0018\u00010!X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010#\"\u0004\b(\u0010%R\u001a\u0010)\u001a\u00020*X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.RH\u00103\u001a\u0004\u0018\u0001022\b\u00101\u001a\u0004\u0018\u0001028V@VX\u0097\u000er\u0018\b9\u0012\b\b:\u0012\u0004\b\b(;\u0012\n\b<\u0012\u0006\b\n0=8>¢\u0006\u0012\u0012\u0004\b4\u0010\u0004\u001a\u0004\b5\u00106\"\u0004\b7\u00108Ê\u0001\u0002\b@¨\u0006?"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/builder/FirSmartCastExpressionBuilder;", "Lorg/jetbrains/kotlin/fir/builder/FirAnnotationContainerBuilder;", "Lorg/jetbrains/kotlin/fir/expressions/builder/FirExpressionBuilder;", "<init>", "()V", "coneTypeOrNull", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getConeTypeOrNull", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "setConeTypeOrNull", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)V", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "getAnnotations", "()Ljava/util/List;", "originalExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "getOriginalExpression", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "setOriginalExpression", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)V", "upperTypesFromSmartCast", Argument.Delimiters.none, "getUpperTypesFromSmartCast", "()Ljava/util/Collection;", "setUpperTypesFromSmartCast", "(Ljava/util/Collection;)V", "lowerTypesFromSmartCast", "Lorg/jetbrains/kotlin/fir/DfaType;", "getLowerTypesFromSmartCast", "setLowerTypesFromSmartCast", "smartcastType", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "getSmartcastType", "()Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "setSmartcastType", "(Lorg/jetbrains/kotlin/fir/types/FirTypeRef;)V", "smartcastTypeWithoutNullableNothing", "getSmartcastTypeWithoutNullableNothing", "setSmartcastTypeWithoutNullableNothing", "smartcastStability", "Lorg/jetbrains/kotlin/types/SmartcastStability;", "getSmartcastStability", "()Lorg/jetbrains/kotlin/types/SmartcastStability;", "setSmartcastStability", "(Lorg/jetbrains/kotlin/types/SmartcastStability;)V", "build", "Lorg/jetbrains/kotlin/fir/expressions/FirSmartCastExpression;", InlineClassManglingUtilsKt.NOT_INLINE_CLASS_PARAMETER_PLACEHOLDER, "Lorg/jetbrains/kotlin/KtSourceElement;", "source", "getSource$annotations", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "setSource", "(Lorg/jetbrains/kotlin/KtSourceElement;)V", "Lkotlin/Deprecated;", "message", "Modification of 'source' has no impact for FirSmartCastExpressionBuilder", "level", "Lkotlin/DeprecationLevel;", "HIDDEN", "org.jetbrains.kotlin:tree", "Lorg/jetbrains/kotlin/fir/builder/FirBuilderDsl;"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirSmartCastExpressionBuilder implements FirAnnotationContainerBuilder, FirExpressionBuilder {
    private final List<FirAnnotation> annotations = new ArrayList();
    private ConeKotlinType coneTypeOrNull;
    public Collection<? extends DfaType> lowerTypesFromSmartCast;
    public FirExpression originalExpression;
    public SmartcastStability smartcastStability;
    public FirTypeRef smartcastType;
    private FirTypeRef smartcastTypeWithoutNullableNothing;
    public Collection<? extends ConeKotlinType> upperTypesFromSmartCast;

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Modification of 'source' has no impact for FirSmartCastExpressionBuilder")
    public static /* synthetic */ void getSource$annotations() {
    }

    @Override // org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder
    /* JADX INFO: renamed from: build */
    public FirSmartCastExpression mo288build() {
        return new FirSmartCastExpressionImpl(getConeTypeOrNull(), FirBuilderDslKt.toMutableOrEmpty(getAnnotations()), getOriginalExpression(), getUpperTypesFromSmartCast(), getLowerTypesFromSmartCast(), getSmartcastType(), this.smartcastTypeWithoutNullableNothing, getSmartcastStability(), null);
    }

    @Override // org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public List<FirAnnotation> getAnnotations() {
        return this.annotations;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirExpressionBuilder
    public ConeKotlinType getConeTypeOrNull() {
        return this.coneTypeOrNull;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final Collection<DfaType> getLowerTypesFromSmartCast() throws UninitializedPropertyAccessException {
        Collection collection = this.lowerTypesFromSmartCast;
        if (collection != null) {
            return collection;
        }
        Intrinsics.throwUninitializedPropertyAccessException("lowerTypesFromSmartCast");
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final FirExpression getOriginalExpression() throws UninitializedPropertyAccessException {
        FirExpression firExpression = this.originalExpression;
        if (firExpression != null) {
            return firExpression;
        }
        Intrinsics.throwUninitializedPropertyAccessException("originalExpression");
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final SmartcastStability getSmartcastStability() throws UninitializedPropertyAccessException {
        SmartcastStability smartcastStability = this.smartcastStability;
        if (smartcastStability != null) {
            return smartcastStability;
        }
        Intrinsics.throwUninitializedPropertyAccessException("smartcastStability");
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final FirTypeRef getSmartcastType() throws UninitializedPropertyAccessException {
        FirTypeRef firTypeRef = this.smartcastType;
        if (firTypeRef != null) {
            return firTypeRef;
        }
        Intrinsics.throwUninitializedPropertyAccessException("smartcastType");
        return null;
    }

    public final FirTypeRef getSmartcastTypeWithoutNullableNothing() {
        return this.smartcastTypeWithoutNullableNothing;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirExpressionBuilder
    public /* synthetic */ KtSourceElement getSource() {
        throw new IllegalStateException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final Collection<ConeKotlinType> getUpperTypesFromSmartCast() throws UninitializedPropertyAccessException {
        Collection<? extends ConeKotlinType> collection = this.upperTypesFromSmartCast;
        if (collection != null) {
            return collection;
        }
        Intrinsics.throwUninitializedPropertyAccessException("upperTypesFromSmartCast");
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirExpressionBuilder
    public void setConeTypeOrNull(ConeKotlinType coneKotlinType) {
        this.coneTypeOrNull = coneKotlinType;
    }

    public final void setLowerTypesFromSmartCast(Collection<? extends DfaType> collection) {
        collection.getClass();
        this.lowerTypesFromSmartCast = collection;
    }

    public final void setOriginalExpression(FirExpression firExpression) {
        firExpression.getClass();
        this.originalExpression = firExpression;
    }

    public final void setSmartcastStability(SmartcastStability smartcastStability) {
        smartcastStability.getClass();
        this.smartcastStability = smartcastStability;
    }

    public final void setSmartcastType(FirTypeRef firTypeRef) {
        firTypeRef.getClass();
        this.smartcastType = firTypeRef;
    }

    public final void setSmartcastTypeWithoutNullableNothing(FirTypeRef firTypeRef) {
        this.smartcastTypeWithoutNullableNothing = firTypeRef;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirExpressionBuilder
    public /* synthetic */ void setSource(KtSourceElement ktSourceElement) {
        throw new IllegalStateException();
    }

    public final void setUpperTypesFromSmartCast(Collection<? extends ConeKotlinType> collection) {
        collection.getClass();
        this.upperTypesFromSmartCast = collection;
    }
}
