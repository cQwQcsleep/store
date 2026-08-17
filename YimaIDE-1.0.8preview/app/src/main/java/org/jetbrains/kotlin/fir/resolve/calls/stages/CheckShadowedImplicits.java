package org.jetbrains.kotlin.fir.resolve.calls.stages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirThisReceiverExpression;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ImplicitValue;
import org.jetbrains.kotlin.fir.resolve.calls.InaccessibleImplicitReceiverValue;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionContext;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CheckerSink;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J.\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0096@R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\fJ=\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0012J\u0012\u0010\u0013\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0014*\u00020\u0015H\u0002¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/stages/CheckShadowedImplicits;", "Lorg/jetbrains/kotlin/fir/resolve/calls/stages/ResolutionStage;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;", "sink", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "context", "candidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "checkImpl", "receiverValueToCheck", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtom;", "kind", "Lorg/jetbrains/kotlin/fir/resolve/calls/stages/ImplicitKind;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtom;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;Lorg/jetbrains/kotlin/fir/resolve/calls/stages/ImplicitKind;)V", "implicitlyReferencedSymbolOrNull", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CheckShadowedImplicits extends ResolutionStage {
    public static final CheckShadowedImplicits INSTANCE = new CheckShadowedImplicits();

    private CheckShadowedImplicits() {
    }

    private final void checkImpl(CheckerSink checkerSink, ResolutionContext resolutionContext, ConeResolutionAtom coneResolutionAtom, Candidate candidate, ImplicitKind implicitKind) {
        FirBasedSymbol<?> firBasedSymbolImplicitlyReferencedSymbolOrNull = implicitlyReferencedSymbolOrNull(FirExpressionUtilKt.unwrapSmartcastExpression(coneResolutionAtom.getExpression()));
        if (firBasedSymbolImplicitlyReferencedSymbolOrNull == null) {
            return;
        }
        Collection<ImplicitValue<?>> implicitValues = resolutionContext.getBodyResolveContext().getImplicitValueStorage().getImplicitValues();
        ArrayList arrayList = new ArrayList();
        for (Object obj : implicitValues) {
            if (!(((ImplicitValue) obj) instanceof InaccessibleImplicitReceiverValue)) {
                arrayList.add(obj);
            }
        }
        FirBasedSymbol firBasedSymbolContainingDeclarationIfParameter = ResolutionStagesKt.containingDeclarationIfParameter(firBasedSymbolImplicitlyReferencedSymbolOrNull);
        ArrayList arrayList2 = new ArrayList();
        boolean z = false;
        for (Object obj2 : arrayList) {
            if (z) {
                arrayList2.add(obj2);
            } else if (Intrinsics.areEqual(firBasedSymbolContainingDeclarationIfParameter, ResolutionStagesKt.containingDeclarationIfParameter(((ImplicitValue) obj2).getBoundSymbol()))) {
                arrayList2.add(obj2);
                z = true;
            }
        }
        if (arrayList2.isEmpty()) {
            return;
        }
        CheckDslScopeViolation.INSTANCE.check(checkerSink, resolutionContext, coneResolutionAtom.getExpression(), candidate, firBasedSymbolImplicitlyReferencedSymbolOrNull, arrayList2);
        if (implicitKind != ImplicitKind.ContextArgument) {
            CheckReceiverShadowedByContextParameter.INSTANCE.check(checkerSink, resolutionContext, candidate, arrayList2, implicitKind);
        }
    }

    private final FirBasedSymbol<?> implicitlyReferencedSymbolOrNull(FirExpression firExpression) {
        if (firExpression instanceof FirThisReceiverExpression) {
            FirThisReceiverExpression firThisReceiverExpression = (FirThisReceiverExpression) firExpression;
            if (firThisReceiverExpression.getIsImplicit()) {
                return FirReferenceUtilsKt.getSymbol(firThisReceiverExpression.getCalleeReference());
            }
        }
        if (firExpression instanceof FirPropertyAccessExpression) {
            FirPropertyAccessExpression firPropertyAccessExpression = (FirPropertyAccessExpression) firExpression;
            KtSourceElement source = firPropertyAccessExpression.getSource();
            if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
                return FirReferenceUtilsKt.getSymbol(firPropertyAccessExpression.getCalleeReference());
            }
        }
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.stages.ResolutionStage
    public Object check(CheckerSink checkerSink, ResolutionContext resolutionContext, Candidate candidate, Continuation<? super Unit> continuation) {
        ConeResolutionAtom dispatchReceiver = candidate.getDispatchReceiver();
        if (dispatchReceiver != null) {
            INSTANCE.checkImpl(checkerSink, resolutionContext, dispatchReceiver, candidate, ImplicitKind.DispatchReceiver);
        }
        ConeResolutionAtom chosenExtensionReceiver = candidate.getChosenExtensionReceiver();
        if (chosenExtensionReceiver != null) {
            INSTANCE.checkImpl(checkerSink, resolutionContext, chosenExtensionReceiver, candidate, ImplicitKind.ExtensionReceiver);
        }
        List<ConeResolutionAtom> contextArguments = candidate.getContextArguments();
        if (contextArguments != null) {
            Iterator<T> it = contextArguments.iterator();
            while (it.hasNext()) {
                INSTANCE.checkImpl(checkerSink, resolutionContext, (ConeResolutionAtom) it.next(), candidate, ImplicitKind.ContextArgument);
            }
        }
        if (candidate.getCallInfo().isImplicitInvoke()) {
            Integer expectedContextParameterCountForInvoke = candidate.getExpectedContextParameterCountForInvoke();
            int i = 0;
            int iIntValue = expectedContextParameterCountForInvoke != null ? expectedContextParameterCountForInvoke.intValue() : 0;
            for (Object obj : candidate.getArguments()) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                INSTANCE.checkImpl(checkerSink, resolutionContext, (ConeResolutionAtom) obj, candidate, i < iIntValue ? ImplicitKind.ContextArgument : ImplicitKind.ExtensionReceiver);
                i = i2;
            }
        }
        return Unit.INSTANCE;
    }
}
