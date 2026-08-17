package org.jetbrains.kotlin.fir.expressions.builder;

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
import org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder;
import org.jetbrains.kotlin.fir.builder.FirBuilderDsl;
import org.jetbrains.kotlin.fir.builder.FirBuilderDslKt;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirArgumentList;
import org.jetbrains.kotlin.fir.expressions.FirComponentCall;
import org.jetbrains.kotlin.fir.expressions.FirEmptyArgumentList;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.impl.FirComponentCallImpl;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@FirBuilderDsl
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010:\u001a\u00020;H\u0016R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\r¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\r¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010R\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001c\u0010\u001c\u001a\u0004\u0018\u00010\u0012X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0019\"\u0004\b\u001e\u0010\u001bR\u001c\u0010\u001f\u001a\u0004\u0018\u00010 X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u0017\u0010%\u001a\b\u0012\u0004\u0012\u00020&0\r¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0010R\u001a\u0010(\u001a\u00020)X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001a\u0010.\u001a\u00020\u0012X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\u0019\"\u0004\b0\u0010\u001bR+\u00103\u001a\u0002022\u0006\u00101\u001a\u0002028F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b8\u00109\u001a\u0004\b4\u00105\"\u0004\b6\u00107Ê\u0001\u0002\b=¨\u0006<"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/builder/FirComponentCallBuilder;", "Lorg/jetbrains/kotlin/fir/expressions/builder/FirCallBuilder;", "Lorg/jetbrains/kotlin/fir/builder/FirAnnotationContainerBuilder;", "Lorg/jetbrains/kotlin/fir/expressions/builder/FirExpressionBuilder;", "<init>", "()V", "coneTypeOrNull", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getConeTypeOrNull", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "setConeTypeOrNull", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)V", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "getAnnotations", "()Ljava/util/List;", "contextArguments", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "getContextArguments", "typeArguments", "Lorg/jetbrains/kotlin/fir/types/FirTypeProjection;", "getTypeArguments", "dispatchReceiver", "getDispatchReceiver", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "setDispatchReceiver", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)V", "extensionReceiver", "getExtensionReceiver", "setExtensionReceiver", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "setSource", "(Lorg/jetbrains/kotlin/KtSourceElement;)V", "nonFatalDiagnostics", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "getNonFatalDiagnostics", "argumentList", "Lorg/jetbrains/kotlin/fir/expressions/FirArgumentList;", "getArgumentList", "()Lorg/jetbrains/kotlin/fir/expressions/FirArgumentList;", "setArgumentList", "(Lorg/jetbrains/kotlin/fir/expressions/FirArgumentList;)V", "explicitReceiver", "getExplicitReceiver", "setExplicitReceiver", "<set-?>", Argument.Delimiters.none, "componentIndex", "getComponentIndex", "()I", "setComponentIndex", "(I)V", "componentIndex$delegate", "Lkotlin/properties/ReadWriteProperty;", "build", "Lorg/jetbrains/kotlin/fir/expressions/FirComponentCall;", "org.jetbrains.kotlin:tree", "Lorg/jetbrains/kotlin/fir/builder/FirBuilderDsl;"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirComponentCallBuilder implements FirAnnotationContainerBuilder, FirCallBuilder, FirExpressionBuilder {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new MutablePropertyReference1Impl<>(FirComponentCallBuilder.class, "componentIndex", "getComponentIndex()I", 0)};
    private ConeKotlinType coneTypeOrNull;
    private FirExpression dispatchReceiver;
    public FirExpression explicitReceiver;
    private FirExpression extensionReceiver;
    private KtSourceElement source;
    private final List<FirAnnotation> annotations = new ArrayList();
    private final List<FirExpression> contextArguments = new ArrayList();
    private final List<FirTypeProjection> typeArguments = new ArrayList();
    private final List<ConeDiagnostic> nonFatalDiagnostics = new ArrayList();
    private FirArgumentList argumentList = FirEmptyArgumentList.INSTANCE;

    /* JADX INFO: renamed from: componentIndex$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty componentIndex = Delegates.INSTANCE.notNull();

    @Override // org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder
    public FirComponentCall build() {
        return new FirComponentCallImpl(getConeTypeOrNull(), FirBuilderDslKt.toMutableOrEmpty(getAnnotations()), FirBuilderDslKt.toMutableOrEmpty(this.contextArguments), FirBuilderDslKt.toMutableOrEmpty(this.typeArguments), this.dispatchReceiver, this.extensionReceiver, getSource(), FirBuilderDslKt.toMutableOrEmpty(this.nonFatalDiagnostics), getArgumentList(), getExplicitReceiver(), getComponentIndex(), null);
    }

    @Override // org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public List<FirAnnotation> getAnnotations() {
        return this.annotations;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirCallBuilder
    public FirArgumentList getArgumentList() {
        return this.argumentList;
    }

    public final int getComponentIndex() {
        return ((Number) this.componentIndex.getValue(this, $$delegatedProperties[0])).intValue();
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirExpressionBuilder
    public ConeKotlinType getConeTypeOrNull() {
        return this.coneTypeOrNull;
    }

    public final List<FirExpression> getContextArguments() {
        return this.contextArguments;
    }

    public final FirExpression getDispatchReceiver() {
        return this.dispatchReceiver;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final FirExpression getExplicitReceiver() throws UninitializedPropertyAccessException {
        FirExpression firExpression = this.explicitReceiver;
        if (firExpression != null) {
            return firExpression;
        }
        Intrinsics.throwUninitializedPropertyAccessException("explicitReceiver");
        return null;
    }

    public final FirExpression getExtensionReceiver() {
        return this.extensionReceiver;
    }

    public final List<ConeDiagnostic> getNonFatalDiagnostics() {
        return this.nonFatalDiagnostics;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirCallBuilder, org.jetbrains.kotlin.fir.expressions.builder.FirQualifiedAccessExpressionBuilder
    public KtSourceElement getSource() {
        return this.source;
    }

    public final List<FirTypeProjection> getTypeArguments() {
        return this.typeArguments;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirCallBuilder
    public void setArgumentList(FirArgumentList firArgumentList) {
        firArgumentList.getClass();
        this.argumentList = firArgumentList;
    }

    public final void setComponentIndex(int i) {
        this.componentIndex.setValue(this, $$delegatedProperties[0], Integer.valueOf(i));
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirExpressionBuilder
    public void setConeTypeOrNull(ConeKotlinType coneKotlinType) {
        this.coneTypeOrNull = coneKotlinType;
    }

    public final void setDispatchReceiver(FirExpression firExpression) {
        this.dispatchReceiver = firExpression;
    }

    public final void setExplicitReceiver(FirExpression firExpression) {
        firExpression.getClass();
        this.explicitReceiver = firExpression;
    }

    public final void setExtensionReceiver(FirExpression firExpression) {
        this.extensionReceiver = firExpression;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirCallBuilder, org.jetbrains.kotlin.fir.expressions.builder.FirQualifiedAccessExpressionBuilder
    public void setSource(KtSourceElement ktSourceElement) {
        this.source = ktSourceElement;
    }
}
