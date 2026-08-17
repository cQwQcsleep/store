package org.jetbrains.kotlin.fir.resolve.dfa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.TuplesKt;
import kotlin.UninitializedPropertyAccessException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.collections.immutable.ExtensionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.K2JsArgumentConstants;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.EventOccurrencesRange;
import org.jetbrains.kotlin.contracts.description.EventOccurrencesRangeKt;
import org.jetbrains.kotlin.contracts.description.KtBooleanExpression;
import org.jetbrains.kotlin.contracts.description.KtConditionalEffectDeclaration;
import org.jetbrains.kotlin.contracts.description.KtConditionalReturnsDeclaration;
import org.jetbrains.kotlin.contracts.description.KtEffectDeclaration;
import org.jetbrains.kotlin.contracts.description.KtHoldsInEffectDeclaration;
import org.jetbrains.kotlin.contracts.description.KtReturnsEffectDeclaration;
import org.jetbrains.kotlin.contracts.description.LogicOperationKind;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.DfaType;
import org.jetbrains.kotlin.fir.EqualsOverrideContract;
import org.jetbrains.kotlin.fir.FirEqualsOverrideHelpersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.PrivateToThisUtilsKt;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.contracts.FirEffectDeclaration;
import org.jetbrains.kotlin.fir.contracts.FirResolvedContractDescription;
import org.jetbrains.kotlin.fir.contracts.description.ConeContractConstantValues;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousInitializer;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirCodeFragment;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirPropertyBodyResolveStateKt;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhaseKt;
import org.jetbrains.kotlin.fir.declarations.FirScript;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.impl.FirDefaultPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousObjectExpression;
import org.jetbrains.kotlin.fir.expressions.FirArgumentList;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirBooleanOperatorExpression;
import org.jetbrains.kotlin.fir.expressions.FirCall;
import org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess;
import org.jetbrains.kotlin.fir.expressions.FirCatch;
import org.jetbrains.kotlin.fir.expressions.FirCheckNotNullCall;
import org.jetbrains.kotlin.fir.expressions.FirComparisonExpression;
import org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall;
import org.jetbrains.kotlin.fir.expressions.FirElvisExpression;
import org.jetbrains.kotlin.fir.expressions.FirEqualityOperatorCall;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirGetClassCall;
import org.jetbrains.kotlin.fir.expressions.FirJump;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.expressions.FirLoop;
import org.jetbrains.kotlin.fir.expressions.FirOperation;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvable;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.FirSafeCallExpression;
import org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.FirStringConcatenationCall;
import org.jetbrains.kotlin.fir.expressions.FirThrowExpression;
import org.jetbrains.kotlin.fir.expressions.FirTryExpression;
import org.jetbrains.kotlin.fir.expressions.FirTypeOperatorCall;
import org.jetbrains.kotlin.fir.expressions.FirVariableAssignment;
import org.jetbrains.kotlin.fir.expressions.FirWhenBranch;
import org.jetbrains.kotlin.fir.expressions.FirWhenExpression;
import org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression;
import org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt;
import org.jetbrains.kotlin.fir.expressions.impl.FirResolvedArgumentList;
import org.jetbrains.kotlin.fir.references.FirControlFlowGraphReference;
import org.jetbrains.kotlin.fir.references.FirPropertyWithExplicitBackingFieldResolvedNamedReference;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ExplicitFieldsUtilsKt;
import org.jetbrains.kotlin.fir.resolve.FirCodeFragmentContext;
import org.jetbrains.kotlin.fir.resolve.ImplicitValueStorage;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.ConeResolutionAtom;
import org.jetbrains.kotlin.fir.resolve.calls.ImplicitValue;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.CandidateFactoryKt;
import org.jetbrains.kotlin.fir.resolve.dfa.FirDataFlowAnalyzer;
import org.jetbrains.kotlin.fir.resolve.dfa.FlowPath;
import org.jetbrains.kotlin.fir.resolve.dfa.Implication;
import org.jetbrains.kotlin.fir.resolve.dfa.MutableFlow;
import org.jetbrains.kotlin.fir.resolve.dfa.OperationStatement;
import org.jetbrains.kotlin.fir.resolve.dfa.RealVariable;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.AnonymousFunctionCaptureNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.AnonymousFunctionExpressionNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.AnonymousObjectExpressionExitNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.BooleanOperatorExitNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNodeKt;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ClassEnterNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ClassExitNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.CodeFragmentExitNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraph;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphBuilder;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphBuilderKt;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.Edge;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ElvisExitNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ElvisLhsExitNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ElvisLhsIsNotNullNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ElvisRhsEnterNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.EnterDefaultArgumentsNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.EnterValueParameterNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.EqualityOperatorCallNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ExitDefaultArgumentsNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ExitSafeCallNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ExitValueParameterNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.FieldInitializerEnterNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.FieldInitializerExitNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.FileEnterNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.FileExitNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.FinallyBlockEnterNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.FinallyBlockExitNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.FirAnonymousFunctionReturnExpressionInfo;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.FunctionCallArgumentsEnterNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.FunctionCallArgumentsExitNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.FunctionCallEnterNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.FunctionCallExitNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.FunctionEnterNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.FunctionExitNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.InitBlockExitNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.LocalFunctionDeclarationNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.LoopBlockEnterNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.LoopBlockExitNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.LoopConditionEnterNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.LoopConditionExitNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.LoopEnterNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.LoopExitNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.MergePostponedLambdaExitsNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.NormalPath;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.PostponedLambdaExitNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.PostponedPath;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.PropertyInitializerEnterNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.PropertyInitializerExitNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ScriptEnterNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ScriptExitNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.SplitPostponedLambdasNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.TryExpressionEnterNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.TryMainBlockEnterNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.UncaughtExceptionPath;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.WhenBranchConditionExitNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.WhenBranchResultEnterNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.WhenExitNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.WhenSyntheticElseBranchNode;
import org.jetbrains.kotlin.fir.resolve.substitution.ChainedSubstitutorKt;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutorByMapKt;
import org.jetbrains.kotlin.fir.resolve.transformers.FirCallCompletionResultsWriterTransformerKt;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirAbstractBodyResolveTransformer;
import org.jetbrains.kotlin.fir.scopes.impl.FirNestedClassifierScopeKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirBackingFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirEnumEntrySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjectionKt;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.RefinedTypeForDataFlowTypeAttributeKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.types.ConstantValueKind;
import org.jetbrains.kotlin.types.SmartcastStability;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000è\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\b&\u0018\u0000 Ã\u00022\u00020\u0001:\u0004Ã\u0002Ä\u0002B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H$J\u001c\u0010%\u001a\u00020&*\u00020'2\u000e\u0010(\u001a\n\u0012\u0004\u0012\u00020*\u0018\u00010)H\u0002J$\u0010+\u001a\u00020,*\u00020-2\u0006\u0010.\u001a\u00020/2\u000e\u00100\u001a\n\u0012\u0004\u0012\u00020*\u0018\u00010)H\u0002J6\u00101\u001a\u0004\u0018\u0001022\u0006\u00103\u001a\u0002042\u001e\b\u0002\u00105\u001a\u0018\u0012\u0004\u0012\u00020-\u0012\u0006\u0012\u0004\u0018\u00010\u0019\u0012\u0006\u0012\u0004\u0018\u00010\u001906H\u0086\bø\u0001\u0000J\u001a\u00107\u001a\u0004\u0018\u00010\u00192\u0006\u0010.\u001a\u00020/2\u0006\u00108\u001a\u00020-H\u0016J\"\u00109\u001a\u0004\u0018\u0001022\u0006\u0010.\u001a\u00020/2\u0006\u00108\u001a\u00020-2\b\u0010:\u001a\u0004\u0018\u00010\u0019J\u0018\u0010;\u001a\n\u0012\u0004\u0012\u00020<\u0018\u00010)2\u0006\u00108\u001a\u00020-H\u0002J\u0018\u0010=\u001a\n\u0012\u0004\u0012\u00020<\u0018\u00010)2\u0006\u0010>\u001a\u00020?H\u0002J\u0014\u0010@\u001a\b\u0012\u0004\u0012\u00020B0A2\u0006\u0010C\u001a\u00020DJ\u000e\u0010E\u001a\u00020\u00172\u0006\u0010C\u001a\u00020FJ\u0010\u0010G\u001a\u0004\u0018\u00010H2\u0006\u0010C\u001a\u00020FJ\u000e\u0010I\u001a\u00020\u00172\u0006\u0010J\u001a\u00020KJ\u0016\u0010L\u001a\u00020\u00172\u0006\u0010M\u001a\u00020N2\u0006\u0010O\u001a\u00020&J\b\u0010P\u001a\u0004\u0018\u00010QJ\u0016\u0010R\u001a\u00020\u00172\u0006\u0010S\u001a\u00020T2\u0006\u0010O\u001a\u00020&J\b\u0010U\u001a\u0004\u0018\u00010QJ\u000e\u0010V\u001a\u00020\u00172\u0006\u0010W\u001a\u00020XJ\u0016\u0010Y\u001a\u00020\u00172\u0006\u0010Z\u001a\u00020[2\u0006\u0010O\u001a\u00020&J\b\u0010\\\u001a\u0004\u0018\u00010QJ\u000e\u0010]\u001a\u00020\u00172\u0006\u0010^\u001a\u00020_J\u000e\u0010`\u001a\u00020Q2\u0006\u0010^\u001a\u00020_J\u000e\u0010a\u001a\u00020\u00172\u0006\u0010b\u001a\u00020cJ\u0010\u0010d\u001a\u0004\u0018\u00010Q2\u0006\u0010b\u001a\u00020cJ\u000e\u0010e\u001a\u00020\u00172\u0006\u0010f\u001a\u00020gJ\u0010\u0010h\u001a\u0004\u0018\u00010Q2\u0006\u0010f\u001a\u00020gJ\u000e\u0010i\u001a\u00020\u00172\u0006\u0010j\u001a\u00020kJ\u0010\u0010l\u001a\u0004\u0018\u00010Q2\u0006\u0010j\u001a\u00020kJ\u0006\u0010m\u001a\u00020\u0017J\u000e\u0010n\u001a\u00020\u00172\u0006\u0010o\u001a\u000204J\u000e\u0010p\u001a\u00020\u00172\u0006\u0010q\u001a\u00020rJ\u000e\u0010s\u001a\u00020\u00172\u0006\u0010q\u001a\u00020rJ\u000e\u0010t\u001a\u00020\u00172\u0006\u0010u\u001a\u00020vJ\u0018\u0010w\u001a\u00020\u00172\u0006\u0010.\u001a\u00020x2\u0006\u0010u\u001a\u00020vH\u0002J\u000e\u0010y\u001a\u00020\u00172\u0006\u0010z\u001a\u00020{J\u0006\u0010|\u001a\u00020\u0017J\u0006\u0010}\u001a\u00020\u0017J\u0018\u0010~\u001a\u00020\u00172\u0007\u0010\u007f\u001a\u00030\u0080\u00012\u0007\u0010\u0081\u0001\u001a\u00020&J6\u0010\u0082\u0001\u001a\u00020\u00172\u0006\u0010.\u001a\u00020x2\u0007\u00103\u001a\u00030\u0080\u00012\u0007\u0010\u0083\u0001\u001a\u0002042\b\u0010\u0084\u0001\u001a\u00030\u0085\u00012\u0007\u0010\u0086\u0001\u001a\u00020&H\u0002J9\u0010\u0087\u0001\u001a\u00020\u00172\u0006\u0010.\u001a\u00020x2\u0006\u00103\u001a\u0002042\u0007\u0010\u0083\u0001\u001a\u0002042\u0007\u0010\u0086\u0001\u001a\u00020&2\f\b\u0002\u0010\u0088\u0001\u001a\u0005\u0018\u00010\u0089\u0001H\u0002J@\u0010\u008a\u0001\u001a\u00020\u00172\u0006\u0010.\u001a\u00020x2\b\u0010\u0088\u0001\u001a\u00030\u0089\u00012\u0007\u00103\u001a\u00030\u0080\u00012\u0007\u0010\u008b\u0001\u001a\u0002042\u0007\u0010\u008c\u0001\u001a\u0002042\b\u0010\u008d\u0001\u001a\u00030\u008e\u0001H\u0002J\u0015\u0010\u008f\u0001\u001a\u00020\u00172\f\u0010\u0090\u0001\u001a\u0007\u0012\u0002\b\u00030\u0091\u0001J\u0015\u0010\u0092\u0001\u001a\u00020\u00172\f\u0010\u0090\u0001\u001a\u0007\u0012\u0002\b\u00030\u0091\u0001J\u0007\u0010\u0093\u0001\u001a\u00020\u0017J\u001a\u0010\u0094\u0001\u001a\u00020\u00172\b\u0010\u0095\u0001\u001a\u00030\u0096\u00012\u0007\u0010\u0081\u0001\u001a\u00020&J\u0011\u0010\u0097\u0001\u001a\u00020\u00172\b\u0010\u0098\u0001\u001a\u00030\u0099\u0001J\u0011\u0010\u009a\u0001\u001a\u00020\u00172\b\u0010\u009b\u0001\u001a\u00030\u009c\u0001J\u0012\u0010\u009d\u0001\u001a\u00020\u0017*\u0007\u0012\u0002\b\u00030\u009e\u0001H\u0002J\u0011\u0010\u009f\u0001\u001a\u00020\u00172\b\u0010\u009b\u0001\u001a\u00030\u009c\u0001J\u0011\u0010 \u0001\u001a\u00020\u00172\b\u0010\u009b\u0001\u001a\u00030\u009c\u0001J\u001a\u0010¡\u0001\u001a\u00020\u00172\b\u0010\u0098\u0001\u001a\u00030\u0099\u00012\u0007\u0010\u0081\u0001\u001a\u00020&J\u0010\u0010¢\u0001\u001a\u00020\u00172\u0007\u00103\u001a\u00030£\u0001J\u0011\u0010¤\u0001\u001a\u00020\u00172\b\u0010¥\u0001\u001a\u00030¦\u0001J\u0011\u0010§\u0001\u001a\u00020\u00172\b\u0010¥\u0001\u001a\u00030¦\u0001J\u0011\u0010¨\u0001\u001a\u00020\u00172\b\u0010¥\u0001\u001a\u00030¦\u0001J?\u0010©\u0001\u001a\u00020\u00172\b\u0010ª\u0001\u001a\u00030«\u00012\u0006\u0010.\u001a\u00020x2\b\u0010¬\u0001\u001a\u00030\u00ad\u00012\b\u0010®\u0001\u001a\u00030¯\u00012\u000e\u0010°\u0001\u001a\t\u0012\u0005\u0012\u00030±\u00010)H\u0002J%\u0010²\u0001\u001a\u00020\u00172\u0006\u0010.\u001a\u00020x2\b\u0010¬\u0001\u001a\u00030\u00ad\u00012\b\u0010³\u0001\u001a\u00030´\u0001H\u0002J!\u0010µ\u0001\u001a\u00020\u00172\u0006\u0010.\u001a\u00020x2\u000e\u0010°\u0001\u001a\t\u0012\u0005\u0012\u00030±\u00010)H\u0002J\u0011\u0010¶\u0001\u001a\u00020\u00172\b\u0010¥\u0001\u001a\u00030¦\u0001J\u0011\u0010·\u0001\u001a\u00020\u00172\b\u0010¥\u0001\u001a\u00030¦\u0001J\u0011\u0010¸\u0001\u001a\u00020\u00172\b\u0010¥\u0001\u001a\u00030¦\u0001J\u0011\u0010¹\u0001\u001a\u00020\u00172\b\u0010º\u0001\u001a\u00030»\u0001J\u0007\u0010¼\u0001\u001a\u00020\u0017J\u0011\u0010½\u0001\u001a\u00020\u00172\b\u0010¾\u0001\u001a\u00030¿\u0001J\u0011\u0010À\u0001\u001a\u00020\u00172\b\u0010¾\u0001\u001a\u00030¿\u0001J\u0007\u0010Á\u0001\u001a\u00020\u0017J\u0007\u0010Â\u0001\u001a\u00020\u0017J\u0010\u0010Ã\u0001\u001a\u00020\u00172\u0007\u0010\u0081\u0001\u001a\u00020&J\u0011\u0010Ä\u0001\u001a\u00020\u00172\b\u0010Å\u0001\u001a\u00030Æ\u0001J\u0011\u0010Ç\u0001\u001a\u00020\u00172\b\u0010È\u0001\u001a\u00030É\u0001J\u0011\u0010Ê\u0001\u001a\u00020\u00172\b\u0010Ë\u0001\u001a\u00030Ì\u0001J\u0011\u0010Í\u0001\u001a\u00020\u00172\b\u0010Ë\u0001\u001a\u00030Ì\u0001J\u0011\u0010Î\u0001\u001a\u00020\u00172\b\u0010Ï\u0001\u001a\u00030Ð\u0001J!\u0010Ñ\u0001\u001a\u00020\u00172\b\u0010Ò\u0001\u001a\u00030Ó\u00012\u000e\u0010Ô\u0001\u001a\t\u0012\u0004\u0012\u0002040Õ\u0001J\u0007\u0010Ö\u0001\u001a\u00020\u0017J\u0007\u0010×\u0001\u001a\u00020\u0017J\u0011\u0010Ø\u0001\u001a\u00020\u00172\b\u0010Ù\u0001\u001a\u00030Ú\u0001J\u001a\u0010Û\u0001\u001a\u00020\u00172\b\u0010Ù\u0001\u001a\u00030Ú\u00012\u0007\u0010\u0081\u0001\u001a\u00020&J\u001a\u0010Ü\u0001\u001a\u00020\u00172\b\u0010Ò\u0001\u001a\u00030Ý\u00012\u0007\u0010\u0081\u0001\u001a\u00020&J\u0007\u0010Þ\u0001\u001a\u00020\u0017J\u0011\u0010ß\u0001\u001a\u00020\u00172\b\u0010Ò\u0001\u001a\u00030à\u0001J*\u0010á\u0001\u001a\u000f\u0012\b\b\u0001\u0012\u0004\u0018\u000104\u0018\u00010â\u0001*\u00030Ó\u00012\u0007\u0010ã\u0001\u001a\u00020FH\u0002¢\u0006\u0003\u0010ä\u0001J4\u0010å\u0001\u001a\u00020\u00172\u0006\u0010.\u001a\u00020x2\b\u0010æ\u0001\u001a\u00030Ó\u00012\n\u0010ç\u0001\u001a\u0005\u0018\u00010\u0089\u00012\u000b\b\u0002\u0010è\u0001\u001a\u0004\u0018\u00010DH\u0002J\u001b\u0010é\u0001\u001a\u00020\u00172\u0006\u0010.\u001a\u00020x2\b\u0010æ\u0001\u001a\u00030Æ\u0001H\u0002J(\u0010ê\u0001\u001a\u00030ë\u00012\u0007\u0010ã\u0001\u001a\u00020F2\b\u0010æ\u0001\u001a\u00030Ó\u00012\t\u0010ì\u0001\u001a\u0004\u0018\u00010FH\u0002J\u0011\u0010í\u0001\u001a\u00020\u00172\b\u0010î\u0001\u001a\u00030\u0085\u0001J\u000f\u0010ï\u0001\u001a\u00020\u00172\u0006\u00108\u001a\u00020gJ\u0018\u0010ð\u0001\u001a\u00020\u00172\u0006\u00108\u001a\u00020g2\u0007\u0010ñ\u0001\u001a\u00020&J\u0011\u0010ò\u0001\u001a\u00020\u00172\b\u0010ó\u0001\u001a\u00030ô\u0001J6\u0010õ\u0001\u001a\u00020\u00172\u0006\u0010.\u001a\u00020x2\u0007\u0010ö\u0001\u001a\u0002042\u0006\u0010f\u001a\u00020g2\t\u0010÷\u0001\u001a\u0004\u0018\u0001042\u0007\u0010ø\u0001\u001a\u00020&H\u0002J\u0011\u0010ù\u0001\u001a\u00020\u00172\b\u0010ú\u0001\u001a\u00030û\u0001J\u0011\u0010ü\u0001\u001a\u00020\u00172\b\u0010ý\u0001\u001a\u00030þ\u0001J\u0011\u0010ÿ\u0001\u001a\u00020\u00172\b\u0010ý\u0001\u001a\u00030þ\u0001J\u0011\u0010\u0080\u0002\u001a\u00020\u00172\b\u0010ý\u0001\u001a\u00030þ\u0001J\u000e\u0010\u0081\u0002\u001a\u00020\u0017*\u00030\u0082\u0002H\u0002J\u001a\u0010\u0083\u0002\u001a\u00020\u00172\u0006\u0010.\u001a\u00020x2\u0007\u00103\u001a\u00030Ú\u0001H\u0002J\u0007\u0010\u0084\u0002\u001a\u00020\u0017J\u0007\u0010\u0085\u0002\u001a\u00020\u0017J\u0011\u0010\u0086\u0002\u001a\u00020\u00172\b\u0010\u0087\u0002\u001a\u00030\u0088\u0002J\u0011\u0010\u0089\u0002\u001a\u00020Q2\b\u0010\u0087\u0002\u001a\u00030\u0088\u0002J\u0007\u0010\u008a\u0002\u001a\u00020\u0017J\u0007\u0010\u008b\u0002\u001a\u00020\u0017J\u0011\u0010\u008c\u0002\u001a\u00020\u00172\b\u0010\u008d\u0002\u001a\u00030\u008e\u0002J\u0011\u0010\u008f\u0002\u001a\u00020\u00172\b\u0010\u008d\u0002\u001a\u00030\u008e\u0002J#\u0010\u0090\u0002\u001a\u00020\u00172\b\u0010\u008d\u0002\u001a\u00030\u008e\u00022\u0007\u0010\u0091\u0002\u001a\u00020&2\u0007\u0010\u0081\u0001\u001a\u00020&J\u0011\u0010\u0092\u0002\u001a\u00020\u00172\b\u0010\u0093\u0002\u001a\u00030\u0094\u0002J\u0011\u0010\u0095\u0002\u001a\u00020\u00172\b\u0010\u0096\u0002\u001a\u00030\u0097\u0002J.\u0010\u009c\u0002\u001a\u00020x*\u0007\u0012\u0002\b\u00030\u009e\u00012\u001a\u0010\u009d\u0002\u001a\u0015\u0012\u0005\u0012\u00030«\u0001\u0012\u0004\u0012\u00020x\u0012\u0004\u0012\u00020\u001706H\u0002J8\u0010\u009e\u0002\u001a\u00020x*\u0007\u0012\u0002\b\u00030\u009e\u00012\b\u0010ª\u0001\u001a\u00030\u009f\u00022\u001a\u0010\u009d\u0002\u001a\u0015\u0012\u0005\u0012\u00030«\u0001\u0012\u0004\u0012\u00020x\u0012\u0004\u0012\u00020\u001706H\u0002J0\u0010 \u0002\u001a\u00020\u0017*\u0007\u0012\u0002\b\u00030\u009e\u00012\u001c\b\u0002\u0010\u009d\u0002\u001a\u0015\u0012\u0005\u0012\u00030«\u0001\u0012\u0004\u0012\u00020x\u0012\u0004\u0012\u00020\u001706H\u0002J.\u0010¡\u0002\u001a\u00020\u0017*\u0007\u0012\u0002\b\u00030\u009e\u00012\u001a\u0010\u009d\u0002\u001a\u0015\u0012\u0005\u0012\u00030«\u0001\u0012\u0004\u0012\u00020x\u0012\u0004\u0012\u00020\u001706H\u0002J0\u0010¢\u0002\u001a\u00020\u0017*\u0007\u0012\u0002\b\u00030\u009e\u00012\u001c\b\u0002\u0010\u009d\u0002\u001a\u0015\u0012\u0005\u0012\u00030«\u0001\u0012\u0004\u0012\u00020x\u0012\u0004\u0012\u00020\u001706H\u0002J\u001d\u0010£\u0002\u001a\u00030\u0089\u0001*\u0007\u0012\u0002\b\u00030\u009e\u00012\b\u0010ª\u0001\u001a\u00030«\u0001H\u0002J\r\u0010¤\u0002\u001a\u00020\u0017*\u00020QH\u0002J\u0007\u0010¥\u0002\u001a\u00020\u0017J\u0013\u0010¦\u0002\u001a\u00020\u00172\b\u0010.\u001a\u0004\u0018\u00010/H\u0002J$\u0010§\u0002\u001a\u00020&2\b\u0010¨\u0002\u001a\u00030\u0089\u00012\u0006\u0010o\u001a\u0002042\u0007\u0010©\u0002\u001a\u00020xH\u0002J\u0017\u0010ª\u0002\u001a\u00020\u0017*\u00020x2\b\u0010«\u0002\u001a\u00030¬\u0002H\u0002J\u0015\u0010\u00ad\u0002\u001a\u00020\u0017*\u00020x2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J(\u0010®\u0002\u001a\u00020\u0017*\u00020x2\u0019\u0010¯\u0002\u001a\u0014\u0012\u0004\u0012\u00020-\u0012\u0004\u0012\u00020\u00190°\u0002j\u0003`±\u0002H\u0002J2\u0010²\u0002\u001a\u00020\u0017*\u00020x2\b\u0010³\u0002\u001a\u00030´\u00022\u0019\u0010¯\u0002\u001a\u0014\u0012\u0004\u0012\u00020-\u0012\u0004\u0012\u00020\u00190°\u0002j\u0003`±\u0002H\u0002J \u0010²\u0002\u001a\u00020\u0017*\u00020x2\b\u0010³\u0002\u001a\u00030´\u00022\u0007\u0010µ\u0002\u001a\u00020/H\u0002J(\u0010¶\u0002\u001a\u0014\u0012\u0004\u0012\u00020-\u0012\u0004\u0012\u00020\u00190°\u0002j\u0003`±\u0002*\u00020x2\u0007\u0010·\u0002\u001a\u00020/H\u0002J\u0017\u0010¸\u0002\u001a\u00020\u0017*\u00020x2\b\u0010«\u0002\u001a\u00030´\u0002H\u0002J \u0010¹\u0002\u001a\u0004\u0018\u00010-*\u00020/2\u0006\u0010o\u001a\u0002042\u0007\u0010º\u0002\u001a\u00020&H\u0002J\u001e\u0010»\u0002\u001a\u0004\u0018\u00010-*\u00020/2\u0006\u0010o\u001a\u0002042\u0007\u0010º\u0002\u001a\u00020&J\u0017\u0010¼\u0002\u001a\u0004\u0018\u00010-*\u00020/2\u0006\u0010o\u001a\u000204H\u0002J\u0017\u0010½\u0002\u001a\u0004\u0018\u00010-*\u00020/2\u0006\u0010o\u001a\u000204H\u0002J\u0017\u0010¾\u0002\u001a\u0004\u0018\u00010-*\u00020/2\u0006\u0010o\u001a\u000204H\u0002J\u0011\u0010¾\u0002\u001a\u0004\u0018\u00010-2\u0006\u0010o\u001a\u000204J\u0017\u0010¿\u0002\u001a\u0004\u0018\u00010'*\u00020/2\u0006\u0010o\u001a\u000204H\u0002J\u0017\u0010À\u0002\u001a\u0004\u0018\u00010'*\u00020/2\u0006\u00108\u001a\u00020'H\u0002J\u001d\u0010Á\u0002\u001a\u0004\u0018\u00010'2\u0007\u0010>\u001a\u00030±\u00012\u0007\u0010Â\u0002\u001a\u00020&H\u0002R\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0012\u0010\u000e\u001a\u00020\u000fX¤\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0012\u0010\u0012\u001a\u00020\u0013X¤\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u001a\u001a\u00020\u001b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001e\u001a\u00020\u001f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b \u0010!R\u000e\u0010\"\u001a\u00020#X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020#X\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\u0099\u0002\u001a\u0004\u0018\u00010/2\t\u0010\u0098\u0002\u001a\u0004\u0018\u00010/@BX\u0086\u000e¢\u0006\n\n\u0000\u001a\u0006\b\u009a\u0002\u0010\u009b\u0002\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006Å\u0002"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/dfa/FirDataFlowAnalyzer;", "Lorg/jetbrains/kotlin/fir/SessionHolder;", "components", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirAbstractBodyResolveTransformer$BodyResolveTransformerComponents;", "context", "Lorg/jetbrains/kotlin/fir/resolve/dfa/DataFlowAnalyzerContext;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirAbstractBodyResolveTransformer$BodyResolveTransformerComponents;Lorg/jetbrains/kotlin/fir/resolve/dfa/DataFlowAnalyzerContext;)V", "getComponents", "()Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirAbstractBodyResolveTransformer$BodyResolveTransformerComponents;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "logicSystem", "Lorg/jetbrains/kotlin/fir/resolve/dfa/LogicSystem;", "getLogicSystem", "()Lorg/jetbrains/kotlin/fir/resolve/dfa/LogicSystem;", "receiverStack", "Lorg/jetbrains/kotlin/fir/resolve/ImplicitValueStorage;", "getReceiverStack", "()Lorg/jetbrains/kotlin/fir/resolve/ImplicitValueStorage;", "implicitUpdated", Argument.Delimiters.none, "info", "Lorg/jetbrains/kotlin/fir/resolve/dfa/TypeStatement;", "graphBuilder", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraphBuilder;", "getGraphBuilder", "()Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraphBuilder;", "variableStorage", "Lorg/jetbrains/kotlin/fir/resolve/dfa/VariableStorage;", "getVariableStorage", "()Lorg/jetbrains/kotlin/fir/resolve/dfa/VariableStorage;", "any", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "nullableNothing", "isUnstableLocalVar", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/dfa/RealVariable;", "types", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "getStability", "Lorg/jetbrains/kotlin/types/SmartcastStability;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/DataFlowVariable;", "flow", "Lorg/jetbrains/kotlin/fir/resolve/dfa/Flow;", "targetTypes", "getTypeUsingSmartcastInfo", "Lorg/jetbrains/kotlin/fir/resolve/dfa/FirDataFlowAnalyzer$SmartCastStatement;", "expression", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "augmentTypeStatement", "Lkotlin/Function2;", "extractTypeStatementFrom", "variable", "buildSmartCastStatement", "typeStatement", "inferLowerTypesFromVariable", "Lorg/jetbrains/kotlin/fir/DfaType;", "inferLowerTypesFromSymbol", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirEnumEntrySymbol;", "returnExpressionsOfAnonymousFunction", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FirAnonymousFunctionReturnExpressionInfo;", "function", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "enterFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "exitFunction", "Lorg/jetbrains/kotlin/fir/references/FirControlFlowGraphReference;", "enterAnonymousFunctionExpression", "anonymousFunctionExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirAnonymousFunctionExpression;", "enterFile", "file", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "buildGraph", "exitFile", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;", "enterClass", "klass", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "exitClass", "exitAnonymousObjectExpression", "anonymousObjectExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirAnonymousObjectExpression;", "enterScript", "script", "Lorg/jetbrains/kotlin/fir/declarations/FirScript;", "exitScript", "enterCodeFragment", "codeFragment", "Lorg/jetbrains/kotlin/fir/declarations/FirCodeFragment;", "exitCodeFragment", "enterValueParameter", "valueParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "exitValueParameter", "enterProperty", "property", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "exitProperty", "enterField", "field", "Lorg/jetbrains/kotlin/fir/declarations/FirField;", "exitField", "enterDelegateExpression", "exitDelegateExpression", "fir", "enterBlock", "block", "Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "exitBlock", "exitTypeOperatorCall", "typeOperatorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirTypeOperatorCall;", "addTypeOperatorStatements", "Lorg/jetbrains/kotlin/fir/resolve/dfa/MutableFlow;", "exitComparisonExpressionCall", "comparisonExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirComparisonExpression;", "enterEqualityOperatorCall", "exitEqualityOperatorLhs", "exitEqualityOperatorCall", "equalityOperatorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirEqualityOperatorCall;", "callCompleted", "processEqConst", "operand", "const", "Lorg/jetbrains/kotlin/fir/expressions/FirLiteralExpression;", "isEq", "processEqNull", "lhsExitFlow", "Lorg/jetbrains/kotlin/fir/resolve/dfa/PersistentFlow;", "processEq", "leftOperand", "rightOperand", "operation", "Lorg/jetbrains/kotlin/fir/expressions/FirOperation;", "enterJump", "jump", "Lorg/jetbrains/kotlin/fir/expressions/FirJump;", "exitJump", "enterCheckNotNullCall", "exitCheckNotNullCall", "checkNotNullCall", "Lorg/jetbrains/kotlin/fir/expressions/FirCheckNotNullCall;", "enterWhenExpression", "whenExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;", "enterWhenBranchCondition", "whenBranch", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenBranch;", "mergeWhenBranchEntryFlow", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "exitWhenBranchCondition", "exitWhenBranchResult", "exitWhenExpression", "exitWhenSubjectExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenSubjectExpression;", "enterWhileLoop", "loop", "Lorg/jetbrains/kotlin/fir/expressions/FirLoop;", "exitWhileLoopCondition", "exitWhileLoop", "processWhileLoopExit", ModuleXmlParser.PATH, "Lorg/jetbrains/kotlin/fir/resolve/dfa/FlowPath;", "node", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LoopExitNode;", "conditionEnterNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LoopConditionEnterNode;", "reassigned", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "processLoopExit", "conditionExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LoopConditionExitNode;", "enterRepeatableStatement", "enterDoWhileLoop", "enterDoWhileLoopCondition", "exitDoWhileLoop", "enterTryExpression", "tryExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirTryExpression;", "exitTryMainBlock", "enterCatchClause", "catch", "Lorg/jetbrains/kotlin/fir/expressions/FirCatch;", "exitCatchClause", "enterFinallyBlock", "exitFinallyBlock", "exitTryExpression", "exitQualifiedAccessExpression", "qualifiedAccessExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "exitSmartCastExpression", "smartCastExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirSmartCastExpression;", "enterSafeCallAfterNullCheck", "safeCall", "Lorg/jetbrains/kotlin/fir/expressions/FirSafeCallExpression;", "exitSafeCall", "exitResolvedQualifierNode", "resolvedQualifier", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "enterCallArguments", K2JsArgumentConstants.CALL, "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "arguments", Argument.Delimiters.none, "exitCallArguments", "exitCallExplicitReceiver", "enterFunctionCall", "functionCall", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "exitFunctionCall", "exitDelegatedConstructorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirDelegatedConstructorCall;", "enterStringConcatenationCall", "exitStringConcatenationCall", "Lorg/jetbrains/kotlin/fir/expressions/FirStringConcatenationCall;", "orderedArguments", Argument.Delimiters.none, "callee", "(Lorg/jetbrains/kotlin/fir/expressions/FirStatement;Lorg/jetbrains/kotlin/fir/declarations/FirFunction;)[Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "processConditionalContract", "qualifiedAccess", "callArgsExit", "targetLambdaArgument", "processBackingFieldAccess", "getSubstitutor", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "originalFunction", "exitLiteralExpression", "literalExpression", "enterLocalVariableDeclaration", "exitLocalVariableDeclaration", "hadExplicitType", "exitVariableAssignment", "assignment", "Lorg/jetbrains/kotlin/fir/expressions/FirVariableAssignment;", "exitVariableInitialization", "initializer", "assignmentLhs", "hasExplicitType", "exitThrowExceptionNode", "throwExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirThrowExpression;", "enterBooleanOperatorExpression", "booleanOperatorExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirBooleanOperatorExpression;", "exitLeftBooleanOperatorExpressionArgument", "exitBooleanOperatorExpression", "mergeBooleanLogicOperatorFlow", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/BooleanOperatorExitNode;", "exitBooleanNot", "enterAnnotation", "exitAnnotation", "enterInitBlock", "initBlock", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousInitializer;", "exitInitBlock", "enterContractDescription", "exitContractDescription", "enterElvis", "elvisExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirElvisExpression;", "exitElvisLhs", "exitElvis", "isLhsNotNull", "exitCallableReference", "callableReferenceAccess", "Lorg/jetbrains/kotlin/fir/expressions/FirCallableReferenceAccess;", "exitGetClassCall", "getClassCall", "Lorg/jetbrains/kotlin/fir/expressions/FirGetClassCall;", "value", "currentSmartCastPosition", "getCurrentSmartCastPosition", "()Lorg/jetbrains/kotlin/fir/resolve/dfa/Flow;", "buildDefaultFlow", "builder", "buildAlternateFlow", "Lorg/jetbrains/kotlin/fir/resolve/dfa/FlowPath$CfgEdge;", "mergeIncomingFlow", "propagateAlternateFlows", "createAlternateFlows", "getFlow", "completePostponedNodes", "resetSmartCastPosition", "resetSmartCastPositionTo", "isSameValueIn", "other", "original", "addImplication", "statement", "Lorg/jetbrains/kotlin/fir/resolve/dfa/Implication;", "addTypeStatement", "addAllStatements", "statements", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/dfa/TypeStatements;", "addAllConditionally", "condition", "Lorg/jetbrains/kotlin/fir/resolve/dfa/OperationStatement;", "from", "getTypeStatementsNotInheritedFrom", "parent", "commitOperationStatement", "getVariable", "createReal", "getVariableWithoutUnwrappingAlias", "getVariableIfUsed", "getVariableIfUsedOrReal", "getOrCreateVariable", "getRealVariableWithoutUnwrappingAlias", "unwrapVariableIfStable", "getLocal", CoroutineCodegenUtilKt.SUSPEND_FUNCTION_CREATE_METHOD_NAME, "Companion", "SmartCastStatement", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirDataFlowAnalyzer implements SessionHolder {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final ConeClassLikeType any;
    private final FirAbstractBodyResolveTransformer.BodyResolveTransformerComponents components;
    private final DataFlowAnalyzerContext context;
    private Flow currentSmartCastPosition;
    private final ConeClassLikeType nullableNothing;

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B3\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003\u0012\u0006\u0010\t\u001a\u00020\u0006¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0006HÆ\u0003J\u000f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\b0\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0006HÆ\u0003J=\u0010\u0016\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00032\b\b\u0002\u0010\t\u001a\u00020\u0006HÆ\u0001J\u0014\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001a\u001a\u00020\u001bHÖ\u0081\u0004J\n\u0010\u001c\u001a\u00020\u001dHÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000f¨\u0006\u001e"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/dfa/FirDataFlowAnalyzer$SmartCastStatement;", Argument.Delimiters.none, "upperTypes", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "upperTypesStability", "Lorg/jetbrains/kotlin/types/SmartcastStability;", "lowerTypes", "Lorg/jetbrains/kotlin/fir/DfaType;", "lowerTypesStability", "<init>", "(Ljava/util/Set;Lorg/jetbrains/kotlin/types/SmartcastStability;Ljava/util/Set;Lorg/jetbrains/kotlin/types/SmartcastStability;)V", "getUpperTypes", "()Ljava/util/Set;", "getUpperTypesStability", "()Lorg/jetbrains/kotlin/types/SmartcastStability;", "getLowerTypes", "getLowerTypesStability", "component1", "component2", "component3", "component4", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class SmartCastStatement {
        private final Set<DfaType> lowerTypes;
        private final SmartcastStability lowerTypesStability;
        private final Set<ConeKotlinType> upperTypes;
        private final SmartcastStability upperTypesStability;

        /* JADX WARN: Multi-variable type inference failed */
        public SmartCastStatement(Set<? extends ConeKotlinType> set, SmartcastStability smartcastStability, Set<? extends DfaType> set2, SmartcastStability smartcastStability2) {
            set.getClass();
            smartcastStability.getClass();
            set2.getClass();
            smartcastStability2.getClass();
            this.upperTypes = set;
            this.upperTypesStability = smartcastStability;
            this.lowerTypes = set2;
            this.lowerTypesStability = smartcastStability2;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ SmartCastStatement copy$default(SmartCastStatement smartCastStatement, Set set, SmartcastStability smartcastStability, Set set2, SmartcastStability smartcastStability2, int i, Object obj) {
            if ((i & 1) != 0) {
                set = smartCastStatement.upperTypes;
            }
            if ((i & 2) != 0) {
                smartcastStability = smartCastStatement.upperTypesStability;
            }
            if ((i & 4) != 0) {
                set2 = smartCastStatement.lowerTypes;
            }
            if ((i & 8) != 0) {
                smartcastStability2 = smartCastStatement.lowerTypesStability;
            }
            return smartCastStatement.copy(set, smartcastStability, set2, smartcastStability2);
        }

        public final Set<ConeKotlinType> component1() {
            return this.upperTypes;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final SmartcastStability getUpperTypesStability() {
            return this.upperTypesStability;
        }

        public final Set<DfaType> component3() {
            return this.lowerTypes;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final SmartcastStability getLowerTypesStability() {
            return this.lowerTypesStability;
        }

        public final SmartCastStatement copy(Set<? extends ConeKotlinType> upperTypes, SmartcastStability upperTypesStability, Set<? extends DfaType> lowerTypes, SmartcastStability lowerTypesStability) {
            upperTypes.getClass();
            upperTypesStability.getClass();
            lowerTypes.getClass();
            lowerTypesStability.getClass();
            return new SmartCastStatement(upperTypes, upperTypesStability, lowerTypes, lowerTypesStability);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SmartCastStatement)) {
                return false;
            }
            SmartCastStatement smartCastStatement = (SmartCastStatement) other;
            return Intrinsics.areEqual(this.upperTypes, smartCastStatement.upperTypes) && this.upperTypesStability == smartCastStatement.upperTypesStability && Intrinsics.areEqual(this.lowerTypes, smartCastStatement.lowerTypes) && this.lowerTypesStability == smartCastStatement.lowerTypesStability;
        }

        public final Set<DfaType> getLowerTypes() {
            return this.lowerTypes;
        }

        public final SmartcastStability getLowerTypesStability() {
            return this.lowerTypesStability;
        }

        public final Set<ConeKotlinType> getUpperTypes() {
            return this.upperTypes;
        }

        public final SmartcastStability getUpperTypesStability() {
            return this.upperTypesStability;
        }

        public int hashCode() {
            return (((((this.upperTypes.hashCode() * 31) + this.upperTypesStability.hashCode()) * 31) + this.lowerTypes.hashCode()) * 31) + this.lowerTypesStability.hashCode();
        }

        public String toString() {
            return "SmartCastStatement(upperTypes=" + this.upperTypes + ", upperTypesStability=" + this.upperTypesStability + ", lowerTypes=" + this.lowerTypes + ", lowerTypesStability=" + this.lowerTypesStability + ')';
        }
    }

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[FirOperation.values().length];
            try {
                iArr[FirOperation.IS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[FirOperation.NOT_IS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[FirOperation.AS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[FirOperation.SAFE_AS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[Operation.values().length];
            try {
                iArr2[Operation.EqTrue.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[Operation.EqFalse.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public FirDataFlowAnalyzer(FirAbstractBodyResolveTransformer.BodyResolveTransformerComponents bodyResolveTransformerComponents, DataFlowAnalyzerContext dataFlowAnalyzerContext) {
        bodyResolveTransformerComponents.getClass();
        dataFlowAnalyzerContext.getClass();
        this.components = bodyResolveTransformerComponents;
        this.context = dataFlowAnalyzerContext;
        this.any = bodyResolveTransformerComponents.getSession().getBuiltinTypes().getAnyType().getConeType();
        this.nullableNothing = bodyResolveTransformerComponents.getSession().getBuiltinTypes().getNullableNothingType().getConeType();
    }

    public static Unit A(FirDataFlowAnalyzer firDataFlowAnalyzer, FirSafeCallExpression firSafeCallExpression, FlowPath flowPath, MutableFlow mutableFlow) {
        flowPath.getClass();
        mutableFlow.getClass();
        DataFlowVariable variableIfUsedOrReal = firDataFlowAnalyzer.getVariableIfUsedOrReal(mutableFlow, firSafeCallExpression.getReceiver());
        if (variableIfUsedOrReal == null) {
            return Unit.INSTANCE;
        }
        firDataFlowAnalyzer.commitOperationStatement(mutableFlow, ModelKt.notEq(variableIfUsedOrReal, null));
        return Unit.INSTANCE;
    }

    public static Unit B(FirDataFlowAnalyzer firDataFlowAnalyzer, FirElvisExpression firElvisExpression, FlowPath flowPath, MutableFlow mutableFlow) {
        flowPath.getClass();
        mutableFlow.getClass();
        DataFlowVariable variableIfUsedOrReal = firDataFlowAnalyzer.getVariableIfUsedOrReal(mutableFlow, firElvisExpression.getLhs());
        if (variableIfUsedOrReal == null) {
            return Unit.INSTANCE;
        }
        firDataFlowAnalyzer.commitOperationStatement(mutableFlow, ModelKt.notEq(variableIfUsedOrReal, null));
        return Unit.INSTANCE;
    }

    public static Implication C(boolean z, Implication implication) {
        implication.getClass();
        if (z || implication.getCondition().getOperation() == Operation.EqNull || implication.getCondition().getOperation() == Operation.NotEqNull) {
            return implication;
        }
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static Unit D(FunctionCallExitNode functionCallExitNode, FirDataFlowAnalyzer firDataFlowAnalyzer, FirFunctionCall firFunctionCall, FlowPath flowPath, MutableFlow mutableFlow) throws KotlinIllegalArgumentExceptionWithAttachments {
        flowPath.getClass();
        mutableFlow.getClass();
        Iterator<T> it = functionCallExitNode.getPreviousNodes().iterator();
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
            if (((CFGNode) next) instanceof FunctionCallEnterNode) {
                if (!z) {
                    z = true;
                    obj = next;
                }
            }
            obj = null;
            break;
        }
        CFGNode cFGNode = (CFGNode) obj;
        processConditionalContract$default(firDataFlowAnalyzer, mutableFlow, firFunctionCall, cFGNode != null ? cFGNode.getFlow() : null, null, 8, null);
        return Unit.INSTANCE;
    }

    public static Unit E(FlowPath flowPath, MutableFlow mutableFlow) {
        flowPath.getClass();
        mutableFlow.getClass();
        return Unit.INSTANCE;
    }

    public static Unit F(FirDataFlowAnalyzer firDataFlowAnalyzer, Set set, FlowPath flowPath, MutableFlow mutableFlow) {
        flowPath.getClass();
        mutableFlow.getClass();
        firDataFlowAnalyzer.enterRepeatableStatement(mutableFlow, set);
        return Unit.INSTANCE;
    }

    public static RealVariable G(FirDataFlowAnalyzer firDataFlowAnalyzer, Flow flow, RealVariable realVariable) {
        realVariable.getClass();
        return firDataFlowAnalyzer.unwrapVariableIfStable(flow, realVariable);
    }

    private final void addAllConditionally(MutableFlow mutableFlow, OperationStatement operationStatement, Map<DataFlowVariable, ? extends TypeStatement> map) {
        Iterator<T> it = map.values().iterator();
        while (it.hasNext()) {
            addImplication(mutableFlow, ModelKt.implies(operationStatement, (TypeStatement) it.next()));
        }
    }

    private final void addAllStatements(MutableFlow mutableFlow, Map<DataFlowVariable, ? extends TypeStatement> map) {
        Iterator<T> it = map.values().iterator();
        while (it.hasNext()) {
            addTypeStatement(mutableFlow, (TypeStatement) it.next());
        }
    }

    private final void addImplication(MutableFlow mutableFlow, Implication implication) {
        getLogicSystem().addImplication(mutableFlow, implication);
    }

    private final void addTypeOperatorStatements(MutableFlow flow, FirTypeOperatorCall typeOperatorCall) {
        List<FirRegularClassSymbol> complementarySymbols;
        ConeKotlinType coneType = FirTypeUtilsKt.getConeType(typeOperatorCall.getConversionTypeRef());
        DataFlowVariable variableIfUsedOrReal = getVariableIfUsedOrReal(flow, (FirExpression) CollectionsKt.first(typeOperatorCall.getArgumentList().getArguments()));
        if (variableIfUsedOrReal == null) {
            return;
        }
        FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(this, FirTypeUtilsKt.getConeType(typeOperatorCall.getConversionTypeRef()));
        if (regularClassSymbol == null || (complementarySymbols = DeclarationUtilsKt.getComplementarySymbols(this, regularClassSymbol)) == null || complementarySymbols.isEmpty()) {
            complementarySymbols = null;
        }
        FirOperation operation = typeOperatorCall.getOperation();
        int i = WhenMappings.$EnumSwitchMapping$0[operation.ordinal()];
        if (i == 1 || i == 2) {
            boolean z = operation == FirOperation.IS;
            if (Intrinsics.areEqual(coneType, this.nullableNothing)) {
                processEqNull$default(this, flow, typeOperatorCall, (FirExpression) CollectionsKt.first(typeOperatorCall.getArgumentList().getArguments()), z, null, 16, null);
                return;
            }
            boolean z2 = z;
            if (Intrinsics.areEqual(coneType, this.any)) {
                processEqNull$default(this, flow, typeOperatorCall, (FirExpression) CollectionsKt.first(typeOperatorCall.getArgumentList().getArguments()), !z2, null, 16, null);
                return;
            }
            SyntheticVariable syntheticVariable = new SyntheticVariable(typeOperatorCall);
            if (ModelKt.isReal(variableIfUsedOrReal)) {
                addImplication(flow, ModelKt.implies(ModelKt.eq(syntheticVariable, z2), ModelKt.typeEq(variableIfUsedOrReal, coneType)));
                addImplication(flow, ModelKt.implies(ModelKt.eq(syntheticVariable, !z2), ModelKt.typeNotEq(variableIfUsedOrReal, coneType)));
                if (complementarySymbols != null) {
                    addImplication(flow, ModelKt.implies(ModelKt.eq(syntheticVariable, z2), ModelKt.valueNotEq((RealVariable) variableIfUsedOrReal, complementarySymbols)));
                }
            }
            if (!TypeUtilsKt.canBeNull$default(coneType, this.components.getSession(), false, null, 6, null)) {
                addImplication(flow, ModelKt.implies(ModelKt.eq(syntheticVariable, z2), ModelKt.notEq(variableIfUsedOrReal, null)));
                return;
            } else {
                if (ConeTypeUtilsKt.isMarkedNullable(coneType)) {
                    addImplication(flow, ModelKt.implies(ModelKt.eq(syntheticVariable, !z2), ModelKt.notEq(variableIfUsedOrReal, null)));
                    return;
                }
                return;
            }
        }
        if (i == 3) {
            if (ModelKt.isReal(variableIfUsedOrReal)) {
                addTypeStatement(flow, ModelKt.typeEq(variableIfUsedOrReal, coneType));
                if (complementarySymbols != null) {
                    addTypeStatement(flow, ModelKt.valueNotEq((RealVariable) variableIfUsedOrReal, complementarySymbols));
                }
            }
            if (!TypeUtilsKt.canBeNull$default(coneType, this.components.getSession(), false, null, 6, null)) {
                commitOperationStatement(flow, ModelKt.notEq(variableIfUsedOrReal, null));
                return;
            }
            SyntheticVariable syntheticVariable2 = new SyntheticVariable(typeOperatorCall);
            addImplication(flow, ModelKt.implies(ModelKt.notEq(syntheticVariable2, null), ModelKt.notEq(variableIfUsedOrReal, null)));
            addImplication(flow, ModelKt.implies(ModelKt.eq(syntheticVariable2, (Void) null), ModelKt.eq(variableIfUsedOrReal, (Void) null)));
            return;
        }
        if (i != 4) {
            g33.a();
            return;
        }
        SyntheticVariable syntheticVariable3 = new SyntheticVariable(typeOperatorCall);
        addImplication(flow, ModelKt.implies(ModelKt.notEq(syntheticVariable3, null), ModelKt.notEq(variableIfUsedOrReal, null)));
        if (ModelKt.isReal(variableIfUsedOrReal)) {
            addImplication(flow, ModelKt.implies(ModelKt.notEq(syntheticVariable3, null), ModelKt.typeEq(variableIfUsedOrReal, coneType)));
            if (complementarySymbols != null) {
                addImplication(flow, ModelKt.implies(ModelKt.notEq(syntheticVariable3, null), ModelKt.valueNotEq((RealVariable) variableIfUsedOrReal, complementarySymbols)));
            }
        }
    }

    private final void addTypeStatement(MutableFlow mutableFlow, TypeStatement typeStatement) {
        TypeStatement typeStatementAddTypeStatement = getLogicSystem().addTypeStatement(mutableFlow, typeStatement);
        if (typeStatementAddTypeStatement == null) {
            return;
        }
        DataFlowVariable variable = typeStatementAddTypeStatement.getVariable();
        if ((variable instanceof RealVariable) && ((RealVariable) variable).getIsImplicit() && mutableFlow == this.currentSmartCastPosition) {
            implicitUpdated(typeStatementAddTypeStatement);
        }
    }

    public static Unit b(FlowPath flowPath, MutableFlow mutableFlow) {
        flowPath.getClass();
        mutableFlow.getClass();
        return Unit.INSTANCE;
    }

    private final MutableFlow buildAlternateFlow(CFGNode<?> cFGNode, FlowPath.CfgEdge cfgEdge, Function2<? super FlowPath, ? super MutableFlow, Unit> function2) {
        boolean z = cFGNode instanceof FinallyBlockEnterNode;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (CFGNode<?> cFGNode2 : cFGNode.getPreviousNodes()) {
            Edge edgeEdgeFrom = cFGNode.edgeFrom(cFGNode2);
            if (CFGNodeKt.usedInDfa(cFGNode, edgeEdgeFrom)) {
                if (z) {
                    FinallyBlockEnterNode finallyBlockEnterNode = (FinallyBlockEnterNode) cFGNode;
                    if (!Intrinsics.areEqual(cfgEdge.getFir(), finallyBlockEnterNode.getFir()) || Intrinsics.areEqual(edgeEdgeFrom.getLabel(), cfgEdge.getLabel())) {
                        if (Intrinsics.areEqual(cfgEdge.getFir(), finallyBlockEnterNode.getFir()) || Intrinsics.areEqual(edgeEdgeFrom.getLabel(), NormalPath.INSTANCE)) {
                        }
                    }
                }
                PersistentFlow alternateFlow = cFGNode2.getAlternateFlow(cfgEdge);
                if (alternateFlow == null) {
                    alternateFlow = cFGNode2.getFlow();
                }
                arrayList.add(alternateFlow);
                if (!Intrinsics.areEqual(edgeEdgeFrom.getLabel(), PostponedPath.INSTANCE)) {
                    arrayList2.add(alternateFlow);
                }
            }
        }
        MutableFlow mutableFlowJoinFlow = getLogicSystem().joinFlow(arrayList, arrayList2, cFGNode.isUnion());
        function2.invoke(cfgEdge, mutableFlowJoinFlow);
        return mutableFlowJoinFlow;
    }

    private final MutableFlow buildDefaultFlow(CFGNode<?> cFGNode, Function2<? super FlowPath, ? super MutableFlow, Unit> function2) {
        PersistentFlow flow;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (CFGNode<?> cFGNode2 : cFGNode.getPreviousNodes()) {
            Edge edgeEdgeFrom = cFGNode.edgeFrom(cFGNode2);
            if (CFGNodeKt.usedInDfa(cFGNode, edgeEdgeFrom)) {
                if ((cFGNode2 instanceof MergePostponedLambdaExitsNode) && !((MergePostponedLambdaExitsNode) cFGNode2).getFlowInitialized()) {
                    mergeIncomingFlow$default(this, cFGNode2, null, 1, null);
                }
                if (cFGNode2 instanceof FinallyBlockExitNode) {
                    FinallyBlockExitNode finallyBlockExitNode = (FinallyBlockExitNode) cFGNode2;
                    flow = finallyBlockExitNode.getAlternateFlow(new FlowPath.CfgEdge(edgeEdgeFrom.getLabel(), finallyBlockExitNode.getFir()));
                    if (flow == null) {
                        flow = finallyBlockExitNode.getFlow();
                    }
                } else {
                    flow = cFGNode2.getFlow();
                }
                arrayList.add(flow);
                if (!Intrinsics.areEqual(edgeEdgeFrom.getLabel(), PostponedPath.INSTANCE)) {
                    arrayList2.add(flow);
                }
            }
        }
        MutableFlow mutableFlowJoinFlow = getLogicSystem().joinFlow(arrayList, arrayList2, cFGNode.isUnion());
        if (Intrinsics.areEqual(getGraphBuilder().getLastNodeOrNull(), cFGNode)) {
            Flow flow2 = this.currentSmartCastPosition;
            if (flow2 == null || !Intrinsics.areEqual(flow2, CollectionsKt.singleOrNull(arrayList))) {
                resetSmartCastPositionTo(mutableFlowJoinFlow);
            } else {
                this.currentSmartCastPosition = mutableFlowJoinFlow;
            }
        }
        function2.invoke(FlowPath.Default.INSTANCE, mutableFlowJoinFlow);
        return mutableFlowJoinFlow;
    }

    public static Unit c(FirDataFlowAnalyzer firDataFlowAnalyzer, FirBooleanOperatorExpression firBooleanOperatorExpression, FlowPath flowPath, MutableFlow mutableFlow) {
        flowPath.getClass();
        mutableFlow.getClass();
        DataFlowVariable variableIfUsed = firDataFlowAnalyzer.getVariableIfUsed(mutableFlow, firBooleanOperatorExpression.getLeftOperand());
        if (variableIfUsed == null) {
            return Unit.INSTANCE;
        }
        firDataFlowAnalyzer.commitOperationStatement(mutableFlow, ModelKt.eq(variableIfUsed, !(firBooleanOperatorExpression.getKind() != LogicOperationKind.AND)));
        return Unit.INSTANCE;
    }

    private final void commitOperationStatement(MutableFlow mutableFlow, OperationStatement operationStatement) {
        addAllStatements(mutableFlow, getLogicSystem().approveOperationStatement(mutableFlow, operationStatement, true));
    }

    private final void completePostponedNodes(ControlFlowGraph controlFlowGraph) {
        Iterator<ControlFlowGraph> it = controlFlowGraph.getSubGraphs().iterator();
        while (it.hasNext()) {
            completePostponedNodes(it.next());
        }
        for (CFGNode<?> cFGNode : controlFlowGraph.getNodes()) {
            if (!(cFGNode instanceof ClassExitNode) && !cFGNode.getFlowInitialized()) {
                mergeIncomingFlow$default(this, cFGNode, null, 1, null);
            }
        }
    }

    private final void createAlternateFlows(CFGNode<?> cFGNode, Function2<? super FlowPath, ? super MutableFlow, Unit> function2) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<CFGNode<?>> it = cFGNode.getPreviousNodes().iterator();
        while (it.hasNext()) {
            Edge edgeEdgeFrom = cFGNode.edgeFrom(it.next());
            if (!Intrinsics.areEqual(edgeEdgeFrom.getLabel(), UncaughtExceptionPath.INSTANCE) && CFGNodeKt.usedInDfa(cFGNode, edgeEdgeFrom) && linkedHashSet.add(edgeEdgeFrom.getLabel())) {
                FlowPath.CfgEdge cfgEdge = new FlowPath.CfgEdge(edgeEdgeFrom.getLabel(), cFGNode.getFir());
                cFGNode.addAlternateFlow(cfgEdge, buildAlternateFlow(cFGNode, cfgEdge, function2).freeze());
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void createAlternateFlows$default(FirDataFlowAnalyzer firDataFlowAnalyzer, CFGNode cFGNode, Function2 function2, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: createAlternateFlows");
            return;
        }
        if ((i & 1) != 0) {
            function2 = new Function2() { // from class: a15
                public final Object invoke(Object obj2, Object obj3) {
                    return FirDataFlowAnalyzer.b((FlowPath) obj2, (MutableFlow) obj3);
                }
            };
        }
        firDataFlowAnalyzer.createAlternateFlows(cFGNode, function2);
    }

    public static Unit d(FirProperty firProperty, FirDataFlowAnalyzer firDataFlowAnalyzer, boolean z, FlowPath flowPath, MutableFlow mutableFlow) {
        flowPath.getClass();
        mutableFlow.getClass();
        FirExpression initializer = firProperty.getInitializer();
        if (initializer == null) {
            return Unit.INSTANCE;
        }
        firDataFlowAnalyzer.exitVariableInitialization(mutableFlow, initializer, firProperty, null, z);
        return Unit.INSTANCE;
    }

    public static Unit e(FirCodeFragment firCodeFragment, FirDataFlowAnalyzer firDataFlowAnalyzer, FlowPath flowPath, MutableFlow mutableFlow) {
        flowPath.getClass();
        mutableFlow.getClass();
        FirCodeFragmentContext codeFragmentContext = org.jetbrains.kotlin.fir.resolve.DeclarationUtilsKt.getCodeFragmentContext(firCodeFragment);
        Map<RealVariable, Set<ConeKotlinType>> smartCasts = codeFragmentContext != null ? codeFragmentContext.getSmartCasts() : null;
        if (smartCasts == null) {
            smartCasts = MapsKt.emptyMap();
        }
        for (Map.Entry<RealVariable, Set<ConeKotlinType>> entry : smartCasts.entrySet()) {
            firDataFlowAnalyzer.addTypeStatement(mutableFlow, new PersistentTypeStatement(firDataFlowAnalyzer.getVariableStorage().remember(entry.getKey()), ExtensionsKt.toPersistentSet(entry.getValue()), ExtensionsKt.persistentSetOf()));
        }
        return Unit.INSTANCE;
    }

    private final void enterRepeatableStatement(MutableFlow flow, Set<? extends FirPropertySymbol> reassigned) {
        Iterator<? extends FirPropertySymbol> it = reassigned.iterator();
        while (it.hasNext()) {
            RealVariable local = getLocal(it.next(), false);
            if (local != null) {
                getLogicSystem().recordNewAssignment(flow, local, this.context.newAssignmentIndex());
            }
        }
    }

    private final void exitBooleanNot(MutableFlow flow, FirFunctionCall expression) {
        FirExpression dispatchReceiver;
        Candidate candidate = CandidateFactoryKt.candidate(expression);
        if (candidate == null || (dispatchReceiver = candidate.dispatchReceiverExpression()) == null) {
            dispatchReceiver = expression.getDispatchReceiver();
            dispatchReceiver.getClass();
        }
        DataFlowVariable variableIfUsed = getVariableIfUsed(flow, dispatchReceiver);
        if (variableIfUsed == null) {
            return;
        }
        final SyntheticVariable syntheticVariable = new SyntheticVariable(expression);
        getLogicSystem().translateVariableFromConditionInStatements(flow, variableIfUsed, syntheticVariable, new Function1() { // from class: f15
            public final Object invoke(Object obj) {
                return FirDataFlowAnalyzer.m(syntheticVariable, (Implication) obj);
            }
        });
    }

    private final void exitVariableInitialization(MutableFlow flow, FirExpression initializer, FirProperty property, FirExpression assignmentLhs, boolean hasExplicitType) {
        RealVariable local;
        boolean z = true;
        if (assignmentLhs != null) {
            DataFlowVariable variableWithoutUnwrappingAlias = getVariableWithoutUnwrappingAlias(flow, assignmentLhs, true);
            local = variableWithoutUnwrappingAlias instanceof RealVariable ? (RealVariable) variableWithoutUnwrappingAlias : null;
            if (local == null) {
                return;
            }
        } else {
            local = getLocal(property.getSymbol(), true);
            if (local == null) {
                return;
            }
        }
        boolean z2 = assignmentLhs != null;
        if (z2) {
            getLogicSystem().recordNewAssignment(flow, local, this.context.newAssignmentIndex());
        }
        SmartcastStability stability = local.getStability(flow, this.components.getSession());
        SmartcastStability smartcastStability = SmartcastStability.STABLE_VALUE;
        if (stability == smartcastStability || stability == SmartcastStability.CAPTURED_VARIABLE) {
            DataFlowVariable variableIfUsedOrReal = getVariableIfUsedOrReal(flow, initializer);
            if (!hasExplicitType && (variableIfUsedOrReal instanceof RealVariable) && (FirExpressionUtilKt.isImplicitWhenSubjectVariable(property) || getStability(variableIfUsedOrReal, flow, null) == smartcastStability)) {
                getLogicSystem().addLocalVariableAlias(flow, local, (RealVariable) variableIfUsedOrReal);
            } else if (variableIfUsedOrReal == null || (FirPropertyBodyResolveStateKt.isEffectivelyLocal(property) && property.getIsVar())) {
                if (!z2 && ((!Intrinsics.areEqual(DeclarationAttributesKt.isReplSnippetDeclaration(property), Boolean.TRUE) || hasExplicitType) && (!(initializer instanceof FirSmartCastExpression) || Intrinsics.areEqual(flow.unwrapVariable(local).getOriginalType(), FirTypeUtilsKt.getResolvedType(initializer))))) {
                    z = false;
                }
                z2 = z;
            } else {
                final boolean zIsEnabled = LanguageVersionUtilsKt.isEnabled(this, LanguageFeature.DfaBooleanVariables);
                getLogicSystem().translateVariableFromConditionInStatements(flow, variableIfUsedOrReal, local, new Function1() { // from class: j05
                    public final Object invoke(Object obj) {
                        return FirDataFlowAnalyzer.C(zIsEnabled, (Implication) obj);
                    }
                });
            }
        }
        if (z2) {
            addTypeStatement(flow, ModelKt.typeEq(flow.unwrapVariable(local), FirTypeUtilsKt.getResolvedType(initializer)));
        }
    }

    public static Map f(FirDataFlowAnalyzer firDataFlowAnalyzer, MutableFlow mutableFlow, OperationStatement operationStatement) {
        operationStatement.getClass();
        return firDataFlowAnalyzer.getLogicSystem().approveOperationStatement(mutableFlow, operationStatement, true);
    }

    public static Unit g(FirLoop firLoop, FirDataFlowAnalyzer firDataFlowAnalyzer, FlowPath flowPath, MutableFlow mutableFlow) {
        flowPath.getClass();
        mutableFlow.getClass();
        if (ConeBuiltinTypeUtilsKt.isBoolean(FirTypeUtilsKt.getResolvedType(firLoop.getCondition()))) {
            DataFlowVariable variableIfUsed = firDataFlowAnalyzer.getVariableIfUsed(mutableFlow, firLoop.getCondition());
            if (variableIfUsed == null) {
                return Unit.INSTANCE;
            }
            firDataFlowAnalyzer.commitOperationStatement(mutableFlow, ModelKt.eq(variableIfUsed, true));
        }
        return Unit.INSTANCE;
    }

    private final PersistentFlow getFlow(CFGNode<?> cFGNode, FlowPath flowPath) {
        if (Intrinsics.areEqual(flowPath, FlowPath.Default.INSTANCE)) {
            return cFGNode.getFlow();
        }
        PersistentFlow alternateFlow = cFGNode.getAlternateFlow(flowPath);
        if (alternateFlow != null) {
            return alternateFlow;
        }
        w04.a("no alternate flow for ", flowPath);
        return null;
    }

    private final ControlFlowGraphBuilder getGraphBuilder() {
        return this.context.getGraphBuilder();
    }

    private final RealVariable getLocal(FirPropertySymbol symbol, boolean create) {
        RealVariable realVariable;
        ConeSimpleKotlinType dispatchReceiverType = symbol.getDispatchReceiverType();
        if (dispatchReceiverType != null) {
            FirClassifierSymbol<?> symbol2 = ToSymbolUtilsKt.toSymbol(this, dispatchReceiverType);
            if (symbol2 == null) {
                return null;
            }
            RealVariable realVariable2 = new RealVariable(symbol2, true, null, null, dispatchReceiverType);
            if (!create) {
                realVariable2 = getVariableStorage().getKnown(realVariable2);
            }
            if (realVariable2 == null) {
                return null;
            }
            realVariable = realVariable2;
        } else {
            realVariable = null;
        }
        RealVariable realVariable3 = new RealVariable(symbol, false, realVariable, null, this.components.getReturnTypeCalculator().tryCalculateReturnType(symbol).getConeType());
        VariableStorage variableStorage = getVariableStorage();
        return create ? variableStorage.remember(realVariable3) : variableStorage.getKnown(realVariable3);
    }

    private final RealVariable getRealVariableWithoutUnwrappingAlias(Flow flow, FirExpression firExpression) {
        DataFlowVariable variableWithoutUnwrappingAlias = getVariableWithoutUnwrappingAlias(flow, firExpression, false);
        if (variableWithoutUnwrappingAlias instanceof RealVariable) {
            return (RealVariable) variableWithoutUnwrappingAlias;
        }
        return null;
    }

    private final SmartcastStability getStability(DataFlowVariable dataFlowVariable, Flow flow, Set<? extends ConeKotlinType> set) {
        if (!(dataFlowVariable instanceof RealVariable)) {
            return SmartcastStability.STABLE_VALUE;
        }
        RealVariable realVariable = (RealVariable) dataFlowVariable;
        SmartcastStability stability = realVariable.getStability(flow, this.components.getSession());
        return (stability != SmartcastStability.CAPTURED_VARIABLE || isUnstableLocalVar(realVariable, set)) ? stability : SmartcastStability.STABLE_VALUE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final ConeSubstitutor getSubstitutor(FirFunction callee, FirStatement qualifiedAccess, FirFunction originalFunction) {
        ConeSubstitutor coneSubstitutorSubstitutorByMap$default;
        List<FirTypeParameterRef> typeParameters = callee instanceof FirPropertyAccessor ? ((FirProperty) ((FirPropertyAccessor) callee).getPropertySymbol().getFir()).getTypeParameters() : callee.getTypeParameters();
        if (typeParameters.isEmpty() || !(qualifiedAccess instanceof FirQualifiedAccessExpression)) {
            coneSubstitutorSubstitutorByMap$default = ConeSubstitutor.Empty.INSTANCE;
        } else {
            List<Pair> listZip = CollectionsKt.zip(typeParameters, ((FirQualifiedAccessExpression) qualifiedAccess).getTypeArguments());
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listZip, 10));
            for (Pair pair : listZip) {
                arrayList.add(TuplesKt.to(((FirTypeParameterRef) pair.component1()).getSymbol(), ConeTypeProjectionKt.getType(FirTypeUtilsKt.toConeTypeProjection((FirTypeProjection) pair.component2()))));
            }
            ArrayList arrayList2 = new ArrayList();
            for (Object obj : arrayList) {
                if (((Pair) obj).getSecond() != null) {
                    arrayList2.add(obj);
                }
            }
            Map map = MapsKt.toMap(arrayList2);
            map.getClass();
            coneSubstitutorSubstitutorByMap$default = ConeSubstitutorByMapKt.substitutorByMap$default(map, this.components.getSession(), false, 4, null);
        }
        if (originalFunction == null) {
            return coneSubstitutorSubstitutorByMap$default;
        }
        List<FirTypeParameterSymbol> typeParameterSymbols = originalFunction.getSymbol().getTypeParameterSymbols();
        List<FirTypeParameterRef> list = typeParameters;
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList3.add(FirNestedClassifierScopeKt.toConeType(((FirTypeParameterRef) it.next()).getSymbol()));
        }
        return ChainedSubstitutorKt.chain(ConeSubstitutorByMapKt.substitutorByMap$default(MapsKt.toMap(CollectionsKt.zip(typeParameterSymbols, arrayList3)), this.components.getSession(), false, 4, null), coneSubstitutorSubstitutorByMap$default);
    }

    private final Map<DataFlowVariable, TypeStatement> getTypeStatementsNotInheritedFrom(MutableFlow mutableFlow, Flow flow) {
        TypeStatement typeStatement;
        Map mapCreateMapBuilder = MapsKt.createMapBuilder();
        for (DataFlowVariable dataFlowVariable : flow.getKnownVariables()) {
            if (Intrinsics.areEqual(mutableFlow.unwrapVariable(dataFlowVariable), dataFlowVariable) && (typeStatement = flow.getTypeStatement(dataFlowVariable)) != null && !Intrinsics.areEqual(typeStatement, mutableFlow.getTypeStatement(dataFlowVariable))) {
                mapCreateMapBuilder.put(dataFlowVariable, typeStatement);
            }
        }
        return MapsKt.build(mapCreateMapBuilder);
    }

    /* JADX WARN: Code duplicated, block: B:17:0x0031  */
    /* JADX WARN: Code duplicated, block: B:19:0x0038 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:20:0x0039  */
    public static /* synthetic */ SmartCastStatement getTypeUsingSmartcastInfo$default(FirDataFlowAnalyzer firDataFlowAnalyzer, FirExpression firExpression, Function2 function2, int i, Object obj) {
        TypeStatement typeStatement;
        TypeStatement typeStatement2 = null;
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: getTypeUsingSmartcastInfo");
            return null;
        }
        if ((i & 2) != 0) {
            function2 = new Function2<DataFlowVariable, TypeStatement, TypeStatement>() { // from class: org.jetbrains.kotlin.fir.resolve.dfa.FirDataFlowAnalyzer.getTypeUsingSmartcastInfo.1
                public final TypeStatement invoke(DataFlowVariable dataFlowVariable, TypeStatement typeStatement3) {
                    dataFlowVariable.getClass();
                    return typeStatement3;
                }
            };
        }
        firExpression.getClass();
        function2.getClass();
        Flow currentSmartCastPosition = firDataFlowAnalyzer.getCurrentSmartCastPosition();
        if (currentSmartCastPosition == null) {
            return null;
        }
        DataFlowVariable syntheticVariable = new SyntheticVariable(firExpression);
        TypeStatement typeStatement3 = (TypeStatement) function2.invoke(syntheticVariable, firDataFlowAnalyzer.extractTypeStatementFrom(currentSmartCastPosition, syntheticVariable));
        if (typeStatement3 == null) {
            syntheticVariable = firDataFlowAnalyzer.getVariableWithoutUnwrappingAlias(currentSmartCastPosition, firExpression, false);
            if (syntheticVariable == null) {
                return null;
            }
            typeStatement = (TypeStatement) function2.invoke(syntheticVariable, firDataFlowAnalyzer.extractTypeStatementFrom(currentSmartCastPosition, syntheticVariable));
            if (typeStatement != null && typeStatement.isNotEmpty()) {
                typeStatement2 = typeStatement;
            }
            typeStatement3 = typeStatement2;
        } else {
            if (!typeStatement3.isNotEmpty()) {
                typeStatement3 = null;
            }
            if (typeStatement3 == null) {
                syntheticVariable = firDataFlowAnalyzer.getVariableWithoutUnwrappingAlias(currentSmartCastPosition, firExpression, false);
                if (syntheticVariable == null) {
                    return null;
                }
                typeStatement = (TypeStatement) function2.invoke(syntheticVariable, firDataFlowAnalyzer.extractTypeStatementFrom(currentSmartCastPosition, syntheticVariable));
                if (typeStatement != null) {
                    typeStatement2 = typeStatement;
                }
                typeStatement3 = typeStatement2;
            }
        }
        return firDataFlowAnalyzer.buildSmartCastStatement(currentSmartCastPosition, syntheticVariable, typeStatement3);
    }

    private final DataFlowVariable getVariable(final Flow flow, FirExpression firExpression, boolean z) {
        return VariableStorage.get$default(getVariableStorage(), firExpression, z, new Function1() { // from class: h15
            public final Object invoke(Object obj) {
                return FirDataFlowAnalyzer.G(this.b, flow, (RealVariable) obj);
            }
        }, null, 8, null);
    }

    private final DataFlowVariable getVariableIfUsed(Flow flow, FirExpression firExpression) {
        DataFlowVariable variable = getVariable(flow, firExpression, false);
        if (variable != null) {
            Collection<Implication> implications = flow.getImplications(variable);
            if (!(implications == null || implications.isEmpty())) {
                return variable;
            }
        }
        return null;
    }

    private final DataFlowVariable getVariableIfUsedOrReal(Flow flow, FirExpression firExpression) {
        Collection<Implication> implications;
        DataFlowVariable variable = getVariable(flow, firExpression, true);
        if (variable == null) {
            return null;
        }
        if (ModelKt.isReal(variable) || !((implications = flow.getImplications(variable)) == null || implications.isEmpty())) {
            return variable;
        }
        return null;
    }

    private final VariableStorage getVariableStorage() {
        return this.context.getVariableStorage();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static Unit h(FirProperty firProperty, FirDataFlowAnalyzer firDataFlowAnalyzer, FirVariableAssignment firVariableAssignment, FlowPath flowPath, MutableFlow mutableFlow) throws KotlinIllegalArgumentExceptionWithAttachments {
        flowPath.getClass();
        mutableFlow.getClass();
        if (firProperty == null) {
            return Unit.INSTANCE;
        }
        if (FirPropertyBodyResolveStateKt.isEffectivelyLocal(firProperty) || firProperty.getIsVal()) {
            firDataFlowAnalyzer.exitVariableInitialization(mutableFlow, firVariableAssignment.getRValue(), firProperty, firVariableAssignment.getLValue(), false);
        } else {
            RealVariable realVariableWithoutUnwrappingAlias = firDataFlowAnalyzer.getRealVariableWithoutUnwrappingAlias(mutableFlow, firVariableAssignment.getLValue());
            if (realVariableWithoutUnwrappingAlias != null) {
                firDataFlowAnalyzer.getLogicSystem().recordNewAssignment(mutableFlow, realVariableWithoutUnwrappingAlias, firDataFlowAnalyzer.context.newAssignmentIndex());
            }
        }
        processConditionalContract$default(firDataFlowAnalyzer, mutableFlow, firVariableAssignment, null, null, 8, null);
        return Unit.INSTANCE;
    }

    public static Unit i(FirDataFlowAnalyzer firDataFlowAnalyzer, FirElvisExpression firElvisExpression, FlowPath flowPath, MutableFlow mutableFlow) {
        flowPath.getClass();
        mutableFlow.getClass();
        DataFlowVariable variableIfUsedOrReal = firDataFlowAnalyzer.getVariableIfUsedOrReal(mutableFlow, firElvisExpression.getLhs());
        if (variableIfUsedOrReal == null) {
            return Unit.INSTANCE;
        }
        firDataFlowAnalyzer.commitOperationStatement(mutableFlow, ModelKt.eq(variableIfUsedOrReal, (Void) null));
        return Unit.INSTANCE;
    }

    private final Set<DfaType> inferLowerTypesFromSymbol(FirEnumEntrySymbol symbol) {
        List<FirEnumEntrySymbol> complementarySymbols = DeclarationUtilsKt.getComplementarySymbols(this.components, symbol);
        LinkedHashSet linkedHashSet = null;
        if (complementarySymbols != null) {
            if (complementarySymbols.isEmpty()) {
                complementarySymbols = null;
            }
            if (complementarySymbols != null) {
                linkedHashSet = new LinkedHashSet();
                Iterator<T> it = complementarySymbols.iterator();
                while (it.hasNext()) {
                    linkedHashSet.add(new DfaType.Symbol((FirBasedSymbol) it.next()));
                }
            }
        }
        return linkedHashSet;
    }

    private final Set<DfaType> inferLowerTypesFromVariable(DataFlowVariable variable) {
        RealVariable realVariable = variable instanceof RealVariable ? (RealVariable) variable : null;
        FirBasedSymbol<?> symbol = realVariable != null ? realVariable.getSymbol() : null;
        FirEnumEntrySymbol firEnumEntrySymbol = symbol instanceof FirEnumEntrySymbol ? (FirEnumEntrySymbol) symbol : null;
        if (firEnumEntrySymbol == null) {
            return null;
        }
        return inferLowerTypesFromSymbol(firEnumEntrySymbol);
    }

    private final boolean isSameValueIn(PersistentFlow other, FirExpression fir, MutableFlow original) {
        RealVariable realVariableWithoutUnwrappingAlias = getRealVariableWithoutUnwrappingAlias(other, fir);
        return realVariableWithoutUnwrappingAlias == null || getLogicSystem().isSameValueIn(other, original, realVariableWithoutUnwrappingAlias);
    }

    private final boolean isUnstableLocalVar(RealVariable realVariable, Set<? extends ConeKotlinType> set) {
        RealVariable dispatchReceiver;
        return this.context.getVariableAssignmentAnalyzer().isUnstableInCurrentScope(realVariable.getSymbol().getFir(), set, this.components.getSession()) || ((dispatchReceiver = realVariable.getDispatchReceiver()) != null && isUnstableLocalVar(dispatchReceiver, null));
    }

    public static Unit j(FirTypeOperatorCall firTypeOperatorCall, FirDataFlowAnalyzer firDataFlowAnalyzer, FlowPath flowPath, MutableFlow mutableFlow) {
        flowPath.getClass();
        mutableFlow.getClass();
        if (!FirOperation.INSTANCE.getTYPES().contains(firTypeOperatorCall.getOperation())) {
            return Unit.INSTANCE;
        }
        firDataFlowAnalyzer.addTypeOperatorStatements(mutableFlow, firTypeOperatorCall);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static Unit k(FirDataFlowAnalyzer firDataFlowAnalyzer, FirQualifiedAccessExpression firQualifiedAccessExpression, FlowPath flowPath, MutableFlow mutableFlow) throws KotlinIllegalArgumentExceptionWithAttachments {
        flowPath.getClass();
        mutableFlow.getClass();
        processConditionalContract$default(firDataFlowAnalyzer, mutableFlow, firQualifiedAccessExpression, null, null, 8, null);
        firDataFlowAnalyzer.processBackingFieldAccess(mutableFlow, firQualifiedAccessExpression);
        return Unit.INSTANCE;
    }

    public static Unit l(FirDataFlowAnalyzer firDataFlowAnalyzer, LoopExitNode loopExitNode, LoopConditionExitNode loopConditionExitNode, FlowPath flowPath, MutableFlow mutableFlow) {
        flowPath.getClass();
        mutableFlow.getClass();
        firDataFlowAnalyzer.processLoopExit(mutableFlow, loopExitNode, loopConditionExitNode);
        return Unit.INSTANCE;
    }

    public static Implication m(SyntheticVariable syntheticVariable, Implication implication) {
        implication.getClass();
        int i = WhenMappings.$EnumSwitchMapping$1[implication.getCondition().getOperation().ordinal()];
        if (i == 1) {
            return ModelKt.implies(ModelKt.eq((DataFlowVariable) syntheticVariable, false), implication.getEffect());
        }
        if (i != 2) {
            return null;
        }
        return ModelKt.implies(ModelKt.eq((DataFlowVariable) syntheticVariable, true), implication.getEffect());
    }

    private final void mergeBooleanLogicOperatorFlow(final BooleanOperatorExitNode booleanOperatorExitNode) {
        mergeIncomingFlow(booleanOperatorExitNode, new Function2() { // from class: r05
            public final Object invoke(Object obj, Object obj2) {
                return FirDataFlowAnalyzer.o(this.b, booleanOperatorExitNode, (FlowPath) obj, (MutableFlow) obj2);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final Map<DataFlowVariable, TypeStatement> mergeBooleanLogicOperatorFlow$lambda$0$getStatementsWhenRightArgumentIs(DataFlowVariable dataFlowVariable, FirDataFlowAnalyzer firDataFlowAnalyzer, Map<DataFlowVariable, ? extends TypeStatement> map, PersistentFlow persistentFlow, boolean z) {
        return dataFlowVariable != null ? firDataFlowAnalyzer.getLogicSystem().andForTypeStatements(map, firDataFlowAnalyzer.getLogicSystem().approveOperationStatement(persistentFlow, ModelKt.eq(dataFlowVariable, z))) : map;
    }

    private final void mergeIncomingFlow(CFGNode<?> cFGNode, Function2<? super FlowPath, ? super MutableFlow, Unit> function2) {
        MutableFlow mutableFlowBuildDefaultFlow = buildDefaultFlow(cFGNode, function2);
        PersistentFlow persistentFlowFreeze = mutableFlowBuildDefaultFlow.freeze();
        cFGNode.setFlow(persistentFlowFreeze);
        if (this.currentSmartCastPosition == mutableFlowBuildDefaultFlow) {
            this.currentSmartCastPosition = persistentFlowFreeze;
        }
        propagateAlternateFlows(cFGNode, function2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void mergeIncomingFlow$default(FirDataFlowAnalyzer firDataFlowAnalyzer, CFGNode cFGNode, Function2 function2, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: mergeIncomingFlow");
            return;
        }
        if ((i & 1) != 0) {
            function2 = new Function2() { // from class: w05
                public final Object invoke(Object obj2, Object obj3) {
                    return FirDataFlowAnalyzer.E((FlowPath) obj2, (MutableFlow) obj3);
                }
            };
        }
        firDataFlowAnalyzer.mergeIncomingFlow(cFGNode, function2);
    }

    private final void mergeWhenBranchEntryFlow(final CFGNode<?> cFGNode) {
        mergeIncomingFlow(cFGNode, new Function2() { // from class: v05
            public final Object invoke(Object obj, Object obj2) {
                return FirDataFlowAnalyzer.z(cFGNode, this, (FlowPath) obj, (MutableFlow) obj2);
            }
        });
    }

    public static Map n(FirDataFlowAnalyzer firDataFlowAnalyzer, MutableFlow mutableFlow, Operation operation, OperationStatement operationStatement) {
        operationStatement.getClass();
        return firDataFlowAnalyzer.getLogicSystem().approveOperationStatement(mutableFlow, operationStatement, operation == null);
    }

    public static Unit o(FirDataFlowAnalyzer firDataFlowAnalyzer, BooleanOperatorExitNode booleanOperatorExitNode, FlowPath flowPath, MutableFlow mutableFlow) {
        Map<DataFlowVariable, TypeStatement> mapEmptyMap;
        Map<DataFlowVariable, TypeStatement> mapApproveOperationStatement;
        flowPath.getClass();
        mutableFlow.getClass();
        boolean zIsEnabled = LanguageVersionUtilsKt.isEnabled(firDataFlowAnalyzer, LanguageFeature.InferMoreImplicationsFromBooleanExpressions);
        boolean z = booleanOperatorExitNode.getFir().getKind() != LogicOperationKind.AND;
        PersistentFlow flow = firDataFlowAnalyzer.getFlow(booleanOperatorExitNode.getLeftOperandNode(), flowPath);
        PersistentFlow flow2 = firDataFlowAnalyzer.getFlow(booleanOperatorExitNode.getRightOperandNode(), flowPath);
        PersistentFlow persistentFlow = ConeBuiltinTypeUtilsKt.isBoolean(FirTypeUtilsKt.getResolvedType(booleanOperatorExitNode.getFir().getLeftOperand())) ? flow : null;
        DataFlowVariable variableIfUsed = persistentFlow != null ? firDataFlowAnalyzer.getVariableIfUsed(persistentFlow, booleanOperatorExitNode.getFir().getLeftOperand()) : null;
        PersistentFlow persistentFlow2 = ConeBuiltinTypeUtilsKt.isBoolean(FirTypeUtilsKt.getResolvedType(booleanOperatorExitNode.getFir().getRightOperand())) ? flow2 : null;
        DataFlowVariable variableIfUsed2 = persistentFlow2 != null ? firDataFlowAnalyzer.getVariableIfUsed(persistentFlow2, booleanOperatorExitNode.getFir().getRightOperand()) : null;
        if (booleanOperatorExitNode.getIsDead() || !booleanOperatorExitNode.getRightOperandNode().getIsDead()) {
            if (zIsEnabled && Intrinsics.areEqual(ControlFlowGraphBuilderKt.getBooleanLiteralValue(booleanOperatorExitNode.getFir().getLeftOperand()), Boolean.valueOf(!z))) {
                if (variableIfUsed2 != null) {
                    LogicSystem.translateVariableFromConditionInStatements$default(firDataFlowAnalyzer.getLogicSystem(), mutableFlow, variableIfUsed2, new SyntheticVariable(booleanOperatorExitNode.getFir()), null, 8, null);
                }
            } else if (!zIsEnabled || !Intrinsics.areEqual(ControlFlowGraphBuilderKt.getBooleanLiteralValue(booleanOperatorExitNode.getFir().getRightOperand()), Boolean.valueOf(!z))) {
                Map<DataFlowVariable, TypeStatement> typeStatementsNotInheritedFrom = firDataFlowAnalyzer.getTypeStatementsNotInheritedFrom(mutableFlow, flow2);
                boolean z2 = !z;
                Map<DataFlowVariable, TypeStatement> mapMergeBooleanLogicOperatorFlow$lambda$0$getStatementsWhenRightArgumentIs = mergeBooleanLogicOperatorFlow$lambda$0$getStatementsWhenRightArgumentIs(variableIfUsed2, firDataFlowAnalyzer, typeStatementsNotInheritedFrom, flow2, z2);
                if (variableIfUsed == null || (variableIfUsed2 == null && !zIsEnabled)) {
                    mapEmptyMap = MapsKt.emptyMap();
                } else {
                    LogicSystem logicSystem = firDataFlowAnalyzer.getLogicSystem();
                    Map<DataFlowVariable, TypeStatement> mapApproveOperationStatement2 = firDataFlowAnalyzer.getLogicSystem().approveOperationStatement(flow, ModelKt.eq(variableIfUsed, z));
                    if (zIsEnabled) {
                        mapApproveOperationStatement = mergeBooleanLogicOperatorFlow$lambda$0$getStatementsWhenRightArgumentIs(variableIfUsed2, firDataFlowAnalyzer, typeStatementsNotInheritedFrom, flow2, z);
                    } else {
                        LogicSystem logicSystem2 = firDataFlowAnalyzer.getLogicSystem();
                        variableIfUsed2.getClass();
                        mapApproveOperationStatement = logicSystem2.approveOperationStatement(flow2, ModelKt.eq(variableIfUsed2, z));
                    }
                    mapEmptyMap = logicSystem.orForTypeStatements(mapApproveOperationStatement2, mapApproveOperationStatement);
                }
                if (zIsEnabled) {
                    firDataFlowAnalyzer.addAllStatements(mutableFlow, firDataFlowAnalyzer.getLogicSystem().orForTypeStatements(mapEmptyMap, mapMergeBooleanLogicOperatorFlow$lambda$0$getStatementsWhenRightArgumentIs));
                }
                SyntheticVariable syntheticVariable = new SyntheticVariable(booleanOperatorExitNode.getFir());
                firDataFlowAnalyzer.addAllConditionally(mutableFlow, ModelKt.eq(syntheticVariable, z), mapEmptyMap);
                firDataFlowAnalyzer.addAllConditionally(mutableFlow, ModelKt.eq(syntheticVariable, z2), mapMergeBooleanLogicOperatorFlow$lambda$0$getStatementsWhenRightArgumentIs);
            } else if (variableIfUsed != null) {
                LogicSystem.translateVariableFromConditionInStatements$default(firDataFlowAnalyzer.getLogicSystem(), mutableFlow, variableIfUsed, new SyntheticVariable(booleanOperatorExitNode.getFir()), null, 8, null);
            }
        } else if (variableIfUsed != null) {
            firDataFlowAnalyzer.commitOperationStatement(mutableFlow, ModelKt.eq(variableIfUsed, z));
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:13:0x002b  */
    private final FirExpression[] orderedArguments(FirStatement firStatement, FirFunction firFunction) {
        FirExpression firExpressionOrderedArguments$firstReceiver;
        LinkedHashMap<ConeResolutionAtom, FirValueParameter> argumentMapping;
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        boolean z = firStatement instanceof FirQualifiedAccessExpression;
        if (z) {
            firExpressionOrderedArguments$firstReceiver = orderedArguments$firstReceiver((FirQualifiedAccessExpression) firStatement);
        } else if (firStatement instanceof FirVariableAssignment) {
            FirExpression lValue = ((FirVariableAssignment) firStatement).getLValue();
            FirQualifiedAccessExpression firQualifiedAccessExpression = lValue instanceof FirQualifiedAccessExpression ? (FirQualifiedAccessExpression) lValue : null;
            if (firQualifiedAccessExpression != null) {
                firExpressionOrderedArguments$firstReceiver = orderedArguments$firstReceiver(firQualifiedAccessExpression);
            } else {
                firExpressionOrderedArguments$firstReceiver = null;
            }
        } else {
            firExpressionOrderedArguments$firstReceiver = null;
        }
        listCreateListBuilder.add(firExpressionOrderedArguments$firstReceiver);
        if (firStatement instanceof FirFunctionCall) {
            FirArgumentList argumentList = ((FirCall) firStatement).getArgumentList();
            LinkedHashMap<FirExpression, FirValueParameter> mapping = argumentList instanceof FirResolvedArgumentList ? ((FirResolvedArgumentList) argumentList).getMapping() : null;
            if (mapping == null) {
                Candidate candidate = CandidateFactoryKt.candidate((FirResolvable) firStatement);
                mapping = (candidate == null || (argumentMapping = candidate.getArgumentMapping()) == null) ? null : FirCallCompletionResultsWriterTransformerKt.unwrapAtoms(argumentMapping);
                if (mapping == null) {
                    return null;
                }
            }
            Set<Map.Entry<FirExpression, FirValueParameter>> setEntrySet = mapping.entrySet();
            setEntrySet.getClass();
            Set<Map.Entry<FirExpression, FirValueParameter>> set = setEntrySet;
            LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(set, 10)), 16));
            Iterator<T> it = set.iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                Object value = entry.getValue();
                Object key = entry.getKey();
                key.getClass();
                Pair pair = TuplesKt.to(value, FirExpressionUtilKt.unwrapArgument((FirExpression) key));
                linkedHashMap.put(pair.getFirst(), pair.getSecond());
            }
            Iterator<T> it2 = firFunction.getValueParameters().iterator();
            while (it2.hasNext()) {
                listCreateListBuilder.add(linkedHashMap.get((FirValueParameter) it2.next()));
            }
        }
        if (z) {
            orderedArguments$addContextArgumentsTo((FirQualifiedAccessExpression) firStatement, listCreateListBuilder);
        } else if (firStatement instanceof FirVariableAssignment) {
            FirVariableAssignment firVariableAssignment = (FirVariableAssignment) firStatement;
            FirExpression lValue2 = firVariableAssignment.getLValue();
            FirQualifiedAccessExpression firQualifiedAccessExpression2 = lValue2 instanceof FirQualifiedAccessExpression ? (FirQualifiedAccessExpression) lValue2 : null;
            if (firQualifiedAccessExpression2 != null) {
                orderedArguments$addContextArgumentsTo(firQualifiedAccessExpression2, listCreateListBuilder);
            }
            listCreateListBuilder.add(firVariableAssignment.getRValue());
        }
        return (FirExpression[]) CollectionsKt.build(listCreateListBuilder).toArray(new FirExpression[0]);
    }

    private static final void orderedArguments$addContextArgumentsTo(FirQualifiedAccessExpression firQualifiedAccessExpression, List<FirExpression> list) {
        Candidate candidate = CandidateFactoryKt.candidate(firQualifiedAccessExpression);
        if (candidate == null) {
            Iterator<T> it = firQualifiedAccessExpression.getContextArguments().iterator();
            while (it.hasNext()) {
                list.add((FirExpression) it.next());
            }
        } else {
            List<ConeResolutionAtom> contextArguments = candidate.getContextArguments();
            if (contextArguments != null) {
                Iterator<T> it2 = contextArguments.iterator();
                while (it2.hasNext()) {
                    list.add(((ConeResolutionAtom) it2.next()).getExpression());
                }
            }
        }
    }

    private static final FirExpression orderedArguments$firstReceiver(FirQualifiedAccessExpression firQualifiedAccessExpression) {
        Candidate candidate = CandidateFactoryKt.candidate(firQualifiedAccessExpression);
        if (candidate != null) {
            FirExpression firExpressionChosenExtensionReceiverExpression = candidate.chosenExtensionReceiverExpression();
            return firExpressionChosenExtensionReceiverExpression == null ? candidate.dispatchReceiverExpression() : firExpressionChosenExtensionReceiverExpression;
        }
        FirExpression extensionReceiver = firQualifiedAccessExpression.getExtensionReceiver();
        return extensionReceiver == null ? firQualifiedAccessExpression.getDispatchReceiver() : extensionReceiver;
    }

    public static Unit p(FirDataFlowAnalyzer firDataFlowAnalyzer, Set set, FlowPath flowPath, MutableFlow mutableFlow) {
        flowPath.getClass();
        mutableFlow.getClass();
        firDataFlowAnalyzer.enterRepeatableStatement(mutableFlow, set);
        return Unit.INSTANCE;
    }

    private final void processBackingFieldAccess(MutableFlow flow, FirQualifiedAccessExpression qualifiedAccess) {
        FirBackingFieldSymbol firBackingFieldSymbolTryAccessExplicitFieldSymbol;
        DataFlowVariable orCreateVariable;
        FirReference calleeReference = qualifiedAccess.getCalleeReference();
        FirPropertyWithExplicitBackingFieldResolvedNamedReference firPropertyWithExplicitBackingFieldResolvedNamedReference = calleeReference instanceof FirPropertyWithExplicitBackingFieldResolvedNamedReference ? (FirPropertyWithExplicitBackingFieldResolvedNamedReference) calleeReference : null;
        if (firPropertyWithExplicitBackingFieldResolvedNamedReference == null || (firBackingFieldSymbolTryAccessExplicitFieldSymbol = ExplicitFieldsUtilsKt.tryAccessExplicitFieldSymbol(firPropertyWithExplicitBackingFieldResolvedNamedReference, this.components.getContext().getPublicApiInlineFunction(), getSession())) == null || PrivateToThisUtilsKt.isPrivateToThisInvisibleAccess(qualifiedAccess, getSession(), firBackingFieldSymbolTryAccessExplicitFieldSymbol) || (orCreateVariable = getOrCreateVariable(flow, qualifiedAccess)) == null) {
            return;
        }
        addTypeStatement(flow, ModelKt.typeEq(orCreateVariable, this.components.getReturnTypeCalculator().tryCalculateReturnType(firBackingFieldSymbolTryAccessExplicitFieldSymbol).getConeType()));
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:114:0x0187  */
    /* JADX WARN: Code duplicated, block: B:36:0x0084  */
    private final void processConditionalContract(MutableFlow flow, FirStatement qualifiedAccess, PersistentFlow callArgsExit, FirAnonymousFunction targetLambdaArgument) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirReference calleeReference;
        FirBasedSymbol<?> symbol;
        FirFunction setter;
        FirFunctionSymbol<FirFunction> symbol2;
        FirExpression[] firExpressionArrOrderedArguments;
        boolean z;
        int i;
        boolean z2;
        DataFlowVariable variableIfUsedOrReal;
        final FirDataFlowAnalyzer firDataFlowAnalyzer = this;
        final MutableFlow mutableFlow = flow;
        FirStatement firStatement = qualifiedAccess;
        if (FirResolvePhaseKt.isBodyResolve(firDataFlowAnalyzer.components.getTransformer().getBaseTransformerPhase())) {
            if (firStatement instanceof FirFunctionCall) {
                FirBasedSymbol<?> symbol3 = FirReferenceUtilsKt.getSymbol(((FirFunctionCall) firStatement).getCalleeReference());
                FirDeclaration fir = symbol3 != null ? symbol3.getFir() : null;
                if (fir instanceof FirNamedFunction) {
                    setter = (FirNamedFunction) fir;
                } else {
                    setter = null;
                }
            } else if (firStatement instanceof FirQualifiedAccessExpression) {
                FirBasedSymbol<?> symbol4 = FirReferenceUtilsKt.getSymbol(((FirQualifiedAccessExpression) firStatement).getCalleeReference());
                if (symbol4 != null) {
                    FirDeclaration fir2 = symbol4.getFir();
                    FirProperty firProperty = fir2 instanceof FirProperty ? (FirProperty) fir2 : null;
                    if (firProperty != null) {
                        setter = firProperty.getGetter();
                    } else {
                        setter = null;
                    }
                } else {
                    setter = null;
                }
            } else if (!(firStatement instanceof FirVariableAssignment) || (calleeReference = ReferenceUtilsKt.getCalleeReference((FirVariableAssignment) firStatement)) == null || (symbol = FirReferenceUtilsKt.getSymbol(calleeReference)) == null) {
                setter = null;
            } else {
                FirDeclaration fir3 = symbol.getFir();
                FirProperty firProperty2 = fir3 instanceof FirProperty ? (FirProperty) fir3 : null;
                if (firProperty2 != null) {
                    setter = firProperty2.getSetter();
                } else {
                    setter = null;
                }
            }
            if (setter == null) {
                return;
            }
            if (Intrinsics.areEqual(setter.getSymbol().getCallableId(), StandardClassIds.Callables.INSTANCE.getNot())) {
                firStatement.getClass();
                firDataFlowAnalyzer.exitBooleanNot(mutableFlow, (FirFunctionCall) firStatement);
                return;
            }
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(setter) || (setter.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(setter) : null;
            if (originalForSubstitutionOverrideAttr == null) {
                originalForSubstitutionOverrideAttr = ClassMembersKt.isIntersectionOverride(setter) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(setter) : null;
            }
            FirFunction firFunction = (FirFunction) originalForSubstitutionOverrideAttr;
            if (firFunction == null || (symbol2 = firFunction.getSymbol()) == null) {
                symbol2 = setter.getSymbol();
            }
            FirResolvedContractDescription resolvedContractDescription = symbol2.getResolvedContractDescription();
            if (resolvedContractDescription == null || (firExpressionArrOrderedArguments = firDataFlowAnalyzer.orderedArguments(firStatement, setter)) == null) {
                return;
            }
            ArrayList<KtConditionalEffectDeclaration> arrayList = new ArrayList();
            ArrayList<KtConditionalReturnsDeclaration> arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            if (targetLambdaArgument == null) {
                i = -1;
                z = true;
            } else {
                int length = firExpressionArrOrderedArguments.length;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        z = true;
                        i2 = -1;
                        break;
                    }
                    FirExpression firExpression = firExpressionArrOrderedArguments[i2];
                    z = true;
                    FirAnonymousFunctionExpression firAnonymousFunctionExpression = firExpression instanceof FirAnonymousFunctionExpression ? (FirAnonymousFunctionExpression) firExpression : null;
                    if (Intrinsics.areEqual(firAnonymousFunctionExpression != null ? firAnonymousFunctionExpression.getAnonymousFunction() : null, targetLambdaArgument)) {
                        break;
                    } else {
                        i2++;
                    }
                }
                i = i2 - 1;
            }
            Iterator<T> it = resolvedContractDescription.getEffects().iterator();
            boolean z3 = false;
            while (it.hasNext()) {
                KtEffectDeclaration<ConeKotlinType, ConeDiagnostic> effect = ((FirEffectDeclaration) it.next()).getEffect();
                if (targetLambdaArgument == null && (effect instanceof KtConditionalEffectDeclaration)) {
                    arrayList.add(effect);
                } else if (targetLambdaArgument == null && (effect instanceof KtConditionalReturnsDeclaration)) {
                    arrayList2.add(effect);
                } else if (targetLambdaArgument != null && (effect instanceof KtHoldsInEffectDeclaration) && ((KtHoldsInEffectDeclaration) effect).getValueParameterReference().getParameterIndex() == i) {
                    arrayList3.add(effect);
                }
                z3 = z;
            }
            if (z3) {
                int length2 = firExpressionArrOrderedArguments.length;
                DataFlowVariable[] dataFlowVariableArr = new DataFlowVariable[length2];
                for (int i3 = 0; i3 < length2; i3++) {
                    FirExpression firExpression2 = firExpressionArrOrderedArguments[i3];
                    if (firExpression2 != null) {
                        variableIfUsedOrReal = firDataFlowAnalyzer.getVariableIfUsedOrReal(mutableFlow, firExpression2);
                        if (callArgsExit != null && !firDataFlowAnalyzer.isSameValueIn(callArgsExit, firExpression2, mutableFlow)) {
                            variableIfUsedOrReal = null;
                        }
                    } else {
                        variableIfUsedOrReal = null;
                    }
                    dataFlowVariableArr[i3] = variableIfUsedOrReal;
                }
                int length3 = firExpressionArrOrderedArguments.length;
                DataFlowVariable[] dataFlowVariableArr2 = new DataFlowVariable[length3];
                for (int i4 = 0; i4 < length3; i4++) {
                    FirExpression firExpression3 = firExpressionArrOrderedArguments[i4];
                    dataFlowVariableArr2[i4] = firExpression3 != null ? firDataFlowAnalyzer.getOrCreateVariable(mutableFlow, firExpression3) : null;
                }
                ConeSubstitutor substitutor = firDataFlowAnalyzer.getSubstitutor(setter, firStatement, firFunction);
                for (int i5 = 0; i5 < length2; i5++) {
                    if (dataFlowVariableArr[i5] != null) {
                        if (targetLambdaArgument == null) {
                            for (KtConditionalEffectDeclaration ktConditionalEffectDeclaration : arrayList) {
                                KtEffectDeclaration effect2 = ktConditionalEffectDeclaration.getEffect();
                                KtReturnsEffectDeclaration ktReturnsEffectDeclaration = effect2 instanceof KtReturnsEffectDeclaration ? (KtReturnsEffectDeclaration) effect2 : null;
                                if (ktReturnsEffectDeclaration != null) {
                                    processConditionalContract$processEffect(firDataFlowAnalyzer, dataFlowVariableArr, substitutor, mutableFlow, firStatement, ktConditionalEffectDeclaration.getCondition(), ContractsKt.toOperation(ktReturnsEffectDeclaration.getValue()));
                                    firDataFlowAnalyzer = this;
                                    mutableFlow = flow;
                                    firStatement = qualifiedAccess;
                                }
                            }
                        } else {
                            Iterator it2 = arrayList3.iterator();
                            while (it2.hasNext()) {
                                processConditionalContract$processEffect(this, dataFlowVariableArr, substitutor, flow, qualifiedAccess, ((KtHoldsInEffectDeclaration) it2.next()).getArgumentsCondition(), null);
                            }
                        }
                        firDataFlowAnalyzer = this;
                        mutableFlow = flow;
                        firStatement = qualifiedAccess;
                        break;
                    }
                }
                if (targetLambdaArgument == null && (firStatement instanceof FirExpression)) {
                    for (KtConditionalReturnsDeclaration ktConditionalReturnsDeclaration : arrayList2) {
                        KtEffectDeclaration returnsEffect = ktConditionalReturnsDeclaration.getReturnsEffect();
                        KtReturnsEffectDeclaration ktReturnsEffectDeclaration2 = returnsEffect instanceof KtReturnsEffectDeclaration ? (KtReturnsEffectDeclaration) returnsEffect : null;
                        if (ktReturnsEffectDeclaration2 != null && Intrinsics.areEqual(ktReturnsEffectDeclaration2.getValue(), ConeContractConstantValues.INSTANCE.getNOT_NULL())) {
                            ConeSubstitutor coneSubstitutor = substitutor;
                            DataFlowVariable[] dataFlowVariableArr3 = dataFlowVariableArr2;
                            Map<DataFlowVariable, TypeStatement> mapApproveContractStatement = ContractsKt.approveContractStatement(firDataFlowAnalyzer.getLogicSystem(), ktConditionalReturnsDeclaration.getArgumentsCondition(), dataFlowVariableArr3, coneSubstitutor, false, new Function1() { // from class: z05
                                public final Object invoke(Object obj) {
                                    return FirDataFlowAnalyzer.f(this.b, mutableFlow, (OperationStatement) obj);
                                }
                            });
                            if (mapApproveContractStatement != null) {
                                Iterator<Map.Entry<DataFlowVariable, TypeStatement>> it3 = mapApproveContractStatement.entrySet().iterator();
                                while (it3.hasNext()) {
                                    if (firDataFlowAnalyzer.getLogicSystem().approveTypeStatement(mutableFlow, it3.next().getValue())) {
                                        z2 = z;
                                        firDataFlowAnalyzer.addAllStatements(mutableFlow, firDataFlowAnalyzer.getLogicSystem().approveOperationStatement(mutableFlow, new OperationStatement(new SyntheticVariable((FirExpression) firStatement), Operation.NotEqNull), z2));
                                    } else {
                                        z2 = z;
                                    }
                                    z = z2;
                                }
                            }
                            dataFlowVariableArr2 = dataFlowVariableArr3;
                            substitutor = coneSubstitutor;
                        }
                    }
                }
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static /* synthetic */ void processConditionalContract$default(FirDataFlowAnalyzer firDataFlowAnalyzer, MutableFlow mutableFlow, FirStatement firStatement, PersistentFlow persistentFlow, FirAnonymousFunction firAnonymousFunction, int i, Object obj) throws KotlinIllegalArgumentExceptionWithAttachments {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: processConditionalContract");
            return;
        }
        if ((i & 8) != 0) {
            firAnonymousFunction = null;
        }
        firDataFlowAnalyzer.processConditionalContract(mutableFlow, firStatement, persistentFlow, firAnonymousFunction);
    }

    private static final void processConditionalContract$processEffect(final FirDataFlowAnalyzer firDataFlowAnalyzer, DataFlowVariable[] dataFlowVariableArr, ConeSubstitutor coneSubstitutor, final MutableFlow mutableFlow, FirStatement firStatement, KtBooleanExpression<ConeKotlinType, ConeDiagnostic> ktBooleanExpression, final Operation operation) {
        DataFlowVariable orCreateVariable;
        Map<DataFlowVariable, ? extends TypeStatement> mapApproveContractStatement$default = ContractsKt.approveContractStatement$default(firDataFlowAnalyzer.getLogicSystem(), ktBooleanExpression, dataFlowVariableArr, coneSubstitutor, false, new Function1() { // from class: j15
            public final Object invoke(Object obj) {
                return FirDataFlowAnalyzer.n(this.b, mutableFlow, operation, (OperationStatement) obj);
            }
        }, 8, null);
        if (mapApproveContractStatement$default == null) {
            return;
        }
        if (operation == null) {
            firDataFlowAnalyzer.addAllStatements(mutableFlow, mapApproveContractStatement$default);
        } else {
            if (!(firStatement instanceof FirExpression) || (orCreateVariable = firDataFlowAnalyzer.getOrCreateVariable(mutableFlow, (FirExpression) firStatement)) == null) {
                return;
            }
            firDataFlowAnalyzer.addAllConditionally(mutableFlow, new OperationStatement(orCreateVariable, operation), mapApproveContractStatement$default);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final void processEq(MutableFlow flow, PersistentFlow lhsExitFlow, FirEqualityOperatorCall expression, FirExpression leftOperand, FirExpression rightOperand, FirOperation operation) throws KotlinIllegalArgumentExceptionWithAttachments {
        boolean zIsEq = UtilKt.isEq(operation);
        ConeKotlinType resolvedType = FirTypeUtilsKt.getResolvedType(leftOperand);
        ConeKotlinType resolvedType2 = FirTypeUtilsKt.getResolvedType(rightOperand);
        boolean zIsMarkedNullable = ConeTypeUtilsKt.isMarkedNullable(resolvedType);
        boolean zIsMarkedNullable2 = ConeTypeUtilsKt.isMarkedNullable(resolvedType2);
        if (zIsMarkedNullable && zIsMarkedNullable2) {
            return;
        }
        DataFlowVariable variableIfUsedOrReal = getVariableIfUsedOrReal(flow, leftOperand);
        if (!isSameValueIn(lhsExitFlow, leftOperand, flow)) {
            variableIfUsedOrReal = null;
        }
        DataFlowVariable variableIfUsedOrReal2 = getVariableIfUsedOrReal(flow, rightOperand);
        if (variableIfUsedOrReal == null && variableIfUsedOrReal2 == null) {
            return;
        }
        DataFlowVariable dataFlowVariable = variableIfUsedOrReal;
        SyntheticVariable syntheticVariable = new SyntheticVariable(expression);
        if (zIsMarkedNullable || zIsMarkedNullable2) {
            DataFlowVariable dataFlowVariable2 = zIsMarkedNullable ? dataFlowVariable : variableIfUsedOrReal2;
            if (dataFlowVariable2 != null) {
                addImplication(flow, ModelKt.implies(ModelKt.eq(syntheticVariable, zIsEq), ModelKt.notEq(dataFlowVariable2, null)));
            }
        }
        if ((dataFlowVariable instanceof RealVariable) || (variableIfUsedOrReal2 instanceof RealVariable)) {
            EqualsOverrideContract equalsOverrideContractComputeEqualsOverrideContract = (operation == FirOperation.EQ || operation == FirOperation.NOT_EQ) ? FirEqualsOverrideHelpersKt.computeEqualsOverrideContract(resolvedType, this.components.getSession(), this.components.getScopeSession(), false) : EqualsOverrideContract.SAFE_FOR_SMART_CAST;
            processEq$addEqualityImplications(equalsOverrideContractComputeEqualsOverrideContract, this, flow, syntheticVariable, zIsEq, dataFlowVariable, rightOperand);
            processEq$addEqualityImplications(equalsOverrideContractComputeEqualsOverrideContract, this, flow, syntheticVariable, zIsEq, variableIfUsedOrReal2, leftOperand);
        }
    }

    private static final void processEq$addEqualityImplications(EqualsOverrideContract equalsOverrideContract, FirDataFlowAnalyzer firDataFlowAnalyzer, MutableFlow mutableFlow, SyntheticVariable syntheticVariable, boolean z, DataFlowVariable dataFlowVariable, FirExpression firExpression) {
        FirBasedSymbol symbol;
        if (dataFlowVariable instanceof RealVariable) {
            if (equalsOverrideContract == EqualsOverrideContract.SAFE_FOR_SMART_CAST) {
                firDataFlowAnalyzer.addImplication(mutableFlow, ModelKt.implies(ModelKt.eq(syntheticVariable, z), ModelKt.typeEq(dataFlowVariable, FirTypeUtilsKt.getResolvedType(firExpression))));
            }
            FirExpression firExpressionUnwrapSmartcastExpression = FirExpressionUtilKt.unwrapSmartcastExpression(firExpression);
            List complementarySymbols = null;
            if (!(firExpressionUnwrapSmartcastExpression instanceof FirPropertyAccessExpression) ? !(firExpressionUnwrapSmartcastExpression instanceof FirResolvedQualifier) || (symbol = ((FirResolvedQualifier) firExpressionUnwrapSmartcastExpression).getSymbol()) == null || !processEq$isSingleton(symbol) : (symbol = FirReferenceUtilsKt.toResolvedBaseSymbol$default(((FirPropertyAccessExpression) firExpressionUnwrapSmartcastExpression).getCalleeReference(), false, 1, null)) == null || !processEq$isSingleton(symbol)) {
                symbol = null;
            }
            if (symbol != null) {
                firDataFlowAnalyzer.addImplication(mutableFlow, ModelKt.implies(ModelKt.eq(syntheticVariable, !z), ModelKt.valueNotEq((RealVariable) dataFlowVariable, (FirBasedSymbol<?>) symbol)));
            }
            if (symbol instanceof FirEnumEntrySymbol) {
                complementarySymbols = DeclarationUtilsKt.getComplementarySymbols(firDataFlowAnalyzer.components, (FirEnumEntrySymbol) symbol);
            } else if (symbol instanceof FirRegularClassSymbol) {
                FirRegularClassSymbol firRegularClassSymbol = (FirRegularClassSymbol) symbol;
                if (firRegularClassSymbol.getClassKind() == ClassKind.OBJECT) {
                    complementarySymbols = DeclarationUtilsKt.getComplementarySymbols(firDataFlowAnalyzer.components, firRegularClassSymbol);
                }
            }
            if (complementarySymbols == null || complementarySymbols.isEmpty()) {
                return;
            }
            firDataFlowAnalyzer.addImplication(mutableFlow, ModelKt.implies(ModelKt.eq(syntheticVariable, z), ModelKt.valueNotEq((RealVariable) dataFlowVariable, (List<? extends FirBasedSymbol<?>>) complementarySymbols)));
        }
    }

    private static final boolean processEq$isSingleton(FirBasedSymbol<?> firBasedSymbol) {
        if (firBasedSymbol instanceof FirEnumEntrySymbol) {
            return true;
        }
        return (firBasedSymbol instanceof FirRegularClassSymbol) && ((FirRegularClassSymbol) firBasedSymbol).getClassKind() == ClassKind.OBJECT;
    }

    private final void processEqConst(MutableFlow flow, FirEqualityOperatorCall expression, FirExpression operand, FirLiteralExpression firLiteralExpression, boolean isEq) {
        if (Intrinsics.areEqual(firLiteralExpression.getKind(), ConstantValueKind.Null.INSTANCE)) {
            processEqNull$default(this, flow, expression, operand, isEq, null, 16, null);
            return;
        }
        DataFlowVariable variableIfUsedOrReal = getVariableIfUsedOrReal(flow, operand);
        if (variableIfUsedOrReal == null) {
            return;
        }
        SyntheticVariable syntheticVariable = new SyntheticVariable(expression);
        if (Intrinsics.areEqual(firLiteralExpression.getKind(), ConstantValueKind.Boolean.INSTANCE) && ConeBuiltinTypeUtilsKt.isBooleanOrNullableBoolean(FirTypeUtilsKt.getResolvedType(operand))) {
            Object value = firLiteralExpression.getValue();
            value.getClass();
            boolean zBooleanValue = ((Boolean) value).booleanValue();
            addImplication(flow, ModelKt.implies(ModelKt.eq(syntheticVariable, isEq), ModelKt.eq(variableIfUsedOrReal, zBooleanValue)));
            if (ConeBuiltinTypeUtilsKt.isBoolean(FirTypeUtilsKt.getResolvedType(operand))) {
                addImplication(flow, ModelKt.implies(ModelKt.eq(syntheticVariable, !isEq), ModelKt.eq(variableIfUsedOrReal, !zBooleanValue)));
            }
            if (variableIfUsedOrReal instanceof RealVariable) {
                addImplication(flow, ModelKt.implies(ModelKt.eq(syntheticVariable, !isEq), ModelKt.valueNotEq((RealVariable) variableIfUsedOrReal, zBooleanValue)));
            }
        } else {
            addImplication(flow, ModelKt.implies(ModelKt.eq(syntheticVariable, isEq), ModelKt.notEq(variableIfUsedOrReal, null)));
        }
        if ((variableIfUsedOrReal instanceof RealVariable) && Intrinsics.areEqual(firLiteralExpression, expression.getArgumentList().getArguments().get(0)) && FirEqualsOverrideHelpersKt.isSmartcastPrimitive(ConeTypeUtilsKt.getClassId(FirTypeUtilsKt.getResolvedType(firLiteralExpression)))) {
            addImplication(flow, ModelKt.implies(ModelKt.eq(syntheticVariable, isEq), ModelKt.typeEq(variableIfUsedOrReal, FirTypeUtilsKt.getResolvedType(firLiteralExpression))));
        }
    }

    private final void processEqNull(MutableFlow flow, FirExpression expression, FirExpression operand, boolean isEq, PersistentFlow lhsExitFlow) {
        DataFlowVariable variableIfUsedOrReal = getVariableIfUsedOrReal(flow, operand);
        if (variableIfUsedOrReal == null) {
            return;
        }
        if (!(variableIfUsedOrReal instanceof RealVariable) || lhsExitFlow == null || getLogicSystem().isSameValueIn(lhsExitFlow, flow, (RealVariable) variableIfUsedOrReal)) {
            SyntheticVariable syntheticVariable = new SyntheticVariable(expression);
            addImplication(flow, ModelKt.implies(ModelKt.eq(syntheticVariable, isEq), ModelKt.eq(variableIfUsedOrReal, (Void) null)));
            addImplication(flow, ModelKt.implies(ModelKt.eq(syntheticVariable, !isEq), ModelKt.notEq(variableIfUsedOrReal, null)));
        }
    }

    public static /* synthetic */ void processEqNull$default(FirDataFlowAnalyzer firDataFlowAnalyzer, MutableFlow mutableFlow, FirExpression firExpression, FirExpression firExpression2, boolean z, PersistentFlow persistentFlow, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: processEqNull");
            return;
        }
        if ((i & 16) != 0) {
            persistentFlow = null;
        }
        firDataFlowAnalyzer.processEqNull(mutableFlow, firExpression, firExpression2, z, persistentFlow);
    }

    private final void processLoopExit(MutableFlow flow, LoopExitNode node, LoopConditionExitNode conditionExitNode) {
        int i;
        DataFlowVariable variableIfUsed;
        if (conditionExitNode.getIsDead()) {
            return;
        }
        List<CFGNode<?>> previousNodes = node.getPreviousNodes();
        if ((previousNodes instanceof Collection) && previousNodes.isEmpty()) {
            i = 0;
        } else {
            Iterator<T> it = previousNodes.iterator();
            i = 0;
            while (it.hasNext()) {
                if (!((CFGNode) it.next()).getIsDead() && (i = i + 1) < 0) {
                    CollectionsKt.throwCountOverflow();
                }
            }
        }
        if (i <= 1 && ConeBuiltinTypeUtilsKt.isBoolean(FirTypeUtilsKt.getResolvedType(conditionExitNode.getFir())) && (variableIfUsed = getVariableIfUsed(flow, conditionExitNode.getFir())) != null) {
            commitOperationStatement(flow, ModelKt.eq(variableIfUsed, false));
        }
    }

    private final void processWhileLoopExit(FlowPath path, MutableFlow flow, LoopExitNode node, LoopConditionEnterNode conditionEnterNode, Set<? extends FirPropertySymbol> reassigned) {
        if (reassigned.isEmpty()) {
            return;
        }
        PersistentFlow flow2 = getFlow(conditionEnterNode, path);
        List<CFGNode<?>> previousLiveNodes = CFGNodeKt.getPreviousLiveNodes(conditionEnterNode);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(previousLiveNodes, 10));
        Iterator<T> it = previousLiveNodes.iterator();
        while (it.hasNext()) {
            arrayList.add(getFlow((CFGNode) it.next(), path));
        }
        List<CFGNode<?>> previousLiveNodes2 = CFGNodeKt.getPreviousLiveNodes(node);
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(previousLiveNodes2, 10));
        Iterator<T> it2 = previousLiveNodes2.iterator();
        while (it2.hasNext()) {
            arrayList2.add(getFlow((CFGNode) it2.next(), path));
        }
        Iterator<T> it3 = reassigned.iterator();
        while (it3.hasNext()) {
            RealVariable local = getLocal((FirPropertySymbol) it3.next(), false);
            if (local != null) {
                LogicSystem logicSystem = getLogicSystem();
                ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
                Iterator it4 = arrayList.iterator();
                while (true) {
                    if (!it4.hasNext()) {
                        TypeStatement typeStatementOr = logicSystem.or(arrayList3);
                        if (typeStatementOr != null) {
                            if (!typeStatementOr.isNotEmpty()) {
                                typeStatementOr = null;
                            }
                            if (typeStatementOr == null) {
                                break;
                            }
                            LogicSystem logicSystem2 = getLogicSystem();
                            ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList2, 10));
                            Iterator it5 = arrayList2.iterator();
                            while (true) {
                                if (!it5.hasNext()) {
                                    TypeStatement typeStatementOr2 = logicSystem2.or(arrayList4);
                                    if (typeStatementOr2 != null) {
                                        addTypeStatement(flow, typeStatementOr2);
                                        break;
                                    }
                                    break;
                                }
                                PersistentFlow persistentFlow = (PersistentFlow) it5.next();
                                TypeStatement typeStatement = persistentFlow.getTypeStatement(local);
                                if (getLogicSystem().isSameValueIn(flow2, persistentFlow, local)) {
                                    typeStatement = getLogicSystem().and(typeStatement, typeStatementOr);
                                } else if (typeStatement == null) {
                                    break;
                                }
                                arrayList4.add(typeStatement);
                            }
                        } else {
                            break;
                        }
                    } else {
                        TypeStatement typeStatement2 = ((PersistentFlow) it4.next()).getTypeStatement(local);
                        if (typeStatement2 == null) {
                            break;
                        } else {
                            arrayList3.add(typeStatement2);
                        }
                    }
                }
            }
        }
    }

    private final void propagateAlternateFlows(CFGNode<?> cFGNode, Function2<? super FlowPath, ? super MutableFlow, Unit> function2) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (CFGNode<?> cFGNode2 : cFGNode.getPreviousNodes()) {
            if (!cFGNode2.getAlternateFlowPaths().isEmpty()) {
                Edge edgeEdgeFrom = cFGNode.edgeFrom(cFGNode2);
                if (Intrinsics.areEqual(edgeEdgeFrom.getLabel(), NormalPath.INSTANCE) && CFGNodeKt.usedInDfa(cFGNode, edgeEdgeFrom)) {
                    for (FlowPath flowPath : cFGNode2.getAlternateFlowPaths()) {
                        if (flowPath instanceof FlowPath.CfgEdge) {
                            FlowPath.CfgEdge cfgEdge = (FlowPath.CfgEdge) flowPath;
                            if (getGraphBuilder().withinFinallyBlock(cfgEdge.getFir()) && linkedHashSet.add(flowPath)) {
                                cFGNode.addAlternateFlow(flowPath, buildAlternateFlow(cFGNode, cfgEdge, function2).freeze());
                            }
                        }
                    }
                }
            }
        }
    }

    public static Unit q(FirDataFlowAnalyzer firDataFlowAnalyzer, LoopExitNode loopExitNode, LoopConditionEnterNode loopConditionEnterNode, Set set, FlowPath flowPath, MutableFlow mutableFlow) {
        flowPath.getClass();
        mutableFlow.getClass();
        firDataFlowAnalyzer.processWhileLoopExit(flowPath, mutableFlow, loopExitNode, loopConditionEnterNode, set);
        CFGNode<?> firstPreviousNode = CFGNodeKt.getFirstPreviousNode(loopExitNode);
        firstPreviousNode.getClass();
        firDataFlowAnalyzer.processLoopExit(mutableFlow, loopExitNode, (LoopConditionExitNode) firstPreviousNode);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static Unit r(boolean z, FirLiteralExpression firLiteralExpression, boolean z2, FirLiteralExpression firLiteralExpression2, FirDataFlowAnalyzer firDataFlowAnalyzer, FirEqualityOperatorCall firEqualityOperatorCall, FirExpression firExpression, FirOperation firOperation, CFGNode cFGNode, FirExpression firExpression2, FlowPath flowPath, MutableFlow mutableFlow) throws KotlinIllegalArgumentExceptionWithAttachments {
        flowPath.getClass();
        mutableFlow.getClass();
        if (z || firLiteralExpression != null || z2 || firLiteralExpression2 != null) {
            if (z) {
                firDataFlowAnalyzer.processEqNull(mutableFlow, firEqualityOperatorCall, firExpression, UtilKt.isEq(firOperation), cFGNode.getFlow());
            } else if (firLiteralExpression != null) {
                firDataFlowAnalyzer.processEqConst(mutableFlow, firEqualityOperatorCall, firExpression, firLiteralExpression, UtilKt.isEq(firOperation));
            }
            if (z2) {
                firDataFlowAnalyzer.processEqNull(mutableFlow, firEqualityOperatorCall, firExpression2, UtilKt.isEq(firOperation), cFGNode.getFlow());
            } else if (firLiteralExpression2 != null) {
                firDataFlowAnalyzer.processEqConst(mutableFlow, firEqualityOperatorCall, firExpression2, firLiteralExpression2, UtilKt.isEq(firOperation));
            }
        } else {
            firDataFlowAnalyzer.processEq(mutableFlow, cFGNode.getFlow(), firEqualityOperatorCall, firExpression2, firExpression, firOperation);
        }
        return Unit.INSTANCE;
    }

    private final void resetSmartCastPositionTo(Flow flow) {
        Flow flow2 = this.currentSmartCastPosition;
        if (Intrinsics.areEqual(flow2, flow)) {
            return;
        }
        Iterator<T> it = getReceiverStack().getImplicitValues().iterator();
        while (it.hasNext()) {
            ImplicitValue implicitValue = (ImplicitValue) it.next();
            RealVariable realVariableImplicit = RealVariable.INSTANCE.implicit(implicitValue.getBoundSymbol(), implicitValue.getOriginalType());
            TypeStatement typeStatement = flow != null ? flow.getTypeStatement(realVariableImplicit) : null;
            if (!Intrinsics.areEqual(typeStatement, flow2 != null ? flow2.getTypeStatement(realVariableImplicit) : null)) {
                if (typeStatement == null) {
                    typeStatement = new MutableTypeStatement(realVariableImplicit, null, null, 6, null);
                }
                implicitUpdated(typeStatement);
            }
        }
        this.currentSmartCastPosition = flow;
    }

    public static RealVariable s(RealVariable realVariable) {
        realVariable.getClass();
        return realVariable;
    }

    public static Unit t(FirDataFlowAnalyzer firDataFlowAnalyzer, FirCheckNotNullCall firCheckNotNullCall, FlowPath flowPath, MutableFlow mutableFlow) {
        flowPath.getClass();
        mutableFlow.getClass();
        DataFlowVariable variableIfUsedOrReal = firDataFlowAnalyzer.getVariableIfUsedOrReal(mutableFlow, (FirExpression) CollectionsKt.first(firCheckNotNullCall.getArgumentList().getArguments()));
        if (variableIfUsedOrReal == null) {
            return Unit.INSTANCE;
        }
        firDataFlowAnalyzer.commitOperationStatement(mutableFlow, ModelKt.notEq(variableIfUsedOrReal, null));
        return Unit.INSTANCE;
    }

    public static Unit u(FirWhenBranch firWhenBranch, FirDataFlowAnalyzer firDataFlowAnalyzer, FlowPath flowPath, MutableFlow mutableFlow) {
        flowPath.getClass();
        mutableFlow.getClass();
        if (ConeBuiltinTypeUtilsKt.isBoolean(FirTypeUtilsKt.getResolvedType(firWhenBranch.getCondition()))) {
            DataFlowVariable variableIfUsed = firDataFlowAnalyzer.getVariableIfUsed(mutableFlow, firWhenBranch.getCondition());
            if (variableIfUsed == null) {
                return Unit.INSTANCE;
            }
            firDataFlowAnalyzer.commitOperationStatement(mutableFlow, ModelKt.eq(variableIfUsed, true));
        }
        return Unit.INSTANCE;
    }

    private final RealVariable unwrapVariableIfStable(Flow flow, RealVariable realVariable) {
        RealVariable realVariableUnwrapVariable = flow.unwrapVariable(realVariable);
        if (Intrinsics.areEqual(realVariableUnwrapVariable, realVariable) || !isUnstableLocalVar(realVariable, null)) {
            return realVariableUnwrapVariable;
        }
        return null;
    }

    public static Unit v(ExitSafeCallNode exitSafeCallNode, FirDataFlowAnalyzer firDataFlowAnalyzer, FirSafeCallExpression firSafeCallExpression, FlowPath flowPath, MutableFlow mutableFlow) {
        DataFlowVariable orCreateVariable;
        flowPath.getClass();
        mutableFlow.getClass();
        if (exitSafeCallNode.getPreviousNodes().size() >= 2 && (orCreateVariable = firDataFlowAnalyzer.getOrCreateVariable(mutableFlow, firSafeCallExpression)) != null) {
            PersistentFlow flow = firDataFlowAnalyzer.getFlow(CFGNodeKt.getLastPreviousNode(exitSafeCallNode), flowPath);
            firDataFlowAnalyzer.addAllConditionally(mutableFlow, ModelKt.notEq(orCreateVariable, null), flow);
            Collection<Implication> implications = flow.getImplications(orCreateVariable);
            if (implications != null) {
                for (Implication implication : implications) {
                    if (implication.getCondition().getOperation() != Operation.EqNull) {
                        firDataFlowAnalyzer.addImplication(mutableFlow, implication);
                    }
                }
            }
            return Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }

    public static Unit w(boolean z, FirElvisExpression firElvisExpression, FirDataFlowAnalyzer firDataFlowAnalyzer, ElvisExitNode elvisExitNode, FlowPath flowPath, MutableFlow mutableFlow) {
        DataFlowVariable variableIfUsedOrReal;
        DataFlowVariable variableIfUsedOrReal2;
        flowPath.getClass();
        mutableFlow.getClass();
        if (z) {
            return Unit.INSTANCE;
        }
        SyntheticVariable syntheticVariable = new SyntheticVariable(firElvisExpression);
        ConeKotlinType coneTypeOrNull = firElvisExpression.getRhs().getConeTypeOrNull();
        if (coneTypeOrNull != null && ConeBuiltinTypeUtilsKt.isNullableNothing(coneTypeOrNull) && (variableIfUsedOrReal2 = firDataFlowAnalyzer.getVariableIfUsedOrReal(mutableFlow, firElvisExpression.getLhs())) != null) {
            firDataFlowAnalyzer.addImplication(mutableFlow, ModelKt.implies(ModelKt.notEq(syntheticVariable, null), ModelKt.notEq(variableIfUsedOrReal2, null)));
        }
        ConeKotlinType coneTypeOrNull2 = firElvisExpression.getLhs().getConeTypeOrNull();
        if (coneTypeOrNull2 != null && ConeBuiltinTypeUtilsKt.isNullableNothing(coneTypeOrNull2) && (variableIfUsedOrReal = firDataFlowAnalyzer.getVariableIfUsedOrReal(mutableFlow, firElvisExpression.getRhs())) != null) {
            firDataFlowAnalyzer.addImplication(mutableFlow, ModelKt.implies(ModelKt.notEq(syntheticVariable, null), ModelKt.notEq(variableIfUsedOrReal, null)));
        }
        FirExpression rhs = firElvisExpression.getRhs();
        FirLiteralExpression firLiteralExpression = rhs instanceof FirLiteralExpression ? (FirLiteralExpression) rhs : null;
        Object value = firLiteralExpression != null ? firLiteralExpression.getValue() : null;
        Boolean bool = value instanceof Boolean ? (Boolean) value : null;
        if (bool != null) {
            firDataFlowAnalyzer.addAllConditionally(mutableFlow, ModelKt.eq(syntheticVariable, !bool.booleanValue()), firDataFlowAnalyzer.getFlow(CFGNodeKt.getFirstPreviousNode(elvisExitNode), flowPath));
        }
        return Unit.INSTANCE;
    }

    public static RealVariable x(FirDataFlowAnalyzer firDataFlowAnalyzer, Flow flow, RealVariable realVariable) {
        realVariable.getClass();
        return firDataFlowAnalyzer.unwrapVariableIfStable(flow, realVariable);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static Unit y(FirFunction firFunction, FirDataFlowAnalyzer firDataFlowAnalyzer, Set set, FlowPath flowPath, MutableFlow mutableFlow) throws KotlinIllegalArgumentExceptionWithAttachments {
        flowPath.getClass();
        mutableFlow.getClass();
        if (firFunction instanceof FirAnonymousFunction) {
            FirAnonymousFunction firAnonymousFunction = (FirAnonymousFunction) firFunction;
            EventOccurrencesRange invocationKind = firAnonymousFunction.getInvocationKind();
            if (invocationKind == null || EventOccurrencesRangeKt.canBeRevisited(invocationKind)) {
                firDataFlowAnalyzer.enterRepeatableStatement(mutableFlow, set);
            }
            FirQualifiedAccessExpression lambdaArgumentParent = DeclarationAttributesKt.getLambdaArgumentParent(firAnonymousFunction);
            if (lambdaArgumentParent != null) {
                firDataFlowAnalyzer.processConditionalContract(mutableFlow, lambdaArgumentParent, null, firAnonymousFunction);
            }
        }
        return Unit.INSTANCE;
    }

    public static Unit z(CFGNode cFGNode, FirDataFlowAnalyzer firDataFlowAnalyzer, FlowPath flowPath, MutableFlow mutableFlow) {
        DataFlowVariable variableIfUsed;
        flowPath.getClass();
        mutableFlow.getClass();
        Object objSingleOrNull = CollectionsKt.singleOrNull(cFGNode.getPreviousNodes());
        WhenBranchConditionExitNode whenBranchConditionExitNode = objSingleOrNull instanceof WhenBranchConditionExitNode ? (WhenBranchConditionExitNode) objSingleOrNull : null;
        if (whenBranchConditionExitNode == null) {
            return Unit.INSTANCE;
        }
        FirExpression condition = whenBranchConditionExitNode.getFir().getCondition();
        if (ConeBuiltinTypeUtilsKt.isBoolean(FirTypeUtilsKt.getResolvedType(condition)) && (variableIfUsed = firDataFlowAnalyzer.getVariableIfUsed(mutableFlow, condition)) != null) {
            firDataFlowAnalyzer.commitOperationStatement(mutableFlow, ModelKt.eq(variableIfUsed, false));
            return Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }

    public final SmartCastStatement buildSmartCastStatement(Flow flow, DataFlowVariable variable, TypeStatement typeStatement) {
        flow.getClass();
        variable.getClass();
        Set<ConeKotlinType> upperTypes = typeStatement != null ? typeStatement.getUpperTypes() : null;
        SmartcastStability stability = upperTypes != null ? getStability(variable, flow, upperTypes) : SmartcastStability.STABLE_VALUE;
        SmartcastStability stability2 = getStability(variable, flow, null);
        Set<DfaType> lowerTypes = typeStatement != null ? typeStatement.getLowerTypes() : null;
        DataFlowVariable dataFlowVariableUnwrapVariable = flow.unwrapVariable(variable);
        Set<DfaType> setInferLowerTypesFromVariable = Intrinsics.areEqual(dataFlowVariableUnwrapVariable, variable) ? null : inferLowerTypesFromVariable(dataFlowVariableUnwrapVariable);
        if ((upperTypes == null || !(!upperTypes.isEmpty())) && ((lowerTypes == null || !(!lowerTypes.isEmpty())) && (setInferLowerTypesFromVariable == null || !(!setInferLowerTypesFromVariable.isEmpty())))) {
            return null;
        }
        if (upperTypes == null) {
            upperTypes = SetsKt.emptySet();
        }
        if (lowerTypes == null) {
            lowerTypes = SetsKt.emptySet();
        }
        if (setInferLowerTypesFromVariable == null) {
            setInferLowerTypesFromVariable = SetsKt.emptySet();
        }
        return new SmartCastStatement(upperTypes, stability, SetsKt.plus(lowerTypes, setInferLowerTypesFromVariable), stability2);
    }

    public final void enterAnnotation() {
        mergeIncomingFlow$default(this, getGraphBuilder().enterFakeExpression(), null, 1, null);
    }

    public final void enterAnonymousFunctionExpression(FirAnonymousFunctionExpression anonymousFunctionExpression) {
        anonymousFunctionExpression.getClass();
        Pair<AnonymousFunctionExpressionNode, AnonymousFunctionCaptureNode> pairEnterAnonymousFunctionExpression = getGraphBuilder().enterAnonymousFunctionExpression(anonymousFunctionExpression);
        AnonymousFunctionExpressionNode anonymousFunctionExpressionNode = (AnonymousFunctionExpressionNode) pairEnterAnonymousFunctionExpression.component1();
        AnonymousFunctionCaptureNode anonymousFunctionCaptureNode = (AnonymousFunctionCaptureNode) pairEnterAnonymousFunctionExpression.component2();
        if (anonymousFunctionCaptureNode != null) {
            mergeIncomingFlow$default(this, anonymousFunctionCaptureNode, null, 1, null);
        }
        if (anonymousFunctionExpressionNode != null) {
            mergeIncomingFlow$default(this, anonymousFunctionExpressionNode, null, 1, null);
        }
    }

    public final void enterBlock(FirBlock block) {
        block.getClass();
        mergeIncomingFlow$default(this, getGraphBuilder().enterBlock(block), null, 1, null);
    }

    public final void enterBooleanOperatorExpression(FirBooleanOperatorExpression booleanOperatorExpression) {
        booleanOperatorExpression.getClass();
        mergeIncomingFlow$default(this, getGraphBuilder().enterBooleanOperatorExpression(booleanOperatorExpression), null, 1, null);
    }

    public final void enterCallArguments(FirStatement call, List<? extends FirExpression> arguments) {
        call.getClass();
        arguments.getClass();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = arguments.iterator();
        while (it.hasNext()) {
            FirAnonymousFunction firAnonymousFunctionUnwrapAnonymousFunctionExpression = FirExpressionUtilKt.unwrapAnonymousFunctionExpression((FirExpression) it.next());
            if (firAnonymousFunctionUnwrapAnonymousFunctionExpression != null) {
                arrayList.add(firAnonymousFunctionUnwrapAnonymousFunctionExpression);
            }
        }
        ControlFlowGraphBuilder graphBuilder = getGraphBuilder();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            linkedHashSet.add(((FirAnonymousFunction) it2.next()).getSymbol());
        }
        graphBuilder.enterCall(linkedHashSet);
        this.context.getVariableAssignmentAnalyzer().enterFunctionCall(arrayList);
        FunctionCallArgumentsEnterNode functionCallArgumentsEnterNodeEnterCallArguments = getGraphBuilder().enterCallArguments(call, arrayList);
        if (functionCallArgumentsEnterNodeEnterCallArguments != null) {
            mergeIncomingFlow$default(this, functionCallArgumentsEnterNodeEnterCallArguments, null, 1, null);
        }
    }

    public final void enterCatchClause(FirCatch firCatch) {
        firCatch.getClass();
        mergeIncomingFlow$default(this, getGraphBuilder().enterCatchClause(firCatch), null, 1, null);
    }

    public final void enterCheckNotNullCall() {
        ControlFlowGraphBuilder.enterCall$default(getGraphBuilder(), null, 1, null);
    }

    public final void enterClass(FirClass klass, boolean buildGraph) {
        klass.getClass();
        Pair<CFGNode<?>, ClassEnterNode> pairEnterClass = getGraphBuilder().enterClass(klass, buildGraph);
        CFGNode cFGNode = (CFGNode) pairEnterClass.component1();
        ClassEnterNode classEnterNode = (ClassEnterNode) pairEnterClass.component2();
        if (cFGNode != null) {
            mergeIncomingFlow$default(this, cFGNode, null, 1, null);
        }
        if (classEnterNode != null) {
            mergeIncomingFlow$default(this, classEnterNode, null, 1, null);
        }
        this.context.getVariableAssignmentAnalyzer().enterClass(klass);
    }

    public final void enterCodeFragment(final FirCodeFragment codeFragment) {
        codeFragment.getClass();
        this.context.getVariableAssignmentAnalyzer().enterCodeFragment(codeFragment);
        mergeIncomingFlow(getGraphBuilder().enterCodeFragment(codeFragment), new Function2() { // from class: n05
            public final Object invoke(Object obj, Object obj2) {
                return FirDataFlowAnalyzer.e(codeFragment, this, (FlowPath) obj, (MutableFlow) obj2);
            }
        });
    }

    public final void enterContractDescription() {
        mergeIncomingFlow$default(this, getGraphBuilder().enterFakeExpression(), null, 1, null);
    }

    public final void enterDelegateExpression() {
        getGraphBuilder().enterDelegateExpression();
    }

    public final void enterDoWhileLoop(FirLoop loop) {
        loop.getClass();
        final Set<FirPropertySymbol> setEnterLoop = this.context.getVariableAssignmentAnalyzer().enterLoop(loop);
        Pair<LoopEnterNode, LoopBlockEnterNode> pairEnterDoWhileLoop = getGraphBuilder().enterDoWhileLoop(loop);
        LoopEnterNode loopEnterNode = (LoopEnterNode) pairEnterDoWhileLoop.component1();
        LoopBlockEnterNode loopBlockEnterNode = (LoopBlockEnterNode) pairEnterDoWhileLoop.component2();
        mergeIncomingFlow(loopEnterNode, new Function2() { // from class: s05
            public final Object invoke(Object obj, Object obj2) {
                return FirDataFlowAnalyzer.F(this.b, setEnterLoop, (FlowPath) obj, (MutableFlow) obj2);
            }
        });
        mergeIncomingFlow$default(this, loopBlockEnterNode, null, 1, null);
    }

    public final void enterDoWhileLoopCondition(FirLoop loop) {
        loop.getClass();
        Pair<LoopBlockExitNode, LoopConditionEnterNode> pairEnterDoWhileLoopCondition = getGraphBuilder().enterDoWhileLoopCondition(loop);
        LoopBlockExitNode loopBlockExitNode = (LoopBlockExitNode) pairEnterDoWhileLoopCondition.component1();
        LoopConditionEnterNode loopConditionEnterNode = (LoopConditionEnterNode) pairEnterDoWhileLoopCondition.component2();
        mergeIncomingFlow$default(this, loopBlockExitNode, null, 1, null);
        mergeIncomingFlow$default(this, loopConditionEnterNode, null, 1, null);
    }

    public final void enterElvis(FirElvisExpression elvisExpression) {
        elvisExpression.getClass();
        getGraphBuilder().enterElvis(elvisExpression);
    }

    public final void enterEqualityOperatorCall() {
        ControlFlowGraphBuilder.enterCall$default(getGraphBuilder(), null, 1, null);
    }

    public final void enterField(FirField field) {
        field.getClass();
        FieldInitializerEnterNode fieldInitializerEnterNodeEnterField = getGraphBuilder().enterField(field);
        if (fieldInitializerEnterNodeEnterField != null) {
            mergeIncomingFlow$default(this, fieldInitializerEnterNodeEnterField, null, 1, null);
        }
    }

    public final void enterFile(FirFile file, boolean buildGraph) {
        file.getClass();
        FileEnterNode fileEnterNodeEnterFile = getGraphBuilder().enterFile(file, buildGraph);
        if (fileEnterNodeEnterFile != null) {
            mergeIncomingFlow$default(this, fileEnterNodeEnterFile, null, 1, null);
        }
    }

    public final void enterFinallyBlock() {
        FinallyBlockEnterNode finallyBlockEnterNodeEnterFinallyBlock = getGraphBuilder().enterFinallyBlock();
        mergeIncomingFlow$default(this, finallyBlockEnterNodeEnterFinallyBlock, null, 1, null);
        createAlternateFlows$default(this, finallyBlockEnterNodeEnterFinallyBlock, null, 1, null);
    }

    public final void enterFunction(final FirFunction function) {
        function.getClass();
        if (function instanceof FirDefaultPropertyAccessor) {
            return;
        }
        final Set<FirPropertySymbol> setEnterFunction = this.context.getVariableAssignmentAnalyzer().enterFunction(function);
        Pair<LocalFunctionDeclarationNode, FunctionEnterNode> pairEnterFunction = function instanceof FirAnonymousFunction ? TuplesKt.to(null, getGraphBuilder().enterAnonymousFunction((FirAnonymousFunction) function)) : getGraphBuilder().enterFunction(function);
        LocalFunctionDeclarationNode localFunctionDeclarationNode = (LocalFunctionDeclarationNode) pairEnterFunction.component1();
        FunctionEnterNode functionEnterNode = (FunctionEnterNode) pairEnterFunction.component2();
        if (localFunctionDeclarationNode != null) {
            mergeIncomingFlow$default(this, localFunctionDeclarationNode, null, 1, null);
        }
        mergeIncomingFlow(functionEnterNode, new Function2() { // from class: y05
            public final Object invoke(Object obj, Object obj2) {
                return FirDataFlowAnalyzer.y(function, this, setEnterFunction, (FlowPath) obj, (MutableFlow) obj2);
            }
        });
    }

    public final void enterFunctionCall(FirFunctionCall functionCall) {
        functionCall.getClass();
        mergeIncomingFlow$default(this, getGraphBuilder().enterFunctionCall(functionCall), null, 1, null);
    }

    public final void enterInitBlock(FirAnonymousInitializer initBlock) {
        initBlock.getClass();
        this.context.getVariableAssignmentAnalyzer().enterAnonymousInitializer(initBlock);
        mergeIncomingFlow$default(this, getGraphBuilder().enterInitBlock(initBlock), null, 1, null);
    }

    public final void enterJump(FirJump<?> jump) {
        jump.getClass();
        getGraphBuilder().enterJump(jump);
    }

    public final void enterLocalVariableDeclaration(FirProperty variable) {
        variable.getClass();
        mergeIncomingFlow$default(this, getGraphBuilder().enterVariableDeclaration(variable), null, 1, null);
    }

    public final void enterProperty(FirProperty property) {
        property.getClass();
        PropertyInitializerEnterNode propertyInitializerEnterNodeEnterProperty = getGraphBuilder().enterProperty(property);
        if (propertyInitializerEnterNodeEnterProperty != null) {
            mergeIncomingFlow$default(this, propertyInitializerEnterNodeEnterProperty, null, 1, null);
        }
    }

    public final void enterSafeCallAfterNullCheck(final FirSafeCallExpression safeCall) {
        safeCall.getClass();
        mergeIncomingFlow(getGraphBuilder().enterSafeCall(safeCall), new Function2() { // from class: p05
            public final Object invoke(Object obj, Object obj2) {
                return FirDataFlowAnalyzer.A(this.b, safeCall, (FlowPath) obj, (MutableFlow) obj2);
            }
        });
    }

    public final void enterScript(FirScript script, boolean buildGraph) {
        script.getClass();
        ScriptEnterNode scriptEnterNodeEnterScript = getGraphBuilder().enterScript(script, buildGraph);
        if (scriptEnterNodeEnterScript != null) {
            mergeIncomingFlow$default(this, scriptEnterNodeEnterScript, null, 1, null);
        }
    }

    public final void enterStringConcatenationCall() {
        ControlFlowGraphBuilder.enterCall$default(getGraphBuilder(), null, 1, null);
    }

    public final void enterTryExpression(FirTryExpression tryExpression) {
        tryExpression.getClass();
        Pair<TryExpressionEnterNode, TryMainBlockEnterNode> pairEnterTryExpression = getGraphBuilder().enterTryExpression(tryExpression);
        TryExpressionEnterNode tryExpressionEnterNode = (TryExpressionEnterNode) pairEnterTryExpression.component1();
        TryMainBlockEnterNode tryMainBlockEnterNode = (TryMainBlockEnterNode) pairEnterTryExpression.component2();
        mergeIncomingFlow$default(this, tryExpressionEnterNode, null, 1, null);
        mergeIncomingFlow$default(this, tryMainBlockEnterNode, null, 1, null);
    }

    public final void enterValueParameter(FirValueParameter valueParameter) {
        valueParameter.getClass();
        Pair<EnterValueParameterNode, EnterDefaultArgumentsNode> pairEnterValueParameter = getGraphBuilder().enterValueParameter(valueParameter);
        if (pairEnterValueParameter == null) {
            return;
        }
        EnterValueParameterNode enterValueParameterNode = (EnterValueParameterNode) pairEnterValueParameter.component1();
        EnterDefaultArgumentsNode enterDefaultArgumentsNode = (EnterDefaultArgumentsNode) pairEnterValueParameter.component2();
        mergeIncomingFlow$default(this, enterValueParameterNode, null, 1, null);
        mergeIncomingFlow$default(this, enterDefaultArgumentsNode, null, 1, null);
    }

    public final void enterWhenBranchCondition(FirWhenBranch whenBranch) {
        whenBranch.getClass();
        mergeWhenBranchEntryFlow(getGraphBuilder().enterWhenBranchCondition(whenBranch));
    }

    public final void enterWhenExpression(FirWhenExpression whenExpression) {
        whenExpression.getClass();
        mergeIncomingFlow$default(this, getGraphBuilder().enterWhenExpression(whenExpression), null, 1, null);
    }

    public final void enterWhileLoop(FirLoop loop) {
        loop.getClass();
        final Set<FirPropertySymbol> setEnterLoop = this.context.getVariableAssignmentAnalyzer().enterLoop(loop);
        Pair<LoopEnterNode, LoopConditionEnterNode> pairEnterWhileLoop = getGraphBuilder().enterWhileLoop(loop);
        LoopEnterNode loopEnterNode = (LoopEnterNode) pairEnterWhileLoop.component1();
        LoopConditionEnterNode loopConditionEnterNode = (LoopConditionEnterNode) pairEnterWhileLoop.component2();
        mergeIncomingFlow$default(this, loopEnterNode, null, 1, null);
        mergeIncomingFlow(loopConditionEnterNode, new Function2() { // from class: k05
            public final Object invoke(Object obj, Object obj2) {
                return FirDataFlowAnalyzer.p(this.b, setEnterLoop, (FlowPath) obj, (MutableFlow) obj2);
            }
        });
    }

    public final void exitAnnotation() {
        getGraphBuilder().exitFakeExpression();
        resetSmartCastPosition();
    }

    public final void exitAnonymousObjectExpression(FirAnonymousObjectExpression anonymousObjectExpression) {
        anonymousObjectExpression.getClass();
        AnonymousObjectExpressionExitNode anonymousObjectExpressionExitNodeExitAnonymousObjectExpression = getGraphBuilder().exitAnonymousObjectExpression(anonymousObjectExpression);
        if (anonymousObjectExpressionExitNodeExitAnonymousObjectExpression != null) {
            mergeIncomingFlow$default(this, anonymousObjectExpressionExitNodeExitAnonymousObjectExpression, null, 1, null);
        }
    }

    public final void exitBlock(FirBlock block) {
        block.getClass();
        mergeIncomingFlow$default(this, getGraphBuilder().exitBlock(block), null, 1, null);
    }

    public final void exitBooleanOperatorExpression(FirBooleanOperatorExpression booleanOperatorExpression) {
        booleanOperatorExpression.getClass();
        mergeBooleanLogicOperatorFlow(getGraphBuilder().exitBooleanOperatorExpression(booleanOperatorExpression));
    }

    public final void exitCallArguments() {
        Pair<SplitPostponedLambdasNode, FunctionCallArgumentsExitNode> pairExitCallArguments = getGraphBuilder().exitCallArguments();
        SplitPostponedLambdasNode splitPostponedLambdasNode = (SplitPostponedLambdasNode) pairExitCallArguments.component1();
        FunctionCallArgumentsExitNode functionCallArgumentsExitNode = (FunctionCallArgumentsExitNode) pairExitCallArguments.component2();
        if (splitPostponedLambdasNode != null) {
            mergeIncomingFlow$default(this, splitPostponedLambdasNode, null, 1, null);
        }
        if (functionCallArgumentsExitNode != null) {
            mergeIncomingFlow$default(this, functionCallArgumentsExitNode, null, 1, null);
            resetSmartCastPositionTo(functionCallArgumentsExitNode.getExplicitReceiverExitNode().getFlow());
        }
    }

    public final void exitCallExplicitReceiver() {
        getGraphBuilder().exitCallExplicitReceiver();
    }

    public final void exitCallableReference(FirCallableReferenceAccess callableReferenceAccess) {
        callableReferenceAccess.getClass();
        mergeIncomingFlow$default(this, getGraphBuilder().exitCallableReference(callableReferenceAccess), null, 1, null);
    }

    public final void exitCatchClause(FirCatch firCatch) {
        firCatch.getClass();
        mergeIncomingFlow$default(this, getGraphBuilder().exitCatchClause(firCatch), null, 1, null);
    }

    public final void exitCheckNotNullCall(final FirCheckNotNullCall checkNotNullCall, boolean callCompleted) {
        checkNotNullCall.getClass();
        mergeIncomingFlow(getGraphBuilder().exitCheckNotNullCall(checkNotNullCall, callCompleted), new Function2() { // from class: q05
            public final Object invoke(Object obj, Object obj2) {
                return FirDataFlowAnalyzer.t(this.b, checkNotNullCall, (FlowPath) obj, (MutableFlow) obj2);
            }
        });
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final ControlFlowGraph exitClass() throws UninitializedPropertyAccessException {
        this.context.getVariableAssignmentAnalyzer().exitClass();
        Pair<ClassExitNode, ControlFlowGraph> pairExitClass = getGraphBuilder().exitClass();
        ClassExitNode classExitNode = (ClassExitNode) pairExitClass.component1();
        ControlFlowGraph controlFlowGraph = (ControlFlowGraph) pairExitClass.component2();
        if (classExitNode != null) {
            mergeIncomingFlow$default(this, classExitNode, null, 1, null);
        } else {
            resetSmartCastPosition();
        }
        if (controlFlowGraph != null) {
            completePostponedNodes(controlFlowGraph);
        }
        return controlFlowGraph;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final ControlFlowGraph exitCodeFragment(FirCodeFragment codeFragment) throws UninitializedPropertyAccessException {
        codeFragment.getClass();
        this.context.getVariableAssignmentAnalyzer().exitCodeFragment(codeFragment);
        Pair<CodeFragmentExitNode, ControlFlowGraph> pairExitCodeFragment = getGraphBuilder().exitCodeFragment();
        CodeFragmentExitNode codeFragmentExitNode = (CodeFragmentExitNode) pairExitCodeFragment.component1();
        ControlFlowGraph controlFlowGraph = (ControlFlowGraph) pairExitCodeFragment.component2();
        mergeIncomingFlow$default(this, codeFragmentExitNode, null, 1, null);
        completePostponedNodes(controlFlowGraph);
        return controlFlowGraph;
    }

    public final void exitComparisonExpressionCall(FirComparisonExpression comparisonExpression) {
        comparisonExpression.getClass();
        mergeIncomingFlow$default(this, getGraphBuilder().exitComparisonExpression(comparisonExpression), null, 1, null);
    }

    public final void exitContractDescription() {
        getGraphBuilder().exitFakeExpression();
    }

    public final void exitDelegateExpression(FirExpression fir) {
        fir.getClass();
        mergeIncomingFlow$default(this, getGraphBuilder().exitDelegateExpression(fir), null, 1, null);
    }

    public final void exitDelegatedConstructorCall(FirDelegatedConstructorCall call, boolean callCompleted) {
        call.getClass();
        this.context.getVariableAssignmentAnalyzer().exitFunctionCall(callCompleted);
        mergeIncomingFlow$default(this, getGraphBuilder().exitDelegatedConstructorCall(call, callCompleted), null, 1, null);
    }

    public final void exitDoWhileLoop(FirLoop loop) {
        loop.getClass();
        this.context.getVariableAssignmentAnalyzer().exitLoop();
        Pair<LoopConditionExitNode, LoopExitNode> pairExitDoWhileLoop = getGraphBuilder().exitDoWhileLoop(loop);
        final LoopConditionExitNode loopConditionExitNode = (LoopConditionExitNode) pairExitDoWhileLoop.component1();
        final LoopExitNode loopExitNode = (LoopExitNode) pairExitDoWhileLoop.component2();
        mergeIncomingFlow$default(this, loopConditionExitNode, null, 1, null);
        mergeIncomingFlow(loopExitNode, new Function2() { // from class: k15
            public final Object invoke(Object obj, Object obj2) {
                return FirDataFlowAnalyzer.l(this.b, loopExitNode, loopConditionExitNode, (FlowPath) obj, (MutableFlow) obj2);
            }
        });
    }

    public final void exitElvis(final FirElvisExpression elvisExpression, final boolean isLhsNotNull, boolean callCompleted) {
        elvisExpression.getClass();
        final ElvisExitNode elvisExitNodeExitElvis = getGraphBuilder().exitElvis(isLhsNotNull, callCompleted);
        mergeIncomingFlow(elvisExitNodeExitElvis, new Function2() { // from class: o15
            public final Object invoke(Object obj, Object obj2) {
                return FirDataFlowAnalyzer.w(isLhsNotNull, elvisExpression, this, elvisExitNodeExitElvis, (FlowPath) obj, (MutableFlow) obj2);
            }
        });
    }

    public final void exitElvisLhs(final FirElvisExpression elvisExpression) {
        elvisExpression.getClass();
        Triple<ElvisLhsExitNode, ElvisLhsIsNotNullNode, ElvisRhsEnterNode> tripleExitElvisLhs = getGraphBuilder().exitElvisLhs(elvisExpression);
        ElvisLhsExitNode elvisLhsExitNode = (ElvisLhsExitNode) tripleExitElvisLhs.component1();
        ElvisLhsIsNotNullNode elvisLhsIsNotNullNode = (ElvisLhsIsNotNullNode) tripleExitElvisLhs.component2();
        ElvisRhsEnterNode elvisRhsEnterNode = (ElvisRhsEnterNode) tripleExitElvisLhs.component3();
        mergeIncomingFlow$default(this, elvisLhsExitNode, null, 1, null);
        mergeIncomingFlow(elvisLhsIsNotNullNode, new Function2() { // from class: l05
            public final Object invoke(Object obj, Object obj2) {
                return FirDataFlowAnalyzer.B(this.b, elvisExpression, (FlowPath) obj, (MutableFlow) obj2);
            }
        });
        mergeIncomingFlow(elvisRhsEnterNode, new Function2() { // from class: m05
            public final Object invoke(Object obj, Object obj2) {
                return FirDataFlowAnalyzer.i(this.b, elvisExpression, (FlowPath) obj, (MutableFlow) obj2);
            }
        });
    }

    public final void exitEqualityOperatorCall(final FirEqualityOperatorCall equalityOperatorCall, boolean callCompleted) {
        equalityOperatorCall.getClass();
        Pair<CFGNode<?>, EqualityOperatorCallNode> pairExitEqualityOperatorCall = getGraphBuilder().exitEqualityOperatorCall(equalityOperatorCall, callCompleted);
        final CFGNode cFGNode = (CFGNode) pairExitEqualityOperatorCall.component1();
        EqualityOperatorCallNode equalityOperatorCallNode = (EqualityOperatorCallNode) pairExitEqualityOperatorCall.component2();
        final FirOperation operation = equalityOperatorCall.getOperation();
        final FirExpression firExpression = equalityOperatorCall.getArgumentList().getArguments().get(0);
        final boolean z = true;
        final FirExpression firExpression2 = equalityOperatorCall.getArgumentList().getArguments().get(1);
        FirExpression whenSubject = firExpression instanceof FirWhenSubjectExpression ? FirExpressionUtilKt.getWhenSubject((FirWhenSubjectExpression) firExpression) : firExpression;
        final FirLiteralExpression firLiteralExpression = whenSubject instanceof FirLiteralExpression ? (FirLiteralExpression) whenSubject : null;
        final FirLiteralExpression firLiteralExpression2 = firExpression2 instanceof FirLiteralExpression ? (FirLiteralExpression) firExpression2 : null;
        ConstantValueKind kind = firLiteralExpression != null ? firLiteralExpression.getKind() : null;
        ConstantValueKind.Null r6 = ConstantValueKind.Null.INSTANCE;
        boolean zAreEqual = Intrinsics.areEqual(kind, r6);
        boolean zAreEqual2 = Intrinsics.areEqual(firLiteralExpression2 != null ? firLiteralExpression2.getKind() : null, r6);
        if (!zAreEqual && (!ConeBuiltinTypeUtilsKt.isNullableNothing(FirTypeUtilsKt.getResolvedType(firExpression)) || zAreEqual2)) {
            z = false;
        }
        final boolean z2 = zAreEqual2 || (ConeBuiltinTypeUtilsKt.isNullableNothing(FirTypeUtilsKt.getResolvedType(firExpression2)) && !zAreEqual);
        mergeIncomingFlow(equalityOperatorCallNode, new Function2() { // from class: m15
            public final Object invoke(Object obj, Object obj2) {
                return FirDataFlowAnalyzer.r(z, firLiteralExpression, z2, firLiteralExpression2, this, equalityOperatorCall, firExpression2, operation, cFGNode, firExpression, (FlowPath) obj, (MutableFlow) obj2);
            }
        });
    }

    public final void exitEqualityOperatorLhs() {
        getGraphBuilder().exitEqualityOperatorLhs();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final ControlFlowGraph exitField(FirField field) throws UninitializedPropertyAccessException {
        field.getClass();
        Pair<FieldInitializerExitNode, ControlFlowGraph> pairExitField = getGraphBuilder().exitField(field);
        if (pairExitField == null) {
            return null;
        }
        FieldInitializerExitNode fieldInitializerExitNode = (FieldInitializerExitNode) pairExitField.component1();
        ControlFlowGraph controlFlowGraph = (ControlFlowGraph) pairExitField.component2();
        mergeIncomingFlow$default(this, fieldInitializerExitNode, null, 1, null);
        completePostponedNodes(controlFlowGraph);
        return controlFlowGraph;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final ControlFlowGraph exitFile() throws UninitializedPropertyAccessException {
        Pair<FileExitNode, ControlFlowGraph> pairExitFile = getGraphBuilder().exitFile();
        FileExitNode fileExitNode = (FileExitNode) pairExitFile.component1();
        ControlFlowGraph controlFlowGraph = (ControlFlowGraph) pairExitFile.component2();
        if (fileExitNode != null) {
            mergeIncomingFlow$default(this, fileExitNode, null, 1, null);
        } else {
            resetSmartCastPosition();
        }
        if (controlFlowGraph != null) {
            completePostponedNodes(controlFlowGraph);
        }
        return controlFlowGraph;
    }

    public final void exitFinallyBlock() {
        mergeIncomingFlow$default(this, getGraphBuilder().exitFinallyBlock(), null, 1, null);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final FirControlFlowGraphReference exitFunction(FirFunction function) throws UninitializedPropertyAccessException {
        function.getClass();
        if (function instanceof FirDefaultPropertyAccessor) {
            return null;
        }
        this.context.getVariableAssignmentAnalyzer().exitFunction();
        if (!(function instanceof FirAnonymousFunction)) {
            Pair<FunctionExitNode, ControlFlowGraph> pairExitFunction = getGraphBuilder().exitFunction(function);
            FunctionExitNode functionExitNode = (FunctionExitNode) pairExitFunction.component1();
            ControlFlowGraph controlFlowGraph = (ControlFlowGraph) pairExitFunction.component2();
            mergeIncomingFlow$default(this, functionExitNode, null, 1, null);
            completePostponedNodes(controlFlowGraph);
            resetSmartCastPosition();
            return new FirControlFlowGraphReferenceImpl(controlFlowGraph);
        }
        Triple<FunctionExitNode, PostponedLambdaExitNode, ControlFlowGraph> tripleExitAnonymousFunction = getGraphBuilder().exitAnonymousFunction((FirAnonymousFunction) function);
        FunctionExitNode functionExitNode2 = (FunctionExitNode) tripleExitAnonymousFunction.component1();
        PostponedLambdaExitNode postponedLambdaExitNode = (PostponedLambdaExitNode) tripleExitAnonymousFunction.component2();
        ControlFlowGraph controlFlowGraph2 = (ControlFlowGraph) tripleExitAnonymousFunction.component3();
        mergeIncomingFlow$default(this, functionExitNode2, null, 1, null);
        if (postponedLambdaExitNode != null) {
            mergeIncomingFlow$default(this, postponedLambdaExitNode, null, 1, null);
        }
        resetSmartCastPosition();
        return new FirControlFlowGraphReferenceImpl(controlFlowGraph2);
    }

    public final void exitFunctionCall(final FirFunctionCall functionCall, boolean callCompleted) {
        functionCall.getClass();
        this.context.getVariableAssignmentAnalyzer().exitFunctionCall(callCompleted);
        final FunctionCallExitNode functionCallExitNodeExitFunctionCall = getGraphBuilder().exitFunctionCall(functionCall, callCompleted);
        mergeIncomingFlow(functionCallExitNodeExitFunctionCall, new Function2() { // from class: l15
            public final Object invoke(Object obj, Object obj2) {
                return FirDataFlowAnalyzer.D(functionCallExitNodeExitFunctionCall, this, functionCall, (FlowPath) obj, (MutableFlow) obj2);
            }
        });
    }

    public final void exitGetClassCall(FirGetClassCall getClassCall) {
        getClassCall.getClass();
        mergeIncomingFlow$default(this, getGraphBuilder().exitGetClassCall(getClassCall), null, 1, null);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final ControlFlowGraph exitInitBlock(FirAnonymousInitializer initBlock) throws UninitializedPropertyAccessException {
        initBlock.getClass();
        this.context.getVariableAssignmentAnalyzer().exitAnonymousInitializer(initBlock);
        Pair<InitBlockExitNode, ControlFlowGraph> pairExitInitBlock = getGraphBuilder().exitInitBlock();
        InitBlockExitNode initBlockExitNode = (InitBlockExitNode) pairExitInitBlock.component1();
        ControlFlowGraph controlFlowGraph = (ControlFlowGraph) pairExitInitBlock.component2();
        mergeIncomingFlow$default(this, initBlockExitNode, null, 1, null);
        completePostponedNodes(controlFlowGraph);
        return controlFlowGraph;
    }

    public final void exitJump(FirJump<?> jump) {
        jump.getClass();
        mergeIncomingFlow$default(this, getGraphBuilder().exitJump(jump), null, 1, null);
    }

    public final void exitLeftBooleanOperatorExpressionArgument(final FirBooleanOperatorExpression booleanOperatorExpression) {
        booleanOperatorExpression.getClass();
        Pair<CFGNode<FirBooleanOperatorExpression>, CFGNode<FirBooleanOperatorExpression>> pairExitLeftBooleanOperatorExpressionArgument = getGraphBuilder().exitLeftBooleanOperatorExpressionArgument(booleanOperatorExpression);
        CFGNode cFGNode = (CFGNode) pairExitLeftBooleanOperatorExpressionArgument.component1();
        CFGNode<?> cFGNode2 = (CFGNode) pairExitLeftBooleanOperatorExpressionArgument.component2();
        mergeIncomingFlow$default(this, cFGNode, null, 1, null);
        mergeIncomingFlow(cFGNode2, new Function2() { // from class: t05
            public final Object invoke(Object obj, Object obj2) {
                return FirDataFlowAnalyzer.c(this.b, booleanOperatorExpression, (FlowPath) obj, (MutableFlow) obj2);
            }
        });
    }

    public final void exitLiteralExpression(FirLiteralExpression literalExpression) {
        literalExpression.getClass();
        if (FirTypeUtilsKt.getHasResolvedType(literalExpression)) {
            return;
        }
        mergeIncomingFlow$default(this, getGraphBuilder().exitLiteralExpression(literalExpression), null, 1, null);
    }

    public final void exitLocalVariableDeclaration(final FirProperty variable, final boolean hadExplicitType) {
        variable.getClass();
        mergeIncomingFlow(getGraphBuilder().exitVariableDeclaration(variable), new Function2() { // from class: c15
            public final Object invoke(Object obj, Object obj2) {
                return FirDataFlowAnalyzer.d(variable, this, hadExplicitType, (FlowPath) obj, (MutableFlow) obj2);
            }
        });
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final ControlFlowGraph exitProperty(FirProperty property) throws UninitializedPropertyAccessException {
        property.getClass();
        Pair<PropertyInitializerExitNode, ControlFlowGraph> pairExitProperty = getGraphBuilder().exitProperty(property);
        if (pairExitProperty == null) {
            return null;
        }
        PropertyInitializerExitNode propertyInitializerExitNode = (PropertyInitializerExitNode) pairExitProperty.component1();
        ControlFlowGraph controlFlowGraph = (ControlFlowGraph) pairExitProperty.component2();
        mergeIncomingFlow$default(this, propertyInitializerExitNode, null, 1, null);
        completePostponedNodes(controlFlowGraph);
        return controlFlowGraph;
    }

    public final void exitQualifiedAccessExpression(final FirQualifiedAccessExpression qualifiedAccessExpression) {
        qualifiedAccessExpression.getClass();
        mergeIncomingFlow(getGraphBuilder().exitQualifiedAccessExpression(qualifiedAccessExpression), new Function2() { // from class: u05
            public final Object invoke(Object obj, Object obj2) {
                return FirDataFlowAnalyzer.k(this.b, qualifiedAccessExpression, (FlowPath) obj, (MutableFlow) obj2);
            }
        });
    }

    public final void exitResolvedQualifierNode(FirResolvedQualifier resolvedQualifier) {
        resolvedQualifier.getClass();
        mergeIncomingFlow$default(this, getGraphBuilder().exitResolvedQualifierNode(resolvedQualifier), null, 1, null);
    }

    public final void exitSafeCall(final FirSafeCallExpression safeCall) {
        safeCall.getClass();
        final ExitSafeCallNode exitSafeCallNodeExitSafeCall = getGraphBuilder().exitSafeCall();
        mergeIncomingFlow(exitSafeCallNodeExitSafeCall, new Function2() { // from class: x05
            public final Object invoke(Object obj, Object obj2) {
                return FirDataFlowAnalyzer.v(exitSafeCallNodeExitSafeCall, this, safeCall, (FlowPath) obj, (MutableFlow) obj2);
            }
        });
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public final ControlFlowGraph exitScript() throws UninitializedPropertyAccessException, KotlinIllegalArgumentExceptionWithAttachments {
        Pair<ScriptExitNode, ControlFlowGraph> pairExitScript = getGraphBuilder().exitScript();
        ScriptExitNode scriptExitNode = (ScriptExitNode) pairExitScript.component1();
        ControlFlowGraph controlFlowGraph = (ControlFlowGraph) pairExitScript.component2();
        if (scriptExitNode != null) {
            mergeIncomingFlow$default(this, scriptExitNode, null, 1, null);
        }
        if (controlFlowGraph != null) {
            completePostponedNodes(controlFlowGraph);
        }
        return controlFlowGraph;
    }

    public final void exitSmartCastExpression(FirSmartCastExpression smartCastExpression) {
        smartCastExpression.getClass();
        mergeIncomingFlow$default(this, getGraphBuilder().exitSmartCastExpression(smartCastExpression), null, 1, null);
    }

    public final void exitStringConcatenationCall(FirStringConcatenationCall call) {
        call.getClass();
        mergeIncomingFlow$default(this, getGraphBuilder().exitStringConcatenationCall(call), null, 1, null);
    }

    public final void exitThrowExceptionNode(FirThrowExpression throwExpression) {
        throwExpression.getClass();
        mergeIncomingFlow$default(this, getGraphBuilder().exitThrowExceptionNode(throwExpression), null, 1, null);
    }

    public final void exitTryExpression(boolean callCompleted) {
        mergeIncomingFlow$default(this, getGraphBuilder().exitTryExpression(callCompleted), null, 1, null);
    }

    public final void exitTryMainBlock() {
        mergeIncomingFlow$default(this, getGraphBuilder().exitTryMainBlock(), null, 1, null);
    }

    public final void exitTypeOperatorCall(final FirTypeOperatorCall typeOperatorCall) {
        typeOperatorCall.getClass();
        mergeIncomingFlow(getGraphBuilder().exitTypeOperatorCall(typeOperatorCall), new Function2() { // from class: o05
            public final Object invoke(Object obj, Object obj2) {
                return FirDataFlowAnalyzer.j(typeOperatorCall, this, (FlowPath) obj, (MutableFlow) obj2);
            }
        });
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final ControlFlowGraph exitValueParameter(FirValueParameter valueParameter) throws UninitializedPropertyAccessException {
        valueParameter.getClass();
        Triple<ExitDefaultArgumentsNode, ExitValueParameterNode, ControlFlowGraph> tripleExitValueParameter = getGraphBuilder().exitValueParameter(valueParameter);
        if (tripleExitValueParameter == null) {
            return null;
        }
        ExitDefaultArgumentsNode exitDefaultArgumentsNode = (ExitDefaultArgumentsNode) tripleExitValueParameter.component1();
        ExitValueParameterNode exitValueParameterNode = (ExitValueParameterNode) tripleExitValueParameter.component2();
        ControlFlowGraph controlFlowGraph = (ControlFlowGraph) tripleExitValueParameter.component3();
        mergeIncomingFlow$default(this, exitDefaultArgumentsNode, null, 1, null);
        mergeIncomingFlow$default(this, exitValueParameterNode, null, 1, null);
        completePostponedNodes(controlFlowGraph);
        return controlFlowGraph;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void exitVariableAssignment(final FirVariableAssignment assignment) {
        FirPropertySymbol resolvedPropertySymbol$default;
        assignment.getClass();
        FirReference calleeReference = ReferenceUtilsKt.getCalleeReference(assignment);
        final FirProperty firProperty = null;
        if (calleeReference != null && (resolvedPropertySymbol$default = FirReferenceUtilsKt.toResolvedPropertySymbol$default(calleeReference, false, 1, null)) != null) {
            firProperty = (FirProperty) resolvedPropertySymbol$default.getFir();
        }
        if (firProperty != null && FirPropertyBodyResolveStateKt.isEffectivelyLocal(firProperty)) {
            this.context.getVariableAssignmentAnalyzer().visitAssignment(firProperty, RefinedTypeForDataFlowTypeAttributeKt.getRefinedTypeForDataFlowOrSelf(FirTypeUtilsKt.getResolvedType(assignment.getRValue())));
        }
        mergeIncomingFlow(getGraphBuilder().exitVariableAssignment(assignment), new Function2() { // from class: b15
            public final Object invoke(Object obj, Object obj2) {
                return FirDataFlowAnalyzer.h(firProperty, this, assignment, (FlowPath) obj, (MutableFlow) obj2);
            }
        });
    }

    public final void exitWhenBranchCondition(final FirWhenBranch whenBranch) {
        whenBranch.getClass();
        Pair<WhenBranchConditionExitNode, WhenBranchResultEnterNode> pairExitWhenBranchCondition = getGraphBuilder().exitWhenBranchCondition(whenBranch);
        WhenBranchConditionExitNode whenBranchConditionExitNode = (WhenBranchConditionExitNode) pairExitWhenBranchCondition.component1();
        WhenBranchResultEnterNode whenBranchResultEnterNode = (WhenBranchResultEnterNode) pairExitWhenBranchCondition.component2();
        mergeIncomingFlow$default(this, whenBranchConditionExitNode, null, 1, null);
        mergeIncomingFlow(whenBranchResultEnterNode, new Function2() { // from class: i15
            public final Object invoke(Object obj, Object obj2) {
                return FirDataFlowAnalyzer.u(whenBranch, this, (FlowPath) obj, (MutableFlow) obj2);
            }
        });
    }

    public final void exitWhenBranchResult(FirWhenBranch whenBranch) {
        whenBranch.getClass();
        mergeIncomingFlow$default(this, getGraphBuilder().exitWhenBranchResult(whenBranch), null, 1, null);
    }

    public final void exitWhenExpression(FirWhenExpression whenExpression, boolean callCompleted) {
        whenExpression.getClass();
        Pair<WhenExitNode, WhenSyntheticElseBranchNode> pairExitWhenExpression = getGraphBuilder().exitWhenExpression(whenExpression, callCompleted);
        WhenExitNode whenExitNode = (WhenExitNode) pairExitWhenExpression.component1();
        WhenSyntheticElseBranchNode whenSyntheticElseBranchNode = (WhenSyntheticElseBranchNode) pairExitWhenExpression.component2();
        if (whenSyntheticElseBranchNode != null) {
            mergeWhenBranchEntryFlow(whenSyntheticElseBranchNode);
        }
        mergeIncomingFlow$default(this, whenExitNode, null, 1, null);
    }

    public final void exitWhenSubjectExpression(FirWhenSubjectExpression expression) {
        expression.getClass();
        mergeIncomingFlow$default(this, getGraphBuilder().exitWhenSubjectExpression(expression), null, 1, null);
    }

    public final void exitWhileLoop(FirLoop loop) {
        loop.getClass();
        final Set<FirPropertySymbol> setExitLoop = this.context.getVariableAssignmentAnalyzer().exitLoop();
        Triple<LoopConditionEnterNode, LoopBlockExitNode, LoopExitNode> tripleExitWhileLoop = getGraphBuilder().exitWhileLoop(loop);
        final LoopConditionEnterNode loopConditionEnterNode = (LoopConditionEnterNode) tripleExitWhileLoop.component1();
        LoopBlockExitNode loopBlockExitNode = (LoopBlockExitNode) tripleExitWhileLoop.component2();
        final LoopExitNode loopExitNode = (LoopExitNode) tripleExitWhileLoop.component3();
        mergeIncomingFlow$default(this, loopBlockExitNode, null, 1, null);
        mergeIncomingFlow(loopExitNode, new Function2() { // from class: n15
            public final Object invoke(Object obj, Object obj2) {
                return FirDataFlowAnalyzer.q(this.b, loopExitNode, loopConditionEnterNode, setExitLoop, (FlowPath) obj, (MutableFlow) obj2);
            }
        });
    }

    public final void exitWhileLoopCondition(final FirLoop loop) {
        loop.getClass();
        Pair<LoopConditionExitNode, LoopBlockEnterNode> pairExitWhileLoopCondition = getGraphBuilder().exitWhileLoopCondition(loop);
        LoopConditionExitNode loopConditionExitNode = (LoopConditionExitNode) pairExitWhileLoopCondition.component1();
        LoopBlockEnterNode loopBlockEnterNode = (LoopBlockEnterNode) pairExitWhileLoopCondition.component2();
        mergeIncomingFlow$default(this, loopConditionExitNode, null, 1, null);
        mergeIncomingFlow(loopBlockEnterNode, new Function2() { // from class: g15
            public final Object invoke(Object obj, Object obj2) {
                return FirDataFlowAnalyzer.g(loop, this, (FlowPath) obj, (MutableFlow) obj2);
            }
        });
    }

    public TypeStatement extractTypeStatementFrom(Flow flow, DataFlowVariable variable) {
        flow.getClass();
        variable.getClass();
        return flow.getTypeStatement(variable);
    }

    public final FirAbstractBodyResolveTransformer.BodyResolveTransformerComponents getComponents() {
        return this.components;
    }

    public final Flow getCurrentSmartCastPosition() {
        return this.currentSmartCastPosition;
    }

    public abstract LogicSystem getLogicSystem();

    public final DataFlowVariable getOrCreateVariable(FirExpression fir) {
        fir.getClass();
        Flow flow = this.currentSmartCastPosition;
        if (flow != null) {
            return getOrCreateVariable(flow, fir);
        }
        return null;
    }

    public abstract ImplicitValueStorage getReceiverStack();

    @Override // org.jetbrains.kotlin.fir.SessionHolder
    public FirSession getSession() {
        return this.components.getSession();
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0029  */
    /* JADX WARN: Code duplicated, block: B:14:0x0030 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:15:0x0031  */
    public final SmartCastStatement getTypeUsingSmartcastInfo(FirExpression expression, Function2<? super DataFlowVariable, ? super TypeStatement, ? extends TypeStatement> augmentTypeStatement) {
        TypeStatement typeStatement;
        expression.getClass();
        augmentTypeStatement.getClass();
        Flow currentSmartCastPosition = getCurrentSmartCastPosition();
        TypeStatement typeStatement2 = null;
        if (currentSmartCastPosition == null) {
            return null;
        }
        DataFlowVariable syntheticVariable = new SyntheticVariable(expression);
        TypeStatement typeStatement3 = (TypeStatement) augmentTypeStatement.invoke(syntheticVariable, extractTypeStatementFrom(currentSmartCastPosition, syntheticVariable));
        if (typeStatement3 == null) {
            syntheticVariable = getVariableWithoutUnwrappingAlias(currentSmartCastPosition, expression, false);
            if (syntheticVariable == null) {
                return null;
            }
            typeStatement = (TypeStatement) augmentTypeStatement.invoke(syntheticVariable, extractTypeStatementFrom(currentSmartCastPosition, syntheticVariable));
            if (typeStatement != null && typeStatement.isNotEmpty()) {
                typeStatement2 = typeStatement;
            }
            typeStatement3 = typeStatement2;
        } else {
            if (!typeStatement3.isNotEmpty()) {
                typeStatement3 = null;
            }
            if (typeStatement3 == null) {
                syntheticVariable = getVariableWithoutUnwrappingAlias(currentSmartCastPosition, expression, false);
                if (syntheticVariable == null) {
                    return null;
                }
                typeStatement = (TypeStatement) augmentTypeStatement.invoke(syntheticVariable, extractTypeStatementFrom(currentSmartCastPosition, syntheticVariable));
                if (typeStatement != null) {
                    typeStatement2 = typeStatement;
                }
                typeStatement3 = typeStatement2;
            }
        }
        return buildSmartCastStatement(currentSmartCastPosition, syntheticVariable, typeStatement3);
    }

    public final DataFlowVariable getVariableWithoutUnwrappingAlias(final Flow flow, FirExpression firExpression, boolean z) {
        flow.getClass();
        firExpression.getClass();
        return getVariableStorage().get(firExpression, z, new Function1() { // from class: d15
            public final Object invoke(Object obj) {
                return FirDataFlowAnalyzer.s((RealVariable) obj);
            }
        }, new Function1() { // from class: e15
            public final Object invoke(Object obj) {
                return FirDataFlowAnalyzer.x(this.b, flow, (RealVariable) obj);
            }
        });
    }

    public abstract void implicitUpdated(TypeStatement info);

    public final void resetSmartCastPosition() {
        CFGNode<?> lastNodeOrNull = getGraphBuilder().getLastNodeOrNull();
        resetSmartCastPositionTo(lastNodeOrNull != null ? lastNodeOrNull.getFlow() : null);
    }

    public final Collection<FirAnonymousFunctionReturnExpressionInfo> returnExpressionsOfAnonymousFunction(FirAnonymousFunction function) {
        function.getClass();
        Collection<FirAnonymousFunctionReturnExpressionInfo> collectionReturnExpressionsOfAnonymousFunction = getGraphBuilder().returnExpressionsOfAnonymousFunction(function);
        if (collectionReturnExpressionsOfAnonymousFunction != null) {
            return collectionReturnExpressionsOfAnonymousFunction;
        }
        b88.a("anonymous function ", UtilsKt.render(function), " not analyzed");
        return null;
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/dfa/FirDataFlowAnalyzer$Companion;", Argument.Delimiters.none, "<init>", "()V", "createFirDataFlowAnalyzer", "Lorg/jetbrains/kotlin/fir/resolve/dfa/FirDataFlowAnalyzer;", "components", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirAbstractBodyResolveTransformer$BodyResolveTransformerComponents;", "dataFlowAnalyzerContext", "Lorg/jetbrains/kotlin/fir/resolve/dfa/DataFlowAnalyzerContext;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final FirDataFlowAnalyzer createFirDataFlowAnalyzer(FirAbstractBodyResolveTransformer.BodyResolveTransformerComponents components, DataFlowAnalyzerContext dataFlowAnalyzerContext) {
            components.getClass();
            dataFlowAnalyzerContext.getClass();
            return new FirDataFlowAnalyzer$Companion$createFirDataFlowAnalyzer$1(components, dataFlowAnalyzerContext);
        }

        private Companion() {
        }
    }

    private final DataFlowVariable getOrCreateVariable(Flow flow, FirExpression firExpression) {
        return getVariable(flow, firExpression, true);
    }

    private final void addAllConditionally(MutableFlow mutableFlow, OperationStatement operationStatement, Flow flow) {
        addAllConditionally(mutableFlow, operationStatement, getTypeStatementsNotInheritedFrom(mutableFlow, flow));
    }
}
