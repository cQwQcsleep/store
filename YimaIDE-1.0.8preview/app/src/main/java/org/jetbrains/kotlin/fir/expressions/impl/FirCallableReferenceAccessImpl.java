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
import org.jetbrains.kotlin.fir.FirImplementationDetail;
import org.jetbrains.kotlin.fir.MutableOrEmptyList;
import org.jetbrains.kotlin.fir.builder.FirBuilderDslKt;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirArgumentList;
import org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess;
import org.jetbrains.kotlin.fir.expressions.FirContextArgumentListOwner;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvable;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.UnresolvedExpressionTypeAccess;
import org.jetbrains.kotlin.fir.references.FirNamedReference;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirTransformerUtilKt;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b$\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u008b\u0001\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0005\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\b\u0012\b\u0010\r\u001a\u0004\u0018\u00010\b\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u0005\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017¢\u0006\u0004\b\u0018\u0010\u0019J5\u0010D\u001a\u00020E\"\u0004\b\u0000\u0010F\"\u0004\b\u0001\u0010G2\u0012\u0010H\u001a\u000e\u0012\u0004\u0012\u0002HF\u0012\u0004\u0012\u0002HG0I2\u0006\u0010J\u001a\u0002HGH\u0016¢\u0006\u0002\u0010KJ)\u0010L\u001a\u00020\u0000\"\u0004\b\u0000\u0010G2\f\u0010M\u001a\b\u0012\u0004\u0012\u0002HG0N2\u0006\u0010J\u001a\u0002HGH\u0016¢\u0006\u0002\u0010OJ)\u0010P\u001a\u00020\u0000\"\u0004\b\u0000\u0010G2\f\u0010M\u001a\b\u0012\u0004\u0012\u0002HG0N2\u0006\u0010J\u001a\u0002HGH\u0016¢\u0006\u0002\u0010OJ)\u0010Q\u001a\u00020\u0000\"\u0004\b\u0000\u0010G2\f\u0010M\u001a\b\u0012\u0004\u0012\u0002HG0N2\u0006\u0010J\u001a\u0002HGH\u0016¢\u0006\u0002\u0010OJ)\u0010R\u001a\u00020\u0000\"\u0004\b\u0000\u0010G2\f\u0010M\u001a\b\u0012\u0004\u0012\u0002HG0N2\u0006\u0010J\u001a\u0002HGH\u0016¢\u0006\u0002\u0010OJ)\u0010S\u001a\u00020\u0000\"\u0004\b\u0000\u0010G2\f\u0010M\u001a\b\u0012\u0004\u0012\u0002HG0N2\u0006\u0010J\u001a\u0002HGH\u0016¢\u0006\u0002\u0010OJ)\u0010T\u001a\u00020\u0000\"\u0004\b\u0000\u0010G2\f\u0010M\u001a\b\u0012\u0004\u0012\u0002HG0N2\u0006\u0010J\u001a\u0002HGH\u0016¢\u0006\u0002\u0010OJ)\u0010U\u001a\u00020\u0000\"\u0004\b\u0000\u0010G2\f\u0010M\u001a\b\u0012\u0004\u0012\u0002HG0N2\u0006\u0010J\u001a\u0002HGH\u0016¢\u0006\u0002\u0010OJ\u0012\u0010V\u001a\u00020E2\b\u0010W\u001a\u0004\u0018\u00010\u0003H\u0016J\u0016\u0010X\u001a\u00020E2\f\u0010Y\u001a\b\u0012\u0004\u0012\u00020\u00060ZH\u0016J\u0016\u0010[\u001a\u00020E2\f\u0010\\\u001a\b\u0012\u0004\u0012\u00020\b0ZH\u0016J\u0016\u0010]\u001a\u00020E2\f\u0010^\u001a\b\u0012\u0004\u0012\u00020\n0ZH\u0016J\u0012\u0010_\u001a\u00020E2\b\u0010`\u001a\u0004\u0018\u00010\bH\u0016J\u0012\u0010a\u001a\u00020E2\b\u0010b\u001a\u0004\u0018\u00010\bH\u0016J\u0012\u0010c\u001a\u00020E2\b\u0010d\u001a\u0004\u0018\u00010\bH\u0016J\u0016\u0010e\u001a\u00020E2\b\u0010f\u001a\u0004\u0018\u00010\u000fH\u0017b\u0002\bgJ\u0016\u0010h\u001a\u00020E2\f\u0010i\u001a\b\u0012\u0004\u0012\u00020\u00110ZH\u0016J\u0010\u0010j\u001a\u00020E2\u0006\u0010k\u001a\u00020\u0013H\u0016J\u0010\u0010j\u001a\u00020E2\u0006\u0010k\u001a\u00020lH\u0016J\u0010\u0010m\u001a\u00020E2\u0006\u0010n\u001a\u00020\u0015H\u0016J\u0012\u0010o\u001a\u00020E2\b\u0010p\u001a\u0004\u0018\u00010\u0017H\u0016R*\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0016@\u0016X\u0097\u000er\u0002\b ¢\u0006\u0014\n\u0000\u0012\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\"\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0096\u000e¢\u0006\u0010\n\u0002\u0010%\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\"\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005X\u0096\u000e¢\u0006\u0010\n\u0002\u0010%\u001a\u0004\b&\u0010\"\"\u0004\b'\u0010$R\"\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u0005X\u0096\u000e¢\u0006\u0010\n\u0002\u0010%\u001a\u0004\b(\u0010\"\"\u0004\b)\u0010$R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u001c\u0010\f\u001a\u0004\u0018\u00010\bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010+\"\u0004\b/\u0010-R\u001c\u0010\r\u001a\u0004\u0018\u00010\bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u0010+\"\u0004\b1\u0010-R\u001c\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u00103\"\u0004\b4\u00105R\"\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u0005X\u0096\u000e¢\u0006\u0010\n\u0002\u0010%\u001a\u0004\b6\u0010\"\"\u0004\b7\u0010$R\u001a\u0010\u0012\u001a\u00020\u0013X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u00109\"\u0004\b:\u0010;R\u001a\u0010\u0014\u001a\u00020\u0015X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010A\"\u0004\bB\u0010C¨\u0006q"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/impl/FirCallableReferenceAccessImpl;", "Lorg/jetbrains/kotlin/fir/expressions/FirCallableReferenceAccess;", "coneTypeOrNull", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "annotations", "Lorg/jetbrains/kotlin/fir/MutableOrEmptyList;", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "contextArguments", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "typeArguments", "Lorg/jetbrains/kotlin/fir/types/FirTypeProjection;", "explicitReceiver", "dispatchReceiver", "extensionReceiver", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "nonFatalDiagnostics", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "calleeReference", "Lorg/jetbrains/kotlin/fir/references/FirNamedReference;", "hasQuestionMarkAtLHS", Argument.Delimiters.none, "errorArgumentList", "Lorg/jetbrains/kotlin/fir/expressions/FirArgumentList;", "<init>", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Ljava/util/List;Ljava/util/List;Ljava/util/List;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/KtSourceElement;Ljava/util/List;Lorg/jetbrains/kotlin/fir/references/FirNamedReference;ZLorg/jetbrains/kotlin/fir/expressions/FirArgumentList;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "getConeTypeOrNull$annotations", "()V", "getConeTypeOrNull", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "setConeTypeOrNull", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)V", "Lorg/jetbrains/kotlin/fir/expressions/UnresolvedExpressionTypeAccess;", "getAnnotations-5e3fPpI", "()Ljava/util/List;", "setAnnotations-GqUYU-s", "(Ljava/util/List;)V", "Ljava/util/List;", "getContextArguments-5e3fPpI", "setContextArguments-GqUYU-s", "getTypeArguments-5e3fPpI", "setTypeArguments-GqUYU-s", "getExplicitReceiver", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "setExplicitReceiver", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)V", "getDispatchReceiver", "setDispatchReceiver", "getExtensionReceiver", "setExtensionReceiver", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "setSource", "(Lorg/jetbrains/kotlin/KtSourceElement;)V", "getNonFatalDiagnostics-5e3fPpI", "setNonFatalDiagnostics-GqUYU-s", "getCalleeReference", "()Lorg/jetbrains/kotlin/fir/references/FirNamedReference;", "setCalleeReference", "(Lorg/jetbrains/kotlin/fir/references/FirNamedReference;)V", "getHasQuestionMarkAtLHS", "()Z", "setHasQuestionMarkAtLHS", "(Z)V", "getErrorArgumentList", "()Lorg/jetbrains/kotlin/fir/expressions/FirArgumentList;", "setErrorArgumentList", "(Lorg/jetbrains/kotlin/fir/expressions/FirArgumentList;)V", "acceptChildren", Argument.Delimiters.none, "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)V", "transformChildren", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/expressions/impl/FirCallableReferenceAccessImpl;", "transformAnnotations", "transformContextArguments", "transformTypeArguments", "transformExplicitReceiver", "transformCalleeReference", "transformErrorArgumentList", "replaceConeTypeOrNull", "newConeTypeOrNull", "replaceAnnotations", "newAnnotations", Argument.Delimiters.none, "replaceContextArguments", "newContextArguments", "replaceTypeArguments", "newTypeArguments", "replaceExplicitReceiver", "newExplicitReceiver", "replaceDispatchReceiver", "newDispatchReceiver", "replaceExtensionReceiver", "newExtensionReceiver", "replaceSource", "newSource", "Lorg/jetbrains/kotlin/fir/FirImplementationDetail;", "replaceNonFatalDiagnostics", "newNonFatalDiagnostics", "replaceCalleeReference", "newCalleeReference", "Lorg/jetbrains/kotlin/fir/references/FirReference;", "replaceHasQuestionMarkAtLHS", "newHasQuestionMarkAtLHS", "replaceErrorArgumentList", "newErrorArgumentList", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirCallableReferenceAccessImpl extends FirCallableReferenceAccess {
    private List<FirAnnotation> annotations;
    private FirNamedReference calleeReference;
    private ConeKotlinType coneTypeOrNull;
    private List<FirExpression> contextArguments;
    private FirExpression dispatchReceiver;
    private FirArgumentList errorArgumentList;
    private FirExpression explicitReceiver;
    private FirExpression extensionReceiver;
    private boolean hasQuestionMarkAtLHS;
    private List<ConeDiagnostic> nonFatalDiagnostics;
    private KtSourceElement source;
    private List<FirTypeProjection> typeArguments;

    private FirCallableReferenceAccessImpl(ConeKotlinType coneKotlinType, List<FirAnnotation> list, List<FirExpression> list2, List<FirTypeProjection> list3, FirExpression firExpression, FirExpression firExpression2, FirExpression firExpression3, KtSourceElement ktSourceElement, List<ConeDiagnostic> list4, FirNamedReference firNamedReference, boolean z, FirArgumentList firArgumentList) {
        firNamedReference.getClass();
        this.coneTypeOrNull = coneKotlinType;
        this.annotations = list;
        this.contextArguments = list2;
        this.typeArguments = list3;
        this.explicitReceiver = firExpression;
        this.dispatchReceiver = firExpression2;
        this.extensionReceiver = firExpression3;
        this.source = ktSourceElement;
        this.nonFatalDiagnostics = list4;
        this.calleeReference = firNamedReference;
        this.hasQuestionMarkAtLHS = z;
        this.errorArgumentList = firArgumentList;
    }

    @UnresolvedExpressionTypeAccess
    public static /* synthetic */ void getConeTypeOrNull$annotations() {
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <R, D> void acceptChildren(FirVisitor<? extends R, ? super D> visitor, D data) {
        FirExpression extensionReceiver;
        FirExpression dispatchReceiver;
        visitor.getClass();
        Iterator<T> it = MutableOrEmptyList.m194boximpl(m386getAnnotations5e3fPpI()).iterator();
        while (it.hasNext()) {
            ((FirAnnotation) it.next()).accept(visitor, data);
        }
        Iterator<T> it2 = MutableOrEmptyList.m194boximpl(m387getContextArguments5e3fPpI()).iterator();
        while (it2.hasNext()) {
            ((FirExpression) it2.next()).accept(visitor, data);
        }
        Iterator<T> it3 = MutableOrEmptyList.m194boximpl(m389getTypeArguments5e3fPpI()).iterator();
        while (it3.hasNext()) {
            ((FirTypeProjection) it3.next()).accept(visitor, data);
        }
        FirExpression explicitReceiver = getExplicitReceiver();
        if (explicitReceiver != null) {
            explicitReceiver.accept(visitor, data);
        }
        if (getDispatchReceiver() != getExplicitReceiver() && (dispatchReceiver = getDispatchReceiver()) != null) {
            dispatchReceiver.accept(visitor, data);
        }
        if (getExtensionReceiver() != getExplicitReceiver() && getExtensionReceiver() != getDispatchReceiver() && (extensionReceiver = getExtensionReceiver()) != null) {
            extensionReceiver.accept(visitor, data);
        }
        getCalleeReference().accept(visitor, data);
        FirArgumentList errorArgumentList = getErrorArgumentList();
        if (errorArgumentList != null) {
            errorArgumentList.accept(visitor, data);
        }
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ List getAnnotations() {
        return MutableOrEmptyList.m194boximpl(m386getAnnotations5e3fPpI());
    }

    /* JADX INFO: renamed from: getAnnotations-5e3fPpI, reason: not valid java name */
    public List<FirAnnotation> m386getAnnotations5e3fPpI() {
        return this.annotations;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirExpression
    public ConeKotlinType getConeTypeOrNull() {
        return this.coneTypeOrNull;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirContextArgumentListOwner
    public /* bridge */ /* synthetic */ List getContextArguments() {
        return MutableOrEmptyList.m194boximpl(m387getContextArguments5e3fPpI());
    }

    /* JADX INFO: renamed from: getContextArguments-5e3fPpI, reason: not valid java name */
    public List<FirExpression> m387getContextArguments5e3fPpI() {
        return this.contextArguments;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression
    public FirExpression getDispatchReceiver() {
        return this.dispatchReceiver;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess
    public FirArgumentList getErrorArgumentList() {
        return this.errorArgumentList;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression
    public FirExpression getExplicitReceiver() {
        return this.explicitReceiver;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression
    public FirExpression getExtensionReceiver() {
        return this.extensionReceiver;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess
    public boolean getHasQuestionMarkAtLHS() {
        return this.hasQuestionMarkAtLHS;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression
    public /* bridge */ /* synthetic */ List getNonFatalDiagnostics() {
        return MutableOrEmptyList.m194boximpl(m388getNonFatalDiagnostics5e3fPpI());
    }

    /* JADX INFO: renamed from: getNonFatalDiagnostics-5e3fPpI, reason: not valid java name */
    public List<ConeDiagnostic> m388getNonFatalDiagnostics5e3fPpI() {
        return this.nonFatalDiagnostics;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.FirElement
    public KtSourceElement getSource() {
        return this.source;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression
    public /* bridge */ /* synthetic */ List getTypeArguments() {
        return MutableOrEmptyList.m194boximpl(m389getTypeArguments5e3fPpI());
    }

    /* JADX INFO: renamed from: getTypeArguments-5e3fPpI, reason: not valid java name */
    public List<FirTypeProjection> m389getTypeArguments5e3fPpI() {
        return this.typeArguments;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public void replaceAnnotations(List<? extends FirAnnotation> newAnnotations) {
        newAnnotations.getClass();
        m390setAnnotationsGqUYUs(FirBuilderDslKt.toMutableOrEmptyForImmutable(newAnnotations));
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirResolvable
    public void replaceCalleeReference(FirReference newCalleeReference) {
        newCalleeReference.getClass();
        if (newCalleeReference instanceof FirNamedReference) {
            replaceCalleeReference((FirNamedReference) newCalleeReference);
        } else {
            w01.a("Failed requirement.");
        }
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirExpression
    public void replaceConeTypeOrNull(ConeKotlinType newConeTypeOrNull) {
        setConeTypeOrNull(newConeTypeOrNull);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirContextArgumentListOwner
    public void replaceContextArguments(List<? extends FirExpression> newContextArguments) {
        newContextArguments.getClass();
        m391setContextArgumentsGqUYUs(FirBuilderDslKt.toMutableOrEmptyForImmutable(newContextArguments));
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression
    public void replaceDispatchReceiver(FirExpression newDispatchReceiver) {
        setDispatchReceiver(newDispatchReceiver);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess
    public void replaceErrorArgumentList(FirArgumentList newErrorArgumentList) {
        setErrorArgumentList(newErrorArgumentList);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression
    public void replaceExplicitReceiver(FirExpression newExplicitReceiver) {
        setExplicitReceiver(newExplicitReceiver);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression
    public void replaceExtensionReceiver(FirExpression newExtensionReceiver) {
        setExtensionReceiver(newExtensionReceiver);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess
    public void replaceHasQuestionMarkAtLHS(boolean newHasQuestionMarkAtLHS) {
        setHasQuestionMarkAtLHS(newHasQuestionMarkAtLHS);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression
    public void replaceNonFatalDiagnostics(List<? extends ConeDiagnostic> newNonFatalDiagnostics) {
        newNonFatalDiagnostics.getClass();
        m392setNonFatalDiagnosticsGqUYUs(FirBuilderDslKt.toMutableOrEmptyForImmutable(newNonFatalDiagnostics));
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression
    @FirImplementationDetail
    public void replaceSource(KtSourceElement newSource) {
        setSource(newSource);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression
    public void replaceTypeArguments(List<? extends FirTypeProjection> newTypeArguments) {
        newTypeArguments.getClass();
        m393setTypeArgumentsGqUYUs(FirBuilderDslKt.toMutableOrEmptyForImmutable(newTypeArguments));
    }

    /* JADX INFO: renamed from: setAnnotations-GqUYU-s, reason: not valid java name */
    public void m390setAnnotationsGqUYUs(List<FirAnnotation> list) {
        this.annotations = list;
    }

    public void setCalleeReference(FirNamedReference firNamedReference) {
        firNamedReference.getClass();
        this.calleeReference = firNamedReference;
    }

    public void setConeTypeOrNull(ConeKotlinType coneKotlinType) {
        this.coneTypeOrNull = coneKotlinType;
    }

    /* JADX INFO: renamed from: setContextArguments-GqUYU-s, reason: not valid java name */
    public void m391setContextArgumentsGqUYUs(List<FirExpression> list) {
        this.contextArguments = list;
    }

    public void setDispatchReceiver(FirExpression firExpression) {
        this.dispatchReceiver = firExpression;
    }

    public void setErrorArgumentList(FirArgumentList firArgumentList) {
        this.errorArgumentList = firArgumentList;
    }

    public void setExplicitReceiver(FirExpression firExpression) {
        this.explicitReceiver = firExpression;
    }

    public void setExtensionReceiver(FirExpression firExpression) {
        this.extensionReceiver = firExpression;
    }

    public void setHasQuestionMarkAtLHS(boolean z) {
        this.hasQuestionMarkAtLHS = z;
    }

    /* JADX INFO: renamed from: setNonFatalDiagnostics-GqUYU-s, reason: not valid java name */
    public void m392setNonFatalDiagnosticsGqUYUs(List<ConeDiagnostic> list) {
        this.nonFatalDiagnostics = list;
    }

    public void setSource(KtSourceElement ktSourceElement) {
        this.source = ktSourceElement;
    }

    /* JADX INFO: renamed from: setTypeArguments-GqUYU-s, reason: not valid java name */
    public void m393setTypeArgumentsGqUYUs(List<FirTypeProjection> list) {
        this.typeArguments = list;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public <D> FirCallableReferenceAccessImpl transformAnnotations(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirTransformerUtilKt.m709transformInplaceaLnlfrU(m386getAnnotations5e3fPpI(), transformer, data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirResolvable
    public <D> FirCallableReferenceAccessImpl transformCalleeReference(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        setCalleeReference((FirNamedReference) getCalleeReference().transform(transformer, data));
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <D> FirCallableReferenceAccessImpl transformChildren(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        transformAnnotations((FirTransformer) transformer, (Object) data);
        transformContextArguments((FirTransformer) transformer, (Object) data);
        transformTypeArguments((FirTransformer) transformer, (Object) data);
        FirExpression explicitReceiver = getExplicitReceiver();
        setExplicitReceiver(explicitReceiver != null ? (FirExpression) explicitReceiver.transform(transformer, data) : null);
        if (getDispatchReceiver() != getExplicitReceiver()) {
            FirExpression dispatchReceiver = getDispatchReceiver();
            setDispatchReceiver(dispatchReceiver != null ? (FirExpression) dispatchReceiver.transform(transformer, data) : null);
        }
        if (getExtensionReceiver() != getExplicitReceiver() && getExtensionReceiver() != getDispatchReceiver()) {
            FirExpression extensionReceiver = getExtensionReceiver();
            setExtensionReceiver(extensionReceiver != null ? (FirExpression) extensionReceiver.transform(transformer, data) : null);
        }
        transformCalleeReference((FirTransformer) transformer, (Object) data);
        transformErrorArgumentList((FirTransformer) transformer, (Object) data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirContextArgumentListOwner
    public <D> FirCallableReferenceAccessImpl transformContextArguments(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirTransformerUtilKt.m709transformInplaceaLnlfrU(m387getContextArguments5e3fPpI(), transformer, data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess
    public <D> FirCallableReferenceAccessImpl transformErrorArgumentList(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirArgumentList errorArgumentList = getErrorArgumentList();
        setErrorArgumentList(errorArgumentList != null ? (FirArgumentList) errorArgumentList.transform(transformer, data) : null);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression
    public <D> FirCallableReferenceAccessImpl transformExplicitReceiver(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirExpression explicitReceiver = getExplicitReceiver();
        setExplicitReceiver(explicitReceiver != null ? (FirExpression) explicitReceiver.transform(transformer, data) : null);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression
    public <D> FirCallableReferenceAccessImpl transformTypeArguments(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        FirTransformerUtilKt.m709transformInplaceaLnlfrU(m389getTypeArguments5e3fPpI(), transformer, data);
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirResolvable
    public FirNamedReference getCalleeReference() {
        return this.calleeReference;
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirCallableReferenceAccess transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirContextArgumentListOwner
    public /* bridge */ /* synthetic */ FirContextArgumentListOwner transformContextArguments(FirTransformer firTransformer, Object obj) {
        return transformContextArguments((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression
    public /* bridge */ /* synthetic */ FirQualifiedAccessExpression transformTypeArguments(FirTransformer firTransformer, Object obj) {
        return transformTypeArguments((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirExpression transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirContextArgumentListOwner
    public /* bridge */ /* synthetic */ FirQualifiedAccessExpression transformContextArguments(FirTransformer firTransformer, Object obj) {
        return transformContextArguments((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression
    public /* bridge */ /* synthetic */ FirCallableReferenceAccess transformTypeArguments(FirTransformer firTransformer, Object obj) {
        return transformTypeArguments((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirQualifiedAccessExpression transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirContextArgumentListOwner
    public /* bridge */ /* synthetic */ FirCallableReferenceAccess transformContextArguments(FirTransformer firTransformer, Object obj) {
        return transformContextArguments((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirStatement transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirAnnotationContainer transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirResolvable
    public /* bridge */ /* synthetic */ FirQualifiedAccessExpression transformCalleeReference(FirTransformer firTransformer, Object obj) {
        return transformCalleeReference((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirResolvable
    public /* bridge */ /* synthetic */ FirResolvable transformCalleeReference(FirTransformer firTransformer, Object obj) {
        return transformCalleeReference((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess
    public void replaceCalleeReference(FirNamedReference newCalleeReference) {
        newCalleeReference.getClass();
        setCalleeReference(newCalleeReference);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression, org.jetbrains.kotlin.fir.expressions.FirResolvable
    public /* bridge */ /* synthetic */ FirCallableReferenceAccess transformCalleeReference(FirTransformer firTransformer, Object obj) {
        return transformCalleeReference((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess
    public /* bridge */ /* synthetic */ FirCallableReferenceAccess transformErrorArgumentList(FirTransformer firTransformer, Object obj) {
        return transformErrorArgumentList((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression
    public /* bridge */ /* synthetic */ FirQualifiedAccessExpression transformExplicitReceiver(FirTransformer firTransformer, Object obj) {
        return transformExplicitReceiver((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess, org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression
    public /* bridge */ /* synthetic */ FirCallableReferenceAccess transformExplicitReceiver(FirTransformer firTransformer, Object obj) {
        return transformExplicitReceiver((FirTransformer<? super Object>) firTransformer, obj);
    }

    public /* synthetic */ FirCallableReferenceAccessImpl(ConeKotlinType coneKotlinType, List list, List list2, List list3, FirExpression firExpression, FirExpression firExpression2, FirExpression firExpression3, KtSourceElement ktSourceElement, List list4, FirNamedReference firNamedReference, boolean z, FirArgumentList firArgumentList, DefaultConstructorMarker defaultConstructorMarker) {
        this(coneKotlinType, list, list2, list3, firExpression, firExpression2, firExpression3, ktSourceElement, list4, firNamedReference, z, firArgumentList);
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public /* bridge */ /* synthetic */ FirElement transformChildren(FirTransformer firTransformer, Object obj) {
        return transformChildren((FirTransformer<? super Object>) firTransformer, obj);
    }
}
