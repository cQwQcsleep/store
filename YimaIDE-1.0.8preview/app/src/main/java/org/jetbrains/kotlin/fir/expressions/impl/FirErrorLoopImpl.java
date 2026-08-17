package org.jetbrains.kotlin.fir.expressions.impl;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirLabel;
import org.jetbrains.kotlin.fir.MutableOrEmptyList;
import org.jetbrains.kotlin.fir.builder.FirBuilderDslKt;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.ConeUnreportedDuplicateDiagnostic;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirErrorLoop;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirLoop;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirTransformerUtilKt;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0000\b\u0000\u0018\u00002\u00020\u0001B1\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ5\u0010&\u001a\u00020'\"\u0004\b\u0000\u0010(\"\u0004\b\u0001\u0010)2\u0012\u0010*\u001a\u000e\u0012\u0004\u0012\u0002H(\u0012\u0004\u0012\u0002H)0+2\u0006\u0010,\u001a\u0002H)H\u0016¢\u0006\u0002\u0010-J)\u0010.\u001a\u00020\u0000\"\u0004\b\u0000\u0010)2\f\u0010/\u001a\b\u0012\u0004\u0012\u0002H)002\u0006\u0010,\u001a\u0002H)H\u0016¢\u0006\u0002\u00101J)\u00102\u001a\u00020\u0000\"\u0004\b\u0000\u0010)2\f\u0010/\u001a\b\u0012\u0004\u0012\u0002H)002\u0006\u0010,\u001a\u0002H)H\u0016¢\u0006\u0002\u00101J)\u00103\u001a\u00020\u0000\"\u0004\b\u0000\u0010)2\f\u0010/\u001a\b\u0012\u0004\u0012\u0002H)002\u0006\u0010,\u001a\u0002H)H\u0016¢\u0006\u0002\u00101J)\u00104\u001a\u00020\u0000\"\u0004\b\u0000\u0010)2\f\u0010/\u001a\b\u0012\u0004\u0012\u0002H)002\u0006\u0010,\u001a\u0002H)H\u0016¢\u0006\u0002\u00101J)\u00105\u001a\u00020\u0000\"\u0004\b\u0000\u0010)2\f\u0010/\u001a\b\u0012\u0004\u0012\u0002H)002\u0006\u0010,\u001a\u0002H)H\u0016¢\u0006\u0002\u00101J\u0016\u00106\u001a\u00020'2\f\u00107\u001a\b\u0012\u0004\u0012\u00020\u000608H\u0016R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\"\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0096\u000e¢\u0006\u0010\n\u0002\u0010\u0013\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0014\u0010\t\u001a\u00020\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u001bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001a\u0010 \u001a\u00020!X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%¨\u00069"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/impl/FirErrorLoopImpl;", "Lorg/jetbrains/kotlin/fir/expressions/FirErrorLoop;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "annotations", "Lorg/jetbrains/kotlin/fir/MutableOrEmptyList;", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", CoroutineCodegenUtilKt.COROUTINE_LABEL_FIELD_NAME, "Lorg/jetbrains/kotlin/fir/FirLabel;", "diagnostic", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "<init>", "(Lorg/jetbrains/kotlin/KtSourceElement;Ljava/util/List;Lorg/jetbrains/kotlin/fir/FirLabel;Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "getAnnotations-5e3fPpI", "()Ljava/util/List;", "setAnnotations-GqUYU-s", "(Ljava/util/List;)V", "Ljava/util/List;", "getLabel", "()Lorg/jetbrains/kotlin/fir/FirLabel;", "setLabel", "(Lorg/jetbrains/kotlin/fir/FirLabel;)V", "getDiagnostic", "()Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "block", "Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "getBlock", "()Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "setBlock", "(Lorg/jetbrains/kotlin/fir/expressions/FirBlock;)V", "condition", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "getCondition", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "setCondition", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)V", "acceptChildren", Argument.Delimiters.none, "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)V", "transformChildren", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/expressions/impl/FirErrorLoopImpl;", "transformAnnotations", "transformBlock", "transformCondition", "transformLabel", "replaceAnnotations", "newAnnotations", Argument.Delimiters.none, "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirErrorLoopImpl extends FirErrorLoop {
    private List<FirAnnotation> annotations;
    private FirBlock block;
    private FirExpression condition;
    private final ConeDiagnostic diagnostic;
    private FirLabel label;
    private final KtSourceElement source;

    private FirErrorLoopImpl(KtSourceElement ktSourceElement, List<FirAnnotation> list, FirLabel firLabel, ConeDiagnostic coneDiagnostic) {
        coneDiagnostic.getClass();
        this.source = ktSourceElement;
        this.annotations = list;
        this.label = firLabel;
        this.diagnostic = coneDiagnostic;
        this.block = new FirEmptyExpressionBlock();
        this.condition = new FirErrorExpressionImpl(getSource(), MutableOrEmptyList.INSTANCE.m213empty5e3fPpI(), new ConeUnreportedDuplicateDiagnostic(getDiagnostic()), null, null, null);
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <R, D> void acceptChildren(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        Iterator<T> it = MutableOrEmptyList.m194boximpl(m436getAnnotations5e3fPpI()).iterator();
        while (it.hasNext()) {
            ((FirAnnotation) it.next()).accept(visitor, data);
        }
        getBlock().accept(visitor, data);
        getCondition().accept(visitor, data);
        FirLabel label = getLabel();
        if (label != null) {
            label.accept(visitor, data);
        }
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorLoop, org.jetbrains.kotlin.fir.expressions.FirLoop, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ List getAnnotations() {
        return MutableOrEmptyList.m194boximpl(m436getAnnotations5e3fPpI());
    }

    /* JADX INFO: renamed from: getAnnotations-5e3fPpI, reason: not valid java name */
    public List<FirAnnotation> m436getAnnotations5e3fPpI() {
        return this.annotations;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorLoop, org.jetbrains.kotlin.fir.expressions.FirLoop
    public FirBlock getBlock() {
        return this.block;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorLoop, org.jetbrains.kotlin.fir.expressions.FirLoop
    public FirExpression getCondition() {
        return this.condition;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorLoop, org.jetbrains.kotlin.fir.diagnostics.FirDiagnosticHolder
    public ConeDiagnostic getDiagnostic() {
        return this.diagnostic;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorLoop, org.jetbrains.kotlin.fir.expressions.FirLoop
    public FirLabel getLabel() {
        return this.label;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorLoop, org.jetbrains.kotlin.fir.expressions.FirLoop, org.jetbrains.kotlin.fir.FirElement
    public KtSourceElement getSource() {
        return this.source;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorLoop, org.jetbrains.kotlin.fir.expressions.FirLoop, org.jetbrains.kotlin.fir.expressions.FirStatement
    public void replaceAnnotations(List<? extends FirAnnotation> newAnnotations) {
        newAnnotations.getClass();
        m437setAnnotationsGqUYUs(FirBuilderDslKt.toMutableOrEmptyForImmutable(newAnnotations));
    }

    /* JADX INFO: renamed from: setAnnotations-GqUYU-s, reason: not valid java name */
    public void m437setAnnotationsGqUYUs(List<FirAnnotation> list) {
        this.annotations = list;
    }

    public void setBlock(FirBlock firBlock) {
        firBlock.getClass();
        this.block = firBlock;
    }

    public void setCondition(FirExpression firExpression) {
        firExpression.getClass();
        this.condition = firExpression;
    }

    public void setLabel(FirLabel firLabel) {
        this.label = firLabel;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorLoop, org.jetbrains.kotlin.fir.expressions.FirLoop, org.jetbrains.kotlin.fir.expressions.FirStatement
    public <D> FirErrorLoopImpl transformAnnotations(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirTransformerUtilKt.m709transformInplaceaLnlfrU(m436getAnnotations5e3fPpI(), transformer, data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorLoop, org.jetbrains.kotlin.fir.expressions.FirLoop
    public <D> FirErrorLoopImpl transformBlock(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        setBlock((FirBlock) getBlock().transform(transformer, data));
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <D> FirErrorLoopImpl transformChildren(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        transformAnnotations((FirTransformer) transformer, (Object) data);
        transformBlock((FirTransformer) transformer, (Object) data);
        transformCondition((FirTransformer) transformer, (Object) data);
        transformLabel((FirTransformer) transformer, (Object) data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorLoop, org.jetbrains.kotlin.fir.expressions.FirLoop
    public <D> FirErrorLoopImpl transformCondition(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        setCondition((FirExpression) getCondition().transform(transformer, data));
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorLoop, org.jetbrains.kotlin.fir.expressions.FirLoop
    public <D> FirErrorLoopImpl transformLabel(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirLabel label = getLabel();
        setLabel(label != null ? (FirLabel) label.transform(transformer, data) : null);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorLoop, org.jetbrains.kotlin.fir.expressions.FirLoop, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirErrorLoop transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorLoop, org.jetbrains.kotlin.fir.expressions.FirLoop, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirLoop transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorLoop, org.jetbrains.kotlin.fir.expressions.FirLoop, org.jetbrains.kotlin.fir.expressions.FirStatement, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirStatement transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorLoop, org.jetbrains.kotlin.fir.expressions.FirLoop, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirAnnotationContainer transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public /* bridge */ /* synthetic */ FirElement transformChildren(FirTransformer firTransformer, Object obj) {
        return transformChildren((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorLoop, org.jetbrains.kotlin.fir.expressions.FirLoop
    public /* bridge */ /* synthetic */ FirLoop transformBlock(FirTransformer firTransformer, Object obj) {
        return transformBlock((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorLoop, org.jetbrains.kotlin.fir.expressions.FirLoop
    public /* bridge */ /* synthetic */ FirLoop transformCondition(FirTransformer firTransformer, Object obj) {
        return transformCondition((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorLoop, org.jetbrains.kotlin.fir.expressions.FirLoop
    public /* bridge */ /* synthetic */ FirErrorLoop transformBlock(FirTransformer firTransformer, Object obj) {
        return transformBlock((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorLoop, org.jetbrains.kotlin.fir.expressions.FirLoop
    public /* bridge */ /* synthetic */ FirErrorLoop transformCondition(FirTransformer firTransformer, Object obj) {
        return transformCondition((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorLoop, org.jetbrains.kotlin.fir.expressions.FirLoop
    public /* bridge */ /* synthetic */ FirLoop transformLabel(FirTransformer firTransformer, Object obj) {
        return transformLabel((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirErrorLoop, org.jetbrains.kotlin.fir.expressions.FirLoop
    public /* bridge */ /* synthetic */ FirErrorLoop transformLabel(FirTransformer firTransformer, Object obj) {
        return transformLabel((FirTransformer<? super Object>) firTransformer, obj);
    }

    public /* synthetic */ FirErrorLoopImpl(KtSourceElement ktSourceElement, List list, FirLabel firLabel, ConeDiagnostic coneDiagnostic, DefaultConstructorMarker defaultConstructorMarker) {
        this(ktSourceElement, list, firLabel, coneDiagnostic);
    }
}
