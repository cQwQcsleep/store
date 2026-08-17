package org.jetbrains.kotlin.fir.resolve.calls.tower;

import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineConstantsKt;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirTowerDataElement;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.builder.FirExpressionStubBuilder;
import org.jetbrains.kotlin.fir.resolve.BodyResolveComponents;
import org.jetbrains.kotlin.fir.resolve.DoubleColonLHS;
import org.jetbrains.kotlin.fir.resolve.ResolveUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.ClassQualifierReceiver;
import org.jetbrains.kotlin.fir.resolve.calls.CompanionExtensionPolicy;
import org.jetbrains.kotlin.fir.resolve.calls.ConstructorFilter;
import org.jetbrains.kotlin.fir.resolve.calls.ExpressionReceiverValue;
import org.jetbrains.kotlin.fir.resolve.calls.QualifierReceiver;
import org.jetbrains.kotlin.fir.resolve.calls.QualifierReceiverKt;
import org.jetbrains.kotlin.fir.resolve.calls.ReceiverValue;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CallInfo;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CallKind;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CallableReferenceInfo;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CandidateCollector;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CandidateFactory;
import org.jetbrains.kotlin.fir.scopes.FirScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirAbstractImportingScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirPackageMemberScope;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.resolve.calls.tasks.ExplicitReceiverKind;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000ª\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\b\u0010\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\u001e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0086@¢\u0006\u0002\u0010\u0016JR\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\u0010\b\u0002\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u001d2\u0010\b\u0002\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u001dH\u0082@¢\u0006\u0002\u0010 J\u001f\u0010!\u001a\u00020\u000f*\u0004\u0018\u00010\u001eH\u0002\u0082\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0000\u001a\u0004\b\u0003\u0010\u0000J \u0010\"\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010#\u001a\u0004\u0018\u00010$H\u0082@¢\u0006\u0002\u0010%J \u0010&\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010#\u001a\u0004\u0018\u00010$H\u0082@¢\u0006\u0002\u0010%J(\u0010'\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010(\u001a\u00020)2\b\b\u0002\u0010\u0018\u001a\u00020\u0019H\u0086@¢\u0006\u0002\u0010*J \u0010+\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010,\u001a\u00020\u000fH\u0086@¢\u0006\u0002\u0010-JB\u0010.\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020\u00192\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dH\u0082@¢\u0006\u0002\u00102J\u001e\u00103\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u00104\u001a\u000205H\u0086@¢\u0006\u0002\u00106J.\u00107\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u00108\u001a\u0002092\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0082@¢\u0006\u0002\u0010:JP\u0010;\u001a\u00020\u00112\u0006\u0010<\u001a\u00020\u001e2\u0006\u0010=\u001a\u00020>2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010?\u001a\u00020\u00192\u0006\u0010@\u001a\u00020A2\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\u000e\b\u0002\u0010B\u001a\b\u0012\u0004\u0012\u00020\u00110CH\u0082H¢\u0006\u0002\u0010DJ`\u0010E\u001a\u00020\u00112\n\u0010(\u001a\u0006\u0012\u0002\b\u00030F2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u00192\u0010\u0010G\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030F0\u001d2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\u0006\u0010,\u001a\u00020\u000fH\u0082@¢\u0006\u0002\u0010HJ>\u0010I\u001a\u00020\u00112\u0006\u00108\u001a\u0002092\u0006\u0010J\u001a\u00020\u001e2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010K\u001a\u00020L2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0018\u001a\u00020\u0019H\u0082@¢\u0006\u0002\u0010MR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006N"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/tower/FirTowerResolveTask;", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/FirBaseTowerResolveTask;", "components", "Lorg/jetbrains/kotlin/fir/resolve/BodyResolveComponents;", "manager", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerResolveManager;", "towerDataElementsForName", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerDataElementsForName;", "collector", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CandidateCollector;", "candidateFactory", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CandidateFactory;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/BodyResolveComponents;Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerResolveManager;Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerDataElementsForName;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CandidateCollector;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CandidateFactory;)V", "companionExtensionsEnabled", Argument.Delimiters.none, "runResolverForQualifierReceiver", Argument.Delimiters.none, "info", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;", "resolvedQualifier", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "enumerateTowerLevelsForCompanionExtensions", "parentGroup", "Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroup;", "explicitReceiverKind", "Lorg/jetbrains/kotlin/resolve/calls/tasks/ExplicitReceiverKind;", "emptyScopes", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "scopesWithoutCompanionExtensions", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroup;Lorg/jetbrains/kotlin/resolve/calls/tasks/ExplicitReceiverKind;Ljava/util/Set;Ljava/util/Set;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "canContainCompanionExtensions", "processQualifierScopes", "qualifierReceiver", "Lorg/jetbrains/kotlin/fir/resolve/calls/QualifierReceiver;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;Lorg/jetbrains/kotlin/fir/resolve/calls/QualifierReceiver;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "processClassifierScope", "runResolverForExpressionReceiver", "receiver", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroup;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "runResolverForNoReceiver", "skipSynthetics", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "enumerateTowerLevelsForCompanionExtensionsForImplicitReceiver", "staticScopeOwnerSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "group", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroup;Ljava/util/Set;Ljava/util/Set;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "runResolverForSuperReceiver", "superCall", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "processExtensionsThatHideMembers", "receiverValue", "Lorg/jetbrains/kotlin/fir/resolve/calls/ReceiverValue;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;Lorg/jetbrains/kotlin/fir/resolve/calls/ReceiverValue;Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroup;Lorg/jetbrains/kotlin/resolve/calls/tasks/ExplicitReceiverKind;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "processScopeForExplicitReceiver", "scope", "explicitReceiverValue", "Lorg/jetbrains/kotlin/fir/resolve/calls/ExpressionReceiverValue;", "towerGroup", "companionExtensionPolicy", "Lorg/jetbrains/kotlin/fir/resolve/calls/CompanionExtensionPolicy;", "onEmptyLevel", "Lkotlin/Function0;", "(Lorg/jetbrains/kotlin/fir/scopes/FirScope;Lorg/jetbrains/kotlin/fir/resolve/calls/ExpressionReceiverValue;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroup;Lorg/jetbrains/kotlin/fir/resolve/calls/CompanionExtensionPolicy;Lorg/jetbrains/kotlin/resolve/calls/tasks/ExplicitReceiverKind;Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "processCandidatesWithGivenImplicitReceiverAsValue", "Lorg/jetbrains/kotlin/fir/resolve/calls/ImplicitReceiverValue;", "implicitReceiverValuesWithEmptyScopes", "(Lorg/jetbrains/kotlin/fir/resolve/calls/ImplicitReceiverValue;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroup;Ljava/util/Set;Ljava/util/Set;Ljava/util/Set;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "processHideMembersLevel", "topLevelScope", "index", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/resolve/calls/ReceiverValue;Lorg/jetbrains/kotlin/fir/scopes/FirScope;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;ILorg/jetbrains/kotlin/resolve/calls/tasks/ExplicitReceiverKind;Lorg/jetbrains/kotlin/fir/resolve/calls/tower/TowerGroup;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class FirTowerResolveTask extends FirBaseTowerResolveTask {
    private final boolean companionExtensionsEnabled;

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.resolve.calls.tower.FirTowerResolveTask$enumerateTowerLevelsForCompanionExtensions$1, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$12;
        Object L$13;
        Object L$14;
        Object L$15;
        Object L$16;
        Object L$17;
        Object L$18;
        Object L$19;
        Object L$2;
        Object L$20;
        Object L$21;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FirTowerResolveTask.this.enumerateTowerLevelsForCompanionExtensions(null, null, null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.resolve.calls.tower.FirTowerResolveTask$processCandidatesWithGivenImplicitReceiverAsValue$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class C00411 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$12;
        Object L$13;
        Object L$14;
        Object L$15;
        Object L$16;
        Object L$17;
        Object L$18;
        Object L$19;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        public C00411(Continuation<? super C00411> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FirTowerResolveTask.this.processCandidatesWithGivenImplicitReceiverAsValue(null, null, null, null, null, null, false, this);
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.resolve.calls.tower.FirTowerResolveTask$processClassifierScope$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class C00421 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        int label;
        /* synthetic */ Object result;

        public C00421(Continuation<? super C00421> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FirTowerResolveTask.this.processClassifierScope(null, null, this);
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.resolve.calls.tower.FirTowerResolveTask$processExtensionsThatHideMembers$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class C00431 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        int label;
        /* synthetic */ Object result;

        public C00431(Continuation<? super C00431> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FirTowerResolveTask.this.processExtensionsThatHideMembers(null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.resolve.calls.tower.FirTowerResolveTask$processHideMembersLevel$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class C00441 extends ContinuationImpl {
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

        public C00441(Continuation<? super C00441> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FirTowerResolveTask.this.processHideMembersLevel(null, null, null, 0, null, null, this);
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.resolve.calls.tower.FirTowerResolveTask$processQualifierScopes$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class C00451 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        int label;
        /* synthetic */ Object result;

        public C00451(Continuation<? super C00451> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FirTowerResolveTask.this.processQualifierScopes(null, null, this);
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.resolve.calls.tower.FirTowerResolveTask$runResolverForExpressionReceiver$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class C00461 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$12;
        Object L$13;
        Object L$14;
        Object L$15;
        Object L$16;
        Object L$17;
        Object L$18;
        Object L$19;
        Object L$2;
        Object L$20;
        Object L$21;
        Object L$22;
        Object L$23;
        Object L$24;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        int label;
        /* synthetic */ Object result;

        public C00461(Continuation<? super C00461> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FirTowerResolveTask.this.runResolverForExpressionReceiver(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.resolve.calls.tower.FirTowerResolveTask$runResolverForNoReceiver$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class C00471 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        Object L$10;
        Object L$11;
        Object L$12;
        Object L$13;
        Object L$14;
        Object L$15;
        Object L$16;
        Object L$17;
        Object L$18;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        Object L$8;
        Object L$9;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        public C00471(Continuation<? super C00471> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FirTowerResolveTask.this.runResolverForNoReceiver(null, false, this);
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.resolve.calls.tower.FirTowerResolveTask$runResolverForQualifierReceiver$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class C00481 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        int label;
        /* synthetic */ Object result;

        public C00481(Continuation<? super C00481> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FirTowerResolveTask.this.runResolverForQualifierReceiver(null, null, this);
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.resolve.calls.tower.FirTowerResolveTask$runResolverForSuperReceiver$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class C00491 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        Object L$6;
        Object L$7;
        int label;
        /* synthetic */ Object result;

        public C00491(Continuation<? super C00491> continuation) {
            super(continuation);
        }

        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return FirTowerResolveTask.this.runResolverForSuperReceiver(null, null, this);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirTowerResolveTask(BodyResolveComponents bodyResolveComponents, TowerResolveManager towerResolveManager, TowerDataElementsForName towerDataElementsForName, CandidateCollector candidateCollector, CandidateFactory candidateFactory) {
        super(bodyResolveComponents, towerResolveManager, towerDataElementsForName, candidateCollector, candidateFactory);
        bodyResolveComponents.getClass();
        towerResolveManager.getClass();
        towerDataElementsForName.getClass();
        candidateCollector.getClass();
        candidateFactory.getClass();
        this.companionExtensionsEnabled = LanguageVersionUtilsKt.isEnabled(this, LanguageFeature.CompanionBlocksAndExtensions);
    }

    private final boolean canContainCompanionExtensions(FirScope firScope) {
        return (firScope instanceof FirPackageMemberScope) || (firScope instanceof FirAbstractImportingScope);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:26:0x00e1  */
    /* JADX WARN: Code duplicated, block: B:28:0x00f5  */
    /* JADX WARN: Code duplicated, block: B:7:0x0017  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:41:0x01c3 -> B:42:0x01cb). Please report as a decompilation issue!!! */
    public final Object enumerateTowerLevelsForCompanionExtensions(CallInfo callInfo, FirResolvedQualifier firResolvedQualifier, TowerGroup towerGroup, ExplicitReceiverKind explicitReceiverKind, Set<FirScope> set, Set<FirScope> set2, Continuation<? super Unit> continuation) {
        AnonymousClass1 anonymousClass1;
        CallInfo callInfo2;
        TowerGroup towerGroup2;
        ExplicitReceiverKind explicitReceiverKind2;
        Set<FirScope> set3;
        FirResolvedQualifier firResolvedQualifier2;
        Iterator it;
        int i;
        AnonymousClass1 anonymousClass2;
        ExpressionReceiverValue expressionReceiverValue;
        Set<FirScope> set4;
        FirRegularClassSymbol firRegularClassSymbolFullyExpandedClass;
        int i2;
        FirScope scope;
        Iterator it2;
        boolean z;
        ScopeBasedTowerLevel scopeBasedTowerLevel;
        Object obj;
        FirScope firScope;
        int i3;
        Set<FirScope> set5;
        ExplicitReceiverKind explicitReceiverKind3;
        FirResolvedQualifier firResolvedQualifier3;
        FirTowerResolveTask firTowerResolveTask = this;
        if (continuation instanceof AnonymousClass1) {
            anonymousClass1 = (AnonymousClass1) continuation;
            int i4 = anonymousClass1.label;
            if ((i4 & Integer.MIN_VALUE) != 0) {
                anonymousClass1.label = i4 - Integer.MIN_VALUE;
            } else {
                anonymousClass1 = firTowerResolveTask.new AnonymousClass1(continuation);
            }
        } else {
            anonymousClass1 = firTowerResolveTask.new AnonymousClass1(continuation);
        }
        Object obj2 = anonymousClass1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i5 = anonymousClass1.label;
        ClassKind classKind = null;
        boolean z2 = true;
        if (i5 == 0) {
            ResultKt.throwOnFailure(obj2);
            FirClassLikeSymbol<?> symbol = firResolvedQualifier.getSymbol();
            if (symbol != null && (firRegularClassSymbolFullyExpandedClass = DeclarationUtilsKt.fullyExpandedClass(firTowerResolveTask, symbol)) != null) {
                classKind = firRegularClassSymbolFullyExpandedClass.getClassKind();
            }
            if (classKind == null || classKind == ClassKind.OBJECT) {
                return Unit.INSTANCE;
            }
            ExpressionReceiverValue expressionReceiverValue2 = new ExpressionReceiverValue(firResolvedQualifier);
            callInfo2 = callInfo;
            towerGroup2 = towerGroup;
            explicitReceiverKind2 = explicitReceiverKind;
            set3 = set;
            firResolvedQualifier2 = firResolvedQualifier;
            it = firTowerResolveTask.getTowerDataElementsForName().getNonLocalTowerDataElements().iterator();
            i = 0;
            anonymousClass2 = anonymousClass1;
            expressionReceiverValue = expressionReceiverValue2;
            set4 = set2;
            while (it.hasNext()) {
                i2 = i + 1;
                FirTowerDataElement firTowerDataElement = (FirTowerDataElement) it.next();
                scope = firTowerDataElement.getScope();
                if (firTowerResolveTask.canContainCompanionExtensions(scope) || firTowerDataElement.getIsLocal() || ((set3 != null && set3.contains(scope) == z2) || (set4 != null && set4.contains(scope) == z2))) {
                    i = i2;
                    anonymousClass2 = anonymousClass2;
                    set4 = set4;
                    it = it;
                    firResolvedQualifier2 = firResolvedQualifier2;
                    z2 = z2;
                    coroutine_suspended = coroutine_suspended;
                    firTowerResolveTask = this;
                } else {
                    TowerGroup towerGroupNonLocal = towerGroup2.NonLocal(i);
                    CompanionExtensionPolicy companionExtensionPolicy = CompanionExtensionPolicy.OnlyCompanionExtensions;
                    Set<FirScope> set6 = set4;
                    AnonymousClass1 anonymousClass3 = anonymousClass2;
                    FirResolvedQualifier firResolvedQualifier4 = firResolvedQualifier2;
                    it2 = it;
                    ScopeBasedTowerLevel scopeBasedTowerLevel$default = FirBaseTowerResolveTask.toScopeBasedTowerLevel$default(firTowerResolveTask, scope, expressionReceiverValue, false, null, companionExtensionPolicy, null, 22, null);
                    scopeBasedTowerLevel$default.getClass();
                    anonymousClass3.L$0 = callInfo2;
                    anonymousClass3.L$1 = SpillingKt.nullOutSpilledVariable(firResolvedQualifier4);
                    anonymousClass3.L$2 = towerGroup2;
                    anonymousClass3.L$3 = explicitReceiverKind2;
                    anonymousClass3.L$4 = set3;
                    anonymousClass3.L$5 = set6;
                    anonymousClass3.L$6 = expressionReceiverValue;
                    anonymousClass3.L$7 = it2;
                    anonymousClass3.L$8 = SpillingKt.nullOutSpilledVariable(firTowerDataElement);
                    anonymousClass3.L$9 = scope;
                    anonymousClass3.L$10 = SpillingKt.nullOutSpilledVariable(this);
                    anonymousClass3.L$11 = SpillingKt.nullOutSpilledVariable(scope);
                    anonymousClass3.L$12 = SpillingKt.nullOutSpilledVariable(expressionReceiverValue);
                    anonymousClass3.L$13 = SpillingKt.nullOutSpilledVariable(callInfo2);
                    anonymousClass3.L$14 = SpillingKt.nullOutSpilledVariable(towerGroupNonLocal);
                    anonymousClass3.L$15 = SpillingKt.nullOutSpilledVariable(companionExtensionPolicy);
                    anonymousClass3.L$16 = SpillingKt.nullOutSpilledVariable(explicitReceiverKind2);
                    anonymousClass3.L$17 = SpillingKt.nullOutSpilledVariable(this);
                    anonymousClass3.L$18 = scopeBasedTowerLevel$default;
                    anonymousClass3.L$19 = SpillingKt.nullOutSpilledVariable(callInfo2);
                    anonymousClass3.L$20 = SpillingKt.nullOutSpilledVariable(towerGroupNonLocal);
                    anonymousClass3.L$21 = SpillingKt.nullOutSpilledVariable(explicitReceiverKind2);
                    anonymousClass3.I$0 = i2;
                    anonymousClass3.I$1 = i;
                    z = true;
                    anonymousClass3.label = 1;
                    Object objProcessLevel = processLevel(scopeBasedTowerLevel$default, callInfo2, towerGroupNonLocal, explicitReceiverKind2, anonymousClass3);
                    scopeBasedTowerLevel = scopeBasedTowerLevel$default;
                    obj = coroutine_suspended;
                    if (objProcessLevel == obj) {
                        return obj;
                    }
                    firScope = scope;
                    obj2 = objProcessLevel;
                    i3 = i2;
                    anonymousClass2 = anonymousClass3;
                    set5 = set6;
                    explicitReceiverKind3 = explicitReceiverKind2;
                    firResolvedQualifier3 = firResolvedQualifier4;
                }
                while (it.hasNext()) {
                    i2 = i + 1;
                    FirTowerDataElement firTowerDataElement2 = (FirTowerDataElement) it.next();
                    scope = firTowerDataElement2.getScope();
                    if (firTowerResolveTask.canContainCompanionExtensions(scope)) {
                    }
                    i = i2;
                    anonymousClass2 = anonymousClass2;
                    set4 = set4;
                    it = it;
                    firResolvedQualifier2 = firResolvedQualifier2;
                    z2 = z2;
                    coroutine_suspended = coroutine_suspended;
                    firTowerResolveTask = this;
                }
            }
            return Unit.INSTANCE;
        }
        if (i5 != 1) {
            k2d.a(CoroutineConstantsKt.ILLEGAL_STATE_ERROR_MESSAGE);
            return null;
        }
        int i6 = anonymousClass1.I$0;
        scopeBasedTowerLevel = (ScopeBasedTowerLevel) anonymousClass1.L$18;
        FirScope firScope2 = (FirScope) anonymousClass1.L$9;
        Iterator it3 = (Iterator) anonymousClass1.L$7;
        ExpressionReceiverValue expressionReceiverValue3 = (ExpressionReceiverValue) anonymousClass1.L$6;
        set5 = (Set) anonymousClass1.L$5;
        Set<FirScope> set7 = (Set) anonymousClass1.L$4;
        ExplicitReceiverKind explicitReceiverKind4 = (ExplicitReceiverKind) anonymousClass1.L$3;
        towerGroup2 = (TowerGroup) anonymousClass1.L$2;
        firResolvedQualifier3 = (FirResolvedQualifier) anonymousClass1.L$1;
        CallInfo callInfo3 = (CallInfo) anonymousClass1.L$0;
        ResultKt.throwOnFailure(obj2);
        anonymousClass2 = anonymousClass1;
        expressionReceiverValue = expressionReceiverValue3;
        firScope = firScope2;
        obj = coroutine_suspended;
        explicitReceiverKind3 = explicitReceiverKind4;
        it2 = it3;
        i3 = i6;
        set3 = set7;
        callInfo2 = callInfo3;
        z = true;
        if (!((Boolean) obj2).booleanValue()) {
            scopeBasedTowerLevel.getHasFoundCompanionExtensions();
        } else if (set3 != null) {
            Boxing.boxBoolean(set3.add(firScope));
        }
        z2 = z;
        i = i3;
        set4 = set5;
        it = it2;
        firResolvedQualifier2 = firResolvedQualifier3;
        firTowerResolveTask = this;
        explicitReceiverKind2 = explicitReceiverKind3;
        coroutine_suspended = obj;
        while (it.hasNext()) {
            i2 = i + 1;
            FirTowerDataElement firTowerDataElement3 = (FirTowerDataElement) it.next();
            scope = firTowerDataElement3.getScope();
            if (firTowerResolveTask.canContainCompanionExtensions(scope)) {
            }
            i = i2;
            anonymousClass2 = anonymousClass2;
            set4 = set4;
            it = it;
            firResolvedQualifier2 = firResolvedQualifier2;
            z2 = z2;
            coroutine_suspended = coroutine_suspended;
            firTowerResolveTask = this;
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object enumerateTowerLevelsForCompanionExtensions$default(FirTowerResolveTask firTowerResolveTask, CallInfo callInfo, FirResolvedQualifier firResolvedQualifier, TowerGroup towerGroup, ExplicitReceiverKind explicitReceiverKind, Set set, Set set2, Continuation continuation, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: enumerateTowerLevelsForCompanionExtensions");
            return null;
        }
        if ((i & 16) != 0) {
            set = null;
        }
        if ((i & 32) != 0) {
            set2 = null;
        }
        return firTowerResolveTask.enumerateTowerLevelsForCompanionExtensions(callInfo, firResolvedQualifier, towerGroup, explicitReceiverKind, set, set2, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object enumerateTowerLevelsForCompanionExtensionsForImplicitReceiver(CallInfo callInfo, FirRegularClassSymbol firRegularClassSymbol, TowerGroup towerGroup, Set<FirScope> set, Set<FirScope> set2, Continuation<? super Unit> continuation) {
        BodyResolveComponents components = getComponents();
        KtSourceElement source = callInfo.getCallSite().getSource();
        return enumerateTowerLevelsForCompanionExtensions(callInfo, ResolveUtilsKt.toImplicitResolvedQualifierReceiver(firRegularClassSymbol, components, source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.ImplicitReceiver.INSTANCE, null, 2, null) : null), towerGroup, ExplicitReceiverKind.NO_EXPLICIT_RECEIVER, set, set2, continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:56:0x03b9  */
    /* JADX WARN: Code duplicated, block: B:58:0x03c8  */
    /* JADX WARN: Code duplicated, block: B:60:0x03d3  */
    /* JADX WARN: Code duplicated, block: B:62:0x03dd  */
    /* JADX WARN: Code duplicated, block: B:65:0x0486  */
    /* JADX WARN: Code duplicated, block: B:68:0x049e  */
    /* JADX WARN: Code duplicated, block: B:69:0x04a5  */
    /* JADX WARN: Code duplicated, block: B:71:0x04ab  */
    /* JADX WARN: Code duplicated, block: B:79:0x04e2  */
    /* JADX WARN: Code duplicated, block: B:82:0x04f9  */
    /* JADX WARN: Code duplicated, block: B:84:0x0503  */
    /* JADX WARN: Code duplicated, block: B:87:0x0595  */
    /* JADX WARN: Code duplicated, block: B:89:0x05ab  */
    /* JADX WARN: Code duplicated, block: B:8:0x0018  */
    /* JADX WARN: Code duplicated, block: B:92:0x05be  */
    /* JADX WARN: Code duplicated, block: B:96:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:97:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:42:0x034a -> B:43:0x0356). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:51:0x037c -> B:50:0x037a). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:87:0x0595 -> B:88:0x059f). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:89:0x05ab -> B:90:0x05b6). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:92:0x05be -> B:91:0x05bb). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public final java.lang.Object processCandidatesWithGivenImplicitReceiverAsValue(org.jetbrains.kotlin.fir.resolve.calls.ImplicitReceiverValue<?> r28, org.jetbrains.kotlin.fir.resolve.calls.candidate.CallInfo r29, org.jetbrains.kotlin.fir.resolve.calls.tower.TowerGroup r30, java.util.Set<org.jetbrains.kotlin.fir.resolve.calls.ImplicitReceiverValue<?>> r31, java.util.Set<org.jetbrains.kotlin.fir.scopes.FirScope> r32, java.util.Set<org.jetbrains.kotlin.fir.scopes.FirScope> r33, boolean r34, kotlin.coroutines.Continuation<? super kotlin.Unit> r35) {
        /*
            Method dump skipped, instruction units count: 1482
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.kotlin.fir.resolve.calls.tower.FirTowerResolveTask.processCandidatesWithGivenImplicitReceiverAsValue(org.jetbrains.kotlin.fir.resolve.calls.ImplicitReceiverValue, org.jetbrains.kotlin.fir.resolve.calls.candidate.CallInfo, org.jetbrains.kotlin.fir.resolve.calls.tower.TowerGroup, java.util.Set, java.util.Set, java.util.Set, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0017  */
    public final Object processClassifierScope(CallInfo callInfo, QualifierReceiver qualifierReceiver, Continuation<? super Unit> continuation) {
        C00421 c00421;
        ScopeBasedTowerLevel scopeBasedTowerLevel;
        if (continuation instanceof C00421) {
            c00421 = (C00421) continuation;
            int i = c00421.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                c00421.label = i - Integer.MIN_VALUE;
            } else {
                c00421 = new C00421(continuation);
            }
        } else {
            c00421 = new C00421(continuation);
        }
        Object objProcessLevel = c00421.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c00421.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(objProcessLevel);
            if (qualifierReceiver == null) {
                return Unit.INSTANCE;
            }
            if (!Intrinsics.areEqual(callInfo.getCallKind(), CallKind.CallableReference.INSTANCE) && (qualifierReceiver instanceof ClassQualifierReceiver)) {
                ClassQualifierReceiver classQualifierReceiver = (ClassQualifierReceiver) qualifierReceiver;
                if (!Intrinsics.areEqual(classQualifierReceiver.getClassSymbol(), classQualifierReceiver.getOriginalSymbol())) {
                    return Unit.INSTANCE;
                }
            }
            FirScope firScopeClassifierScope = qualifierReceiver.classifierScope();
            if (firScopeClassifierScope == null) {
                return Unit.INSTANCE;
            }
            ScopeBasedTowerLevel scopeBasedTowerLevel$default = FirBaseTowerResolveTask.toScopeBasedTowerLevel$default(this, firScopeClassifierScope, null, false, ConstructorFilter.OnlyNested, null, null, 27, null);
            TowerGroup qualifierOrClassifier = TowerGroup.INSTANCE.getQualifierOrClassifier();
            ExplicitReceiverKind explicitReceiverKind = ExplicitReceiverKind.NO_EXPLICIT_RECEIVER;
            scopeBasedTowerLevel$default.getClass();
            c00421.L$0 = SpillingKt.nullOutSpilledVariable(callInfo);
            c00421.L$1 = SpillingKt.nullOutSpilledVariable(qualifierReceiver);
            c00421.L$2 = SpillingKt.nullOutSpilledVariable(firScopeClassifierScope);
            c00421.L$3 = SpillingKt.nullOutSpilledVariable(this);
            c00421.L$4 = scopeBasedTowerLevel$default;
            c00421.L$5 = SpillingKt.nullOutSpilledVariable(callInfo);
            c00421.L$6 = SpillingKt.nullOutSpilledVariable(qualifierOrClassifier);
            c00421.L$7 = SpillingKt.nullOutSpilledVariable(explicitReceiverKind);
            c00421.label = 1;
            objProcessLevel = processLevel(scopeBasedTowerLevel$default, callInfo, qualifierOrClassifier, explicitReceiverKind, c00421);
            if (objProcessLevel == coroutine_suspended) {
                return coroutine_suspended;
            }
            scopeBasedTowerLevel = scopeBasedTowerLevel$default;
        } else {
            if (i2 != 1) {
                k2d.a(CoroutineConstantsKt.ILLEGAL_STATE_ERROR_MESSAGE);
                return null;
            }
            scopeBasedTowerLevel = (ScopeBasedTowerLevel) c00421.L$4;
            ResultKt.throwOnFailure(objProcessLevel);
        }
        if (!((Boolean) objProcessLevel).booleanValue()) {
            scopeBasedTowerLevel.getHasFoundCompanionExtensions();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:23:0x0097  */
    /* JADX WARN: Code duplicated, block: B:25:0x00c3 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:7:0x0015  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x00c1 -> B:12:0x004c). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public final java.lang.Object processExtensionsThatHideMembers(org.jetbrains.kotlin.fir.resolve.calls.candidate.CallInfo r15, org.jetbrains.kotlin.fir.resolve.calls.ReceiverValue r16, org.jetbrains.kotlin.fir.resolve.calls.tower.TowerGroup r17, org.jetbrains.kotlin.resolve.calls.tasks.ExplicitReceiverKind r18, kotlin.coroutines.Continuation<? super kotlin.Unit> r19) {
        /*
            Method dump skipped, instruction units count: 202
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.kotlin.fir.resolve.calls.tower.FirTowerResolveTask.processExtensionsThatHideMembers(org.jetbrains.kotlin.fir.resolve.calls.candidate.CallInfo, org.jetbrains.kotlin.fir.resolve.calls.ReceiverValue, org.jetbrains.kotlin.fir.resolve.calls.tower.TowerGroup, org.jetbrains.kotlin.resolve.calls.tasks.ExplicitReceiverKind, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0017  */
    public final Object processHideMembersLevel(ReceiverValue receiverValue, FirScope firScope, CallInfo callInfo, int i, ExplicitReceiverKind explicitReceiverKind, TowerGroup towerGroup, Continuation<? super Unit> continuation) {
        C00441 c00441;
        ScopeBasedTowerLevel scopeBasedTowerLevel;
        if (continuation instanceof C00441) {
            c00441 = (C00441) continuation;
            int i2 = c00441.label;
            if ((i2 & Integer.MIN_VALUE) != 0) {
                c00441.label = i2 - Integer.MIN_VALUE;
            } else {
                c00441 = new C00441(continuation);
            }
        } else {
            c00441 = new C00441(continuation);
        }
        Object objProcessLevel = c00441.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i3 = c00441.label;
        if (i3 == 0) {
            ResultKt.throwOnFailure(objProcessLevel);
            ScopeBasedTowerLevel scopeBasedTowerLevel$default = FirBaseTowerResolveTask.toScopeBasedTowerLevel$default(this, firScope, receiverValue, true, null, null, null, 28, null);
            TowerGroup towerGroupTopPrioritized = towerGroup.TopPrioritized(i);
            scopeBasedTowerLevel$default.getClass();
            c00441.L$0 = SpillingKt.nullOutSpilledVariable(receiverValue);
            c00441.L$1 = SpillingKt.nullOutSpilledVariable(firScope);
            c00441.L$2 = SpillingKt.nullOutSpilledVariable(callInfo);
            c00441.L$3 = SpillingKt.nullOutSpilledVariable(explicitReceiverKind);
            c00441.L$4 = SpillingKt.nullOutSpilledVariable(towerGroup);
            c00441.L$5 = SpillingKt.nullOutSpilledVariable(this);
            c00441.L$6 = scopeBasedTowerLevel$default;
            c00441.L$7 = SpillingKt.nullOutSpilledVariable(callInfo);
            c00441.L$8 = SpillingKt.nullOutSpilledVariable(towerGroupTopPrioritized);
            c00441.L$9 = SpillingKt.nullOutSpilledVariable(explicitReceiverKind);
            c00441.I$0 = i;
            c00441.label = 1;
            objProcessLevel = processLevel(scopeBasedTowerLevel$default, callInfo, towerGroupTopPrioritized, explicitReceiverKind, c00441);
            if (objProcessLevel == coroutine_suspended) {
                return coroutine_suspended;
            }
            scopeBasedTowerLevel = scopeBasedTowerLevel$default;
        } else {
            if (i3 != 1) {
                k2d.a(CoroutineConstantsKt.ILLEGAL_STATE_ERROR_MESSAGE);
                return null;
            }
            scopeBasedTowerLevel = (ScopeBasedTowerLevel) c00441.L$6;
            ResultKt.throwOnFailure(objProcessLevel);
        }
        if (!((Boolean) objProcessLevel).booleanValue()) {
            scopeBasedTowerLevel.getHasFoundCompanionExtensions();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:7:0x0017  */
    public final Object processQualifierScopes(CallInfo callInfo, QualifierReceiver qualifierReceiver, Continuation<? super Unit> continuation) {
        C00451 c00451;
        FirScope firScopeCallableScope;
        ScopeBasedTowerLevel scopeBasedTowerLevel;
        if (continuation instanceof C00451) {
            c00451 = (C00451) continuation;
            int i = c00451.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                c00451.label = i - Integer.MIN_VALUE;
            } else {
                c00451 = new C00451(continuation);
            }
        } else {
            c00451 = new C00451(continuation);
        }
        Object objProcessLevel = c00451.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c00451.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(objProcessLevel);
            if (qualifierReceiver != null && (firScopeCallableScope = qualifierReceiver.callableScope()) != null) {
                ScopeBasedTowerLevel scopeBasedTowerLevel$default = FirBaseTowerResolveTask.toScopeBasedTowerLevel$default(this, firScopeCallableScope, null, false, ConstructorFilter.OnlyNested, null, qualifierReceiver instanceof ClassQualifierReceiver ? new ExpressionReceiverValue(((ClassQualifierReceiver) qualifierReceiver).getExplicitReceiver()) : null, 11, null);
                TowerGroup qualifierOrClassifier = TowerGroup.INSTANCE.getQualifierOrClassifier();
                ExplicitReceiverKind explicitReceiverKind = ExplicitReceiverKind.NO_EXPLICIT_RECEIVER;
                scopeBasedTowerLevel$default.getClass();
                c00451.L$0 = SpillingKt.nullOutSpilledVariable(callInfo);
                c00451.L$1 = SpillingKt.nullOutSpilledVariable(qualifierReceiver);
                c00451.L$2 = SpillingKt.nullOutSpilledVariable(firScopeCallableScope);
                c00451.L$3 = SpillingKt.nullOutSpilledVariable(this);
                c00451.L$4 = scopeBasedTowerLevel$default;
                c00451.L$5 = SpillingKt.nullOutSpilledVariable(callInfo);
                c00451.L$6 = SpillingKt.nullOutSpilledVariable(qualifierOrClassifier);
                c00451.L$7 = SpillingKt.nullOutSpilledVariable(explicitReceiverKind);
                c00451.label = 1;
                objProcessLevel = processLevel(scopeBasedTowerLevel$default, callInfo, qualifierOrClassifier, explicitReceiverKind, c00451);
                if (objProcessLevel == coroutine_suspended) {
                    return coroutine_suspended;
                }
                scopeBasedTowerLevel = scopeBasedTowerLevel$default;
            }
            return Unit.INSTANCE;
        }
        if (i2 != 1) {
            k2d.a(CoroutineConstantsKt.ILLEGAL_STATE_ERROR_MESSAGE);
            return null;
        }
        scopeBasedTowerLevel = (ScopeBasedTowerLevel) c00451.L$4;
        ResultKt.throwOnFailure(objProcessLevel);
        if (!((Boolean) objProcessLevel).booleanValue()) {
            scopeBasedTowerLevel.getHasFoundCompanionExtensions();
        }
        return Unit.INSTANCE;
    }

    public static /* synthetic */ Object runResolverForExpressionReceiver$default(FirTowerResolveTask firTowerResolveTask, CallInfo callInfo, FirExpression firExpression, TowerGroup towerGroup, Continuation continuation, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: runResolverForExpressionReceiver");
            return null;
        }
        if ((i & 4) != 0) {
            towerGroup = TowerGroup.INSTANCE.getEmptyRoot();
        }
        return firTowerResolveTask.runResolverForExpressionReceiver(callInfo, firExpression, towerGroup, continuation);
    }

    public static /* synthetic */ Object runResolverForNoReceiver$default(FirTowerResolveTask firTowerResolveTask, CallInfo callInfo, boolean z, Continuation continuation, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: runResolverForNoReceiver");
            return null;
        }
        if ((i & 2) != 0) {
            z = false;
        }
        return firTowerResolveTask.runResolverForNoReceiver(callInfo, z, continuation);
    }

    /* JADX WARN: Code duplicated, block: B:48:0x0338  */
    /* JADX WARN: Code duplicated, block: B:50:0x0347  */
    /* JADX WARN: Code duplicated, block: B:52:0x0351  */
    /* JADX WARN: Code duplicated, block: B:55:0x0411  */
    /* JADX WARN: Code duplicated, block: B:59:0x0427  */
    /* JADX WARN: Code duplicated, block: B:61:0x0432  */
    /* JADX WARN: Code duplicated, block: B:65:0x044b  */
    /* JADX WARN: Code duplicated, block: B:68:0x0461  */
    /* JADX WARN: Code duplicated, block: B:71:0x04f1  */
    /* JADX WARN: Code duplicated, block: B:73:0x0504  */
    /* JADX WARN: Code duplicated, block: B:76:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:77:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:8:0x0018  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:39:0x0307 -> B:22:0x0173). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:71:0x04f1 -> B:17:0x0090). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:73:0x0504 -> B:46:0x0332). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public final java.lang.Object runResolverForExpressionReceiver(org.jetbrains.kotlin.fir.resolve.calls.candidate.CallInfo r24, org.jetbrains.kotlin.fir.expressions.FirExpression r25, org.jetbrains.kotlin.fir.resolve.calls.tower.TowerGroup r26, kotlin.coroutines.Continuation<? super kotlin.Unit> r27) {
        /*
            Method dump skipped, instruction units count: 1298
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.kotlin.fir.resolve.calls.tower.FirTowerResolveTask.runResolverForExpressionReceiver(org.jetbrains.kotlin.fir.resolve.calls.candidate.CallInfo, org.jetbrains.kotlin.fir.expressions.FirExpression, org.jetbrains.kotlin.fir.resolve.calls.tower.TowerGroup, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:44:0x0297  */
    /* JADX WARN: Code duplicated, block: B:46:0x02a5  */
    /* JADX WARN: Code duplicated, block: B:48:0x02af  */
    /* JADX WARN: Code duplicated, block: B:50:0x02c3  */
    /* JADX WARN: Code duplicated, block: B:56:0x0352  */
    /* JADX WARN: Code duplicated, block: B:57:0x0355  */
    /* JADX WARN: Code duplicated, block: B:60:0x036b  */
    /* JADX WARN: Code duplicated, block: B:61:0x0372  */
    /* JADX WARN: Code duplicated, block: B:63:0x0378  */
    /* JADX WARN: Code duplicated, block: B:66:0x038e  */
    /* JADX WARN: Code duplicated, block: B:68:0x03a4  */
    /* JADX WARN: Code duplicated, block: B:70:0x03b0  */
    /* JADX WARN: Code duplicated, block: B:72:0x0403  */
    /* JADX WARN: Code duplicated, block: B:73:0x0406  */
    /* JADX WARN: Code duplicated, block: B:7:0x0017  */
    /* JADX WARN: Code duplicated, block: B:80:0x0447  */
    /* JADX WARN: Code duplicated, block: B:83:0x049b  */
    /* JADX WARN: Code duplicated, block: B:85:0x04a9  */
    /* JADX WARN: Code duplicated, block: B:90:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:28:0x01e4 -> B:40:0x027a). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:32:0x024d -> B:33:0x0257). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:83:0x049b -> B:84:0x04a0). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:85:0x04a9 -> B:42:0x0291). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    public final java.lang.Object runResolverForNoReceiver(org.jetbrains.kotlin.fir.resolve.calls.candidate.CallInfo r23, boolean r24, kotlin.coroutines.Continuation<? super kotlin.Unit> r25) {
        /*
            Method dump skipped, instruction units count: 1208
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.kotlin.fir.resolve.calls.tower.FirTowerResolveTask.runResolverForNoReceiver(org.jetbrains.kotlin.fir.resolve.calls.candidate.CallInfo, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:33:0x00da  */
    /* JADX WARN: Code duplicated, block: B:39:0x0104  */
    /* JADX WARN: Code duplicated, block: B:41:0x0108  */
    /* JADX WARN: Code duplicated, block: B:43:0x0113  */
    /* JADX WARN: Code duplicated, block: B:45:0x011e  */
    /* JADX WARN: Code duplicated, block: B:46:0x0123  */
    /* JADX WARN: Code duplicated, block: B:57:0x0196 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:8:0x001a  */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00f8, code lost:
    
        if (enumerateTowerLevelsForCompanionExtensions$default(r15, r1, r2, r3, r4, null, null, r7, 48, null) == r10) goto L56;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object runResolverForQualifierReceiver(CallInfo callInfo, FirResolvedQualifier firResolvedQualifier, Continuation<? super Unit> continuation) {
        C00481 c00481;
        QualifierReceiver qualifierReceiverCreateQualifierReceiver;
        QualifierReceiver qualifierReceiver;
        CallInfo callInfo2;
        QualifierReceiver qualifierReceiver2;
        CallableReferenceInfo callableReferenceInfo;
        FirExpression explicitReceiver;
        KtSourceElement source;
        FirExpression firExpressionBuild;
        CallInfo callInfoReplaceExplicitReceiver;
        TowerGroup qualifierValue;
        Object objRunResolverForExpressionReceiver;
        CallInfo callInfo3 = callInfo;
        FirResolvedQualifier firResolvedQualifier2 = firResolvedQualifier;
        if (continuation instanceof C00481) {
            c00481 = (C00481) continuation;
            int i = c00481.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                c00481.label = i - Integer.MIN_VALUE;
            } else {
                c00481 = new C00481(continuation);
            }
        } else {
            c00481 = new C00481(continuation);
        }
        C00481 c00482 = c00481;
        Object obj = c00482.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c00482.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            qualifierReceiverCreateQualifierReceiver = QualifierReceiverKt.createQualifierReceiver(firResolvedQualifier2, getSession(), getComponents().getScopeSession());
            c00482.L$0 = callInfo3;
            c00482.L$1 = firResolvedQualifier2;
            c00482.L$2 = qualifierReceiverCreateQualifierReceiver;
            c00482.label = 1;
            if (processQualifierScopes(callInfo3, qualifierReceiverCreateQualifierReceiver, c00482) != coroutine_suspended) {
            }
            return coroutine_suspended;
        }
        if (i2 == 1) {
            QualifierReceiver qualifierReceiver3 = (QualifierReceiver) c00482.L$2;
            firResolvedQualifier2 = (FirResolvedQualifier) c00482.L$1;
            CallInfo callInfo4 = (CallInfo) c00482.L$0;
            ResultKt.throwOnFailure(obj);
            qualifierReceiverCreateQualifierReceiver = qualifierReceiver3;
            callInfo3 = callInfo4;
        } else {
            if (i2 == 2) {
                QualifierReceiver qualifierReceiver4 = (QualifierReceiver) c00482.L$2;
                firResolvedQualifier2 = (FirResolvedQualifier) c00482.L$1;
                CallInfo callInfo5 = (CallInfo) c00482.L$0;
                ResultKt.throwOnFailure(obj);
                qualifierReceiver = qualifierReceiver4;
                callInfo3 = callInfo5;
                if (this.companionExtensionsEnabled) {
                    TowerGroup qualifierOrClassifier = TowerGroup.INSTANCE.getQualifierOrClassifier();
                    ExplicitReceiverKind explicitReceiverKind = ExplicitReceiverKind.EXTENSION_RECEIVER;
                    c00482.L$0 = callInfo3;
                    c00482.L$1 = firResolvedQualifier2;
                    c00482.L$2 = SpillingKt.nullOutSpilledVariable(qualifierReceiver);
                    c00482.label = 3;
                }
                callInfo2 = callInfo3;
                qualifierReceiver2 = qualifierReceiver;
                if (firResolvedQualifier2.getSymbol() != null) {
                    if (callInfo2 instanceof CallableReferenceInfo) {
                        callableReferenceInfo = (CallableReferenceInfo) callInfo2;
                        if (callableReferenceInfo.getLhs() instanceof DoubleColonLHS.Type) {
                            FirExpressionStubBuilder firExpressionStubBuilder = new FirExpressionStubBuilder();
                            explicitReceiver = callInfo2.getExplicitReceiver();
                            if (explicitReceiver != null) {
                                source = explicitReceiver.getSource();
                            } else {
                                source = null;
                            }
                            firExpressionStubBuilder.setSource(source);
                            firExpressionStubBuilder.setConeTypeOrNull(((DoubleColonLHS.Type) callableReferenceInfo.getLhs()).getType());
                            firExpressionBuild = firExpressionStubBuilder.mo288build();
                            callInfoReplaceExplicitReceiver = callInfo2.replaceExplicitReceiver(firExpressionBuild);
                            qualifierValue = TowerGroup.INSTANCE.getQualifierValue();
                            c00482.L$0 = callInfo2;
                            c00482.L$1 = firResolvedQualifier2;
                            c00482.L$2 = SpillingKt.nullOutSpilledVariable(qualifierReceiver2);
                            c00482.L$3 = SpillingKt.nullOutSpilledVariable(firExpressionBuild);
                            c00482.L$4 = SpillingKt.nullOutSpilledVariable(callInfoReplaceExplicitReceiver);
                            c00482.label = 4;
                            if (runResolverForExpressionReceiver(callInfoReplaceExplicitReceiver, firExpressionBuild, qualifierValue, c00482) != coroutine_suspended) {
                            }
                            return coroutine_suspended;
                        }
                    }
                }
                return Unit.INSTANCE;
            }
            if (i2 == 3) {
                qualifierReceiver2 = (QualifierReceiver) c00482.L$2;
                firResolvedQualifier2 = (FirResolvedQualifier) c00482.L$1;
                callInfo2 = (CallInfo) c00482.L$0;
                ResultKt.throwOnFailure(obj);
                if (firResolvedQualifier2.getSymbol() != null) {
                    if (callInfo2 instanceof CallableReferenceInfo) {
                        callableReferenceInfo = (CallableReferenceInfo) callInfo2;
                        if (callableReferenceInfo.getLhs() instanceof DoubleColonLHS.Type) {
                            FirExpressionStubBuilder firExpressionStubBuilder2 = new FirExpressionStubBuilder();
                            explicitReceiver = callInfo2.getExplicitReceiver();
                            if (explicitReceiver != null) {
                                source = explicitReceiver.getSource();
                            } else {
                                source = null;
                            }
                            firExpressionStubBuilder2.setSource(source);
                            firExpressionStubBuilder2.setConeTypeOrNull(((DoubleColonLHS.Type) callableReferenceInfo.getLhs()).getType());
                            firExpressionBuild = firExpressionStubBuilder2.mo288build();
                            callInfoReplaceExplicitReceiver = callInfo2.replaceExplicitReceiver(firExpressionBuild);
                            qualifierValue = TowerGroup.INSTANCE.getQualifierValue();
                            c00482.L$0 = callInfo2;
                            c00482.L$1 = firResolvedQualifier2;
                            c00482.L$2 = SpillingKt.nullOutSpilledVariable(qualifierReceiver2);
                            c00482.L$3 = SpillingKt.nullOutSpilledVariable(firExpressionBuild);
                            c00482.L$4 = SpillingKt.nullOutSpilledVariable(callInfoReplaceExplicitReceiver);
                            c00482.label = 4;
                            if (runResolverForExpressionReceiver(callInfoReplaceExplicitReceiver, firExpressionBuild, qualifierValue, c00482) != coroutine_suspended) {
                            }
                            return coroutine_suspended;
                        }
                    }
                }
                return Unit.INSTANCE;
            }
            if (i2 != 4) {
                if (i2 != 5) {
                    k2d.a(CoroutineConstantsKt.ILLEGAL_STATE_ERROR_MESSAGE);
                    return null;
                }
                ResultKt.throwOnFailure(obj);
                return obj;
            }
            qualifierReceiver2 = (QualifierReceiver) c00482.L$2;
            firResolvedQualifier2 = (FirResolvedQualifier) c00482.L$1;
            callInfo2 = (CallInfo) c00482.L$0;
            ResultKt.throwOnFailure(obj);
        }
        if (firResolvedQualifier2.getCanBeValue() && firResolvedQualifier2.getTypeArguments().isEmpty()) {
            TowerGroup qualifierValue2 = TowerGroup.INSTANCE.getQualifierValue();
            c00482.L$0 = SpillingKt.nullOutSpilledVariable(callInfo2);
            c00482.L$1 = SpillingKt.nullOutSpilledVariable(firResolvedQualifier2);
            c00482.L$2 = SpillingKt.nullOutSpilledVariable(qualifierReceiver2);
            c00482.L$3 = null;
            c00482.L$4 = null;
            c00482.label = 5;
            objRunResolverForExpressionReceiver = runResolverForExpressionReceiver(callInfo2, firResolvedQualifier2, qualifierValue2, c00482);
            if (objRunResolverForExpressionReceiver != coroutine_suspended) {
                return coroutine_suspended;
            }
            return objRunResolverForExpressionReceiver;
        }
        return Unit.INSTANCE;
        c00482.L$0 = callInfo3;
        c00482.L$1 = firResolvedQualifier2;
        c00482.L$2 = SpillingKt.nullOutSpilledVariable(qualifierReceiverCreateQualifierReceiver);
        c00482.label = 2;
        if (processClassifierScope(callInfo3, qualifierReceiverCreateQualifierReceiver, c00482) != coroutine_suspended) {
            qualifierReceiver = qualifierReceiverCreateQualifierReceiver;
            if (this.companionExtensionsEnabled) {
                TowerGroup qualifierOrClassifier2 = TowerGroup.INSTANCE.getQualifierOrClassifier();
                ExplicitReceiverKind explicitReceiverKind2 = ExplicitReceiverKind.EXTENSION_RECEIVER;
                c00482.L$0 = callInfo3;
                c00482.L$1 = firResolvedQualifier2;
                c00482.L$2 = SpillingKt.nullOutSpilledVariable(qualifierReceiver);
                c00482.label = 3;
            }
            callInfo2 = callInfo3;
            qualifierReceiver2 = qualifierReceiver;
            if (firResolvedQualifier2.getSymbol() != null) {
                if (callInfo2 instanceof CallableReferenceInfo) {
                    callableReferenceInfo = (CallableReferenceInfo) callInfo2;
                    if (callableReferenceInfo.getLhs() instanceof DoubleColonLHS.Type) {
                        FirExpressionStubBuilder firExpressionStubBuilder3 = new FirExpressionStubBuilder();
                        explicitReceiver = callInfo2.getExplicitReceiver();
                        if (explicitReceiver != null) {
                            source = explicitReceiver.getSource();
                        } else {
                            source = null;
                        }
                        firExpressionStubBuilder3.setSource(source);
                        firExpressionStubBuilder3.setConeTypeOrNull(((DoubleColonLHS.Type) callableReferenceInfo.getLhs()).getType());
                        firExpressionBuild = firExpressionStubBuilder3.mo288build();
                        callInfoReplaceExplicitReceiver = callInfo2.replaceExplicitReceiver(firExpressionBuild);
                        qualifierValue = TowerGroup.INSTANCE.getQualifierValue();
                        c00482.L$0 = callInfo2;
                        c00482.L$1 = firResolvedQualifier2;
                        c00482.L$2 = SpillingKt.nullOutSpilledVariable(qualifierReceiver2);
                        c00482.L$3 = SpillingKt.nullOutSpilledVariable(firExpressionBuild);
                        c00482.L$4 = SpillingKt.nullOutSpilledVariable(callInfoReplaceExplicitReceiver);
                        c00482.label = 4;
                        if (runResolverForExpressionReceiver(callInfoReplaceExplicitReceiver, firExpressionBuild, qualifierValue, c00482) != coroutine_suspended) {
                        }
                    }
                }
                if (firResolvedQualifier2.getCanBeValue()) {
                    TowerGroup qualifierValue3 = TowerGroup.INSTANCE.getQualifierValue();
                    c00482.L$0 = SpillingKt.nullOutSpilledVariable(callInfo2);
                    c00482.L$1 = SpillingKt.nullOutSpilledVariable(firResolvedQualifier2);
                    c00482.L$2 = SpillingKt.nullOutSpilledVariable(qualifierReceiver2);
                    c00482.L$3 = null;
                    c00482.L$4 = null;
                    c00482.label = 5;
                    objRunResolverForExpressionReceiver = runResolverForExpressionReceiver(callInfo2, firResolvedQualifier2, qualifierValue3, c00482);
                    if (objRunResolverForExpressionReceiver != coroutine_suspended) {
                        return objRunResolverForExpressionReceiver;
                    }
                }
            }
            return Unit.INSTANCE;
        }
        return coroutine_suspended;
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    public final Object runResolverForSuperReceiver(CallInfo callInfo, FirQualifiedAccessExpression firQualifiedAccessExpression, Continuation<? super Unit> continuation) {
        C00491 c00491;
        if (continuation instanceof C00491) {
            c00491 = (C00491) continuation;
            int i = c00491.label;
            if ((i & Integer.MIN_VALUE) != 0) {
                c00491.label = i - Integer.MIN_VALUE;
            } else {
                c00491 = new C00491(continuation);
            }
        } else {
            c00491 = new C00491(continuation);
        }
        Object objProcessLevel = c00491.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = c00491.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(objProcessLevel);
            ExpressionReceiverValue expressionReceiverValue = new ExpressionReceiverValue(firQualifiedAccessExpression);
            DispatchReceiverMemberScopeTowerLevel dispatchReceiverMemberScopeTowerLevel$default = FirBaseTowerResolveTask.toDispatchReceiverMemberScopeTowerLevel$default(this, expressionReceiverValue, null, false, 3, null);
            TowerGroup member = TowerGroup.INSTANCE.getMember();
            ExplicitReceiverKind explicitReceiverKind = ExplicitReceiverKind.DISPATCH_RECEIVER;
            c00491.L$0 = SpillingKt.nullOutSpilledVariable(callInfo);
            c00491.L$1 = SpillingKt.nullOutSpilledVariable(firQualifiedAccessExpression);
            c00491.L$2 = SpillingKt.nullOutSpilledVariable(expressionReceiverValue);
            c00491.L$3 = SpillingKt.nullOutSpilledVariable(this);
            c00491.L$4 = SpillingKt.nullOutSpilledVariable(dispatchReceiverMemberScopeTowerLevel$default);
            c00491.L$5 = SpillingKt.nullOutSpilledVariable(callInfo);
            c00491.L$6 = SpillingKt.nullOutSpilledVariable(member);
            c00491.L$7 = SpillingKt.nullOutSpilledVariable(explicitReceiverKind);
            c00491.label = 1;
            objProcessLevel = processLevel(dispatchReceiverMemberScopeTowerLevel$default, callInfo, member, explicitReceiverKind, c00491);
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
}
