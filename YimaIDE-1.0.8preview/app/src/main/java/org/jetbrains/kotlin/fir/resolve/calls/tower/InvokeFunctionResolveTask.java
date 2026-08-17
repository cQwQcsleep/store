package org.jetbrains.kotlin.fir.resolve.calls.tower;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SpillingKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineConstantsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.BodyResolveComponents;
import org.jetbrains.kotlin.fir.resolve.calls.ExpressionReceiverValue;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CallInfo;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CandidateCollector;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CandidateFactory;
import org.jetbrains.kotlin.resolve.calls.tasks.ExplicitReceiverKind;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ\u0014\u0010\u0010\u001a\u00020\t*\u00020\t2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u001e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0086@¢\u0006\u0002\u0010\u0019J\u001e\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0086@¢\u0006\u0002\u0010\u0019J\u001e\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0086@¢\u0006\u0002\u0010\u0019J.\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00162\u0006\u0010 \u001a\u00020\t2\u0006\u0010!\u001a\u00020\"H\u0082@¢\u0006\u0002\u0010#R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/tower/InvokeFunctionResolveTask;", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/FirBaseTowerResolveTask;", "components", "Lorg/jetbrains/kotlin/fir/resolve/BodyResolveComponents;", "manager", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerResolveManager;", "towerDataElementsForName", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerDataElementsForName;", "receiverGroup", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroup;", "collector", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CandidateCollector;", "candidateFactory", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CandidateFactory;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/BodyResolveComponents;Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerResolveManager;Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerDataElementsForName;Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroup;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CandidateCollector;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CandidateFactory;)V", "withGivenInvokeReceiverGroup", "invokeResolvePriority", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/InvokeResolvePriority;", "runResolverForInvoke", Argument.Delimiters.none, "info", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;", "invokeReceiverValue", "Lorg/jetbrains/kotlin/fir/resolve/calls/ExpressionReceiverValue;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;Lorg/jetbrains/kotlin/fir/resolve/calls/ExpressionReceiverValue;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "runResolverForBuiltinInvokeExtensionWithExplicitArgument", "runResolverForBuiltinInvokeExtensionWithImplicitArgument", "processLevelForRegularInvoke", "towerLevel", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerLevel;", "callInfo", "group", "explicitReceiverKind", "Lorg/jetbrains/kotlin/resolve/calls/tasks/ExplicitReceiverKind;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerLevel;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroup;Lorg/jetbrains/kotlin/resolve/calls/tasks/ExplicitReceiverKind;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class InvokeFunctionResolveTask extends FirBaseTowerResolveTask {
    private final TowerGroup receiverGroup;

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.resolve.calls.tower.InvokeFunctionResolveTask$processLevelForRegularInvoke$1, reason: invalid class name */
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
            return InvokeFunctionResolveTask.this.processLevelForRegularInvoke(null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.resolve.calls.tower.InvokeFunctionResolveTask$runResolverForBuiltinInvokeExtensionWithExplicitArgument$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class C00501 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;
        /* synthetic */ Object result;

        public C00501(Continuation<? super C00501> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return InvokeFunctionResolveTask.this.runResolverForBuiltinInvokeExtensionWithExplicitArgument(null, null, this);
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.resolve.calls.tower.InvokeFunctionResolveTask$runResolverForBuiltinInvokeExtensionWithImplicitArgument$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class C00511 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;
        /* synthetic */ Object result;

        public C00511(Continuation<? super C00511> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return InvokeFunctionResolveTask.this.runResolverForBuiltinInvokeExtensionWithImplicitArgument(null, null, this);
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.resolve.calls.tower.InvokeFunctionResolveTask$runResolverForInvoke$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class C00521 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;
        /* synthetic */ Object result;

        public C00521(Continuation<? super C00521> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return InvokeFunctionResolveTask.this.runResolverForInvoke(null, null, this);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InvokeFunctionResolveTask(BodyResolveComponents bodyResolveComponents, TowerResolveManager towerResolveManager, TowerDataElementsForName towerDataElementsForName, TowerGroup towerGroup, CandidateCollector candidateCollector, CandidateFactory candidateFactory) {
        super(bodyResolveComponents, towerResolveManager, towerDataElementsForName, candidateCollector, candidateFactory);
        bodyResolveComponents.getClass();
        towerResolveManager.getClass();
        towerDataElementsForName.getClass();
        towerGroup.getClass();
        candidateCollector.getClass();
        candidateFactory.getClass();
        this.receiverGroup = towerGroup;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:8:0x0014  */
    public final Object processLevelForRegularInvoke(TowerLevel towerLevel, CallInfo callInfo, TowerGroup towerGroup, ExplicitReceiverKind explicitReceiverKind, Continuation<? super Unit> continuation) {
        AnonymousClass1 anonymousClass1;
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
        AnonymousClass1 anonymousClass2 = anonymousClass1;
        Object objProcessLevel = anonymousClass2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = anonymousClass2.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(objProcessLevel);
            TowerGroup towerGroupWithGivenInvokeReceiverGroup = withGivenInvokeReceiverGroup(towerGroup, InvokeResolvePriority.COMMON_INVOKE);
            anonymousClass2.L$0 = SpillingKt.nullOutSpilledVariable(towerLevel);
            anonymousClass2.L$1 = SpillingKt.nullOutSpilledVariable(callInfo);
            anonymousClass2.L$2 = SpillingKt.nullOutSpilledVariable(towerGroup);
            anonymousClass2.L$3 = SpillingKt.nullOutSpilledVariable(explicitReceiverKind);
            anonymousClass2.L$4 = SpillingKt.nullOutSpilledVariable(this);
            anonymousClass2.L$5 = SpillingKt.nullOutSpilledVariable(towerLevel);
            anonymousClass2.L$6 = SpillingKt.nullOutSpilledVariable(callInfo);
            anonymousClass2.L$7 = SpillingKt.nullOutSpilledVariable(towerGroupWithGivenInvokeReceiverGroup);
            anonymousClass2.L$8 = SpillingKt.nullOutSpilledVariable(explicitReceiverKind);
            anonymousClass2.label = 1;
            objProcessLevel = processLevel(towerLevel, callInfo, towerGroupWithGivenInvokeReceiverGroup, explicitReceiverKind, anonymousClass2);
            if (objProcessLevel == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i2 != 1) {
                k2d.a(CoroutineConstantsKt.ILLEGAL_STATE_ERROR_MESSAGE);
                return null;
            }
            ResultKt.throwOnFailure(objProcessLevel);
        }
        ((Boolean) objProcessLevel).booleanValue();
        return Unit.INSTANCE;
    }

    private final TowerGroup withGivenInvokeReceiverGroup(TowerGroup towerGroup, InvokeResolvePriority invokeResolvePriority) {
        return towerGroup.InvokeReceiver(this.receiverGroup, invokeResolvePriority);
    }

    /* JADX WARN: Code duplicated, block: B:8:0x0014  */
    public final Object runResolverForBuiltinInvokeExtensionWithExplicitArgument(CallInfo callInfo, ExpressionReceiverValue expressionReceiverValue, Continuation<? super Unit> continuation) {
        C00501 c00501;
        if (continuation instanceof C00501) {
            c00501 = (C00501) continuation;
            int i = c00501.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                c00501.label = i - Integer.MIN_VALUE;
            } else {
                c00501 = new C00501(continuation);
            }
        } else {
            c00501 = new C00501(continuation);
        }
        C00501 c00502 = c00501;
        Object objProcessLevel = c00502.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c00502.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(objProcessLevel);
            DispatchReceiverMemberScopeTowerLevel dispatchReceiverMemberScopeTowerLevel$default = FirBaseTowerResolveTask.toDispatchReceiverMemberScopeTowerLevel$default(this, expressionReceiverValue, null, false, 3, null);
            TowerGroup towerGroupWithGivenInvokeReceiverGroup = withGivenInvokeReceiverGroup(TowerGroup.INSTANCE.getMember(), InvokeResolvePriority.INVOKE_EXTENSION);
            ExplicitReceiverKind explicitReceiverKind = ExplicitReceiverKind.DISPATCH_RECEIVER;
            c00502.L$0 = SpillingKt.nullOutSpilledVariable(callInfo);
            c00502.L$1 = SpillingKt.nullOutSpilledVariable(expressionReceiverValue);
            c00502.L$2 = SpillingKt.nullOutSpilledVariable(this);
            c00502.L$3 = SpillingKt.nullOutSpilledVariable(dispatchReceiverMemberScopeTowerLevel$default);
            c00502.L$4 = SpillingKt.nullOutSpilledVariable(callInfo);
            c00502.L$5 = SpillingKt.nullOutSpilledVariable(towerGroupWithGivenInvokeReceiverGroup);
            c00502.L$6 = SpillingKt.nullOutSpilledVariable(explicitReceiverKind);
            c00502.label = 1;
            objProcessLevel = processLevel(dispatchReceiverMemberScopeTowerLevel$default, callInfo, towerGroupWithGivenInvokeReceiverGroup, explicitReceiverKind, c00502);
            if (objProcessLevel == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i2 != 1) {
                k2d.a(CoroutineConstantsKt.ILLEGAL_STATE_ERROR_MESSAGE);
                return null;
            }
            ResultKt.throwOnFailure(objProcessLevel);
        }
        ((Boolean) objProcessLevel).booleanValue();
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:17:0x0076  */
    /* JADX WARN: Code duplicated, block: B:19:0x00e5 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x00e3 -> B:20:0x00e6). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public final java.lang.Object runResolverForBuiltinInvokeExtensionWithImplicitArgument(org.jetbrains.kotlin.fir.resolve.calls.candidate.CallInfo r13, org.jetbrains.kotlin.fir.resolve.calls.ExpressionReceiverValue r14, kotlin.coroutines.Continuation<? super kotlin.Unit> r15) {
        /*
            Method dump skipped, instruction units count: 240
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.kotlin.fir.resolve.calls.tower.InvokeFunctionResolveTask.runResolverForBuiltinInvokeExtensionWithImplicitArgument(org.jetbrains.kotlin.fir.resolve.calls.candidate.CallInfo, org.jetbrains.kotlin.fir.resolve.calls.ExpressionReceiverValue, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:36:0x01b4  */
    /* JADX WARN: Code duplicated, block: B:38:0x01c3  */
    /* JADX WARN: Code duplicated, block: B:40:0x01cd  */
    /* JADX WARN: Code duplicated, block: B:43:0x0232  */
    /* JADX WARN: Code duplicated, block: B:46:0x0245  */
    /* JADX WARN: Code duplicated, block: B:50:0x025f  */
    /* JADX WARN: Code duplicated, block: B:53:0x0277  */
    /* JADX WARN: Code duplicated, block: B:56:0x02c9  */
    /* JADX WARN: Code duplicated, block: B:58:0x02d1  */
    /* JADX WARN: Code duplicated, block: B:62:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:63:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:7:0x0017  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:30:0x018c -> B:31:0x0191). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:56:0x02c9 -> B:57:0x02cd). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public final java.lang.Object runResolverForInvoke(org.jetbrains.kotlin.fir.resolve.calls.candidate.CallInfo r22, org.jetbrains.kotlin.fir.resolve.calls.ExpressionReceiverValue r23, kotlin.coroutines.Continuation<? super kotlin.Unit> r24) {
        /*
            Method dump skipped, instruction units count: 733
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.kotlin.fir.resolve.calls.tower.InvokeFunctionResolveTask.runResolverForInvoke(org.jetbrains.kotlin.fir.resolve.calls.candidate.CallInfo, org.jetbrains.kotlin.fir.resolve.calls.ExpressionReceiverValue, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
