package org.jetbrains.kotlin.fir.expressions;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirLabel;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.FirDiagnosticHolder;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J5\u0010\u001e\u001a\u0002H\u001f\"\u0004\b\u0000\u0010\u001f\"\u0004\b\u0001\u0010 2\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u0002H\u001f\u0012\u0004\u0012\u0002H 0\"2\u0006\u0010#\u001a\u0002H H\u0016¢\u0006\u0002\u0010$J3\u0010%\u001a\u0002H&\"\b\b\u0000\u0010&*\u00020'\"\u0004\b\u0001\u0010 2\f\u0010(\u001a\b\u0012\u0004\u0012\u0002H 0)2\u0006\u0010#\u001a\u0002H H\u0016¢\u0006\u0002\u0010*J\u0016\u0010+\u001a\u00020,2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH&J)\u0010.\u001a\u00020\u0000\"\u0004\b\u0000\u0010 2\f\u0010(\u001a\b\u0012\u0004\u0012\u0002H 0)2\u0006\u0010#\u001a\u0002H H&¢\u0006\u0002\u0010/J)\u00100\u001a\u00020\u0000\"\u0004\b\u0000\u0010 2\f\u0010(\u001a\b\u0012\u0004\u0012\u0002H 0)2\u0006\u0010#\u001a\u0002H H&¢\u0006\u0002\u0010/J)\u00101\u001a\u00020\u0000\"\u0004\b\u0000\u0010 2\f\u0010(\u001a\b\u0012\u0004\u0012\u0002H 0)2\u0006\u0010#\u001a\u0002H H&¢\u0006\u0002\u0010/J)\u00102\u001a\u00020\u0000\"\u0004\b\u0000\u0010 2\f\u0010(\u001a\b\u0012\u0004\u0012\u0002H 0)2\u0006\u0010#\u001a\u0002H H&¢\u0006\u0002\u0010/R\u0014\u0010\u0005\u001a\u0004\u0018\u00010\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0018\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0012\u0010\u000e\u001a\u00020\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0012\u0010\u0012\u001a\u00020\u0013X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u0004\u0018\u00010\u0017X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u0012\u0010\u001a\u001a\u00020\u001bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001d¨\u00063"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/FirErrorLoop;", "Lorg/jetbrains/kotlin/fir/expressions/FirLoop;", "Lorg/jetbrains/kotlin/fir/diagnostics/FirDiagnosticHolder;", "<init>", "()V", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "getAnnotations", "()Ljava/util/List;", "block", "Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "getBlock", "()Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "condition", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "getCondition", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", CoroutineCodegenUtilKt.COROUTINE_LABEL_FIELD_NAME, "Lorg/jetbrains/kotlin/fir/FirLabel;", "getLabel", "()Lorg/jetbrains/kotlin/fir/FirLabel;", "diagnostic", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "getDiagnostic", "()Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "accept", "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "transform", "E", "Lorg/jetbrains/kotlin/fir/FirElement;", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/FirElement;", "replaceAnnotations", Argument.Delimiters.none, "newAnnotations", "transformAnnotations", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/expressions/FirErrorLoop;", "transformBlock", "transformCondition", "transformLabel", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirErrorLoop extends FirLoop implements FirDiagnosticHolder {
    public FirErrorLoop() {
        super(null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.expressions.FirLoop, org.jetbrains.kotlin.fir.FirElement
    public <R, D> R accept(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        return visitor.visitErrorLoop(this, data);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirLoop, org.jetbrains.kotlin.fir.expressions.FirStatement
    public abstract List<FirAnnotation> getAnnotations();

    @Override // org.jetbrains.kotlin.fir.expressions.FirLoop
    public abstract FirBlock getBlock();

    @Override // org.jetbrains.kotlin.fir.expressions.FirLoop
    public abstract FirExpression getCondition();

    @Override // org.jetbrains.kotlin.fir.diagnostics.FirDiagnosticHolder
    public abstract ConeDiagnostic getDiagnostic();

    @Override // org.jetbrains.kotlin.fir.expressions.FirLoop
    public abstract FirLabel getLabel();

    @Override // org.jetbrains.kotlin.fir.expressions.FirLoop, org.jetbrains.kotlin.fir.FirElement
    public abstract KtSourceElement getSource();

    @Override // org.jetbrains.kotlin.fir.expressions.FirLoop, org.jetbrains.kotlin.fir.expressions.FirStatement
    public abstract void replaceAnnotations(List<? extends FirAnnotation> newAnnotations);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.expressions.FirLoop, org.jetbrains.kotlin.fir.FirElement
    public <E extends FirElement, D> E transform(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirStatement firStatementTransformErrorLoop = transformer.transformErrorLoop(this, data);
        firStatementTransformErrorLoop.getClass();
        return firStatementTransformErrorLoop;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirLoop, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirAnnotationContainer transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirLoop, org.jetbrains.kotlin.fir.expressions.FirStatement
    public abstract <D> FirErrorLoop transformAnnotations(FirTransformer<? super D> transformer, D data);

    @Override // org.jetbrains.kotlin.fir.expressions.FirLoop
    public abstract <D> FirErrorLoop transformBlock(FirTransformer<? super D> transformer, D data);

    @Override // org.jetbrains.kotlin.fir.expressions.FirLoop
    public /* bridge */ /* synthetic */ FirLoop transformBlock(FirTransformer firTransformer, Object obj) {
        return transformBlock((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirLoop
    public abstract <D> FirErrorLoop transformCondition(FirTransformer<? super D> transformer, D data);

    @Override // org.jetbrains.kotlin.fir.expressions.FirLoop
    public /* bridge */ /* synthetic */ FirLoop transformCondition(FirTransformer firTransformer, Object obj) {
        return transformCondition((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirLoop
    public abstract <D> FirErrorLoop transformLabel(FirTransformer<? super D> transformer, D data);

    @Override // org.jetbrains.kotlin.fir.expressions.FirLoop
    public /* bridge */ /* synthetic */ FirLoop transformLabel(FirTransformer firTransformer, Object obj) {
        return transformLabel((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirLoop, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirLoop transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirLoop, org.jetbrains.kotlin.fir.expressions.FirStatement, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirStatement transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }
}
