package org.jetbrains.kotlin.fir.resolve.calls.overloads;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.kotlin.builtins.functions.FunctionTypeKind;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.K2JsArgumentConstants;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirNamedArgumentExpression;
import org.jetbrains.kotlin.fir.expressions.FirSpreadArgumentExpression;
import org.jetbrains.kotlin.fir.references.FirNamedReference;
import org.jetbrains.kotlin.fir.resolve.BodyResolveComponents;
import org.jetbrains.kotlin.fir.resolve.FirSamResolver;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.CallableReferenceAdaptation;
import org.jetbrains.kotlin.fir.resolve.calls.ConePostponedResolvedAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolvedCallableReferenceAtom;
import org.jetbrains.kotlin.fir.resolve.calls.TypeVariableReplacement;
import org.jetbrains.kotlin.fir.resolve.calls.TypeVariableTypeRemovingSubstitutorKt;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.FirNamedReferenceWithCandidate;
import org.jetbrains.kotlin.fir.resolve.calls.stages.ResolutionStagesKt;
import org.jetbrains.kotlin.fir.resolve.inference.FirInferenceLogger;
import org.jetbrains.kotlin.fir.resolve.inference.FirInferenceLoggerKt;
import org.jetbrains.kotlin.fir.resolve.inference.InferenceComponents;
import org.jetbrains.kotlin.fir.scopes.FirScope;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.MemberWithBaseScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirOverrideUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FunctionalTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.utils.exceptions.FirExceptionUtilsKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.resolve.calls.results.FlatSignature;
import org.jetbrains.kotlin.resolve.calls.results.FlatSignatureKt;
import org.jetbrains.kotlin.resolve.calls.results.SimpleConstraintSystem;
import org.jetbrains.kotlin.resolve.calls.results.SpecificityComparisonCallbacks;
import org.jetbrains.kotlin.resolve.calls.results.TypeSpecificityComparator;
import org.jetbrains.kotlin.resolve.calls.results.TypeWithConversion;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;
import org.jetbrains.kotlin.types.model.MarkerExtensionsKt;
import org.jetbrains.kotlin.types.model.TypeSystemContextKt;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000µ\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001.\u0018\u00002\u00020\u00012\u00020\u0002:\u0001PB\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u001c\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0016J$\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u001c\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0002J\u0014\u0010\u0017\u001a\u00020\u0014*\u00020\u00112\u0006\u0010\u0018\u001a\u00020\u0011H\u0002J\u001c\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0002J$\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u001a\u001a\u00020\u001bH\u0002JA\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u00102\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0012\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00140\u001e2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001b0 H\u0082\bJ\f\u0010!\u001a\u00020\u0014*\u00020\u0011H\u0002J*\u0010\"\u001a\u0004\u0018\u00010\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010#\u001a\u00020\u0014H\u0002J>\u0010$\u001a\u00020\u00142\u0010\u0010%\u001a\f\u0012\u0004\u0012\u00020\u00110&j\u0002`'2\u0010\u0010(\u001a\f\u0012\u0004\u0012\u00020\u00110&j\u0002`'2\u0006\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010#\u001a\u00020\u0014H\u0002J*\u0010)\u001a\u0010\u0012\u0004\u0012\u00020\u0011\u0018\u00010&j\u0004\u0018\u0001`'*\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00110&j\u0002`'0*H\u0002J$\u0010+\u001a\u00020\u00142\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00110&2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00110&H\u0002J4\u0010,\u001a\u00020\u00142\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00110&2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00110&2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010#\u001a\u00020\u0014H\u0002J\u0016\u00102\u001a\b\u0012\u0004\u0012\u00020\u00110&2\u0006\u00103\u001a\u00020\u0011H\u0002J\u001e\u00102\u001a\b\u0012\u0004\u0012\u00020\u00110&2\u0006\u00103\u001a\u00020\u00112\u0006\u00104\u001a\u000205H\u0002J\u001e\u00102\u001a\b\u0012\u0004\u0012\u00020\u00110&2\u0006\u00103\u001a\u00020\u00112\u0006\u00106\u001a\u000207H\u0002J\u001e\u00102\u001a\b\u0012\u0004\u0012\u00020\u00110&2\u0006\u00103\u001a\u00020\u00112\u0006\u00108\u001a\u000209H\u0002J\u0014\u0010:\u001a\u00020;*\u00020<2\u0006\u0010=\u001a\u00020>H\u0002J\u001e\u0010?\u001a\b\u0012\u0004\u0012\u00020@0*2\u0006\u00103\u001a\u00020\u00112\u0006\u0010A\u001a\u00020BH\u0002J$\u0010C\u001a\u00020@*\u00020<2\u0006\u0010=\u001a\u00020>2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u00103\u001a\u00020\u0011H\u0002J\u0014\u0010D\u001a\u00020;*\u00020;2\u0006\u0010E\u001a\u00020\u0011H\u0002J\u0016\u0010F\u001a\u0004\u0018\u00010;*\u00020<2\u0006\u00103\u001a\u00020\u0011H\u0002J\u001e\u00102\u001a\b\u0012\u0004\u0012\u00020\u00110&2\u0006\u00103\u001a\u00020\u00112\u0006\u0010G\u001a\u00020HH\u0002J\b\u0010I\u001a\u00020JH\u0002J\u001f\u0010K\u001a\u0004\u0018\u00010\u00142\u0006\u0010L\u001a\u00020M2\u0006\u0010N\u001a\u00020MH\u0002¢\u0006\u0002\u0010OR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0016\u0010-\u001a\u00020.X\u0082\u0004¢\u0006\n\n\u0002\u00101\u0012\u0004\b/\u00100¨\u0006Q"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/overloads/ConeOverloadConflictResolver;", "Lorg/jetbrains/kotlin/fir/resolve/calls/overloads/ConeCallConflictResolver;", "Lorg/jetbrains/kotlin/fir/SessionHolder;", "specificityComparator", "Lorg/jetbrains/kotlin/resolve/calls/results/TypeSpecificityComparator;", "inferenceComponents", "Lorg/jetbrains/kotlin/fir/resolve/inference/InferenceComponents;", "transformerComponents", "Lorg/jetbrains/kotlin/fir/resolve/BodyResolveComponents;", "<init>", "(Lorg/jetbrains/kotlin/resolve/calls/results/TypeSpecificityComparator;Lorg/jetbrains/kotlin/fir/resolve/inference/InferenceComponents;Lorg/jetbrains/kotlin/fir/resolve/BodyResolveComponents;)V", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "chooseMaximallySpecificCandidates", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "candidates", "discriminateGenerics", Argument.Delimiters.none, "filterOverrides", "candidateSet", "overrides", "other", "chooseCandidatesWithMostSpecificInvokeReceiver", "discriminationFlags", "Lorg/jetbrains/kotlin/fir/resolve/calls/overloads/ConeOverloadConflictResolver$DiscriminationFlags;", "filterCandidatesByDiscriminationFlag", "filter", "Lkotlin/Function1;", "newFlags", "Lkotlin/Function0;", "hasPostponedAtomWithAdaptation", "findMaximallySpecificCall", "useOriginalSamTypes", "isEquallyOrMoreSpecificCallWithArgumentMapping", "call1", "Lorg/jetbrains/kotlin/resolve/calls/results/FlatSignature;", "Lorg/jetbrains/kotlin/fir/resolve/calls/overloads/CandidateSignature;", "call2", "exactMaxWith", Argument.Delimiters.none, "checkExpectAndEquallyOrMoreSpecificShape", "compareCallsByUsedArguments", "SpecificityComparisonWithNumerics", "org/jetbrains/kotlin/fir/resolve/calls/overloads/ConeOverloadConflictResolver$SpecificityComparisonWithNumerics$1", "getSpecificityComparisonWithNumerics$annotations", "()V", "Lorg/jetbrains/kotlin/fir/resolve/calls/overloads/ConeOverloadConflictResolver$SpecificityComparisonWithNumerics$1;", "createFlatSignature", K2JsArgumentConstants.CALL, "variable", "Lorg/jetbrains/kotlin/fir/declarations/FirVariable;", "constructor", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "function", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "argumentType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "argument", "Lorg/jetbrains/kotlin/fir/resolve/calls/ConeResolutionAtom;", "computeSignatureTypes", "Lorg/jetbrains/kotlin/resolve/calls/results/TypeWithConversion;", "called", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "toTypeWithConversion", "prepareType", "candidate", "toFunctionTypeForSamOrNull", "klass", "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "createEmptyConstraintSystem", "Lorg/jetbrains/kotlin/resolve/calls/results/SimpleConstraintSystem;", "customSubtypingForNumerics", "subtype", "Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;", "supertype", "(Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;)Ljava/lang/Boolean;", "DiscriminationFlags", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeOverloadConflictResolver extends ConeCallConflictResolver implements SessionHolder {
    private final ConeOverloadConflictResolver$SpecificityComparisonWithNumerics$1 SpecificityComparisonWithNumerics;
    private final InferenceComponents inferenceComponents;
    private final TypeSpecificityComparator specificityComparator;
    private final BodyResolveComponents transformerComponents;

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u001b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003JO\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u001c\u001a\u00020\u00032\b\u0010\u001d\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001e\u001a\u00020\u001fHÖ\u0081\u0004J\n\u0010 \u001a\u00020!HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\rR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\rR\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\r¨\u0006\""}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/overloads/ConeOverloadConflictResolver$DiscriminationFlags;", Argument.Delimiters.none, "lowPrioritySAMs", Argument.Delimiters.none, "adaptationsInPostponedAtoms", "generics", "SAMs", "suspendConversions", "byUnwrappedSmartCastOrigin", "unitCoercionInLambdas", "<init>", "(ZZZZZZZ)V", "getLowPrioritySAMs", "()Z", "getAdaptationsInPostponedAtoms", "getGenerics", "getSAMs", "getSuspendConversions", "getByUnwrappedSmartCastOrigin", "getUnitCoercionInLambdas", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class DiscriminationFlags {
        private final boolean SAMs;
        private final boolean adaptationsInPostponedAtoms;
        private final boolean byUnwrappedSmartCastOrigin;
        private final boolean generics;
        private final boolean lowPrioritySAMs;
        private final boolean suspendConversions;
        private final boolean unitCoercionInLambdas;

        public DiscriminationFlags(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7) {
            this.lowPrioritySAMs = z;
            this.adaptationsInPostponedAtoms = z2;
            this.generics = z3;
            this.SAMs = z4;
            this.suspendConversions = z5;
            this.byUnwrappedSmartCastOrigin = z6;
            this.unitCoercionInLambdas = z7;
        }

        public static /* synthetic */ DiscriminationFlags copy$default(DiscriminationFlags discriminationFlags, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, int i, Object obj) {
            if ((i & 1) != 0) {
                z = discriminationFlags.lowPrioritySAMs;
            }
            if ((i & 2) != 0) {
                z2 = discriminationFlags.adaptationsInPostponedAtoms;
            }
            if ((i & 4) != 0) {
                z3 = discriminationFlags.generics;
            }
            if ((i & 8) != 0) {
                z4 = discriminationFlags.SAMs;
            }
            if ((i & 16) != 0) {
                z5 = discriminationFlags.suspendConversions;
            }
            if ((i & 32) != 0) {
                z6 = discriminationFlags.byUnwrappedSmartCastOrigin;
            }
            if ((i & 64) != 0) {
                z7 = discriminationFlags.unitCoercionInLambdas;
            }
            boolean z8 = z6;
            boolean z9 = z7;
            boolean z10 = z5;
            boolean z11 = z3;
            return discriminationFlags.copy(z, z2, z11, z4, z10, z8, z9);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final boolean getLowPrioritySAMs() {
            return this.lowPrioritySAMs;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getAdaptationsInPostponedAtoms() {
            return this.adaptationsInPostponedAtoms;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final boolean getGenerics() {
            return this.generics;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final boolean getSAMs() {
            return this.SAMs;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final boolean getSuspendConversions() {
            return this.suspendConversions;
        }

        /* JADX INFO: renamed from: component6, reason: from getter */
        public final boolean getByUnwrappedSmartCastOrigin() {
            return this.byUnwrappedSmartCastOrigin;
        }

        /* JADX INFO: renamed from: component7, reason: from getter */
        public final boolean getUnitCoercionInLambdas() {
            return this.unitCoercionInLambdas;
        }

        public final DiscriminationFlags copy(boolean lowPrioritySAMs, boolean adaptationsInPostponedAtoms, boolean generics, boolean SAMs, boolean suspendConversions, boolean byUnwrappedSmartCastOrigin, boolean unitCoercionInLambdas) {
            return new DiscriminationFlags(lowPrioritySAMs, adaptationsInPostponedAtoms, generics, SAMs, suspendConversions, byUnwrappedSmartCastOrigin, unitCoercionInLambdas);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DiscriminationFlags)) {
                return false;
            }
            DiscriminationFlags discriminationFlags = (DiscriminationFlags) other;
            return this.lowPrioritySAMs == discriminationFlags.lowPrioritySAMs && this.adaptationsInPostponedAtoms == discriminationFlags.adaptationsInPostponedAtoms && this.generics == discriminationFlags.generics && this.SAMs == discriminationFlags.SAMs && this.suspendConversions == discriminationFlags.suspendConversions && this.byUnwrappedSmartCastOrigin == discriminationFlags.byUnwrappedSmartCastOrigin && this.unitCoercionInLambdas == discriminationFlags.unitCoercionInLambdas;
        }

        public final boolean getAdaptationsInPostponedAtoms() {
            return this.adaptationsInPostponedAtoms;
        }

        public final boolean getByUnwrappedSmartCastOrigin() {
            return this.byUnwrappedSmartCastOrigin;
        }

        public final boolean getGenerics() {
            return this.generics;
        }

        public final boolean getLowPrioritySAMs() {
            return this.lowPrioritySAMs;
        }

        public final boolean getSAMs() {
            return this.SAMs;
        }

        public final boolean getSuspendConversions() {
            return this.suspendConversions;
        }

        public final boolean getUnitCoercionInLambdas() {
            return this.unitCoercionInLambdas;
        }

        public int hashCode() {
            return (((((((((((Boolean.hashCode(this.lowPrioritySAMs) * 31) + Boolean.hashCode(this.adaptationsInPostponedAtoms)) * 31) + Boolean.hashCode(this.generics)) * 31) + Boolean.hashCode(this.SAMs)) * 31) + Boolean.hashCode(this.suspendConversions)) * 31) + Boolean.hashCode(this.byUnwrappedSmartCastOrigin)) * 31) + Boolean.hashCode(this.unitCoercionInLambdas);
        }

        public String toString() {
            return "DiscriminationFlags(lowPrioritySAMs=" + this.lowPrioritySAMs + ", adaptationsInPostponedAtoms=" + this.adaptationsInPostponedAtoms + ", generics=" + this.generics + ", SAMs=" + this.SAMs + ", suspendConversions=" + this.suspendConversions + ", byUnwrappedSmartCastOrigin=" + this.byUnwrappedSmartCastOrigin + ", unitCoercionInLambdas=" + this.unitCoercionInLambdas + ')';
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.resolve.calls.overloads.ConeOverloadConflictResolver$createEmptyConstraintSystem$1, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function2<KotlinTypeMarker, KotlinTypeMarker, Boolean> {
        public AnonymousClass1(Object obj) {
            super(2, obj, ConeOverloadConflictResolver.class, "customSubtypingForNumerics", "customSubtypingForNumerics(Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;)Ljava/lang/Boolean;", 0);
        }

        public final Boolean invoke(KotlinTypeMarker kotlinTypeMarker, KotlinTypeMarker kotlinTypeMarker2) {
            kotlinTypeMarker.getClass();
            kotlinTypeMarker2.getClass();
            return ((ConeOverloadConflictResolver) ((CallableReference) this).receiver).customSubtypingForNumerics(kotlinTypeMarker, kotlinTypeMarker2);
        }
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [org.jetbrains.kotlin.fir.resolve.calls.overloads.ConeOverloadConflictResolver$SpecificityComparisonWithNumerics$1] */
    public ConeOverloadConflictResolver(TypeSpecificityComparator typeSpecificityComparator, InferenceComponents inferenceComponents, BodyResolveComponents bodyResolveComponents) {
        typeSpecificityComparator.getClass();
        inferenceComponents.getClass();
        bodyResolveComponents.getClass();
        this.specificityComparator = typeSpecificityComparator;
        this.inferenceComponents = inferenceComponents;
        this.transformerComponents = bodyResolveComponents;
        this.SpecificityComparisonWithNumerics = new SpecificityComparisonCallbacks() { // from class: org.jetbrains.kotlin.fir.resolve.calls.overloads.ConeOverloadConflictResolver$SpecificityComparisonWithNumerics$1
            private final boolean discriminateSuspend;
            private final boolean useCorrectSignedCheck;

            {
                this.useCorrectSignedCheck = FirLanguageSettingsComponentKt.getLanguageVersionSettings(this.this$0.inferenceComponents.getSession()).supportsFeature(LanguageFeature.CorrectSpecificityCheckForSignedAndUnsigned);
                this.discriminateSuspend = FirLanguageSettingsComponentKt.getLanguageVersionSettings(this.this$0.inferenceComponents.getSession()).supportsFeature(LanguageFeature.DiscriminateSuspendInOverloadResolution);
            }

            private final boolean isSignedIntegerType(ClassId classId) {
                if (this.useCorrectSignedCheck) {
                    return StandardClassIds.INSTANCE.getSignedIntegerTypes().contains(classId);
                }
                return !isUnsigned(classId);
            }

            private final boolean isUnsigned(ClassId classId) {
                return StandardClassIds.INSTANCE.getUnsignedTypes().contains(classId);
            }

            public boolean isNonSubtypeEquallyOrMoreSpecific(KotlinTypeMarker specific, KotlinTypeMarker general) {
                ClassId classId;
                specific.getClass();
                general.getClass();
                TypeSystemContextKt.requireOrDescribe(specific instanceof ConeKotlinType, specific);
                TypeSystemContextKt.requireOrDescribe(general instanceof ConeKotlinType, general);
                ClassId classId2 = ConeTypeUtilsKt.getClassId(ConeTypeUtilsKt.lowerBoundIfFlexible((ConeKotlinType) specific));
                if (classId2 == null || (classId = ConeTypeUtilsKt.getClassId(ConeTypeUtilsKt.upperBoundIfFlexible((ConeKotlinType) general))) == null) {
                    return false;
                }
                if (isSignedIntegerType(classId2) && isUnsigned(classId)) {
                    return true;
                }
                StandardClassIds standardClassIds = StandardClassIds.INSTANCE;
                if (Intrinsics.areEqual(classId2, standardClassIds.getInt())) {
                    return Intrinsics.areEqual(classId, standardClassIds.getLong()) || Intrinsics.areEqual(classId, standardClassIds.getShort()) || Intrinsics.areEqual(classId, standardClassIds.getByte());
                }
                if (Intrinsics.areEqual(classId2, standardClassIds.getShort()) && Intrinsics.areEqual(classId, standardClassIds.getByte())) {
                    return true;
                }
                if (Intrinsics.areEqual(classId2, standardClassIds.getUInt())) {
                    return Intrinsics.areEqual(classId, standardClassIds.getULong()) || Intrinsics.areEqual(classId, standardClassIds.getUShort()) || Intrinsics.areEqual(classId, standardClassIds.getUByte());
                }
                if (Intrinsics.areEqual(classId2, standardClassIds.getUShort()) && Intrinsics.areEqual(classId, standardClassIds.getUByte())) {
                    return true;
                }
                if (Intrinsics.areEqual(classId2, standardClassIds.getDouble()) && Intrinsics.areEqual(classId, standardClassIds.getFloat())) {
                    return true;
                }
                return this.discriminateSuspend && Intrinsics.areEqual(FunctionalTypeUtilsKt.functionTypeKind(classId2, this.this$0.inferenceComponents.getSession()), FunctionTypeKind.Function.INSTANCE) && Intrinsics.areEqual(FunctionalTypeUtilsKt.functionTypeKind(classId, this.this$0.inferenceComponents.getSession()), FunctionTypeKind.SuspendFunction.INSTANCE);
            }
        };
    }

    private final ConeKotlinType argumentType(FirValueParameter firValueParameter, ConeResolutionAtom coneResolutionAtom) {
        ConeKotlinType coneType = FirTypeUtilsKt.getConeType(firValueParameter.getReturnTypeRef());
        boolean z = coneResolutionAtom.getExpression() instanceof FirNamedArgumentExpression;
        boolean z2 = coneResolutionAtom.getExpression() instanceof FirSpreadArgumentExpression;
        if (!firValueParameter.getIsVararg() || z || z2) {
            return coneType;
        }
        ConeKotlinType coneKotlinTypeArrayElementType$default = FirTypeUtilsKt.arrayElementType$default(coneType, false, 1, null);
        coneKotlinTypeArrayElementType$default.getClass();
        return coneKotlinTypeArrayElementType$default;
    }

    private final boolean checkExpectAndEquallyOrMoreSpecificShape(FlatSignature<Candidate> call1, FlatSignature<Candidate> call2) {
        boolean hasVarargs = call1.getHasVarargs();
        boolean hasVarargs2 = call2.getHasVarargs();
        if (!hasVarargs || hasVarargs2) {
            return (!hasVarargs && hasVarargs2) || call1.getNumDefaults() <= call2.getNumDefaults();
        }
        return false;
    }

    private final Set<Candidate> chooseCandidatesWithMostSpecificInvokeReceiver(Set<Candidate> candidates) {
        Set<Candidate> set = candidates;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (Candidate candidate : set) {
            Candidate candidateForCommonInvokeReceiver = candidate.getCallInfo().getCandidateForCommonInvokeReceiver();
            if (candidateForCommonInvokeReceiver == null) {
                x04.a("If one candidate within a group is property+invoke, other should be the same, but ", candidate, " found");
                return null;
            }
            linkedHashSet.add(candidateForCommonInvokeReceiver);
        }
        Candidate candidate2 = (Candidate) CollectionsKt.singleOrNull(chooseMaximallySpecificCandidates((Set<Candidate>) linkedHashSet, false));
        if (candidate2 == null) {
            return candidates;
        }
        LinkedHashSet linkedHashSet2 = new LinkedHashSet();
        for (Object obj : set) {
            if (Intrinsics.areEqual(((Candidate) obj).getCallInfo().getCandidateForCommonInvokeReceiver(), candidate2)) {
                linkedHashSet2.add(obj);
            }
        }
        return linkedHashSet2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final Set<Candidate> chooseMaximallySpecificCandidates(Set<Candidate> candidates, DiscriminationFlags discriminationFlags) {
        ConeOverloadConflictResolver coneOverloadConflictResolver;
        Set<Candidate> set;
        Candidate candidateFindMaximallySpecificCall;
        ConeOverloadConflictResolver coneOverloadConflictResolver2;
        Set<Candidate> setChooseMaximallySpecificCandidates = null;
        if (discriminationFlags.getLowPrioritySAMs()) {
            Set linkedHashSet = new LinkedHashSet();
            for (Object obj : candidates) {
                if (!ResolutionStagesKt.shouldHaveLowPriorityDueToSAM((Candidate) obj, this.transformerComponents)) {
                    linkedHashSet.add(obj);
                }
            }
            int size = linkedHashSet.size();
            if (size != 1) {
                linkedHashSet = (size == 0 || size == candidates.size()) ? null : chooseMaximallySpecificCandidates((Set<Candidate>) linkedHashSet, DiscriminationFlags.copy$default(discriminationFlags, false, false, false, false, false, false, false, 126, null));
            }
            if (linkedHashSet != null) {
                return linkedHashSet;
            }
        }
        if (discriminationFlags.getAdaptationsInPostponedAtoms()) {
            Set linkedHashSet2 = new LinkedHashSet();
            for (Object obj2 : candidates) {
                if (!hasPostponedAtomWithAdaptation((Candidate) obj2)) {
                    linkedHashSet2.add(obj2);
                }
            }
            int size2 = linkedHashSet2.size();
            if (size2 != 1) {
                linkedHashSet2 = (size2 == 0 || size2 == candidates.size()) ? null : chooseMaximallySpecificCandidates((Set<Candidate>) linkedHashSet2, DiscriminationFlags.copy$default(discriminationFlags, false, false, false, false, false, false, false, 125, null));
            }
            if (linkedHashSet2 != null) {
                return linkedHashSet2;
            }
        }
        if (discriminationFlags.getUnitCoercionInLambdas()) {
            Set linkedHashSet3 = new LinkedHashSet();
            for (Object obj3 : candidates) {
                if (!((Candidate) obj3).getUsesCoercionToUnitInLambda()) {
                    linkedHashSet3.add(obj3);
                }
            }
            int size3 = linkedHashSet3.size();
            if (size3 != 1) {
                linkedHashSet3 = (size3 == 0 || size3 == candidates.size()) ? null : chooseMaximallySpecificCandidates((Set<Candidate>) linkedHashSet3, DiscriminationFlags.copy$default(discriminationFlags, false, false, false, false, false, false, false, 63, null));
            }
            if (linkedHashSet3 != null) {
                return linkedHashSet3;
            }
        }
        Candidate candidateFindMaximallySpecificCall$default = findMaximallySpecificCall$default(this, candidates, false, false, 4, null);
        if (candidateFindMaximallySpecificCall$default != null) {
            return SetsKt.setOf(candidateFindMaximallySpecificCall$default);
        }
        if (discriminationFlags.getGenerics()) {
            coneOverloadConflictResolver2 = this;
            set = candidates;
            Candidate candidateFindMaximallySpecificCall$default2 = findMaximallySpecificCall$default(coneOverloadConflictResolver2, set, true, false, 4, null);
            if (candidateFindMaximallySpecificCall$default2 != null) {
                coneOverloadConflictResolver = coneOverloadConflictResolver2;
                return SetsKt.setOf(candidateFindMaximallySpecificCall$default2);
            }
        } else {
            coneOverloadConflictResolver = this;
            set = candidates;
        }
        coneOverloadConflictResolver = coneOverloadConflictResolver2;
        if (discriminationFlags.getSAMs()) {
            Set linkedHashSet4 = new LinkedHashSet();
            for (Object obj4 : set) {
                if (!((Candidate) obj4).getUsesSamConversionOrSamConstructor()) {
                    linkedHashSet4.add(obj4);
                }
            }
            int size4 = linkedHashSet4.size();
            if (size4 != 1) {
                linkedHashSet4 = (size4 == 0 || size4 == set.size()) ? null : coneOverloadConflictResolver.chooseMaximallySpecificCandidates((Set<Candidate>) linkedHashSet4, DiscriminationFlags.copy$default(discriminationFlags, false, false, false, false, false, false, false, 119, null));
            }
            if (linkedHashSet4 != null) {
                return linkedHashSet4;
            }
        }
        if (discriminationFlags.getSuspendConversions()) {
            Set linkedHashSet5 = new LinkedHashSet();
            for (Object obj5 : set) {
                if (!((Candidate) obj5).getUsesFunctionKindConversion()) {
                    linkedHashSet5.add(obj5);
                }
            }
            int size5 = linkedHashSet5.size();
            if (size5 != 1) {
                linkedHashSet5 = (size5 == 0 || size5 == set.size()) ? null : coneOverloadConflictResolver.chooseMaximallySpecificCandidates((Set<Candidate>) linkedHashSet5, DiscriminationFlags.copy$default(discriminationFlags, false, false, false, false, false, false, false, 111, null));
            }
            if (linkedHashSet5 != null) {
                return linkedHashSet5;
            }
        }
        if (discriminationFlags.getByUnwrappedSmartCastOrigin()) {
            LinkedHashSet linkedHashSet6 = new LinkedHashSet();
            for (Object obj6 : set) {
                if (!((Candidate) obj6).getIsFromOriginalTypeInPresenceOfSmartCast()) {
                    linkedHashSet6.add(obj6);
                }
            }
            int size6 = linkedHashSet6.size();
            if (size6 == 1) {
                setChooseMaximallySpecificCandidates = linkedHashSet6;
            } else if (size6 != 0 && size6 != set.size()) {
                setChooseMaximallySpecificCandidates = coneOverloadConflictResolver.chooseMaximallySpecificCandidates(linkedHashSet6, DiscriminationFlags.copy$default(discriminationFlags, false, false, false, false, false, false, false, 95, null));
            }
            if (setChooseMaximallySpecificCandidates != null) {
                return setChooseMaximallySpecificCandidates;
            }
        }
        LinkedHashSet linkedHashSet7 = new LinkedHashSet();
        for (Object obj7 : set) {
            if (((Candidate) obj7).getUsesSamConversionOrSamConstructor()) {
                linkedHashSet7.add(obj7);
            }
        }
        return (linkedHashSet7.isEmpty() || (candidateFindMaximallySpecificCall = coneOverloadConflictResolver.findMaximallySpecificCall(set, false, true)) == null) ? set : SetsKt.setOf(candidateFindMaximallySpecificCall);
    }

    private final boolean compareCallsByUsedArguments(FlatSignature<Candidate> call1, FlatSignature<Candidate> call2, boolean discriminateGenerics, boolean useOriginalSamTypes) {
        if (discriminateGenerics) {
            boolean zIsGeneric = call1.isGeneric();
            boolean zIsGeneric2 = call2.isGeneric();
            if (!zIsGeneric && zIsGeneric2) {
                return true;
            }
            if (zIsGeneric) {
                return false;
            }
        }
        SimpleConstraintSystem simpleConstraintSystemCreateEmptyConstraintSystem = createEmptyConstraintSystem();
        FirInferenceLogger inferenceLogger = FirInferenceLoggerKt.getInferenceLogger(this.inferenceComponents.getSession());
        if (inferenceLogger != null) {
            inferenceLogger.logStage("Some compareCallsByUsedArguments() call", simpleConstraintSystemCreateEmptyConstraintSystem.getConstraintSystemMarker());
        }
        return FlatSignatureKt.isSignatureEquallyOrMoreSpecific(simpleConstraintSystemCreateEmptyConstraintSystem, call1, call2, this.SpecificityComparisonWithNumerics, this.specificityComparator, useOriginalSamTypes);
    }

    private final List<TypeWithConversion> computeSignatureTypes(Candidate call, FirCallableDeclaration called) {
        FirTypeRef typeRef;
        ConeKotlinType coneType;
        ConeKotlinType coneKotlinTypePrepareType;
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        List list = listCreateListBuilder;
        FirReceiverParameter receiverParameter = called.getReceiverParameter();
        org.jetbrains.kotlin.utils.CollectionsKt.addIfNotNull(list, (receiverParameter == null || (typeRef = receiverParameter.getTypeRef()) == null || (coneType = FirTypeUtilsKt.getConeType(typeRef)) == null || (coneKotlinTypePrepareType = prepareType(coneType, call)) == null) ? null : new TypeWithConversion(coneKotlinTypePrepareType, (KotlinTypeMarker) null, 2, (DefaultConstructorMarker) null));
        ConeKotlinType resultingTypeForCallableReference = call.getResultingTypeForCallableReference();
        if (resultingTypeForCallableReference != null) {
            for (ConeTypeProjection coneTypeProjection : ArraysKt.dropLast(resultingTypeForCallableReference.getTypeArguments(), 1)) {
                coneTypeProjection.getClass();
                list.add(new TypeWithConversion(TypeVariableTypeRemovingSubstitutorKt.removeTypeVariableTypes$default(prepareType((ConeKotlinType) coneTypeProjection, call), TypeComponentsKt.getTypeContext(getSession()), TypeVariableReplacement.TypeParameter, null, 4, null), (KotlinTypeMarker) null, 2, (DefaultConstructorMarker) null));
            }
        } else if (call.getArgumentMappingInitialized()) {
            for (Map.Entry<ConeResolutionAtom, FirValueParameter> entry : call.getArgumentMapping().entrySet()) {
                TypeWithConversion typeWithConversion = toTypeWithConversion(entry.getValue(), entry.getKey(), getSession(), call);
                if (typeWithConversion != null) {
                    list.add(typeWithConversion);
                }
            }
        }
        return CollectionsKt.build(listCreateListBuilder);
    }

    private final SimpleConstraintSystem createEmptyConstraintSystem() {
        return new ConeSimpleConstraintSystemImpl(this.inferenceComponents.createConstraintSystem(LanguageVersionUtilsKt.isEnabled(this, LanguageFeature.EagerLambdaAnalysis) ? new AnonymousClass1(this) : null), this.inferenceComponents.getSession());
    }

    private final FlatSignature<Candidate> createFlatSignature(Candidate call, FirNamedFunction function) {
        boolean z;
        List<FirTypeParameter> typeParameters = function.getTypeParameters();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(typeParameters, 10));
        Iterator<T> it = typeParameters.iterator();
        while (it.hasNext()) {
            arrayList.add(((FirTypeParameter) it.next()).getSymbol().getLookupTag());
        }
        List<TypeWithConversion> listComputeSignatureTypes = computeSignatureTypes(call, function);
        boolean z2 = function.getReceiverParameter() != null;
        List<FirValueParameter> valueParameters = function.getValueParameters();
        if ((valueParameters instanceof Collection) && valueParameters.isEmpty()) {
            z = false;
        } else {
            Iterator<T> it2 = valueParameters.iterator();
            while (it2.hasNext()) {
                if (((FirValueParameter) it2.next()).getIsVararg()) {
                    z = true;
                }
            }
            z = false;
        }
        return new FlatSignature<>(call, arrayList, z2, 0, z, call.getNumDefaults(), function.getStatus().isExpect(), false, listComputeSignatureTypes);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Boolean customSubtypingForNumerics(KotlinTypeMarker subtype, KotlinTypeMarker supertype) {
        ConeKotlinType coneKotlinType;
        ClassId classId;
        TypeSystemContextKt.requireOrDescribe(subtype instanceof ConeKotlinType, subtype);
        TypeSystemContextKt.requireOrDescribe(supertype instanceof ConeKotlinType, supertype);
        ConeKotlinType coneKotlinType2 = (ConeKotlinType) subtype;
        ClassId classId2 = ConeTypeUtilsKt.getClassId(coneKotlinType2);
        if (classId2 == null || (classId = ConeTypeUtilsKt.getClassId((coneKotlinType = (ConeKotlinType) supertype))) == null) {
            return null;
        }
        if (ConeTypeUtilsKt.isMarkedNullable(ConeTypeUtilsKt.upperBoundIfFlexible(coneKotlinType2)) && !ConeTypeUtilsKt.isMarkedNullable(ConeTypeUtilsKt.upperBoundIfFlexible(coneKotlinType))) {
            return null;
        }
        StandardClassIds standardClassIds = StandardClassIds.INSTANCE;
        if (Intrinsics.areEqual(classId2, standardClassIds.getInt()) && Intrinsics.areEqual(classId, standardClassIds.getLong())) {
            return Boolean.TRUE;
        }
        if (Intrinsics.areEqual(classId2, standardClassIds.getUInt()) && Intrinsics.areEqual(classId, standardClassIds.getULong())) {
            return Boolean.TRUE;
        }
        return null;
    }

    private final FlatSignature<Candidate> exactMaxWith(List<FlatSignature<Candidate>> list) {
        FlatSignature<Candidate> flatSignature = null;
        for (FlatSignature<Candidate> flatSignature2 : list) {
            if (flatSignature == null || checkExpectAndEquallyOrMoreSpecificShape(flatSignature2, flatSignature)) {
                flatSignature = flatSignature2;
            }
        }
        if (flatSignature == null) {
            return null;
        }
        List<FlatSignature<Candidate>> list2 = list;
        if ((list2 instanceof Collection) && list2.isEmpty()) {
            return flatSignature;
        }
        for (FlatSignature<Candidate> flatSignature3 : list2) {
            if (!Intrinsics.areEqual(flatSignature3, flatSignature) && checkExpectAndEquallyOrMoreSpecificShape(flatSignature3, flatSignature)) {
                return null;
            }
        }
        return flatSignature;
    }

    private final Set<Candidate> filterOverrides(Set<Candidate> candidateSet) {
        if (candidateSet.size() <= 1) {
            return candidateSet;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (Candidate candidate : candidateSet) {
            Iterator it = linkedHashSet.iterator();
            while (true) {
                if (!it.hasNext()) {
                    linkedHashSet.add(candidate);
                    break;
                }
                Candidate candidate2 = (Candidate) it.next();
                if (!overrides(candidate, candidate2)) {
                    if (overrides(candidate2, candidate)) {
                        break;
                    }
                } else {
                    it.remove();
                }
            }
        }
        if (!linkedHashSet.isEmpty()) {
            return linkedHashSet;
        }
        dt1.a("All candidates filtered out from ", candidateSet);
        return null;
    }

    private final Candidate findMaximallySpecificCall(Set<Candidate> candidates, boolean discriminateGenerics, boolean useOriginalSamTypes) {
        if (candidates.size() <= 1) {
            return (Candidate) CollectionsKt.singleOrNull(candidates);
        }
        Set<Candidate> set = candidates;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(set, 10));
        Iterator<T> it = set.iterator();
        while (it.hasNext()) {
            arrayList.add(createFlatSignature((Candidate) it.next()));
        }
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : arrayList) {
            FlatSignature<Candidate> flatSignature = (FlatSignature) obj;
            if (!arrayList.isEmpty()) {
                Iterator it2 = arrayList.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        FlatSignature<Candidate> flatSignature2 = (FlatSignature) it2.next();
                        if (flatSignature != flatSignature2 && !isEquallyOrMoreSpecificCallWithArgumentMapping(flatSignature, flatSignature2, discriminateGenerics, useOriginalSamTypes)) {
                            break;
                        }
                    }
                }
            }
            arrayList2.add(obj);
        }
        FlatSignature<Candidate> flatSignatureExactMaxWith = exactMaxWith(arrayList2);
        if (flatSignatureExactMaxWith != null) {
            return (Candidate) flatSignatureExactMaxWith.getOrigin();
        }
        return null;
    }

    public static /* synthetic */ Candidate findMaximallySpecificCall$default(ConeOverloadConflictResolver coneOverloadConflictResolver, Set set, boolean z, boolean z2, int i, Object obj) {
        if ((i & 4) != 0) {
            z2 = false;
        }
        return coneOverloadConflictResolver.findMaximallySpecificCall(set, z, z2);
    }

    private final boolean hasPostponedAtomWithAdaptation(Candidate candidate) {
        Candidate candidate2;
        List<ConePostponedResolvedAtom> postponedAtoms = candidate.getPostponedAtoms();
        if ((postponedAtoms instanceof Collection) && postponedAtoms.isEmpty()) {
            return false;
        }
        for (ConePostponedResolvedAtom conePostponedResolvedAtom : postponedAtoms) {
            if (conePostponedResolvedAtom instanceof ConeResolvedCallableReferenceAtom) {
                FirNamedReference resultingReference = ((ConeResolvedCallableReferenceAtom) conePostponedResolvedAtom).getResultingReference();
                CallableReferenceAdaptation callableReferenceAdaptation = null;
                FirNamedReferenceWithCandidate firNamedReferenceWithCandidate = resultingReference instanceof FirNamedReferenceWithCandidate ? (FirNamedReferenceWithCandidate) resultingReference : null;
                if (firNamedReferenceWithCandidate != null && (candidate2 = firNamedReferenceWithCandidate.getCandidate()) != null) {
                    callableReferenceAdaptation = candidate2.getCallableReferenceAdaptation();
                }
                if (callableReferenceAdaptation != null) {
                    return true;
                }
            }
        }
        return false;
    }

    private final boolean isEquallyOrMoreSpecificCallWithArgumentMapping(FlatSignature<Candidate> call1, FlatSignature<Candidate> call2, boolean discriminateGenerics, boolean useOriginalSamTypes) {
        return compareCallsByUsedArguments(call1, call2, discriminateGenerics, useOriginalSamTypes);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean overrides(Candidate candidate, Candidate candidate2) {
        Function3 function3;
        FirBasedSymbol<?> symbol = candidate.getSymbol();
        if (!(symbol instanceof FirCallableSymbol) || !(candidate2.getSymbol() instanceof FirCallableSymbol)) {
            return false;
        }
        FirBasedSymbol<?> symbol2 = candidate2.getSymbol();
        symbol2.getClass();
        FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) ((FirCallableSymbol) symbol2).getFir();
        while (true) {
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
            if (originalForSubstitutionOverrideAttr == null) {
                break;
            }
            firCallableDeclaration = originalForSubstitutionOverrideAttr;
        }
        FirCallableSymbol<FirCallableDeclaration> symbol3 = firCallableDeclaration.getSymbol();
        if (symbol3 == null) {
            x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol<*>");
            return false;
        }
        FirCallableSymbol firCallableSymbol = (FirCallableSymbol) symbol;
        FirCallableDeclaration firCallableDeclaration2 = (FirCallableDeclaration) firCallableSymbol.getFir();
        while (true) {
            FirCallableDeclaration originalForSubstitutionOverrideAttr2 = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration2) || (firCallableDeclaration2.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration2) : null;
            if (originalForSubstitutionOverrideAttr2 == null) {
                break;
            }
            firCallableDeclaration2 = originalForSubstitutionOverrideAttr2;
        }
        FirCallableSymbol<FirCallableDeclaration> symbol4 = firCallableDeclaration2.getSymbol();
        if (symbol4 == null) {
            x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol<*>");
            return false;
        }
        if (Intrinsics.areEqual(symbol4, symbol3)) {
            return true;
        }
        FirScope originScope = candidate.getOriginScope();
        FirTypeScope firTypeScope = originScope instanceof FirTypeScope ? (FirTypeScope) originScope : null;
        if (firTypeScope == null) {
            return false;
        }
        if (firCallableSymbol instanceof FirNamedFunctionSymbol) {
            ConeOverloadConflictResolver$overrides$overriddenProducer$1 coneOverloadConflictResolver$overrides$overriddenProducer$1 = ConeOverloadConflictResolver$overrides$overriddenProducer$1.INSTANCE;
            coneOverloadConflictResolver$overrides$overriddenProducer$1.getClass();
            function3 = (Function3) TypeIntrinsics.beforeCheckcastToFunctionOfArity(coneOverloadConflictResolver$overrides$overriddenProducer$1, 3);
        } else {
            if (!(firCallableSymbol instanceof FirPropertySymbol)) {
                return false;
            }
            ConeOverloadConflictResolver$overrides$overriddenProducer$2 coneOverloadConflictResolver$overrides$overriddenProducer$2 = ConeOverloadConflictResolver$overrides$overriddenProducer$2.INSTANCE;
            coneOverloadConflictResolver$overrides$overriddenProducer$2.getClass();
            function3 = (Function3) TypeIntrinsics.beforeCheckcastToFunctionOfArity(coneOverloadConflictResolver$overrides$overriddenProducer$2, 3);
        }
        return FirOverrideUtilsKt.overrides(new MemberWithBaseScope(firCallableSymbol, firTypeScope), symbol3, function3);
    }

    private final ConeKotlinType prepareType(ConeKotlinType coneKotlinType, Candidate candidate) {
        ConeKotlinType coneKotlinTypeFullyExpandedType = TypeExpansionUtilsKt.fullyExpandedType(this, coneKotlinType);
        return !candidate.getSystem().getUsesOuterCs() ? coneKotlinTypeFullyExpandedType : MarkerExtensionsKt.safeSubstitute(candidate.getSystem().buildNotFixedVariablesToStubTypesSubstitutor(), TypeComponentsKt.getTypeContext(getSession()), coneKotlinTypeFullyExpandedType);
    }

    private final ConeKotlinType toFunctionTypeForSamOrNull(FirValueParameter firValueParameter, Candidate candidate) {
        FirSamResolver.SamConversionInfo samConversionInfo;
        HashMap<FirExpression, FirSamResolver.SamConversionInfo> samConversionInfosOfArguments = candidate.getSamConversionInfosOfArguments();
        if (samConversionInfosOfArguments == null) {
            return null;
        }
        Set<Map.Entry<ConeResolutionAtom, FirValueParameter>> setEntrySet = candidate.getArgumentMapping().entrySet();
        setEntrySet.getClass();
        Iterator<T> it = setEntrySet.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            ConeKotlinType functionalType = (!Intrinsics.areEqual(entry.getValue(), firValueParameter) || (samConversionInfo = samConversionInfosOfArguments.get(FirExpressionUtilKt.unwrapArgument(((ConeResolutionAtom) entry.getKey()).getExpression()))) == null) ? null : samConversionInfo.getFunctionalType();
            if (functionalType != null) {
                return functionalType;
            }
        }
        return null;
    }

    private final TypeWithConversion toTypeWithConversion(FirValueParameter firValueParameter, ConeResolutionAtom coneResolutionAtom, FirSession firSession, Candidate candidate) {
        ConeKotlinType coneKotlinTypePrepareType = prepareType(argumentType(firValueParameter, coneResolutionAtom), candidate);
        ConeKotlinType functionTypeForSamOrNull = toFunctionTypeForSamOrNull(firValueParameter, candidate);
        return functionTypeForSamOrNull == null ? new TypeWithConversion(coneKotlinTypePrepareType, (KotlinTypeMarker) null, 2, (DefaultConstructorMarker) null) : new TypeWithConversion(functionTypeForSamOrNull, coneKotlinTypePrepareType);
    }

    @Override // org.jetbrains.kotlin.fir.SessionHolder
    public FirSession getSession() {
        return this.transformerComponents.getSession();
    }

    private final FlatSignature<Candidate> createFlatSignature(Candidate call, FirVariable variable) {
        List listEmptyList;
        List<FirTypeParameter> typeParameters;
        FirProperty firProperty = variable instanceof FirProperty ? (FirProperty) variable : null;
        if (firProperty == null || (typeParameters = firProperty.getTypeParameters()) == null) {
            listEmptyList = null;
        } else {
            List<FirTypeParameter> list = typeParameters;
            listEmptyList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                listEmptyList.add(((FirTypeParameter) it.next()).getSymbol().getLookupTag());
            }
        }
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        List<TypeWithConversion> listComputeSignatureTypes = computeSignatureTypes(call, variable);
        boolean z = variable.getReceiverParameter() != null;
        FirProperty firProperty2 = variable instanceof FirProperty ? (FirProperty) variable : null;
        return new FlatSignature<>(call, listEmptyList, z, 0, false, 0, firProperty2 != null && firProperty2.getStatus().isExpect(), false, listComputeSignatureTypes);
    }

    private final FlatSignature<Candidate> createFlatSignature(Candidate call, FirConstructor constructor) {
        List<FirTypeParameterRef> typeParameters = constructor.getTypeParameters();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(typeParameters, 10));
        Iterator<T> it = typeParameters.iterator();
        while (it.hasNext()) {
            arrayList.add(((FirTypeParameterRef) it.next()).getSymbol().getLookupTag());
        }
        List<TypeWithConversion> listComputeSignatureTypes = computeSignatureTypes(call, constructor);
        List<FirValueParameter> valueParameters = constructor.getValueParameters();
        boolean z = false;
        if (!(valueParameters instanceof Collection) || !valueParameters.isEmpty()) {
            Iterator<T> it2 = valueParameters.iterator();
            while (it2.hasNext()) {
                if (((FirValueParameter) it2.next()).getIsVararg()) {
                    z = true;
                    break;
                }
            }
        }
        return new FlatSignature<>(call, arrayList, false, 0, z, call.getNumDefaults(), constructor.getStatus().isExpect(), false, listComputeSignatureTypes);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final FlatSignature<Candidate> createFlatSignature(Candidate call) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirDeclaration fir = call.getSymbol().getFir();
        if (fir instanceof FirNamedFunction) {
            return createFlatSignature(call, (FirNamedFunction) fir);
        }
        if (fir instanceof FirConstructor) {
            return createFlatSignature(call, (FirConstructor) fir);
        }
        if (fir instanceof FirVariable) {
            return createFlatSignature(call, (FirVariable) fir);
        }
        if (!(fir instanceof FirClass) && !(fir instanceof FirTypeAlias)) {
            KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Not supported: " + fir.getClass(), (Throwable) null);
            ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
            FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "declaration", fir);
            kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
            throw kotlinIllegalArgumentExceptionWithAttachments;
        }
        return createFlatSignature(call, (FirClassLikeDeclaration) fir);
    }

    private final FlatSignature<Candidate> createFlatSignature(Candidate call, FirClassLikeDeclaration klass) {
        List listEmptyList;
        List<FirTypeParameterRef> typeParameters;
        FirClassLikeDeclaration firClassLikeDeclaration = klass != null ? klass : null;
        if (firClassLikeDeclaration == null || (typeParameters = firClassLikeDeclaration.getTypeParameters()) == null) {
            listEmptyList = null;
        } else {
            List<FirTypeParameterRef> list = typeParameters;
            listEmptyList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                listEmptyList.add(((FirTypeParameterRef) it.next()).getSymbol().getLookupTag());
            }
        }
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        List list2 = listEmptyList;
        List listEmptyList2 = CollectionsKt.emptyList();
        FirRegularClass firRegularClass = klass instanceof FirRegularClass ? (FirRegularClass) klass : null;
        return new FlatSignature<>(call, list2, listEmptyList2, false, 0, false, 0, firRegularClass != null && firRegularClass.getStatus().isExpect(), false);
    }

    private final Set<Candidate> chooseMaximallySpecificCandidates(Set<Candidate> candidates, boolean discriminateGenerics) {
        if (candidates.size() == 1) {
            return candidates;
        }
        if (((Candidate) CollectionsKt.first(candidates)).getCallInfo().getCandidateForCommonInvokeReceiver() != null) {
            candidates = chooseCandidatesWithMostSpecificInvokeReceiver(candidates);
        }
        Set<Candidate> setFilterOverrides = filterOverrides(candidates);
        boolean zDisableCompatibilityModeForNewInference = LanguageVersionUtilsKt.disableCompatibilityModeForNewInference(this.transformerComponents);
        LanguageFeature languageFeature = LanguageFeature.EagerLambdaAnalysis;
        DiscriminationFlags discriminationFlags = new DiscriminationFlags(zDisableCompatibilityModeForNewInference, zDisableCompatibilityModeForNewInference, discriminateGenerics, true, true, true, LanguageVersionUtilsKt.isEnabled(this, languageFeature));
        if (LanguageVersionUtilsKt.isDisabled(this, languageFeature)) {
            return chooseMaximallySpecificCandidates(setFilterOverrides, discriminationFlags);
        }
        while (true) {
            Set<Candidate> setRunEagerLambdaAnalysisAndFilterOutInapplicableCandidates = EagerLambdaResolutionKt.runEagerLambdaAnalysisAndFilterOutInapplicableCandidates(setFilterOverrides, this.transformerComponents);
            if (setRunEagerLambdaAnalysisAndFilterOutInapplicableCandidates == null) {
                setRunEagerLambdaAnalysisAndFilterOutInapplicableCandidates = setFilterOverrides;
            }
            if (setRunEagerLambdaAnalysisAndFilterOutInapplicableCandidates.size() == 1) {
                return setRunEagerLambdaAnalysisAndFilterOutInapplicableCandidates;
            }
            Set<Candidate> setChooseMaximallySpecificCandidates = chooseMaximallySpecificCandidates(setRunEagerLambdaAnalysisAndFilterOutInapplicableCandidates, discriminationFlags);
            if (setChooseMaximallySpecificCandidates.size() <= 1 || setFilterOverrides.size() == setChooseMaximallySpecificCandidates.size()) {
                return setChooseMaximallySpecificCandidates;
            }
            setFilterOverrides = setChooseMaximallySpecificCandidates;
        }
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.overloads.ConeCallConflictResolver
    public Set<Candidate> chooseMaximallySpecificCandidates(Set<Candidate> candidates) {
        candidates.getClass();
        return chooseMaximallySpecificCandidates(candidates, !(((Candidate) CollectionsKt.first(candidates)).getCallInfo().getCallSite() instanceof FirCallableReferenceAccess));
    }
}
