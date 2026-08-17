package org.jetbrains.kotlin.fir.analysis.checkers;

import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.analysis.cfa.AbstractFirPropertyInitializationChecker;
import org.jetbrains.kotlin.fir.analysis.cfa.FirCallsEffectAnalyzer;
import org.jetbrains.kotlin.fir.analysis.cfa.FirPropertyInitializationAnalyzer;
import org.jetbrains.kotlin.fir.analysis.checkers.cfa.FirControlFlowChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.ContractSyntaxV2FunctionChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.ContractSyntaxV2PropertyChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirActualTypeAliasChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirAmbiguousAnonymousTypeChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirAnnotationChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirAnnotationClassDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirAnnotationClassInheritanceChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirAnonymousFunctionParametersChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirAnonymousFunctionTypeParametersChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirAnonymousInitializerInInterfaceChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirAnyDeprecationChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirAnyTypeAliasChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirBadInheritedJavaSignaturesChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirClassVarianceChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirCommonConstructorDelegationIssuesChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirCompanionBlockChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirCompanionBlockMemberChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirCompanionExtensionChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirConflictsDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirConstPropertyChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirConstructorAllowedChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirContextParametersDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirContextReceiversDeprecatedDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirContextualPropertyWithBackingFieldChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirContractChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirCoroutineContextAsContextParameterDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirCyclicTypeBoundsChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDataClassConsistentDataCopyAnnotationChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDataClassNonPublicConstructorChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDataClassPrimaryConstructorChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDataObjectContentChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDelegateFieldTypeMismatchChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDelegateUsesExtensionPropertyTypeParameterChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDelegatedPropertyChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDelegationSuperCallInEnumConstructorChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDestructuringDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDestructuringParameterChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDynamicReceiverChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDynamicSupertypeChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirEnumClassSimpleChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirEnumCompanionInEnumConstructorCallChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirEnumEntriesRedeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirEnumEntryInitializationChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirExpectActualClassifiersAreInBetaChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirExpectActualDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirExpectConsistencyChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirExpectRefinementChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirExplicitBackingFieldForbiddenChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirExplicitBackingFieldsUnsupportedChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirExposedVisibilityDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirExtensionShadowedByMemberChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirFiniteBoundRestrictionChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirFunInterfaceDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirFunctionNameChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirFunctionParameterChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirFunctionReturnChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirIllegalCompanionBlockMemberChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirImplementationMismatchChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirImplicitNothingReturnTypeChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirImplicitReturnTypeAnnotationMissingDependencyChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirImportsChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirInapplicableLateinitChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirInfixFunctionDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirInitializerTypeMismatchChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirInlineBodyRegularClassChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirInlineBodySimpleFunctionChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirInlineClassDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirInlineDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirInlinePropertyChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirInlinedLambdaNonSourceAnnotationsChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirKClassWithIncorrectTypeArgumentChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirLocalEntityNotAllowedChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirLocalExtensionPropertyChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirManyCompanionObjectsChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirMemberFunctionsChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirMemberPropertiesChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirMethodOfAnyImplementedInInterfaceChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirMissingDependencyClassForLambdaReceiverChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirMissingDependencyClassForParameterChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirMissingDependencySupertypeInDeclarationsChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirMixedFunctionalTypesInSupertypesChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirModifierChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirMultipleDefaultsInheritedFromSupertypesChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirNestedClassChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirNonExpansiveInheritanceRestrictionChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirNonMemberFunctionsChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirNotImplementedOverrideChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirNotImplementedOverrideSimpleEnumEntryChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirObjectConstructorChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirOpenMemberChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirOperatorModifierChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirOperatorOfChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirOptInAnnotationClassChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirOptInEnumEntryChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirOptInImportsChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirOptInMarkedDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirOptionalExpectationDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirOuterClassArgumentsRequiredChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirOverrideChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirPackageConflictsWithClassifierChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirPrimaryConstructorSuperTypeChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirPropertyAccessorsTypesChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirPropertyFieldTypeChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirPropertyFromParameterChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirPropertyInitializationChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirPropertyTypeParametersChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirPublishedApiChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirReifiedTypeParameterChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirRequiresOptInOnExpectChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirReservedUnderscoreDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirScriptPropertiesChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirSealedInterfaceAllowedChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirSealedSupertypeChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirSupertypesChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirSuspendAnonymousFunctionChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirSuspendLimitationsChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirTailrecFunctionChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirThrowableSubclassChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirTooLargeFunctionImportChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirTopLevelPropertiesChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirTypeConstraintsChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirTypeParameterBoundsChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirTypeParameterVarianceChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirTypeParametersInObjectChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirUnnamedPropertyChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirValueClassDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirValueParameterDefaultValueTypeMismatchChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirVersionOverloadsChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirVolatileAnnotationChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.PlatformClassMappedToKotlinImportsChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.crv.FirReturnValueAnnotationsChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.crv.FirReturnValueOverrideChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.crv.FirUnusedReturnValueChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.extra.FirUnusedExpressionChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.syntax.FirDelegationInExpectClassSyntaxChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.syntax.FirDelegationInInterfaceSyntaxChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.syntax.FirExplicitApiDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.syntax.FirFunctionTypeParametersSyntaxChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.syntax.FirLocalVariableTypeParametersSyntaxChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.syntax.FirMissingConstructorKeywordSyntaxChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.syntax.FirTypeParameterSyntaxChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.syntax.FirUnresolvedInMiddleOfImportChecker;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousInitializer;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirScript;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000ü\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R$\u0010\u0004\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00070\u0006j\u0002`\b0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR$\u0010\u000b\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\f0\u0006j\u0002`\r0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\nR$\u0010\u000f\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00100\u0006j\u0002`\u00110\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\nR$\u0010\u0013\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00140\u0006j\u0002`\u00150\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\nR$\u0010\u0017\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00180\u0006j\u0002`\u00190\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\nR$\u0010\u001b\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u001c0\u0006j\u0002`\u001d0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\nR$\u0010\u001f\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020 0\u0006j\u0002`!0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\nR$\u0010#\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020$0\u0006j\u0002`%0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\nR$\u0010'\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020(0\u0006j\u0002`)0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\nR$\u0010+\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020,0\u0006j\u0002`-0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\nR$\u0010/\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u0002000\u0006j\u0002`10\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\nR$\u00103\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u0002040\u0006j\u0002`50\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b6\u0010\nR\u001a\u00107\u001a\b\u0012\u0004\u0012\u0002080\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b9\u0010\nR\u001a\u0010:\u001a\b\u0012\u0004\u0012\u00020;0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b<\u0010\nR$\u0010=\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020>0\u0006j\u0002`?0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b@\u0010\nR$\u0010A\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020B0\u0006j\u0002`C0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bD\u0010\nR$\u0010E\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020F0\u0006j\u0002`G0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bH\u0010\nR$\u0010I\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020J0\u0006j\u0002`K0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bL\u0010\nR$\u0010M\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020N0\u0006j\u0002`O0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bP\u0010\nR$\u0010Q\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020R0\u0006j\u0002`S0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bT\u0010\n¨\u0006U"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/CommonDeclarationCheckers;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/DeclarationCheckers;", "<init>", "()V", "basicDeclarationCheckers", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirBasicDeclarationChecker;", "getBasicDeclarationCheckers", "()Ljava/util/Set;", "classLikeCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirClassLikeChecker;", "getClassLikeCheckers", "callableDeclarationCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirCallableDeclarationChecker;", "getCallableDeclarationCheckers", "functionCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirFunctionChecker;", "getFunctionCheckers", "simpleFunctionCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirSimpleFunctionChecker;", "getSimpleFunctionCheckers", "propertyCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirPropertyChecker;", "getPropertyCheckers", "backingFieldCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirBackingField;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirBackingFieldChecker;", "getBackingFieldCheckers", "classCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirClassChecker;", "getClassCheckers", "regularClassCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirRegularClassChecker;", "getRegularClassCheckers", "constructorCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirConstructorChecker;", "getConstructorCheckers", "fileCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirFileChecker;", "getFileCheckers", "scriptCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirScript;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirScriptChecker;", "getScriptCheckers", "controlFlowAnalyserCheckers", "Lorg/jetbrains/kotlin/fir/analysis/checkers/cfa/FirControlFlowChecker;", "getControlFlowAnalyserCheckers", "variableAssignmentCfaBasedCheckers", "Lorg/jetbrains/kotlin/fir/analysis/cfa/AbstractFirPropertyInitializationChecker;", "getVariableAssignmentCfaBasedCheckers", "typeParameterCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirTypeParameterChecker;", "getTypeParameterCheckers", "typeAliasCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirTypeAliasChecker;", "getTypeAliasCheckers", "anonymousFunctionCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirAnonymousFunctionChecker;", "getAnonymousFunctionCheckers", "anonymousInitializerCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousInitializer;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirAnonymousInitializerChecker;", "getAnonymousInitializerCheckers", "valueParameterCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirValueParameterChecker;", "getValueParameterCheckers", "enumEntryCheckers", "Lorg/jetbrains/kotlin/fir/declarations/FirEnumEntry;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirEnumEntryChecker;", "getEnumEntryCheckers", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CommonDeclarationCheckers extends DeclarationCheckers {
    public static final CommonDeclarationCheckers INSTANCE = new CommonDeclarationCheckers();
    private static final Set<FirDeclarationChecker<FirDeclaration>> basicDeclarationCheckers = SetsKt.setOf(new FirDeclarationChecker[]{FirModifierChecker.INSTANCE, FirConflictsDeclarationChecker.INSTANCE, FirTypeConstraintsChecker.INSTANCE, FirReservedUnderscoreDeclarationChecker.INSTANCE, FirExposedVisibilityDeclarationChecker.INSTANCE, FirCyclicTypeBoundsChecker.INSTANCE, FirExpectActualDeclarationChecker.INSTANCE, FirExpectRefinementChecker.INSTANCE, FirRequiresOptInOnExpectChecker.INSTANCE, FirAmbiguousAnonymousTypeChecker.INSTANCE, FirExplicitApiDeclarationChecker.INSTANCE, FirAnnotationChecker.INSTANCE, FirPublishedApiChecker.INSTANCE, FirContextReceiversDeprecatedDeclarationChecker.INSTANCE, FirOptInMarkedDeclarationChecker.INSTANCE, FirExpectConsistencyChecker.INSTANCE, FirOptionalExpectationDeclarationChecker.INSTANCE, FirMissingDependencySupertypeInDeclarationsChecker.INSTANCE, FirContextParametersDeclarationChecker.INSTANCE, FirUnusedExpressionChecker.INSTANCE, FirUnusedReturnValueChecker.INSTANCE, FirReturnValueAnnotationsChecker.INSTANCE, FirIllegalCompanionBlockMemberChecker.INSTANCE});
    private static final Set<FirDeclarationChecker<FirClassLikeDeclaration>> classLikeCheckers = SetsKt.setOf(FirExpectActualClassifiersAreInBetaChecker.INSTANCE);
    private static final Set<FirDeclarationChecker<FirCallableDeclaration>> callableDeclarationCheckers = SetsKt.setOf(new FirDeclarationChecker[]{FirKClassWithIncorrectTypeArgumentChecker.INSTANCE, FirImplicitNothingReturnTypeChecker.INSTANCE, FirDynamicReceiverChecker.INSTANCE, FirExtensionShadowedByMemberChecker.Regular.INSTANCE, FirExtensionShadowedByMemberChecker.ForExpectDeclaration.INSTANCE, FirReturnValueOverrideChecker.INSTANCE, FirImplicitReturnTypeAnnotationMissingDependencyChecker.INSTANCE, FirCoroutineContextAsContextParameterDeclarationChecker.INSTANCE, FirCompanionExtensionChecker.INSTANCE, FirCompanionBlockMemberChecker.INSTANCE});
    private static final Set<FirDeclarationChecker<FirFunction>> functionCheckers = SetsKt.setOf(new FirDeclarationChecker[]{FirContractChecker.INSTANCE, FirFunctionParameterChecker.INSTANCE, FirFunctionReturnChecker.INSTANCE, FirInlineDeclarationChecker.INSTANCE, FirNonMemberFunctionsChecker.INSTANCE, FirSuspendLimitationsChecker.INSTANCE, FirInfixFunctionDeclarationChecker.INSTANCE, FirOperatorModifierChecker.INSTANCE, FirTailrecFunctionChecker.INSTANCE, FirVersionOverloadsChecker.INSTANCE});
    private static final Set<FirDeclarationChecker<FirNamedFunction>> simpleFunctionCheckers = SetsKt.setOf(new FirDeclarationChecker[]{FirFunctionNameChecker.INSTANCE, FirFunctionTypeParametersSyntaxChecker.INSTANCE, FirMemberFunctionsChecker.INSTANCE, FirInlineBodySimpleFunctionChecker.INSTANCE, FirDataObjectContentChecker.INSTANCE, ContractSyntaxV2FunctionChecker.INSTANCE, FirAnyDeprecationChecker.INSTANCE});
    private static final Set<FirDeclarationChecker<FirProperty>> propertyCheckers = SetsKt.setOf(new FirDeclarationChecker[]{FirInapplicableLateinitChecker.INSTANCE, FirDestructuringDeclarationChecker.INSTANCE, FirConstPropertyChecker.INSTANCE, FirPropertyAccessorsTypesChecker.INSTANCE, FirPropertyTypeParametersChecker.INSTANCE, FirInitializerTypeMismatchChecker.INSTANCE, FirDelegatedPropertyChecker.INSTANCE, FirPropertyFieldTypeChecker.INSTANCE, FirPropertyFromParameterChecker.INSTANCE, FirLocalVariableTypeParametersSyntaxChecker.INSTANCE, FirDelegateUsesExtensionPropertyTypeParameterChecker.INSTANCE, FirLocalExtensionPropertyChecker.INSTANCE, ContractSyntaxV2PropertyChecker.INSTANCE, FirVolatileAnnotationChecker.INSTANCE, FirInlinePropertyChecker.INSTANCE, FirUnnamedPropertyChecker.INSTANCE, FirContextualPropertyWithBackingFieldChecker.INSTANCE});
    private static final Set<FirDeclarationChecker<FirBackingField>> backingFieldCheckers = SetsKt.setOf(new FirDeclarationChecker[]{FirExplicitBackingFieldForbiddenChecker.INSTANCE, FirExplicitBackingFieldsUnsupportedChecker.INSTANCE});
    private static final Set<FirDeclarationChecker<FirClass>> classCheckers = SetsKt.setOf(new FirDeclarationChecker[]{FirOverrideChecker.Regular.INSTANCE, FirOverrideChecker.ForExpectClass.INSTANCE, FirNotImplementedOverrideChecker.INSTANCE, FirNotImplementedOverrideSimpleEnumEntryChecker.Regular.INSTANCE, FirNotImplementedOverrideSimpleEnumEntryChecker.ForExpectClass.INSTANCE, FirThrowableSubclassChecker.INSTANCE, FirOpenMemberChecker.INSTANCE, FirClassVarianceChecker.INSTANCE, FirSealedSupertypeChecker.INSTANCE, FirMemberPropertiesChecker.INSTANCE, FirImplementationMismatchChecker.Regular.INSTANCE, FirImplementationMismatchChecker.ForExpectClass.INSTANCE, FirTypeParametersInObjectChecker.INSTANCE, FirSupertypesChecker.INSTANCE, FirPrimaryConstructorSuperTypeChecker.INSTANCE, FirDynamicSupertypeChecker.INSTANCE, FirDataClassConsistentDataCopyAnnotationChecker.INSTANCE, FirEnumCompanionInEnumConstructorCallChecker.INSTANCE, FirBadInheritedJavaSignaturesChecker.INSTANCE, FirSealedInterfaceAllowedChecker.INSTANCE, FirMixedFunctionalTypesInSupertypesChecker.Regular.INSTANCE, FirMixedFunctionalTypesInSupertypesChecker.ForExpectClass.INSTANCE, FirDelegateFieldTypeMismatchChecker.INSTANCE, FirAnnotationClassInheritanceChecker.INSTANCE, FirMultipleDefaultsInheritedFromSupertypesChecker.Regular.INSTANCE, FirMultipleDefaultsInheritedFromSupertypesChecker.ForExpectClass.INSTANCE, FirPropertyInitializationChecker.INSTANCE, FirCompanionBlockChecker.INSTANCE});
    private static final Set<FirDeclarationChecker<FirRegularClass>> regularClassCheckers = SetsKt.setOf(new FirDeclarationChecker[]{FirAnnotationClassDeclarationChecker.INSTANCE, FirOptInAnnotationClassChecker.INSTANCE, FirOperatorOfChecker.INSTANCE, FirCommonConstructorDelegationIssuesChecker.INSTANCE, FirDelegationSuperCallInEnumConstructorChecker.INSTANCE, FirDelegationInExpectClassSyntaxChecker.INSTANCE, FirDelegationInInterfaceSyntaxChecker.INSTANCE, FirEnumClassSimpleChecker.INSTANCE, FirLocalEntityNotAllowedChecker.INSTANCE, FirInlineBodyRegularClassChecker.INSTANCE, FirManyCompanionObjectsChecker.INSTANCE, FirMethodOfAnyImplementedInInterfaceChecker.INSTANCE, FirDataClassPrimaryConstructorChecker.INSTANCE, FirDataClassNonPublicConstructorChecker.INSTANCE, FirFunInterfaceDeclarationChecker.Regular.INSTANCE, FirFunInterfaceDeclarationChecker.ForExpectClass.INSTANCE, FirNestedClassChecker.INSTANCE, FirValueClassDeclarationChecker.Regular.INSTANCE, FirValueClassDeclarationChecker.ForExpectClass.INSTANCE, FirOuterClassArgumentsRequiredChecker.INSTANCE, FirFiniteBoundRestrictionChecker.INSTANCE, FirNonExpansiveInheritanceRestrictionChecker.INSTANCE, FirObjectConstructorChecker.INSTANCE, FirInlineClassDeclarationChecker.INSTANCE, FirEnumEntryInitializationChecker.INSTANCE});
    private static final Set<FirDeclarationChecker<FirConstructor>> constructorCheckers = SetsKt.setOf(new FirDeclarationChecker[]{FirConstructorAllowedChecker.INSTANCE, FirMissingConstructorKeywordSyntaxChecker.INSTANCE});
    private static final Set<FirDeclarationChecker<FirFile>> fileCheckers = SetsKt.setOf(new FirDeclarationChecker[]{FirImportsChecker.INSTANCE, FirOptInImportsChecker.INSTANCE, FirUnresolvedInMiddleOfImportChecker.INSTANCE, FirTooLargeFunctionImportChecker.INSTANCE, FirTopLevelPropertiesChecker.INSTANCE, FirPackageConflictsWithClassifierChecker.INSTANCE, PlatformClassMappedToKotlinImportsChecker.INSTANCE});
    private static final Set<FirDeclarationChecker<FirScript>> scriptCheckers = SetsKt.setOf(FirScriptPropertiesChecker.INSTANCE);
    private static final Set<FirControlFlowChecker> controlFlowAnalyserCheckers = SetsKt.setOf(FirCallsEffectAnalyzer.INSTANCE);
    private static final Set<AbstractFirPropertyInitializationChecker> variableAssignmentCfaBasedCheckers = SetsKt.setOf(FirPropertyInitializationAnalyzer.INSTANCE);
    private static final Set<FirDeclarationChecker<FirTypeParameter>> typeParameterCheckers = SetsKt.setOf(new FirDeclarationChecker[]{FirTypeParameterBoundsChecker.Regular.INSTANCE, FirTypeParameterBoundsChecker.ForExpectClass.INSTANCE, FirTypeParameterVarianceChecker.INSTANCE, FirReifiedTypeParameterChecker.INSTANCE, FirTypeParameterSyntaxChecker.INSTANCE});
    private static final Set<FirDeclarationChecker<FirTypeAlias>> typeAliasCheckers = SetsKt.setOf(new FirDeclarationChecker[]{FirAnyTypeAliasChecker.INSTANCE, FirActualTypeAliasChecker.INSTANCE});
    private static final Set<FirDeclarationChecker<FirAnonymousFunction>> anonymousFunctionCheckers = SetsKt.setOf(new FirDeclarationChecker[]{FirAnonymousFunctionParametersChecker.INSTANCE, FirAnonymousFunctionTypeParametersChecker.INSTANCE, FirInlinedLambdaNonSourceAnnotationsChecker.INSTANCE, FirSuspendAnonymousFunctionChecker.INSTANCE, FirMissingDependencyClassForLambdaReceiverChecker.INSTANCE});
    private static final Set<FirDeclarationChecker<FirAnonymousInitializer>> anonymousInitializerCheckers = SetsKt.setOf(FirAnonymousInitializerInInterfaceChecker.INSTANCE);
    private static final Set<FirDeclarationChecker<FirValueParameter>> valueParameterCheckers = SetsKt.setOf(new FirDeclarationChecker[]{FirValueParameterDefaultValueTypeMismatchChecker.INSTANCE, FirMissingDependencyClassForParameterChecker.INSTANCE, FirDestructuringParameterChecker.INSTANCE});
    private static final Set<FirDeclarationChecker<FirEnumEntry>> enumEntryCheckers = SetsKt.setOf(new FirDeclarationChecker[]{FirEnumEntriesRedeclarationChecker.INSTANCE, FirOptInEnumEntryChecker.INSTANCE});

    private CommonDeclarationCheckers() {
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirAnonymousFunction>> getAnonymousFunctionCheckers() {
        return anonymousFunctionCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirAnonymousInitializer>> getAnonymousInitializerCheckers() {
        return anonymousInitializerCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirBackingField>> getBackingFieldCheckers() {
        return backingFieldCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirDeclaration>> getBasicDeclarationCheckers() {
        return basicDeclarationCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirCallableDeclaration>> getCallableDeclarationCheckers() {
        return callableDeclarationCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirClass>> getClassCheckers() {
        return classCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirClassLikeDeclaration>> getClassLikeCheckers() {
        return classLikeCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirConstructor>> getConstructorCheckers() {
        return constructorCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirControlFlowChecker> getControlFlowAnalyserCheckers() {
        return controlFlowAnalyserCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirEnumEntry>> getEnumEntryCheckers() {
        return enumEntryCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirFile>> getFileCheckers() {
        return fileCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirFunction>> getFunctionCheckers() {
        return functionCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirProperty>> getPropertyCheckers() {
        return propertyCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirRegularClass>> getRegularClassCheckers() {
        return regularClassCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirScript>> getScriptCheckers() {
        return scriptCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirNamedFunction>> getSimpleFunctionCheckers() {
        return simpleFunctionCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirTypeAlias>> getTypeAliasCheckers() {
        return typeAliasCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirTypeParameter>> getTypeParameterCheckers() {
        return typeParameterCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<FirDeclarationChecker<FirValueParameter>> getValueParameterCheckers() {
        return valueParameterCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
    public Set<AbstractFirPropertyInitializationChecker> getVariableAssignmentCfaBasedCheckers() {
        return variableAssignmentCfaBasedCheckers;
    }
}
