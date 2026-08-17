package org.jetbrains.kotlin.fir.analysis.diagnostics;

import com.intellij.lang.LighterASTNode;
import com.intellij.lang.LighterASTTokenNode;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtLightSourceElement;
import org.jetbrains.kotlin.KtNodeTypes;
import org.jetbrains.kotlin.KtPsiSourceElement;
import org.jetbrains.kotlin.KtRealSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKind;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticBaseContext;
import org.jetbrains.kotlin.diagnostics.KtDiagnostic;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory0;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory3;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory4;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticWithParameters1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticWithParameters2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticWithParameters3;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticWithParameters4;
import org.jetbrains.kotlin.diagnostics.KtSimpleDiagnostic;
import org.jetbrains.kotlin.diagnostics.SourceElementPositioningStrategies;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.analysis.FirSourceUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.type.FirDynamicUnsupportedChecker;
import org.jetbrains.kotlin.fir.builder.FirSyntaxErrors;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.diagnostics.ConeAmbiguousFunctionTypeKinds;
import org.jetbrains.kotlin.fir.diagnostics.ConeAmbiguousSuper;
import org.jetbrains.kotlin.fir.diagnostics.ConeCannotInferReceiverParameterType;
import org.jetbrains.kotlin.fir.diagnostics.ConeCannotInferTypeParameterType;
import org.jetbrains.kotlin.fir.diagnostics.ConeCannotInferValueParameterType;
import org.jetbrains.kotlin.fir.diagnostics.ConeCollectionLiteralAmbiguity;
import org.jetbrains.kotlin.fir.diagnostics.ConeContextParameterWithDefaultValue;
import org.jetbrains.kotlin.fir.diagnostics.ConeDestructuringDeclarationsOnTopLevel;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.ConeIntermediateDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.ConeMultipleLabelsAreForbidden;
import org.jetbrains.kotlin.fir.diagnostics.ConeNoConstructorError;
import org.jetbrains.kotlin.fir.diagnostics.ConeNoImplicitDefaultConstructorOnExpectClass;
import org.jetbrains.kotlin.fir.diagnostics.ConeNotAnnotationContainer;
import org.jetbrains.kotlin.fir.diagnostics.ConeSimpleDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.ConeSyntaxDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.ConeTypeVariableTypeIsNotInferred;
import org.jetbrains.kotlin.fir.diagnostics.ConeUnderscoreIsReserved;
import org.jetbrains.kotlin.fir.diagnostics.ConeUnexpectedTypeArgumentsError;
import org.jetbrains.kotlin.fir.diagnostics.ConeUnreportedDuplicateDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.ConeUnsupportedClassLiteralsWithEmptyLhs;
import org.jetbrains.kotlin.fir.diagnostics.DiagnosticKind;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression;
import org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess;
import org.jetbrains.kotlin.fir.expressions.FirCollectionLiteral;
import org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.AbstractCallCandidate;
import org.jetbrains.kotlin.fir.resolve.calls.AbstractCandidate;
import org.jetbrains.kotlin.fir.resolve.calls.AbstractConeResolutionAtom;
import org.jetbrains.kotlin.fir.resolve.calls.AdaptedCallableReferenceIsUsedWithReflection;
import org.jetbrains.kotlin.fir.resolve.calls.AmbiguousContextArgument;
import org.jetbrains.kotlin.fir.resolve.calls.AmbiguousInterceptedSymbol;
import org.jetbrains.kotlin.fir.resolve.calls.ArgumentPassedTwice;
import org.jetbrains.kotlin.fir.resolve.calls.ArgumentTypeMismatch;
import org.jetbrains.kotlin.fir.resolve.calls.DslScopeViolation;
import org.jetbrains.kotlin.fir.resolve.calls.DynamicReceiverExpectedButWasNonDynamic;
import org.jetbrains.kotlin.fir.resolve.calls.ErrorTypeInArguments;
import org.jetbrains.kotlin.fir.resolve.calls.FirSyntheticFunctionSymbol;
import org.jetbrains.kotlin.fir.resolve.calls.InaccessibleFromClassHeader;
import org.jetbrains.kotlin.fir.resolve.calls.InaccessibleOuterClassReceiver;
import org.jetbrains.kotlin.fir.resolve.calls.InapplicableNullableReceiver;
import org.jetbrains.kotlin.fir.resolve.calls.InferenceError;
import org.jetbrains.kotlin.fir.resolve.calls.InferredEmptyIntersectionDiagnostic;
import org.jetbrains.kotlin.fir.resolve.calls.InfixCallOfNonInfixFunction;
import org.jetbrains.kotlin.fir.resolve.calls.ManyLambdaExpressionArguments;
import org.jetbrains.kotlin.fir.resolve.calls.MissingInnerClassConstructorReceiver;
import org.jetbrains.kotlin.fir.resolve.calls.MixingNamedAndPositionArguments;
import org.jetbrains.kotlin.fir.resolve.calls.MultipleContextReceiversApplicableForExtensionReceivers;
import org.jetbrains.kotlin.fir.resolve.calls.NameForAmbiguousParameter;
import org.jetbrains.kotlin.fir.resolve.calls.NameNotFound;
import org.jetbrains.kotlin.fir.resolve.calls.NamedArgumentNotAllowed;
import org.jetbrains.kotlin.fir.resolve.calls.NoContextArgument;
import org.jetbrains.kotlin.fir.resolve.calls.NoReceiverAllowed;
import org.jetbrains.kotlin.fir.resolve.calls.NoValueForParameter;
import org.jetbrains.kotlin.fir.resolve.calls.NonVarargSpread;
import org.jetbrains.kotlin.fir.resolve.calls.NullForNotNullType;
import org.jetbrains.kotlin.fir.resolve.calls.OperatorCallOfConstructor;
import org.jetbrains.kotlin.fir.resolve.calls.OperatorCallOfNonOperatorFunction;
import org.jetbrains.kotlin.fir.resolve.calls.ReceiverShadowedByContextParameter;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionDiagnostic;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionDiagnosticKt;
import org.jetbrains.kotlin.fir.resolve.calls.TooManyArguments;
import org.jetbrains.kotlin.fir.resolve.calls.TypeParameterAsExpression;
import org.jetbrains.kotlin.fir.resolve.calls.TypeVariableAsExplicitReceiver;
import org.jetbrains.kotlin.fir.resolve.calls.TypeVariableReplacement;
import org.jetbrains.kotlin.fir.resolve.calls.TypeVariableTypeRemovingSubstitutorKt;
import org.jetbrains.kotlin.fir.resolve.calls.UnitReturnTypeLambdaContradictsExpectedType;
import org.jetbrains.kotlin.fir.resolve.calls.UnstableSmartCast;
import org.jetbrains.kotlin.fir.resolve.calls.UnsuccessfulCallableReferenceArgument;
import org.jetbrains.kotlin.fir.resolve.calls.UnsuccessfulCollectionLiteralArgument;
import org.jetbrains.kotlin.fir.resolve.calls.UnsupportedCompanionBlockOrExtensionCall;
import org.jetbrains.kotlin.fir.resolve.calls.UnsupportedContextualDeclarationCall;
import org.jetbrains.kotlin.fir.resolve.calls.VarargArgumentOutsideParentheses;
import org.jetbrains.kotlin.fir.resolve.calls.WrongNumberOfTypeArguments;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeAmbiguityError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeAmbiguousAlteredAssign;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeAmbiguouslyResolvedAnnotationArgument;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeAmbiguouslyResolvedAnnotationFromPlugin;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeConstraintSystemHasContradiction;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeContractDescriptionError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeCyclicTypeBound;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeDynamicUnsupported;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeForbiddenIntersection;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeFunctionCallExpectedError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeFunctionExpectedError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeHiddenCandidateError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeIllegalAnnotationError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeImportFromSingleton;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeInaccessibleOuterClass;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeInapplicableCandidateError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeInapplicableWrongReceiver;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeInstanceAccessBeforeSuperCall;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeLocalVariableNoTypeOrInitializer;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeNestedClassAccessedViaInstanceReference;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeNoCompanionObject;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeNoTypeArgumentsOnRhsError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeOperatorAmbiguityError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeOuterClassArgumentsRequired;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConePlaceholderProjectionInQualifierResolution;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeResolutionToClassifierError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeTypeArgumentsForOuterClassWhenNestedReferencedError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeTypeMismatch;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeTypeParameterInQualifiedAccess;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeTypeParameterSupertype;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeTypeVisibilityError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeUnresolvedNameError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeUnresolvedParentInImport;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeUnresolvedReferenceError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeUnresolvedSymbolError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeUnresolvedTypeQualifierError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeUnsupported;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeVariableExpectedError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeVisibilityError;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeWrongNumberOfTypeArgumentsError;
import org.jetbrains.kotlin.fir.resolve.inference.AnonymousFunctionBasedMultiLambdaBuilderInferenceRestriction;
import org.jetbrains.kotlin.fir.resolve.inference.ConeTypeParameterBasedTypeVariable;
import org.jetbrains.kotlin.fir.resolve.inference.model.ConeArgumentConstraintPosition;
import org.jetbrains.kotlin.fir.resolve.inference.model.ConeExpectedTypeConstraintPosition;
import org.jetbrains.kotlin.fir.resolve.inference.model.ConeLambdaArgumentConstraintPosition;
import org.jetbrains.kotlin.fir.resolve.inference.model.ConeReceiverConstraintPosition;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.symbols.ConeTypeParameterLookupTag;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirLocalPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeCapturedType;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeInferenceContext;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeContext;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeTypeVariable;
import org.jetbrains.kotlin.fir.types.FirErrorTypeRef;
import org.jetbrains.kotlin.fir.types.FirQualifierPart;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FunctionalTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.InferenceUtilsKt;
import org.jetbrains.kotlin.fir.types.ProjectionKind;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.fir.visitors.FirVisitorVoid;
import org.jetbrains.kotlin.lexer.KtSingleValueToken;
import org.jetbrains.kotlin.lexer.KtToken;
import org.jetbrains.kotlin.lexer.KtTokens;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.SpecialNames;
import org.jetbrains.kotlin.psi.KtLambdaExpression;
import org.jetbrains.kotlin.psi.stubs.elements.KtStubElementType;
import org.jetbrains.kotlin.psi.stubs.elements.KtStubElementTypes;
import org.jetbrains.kotlin.resolve.calls.inference.model.ConstrainingTypeIsError;
import org.jetbrains.kotlin.resolve.calls.inference.model.ConstraintPositionAndErrorsKt;
import org.jetbrains.kotlin.resolve.calls.inference.model.ConstraintSystemError;
import org.jetbrains.kotlin.resolve.calls.inference.model.FixVariableConstraintPosition;
import org.jetbrains.kotlin.resolve.calls.inference.model.InferredEmptyIntersection;
import org.jetbrains.kotlin.resolve.calls.inference.model.InferredEmptyIntersectionError;
import org.jetbrains.kotlin.resolve.calls.inference.model.LambdaArgumentConstraintPosition;
import org.jetbrains.kotlin.resolve.calls.inference.model.MultiLambdaBuilderInferenceRestriction;
import org.jetbrains.kotlin.resolve.calls.inference.model.NewConstraintError;
import org.jetbrains.kotlin.resolve.calls.inference.model.NotEnoughInformationForTypeParameter;
import org.jetbrains.kotlin.resolve.calls.inference.model.OnlyInputTypesDiagnostic;
import org.jetbrains.kotlin.resolve.calls.tower.CandidateApplicability;
import org.jetbrains.kotlin.resolve.calls.tower.CandidateApplicabilityKt;
import org.jetbrains.kotlin.types.EmptyIntersectionTypeKind;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;
import org.jetbrains.kotlin.types.model.TypeParameterMarker;
import org.jetbrains.kotlin.types.model.TypeVariableMarker;
import org.jetbrains.kotlin.util.LightTreeUtilsKt;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000ð\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\"\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u0006\u0012\u0002\b\u00030\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u001a8\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\b*\u00020\t2\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u001a.\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00010\b*\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u000f\u001a\u0004\u0018\u00010\u0004H\u0002\u001a.\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\b*\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u000f\u001a\u0004\u0018\u00010\u0004H\u0002\u001a.\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00010\b*\u00020\u00132\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002\u001a4\u0010\u0014\u001a\u0004\u0018\u00010\u0001*\u00020\t2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\n\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002\u001a:\u0010\u0015\u001a\u0004\u0018\u00010\u00012\n\u0010\u0016\u001a\u0006\u0012\u0002\b\u00030\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u000f\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002\u001a\u001a\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002\u001aJ\u0010\u001e\u001a\u0004\u0018\u00010\u00012\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020#2\n\u0010\u0016\u001a\u0006\u0012\u0002\b\u00030\u00172\b\u0010$\u001a\u0004\u0018\u00010%2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002\u001a\u0016\u0010&\u001a\u0004\u0018\u00010\u0001*\u00020'2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002\u001a6\u0010(\u001a\u0004\u0018\u00010\u0001*\u00020)2\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u000f\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\n\u0010\u0016\u001a\u0006\u0012\u0002\b\u00030\u0017H\u0002\u001a \u0010*\u001a\u00020 *\u00020 2\n\u0010\u0016\u001a\u0006\u0012\u0002\b\u00030\u00172\u0006\u0010+\u001a\u00020,H\u0002\u001aP\u0010-\u001a\u0004\u0018\u00010\u00012\b\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010.\u001a\u00020/2\f\u00100\u001a\b\u0012\u0004\u0012\u00020 012\f\u00102\u001a\b\u0012\u0004\u0012\u00020 012\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u00020#2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002\u001a\u0016\u0010<\u001a\u00020=*\u00020>2\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0002\u001a\f\u0010?\u001a\u00020@*\u00020\u0006H\u0002\u001a,\u0010A\u001a\u0004\u0018\u00010\u001b*\u00020=2\b\u0010B\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010C\u001a\u0004\u0018\u00010DH\u0000\u001aK\u0010A\u001a\n\u0012\u0004\u0012\u0002HF\u0018\u00010E\"\u0004\b\u0000\u0010F*\b\u0012\u0004\u0012\u0002HF0G2\b\u0010B\u001a\u0004\u0018\u00010\u00042\u0006\u0010H\u001a\u0002HF2\u0006\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010C\u001a\u0004\u0018\u00010DH\u0000¢\u0006\u0002\u0010I\u001ae\u0010A\u001a\u0010\u0012\u0004\u0012\u0002HF\u0012\u0004\u0012\u0002HK\u0018\u00010J\"\u0004\b\u0000\u0010F\"\u0004\b\u0001\u0010K*\u000e\u0012\u0004\u0012\u0002HF\u0012\u0004\u0012\u0002HK0L2\b\u0010B\u001a\u0004\u0018\u00010\u00042\u0006\u0010H\u001a\u0002HF2\u0006\u0010M\u001a\u0002HK2\u0006\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010C\u001a\u0004\u0018\u00010DH\u0000¢\u0006\u0002\u0010N\u001a\u007f\u0010A\u001a\u0016\u0012\u0004\u0012\u0002HF\u0012\u0004\u0012\u0002HK\u0012\u0004\u0012\u0002HP\u0018\u00010O\"\u0004\b\u0000\u0010F\"\u0004\b\u0001\u0010K\"\u0004\b\u0002\u0010P*\u0014\u0012\u0004\u0012\u0002HF\u0012\u0004\u0012\u0002HK\u0012\u0004\u0012\u0002HP0Q2\b\u0010B\u001a\u0004\u0018\u00010\u00042\u0006\u0010H\u001a\u0002HF2\u0006\u0010M\u001a\u0002HK2\u0006\u0010R\u001a\u0002HP2\u0006\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010C\u001a\u0004\u0018\u00010DH\u0000¢\u0006\u0002\u0010S\u001a\u0099\u0001\u0010A\u001a\u001c\u0012\u0004\u0012\u0002HF\u0012\u0004\u0012\u0002HK\u0012\u0004\u0012\u0002HP\u0012\u0004\u0012\u0002HU\u0018\u00010T\"\u0004\b\u0000\u0010F\"\u0004\b\u0001\u0010K\"\u0004\b\u0002\u0010P\"\u0004\b\u0003\u0010U*\u001a\u0012\u0004\u0012\u0002HF\u0012\u0004\u0012\u0002HK\u0012\u0004\u0012\u0002HP\u0012\u0004\u0012\u0002HU0V2\b\u0010B\u001a\u0004\u0018\u00010\u00042\u0006\u0010H\u001a\u0002HF2\u0006\u0010M\u001a\u0002HK2\u0006\u0010R\u001a\u0002HP2\u0006\u0010W\u001a\u0002HU2\u0006\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010C\u001a\u0004\u0018\u00010DH\u0000¢\u0006\u0002\u0010X\"\u0018\u00106\u001a\u00020 *\u0002078BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b8\u00109\"\u0018\u0010:\u001a\u00020 *\u0002078BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b;\u00109¨\u0006Y"}, d2 = {"toInvisibleReferenceDiagnostic", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnostic;", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "toFirDiagnostics", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "callOrAssignmentSource", "valueParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "mapInapplicableCandidateError", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeInapplicableCandidateError;", "qualifiedAccessSource", "mapSystemHasContradictionError", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeConstraintSystemHasContradiction;", "mapConeAmbiguityError", "Lorg/jetbrains/kotlin/fir/resolve/diagnostics/ConeAmbiguityError;", "mapOtherDiagnostic", "inapplicableNullableReceiver", "candidate", "Lorg/jetbrains/kotlin/fir/resolve/calls/AbstractCallCandidate;", "rootCause", "Lorg/jetbrains/kotlin/fir/resolve/calls/InapplicableNullableReceiver;", "unexpectedTrailingLambdaOnNewLineOrNull", "Lorg/jetbrains/kotlin/diagnostics/KtSimpleDiagnostic;", "argument", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "argumentTypeMismatch", "expectedType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "actualType", "isMismatchDueToNullability", Argument.Delimiters.none, "anonymousFunctionIfReturnExpression", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "mapUnstableSmartCast", "Lorg/jetbrains/kotlin/fir/resolve/calls/UnstableSmartCast;", "mapConstraintSystemError", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/ConstraintSystemError;", "substituteTypeVariableTypes", "typeContext", "Lorg/jetbrains/kotlin/fir/types/ConeTypeContext;", "inferredIntoEmptyIntersection", "typeVariable", "Lorg/jetbrains/kotlin/fir/types/ConeTypeVariable;", "incompatibleTypes", Argument.Delimiters.none, "causingTypes", "kind", "Lorg/jetbrains/kotlin/types/EmptyIntersectionTypeKind;", "isError", "lowerConeType", "Lorg/jetbrains/kotlin/resolve/calls/inference/model/NewConstraintError;", "getLowerConeType", "(Lorg/jetbrains/kotlin/resolve/calls/inference/model/NewConstraintError;)Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "upperConeType", "getUpperConeType", "getFactory", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory0;", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeSimpleDiagnostic;", "toDiagnosticContext", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticBaseContext;", "createOn", "element", "positioningStrategy", "Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticWithParameters1;", "A", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory1;", "a", "(Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory1;Lorg/jetbrains/kotlin/KtSourceElement;Ljava/lang/Object;Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;)Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticWithParameters1;", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticWithParameters2;", "B", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory2;", "b", "(Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory2;Lorg/jetbrains/kotlin/KtSourceElement;Ljava/lang/Object;Ljava/lang/Object;Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;)Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticWithParameters2;", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticWithParameters3;", "C", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory3;", "c", "(Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory3;Lorg/jetbrains/kotlin/KtSourceElement;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;)Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticWithParameters3;", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticWithParameters4;", "D", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory4;", "d", "(Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory4;Lorg/jetbrains/kotlin/KtSourceElement;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/diagnostics/AbstractSourceElementPositioningStrategy;)Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticWithParameters4;", "org.jetbrains.kotlin:checkers"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeDiagnosticToFirDiagnosticKt {

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
            try {
                iArr[ClassKind.CLASS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[DiagnosticKind.values().length];
            try {
                iArr2[DiagnosticKind.ReturnNotAllowed.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr2[DiagnosticKind.NotAFunctionLabel.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr2[DiagnosticKind.UnresolvedLabel.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[DiagnosticKind.AmbiguousLabel.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr2[DiagnosticKind.LabelNameClash.ordinal()] = 5;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr2[DiagnosticKind.NoThis.ordinal()] = 6;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr2[DiagnosticKind.IllegalConstExpression.ordinal()] = 7;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr2[DiagnosticKind.IllegalUnderscore.ordinal()] = 8;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr2[DiagnosticKind.DeserializationError.ordinal()] = 9;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr2[DiagnosticKind.InferenceError.ordinal()] = 10;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr2[DiagnosticKind.RecursionInImplicitTypes.ordinal()] = 11;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                iArr2[DiagnosticKind.Java.ordinal()] = 12;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                iArr2[DiagnosticKind.SuperNotAllowed.ordinal()] = 13;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                iArr2[DiagnosticKind.ExpressionExpected.ordinal()] = 14;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                iArr2[DiagnosticKind.JumpOutsideLoop.ordinal()] = 15;
            } catch (NoSuchFieldError unused17) {
            }
            try {
                iArr2[DiagnosticKind.NotLoopLabel.ordinal()] = 16;
            } catch (NoSuchFieldError unused18) {
            }
            try {
                iArr2[DiagnosticKind.VariableExpected.ordinal()] = 17;
            } catch (NoSuchFieldError unused19) {
            }
            try {
                iArr2[DiagnosticKind.ValueParameterWithNoTypeAnnotation.ordinal()] = 18;
            } catch (NoSuchFieldError unused20) {
            }
            try {
                iArr2[DiagnosticKind.IllegalProjectionUsage.ordinal()] = 19;
            } catch (NoSuchFieldError unused21) {
            }
            try {
                iArr2[DiagnosticKind.MissingStdlibClass.ordinal()] = 20;
            } catch (NoSuchFieldError unused22) {
            }
            try {
                iArr2[DiagnosticKind.IntLiteralOutOfRange.ordinal()] = 21;
            } catch (NoSuchFieldError unused23) {
            }
            try {
                iArr2[DiagnosticKind.IntLiteralWithLeadingZeros.ordinal()] = 22;
            } catch (NoSuchFieldError unused24) {
            }
            try {
                iArr2[DiagnosticKind.FloatLiteralOutOfRange.ordinal()] = 23;
            } catch (NoSuchFieldError unused25) {
            }
            try {
                iArr2[DiagnosticKind.WrongLongSuffix.ordinal()] = 24;
            } catch (NoSuchFieldError unused26) {
            }
            try {
                iArr2[DiagnosticKind.UnsignedNumbersAreNotPresent.ordinal()] = 25;
            } catch (NoSuchFieldError unused27) {
            }
            try {
                iArr2[DiagnosticKind.IncorrectCharacterLiteral.ordinal()] = 26;
            } catch (NoSuchFieldError unused28) {
            }
            try {
                iArr2[DiagnosticKind.EmptyCharacterLiteral.ordinal()] = 27;
            } catch (NoSuchFieldError unused29) {
            }
            try {
                iArr2[DiagnosticKind.TooManyCharactersInCharacterLiteral.ordinal()] = 28;
            } catch (NoSuchFieldError unused30) {
            }
            try {
                iArr2[DiagnosticKind.IllegalEscape.ordinal()] = 29;
            } catch (NoSuchFieldError unused31) {
            }
            try {
                iArr2[DiagnosticKind.RecursiveTypealiasExpansion.ordinal()] = 30;
            } catch (NoSuchFieldError unused32) {
            }
            try {
                iArr2[DiagnosticKind.LoopInSupertype.ordinal()] = 31;
            } catch (NoSuchFieldError unused33) {
            }
            try {
                iArr2[DiagnosticKind.IllegalSelector.ordinal()] = 32;
            } catch (NoSuchFieldError unused34) {
            }
            try {
                iArr2[DiagnosticKind.NoReceiverAllowed.ordinal()] = 33;
            } catch (NoSuchFieldError unused35) {
            }
            try {
                iArr2[DiagnosticKind.IsEnumEntry.ordinal()] = 34;
            } catch (NoSuchFieldError unused36) {
            }
            try {
                iArr2[DiagnosticKind.EnumEntryAsType.ordinal()] = 35;
            } catch (NoSuchFieldError unused37) {
            }
            try {
                iArr2[DiagnosticKind.NotASupertype.ordinal()] = 36;
            } catch (NoSuchFieldError unused38) {
            }
            try {
                iArr2[DiagnosticKind.SuperNotAvailable.ordinal()] = 37;
            } catch (NoSuchFieldError unused39) {
            }
            try {
                iArr2[DiagnosticKind.AnnotationInWhereClause.ordinal()] = 38;
            } catch (NoSuchFieldError unused40) {
            }
            try {
                iArr2[DiagnosticKind.MultipleAnnotationWithAllTarget.ordinal()] = 39;
            } catch (NoSuchFieldError unused41) {
            }
            try {
                iArr2[DiagnosticKind.UnderscoreWithoutRenamingInDestructuring.ordinal()] = 40;
            } catch (NoSuchFieldError unused42) {
            }
            try {
                iArr2[DiagnosticKind.UnresolvedSupertype.ordinal()] = 41;
            } catch (NoSuchFieldError unused43) {
            }
            try {
                iArr2[DiagnosticKind.UnresolvedExpandedType.ordinal()] = 42;
            } catch (NoSuchFieldError unused44) {
            }
            try {
                iArr2[DiagnosticKind.Other.ordinal()] = 43;
            } catch (NoSuchFieldError unused45) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    @Metadata(d1 = {"\u00007\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\u008a\b\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\r\u001a\u00020\u0004HÆ\u0003J\u0018\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u0004HÆ\u0001¢\u0006\u0002\u0010\u000fJ\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u0018"}, d2 = {"org/jetbrains/kotlin/fir/analysis/diagnostics/ConeDiagnosticToFirDiagnosticKt$toDiagnosticContext$SessionWrapper", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticBaseContext;", "Lorg/jetbrains/kotlin/fir/SessionHolder;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "getLanguageVersionSettings", "()Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "component1", "copy", "(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/analysis/diagnostics/ConeDiagnosticToFirDiagnosticKt$toDiagnosticContext$SessionWrapper;", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class SessionWrapper implements DiagnosticBaseContext, SessionHolder {
        private final FirSession session;

        public SessionWrapper(FirSession firSession) {
            firSession.getClass();
            this.session = firSession;
        }

        public static /* synthetic */ SessionWrapper copy$default(SessionWrapper sessionWrapper, FirSession firSession, int i, Object obj) {
            if ((i & 1) != 0) {
                firSession = sessionWrapper.session;
            }
            return sessionWrapper.copy(firSession);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final FirSession getSession() {
            return this.session;
        }

        public final SessionWrapper copy(FirSession session) {
            session.getClass();
            return new SessionWrapper(session);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof SessionWrapper) && Intrinsics.areEqual(this.session, ((SessionWrapper) other).session);
        }

        @Override // org.jetbrains.kotlin.diagnostics.DiagnosticBaseContext
        /* JADX INFO: renamed from: getLanguageVersionSettings */
        public LanguageVersionSettings get$languageVersionSettings() {
            return FirLanguageSettingsComponentKt.getLanguageVersionSettings(getSession());
        }

        @Override // org.jetbrains.kotlin.fir.SessionHolder
        public FirSession getSession() {
            return this.session;
        }

        public int hashCode() {
            return this.session.hashCode();
        }

        public String toString() {
            return "SessionWrapper(session=" + this.session + ')';
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final KtDiagnostic argumentTypeMismatch(KtSourceElement ktSourceElement, ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2, boolean z, AbstractCallCandidate<?> abstractCallCandidate, FirAnonymousFunction firAnonymousFunction, FirSession firSession) {
        FirExpression expression;
        FirBasedSymbol<?> symbol = abstractCallCandidate.getSymbol();
        symbol.getClass();
        FirCallableSymbol firCallableSymbol = (FirCallableSymbol) symbol;
        AbstractConeResolutionAtom chosenExtensionReceiver = abstractCallCandidate.getChosenExtensionReceiver();
        if (chosenExtensionReceiver == null) {
            chosenExtensionReceiver = abstractCallCandidate.getDispatchReceiver();
        }
        ConeKotlinType resolvedType = (chosenExtensionReceiver == null || (expression = chosenExtensionReceiver.getExpression()) == null) ? null : FirTypeUtilsKt.getResolvedType(expression);
        if (firAnonymousFunction != null) {
            return createOn$default(FirErrors.INSTANCE.getRETURN_TYPE_MISMATCH(), ktSourceElement, coneKotlinType, coneKotlinType2, firAnonymousFunction, Boolean.valueOf(z), firSession, null, 64, null);
        }
        if (coneKotlinType instanceof ConeCapturedType) {
            ConeCapturedType coneCapturedType = (ConeCapturedType) coneKotlinType;
            if (argumentTypeMismatch$isBasedOnStarOrOut(coneCapturedType) && resolvedType != null) {
                KtDiagnosticFactory3<ConeKotlinType, String, FirCallableSymbol<?>> member_projected_out = FirErrors.INSTANCE.getMEMBER_PROJECTED_OUT();
                String strProjectionKindAsString = FirHelpersKt.projectionKindAsString(coneCapturedType);
                FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) firCallableSymbol.getFir();
                while (ClassMembersKt.isSubstitutionOrIntersectionOverride(firCallableDeclaration)) {
                    FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
                    if (originalForSubstitutionOverrideAttr == null) {
                        originalForSubstitutionOverrideAttr = ClassMembersKt.isIntersectionOverride(firCallableDeclaration) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
                    }
                    if (originalForSubstitutionOverrideAttr == null) {
                        break;
                    }
                    firCallableDeclaration = originalForSubstitutionOverrideAttr;
                }
                FirCallableSymbol<FirCallableDeclaration> symbol2 = firCallableDeclaration.getSymbol();
                if (symbol2 != null) {
                    return createOn$default(member_projected_out, ktSourceElement, resolvedType, strProjectionKindAsString, symbol2, firSession, null, 32, null);
                }
                x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol<*>");
                return null;
            }
        }
        return createOn$default(FirErrors.INSTANCE.getARGUMENT_TYPE_MISMATCH(), ktSourceElement, coneKotlinType2, coneKotlinType, Boolean.valueOf(z), firSession, null, 32, null);
    }

    private static final boolean argumentTypeMismatch$isBasedOnStarOrOut(ConeCapturedType coneCapturedType) {
        ProjectionKind kind = coneCapturedType.getConstructor().getProjection().getKind();
        return kind == ProjectionKind.OUT || kind == ProjectionKind.STAR;
    }

    public static final <A, B> KtDiagnosticWithParameters2<A, B> createOn(KtDiagnosticFactory2<A, B> ktDiagnosticFactory2, KtSourceElement ktSourceElement, A a, B b, FirSession firSession, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy) {
        ktDiagnosticFactory2.getClass();
        firSession.getClass();
        return ktDiagnosticFactory2.on(KtDiagnosticReportHelpersKt.requireNotNull(ktSourceElement), a, b, abstractSourceElementPositioningStrategy, toDiagnosticContext(firSession));
    }

    public static /* synthetic */ KtDiagnosticWithParameters4 createOn$default(KtDiagnosticFactory4 ktDiagnosticFactory4, KtSourceElement ktSourceElement, Object obj, Object obj2, Object obj3, Object obj4, FirSession firSession, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy, int i, Object obj5) {
        return createOn(ktDiagnosticFactory4, ktSourceElement, obj, obj2, obj3, obj4, firSession, (i & 64) != 0 ? null : abstractSourceElementPositioningStrategy);
    }

    private static final KtDiagnosticFactory0 getFactory(ConeSimpleDiagnostic coneSimpleDiagnostic, KtSourceElement ktSourceElement) {
        switch (WhenMappings.$EnumSwitchMapping$1[coneSimpleDiagnostic.getKind().ordinal()]) {
            case 1:
                return FirErrors.INSTANCE.getRETURN_NOT_ALLOWED();
            case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
                return FirErrors.INSTANCE.getNOT_A_FUNCTION_LABEL();
            case 3:
                return FirErrors.INSTANCE.getUNRESOLVED_LABEL();
            case 4:
                return FirErrors.INSTANCE.getAMBIGUOUS_LABEL();
            case 5:
                return FirErrors.INSTANCE.getLABEL_NAME_CLASH();
            case 6:
                return FirErrors.INSTANCE.getNO_THIS();
            case 7:
                return FirErrors.INSTANCE.getILLEGAL_CONST_EXPRESSION();
            case 8:
                return FirErrors.INSTANCE.getILLEGAL_UNDERSCORE();
            case 9:
                return FirErrors.INSTANCE.getDESERIALIZATION_ERROR();
            case 10:
                return FirErrors.INSTANCE.getINFERENCE_ERROR();
            case 11:
                return FirErrors.INSTANCE.getRECURSION_IN_IMPLICIT_TYPES();
            case 12:
                return FirErrors.INSTANCE.getERROR_FROM_JAVA_RESOLUTION();
            case 13:
                return FirErrors.INSTANCE.getSUPER_IS_NOT_AN_EXPRESSION();
            case 14:
                IElementType elementType = ktSourceElement != null ? ktSourceElement.getElementType() : null;
                if (Intrinsics.areEqual(elementType, KtNodeTypes.BINARY_EXPRESSION)) {
                    return FirErrors.INSTANCE.getASSIGNMENT_IN_EXPRESSION_CONTEXT();
                }
                if (Intrinsics.areEqual(elementType, KtNodeTypes.FUN)) {
                    return FirErrors.INSTANCE.getANONYMOUS_FUNCTION_WITH_NAME();
                }
                return (Intrinsics.areEqual(elementType, KtNodeTypes.WHEN_CONDITION_IN_RANGE) || Intrinsics.areEqual(elementType, KtNodeTypes.WHEN_CONDITION_IS_PATTERN) || Intrinsics.areEqual(elementType, KtNodeTypes.WHEN_CONDITION_EXPRESSION)) ? FirErrors.INSTANCE.getEXPECTED_CONDITION() : FirErrors.INSTANCE.getEXPRESSION_EXPECTED();
            case 15:
                return FirErrors.INSTANCE.getBREAK_OR_CONTINUE_OUTSIDE_A_LOOP();
            case 16:
                return FirErrors.INSTANCE.getNOT_A_LOOP_LABEL();
            case 17:
                return FirErrors.INSTANCE.getVARIABLE_EXPECTED();
            case 18:
                return FirErrors.INSTANCE.getVALUE_PARAMETER_WITHOUT_EXPLICIT_TYPE();
            case 19:
                return FirErrors.INSTANCE.getILLEGAL_PROJECTION_USAGE();
            case 20:
                return FirErrors.INSTANCE.getMISSING_STDLIB_CLASS();
            case 21:
                return FirErrors.INSTANCE.getINT_LITERAL_OUT_OF_RANGE();
            case 22:
                return FirErrors.INSTANCE.getINT_LITERAL_WITH_LEADING_ZEROS();
            case 23:
                return FirErrors.INSTANCE.getFLOAT_LITERAL_OUT_OF_RANGE();
            case 24:
                return FirErrors.INSTANCE.getWRONG_LONG_SUFFIX();
            case 25:
                return FirErrors.INSTANCE.getUNSIGNED_LITERAL_WITHOUT_DECLARATIONS_ON_CLASSPATH();
            case 26:
                return FirErrors.INSTANCE.getINCORRECT_CHARACTER_LITERAL();
            case 27:
                return FirErrors.INSTANCE.getEMPTY_CHARACTER_LITERAL();
            case 28:
                return FirErrors.INSTANCE.getTOO_MANY_CHARACTERS_IN_CHARACTER_LITERAL();
            case 29:
                return FirErrors.INSTANCE.getILLEGAL_ESCAPE();
            case 30:
                return FirErrors.INSTANCE.getRECURSIVE_TYPEALIAS_EXPANSION();
            case 31:
                return FirErrors.INSTANCE.getCYCLIC_INHERITANCE_HIERARCHY();
            case 32:
                return FirErrors.INSTANCE.getILLEGAL_SELECTOR();
            case 33:
                return FirErrors.INSTANCE.getNO_RECEIVER_ALLOWED();
            case 34:
                return FirErrors.INSTANCE.getIS_ENUM_ENTRY();
            case 35:
                return FirErrors.INSTANCE.getENUM_ENTRY_AS_TYPE();
            case 36:
                return FirErrors.INSTANCE.getNOT_A_SUPERTYPE();
            case 37:
                return FirErrors.INSTANCE.getSUPER_NOT_AVAILABLE();
            case 38:
                return FirErrors.INSTANCE.getANNOTATION_IN_WHERE_CLAUSE_ERROR();
            case 39:
                return FirErrors.INSTANCE.getINAPPLICABLE_ALL_TARGET_IN_MULTI_ANNOTATION();
            case 40:
                return FirErrors.INSTANCE.getNAME_BASED_DESTRUCTURING_UNDERSCORE_WITHOUT_RENAMING();
            case 41:
            case 42:
            case 43:
                return FirErrors.INSTANCE.getOTHER_ERROR();
            default:
                bu8.a();
                return null;
        }
    }

    private static final ConeKotlinType getLowerConeType(NewConstraintError newConstraintError) {
        return newConstraintError.getLowerType();
    }

    private static final ConeKotlinType getUpperConeType(NewConstraintError newConstraintError) {
        return newConstraintError.getUpperType();
    }

    /* JADX WARN: Code duplicated, block: B:28:0x0078  */
    /* JADX WARN: Code duplicated, block: B:38:0x009e  */
    /* JADX WARN: Code duplicated, block: B:39:0x00af  */
    /* JADX WARN: Code duplicated, block: B:41:0x00b2  */
    /* JADX WARN: Code duplicated, block: B:42:0x00c2  */
    /* JADX WARN: Code duplicated, block: B:44:0x00c5  */
    /* JADX WARN: Code duplicated, block: B:46:0x00dc  */
    /* JADX WARN: Code duplicated, block: B:47:0x00de  */
    /* JADX WARN: Code duplicated, block: B:50:0x00eb  */
    /* JADX WARN: Code duplicated, block: B:52:0x0103  */
    /* JADX WARN: Code duplicated, block: B:53:0x0105  */
    private static final KtDiagnostic inapplicableNullableReceiver(AbstractCallCandidate<?> abstractCallCandidate, InapplicableNullableReceiver inapplicableNullableReceiver, KtSourceElement ktSourceElement, KtSourceElement ktSourceElement2, FirSession firSession) {
        KtSourceElement child$default;
        KtSourceElement child$default2;
        FirExpression firExpression;
        FirExpression firExpression2;
        if (abstractCallCandidate.getCallInfo().isImplicitInvoke()) {
            return createOn$default(FirErrors.INSTANCE.getUNSAFE_IMPLICIT_INVOKE_CALL(), ktSourceElement, inapplicableNullableReceiver.getActualType(), firSession, null, 8, null);
        }
        FirBasedSymbol<?> symbol = abstractCallCandidate.getSymbol();
        FirNamedFunctionSymbol firNamedFunctionSymbol = symbol instanceof FirNamedFunctionSymbol ? (FirNamedFunctionSymbol) symbol : null;
        Name name = firNamedFunctionSymbol != null ? firNamedFunctionSymbol.getName() : null;
        FirExpression explicitReceiver = abstractCallCandidate.getCallInfo().getExplicitReceiver();
        FirExpression firExpression3 = (FirExpression) CollectionsKt.singleOrNull(abstractCallCandidate.getCallInfo().getArgumentList().getArguments());
        if (explicitReceiver != null && firExpression3 != null) {
            IElementType elementType = ktSourceElement != null ? ktSourceElement.getElementType() : null;
            IElementType iElementType = KtNodeTypes.OPERATION_REFERENCE;
            if (!Intrinsics.areEqual(elementType, iElementType)) {
                if (Intrinsics.areEqual(ktSourceElement != null ? ktSourceElement.getElementType() : null, KtNodeTypes.BINARY_EXPRESSION)) {
                    if (firNamedFunctionSymbol != null) {
                        if (Intrinsics.areEqual(ktSourceElement.getElementType(), KtNodeTypes.BINARY_EXPRESSION)) {
                            iElementType.getClass();
                            child$default = FirSourceUtilsKt.getChild$default(ktSourceElement, iElementType, 0, 0, false, 14, (Object) null);
                        } else {
                            child$default = ktSourceElement;
                        }
                        if (child$default != null) {
                            KtToken ktToken = KtTokens.IDENTIFIER;
                            ktToken.getClass();
                            child$default2 = FirSourceUtilsKt.getChild$default(child$default, (IElementType) ktToken, 0, 0, false, 14, (Object) null);
                        } else {
                            child$default2 = null;
                        }
                        if (child$default2 != null) {
                            KtDiagnosticFactory4<ConeKotlinType, FirExpression, String, FirExpression> unsafe_infix_call = FirErrors.INSTANCE.getUNSAFE_INFIX_CALL();
                            ConeKotlinType actualType = inapplicableNullableReceiver.getActualType();
                            name.getClass();
                            String strAsString = name.asString();
                            if (firExpression3.getSource() != null) {
                                firExpression2 = firExpression3;
                            } else {
                                firExpression2 = null;
                            }
                            return createOn$default(unsafe_infix_call, ktSourceElement, actualType, explicitReceiver, strAsString, firExpression2, firSession, null, 64, null);
                        }
                        KtDiagnosticFactory4<ConeKotlinType, FirExpression, String, FirExpression> unsafe_operator_call = FirErrors.INSTANCE.getUNSAFE_OPERATOR_CALL();
                        ConeKotlinType actualType2 = inapplicableNullableReceiver.getActualType();
                        name.getClass();
                        String strAsString2 = name.asString();
                        if (firExpression3.getSource() != null) {
                            firExpression = firExpression3;
                        } else {
                            firExpression = null;
                        }
                        return createOn$default(unsafe_operator_call, ktSourceElement, actualType2, explicitReceiver, strAsString2, firExpression, firSession, null, 64, null);
                    }
                    if (Intrinsics.areEqual(ktSourceElement.getElementType(), KtNodeTypes.BINARY_EXPRESSION)) {
                        iElementType.getClass();
                        child$default = FirSourceUtilsKt.getChild$default(ktSourceElement, iElementType, 0, 0, false, 14, (Object) null);
                    } else {
                        child$default = ktSourceElement;
                    }
                    if (child$default != null) {
                        KtToken ktToken2 = KtTokens.IDENTIFIER;
                        ktToken2.getClass();
                        child$default2 = FirSourceUtilsKt.getChild$default(child$default, (IElementType) ktToken2, 0, 0, false, 14, (Object) null);
                    } else {
                        child$default2 = null;
                    }
                    if (child$default2 != null) {
                        KtDiagnosticFactory4<ConeKotlinType, FirExpression, String, FirExpression> unsafe_infix_call2 = FirErrors.INSTANCE.getUNSAFE_INFIX_CALL();
                        ConeKotlinType actualType3 = inapplicableNullableReceiver.getActualType();
                        name.getClass();
                        String strAsString3 = name.asString();
                        if (firExpression3.getSource() != null) {
                            firExpression2 = firExpression3;
                        } else {
                            firExpression2 = null;
                        }
                        return createOn$default(unsafe_infix_call2, ktSourceElement, actualType3, explicitReceiver, strAsString3, firExpression2, firSession, null, 64, null);
                    }
                    KtDiagnosticFactory4<ConeKotlinType, FirExpression, String, FirExpression> unsafe_operator_call2 = FirErrors.INSTANCE.getUNSAFE_OPERATOR_CALL();
                    ConeKotlinType actualType4 = inapplicableNullableReceiver.getActualType();
                    name.getClass();
                    String strAsString4 = name.asString();
                    if (firExpression3.getSource() != null) {
                        firExpression = firExpression3;
                    } else {
                        firExpression = null;
                    }
                    return createOn$default(unsafe_operator_call2, ktSourceElement, actualType4, explicitReceiver, strAsString4, firExpression, firSession, null, 64, null);
                }
            } else if ((firNamedFunctionSymbol != null && firNamedFunctionSymbol.getResolvedStatus().isOperator()) || (firNamedFunctionSymbol != null && firNamedFunctionSymbol.getResolvedStatus().isInfix())) {
                if (Intrinsics.areEqual(ktSourceElement.getElementType(), KtNodeTypes.BINARY_EXPRESSION)) {
                    iElementType.getClass();
                    child$default = FirSourceUtilsKt.getChild$default(ktSourceElement, iElementType, 0, 0, false, 14, (Object) null);
                } else {
                    child$default = ktSourceElement;
                }
                if (child$default != null) {
                    KtToken ktToken3 = KtTokens.IDENTIFIER;
                    ktToken3.getClass();
                    child$default2 = FirSourceUtilsKt.getChild$default(child$default, (IElementType) ktToken3, 0, 0, false, 14, (Object) null);
                } else {
                    child$default2 = null;
                }
                if (child$default2 != null) {
                    KtDiagnosticFactory4<ConeKotlinType, FirExpression, String, FirExpression> unsafe_infix_call3 = FirErrors.INSTANCE.getUNSAFE_INFIX_CALL();
                    ConeKotlinType actualType5 = inapplicableNullableReceiver.getActualType();
                    name.getClass();
                    String strAsString5 = name.asString();
                    if (firExpression3.getSource() != null) {
                        firExpression2 = firExpression3;
                    } else {
                        firExpression2 = null;
                    }
                    return createOn$default(unsafe_infix_call3, ktSourceElement, actualType5, explicitReceiver, strAsString5, firExpression2, firSession, null, 64, null);
                }
                KtDiagnosticFactory4<ConeKotlinType, FirExpression, String, FirExpression> unsafe_operator_call3 = FirErrors.INSTANCE.getUNSAFE_OPERATOR_CALL();
                ConeKotlinType actualType6 = inapplicableNullableReceiver.getActualType();
                name.getClass();
                String strAsString6 = name.asString();
                if (firExpression3.getSource() != null) {
                    firExpression = firExpression3;
                } else {
                    firExpression = null;
                }
                return createOn$default(unsafe_operator_call3, ktSourceElement, actualType6, explicitReceiver, strAsString6, firExpression, firSession, null, 64, null);
            }
        }
        if (abstractCallCandidate.getCallInfo().getCallSite() instanceof FirCallableReferenceAccess) {
            return createOn$default(FirErrors.INSTANCE.getUNSAFE_CALLABLE_REFERENCE(), ktSourceElement2 == null ? ktSourceElement : ktSourceElement2, inapplicableNullableReceiver.getActualType(), firSession, null, 8, null);
        }
        if (Intrinsics.areEqual(ktSourceElement != null ? ktSourceElement.getKind() : null, KtFakeSourceElementKind.ArrayAccessNameReference.INSTANCE)) {
            return createOn$default(FirErrors.INSTANCE.getUNSAFE_CALL(), ktSourceElement, inapplicableNullableReceiver.getActualType(), explicitReceiver, firSession, null, 16, null);
        }
        return createOn$default(FirErrors.INSTANCE.getUNSAFE_CALL(), ktSourceElement2 == null ? ktSourceElement : ktSourceElement2, inapplicableNullableReceiver.getActualType(), explicitReceiver, firSession, null, 16, null);
    }

    private static final KtDiagnostic inferredIntoEmptyIntersection(KtSourceElement ktSourceElement, ConeTypeVariable coneTypeVariable, Collection<? extends ConeKotlinType> collection, Collection<? extends ConeKotlinType> collection2, EmptyIntersectionTypeKind emptyIntersectionTypeKind, boolean z, FirSession firSession) {
        String string;
        String str;
        KtDiagnosticFactory4<String, Collection<ConeKotlinType>, String, String> inferred_type_variable_into_possible_empty_intersection;
        Name name;
        TypeParameterMarker originalTypeParameter = coneTypeVariable.getTypeConstructor().getOriginalTypeParameter();
        ConeTypeParameterLookupTag coneTypeParameterLookupTag = originalTypeParameter instanceof ConeTypeParameterLookupTag ? (ConeTypeParameterLookupTag) originalTypeParameter : null;
        if (coneTypeParameterLookupTag == null || (name = coneTypeParameterLookupTag.getName()) == null || (string = name.asString()) == null) {
            string = coneTypeVariable.toString();
        }
        String str2 = string;
        if (Intrinsics.areEqual(collection, collection2)) {
            str = Argument.Delimiters.none;
        } else {
            str = ": " + CollectionsKt.joinToString$default(collection2, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 63, (Object) null);
        }
        String str3 = str;
        if (emptyIntersectionTypeKind.isDefinitelyEmpty()) {
            inferred_type_variable_into_possible_empty_intersection = z ? (KtDiagnosticFactory4) FirErrors.INSTANCE.getINFERRED_TYPE_VARIABLE_INTO_EMPTY_INTERSECTION().getErrorFactory() : (KtDiagnosticFactory4) FirErrors.INSTANCE.getINFERRED_TYPE_VARIABLE_INTO_EMPTY_INTERSECTION().getWarningFactory();
        } else {
            inferred_type_variable_into_possible_empty_intersection = FirErrors.INSTANCE.getINFERRED_TYPE_VARIABLE_INTO_POSSIBLE_EMPTY_INTERSECTION();
        }
        return createOn$default(inferred_type_variable_into_possible_empty_intersection, ktSourceElement, str2, collection, emptyIntersectionTypeKind.getDescription(), str3, firSession, null, 64, null);
    }

    /* JADX WARN: Code duplicated, block: B:75:0x013c  */
    private static final List<KtDiagnostic> mapConeAmbiguityError(ConeAmbiguityError coneAmbiguityError, KtSourceElement ktSourceElement, KtSourceElement ktSourceElement2, FirSession firSession) {
        UnstableSmartCast unstableSmartCast;
        List<ResolutionDiagnostic> diagnostics;
        Object next;
        Pair pair;
        List<ResolutionDiagnostic> diagnostics2;
        Object next2;
        Collection<AbstractCandidate> candidates = coneAmbiguityError.getCandidates();
        if (!(candidates instanceof Collection) || !candidates.isEmpty()) {
            Iterator<T> it = candidates.iterator();
            while (true) {
                if (it.hasNext()) {
                    AbstractCandidate abstractCandidate = (AbstractCandidate) it.next();
                    if (!(abstractCandidate instanceof AbstractCallCandidate)) {
                        break;
                    }
                    AbstractCallCandidate abstractCallCandidate = (AbstractCallCandidate) abstractCandidate;
                    if (!abstractCallCandidate.getArgumentMappingInitialized()) {
                        break;
                    }
                    Set setKeySet = abstractCallCandidate.getArgumentMapping().keySet();
                    setKeySet.getClass();
                    Set set = setKeySet;
                    if (!(set instanceof Collection) || !set.isEmpty()) {
                        Iterator it2 = set.iterator();
                        while (true) {
                            if (it2.hasNext()) {
                                if (mapConeAmbiguityError$containsErrorTypeForSuppressingAmbiguityError((AbstractConeResolutionAtom) it2.next())) {
                                }
                            }
                        }
                    }
                    List<AbstractConeResolutionAtom> contextArguments = abstractCallCandidate.getContextArguments();
                    if (contextArguments != null) {
                        List<AbstractConeResolutionAtom> list = contextArguments;
                        if (!(list instanceof Collection) || !list.isEmpty()) {
                            Iterator<T> it3 = list.iterator();
                            while (true) {
                                if (it3.hasNext()) {
                                    if (mapConeAmbiguityError$containsErrorTypeForSuppressingAmbiguityError((AbstractConeResolutionAtom) it3.next())) {
                                    }
                                }
                            }
                        }
                    }
                    AbstractConeResolutionAtom chosenExtensionReceiver = abstractCallCandidate.getChosenExtensionReceiver();
                    if (chosenExtensionReceiver == null || !mapConeAmbiguityError$containsErrorTypeForSuppressingAmbiguityError(chosenExtensionReceiver)) {
                        break;
                    }
                }
            }
            if (CandidateApplicabilityKt.isSuccess(coneAmbiguityError.getApplicability())) {
                KtDiagnosticFactory1<Collection<FirBasedSymbol<?>>> overload_resolution_ambiguity = FirErrors.INSTANCE.getOVERLOAD_RESOLUTION_AMBIGUITY();
                Collection<AbstractCandidate> candidates2 = coneAmbiguityError.getCandidates();
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(candidates2, 10));
                Iterator<T> it4 = candidates2.iterator();
                while (it4.hasNext()) {
                    arrayList.add(((AbstractCandidate) it4.next()).getSymbol());
                }
                return CollectionsKt.listOfNotNull(createOn$default(overload_resolution_ambiguity, ktSourceElement, arrayList, firSession, null, 8, null));
            }
            Pair pair2 = null;
            if (coneAmbiguityError.getApplicability() == CandidateApplicability.UNSAFE_CALL) {
                for (AbstractCandidate abstractCandidate2 : coneAmbiguityError.getCandidates()) {
                    AbstractCallCandidate abstractCallCandidate2 = abstractCandidate2 instanceof AbstractCallCandidate ? (AbstractCallCandidate) abstractCandidate2 : null;
                    if (abstractCallCandidate2 == null || (diagnostics2 = abstractCallCandidate2.getDiagnostics()) == null) {
                        pair = null;
                    } else {
                        Iterator<T> it5 = diagnostics2.iterator();
                        do {
                            if (!it5.hasNext()) {
                                next2 = null;
                                break;
                            }
                            next2 = it5.next();
                        } while (!(next2 instanceof InapplicableNullableReceiver));
                        InapplicableNullableReceiver inapplicableNullableReceiver = (InapplicableNullableReceiver) next2;
                        if (inapplicableNullableReceiver != null) {
                            pair = TuplesKt.to(inapplicableNullableReceiver, abstractCandidate2);
                        } else {
                            pair = null;
                        }
                    }
                    if (pair != null) {
                        pair2 = pair;
                        break;
                    }
                }
                return pair2 != null ? CollectionsKt.listOfNotNull(inapplicableNullableReceiver((AbstractCallCandidate) pair2.getSecond(), (InapplicableNullableReceiver) pair2.getFirst(), ktSourceElement, ktSourceElement2, firSession)) : mapConeAmbiguityError$noneApplicable(coneAmbiguityError, ktSourceElement, firSession);
            }
            if (coneAmbiguityError.getApplicability() != CandidateApplicability.UNSTABLE_SMARTCAST) {
                return mapConeAmbiguityError$noneApplicable(coneAmbiguityError, ktSourceElement, firSession);
            }
            Iterator<T> it6 = coneAmbiguityError.getCandidates().iterator();
            do {
                if (!it6.hasNext()) {
                    unstableSmartCast = null;
                    break;
                }
                AbstractCandidate abstractCandidate3 = (AbstractCandidate) it6.next();
                AbstractCallCandidate abstractCallCandidate3 = abstractCandidate3 instanceof AbstractCallCandidate ? (AbstractCallCandidate) abstractCandidate3 : null;
                if (abstractCallCandidate3 == null || (diagnostics = abstractCallCandidate3.getDiagnostics()) == null) {
                    unstableSmartCast = null;
                } else {
                    Iterator<T> it7 = diagnostics.iterator();
                    do {
                        if (!it7.hasNext()) {
                            next = null;
                            break;
                        }
                        next = it7.next();
                    } while (!(next instanceof UnstableSmartCast));
                    unstableSmartCast = (UnstableSmartCast) next;
                }
            } while (unstableSmartCast == null);
            if (unstableSmartCast != null) {
                return CollectionsKt.listOfNotNull(mapUnstableSmartCast(unstableSmartCast, firSession));
            }
            hb9.a("No element of the collection was transformed to a non-null value.");
            return null;
        }
        return CollectionsKt.emptyList();
    }

    private static final boolean mapConeAmbiguityError$containsErrorTypeForSuppressingAmbiguityError(AbstractConeResolutionAtom abstractConeResolutionAtom) {
        FirExpression expression = abstractConeResolutionAtom.getExpression();
        if ((expression instanceof FirCollectionLiteral) || (expression instanceof FirCallableReferenceAccess) || (expression instanceof FirAnonymousFunctionExpression)) {
            return false;
        }
        return ConeTypeUtilsKt.hasError(FirTypeUtilsKt.getResolvedType(abstractConeResolutionAtom.getExpression()));
    }

    private static final List<KtDiagnostic> mapConeAmbiguityError$noneApplicable(ConeAmbiguityError coneAmbiguityError, KtSourceElement ktSourceElement, FirSession firSession) {
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        Map<? extends AbstractCandidate, ConeDiagnostic> candidatesWithErrors = coneAmbiguityError.getCandidatesWithErrors();
        ArrayList<Pair> arrayList = new ArrayList(candidatesWithErrors.size());
        for (Map.Entry<? extends AbstractCandidate, ConeDiagnostic> entry : candidatesWithErrors.entrySet()) {
            AbstractCandidate key = entry.getKey();
            ConeDiagnostic value = entry.getValue();
            FirBasedSymbol<?> symbol = key.getSymbol();
            List<KtDiagnostic> firDiagnostics = value != null ? toFirDiagnostics(value, firSession, ktSourceElement, null, null) : null;
            if (firDiagnostics == null) {
                firDiagnostics = CollectionsKt.emptyList();
            }
            arrayList.add(TuplesKt.to(symbol, firDiagnostics));
        }
        ArrayList<Pair> arrayList2 = new ArrayList();
        for (Pair pair : arrayList) {
            FirBasedSymbol firBasedSymbol = (FirBasedSymbol) pair.component1();
            List list = (List) pair.component2();
            ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator it = list.iterator();
            while (it.hasNext()) {
                arrayList3.add(TuplesKt.to((KtDiagnostic) it.next(), firBasedSymbol));
            }
            CollectionsKt.addAll(arrayList2, arrayList3);
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Pair pair2 : arrayList2) {
            KtDiagnostic ktDiagnostic = (KtDiagnostic) pair2.getFirst();
            Object arrayList4 = linkedHashMap.get(ktDiagnostic);
            if (arrayList4 == null) {
                arrayList4 = new ArrayList();
                linkedHashMap.put(ktDiagnostic, arrayList4);
            }
            ((List) arrayList4).add((FirBasedSymbol) pair2.getSecond());
        }
        LinkedHashMap linkedHashMap2 = new LinkedHashMap();
        for (Map.Entry entry2 : linkedHashMap.entrySet()) {
            if (((List) entry2.getValue()).size() == arrayList.size()) {
                linkedHashMap2.put(entry2.getKey(), entry2.getValue());
            }
        }
        ArrayList arrayList5 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        for (Pair pair3 : arrayList) {
            FirBasedSymbol firBasedSymbol2 = (FirBasedSymbol) pair3.component1();
            List list2 = (List) pair3.component2();
            ArrayList arrayList6 = new ArrayList();
            for (Object obj : list2) {
                if (!linkedHashMap2.containsKey((KtDiagnostic) obj)) {
                    arrayList6.add(obj);
                }
            }
            ArrayList arrayList7 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList6, 10));
            Iterator it2 = arrayList6.iterator();
            while (it2.hasNext()) {
                arrayList7.add(((KtDiagnostic) it2.next()).renderMessage());
            }
            arrayList5.add(TuplesKt.to(firBasedSymbol2, arrayList7));
        }
        org.jetbrains.kotlin.utils.CollectionsKt.addIfNotNull(listCreateListBuilder, createOn$default(FirErrors.INSTANCE.getNONE_APPLICABLE(), ktSourceElement, arrayList5, firSession, null, 8, null));
        Iterator it3 = linkedHashMap2.entrySet().iterator();
        while (it3.hasNext()) {
            listCreateListBuilder.add((KtDiagnostic) ((Map.Entry) it3.next()).getKey());
        }
        return CollectionsKt.build(listCreateListBuilder);
    }

    private static final KtDiagnostic mapConstraintSystemError(ConstraintSystemError constraintSystemError, KtSourceElement ktSourceElement, KtSourceElement ktSourceElement2, FirSession firSession, AbstractCallCandidate<?> abstractCallCandidate) {
        Pair pair;
        ConeInferenceContext typeContext = TypeComponentsKt.getTypeContext(firSession);
        if (constraintSystemError instanceof NewConstraintError) {
            NewConstraintError newConstraintError = (NewConstraintError) constraintSystemError;
            LambdaArgumentConstraintPosition from = newConstraintError.getPosition().getFrom();
            if (from instanceof ConeArgumentConstraintPosition) {
                pair = TuplesKt.to(((ConeArgumentConstraintPosition) from).getArgument(), null);
            } else if (from instanceof ConeLambdaArgumentConstraintPosition) {
                Object lambda = ConstraintPositionAndErrorsKt.getLambda(from);
                FirExpression anonymousFunctionReturnExpression = ((ConeLambdaArgumentConstraintPosition) from).getAnonymousFunctionReturnExpression();
                pair = TuplesKt.to(lambda, anonymousFunctionReturnExpression != null ? anonymousFunctionReturnExpression.getSource() : null);
            } else if (from instanceof ConeReceiverConstraintPosition) {
                ConeReceiverConstraintPosition coneReceiverConstraintPosition = (ConeReceiverConstraintPosition) from;
                Object argument = coneReceiverConstraintPosition.getArgument();
                KtSourceElement source = ((FirExpression) coneReceiverConstraintPosition.getArgument()).getSource();
                if (!Intrinsics.areEqual(source != null ? source.getKind() : null, KtRealSourceElementKind.INSTANCE)) {
                    source = null;
                }
                if (source == null) {
                    source = coneReceiverConstraintPosition.getSource();
                }
                pair = TuplesKt.to(argument, source);
            } else {
                pair = TuplesKt.to(null, null);
            }
            FirElement firElement = (FirElement) pair.component1();
            KtSourceElement source2 = (KtSourceElement) pair.component2();
            boolean zIsTypeMismatchDueToNullability = InferenceUtilsKt.isTypeMismatchDueToNullability(typeContext, getLowerConeType(newConstraintError), getUpperConeType(newConstraintError));
            if (firElement == null) {
                if (!(from instanceof ConeExpectedTypeConstraintPosition)) {
                    return null;
                }
                return createOn$default(FirErrors.INSTANCE.getTYPE_MISMATCH(), ktSourceElement2 == null ? ktSourceElement : ktSourceElement2, substituteTypeVariableTypes(getUpperConeType(newConstraintError), abstractCallCandidate, typeContext), substituteTypeVariableTypes(!ConeBuiltinTypeUtilsKt.isNullableNothing(getLowerConeType(newConstraintError)) ? getLowerConeType(newConstraintError) : TypeUtilsKt.withNullability$default(getUpperConeType(newConstraintError), true, typeContext, null, false, 12, null), abstractCallCandidate, typeContext), Boolean.valueOf(zIsTypeMismatchDueToNullability), firSession, null, 32, null);
            }
            if (source2 == null && (source2 = firElement.getSource()) == null) {
                source2 = ktSourceElement;
            }
            ConeKotlinType coneKotlinTypeSubstituteTypeVariableTypes = substituteTypeVariableTypes(getUpperConeType(newConstraintError), abstractCallCandidate, typeContext);
            ConeKotlinType coneKotlinTypeSubstituteTypeVariableTypes2 = substituteTypeVariableTypes(getLowerConeType(newConstraintError), abstractCallCandidate, typeContext);
            ConeLambdaArgumentConstraintPosition coneLambdaArgumentConstraintPosition = from instanceof ConeLambdaArgumentConstraintPosition ? (ConeLambdaArgumentConstraintPosition) from : null;
            return argumentTypeMismatch(source2, coneKotlinTypeSubstituteTypeVariableTypes, coneKotlinTypeSubstituteTypeVariableTypes2, zIsTypeMismatchDueToNullability, abstractCallCandidate, coneLambdaArgumentConstraintPosition != null ? (FirAnonymousFunction) ConstraintPositionAndErrorsKt.getLambda(coneLambdaArgumentConstraintPosition) : null, firSession);
        }
        if (constraintSystemError instanceof NotEnoughInformationForTypeParameter) {
            if (mapConstraintSystemError$isUnreportedNotEnoughInformationForTypeParameter(abstractCallCandidate, ktSourceElement)) {
                TypeParameterMarker originalTypeParameter = ((ConeTypeVariable) ((NotEnoughInformationForTypeParameter) constraintSystemError).getTypeVariable()).getTypeConstructor().getOriginalTypeParameter();
                ConeTypeParameterLookupTag coneTypeParameterLookupTag = originalTypeParameter != null ? (ConeTypeParameterLookupTag) originalTypeParameter : null;
                if (coneTypeParameterLookupTag != null) {
                    return createOn$default(FirErrors.INSTANCE.getCANNOT_INFER_PARAMETER_TYPE(), ktSourceElement, coneTypeParameterLookupTag.getTypeParameterSymbol(), firSession, null, 8, null);
                }
            }
            return null;
        }
        if (constraintSystemError instanceof InferredEmptyIntersection) {
            InferredEmptyIntersection inferredEmptyIntersection = (InferredEmptyIntersection) constraintSystemError;
            ConeTypeVariable coneTypeVariable = (ConeTypeVariable) inferredEmptyIntersection.getTypeVariable();
            KtSourceElement ktSourceElementMapConstraintSystemError$sourceOfCallToSymbolWith = mapConstraintSystemError$sourceOfCallToSymbolWith(abstractCallCandidate, coneTypeVariable);
            if (ktSourceElementMapConstraintSystemError$sourceOfCallToSymbolWith == null) {
                ktSourceElementMapConstraintSystemError$sourceOfCallToSymbolWith = ktSourceElement;
            }
            List incompatibleTypes = inferredEmptyIntersection.getIncompatibleTypes();
            incompatibleTypes.getClass();
            List causingTypes = inferredEmptyIntersection.getCausingTypes();
            causingTypes.getClass();
            return inferredIntoEmptyIntersection(ktSourceElementMapConstraintSystemError$sourceOfCallToSymbolWith, coneTypeVariable, incompatibleTypes, causingTypes, inferredEmptyIntersection.getKind(), constraintSystemError instanceof InferredEmptyIntersectionError, firSession);
        }
        if (constraintSystemError instanceof OnlyInputTypesDiagnostic) {
            KtDiagnosticFactory1<FirTypeParameterSymbol> type_inference_only_input_types_error = FirErrors.INSTANCE.getTYPE_INFERENCE_ONLY_INPUT_TYPES_ERROR();
            TypeVariableMarker typeVariable = ((OnlyInputTypesDiagnostic) constraintSystemError).getTypeVariable();
            typeVariable.getClass();
            return createOn$default(type_inference_only_input_types_error, ktSourceElement, ((ConeTypeParameterBasedTypeVariable) typeVariable).getTypeParameterSymbol(), firSession, null, 8, null);
        }
        if (!(constraintSystemError instanceof AnonymousFunctionBasedMultiLambdaBuilderInferenceRestriction)) {
            if (!(constraintSystemError instanceof MultiLambdaBuilderInferenceRestriction)) {
                return null;
            }
            AddToStdlibKt.shouldNotBeCalled$default((String) null, 1, (Object) null);
            wq6.a();
            return null;
        }
        AnonymousFunctionBasedMultiLambdaBuilderInferenceRestriction anonymousFunctionBasedMultiLambdaBuilderInferenceRestriction = (AnonymousFunctionBasedMultiLambdaBuilderInferenceRestriction) constraintSystemError;
        FirTypeParameterSymbol typeParameterSymbol = ((ConeTypeParameterLookupTag) anonymousFunctionBasedMultiLambdaBuilderInferenceRestriction.getTypeParameter()).getTypeParameterSymbol();
        KtDiagnosticFactory2<Name, Name> builder_inference_multi_lambda_restriction = FirErrors.INSTANCE.getBUILDER_INFERENCE_MULTI_LAMBDA_RESTRICTION();
        KtSourceElement source3 = ((FirAnonymousFunction) anonymousFunctionBasedMultiLambdaBuilderInferenceRestriction.getAnonymous()).getSource();
        if (source3 == null) {
            source3 = ktSourceElement;
        }
        Name name = typeParameterSymbol.getName();
        Name memberDeclarationNameOrNull = FirDeclarationUtilKt.getMemberDeclarationNameOrNull(typeParameterSymbol.getContainingDeclarationSymbol());
        if (memberDeclarationNameOrNull != null) {
            return createOn$default(builder_inference_multi_lambda_restriction, source3, name, memberDeclarationNameOrNull, firSession, null, 16, null);
        }
        k2d.a("containingDeclarationSymbol must have been a member declaration");
        return null;
    }

    private static final boolean mapConstraintSystemError$isUnreportedNotEnoughInformationForTypeParameter(AbstractCallCandidate<?> abstractCallCandidate, KtSourceElement ktSourceElement) {
        if ((abstractCallCandidate.getSymbol() instanceof FirConstructorSymbol) && (abstractCallCandidate.getCallInfo().getCallSite() instanceof FirDelegatedConstructorCall)) {
            return true;
        }
        return Intrinsics.areEqual(ktSourceElement != null ? ktSourceElement.getKind() : null, KtFakeSourceElementKind.ErrorExpressionForTransformedArrayOf.INSTANCE);
    }

    private static final KtSourceElement mapConstraintSystemError$sourceOfCallToSymbolWith(AbstractCallCandidate<?> abstractCallCandidate, final ConeTypeVariable coneTypeVariable) {
        if (!(coneTypeVariable instanceof ConeTypeParameterBasedTypeVariable)) {
            return null;
        }
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        abstractCallCandidate.getCallInfo().getCallSite().accept(new FirVisitorVoid() { // from class: org.jetbrains.kotlin.fir.analysis.diagnostics.ConeDiagnosticToFirDiagnosticKt$mapConstraintSystemError$sourceOfCallToSymbolWith$1
            public void visitElement(FirElement element) {
                element.getClass();
                if (objectRef.element != null) {
                    return;
                }
                if (element instanceof FirQualifiedAccessExpression) {
                    FirQualifiedAccessExpression firQualifiedAccessExpression = (FirQualifiedAccessExpression) element;
                    FirCallableSymbol resolvedCallableSymbol$default = FirReferenceUtilsKt.toResolvedCallableSymbol$default(firQualifiedAccessExpression.getCalleeReference(), false, 1, null);
                    if (resolvedCallableSymbol$default != null && resolvedCallableSymbol$default.getTypeParameterSymbols().contains(((ConeTypeParameterBasedTypeVariable) coneTypeVariable).getTypeParameterSymbol())) {
                        objectRef.element = firQualifiedAccessExpression.getCalleeReference().getSource();
                        return;
                    }
                }
                element.acceptChildren(this);
            }
        }, null);
        return (KtSourceElement) objectRef.element;
    }

    /* JADX WARN: Code duplicated, block: B:231:0x0635  */
    /* JADX WARN: Code duplicated, block: B:251:0x0638 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:51:0x019d  */
    private static final List<KtDiagnostic> mapInapplicableCandidateError(ConeInapplicableCandidateError coneInapplicableCandidateError, FirSession firSession, KtSourceElement ktSourceElement, KtSourceElement ktSourceElement2) {
        KtSourceElement child$default;
        KtSourceElement ktSourceElementCreateOn;
        FirSession firSession2;
        KtSourceElement ktSourceElementInferredIntoEmptyIntersection;
        FirSession firSession3;
        ConeInferenceContext typeContext = TypeComponentsKt.getTypeContext(firSession);
        KtSourceElement ktSourceElementCreateOn$default = createOn$default(FirErrors.INSTANCE.getINAPPLICABLE_CANDIDATE(), ktSourceElement, coneInapplicableCandidateError.getCandidate().getSymbol(), firSession, null, 8, null);
        List<ResolutionDiagnostic> diagnostics = coneInapplicableCandidateError.getCandidate().getDiagnostics();
        ArrayList<ResolutionDiagnostic> arrayList = new ArrayList();
        for (Object obj : diagnostics) {
            if (!ResolutionDiagnosticKt.isSuccess((ResolutionDiagnostic) obj)) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (ResolutionDiagnostic resolutionDiagnostic : arrayList) {
            if (resolutionDiagnostic instanceof VarargArgumentOutsideParentheses) {
                KtDiagnosticFactory0 vararg_outside_parentheses = FirErrors.INSTANCE.getVARARG_OUTSIDE_PARENTHESES();
                KtSourceElement source = ((VarargArgumentOutsideParentheses) resolutionDiagnostic).getArgument().getSource();
                if (source == null) {
                    source = ktSourceElement2;
                }
                ktSourceElementCreateOn = createOn$default(vararg_outside_parentheses, source, firSession, null, 4, null);
            } else if (resolutionDiagnostic instanceof NamedArgumentNotAllowed) {
                NamedArgumentNotAllowed namedArgumentNotAllowed = (NamedArgumentNotAllowed) resolutionDiagnostic;
                ktSourceElementCreateOn = createOn$default(FirErrors.INSTANCE.getNAMED_ARGUMENTS_NOT_ALLOWED(), namedArgumentNotAllowed.getArgument().getSource(), namedArgumentNotAllowed.getForbiddenNamedArgumentsTarget(), firSession, null, 8, null);
            } else if (resolutionDiagnostic instanceof MixingNamedAndPositionArguments) {
                ktSourceElementCreateOn = createOn$default(FirErrors.INSTANCE.getMIXING_NAMED_AND_POSITIONAL_ARGUMENTS(), ((MixingNamedAndPositionArguments) resolutionDiagnostic).getArgument().getSource(), firSession, null, 4, null);
            } else {
                child$default = null;
                if (resolutionDiagnostic instanceof ArgumentTypeMismatch) {
                    if (coneInapplicableCandidateError.getCandidate().getUsedOuterCs() || !((ArgumentTypeMismatch) resolutionDiagnostic).getSystemHadContradiction()) {
                        ArgumentTypeMismatch argumentTypeMismatch = (ArgumentTypeMismatch) resolutionDiagnostic;
                        KtSourceElement source2 = argumentTypeMismatch.getArgument().getSource();
                        if (source2 == null) {
                            source2 = ktSourceElement;
                        }
                        ktSourceElementCreateOn = argumentTypeMismatch(source2, substituteTypeVariableTypes(argumentTypeMismatch.getExpectedType(), coneInapplicableCandidateError.getCandidate(), typeContext), (!(argumentTypeMismatch.getArgument() instanceof FirAnonymousFunctionExpression) || ConeTypeUtilsKt.hasError(FirTypeUtilsKt.getResolvedType(argumentTypeMismatch.getArgument()))) ? substituteTypeVariableTypes(argumentTypeMismatch.getActualType(), coneInapplicableCandidateError.getCandidate(), typeContext) : FirTypeUtilsKt.getResolvedType(argumentTypeMismatch.getArgument()), argumentTypeMismatch.getIsMismatchDueToNullability(), coneInapplicableCandidateError.getCandidate(), argumentTypeMismatch.getAnonymousFunctionIfReturnExpression(), firSession);
                    }
                    if (child$default != null) {
                        arrayList2.add(child$default);
                    }
                } else if (resolutionDiagnostic instanceof UnitReturnTypeLambdaContradictsExpectedType) {
                    KtDiagnosticFactory3<ConeKotlinType, ConeKotlinType, Boolean> argument_type_mismatch = FirErrors.INSTANCE.getARGUMENT_TYPE_MISMATCH();
                    UnitReturnTypeLambdaContradictsExpectedType unitReturnTypeLambdaContradictsExpectedType = (UnitReturnTypeLambdaContradictsExpectedType) resolutionDiagnostic;
                    KtSourceElement sourceForFunctionExpression = unitReturnTypeLambdaContradictsExpectedType.getSourceForFunctionExpression();
                    if (sourceForFunctionExpression == null && (sourceForFunctionExpression = unitReturnTypeLambdaContradictsExpectedType.getLambda().getSource()) == null) {
                        sourceForFunctionExpression = ktSourceElement;
                    }
                    ktSourceElementCreateOn = createOn$default(argument_type_mismatch, sourceForFunctionExpression, substituteTypeVariableTypes(FirTypeUtilsKt.getConeType(unitReturnTypeLambdaContradictsExpectedType.getLambda().getTypeRef()), coneInapplicableCandidateError.getCandidate(), typeContext), substituteTypeVariableTypes(unitReturnTypeLambdaContradictsExpectedType.getWholeLambdaExpectedType(), coneInapplicableCandidateError.getCandidate(), typeContext), Boolean.FALSE, firSession, null, 32, null);
                } else {
                    if ((resolutionDiagnostic instanceof ErrorTypeInArguments) || (resolutionDiagnostic instanceof UnsuccessfulCallableReferenceArgument) || (resolutionDiagnostic instanceof UnsuccessfulCollectionLiteralArgument)) {
                        ktSourceElementCreateOn = null;
                    } else if (resolutionDiagnostic instanceof MultipleContextReceiversApplicableForExtensionReceivers) {
                        ktSourceElementCreateOn = createOn$default(FirErrors.INSTANCE.getAMBIGUOUS_CALL_WITH_IMPLICIT_CONTEXT_RECEIVER(), ktSourceElement2 == 0 ? ktSourceElement : ktSourceElement2, firSession, null, 4, null);
                    } else if (resolutionDiagnostic instanceof NoReceiverAllowed) {
                        ktSourceElementCreateOn = createOn$default(FirErrors.INSTANCE.getNO_RECEIVER_ALLOWED(), ktSourceElement2 == 0 ? ktSourceElement : ktSourceElement2, firSession, null, 4, null);
                    } else if (resolutionDiagnostic instanceof NoContextArgument) {
                        ktSourceElementCreateOn = createOn$default(FirErrors.INSTANCE.getNO_CONTEXT_ARGUMENT(), ktSourceElement2 == 0 ? ktSourceElement : ktSourceElement2, ((NoContextArgument) resolutionDiagnostic).getSymbol(), firSession, null, 8, null);
                    } else if (resolutionDiagnostic instanceof UnsupportedContextualDeclarationCall) {
                        ktSourceElementCreateOn = createOn$default(FirErrors.INSTANCE.getUNSUPPORTED_CONTEXTUAL_DECLARATION_CALL(), ktSourceElement, firSession, null, 4, null);
                    } else if (resolutionDiagnostic instanceof AmbiguousContextArgument) {
                        ktSourceElementCreateOn = createOn$default(FirErrors.INSTANCE.getAMBIGUOUS_CONTEXT_ARGUMENT(), ktSourceElement2 == 0 ? ktSourceElement : ktSourceElement2, ((AmbiguousContextArgument) resolutionDiagnostic).getSymbol(), firSession, null, 8, null);
                    } else if (resolutionDiagnostic instanceof TypeVariableAsExplicitReceiver) {
                        TypeVariableAsExplicitReceiver typeVariableAsExplicitReceiver = (TypeVariableAsExplicitReceiver) resolutionDiagnostic;
                        FirTypeParameter typeParameter = typeVariableAsExplicitReceiver.getTypeParameter();
                        KtDiagnosticFactory2<Name, Name> builder_inference_stub_receiver = FirErrors.INSTANCE.getBUILDER_INFERENCE_STUB_RECEIVER();
                        KtSourceElement source3 = typeVariableAsExplicitReceiver.getExplicitReceiver().getSource();
                        Name name = typeParameter.getSymbol().getName();
                        Name memberDeclarationNameOrNull = FirDeclarationUtilKt.getMemberDeclarationNameOrNull(typeParameter.getSymbol().getContainingDeclarationSymbol());
                        if (memberDeclarationNameOrNull == null) {
                            k2d.a("containingDeclarationSymbol must have been a member declaration");
                            return null;
                        }
                        ktSourceElementCreateOn = createOn$default(builder_inference_stub_receiver, source3, name, memberDeclarationNameOrNull, firSession, null, 16, null);
                    } else if (resolutionDiagnostic instanceof NullForNotNullType) {
                        KtDiagnosticFactory1<ConeKotlinType> null_for_nonnull_type = FirErrors.INSTANCE.getNULL_FOR_NONNULL_TYPE();
                        NullForNotNullType nullForNotNullType = (NullForNotNullType) resolutionDiagnostic;
                        KtSourceElement source4 = nullForNotNullType.getArgument().getSource();
                        if (source4 == null) {
                            source4 = ktSourceElement;
                        }
                        ktSourceElementCreateOn = createOn$default(null_for_nonnull_type, source4, substituteTypeVariableTypes(nullForNotNullType.getExpectedType(), coneInapplicableCandidateError.getCandidate(), typeContext), firSession, null, 8, null);
                    } else if (resolutionDiagnostic instanceof NonVarargSpread) {
                        KtDiagnosticFactory0 non_vararg_spread = FirErrors.INSTANCE.getNON_VARARG_SPREAD();
                        KtSourceElement source5 = ((NonVarargSpread) resolutionDiagnostic).getArgument().getSource();
                        if (source5 != null) {
                            KtSingleValueToken ktSingleValueToken = KtTokens.MUL;
                            ktSingleValueToken.getClass();
                            child$default = FirSourceUtilsKt.getChild$default(source5, (IElementType) ktSingleValueToken, 0, 1, false, 10, (Object) null);
                        }
                        KtSourceElement ktSourceElement3 = child$default;
                        ktSourceElement3.getClass();
                        ktSourceElementCreateOn = createOn$default(non_vararg_spread, ktSourceElement3, firSession, null, 4, null);
                    } else if (resolutionDiagnostic instanceof ArgumentPassedTwice) {
                        firSession3 = firSession;
                        ktSourceElementCreateOn = createOn$default(FirErrors.INSTANCE.getARGUMENT_PASSED_TWICE(), ((ArgumentPassedTwice) resolutionDiagnostic).getArgument().getSource(), firSession3, null, 4, null);
                    } else if (resolutionDiagnostic instanceof TooManyArguments) {
                        TooManyArguments tooManyArguments = (TooManyArguments) resolutionDiagnostic;
                        ktSourceElementInferredIntoEmptyIntersection = unexpectedTrailingLambdaOnNewLineOrNull(tooManyArguments.getArgument(), firSession);
                        if (ktSourceElementInferredIntoEmptyIntersection != null) {
                            ktSourceElementCreateOn = ktSourceElementInferredIntoEmptyIntersection;
                        } else {
                            KtDiagnosticFactory1<FirCallableSymbol<?>> too_many_arguments = FirErrors.INSTANCE.getTOO_MANY_ARGUMENTS();
                            KtSourceElement source6 = tooManyArguments.getArgument().getSource();
                            if (source6 == null) {
                                source6 = ktSourceElement;
                            }
                            firSession2 = firSession;
                            ktSourceElementCreateOn = createOn$default(too_many_arguments, source6, tooManyArguments.getFunction().getSymbol(), firSession2, null, 8, null);
                        }
                    } else if (resolutionDiagnostic instanceof NoValueForParameter) {
                        FirValueParameterSymbol symbol = ((NoValueForParameter) resolutionDiagnostic).getValueParameter().getSymbol();
                        KtDiagnosticFactory1<Name> no_value_for_parameter = FirErrors.INSTANCE.getNO_VALUE_FOR_PARAMETER();
                        KtSourceElement ktSourceElement4 = ktSourceElement2 == 0 ? ktSourceElement : ktSourceElement2;
                        Name nameValueParameterName = FunctionalTypeUtilsKt.valueParameterName(symbol.getResolvedReturnType(), firSession);
                        if (nameValueParameterName == null) {
                            nameValueParameterName = symbol.getName();
                        }
                        ktSourceElementCreateOn = createOn$default(no_value_for_parameter, ktSourceElement4, nameValueParameterName, firSession, null, 8, null);
                    } else if (resolutionDiagnostic instanceof NameNotFound) {
                        KtDiagnosticFactory1<String> named_parameter_not_found = FirErrors.INSTANCE.getNAMED_PARAMETER_NOT_FOUND();
                        NameNotFound nameNotFound = (NameNotFound) resolutionDiagnostic;
                        KtSourceElement source7 = nameNotFound.getArgument().getSource();
                        if (source7 == null) {
                            source7 = ktSourceElement;
                        }
                        ktSourceElementCreateOn = createOn$default(named_parameter_not_found, source7, nameNotFound.getArgument().getName().asString(), firSession, null, 8, null);
                    } else if (resolutionDiagnostic instanceof NameForAmbiguousParameter) {
                        KtDiagnosticFactory0 name_for_ambiguous_parameter = FirErrors.INSTANCE.getNAME_FOR_AMBIGUOUS_PARAMETER();
                        KtSourceElement source8 = ((NameForAmbiguousParameter) resolutionDiagnostic).getArgument().getSource();
                        if (source8 == null) {
                            source8 = ktSourceElement;
                        }
                        firSession3 = firSession;
                        ktSourceElementCreateOn = createOn$default(name_for_ambiguous_parameter, source8, firSession3, null, 4, null);
                    } else if (resolutionDiagnostic instanceof InapplicableNullableReceiver) {
                        ktSourceElementCreateOn = inapplicableNullableReceiver(coneInapplicableCandidateError.getCandidate(), (InapplicableNullableReceiver) resolutionDiagnostic, ktSourceElement, ktSourceElement2, firSession);
                    } else if (resolutionDiagnostic instanceof ManyLambdaExpressionArguments) {
                        ManyLambdaExpressionArguments manyLambdaExpressionArguments = (ManyLambdaExpressionArguments) resolutionDiagnostic;
                        KtSourceElement ktSourceElementUnexpectedTrailingLambdaOnNewLineOrNull = unexpectedTrailingLambdaOnNewLineOrNull(manyLambdaExpressionArguments.getArgument(), firSession);
                        if (ktSourceElementUnexpectedTrailingLambdaOnNewLineOrNull == null) {
                            KtDiagnosticFactory0 many_lambda_expression_arguments = FirErrors.INSTANCE.getMANY_LAMBDA_EXPRESSION_ARGUMENTS();
                            KtSourceElement source9 = manyLambdaExpressionArguments.getArgument().getSource();
                            if (source9 == null) {
                                source9 = ktSourceElement;
                            }
                            ktSourceElementCreateOn = createOn$default(many_lambda_expression_arguments, source9, firSession, null, 4, null);
                        } else {
                            ktSourceElementCreateOn = ktSourceElementUnexpectedTrailingLambdaOnNewLineOrNull;
                        }
                    } else if (resolutionDiagnostic instanceof InfixCallOfNonInfixFunction) {
                        ktSourceElementCreateOn = createOn$default(FirErrors.INSTANCE.getINFIX_MODIFIER_REQUIRED(), ktSourceElement, ((InfixCallOfNonInfixFunction) resolutionDiagnostic).getFunction(), firSession, null, 8, null);
                    } else if (resolutionDiagnostic instanceof OperatorCallOfNonOperatorFunction) {
                        ktSourceElementCreateOn = createOn$default(FirErrors.INSTANCE.getOPERATOR_MODIFIER_REQUIRED(), ktSourceElement, ((OperatorCallOfNonOperatorFunction) resolutionDiagnostic).getFunction(), firSession, null, 8, null);
                    } else if (resolutionDiagnostic instanceof OperatorCallOfConstructor) {
                        firSession2 = firSession;
                        ktSourceElementCreateOn = createOn$default(FirErrors.INSTANCE.getOPERATOR_CALL_ON_CONSTRUCTOR(), ktSourceElement, ((OperatorCallOfConstructor) resolutionDiagnostic).getConstructor().getName().asString(), firSession2, null, 8, null);
                    } else if (resolutionDiagnostic instanceof UnstableSmartCast) {
                        ktSourceElementCreateOn = mapUnstableSmartCast((UnstableSmartCast) resolutionDiagnostic, firSession);
                    } else if (resolutionDiagnostic instanceof DslScopeViolation) {
                        ktSourceElementCreateOn = createOn$default(FirErrors.INSTANCE.getDSL_SCOPE_VIOLATION(), ktSourceElement, ((DslScopeViolation) resolutionDiagnostic).getCalleeSymbol(), firSession, null, 8, null);
                    } else if (resolutionDiagnostic instanceof ReceiverShadowedByContextParameter) {
                        ReceiverShadowedByContextParameter receiverShadowedByContextParameter = (ReceiverShadowedByContextParameter) resolutionDiagnostic;
                        ktSourceElementCreateOn = createOn$default(FirErrors.INSTANCE.getRECEIVER_SHADOWED_BY_CONTEXT_PARAMETER(), ktSourceElement, receiverShadowedByContextParameter.getCalleeSymbol(), Boolean.valueOf(receiverShadowedByContextParameter.getIsDispatchOfMemberExtension()), receiverShadowedByContextParameter.getCompatibleContextParameters(), firSession, null, 32, null);
                    } else if (resolutionDiagnostic instanceof InferenceError) {
                        ktSourceElementCreateOn = mapConstraintSystemError(((InferenceError) resolutionDiagnostic).getConstraintError(), ktSourceElement, ktSourceElement2, firSession, coneInapplicableCandidateError.getCandidate());
                    } else if (resolutionDiagnostic instanceof InferredEmptyIntersectionDiagnostic) {
                        InferredEmptyIntersectionDiagnostic inferredEmptyIntersectionDiagnostic = (InferredEmptyIntersectionDiagnostic) resolutionDiagnostic;
                        ktSourceElementInferredIntoEmptyIntersection = inferredIntoEmptyIntersection(ktSourceElement, inferredEmptyIntersectionDiagnostic.getTypeVariable(), inferredEmptyIntersectionDiagnostic.getIncompatibleTypes(), inferredEmptyIntersectionDiagnostic.getCausingTypes(), inferredEmptyIntersectionDiagnostic.getKind(), inferredEmptyIntersectionDiagnostic.getIsError(), firSession);
                        ktSourceElementCreateOn = ktSourceElementInferredIntoEmptyIntersection;
                    } else if (resolutionDiagnostic instanceof AdaptedCallableReferenceIsUsedWithReflection) {
                        ktSourceElementCreateOn = createOn$default(FirErrors.INSTANCE.getADAPTED_CALLABLE_REFERENCE_AGAINST_REFLECTION_TYPE(), ktSourceElement2, firSession, null, 4, null);
                    } else {
                        if (!(resolutionDiagnostic instanceof TypeParameterAsExpression)) {
                            if (resolutionDiagnostic instanceof AmbiguousInterceptedSymbol) {
                                ktSourceElementCreateOn = createOn$default(FirErrors.INSTANCE.getPLUGIN_AMBIGUOUS_INTERCEPTED_SYMBOL(), ktSourceElement, ((AmbiguousInterceptedSymbol) resolutionDiagnostic).getPluginNames(), firSession, null, 8, null);
                            } else if (resolutionDiagnostic instanceof MissingInnerClassConstructorReceiver) {
                                ktSourceElementCreateOn = createOn$default(FirErrors.INSTANCE.getINNER_CLASS_CONSTRUCTOR_NO_RECEIVER(), ktSourceElement2 == null ? ktSourceElement : ktSourceElement2, ((MissingInnerClassConstructorReceiver) resolutionDiagnostic).getCandidateSymbol(), firSession, null, 8, null);
                            } else if (resolutionDiagnostic instanceof WrongNumberOfTypeArguments) {
                                WrongNumberOfTypeArguments wrongNumberOfTypeArguments = (WrongNumberOfTypeArguments) resolutionDiagnostic;
                                ktSourceElementCreateOn = createOn$default(FirErrors.INSTANCE.getWRONG_NUMBER_OF_TYPE_ARGUMENTS(), ktSourceElement2 == null ? ktSourceElement : ktSourceElement2, Integer.valueOf(wrongNumberOfTypeArguments.getDesiredCount()), wrongNumberOfTypeArguments.getSymbol(), firSession, null, 16, null);
                            } else if (resolutionDiagnostic instanceof InaccessibleOuterClassReceiver) {
                                ktSourceElementCreateOn = createOn$default(FirErrors.INSTANCE.getINACCESSIBLE_OUTER_CLASS_RECEIVER(), ktSourceElement2 == null ? ktSourceElement : ktSourceElement2, ((InaccessibleOuterClassReceiver) resolutionDiagnostic).getSymbol(), firSession, null, 8, null);
                            } else if (resolutionDiagnostic instanceof InaccessibleFromClassHeader) {
                                firSession2 = firSession;
                                ktSourceElementCreateOn = createOn$default(FirErrors.INSTANCE.getINSTANCE_ACCESS_BEFORE_SUPER_CALL(), ktSourceElement2 == null ? ktSourceElement : ktSourceElement2, "<this>", firSession2, null, 8, null);
                            } else if (Intrinsics.areEqual(resolutionDiagnostic, UnsupportedCompanionBlockOrExtensionCall.INSTANCE)) {
                                ktSourceElementCreateOn = createOn(FirErrors.INSTANCE.getUNSUPPORTED_FEATURE(), ktSourceElement2 == null ? ktSourceElement : ktSourceElement2, TuplesKt.to(LanguageFeature.CompanionBlocksAndExtensions, FirLanguageSettingsComponentKt.getLanguageVersionSettings(firSession)), firSession, SourceElementPositioningStrategies.INSTANCE.getREFERENCE_BY_QUALIFIED());
                            } else if (!(coneInapplicableCandidateError.getCandidate().getSymbol() instanceof FirSyntheticFunctionSymbol)) {
                                ktSourceElementCreateOn = ktSourceElementCreateOn$default;
                            }
                        }
                        ktSourceElementCreateOn = null;
                    }
                    child$default = ktSourceElementCreateOn;
                    if (child$default != null) {
                        arrayList2.add(child$default);
                    }
                }
            }
            child$default = ktSourceElementCreateOn;
            if (child$default != null) {
                arrayList2.add(child$default);
            }
        }
        List<KtDiagnostic> listDistinct = CollectionsKt.distinct(arrayList2);
        if (listDistinct.size() <= 1) {
            return listDistinct;
        }
        ArrayList arrayList3 = new ArrayList();
        for (Object obj2 : listDistinct) {
            if (!Intrinsics.areEqual((KtDiagnostic) obj2, ktSourceElementCreateOn$default)) {
                arrayList3.add(obj2);
            }
        }
        return arrayList3;
    }

    private static final KtDiagnostic mapOtherDiagnostic(ConeDiagnostic coneDiagnostic, KtSourceElement ktSourceElement, FirValueParameter firValueParameter, KtSourceElement ktSourceElement2, FirSession firSession) {
        KtDiagnosticWithParameters1 ktDiagnosticWithParameters1CreateOn$default;
        Name name;
        if (coneDiagnostic instanceof ConeUnresolvedReferenceError) {
            return createOn$default(FirErrors.INSTANCE.getUNRESOLVED_REFERENCE(), ktSourceElement, ((ConeUnresolvedReferenceError) coneDiagnostic).getName().asString(), null, firSession, null, 16, null);
        }
        KtSourceElement ktSourceElement3 = ktSourceElement;
        if (coneDiagnostic instanceof ConeUnresolvedSymbolError) {
            return createOn$default(FirErrors.INSTANCE.getUNRESOLVED_REFERENCE(), ktSourceElement3, ((ConeUnresolvedSymbolError) coneDiagnostic).getClassId().asString(), null, firSession, null, 16, null);
        }
        if (coneDiagnostic instanceof ConeUnresolvedNameError) {
            ConeUnresolvedNameError coneUnresolvedNameError = (ConeUnresolvedNameError) coneDiagnostic;
            return createOn$default(FirErrors.INSTANCE.getUNRESOLVED_REFERENCE(), ktSourceElement3, coneUnresolvedNameError.getName().asString(), coneUnresolvedNameError.getOperatorToken(), firSession, null, 16, null);
        }
        if (coneDiagnostic instanceof ConeUnresolvedTypeQualifierError) {
            if (!Intrinsics.areEqual(ktSourceElement3 != null ? ktSourceElement3.getKind() : null, KtRealSourceElementKind.INSTANCE)) {
                return createOn$default(FirErrors.INSTANCE.getUNRESOLVED_REFERENCE(), ktSourceElement3, ((ConeUnresolvedTypeQualifierError) coneDiagnostic).getQualifier(), null, firSession, null, 16, null);
            }
            FirQualifierPart firQualifierPart = (FirQualifierPart) CollectionsKt.last(((ConeUnresolvedTypeQualifierError) coneDiagnostic).getQualifiers());
            return createOn$default(FirErrors.INSTANCE.getUNRESOLVED_REFERENCE(), firQualifierPart.getSource(), firQualifierPart.getName().asString(), null, firSession, null, 16, null);
        }
        if (coneDiagnostic instanceof ConeFunctionCallExpectedError) {
            ConeFunctionCallExpectedError coneFunctionCallExpectedError = (ConeFunctionCallExpectedError) coneDiagnostic;
            return createOn$default(FirErrors.INSTANCE.getFUNCTION_CALL_EXPECTED(), ktSourceElement3, coneFunctionCallExpectedError.getName().asString(), Boolean.valueOf(coneFunctionCallExpectedError.getHasValueParameters()), firSession, null, 16, null);
        }
        if (coneDiagnostic instanceof ConeFunctionExpectedError) {
            ConeFunctionExpectedError coneFunctionExpectedError = (ConeFunctionExpectedError) coneDiagnostic;
            return createOn$default(FirErrors.INSTANCE.getFUNCTION_EXPECTED(), ktSourceElement3, coneFunctionExpectedError.getExpression(), coneFunctionExpectedError.getType(), firSession, null, 16, null);
        }
        if (coneDiagnostic instanceof ConeNoConstructorError) {
            KtDiagnosticFactory0 no_constructor = FirErrors.INSTANCE.getNO_CONSTRUCTOR();
            if (ktSourceElement2 != null) {
                ktSourceElement3 = ktSourceElement2;
            }
            return createOn$default(no_constructor, ktSourceElement3, firSession, null, 4, null);
        }
        if (coneDiagnostic instanceof ConeNoImplicitDefaultConstructorOnExpectClass) {
            KtDiagnosticFactory0 no_implicit_default_constructor_on_expect_class = FirErrors.INSTANCE.getNO_IMPLICIT_DEFAULT_CONSTRUCTOR_ON_EXPECT_CLASS();
            if (ktSourceElement2 != null) {
                ktSourceElement3 = ktSourceElement2;
            }
            return createOn$default(no_implicit_default_constructor_on_expect_class, ktSourceElement3, firSession, null, 4, null);
        }
        if (coneDiagnostic instanceof ConeResolutionToClassifierError) {
            ConeResolutionToClassifierError coneResolutionToClassifierError = (ConeResolutionToClassifierError) coneDiagnostic;
            int i = WhenMappings.$EnumSwitchMapping$0[coneResolutionToClassifierError.getCandidateSymbol().getClassKind().ordinal()];
            if (i == 1) {
                return createOn$default(FirErrors.INSTANCE.getINTERFACE_AS_FUNCTION(), ktSourceElement3, coneResolutionToClassifierError.getCandidateSymbol(), firSession, null, 8, null);
            }
            if (i != 2) {
                return createOn$default(FirErrors.INSTANCE.getRESOLUTION_TO_CLASSIFIER(), ktSourceElement3, coneResolutionToClassifierError.getCandidateSymbol(), firSession, null, 8, null);
            }
            if (coneResolutionToClassifierError.getCandidateSymbol().getRawStatus().isInner()) {
                return createOn$default(FirErrors.INSTANCE.getINNER_CLASS_CONSTRUCTOR_NO_RECEIVER(), ktSourceElement3, coneResolutionToClassifierError.getCandidateSymbol(), firSession, null, 8, null);
            }
            return coneResolutionToClassifierError.getCandidateSymbol().getRawStatus().isExpect() ? createOn$default(FirErrors.INSTANCE.getEXPECT_CLASS_AS_FUNCTION(), ktSourceElement3, coneResolutionToClassifierError.getCandidateSymbol(), firSession, null, 8, null) : createOn$default(FirErrors.INSTANCE.getRESOLUTION_TO_CLASSIFIER(), ktSourceElement3, coneResolutionToClassifierError.getCandidateSymbol(), firSession, null, 8, null);
        }
        if (coneDiagnostic instanceof ConeHiddenCandidateError) {
            KtDiagnosticFactory2<String, String> unresolved_reference = FirErrors.INSTANCE.getUNRESOLVED_REFERENCE();
            KtSourceElementKind candidateSymbol = ((ConeHiddenCandidateError) coneDiagnostic).getCandidateSymbol();
            KtSourceElementKind ktSourceElementKind = candidateSymbol instanceof FirCallableSymbol ? (FirCallableSymbol) candidateSymbol : null;
            if (ktSourceElementKind == null || (name = ktSourceElementKind.getName()) == null) {
                name = SpecialNames.NO_NAME_PROVIDED;
            }
            return createOn$default(unresolved_reference, ktSourceElement3, name.asString(), null, firSession, null, 16, null);
        }
        if (coneDiagnostic instanceof ConeTypeVisibilityError) {
            ConeTypeVisibilityError coneTypeVisibilityError = (ConeTypeVisibilityError) coneDiagnostic;
            return toInvisibleReferenceDiagnostic(coneTypeVisibilityError.getSymbol(), ((FirQualifierPart) CollectionsKt.last(coneTypeVisibilityError.getSmallestUnresolvablePrefix())).getSource(), firSession);
        }
        if (coneDiagnostic instanceof ConeVisibilityError) {
            return toInvisibleReferenceDiagnostic(((ConeVisibilityError) coneDiagnostic).getSymbol(), ktSourceElement3, firSession);
        }
        if (coneDiagnostic instanceof ConeInapplicableWrongReceiver) {
            ConeInapplicableWrongReceiver coneInapplicableWrongReceiver = (ConeInapplicableWrongReceiver) coneDiagnostic;
            ResolutionDiagnostic primaryDiagnostic = coneInapplicableWrongReceiver.getPrimaryDiagnostic();
            return primaryDiagnostic instanceof DynamicReceiverExpectedButWasNonDynamic ? createOn$default(FirErrors.INSTANCE.getDYNAMIC_RECEIVER_EXPECTED_BUT_WAS_NON_DYNAMIC(), ktSourceElement3, ((DynamicReceiverExpectedButWasNonDynamic) primaryDiagnostic).getActualType(), firSession, null, 8, null) : createOn$default(FirErrors.INSTANCE.getUNRESOLVED_REFERENCE_WRONG_RECEIVER(), ktSourceElement3, coneInapplicableWrongReceiver.getCandidateSymbols(), firSession, null, 8, null);
        }
        if (coneDiagnostic instanceof ConeNoCompanionObject) {
            KtDiagnosticFactory1<FirClassLikeSymbol<?>> no_companion_object = FirErrors.INSTANCE.getNO_COMPANION_OBJECT();
            FirBasedSymbol<?> candidateSymbol2 = ((ConeNoCompanionObject) coneDiagnostic).getCandidateSymbol();
            candidateSymbol2.getClass();
            return createOn$default(no_companion_object, ktSourceElement3, (FirClassLikeSymbol) candidateSymbol2, firSession, null, 8, null);
        }
        if (coneDiagnostic instanceof ConeOperatorAmbiguityError) {
            return createOn$default(FirErrors.INSTANCE.getASSIGN_OPERATOR_AMBIGUITY(), ktSourceElement3, ((ConeOperatorAmbiguityError) coneDiagnostic).getCandidateSymbols(), firSession, null, 8, null);
        }
        if (coneDiagnostic instanceof ConeVariableExpectedError) {
            return createOn$default(FirErrors.INSTANCE.getVARIABLE_EXPECTED(), ktSourceElement3, firSession, null, 4, null);
        }
        if (coneDiagnostic instanceof ConeUnexpectedTypeArgumentsError) {
            KtDiagnosticFactory1<String> type_arguments_not_allowed = FirErrors.INSTANCE.getTYPE_ARGUMENTS_NOT_ALLOWED();
            KtSourceElement source = ((ConeUnexpectedTypeArgumentsError) coneDiagnostic).getSource();
            if (source != null) {
                ktSourceElement3 = source;
            }
            return createOn$default(type_arguments_not_allowed, ktSourceElement3, "for type parameters", firSession, null, 8, null);
        }
        if (coneDiagnostic instanceof ConeIllegalAnnotationError) {
            return createOn$default(FirErrors.INSTANCE.getNOT_AN_ANNOTATION_CLASS(), ktSourceElement3, ((ConeIllegalAnnotationError) coneDiagnostic).getName().asString(), firSession, null, 8, null);
        }
        if (coneDiagnostic instanceof ConePlaceholderProjectionInQualifierResolution) {
            return createOn$default(FirErrors.INSTANCE.getPLACEHOLDER_PROJECTION_IN_QUALIFIER(), ktSourceElement3, firSession, null, 4, null);
        }
        if (coneDiagnostic instanceof ConeWrongNumberOfTypeArgumentsError) {
            ConeWrongNumberOfTypeArgumentsError coneWrongNumberOfTypeArgumentsError = (ConeWrongNumberOfTypeArgumentsError) coneDiagnostic;
            return createOn$default(FirErrors.INSTANCE.getWRONG_NUMBER_OF_TYPE_ARGUMENTS(), coneWrongNumberOfTypeArgumentsError.getSource(), Integer.valueOf(coneWrongNumberOfTypeArgumentsError.getDesiredCount()), coneWrongNumberOfTypeArgumentsError.getSymbol(), firSession, null, 16, null);
        }
        if (coneDiagnostic instanceof ConeTypeArgumentsForOuterClassWhenNestedReferencedError) {
            return createOn$default(FirErrors.INSTANCE.getTYPE_ARGUMENTS_FOR_OUTER_CLASS_WHEN_NESTED_REFERENCED(), ((ConeTypeArgumentsForOuterClassWhenNestedReferencedError) coneDiagnostic).getSource(), firSession, null, 4, null);
        }
        if (coneDiagnostic instanceof ConeNestedClassAccessedViaInstanceReference) {
            ConeNestedClassAccessedViaInstanceReference coneNestedClassAccessedViaInstanceReference = (ConeNestedClassAccessedViaInstanceReference) coneDiagnostic;
            return createOn$default(FirErrors.INSTANCE.getNESTED_CLASS_ACCESSED_VIA_INSTANCE_REFERENCE(), coneNestedClassAccessedViaInstanceReference.getSource(), coneNestedClassAccessedViaInstanceReference.getSymbol(), firSession, null, 8, null);
        }
        if (coneDiagnostic instanceof ConeOuterClassArgumentsRequired) {
            KtDiagnosticFactory1<FirClassLikeSymbol<?>> outer_class_arguments_required = FirErrors.INSTANCE.getOUTER_CLASS_ARGUMENTS_REQUIRED();
            if (ktSourceElement2 != null) {
                ktSourceElement3 = ktSourceElement2;
            }
            return createOn$default(outer_class_arguments_required, ktSourceElement3, ((ConeOuterClassArgumentsRequired) coneDiagnostic).getSymbol(), firSession, null, 8, null);
        }
        if (coneDiagnostic instanceof ConeNoTypeArgumentsOnRhsError) {
            KtDiagnosticFactory2<Integer, FirClassLikeSymbol<?>> no_type_arguments_on_rhs = FirErrors.INSTANCE.getNO_TYPE_ARGUMENTS_ON_RHS();
            if (ktSourceElement2 != null) {
                ktSourceElement3 = ktSourceElement2;
            }
            ConeNoTypeArgumentsOnRhsError coneNoTypeArgumentsOnRhsError = (ConeNoTypeArgumentsOnRhsError) coneDiagnostic;
            return createOn$default(no_type_arguments_on_rhs, ktSourceElement3, Integer.valueOf(coneNoTypeArgumentsOnRhsError.getDesiredCount()), coneNoTypeArgumentsOnRhsError.getSymbol(), firSession, null, 16, null);
        }
        if (coneDiagnostic instanceof ConeSyntaxDiagnostic) {
            KtDiagnosticFactory1<String> syntax = FirSyntaxErrors.INSTANCE.getSYNTAX();
            if (ktSourceElement2 != null) {
                ktSourceElement3 = ktSourceElement2;
            }
            return createOn$default(syntax, ktSourceElement3, ((ConeSyntaxDiagnostic) coneDiagnostic).getReason(), firSession, null, 8, null);
        }
        if (coneDiagnostic instanceof ConeSimpleDiagnostic) {
            KtSourceElementKind kind = ktSourceElement3 != null ? ktSourceElement3.getKind() : null;
            KtFakeSourceElementKind ktFakeSourceElementKind = kind instanceof KtFakeSourceElementKind ? (KtFakeSourceElementKind) kind : null;
            if (ktFakeSourceElementKind != null && mapOtherDiagnostic$shouldIgnoreSimpleDiagnostic(ktFakeSourceElementKind)) {
                return null;
            }
            if (Intrinsics.areEqual(firValueParameter != null ? firValueParameter.getName() : null, SpecialNames.NO_NAME_PROVIDED) && ((ConeSimpleDiagnostic) coneDiagnostic).getKind() == DiagnosticKind.ValueParameterWithNoTypeAnnotation) {
                return null;
            }
            KtDiagnosticFactory0 factory = getFactory((ConeSimpleDiagnostic) coneDiagnostic, ktSourceElement3);
            if (ktSourceElement2 != null) {
                ktSourceElement3 = ktSourceElement2;
            }
            return createOn$default(factory, ktSourceElement3, firSession, null, 4, null);
        }
        if (coneDiagnostic instanceof ConeDestructuringDeclarationsOnTopLevel) {
            return null;
        }
        if (coneDiagnostic instanceof ConeCannotInferTypeParameterType) {
            return createOn$default(FirErrors.INSTANCE.getCANNOT_INFER_PARAMETER_TYPE(), ktSourceElement3, ((ConeCannotInferTypeParameterType) coneDiagnostic).getTypeParameter(), firSession, null, 8, null);
        }
        if (coneDiagnostic instanceof ConeCannotInferValueParameterType) {
            ConeCannotInferValueParameterType coneCannotInferValueParameterType = (ConeCannotInferValueParameterType) coneDiagnostic;
            if (coneCannotInferValueParameterType.getIsTopLevelLambda()) {
                return createOn$default(FirErrors.INSTANCE.getVALUE_PARAMETER_WITHOUT_EXPLICIT_TYPE(), ktSourceElement3, firSession, null, 4, null);
            }
            if (Intrinsics.areEqual(ktSourceElement3 != null ? ktSourceElement3.getElementType() : null, KtNodeTypes.THIS_EXPRESSION)) {
                return createOn$default(FirErrors.INSTANCE.getCANNOT_INFER_RECEIVER_PARAMETER_TYPE(), ktSourceElement3, firSession, null, 4, null);
            }
            FirValueParameterSymbol valueParameter = coneCannotInferValueParameterType.getValueParameter();
            if (valueParameter == null) {
                valueParameter = firValueParameter != null ? firValueParameter.getSymbol() : null;
            }
            if (valueParameter != null) {
                FirValueParameterSymbol firValueParameterSymbol = !valueParameter.getName().isSpecial() ? valueParameter : null;
                if (firValueParameterSymbol != null && (ktDiagnosticWithParameters1CreateOn$default = createOn$default(FirErrors.INSTANCE.getCANNOT_INFER_VALUE_PARAMETER_TYPE(), ktSourceElement3, firValueParameterSymbol, firSession, null, 8, null)) != null) {
                    return ktDiagnosticWithParameters1CreateOn$default;
                }
            }
            return createOn$default(FirErrors.INSTANCE.getCANNOT_INFER_IT_PARAMETER_TYPE(), ktSourceElement3, firSession, null, 4, null);
        }
        if (coneDiagnostic instanceof ConeCannotInferReceiverParameterType) {
            return createOn$default(FirErrors.INSTANCE.getCANNOT_INFER_RECEIVER_PARAMETER_TYPE(), ktSourceElement3, firSession, null, 4, null);
        }
        if (coneDiagnostic instanceof ConeTypeVariableTypeIsNotInferred) {
            KtDiagnosticFactory0 inference_error = FirErrors.INSTANCE.getINFERENCE_ERROR();
            if (ktSourceElement2 != null) {
                ktSourceElement3 = ktSourceElement2;
            }
            return createOn$default(inference_error, ktSourceElement3, firSession, null, 4, null);
        }
        if (coneDiagnostic instanceof ConeInstanceAccessBeforeSuperCall) {
            return createOn$default(FirErrors.INSTANCE.getINSTANCE_ACCESS_BEFORE_SUPER_CALL(), ktSourceElement3, ((ConeInstanceAccessBeforeSuperCall) coneDiagnostic).getTarget(), firSession, null, 8, null);
        }
        if (coneDiagnostic instanceof ConeInaccessibleOuterClass) {
            return createOn$default(FirErrors.INSTANCE.getINACCESSIBLE_OUTER_CLASS_RECEIVER(), ktSourceElement3, ((ConeInaccessibleOuterClass) coneDiagnostic).getSymbol(), firSession, null, 8, null);
        }
        if ((coneDiagnostic instanceof ConeUnreportedDuplicateDiagnostic) || (coneDiagnostic instanceof ConeIntermediateDiagnostic)) {
            return null;
        }
        if (coneDiagnostic instanceof ConeContractDescriptionError) {
            return createOn$default(FirErrors.INSTANCE.getERROR_IN_CONTRACT_DESCRIPTION(), ktSourceElement3, coneDiagnostic.getReason(), firSession, null, 8, null);
        }
        if (coneDiagnostic instanceof ConeTypeParameterSupertype) {
            return createOn$default(FirErrors.INSTANCE.getSUPERTYPE_NOT_A_CLASS_OR_INTERFACE(), ktSourceElement3, ((ConeTypeParameterSupertype) coneDiagnostic).getReason(), firSession, null, 8, null);
        }
        if ((coneDiagnostic instanceof ConeTypeParameterInQualifiedAccess) || (coneDiagnostic instanceof ConeNotAnnotationContainer)) {
            return null;
        }
        if (coneDiagnostic instanceof ConeImportFromSingleton) {
            return createOn$default(FirErrors.INSTANCE.getCANNOT_ALL_UNDER_IMPORT_FROM_SINGLETON(), ktSourceElement3, ((ConeImportFromSingleton) coneDiagnostic).getName(), firSession, null, 8, null);
        }
        if (coneDiagnostic instanceof ConeUnsupported) {
            KtDiagnosticFactory1<String> unsupported = FirErrors.INSTANCE.getUNSUPPORTED();
            ConeUnsupported coneUnsupported = (ConeUnsupported) coneDiagnostic;
            KtSourceElement source2 = coneUnsupported.getSource();
            if (source2 != null) {
                ktSourceElement3 = source2;
            }
            return createOn$default(unsupported, ktSourceElement3, coneUnsupported.getReason(), firSession, null, 8, null);
        }
        if (coneDiagnostic instanceof ConeLocalVariableNoTypeOrInitializer) {
            if (((ConeLocalVariableNoTypeOrInitializer) coneDiagnostic).getVariable().getSymbol() instanceof FirLocalPropertySymbol) {
                return createOn$default(FirErrors.INSTANCE.getVARIABLE_WITH_NO_TYPE_NO_INITIALIZER(), ktSourceElement3, firSession, null, 4, null);
            }
            return null;
        }
        if (coneDiagnostic instanceof ConeForbiddenIntersection) {
            return null;
        }
        if (coneDiagnostic instanceof ConeUnderscoreIsReserved) {
            return createOn$default(FirErrors.INSTANCE.getUNDERSCORE_IS_RESERVED(), ((ConeUnderscoreIsReserved) coneDiagnostic).getSource(), firSession, null, 4, null);
        }
        if (coneDiagnostic instanceof ConeAmbiguousSuper) {
            return createOn$default(FirErrors.INSTANCE.getAMBIGUOUS_SUPER(), ktSourceElement3, ((ConeAmbiguousSuper) coneDiagnostic).getCandidateTypes(), firSession, null, 8, null);
        }
        if (coneDiagnostic instanceof ConeUnresolvedParentInImport) {
            return null;
        }
        if (coneDiagnostic instanceof ConeAmbiguousAlteredAssign) {
            return createOn$default(FirErrors.INSTANCE.getAMBIGUOUS_ALTERED_ASSIGN(), ktSourceElement3, ((ConeAmbiguousAlteredAssign) coneDiagnostic).getAltererNames(), firSession, null, 8, null);
        }
        if (coneDiagnostic instanceof ConeAmbiguouslyResolvedAnnotationFromPlugin) {
            ConeAmbiguouslyResolvedAnnotationFromPlugin coneAmbiguouslyResolvedAnnotationFromPlugin = (ConeAmbiguouslyResolvedAnnotationFromPlugin) coneDiagnostic;
            return createOn$default(FirErrors.INSTANCE.getCOMPILER_REQUIRED_ANNOTATION_AMBIGUITY(), ktSourceElement3, coneAmbiguouslyResolvedAnnotationFromPlugin.getTypeFromCompilerPhase(), coneAmbiguouslyResolvedAnnotationFromPlugin.getTypeFromTypesPhase(), firSession, null, 16, null);
        }
        if (coneDiagnostic instanceof ConeAmbiguouslyResolvedAnnotationArgument) {
            ConeAmbiguouslyResolvedAnnotationArgument coneAmbiguouslyResolvedAnnotationArgument = (ConeAmbiguouslyResolvedAnnotationArgument) coneDiagnostic;
            return createOn$default(FirErrors.INSTANCE.getAMBIGUOUS_ANNOTATION_ARGUMENT(), ktSourceElement3, CollectionsKt.listOfNotNull(new FirBasedSymbol[]{coneAmbiguouslyResolvedAnnotationArgument.getSymbolFromCompilerPhase(), coneAmbiguouslyResolvedAnnotationArgument.getSymbolFromAnnotationArgumentsPhase()}), firSession, null, 8, null);
        }
        if (coneDiagnostic instanceof ConeAmbiguousFunctionTypeKinds) {
            return createOn$default(FirErrors.INSTANCE.getAMBIGUOUS_FUNCTION_TYPE_KIND(), ktSourceElement3, ((ConeAmbiguousFunctionTypeKinds) coneDiagnostic).getKinds(), firSession, null, 8, null);
        }
        if (coneDiagnostic instanceof ConeUnsupportedClassLiteralsWithEmptyLhs) {
            return createOn$default(FirErrors.INSTANCE.getUNSUPPORTED_CLASS_LITERALS_WITH_EMPTY_LHS(), ktSourceElement3, firSession, null, 4, null);
        }
        if (coneDiagnostic instanceof ConeMultipleLabelsAreForbidden) {
            return createOn$default(FirErrors.INSTANCE.getMULTIPLE_LABELS_ARE_FORBIDDEN(), ((ConeMultipleLabelsAreForbidden) coneDiagnostic).getSource(), firSession, null, 4, null);
        }
        if (coneDiagnostic instanceof ConeTypeMismatch) {
            ConeTypeMismatch coneTypeMismatch = (ConeTypeMismatch) coneDiagnostic;
            return createOn$default(FirErrors.INSTANCE.getTYPE_MISMATCH(), ktSourceElement3, coneTypeMismatch.getLowerType(), coneTypeMismatch.getUpperType(), Boolean.FALSE, firSession, null, 32, null);
        }
        if (coneDiagnostic instanceof ConeDynamicUnsupported) {
            return createOn$default(FirErrors.INSTANCE.getUNSUPPORTED(), ktSourceElement3, FirDynamicUnsupportedChecker.MESSAGE, firSession, null, 8, null);
        }
        if (coneDiagnostic instanceof ConeContextParameterWithDefaultValue) {
            return createOn$default(FirErrors.INSTANCE.getCONTEXT_PARAMETER_WITH_DEFAULT(), ktSourceElement3, firSession, null, 4, null);
        }
        if (coneDiagnostic instanceof ConeCyclicTypeBound) {
            return null;
        }
        if (coneDiagnostic instanceof ConeCollectionLiteralAmbiguity) {
            return createOn$default(FirErrors.INSTANCE.getAMBIGUOUS_COLLECTION_LITERAL(), ktSourceElement3, ((ConeCollectionLiteralAmbiguity) coneDiagnostic).getCandidatesWithOf(), firSession, null, 8, null);
        }
        z01.a("Unsupported diagnostic type: ", coneDiagnostic.getClass());
        return null;
    }

    private static final boolean mapOtherDiagnostic$shouldIgnoreSimpleDiagnostic(KtFakeSourceElementKind ktFakeSourceElementKind) {
        return Intrinsics.areEqual(ktFakeSourceElementKind, KtFakeSourceElementKind.DelegatingConstructorCall.INSTANCE) || Intrinsics.areEqual(ktFakeSourceElementKind, KtFakeSourceElementKind.ErrorTypeRef.INSTANCE);
    }

    /* JADX WARN: Code duplicated, block: B:55:0x00e9  */
    /* JADX WARN: Code duplicated, block: B:56:0x00eb  */
    private static final List<KtDiagnostic> mapSystemHasContradictionError(ConeConstraintSystemHasContradiction coneConstraintSystemHasContradiction, FirSession firSession, KtSourceElement ktSourceElement, KtSourceElement ktSourceElement2) {
        Object objCreateOn$default;
        KtSourceElement ktSourceElement3;
        List<ConstraintSystemError> errors = coneConstraintSystemHasContradiction.getCandidate().getErrors();
        List<KtDiagnostic> arrayList = new ArrayList<>();
        Iterator<T> it = errors.iterator();
        while (it.hasNext()) {
            KtDiagnostic ktDiagnosticMapConstraintSystemError = mapConstraintSystemError((ConstraintSystemError) it.next(), ktSourceElement, ktSourceElement2, firSession, coneConstraintSystemHasContradiction.getCandidate());
            if (ktDiagnosticMapConstraintSystemError != null) {
                arrayList.add(ktDiagnosticMapConstraintSystemError);
            }
        }
        if (arrayList.isEmpty()) {
            boolean z = errors instanceof Collection;
            Object obj = null;
            if (!z || !errors.isEmpty()) {
                Iterator<T> it2 = errors.iterator();
                while (it2.hasNext()) {
                    NotEnoughInformationForTypeParameter notEnoughInformationForTypeParameter = (ConstraintSystemError) it2.next();
                    if (!(notEnoughInformationForTypeParameter instanceof ConstrainingTypeIsError)) {
                        if (notEnoughInformationForTypeParameter instanceof NotEnoughInformationForTypeParameter) {
                            NotEnoughInformationForTypeParameter notEnoughInformationForTypeParameter2 = notEnoughInformationForTypeParameter;
                            if (!(notEnoughInformationForTypeParameter2.getTypeVariable() instanceof ConeTypeParameterBasedTypeVariable)) {
                                Object resolvedAtom = notEnoughInformationForTypeParameter2.getResolvedAtom();
                                FirAnonymousFunction firAnonymousFunction = resolvedAtom instanceof FirAnonymousFunction ? (FirAnonymousFunction) resolvedAtom : null;
                                if (firAnonymousFunction == null || !mapSystemHasContradictionError$lambda$1$0$containsErrorType(firAnonymousFunction)) {
                                }
                            }
                        }
                    }
                    return CollectionsKt.emptyList();
                }
            }
            Iterator<T> it3 = errors.iterator();
            while (it3.hasNext()) {
                NewConstraintError newConstraintError = (ConstraintSystemError) it3.next();
                if (newConstraintError instanceof NewConstraintError) {
                    NewConstraintError newConstraintError2 = newConstraintError;
                    if (!(newConstraintError2.getPosition().getFrom() instanceof FixVariableConstraintPosition) || (z && errors.isEmpty())) {
                        ConeInferenceContext typeContext = TypeComponentsKt.getTypeContext(firSession);
                        KtDiagnosticFactory3<ConeKotlinType, ConeKotlinType, Boolean> type_mismatch = FirErrors.INSTANCE.getTYPE_MISMATCH();
                        if (ktSourceElement2 == null) {
                            ktSourceElement3 = ktSourceElement;
                        } else {
                            ktSourceElement3 = ktSourceElement2;
                        }
                        objCreateOn$default = createOn$default(type_mismatch, ktSourceElement3, substituteTypeVariableTypes(getUpperConeType(newConstraintError2), coneConstraintSystemHasContradiction.getCandidate(), typeContext), substituteTypeVariableTypes(getLowerConeType(newConstraintError2), coneConstraintSystemHasContradiction.getCandidate(), typeContext), Boolean.FALSE, firSession, null, 32, null);
                    } else {
                        Iterator<T> it4 = errors.iterator();
                        while (true) {
                            if (it4.hasNext()) {
                                NewConstraintError newConstraintError3 = (ConstraintSystemError) it4.next();
                                if ((newConstraintError3 instanceof NewConstraintError) && !(newConstraintError3.getPosition().getFrom() instanceof FixVariableConstraintPosition)) {
                                    objCreateOn$default = null;
                                }
                            } else {
                                ConeInferenceContext typeContext2 = TypeComponentsKt.getTypeContext(firSession);
                                KtDiagnosticFactory3<ConeKotlinType, ConeKotlinType, Boolean> type_mismatch2 = FirErrors.INSTANCE.getTYPE_MISMATCH();
                                if (ktSourceElement2 == null) {
                                    ktSourceElement3 = ktSourceElement;
                                } else {
                                    ktSourceElement3 = ktSourceElement2;
                                }
                                objCreateOn$default = createOn$default(type_mismatch2, ktSourceElement3, substituteTypeVariableTypes(getUpperConeType(newConstraintError2), coneConstraintSystemHasContradiction.getCandidate(), typeContext2), substituteTypeVariableTypes(getLowerConeType(newConstraintError2), coneConstraintSystemHasContradiction.getCandidate(), typeContext2), Boolean.FALSE, firSession, null, 32, null);
                            }
                        }
                    }
                } else {
                    objCreateOn$default = createOn$default(FirErrors.INSTANCE.getNEW_INFERENCE_ERROR(), ktSourceElement2 == null ? ktSourceElement : ktSourceElement2, "Inference error: " + Reflection.getOrCreateKotlinClass(newConstraintError.getClass()).getSimpleName(), firSession, null, 8, null);
                }
                if (objCreateOn$default != null) {
                    obj = objCreateOn$default;
                    break;
                }
            }
            arrayList = CollectionsKt.listOfNotNull(obj);
        }
        return arrayList;
    }

    private static final boolean mapSystemHasContradictionError$lambda$1$0$containsErrorType(FirAnonymousFunction firAnonymousFunction) {
        if ((firAnonymousFunction.getReturnTypeRef() instanceof FirErrorTypeRef) || (InferenceUtilsKt.getReceiverType(firAnonymousFunction) instanceof ConeErrorType)) {
            return true;
        }
        List<FirValueParameter> valueParameters = firAnonymousFunction.getValueParameters();
        if (!(valueParameters instanceof Collection) || !valueParameters.isEmpty()) {
            Iterator<T> it = valueParameters.iterator();
            while (it.hasNext()) {
                if (((FirValueParameter) it.next()).getReturnTypeRef() instanceof FirErrorTypeRef) {
                    return true;
                }
            }
        }
        List<FirValueParameter> contextParameters = firAnonymousFunction.getContextParameters();
        if ((contextParameters instanceof Collection) && contextParameters.isEmpty()) {
            return false;
        }
        Iterator<T> it2 = contextParameters.iterator();
        while (it2.hasNext()) {
            if (((FirValueParameter) it2.next()).getReturnTypeRef() instanceof FirErrorTypeRef) {
                return true;
            }
        }
        return false;
    }

    private static final KtDiagnostic mapUnstableSmartCast(UnstableSmartCast unstableSmartCast, FirSession firSession) {
        return createOn$default(unstableSmartCast.getIsImplicitInvokeReceiver() ? FirErrors.INSTANCE.getSMARTCAST_IMPOSSIBLE_ON_IMPLICIT_INVOKE_RECEIVER() : FirErrors.INSTANCE.getSMARTCAST_IMPOSSIBLE(), unstableSmartCast.getArgument().getSource(), unstableSmartCast.getTargetType(), unstableSmartCast.getArgument(), unstableSmartCast.getArgument().getSmartcastStability().getDescription(), Boolean.valueOf(unstableSmartCast.getIsCastToNotNull()), firSession, null, 64, null);
    }

    private static final ConeKotlinType substituteTypeVariableTypes(ConeKotlinType coneKotlinType, AbstractCallCandidate<?> abstractCallCandidate, ConeTypeContext coneTypeContext) {
        Map fixedTypeVariables = abstractCallCandidate.getSystem().asReadOnlyStorage().getFixedTypeVariables();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (Map.Entry entry : fixedTypeVariables.entrySet()) {
            if (!(((KotlinTypeMarker) entry.getValue()) instanceof ConeErrorType)) {
                linkedHashMap.put(entry.getKey(), entry.getValue());
            }
        }
        return TypeVariableTypeRemovingSubstitutorKt.removeTypeVariableTypes$default(((ConeSubstitutor) coneTypeContext.typeSubstitutorByTypeConstructor(linkedHashMap)).substituteOrSelf(coneKotlinType), coneTypeContext, TypeVariableReplacement.ErrorType, null, 4, null);
    }

    private static final DiagnosticBaseContext toDiagnosticContext(FirSession firSession) {
        return new SessionWrapper(firSession);
    }

    public static final List<KtDiagnostic> toFirDiagnostics(ConeDiagnostic coneDiagnostic, FirSession firSession, KtSourceElement ktSourceElement, KtSourceElement ktSourceElement2, FirValueParameter firValueParameter) {
        coneDiagnostic.getClass();
        firSession.getClass();
        if (coneDiagnostic instanceof ConeInapplicableCandidateError) {
            return mapInapplicableCandidateError((ConeInapplicableCandidateError) coneDiagnostic, firSession, ktSourceElement, ktSourceElement2);
        }
        if (coneDiagnostic instanceof ConeConstraintSystemHasContradiction) {
            return mapSystemHasContradictionError((ConeConstraintSystemHasContradiction) coneDiagnostic, firSession, ktSourceElement, ktSourceElement2);
        }
        return coneDiagnostic instanceof ConeAmbiguityError ? mapConeAmbiguityError((ConeAmbiguityError) coneDiagnostic, ktSourceElement, ktSourceElement2, firSession) : CollectionsKt.listOfNotNull(mapOtherDiagnostic(coneDiagnostic, ktSourceElement, firValueParameter, ktSourceElement2, firSession));
    }

    public static /* synthetic */ List toFirDiagnostics$default(ConeDiagnostic coneDiagnostic, FirSession firSession, KtSourceElement ktSourceElement, KtSourceElement ktSourceElement2, FirValueParameter firValueParameter, int i, Object obj) {
        if ((i & 8) != 0) {
            firValueParameter = null;
        }
        return toFirDiagnostics(coneDiagnostic, firSession, ktSourceElement, ktSourceElement2, firValueParameter);
    }

    public static final KtDiagnostic toInvisibleReferenceDiagnostic(FirBasedSymbol<?> firBasedSymbol, KtSourceElement ktSourceElement, FirSession firSession) {
        firBasedSymbol.getClass();
        firSession.getClass();
        if (firBasedSymbol instanceof FirCallableSymbol) {
            KtDiagnosticFactory3<FirBasedSymbol<?>, Visibility, ClassId> invisible_reference = FirErrors.INSTANCE.getINVISIBLE_REFERENCE();
            FirCallableSymbol firCallableSymbol = (FirCallableSymbol) firBasedSymbol;
            Visibility visibility = firCallableSymbol.getResolvedStatus().getVisibility();
            CallableId callableId = firCallableSymbol.getCallableId();
            return createOn$default(invisible_reference, ktSourceElement, firBasedSymbol, visibility, callableId != null ? callableId.getClassId() : null, firSession, null, 32, null);
        }
        if (firBasedSymbol instanceof FirClassLikeSymbol) {
            FirClassLikeSymbol firClassLikeSymbol = (FirClassLikeSymbol) firBasedSymbol;
            return createOn$default(FirErrors.INSTANCE.getINVISIBLE_REFERENCE(), ktSourceElement, firBasedSymbol, firClassLikeSymbol.getRawStatus().getVisibility(), firClassLikeSymbol.getClassId().getOuterClassId(), firSession, null, 32, null);
        }
        AddToStdlibKt.shouldNotBeCalled("Unexpected receiver " + firBasedSymbol.getClass());
        wq6.a();
        return null;
    }

    private static final KtSimpleDiagnostic unexpectedTrailingLambdaOnNewLineOrNull(FirExpression firExpression, FirSession firSession) {
        KtPsiSourceElement source = firExpression.getSource();
        if (source instanceof KtPsiSourceElement) {
            KtLambdaExpression psi = source.getPsi();
            if ((psi instanceof KtLambdaExpression) && psi.isTrailingLambdaOnNewLine()) {
                return createOn$default(FirErrors.INSTANCE.getUNEXPECTED_TRAILING_LAMBDA_ON_A_NEW_LINE(), source, firSession, null, 4, null);
            }
        }
        if ((source instanceof KtLightSourceElement) && unexpectedTrailingLambdaOnNewLineOrNull$isTrailingLambdaOnNewLine((KtLightSourceElement) source)) {
            return createOn$default(FirErrors.INSTANCE.getUNEXPECTED_TRAILING_LAMBDA_ON_A_NEW_LINE(), source, firSession, null, 4, null);
        }
        return null;
    }

    private static final boolean unexpectedTrailingLambdaOnNewLineOrNull$isTrailingLambdaOnNewLine(KtLightSourceElement ktLightSourceElement) {
        LighterASTNode lighterASTNode = (LighterASTNode) ktLightSourceElement.getTreeStructure().getParent(ktLightSourceElement.getLighterASTNode());
        if (lighterASTNode == null) {
            return false;
        }
        if ((!Intrinsics.areEqual(lighterASTNode.getTokenType(), KtNodeTypes.LABELED_EXPRESSION) || (lighterASTNode = (LighterASTNode) ktLightSourceElement.getTreeStructure().getParent(lighterASTNode)) != null) && Intrinsics.areEqual(lighterASTNode.getTokenType(), KtStubElementTypes.LAMBDA_ARGUMENT)) {
            LighterASTTokenNode previousSibling = LightTreeUtilsKt.getPreviousSibling(lighterASTNode, ktLightSourceElement.getTreeStructure());
            while (previousSibling != null && !(previousSibling.getTokenType() instanceof KtStubElementType)) {
                if (Intrinsics.areEqual(previousSibling.getTokenType(), TokenType.WHITE_SPACE) && (previousSibling instanceof LighterASTTokenNode)) {
                    CharSequence text = previousSibling.getText();
                    text.getClass();
                    if (StringsKt.contains$default(text, "\n", false, 2, (Object) null)) {
                        return true;
                    }
                }
                previousSibling = LightTreeUtilsKt.getPreviousSibling(previousSibling, ktLightSourceElement.getTreeStructure());
            }
        }
        return false;
    }

    public static final <A> KtDiagnosticWithParameters1<A> createOn(KtDiagnosticFactory1<A> ktDiagnosticFactory1, KtSourceElement ktSourceElement, A a, FirSession firSession, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy) {
        ktDiagnosticFactory1.getClass();
        firSession.getClass();
        return ktDiagnosticFactory1.on(KtDiagnosticReportHelpersKt.requireNotNull(ktSourceElement), a, abstractSourceElementPositioningStrategy, toDiagnosticContext(firSession));
    }

    public static /* synthetic */ KtDiagnosticWithParameters1 createOn$default(KtDiagnosticFactory1 ktDiagnosticFactory1, KtSourceElement ktSourceElement, Object obj, FirSession firSession, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy, int i, Object obj2) {
        if ((i & 8) != 0) {
            abstractSourceElementPositioningStrategy = null;
        }
        return createOn(ktDiagnosticFactory1, ktSourceElement, obj, firSession, abstractSourceElementPositioningStrategy);
    }

    public static final KtSimpleDiagnostic createOn(KtDiagnosticFactory0 ktDiagnosticFactory0, KtSourceElement ktSourceElement, FirSession firSession, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy) {
        ktDiagnosticFactory0.getClass();
        firSession.getClass();
        return ktDiagnosticFactory0.on(KtDiagnosticReportHelpersKt.requireNotNull(ktSourceElement), abstractSourceElementPositioningStrategy, toDiagnosticContext(firSession));
    }

    public static /* synthetic */ KtDiagnosticWithParameters2 createOn$default(KtDiagnosticFactory2 ktDiagnosticFactory2, KtSourceElement ktSourceElement, Object obj, Object obj2, FirSession firSession, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy, int i, Object obj3) {
        if ((i & 16) != 0) {
            abstractSourceElementPositioningStrategy = null;
        }
        return createOn(ktDiagnosticFactory2, ktSourceElement, obj, obj2, firSession, abstractSourceElementPositioningStrategy);
    }

    public static final <A, B, C> KtDiagnosticWithParameters3<A, B, C> createOn(KtDiagnosticFactory3<A, B, C> ktDiagnosticFactory3, KtSourceElement ktSourceElement, A a, B b, C c, FirSession firSession, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy) {
        ktDiagnosticFactory3.getClass();
        firSession.getClass();
        return ktDiagnosticFactory3.on(KtDiagnosticReportHelpersKt.requireNotNull(ktSourceElement), a, b, c, abstractSourceElementPositioningStrategy, toDiagnosticContext(firSession));
    }

    public static /* synthetic */ KtDiagnosticWithParameters3 createOn$default(KtDiagnosticFactory3 ktDiagnosticFactory3, KtSourceElement ktSourceElement, Object obj, Object obj2, Object obj3, FirSession firSession, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy, int i, Object obj4) {
        if ((i & 32) != 0) {
            abstractSourceElementPositioningStrategy = null;
        }
        return createOn(ktDiagnosticFactory3, ktSourceElement, obj, obj2, obj3, firSession, abstractSourceElementPositioningStrategy);
    }

    public static final <A, B, C, D> KtDiagnosticWithParameters4<A, B, C, D> createOn(KtDiagnosticFactory4<A, B, C, D> ktDiagnosticFactory4, KtSourceElement ktSourceElement, A a, B b, C c, D d, FirSession firSession, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy) {
        ktDiagnosticFactory4.getClass();
        firSession.getClass();
        return ktDiagnosticFactory4.on(KtDiagnosticReportHelpersKt.requireNotNull(ktSourceElement), a, b, c, d, abstractSourceElementPositioningStrategy, toDiagnosticContext(firSession));
    }

    public static /* synthetic */ KtSimpleDiagnostic createOn$default(KtDiagnosticFactory0 ktDiagnosticFactory0, KtSourceElement ktSourceElement, FirSession firSession, AbstractSourceElementPositioningStrategy abstractSourceElementPositioningStrategy, int i, Object obj) {
        if ((i & 4) != 0) {
            abstractSourceElementPositioningStrategy = null;
        }
        return createOn(ktDiagnosticFactory0, ktSourceElement, firSession, abstractSourceElementPositioningStrategy);
    }
}
