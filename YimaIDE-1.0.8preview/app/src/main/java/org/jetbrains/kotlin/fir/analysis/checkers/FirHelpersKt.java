package org.jetbrains.kotlin.fir.analysis.checkers;

import com.intellij.lang.LighterASTNode;
import com.intellij.psi.tree.TokenSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtLightSourceElement;
import org.jetbrains.kotlin.KtNodeTypes;
import org.jetbrains.kotlin.KtPsiSourceElement;
import org.jetbrains.kotlin.KtRealPsiSourceElement;
import org.jetbrains.kotlin.KtRealSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKind;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.descriptors.annotations.KotlinTarget;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory3;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory4;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.diagnostics.LightTreePositioningStrategiesKt;
import org.jetbrains.kotlin.diagnostics.SourceElementPositioningStrategy;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirFunctionTypeParameter;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.SessionAndScopeSessionHolder;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.analysis.FirSourceUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.DelegateFieldsMapKt;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousInitializer;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousObject;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.declarations.utils.FirStatusUtilsKt;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.ConeSimpleDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.DiagnosticKind;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationArgumentMapping;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCallOrigin;
import org.jetbrains.kotlin.fir.expressions.FirGetClassCall;
import org.jetbrains.kotlin.fir.expressions.FirIntegerLiteralOperatorCall;
import org.jetbrains.kotlin.fir.expressions.FirJump;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.FirReturnExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.FirSuperReceiverExpression;
import org.jetbrains.kotlin.fir.expressions.FirVariableAssignment;
import org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt;
import org.jetbrains.kotlin.fir.expressions.impl.FirEmptyExpressionBlock;
import org.jetbrains.kotlin.fir.references.FirControlFlowGraphReference;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ContainingClassUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.dfa.FirControlFlowGraphReferenceImplKt;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.BlockExitNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraph;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.FinallyBlockEnterNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.FinallyBlockExitNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.JumpNode;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope;
import org.jetbrains.kotlin.fir.scopes.FirKotlinScopeProviderKt;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.FirTypeScopeKt;
import org.jetbrains.kotlin.fir.scopes.MemberWithBaseScope;
import org.jetbrains.kotlin.fir.scopes.ProcessorAction;
import org.jetbrains.kotlin.fir.scopes.ScopesKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirAbstractImportingScopeKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirDeclaredMemberScopeProviderKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirDelegatedMemberScopeKt;
import org.jetbrains.kotlin.fir.scopes.impl.ImportedFromObjectOrStaticData;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousInitializerSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousObjectSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbolKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFileSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirIntersectionCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirLocalPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirScriptSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeCapturedType;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeInferenceContext;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeConflictingProjection;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeIntersector;
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjectionKt;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeTypesKt;
import org.jetbrains.kotlin.fir.types.FirFunctionTypeRef;
import org.jetbrains.kotlin.fir.types.FirQualifierPart;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.fir.types.FirTypeProjectionWithVariance;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirUserTypeRef;
import org.jetbrains.kotlin.fir.types.FunctionalTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.InferenceUtilsKt;
import org.jetbrains.kotlin.fir.types.ProjectionKind;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.lexer.KtTokens;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.SpecialNames;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.psi.KtExpression;
import org.jetbrains.kotlin.psi.KtParameter;
import org.jetbrains.kotlin.resolve.AnnotationTargetList;
import org.jetbrains.kotlin.resolve.AnnotationTargetLists;
import org.jetbrains.kotlin.types.AbstractTypeChecker;
import org.jetbrains.kotlin.types.ConstantValueKind;
import org.jetbrains.kotlin.types.TypeApproximatorConfiguration;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;
import org.jetbrains.kotlin.types.model.TypeCheckerProviderContext;
import org.jetbrains.kotlin.util.ImplementationStatus;
import org.jetbrains.kotlin.util.LightTreeUtilsKt;
import org.jetbrains.kotlin.util.OperatorNameConventions;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000ª\u0003\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\b\u001a\u001b\u0010\u0002\u001a\u00020\u0003*\u00020\u0006R\u00020\u0004j\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\u0007\u001a\u001f\u0010\u0002\u001a\u00020\u0003*\u0006\u0012\u0002\b\u00030\bR\u00020\u0004j\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\t\u001a\u0016\u0010\u0002\u001a\u00020\u0003*\u0006\u0012\u0002\b\u00030\b2\u0006\u0010\u0005\u001a\u00020\u0004\u001a\u001f\u0010\n\u001a\u00020\u000b*\u0006\u0012\u0002\b\u00030\bR\u00020\u0004j\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\f\u001a\u0018\u0010\r\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u000e*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011\u001a\"\u0010\u0012\u001a\u00020\u0013*\u0006\u0012\u0002\b\u00030\b2\n\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\b2\u0006\u0010\u0010\u001a\u00020\u0011\u001a\u0012\u0010\u0015\u001a\u00020\u0013*\u00020\u00162\u0006\u0010\u0010\u001a\u00020\u0011\u001a\u0012\u0010\u0017\u001a\u00020\u0013*\u00020\u00162\u0006\u0010\u0010\u001a\u00020\u0011\u001a\u0014\u0010\u0018\u001a\u00020\u0013*\u00020\u00162\u0006\u0010\u0010\u001a\u00020\u0011H\u0002\u001a\u0012\u0010\u0019\u001a\u00020\u0013*\u00020\u00162\u0006\u0010\u0010\u001a\u00020\u0011\u001a4\u0010\u0019\u001a\u00020\u0013*\u00020\u00162\u0016\u0010\u001a\u001a\u0012\u0012\u0004\u0012\u00020\u00160\u001bj\b\u0012\u0004\u0012\u00020\u0016`\u001c2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u0013H\u0002\u001a%\u0010\u001e\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u000e*\u0006\u0012\u0002\b\u00030\u000eR\u00020\u0004j\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\u001f\u001a\u0010\u0010 \u001a\b\u0012\u0002\b\u0003\u0018\u00010\b*\u00020\u0004\u001a1\u0010!\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030#0\"*\u00020$2\n\u0010%\u001a\u0006\u0012\u0002\b\u00030\bR\u00020\u0004j\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010&\u001a\u0018\u0010'\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0006\u0012\u0004\u0018\u00010)0(*\u00020\u0006\u001a\f\u0010*\u001a\u0004\u0018\u00010+*\u00020\u0006\u001a\u0010\u0010*\u001a\u0004\u0018\u00010+*\u0006\u0012\u0002\b\u00030\b\u001a)\u0010,\u001a\b\u0012\u0004\u0012\u00020+0-*\u00020.2\u0006\u0010/\u001a\u00020+R\u00020\u0004j\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u00100\u001a\f\u00101\u001a\u00020\u0013*\u000202H\u0002\u001a\u001d\u00103\u001a\u0004\u0018\u00010\u000f*\u00020\u0006R\u00020\u0004j\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u00104\u001a\u0012\u00108\u001a\u00020\u0013*\u00020\u00162\u0006\u0010\u0010\u001a\u00020\u0011\u001a\u001c\u0010\u0012\u001a\u00020\u0013*\u00020=2\u0006\u0010\u0005\u001a\u00020>2\b\u0010?\u001a\u0004\u0018\u00010=\u001a\u0012\u0010@\u001a\u00020\u0013*\u00020A2\u0006\u0010\u0010\u001a\u00020\u0011\u001a\u0016\u0010@\u001a\u00020\u0013*\u0006\u0012\u0002\b\u00030B2\u0006\u0010\u0010\u001a\u00020\u0011\u001a\u001e\u0010C\u001a\u00020\u00132\u0006\u0010\u0005\u001a\u00020D2\u0006\u0010E\u001a\u00020\u00162\u0006\u0010F\u001a\u00020\u0016\u001a+\u0010G\u001a\u00020H*\u0006\u0012\u0002\b\u00030B2\n\u0010K\u001a\u0006\u0012\u0002\b\u00030\bR\u00020Ij\u0006\u0010J\u001a\u00020I¢\u0006\u0002\u0010L\u001a\u0016\u0010M\u001a\u00020\u0013*\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030B0NH\u0002\u001a\u0014\u0010b\u001a\u0004\u0018\u00010_*\u00020\u00042\u0006\u0010c\u001a\u00020d\u001aM\u0010e\u001a\u00020f2\u0006\u0010i\u001a\u00020\u00162\b\u0010j\u001a\u0004\u0018\u00010k2\u0006\u0010l\u001a\u00020m2\u0006\u0010n\u001a\u00020o2\u0006\u0010p\u001a\u00020\u0013R\u00020\u0004R\u00020gj\u0006\u0010\u0005\u001a\u00020\u0004j\u0006\u0010h\u001a\u00020g¢\u0006\u0002\u0010q\u001a=\u0010r\u001a\u00020\u00132\u0006\u0010s\u001a\u00020\u00162\u0006\u0010l\u001a\u00020m2\u0006\u0010t\u001a\u00020\u0016H\u0002R\u00020\u0004R\u00020gj\u0006\u0010\u0005\u001a\u00020\u0004j\u0006\u0010h\u001a\u00020g¢\u0006\u0002\u0010u\u001a\n\u0010v\u001a\u00020w*\u00020x\u001a-\u0010y\u001a\u00020f2\u0006\u0010z\u001a\u00020mH\u0000R\u00020\u0004R\u00020gj\u0006\u0010\u0005\u001a\u00020\u0004j\u0006\u0010h\u001a\u00020g¢\u0006\u0002\u0010{\u001a\u0014\u0010|\u001a\b\u0012\u0004\u0012\u00020}0N2\u0006\u0010~\u001a\u00020\u007f\u001a\u0019\u0010|\u001a\n\u0012\u0004\u0012\u00020}\u0018\u00010N2\t\u0010\u0080\u0001\u001a\u0004\u0018\u00010\u000f\u001a\u000f\u0010\u008a\u0001\u001a\u00020\u0013*\u0006\u0012\u0002\b\u00030#\u001a*\u0010\u008d\u0001\u001a\u00030\u008e\u00012\f\u0010\u0090\u0001\u001a\u0007\u0012\u0002\b\u00030\u0087\u0001R\u00030\u008f\u0001j\u0007\u0010J\u001a\u00030\u008f\u0001¢\u0006\u0003\u0010\u0091\u0001\u001a&\u0010\u008d\u0001\u001a\u00030\u008e\u00012\b\u0010\u0090\u0001\u001a\u00030\u0092\u0001R\u00030\u008f\u0001j\u0007\u0010J\u001a\u00030\u008f\u0001¢\u0006\u0003\u0010\u0093\u0001\u001a\u001a\u0010\u008d\u0001\u001a\u00030\u008e\u00012\b\u0010\u0090\u0001\u001a\u00030\u0092\u00012\u0006\u0010\u0010\u001a\u00020\u0011\u001a\f\u0010\u0096\u0001\u001a\u00020\u0013*\u00030\u0097\u0001\u001a\u0015\u0010\u009b\u0001\u001a\u0005\u0018\u00010\u009c\u00012\u0007\u0010n\u001a\u00030\u009c\u0001H\u0002\u001a+\u0010\u009d\u0001\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030B0N*\u0006\u0012\u0002\b\u00030BR\u00020\u0004j\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0003\u0010\u009e\u0001\u001a#\u0010\u009f\u0001\u001a\b\u0012\u0004\u0012\u00020$0N*\u00020$R\u00020\u0004j\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0003\u0010 \u0001\u001a\u0019\u0010\u009f\u0001\u001a\b\u0012\u0004\u0012\u00020$0N*\u00020$2\u0006\u0010\u0005\u001a\u00020\u0004\u001a%\u0010¡\u0001\u001a\t\u0012\u0005\u0012\u00030¢\u00010N*\u00030¢\u0001R\u00020\u0004j\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0003\u0010£\u0001\u001a\u001b\u0010¡\u0001\u001a\t\u0012\u0005\u0012\u00030¢\u00010N*\u00030¢\u00012\u0006\u0010\u0005\u001a\u00020\u0004\u001a;\u0010¤\u0001\u001a\u00020f*\u00020$2\u0016\b\u0004\u0010¥\u0001\u001a\u000f\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020f0¦\u0001H\u0086\bR\u00020\u0004j\u0006\u0010\u0005\u001a\u00020\u0004ø\u0001\u0000¢\u0006\u0003\u0010§\u0001\u001a4\u0010¨\u0001\u001a\u00020f*\u00020$2\u0015\u0010¥\u0001\u001a\u0010\u0012\u0004\u0012\u00020$\u0012\u0005\u0012\u00030©\u00010¦\u0001R\u00020\u0004j\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0003\u0010§\u0001\u001a6\u0010ª\u0001\u001a\u00020f*\u00030¢\u00012\u0016\u0010¥\u0001\u001a\u0011\u0012\u0005\u0012\u00030¢\u0001\u0012\u0005\u0012\u00030©\u00010¦\u0001R\u00020\u0004j\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0003\u0010«\u0001\u001a%\u0010¬\u0001\u001a\u0004\u0018\u00010\u0003*\u0006\u0012\u0002\b\u00030BH\u0002R\u00020\u0004j\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0003\u0010\u00ad\u0001\u001a\u001b\u0010±\u0001\u001a\t\u0012\u0002\b\u0003\u0018\u00010\u0087\u0001*\u00020\u00042\u0007\u0010²\u0001\u001a\u000202\u001a!\u0010µ\u0001\u001a\u00020\u0013*\u0007\u0012\u0002\b\u00030\u0087\u00012\u0007\u0010¶\u0001\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u0011\u001a\u001c\u0010µ\u0001\u001a\u00020\u0013*\u0002022\u0007\u0010¶\u0001\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u0011\u001a#\u0010·\u0001\u001a\u0004\u0018\u00010m*\u0007\u0012\u0002\b\u00030\u0087\u00012\u0007\u0010¶\u0001\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u0011\u001a#\u0010¸\u0001\u001a\u0004\u0018\u00010w*\u0007\u0012\u0002\b\u00030\u0087\u00012\u0007\u0010¶\u0001\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u0011\u001a)\u0010¹\u0001\u001a\u0004\u0018\u00010\u0013*\u0007\u0012\u0002\b\u00030\u0087\u00012\u0007\u0010¶\u0001\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0003\u0010º\u0001\u001a\u001d\u0010»\u0001\u001a\u00020\u0013*\u00020_R\u00020\u0004j\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0003\u0010¼\u0001\u001a\u0013\u0010½\u0001\u001a\u00020\u0016*\u00020\u00162\u0006\u0010\u0010\u001a\u00020\u0011\u001a\u0015\u0010¾\u0001\u001a\u0004\u0018\u00010\u0001*\u00020\u00162\u0006\u0010\u0010\u001a\u00020\u0011\u001a&\u0010¿\u0001\u001a\u00020\u0013*\u00020\u00162\b\u0010À\u0001\u001a\u00030Á\u0001\u0082\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0000\u001a\u0004\u0010\u0000(\u0000\u001a\u001d\u0010Ã\u0001\u001a\u00020\u0016*\u00020\u0016R\u00020\u0004j\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0003\u0010Ä\u0001\u001a\u001d\u0010Å\u0001\u001a\u00020\u0013*\u00020\u007fR\u00020\u0004j\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0003\u0010Æ\u0001\u001a\u001d\u0010Ç\u0001\u001a\u00020\u0013*\u00020\u007fR\u00020\u0004j\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0003\u0010Æ\u0001\u001a\u0011\u0010È\u0001\u001a\u00020\u00132\b\u0010n\u001a\u0004\u0018\u00010o\u001a\u0012\u0010Ì\u0001\u001a\b\u0012\u0004\u0012\u00020m0N*\u00030Í\u0001\u001a&\u0010Î\u0001\u001a\u00020\u0013*\u00020\u00162\u0007\u0010Ï\u0001\u001a\u00020\u0013R\u00020\u0004j\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0003\u0010Ð\u0001\u001a(\u0010Ñ\u0001\u001a\u00020\u0013*\u00020\u00162\u0007\u0010Ï\u0001\u001a\u00020\u0013H\u0002R\u00020\u0004j\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0003\u0010Ð\u0001\u001aC\u0010Ò\u0001\u001a\u00020f*\u0004\u0018\u00010o2\b\u0010Ó\u0001\u001a\u00030Ô\u00012\f\b\u0002\u0010Õ\u0001\u001a\u0005\u0018\u00010Ö\u0001R\u00020\u0004R\u00020gj\u0006\u0010\u0005\u001a\u00020\u0004j\u0006\u0010h\u001a\u00020g¢\u0006\u0003\u0010×\u0001\u001aA\u0010Ò\u0001\u001a\u00020f*\u00020_2\b\u0010Ó\u0001\u001a\u00030Ô\u00012\f\b\u0002\u0010Õ\u0001\u001a\u0005\u0018\u00010Ö\u0001R\u00020\u0004R\u00020gj\u0006\u0010\u0005\u001a\u00020\u0004j\u0006\u0010h\u001a\u00020g¢\u0006\u0003\u0010Ø\u0001\u001aU\u0010Ü\u0001\u001a\u00020f2\u0006\u0010?\u001a\u00020\u00162\b\u0010n\u001a\u0004\u0018\u00010o2\u0007\u0010Ý\u0001\u001a\u00020\u00012\u0013\u0010Þ\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010(R\u00020\u0004R\u00020gj\u0006\u0010\u0005\u001a\u00020\u0004j\u0006\u0010h\u001a\u00020g¢\u0006\u0003\u0010ß\u0001\u001a#\u0010à\u0001\u001a\u00020\u0013*\t\u0012\u0002\b\u0003\u0018\u00010\u0087\u0001\u0082\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0000\u001a\u0004\u0010\u0000(\u0001\u001a\u0012\u0010â\u0001\u001a\u00020\u0013*\t\u0012\u0002\b\u0003\u0018\u00010\u0087\u0001\u001a%\u0010ã\u0001\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u000e*\u00020\u007fR\u00030\u008f\u0001j\u0007\u0010\u0005\u001a\u00030\u008f\u0001¢\u0006\u0003\u0010ä\u0001\u001a%\u0010å\u0001\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u000e*\u00020\u007fR\u00030\u008f\u0001j\u0007\u0010\u0005\u001a\u00030\u008f\u0001¢\u0006\u0003\u0010ä\u0001\u001a\u001d\u0010æ\u0001\u001a\u00020\u0013*\u00020mR\u00020\u0004j\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0003\u0010ç\u0001\u001a\u000b\u0010è\u0001\u001a\u00020\u0013*\u00020m\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0015\u00105\u001a\u00020\u0013*\u0002068F¢\u0006\u0006\u001a\u0004\b5\u00107\"\u0015\u00109\u001a\u00020\u0013*\u00020:8F¢\u0006\u0006\u001a\u0004\b;\u0010<\"\u0018\u0010O\u001a\u00020\u0013*\u00020$8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bP\u0010Q\"\u0018\u0010R\u001a\u00020\u0013*\u00020$8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bS\u0010Q\"\u0018\u0010T\u001a\u00020\u0013*\u00020$8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bU\u0010Q\"\u0018\u0010V\u001a\u00020\u0013*\u00020$8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bW\u0010Q\"\u0015\u0010X\u001a\u00020\u0013*\u00020Y8F¢\u0006\u0006\u001a\u0004\bX\u0010Z\"\u0015\u0010[\u001a\u00020\u0013*\u00020\\8F¢\u0006\u0006\u001a\u0004\b[\u0010]\"\u0017\u0010^\u001a\u0004\u0018\u00010_*\u00020\u00048F¢\u0006\u0006\u001a\u0004\b`\u0010a\"\u001f\u0010\u0081\u0001\u001a\u0005\u0018\u00010\u0082\u0001*\u0006\u0012\u0002\b\u00030\u000e8F¢\u0006\b\u001a\u0006\b\u0083\u0001\u0010\u0084\u0001\"&\u0010\u0085\u0001\u001a\u000b\u0012\u0005\u0012\u00030\u0086\u0001\u0018\u00010N*\u0007\u0012\u0002\b\u00030\u0087\u00018F¢\u0006\b\u001a\u0006\b\u0088\u0001\u0010\u0089\u0001\"\u001b\u0010\u008b\u0001\u001a\u00020\u0013*\u00020A8BX\u0082\u0004¢\u0006\b\u001a\u0006\b\u008b\u0001\u0010\u008c\u0001\"\u001d\u0010\u0098\u0001\u001a\u0004\u0018\u00010o*\u00020o8@X\u0080\u0004¢\u0006\b\u001a\u0006\b\u0099\u0001\u0010\u009a\u0001\"\u001f\u0010®\u0001\u001a\t\u0012\u0002\b\u0003\u0018\u00010\u0087\u0001*\u00020\u00048F¢\u0006\b\u001a\u0006\b¯\u0001\u0010°\u0001\"\u0018\u0010³\u0001\u001a\u00020\u0013*\u00020\u00048F¢\u0006\b\u001a\u0006\b³\u0001\u0010´\u0001\"\u0019\u0010É\u0001\u001a\u00020\u0013*\u00030Ê\u00018F¢\u0006\b\u001a\u0006\bÉ\u0001\u0010Ë\u0001\"(\u0010Ù\u0001\u001a\u00020\u0013*\u00020\u00168@X\u0080\u0004b\u00020\u0004\u008a\u0001\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\b\u001a\u0006\bÚ\u0001\u0010Û\u0001*\u000f\b\u0002\u0010\u0094\u0001\"\u00030\u0095\u00012\u00030\u0095\u0001ò\u0001\n\n\u00030Â\u0001\n\u00030á\u0001\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006é\u0001"}, d2 = {"INLINE_ONLY_ANNOTATION_CLASS_ID", "Lorg/jetbrains/kotlin/name/ClassId;", "unsubstitutedScope", "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/declarations/FirClass;)Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;)Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "declaredMemberScope", "Lorg/jetbrains/kotlin/fir/scopes/FirContainingNamesAwareScope;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;)Lorg/jetbrains/kotlin/fir/scopes/FirContainingNamesAwareScope;", "toClassLikeSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "isSupertypeOf", Argument.Delimiters.none, "other", "isValueClass", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "isSingleFieldValueClass", "isRecursiveSingleFieldValueClassType", "isRecursiveValueClassType", "visited", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "onlyInline", "outerClassSymbol", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "findClosestClassOrObject", "overriddenFunctions", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "containingClass", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;)Ljava/util/Collection;", "collectSupertypesWithDelegates", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFieldSymbol;", "modality", "Lorg/jetbrains/kotlin/descriptors/Modality;", "redundantModalities", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirMemberDeclaration;", "defaultModality", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/declarations/FirMemberDeclaration;Lorg/jetbrains/kotlin/descriptors/Modality;)Ljava/util/Set;", "hasBody", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "findNonInterfaceSupertype", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/declarations/FirClass;)Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "isIterator", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "(Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;)Z", "isSubtypeOfThrowable", "hasValOrVar", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "getHasValOrVar", "(Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;)Z", "Lorg/jetbrains/kotlin/types/model/KotlinTypeMarker;", "Lorg/jetbrains/kotlin/types/model/TypeCheckerProviderContext;", ModuleXmlParser.TYPE, "isInlineOnly", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "isSubtypeForTypeMismatch", "Lorg/jetbrains/kotlin/fir/types/ConeInferenceContext;", "subtype", "supertype", "getImplementationStatus", "Lorg/jetbrains/kotlin/util/ImplementationStatus;", "Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;", "sessionHolder", "parentClassSymbol", "(Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;)Lorg/jetbrains/kotlin/util/ImplementationStatus;", "subjectToManyNotImplemented", Argument.Delimiters.none, "matchesDataClassSyntheticMemberSignatures", "getMatchesDataClassSyntheticMemberSignatures", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;)Z", "matchesEqualsSignature", "getMatchesEqualsSignature", "matchesHashCodeSignature", "getMatchesHashCodeSignature", "matchesToStringSignature", "getMatchesToStringSignature", "isDelegated", "Lorg/jetbrains/kotlin/name/Name;", "(Lorg/jetbrains/kotlin/name/Name;)Z", "isConflictingOrNotInvariant", "Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;", "(Lorg/jetbrains/kotlin/fir/types/ConeTypeProjection;)Z", "secondToLastContainer", "Lorg/jetbrains/kotlin/fir/FirElement;", "getSecondToLastContainer", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;)Lorg/jetbrains/kotlin/fir/FirElement;", "nthLastContainer", "n", Argument.Delimiters.none, "checkTypeMismatch", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "lValueOriginalType", "assignment", "Lorg/jetbrains/kotlin/fir/expressions/FirVariableAssignment;", "rValue", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "isInitializer", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/expressions/FirVariableAssignment;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/KtSourceElement;Z)V", "reportReturnTypeMismatchInLambda", "lValueType", "rValueType", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Z", "projectionKindAsString", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeCapturedType;", "checkCondition", "condition", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)V", "extractArgumentsTypeRefAndSource", "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirTypeRefSource;", "qualifier", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "typeRef", "classKind", "Lorg/jetbrains/kotlin/descriptors/ClassKind;", "getClassKind", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;)Lorg/jetbrains/kotlin/descriptors/ClassKind;", "typeParameterSymbols", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "getTypeParameterSymbols", "(Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;)Ljava/util/List;", "isFunctionForExpectTypeFromCastFeature", "isMember", "(Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;)Z", "getActualTargetList", "Lorg/jetbrains/kotlin/resolve/AnnotationTargetList;", "Lorg/jetbrains/kotlin/fir/SessionHolder;", "container", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;)Lorg/jetbrains/kotlin/resolve/AnnotationTargetList;", "Lorg/jetbrains/kotlin/fir/FirAnnotationContainer;", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/FirAnnotationContainer;)Lorg/jetbrains/kotlin/resolve/AnnotationTargetList;", "TargetLists", "Lorg/jetbrains/kotlin/resolve/AnnotationTargetLists;", "explicitReceiverIsNotSuperReference", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "defaultValueForParameter", "getDefaultValueForParameter", "(Lorg/jetbrains/kotlin/KtSourceElement;)Lorg/jetbrains/kotlin/KtSourceElement;", "findDefaultValue", "Lorg/jetbrains/kotlin/KtLightSourceElement;", "directOverriddenSymbolsSafe", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;)Ljava/util/List;", "directOverriddenFunctionsSafe", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;)Ljava/util/List;", "directOverriddenPropertiesSafe", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;)Ljava/util/List;", "processOverriddenFunctionsSafe", "action", "Lkotlin/Function1;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;Lkotlin/jvm/functions/Function1;)V", "processOverriddenFunctionsWithActionSafe", "Lorg/jetbrains/kotlin/fir/scopes/ProcessorAction;", "processOverriddenPropertiesWithActionSafe", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;Lkotlin/jvm/functions/Function1;)V", "containingClassUnsubstitutedScope", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;)Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "closestNonLocal", "getClosestNonLocal", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;)Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "closestNonLocalWith", "declaration", "isTopLevel", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;)Z", "hasAnnotationOrInsideAnnotatedClass", "classId", "getAnnotationFirstArgument", "getAnnotationStringParameter", "getAnnotationBooleanParameter", "(Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;Lorg/jetbrains/kotlin/name/ClassId;Lorg/jetbrains/kotlin/fir/FirSession;)Ljava/lang/Boolean;", "isLhsOfAssignment", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/FirElement;)Z", "leastUpperBound", "fullyExpandedClassId", "hasDiagnosticKind", "kind", "Lorg/jetbrains/kotlin/fir/diagnostics/DiagnosticKind;", "Lorg/jetbrains/kotlin/fir/types/ConeErrorType;", "finalApproximationOrSelf", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "isStandalone", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;)Z", "isExplicitParentOfResolvedQualifier", "isExplicitTypeArgumentSource", "isExplicit", "Lorg/jetbrains/kotlin/fir/types/FirTypeProjection;", "(Lorg/jetbrains/kotlin/fir/types/FirTypeProjection;)Z", "getReturnedExpressions", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirAnonymousFunctionSymbol;", "isMalformedExpandedType", "allowNullableNothing", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Z)Z", "containsMalformedArgument", "requireFeatureSupport", "feature", "Lorg/jetbrains/kotlin/config/LanguageFeature;", "positioningStrategy", "Lorg/jetbrains/kotlin/diagnostics/SourceElementPositioningStrategy;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/config/LanguageFeature;Lorg/jetbrains/kotlin/diagnostics/SourceElementPositioningStrategy;)V", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/FirElement;Lorg/jetbrains/kotlin/config/LanguageFeature;Lorg/jetbrains/kotlin/diagnostics/SourceElementPositioningStrategy;)V", "hasStableIdentityForAtomicOperations", "getHasStableIdentityForAtomicOperations", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Z", "checkAtomicCallReceiverForStableIdentity", "atomicReferenceClassId", "appropriateCandidatesForArgument", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/name/ClassId;Ljava/util/Map;)V", "isPrimaryConstructor", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirConstructorSymbol;", "isExpect", "resolvedSymbolOrCompanionSymbol", "(Lorg/jetbrains/kotlin/fir/SessionHolder;Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "resolvedCompanionSymbol", "isDispatchReceiver", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)Z", "hasIntegerLiteralTypeAmbiguity", "org.jetbrains.kotlin:checkers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirHelpersKt {
    private static final ClassId INLINE_ONLY_ANNOTATION_CLASS_ID = ClassId.Companion.topLevel(new FqName("kotlin.internal.InlineOnly"));

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[ClassKind.values().length];
            try {
                iArr[ClassKind.INTERFACE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[ProjectionKind.values().length];
            try {
                iArr2[ProjectionKind.OUT.ordinal()] = 1;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr2[ProjectionKind.IN.ordinal()] = 2;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr2[ProjectionKind.STAR.ordinal()] = 3;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr2[ProjectionKind.INVARIANT.ordinal()] = 4;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public static boolean a(FirTypeParameterSymbol firTypeParameterSymbol, ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        ConeRigidType coneRigidTypeLowerBoundIfFlexible = ConeTypeUtilsKt.lowerBoundIfFlexible(coneKotlinType);
        ConeTypeParameterType coneTypeParameterType = coneRigidTypeLowerBoundIfFlexible instanceof ConeTypeParameterType ? (ConeTypeParameterType) coneRigidTypeLowerBoundIfFlexible : null;
        return Intrinsics.areEqual(coneTypeParameterType != null ? coneTypeParameterType.getLookupTag() : null, firTypeParameterSymbol.getLookupTag());
    }

    public static Unit b(FirVariableSymbol firVariableSymbol) {
        firVariableSymbol.getClass();
        return Unit.INSTANCE;
    }

    public static Unit c(FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        return Unit.INSTANCE;
    }

    public static final void checkAtomicCallReceiverForStableIdentity(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, ConeKotlinType coneKotlinType, KtSourceElement ktSourceElement, ClassId classId, Map<ClassId, ClassId> map) {
        ConeKotlinType type;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        coneKotlinType.getClass();
        classId.getClass();
        map.getClass();
        ConeTypeProjection coneTypeProjection = (ConeTypeProjection) ArraysKt.firstOrNull(TypeExpansionUtilsKt.fullyExpandedType(checkerContext, coneKotlinType).getTypeArguments());
        if (coneTypeProjection == null || (type = ConeTypeProjectionKt.getType(coneTypeProjection)) == null || getHasStableIdentityForAtomicOperations(checkerContext, type)) {
            return;
        }
        KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory3<ClassId, ConeKotlinType, ClassId>) ((KtDiagnosticFactory3<Object, Object, Object>) FirErrors.INSTANCE.getATOMIC_REF_WITHOUT_CONSISTENT_IDENTITY()), classId, type, map.get(ConeTypeUtilsKt.getClassId(type)), (64 & 64) != 0 ? null : null);
    }

    public static final void checkCondition(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirExpression firExpression) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firExpression.getClass();
        ConeRigidType coneRigidTypeLowerBoundIfFlexible = ConeTypeUtilsKt.lowerBoundIfFlexible(TypeExpansionUtilsKt.fullyExpandedType(checkerContext, FirTypeUtilsKt.getResolvedType(firExpression)));
        if ((coneRigidTypeLowerBoundIfFlexible instanceof ConeErrorType) || TypeUtilsKt.isSubtypeOf((KotlinTypeMarker) coneRigidTypeLowerBoundIfFlexible, (TypeCheckerProviderContext) TypeComponentsKt.getTypeContext(checkerContext.getSession()), (KotlinTypeMarker) checkerContext.getSession().getBuiltinTypes().getBooleanType().getConeType())) {
            return;
        }
        if (firExpression instanceof FirFunctionCall) {
            FirFunctionCall firFunctionCall = (FirFunctionCall) firExpression;
            if (firFunctionCall.getOrigin() == FirFunctionCallOrigin.Operator && Intrinsics.areEqual(firFunctionCall.getCalleeReference().getName(), OperatorNameConventions.HAS_NEXT)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firFunctionCall.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getHAS_NEXT_FUNCTION_TYPE_MISMATCH(), (Object) coneRigidTypeLowerBoundIfFlexible, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                return;
            }
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firExpression.getSource(), (KtDiagnosticFactory2) FirErrors.INSTANCE.getCONDITION_TYPE_MISMATCH(), (Object) coneRigidTypeLowerBoundIfFlexible, (Object) Boolean.valueOf(ConeBuiltinTypeUtilsKt.isNullableBoolean(coneRigidTypeLowerBoundIfFlexible)), (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static final void checkTypeMismatch(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, ConeKotlinType coneKotlinType, FirVariableAssignment firVariableAssignment, FirExpression firExpression, KtSourceElement ktSourceElement, boolean z) throws KotlinIllegalArgumentExceptionWithAttachments {
        ConeKotlinType coneKotlinType2;
        ConeKotlinType coneKotlinType3;
        FirExpression dispatchReceiver;
        KtDiagnosticFactory3<ConeKotlinType, ConeKotlinType, Boolean> field_initializer_type_mismatch;
        KtSourceElement source;
        KtSourceElement source2;
        FirReference calleeReference;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        coneKotlinType.getClass();
        firExpression.getClass();
        ktSourceElement.getClass();
        ConeKotlinType resolvedType = FirTypeUtilsKt.getResolvedType(firExpression);
        if ((ktSourceElement.getKind() instanceof KtFakeSourceElementKind.DesugaredIncrementOrDecrement) && !ConeTypeUtilsKt.isMarkedOrFlexiblyNullable(coneKotlinType) && ConeTypeUtilsKt.isMarkedOrFlexiblyNullable(resolvedType)) {
            coneKotlinType3 = coneKotlinType;
            coneKotlinType2 = resolvedType;
        } else {
            coneKotlinType2 = coneKotlinType;
            coneKotlinType3 = resolvedType;
        }
        if (isSubtypeForTypeMismatch(TypeComponentsKt.getTypeContext(checkerContext.getSession()), coneKotlinType3, coneKotlinType2)) {
            return;
        }
        FirCallableSymbol resolvedCallableSymbol$default = (firVariableAssignment == null || (calleeReference = ReferenceUtilsKt.getCalleeReference(firVariableAssignment)) == null) ? null : FirReferenceUtilsKt.toResolvedCallableSymbol$default(calleeReference, false, 1, null);
        FirPropertySymbol firPropertySymbol = resolvedCallableSymbol$default instanceof FirPropertySymbol ? (FirPropertySymbol) resolvedCallableSymbol$default : null;
        if (firVariableAssignment == null || (dispatchReceiver = FirExpressionUtilKt.getExtensionReceiver(firVariableAssignment)) == null) {
            dispatchReceiver = firVariableAssignment != null ? FirExpressionUtilKt.getDispatchReceiver(firVariableAssignment) : null;
        }
        ConeKotlinType resolvedType2 = dispatchReceiver != null ? FirTypeUtilsKt.getResolvedType(dispatchReceiver) : null;
        if (firPropertySymbol != null && resolvedType2 != null && (coneKotlinType2 instanceof ConeCapturedType)) {
            ConeCapturedType coneCapturedType = (ConeCapturedType) coneKotlinType2;
            ProjectionKind kind = coneCapturedType.getConstructor().getProjection().getKind();
            if (kind == ProjectionKind.STAR || kind == ProjectionKind.OUT) {
                KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firVariableAssignment.getSource(), (KtDiagnosticFactory3<ConeKotlinType, String, FirPropertySymbol>) ((KtDiagnosticFactory3<Object, Object, Object>) FirErrors.INSTANCE.getSETTER_PROJECTED_OUT()), resolvedType2, projectionKindAsString(coneCapturedType), firPropertySymbol, (64 & 64) != 0 ? null : null);
                return;
            }
        }
        if (FirTypeUtilsKt.isNullLiteral(firExpression) && !ConeTypeUtilsKt.isMarkedOrFlexiblyNullable(coneKotlinType2)) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firExpression.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getNULL_FOR_NONNULL_TYPE(), (Object) coneKotlinType2, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            return;
        }
        KtSourceElementKind kind2 = null;
        if (!(ktSourceElement.getKind() instanceof KtFakeSourceElementKind.DesugaredIncrementOrDecrement)) {
            if (firVariableAssignment != null && (source2 = firVariableAssignment.getSource()) != null) {
                kind2 = source2.getKind();
            }
            if (!(kind2 instanceof KtFakeSourceElementKind.DesugaredIncrementOrDecrement)) {
                if (reportReturnTypeMismatchInLambda(checkerContext, diagnosticReporter, TypeExpansionUtilsKt.fullyExpandedType(checkerContext, coneKotlinType2), firExpression, TypeExpansionUtilsKt.fullyExpandedType(checkerContext, coneKotlinType3))) {
                    return;
                }
                if (z) {
                    field_initializer_type_mismatch = Intrinsics.areEqual(ktSourceElement.getElementType(), KtNodeTypes.BACKING_FIELD) ? FirErrors.INSTANCE.getFIELD_INITIALIZER_TYPE_MISMATCH() : FirErrors.INSTANCE.getINITIALIZER_TYPE_MISMATCH();
                } else {
                    field_initializer_type_mismatch = FirErrors.INSTANCE.getASSIGNMENT_TYPE_MISMATCH();
                }
                if (firVariableAssignment == null || (source = firVariableAssignment.getSource()) == null) {
                    source = ktSourceElement;
                }
                KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, (KtDiagnosticFactory3<ConeKotlinType, ConeKotlinType, Boolean>) ((KtDiagnosticFactory3<Object, Object, Object>) field_initializer_type_mismatch), coneKotlinType2, coneKotlinType3, Boolean.valueOf(InferenceUtilsKt.isTypeMismatchDueToNullability(TypeComponentsKt.getTypeContext(checkerContext.getSession()), coneKotlinType3, coneKotlinType2)), (64 & 64) != 0 ? null : null);
                return;
            }
        }
        if (!ConeTypeUtilsKt.isMarkedOrFlexiblyNullable(coneKotlinType2) && ConeTypeUtilsKt.isMarkedOrFlexiblyNullable(coneKotlinType3)) {
            ConeKotlinType coneKotlinType4 = coneKotlinType3;
            coneKotlinType3 = coneKotlinType2;
            coneKotlinType2 = coneKotlinType4;
        }
        if (ConeBuiltinTypeUtilsKt.isUnit(coneKotlinType3)) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, FirErrors.INSTANCE.getINC_DEC_SHOULD_NOT_RETURN_UNIT(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        } else {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory2) FirErrors.INSTANCE.getRESULT_TYPE_MISMATCH(), (Object) coneKotlinType2, (Object) coneKotlinType3, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
        }
    }

    public static final FirBasedSymbol<?> closestNonLocalWith(CheckerContext checkerContext, FirDeclaration firDeclaration) {
        checkerContext.getClass();
        firDeclaration.getClass();
        for (FirBasedSymbol<?> firBasedSymbol : CollectionsKt.plus(checkerContext.getContainingDeclarations(), firDeclaration.getSymbol())) {
            if ((firBasedSymbol instanceof FirCallableSymbol) || (firBasedSymbol instanceof FirAnonymousInitializerSymbol)) {
                return firBasedSymbol;
            }
        }
        return firDeclaration.getSymbol();
    }

    public static final Map<FirTypeRef, FirFieldSymbol> collectSupertypesWithDelegates(FirClass firClass) {
        firClass.getClass();
        Map<Integer, FirFieldSymbol> delegateFieldsMap = DelegateFieldsMapKt.getDelegateFieldsMap(firClass);
        if (delegateFieldsMap == null) {
            delegateFieldsMap = MapsKt.emptyMap();
        }
        List<FirTypeRef> superTypeRefs = firClass.getSuperTypeRefs();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(superTypeRefs, 10));
        int i = 0;
        for (Object obj : superTypeRefs) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            arrayList.add(TuplesKt.to((FirTypeRef) obj, delegateFieldsMap.get(Integer.valueOf(i))));
            i = i2;
        }
        return MapsKt.toMap(arrayList);
    }

    private static final FirTypeScope containingClassUnsubstitutedScope(CheckerContext checkerContext, FirCallableSymbol<?> firCallableSymbol) {
        FirClassLikeSymbol<?> containingClassSymbol = ContainingClassUtilsKt.getContainingClassSymbol(firCallableSymbol);
        FirClassSymbol firClassSymbol = containingClassSymbol instanceof FirClassSymbol ? (FirClassSymbol) containingClassSymbol : null;
        if (firClassSymbol == null) {
            return null;
        }
        return unsubstitutedScope(checkerContext, (FirClassSymbol<?>) firClassSymbol);
    }

    private static final boolean containsMalformedArgument(CheckerContext checkerContext, ConeKotlinType coneKotlinType, boolean z) {
        ConeKotlinType coneKotlinTypeFullyExpandedType;
        for (ConeTypeProjection coneTypeProjection : coneKotlinType.getTypeArguments()) {
            ConeKotlinType type = ConeTypeProjectionKt.getType(coneTypeProjection);
            if (type != null && (coneKotlinTypeFullyExpandedType = TypeExpansionUtilsKt.fullyExpandedType(checkerContext, type)) != null && isMalformedExpandedType(checkerContext, coneKotlinTypeFullyExpandedType, z)) {
                return true;
            }
        }
        return false;
    }

    public static Unit d(FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        return Unit.INSTANCE;
    }

    public static final FirContainingNamesAwareScope declaredMemberScope(CheckerContext checkerContext, FirClassSymbol<?> firClassSymbol) {
        checkerContext.getClass();
        firClassSymbol.getClass();
        return FirDeclaredMemberScopeProviderKt.declaredMemberScope(firClassSymbol, checkerContext.getSessionHolder().getSession(), FirResolvePhase.STATUS);
    }

    public static final List<FirNamedFunctionSymbol> directOverriddenFunctionsSafe(FirNamedFunctionSymbol firNamedFunctionSymbol, CheckerContext checkerContext) {
        firNamedFunctionSymbol.getClass();
        checkerContext.getClass();
        List listDirectOverriddenSymbolsSafe = directOverriddenSymbolsSafe(checkerContext, firNamedFunctionSymbol);
        listDirectOverriddenSymbolsSafe.getClass();
        return listDirectOverriddenSymbolsSafe;
    }

    public static final List<FirPropertySymbol> directOverriddenPropertiesSafe(FirPropertySymbol firPropertySymbol, CheckerContext checkerContext) {
        firPropertySymbol.getClass();
        checkerContext.getClass();
        List listDirectOverriddenSymbolsSafe = directOverriddenSymbolsSafe(checkerContext, firPropertySymbol);
        listDirectOverriddenSymbolsSafe.getClass();
        return listDirectOverriddenSymbolsSafe;
    }

    public static final List<FirCallableSymbol<?>> directOverriddenSymbolsSafe(CheckerContext checkerContext, FirCallableSymbol<?> firCallableSymbol) {
        FirTypeScope firTypeScopeContainingClassUnsubstitutedScope;
        checkerContext.getClass();
        firCallableSymbol.getClass();
        if (firCallableSymbol.getResolvedStatus().isOverride() && (firTypeScopeContainingClassUnsubstitutedScope = containingClassUnsubstitutedScope(checkerContext, firCallableSymbol)) != null) {
            firTypeScopeContainingClassUnsubstitutedScope.processFunctionsByName(firCallableSymbol.getName(), new Function1() { // from class: f85
                public final Object invoke(Object obj) {
                    return FirHelpersKt.d((FirNamedFunctionSymbol) obj);
                }
            });
            return FirTypeScopeKt.getDirectOverriddenMembers(firTypeScopeContainingClassUnsubstitutedScope, firCallableSymbol, true);
        }
        return CollectionsKt.emptyList();
    }

    public static final boolean explicitReceiverIsNotSuperReference(FirQualifiedAccessExpression firQualifiedAccessExpression) {
        firQualifiedAccessExpression.getClass();
        return !(firQualifiedAccessExpression.getExplicitReceiver() instanceof FirSuperReceiverExpression);
    }

    public static final List<FirTypeRefSource> extractArgumentsTypeRefAndSource(FirTypeRef firTypeRef) {
        if (!(firTypeRef instanceof FirResolvedTypeRef)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        FirUserTypeRef delegatedTypeRef = ((FirResolvedTypeRef) firTypeRef).getDelegatedTypeRef();
        if (!(delegatedTypeRef instanceof FirUserTypeRef)) {
            if (!(delegatedTypeRef instanceof FirFunctionTypeRef)) {
                return null;
            }
            FirFunctionTypeRef firFunctionTypeRef = (FirFunctionTypeRef) delegatedTypeRef;
            List parameters = firFunctionTypeRef.getParameters();
            for (FirTypeRef firTypeRef2 : firFunctionTypeRef.getContextParameterTypeRefs()) {
                arrayList.add(new FirTypeRefSource(firTypeRef2, firTypeRef2.getSource()));
            }
            FirTypeRef receiverTypeRef = firFunctionTypeRef.getReceiverTypeRef();
            if (receiverTypeRef != null) {
                arrayList.add(new FirTypeRefSource(receiverTypeRef, receiverTypeRef.getSource()));
            }
            Iterator it = parameters.iterator();
            while (it.hasNext()) {
                FirTypeRef returnTypeRef = ((FirFunctionTypeParameter) it.next()).getReturnTypeRef();
                arrayList.add(new FirTypeRefSource(returnTypeRef, returnTypeRef.getSource()));
            }
            FirTypeRef returnTypeRef2 = firFunctionTypeRef.getReturnTypeRef();
            arrayList.add(new FirTypeRefSource(returnTypeRef2, returnTypeRef2.getSource()));
            return arrayList;
        }
        List qualifier = delegatedTypeRef.getQualifier();
        int size = qualifier.size();
        while (true) {
            size--;
            if (-1 >= size) {
                return arrayList;
            }
            for (FirTypeProjection firTypeProjection : ((FirQualifierPart) qualifier.get(size)).getTypeArgumentList().getTypeArguments()) {
                FirTypeProjectionWithVariance firTypeProjectionWithVariance = firTypeProjection instanceof FirTypeProjectionWithVariance ? (FirTypeProjectionWithVariance) firTypeProjection : null;
                arrayList.add(new FirTypeRefSource(firTypeProjectionWithVariance != null ? firTypeProjectionWithVariance.getTypeRef() : null, firTypeProjection.getSource()));
            }
        }
    }

    public static final ConeKotlinType finalApproximationOrSelf(CheckerContext checkerContext, ConeKotlinType coneKotlinType) {
        checkerContext.getClass();
        coneKotlinType.getClass();
        ConeKotlinType coneKotlinTypeApproximateToSuperType = TypeComponentsKt.getTypeApproximator(checkerContext.getSession()).approximateToSuperType(coneKotlinType, TypeApproximatorConfiguration.FinalApproximationAfterResolutionAndInference.INSTANCE);
        return coneKotlinTypeApproximateToSuperType == null ? coneKotlinType : coneKotlinTypeApproximateToSuperType;
    }

    public static final FirClassSymbol<?> findClosestClassOrObject(CheckerContext checkerContext) {
        checkerContext.getClass();
        for (FirBasedSymbol firBasedSymbol : CollectionsKt.asReversed(checkerContext.getContainingDeclarations())) {
            if ((firBasedSymbol instanceof FirRegularClassSymbol) || (firBasedSymbol instanceof FirAnonymousObjectSymbol)) {
                return (FirClassSymbol) firBasedSymbol;
            }
        }
        return null;
    }

    private static final KtLightSourceElement findDefaultValue(KtLightSourceElement ktLightSourceElement) {
        LighterASTNode lighterASTNode;
        int startOffset = ktLightSourceElement.getStartOffset();
        Iterator it = LightTreeUtilsKt.getChildren(ktLightSourceElement.getLighterASTNode(), ktLightSourceElement.getTreeStructure()).iterator();
        int endOffset = startOffset;
        while (true) {
            if (!it.hasNext()) {
                lighterASTNode = null;
                break;
            }
            LighterASTNode lighterASTNode2 = (LighterASTNode) it.next();
            if (LightTreePositioningStrategiesKt.isExpression(lighterASTNode2)) {
                lighterASTNode = lighterASTNode2;
                break;
            }
            endOffset += lighterASTNode2.getEndOffset() - lighterASTNode2.getStartOffset();
        }
        if (lighterASTNode == null) {
            return null;
        }
        return new KtLightSourceElement(lighterASTNode, endOffset, endOffset + lighterASTNode.getTextLength(), ktLightSourceElement.getTreeStructure(), KtRealSourceElementKind.INSTANCE);
    }

    public static final FirTypeRef findNonInterfaceSupertype(CheckerContext checkerContext, FirClass firClass) {
        ConeClassLikeLookupTag lookupTag;
        FirClassSymbol<?> classSymbol;
        checkerContext.getClass();
        firClass.getClass();
        Iterator<FirTypeRef> it = firClass.getSuperTypeRefs().iterator();
        while (true) {
            if (!it.hasNext()) {
                return null;
            }
            FirTypeRef next = it.next();
            ConeKotlinType coneType = FirTypeUtilsKt.getConeType(next);
            ConeClassLikeType coneClassLikeType = coneType instanceof ConeClassLikeType ? (ConeClassLikeType) coneType : null;
            if (coneClassLikeType != null && (lookupTag = coneClassLikeType.getLookupTag()) != null && (classSymbol = ToSymbolUtilsKt.toClassSymbol(checkerContext, lookupTag)) != null && classSymbol.getClassKind() != ClassKind.INTERFACE) {
                return next;
            }
        }
    }

    public static final ClassId fullyExpandedClassId(ConeKotlinType coneKotlinType, FirSession firSession) {
        coneKotlinType.getClass();
        firSession.getClass();
        return ConeTypeUtilsKt.getClassId(TypeExpansionUtilsKt.fullyExpandedType$default(coneKotlinType, firSession, (Function1) null, 2, (Object) null));
    }

    public static final AnnotationTargetList getActualTargetList(FirAnnotationContainer firAnnotationContainer, FirSession firSession) {
        firAnnotationContainer.getClass();
        firSession.getClass();
        if (firAnnotationContainer instanceof FirBackingField) {
            FirBackingField firBackingField = (FirBackingField) firAnnotationContainer;
            if (DeclarationAttributesKt.getHasBackingField(firBackingField.getPropertySymbol())) {
                FirClassLikeSymbol<?> containingClassSymbol = ContainingClassUtilsKt.getContainingClassSymbol(firBackingField.getPropertySymbol());
                if ((containingClassSymbol != null ? getClassKind(containingClassSymbol) : null) == ClassKind.ANNOTATION_CLASS) {
                    return AnnotationTargetLists.INSTANCE.getT_MEMBER_PROPERTY_IN_ANNOTATION();
                }
                firAnnotationContainer = (FirCallableDeclaration) firAnnotationContainer;
            } else {
                firAnnotationContainer = ClassMembersKt.getPropertyIfBackingField((FirCallableDeclaration) firAnnotationContainer);
            }
        }
        if (firAnnotationContainer instanceof FirRegularClass) {
            FirRegularClass firRegularClass = (FirRegularClass) firAnnotationContainer;
            return new AnnotationTargetList(KotlinTarget.INSTANCE.classActualTargets(firRegularClass.getClassKind(), ((FirMemberDeclaration) firAnnotationContainer).getStatus().isInner(), firRegularClass.getStatus().isCompanion(), !Intrinsics.areEqual(DeclarationAttributesKt.isReplSnippetDeclaration((FirDeclaration) firAnnotationContainer), Boolean.TRUE) && firRegularClass.getIsLocal()), (List) null, (List) null, 6, (DefaultConstructorMarker) null);
        }
        if (firAnnotationContainer instanceof FirEnumEntry) {
            return new AnnotationTargetList(KotlinTarget.INSTANCE.classActualTargets(ClassKind.ENUM_ENTRY, ((FirMemberDeclaration) firAnnotationContainer).getStatus().isInner(), false, false), (List) null, (List) null, 6, (DefaultConstructorMarker) null);
        }
        if (firAnnotationContainer instanceof FirProperty) {
            FirProperty firProperty = (FirProperty) firAnnotationContainer;
            if (firProperty.getSymbol() instanceof FirLocalPropertySymbol) {
                if (Intrinsics.areEqual(firProperty.getName(), SpecialNames.DESTRUCT)) {
                    return FirLanguageSettingsComponentKt.getLanguageVersionSettings(firSession).supportsFeature(LanguageFeature.LocalVariableTargetedAnnotationOnDestructuring) ? AnnotationTargetLists.INSTANCE.getT_DESTRUCTURING_DECLARATION_NEW() : AnnotationTargetLists.INSTANCE.getT_DESTRUCTURING_DECLARATION();
                }
                Boolean boolIsCatchParameter = ClassMembersKt.isCatchParameter(firProperty);
                Boolean bool = Boolean.TRUE;
                if (Intrinsics.areEqual(boolIsCatchParameter, bool)) {
                    return AnnotationTargetLists.INSTANCE.getT_CATCH_PARAMETER();
                }
                return Intrinsics.areEqual(ClassMembersKt.isForLoopParameter(firProperty), bool) ? AnnotationTargetLists.INSTANCE.getT_VALUE_PARAMETER_WITHOUT_VAL() : AnnotationTargetLists.INSTANCE.getT_LOCAL_VARIABLE();
            }
            FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) firAnnotationContainer;
            if (!isMember(firCallableDeclaration)) {
                if (FirStatusUtilsKt.isCompanionBlockMember(firCallableDeclaration)) {
                    return AnnotationTargetLists.INSTANCE.T_MEMBER_PROPERTY(DeclarationAttributesKt.getHasBackingField(firProperty), firProperty.getDelegate() != null, true);
                }
                return AnnotationTargetLists.INSTANCE.T_TOP_LEVEL_PROPERTY(DeclarationAttributesKt.getHasBackingField(firProperty), firProperty.getDelegate() != null, FirStatusUtilsKt.isCompanionExtension(firCallableDeclaration));
            }
            KtSourceElement source = firProperty.getSource();
            if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.PropertyFromParameter.INSTANCE)) {
                return AnnotationTargetLists.INSTANCE.getT_VALUE_PARAMETER_WITH_VAL();
            }
            return AnnotationTargetLists.INSTANCE.T_MEMBER_PROPERTY(DeclarationAttributesKt.getHasBackingField(firProperty), firProperty.getDelegate() != null, false);
        }
        if (firAnnotationContainer instanceof FirValueParameter) {
            return getHasValOrVar((FirValueParameter) firAnnotationContainer) ? AnnotationTargetLists.INSTANCE.getT_VALUE_PARAMETER_WITH_VAL() : AnnotationTargetLists.INSTANCE.getT_VALUE_PARAMETER_WITHOUT_VAL();
        }
        if (firAnnotationContainer instanceof FirConstructor) {
            return AnnotationTargetLists.INSTANCE.getT_CONSTRUCTOR();
        }
        if (firAnnotationContainer instanceof FirAnonymousFunction) {
            return AnnotationTargetLists.INSTANCE.getT_FUNCTION_EXPRESSION();
        }
        if (firAnnotationContainer instanceof FirNamedFunction) {
            if (Intrinsics.areEqual(((FirNamedFunction) firAnnotationContainer).getStatus().getVisibility(), Visibilities.Local.INSTANCE)) {
                return AnnotationTargetLists.INSTANCE.getT_LOCAL_FUNCTION();
            }
            FirCallableDeclaration firCallableDeclaration2 = (FirCallableDeclaration) firAnnotationContainer;
            if (FirStatusUtilsKt.isCompanionBlockMember(firCallableDeclaration2)) {
                return AnnotationTargetLists.INSTANCE.getT_COMPANION_MEMBER_FUNCTION();
            }
            if (isMember(firCallableDeclaration2)) {
                return AnnotationTargetLists.INSTANCE.getT_MEMBER_FUNCTION();
            }
            return FirStatusUtilsKt.isCompanionExtension(firCallableDeclaration2) ? AnnotationTargetLists.INSTANCE.getT_COMPANION_EXTENSION_FUNCTION() : AnnotationTargetLists.INSTANCE.getT_TOP_LEVEL_FUNCTION();
        }
        if (firAnnotationContainer instanceof FirTypeAlias) {
            return AnnotationTargetLists.INSTANCE.getT_TYPEALIAS();
        }
        if (firAnnotationContainer instanceof FirPropertyAccessor) {
            return ((FirPropertyAccessor) firAnnotationContainer).getIsGetter() ? AnnotationTargetLists.INSTANCE.getT_PROPERTY_GETTER() : AnnotationTargetLists.INSTANCE.getT_PROPERTY_SETTER();
        }
        if (firAnnotationContainer instanceof FirBackingField) {
            return AnnotationTargetLists.INSTANCE.getT_BACKING_FIELD();
        }
        if (firAnnotationContainer instanceof FirFile) {
            return AnnotationTargetLists.INSTANCE.getT_FILE();
        }
        if (firAnnotationContainer instanceof FirTypeParameter) {
            return AnnotationTargetLists.INSTANCE.getT_TYPE_PARAMETER();
        }
        if (firAnnotationContainer instanceof FirReceiverParameter) {
            return AnnotationTargetLists.INSTANCE.getT_TYPE_REFERENCE();
        }
        if (firAnnotationContainer instanceof FirAnonymousInitializer) {
            return AnnotationTargetLists.INSTANCE.getT_INITIALIZER();
        }
        if (!(firAnnotationContainer instanceof FirAnonymousObject)) {
            return AnnotationTargetLists.INSTANCE.getEMPTY();
        }
        KtSourceElement source2 = ((FirAnonymousObject) firAnnotationContainer).getSource();
        return Intrinsics.areEqual(source2 != null ? source2.getKind() : null, KtFakeSourceElementKind.EnumInitializer.INSTANCE) ? new AnnotationTargetList(KotlinTarget.INSTANCE.classActualTargets(ClassKind.ENUM_ENTRY, false, false, false), (List) null, (List) null, 6, (DefaultConstructorMarker) null) : AnnotationTargetLists.INSTANCE.getT_OBJECT_LITERAL();
    }

    public static final Boolean getAnnotationBooleanParameter(FirBasedSymbol<?> firBasedSymbol, ClassId classId, FirSession firSession) {
        firBasedSymbol.getClass();
        classId.getClass();
        firSession.getClass();
        FirExpression annotationFirstArgument = getAnnotationFirstArgument(firBasedSymbol, classId, firSession);
        FirLiteralExpression firLiteralExpression = annotationFirstArgument instanceof FirLiteralExpression ? (FirLiteralExpression) annotationFirstArgument : null;
        Object value = firLiteralExpression != null ? firLiteralExpression.getValue() : null;
        if (value instanceof Boolean) {
            return (Boolean) value;
        }
        return null;
    }

    public static final FirExpression getAnnotationFirstArgument(FirBasedSymbol<?> firBasedSymbol, ClassId classId, FirSession firSession) {
        FirAnnotationArgumentMapping argumentMapping;
        Map<Name, FirExpression> mapping;
        Collection<FirExpression> collectionValues;
        firBasedSymbol.getClass();
        classId.getClass();
        firSession.getClass();
        FirAnnotation annotationWithResolvedArgumentsByClassId = FirAnnotationUtilsKt.getAnnotationWithResolvedArgumentsByClassId(firBasedSymbol, classId, firSession);
        if (annotationWithResolvedArgumentsByClassId == null || (argumentMapping = annotationWithResolvedArgumentsByClassId.getArgumentMapping()) == null || (mapping = argumentMapping.getMapping()) == null || (collectionValues = mapping.values()) == null) {
            return null;
        }
        return (FirExpression) CollectionsKt.firstOrNull(collectionValues);
    }

    public static final String getAnnotationStringParameter(FirBasedSymbol<?> firBasedSymbol, ClassId classId, FirSession firSession) {
        firBasedSymbol.getClass();
        classId.getClass();
        firSession.getClass();
        FirExpression annotationFirstArgument = getAnnotationFirstArgument(firBasedSymbol, classId, firSession);
        FirLiteralExpression firLiteralExpression = annotationFirstArgument instanceof FirLiteralExpression ? (FirLiteralExpression) annotationFirstArgument : null;
        Object value = firLiteralExpression != null ? firLiteralExpression.getValue() : null;
        if (value instanceof String) {
            return (String) value;
        }
        return null;
    }

    public static final ClassKind getClassKind(FirClassLikeSymbol<?> firClassLikeSymbol) {
        firClassLikeSymbol.getClass();
        FirClassSymbol firClassSymbol = firClassLikeSymbol instanceof FirClassSymbol ? (FirClassSymbol) firClassLikeSymbol : null;
        if (firClassSymbol != null) {
            return firClassSymbol.getClassKind();
        }
        return null;
    }

    public static final FirBasedSymbol<?> getClosestNonLocal(CheckerContext checkerContext) {
        checkerContext.getClass();
        for (FirBasedSymbol<?> firBasedSymbol : checkerContext.getContainingDeclarations()) {
            if ((firBasedSymbol instanceof FirCallableSymbol) || (firBasedSymbol instanceof FirAnonymousInitializerSymbol)) {
                return firBasedSymbol;
            }
        }
        return (FirBasedSymbol) CollectionsKt.lastOrNull(checkerContext.getContainingDeclarations());
    }

    public static final KtSourceElement getDefaultValueForParameter(KtSourceElement ktSourceElement) {
        KtExpression defaultValue;
        ktSourceElement.getClass();
        if (!(ktSourceElement instanceof KtPsiSourceElement)) {
            if (ktSourceElement instanceof KtLightSourceElement) {
                return findDefaultValue((KtLightSourceElement) ktSourceElement);
            }
            bu8.a();
            return null;
        }
        KtParameter psi = ((KtPsiSourceElement) ktSourceElement).getPsi();
        KtParameter ktParameter = psi instanceof KtParameter ? psi : null;
        if (ktParameter != null && (defaultValue = ktParameter.getDefaultValue()) != null) {
            if (KtRealSourceElementKind.INSTANCE != null) {
                return new KtRealPsiSourceElement(defaultValue);
            }
            bu8.a();
        }
        return null;
    }

    public static final boolean getHasStableIdentityForAtomicOperations(CheckerContext checkerContext, ConeKotlinType coneKotlinType) {
        checkerContext.getClass();
        coneKotlinType.getClass();
        ConeSimpleKotlinType coneSimpleKotlinTypeUnwrapToSimpleTypeUsingLowerBound = ConeTypesKt.unwrapToSimpleTypeUsingLowerBound(TypeExpansionUtilsKt.fullyExpandedType(checkerContext, coneKotlinType));
        return (ConeBuiltinTypeUtilsKt.isPrimitiveOrNullablePrimitive(coneSimpleKotlinTypeUnwrapToSimpleTypeUsingLowerBound) || isValueClass(coneSimpleKotlinTypeUnwrapToSimpleTypeUsingLowerBound, checkerContext.getSession())) ? false : true;
    }

    public static final boolean getHasValOrVar(FirValueParameter firValueParameter) {
        firValueParameter.getClass();
        KtSourceElement source = firValueParameter.getSource();
        if (source == null) {
            return false;
        }
        TokenSet tokenSet = KtTokens.VAL_VAR;
        tokenSet.getClass();
        return FirSourceUtilsKt.getChild$default(source, tokenSet, 0, 0, false, 14, (Object) null) != null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code restructure failed: missing block: B:106:0x014e, code lost:
    
        if (org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt.isJavaOrEnhancement(r3) == true) goto L107;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static final ImplementationStatus getImplementationStatus(SessionAndScopeSessionHolder sessionAndScopeSessionHolder, FirCallableSymbol<?> firCallableSymbol, FirClassSymbol<?> firClassSymbol) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirCallableDeclaration firCallableDeclaration;
        sessionAndScopeSessionHolder.getClass();
        firCallableSymbol.getClass();
        firClassSymbol.getClass();
        FirClassLikeSymbol<?> containingClassSymbol = ContainingClassUtilsKt.getContainingClassSymbol(firCallableSymbol);
        if (Intrinsics.areEqual(FirDelegatedMemberScopeKt.getMultipleDelegatesWithTheSameSignature(firCallableSymbol), Boolean.TRUE) && Intrinsics.areEqual(containingClassSymbol, firClassSymbol)) {
            return ImplementationStatus.AMBIGUOUSLY_INHERITED;
        }
        if (firCallableSymbol instanceof FirIntersectionCallableSymbol) {
            MemberWithBaseScope memberWithBaseScope = new MemberWithBaseScope(firCallableSymbol, DeclarationUtilsKt.dispatchReceiverScope(sessionAndScopeSessionHolder, firCallableSymbol));
            List<FirCallableSymbol<?>> nonSubsumedOverriddenSymbols = DeclarationUtilsKt.getNonSubsumedOverriddenSymbols(memberWithBaseScope);
            if (containingClassSymbol == firClassSymbol && !DeclarationUtilsKt.isTrivialIntersection(memberWithBaseScope) && subjectToManyNotImplemented(nonSubsumedOverriddenSymbols)) {
                return ImplementationStatus.AMBIGUOUSLY_INHERITED;
            }
            Iterator<FirCallableSymbol<?>> it = nonSubsumedOverriddenSymbols.iterator();
            boolean z = false;
            boolean z2 = false;
            boolean z3 = false;
            boolean z4 = false;
            loop0: while (true) {
                boolean z5 = z4;
                while (true) {
                    if (!it.hasNext()) {
                        firCallableDeclaration = null;
                        if (z && !z2) {
                            return ImplementationStatus.NOT_IMPLEMENTED;
                        }
                        if (!z3 || !z4 || z5) {
                            break loop0;
                            break loop0;
                            break loop0;
                        }
                        return ImplementationStatus.VAR_IMPLEMENTED_BY_VAL;
                    }
                    FirCallableSymbol<?> next = it.next();
                    FirCallableDeclaration firCallableDeclaration2 = (FirCallableDeclaration) next.getFir();
                    while (true) {
                        FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration2) || (firCallableDeclaration2.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration2) : null;
                        if (originalForSubstitutionOverrideAttr == null) {
                            originalForSubstitutionOverrideAttr = ClassMembersKt.isIntersectionOverride(firCallableDeclaration2) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration2) : null;
                        }
                        if (originalForSubstitutionOverrideAttr == null) {
                            break;
                        }
                        firCallableDeclaration2 = originalForSubstitutionOverrideAttr;
                    }
                    FirCallableSymbol<FirCallableDeclaration> symbol = firCallableDeclaration2.getSymbol();
                    if (symbol == null) {
                        x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol<*>");
                        return null;
                    }
                    boolean z6 = (symbol instanceof FirPropertySymbol) && ((FirPropertySymbol) symbol).isVar();
                    FirClassLikeSymbol<?> containingClassSymbol2 = ContainingClassUtilsKt.getContainingClassSymbol(symbol);
                    boolean z7 = (containingClassSymbol2 != null ? getClassKind(containingClassSymbol2) : null) == ClassKind.CLASS;
                    if (next.getResolvedStatus().getModality() == Modality.ABSTRACT) {
                        if (z7) {
                            z = true;
                        }
                        if (z6) {
                            z3 = true;
                        }
                    } else {
                        if (Intrinsics.areEqual(next.getOrigin(), FirDeclarationOrigin.Delegated.INSTANCE)) {
                            z2 = true;
                        }
                        if (z7) {
                            z4 = true;
                            if (z6) {
                                break;
                            }
                        } else {
                            continue;
                        }
                    }
                }
            }
        } else {
            firCallableDeclaration = null;
        }
        if (firCallableSymbol instanceof FirNamedFunctionSymbol) {
            if ((firClassSymbol instanceof FirRegularClassSymbol) && firClassSymbol.getRawStatus().isData() && getMatchesDataClassSyntheticMemberSignatures((FirNamedFunctionSymbol) firCallableSymbol)) {
                return ImplementationStatus.INHERITED_OR_SYNTHESIZED;
            }
        } else if (firCallableSymbol instanceof FirFieldSymbol) {
            FirDeclarationOrigin origin = firCallableSymbol.getOrigin();
            if (!(origin instanceof FirDeclarationOrigin.Java) && !Intrinsics.areEqual(origin, FirDeclarationOrigin.Enhancement.INSTANCE)) {
                Object fir = firCallableSymbol.getFir();
                FirCallableDeclaration firCallableDeclaration3 = fir instanceof FirCallableDeclaration ? (FirCallableDeclaration) fir : firCallableDeclaration;
                if (firCallableDeclaration3 != null) {
                    ImportedFromObjectOrStaticData importedFromObjectOrStaticData = FirAbstractImportingScopeKt.getImportedFromObjectOrStaticData(firCallableDeclaration3);
                    if (importedFromObjectOrStaticData != null) {
                        FirCallableDeclaration original = importedFromObjectOrStaticData.getOriginal();
                        if (original != null) {
                        }
                    }
                }
            }
            return ImplementationStatus.CANNOT_BE_IMPLEMENTED;
        }
        if (firCallableSymbol.getResolvedStatus().getModality() == Modality.FINAL) {
            return ImplementationStatus.CANNOT_BE_IMPLEMENTED;
        }
        if (containingClassSymbol == firClassSymbol && (Intrinsics.areEqual(firCallableSymbol.getOrigin(), FirDeclarationOrigin.Source.INSTANCE) || Intrinsics.areEqual(firCallableSymbol.getOrigin(), FirDeclarationOrigin.Precompiled.INSTANCE))) {
            return ImplementationStatus.ALREADY_IMPLEMENTED;
        }
        return firCallableSymbol.getResolvedStatus().getModality() == Modality.ABSTRACT ? ImplementationStatus.NOT_IMPLEMENTED : ImplementationStatus.INHERITED_OR_SYNTHESIZED;
    }

    private static final boolean getMatchesDataClassSyntheticMemberSignatures(FirNamedFunctionSymbol firNamedFunctionSymbol) {
        Name callableName = firNamedFunctionSymbol.getCallableId().getCallableName();
        if (firNamedFunctionSymbol.getReceiverParameterSymbol() == null && !FirCallableSymbolKt.getHasContextParameters(firNamedFunctionSymbol) && Intrinsics.areEqual(callableName, OperatorNameConventions.EQUALS) && getMatchesEqualsSignature(firNamedFunctionSymbol)) {
            return true;
        }
        if (Intrinsics.areEqual(callableName, StandardNames.HASHCODE_NAME) && getMatchesHashCodeSignature(firNamedFunctionSymbol)) {
            return true;
        }
        return Intrinsics.areEqual(callableName, OperatorNameConventions.TO_STRING) && getMatchesToStringSignature(firNamedFunctionSymbol);
    }

    private static final boolean getMatchesEqualsSignature(FirNamedFunctionSymbol firNamedFunctionSymbol) {
        List<FirValueParameterSymbol> valueParameterSymbols = firNamedFunctionSymbol.getValueParameterSymbols();
        return valueParameterSymbols.size() == 1 && ConeBuiltinTypeUtilsKt.isNullableAny(valueParameterSymbols.get(0).getResolvedReturnType());
    }

    private static final boolean getMatchesHashCodeSignature(FirNamedFunctionSymbol firNamedFunctionSymbol) {
        return firNamedFunctionSymbol.getValueParameterSymbols().isEmpty();
    }

    private static final boolean getMatchesToStringSignature(FirNamedFunctionSymbol firNamedFunctionSymbol) {
        return firNamedFunctionSymbol.getValueParameterSymbols().isEmpty();
    }

    public static final List<FirExpression> getReturnedExpressions(FirAnonymousFunctionSymbol firAnonymousFunctionSymbol) {
        ControlFlowGraph controlFlowGraph;
        CFGNode<?> exitNode;
        firAnonymousFunctionSymbol.getClass();
        FirControlFlowGraphReference resolvedControlFlowGraphReference = firAnonymousFunctionSymbol.getResolvedControlFlowGraphReference();
        if (resolvedControlFlowGraphReference == null || (controlFlowGraph = FirControlFlowGraphReferenceImplKt.getControlFlowGraph(resolvedControlFlowGraphReference)) == null || (exitNode = controlFlowGraph.getExitNode()) == null) {
            return CollectionsKt.emptyList();
        }
        List<CFGNode<?>> previousNodes = exitNode.getPreviousNodes();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = previousNodes.iterator();
        while (it.hasNext()) {
            FirExpression returnedExpressions$extractReturnedExpression = getReturnedExpressions$extractReturnedExpression(exitNode, (CFGNode) it.next());
            if (returnedExpressions$extractReturnedExpression != null) {
                arrayList.add(returnedExpressions$extractReturnedExpression);
            }
        }
        return CollectionsKt.distinct(arrayList);
    }

    private static final FirExpression getReturnedExpressions$extractReturnedExpression(CFGNode<?> cFGNode, CFGNode<?> cFGNode2) {
        Object next;
        if (cFGNode2 instanceof JumpNode) {
            FirJump<?> fir = ((JumpNode) cFGNode2).getFir();
            FirReturnExpression firReturnExpression = fir instanceof FirReturnExpression ? (FirReturnExpression) fir : null;
            if (firReturnExpression != null) {
                return firReturnExpression.getResult();
            }
            return null;
        }
        if (cFGNode2 instanceof BlockExitNode) {
            Object objLastOrNull = CollectionsKt.lastOrNull(((BlockExitNode) cFGNode2).getFir().getStatements());
            FirReturnExpression firReturnExpression2 = objLastOrNull instanceof FirReturnExpression ? (FirReturnExpression) objLastOrNull : null;
            if (firReturnExpression2 != null) {
                return firReturnExpression2.getResult();
            }
            return null;
        }
        if (cFGNode2 instanceof FinallyBlockExitNode) {
            FinallyBlockEnterNode enterNode = ((FinallyBlockExitNode) cFGNode2).getEnterNode();
            Iterator<T> it = enterNode.getPreviousNodes().iterator();
            do {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
            } while (!Intrinsics.areEqual(enterNode.edgeFrom((CFGNode) next), cFGNode.edgeFrom(cFGNode2)));
            CFGNode cFGNode3 = (CFGNode) next;
            if (cFGNode3 != null) {
                return getReturnedExpressions$extractReturnedExpression(cFGNode, cFGNode3);
            }
        }
        return null;
    }

    public static final FirElement getSecondToLastContainer(CheckerContext checkerContext) {
        checkerContext.getClass();
        return nthLastContainer(checkerContext, 2);
    }

    public static final List<FirTypeParameterSymbol> getTypeParameterSymbols(FirBasedSymbol<?> firBasedSymbol) {
        firBasedSymbol.getClass();
        if (firBasedSymbol instanceof FirCallableSymbol) {
            return ((FirCallableSymbol) firBasedSymbol).getTypeParameterSymbols();
        }
        if (firBasedSymbol instanceof FirClassLikeSymbol) {
            return ((FirClassLikeSymbol) firBasedSymbol).getTypeParameterSymbols();
        }
        return null;
    }

    public static final boolean hasAnnotationOrInsideAnnotatedClass(FirBasedSymbol<?> firBasedSymbol, ClassId classId, FirSession firSession) {
        firBasedSymbol.getClass();
        classId.getClass();
        firSession.getClass();
        if (FirAnnotationUtilsKt.hasAnnotation(firBasedSymbol, classId, firSession)) {
            return true;
        }
        FirClassLikeSymbol<?> containingClassSymbol = ContainingClassUtilsKt.getContainingClassSymbol(firBasedSymbol);
        if (containingClassSymbol == null) {
            return false;
        }
        return hasAnnotationOrInsideAnnotatedClass(containingClassSymbol, classId, firSession);
    }

    private static final boolean hasBody(FirDeclaration firDeclaration) {
        if (firDeclaration instanceof FirNamedFunction) {
            FirNamedFunction firNamedFunction = (FirNamedFunction) firDeclaration;
            return (firNamedFunction.getBody() == null || (firNamedFunction.getBody() instanceof FirEmptyExpressionBlock)) ? false : true;
        }
        if (!(firDeclaration instanceof FirProperty)) {
            return false;
        }
        FirProperty firProperty = (FirProperty) firDeclaration;
        FirPropertyAccessor setter = firProperty.getSetter();
        FirBlock body = setter != null ? setter.getBody() : null;
        if (body == null ? true : body instanceof FirEmptyExpressionBlock) {
            FirPropertyAccessor getter = firProperty.getGetter();
            FirBlock body2 = getter != null ? getter.getBody() : null;
            if (body2 == null ? true : body2 instanceof FirEmptyExpressionBlock) {
                return false;
            }
        }
        return true;
    }

    public static final boolean hasDiagnosticKind(ConeKotlinType coneKotlinType, DiagnosticKind diagnosticKind) {
        coneKotlinType.getClass();
        diagnosticKind.getClass();
        if (!(coneKotlinType instanceof ConeErrorType)) {
            return false;
        }
        ConeDiagnostic diagnostic = ((ConeErrorType) coneKotlinType).getDiagnostic();
        ConeSimpleDiagnostic coneSimpleDiagnostic = diagnostic instanceof ConeSimpleDiagnostic ? (ConeSimpleDiagnostic) diagnostic : null;
        return (coneSimpleDiagnostic != null ? coneSimpleDiagnostic.getKind() : null) == diagnosticKind;
    }

    public static final boolean hasIntegerLiteralTypeAmbiguity(FirExpression firExpression) {
        ConstantValueKind kind;
        firExpression.getClass();
        FirLiteralExpression firLiteralExpressionHasIntegerLiteralTypeAmbiguity$unwrapLeftmostLiteralExpression = hasIntegerLiteralTypeAmbiguity$unwrapLeftmostLiteralExpression(firExpression);
        if (firLiteralExpressionHasIntegerLiteralTypeAmbiguity$unwrapLeftmostLiteralExpression == null || (kind = firLiteralExpressionHasIntegerLiteralTypeAmbiguity$unwrapLeftmostLiteralExpression.getKind()) == null) {
            return false;
        }
        return Intrinsics.areEqual(kind, ConstantValueKind.Int.INSTANCE) || Intrinsics.areEqual(kind, ConstantValueKind.UnsignedInt.INSTANCE);
    }

    private static final FirLiteralExpression hasIntegerLiteralTypeAmbiguity$unwrapLeftmostLiteralExpression(FirExpression firExpression) {
        if (firExpression instanceof FirLiteralExpression) {
            return (FirLiteralExpression) firExpression;
        }
        if (firExpression instanceof FirIntegerLiteralOperatorCall) {
            return hasIntegerLiteralTypeAmbiguity$unwrapLeftmostLiteralExpression(((FirIntegerLiteralOperatorCall) firExpression).getDispatchReceiver());
        }
        return null;
    }

    public static final boolean isConflictingOrNotInvariant(ConeTypeProjection coneTypeProjection) {
        coneTypeProjection.getClass();
        return coneTypeProjection.getKind() != ProjectionKind.INVARIANT || (coneTypeProjection instanceof ConeKotlinTypeConflictingProjection);
    }

    public static final boolean isDelegated(Name name) {
        name.getClass();
        String strAsString = name.asString();
        strAsString.getClass();
        return StringsKt.startsWith$default(strAsString, "$$delegate_", false, 2, (Object) null);
    }

    public static final boolean isDispatchReceiver(CheckerContext checkerContext, FirExpression firExpression) {
        checkerContext.getClass();
        firExpression.getClass();
        FirElement firElement = (FirElement) CollectionsKt.getOrNull(checkerContext.getContainingElements(), checkerContext.getContainingElements().size() - 2);
        return (firElement instanceof FirQualifiedAccessExpression) && Intrinsics.areEqual(((FirQualifiedAccessExpression) firElement).getDispatchReceiver(), firExpression);
    }

    public static final boolean isExpect(FirBasedSymbol<?> firBasedSymbol) {
        if (firBasedSymbol instanceof FirCallableSymbol) {
            return ((FirCallableSymbol) firBasedSymbol).getRawStatus().isExpect();
        }
        if (firBasedSymbol instanceof FirClassLikeSymbol) {
            return ((FirClassLikeSymbol) firBasedSymbol).getRawStatus().isExpect();
        }
        return false;
    }

    public static final boolean isExplicit(FirTypeProjection firTypeProjection) {
        firTypeProjection.getClass();
        return isExplicitTypeArgumentSource(firTypeProjection.getSource());
    }

    public static final boolean isExplicitParentOfResolvedQualifier(CheckerContext checkerContext, FirResolvedQualifier firResolvedQualifier) {
        checkerContext.getClass();
        firResolvedQualifier.getClass();
        FirElement secondToLastContainer = getSecondToLastContainer(checkerContext);
        return (secondToLastContainer instanceof FirResolvedQualifier) && Intrinsics.areEqual(((FirResolvedQualifier) secondToLastContainer).getExplicitParent(), firResolvedQualifier);
    }

    public static final boolean isExplicitTypeArgumentSource(KtSourceElement ktSourceElement) {
        return (ktSourceElement == null || (ktSourceElement.getKind() instanceof KtFakeSourceElementKind.ImplicitTypeArgument)) ? false : true;
    }

    /* JADX WARN: Code duplicated, block: B:30:0x0070  */
    /* JADX WARN: Code duplicated, block: B:32:0x007a  */
    /* JADX WARN: Code duplicated, block: B:35:0x0084  */
    /* JADX WARN: Code duplicated, block: B:38:0x008e  */
    /* JADX WARN: Code duplicated, block: B:43:0x009e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:44:? A[LOOP:0: B:36:0x0088->B:44:?, LOOP_END, SYNTHETIC] */
    public static final boolean isFunctionForExpectTypeFromCastFeature(FirFunctionSymbol<?> firFunctionSymbol) {
        FirResolvedTypeRef resolvedReceiverTypeRef;
        List<FirValueParameterSymbol> contextParameterSymbols;
        Iterator<T> it;
        firFunctionSymbol.getClass();
        FirTypeParameterSymbol firTypeParameterSymbol = (FirTypeParameterSymbol) CollectionsKt.singleOrNull(firFunctionSymbol.getTypeParameterSymbols());
        if (firTypeParameterSymbol == null) {
            return false;
        }
        ConeRigidType coneRigidTypeLowerBoundIfFlexible = ConeTypeUtilsKt.lowerBoundIfFlexible(firFunctionSymbol.getResolvedReturnType());
        ConeTypeParameterType coneTypeParameterType = coneRigidTypeLowerBoundIfFlexible instanceof ConeTypeParameterType ? (ConeTypeParameterType) coneRigidTypeLowerBoundIfFlexible : null;
        if (!Intrinsics.areEqual(coneTypeParameterType != null ? coneTypeParameterType.getLookupTag() : null, firTypeParameterSymbol.getLookupTag())) {
            return false;
        }
        List<FirValueParameterSymbol> valueParameterSymbols = firFunctionSymbol.getValueParameterSymbols();
        if ((valueParameterSymbols instanceof Collection) && valueParameterSymbols.isEmpty()) {
            resolvedReceiverTypeRef = firFunctionSymbol.getResolvedReceiverTypeRef();
            if (resolvedReceiverTypeRef != null) {
                contextParameterSymbols = firFunctionSymbol.getContextParameterSymbols();
                if (contextParameterSymbols instanceof Collection) {
                    it = contextParameterSymbols.iterator();
                    while (it.hasNext()) {
                        if (isFunctionForExpectTypeFromCastFeature$isBadType(((FirValueParameterSymbol) it.next()).getResolvedReturnTypeRef(), firTypeParameterSymbol)) {
                        }
                    }
                } else {
                    it = contextParameterSymbols.iterator();
                    while (it.hasNext()) {
                        if (isFunctionForExpectTypeFromCastFeature$isBadType(((FirValueParameterSymbol) it.next()).getResolvedReturnTypeRef(), firTypeParameterSymbol)) {
                        }
                    }
                }
                return true;
            }
            contextParameterSymbols = firFunctionSymbol.getContextParameterSymbols();
            if (contextParameterSymbols instanceof Collection) {
                it = contextParameterSymbols.iterator();
                while (it.hasNext()) {
                    if (isFunctionForExpectTypeFromCastFeature$isBadType(((FirValueParameterSymbol) it.next()).getResolvedReturnTypeRef(), firTypeParameterSymbol)) {
                    }
                }
            } else {
                it = contextParameterSymbols.iterator();
                while (it.hasNext()) {
                    if (isFunctionForExpectTypeFromCastFeature$isBadType(((FirValueParameterSymbol) it.next()).getResolvedReturnTypeRef(), firTypeParameterSymbol)) {
                    }
                }
            }
            return true;
        }
        Iterator<T> it2 = valueParameterSymbols.iterator();
        while (it2.hasNext()) {
            if (isFunctionForExpectTypeFromCastFeature$isBadType(((FirValueParameterSymbol) it2.next()).getResolvedReturnTypeRef(), firTypeParameterSymbol)) {
            }
        }
        resolvedReceiverTypeRef = firFunctionSymbol.getResolvedReceiverTypeRef();
        if (resolvedReceiverTypeRef != null || !isFunctionForExpectTypeFromCastFeature$isBadType(resolvedReceiverTypeRef, firTypeParameterSymbol)) {
            contextParameterSymbols = firFunctionSymbol.getContextParameterSymbols();
            if ((contextParameterSymbols instanceof Collection) || !contextParameterSymbols.isEmpty()) {
                it = contextParameterSymbols.iterator();
                while (it.hasNext()) {
                    if (isFunctionForExpectTypeFromCastFeature$isBadType(((FirValueParameterSymbol) it.next()).getResolvedReturnTypeRef(), firTypeParameterSymbol)) {
                    }
                }
            }
            return true;
        }
        return false;
    }

    private static final boolean isFunctionForExpectTypeFromCastFeature$isBadType(FirTypeRef firTypeRef, final FirTypeParameterSymbol firTypeParameterSymbol) {
        return ConeTypeUtilsKt.contains(FirTypeUtilsKt.getConeType(firTypeRef), new Function1() { // from class: d85
            public final Object invoke(Object obj) {
                return Boolean.valueOf(FirHelpersKt.a(firTypeParameterSymbol, (ConeKotlinType) obj));
            }
        });
    }

    public static final boolean isInlineOnly(FirCallableSymbol<?> firCallableSymbol, FirSession firSession) {
        firCallableSymbol.getClass();
        firSession.getClass();
        return firCallableSymbol.getRawStatus().isInline() && FirAnnotationUtilsKt.hasAnnotation(firCallableSymbol, INLINE_ONLY_ANNOTATION_CLASS_ID, firSession);
    }

    public static final boolean isIterator(FirFunctionCall firFunctionCall) {
        firFunctionCall.getClass();
        return Intrinsics.areEqual(firFunctionCall.getCalleeReference().getName(), SpecialNames.ITERATOR);
    }

    public static final boolean isLhsOfAssignment(CheckerContext checkerContext, FirElement firElement) {
        FirStatement firStatementPrevious;
        checkerContext.getClass();
        firElement.getClass();
        if (!(firElement instanceof FirQualifiedAccessExpression)) {
            return false;
        }
        List<FirStatement> callsOrAssignments = checkerContext.getCallsOrAssignments();
        ListIterator<FirStatement> listIterator = callsOrAssignments.listIterator(callsOrAssignments.size());
        do {
            if (!listIterator.hasPrevious()) {
                firStatementPrevious = null;
                break;
            }
            firStatementPrevious = listIterator.previous();
        } while (Intrinsics.areEqual(firStatementPrevious, firElement));
        FirStatement firStatement = firStatementPrevious;
        return firStatement != null && (firStatement instanceof FirVariableAssignment) && Intrinsics.areEqual(((FirVariableAssignment) firStatement).getLValue(), firElement);
    }

    public static final boolean isMalformedExpandedType(CheckerContext checkerContext, ConeKotlinType coneKotlinType, boolean z) {
        ConeKotlinType type;
        checkerContext.getClass();
        coneKotlinType.getClass();
        ConeKotlinType coneKotlinTypeFullyExpandedType = TypeExpansionUtilsKt.fullyExpandedType(checkerContext, coneKotlinType);
        if (Intrinsics.areEqual(ConeTypeUtilsKt.getClassId(coneKotlinTypeFullyExpandedType), StandardClassIds.INSTANCE.getArray())) {
            ConeTypeProjection coneTypeProjection = (ConeTypeProjection) ArraysKt.singleOrNull(coneKotlinTypeFullyExpandedType.getTypeArguments());
            ConeKotlinType coneKotlinTypeFullyExpandedType2 = (coneTypeProjection == null || (type = ConeTypeProjectionKt.getType(coneTypeProjection)) == null) ? null : TypeExpansionUtilsKt.fullyExpandedType(checkerContext, type);
            if (coneKotlinTypeFullyExpandedType2 != null) {
                if (ConeBuiltinTypeUtilsKt.isNothing(coneKotlinTypeFullyExpandedType2)) {
                    return true;
                }
                if (ConeBuiltinTypeUtilsKt.isNullableNothing(coneKotlinTypeFullyExpandedType2) && !z) {
                    return true;
                }
            }
        }
        return containsMalformedArgument(checkerContext, coneKotlinTypeFullyExpandedType, z);
    }

    private static final boolean isMember(FirCallableDeclaration firCallableDeclaration) {
        return firCallableDeclaration.getDispatchReceiverType() != null;
    }

    public static final boolean isPrimaryConstructor(FirBasedSymbol<?> firBasedSymbol) {
        if (firBasedSymbol instanceof FirConstructorSymbol) {
            return ((FirConstructorSymbol) firBasedSymbol).isPrimary();
        }
        return false;
    }

    private static final boolean isRecursiveSingleFieldValueClassType(ConeKotlinType coneKotlinType, FirSession firSession) {
        return isRecursiveValueClassType(coneKotlinType, new HashSet(), firSession, true);
    }

    private static final boolean isRecursiveValueClassType(ConeKotlinType coneKotlinType, HashSet<ConeKotlinType> hashSet, FirSession firSession, boolean z) {
        FirConstructorSymbol firConstructorSymbolPrimaryConstructorIfAny;
        boolean z2;
        FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(coneKotlinType, firSession);
        if (regularClassSymbol != null) {
            if (!DeclarationUtilsKt.isInlineOrValueClass(regularClassSymbol)) {
                regularClassSymbol = null;
            }
            if (regularClassSymbol == null || (firConstructorSymbolPrimaryConstructorIfAny = DeclarationUtilsKt.primaryConstructorIfAny(regularClassSymbol, firSession)) == null) {
                return false;
            }
            if (firConstructorSymbolPrimaryConstructorIfAny.getValueParameterSymbols().size() > 1 && z) {
                return false;
            }
            if (hashSet.add(coneKotlinType)) {
                List<FirValueParameterSymbol> valueParameterSymbols = firConstructorSymbolPrimaryConstructorIfAny.getValueParameterSymbols();
                if (!(valueParameterSymbols instanceof Collection) || !valueParameterSymbols.isEmpty()) {
                    Iterator<T> it = valueParameterSymbols.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            z2 = false;
                            break;
                        }
                        if (isRecursiveValueClassType(((FirValueParameterSymbol) it.next()).getResolvedReturnType(), hashSet, firSession, z)) {
                            z2 = true;
                            break;
                        }
                    }
                } else {
                    z2 = false;
                    break;
                }
                hashSet.remove(coneKotlinType);
                if (!z2) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static final boolean isSingleFieldValueClass(ConeKotlinType coneKotlinType, FirSession firSession) {
        coneKotlinType.getClass();
        firSession.getClass();
        ConeInferenceContext typeContext = TypeComponentsKt.getTypeContext(firSession);
        return isRecursiveSingleFieldValueClassType(coneKotlinType, firSession) || typeContext.isInlineClass(typeContext.typeConstructor(coneKotlinType));
    }

    public static final boolean isStandalone(CheckerContext checkerContext, FirResolvedQualifier firResolvedQualifier) {
        checkerContext.getClass();
        firResolvedQualifier.getClass();
        Object objLastOrNull = CollectionsKt.lastOrNull(checkerContext.getCallsOrAssignments());
        FirQualifiedAccessExpression firQualifiedAccessExpression = objLastOrNull instanceof FirQualifiedAccessExpression ? (FirQualifiedAccessExpression) objLastOrNull : null;
        if ((firQualifiedAccessExpression != null ? firQualifiedAccessExpression.getExplicitReceiver() : null) != firResolvedQualifier) {
            if ((firQualifiedAccessExpression != null ? firQualifiedAccessExpression.getDispatchReceiver() : null) != firResolvedQualifier) {
                if (!Intrinsics.areEqual(firQualifiedAccessExpression != null ? firQualifiedAccessExpression.getExtensionReceiver() : null, firResolvedQualifier)) {
                    FirGetClassCall firGetClassCall = (FirGetClassCall) CollectionsKt.lastOrNull(checkerContext.getGetClassCalls());
                    return ((firGetClassCall != null ? firGetClassCall.getArgument() : null) == firResolvedQualifier || isExplicitParentOfResolvedQualifier(checkerContext, firResolvedQualifier)) ? false : true;
                }
            }
        }
        return false;
    }

    public static final boolean isSubtypeForTypeMismatch(ConeInferenceContext coneInferenceContext, ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2) {
        coneInferenceContext.getClass();
        coneKotlinType.getClass();
        coneKotlinType2.getClass();
        return AbstractTypeChecker.isSubtypeOf$default(AbstractTypeChecker.INSTANCE, coneInferenceContext.newTypeCheckerState(true, false, false), TypeExpansionUtilsKt.fullyExpandedType$default(coneKotlinType, coneInferenceContext.getSession(), (Function1) null, 2, (Object) null), TypeExpansionUtilsKt.fullyExpandedType$default(coneKotlinType2, coneInferenceContext.getSession(), (Function1) null, 2, (Object) null), false, 8, (Object) null);
    }

    public static final boolean isSubtypeOfThrowable(ConeKotlinType coneKotlinType, FirSession firSession) {
        coneKotlinType.getClass();
        firSession.getClass();
        return isSupertypeOf((KotlinTypeMarker) firSession.getBuiltinTypes().getThrowableType().getConeType(), (TypeCheckerProviderContext) TypeComponentsKt.getTypeContext(firSession), (KotlinTypeMarker) TypeExpansionUtilsKt.fullyExpandedType$default(coneKotlinType, firSession, (Function1) null, 2, (Object) null));
    }

    public static final boolean isSupertypeOf(KotlinTypeMarker kotlinTypeMarker, TypeCheckerProviderContext typeCheckerProviderContext, KotlinTypeMarker kotlinTypeMarker2) {
        kotlinTypeMarker.getClass();
        typeCheckerProviderContext.getClass();
        return kotlinTypeMarker2 != null && AbstractTypeChecker.isSubtypeOf$default(AbstractTypeChecker.INSTANCE, typeCheckerProviderContext, kotlinTypeMarker2, kotlinTypeMarker, false, 8, (Object) null);
    }

    private static final boolean isSupertypeOf$isSupertypeOf(FirClassSymbol<?> firClassSymbol, FirSession firSession, FirClassSymbol<?> firClassSymbol2, Set<FirClassSymbol<?>> set) {
        FirRegularClassSymbol firRegularClassSymbolFullyExpandedClass;
        Iterator<FirResolvedTypeRef> it = firClassSymbol2.getResolvedSuperTypeRefs().iterator();
        while (it.hasNext()) {
            FirClassLikeSymbol<?> classLikeSymbol = toClassLikeSymbol(it.next(), firSession);
            if (classLikeSymbol != null && (firRegularClassSymbolFullyExpandedClass = DeclarationUtilsKt.fullyExpandedClass(classLikeSymbol, firSession)) != null && !set.contains(firRegularClassSymbolFullyExpandedClass)) {
                set.add(firRegularClassSymbolFullyExpandedClass);
                if (Intrinsics.areEqual(firRegularClassSymbolFullyExpandedClass, firClassSymbol) || isSupertypeOf$isSupertypeOf(firClassSymbol, firSession, firRegularClassSymbolFullyExpandedClass, set)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static final boolean isTopLevel(CheckerContext checkerContext) {
        checkerContext.getClass();
        FirBasedSymbol firBasedSymbol = (FirBasedSymbol) CollectionsKt.lastOrNull(checkerContext.getContainingDeclarations());
        return (firBasedSymbol instanceof FirFileSymbol) || (firBasedSymbol instanceof FirScriptSymbol);
    }

    public static final boolean isValueClass(ConeKotlinType coneKotlinType, FirSession firSession) {
        coneKotlinType.getClass();
        firSession.getClass();
        FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(coneKotlinType, firSession);
        if (regularClassSymbol != null) {
            return regularClassSymbol.getRawStatus().isInline() || regularClassSymbol.getRawStatus().isValue();
        }
        return false;
    }

    public static final ConeKotlinType leastUpperBound(ConeKotlinType coneKotlinType, FirSession firSession) {
        coneKotlinType.getClass();
        firSession.getClass();
        Set<ConeClassLikeType> setCollectUpperBounds = org.jetbrains.kotlin.fir.TypeUtilsKt.collectUpperBounds(coneKotlinType, TypeComponentsKt.getTypeContext(firSession));
        if (setCollectUpperBounds.isEmpty()) {
            setCollectUpperBounds = null;
        }
        return setCollectUpperBounds == null ? firSession.getBuiltinTypes().getNullableAnyType().getConeType() : ConeTypeIntersector.INSTANCE.intersectTypes(TypeComponentsKt.getTypeContext(firSession), setCollectUpperBounds);
    }

    public static final Modality modality(FirClass firClass) {
        firClass.getClass();
        return firClass instanceof FirRegularClass ? firClass.getStatus().getModality() : Modality.FINAL;
    }

    public static final FirElement nthLastContainer(CheckerContext checkerContext, int i) {
        checkerContext.getClass();
        List<FirElement> containingElements = checkerContext.getContainingElements();
        return (FirElement) CollectionsKt.getOrNull(containingElements, containingElements.size() - i);
    }

    public static final FirClassLikeSymbol<?> outerClassSymbol(CheckerContext checkerContext, FirClassLikeSymbol<?> firClassLikeSymbol) {
        checkerContext.getClass();
        firClassLikeSymbol.getClass();
        if (firClassLikeSymbol instanceof FirClassSymbol) {
            return org.jetbrains.kotlin.fir.resolve.DeclarationUtilsKt.getContainingDeclaration((FirClassLikeSymbol<? extends FirClassLikeDeclaration>) firClassLikeSymbol, checkerContext.getSession());
        }
        return null;
    }

    public static final Collection<FirFunctionSymbol<?>> overriddenFunctions(CheckerContext checkerContext, FirNamedFunctionSymbol firNamedFunctionSymbol, FirClassSymbol<?> firClassSymbol) {
        checkerContext.getClass();
        firNamedFunctionSymbol.getClass();
        firClassSymbol.getClass();
        return ScopesKt.overriddenFunctions(firNamedFunctionSymbol, firClassSymbol, checkerContext.getSession(), checkerContext.getScopeSession());
    }

    public static final void processOverriddenFunctionsSafe(CheckerContext checkerContext, FirNamedFunctionSymbol firNamedFunctionSymbol, final Function1<? super FirNamedFunctionSymbol, Unit> function1) {
        checkerContext.getClass();
        firNamedFunctionSymbol.getClass();
        function1.getClass();
        processOverriddenFunctionsWithActionSafe(checkerContext, firNamedFunctionSymbol, new Function1<FirNamedFunctionSymbol, ProcessorAction>() { // from class: org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt.processOverriddenFunctionsSafe.1
            public final ProcessorAction invoke(FirNamedFunctionSymbol firNamedFunctionSymbol2) {
                firNamedFunctionSymbol2.getClass();
                function1.invoke(firNamedFunctionSymbol2);
                return ProcessorAction.NEXT;
            }
        });
    }

    public static final void processOverriddenFunctionsWithActionSafe(CheckerContext checkerContext, FirNamedFunctionSymbol firNamedFunctionSymbol, Function1<? super FirNamedFunctionSymbol, ? extends ProcessorAction> function1) {
        checkerContext.getClass();
        firNamedFunctionSymbol.getClass();
        function1.getClass();
        FirTypeScope firTypeScopeContainingClassUnsubstitutedScope = containingClassUnsubstitutedScope(checkerContext, firNamedFunctionSymbol);
        if (firTypeScopeContainingClassUnsubstitutedScope == null) {
            return;
        }
        firTypeScopeContainingClassUnsubstitutedScope.processFunctionsByName(firNamedFunctionSymbol.getCallableId().getCallableName(), new Function1() { // from class: e85
            public final Object invoke(Object obj) {
                return FirHelpersKt.c((FirNamedFunctionSymbol) obj);
            }
        });
        FirTypeScopeKt.processOverriddenFunctions(firTypeScopeContainingClassUnsubstitutedScope, firNamedFunctionSymbol, function1);
    }

    public static final void processOverriddenPropertiesWithActionSafe(CheckerContext checkerContext, FirPropertySymbol firPropertySymbol, Function1<? super FirPropertySymbol, ? extends ProcessorAction> function1) {
        checkerContext.getClass();
        firPropertySymbol.getClass();
        function1.getClass();
        FirTypeScope firTypeScopeContainingClassUnsubstitutedScope = containingClassUnsubstitutedScope(checkerContext, firPropertySymbol);
        if (firTypeScopeContainingClassUnsubstitutedScope == null) {
            return;
        }
        firTypeScopeContainingClassUnsubstitutedScope.processPropertiesByName(firPropertySymbol.getName(), new Function1() { // from class: c85
            public final Object invoke(Object obj) {
                return FirHelpersKt.b((FirVariableSymbol) obj);
            }
        });
        FirTypeScopeKt.processOverriddenProperties(firTypeScopeContainingClassUnsubstitutedScope, firPropertySymbol, function1);
    }

    public static final String projectionKindAsString(ConeCapturedType coneCapturedType) {
        coneCapturedType.getClass();
        int i = WhenMappings.$EnumSwitchMapping$1[coneCapturedType.getConstructor().getProjection().getKind().ordinal()];
        if (i == 1) {
            return "out";
        }
        if (i == 2) {
            return "in";
        }
        if (i == 3) {
            return "star";
        }
        if (i != 4) {
            bu8.a();
            return null;
        }
        k2d.a("no projection");
        return null;
    }

    public static final Set<Modality> redundantModalities(CheckerContext checkerContext, FirMemberDeclaration firMemberDeclaration, Modality modality) {
        checkerContext.getClass();
        firMemberDeclaration.getClass();
        modality.getClass();
        if (firMemberDeclaration instanceof FirRegularClass) {
            return WhenMappings.$EnumSwitchMapping$0[((FirRegularClass) firMemberDeclaration).getClassKind().ordinal()] == 1 ? SetsKt.setOf(new Modality[]{Modality.ABSTRACT, Modality.OPEN}) : SetsKt.setOf(modality);
        }
        FirClassSymbol<?> firClassSymbolFindClosestClassOrObject = findClosestClassOrObject(checkerContext);
        if (firClassSymbolFindClosestClassOrObject == null) {
            return SetsKt.setOf(modality);
        }
        if (firMemberDeclaration.getStatus().isOverride() && firClassSymbolFindClosestClassOrObject.getResolvedStatus().getModality() != Modality.FINAL) {
            return SetsKt.setOf(Modality.OPEN);
        }
        if (firClassSymbolFindClosestClassOrObject.getClassKind() == ClassKind.INTERFACE) {
            return hasBody(firMemberDeclaration) ? SetsKt.setOf(Modality.OPEN) : SetsKt.setOf(new Modality[]{Modality.ABSTRACT, Modality.OPEN});
        }
        return SetsKt.setOf(modality);
    }

    private static final boolean reportReturnTypeMismatchInLambda(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, ConeKotlinType coneKotlinType, FirExpression firExpression, ConeKotlinType coneKotlinType2) {
        ConeKotlinType type;
        boolean z = false;
        if (!(firExpression instanceof FirAnonymousFunctionExpression)) {
            return false;
        }
        if (!FunctionalTypeUtilsKt.isSomeFunctionType(coneKotlinType, checkerContext.getSession()) && !Intrinsics.areEqual(ConeTypeUtilsKt.getClassId(coneKotlinType), StandardClassIds.INSTANCE.getFunction())) {
            return false;
        }
        ConeTypeProjection coneTypeProjection = (ConeTypeProjection) ArraysKt.lastOrNull(coneKotlinType.getTypeArguments());
        if (coneTypeProjection != null && (type = ConeTypeProjectionKt.getType(coneTypeProjection)) != null) {
            if (!isSubtypeForTypeMismatch(TypeComponentsKt.getTypeContext(checkerContext.getSession()), TypeUtilsKt.withArguments(coneKotlinType2, (ConeTypeProjection[]) CollectionsKt.plus(ArraysKt.dropLast(coneKotlinType2.getTypeArguments(), 1), type).toArray(new ConeTypeProjection[0])), coneKotlinType)) {
                return false;
            }
            FirAnonymousFunctionExpression firAnonymousFunctionExpression = (FirAnonymousFunctionExpression) firExpression;
            for (FirExpression firExpression2 : getReturnedExpressions(firAnonymousFunctionExpression.getAnonymousFunction().getSymbol())) {
                if (!isSubtypeForTypeMismatch(TypeComponentsKt.getTypeContext(checkerContext.getSession()), FirTypeUtilsKt.getResolvedType(firExpression2), type)) {
                    KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firExpression2.getSource(), (KtDiagnosticFactory4<ConeKotlinType, ConeKotlinType, FirAnonymousFunction, Boolean>) ((KtDiagnosticFactory4<Object, Object, Object, Object>) FirErrors.INSTANCE.getRETURN_TYPE_MISMATCH()), type, FirTypeUtilsKt.getResolvedType(firExpression2), firAnonymousFunctionExpression.getAnonymousFunction(), Boolean.valueOf(InferenceUtilsKt.isTypeMismatchDueToNullability(TypeComponentsKt.getTypeContext(checkerContext.getSession()), FirTypeUtilsKt.getResolvedType(firExpression2), type)), (128 & 128) != 0 ? null : null);
                    z = true;
                }
            }
        }
        return z;
    }

    public static final void requireFeatureSupport(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, KtSourceElement ktSourceElement, LanguageFeature languageFeature, SourceElementPositioningStrategy sourceElementPositioningStrategy) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        languageFeature.getClass();
        if (LanguageVersionUtilsKt.isDisabled(checkerContext, languageFeature)) {
            KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory1<Pair>) FirErrors.INSTANCE.getUNSUPPORTED_FEATURE(), TuplesKt.to(languageFeature, checkerContext.get$languageVersionSettings()), (AbstractSourceElementPositioningStrategy) sourceElementPositioningStrategy);
        }
    }

    public static /* synthetic */ void requireFeatureSupport$default(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, KtSourceElement ktSourceElement, LanguageFeature languageFeature, SourceElementPositioningStrategy sourceElementPositioningStrategy, int i, Object obj) {
        if ((i & 8) != 0) {
            sourceElementPositioningStrategy = null;
        }
        requireFeatureSupport(checkerContext, diagnosticReporter, ktSourceElement, languageFeature, sourceElementPositioningStrategy);
    }

    public static final FirClassLikeSymbol<?> resolvedCompanionSymbol(SessionHolder sessionHolder, FirResolvedQualifier firResolvedQualifier) {
        FirRegularClassSymbol firRegularClassSymbolFullyExpandedClass;
        sessionHolder.getClass();
        firResolvedQualifier.getClass();
        FirClassLikeSymbol<?> symbol = firResolvedQualifier.getSymbol();
        if (!firResolvedQualifier.getResolvedToCompanionObject()) {
            symbol = null;
        }
        if (symbol == null || (firRegularClassSymbolFullyExpandedClass = DeclarationUtilsKt.fullyExpandedClass(sessionHolder, symbol)) == null) {
            return null;
        }
        return firRegularClassSymbolFullyExpandedClass.getResolvedCompanionObjectSymbol();
    }

    public static final FirClassLikeSymbol<?> resolvedSymbolOrCompanionSymbol(SessionHolder sessionHolder, FirResolvedQualifier firResolvedQualifier) {
        sessionHolder.getClass();
        firResolvedQualifier.getClass();
        FirClassLikeSymbol<?> symbol = firResolvedQualifier.getSymbol();
        if (symbol == null) {
            return null;
        }
        if (!firResolvedQualifier.getResolvedToCompanionObject()) {
            return symbol;
        }
        FirRegularClassSymbol firRegularClassSymbolFullyExpandedClass = DeclarationUtilsKt.fullyExpandedClass(sessionHolder, symbol);
        if (firRegularClassSymbolFullyExpandedClass != null) {
            return firRegularClassSymbolFullyExpandedClass.getResolvedCompanionObjectSymbol();
        }
        return null;
    }

    private static final boolean subjectToManyNotImplemented(List<? extends FirCallableSymbol<?>> list) {
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (FirCallableSymbol<?> firCallableSymbol : list) {
            FirClassLikeSymbol<?> containingClassSymbol = ContainingClassUtilsKt.getContainingClassSymbol(firCallableSymbol);
            FirRegularClassSymbol firRegularClassSymbol = containingClassSymbol instanceof FirRegularClassSymbol ? (FirRegularClassSymbol) containingClassSymbol : null;
            boolean z = (firRegularClassSymbol != null ? firRegularClassSymbol.getClassKind() : null) == ClassKind.INTERFACE;
            if (firCallableSymbol.getResolvedStatus().getModality() != Modality.ABSTRACT) {
                if (z) {
                    i3++;
                } else {
                    i2++;
                }
            } else if (z) {
                i++;
            }
            if (i2 + i3 > 1) {
                return true;
            }
            if (i3 > 0 && i > 0) {
                return true;
            }
        }
        return false;
    }

    public static final FirClassLikeSymbol<?> toClassLikeSymbol(FirTypeRef firTypeRef, FirSession firSession) {
        firTypeRef.getClass();
        firSession.getClass();
        return ToSymbolUtilsKt.toClassLikeSymbol(FirTypeUtilsKt.getConeType(firTypeRef), firSession);
    }

    public static final FirTypeScope unsubstitutedScope(FirClassSymbol<?> firClassSymbol, CheckerContext checkerContext) {
        firClassSymbol.getClass();
        checkerContext.getClass();
        return FirKotlinScopeProviderKt.unsubstitutedScope(firClassSymbol, checkerContext.getSessionHolder().getSession(), checkerContext.getSessionHolder().getScopeSession(), true, FirResolvePhase.STATUS);
    }

    public static /* synthetic */ void requireFeatureSupport$default(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirElement firElement, LanguageFeature languageFeature, SourceElementPositioningStrategy sourceElementPositioningStrategy, int i, Object obj) {
        if ((i & 8) != 0) {
            sourceElementPositioningStrategy = null;
        }
        requireFeatureSupport(checkerContext, diagnosticReporter, firElement, languageFeature, sourceElementPositioningStrategy);
    }

    public static final List<FirNamedFunctionSymbol> directOverriddenFunctionsSafe(CheckerContext checkerContext, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        checkerContext.getClass();
        firNamedFunctionSymbol.getClass();
        return directOverriddenFunctionsSafe(firNamedFunctionSymbol, checkerContext);
    }

    public static final List<FirPropertySymbol> directOverriddenPropertiesSafe(CheckerContext checkerContext, FirPropertySymbol firPropertySymbol) {
        checkerContext.getClass();
        firPropertySymbol.getClass();
        return directOverriddenPropertiesSafe(firPropertySymbol, checkerContext);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final Modality modality(FirClassSymbol<?> firClassSymbol) {
        firClassSymbol.getClass();
        FirLazyDeclarationResolverKt.lazyResolveToPhase(firClassSymbol, FirResolvePhase.STATUS);
        return modality((FirClass) firClassSymbol.getFir());
    }

    public static final boolean isSupertypeOf(FirClassSymbol<?> firClassSymbol, FirClassSymbol<?> firClassSymbol2, FirSession firSession) {
        firClassSymbol.getClass();
        firClassSymbol2.getClass();
        firSession.getClass();
        return isSupertypeOf$isSupertypeOf(firClassSymbol, firSession, firClassSymbol2, new LinkedHashSet());
    }

    public static final boolean isInlineOnly(FirCallableDeclaration firCallableDeclaration, FirSession firSession) {
        firCallableDeclaration.getClass();
        firSession.getClass();
        return isInlineOnly(firCallableDeclaration.getSymbol(), firSession);
    }

    public static final boolean hasAnnotationOrInsideAnnotatedClass(FirDeclaration firDeclaration, ClassId classId, FirSession firSession) {
        firDeclaration.getClass();
        classId.getClass();
        firSession.getClass();
        return hasAnnotationOrInsideAnnotatedClass(firDeclaration.getSymbol(), classId, firSession);
    }

    public static final FirTypeScope unsubstitutedScope(CheckerContext checkerContext, FirClassSymbol<?> firClassSymbol) {
        checkerContext.getClass();
        firClassSymbol.getClass();
        return unsubstitutedScope(firClassSymbol, checkerContext);
    }

    public static final FirTypeScope unsubstitutedScope(CheckerContext checkerContext, FirClass firClass) {
        checkerContext.getClass();
        firClass.getClass();
        return FirKotlinScopeProviderKt.unsubstitutedScope((SessionAndScopeSessionHolder) checkerContext, firClass, true, FirResolvePhase.STATUS);
    }

    public static final void requireFeatureSupport(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirElement firElement, LanguageFeature languageFeature, SourceElementPositioningStrategy sourceElementPositioningStrategy) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firElement.getClass();
        languageFeature.getClass();
        requireFeatureSupport(checkerContext, diagnosticReporter, firElement.getSource(), languageFeature, sourceElementPositioningStrategy);
    }

    public static final boolean isRecursiveValueClassType(ConeKotlinType coneKotlinType, FirSession firSession) {
        coneKotlinType.getClass();
        firSession.getClass();
        return isRecursiveValueClassType(coneKotlinType, new HashSet(), firSession, false);
    }

    public static final List<FirTypeRefSource> extractArgumentsTypeRefAndSource(FirResolvedQualifier firResolvedQualifier) {
        firResolvedQualifier.getClass();
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        for (FirTypeProjection firTypeProjection : firResolvedQualifier.getTypeArguments()) {
            FirTypeRef typeRef = null;
            FirTypeProjectionWithVariance firTypeProjectionWithVariance = firTypeProjection instanceof FirTypeProjectionWithVariance ? (FirTypeProjectionWithVariance) firTypeProjection : null;
            if (firTypeProjectionWithVariance != null) {
                typeRef = firTypeProjectionWithVariance.getTypeRef();
            }
            listCreateListBuilder.add(new FirTypeRefSource(typeRef, firTypeProjection.getSource()));
        }
        return CollectionsKt.build(listCreateListBuilder);
    }

    public static final AnnotationTargetList getActualTargetList(SessionHolder sessionHolder, FirAnnotationContainer firAnnotationContainer) {
        sessionHolder.getClass();
        firAnnotationContainer.getClass();
        return getActualTargetList(firAnnotationContainer, sessionHolder.getSession());
    }

    public static final AnnotationTargetList getActualTargetList(SessionHolder sessionHolder, FirBasedSymbol<?> firBasedSymbol) {
        sessionHolder.getClass();
        firBasedSymbol.getClass();
        return getActualTargetList(firBasedSymbol.getFir(), sessionHolder.getSession());
    }
}
