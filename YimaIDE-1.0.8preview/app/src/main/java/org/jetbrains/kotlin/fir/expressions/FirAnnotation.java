package org.jetbrains.kotlin.fir.expressions;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.annotations.AnnotationUseSiteTarget;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0010\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J5\u0010!\u001a\u0002H\"\"\u0004\b\u0000\u0010\"\"\u0004\b\u0001\u0010#2\u0012\u0010$\u001a\u000e\u0012\u0004\u0012\u0002H\"\u0012\u0004\u0012\u0002H#0%2\u0006\u0010&\u001a\u0002H#H\u0016¢\u0006\u0002\u0010'J3\u0010(\u001a\u0002H)\"\b\b\u0000\u0010)*\u00020*\"\u0004\b\u0001\u0010#2\f\u0010+\u001a\b\u0012\u0004\u0012\u0002H#0,2\u0006\u0010&\u001a\u0002H#H\u0016¢\u0006\u0002\u0010-J\u0012\u0010.\u001a\u00020/2\b\u00100\u001a\u0004\u0018\u00010\tH&J\u0016\u00101\u001a\u00020/2\f\u00102\u001a\b\u0012\u0004\u0012\u00020\u00000\u000fH&J\u0012\u00103\u001a\u00020/2\b\u00104\u001a\u0004\u0018\u00010\u0013H&J\u0010\u00105\u001a\u00020/2\u0006\u00106\u001a\u00020\u0017H&J\u0010\u00107\u001a\u00020/2\u0006\u00108\u001a\u00020\u001bH&J\u0016\u00109\u001a\u00020/2\f\u0010:\u001a\b\u0012\u0004\u0012\u00020\u001f0\u000fH&J)\u0010;\u001a\u00020\u0000\"\u0004\b\u0000\u0010#2\f\u0010+\u001a\b\u0012\u0004\u0012\u0002H#0,2\u0006\u0010&\u001a\u0002H#H&¢\u0006\u0002\u0010<J)\u0010=\u001a\u00020\u0000\"\u0004\b\u0000\u0010#2\f\u0010+\u001a\b\u0012\u0004\u0012\u0002H#0,2\u0006\u0010&\u001a\u0002H#H&¢\u0006\u0002\u0010<J)\u0010>\u001a\u00020\u0000\"\u0004\b\u0000\u0010#2\f\u0010+\u001a\b\u0012\u0004\u0012\u0002H#0,2\u0006\u0010&\u001a\u0002H#H&¢\u0006\u0002\u0010<R\u0014\u0010\u0004\u001a\u0004\u0018\u00010\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R \u0010\b\u001a\u0004\u0018\u00010\t8&X§\u0004r\u0002\b\r¢\u0006\f\u0012\u0004\b\n\u0010\u0003\u001a\u0004\b\u000b\u0010\fR\u0018\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00000\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u0004\u0018\u00010\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0012\u0010\u0016\u001a\u00020\u0017X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0012\u0010\u001a\u001a\u00020\u001bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0018\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b \u0010\u0011¨\u0006?"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "<init>", "()V", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "coneTypeOrNull", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getConeTypeOrNull$annotations", "getConeTypeOrNull", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Lorg/jetbrains/kotlin/fir/expressions/UnresolvedExpressionTypeAccess;", "annotations", Argument.Delimiters.none, "getAnnotations", "()Ljava/util/List;", "useSiteTarget", "Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationUseSiteTarget;", "getUseSiteTarget", "()Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationUseSiteTarget;", "annotationTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "getAnnotationTypeRef", "()Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "argumentMapping", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationArgumentMapping;", "getArgumentMapping", "()Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationArgumentMapping;", "typeArguments", "Lorg/jetbrains/kotlin/fir/types/FirTypeProjection;", "getTypeArguments", "accept", "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "transform", "E", "Lorg/jetbrains/kotlin/fir/FirElement;", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/FirElement;", "replaceConeTypeOrNull", Argument.Delimiters.none, "newConeTypeOrNull", "replaceAnnotations", "newAnnotations", "replaceUseSiteTarget", "newUseSiteTarget", "replaceAnnotationTypeRef", "newAnnotationTypeRef", "replaceArgumentMapping", "newArgumentMapping", "replaceTypeArguments", "newTypeArguments", "transformAnnotations", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "transformAnnotationTypeRef", "transformTypeArguments", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirAnnotation extends FirExpression {
    @UnresolvedExpressionTypeAccess
    public static /* synthetic */ void getConeTypeOrNull$annotations() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.FirElement
    public <R, D> R accept(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        return visitor.visitAnnotation(this, data);
    }

    public abstract FirTypeRef getAnnotationTypeRef();

    @Override // org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public abstract List<FirAnnotation> getAnnotations();

    public abstract FirAnnotationArgumentMapping getArgumentMapping();

    @Override // org.jetbrains.kotlin.fir.expressions.FirExpression
    public abstract ConeKotlinType getConeTypeOrNull();

    @Override // org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.FirElement
    public abstract KtSourceElement getSource();

    public abstract List<FirTypeProjection> getTypeArguments();

    public abstract AnnotationUseSiteTarget getUseSiteTarget();

    public abstract void replaceAnnotationTypeRef(FirTypeRef newAnnotationTypeRef);

    @Override // org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public abstract void replaceAnnotations(List<? extends FirAnnotation> newAnnotations);

    public abstract void replaceArgumentMapping(FirAnnotationArgumentMapping newArgumentMapping);

    @Override // org.jetbrains.kotlin.fir.expressions.FirExpression
    public abstract void replaceConeTypeOrNull(ConeKotlinType newConeTypeOrNull);

    public abstract void replaceTypeArguments(List<? extends FirTypeProjection> newTypeArguments);

    public abstract void replaceUseSiteTarget(AnnotationUseSiteTarget newUseSiteTarget);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.FirElement
    public <E extends FirElement, D> E transform(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirStatement firStatementTransformAnnotation = transformer.transformAnnotation(this, data);
        firStatementTransformAnnotation.getClass();
        return firStatementTransformAnnotation;
    }

    public abstract <D> FirAnnotation transformAnnotationTypeRef(FirTransformer<? super D> transformer, D data);

    @Override // org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirAnnotationContainer transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public abstract <D> FirAnnotation transformAnnotations(FirTransformer<? super D> transformer, D data);

    public abstract <D> FirAnnotation transformTypeArguments(FirTransformer<? super D> transformer, D data);

    @Override // org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirExpression transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirStatement transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }
}
