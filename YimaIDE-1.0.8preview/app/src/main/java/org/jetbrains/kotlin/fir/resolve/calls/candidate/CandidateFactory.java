package org.jetbrains.kotlin.fir.resolve.calls.candidate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirModuleDataKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.DeprecationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirResolveStateKt;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.declarations.builder.FirErrorFunctionBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirErrorPropertyBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirNamedFunctionBuilder;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCallOrigin;
import org.jetbrains.kotlin.fir.expressions.FirInaccessibleReceiverExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.extensions.FirExtensionServiceKt;
import org.jetbrains.kotlin.fir.extensions.FirFunctionCallRefinementExtension;
import org.jetbrains.kotlin.fir.extensions.FirFunctionCallRefinementExtensionKt;
import org.jetbrains.kotlin.fir.extensions.OriginalCallData;
import org.jetbrains.kotlin.fir.resolve.ContainingClassUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ResolveUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.AmbiguousInterceptedSymbol;
import org.jetbrains.kotlin.fir.resolve.calls.ArgumentUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtom;
import org.jetbrains.kotlin.fir.resolve.calls.LowerPriorityToPreserveCompatibilityDiagnostic;
import org.jetbrains.kotlin.fir.resolve.calls.NoCompanionObject;
import org.jetbrains.kotlin.fir.resolve.calls.NotFunctionAsOperator;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionContext;
import org.jetbrains.kotlin.fir.resolve.calls.Unsupported;
import org.jetbrains.kotlin.fir.resolve.calls.UnsupportedCompanionBlockOrExtensionCall;
import org.jetbrains.kotlin.fir.resolve.inference.FirInferenceLogger;
import org.jetbrains.kotlin.fir.resolve.inference.FirInferenceLoggerKt;
import org.jetbrains.kotlin.fir.resolve.inference.InferenceComponents;
import org.jetbrains.kotlin.fir.scopes.FirScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirAbstractImportingScopeKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirIntegerConstantOperatorScopeKt;
import org.jetbrains.kotlin.fir.scopes.impl.ImportedFromObjectOrStaticData;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirBackingFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirEnumEntrySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirErrorFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirErrorPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirLocalPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.resolve.calls.inference.components.ConstraintSystemMarker;
import org.jetbrains.kotlin.resolve.calls.inference.model.ConstraintStorage;
import org.jetbrains.kotlin.resolve.calls.inference.model.NewConstraintSystemImpl;
import org.jetbrains.kotlin.resolve.calls.tasks.ExplicitReceiverKind;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 42\u00020\u0001:\u00014B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007B\u0019\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\u0006\u0010\nJX\u0010\u0012\u001a\u00020\u00132\u0006\u0010\b\u001a\u00020\t2\n\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\u00152\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001b2\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001f\u001a\u00020\u001eJ\u0010\u0010 \u001a\u00020\u001e*\u0006\u0012\u0002\b\u00030\u0015H\u0002J4\u0010!\u001a\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0015\u0012\u0006\u0012\u0004\u0018\u00010#0\"*\u00020$2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\b\u001a\u00020\tH\u0002J\u0018\u0010%\u001a\u00020\u001e*\u0006\u0012\u0002\b\u00030\u00152\u0006\u0010&\u001a\u00020'H\u0002J\u001c\u0010(\u001a\u0006\u0012\u0002\b\u00030\u0015*\u0006\u0012\u0002\b\u00030\u00152\u0006\u0010\b\u001a\u00020\tH\u0002J\u0016\u0010)\u001a\u00020\u001e*\u0004\u0018\u00010\u001b2\u0006\u0010*\u001a\u00020'H\u0002J\u0016\u0010+\u001a\u00020\u00132\u0006\u0010\b\u001a\u00020\t2\u0006\u0010,\u001a\u00020-J\u0010\u0010.\u001a\u00020/2\u0006\u0010,\u001a\u00020-H\u0002J\u001a\u00100\u001a\u0002012\u0006\u0010,\u001a\u00020-2\b\u00102\u001a\u0004\u0018\u000103H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000eX\u0082\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0010\u0010\u0011¨\u00065"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CandidateFactory;", Argument.Delimiters.none, "context", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "baseSystem", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/ConstraintStorage;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/resolve/calls/inference/model/ConstraintStorage;)V", "callInfo", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;", "(Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;)V", "getContext", "()Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "callRefinementExtensions", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/extensions/FirFunctionCallRefinementExtension;", "getCallRefinementExtensions$annotations", "()V", "createCandidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "explicitReceiverKind", "Lorg/jetbrains/kotlin/resolve/calls/tasks/ExplicitReceiverKind;", "scope", "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "dispatchReceiver", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "givenExtensionReceiver", "objectsByName", Argument.Delimiters.none, "isFromOriginalTypeInPresenceOfSmartCast", "requiresCompanionBlockOrExtensionLf", "replaceFromPluginsIfNeeded", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/fir/resolve/calls/AmbiguousInterceptedSymbol;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "isRegularClassWithoutCompanion", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "unwrapIntegerOperatorSymbolIfNeeded", "isCandidateFromCompanionObjectTypeScope", "useSiteSession", "createErrorCandidate", "diagnostic", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "createErrorFunctionSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirErrorFunctionSymbol;", "createErrorPropertySymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirErrorPropertySymbol;", "sourceElement", "Lorg/jetbrains/kotlin/KtSourceElement;", "Companion", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CandidateFactory {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final ConstraintStorage baseSystem;
    private final List<FirFunctionCallRefinementExtension> callRefinementExtensions;
    private final ResolutionContext context;

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ExplicitReceiverKind.values().length];
            try {
                iArr[ExplicitReceiverKind.EXTENSION_RECEIVER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ExplicitReceiverKind.DISPATCH_RECEIVER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ExplicitReceiverKind.NO_EXPLICIT_RECEIVER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[ExplicitReceiverKind.BOTH_RECEIVERS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private CandidateFactory(ResolutionContext resolutionContext, ConstraintStorage constraintStorage) {
        this.context = resolutionContext;
        this.baseSystem = constraintStorage;
        List<FirFunctionCallRefinementExtension> callRefinementExtensions = FirFunctionCallRefinementExtensionKt.getCallRefinementExtensions(FirExtensionServiceKt.getExtensionService(resolutionContext.getSession()));
        this.callRefinementExtensions = callRefinementExtensions.isEmpty() ? null : callRefinementExtensions;
    }

    public static /* synthetic */ Candidate createCandidate$default(CandidateFactory candidateFactory, CallInfo callInfo, FirBasedSymbol firBasedSymbol, ExplicitReceiverKind explicitReceiverKind, FirScope firScope, FirExpression firExpression, FirExpression firExpression2, boolean z, boolean z2, int i, Object obj) {
        if ((i & 16) != 0) {
            firExpression = null;
        }
        if ((i & 32) != 0) {
            firExpression2 = null;
        }
        if ((i & 64) != 0) {
            z = false;
        }
        if ((i & 128) != 0) {
            z2 = false;
        }
        return candidateFactory.createCandidate(callInfo, firBasedSymbol, explicitReceiverKind, firScope, firExpression, firExpression2, z, z2);
    }

    private final FirErrorFunctionSymbol createErrorFunctionSymbol(ConeDiagnostic diagnostic) {
        FirErrorFunctionSymbol firErrorFunctionSymbol = new FirErrorFunctionSymbol();
        FirErrorFunctionBuilder firErrorFunctionBuilder = new FirErrorFunctionBuilder();
        firErrorFunctionBuilder.setModuleData(FirModuleDataKt.getModuleData(this.context.getSession()));
        firErrorFunctionBuilder.setResolvePhase(FirResolvePhase.BODY_RESOLVE);
        firErrorFunctionBuilder.setOrigin(FirDeclarationOrigin.Synthetic.Error.INSTANCE);
        firErrorFunctionBuilder.setDiagnostic(diagnostic);
        firErrorFunctionBuilder.setSymbol(firErrorFunctionSymbol);
        firErrorFunctionBuilder.mo288build();
        return firErrorFunctionSymbol;
    }

    private final FirErrorPropertySymbol createErrorPropertySymbol(ConeDiagnostic diagnostic, KtSourceElement sourceElement) {
        FirErrorPropertySymbol firErrorPropertySymbol = new FirErrorPropertySymbol(diagnostic);
        FirErrorPropertyBuilder firErrorPropertyBuilder = new FirErrorPropertyBuilder();
        firErrorPropertyBuilder.setModuleData(FirModuleDataKt.getModuleData(this.context.getSession()));
        firErrorPropertyBuilder.setResolvePhase(FirResolvePhase.BODY_RESOLVE);
        firErrorPropertyBuilder.setOrigin(FirDeclarationOrigin.Synthetic.Error.INSTANCE);
        firErrorPropertyBuilder.setName(FirErrorPropertySymbol.INSTANCE.getNAME());
        firErrorPropertyBuilder.setDiagnostic(diagnostic);
        firErrorPropertyBuilder.setSymbol(firErrorPropertySymbol);
        firErrorPropertyBuilder.setSource(sourceElement);
        firErrorPropertyBuilder.mo288build();
        return firErrorPropertySymbol;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean isCandidateFromCompanionObjectTypeScope(FirExpression firExpression, FirSession firSession) {
        ClassId classId;
        FirRegularClassSymbol firRegularClassSymbolFullyExpandedClass;
        FirRegularClass firRegularClass;
        FirExpression firExpressionUnwrapSmartcastExpression = firExpression != null ? FirExpressionUtilKt.unwrapSmartcastExpression(firExpression) : null;
        FirResolvedQualifier firResolvedQualifier = firExpressionUnwrapSmartcastExpression instanceof FirResolvedQualifier ? (FirResolvedQualifier) firExpressionUnwrapSmartcastExpression : null;
        if (firResolvedQualifier == null || (classId = ConeTypeUtilsKt.getClassId(FirTypeUtilsKt.getResolvedType(firExpression))) == null) {
            return false;
        }
        FirClassLikeSymbol<?> symbol = firResolvedQualifier.getSymbol();
        FirRegularClassSymbol companionObjectSymbol = (symbol == null || (firRegularClassSymbolFullyExpandedClass = DeclarationUtilsKt.fullyExpandedClass(symbol, firSession)) == null || (firRegularClass = (FirRegularClass) firRegularClassSymbolFullyExpandedClass.getFir()) == null) ? null : firRegularClass.getCompanionObjectSymbol();
        return Intrinsics.areEqual(companionObjectSymbol != null ? companionObjectSymbol.getClassId() : null, classId);
    }

    private final boolean isRegularClassWithoutCompanion(FirBasedSymbol<?> firBasedSymbol, FirSession firSession) {
        FirRegularClassSymbol firRegularClassSymbolFullyExpandedClass;
        FirClassLikeSymbol firClassLikeSymbol = firBasedSymbol instanceof FirClassLikeSymbol ? (FirClassLikeSymbol) firBasedSymbol : null;
        if (firClassLikeSymbol == null || (firRegularClassSymbolFullyExpandedClass = DeclarationUtilsKt.fullyExpandedClass((FirClassLikeSymbol<?>) firClassLikeSymbol, firSession)) == null || firRegularClassSymbolFullyExpandedClass.getClassKind() == ClassKind.OBJECT) {
            return false;
        }
        FirRegularClassSymbol resolvedCompanionObjectSymbol = firRegularClassSymbolFullyExpandedClass.getResolvedCompanionObjectSymbol();
        if (resolvedCompanionObjectSymbol == null) {
            return true;
        }
        return FirLanguageSettingsComponentKt.getLanguageVersionSettings(firSession).supportsFeature(LanguageFeature.SkipHiddenObjectsInResolution) && DeprecationUtilsKt.isDeprecationLevelHidden(resolvedCompanionObjectSymbol, firSession);
    }

    private final Pair<FirBasedSymbol<?>, AmbiguousInterceptedSymbol> replaceFromPluginsIfNeeded(FirNamedFunctionSymbol firNamedFunctionSymbol, List<? extends FirFunctionCallRefinementExtension> list, CallInfo callInfo) {
        Pair ambiguousInterceptedSymbol;
        FirBasedSymbol<?> firBasedSymbolUnwrapIntegerOperatorSymbolIfNeeded;
        ArrayList<Pair> arrayList = new ArrayList();
        Iterator<T> it = list.iterator();
        while (true) {
            ambiguousInterceptedSymbol = null;
            if (!it.hasNext()) {
                break;
            }
            FirFunctionCallRefinementExtension firFunctionCallRefinementExtension = (FirFunctionCallRefinementExtension) it.next();
            FirFunctionCallRefinementExtension.CallReturnType callReturnTypeIntercept = firFunctionCallRefinementExtension.intercept(callInfo, firNamedFunctionSymbol);
            ambiguousInterceptedSymbol = callReturnTypeIntercept != null ? TuplesKt.to(callReturnTypeIntercept, firFunctionCallRefinementExtension) : null;
            if (ambiguousInterceptedSymbol != null) {
                arrayList.add(ambiguousInterceptedSymbol);
            }
        }
        int size = arrayList.size();
        if (size == 0) {
            firBasedSymbolUnwrapIntegerOperatorSymbolIfNeeded = unwrapIntegerOperatorSymbolIfNeeded(firNamedFunctionSymbol, callInfo);
        } else if (size != 1) {
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
            for (Pair pair : arrayList) {
                String qualifiedName = Reflection.getOrCreateKotlinClass(pair.getSecond().getClass()).getQualifiedName();
                if (qualifiedName == null) {
                    qualifiedName = pair.getSecond().getClass().getName();
                }
                arrayList2.add(qualifiedName);
            }
            ambiguousInterceptedSymbol = new AmbiguousInterceptedSymbol(arrayList2);
            firBasedSymbolUnwrapIntegerOperatorSymbolIfNeeded = unwrapIntegerOperatorSymbolIfNeeded(firNamedFunctionSymbol, callInfo);
        } else {
            Pair pair2 = (Pair) arrayList.get(0);
            firBasedSymbolUnwrapIntegerOperatorSymbolIfNeeded = replaceFromPluginsIfNeeded$process(firNamedFunctionSymbol, (FirFunctionCallRefinementExtension.CallReturnType) pair2.component1(), (FirFunctionCallRefinementExtension) pair2.component2());
        }
        return new Pair<>(firBasedSymbolUnwrapIntegerOperatorSymbolIfNeeded, ambiguousInterceptedSymbol);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final FirNamedFunctionSymbol replaceFromPluginsIfNeeded$process(FirNamedFunctionSymbol firNamedFunctionSymbol, FirFunctionCallRefinementExtension.CallReturnType callReturnType, FirFunctionCallRefinementExtension firFunctionCallRefinementExtension) {
        FirNamedFunctionSymbol firNamedFunctionSymbol2 = new FirNamedFunctionSymbol(firNamedFunctionSymbol.getCallableId());
        FirNamedFunction firNamedFunction = (FirNamedFunction) firNamedFunctionSymbol.getFir();
        FirNamedFunctionBuilder firNamedFunctionBuilder = new FirNamedFunctionBuilder();
        firNamedFunctionBuilder.setSource(firNamedFunction.getSource());
        firNamedFunctionBuilder.setResolvePhase(FirResolveStateKt.getResolvePhase(firNamedFunction));
        firNamedFunctionBuilder.setModuleData(firNamedFunction.getModuleData());
        firNamedFunctionBuilder.setOrigin(firNamedFunction.getOrigin());
        firNamedFunctionBuilder.setAttributes(firNamedFunction.getAttributes().copy());
        firNamedFunctionBuilder.setStatus(firNamedFunction.getStatus());
        firNamedFunctionBuilder.setLocal(firNamedFunction.getIsLocal());
        firNamedFunctionBuilder.setReturnTypeRef(firNamedFunction.getReturnTypeRef());
        firNamedFunctionBuilder.setReceiverParameter(firNamedFunction.getReceiverParameter());
        firNamedFunctionBuilder.setDeprecationsProvider(firNamedFunction.getDeprecationsProvider());
        firNamedFunctionBuilder.setContainerSource(firNamedFunction.getContainerSource());
        firNamedFunctionBuilder.setDispatchReceiverType(firNamedFunction.getDispatchReceiverType());
        firNamedFunctionBuilder.getContextParameters().addAll(firNamedFunction.getContextParameters());
        firNamedFunctionBuilder.getValueParameters().addAll(firNamedFunction.getValueParameters());
        firNamedFunctionBuilder.setBody(firNamedFunction.getBody());
        firNamedFunctionBuilder.setContractDescription(firNamedFunction.getContractDescription());
        firNamedFunctionBuilder.setName(firNamedFunction.getName());
        firNamedFunctionBuilder.getAnnotations().addAll(firNamedFunction.getAnnotations());
        firNamedFunctionBuilder.getTypeParameters().addAll(firNamedFunction.getTypeParameters());
        firNamedFunctionBuilder.setBody(null);
        firNamedFunctionBuilder.setSymbol(firNamedFunctionSymbol2);
        firNamedFunctionBuilder.setReturnTypeRef(callReturnType.getTypeRef());
        FirFunctionCallRefinementExtensionKt.setOriginalCallDataForPluginRefinedCall(firNamedFunctionBuilder.mo288build(), new OriginalCallData(firNamedFunctionSymbol, firFunctionCallRefinementExtension));
        Function1<FirNamedFunctionSymbol, Unit> callback = callReturnType.getCallback();
        if (callback != null) {
            callback.invoke(firNamedFunctionSymbol2);
        }
        return firNamedFunctionSymbol2;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    private final boolean requiresCompanionBlockOrExtensionLf(FirBasedSymbol<?> firBasedSymbol) throws KotlinIllegalArgumentExceptionWithAttachments {
        ImportedFromObjectOrStaticData importedFromObjectOrStaticData;
        FirCallableDeclaration original;
        if (!(firBasedSymbol instanceof FirCallableSymbol)) {
            return false;
        }
        FirDeclarationOrigin origin = firBasedSymbol.getOrigin();
        if (!(origin instanceof FirDeclarationOrigin.Java) && !Intrinsics.areEqual(origin, FirDeclarationOrigin.Enhancement.INSTANCE)) {
            FirDeclaration fir = firBasedSymbol.getFir();
            FirCallableDeclaration firCallableDeclaration = fir instanceof FirCallableDeclaration ? (FirCallableDeclaration) fir : null;
            if (firCallableDeclaration == null || (importedFromObjectOrStaticData = FirAbstractImportingScopeKt.getImportedFromObjectOrStaticData(firCallableDeclaration)) == null || (original = importedFromObjectOrStaticData.getOriginal()) == null || !DeclarationUtilsKt.isJavaOrEnhancement(original)) {
                FirCallableSymbol firCallableSymbol = (FirCallableSymbol) firBasedSymbol;
                if (!firCallableSymbol.getRawStatus().isStatic() || (firBasedSymbol instanceof FirEnumEntrySymbol)) {
                    return false;
                }
                FirClassLikeSymbol<?> containingClassSymbol = ContainingClassUtilsKt.getContainingClassSymbol(firBasedSymbol);
                FirClassSymbol firClassSymbol = containingClassSymbol instanceof FirClassSymbol ? (FirClassSymbol) containingClassSymbol : null;
                return firClassSymbol == null || !UtilsKt.isGeneratedStaticEnumMember((FirCallableDeclaration) firCallableSymbol.getFir(), (FirClass) firClassSymbol.getFir());
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final FirBasedSymbol<?> unwrapIntegerOperatorSymbolIfNeeded(FirBasedSymbol<?> firBasedSymbol, CallInfo callInfo) {
        FirNamedFunctionSymbol originalForWrappedIntegerOperator;
        if (!(firBasedSymbol instanceof FirNamedFunctionSymbol)) {
            return firBasedSymbol;
        }
        FirNamedFunctionSymbol firNamedFunctionSymbol = (FirNamedFunctionSymbol) firBasedSymbol;
        if (((FirNamedFunction) firNamedFunctionSymbol.getFir()).getValueParameters().isEmpty() || (originalForWrappedIntegerOperator = FirIntegerConstantOperatorScopeKt.getOriginalForWrappedIntegerOperator((FirNamedFunction) firNamedFunctionSymbol.getFir())) == null) {
            return firBasedSymbol;
        }
        FirExpression firExpression = (FirExpression) CollectionsKt.firstOrNull(callInfo.getArguments());
        return (firExpression == null || !ResolveUtilsKt.isIntegerLiteralOrOperatorCall(firExpression)) ? originalForWrappedIntegerOperator : firBasedSymbol;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Candidate createCandidate(CallInfo callInfo, FirBasedSymbol<?> symbol, ExplicitReceiverKind explicitReceiverKind, FirScope scope, FirExpression dispatchReceiver, FirExpression givenExtensionReceiver, boolean objectsByName, boolean isFromOriginalTypeInPresenceOfSmartCast) {
        FirBasedSymbol<?> firBasedSymbolUnwrapIntegerOperatorSymbolIfNeeded;
        AmbiguousInterceptedSymbol ambiguousInterceptedSymbol;
        boolean zIsCandidateFromCompanionObjectTypeScope;
        FirRegularClassSymbol regularClassSymbol;
        FirBasedSymbol<?> symbol2;
        callInfo.getClass();
        symbol.getClass();
        explicitReceiverKind.getClass();
        if (this.callRefinementExtensions != null && Intrinsics.areEqual(callInfo.getCallKind(), CallKind.Function.INSTANCE) && (symbol instanceof FirNamedFunctionSymbol)) {
            Pair<FirBasedSymbol<?>, AmbiguousInterceptedSymbol> pairReplaceFromPluginsIfNeeded = replaceFromPluginsIfNeeded((FirNamedFunctionSymbol) symbol, this.callRefinementExtensions, callInfo);
            AmbiguousInterceptedSymbol ambiguousInterceptedSymbol2 = (AmbiguousInterceptedSymbol) pairReplaceFromPluginsIfNeeded.getSecond();
            firBasedSymbolUnwrapIntegerOperatorSymbolIfNeeded = (FirBasedSymbol) pairReplaceFromPluginsIfNeeded.getFirst();
            ambiguousInterceptedSymbol = ambiguousInterceptedSymbol2;
        } else {
            firBasedSymbolUnwrapIntegerOperatorSymbolIfNeeded = unwrapIntegerOperatorSymbolIfNeeded(symbol, callInfo);
            ambiguousInterceptedSymbol = null;
        }
        ConeResolutionAtom.Companion companion = ConeResolutionAtom.INSTANCE;
        AmbiguousInterceptedSymbol ambiguousInterceptedSymbol3 = ambiguousInterceptedSymbol;
        ConeResolutionAtom coneResolutionAtomCreateRawAtomNullable = companion.createRawAtomNullable(dispatchReceiver);
        ConeResolutionAtom coneResolutionAtomCreateRawAtomNullable2 = companion.createRawAtomNullable(givenExtensionReceiver);
        InferenceComponents.ConstraintSystemFactory constraintSystemFactory = this.context.getInferenceComponents().getConstraintSystemFactory();
        ConstraintStorage constraintStorage = this.baseSystem;
        int i = WhenMappings.$EnumSwitchMapping$0[explicitReceiverKind.ordinal()];
        if (i == 1) {
            zIsCandidateFromCompanionObjectTypeScope = isCandidateFromCompanionObjectTypeScope(givenExtensionReceiver, callInfo.getSession());
        } else if (i == 2) {
            zIsCandidateFromCompanionObjectTypeScope = isCandidateFromCompanionObjectTypeScope(dispatchReceiver, callInfo.getSession());
        } else {
            if (i != 3 && i != 4) {
                bu8.a();
                return null;
            }
            zIsCandidateFromCompanionObjectTypeScope = false;
        }
        Candidate candidate = new Candidate(firBasedSymbolUnwrapIntegerOperatorSymbolIfNeeded, coneResolutionAtomCreateRawAtomNullable, coneResolutionAtomCreateRawAtomNullable2, explicitReceiverKind, constraintSystemFactory, constraintStorage, callInfo, scope, zIsCandidateFromCompanionObjectTypeScope, isFromOriginalTypeInPresenceOfSmartCast, this.context.getBodyResolveContext());
        if (ambiguousInterceptedSymbol3 != null) {
            candidate.addDiagnostic(ambiguousInterceptedSymbol3);
        }
        FirElement callSite = callInfo.getCallSite();
        if (callSite instanceof FirCallableReferenceAccess) {
            if ((firBasedSymbolUnwrapIntegerOperatorSymbolIfNeeded instanceof FirValueParameterSymbol) || (firBasedSymbolUnwrapIntegerOperatorSymbolIfNeeded instanceof FirLocalPropertySymbol) || (firBasedSymbolUnwrapIntegerOperatorSymbolIfNeeded instanceof FirBackingFieldSymbol)) {
                candidate.addDiagnostic(new Unsupported("References to variables aren't supported yet", ((FirCallableReferenceAccess) callSite).getCalleeReference().getSource()));
            } else if (firBasedSymbolUnwrapIntegerOperatorSymbolIfNeeded instanceof FirEnumEntrySymbol) {
                candidate.addDiagnostic(new Unsupported("References to enum entries aren't supported", ((FirCallableReferenceAccess) callSite).getCalleeReference().getSource()));
            }
        } else if (objectsByName && isRegularClassWithoutCompanion(firBasedSymbolUnwrapIntegerOperatorSymbolIfNeeded, callInfo.getSession())) {
            candidate.addDiagnostic(NoCompanionObject.INSTANCE);
        }
        if (callInfo.getOrigin() == FirFunctionCallOrigin.Operator) {
            if (firBasedSymbolUnwrapIntegerOperatorSymbolIfNeeded instanceof FirFunctionSymbol) {
                Candidate candidateForCommonInvokeReceiver = callInfo.getCandidateForCommonInvokeReceiver();
                if (candidateForCommonInvokeReceiver == null || (symbol2 = candidateForCommonInvokeReceiver.getSymbol()) == null || (symbol2 instanceof FirFunctionSymbol)) {
                    symbol2 = null;
                }
            } else {
                symbol2 = firBasedSymbolUnwrapIntegerOperatorSymbolIfNeeded;
            }
            if (symbol2 != null) {
                candidate.addDiagnostic(new NotFunctionAsOperator(symbol2));
            }
        }
        if ((firBasedSymbolUnwrapIntegerOperatorSymbolIfNeeded instanceof FirPropertySymbol) && !FirLanguageSettingsComponentKt.getLanguageVersionSettings(this.context.getSession()).supportsFeature(LanguageFeature.PrioritizedEnumEntries)) {
            ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag((FirCallableSymbol<?>) firBasedSymbolUnwrapIntegerOperatorSymbolIfNeeded);
            FirRegularClass firRegularClass = (coneClassLikeLookupTagContainingClassLookupTag == null || (regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(coneClassLikeLookupTagContainingClassLookupTag, this.context.getSession())) == null) ? null : (FirRegularClass) regularClassSymbol.getFir();
            if (firRegularClass != null && UtilsKt.isEnumEntries((FirVariable) ((FirPropertySymbol) firBasedSymbolUnwrapIntegerOperatorSymbolIfNeeded).getFir(), firRegularClass)) {
                candidate.addDiagnostic(LowerPriorityToPreserveCompatibilityDiagnostic.INSTANCE);
            }
        }
        if (ArgumentUtilsKt.isInaccessibleAndInapplicable(dispatchReceiver)) {
            candidate.addDiagnostic(ArgumentUtilsKt.toInaccessibleReceiverDiagnostic((FirInaccessibleReceiverExpression) dispatchReceiver));
        }
        if (ArgumentUtilsKt.isInaccessibleAndInapplicable(givenExtensionReceiver)) {
            candidate.addDiagnostic(ArgumentUtilsKt.toInaccessibleReceiverDiagnostic((FirInaccessibleReceiverExpression) givenExtensionReceiver));
        }
        if (!FirLanguageSettingsComponentKt.getLanguageVersionSettings(this.context.getSession()).supportsFeature(LanguageFeature.CompanionBlocksAndExtensions) && requiresCompanionBlockOrExtensionLf(candidate.getSymbol())) {
            candidate.addDiagnostic(UnsupportedCompanionBlockOrExtensionCall.INSTANCE);
        }
        return candidate;
    }

    public final Candidate createErrorCandidate(CallInfo callInfo, ConeDiagnostic diagnostic) {
        FirBasedSymbol firBasedSymbolCreateErrorFunctionSymbol;
        callInfo.getClass();
        diagnostic.getClass();
        CallKind callKind = callInfo.getCallKind();
        if (callKind instanceof CallKind.VariableAccess) {
            firBasedSymbolCreateErrorFunctionSymbol = createErrorPropertySymbol(diagnostic, callInfo.getCallSite().getSource());
        } else {
            if (!(callKind instanceof CallKind.Function) && !(callKind instanceof CallKind.DelegatingConstructorCall) && !(callKind instanceof CallKind.CallableReference) && !(callKind instanceof CallKind.CollectionLiteral)) {
                if ((callKind instanceof CallKind.SyntheticSelect) || (callKind instanceof CallKind.SyntheticIdForCallableReferencesResolution) || (callKind instanceof CallKind.CustomForIde)) {
                    g33.a();
                    return null;
                }
                bu8.a();
                return null;
            }
            firBasedSymbolCreateErrorFunctionSymbol = createErrorFunctionSymbol(diagnostic);
        }
        return new Candidate(firBasedSymbolCreateErrorFunctionSymbol, null, null, ExplicitReceiverKind.NO_EXPLICIT_RECEIVER, this.context.getInferenceComponents().getConstraintSystemFactory(), this.baseSystem, callInfo, null, false, false, this.context.getBodyResolveContext(), 768, null);
    }

    public final ResolutionContext getContext() {
        return this.context;
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002J\u0016\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\rJ\"\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\r2\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0002¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CandidateFactory$Companion;", Argument.Delimiters.none, "<init>", "()V", "buildBaseSystem", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/ConstraintStorage;", "context", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "callInfo", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;", "createForCallableReferences", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CandidateFactory;", "containingCall", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "buildBaseSystemForContainingCallAwareCases", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final ConstraintStorage buildBaseSystem(ResolutionContext context, CallInfo callInfo) {
            Candidate containingCandidateForCollectionLiteral = callInfo.getContainingCandidateForCollectionLiteral();
            if (containingCandidateForCollectionLiteral != null) {
                return CandidateFactory.INSTANCE.buildBaseSystemForContainingCallAwareCases(context, containingCandidateForCollectionLiteral, callInfo);
            }
            ConstraintSystemMarker constraintSystemMarkerCreateConstraintSystem$default = InferenceComponents.createConstraintSystem$default(context.getInferenceComponents(), null, 1, null);
            Iterator<T> it = callInfo.getArgumentAtoms().iterator();
            while (it.hasNext()) {
                CandidateFactoryKt.addSubsystemFromAtom(constraintSystemMarkerCreateConstraintSystem$default, (ConeResolutionAtom) it.next());
            }
            FirInferenceLogger inferenceLogger = FirInferenceLoggerKt.getInferenceLogger(context.getSession());
            if (inferenceLogger != null) {
                inferenceLogger.logStage("CandidateFactory.buildBaseSystem()", constraintSystemMarkerCreateConstraintSystem$default);
            }
            return constraintSystemMarkerCreateConstraintSystem$default.asReadOnlyStorage();
        }

        private final ConstraintStorage buildBaseSystemForContainingCallAwareCases(ResolutionContext context, Candidate containingCall, CallInfo callInfo) {
            List<ConeResolutionAtom> argumentAtoms;
            NewConstraintSystemImpl newConstraintSystemImplCreateConstraintSystem$default = InferenceComponents.createConstraintSystem$default(context.getInferenceComponents(), null, 1, null);
            newConstraintSystemImplCreateConstraintSystem$default.setBaseSystem(containingCall.getSystem().currentStorage());
            if (callInfo != null && (argumentAtoms = callInfo.getArgumentAtoms()) != null) {
                Iterator<T> it = argumentAtoms.iterator();
                while (it.hasNext()) {
                    CandidateFactoryKt.addSubsystemFromAtom(newConstraintSystemImplCreateConstraintSystem$default, (ConeResolutionAtom) it.next());
                }
            }
            return newConstraintSystemImplCreateConstraintSystem$default.asReadOnlyStorage();
        }

        public final CandidateFactory createForCallableReferences(ResolutionContext context, Candidate containingCall) {
            context.getClass();
            containingCall.getClass();
            return new CandidateFactory(context, buildBaseSystemForContainingCallAwareCases(context, containingCall, null), null);
        }

        private Companion() {
        }
    }

    public /* synthetic */ CandidateFactory(ResolutionContext resolutionContext, ConstraintStorage constraintStorage, DefaultConstructorMarker defaultConstructorMarker) {
        this(resolutionContext, constraintStorage);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public CandidateFactory(ResolutionContext resolutionContext, CallInfo callInfo) {
        this(resolutionContext, INSTANCE.buildBaseSystem(resolutionContext, callInfo));
        resolutionContext.getClass();
        callInfo.getClass();
    }
}
