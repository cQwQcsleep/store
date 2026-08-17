package org.jetbrains.kotlin.fir.resolve.inference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.AnalysisFlags;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.diagnostics.ConeCannotInferTypeParameterType;
import org.jetbrains.kotlin.fir.diagnostics.ConeCannotInferValueParameterType;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.resolve.BodyResolveComponents;
import org.jetbrains.kotlin.fir.resolve.calls.ConeAtomWithCandidate;
import org.jetbrains.kotlin.fir.resolve.calls.ConeCollectionLiteralAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConeContextSensitiveAlternativeForQualifierAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConeFunctionTypeRelatedPostponedResolvedAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConeLambdaWithTypeVariableAsExpectedTypeAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConePostponedResolvedAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolvedCallableReferenceAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolvedLambdaAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConeSimpleNameForContextSensitiveResolution;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionContext;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CandidateTraversalKt;
import org.jetbrains.kotlin.fir.resolve.inference.ConstraintSystemCompleter;
import org.jetbrains.kotlin.fir.resolve.inference.model.ConeFixVariableConstraintPosition;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeVariable;
import org.jetbrains.kotlin.fir.types.ConeTypeVariableTypeConstructor;
import org.jetbrains.kotlin.resolve.calls.inference.components.ConstraintSystemCompletionContext;
import org.jetbrains.kotlin.resolve.calls.inference.components.ConstraintSystemCompletionMode;
import org.jetbrains.kotlin.resolve.calls.inference.components.PostponedArgumentInputTypesResolver;
import org.jetbrains.kotlin.resolve.calls.inference.components.TypeVariableDependencyInformationProvider;
import org.jetbrains.kotlin.resolve.calls.inference.components.TypeVariableDirectionCalculator;
import org.jetbrains.kotlin.resolve.calls.inference.components.VariableFixationFinder;
import org.jetbrains.kotlin.resolve.calls.inference.model.NotEnoughInformationForTypeParameter;
import org.jetbrains.kotlin.resolve.calls.inference.model.VariableWithConstraints;
import org.jetbrains.kotlin.resolve.calls.model.PostponedAtomWithRevisableExpectedType;
import org.jetbrains.kotlin.types.AbstractTypeChecker;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;
import org.jetbrains.kotlin.types.model.RigidTypeMarker;
import org.jetbrains.kotlin.types.model.TypeArgumentListMarker;
import org.jetbrains.kotlin.types.model.TypeArgumentMarker;
import org.jetbrains.kotlin.types.model.TypeConstructorMarker;
import org.jetbrains.kotlin.types.model.TypeVariableMarker;
import org.jetbrains.kotlin.types.model.TypeVariableTypeConstructorMarker;
import org.jetbrains.kotlin.utils.CollectionsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0092\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 <2\u00020\u0001:\u0002;<B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001e\u0010\u000e\u001a\u00020\u000f*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\u0013\u001a\u00020\u0014H\u0002J\u0014\u0010\u000e\u001a\u00020\u000f*\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J<\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0010J:\u0010$\u001a\u00020\u000f*\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\u0006\u0010%\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0010H\u0002J:\u0010&\u001a\u0004\u0018\u00010'*\u00020\u00192\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00120\u001d2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010%\u001a\u00020 H\u0002J,\u0010)\u001a\u0004\u0018\u00010\u0016*\u00020\u00192\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00120\u001d2\u0006\u0010!\u001a\u00020\"2\u0006\u0010*\u001a\u00020+H\u0002J*\u0010,\u001a\u00020\u0014*\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00120\u001d2\u0006\u0010#\u001a\u00020\u0010H\u0002J \u0010-\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010.\u001a\u00020\"2\u0006\u0010/\u001a\u000200H\u0002J\u0014\u00101\u001a\u00020\u0014*\u00020\u00192\u0006\u00102\u001a\u00020'H\u0002J\u001e\u00103\u001a\u00020\u00142\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00120\u001d2\u0006\u0010#\u001a\u00020\u0010H\u0002J8\u00104\u001a\u00020\u000f*\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\u0006\u0010%\u001a\u00020 2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00120\u001dH\u0002J\"\u00105\u001a\u00020\u000f*\u00020\u00192\u0006\u00106\u001a\u0002072\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dH\u0002J \u00108\u001a\b\u0012\u0004\u0012\u0002090\u001d*\u00020\u00192\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001dH\u0002J\u0018\u0010:\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u00106\u001a\u000207H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006="}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/inference/ConstraintSystemCompleter;", Argument.Delimiters.none, "components", "Lorg/jetbrains/kotlin/fir/resolve/BodyResolveComponents;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/BodyResolveComponents;)V", "inferenceComponents", "Lorg/jetbrains/kotlin/fir/resolve/inference/InferenceComponents;", "variableFixationFinder", "Lorg/jetbrains/kotlin/resolve/calls/inference/components/VariableFixationFinder;", "postponedArgumentsInputTypesResolver", "Lorg/jetbrains/kotlin/resolve/calls/inference/components/PostponedArgumentInputTypesResolver;", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "analyze", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/inference/ConstraintSystemCompleter$PostponedAtomAnalyzer;", "postponedResolvedAtom", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConePostponedResolvedAtom;", "withPCLASession", Argument.Delimiters.none, "precalculatedBoundsForCL", "Lorg/jetbrains/kotlin/fir/resolve/inference/CollectionLiteralBounds;", "complete", "c", "Lorg/jetbrains/kotlin/resolve/calls/inference/components/ConstraintSystemCompletionContext;", "completionMode", "Lorg/jetbrains/kotlin/resolve/calls/inference/components/ConstraintSystemCompletionMode;", "topLevelAtoms", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtom;", "candidateReturnType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "context", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "analyzer", "runCompletion", "topLevelType", "findFirstVariableForFixation", "Lorg/jetbrains/kotlin/resolve/calls/inference/components/VariableFixationFinder$VariableForFixation;", "postponedArguments", "findFirstCollectionLiteralForFixation", "dependencyProvider", "Lorg/jetbrains/kotlin/resolve/calls/inference/components/TypeVariableDependencyInformationProvider;", "tryToCompleteWithPCLA", "transformToAtomWithNewFunctionExpectedType", "resolutionContext", "argument", "Lorg/jetbrains/kotlin/resolve/calls/model/PostponedAtomWithRevisableExpectedType;", "fixVariableIfReady", "variableForFixation", "analyzeContextSensitiveResolutionAlternatives", "reportNotEnoughTypeInformation", "processVariableWhenNotEnoughInformation", "variableWithConstraints", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/VariableWithConstraints;", "getOrderedAllTypeVariables", "Lorg/jetbrains/kotlin/types/model/TypeConstructorMarker;", "fixVariable", "PostponedAtomAnalyzer", "Companion", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConstraintSystemCompleter {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final InferenceComponents inferenceComponents;
    private final LanguageVersionSettings languageVersionSettings;
    private final PostponedArgumentInputTypesResolver postponedArgumentsInputTypesResolver;
    private final VariableFixationFinder variableFixationFinder;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/inference/ConstraintSystemCompleter$PostponedAtomAnalyzer;", Argument.Delimiters.none, "analyzeInternal", Argument.Delimiters.none, "postponedResolvedAtom", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConePostponedResolvedAtom;", "withPCLASession", Argument.Delimiters.none, "precalculatedBoundsForCL", "Lorg/jetbrains/kotlin/fir/resolve/inference/CollectionLiteralBounds;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public interface PostponedAtomAnalyzer {
        void analyzeInternal(ConePostponedResolvedAtom postponedResolvedAtom, boolean withPCLASession, CollectionLiteralBounds precalculatedBoundsForCL);
    }

    public ConstraintSystemCompleter(BodyResolveComponents bodyResolveComponents) {
        bodyResolveComponents.getClass();
        InferenceComponents inferenceComponents = InferenceComponentsKt.getInferenceComponents(bodyResolveComponents.getSession());
        this.inferenceComponents = inferenceComponents;
        this.variableFixationFinder = inferenceComponents.getVariableFixationFinder();
        this.postponedArgumentsInputTypesResolver = inferenceComponents.getPostponedArgumentInputTypesResolver();
        this.languageVersionSettings = FirLanguageSettingsComponentKt.getLanguageVersionSettings(bodyResolveComponents.getSession());
    }

    public static Unit a(ConstraintSystemCompleter constraintSystemCompleter, PostponedAtomAnalyzer postponedAtomAnalyzer, ConePostponedResolvedAtom conePostponedResolvedAtom) {
        conePostponedResolvedAtom.getClass();
        analyze$default(constraintSystemCompleter, postponedAtomAnalyzer, conePostponedResolvedAtom, false, 2, null);
        return Unit.INSTANCE;
    }

    private final void analyze(PostponedAtomAnalyzer postponedAtomAnalyzer, CollectionLiteralBounds collectionLiteralBounds) {
        postponedAtomAnalyzer.analyzeInternal(collectionLiteralBounds.getAtom(), false, collectionLiteralBounds);
    }

    public static /* synthetic */ void analyze$default(ConstraintSystemCompleter constraintSystemCompleter, PostponedAtomAnalyzer postponedAtomAnalyzer, ConePostponedResolvedAtom conePostponedResolvedAtom, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        constraintSystemCompleter.analyze(postponedAtomAnalyzer, conePostponedResolvedAtom, z);
    }

    private final boolean analyzeContextSensitiveResolutionAlternatives(List<? extends ConePostponedResolvedAtom> postponedArguments, PostponedAtomAnalyzer analyzer) {
        if (!((Boolean) this.languageVersionSettings.getFlag(AnalysisFlags.getIdeMode())).booleanValue()) {
            return false;
        }
        boolean z = false;
        for (ConePostponedResolvedAtom conePostponedResolvedAtom : postponedArguments) {
            if (conePostponedResolvedAtom instanceof ConeContextSensitiveAlternativeForQualifierAtom) {
                analyze(analyzer, conePostponedResolvedAtom, false);
                z = true;
            }
        }
        return z;
    }

    public static Unit b(LinkedHashSet linkedHashSet, ConstraintSystemCompletionContext constraintSystemCompletionContext, Candidate candidate) {
        candidate.getClass();
        Iterator<T> it = candidate.getFreshVariables().iterator();
        while (it.hasNext()) {
            TypeConstructorMarker orderedAllTypeVariables$toTypeConstructor = getOrderedAllTypeVariables$toTypeConstructor((ConeTypeVariable) it.next(), constraintSystemCompletionContext);
            if (orderedAllTypeVariables$toTypeConstructor != null) {
                linkedHashSet.add(orderedAllTypeVariables$toTypeConstructor);
            }
        }
        return Unit.INSTANCE;
    }

    public static Unit c(ConstraintSystemCompleter constraintSystemCompleter, PostponedAtomAnalyzer postponedAtomAnalyzer, ConePostponedResolvedAtom conePostponedResolvedAtom) {
        conePostponedResolvedAtom.getClass();
        analyze$default(constraintSystemCompleter, postponedAtomAnalyzer, conePostponedResolvedAtom, false, 2, null);
        return Unit.INSTANCE;
    }

    public static Unit d(ConstraintSystemCompleter constraintSystemCompleter, PostponedAtomAnalyzer postponedAtomAnalyzer, ConePostponedResolvedAtom conePostponedResolvedAtom) {
        conePostponedResolvedAtom.getClass();
        analyze$default(constraintSystemCompleter, postponedAtomAnalyzer, conePostponedResolvedAtom, false, 2, null);
        return Unit.INSTANCE;
    }

    public static Unit e(LinkedHashSet linkedHashSet, ConstraintSystemCompletionContext constraintSystemCompletionContext, ConePostponedResolvedAtom conePostponedResolvedAtom) {
        conePostponedResolvedAtom.getClass();
        if (conePostponedResolvedAtom instanceof ConeResolvedLambdaAtom) {
            CollectionsKt.addIfNotNull(linkedHashSet, getOrderedAllTypeVariables$toTypeConstructor(((ConeResolvedLambdaAtom) conePostponedResolvedAtom).getTypeVariableForLambdaReturnType(), constraintSystemCompletionContext));
        } else if (conePostponedResolvedAtom instanceof ConeLambdaWithTypeVariableAsExpectedTypeAtom) {
            getOrderedAllTypeVariables$collectNotFixedVariables((PostponedAtomWithRevisableExpectedType) conePostponedResolvedAtom, constraintSystemCompletionContext, linkedHashSet);
        } else if (conePostponedResolvedAtom instanceof ConeResolvedCallableReferenceAtom) {
            if (((ConeResolvedCallableReferenceAtom) conePostponedResolvedAtom).getNeedsResolution()) {
                getOrderedAllTypeVariables$collectNotFixedVariables((PostponedAtomWithRevisableExpectedType) conePostponedResolvedAtom, constraintSystemCompletionContext, linkedHashSet);
            }
        } else if (!(conePostponedResolvedAtom instanceof ConeSimpleNameForContextSensitiveResolution) && !(conePostponedResolvedAtom instanceof ConeContextSensitiveAlternativeForQualifierAtom) && !(conePostponedResolvedAtom instanceof ConeCollectionLiteralAtom)) {
            bu8.a();
            return null;
        }
        return Unit.INSTANCE;
    }

    public static Unit f(ConstraintSystemCompleter constraintSystemCompleter, PostponedAtomAnalyzer postponedAtomAnalyzer, ConePostponedResolvedAtom conePostponedResolvedAtom) {
        conePostponedResolvedAtom.getClass();
        analyze$default(constraintSystemCompleter, postponedAtomAnalyzer, conePostponedResolvedAtom, false, 2, null);
        return Unit.INSTANCE;
    }

    private final CollectionLiteralBounds findFirstCollectionLiteralForFixation(ConstraintSystemCompletionContext constraintSystemCompletionContext, List<? extends ConePostponedResolvedAtom> list, ResolutionContext resolutionContext, TypeVariableDependencyInformationProvider typeVariableDependencyInformationProvider) {
        CollectionLiteralBoundsCollector collectionLiteralBoundsCollector = new CollectionLiteralBoundsCollector(typeVariableDependencyInformationProvider);
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            if (obj instanceof ConeCollectionLiteralAtom) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            CollectionLiteralBounds collectionLiteralBoundsCollectBoundsForCollectionLiteral = collectionLiteralBoundsCollector.collectBoundsForCollectionLiteral(constraintSystemCompletionContext, resolutionContext, (ConeCollectionLiteralAtom) it.next());
            if (collectionLiteralBoundsCollectBoundsForCollectionLiteral != null) {
                arrayList2.add(collectionLiteralBoundsCollectBoundsForCollectionLiteral);
            }
        }
        return (CollectionLiteralBounds) kotlin.collections.CollectionsKt.maxOrNull(arrayList2);
    }

    private final VariableFixationFinder.VariableForFixation findFirstVariableForFixation(ConstraintSystemCompletionContext constraintSystemCompletionContext, List<? extends ConeResolutionAtom> list, List<? extends ConePostponedResolvedAtom> list2, ConstraintSystemCompletionMode constraintSystemCompletionMode, ConeKotlinType coneKotlinType) {
        return this.variableFixationFinder.findFirstVariableForFixation(constraintSystemCompletionContext, getOrderedAllTypeVariables(constraintSystemCompletionContext, list), list2, constraintSystemCompletionMode, coneKotlinType);
    }

    private final void fixVariable(ConstraintSystemCompletionContext c, VariableWithConstraints variableWithConstraints) {
        KotlinTypeMarker kotlinTypeMarkerFindResultType = this.inferenceComponents.getResultTypeResolver().findResultType(c, variableWithConstraints, TypeVariableDirectionCalculator.ResolveDirection.UNKNOWN);
        TypeVariableMarker typeVariable = variableWithConstraints.getTypeVariable();
        c.fixVariable(typeVariable, kotlinTypeMarkerFindResultType, new ConeFixVariableConstraintPosition(typeVariable));
    }

    private final boolean fixVariableIfReady(ConstraintSystemCompletionContext constraintSystemCompletionContext, VariableFixationFinder.VariableForFixation variableForFixation) {
        VariableWithConstraints variableWithConstraints = (VariableWithConstraints) MapsKt.getValue(constraintSystemCompletionContext.getNotFixedTypeVariables(), variableForFixation.getVariable());
        if (!variableForFixation.isReady()) {
            return false;
        }
        fixVariable(constraintSystemCompletionContext, variableWithConstraints);
        return true;
    }

    private final List<TypeConstructorMarker> getOrderedAllTypeVariables(ConstraintSystemCompletionContext constraintSystemCompletionContext, List<? extends ConeResolutionAtom> list) {
        LinkedHashSet linkedHashSet = new LinkedHashSet(constraintSystemCompletionContext.getNotFixedTypeVariables().size());
        Iterator<? extends ConeResolutionAtom> it = list.iterator();
        while (it.hasNext()) {
            getOrderedAllTypeVariables$collectAllTypeVariables(it.next(), linkedHashSet, constraintSystemCompletionContext);
        }
        return kotlin.collections.CollectionsKt.toList(linkedHashSet);
    }

    private static final void getOrderedAllTypeVariables$collectAllTypeVariables(ConeResolutionAtom coneResolutionAtom, final LinkedHashSet<TypeConstructorMarker> linkedHashSet, final ConstraintSystemCompletionContext constraintSystemCompletionContext) {
        CandidateTraversalKt.processCandidatesAndPostponedAtoms(coneResolutionAtom, new Function1() { // from class: os2
            public final Object invoke(Object obj) {
                return ConstraintSystemCompleter.b(linkedHashSet, constraintSystemCompletionContext, (Candidate) obj);
            }
        }, new Function1() { // from class: ps2
            public final Object invoke(Object obj) {
                return ConstraintSystemCompleter.e(linkedHashSet, constraintSystemCompletionContext, (ConePostponedResolvedAtom) obj);
            }
        });
    }

    private static final void getOrderedAllTypeVariables$collectNotFixedVariables(PostponedAtomWithRevisableExpectedType postponedAtomWithRevisableExpectedType, ConstraintSystemCompletionContext constraintSystemCompletionContext, LinkedHashSet<TypeConstructorMarker> linkedHashSet) {
        RigidTypeMarker rigidTypeMarkerLowerBoundIfFlexible;
        TypeArgumentListMarker typeArgumentListMarkerAsArgumentList;
        TypeConstructorMarker typeConstructorMarkerTypeConstructor;
        KotlinTypeMarker revisedExpectedType = postponedAtomWithRevisableExpectedType.getRevisedExpectedType();
        if (revisedExpectedType == null || (rigidTypeMarkerLowerBoundIfFlexible = constraintSystemCompletionContext.lowerBoundIfFlexible(revisedExpectedType)) == null || (typeArgumentListMarkerAsArgumentList = constraintSystemCompletionContext.asArgumentList(rigidTypeMarkerLowerBoundIfFlexible)) == null) {
            return;
        }
        Iterator it = constraintSystemCompletionContext.iterator(typeArgumentListMarkerAsArgumentList);
        while (it.hasNext()) {
            KotlinTypeMarker type = constraintSystemCompletionContext.getType((TypeArgumentMarker) it.next());
            if (type != null && (typeConstructorMarkerTypeConstructor = constraintSystemCompletionContext.typeConstructor(type)) != null && constraintSystemCompletionContext.getNotFixedTypeVariables().containsKey(typeConstructorMarkerTypeConstructor)) {
                linkedHashSet.add(typeConstructorMarkerTypeConstructor);
            }
        }
    }

    private static final TypeConstructorMarker getOrderedAllTypeVariables$toTypeConstructor(ConeTypeVariable coneTypeVariable, ConstraintSystemCompletionContext constraintSystemCompletionContext) {
        ConeTypeVariableTypeConstructor typeConstructor;
        if (coneTypeVariable == null || (typeConstructor = coneTypeVariable.getTypeConstructor()) == null || !constraintSystemCompletionContext.getNotFixedTypeVariables().keySet().contains(typeConstructor)) {
            return null;
        }
        return typeConstructor;
    }

    private final void processVariableWhenNotEnoughInformation(ConstraintSystemCompletionContext constraintSystemCompletionContext, VariableWithConstraints variableWithConstraints, List<? extends ConeResolutionAtom> list) {
        ConeErrorType coneErrorTypeCreateCannotInferErrorType$default;
        TypeVariableMarker typeVariable = variableWithConstraints.getTypeVariable();
        Companion companion = INSTANCE;
        Object objFindStatementOfFirstAtomWithVariable = companion.findStatementOfFirstAtomWithVariable(typeVariable, list);
        if (objFindStatementOfFirstAtomWithVariable == null) {
            ConeResolutionAtom coneResolutionAtom = (ConeResolutionAtom) kotlin.collections.CollectionsKt.firstOrNull(list);
            objFindStatementOfFirstAtomWithVariable = coneResolutionAtom != null ? coneResolutionAtom.getExpression() : null;
        }
        if (objFindStatementOfFirstAtomWithVariable != null) {
            constraintSystemCompletionContext.addError(new NotEnoughInformationForTypeParameter(typeVariable, objFindStatementOfFirstAtomWithVariable, constraintSystemCompletionContext.couldBeResolvedWithUnrestrictedBuilderInference()));
        }
        if (typeVariable instanceof ConeTypeParameterBasedTypeVariable) {
            ConeTypeParameterBasedTypeVariable coneTypeParameterBasedTypeVariable = (ConeTypeParameterBasedTypeVariable) typeVariable;
            coneErrorTypeCreateCannotInferErrorType$default = companion.createCannotInferErrorType(coneTypeParameterBasedTypeVariable.getTypeParameterSymbol(), "Cannot infer argument for type parameter " + coneTypeParameterBasedTypeVariable.getTypeParameterSymbol().getName(), true);
        } else if (typeVariable instanceof ConeTypeVariableForLambdaParameterType) {
            coneErrorTypeCreateCannotInferErrorType$default = Companion.createCannotInferErrorType$default(companion, null, "Cannot infer lambda parameter type", false, 4, null);
        } else {
            coneErrorTypeCreateCannotInferErrorType$default = Companion.createCannotInferErrorType$default(companion, null, "Cannot infer type variable " + typeVariable, false, 4, null);
        }
        constraintSystemCompletionContext.fixVariable(typeVariable, coneErrorTypeCreateCannotInferErrorType$default, new ConeFixVariableConstraintPosition(typeVariable));
    }

    private final void reportNotEnoughTypeInformation(ConstraintSystemCompletionContext constraintSystemCompletionContext, ConstraintSystemCompletionMode constraintSystemCompletionMode, List<? extends ConeResolutionAtom> list, ConeKotlinType coneKotlinType, List<? extends ConePostponedResolvedAtom> list2) {
        while (true) {
            VariableFixationFinder.VariableForFixation variableForFixationFindFirstVariableForFixation = findFirstVariableForFixation(constraintSystemCompletionContext, list, list2, constraintSystemCompletionMode, coneKotlinType);
            if (variableForFixationFindFirstVariableForFixation == null) {
                return;
            }
            variableForFixationFindFirstVariableForFixation.isReady();
            processVariableWhenNotEnoughInformation(constraintSystemCompletionContext, (VariableWithConstraints) MapsKt.getValue(constraintSystemCompletionContext.getNotFixedTypeVariables(), variableForFixationFindFirstVariableForFixation.getVariable()), list);
        }
    }

    private final void runCompletion(ConstraintSystemCompletionContext constraintSystemCompletionContext, ConstraintSystemCompletionMode constraintSystemCompletionMode, List<? extends ConeResolutionAtom> list, ConeKotlinType coneKotlinType, ResolutionContext resolutionContext, final PostponedAtomAnalyzer postponedAtomAnalyzer) {
        List<? extends ConePostponedResolvedAtom> list2;
        ArrayList arrayList;
        final ConstraintSystemCompleter constraintSystemCompleter = this;
        ConstraintSystemCompletionContext constraintSystemCompletionContext2 = constraintSystemCompletionContext;
        coneKotlinType = coneKotlinType;
        Set setExtractTypeVariables = constraintSystemCompletionContext2.extractTypeVariables(coneKotlinType);
        FirInferenceLogger inferenceLogger = FirInferenceLoggerKt.getInferenceLogger(resolutionContext.getSession());
        if (inferenceLogger != null) {
            inferenceLogger.logStage("Call Completion", constraintSystemCompletionContext2);
        }
        while (true) {
            if (constraintSystemCompletionMode.getShouldForkPointConstraintsBeResolved()) {
                constraintSystemCompletionContext2.resolveForkPointsConstraints();
            }
            List<? extends ConePostponedResolvedAtom> orderedNotAnalyzedPostponedArguments = INSTANCE.getOrderedNotAnalyzedPostponedArguments(list);
            if (constraintSystemCompletionMode.isUntilFirstLambda() && constraintSystemCompletionContext2.hasLambdaToAnalyze(orderedNotAnalyzedPostponedArguments)) {
                return;
            }
            if (constraintSystemCompleter.analyzeContextSensitiveResolutionAlternatives(orderedNotAnalyzedPostponedArguments, postponedAtomAnalyzer)) {
                coneKotlinType = coneKotlinType;
            } else if (constraintSystemCompletionContext2.analyzeArgumentWithFixedParameterTypes(orderedNotAnalyzedPostponedArguments, new Function1() { // from class: ks2
                public final Object invoke(Object obj) {
                    return ConstraintSystemCompleter.d(this.b, postponedAtomAnalyzer, (ConePostponedResolvedAtom) obj);
                }
            })) {
                continue;
            } else {
                VariableFixationFinder.VariableForFixation variableForFixationFindFirstVariableForFixation = constraintSystemCompleter.findFirstVariableForFixation(constraintSystemCompletionContext2, list, orderedNotAnalyzedPostponedArguments, constraintSystemCompletionMode, coneKotlinType);
                final ConstraintSystemCompleter constraintSystemCompleter2 = constraintSystemCompleter;
                boolean z = variableForFixationFindFirstVariableForFixation != null;
                if (orderedNotAnalyzedPostponedArguments.isEmpty() && !z) {
                    return;
                }
                List<? extends ConePostponedResolvedAtom> list3 = orderedNotAnalyzedPostponedArguments;
                ArrayList arrayList2 = new ArrayList();
                for (Object obj : list3) {
                    if (obj instanceof PostponedAtomWithRevisableExpectedType) {
                        arrayList2.add(obj);
                    }
                }
                TypeVariableDependencyInformationProvider typeVariableDependencyInformationProvider = new TypeVariableDependencyInformationProvider(constraintSystemCompletionContext.getNotFixedTypeVariables(), orderedNotAnalyzedPostponedArguments, coneKotlinType, constraintSystemCompletionContext, constraintSystemCompleter2.languageVersionSettings);
                List<? extends ConePostponedResolvedAtom> list4 = orderedNotAnalyzedPostponedArguments;
                constraintSystemCompletionContext2 = constraintSystemCompletionContext;
                CollectionLiteralBounds collectionLiteralBoundsFindFirstCollectionLiteralForFixation = constraintSystemCompleter2.findFirstCollectionLiteralForFixation(constraintSystemCompletionContext2, list4, resolutionContext, typeVariableDependencyInformationProvider);
                if (collectionLiteralBoundsFindFirstCollectionLiteralForFixation instanceof CollectionLiteralBounds.NonTvExpected) {
                    constraintSystemCompleter2.analyze(postponedAtomAnalyzer, collectionLiteralBoundsFindFirstCollectionLiteralForFixation);
                    coneKotlinType = coneKotlinType;
                    constraintSystemCompleter = constraintSystemCompleter2;
                } else {
                    TypeVariableDependencyInformationProvider typeVariableDependencyInformationProvider2 = typeVariableDependencyInformationProvider;
                    Set set = setExtractTypeVariables;
                    if (constraintSystemCompleter2.postponedArgumentsInputTypesResolver.collectParameterTypesAndBuildNewExpectedTypes(constraintSystemCompletionContext2, arrayList2, constraintSystemCompletionMode, typeVariableDependencyInformationProvider2, set)) {
                        constraintSystemCompletionContext2 = constraintSystemCompletionContext;
                    } else {
                        ArrayList arrayList3 = new ArrayList();
                        for (Object obj2 : list3) {
                            if (((ConePostponedResolvedAtom) obj2) instanceof ConeFunctionTypeRelatedPostponedResolvedAtom) {
                                arrayList3.add(obj2);
                            }
                        }
                        if (constraintSystemCompletionMode.getAllLambdasShouldBeAnalyzed()) {
                            Iterator it = arrayList3.iterator();
                            while (true) {
                                if (it.hasNext()) {
                                    ArrayList arrayList4 = arrayList3;
                                    TypeVariableDependencyInformationProvider typeVariableDependencyInformationProvider3 = typeVariableDependencyInformationProvider2;
                                    List<? extends ConePostponedResolvedAtom> list5 = list4;
                                    constraintSystemCompletionContext2 = constraintSystemCompletionContext;
                                    VariableFixationFinder.VariableForFixation variableForFixationFindNextReadyVariableForParameterType = constraintSystemCompleter2.postponedArgumentsInputTypesResolver.findNextReadyVariableForParameterType(constraintSystemCompletionContext2, (ConePostponedResolvedAtom) it.next(), list5, coneKotlinType, typeVariableDependencyInformationProvider3);
                                    typeVariableDependencyInformationProvider2 = typeVariableDependencyInformationProvider3;
                                    if (variableForFixationFindNextReadyVariableForParameterType == null || !constraintSystemCompleter2.fixVariableIfReady(constraintSystemCompletionContext2, variableForFixationFindNextReadyVariableForParameterType)) {
                                        arrayList3 = arrayList4;
                                        list4 = list5;
                                    }
                                } else {
                                    constraintSystemCompletionContext2 = constraintSystemCompletionContext;
                                    list2 = list4;
                                    arrayList = arrayList3;
                                    Iterator it2 = arrayList2.iterator();
                                    while (true) {
                                        if (it2.hasNext()) {
                                            if (constraintSystemCompleter2.transformToAtomWithNewFunctionExpectedType(constraintSystemCompletionContext2, resolutionContext, (PostponedAtomWithRevisableExpectedType) it2.next())) {
                                            }
                                        }
                                    }
                                }
                            }
                        } else {
                            constraintSystemCompletionContext2 = constraintSystemCompletionContext;
                            list2 = list4;
                            arrayList = arrayList3;
                        }
                        if (!constraintSystemCompletionContext2.analyzeNextReadyPostponedArgument(list2, constraintSystemCompletionMode, new Function1() { // from class: ls2
                            public final Object invoke(Object obj3) {
                                return ConstraintSystemCompleter.f(this.b, postponedAtomAnalyzer, (ConePostponedResolvedAtom) obj3);
                            }
                        }) && ((variableForFixationFindFirstVariableForFixation == null || !constraintSystemCompleter2.fixVariableIfReady(constraintSystemCompletionContext2, variableForFixationFindFirstVariableForFixation)) && !constraintSystemCompleter2.tryToCompleteWithPCLA(constraintSystemCompletionContext2, constraintSystemCompletionMode, list2, postponedAtomAnalyzer))) {
                            if (!constraintSystemCompletionMode.getAllLambdasShouldBeAnalyzed() || collectionLiteralBoundsFindFirstCollectionLiteralForFixation == null) {
                                if (constraintSystemCompletionMode.getFixNotInferredTypeVariablesToErrorType()) {
                                    List<? extends ConePostponedResolvedAtom> list6 = list2;
                                    constraintSystemCompleter = constraintSystemCompleter2;
                                    constraintSystemCompleter.reportNotEnoughTypeInformation(constraintSystemCompletionContext2, constraintSystemCompletionMode, list, coneKotlinType, list6);
                                    list2 = list6;
                                } else {
                                    constraintSystemCompleter = constraintSystemCompleter2;
                                }
                                if ((!constraintSystemCompletionMode.getAllLambdasShouldBeAnalyzed() || !constraintSystemCompletionContext2.analyzeRemainingNotAnalyzedPostponedArgument(arrayList, new Function1() { // from class: ms2
                                    public final Object invoke(Object obj3) {
                                        return ConstraintSystemCompleter.a(this.b, postponedAtomAnalyzer, (ConePostponedResolvedAtom) obj3);
                                    }
                                })) && (!constraintSystemCompletionMode.getAllPostponedAtomsShouldBeAnalyzed() || !constraintSystemCompletionContext2.analyzeRemainingNotAnalyzedPostponedArgument(list2, new Function1() { // from class: ns2
                                    public final Object invoke(Object obj3) {
                                        return ConstraintSystemCompleter.c(this.b, postponedAtomAnalyzer, (ConePostponedResolvedAtom) obj3);
                                    }
                                }))) {
                                    return;
                                }
                            } else {
                                constraintSystemCompleter2.analyze(postponedAtomAnalyzer, collectionLiteralBoundsFindFirstCollectionLiteralForFixation);
                            }
                        }
                        setExtractTypeVariables = set;
                    }
                    constraintSystemCompleter = constraintSystemCompleter2;
                    setExtractTypeVariables = set;
                }
            }
        }
    }

    private final boolean transformToAtomWithNewFunctionExpectedType(ConstraintSystemCompletionContext c, ResolutionContext resolutionContext, PostponedAtomWithRevisableExpectedType argument) {
        KotlinTypeMarker revisedExpectedType = argument.getRevisedExpectedType();
        if (revisedExpectedType != null) {
            if (!c.isFunctionOrKFunctionWithAnySuspendability(revisedExpectedType)) {
                revisedExpectedType = null;
            }
            if (revisedExpectedType != null) {
                ConeKotlinType coneKotlinType = (ConeKotlinType) revisedExpectedType;
                if (argument instanceof ConeResolvedCallableReferenceAtom) {
                    return false;
                }
                if (argument instanceof ConeLambdaWithTypeVariableAsExpectedTypeAtom) {
                    PostponedArgumentsAnalyzerKt.transformToResolvedLambda$default((ConeLambdaWithTypeVariableAsExpectedTypeAtom) argument, c.getBuilder(), resolutionContext, coneKotlinType, null, 8, null);
                    return true;
                }
                qu7.a("Unsupported postponed argument type of ", argument);
            }
        }
        return false;
    }

    private final boolean tryToCompleteWithPCLA(ConstraintSystemCompletionContext constraintSystemCompletionContext, ConstraintSystemCompletionMode constraintSystemCompletionMode, List<? extends ConePostponedResolvedAtom> list, PostponedAtomAnalyzer postponedAtomAnalyzer) {
        boolean z = false;
        if (!constraintSystemCompletionMode.getAllLambdasShouldBeAnalyzed()) {
            return false;
        }
        ArrayList<ConeResolvedLambdaAtom> arrayList = new ArrayList();
        for (Object obj : list) {
            if (obj instanceof ConeResolvedLambdaAtom) {
                arrayList.add(obj);
            }
        }
        if (arrayList.isEmpty()) {
            arrayList = null;
        }
        if (arrayList == null) {
            return false;
        }
        for (ConeResolvedLambdaAtom coneResolvedLambdaAtom : arrayList) {
            Collection<ConeKotlinType> inputTypes = coneResolvedLambdaAtom.getInputTypes();
            ArrayList arrayList2 = new ArrayList();
            Iterator<T> it = inputTypes.iterator();
            while (it.hasNext()) {
                kotlin.collections.CollectionsKt.addAll(arrayList2, constraintSystemCompletionContext.extractTypeVariables((ConeKotlinType) it.next()));
            }
            ArrayList arrayList3 = new ArrayList();
            for (Object obj2 : arrayList2) {
                if (!constraintSystemCompletionContext.getFixedTypeVariables().containsKey((TypeVariableTypeConstructorMarker) obj2)) {
                    arrayList3.add(obj2);
                }
            }
            if (!arrayList3.isEmpty()) {
                z = true;
                analyze(postponedAtomAnalyzer, coneResolvedLambdaAtom, true);
            }
        }
        return z;
    }

    public final void complete(ConstraintSystemCompletionContext c, ConstraintSystemCompletionMode completionMode, List<? extends ConeResolutionAtom> topLevelAtoms, ConeKotlinType candidateReturnType, ResolutionContext context, PostponedAtomAnalyzer analyzer) {
        c.getClass();
        completionMode.getClass();
        topLevelAtoms.getClass();
        candidateReturnType.getClass();
        context.getClass();
        analyzer.getClass();
        runCompletion(c, completionMode, topLevelAtoms, candidateReturnType, context, analyzer);
    }

    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001b\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0000¢\u0006\u0002\b\tJ\u001c\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0005H\u0002J \u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0005H\u0002J$\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u0017H\u0002¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/inference/ConstraintSystemCompleter$Companion;", Argument.Delimiters.none, "<init>", "()V", "getOrderedNotAnalyzedPostponedArguments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/ConePostponedResolvedAtom;", "candidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "getOrderedNotAnalyzedPostponedArguments$org_jetbrains_kotlin_resolve", "topLevelAtoms", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtom;", "findStatementOfFirstAtomWithVariable", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "typeVariable", "Lorg/jetbrains/kotlin/types/model/TypeVariableMarker;", "createCannotInferErrorType", "Lorg/jetbrains/kotlin/fir/types/ConeErrorType;", "typeParameterSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "message", Argument.Delimiters.none, "isUninferredParameter", Argument.Delimiters.none, "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static Unit a(TypeVariableMarker typeVariableMarker, Ref.ObjectRef objectRef, ConePostponedResolvedAtom conePostponedResolvedAtom) {
            conePostponedResolvedAtom.getClass();
            if (conePostponedResolvedAtom instanceof ConeResolvedLambdaAtom) {
                ConeResolvedLambdaAtom coneResolvedLambdaAtom = (ConeResolvedLambdaAtom) conePostponedResolvedAtom;
                if (Intrinsics.areEqual(coneResolvedLambdaAtom.getTypeVariableForLambdaReturnType(), typeVariableMarker)) {
                    findStatementOfFirstAtomWithVariable$findFirstStatementContainingVariable$suggestElement(objectRef, coneResolvedLambdaAtom.getAnonymousFunction());
                }
            }
            return Unit.INSTANCE;
        }

        public static Unit b(Map map, Candidate candidate) {
            candidate.getClass();
            if (map != null) {
                for (ConePostponedResolvedAtom conePostponedResolvedAtom : candidate.getPostponedAtoms()) {
                    final Function1 function1 = new Function1() { // from class: us2
                        public final Object invoke(Object obj) {
                            return ConstraintSystemCompleter.Companion.getOrderedNotAnalyzedPostponedArguments$lambda$1$0((ConePostponedResolvedAtom) obj);
                        }
                    };
                    map.computeIfAbsent(conePostponedResolvedAtom, new Function() { // from class: vs2
                        @Override // java.util.function.Function
                        public final Object apply(Object obj) {
                            return ConstraintSystemCompleter.Companion.getOrderedNotAnalyzedPostponedArguments$lambda$1$1(function1, obj);
                        }
                    });
                }
            }
            return Unit.INSTANCE;
        }

        public static Unit c(TypeVariableMarker typeVariableMarker, Ref.ObjectRef objectRef, Candidate candidate) {
            candidate.getClass();
            if (kotlin.collections.CollectionsKt.contains(candidate.getFreshVariables(), typeVariableMarker)) {
                findStatementOfFirstAtomWithVariable$findFirstStatementContainingVariable$suggestElement(objectRef, candidate.getCallInfo().getCallSite());
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final ConeErrorType createCannotInferErrorType(FirTypeParameterSymbol typeParameterSymbol, String message, boolean isUninferredParameter) {
            return new ConeErrorType(typeParameterSymbol == null ? new ConeCannotInferValueParameterType(null, message, false, 4, null) : new ConeCannotInferTypeParameterType(typeParameterSymbol, message), isUninferredParameter, null, null, null, null, null, 124, null);
        }

        public static /* synthetic */ ConeErrorType createCannotInferErrorType$default(Companion companion, FirTypeParameterSymbol firTypeParameterSymbol, String str, boolean z, int i, Object obj) {
            if ((i & 4) != 0) {
                z = false;
            }
            return companion.createCannotInferErrorType(firTypeParameterSymbol, str, z);
        }

        public static Unit e(ArrayList arrayList, Map map, ConePostponedResolvedAtom conePostponedResolvedAtom) {
            conePostponedResolvedAtom.getClass();
            CollectionsKt.addIfNotNull(arrayList, !conePostponedResolvedAtom.getAnalyzed() ? conePostponedResolvedAtom : null);
            if (map != null) {
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final FirStatement findStatementOfFirstAtomWithVariable(TypeVariableMarker typeVariable, List<? extends ConeResolutionAtom> topLevelAtoms) {
            Iterator<T> it = topLevelAtoms.iterator();
            while (it.hasNext()) {
                FirStatement firStatementFindStatementOfFirstAtomWithVariable$findFirstStatementContainingVariable = findStatementOfFirstAtomWithVariable$findFirstStatementContainingVariable((ConeResolutionAtom) it.next(), typeVariable);
                if (firStatementFindStatementOfFirstAtomWithVariable$findFirstStatementContainingVariable != null) {
                    return firStatementFindStatementOfFirstAtomWithVariable$findFirstStatementContainingVariable;
                }
            }
            return null;
        }

        private static final FirStatement findStatementOfFirstAtomWithVariable$findFirstStatementContainingVariable(ConeResolutionAtom coneResolutionAtom, final TypeVariableMarker typeVariableMarker) {
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            CandidateTraversalKt.processCandidatesAndPostponedAtoms(coneResolutionAtom, new Function1() { // from class: ss2
                public final Object invoke(Object obj) {
                    return ConstraintSystemCompleter.Companion.c(typeVariableMarker, objectRef, (Candidate) obj);
                }
            }, new Function1() { // from class: ts2
                public final Object invoke(Object obj) {
                    return ConstraintSystemCompleter.Companion.a(typeVariableMarker, objectRef, (ConePostponedResolvedAtom) obj);
                }
            });
            return (FirStatement) objectRef.element;
        }

        private static final void findStatementOfFirstAtomWithVariable$findFirstStatementContainingVariable$suggestElement(Ref.ObjectRef<FirStatement> objectRef, FirElement firElement) {
            if (objectRef.element == null && (firElement instanceof FirStatement)) {
                objectRef.element = firElement;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final List<ConePostponedResolvedAtom> getOrderedNotAnalyzedPostponedArguments(List<? extends ConeResolutionAtom> topLevelAtoms) {
            final ArrayList arrayList = new ArrayList();
            for (ConeResolutionAtom coneResolutionAtom : topLevelAtoms) {
                final LinkedHashMap linkedHashMap = AbstractTypeChecker.RUN_SLOW_ASSERTIONS ? new LinkedHashMap() : null;
                CandidateTraversalKt.processCandidatesAndPostponedAtoms(coneResolutionAtom, new Function1() { // from class: qs2
                    public final Object invoke(Object obj) {
                        return ConstraintSystemCompleter.Companion.b(linkedHashMap, (Candidate) obj);
                    }
                }, new Function1() { // from class: rs2
                    public final Object invoke(Object obj) {
                        return ConstraintSystemCompleter.Companion.e(arrayList, linkedHashMap, (ConePostponedResolvedAtom) obj);
                    }
                });
                if (linkedHashMap != null) {
                    Collection collectionValues = linkedHashMap.values();
                    if (!(collectionValues instanceof Collection) || !collectionValues.isEmpty()) {
                        Iterator it = collectionValues.iterator();
                        while (it.hasNext()) {
                            if (!((Boolean) it.next()).booleanValue()) {
                                k2d.a("Some postponed atoms were not collected.");
                                return null;
                            }
                        }
                    }
                }
            }
            return arrayList;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean getOrderedNotAnalyzedPostponedArguments$lambda$1$0(ConePostponedResolvedAtom conePostponedResolvedAtom) {
            conePostponedResolvedAtom.getClass();
            return Boolean.FALSE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Boolean getOrderedNotAnalyzedPostponedArguments$lambda$1$1(Function1 function1, Object obj) {
            return (Boolean) function1.invoke(obj);
        }

        public final List<ConePostponedResolvedAtom> getOrderedNotAnalyzedPostponedArguments$org_jetbrains_kotlin_resolve(Candidate candidate) {
            candidate.getClass();
            FirElement callSite = candidate.getCallInfo().getCallSite();
            callSite.getClass();
            return getOrderedNotAnalyzedPostponedArguments(kotlin.collections.CollectionsKt.listOf(new ConeAtomWithCandidate((FirExpression) callSite, candidate)));
        }

        private Companion() {
        }
    }

    private final void analyze(PostponedAtomAnalyzer postponedAtomAnalyzer, ConePostponedResolvedAtom conePostponedResolvedAtom, boolean z) {
        postponedAtomAnalyzer.analyzeInternal(conePostponedResolvedAtom, z, null);
    }
}
