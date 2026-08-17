package org.jetbrains.kotlin.fir.expressions;

import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.DfaType;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;
import org.jetbrains.kotlin.types.SmartcastStability;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\t\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J5\u0010*\u001a\u0002H+\"\u0004\b\u0000\u0010+\"\u0004\b\u0001\u0010,2\u0012\u0010-\u001a\u000e\u0012\u0004\u0012\u0002H+\u0012\u0004\u0012\u0002H,0.2\u0006\u0010/\u001a\u0002H,H\u0016¢\u0006\u0002\u00100J3\u00101\u001a\u0002H2\"\b\b\u0000\u00102*\u000203\"\u0004\b\u0001\u0010,2\f\u00104\u001a\b\u0012\u0004\u0012\u0002H,052\u0006\u0010/\u001a\u0002H,H\u0016¢\u0006\u0002\u00106J\u0012\u00107\u001a\u0002082\b\u00109\u001a\u0004\u0018\u00010\tH&J\u0016\u0010:\u001a\u0002082\f\u0010;\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fH&J\u0010\u0010<\u001a\u0002082\u0006\u0010=\u001a\u00020\u0001H&J)\u0010>\u001a\u00020\u0000\"\u0004\b\u0000\u0010,2\f\u00104\u001a\b\u0012\u0004\u0012\u0002H,052\u0006\u0010/\u001a\u0002H,H&¢\u0006\u0002\u0010?J)\u0010@\u001a\u00020\u0000\"\u0004\b\u0000\u0010,2\f\u00104\u001a\b\u0012\u0004\u0012\u0002H,052\u0006\u0010/\u001a\u0002H,H&¢\u0006\u0002\u0010?R\u0014\u0010\u0004\u001a\u0004\u0018\u00010\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R \u0010\b\u001a\u0004\u0018\u00010\t8&X§\u0004r\u0002\b\r¢\u0006\f\u0012\u0004\b\n\u0010\u0003\u001a\u0004\b\u000b\u0010\fR\u0018\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0012\u0010\u0013\u001a\u00020\u0001X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0018\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\t0\u0017X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0018\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0017X¦\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u0019R\u0012\u0010\u001d\u001a\u00020\u001eX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u0014\u0010!\u001a\u0004\u0018\u00010\u001eX¦\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010 R\u0012\u0010#\u001a\u00020$X¦\u0004¢\u0006\u0006\u001a\u0004\b#\u0010%R\u0012\u0010&\u001a\u00020'X¦\u0004¢\u0006\u0006\u001a\u0004\b(\u0010)¨\u0006A"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/FirSmartCastExpression;", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "<init>", "()V", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "coneTypeOrNull", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getConeTypeOrNull$annotations", "getConeTypeOrNull", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Lorg/jetbrains/kotlin/fir/expressions/UnresolvedExpressionTypeAccess;", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "getAnnotations", "()Ljava/util/List;", "originalExpression", "getOriginalExpression", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "upperTypesFromSmartCast", Argument.Delimiters.none, "getUpperTypesFromSmartCast", "()Ljava/util/Collection;", "lowerTypesFromSmartCast", "Lorg/jetbrains/kotlin/fir/DfaType;", "getLowerTypesFromSmartCast", "smartcastType", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "getSmartcastType", "()Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "smartcastTypeWithoutNullableNothing", "getSmartcastTypeWithoutNullableNothing", "isStable", Argument.Delimiters.none, "()Z", "smartcastStability", "Lorg/jetbrains/kotlin/types/SmartcastStability;", "getSmartcastStability", "()Lorg/jetbrains/kotlin/types/SmartcastStability;", "accept", "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "transform", "E", "Lorg/jetbrains/kotlin/fir/FirElement;", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/FirElement;", "replaceConeTypeOrNull", Argument.Delimiters.none, "newConeTypeOrNull", "replaceAnnotations", "newAnnotations", "replaceOriginalExpression", "newOriginalExpression", "transformAnnotations", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/expressions/FirSmartCastExpression;", "transformOriginalExpression", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirSmartCastExpression extends FirExpression {
    @UnresolvedExpressionTypeAccess
    public static /* synthetic */ void getConeTypeOrNull$annotations() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.FirElement
    public <R, D> R accept(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        return visitor.visitSmartCastExpression(this, data);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public abstract List<FirAnnotation> getAnnotations();

    @Override // org.jetbrains.kotlin.fir.expressions.FirExpression
    public abstract ConeKotlinType getConeTypeOrNull();

    public abstract Collection<DfaType> getLowerTypesFromSmartCast();

    public abstract FirExpression getOriginalExpression();

    public abstract SmartcastStability getSmartcastStability();

    public abstract FirTypeRef getSmartcastType();

    public abstract FirTypeRef getSmartcastTypeWithoutNullableNothing();

    @Override // org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.FirElement
    public abstract KtSourceElement getSource();

    public abstract Collection<ConeKotlinType> getUpperTypesFromSmartCast();

    public abstract boolean isStable();

    @Override // org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public abstract void replaceAnnotations(List<? extends FirAnnotation> newAnnotations);

    @Override // org.jetbrains.kotlin.fir.expressions.FirExpression
    public abstract void replaceConeTypeOrNull(ConeKotlinType newConeTypeOrNull);

    public abstract void replaceOriginalExpression(FirExpression newOriginalExpression);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.FirElement
    public <E extends FirElement, D> E transform(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirStatement firStatementTransformSmartCastExpression = transformer.transformSmartCastExpression(this, data);
        firStatementTransformSmartCastExpression.getClass();
        return firStatementTransformSmartCastExpression;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirAnnotationContainer transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public abstract <D> FirSmartCastExpression transformAnnotations(FirTransformer<? super D> transformer, D data);

    public abstract <D> FirSmartCastExpression transformOriginalExpression(FirTransformer<? super D> transformer, D data);

    @Override // org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirExpression transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirStatement transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }
}
