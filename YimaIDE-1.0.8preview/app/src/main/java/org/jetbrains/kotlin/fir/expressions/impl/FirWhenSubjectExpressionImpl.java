package org.jetbrains.kotlin.fir.expressions.impl;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirIdeOnly;
import org.jetbrains.kotlin.fir.FirImplementationDetail;
import org.jetbrains.kotlin.fir.MutableOrEmptyList;
import org.jetbrains.kotlin.fir.builder.FirBuilderDslKt;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirContextArgumentListOwner;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvable;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression;
import org.jetbrains.kotlin.fir.expressions.UnresolvedExpressionTypeAccess;
import org.jetbrains.kotlin.fir.references.FirNamedReference;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirTransformerUtilKt;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001BI\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0005\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\u0006\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b\u000f\u0010\u0010J5\u0010;\u001a\u00020<\"\u0004\b\u0000\u0010=\"\u0004\b\u0001\u0010>2\u0012\u0010?\u001a\u000e\u0012\u0004\u0012\u0002H=\u0012\u0004\u0012\u0002H>0@2\u0006\u0010A\u001a\u0002H>H\u0016¢\u0006\u0002\u0010BJ)\u0010C\u001a\u00020\u0000\"\u0004\b\u0000\u0010>2\f\u0010D\u001a\b\u0012\u0004\u0012\u0002H>0E2\u0006\u0010A\u001a\u0002H>H\u0016¢\u0006\u0002\u0010FJ)\u0010G\u001a\u00020\u0000\"\u0004\b\u0000\u0010>2\f\u0010D\u001a\b\u0012\u0004\u0012\u0002H>0E2\u0006\u0010A\u001a\u0002H>H\u0016¢\u0006\u0002\u0010FJ)\u0010H\u001a\u00020\u0000\"\u0004\b\u0000\u0010>2\f\u0010D\u001a\b\u0012\u0004\u0012\u0002H>0E2\u0006\u0010A\u001a\u0002H>H\u0016¢\u0006\u0002\u0010FJ)\u0010I\u001a\u00020\u0000\"\u0004\b\u0000\u0010>2\f\u0010D\u001a\b\u0012\u0004\u0012\u0002H>0E2\u0006\u0010A\u001a\u0002H>H\u0016¢\u0006\u0002\u0010FJ)\u0010J\u001a\u00020\u0000\"\u0004\b\u0000\u0010>2\f\u0010D\u001a\b\u0012\u0004\u0012\u0002H>0E2\u0006\u0010A\u001a\u0002H>H\u0016¢\u0006\u0002\u0010FJ)\u0010K\u001a\u00020\u0000\"\u0004\b\u0000\u0010>2\f\u0010D\u001a\b\u0012\u0004\u0012\u0002H>0E2\u0006\u0010A\u001a\u0002H>H\u0016¢\u0006\u0002\u0010FJ\u0012\u0010L\u001a\u00020<2\b\u0010M\u001a\u0004\u0018\u00010\u0003H\u0016J\u0016\u0010N\u001a\u00020<2\f\u0010O\u001a\b\u0012\u0004\u0012\u00020\u00060.H\u0016J\u0016\u0010P\u001a\u00020<2\f\u0010Q\u001a\b\u0012\u0004\u0012\u00020/0.H\u0016J\u0016\u0010R\u001a\u00020<2\f\u0010S\u001a\b\u0012\u0004\u0012\u0002020.H\u0016J\u0012\u0010T\u001a\u00020<2\b\u0010U\u001a\u0004\u0018\u00010/H\u0016J\u0012\u0010V\u001a\u00020<2\b\u0010W\u001a\u0004\u0018\u00010/H\u0016J\u0012\u0010X\u001a\u00020<2\b\u0010Y\u001a\u0004\u0018\u00010/H\u0016J\u0016\u0010Z\u001a\u00020<2\b\u0010[\u001a\u0004\u0018\u00010\bH\u0017b\u0002\b\\J\u0016\u0010]\u001a\u00020<2\f\u0010^\u001a\b\u0012\u0004\u0012\u00020\n0.H\u0016J\u0012\u0010_\u001a\u00020<2\b\u0010`\u001a\u0004\u0018\u00010\fH\u0016J\u0010\u0010a\u001a\u00020<2\u0006\u0010b\u001a\u00020\u000eH\u0016J\u0010\u0010a\u001a\u00020<2\u0006\u0010b\u001a\u00020cH\u0016R*\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0016@\u0016X\u0097\u000er\u0002\b\u0017¢\u0006\u0014\n\u0000\u0012\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\"\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0096\u000e¢\u0006\u0010\n\u0002\u0010\u001c\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\"\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0005X\u0096\u000e¢\u0006\u0010\n\u0002\u0010\u001c\u001a\u0004\b!\u0010\u0019\"\u0004\b\"\u0010\u001bR*\u0010\u000b\u001a\u0004\u0018\u00010\f8\u0016@\u0016X\u0097\u000er\u0002\b(¢\u0006\u0014\n\u0000\u0012\u0004\b#\u0010\u0012\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u001a\u0010\r\u001a\u00020\u000eX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,R\u001a\u0010-\u001a\b\u0012\u0004\u0012\u00020/0.8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b0\u0010\u0019R\u001a\u00101\u001a\b\u0012\u0004\u0012\u0002020.8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b3\u0010\u0019R\u0016\u00104\u001a\u0004\u0018\u00010/8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b5\u00106R\u0016\u00107\u001a\u0004\u0018\u00010/8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b8\u00106R\u0016\u00109\u001a\u0004\u0018\u00010/8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b:\u00106¨\u0006d"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/impl/FirWhenSubjectExpressionImpl;", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenSubjectExpression;", "coneTypeOrNull", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "annotations", "Lorg/jetbrains/kotlin/fir/MutableOrEmptyList;", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "nonFatalDiagnostics", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "contextSensitiveAlternative", "Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;", "calleeReference", "Lorg/jetbrains/kotlin/fir/references/FirNamedReference;", "<init>", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Ljava/util/List;Lorg/jetbrains/kotlin/KtSourceElement;Ljava/util/List;Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;Lorg/jetbrains/kotlin/fir/references/FirNamedReference;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getConeTypeOrNull$annotations", "()V", "getConeTypeOrNull", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "setConeTypeOrNull", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)V", "Lorg/jetbrains/kotlin/fir/expressions/UnresolvedExpressionTypeAccess;", "getAnnotations-5e3fPpI", "()Ljava/util/List;", "setAnnotations-GqUYU-s", "(Ljava/util/List;)V", "Ljava/util/List;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "setSource", "(Lorg/jetbrains/kotlin/KtSourceElement;)V", "getNonFatalDiagnostics-5e3fPpI", "setNonFatalDiagnostics-GqUYU-s", "getContextSensitiveAlternative$annotations", "getContextSensitiveAlternative", "()Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;", "setContextSensitiveAlternative", "(Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;)V", "Lorg/jetbrains/kotlin/fir/FirIdeOnly;", "getCalleeReference", "()Lorg/jetbrains/kotlin/fir/references/FirNamedReference;", "setCalleeReference", "(Lorg/jetbrains/kotlin/fir/references/FirNamedReference;)V", "contextArguments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "getContextArguments", "typeArguments", "Lorg/jetbrains/kotlin/fir/types/FirTypeProjection;", "getTypeArguments", "explicitReceiver", "getExplicitReceiver", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "dispatchReceiver", "getDispatchReceiver", "extensionReceiver", "getExtensionReceiver", "acceptChildren", Argument.Delimiters.none, "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)V", "transformChildren", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/expressions/impl/FirWhenSubjectExpressionImpl;", "transformAnnotations", "transformContextArguments", "transformTypeArguments", "transformExplicitReceiver", "transformCalleeReference", "replaceConeTypeOrNull", "newConeTypeOrNull", "replaceAnnotations", "newAnnotations", "replaceContextArguments", "newContextArguments", "replaceTypeArguments", "newTypeArguments", "replaceExplicitReceiver", "newExplicitReceiver", "replaceDispatchReceiver", "newDispatchReceiver", "replaceExtensionReceiver", "newExtensionReceiver", "replaceSource", "newSource", "Lorg/jetbrains/kotlin/fir/FirImplementationDetail;", "replaceNonFatalDiagnostics", "newNonFatalDiagnostics", "replaceContextSensitiveAlternative", "newContextSensitiveAlternative", "replaceCalleeReference", "newCalleeReference", "Lorg/jetbrains/kotlin/fir/references/FirReference;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirWhenSubjectExpressionImpl extends FirWhenSubjectExpression {
    private List<FirAnnotation> annotations;
    private FirNamedReference calleeReference;
    private ConeKotlinType coneTypeOrNull;
    private FirPropertyAccessExpression contextSensitiveAlternative;
    private List<ConeDiagnostic> nonFatalDiagnostics;
    private KtSourceElement source;

    private FirWhenSubjectExpressionImpl(ConeKotlinType coneKotlinType, List<FirAnnotation> list, KtSourceElement ktSourceElement, List<ConeDiagnostic> list2, FirPropertyAccessExpression firPropertyAccessExpression, FirNamedReference firNamedReference) {
        firNamedReference.getClass();
        this.coneTypeOrNull = coneKotlinType;
        this.annotations = list;
        this.source = ktSourceElement;
        this.nonFatalDiagnostics = list2;
        this.contextSensitiveAlternative = firPropertyAccessExpression;
        this.calleeReference = firNamedReference;
    }

    @UnresolvedExpressionTypeAccess
    public static /* synthetic */ void getConeTypeOrNull$annotations() {
    }

    @FirIdeOnly
    public static /* synthetic */ void getContextSensitiveAlternative$annotations() {
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <R, D> void acceptChildren(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        Iterator<T> it = MutableOrEmptyList.m194boximpl(m540getAnnotations5e3fPpI()).iterator();
        while (it.hasNext()) {
            ((FirAnnotation) it.next()).accept(visitor, data);
        }
        getCalleeReference().accept(visitor, data);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression, org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ List getAnnotations() {
        return MutableOrEmptyList.m194boximpl(m540getAnnotations5e3fPpI());
    }

    /* JADX INFO: renamed from: getAnnotations-5e3fPpI, reason: not valid java name */
    public List<FirAnnotation> m540getAnnotations5e3fPpI() {
        return this.annotations;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression, org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirExpression
    public ConeKotlinType getConeTypeOrNull() {
        return this.coneTypeOrNull;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression, org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirContextArgumentListOwner
    public List<FirExpression> getContextArguments() {
        return CollectionsKt.emptyList();
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression, org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression, org.jetbrains.kotlin.fir.expressions.FirQualifierWithContextSensitiveAlternative
    public FirPropertyAccessExpression getContextSensitiveAlternative() {
        return this.contextSensitiveAlternative;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression, org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression
    public FirExpression getDispatchReceiver() {
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression, org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression
    public FirExpression getExplicitReceiver() {
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression, org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression
    public FirExpression getExtensionReceiver() {
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression, org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression
    public /* bridge */ /* synthetic */ List getNonFatalDiagnostics() {
        return MutableOrEmptyList.m194boximpl(m541getNonFatalDiagnostics5e3fPpI());
    }

    /* JADX INFO: renamed from: getNonFatalDiagnostics-5e3fPpI, reason: not valid java name */
    public List<ConeDiagnostic> m541getNonFatalDiagnostics5e3fPpI() {
        return this.nonFatalDiagnostics;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression, org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.FirElement
    public KtSourceElement getSource() {
        return this.source;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression, org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression
    public List<FirTypeProjection> getTypeArguments() {
        return CollectionsKt.emptyList();
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression, org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public void replaceAnnotations(List<? extends FirAnnotation> newAnnotations) {
        newAnnotations.getClass();
        m542setAnnotationsGqUYUs(FirBuilderDslKt.toMutableOrEmptyForImmutable(newAnnotations));
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression, org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirResolvable
    public void replaceCalleeReference(FirReference newCalleeReference) {
        newCalleeReference.getClass();
        if (newCalleeReference instanceof FirNamedReference) {
            replaceCalleeReference((FirNamedReference) newCalleeReference);
        } else {
            w01.a("Failed requirement.");
        }
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression, org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirExpression
    public void replaceConeTypeOrNull(ConeKotlinType newConeTypeOrNull) {
        setConeTypeOrNull(newConeTypeOrNull);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression, org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirContextArgumentListOwner
    public void replaceContextArguments(List<? extends FirExpression> newContextArguments) {
        newContextArguments.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression, org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression, org.jetbrains.kotlin.fir.expressions.FirQualifierWithContextSensitiveAlternative
    public void replaceContextSensitiveAlternative(FirPropertyAccessExpression newContextSensitiveAlternative) {
        setContextSensitiveAlternative(newContextSensitiveAlternative);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression, org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression
    public void replaceDispatchReceiver(FirExpression newDispatchReceiver) {
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression, org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression
    public void replaceExplicitReceiver(FirExpression newExplicitReceiver) {
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression, org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression
    public void replaceExtensionReceiver(FirExpression newExtensionReceiver) {
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression, org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression
    public void replaceNonFatalDiagnostics(List<? extends ConeDiagnostic> newNonFatalDiagnostics) {
        newNonFatalDiagnostics.getClass();
        m543setNonFatalDiagnosticsGqUYUs(FirBuilderDslKt.toMutableOrEmptyForImmutable(newNonFatalDiagnostics));
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression, org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression
    @FirImplementationDetail
    public void replaceSource(KtSourceElement newSource) {
        setSource(newSource);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression, org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression
    public void replaceTypeArguments(List<? extends FirTypeProjection> newTypeArguments) {
        newTypeArguments.getClass();
    }

    /* JADX INFO: renamed from: setAnnotations-GqUYU-s, reason: not valid java name */
    public void m542setAnnotationsGqUYUs(List<FirAnnotation> list) {
        this.annotations = list;
    }

    public void setCalleeReference(FirNamedReference firNamedReference) {
        firNamedReference.getClass();
        this.calleeReference = firNamedReference;
    }

    public void setConeTypeOrNull(ConeKotlinType coneKotlinType) {
        this.coneTypeOrNull = coneKotlinType;
    }

    public void setContextSensitiveAlternative(FirPropertyAccessExpression firPropertyAccessExpression) {
        this.contextSensitiveAlternative = firPropertyAccessExpression;
    }

    /* JADX INFO: renamed from: setNonFatalDiagnostics-GqUYU-s, reason: not valid java name */
    public void m543setNonFatalDiagnosticsGqUYUs(List<ConeDiagnostic> list) {
        this.nonFatalDiagnostics = list;
    }

    public void setSource(KtSourceElement ktSourceElement) {
        this.source = ktSourceElement;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression, org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public <D> FirWhenSubjectExpressionImpl transformAnnotations(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirTransformerUtilKt.m709transformInplaceaLnlfrU(m540getAnnotations5e3fPpI(), transformer, data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression, org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirResolvable
    public <D> FirWhenSubjectExpressionImpl transformCalleeReference(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        setCalleeReference((FirNamedReference) getCalleeReference().transform(transformer, data));
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <D> FirWhenSubjectExpressionImpl transformChildren(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        transformAnnotations((FirTransformer) transformer, (Object) data);
        transformCalleeReference((FirTransformer) transformer, (Object) data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression, org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirContextArgumentListOwner
    public /* bridge */ /* synthetic */ FirContextArgumentListOwner transformContextArguments(FirTransformer firTransformer, Object obj) {
        return transformContextArguments((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression, org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression
    public /* bridge */ /* synthetic */ FirPropertyAccessExpression transformExplicitReceiver(FirTransformer firTransformer, Object obj) {
        return transformExplicitReceiver((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression, org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression
    public /* bridge */ /* synthetic */ FirPropertyAccessExpression transformTypeArguments(FirTransformer firTransformer, Object obj) {
        return transformTypeArguments((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression, org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirResolvable
    public FirNamedReference getCalleeReference() {
        return this.calleeReference;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression, org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirContextArgumentListOwner
    public <D> FirWhenSubjectExpressionImpl transformContextArguments(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression, org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression
    public <D> FirWhenSubjectExpressionImpl transformExplicitReceiver(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression, org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression
    public <D> FirWhenSubjectExpressionImpl transformTypeArguments(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression, org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirContextArgumentListOwner
    public /* bridge */ /* synthetic */ FirPropertyAccessExpression transformContextArguments(FirTransformer firTransformer, Object obj) {
        return transformContextArguments((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression, org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression
    public /* bridge */ /* synthetic */ FirQualifiedAccessExpression transformExplicitReceiver(FirTransformer firTransformer, Object obj) {
        return transformExplicitReceiver((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression, org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression
    public /* bridge */ /* synthetic */ FirQualifiedAccessExpression transformTypeArguments(FirTransformer firTransformer, Object obj) {
        return transformTypeArguments((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression, org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirContextArgumentListOwner
    public /* bridge */ /* synthetic */ FirQualifiedAccessExpression transformContextArguments(FirTransformer firTransformer, Object obj) {
        return transformContextArguments((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression, org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression
    public /* bridge */ /* synthetic */ FirWhenSubjectExpression transformExplicitReceiver(FirTransformer firTransformer, Object obj) {
        return transformExplicitReceiver((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression, org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression
    public /* bridge */ /* synthetic */ FirWhenSubjectExpression transformTypeArguments(FirTransformer firTransformer, Object obj) {
        return transformTypeArguments((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression, org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirContextArgumentListOwner
    public /* bridge */ /* synthetic */ FirWhenSubjectExpression transformContextArguments(FirTransformer firTransformer, Object obj) {
        return transformContextArguments((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public /* bridge */ /* synthetic */ FirElement transformChildren(FirTransformer firTransformer, Object obj) {
        return transformChildren((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression, org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirExpression transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression, org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirPropertyAccessExpression transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression, org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirQualifiedAccessExpression transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression, org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirStatement transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression, org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirWhenSubjectExpression transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression, org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirAnnotationContainer transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression, org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirResolvable
    public /* bridge */ /* synthetic */ FirQualifiedAccessExpression transformCalleeReference(FirTransformer firTransformer, Object obj) {
        return transformCalleeReference((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression, org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirResolvable
    public /* bridge */ /* synthetic */ FirResolvable transformCalleeReference(FirTransformer firTransformer, Object obj) {
        return transformCalleeReference((FirTransformer<? super Object>) firTransformer, obj);
    }

    public /* synthetic */ FirWhenSubjectExpressionImpl(ConeKotlinType coneKotlinType, List list, KtSourceElement ktSourceElement, List list2, FirPropertyAccessExpression firPropertyAccessExpression, FirNamedReference firNamedReference, DefaultConstructorMarker defaultConstructorMarker) {
        this(coneKotlinType, list, ktSourceElement, list2, firPropertyAccessExpression, firNamedReference);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression, org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression
    public void replaceCalleeReference(FirNamedReference newCalleeReference) {
        newCalleeReference.getClass();
        setCalleeReference(newCalleeReference);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression, org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirResolvable
    public /* bridge */ /* synthetic */ FirWhenSubjectExpression transformCalleeReference(FirTransformer firTransformer, Object obj) {
        return transformCalleeReference((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression, org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirResolvable
    public /* bridge */ /* synthetic */ FirPropertyAccessExpression transformCalleeReference(FirTransformer firTransformer, Object obj) {
        return transformCalleeReference((FirTransformer<? super Object>) firTransformer, obj);
    }
}
