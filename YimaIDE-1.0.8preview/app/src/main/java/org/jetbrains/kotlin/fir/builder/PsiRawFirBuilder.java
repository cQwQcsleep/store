package org.jetbrains.kotlin.fir.builder;

import com.google.common.collect.ImmutableSet;
import com.intellij.extapi.psi.StubBasedPsiElementBase;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.util.ThrowableComputable;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiErrorElement;
import com.intellij.psi.stubs.IStubElementType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.AstLoadingFilter;
import defpackage.f2f;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.UninitializedPropertyAccessException;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.KtFakePsiSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtNodeTypes;
import org.jetbrains.kotlin.KtPsiSourceElement;
import org.jetbrains.kotlin.KtPsiSourceFile;
import org.jetbrains.kotlin.KtPsiSourceFileLinesMapping;
import org.jetbrains.kotlin.KtRealPsiSourceElement;
import org.jetbrains.kotlin.KtRealSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.KtSourceFile;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.codegen.state.InlineClassManglingUtilsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.EffectiveVisibility;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.descriptors.annotations.AnnotationUseSiteTarget;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.CompanionBlockInfo;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirExpressionRef;
import org.jetbrains.kotlin.fir.FirFunctionTarget;
import org.jetbrains.kotlin.fir.FirGenerationKt;
import org.jetbrains.kotlin.fir.FirLabel;
import org.jetbrains.kotlin.fir.FirLoopTarget;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.FirTarget;
import org.jetbrains.kotlin.fir.MutableOrEmptyList;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder;
import org.jetbrains.kotlin.fir.builder.PsiRawFirBuilder;
import org.jetbrains.kotlin.fir.builder.PsiRawFirBuilder.Visitor;
import org.jetbrains.kotlin.fir.contracts.FirContractDescription;
import org.jetbrains.kotlin.fir.contracts.builder.FirRawContractDescriptionBuilder;
import org.jetbrains.kotlin.fir.declarations.DelegateFieldsMapKt;
import org.jetbrains.kotlin.fir.declarations.DestructuringDeclarationAttributesKt;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousInitializer;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousObject;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.FirCodeFragment;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDanglingModifierList;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirImport;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirReplSnippet;
import org.jetbrains.kotlin.fir.declarations.FirResolvedDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirScript;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameterKind;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.declarations.builder.FirAbstractConstructorBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirAnonymousFunctionBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirAnonymousInitializerBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirAnonymousObjectBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirBackingFieldBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirClassBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirCodeFragmentBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirConstructorBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirDanglingModifierListBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirDefaultSetterValueParameterBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirEnumEntryBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirFieldBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirFileBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirFunctionBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirImportBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirNamedFunctionBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirPrimaryConstructorBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirPropertyAccessorBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirPropertyBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirRegularClassBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirReplSnippetBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirScriptBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirTypeAliasBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirTypeParameterBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirTypeParametersOwnerBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirValueParameterBuilder;
import org.jetbrains.kotlin.fir.declarations.impl.FirDeclarationStatusImpl;
import org.jetbrains.kotlin.fir.declarations.impl.FirDefaultPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.impl.FirDefaultPropertyBackingField;
import org.jetbrains.kotlin.fir.declarations.impl.FirDefaultPropertyGetter;
import org.jetbrains.kotlin.fir.declarations.impl.FirDefaultPropertySetter;
import org.jetbrains.kotlin.fir.declarations.impl.FirResolvedDeclarationStatusImpl;
import org.jetbrains.kotlin.fir.declarations.utils.DanglingTypeConstraint;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationBuildingUtilsKt;
import org.jetbrains.kotlin.fir.diagnostics.ConeContextParameterWithDefaultValue;
import org.jetbrains.kotlin.fir.diagnostics.ConeContractMayNotHaveLabel;
import org.jetbrains.kotlin.fir.diagnostics.ConeContractShouldBeFirstStatement;
import org.jetbrains.kotlin.fir.diagnostics.ConeDanglingModifierOnTopLevel;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.ConeNoConstructorError;
import org.jetbrains.kotlin.fir.diagnostics.ConeNotAnnotationContainer;
import org.jetbrains.kotlin.fir.diagnostics.ConeSimpleDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.ConeSyntaxDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.ConeUnsupportedClassLiteralsWithEmptyLhs;
import org.jetbrains.kotlin.fir.diagnostics.DiagnosticKind;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousObjectExpression;
import org.jetbrains.kotlin.fir.expressions.FirArgumentList;
import org.jetbrains.kotlin.fir.expressions.FirArgumentUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirCatch;
import org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall;
import org.jetbrains.kotlin.fir.expressions.FirErrorAnnotationCall;
import org.jetbrains.kotlin.fir.expressions.FirErrorExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCallOrigin;
import org.jetbrains.kotlin.fir.expressions.FirLazyBlock;
import org.jetbrains.kotlin.fir.expressions.FirLazyExpression;
import org.jetbrains.kotlin.fir.expressions.FirOperation;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirReplDeclarationReference;
import org.jetbrains.kotlin.fir.expressions.FirReplPropertyDelegate;
import org.jetbrains.kotlin.fir.expressions.FirReplPropertyInitializer;
import org.jetbrains.kotlin.fir.expressions.FirResolvable;
import org.jetbrains.kotlin.fir.expressions.FirReturnExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.FirSuperReceiverExpression;
import org.jetbrains.kotlin.fir.expressions.FirWhenBranch;
import org.jetbrains.kotlin.fir.expressions.FirWhenExpression;
import org.jetbrains.kotlin.fir.expressions.builder.FirAbstractFunctionCallBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirAbstractWhenBranchBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirAnnotationCallBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirAnonymousFunctionExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirAnonymousObjectExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirArgumentListBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirBlockBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirBreakExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirCallBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirCallableReferenceAccessBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirCatchBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirCheckNotNullCallBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirCollectionLiteralBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirContinueExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirDelegatedConstructorCallBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirDoWhileLoopBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirElseIfTrueConditionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirEmptyExpressionBlockBuilderKt;
import org.jetbrains.kotlin.fir.expressions.builder.FirEqualityOperatorCallBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirErrorAnnotationCallBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirErrorExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirExpressionStubBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirFunctionCallBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirGetClassCallBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirGuardedWhenBranchBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirImplicitInvokeCallBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirLazyBlockBuilderKt;
import org.jetbrains.kotlin.fir.expressions.builder.FirLazyExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirMultiDelegatedConstructorCallBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirNamedArgumentExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirPropertyAccessExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirQualifiedAccessExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirQualifiedErrorAccessExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirRegularWhenBranchBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirReplDeclarationReferenceBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirReplExpressionReferenceBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirReplPropertyDelegateBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirReplPropertyInitializerBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirReturnExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirSpreadArgumentExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirStringConcatenationCallBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirSuperReceiverExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirThisReceiverExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirThrowExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirTryExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirTypeOperatorCallBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirUnitExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirWhenExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirWhileLoopBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirWrappedDelegateExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.impl.FirSingleExpressionBlock;
import org.jetbrains.kotlin.fir.references.FirNamedReference;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.references.builder.FirExplicitSuperReferenceBuilder;
import org.jetbrains.kotlin.fir.references.builder.FirExplicitThisReferenceBuilder;
import org.jetbrains.kotlin.fir.references.builder.FirPropertyFromParameterResolvedNamedReferenceBuilder;
import org.jetbrains.kotlin.fir.references.builder.FirSimpleNamedReferenceBuilder;
import org.jetbrains.kotlin.fir.scopes.FirScopeProvider;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousInitializerSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousObjectSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirBackingFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCodeFragmentSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirDanglingModifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirEnumEntrySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFileSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirLocalPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertyAccessorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirReplSnippetSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirScriptSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.FirErrorTypeRef;
import org.jetbrains.kotlin.fir.types.FirQualifierPart;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirUserTypeRef;
import org.jetbrains.kotlin.fir.types.builder.FirDynamicTypeRefBuilder;
import org.jetbrains.kotlin.fir.types.builder.FirErrorTypeRefBuilder;
import org.jetbrains.kotlin.fir.types.builder.FirFunctionTypeRefBuilder;
import org.jetbrains.kotlin.fir.types.builder.FirIntersectionTypeRefBuilder;
import org.jetbrains.kotlin.fir.types.builder.FirPlaceholderProjectionBuilder;
import org.jetbrains.kotlin.fir.types.builder.FirResolvedTypeRefBuilder;
import org.jetbrains.kotlin.fir.types.builder.FirStarProjectionBuilder;
import org.jetbrains.kotlin.fir.types.builder.FirTypeProjectionWithVarianceBuilder;
import org.jetbrains.kotlin.fir.types.builder.FirUserTypeRefBuilder;
import org.jetbrains.kotlin.fir.types.impl.ConeClassLikeTypeImpl;
import org.jetbrains.kotlin.fir.types.impl.FirImplicitTypeRefImplWithoutSource;
import org.jetbrains.kotlin.fir.types.impl.FirQualifierPartImpl;
import org.jetbrains.kotlin.fir.types.impl.FirTypeArgumentListImpl;
import org.jetbrains.kotlin.fir.types.impl.ResolvedImplicitTypeRef;
import org.jetbrains.kotlin.lexer.KtModifierKeywordToken;
import org.jetbrains.kotlin.lexer.KtSingleValueToken;
import org.jetbrains.kotlin.lexer.KtToken;
import org.jetbrains.kotlin.lexer.KtTokens;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.NameUtils;
import org.jetbrains.kotlin.name.SpecialNames;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.psi.KtAnnotated;
import org.jetbrains.kotlin.psi.KtAnnotatedExpression;
import org.jetbrains.kotlin.psi.KtAnnotation;
import org.jetbrains.kotlin.psi.KtAnnotationEntry;
import org.jetbrains.kotlin.psi.KtAnnotationUseSiteTarget;
import org.jetbrains.kotlin.psi.KtAnonymousInitializer;
import org.jetbrains.kotlin.psi.KtArrayAccessExpression;
import org.jetbrains.kotlin.psi.KtBackingField;
import org.jetbrains.kotlin.psi.KtBinaryExpression;
import org.jetbrains.kotlin.psi.KtBinaryExpressionWithTypeRHS;
import org.jetbrains.kotlin.psi.KtBlockCodeFragment;
import org.jetbrains.kotlin.psi.KtBlockExpression;
import org.jetbrains.kotlin.psi.KtBlockStringTemplateEntry;
import org.jetbrains.kotlin.psi.KtBreakExpression;
import org.jetbrains.kotlin.psi.KtCallElement;
import org.jetbrains.kotlin.psi.KtCallExpression;
import org.jetbrains.kotlin.psi.KtCallableReferenceExpression;
import org.jetbrains.kotlin.psi.KtCatchClause;
import org.jetbrains.kotlin.psi.KtClass;
import org.jetbrains.kotlin.psi.KtClassBody;
import org.jetbrains.kotlin.psi.KtClassInitializer;
import org.jetbrains.kotlin.psi.KtClassLiteralExpression;
import org.jetbrains.kotlin.psi.KtClassOrObject;
import org.jetbrains.kotlin.psi.KtCodeFragment;
import org.jetbrains.kotlin.psi.KtCollectionLiteralExpression;
import org.jetbrains.kotlin.psi.KtCompanionBlock;
import org.jetbrains.kotlin.psi.KtConstantExpression;
import org.jetbrains.kotlin.psi.KtConstructor;
import org.jetbrains.kotlin.psi.KtConstructorCalleeExpression;
import org.jetbrains.kotlin.psi.KtConstructorDelegationCall;
import org.jetbrains.kotlin.psi.KtConstructorDelegationReferenceExpression;
import org.jetbrains.kotlin.psi.KtContainerNodeForControlStructureBody;
import org.jetbrains.kotlin.psi.KtContextParameterList;
import org.jetbrains.kotlin.psi.KtContextReceiver;
import org.jetbrains.kotlin.psi.KtContinueExpression;
import org.jetbrains.kotlin.psi.KtContractEffect;
import org.jetbrains.kotlin.psi.KtContractEffectKt;
import org.jetbrains.kotlin.psi.KtContractEffectList;
import org.jetbrains.kotlin.psi.KtContractEffectListKt;
import org.jetbrains.kotlin.psi.KtDeclaration;
import org.jetbrains.kotlin.psi.KtDeclarationModifierList;
import org.jetbrains.kotlin.psi.KtDeclarationWithBody;
import org.jetbrains.kotlin.psi.KtDeclarationWithInitializer;
import org.jetbrains.kotlin.psi.KtDelegatedSuperTypeEntry;
import org.jetbrains.kotlin.psi.KtDestructuringDeclaration;
import org.jetbrains.kotlin.psi.KtDestructuringDeclarationEntry;
import org.jetbrains.kotlin.psi.KtDoWhileExpression;
import org.jetbrains.kotlin.psi.KtDotQualifiedExpression;
import org.jetbrains.kotlin.psi.KtDynamicType;
import org.jetbrains.kotlin.psi.KtElement;
import org.jetbrains.kotlin.psi.KtElementImplStub;
import org.jetbrains.kotlin.psi.KtEnumEntry;
import org.jetbrains.kotlin.psi.KtEscapeStringTemplateEntry;
import org.jetbrains.kotlin.psi.KtExpression;
import org.jetbrains.kotlin.psi.KtExpressionCodeFragment;
import org.jetbrains.kotlin.psi.KtExpressionWithLabel;
import org.jetbrains.kotlin.psi.KtFile;
import org.jetbrains.kotlin.psi.KtFileAnnotationList;
import org.jetbrains.kotlin.psi.KtFinallySection;
import org.jetbrains.kotlin.psi.KtForExpression;
import org.jetbrains.kotlin.psi.KtFunction;
import org.jetbrains.kotlin.psi.KtFunctionLiteral;
import org.jetbrains.kotlin.psi.KtFunctionType;
import org.jetbrains.kotlin.psi.KtIfExpression;
import org.jetbrains.kotlin.psi.KtImportAlias;
import org.jetbrains.kotlin.psi.KtImportDirective;
import org.jetbrains.kotlin.psi.KtIntersectionType;
import org.jetbrains.kotlin.psi.KtIsExpression;
import org.jetbrains.kotlin.psi.KtLabeledExpression;
import org.jetbrains.kotlin.psi.KtLambdaArgument;
import org.jetbrains.kotlin.psi.KtLambdaExpression;
import org.jetbrains.kotlin.psi.KtLiteralStringTemplateEntry;
import org.jetbrains.kotlin.psi.KtModifierList;
import org.jetbrains.kotlin.psi.KtModifierListOwner;
import org.jetbrains.kotlin.psi.KtNameReferenceExpression;
import org.jetbrains.kotlin.psi.KtNamedFunction;
import org.jetbrains.kotlin.psi.KtNullableType;
import org.jetbrains.kotlin.psi.KtObjectDeclaration;
import org.jetbrains.kotlin.psi.KtObjectLiteralExpression;
import org.jetbrains.kotlin.psi.KtOperationReferenceExpression;
import org.jetbrains.kotlin.psi.KtPackageDirective;
import org.jetbrains.kotlin.psi.KtParameter;
import org.jetbrains.kotlin.psi.KtParenthesizedExpression;
import org.jetbrains.kotlin.psi.KtPrefixExpression;
import org.jetbrains.kotlin.psi.KtPrimaryConstructor;
import org.jetbrains.kotlin.psi.KtProjectionKind;
import org.jetbrains.kotlin.psi.KtProperty;
import org.jetbrains.kotlin.psi.KtPropertyAccessor;
import org.jetbrains.kotlin.psi.KtPropertyDelegate;
import org.jetbrains.kotlin.psi.KtQualifiedExpression;
import org.jetbrains.kotlin.psi.KtReturnExpression;
import org.jetbrains.kotlin.psi.KtSafeQualifiedExpression;
import org.jetbrains.kotlin.psi.KtScript;
import org.jetbrains.kotlin.psi.KtScriptInitializer;
import org.jetbrains.kotlin.psi.KtSecondaryConstructor;
import org.jetbrains.kotlin.psi.KtSimpleNameExpression;
import org.jetbrains.kotlin.psi.KtSimpleNameStringTemplateEntry;
import org.jetbrains.kotlin.psi.KtStringInterpolationPrefix;
import org.jetbrains.kotlin.psi.KtStringTemplateEntry;
import org.jetbrains.kotlin.psi.KtStringTemplateEntryWithExpression;
import org.jetbrains.kotlin.psi.KtStringTemplateExpression;
import org.jetbrains.kotlin.psi.KtSuperExpression;
import org.jetbrains.kotlin.psi.KtSuperTypeCallEntry;
import org.jetbrains.kotlin.psi.KtSuperTypeEntry;
import org.jetbrains.kotlin.psi.KtSuperTypeListEntry;
import org.jetbrains.kotlin.psi.KtThisExpression;
import org.jetbrains.kotlin.psi.KtThrowExpression;
import org.jetbrains.kotlin.psi.KtTryExpression;
import org.jetbrains.kotlin.psi.KtTypeAlias;
import org.jetbrains.kotlin.psi.KtTypeArgumentList;
import org.jetbrains.kotlin.psi.KtTypeCodeFragment;
import org.jetbrains.kotlin.psi.KtTypeConstraint;
import org.jetbrains.kotlin.psi.KtTypeElement;
import org.jetbrains.kotlin.psi.KtTypeParameter;
import org.jetbrains.kotlin.psi.KtTypeParameterListOwner;
import org.jetbrains.kotlin.psi.KtTypeProjection;
import org.jetbrains.kotlin.psi.KtTypeReference;
import org.jetbrains.kotlin.psi.KtUnaryExpression;
import org.jetbrains.kotlin.psi.KtUserType;
import org.jetbrains.kotlin.psi.KtValueArgument;
import org.jetbrains.kotlin.psi.KtValueArgumentList;
import org.jetbrains.kotlin.psi.KtVariableDeclaration;
import org.jetbrains.kotlin.psi.KtVisitor;
import org.jetbrains.kotlin.psi.KtWhenCondition;
import org.jetbrains.kotlin.psi.KtWhenConditionWithExpression;
import org.jetbrains.kotlin.psi.KtWhenEntry;
import org.jetbrains.kotlin.psi.KtWhenExpression;
import org.jetbrains.kotlin.psi.KtWhileExpression;
import org.jetbrains.kotlin.psi.ValueArgument;
import org.jetbrains.kotlin.psi.ValueArgumentName;
import org.jetbrains.kotlin.psi.psiUtil.KtPsiUtilKt;
import org.jetbrains.kotlin.psi.psiUtil.PsiUtilsKt;
import org.jetbrains.kotlin.psi.stubs.KotlinFileStub;
import org.jetbrains.kotlin.psi.stubs.elements.KtModifierListElementType;
import org.jetbrains.kotlin.psi.stubs.elements.KtStubElementTypes;
import org.jetbrains.kotlin.types.Variance;
import org.jetbrains.kotlin.types.expressions.OperatorConventions;
import org.jetbrains.kotlin.util.OperatorNameConventions;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachementBuilderUtilsKt;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0082\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001tB!\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0014J$\u0010\u0017\u001a\u0002H\u0018\"\u0004\b\u0000\u0010\u00182\u000e\b\u0004\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001aH\u0082\b¢\u0006\u0002\u0010\u001bJ\u000e\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fJ\u001a\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\n\u0010$\u001a\u0006\u0012\u0002\b\u00030%J\u000e\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)J\u0016\u0010*\u001a\u00020+*\u00020\u00022\b\u0010,\u001a\u0004\u0018\u00010-H\u0016J\u0016\u00108\u001a\u0004\u0018\u00010\u0002*\u00020\u00022\u0006\u00109\u001a\u00020/H\u0016J\f\u0010:\u001a\u00020;*\u00020\u0002H\u0016J\u000e\u0010<\u001a\u0004\u0018\u000105*\u00020\u0002H\u0016J\u000e\u0010=\u001a\u0004\u0018\u00010\u0002*\u00020\u0002H\u0016J\u000e\u0010>\u001a\u0004\u0018\u00010\u0002*\u00020\u0002H\u0016J\u000e\u0010?\u001a\u0004\u0018\u00010\u0002*\u00020\u0002H\u0016J\u0016\u0010N\u001a\u00020O*\u00020P2\b\b\u0002\u0010Q\u001a\u00020LH\u0002J\u0010\u0010V\u001a\u00020O2\u0006\u0010W\u001a\u00020XH\u0002J\u0018\u0010^\u001a\u00020L2\u0006\u0010_\u001a\u00020\u00022\u0006\u0010`\u001a\u00020aH\u0014J9\u0010b\u001a\u00020c2\u0006\u0010_\u001a\u00020\u00022\u0006\u0010d\u001a\u00020e2\u0006\u0010f\u001a\u0002052\u0017\u0010g\u001a\u0013\u0012\u0004\u0012\u00020i\u0012\u0004\u0012\u00020\u000e0h¢\u0006\u0002\bjH\u0014Jq\u0010k\u001a\u00020l2\u0006\u0010_\u001a\u00020\u00022\u0006\u0010d\u001a\u00020e2\u0006\u0010f\u001a\u0002052\u0017\u0010m\u001a\u0013\u0012\u0004\u0012\u00020n\u0012\u0004\u0012\u00020\u000e0h¢\u0006\u0002\bj2\u0017\u0010o\u001a\u0013\u0012\u0004\u0012\u00020p\u0012\u0004\u0012\u00020\u000e0h¢\u0006\u0002\bj2\u001d\u0010q\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020s0r\u0012\u0004\u0012\u00020\u000e0h¢\u0006\u0002\bjH\u0014R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001e\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u001e\u0010.\u001a\u00020/*\u00020\u00028VX\u0096\u0004¢\u0006\f\u0012\u0004\b0\u00101\u001a\u0004\b2\u00103R\u0018\u00104\u001a\u000205*\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b6\u00107R\u001c\u0010@\u001a\u0004\u0018\u00010\u0002*\u0004\u0018\u00010\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bA\u0010BR\u001c\u0010C\u001a\u0004\u0018\u00010\u0002*\u0004\u0018\u00010\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bD\u0010BR\u001c\u0010E\u001a\u0004\u0018\u00010\u0002*\u0004\u0018\u00010\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bF\u0010BR\"\u0010G\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010H*\u0004\u0018\u00010\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bI\u0010JR\u0018\u0010K\u001a\u00020L*\u00020\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bK\u0010MR\u001e\u0010R\u001a\u0004\u0018\u00010O*\u0006\u0012\u0002\b\u00030S8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bT\u0010UR\u001a\u0010Y\u001a\u0004\u0018\u00010Z*\u00020[8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\\\u0010]¨\u0006u"}, d2 = {"Lorg/jetbrains/kotlin/fir/builder/PsiRawFirBuilder;", "Lorg/jetbrains/kotlin/fir/builder/AbstractRawFirBuilder;", "Lcom/intellij/psi/PsiElement;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "baseScopeProvider", "Lorg/jetbrains/kotlin/fir/scopes/FirScopeProvider;", "bodyBuildingMode", "Lorg/jetbrains/kotlin/fir/builder/BodyBuildingMode;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/scopes/FirScopeProvider;Lorg/jetbrains/kotlin/fir/builder/BodyBuildingMode;)V", "getBaseScopeProvider", "()Lorg/jetbrains/kotlin/fir/scopes/FirScopeProvider;", "bindFunctionTarget", Argument.Delimiters.none, "target", "Lorg/jetbrains/kotlin/fir/FirFunctionTarget;", "function", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "value", "mode", "getMode", "()Lorg/jetbrains/kotlin/fir/builder/BodyBuildingMode;", "runOnStubs", "T", "body", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "buildFirFile", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "file", "Lorg/jetbrains/kotlin/psi/KtFile;", "buildAnnotationCall", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationCall;", "annotation", "Lorg/jetbrains/kotlin/psi/KtAnnotationEntry;", "containerSymbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "buildTypeReference", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "reference", "Lorg/jetbrains/kotlin/psi/KtTypeReference;", "toFirSourceElement", "Lorg/jetbrains/kotlin/KtPsiSourceElement;", "kind", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", "elementType", "Lcom/intellij/psi/tree/IElementType;", "getElementType$annotations", "(Lcom/intellij/psi/PsiElement;)V", "getElementType", "(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/tree/IElementType;", "asText", Argument.Delimiters.none, "getAsText", "(Lcom/intellij/psi/PsiElement;)Ljava/lang/String;", "getChildNodeByType", ModuleXmlParser.TYPE, "getReferencedNameAsName", "Lorg/jetbrains/kotlin/name/Name;", "getLabelName", "getExpressionInParentheses", "getAnnotatedExpression", "getLabeledExpression", "receiverExpression", "getReceiverExpression", "(Lcom/intellij/psi/PsiElement;)Lcom/intellij/psi/PsiElement;", "selectorExpression", "getSelectorExpression", "arrayExpression", "getArrayExpression", "indexExpressions", Argument.Delimiters.none, "getIndexExpressions", "(Lcom/intellij/psi/PsiElement;)Ljava/util/List;", "isVararg", Argument.Delimiters.none, "(Lcom/intellij/psi/PsiElement;)Z", "getVisibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "Lorg/jetbrains/kotlin/psi/KtModifierListOwner;", "publicByDefault", "constructorExplicitVisibility", "Lorg/jetbrains/kotlin/psi/KtConstructor;", "getConstructorExplicitVisibility", "(Lorg/jetbrains/kotlin/psi/KtConstructor;)Lorg/jetbrains/kotlin/descriptors/Visibility;", "constructorDefaultVisibility", "owner", "Lorg/jetbrains/kotlin/psi/KtClassOrObject;", "modality", "Lorg/jetbrains/kotlin/descriptors/Modality;", "Lorg/jetbrains/kotlin/psi/KtDeclaration;", "getModality", "(Lorg/jetbrains/kotlin/psi/KtDeclaration;)Lorg/jetbrains/kotlin/descriptors/Modality;", "isReplSnippet", "script", "sourceFile", "Lorg/jetbrains/kotlin/KtSourceFile;", "convertScript", "Lorg/jetbrains/kotlin/fir/declarations/FirScript;", "scriptSource", "Lorg/jetbrains/kotlin/KtSourceElement;", "fileName", "setup", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirScriptBuilder;", "Lkotlin/ExtensionFunctionType;", "convertReplSnippet", "Lorg/jetbrains/kotlin/fir/declarations/FirReplSnippet;", "snippetSetup", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirReplSnippetBuilder;", "functionBodySetup", "Lorg/jetbrains/kotlin/fir/expressions/builder/FirBlockBuilder;", "statementsSetup", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/FirElement;", "Visitor", "org.jetbrains.kotlin:psi2fir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class PsiRawFirBuilder extends AbstractRawFirBuilder<PsiElement> {
    private final FirScopeProvider baseScopeProvider;
    private BodyBuildingMode mode;

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[BodyBuildingMode.values().length];
            try {
                iArr[BodyBuildingMode.NORMAL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[BodyBuildingMode.LAZY_BODIES.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PsiRawFirBuilder(FirSession firSession, FirScopeProvider firScopeProvider, BodyBuildingMode bodyBuildingMode) {
        super(firSession, null, 2, null);
        firSession.getClass();
        firScopeProvider.getClass();
        bodyBuildingMode.getClass();
        this.baseScopeProvider = firScopeProvider;
        this.mode = bodyBuildingMode;
    }

    public static final /* synthetic */ Modality access$getModality(PsiRawFirBuilder psiRawFirBuilder, KtDeclaration ktDeclaration) {
        return psiRawFirBuilder.getModality(ktDeclaration);
    }

    public static final /* synthetic */ Visibility access$getVisibility(PsiRawFirBuilder psiRawFirBuilder, KtModifierListOwner ktModifierListOwner, boolean z) {
        return psiRawFirBuilder.getVisibility(ktModifierListOwner, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Visibility constructorDefaultVisibility(KtClassOrObject owner) {
        if ((owner instanceof KtObjectDeclaration) || owner.hasModifier(KtTokens.ENUM_KEYWORD) || (owner instanceof KtEnumEntry)) {
            return Visibilities.Private.INSTANCE;
        }
        return owner.hasModifier(KtTokens.SEALED_KEYWORD) ? Visibilities.Protected.INSTANCE : Visibilities.Unknown.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Visibility getConstructorExplicitVisibility(KtConstructor<?> ktConstructor) {
        Visibility visibility$default = getVisibility$default(this, ktConstructor, false, 1, null);
        if (Intrinsics.areEqual(visibility$default, Visibilities.Unknown.INSTANCE)) {
            return null;
        }
        return visibility$default;
    }

    public static /* synthetic */ void getElementType$annotations(PsiElement psiElement) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Modality getModality(KtDeclaration ktDeclaration) {
        KtModifierList modifierList = ktDeclaration.getModifierList();
        if (modifierList == null) {
            return null;
        }
        if (modifierList.hasModifier(KtTokens.FINAL_KEYWORD)) {
            return Modality.FINAL;
        }
        if (modifierList.hasModifier(KtTokens.SEALED_KEYWORD)) {
            if (ktDeclaration instanceof KtClassOrObject) {
                return Modality.SEALED;
            }
            return null;
        }
        if (modifierList.hasModifier(KtTokens.ABSTRACT_KEYWORD)) {
            return Modality.ABSTRACT;
        }
        if (modifierList.hasModifier(KtTokens.OPEN_KEYWORD)) {
            return Modality.OPEN;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Visibility getVisibility(KtModifierListOwner ktModifierListOwner, boolean z) {
        KtModifierList modifierList = ktModifierListOwner.getModifierList();
        Visibility visibility = null;
        if (modifierList != null) {
            if (modifierList.hasModifier(KtTokens.PRIVATE_KEYWORD)) {
                visibility = Visibilities.Private.INSTANCE;
            } else if (modifierList.hasModifier(KtTokens.PUBLIC_KEYWORD)) {
                visibility = Visibilities.Public.INSTANCE;
            } else if (modifierList.hasModifier(KtTokens.PROTECTED_KEYWORD)) {
                visibility = Visibilities.Protected.INSTANCE;
            } else if (modifierList.hasModifier(KtTokens.INTERNAL_KEYWORD)) {
                visibility = Visibilities.Internal.INSTANCE;
            }
        }
        if (visibility == null) {
            return z ? Visibilities.Public.INSTANCE : Visibilities.Unknown.INSTANCE;
        }
        return visibility;
    }

    public static /* synthetic */ Visibility getVisibility$default(PsiRawFirBuilder psiRawFirBuilder, KtModifierListOwner ktModifierListOwner, boolean z, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: getVisibility");
            return null;
        }
        if ((i & 1) != 0) {
            z = false;
        }
        return psiRawFirBuilder.getVisibility(ktModifierListOwner, z);
    }

    public void bindFunctionTarget(FirFunctionTarget target, FirFunction function) {
        target.getClass();
        function.getClass();
        target.bind(function);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
    public final FirAnnotationCall buildAnnotationCall(KtAnnotationEntry annotation, FirBasedSymbol<?> containerSymbol) throws KotlinIllegalStateExceptionWithAttachments {
        annotation.getClass();
        containerSymbol.getClass();
        getContext().pushContainerSymbol(containerSymbol);
        try {
            FirElement firElementVisitAnnotationEntry = new Visitor().visitAnnotationEntry(annotation, (FirElement) null);
            firElementVisitAnnotationEntry.getClass();
            return (FirAnnotationCall) firElementVisitAnnotationEntry;
        } finally {
            getContext().popContainerSymbol(containerSymbol);
        }
    }

    public final FirFile buildFirFile(final KtFile file) {
        Object objDisallowTreeLoading;
        file.getClass();
        int i = WhenMappings.$EnumSwitchMapping$0[getMode().ordinal()];
        if (i == 1) {
            Object objAccept = file.accept(new Visitor(), (Object) null);
            objAccept.getClass();
            objDisallowTreeLoading = (FirFile) objAccept;
        } else {
            if (i != 2) {
                bu8.a();
                return null;
            }
            objDisallowTreeLoading = AstLoadingFilter.disallowTreeLoading(new ThrowableComputable() { // from class: org.jetbrains.kotlin.fir.builder.PsiRawFirBuilder$buildFirFile$$inlined$runOnStubs$1
                public final T compute() {
                    Object objAccept2 = file.accept(this.new Visitor(), (Object) null);
                    objAccept2.getClass();
                    return (T) ((FirFile) objAccept2);
                }
            });
        }
        return (FirFile) objDisallowTreeLoading;
    }

    public final FirTypeRef buildTypeReference(KtTypeReference reference) {
        reference.getClass();
        Object objAccept = reference.accept(new Visitor(), (Object) null);
        objAccept.getClass();
        return (FirTypeRef) objAccept;
    }

    /* JADX INFO: renamed from: convertReplSnippet, reason: avoid collision after fix types in other method */
    public FirReplSnippet convertReplSnippet2(PsiElement script, KtSourceElement scriptSource, String fileName, Function1<? super FirReplSnippetBuilder, Unit> snippetSetup, Function1<? super FirBlockBuilder, Unit> functionBodySetup, Function1<? super List<FirElement>, Unit> statementsSetup) {
        script.getClass();
        scriptSource.getClass();
        fileName.getClass();
        snippetSetup.getClass();
        functionBodySetup.getClass();
        statementsSetup.getClass();
        return new Visitor().convertReplSnippet((KtScript) script, (KtPsiSourceElement) scriptSource, fileName, snippetSetup, functionBodySetup, statementsSetup);
    }

    /* JADX INFO: renamed from: convertScript, reason: avoid collision after fix types in other method */
    public FirScript convertScript2(PsiElement script, KtSourceElement scriptSource, String fileName, Function1<? super FirScriptBuilder, Unit> setup) {
        script.getClass();
        scriptSource.getClass();
        fileName.getClass();
        setup.getClass();
        return new Visitor().convertScript((KtScript) script, (KtPsiSourceElement) scriptSource, fileName, setup);
    }

    @Override // org.jetbrains.kotlin.fir.builder.AbstractRawFirBuilder
    public PsiElement getAnnotatedExpression(PsiElement psiElement) {
        psiElement.getClass();
        return ((KtAnnotatedExpression) psiElement).getBaseExpression();
    }

    @Override // org.jetbrains.kotlin.fir.builder.AbstractRawFirBuilder
    public PsiElement getArrayExpression(PsiElement psiElement) {
        KtArrayAccessExpression ktArrayAccessExpression = psiElement instanceof KtArrayAccessExpression ? (KtArrayAccessExpression) psiElement : null;
        if (ktArrayAccessExpression != null) {
            return ktArrayAccessExpression.getArrayExpression();
        }
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.builder.AbstractRawFirBuilder
    public String getAsText(PsiElement psiElement) {
        psiElement.getClass();
        String text = psiElement.getText();
        text.getClass();
        return text;
    }

    public final FirScopeProvider getBaseScopeProvider() {
        return this.baseScopeProvider;
    }

    @Override // org.jetbrains.kotlin.fir.builder.AbstractRawFirBuilder
    public PsiElement getChildNodeByType(PsiElement psiElement, IElementType iElementType) {
        PsiElement psiElement2;
        psiElement.getClass();
        iElementType.getClass();
        PsiElement[] children = psiElement.getChildren();
        children.getClass();
        int length = children.length;
        for (int i = 0; i < length; i++) {
            psiElement2 = children[i];
            if (Intrinsics.areEqual(psiElement2.getNode().getElementType(), iElementType)) {
                return psiElement2;
            }
        }
        psiElement2 = null;
        return psiElement2;
    }

    @Override // org.jetbrains.kotlin.fir.builder.AbstractRawFirBuilder
    public IElementType getElementType(PsiElement psiElement) {
        IStubElementType elementType;
        psiElement.getClass();
        StubBasedPsiElementBase stubBasedPsiElementBase = psiElement instanceof StubBasedPsiElementBase ? (StubBasedPsiElementBase) psiElement : null;
        if (stubBasedPsiElementBase != null && (elementType = stubBasedPsiElementBase.getElementType()) != null) {
            return elementType;
        }
        IElementType elementType2 = psiElement.getNode().getElementType();
        elementType2.getClass();
        return elementType2;
    }

    @Override // org.jetbrains.kotlin.fir.builder.AbstractRawFirBuilder
    public PsiElement getExpressionInParentheses(PsiElement psiElement) {
        psiElement.getClass();
        return ((KtParenthesizedExpression) psiElement).getExpression();
    }

    @Override // org.jetbrains.kotlin.fir.builder.AbstractRawFirBuilder
    public List<PsiElement> getIndexExpressions(PsiElement psiElement) {
        KtArrayAccessExpression ktArrayAccessExpression = psiElement instanceof KtArrayAccessExpression ? (KtArrayAccessExpression) psiElement : null;
        if (ktArrayAccessExpression != null) {
            return ktArrayAccessExpression.getIndexExpressions();
        }
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.builder.AbstractRawFirBuilder
    public String getLabelName(PsiElement psiElement) {
        psiElement.getClass();
        if (psiElement instanceof KtExpressionWithLabel) {
            return ((KtExpressionWithLabel) psiElement).getLabelName();
        }
        if (!(psiElement instanceof KtNamedFunction)) {
            return null;
        }
        PsiElement parent = ((KtNamedFunction) psiElement).getParent();
        parent.getClass();
        return getLabelName(parent);
    }

    @Override // org.jetbrains.kotlin.fir.builder.AbstractRawFirBuilder
    public PsiElement getLabeledExpression(PsiElement psiElement) {
        psiElement.getClass();
        return ((KtLabeledExpression) psiElement).getBaseExpression();
    }

    public final BodyBuildingMode getMode() {
        return this.mode;
    }

    @Override // org.jetbrains.kotlin.fir.builder.AbstractRawFirBuilder
    public PsiElement getReceiverExpression(PsiElement psiElement) {
        KtQualifiedExpression ktQualifiedExpression = psiElement instanceof KtQualifiedExpression ? (KtQualifiedExpression) psiElement : null;
        if (ktQualifiedExpression != null) {
            return ktQualifiedExpression.getReceiverExpression();
        }
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.builder.AbstractRawFirBuilder
    public Name getReferencedNameAsName(PsiElement psiElement) {
        psiElement.getClass();
        return ((KtSimpleNameExpression) psiElement).getReferencedNameAsName();
    }

    @Override // org.jetbrains.kotlin.fir.builder.AbstractRawFirBuilder
    public PsiElement getSelectorExpression(PsiElement psiElement) {
        KtQualifiedExpression ktQualifiedExpression = psiElement instanceof KtQualifiedExpression ? (KtQualifiedExpression) psiElement : null;
        if (ktQualifiedExpression != null) {
            return ktQualifiedExpression.getSelectorExpression();
        }
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.builder.AbstractRawFirBuilder
    public boolean isReplSnippet(PsiElement script, KtSourceFile sourceFile) {
        script.getClass();
        sourceFile.getClass();
        KtScript ktScript = script instanceof KtScript ? (KtScript) script : null;
        return ktScript != null && ktScript.isReplSnippet();
    }

    @Override // org.jetbrains.kotlin.fir.builder.AbstractRawFirBuilder
    public boolean isVararg(PsiElement psiElement) {
        psiElement.getClass();
        KtParameter ktParameter = psiElement instanceof KtParameter ? (KtParameter) psiElement : null;
        return ktParameter != null && ktParameter.isVarArg();
    }

    @Override // org.jetbrains.kotlin.fir.builder.AbstractRawFirBuilder
    public KtPsiSourceElement toFirSourceElement(PsiElement psiElement, KtFakeSourceElementKind ktFakeSourceElementKind) {
        psiElement.getClass();
        if (ktFakeSourceElementKind == null) {
            ktFakeSourceElementKind = KtRealSourceElementKind.INSTANCE;
        }
        if (ktFakeSourceElementKind instanceof KtRealSourceElementKind) {
            return new KtRealPsiSourceElement(psiElement);
        }
        if (ktFakeSourceElementKind instanceof KtFakeSourceElementKind) {
            return new KtFakePsiSourceElement(psiElement, ktFakeSourceElementKind);
        }
        bu8.a();
        return null;
    }

    public /* synthetic */ PsiRawFirBuilder(FirSession firSession, FirScopeProvider firScopeProvider, BodyBuildingMode bodyBuildingMode, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(firSession, firScopeProvider, (i & 4) != 0 ? BodyBuildingMode.NORMAL : bodyBuildingMode);
    }

    @Override // org.jetbrains.kotlin.fir.builder.AbstractRawFirBuilder
    public /* bridge */ /* synthetic */ FirScript convertScript(PsiElement psiElement, KtSourceElement ktSourceElement, String str, Function1 function1) {
        return convertScript2(psiElement, ktSourceElement, str, (Function1<? super FirScriptBuilder, Unit>) function1);
    }

    @Override // org.jetbrains.kotlin.fir.builder.AbstractRawFirBuilder
    public /* bridge */ /* synthetic */ FirReplSnippet convertReplSnippet(PsiElement psiElement, KtSourceElement ktSourceElement, String str, Function1 function1, Function1 function2, Function1 function3) {
        return convertReplSnippet2(psiElement, ktSourceElement, str, (Function1<? super FirReplSnippetBuilder, Unit>) function1, (Function1<? super FirBlockBuilder, Unit>) function2, (Function1<? super List<FirElement>, Unit>) function3);
    }

    @Metadata(d1 = {"\u0000Ô\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0094\u0004\u0018\u00002\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\b\u0012\u0004\u0012\u00020\u00040\u0003B\u0007¢\u0006\u0004\b\u0005\u0010\u0006J \u0010\u001c\u001a\u00020\u001d*\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u001f2\n\u0010 \u001a\u0006\u0012\u0002\b\u00030!H\u0016J \u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u00172\f\u0010%\u001a\b\u0012\u0004\u0012\u00020#0&H\u0016J\u001e\u0010'\u001a\u0002H(\"\n\b\u0000\u0010(\u0018\u0001*\u00020\u0002*\u00020)H\u0082\b¢\u0006\u0002\u0010*J2\u0010+\u001a\u0002H,\"\u0004\b\u0000\u0010,2\f\u0010-\u001a\b\u0012\u0004\u0012\u0002H,0&2\u000e\b\b\u0010.\u001a\b\u0012\u0004\u0012\u0002H,0&H\u0082\b¢\u0006\u0002\u0010/J!\u00100\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u00172\f\u0010%\u001a\b\u0012\u0004\u0012\u00020#0&H\u0082\bJ\u0017\u00101\u001a\u0002022\f\u00103\u001a\b\u0012\u0004\u0012\u0002020&H\u0082\bJ'\u00104\u001a\u0002052\u0006\u00106\u001a\u00020\u00142\u0006\u00107\u001a\u00020\b2\f\u00108\u001a\b\u0012\u0004\u0012\u0002050&H\u0082\bJ\u001c\u00109\u001a\u0004\u0018\u00010\u00022\u0006\u0010:\u001a\u00020)2\n\b\u0002\u0010;\u001a\u0004\u0018\u00010\u0002J\u001c\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020?2\f\u0010@\u001a\b\u0012\u0002\b\u0003\u0018\u00010AJ\u000e\u0010B\u001a\u00020\b*\u0004\u0018\u00010CH\u0002J\u000e\u0010D\u001a\u00020\b*\u0004\u0018\u00010CH\u0002J\u000e\u0010E\u001a\u00020\b*\u0004\u0018\u00010CH\u0004J\u001e\u0010F\u001a\u00020#*\u0004\u0018\u00010)2\u0006\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020)H\u0002J\u0014\u0010F\u001a\u00020#*\u00020)2\u0006\u0010G\u001a\u00020HH\u0002JP\u0010F\u001a\u00020#*\u0004\u0018\u00010)2\u0006\u0010I\u001a\u00020)2\u0014\b\u0002\u0010J\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\u00140K2!\u0010L\u001a\u001d\u0012\u0013\u0012\u00110\u0014¢\u0006\f\bM\u0012\b\b\u000b\u0012\u0004\b\b(N\u0012\u0004\u0012\u00020O0KH\u0082\bJ\u0014\u0010P\u001a\u00020#*\u00020)2\u0006\u0010Q\u001a\u00020#H\u0002J\u001b\u0010R\u001a\u00020S*\u00020T2\f\u0010U\u001a\b\u0012\u0004\u0012\u00020H0&H\u0082\bJ\f\u0010R\u001a\u00020S*\u00020TH\u0002J:\u0010V\u001a\u00020W*\u00020X2\u0006\u0010Y\u001a\u00020\b2\u0006\u0010Z\u001a\u00020[2\u0006\u0010\\\u001a\u00020]2\u0006\u0010^\u001a\u00020_2\f\u0010`\u001a\b\u0012\u0004\u0012\u00020b0aH\u0002J\u000e\u0010c\u001a\u000202*\u0004\u0018\u00010TH\u0002J\u001c\u0010d\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u000102\u0012\u0006\u0012\u0004\u0018\u00010f0e*\u00020gH\u0002J\u0010\u0010h\u001a\u00020\u00142\u0006\u0010i\u001a\u00020jH\u0002J\u0010\u0010k\u001a\u00020\u00142\u0006\u0010i\u001a\u00020jH\u0002J\f\u0010F\u001a\u00020#*\u00020lH\u0002JT\u0010m\u001a\u0004\u0018\u00010n*\u0004\u0018\u00010o2\u0006\u0010>\u001a\u00020?2\u0006\u0010p\u001a\u00020\b2\u0006\u0010q\u001a\u00020r2\u0006\u0010s\u001a\u00020\u00142\f\u0010t\u001a\b\u0012\u0004\u0012\u00020u0a2\f\u0010v\u001a\b\u0012\u0004\u0012\u00020u0a2\u0006\u0010w\u001a\u00020\u0014H\u0002J\"\u0010x\u001a\u00020y2\u0006\u0010z\u001a\u00020{2\b\u0010|\u001a\u0004\u0018\u00010X2\u0006\u0010>\u001a\u00020?H\u0002J7\u0010}\u001a\u00020~*\u0004\u0018\u00010\u007f2\u0006\u0010>\u001a\u00020?2\u0006\u0010q\u001a\u00020r2\u0007\u0010\u0080\u0001\u001a\u00020\b2\u000e\u0010\u0081\u0001\u001a\t\u0012\u0005\u0012\u00030\u0082\u00010aH\u0002JB\u0010\u0083\u0001\u001a\u00030\u0084\u0001*\u00030\u0085\u00012\t\u0010\u0086\u0001\u001a\u0004\u0018\u00010\b2\u000b\u0010\u0087\u0001\u001a\u0006\u0012\u0002\b\u00030!2\b\u0010\u0088\u0001\u001a\u00030\u0089\u00012\u000f\b\u0002\u0010\u008a\u0001\u001a\b\u0012\u0004\u0012\u00020u0aH\u0002J!\u0010\u008b\u0001\u001a\u00020\u001d*\u00030\u008c\u00012\b\u0010\u008d\u0001\u001a\u00030\u0085\u00012\u0007\u0010\u008e\u0001\u001a\u00020\u0014H\u0002J\u0018\u0010\u008f\u0001\u001a\u00020=*\u00030\u0085\u00012\b\u0010\u0090\u0001\u001a\u00030\u0084\u0001H\u0002J\u0018\u0010\u0091\u0001\u001a\u00020\u001d*\u00030\u0092\u00012\b\u0010\u0093\u0001\u001a\u00030\u0094\u0001H\u0002J\u0017\u0010\u001c\u001a\u00020\u001d*\u00030\u0094\u00012\b\u0010\u0095\u0001\u001a\u00030\u0096\u0001H\u0002J\u0016\u0010\u001c\u001a\u00020\u001d*\u00030\u0094\u00012\u0007\u0010\u0095\u0001\u001a\u00020\u001fH\u0002J\"\u0010\u0097\u0001\u001a\t\u0012\u0004\u0012\u00020b0\u0098\u0001*\u00030\u0099\u00012\u000b\u0010\u009a\u0001\u001a\u0006\u0012\u0002\b\u00030!H\u0002J%\u0010\u009b\u0001\u001a\u00020\u001d*\u00030\u0099\u00012\b\u0010\u0095\u0001\u001a\u00030\u009c\u00012\u000b\u0010\u009a\u0001\u001a\u0006\u0012\u0002\b\u00030!H\u0002J!\u0010\u009d\u0001\u001a\u00030\u009e\u00012\b\u0010\u009f\u0001\u001a\u00030 \u00012\u000b\u0010\u009a\u0001\u001a\u0006\u0012\u0002\b\u00030!H\u0002J\u001e\u0010¡\u0001\u001a\u00020\u00022\b\u0010\u009f\u0001\u001a\u00030 \u00012\t\u0010¢\u0001\u001a\u0004\u0018\u00010\u0002H\u0016J,\u0010£\u0001\u001a\u00020\u001d\"\r\b\u0000\u0010,*\u00020W*\u00030¤\u0001*\u00030\u0099\u00012\u0007\u0010¥\u0001\u001a\u0002H,H\u0002¢\u0006\u0003\u0010¦\u0001JM\u0010§\u0001\u001a\u00020\u001d*\u00020g2\b\u0010\u0095\u0001\u001a\u00030¨\u00012\f\u0010©\u0001\u001a\u0007\u0012\u0002\b\u00030ª\u00012\b\u0010\u0088\u0001\u001a\u00030\u0089\u00012\u000b\b\u0002\u0010\u0086\u0001\u001a\u0004\u0018\u00010\b2\u000f\b\u0002\u0010\u008a\u0001\u001a\b\u0012\u0004\u0012\u00020u0aH\u0002J\u0018\u0010«\u0001\u001a\u00020\u001d*\u00030¬\u00012\b\u0010\u0095\u0001\u001a\u00030\u00ad\u0001H\u0002J'\u0010®\u0001\u001a\u00030¯\u00012\b\u0010°\u0001\u001a\u00030±\u00012\u0007\u0010²\u0001\u001a\u00020\b2\b\u0010³\u0001\u001a\u00030´\u0001H\u0004Jk\u0010µ\u0001\u001a\u001f\u0012\u0004\u0012\u00020\b\u0012\u0015\u0012\u0013\u0012\u0005\u0012\u00030´\u0001\u0012\u0005\u0012\u00030·\u0001\u0018\u00010¶\u00010e*\u00020]2\u0007\u0010\u0095\u0001\u001a\u00020_2\t\u0010¸\u0001\u001a\u0004\u0018\u00010\b2\t\u0010¹\u0001\u001a\u0004\u0018\u00010\b2\b\u0010º\u0001\u001a\u00030»\u00012\r\u0010¼\u0001\u001a\b\u0012\u0004\u0012\u00020b0a2\u0007\u0010½\u0001\u001a\u00020\u0014H\u0002J\u0096\u0001\u0010¾\u0001\u001a\u00030¿\u0001*\u0005\u0018\u00010À\u00012\n\u0010Á\u0001\u001a\u0005\u0018\u00010Â\u00012\t\u0010Ã\u0001\u001a\u0004\u0018\u00010\b2\u0007\u0010¸\u0001\u001a\u00020\b2\u0006\u0010\\\u001a\u00020]2\f\u0010`\u001a\b\u0012\u0004\u0012\u00020b0a2\u001a\u0010Ä\u0001\u001a\u0015\u0012\u0011\u0012\u000f\u0012\u0005\u0012\u00030Â\u0001\u0012\u0004\u0012\u00020\b0e0a2\u0007\u0010½\u0001\u001a\u00020\u00142\u0007\u0010Å\u0001\u001a\u00020\u00142\t\b\u0002\u0010Æ\u0001\u001a\u00020\u00142\t\b\u0002\u0010Ç\u0001\u001a\u00020\u00142\t\b\u0002\u0010È\u0001\u001a\u00020\u0014H\u0004J\u0010\u0010É\u0001\u001a\u0005\u0018\u00010Ê\u0001*\u00020]H\u0002J\u001e\u0010Ë\u0001\u001a\u00020\u00022\b\u0010Ì\u0001\u001a\u00030Í\u00012\t\u0010¢\u0001\u001a\u0004\u0018\u00010\u0002H\u0016J\u0014\u0010Ò\u0001\u001a\u00030Ï\u00012\b\u0010Ó\u0001\u001a\u00030Ô\u0001H\u0002J\u0014\u0010Õ\u0001\u001a\u00030Ö\u00012\b\u0010×\u0001\u001a\u00030Ø\u0001H\u0004JA\u0010Ù\u0001\u001a\u00030Ú\u00012\b\u0010Û\u0001\u001a\u00030Ü\u00012\b\u0010Ý\u0001\u001a\u00030Þ\u00012\u0007\u0010ß\u0001\u001a\u00020H2\u001a\u0010à\u0001\u001a\u0015\u0012\u0005\u0012\u00030á\u0001\u0012\u0004\u0012\u00020\u001d0K¢\u0006\u0003\bâ\u0001J\u007f\u0010ã\u0001\u001a\u00030ä\u00012\b\u0010Û\u0001\u001a\u00030Ü\u00012\b\u0010Ý\u0001\u001a\u00030Þ\u00012\u0007\u0010ß\u0001\u001a\u00020H2\u001a\u0010å\u0001\u001a\u0015\u0012\u0005\u0012\u00030æ\u0001\u0012\u0004\u0012\u00020\u001d0K¢\u0006\u0003\bâ\u00012\u001a\u0010ç\u0001\u001a\u0015\u0012\u0005\u0012\u00030è\u0001\u0012\u0004\u0012\u00020\u001d0K¢\u0006\u0003\bâ\u00012 \u0010é\u0001\u001a\u001b\u0012\u000b\u0012\t\u0012\u0004\u0012\u00020\u00020\u0098\u0001\u0012\u0004\u0012\u00020\u001d0K¢\u0006\u0003\bâ\u0001JI\u0010ê\u0001\u001a\u00030ë\u00012\b\u0010Û\u0001\u001a\u00030Ü\u00012\b\u0010ì\u0001\u001a\u00030í\u00012\r\u0010î\u0001\u001a\b\u0012\u0004\u0012\u00020\u00020a2\u001a\u0010ç\u0001\u001a\u0015\u0012\u0005\u0012\u00030è\u0001\u0012\u0004\u0012\u00020\u001d0K¢\u0006\u0003\bâ\u0001H\u0002J\u0011\u0010ï\u0001\u001a\u00020\u00022\u0006\u0010:\u001a\u00020\u0002H\u0002J9\u0010ð\u0001\u001a\b\u0012\u0004\u0012\u00020\u00020a2\b\u0010Û\u0001\u001a\u00030Ü\u00012\b\u0010\u0087\u0001\u001a\u00030ñ\u00012\u0014\u0010ò\u0001\u001a\u000f\u0012\u0004\u0012\u00020r\u0012\u0004\u0012\u00020=0ó\u0001H\u0002J\u001e\u0010ô\u0001\u001a\u00030õ\u00012\b\u0010Ì\u0001\u001a\u00030ö\u00012\b\u0010÷\u0001\u001a\u00030ø\u0001H\u0002J\u0013\u0010ù\u0001\u001a\u0002022\b\u0010Ì\u0001\u001a\u00030ú\u0001H\u0002J \u0010û\u0001\u001a\u00020W*\u00030ü\u00012\u0007\u0010ý\u0001\u001a\u00020[2\u0007\u0010þ\u0001\u001a\u00020\u0014H\u0004J2\u0010ÿ\u0001\u001a\u00020\u001d*\n\u0012\u0005\u0012\u00030\u0084\u00010\u0098\u00012\u000e\u0010\u0080\u0002\u001a\t\u0012\u0005\u0012\u00030\u0081\u00020a2\u000b\u0010\u0087\u0001\u001a\u0006\u0012\u0002\b\u00030!H\u0002J\u001d\u0010\u0082\u0002\u001a\u00020\u00022\u0007\u0010\u0083\u0002\u001a\u00020]2\t\u0010¢\u0001\u001a\u0004\u0018\u00010\u0002H\u0016J;\u0010\u0084\u0002\u001a\u00020\u001d*\u00020_2\n\u0010\u0085\u0002\u001a\u0005\u0018\u00010\u0086\u00022\u0006\u0010Y\u001a\u00020\b2\u0006\u0010Z\u001a\u00020[2\u0006\u0010\\\u001a\u00020]2\b\u0010\u0087\u0002\u001a\u00030\u0088\u0002H\u0002J\u001e\u0010\u0089\u0002\u001a\u00020\u00022\b\u0010\u008a\u0002\u001a\u00030\u008b\u00022\t\u0010¢\u0001\u001a\u0004\u0018\u00010\u0002H\u0016J\u001e\u0010\u008c\u0002\u001a\u00020\u00022\b\u0010\u008d\u0002\u001a\u00030\u008e\u00022\t\u0010¢\u0001\u001a\u0004\u0018\u00010\u0002H\u0016J\u001e\u0010\u008f\u0002\u001a\u00020\u00022\b\u0010\u0090\u0002\u001a\u00030\u0091\u00022\t\u0010¢\u0001\u001a\u0004\u0018\u00010\u0002H\u0016J\u000f\u0010\u0092\u0002\u001a\u0004\u0018\u00010f*\u00020gH\u0002J\u001e\u0010\u0093\u0002\u001a\u00020\u001d*\u00030\u0094\u00022\u000e\u0010\u0095\u0002\u001a\t\u0012\u0004\u0012\u00020#0\u0098\u0001H\u0002J\u001e\u0010\u0096\u0002\u001a\u00020\u00022\b\u0010\u008a\u0002\u001a\u00030\u0097\u00022\t\u0010¢\u0001\u001a\u0004\u0018\u00010\u0002H\u0016J7\u0010¾\u0001\u001a\u00030¿\u0001*\u00030\u0098\u00022\u0007\u0010\u0099\u0002\u001a\u00020\b2\u0007\u0010\u009a\u0002\u001a\u00020\b2\u0006\u0010\\\u001a\u00020]2\f\u0010`\u001a\b\u0012\u0004\u0012\u00020b0aH\u0004J\u0016\u0010'\u001a\u000205*\u00030\u009b\u00022\u0007\u0010\u009c\u0002\u001a\u00020\bH\u0002J\u0010\u0010\u009d\u0002\u001a\u0004\u0018\u00010#*\u00030\u009e\u0002H\u0004J1\u0010\u008f\u0001\u001a\u00020=\"\u0004\b\u0000\u0010,*\u00020?2\f\u0010@\u001a\b\u0012\u0002\b\u0003\u0018\u00010A2\u000e\u0010\u009f\u0002\u001a\t\u0012\u0004\u0012\u0002H,0 \u0002H\u0002J7\u0010¡\u0002\u001a\u00030¢\u00022\b\u0010£\u0002\u001a\u00030¤\u00022\u000b\u0010\u0087\u0001\u001a\u0006\u0012\u0002\b\u00030!2\t\b\u0002\u0010¥\u0002\u001a\u00020\u00142\t\b\u0002\u0010¦\u0002\u001a\u00020\u0014H\u0004J\u001c\u0010§\u0002\u001a\u00020\u00022\u0006\u0010>\u001a\u00020?2\t\u0010¢\u0001\u001a\u0004\u0018\u00010\u0002H\u0016J\u001d\u0010¨\u0002\u001a\u00020\u00022\u0007\u0010©\u0002\u001a\u00020C2\t\u0010¢\u0001\u001a\u0004\u0018\u00010\u0002H\u0016J\r\u0010ª\u0002\u001a\u00020\b*\u00020CH\u0002J0\u0010«\u0002\u001a\u00030¬\u00022\u0007\u0010\u0016\u001a\u00030Þ\u00012\u0007\u0010\u00ad\u0002\u001a\u00020\u00142\b\u0010®\u0002\u001a\u00030¯\u00022\b\u0010°\u0002\u001a\u00030±\u0002H\u0002J\u001e\u0010²\u0002\u001a\u00020\u00022\b\u0010³\u0002\u001a\u00030´\u00022\t\u0010¢\u0001\u001a\u0004\u0018\u00010\u0002H\u0016J\u001e\u0010µ\u0002\u001a\u00020\u00022\b\u0010¶\u0002\u001a\u00030·\u00022\t\u0010¢\u0001\u001a\u0004\u0018\u00010\u0002H\u0016J\u001e\u0010¸\u0002\u001a\u00020\u00022\b\u0010\u008a\u0002\u001a\u00030¹\u00022\t\u0010¢\u0001\u001a\u0004\u0018\u00010\u0002H\u0016J\"\u0010º\u0002\u001a\u00030è\u00012\b\u0010\u008a\u0002\u001a\u00030¹\u00022\f\b\u0002\u0010»\u0002\u001a\u0005\u0018\u00010¼\u0002H\u0002J\u001e\u0010½\u0002\u001a\u00020\u00022\b\u0010\u008a\u0002\u001a\u00030±\u00022\t\u0010¢\u0001\u001a\u0004\u0018\u00010\u0002H\u0016J\u001e\u0010¾\u0002\u001a\u00020\u00022\b\u0010\u008a\u0002\u001a\u00030¿\u00022\t\u0010¢\u0001\u001a\u0004\u0018\u00010\u0002H\u0016J\u001e\u0010À\u0002\u001a\u00020\u00022\b\u0010\u008a\u0002\u001a\u00030Á\u00022\t\u0010¢\u0001\u001a\u0004\u0018\u00010\u0002H\u0016J\u001e\u0010Â\u0002\u001a\u00020\u00022\b\u0010\u008a\u0002\u001a\u00030Ã\u00022\t\u0010¢\u0001\u001a\u0004\u0018\u00010\u0002H\u0016J\u001e\u0010Ä\u0002\u001a\u00020\u00022\b\u0010\u008a\u0002\u001a\u00030Å\u00022\t\u0010¢\u0001\u001a\u0004\u0018\u00010\u0002H\u0016J\u001e\u0010Æ\u0002\u001a\u00020\u00022\b\u0010\u008a\u0002\u001a\u00030Ç\u00022\t\u0010¢\u0001\u001a\u0004\u0018\u00010\u0002H\u0016J\u001e\u0010È\u0002\u001a\u00020\u00022\b\u0010\u008a\u0002\u001a\u00030É\u00022\t\u0010¢\u0001\u001a\u0004\u0018\u00010\u0002H\u0016J\u001e\u0010Í\u0002\u001a\u00020\u00022\b\u0010\u008a\u0002\u001a\u00030Î\u00022\t\u0010¢\u0001\u001a\u0004\u0018\u00010\u0002H\u0016J\u001e\u0010Ï\u0002\u001a\u00020\u00022\b\u0010\u008a\u0002\u001a\u00030Ð\u00022\t\u0010¢\u0001\u001a\u0004\u0018\u00010\u0002H\u0016J\u001e\u0010Ñ\u0002\u001a\u00020\u00022\b\u0010\u008a\u0002\u001a\u00030Ò\u00022\t\u0010¢\u0001\u001a\u0004\u0018\u00010\u0002H\u0016J\u001e\u0010Ó\u0002\u001a\u00020\u00022\b\u0010\u008a\u0002\u001a\u00030Ô\u00022\t\u0010¢\u0001\u001a\u0004\u0018\u00010\u0002H\u0016J\u001e\u0010Õ\u0002\u001a\u00020\u00022\b\u0010\u008a\u0002\u001a\u00030Ö\u00022\t\u0010¢\u0001\u001a\u0004\u0018\u00010\u0002H\u0016J\u001e\u0010×\u0002\u001a\u00020\u00022\b\u0010\u008a\u0002\u001a\u00030Ø\u00022\t\u0010¢\u0001\u001a\u0004\u0018\u00010\u0002H\u0016J\u0013\u0010Ù\u0002\u001a\u00020\u00022\b\u0010\u008a\u0002\u001a\u00030Ø\u0002H\u0002J\u001e\u0010Ú\u0002\u001a\u00020\u00022\b\u0010\u008a\u0002\u001a\u00030Û\u00022\t\u0010¢\u0001\u001a\u0004\u0018\u00010\u0002H\u0016J\u001e\u0010Ü\u0002\u001a\u00020\u00022\b\u0010\u008a\u0002\u001a\u00030Ý\u00022\t\u0010¢\u0001\u001a\u0004\u0018\u00010\u0002H\u0016J\u001e\u0010Þ\u0002\u001a\u00020\u00022\b\u0010\u008a\u0002\u001a\u00030ß\u00022\t\u0010¢\u0001\u001a\u0004\u0018\u00010\u0002H\u0016J\u001f\u0010à\u0002\u001a\u00030á\u00022\t\u0010â\u0002\u001a\u0004\u0018\u00010T2\b\u0010ã\u0002\u001a\u00030Þ\u0001H\u0002J\u001e\u0010ä\u0002\u001a\u00020\u00022\b\u0010\u008a\u0002\u001a\u00030å\u00022\t\u0010¢\u0001\u001a\u0004\u0018\u00010\u0002H\u0016J\u001e\u0010æ\u0002\u001a\u00020\u00022\b\u0010\u008a\u0002\u001a\u00030ç\u00022\t\u0010¢\u0001\u001a\u0004\u0018\u00010\u0002H\u0016J\u001e\u0010è\u0002\u001a\u00020\u00022\b\u0010\u008a\u0002\u001a\u00030é\u00022\t\u0010¢\u0001\u001a\u0004\u0018\u00010\u0002H\u0016J\u001e\u0010ê\u0002\u001a\u00020\u00022\b\u0010\u008a\u0002\u001a\u00030ë\u00022\t\u0010¢\u0001\u001a\u0004\u0018\u00010\u0002H\u0016J\u001e\u0010ì\u0002\u001a\u00020\u00022\b\u0010\u008a\u0002\u001a\u00030í\u00022\t\u0010¢\u0001\u001a\u0004\u0018\u00010\u0002H\u0016J\u001e\u0010î\u0002\u001a\u00020\u00022\b\u0010\u008a\u0002\u001a\u00030ï\u00022\t\u0010¢\u0001\u001a\u0004\u0018\u00010\u0002H\u0016J\u001e\u0010ð\u0002\u001a\u00020\u00022\b\u0010\u008a\u0002\u001a\u00030ñ\u00022\t\u0010¢\u0001\u001a\u0004\u0018\u00010\u0002H\u0016J\u001e\u0010ò\u0002\u001a\u00020\u00022\b\u0010\u008a\u0002\u001a\u00030ó\u00022\t\u0010¢\u0001\u001a\u0004\u0018\u00010\u0002H\u0016J\u001e\u0010ô\u0002\u001a\u00020\u00022\b\u0010\u008a\u0002\u001a\u00030õ\u00022\t\u0010¢\u0001\u001a\u0004\u0018\u00010\u0002H\u0016J\u001e\u0010ö\u0002\u001a\u00020\u00022\b\u0010÷\u0002\u001a\u00030Ø\u00012\t\u0010¢\u0001\u001a\u0004\u0018\u00010\u0002H\u0016J\u001e\u0010ø\u0002\u001a\u00020\u00022\b\u0010\u008a\u0002\u001a\u00030ù\u00022\t\u0010¢\u0001\u001a\u0004\u0018\u00010\u0002H\u0016J\u001e\u0010ú\u0002\u001a\u00020\u00022\b\u0010\u008a\u0002\u001a\u00030û\u00022\t\u0010¢\u0001\u001a\u0004\u0018\u00010\u0002H\u0016J\u001e\u0010\u0080\u0003\u001a\u00020\u00022\b\u0010\u008a\u0002\u001a\u00030\u0081\u00032\t\u0010¢\u0001\u001a\u0004\u0018\u00010\u0002H\u0016J\u001d\u0010\u0082\u0003\u001a\u00020\u00022\u0007\u0010\u008a\u0002\u001a\u00020T2\t\u0010¢\u0001\u001a\u0004\u0018\u00010\u0002H\u0016J%\u0010\u0083\u0003\u001a\u00020\u001d*\n\u0012\u0005\u0012\u00030\u0084\u00030\u0098\u00012\u000e\u0010\u0085\u0003\u001a\t\u0012\u0005\u0012\u00030·\u00020aH\u0002J\u0014\u0010\u0086\u0003\u001a\u00030\u0087\u00032\b\u0010\u0088\u0003\u001a\u00030\u0089\u0003H\u0002R\u0018\u0010\u0007\u001a\u00020\b*\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u001e\u0010\u000b\u001a\u00020\f*\u00020\u00048VX\u0096\u0004¢\u0006\f\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u0004\u0018\u00010\f*\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0010R\u0018\u0010\u0013\u001a\u00020\u0014*\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0015R\u0018\u0010\u0016\u001a\u00020\u0017*\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u0004\u0018\u00010\u0017*\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0019R\u001d\u0010Î\u0001\u001a\u00030Ï\u0001*\u00030Í\u00018BX\u0082\u0004¢\u0006\b\u001a\u0006\bÐ\u0001\u0010Ñ\u0001R\u001b\u0010Ê\u0002\u001a\u00020\u0014*\u00020T8BX\u0082\u0004¢\u0006\b\u001a\u0006\bË\u0002\u0010Ì\u0002R\u001f\u0010ü\u0002\u001a\u0005\u0018\u00010ý\u0002*\u00030û\u00028BX\u0082\u0004¢\u0006\b\u001a\u0006\bþ\u0002\u0010ÿ\u0002¨\u0006\u008a\u0003"}, d2 = {"Lorg/jetbrains/kotlin/fir/builder/PsiRawFirBuilder$Visitor;", "Lorg/jetbrains/kotlin/psi/KtVisitor;", "Lorg/jetbrains/kotlin/fir/FirElement;", "Lorg/jetbrains/kotlin/fir/builder/DestructuringContext;", "Lorg/jetbrains/kotlin/psi/KtDestructuringDeclarationEntry;", "<init>", "(Lorg/jetbrains/kotlin/fir/builder/PsiRawFirBuilder;)V", "returnTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "getReturnTypeRef", "(Lorg/jetbrains/kotlin/psi/KtDestructuringDeclarationEntry;)Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "getName$annotations", "(Lorg/jetbrains/kotlin/psi/KtDestructuringDeclarationEntry;)V", "getName", "(Lorg/jetbrains/kotlin/psi/KtDestructuringDeclarationEntry;)Lorg/jetbrains/kotlin/name/Name;", "initializerName", "getInitializerName", "isVar", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/psi/KtDestructuringDeclarationEntry;)Z", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "(Lorg/jetbrains/kotlin/psi/KtDestructuringDeclarationEntry;)Lorg/jetbrains/kotlin/KtSourceElement;", "initializerSource", "getInitializerSource", "extractAnnotationsTo", Argument.Delimiters.none, "target", "Lorg/jetbrains/kotlin/fir/builder/FirAnnotationContainerBuilder;", "containerSymbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "interceptExpressionBuilding", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "sourceElement", "buildExpression", "Lkotlin/Function0;", "convert", "R", "Lorg/jetbrains/kotlin/psi/KtElement;", "(Lorg/jetbrains/kotlin/psi/KtElement;)Lorg/jetbrains/kotlin/fir/FirElement;", "buildOrLazy", "T", "build", "lazy", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "buildOrLazyExpression", "buildOrLazyBlock", "Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "buildBlock", "buildOrLazyDelegatedConstructorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirDelegatedConstructorCall;", "isThis", "constructedTypeRef", "buildCall", "convertElement", "element", "original", "convertProperty", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "property", "Lorg/jetbrains/kotlin/psi/KtProperty;", "ownerRegularOrAnonymousObjectSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "toFirOrImplicitType", "Lorg/jetbrains/kotlin/psi/KtTypeReference;", "toFirOrUnitType", "toFirOrErrorType", "toFirExpression", "errorReason", Argument.Delimiters.none, "sourceWhenInvalidExpression", "isValidExpression", "Lkotlin/Function1;", "diagnosticFn", "Lkotlin/ParameterName;", "missing", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "checkSelectorInvariant", CoroutineCodegenUtilKt.CONTINUATION_RESULT_FIELD_NAME, "toFirStatement", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "Lorg/jetbrains/kotlin/psi/KtExpression;", "errorReasonLazy", "toFirDeclaration", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "Lorg/jetbrains/kotlin/psi/KtDeclaration;", "delegatedSuperType", "delegatedSelfType", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "owner", "Lorg/jetbrains/kotlin/psi/KtClassOrObject;", "ownerClassBuilder", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirClassBuilder;", "ownerTypeParameters", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRef;", "toFirBlock", "buildFirBody", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/fir/contracts/FirContractDescription;", "Lorg/jetbrains/kotlin/psi/KtDeclarationWithBody;", "isCallTheFirstStatement", "psi", "Lcom/intellij/psi/PsiElement;", "functionCallHasLabel", "Lorg/jetbrains/kotlin/psi/ValueArgument;", "toFirPropertyAccessor", "Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;", "Lorg/jetbrains/kotlin/psi/KtPropertyAccessor;", "propertyTypeRef", "propertySymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "isGetter", "accessorAnnotationsFromProperty", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "parameterAnnotationsFromProperty", "isCompanionBlockMember", "obtainPropertyComponentStatus", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;", "componentVisibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "declaration", "toFirBackingField", "Lorg/jetbrains/kotlin/fir/declarations/FirBackingField;", "Lorg/jetbrains/kotlin/psi/KtBackingField;", "propertyReturnType", "annotationsFromProperty", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationCall;", "toFirValueParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "Lorg/jetbrains/kotlin/psi/KtParameter;", "defaultTypeRef", "containingDeclarationSymbol", "valueParameterDeclaration", "Lorg/jetbrains/kotlin/fir/builder/AbstractRawFirBuilder$ValueParameterDeclaration;", "additionalAnnotations", "addAnnotationsFrom", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirValueParameterBuilder;", "ktParameter", "isFromPrimaryConstructor", "toFirProperty", "firParameter", "extractAnnotationsFrom", "Lorg/jetbrains/kotlin/fir/declarations/impl/FirDefaultPropertyAccessor;", "annotated", "Lorg/jetbrains/kotlin/psi/KtAnnotated;", "container", "Lorg/jetbrains/kotlin/fir/FirAnnotationContainer;", "convertTypeParameters", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/psi/KtTypeParameterListOwner;", "declarationSymbol", "extractTypeParametersTo", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirTypeParametersOwnerBuilder;", "extractTypeParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;", "parameter", "Lorg/jetbrains/kotlin/psi/KtTypeParameter;", "visitTypeParameter", "data", "fillDanglingConstraintsTo", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRefsOwner;", "to", "(Lorg/jetbrains/kotlin/psi/KtTypeParameterListOwner;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)V", "extractValueParametersTo", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirFunctionBuilder;", "functionSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;", "extractArgumentsTo", "Lorg/jetbrains/kotlin/psi/KtCallElement;", "Lorg/jetbrains/kotlin/fir/expressions/builder/FirCallBuilder;", "buildFieldForSupertypeDelegate", "Lorg/jetbrains/kotlin/fir/declarations/FirField;", "entry", "Lorg/jetbrains/kotlin/psi/KtDelegatedSuperTypeEntry;", ModuleXmlParser.TYPE, "fieldOrd", Argument.Delimiters.none, "extractSuperTypeListEntriesTo", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFieldSymbol;", "delegatedSelfTypeRef", "delegatedEnumSuperTypeRef", "classKind", "Lorg/jetbrains/kotlin/descriptors/ClassKind;", "containerTypeParameters", "containingClassIsExpectClass", "toFirConstructor", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "Lorg/jetbrains/kotlin/psi/KtPrimaryConstructor;", "superTypeCallEntry", "Lorg/jetbrains/kotlin/psi/KtSuperTypeCallEntry;", "delegatedSuperTypeRef", "allSuperTypeCallEntries", "copyConstructedTypeRefWithImplicitSource", "isErrorConstructor", "isImplicitlyActual", "isKotlinAny", "obtainDispatchReceiverForConstructor", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "visitKtFile", "file", "Lorg/jetbrains/kotlin/psi/KtFile;", "properPackageFqName", "Lorg/jetbrains/kotlin/name/FqName;", "getProperPackageFqName", "(Lorg/jetbrains/kotlin/psi/KtFile;)Lorg/jetbrains/kotlin/name/FqName;", "parsePackageName", "node", "Lorg/jetbrains/kotlin/psi/KtPackageDirective;", "buildScriptDestructuringDeclaration", "Lorg/jetbrains/kotlin/fir/declarations/FirVariable;", "destructuringDeclaration", "Lorg/jetbrains/kotlin/psi/KtDestructuringDeclaration;", "convertScript", "Lorg/jetbrains/kotlin/fir/declarations/FirScript;", "script", "Lorg/jetbrains/kotlin/psi/KtScript;", "scriptSource", "Lorg/jetbrains/kotlin/KtPsiSourceElement;", "fileName", "setup", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirScriptBuilder;", "Lkotlin/ExtensionFunctionType;", "convertReplSnippet", "Lorg/jetbrains/kotlin/fir/declarations/FirReplSnippet;", "snippetSetup", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirReplSnippetBuilder;", "functionBodySetup", "Lorg/jetbrains/kotlin/fir/expressions/builder/FirBlockBuilder;", "statementsSetup", "createEvalFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "evalSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "replElements", "convertReplElement", "extractReplElements", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "copiedDelegatedProperties", Argument.Delimiters.none, "convertCodeFragment", "Lorg/jetbrains/kotlin/fir/declarations/FirCodeFragment;", "Lorg/jetbrains/kotlin/psi/KtCodeFragment;", "fileBuilder", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirFileBuilder;", "convertTypeCodeFragmentBlock", "Lorg/jetbrains/kotlin/psi/KtTypeCodeFragment;", "toFirEnumEntry", "Lorg/jetbrains/kotlin/psi/KtEnumEntry;", "delegatedEnumSelfTypeRef", "ownerClassHasDefaultConstructor", "addContextParameters", "contextLists", "Lorg/jetbrains/kotlin/psi/KtContextParameterList;", "visitClassOrObject", "classOrObject", "addDeclarations", "classBody", "Lorg/jetbrains/kotlin/psi/KtClassBody;", "companionBlockCollector", "Lorg/jetbrains/kotlin/fir/builder/CompanionBlockCollector;", "visitObjectLiteralExpression", "expression", "Lorg/jetbrains/kotlin/psi/KtObjectLiteralExpression;", "visitTypeAlias", "typeAlias", "Lorg/jetbrains/kotlin/psi/KtTypeAlias;", "visitNamedFunction", "function", "Lorg/jetbrains/kotlin/psi/KtNamedFunction;", "obtainContractDescription", "extractRawEffects", "Lorg/jetbrains/kotlin/psi/KtContractEffectList;", "destination", "visitLambdaExpression", "Lorg/jetbrains/kotlin/psi/KtLambdaExpression;", "Lorg/jetbrains/kotlin/psi/KtSecondaryConstructor;", "delegatedTypeRef", "selfTypeRef", "Lorg/jetbrains/kotlin/psi/KtConstructorDelegationCall;", "delegatedType", "toInitializerExpression", "Lorg/jetbrains/kotlin/psi/KtDeclarationWithInitializer;", "context", "Lorg/jetbrains/kotlin/fir/builder/Context;", "buildAnonymousInitializer", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousInitializer;", "initializer", "Lorg/jetbrains/kotlin/psi/KtAnonymousInitializer;", "allowLazyBody", "isLocal", "visitProperty", "visitTypeReference", "typeReference", "toFirType", "convertKtTypeElement", "Lorg/jetbrains/kotlin/fir/types/builder/FirUserTypeRefBuilder;", "isNullable", "ktUserType", "Lorg/jetbrains/kotlin/psi/KtUserType;", "reference", "Lorg/jetbrains/kotlin/psi/KtSimpleNameExpression;", "visitAnnotationEntry", "annotationEntry", "Lorg/jetbrains/kotlin/psi/KtAnnotationEntry;", "visitTypeProjection", "typeProjection", "Lorg/jetbrains/kotlin/psi/KtTypeProjection;", "visitBlockExpression", "Lorg/jetbrains/kotlin/psi/KtBlockExpression;", "configureBlockWithoutBuilding", "kind", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", "visitSimpleNameExpression", "visitConstantExpression", "Lorg/jetbrains/kotlin/psi/KtConstantExpression;", "visitStringTemplateExpression", "Lorg/jetbrains/kotlin/psi/KtStringTemplateExpression;", "visitReturnExpression", "Lorg/jetbrains/kotlin/psi/KtReturnExpression;", "visitTryExpression", "Lorg/jetbrains/kotlin/psi/KtTryExpression;", "visitIfExpression", "Lorg/jetbrains/kotlin/psi/KtIfExpression;", "visitWhenExpression", "Lorg/jetbrains/kotlin/psi/KtWhenExpression;", "usedAsExpression", "getUsedAsExpression", "(Lorg/jetbrains/kotlin/psi/KtExpression;)Z", "visitDoWhileExpression", "Lorg/jetbrains/kotlin/psi/KtDoWhileExpression;", "visitWhileExpression", "Lorg/jetbrains/kotlin/psi/KtWhileExpression;", "visitForExpression", "Lorg/jetbrains/kotlin/psi/KtForExpression;", "visitBreakExpression", "Lorg/jetbrains/kotlin/psi/KtBreakExpression;", "visitContinueExpression", "Lorg/jetbrains/kotlin/psi/KtContinueExpression;", "visitBinaryExpression", "Lorg/jetbrains/kotlin/psi/KtBinaryExpression;", "visitBinaryExpressionFallback", "visitBinaryWithTypeRHSExpression", "Lorg/jetbrains/kotlin/psi/KtBinaryExpressionWithTypeRHS;", "visitIsExpression", "Lorg/jetbrains/kotlin/psi/KtIsExpression;", "visitUnaryExpression", "Lorg/jetbrains/kotlin/psi/KtUnaryExpression;", "splitToCalleeAndReceiver", "Lorg/jetbrains/kotlin/fir/builder/CalleeAndReceiver;", "calleeExpression", "defaultSource", "visitCallExpression", "Lorg/jetbrains/kotlin/psi/KtCallExpression;", "visitArrayAccessExpression", "Lorg/jetbrains/kotlin/psi/KtArrayAccessExpression;", "visitQualifiedExpression", "Lorg/jetbrains/kotlin/psi/KtQualifiedExpression;", "visitThisExpression", "Lorg/jetbrains/kotlin/psi/KtThisExpression;", "visitSuperExpression", "Lorg/jetbrains/kotlin/psi/KtSuperExpression;", "visitParenthesizedExpression", "Lorg/jetbrains/kotlin/psi/KtParenthesizedExpression;", "visitLabeledExpression", "Lorg/jetbrains/kotlin/psi/KtLabeledExpression;", "visitAnnotatedExpression", "Lorg/jetbrains/kotlin/psi/KtAnnotatedExpression;", "visitThrowExpression", "Lorg/jetbrains/kotlin/psi/KtThrowExpression;", "visitDestructuringDeclaration", "multiDeclaration", "visitClassLiteralExpression", "Lorg/jetbrains/kotlin/psi/KtClassLiteralExpression;", "visitCallableReferenceExpression", "Lorg/jetbrains/kotlin/psi/KtCallableReferenceExpression;", "errorValueArgumentList", "Lorg/jetbrains/kotlin/psi/KtValueArgumentList;", "getErrorValueArgumentList", "(Lorg/jetbrains/kotlin/psi/KtCallableReferenceExpression;)Lorg/jetbrains/kotlin/psi/KtValueArgumentList;", "visitCollectionLiteralExpression", "Lorg/jetbrains/kotlin/psi/KtCollectionLiteralExpression;", "visitExpression", "appendTypeArguments", "Lorg/jetbrains/kotlin/fir/types/FirTypeProjection;", "args", "buildErrorNonLocalDeclarationForDanglingModifierList", "Lorg/jetbrains/kotlin/fir/declarations/FirDanglingModifierList;", "modifierList", "Lorg/jetbrains/kotlin/psi/KtModifierList;", "org.jetbrains.kotlin:psi2fir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public class Visitor extends KtVisitor<FirElement, FirElement> implements DestructuringContext<KtDestructuringDeclarationEntry> {

        @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;
            public static final /* synthetic */ int[] $EnumSwitchMapping$1;

            static {
                int[] iArr = new int[BodyBuildingMode.values().length];
                try {
                    iArr[BodyBuildingMode.NORMAL.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[BodyBuildingMode.LAZY_BODIES.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                $EnumSwitchMapping$0 = iArr;
                int[] iArr2 = new int[KtProjectionKind.values().length];
                try {
                    iArr2[KtProjectionKind.IN.ordinal()] = 1;
                } catch (NoSuchFieldError unused3) {
                }
                try {
                    iArr2[KtProjectionKind.OUT.ordinal()] = 2;
                } catch (NoSuchFieldError unused4) {
                }
                try {
                    iArr2[KtProjectionKind.NONE.ordinal()] = 3;
                } catch (NoSuchFieldError unused5) {
                }
                try {
                    iArr2[KtProjectionKind.STAR.ordinal()] = 4;
                } catch (NoSuchFieldError unused6) {
                }
                $EnumSwitchMapping$1 = iArr2;
            }
        }

        public Visitor() {
        }

        private static final KtExpression _get_usedAsExpression_$getLastChildExpression(PsiElement psiElement) {
            Object next;
            PsiElement[] children = psiElement.getChildren();
            children.getClass();
            Iterator it = CollectionsKt.asReversed(ArraysKt.asList(children)).iterator();
            while (it.hasNext()) {
                next = it.next();
                if (next instanceof KtExpression) {
                    return (KtExpression) next;
                }
            }
            next = null;
            return (KtExpression) next;
        }

        private final void addAnnotationsFrom(FirValueParameterBuilder firValueParameterBuilder, KtParameter ktParameter, boolean z) {
            for (KtAnnotationEntry ktAnnotationEntry : ktParameter.getAnnotationEntries()) {
                ktAnnotationEntry.getClass();
                FirElement firElementConvertElement = convertElement(ktAnnotationEntry, null);
                if (firElementConvertElement == null) {
                    x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.expressions.FirAnnotation");
                    return;
                }
                FirAnnotation firAnnotation = (FirAnnotation) firElementConvertElement;
                FirAnnotation firAnnotation2 = (!z || ConversionUtilsKt.appliesToPrimaryConstructorParameter(firAnnotation.getUseSiteTarget())) ? firAnnotation : null;
                if (firAnnotation2 != null) {
                    firValueParameterBuilder.getAnnotations().add(firAnnotation2);
                }
            }
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
        /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
        private final void addContextParameters(List<FirValueParameter> list, List<? extends KtContextParameterList> list2, FirBasedSymbol<?> firBasedSymbol) throws KotlinIllegalStateExceptionWithAttachments, UninitializedPropertyAccessException {
            String strNameForReceiverLabel;
            for (KtContextParameterList ktContextParameterList : list2) {
                List<FirValueParameter> list3 = list;
                Iterator it = ktContextParameterList.getContextParameters().iterator();
                while (it.hasNext()) {
                    Visitor visitor = this;
                    list3.add(toFirValueParameter$default(visitor, (KtParameter) it.next(), null, firBasedSymbol, AbstractRawFirBuilder.ValueParameterDeclaration.CONTEXT_PARAMETER, null, 8, null));
                    this = visitor;
                }
                Visitor visitor2 = this;
                FirBasedSymbol<?> firBasedSymbol2 = firBasedSymbol;
                List<KtContextReceiver> listContextReceivers = ktContextParameterList.contextReceivers();
                PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
                for (KtContextReceiver ktContextReceiver : listContextReceivers) {
                    FirValueParameterBuilder firValueParameterBuilder = new FirValueParameterBuilder();
                    Name nameIdentifier = null;
                    firValueParameterBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, ktContextReceiver, null, 1, null));
                    firValueParameterBuilder.setModuleData(psiRawFirBuilder.getBaseModuleData());
                    firValueParameterBuilder.setOrigin(FirDeclarationOrigin.Source.INSTANCE);
                    Name nameLabelNameAsName = ktContextReceiver.labelNameAsName();
                    KtTypeReference ktTypeReferenceTypeReference = ktContextReceiver.typeReference();
                    if (ktTypeReferenceTypeReference != null && (strNameForReceiverLabel = ktTypeReferenceTypeReference.nameForReceiverLabel()) != null) {
                        nameIdentifier = Name.identifier(strNameForReceiverLabel);
                    }
                    if (nameLabelNameAsName == null) {
                        nameLabelNameAsName = nameIdentifier == null ? SpecialNames.UNDERSCORE_FOR_UNUSED_VAR : nameIdentifier;
                    }
                    firValueParameterBuilder.setName(nameLabelNameAsName);
                    firValueParameterBuilder.setSymbol(new FirValueParameterSymbol());
                    FirValueParameterSymbol symbol = firValueParameterBuilder.getSymbol();
                    psiRawFirBuilder.getContext().pushContainerSymbol(symbol);
                    try {
                        firValueParameterBuilder.setReturnTypeRef(visitor2.toFirOrErrorType(ktContextReceiver.typeReference()));
                        Unit unit = Unit.INSTANCE;
                        psiRawFirBuilder.getContext().popContainerSymbol(symbol);
                        firValueParameterBuilder.setContainingDeclarationSymbol(firBasedSymbol2);
                        firValueParameterBuilder.setValueParameterKind(FirValueParameterKind.LegacyContextReceiver);
                        list3.add(firValueParameterBuilder.mo288build());
                    } catch (Throwable th) {
                        psiRawFirBuilder.getContext().popContainerSymbol(symbol);
                        throw th;
                    }
                }
                this = visitor2;
                firBasedSymbol = firBasedSymbol2;
            }
        }

        private final void addDeclarations(FirClassBuilder firClassBuilder, KtClassBody ktClassBody, FirTypeRef firTypeRef, FirResolvedTypeRef firResolvedTypeRef, KtClassOrObject ktClassOrObject, CompanionBlockCollector companionBlockCollector) {
            List<KtCompanionBlock> declarationsAndCompanionBlocks;
            if (ktClassBody == null || (declarationsAndCompanionBlocks = ktClassBody.getDeclarationsAndCompanionBlocks()) == null) {
                return;
            }
            PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
            for (KtCompanionBlock ktCompanionBlock : declarationsAndCompanionBlocks) {
                if (ktCompanionBlock instanceof KtDeclaration) {
                    firClassBuilder.getDeclarations().add(toFirDeclaration((KtDeclaration) ktCompanionBlock, firTypeRef, firResolvedTypeRef, ktClassOrObject, firClassBuilder, firClassBuilder.getTypeParameters()));
                } else if (ktCompanionBlock instanceof KtCompanionBlock) {
                    companionBlockCollector.collect(AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, ktCompanionBlock, null, 1, null), psiRawFirBuilder.isDirectlyInsideCompanionBlock());
                    FirBasedSymbol<?> currentCompanionBlockOwnerOrNull = psiRawFirBuilder.getContext().getCurrentCompanionBlockOwnerOrNull();
                    psiRawFirBuilder.getContext().setCurrentCompanionBlockOwnerOrNull(psiRawFirBuilder.getContext().getContainerSymbolIfAny());
                    try {
                        addDeclarations(firClassBuilder, ktCompanionBlock.getBody(), firTypeRef, firResolvedTypeRef, ktClassOrObject, companionBlockCollector);
                        psiRawFirBuilder.getContext().setCurrentCompanionBlockOwnerOrNull(currentCompanionBlockOwnerOrNull);
                    } catch (Throwable th) {
                        psiRawFirBuilder.getContext().setCurrentCompanionBlockOwnerOrNull(currentCompanionBlockOwnerOrNull);
                        throw th;
                    }
                } else {
                    continue;
                }
            }
        }

        private final void appendTypeArguments(List<FirTypeProjection> list, List<? extends KtTypeProjection> list2) {
            Iterator<? extends KtTypeProjection> it = list2.iterator();
            while (it.hasNext()) {
                List<FirTypeProjection> list3 = list;
                FirElement firElementConvertElement = convertElement(it.next(), null);
                if (firElementConvertElement == null) {
                    x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.types.FirTypeProjection");
                    return;
                }
                list3.add((FirTypeProjection) firElementConvertElement);
            }
        }

        public static /* synthetic */ FirAnonymousInitializer buildAnonymousInitializer$default(Visitor visitor, KtAnonymousInitializer ktAnonymousInitializer, FirBasedSymbol firBasedSymbol, boolean z, boolean z2, int i, Object obj) {
            if (obj != null) {
                c41.a("Super calls with default arguments not supported in this target, function: buildAnonymousInitializer");
                return null;
            }
            if ((i & 4) != 0) {
                z = true;
            }
            if ((i & 8) != 0) {
                z2 = false;
            }
            return visitor.buildAnonymousInitializer(ktAnonymousInitializer, firBasedSymbol, z, z2);
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
        /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
        private final FirDanglingModifierList buildErrorNonLocalDeclarationForDanglingModifierList(KtModifierList modifierList) throws KotlinIllegalStateExceptionWithAttachments, UninitializedPropertyAccessException {
            PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
            FirDanglingModifierListBuilder firDanglingModifierListBuilder = new FirDanglingModifierListBuilder();
            firDanglingModifierListBuilder.setSource(psiRawFirBuilder.toFirSourceElement((PsiElement) modifierList, (KtFakeSourceElementKind) KtFakeSourceElementKind.DanglingModifierList.INSTANCE));
            firDanglingModifierListBuilder.setModuleData(psiRawFirBuilder.getBaseModuleData());
            firDanglingModifierListBuilder.setOrigin(FirDeclarationOrigin.Source.INSTANCE);
            firDanglingModifierListBuilder.setDiagnostic(ConeDanglingModifierOnTopLevel.INSTANCE);
            firDanglingModifierListBuilder.setSymbol(new FirDanglingModifierSymbol());
            FirDanglingModifierSymbol symbol = firDanglingModifierListBuilder.getSymbol();
            psiRawFirBuilder.getContext().pushContainerSymbol(symbol);
            try {
                for (KtAnnotationEntry ktAnnotationEntry : modifierList.getAnnotationEntries()) {
                    List<FirAnnotation> annotations = firDanglingModifierListBuilder.getAnnotations();
                    ktAnnotationEntry.getClass();
                    FirElement firElementConvertElement = convertElement(ktAnnotationEntry, null);
                    if (firElementConvertElement == null) {
                        throw new NullPointerException("null cannot be cast to non-null type org.jetbrains.kotlin.fir.expressions.FirAnnotation");
                    }
                    annotations.add((FirAnnotation) firElementConvertElement);
                }
                List<FirValueParameter> contextParameters = firDanglingModifierListBuilder.getContextParameters();
                List<? extends KtContextParameterList> contextParameterLists = modifierList.getContextParameterLists();
                contextParameterLists.getClass();
                addContextParameters(contextParameters, contextParameterLists, firDanglingModifierListBuilder.getSymbol());
                Unit unit = Unit.INSTANCE;
                psiRawFirBuilder.getContext().popContainerSymbol(symbol);
                return firDanglingModifierListBuilder.mo288build();
            } catch (Throwable th) {
                psiRawFirBuilder.getContext().popContainerSymbol(symbol);
                throw th;
            }
        }

        private final Pair<FirBlock, FirContractDescription> buildFirBody(KtDeclarationWithBody ktDeclarationWithBody) {
            Object objInvoke;
            Object objProcessLegacyContractDescription;
            Object firSingleExpressionBlock;
            FirContractDescription firContractDescription = null;
            ConeDiagnostic coneDiagnostic = null;
            firContractDescription = null;
            if (!ktDeclarationWithBody.hasBody()) {
                return TuplesKt.to(null, null);
            }
            if (!ktDeclarationWithBody.hasBlockBody()) {
                PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
                PsiRawFirBuilder$Visitor$buildOrLazyBlock$1 psiRawFirBuilder$Visitor$buildOrLazyBlock$1 = PsiRawFirBuilder$Visitor$buildOrLazyBlock$1.INSTANCE;
                int i = WhenMappings.$EnumSwitchMapping$0[psiRawFirBuilder.getMode().ordinal()];
                if (i == 1) {
                    FirExpression firExpression = toFirExpression(ktDeclarationWithBody.getBodyExpression(), "Function has no body (but should)", ktDeclarationWithBody);
                    firSingleExpressionBlock = new FirSingleExpressionBlock(AbstractRawFirBuilder.toReturn$default(psiRawFirBuilder, firExpression, firExpression.getSource(), null, false, 6, null));
                } else {
                    if (i != 2) {
                        bu8.a();
                        return null;
                    }
                    int i2 = WhenMappings.$EnumSwitchMapping$0[PsiRawFirBuilder.this.getMode().ordinal()];
                    if (i2 == 1) {
                        firSingleExpressionBlock = psiRawFirBuilder$Visitor$buildOrLazyBlock$1.invoke();
                    } else {
                        if (i2 != 2) {
                            bu8.a();
                            return null;
                        }
                        firSingleExpressionBlock = AstLoadingFilter.disallowTreeLoading(new PsiRawFirBuilder$runOnStubs$1(psiRawFirBuilder$Visitor$buildOrLazyBlock$1));
                    }
                }
                return TuplesKt.to((FirBlock) firSingleExpressionBlock, null);
            }
            PsiRawFirBuilder$Visitor$buildOrLazyBlock$1 psiRawFirBuilder$Visitor$buildOrLazyBlock$2 = PsiRawFirBuilder$Visitor$buildOrLazyBlock$1.INSTANCE;
            BodyBuildingMode mode = PsiRawFirBuilder.this.getMode();
            int[] iArr = WhenMappings.$EnumSwitchMapping$0;
            int i3 = iArr[mode.ordinal()];
            if (i3 == 1) {
                KtBlockExpression bodyBlockExpression = ktDeclarationWithBody.getBodyBlockExpression();
                FirElement firElement = bodyBlockExpression != null ? (FirElement) bodyBlockExpression.accept(this, (Object) null) : null;
                objInvoke = firElement instanceof FirBlock ? (FirBlock) firElement : null;
                if (objInvoke == null) {
                    return TuplesKt.to(null, null);
                }
            } else {
                if (i3 != 2) {
                    bu8.a();
                    return null;
                }
                int i4 = WhenMappings.$EnumSwitchMapping$0[PsiRawFirBuilder.this.getMode().ordinal()];
                if (i4 == 1) {
                    objInvoke = psiRawFirBuilder$Visitor$buildOrLazyBlock$2.invoke();
                } else {
                    if (i4 != 2) {
                        bu8.a();
                        return null;
                    }
                    objInvoke = AstLoadingFilter.disallowTreeLoading(new PsiRawFirBuilder$runOnStubs$1(psiRawFirBuilder$Visitor$buildOrLazyBlock$2));
                }
            }
            FirBlock firBlock = (FirBlock) objInvoke;
            if (!ktDeclarationWithBody.hasContractEffectList() && ktDeclarationWithBody.mayHaveContract()) {
                PsiRawFirBuilder$Visitor$buildFirBody$contractDescription$2 psiRawFirBuilder$Visitor$buildFirBody$contractDescription$2 = PsiRawFirBuilder$Visitor$buildFirBody$contractDescription$2.INSTANCE;
                int i5 = iArr[PsiRawFirBuilder.this.getMode().ordinal()];
                if (i5 == 1) {
                    KtSourceElement source = firBlock.getSource();
                    PsiElement psi = source != null ? KtSourceElementKt.getPsi(source) : null;
                    if (psi == null || !isCallTheFirstStatement(psi)) {
                        coneDiagnostic = ConeContractShouldBeFirstStatement.INSTANCE;
                    } else if (functionCallHasLabel(psi)) {
                        coneDiagnostic = ConeContractMayNotHaveLabel.INSTANCE;
                    }
                    objProcessLegacyContractDescription = ConversionUtilsKt.processLegacyContractDescription(firBlock, coneDiagnostic);
                } else {
                    if (i5 != 2) {
                        bu8.a();
                        return null;
                    }
                    int i6 = WhenMappings.$EnumSwitchMapping$0[PsiRawFirBuilder.this.getMode().ordinal()];
                    if (i6 == 1) {
                        objProcessLegacyContractDescription = psiRawFirBuilder$Visitor$buildFirBody$contractDescription$2.invoke();
                    } else {
                        if (i6 != 2) {
                            bu8.a();
                            return null;
                        }
                        objProcessLegacyContractDescription = AstLoadingFilter.disallowTreeLoading(new PsiRawFirBuilder$runOnStubs$1(psiRawFirBuilder$Visitor$buildFirBody$contractDescription$2));
                    }
                }
                firContractDescription = (FirContractDescription) objProcessLegacyContractDescription;
            }
            return TuplesKt.to(firBlock, firContractDescription);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final FirExpression checkSelectorInvariant(KtElement ktElement, FirExpression firExpression) {
            KtPsiSourceElement firSourceElement$default;
            KtExpression calleeExpression;
            boolean z = ktElement instanceof KtCallExpression;
            KtCallExpression ktCallExpression = z ? (KtCallExpression) ktElement : null;
            PsiElement psiElementUnwrapParenthesesLabelsAndAnnotations = (ktCallExpression == null || (calleeExpression = ktCallExpression.getCalleeExpression()) == null) ? null : PsiUtilsKt.unwrapParenthesesLabelsAndAnnotations(calleeExpression);
            if (ktElement instanceof KtNameReferenceExpression) {
                return firExpression;
            }
            if ((z && !(psiElementUnwrapParenthesesLabelsAndAnnotations instanceof KtLambdaExpression)) || KtPsiUtilKt.getQualifiedExpressionForSelector(ktElement) == null) {
                return firExpression;
            }
            PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
            FirErrorExpressionBuilder firErrorExpressionBuilder = new FirErrorExpressionBuilder();
            if (psiElementUnwrapParenthesesLabelsAndAnnotations == null || (firSourceElement$default = AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, psiElementUnwrapParenthesesLabelsAndAnnotations, null, 1, null)) == null) {
                firSourceElement$default = AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, ktElement, null, 1, null);
            }
            firErrorExpressionBuilder.setSource(firSourceElement$default);
            firErrorExpressionBuilder.setDiagnostic(new ConeSimpleDiagnostic("The expression cannot be a selector (occur after a dot)", psiElementUnwrapParenthesesLabelsAndAnnotations == null ? DiagnosticKind.IllegalSelector : DiagnosticKind.NoReceiverAllowed));
            firErrorExpressionBuilder.setExpression(firExpression);
            return firErrorExpressionBuilder.mo288build();
        }

        /* JADX WARN: Code duplicated, block: B:17:0x007b  */
        private final FirBlockBuilder configureBlockWithoutBuilding(KtBlockExpression expression, KtFakeSourceElementKind kind) {
            FirStatement firStatementMo288build;
            boolean z;
            FirBlockBuilder firBlockBuilder = new FirBlockBuilder();
            firBlockBuilder.setSource(PsiRawFirBuilder.this.toFirSourceElement((PsiElement) expression, kind));
            for (KtExpression ktExpression : expression.getStatements()) {
                ktExpression.getClass();
                FirElement firElementConvertElement = convertElement(ktExpression, null);
                if (firElementConvertElement instanceof FirStatement) {
                    firStatementMo288build = (FirStatement) firElementConvertElement;
                } else {
                    PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
                    FirErrorExpressionBuilder firErrorExpressionBuilder = new FirErrorExpressionBuilder();
                    firErrorExpressionBuilder.setNonExpressionElement(firElementConvertElement);
                    firErrorExpressionBuilder.setDiagnostic(new ConeSyntaxDiagnostic("Statement expected: " + ktExpression.getText()));
                    firErrorExpressionBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, ktExpression, null, 1, null));
                    firStatementMo288build = firErrorExpressionBuilder.mo288build();
                }
                boolean z2 = firStatementMo288build instanceof FirBlock;
                if (z2) {
                    KtSourceElement source = ((FirBlock) firStatementMo288build).getSource();
                    z = Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DesugaredForLoop.INSTANCE);
                }
                if (z2 && !z) {
                    FirBlock firBlock = (FirBlock) firStatementMo288build;
                    if (firBlock.getAnnotations().isEmpty()) {
                        CollectionsKt.addAll(firBlockBuilder.getStatements(), firBlock.getStatements());
                    }
                }
                firBlockBuilder.getStatements().add(firStatementMo288build);
            }
            return firBlockBuilder;
        }

        public static /* synthetic */ FirBlockBuilder configureBlockWithoutBuilding$default(Visitor visitor, KtBlockExpression ktBlockExpression, KtFakeSourceElementKind ktFakeSourceElementKind, int i, Object obj) {
            if (obj != null) {
                c41.a("Super calls with default arguments not supported in this target, function: configureBlockWithoutBuilding");
                return null;
            }
            if ((i & 2) != 0) {
                ktFakeSourceElementKind = null;
            }
            return visitor.configureBlockWithoutBuilding(ktBlockExpression, ktFakeSourceElementKind);
        }

        private final FirDelegatedConstructorCall convert(KtConstructorDelegationCall ktConstructorDelegationCall, FirTypeRef firTypeRef) {
            KtPsiSourceElement ktPsiSourceElementFakeElement$default;
            FirReference firReferenceBuild;
            boolean zIsCallToThis = ktConstructorDelegationCall.isCallToThis();
            boolean zIsImplicit = ktConstructorDelegationCall.isImplicit();
            PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
            KtPsiSourceElement firSourceElement = zIsImplicit ? psiRawFirBuilder.toFirSourceElement((PsiElement) ktConstructorDelegationCall, (KtFakeSourceElementKind) KtFakeSourceElementKind.ImplicitConstructor.INSTANCE) : AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, ktConstructorDelegationCall, null, 1, null);
            PsiRawFirBuilder psiRawFirBuilder2 = PsiRawFirBuilder.this;
            FirDelegatedConstructorCallBuilder firDelegatedConstructorCallBuilder = new FirDelegatedConstructorCallBuilder();
            firDelegatedConstructorCallBuilder.setSource(firSourceElement);
            firDelegatedConstructorCallBuilder.setConstructedTypeRef(UtilsKt.copyWithNewSourceKind(firTypeRef, KtFakeSourceElementKind.ImplicitTypeRef.INSTANCE));
            firDelegatedConstructorCallBuilder.setThis(zIsCallToThis);
            KtFakeSourceElementKind.ImplicitConstructor implicitConstructor = ktConstructorDelegationCall.isImplicit() ? KtFakeSourceElementKind.ImplicitConstructor.INSTANCE : KtFakeSourceElementKind.DelegatingConstructorCall.INSTANCE;
            KtConstructorDelegationReferenceExpression calleeExpression = ktConstructorDelegationCall.getCalleeExpression();
            if (calleeExpression == null || (ktPsiSourceElementFakeElement$default = psiRawFirBuilder2.toFirSourceElement((PsiElement) calleeExpression, (KtFakeSourceElementKind) implicitConstructor)) == null) {
                ktPsiSourceElementFakeElement$default = KtSourceElementKt.fakeElement$default(firSourceElement, implicitConstructor, null, 2, null);
            }
            if (zIsCallToThis) {
                FirExplicitThisReferenceBuilder firExplicitThisReferenceBuilder = new FirExplicitThisReferenceBuilder();
                firExplicitThisReferenceBuilder.setSource(ktPsiSourceElementFakeElement$default);
                firReferenceBuild = firExplicitThisReferenceBuilder.build();
            } else {
                FirExplicitSuperReferenceBuilder firExplicitSuperReferenceBuilder = new FirExplicitSuperReferenceBuilder();
                firExplicitSuperReferenceBuilder.setSource(ktPsiSourceElementFakeElement$default);
                firExplicitSuperReferenceBuilder.setSuperTypeRef(firDelegatedConstructorCallBuilder.getConstructedTypeRef());
                firReferenceBuild = firExplicitSuperReferenceBuilder.build();
            }
            firDelegatedConstructorCallBuilder.setCalleeReference(firReferenceBuild);
            extractArgumentsTo(ktConstructorDelegationCall, firDelegatedConstructorCallBuilder);
            return firDelegatedConstructorCallBuilder.mo288build();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
        /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
        private final FirCodeFragment convertCodeFragment(KtCodeFragment file, FirFileBuilder fileBuilder) throws KotlinIllegalStateExceptionWithAttachments, UninitializedPropertyAccessException {
            Object objConvertTypeCodeFragmentBlock;
            PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
            FirCodeFragmentBuilder firCodeFragmentBuilder = new FirCodeFragmentBuilder();
            firCodeFragmentBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, file, null, 1, null));
            firCodeFragmentBuilder.setModuleData(psiRawFirBuilder.getBaseModuleData());
            firCodeFragmentBuilder.setOrigin(FirDeclarationOrigin.Source.INSTANCE);
            firCodeFragmentBuilder.setSymbol(new FirCodeFragmentSymbol());
            FirCodeFragmentSymbol symbol = firCodeFragmentBuilder.getSymbol();
            psiRawFirBuilder.getContext().pushContainerSymbol(symbol);
            try {
                PsiRawFirBuilder$Visitor$buildOrLazyBlock$1 psiRawFirBuilder$Visitor$buildOrLazyBlock$1 = PsiRawFirBuilder$Visitor$buildOrLazyBlock$1.INSTANCE;
                int i = WhenMappings.$EnumSwitchMapping$0[PsiRawFirBuilder.this.getMode().ordinal()];
                if (i == 1) {
                    boolean forceKeepingTheBodyInHeaderMode = psiRawFirBuilder.getContext().getForceKeepingTheBodyInHeaderMode();
                    psiRawFirBuilder.getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
                    boolean inLocalContext = psiRawFirBuilder.getContext().getInLocalContext();
                    psiRawFirBuilder.getContext().setInLocalContext(true);
                    FqName classNameBeforeLocalContext = psiRawFirBuilder.getContext().getClassNameBeforeLocalContext();
                    if (!inLocalContext) {
                        psiRawFirBuilder.getContext().setClassNameBeforeLocalContext(psiRawFirBuilder.getContext().getClassName());
                    }
                    FqName className = psiRawFirBuilder.getContext().getClassName();
                    psiRawFirBuilder.getContext().setClassName(FqName.ROOT);
                    try {
                        if (file instanceof KtExpressionCodeFragment) {
                            KtExpression contentElement = ((KtExpressionCodeFragment) file).getContentElement();
                            if (contentElement == null || (objConvertTypeCodeFragmentBlock = toFirBlock(contentElement)) == null) {
                                objConvertTypeCodeFragmentBlock = FirEmptyExpressionBlockBuilderKt.buildEmptyExpressionBlock();
                            }
                        } else if (file instanceof KtBlockCodeFragment) {
                            objConvertTypeCodeFragmentBlock = configureBlockWithoutBuilding$default(this, ((KtBlockCodeFragment) file).getContentElement(), null, 2, null).mo288build();
                        } else {
                            if (!(file instanceof KtTypeCodeFragment)) {
                                throw new IllegalStateException(("Unexpected code fragment type: " + Reflection.getOrCreateKotlinClass(file.getClass())).toString());
                            }
                            objConvertTypeCodeFragmentBlock = convertTypeCodeFragmentBlock((KtTypeCodeFragment) file);
                        }
                        psiRawFirBuilder.getContext().setClassNameBeforeLocalContext(classNameBeforeLocalContext);
                        psiRawFirBuilder.getContext().setInLocalContext(inLocalContext);
                        psiRawFirBuilder.getContext().setClassName(className);
                        psiRawFirBuilder.getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
                    } catch (Throwable th) {
                        psiRawFirBuilder.getContext().setClassNameBeforeLocalContext(classNameBeforeLocalContext);
                        psiRawFirBuilder.getContext().setInLocalContext(inLocalContext);
                        psiRawFirBuilder.getContext().setClassName(className);
                        psiRawFirBuilder.getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
                        throw th;
                    }
                } else {
                    if (i != 2) {
                        throw new NoWhenBranchMatchedException();
                    }
                    int i2 = WhenMappings.$EnumSwitchMapping$0[PsiRawFirBuilder.this.getMode().ordinal()];
                    if (i2 == 1) {
                        objConvertTypeCodeFragmentBlock = psiRawFirBuilder$Visitor$buildOrLazyBlock$1.invoke();
                    } else {
                        if (i2 != 2) {
                            throw new NoWhenBranchMatchedException();
                        }
                        objConvertTypeCodeFragmentBlock = AstLoadingFilter.disallowTreeLoading(new PsiRawFirBuilder$runOnStubs$1(psiRawFirBuilder$Visitor$buildOrLazyBlock$1));
                    }
                }
                firCodeFragmentBuilder.setBlock((FirBlock) objConvertTypeCodeFragmentBlock);
                Unit unit = Unit.INSTANCE;
                psiRawFirBuilder.getContext().popContainerSymbol(symbol);
                return firCodeFragmentBuilder.mo288build();
            } catch (Throwable th2) {
                psiRawFirBuilder.getContext().popContainerSymbol(symbol);
                throw th2;
            }
        }

        public static /* synthetic */ FirElement convertElement$default(Visitor visitor, KtElement ktElement, FirElement firElement, int i, Object obj) {
            if (obj != null) {
                c41.a("Super calls with default arguments not supported in this target, function: convertElement");
                return null;
            }
            if ((i & 2) != 0) {
                firElement = null;
            }
            return visitor.convertElement(ktElement, firElement);
        }

        private final FirUserTypeRefBuilder convertKtTypeElement(KtPsiSourceElement source, boolean isNullable, KtUserType ktUserType, KtSimpleNameExpression reference) {
            KtPsiSourceElement ktRealPsiSourceElement;
            KtTypeArgumentList typeArgumentList;
            FirUserTypeRefBuilder firUserTypeRefBuilder = new FirUserTypeRefBuilder();
            PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
            firUserTypeRefBuilder.setSource(source);
            firUserTypeRefBuilder.setMarkedNullable(isNullable);
            do {
                reference.getClass();
                KtSourceElement firSourceElement$default = AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, reference, null, 1, null);
                Name referencedNameAsName = reference.getReferencedNameAsName();
                if (ktUserType == null || (typeArgumentList = ktUserType.getTypeArgumentList()) == null) {
                    ktRealPsiSourceElement = source;
                } else {
                    if (KtRealSourceElementKind.INSTANCE == null) {
                        bu8.a();
                        return null;
                    }
                    ktRealPsiSourceElement = new KtRealPsiSourceElement(typeArgumentList);
                }
                FirTypeArgumentListImpl firTypeArgumentListImpl = new FirTypeArgumentListImpl(ktRealPsiSourceElement);
                List<FirTypeProjection> typeArguments = firTypeArgumentListImpl.getTypeArguments();
                ktUserType.getClass();
                List<? extends KtTypeProjection> typeArguments2 = ktUserType.getTypeArguments();
                typeArguments2.getClass();
                appendTypeArguments(typeArguments, typeArguments2);
                Unit unit = Unit.INSTANCE;
                firUserTypeRefBuilder.getQualifier().add(new FirQualifierPartImpl(firSourceElement$default, referencedNameAsName, firTypeArgumentListImpl));
                ktUserType = ktUserType.getQualifier();
                reference = ktUserType != null ? ktUserType.getReferenceExpression() : null;
            } while (reference != null);
            CollectionsKt.reverse(firUserTypeRefBuilder.getQualifier());
            return firUserTypeRefBuilder;
        }

        private final FirElement convertReplElement(FirElement element) {
            if (element instanceof FirProperty) {
                FirProperty firProperty = (FirProperty) element;
                FirExpression initializer = firProperty.getInitializer();
                FirExpression delegate = firProperty.getDelegate();
                if (!firProperty.getIsLocal()) {
                    if (((FirMemberDeclaration) element).getStatus().isConst()) {
                        FirReplDeclarationReferenceBuilder firReplDeclarationReferenceBuilder = new FirReplDeclarationReferenceBuilder();
                        KtSourceElement source = firProperty.getSource();
                        firReplDeclarationReferenceBuilder.setSource(source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.ReplEvalFunction.INSTANCE, null, 2, null) : null);
                        firReplDeclarationReferenceBuilder.setSymbol(firProperty.getSymbol());
                        return firReplDeclarationReferenceBuilder.mo288build();
                    }
                    if (delegate != null) {
                        FirReplExpressionReferenceBuilder firReplExpressionReferenceBuilder = new FirReplExpressionReferenceBuilder();
                        KtSourceElement source2 = delegate.getSource();
                        firReplExpressionReferenceBuilder.setSource(source2 != null ? KtSourceElementKt.fakeElement$default(source2, KtFakeSourceElementKind.ReplEvalFunction.INSTANCE, null, 2, null) : null);
                        FirExpressionRef<FirExpression> firExpressionRef = new FirExpressionRef<>();
                        firExpressionRef.bind(delegate);
                        firReplExpressionReferenceBuilder.setExpressionRef(firExpressionRef);
                        firProperty.replaceDelegate(firReplExpressionReferenceBuilder.mo288build());
                        FirReplPropertyDelegateBuilder firReplPropertyDelegateBuilder = new FirReplPropertyDelegateBuilder();
                        KtSourceElement source3 = delegate.getSource();
                        firReplPropertyDelegateBuilder.setSource(source3 != null ? KtSourceElementKt.fakeElement$default(source3, KtFakeSourceElementKind.ReplEvalFunction.INSTANCE, null, 2, null) : null);
                        firReplPropertyDelegateBuilder.setPropertySymbol(firProperty.getSymbol());
                        firReplPropertyDelegateBuilder.setDelegate(delegate);
                        return firReplPropertyDelegateBuilder.mo288build();
                    }
                    if (initializer == null) {
                        FirReplDeclarationReferenceBuilder firReplDeclarationReferenceBuilder2 = new FirReplDeclarationReferenceBuilder();
                        KtSourceElement source4 = firProperty.getSource();
                        firReplDeclarationReferenceBuilder2.setSource(source4 != null ? KtSourceElementKt.fakeElement$default(source4, KtFakeSourceElementKind.ReplEvalFunction.INSTANCE, null, 2, null) : null);
                        firReplDeclarationReferenceBuilder2.setSymbol(firProperty.getSymbol());
                        return firReplDeclarationReferenceBuilder2.mo288build();
                    }
                    FirReplExpressionReferenceBuilder firReplExpressionReferenceBuilder2 = new FirReplExpressionReferenceBuilder();
                    KtSourceElement source5 = initializer.getSource();
                    firReplExpressionReferenceBuilder2.setSource(source5 != null ? KtSourceElementKt.fakeElement$default(source5, KtFakeSourceElementKind.ReplEvalFunction.INSTANCE, null, 2, null) : null);
                    FirExpressionRef<FirExpression> firExpressionRef2 = new FirExpressionRef<>();
                    firExpressionRef2.bind(initializer);
                    firReplExpressionReferenceBuilder2.setExpressionRef(firExpressionRef2);
                    firProperty.replaceInitializer(firReplExpressionReferenceBuilder2.mo288build());
                    FirReplPropertyInitializerBuilder firReplPropertyInitializerBuilder = new FirReplPropertyInitializerBuilder();
                    KtSourceElement source6 = initializer.getSource();
                    firReplPropertyInitializerBuilder.setSource(source6 != null ? KtSourceElementKt.fakeElement$default(source6, KtFakeSourceElementKind.ReplEvalFunction.INSTANCE, null, 2, null) : null);
                    firReplPropertyInitializerBuilder.setPropertySymbol(firProperty.getSymbol());
                    firReplPropertyInitializerBuilder.setInitializer(initializer);
                    return firReplPropertyInitializerBuilder.mo288build();
                }
            } else if ((element instanceof FirNamedFunction) || (element instanceof FirRegularClass) || (element instanceof FirTypeAlias)) {
                FirReplDeclarationReferenceBuilder firReplDeclarationReferenceBuilder3 = new FirReplDeclarationReferenceBuilder();
                KtSourceElement source7 = ((FirStatement) element).getSource();
                firReplDeclarationReferenceBuilder3.setSource(source7 != null ? KtSourceElementKt.fakeElement$default(source7, KtFakeSourceElementKind.ReplEvalFunction.INSTANCE, null, 2, null) : null);
                firReplDeclarationReferenceBuilder3.setSymbol(((FirMemberDeclaration) element).getSymbol());
                return firReplDeclarationReferenceBuilder3.mo288build();
            }
            return element;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit convertScript$lambda$0$0$0(PsiRawFirBuilder psiRawFirBuilder, FirVariable firVariable, FirVariable firVariable2) {
            firVariable2.getClass();
            psiRawFirBuilder.configureScriptDestructuringDeclarationEntry(firVariable2, firVariable);
            DeclarationAttributesKt.setScriptTopLevelDeclaration(firVariable2, Boolean.TRUE);
            return Unit.INSTANCE;
        }

        private final FirBlock convertTypeCodeFragmentBlock(KtTypeCodeFragment file) {
            PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
            FirBlockBuilder firBlockBuilder = new FirBlockBuilder();
            FirAnonymousFunctionSymbol firAnonymousFunctionSymbol = new FirAnonymousFunctionSymbol();
            List<FirStatement> statements = firBlockBuilder.getStatements();
            FirAnonymousFunctionExpressionBuilder firAnonymousFunctionExpressionBuilder = new FirAnonymousFunctionExpressionBuilder();
            FirAnonymousFunctionBuilder firAnonymousFunctionBuilder = new FirAnonymousFunctionBuilder();
            firAnonymousFunctionBuilder.setModuleData(psiRawFirBuilder.getBaseModuleData());
            FirDeclarationOrigin.Source source = FirDeclarationOrigin.Source.INSTANCE;
            firAnonymousFunctionBuilder.setOrigin(source);
            KtFakeSourceElementKind.CodeFragment codeFragment = KtFakeSourceElementKind.CodeFragment.INSTANCE;
            firAnonymousFunctionBuilder.setSource(psiRawFirBuilder.toFirSourceElement((PsiElement) file, (KtFakeSourceElementKind) codeFragment));
            firAnonymousFunctionBuilder.setSymbol(firAnonymousFunctionSymbol);
            firAnonymousFunctionBuilder.setHasExplicitParameterList(true);
            firAnonymousFunctionBuilder.setLambda(false);
            List<FirValueParameter> valueParameters = firAnonymousFunctionBuilder.getValueParameters();
            FirValueParameterBuilder firValueParameterBuilder = new FirValueParameterBuilder();
            firValueParameterBuilder.setModuleData(psiRawFirBuilder.getBaseModuleData());
            firValueParameterBuilder.setOrigin(source);
            firValueParameterBuilder.setSource(psiRawFirBuilder.toFirSourceElement((PsiElement) file, (KtFakeSourceElementKind) codeFragment));
            firValueParameterBuilder.setName(StandardNames.DEFAULT_VALUE_PARAMETER);
            firValueParameterBuilder.setSymbol(new FirValueParameterSymbol());
            firValueParameterBuilder.setContainingDeclarationSymbol(firAnonymousFunctionSymbol);
            firValueParameterBuilder.setReturnTypeRef(toFirOrErrorType(file.getContentElement()));
            firValueParameterBuilder.setCrossinline(false);
            firValueParameterBuilder.setNoinline(false);
            firValueParameterBuilder.setVararg(false);
            valueParameters.add(firValueParameterBuilder.mo288build());
            firAnonymousFunctionBuilder.setReturnTypeRef(psiRawFirBuilder.getImplicitUnitType());
            FirBlockBuilder firBlockBuilder2 = new FirBlockBuilder();
            Unit unit = Unit.INSTANCE;
            firAnonymousFunctionBuilder.setBody(firBlockBuilder2.mo288build());
            firAnonymousFunctionExpressionBuilder.setAnonymousFunction(firAnonymousFunctionBuilder.mo288build());
            statements.add(firAnonymousFunctionExpressionBuilder.mo288build());
            return firBlockBuilder.mo288build();
        }

        private final List<FirTypeParameterRef> convertTypeParameters(KtTypeParameterListOwner ktTypeParameterListOwner, FirBasedSymbol<?> firBasedSymbol) {
            List<KtTypeParameter> typeParameters = ktTypeParameterListOwner.getTypeParameters();
            typeParameters.getClass();
            ArrayList arrayList = new ArrayList();
            for (KtTypeParameter ktTypeParameter : typeParameters) {
                ktTypeParameter.getClass();
                arrayList.add(extractTypeParameter(ktTypeParameter, firBasedSymbol));
            }
            return arrayList;
        }

        private final FirNamedFunction createEvalFunction(KtScript script, FirNamedFunctionSymbol evalSymbol, List<? extends FirElement> replElements, Function1<? super FirBlockBuilder, Unit> functionBodySetup) {
            Object objMo288build;
            FirFunctionTarget firFunctionTarget = new FirFunctionTarget(null, false);
            PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
            FirNamedFunctionBuilder firNamedFunctionBuilder = new FirNamedFunctionBuilder();
            KtFakeSourceElementKind.ReplEvalFunction replEvalFunction = KtFakeSourceElementKind.ReplEvalFunction.INSTANCE;
            if (replEvalFunction == null) {
                bu8.a();
                return null;
            }
            firNamedFunctionBuilder.setSource(new KtFakePsiSourceElement(script, replEvalFunction));
            firNamedFunctionBuilder.setModuleData(psiRawFirBuilder.getBaseModuleData());
            firNamedFunctionBuilder.setOrigin(FirDeclarationOrigin.Synthetic.ReplEvalFunction.INSTANCE);
            firNamedFunctionBuilder.setName(evalSymbol.getName());
            firNamedFunctionBuilder.setSymbol(evalSymbol);
            firNamedFunctionBuilder.setDispatchReceiverType(psiRawFirBuilder.currentDispatchReceiverType());
            firNamedFunctionBuilder.setStatus(new FirDeclarationStatusImpl(Visibilities.Public.INSTANCE, Modality.FINAL));
            firNamedFunctionBuilder.setReturnTypeRef(new ResolvedImplicitTypeRef(psiRawFirBuilder.getImplicitUnitType()));
            firNamedFunctionBuilder.setLocal(false);
            psiRawFirBuilder.getContext().getFirFunctionTargets().add(firFunctionTarget);
            PsiRawFirBuilder$Visitor$buildOrLazyBlock$1 psiRawFirBuilder$Visitor$buildOrLazyBlock$1 = PsiRawFirBuilder$Visitor$buildOrLazyBlock$1.INSTANCE;
            int i = WhenMappings.$EnumSwitchMapping$0[PsiRawFirBuilder.this.getMode().ordinal()];
            if (i == 1) {
                FirBlockBuilder firBlockBuilder = new FirBlockBuilder();
                for (FirElement firElement : replElements) {
                    if (firElement instanceof FirAnonymousInitializer) {
                        List<FirStatement> statements = firBlockBuilder.getStatements();
                        FirBlock body = ((FirAnonymousInitializer) firElement).getBody();
                        body.getClass();
                        CollectionsKt.addAll(statements, body.getStatements());
                    } else {
                        if (!(firElement instanceof FirStatement)) {
                            f2f.a("unexpected element type in REPL snippet: ", Reflection.getOrCreateKotlinClass(firElement.getClass()));
                            return null;
                        }
                        firBlockBuilder.getStatements().add(firElement);
                    }
                }
                functionBodySetup.invoke(firBlockBuilder);
                objMo288build = firBlockBuilder.mo288build();
            } else {
                if (i != 2) {
                    bu8.a();
                    return null;
                }
                int i2 = WhenMappings.$EnumSwitchMapping$0[PsiRawFirBuilder.this.getMode().ordinal()];
                if (i2 == 1) {
                    objMo288build = psiRawFirBuilder$Visitor$buildOrLazyBlock$1.invoke();
                } else {
                    if (i2 != 2) {
                        bu8.a();
                        return null;
                    }
                    objMo288build = AstLoadingFilter.disallowTreeLoading(new PsiRawFirBuilder$runOnStubs$1(psiRawFirBuilder$Visitor$buildOrLazyBlock$1));
                }
            }
            firNamedFunctionBuilder.setBody((FirBlock) objMo288build);
            psiRawFirBuilder.removeLast(psiRawFirBuilder.getContext().getFirFunctionTargets());
            FirNamedFunction firNamedFunctionMo288build = firNamedFunctionBuilder.mo288build();
            PsiRawFirBuilder.this.bindFunctionTarget(firFunctionTarget, firNamedFunctionMo288build);
            return firNamedFunctionMo288build;
        }

        public static FirBlock d(Visitor visitor, KtDoWhileExpression ktDoWhileExpression) {
            return visitor.toFirBlock(ktDoWhileExpression.getBody());
        }

        private final void extractAnnotationsFrom(FirDefaultPropertyAccessor firDefaultPropertyAccessor, KtAnnotated ktAnnotated) {
            extractAnnotationsTo(ktAnnotated, firDefaultPropertyAccessor);
        }

        private final void extractAnnotationsTo(KtAnnotated ktAnnotated, FirAnnotationContainer firAnnotationContainer) {
            if (ktAnnotated.getAnnotationEntries().isEmpty()) {
                return;
            }
            List listCreateListBuilder = CollectionsKt.createListBuilder();
            listCreateListBuilder.addAll(firAnnotationContainer.getAnnotations());
            for (KtAnnotationEntry ktAnnotationEntry : ktAnnotated.getAnnotationEntries()) {
                ktAnnotationEntry.getClass();
                FirElement firElementConvertElement = convertElement(ktAnnotationEntry, null);
                if (firElementConvertElement == null) {
                    x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.expressions.FirAnnotation");
                    return;
                }
                listCreateListBuilder.add((FirAnnotation) firElementConvertElement);
            }
            firAnnotationContainer.replaceAnnotations(CollectionsKt.build(listCreateListBuilder));
        }

        private final void extractArgumentsTo(KtCallElement ktCallElement, FirCallBuilder firCallBuilder) {
            Object firExpression;
            PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
            FirArgumentListBuilder firArgumentListBuilder = new FirArgumentListBuilder();
            KtValueArgumentList valueArgumentList = ktCallElement.getValueArgumentList();
            firArgumentListBuilder.setSource(valueArgumentList != null ? AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, valueArgumentList, null, 1, null) : null);
            for (PsiElement psiElement : ktCallElement.getValueArguments()) {
                PsiElement psiElement2 = psiElement instanceof PsiElement ? psiElement : null;
                PsiRawFirBuilder$Visitor$buildOrLazyExpression$1 psiRawFirBuilder$Visitor$buildOrLazyExpression$1 = new PsiRawFirBuilder$Visitor$buildOrLazyExpression$1(psiElement2 != null ? AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, psiElement2, null, 1, null) : null);
                int i = WhenMappings.$EnumSwitchMapping$0[PsiRawFirBuilder.this.getMode().ordinal()];
                if (i == 1) {
                    psiElement.getClass();
                    firExpression = toFirExpression(psiElement);
                } else {
                    if (i != 2) {
                        bu8.a();
                        return;
                    }
                    int i2 = WhenMappings.$EnumSwitchMapping$0[PsiRawFirBuilder.this.getMode().ordinal()];
                    if (i2 == 1) {
                        firExpression = psiRawFirBuilder$Visitor$buildOrLazyExpression$1.invoke();
                    } else {
                        if (i2 != 2) {
                            bu8.a();
                            return;
                        }
                        firExpression = AstLoadingFilter.disallowTreeLoading(new PsiRawFirBuilder$runOnStubs$1(psiRawFirBuilder$Visitor$buildOrLazyExpression$1));
                    }
                }
                FirExpression firExpression2 = (FirExpression) firExpression;
                List<FirExpression> arguments = firArgumentListBuilder.getArguments();
                if (psiElement instanceof KtLambdaArgument) {
                    FirAnonymousFunctionExpression firAnonymousFunctionExpression = firExpression2 instanceof FirAnonymousFunctionExpression ? (FirAnonymousFunctionExpression) firExpression2 : null;
                    if (firAnonymousFunctionExpression != null) {
                        firAnonymousFunctionExpression.replaceIsTrailingLambda(true);
                    }
                }
                arguments.add(firExpression2);
            }
            firCallBuilder.setArgumentList(firArgumentListBuilder.build());
        }

        private final void extractRawEffects(KtContractEffectList ktContractEffectList, List<FirExpression> list) {
            Object objInvoke;
            List<KtContractEffect> contractEffects = KtContractEffectListKt.getContractEffects(ktContractEffectList);
            List<FirExpression> list2 = list;
            PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
            for (KtContractEffect ktContractEffect : contractEffects) {
                PsiRawFirBuilder$Visitor$buildOrLazyExpression$1 psiRawFirBuilder$Visitor$buildOrLazyExpression$1 = new PsiRawFirBuilder$Visitor$buildOrLazyExpression$1(AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, ktContractEffect, null, 1, null));
                int i = WhenMappings.$EnumSwitchMapping$0[PsiRawFirBuilder.this.getMode().ordinal()];
                if (i == 1) {
                    Object objAccept = KtContractEffectKt.getExpression(ktContractEffect).accept(this, (Object) null);
                    objAccept.getClass();
                    objInvoke = (FirExpression) objAccept;
                } else {
                    if (i != 2) {
                        bu8.a();
                        return;
                    }
                    int i2 = WhenMappings.$EnumSwitchMapping$0[PsiRawFirBuilder.this.getMode().ordinal()];
                    if (i2 == 1) {
                        objInvoke = psiRawFirBuilder$Visitor$buildOrLazyExpression$1.invoke();
                    } else {
                        if (i2 != 2) {
                            bu8.a();
                            return;
                        }
                        objInvoke = AstLoadingFilter.disallowTreeLoading(new PsiRawFirBuilder$runOnStubs$1(psiRawFirBuilder$Visitor$buildOrLazyExpression$1));
                    }
                }
                list2.add((FirExpression) objInvoke);
            }
        }

        private final List<FirElement> extractReplElements(KtScript script, FirRegularClassSymbol containingDeclarationSymbol, Map<FirPropertySymbol, FirProperty> copiedDelegatedProperties) throws Throwable {
            final PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
            List listCreateListBuilder = CollectionsKt.createListBuilder();
            Iterator it = script.getDeclarations().iterator();
            while (it.hasNext()) {
                KtDestructuringDeclaration ktDestructuringDeclaration = (KtDeclaration) it.next();
                if (ktDestructuringDeclaration instanceof KtScriptInitializer) {
                    listCreateListBuilder.add(buildAnonymousInitializer((KtAnonymousInitializer) ktDestructuringDeclaration, containingDeclarationSymbol, it.hasNext(), true));
                } else if (ktDestructuringDeclaration instanceof KtDestructuringDeclaration) {
                    KtDestructuringDeclaration ktDestructuringDeclaration2 = ktDestructuringDeclaration;
                    final FirVariable firVariableGenerateTemporaryVariable$default = PsiConversionUtilsKt.generateTemporaryVariable$default(psiRawFirBuilder.getBaseModuleData(), AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, ktDestructuringDeclaration, null, 1, null), "destruct", toFirExpression(ktDestructuringDeclaration2.getInitializer(), "Initializer required for destructuring declaration", ktDestructuringDeclaration), null, new Function2() { // from class: zqb
                        public final Object invoke(Object obj, Object obj2) {
                            return PsiRawFirBuilder.Visitor.extractReplElements$lambda$0$0(this.b, (KtAnnotated) obj, (FirAnnotationContainerBuilder) obj2);
                        }
                    }, 16, null);
                    listCreateListBuilder.add(firVariableGenerateTemporaryVariable$default);
                    PsiConversionUtilsKt.addDestructuringVariables(this, psiRawFirBuilder, listCreateListBuilder, psiRawFirBuilder.getBaseModuleData(), ktDestructuringDeclaration2, firVariableGenerateTemporaryVariable$default, false, false, new Function1() { // from class: arb
                        public final Object invoke(Object obj) {
                            return PsiRawFirBuilder.Visitor.extractReplElements$lambda$0$1(psiRawFirBuilder, firVariableGenerateTemporaryVariable$default, (FirVariable) obj);
                        }
                    });
                } else if (ktDestructuringDeclaration instanceof KtProperty) {
                    KtProperty ktProperty = (KtProperty) ktDestructuringDeclaration;
                    FirProperty firProperty = toFirProperty(ktProperty, containingDeclarationSymbol, psiRawFirBuilder.getContext());
                    if (firProperty.getDelegate() != null) {
                        copiedDelegatedProperties.put(firProperty.getSymbol(), toFirProperty(ktProperty, containingDeclarationSymbol, psiRawFirBuilder.getContext()));
                    }
                    listCreateListBuilder.add(firProperty);
                } else {
                    FirStatement firStatement = toFirStatement(ktDestructuringDeclaration);
                    if (!(firStatement instanceof FirDeclaration)) {
                        k2d.a("unexpected declaration type in script");
                        return null;
                    }
                    listCreateListBuilder.add(firStatement);
                }
            }
            return CollectionsKt.build(listCreateListBuilder);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit extractReplElements$lambda$0$0(Visitor visitor, KtAnnotated ktAnnotated, FirAnnotationContainerBuilder firAnnotationContainerBuilder) {
            ktAnnotated.getClass();
            firAnnotationContainerBuilder.getClass();
            visitor.extractAnnotationsTo(ktAnnotated, firAnnotationContainerBuilder);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit extractReplElements$lambda$0$1(PsiRawFirBuilder psiRawFirBuilder, FirVariable firVariable, FirVariable firVariable2) {
            firVariable2.getClass();
            psiRawFirBuilder.configureScriptDestructuringDeclarationEntry(firVariable2, firVariable);
            return Unit.INSTANCE;
        }

        private final Pair<FirTypeRef, Map<Integer, FirFieldSymbol>> extractSuperTypeListEntriesTo(KtClassOrObject ktClassOrObject, FirClassBuilder firClassBuilder, FirTypeRef firTypeRef, FirTypeRef firTypeRef2, ClassKind classKind, List<? extends FirTypeParameterRef> list, boolean z) {
            FirTypeRef firTypeRef3;
            ConeKotlinType coneType;
            ArrayList arrayList = new ArrayList();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            int i = 0;
            KtSuperTypeEntry ktSuperTypeEntry = null;
            FirTypeRef implicitAnyType = null;
            for (Object obj : ktClassOrObject.getSuperTypeListEntries()) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                KtSuperTypeEntry ktSuperTypeEntry2 = (KtSuperTypeListEntry) obj;
                if (ktSuperTypeEntry2 instanceof KtSuperTypeEntry) {
                    firClassBuilder.getSuperTypeRefs().add(toFirOrErrorType(ktSuperTypeEntry2.getTypeReference()));
                } else if (ktSuperTypeEntry2 instanceof KtSuperTypeCallEntry) {
                    implicitAnyType = toFirOrErrorType(((KtSuperTypeCallEntry) ktSuperTypeEntry2).getCalleeExpression().getTypeReference());
                    firClassBuilder.getSuperTypeRefs().add(implicitAnyType);
                    arrayList.add(TuplesKt.to(ktSuperTypeEntry2, implicitAnyType));
                    ktSuperTypeEntry = ktSuperTypeEntry2;
                } else if (ktSuperTypeEntry2 instanceof KtDelegatedSuperTypeEntry) {
                    KtDelegatedSuperTypeEntry ktDelegatedSuperTypeEntry = (KtDelegatedSuperTypeEntry) ktSuperTypeEntry2;
                    FirTypeRef firOrErrorType = toFirOrErrorType(ktDelegatedSuperTypeEntry.getTypeReference());
                    firClassBuilder.getSuperTypeRefs().add(firOrErrorType);
                    linkedHashMap.put(Integer.valueOf(i), buildFieldForSupertypeDelegate(ktDelegatedSuperTypeEntry, firOrErrorType, linkedHashMap.size()).getSymbol());
                }
                i = i2;
            }
            boolean z2 = ktClassOrObject instanceof KtClass;
            if (z2 && classKind == ClassKind.ENUM_CLASS && ktSuperTypeEntry == null) {
                PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
                FirResolvedTypeRefBuilder firResolvedTypeRefBuilder = new FirResolvedTypeRefBuilder();
                firResolvedTypeRefBuilder.setConeType(new ConeClassLikeTypeImpl(psiRawFirBuilder.getImplicitEnumType().getConeType().getLookupTag(), (firTypeRef == null || (coneType = FirTypeUtilsKt.getConeType(firTypeRef)) == null) ? new ConeTypeProjection[0] : new ConeKotlinType[]{coneType}, false, null, 8, null));
                KtSourceElement source = firClassBuilder.getSource();
                firResolvedTypeRefBuilder.setSource(source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.EnumSuperTypeRef.INSTANCE, null, 2, null) : null);
                implicitAnyType = firResolvedTypeRefBuilder.build();
                firClassBuilder.getSuperTypeRefs().add(implicitAnyType);
            } else if (z2 && classKind == ClassKind.ANNOTATION_CLASS) {
                firClassBuilder.getSuperTypeRefs().add(PsiRawFirBuilder.this.getImplicitAnnotationType());
                implicitAnyType = PsiRawFirBuilder.this.getImplicitAnyType();
            }
            ClassId currentClassId = PsiRawFirBuilder.this.getContext().getCurrentClassId();
            StandardClassIds standardClassIds = StandardClassIds.INSTANCE;
            boolean zAreEqual = Intrinsics.areEqual(currentClassId, standardClassIds.getAny());
            FirTypeRef implicitAnyType2 = (classKind == ClassKind.ENUM_ENTRY && z2) ? firTypeRef2 == null ? PsiRawFirBuilder.this.getImplicitAnyType() : firTypeRef2 : (!firClassBuilder.getSuperTypeRefs().isEmpty() || zAreEqual) ? FirImplicitTypeRefImplWithoutSource.INSTANCE : PsiRawFirBuilder.this.getImplicitAnyType();
            if (firClassBuilder.getSuperTypeRefs().isEmpty() && !zAreEqual) {
                if (!Intrinsics.areEqual(currentClassId, standardClassIds.getNothing())) {
                    firClassBuilder.getSuperTypeRefs().add(PsiRawFirBuilder.this.getImplicitAnyType());
                }
                implicitAnyType = PsiRawFirBuilder.this.getImplicitAnyType();
            }
            if (implicitAnyType != null) {
                implicitAnyType2 = implicitAnyType;
            }
            boolean z3 = ktClassOrObject.getPrimaryConstructor() != null || (!ktClassOrObject.hasSecondaryConstructors() && !z && (!z2 || !((KtClass) ktClassOrObject).isInterface()));
            if (z3 || ktSuperTypeEntry != null) {
                firTypeRef3 = implicitAnyType2;
                firClassBuilder.getDeclarations().add(toFirConstructor(ktClassOrObject.getPrimaryConstructor(), (KtSuperTypeCallEntry) ktSuperTypeEntry, firTypeRef3, firTypeRef == null ? implicitAnyType2 : firTypeRef, ktClassOrObject, list, arrayList, z, true, !z3, PsiRawFirBuilder.this.isImplicitlyActual(firClassBuilder.getStatus(), classKind), zAreEqual));
            } else {
                firTypeRef3 = implicitAnyType2;
            }
            Collection collectionValues = linkedHashMap.values();
            List<FirDeclaration> declarations = firClassBuilder.getDeclarations();
            Iterator it = collectionValues.iterator();
            while (it.hasNext()) {
                declarations.add((FirField) ((FirFieldSymbol) it.next()).getFir());
            }
            if (linkedHashMap.isEmpty()) {
                linkedHashMap = null;
            }
            return TuplesKt.to(firTypeRef3, linkedHashMap);
        }

        private final FirTypeParameter extractTypeParameter(KtTypeParameter parameter, FirBasedSymbol<?> declarationSymbol) {
            Name nameSpecial;
            KtSimpleNameExpression referenceExpression;
            List qualifier;
            FirQualifierPart firQualifierPart;
            Name nameAsSafeName = parameter.getNameAsSafeName();
            nameAsSafeName.getClass();
            PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
            FirTypeParameterBuilder firTypeParameterBuilder = new FirTypeParameterBuilder();
            firTypeParameterBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, parameter, null, 1, null));
            firTypeParameterBuilder.setModuleData(psiRawFirBuilder.getBaseModuleData());
            firTypeParameterBuilder.setOrigin(FirDeclarationOrigin.Source.INSTANCE);
            firTypeParameterBuilder.setName(nameAsSafeName);
            firTypeParameterBuilder.setSymbol(new FirTypeParameterSymbol());
            firTypeParameterBuilder.setContainingDeclarationSymbol(declarationSymbol);
            Variance variance = parameter.getVariance();
            variance.getClass();
            firTypeParameterBuilder.setVariance(variance);
            firTypeParameterBuilder.setReified(parameter.hasModifier(KtTokens.REIFIED_KEYWORD));
            extractAnnotationsTo((KtAnnotated) parameter, (FirAnnotationContainerBuilder) firTypeParameterBuilder);
            KtElement extendsBound = parameter.getExtendsBound();
            if (extendsBound != null) {
                List<FirTypeRef> bounds = firTypeParameterBuilder.getBounds();
                FirElement firElementConvertElement = convertElement(extendsBound, null);
                if (firElementConvertElement == null) {
                    x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.types.FirTypeRef");
                    return null;
                }
                bounds.add((FirTypeRef) firElementConvertElement);
            }
            KtTypeParameterListOwner parentOfType = PsiTreeUtil.getParentOfType(parameter, KtTypeParameterListOwner.class, true);
            if (parentOfType != null) {
                for (KtTypeConstraint ktTypeConstraint : parentOfType.getTypeConstraints()) {
                    KtSimpleNameExpression subjectTypeParameterName = ktTypeConstraint.getSubjectTypeParameterName();
                    if (Intrinsics.areEqual(subjectTypeParameterName != null ? subjectTypeParameterName.getReferencedNameAsName() : null, nameAsSafeName)) {
                        firTypeParameterBuilder.getBounds().add(toFirOrErrorType(ktTypeConstraint.getBoundTypeReference()));
                    }
                    for (KtCallElement ktCallElement : ktTypeConstraint.getAnnotationEntries()) {
                        List<FirAnnotation> annotations = firTypeParameterBuilder.getAnnotations();
                        FirErrorAnnotationCallBuilder firErrorAnnotationCallBuilder = new FirErrorAnnotationCallBuilder();
                        ktCallElement.getClass();
                        firErrorAnnotationCallBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, ktCallElement, null, 1, null));
                        KtAnnotationUseSiteTarget useSiteTarget = ktCallElement.getUseSiteTarget();
                        firErrorAnnotationCallBuilder.setUseSiteTarget(useSiteTarget != null ? useSiteTarget.getAnnotationUseSiteTarget() : null);
                        firErrorAnnotationCallBuilder.setAnnotationTypeRef(toFirOrErrorType(ktCallElement.getTypeReference()));
                        firErrorAnnotationCallBuilder.setDiagnostic(new ConeSimpleDiagnostic("Type parameter annotations are not allowed inside where clauses", DiagnosticKind.AnnotationInWhereClause));
                        FirUserTypeRef annotationTypeRef = firErrorAnnotationCallBuilder.getAnnotationTypeRef();
                        FirUserTypeRef firUserTypeRef = annotationTypeRef instanceof FirUserTypeRef ? annotationTypeRef : null;
                        if (firUserTypeRef == null || (qualifier = firUserTypeRef.getQualifier()) == null || (firQualifierPart = (FirQualifierPart) CollectionsKt.last(qualifier)) == null || (nameSpecial = firQualifierPart.getName()) == null) {
                            nameSpecial = Name.special("<no-annotation-name>");
                            nameSpecial.getClass();
                        }
                        FirSimpleNamedReferenceBuilder firSimpleNamedReferenceBuilder = new FirSimpleNamedReferenceBuilder();
                        KtTypeReference typeReference = ktCallElement.getTypeReference();
                        KtTypeElement typeElement = typeReference != null ? typeReference.getTypeElement() : null;
                        KtUserType ktUserType = typeElement instanceof KtUserType ? (KtUserType) typeElement : null;
                        firSimpleNamedReferenceBuilder.setSource((ktUserType == null || (referenceExpression = ktUserType.getReferenceExpression()) == null) ? null : AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, referenceExpression, null, 1, null));
                        firSimpleNamedReferenceBuilder.setName(nameSpecial);
                        firErrorAnnotationCallBuilder.setCalleeReference(firSimpleNamedReferenceBuilder.build());
                        extractArgumentsTo(ktCallElement, firErrorAnnotationCallBuilder);
                        List<FirTypeProjection> typeArguments = firErrorAnnotationCallBuilder.getTypeArguments();
                        List<? extends KtTypeProjection> typeArguments2 = ktCallElement.getTypeArguments();
                        typeArguments2.getClass();
                        appendTypeArguments(typeArguments, typeArguments2);
                        firErrorAnnotationCallBuilder.setContainingDeclarationSymbol(psiRawFirBuilder.getContext().getContainerSymbol());
                        annotations.add(firErrorAnnotationCallBuilder.mo288build());
                    }
                }
                FirDeclarationBuildingUtilsKt.addDefaultBoundIfNecessary(firTypeParameterBuilder);
            }
            return firTypeParameterBuilder.mo288build();
        }

        private final void extractTypeParametersTo(KtTypeParameterListOwner ktTypeParameterListOwner, FirTypeParametersOwnerBuilder firTypeParametersOwnerBuilder, FirBasedSymbol<?> firBasedSymbol) {
            for (KtTypeParameter ktTypeParameter : ktTypeParameterListOwner.getTypeParameters()) {
                List<FirTypeParameter> typeParameters = firTypeParametersOwnerBuilder.getTypeParameters();
                ktTypeParameter.getClass();
                typeParameters.add(extractTypeParameter(ktTypeParameter, firBasedSymbol));
            }
        }

        private final void extractValueParametersTo(KtDeclarationWithBody ktDeclarationWithBody, FirFunctionBuilder firFunctionBuilder, FirFunctionSymbol<?> firFunctionSymbol, AbstractRawFirBuilder.ValueParameterDeclaration valueParameterDeclaration, FirTypeRef firTypeRef, List<? extends FirAnnotation> list) {
            for (KtParameter ktParameter : ktDeclarationWithBody.getValueParameters()) {
                List<FirValueParameter> valueParameters = firFunctionBuilder.getValueParameters();
                ktParameter.getClass();
                Visitor visitor = this;
                valueParameters.add(visitor.toFirValueParameter(ktParameter, firTypeRef, firFunctionSymbol, valueParameterDeclaration, list));
                this = visitor;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ void extractValueParametersTo$default(Visitor visitor, KtDeclarationWithBody ktDeclarationWithBody, FirFunctionBuilder firFunctionBuilder, FirFunctionSymbol firFunctionSymbol, AbstractRawFirBuilder.ValueParameterDeclaration valueParameterDeclaration, FirTypeRef firTypeRef, List list, int i, Object obj) {
            if (obj != null) {
                c41.a("Super calls with default arguments not supported in this target, function: extractValueParametersTo");
                return;
            }
            if ((i & 8) != 0) {
                firTypeRef = null;
            }
            FirTypeRef firTypeRef2 = firTypeRef;
            if ((i & 16) != 0) {
                list = CollectionsKt.emptyList();
            }
            visitor.extractValueParametersTo(ktDeclarationWithBody, firFunctionBuilder, firFunctionSymbol, valueParameterDeclaration, firTypeRef2, list);
        }

        private final <T extends FirDeclaration & FirTypeParameterRefsOwner> void fillDanglingConstraintsTo(KtTypeParameterListOwner ktTypeParameterListOwner, T t) {
            Name referencedNameAsName;
            List typeParameters = ktTypeParameterListOwner.getTypeParameters();
            typeParameters.getClass();
            ArrayList arrayList = new ArrayList();
            Iterator it = typeParameters.iterator();
            while (it.hasNext()) {
                Name nameAsName = ((KtTypeParameter) it.next()).getNameAsName();
                if (nameAsName != null) {
                    arrayList.add(nameAsName);
                }
            }
            Set set = CollectionsKt.toSet(arrayList);
            List<KtTypeConstraint> typeConstraints = ktTypeParameterListOwner.getTypeConstraints();
            typeConstraints.getClass();
            PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
            ArrayList arrayList2 = new ArrayList();
            for (KtTypeConstraint ktTypeConstraint : typeConstraints) {
                KtSimpleNameExpression subjectTypeParameterName = ktTypeConstraint.getSubjectTypeParameterName();
                DanglingTypeConstraint danglingTypeConstraint = null;
                if (subjectTypeParameterName != null && (referencedNameAsName = subjectTypeParameterName.getReferencedNameAsName()) != null && !set.contains(referencedNameAsName)) {
                    KtSimpleNameExpression subjectTypeParameterName2 = ktTypeConstraint.getSubjectTypeParameterName();
                    subjectTypeParameterName2.getClass();
                    danglingTypeConstraint = new DanglingTypeConstraint(referencedNameAsName, AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, subjectTypeParameterName2, null, 1, null));
                }
                if (danglingTypeConstraint != null) {
                    arrayList2.add(danglingTypeConstraint);
                }
            }
            if (arrayList2.isEmpty()) {
                return;
            }
            DeclarationAttributesKt.setDanglingTypeConstraints(t, arrayList2);
        }

        private final boolean functionCallHasLabel(PsiElement psi) {
            Object obj;
            Object next;
            Object objSingleOrNull;
            PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
            Iterator it = SequencesKt.toList(PsiUtilsKt.getAllChildren(psi)).iterator();
            do {
                obj = null;
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
            } while (!Intrinsics.areEqual(psiRawFirBuilder.getElementType((PsiElement) next), KtNodeTypes.CALL_EXPRESSION));
            if (next == null) {
                return false;
            }
            for (Object obj2 : SequencesKt.toList(PsiUtilsKt.getAllChildren((PsiElement) next))) {
                if (Intrinsics.areEqual(psiRawFirBuilder.getElementType((PsiElement) obj2), KtNodeTypes.LAMBDA_ARGUMENT)) {
                    obj = obj2;
                    break;
                }
            }
            if (obj == null || (objSingleOrNull = CollectionsKt.singleOrNull(SequencesKt.toList(PsiUtilsKt.getAllChildren((PsiElement) obj)))) == null) {
                return false;
            }
            return Intrinsics.areEqual(psiRawFirBuilder.getElementType((PsiElement) objSingleOrNull), KtNodeTypes.LABELED_EXPRESSION);
        }

        private final KtValueArgumentList getErrorValueArgumentList(KtCallableReferenceExpression ktCallableReferenceExpression) {
            List childrenOfTypeAsList = PsiTreeUtil.getChildrenOfTypeAsList(ktCallableReferenceExpression, PsiErrorElement.class);
            childrenOfTypeAsList.getClass();
            Iterator it = childrenOfTypeAsList.iterator();
            while (it.hasNext()) {
                List childrenOfTypeAsList2 = PsiTreeUtil.getChildrenOfTypeAsList((PsiErrorElement) it.next(), KtValueArgumentList.class);
                childrenOfTypeAsList2.getClass();
                KtValueArgumentList ktValueArgumentList = (KtValueArgumentList) CollectionsKt.firstOrNull(childrenOfTypeAsList2);
                if (ktValueArgumentList != null) {
                    return ktValueArgumentList;
                }
            }
            return null;
        }

        public static /* synthetic */ void getName$annotations(KtDestructuringDeclarationEntry ktDestructuringDeclarationEntry) {
        }

        private final FqName getProperPackageFqName(KtFile ktFile) {
            FqName packageName;
            KtPackageDirective packageDirective = ktFile.getPackageDirective();
            return (packageDirective == null || (packageName = parsePackageName(packageDirective)) == null) ? FqName.ROOT : packageName;
        }

        private final boolean getUsedAsExpression(KtExpression ktExpression) {
            KtTryExpression ktTryExpression;
            KtCatchClause parent = ktExpression.getParent();
            while (true) {
                PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
                parent.getClass();
                if (!Intrinsics.areEqual(psiRawFirBuilder.getElementType((PsiElement) parent), KtNodeTypes.ANNOTATED_EXPRESSION)) {
                    PsiRawFirBuilder psiRawFirBuilder2 = PsiRawFirBuilder.this;
                    parent.getClass();
                    if (!Intrinsics.areEqual(psiRawFirBuilder2.getElementType((PsiElement) parent), KtNodeTypes.LABELED_EXPRESSION)) {
                        break;
                    }
                }
                parent = parent.getParent();
            }
            PsiRawFirBuilder psiRawFirBuilder3 = PsiRawFirBuilder.this;
            parent.getClass();
            IElementType elementType = psiRawFirBuilder3.getElementType((PsiElement) parent);
            boolean z = false;
            if (Intrinsics.areEqual(elementType, KtNodeTypes.THEN) || Intrinsics.areEqual(elementType, KtNodeTypes.ELSE) || Intrinsics.areEqual(elementType, KtNodeTypes.WHEN_ENTRY)) {
                KtTryExpression parent2 = parent.getParent();
                ktTryExpression = parent2 instanceof KtExpression ? (KtExpression) parent2 : null;
                if (ktTryExpression != null && !getUsedAsExpression(ktTryExpression)) {
                    z = true;
                }
                return !z;
            }
            if ((parent instanceof KtBlockExpression) || (parent instanceof KtTryExpression)) {
                return Intrinsics.areEqual(_get_usedAsExpression_$getLastChildExpression(parent), ktExpression) && getUsedAsExpression((KtExpression) parent);
            }
            if (parent instanceof KtCatchClause) {
                KtTryExpression parent3 = parent.getParent();
                ktTryExpression = parent3 instanceof KtTryExpression ? parent3 : null;
                return ktTryExpression != null && getUsedAsExpression(ktTryExpression);
            }
            if (!(parent instanceof KtClassInitializer) && !(parent instanceof KtScriptInitializer) && !(parent instanceof KtSecondaryConstructor) && !(parent instanceof KtFunctionLiteral) && !(parent instanceof KtFinallySection)) {
                if (parent instanceof KtDotQualifiedExpression) {
                    return Intrinsics.areEqual(((KtDotQualifiedExpression) parent).getFirstChild(), ktExpression);
                }
                if (!(parent instanceof KtFunction) && !(parent instanceof KtPropertyAccessor)) {
                    if (!(parent instanceof KtContainerNodeForControlStructureBody)) {
                        return true;
                    }
                    PsiRawFirBuilder psiRawFirBuilder4 = PsiRawFirBuilder.this;
                    PsiElement parent4 = ((KtContainerNodeForControlStructureBody) parent).getParent();
                    parent4.getClass();
                    IElementType elementType2 = psiRawFirBuilder4.getElementType(parent4);
                    return (Intrinsics.areEqual(elementType2, KtNodeTypes.FOR) || Intrinsics.areEqual(elementType2, KtNodeTypes.WHILE) || Intrinsics.areEqual(elementType2, KtNodeTypes.DO_WHILE)) ? false : true;
                }
                KtDeclarationWithBody ktDeclarationWithBody = (KtDeclarationWithBody) parent;
                if (ktDeclarationWithBody.hasBody() && !ktDeclarationWithBody.hasBlockBody()) {
                    return true;
                }
            }
            return false;
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
        public static IElementType h(PsiElement psiElement) throws KotlinIllegalArgumentExceptionWithAttachments {
            psiElement.getClass();
            if (psiElement instanceof KtLiteralStringTemplateEntry) {
                IElementType iElementType = KtNodeTypes.LITERAL_STRING_TEMPLATE_ENTRY;
                iElementType.getClass();
                return iElementType;
            }
            if (psiElement instanceof KtEscapeStringTemplateEntry) {
                IElementType iElementType2 = KtNodeTypes.ESCAPE_STRING_TEMPLATE_ENTRY;
                iElementType2.getClass();
                return iElementType2;
            }
            if (psiElement instanceof KtSimpleNameStringTemplateEntry) {
                IElementType iElementType3 = KtNodeTypes.SHORT_STRING_TEMPLATE_ENTRY;
                iElementType3.getClass();
                return iElementType3;
            }
            if (psiElement instanceof KtBlockStringTemplateEntry) {
                IElementType iElementType4 = KtNodeTypes.LONG_STRING_TEMPLATE_ENTRY;
                iElementType4.getClass();
                return iElementType4;
            }
            KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("invalid node type " + Reflection.getOrCreateKotlinClass(psiElement.getClass()), (Throwable) null);
            ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
            ExceptionAttachementBuilderUtilsKt.withPsiEntry(exceptionAttachmentBuilder, "element", psiElement);
            kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
            throw kotlinIllegalArgumentExceptionWithAttachments;
        }

        private final boolean isCallTheFirstStatement(PsiElement psi) {
            PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
            List mutableList = CollectionsKt.toMutableList(CollectionsKt.asReversed(SequencesKt.toList(PsiUtilsKt.getAllChildren(psi))));
            while (true) {
                List list = mutableList;
                if (!list.isEmpty()) {
                    PsiElement psiElement = (PsiElement) AddToStdlibKt.popLast(mutableList);
                    IElementType elementType = psiRawFirBuilder.getElementType(psiElement);
                    if (!Intrinsics.areEqual(elementType, KtTokens.LBRACE) && !Intrinsics.areEqual(elementType, KtTokens.WHITE_SPACE) && !Intrinsics.areEqual(elementType, KtTokens.DOT) && !Intrinsics.areEqual(elementType, KtTokens.EOL_COMMENT)) {
                        if (!Intrinsics.areEqual(elementType, KtNodeTypes.CALL_EXPRESSION)) {
                            if (!Intrinsics.areEqual(elementType, KtNodeTypes.REFERENCE_EXPRESSION)) {
                                if (!Intrinsics.areEqual(elementType, KtNodeTypes.DOT_QUALIFIED_EXPRESSION)) {
                                    if (!Intrinsics.areEqual(elementType, KtNodeTypes.ANNOTATION_ENTRY)) {
                                        if (!Intrinsics.areEqual(elementType, KtNodeTypes.ANNOTATED_EXPRESSION)) {
                                            break;
                                        }
                                        CollectionsKt.addAll(list, CollectionsKt.asReversed(SequencesKt.toList(PsiUtilsKt.getAllChildren(psiElement))));
                                    } else {
                                        continue;
                                    }
                                } else {
                                    CollectionsKt.addAll(list, CollectionsKt.asReversed(SequencesKt.toList(PsiUtilsKt.getAllChildren(psiElement))));
                                }
                            } else {
                                continue;
                            }
                        } else {
                            return true;
                        }
                    }
                } else {
                    break;
                }
            }
            return false;
        }

        public static String k(KtStringTemplateExpression ktStringTemplateExpression) {
            String text;
            KtStringInterpolationPrefix interpolationPrefix = ktStringTemplateExpression.getInterpolationPrefix();
            return (interpolationPrefix == null || (text = interpolationPrefix.getText()) == null) ? Argument.Delimiters.none : text;
        }

        public static Collection n(Visitor visitor, PsiElement psiElement, String str) {
            str.getClass();
            psiElement.getClass();
            List expressions = ((KtStringTemplateEntryWithExpression) psiElement).getExpressions();
            expressions.getClass();
            List<KtExpression> list = expressions;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            for (KtExpression ktExpression : list) {
                ktExpression.getClass();
                arrayList.add(visitor.toFirExpression(ktExpression, str));
            }
            return arrayList;
        }

        public static FirExpression o(Visitor visitor, PsiElement psiElement) {
            psiElement.getClass();
            return visitor.toFirExpression((KtExpression) psiElement, "Incorrect expression inside inc/dec");
        }

        private final FirContractDescription obtainContractDescription(KtDeclarationWithBody ktDeclarationWithBody) {
            KtContractEffectList contractDescription = ktDeclarationWithBody.getContractDescription();
            if (contractDescription == null) {
                return null;
            }
            PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
            FirRawContractDescriptionBuilder firRawContractDescriptionBuilder = new FirRawContractDescriptionBuilder();
            firRawContractDescriptionBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, contractDescription, null, 1, null));
            extractRawEffects(contractDescription, firRawContractDescriptionBuilder.getRawEffects());
            return firRawContractDescriptionBuilder.build();
        }

        private final ConeClassLikeType obtainDispatchReceiverForConstructor(KtClassOrObject ktClassOrObject) {
            if (PsiUtilsKt.hasInnerModifier(ktClassOrObject)) {
                return PsiRawFirBuilder.this.dispatchReceiverForInnerClassConstructor();
            }
            return null;
        }

        private final FirDeclarationStatus obtainPropertyComponentStatus(Visibility componentVisibility, KtDeclaration declaration, KtProperty property) {
            FirDeclarationStatusImpl firDeclarationStatusImpl = new FirDeclarationStatusImpl(componentVisibility, declaration != null ? PsiRawFirBuilder.this.getModality(declaration) : null);
            KtModifierKeywordToken ktModifierKeywordToken = KtTokens.INLINE_KEYWORD;
            boolean z = false;
            firDeclarationStatusImpl.setInline(property.hasModifier(ktModifierKeywordToken) || (declaration != null && declaration.hasModifier(ktModifierKeywordToken)));
            KtModifierKeywordToken ktModifierKeywordToken2 = KtTokens.EXTERNAL_KEYWORD;
            firDeclarationStatusImpl.setExternal(property.hasModifier(ktModifierKeywordToken2) || (declaration != null && declaration.hasModifier(ktModifierKeywordToken2)));
            if (declaration != null && declaration.hasModifier(KtTokens.LATEINIT_KEYWORD)) {
                z = true;
            }
            firDeclarationStatusImpl.setLateInit(z);
            return firDeclarationStatusImpl;
        }

        private final FqName parsePackageName(KtPackageDirective node) {
            FqName fqNameChild = FqName.ROOT;
            List packageNames = node.getPackageNames();
            packageNames.getClass();
            Iterator it = packageNames.iterator();
            while (it.hasNext()) {
                Name nameIdentifier = Name.identifier(((KtSimpleNameExpression) it.next()).getReferencedName());
                nameIdentifier.getClass();
                fqNameChild = fqNameChild.child(nameIdentifier);
            }
            return fqNameChild;
        }

        public static Unit r(Visitor visitor, KtAnnotated ktAnnotated, FirAnnotationContainerBuilder firAnnotationContainerBuilder) {
            ktAnnotated.getClass();
            firAnnotationContainerBuilder.getClass();
            visitor.extractAnnotationsTo(ktAnnotated, firAnnotationContainerBuilder);
            return Unit.INSTANCE;
        }

        /* JADX WARN: Multi-variable type inference failed */
        private final CalleeAndReceiver splitToCalleeAndReceiver(KtExpression calleeExpression, KtPsiSourceElement defaultSource) {
            KtPsiSourceElement source;
            FirReference calleeReference;
            KtExpression expression;
            KtParenthesizedExpression ktParenthesizedExpression = calleeExpression instanceof KtParenthesizedExpression ? (KtParenthesizedExpression) calleeExpression : null;
            FirExpression firExpression = (ktParenthesizedExpression == null || (expression = ktParenthesizedExpression.getExpression()) == null) ? 0 : toFirExpression(expression, "Incorrect invoke receiver");
            if (calleeExpression instanceof KtSimpleNameExpression) {
                PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
                FirSimpleNamedReferenceBuilder firSimpleNamedReferenceBuilder = new FirSimpleNamedReferenceBuilder();
                firSimpleNamedReferenceBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, calleeExpression, null, 1, null));
                firSimpleNamedReferenceBuilder.setName(((KtSimpleNameExpression) calleeExpression).getReferencedNameAsName());
                return new CalleeAndReceiver(firSimpleNamedReferenceBuilder.build(), null, 2, null);
            }
            boolean z = calleeExpression instanceof KtSuperExpression;
            if (z || (firExpression instanceof FirSuperReceiverExpression)) {
                KtSuperExpression ktSuperExpression = z ? (KtSuperExpression) calleeExpression : null;
                if (ktSuperExpression == null || (source = AbstractRawFirBuilder.toFirSourceElement$default(PsiRawFirBuilder.this, ktSuperExpression, null, 1, null)) == null) {
                    FirResolvable firResolvable = firExpression instanceof FirResolvable ? (FirResolvable) firExpression : null;
                    source = (firResolvable == null || (calleeReference = firResolvable.getCalleeReference()) == null) ? null : calleeReference.getSource();
                }
                return new CalleeAndReceiver(FirReferenceUtilsKt.buildErrorNamedReferenceWithNoName(new ConeSimpleDiagnostic("Super cannot be a callee", DiagnosticKind.SuperNotAllowed), source), null, 2, null);
            }
            if (firExpression != 0) {
                FirSimpleNamedReferenceBuilder firSimpleNamedReferenceBuilder2 = new FirSimpleNamedReferenceBuilder();
                firSimpleNamedReferenceBuilder2.setSource(KtSourceElementKt.fakeElement$default(defaultSource, KtFakeSourceElementKind.ImplicitInvokeCall.INSTANCE, null, 2, null));
                firSimpleNamedReferenceBuilder2.setName(OperatorNameConventions.INVOKE);
                return new CalleeAndReceiver(firSimpleNamedReferenceBuilder2.build(), firExpression);
            }
            if (calleeExpression == null) {
                return new CalleeAndReceiver(FirReferenceUtilsKt.buildErrorNamedReferenceWithNoName(new ConeSyntaxDiagnostic("Call has no callee"), defaultSource), null, 2, null);
            }
            FirSimpleNamedReferenceBuilder firSimpleNamedReferenceBuilder3 = new FirSimpleNamedReferenceBuilder();
            firSimpleNamedReferenceBuilder3.setSource(KtSourceElementKt.fakeElement$default(defaultSource, KtFakeSourceElementKind.ImplicitInvokeCall.INSTANCE, null, 2, null));
            firSimpleNamedReferenceBuilder3.setName(OperatorNameConventions.INVOKE);
            return new CalleeAndReceiver(firSimpleNamedReferenceBuilder3.build(), toFirExpression(calleeExpression, "Incorrect invoke receiver"));
        }

        public static String t(KtParameter ktParameter) {
            ASTNode node;
            PsiElement nameIdentifier = ktParameter.getNameIdentifier();
            if (nameIdentifier == null || (node = nameIdentifier.getNode()) == null) {
                return null;
            }
            return node.getText();
        }

        private final FirBackingField toFirBackingField(KtBackingField ktBackingField, KtProperty ktProperty, FirPropertySymbol firPropertySymbol, FirTypeRef firTypeRef, List<? extends FirAnnotationCall> list) {
            FirDeclarationStatus firDeclarationStatusObtainPropertyComponentStatus = obtainPropertyComponentStatus(Visibilities.Private.INSTANCE, ktBackingField, ktProperty);
            FirExpression initializerExpression = ktBackingField != null ? toInitializerExpression(ktBackingField) : null;
            FirTypeRef firOrImplicitType = toFirOrImplicitType(ktBackingField != null ? ktBackingField.getTypeReference() : null);
            KtPsiSourceElement firSourceElement$default = ktBackingField != null ? AbstractRawFirBuilder.toFirSourceElement$default(PsiRawFirBuilder.this, ktBackingField, null, 1, null) : null;
            PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
            if (ktBackingField == null) {
                FirModuleData baseModuleData = psiRawFirBuilder.getBaseModuleData();
                FirDeclarationOrigin.Source source = FirDeclarationOrigin.Source.INSTANCE;
                PsiRawFirBuilder psiRawFirBuilder2 = PsiRawFirBuilder.this;
                KtFakeSourceElementKind.DefaultAccessor defaultAccessor = KtFakeSourceElementKind.DefaultAccessor.INSTANCE;
                return new FirDefaultPropertyBackingField(baseModuleData, source, psiRawFirBuilder2.toFirSourceElement((PsiElement) ktProperty, (KtFakeSourceElementKind) defaultAccessor), CollectionsKt.toMutableList(list), UtilsKt.copyWithNewSourceKind(firTypeRef, defaultAccessor), ktProperty.isVar(), firPropertySymbol, firDeclarationStatusObtainPropertyComponentStatus, null, 256, null);
            }
            FirBackingFieldBuilder firBackingFieldBuilder = new FirBackingFieldBuilder();
            firBackingFieldBuilder.setSource(firSourceElement$default);
            firBackingFieldBuilder.setModuleData(psiRawFirBuilder.getBaseModuleData());
            firBackingFieldBuilder.setOrigin(FirDeclarationOrigin.Source.INSTANCE);
            firBackingFieldBuilder.setReturnTypeRef(firOrImplicitType);
            firBackingFieldBuilder.setStatus(firDeclarationStatusObtainPropertyComponentStatus);
            extractAnnotationsTo((KtAnnotated) ktBackingField, (FirAnnotationContainerBuilder) firBackingFieldBuilder);
            CollectionsKt.addAll(firBackingFieldBuilder.getAnnotations(), list);
            firBackingFieldBuilder.setName(StandardNames.BACKING_FIELD);
            firBackingFieldBuilder.setSymbol(new FirBackingFieldSymbol());
            firBackingFieldBuilder.setPropertySymbol(firPropertySymbol);
            firBackingFieldBuilder.setInitializer(initializerExpression);
            firBackingFieldBuilder.setVar(ktProperty.isVar());
            firBackingFieldBuilder.setVal(!ktProperty.isVar());
            return firBackingFieldBuilder.mo288build();
        }

        /* JADX WARN: Code duplicated, block: B:14:0x003d  */
        private final FirBlock toFirBlock(KtExpression ktExpression) {
            FirBlock firBlock;
            if (ktExpression instanceof KtBlockExpression) {
                Object objAccept = ((KtBlockExpression) ktExpression).accept(this, (Object) null);
                objAccept.getClass();
                return (FirBlock) objAccept;
            }
            if (ktExpression == null) {
                return FirEmptyExpressionBlockBuilderKt.buildEmptyExpressionBlock();
            }
            if (ktExpression instanceof KtAnnotatedExpression) {
                PsiElement[] children = ((KtAnnotatedExpression) ktExpression).getChildren();
                children.getClass();
                PsiElement psiElement = (PsiElement) ArraysKt.lastOrNull(children);
                if (psiElement instanceof KtBlockExpression) {
                    firBlock = toFirBlock((KtExpression) psiElement);
                    extractAnnotationsTo((KtAnnotated) ktExpression, firBlock);
                } else {
                    firBlock = null;
                }
            } else {
                firBlock = null;
            }
            if (firBlock != null) {
                return firBlock;
            }
            FirElement firElementConvertElement = convertElement(ktExpression, null);
            if (firElementConvertElement != null) {
                return new FirSingleExpressionBlock((FirStatement) firElementConvertElement);
            }
            x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.expressions.FirStatement");
            return null;
        }

        public static /* synthetic */ FirConstructor toFirConstructor$default(Visitor visitor, KtPrimaryConstructor ktPrimaryConstructor, KtSuperTypeCallEntry ktSuperTypeCallEntry, FirTypeRef firTypeRef, FirTypeRef firTypeRef2, KtClassOrObject ktClassOrObject, List list, List list2, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, int i, Object obj) {
            if (obj != null) {
                c41.a("Super calls with default arguments not supported in this target, function: toFirConstructor");
                return null;
            }
            if ((i & 256) != 0) {
                z3 = false;
            }
            if ((i & 512) != 0) {
                z4 = false;
            }
            if ((i & 1024) != 0) {
                z5 = false;
            }
            return visitor.toFirConstructor(ktPrimaryConstructor, ktSuperTypeCallEntry, firTypeRef, firTypeRef2, ktClassOrObject, list, list2, z, z2, z3, z4, z5);
        }

        private static final FirDelegatedConstructorCall toFirConstructor$lambda$0$buildDelegatedCall(PsiRawFirBuilder psiRawFirBuilder, boolean z, Visitor visitor, KtPsiSourceElement ktPsiSourceElement, KtSuperTypeCallEntry ktSuperTypeCallEntry, FirTypeRef firTypeRef) {
            Object objMo288build;
            KtConstructorCalleeExpression calleeExpression;
            KtSourceElement firSourceElement;
            KtSourceElement ktSourceElementFakeElement$default = null;
            KtPsiSourceElement firSourceElement$default = ktSuperTypeCallEntry != null ? AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, ktSuperTypeCallEntry, null, 1, null) : null;
            if (z) {
                firTypeRef = UtilsKt.copyWithNewSourceKind(firTypeRef, KtFakeSourceElementKind.ImplicitTypeRef.INSTANCE);
            }
            PsiRawFirBuilder$Visitor$buildOrLazyDelegatedConstructorCall$1 psiRawFirBuilder$Visitor$buildOrLazyDelegatedConstructorCall$1 = new PsiRawFirBuilder$Visitor$buildOrLazyDelegatedConstructorCall$1(false, firTypeRef);
            int i = WhenMappings.$EnumSwitchMapping$0[PsiRawFirBuilder.this.getMode().ordinal()];
            if (i == 1) {
                FirDelegatedConstructorCallBuilder firDelegatedConstructorCallBuilder = new FirDelegatedConstructorCallBuilder();
                if (firSourceElement$default == null) {
                    firSourceElement$default = KtSourceElementKt.fakeElement$default(ktPsiSourceElement, KtFakeSourceElementKind.DelegatingConstructorCall.INSTANCE, null, 2, null);
                }
                firDelegatedConstructorCallBuilder.setSource(firSourceElement$default);
                firDelegatedConstructorCallBuilder.setConstructedTypeRef(firTypeRef);
                firDelegatedConstructorCallBuilder.setThis(false);
                FirExplicitSuperReferenceBuilder firExplicitSuperReferenceBuilder = new FirExplicitSuperReferenceBuilder();
                if (ktSuperTypeCallEntry == null || (calleeExpression = ktSuperTypeCallEntry.getCalleeExpression()) == null || (firSourceElement = psiRawFirBuilder.toFirSourceElement((PsiElement) calleeExpression, (KtFakeSourceElementKind) KtFakeSourceElementKind.DelegatingConstructorCall.INSTANCE)) == null) {
                    KtSourceElement source = firDelegatedConstructorCallBuilder.getSource();
                    if (source != null) {
                        ktSourceElementFakeElement$default = KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.DelegatingConstructorCall.INSTANCE, null, 2, null);
                    }
                } else {
                    ktSourceElementFakeElement$default = firSourceElement;
                }
                firExplicitSuperReferenceBuilder.setSource(ktSourceElementFakeElement$default);
                firExplicitSuperReferenceBuilder.setSuperTypeRef(firDelegatedConstructorCallBuilder.getConstructedTypeRef());
                firDelegatedConstructorCallBuilder.setCalleeReference(firExplicitSuperReferenceBuilder.build());
                if (ktSuperTypeCallEntry != null) {
                    visitor.extractArgumentsTo(ktSuperTypeCallEntry, firDelegatedConstructorCallBuilder);
                }
                objMo288build = firDelegatedConstructorCallBuilder.mo288build();
            } else {
                if (i != 2) {
                    bu8.a();
                    return null;
                }
                int i2 = WhenMappings.$EnumSwitchMapping$0[PsiRawFirBuilder.this.getMode().ordinal()];
                if (i2 == 1) {
                    objMo288build = psiRawFirBuilder$Visitor$buildOrLazyDelegatedConstructorCall$1.invoke();
                } else {
                    if (i2 != 2) {
                        bu8.a();
                        return null;
                    }
                    objMo288build = AstLoadingFilter.disallowTreeLoading(new PsiRawFirBuilder$runOnStubs$1(psiRawFirBuilder$Visitor$buildOrLazyDelegatedConstructorCall$1));
                }
            }
            return (FirDelegatedConstructorCall) objMo288build;
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
        /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
        /* JADX WARN: Multi-variable type inference failed */
        private final FirDeclaration toFirDeclaration(KtDeclaration ktDeclaration, FirTypeRef firTypeRef, FirResolvedTypeRef firResolvedTypeRef, KtClassOrObject ktClassOrObject, FirClassBuilder firClassBuilder, List<? extends FirTypeParameterRef> list) throws KotlinIllegalStateExceptionWithAttachments, UninitializedPropertyAccessException {
            boolean zIsEmpty;
            List valueParameters;
            FirTypeRef firTypeRef2;
            if (ktDeclaration instanceof KtSecondaryConstructor) {
                KtSecondaryConstructor ktSecondaryConstructor = (KtSecondaryConstructor) ktDeclaration;
                if (ktSecondaryConstructor.isDelegatedCallToThis()) {
                    firTypeRef2 = firTypeRef;
                    firTypeRef2 = firResolvedTypeRef;
                }
                firTypeRef2 = firTypeRef;
                FirConstructor firConstructor = toFirConstructor(ktSecondaryConstructor, firTypeRef2, firResolvedTypeRef, ktClassOrObject, list);
                if (PsiRawFirBuilder.this.isDirectlyInsideCompanionBlock()) {
                    ClassMembersKt.setIllegalCompanionBlockMember(firConstructor, Boolean.TRUE);
                }
                return firConstructor;
            }
            boolean z = true;
            if (!(ktDeclaration instanceof KtEnumEntry)) {
                if (ktDeclaration instanceof KtProperty) {
                    return convertProperty((KtProperty) ktDeclaration, ConversionUtilsKt.getOwnerRegularOrAnonymousObjectSymbol(firClassBuilder));
                }
                if (ktDeclaration instanceof KtDestructuringDeclaration) {
                    FirExpression initializerExpression = toInitializerExpression((KtDeclarationWithInitializer) ktDeclaration);
                    PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
                    return psiRawFirBuilder.buildErrorNonLocalDestructuringDeclaration(AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, ktDeclaration, null, 1, null), initializerExpression);
                }
                if (ktDeclaration instanceof KtClassInitializer) {
                    FirAnonymousInitializer firAnonymousInitializerBuildAnonymousInitializer$default = buildAnonymousInitializer$default(this, (KtAnonymousInitializer) ktDeclaration, ConversionUtilsKt.getOwnerRegularOrAnonymousObjectSymbol(firClassBuilder), false, false, 12, null);
                    if (PsiRawFirBuilder.this.isDirectlyInsideCompanionBlock()) {
                        ClassMembersKt.setIllegalCompanionBlockMember(firAnonymousInitializerBuildAnonymousInitializer$default, Boolean.TRUE);
                    }
                    return firAnonymousInitializerBuildAnonymousInitializer$default;
                }
                FirElement firElementConvertElement = convertElement(ktDeclaration, null);
                if (firElementConvertElement != null) {
                    return (FirDeclaration) firElementConvertElement;
                }
                x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.declarations.FirDeclaration");
                return null;
            }
            KtPrimaryConstructor primaryConstructor = ktClassOrObject.getPrimaryConstructor();
            if (primaryConstructor == null || (valueParameters = primaryConstructor.getValueParameters()) == null) {
                List secondaryConstructors = ktClassOrObject.getSecondaryConstructors();
                if (!secondaryConstructors.isEmpty()) {
                    List list2 = secondaryConstructors;
                    if (!(list2 instanceof Collection) || !list2.isEmpty()) {
                        Iterator it = list2.iterator();
                        do {
                            if (!it.hasNext()) {
                                z = false;
                                break;
                            }
                        } while (!((KtSecondaryConstructor) it.next()).getValueParameters().isEmpty());
                    } else {
                        z = false;
                        break;
                    }
                }
                zIsEmpty = z;
            } else {
                zIsEmpty = valueParameters.isEmpty();
            }
            return toFirEnumEntry((KtEnumEntry) ktDeclaration, firResolvedTypeRef, zIsEmpty);
        }

        private final FirExpression toFirExpression(KtElement ktElement, String str, KtElement ktElement2) {
            KtSourceElement firSourceElement$default;
            KtSourceElement source;
            KtSourceElement firSourceElement;
            if (ktElement == null) {
                return FirExpressionUtilKt.buildErrorExpression$default(PsiRawFirBuilder.this.toFirSourceElement((PsiElement) ktElement2, (KtFakeSourceElementKind) KtFakeSourceElementKind.ErrorExpression.INSTANCE), new ConeSyntaxDiagnostic(str), null, 4, null);
            }
            FirElement firElementConvertElement = convertElement(ktElement, null);
            if (!(firElementConvertElement instanceof FirExpression)) {
                PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
                FirErrorExpressionBuilder firErrorExpressionBuilder = new FirErrorExpressionBuilder();
                firErrorExpressionBuilder.setNonExpressionElement(firElementConvertElement);
                firErrorExpressionBuilder.setDiagnostic(firElementConvertElement == null ? new ConeSyntaxDiagnostic(str) : new ConeSimpleDiagnostic(str, DiagnosticKind.ExpressionExpected));
                if (firElementConvertElement == null || (source = firElementConvertElement.getSource()) == null || (firSourceElement$default = KtSourceElementKt.realElement(source)) == null) {
                    firSourceElement$default = AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, ktElement, null, 1, null);
                }
                firErrorExpressionBuilder.setSource(firSourceElement$default);
                return firErrorExpressionBuilder.mo288build();
            }
            FirExpression firExpression = (FirExpression) firElementConvertElement;
            if (!UtilsKt.isStatementLikeExpression(firExpression)) {
                return checkSelectorInvariant(ktElement, firExpression);
            }
            PsiRawFirBuilder psiRawFirBuilder2 = PsiRawFirBuilder.this;
            FirErrorExpressionBuilder firErrorExpressionBuilder2 = new FirErrorExpressionBuilder();
            firErrorExpressionBuilder2.setNonExpressionElement(firElementConvertElement);
            firErrorExpressionBuilder2.setDiagnostic(new ConeSimpleDiagnostic(str, DiagnosticKind.ExpressionExpected));
            KtSourceElement source2 = firExpression.getSource();
            if (source2 == null || (firSourceElement = KtSourceElementKt.realElement(source2)) == null) {
                firSourceElement = psiRawFirBuilder2.toFirSourceElement((PsiElement) ktElement2, (KtFakeSourceElementKind) KtFakeSourceElementKind.ErrorExpression.INSTANCE);
            }
            firErrorExpressionBuilder2.setSource(firSourceElement);
            return firErrorExpressionBuilder2.mo288build();
        }

        private final FirTypeRef toFirOrImplicitType(KtTypeReference ktTypeReference) {
            FirTypeRef firType;
            return (ktTypeReference == null || (firType = toFirType(ktTypeReference)) == null) ? FirImplicitTypeRefImplWithoutSource.INSTANCE : firType;
        }

        private final FirTypeRef toFirOrUnitType(KtTypeReference ktTypeReference) {
            FirTypeRef firType;
            return (ktTypeReference == null || (firType = toFirType(ktTypeReference)) == null) ? PsiRawFirBuilder.this.getImplicitUnitType() : firType;
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
        /* JADX WARN: Code duplicated, block: B:18:0x003a  */
        private final <T> FirProperty toFirProperty(KtProperty ktProperty, FirClassSymbol<?> firClassSymbol, Context<T> context) throws Throwable {
            Name nameAsSafeName;
            PsiRawFirBuilder psiRawFirBuilder;
            FirPropertyBuilder firPropertyBuilder;
            List<? extends KtContextParameterList> list;
            Object firSourceElement;
            PsiElement expression;
            Object obj;
            Object objInvoke;
            KtPsiSourceElement firSourceElement2;
            final Visitor visitor = this;
            KtProperty ktProperty2 = ktProperty;
            boolean z = context.getContainingScriptSymbol() != null && Intrinsics.areEqual(context.getClassName(), FqName.ROOT);
            if (ktProperty2.isLocal() || z) {
                PsiElement nameIdentifier = ktProperty2.getNameIdentifier();
                if (Intrinsics.areEqual(nameIdentifier != null ? nameIdentifier.getText() : null, InlineClassManglingUtilsKt.NOT_INLINE_CLASS_PARAMETER_PLACEHOLDER)) {
                    nameAsSafeName = SpecialNames.UNDERSCORE_FOR_UNUSED_VAR;
                } else {
                    nameAsSafeName = ktProperty2.getNameAsSafeName();
                    nameAsSafeName.getClass();
                }
            } else {
                nameAsSafeName = ktProperty2.getNameAsSafeName();
                nameAsSafeName.getClass();
            }
            FirPropertySymbol firLocalPropertySymbol = ktProperty2.isLocal() ? new FirLocalPropertySymbol() : new FirRegularPropertySymbol(PsiRawFirBuilder.this.callableIdForName(nameAsSafeName));
            boolean zIsDirectlyInsideCompanionBlock = PsiRawFirBuilder.this.isDirectlyInsideCompanionBlock();
            PsiRawFirBuilder psiRawFirBuilder2 = PsiRawFirBuilder.this;
            boolean zIsLocal = ktProperty2.isLocal();
            PsiRawFirBuilder psiRawFirBuilder3 = PsiRawFirBuilder.this;
            if (!zIsLocal) {
                psiRawFirBuilder2.getContext().pushContainerSymbol(firLocalPropertySymbol);
            }
            try {
                FirTypeRef firOrImplicitType = visitor.toFirOrImplicitType(ktProperty2.getTypeReference());
                boolean zIsVar = ktProperty2.isVar();
                FirExpression initializerExpression = toInitializerExpression(ktProperty);
                KtSourceElement ktSourceElement = (KtPsiSourceElement) AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder3, ktProperty2, null, 1, null);
                FirPropertyBuilder firPropertyBuilder2 = new FirPropertyBuilder();
                firPropertyBuilder2.setSource(ktSourceElement);
                firPropertyBuilder2.setModuleData(psiRawFirBuilder3.getBaseModuleData());
                firPropertyBuilder2.setOrigin(FirDeclarationOrigin.Source.INSTANCE);
                firPropertyBuilder2.setReturnTypeRef(firOrImplicitType);
                firPropertyBuilder2.setName(nameAsSafeName);
                firPropertyBuilder2.setVar(zIsVar);
                final KtTypeReference receiverTypeReference = ktProperty2.getReceiverTypeReference();
                firPropertyBuilder2.setReceiverParameter(receiverTypeReference != null ? ConversionUtilsKt.createReceiverParameter(psiRawFirBuilder3, new Function0() { // from class: irb
                    public final Object invoke() {
                        return this.b.toFirType(receiverTypeReference);
                    }
                }, firPropertyBuilder2.getModuleData(), firLocalPropertySymbol) : null);
                firPropertyBuilder2.setInitializer(initializerExpression);
                firPropertyBuilder2.setLocal(context.getInLocalContext());
                List arrayList = new ArrayList();
                for (KtElement ktElement : ktProperty2.getAnnotationEntries()) {
                    ktElement.getClass();
                    FirElement firElementConvertElement = visitor.convertElement(ktElement, null);
                    if (firElementConvertElement == null) {
                        throw new NullPointerException("null cannot be cast to non-null type org.jetbrains.kotlin.fir.expressions.FirAnnotationCall");
                    }
                    arrayList.add((FirAnnotationCall) firElementConvertElement);
                }
                try {
                    if (ktProperty2.isLocal()) {
                        firPropertyBuilder2.setSymbol(firLocalPropertySymbol);
                        visitor.extractTypeParametersTo(ktProperty2, firPropertyBuilder2, firPropertyBuilder2.getSymbol());
                        psiRawFirBuilder = psiRawFirBuilder2;
                        firPropertyBuilder2.setBackingField(visitor.toFirBackingField(ktProperty2.getFieldDeclaration(), ktProperty2, firPropertyBuilder2.getSymbol(), firOrImplicitType, CollectionsKt.emptyList()));
                        FirDeclarationStatusImpl firDeclarationStatusImpl = new FirDeclarationStatusImpl(Visibilities.Local.INSTANCE, Modality.FINAL);
                        firDeclarationStatusImpl.setLateInit(ktProperty2.hasModifier(KtTokens.LATEINIT_KEYWORD));
                        firPropertyBuilder2.setStatus(firDeclarationStatusImpl);
                        if (ktProperty2.hasDelegate()) {
                            FirWrappedDelegateExpressionBuilder firWrappedDelegateExpressionBuilder = new FirWrappedDelegateExpressionBuilder();
                            FirExpression firProperty$lambda$2$0$extractDelegateExpression = toFirProperty$lambda$2$0$extractDelegateExpression(this, ktProperty);
                            KtSourceElement source = firProperty$lambda$2$0$extractDelegateExpression.getSource();
                            if (source == null || (firSourceElement2 = KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.WrappedDelegate.INSTANCE, null, 2, null)) == null) {
                                PsiElement delegate = ktProperty2.getDelegate();
                                firSourceElement2 = delegate != null ? psiRawFirBuilder3.toFirSourceElement(delegate, KtFakeSourceElementKind.WrappedDelegate.INSTANCE) : null;
                            }
                            firWrappedDelegateExpressionBuilder.setSource(firSourceElement2);
                            firWrappedDelegateExpressionBuilder.setExpression(firProperty$lambda$2$0$extractDelegateExpression);
                            ConversionUtilsKt.generateAccessorsByDelegate$default(firPropertyBuilder2, firWrappedDelegateExpressionBuilder, psiRawFirBuilder3.getBaseModuleData(), null, context, false, null, null, null, ktSourceElement, 224, null);
                            firPropertyBuilder = firPropertyBuilder2;
                            list = null;
                        } else {
                            firPropertyBuilder = firPropertyBuilder2;
                            list = null;
                        }
                    } else {
                        psiRawFirBuilder = psiRawFirBuilder2;
                        firPropertyBuilder2.setSymbol(firLocalPropertySymbol);
                        if (!zIsDirectlyInsideCompanionBlock) {
                            firPropertyBuilder2.setDispatchReceiverType(psiRawFirBuilder3.currentDispatchReceiverType());
                        }
                        visitor.extractTypeParametersTo(ktProperty2, firPropertyBuilder2, firPropertyBuilder2.getSymbol());
                        psiRawFirBuilder3.addCapturedTypeParameters(true, ktSourceElement, firPropertyBuilder2.getTypeParameters());
                        try {
                            KtBackingField fieldDeclaration = ktProperty2.getFieldDeclaration();
                            FirPropertySymbol symbol = firPropertyBuilder2.getSymbol();
                            ArrayList arrayList2 = new ArrayList();
                            for (Object obj2 : arrayList) {
                                FirAnnotationCall firAnnotationCall = (FirAnnotationCall) obj2;
                                if (firAnnotationCall.getUseSiteTarget() == AnnotationUseSiteTarget.FIELD || firAnnotationCall.getUseSiteTarget() == AnnotationUseSiteTarget.PROPERTY_DELEGATE_FIELD) {
                                    arrayList2.add(obj2);
                                }
                            }
                            firPropertyBuilder2.setBackingField(visitor.toFirBackingField(fieldDeclaration, ktProperty2, symbol, firOrImplicitType, arrayList2));
                            firPropertyBuilder2.setGetter(toFirPropertyAccessor(ktProperty.getGetter(), ktProperty, firOrImplicitType, firPropertyBuilder2.getSymbol(), true, ConversionUtilsKt.filterUseSiteTarget(arrayList, AnnotationUseSiteTarget.PROPERTY_GETTER), CollectionsKt.emptyList(), zIsDirectlyInsideCompanionBlock));
                            visitor = this;
                            ktProperty2 = ktProperty;
                            firPropertyBuilder2.setSetter(visitor.toFirPropertyAccessor(ktProperty.getSetter(), ktProperty2, firOrImplicitType, firPropertyBuilder2.getSymbol(), false, ConversionUtilsKt.filterUseSiteTarget(arrayList, AnnotationUseSiteTarget.PROPERTY_SETTER), ConversionUtilsKt.filterUseSiteTarget(arrayList, AnnotationUseSiteTarget.SETTER_PARAMETER), zIsDirectlyInsideCompanionBlock));
                            FirDeclarationStatusImpl firDeclarationStatusImpl2 = new FirDeclarationStatusImpl(PsiRawFirBuilder.getVisibility$default(psiRawFirBuilder3, ktProperty2, false, 1, null), psiRawFirBuilder3.getModality(ktProperty2));
                            firDeclarationStatusImpl2.setExpect(PsiUtilsKt.hasExpectModifier(ktProperty2) || psiRawFirBuilder3.getContext().getContainerIsExpect());
                            firDeclarationStatusImpl2.setActual(PsiUtilsKt.hasActualModifier(ktProperty2));
                            firDeclarationStatusImpl2.setOverride(ktProperty2.hasModifier(KtTokens.OVERRIDE_KEYWORD));
                            firDeclarationStatusImpl2.setConst(ktProperty2.hasModifier(KtTokens.CONST_KEYWORD));
                            firDeclarationStatusImpl2.setLateInit(ktProperty2.hasModifier(KtTokens.LATEINIT_KEYWORD));
                            firDeclarationStatusImpl2.setExternal(ktProperty2.hasModifier(KtTokens.EXTERNAL_KEYWORD));
                            firDeclarationStatusImpl2.setStatic(ktProperty2.hasModifier(KtTokens.COMPANION_KEYWORD) || zIsDirectlyInsideCompanionBlock);
                            firPropertyBuilder2.setStatus(firDeclarationStatusImpl2);
                            if (ktProperty2.hasDelegate()) {
                                KtFakeSourceElementKind ktFakeSourceElementKind = KtFakeSourceElementKind.WrappedDelegate.INSTANCE;
                                final KtPsiSourceElement firSourceElement3 = psiRawFirBuilder3.toFirSourceElement((PsiElement) ktProperty2, ktFakeSourceElementKind);
                                final FirWrappedDelegateExpressionBuilder firWrappedDelegateExpressionBuilder2 = new FirWrappedDelegateExpressionBuilder();
                                FirExpression firProperty$lambda$2$0$3$extractDelegateExpression = toFirProperty$lambda$2$0$3$extractDelegateExpression(visitor, firSourceElement3, ktProperty2);
                                Function0 function0 = new Function0() { // from class: jrb
                                    public final Object invoke() {
                                        return PsiRawFirBuilder.Visitor.toFirProperty$lambda$2$0$3$3$1(firSourceElement3);
                                    }
                                };
                                BodyBuildingMode mode = PsiRawFirBuilder.this.getMode();
                                int[] iArr = WhenMappings.$EnumSwitchMapping$0;
                                int i = iArr[mode.ordinal()];
                                if (i == 1) {
                                    PsiElement delegate2 = ktProperty2.getDelegate();
                                    if (delegate2 != null && (expression = delegate2.getExpression()) != null) {
                                        delegate2 = expression;
                                    }
                                    firSourceElement = delegate2 != null ? psiRawFirBuilder3.toFirSourceElement(delegate2, ktFakeSourceElementKind) : null;
                                } else {
                                    if (i != 2) {
                                        throw new NoWhenBranchMatchedException();
                                    }
                                    int i2 = WhenMappings.$EnumSwitchMapping$0[PsiRawFirBuilder.this.getMode().ordinal()];
                                    if (i2 == 1) {
                                        firSourceElement = function0.invoke();
                                    } else {
                                        if (i2 != 2) {
                                            throw new NoWhenBranchMatchedException();
                                        }
                                        firSourceElement = AstLoadingFilter.disallowTreeLoading(new PsiRawFirBuilder$runOnStubs$1(function0));
                                    }
                                }
                                firWrappedDelegateExpressionBuilder2.setSource((KtSourceElement) firSourceElement);
                                firWrappedDelegateExpressionBuilder2.setExpression(firProperty$lambda$2$0$3$extractDelegateExpression);
                                Function0 function1 = new Function0() { // from class: krb
                                    public final Object invoke() {
                                        return PsiRawFirBuilder.Visitor.toFirProperty$lambda$2$0$3$5(firWrappedDelegateExpressionBuilder2);
                                    }
                                };
                                int i3 = iArr[PsiRawFirBuilder.this.getMode().ordinal()];
                                boolean z2 = true;
                                if (i3 == 1) {
                                    list = null;
                                    obj = TuplesKt.to(null, null);
                                } else {
                                    if (i3 != 2) {
                                        throw new NoWhenBranchMatchedException();
                                    }
                                    int i4 = WhenMappings.$EnumSwitchMapping$0[PsiRawFirBuilder.this.getMode().ordinal()];
                                    z2 = true;
                                    if (i4 == 1) {
                                        objInvoke = function1.invoke();
                                    } else {
                                        if (i4 != 2) {
                                            throw new NoWhenBranchMatchedException();
                                        }
                                        objInvoke = AstLoadingFilter.disallowTreeLoading(new PsiRawFirBuilder$runOnStubs$1(function1));
                                    }
                                    obj = objInvoke;
                                    list = null;
                                }
                                Pair pair = (Pair) obj;
                                firPropertyBuilder = firPropertyBuilder2;
                                ConversionUtilsKt.generateAccessorsByDelegate(firPropertyBuilder, firWrappedDelegateExpressionBuilder2, psiRawFirBuilder3.getBaseModuleData(), firClassSymbol, context, ktProperty2.getReceiverTypeReference() != null ? z2 : false, (FirLazyExpression) pair.component1(), (FirLazyBlock) pair.component2(), new PsiRawFirBuilder$Visitor$toFirProperty$2$1$3$5(psiRawFirBuilder3), ktSourceElement);
                            } else {
                                firPropertyBuilder = firPropertyBuilder2;
                                list = null;
                            }
                            Unit unit = Unit.INSTANCE;
                            psiRawFirBuilder3.getContext().popFirTypeParameters();
                        } catch (Throwable th) {
                            psiRawFirBuilder3.getContext().popFirTypeParameters();
                            throw th;
                        }
                    }
                    List<FirAnnotation> annotations = firPropertyBuilder.getAnnotations();
                    if (!ktProperty2.isLocal()) {
                        arrayList = AbstractRawFirBuilderKt.filterStandalonePropertyRelevantAnnotations(arrayList, zIsVar);
                    }
                    CollectionsKt.addAll(annotations, arrayList);
                    List<FirValueParameter> contextParameters = firPropertyBuilder.getContextParameters();
                    KtModifierList modifierList = ktProperty2.getModifierList();
                    List<? extends KtContextParameterList> contextParameterLists = modifierList != null ? modifierList.getContextParameterLists() : list;
                    if (contextParameterLists == null) {
                        contextParameterLists = CollectionsKt.emptyList();
                    }
                    visitor.addContextParameters(contextParameters, contextParameterLists, firLocalPropertySymbol);
                    FirProperty firPropertyMo288build = firPropertyBuilder.mo288build();
                    if (!ktProperty2.isLocal()) {
                        visitor.fillDanglingConstraintsTo(ktProperty2, firPropertyMo288build);
                        if (zIsDirectlyInsideCompanionBlock) {
                            psiRawFirBuilder3.initContainingClassAttr(firPropertyMo288build);
                        }
                    }
                    if (!zIsLocal) {
                        psiRawFirBuilder.getContext().popContainerSymbol(firLocalPropertySymbol);
                    }
                    return firPropertyMo288build;
                } catch (Throwable th2) {
                    th = th2;
                    if (!zIsLocal) {
                        psiRawFirBuilder.getContext().popContainerSymbol(firLocalPropertySymbol);
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                psiRawFirBuilder = psiRawFirBuilder2;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final KtPsiSourceElement toFirProperty$lambda$2$0$3$3$1(KtPsiSourceElement ktPsiSourceElement) {
            return ktPsiSourceElement;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Pair toFirProperty$lambda$2$0$3$5(FirWrappedDelegateExpressionBuilder firWrappedDelegateExpressionBuilder) {
            FirLazyExpressionBuilder firLazyExpressionBuilder = new FirLazyExpressionBuilder();
            firLazyExpressionBuilder.setSource(firWrappedDelegateExpressionBuilder.getSource());
            return TuplesKt.to(firLazyExpressionBuilder.mo288build(), FirLazyBlockBuilderKt.buildLazyBlock());
        }

        private static final FirExpression toFirProperty$lambda$2$0$3$extractDelegateExpression(Visitor visitor, KtPsiSourceElement ktPsiSourceElement, KtProperty ktProperty) {
            Object firExpression;
            PsiRawFirBuilder$Visitor$buildOrLazyExpression$1 psiRawFirBuilder$Visitor$buildOrLazyExpression$1 = new PsiRawFirBuilder$Visitor$buildOrLazyExpression$1(ktPsiSourceElement);
            int i = WhenMappings.$EnumSwitchMapping$0[PsiRawFirBuilder.this.getMode().ordinal()];
            if (i == 1) {
                KtPropertyDelegate delegate = ktProperty.getDelegate();
                firExpression = visitor.toFirExpression(delegate != null ? delegate.getExpression() : null, "Should have delegate", ktProperty);
            } else {
                if (i != 2) {
                    bu8.a();
                    return null;
                }
                int i2 = WhenMappings.$EnumSwitchMapping$0[PsiRawFirBuilder.this.getMode().ordinal()];
                if (i2 == 1) {
                    firExpression = psiRawFirBuilder$Visitor$buildOrLazyExpression$1.invoke();
                } else {
                    if (i2 != 2) {
                        bu8.a();
                        return null;
                    }
                    firExpression = AstLoadingFilter.disallowTreeLoading(new PsiRawFirBuilder$runOnStubs$1(psiRawFirBuilder$Visitor$buildOrLazyExpression$1));
                }
            }
            return (FirExpression) firExpression;
        }

        private static final FirExpression toFirProperty$lambda$2$0$extractDelegateExpression(Visitor visitor, KtProperty ktProperty) {
            KtPropertyDelegate delegate = ktProperty.getDelegate();
            return visitor.toFirExpression(delegate != null ? delegate.getExpression() : null, "Incorrect delegate expression", ktProperty);
        }

        private final FirPropertyAccessor toFirPropertyAccessor(KtPropertyAccessor ktPropertyAccessor, KtProperty ktProperty, FirTypeRef firTypeRef, FirPropertySymbol firPropertySymbol, boolean z, List<? extends FirAnnotation> list, List<? extends FirAnnotation> list2, boolean z2) {
            KtPsiSourceElement ktFakePsiSourceElement;
            KtTypeReference typeReference;
            FirTypeRef firType;
            FirValueParameter firValueParameter;
            List valueParameters;
            FirTypeRef firOrUnitType;
            KtRealPsiSourceElement ktRealPsiSourceElement = null;
            Visibility visibility$default = ktPropertyAccessor != null ? PsiRawFirBuilder.getVisibility$default(PsiRawFirBuilder.this, ktPropertyAccessor, false, 1, null) : null;
            if (visibility$default == null || Intrinsics.areEqual(visibility$default, Visibilities.Unknown.INSTANCE)) {
                visibility$default = PsiRawFirBuilder.getVisibility$default(PsiRawFirBuilder.this, ktProperty, false, 1, null);
            }
            Visibility visibility = visibility$default;
            FirDeclarationStatusImpl firDeclarationStatusImpl = new FirDeclarationStatusImpl(visibility, ktPropertyAccessor != null ? PsiRawFirBuilder.this.getModality(ktPropertyAccessor) : null);
            KtModifierKeywordToken ktModifierKeywordToken = KtTokens.INLINE_KEYWORD;
            firDeclarationStatusImpl.setInline(ktProperty.hasModifier(ktModifierKeywordToken) || (ktPropertyAccessor != null && ktPropertyAccessor.hasModifier(ktModifierKeywordToken)));
            KtModifierKeywordToken ktModifierKeywordToken2 = KtTokens.EXTERNAL_KEYWORD;
            firDeclarationStatusImpl.setExternal(ktProperty.hasModifier(ktModifierKeywordToken2) || (ktPropertyAccessor != null && ktPropertyAccessor.hasModifier(ktModifierKeywordToken2)));
            KtModifierKeywordToken ktModifierKeywordToken3 = KtTokens.EXPECT_KEYWORD;
            firDeclarationStatusImpl.setExpect(ktProperty.hasModifier(ktModifierKeywordToken3) || (ktPropertyAccessor != null && ktPropertyAccessor.hasModifier(ktModifierKeywordToken3)));
            firDeclarationStatusImpl.setStatic(ktProperty.hasModifier(KtTokens.COMPANION_KEYWORD) || z2);
            FirTypeRef firTypeRefCopyWithNewSourceKind = UtilsKt.copyWithNewSourceKind(firTypeRef, KtFakeSourceElementKind.ImplicitTypeRef.INSTANCE);
            if (ktPropertyAccessor == null || !ktPropertyAccessor.hasBody()) {
                if (ktPropertyAccessor == null && !z && !ktProperty.isVar()) {
                    return null;
                }
                if (ktPropertyAccessor == null || (ktFakePsiSourceElement = AbstractRawFirBuilder.toFirSourceElement$default(PsiRawFirBuilder.this, ktPropertyAccessor, null, 1, null)) == null) {
                    KtFakeSourceElementKind.DefaultAccessor defaultAccessor = KtFakeSourceElementKind.DefaultAccessor.INSTANCE;
                    if (defaultAccessor == null) {
                        bu8.a();
                        return null;
                    }
                    ktFakePsiSourceElement = new KtFakePsiSourceElement(ktProperty, defaultAccessor);
                }
                KtParameter ktParameter = (ktPropertyAccessor == null || (valueParameters = ktPropertyAccessor.getValueParameters()) == null) ? null : (KtParameter) CollectionsKt.firstOrNull(valueParameters);
                FirDefaultPropertyAccessor.Companion companion = FirDefaultPropertyAccessor.INSTANCE;
                FirModuleData baseModuleData = PsiRawFirBuilder.this.getBaseModuleData();
                KtPsiSourceElement ktPsiSourceElement = ktFakePsiSourceElement;
                FirDeclarationOrigin.Source source = FirDeclarationOrigin.Source.INSTANCE;
                if (ktParameter != null) {
                    if (KtRealSourceElementKind.INSTANCE == null) {
                        bu8.a();
                        return null;
                    }
                    ktRealPsiSourceElement = new KtRealPsiSourceElement(ktParameter);
                }
                FirDefaultPropertyAccessor firDefaultPropertyAccessorCreateGetterOrSetter = companion.createGetterOrSetter(ktPsiSourceElement, baseModuleData, source, firTypeRefCopyWithNewSourceKind, visibility, firPropertySymbol, z, list2, ktRealPsiSourceElement);
                PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
                if (ktPropertyAccessor != null) {
                    extractAnnotationsFrom(firDefaultPropertyAccessorCreateGetterOrSetter, ktPropertyAccessor);
                }
                firDefaultPropertyAccessorCreateGetterOrSetter.replaceAnnotations(UtilsKt.smartPlus(MutableOrEmptyList.m194boximpl(firDefaultPropertyAccessorCreateGetterOrSetter.m338getAnnotations5e3fPpI()), list));
                firDefaultPropertyAccessorCreateGetterOrSetter.setStatus(firDeclarationStatusImpl);
                psiRawFirBuilder.initContainingClassAttr(firDefaultPropertyAccessorCreateGetterOrSetter);
                if (ktParameter != null && (typeReference = ktParameter.getTypeReference()) != null && (firType = toFirType(typeReference)) != null && (firValueParameter = (FirValueParameter) CollectionsKt.firstOrNull(firDefaultPropertyAccessorCreateGetterOrSetter.getValueParameters())) != null) {
                    firValueParameter.replaceReturnTypeRef(firType);
                }
                return firDefaultPropertyAccessorCreateGetterOrSetter;
            }
            KtSourceElement ktSourceElement = (KtPsiSourceElement) AbstractRawFirBuilder.toFirSourceElement$default(PsiRawFirBuilder.this, ktPropertyAccessor, null, 1, null);
            FirFunctionTarget firFunctionTarget = new FirFunctionTarget(null, false);
            PsiRawFirBuilder psiRawFirBuilder2 = PsiRawFirBuilder.this;
            FirPropertyAccessorBuilder firPropertyAccessorBuilder = new FirPropertyAccessorBuilder();
            firPropertyAccessorBuilder.setSource(ktSourceElement);
            firPropertyAccessorBuilder.setModuleData(psiRawFirBuilder2.getBaseModuleData());
            FirDeclarationOrigin.Source source2 = FirDeclarationOrigin.Source.INSTANCE;
            firPropertyAccessorBuilder.setOrigin(source2);
            if (z) {
                KtTypeReference typeReference2 = ktPropertyAccessor.getTypeReference();
                if (typeReference2 == null || (firOrUnitType = toFirType(typeReference2)) == null) {
                    firOrUnitType = firTypeRefCopyWithNewSourceKind;
                }
            } else {
                firOrUnitType = toFirOrUnitType(ktPropertyAccessor.getTypeReference());
            }
            firPropertyAccessorBuilder.setReturnTypeRef(firOrUnitType);
            firPropertyAccessorBuilder.setGetter(z);
            firPropertyAccessorBuilder.setStatus(firDeclarationStatusImpl);
            CollectionsKt.addAll(firPropertyAccessorBuilder.getAnnotations(), list);
            extractAnnotationsTo((KtAnnotated) ktPropertyAccessor, (FirAnnotationContainerBuilder) firPropertyAccessorBuilder);
            psiRawFirBuilder2.getContext().getFirFunctionTargets().add(firFunctionTarget);
            firPropertyAccessorBuilder.setSymbol(new FirPropertyAccessorSymbol());
            extractValueParametersTo(ktPropertyAccessor, firPropertyAccessorBuilder, firPropertyAccessorBuilder.getSymbol(), AbstractRawFirBuilder.ValueParameterDeclaration.SETTER, firTypeRefCopyWithNewSourceKind, list2);
            if (!z && firPropertyAccessorBuilder.getValueParameters().isEmpty()) {
                List<FirValueParameter> valueParameters2 = firPropertyAccessorBuilder.getValueParameters();
                FirDefaultSetterValueParameterBuilder firDefaultSetterValueParameterBuilder = new FirDefaultSetterValueParameterBuilder();
                firDefaultSetterValueParameterBuilder.setSource(KtSourceElementKt.fakeElement$default(ktSourceElement, KtFakeSourceElementKind.DefaultAccessor.INSTANCE, null, 2, null));
                firDefaultSetterValueParameterBuilder.setModuleData(psiRawFirBuilder2.getBaseModuleData());
                firDefaultSetterValueParameterBuilder.setOrigin(source2);
                firDefaultSetterValueParameterBuilder.setReturnTypeRef(firTypeRefCopyWithNewSourceKind);
                firDefaultSetterValueParameterBuilder.setSymbol(new FirValueParameterSymbol());
                CollectionsKt.addAll(firDefaultSetterValueParameterBuilder.getAnnotations(), list2);
                valueParameters2.add(firDefaultSetterValueParameterBuilder.mo288build());
            }
            FirContractDescription firContractDescriptionObtainContractDescription = obtainContractDescription(ktPropertyAccessor);
            boolean forceKeepingTheBodyInHeaderMode = psiRawFirBuilder2.getContext().getForceKeepingTheBodyInHeaderMode();
            psiRawFirBuilder2.getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
            boolean inLocalContext = psiRawFirBuilder2.getContext().getInLocalContext();
            psiRawFirBuilder2.getContext().setInLocalContext(true);
            FqName classNameBeforeLocalContext = psiRawFirBuilder2.getContext().getClassNameBeforeLocalContext();
            if (!inLocalContext) {
                psiRawFirBuilder2.getContext().setClassNameBeforeLocalContext(psiRawFirBuilder2.getContext().getClassName());
            }
            FqName className = psiRawFirBuilder2.getContext().getClassName();
            psiRawFirBuilder2.getContext().setClassName(FqName.ROOT);
            try {
                Pair<FirBlock, FirContractDescription> pairBuildFirBody = buildFirBody(ktPropertyAccessor);
                psiRawFirBuilder2.getContext().setClassNameBeforeLocalContext(classNameBeforeLocalContext);
                psiRawFirBuilder2.getContext().setInLocalContext(inLocalContext);
                psiRawFirBuilder2.getContext().setClassName(className);
                psiRawFirBuilder2.getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
                FirBlock firBlock = (FirBlock) pairBuildFirBody.component1();
                FirContractDescription firContractDescription = (FirContractDescription) pairBuildFirBody.component2();
                firPropertyAccessorBuilder.setBody(firBlock);
                if (firContractDescriptionObtainContractDescription == null) {
                    firContractDescriptionObtainContractDescription = firContractDescription;
                }
                if (firContractDescriptionObtainContractDescription != null) {
                    firPropertyAccessorBuilder.setContractDescription(firContractDescriptionObtainContractDescription);
                }
                firPropertyAccessorBuilder.setPropertySymbol(firPropertySymbol);
                FirPropertyAccessor firPropertyAccessorMo288build = firPropertyAccessorBuilder.mo288build();
                PsiRawFirBuilder psiRawFirBuilder3 = PsiRawFirBuilder.this;
                psiRawFirBuilder3.initContainingClassAttr(firPropertyAccessorMo288build);
                psiRawFirBuilder3.bindFunctionTarget(firFunctionTarget, firPropertyAccessorMo288build);
                psiRawFirBuilder3.removeLast(psiRawFirBuilder3.getContext().getFirFunctionTargets());
                return firPropertyAccessorMo288build;
            } catch (Throwable th) {
                psiRawFirBuilder2.getContext().setClassNameBeforeLocalContext(classNameBeforeLocalContext);
                psiRawFirBuilder2.getContext().setInLocalContext(inLocalContext);
                psiRawFirBuilder2.getContext().setClassName(className);
                psiRawFirBuilder2.getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
                throw th;
            }
        }

        private final FirStatement toFirStatement(KtExpression ktExpression) {
            FirElement firElementConvertElement = convertElement(ktExpression, null);
            if (firElementConvertElement != null) {
                return (FirStatement) firElementConvertElement;
            }
            x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.expressions.FirStatement");
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
        public final FirTypeRef toFirType(KtTypeReference ktTypeReference) throws KotlinIllegalArgumentExceptionWithAttachments {
            FirDynamicTypeRefBuilder firErrorTypeRefBuilder;
            List contextParameters;
            List listContextReceivers;
            KtTypeElement typeElement = ktTypeReference.getTypeElement();
            KtSourceElement ktSourceElement = (KtPsiSourceElement) AbstractRawFirBuilder.toFirSourceElement$default(PsiRawFirBuilder.this, ktTypeReference, null, 1, null);
            boolean z = typeElement instanceof KtNullableType;
            KtDeclarationModifierList[] firType$getAllModifierLists = toFirType$getAllModifierLists(ktTypeReference);
            List listMutableListOf = CollectionsKt.mutableListOf(Arrays.copyOf(firType$getAllModifierLists, firType$getAllModifierLists.length));
            KtFunctionType firType$unwrapNullable = toFirType$unwrapNullable(typeElement, listMutableListOf);
            if (firType$unwrapNullable instanceof KtDynamicType) {
                firErrorTypeRefBuilder = new FirDynamicTypeRefBuilder();
                firErrorTypeRefBuilder.setSource(ktSourceElement);
                firErrorTypeRefBuilder.setMarkedNullable(z);
            } else {
                boolean z2 = false;
                if (firType$unwrapNullable instanceof KtUserType) {
                    KtUserType ktUserType = (KtUserType) firType$unwrapNullable;
                    KtSimpleNameExpression referenceExpression = ktUserType.getReferenceExpression();
                    if (referenceExpression != null) {
                        firErrorTypeRefBuilder = convertKtTypeElement(ktSourceElement, z, ktUserType, referenceExpression);
                    } else {
                        FirDynamicTypeRefBuilder firErrorTypeRefBuilder2 = new FirErrorTypeRefBuilder();
                        PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
                        firErrorTypeRefBuilder2.setSource(ktSourceElement);
                        firErrorTypeRefBuilder2.setDiagnostic(new ConeSyntaxDiagnostic("Incomplete user type"));
                        KtUserType qualifier = ktUserType.getQualifier();
                        KtSimpleNameExpression referenceExpression2 = qualifier != null ? qualifier.getReferenceExpression() : null;
                        if (qualifier != null && referenceExpression2 != null) {
                            firErrorTypeRefBuilder2.setPartiallyResolvedTypeRef(convertKtTypeElement((KtPsiSourceElement) AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, qualifier, null, 1, null), false, qualifier, referenceExpression2).build());
                        }
                        firErrorTypeRefBuilder = firErrorTypeRefBuilder2;
                    }
                } else if (firType$unwrapNullable instanceof KtFunctionType) {
                    FirDynamicTypeRefBuilder firFunctionTypeRefBuilder = new FirFunctionTypeRefBuilder();
                    PsiRawFirBuilder psiRawFirBuilder2 = PsiRawFirBuilder.this;
                    firFunctionTypeRefBuilder.setSource(ktSourceElement);
                    firFunctionTypeRefBuilder.setMarkedNullable(z);
                    List list = listMutableListOf;
                    if (!(list instanceof Collection) || !list.isEmpty()) {
                        Iterator it = list.iterator();
                        while (it.hasNext()) {
                            if (PsiUtilsKt.hasSuspendModifier((KtModifierList) it.next())) {
                                z2 = true;
                                break;
                            }
                        }
                    }
                    firFunctionTypeRefBuilder.setSuspend(z2);
                    KtFunctionType ktFunctionType = firType$unwrapNullable;
                    KtTypeReference receiverTypeReference = ktFunctionType.getReceiverTypeReference();
                    firFunctionTypeRefBuilder.setReceiverTypeRef(receiverTypeReference != null ? toFirType(receiverTypeReference) : null);
                    firFunctionTypeRefBuilder.setReturnTypeRef(toFirOrErrorType(ktFunctionType.getReturnTypeReference()));
                    for (KtParameter ktParameter : ktFunctionType.getParameters()) {
                        List parameters = firFunctionTypeRefBuilder.getParameters();
                        FirFunctionTypeParameterBuilder firFunctionTypeParameterBuilder = new FirFunctionTypeParameterBuilder();
                        ktParameter.getClass();
                        KtSourceElement ktSourceElement2 = (KtPsiSourceElement) AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder2, ktParameter, null, 1, null);
                        firFunctionTypeParameterBuilder.setSource(ktSourceElement2);
                        firFunctionTypeParameterBuilder.setName(ktParameter.getNameAsName());
                        firFunctionTypeParameterBuilder.setReturnTypeRef(ktParameter.getTypeReference() != null ? toFirOrErrorType(ktParameter.getTypeReference()) : psiRawFirBuilder2.createNoTypeForParameterTypeRef(ktSourceElement2));
                        parameters.add(firFunctionTypeParameterBuilder.build());
                    }
                    KtContextParameterList contextParameterList = ktFunctionType.getContextParameterList();
                    if (contextParameterList != null && (listContextReceivers = contextParameterList.contextReceivers()) != null) {
                        List contextParameterTypeRefs = firFunctionTypeRefBuilder.getContextParameterTypeRefs();
                        Iterator it2 = listContextReceivers.iterator();
                        while (it2.hasNext()) {
                            KtTypeReference ktTypeReferenceTypeReference = ((KtContextReceiver) it2.next()).typeReference();
                            FirTypeRef firType = ktTypeReferenceTypeReference != null ? toFirType(ktTypeReferenceTypeReference) : null;
                            if (firType != null) {
                                contextParameterTypeRefs.add(firType);
                            }
                        }
                    }
                    if (contextParameterList != null && (contextParameters = contextParameterList.getContextParameters()) != null) {
                        List contextParameterTypeRefs2 = firFunctionTypeRefBuilder.getContextParameterTypeRefs();
                        Iterator it3 = contextParameters.iterator();
                        while (it3.hasNext()) {
                            KtTypeReference typeReference = ((KtParameter) it3.next()).getTypeReference();
                            FirTypeRef firType2 = typeReference != null ? toFirType(typeReference) : null;
                            if (firType2 != null) {
                                contextParameterTypeRefs2.add(firType2);
                            }
                        }
                    }
                    firErrorTypeRefBuilder = firFunctionTypeRefBuilder;
                } else if (firType$unwrapNullable instanceof KtIntersectionType) {
                    FirDynamicTypeRefBuilder firIntersectionTypeRefBuilder = new FirIntersectionTypeRefBuilder();
                    firIntersectionTypeRefBuilder.setSource(ktSourceElement);
                    firIntersectionTypeRefBuilder.setMarkedNullable(z);
                    KtIntersectionType ktIntersectionType = (KtIntersectionType) firType$unwrapNullable;
                    firIntersectionTypeRefBuilder.setLeftType(toFirOrErrorType(ktIntersectionType.getLeftTypeRef()));
                    firIntersectionTypeRefBuilder.setRightType(toFirOrErrorType(ktIntersectionType.getRightTypeRef()));
                    firErrorTypeRefBuilder = firIntersectionTypeRefBuilder;
                } else {
                    if (firType$unwrapNullable != null) {
                        KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Unexpected type element: " + Reflection.getOrCreateKotlinClass(firType$unwrapNullable.getClass()).getSimpleName(), (Throwable) null);
                        ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                        ExceptionAttachementBuilderUtilsKt.withPsiEntry(exceptionAttachmentBuilder, "unwrappedElement", firType$unwrapNullable);
                        kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                        throw kotlinIllegalArgumentExceptionWithAttachments;
                    }
                    firErrorTypeRefBuilder = new FirErrorTypeRefBuilder();
                    firErrorTypeRefBuilder.setSource(ktSourceElement);
                    firErrorTypeRefBuilder.setDiagnostic(new ConeSyntaxDiagnostic("Incomplete code"));
                }
            }
            Iterator it4 = listMutableListOf.iterator();
            while (it4.hasNext()) {
                for (KtAnnotationEntry ktAnnotationEntry : ((KtModifierList) it4.next()).getAnnotationEntries()) {
                    List<FirAnnotation> annotations = firErrorTypeRefBuilder.getAnnotations();
                    ktAnnotationEntry.getClass();
                    FirElement firElementConvertElement = convertElement(ktAnnotationEntry, null);
                    if (firElementConvertElement == null) {
                        x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.expressions.FirAnnotation");
                        return null;
                    }
                    annotations.add((FirAnnotation) firElementConvertElement);
                }
            }
            FirAnnotationContainer firAnnotationContainerMo288build = firErrorTypeRefBuilder.mo288build();
            firAnnotationContainerMo288build.getClass();
            return (FirTypeRef) firAnnotationContainerMo288build;
        }

        private static final KtDeclarationModifierList[] toFirType$getAllModifierLists(KtElementImplStub<?> ktElementImplStub) {
            KtModifierListElementType ktModifierListElementType = KtStubElementTypes.MODIFIER_LIST;
            KtDeclarationModifierList[] stubOrPsiChildren = ktElementImplStub.getStubOrPsiChildren(ktModifierListElementType, ktModifierListElementType.getArrayFactory());
            stubOrPsiChildren.getClass();
            return stubOrPsiChildren;
        }

        private static final KtTypeElement toFirType$unwrapNullable(KtTypeElement ktTypeElement, List<KtModifierList> list) {
            if (!(ktTypeElement instanceof KtNullableType)) {
                return ktTypeElement;
            }
            CollectionsKt.addAll(list, toFirType$getAllModifierLists((KtElementImplStub) ktTypeElement));
            return toFirType$unwrapNullable(((KtNullableType) ktTypeElement).getInnerType(), list);
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
        /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
        /* JADX WARN: Multi-variable type inference failed */
        private final FirValueParameter toFirValueParameter(final KtParameter ktParameter, FirTypeRef firTypeRef, FirBasedSymbol<?> firBasedSymbol, AbstractRawFirBuilder.ValueParameterDeclaration valueParameterDeclaration, List<? extends FirAnnotation> list) throws KotlinIllegalStateExceptionWithAttachments, UninitializedPropertyAccessException {
            Object firExpression;
            PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
            Name nameAsSafeName = ktParameter.getNameAsSafeName();
            nameAsSafeName.getClass();
            Name nameConvertValueParameterName = psiRawFirBuilder.convertValueParameterName(nameAsSafeName, valueParameterDeclaration, new Function0() { // from class: brb
                public final Object invoke() {
                    return PsiRawFirBuilder.Visitor.t(ktParameter);
                }
            });
            PsiRawFirBuilder psiRawFirBuilder2 = PsiRawFirBuilder.this;
            FirValueParameterBuilder firValueParameterBuilder = new FirValueParameterBuilder();
            FirExpression firExpressionMo288build = null;
            KtSourceElement ktSourceElement = (KtPsiSourceElement) AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder2, ktParameter, null, 1, null);
            firValueParameterBuilder.setSource(ktSourceElement);
            firValueParameterBuilder.setModuleData(psiRawFirBuilder2.getBaseModuleData());
            firValueParameterBuilder.setOrigin(FirDeclarationOrigin.Source.INSTANCE);
            firValueParameterBuilder.setVararg(ktParameter.isVarArg());
            firValueParameterBuilder.setName(nameConvertValueParameterName);
            firValueParameterBuilder.setSymbol(new FirValueParameterSymbol());
            FirValueParameterSymbol symbol = firValueParameterBuilder.getSymbol();
            boolean isAnnotationOwner = valueParameterDeclaration.getIsAnnotationOwner();
            if (isAnnotationOwner) {
                psiRawFirBuilder2.getContext().pushContainerSymbol(symbol);
            }
            try {
                Object objCreateNoTypeForParameterTypeRef = firTypeRef;
                if (ktParameter.getTypeReference() != null) {
                    objCreateNoTypeForParameterTypeRef = toFirOrErrorType(ktParameter.getTypeReference());
                } else if (firTypeRef == null) {
                    objCreateNoTypeForParameterTypeRef = valueParameterDeclaration.getShouldExplicitParameterTypeBePresent() ? psiRawFirBuilder2.createNoTypeForParameterTypeRef(ktSourceElement) : toFirOrImplicitType(null);
                }
                FirTypeRef firTypeRefWrapIntoArray = objCreateNoTypeForParameterTypeRef;
                if (firValueParameterBuilder.getIsVararg() && (objCreateNoTypeForParameterTypeRef instanceof FirErrorTypeRef)) {
                    firTypeRefWrapIntoArray = objCreateNoTypeForParameterTypeRef;
                    firTypeRefWrapIntoArray = ConversionUtilsKt.wrapIntoArray((FirErrorTypeRef) objCreateNoTypeForParameterTypeRef);
                }
                firTypeRefWrapIntoArray = objCreateNoTypeForParameterTypeRef;
                firValueParameterBuilder.setReturnTypeRef(firTypeRefWrapIntoArray);
                addAnnotationsFrom(firValueParameterBuilder, ktParameter, valueParameterDeclaration == AbstractRawFirBuilder.ValueParameterDeclaration.PRIMARY_CONSTRUCTOR);
                Unit unit = Unit.INSTANCE;
                if (isAnnotationOwner) {
                    psiRawFirBuilder2.getContext().popContainerSymbol(symbol);
                }
                if (ktParameter.hasDefaultValue()) {
                    if (valueParameterDeclaration == AbstractRawFirBuilder.ValueParameterDeclaration.CONTEXT_PARAMETER) {
                        FirErrorExpressionBuilder firErrorExpressionBuilder = new FirErrorExpressionBuilder();
                        firErrorExpressionBuilder.setSource(psiRawFirBuilder2.toFirSourceElement((PsiElement) ktParameter, (KtFakeSourceElementKind) KtFakeSourceElementKind.ContextParameterDefaultValue.INSTANCE));
                        firErrorExpressionBuilder.setDiagnostic(ConeContextParameterWithDefaultValue.INSTANCE);
                        firExpressionMo288build = firErrorExpressionBuilder.mo288build();
                    } else {
                        PsiRawFirBuilder$Visitor$buildOrLazyExpression$1 psiRawFirBuilder$Visitor$buildOrLazyExpression$1 = new PsiRawFirBuilder$Visitor$buildOrLazyExpression$1(null);
                        int i = WhenMappings.$EnumSwitchMapping$0[PsiRawFirBuilder.this.getMode().ordinal()];
                        if (i == 1) {
                            firExpression = toFirExpression(ktParameter.getDefaultValue(), "Should have default value", ktParameter);
                        } else {
                            if (i != 2) {
                                bu8.a();
                                return null;
                            }
                            int i2 = WhenMappings.$EnumSwitchMapping$0[PsiRawFirBuilder.this.getMode().ordinal()];
                            if (i2 == 1) {
                                firExpression = psiRawFirBuilder$Visitor$buildOrLazyExpression$1.invoke();
                            } else {
                                if (i2 != 2) {
                                    bu8.a();
                                    return null;
                                }
                                firExpression = AstLoadingFilter.disallowTreeLoading(new PsiRawFirBuilder$runOnStubs$1(psiRawFirBuilder$Visitor$buildOrLazyExpression$1));
                            }
                        }
                        firExpressionMo288build = (FirExpression) firExpression;
                    }
                }
                firValueParameterBuilder.setDefaultValue(firExpressionMo288build);
                firValueParameterBuilder.setCrossinline(ktParameter.hasModifier(KtTokens.CROSSINLINE_KEYWORD));
                firValueParameterBuilder.setNoinline(ktParameter.hasModifier(KtTokens.NOINLINE_KEYWORD));
                firValueParameterBuilder.setValueParameterKind(valueParameterDeclaration == AbstractRawFirBuilder.ValueParameterDeclaration.CONTEXT_PARAMETER ? FirValueParameterKind.ContextParameter : FirValueParameterKind.Regular);
                firValueParameterBuilder.setContainingDeclarationSymbol(firBasedSymbol);
                CollectionsKt.addAll(firValueParameterBuilder.getAnnotations(), list);
                return firValueParameterBuilder.mo288build();
            } catch (Throwable th) {
                if (isAnnotationOwner) {
                    psiRawFirBuilder2.getContext().popContainerSymbol(symbol);
                }
                throw th;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ FirValueParameter toFirValueParameter$default(Visitor visitor, KtParameter ktParameter, FirTypeRef firTypeRef, FirBasedSymbol firBasedSymbol, AbstractRawFirBuilder.ValueParameterDeclaration valueParameterDeclaration, List list, int i, Object obj) {
            if (obj != null) {
                c41.a("Super calls with default arguments not supported in this target, function: toFirValueParameter");
                return null;
            }
            if ((i & 8) != 0) {
                list = CollectionsKt.emptyList();
            }
            return visitor.toFirValueParameter(ktParameter, firTypeRef, firBasedSymbol, valueParameterDeclaration, list);
        }

        public static FirExpression u(Visitor visitor, KtBinaryExpression ktBinaryExpression, PsiElement psiElement) {
            KtSourceElement firSourceElement$default;
            KtSourceElement source;
            KtSourceElement firSourceElement;
            psiElement.getClass();
            KtExpression ktExpression = (KtExpression) psiElement;
            FirElement firElementConvertElement = visitor.convertElement(ktExpression, null);
            if (!(firElementConvertElement instanceof FirExpression)) {
                PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
                FirErrorExpressionBuilder firErrorExpressionBuilder = new FirErrorExpressionBuilder();
                firErrorExpressionBuilder.setNonExpressionElement(firElementConvertElement);
                firErrorExpressionBuilder.setDiagnostic(firElementConvertElement == null ? new ConeSyntaxDiagnostic("Incorrect expression in assignment") : new ConeSimpleDiagnostic("Incorrect expression in assignment", DiagnosticKind.ExpressionExpected));
                if (firElementConvertElement == null || (source = firElementConvertElement.getSource()) == null || (firSourceElement$default = KtSourceElementKt.realElement(source)) == null) {
                    firSourceElement$default = AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, ktExpression, null, 1, null);
                }
                firErrorExpressionBuilder.setSource(firSourceElement$default);
                return firErrorExpressionBuilder.mo288build();
            }
            FirExpression firExpression = (FirExpression) firElementConvertElement;
            if (!UtilsKt.isStatementLikeExpression(firExpression) || UtilsKt.isArraySet(firExpression)) {
                return visitor.checkSelectorInvariant(ktExpression, firExpression);
            }
            PsiRawFirBuilder psiRawFirBuilder2 = PsiRawFirBuilder.this;
            FirErrorExpressionBuilder firErrorExpressionBuilder2 = new FirErrorExpressionBuilder();
            firErrorExpressionBuilder2.setNonExpressionElement(firElementConvertElement);
            firErrorExpressionBuilder2.setDiagnostic(new ConeSimpleDiagnostic("Incorrect expression in assignment", DiagnosticKind.ExpressionExpected));
            KtSourceElement source2 = firExpression.getSource();
            if (source2 == null || (firSourceElement = KtSourceElementKt.realElement(source2)) == null) {
                firSourceElement = psiRawFirBuilder2.toFirSourceElement((PsiElement) ktBinaryExpression, (KtFakeSourceElementKind) KtFakeSourceElementKind.ErrorExpression.INSTANCE);
            }
            firErrorExpressionBuilder2.setSource(firSourceElement);
            return firErrorExpressionBuilder2.mo288build();
        }

        private final FirElement visitBinaryExpressionFallback(final KtBinaryExpression expression) {
            IElementType operationToken = expression.getOperationToken();
            operationToken.getClass();
            KtToken ktToken = KtTokens.IDENTIFIER;
            boolean zAreEqual = Intrinsics.areEqual(operationToken, ktToken);
            PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
            if (zAreEqual) {
                psiRawFirBuilder.getContext().getCalleeNamesForLambda().add(expression.getOperationReference().getReferencedNameAsName());
            } else {
                psiRawFirBuilder.getContext().getCalleeNamesForLambda().add(null);
            }
            FirExpression firExpression = toFirExpression(expression.getLeft(), "No left operand", expression);
            FirExpression firExpression2 = toFirExpression(expression.getRight(), "No right operand", expression);
            PsiRawFirBuilder psiRawFirBuilder2 = PsiRawFirBuilder.this;
            psiRawFirBuilder2.removeLast(psiRawFirBuilder2.getContext().getCalleeNamesForLambda());
            KtSourceElement ktSourceElement = (KtPsiSourceElement) AbstractRawFirBuilder.toFirSourceElement$default(PsiRawFirBuilder.this, expression, null, 1, null);
            if (Intrinsics.areEqual(operationToken, KtTokens.ELVIS)) {
                return ConversionUtilsKt.generateNotNullOrOther(firExpression, firExpression2, ktSourceElement);
            }
            KtSingleValueToken ktSingleValueToken = KtTokens.ANDAND;
            if (Intrinsics.areEqual(operationToken, ktSingleValueToken) || Intrinsics.areEqual(operationToken, KtTokens.OROR)) {
                return ConversionUtilsKt.generateLazyLogicalOperation(firExpression, firExpression2, Intrinsics.areEqual(operationToken, ktSingleValueToken), ktSourceElement);
            }
            ImmutableSet immutableSet = OperatorConventions.IN_OPERATIONS;
            immutableSet.getClass();
            if (CollectionsKt.contains(immutableSet, operationToken)) {
                boolean zAreEqual2 = Intrinsics.areEqual(operationToken, KtTokens.NOT_IN);
                PsiRawFirBuilder psiRawFirBuilder3 = PsiRawFirBuilder.this;
                KtOperationReferenceExpression operationReference = expression.getOperationReference();
                operationReference.getClass();
                return ConversionUtilsKt.generateContainsOperation(firExpression2, firExpression, zAreEqual2, ktSourceElement, AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder3, operationReference, null, 1, null));
            }
            ImmutableSet immutableSet2 = OperatorConventions.COMPARISON_OPERATIONS;
            immutableSet2.getClass();
            if (CollectionsKt.contains(immutableSet2, operationToken)) {
                PsiRawFirBuilder psiRawFirBuilder4 = PsiRawFirBuilder.this;
                KtOperationReferenceExpression operationReference2 = expression.getOperationReference();
                operationReference2.getClass();
                return ConversionUtilsKt.generateComparisonExpression(firExpression, firExpression2, operationToken, ktSourceElement, AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder4, operationReference2, null, 1, null));
            }
            Name binaryName = ConversionUtilsKt.toBinaryName(operationToken);
            if (binaryName != null || Intrinsics.areEqual(operationToken, ktToken)) {
                PsiRawFirBuilder psiRawFirBuilder5 = PsiRawFirBuilder.this;
                FirFunctionCallBuilder firFunctionCallBuilder = new FirFunctionCallBuilder();
                firFunctionCallBuilder.setSource(ktSourceElement);
                FirSimpleNamedReferenceBuilder firSimpleNamedReferenceBuilder = new FirSimpleNamedReferenceBuilder();
                KtOperationReferenceExpression operationReference3 = expression.getOperationReference();
                operationReference3.getClass();
                firSimpleNamedReferenceBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder5, operationReference3, null, 1, null));
                firSimpleNamedReferenceBuilder.setName(binaryName == null ? expression.getOperationReference().getReferencedNameAsName() : binaryName);
                firFunctionCallBuilder.setCalleeReference(firSimpleNamedReferenceBuilder.build());
                firFunctionCallBuilder.setExplicitReceiver(firExpression);
                firFunctionCallBuilder.setArgumentList(FirArgumentUtilKt.buildUnaryArgumentList(firExpression2));
                firFunctionCallBuilder.setOrigin(binaryName != null ? FirFunctionCallOrigin.Operator : FirFunctionCallOrigin.Infix);
                return firFunctionCallBuilder.mo288build();
            }
            FirOperation firOperation = ConversionUtilsKt.toFirOperation(operationToken);
            if (!FirOperation.INSTANCE.getASSIGNMENTS().contains(firOperation)) {
                FirEqualityOperatorCallBuilder firEqualityOperatorCallBuilder = new FirEqualityOperatorCallBuilder();
                firEqualityOperatorCallBuilder.setSource(ktSourceElement);
                firEqualityOperatorCallBuilder.setOperation(firOperation);
                firEqualityOperatorCallBuilder.setArgumentList(FirArgumentUtilKt.buildBinaryArgumentList(firExpression, firExpression2));
                return firEqualityOperatorCallBuilder.mo288build();
            }
            PsiRawFirBuilder psiRawFirBuilder6 = PsiRawFirBuilder.this;
            KtExpression left = expression.getLeft();
            KtExpression left2 = expression.getLeft();
            KtSourceElement ktSourceElement2 = left2 != null ? (KtPsiSourceElement) AbstractRawFirBuilder.toFirSourceElement$default(PsiRawFirBuilder.this, left2, null, 1, null) : null;
            List<FirAnnotation> annotations = firExpression.getAnnotations();
            KtExpression right = expression.getRight();
            Set unwrappable_token_types = PsiUtilsKt.getUNWRAPPABLE_TOKEN_TYPES();
            PsiElement left3 = expression.getLeft();
            return psiRawFirBuilder6.generateAssignment(left, ktSourceElement, ktSourceElement2, firExpression2, firOperation, annotations, right, CollectionsKt.contains(unwrappable_token_types, left3 != null ? PsiRawFirBuilder.this.getElementType(left3) : null), new Function1() { // from class: xqb
                public final Object invoke(Object obj) {
                    return PsiRawFirBuilder.Visitor.u(this.b, expression, (PsiElement) obj);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
        /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
        public static final Unit visitClassOrObject$lambda$0$0$1$0$4(PsiRawFirBuilder psiRawFirBuilder, Visitor visitor, FirValueParameterBuilder firValueParameterBuilder, PsiElement psiElement) throws KotlinIllegalStateExceptionWithAttachments, UninitializedPropertyAccessException {
            firValueParameterBuilder.getClass();
            psiElement.getClass();
            FirValueParameterSymbol symbol = firValueParameterBuilder.getSymbol();
            psiRawFirBuilder.getContext().pushContainerSymbol(symbol);
            try {
                visitor.addAnnotationsFrom(firValueParameterBuilder, (KtParameter) psiElement, true);
                Unit unit = Unit.INSTANCE;
                return Unit.INSTANCE;
            } finally {
                psiRawFirBuilder.getContext().popContainerSymbol(symbol);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final FirBlock visitForExpression$lambda$0$2(KtParameter ktParameter, PsiRawFirBuilder psiRawFirBuilder, Visitor visitor, KtForExpression ktForExpression, KtPsiSourceElement ktPsiSourceElement, FirProperty firProperty) {
            Visitor visitor2;
            Name nameAsSafeName;
            ArrayList arrayList;
            List annotationEntries;
            FirBlockBuilder firBlockBuilder = new FirBlockBuilder();
            firBlockBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, ktForExpression, null, 1, null));
            if (ktParameter != null) {
                KtDestructuringDeclaration destructuringDeclaration = ktParameter.getDestructuringDeclaration();
                FirModuleData baseModuleData = psiRawFirBuilder.getBaseModuleData();
                KtSourceElement firSourceElement$default = AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, ktParameter, null, 1, null);
                if (destructuringDeclaration != null) {
                    nameAsSafeName = SpecialNames.DESTRUCT;
                } else {
                    PsiElement nameIdentifier = ktParameter.getNameIdentifier();
                    if (Intrinsics.areEqual(nameIdentifier != null ? psiRawFirBuilder.getAsText(nameIdentifier) : null, InlineClassManglingUtilsKt.NOT_INLINE_CLASS_PARAMETER_PLACEHOLDER)) {
                        nameAsSafeName = SpecialNames.UNDERSCORE_FOR_UNUSED_VAR;
                    } else {
                        nameAsSafeName = ktParameter.getNameAsSafeName();
                        nameAsSafeName.getClass();
                    }
                }
                Name name = nameAsSafeName;
                FirFunctionCallBuilder firFunctionCallBuilder = new FirFunctionCallBuilder();
                firFunctionCallBuilder.setSource(ktPsiSourceElement);
                FirSimpleNamedReferenceBuilder firSimpleNamedReferenceBuilder = new FirSimpleNamedReferenceBuilder();
                firSimpleNamedReferenceBuilder.setSource(ktPsiSourceElement);
                firSimpleNamedReferenceBuilder.setName(OperatorNameConventions.NEXT);
                firFunctionCallBuilder.setCalleeReference(firSimpleNamedReferenceBuilder.build());
                firFunctionCallBuilder.setExplicitReceiver(ConversionUtilsKt.generateResolvedAccessExpression(ktPsiSourceElement, firProperty));
                firFunctionCallBuilder.setOrigin(FirFunctionCallOrigin.Operator);
                Unit unit = Unit.INSTANCE;
                FirFunctionCall firFunctionCallMo288build = firFunctionCallBuilder.mo288build();
                FirTypeRef firOrImplicitType = visitor.toFirOrImplicitType(ktParameter.getTypeReference());
                KtModifierList modifierList = ktParameter.getModifierList();
                if (modifierList == null || (annotationEntries = modifierList.getAnnotationEntries()) == null) {
                    arrayList = null;
                } else {
                    List<KtAnnotationEntry> list = annotationEntries;
                    ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                    for (KtAnnotationEntry ktAnnotationEntry : list) {
                        ktAnnotationEntry.getClass();
                        FirElement firElementConvertElement = visitor.convertElement(ktAnnotationEntry, null);
                        if (firElementConvertElement == null) {
                            x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.expressions.FirAnnotation");
                            return null;
                        }
                        arrayList2.add((FirAnnotation) firElementConvertElement);
                    }
                    arrayList = arrayList2;
                }
                FirProperty firPropertyGenerateTemporaryVariable$default = FirGenerationKt.generateTemporaryVariable$default(baseModuleData, firSourceElement$default, name, firFunctionCallMo288build, firOrImplicitType, arrayList, null, 64, null);
                ClassMembersKt.setForLoopParameter(firPropertyGenerateTemporaryVariable$default, Boolean.TRUE);
                if (destructuringDeclaration != null) {
                    visitor2 = visitor;
                    PsiConversionUtilsKt.addDestructuringVariables$default(visitor2, psiRawFirBuilder, firBlockBuilder.getStatements(), psiRawFirBuilder.getBaseModuleData(), destructuringDeclaration, firPropertyGenerateTemporaryVariable$default, true, true, null, 128, null);
                } else {
                    visitor2 = visitor;
                    firBlockBuilder.getStatements().add(firPropertyGenerateTemporaryVariable$default);
                }
            } else {
                visitor2 = visitor;
            }
            firBlockBuilder.getStatements().add(visitor2.toFirBlock(ktForExpression.getBody()));
            return firBlockBuilder.mo288build();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String visitTryExpression$lambda$0$0$0(KtParameter ktParameter) {
            ASTNode node;
            PsiElement nameIdentifier = ktParameter.getNameIdentifier();
            if (nameIdentifier == null || (node = nameIdentifier.getNode()) == null) {
                return null;
            }
            return node.getText();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final FirExpression visitWhenExpression$lambda$2$1$0(Visitor visitor, KtExpression ktExpression, String str, KtElement ktElement) {
            str.getClass();
            ktElement.getClass();
            return visitor.toFirExpression(ktExpression, str, ktElement);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final FirExpression visitWhenExpression$lambda$2$2$0$0(Visitor visitor, KtExpression ktExpression, String str, KtElement ktElement) {
            str.getClass();
            ktElement.getClass();
            return visitor.toFirExpression(ktExpression, str, ktElement);
        }

        public static FirBlock w(Visitor visitor, KtWhileExpression ktWhileExpression) {
            return visitor.toFirBlock(ktWhileExpression.getBody());
        }

        public static Unit x(Visitor visitor, KtAnnotated ktAnnotated, FirAnnotationContainerBuilder firAnnotationContainerBuilder) {
            ktAnnotated.getClass();
            firAnnotationContainerBuilder.getClass();
            visitor.extractAnnotationsTo(ktAnnotated, firAnnotationContainerBuilder);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
        public final FirAnonymousInitializer buildAnonymousInitializer(KtAnonymousInitializer initializer, FirBasedSymbol<?> containingDeclarationSymbol, boolean allowLazyBody, boolean isLocal) throws KotlinIllegalStateExceptionWithAttachments {
            FirBlock firBlock;
            Object objInvoke;
            initializer.getClass();
            containingDeclarationSymbol.getClass();
            PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
            FirAnonymousInitializerBuilder firAnonymousInitializerBuilder = new FirAnonymousInitializerBuilder();
            FirAnonymousInitializerSymbol symbol = firAnonymousInitializerBuilder.getSymbol();
            if (!isLocal) {
                psiRawFirBuilder.getContext().pushContainerSymbol(symbol);
            }
            try {
                firAnonymousInitializerBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, initializer, null, 1, null));
                firAnonymousInitializerBuilder.setModuleData(psiRawFirBuilder.getBaseModuleData());
                firAnonymousInitializerBuilder.setOrigin(FirDeclarationOrigin.Source.INSTANCE);
                if (allowLazyBody) {
                    PsiRawFirBuilder$Visitor$buildOrLazyBlock$1 psiRawFirBuilder$Visitor$buildOrLazyBlock$1 = PsiRawFirBuilder$Visitor$buildOrLazyBlock$1.INSTANCE;
                    int i = WhenMappings.$EnumSwitchMapping$0[PsiRawFirBuilder.this.getMode().ordinal()];
                    if (i == 1) {
                        boolean forceKeepingTheBodyInHeaderMode = psiRawFirBuilder.getContext().getForceKeepingTheBodyInHeaderMode();
                        psiRawFirBuilder.getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
                        boolean inLocalContext = psiRawFirBuilder.getContext().getInLocalContext();
                        psiRawFirBuilder.getContext().setInLocalContext(true);
                        FqName classNameBeforeLocalContext = psiRawFirBuilder.getContext().getClassNameBeforeLocalContext();
                        if (!inLocalContext) {
                            psiRawFirBuilder.getContext().setClassNameBeforeLocalContext(psiRawFirBuilder.getContext().getClassName());
                        }
                        FqName className = psiRawFirBuilder.getContext().getClassName();
                        psiRawFirBuilder.getContext().setClassName(FqName.ROOT);
                        try {
                            FirBlock firBlock2 = toFirBlock(initializer.getBody());
                            psiRawFirBuilder.getContext().setClassNameBeforeLocalContext(classNameBeforeLocalContext);
                            psiRawFirBuilder.getContext().setInLocalContext(inLocalContext);
                            psiRawFirBuilder.getContext().setClassName(className);
                            psiRawFirBuilder.getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
                            objInvoke = firBlock2;
                        } catch (Throwable th) {
                            psiRawFirBuilder.getContext().setClassNameBeforeLocalContext(classNameBeforeLocalContext);
                            psiRawFirBuilder.getContext().setInLocalContext(inLocalContext);
                            psiRawFirBuilder.getContext().setClassName(className);
                            psiRawFirBuilder.getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
                            throw th;
                        }
                    } else {
                        if (i != 2) {
                            throw new NoWhenBranchMatchedException();
                        }
                        int i2 = WhenMappings.$EnumSwitchMapping$0[PsiRawFirBuilder.this.getMode().ordinal()];
                        if (i2 == 1) {
                            objInvoke = psiRawFirBuilder$Visitor$buildOrLazyBlock$1.invoke();
                        } else {
                            if (i2 != 2) {
                                throw new NoWhenBranchMatchedException();
                            }
                            objInvoke = AstLoadingFilter.disallowTreeLoading(new PsiRawFirBuilder$runOnStubs$1(psiRawFirBuilder$Visitor$buildOrLazyBlock$1));
                        }
                    }
                    firBlock = (FirBlock) objInvoke;
                } else {
                    boolean forceKeepingTheBodyInHeaderMode2 = psiRawFirBuilder.getContext().getForceKeepingTheBodyInHeaderMode();
                    psiRawFirBuilder.getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode2);
                    boolean inLocalContext2 = psiRawFirBuilder.getContext().getInLocalContext();
                    psiRawFirBuilder.getContext().setInLocalContext(true);
                    FqName classNameBeforeLocalContext2 = psiRawFirBuilder.getContext().getClassNameBeforeLocalContext();
                    if (!inLocalContext2) {
                        psiRawFirBuilder.getContext().setClassNameBeforeLocalContext(psiRawFirBuilder.getContext().getClassName());
                    }
                    FqName className2 = psiRawFirBuilder.getContext().getClassName();
                    psiRawFirBuilder.getContext().setClassName(FqName.ROOT);
                    try {
                        FirBlock firBlock3 = toFirBlock(initializer.getBody());
                        psiRawFirBuilder.getContext().setClassNameBeforeLocalContext(classNameBeforeLocalContext2);
                        psiRawFirBuilder.getContext().setInLocalContext(inLocalContext2);
                        psiRawFirBuilder.getContext().setClassName(className2);
                        psiRawFirBuilder.getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode2);
                        firBlock = firBlock3;
                    } catch (Throwable th2) {
                        psiRawFirBuilder.getContext().setClassNameBeforeLocalContext(classNameBeforeLocalContext2);
                        psiRawFirBuilder.getContext().setInLocalContext(inLocalContext2);
                        psiRawFirBuilder.getContext().setClassName(className2);
                        psiRawFirBuilder.getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode2);
                        throw th2;
                    }
                }
                firAnonymousInitializerBuilder.setBody(firBlock);
                firAnonymousInitializerBuilder.setContainingDeclarationSymbol(containingDeclarationSymbol);
                extractAnnotationsTo((KtAnnotated) initializer, (FirAnnotationContainerBuilder) firAnonymousInitializerBuilder);
                Unit unit = Unit.INSTANCE;
                if (!isLocal) {
                    psiRawFirBuilder.getContext().popContainerSymbol(symbol);
                }
                return firAnonymousInitializerBuilder.mo288build();
            } catch (Throwable th3) {
                if (!isLocal) {
                    psiRawFirBuilder.getContext().popContainerSymbol(symbol);
                }
                throw th3;
            }
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
        /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
        public final FirField buildFieldForSupertypeDelegate(KtDelegatedSuperTypeEntry entry, FirTypeRef type, int fieldOrd) throws KotlinIllegalStateExceptionWithAttachments, UninitializedPropertyAccessException {
            Object firExpression;
            entry.getClass();
            type.getClass();
            KtSourceElement firSourceElement = PsiRawFirBuilder.this.toFirSourceElement((PsiElement) entry, (KtFakeSourceElementKind) KtFakeSourceElementKind.ClassDelegationField.INSTANCE);
            PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
            FirFieldBuilder firFieldBuilder = new FirFieldBuilder();
            firFieldBuilder.setSource(firSourceElement);
            firFieldBuilder.setModuleData(psiRawFirBuilder.getBaseModuleData());
            firFieldBuilder.setOrigin(FirDeclarationOrigin.Synthetic.DelegateField.INSTANCE);
            firFieldBuilder.setName(NameUtils.delegateFieldName(fieldOrd));
            firFieldBuilder.setSymbol(new FirFieldSymbol(new CallableId(psiRawFirBuilder.getContext().getCurrentClassId(), firFieldBuilder.getName())));
            firFieldBuilder.setReturnTypeRef(type);
            FirFieldSymbol symbol = firFieldBuilder.getSymbol();
            psiRawFirBuilder.getContext().pushContainerSymbol(symbol);
            try {
                PsiRawFirBuilder$Visitor$buildOrLazyExpression$1 psiRawFirBuilder$Visitor$buildOrLazyExpression$1 = new PsiRawFirBuilder$Visitor$buildOrLazyExpression$1(firSourceElement);
                int i = WhenMappings.$EnumSwitchMapping$0[PsiRawFirBuilder.this.getMode().ordinal()];
                if (i == 1) {
                    firExpression = toFirExpression(entry.getDelegateExpression(), "Should have delegate", entry);
                } else {
                    if (i != 2) {
                        throw new NoWhenBranchMatchedException();
                    }
                    int i2 = WhenMappings.$EnumSwitchMapping$0[PsiRawFirBuilder.this.getMode().ordinal()];
                    if (i2 == 1) {
                        firExpression = psiRawFirBuilder$Visitor$buildOrLazyExpression$1.invoke();
                    } else {
                        if (i2 != 2) {
                            throw new NoWhenBranchMatchedException();
                        }
                        firExpression = AstLoadingFilter.disallowTreeLoading(new PsiRawFirBuilder$runOnStubs$1(psiRawFirBuilder$Visitor$buildOrLazyExpression$1));
                    }
                }
                firFieldBuilder.setInitializer((FirExpression) firExpression);
                Unit unit = Unit.INSTANCE;
                psiRawFirBuilder.getContext().popContainerSymbol(symbol);
                firFieldBuilder.setVar(false);
                firFieldBuilder.setStatus(new FirDeclarationStatusImpl(Visibilities.Private.INSTANCE, Modality.FINAL));
                firFieldBuilder.setLocal(psiRawFirBuilder.getContext().getInLocalContext());
                firFieldBuilder.setDispatchReceiverType(psiRawFirBuilder.currentDispatchReceiverType());
                return firFieldBuilder.mo288build();
            } catch (Throwable th) {
                psiRawFirBuilder.getContext().popContainerSymbol(symbol);
                throw th;
            }
        }

        public final FirVariable buildScriptDestructuringDeclaration(KtDestructuringDeclaration destructuringDeclaration) {
            Object firExpression;
            destructuringDeclaration.getClass();
            KtExpression initializer = destructuringDeclaration.getInitializer();
            PsiRawFirBuilder$Visitor$buildOrLazyExpression$1 psiRawFirBuilder$Visitor$buildOrLazyExpression$1 = new PsiRawFirBuilder$Visitor$buildOrLazyExpression$1(initializer != null ? AbstractRawFirBuilder.toFirSourceElement$default(PsiRawFirBuilder.this, initializer, null, 1, null) : null);
            int i = WhenMappings.$EnumSwitchMapping$0[PsiRawFirBuilder.this.getMode().ordinal()];
            if (i == 1) {
                firExpression = toFirExpression(initializer, "Initializer required for destructuring declaration", destructuringDeclaration);
            } else {
                if (i != 2) {
                    bu8.a();
                    return null;
                }
                int i2 = WhenMappings.$EnumSwitchMapping$0[PsiRawFirBuilder.this.getMode().ordinal()];
                if (i2 == 1) {
                    firExpression = psiRawFirBuilder$Visitor$buildOrLazyExpression$1.invoke();
                } else {
                    if (i2 != 2) {
                        bu8.a();
                        return null;
                    }
                    firExpression = AstLoadingFilter.disallowTreeLoading(new PsiRawFirBuilder$runOnStubs$1(psiRawFirBuilder$Visitor$buildOrLazyExpression$1));
                }
            }
            FirVariable firVariableGenerateTemporaryVariable = PsiConversionUtilsKt.generateTemporaryVariable(PsiRawFirBuilder.this.getBaseModuleData(), AbstractRawFirBuilder.toFirSourceElement$default(PsiRawFirBuilder.this, destructuringDeclaration, null, 1, null), "destruct", (FirExpression) firExpression, FirDeclarationOrigin.Synthetic.ScriptTopLevelDestructuringDeclarationContainer.INSTANCE, new Function2() { // from class: sqb
                public final Object invoke(Object obj, Object obj2) {
                    return PsiRawFirBuilder.Visitor.r(this.b, (KtAnnotated) obj, (FirAnnotationContainerBuilder) obj2);
                }
            });
            DestructuringDeclarationAttributesKt.setDestructuringDeclarationContainerVariable(firVariableGenerateTemporaryVariable, Boolean.TRUE);
            return firVariableGenerateTemporaryVariable;
        }

        public final FirElement convertElement(KtElement element, FirElement original) {
            element.getClass();
            return (FirElement) element.accept(this, original);
        }

        public final FirProperty convertProperty(KtProperty property, FirClassSymbol<?> ownerRegularOrAnonymousObjectSymbol) {
            property.getClass();
            return toFirProperty(property, ownerRegularOrAnonymousObjectSymbol, PsiRawFirBuilder.this.getContext());
        }

        /* JADX WARN: Code duplicated, block: B:78:0x0359 A[Catch: all -> 0x028b, TryCatch #6 {all -> 0x028b, blocks: (B:41:0x0255, B:43:0x0265, B:45:0x0273, B:48:0x028e, B:51:0x02e8, B:52:0x030e, B:76:0x0349, B:78:0x0359, B:80:0x0367, B:81:0x037e, B:82:0x0398, B:83:0x0399, B:84:0x03bd), top: B:102:0x0059 }] */
        /* JADX WARN: Code duplicated, block: B:80:0x0367 A[Catch: all -> 0x028b, TryCatch #6 {all -> 0x028b, blocks: (B:41:0x0255, B:43:0x0265, B:45:0x0273, B:48:0x028e, B:51:0x02e8, B:52:0x030e, B:76:0x0349, B:78:0x0359, B:80:0x0367, B:81:0x037e, B:82:0x0398, B:83:0x0399, B:84:0x03bd), top: B:102:0x0059 }] */
        /* JADX WARN: Code duplicated, block: B:83:0x0399 A[Catch: all -> 0x028b, TryCatch #6 {all -> 0x028b, blocks: (B:41:0x0255, B:43:0x0265, B:45:0x0273, B:48:0x028e, B:51:0x02e8, B:52:0x030e, B:76:0x0349, B:78:0x0359, B:80:0x0367, B:81:0x037e, B:82:0x0398, B:83:0x0399, B:84:0x03bd), top: B:102:0x0059 }] */
        /* JADX WARN: Instruction removed from duplicated block: B:83:0x0399, please report this as an issue */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r7v25, types: [java.lang.Object, org.jetbrains.kotlin.fir.declarations.FirDeclaration] */
        /* JADX WARN: Type inference failed for: r7v28 */
        /* JADX WARN: Type inference failed for: r7v29 */
        /* JADX WARN: Type inference failed for: r7v30 */
        public final FirReplSnippet convertReplSnippet(KtScript script, KtPsiSourceElement scriptSource, String fileName, Function1<? super FirReplSnippetBuilder, Unit> snippetSetup, Function1<? super FirBlockBuilder, Unit> functionBodySetup, Function1<? super List<FirElement>, Unit> statementsSetup) throws Throwable {
            PsiRawFirBuilder psiRawFirBuilder;
            ?? fir;
            KtScript ktScript = script;
            ktScript.getClass();
            scriptSource.getClass();
            fileName.getClass();
            snippetSetup.getClass();
            functionBodySetup.getClass();
            statementsSetup.getClass();
            Name nameFirSnippetName = AbstractRawFirBuilder.INSTANCE.firSnippetName(fileName);
            Name snippetTargetClassName = NameUtils.getSnippetTargetClassName(nameFirSnippetName);
            FirRegularClassSymbol firRegularClassSymbol = new FirRegularClassSymbol(new ClassId(PsiRawFirBuilder.this.getContext().getPackageFqName(), snippetTargetClassName));
            FirReplSnippetSymbol firReplSnippetSymbol = new FirReplSnippetSymbol(firRegularClassSymbol);
            Name nameIdentifier = Name.identifier("$$eval");
            nameIdentifier.getClass();
            PsiRawFirBuilder psiRawFirBuilder2 = PsiRawFirBuilder.this;
            if (psiRawFirBuilder2.getContext().getContainingReplSymbol() != null) {
                w01.a("Nested snippets are not supported");
                return null;
            }
            psiRawFirBuilder2.getContext().setContainingReplSymbol(firReplSnippetSymbol);
            try {
                try {
                    psiRawFirBuilder2.getContext().setClassName(psiRawFirBuilder2.getContext().getClassName().child(snippetTargetClassName));
                    boolean containerIsExpect = psiRawFirBuilder2.getContext().getContainerIsExpect();
                    psiRawFirBuilder2.getContext().setContainerIsExpect(containerIsExpect);
                    int size = psiRawFirBuilder2.getContext().getDispatchReceiverTypesStack().size();
                    try {
                        psiRawFirBuilder2.getContext().pushContainerSymbol(firRegularClassSymbol);
                        try {
                            FirNamedFunctionSymbol firNamedFunctionSymbol = new FirNamedFunctionSymbol(psiRawFirBuilder2.callableIdForName(nameIdentifier));
                            FirRegularClassBuilder firRegularClassBuilder = new FirRegularClassBuilder();
                            KtFakeSourceElementKind.ReplBaseClass replBaseClass = KtFakeSourceElementKind.ReplBaseClass.INSTANCE;
                            try {
                                if (replBaseClass == null) {
                                    throw new NoWhenBranchMatchedException();
                                }
                                firRegularClassBuilder.setSource(new KtFakePsiSourceElement(ktScript, replBaseClass));
                                firRegularClassBuilder.setModuleData(psiRawFirBuilder2.getBaseModuleData());
                                firRegularClassBuilder.setOrigin(FirDeclarationOrigin.Synthetic.ReplContainerClass.INSTANCE);
                                firRegularClassBuilder.setName(snippetTargetClassName);
                                firRegularClassBuilder.setStatus(new FirDeclarationStatusImpl(Visibilities.Public.INSTANCE, Modality.FINAL));
                                firRegularClassBuilder.setClassKind(ClassKind.OBJECT);
                                firRegularClassBuilder.setScopeProvider(psiRawFirBuilder2.getBaseScopeProvider());
                                firRegularClassBuilder.setSymbol(firRegularClassSymbol);
                                firRegularClassBuilder.getSuperTypeRefs().add(psiRawFirBuilder2.getImplicitAnyType());
                                psiRawFirBuilder2.getContext().appendOuterTypeParameters(true, firRegularClassBuilder.getTypeParameters());
                                FirTypeRef delegatedSelfType = psiRawFirBuilder2.toDelegatedSelfType(ktScript, firRegularClassBuilder);
                                psiRawFirBuilder2.registerSelfType(delegatedSelfType);
                                ArrayList arrayList = new ArrayList();
                                psiRawFirBuilder2.getContext().pushContainerSymbol(firNamedFunctionSymbol);
                                try {
                                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                                    List mutableList = CollectionsKt.toMutableList(extractReplElements(ktScript, firRegularClassSymbol, linkedHashMap));
                                    statementsSetup.invoke(mutableList);
                                    List list = mutableList;
                                    ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                                    Iterator it = list.iterator();
                                    while (it.hasNext()) {
                                        arrayList2.add(convertReplElement((FirElement) it.next()));
                                    }
                                    Iterator<? extends FirElement> it2 = arrayList2.iterator();
                                    while (it2.hasNext()) {
                                        FirElement next = it2.next();
                                        Iterator<? extends FirElement> it3 = it2;
                                        if (next instanceof FirReplDeclarationReference) {
                                            fir = ((FirReplDeclarationReference) next).getSymbol().getFir();
                                        } else if (next instanceof FirReplPropertyInitializer) {
                                            fir = ((FirReplPropertyInitializer) next).getPropertySymbol().getFir();
                                        } else {
                                            if (next instanceof FirReplPropertyDelegate) {
                                                fir = ((FirReplPropertyDelegate) next).getPropertySymbol().getFir();
                                            }
                                            it2 = it3;
                                        }
                                        DeclarationAttributesKt.setReplSnippetDeclaration(fir, Boolean.TRUE);
                                        arrayList.add(fir);
                                        it2 = it3;
                                    }
                                    FirNamedFunction firNamedFunctionCreateEvalFunction = createEvalFunction(ktScript, firNamedFunctionSymbol, arrayList2, functionBodySetup);
                                    if (!linkedHashMap.isEmpty()) {
                                        DeclarationAttributesKt.setReplSnippetDelegatedPropertyCopies(firNamedFunctionCreateEvalFunction, linkedHashMap);
                                    }
                                    psiRawFirBuilder2.getContext().popContainerSymbol(firNamedFunctionSymbol);
                                    FirConstructorSymbol firConstructorSymbol = new FirConstructorSymbol(psiRawFirBuilder2.callableIdForClassConstructor());
                                    KtFakeSourceElementKind.ImplicitConstructor implicitConstructor = KtFakeSourceElementKind.ImplicitConstructor.INSTANCE;
                                    if (implicitConstructor == null) {
                                        throw new NoWhenBranchMatchedException();
                                    }
                                    KtSourceElement ktFakePsiSourceElement = new KtFakePsiSourceElement(ktScript, implicitConstructor);
                                    FirPrimaryConstructorBuilder firPrimaryConstructorBuilder = new FirPrimaryConstructorBuilder();
                                    firPrimaryConstructorBuilder.setSource(ktFakePsiSourceElement);
                                    firPrimaryConstructorBuilder.setModuleData(psiRawFirBuilder2.getBaseModuleData());
                                    FirDeclarationOrigin.Source source = FirDeclarationOrigin.Source.INSTANCE;
                                    firPrimaryConstructorBuilder.setOrigin(source);
                                    firPrimaryConstructorBuilder.setReturnTypeRef(delegatedSelfType);
                                    psiRawFirBuilder = psiRawFirBuilder2;
                                    try {
                                        firPrimaryConstructorBuilder.setStatus(new FirDeclarationStatusImpl(Visibilities.Public.INSTANCE, Modality.FINAL));
                                        firPrimaryConstructorBuilder.setDispatchReceiverType(psiRawFirBuilder.currentDispatchReceiverType());
                                        firPrimaryConstructorBuilder.setLocal(false);
                                        firPrimaryConstructorBuilder.setSymbol(firConstructorSymbol);
                                        FirDelegatedConstructorCallBuilder firDelegatedConstructorCallBuilder = new FirDelegatedConstructorCallBuilder();
                                        KtFakeSourceElementKind.DelegatingConstructorCall delegatingConstructorCall = KtFakeSourceElementKind.DelegatingConstructorCall.INSTANCE;
                                        try {
                                            firDelegatedConstructorCallBuilder.setSource(KtSourceElementKt.fakeElement$default(ktFakePsiSourceElement, delegatingConstructorCall, null, 2, null));
                                            firDelegatedConstructorCallBuilder.setConstructedTypeRef(psiRawFirBuilder.getImplicitAnyType());
                                            firDelegatedConstructorCallBuilder.setThis(false);
                                            FirExplicitSuperReferenceBuilder firExplicitSuperReferenceBuilder = new FirExplicitSuperReferenceBuilder();
                                            firExplicitSuperReferenceBuilder.setSource(KtSourceElementKt.fakeElement$default(ktFakePsiSourceElement, delegatingConstructorCall, null, 2, null));
                                            firExplicitSuperReferenceBuilder.setSuperTypeRef(psiRawFirBuilder.getImplicitAnyType());
                                            firDelegatedConstructorCallBuilder.setCalleeReference(firExplicitSuperReferenceBuilder.build());
                                            firPrimaryConstructorBuilder.setDelegatedConstructor(firDelegatedConstructorCallBuilder.mo288build());
                                            CollectionsKt.addAll(firRegularClassBuilder.getDeclarations(), CollectionsKt.plus(CollectionsKt.listOf(new FirElement[]{firPrimaryConstructorBuilder.mo288build(), firNamedFunctionCreateEvalFunction}), arrayList));
                                            Pair pair = TuplesKt.to(firRegularClassBuilder.mo288build(), firNamedFunctionSymbol);
                                            try {
                                                psiRawFirBuilder.getContext().popContainerSymbol(firRegularClassSymbol);
                                                if (psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().size() > size + 1) {
                                                    throw new IllegalArgumentException(("Wrong number of " + psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().size()).toString());
                                                }
                                                if (psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().size() > size) {
                                                    psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().remove(CollectionsKt.getLastIndex(psiRawFirBuilder.getContext().getDispatchReceiverTypesStack()));
                                                }
                                                psiRawFirBuilder.getContext().setClassName(psiRawFirBuilder.getContext().getClassName().parent());
                                                psiRawFirBuilder.getContext().setContainerIsExpect(containerIsExpect);
                                                psiRawFirBuilder.getContext().setContainingReplSymbol(null);
                                                FirRegularClass firRegularClass = (FirRegularClass) pair.component1();
                                                FirNamedFunctionSymbol firNamedFunctionSymbol2 = (FirNamedFunctionSymbol) pair.component2();
                                                PsiRawFirBuilder psiRawFirBuilder3 = PsiRawFirBuilder.this;
                                                FirReplSnippetBuilder firReplSnippetBuilder = new FirReplSnippetBuilder();
                                                firReplSnippetBuilder.setSource(scriptSource);
                                                firReplSnippetBuilder.setModuleData(psiRawFirBuilder3.getBaseModuleData());
                                                firReplSnippetBuilder.setOrigin(source);
                                                firReplSnippetBuilder.setName(nameFirSnippetName);
                                                firReplSnippetBuilder.setSymbol(firReplSnippetSymbol);
                                                firReplSnippetBuilder.setSnippetClass(firRegularClass);
                                                firReplSnippetBuilder.setEvalFunctionSymbol(firNamedFunctionSymbol2);
                                                snippetSetup.invoke(firReplSnippetBuilder);
                                                return firReplSnippetBuilder.mo288build();
                                            } catch (Throwable th) {
                                                th = th;
                                                ktScript = "Wrong number of ";
                                                if (psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().size() <= size + 1) {
                                                    throw new IllegalArgumentException((((String) ktScript) + psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().size()).toString());
                                                }
                                                if (psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().size() > size) {
                                                    psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().remove(CollectionsKt.getLastIndex(psiRawFirBuilder.getContext().getDispatchReceiverTypesStack()));
                                                }
                                                psiRawFirBuilder.getContext().setClassName(psiRawFirBuilder.getContext().getClassName().parent());
                                                psiRawFirBuilder.getContext().setContainerIsExpect(containerIsExpect);
                                                throw th;
                                            }
                                        } catch (Throwable th2) {
                                            th = th2;
                                            ktScript = "Wrong number of ";
                                            psiRawFirBuilder.getContext().popContainerSymbol(firRegularClassSymbol);
                                            throw th;
                                        }
                                    } catch (Throwable th3) {
                                        th = th3;
                                        ktScript = "Wrong number of ";
                                    }
                                } catch (Throwable th4) {
                                    psiRawFirBuilder2.getContext().popContainerSymbol(firNamedFunctionSymbol);
                                    throw th4;
                                }
                            } catch (Throwable th5) {
                                th = th5;
                            }
                        } catch (Throwable th6) {
                            th = th6;
                            ktScript = "Wrong number of ";
                            psiRawFirBuilder = psiRawFirBuilder2;
                        }
                        try {
                            psiRawFirBuilder.getContext().popContainerSymbol(firRegularClassSymbol);
                            throw th;
                        } catch (Throwable th7) {
                            th = th7;
                            if (psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().size() <= size + 1) {
                                throw new IllegalArgumentException((((String) ktScript) + psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().size()).toString());
                            }
                            if (psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().size() > size) {
                                psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().remove(CollectionsKt.getLastIndex(psiRawFirBuilder.getContext().getDispatchReceiverTypesStack()));
                            }
                            psiRawFirBuilder.getContext().setClassName(psiRawFirBuilder.getContext().getClassName().parent());
                            psiRawFirBuilder.getContext().setContainerIsExpect(containerIsExpect);
                            throw th;
                        }
                    } catch (Throwable th8) {
                        th = th8;
                        ktScript = "Wrong number of ";
                        psiRawFirBuilder = psiRawFirBuilder2;
                    }
                } catch (Throwable th9) {
                    th = th9;
                    psiRawFirBuilder2.getContext().setContainingReplSymbol(null);
                    throw th;
                }
            } catch (Throwable th10) {
                th = th10;
                psiRawFirBuilder2.getContext().setContainingReplSymbol(null);
                throw th;
            }
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
        /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
        /* JADX WARN: Multi-variable type inference failed */
        public final FirScript convertScript(KtScript script, KtPsiSourceElement scriptSource, String fileName, Function1<? super FirScriptBuilder, Unit> setup) throws KotlinIllegalStateExceptionWithAttachments, UninitializedPropertyAccessException {
            script.getClass();
            scriptSource.getClass();
            fileName.getClass();
            setup.getClass();
            Name nameFirScriptName = AbstractRawFirBuilder.INSTANCE.firScriptName(fileName);
            FirScriptSymbol firScriptSymbol = new FirScriptSymbol(PsiRawFirBuilder.this.getContext().getPackageFqName().child(nameFirScriptName));
            final PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
            FirScriptBuilder firScriptBuilder = new FirScriptBuilder();
            firScriptBuilder.setSource(scriptSource);
            firScriptBuilder.setModuleData(psiRawFirBuilder.getBaseModuleData());
            firScriptBuilder.setOrigin(FirDeclarationOrigin.Source.INSTANCE);
            firScriptBuilder.setName(nameFirScriptName);
            firScriptBuilder.setSymbol(firScriptSymbol);
            ListIterator listIterator = script.getDeclarations().listIterator();
            FirScriptSymbol symbol = firScriptBuilder.getSymbol();
            if (psiRawFirBuilder.getContext().getContainingScriptSymbol() != null) {
                w01.a("Nested scripts are not supported");
                return null;
            }
            psiRawFirBuilder.getContext().setContainingScriptSymbol(symbol);
            psiRawFirBuilder.getContext().pushContainerSymbol(symbol);
            while (listIterator.hasNext()) {
                try {
                    KtAnonymousInitializer ktAnonymousInitializer = (KtDeclaration) listIterator.next();
                    boolean zHasNext = listIterator.hasNext();
                    boolean z = !zHasNext;
                    if (ktAnonymousInitializer instanceof KtScriptInitializer) {
                        FirAnonymousInitializer firAnonymousInitializerBuildAnonymousInitializer = buildAnonymousInitializer(ktAnonymousInitializer, firScriptSymbol, zHasNext, z);
                        DeclarationAttributesKt.setScriptTopLevelDeclaration(firAnonymousInitializerBuildAnonymousInitializer, Boolean.TRUE);
                        firScriptBuilder.getDeclarations().add(firAnonymousInitializerBuildAnonymousInitializer);
                    } else if (ktAnonymousInitializer instanceof KtDestructuringDeclaration) {
                        final FirVariable firVariableBuildScriptDestructuringDeclaration = buildScriptDestructuringDeclaration((KtDestructuringDeclaration) ktAnonymousInitializer);
                        DeclarationAttributesKt.setScriptTopLevelDeclaration(firVariableBuildScriptDestructuringDeclaration, Boolean.TRUE);
                        firScriptBuilder.getDeclarations().add(firVariableBuildScriptDestructuringDeclaration);
                        PsiConversionUtilsKt.addDestructuringVariables(this, psiRawFirBuilder, firScriptBuilder.getDeclarations(), firScriptBuilder.getModuleData(), (KtDestructuringDeclaration) ktAnonymousInitializer, firVariableBuildScriptDestructuringDeclaration, false, false, new Function1() { // from class: drb
                            public final Object invoke(Object obj) {
                                return PsiRawFirBuilder.Visitor.convertScript$lambda$0$0$0(psiRawFirBuilder, firVariableBuildScriptDestructuringDeclaration, (FirVariable) obj);
                            }
                        });
                    } else {
                        FirAnnotationContainer firStatement = toFirStatement(ktAnonymousInitializer);
                        if (!(firStatement instanceof FirDeclaration)) {
                            throw new IllegalStateException("unexpected declaration type in script");
                        }
                        DeclarationAttributesKt.setScriptTopLevelDeclaration((FirDeclaration) firStatement, Boolean.TRUE);
                        firScriptBuilder.getDeclarations().add(firStatement);
                    }
                } catch (Throwable th) {
                    psiRawFirBuilder.getContext().popContainerSymbol(symbol);
                    psiRawFirBuilder.getContext().setContainingScriptSymbol(null);
                    throw th;
                }
            }
            setup.invoke(firScriptBuilder);
            Unit unit = Unit.INSTANCE;
            psiRawFirBuilder.getContext().popContainerSymbol(symbol);
            psiRawFirBuilder.getContext().setContainingScriptSymbol(null);
            return firScriptBuilder.mo288build();
        }

        @Override // org.jetbrains.kotlin.fir.builder.DestructuringContext
        public Name getInitializerName(KtDestructuringDeclarationEntry ktDestructuringDeclarationEntry) {
            ktDestructuringDeclarationEntry.getClass();
            KtNameReferenceExpression initializer = ktDestructuringDeclarationEntry.getInitializer();
            if (initializer != null) {
                return initializer.getReferencedNameAsName();
            }
            return null;
        }

        @Override // org.jetbrains.kotlin.fir.builder.DestructuringContext
        public KtSourceElement getInitializerSource(KtDestructuringDeclarationEntry ktDestructuringDeclarationEntry) {
            ktDestructuringDeclarationEntry.getClass();
            KtNameReferenceExpression initializer = ktDestructuringDeclarationEntry.getInitializer();
            if (initializer != null) {
                if (KtRealSourceElementKind.INSTANCE != null) {
                    return new KtRealPsiSourceElement(initializer);
                }
                bu8.a();
            }
            return null;
        }

        @Override // org.jetbrains.kotlin.fir.builder.DestructuringContext
        public Name getName(KtDestructuringDeclarationEntry ktDestructuringDeclarationEntry) {
            ktDestructuringDeclarationEntry.getClass();
            PsiElement nameIdentifier = ktDestructuringDeclarationEntry.getNameIdentifier();
            if (Intrinsics.areEqual(nameIdentifier != null ? nameIdentifier.getText() : null, InlineClassManglingUtilsKt.NOT_INLINE_CLASS_PARAMETER_PLACEHOLDER)) {
                return SpecialNames.UNDERSCORE_FOR_UNUSED_VAR;
            }
            Name nameAsSafeName = ktDestructuringDeclarationEntry.getNameAsSafeName();
            nameAsSafeName.getClass();
            return nameAsSafeName;
        }

        @Override // org.jetbrains.kotlin.fir.builder.DestructuringContext
        public FirTypeRef getReturnTypeRef(KtDestructuringDeclarationEntry ktDestructuringDeclarationEntry) {
            ktDestructuringDeclarationEntry.getClass();
            return toFirOrImplicitType(ktDestructuringDeclarationEntry.getTypeReference());
        }

        @Override // org.jetbrains.kotlin.fir.builder.DestructuringContext
        public KtSourceElement getSource(KtDestructuringDeclarationEntry ktDestructuringDeclarationEntry) {
            ktDestructuringDeclarationEntry.getClass();
            if (KtRealSourceElementKind.INSTANCE != null) {
                return new KtRealPsiSourceElement(ktDestructuringDeclarationEntry);
            }
            bu8.a();
            return null;
        }

        @Override // org.jetbrains.kotlin.fir.builder.DestructuringContext
        public FirExpression interceptExpressionBuilding(KtSourceElement sourceElement, Function0<? extends FirExpression> buildExpression) {
            Object objInvoke;
            buildExpression.getClass();
            PsiRawFirBuilder$Visitor$buildOrLazyExpression$1 psiRawFirBuilder$Visitor$buildOrLazyExpression$1 = new PsiRawFirBuilder$Visitor$buildOrLazyExpression$1(sourceElement);
            int i = WhenMappings.$EnumSwitchMapping$0[PsiRawFirBuilder.this.getMode().ordinal()];
            if (i == 1) {
                objInvoke = buildExpression.invoke();
            } else {
                if (i != 2) {
                    bu8.a();
                    return null;
                }
                int i2 = WhenMappings.$EnumSwitchMapping$0[PsiRawFirBuilder.this.getMode().ordinal()];
                if (i2 == 1) {
                    objInvoke = psiRawFirBuilder$Visitor$buildOrLazyExpression$1.invoke();
                } else {
                    if (i2 != 2) {
                        bu8.a();
                        return null;
                    }
                    objInvoke = AstLoadingFilter.disallowTreeLoading(new PsiRawFirBuilder$runOnStubs$1(psiRawFirBuilder$Visitor$buildOrLazyExpression$1));
                }
            }
            return (FirExpression) objInvoke;
        }

        @Override // org.jetbrains.kotlin.fir.builder.DestructuringContext
        public boolean isVar(KtDestructuringDeclarationEntry ktDestructuringDeclarationEntry) {
            ktDestructuringDeclarationEntry.getClass();
            return ktDestructuringDeclarationEntry.isVar();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
        /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
        public final FirConstructor toFirConstructor(KtSecondaryConstructor ktSecondaryConstructor, FirTypeRef firTypeRef, FirTypeRef firTypeRef2, KtClassOrObject ktClassOrObject, List<? extends FirTypeParameterRef> list) throws KotlinIllegalStateExceptionWithAttachments, UninitializedPropertyAccessException {
            Object objConvert;
            ktSecondaryConstructor.getClass();
            firTypeRef.getClass();
            firTypeRef2.getClass();
            ktClassOrObject.getClass();
            list.getClass();
            boolean z = false;
            FirFunctionTarget firFunctionTarget = new FirFunctionTarget(null, false);
            PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
            FirConstructorBuilder firConstructorBuilder = new FirConstructorBuilder();
            firConstructorBuilder.setSymbol(new FirConstructorSymbol(psiRawFirBuilder.callableIdForClassConstructor()));
            FirConstructorSymbol symbol = firConstructorBuilder.getSymbol();
            psiRawFirBuilder.getContext().pushContainerSymbol(symbol);
            try {
                firConstructorBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, ktSecondaryConstructor, null, 1, null));
                firConstructorBuilder.setModuleData(psiRawFirBuilder.getBaseModuleData());
                firConstructorBuilder.setOrigin(FirDeclarationOrigin.Source.INSTANCE);
                firConstructorBuilder.setReturnTypeRef(firTypeRef2);
                Visibility constructorExplicitVisibility = psiRawFirBuilder.getConstructorExplicitVisibility(ktSecondaryConstructor);
                FirDeclarationStatusImpl firDeclarationStatusImpl = new FirDeclarationStatusImpl(constructorExplicitVisibility == null ? psiRawFirBuilder.constructorDefaultVisibility(ktClassOrObject) : constructorExplicitVisibility, Modality.FINAL);
                firDeclarationStatusImpl.setExpect(PsiUtilsKt.hasExpectModifier(ktSecondaryConstructor) || psiRawFirBuilder.getContext().getContainerIsExpect());
                firDeclarationStatusImpl.setActual(PsiUtilsKt.hasActualModifier(ktSecondaryConstructor));
                firDeclarationStatusImpl.setInner(PsiUtilsKt.hasInnerModifier(ktClassOrObject));
                if (ktClassOrObject.hasModifier(KtTokens.SEALED_KEYWORD) && constructorExplicitVisibility != Visibilities.Private.INSTANCE) {
                    z = true;
                }
                firDeclarationStatusImpl.setFromSealedClass(z);
                firDeclarationStatusImpl.setFromEnumClass(ktClassOrObject.hasModifier(KtTokens.ENUM_KEYWORD));
                firConstructorBuilder.setStatus(firDeclarationStatusImpl);
                firConstructorBuilder.setLocal(psiRawFirBuilder.getContext().getInLocalContext());
                firConstructorBuilder.setDispatchReceiverType(obtainDispatchReceiverForConstructor(ktClassOrObject));
                List<FirValueParameter> contextParameters = firConstructorBuilder.getContextParameters();
                KtModifierList modifierList = ktSecondaryConstructor.getModifierList();
                List<? extends KtContextParameterList> contextParameterLists = modifierList != null ? modifierList.getContextParameterLists() : null;
                if (contextParameterLists == null) {
                    contextParameterLists = CollectionsKt.emptyList();
                }
                addContextParameters(contextParameters, contextParameterLists, firConstructorBuilder.getSymbol());
                if ((!ktClassOrObject.hasModifier(KtTokens.EXTERNAL_KEYWORD) && !firConstructorBuilder.getStatus().isExpect()) || ktSecondaryConstructor.isExplicitDelegationCall()) {
                    PsiRawFirBuilder$Visitor$buildOrLazyDelegatedConstructorCall$1 psiRawFirBuilder$Visitor$buildOrLazyDelegatedConstructorCall$1 = new PsiRawFirBuilder$Visitor$buildOrLazyDelegatedConstructorCall$1(ktSecondaryConstructor.isDelegatedCallToThis(), firTypeRef);
                    int i = WhenMappings.$EnumSwitchMapping$0[PsiRawFirBuilder.this.getMode().ordinal()];
                    if (i == 1) {
                        objConvert = convert(ktSecondaryConstructor.getDelegationCall(), firTypeRef);
                    } else {
                        if (i != 2) {
                            throw new NoWhenBranchMatchedException();
                        }
                        int i2 = WhenMappings.$EnumSwitchMapping$0[PsiRawFirBuilder.this.getMode().ordinal()];
                        if (i2 == 1) {
                            objConvert = psiRawFirBuilder$Visitor$buildOrLazyDelegatedConstructorCall$1.invoke();
                        } else {
                            if (i2 != 2) {
                                throw new NoWhenBranchMatchedException();
                            }
                            objConvert = AstLoadingFilter.disallowTreeLoading(new PsiRawFirBuilder$runOnStubs$1(psiRawFirBuilder$Visitor$buildOrLazyDelegatedConstructorCall$1));
                        }
                    }
                    firConstructorBuilder.setDelegatedConstructor((FirDelegatedConstructorCall) objConvert);
                }
                psiRawFirBuilder.getContext().getFirFunctionTargets().add(firFunctionTarget);
                extractAnnotationsTo((KtAnnotated) ktSecondaryConstructor, (FirAnnotationContainerBuilder) firConstructorBuilder);
                CollectionsKt.addAll(firConstructorBuilder.getTypeParameters(), psiRawFirBuilder.constructorTypeParametersFromConstructedClass(list));
                extractValueParametersTo$default(this, ktSecondaryConstructor, firConstructorBuilder, firConstructorBuilder.getSymbol(), AbstractRawFirBuilder.ValueParameterDeclaration.FUNCTION, null, null, 24, null);
                boolean forceKeepingTheBodyInHeaderMode = psiRawFirBuilder.getContext().getForceKeepingTheBodyInHeaderMode();
                psiRawFirBuilder.getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
                boolean inLocalContext = psiRawFirBuilder.getContext().getInLocalContext();
                psiRawFirBuilder.getContext().setInLocalContext(true);
                FqName classNameBeforeLocalContext = psiRawFirBuilder.getContext().getClassNameBeforeLocalContext();
                if (!inLocalContext) {
                    psiRawFirBuilder.getContext().setClassNameBeforeLocalContext(psiRawFirBuilder.getContext().getClassName());
                }
                FqName className = psiRawFirBuilder.getContext().getClassName();
                psiRawFirBuilder.getContext().setClassName(FqName.ROOT);
                try {
                    Pair<FirBlock, FirContractDescription> pairBuildFirBody = buildFirBody(ktSecondaryConstructor);
                    psiRawFirBuilder.getContext().setClassNameBeforeLocalContext(classNameBeforeLocalContext);
                    psiRawFirBuilder.getContext().setInLocalContext(inLocalContext);
                    psiRawFirBuilder.getContext().setClassName(className);
                    psiRawFirBuilder.getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
                    FirBlock firBlock = (FirBlock) pairBuildFirBody.component1();
                    FirContractDescription firContractDescription = (FirContractDescription) pairBuildFirBody.component2();
                    if (firContractDescription != null) {
                        firConstructorBuilder.setContractDescription(firContractDescription);
                    }
                    firConstructorBuilder.setBody(firBlock);
                    psiRawFirBuilder.removeLast(psiRawFirBuilder.getContext().getFirFunctionTargets());
                    Unit unit = Unit.INSTANCE;
                    psiRawFirBuilder.getContext().popContainerSymbol(symbol);
                    FirConstructor firConstructorMo288build = firConstructorBuilder.mo288build();
                    PsiRawFirBuilder psiRawFirBuilder2 = PsiRawFirBuilder.this;
                    ConeClassLikeType coneClassLikeTypeCurrentDispatchReceiverType = psiRawFirBuilder2.currentDispatchReceiverType();
                    coneClassLikeTypeCurrentDispatchReceiverType.getClass();
                    ClassMembersKt.setContainingClassForStaticMemberAttr(firConstructorMo288build, coneClassLikeTypeCurrentDispatchReceiverType.getLookupTag());
                    psiRawFirBuilder2.bindFunctionTarget(firFunctionTarget, firConstructorMo288build);
                    return firConstructorMo288build;
                } catch (Throwable th) {
                    psiRawFirBuilder.getContext().setClassNameBeforeLocalContext(classNameBeforeLocalContext);
                    psiRawFirBuilder.getContext().setInLocalContext(inLocalContext);
                    psiRawFirBuilder.getContext().setClassName(className);
                    psiRawFirBuilder.getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
                    throw th;
                }
            } catch (Throwable th2) {
                psiRawFirBuilder.getContext().popContainerSymbol(symbol);
                throw th2;
            }
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
        /* JADX WARN: Code duplicated, block: B:100:0x03bf A[Catch: all -> 0x0295, TryCatch #6 {all -> 0x0295, blocks: (B:66:0x0253, B:68:0x0285, B:71:0x02a0, B:98:0x0399, B:100:0x03bf, B:101:0x03c2), top: B:164:0x0253 }] */
        /* JADX WARN: Code duplicated, block: B:104:0x03db A[Catch: all -> 0x0403, TryCatch #3 {all -> 0x0403, blocks: (B:111:0x042b, B:110:0x0424, B:102:0x03cb, B:104:0x03db, B:106:0x03eb, B:109:0x0408), top: B:158:0x03cb }] */
        /* JADX WARN: Code duplicated, block: B:106:0x03eb A[Catch: all -> 0x0403, TryCatch #3 {all -> 0x0403, blocks: (B:111:0x042b, B:110:0x0424, B:102:0x03cb, B:104:0x03db, B:106:0x03eb, B:109:0x0408), top: B:158:0x03cb }] */
        /* JADX WARN: Code duplicated, block: B:114:0x0447  */
        /* JADX WARN: Code duplicated, block: B:119:0x0473  */
        /* JADX WARN: Code duplicated, block: B:145:0x0558 A[Catch: all -> 0x0470, TryCatch #1 {all -> 0x0470, blocks: (B:115:0x0449, B:116:0x046f, B:143:0x0548, B:145:0x0558, B:147:0x0566, B:148:0x057d, B:149:0x0597, B:150:0x0598, B:151:0x05bc), top: B:155:0x0101 }] */
        /* JADX WARN: Code duplicated, block: B:147:0x0566 A[Catch: all -> 0x0470, TryCatch #1 {all -> 0x0470, blocks: (B:115:0x0449, B:116:0x046f, B:143:0x0548, B:145:0x0558, B:147:0x0566, B:148:0x057d, B:149:0x0597, B:150:0x0598, B:151:0x05bc), top: B:155:0x0101 }] */
        /* JADX WARN: Code duplicated, block: B:150:0x0598 A[Catch: all -> 0x0470, TryCatch #1 {all -> 0x0470, blocks: (B:115:0x0449, B:116:0x046f, B:143:0x0548, B:145:0x0558, B:147:0x0566, B:148:0x057d, B:149:0x0597, B:150:0x0598, B:151:0x05bc), top: B:155:0x0101 }] */
        /* JADX WARN: Code duplicated, block: B:177:0x033c A[EDGE_INSN: B:177:0x033c->B:89:0x033c BREAK  A[LOOP:1: B:82:0x0312->B:88:0x0335], SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:68:0x0285 A[Catch: all -> 0x0295, TryCatch #6 {all -> 0x0295, blocks: (B:66:0x0253, B:68:0x0285, B:71:0x02a0, B:98:0x0399, B:100:0x03bf, B:101:0x03c2), top: B:164:0x0253 }] */
        /* JADX WARN: Code duplicated, block: B:75:0x02f6 A[Catch: all -> 0x02fb, TryCatch #10 {all -> 0x02fb, blocks: (B:73:0x02df, B:75:0x02f6, B:80:0x030a, B:81:0x030e, B:82:0x0312, B:84:0x0318, B:86:0x032e, B:88:0x0335, B:89:0x033c), top: B:154:0x02df, outer: #0 }] */
        /* JADX WARN: Code duplicated, block: B:78:0x0306  */
        /* JADX WARN: Code duplicated, block: B:80:0x030a A[Catch: all -> 0x02fb, TryCatch #10 {all -> 0x02fb, blocks: (B:73:0x02df, B:75:0x02f6, B:80:0x030a, B:81:0x030e, B:82:0x0312, B:84:0x0318, B:86:0x032e, B:88:0x0335, B:89:0x033c), top: B:154:0x02df, outer: #0 }] */
        /* JADX WARN: Code duplicated, block: B:84:0x0318 A[Catch: all -> 0x02fb, TryCatch #10 {all -> 0x02fb, blocks: (B:73:0x02df, B:75:0x02f6, B:80:0x030a, B:81:0x030e, B:82:0x0312, B:84:0x0318, B:86:0x032e, B:88:0x0335, B:89:0x033c), top: B:154:0x02df, outer: #0 }] */
        /* JADX WARN: Code duplicated, block: B:86:0x032e A[Catch: all -> 0x02fb, TryCatch #10 {all -> 0x02fb, blocks: (B:73:0x02df, B:75:0x02f6, B:80:0x030a, B:81:0x030e, B:82:0x0312, B:84:0x0318, B:86:0x032e, B:88:0x0335, B:89:0x033c), top: B:154:0x02df, outer: #0 }] */
        /* JADX WARN: Code duplicated, block: B:87:0x0333  */
        /* JADX WARN: Code duplicated, block: B:92:0x034e A[Catch: all -> 0x0374, TryCatch #5 {all -> 0x0374, blocks: (B:72:0x02b1, B:90:0x033e, B:92:0x034e, B:94:0x035c, B:97:0x037f), top: B:162:0x02b1 }] */
        /* JADX WARN: Code duplicated, block: B:94:0x035c A[Catch: all -> 0x0374, TryCatch #5 {all -> 0x0374, blocks: (B:72:0x02b1, B:90:0x033e, B:92:0x034e, B:94:0x035c, B:97:0x037f), top: B:162:0x02b1 }] */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v6, types: [java.lang.StringBuilder] */
        /* JADX WARN: Type inference failed for: r13v1 */
        /* JADX WARN: Type inference failed for: r13v10 */
        /* JADX WARN: Type inference failed for: r13v11 */
        /* JADX WARN: Type inference failed for: r13v12 */
        /* JADX WARN: Type inference failed for: r13v2, types: [boolean] */
        /* JADX WARN: Type inference failed for: r13v23 */
        /* JADX WARN: Type inference failed for: r13v24 */
        /* JADX WARN: Type inference failed for: r13v5 */
        /* JADX WARN: Type inference failed for: r13v9 */
        /* JADX WARN: Type inference failed for: r15v0, types: [java.lang.String] */
        /* JADX WARN: Type inference failed for: r1v2 */
        /* JADX WARN: Type inference failed for: r1v21 */
        /* JADX WARN: Type inference failed for: r1v3, types: [java.lang.String] */
        /* JADX WARN: Type inference failed for: r1v30 */
        /* JADX WARN: Type inference failed for: r1v31 */
        /* JADX WARN: Type inference failed for: r1v32, types: [org.jetbrains.kotlin.fir.builder.Context] */
        /* JADX WARN: Type inference failed for: r1v34 */
        /* JADX WARN: Type inference failed for: r1v40, types: [java.util.Iterator] */
        /* JADX WARN: Type inference failed for: r1v76 */
        /* JADX WARN: Type inference failed for: r25v0, types: [org.jetbrains.kotlin.fir.builder.PsiRawFirBuilder$Visitor] */
        /* JADX WARN: Type inference failed for: r2v10 */
        /* JADX WARN: Type inference failed for: r2v12 */
        /* JADX WARN: Type inference failed for: r2v15 */
        /* JADX WARN: Type inference failed for: r2v16 */
        /* JADX WARN: Type inference failed for: r2v18, types: [org.jetbrains.kotlin.name.FqName] */
        /* JADX WARN: Type inference failed for: r2v2 */
        /* JADX WARN: Type inference failed for: r2v20 */
        /* JADX WARN: Type inference failed for: r2v22, types: [boolean] */
        /* JADX WARN: Type inference failed for: r2v3, types: [int] */
        /* JADX WARN: Type inference failed for: r2v39 */
        public final FirDeclaration toFirEnumEntry(KtEnumEntry ktEnumEntry, FirResolvedTypeRef firResolvedTypeRef, boolean z) throws Throwable {
            PsiRawFirBuilder psiRawFirBuilder;
            ?? r13;
            ?? r2;
            boolean z2;
            Object next;
            KtPsiSourceElement source;
            boolean z3;
            FirEnumEntrySymbol firEnumEntrySymbol;
            FirEnumEntryBuilder firEnumEntryBuilder;
            boolean forceKeepingTheBodyInHeaderMode;
            boolean inLocalContext;
            FqName classNameBeforeLocalContext;
            FqName className;
            ?? context;
            ?? className2;
            boolean containerIsExpect;
            int size;
            KtClassBody body;
            List listEmptyList;
            FirAnonymousObject firAnonymousObjectMo288build;
            CompanionBlockInfo companionBlockInfoOrNull;
            Object objMo288build;
            ConeClassLikeType coneClassLikeTypeCurrentDispatchReceiverType;
            ConeClassLikeLookupTag lookupTag;
            KtConstructorCalleeExpression calleeExpression;
            KtTypeReference typeReference;
            FirEnumEntryBuilder firEnumEntryBuilder2;
            ?? r15 = "Wrong number of ";
            ktEnumEntry.getClass();
            firResolvedTypeRef.getClass();
            FirEnumEntrySymbol firEnumEntrySymbol2 = null;
            boolean z4 = PsiUtilsKt.hasExpectModifier(ktEnumEntry) || PsiRawFirBuilder.this.getContext().getContainerIsExpect();
            PsiRawFirBuilder psiRawFirBuilder2 = PsiRawFirBuilder.this;
            Name nameAsSafeName = ktEnumEntry.getNameAsSafeName();
            nameAsSafeName.getClass();
            FirEnumEntrySymbol firEnumEntrySymbol3 = new FirEnumEntrySymbol(psiRawFirBuilder2.callableIdForName(nameAsSafeName));
            PsiRawFirBuilder psiRawFirBuilder3 = PsiRawFirBuilder.this;
            psiRawFirBuilder3.getContext().pushContainerSymbol(firEnumEntrySymbol3);
            try {
                FirEnumEntryBuilder firEnumEntryBuilder3 = new FirEnumEntryBuilder();
                firEnumEntryBuilder3.setSource(AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder3, ktEnumEntry, null, 1, null));
                firEnumEntryBuilder3.setModuleData(psiRawFirBuilder3.getBaseModuleData());
                FirDeclarationOrigin.Source source2 = FirDeclarationOrigin.Source.INSTANCE;
                firEnumEntryBuilder3.setOrigin(source2);
                firEnumEntryBuilder3.setReturnTypeRef(firResolvedTypeRef);
                Name nameAsSafeName2 = ktEnumEntry.getNameAsSafeName();
                nameAsSafeName2.getClass();
                firEnumEntryBuilder3.setName(nameAsSafeName2);
                Visibilities.Public r12 = Visibilities.Public.INSTANCE;
                Modality modality = Modality.FINAL;
                FirDeclarationStatusImpl firDeclarationStatusImpl = new FirDeclarationStatusImpl(r12, modality);
                firDeclarationStatusImpl.setStatic(true);
                firDeclarationStatusImpl.setExpect(z4);
                firEnumEntryBuilder3.setStatus(firDeclarationStatusImpl);
                firEnumEntryBuilder3.setSymbol(firEnumEntrySymbol3);
                firEnumEntryBuilder3.setLocal(psiRawFirBuilder3.getContext().getInLocalContext());
                if (z && ktEnumEntry.getInitializerList() == null && ktEnumEntry.getAnnotationEntries().isEmpty() && ktEnumEntry.getBody() == null) {
                    firEnumEntrySymbol = firEnumEntrySymbol3;
                    psiRawFirBuilder = psiRawFirBuilder3;
                    firEnumEntryBuilder2 = firEnumEntryBuilder3;
                } else {
                    extractAnnotationsTo(ktEnumEntry, firEnumEntryBuilder3);
                    KtFakeSourceElementKind.EnumInitializer enumInitializer = KtFakeSourceElementKind.EnumInitializer.INSTANCE;
                    PsiRawFirBuilder$Visitor$buildOrLazyExpression$1 psiRawFirBuilder$Visitor$buildOrLazyExpression$1 = new PsiRawFirBuilder$Visitor$buildOrLazyExpression$1(psiRawFirBuilder3.toFirSourceElement((PsiElement) ktEnumEntry, (KtFakeSourceElementKind) enumInitializer));
                    int i = WhenMappings.$EnumSwitchMapping$0[PsiRawFirBuilder.this.getMode().ordinal()];
                    if (i == 1) {
                        try {
                            Name nameAsSafeName3 = ktEnumEntry.getNameAsSafeName();
                            nameAsSafeName3.getClass();
                            psiRawFirBuilder3.getContext().setClassName(psiRawFirBuilder3.getContext().getClassName().child(nameAsSafeName3));
                            boolean containerIsExpect2 = psiRawFirBuilder3.getContext().getContainerIsExpect();
                            psiRawFirBuilder3.getContext().setContainerIsExpect(containerIsExpect2);
                            int size2 = psiRawFirBuilder3.getContext().getDispatchReceiverTypesStack().size();
                            try {
                                FirAnonymousObjectExpressionBuilder firAnonymousObjectExpressionBuilder = new FirAnonymousObjectExpressionBuilder();
                                KtSourceElement firSourceElement = psiRawFirBuilder3.toFirSourceElement((PsiElement) ktEnumEntry, (KtFakeSourceElementKind) enumInitializer);
                                firAnonymousObjectExpressionBuilder.setSource(firSourceElement);
                                CompanionBlockCollector companionBlockCollector = new CompanionBlockCollector();
                                try {
                                    FirAnonymousObjectBuilder firAnonymousObjectBuilder = new FirAnonymousObjectBuilder();
                                    firAnonymousObjectBuilder.setSource(firSourceElement);
                                    firAnonymousObjectBuilder.setModuleData(psiRawFirBuilder3.getBaseModuleData());
                                    firAnonymousObjectBuilder.setOrigin(source2);
                                    firAnonymousObjectBuilder.setClassKind(ClassKind.ENUM_ENTRY);
                                    firAnonymousObjectBuilder.setScopeProvider(psiRawFirBuilder3.getBaseScopeProvider());
                                    firAnonymousObjectBuilder.setSymbol(new FirAnonymousObjectSymbol(psiRawFirBuilder3.getContext().getPackageFqName()));
                                    firAnonymousObjectBuilder.setStatus(new FirDeclarationStatusImpl(Visibilities.Local.INSTANCE, modality));
                                    FirResolvedTypeRefBuilder firResolvedTypeRefBuilder = new FirResolvedTypeRefBuilder();
                                    firResolvedTypeRefBuilder.setConeType(new ConeClassLikeTypeImpl(firAnonymousObjectBuilder.getSymbol().getLookupTag(), new ConeTypeProjection[0], false, null, 8, null));
                                    firResolvedTypeRefBuilder.setSource(psiRawFirBuilder3.toFirSourceElement((PsiElement) ktEnumEntry, (KtFakeSourceElementKind) KtFakeSourceElementKind.ClassSelfTypeRef.INSTANCE));
                                    FirResolvedTypeRef firResolvedTypeRefBuild = firResolvedTypeRefBuilder.build();
                                    psiRawFirBuilder3.registerSelfType(firResolvedTypeRefBuild);
                                    firAnonymousObjectBuilder.getSuperTypeRefs().add(firResolvedTypeRef);
                                    List superTypeListEntries = ktEnumEntry.getSuperTypeListEntries();
                                    superTypeListEntries.getClass();
                                    Iterator it = superTypeListEntries.iterator();
                                    do {
                                        if (!it.hasNext()) {
                                            next = null;
                                            break;
                                        }
                                        next = it.next();
                                    } while (!(next instanceof KtSuperTypeCallEntry));
                                    KtSuperTypeCallEntry ktSuperTypeCallEntry = (KtSuperTypeCallEntry) next;
                                    FirResolvedTypeRefBuilder firResolvedTypeRefBuilder2 = new FirResolvedTypeRefBuilder();
                                    try {
                                        try {
                                            try {
                                                try {
                                                    try {
                                                        try {
                                                            if (ktSuperTypeCallEntry != null && (calleeExpression = ktSuperTypeCallEntry.getCalleeExpression()) != null && (typeReference = calleeExpression.getTypeReference()) != null) {
                                                                source = AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder3, typeReference, null, 1, null);
                                                                if (source != null) {
                                                                }
                                                                firResolvedTypeRefBuilder2.setSource(source);
                                                                firResolvedTypeRefBuilder2.setConeType(firResolvedTypeRef.getConeType());
                                                                FirResolvedTypeRef firResolvedTypeRefBuild2 = firResolvedTypeRefBuilder2.build();
                                                                z3 = containerIsExpect2;
                                                                r13 = 1792;
                                                                firEnumEntrySymbol = firEnumEntrySymbol3;
                                                                firEnumEntryBuilder = firEnumEntryBuilder3;
                                                                psiRawFirBuilder = psiRawFirBuilder3;
                                                                firAnonymousObjectBuilder.getDeclarations().add(toFirConstructor$default(this, ktEnumEntry.getPrimaryConstructor(), ktSuperTypeCallEntry, firResolvedTypeRefBuild2, firResolvedTypeRefBuild, ktEnumEntry, firAnonymousObjectBuilder.getTypeParameters(), CollectionsKt.emptyList(), z4, true, false, false, false, 1792, null));
                                                                Name name = SpecialNames.ANONYMOUS;
                                                                forceKeepingTheBodyInHeaderMode = psiRawFirBuilder.getContext().getForceKeepingTheBodyInHeaderMode();
                                                                psiRawFirBuilder.getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
                                                                inLocalContext = psiRawFirBuilder.getContext().getInLocalContext();
                                                                psiRawFirBuilder.getContext().setInLocalContext(true);
                                                                classNameBeforeLocalContext = psiRawFirBuilder.getContext().getClassNameBeforeLocalContext();
                                                                if (!inLocalContext) {
                                                                    psiRawFirBuilder.getContext().setClassNameBeforeLocalContext(psiRawFirBuilder.getContext().getClassName());
                                                                }
                                                                className = psiRawFirBuilder.getContext().getClassName();
                                                                psiRawFirBuilder.getContext().setClassName(FqName.ROOT);
                                                                context = psiRawFirBuilder.getContext();
                                                                className2 = psiRawFirBuilder.getContext().getClassName();
                                                                context.setClassName(className2.child(name));
                                                                containerIsExpect = psiRawFirBuilder.getContext().getContainerIsExpect();
                                                                psiRawFirBuilder.getContext().setContainerIsExpect(containerIsExpect);
                                                                size = psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().size();
                                                                addDeclarations(firAnonymousObjectBuilder, ktEnumEntry.getBody(), firResolvedTypeRefBuild2, firResolvedTypeRefBuild, ktEnumEntry, companionBlockCollector);
                                                                body = ktEnumEntry.getBody();
                                                                if (body != null) {
                                                                    listEmptyList = body.getDanglingModifierLists();
                                                                } else {
                                                                    listEmptyList = null;
                                                                }
                                                                if (listEmptyList == null) {
                                                                    listEmptyList = CollectionsKt.emptyList();
                                                                }
                                                                context = listEmptyList.iterator();
                                                                while (true) {
                                                                    className2 = context.hasNext();
                                                                    if (className2 != 0) {
                                                                        break;
                                                                    }
                                                                    KtModifierList ktModifierList = (KtModifierList) context.next();
                                                                    List<FirDeclaration> declarations = firAnonymousObjectBuilder.getDeclarations();
                                                                    FirDanglingModifierList firDanglingModifierListBuildErrorNonLocalDeclarationForDanglingModifierList = buildErrorNonLocalDeclarationForDanglingModifierList(ktModifierList);
                                                                    coneClassLikeTypeCurrentDispatchReceiverType = psiRawFirBuilder.currentDispatchReceiverType();
                                                                    if (coneClassLikeTypeCurrentDispatchReceiverType != null) {
                                                                        lookupTag = coneClassLikeTypeCurrentDispatchReceiverType.getLookupTag();
                                                                    } else {
                                                                        lookupTag = null;
                                                                    }
                                                                    ClassMembersKt.setContainingClassAttr(firDanglingModifierListBuildErrorNonLocalDeclarationForDanglingModifierList, lookupTag);
                                                                    declarations.add(firDanglingModifierListBuildErrorNonLocalDeclarationForDanglingModifierList);
                                                                }
                                                                Unit unit = Unit.INSTANCE;
                                                                if (psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().size() <= size + 1) {
                                                                    throw new IllegalArgumentException(("Wrong number of " + psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().size()).toString());
                                                                }
                                                                if (psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().size() > size) {
                                                                    psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().remove(CollectionsKt.getLastIndex(psiRawFirBuilder.getContext().getDispatchReceiverTypesStack()));
                                                                }
                                                                psiRawFirBuilder.getContext().setClassName(psiRawFirBuilder.getContext().getClassName().parent());
                                                                psiRawFirBuilder.getContext().setContainerIsExpect(containerIsExpect);
                                                                psiRawFirBuilder.getContext().setClassNameBeforeLocalContext(classNameBeforeLocalContext);
                                                                psiRawFirBuilder.getContext().setInLocalContext(inLocalContext);
                                                                psiRawFirBuilder.getContext().setClassName(className);
                                                                psiRawFirBuilder.getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
                                                                firAnonymousObjectMo288build = firAnonymousObjectBuilder.mo288build();
                                                                companionBlockInfoOrNull = companionBlockCollector.toCompanionBlockInfoOrNull();
                                                                if (companionBlockInfoOrNull != null) {
                                                                    ClassMembersKt.setCompanionBlocks(firAnonymousObjectMo288build, companionBlockInfoOrNull);
                                                                }
                                                                firAnonymousObjectExpressionBuilder.setAnonymousObject(firAnonymousObjectMo288build);
                                                                objMo288build = firAnonymousObjectExpressionBuilder.mo288build();
                                                                try {
                                                                    if (psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().size() <= size2 + 1) {
                                                                        throw new IllegalArgumentException(("Wrong number of " + psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().size()).toString());
                                                                    }
                                                                    if (psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().size() > size2) {
                                                                        psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().remove(CollectionsKt.getLastIndex(psiRawFirBuilder.getContext().getDispatchReceiverTypesStack()));
                                                                    }
                                                                    psiRawFirBuilder.getContext().setClassName(psiRawFirBuilder.getContext().getClassName().parent());
                                                                    psiRawFirBuilder.getContext().setContainerIsExpect(z3);
                                                                } catch (Throwable th) {
                                                                    th = th;
                                                                    firEnumEntrySymbol2 = firEnumEntrySymbol;
                                                                    psiRawFirBuilder.getContext().popContainerSymbol(firEnumEntrySymbol2);
                                                                    throw th;
                                                                }
                                                            }
                                                            addDeclarations(firAnonymousObjectBuilder, ktEnumEntry.getBody(), firResolvedTypeRefBuild2, firResolvedTypeRefBuild, ktEnumEntry, companionBlockCollector);
                                                            body = ktEnumEntry.getBody();
                                                            if (body != null) {
                                                                listEmptyList = body.getDanglingModifierLists();
                                                            } else {
                                                                listEmptyList = null;
                                                            }
                                                            if (listEmptyList == null) {
                                                                listEmptyList = CollectionsKt.emptyList();
                                                            }
                                                            context = listEmptyList.iterator();
                                                            while (true) {
                                                                className2 = context.hasNext();
                                                                if (className2 != 0) {
                                                                    break;
                                                                    break;
                                                                }
                                                                KtModifierList ktModifierList2 = (KtModifierList) context.next();
                                                                List<FirDeclaration> declarations2 = firAnonymousObjectBuilder.getDeclarations();
                                                                FirDanglingModifierList firDanglingModifierListBuildErrorNonLocalDeclarationForDanglingModifierList2 = buildErrorNonLocalDeclarationForDanglingModifierList(ktModifierList2);
                                                                coneClassLikeTypeCurrentDispatchReceiverType = psiRawFirBuilder.currentDispatchReceiverType();
                                                                if (coneClassLikeTypeCurrentDispatchReceiverType != null) {
                                                                    lookupTag = coneClassLikeTypeCurrentDispatchReceiverType.getLookupTag();
                                                                } else {
                                                                    lookupTag = null;
                                                                }
                                                                ClassMembersKt.setContainingClassAttr(firDanglingModifierListBuildErrorNonLocalDeclarationForDanglingModifierList2, lookupTag);
                                                                declarations2.add(firDanglingModifierListBuildErrorNonLocalDeclarationForDanglingModifierList2);
                                                            }
                                                            Unit unit2 = Unit.INSTANCE;
                                                            if (psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().size() <= size + 1) {
                                                                throw new IllegalArgumentException(("Wrong number of " + psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().size()).toString());
                                                            }
                                                            if (psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().size() > size) {
                                                                psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().remove(CollectionsKt.getLastIndex(psiRawFirBuilder.getContext().getDispatchReceiverTypesStack()));
                                                            }
                                                            psiRawFirBuilder.getContext().setClassName(psiRawFirBuilder.getContext().getClassName().parent());
                                                            psiRawFirBuilder.getContext().setContainerIsExpect(containerIsExpect);
                                                            psiRawFirBuilder.getContext().setClassNameBeforeLocalContext(classNameBeforeLocalContext);
                                                            psiRawFirBuilder.getContext().setInLocalContext(inLocalContext);
                                                            psiRawFirBuilder.getContext().setClassName(className);
                                                            psiRawFirBuilder.getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
                                                            firAnonymousObjectMo288build = firAnonymousObjectBuilder.mo288build();
                                                            companionBlockInfoOrNull = companionBlockCollector.toCompanionBlockInfoOrNull();
                                                            if (companionBlockInfoOrNull != null) {
                                                                ClassMembersKt.setCompanionBlocks(firAnonymousObjectMo288build, companionBlockInfoOrNull);
                                                            }
                                                            firAnonymousObjectExpressionBuilder.setAnonymousObject(firAnonymousObjectMo288build);
                                                            objMo288build = firAnonymousObjectExpressionBuilder.mo288build();
                                                            if (psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().size() <= size2 + 1) {
                                                                throw new IllegalArgumentException(("Wrong number of " + psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().size()).toString());
                                                            }
                                                            if (psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().size() > size2) {
                                                                psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().remove(CollectionsKt.getLastIndex(psiRawFirBuilder.getContext().getDispatchReceiverTypesStack()));
                                                            }
                                                            psiRawFirBuilder.getContext().setClassName(psiRawFirBuilder.getContext().getClassName().parent());
                                                            psiRawFirBuilder.getContext().setContainerIsExpect(z3);
                                                        } catch (Throwable th2) {
                                                            th = th2;
                                                            r15 = context;
                                                            r2 = className2;
                                                        }
                                                    } catch (Throwable th3) {
                                                        if (psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().size() > size + 1) {
                                                            throw new IllegalArgumentException(("Wrong number of " + psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().size()).toString());
                                                        }
                                                        if (psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().size() > size) {
                                                            psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().remove(CollectionsKt.getLastIndex(psiRawFirBuilder.getContext().getDispatchReceiverTypesStack()));
                                                        }
                                                        psiRawFirBuilder.getContext().setClassName(psiRawFirBuilder.getContext().getClassName().parent());
                                                        psiRawFirBuilder.getContext().setContainerIsExpect(containerIsExpect);
                                                        throw th3;
                                                    }
                                                    context = psiRawFirBuilder.getContext();
                                                    className2 = psiRawFirBuilder.getContext().getClassName();
                                                    context.setClassName(className2.child(name));
                                                    containerIsExpect = psiRawFirBuilder.getContext().getContainerIsExpect();
                                                    psiRawFirBuilder.getContext().setContainerIsExpect(containerIsExpect);
                                                    size = psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().size();
                                                } catch (Throwable th4) {
                                                    th = th4;
                                                    r2 = size2;
                                                    r15 = "Wrong number of ";
                                                    r13 = z3 ? 1 : 0;
                                                }
                                                firAnonymousObjectBuilder.getDeclarations().add(toFirConstructor$default(this, ktEnumEntry.getPrimaryConstructor(), ktSuperTypeCallEntry, firResolvedTypeRefBuild2, firResolvedTypeRefBuild, ktEnumEntry, firAnonymousObjectBuilder.getTypeParameters(), CollectionsKt.emptyList(), z4, true, false, false, false, 1792, null));
                                                Name name2 = SpecialNames.ANONYMOUS;
                                                forceKeepingTheBodyInHeaderMode = psiRawFirBuilder.getContext().getForceKeepingTheBodyInHeaderMode();
                                                psiRawFirBuilder.getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
                                                inLocalContext = psiRawFirBuilder.getContext().getInLocalContext();
                                                psiRawFirBuilder.getContext().setInLocalContext(true);
                                                classNameBeforeLocalContext = psiRawFirBuilder.getContext().getClassNameBeforeLocalContext();
                                                if (!inLocalContext) {
                                                    psiRawFirBuilder.getContext().setClassNameBeforeLocalContext(psiRawFirBuilder.getContext().getClassName());
                                                }
                                                className = psiRawFirBuilder.getContext().getClassName();
                                                psiRawFirBuilder.getContext().setClassName(FqName.ROOT);
                                                try {
                                                    psiRawFirBuilder.getContext().setClassNameBeforeLocalContext(classNameBeforeLocalContext);
                                                    psiRawFirBuilder.getContext().setInLocalContext(inLocalContext);
                                                    psiRawFirBuilder.getContext().setClassName(className);
                                                    psiRawFirBuilder.getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
                                                    throw th;
                                                } catch (Throwable th5) {
                                                    th = th5;
                                                    if (psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().size() > r2 + 1) {
                                                        ?? sb = new StringBuilder();
                                                        sb.append(r15);
                                                        sb.append(psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().size());
                                                        throw new IllegalArgumentException(sb.toString().toString());
                                                    }
                                                    if (psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().size() > r2) {
                                                        psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().remove(CollectionsKt.getLastIndex(psiRawFirBuilder.getContext().getDispatchReceiverTypesStack()));
                                                    }
                                                    psiRawFirBuilder.getContext().setClassName(psiRawFirBuilder.getContext().getClassName().parent());
                                                    psiRawFirBuilder.getContext().setContainerIsExpect(r13);
                                                    throw th;
                                                }
                                            } catch (Throwable th6) {
                                                th = th6;
                                                r2 = size2;
                                                r15 = "Wrong number of ";
                                                r13 = z3 ? 1 : 0;
                                            }
                                            r13 = 1792;
                                            firEnumEntrySymbol = firEnumEntrySymbol3;
                                            firEnumEntryBuilder = firEnumEntryBuilder3;
                                            psiRawFirBuilder = psiRawFirBuilder3;
                                        } catch (Throwable th7) {
                                            th = th7;
                                            psiRawFirBuilder = psiRawFirBuilder3;
                                            r13 = z3;
                                            r2 = size2;
                                            if (psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().size() > r2 + 1) {
                                                ?? sb2 = new StringBuilder();
                                                sb2.append(r15);
                                                sb2.append(psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().size());
                                                throw new IllegalArgumentException(sb2.toString().toString());
                                            }
                                            if (psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().size() > r2) {
                                                psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().remove(CollectionsKt.getLastIndex(psiRawFirBuilder.getContext().getDispatchReceiverTypesStack()));
                                            }
                                            psiRawFirBuilder.getContext().setClassName(psiRawFirBuilder.getContext().getClassName().parent());
                                            psiRawFirBuilder.getContext().setContainerIsExpect(r13);
                                            throw th;
                                        }
                                    } catch (Throwable th8) {
                                        th = th8;
                                        z2 = z3;
                                        psiRawFirBuilder = psiRawFirBuilder3;
                                        r13 = z2;
                                        r2 = size2;
                                        if (psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().size() > r2 + 1) {
                                            ?? sb3 = new StringBuilder();
                                            sb3.append(r15);
                                            sb3.append(psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().size());
                                            throw new IllegalArgumentException(sb3.toString().toString());
                                        }
                                        if (psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().size() > r2) {
                                            psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().remove(CollectionsKt.getLastIndex(psiRawFirBuilder.getContext().getDispatchReceiverTypesStack()));
                                        }
                                        psiRawFirBuilder.getContext().setClassName(psiRawFirBuilder.getContext().getClassName().parent());
                                        psiRawFirBuilder.getContext().setContainerIsExpect(r13);
                                        throw th;
                                    }
                                    source = firResolvedTypeRefBuild.getSource();
                                    firResolvedTypeRefBuilder2.setSource(source);
                                    firResolvedTypeRefBuilder2.setConeType(firResolvedTypeRef.getConeType());
                                    FirResolvedTypeRef firResolvedTypeRefBuild3 = firResolvedTypeRefBuilder2.build();
                                    z3 = containerIsExpect2;
                                } catch (Throwable th9) {
                                    th = th9;
                                    z2 = containerIsExpect2;
                                }
                            } catch (Throwable th10) {
                                th = th10;
                                r13 = containerIsExpect2;
                                psiRawFirBuilder = psiRawFirBuilder3;
                                r2 = size2;
                                r15 = "Wrong number of ";
                            }
                        } catch (Throwable th11) {
                            th = th11;
                            psiRawFirBuilder.getContext().popContainerSymbol(firEnumEntrySymbol2);
                            throw th;
                        }
                    } else {
                        if (i != 2) {
                            throw new NoWhenBranchMatchedException();
                        }
                        int i2 = WhenMappings.$EnumSwitchMapping$0[PsiRawFirBuilder.this.getMode().ordinal()];
                        if (i2 == 1) {
                            objMo288build = psiRawFirBuilder$Visitor$buildOrLazyExpression$1.invoke();
                        } else {
                            if (i2 != 2) {
                                throw new NoWhenBranchMatchedException();
                            }
                            objMo288build = AstLoadingFilter.disallowTreeLoading(new PsiRawFirBuilder$runOnStubs$1(psiRawFirBuilder$Visitor$buildOrLazyExpression$1));
                        }
                        firEnumEntrySymbol = firEnumEntrySymbol3;
                        psiRawFirBuilder = psiRawFirBuilder3;
                        firEnumEntryBuilder = firEnumEntryBuilder3;
                    }
                    firEnumEntryBuilder2 = firEnumEntryBuilder;
                    firEnumEntryBuilder2.setInitializer((FirExpression) objMo288build);
                }
                FirEnumEntry firEnumEntryMo288build = firEnumEntryBuilder2.mo288build();
                ConeClassLikeType coneClassLikeTypeCurrentDispatchReceiverType2 = psiRawFirBuilder.currentDispatchReceiverType();
                coneClassLikeTypeCurrentDispatchReceiverType2.getClass();
                ClassMembersKt.setContainingClassForStaticMemberAttr(firEnumEntryMo288build, coneClassLikeTypeCurrentDispatchReceiverType2.getLookupTag());
                psiRawFirBuilder.getContext().popContainerSymbol(firEnumEntrySymbol);
                return firEnumEntryMo288build;
            } catch (Throwable th12) {
                th = th12;
                firEnumEntrySymbol2 = firEnumEntrySymbol3;
                psiRawFirBuilder = psiRawFirBuilder3;
            }
        }

        public final FirTypeRef toFirOrErrorType(KtTypeReference ktTypeReference) {
            FirTypeRef firType;
            if (ktTypeReference != null && (firType = toFirType(ktTypeReference)) != null) {
                return firType;
            }
            PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
            FirErrorTypeRefBuilder firErrorTypeRefBuilder = new FirErrorTypeRefBuilder();
            firErrorTypeRefBuilder.setSource(ktTypeReference != null ? AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, ktTypeReference, null, 1, null) : null);
            firErrorTypeRefBuilder.setDiagnostic(new ConeSyntaxDiagnostic(ktTypeReference == null ? "Incomplete code" : "Conversion failed"));
            if (ktTypeReference != null) {
                extractAnnotationsTo((KtAnnotated) ktTypeReference, (FirAnnotationContainerBuilder) firErrorTypeRefBuilder);
            }
            return firErrorTypeRefBuilder.build();
        }

        public final FirExpression toInitializerExpression(KtDeclarationWithInitializer ktDeclarationWithInitializer) {
            Object firExpression;
            ktDeclarationWithInitializer.getClass();
            boolean zHasInitializer = ktDeclarationWithInitializer.hasInitializer();
            PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
            if (!zHasInitializer) {
                return null;
            }
            psiRawFirBuilder.getContext().getCalleeNamesForLambda().add(null);
            PsiRawFirBuilder$Visitor$buildOrLazyExpression$1 psiRawFirBuilder$Visitor$buildOrLazyExpression$1 = new PsiRawFirBuilder$Visitor$buildOrLazyExpression$1(null);
            int i = WhenMappings.$EnumSwitchMapping$0[PsiRawFirBuilder.this.getMode().ordinal()];
            if (i == 1) {
                boolean forceKeepingTheBodyInHeaderMode = psiRawFirBuilder.getContext().getForceKeepingTheBodyInHeaderMode();
                psiRawFirBuilder.getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
                boolean inLocalContext = psiRawFirBuilder.getContext().getInLocalContext();
                psiRawFirBuilder.getContext().setInLocalContext(true);
                FqName classNameBeforeLocalContext = psiRawFirBuilder.getContext().getClassNameBeforeLocalContext();
                if (!inLocalContext) {
                    psiRawFirBuilder.getContext().setClassNameBeforeLocalContext(psiRawFirBuilder.getContext().getClassName());
                }
                FqName className = psiRawFirBuilder.getContext().getClassName();
                psiRawFirBuilder.getContext().setClassName(FqName.ROOT);
                try {
                    firExpression = toFirExpression(ktDeclarationWithInitializer.getInitializer(), "Should have initializer", ktDeclarationWithInitializer);
                    psiRawFirBuilder.getContext().setClassNameBeforeLocalContext(classNameBeforeLocalContext);
                    psiRawFirBuilder.getContext().setInLocalContext(inLocalContext);
                    psiRawFirBuilder.getContext().setClassName(className);
                    psiRawFirBuilder.getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
                } catch (Throwable th) {
                    psiRawFirBuilder.getContext().setClassNameBeforeLocalContext(classNameBeforeLocalContext);
                    psiRawFirBuilder.getContext().setInLocalContext(inLocalContext);
                    psiRawFirBuilder.getContext().setClassName(className);
                    psiRawFirBuilder.getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
                    throw th;
                }
            } else {
                if (i != 2) {
                    bu8.a();
                    return null;
                }
                int i2 = WhenMappings.$EnumSwitchMapping$0[PsiRawFirBuilder.this.getMode().ordinal()];
                if (i2 == 1) {
                    firExpression = psiRawFirBuilder$Visitor$buildOrLazyExpression$1.invoke();
                } else {
                    if (i2 != 2) {
                        bu8.a();
                        return null;
                    }
                    firExpression = AstLoadingFilter.disallowTreeLoading(new PsiRawFirBuilder$runOnStubs$1(psiRawFirBuilder$Visitor$buildOrLazyExpression$1));
                }
            }
            FirExpression firExpression2 = (FirExpression) firExpression;
            psiRawFirBuilder.removeLast(psiRawFirBuilder.getContext().getCalleeNamesForLambda());
            return firExpression2;
        }

        public FirElement visitAnnotatedExpression(KtAnnotatedExpression expression, FirElement data) {
            String strRender;
            expression.getClass();
            KtExpression baseExpression = expression.getBaseExpression();
            PsiRawFirBuilder.this.getContext().forwardLabelUsagePermission(expression, baseExpression);
            FirElement firElement = baseExpression != null ? (FirElement) baseExpression.accept(this, data) : null;
            FirAnnotationContainer firAnnotationContainerBuildErrorExpression$default = firElement instanceof FirAnnotationContainer ? (FirAnnotationContainer) firElement : null;
            if (firAnnotationContainerBuildErrorExpression$default == null) {
                KtSourceElement firSourceElement$default = AbstractRawFirBuilder.toFirSourceElement$default(PsiRawFirBuilder.this, expression, null, 1, null);
                if (firElement == null || (strRender = UtilsKt.render(firElement)) == null) {
                    strRender = "???";
                }
                firAnnotationContainerBuildErrorExpression$default = FirExpressionUtilKt.buildErrorExpression$default(firSourceElement$default, new ConeNotAnnotationContainer(strRender), null, 4, null);
            }
            extractAnnotationsTo((KtAnnotated) expression, firAnnotationContainerBuildErrorExpression$default);
            return firAnnotationContainerBuildErrorExpression$default;
        }

        public FirElement visitAnnotationEntry(KtAnnotationEntry annotationEntry, FirElement data) {
            Name nameSpecial;
            FirAnnotationCall firAnnotationCallMo288build;
            KtSimpleNameExpression referenceExpression;
            List qualifier;
            FirQualifierPart firQualifierPart;
            Name nameSpecial2;
            KtSimpleNameExpression referenceExpression2;
            List qualifier2;
            FirQualifierPart firQualifierPart2;
            annotationEntry.getClass();
            PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
            boolean forceKeepingTheBodyInHeaderMode = psiRawFirBuilder.getContext().getForceKeepingTheBodyInHeaderMode();
            psiRawFirBuilder.getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
            boolean inLocalContext = psiRawFirBuilder.getContext().getInLocalContext();
            psiRawFirBuilder.getContext().setInLocalContext(true);
            FqName classNameBeforeLocalContext = psiRawFirBuilder.getContext().getClassNameBeforeLocalContext();
            if (!inLocalContext) {
                psiRawFirBuilder.getContext().setClassNameBeforeLocalContext(psiRawFirBuilder.getContext().getClassName());
            }
            FqName className = psiRawFirBuilder.getContext().getClassName();
            psiRawFirBuilder.getContext().setClassName(FqName.ROOT);
            try {
                KtAnnotationUseSiteTarget useSiteTarget = annotationEntry.getUseSiteTarget();
                KtPsiSourceElement firSourceElement$default = null;
                AnnotationUseSiteTarget annotationUseSiteTarget = useSiteTarget != null ? useSiteTarget.getAnnotationUseSiteTarget() : null;
                if (annotationUseSiteTarget == AnnotationUseSiteTarget.ALL && (annotationEntry.getParent() instanceof KtAnnotation)) {
                    FirErrorAnnotationCallBuilder firErrorAnnotationCallBuilder = new FirErrorAnnotationCallBuilder();
                    firErrorAnnotationCallBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, annotationEntry, null, 1, null));
                    firErrorAnnotationCallBuilder.setUseSiteTarget(annotationUseSiteTarget);
                    firErrorAnnotationCallBuilder.setAnnotationTypeRef(toFirOrErrorType(annotationEntry.getTypeReference()));
                    extractArgumentsTo(annotationEntry, firErrorAnnotationCallBuilder);
                    FirUserTypeRef annotationTypeRef = firErrorAnnotationCallBuilder.getAnnotationTypeRef();
                    FirUserTypeRef firUserTypeRef = annotationTypeRef instanceof FirUserTypeRef ? annotationTypeRef : null;
                    if (firUserTypeRef == null || (qualifier2 = firUserTypeRef.getQualifier()) == null || (firQualifierPart2 = (FirQualifierPart) CollectionsKt.last(qualifier2)) == null || (nameSpecial2 = firQualifierPart2.getName()) == null) {
                        nameSpecial2 = Name.special("<no-annotation-name>");
                        nameSpecial2.getClass();
                    }
                    FirSimpleNamedReferenceBuilder firSimpleNamedReferenceBuilder = new FirSimpleNamedReferenceBuilder();
                    KtTypeReference typeReference = annotationEntry.getTypeReference();
                    KtTypeElement typeElement = typeReference != null ? typeReference.getTypeElement() : null;
                    KtUserType ktUserType = typeElement instanceof KtUserType ? (KtUserType) typeElement : null;
                    if (ktUserType != null && (referenceExpression2 = ktUserType.getReferenceExpression()) != null) {
                        firSourceElement$default = AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, referenceExpression2, null, 1, null);
                    }
                    firSimpleNamedReferenceBuilder.setSource(firSourceElement$default);
                    firSimpleNamedReferenceBuilder.setName(nameSpecial2);
                    firErrorAnnotationCallBuilder.setCalleeReference(firSimpleNamedReferenceBuilder.build());
                    List<FirTypeProjection> typeArguments = firErrorAnnotationCallBuilder.getTypeArguments();
                    List<? extends KtTypeProjection> typeArguments2 = annotationEntry.getTypeArguments();
                    typeArguments2.getClass();
                    appendTypeArguments(typeArguments, typeArguments2);
                    firErrorAnnotationCallBuilder.setContainingDeclarationSymbol(psiRawFirBuilder.getContext().getContainerSymbol());
                    firErrorAnnotationCallBuilder.setDiagnostic(new ConeSimpleDiagnostic("Multiple annotation syntax with @all use-site target is forbidden", DiagnosticKind.MultipleAnnotationWithAllTarget));
                    firAnnotationCallMo288build = firErrorAnnotationCallBuilder.mo288build();
                } else {
                    FirAnnotationCallBuilder firAnnotationCallBuilder = new FirAnnotationCallBuilder();
                    firAnnotationCallBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, annotationEntry, null, 1, null));
                    firAnnotationCallBuilder.setUseSiteTarget(annotationUseSiteTarget);
                    firAnnotationCallBuilder.setAnnotationTypeRef(toFirOrErrorType(annotationEntry.getTypeReference()));
                    extractArgumentsTo(annotationEntry, firAnnotationCallBuilder);
                    FirUserTypeRef annotationTypeRef2 = firAnnotationCallBuilder.getAnnotationTypeRef();
                    FirUserTypeRef firUserTypeRef2 = annotationTypeRef2 instanceof FirUserTypeRef ? annotationTypeRef2 : null;
                    if (firUserTypeRef2 == null || (qualifier = firUserTypeRef2.getQualifier()) == null || (firQualifierPart = (FirQualifierPart) CollectionsKt.last(qualifier)) == null || (nameSpecial = firQualifierPart.getName()) == null) {
                        nameSpecial = Name.special("<no-annotation-name>");
                        nameSpecial.getClass();
                    }
                    FirSimpleNamedReferenceBuilder firSimpleNamedReferenceBuilder2 = new FirSimpleNamedReferenceBuilder();
                    KtTypeReference typeReference2 = annotationEntry.getTypeReference();
                    KtTypeElement typeElement2 = typeReference2 != null ? typeReference2.getTypeElement() : null;
                    KtUserType ktUserType2 = typeElement2 instanceof KtUserType ? (KtUserType) typeElement2 : null;
                    if (ktUserType2 != null && (referenceExpression = ktUserType2.getReferenceExpression()) != null) {
                        firSourceElement$default = AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, referenceExpression, null, 1, null);
                    }
                    firSimpleNamedReferenceBuilder2.setSource(firSourceElement$default);
                    firSimpleNamedReferenceBuilder2.setName(nameSpecial);
                    firAnnotationCallBuilder.setCalleeReference(firSimpleNamedReferenceBuilder2.build());
                    List<FirTypeProjection> typeArguments3 = firAnnotationCallBuilder.getTypeArguments();
                    List<? extends KtTypeProjection> typeArguments4 = annotationEntry.getTypeArguments();
                    typeArguments4.getClass();
                    appendTypeArguments(typeArguments3, typeArguments4);
                    firAnnotationCallBuilder.setContainingDeclarationSymbol(psiRawFirBuilder.getContext().getContainerSymbol());
                    firAnnotationCallMo288build = firAnnotationCallBuilder.mo288build();
                }
                return firAnnotationCallMo288build;
            } finally {
                psiRawFirBuilder.getContext().setClassNameBeforeLocalContext(classNameBeforeLocalContext);
                psiRawFirBuilder.getContext().setInLocalContext(inLocalContext);
                psiRawFirBuilder.getContext().setClassName(className);
                psiRawFirBuilder.getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
            }
        }

        public FirElement visitArrayAccessExpression(KtArrayAccessExpression expression, FirElement data) {
            expression.getClass();
            KtExpression arrayExpression = expression.getArrayExpression();
            FirExpression firExpressionRemove = PsiRawFirBuilder.this.getContext().getArraySetArgument().remove(expression);
            PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
            FirFunctionCallBuilder firFunctionCallBuilder = new FirFunctionCallBuilder();
            boolean z = firExpressionRemove == null;
            KtArrayAccessExpression parent = z ? expression : expression.getParent();
            parent.getClass();
            firFunctionCallBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, parent, null, 1, null));
            FirSimpleNamedReferenceBuilder firSimpleNamedReferenceBuilder = new FirSimpleNamedReferenceBuilder();
            firSimpleNamedReferenceBuilder.setSource(KtSourceElementKt.fakeElement$default(AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, expression, null, 1, null), KtFakeSourceElementKind.ArrayAccessNameReference.INSTANCE, null, 2, null));
            firSimpleNamedReferenceBuilder.setName(z ? OperatorNameConventions.GET : OperatorNameConventions.SET);
            firFunctionCallBuilder.setCalleeReference(firSimpleNamedReferenceBuilder.build());
            firFunctionCallBuilder.setExplicitReceiver(toFirExpression(arrayExpression, "No array expression", expression));
            FirArgumentListBuilder firArgumentListBuilder = new FirArgumentListBuilder();
            for (KtExpression ktExpression : expression.getIndexExpressions()) {
                List<FirExpression> arguments = firArgumentListBuilder.getArguments();
                ktExpression.getClass();
                arguments.add(toFirExpression(ktExpression, "Incorrect index expression"));
            }
            if (firExpressionRemove != null) {
                firArgumentListBuilder.getArguments().add(firExpressionRemove);
            }
            firFunctionCallBuilder.setArgumentList(firArgumentListBuilder.build());
            firFunctionCallBuilder.setOrigin(FirFunctionCallOrigin.Operator);
            return ConversionUtilsKt.pullUpSafeCallIfNecessary(firFunctionCallBuilder.mo288build());
        }

        public FirElement visitBinaryExpression(KtBinaryExpression expression, FirElement data) {
            expression.getClass();
            List listTryFlattenStringConcatenationArguments = PsiUtilsKt.tryFlattenStringConcatenationArguments(expression);
            if (listTryFlattenStringConcatenationArguments == null) {
                return visitBinaryExpressionFallback(expression);
            }
            PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
            FirStringConcatenationCallBuilder firStringConcatenationCallBuilder = new FirStringConcatenationCallBuilder();
            KtSourceElement ktSourceElement = (KtPsiSourceElement) AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, expression, null, 1, null);
            FirArgumentListBuilder firArgumentListBuilder = new FirArgumentListBuilder();
            List<FirExpression> arguments = firArgumentListBuilder.getArguments();
            Iterator it = listTryFlattenStringConcatenationArguments.iterator();
            while (it.hasNext()) {
                FirElement firElementConvertElement = convertElement((KtStringTemplateExpression) it.next(), null);
                if (firElementConvertElement == null) {
                    x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.expressions.FirExpression");
                    return null;
                }
                arguments.add((FirExpression) firElementConvertElement);
            }
            firArgumentListBuilder.setSource(ktSourceElement);
            firStringConcatenationCallBuilder.setArgumentList(firArgumentListBuilder.build());
            firStringConcatenationCallBuilder.setSource(ktSourceElement);
            firStringConcatenationCallBuilder.setInterpolationPrefix(Argument.Delimiters.none);
            firStringConcatenationCallBuilder.setFoldedStrings(true);
            return firStringConcatenationCallBuilder.mo288build();
        }

        public FirElement visitBinaryWithTypeRHSExpression(KtBinaryExpressionWithTypeRHS expression, FirElement data) {
            expression.getClass();
            PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
            FirTypeOperatorCallBuilder firTypeOperatorCallBuilder = new FirTypeOperatorCallBuilder();
            firTypeOperatorCallBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, expression, null, 1, null));
            firTypeOperatorCallBuilder.setOperation(ConversionUtilsKt.toFirOperation(expression.getOperationReference().getReferencedNameElementType()));
            firTypeOperatorCallBuilder.setConversionTypeRef(toFirOrErrorType(expression.getRight()));
            KtExpression left = expression.getLeft();
            left.getClass();
            firTypeOperatorCallBuilder.setArgumentList(FirArgumentUtilKt.buildUnaryArgumentList(toFirExpression(left, "No left operand")));
            return firTypeOperatorCallBuilder.mo288build();
        }

        public FirElement visitBlockExpression(KtBlockExpression expression, FirElement data) {
            expression.getClass();
            return configureBlockWithoutBuilding$default(this, expression, null, 2, null).mo288build();
        }

        public FirElement visitBreakExpression(KtBreakExpression expression, FirElement data) {
            expression.getClass();
            PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
            FirBreakExpressionBuilder firBreakExpressionBuilder = new FirBreakExpressionBuilder();
            firBreakExpressionBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(PsiRawFirBuilder.this, expression, null, 1, null));
            return psiRawFirBuilder.bindLabel(firBreakExpressionBuilder, expression).build();
        }

        public FirElement visitCallExpression(KtCallExpression expression, FirElement data) {
            FirQualifiedAccessExpressionBuilder firQualifiedAccessExpressionBuilder;
            String text;
            Object firExpression;
            expression.getClass();
            KtCallExpression ktCallExpression = PsiRawFirBuilder.this.getImitateLambdaSuspendModifier() ? expression : null;
            KtExpression calleeExpression = ktCallExpression != null ? ktCallExpression.getCalleeExpression() : null;
            if (calleeExpression instanceof KtNameReferenceExpression) {
                KtNameReferenceExpression ktNameReferenceExpression = (KtNameReferenceExpression) calleeExpression;
                if (Intrinsics.areEqual(ktNameReferenceExpression.getReferencedName(), StandardClassIds.Callables.INSTANCE.getSuspend().getCallableName().getIdentifier())) {
                    PsiElement parent = expression.getParent();
                    PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
                    if ((!Intrinsics.areEqual(psiRawFirBuilder.getSelectorExpression(parent), expression) || psiRawFirBuilder.getReceiverExpression(parent) == null) && expression.getValueArgumentList() == null && expression.getLambdaArguments().size() == 1 && expression.getTypeArgumentList() == null && (text = ktNameReferenceExpression.getReferencedNameElement().getText()) != null && !StringsKt.startsWith$default(text, '`', false, 2, (Object) null)) {
                        ValueArgument valueArgument = (KtLambdaArgument) CollectionsKt.single(expression.getLambdaArguments());
                        PsiRawFirBuilder$Visitor$buildOrLazyExpression$1 psiRawFirBuilder$Visitor$buildOrLazyExpression$1 = new PsiRawFirBuilder$Visitor$buildOrLazyExpression$1(AbstractRawFirBuilder.toFirSourceElement$default(PsiRawFirBuilder.this, valueArgument, null, 1, null));
                        int i = WhenMappings.$EnumSwitchMapping$0[PsiRawFirBuilder.this.getMode().ordinal()];
                        if (i == 1) {
                            firExpression = toFirExpression(valueArgument);
                            if (!(firExpression instanceof FirAnonymousFunctionExpression)) {
                                w01.a("Failed requirement.");
                                return null;
                            }
                            FirAnonymousFunctionExpression firAnonymousFunctionExpression = (FirAnonymousFunctionExpression) firExpression;
                            FirAnonymousFunction anonymousFunction = firAnonymousFunctionExpression.getAnonymousFunction();
                            FirDeclarationStatus status = firAnonymousFunctionExpression.getAnonymousFunction().getStatus();
                            anonymousFunction.replaceStatus(UtilsKt.copy(status, (8388575 & 1) != 0 ? status.getVisibility() : null, (8388575 & 2) != 0 ? status.getModality() : null, (8388575 & 4) != 0 ? status.isExpect() : false, (8388575 & 8) != 0 ? status.isActual() : false, (8388575 & 16) != 0 ? status.isOverride() : false, (8388575 & 32) != 0 ? status.isOperator() : false, (8388575 & 64) != 0 ? status.isInfix() : false, (8388575 & 128) != 0 ? status.isInline() : false, (8388575 & 256) != 0 ? status.isValue() : false, (8388575 & 512) != 0 ? status.isTailRec() : false, (8388575 & 1024) != 0 ? status.isExternal() : false, (8388575 & 2048) != 0 ? status.isConst() : false, (8388575 & 4096) != 0 ? status.isLateInit() : false, (8388575 & 8192) != 0 ? status.isInner() : false, (8388575 & 16384) != 0 ? status.isCompanion() : false, (8388575 & 32768) != 0 ? status.isData() : false, (8388575 & 65536) != 0 ? status.isSuspend() : true, (8388575 & 131072) != 0 ? status.isStatic() : false, (8388575 & 262144) != 0 ? status.isFromSealedClass() : false, (8388575 & 524288) != 0 ? status.isFromEnumClass() : false, (8388575 & 1048576) != 0 ? status.isFun() : false, (8388575 & 2097152) != 0 ? status.getHasStableParameterNames() : false, (8388575 & 4194304) != 0 ? status.getReturnValueStatus() : null));
                        } else {
                            if (i != 2) {
                                bu8.a();
                                return null;
                            }
                            int i2 = WhenMappings.$EnumSwitchMapping$0[PsiRawFirBuilder.this.getMode().ordinal()];
                            if (i2 == 1) {
                                firExpression = psiRawFirBuilder$Visitor$buildOrLazyExpression$1.invoke();
                            } else {
                                if (i2 != 2) {
                                    bu8.a();
                                    return null;
                                }
                                firExpression = AstLoadingFilter.disallowTreeLoading(new PsiRawFirBuilder$runOnStubs$1(psiRawFirBuilder$Visitor$buildOrLazyExpression$1));
                            }
                        }
                        return (FirExpression) firExpression;
                    }
                }
            }
            KtPsiSourceElement firSourceElement$default = AbstractRawFirBuilder.toFirSourceElement$default(PsiRawFirBuilder.this, expression, null, 1, null);
            CalleeAndReceiver calleeAndReceiverSplitToCalleeAndReceiver = splitToCalleeAndReceiver(expression.getCalleeExpression(), firSourceElement$default);
            FirNamedReference reference = calleeAndReceiverSplitToCalleeAndReceiver.getReference();
            FirExpression receiverForInvoke = calleeAndReceiverSplitToCalleeAndReceiver.getReceiverForInvoke();
            if (expression.getValueArgumentList() == null && expression.getLambdaArguments().isEmpty()) {
                FirPropertyAccessExpressionBuilder firPropertyAccessExpressionBuilder = new FirPropertyAccessExpressionBuilder();
                firPropertyAccessExpressionBuilder.setSource(firSourceElement$default);
                firPropertyAccessExpressionBuilder.setCalleeReference(reference);
                firQualifiedAccessExpressionBuilder = firPropertyAccessExpressionBuilder;
            } else {
                FirAbstractFunctionCallBuilder firImplicitInvokeCallBuilder = receiverForInvoke != null ? new FirImplicitInvokeCallBuilder() : new FirFunctionCallBuilder();
                PsiRawFirBuilder psiRawFirBuilder2 = PsiRawFirBuilder.this;
                firImplicitInvokeCallBuilder.setSource(firSourceElement$default);
                firImplicitInvokeCallBuilder.setCalleeReference(reference);
                psiRawFirBuilder2.getContext().getCalleeNamesForLambda().add(reference.getName());
                extractArgumentsTo(expression, firImplicitInvokeCallBuilder);
                psiRawFirBuilder2.removeLast(psiRawFirBuilder2.getContext().getCalleeNamesForLambda());
                firQualifiedAccessExpressionBuilder = firImplicitInvokeCallBuilder;
            }
            firQualifiedAccessExpressionBuilder.setExplicitReceiver(receiverForInvoke);
            appendTypeArguments(firQualifiedAccessExpressionBuilder.getTypeArguments(), expression.getTypeArguments());
            return ConversionUtilsKt.pullUpSafeCallIfNecessary(firQualifiedAccessExpressionBuilder.mo373build());
        }

        public FirElement visitCallableReferenceExpression(KtCallableReferenceExpression expression, FirElement data) {
            Object firExpression;
            expression.getClass();
            PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
            FirCallableReferenceAccessBuilder firCallableReferenceAccessBuilder = new FirCallableReferenceAccessBuilder();
            firCallableReferenceAccessBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, expression, null, 1, null));
            FirSimpleNamedReferenceBuilder firSimpleNamedReferenceBuilder = new FirSimpleNamedReferenceBuilder();
            KtSimpleNameExpression callableReference = expression.getCallableReference();
            callableReference.getClass();
            firSimpleNamedReferenceBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, callableReference, null, 1, null));
            firSimpleNamedReferenceBuilder.setName(expression.getCallableReference().getReferencedNameAsName());
            firCallableReferenceAccessBuilder.setCalleeReference(firSimpleNamedReferenceBuilder.build());
            KtExpression receiverExpression = expression.getReceiverExpression();
            firCallableReferenceAccessBuilder.setExplicitReceiver(receiverExpression != null ? toFirExpression(receiverExpression, "Incorrect receiver expression") : null);
            firCallableReferenceAccessBuilder.setHasQuestionMarkAtLHS(expression.getHasQuestionMarks());
            KtValueArgumentList errorValueArgumentList = getErrorValueArgumentList(expression);
            if (errorValueArgumentList != null) {
                FirArgumentListBuilder firArgumentListBuilder = new FirArgumentListBuilder();
                firArgumentListBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, errorValueArgumentList, null, 1, null));
                for (KtValueArgument ktValueArgument : errorValueArgumentList.getArguments()) {
                    List<FirExpression> arguments = firArgumentListBuilder.getArguments();
                    ktValueArgument.getClass();
                    PsiRawFirBuilder$Visitor$buildOrLazyExpression$1 psiRawFirBuilder$Visitor$buildOrLazyExpression$1 = new PsiRawFirBuilder$Visitor$buildOrLazyExpression$1(AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, ktValueArgument, null, 1, null));
                    int i = WhenMappings.$EnumSwitchMapping$0[PsiRawFirBuilder.this.getMode().ordinal()];
                    if (i == 1) {
                        firExpression = toFirExpression(ktValueArgument);
                    } else {
                        if (i != 2) {
                            bu8.a();
                            return null;
                        }
                        int i2 = WhenMappings.$EnumSwitchMapping$0[PsiRawFirBuilder.this.getMode().ordinal()];
                        if (i2 == 1) {
                            firExpression = psiRawFirBuilder$Visitor$buildOrLazyExpression$1.invoke();
                        } else {
                            if (i2 != 2) {
                                bu8.a();
                                return null;
                            }
                            firExpression = AstLoadingFilter.disallowTreeLoading(new PsiRawFirBuilder$runOnStubs$1(psiRawFirBuilder$Visitor$buildOrLazyExpression$1));
                        }
                    }
                    arguments.add((FirExpression) firExpression);
                }
                firCallableReferenceAccessBuilder.setErrorArgumentList(firArgumentListBuilder.build());
            }
            return firCallableReferenceAccessBuilder.mo288build();
        }

        public FirElement visitClassLiteralExpression(KtClassLiteralExpression expression, FirElement data) {
            KtSourceElement firSourceElement$default;
            FirExpression firExpressionMo288build;
            KtSourceElement source;
            KtSourceElement firSourceElement;
            expression.getClass();
            PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
            FirGetClassCallBuilder firGetClassCallBuilder = new FirGetClassCallBuilder();
            firGetClassCallBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, expression, null, 1, null));
            KtExpression receiverExpression = expression.getReceiverExpression();
            if (receiverExpression == null) {
                firExpressionMo288build = FirExpressionUtilKt.buildErrorExpression$default(PsiRawFirBuilder.this.toFirSourceElement((PsiElement) expression, (KtFakeSourceElementKind) KtFakeSourceElementKind.ErrorExpression.INSTANCE), ConeUnsupportedClassLiteralsWithEmptyLhs.INSTANCE, null, 4, null);
            } else {
                FirElement firElementConvertElement = convertElement(receiverExpression, null);
                if (firElementConvertElement instanceof FirExpression) {
                    FirExpression firExpression = (FirExpression) firElementConvertElement;
                    if (UtilsKt.isStatementLikeExpression(firExpression)) {
                        PsiRawFirBuilder psiRawFirBuilder2 = PsiRawFirBuilder.this;
                        FirErrorExpressionBuilder firErrorExpressionBuilder = new FirErrorExpressionBuilder();
                        firErrorExpressionBuilder.setNonExpressionElement(firElementConvertElement);
                        firErrorExpressionBuilder.setDiagnostic(ConeUnsupportedClassLiteralsWithEmptyLhs.INSTANCE);
                        KtSourceElement source2 = firExpression.getSource();
                        if (source2 == null || (firSourceElement = KtSourceElementKt.realElement(source2)) == null) {
                            firSourceElement = psiRawFirBuilder2.toFirSourceElement((PsiElement) expression, (KtFakeSourceElementKind) KtFakeSourceElementKind.ErrorExpression.INSTANCE);
                        }
                        firErrorExpressionBuilder.setSource(firSourceElement);
                        firExpressionMo288build = firErrorExpressionBuilder.mo288build();
                    } else {
                        firExpressionMo288build = checkSelectorInvariant(receiverExpression, firExpression);
                    }
                } else {
                    PsiRawFirBuilder psiRawFirBuilder3 = PsiRawFirBuilder.this;
                    FirErrorExpressionBuilder firErrorExpressionBuilder2 = new FirErrorExpressionBuilder();
                    firErrorExpressionBuilder2.setNonExpressionElement(firElementConvertElement);
                    firErrorExpressionBuilder2.setDiagnostic(ConeUnsupportedClassLiteralsWithEmptyLhs.INSTANCE);
                    if (firElementConvertElement == null || (source = firElementConvertElement.getSource()) == null || (firSourceElement$default = KtSourceElementKt.realElement(source)) == null) {
                        firSourceElement$default = AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder3, receiverExpression, null, 1, null);
                    }
                    firErrorExpressionBuilder2.setSource(firSourceElement$default);
                    firExpressionMo288build = firErrorExpressionBuilder2.mo288build();
                }
            }
            firGetClassCallBuilder.setArgumentList(FirArgumentUtilKt.buildUnaryArgumentList(firExpressionMo288build));
            return firGetClassCallBuilder.mo288build();
        }

        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        /*  JADX ERROR: Types fix failed
            jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached with updateSeq = 27431. Try increasing type updates limit count.
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
            	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
            	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
            	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:99)
            */
        public org.jetbrains.kotlin.fir.FirElement visitClassOrObject(org.jetbrains.kotlin.psi.KtClassOrObject r32, org.jetbrains.kotlin.fir.FirElement r33) {
            /*
                Method dump skipped, instruction units count: 2743
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.kotlin.fir.builder.PsiRawFirBuilder.Visitor.visitClassOrObject(org.jetbrains.kotlin.psi.KtClassOrObject, org.jetbrains.kotlin.fir.FirElement):org.jetbrains.kotlin.fir.FirElement");
        }

        public FirElement visitCollectionLiteralExpression(KtCollectionLiteralExpression expression, FirElement data) {
            expression.getClass();
            FirArgumentListBuilder firArgumentListBuilder = new FirArgumentListBuilder();
            for (KtExpression ktExpression : expression.getInnerExpressions()) {
                List<FirExpression> arguments = firArgumentListBuilder.getArguments();
                ktExpression.getClass();
                arguments.add(toFirExpression(ktExpression, "Incorrect collection literal argument"));
            }
            FirArgumentList firArgumentListBuild = firArgumentListBuilder.build();
            PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
            FirCollectionLiteralBuilder firCollectionLiteralBuilder = new FirCollectionLiteralBuilder();
            firCollectionLiteralBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, expression, null, 1, null));
            firCollectionLiteralBuilder.setArgumentList(firArgumentListBuild);
            return firCollectionLiteralBuilder.mo288build();
        }

        public FirElement visitConstantExpression(KtConstantExpression expression, FirElement data) {
            expression.getClass();
            return PsiRawFirBuilder.this.generateConstantExpressionByLiteral(expression);
        }

        public FirElement visitContinueExpression(KtContinueExpression expression, FirElement data) {
            expression.getClass();
            PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
            FirContinueExpressionBuilder firContinueExpressionBuilder = new FirContinueExpressionBuilder();
            firContinueExpressionBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(PsiRawFirBuilder.this, expression, null, 1, null));
            return psiRawFirBuilder.bindLabel(firContinueExpressionBuilder, expression).build();
        }

        public FirElement visitDestructuringDeclaration(KtDestructuringDeclaration multiDeclaration, FirElement data) {
            multiDeclaration.getClass();
            FirVariable firVariableGenerateTemporaryVariable$default = PsiConversionUtilsKt.generateTemporaryVariable$default(PsiRawFirBuilder.this.getBaseModuleData(), AbstractRawFirBuilder.toFirSourceElement$default(PsiRawFirBuilder.this, multiDeclaration, null, 1, null), "destruct", toFirExpression(multiDeclaration.getInitializer(), "Initializer required for destructuring declaration", multiDeclaration), null, new Function2() { // from class: tqb
                public final Object invoke(Object obj, Object obj2) {
                    return PsiRawFirBuilder.Visitor.x(this.b, (KtAnnotated) obj, (FirAnnotationContainerBuilder) obj2);
                }
            }, 16, null);
            PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
            return PsiConversionUtilsKt.generateDestructuringBlock(this, psiRawFirBuilder, psiRawFirBuilder.getBaseModuleData(), multiDeclaration, firVariableGenerateTemporaryVariable$default, true);
        }

        public FirElement visitDoWhileExpression(final KtDoWhileExpression expression, FirElement data) {
            expression.getClass();
            PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
            FirDoWhileLoopBuilder firDoWhileLoopBuilder = new FirDoWhileLoopBuilder();
            PsiRawFirBuilder psiRawFirBuilder2 = PsiRawFirBuilder.this;
            firDoWhileLoopBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder2, expression, null, 1, null));
            FirLoopTarget firLoopTargetPrepareTarget = psiRawFirBuilder2.prepareTarget(firDoWhileLoopBuilder, expression);
            KtExpression condition = expression.getCondition();
            IElementType iElementType = KtNodeTypes.CONDITION;
            iElementType.getClass();
            PsiElement childNodeByType = psiRawFirBuilder2.getChildNodeByType((PsiElement) expression, iElementType);
            KtDoWhileExpression ktDoWhileExpression = childNodeByType instanceof KtElement ? (KtElement) childNodeByType : null;
            if (ktDoWhileExpression == null) {
                ktDoWhileExpression = expression;
            }
            firDoWhileLoopBuilder.setCondition(toFirExpression(condition, "No condition in do-while loop", ktDoWhileExpression));
            return psiRawFirBuilder.configure(firDoWhileLoopBuilder, firLoopTargetPrepareTarget, new Function0() { // from class: crb
                public final Object invoke() {
                    return PsiRawFirBuilder.Visitor.d(this.b, expression);
                }
            });
        }

        public FirElement visitExpression(KtExpression expression, FirElement data) {
            expression.getClass();
            PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
            FirExpressionStubBuilder firExpressionStubBuilder = new FirExpressionStubBuilder();
            firExpressionStubBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, expression, null, 1, null));
            return firExpressionStubBuilder.mo288build();
        }

        public FirElement visitForExpression(final KtForExpression expression, FirElement data) {
            KtSourceElement firSourceElement;
            expression.getClass();
            KtExpression loopRange = expression.getLoopRange();
            PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
            IElementType iElementType = KtNodeTypes.LOOP_RANGE;
            iElementType.getClass();
            PsiElement childNodeByType = psiRawFirBuilder.getChildNodeByType((PsiElement) expression, iElementType);
            KtForExpression ktForExpression = childNodeByType instanceof KtElement ? (KtElement) childNodeByType : null;
            if (ktForExpression == null) {
                ktForExpression = expression;
            }
            FirExpression firExpression = toFirExpression(loopRange, "No range in for loop", ktForExpression);
            final KtParameter loopParameter = expression.getLoopParameter();
            KtFakeSourceElementKind ktFakeSourceElementKind = KtFakeSourceElementKind.DesugaredForLoop.INSTANCE;
            if (ktFakeSourceElementKind == null) {
                bu8.a();
                return null;
            }
            KtSourceElement ktFakePsiSourceElement = new KtFakePsiSourceElement(expression, ktFakeSourceElementKind);
            PsiElement loopRange2 = expression.getLoopRange();
            KtSourceElement ktSourceElement = (loopRange2 == null || (firSourceElement = PsiRawFirBuilder.this.toFirSourceElement(loopRange2, ktFakeSourceElementKind)) == null) ? ktFakePsiSourceElement : firSourceElement;
            final PsiRawFirBuilder psiRawFirBuilder2 = PsiRawFirBuilder.this;
            FirBlockBuilder firBlockBuilder = new FirBlockBuilder();
            firBlockBuilder.setSource(ktFakePsiSourceElement);
            FirModuleData baseModuleData = psiRawFirBuilder2.getBaseModuleData();
            Name name = SpecialNames.ITERATOR;
            FirFunctionCallBuilder firFunctionCallBuilder = new FirFunctionCallBuilder();
            firFunctionCallBuilder.setSource(ktSourceElement);
            FirSimpleNamedReferenceBuilder firSimpleNamedReferenceBuilder = new FirSimpleNamedReferenceBuilder();
            firSimpleNamedReferenceBuilder.setSource(ktSourceElement);
            firSimpleNamedReferenceBuilder.setName(OperatorNameConventions.ITERATOR);
            firFunctionCallBuilder.setCalleeReference(firSimpleNamedReferenceBuilder.build());
            firFunctionCallBuilder.setExplicitReceiver(firExpression);
            FirFunctionCallOrigin firFunctionCallOrigin = FirFunctionCallOrigin.Operator;
            firFunctionCallBuilder.setOrigin(firFunctionCallOrigin);
            Unit unit = Unit.INSTANCE;
            final FirProperty firPropertyGenerateTemporaryVariable$default = FirGenerationKt.generateTemporaryVariable$default(baseModuleData, ktSourceElement, name, firFunctionCallBuilder.mo288build(), null, null, null, 112, null);
            firBlockBuilder.getStatements().add(firPropertyGenerateTemporaryVariable$default);
            List<FirStatement> statements = firBlockBuilder.getStatements();
            FirWhileLoopBuilder firWhileLoopBuilder = new FirWhileLoopBuilder();
            firWhileLoopBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder2, expression, null, 1, null));
            FirFunctionCallBuilder firFunctionCallBuilder2 = new FirFunctionCallBuilder();
            firFunctionCallBuilder2.setSource(ktSourceElement);
            FirSimpleNamedReferenceBuilder firSimpleNamedReferenceBuilder2 = new FirSimpleNamedReferenceBuilder();
            firSimpleNamedReferenceBuilder2.setSource(ktSourceElement);
            firSimpleNamedReferenceBuilder2.setName(OperatorNameConventions.HAS_NEXT);
            firFunctionCallBuilder2.setCalleeReference(firSimpleNamedReferenceBuilder2.build());
            firFunctionCallBuilder2.setExplicitReceiver(ConversionUtilsKt.generateResolvedAccessExpression(ktSourceElement, firPropertyGenerateTemporaryVariable$default));
            firFunctionCallBuilder2.setOrigin(firFunctionCallOrigin);
            firWhileLoopBuilder.setCondition(firFunctionCallBuilder2.mo288build());
            final KtSourceElement ktSourceElement2 = ktSourceElement;
            statements.add(psiRawFirBuilder2.configure(firWhileLoopBuilder, psiRawFirBuilder2.prepareTarget(firWhileLoopBuilder, expression), new Function0() { // from class: wqb
                public final Object invoke() {
                    return PsiRawFirBuilder.Visitor.visitForExpression$lambda$0$2(loopParameter, psiRawFirBuilder2, this, expression, ktSourceElement2, firPropertyGenerateTemporaryVariable$default);
                }
            }));
            return firBlockBuilder.mo288build();
        }

        public FirElement visitIfExpression(KtIfExpression expression, FirElement data) {
            expression.getClass();
            PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
            FirWhenExpressionBuilder firWhenExpressionBuilder = new FirWhenExpressionBuilder();
            KtRealPsiSourceElement ktRealPsiSourceElement = null;
            firWhenExpressionBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, expression, null, 1, null));
            KtExpression condition = expression.getCondition();
            List<FirWhenBranch> branches = firWhenExpressionBuilder.getBranches();
            FirRegularWhenBranchBuilder firRegularWhenBranchBuilder = new FirRegularWhenBranchBuilder();
            firRegularWhenBranchBuilder.setSource(condition != null ? psiRawFirBuilder.toFirSourceElement((PsiElement) condition, (KtFakeSourceElementKind) KtFakeSourceElementKind.WhenCondition.INSTANCE) : null);
            firRegularWhenBranchBuilder.setCondition(toFirExpression(condition, "If statement should have condition", expression));
            firRegularWhenBranchBuilder.setResult(toFirBlock(expression.getThen()));
            branches.add(firRegularWhenBranchBuilder.build());
            if (expression.getElse() != null) {
                List<FirWhenBranch> branches2 = firWhenExpressionBuilder.getBranches();
                FirRegularWhenBranchBuilder firRegularWhenBranchBuilder2 = new FirRegularWhenBranchBuilder();
                PsiElement elseKeyword = expression.getElseKeyword();
                if (elseKeyword != null) {
                    if (KtRealSourceElementKind.INSTANCE == null) {
                        bu8.a();
                        return null;
                    }
                    ktRealPsiSourceElement = new KtRealPsiSourceElement(elseKeyword);
                }
                firRegularWhenBranchBuilder2.setSource(ktRealPsiSourceElement);
                FirElseIfTrueConditionBuilder firElseIfTrueConditionBuilder = new FirElseIfTrueConditionBuilder();
                Unit unit = Unit.INSTANCE;
                firRegularWhenBranchBuilder2.setCondition(firElseIfTrueConditionBuilder.mo288build());
                firRegularWhenBranchBuilder2.setResult(toFirBlock(expression.getElse()));
                branches2.add(firRegularWhenBranchBuilder2.build());
            }
            firWhenExpressionBuilder.setUsedAsExpression(getUsedAsExpression(expression));
            return firWhenExpressionBuilder.mo288build();
        }

        public FirElement visitIsExpression(KtIsExpression expression, FirElement data) {
            expression.getClass();
            PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
            FirTypeOperatorCallBuilder firTypeOperatorCallBuilder = new FirTypeOperatorCallBuilder();
            firTypeOperatorCallBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, expression, null, 1, null));
            firTypeOperatorCallBuilder.setOperation(expression.isNegated() ? FirOperation.NOT_IS : FirOperation.IS);
            firTypeOperatorCallBuilder.setConversionTypeRef(toFirOrErrorType(expression.getTypeReference()));
            KtExpression leftHandSide = expression.getLeftHandSide();
            leftHandSide.getClass();
            firTypeOperatorCallBuilder.setArgumentList(FirArgumentUtilKt.buildUnaryArgumentList(toFirExpression(leftHandSide, "No left operand")));
            return firTypeOperatorCallBuilder.mo288build();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
        /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
        public FirElement visitKtFile(KtFile file, FirElement data) throws KotlinIllegalStateExceptionWithAttachments, KotlinIllegalArgumentExceptionWithAttachments {
            FqName properPackageFqName;
            KtRealPsiSourceElement ktRealPsiSourceElement;
            FirDeclaration firDeclarationBuildErrorNonLocalDestructuringDeclaration;
            file.getClass();
            Context<PsiElement> context = PsiRawFirBuilder.this.getContext();
            int i = WhenMappings.$EnumSwitchMapping$0[PsiRawFirBuilder.this.getMode().ordinal()];
            if (i == 1) {
                properPackageFqName = getProperPackageFqName(file);
            } else {
                if (i != 2) {
                    bu8.a();
                    return null;
                }
                KotlinFileStub stub = file.getStub();
                if (stub == null || (properPackageFqName = stub.getPackageFqName()) == null) {
                    properPackageFqName = getProperPackageFqName(file);
                }
            }
            context.setPackageFqName(properPackageFqName);
            KtSourceFile ktPsiSourceFile = new KtPsiSourceFile(file);
            PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
            FirFileBuilder firFileBuilder = new FirFileBuilder();
            firFileBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, file, null, 1, null));
            firFileBuilder.setModuleData(psiRawFirBuilder.getBaseModuleData());
            firFileBuilder.setOrigin(FirDeclarationOrigin.Source.INSTANCE);
            String name = file.getName();
            name.getClass();
            firFileBuilder.setName(name);
            firFileBuilder.setSourceFile(ktPsiSourceFile);
            firFileBuilder.setSourceFileLinesMapping(new KtPsiSourceFileLinesMapping(file));
            FirPackageDirectiveBuilder firPackageDirectiveBuilder = new FirPackageDirectiveBuilder();
            firPackageDirectiveBuilder.setPackageFqName(psiRawFirBuilder.getContext().getPackageFqName());
            KtPackageDirective packageDirective = file.getPackageDirective();
            if (packageDirective == null) {
                ktRealPsiSourceElement = null;
            } else {
                if (KtRealSourceElementKind.INSTANCE == null) {
                    bu8.a();
                    return null;
                }
                ktRealPsiSourceElement = new KtRealPsiSourceElement(packageDirective);
            }
            firPackageDirectiveBuilder.setSource(ktRealPsiSourceElement);
            firFileBuilder.setPackageDirective(firPackageDirectiveBuilder.build());
            KtFileAnnotationList fileAnnotationList = file.getFileAnnotationList();
            if (fileAnnotationList != null) {
                FirFileSymbol symbol = firFileBuilder.getSymbol();
                psiRawFirBuilder.getContext().pushContainerSymbol(symbol);
                try {
                    for (KtAnnotationEntry ktAnnotationEntry : fileAnnotationList.getAnnotationEntries()) {
                        List<FirAnnotation> annotations = firFileBuilder.getAnnotations();
                        ktAnnotationEntry.getClass();
                        FirElement firElementConvertElement = convertElement(ktAnnotationEntry, null);
                        if (firElementConvertElement == null) {
                            throw new NullPointerException("null cannot be cast to non-null type org.jetbrains.kotlin.fir.expressions.FirAnnotation");
                        }
                        annotations.add((FirAnnotation) firElementConvertElement);
                    }
                    Unit unit = Unit.INSTANCE;
                    psiRawFirBuilder.getContext().popContainerSymbol(symbol);
                } catch (Throwable th) {
                    psiRawFirBuilder.getContext().popContainerSymbol(symbol);
                    throw th;
                }
            }
            for (KtImportDirective ktImportDirective : file.getImportDirectives()) {
                List<FirImport> imports = firFileBuilder.getImports();
                FirImportBuilder firImportBuilder = new FirImportBuilder();
                firImportBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, ktImportDirective, null, 1, null));
                firImportBuilder.setImportedFqName(ktImportDirective.getImportedFqName());
                firImportBuilder.setAllUnder(ktImportDirective.isAllUnder());
                String aliasName = ktImportDirective.getAliasName();
                firImportBuilder.setAliasName(aliasName != null ? Name.identifier(aliasName) : null);
                KtImportAlias alias = ktImportDirective.getAlias();
                firImportBuilder.setAliasSource(alias != null ? AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, alias, null, 1, null) : null);
                imports.add(firImportBuilder.build());
            }
            if (file instanceof KtCodeFragment) {
                firFileBuilder.getDeclarations().add(convertCodeFragment((KtCodeFragment) file, firFileBuilder));
            } else {
                for (KtDeclaration ktDeclaration : file.getDeclarations()) {
                    List<FirDeclaration> declarations = firFileBuilder.getDeclarations();
                    if (ktDeclaration instanceof KtScript) {
                        firDeclarationBuildErrorNonLocalDestructuringDeclaration = psiRawFirBuilder.convertScriptOrSnippets(ktDeclaration, ktPsiSourceFile, firFileBuilder);
                    } else if (ktDeclaration instanceof KtDestructuringDeclaration) {
                        firDeclarationBuildErrorNonLocalDestructuringDeclaration = psiRawFirBuilder.buildErrorNonLocalDestructuringDeclaration(AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, ktDeclaration, null, 1, null), toInitializerExpression((KtDeclarationWithInitializer) ktDeclaration));
                    } else {
                        FirElement firElementConvertElement2 = convertElement(ktDeclaration, null);
                        if (firElementConvertElement2 == null) {
                            x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.declarations.FirDeclaration");
                            return null;
                        }
                        firDeclarationBuildErrorNonLocalDestructuringDeclaration = (FirDeclaration) firElementConvertElement2;
                    }
                    declarations.add(firDeclarationBuildErrorNonLocalDestructuringDeclaration);
                }
                for (KtModifierList ktModifierList : file.getDanglingModifierLists()) {
                    firFileBuilder.getDeclarations().add(buildErrorNonLocalDeclarationForDanglingModifierList(ktModifierList));
                }
            }
            return firFileBuilder.mo288build();
        }

        public FirElement visitLabeledExpression(KtLabeledExpression expression, FirElement data) {
            FirElement firElement;
            KtSourceElement forbiddenLabelKind;
            KtSourceElement ktRealPsiSourceElement;
            expression.getClass();
            KtSimpleNameExpression targetLabel = expression.getTargetLabel();
            boolean z = expression.getBaseExpression() instanceof KtLabeledExpression;
            if (targetLabel != null) {
                ASTNode node = targetLabel.getReferencedNameElement().getNode();
                node.getClass();
                String text = node.getText();
                text.getClass();
                forbiddenLabelKind = PsiRawFirBuilder.this.getForbiddenLabelKind(text, z);
                if (KtRealSourceElementKind.INSTANCE == null) {
                    bu8.a();
                    return null;
                }
                ktRealPsiSourceElement = new KtRealPsiSourceElement(targetLabel);
                FirLabel firLabelBuildLabel = PsiRawFirBuilder.this.buildLabel(text, ktRealPsiSourceElement);
                Context<PsiElement> context = PsiRawFirBuilder.this.getContext();
                KtExpression baseExpression = expression.getBaseExpression();
                context.addNewLabel(firLabelBuildLabel);
                context.setNewLabelUserNode(baseExpression);
                try {
                    KtExpression baseExpression2 = expression.getBaseExpression();
                    firElement = baseExpression2 != null ? (FirElement) baseExpression2.accept(this, data) : null;
                } finally {
                    context.dropLastLabel();
                }
            } else {
                KtExpression baseExpression3 = expression.getBaseExpression();
                if (baseExpression3 != null) {
                    firElement = (FirElement) baseExpression3.accept(this, data);
                    forbiddenLabelKind = null;
                } else {
                    firElement = null;
                    forbiddenLabelKind = null;
                }
                ktRealPsiSourceElement = forbiddenLabelKind;
            }
            PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
            return psiRawFirBuilder.buildExpressionHandlingLabelErrors(firElement, AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, expression, null, 1, null), forbiddenLabelKind, ktRealPsiSourceElement);
        }

        public FirElement visitLambdaExpression(KtLambdaExpression expression, FirElement data) throws Throwable {
            boolean z;
            FqName fqName;
            FqName fqName2;
            FirBlock firSingleExpressionBlock;
            Ref.ObjectRef objectRef;
            FirAnonymousFunctionBuilder firAnonymousFunctionBuilder;
            ArrayList arrayList;
            List<FirValueParameter> list;
            PsiRawFirBuilder psiRawFirBuilder;
            FirValueParameter firValueParameter$default;
            expression.getClass();
            KtFunctionLiteral functionLiteral = expression.getFunctionLiteral();
            functionLiteral.getClass();
            KtFakeSourceElementKind ktFakeSourceElementKind = null;
            KtSourceElement ktSourceElement = (KtPsiSourceElement) AbstractRawFirBuilder.toFirSourceElement$default(PsiRawFirBuilder.this, functionLiteral, null, 1, null);
            Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
            PsiRawFirBuilder psiRawFirBuilder2 = PsiRawFirBuilder.this;
            FirAnonymousFunctionBuilder firAnonymousFunctionBuilder2 = new FirAnonymousFunctionBuilder();
            firAnonymousFunctionBuilder2.setSource(ktSourceElement);
            firAnonymousFunctionBuilder2.setModuleData(psiRawFirBuilder2.getBaseModuleData());
            firAnonymousFunctionBuilder2.setOrigin(FirDeclarationOrigin.Source.INSTANCE);
            firAnonymousFunctionBuilder2.setReturnTypeRef(FirImplicitTypeRefImplWithoutSource.INSTANCE);
            firAnonymousFunctionBuilder2.setSymbol(new FirAnonymousFunctionSymbol());
            firAnonymousFunctionBuilder2.setReceiverParameter(ConversionUtilsKt.asReceiverParameter(ktSourceElement, firAnonymousFunctionBuilder2.getModuleData(), firAnonymousFunctionBuilder2.getSymbol()));
            firAnonymousFunctionBuilder2.setLambda(true);
            firAnonymousFunctionBuilder2.setHasExplicitParameterList(expression.getFunctionLiteral().getArrow() != null);
            ArrayList arrayList2 = new ArrayList();
            for (KtParameter ktParameter : functionLiteral.getValueParameters()) {
                ArrayList arrayList3 = arrayList2;
                KtDestructuringDeclaration destructuringDeclaration = ktParameter.getDestructuringDeclaration();
                List<FirValueParameter> valueParameters = firAnonymousFunctionBuilder2.getValueParameters();
                if (destructuringDeclaration != null) {
                    Name name = SpecialNames.DESTRUCT;
                    FirValueParameterBuilder firValueParameterBuilder = new FirValueParameterBuilder();
                    firValueParameterBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder2, ktParameter, ktFakeSourceElementKind, 1, ktFakeSourceElementKind));
                    firValueParameterBuilder.setContainingDeclarationSymbol(firAnonymousFunctionBuilder2.getSymbol());
                    firValueParameterBuilder.setModuleData(psiRawFirBuilder2.getBaseModuleData());
                    firValueParameterBuilder.setOrigin(FirDeclarationOrigin.Source.INSTANCE);
                    firValueParameterBuilder.setReturnTypeRef(toFirOrImplicitType(ktParameter.getTypeReference()));
                    firValueParameterBuilder.setName(name);
                    firValueParameterBuilder.setSymbol(new FirValueParameterSymbol());
                    firValueParameterBuilder.setCrossinline(false);
                    firValueParameterBuilder.setNoinline(false);
                    firValueParameterBuilder.setVararg(false);
                    FirValueParameter firValueParameterMo288build = firValueParameterBuilder.mo288build();
                    firAnonymousFunctionBuilder = firAnonymousFunctionBuilder2;
                    Ref.ObjectRef objectRef3 = objectRef2;
                    PsiRawFirBuilder psiRawFirBuilder3 = psiRawFirBuilder2;
                    objectRef = objectRef3;
                    list = valueParameters;
                    PsiConversionUtilsKt.addDestructuringVariables$default(this, psiRawFirBuilder3, arrayList3, psiRawFirBuilder2.getBaseModuleData(), destructuringDeclaration, firValueParameterMo288build, false, true, null, 128, null);
                    psiRawFirBuilder = psiRawFirBuilder3;
                    arrayList = arrayList3;
                    firValueParameter$default = firValueParameterMo288build;
                } else {
                    objectRef = objectRef2;
                    firAnonymousFunctionBuilder = firAnonymousFunctionBuilder2;
                    arrayList = arrayList3;
                    list = valueParameters;
                    psiRawFirBuilder = psiRawFirBuilder2;
                    firValueParameter$default = toFirValueParameter$default(this, ktParameter, toFirOrImplicitType(ktParameter.getTypeReference()), firAnonymousFunctionBuilder.getSymbol(), AbstractRawFirBuilder.ValueParameterDeclaration.LAMBDA, null, 8, null);
                }
                list.add(firValueParameter$default);
                psiRawFirBuilder2 = psiRawFirBuilder;
                arrayList2 = arrayList;
                firAnonymousFunctionBuilder2 = firAnonymousFunctionBuilder;
                objectRef2 = objectRef;
                ktFakeSourceElementKind = null;
            }
            Ref.ObjectRef objectRef4 = objectRef2;
            PsiRawFirBuilder psiRawFirBuilder4 = psiRawFirBuilder2;
            FirAnonymousFunctionBuilder firAnonymousFunctionBuilder3 = firAnonymousFunctionBuilder2;
            ArrayList arrayList4 = arrayList2;
            KtFakeSourceElementKind ktFakeSourceElementKind2 = ktFakeSourceElementKind;
            KtPsiSourceElement firSourceElement$default = AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder4, expression, ktFakeSourceElementKind2, 1, ktFakeSourceElementKind2);
            FirLabel lastLabel = psiRawFirBuilder4.getContext().getLastLabel(expression);
            if (lastLabel == null) {
                Name name2 = (Name) CollectionsKt.lastOrNull(psiRawFirBuilder4.getContext().getCalleeNamesForLambda());
                if (name2 != null) {
                    FirLabelBuilder firLabelBuilder = new FirLabelBuilder();
                    firLabelBuilder.setSource(KtSourceElementKt.fakeElement$default(firSourceElement$default, KtFakeSourceElementKind.GeneratedLambdaLabel.INSTANCE, null, 2, null));
                    String strAsString = name2.asString();
                    strAsString.getClass();
                    firLabelBuilder.setName(strAsString);
                    lastLabel = firLabelBuilder.build();
                } else {
                    lastLabel = null;
                }
            }
            firAnonymousFunctionBuilder3.setLabel(lastLabel);
            FirLabel label = firAnonymousFunctionBuilder3.getLabel();
            FirFunctionTarget firFunctionTarget = new FirFunctionTarget(label != null ? label.getName() : null, true);
            psiRawFirBuilder4.getContext().getFirFunctionTargets().add(firFunctionTarget);
            objectRef4.element = firFunctionTarget;
            KtBlockExpression bodyExpression = functionLiteral.getBodyExpression();
            boolean forceKeepingTheBodyInHeaderMode = psiRawFirBuilder4.getContext().getForceKeepingTheBodyInHeaderMode();
            psiRawFirBuilder4.getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
            boolean inLocalContext = psiRawFirBuilder4.getContext().getInLocalContext();
            psiRawFirBuilder4.getContext().setInLocalContext(true);
            FqName classNameBeforeLocalContext = psiRawFirBuilder4.getContext().getClassNameBeforeLocalContext();
            if (!inLocalContext) {
                psiRawFirBuilder4.getContext().setClassNameBeforeLocalContext(psiRawFirBuilder4.getContext().getClassName());
            }
            FqName className = psiRawFirBuilder4.getContext().getClassName();
            psiRawFirBuilder4.getContext().setClassName(FqName.ROOT);
            if (bodyExpression == null) {
                try {
                    FirErrorExpression firErrorExpressionBuildErrorExpression$default = FirExpressionUtilKt.buildErrorExpression$default(ktSourceElement, new ConeSyntaxDiagnostic("Lambda has no body"), null, 4, null);
                    fqName = className;
                    z = inLocalContext;
                    try {
                        FirReturnExpression return$default = AbstractRawFirBuilder.toReturn$default(psiRawFirBuilder4, firErrorExpressionBuildErrorExpression$default, null, null, false, 7, null);
                        psiRawFirBuilder4 = psiRawFirBuilder4;
                        try {
                            firSingleExpressionBlock = new FirSingleExpressionBlock(return$default);
                            fqName2 = fqName;
                        } catch (Throwable th) {
                            th = th;
                            fqName2 = fqName;
                            psiRawFirBuilder4.getContext().setClassNameBeforeLocalContext(classNameBeforeLocalContext);
                            psiRawFirBuilder4.getContext().setInLocalContext(z);
                            psiRawFirBuilder4.getContext().setClassName(fqName2);
                            psiRawFirBuilder4.getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        psiRawFirBuilder4 = psiRawFirBuilder4;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    z = inLocalContext;
                    fqName = className;
                }
            } else {
                z = inLocalContext;
                fqName2 = className;
                try {
                    FirBlockBuilder firBlockBuilderConfigureBlockWithoutBuilding = configureBlockWithoutBuilding(bodyExpression, !arrayList4.isEmpty() ? KtFakeSourceElementKind.LambdaDestructuringBlock.INSTANCE : null);
                    if (firBlockBuilderConfigureBlockWithoutBuilding.getStatements().isEmpty()) {
                        List<FirStatement> statements = firBlockBuilderConfigureBlockWithoutBuilding.getStatements();
                        FirReturnExpressionBuilder firReturnExpressionBuilder = new FirReturnExpressionBuilder();
                        firReturnExpressionBuilder.setSource(KtSourceElementKt.fakeElement$default(firSourceElement$default, KtFakeSourceElementKind.ImplicitReturn.FromExpressionBody.INSTANCE, null, 2, null));
                        firReturnExpressionBuilder.setTarget((FirTarget) objectRef4.element);
                        FirUnitExpressionBuilder firUnitExpressionBuilder = new FirUnitExpressionBuilder();
                        firUnitExpressionBuilder.setSource(KtSourceElementKt.fakeElement$default(firSourceElement$default, KtFakeSourceElementKind.ImplicitUnit.ForEmptyLambda.INSTANCE, null, 2, null));
                        firReturnExpressionBuilder.setResult(firUnitExpressionBuilder.mo288build());
                        statements.add(firReturnExpressionBuilder.mo288build());
                    }
                    firSingleExpressionBlock = firBlockBuilderConfigureBlockWithoutBuilding.mo288build();
                    if (!arrayList4.isEmpty()) {
                        FirBlockBuilder firBlockBuilder = new FirBlockBuilder();
                        KtSourceElement source = firSingleExpressionBlock.getSource();
                        firBlockBuilder.setSource(source != null ? KtSourceElementKt.realElement(source) : null);
                        firBlockBuilder.getStatements().addAll(arrayList4);
                        firBlockBuilder.getStatements().add(firSingleExpressionBlock);
                        firSingleExpressionBlock = firBlockBuilder.mo288build();
                    }
                } catch (Throwable th4) {
                    th = th4;
                    psiRawFirBuilder4.getContext().setClassNameBeforeLocalContext(classNameBeforeLocalContext);
                    psiRawFirBuilder4.getContext().setInLocalContext(z);
                    psiRawFirBuilder4.getContext().setClassName(fqName2);
                    psiRawFirBuilder4.getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
                    throw th;
                }
            }
            psiRawFirBuilder4.getContext().setClassNameBeforeLocalContext(classNameBeforeLocalContext);
            psiRawFirBuilder4.getContext().setInLocalContext(z);
            psiRawFirBuilder4.getContext().setClassName(fqName2);
            psiRawFirBuilder4.getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
            firAnonymousFunctionBuilder3.setBody(firSingleExpressionBlock);
            psiRawFirBuilder4.removeLast(psiRawFirBuilder4.getContext().getFirFunctionTargets());
            FirAnonymousFunction firAnonymousFunctionMo288build = firAnonymousFunctionBuilder3.mo288build();
            PsiRawFirBuilder.this.bindFunctionTarget((FirFunctionTarget) objectRef4.element, firAnonymousFunctionMo288build);
            FirAnonymousFunctionExpressionBuilder firAnonymousFunctionExpressionBuilder = new FirAnonymousFunctionExpressionBuilder();
            if (KtRealSourceElementKind.INSTANCE == null) {
                bu8.a();
                return null;
            }
            firAnonymousFunctionExpressionBuilder.setSource(new KtRealPsiSourceElement(expression));
            firAnonymousFunctionExpressionBuilder.setAnonymousFunction(firAnonymousFunctionMo288build);
            return firAnonymousFunctionExpressionBuilder.mo288build();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r14v30, types: [org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirFunctionBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirTypeParametersOwnerBuilder] */
        /* JADX WARN: Type inference failed for: r40v0, types: [org.jetbrains.kotlin.fir.builder.PsiRawFirBuilder$Visitor] */
        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        public FirElement visitNamedFunction(KtNamedFunction function, FirElement data) throws KotlinIllegalStateExceptionWithAttachments {
            FirCallableSymbol firNamedFunctionSymbol;
            String identifier;
            Object obj;
            FirAnonymousFunctionExpression firAnonymousFunctionExpressionMo288build;
            String identifier2;
            function.getClass();
            boolean zIsAnonymous = function.isAnonymous();
            boolean zIsLocal = function.isLocal();
            if (zIsAnonymous) {
                firNamedFunctionSymbol = new FirAnonymousFunctionSymbol();
            } else {
                PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
                Name nameAsSafeName = function.getNameAsSafeName();
                nameAsSafeName.getClass();
                firNamedFunctionSymbol = new FirNamedFunctionSymbol(psiRawFirBuilder.callableIdForName(nameAsSafeName));
            }
            FirCallableSymbol firCallableSymbol = firNamedFunctionSymbol;
            boolean zIsDirectlyInsideCompanionBlock = PsiRawFirBuilder.this.isDirectlyInsideCompanionBlock();
            PsiRawFirBuilder psiRawFirBuilder2 = PsiRawFirBuilder.this;
            if (!zIsLocal) {
                psiRawFirBuilder2.getContext().pushContainerSymbol(firCallableSymbol);
            }
            try {
                KtTypeReference typeReference = function.getTypeReference();
                FirTypeRef firOrUnitType = function.hasBlockBody() ? toFirOrUnitType(typeReference) : toFirOrImplicitType(typeReference);
                final KtTypeReference receiverTypeReference = function.getReceiverTypeReference();
                Function0 function0 = receiverTypeReference != null ? new Function0() { // from class: lrb
                    public final Object invoke() {
                        return this.b.toFirType(receiverTypeReference);
                    }
                } : null;
                Ref.ObjectRef objectRef = new Ref.ObjectRef();
                if (zIsAnonymous) {
                    FirAnonymousFunctionBuilder firAnonymousFunctionBuilder = new FirAnonymousFunctionBuilder();
                    firAnonymousFunctionBuilder.setReceiverParameter(function0 != null ? ConversionUtilsKt.createReceiverParameter(psiRawFirBuilder2, function0, psiRawFirBuilder2.getBaseModuleData(), firCallableSymbol) : null);
                    firAnonymousFunctionBuilder.setSymbol((FirAnonymousFunctionSymbol) firCallableSymbol);
                    firAnonymousFunctionBuilder.setLambda(false);
                    firAnonymousFunctionBuilder.setHasExplicitParameterList(true);
                    firAnonymousFunctionBuilder.setLabel(psiRawFirBuilder2.getContext().getLastLabel(function));
                    FirLabel label = firAnonymousFunctionBuilder.getLabel();
                    if (label == null || (identifier2 = label.getName()) == null) {
                        Name name = (Name) CollectionsKt.lastOrNull(psiRawFirBuilder2.getContext().getCalleeNamesForLambda());
                        identifier2 = name != null ? name.getIdentifier() : null;
                    }
                    objectRef.element = identifier2;
                    boolean z = PsiUtilsKt.hasExpectModifier(function) || psiRawFirBuilder2.getContext().getContainerIsExpect();
                    boolean zHasActualModifier = PsiUtilsKt.hasActualModifier(function);
                    boolean zHasModifier = function.hasModifier(KtTokens.OVERRIDE_KEYWORD);
                    boolean zHasModifier2 = function.hasModifier(KtTokens.OPERATOR_KEYWORD);
                    boolean zHasModifier3 = function.hasModifier(KtTokens.INFIX_KEYWORD);
                    boolean zHasModifier4 = function.hasModifier(KtTokens.INLINE_KEYWORD);
                    boolean zHasModifier5 = function.hasModifier(KtTokens.TAILREC_KEYWORD);
                    boolean zHasModifier6 = function.hasModifier(KtTokens.EXTERNAL_KEYWORD);
                    boolean zHasModifier7 = function.hasModifier(KtTokens.SUSPEND_KEYWORD);
                    obj = firAnonymousFunctionBuilder;
                    if (z || zHasActualModifier || zHasModifier || zHasModifier2 || zHasModifier3 || zHasModifier4 || zHasModifier5 || zHasModifier6 || zHasModifier7) {
                        FirResolvedDeclarationStatus default_status_for_statusless_declarations = FirResolvedDeclarationStatusImpl.INSTANCE.getDEFAULT_STATUS_FOR_STATUSLESS_DECLARATIONS();
                        firAnonymousFunctionBuilder.setStatus(UtilsKt.copy(default_status_for_statusless_declarations, (8388575 & 1) != 0 ? default_status_for_statusless_declarations.getVisibility() : null, (8388575 & 2) != 0 ? default_status_for_statusless_declarations.getModality() : null, (8388575 & 4) != 0 ? default_status_for_statusless_declarations.isExpect() : z, (8388575 & 8) != 0 ? default_status_for_statusless_declarations.isActual() : zHasActualModifier, (8388575 & 16) != 0 ? default_status_for_statusless_declarations.isOverride() : zHasModifier, (8388575 & 32) != 0 ? default_status_for_statusless_declarations.isOperator() : zHasModifier2, (8388575 & 64) != 0 ? default_status_for_statusless_declarations.isInfix() : zHasModifier3, (8388575 & 128) != 0 ? default_status_for_statusless_declarations.isInline() : zHasModifier4, (8388575 & 256) != 0 ? default_status_for_statusless_declarations.isValue() : false, (8388575 & 512) != 0 ? default_status_for_statusless_declarations.isTailRec() : zHasModifier5, (8388575 & 1024) != 0 ? default_status_for_statusless_declarations.isExternal() : zHasModifier6, (8388575 & 2048) != 0 ? default_status_for_statusless_declarations.isConst() : false, (8388575 & 4096) != 0 ? default_status_for_statusless_declarations.isLateInit() : false, (8388575 & 8192) != 0 ? default_status_for_statusless_declarations.isInner() : false, (8388575 & 16384) != 0 ? default_status_for_statusless_declarations.isCompanion() : false, (8388575 & 32768) != 0 ? default_status_for_statusless_declarations.isData() : false, (8388575 & 65536) != 0 ? default_status_for_statusless_declarations.isSuspend() : zHasModifier7, (8388575 & 131072) != 0 ? default_status_for_statusless_declarations.isStatic() : false, (8388575 & 262144) != 0 ? default_status_for_statusless_declarations.isFromSealedClass() : false, (8388575 & 524288) != 0 ? default_status_for_statusless_declarations.isFromEnumClass() : false, (8388575 & 1048576) != 0 ? default_status_for_statusless_declarations.isFun() : false, (8388575 & 2097152) != 0 ? default_status_for_statusless_declarations.getHasStableParameterNames() : false, (8388575 & 4194304) != 0 ? default_status_for_statusless_declarations.getReturnValueStatus() : null));
                        obj = firAnonymousFunctionBuilder;
                    }
                } else {
                    FirNamedFunctionBuilder firNamedFunctionBuilder = new FirNamedFunctionBuilder();
                    firNamedFunctionBuilder.setReceiverParameter(function0 != null ? ConversionUtilsKt.createReceiverParameter(psiRawFirBuilder2, function0, psiRawFirBuilder2.getBaseModuleData(), firCallableSymbol) : null);
                    Name nameAsSafeName2 = function.getNameAsSafeName();
                    nameAsSafeName2.getClass();
                    firNamedFunctionBuilder.setName(nameAsSafeName2);
                    FirLabel lastLabel = psiRawFirBuilder2.getContext().getLastLabel(function);
                    if (lastLabel == null || (identifier = lastLabel.getName()) == null) {
                        identifier = !firNamedFunctionBuilder.getName().isSpecial() ? firNamedFunctionBuilder.getName().getIdentifier() : null;
                    }
                    objectRef.element = identifier;
                    firNamedFunctionBuilder.setSymbol((FirNamedFunctionSymbol) firCallableSymbol);
                    firNamedFunctionBuilder.setDispatchReceiverType(!zIsLocal && !zIsDirectlyInsideCompanionBlock ? psiRawFirBuilder2.currentDispatchReceiverType() : null);
                    firNamedFunctionBuilder.setLocal(psiRawFirBuilder2.getContext().getInLocalContext());
                    FirDeclarationStatusImpl firDeclarationStatusImpl = new FirDeclarationStatusImpl(zIsLocal ? Visibilities.Local.INSTANCE : PsiRawFirBuilder.getVisibility$default(psiRawFirBuilder2, function, false, 1, null), psiRawFirBuilder2.getModality(function));
                    firDeclarationStatusImpl.setExpect(PsiUtilsKt.hasExpectModifier(function) || psiRawFirBuilder2.getContext().getContainerIsExpect());
                    firDeclarationStatusImpl.setActual(PsiUtilsKt.hasActualModifier(function));
                    firDeclarationStatusImpl.setOverride(function.hasModifier(KtTokens.OVERRIDE_KEYWORD));
                    firDeclarationStatusImpl.setOperator(function.hasModifier(KtTokens.OPERATOR_KEYWORD));
                    firDeclarationStatusImpl.setInfix(function.hasModifier(KtTokens.INFIX_KEYWORD));
                    firDeclarationStatusImpl.setInline(function.hasModifier(KtTokens.INLINE_KEYWORD));
                    firDeclarationStatusImpl.setTailRec(function.hasModifier(KtTokens.TAILREC_KEYWORD));
                    firDeclarationStatusImpl.setExternal(function.hasModifier(KtTokens.EXTERNAL_KEYWORD));
                    firDeclarationStatusImpl.setSuspend(function.hasModifier(KtTokens.SUSPEND_KEYWORD));
                    firDeclarationStatusImpl.setStatic(function.hasModifier(KtTokens.COMPANION_KEYWORD) || zIsDirectlyInsideCompanionBlock);
                    firNamedFunctionBuilder.setStatus(firDeclarationStatusImpl);
                    obj = firNamedFunctionBuilder;
                }
                ?? r14 = obj;
                FirFunctionTarget firFunctionTarget = new FirFunctionTarget((String) objectRef.element, false);
                KtPsiSourceElement firSourceElement$default = AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder2, function, null, 1, null);
                r14.setSource(firSourceElement$default);
                r14.setModuleData(psiRawFirBuilder2.getBaseModuleData());
                r14.setOrigin(FirDeclarationOrigin.Source.INSTANCE);
                r14.setReturnTypeRef(firOrUnitType);
                psiRawFirBuilder2.getContext().getFirFunctionTargets().add(firFunctionTarget);
                extractAnnotationsTo(function, r14);
                extractTypeParametersTo(function, r14, firCallableSymbol);
                List<FirValueParameter> contextParameters = r14.getContextParameters();
                KtModifierList modifierList = function.getModifierList();
                List contextParameterLists = modifierList != null ? modifierList.getContextParameterLists() : null;
                if (contextParameterLists == null) {
                    contextParameterLists = CollectionsKt.emptyList();
                }
                addContextParameters(contextParameters, contextParameterLists, firCallableSymbol);
                Iterator it = function.getValueParameters().iterator();
                while (it.hasNext()) {
                    r14.getValueParameters().add(toFirValueParameter$default(this, (KtParameter) it.next(), null, firCallableSymbol, zIsAnonymous ? AbstractRawFirBuilder.ValueParameterDeclaration.LAMBDA : AbstractRawFirBuilder.ValueParameterDeclaration.FUNCTION, null, 8, null));
                    firSourceElement$default = firSourceElement$default;
                    zIsAnonymous = zIsAnonymous;
                }
                KtPsiSourceElement ktPsiSourceElement = firSourceElement$default;
                psiRawFirBuilder2.addCapturedTypeParameters(true, ktPsiSourceElement, r14.getTypeParameters());
                try {
                    FirContractDescription firContractDescriptionObtainContractDescription = obtainContractDescription(function);
                    boolean forceKeepingTheBodyInHeaderMode = psiRawFirBuilder2.getContext().getForceKeepingTheBodyInHeaderMode();
                    psiRawFirBuilder2.getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
                    boolean inLocalContext = psiRawFirBuilder2.getContext().getInLocalContext();
                    psiRawFirBuilder2.getContext().setInLocalContext(true);
                    FqName classNameBeforeLocalContext = psiRawFirBuilder2.getContext().getClassNameBeforeLocalContext();
                    if (!inLocalContext) {
                        psiRawFirBuilder2.getContext().setClassNameBeforeLocalContext(psiRawFirBuilder2.getContext().getClassName());
                    }
                    FqName className = psiRawFirBuilder2.getContext().getClassName();
                    psiRawFirBuilder2.getContext().setClassName(FqName.ROOT);
                    try {
                        Pair<FirBlock, FirContractDescription> pairBuildFirBody = buildFirBody(function);
                        psiRawFirBuilder2.getContext().setClassNameBeforeLocalContext(classNameBeforeLocalContext);
                        psiRawFirBuilder2.getContext().setInLocalContext(inLocalContext);
                        psiRawFirBuilder2.getContext().setClassName(className);
                        psiRawFirBuilder2.getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
                        FirBlock firBlock = (FirBlock) pairBuildFirBody.component1();
                        FirContractDescription firContractDescription = (FirContractDescription) pairBuildFirBody.component2();
                        r14.setBody(firBlock);
                        if (firContractDescriptionObtainContractDescription == null) {
                            firContractDescriptionObtainContractDescription = firContractDescription;
                        }
                        if (firContractDescriptionObtainContractDescription != null) {
                            if (r14 instanceof FirNamedFunctionBuilder) {
                                ((FirNamedFunctionBuilder) r14).setContractDescription(firContractDescriptionObtainContractDescription);
                            } else if (r14 instanceof FirAnonymousFunctionBuilder) {
                                ((FirAnonymousFunctionBuilder) r14).setContractDescription(firContractDescriptionObtainContractDescription);
                            }
                            Unit unit = Unit.INSTANCE;
                        }
                        psiRawFirBuilder2.getContext().popFirTypeParameters();
                        psiRawFirBuilder2.removeLast(psiRawFirBuilder2.getContext().getFirFunctionTargets());
                        FirFunction firFunctionMo288build = r14.mo288build();
                        psiRawFirBuilder2.bindFunctionTarget(firFunctionTarget, firFunctionMo288build);
                        fillDanglingConstraintsTo(function, firFunctionMo288build);
                        if (!zIsLocal && zIsDirectlyInsideCompanionBlock) {
                            psiRawFirBuilder2.initContainingClassAttr(firFunctionMo288build);
                        }
                        boolean z2 = firFunctionMo288build instanceof FirAnonymousFunction;
                        FirElement firElement = firFunctionMo288build;
                        if (z2) {
                            FirAnonymousFunctionExpressionBuilder firAnonymousFunctionExpressionBuilder = new FirAnonymousFunctionExpressionBuilder();
                            firAnonymousFunctionExpressionBuilder.setSource(ktPsiSourceElement);
                            firAnonymousFunctionExpressionBuilder.setAnonymousFunction((FirAnonymousFunction) firFunctionMo288build);
                            firAnonymousFunctionExpressionMo288build = firAnonymousFunctionExpressionBuilder.mo288build();
                        }
                        if (!zIsLocal) {
                            firElement = firAnonymousFunctionExpressionMo288build;
                            psiRawFirBuilder2.getContext().popContainerSymbol(firCallableSymbol);
                        }
                        firElement = firAnonymousFunctionExpressionMo288build;
                        return firElement;
                    } catch (Throwable th) {
                        psiRawFirBuilder2.getContext().setClassNameBeforeLocalContext(classNameBeforeLocalContext);
                        psiRawFirBuilder2.getContext().setInLocalContext(inLocalContext);
                        psiRawFirBuilder2.getContext().setClassName(className);
                        psiRawFirBuilder2.getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
                        throw th;
                    }
                } catch (Throwable th2) {
                    psiRawFirBuilder2.getContext().popFirTypeParameters();
                    throw th2;
                }
            } catch (Throwable th3) {
                if (!zIsLocal) {
                    psiRawFirBuilder2.getContext().popContainerSymbol(firCallableSymbol);
                }
                throw th3;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r18v0, types: [org.jetbrains.kotlin.fir.builder.PsiRawFirBuilder$Visitor] */
        /* JADX WARN: Type inference failed for: r20v1 */
        /* JADX WARN: Type inference failed for: r20v2, types: [org.jetbrains.kotlin.fir.builder.AbstractRawFirBuilder] */
        /* JADX WARN: Type inference failed for: r20v7 */
        public FirElement visitObjectLiteralExpression(KtObjectLiteralExpression expression, FirElement data) throws Throwable {
            ?? r20;
            PsiRawFirBuilder psiRawFirBuilder;
            List<KtModifierList> listEmptyList;
            expression.getClass();
            Name name = SpecialNames.ANONYMOUS;
            PsiRawFirBuilder psiRawFirBuilder2 = PsiRawFirBuilder.this;
            boolean forceKeepingTheBodyInHeaderMode = psiRawFirBuilder2.getContext().getForceKeepingTheBodyInHeaderMode();
            psiRawFirBuilder2.getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
            boolean inLocalContext = psiRawFirBuilder2.getContext().getInLocalContext();
            psiRawFirBuilder2.getContext().setInLocalContext(true);
            FqName classNameBeforeLocalContext = psiRawFirBuilder2.getContext().getClassNameBeforeLocalContext();
            if (!inLocalContext) {
                psiRawFirBuilder2.getContext().setClassNameBeforeLocalContext(psiRawFirBuilder2.getContext().getClassName());
            }
            FqName className = psiRawFirBuilder2.getContext().getClassName();
            psiRawFirBuilder2.getContext().setClassName(FqName.ROOT);
            try {
                try {
                    psiRawFirBuilder2.getContext().setClassName(psiRawFirBuilder2.getContext().getClassName().child(name));
                    boolean containerIsExpect = psiRawFirBuilder2.getContext().getContainerIsExpect();
                    psiRawFirBuilder2.getContext().setContainerIsExpect(containerIsExpect);
                    int size = psiRawFirBuilder2.getContext().getDispatchReceiverTypesStack().size();
                    try {
                        FirAnonymousObjectExpressionBuilder firAnonymousObjectExpressionBuilder = new FirAnonymousObjectExpressionBuilder();
                        firAnonymousObjectExpressionBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder2, expression, null, 1, null));
                        CompanionBlockCollector companionBlockCollector = new CompanionBlockCollector();
                        FirAnonymousObjectBuilder firAnonymousObjectBuilder = new FirAnonymousObjectBuilder();
                        KtObjectDeclaration objectDeclaration = expression.getObjectDeclaration();
                        objectDeclaration.getClass();
                        firAnonymousObjectBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder2, objectDeclaration, null, 1, null));
                        firAnonymousObjectBuilder.setModuleData(psiRawFirBuilder2.getBaseModuleData());
                        firAnonymousObjectBuilder.setOrigin(FirDeclarationOrigin.Source.INSTANCE);
                        ClassKind classKind = ClassKind.CLASS;
                        firAnonymousObjectBuilder.setClassKind(classKind);
                        firAnonymousObjectBuilder.setScopeProvider(psiRawFirBuilder2.getBaseScopeProvider());
                        firAnonymousObjectBuilder.setSymbol(new FirAnonymousObjectSymbol(psiRawFirBuilder2.getContext().getPackageFqName()));
                        firAnonymousObjectBuilder.setStatus(new FirDeclarationStatusImpl(Visibilities.Local.INSTANCE, Modality.FINAL));
                        psiRawFirBuilder2.getContext().appendOuterTypeParameters(false, firAnonymousObjectBuilder.getTypeParameters());
                        FirResolvedTypeRef delegatedSelfType = psiRawFirBuilder2.toDelegatedSelfType(objectDeclaration, firAnonymousObjectBuilder);
                        psiRawFirBuilder2.registerSelfType(delegatedSelfType);
                        extractAnnotationsTo(objectDeclaration, firAnonymousObjectBuilder);
                        psiRawFirBuilder = psiRawFirBuilder2;
                        try {
                            Pair<FirTypeRef, Map<Integer, FirFieldSymbol>> pairExtractSuperTypeListEntriesTo = extractSuperTypeListEntriesTo(objectDeclaration, firAnonymousObjectBuilder, delegatedSelfType, null, classKind, CollectionsKt.emptyList(), false);
                            FirTypeRef firTypeRef = (FirTypeRef) pairExtractSuperTypeListEntriesTo.component1();
                            Map map = (Map) pairExtractSuperTypeListEntriesTo.component2();
                            addDeclarations(firAnonymousObjectBuilder, objectDeclaration.getBody(), firTypeRef, delegatedSelfType, objectDeclaration, companionBlockCollector);
                            KtClassBody body = objectDeclaration.getBody();
                            if (body == null || (listEmptyList = body.getDanglingModifierLists()) == null) {
                                listEmptyList = CollectionsKt.emptyList();
                            }
                            for (KtModifierList ktModifierList : listEmptyList) {
                                List<FirDeclaration> declarations = firAnonymousObjectBuilder.getDeclarations();
                                FirDanglingModifierList firDanglingModifierListBuildErrorNonLocalDeclarationForDanglingModifierList = buildErrorNonLocalDeclarationForDanglingModifierList(ktModifierList);
                                ConeClassLikeType coneClassLikeTypeCurrentDispatchReceiverType = psiRawFirBuilder.currentDispatchReceiverType();
                                ClassMembersKt.setContainingClassAttr(firDanglingModifierListBuildErrorNonLocalDeclarationForDanglingModifierList, coneClassLikeTypeCurrentDispatchReceiverType != null ? coneClassLikeTypeCurrentDispatchReceiverType.getLookupTag() : null);
                                declarations.add(firDanglingModifierListBuildErrorNonLocalDeclarationForDanglingModifierList);
                            }
                            FirAnonymousObject firAnonymousObjectMo288build = firAnonymousObjectBuilder.mo288build();
                            DelegateFieldsMapKt.setDelegateFieldsMap(firAnonymousObjectMo288build, map);
                            CompanionBlockInfo companionBlockInfoOrNull = companionBlockCollector.toCompanionBlockInfoOrNull();
                            if (companionBlockInfoOrNull != null) {
                                ClassMembersKt.setCompanionBlocks(firAnonymousObjectMo288build, companionBlockInfoOrNull);
                            }
                            firAnonymousObjectExpressionBuilder.setAnonymousObject(firAnonymousObjectMo288build);
                            FirAnonymousObjectExpression firAnonymousObjectExpressionMo288build = firAnonymousObjectExpressionBuilder.mo288build();
                            if (psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().size() > size + 1) {
                                throw new IllegalArgumentException(("Wrong number of " + psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().size()).toString());
                            }
                            if (psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().size() > size) {
                                psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().remove(CollectionsKt.getLastIndex(psiRawFirBuilder.getContext().getDispatchReceiverTypesStack()));
                            }
                            psiRawFirBuilder.getContext().setClassName(psiRawFirBuilder.getContext().getClassName().parent());
                            psiRawFirBuilder.getContext().setContainerIsExpect(containerIsExpect);
                            psiRawFirBuilder.getContext().setClassNameBeforeLocalContext(classNameBeforeLocalContext);
                            psiRawFirBuilder.getContext().setInLocalContext(inLocalContext);
                            psiRawFirBuilder.getContext().setClassName(className);
                            psiRawFirBuilder.getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
                            return firAnonymousObjectExpressionMo288build;
                        } catch (Throwable th) {
                            th = th;
                            if (psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().size() > size + 1) {
                                throw new IllegalArgumentException(("Wrong number of " + psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().size()).toString());
                            }
                            if (psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().size() > size) {
                                psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().remove(CollectionsKt.getLastIndex(psiRawFirBuilder.getContext().getDispatchReceiverTypesStack()));
                            }
                            psiRawFirBuilder.getContext().setClassName(psiRawFirBuilder.getContext().getClassName().parent());
                            psiRawFirBuilder.getContext().setContainerIsExpect(containerIsExpect);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        psiRawFirBuilder = psiRawFirBuilder2;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    r20 = data;
                    r20.getContext().setClassNameBeforeLocalContext(classNameBeforeLocalContext);
                    r20.getContext().setInLocalContext(inLocalContext);
                    r20.getContext().setClassName(className);
                    r20.getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                r20 = psiRawFirBuilder2;
                r20.getContext().setClassNameBeforeLocalContext(classNameBeforeLocalContext);
                r20.getContext().setInLocalContext(inLocalContext);
                r20.getContext().setClassName(className);
                r20.getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
                throw th;
            }
        }

        public FirElement visitParenthesizedExpression(KtParenthesizedExpression expression, FirElement data) {
            expression.getClass();
            PsiRawFirBuilder.this.getContext().forwardLabelUsagePermission(expression, expression.getExpression());
            return toFirExpression(expression.getExpression(), "Empty parentheses", expression);
        }

        public FirElement visitProperty(KtProperty property, FirElement data) {
            property.getClass();
            return toFirProperty(property, null, PsiRawFirBuilder.this.getContext());
        }

        public FirElement visitQualifiedExpression(KtQualifiedExpression expression, FirElement data) {
            expression.getClass();
            FirExpression firExpression = toFirExpression(expression.getReceiverExpression(), "Incorrect receiver expression");
            KtExpression selectorExpression = expression.getSelectorExpression();
            if (selectorExpression == null) {
                PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
                FirErrorExpressionBuilder firErrorExpressionBuilder = new FirErrorExpressionBuilder();
                firErrorExpressionBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, expression, null, 1, null));
                firErrorExpressionBuilder.setDiagnostic(new ConeSyntaxDiagnostic("Qualified expression without selector"));
                firErrorExpressionBuilder.setExpression(firExpression);
                return firErrorExpressionBuilder.mo288build();
            }
            FirExpression firExpression2 = toFirExpression(selectorExpression, "Incorrect selector expression");
            if (firExpression2 instanceof FirQualifiedAccessExpression) {
                boolean z = expression instanceof KtSafeQualifiedExpression;
                PsiRawFirBuilder psiRawFirBuilder2 = PsiRawFirBuilder.this;
                if (!z) {
                    return psiRawFirBuilder2.convertFirSelector((FirQualifiedAccessExpression) firExpression2, AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder2, expression, null, 1, null), firExpression);
                }
                FirQualifiedAccessExpression firQualifiedAccessExpression = (FirQualifiedAccessExpression) firExpression2;
                firQualifiedAccessExpression.replaceSource(psiRawFirBuilder2.toFirSourceElement((PsiElement) expression, (KtFakeSourceElementKind) KtFakeSourceElementKind.DesugaredSafeCallExpression.INSTANCE));
                return ConversionUtilsKt.createSafeCall(firQualifiedAccessExpression, firExpression, AbstractRawFirBuilder.toFirSourceElement$default(PsiRawFirBuilder.this, expression, null, 1, null));
            }
            if (!(firExpression2 instanceof FirErrorExpression)) {
                return firExpression2;
            }
            PsiRawFirBuilder psiRawFirBuilder3 = PsiRawFirBuilder.this;
            FirQualifiedErrorAccessExpressionBuilder firQualifiedErrorAccessExpressionBuilder = new FirQualifiedErrorAccessExpressionBuilder();
            firQualifiedErrorAccessExpressionBuilder.setReceiver(firExpression);
            firQualifiedErrorAccessExpressionBuilder.setSelector((FirErrorExpression) firExpression2);
            firQualifiedErrorAccessExpressionBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder3, expression, null, 1, null));
            firQualifiedErrorAccessExpressionBuilder.setDiagnostic(new ConeSyntaxDiagnostic("Qualified expression with unexpected selector"));
            return firQualifiedErrorAccessExpressionBuilder.mo288build();
        }

        public FirElement visitReturnExpression(KtReturnExpression expression, FirElement data) {
            FirExpression firExpressionMo288build;
            expression.getClass();
            KtSourceElement firSourceElement = PsiRawFirBuilder.this.toFirSourceElement((PsiElement) expression, (KtFakeSourceElementKind) KtFakeSourceElementKind.ImplicitUnit.Return.INSTANCE);
            KtExpression returnedExpression = expression.getReturnedExpression();
            if (returnedExpression == null || (firExpressionMo288build = toFirExpression(returnedExpression, "Incorrect return expression")) == null) {
                FirUnitExpressionBuilder firUnitExpressionBuilder = new FirUnitExpressionBuilder();
                firUnitExpressionBuilder.setSource(firSourceElement);
                firExpressionMo288build = firUnitExpressionBuilder.mo288build();
            }
            PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
            KtSimpleNameExpression targetLabel = expression.getTargetLabel();
            return psiRawFirBuilder.toReturn(firExpressionMo288build, firSourceElement, targetLabel != null ? targetLabel.getReferencedName() : null, true);
        }

        public FirElement visitSimpleNameExpression(KtSimpleNameExpression expression, FirElement data) {
            expression.getClass();
            PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
            KtSimpleNameExpression parent = KtPsiUtilKt.getQualifiedExpressionForSelector(expression) != null ? expression.getParent() : expression;
            parent.getClass();
            return ConversionUtilsKt.generateAccessExpression(AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, parent, null, 1, null), AbstractRawFirBuilder.toFirSourceElement$default(PsiRawFirBuilder.this, expression, null, 1, null), expression.getReferencedNameAsName());
        }

        public FirElement visitStringTemplateExpression(final KtStringTemplateExpression expression, FirElement data) {
            expression.getClass();
            PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
            KtStringTemplateEntry[] entries = expression.getEntries();
            entries.getClass();
            return psiRawFirBuilder.toInterpolatingCall(entries, expression, new Function1() { // from class: pqb
                public final Object invoke(Object obj) {
                    return PsiRawFirBuilder.Visitor.h((PsiElement) obj);
                }
            }, new Function2() { // from class: qqb
                public final Object invoke(Object obj, Object obj2) {
                    return PsiRawFirBuilder.Visitor.n(this.b, (PsiElement) obj, (String) obj2);
                }
            }, new Function0() { // from class: rqb
                public final Object invoke() {
                    return PsiRawFirBuilder.Visitor.k(expression);
                }
            });
        }

        public FirElement visitSuperExpression(KtSuperExpression expression, FirElement data) {
            expression.getClass();
            KtTypeReference superTypeQualifier = expression.getSuperTypeQualifier();
            KtSourceElement ktSourceElement = (KtPsiSourceElement) AbstractRawFirBuilder.toFirSourceElement$default(PsiRawFirBuilder.this, expression, null, 1, null);
            FirSuperReceiverExpressionBuilder firSuperReceiverExpressionBuilder = new FirSuperReceiverExpressionBuilder();
            firSuperReceiverExpressionBuilder.setSource(ktSourceElement);
            FirExplicitSuperReferenceBuilder firExplicitSuperReferenceBuilder = new FirExplicitSuperReferenceBuilder();
            firExplicitSuperReferenceBuilder.setSource(KtSourceElementKt.fakeElement$default(ktSourceElement, KtFakeSourceElementKind.ReferenceInAtomicQualifiedAccess.INSTANCE, null, 2, null));
            firExplicitSuperReferenceBuilder.setLabelName(expression.getLabelName());
            firExplicitSuperReferenceBuilder.setSuperTypeRef(toFirOrImplicitType(superTypeQualifier));
            firSuperReceiverExpressionBuilder.setCalleeReference(firExplicitSuperReferenceBuilder.build());
            return firSuperReceiverExpressionBuilder.mo288build();
        }

        public FirElement visitThisExpression(KtThisExpression expression, FirElement data) {
            expression.getClass();
            PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
            FirThisReceiverExpressionBuilder firThisReceiverExpressionBuilder = new FirThisReceiverExpressionBuilder();
            KtPsiSourceElement firSourceElement$default = AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, expression, null, 1, null);
            firThisReceiverExpressionBuilder.setSource(firSourceElement$default);
            FirExplicitThisReferenceBuilder firExplicitThisReferenceBuilder = new FirExplicitThisReferenceBuilder();
            firExplicitThisReferenceBuilder.setSource(KtSourceElementKt.fakeElement$default(firSourceElement$default, KtFakeSourceElementKind.ReferenceInAtomicQualifiedAccess.INSTANCE, null, 2, null));
            firExplicitThisReferenceBuilder.setLabelName(expression.getLabelName());
            firThisReceiverExpressionBuilder.setCalleeReference(firExplicitThisReferenceBuilder.build());
            return firThisReceiverExpressionBuilder.mo288build();
        }

        public FirElement visitThrowExpression(KtThrowExpression expression, FirElement data) {
            expression.getClass();
            PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
            FirThrowExpressionBuilder firThrowExpressionBuilder = new FirThrowExpressionBuilder();
            firThrowExpressionBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, expression, null, 1, null));
            firThrowExpressionBuilder.setException(toFirExpression(expression.getThrownExpression(), "Nothing to throw", expression));
            return firThrowExpressionBuilder.mo288build();
        }

        public FirElement visitTryExpression(KtTryExpression expression, FirElement data) {
            KtBlockExpression finalExpression;
            expression.getClass();
            PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
            FirTryExpressionBuilder firTryExpressionBuilder = new FirTryExpressionBuilder();
            firTryExpressionBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, expression, null, 1, null));
            firTryExpressionBuilder.setTryBlock(toFirBlock(expression.getTryBlock()));
            KtFinallySection finallyBlock = expression.getFinallyBlock();
            firTryExpressionBuilder.setFinallyBlock((finallyBlock == null || (finalExpression = finallyBlock.getFinalExpression()) == null) ? null : toFirBlock(finalExpression));
            for (KtCatchClause ktCatchClause : expression.getCatchClauses()) {
                final KtParameter catchParameter = ktCatchClause.getCatchParameter();
                if (catchParameter != null) {
                    Name nameAsSafeName = catchParameter.getNameAsSafeName();
                    nameAsSafeName.getClass();
                    Name nameConvertValueParameterName = psiRawFirBuilder.convertValueParameterName(nameAsSafeName, AbstractRawFirBuilder.ValueParameterDeclaration.CATCH, new Function0() { // from class: nqb
                        public final Object invoke() {
                            return PsiRawFirBuilder.Visitor.visitTryExpression$lambda$0$0$0(catchParameter);
                        }
                    });
                    FirPropertyBuilder firPropertyBuilder = new FirPropertyBuilder();
                    KtSourceElement ktSourceElement = (KtPsiSourceElement) AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, catchParameter, null, 1, null);
                    firPropertyBuilder.setSource(ktSourceElement);
                    firPropertyBuilder.setModuleData(psiRawFirBuilder.getBaseModuleData());
                    firPropertyBuilder.setOrigin(FirDeclarationOrigin.Source.INSTANCE);
                    firPropertyBuilder.setReturnTypeRef(catchParameter.getTypeReference() != null ? toFirOrErrorType(catchParameter.getTypeReference()) : psiRawFirBuilder.createNoTypeForParameterTypeRef(ktSourceElement));
                    firPropertyBuilder.setVar(false);
                    firPropertyBuilder.setStatus(new FirResolvedDeclarationStatusImpl(Visibilities.Local.INSTANCE, Modality.FINAL, EffectiveVisibility.Local.INSTANCE));
                    firPropertyBuilder.setLocal(true);
                    firPropertyBuilder.setName(nameConvertValueParameterName);
                    firPropertyBuilder.setSymbol(new FirLocalPropertySymbol());
                    for (KtAnnotationEntry ktAnnotationEntry : catchParameter.getAnnotationEntries()) {
                        List<FirAnnotation> annotations = firPropertyBuilder.getAnnotations();
                        ktAnnotationEntry.getClass();
                        FirElement firElementConvertElement = convertElement(ktAnnotationEntry, null);
                        if (firElementConvertElement == null) {
                            x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.expressions.FirAnnotation");
                            return null;
                        }
                        annotations.add((FirAnnotation) firElementConvertElement);
                    }
                    FirProperty firPropertyMo288build = firPropertyBuilder.mo288build();
                    ClassMembersKt.setCatchParameter(firPropertyMo288build, Boolean.TRUE);
                    if (firPropertyMo288build != null) {
                        List<FirCatch> catches = firTryExpressionBuilder.getCatches();
                        FirCatchBuilder firCatchBuilder = new FirCatchBuilder();
                        firCatchBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, ktCatchClause, null, 1, null));
                        firCatchBuilder.setParameter(firPropertyMo288build);
                        firCatchBuilder.setBlock(toFirBlock(ktCatchClause.getCatchBody()));
                        catches.add(firCatchBuilder.build());
                    }
                }
            }
            return firTryExpressionBuilder.mo288build();
        }

        /* JADX WARN: Code duplicated, block: B:54:0x01ae  */
        /* JADX WARN: Code duplicated, block: B:56:0x01bc  */
        public FirElement visitTypeAlias(KtTypeAlias typeAlias, FirElement data) throws Throwable {
            typeAlias.getClass();
            boolean z = PsiUtilsKt.hasExpectModifier(typeAlias) || PsiRawFirBuilder.this.getContext().getContainerIsExpect();
            PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
            Name nameAsSafeName = typeAlias.getNameAsSafeName();
            nameAsSafeName.getClass();
            PsiRawFirBuilder psiRawFirBuilder2 = PsiRawFirBuilder.this;
            psiRawFirBuilder.getContext().setClassName(psiRawFirBuilder.getContext().getClassName().child(nameAsSafeName));
            boolean containerIsExpect = psiRawFirBuilder.getContext().getContainerIsExpect();
            psiRawFirBuilder.getContext().setContainerIsExpect(containerIsExpect || z);
            int size = psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().size();
            try {
                try {
                    FirTypeAliasBuilder firTypeAliasBuilder = new FirTypeAliasBuilder();
                    firTypeAliasBuilder.setSymbol(new FirTypeAliasSymbol(psiRawFirBuilder2.getContext().getCurrentClassId()));
                    boolean zHasInnerModifier = PsiUtilsKt.hasInnerModifier(typeAlias);
                    FirTypeAliasSymbol symbol = firTypeAliasBuilder.getSymbol();
                    psiRawFirBuilder2.getContext().pushContainerSymbol(symbol);
                    try {
                        firTypeAliasBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder2, typeAlias, null, 1, null));
                        firTypeAliasBuilder.setModuleData(psiRawFirBuilder2.getBaseModuleData());
                        firTypeAliasBuilder.setOrigin(FirDeclarationOrigin.Source.INSTANCE);
                        firTypeAliasBuilder.setScopeProvider(psiRawFirBuilder2.getBaseScopeProvider());
                        Name nameAsSafeName2 = typeAlias.getNameAsSafeName();
                        nameAsSafeName2.getClass();
                        firTypeAliasBuilder.setName(nameAsSafeName2);
                        boolean inLocalContext = psiRawFirBuilder2.getContext().getInLocalContext();
                        data = null;
                        try {
                            FirDeclarationStatusImpl firDeclarationStatusImpl = new FirDeclarationStatusImpl(inLocalContext ? Visibilities.Local.INSTANCE : psiRawFirBuilder2.getVisibility(typeAlias, true), Modality.FINAL);
                            firDeclarationStatusImpl.setExpect(z);
                            firDeclarationStatusImpl.setActual(PsiUtilsKt.hasActualModifier(typeAlias));
                            firDeclarationStatusImpl.setInner(zHasInnerModifier);
                            firTypeAliasBuilder.setStatus(firDeclarationStatusImpl);
                            firTypeAliasBuilder.setExpandedTypeRef(toFirOrErrorType(typeAlias.getTypeReference()));
                            extractAnnotationsTo((KtAnnotated) typeAlias, (FirAnnotationContainerBuilder) firTypeAliasBuilder);
                            CollectionsKt.addAll(firTypeAliasBuilder.getTypeParameters(), convertTypeParameters(typeAlias, firTypeAliasBuilder.getSymbol()));
                            if (zHasInnerModifier || inLocalContext) {
                                psiRawFirBuilder2.getContext().appendOuterTypeParameters(false, firTypeAliasBuilder.getTypeParameters());
                            }
                            Unit unit = Unit.INSTANCE;
                            psiRawFirBuilder2.getContext().popContainerSymbol(symbol);
                            FirTypeAlias firTypeAliasMo288build = firTypeAliasBuilder.mo288build();
                            if (psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().size() <= size + 1) {
                                if (psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().size() > size) {
                                    psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().remove(CollectionsKt.getLastIndex(psiRawFirBuilder.getContext().getDispatchReceiverTypesStack()));
                                }
                                psiRawFirBuilder.getContext().setClassName(psiRawFirBuilder.getContext().getClassName().parent());
                                psiRawFirBuilder.getContext().setContainerIsExpect(containerIsExpect);
                                PsiRawFirBuilder psiRawFirBuilder3 = PsiRawFirBuilder.this;
                                if (typeAlias.getParent() instanceof KtClassBody) {
                                    psiRawFirBuilder3.initContainingClassForLocalAttr(firTypeAliasMo288build);
                                }
                                if (psiRawFirBuilder3.isDirectlyInsideCompanionBlock()) {
                                    ClassMembersKt.setIllegalCompanionBlockMember(firTypeAliasMo288build, Boolean.TRUE);
                                }
                                return firTypeAliasMo288build;
                            }
                            v1f.a("Wrong number of ", psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().size());
                            return data;
                        } catch (Throwable th) {
                            th = th;
                            psiRawFirBuilder2.getContext().popContainerSymbol(symbol);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    if (psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().size() <= size + 1) {
                        if (psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().size() > size) {
                            psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().remove(CollectionsKt.getLastIndex(psiRawFirBuilder.getContext().getDispatchReceiverTypesStack()));
                        }
                        psiRawFirBuilder.getContext().setClassName(psiRawFirBuilder.getContext().getClassName().parent());
                        psiRawFirBuilder.getContext().setContainerIsExpect(containerIsExpect);
                        throw th;
                    }
                }
            } catch (Throwable th4) {
                th = th4;
                data = null;
                if (psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().size() <= size + 1) {
                    if (psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().size() > size) {
                        psiRawFirBuilder.getContext().getDispatchReceiverTypesStack().remove(CollectionsKt.getLastIndex(psiRawFirBuilder.getContext().getDispatchReceiverTypesStack()));
                    }
                    psiRawFirBuilder.getContext().setClassName(psiRawFirBuilder.getContext().getClassName().parent());
                    psiRawFirBuilder.getContext().setContainerIsExpect(containerIsExpect);
                    throw th;
                }
            }
        }

        public FirElement visitTypeParameter(KtTypeParameter parameter, FirElement data) {
            parameter.getClass();
            throw new AssertionError("KtTypeParameter should be process via extractTypeParameter");
        }

        public FirElement visitTypeProjection(KtTypeProjection typeProjection, FirElement data) {
            Variance variance;
            typeProjection.getClass();
            KtProjectionKind projectionKind = typeProjection.getProjectionKind();
            projectionKind.getClass();
            KtPsiSourceElement firSourceElement$default = AbstractRawFirBuilder.toFirSourceElement$default(PsiRawFirBuilder.this, typeProjection, null, 1, null);
            if (projectionKind == KtProjectionKind.STAR) {
                FirStarProjectionBuilder firStarProjectionBuilder = new FirStarProjectionBuilder();
                firStarProjectionBuilder.setSource(firSourceElement$default);
                return firStarProjectionBuilder.build();
            }
            KtTypeArgumentList parent = typeProjection.getParent();
            KtTypeArgumentList ktTypeArgumentList = parent instanceof KtTypeArgumentList ? parent : null;
            KtTypeReference typeReference = typeProjection.getTypeReference();
            if (((ktTypeArgumentList != null ? ktTypeArgumentList.getParent() : null) instanceof KtCallExpression) && typeReference != null && typeReference.isPlaceholder()) {
                FirPlaceholderProjectionBuilder firPlaceholderProjectionBuilder = new FirPlaceholderProjectionBuilder();
                firPlaceholderProjectionBuilder.setSource(firSourceElement$default);
                return firPlaceholderProjectionBuilder.build();
            }
            FirTypeRef firOrErrorType = toFirOrErrorType(typeReference);
            FirTypeProjectionWithVarianceBuilder firTypeProjectionWithVarianceBuilder = new FirTypeProjectionWithVarianceBuilder();
            firTypeProjectionWithVarianceBuilder.setSource(firSourceElement$default);
            firTypeProjectionWithVarianceBuilder.setTypeRef(firOrErrorType);
            int i = WhenMappings.$EnumSwitchMapping$1[projectionKind.ordinal()];
            if (i == 1) {
                variance = Variance.IN_VARIANCE;
            } else if (i == 2) {
                variance = Variance.OUT_VARIANCE;
            } else {
                if (i != 3) {
                    if (i != 4) {
                        bu8.a();
                        return null;
                    }
                    AddToStdlibKt.shouldNotBeCalled$default((String) null, 1, (Object) null);
                    wq6.a();
                    return null;
                }
                variance = Variance.INVARIANT;
            }
            firTypeProjectionWithVarianceBuilder.setVariance(variance);
            return firTypeProjectionWithVarianceBuilder.build();
        }

        public FirElement visitTypeReference(KtTypeReference typeReference, FirElement data) {
            typeReference.getClass();
            return toFirType(typeReference);
        }

        public FirElement visitUnaryExpression(KtUnaryExpression expression, FirElement data) {
            expression.getClass();
            IElementType operationToken = expression.getOperationToken();
            KtExpression baseExpression = expression.getBaseExpression();
            operationToken.getClass();
            Name unaryName = ConversionUtilsKt.toUnaryName(operationToken);
            if (Intrinsics.areEqual(operationToken, KtTokens.EXCLEXCL)) {
                PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
                FirCheckNotNullCallBuilder firCheckNotNullCallBuilder = new FirCheckNotNullCallBuilder();
                firCheckNotNullCallBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, expression, null, 1, null));
                firCheckNotNullCallBuilder.setArgumentList(FirArgumentUtilKt.buildUnaryArgumentList(toFirExpression(baseExpression, "No operand", expression)));
                return firCheckNotNullCallBuilder.mo288build();
            }
            if (unaryName == null) {
                sle.a("Unexpected expression: ", expression.getText());
                return null;
            }
            ImmutableSet immutableSet = OperatorConventions.INCREMENT_OPERATIONS;
            immutableSet.getClass();
            if (CollectionsKt.contains(immutableSet, operationToken)) {
                return PsiRawFirBuilder.this.generateIncrementOrDecrementBlock(expression, expression.getOperationReference(), baseExpression, unaryName, expression instanceof KtPrefixExpression, new Function1() { // from class: oqb
                    public final Object invoke(Object obj) {
                        return PsiRawFirBuilder.Visitor.o(this.b, (PsiElement) obj);
                    }
                });
            }
            FirExpression firExpression = toFirExpression(baseExpression, "No operand", expression);
            FirExpression firExpressionConvertUnaryPlusMinusCallOnIntegerLiteralIfNecessary = PsiRawFirBuilder.this.convertUnaryPlusMinusCallOnIntegerLiteralIfNecessary(expression, firExpression, operationToken);
            if (firExpressionConvertUnaryPlusMinusCallOnIntegerLiteralIfNecessary != null) {
                return firExpressionConvertUnaryPlusMinusCallOnIntegerLiteralIfNecessary;
            }
            PsiRawFirBuilder psiRawFirBuilder2 = PsiRawFirBuilder.this;
            FirFunctionCallBuilder firFunctionCallBuilder = new FirFunctionCallBuilder();
            firFunctionCallBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder2, expression, null, 1, null));
            FirSimpleNamedReferenceBuilder firSimpleNamedReferenceBuilder = new FirSimpleNamedReferenceBuilder();
            KtSimpleNameExpression operationReference = expression.getOperationReference();
            operationReference.getClass();
            firSimpleNamedReferenceBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder2, operationReference, null, 1, null));
            firSimpleNamedReferenceBuilder.setName(unaryName);
            firFunctionCallBuilder.setCalleeReference(firSimpleNamedReferenceBuilder.build());
            firFunctionCallBuilder.setExplicitReceiver(firExpression);
            firFunctionCallBuilder.setOrigin(FirFunctionCallOrigin.Operator);
            return firFunctionCallBuilder.mo288build();
        }

        public FirElement visitWhenExpression(KtWhenExpression expression, FirElement data) {
            FirExpression firExpression;
            FirProperty firPropertyMo288build;
            FirProperty firProperty;
            int i;
            int i2;
            FirWhenBranch firWhenBranchBuild;
            FirExpression firExpressionBuildBalancedOrExpressionTree$default;
            boolean z;
            FirExpression firExpressionMo288build;
            Name nameAsSafeName;
            final Visitor visitor = this;
            expression.getClass();
            KtElement subjectExpression = expression.getSubjectExpression();
            boolean z2 = subjectExpression instanceof KtVariableDeclaration;
            KtElement initializer = z2 ? ((KtVariableDeclaration) subjectExpression).getInitializer() : subjectExpression;
            FirExpression firExpression2 = null;
            if (initializer != null) {
                StringBuilder sb = new StringBuilder("Incorrect when subject expression: ");
                sb.append(subjectExpression != null ? subjectExpression.getText() : null);
                firExpression = visitor.toFirExpression(initializer, sb.toString());
            } else {
                firExpression = null;
            }
            int i3 = 0;
            int i4 = 1;
            if (z2) {
                KtVariableDeclaration ktVariableDeclaration = (KtVariableDeclaration) subjectExpression;
                PsiElement nameIdentifier = ktVariableDeclaration.getNameIdentifier();
                if (Intrinsics.areEqual(nameIdentifier != null ? nameIdentifier.getText() : null, InlineClassManglingUtilsKt.NOT_INLINE_CLASS_PARAMETER_PLACEHOLDER)) {
                    nameAsSafeName = SpecialNames.UNDERSCORE_FOR_UNUSED_VAR;
                } else {
                    nameAsSafeName = ktVariableDeclaration.getNameAsSafeName();
                    nameAsSafeName.getClass();
                }
                PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
                FirPropertyBuilder firPropertyBuilder = new FirPropertyBuilder();
                firPropertyBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, subjectExpression, null, 1, null));
                firPropertyBuilder.setModuleData(psiRawFirBuilder.getBaseModuleData());
                firPropertyBuilder.setOrigin(FirDeclarationOrigin.Source.INSTANCE);
                firPropertyBuilder.setReturnTypeRef(visitor.toFirOrImplicitType(ktVariableDeclaration.getTypeReference()));
                firPropertyBuilder.setName(nameAsSafeName);
                firPropertyBuilder.setInitializer(firExpression);
                firPropertyBuilder.setDelegate(null);
                firPropertyBuilder.setVar(false);
                firPropertyBuilder.setSymbol(new FirLocalPropertySymbol());
                firPropertyBuilder.setStatus(new FirDeclarationStatusImpl(Visibilities.Local.INSTANCE, Modality.FINAL));
                firPropertyBuilder.setLocal(true);
                final KtTypeReference receiverTypeReference = ktVariableDeclaration.getReceiverTypeReference();
                firPropertyBuilder.setReceiverParameter(receiverTypeReference != null ? ConversionUtilsKt.createReceiverParameter(psiRawFirBuilder, new Function0() { // from class: yqb
                    public final Object invoke() {
                        return this.b.toFirType(receiverTypeReference);
                    }
                }, firPropertyBuilder.getModuleData(), firPropertyBuilder.getSymbol()) : null);
                visitor.extractAnnotationsTo((KtAnnotated) subjectExpression, firPropertyBuilder);
                firPropertyMo288build = firPropertyBuilder.mo288build();
            } else {
                firPropertyMo288build = null;
            }
            boolean z3 = firExpression != null;
            if (z3 && firPropertyMo288build == null) {
                Name name = SpecialNames.WHEN_SUBJECT;
                PsiRawFirBuilder psiRawFirBuilder2 = PsiRawFirBuilder.this;
                FirPropertyBuilder firPropertyBuilder2 = new FirPropertyBuilder();
                KtSourceElement source = firExpression.getSource();
                firPropertyBuilder2.setSource(source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.WhenGeneratedSubject.INSTANCE, null, 2, null) : null);
                firPropertyBuilder2.setModuleData(psiRawFirBuilder2.getBaseModuleData());
                firPropertyBuilder2.setOrigin(FirDeclarationOrigin.Synthetic.ImplicitWhenSubject.INSTANCE);
                firPropertyBuilder2.setReturnTypeRef(FirImplicitTypeRefImplWithoutSource.INSTANCE);
                firPropertyBuilder2.setName(name);
                firPropertyBuilder2.setInitializer(firExpression);
                firPropertyBuilder2.setDelegate(null);
                firPropertyBuilder2.setVar(false);
                firPropertyBuilder2.setSymbol(new FirLocalPropertySymbol());
                firPropertyBuilder2.setStatus(new FirDeclarationStatusImpl(Visibilities.Local.INSTANCE, Modality.FINAL));
                firPropertyBuilder2.setLocal(true);
                firPropertyMo288build = firPropertyBuilder2.mo288build();
            }
            FirExpressionRef firExpressionRef = new FirExpressionRef();
            PsiRawFirBuilder psiRawFirBuilder3 = PsiRawFirBuilder.this;
            FirWhenExpressionBuilder firWhenExpressionBuilder = new FirWhenExpressionBuilder();
            firWhenExpressionBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder3, expression, null, 1, null));
            firWhenExpressionBuilder.setSubjectVariable(firPropertyMo288build);
            firWhenExpressionBuilder.setUsedAsExpression(getUsedAsExpression(expression));
            boolean z4 = z3;
            for (KtWhenEntry ktWhenEntry : expression.getEntries()) {
                ktWhenEntry.getClass();
                KtSourceElement ktSourceElement = (KtPsiSourceElement) AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder3, ktWhenEntry, firExpression2, i4, firExpression2);
                KtElement guard = ktWhenEntry.getGuard();
                FirExpression firExpression3 = guard != null ? visitor.toFirExpression(guard.getExpression(), "No expression in guard", guard) : firExpression2;
                FirBlock firBlock = visitor.toFirBlock(ktWhenEntry.getExpression());
                List<FirWhenBranch> branches = firWhenExpressionBuilder.getBranches();
                if (ktWhenEntry.getElseKeyword() != null) {
                    firProperty = firPropertyMo288build;
                    i = i3;
                    i2 = i4;
                    FirAbstractWhenBranchBuilder firGuardedWhenBranchBuilder = (firExpression3 != null ? i2 : i) != 0 ? new FirGuardedWhenBranchBuilder() : new FirRegularWhenBranchBuilder();
                    firGuardedWhenBranchBuilder.setSource(ktSourceElement);
                    if (firExpression3 == null) {
                        FirElseIfTrueConditionBuilder firElseIfTrueConditionBuilder = new FirElseIfTrueConditionBuilder();
                        Unit unit = Unit.INSTANCE;
                        firExpression3 = firElseIfTrueConditionBuilder.mo288build();
                    }
                    firGuardedWhenBranchBuilder.setCondition(firExpression3);
                    firGuardedWhenBranchBuilder.setResult(firBlock);
                    firWhenBranchBuild = firGuardedWhenBranchBuilder.build();
                } else if (z3) {
                    FirAbstractWhenBranchBuilder firGuardedWhenBranchBuilder2 = (firExpression3 != null ? i4 : i3) != 0 ? new FirGuardedWhenBranchBuilder() : new FirRegularWhenBranchBuilder();
                    firGuardedWhenBranchBuilder2.setSource(ktSourceElement);
                    KtWhenCondition[] conditions = ktWhenEntry.getConditions();
                    conditions.getClass();
                    firGuardedWhenBranchBuilder2.setCondition(ConversionUtilsKt.guardedBy(PsiConversionUtilsKt.toFirWhenCondition(conditions, firPropertyMo288build, (Function3<? super KtExpression, ? super String, ? super KtElement, ? extends FirExpression>) new Function3() { // from class: erb
                        public final Object invoke(Object obj, Object obj2, Object obj3) {
                            return PsiRawFirBuilder.Visitor.visitWhenExpression$lambda$2$1$0(this.b, (KtExpression) obj, (String) obj2, (KtElement) obj3);
                        }
                    }, (Function1<? super KtTypeReference, ? extends FirTypeRef>) new Function1() { // from class: frb
                        public final Object invoke(Object obj) {
                            return this.b.toFirOrErrorType((KtTypeReference) obj);
                        }
                    }), firExpression3));
                    firGuardedWhenBranchBuilder2.setResult(firBlock);
                    firProperty = firPropertyMo288build;
                    firWhenBranchBuild = firGuardedWhenBranchBuilder2.build();
                    i2 = i4;
                    i = 0;
                    firExpression2 = null;
                } else {
                    KtWhenCondition[] conditions2 = ktWhenEntry.getConditions();
                    conditions2.getClass();
                    KtElement ktElement = (KtWhenCondition) ArraysKt.first(conditions2);
                    FirAbstractWhenBranchBuilder firGuardedWhenBranchBuilder3 = (firExpression3 != null ? i4 : 0) != 0 ? new FirGuardedWhenBranchBuilder() : new FirRegularWhenBranchBuilder();
                    firGuardedWhenBranchBuilder3.setSource(ktSourceElement);
                    if (ktWhenEntry.getConditions().length == i4 && (ktElement instanceof KtWhenConditionWithExpression)) {
                        KtElement expression2 = ((KtWhenConditionWithExpression) ktElement).getExpression();
                        if (expression2 != null) {
                            ktElement = expression2;
                        }
                        firProperty = firPropertyMo288build;
                        firExpressionBuildBalancedOrExpressionTree$default = visitor.toFirExpression(ktElement, "No expression in condition with expression");
                        i2 = i4;
                        i = 0;
                        firExpression2 = null;
                    } else {
                        KtElement[] conditions3 = ktWhenEntry.getConditions();
                        conditions3.getClass();
                        ArrayList arrayList = new ArrayList(conditions3.length);
                        int length = conditions3.length;
                        int i5 = 0;
                        while (i5 < length) {
                            KtElement[] ktElementArr = conditions3;
                            KtElement ktElement2 = ktElementArr[i5];
                            int i6 = i5;
                            if (ktElement2 instanceof KtWhenConditionWithExpression) {
                                firExpressionMo288build = visitor.toFirExpression(((KtWhenConditionWithExpression) ktElement2).getExpression(), "No expression in condition with expression", ktElement2);
                                firPropertyMo288build = firPropertyMo288build;
                            } else {
                                ktElement2.getClass();
                                FirExpression firWhenCondition = PsiConversionUtilsKt.toFirWhenCondition((KtWhenCondition) ktElement2, (FirVariable) firPropertyMo288build, (Function3<? super KtExpression, ? super String, ? super KtElement, ? extends FirExpression>) new Function3() { // from class: grb
                                    public final Object invoke(Object obj, Object obj2, Object obj3) {
                                        return PsiRawFirBuilder.Visitor.visitWhenExpression$lambda$2$2$0$0(this.b, (KtExpression) obj, (String) obj2, (KtElement) obj3);
                                    }
                                }, (Function1<? super KtTypeReference, ? extends FirTypeRef>) new Function1() { // from class: hrb
                                    public final Object invoke(Object obj) {
                                        return this.b.toFirOrErrorType((KtTypeReference) obj);
                                    }
                                });
                                FirExpression firExpression4 = firPropertyMo288build != null ? firWhenCondition : null;
                                if (firExpression4 == null) {
                                    FirErrorExpressionBuilder firErrorExpressionBuilder = new FirErrorExpressionBuilder();
                                    z = true;
                                    firErrorExpressionBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder3, ktElement2, null, 1, null));
                                    firErrorExpressionBuilder.setNonExpressionElement(firWhenCondition);
                                    firErrorExpressionBuilder.setDiagnostic(new ConeSimpleDiagnostic("No expression in condition with expression", DiagnosticKind.ExpressionExpected));
                                    firExpressionMo288build = firErrorExpressionBuilder.mo288build();
                                } else {
                                    z = true;
                                    firExpressionMo288build = firExpression4;
                                }
                                z4 = z;
                            }
                            arrayList.add(firExpressionMo288build);
                            i5 = i6 + 1;
                            visitor = this;
                            conditions3 = ktElementArr;
                            firPropertyMo288build = firPropertyMo288build;
                        }
                        firProperty = firPropertyMo288build;
                        i2 = 1;
                        i = 0;
                        firExpression2 = null;
                        firExpressionBuildBalancedOrExpressionTree$default = ConversionUtilsKt.buildBalancedOrExpressionTree$default(arrayList, 0, 0, 6, null);
                    }
                    firGuardedWhenBranchBuilder3.setCondition(ConversionUtilsKt.guardedBy(firExpressionBuildBalancedOrExpressionTree$default, firExpression3));
                    firGuardedWhenBranchBuilder3.setResult(firBlock);
                    firWhenBranchBuild = firGuardedWhenBranchBuilder3.build();
                }
                branches.add(firWhenBranchBuild);
                i4 = i2;
                i3 = i;
                z3 = z3;
                firPropertyMo288build = firProperty;
                visitor = this;
            }
            FirWhenExpression firWhenExpressionMo288build = firWhenExpressionBuilder.mo288build();
            if (z4) {
                firExpressionRef.bind(firWhenExpressionMo288build);
            }
            return firWhenExpressionMo288build;
        }

        public FirElement visitWhileExpression(final KtWhileExpression expression, FirElement data) {
            expression.getClass();
            PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
            FirWhileLoopBuilder firWhileLoopBuilder = new FirWhileLoopBuilder();
            PsiRawFirBuilder psiRawFirBuilder2 = PsiRawFirBuilder.this;
            firWhileLoopBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder2, expression, null, 1, null));
            KtExpression condition = expression.getCondition();
            IElementType iElementType = KtNodeTypes.CONDITION;
            iElementType.getClass();
            PsiElement childNodeByType = psiRawFirBuilder2.getChildNodeByType((PsiElement) expression, iElementType);
            KtWhileExpression ktWhileExpression = childNodeByType instanceof KtElement ? (KtElement) childNodeByType : null;
            if (ktWhileExpression == null) {
                ktWhileExpression = expression;
            }
            firWhileLoopBuilder.setCondition(toFirExpression(condition, "No condition in while loop", ktWhileExpression));
            return psiRawFirBuilder.configure(firWhileLoopBuilder, psiRawFirBuilder2.prepareTarget(firWhileLoopBuilder, expression), new Function0() { // from class: uqb
                public final Object invoke() {
                    return PsiRawFirBuilder.Visitor.w(this.b, expression);
                }
            });
        }

        /* JADX INFO: renamed from: extractAnnotationsTo, reason: avoid collision after fix types in other method */
        public void extractAnnotationsTo2(KtDestructuringDeclarationEntry ktDestructuringDeclarationEntry, FirAnnotationContainerBuilder firAnnotationContainerBuilder, FirBasedSymbol<?> firBasedSymbol) {
            ktDestructuringDeclarationEntry.getClass();
            firAnnotationContainerBuilder.getClass();
            firBasedSymbol.getClass();
            extractAnnotationsTo((KtAnnotated) ktDestructuringDeclarationEntry, firAnnotationContainerBuilder);
        }

        @Override // org.jetbrains.kotlin.fir.builder.DestructuringContext
        public /* bridge */ /* synthetic */ void extractAnnotationsTo(KtDestructuringDeclarationEntry ktDestructuringDeclarationEntry, FirAnnotationContainerBuilder firAnnotationContainerBuilder, FirBasedSymbol firBasedSymbol) {
            extractAnnotationsTo2(ktDestructuringDeclarationEntry, firAnnotationContainerBuilder, (FirBasedSymbol<?>) firBasedSymbol);
        }

        private final void extractAnnotationsTo(KtAnnotated ktAnnotated, FirAnnotationContainerBuilder firAnnotationContainerBuilder) {
            for (KtAnnotationEntry ktAnnotationEntry : ktAnnotated.getAnnotationEntries()) {
                List<FirAnnotation> annotations = firAnnotationContainerBuilder.getAnnotations();
                ktAnnotationEntry.getClass();
                FirElement firElementConvertElement = convertElement(ktAnnotationEntry, null);
                if (firElementConvertElement != null) {
                    annotations.add((FirAnnotation) firElementConvertElement);
                } else {
                    x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.expressions.FirAnnotation");
                    return;
                }
            }
        }

        private final FirExpression toFirExpression(ValueArgument valueArgument) {
            FirExpression firExpression;
            ValueArgumentName argumentName = valueArgument.getArgumentName();
            Name asName = argumentName != null ? argumentName.getAsName() : null;
            KtElementImplStub argumentExpression = valueArgument.getArgumentExpression();
            if (!(argumentExpression instanceof KtConstantExpression) && !(argumentExpression instanceof KtStringTemplateExpression)) {
                firExpression = toFirExpression(argumentExpression, "Argument is absent", valueArgument.asElement());
            } else {
                Object objAccept = argumentExpression.accept(this, (Object) null);
                objAccept.getClass();
                firExpression = (FirExpression) objAccept;
            }
            boolean zIsSpread = valueArgument.isSpread();
            if (asName != null) {
                PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
                FirNamedArgumentExpressionBuilder firNamedArgumentExpressionBuilder = new FirNamedArgumentExpressionBuilder();
                PsiElement psiElement = valueArgument instanceof PsiElement ? (PsiElement) valueArgument : null;
                firNamedArgumentExpressionBuilder.setSource(psiElement != null ? AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, psiElement, null, 1, null) : null);
                firNamedArgumentExpressionBuilder.setExpression(firExpression);
                firNamedArgumentExpressionBuilder.setSpread(zIsSpread);
                firNamedArgumentExpressionBuilder.setName(asName);
                return firNamedArgumentExpressionBuilder.mo288build();
            }
            if (!zIsSpread) {
                return firExpression;
            }
            PsiRawFirBuilder psiRawFirBuilder2 = PsiRawFirBuilder.this;
            FirSpreadArgumentExpressionBuilder firSpreadArgumentExpressionBuilder = new FirSpreadArgumentExpressionBuilder();
            PsiElement psiElement2 = valueArgument instanceof PsiElement ? (PsiElement) valueArgument : null;
            firSpreadArgumentExpressionBuilder.setSource(psiElement2 != null ? AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder2, psiElement2, null, 1, null) : null);
            firSpreadArgumentExpressionBuilder.setExpression(firExpression);
            return firSpreadArgumentExpressionBuilder.mo288build();
        }

        private final FirExpression toFirExpression(KtElement ktElement, String str) {
            ConeDiagnostic coneSimpleDiagnostic;
            KtSourceElement firSourceElement$default;
            KtSourceElement source;
            KtSourceElement firSourceElement;
            if (ktElement == null) {
                return FirExpressionUtilKt.buildErrorExpression$default(PsiRawFirBuilder.this.toFirSourceElement((PsiElement) ktElement, (KtFakeSourceElementKind) KtFakeSourceElementKind.ErrorExpression.INSTANCE), new ConeSyntaxDiagnostic(str), null, 4, null);
            }
            FirElement firElementConvertElement = convertElement(ktElement, null);
            if (firElementConvertElement instanceof FirExpression) {
                FirExpression firExpression = (FirExpression) firElementConvertElement;
                if (!UtilsKt.isStatementLikeExpression(firExpression)) {
                    return checkSelectorInvariant(ktElement, firExpression);
                }
                PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
                FirErrorExpressionBuilder firErrorExpressionBuilder = new FirErrorExpressionBuilder();
                firErrorExpressionBuilder.setNonExpressionElement(firElementConvertElement);
                firErrorExpressionBuilder.setDiagnostic(new ConeSimpleDiagnostic(str, DiagnosticKind.ExpressionExpected));
                KtSourceElement source2 = firExpression.getSource();
                if (source2 == null || (firSourceElement = KtSourceElementKt.realElement(source2)) == null) {
                    firSourceElement = psiRawFirBuilder.toFirSourceElement((PsiElement) ktElement, (KtFakeSourceElementKind) KtFakeSourceElementKind.ErrorExpression.INSTANCE);
                }
                firErrorExpressionBuilder.setSource(firSourceElement);
                return firErrorExpressionBuilder.mo288build();
            }
            PsiRawFirBuilder psiRawFirBuilder2 = PsiRawFirBuilder.this;
            FirErrorExpressionBuilder firErrorExpressionBuilder2 = new FirErrorExpressionBuilder();
            firErrorExpressionBuilder2.setNonExpressionElement(firElementConvertElement);
            if (firElementConvertElement == null) {
                coneSimpleDiagnostic = new ConeSyntaxDiagnostic(str);
            } else {
                coneSimpleDiagnostic = new ConeSimpleDiagnostic(str, DiagnosticKind.ExpressionExpected);
            }
            firErrorExpressionBuilder2.setDiagnostic(coneSimpleDiagnostic);
            if (firElementConvertElement == null || (source = firElementConvertElement.getSource()) == null || (firSourceElement$default = KtSourceElementKt.realElement(source)) == null) {
                firSourceElement$default = AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder2, ktElement, null, 1, null);
            }
            firErrorExpressionBuilder2.setSource(firSourceElement$default);
            return firErrorExpressionBuilder2.mo288build();
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
        /* JADX WARN: Code duplicated, block: B:11:0x0038 A[Catch: all -> 0x0030, TryCatch #0 {all -> 0x0030, blocks: (B:4:0x0026, B:12:0x003e, B:14:0x0053, B:16:0x0059, B:24:0x00ab, B:26:0x00b1, B:28:0x00b5, B:30:0x00bb, B:32:0x00c3, B:39:0x00d7, B:41:0x00dc, B:47:0x00e8, B:49:0x00f7, B:53:0x0100, B:55:0x010b, B:59:0x0111, B:61:0x011f, B:63:0x012b, B:65:0x016d, B:67:0x0173, B:69:0x0179, B:71:0x0182, B:73:0x0187, B:74:0x01a6, B:62:0x0126, B:35:0x00ca, B:17:0x0067, B:18:0x007b, B:20:0x0081, B:21:0x00a3, B:9:0x0034, B:11:0x0038, B:77:0x01c3, B:78:0x01c8), top: B:81:0x0026 }] */
        /* JADX WARN: Code duplicated, block: B:77:0x01c3 A[Catch: all -> 0x0030, TRY_ENTER, TryCatch #0 {all -> 0x0030, blocks: (B:4:0x0026, B:12:0x003e, B:14:0x0053, B:16:0x0059, B:24:0x00ab, B:26:0x00b1, B:28:0x00b5, B:30:0x00bb, B:32:0x00c3, B:39:0x00d7, B:41:0x00dc, B:47:0x00e8, B:49:0x00f7, B:53:0x0100, B:55:0x010b, B:59:0x0111, B:61:0x011f, B:63:0x012b, B:65:0x016d, B:67:0x0173, B:69:0x0179, B:71:0x0182, B:73:0x0187, B:74:0x01a6, B:62:0x0126, B:35:0x00ca, B:17:0x0067, B:18:0x007b, B:20:0x0081, B:21:0x00a3, B:9:0x0034, B:11:0x0038, B:77:0x01c3, B:78:0x01c8), top: B:81:0x0026 }] */
        /* JADX WARN: Code duplicated, block: B:9:0x0034 A[Catch: all -> 0x0030, TryCatch #0 {all -> 0x0030, blocks: (B:4:0x0026, B:12:0x003e, B:14:0x0053, B:16:0x0059, B:24:0x00ab, B:26:0x00b1, B:28:0x00b5, B:30:0x00bb, B:32:0x00c3, B:39:0x00d7, B:41:0x00dc, B:47:0x00e8, B:49:0x00f7, B:53:0x0100, B:55:0x010b, B:59:0x0111, B:61:0x011f, B:63:0x012b, B:65:0x016d, B:67:0x0173, B:69:0x0179, B:71:0x0182, B:73:0x0187, B:74:0x01a6, B:62:0x0126, B:35:0x00ca, B:17:0x0067, B:18:0x007b, B:20:0x0081, B:21:0x00a3, B:9:0x0034, B:11:0x0038, B:77:0x01c3, B:78:0x01c8), top: B:81:0x0026 }] */
        public final FirConstructor toFirConstructor(KtPrimaryConstructor ktPrimaryConstructor, KtSuperTypeCallEntry ktSuperTypeCallEntry, FirTypeRef firTypeRef, FirTypeRef firTypeRef2, KtClassOrObject ktClassOrObject, List<? extends FirTypeParameterRef> list, List<? extends Pair<? extends KtSuperTypeCallEntry, ? extends FirTypeRef>> list2, boolean z, boolean z2, boolean z3, boolean z4, boolean z5) throws KotlinIllegalStateExceptionWithAttachments {
            KtSourceElement ktFakePsiSourceElement;
            FirDelegatedConstructorCall firDelegatedConstructorCallMo288build;
            FirAbstractConstructorBuilder firPrimaryConstructorBuilder;
            KtModifierList modifierList;
            List<? extends KtContextParameterList> contextParameterLists;
            KtFakeSourceElementKind.ImplicitConstructor implicitConstructor;
            firTypeRef2.getClass();
            ktClassOrObject.getClass();
            list.getClass();
            list2.getClass();
            FirConstructorSymbol firConstructorSymbol = new FirConstructorSymbol(PsiRawFirBuilder.this.callableIdForClassConstructor());
            PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
            psiRawFirBuilder.getContext().pushContainerSymbol(firConstructorSymbol);
            boolean z6 = true;
            if (ktPrimaryConstructor != null) {
                try {
                    KtSourceElement ktSourceElement = (KtPsiSourceElement) AbstractRawFirBuilder.toFirSourceElement$default(psiRawFirBuilder, ktPrimaryConstructor, null, 1, null);
                    if (ktSourceElement != null) {
                        ktFakePsiSourceElement = ktSourceElement;
                    } else {
                        implicitConstructor = KtFakeSourceElementKind.ImplicitConstructor.INSTANCE;
                        if (implicitConstructor != null) {
                            ktFakePsiSourceElement = new KtFakePsiSourceElement(ktClassOrObject, implicitConstructor);
                        } else {
                            throw new NoWhenBranchMatchedException();
                        }
                    }
                } catch (Throwable th) {
                    psiRawFirBuilder.getContext().popContainerSymbol(firConstructorSymbol);
                    throw th;
                }
            } else {
                implicitConstructor = KtFakeSourceElementKind.ImplicitConstructor.INSTANCE;
                if (implicitConstructor != null) {
                    ktFakePsiSourceElement = new KtFakePsiSourceElement(ktClassOrObject, implicitConstructor);
                } else {
                    throw new NoWhenBranchMatchedException();
                }
            }
            if (!ConversionUtilsKt.shouldGenerateDelegatedSuperCall(z5, z, ktClassOrObject instanceof KtEnumEntry, !list2.isEmpty())) {
                firDelegatedConstructorCallMo288build = null;
            } else if (list2.size() <= 1) {
                firTypeRef.getClass();
                firDelegatedConstructorCallMo288build = toFirConstructor$lambda$0$buildDelegatedCall(psiRawFirBuilder, z2, this, ktFakePsiSourceElement, ktSuperTypeCallEntry, firTypeRef);
            } else {
                FirMultiDelegatedConstructorCallBuilder firMultiDelegatedConstructorCallBuilder = new FirMultiDelegatedConstructorCallBuilder();
                List<FirDelegatedConstructorCall> delegatedConstructorCalls = firMultiDelegatedConstructorCallBuilder.getDelegatedConstructorCalls();
                Iterator<T> it = list2.iterator();
                while (it.hasNext()) {
                    Pair pair = (Pair) it.next();
                    FirDelegatedConstructorCall firConstructor$lambda$0$buildDelegatedCall = toFirConstructor$lambda$0$buildDelegatedCall(psiRawFirBuilder, z2, this, ktFakePsiSourceElement, (KtSuperTypeCallEntry) pair.component1(), (FirTypeRef) pair.component2());
                    firConstructor$lambda$0$buildDelegatedCall.getClass();
                    delegatedConstructorCalls.add(firConstructor$lambda$0$buildDelegatedCall);
                }
                firDelegatedConstructorCallMo288build = firMultiDelegatedConstructorCallBuilder.mo288build();
            }
            Visibility constructorExplicitVisibility = ktPrimaryConstructor != null ? psiRawFirBuilder.getConstructorExplicitVisibility(ktPrimaryConstructor) : null;
            FirDeclarationStatusImpl firDeclarationStatusImpl = new FirDeclarationStatusImpl(constructorExplicitVisibility == null ? psiRawFirBuilder.constructorDefaultVisibility(ktClassOrObject) : constructorExplicitVisibility, Modality.FINAL);
            firDeclarationStatusImpl.setExpect((ktPrimaryConstructor != null && PsiUtilsKt.hasExpectModifier(ktPrimaryConstructor)) || psiRawFirBuilder.getContext().getContainerIsExpect());
            firDeclarationStatusImpl.setActual((ktPrimaryConstructor != null && PsiUtilsKt.hasActualModifier(ktPrimaryConstructor)) || z4);
            firDeclarationStatusImpl.setInner(!(ktClassOrObject.getParent().getParent() instanceof KtScript) && PsiUtilsKt.hasInnerModifier(ktClassOrObject));
            if (!ktClassOrObject.hasModifier(KtTokens.SEALED_KEYWORD) || constructorExplicitVisibility == Visibilities.Private.INSTANCE) {
                z6 = false;
            }
            firDeclarationStatusImpl.setFromSealedClass(z6);
            firDeclarationStatusImpl.setFromEnumClass(ktClassOrObject.hasModifier(KtTokens.ENUM_KEYWORD));
            if (z3) {
                firPrimaryConstructorBuilder = psiRawFirBuilder.createErrorConstructorBuilder(ConeNoConstructorError.INSTANCE);
            } else {
                firPrimaryConstructorBuilder = new FirPrimaryConstructorBuilder();
            }
            firPrimaryConstructorBuilder.setSource(ktFakePsiSourceElement);
            firPrimaryConstructorBuilder.setModuleData(psiRawFirBuilder.getBaseModuleData());
            firPrimaryConstructorBuilder.setOrigin(FirDeclarationOrigin.Source.INSTANCE);
            firPrimaryConstructorBuilder.setReturnTypeRef(firTypeRef2);
            firPrimaryConstructorBuilder.setStatus(firDeclarationStatusImpl);
            firPrimaryConstructorBuilder.setLocal(psiRawFirBuilder.getContext().getInLocalContext());
            firPrimaryConstructorBuilder.setDispatchReceiverType(obtainDispatchReceiverForConstructor(ktClassOrObject));
            firPrimaryConstructorBuilder.setSymbol(firConstructorSymbol);
            firPrimaryConstructorBuilder.setDelegatedConstructor(firDelegatedConstructorCallMo288build);
            CollectionsKt.addAll(firPrimaryConstructorBuilder.getTypeParameters(), psiRawFirBuilder.constructorTypeParametersFromConstructedClass(list));
            if (ktPrimaryConstructor != null && (modifierList = ktPrimaryConstructor.getModifierList()) != null && (contextParameterLists = modifierList.getContextParameterLists()) != null) {
                addContextParameters(firPrimaryConstructorBuilder.getContextParameters(), contextParameterLists, firConstructorSymbol);
            }
            if (ktPrimaryConstructor != null) {
                extractAnnotationsTo((KtAnnotated) ktPrimaryConstructor, (FirAnnotationContainerBuilder) firPrimaryConstructorBuilder);
            }
            if (ktPrimaryConstructor != null) {
                extractValueParametersTo$default(this, ktPrimaryConstructor, firPrimaryConstructorBuilder, firPrimaryConstructorBuilder.getSymbol(), AbstractRawFirBuilder.ValueParameterDeclaration.PRIMARY_CONSTRUCTOR, null, null, 24, null);
            }
            firPrimaryConstructorBuilder.setBody(null);
            FirConstructor firConstructorMo288build = firPrimaryConstructorBuilder.mo288build();
            ConeClassLikeType coneClassLikeTypeCurrentDispatchReceiverType = psiRawFirBuilder.currentDispatchReceiverType();
            coneClassLikeTypeCurrentDispatchReceiverType.getClass();
            ClassMembersKt.setContainingClassForStaticMemberAttr(firConstructorMo288build, coneClassLikeTypeCurrentDispatchReceiverType.getLookupTag());
            psiRawFirBuilder.getContext().popContainerSymbol(firConstructorSymbol);
            return firConstructorMo288build;
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
        private final FirProperty toFirProperty(KtParameter ktParameter, FirValueParameter firValueParameter) throws Throwable {
            FirRegularPropertySymbol firRegularPropertySymbol;
            FirDefaultPropertySetter firDefaultPropertySetter;
            if (ktParameter.hasValOrVar()) {
                FirDeclarationStatusImpl firDeclarationStatusImpl = new FirDeclarationStatusImpl(PsiRawFirBuilder.getVisibility$default(PsiRawFirBuilder.this, ktParameter, false, 1, null), PsiRawFirBuilder.this.getModality(ktParameter));
                firDeclarationStatusImpl.setExpect(PsiUtilsKt.hasExpectModifier(ktParameter) || PsiRawFirBuilder.this.getContext().getContainerIsExpect());
                firDeclarationStatusImpl.setActual(PsiUtilsKt.hasActualModifier(ktParameter));
                firDeclarationStatusImpl.setOverride(ktParameter.hasModifier(KtTokens.OVERRIDE_KEYWORD));
                firDeclarationStatusImpl.setConst(ktParameter.hasModifier(KtTokens.CONST_KEYWORD));
                firDeclarationStatusImpl.setLateInit(ktParameter.hasModifier(KtTokens.LATEINIT_KEYWORD));
                firDeclarationStatusImpl.setExternal(ktParameter.hasModifier(KtTokens.EXTERNAL_KEYWORD));
                Name nameAsSafeName = ktParameter.getNameAsSafeName();
                nameAsSafeName.getClass();
                FirRegularPropertySymbol firRegularPropertySymbol2 = new FirRegularPropertySymbol(PsiRawFirBuilder.this.callableIdForName(nameAsSafeName));
                PsiRawFirBuilder psiRawFirBuilder = PsiRawFirBuilder.this;
                psiRawFirBuilder.getContext().pushContainerSymbol(firRegularPropertySymbol2);
                try {
                    KtSourceElement firSourceElement = psiRawFirBuilder.toFirSourceElement((PsiElement) ktParameter, KtFakeSourceElementKind.PropertyFromParameter.INSTANCE);
                    ArrayList arrayList = new ArrayList();
                    for (KtAnnotationEntry ktAnnotationEntry : ktParameter.getAnnotationEntries()) {
                        ktAnnotationEntry.getClass();
                        FirElement firElementConvertElement = convertElement(ktAnnotationEntry, null);
                        if (firElementConvertElement != null) {
                            FirAnnotationCall firAnnotationCallMo288build = (FirAnnotationCall) firElementConvertElement;
                            if (firAnnotationCallMo288build instanceof FirErrorAnnotationCall) {
                                FirAnnotationCallBuilder firAnnotationCallBuilder = new FirAnnotationCallBuilder();
                                firAnnotationCallBuilder.setSource(firAnnotationCallMo288build.getSource());
                                firAnnotationCallBuilder.setUseSiteTarget(firAnnotationCallMo288build.getUseSiteTarget());
                                firAnnotationCallBuilder.setAnnotationTypeRef(firAnnotationCallMo288build.getAnnotationTypeRef());
                                firAnnotationCallBuilder.getTypeArguments().addAll(firAnnotationCallMo288build.getTypeArguments());
                                firAnnotationCallBuilder.setArgumentList(firAnnotationCallMo288build.getArgumentList());
                                firAnnotationCallBuilder.setCalleeReference(firAnnotationCallMo288build.getCalleeReference());
                                firAnnotationCallBuilder.setArgumentMapping(firAnnotationCallMo288build.getArgumentMapping());
                                firAnnotationCallBuilder.setAnnotationResolvePhase(firAnnotationCallMo288build.getAnnotationResolvePhase());
                                firAnnotationCallBuilder.setContainingDeclarationSymbol(firAnnotationCallMo288build.getContainingDeclarationSymbol());
                                firAnnotationCallMo288build = firAnnotationCallBuilder.mo288build();
                            }
                            arrayList.add(firAnnotationCallMo288build);
                        } else {
                            throw new NullPointerException("null cannot be cast to non-null type org.jetbrains.kotlin.fir.expressions.FirAnnotationCall");
                        }
                    }
                    FirPropertyBuilder firPropertyBuilder = new FirPropertyBuilder();
                    firPropertyBuilder.setSource(firSourceElement);
                    firPropertyBuilder.setModuleData(psiRawFirBuilder.getBaseModuleData());
                    FirDeclarationOrigin.Source source = FirDeclarationOrigin.Source.INSTANCE;
                    firPropertyBuilder.setOrigin(source);
                    firPropertyBuilder.setReturnTypeRef(UtilsKt.copyWithNewSourceKind(firValueParameter.getReturnTypeRef(), KtFakeSourceElementKind.PropertyFromParameter.INSTANCE));
                    firPropertyBuilder.setName(nameAsSafeName);
                    FirPropertyAccessExpressionBuilder firPropertyAccessExpressionBuilder = new FirPropertyAccessExpressionBuilder();
                    firPropertyAccessExpressionBuilder.setSource(firSourceElement);
                    FirPropertyFromParameterResolvedNamedReferenceBuilder firPropertyFromParameterResolvedNamedReferenceBuilder = new FirPropertyFromParameterResolvedNamedReferenceBuilder();
                    firPropertyFromParameterResolvedNamedReferenceBuilder.setSource(firSourceElement);
                    firPropertyFromParameterResolvedNamedReferenceBuilder.setName(nameAsSafeName);
                    firPropertyFromParameterResolvedNamedReferenceBuilder.setResolvedSymbol(firValueParameter.getSymbol());
                    firPropertyAccessExpressionBuilder.setCalleeReference(firPropertyFromParameterResolvedNamedReferenceBuilder.build());
                    firPropertyBuilder.setInitializer(firPropertyAccessExpressionBuilder.mo288build());
                    firPropertyBuilder.setVar(ktParameter.isMutable());
                    firPropertyBuilder.setSymbol(firRegularPropertySymbol2);
                    KtSourceElement ktSourceElementFakeElement$default = KtSourceElementKt.fakeElement$default(firSourceElement, KtFakeSourceElementKind.DefaultAccessor.INSTANCE, null, 2, null);
                    FirModuleData baseModuleData = psiRawFirBuilder.getBaseModuleData();
                    ArrayList arrayList2 = new ArrayList();
                    for (Object obj : arrayList) {
                        FirAnnotationCall firAnnotationCall = (FirAnnotationCall) obj;
                        if (firAnnotationCall.getUseSiteTarget() == AnnotationUseSiteTarget.FIELD || firAnnotationCall.getUseSiteTarget() == AnnotationUseSiteTarget.PROPERTY_DELEGATE_FIELD) {
                            arrayList2.add(obj);
                        }
                    }
                    List mutableList = CollectionsKt.toMutableList(arrayList2);
                    FirTypeRef returnTypeRef = firPropertyBuilder.getReturnTypeRef();
                    KtFakeSourceElementKind.DefaultAccessor defaultAccessor = KtFakeSourceElementKind.DefaultAccessor.INSTANCE;
                    try {
                        firPropertyBuilder.setBackingField(new FirDefaultPropertyBackingField(baseModuleData, source, ktSourceElementFakeElement$default, mutableList, UtilsKt.copyWithNewSourceKind(returnTypeRef, defaultAccessor), firPropertyBuilder.isVar(), firPropertyBuilder.getSymbol(), UtilsKt.copy(firDeclarationStatusImpl, (8388575 & 1) != 0 ? firDeclarationStatusImpl.getVisibility() : null, (8388575 & 2) != 0 ? firDeclarationStatusImpl.getModality() : null, (8388575 & 4) != 0 ? firDeclarationStatusImpl.isExpect() : false, (8388575 & 8) != 0 ? firDeclarationStatusImpl.isActual() : false, (8388575 & 16) != 0 ? firDeclarationStatusImpl.isOverride() : false, (8388575 & 32) != 0 ? firDeclarationStatusImpl.isOperator() : false, (8388575 & 64) != 0 ? firDeclarationStatusImpl.isInfix() : false, (8388575 & 128) != 0 ? firDeclarationStatusImpl.isInline() : false, (8388575 & 256) != 0 ? firDeclarationStatusImpl.isValue() : false, (8388575 & 512) != 0 ? firDeclarationStatusImpl.isTailRec() : false, (8388575 & 1024) != 0 ? firDeclarationStatusImpl.isExternal() : false, (8388575 & 2048) != 0 ? firDeclarationStatusImpl.isConst() : false, (8388575 & 4096) != 0 ? firDeclarationStatusImpl.isLateInit() : false, (8388575 & 8192) != 0 ? firDeclarationStatusImpl.isInner() : false, (8388575 & 16384) != 0 ? firDeclarationStatusImpl.isCompanion() : false, (8388575 & 32768) != 0 ? firDeclarationStatusImpl.isData() : false, (8388575 & 65536) != 0 ? firDeclarationStatusImpl.isSuspend() : false, (8388575 & 131072) != 0 ? firDeclarationStatusImpl.isStatic() : false, (8388575 & 262144) != 0 ? firDeclarationStatusImpl.isFromSealedClass() : false, (8388575 & 524288) != 0 ? firDeclarationStatusImpl.isFromEnumClass() : false, (8388575 & 1048576) != 0 ? firDeclarationStatusImpl.isFun() : false, (8388575 & 2097152) != 0 ? firDeclarationStatusImpl.getHasStableParameterNames() : false, (8388575 & 4194304) != 0 ? firDeclarationStatusImpl.getReturnValueStatus() : null), null, 256, null));
                        firPropertyBuilder.setStatus(firDeclarationStatusImpl);
                        firPropertyBuilder.setLocal(psiRawFirBuilder.getContext().getInLocalContext());
                        FirModuleData baseModuleData2 = psiRawFirBuilder.getBaseModuleData();
                        FirDeclarationOrigin.Source source2 = FirDeclarationOrigin.Source.INSTANCE;
                        FirTypeRef firTypeRefCopyWithNewSourceKind = UtilsKt.copyWithNewSourceKind(firPropertyBuilder.getReturnTypeRef(), defaultAccessor);
                        Visibility visibility = firDeclarationStatusImpl.getVisibility();
                        FirPropertySymbol symbol = firPropertyBuilder.getSymbol();
                        Modality modality = firDeclarationStatusImpl.getModality();
                        KtModifierKeywordToken ktModifierKeywordToken = KtTokens.INLINE_KEYWORD;
                        FirDefaultPropertyGetter firDefaultPropertyGetter = new FirDefaultPropertyGetter(ktSourceElementFakeElement$default, baseModuleData2, source2, firTypeRefCopyWithNewSourceKind, visibility, symbol, modality, null, ktParameter.hasModifier(ktModifierKeywordToken), false, null, null, null, 7808, null);
                        psiRawFirBuilder = psiRawFirBuilder;
                        try {
                            psiRawFirBuilder.initContainingClassAttr(firDefaultPropertyGetter);
                            firDefaultPropertyGetter.replaceAnnotations(ConversionUtilsKt.filterUseSiteTarget(arrayList, AnnotationUseSiteTarget.PROPERTY_GETTER));
                            firPropertyBuilder.setGetter(firDefaultPropertyGetter);
                            if (ktParameter.isMutable()) {
                                FirDefaultPropertySetter firDefaultPropertySetter2 = new FirDefaultPropertySetter(ktSourceElementFakeElement$default, psiRawFirBuilder.getBaseModuleData(), source2, UtilsKt.copyWithNewSourceKind(firPropertyBuilder.getReturnTypeRef(), defaultAccessor), firDeclarationStatusImpl.getVisibility(), firPropertyBuilder.getSymbol(), firDeclarationStatusImpl.getModality(), null, ktParameter.hasModifier(ktModifierKeywordToken), false, null, null, ConversionUtilsKt.filterUseSiteTarget(arrayList, AnnotationUseSiteTarget.SETTER_PARAMETER), null, null, 28288, null);
                                psiRawFirBuilder.initContainingClassAttr(firDefaultPropertySetter2);
                                firDefaultPropertySetter2.replaceAnnotations(ConversionUtilsKt.filterUseSiteTarget(arrayList, AnnotationUseSiteTarget.PROPERTY_SETTER));
                                firDefaultPropertySetter = firDefaultPropertySetter2;
                            } else {
                                firDefaultPropertySetter = null;
                            }
                            firPropertyBuilder.setSetter(firDefaultPropertySetter);
                            CollectionsKt.addAll(firPropertyBuilder.getAnnotations(), AbstractRawFirBuilderKt.filterConstructorPropertyRelevantAnnotations(arrayList, ktParameter.isMutable()));
                            firPropertyBuilder.setDispatchReceiverType(psiRawFirBuilder.currentDispatchReceiverType());
                            FirProperty firPropertyMo288build = firPropertyBuilder.mo288build();
                            if (firValueParameter.getIsVararg()) {
                                DeclarationAttributesKt.setFromVararg(firPropertyMo288build, Boolean.TRUE);
                            }
                            ClassMembersKt.setCorrespondingProperty(firValueParameter, firPropertyMo288build);
                            DeclarationAttributesKt.setFromPrimaryConstructor(firPropertyMo288build, Boolean.TRUE);
                            psiRawFirBuilder.getContext().popContainerSymbol(firRegularPropertySymbol2);
                            return firPropertyMo288build;
                        } catch (Throwable th) {
                            th = th;
                            firRegularPropertySymbol = firRegularPropertySymbol2;
                            psiRawFirBuilder.getContext().popContainerSymbol(firRegularPropertySymbol);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        psiRawFirBuilder = psiRawFirBuilder;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    firRegularPropertySymbol = firRegularPropertySymbol2;
                }
            } else {
                w01.a("Failed requirement.");
                return null;
            }
        }
    }
}
