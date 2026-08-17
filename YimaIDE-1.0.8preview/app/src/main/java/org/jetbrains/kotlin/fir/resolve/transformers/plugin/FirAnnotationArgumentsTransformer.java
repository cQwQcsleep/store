package org.jetbrains.kotlin.fir.resolve.transformers.plugin;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.transformers.ReturnTypeCalculator;
import org.jetbrains.kotlin.fir.resolve.transformers.ReturnTypeCalculatorForFullBodyResolve;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.BodyResolveContext;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirAbstractBodyResolveTransformerDispatcher;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirDeclarationsResolveTransformer;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirExpressionsResolveTransformer;
import org.jetbrains.kotlin.util.PrivateForInline;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0016\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ%\u0010\u001e\u001a\u0002H\u001f\"\u0004\b\u0000\u0010\u001f2\f\u0010 \u001a\b\u0012\u0004\u0012\u0002H\u001f0!H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\"R\u0011\u0010\u000e\u001a\u00020\u000f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R(\u0010\u0016\u001a\u00020\u00178\u0006@\u0006X\u0087\u000er\u0002\b\u001d¢\u0006\u0014\n\u0000\u0012\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u0016\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u0017\u0010#\u001a\u00020\u00158F¢\u0006\f\u0012\u0004\b$\u0010\u0019\u001a\u0004\b%\u0010&\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006'"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/plugin/FirAnnotationArgumentsTransformer;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirAbstractBodyResolveTransformerDispatcher;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "resolvePhase", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "outerBodyResolveContext", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/BodyResolveContext;", "returnTypeCalculator", "Lorg/jetbrains/kotlin/fir/resolve/transformers/ReturnTypeCalculator;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/BodyResolveContext;Lorg/jetbrains/kotlin/fir/resolve/transformers/ReturnTypeCalculator;)V", "expressionsTransformer", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirExpressionsResolveTransformer;", "getExpressionsTransformer", "()Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirExpressionsResolveTransformer;", "declarationsResolveTransformerForAnnotationArguments", "Lorg/jetbrains/kotlin/fir/resolve/transformers/plugin/FirDeclarationsResolveTransformerForAnnotationArguments;", "usualDeclarationTransformer", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirDeclarationsResolveTransformer;", "isInsideAnnotationArgument", Argument.Delimiters.none, "isInsideAnnotationArgument$annotations", "()V", "()Z", "setInsideAnnotationArgument", "(Z)V", "Lorg/jetbrains/kotlin/util/PrivateForInline;", "insideAnnotationArgument", "R", "action", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "declarationsTransformer", "getDeclarationsTransformer$annotations", "getDeclarationsTransformer", "()Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirDeclarationsResolveTransformer;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class FirAnnotationArgumentsTransformer extends FirAbstractBodyResolveTransformerDispatcher {
    private final FirDeclarationsResolveTransformerForAnnotationArguments declarationsResolveTransformerForAnnotationArguments;
    private final FirExpressionsResolveTransformer expressionsTransformer;
    private boolean isInsideAnnotationArgument;
    private final FirDeclarationsResolveTransformer usualDeclarationTransformer;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirAnnotationArgumentsTransformer(FirSession firSession, ScopeSession scopeSession, FirResolvePhase firResolvePhase, BodyResolveContext bodyResolveContext, ReturnTypeCalculator returnTypeCalculator) {
        super(firSession, firResolvePhase, false, scopeSession, returnTypeCalculator, bodyResolveContext, true);
        firSession.getClass();
        scopeSession.getClass();
        firResolvePhase.getClass();
        returnTypeCalculator.getClass();
        this.expressionsTransformer = new FirExpressionTransformerForAnnotationArguments(this);
        this.declarationsResolveTransformerForAnnotationArguments = new FirDeclarationsResolveTransformerForAnnotationArguments(this);
        this.usualDeclarationTransformer = new FirDeclarationsResolveTransformer(this);
    }

    public static /* synthetic */ void getDeclarationsTransformer$annotations() {
    }

    @PrivateForInline
    public static /* synthetic */ void isInsideAnnotationArgument$annotations() {
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirAbstractBodyResolveTransformerDispatcher
    public final FirDeclarationsResolveTransformer getDeclarationsTransformer() {
        return this.isInsideAnnotationArgument ? this.usualDeclarationTransformer : this.declarationsResolveTransformerForAnnotationArguments;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirAbstractBodyResolveTransformerDispatcher
    public final FirExpressionsResolveTransformer getExpressionsTransformer() {
        return this.expressionsTransformer;
    }

    public final <R> R insideAnnotationArgument(Function0<? extends R> action) {
        action.getClass();
        boolean isInsideAnnotationArgument = getIsInsideAnnotationArgument();
        setInsideAnnotationArgument(true);
        try {
            return (R) action.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            setInsideAnnotationArgument(isInsideAnnotationArgument);
            InlineMarker.finallyEnd(1);
        }
    }

    /* JADX INFO: renamed from: isInsideAnnotationArgument, reason: from getter */
    public final boolean getIsInsideAnnotationArgument() {
        return this.isInsideAnnotationArgument;
    }

    public final void setInsideAnnotationArgument(boolean z) {
        this.isInsideAnnotationArgument = z;
    }

    public /* synthetic */ FirAnnotationArgumentsTransformer(FirSession firSession, ScopeSession scopeSession, FirResolvePhase firResolvePhase, BodyResolveContext bodyResolveContext, ReturnTypeCalculator returnTypeCalculator, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(firSession, scopeSession, firResolvePhase, (i & 8) != 0 ? null : bodyResolveContext, (i & 16) != 0 ? ReturnTypeCalculatorForFullBodyResolve.INSTANCE.getDefault() : returnTypeCalculator);
    }
}
