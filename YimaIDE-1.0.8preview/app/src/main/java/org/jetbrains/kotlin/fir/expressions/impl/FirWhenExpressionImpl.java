package org.jetbrains.kotlin.fir.expressions.impl;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.MutableOrEmptyList;
import org.jetbrains.kotlin.fir.builder.FirBuilderDslKt;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.expressions.ExhaustivenessStatus;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvable;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.FirWhenBranch;
import org.jetbrains.kotlin.fir.expressions.FirWhenExpression;
import org.jetbrains.kotlin.fir.expressions.UnresolvedExpressionTypeAccess;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirTransformerUtilKt;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010 \n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B[\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013¢\u0006\u0004\b\u0014\u0010\u0015J5\u00103\u001a\u000204\"\u0004\b\u0000\u00105\"\u0004\b\u0001\u001062\u0012\u00107\u001a\u000e\u0012\u0004\u0012\u0002H5\u0012\u0004\u0012\u0002H6082\u0006\u00109\u001a\u0002H6H\u0016¢\u0006\u0002\u0010:J)\u0010;\u001a\u00020\u0000\"\u0004\b\u0000\u001062\f\u0010<\u001a\b\u0012\u0004\u0012\u0002H60=2\u0006\u00109\u001a\u0002H6H\u0016¢\u0006\u0002\u0010>J)\u0010?\u001a\u00020\u0000\"\u0004\b\u0000\u001062\f\u0010<\u001a\b\u0012\u0004\u0012\u0002H60=2\u0006\u00109\u001a\u0002H6H\u0016¢\u0006\u0002\u0010>J)\u0010@\u001a\u00020\u0000\"\u0004\b\u0000\u001062\f\u0010<\u001a\b\u0012\u0004\u0012\u0002H60=2\u0006\u00109\u001a\u0002H6H\u0016¢\u0006\u0002\u0010>J)\u0010A\u001a\u00020\u0000\"\u0004\b\u0000\u001062\f\u0010<\u001a\b\u0012\u0004\u0012\u0002H60=2\u0006\u00109\u001a\u0002H6H\u0016¢\u0006\u0002\u0010>J)\u0010B\u001a\u00020\u0000\"\u0004\b\u0000\u001062\f\u0010<\u001a\b\u0012\u0004\u0012\u0002H60=2\u0006\u00109\u001a\u0002H6H\u0016¢\u0006\u0002\u0010>J)\u0010C\u001a\u00020\u0000\"\u0004\b\u0000\u001062\f\u0010<\u001a\b\u0012\u0004\u0012\u0002H60=2\u0006\u00109\u001a\u0002H6H\u0016¢\u0006\u0002\u0010>J\u0012\u0010D\u001a\u0002042\b\u0010E\u001a\u0004\u0018\u00010\u0005H\u0016J\u0016\u0010F\u001a\u0002042\f\u0010G\u001a\b\u0012\u0004\u0012\u00020\b0HH\u0016J\u0010\u0010I\u001a\u0002042\u0006\u0010J\u001a\u00020\nH\u0016J\u0012\u0010K\u001a\u0002042\b\u0010L\u001a\u0004\u0018\u00010\u0011H\u0016R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R*\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0016@\u0016X\u0097\u000er\u0002\b\u001e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\"\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0096\u000e¢\u0006\u0010\n\u0002\u0010#\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001a\u0010\t\u001a\u00020\nX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010)\"\u0004\b*\u0010+R\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b,\u0010 R\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u0014\u0010\u0012\u001a\u00020\u0013X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b1\u00102¨\u0006M"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/impl/FirWhenExpressionImpl;", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "coneTypeOrNull", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "annotations", "Lorg/jetbrains/kotlin/fir/MutableOrEmptyList;", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "calleeReference", "Lorg/jetbrains/kotlin/fir/references/FirReference;", "subjectVariable", "Lorg/jetbrains/kotlin/fir/declarations/FirVariable;", "branches", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirWhenBranch;", "exhaustivenessStatus", "Lorg/jetbrains/kotlin/fir/expressions/ExhaustivenessStatus;", "usedAsExpression", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Ljava/util/List;Lorg/jetbrains/kotlin/fir/references/FirReference;Lorg/jetbrains/kotlin/fir/declarations/FirVariable;Ljava/util/List;Lorg/jetbrains/kotlin/fir/expressions/ExhaustivenessStatus;ZLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "getConeTypeOrNull$annotations", "()V", "getConeTypeOrNull", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "setConeTypeOrNull", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)V", "Lorg/jetbrains/kotlin/fir/expressions/UnresolvedExpressionTypeAccess;", "getAnnotations-5e3fPpI", "()Ljava/util/List;", "setAnnotations-GqUYU-s", "(Ljava/util/List;)V", "Ljava/util/List;", "getCalleeReference", "()Lorg/jetbrains/kotlin/fir/references/FirReference;", "setCalleeReference", "(Lorg/jetbrains/kotlin/fir/references/FirReference;)V", "getSubjectVariable", "()Lorg/jetbrains/kotlin/fir/declarations/FirVariable;", "setSubjectVariable", "(Lorg/jetbrains/kotlin/fir/declarations/FirVariable;)V", "getBranches", "getExhaustivenessStatus", "()Lorg/jetbrains/kotlin/fir/expressions/ExhaustivenessStatus;", "setExhaustivenessStatus", "(Lorg/jetbrains/kotlin/fir/expressions/ExhaustivenessStatus;)V", "getUsedAsExpression", "()Z", "acceptChildren", Argument.Delimiters.none, "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)V", "transformChildren", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/expressions/impl/FirWhenExpressionImpl;", "transformAnnotations", "transformCalleeReference", "transformSubjectVariable", "transformBranches", "transformOtherChildren", "replaceConeTypeOrNull", "newConeTypeOrNull", "replaceAnnotations", "newAnnotations", Argument.Delimiters.none, "replaceCalleeReference", "newCalleeReference", "replaceExhaustivenessStatus", "newExhaustivenessStatus", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirWhenExpressionImpl extends FirWhenExpression {
    private List<FirAnnotation> annotations;
    private final List<FirWhenBranch> branches;
    private FirReference calleeReference;
    private ConeKotlinType coneTypeOrNull;
    private ExhaustivenessStatus exhaustivenessStatus;
    private final KtSourceElement source;
    private FirVariable subjectVariable;
    private final boolean usedAsExpression;

    private FirWhenExpressionImpl(KtSourceElement ktSourceElement, ConeKotlinType coneKotlinType, List<FirAnnotation> list, FirReference firReference, FirVariable firVariable, List<FirWhenBranch> list2, ExhaustivenessStatus exhaustivenessStatus, boolean z) {
        firReference.getClass();
        list2.getClass();
        this.source = ktSourceElement;
        this.coneTypeOrNull = coneKotlinType;
        this.annotations = list;
        this.calleeReference = firReference;
        this.subjectVariable = firVariable;
        this.branches = list2;
        this.exhaustivenessStatus = exhaustivenessStatus;
        this.usedAsExpression = z;
    }

    @UnresolvedExpressionTypeAccess
    public static /* synthetic */ void getConeTypeOrNull$annotations() {
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <R, D> void acceptChildren(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        Iterator<T> it = MutableOrEmptyList.m194boximpl(m538getAnnotations5e3fPpI()).iterator();
        while (it.hasNext()) {
            ((FirAnnotation) it.next()).accept(visitor, data);
        }
        getCalleeReference().accept(visitor, data);
        FirVariable subjectVariable = getSubjectVariable();
        if (subjectVariable != null) {
            subjectVariable.accept(visitor, data);
        }
        Iterator<T> it2 = getBranches().iterator();
        while (it2.hasNext()) {
            ((FirWhenBranch) it2.next()).accept(visitor, data);
        }
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ List getAnnotations() {
        return MutableOrEmptyList.m194boximpl(m538getAnnotations5e3fPpI());
    }

    /* JADX INFO: renamed from: getAnnotations-5e3fPpI, reason: not valid java name */
    public List<FirAnnotation> m538getAnnotations5e3fPpI() {
        return this.annotations;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenExpression
    public List<FirWhenBranch> getBranches() {
        return this.branches;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenExpression, org.jetbrains.kotlin.fir.expressions.FirResolvable
    public FirReference getCalleeReference() {
        return this.calleeReference;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenExpression, org.jetbrains.kotlin.fir.expressions.FirExpression
    public ConeKotlinType getConeTypeOrNull() {
        return this.coneTypeOrNull;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenExpression
    public ExhaustivenessStatus getExhaustivenessStatus() {
        return this.exhaustivenessStatus;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.FirElement
    public KtSourceElement getSource() {
        return this.source;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenExpression
    public FirVariable getSubjectVariable() {
        return this.subjectVariable;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenExpression
    public boolean getUsedAsExpression() {
        return this.usedAsExpression;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public void replaceAnnotations(List<? extends FirAnnotation> newAnnotations) {
        newAnnotations.getClass();
        m539setAnnotationsGqUYUs(FirBuilderDslKt.toMutableOrEmptyForImmutable(newAnnotations));
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenExpression, org.jetbrains.kotlin.fir.expressions.FirResolvable
    public void replaceCalleeReference(FirReference newCalleeReference) {
        newCalleeReference.getClass();
        setCalleeReference(newCalleeReference);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenExpression, org.jetbrains.kotlin.fir.expressions.FirExpression
    public void replaceConeTypeOrNull(ConeKotlinType newConeTypeOrNull) {
        setConeTypeOrNull(newConeTypeOrNull);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenExpression
    public void replaceExhaustivenessStatus(ExhaustivenessStatus newExhaustivenessStatus) {
        setExhaustivenessStatus(newExhaustivenessStatus);
    }

    /* JADX INFO: renamed from: setAnnotations-GqUYU-s, reason: not valid java name */
    public void m539setAnnotationsGqUYUs(List<FirAnnotation> list) {
        this.annotations = list;
    }

    public void setCalleeReference(FirReference firReference) {
        firReference.getClass();
        this.calleeReference = firReference;
    }

    public void setConeTypeOrNull(ConeKotlinType coneKotlinType) {
        this.coneTypeOrNull = coneKotlinType;
    }

    public void setExhaustivenessStatus(ExhaustivenessStatus exhaustivenessStatus) {
        this.exhaustivenessStatus = exhaustivenessStatus;
    }

    public void setSubjectVariable(FirVariable firVariable) {
        this.subjectVariable = firVariable;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public <D> FirWhenExpressionImpl transformAnnotations(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirTransformerUtilKt.m709transformInplaceaLnlfrU(m538getAnnotations5e3fPpI(), transformer, data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenExpression
    public <D> FirWhenExpressionImpl transformBranches(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirTransformerUtilKt.transformInplace(getBranches(), transformer, data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenExpression, org.jetbrains.kotlin.fir.expressions.FirResolvable
    public <D> FirWhenExpressionImpl transformCalleeReference(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        setCalleeReference((FirReference) getCalleeReference().transform(transformer, data));
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <D> FirWhenExpressionImpl transformChildren(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        transformCalleeReference((FirTransformer) transformer, (Object) data);
        transformSubjectVariable((FirTransformer) transformer, (Object) data);
        transformBranches((FirTransformer) transformer, (Object) data);
        transformOtherChildren((FirTransformer) transformer, (Object) data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenExpression
    public <D> FirWhenExpressionImpl transformOtherChildren(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        transformAnnotations((FirTransformer) transformer, (Object) data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenExpression
    public <D> FirWhenExpressionImpl transformSubjectVariable(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirVariable subjectVariable = getSubjectVariable();
        setSubjectVariable(subjectVariable != null ? (FirVariable) subjectVariable.transform(transformer, data) : null);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenExpression
    public /* bridge */ /* synthetic */ FirWhenExpression transformOtherChildren(FirTransformer firTransformer, Object obj) {
        return transformOtherChildren((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirExpression transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenExpression
    public /* bridge */ /* synthetic */ FirWhenExpression transformBranches(FirTransformer firTransformer, Object obj) {
        return transformBranches((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirStatement transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirWhenExpression transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirAnnotationContainer transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public /* bridge */ /* synthetic */ FirElement transformChildren(FirTransformer firTransformer, Object obj) {
        return transformChildren((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenExpression, org.jetbrains.kotlin.fir.expressions.FirResolvable
    public /* bridge */ /* synthetic */ FirWhenExpression transformCalleeReference(FirTransformer firTransformer, Object obj) {
        return transformCalleeReference((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenExpression, org.jetbrains.kotlin.fir.expressions.FirResolvable
    public /* bridge */ /* synthetic */ FirResolvable transformCalleeReference(FirTransformer firTransformer, Object obj) {
        return transformCalleeReference((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenExpression
    public /* bridge */ /* synthetic */ FirWhenExpression transformSubjectVariable(FirTransformer firTransformer, Object obj) {
        return transformSubjectVariable((FirTransformer<? super Object>) firTransformer, obj);
    }

    public /* synthetic */ FirWhenExpressionImpl(KtSourceElement ktSourceElement, ConeKotlinType coneKotlinType, List list, FirReference firReference, FirVariable firVariable, List list2, ExhaustivenessStatus exhaustivenessStatus, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
        this(ktSourceElement, coneKotlinType, list, firReference, firVariable, list2, exhaustivenessStatus, z);
    }
}
