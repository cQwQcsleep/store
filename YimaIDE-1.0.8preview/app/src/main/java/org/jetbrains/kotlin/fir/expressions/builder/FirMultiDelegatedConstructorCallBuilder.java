package org.jetbrains.kotlin.fir.expressions.builder;

import java.util.ArrayList;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.state.InlineClassManglingUtilsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder;
import org.jetbrains.kotlin.fir.builder.FirBuilderDsl;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall;
import org.jetbrains.kotlin.fir.expressions.FirMultiDelegatedConstructorCall;
import org.jetbrains.kotlin.fir.expressions.impl.FirMultiDelegatedConstructorCallImpl;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@FirBuilderDsl
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010\n\u001a\u00020\u000bH\u0016R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR<\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u00068\u0016X\u0097\u0004r\u0018\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u0012\u0012\n\b\u0013\u0012\u0006\b\n0\u00148\u0015¢\u0006\u000e\n\u0000\u0012\u0004\b\u000e\u0010\u0004\u001a\u0004\b\u000f\u0010\tRH\u0010\u0018\u001a\u0004\u0018\u00010\u00172\b\u0010\u0016\u001a\u0004\u0018\u00010\u00178V@VX\u0097\u000er\u0018\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(\u001e\u0012\n\b\u0013\u0012\u0006\b\n0\u00148\u0015¢\u0006\u0012\u0012\u0004\b\u0019\u0010\u0004\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dRH\u0010 \u001a\u0004\u0018\u00010\u001f2\b\u0010\u0016\u001a\u0004\u0018\u00010\u001f8V@VX\u0097\u000er\u0018\b\u0010\u0012\b\b\u0011\u0012\u0004\b\b(&\u0012\n\b\u0013\u0012\u0006\b\n0\u00148\u0015¢\u0006\u0012\u0012\u0004\b!\u0010\u0004\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%Ê\u0001\u0002\b(¨\u0006'"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/builder/FirMultiDelegatedConstructorCallBuilder;", "Lorg/jetbrains/kotlin/fir/builder/FirAnnotationContainerBuilder;", "Lorg/jetbrains/kotlin/fir/expressions/builder/FirExpressionBuilder;", "<init>", "()V", "delegatedConstructorCalls", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirDelegatedConstructorCall;", "getDelegatedConstructorCalls", "()Ljava/util/List;", "build", "Lorg/jetbrains/kotlin/fir/expressions/FirMultiDelegatedConstructorCall;", "annotations", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "getAnnotations$annotations", "getAnnotations", "Lkotlin/Deprecated;", "message", "Modification of 'annotations' has no impact for FirMultiDelegatedConstructorCallBuilder", "level", "Lkotlin/DeprecationLevel;", "HIDDEN", InlineClassManglingUtilsKt.NOT_INLINE_CLASS_PARAMETER_PLACEHOLDER, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "coneTypeOrNull", "getConeTypeOrNull$annotations", "getConeTypeOrNull", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "setConeTypeOrNull", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)V", "Modification of 'coneTypeOrNull' has no impact for FirMultiDelegatedConstructorCallBuilder", "Lorg/jetbrains/kotlin/KtSourceElement;", "source", "getSource$annotations", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "setSource", "(Lorg/jetbrains/kotlin/KtSourceElement;)V", "Modification of 'source' has no impact for FirMultiDelegatedConstructorCallBuilder", "org.jetbrains.kotlin:tree", "Lorg/jetbrains/kotlin/fir/builder/FirBuilderDsl;"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirMultiDelegatedConstructorCallBuilder implements FirAnnotationContainerBuilder, FirExpressionBuilder {
    private final List<FirDelegatedConstructorCall> delegatedConstructorCalls = new ArrayList();
    private final List<FirAnnotation> annotations = new ArrayList();

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Modification of 'annotations' has no impact for FirMultiDelegatedConstructorCallBuilder")
    public static /* synthetic */ void getAnnotations$annotations() {
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Modification of 'coneTypeOrNull' has no impact for FirMultiDelegatedConstructorCallBuilder")
    public static /* synthetic */ void getConeTypeOrNull$annotations() {
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Modification of 'source' has no impact for FirMultiDelegatedConstructorCallBuilder")
    public static /* synthetic */ void getSource$annotations() {
    }

    @Override // org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder
    public FirMultiDelegatedConstructorCall build() {
        return new FirMultiDelegatedConstructorCallImpl(this.delegatedConstructorCalls);
    }

    @Override // org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public /* synthetic */ List getAnnotations() {
        return this.annotations;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirExpressionBuilder
    public /* synthetic */ ConeKotlinType getConeTypeOrNull() {
        throw new IllegalStateException();
    }

    public final List<FirDelegatedConstructorCall> getDelegatedConstructorCalls() {
        return this.delegatedConstructorCalls;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirExpressionBuilder
    public /* synthetic */ KtSourceElement getSource() {
        throw new IllegalStateException();
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirExpressionBuilder
    public /* synthetic */ void setConeTypeOrNull(ConeKotlinType coneKotlinType) {
        throw new IllegalStateException();
    }

    @Override // org.jetbrains.kotlin.fir.expressions.builder.FirExpressionBuilder
    public /* synthetic */ void setSource(KtSourceElement ktSourceElement) {
        throw new IllegalStateException();
    }
}
