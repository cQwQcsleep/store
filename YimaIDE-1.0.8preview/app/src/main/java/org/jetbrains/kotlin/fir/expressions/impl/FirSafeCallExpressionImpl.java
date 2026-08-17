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
import org.jetbrains.kotlin.fir.FirExpressionRef;
import org.jetbrains.kotlin.fir.MutableOrEmptyList;
import org.jetbrains.kotlin.fir.builder.FirBuilderDslKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirCheckedSafeCallSubject;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirSafeCallExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.UnresolvedExpressionTypeAccess;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirTransformerUtilKt;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010 \n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001BG\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0004\b\u0010\u0010\u0011J5\u0010*\u001a\u00020+\"\u0004\b\u0000\u0010,\"\u0004\b\u0001\u0010-2\u0012\u0010.\u001a\u000e\u0012\u0004\u0012\u0002H,\u0012\u0004\u0012\u0002H-0/2\u0006\u00100\u001a\u0002H-H\u0016¢\u0006\u0002\u00101J)\u00102\u001a\u00020\u0000\"\u0004\b\u0000\u0010-2\f\u00103\u001a\b\u0012\u0004\u0012\u0002H-042\u0006\u00100\u001a\u0002H-H\u0016¢\u0006\u0002\u00105J)\u00106\u001a\u00020\u0000\"\u0004\b\u0000\u0010-2\f\u00103\u001a\b\u0012\u0004\u0012\u0002H-042\u0006\u00100\u001a\u0002H-H\u0016¢\u0006\u0002\u00105J)\u00107\u001a\u00020\u0000\"\u0004\b\u0000\u0010-2\f\u00103\u001a\b\u0012\u0004\u0012\u0002H-042\u0006\u00100\u001a\u0002H-H\u0016¢\u0006\u0002\u00105J)\u00108\u001a\u00020\u0000\"\u0004\b\u0000\u0010-2\f\u00103\u001a\b\u0012\u0004\u0012\u0002H-042\u0006\u00100\u001a\u0002H-H\u0016¢\u0006\u0002\u00105J\u0012\u00109\u001a\u00020+2\b\u0010:\u001a\u0004\u0018\u00010\u0005H\u0016J\u0016\u0010;\u001a\u00020+2\f\u0010<\u001a\b\u0012\u0004\u0012\u00020\b0=H\u0016J\u0010\u0010>\u001a\u00020+2\u0006\u0010?\u001a\u00020\u000fH\u0016R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R*\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0016@\u0016X\u0097\u000er\u0002\b\u001a¢\u0006\u0014\n\u0000\u0012\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\"\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0096\u000e¢\u0006\u0010\n\u0002\u0010\u001f\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001a\u0010\t\u001a\u00020\nX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u001a\u0010\u000e\u001a\u00020\u000fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)¨\u0006@"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/impl/FirSafeCallExpressionImpl;", "Lorg/jetbrains/kotlin/fir/expressions/FirSafeCallExpression;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "coneTypeOrNull", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "annotations", "Lorg/jetbrains/kotlin/fir/MutableOrEmptyList;", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "receiver", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "checkedSubjectRef", "Lorg/jetbrains/kotlin/fir/FirExpressionRef;", "Lorg/jetbrains/kotlin/fir/expressions/FirCheckedSafeCallSubject;", "selector", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "<init>", "(Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Ljava/util/List;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/fir/FirExpressionRef;Lorg/jetbrains/kotlin/fir/expressions/FirStatement;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "getConeTypeOrNull$annotations", "()V", "getConeTypeOrNull", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "setConeTypeOrNull", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)V", "Lorg/jetbrains/kotlin/fir/expressions/UnresolvedExpressionTypeAccess;", "getAnnotations-5e3fPpI", "()Ljava/util/List;", "setAnnotations-GqUYU-s", "(Ljava/util/List;)V", "Ljava/util/List;", "getReceiver", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "setReceiver", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)V", "getCheckedSubjectRef", "()Lorg/jetbrains/kotlin/fir/FirExpressionRef;", "getSelector", "()Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "setSelector", "(Lorg/jetbrains/kotlin/fir/expressions/FirStatement;)V", "acceptChildren", Argument.Delimiters.none, "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)V", "transformChildren", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/expressions/impl/FirSafeCallExpressionImpl;", "transformAnnotations", "transformReceiver", "transformSelector", "replaceConeTypeOrNull", "newConeTypeOrNull", "replaceAnnotations", "newAnnotations", Argument.Delimiters.none, "replaceSelector", "newSelector", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirSafeCallExpressionImpl extends FirSafeCallExpression {
    private List<FirAnnotation> annotations;
    private final FirExpressionRef<FirCheckedSafeCallSubject> checkedSubjectRef;
    private ConeKotlinType coneTypeOrNull;
    private FirExpression receiver;
    private FirStatement selector;
    private final KtSourceElement source;

    private FirSafeCallExpressionImpl(KtSourceElement ktSourceElement, ConeKotlinType coneKotlinType, List<FirAnnotation> list, FirExpression firExpression, FirExpressionRef<FirCheckedSafeCallSubject> firExpressionRef, FirStatement firStatement) {
        firExpression.getClass();
        firExpressionRef.getClass();
        firStatement.getClass();
        this.source = ktSourceElement;
        this.coneTypeOrNull = coneKotlinType;
        this.annotations = list;
        this.receiver = firExpression;
        this.checkedSubjectRef = firExpressionRef;
        this.selector = firStatement;
    }

    @UnresolvedExpressionTypeAccess
    public static /* synthetic */ void getConeTypeOrNull$annotations() {
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <R, D> void acceptChildren(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        Iterator<T> it = MutableOrEmptyList.m194boximpl(m504getAnnotations5e3fPpI()).iterator();
        while (it.hasNext()) {
            ((FirAnnotation) it.next()).accept(visitor, data);
        }
        getReceiver().accept(visitor, data);
        getSelector().accept(visitor, data);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirSafeCallExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ List getAnnotations() {
        return MutableOrEmptyList.m194boximpl(m504getAnnotations5e3fPpI());
    }

    /* JADX INFO: renamed from: getAnnotations-5e3fPpI, reason: not valid java name */
    public List<FirAnnotation> m504getAnnotations5e3fPpI() {
        return this.annotations;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirSafeCallExpression
    public FirExpressionRef<FirCheckedSafeCallSubject> getCheckedSubjectRef() {
        return this.checkedSubjectRef;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirSafeCallExpression, org.jetbrains.kotlin.fir.expressions.FirExpression
    public ConeKotlinType getConeTypeOrNull() {
        return this.coneTypeOrNull;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirSafeCallExpression
    public FirExpression getReceiver() {
        return this.receiver;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirSafeCallExpression
    public FirStatement getSelector() {
        return this.selector;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirSafeCallExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.FirElement
    public KtSourceElement getSource() {
        return this.source;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirSafeCallExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public void replaceAnnotations(List<? extends FirAnnotation> newAnnotations) {
        newAnnotations.getClass();
        m505setAnnotationsGqUYUs(FirBuilderDslKt.toMutableOrEmptyForImmutable(newAnnotations));
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirSafeCallExpression, org.jetbrains.kotlin.fir.expressions.FirExpression
    public void replaceConeTypeOrNull(ConeKotlinType newConeTypeOrNull) {
        setConeTypeOrNull(newConeTypeOrNull);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirSafeCallExpression
    public void replaceSelector(FirStatement newSelector) {
        newSelector.getClass();
        setSelector(newSelector);
    }

    /* JADX INFO: renamed from: setAnnotations-GqUYU-s, reason: not valid java name */
    public void m505setAnnotationsGqUYUs(List<FirAnnotation> list) {
        this.annotations = list;
    }

    public void setConeTypeOrNull(ConeKotlinType coneKotlinType) {
        this.coneTypeOrNull = coneKotlinType;
    }

    public void setReceiver(FirExpression firExpression) {
        firExpression.getClass();
        this.receiver = firExpression;
    }

    public void setSelector(FirStatement firStatement) {
        firStatement.getClass();
        this.selector = firStatement;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirSafeCallExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public <D> FirSafeCallExpressionImpl transformAnnotations(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirTransformerUtilKt.m709transformInplaceaLnlfrU(m504getAnnotations5e3fPpI(), transformer, data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <D> FirSafeCallExpressionImpl transformChildren(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        transformAnnotations((FirTransformer) transformer, (Object) data);
        transformReceiver((FirTransformer) transformer, (Object) data);
        transformSelector((FirTransformer) transformer, (Object) data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirSafeCallExpression
    public <D> FirSafeCallExpressionImpl transformReceiver(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        setReceiver((FirExpression) getReceiver().transform(transformer, data));
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirSafeCallExpression
    public <D> FirSafeCallExpressionImpl transformSelector(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        setSelector((FirStatement) getSelector().transform(transformer, data));
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirSafeCallExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirExpression transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirSafeCallExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirSafeCallExpression transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirSafeCallExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirStatement transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public /* bridge */ /* synthetic */ FirElement transformChildren(FirTransformer firTransformer, Object obj) {
        return transformChildren((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirSafeCallExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirAnnotationContainer transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirSafeCallExpression
    public /* bridge */ /* synthetic */ FirSafeCallExpression transformReceiver(FirTransformer firTransformer, Object obj) {
        return transformReceiver((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirSafeCallExpression
    public /* bridge */ /* synthetic */ FirSafeCallExpression transformSelector(FirTransformer firTransformer, Object obj) {
        return transformSelector((FirTransformer<? super Object>) firTransformer, obj);
    }

    public /* synthetic */ FirSafeCallExpressionImpl(KtSourceElement ktSourceElement, ConeKotlinType coneKotlinType, List list, FirExpression firExpression, FirExpressionRef firExpressionRef, FirStatement firStatement, DefaultConstructorMarker defaultConstructorMarker) {
        this(ktSourceElement, coneKotlinType, list, firExpression, firExpressionRef, firStatement);
    }
}
