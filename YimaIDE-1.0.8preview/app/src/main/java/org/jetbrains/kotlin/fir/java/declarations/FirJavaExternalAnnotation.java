package org.jetbrains.kotlin.fir.java.declarations;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.annotations.AnnotationUseSiteTarget;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirImplementationDetail;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationArgumentMapping;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0011\u0018\u00002\u00020\u0001B\u001d\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u0002\b\b¢\u0006\u0004\b\u0006\u0010\u0007J5\u0010&\u001a\u00020'\"\u0004\b\u0000\u0010(\"\u0004\b\u0001\u0010)2\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u0002H(\u0012\u0004\u0012\u0002H)0+2\u0006\u0010,\u001a\u0002H)H\u0016¢\u0006\u0002\u0010-J)\u0010.\u001a\u00020\u0000\"\u0004\b\u0000\u0010)2\f\u0010/\u001a\b\u0012\u0004\u0012\u0002H)002\u0006\u0010,\u001a\u0002H)H\u0016¢\u0006\u0002\u00101J)\u00102\u001a\u00020\u0000\"\u0004\b\u0000\u0010)2\f\u0010/\u001a\b\u0012\u0004\u0012\u0002H)002\u0006\u0010,\u001a\u0002H)H\u0016¢\u0006\u0002\u00101J)\u00103\u001a\u00020\u0000\"\u0004\b\u0000\u0010)2\f\u0010/\u001a\b\u0012\u0004\u0012\u0002H)002\u0006\u0010,\u001a\u0002H)H\u0016¢\u0006\u0002\u00101J)\u00104\u001a\u00020\u0000\"\u0004\b\u0000\u0010)2\f\u0010/\u001a\b\u0012\u0004\u0012\u0002H)002\u0006\u0010,\u001a\u0002H)H\u0016¢\u0006\u0002\u00101J\u0012\u00105\u001a\u00020'2\b\u00106\u001a\u0004\u0018\u00010\u001fH\u0016J\u0016\u00107\u001a\u00020'2\f\u00108\u001a\b\u0012\u0004\u0012\u00020\u00010\u001aH\u0016J\u0012\u00109\u001a\u00020'2\b\u0010:\u001a\u0004\u0018\u00010\u0012H\u0016J\u0010\u0010;\u001a\u00020'2\u0006\u0010<\u001a\u00020\u0003H\u0016J\u0010\u0010=\u001a\u00020'2\u0006\u0010>\u001a\u00020\u0005H\u0016J\u0016\u0010?\u001a\u00020'2\f\u0010@\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aH\u0016R\u001a\u0010\u0002\u001a\u00020\u0003X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0004\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0016\u0010\u0011\u001a\u0004\u0018\u00010\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0016\u0010\u0015\u001a\u0004\u0018\u00010\u00168VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u001c\u0010\u001e\u001a\u0004\u0018\u00010\u001f8VX\u0096\u0004¢\u0006\f\u0012\u0004\b \u0010!\u001a\u0004\b\"\u0010#R\u001a\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00010\u001a8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b%\u0010\u001d¨\u0006A"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaExternalAnnotation;", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "annotationTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "argumentMapping", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationArgumentMapping;", "<init>", "(Lorg/jetbrains/kotlin/fir/types/FirTypeRef;Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationArgumentMapping;)V", "Lorg/jetbrains/kotlin/fir/FirImplementationDetail;", "getAnnotationTypeRef", "()Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "setAnnotationTypeRef", "(Lorg/jetbrains/kotlin/fir/types/FirTypeRef;)V", "getArgumentMapping", "()Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationArgumentMapping;", "setArgumentMapping", "(Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationArgumentMapping;)V", "useSiteTarget", "Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationUseSiteTarget;", "getUseSiteTarget", "()Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationUseSiteTarget;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "typeArguments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/FirTypeProjection;", "getTypeArguments", "()Ljava/util/List;", "coneTypeOrNull", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getConeTypeOrNull$annotations", "()V", "getConeTypeOrNull", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "annotations", "getAnnotations", "acceptChildren", Argument.Delimiters.none, "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)V", "transformChildren", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaExternalAnnotation;", "transformAnnotations", "transformAnnotationTypeRef", "transformTypeArguments", "replaceConeTypeOrNull", "newConeTypeOrNull", "replaceAnnotations", "newAnnotations", "replaceUseSiteTarget", "newUseSiteTarget", "replaceAnnotationTypeRef", "newAnnotationTypeRef", "replaceArgumentMapping", "newArgumentMapping", "replaceTypeArguments", "newTypeArguments", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJavaExternalAnnotation extends FirAnnotation {
    private FirTypeRef annotationTypeRef;
    private FirAnnotationArgumentMapping argumentMapping;

    @FirImplementationDetail
    public FirJavaExternalAnnotation(FirTypeRef firTypeRef, FirAnnotationArgumentMapping firAnnotationArgumentMapping) {
        firTypeRef.getClass();
        firAnnotationArgumentMapping.getClass();
        this.annotationTypeRef = firTypeRef;
        this.argumentMapping = firAnnotationArgumentMapping;
    }

    public static /* synthetic */ void getConeTypeOrNull$annotations() {
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <R, D> void acceptChildren(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        getAnnotationTypeRef().accept(visitor, data);
        getArgumentMapping().accept(visitor, data);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotation
    public FirTypeRef getAnnotationTypeRef() {
        return this.annotationTypeRef;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotation, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public List<FirAnnotation> getAnnotations() {
        return CollectionsKt.emptyList();
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotation
    public FirAnnotationArgumentMapping getArgumentMapping() {
        return this.argumentMapping;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotation, org.jetbrains.kotlin.fir.expressions.FirExpression
    public ConeKotlinType getConeTypeOrNull() {
        return FirTypeUtilsKt.getConeTypeOrNull(getAnnotationTypeRef());
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotation, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.FirElement
    public KtSourceElement getSource() {
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotation
    public List<FirTypeProjection> getTypeArguments() {
        return CollectionsKt.emptyList();
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotation
    public AnnotationUseSiteTarget getUseSiteTarget() {
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotation
    public void replaceAnnotationTypeRef(FirTypeRef newAnnotationTypeRef) {
        newAnnotationTypeRef.getClass();
        setAnnotationTypeRef(newAnnotationTypeRef);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotation, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public void replaceAnnotations(List<? extends FirAnnotation> newAnnotations) {
        newAnnotations.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotation
    public void replaceArgumentMapping(FirAnnotationArgumentMapping newArgumentMapping) {
        newArgumentMapping.getClass();
        setArgumentMapping(newArgumentMapping);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotation, org.jetbrains.kotlin.fir.expressions.FirExpression
    public void replaceConeTypeOrNull(ConeKotlinType newConeTypeOrNull) {
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotation
    public void replaceTypeArguments(List<? extends FirTypeProjection> newTypeArguments) {
        newTypeArguments.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotation
    public void replaceUseSiteTarget(AnnotationUseSiteTarget newUseSiteTarget) {
    }

    public void setAnnotationTypeRef(FirTypeRef firTypeRef) {
        firTypeRef.getClass();
        this.annotationTypeRef = firTypeRef;
    }

    public void setArgumentMapping(FirAnnotationArgumentMapping firAnnotationArgumentMapping) {
        firAnnotationArgumentMapping.getClass();
        this.argumentMapping = firAnnotationArgumentMapping;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotation
    public <D> FirJavaExternalAnnotation transformAnnotationTypeRef(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        setAnnotationTypeRef((FirTypeRef) getAnnotationTypeRef().transform(transformer, data));
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotation, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirAnnotationContainer transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <D> FirJavaExternalAnnotation transformChildren(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        transformAnnotationTypeRef((FirTransformer) transformer, (Object) data);
        setArgumentMapping((FirAnnotationArgumentMapping) getArgumentMapping().transform(transformer, data));
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotation
    public /* bridge */ /* synthetic */ FirAnnotation transformTypeArguments(FirTransformer firTransformer, Object obj) {
        return transformTypeArguments((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotation, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public <D> FirJavaExternalAnnotation transformAnnotations(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotation
    public <D> FirJavaExternalAnnotation transformTypeArguments(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotation, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirAnnotation transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotation, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirExpression transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotation, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirStatement transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirAnnotation
    public /* bridge */ /* synthetic */ FirAnnotation transformAnnotationTypeRef(FirTransformer firTransformer, Object obj) {
        return transformAnnotationTypeRef((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public /* bridge */ /* synthetic */ FirElement transformChildren(FirTransformer firTransformer, Object obj) {
        return transformChildren((FirTransformer<? super Object>) firTransformer, obj);
    }
}
