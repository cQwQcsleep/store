package org.jetbrains.kotlin.fir.resolve.calls.stages;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SpillingKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineConstantsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirVisibilityChecker;
import org.jetbrains.kotlin.fir.FirVisibilityCheckerKt;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.declarations.FirCodeFragment;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.FragmentVisibilityError;
import org.jetbrains.kotlin.fir.resolve.calls.HiddenCandidate;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionContext;
import org.jetbrains.kotlin.fir.resolve.calls.VisibilityError;
import org.jetbrains.kotlin.fir.resolve.calls.VisibilityUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CallInfo;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CheckerSink;
import org.jetbrains.kotlin.fir.scopes.impl.TypeAliasConstructorInfo;
import org.jetbrains.kotlin.fir.scopes.impl.TypeAliasConstructorsSubstitutingScopeKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0082@¢\u0006\u0002\u0010\tJ.\u0010\n\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000fH\u0096@R\u00020\u0006R\u00020\fj\u0006\u0010\u000b\u001a\u00020\u0006j\u0006\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010\u0010¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/stages/CheckVisibility;", "Lorg/jetbrains/kotlin/fir/resolve/calls/stages/ResolutionStage;", "<init>", "()V", "yieldVisibilityError", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;", "callInfo", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "check", "sink", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "context", "candidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CheckerSink;Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CheckVisibility extends ResolutionStage {
    public static final CheckVisibility INSTANCE = new CheckVisibility();

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.resolve.calls.stages.CheckVisibility$check$1, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return CheckVisibility.this.check(null, null, null, this);
        }
    }

    private CheckVisibility() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object yieldVisibilityError(CheckerSink checkerSink, CallInfo callInfo, Continuation<? super Unit> continuation) {
        checkerSink.reportDiagnostic(CollectionsKt.getOrNull(callInfo.getContainingDeclarations(), 1) instanceof FirCodeFragment ? FragmentVisibilityError.INSTANCE : VisibilityError.INSTANCE);
        return checkerSink.getNeedYielding() ? checkerSink.yield(continuation) : Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:50:0x014f  */
    /* JADX WARN: Code duplicated, block: B:53:0x0182 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:54:0x0183  */
    /* JADX WARN: Code duplicated, block: B:56:0x0187  */
    /* JADX WARN: Code duplicated, block: B:57:0x018b  */
    /* JADX WARN: Code duplicated, block: B:59:0x018e  */
    /* JADX WARN: Code duplicated, block: B:7:0x0019  */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x01e2, code lost:
    
        if (r1.yieldVisibilityError(r11, r5, r3) == r4) goto L67;
     */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.resolve.calls.stages.ResolutionStage
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object check(CheckerSink checkerSink, ResolutionContext resolutionContext, Candidate candidate, Continuation<? super Unit> continuation) throws KotlinIllegalArgumentExceptionWithAttachments {
        AnonymousClass1 anonymousClass1;
        FirVisibilityChecker visibilityChecker;
        FirMemberDeclaration firMemberDeclaration;
        ResolutionContext resolutionContext2;
        ResolutionContext resolutionContext3;
        CheckerSink checkerSink2;
        FirMemberDeclaration firMemberDeclaration2;
        FirVisibilityChecker firVisibilityChecker;
        Candidate candidate2;
        FirMemberDeclaration firMemberDeclaration3;
        FirConstructor firConstructor;
        TypeAliasConstructorInfo typeAliasConstructorInfo;
        FirTypeAliasSymbol typeAliasSymbol;
        Object objYieldVisibilityError;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            int i = anonymousClass1.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label = i - Integer.MIN_VALUE;
            } else {
                anonymousClass1 = new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = new AnonymousClass1(continuation);
        }
        Object obj = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = anonymousClass1.label;
        if (i2 != 0) {
            if (i2 == 1) {
                firMemberDeclaration2 = (FirMemberDeclaration) anonymousClass1.L$4;
                visibilityChecker = (FirVisibilityChecker) anonymousClass1.L$3;
                Candidate candidate3 = (Candidate) anonymousClass1.L$2;
                resolutionContext3 = (ResolutionContext) anonymousClass1.L$1;
                checkerSink2 = (CheckerSink) anonymousClass1.L$0;
                ResultKt.throwOnFailure(obj);
                candidate = candidate3;
            } else {
                if (i2 == 2) {
                    ResultKt.throwOnFailure(obj);
                    return obj;
                }
                if (i2 != 3) {
                    k2d.a(CoroutineConstantsKt.ILLEGAL_STATE_ERROR_MESSAGE);
                    return null;
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
        ResultKt.throwOnFailure(obj);
        visibilityChecker = FirVisibilityCheckerKt.getVisibilityChecker(candidate.getCallInfo().getSession());
        FirDeclaration fir = candidate.getSymbol().getFir();
        firMemberDeclaration = fir instanceof FirMemberDeclaration ? (FirMemberDeclaration) fir : null;
        if (firMemberDeclaration == null) {
            return Unit.INSTANCE;
        }
        if (firMemberDeclaration instanceof FirConstructor) {
            ConeClassLikeLookupTag classLikeLookupTagIfAny = ConeTypeUtilsKt.getClassLikeLookupTagIfAny(FirTypeUtilsKt.getConeType(((FirConstructor) firMemberDeclaration).getReturnTypeRef()));
            resolutionContext2 = resolutionContext;
            FirClassLikeSymbol<?> symbol = classLikeLookupTagIfAny != null ? ToSymbolUtilsKt.toSymbol((SessionHolder) resolutionContext2, classLikeLookupTagIfAny) : null;
            if ((symbol instanceof FirRegularClassSymbol) && ((FirRegularClass) ((FirRegularClassSymbol) symbol).getFir()).getClassKind().isSingleton()) {
                HiddenCandidate hiddenCandidate = HiddenCandidate.INSTANCE;
                checkerSink.reportDiagnostic(hiddenCandidate);
                if (checkerSink.getNeedYielding()) {
                    anonymousClass1.L$0 = checkerSink;
                    anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(resolutionContext2);
                    candidate = candidate;
                    anonymousClass1.L$2 = candidate;
                    anonymousClass1.L$3 = visibilityChecker;
                    anonymousClass1.L$4 = firMemberDeclaration;
                    anonymousClass1.L$5 = SpillingKt.nullOutSpilledVariable(symbol);
                    anonymousClass1.L$6 = SpillingKt.nullOutSpilledVariable(checkerSink);
                    anonymousClass1.L$7 = SpillingKt.nullOutSpilledVariable(hiddenCandidate);
                    anonymousClass1.L$8 = SpillingKt.nullOutSpilledVariable(checkerSink);
                    anonymousClass1.label = 1;
                    if (checkerSink.yield(anonymousClass1) != coroutine_suspended) {
                        resolutionContext3 = resolutionContext2;
                        checkerSink2 = checkerSink;
                        firMemberDeclaration2 = firMemberDeclaration;
                    }
                } else {
                    Unit unit = Unit.INSTANCE;
                }
                return coroutine_suspended;
            }
            resolutionContext3 = resolutionContext2;
            checkerSink2 = checkerSink;
            firVisibilityChecker = visibilityChecker;
            candidate2 = candidate;
            firMemberDeclaration3 = firMemberDeclaration;
            if (VisibilityUtilsKt.isVisible$default(firVisibilityChecker, firMemberDeclaration3, candidate2, false, 4, null)) {
                if (firMemberDeclaration3 instanceof FirConstructor) {
                    firConstructor = (FirConstructor) firMemberDeclaration3;
                } else {
                    firConstructor = null;
                }
                if (firConstructor != null && (typeAliasConstructorInfo = TypeAliasConstructorsSubstitutingScopeKt.getTypeAliasConstructorInfo(firConstructor)) != null && (typeAliasSymbol = typeAliasConstructorInfo.getTypeAliasSymbol()) != null && !VisibilityUtilsKt.isVisible$default(firVisibilityChecker, (FirMemberDeclaration) typeAliasSymbol.getFir(), candidate2, false, 4, null)) {
                    CheckVisibility checkVisibility = INSTANCE;
                    CallInfo callInfo = candidate2.getCallInfo();
                    anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(checkerSink2);
                    anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(resolutionContext3);
                    anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(candidate2);
                    anonymousClass1.L$3 = SpillingKt.nullOutSpilledVariable(firVisibilityChecker);
                    anonymousClass1.L$4 = SpillingKt.nullOutSpilledVariable(firMemberDeclaration3);
                    anonymousClass1.L$5 = SpillingKt.nullOutSpilledVariable(typeAliasSymbol);
                    anonymousClass1.L$6 = null;
                    anonymousClass1.L$7 = null;
                    anonymousClass1.L$8 = null;
                    anonymousClass1.label = 3;
                }
                return Unit.INSTANCE;
            }
            CallInfo callInfo2 = candidate2.getCallInfo();
            anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(checkerSink2);
            anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(resolutionContext3);
            anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(candidate2);
            anonymousClass1.L$3 = SpillingKt.nullOutSpilledVariable(firVisibilityChecker);
            anonymousClass1.L$4 = SpillingKt.nullOutSpilledVariable(firMemberDeclaration3);
            anonymousClass1.L$5 = null;
            anonymousClass1.L$6 = null;
            anonymousClass1.L$7 = null;
            anonymousClass1.L$8 = null;
            anonymousClass1.label = 2;
            objYieldVisibilityError = yieldVisibilityError(checkerSink2, callInfo2, anonymousClass1);
            if (objYieldVisibilityError != coroutine_suspended) {
                return objYieldVisibilityError;
            }
            return coroutine_suspended;
        }
        resolutionContext2 = resolutionContext;
        resolutionContext3 = resolutionContext2;
        checkerSink2 = checkerSink;
        firVisibilityChecker = visibilityChecker;
        candidate2 = candidate;
        firMemberDeclaration3 = firMemberDeclaration;
        if (VisibilityUtilsKt.isVisible$default(firVisibilityChecker, firMemberDeclaration3, candidate2, false, 4, null)) {
            if (firMemberDeclaration3 instanceof FirConstructor) {
                firConstructor = (FirConstructor) firMemberDeclaration3;
            } else {
                firConstructor = null;
            }
            if (firConstructor != null) {
                CheckVisibility checkVisibility2 = INSTANCE;
                CallInfo callInfo3 = candidate2.getCallInfo();
                anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(checkerSink2);
                anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(resolutionContext3);
                anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(candidate2);
                anonymousClass1.L$3 = SpillingKt.nullOutSpilledVariable(firVisibilityChecker);
                anonymousClass1.L$4 = SpillingKt.nullOutSpilledVariable(firMemberDeclaration3);
                anonymousClass1.L$5 = SpillingKt.nullOutSpilledVariable(typeAliasSymbol);
                anonymousClass1.L$6 = null;
                anonymousClass1.L$7 = null;
                anonymousClass1.L$8 = null;
                anonymousClass1.label = 3;
            }
            return Unit.INSTANCE;
        }
        CallInfo callInfo4 = candidate2.getCallInfo();
        anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(checkerSink2);
        anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(resolutionContext3);
        anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(candidate2);
        anonymousClass1.L$3 = SpillingKt.nullOutSpilledVariable(firVisibilityChecker);
        anonymousClass1.L$4 = SpillingKt.nullOutSpilledVariable(firMemberDeclaration3);
        anonymousClass1.L$5 = null;
        anonymousClass1.L$6 = null;
        anonymousClass1.L$7 = null;
        anonymousClass1.L$8 = null;
        anonymousClass1.label = 2;
        objYieldVisibilityError = yieldVisibilityError(checkerSink2, callInfo4, anonymousClass1);
        if (objYieldVisibilityError != coroutine_suspended) {
            return objYieldVisibilityError;
        }
        return coroutine_suspended;
        firMemberDeclaration = firMemberDeclaration2;
        firVisibilityChecker = visibilityChecker;
        candidate2 = candidate;
        firMemberDeclaration3 = firMemberDeclaration;
        if (VisibilityUtilsKt.isVisible$default(firVisibilityChecker, firMemberDeclaration3, candidate2, false, 4, null)) {
            if (firMemberDeclaration3 instanceof FirConstructor) {
                firConstructor = (FirConstructor) firMemberDeclaration3;
            } else {
                firConstructor = null;
            }
            if (firConstructor != null) {
                CheckVisibility checkVisibility3 = INSTANCE;
                CallInfo callInfo5 = candidate2.getCallInfo();
                anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(checkerSink2);
                anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(resolutionContext3);
                anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(candidate2);
                anonymousClass1.L$3 = SpillingKt.nullOutSpilledVariable(firVisibilityChecker);
                anonymousClass1.L$4 = SpillingKt.nullOutSpilledVariable(firMemberDeclaration3);
                anonymousClass1.L$5 = SpillingKt.nullOutSpilledVariable(typeAliasSymbol);
                anonymousClass1.L$6 = null;
                anonymousClass1.L$7 = null;
                anonymousClass1.L$8 = null;
                anonymousClass1.label = 3;
            }
            return Unit.INSTANCE;
        }
        CallInfo callInfo6 = candidate2.getCallInfo();
        anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(checkerSink2);
        anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(resolutionContext3);
        anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(candidate2);
        anonymousClass1.L$3 = SpillingKt.nullOutSpilledVariable(firVisibilityChecker);
        anonymousClass1.L$4 = SpillingKt.nullOutSpilledVariable(firMemberDeclaration3);
        anonymousClass1.L$5 = null;
        anonymousClass1.L$6 = null;
        anonymousClass1.L$7 = null;
        anonymousClass1.L$8 = null;
        anonymousClass1.label = 2;
        objYieldVisibilityError = yieldVisibilityError(checkerSink2, callInfo6, anonymousClass1);
        if (objYieldVisibilityError != coroutine_suspended) {
            return objYieldVisibilityError;
        }
        return coroutine_suspended;
    }
}
