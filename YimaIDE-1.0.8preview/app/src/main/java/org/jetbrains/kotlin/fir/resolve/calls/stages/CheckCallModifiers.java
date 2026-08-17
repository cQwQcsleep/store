package org.jetbrains.kotlin.fir.resolve.calls.stages;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCallOrigin;
import org.jetbrains.kotlin.fir.resolve.calls.InfixCallOfNonInfixFunction;
import org.jetbrains.kotlin.fir.resolve.calls.OperatorCallOfConstructor;
import org.jetbrains.kotlin.fir.resolve.calls.OperatorCallOfNonOperatorFunction;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionContext;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CheckerSink;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J.\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0096@R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/stages/CheckCallModifiers;", "Lorg/jetbrains/kotlin/fir/resolve/calls/stages/ResolutionStage;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;", "sink", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "context", "candidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CheckCallModifiers extends ResolutionStage {
    public static final CheckCallModifiers INSTANCE = new CheckCallModifiers();

    private CheckCallModifiers() {
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0043  */
    /* JADX WARN: Code duplicated, block: B:13:0x0055  */
    /* JADX WARN: Code duplicated, block: B:15:0x0068  */
    /* JADX WARN: Code duplicated, block: B:16:0x0071  */
    /* JADX WARN: Code duplicated, block: B:18:0x007b  */
    /* JADX WARN: Code duplicated, block: B:20:0x008d  */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.resolve.calls.stages.ResolutionStage
    public Object check(CheckerSink checkerSink, ResolutionContext resolutionContext, Candidate candidate, Continuation<? super Unit> continuation) {
        FirNamedFunctionSymbol firNamedFunctionSymbol;
        FirNamedFunctionSymbol firNamedFunctionSymbol2;
        if (candidate.getCallInfo().getCallSite() instanceof FirFunctionCall) {
            FirBasedSymbol<?> symbol = candidate.getSymbol();
            if (symbol instanceof FirNamedFunctionSymbol) {
                if (((FirFunctionCall) candidate.getCallInfo().getCallSite()).getOrigin() == FirFunctionCallOrigin.Infix) {
                    FirNamedFunctionSymbol firNamedFunctionSymbol3 = (FirNamedFunctionSymbol) symbol;
                    if (!((FirMemberDeclaration) firNamedFunctionSymbol3.getFir()).getStatus().isInfix()) {
                        checkerSink.reportDiagnostic(new InfixCallOfNonInfixFunction(firNamedFunctionSymbol3));
                    } else if (((FirFunctionCall) candidate.getCallInfo().getCallSite()).getOrigin() == FirFunctionCallOrigin.Operator) {
                        firNamedFunctionSymbol2 = (FirNamedFunctionSymbol) symbol;
                        if (!((FirMemberDeclaration) firNamedFunctionSymbol2.getFir()).getStatus().isOperator()) {
                            checkerSink.reportDiagnostic(new OperatorCallOfNonOperatorFunction(firNamedFunctionSymbol2));
                        } else if (candidate.getCallInfo().isImplicitInvoke()) {
                            firNamedFunctionSymbol = (FirNamedFunctionSymbol) symbol;
                            if (!((FirMemberDeclaration) firNamedFunctionSymbol.getFir()).getStatus().isOperator()) {
                                checkerSink.reportDiagnostic(new OperatorCallOfNonOperatorFunction(firNamedFunctionSymbol));
                            }
                        }
                    } else if (candidate.getCallInfo().isImplicitInvoke()) {
                        firNamedFunctionSymbol = (FirNamedFunctionSymbol) symbol;
                        if (!((FirMemberDeclaration) firNamedFunctionSymbol.getFir()).getStatus().isOperator()) {
                            checkerSink.reportDiagnostic(new OperatorCallOfNonOperatorFunction(firNamedFunctionSymbol));
                        }
                    }
                } else if (((FirFunctionCall) candidate.getCallInfo().getCallSite()).getOrigin() == FirFunctionCallOrigin.Operator) {
                    firNamedFunctionSymbol2 = (FirNamedFunctionSymbol) symbol;
                    if (!((FirMemberDeclaration) firNamedFunctionSymbol2.getFir()).getStatus().isOperator()) {
                        checkerSink.reportDiagnostic(new OperatorCallOfNonOperatorFunction(firNamedFunctionSymbol2));
                    } else if (candidate.getCallInfo().isImplicitInvoke()) {
                        firNamedFunctionSymbol = (FirNamedFunctionSymbol) symbol;
                        if (!((FirMemberDeclaration) firNamedFunctionSymbol.getFir()).getStatus().isOperator()) {
                            checkerSink.reportDiagnostic(new OperatorCallOfNonOperatorFunction(firNamedFunctionSymbol));
                        }
                    }
                } else if (candidate.getCallInfo().isImplicitInvoke()) {
                    firNamedFunctionSymbol = (FirNamedFunctionSymbol) symbol;
                    if (!((FirMemberDeclaration) firNamedFunctionSymbol.getFir()).getStatus().isOperator()) {
                        checkerSink.reportDiagnostic(new OperatorCallOfNonOperatorFunction(firNamedFunctionSymbol));
                    }
                }
            } else if ((symbol instanceof FirConstructorSymbol) && ((FirFunctionCall) candidate.getCallInfo().getCallSite()).getOrigin() == FirFunctionCallOrigin.Operator) {
                checkerSink.reportDiagnostic(new OperatorCallOfConstructor((FirConstructorSymbol) symbol));
            }
        }
        return Unit.INSTANCE;
    }
}
