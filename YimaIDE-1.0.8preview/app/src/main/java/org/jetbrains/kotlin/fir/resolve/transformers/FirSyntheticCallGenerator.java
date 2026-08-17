package org.jetbrains.kotlin.fir.resolve.transformers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirModuleDataKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.caches.FirCache;
import org.jetbrains.kotlin.fir.caches.FirCachesFactoryKt;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.builder.FirNamedFunctionBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirTypeParameterBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirValueParameterBuilder;
import org.jetbrains.kotlin.fir.declarations.impl.FirResolvedDeclarationStatusImpl;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationBuildingUtilsKt;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.FirDiagnosticHolder;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression;
import org.jetbrains.kotlin.fir.expressions.FirArgumentList;
import org.jetbrains.kotlin.fir.expressions.FirArgumentUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess;
import org.jetbrains.kotlin.fir.expressions.FirCatch;
import org.jetbrains.kotlin.fir.expressions.FirCheckNotNullCall;
import org.jetbrains.kotlin.fir.expressions.FirCollectionLiteral;
import org.jetbrains.kotlin.fir.expressions.FirElvisExpression;
import org.jetbrains.kotlin.fir.expressions.FirEqualityOperatorCall;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirResolvable;
import org.jetbrains.kotlin.fir.expressions.FirTryExpression;
import org.jetbrains.kotlin.fir.expressions.FirWhenBranch;
import org.jetbrains.kotlin.fir.expressions.FirWhenExpression;
import org.jetbrains.kotlin.fir.expressions.builder.FirArgumentListBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirFunctionCallBuilder;
import org.jetbrains.kotlin.fir.references.FirErrorNamedReference;
import org.jetbrains.kotlin.fir.references.FirNamedReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.references.FirResolvedCallableReference;
import org.jetbrains.kotlin.fir.references.FirResolvedErrorReference;
import org.jetbrains.kotlin.fir.references.builder.FirErrorNamedReferenceBuilder;
import org.jetbrains.kotlin.fir.references.builder.FirResolvedErrorReferenceBuilder;
import org.jetbrains.kotlin.fir.references.impl.FirSimpleNamedReference;
import org.jetbrains.kotlin.fir.resolve.BodyResolveComponents;
import org.jetbrains.kotlin.fir.resolve.ResolutionMode;
import org.jetbrains.kotlin.fir.resolve.ResolveUtilsKt;
import org.jetbrains.kotlin.fir.resolve.StdlibFactoryFunctionsUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.AbstractCallCandidate;
import org.jetbrains.kotlin.fir.resolve.calls.ArgumentTypeMismatch;
import org.jetbrains.kotlin.fir.resolve.calls.FirSyntheticFunctionSymbol;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionContext;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionDiagnostic;
import org.jetbrains.kotlin.fir.resolve.calls.TypeVariableReplacement;
import org.jetbrains.kotlin.fir.resolve.calls.TypeVariableTypeRemovingSubstitutorKt;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CallInfo;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CallKind;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CandidateFactory;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.ErrorCandidateUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.FirErrorReferenceWithCandidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.FirNamedReferenceWithCandidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.ImplicitInvokeMode;
import org.jetbrains.kotlin.fir.resolve.calls.stages.ResolutionStageRunner;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeAmbiguityError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeInapplicableCandidateError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeUnresolvedNameError;
import org.jetbrains.kotlin.fir.resolve.inference.FirCallCompleter;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderKt;
import org.jetbrains.kotlin.fir.symbols.SyntheticCallableId;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ArrayUtilsKt;
import org.jetbrains.kotlin.fir.types.CompilerConeAttributes;
import org.jetbrains.kotlin.fir.types.CompilerConeAttributesKt;
import org.jetbrains.kotlin.fir.types.ConeAttributes;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeContext;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FunctionalTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.fir.types.builder.FirTypeProjectionWithVarianceBuilder;
import org.jetbrains.kotlin.fir.types.impl.ConeTypeParameterTypeImpl;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.resolve.ArrayFqNames;
import org.jetbrains.kotlin.resolve.calls.tasks.ExplicitReceiverKind;
import org.jetbrains.kotlin.resolve.calls.tower.CandidateApplicability;
import org.jetbrains.kotlin.types.Variance;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;
import org.jetbrains.kotlin.types.model.MarkerExtensionsKt;
import org.jetbrains.kotlin.types.model.TypeConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u009e\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u001e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eJ\u001e\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eJ\u001e\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020#2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eJ\u001e\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020&2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eJ\u001e\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020)2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eJ\u001e\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eJ\u0012\u0010/\u001a\u0004\u0018\u00010\u00122\u0006\u00100\u001a\u000201H\u0002J&\u00102\u001a\u00020,2\u0006\u0010-\u001a\u0002032\u0006\u00100\u001a\u0002012\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eJ\u0012\u00104\u001a\u0004\u0018\u00010\u00122\u0006\u00105\u001a\u00020\u0011H\u0002J \u00106\u001a\u00020.2\u0006\u00107\u001a\u0002032\b\u00108\u001a\u0004\u0018\u0001092\u0006\u0010\u001b\u001a\u00020\u001cJ \u0010:\u001a\u00020.2\u0006\u0010;\u001a\u00020<2\b\u00108\u001a\u0004\u0018\u0001092\u0006\u0010\u001b\u001a\u00020\u001cJ\u0014\u0010=\u001a\u000201*\u0002012\u0006\u0010>\u001a\u00020?H\u0002J\u0014\u0010@\u001a\u00020A*\u00020.2\u0006\u0010B\u001a\u00020CH\u0002J\u001e\u0010D\u001a\u00020.*\u00020.2\u0006\u0010B\u001a\u00020C2\b\u0010E\u001a\u0004\u0018\u00010FH\u0002J \u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020H2\b\u00100\u001a\u0004\u0018\u0001012\u0006\u0010\u001b\u001a\u00020\u001cJ\u001c\u0010J\u001a\u00020\u0015*\u00020H2\u0006\u0010K\u001a\u00020,2\u0006\u0010L\u001a\u00020AH\u0002J*\u0010M\u001a\u00020N2\u0006\u0010O\u001a\u00020.2\u0006\u0010P\u001a\u00020Q2\b\u00100\u001a\u0004\u0018\u0001012\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J(\u0010R\u001a\u00020N2\u0006\u0010O\u001a\u00020.2\u0006\u0010P\u001a\u00020Q2\u0006\u0010S\u001a\u0002012\u0006\u0010\u001b\u001a\u00020\u001cH\u0002JB\u0010T\u001a\u00020N2\u0006\u0010O\u001a\u00020.2\u0006\u0010U\u001a\u00020\t2\u0006\u0010P\u001a\u00020Q2\u0006\u0010V\u001a\u00020\u00112\b\b\u0002\u0010W\u001a\u00020X2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J \u0010Y\u001a\u00020Z2\u0006\u0010[\u001a\u00020\\2\u0006\u0010U\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J0\u0010]\u001a\u00020\\2\u0006\u0010O\u001a\u00020.2\u0006\u0010V\u001a\u00020\u00112\u0006\u0010P\u001a\u00020Q2\u0006\u0010W\u001a\u00020X2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u001c\u0010^\u001a\u000e\u0012\u0004\u0012\u00020`\u0012\u0004\u0012\u0002010_2\u0006\u0010a\u001a\u00020bH\u0002J\u0010\u0010c\u001a\u00020\t2\u0006\u0010d\u001a\u00020eH\u0002J\b\u0010f\u001a\u00020\tH\u0002J\b\u0010g\u001a\u00020\tH\u0002J\b\u0010h\u001a\u00020\tH\u0002J \u0010i\u001a\u00020j2\u0006\u0010k\u001a\u00020\u00122\u0006\u0010V\u001a\u00020\u00112\u0006\u0010l\u001a\u00020mH\u0002J*\u0010n\u001a\u00020o*\u0002012\u0006\u0010p\u001a\u00020q2\n\u0010a\u001a\u0006\u0012\u0002\b\u00030r2\b\b\u0002\u0010s\u001a\u00020AH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u000f\u001a\u0018\u0012\u0004\u0012\u00020\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006t"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/FirSyntheticCallGenerator;", Argument.Delimiters.none, "components", "Lorg/jetbrains/kotlin/fir/resolve/BodyResolveComponents;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/BodyResolveComponents;)V", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "whenSelectFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "trySelectFunction", "idFunction", "checkNotNullFunction", "elvisFunction", "equalityFunction", "arrayOfSymbolCache", "Lorg/jetbrains/kotlin/fir/caches/FirCache;", "Lorg/jetbrains/kotlin/name/Name;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", Argument.Delimiters.none, "assertSyntheticResolvableReferenceIsNotResolved", Argument.Delimiters.none, "resolvable", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvable;", "generateCalleeForWhenExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;", "whenExpression", "context", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "resolutionMode", "Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode;", "generateCalleeForTryExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirTryExpression;", "tryExpression", "generateCalleeForCheckNotNullCall", "Lorg/jetbrains/kotlin/fir/expressions/FirCheckNotNullCall;", "checkNotNullCall", "generateCalleeForElvisExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirElvisExpression;", "elvisExpression", "generateCalleeForEqualityOperatorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirEqualityOperatorCall;", "equalityOperatorCall", "generateSyntheticIdCall", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "arrayLiteral", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "calculateArrayOfSymbol", "expectedType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "generateSyntheticArrayOfCall", "Lorg/jetbrains/kotlin/fir/expressions/FirCollectionLiteral;", "getArrayOfSymbol", "arrayOfName", "resolveCollectionLiteralExpressionWithSyntheticOuterCall", "collectionLiteral", "expectedTypeData", "Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode$WithExpectedType;", "resolveAnonymousFunctionExpressionWithSyntheticOuterCall", "anonymousFunctionExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirAnonymousFunctionExpression;", "adaptExpectedTypeForLambdaIfNeeded", "lambda", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "adaptForTypeMismatch", Argument.Delimiters.none, "diagnostic", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "withAdaptedError", "sourceIfNotAdaptedForTypeMismatch", "Lorg/jetbrains/kotlin/KtSourceElement;", "resolveCallableReferenceWithSyntheticOuterCall", "Lorg/jetbrains/kotlin/fir/expressions/FirCallableReferenceAccess;", "callableReferenceAccess", "updateErrorsIfNecessary", "fakeCall", "initialCallWasUnresolved", "generateCalleeReferenceToFunctionWithExpectedTypeForArgument", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/FirNamedReferenceWithCandidate;", "callSite", "argumentList", "Lorg/jetbrains/kotlin/fir/expressions/FirArgumentList;", "generateCalleeReferenceToFunctionWithSingleParameterOfSpecifiedType", "parameterType", "generateCalleeReferenceWithCandidate", "function", ModuleXmlParser.NAME, "callKind", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallKind;", "generateCandidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate;", "callInfo", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/CallInfo;", "generateCallInfo", "generateSyntheticSelectTypeParameter", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;", "functionSymbol", "Lorg/jetbrains/kotlin/fir/resolve/calls/FirSyntheticFunctionSymbol;", "generateSyntheticSelectFunction", "callableId", "Lorg/jetbrains/kotlin/name/CallableId;", "generateSyntheticCheckNotNullFunction", "generateSyntheticElvisFunction", "generateSyntheticEqualityFunction", "generateMemberFunction", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirNamedFunctionBuilder;", "symbol", "returnType", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "toValueParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "nameAsString", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;", "isVararg", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirSyntheticCallGenerator {
    private final FirCache arrayOfSymbolCache;
    private final FirNamedFunction checkNotNullFunction;
    private final BodyResolveComponents components;
    private final FirNamedFunction elvisFunction;
    private final FirNamedFunction equalityFunction;
    private final FirNamedFunction idFunction;
    private final FirSession session;
    private final FirNamedFunction trySelectFunction;
    private final FirNamedFunction whenSelectFunction;

    public FirSyntheticCallGenerator(BodyResolveComponents bodyResolveComponents) {
        bodyResolveComponents.getClass();
        this.components = bodyResolveComponents;
        FirSession session = bodyResolveComponents.getSession();
        this.session = session;
        SyntheticCallableId syntheticCallableId = SyntheticCallableId.INSTANCE;
        this.whenSelectFunction = generateSyntheticSelectFunction(syntheticCallableId.getWHEN());
        this.trySelectFunction = generateSyntheticSelectFunction(syntheticCallableId.getTRY());
        this.idFunction = generateSyntheticSelectFunction(syntheticCallableId.getID());
        this.checkNotNullFunction = generateSyntheticCheckNotNullFunction();
        this.elvisFunction = generateSyntheticElvisFunction();
        this.equalityFunction = generateSyntheticEqualityFunction();
        this.arrayOfSymbolCache = FirCachesFactoryKt.getFirCachesFactory(session).createCache(new Function2() { // from class: org.jetbrains.kotlin.fir.resolve.transformers.FirSyntheticCallGenerator$special$$inlined$createCache$1
            public final FirNamedFunctionSymbol invoke(Name name, Void r2) {
                name.getClass();
                return this.$receiver$inlined.getArrayOfSymbol(name);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                return invoke((Name) obj, (Void) obj2);
            }
        });
    }

    private final ConeKotlinType adaptExpectedTypeForLambdaIfNeeded(ConeKotlinType coneKotlinType, FirAnonymousFunction firAnonymousFunction) {
        if (firAnonymousFunction.getHasExplicitParameterList() && FunctionalTypeUtilsKt.isSomeFunctionType(coneKotlinType, this.session) && (FunctionalTypeUtilsKt.receiverType(coneKotlinType, this.session) != null || CompilerConeAttributesKt.getContextParameterNumberForFunctionType(coneKotlinType) != 0)) {
            ConeSimpleKotlinType coneSimpleKotlinTypeUnwrapLowerBound = ConeTypeUtilsKt.unwrapLowerBound(coneKotlinType);
            ConeClassLikeType coneClassLikeType = coneSimpleKotlinTypeUnwrapLowerBound instanceof ConeClassLikeType ? (ConeClassLikeType) coneSimpleKotlinTypeUnwrapLowerBound : null;
            if (coneClassLikeType != null && firAnonymousFunction.getValueParameters().size() == FunctionalTypeUtilsKt.valueParameterTypesIncludingReceiver(coneClassLikeType, this.session).size()) {
                return TypeUtilsKt.withAttributes(coneClassLikeType, coneClassLikeType.getAttributes().remove(Reflection.getOrCreateKotlinClass(CompilerConeAttributes.ExtensionFunctionType.class)).remove(Reflection.getOrCreateKotlinClass(CompilerConeAttributes.ContextFunctionTypeParams.class)));
            }
        }
        return coneKotlinType;
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x0116, code lost:
    
        if (kotlin.jvm.internal.Intrinsics.areEqual(r9 != null ? r9.getKind() : null, org.jetbrains.kotlin.KtFakeSourceElementKind.CalleeReferenceForOperatorOfCall.INSTANCE) != false) goto L42;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final boolean adaptForTypeMismatch(FirExpression firExpression, ConeDiagnostic coneDiagnostic) {
        if (!(coneDiagnostic instanceof ConeInapplicableCandidateError)) {
            return false;
        }
        AbstractCallCandidate<?> candidate = ((ConeInapplicableCandidateError) coneDiagnostic).getCandidate();
        candidate.getClass();
        Candidate candidate2 = (Candidate) candidate;
        Iterator<T> it = candidate2.getDiagnostics().iterator();
        boolean z = false;
        Object obj = null;
        while (true) {
            if (!it.hasNext()) {
                if (!z) {
                    break;
                }
                break;
            }
            Object next = it.next();
            ResolutionDiagnostic resolutionDiagnostic = (ResolutionDiagnostic) next;
            if ((resolutionDiagnostic instanceof ArgumentTypeMismatch) && Intrinsics.areEqual(((ArgumentTypeMismatch) resolutionDiagnostic).getArgument(), firExpression)) {
                if (!z) {
                    obj = next;
                    z = true;
                }
            }
            obj = null;
            break;
        }
        ArgumentTypeMismatch argumentTypeMismatch = (ArgumentTypeMismatch) obj;
        if (argumentTypeMismatch == null) {
            return false;
        }
        if (!(firExpression instanceof FirAnonymousFunctionExpression)) {
            if (!(firExpression instanceof FirFunctionCall)) {
                s0g.a("Expected ", Reflection.getOrCreateKotlinClass(FirAnonymousFunctionExpression.class).getSimpleName(), " or ", Reflection.getOrCreateKotlinClass(FirFunctionCall.class).getSimpleName());
                return false;
            }
            if (FirLanguageSettingsComponentKt.getLanguageVersionSettings(this.session).supportsFeature(LanguageFeature.CollectionLiterals)) {
                KtSourceElement source = ((FirFunctionCall) firExpression).getCalleeReference().getSource();
            }
            w68.a("Expected ", Reflection.getOrCreateKotlinClass(FirFunctionCall.class).getSimpleName(), " originating from ", Reflection.getOrCreateKotlinClass(FirCollectionLiteral.class).getSimpleName());
            return false;
        }
        Map fixedTypeVariables = (candidate2.getUsedOuterCs() ? candidate2.getSystem().currentStorage() : candidate2.getSystem().asReadOnlyStorage()).getFixedTypeVariables();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry entry : fixedTypeVariables.entrySet()) {
            if (!(((KotlinTypeMarker) entry.getValue()) instanceof ConeErrorType)) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        FirAnonymousFunctionExpression firAnonymousFunctionExpression = (FirAnonymousFunctionExpression) firExpression;
        firAnonymousFunctionExpression.getAnonymousFunction().replaceTypeRef(TypeUtilsKt.withReplacedConeType$default(firAnonymousFunctionExpression.getAnonymousFunction().getTypeRef(), TypeVariableTypeRemovingSubstitutorKt.removeTypeVariableTypes(MarkerExtensionsKt.safeSubstitute(TypeComponentsKt.getTypeContext(this.components.getSession()).typeSubstitutorByTypeConstructor((Map<TypeConstructorMarker, ? extends KotlinTypeMarker>) linkedHashMap), TypeComponentsKt.getTypeContext(this.components.getSession()), argumentTypeMismatch.getActualType()), TypeComponentsKt.getTypeContext(this.components.getSession()), TypeVariableReplacement.ErrorType, candidate2.getSystem().getOuterTypeVariables()), null, 2, null));
        return true;
    }

    private final void assertSyntheticResolvableReferenceIsNotResolved(FirResolvable resolvable) {
        resolvable.getCalleeReference();
    }

    private final FirNamedFunctionSymbol calculateArrayOfSymbol(ConeKotlinType expectedType) {
        Name arrayOfFactoryName = StdlibFactoryFunctionsUtilsKt.toArrayOfFactoryName(expectedType, this.session, true);
        if (arrayOfFactoryName != null) {
            return (FirNamedFunctionSymbol) this.arrayOfSymbolCache.getValue(arrayOfFactoryName, null);
        }
        return null;
    }

    private final CallInfo generateCallInfo(FirExpression callSite, Name name, FirArgumentList argumentList, CallKind callKind, ResolutionMode resolutionMode) {
        return new CallInfo(callSite, callKind, name, null, argumentList, false, CollectionsKt.emptyList(), this.session, this.components.getFile(), this.components.getContainingDeclarations(), null, resolutionMode, null, ImplicitInvokeMode.None, null, 21504, null);
    }

    private final FirNamedReferenceWithCandidate generateCalleeReferenceToFunctionWithExpectedTypeForArgument(FirExpression callSite, FirArgumentList argumentList, ConeKotlinType expectedType, ResolutionContext context) {
        if (expectedType == null || TypeUtilsKt.isUnitOrFlexibleUnit(expectedType)) {
            expectedType = context.getSession().getBuiltinTypes().getAnyType().getConeType();
        }
        return generateCalleeReferenceToFunctionWithSingleParameterOfSpecifiedType(callSite, argumentList, expectedType, context);
    }

    private final FirNamedReferenceWithCandidate generateCalleeReferenceToFunctionWithSingleParameterOfSpecifiedType(FirExpression callSite, FirArgumentList argumentList, ConeKotlinType parameterType, ResolutionContext context) {
        if (argumentList.getArguments().size() != 1) {
            k2d.a("Check failed.");
            return null;
        }
        CallableId accept_specific_type = SyntheticCallableId.INSTANCE.getACCEPT_SPECIFIC_TYPE();
        FirSyntheticFunctionSymbol firSyntheticFunctionSymbol = new FirSyntheticFunctionSymbol(accept_specific_type);
        FirNamedFunctionBuilder firNamedFunctionBuilderGenerateMemberFunction = generateMemberFunction(firSyntheticFunctionSymbol, accept_specific_type.getCallableName(), context.getSession().getBuiltinTypes().getUnitType());
        firNamedFunctionBuilderGenerateMemberFunction.getValueParameters().add(toValueParameter(parameterType, "reference", firSyntheticFunctionSymbol, false));
        return generateCalleeReferenceWithCandidate(callSite, firNamedFunctionBuilderGenerateMemberFunction.mo288build(), argumentList, accept_specific_type.getCallableName(), CallKind.SyntheticIdForCallableReferencesResolution.INSTANCE, context, ResolutionMode.ContextIndependent.INSTANCE);
    }

    private final FirNamedReferenceWithCandidate generateCalleeReferenceWithCandidate(FirExpression callSite, FirNamedFunction function, FirArgumentList argumentList, Name name, CallKind callKind, ResolutionContext context, ResolutionMode resolutionMode) {
        Candidate candidateGenerateCandidate = generateCandidate(generateCallInfo(callSite, name, argumentList, callKind, resolutionMode), function, context);
        CandidateApplicability candidateApplicabilityProcessCandidate$default = ResolutionStageRunner.processCandidate$default(this.components.getResolutionStageRunner(), candidateGenerateCandidate, context, false, false, 12, null);
        KtSourceElement source = callSite.getSource();
        KtSourceElement ktSourceElementFakeElement$default = source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.SyntheticCall.INSTANCE, null, 2, null) : null;
        return !candidateGenerateCandidate.isSuccessful() ? ErrorCandidateUtilsKt.createErrorReferenceWithExistingCandidate(candidateGenerateCandidate, new ConeInapplicableCandidateError(candidateApplicabilityProcessCandidate$default, candidateGenerateCandidate), ktSourceElementFakeElement$default, context, this.components.getResolutionStageRunner()) : new FirNamedReferenceWithCandidate(ktSourceElementFakeElement$default, name, candidateGenerateCandidate);
    }

    public static /* synthetic */ FirNamedReferenceWithCandidate generateCalleeReferenceWithCandidate$default(FirSyntheticCallGenerator firSyntheticCallGenerator, FirExpression firExpression, FirNamedFunction firNamedFunction, FirArgumentList firArgumentList, Name name, CallKind callKind, ResolutionContext resolutionContext, ResolutionMode resolutionMode, int i, Object obj) {
        if ((i & 16) != 0) {
            callKind = CallKind.SyntheticSelect.INSTANCE;
        }
        return firSyntheticCallGenerator.generateCalleeReferenceWithCandidate(firExpression, firNamedFunction, firArgumentList, name, callKind, resolutionContext, resolutionMode);
    }

    private final Candidate generateCandidate(CallInfo callInfo, FirNamedFunction function, ResolutionContext context) {
        return CandidateFactory.createCandidate$default(new CandidateFactory(context, callInfo), callInfo, function.getSymbol(), ExplicitReceiverKind.NO_EXPLICIT_RECEIVER, null, null, null, false, false, 240, null);
    }

    private final FirNamedFunctionBuilder generateMemberFunction(FirNamedFunctionSymbol symbol, Name name, FirTypeRef returnType) {
        FirNamedFunctionBuilder firNamedFunctionBuilder = new FirNamedFunctionBuilder();
        firNamedFunctionBuilder.setModuleData(FirModuleDataKt.getModuleData(this.session));
        firNamedFunctionBuilder.setOrigin(FirDeclarationOrigin.Synthetic.FakeFunction.INSTANCE);
        firNamedFunctionBuilder.setSymbol(symbol);
        firNamedFunctionBuilder.setName(name);
        firNamedFunctionBuilder.setStatus(FirResolvedDeclarationStatusImpl.INSTANCE.getDEFAULT_STATUS_FOR_STATUSLESS_DECLARATIONS());
        firNamedFunctionBuilder.setLocal(false);
        firNamedFunctionBuilder.setReturnTypeRef(returnType);
        firNamedFunctionBuilder.setResolvePhase(FirResolvePhase.BODY_RESOLVE);
        return firNamedFunctionBuilder;
    }

    private final FirNamedFunction generateSyntheticCheckNotNullFunction() {
        SyntheticCallableId syntheticCallableId = SyntheticCallableId.INSTANCE;
        FirSyntheticFunctionSymbol firSyntheticFunctionSymbol = new FirSyntheticFunctionSymbol(syntheticCallableId.getCHECK_NOT_NULL());
        Pair<FirTypeParameter, ConeKotlinType> pairGenerateSyntheticSelectTypeParameter = generateSyntheticSelectTypeParameter(firSyntheticFunctionSymbol);
        FirTypeParameter firTypeParameter = (FirTypeParameter) pairGenerateSyntheticSelectTypeParameter.component1();
        ConeKotlinType coneKotlinType = (ConeKotlinType) pairGenerateSyntheticSelectTypeParameter.component2();
        FirNamedFunctionBuilder firNamedFunctionBuilderGenerateMemberFunction = generateMemberFunction(firSyntheticFunctionSymbol, syntheticCallableId.getCHECK_NOT_NULL().getCallableName(), UtilsKt.toFirResolvedTypeRef$default(TypeUtilsKt.makeConeTypeDefinitelyNotNullOrNotNull$default(coneKotlinType, (ConeTypeContext) TypeComponentsKt.getTypeContext(this.session), true, false, 4, (Object) null), null, null, 3, null));
        firNamedFunctionBuilderGenerateMemberFunction.getTypeParameters().add(firTypeParameter);
        firNamedFunctionBuilderGenerateMemberFunction.getValueParameters().add(toValueParameter$default(this, TypeUtilsKt.withNullability$default(coneKotlinType, true, TypeComponentsKt.getTypeContext(this.session), null, false, 12, null), "arg", firSyntheticFunctionSymbol, false, 4, null));
        return firNamedFunctionBuilderGenerateMemberFunction.mo288build();
    }

    private final FirNamedFunction generateSyntheticElvisFunction() {
        SyntheticCallableId syntheticCallableId = SyntheticCallableId.INSTANCE;
        FirSyntheticFunctionSymbol firSyntheticFunctionSymbol = new FirSyntheticFunctionSymbol(syntheticCallableId.getELVIS());
        Pair<FirTypeParameter, ConeKotlinType> pairGenerateSyntheticSelectTypeParameter = generateSyntheticSelectTypeParameter(firSyntheticFunctionSymbol);
        FirTypeParameter firTypeParameter = (FirTypeParameter) pairGenerateSyntheticSelectTypeParameter.component1();
        ConeKotlinType coneKotlinType = (ConeKotlinType) pairGenerateSyntheticSelectTypeParameter.component2();
        FirResolvedTypeRef firResolvedTypeRef$default = UtilsKt.toFirResolvedTypeRef$default(TypeUtilsKt.withAttributes(coneKotlinType, ConeAttributes.INSTANCE.create(CollectionsKt.listOf(CompilerConeAttributes.Exact.INSTANCE))), null, null, 3, null);
        FirTypeProjectionWithVarianceBuilder firTypeProjectionWithVarianceBuilder = new FirTypeProjectionWithVarianceBuilder();
        firTypeProjectionWithVarianceBuilder.setTypeRef(firResolvedTypeRef$default);
        firTypeProjectionWithVarianceBuilder.setVariance(Variance.INVARIANT);
        FirNamedFunctionBuilder firNamedFunctionBuilderGenerateMemberFunction = generateMemberFunction(firSyntheticFunctionSymbol, syntheticCallableId.getELVIS().getCallableName(), firTypeProjectionWithVarianceBuilder.build().getTypeRef());
        firNamedFunctionBuilderGenerateMemberFunction.getTypeParameters().add(firTypeParameter);
        firNamedFunctionBuilderGenerateMemberFunction.getValueParameters().add(toValueParameter$default(this, TypeUtilsKt.withNullability$default(coneKotlinType, true, TypeComponentsKt.getTypeContext(this.session), null, false, 12, null), "x", firSyntheticFunctionSymbol, false, 4, null));
        firNamedFunctionBuilderGenerateMemberFunction.getValueParameters().add(toValueParameter$default(this, coneKotlinType, "y", firSyntheticFunctionSymbol, false, 4, null));
        return firNamedFunctionBuilderGenerateMemberFunction.mo288build();
    }

    private final FirNamedFunction generateSyntheticEqualityFunction() {
        SyntheticCallableId syntheticCallableId = SyntheticCallableId.INSTANCE;
        FirSyntheticFunctionSymbol firSyntheticFunctionSymbol = new FirSyntheticFunctionSymbol(syntheticCallableId.getEQUALITY());
        FirNamedFunctionBuilder firNamedFunctionBuilderGenerateMemberFunction = generateMemberFunction(firSyntheticFunctionSymbol, syntheticCallableId.getEQUALITY().getCallableName(), this.session.getBuiltinTypes().getBooleanType());
        firNamedFunctionBuilderGenerateMemberFunction.getValueParameters().add(toValueParameter$default(this, this.session.getBuiltinTypes().getNullableAnyType().getConeType(), "x", firSyntheticFunctionSymbol, false, 4, null));
        firNamedFunctionBuilderGenerateMemberFunction.getValueParameters().add(toValueParameter$default(this, this.session.getBuiltinTypes().getNullableAnyType().getConeType(), "y", firSyntheticFunctionSymbol, false, 4, null));
        return firNamedFunctionBuilderGenerateMemberFunction.mo288build();
    }

    private final FirNamedFunction generateSyntheticSelectFunction(CallableId callableId) {
        FirSyntheticFunctionSymbol firSyntheticFunctionSymbol = new FirSyntheticFunctionSymbol(callableId);
        Pair<FirTypeParameter, ConeKotlinType> pairGenerateSyntheticSelectTypeParameter = generateSyntheticSelectTypeParameter(firSyntheticFunctionSymbol);
        FirTypeParameter firTypeParameter = (FirTypeParameter) pairGenerateSyntheticSelectTypeParameter.component1();
        ConeKotlinType coneKotlinType = (ConeKotlinType) pairGenerateSyntheticSelectTypeParameter.component2();
        FirTypeProjectionWithVarianceBuilder firTypeProjectionWithVarianceBuilder = new FirTypeProjectionWithVarianceBuilder();
        firTypeProjectionWithVarianceBuilder.setTypeRef(UtilsKt.toFirResolvedTypeRef$default(coneKotlinType, null, null, 3, null));
        firTypeProjectionWithVarianceBuilder.setVariance(Variance.INVARIANT);
        FirNamedFunctionBuilder firNamedFunctionBuilderGenerateMemberFunction = generateMemberFunction(firSyntheticFunctionSymbol, callableId.getCallableName(), firTypeProjectionWithVarianceBuilder.build().getTypeRef());
        firNamedFunctionBuilderGenerateMemberFunction.getTypeParameters().add(firTypeParameter);
        firNamedFunctionBuilderGenerateMemberFunction.getValueParameters().add(toValueParameter(ArrayUtilsKt.createArrayType$default(coneKotlinType, false, false, 3, null), "branches", firSyntheticFunctionSymbol, true));
        return firNamedFunctionBuilderGenerateMemberFunction.mo288build();
    }

    private final Pair<FirTypeParameter, ConeKotlinType> generateSyntheticSelectTypeParameter(FirSyntheticFunctionSymbol functionSymbol) {
        FirTypeParameterSymbol firTypeParameterSymbol = new FirTypeParameterSymbol();
        FirTypeParameterBuilder firTypeParameterBuilder = new FirTypeParameterBuilder();
        firTypeParameterBuilder.setModuleData(FirModuleDataKt.getModuleData(this.session));
        firTypeParameterBuilder.setOrigin(FirDeclarationOrigin.Synthetic.FakeFunction.INSTANCE);
        firTypeParameterBuilder.setResolvePhase(FirResolvePhase.INSTANCE.getANALYZED_DEPENDENCIES());
        Name nameIdentifier = Name.identifier("K");
        nameIdentifier.getClass();
        firTypeParameterBuilder.setName(nameIdentifier);
        firTypeParameterBuilder.setSymbol(firTypeParameterSymbol);
        firTypeParameterBuilder.setContainingDeclarationSymbol(functionSymbol);
        firTypeParameterBuilder.setVariance(Variance.INVARIANT);
        firTypeParameterBuilder.setReified(false);
        FirDeclarationBuildingUtilsKt.addDefaultBoundIfNecessary(firTypeParameterBuilder);
        return TuplesKt.to(firTypeParameterBuilder.mo288build(), new ConeTypeParameterTypeImpl(firTypeParameterSymbol.getLookupTag(), false, null, 4, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FirNamedFunctionSymbol getArrayOfSymbol(Name arrayOfName) {
        return (FirNamedFunctionSymbol) CollectionsKt.firstOrNull(FirSymbolProviderKt.getSymbolProvider(this.session).getTopLevelFunctionSymbols(StandardNames.BUILT_INS_PACKAGE_FQ_NAME, arrayOfName));
    }

    private final FirValueParameter toValueParameter(ConeKotlinType coneKotlinType, String str, FirFunctionSymbol<?> firFunctionSymbol, boolean z) {
        Name nameIdentifier = Name.identifier(str);
        nameIdentifier.getClass();
        FirValueParameterBuilder firValueParameterBuilder = new FirValueParameterBuilder();
        firValueParameterBuilder.setModuleData(FirModuleDataKt.getModuleData(this.session));
        firValueParameterBuilder.setContainingDeclarationSymbol(firFunctionSymbol);
        firValueParameterBuilder.setOrigin(FirDeclarationOrigin.Synthetic.FakeFunction.INSTANCE);
        firValueParameterBuilder.setName(nameIdentifier);
        firValueParameterBuilder.setReturnTypeRef(UtilsKt.toFirResolvedTypeRef$default(coneKotlinType, null, null, 3, null));
        firValueParameterBuilder.setCrossinline(false);
        firValueParameterBuilder.setNoinline(false);
        firValueParameterBuilder.setVararg(z);
        firValueParameterBuilder.setSymbol(new FirValueParameterSymbol());
        firValueParameterBuilder.setResolvePhase(FirResolvePhase.BODY_RESOLVE);
        return firValueParameterBuilder.mo288build();
    }

    public static /* synthetic */ FirValueParameter toValueParameter$default(FirSyntheticCallGenerator firSyntheticCallGenerator, ConeKotlinType coneKotlinType, String str, FirFunctionSymbol firFunctionSymbol, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return firSyntheticCallGenerator.toValueParameter(coneKotlinType, str, firFunctionSymbol, z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void updateErrorsIfNecessary(FirCallableReferenceAccess firCallableReferenceAccess, FirFunctionCall firFunctionCall, boolean z) {
        FirNamedReference errorReference;
        FirNamedReference calleeReference = firFunctionCall.getCalleeReference();
        FirNamedReference calleeReference2 = firCallableReferenceAccess.getCalleeReference();
        if (!FirReferenceUtilsKt.isError(calleeReference)) {
            if (z && (calleeReference2 instanceof FirErrorNamedReference)) {
                FirErrorNamedReference firErrorNamedReference = (FirErrorNamedReference) calleeReference2;
                ConeDiagnostic diagnostic = firErrorNamedReference.getDiagnostic();
                ConeAmbiguityError coneAmbiguityError = diagnostic instanceof ConeAmbiguityError ? (ConeAmbiguityError) diagnostic : null;
                if (coneAmbiguityError != null) {
                    FirErrorNamedReferenceBuilder firErrorNamedReferenceBuilder = new FirErrorNamedReferenceBuilder();
                    firErrorNamedReferenceBuilder.setSource(firErrorNamedReference.getSource());
                    firErrorNamedReferenceBuilder.setDiagnostic(new ConeAmbiguityError(coneAmbiguityError.getName(), CandidateApplicability.INAPPLICABLE, coneAmbiguityError.getCandidatesWithErrors()));
                    firErrorNamedReferenceBuilder.setName(coneAmbiguityError.getName());
                    firCallableReferenceAccess.replaceCalleeReference((FirNamedReference) firErrorNamedReferenceBuilder.build());
                    return;
                }
                return;
            }
            return;
        }
        FirNamedReferenceWithCandidate firNamedReferenceWithCandidate = calleeReference2 instanceof FirNamedReferenceWithCandidate ? (FirNamedReferenceWithCandidate) calleeReference2 : null;
        if (firNamedReferenceWithCandidate != null && (errorReference = ResolveUtilsKt.toErrorReference(firNamedReferenceWithCandidate, ((FirDiagnosticHolder) calleeReference).getDiagnostic())) != null) {
            firCallableReferenceAccess.replaceCalleeReference(errorReference);
        }
        if (FirReferenceUtilsKt.isError(calleeReference2)) {
            return;
        }
        FirResolvedCallableReference firResolvedCallableReference = calleeReference2 instanceof FirResolvedCallableReference ? (FirResolvedCallableReference) calleeReference2 : null;
        if (firResolvedCallableReference == null) {
            k2d.a("By this time the actual callable reference must have already been resolved");
            return;
        }
        FirResolvedErrorReferenceBuilder firResolvedErrorReferenceBuilder = new FirResolvedErrorReferenceBuilder();
        firResolvedErrorReferenceBuilder.setName(firResolvedCallableReference.getName());
        firResolvedErrorReferenceBuilder.setSource(firResolvedCallableReference.getSource());
        firResolvedErrorReferenceBuilder.setResolvedSymbol(firResolvedCallableReference.getResolvedSymbol());
        firResolvedErrorReferenceBuilder.setDiagnostic(((FirDiagnosticHolder) calleeReference).getDiagnostic());
        firCallableReferenceAccess.replaceCalleeReference((FirNamedReference) firResolvedErrorReferenceBuilder.build());
    }

    private final FirExpression withAdaptedError(FirExpression firExpression, ConeDiagnostic coneDiagnostic, KtSourceElement ktSourceElement) {
        return adaptForTypeMismatch(firExpression, coneDiagnostic) ? firExpression : FirExpressionUtilKt.buildErrorExpression(ktSourceElement, coneDiagnostic, firExpression);
    }

    public final FirCheckNotNullCall generateCalleeForCheckNotNullCall(FirCheckNotNullCall checkNotNullCall, ResolutionContext context, ResolutionMode resolutionMode) {
        checkNotNullCall.getClass();
        context.getClass();
        resolutionMode.getClass();
        assertSyntheticResolvableReferenceIsNotResolved(checkNotNullCall);
        return checkNotNullCall.transformCalleeReference((FirTransformer<? super FirNamedReferenceWithCandidate>) UpdateReference.INSTANCE, generateCalleeReferenceWithCandidate$default(this, checkNotNullCall, this.checkNotNullFunction, checkNotNullCall.getArgumentList(), SyntheticCallableId.INSTANCE.getCHECK_NOT_NULL().getCallableName(), null, context, resolutionMode, 16, null));
    }

    public final FirElvisExpression generateCalleeForElvisExpression(FirElvisExpression elvisExpression, ResolutionContext context, ResolutionMode resolutionMode) {
        elvisExpression.getClass();
        context.getClass();
        resolutionMode.getClass();
        assertSyntheticResolvableReferenceIsNotResolved(elvisExpression);
        FirArgumentListBuilder firArgumentListBuilder = new FirArgumentListBuilder();
        firArgumentListBuilder.getArguments().add(elvisExpression.getLhs());
        firArgumentListBuilder.getArguments().add(elvisExpression.getRhs());
        return elvisExpression.transformCalleeReference((FirTransformer<? super FirNamedReferenceWithCandidate>) UpdateReference.INSTANCE, generateCalleeReferenceWithCandidate$default(this, elvisExpression, this.elvisFunction, firArgumentListBuilder.build(), SyntheticCallableId.INSTANCE.getELVIS().getCallableName(), null, context, resolutionMode, 16, null));
    }

    public final FirEqualityOperatorCall generateCalleeForEqualityOperatorCall(FirEqualityOperatorCall equalityOperatorCall, ResolutionContext context, ResolutionMode resolutionMode) {
        equalityOperatorCall.getClass();
        context.getClass();
        resolutionMode.getClass();
        assertSyntheticResolvableReferenceIsNotResolved(equalityOperatorCall);
        return equalityOperatorCall.transformCalleeReference((FirTransformer<? super FirNamedReferenceWithCandidate>) UpdateReference.INSTANCE, generateCalleeReferenceWithCandidate$default(this, equalityOperatorCall, this.equalityFunction, equalityOperatorCall.getArgumentList(), SyntheticCallableId.INSTANCE.getEQUALITY().getCallableName(), null, context, resolutionMode, 16, null));
    }

    public final FirTryExpression generateCalleeForTryExpression(FirTryExpression tryExpression, ResolutionContext context, ResolutionMode resolutionMode) {
        tryExpression.getClass();
        context.getClass();
        resolutionMode.getClass();
        assertSyntheticResolvableReferenceIsNotResolved(tryExpression);
        FirArgumentListBuilder firArgumentListBuilder = new FirArgumentListBuilder();
        firArgumentListBuilder.getArguments().add(tryExpression.getTryBlock());
        Iterator<T> it = tryExpression.getCatches().iterator();
        while (it.hasNext()) {
            firArgumentListBuilder.getArguments().add(((FirCatch) it.next()).getBlock());
        }
        return tryExpression.transformCalleeReference((FirTransformer<? super FirNamedReferenceWithCandidate>) UpdateReference.INSTANCE, generateCalleeReferenceWithCandidate$default(this, tryExpression, this.trySelectFunction, firArgumentListBuilder.build(), SyntheticCallableId.INSTANCE.getTRY().getCallableName(), null, context, resolutionMode, 16, null));
    }

    public final FirWhenExpression generateCalleeForWhenExpression(FirWhenExpression whenExpression, ResolutionContext context, ResolutionMode resolutionMode) {
        whenExpression.getClass();
        context.getClass();
        resolutionMode.getClass();
        assertSyntheticResolvableReferenceIsNotResolved(whenExpression);
        FirArgumentListBuilder firArgumentListBuilder = new FirArgumentListBuilder();
        List<FirExpression> arguments = firArgumentListBuilder.getArguments();
        List<FirWhenBranch> branches = whenExpression.getBranches();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(branches, 10));
        Iterator<T> it = branches.iterator();
        while (it.hasNext()) {
            arrayList.add(((FirWhenBranch) it.next()).getResult());
        }
        CollectionsKt.addAll(arguments, arrayList);
        return whenExpression.transformCalleeReference((FirTransformer<? super FirNamedReferenceWithCandidate>) UpdateReference.INSTANCE, generateCalleeReferenceWithCandidate$default(this, whenExpression, this.whenSelectFunction, firArgumentListBuilder.build(), SyntheticCallableId.INSTANCE.getWHEN().getCallableName(), null, context, resolutionMode, 16, null));
    }

    /* JADX WARN: Code duplicated, block: B:11:0x007b  */
    /* JADX WARN: Multi-variable type inference failed */
    public final FirFunctionCall generateSyntheticArrayOfCall(FirCollectionLiteral arrayLiteral, ConeKotlinType expectedType, ResolutionContext context, ResolutionMode resolutionMode) {
        FirSyntheticCallGenerator firSyntheticCallGenerator;
        FirCollectionLiteral firCollectionLiteral;
        FirNamedReference firNamedReferenceBuild;
        FirFunctionCall firFunctionCallBuild;
        arrayLiteral.getClass();
        expectedType.getClass();
        context.getClass();
        resolutionMode.getClass();
        FirArgumentList argumentList = arrayLiteral.getArgumentList();
        FirNamedFunctionSymbol firNamedFunctionSymbolCalculateArrayOfSymbol = calculateArrayOfSymbol(expectedType);
        FirFunctionCallBuilder firFunctionCallBuilder = new FirFunctionCallBuilder();
        firFunctionCallBuilder.setArgumentList(argumentList);
        CollectionsKt.addAll(firFunctionCallBuilder.getAnnotations(), arrayLiteral.getAnnotations());
        if (firNamedFunctionSymbolCalculateArrayOfSymbol != null) {
            firSyntheticCallGenerator = this;
            firCollectionLiteral = arrayLiteral;
            firNamedReferenceBuild = firSyntheticCallGenerator.generateCalleeReferenceWithCandidate(firCollectionLiteral, (FirNamedFunction) firNamedFunctionSymbolCalculateArrayOfSymbol.getFir(), argumentList, ArrayFqNames.INSTANCE.getARRAY_OF_FUNCTION(), CallKind.Function.INSTANCE, context, resolutionMode);
            if (firNamedReferenceBuild == null) {
            }
            firFunctionCallBuilder.setCalleeReference(firNamedReferenceBuild);
            firFunctionCallBuilder.setSource(firCollectionLiteral.getSource());
            firFunctionCallBuild = firFunctionCallBuilder.mo288build();
            if (firNamedFunctionSymbolCalculateArrayOfSymbol == null) {
                firFunctionCallBuild.replaceConeTypeOrNull(ResolveUtilsKt.typeFromCallee(firSyntheticCallGenerator.components, firFunctionCallBuild));
            }
            return firFunctionCallBuild;
        }
        firSyntheticCallGenerator = this;
        firCollectionLiteral = arrayLiteral;
        FirErrorNamedReferenceBuilder firErrorNamedReferenceBuilder = new FirErrorNamedReferenceBuilder();
        ArrayFqNames arrayFqNames = ArrayFqNames.INSTANCE;
        firErrorNamedReferenceBuilder.setDiagnostic(new ConeUnresolvedNameError(arrayFqNames.getARRAY_OF_FUNCTION(), null, null, 6, null));
        firErrorNamedReferenceBuilder.setName(arrayFqNames.getARRAY_OF_FUNCTION());
        firNamedReferenceBuild = firErrorNamedReferenceBuilder.build();
        firFunctionCallBuilder.setCalleeReference(firNamedReferenceBuild);
        firFunctionCallBuilder.setSource(firCollectionLiteral.getSource());
        firFunctionCallBuild = firFunctionCallBuilder.mo288build();
        if (firNamedFunctionSymbolCalculateArrayOfSymbol == null) {
            firFunctionCallBuild.replaceConeTypeOrNull(ResolveUtilsKt.typeFromCallee(firSyntheticCallGenerator.components, firFunctionCallBuild));
        }
        return firFunctionCallBuild;
    }

    public final FirFunctionCall generateSyntheticIdCall(FirExpression arrayLiteral, ResolutionContext context, ResolutionMode resolutionMode) {
        arrayLiteral.getClass();
        context.getClass();
        resolutionMode.getClass();
        FirArgumentListBuilder firArgumentListBuilder = new FirArgumentListBuilder();
        firArgumentListBuilder.getArguments().add(arrayLiteral);
        FirArgumentList firArgumentListBuild = firArgumentListBuilder.build();
        FirFunctionCallBuilder firFunctionCallBuilder = new FirFunctionCallBuilder();
        firFunctionCallBuilder.setArgumentList(firArgumentListBuild);
        firFunctionCallBuilder.setCalleeReference(generateCalleeReferenceWithCandidate$default(this, arrayLiteral, this.idFunction, firArgumentListBuild, SyntheticCallableId.INSTANCE.getID().getCallableName(), null, context, resolutionMode, 16, null));
        return firFunctionCallBuilder.mo288build();
    }

    public final FirExpression resolveAnonymousFunctionExpressionWithSyntheticOuterCall(FirAnonymousFunctionExpression anonymousFunctionExpression, ResolutionMode.WithExpectedType expectedTypeData, ResolutionContext context) {
        ConeKotlinType expectedType;
        anonymousFunctionExpression.getClass();
        context.getClass();
        FirArgumentList firArgumentListBuildUnaryArgumentList = FirArgumentUtilKt.buildUnaryArgumentList(anonymousFunctionExpression);
        FirNamedReferenceWithCandidate firNamedReferenceWithCandidateGenerateCalleeReferenceToFunctionWithExpectedTypeForArgument = generateCalleeReferenceToFunctionWithExpectedTypeForArgument(anonymousFunctionExpression, firArgumentListBuildUnaryArgumentList, (expectedTypeData == null || (expectedType = expectedTypeData.getExpectedType()) == null) ? null : adaptExpectedTypeForLambdaIfNeeded(expectedType, anonymousFunctionExpression.getAnonymousFunction()), context);
        FirFunctionCallBuilder firFunctionCallBuilder = new FirFunctionCallBuilder();
        firFunctionCallBuilder.setCalleeReference(firNamedReferenceWithCandidateGenerateCalleeReferenceToFunctionWithExpectedTypeForArgument);
        firFunctionCallBuilder.setArgumentList(firArgumentListBuildUnaryArgumentList);
        FirFunctionCall firFunctionCallBuild = firFunctionCallBuilder.mo288build();
        this.components.getDataFlowAnalyzer().enterCallArguments(firFunctionCallBuild, firArgumentListBuildUnaryArgumentList.getArguments());
        this.components.getDataFlowAnalyzer().enterAnonymousFunctionExpression(anonymousFunctionExpression);
        this.components.getDataFlowAnalyzer().exitCallArguments();
        FirFunctionCall firFunctionCall = (FirFunctionCall) FirCallCompleter.completeCall$default(this.components.getCallCompleter(), firFunctionCallBuild, ResolutionMode.ContextIndependent.INSTANCE, false, 4, null);
        this.components.getDataFlowAnalyzer().exitFunctionCall(firFunctionCallBuild, true);
        FirExpression firExpression = firFunctionCall.getArgumentList().getArguments().get(0);
        FirNamedReference calleeReference = firFunctionCall.getCalleeReference();
        FirResolvedErrorReference firResolvedErrorReference = calleeReference instanceof FirResolvedErrorReference ? (FirResolvedErrorReference) calleeReference : null;
        if (firResolvedErrorReference == null) {
            return firExpression;
        }
        ConeDiagnostic diagnostic = firResolvedErrorReference.getDiagnostic();
        KtSourceElement source = anonymousFunctionExpression.getSource();
        return withAdaptedError(anonymousFunctionExpression, diagnostic, source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.ErrorExpressionForTopLevelLambda.INSTANCE, null, 2, null) : null);
    }

    public final FirCallableReferenceAccess resolveCallableReferenceWithSyntheticOuterCall(FirCallableReferenceAccess callableReferenceAccess, ConeKotlinType expectedType, ResolutionContext context) {
        boolean z;
        callableReferenceAccess.getClass();
        context.getClass();
        FirArgumentList firArgumentListBuildUnaryArgumentList = FirArgumentUtilKt.buildUnaryArgumentList(callableReferenceAccess);
        FirNamedReferenceWithCandidate firNamedReferenceWithCandidateGenerateCalleeReferenceToFunctionWithExpectedTypeForArgument = generateCalleeReferenceToFunctionWithExpectedTypeForArgument(callableReferenceAccess, firArgumentListBuildUnaryArgumentList, expectedType, context);
        if (!(firNamedReferenceWithCandidateGenerateCalleeReferenceToFunctionWithExpectedTypeForArgument instanceof FirErrorReferenceWithCandidate) || !(((FirErrorReferenceWithCandidate) firNamedReferenceWithCandidateGenerateCalleeReferenceToFunctionWithExpectedTypeForArgument).getDiagnostic() instanceof ConeInapplicableCandidateError)) {
            z = false;
        } else {
            if (!(callableReferenceAccess.getCalleeReference() instanceof FirSimpleNamedReference) || FirTypeUtilsKt.getHasResolvedType(callableReferenceAccess)) {
                k2d.a("Expected FirCallableReferenceAccess to be unresolved.");
                return null;
            }
            firNamedReferenceWithCandidateGenerateCalleeReferenceToFunctionWithExpectedTypeForArgument = generateCalleeReferenceToFunctionWithSingleParameterOfSpecifiedType(callableReferenceAccess, firArgumentListBuildUnaryArgumentList, context.getSession().getBuiltinTypes().getAnyType().getConeType(), context);
            z = true;
        }
        FirFunctionCallBuilder firFunctionCallBuilder = new FirFunctionCallBuilder();
        firFunctionCallBuilder.setCalleeReference(firNamedReferenceWithCandidateGenerateCalleeReferenceToFunctionWithExpectedTypeForArgument);
        firFunctionCallBuilder.setArgumentList(firArgumentListBuildUnaryArgumentList);
        FirFunctionCall firFunctionCallBuild = firFunctionCallBuilder.mo288build();
        FirCallCompleter.completeCall$default(this.components.getCallCompleter(), firFunctionCallBuild, ResolutionMode.ContextIndependent.INSTANCE, false, 4, null);
        updateErrorsIfNecessary(callableReferenceAccess, firFunctionCallBuild, z);
        return callableReferenceAccess;
    }

    public final FirExpression resolveCollectionLiteralExpressionWithSyntheticOuterCall(FirCollectionLiteral collectionLiteral, ResolutionMode.WithExpectedType expectedTypeData, ResolutionContext context) {
        collectionLiteral.getClass();
        context.getClass();
        FirArgumentList firArgumentListBuildUnaryArgumentList = FirArgumentUtilKt.buildUnaryArgumentList(collectionLiteral);
        FirNamedReferenceWithCandidate firNamedReferenceWithCandidateGenerateCalleeReferenceToFunctionWithExpectedTypeForArgument = generateCalleeReferenceToFunctionWithExpectedTypeForArgument(collectionLiteral, firArgumentListBuildUnaryArgumentList, expectedTypeData != null ? expectedTypeData.getExpectedType() : null, context);
        FirFunctionCallBuilder firFunctionCallBuilder = new FirFunctionCallBuilder();
        firFunctionCallBuilder.setCalleeReference(firNamedReferenceWithCandidateGenerateCalleeReferenceToFunctionWithExpectedTypeForArgument);
        firFunctionCallBuilder.setArgumentList(firArgumentListBuildUnaryArgumentList);
        FirFunctionCall firFunctionCallBuild = firFunctionCallBuilder.mo288build();
        this.components.getDataFlowAnalyzer().enterCallArguments(firFunctionCallBuild, firArgumentListBuildUnaryArgumentList.getArguments());
        this.components.getDataFlowAnalyzer().exitCallArguments();
        FirFunctionCall firFunctionCall = (FirFunctionCall) FirCallCompleter.completeCall$default(this.components.getCallCompleter(), firFunctionCallBuild, ResolutionMode.ContextIndependent.INSTANCE, false, 4, null);
        this.components.getDataFlowAnalyzer().exitFunctionCall(firFunctionCallBuild, true);
        FirExpression firExpression = (FirExpression) CollectionsKt.single(firFunctionCall.getArgumentList().getArguments());
        FirNamedReference calleeReference = firFunctionCall.getCalleeReference();
        if (!(calleeReference instanceof FirResolvedErrorReference)) {
            return firExpression;
        }
        ConeDiagnostic diagnostic = ((FirResolvedErrorReference) calleeReference).getDiagnostic();
        KtSourceElement source = collectionLiteral.getSource();
        return withAdaptedError(firExpression, diagnostic, source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.ErrorExpressionForTopLevelCollectionLiteral.INSTANCE, null, 2, null) : null);
    }
}
