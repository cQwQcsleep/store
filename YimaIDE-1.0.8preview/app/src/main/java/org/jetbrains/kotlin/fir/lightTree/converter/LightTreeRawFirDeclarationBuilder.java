package org.jetbrains.kotlin.fir.lightTree.converter;

import com.intellij.lang.LighterASTNode;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.diff.FlyweightCapableTreeStructure;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.UninitializedPropertyAccessException;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.kotlin.ElementTypeUtils;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtLightSourceElement;
import org.jetbrains.kotlin.KtNodeTypes;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.KtSourceFile;
import org.jetbrains.kotlin.KtSourceFileLinesMapping;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.state.InlineClassManglingUtilsKt;
import org.jetbrains.kotlin.config.AnalysisFlags;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.descriptors.annotations.AnnotationUseSiteTarget;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.CompanionBlockInfo;
import org.jetbrains.kotlin.fir.EnumClassUtilsKt;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirFunctionTarget;
import org.jetbrains.kotlin.fir.FirFunctionTypeParameter;
import org.jetbrains.kotlin.fir.FirGenerationKt;
import org.jetbrains.kotlin.fir.FirLabel;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirPackageDirective;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.builder.AbstractRawFirBuilder;
import org.jetbrains.kotlin.fir.builder.CompanionBlockCollector;
import org.jetbrains.kotlin.fir.builder.Context;
import org.jetbrains.kotlin.fir.builder.ConversionUtilsKt;
import org.jetbrains.kotlin.fir.builder.FirFunctionTypeParameterBuilder;
import org.jetbrains.kotlin.fir.builder.FirPackageDirectiveBuilder;
import org.jetbrains.kotlin.fir.contracts.FirContractDescription;
import org.jetbrains.kotlin.fir.contracts.builder.FirRawContractDescriptionBuilder;
import org.jetbrains.kotlin.fir.declarations.DelegateFieldsMapKt;
import org.jetbrains.kotlin.fir.declarations.DestructuringDeclarationAttributesKt;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousInitializer;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousObject;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
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
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirReplSnippet;
import org.jetbrains.kotlin.fir.declarations.FirResolvedDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirScript;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameterKind;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.declarations.builder.FirAbstractConstructorBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirAnonymousFunctionBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirAnonymousInitializerBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirAnonymousObjectBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirBackingFieldBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirConstructorBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirDanglingModifierListBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirDefaultSetterValueParameterBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirEnumEntryBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirFieldBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirFileBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirImportBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirNamedFunctionBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirPrimaryConstructorBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirPropertyAccessorBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirRegularClassBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirReplSnippetBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirScriptBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirTypeAliasBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirTypeParameterBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirValueParameterBuilder;
import org.jetbrains.kotlin.fir.declarations.impl.FirDeclarationStatusImpl;
import org.jetbrains.kotlin.fir.declarations.impl.FirDefaultPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.impl.FirDefaultPropertyBackingField;
import org.jetbrains.kotlin.fir.declarations.impl.FirResolvedDeclarationStatusImpl;
import org.jetbrains.kotlin.fir.declarations.utils.DanglingTypeConstraint;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationBuildingUtilsKt;
import org.jetbrains.kotlin.fir.diagnostics.ConeContractMayNotHaveLabel;
import org.jetbrains.kotlin.fir.diagnostics.ConeContractShouldBeFirstStatement;
import org.jetbrains.kotlin.fir.diagnostics.ConeDanglingModifierOnTopLevel;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.ConeNoConstructorError;
import org.jetbrains.kotlin.fir.diagnostics.ConeSimpleDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.ConeSyntaxDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.DiagnosticKind;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousObjectExpression;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.builder.FirAnnotationCallBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirAnonymousFunctionExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirAnonymousObjectExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirBlockBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirDelegatedConstructorCallBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirEmptyExpressionBlockBuilderKt;
import org.jetbrains.kotlin.fir.expressions.builder.FirErrorAnnotationCallBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirMultiDelegatedConstructorCallBuilder;
import org.jetbrains.kotlin.fir.expressions.impl.FirSingleExpressionBlock;
import org.jetbrains.kotlin.fir.lightTree.converter.LightTreeRawFirDeclarationBuilder;
import org.jetbrains.kotlin.fir.lightTree.fir.ClassWrapper;
import org.jetbrains.kotlin.fir.lightTree.fir.DelegatedConstructorWrapper;
import org.jetbrains.kotlin.fir.lightTree.fir.DestructuringDeclaration;
import org.jetbrains.kotlin.fir.lightTree.fir.DestructuringDeclarationKt;
import org.jetbrains.kotlin.fir.lightTree.fir.DestructuringEntry;
import org.jetbrains.kotlin.fir.lightTree.fir.PrimaryConstructor;
import org.jetbrains.kotlin.fir.lightTree.fir.TypeConstraint;
import org.jetbrains.kotlin.fir.lightTree.fir.ValueParameter;
import org.jetbrains.kotlin.fir.lightTree.fir.modifier.ModifierList;
import org.jetbrains.kotlin.fir.lightTree.fir.modifier.TypeParameterModifierList;
import org.jetbrains.kotlin.fir.lightTree.fir.modifier.TypeProjectionModifierList;
import org.jetbrains.kotlin.fir.references.FirNamedReference;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.references.builder.FirExplicitSuperReferenceBuilder;
import org.jetbrains.kotlin.fir.references.builder.FirExplicitThisReferenceBuilder;
import org.jetbrains.kotlin.fir.references.builder.FirSimpleNamedReferenceBuilder;
import org.jetbrains.kotlin.fir.scopes.FirScopeProvider;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousInitializerSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousObjectSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirBackingFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirDanglingModifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirEnumEntrySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFileSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertyAccessorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirScriptSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.FirImplicitTypeRef;
import org.jetbrains.kotlin.fir.types.FirQualifierPart;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeArgumentList;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
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
import org.jetbrains.kotlin.fir.types.impl.FirImplicitBuiltinTypeRef;
import org.jetbrains.kotlin.fir.types.impl.FirImplicitTypeRefImplWithoutSource;
import org.jetbrains.kotlin.fir.types.impl.FirQualifierPartImpl;
import org.jetbrains.kotlin.fir.types.impl.FirTypeArgumentListImpl;
import org.jetbrains.kotlin.lexer.KtModifierKeywordToken;
import org.jetbrains.kotlin.lexer.KtToken;
import org.jetbrains.kotlin.lexer.KtTokens;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.NameUtils;
import org.jetbrains.kotlin.name.SpecialNames;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.psi.stubs.elements.KtStubElementTypes;
import org.jetbrains.kotlin.util.LightTreeUtilsKt;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000°\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010%\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0002\u0095\u0002B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\n¢\u0006\u0004\b\u000b\u0010\fJ\u001e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\u0018\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\b2\b\b\u0002\u0010\u001d\u001a\u00020\u0012J$\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u001c\u001a\u00020\b2\n\b\u0002\u0010 \u001a\u0004\u0018\u00010!2\b\b\u0002\u0010\u001d\u001a\u00020\u0012J\u0010\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\bH\u0002J\u0010\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\bH\u0002J\u0016\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)2\u0006\u0010'\u001a\u00020\bH\u0002J\u001e\u0010+\u001a\u0010\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020-\u0018\u00010,2\u0006\u0010.\u001a\u00020\bH\u0002J\u0010\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020\bH\u0002J\u001a\u00102\u001a\u000203*\b\u0012\u0004\u0012\u00020*042\u0006\u00105\u001a\u00020\bH\u0002J\u0016\u00106\u001a\b\u0012\u0004\u0012\u0002000)2\u0006\u00107\u001a\u00020\bH\u0002J\u001a\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020\b2\b\b\u0002\u0010;\u001a\u00020\u0012H\u0002J\u0010\u0010<\u001a\u00020=2\u0006\u0010:\u001a\u00020\bH\u0002J\u0010\u0010>\u001a\u00020?2\u0006\u0010:\u001a\u00020\bH\u0002J\u001e\u0010@\u001a\u000203*\u0002092\u0006\u0010A\u001a\u00020\b2\b\b\u0002\u0010;\u001a\u00020\u0012H\u0002J \u0010B\u001a\u0002032\u0006\u0010A\u001a\u00020\b2\u000e\u0010C\u001a\n\u0012\u0006\b\u0000\u0012\u00020D04H\u0002J\u001c\u0010E\u001a\u000203*\u0002092\u000e\u0010C\u001a\n\u0012\u0006\b\u0000\u0012\u00020D04H\u0002J\u0012\u0010F\u001a\b\u0012\u0004\u0012\u00020D0)*\u000209H\u0002J \u0010G\u001a\u0002032\u0006\u0010'\u001a\u00020\b2\u000e\u0010C\u001a\n\u0012\u0006\b\u0000\u0012\u00020D04H\u0002J\u001e\u0010H\u001a\u0002032\u0006\u0010'\u001a\u00020\b2\u000e\u0010C\u001a\n\u0012\u0006\b\u0000\u0012\u00020D04J\u0010\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020\bH\u0002J&\u0010L\u001a\u00020D2\u0006\u0010M\u001a\u00020\b2\n\b\u0002\u0010N\u001a\u0004\u0018\u00010J2\n\b\u0002\u0010O\u001a\u0004\u0018\u00010PJ\f\u0010Q\u001a\u00020\u0012*\u00020\bH\u0002J\u000e\u0010R\u001a\u00020S2\u0006\u0010T\u001a\u00020\bJ\u000e\u0010U\u001a\u00020V2\u0006\u0010W\u001a\u00020\bJ\u0018\u0010X\u001a\u00020Y2\u0006\u0010Z\u001a\u00020\b2\u0006\u0010[\u001a\u00020\\H\u0002J\u0016\u0010]\u001a\b\u0012\u0004\u0012\u00020^0)2\u0006\u0010_\u001a\u00020\bH\u0002J \u0010`\u001a\b\u0012\u0004\u0012\u00020a0)2\u0006\u0010b\u001a\u00020\b2\b\u0010[\u001a\u0004\u0018\u00010\\H\u0002J6\u0010c\u001a\u0002032\u0006\u0010'\u001a\u00020\b2\f\u0010d\u001a\b\u0012\u0004\u0012\u00020a042\b\u0010[\u001a\u0004\u0018\u00010\\2\f\u0010e\u001a\b\u0012\u0004\u0012\u00020\b04H\u0002J$\u0010f\u001a\u0002032\f\u0010e\u001a\b\u0012\u0004\u0012\u00020\b042\f\u0010g\u001a\b\u0012\u0004\u0012\u00020a04H\u0002J\u0010\u0010h\u001a\u00020^2\u0006\u0010i\u001a\u00020\bH\u0002J\u0010\u0010j\u001a\u00020k2\u0006\u0010'\u001a\u00020\bH\u0002JV\u0010l\u001a\u0004\u0018\u00010m2\b\u0010n\u001a\u0004\u0018\u00010\b2\b\u0010o\u001a\u0004\u0018\u00010-2\u0006\u0010[\u001a\u00020\\2\b\u0010p\u001a\u0004\u0018\u00010q2\b\b\u0002\u0010r\u001a\u00020\u00122\u0006\u0010s\u001a\u00020\u00122\b\b\u0002\u0010t\u001a\u00020\u00122\b\b\u0002\u0010u\u001a\u00020\u0012H\u0002J(\u0010v\u001a\u0004\u0018\u00010w2\u0006\u0010x\u001a\u00020\b2\n\u0010y\u001a\u0006\u0012\u0002\b\u00030z2\b\b\u0002\u0010{\u001a\u00020\u0012H\u0002J&\u0010|\u001a\u00020w2\u0006\u0010}\u001a\u00020\b2\n\u0010y\u001a\u0006\u0012\u0002\b\u00030z2\b\b\u0002\u0010{\u001a\u00020\u0012H\u0002JC\u0010~\u001a\u00020w2\u0006\u0010x\u001a\u00020\b2\n\u0010y\u001a\u0006\u0012\u0002\b\u00030z2\u0006\u0010{\u001a\u00020\u00122\u001c\u0010\u007f\u001a\u0018\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u0081\u000104\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u0080\u0001H\u0082\bJ\u001b\u0010\u0082\u0001\u001a\u00030\u0083\u00012\u0007\u0010\u0084\u0001\u001a\u00020\b2\u0006\u0010[\u001a\u00020\\H\u0002J\u0010\u0010\u0085\u0001\u001a\u0005\u0018\u00010\u0086\u0001*\u00020\\H\u0002J&\u0010\u0087\u0001\u001a\u0005\u0018\u00010\u0088\u00012\u0007\u0010\u0089\u0001\u001a\u00020\b2\u0006\u0010[\u001a\u00020\\2\u0007\u0010\u008a\u0001\u001a\u00020\u0012H\u0002J\u0011\u0010\u008b\u0001\u001a\u00030\u008c\u00012\u0007\u0010\u008d\u0001\u001a\u00020\bJ\u001d\u0010\u008e\u0001\u001a\u00030\u008f\u00012\u0007\u0010\u0090\u0001\u001a\u00020\b2\n\b\u0002\u0010[\u001a\u0004\u0018\u00010\\J\u0019\u0010\u0091\u0001\u001a\u00030\u0092\u00012\u0007\u0010\u0093\u0001\u001a\u00020\bH\u0000¢\u0006\u0003\b\u0094\u0001J\u001c\u0010\u0095\u0001\u001a\u00030\u0096\u00012\u0007\u0010\u0097\u0001\u001a\u00020\b2\u0007\u0010\u0098\u0001\u001a\u00020\u0012H\u0002JR\u0010\u0099\u0001\u001a\u00030\u009a\u00012\u0007\u0010\u009b\u0001\u001a\u00020\b2\b\u0010\u009c\u0001\u001a\u00030\u009d\u00012\b\u0010\u009e\u0001\u001a\u00030\u009f\u00012\b\u0010 \u0001\u001a\u00030¡\u00012\u0007\u0010¢\u0001\u001a\u0002092\r\u0010£\u0001\u001a\b\u0012\u0004\u0012\u00020D0)2\u0007\u0010¤\u0001\u001a\u00020\u0012H\u0002JN\u0010¥\u0001\u001a\u00030¦\u0001*\u0004\u0018\u00010\b2\b\u0010 \u0001\u001a\u00030¡\u00012\u0007\u0010¢\u0001\u001a\u0002092\b\u0010§\u0001\u001a\u00030\u009d\u00012\u0007\u0010\u0098\u0001\u001a\u00020\u00122\r\u0010¨\u0001\u001a\b\u0012\u0004\u0012\u00020D0)2\u0007\u0010\u0090\u0001\u001a\u00020\bH\u0002J%\u0010©\u0001\u001a\u00030ª\u00012\b\u0010«\u0001\u001a\u00030\u009f\u00012\u0006\u0010:\u001a\u0002092\u0007\u0010¢\u0001\u001a\u000209H\u0002J\u0013\u0010¬\u0001\u001a\u00030\u00ad\u00012\u0007\u0010®\u0001\u001a\u00020\bH\u0002J!\u0010¯\u0001\u001a\u0002032\u0007\u0010®\u0001\u001a\u00020\b2\r\u0010°\u0001\u001a\b\u0012\u0004\u0012\u00020^04H\u0002J;\u0010±\u0001\u001a\u00030²\u00012\u0007\u0010³\u0001\u001a\u00020\b2\f\u0010´\u0001\u001a\u0007\u0012\u0002\b\u00030µ\u00012\b\u0010\u009c\u0001\u001a\u00030\u009d\u00012\u000e\u0010¶\u0001\u001a\t\u0012\u0005\u0012\u00030\u0081\u00010)H\u0002J\u0011\u0010·\u0001\u001a\u00030¸\u00012\u0007\u0010¹\u0001\u001a\u00020\bJ8\u0010º\u0001\u001a\u0013\u0012\u0006\u0012\u0004\u0018\u00010\u001b\u0012\u0007\u0012\u0005\u0018\u00010\u00ad\u00010,2\t\u0010»\u0001\u001a\u0004\u0018\u00010\b2\b\u00105\u001a\u0004\u0018\u00010\b2\u0007\u0010¼\u0001\u001a\u00020\u0012H\u0002J\u0012\u0010½\u0001\u001a\u00020\u00122\u0007\u0010¾\u0001\u001a\u00020-H\u0002J\u0012\u0010¿\u0001\u001a\u00020\u00122\u0007\u0010¾\u0001\u001a\u00020-H\u0002J\u001b\u0010À\u0001\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\b2\b\b\u0002\u0010\u001d\u001a\u00020\u0012J\u0013\u0010Á\u0001\u001a\u00030Â\u00012\u0007\u0010Ã\u0001\u001a\u00020\bH\u0002J%\u0010Ä\u0001\u001a\u0015\u0012\u0005\u0012\u00030\u009d\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020^0)0,2\u0007\u0010Å\u0001\u001a\u00020\bH\u0002J5\u0010Æ\u0001\u001a\u00030\u009d\u00012\u0007\u0010Ç\u0001\u001a\u00020\b2\u0016\u0010È\u0001\u001a\u0011\u0012\u0005\u0012\u00030Ê\u0001\u0012\u0005\u0012\u00030Ë\u00010É\u00012\b\u0010Ì\u0001\u001a\u00030Ê\u0001H\u0002J5\u0010Í\u0001\u001a\t\u0012\u0005\u0012\u00030Î\u00010)2\u0007\u0010Ï\u0001\u001a\u00020\b2\u000e\u0010Ð\u0001\u001a\t\u0012\u0005\u0012\u00030Ñ\u00010)2\n\u0010y\u001a\u0006\u0012\u0002\b\u00030zH\u0002J\u0019\u0010Ò\u0001\u001a\t\u0012\u0005\u0012\u00030Ñ\u00010)2\u0007\u0010Ð\u0001\u001a\u00020\bH\u0002J\u0013\u0010Ó\u0001\u001a\u00030Ñ\u00012\u0007\u0010Ô\u0001\u001a\u00020\bH\u0002J0\u0010Õ\u0001\u001a\u00030Î\u00012\u0007\u0010Ö\u0001\u001a\u00020\b2\u000e\u0010Ð\u0001\u001a\t\u0012\u0005\u0012\u00030Ñ\u00010)2\u000b\u0010×\u0001\u001a\u0006\u0012\u0002\b\u00030zH\u0002J\u0011\u0010Ø\u0001\u001a\u00030\u009d\u00012\u0007\u0010Ù\u0001\u001a\u00020\bJ%\u0010Ú\u0001\u001a\u00030\u009d\u00012\u0007\u0010Û\u0001\u001a\u00020-2\u0007\u0010Ü\u0001\u001a\u00020\b2\u0007\u0010Ý\u0001\u001a\u00020\u0012H\u0002J\u0013\u0010Þ\u0001\u001a\u00030\u009d\u00012\u0007\u0010ß\u0001\u001a\u00020\bH\u0002J6\u0010à\u0001\u001a\u00030\u009d\u00012\u0007\u0010Û\u0001\u001a\u00020-2\u0007\u0010á\u0001\u001a\u00020\b2\r\u0010â\u0001\u001a\b\u0012\u0004\u0012\u000209042\t\b\u0002\u0010Ý\u0001\u001a\u00020\u0012H\u0002J'\u0010ã\u0001\u001a\u00030\u009d\u00012\u0007\u0010Û\u0001\u001a\u00020-2\u0007\u0010ä\u0001\u001a\u00020\b2\t\b\u0002\u0010Ý\u0001\u001a\u00020\u0012H\u0002J \u0010å\u0001\u001a\t\u0012\u0005\u0012\u00030æ\u00010)2\u0007\u0010ç\u0001\u001a\u00020\b2\u0007\u0010è\u0001\u001a\u00020\u0012J\u001c\u0010é\u0001\u001a\u00030æ\u00012\u0007\u0010ê\u0001\u001a\u00020\b2\u0007\u0010è\u0001\u001a\u00020\u0012H\u0002J6\u0010î\u0001\u001a\u00030\u009d\u00012\u0007\u0010Û\u0001\u001a\u00020-2\u0007\u0010ï\u0001\u001a\u00020\b2\r\u0010â\u0001\u001a\b\u0012\u0004\u0012\u0002090)2\t\b\u0002\u0010Ý\u0001\u001a\u00020\u0012H\u0002J\u0019\u0010ð\u0001\u001a\t\u0012\u0005\u0012\u00030ñ\u00010)2\u0007\u0010ò\u0001\u001a\u00020\bH\u0002JA\u0010ó\u0001\u001a\t\u0012\u0005\u0012\u00030ô\u00010)2\u0007\u0010õ\u0001\u001a\u00020\b2\f\u0010´\u0001\u001a\u0007\u0012\u0002\b\u00030µ\u00012\b\u0010ö\u0001\u001a\u00030÷\u00012\u0010\b\u0002\u0010¶\u0001\u001a\t\u0012\u0005\u0012\u00030\u0081\u00010)J;\u0010ø\u0001\u001a\u00030ô\u00012\u0007\u0010ù\u0001\u001a\u00020\b2\f\u0010y\u001a\b\u0012\u0002\b\u0003\u0018\u00010z2\b\u0010ö\u0001\u001a\u00030÷\u00012\u0010\b\u0002\u0010¶\u0001\u001a\t\u0012\u0005\u0012\u00030\u0081\u00010)JI\u0010ú\u0001\u001a\u000203\"\u000e\b\u0000\u0010û\u0001*\u00020a*\u00030ü\u00012\u000e\u0010ý\u0001\u001a\t\u0012\u0005\u0012\u00030Î\u00010)2\u000e\u0010Ð\u0001\u001a\t\u0012\u0005\u0012\u00030Ñ\u00010)2\b\u0010þ\u0001\u001a\u0003Hû\u0001H\u0002¢\u0006\u0003\u0010ÿ\u0001J1\u0010\u0080\u0002\u001a\u000203*\t\u0012\u0005\u0012\u00030²\u0001042\u000f\u0010\u0081\u0002\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010)2\n\u0010y\u001a\u0006\u0012\u0002\b\u00030zH\u0002JB\u0010\u0086\u0002\u001a\u00030\u0087\u00022\u0007\u0010\u0088\u0002\u001a\u00020\b2\u0007\u0010\u0089\u0002\u001a\u00020-2\u0007\u0010\u008a\u0002\u001a\u00020*2\u001b\u0010\u008b\u0002\u001a\u0016\u0012\u0005\u0012\u00030\u008c\u0002\u0012\u0004\u0012\u0002030\u0080\u0001¢\u0006\u0003\b\u008d\u0002H\u0014J\u0081\u0001\u0010\u008e\u0002\u001a\u00030\u008f\u00022\u0007\u0010\u0088\u0002\u001a\u00020\b2\u0007\u0010\u0089\u0002\u001a\u00020-2\u0007\u0010\u008a\u0002\u001a\u00020*2\u001b\u0010\u0090\u0002\u001a\u0016\u0012\u0005\u0012\u00030\u0091\u0002\u0012\u0004\u0012\u0002030\u0080\u0001¢\u0006\u0003\b\u008d\u00022\u001a\u0010\u0092\u0002\u001a\u0015\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u0002030\u0080\u0001¢\u0006\u0003\b\u008d\u00022!\u0010\u0093\u0002\u001a\u001c\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u0094\u000204\u0012\u0004\u0012\u0002030\u0080\u0001¢\u0006\u0003\b\u008d\u0002H\u0014R\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0019\u0010ë\u0001\u001a\u00020\u0012*\u00030ì\u00018F¢\u0006\b\u001a\u0006\bë\u0001\u0010í\u0001R!\u0010\u0082\u0002\u001a\u0014\u0012\u000f\u0012\r \u0085\u0002*\u0005\u0018\u00010\u0084\u00020\u0084\u00020\u0083\u0002X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0096\u0002"}, d2 = {"Lorg/jetbrains/kotlin/fir/lightTree/converter/LightTreeRawFirDeclarationBuilder;", "Lorg/jetbrains/kotlin/fir/lightTree/converter/AbstractLightTreeRawFirBuilder;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "baseScopeProvider", "Lorg/jetbrains/kotlin/fir/scopes/FirScopeProvider;", "tree", "Lcom/intellij/util/diff/FlyweightCapableTreeStructure;", "Lcom/intellij/lang/LighterASTNode;", "context", "Lorg/jetbrains/kotlin/fir/builder/Context;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/scopes/FirScopeProvider;Lcom/intellij/util/diff/FlyweightCapableTreeStructure;Lorg/jetbrains/kotlin/fir/builder/Context;)V", "getBaseScopeProvider$org_jetbrains_kotlin_fir_light_tree2fir", "()Lorg/jetbrains/kotlin/fir/scopes/FirScopeProvider;", "expressionConverter", "Lorg/jetbrains/kotlin/fir/lightTree/converter/LightTreeRawFirExpressionBuilder;", "headerMode", Argument.Delimiters.none, "convertFile", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "file", "sourceFile", "Lorg/jetbrains/kotlin/KtSourceFile;", "linesMapping", "Lorg/jetbrains/kotlin/KtSourceFileLinesMapping;", "convertBlockExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "block", "convertOnlyFirstStatement", "convertBlockExpressionWithoutBuilding", "Lorg/jetbrains/kotlin/fir/expressions/builder/FirBlockBuilder;", "kind", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", "convertPackageDirective", "Lorg/jetbrains/kotlin/fir/FirPackageDirective;", "packageNode", "parsePackageName", "Lorg/jetbrains/kotlin/name/FqName;", "node", "parsePackageParts", Argument.Delimiters.none, Argument.Delimiters.none, "convertImportAlias", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/KtSourceElement;", "importAlias", "convertImportDirective", "Lorg/jetbrains/kotlin/fir/declarations/FirImport;", "importDirective", "collectSegments", Argument.Delimiters.none, Argument.Delimiters.none, "expression", "convertImportDirectives", "importList", "convertModifierList", "Lorg/jetbrains/kotlin/fir/lightTree/fir/modifier/ModifierList;", "modifiers", "isInClass", "convertTypeArgumentModifierList", "Lorg/jetbrains/kotlin/fir/lightTree/fir/modifier/TypeProjectionModifierList;", "convertTypeParameterModifiers", "Lorg/jetbrains/kotlin/fir/lightTree/fir/modifier/TypeParameterModifierList;", "consume", "modifierList", "convertAnnotationsOnlyTo", "list", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationCall;", "convertAnnotationsTo", "convertAnnotations", "convertAnnotationOrAnnotationEntryTo", "convertAnnotationTo", "convertAnnotationTarget", "Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationUseSiteTarget;", "annotationUseSiteTarget", "convertAnnotationEntry", "unescapedAnnotation", "defaultAnnotationUseSiteTarget", "diagnostic", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "hasValueParameters", "convertClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "classNode", "convertObjectLiteral", "Lorg/jetbrains/kotlin/fir/expressions/FirAnonymousObjectExpression;", "objectLiteral", "convertEnumEntry", "Lorg/jetbrains/kotlin/fir/declarations/FirEnumEntry;", "enumEntry", "classWrapper", "Lorg/jetbrains/kotlin/fir/lightTree/fir/ClassWrapper;", "convertInitializerList", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "initializerList", "convertClassBody", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "classBody", "convertDeclarationFromClassBody", "container", "modifierLists", "convertDanglingModifierListsInClassBody", "firDeclarations", "buildFirDestructuringDeclarationInitializer", "destructuringDeclaration", "buildErrorNonLocalDeclarationForDanglingModifierList", "Lorg/jetbrains/kotlin/fir/declarations/FirDanglingModifierList;", "convertPrimaryConstructor", "Lorg/jetbrains/kotlin/fir/lightTree/fir/PrimaryConstructor;", "primaryConstructor", "selfTypeSource", "delegatedConstructorSource", "Lorg/jetbrains/kotlin/KtLightSourceElement;", "isEnumEntry", "containingClassIsExpectClass", "isImplicitlyActual", "isKotlinAny", "convertAnonymousInitializer", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousInitializer;", "anonymousInitializer", "containingDeclarationSymbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "isLocal", "convertScriptInitializer", "scriptInitializer", "createAnonymousInitializer", "buildBlock", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "convertSecondaryConstructor", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "secondaryConstructor", "obtainDispatchReceiverForConstructor", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "convertConstructorDelegationCall", "Lorg/jetbrains/kotlin/fir/expressions/FirDelegatedConstructorCall;", "constructorDelegationCall", "isExpect", "convertTypeAlias", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;", "typeAlias", "convertPropertyDeclaration", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "property", "convertDestructingDeclaration", "Lorg/jetbrains/kotlin/fir/lightTree/fir/DestructuringDeclaration;", "destructingDeclaration", "convertDestructingDeclaration$org_jetbrains_kotlin_fir_light_tree2fir", "convertDestructingDeclarationEntry", "Lorg/jetbrains/kotlin/fir/lightTree/fir/DestructuringEntry;", "entry", "isVar", "convertGetterOrSetter", "Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;", "getterOrSetter", "propertyTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "propertyVisibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "propertySymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "propertyModifiers", "propertyAnnotations", "isCompanionBlockMember", "convertBackingField", "Lorg/jetbrains/kotlin/fir/declarations/FirBackingField;", "propertyReturnType", "annotationsFromProperty", "obtainPropertyComponentStatus", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;", "componentVisibility", "obtainContractDescription", "Lorg/jetbrains/kotlin/fir/contracts/FirContractDescription;", "rawContractDescription", "extractRawEffects", "destination", "convertSetterParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "setterParameter", "functionSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;", "additionalAnnotations", "convertFunctionDeclaration", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "functionDeclaration", "convertFunctionBody", "blockNode", "allowLegacyContractDescription", "isCallTheFirstStatement", "sourceElement", "functionCallHasLabel", "convertBlock", "convertDelegationSpecifiers", "Lorg/jetbrains/kotlin/fir/lightTree/converter/LightTreeRawFirDeclarationBuilder$DelegationSpecifiers;", "delegationSpecifiers", "convertConstructorInvocation", "constructorInvocation", "convertExplicitDelegation", "explicitDelegation", "delegateFieldsMap", Argument.Delimiters.none, Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFieldSymbol;", "index", "convertTypeParameters", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;", "typeParameterList", "typeConstraints", "Lorg/jetbrains/kotlin/fir/lightTree/fir/TypeConstraint;", "convertTypeConstraints", "convertTypeConstraint", "typeConstraint", "convertTypeParameter", "typeParameter", "containingSymbol", "convertType", ModuleXmlParser.TYPE, "convertIntersectionType", "typeRefSource", "intersectionType", "isNullable", "convertReceiverType", "receiverType", "convertNullableType", "nullableType", "allTypeModifiers", "convertUserType", "userType", "convertTypeArguments", "Lorg/jetbrains/kotlin/fir/types/FirTypeProjection;", "typeArguments", "allowedUnderscoredTypeArgument", "convertTypeProjection", "typeProjection", "isUnderscored", "Lorg/jetbrains/kotlin/fir/types/FirUserTypeRef;", "(Lorg/jetbrains/kotlin/fir/types/FirUserTypeRef;)Z", "convertFunctionType", "functionType", "convertFunctionTypeParameters", "Lorg/jetbrains/kotlin/fir/FirFunctionTypeParameter;", "parameters", "convertValueParameters", "Lorg/jetbrains/kotlin/fir/lightTree/fir/ValueParameter;", "valueParameters", "valueParameterDeclaration", "Lorg/jetbrains/kotlin/fir/builder/AbstractRawFirBuilder$ValueParameterDeclaration;", "convertValueParameter", "valueParameter", "fillDanglingConstraintsTo", "T", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRefsOwner;", "typeParameters", "to", "(Ljava/util/List;Ljava/util/List;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)V", "addContextParameters", "contextLists", "SCRIPT_DECLARATION_TOKENS", Argument.Delimiters.none, "Lcom/intellij/psi/tree/IElementType;", "kotlin.jvm.PlatformType", "convertScript", "Lorg/jetbrains/kotlin/fir/declarations/FirScript;", "script", "scriptSource", "fileName", "setup", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirScriptBuilder;", "Lkotlin/ExtensionFunctionType;", "convertReplSnippet", "Lorg/jetbrains/kotlin/fir/declarations/FirReplSnippet;", "snippetSetup", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirReplSnippetBuilder;", "functionBodySetup", "statementsSetup", "Lorg/jetbrains/kotlin/fir/FirElement;", "DelegationSpecifiers", "org.jetbrains.kotlin.fir:light-tree2fir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class LightTreeRawFirDeclarationBuilder extends AbstractLightTreeRawFirBuilder {
    private final Set<IElementType> SCRIPT_DECLARATION_TOKENS;
    private final FirScopeProvider baseScopeProvider;
    private final LightTreeRawFirExpressionBuilder expressionConverter;
    private final boolean headerMode;

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B7\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\u0004\b\u000b\u0010\fJ\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003HÆ\u0003J\u0015\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bHÆ\u0003J?\u0010\u0015\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bHÆ\u0001J\u0014\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0019\u001a\u00020\tHÖ\u0081\u0004J\n\u0010\u001a\u001a\u00020\u001bHÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u001d\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/kotlin/fir/lightTree/converter/LightTreeRawFirDeclarationBuilder$DelegationSpecifiers;", Argument.Delimiters.none, "superTypeCalls", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/lightTree/fir/DelegatedConstructorWrapper;", "superTypesRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "delegateFieldsMap", Argument.Delimiters.none, Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFieldSymbol;", "<init>", "(Ljava/util/List;Ljava/util/List;Ljava/util/Map;)V", "getSuperTypeCalls", "()Ljava/util/List;", "getSuperTypesRef", "getDelegateFieldsMap", "()Ljava/util/Map;", "component1", "component2", "component3", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", "toString", Argument.Delimiters.none, "org.jetbrains.kotlin.fir:light-tree2fir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class DelegationSpecifiers {
        private final Map<Integer, FirFieldSymbol> delegateFieldsMap;
        private final List<DelegatedConstructorWrapper> superTypeCalls;
        private final List<FirTypeRef> superTypesRef;

        /* JADX WARN: Multi-variable type inference failed */
        public DelegationSpecifiers(List<DelegatedConstructorWrapper> list, List<? extends FirTypeRef> list2, Map<Integer, ? extends FirFieldSymbol> map) {
            list.getClass();
            list2.getClass();
            map.getClass();
            this.superTypeCalls = list;
            this.superTypesRef = list2;
            this.delegateFieldsMap = map;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ DelegationSpecifiers copy$default(DelegationSpecifiers delegationSpecifiers, List list, List list2, Map map, int i, Object obj) {
            if ((i & 1) != 0) {
                list = delegationSpecifiers.superTypeCalls;
            }
            if ((i & 2) != 0) {
                list2 = delegationSpecifiers.superTypesRef;
            }
            if ((i & 4) != 0) {
                map = delegationSpecifiers.delegateFieldsMap;
            }
            return delegationSpecifiers.copy(list, list2, map);
        }

        public final List<DelegatedConstructorWrapper> component1() {
            return this.superTypeCalls;
        }

        public final List<FirTypeRef> component2() {
            return this.superTypesRef;
        }

        public final Map<Integer, FirFieldSymbol> component3() {
            return this.delegateFieldsMap;
        }

        public final DelegationSpecifiers copy(List<DelegatedConstructorWrapper> superTypeCalls, List<? extends FirTypeRef> superTypesRef, Map<Integer, ? extends FirFieldSymbol> delegateFieldsMap) {
            superTypeCalls.getClass();
            superTypesRef.getClass();
            delegateFieldsMap.getClass();
            return new DelegationSpecifiers(superTypeCalls, superTypesRef, delegateFieldsMap);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DelegationSpecifiers)) {
                return false;
            }
            DelegationSpecifiers delegationSpecifiers = (DelegationSpecifiers) other;
            return Intrinsics.areEqual(this.superTypeCalls, delegationSpecifiers.superTypeCalls) && Intrinsics.areEqual(this.superTypesRef, delegationSpecifiers.superTypesRef) && Intrinsics.areEqual(this.delegateFieldsMap, delegationSpecifiers.delegateFieldsMap);
        }

        public final Map<Integer, FirFieldSymbol> getDelegateFieldsMap() {
            return this.delegateFieldsMap;
        }

        public final List<DelegatedConstructorWrapper> getSuperTypeCalls() {
            return this.superTypeCalls;
        }

        public final List<FirTypeRef> getSuperTypesRef() {
            return this.superTypesRef;
        }

        public int hashCode() {
            return (((this.superTypeCalls.hashCode() * 31) + this.superTypesRef.hashCode()) * 31) + this.delegateFieldsMap.hashCode();
        }

        public String toString() {
            return "DelegationSpecifiers(superTypeCalls=" + this.superTypeCalls + ", superTypesRef=" + this.superTypesRef + ", delegateFieldsMap=" + this.delegateFieldsMap + ')';
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LightTreeRawFirDeclarationBuilder(FirSession firSession, FirScopeProvider firScopeProvider, FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, Context<LighterASTNode> context) {
        super(firSession, flyweightCapableTreeStructure, context);
        firSession.getClass();
        firScopeProvider.getClass();
        flyweightCapableTreeStructure.getClass();
        context.getClass();
        this.baseScopeProvider = firScopeProvider;
        this.expressionConverter = new LightTreeRawFirExpressionBuilder(firSession, flyweightCapableTreeStructure, this, context);
        this.headerMode = ((Boolean) FirLanguageSettingsComponentKt.getLanguageVersionSettings(firSession).getFlag(AnalysisFlags.INSTANCE.getHeaderMode())).booleanValue();
        this.SCRIPT_DECLARATION_TOKENS = SetsKt.setOf(new IElementType[]{KtNodeTypes.CLASS, KtNodeTypes.FUN, KtNodeTypes.PROPERTY, KtNodeTypes.TYPEALIAS, KtNodeTypes.OBJECT_DECLARATION, KtNodeTypes.CLASS_INITIALIZER, KtNodeTypes.MODIFIER_LIST, KtNodeTypes.SCRIPT_INITIALIZER, KtNodeTypes.DESTRUCTURING_DECLARATION});
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:21:0x00a5  */
    /* JADX WARN: Code duplicated, block: B:29:0x00cc  */
    private final void addContextParameters(List<FirValueParameter> list, List<? extends LighterASTNode> list2, FirBasedSymbol<?> firBasedSymbol) throws KotlinIllegalStateExceptionWithAttachments, UninitializedPropertyAccessException {
        Name referencedNameAsName;
        Name referencedNameAsName2;
        FirResolvedTypeRef firResolvedTypeRefConvertType;
        if (list2 == null) {
            return;
        }
        for (LighterASTNode lighterASTNode : list2) {
            IElementType iElementType = KtNodeTypes.VALUE_PARAMETER;
            iElementType.getClass();
            List<FirValueParameter> list3 = list;
            Iterator<T> it = this.getChildNodesByType(lighterASTNode, iElementType).iterator();
            while (it.hasNext()) {
                LightTreeRawFirDeclarationBuilder lightTreeRawFirDeclarationBuilder = this;
                list3.add(convertValueParameter$default(lightTreeRawFirDeclarationBuilder, (LighterASTNode) it.next(), firBasedSymbol, AbstractRawFirBuilder.ValueParameterDeclaration.CONTEXT_PARAMETER, null, 8, null).getFirValueParameter());
                this = lightTreeRawFirDeclarationBuilder;
            }
            LightTreeRawFirDeclarationBuilder lightTreeRawFirDeclarationBuilder2 = this;
            FirBasedSymbol<?> firBasedSymbol2 = firBasedSymbol;
            IElementType iElementType2 = KtNodeTypes.CONTEXT_RECEIVER;
            iElementType2.getClass();
            for (LighterASTNode lighterASTNode2 : lightTreeRawFirDeclarationBuilder2.getChildNodesByType(lighterASTNode, iElementType2)) {
                FirValueParameterBuilder firValueParameterBuilder = new FirValueParameterBuilder();
                firValueParameterBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(lightTreeRawFirDeclarationBuilder2, lighterASTNode2, null, 1, null));
                firValueParameterBuilder.setModuleData(lightTreeRawFirDeclarationBuilder2.getBaseModuleData());
                firValueParameterBuilder.setOrigin(FirDeclarationOrigin.Source.INSTANCE);
                IElementType iElementType3 = KtNodeTypes.LABEL_QUALIFIER;
                iElementType3.getClass();
                LighterASTNode childNodeByType = lightTreeRawFirDeclarationBuilder2.getChildNodeByType(lighterASTNode2, iElementType3);
                if (childNodeByType != null) {
                    IElementType iElementType4 = KtNodeTypes.LABEL;
                    iElementType4.getClass();
                    LighterASTNode childNodeByType2 = lightTreeRawFirDeclarationBuilder2.getChildNodeByType(childNodeByType, iElementType4);
                    if (childNodeByType2 != null) {
                        KtToken ktToken = KtTokens.IDENTIFIER;
                        ktToken.getClass();
                        LighterASTNode childNodeByType3 = lightTreeRawFirDeclarationBuilder2.getChildNodeByType(childNodeByType2, (IElementType) ktToken);
                        if (childNodeByType3 != null) {
                            referencedNameAsName = lightTreeRawFirDeclarationBuilder2.getReferencedNameAsName(childNodeByType3);
                        } else {
                            referencedNameAsName = null;
                        }
                    } else {
                        referencedNameAsName = null;
                    }
                } else {
                    referencedNameAsName = null;
                }
                IElementType iElementType5 = KtNodeTypes.TYPE_REFERENCE;
                iElementType5.getClass();
                LighterASTNode childNodeByType4 = lightTreeRawFirDeclarationBuilder2.getChildNodeByType(lighterASTNode2, iElementType5);
                if (childNodeByType4 != null) {
                    IElementType iElementType6 = KtNodeTypes.USER_TYPE;
                    iElementType6.getClass();
                    LighterASTNode childNodeByType5 = lightTreeRawFirDeclarationBuilder2.getChildNodeByType(childNodeByType4, iElementType6);
                    if (childNodeByType5 != null) {
                        IElementType iElementType7 = KtNodeTypes.REFERENCE_EXPRESSION;
                        iElementType7.getClass();
                        LighterASTNode childNodeByType6 = lightTreeRawFirDeclarationBuilder2.getChildNodeByType(childNodeByType5, iElementType7);
                        if (childNodeByType6 != null) {
                            referencedNameAsName2 = lightTreeRawFirDeclarationBuilder2.getReferencedNameAsName(childNodeByType6);
                        } else {
                            referencedNameAsName2 = null;
                        }
                    } else {
                        referencedNameAsName2 = null;
                    }
                } else {
                    referencedNameAsName2 = null;
                }
                if (referencedNameAsName == null) {
                    referencedNameAsName = referencedNameAsName2 == null ? SpecialNames.UNDERSCORE_FOR_UNUSED_VAR : referencedNameAsName2;
                }
                firValueParameterBuilder.setName(referencedNameAsName);
                firValueParameterBuilder.setSymbol(new FirValueParameterSymbol());
                FirValueParameterSymbol symbol = firValueParameterBuilder.getSymbol();
                lightTreeRawFirDeclarationBuilder2.getContext().pushContainerSymbol(symbol);
                if (childNodeByType4 != null) {
                    try {
                        firResolvedTypeRefConvertType = lightTreeRawFirDeclarationBuilder2.convertType(childNodeByType4);
                        if (firResolvedTypeRefConvertType == null) {
                            FirErrorTypeRefBuilder firErrorTypeRefBuilder = new FirErrorTypeRefBuilder();
                            firErrorTypeRefBuilder.setDiagnostic(new ConeSimpleDiagnostic("Type missing", null, 2, null));
                            firResolvedTypeRefConvertType = firErrorTypeRefBuilder.build();
                        }
                    } catch (Throwable th) {
                        lightTreeRawFirDeclarationBuilder2.getContext().popContainerSymbol(symbol);
                        throw th;
                    }
                } else {
                    FirErrorTypeRefBuilder firErrorTypeRefBuilder2 = new FirErrorTypeRefBuilder();
                    firErrorTypeRefBuilder2.setDiagnostic(new ConeSimpleDiagnostic("Type missing", null, 2, null));
                    firResolvedTypeRefConvertType = firErrorTypeRefBuilder2.build();
                }
                firValueParameterBuilder.setReturnTypeRef(firResolvedTypeRefConvertType);
                Unit unit = Unit.INSTANCE;
                lightTreeRawFirDeclarationBuilder2.getContext().popContainerSymbol(symbol);
                firValueParameterBuilder.setContainingDeclarationSymbol(firBasedSymbol2);
                firValueParameterBuilder.setValueParameterKind(FirValueParameterKind.LegacyContextReceiver);
                list3.add(firValueParameterBuilder.mo288build());
            }
            this = lightTreeRawFirDeclarationBuilder2;
            firBasedSymbol = firBasedSymbol2;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
    private final FirDanglingModifierList buildErrorNonLocalDeclarationForDanglingModifierList(LighterASTNode node) throws KotlinIllegalStateExceptionWithAttachments, UninitializedPropertyAccessException {
        FirDanglingModifierListBuilder firDanglingModifierListBuilder = new FirDanglingModifierListBuilder();
        firDanglingModifierListBuilder.setSource(toFirSourceElement(node, (KtFakeSourceElementKind) KtFakeSourceElementKind.DanglingModifierList.INSTANCE));
        firDanglingModifierListBuilder.setModuleData(getBaseModuleData());
        firDanglingModifierListBuilder.setOrigin(FirDeclarationOrigin.Source.INSTANCE);
        firDanglingModifierListBuilder.setDiagnostic(ConeDanglingModifierOnTopLevel.INSTANCE);
        firDanglingModifierListBuilder.setSymbol(new FirDanglingModifierSymbol());
        FirDanglingModifierSymbol symbol = firDanglingModifierListBuilder.getSymbol();
        getContext().pushContainerSymbol(symbol);
        try {
            ModifierList modifierListConvertModifierList$default = convertModifierList$default(this, node, false, 2, null);
            addContextParameters(firDanglingModifierListBuilder.getContextParameters(), modifierListConvertModifierList$default.getContextLists(), firDanglingModifierListBuilder.getSymbol());
            convertAnnotationsTo(modifierListConvertModifierList$default, firDanglingModifierListBuilder.getAnnotations());
            Unit unit = Unit.INSTANCE;
            return firDanglingModifierListBuilder.mo288build();
        } finally {
            getContext().popContainerSymbol(symbol);
        }
    }

    private final FirExpression buildFirDestructuringDeclarationInitializer(LighterASTNode destructuringDeclaration) {
        KtSourceElement firSourceElement$default;
        FirExpression firExpressionBuildErrorExpression;
        KtSourceElement source;
        KtSourceElement firSourceElement$default2;
        LighterASTNode childExpression = getChildExpression(destructuringDeclaration);
        if (Intrinsics.areEqual(childExpression != null ? childExpression.getTokenType() : null, KtNodeTypes.PROPERTY_DELEGATE)) {
            childExpression = null;
        }
        LightTreeRawFirExpressionBuilder lightTreeRawFirExpressionBuilder = this.expressionConverter;
        FirStatement asFirStatement = childExpression != null ? lightTreeRawFirExpressionBuilder.getAsFirStatement(childExpression, "Initializer required for destructuring declaration") : null;
        if (asFirStatement instanceof FirExpression) {
            firExpressionBuildErrorExpression = (FirExpression) asFirStatement;
            if (UtilsKt.isStatementLikeExpression(firExpressionBuildErrorExpression)) {
                KtSourceElement source2 = firExpressionBuildErrorExpression.getSource();
                if (source2 == null || (firSourceElement$default2 = KtSourceElementKt.realElement(source2)) == null) {
                    firSourceElement$default2 = childExpression != null ? AbstractRawFirBuilder.toFirSourceElement$default(lightTreeRawFirExpressionBuilder, childExpression, null, 1, null) : lightTreeRawFirExpressionBuilder.toFirSourceElement(destructuringDeclaration, (KtFakeSourceElementKind) KtFakeSourceElementKind.ErrorExpression.INSTANCE);
                }
                firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default2, new ConeSimpleDiagnostic("Initializer required for destructuring declaration", DiagnosticKind.ExpressionExpected), asFirStatement);
            }
        } else {
            if (asFirStatement == null || (source = asFirStatement.getSource()) == null || (firSourceElement$default = KtSourceElementKt.realElement(source)) == null) {
                firSourceElement$default = childExpression != null ? AbstractRawFirBuilder.toFirSourceElement$default(lightTreeRawFirExpressionBuilder, childExpression, null, 1, null) : lightTreeRawFirExpressionBuilder.toFirSourceElement(destructuringDeclaration, (KtFakeSourceElementKind) KtFakeSourceElementKind.ErrorExpression.INSTANCE);
            }
            firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default, childExpression == null ? new ConeSyntaxDiagnostic("Initializer required for destructuring declaration") : new ConeSimpleDiagnostic("Initializer required for destructuring declaration", DiagnosticKind.ExpressionExpected), asFirStatement);
        }
        if (firExpressionBuildErrorExpression != null) {
            return firExpressionBuildErrorExpression;
        }
        x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.expressions.FirExpression");
        return null;
    }

    private final void collectSegments(List<String> list, LighterASTNode lighterASTNode) {
        IElementType tokenType = lighterASTNode.getTokenType();
        if (Intrinsics.areEqual(tokenType, KtNodeTypes.REFERENCE_EXPRESSION)) {
            list.add(ConverterUtilKt.getAsStringWithoutBacktick(lighterASTNode));
            return;
        }
        if (Intrinsics.areEqual(tokenType, KtNodeTypes.DOT_QUALIFIED_EXPRESSION)) {
            for (LighterASTNode lighterASTNode2 : getChildrenAsArray(lighterASTNode)) {
                if (lighterASTNode2 == null) {
                    return;
                }
                if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode2.getTokenType())) {
                    collectSegments(list, lighterASTNode2);
                }
            }
        }
    }

    private final void consume(ModifierList modifierList, LighterASTNode lighterASTNode, boolean z) {
        for (LighterASTNode lighterASTNode2 : getChildrenAsArray(lighterASTNode)) {
            if (lighterASTNode2 == null) {
                return;
            }
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode2.getTokenType())) {
                IElementType tokenType = lighterASTNode2.getTokenType();
                if (Intrinsics.areEqual(tokenType, KtNodeTypes.ANNOTATION)) {
                    modifierList.getAnnotations().add(lighterASTNode2);
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.ANNOTATION_ENTRY)) {
                    modifierList.getAnnotations().add(lighterASTNode2);
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.CONTEXT_PARAMETER_LIST)) {
                    modifierList.getContextLists().add(lighterASTNode2);
                } else if (tokenType instanceof KtModifierKeywordToken) {
                    modifierList.addModifier(lighterASTNode2, z);
                }
            }
        }
    }

    public static /* synthetic */ void consume$default(LightTreeRawFirDeclarationBuilder lightTreeRawFirDeclarationBuilder, ModifierList modifierList, LighterASTNode lighterASTNode, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        lightTreeRawFirDeclarationBuilder.consume(modifierList, lighterASTNode, z);
    }

    public static /* synthetic */ FirAnnotationCall convertAnnotationEntry$default(LightTreeRawFirDeclarationBuilder lightTreeRawFirDeclarationBuilder, LighterASTNode lighterASTNode, AnnotationUseSiteTarget annotationUseSiteTarget, ConeDiagnostic coneDiagnostic, int i, Object obj) {
        if ((i & 2) != 0) {
            annotationUseSiteTarget = null;
        }
        if ((i & 4) != 0) {
            coneDiagnostic = null;
        }
        return lightTreeRawFirDeclarationBuilder.convertAnnotationEntry(lighterASTNode, annotationUseSiteTarget, coneDiagnostic);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    private final void convertAnnotationOrAnnotationEntryTo(LighterASTNode node, List<? super FirAnnotationCall> list) throws UninitializedPropertyAccessException {
        IElementType tokenType = node.getTokenType();
        if (Intrinsics.areEqual(tokenType, KtNodeTypes.ANNOTATION)) {
            convertAnnotationTo(node, list);
        } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.ANNOTATION_ENTRY)) {
            list.add(convertAnnotationEntry$default(this, node, null, null, 6, null));
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    private final AnnotationUseSiteTarget convertAnnotationTarget(LighterASTNode annotationUseSiteTarget) throws UninitializedPropertyAccessException {
        AnnotationUseSiteTarget annotationUseSiteTarget2 = null;
        for (LighterASTNode lighterASTNode : getChildrenAsArray(annotationUseSiteTarget)) {
            if (lighterASTNode == null) {
                break;
            }
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType())) {
                IElementType tokenType = lighterASTNode.getTokenType();
                if (Intrinsics.areEqual(tokenType, KtTokens.ALL_KEYWORD)) {
                    annotationUseSiteTarget2 = AnnotationUseSiteTarget.ALL;
                } else if (Intrinsics.areEqual(tokenType, KtTokens.FIELD_KEYWORD)) {
                    annotationUseSiteTarget2 = AnnotationUseSiteTarget.FIELD;
                } else if (Intrinsics.areEqual(tokenType, KtTokens.FILE_KEYWORD)) {
                    annotationUseSiteTarget2 = AnnotationUseSiteTarget.FILE;
                } else if (Intrinsics.areEqual(tokenType, KtTokens.PROPERTY_KEYWORD)) {
                    annotationUseSiteTarget2 = AnnotationUseSiteTarget.PROPERTY;
                } else if (Intrinsics.areEqual(tokenType, KtTokens.GET_KEYWORD)) {
                    annotationUseSiteTarget2 = AnnotationUseSiteTarget.PROPERTY_GETTER;
                } else if (Intrinsics.areEqual(tokenType, KtTokens.SET_KEYWORD)) {
                    annotationUseSiteTarget2 = AnnotationUseSiteTarget.PROPERTY_SETTER;
                } else if (Intrinsics.areEqual(tokenType, KtTokens.RECEIVER_KEYWORD)) {
                    annotationUseSiteTarget2 = AnnotationUseSiteTarget.RECEIVER;
                } else if (Intrinsics.areEqual(tokenType, KtTokens.PARAM_KEYWORD)) {
                    annotationUseSiteTarget2 = AnnotationUseSiteTarget.CONSTRUCTOR_PARAMETER;
                } else if (Intrinsics.areEqual(tokenType, KtTokens.SETPARAM_KEYWORD)) {
                    annotationUseSiteTarget2 = AnnotationUseSiteTarget.SETTER_PARAMETER;
                } else if (Intrinsics.areEqual(tokenType, KtTokens.DELEGATE_KEYWORD)) {
                    annotationUseSiteTarget2 = AnnotationUseSiteTarget.PROPERTY_DELEGATE_FIELD;
                }
            }
        }
        if (annotationUseSiteTarget2 != null) {
            return annotationUseSiteTarget2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("annotationTarget");
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    private final List<FirAnnotationCall> convertAnnotations(ModifierList modifierList) throws UninitializedPropertyAccessException {
        List<? super FirAnnotationCall> listCreateListBuilder = CollectionsKt.createListBuilder();
        convertAnnotationsTo(modifierList, listCreateListBuilder);
        return CollectionsKt.build(listCreateListBuilder);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    private final void convertAnnotationsOnlyTo(LighterASTNode modifierList, List<? super FirAnnotationCall> list) throws UninitializedPropertyAccessException {
        for (LighterASTNode lighterASTNode : getChildrenAsArray(modifierList)) {
            if (lighterASTNode == null) {
                return;
            }
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType())) {
                convertAnnotationOrAnnotationEntryTo(lighterASTNode, list);
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    private final void convertAnnotationsTo(ModifierList modifierList, List<? super FirAnnotationCall> list) throws UninitializedPropertyAccessException {
        Iterator<LighterASTNode> it = modifierList.getAnnotations().iterator();
        while (it.hasNext()) {
            convertAnnotationOrAnnotationEntryTo(it.next(), list);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
    private final FirAnonymousInitializer convertAnonymousInitializer(LighterASTNode anonymousInitializer, FirBasedSymbol<?> containingDeclarationSymbol, boolean isLocal) throws KotlinIllegalStateExceptionWithAttachments {
        Object obj;
        if (this.headerMode && !getContext().getForceKeepingTheBodyInHeaderMode()) {
            return null;
        }
        FirAnonymousInitializerSymbol firAnonymousInitializerSymbol = new FirAnonymousInitializerSymbol();
        if (!isLocal) {
            getContext().pushContainerSymbol(firAnonymousInitializerSymbol);
        }
        try {
            FirAnonymousInitializerBuilder firAnonymousInitializerBuilder = new FirAnonymousInitializerBuilder();
            firAnonymousInitializerBuilder.setSymbol(firAnonymousInitializerSymbol);
            boolean z = true;
            firAnonymousInitializerBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(this, anonymousInitializer, null, 1, null));
            firAnonymousInitializerBuilder.setModuleData(getBaseModuleData());
            firAnonymousInitializerBuilder.setOrigin(FirDeclarationOrigin.Source.INSTANCE);
            boolean forceKeepingTheBodyInHeaderMode = getContext().getForceKeepingTheBodyInHeaderMode();
            getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
            boolean inLocalContext = getContext().getInLocalContext();
            getContext().setInLocalContext(true);
            FqName classNameBeforeLocalContext = getContext().getClassNameBeforeLocalContext();
            if (!inLocalContext) {
                getContext().setClassNameBeforeLocalContext(getContext().getClassName());
            }
            FqName className = getContext().getClassName();
            getContext().setClassName(FqName.ROOT);
            try {
                List<FirAnnotation> annotations = firAnonymousInitializerBuilder.getAnnotations();
                LighterASTNode[] childrenAsArray = getChildrenAsArray(anonymousInitializer);
                int length = childrenAsArray.length;
                FirBlock firBlockBuildEmptyExpressionBlock = null;
                int i = 0;
                while (i < length) {
                    LighterASTNode lighterASTNode = childrenAsArray[i];
                    if (lighterASTNode == null) {
                        break;
                    }
                    if (AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType())) {
                        childrenAsArray = childrenAsArray;
                        obj = null;
                    } else {
                        IElementType tokenType = lighterASTNode.getTokenType();
                        if (Intrinsics.areEqual(tokenType, KtNodeTypes.MODIFIER_LIST)) {
                            convertAnnotationsOnlyTo(lighterASTNode, annotations);
                        } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.BLOCK)) {
                            boolean forceKeepingTheBodyInHeaderMode2 = getContext().getForceKeepingTheBodyInHeaderMode();
                            getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode2);
                            boolean inLocalContext2 = getContext().getInLocalContext();
                            getContext().setInLocalContext(z);
                            FqName classNameBeforeLocalContext2 = getContext().getClassNameBeforeLocalContext();
                            if (!inLocalContext2) {
                                getContext().setClassNameBeforeLocalContext(getContext().getClassName());
                            }
                            FqName className2 = getContext().getClassName();
                            getContext().setClassName(FqName.ROOT);
                            obj = null;
                            try {
                                FirBlock firBlockConvertBlock$default = convertBlock$default(this, lighterASTNode, false, 2, null);
                                Unit unit = Unit.INSTANCE;
                                getContext().setClassNameBeforeLocalContext(classNameBeforeLocalContext2);
                                getContext().setInLocalContext(inLocalContext2);
                                getContext().setClassName(className2);
                                getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode2);
                                firBlockBuildEmptyExpressionBlock = firBlockConvertBlock$default;
                            } catch (Throwable th) {
                                getContext().setClassNameBeforeLocalContext(classNameBeforeLocalContext2);
                                getContext().setInLocalContext(inLocalContext2);
                                getContext().setClassName(className2);
                                getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode2);
                                throw th;
                            }
                        }
                        childrenAsArray = childrenAsArray;
                        obj = null;
                    }
                    i++;
                    length = length;
                    annotations = annotations;
                    childrenAsArray = childrenAsArray;
                    z = true;
                }
                if (firBlockBuildEmptyExpressionBlock == null) {
                    firBlockBuildEmptyExpressionBlock = FirEmptyExpressionBlockBuilderKt.buildEmptyExpressionBlock();
                }
                getContext().setClassNameBeforeLocalContext(classNameBeforeLocalContext);
                getContext().setInLocalContext(inLocalContext);
                getContext().setClassName(className);
                getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
                firAnonymousInitializerBuilder.setBody(firBlockBuildEmptyExpressionBlock);
                firAnonymousInitializerBuilder.setContainingDeclarationSymbol(containingDeclarationSymbol);
                FirAnonymousInitializer firAnonymousInitializerMo288build = firAnonymousInitializerBuilder.mo288build();
                if (!isLocal) {
                    getContext().popContainerSymbol(firAnonymousInitializerSymbol);
                }
                if (isDirectlyInsideCompanionBlock()) {
                    ClassMembersKt.setIllegalCompanionBlockMember(firAnonymousInitializerMo288build, Boolean.TRUE);
                }
                return firAnonymousInitializerMo288build;
            } catch (Throwable th2) {
                getContext().setClassNameBeforeLocalContext(classNameBeforeLocalContext);
                getContext().setInLocalContext(inLocalContext);
                getContext().setClassName(className);
                getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
                throw th2;
            }
        } catch (Throwable th3) {
            if (!isLocal) {
                getContext().popContainerSymbol(firAnonymousInitializerSymbol);
            }
            throw th3;
        }
    }

    public static /* synthetic */ FirAnonymousInitializer convertAnonymousInitializer$default(LightTreeRawFirDeclarationBuilder lightTreeRawFirDeclarationBuilder, LighterASTNode lighterASTNode, FirBasedSymbol firBasedSymbol, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = false;
        }
        return lightTreeRawFirDeclarationBuilder.convertAnonymousInitializer(lighterASTNode, firBasedSymbol, z);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    private final FirBackingField convertBackingField(LighterASTNode lighterASTNode, FirPropertySymbol firPropertySymbol, ModifierList modifierList, FirTypeRef firTypeRef, boolean z, List<? extends FirAnnotationCall> list, LighterASTNode lighterASTNode2) throws Exception {
        ModifierList modifierListConvertModifierList$default;
        KtSourceElement firSourceElement$default;
        FirExpression firExpressionBuildErrorExpression;
        KtSourceElement source;
        KtSourceElement firSourceElement$default2;
        FirTypeRef implicitType = getImplicitType();
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        if (lighterASTNode != null) {
            LighterASTNode[] childrenAsArray = getChildrenAsArray(lighterASTNode);
            int length = childrenAsArray.length;
            boolean z2 = false;
            modifierListConvertModifierList$default = null;
            int i = 0;
            while (i < length) {
                LighterASTNode lighterASTNode3 = childrenAsArray[i];
                if (lighterASTNode3 == null) {
                    break;
                }
                if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode3.getTokenType())) {
                    if (Intrinsics.areEqual(lighterASTNode3.getTokenType(), KtNodeTypes.MODIFIER_LIST)) {
                        modifierListConvertModifierList$default = convertModifierList$default(this, lighterASTNode3, z2, 2, null);
                    } else if (Intrinsics.areEqual(lighterASTNode3.getTokenType(), KtNodeTypes.TYPE_REFERENCE)) {
                        implicitType = convertType(lighterASTNode3);
                    } else if (ElementTypeUtils.INSTANCE.isExpression(lighterASTNode3)) {
                        LightTreeRawFirExpressionBuilder lightTreeRawFirExpressionBuilder = this.expressionConverter;
                        FirStatement asFirStatement = lightTreeRawFirExpressionBuilder.getAsFirStatement(lighterASTNode3, "Should have initializer");
                        if (asFirStatement instanceof FirExpression) {
                            firExpressionBuildErrorExpression = (FirExpression) asFirStatement;
                            if (UtilsKt.isStatementLikeExpression(firExpressionBuildErrorExpression)) {
                                KtSourceElement source2 = firExpressionBuildErrorExpression.getSource();
                                if (source2 == null || (firSourceElement$default2 = KtSourceElementKt.realElement(source2)) == null) {
                                    firSourceElement$default2 = AbstractRawFirBuilder.toFirSourceElement$default(lightTreeRawFirExpressionBuilder, lighterASTNode3, null, 1, null);
                                }
                                firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default2, new ConeSimpleDiagnostic("Should have initializer", DiagnosticKind.ExpressionExpected), asFirStatement);
                            }
                        } else {
                            if (asFirStatement == null || (source = asFirStatement.getSource()) == null || (firSourceElement$default = KtSourceElementKt.realElement(source)) == null) {
                                firSourceElement$default = AbstractRawFirBuilder.toFirSourceElement$default(lightTreeRawFirExpressionBuilder, lighterASTNode3, null, 1, null);
                            }
                            firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default, new ConeSimpleDiagnostic("Should have initializer", DiagnosticKind.ExpressionExpected), asFirStatement);
                        }
                        if (firExpressionBuildErrorExpression == null) {
                            x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.expressions.FirExpression");
                            return null;
                        }
                        objectRef.element = firExpressionBuildErrorExpression;
                    } else {
                        continue;
                    }
                }
                i++;
                z2 = false;
            }
        } else {
            modifierListConvertModifierList$default = null;
        }
        FirDeclarationStatus firDeclarationStatusObtainPropertyComponentStatus = obtainPropertyComponentStatus(Visibilities.Private.INSTANCE, modifierListConvertModifierList$default == null ? new ModifierList(0L, 1, null) : modifierListConvertModifierList$default, modifierList);
        KtLightSourceElement firSourceElement$default3 = lighterASTNode != null ? AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode, null, 1, null) : null;
        if (lighterASTNode == null) {
            FirModuleData baseModuleData = getBaseModuleData();
            FirDeclarationOrigin.Source source3 = FirDeclarationOrigin.Source.INSTANCE;
            KtFakeSourceElementKind.DefaultAccessor defaultAccessor = KtFakeSourceElementKind.DefaultAccessor.INSTANCE;
            return new FirDefaultPropertyBackingField(baseModuleData, source3, toFirSourceElement(lighterASTNode2, (KtFakeSourceElementKind) defaultAccessor), CollectionsKt.toMutableList(list), UtilsKt.copyWithNewSourceKind(firTypeRef, defaultAccessor), z, firPropertySymbol, firDeclarationStatusObtainPropertyComponentStatus, null, 256, null);
        }
        FirBackingFieldBuilder firBackingFieldBuilder = new FirBackingFieldBuilder();
        firBackingFieldBuilder.setSource(firSourceElement$default3);
        firBackingFieldBuilder.setModuleData(getBaseModuleData());
        firBackingFieldBuilder.setOrigin(FirDeclarationOrigin.Source.INSTANCE);
        firBackingFieldBuilder.setReturnTypeRef(implicitType);
        firBackingFieldBuilder.setName(StandardNames.BACKING_FIELD);
        firBackingFieldBuilder.setSymbol(new FirBackingFieldSymbol());
        firBackingFieldBuilder.setStatus(firDeclarationStatusObtainPropertyComponentStatus);
        if (modifierListConvertModifierList$default != null) {
            convertAnnotationsTo(modifierListConvertModifierList$default, firBackingFieldBuilder.getAnnotations());
        }
        CollectionsKt.addAll(firBackingFieldBuilder.getAnnotations(), list);
        firBackingFieldBuilder.setPropertySymbol(firPropertySymbol);
        firBackingFieldBuilder.setInitializer((FirExpression) objectRef.element);
        firBackingFieldBuilder.setVar(z);
        firBackingFieldBuilder.setVal(!z);
        return firBackingFieldBuilder.mo288build();
    }

    public static /* synthetic */ FirBlock convertBlock$default(LightTreeRawFirDeclarationBuilder lightTreeRawFirDeclarationBuilder, LighterASTNode lighterASTNode, boolean z, int i, Object obj) throws Exception {
        if ((i & 2) != 0) {
            z = false;
        }
        return lightTreeRawFirDeclarationBuilder.convertBlock(lighterASTNode, z);
    }

    public static /* synthetic */ FirBlock convertBlockExpression$default(LightTreeRawFirDeclarationBuilder lightTreeRawFirDeclarationBuilder, LighterASTNode lighterASTNode, boolean z, int i, Object obj) throws Exception {
        if ((i & 2) != 0) {
            z = false;
        }
        return lightTreeRawFirDeclarationBuilder.convertBlockExpression(lighterASTNode, z);
    }

    public static /* synthetic */ FirBlockBuilder convertBlockExpressionWithoutBuilding$default(LightTreeRawFirDeclarationBuilder lightTreeRawFirDeclarationBuilder, LighterASTNode lighterASTNode, KtFakeSourceElementKind ktFakeSourceElementKind, boolean z, int i, Object obj) throws Exception {
        if ((i & 2) != 0) {
            ktFakeSourceElementKind = null;
        }
        if ((i & 4) != 0) {
            z = false;
        }
        return lightTreeRawFirDeclarationBuilder.convertBlockExpressionWithoutBuilding(lighterASTNode, ktFakeSourceElementKind, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
    public static final Unit convertClass$lambda$2$0$3$0$11(LightTreeRawFirDeclarationBuilder lightTreeRawFirDeclarationBuilder, FirValueParameterBuilder firValueParameterBuilder, LighterASTNode lighterASTNode) throws KotlinIllegalStateExceptionWithAttachments, UninitializedPropertyAccessException {
        firValueParameterBuilder.getClass();
        lighterASTNode.getClass();
        FirValueParameterSymbol symbol = firValueParameterBuilder.getSymbol();
        lightTreeRawFirDeclarationBuilder.getContext().pushContainerSymbol(symbol);
        try {
            for (LighterASTNode lighterASTNode2 : lightTreeRawFirDeclarationBuilder.getChildrenAsArray(lighterASTNode)) {
                if (lighterASTNode2 == null) {
                    break;
                }
                if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode2.getTokenType()) && Intrinsics.areEqual(lighterASTNode2.getTokenType(), KtNodeTypes.MODIFIER_LIST)) {
                    List<? super FirAnnotationCall> listCreateListBuilder = CollectionsKt.createListBuilder();
                    lightTreeRawFirDeclarationBuilder.convertAnnotationsOnlyTo(lighterASTNode2, listCreateListBuilder);
                    List listBuild = CollectionsKt.build(listCreateListBuilder);
                    Collection annotations = firValueParameterBuilder.getAnnotations();
                    for (Object obj : listBuild) {
                        if (ConversionUtilsKt.appliesToPrimaryConstructorParameter(((FirAnnotationCall) obj).getUseSiteTarget())) {
                            annotations.add(obj);
                        }
                    }
                }
            }
            Unit unit = Unit.INSTANCE;
            return Unit.INSTANCE;
        } finally {
            lightTreeRawFirDeclarationBuilder.getContext().popContainerSymbol(symbol);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
    private final List<FirDeclaration> convertClassBody(LighterASTNode classBody, ClassWrapper classWrapper) throws Throwable {
        ArrayList arrayList = new ArrayList();
        LighterASTNode[] childrenAsArray = getChildrenAsArray(classBody);
        ArrayList arrayList2 = new ArrayList();
        for (LighterASTNode lighterASTNode : childrenAsArray) {
            if (lighterASTNode == null) {
                break;
            }
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType())) {
                convertDeclarationFromClassBody(lighterASTNode, arrayList2, classWrapper, arrayList);
            }
        }
        convertDanglingModifierListsInClassBody(arrayList, arrayList2);
        return arrayList2;
    }

    private final FirDelegatedConstructorCall convertConstructorDelegationCall(LighterASTNode constructorDelegationCall, ClassWrapper classWrapper, boolean isExpect) {
        FirReference firReferenceBuild;
        KtSourceElement firSourceElement;
        LighterASTNode lighterASTNode;
        ArrayList arrayList = new ArrayList();
        LighterASTNode[] childrenAsArray = getChildrenAsArray(constructorDelegationCall);
        int length = childrenAsArray.length;
        boolean z = false;
        for (int i = 0; i < length && (lighterASTNode = childrenAsArray[i]) != null; i++) {
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType())) {
                IElementType tokenType = lighterASTNode.getTokenType();
                if (Intrinsics.areEqual(tokenType, KtNodeTypes.CONSTRUCTOR_DELEGATION_REFERENCE)) {
                    if (Intrinsics.areEqual(getAsText(lighterASTNode), "this")) {
                        z = true;
                    }
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.VALUE_ARGUMENT_LIST)) {
                    CollectionsKt.addAll(arrayList, this.expressionConverter.convertValueArguments(lighterASTNode));
                }
            }
        }
        boolean z2 = constructorDelegationCall.getTextLength() == 0;
        KtSourceElement ktSourceElementFakeElement$default = null;
        if (z2 && (classWrapper.getModifiers().hasExternal() || isExpect)) {
            return null;
        }
        FirTypeRef delegatedSelfTypeRef = z ? classWrapper.getDelegatedSelfTypeRef() : classWrapper.getDelegatedSuperTypeRef();
        FirDelegatedConstructorCallBuilder firDelegatedConstructorCallBuilder = new FirDelegatedConstructorCallBuilder();
        firDelegatedConstructorCallBuilder.setSource(z2 ? KtSourceElementKt.fakeElement$default(AbstractRawFirBuilder.toFirSourceElement$default(this, constructorDelegationCall, null, 1, null), KtFakeSourceElementKind.ImplicitConstructor.INSTANCE, null, 2, null) : AbstractRawFirBuilder.toFirSourceElement$default(this, constructorDelegationCall, null, 1, null));
        firDelegatedConstructorCallBuilder.setConstructedTypeRef(UtilsKt.copyWithNewSourceKind(delegatedSelfTypeRef, KtFakeSourceElementKind.ImplicitTypeRef.INSTANCE));
        firDelegatedConstructorCallBuilder.setThis(z);
        KtFakeSourceElementKind.ImplicitConstructor implicitConstructor = z2 ? KtFakeSourceElementKind.ImplicitConstructor.INSTANCE : KtFakeSourceElementKind.DelegatingConstructorCall.INSTANCE;
        IElementType iElementType = KtNodeTypes.CONSTRUCTOR_DELEGATION_REFERENCE;
        iElementType.getClass();
        LighterASTNode childNodeByType = getChildNodeByType(constructorDelegationCall, iElementType);
        if (childNodeByType == null || (firSourceElement = toFirSourceElement(childNodeByType, (KtFakeSourceElementKind) implicitConstructor)) == null) {
            KtSourceElement source = firDelegatedConstructorCallBuilder.getSource();
            if (source != null) {
                ktSourceElementFakeElement$default = KtSourceElementKt.fakeElement$default(source, implicitConstructor, null, 2, null);
            }
        } else {
            ktSourceElementFakeElement$default = firSourceElement;
        }
        if (z) {
            FirExplicitThisReferenceBuilder firExplicitThisReferenceBuilder = new FirExplicitThisReferenceBuilder();
            firExplicitThisReferenceBuilder.setSource(ktSourceElementFakeElement$default);
            firReferenceBuild = firExplicitThisReferenceBuilder.build();
        } else {
            FirExplicitSuperReferenceBuilder firExplicitSuperReferenceBuilder = new FirExplicitSuperReferenceBuilder();
            firExplicitSuperReferenceBuilder.setSource(ktSourceElementFakeElement$default);
            firExplicitSuperReferenceBuilder.setSuperTypeRef(firDelegatedConstructorCallBuilder.getConstructedTypeRef());
            firReferenceBuild = firExplicitSuperReferenceBuilder.build();
        }
        firDelegatedConstructorCallBuilder.setCalleeReference(firReferenceBuild);
        ConverterUtilKt.extractArgumentsFrom(firDelegatedConstructorCallBuilder, arrayList);
        return firDelegatedConstructorCallBuilder.mo288build();
    }

    private final Pair<FirTypeRef, List<FirExpression>> convertConstructorInvocation(LighterASTNode constructorInvocation) throws Exception {
        FirTypeRef implicitType = getImplicitType();
        ArrayList arrayList = new ArrayList();
        for (LighterASTNode lighterASTNode : getChildrenAsArray(constructorInvocation)) {
            if (lighterASTNode == null) {
                break;
            }
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType())) {
                IElementType tokenType = lighterASTNode.getTokenType();
                if (Intrinsics.areEqual(tokenType, KtNodeTypes.CONSTRUCTOR_CALLEE)) {
                    implicitType = convertType(lighterASTNode);
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.VALUE_ARGUMENT_LIST)) {
                    CollectionsKt.addAll(arrayList, this.expressionConverter.convertValueArguments(lighterASTNode));
                }
            }
        }
        return new Pair<>(implicitType, arrayList);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
    private final void convertDanglingModifierListsInClassBody(List<LighterASTNode> modifierLists, List<FirDeclaration> firDeclarations) throws KotlinIllegalStateExceptionWithAttachments, UninitializedPropertyAccessException {
        Iterator<LighterASTNode> it = modifierLists.iterator();
        while (it.hasNext()) {
            List<FirDeclaration> list = firDeclarations;
            FirDanglingModifierList firDanglingModifierListBuildErrorNonLocalDeclarationForDanglingModifierList = buildErrorNonLocalDeclarationForDanglingModifierList(it.next());
            ConeClassLikeType coneClassLikeTypeCurrentDispatchReceiverType = currentDispatchReceiverType();
            ClassMembersKt.setContainingClassAttr(firDanglingModifierListBuildErrorNonLocalDeclarationForDanglingModifierList, coneClassLikeTypeCurrentDispatchReceiverType != null ? coneClassLikeTypeCurrentDispatchReceiverType.getLookupTag() : null);
            list.add(firDanglingModifierListBuildErrorNonLocalDeclarationForDanglingModifierList);
        }
    }

    private final void convertDeclarationFromClassBody(LighterASTNode node, List<FirDeclaration> container, ClassWrapper classWrapper, List<LighterASTNode> modifierLists) throws Throwable {
        CompanionBlockCollector companionBlockCollector;
        IElementType tokenType = node.getTokenType();
        if (Intrinsics.areEqual(tokenType, KtNodeTypes.ENUM_ENTRY)) {
            classWrapper.getClass();
            container.add(convertEnumEntry(node, classWrapper));
            return;
        }
        if (Intrinsics.areEqual(tokenType, KtNodeTypes.CLASS) || Intrinsics.areEqual(tokenType, KtNodeTypes.OBJECT_DECLARATION)) {
            container.add(convertClass(node));
            return;
        }
        if (Intrinsics.areEqual(tokenType, KtNodeTypes.FUN)) {
            FirAnnotationContainer firAnnotationContainerConvertFunctionDeclaration = convertFunctionDeclaration(node);
            firAnnotationContainerConvertFunctionDeclaration.getClass();
            container.add((FirDeclaration) firAnnotationContainerConvertFunctionDeclaration);
            return;
        }
        if (Intrinsics.areEqual(tokenType, KtNodeTypes.PROPERTY)) {
            container.add(convertPropertyDeclaration(node, classWrapper));
            return;
        }
        if (Intrinsics.areEqual(tokenType, KtNodeTypes.TYPEALIAS)) {
            container.add(convertTypeAlias(node));
            return;
        }
        if (Intrinsics.areEqual(tokenType, KtNodeTypes.CLASS_INITIALIZER)) {
            classWrapper.getClass();
            FirAnonymousInitializer firAnonymousInitializerConvertAnonymousInitializer$default = convertAnonymousInitializer$default(this, node, ConversionUtilsKt.getOwnerRegularOrAnonymousObjectSymbol(classWrapper.getClassBuilder()), false, 4, null);
            if (firAnonymousInitializerConvertAnonymousInitializer$default != null) {
                container.add(firAnonymousInitializerConvertAnonymousInitializer$default);
                return;
            }
            return;
        }
        if (Intrinsics.areEqual(tokenType, KtNodeTypes.SECONDARY_CONSTRUCTOR)) {
            classWrapper.getClass();
            container.add(convertSecondaryConstructor(node, classWrapper));
            return;
        }
        if (Intrinsics.areEqual(tokenType, KtNodeTypes.MODIFIER_LIST)) {
            modifierLists.add(node);
            return;
        }
        if (Intrinsics.areEqual(tokenType, KtNodeTypes.DESTRUCTURING_DECLARATION)) {
            container.add(buildErrorNonLocalDestructuringDeclaration(AbstractRawFirBuilder.toFirSourceElement$default(this, node, null, 1, null), buildFirDestructuringDeclarationInitializer(node)));
            return;
        }
        if (Intrinsics.areEqual(tokenType, KtNodeTypes.COMPANION_BLOCK)) {
            if (classWrapper != null && (companionBlockCollector = classWrapper.getCompanionBlockCollector()) != null) {
                companionBlockCollector.collect(AbstractRawFirBuilder.toFirSourceElement$default(this, node, null, 1, null), isDirectlyInsideCompanionBlock());
            }
            FirBasedSymbol<?> currentCompanionBlockOwnerOrNull = getContext().getCurrentCompanionBlockOwnerOrNull();
            getContext().setCurrentCompanionBlockOwnerOrNull(getContext().getContainerSymbolIfAny());
            try {
                IElementType iElementType = KtNodeTypes.CLASS_BODY;
                iElementType.getClass();
                LighterASTNode childNodeByType = getChildNodeByType(node, iElementType);
                if (childNodeByType != null) {
                    container.addAll(convertClassBody(childNodeByType, classWrapper));
                }
            } finally {
                getContext().setCurrentCompanionBlockOwnerOrNull(currentCompanionBlockOwnerOrNull);
            }
        }
    }

    private final DelegationSpecifiers convertDelegationSpecifiers(LighterASTNode delegationSpecifiers) throws Exception {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int i = 0;
        for (LighterASTNode lighterASTNode : getChildrenAsArray(delegationSpecifiers)) {
            if (lighterASTNode == null) {
                break;
            }
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType())) {
                IElementType tokenType = lighterASTNode.getTokenType();
                if (Intrinsics.areEqual(tokenType, KtNodeTypes.SUPER_TYPE_ENTRY)) {
                    arrayList.add(convertType(lighterASTNode));
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.SUPER_TYPE_CALL_ENTRY)) {
                    Pair<FirTypeRef, List<FirExpression>> pairConvertConstructorInvocation = convertConstructorInvocation(lighterASTNode);
                    arrayList2.add(new DelegatedConstructorWrapper((FirTypeRef) pairConvertConstructorInvocation.getFirst(), (List) pairConvertConstructorInvocation.getSecond(), AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode, null, 1, null)));
                    arrayList.add(pairConvertConstructorInvocation.getFirst());
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.DELEGATED_SUPER_TYPE_ENTRY)) {
                    arrayList.add(convertExplicitDelegation(lighterASTNode, linkedHashMap, i));
                }
                i++;
            }
        }
        return new DelegationSpecifiers(arrayList2, arrayList, linkedHashMap);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    private final DestructuringEntry convertDestructingDeclarationEntry(LighterASTNode entry, boolean isVar) throws Exception {
        LighterASTNode lighterASTNode;
        ArrayList arrayList = new ArrayList();
        LighterASTNode[] childrenAsArray = getChildrenAsArray(entry);
        int length = childrenAsArray.length;
        boolean z = isVar;
        String asText = null;
        KtSourceElement firSourceElement$default = null;
        FirTypeRef implicitType = null;
        Name referencedNameAsName = null;
        boolean z2 = false;
        for (int i = 0; i < length && (lighterASTNode = childrenAsArray[i]) != null; i++) {
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType())) {
                IElementType tokenType = lighterASTNode.getTokenType();
                if (Intrinsics.areEqual(tokenType, KtNodeTypes.MODIFIER_LIST)) {
                    convertAnnotationsOnlyTo(lighterASTNode, arrayList);
                } else if (Intrinsics.areEqual(tokenType, KtTokens.IDENTIFIER)) {
                    asText = getAsText(lighterASTNode);
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.TYPE_REFERENCE)) {
                    implicitType = convertType(lighterASTNode);
                } else if (Intrinsics.areEqual(tokenType, KtTokens.VAL_KEYWORD)) {
                    z2 = true;
                } else if (Intrinsics.areEqual(tokenType, KtTokens.VAR_KEYWORD)) {
                    z = true;
                    z2 = true;
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.REFERENCE_EXPRESSION)) {
                    referencedNameAsName = getReferencedNameAsName(lighterASTNode);
                    firSourceElement$default = AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode, null, 1, null);
                }
            }
        }
        Name nameNameAsSafeName$default = Intrinsics.areEqual(asText, InlineClassManglingUtilsKt.NOT_INLINE_CLASS_PARAMETER_PLACEHOLDER) ? SpecialNames.UNDERSCORE_FOR_UNUSED_VAR : ConverterUtilKt.nameAsSafeName$default(asText, null, 1, null);
        KtSourceElement firSourceElement$default2 = AbstractRawFirBuilder.toFirSourceElement$default(this, entry, null, 1, null);
        if (implicitType == null) {
            implicitType = getImplicitType();
        }
        return new DestructuringEntry(firSourceElement$default2, firSourceElement$default, implicitType, nameNameAsSafeName$default, referencedNameAsName, z, z2, arrayList);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
    private final FirEnumEntry convertEnumEntry(LighterASTNode enumEntry, ClassWrapper classWrapper) throws Throwable {
        String asText;
        int i;
        boolean containerIsExpect;
        int size;
        int i2;
        FirAnonymousObjectExpressionBuilder firAnonymousObjectExpressionBuilder;
        CompanionBlockCollector companionBlockCollector;
        FirAnonymousObjectBuilder firAnonymousObjectBuilder;
        ClassKind classKind;
        LighterASTNode lighterASTNode;
        ClassWrapper classWrapper2;
        LighterASTNode lighterASTNode2;
        FirAnonymousObjectExpression firAnonymousObjectExpressionMo288build;
        FirEnumEntryBuilder firEnumEntryBuilder;
        ArrayList arrayList = new ArrayList();
        KtToken ktToken = KtTokens.IDENTIFIER;
        ktToken.getClass();
        LighterASTNode childNodeByType = getChildNodeByType(enumEntry, (IElementType) ktToken);
        boolean z = false;
        if (childNodeByType != null) {
            asText = getAsText(childNodeByType);
            Unit unit = Unit.INSTANCE;
        } else {
            asText = null;
        }
        if (asText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("identifier");
            asText = null;
        }
        int i3 = 1;
        FirBasedSymbol<?> firBasedSymbolNameAsSafeName$default = ConverterUtilKt.nameAsSafeName$default(asText, null, 1, null);
        boolean z2 = classWrapper.hasExpect() || getContext().getContainerIsExpect();
        FirEnumEntryBuilder firEnumEntryBuilder2 = new FirEnumEntryBuilder();
        firEnumEntryBuilder2.setSymbol(new FirEnumEntrySymbol(new CallableId(getContext().getCurrentClassId(), firBasedSymbolNameAsSafeName$default)));
        FirBasedSymbol<?> symbol = firEnumEntryBuilder2.getSymbol();
        getContext().pushContainerSymbol(symbol);
        try {
            LighterASTNode[] childrenAsArray = getChildrenAsArray(enumEntry);
            int length = childrenAsArray.length;
            ModifierList modifierList = null;
            LighterASTNode lighterASTNode3 = null;
            LighterASTNode lighterASTNode4 = null;
            int i4 = 0;
            while (true) {
                if (i4 < length) {
                    LighterASTNode lighterASTNode5 = childrenAsArray[i4];
                    if (lighterASTNode5 == null) {
                        i = 1;
                        break;
                    }
                    if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode5.getTokenType())) {
                        IElementType tokenType = lighterASTNode5.getTokenType();
                        if (Intrinsics.areEqual(tokenType, KtNodeTypes.MODIFIER_LIST)) {
                            modifierList = convertModifierList$default(this, lighterASTNode5, false, 2, null);
                        } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.INITIALIZER_LIST)) {
                            CollectionsKt.addAll(arrayList, convertInitializerList(lighterASTNode5));
                            IElementType iElementType = KtNodeTypes.SUPER_TYPE_CALL_ENTRY;
                            iElementType.getClass();
                            LighterASTNode childNodeByType2 = getChildNodeByType(lighterASTNode5, iElementType);
                            if (childNodeByType2 != null) {
                                Unit unit2 = Unit.INSTANCE;
                                lighterASTNode4 = childNodeByType2;
                            }
                        } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.CLASS_BODY)) {
                            lighterASTNode3 = lighterASTNode5;
                        }
                    }
                    i4++;
                    z = false;
                    i3 = 1;
                } else {
                    i = i3;
                    break;
                }
            }
            firEnumEntryBuilder2.setSource(AbstractRawFirBuilder.toFirSourceElement$default(this, enumEntry, z, i, z));
            firEnumEntryBuilder2.setModuleData(getBaseModuleData());
            FirDeclarationOrigin.Source source = FirDeclarationOrigin.Source.INSTANCE;
            firEnumEntryBuilder2.setOrigin(source);
            firEnumEntryBuilder2.setReturnTypeRef(classWrapper.getDelegatedSelfTypeRef());
            firEnumEntryBuilder2.setName(firBasedSymbolNameAsSafeName$default);
            Visibilities.Public r6 = Visibilities.Public.INSTANCE;
            Modality modality = Modality.FINAL;
            FirDeclarationStatusImpl firDeclarationStatusImpl = new FirDeclarationStatusImpl(r6, modality);
            firDeclarationStatusImpl.setStatic(true);
            firDeclarationStatusImpl.setExpect(z2);
            firEnumEntryBuilder2.setStatus(firDeclarationStatusImpl);
            firEnumEntryBuilder2.setLocal(getContext().getInLocalContext());
            try {
                try {
                    try {
                        try {
                            try {
                                if (classWrapper.getHasDefaultConstructor()) {
                                    IElementType iElementType2 = KtNodeTypes.INITIALIZER_LIST;
                                    iElementType2.getClass();
                                    if (getChildNodeByType(enumEntry, iElementType2) == null && ((modifierList == null || modifierList.getAnnotations().isEmpty()) && lighterASTNode3 == null)) {
                                        getContext().popContainerSymbol(symbol);
                                        firEnumEntryBuilder = firEnumEntryBuilder2;
                                    }
                                    FirEnumEntry firEnumEntryMo288build = firEnumEntryBuilder.mo288build();
                                    ConeClassLikeType coneClassLikeTypeCurrentDispatchReceiverType = currentDispatchReceiverType();
                                    coneClassLikeTypeCurrentDispatchReceiverType.getClass();
                                    ClassMembersKt.setContainingClassForStaticMemberAttr(firEnumEntryMo288build, coneClassLikeTypeCurrentDispatchReceiverType.getLookupTag());
                                    return firEnumEntryMo288build;
                                }
                                if (getContext().getDispatchReceiverTypesStack().size() > i2 + 1) {
                                    throw new IllegalArgumentException(("Wrong number of " + getContext().getDispatchReceiverTypesStack().size()).toString());
                                }
                                if (getContext().getDispatchReceiverTypesStack().size() > i2) {
                                    getContext().getDispatchReceiverTypesStack().remove(CollectionsKt.getLastIndex(getContext().getDispatchReceiverTypesStack()));
                                }
                                getContext().setClassName(getContext().getClassName().parent());
                                getContext().setContainerIsExpect(containerIsExpect);
                                firEnumEntryBuilder = firEnumEntryBuilder2;
                                firEnumEntryBuilder.setInitializer(firAnonymousObjectExpressionMo288build);
                                getContext().popContainerSymbol(symbol);
                                FirEnumEntry firEnumEntryMo288build2 = firEnumEntryBuilder.mo288build();
                                ConeClassLikeType coneClassLikeTypeCurrentDispatchReceiverType2 = currentDispatchReceiverType();
                                coneClassLikeTypeCurrentDispatchReceiverType2.getClass();
                                ClassMembersKt.setContainingClassForStaticMemberAttr(firEnumEntryMo288build2, coneClassLikeTypeCurrentDispatchReceiverType2.getLookupTag());
                                return firEnumEntryMo288build2;
                            } catch (Throwable th) {
                                th = th;
                                firBasedSymbolNameAsSafeName$default = symbol;
                                getContext().popContainerSymbol(firBasedSymbolNameAsSafeName$default);
                                throw th;
                            }
                            PrimaryConstructor primaryConstructorConvertPrimaryConstructor$default = convertPrimaryConstructor$default(this, null, AbstractRawFirBuilder.toFirSourceElement$default(this, enumEntry, null, 1, null), classWrapper2, lighterASTNode != null ? (KtLightSourceElement) AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode, null, 1, null) : null, true, z2, false, false, 192, null);
                            if (primaryConstructorConvertPrimaryConstructor$default != null) {
                                firAnonymousObjectBuilder.getDeclarations().add(primaryConstructorConvertPrimaryConstructor$default.getFirConstructor());
                            }
                            if (lighterASTNode2 != null) {
                                Name name = SpecialNames.ANONYMOUS;
                                boolean forceKeepingTheBodyInHeaderMode = getContext().getForceKeepingTheBodyInHeaderMode();
                                getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
                                boolean inLocalContext = getContext().getInLocalContext();
                                getContext().setInLocalContext(true);
                                FqName classNameBeforeLocalContext = getContext().getClassNameBeforeLocalContext();
                                if (!inLocalContext) {
                                    getContext().setClassNameBeforeLocalContext(getContext().getClassName());
                                }
                                FqName className = getContext().getClassName();
                                getContext().setClassName(FqName.ROOT);
                                try {
                                    getContext().setClassName(getContext().getClassName().child(name));
                                    boolean containerIsExpect2 = getContext().getContainerIsExpect();
                                    getContext().setContainerIsExpect(containerIsExpect2);
                                    int size2 = getContext().getDispatchReceiverTypesStack().size();
                                    try {
                                        CollectionsKt.addAll(firAnonymousObjectBuilder.getDeclarations(), convertClassBody(lighterASTNode2, classWrapper2));
                                        if (getContext().getDispatchReceiverTypesStack().size() > size2 + 1) {
                                            throw new IllegalArgumentException(("Wrong number of " + getContext().getDispatchReceiverTypesStack().size()).toString());
                                        }
                                        if (getContext().getDispatchReceiverTypesStack().size() > size2) {
                                            getContext().getDispatchReceiverTypesStack().remove(CollectionsKt.getLastIndex(getContext().getDispatchReceiverTypesStack()));
                                        }
                                        getContext().setClassName(getContext().getClassName().parent());
                                        getContext().setContainerIsExpect(containerIsExpect2);
                                        getContext().setClassNameBeforeLocalContext(classNameBeforeLocalContext);
                                        getContext().setInLocalContext(inLocalContext);
                                        getContext().setClassName(className);
                                        getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
                                    } catch (Throwable th2) {
                                        if (getContext().getDispatchReceiverTypesStack().size() > size2 + 1) {
                                            throw new IllegalArgumentException(("Wrong number of " + getContext().getDispatchReceiverTypesStack().size()).toString());
                                        }
                                        if (getContext().getDispatchReceiverTypesStack().size() > size2) {
                                            getContext().getDispatchReceiverTypesStack().remove(CollectionsKt.getLastIndex(getContext().getDispatchReceiverTypesStack()));
                                        }
                                        getContext().setClassName(getContext().getClassName().parent());
                                        getContext().setContainerIsExpect(containerIsExpect2);
                                        throw th2;
                                    }
                                } catch (Throwable th3) {
                                    getContext().setClassNameBeforeLocalContext(classNameBeforeLocalContext);
                                    getContext().setInLocalContext(inLocalContext);
                                    getContext().setClassName(className);
                                    getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
                                    throw th3;
                                }
                            }
                            FirAnonymousObject firAnonymousObjectMo288build = firAnonymousObjectBuilder.mo288build();
                            CompanionBlockInfo companionBlockInfoOrNull = companionBlockCollector.toCompanionBlockInfoOrNull();
                            if (companionBlockInfoOrNull != null) {
                                ClassMembersKt.setCompanionBlocks(firAnonymousObjectMo288build, companionBlockInfoOrNull);
                            }
                            firAnonymousObjectExpressionBuilder.setAnonymousObject(firAnonymousObjectMo288build);
                            firAnonymousObjectExpressionMo288build = firAnonymousObjectExpressionBuilder.mo288build();
                        } catch (Throwable th4) {
                            th = th4;
                            if (getContext().getDispatchReceiverTypesStack().size() > i2 + 1) {
                                throw new IllegalArgumentException(("Wrong number of " + getContext().getDispatchReceiverTypesStack().size()).toString());
                            }
                            if (getContext().getDispatchReceiverTypesStack().size() > i2) {
                                getContext().getDispatchReceiverTypesStack().remove(CollectionsKt.getLastIndex(getContext().getDispatchReceiverTypesStack()));
                            }
                            getContext().setClassName(getContext().getClassName().parent());
                            getContext().setContainerIsExpect(containerIsExpect);
                            throw th;
                        }
                        firAnonymousObjectBuilder.setSymbol(new FirAnonymousObjectSymbol(getContext().getPackageFqName()));
                        firAnonymousObjectBuilder.setStatus(new FirDeclarationStatusImpl(Visibilities.Local.INSTANCE, modality));
                        if (modifierList == null) {
                            modifierList = new ModifierList(0L, 1, null);
                        }
                        ModifierList modifierList2 = modifierList;
                        IElementType iElementType3 = KtNodeTypes.SECONDARY_CONSTRUCTOR;
                        iElementType3.getClass();
                        boolean z3 = !getChildNodesByType(lighterASTNode3, iElementType3).isEmpty();
                        FirResolvedTypeRefBuilder firResolvedTypeRefBuilder = new FirResolvedTypeRefBuilder();
                        firResolvedTypeRefBuilder.setConeType(new ConeClassLikeTypeImpl(firAnonymousObjectBuilder.getSymbol().getLookupTag(), ConeTypeProjection.Companion.getEMPTY_ARRAY(), false, null, 8, null));
                        firResolvedTypeRefBuilder.setSource(toFirSourceElement(enumEntry, (KtFakeSourceElementKind) KtFakeSourceElementKind.ClassSelfTypeRef.INSTANCE));
                        Unit unit3 = Unit.INSTANCE;
                        FirResolvedTypeRef firResolvedTypeRefBuild = firResolvedTypeRefBuilder.build();
                        registerSelfType(firResolvedTypeRefBuild);
                        lighterASTNode = lighterASTNode4;
                        classWrapper2 = new ClassWrapper(modifierList2, classKind, firAnonymousObjectBuilder, z3, false, firResolvedTypeRefBuild, classWrapper.getDelegatedSelfTypeRef(), CollectionsKt.listOf(new DelegatedConstructorWrapper(classWrapper.getDelegatedSelfTypeRef(), arrayList, lighterASTNode != null ? (KtLightSourceElement) AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode, null, 1, null) : null)), companionBlockCollector);
                        firAnonymousObjectBuilder.getSuperTypeRefs().add(classWrapper2.getDelegatedSuperTypeRef());
                        lighterASTNode2 = lighterASTNode3;
                        i2 = size;
                    } catch (Throwable th5) {
                        th = th5;
                        i2 = size;
                    }
                    firAnonymousObjectExpressionBuilder = new FirAnonymousObjectExpressionBuilder();
                    KtSourceElement firSourceElement = toFirSourceElement(enumEntry, (KtFakeSourceElementKind) KtFakeSourceElementKind.EnumInitializer.INSTANCE);
                    firAnonymousObjectExpressionBuilder.setSource(firSourceElement);
                    companionBlockCollector = new CompanionBlockCollector();
                    firAnonymousObjectBuilder = new FirAnonymousObjectBuilder();
                    firAnonymousObjectBuilder.setSource(firSourceElement);
                    firAnonymousObjectBuilder.setModuleData(getBaseModuleData());
                    firAnonymousObjectBuilder.setOrigin(source);
                    classKind = ClassKind.ENUM_ENTRY;
                    firAnonymousObjectBuilder.setClassKind(classKind);
                    firAnonymousObjectBuilder.setScopeProvider(this.baseScopeProvider);
                } catch (Throwable th6) {
                    th = th6;
                    i2 = size;
                }
                getContext().setClassName(getContext().getClassName().child(firBasedSymbolNameAsSafeName$default));
                containerIsExpect = getContext().getContainerIsExpect();
                getContext().setContainerIsExpect(containerIsExpect);
                size = getContext().getDispatchReceiverTypesStack().size();
            } catch (Throwable th7) {
                th = th7;
            }
            if (modifierList != null) {
                convertAnnotationsTo(modifierList, firEnumEntryBuilder2.getAnnotations());
                Unit unit4 = Unit.INSTANCE;
            }
        } catch (Throwable th8) {
            th = th8;
            firBasedSymbolNameAsSafeName$default = symbol;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
    private final FirTypeRef convertExplicitDelegation(LighterASTNode explicitDelegation, Map<Integer, FirFieldSymbol> delegateFieldsMap, int index) throws Exception {
        FirTypeRef firTypeRef;
        FirExpression firExpressionBuildErrorExpression$default;
        KtSourceElement firSourceElement$default;
        KtSourceElement source;
        KtSourceElement firSourceElement$default2;
        FirTypeRef firTypeRefConvertType = null;
        LighterASTNode lighterASTNode = null;
        for (LighterASTNode lighterASTNode2 : getChildrenAsArray(explicitDelegation)) {
            if (lighterASTNode2 == null) {
                break;
            }
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode2.getTokenType())) {
                if (Intrinsics.areEqual(lighterASTNode2.getTokenType(), KtNodeTypes.TYPE_REFERENCE)) {
                    firTypeRefConvertType = convertType(lighterASTNode2);
                } else if (ElementTypeUtils.INSTANCE.isExpression(lighterASTNode2)) {
                    lighterASTNode = lighterASTNode2;
                }
            }
        }
        Integer numValueOf = Integer.valueOf(index);
        FirFieldBuilder firFieldBuilder = new FirFieldBuilder();
        firFieldBuilder.setSource(KtSourceElementKt.fakeElement$default(AbstractRawFirBuilder.toFirSourceElement$default(this, explicitDelegation, null, 1, null), KtFakeSourceElementKind.ClassDelegationField.INSTANCE, null, 2, null));
        firFieldBuilder.setModuleData(getBaseModuleData());
        firFieldBuilder.setOrigin(FirDeclarationOrigin.Synthetic.DelegateField.INSTANCE);
        firFieldBuilder.setName(NameUtils.delegateFieldName(delegateFieldsMap.size()));
        firFieldBuilder.setSymbol(new FirFieldSymbol(new CallableId(getContext().getCurrentClassId(), firFieldBuilder.getName())));
        if (firTypeRefConvertType == null) {
            Intrinsics.throwUninitializedPropertyAccessException("firTypeRef");
            firTypeRef = null;
        } else {
            firTypeRef = firTypeRefConvertType;
        }
        firFieldBuilder.setReturnTypeRef(firTypeRef);
        FirFieldSymbol symbol = firFieldBuilder.getSymbol();
        getContext().pushContainerSymbol(symbol);
        try {
            if (lighterASTNode != null) {
                LightTreeRawFirExpressionBuilder lightTreeRawFirExpressionBuilder = this.expressionConverter;
                FirStatement asFirStatement = lightTreeRawFirExpressionBuilder.getAsFirStatement(lighterASTNode, "Should have delegate");
                if (!(asFirStatement instanceof FirExpression)) {
                    if (asFirStatement == null || (source = asFirStatement.getSource()) == null || (firSourceElement$default = KtSourceElementKt.realElement(source)) == null) {
                        firSourceElement$default = AbstractRawFirBuilder.toFirSourceElement$default(lightTreeRawFirExpressionBuilder, lighterASTNode, null, 1, null);
                    }
                    firExpressionBuildErrorExpression$default = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default, new ConeSimpleDiagnostic("Should have delegate", DiagnosticKind.ExpressionExpected), asFirStatement);
                } else if (UtilsKt.isStatementLikeExpression((FirExpression) asFirStatement)) {
                    KtSourceElement source2 = ((FirExpression) asFirStatement).getSource();
                    if (source2 == null || (firSourceElement$default2 = KtSourceElementKt.realElement(source2)) == null) {
                        firSourceElement$default2 = AbstractRawFirBuilder.toFirSourceElement$default(lightTreeRawFirExpressionBuilder, lighterASTNode, null, 1, null);
                    }
                    firExpressionBuildErrorExpression$default = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default2, new ConeSimpleDiagnostic("Should have delegate", DiagnosticKind.ExpressionExpected), asFirStatement);
                } else {
                    firExpressionBuildErrorExpression$default = (FirExpression) asFirStatement;
                }
                if (firExpressionBuildErrorExpression$default == null) {
                    throw new NullPointerException("null cannot be cast to non-null type org.jetbrains.kotlin.fir.expressions.FirExpression");
                }
            } else {
                firExpressionBuildErrorExpression$default = FirExpressionUtilKt.buildErrorExpression$default(AbstractRawFirBuilder.toFirSourceElement$default(this, explicitDelegation, null, 1, null), new ConeSyntaxDiagnostic("Should have delegate"), null, 4, null);
            }
            firFieldBuilder.setInitializer(firExpressionBuildErrorExpression$default);
            Unit unit = Unit.INSTANCE;
            getContext().popContainerSymbol(symbol);
            firFieldBuilder.setVar(false);
            firFieldBuilder.setStatus(new FirDeclarationStatusImpl(Visibilities.Private.INSTANCE, Modality.FINAL));
            firFieldBuilder.setLocal(getContext().getInLocalContext());
            firFieldBuilder.setDispatchReceiverType(currentDispatchReceiverType());
            delegateFieldsMap.put(numValueOf, firFieldBuilder.mo288build().getSymbol());
            return firTypeRefConvertType;
        } catch (Throwable th) {
            getContext().popContainerSymbol(symbol);
            throw th;
        }
    }

    private final Pair<FirBlock, FirContractDescription> convertFunctionBody(LighterASTNode blockNode, LighterASTNode expression, boolean allowLegacyContractDescription) throws Exception {
        KtSourceElement firSourceElement$default;
        FirExpression firExpressionBuildErrorExpression;
        KtSourceElement source;
        KtSourceElement firSourceElement$default2;
        boolean z = this.headerMode && !getContext().getForceKeepingTheBodyInHeaderMode();
        FirContractDescription firContractDescriptionProcessLegacyContractDescription = null;
        ConeDiagnostic coneDiagnostic = null;
        if (blockNode != null) {
            FirBlock firBlockConvertBlock = convertBlock(blockNode, z);
            if (allowLegacyContractDescription) {
                KtSourceElement source2 = firBlockConvertBlock.getSource();
                if (source2 == null || !isCallTheFirstStatement(source2)) {
                    coneDiagnostic = ConeContractShouldBeFirstStatement.INSTANCE;
                } else if (functionCallHasLabel(source2)) {
                    coneDiagnostic = ConeContractMayNotHaveLabel.INSTANCE;
                }
                firContractDescriptionProcessLegacyContractDescription = ConversionUtilsKt.processLegacyContractDescription(firBlockConvertBlock, coneDiagnostic);
            }
            return z ? TuplesKt.to(FirEmptyExpressionBlockBuilderKt.buildEmptyExpressionBlock(), firContractDescriptionProcessLegacyContractDescription) : TuplesKt.to(firBlockConvertBlock, firContractDescriptionProcessLegacyContractDescription);
        }
        if (expression == null) {
            return TuplesKt.to(null, null);
        }
        if (z) {
            return TuplesKt.to(FirEmptyExpressionBlockBuilderKt.buildEmptyExpressionBlock(), null);
        }
        LightTreeRawFirExpressionBuilder lightTreeRawFirExpressionBuilder = this.expressionConverter;
        FirStatement asFirStatement = lightTreeRawFirExpressionBuilder.getAsFirStatement(expression, "Function has no body (but should)");
        if (asFirStatement instanceof FirExpression) {
            firExpressionBuildErrorExpression = (FirExpression) asFirStatement;
            if (UtilsKt.isStatementLikeExpression(firExpressionBuildErrorExpression)) {
                KtSourceElement source3 = firExpressionBuildErrorExpression.getSource();
                if (source3 == null || (firSourceElement$default2 = KtSourceElementKt.realElement(source3)) == null) {
                    firSourceElement$default2 = AbstractRawFirBuilder.toFirSourceElement$default(lightTreeRawFirExpressionBuilder, expression, null, 1, null);
                }
                firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default2, new ConeSimpleDiagnostic("Function has no body (but should)", DiagnosticKind.ExpressionExpected), asFirStatement);
            }
        } else {
            if (asFirStatement == null || (source = asFirStatement.getSource()) == null || (firSourceElement$default = KtSourceElementKt.realElement(source)) == null) {
                firSourceElement$default = AbstractRawFirBuilder.toFirSourceElement$default(lightTreeRawFirExpressionBuilder, expression, null, 1, null);
            }
            firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default, new ConeSimpleDiagnostic("Function has no body (but should)", DiagnosticKind.ExpressionExpected), asFirStatement);
        }
        FirExpression firExpression = firExpressionBuildErrorExpression;
        if (firExpression != null) {
            return TuplesKt.to(new FirSingleExpressionBlock(AbstractRawFirBuilder.toReturn$default(this, firExpression, null, null, false, 7, null)), null);
        }
        x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.expressions.FirExpression");
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    private final FirTypeRef convertFunctionType(KtSourceElement typeRefSource, LighterASTNode functionType, List<? extends ModifierList> allTypeModifiers, boolean isNullable) throws Exception {
        boolean z;
        Object objBuild;
        ArrayList arrayList = new ArrayList();
        FirTypeRef firTypeRefConvertReceiverType = null;
        FirTypeRef firTypeRefConvertType = null;
        LighterASTNode lighterASTNode = null;
        for (LighterASTNode lighterASTNode2 : getChildrenAsArray(functionType)) {
            if (lighterASTNode2 == null) {
                break;
            }
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode2.getTokenType())) {
                IElementType tokenType = lighterASTNode2.getTokenType();
                if (Intrinsics.areEqual(tokenType, KtNodeTypes.FUNCTION_TYPE_RECEIVER)) {
                    firTypeRefConvertReceiverType = convertReceiverType(lighterASTNode2);
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.VALUE_PARAMETER_LIST)) {
                    CollectionsKt.addAll(arrayList, convertFunctionTypeParameters(lighterASTNode2));
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.TYPE_REFERENCE)) {
                    firTypeRefConvertType = convertType(lighterASTNode2);
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.CONTEXT_PARAMETER_LIST)) {
                    lighterASTNode = lighterASTNode2;
                }
            }
        }
        FirFunctionTypeRefBuilder firFunctionTypeRefBuilder = new FirFunctionTypeRefBuilder();
        firFunctionTypeRefBuilder.setSource(typeRefSource);
        firFunctionTypeRefBuilder.setMarkedNullable(isNullable);
        firFunctionTypeRefBuilder.setReceiverTypeRef(firTypeRefConvertReceiverType);
        if (firTypeRefConvertType == null) {
            Intrinsics.throwUninitializedPropertyAccessException("returnTypeReference");
            firTypeRefConvertType = null;
        }
        firFunctionTypeRefBuilder.setReturnTypeRef(firTypeRefConvertType);
        CollectionsKt.addAll(firFunctionTypeRefBuilder.getParameters(), arrayList);
        List<? extends ModifierList> list = allTypeModifiers;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            Iterator<T> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                if (((ModifierList) it.next()).hasSuspend()) {
                    z = true;
                    break;
                }
            }
        } else {
            z = false;
            break;
        }
        firFunctionTypeRefBuilder.setSuspend(z);
        if (lighterASTNode != null) {
            for (LighterASTNode lighterASTNode3 : getChildrenAsArray(lighterASTNode)) {
                if (lighterASTNode3 == null) {
                    break;
                }
                if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode3.getTokenType())) {
                    IElementType elementType = getElementType(lighterASTNode3);
                    if (Intrinsics.areEqual(elementType, KtNodeTypes.CONTEXT_RECEIVER) || Intrinsics.areEqual(elementType, KtNodeTypes.VALUE_PARAMETER)) {
                        IElementType iElementType = KtNodeTypes.TYPE_REFERENCE;
                        iElementType.getClass();
                        LighterASTNode childNodeByType = getChildNodeByType(lighterASTNode3, iElementType);
                        List contextParameterTypeRefs = firFunctionTypeRefBuilder.getContextParameterTypeRefs();
                        if (childNodeByType == null || (objBuild = convertType(childNodeByType)) == null) {
                            FirErrorTypeRefBuilder firErrorTypeRefBuilder = new FirErrorTypeRefBuilder();
                            firErrorTypeRefBuilder.setDiagnostic(new ConeSimpleDiagnostic("Type missing", null, 2, null));
                            objBuild = firErrorTypeRefBuilder.build();
                        }
                        contextParameterTypeRefs.add(objBuild);
                    }
                }
            }
        }
        return firFunctionTypeRefBuilder.build();
    }

    public static /* synthetic */ FirTypeRef convertFunctionType$default(LightTreeRawFirDeclarationBuilder lightTreeRawFirDeclarationBuilder, KtSourceElement ktSourceElement, LighterASTNode lighterASTNode, List list, boolean z, int i, Object obj) throws Exception {
        if ((i & 8) != 0) {
            z = false;
        }
        return lightTreeRawFirDeclarationBuilder.convertFunctionType(ktSourceElement, lighterASTNode, list, z);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    private final List<FirFunctionTypeParameter> convertFunctionTypeParameters(LighterASTNode parameters) throws UninitializedPropertyAccessException {
        LighterASTNode lighterASTNode;
        LighterASTNode[] childrenAsArray = getChildrenAsArray(parameters);
        ArrayList arrayList = new ArrayList();
        for (LighterASTNode lighterASTNode2 : childrenAsArray) {
            if (lighterASTNode2 == null) {
                break;
            }
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode2.getTokenType()) && Intrinsics.areEqual(lighterASTNode2.getTokenType(), KtNodeTypes.VALUE_PARAMETER)) {
                LighterASTNode[] childrenAsArray2 = getChildrenAsArray(lighterASTNode2);
                int length = childrenAsArray2.length;
                Name nameNameAsSafeName$default = null;
                FirResolvedTypeRef firResolvedTypeRefCreateNoTypeForParameterTypeRef = null;
                for (int i = 0; i < length && (lighterASTNode = childrenAsArray2[i]) != null; i++) {
                    if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType())) {
                        IElementType tokenType = lighterASTNode.getTokenType();
                        if (Intrinsics.areEqual(tokenType, KtTokens.IDENTIFIER)) {
                            nameNameAsSafeName$default = ConverterUtilKt.nameAsSafeName$default(getAsText(lighterASTNode), null, 1, null);
                        } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.TYPE_REFERENCE)) {
                            firResolvedTypeRefCreateNoTypeForParameterTypeRef = convertType(lighterASTNode);
                        }
                    }
                }
                FirFunctionTypeParameterBuilder firFunctionTypeParameterBuilder = new FirFunctionTypeParameterBuilder();
                KtSourceElement ktSourceElement = (KtLightSourceElement) AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode2, null, 1, null);
                firFunctionTypeParameterBuilder.setSource(ktSourceElement);
                firFunctionTypeParameterBuilder.setName(nameNameAsSafeName$default);
                if (firResolvedTypeRefCreateNoTypeForParameterTypeRef == null) {
                    firResolvedTypeRefCreateNoTypeForParameterTypeRef = createNoTypeForParameterTypeRef(ktSourceElement);
                }
                firFunctionTypeParameterBuilder.setReturnTypeRef(firResolvedTypeRefCreateNoTypeForParameterTypeRef);
                arrayList.add(firFunctionTypeParameterBuilder.build());
            }
        }
        return arrayList;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    private final FirPropertyAccessor convertGetterOrSetter(LighterASTNode getterOrSetter, FirTypeRef propertyTypeRef, Visibility propertyVisibility, FirPropertySymbol propertySymbol, ModifierList propertyModifiers, List<? extends FirAnnotationCall> propertyAnnotations, boolean isCompanionBlockMember) throws Exception {
        int i;
        Object obj;
        char c;
        FirImplicitBuiltinTypeRef firImplicitBuiltinTypeRefCopyWithNewSourceKind = UtilsKt.copyWithNewSourceKind(propertyTypeRef, KtFakeSourceElementKind.ImplicitTypeRef.INSTANCE);
        KtSourceElement ktSourceElement = (KtLightSourceElement) AbstractRawFirBuilder.toFirSourceElement$default(this, getterOrSetter, null, 1, null);
        FirPropertyAccessorSymbol firPropertyAccessorSymbol = new FirPropertyAccessorSymbol();
        FirDefaultSetterValueParameterBuilder firDefaultSetterValueParameterBuilder = new FirDefaultSetterValueParameterBuilder();
        firDefaultSetterValueParameterBuilder.setModuleData(getBaseModuleData());
        firDefaultSetterValueParameterBuilder.setContainingDeclarationSymbol(firPropertyAccessorSymbol);
        firDefaultSetterValueParameterBuilder.setOrigin(FirDeclarationOrigin.Source.INSTANCE);
        firDefaultSetterValueParameterBuilder.setSource(KtSourceElementKt.fakeElement$default(ktSourceElement, KtFakeSourceElementKind.DefaultAccessor.INSTANCE, null, 2, null));
        firDefaultSetterValueParameterBuilder.setReturnTypeRef(firImplicitBuiltinTypeRefCopyWithNewSourceKind);
        firDefaultSetterValueParameterBuilder.setSymbol(new FirValueParameterSymbol());
        FirValueParameter firValueParameterMo288build = firDefaultSetterValueParameterBuilder.mo288build();
        LighterASTNode[] childrenAsArray = getChildrenAsArray(getterOrSetter);
        int length = childrenAsArray.length;
        ModifierList modifierList = null;
        LighterASTNode lighterASTNode = null;
        LighterASTNode lighterASTNode2 = null;
        FirImplicitBuiltinTypeRef firImplicitBuiltinTypeRefConvertType = null;
        FirContractDescription firContractDescriptionObtainContractDescription = null;
        boolean z = true;
        int i2 = 0;
        while (i2 < length) {
            LighterASTNode lighterASTNode3 = childrenAsArray[i2];
            if (lighterASTNode3 == null) {
                break;
            }
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode3.getTokenType())) {
                if (Intrinsics.areEqual(getAsText(lighterASTNode3), "set")) {
                    z = false;
                }
                IElementType tokenType = lighterASTNode3.getTokenType();
                if (Intrinsics.areEqual(tokenType, KtTokens.SET_KEYWORD)) {
                    z = false;
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.MODIFIER_LIST)) {
                    childrenAsArray = childrenAsArray;
                    c = 2;
                    modifierList = convertModifierList$default(this, lighterASTNode3, false, 2, null);
                } else {
                    childrenAsArray = childrenAsArray;
                    c = 2;
                    if (Intrinsics.areEqual(tokenType, KtNodeTypes.TYPE_REFERENCE)) {
                        firImplicitBuiltinTypeRefConvertType = convertType(lighterASTNode3);
                    } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.VALUE_PARAMETER_LIST)) {
                        if (!z) {
                            firValueParameterMo288build = convertSetterParameter(lighterASTNode3, firPropertyAccessorSymbol, firImplicitBuiltinTypeRefCopyWithNewSourceKind, ConversionUtilsKt.filterUseSiteTarget(propertyAnnotations, AnnotationUseSiteTarget.SETTER_PARAMETER));
                        }
                    } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.CONTRACT_EFFECT_LIST)) {
                        firContractDescriptionObtainContractDescription = obtainContractDescription(lighterASTNode3);
                    } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.BLOCK)) {
                        lighterASTNode = lighterASTNode3;
                    } else if (ElementTypeUtils.INSTANCE.isExpression(lighterASTNode3)) {
                        lighterASTNode2 = lighterASTNode3;
                    }
                }
                i2++;
                childrenAsArray = childrenAsArray;
            }
            c = 2;
            i2++;
            childrenAsArray = childrenAsArray;
        }
        if (modifierList == null) {
            i = 1;
            obj = null;
            modifierList = new ModifierList(0L, 1, null);
        } else {
            i = 1;
            obj = null;
        }
        Visibility visibility$default = ModifierList.getVisibility$default(modifierList, false, i, obj);
        if (Intrinsics.areEqual(visibility$default, Visibilities.Unknown.INSTANCE)) {
            visibility$default = propertyVisibility;
        }
        FirDeclarationStatusImpl firDeclarationStatusImpl = new FirDeclarationStatusImpl(visibility$default, modifierList.getModality(false));
        firDeclarationStatusImpl.setInline(propertyModifiers.hasInline() || modifierList.hasInline());
        firDeclarationStatusImpl.setExternal(propertyModifiers.hasExternal() || modifierList.hasExternal());
        firDeclarationStatusImpl.setExpect(propertyModifiers.hasExpect() || modifierList.hasExpect());
        firDeclarationStatusImpl.setStatic(propertyModifiers.hasCompanion() || isCompanionBlockMember);
        List<FirAnnotationCall> listFilterUseSiteTarget = ConversionUtilsKt.filterUseSiteTarget(propertyAnnotations, z ? AnnotationUseSiteTarget.PROPERTY_GETTER : AnnotationUseSiteTarget.PROPERTY_SETTER);
        List<FirAnnotationCall> listConvertAnnotations = convertAnnotations(modifierList);
        LighterASTNode lighterASTNode4 = lighterASTNode;
        LighterASTNode lighterASTNode5 = lighterASTNode2;
        if (lighterASTNode4 == null && lighterASTNode5 == null) {
            FirValueParameter firValueParameter = firValueParameterMo288build;
            FirDefaultPropertyAccessor firDefaultPropertyAccessorCreateGetterOrSetter = FirDefaultPropertyAccessor.INSTANCE.createGetterOrSetter(ktSourceElement, getBaseModuleData(), FirDeclarationOrigin.Source.INSTANCE, firImplicitBuiltinTypeRefCopyWithNewSourceKind, visibility$default, propertySymbol, z, (128 & 128) != 0 ? CollectionsKt.emptyList() : null, (128 & 256) != 0 ? null : firValueParameterMo288build.getSource());
            firDefaultPropertyAccessorCreateGetterOrSetter.replaceAnnotations(CollectionsKt.plus(listConvertAnnotations, listFilterUseSiteTarget));
            firDefaultPropertyAccessorCreateGetterOrSetter.setStatus(firDeclarationStatusImpl);
            initContainingClassAttr(firDefaultPropertyAccessorCreateGetterOrSetter);
            FirValueParameter firValueParameter2 = (FirValueParameter) CollectionsKt.firstOrNull(firDefaultPropertyAccessorCreateGetterOrSetter.getValueParameters());
            if (firValueParameter2 != null) {
                firValueParameter2.replaceReturnTypeRef(firValueParameter.getReturnTypeRef());
            }
            return firDefaultPropertyAccessorCreateGetterOrSetter;
        }
        FirValueParameter firValueParameter3 = firValueParameterMo288build;
        boolean z2 = z;
        FirFunctionTarget firFunctionTarget = new FirFunctionTarget(null, false);
        FirPropertyAccessorBuilder firPropertyAccessorBuilder = new FirPropertyAccessorBuilder();
        firPropertyAccessorBuilder.setSource(ktSourceElement);
        firPropertyAccessorBuilder.setModuleData(getBaseModuleData());
        firPropertyAccessorBuilder.setOrigin(FirDeclarationOrigin.Source.INSTANCE);
        if (firImplicitBuiltinTypeRefConvertType != null) {
            firImplicitBuiltinTypeRefCopyWithNewSourceKind = firImplicitBuiltinTypeRefConvertType;
        } else if (!z2) {
            firImplicitBuiltinTypeRefCopyWithNewSourceKind = getImplicitUnitType();
        }
        firPropertyAccessorBuilder.setReturnTypeRef(firImplicitBuiltinTypeRefCopyWithNewSourceKind);
        firPropertyAccessorBuilder.setSymbol(firPropertyAccessorSymbol);
        firPropertyAccessorBuilder.setGetter(z2);
        firPropertyAccessorBuilder.setStatus(firDeclarationStatusImpl);
        getContext().getFirFunctionTargets().add(firFunctionTarget);
        CollectionsKt.addAll(firPropertyAccessorBuilder.getAnnotations(), listFilterUseSiteTarget);
        CollectionsKt.addAll(firPropertyAccessorBuilder.getAnnotations(), listConvertAnnotations);
        if (!z2) {
            firPropertyAccessorBuilder.getValueParameters().add(firValueParameter3);
        }
        boolean z3 = firContractDescriptionObtainContractDescription == null;
        boolean z4 = (propertyTypeRef instanceof FirImplicitTypeRef) || firDeclarationStatusImpl.isInline();
        boolean forceKeepingTheBodyInHeaderMode = getContext().getForceKeepingTheBodyInHeaderMode();
        getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode || z4);
        boolean inLocalContext = getContext().getInLocalContext();
        getContext().setInLocalContext(true);
        FqName classNameBeforeLocalContext = getContext().getClassNameBeforeLocalContext();
        if (!inLocalContext) {
            getContext().setClassNameBeforeLocalContext(getContext().getClassName());
        }
        FqName className = getContext().getClassName();
        getContext().setClassName(FqName.ROOT);
        try {
            Pair<FirBlock, FirContractDescription> pairConvertFunctionBody = convertFunctionBody(lighterASTNode4, lighterASTNode5, z3);
            getContext().setClassNameBeforeLocalContext(classNameBeforeLocalContext);
            getContext().setInLocalContext(inLocalContext);
            getContext().setClassName(className);
            getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
            firPropertyAccessorBuilder.setBody((FirBlock) pairConvertFunctionBody.getFirst());
            if (firContractDescriptionObtainContractDescription == null) {
                firContractDescriptionObtainContractDescription = (FirContractDescription) pairConvertFunctionBody.getSecond();
            }
            FirContractDescription firContractDescription = firContractDescriptionObtainContractDescription;
            if (firContractDescription != null) {
                firPropertyAccessorBuilder.setContractDescription(firContractDescription);
            }
            removeLast(getContext().getFirFunctionTargets());
            firPropertyAccessorBuilder.setPropertySymbol(propertySymbol);
            FirPropertyAccessor firPropertyAccessorMo288build = firPropertyAccessorBuilder.mo288build();
            firFunctionTarget.bind(firPropertyAccessorMo288build);
            initContainingClassAttr(firPropertyAccessorMo288build);
            return firPropertyAccessorMo288build;
        } catch (Throwable th) {
            getContext().setClassNameBeforeLocalContext(classNameBeforeLocalContext);
            getContext().setInLocalContext(inLocalContext);
            getContext().setClassName(className);
            getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
            throw th;
        }
    }

    private final Pair<String, KtSourceElement> convertImportAlias(LighterASTNode importAlias) {
        LighterASTNode lighterASTNode;
        LighterASTNode[] childrenAsArray = getChildrenAsArray(importAlias);
        int length = childrenAsArray.length;
        for (int i = 0; i < length && (lighterASTNode = childrenAsArray[i]) != null; i++) {
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType()) && Intrinsics.areEqual(lighterASTNode.getTokenType(), KtTokens.IDENTIFIER)) {
                return new Pair<>(getAsText(lighterASTNode), AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode, null, 1, null));
            }
        }
        return null;
    }

    private final FirImport convertImportDirective(LighterASTNode importDirective) {
        LighterASTNode lighterASTNode;
        Pair<String, KtSourceElement> pairConvertImportAlias;
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        LighterASTNode[] childrenAsArray = getChildrenAsArray(importDirective);
        int length = childrenAsArray.length;
        boolean z = false;
        Object first = null;
        Object second = null;
        for (int i = 0; i < length && (lighterASTNode = childrenAsArray[i]) != null; i++) {
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType())) {
                IElementType tokenType = lighterASTNode.getTokenType();
                if (Intrinsics.areEqual(tokenType, KtNodeTypes.REFERENCE_EXPRESSION) || Intrinsics.areEqual(tokenType, KtNodeTypes.DOT_QUALIFIED_EXPRESSION)) {
                    ArrayList arrayList = new ArrayList();
                    collectSegments(arrayList, lighterASTNode);
                    objectRef.element = new FqName(CollectionsKt.joinToString$default(arrayList, ".", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null));
                } else if (Intrinsics.areEqual(tokenType, KtTokens.MUL)) {
                    z = true;
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.IMPORT_ALIAS) && (pairConvertImportAlias = convertImportAlias(lighterASTNode)) != null) {
                    first = pairConvertImportAlias.getFirst();
                    second = pairConvertImportAlias.getSecond();
                }
            }
        }
        FirImportBuilder firImportBuilder = new FirImportBuilder();
        firImportBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(this, importDirective, null, 1, null));
        firImportBuilder.setImportedFqName((FqName) objectRef.element);
        firImportBuilder.setAllUnder(z);
        String str = (String) first;
        firImportBuilder.setAliasName(str != null ? Name.identifier(str) : null);
        firImportBuilder.setAliasSource((KtSourceElement) second);
        return firImportBuilder.build();
    }

    private final List<FirImport> convertImportDirectives(LighterASTNode importList) {
        LighterASTNode[] childrenAsArray = getChildrenAsArray(importList);
        ArrayList arrayList = new ArrayList();
        for (LighterASTNode lighterASTNode : childrenAsArray) {
            if (lighterASTNode == null) {
                break;
            }
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType()) && Intrinsics.areEqual(lighterASTNode.getTokenType(), KtNodeTypes.IMPORT_DIRECTIVE)) {
                arrayList.add(convertImportDirective(lighterASTNode));
            }
        }
        return arrayList;
    }

    private final List<FirExpression> convertInitializerList(LighterASTNode initializerList) {
        ArrayList arrayList = new ArrayList();
        for (LighterASTNode lighterASTNode : getChildrenAsArray(initializerList)) {
            if (lighterASTNode == null) {
                break;
            }
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType()) && Intrinsics.areEqual(lighterASTNode.getTokenType(), KtNodeTypes.SUPER_TYPE_CALL_ENTRY)) {
                CollectionsKt.addAll(arrayList, (Iterable) convertConstructorInvocation(lighterASTNode).getSecond());
            }
        }
        return arrayList;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    private final FirTypeRef convertIntersectionType(KtSourceElement typeRefSource, LighterASTNode intersectionType, boolean isNullable) throws UninitializedPropertyAccessException {
        ArrayList arrayList = new ArrayList();
        for (LighterASTNode lighterASTNode : getChildrenAsArray(intersectionType)) {
            if (lighterASTNode == null) {
                break;
            }
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType()) && !Intrinsics.areEqual(lighterASTNode.getTokenType(), KtTokens.AND)) {
                arrayList.add(convertType(lighterASTNode));
            }
        }
        if (arrayList.size() != 2) {
            FirErrorTypeRefBuilder firErrorTypeRefBuilder = new FirErrorTypeRefBuilder();
            firErrorTypeRefBuilder.setSource(typeRefSource);
            firErrorTypeRefBuilder.setDiagnostic(new ConeSyntaxDiagnostic("Wrong code"));
            return firErrorTypeRefBuilder.build();
        }
        FirIntersectionTypeRefBuilder firIntersectionTypeRefBuilder = new FirIntersectionTypeRefBuilder();
        firIntersectionTypeRefBuilder.setSource(typeRefSource);
        firIntersectionTypeRefBuilder.setMarkedNullable(isNullable);
        Object obj = arrayList.get(0);
        obj.getClass();
        firIntersectionTypeRefBuilder.setLeftType((FirTypeRef) obj);
        Object obj2 = arrayList.get(1);
        obj2.getClass();
        firIntersectionTypeRefBuilder.setRightType((FirTypeRef) obj2);
        return firIntersectionTypeRefBuilder.build();
    }

    private final ModifierList convertModifierList(LighterASTNode modifiers, boolean isInClass) {
        ModifierList modifierList = new ModifierList(0L, 1, null);
        consume(modifierList, modifiers, isInClass);
        return modifierList;
    }

    public static /* synthetic */ ModifierList convertModifierList$default(LightTreeRawFirDeclarationBuilder lightTreeRawFirDeclarationBuilder, LighterASTNode lighterASTNode, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return lightTreeRawFirDeclarationBuilder.convertModifierList(lighterASTNode, z);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    private final FirTypeRef convertNullableType(KtSourceElement typeRefSource, LighterASTNode nullableType, List<ModifierList> allTypeModifiers, boolean isNullable) throws Exception {
        LighterASTNode lighterASTNode;
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        LighterASTNode[] childrenAsArray = getChildrenAsArray(nullableType);
        int length = childrenAsArray.length;
        for (int i = 0; i < length && (lighterASTNode = childrenAsArray[i]) != null; i++) {
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType())) {
                IElementType tokenType = lighterASTNode.getTokenType();
                if (Intrinsics.areEqual(tokenType, KtNodeTypes.MODIFIER_LIST)) {
                    allTypeModifiers.add(convertModifierList$default(this, lighterASTNode, false, 2, null));
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.USER_TYPE)) {
                    objectRef.element = convertUserType(typeRefSource, lighterASTNode, isNullable);
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.FUNCTION_TYPE)) {
                    objectRef.element = convertFunctionType(typeRefSource, lighterASTNode, allTypeModifiers, isNullable);
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.NULLABLE_TYPE)) {
                    objectRef.element = convertNullableType$default(this, typeRefSource, lighterASTNode, allTypeModifiers, false, 8, null);
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.DYNAMIC_TYPE)) {
                    FirDynamicTypeRefBuilder firDynamicTypeRefBuilder = new FirDynamicTypeRefBuilder();
                    firDynamicTypeRefBuilder.setSource(typeRefSource);
                    firDynamicTypeRefBuilder.setMarkedNullable(true);
                    objectRef.element = firDynamicTypeRefBuilder.build();
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.INTERSECTION_TYPE)) {
                    objectRef.element = convertIntersectionType(typeRefSource, lighterASTNode, isNullable);
                }
            }
        }
        Object obj = objectRef.element;
        if (obj != null) {
            return (FirTypeRef) obj;
        }
        Intrinsics.throwUninitializedPropertyAccessException("firType");
        return null;
    }

    public static /* synthetic */ FirTypeRef convertNullableType$default(LightTreeRawFirDeclarationBuilder lightTreeRawFirDeclarationBuilder, KtSourceElement ktSourceElement, LighterASTNode lighterASTNode, List list, boolean z, int i, Object obj) throws Exception {
        if ((i & 8) != 0) {
            z = true;
        }
        return lightTreeRawFirDeclarationBuilder.convertNullableType(ktSourceElement, lighterASTNode, list, z);
    }

    private final FirPackageDirective convertPackageDirective(LighterASTNode packageNode) {
        FqName packageName = FqName.ROOT;
        for (LighterASTNode lighterASTNode : getChildrenAsArray(packageNode)) {
            if (lighterASTNode == null) {
                break;
            }
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType())) {
                IElementType tokenType = lighterASTNode.getTokenType();
                if (Intrinsics.areEqual(tokenType, KtNodeTypes.DOT_QUALIFIED_EXPRESSION) || Intrinsics.areEqual(tokenType, KtNodeTypes.REFERENCE_EXPRESSION)) {
                    packageName = parsePackageName(lighterASTNode);
                }
            }
        }
        FirPackageDirectiveBuilder firPackageDirectiveBuilder = new FirPackageDirectiveBuilder();
        firPackageDirectiveBuilder.setPackageFqName(packageName);
        firPackageDirectiveBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(this, packageNode, null, 1, null));
        return firPackageDirectiveBuilder.build();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:93:0x01a2  */
    private final PrimaryConstructor convertPrimaryConstructor(LighterASTNode primaryConstructor, KtSourceElement selfTypeSource, ClassWrapper classWrapper, KtLightSourceElement delegatedConstructorSource, boolean isEnumEntry, boolean containingClassIsExpectClass, boolean isImplicitlyActual, boolean isKotlinAny) throws Throwable {
        FirConstructorSymbol firConstructorSymbol;
        ModifierList modifierList;
        FirConstructorSymbol firConstructorSymbol2;
        List<FirExpression> listEmptyList;
        FirDelegatedConstructorCall firDelegatedConstructorCallConvertPrimaryConstructor$lambda$0$1$createDelegatedConstructorCall;
        Visibility visibility$default;
        KtLightSourceElement ktLightSourceElementFakeElement$default;
        int i;
        LightTreeRawFirDeclarationBuilder lightTreeRawFirDeclarationBuilder = this;
        boolean z = false;
        boolean z2 = primaryConstructor == null && !((classWrapper.isEnumEntry() || !classWrapper.getHasSecondaryConstructor()) && !classWrapper.isInterface() && (!containingClassIsExpectClass || classWrapper.getClassBuilder().getClassKind() == ClassKind.ENUM_ENTRY));
        if (z2 && classWrapper.getDelegatedSuperCalls().isEmpty()) {
            return null;
        }
        FirConstructorSymbol firConstructorSymbol3 = new FirConstructorSymbol(lightTreeRawFirDeclarationBuilder.callableIdForClassConstructor());
        lightTreeRawFirDeclarationBuilder.getContext().pushContainerSymbol(firConstructorSymbol3);
        try {
            ArrayList arrayList = new ArrayList();
            if (primaryConstructor != null) {
                LighterASTNode[] childrenAsArray = getChildrenAsArray(primaryConstructor);
                int length = childrenAsArray.length;
                int i2 = 0;
                ModifierList modifierListConvertModifierList$default = null;
                while (i2 < length) {
                    int i3 = i2;
                    LighterASTNode lighterASTNode = childrenAsArray[i3];
                    if (lighterASTNode == null) {
                        break;
                    }
                    if (AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType())) {
                        i = i3;
                    } else {
                        IElementType tokenType = lighterASTNode.getTokenType();
                        if (Intrinsics.areEqual(tokenType, KtNodeTypes.MODIFIER_LIST)) {
                            modifierListConvertModifierList$default = convertModifierList$default(lightTreeRawFirDeclarationBuilder, lighterASTNode, false, 2, null);
                        } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.VALUE_PARAMETER_LIST)) {
                            i = i3;
                            CollectionsKt.addAll(arrayList, convertValueParameters$default(lightTreeRawFirDeclarationBuilder, lighterASTNode, firConstructorSymbol3, AbstractRawFirBuilder.ValueParameterDeclaration.PRIMARY_CONSTRUCTOR, null, 8, null));
                        }
                        i = i3;
                    }
                    i2 = i + 1;
                }
                modifierList = modifierListConvertModifierList$default;
            } else {
                modifierList = null;
            }
            ModifierList modifierList2 = modifierList == null ? new ModifierList(0L, 1, null) : modifierList;
            if (ConversionUtilsKt.shouldGenerateDelegatedSuperCall(isKotlinAny, containingClassIsExpectClass, isEnumEntry, classWrapper.getDelegatedSuperCalls().isEmpty() ^ true) && (!lightTreeRawFirDeclarationBuilder.headerMode || lightTreeRawFirDeclarationBuilder.getContext().getForceKeepingTheBodyInHeaderMode())) {
                try {
                    try {
                        if (classWrapper.getDelegatedSuperCalls().size() <= 1) {
                            try {
                                FirTypeRef delegatedSuperTypeRef = classWrapper.getDelegatedSuperTypeRef();
                                DelegatedConstructorWrapper delegatedConstructorWrapper = (DelegatedConstructorWrapper) CollectionsKt.lastOrNull(classWrapper.getDelegatedSuperCalls());
                                if (delegatedConstructorWrapper == null || (listEmptyList = delegatedConstructorWrapper.getArguments()) == null) {
                                    listEmptyList = CollectionsKt.emptyList();
                                }
                                firConstructorSymbol2 = firConstructorSymbol3;
                                firDelegatedConstructorCallConvertPrimaryConstructor$lambda$0$1$createDelegatedConstructorCall = convertPrimaryConstructor$lambda$0$1$createDelegatedConstructorCall(primaryConstructor, lightTreeRawFirDeclarationBuilder, selfTypeSource, isEnumEntry, classWrapper, delegatedConstructorSource, delegatedSuperTypeRef, listEmptyList);
                                lightTreeRawFirDeclarationBuilder = this;
                            } catch (Throwable th) {
                                th = th;
                                firConstructorSymbol2 = firConstructorSymbol3;
                                lightTreeRawFirDeclarationBuilder = this;
                                firConstructorSymbol = firConstructorSymbol2;
                                lightTreeRawFirDeclarationBuilder.getContext().popContainerSymbol(firConstructorSymbol);
                                throw th;
                            }
                        } else {
                            firConstructorSymbol2 = firConstructorSymbol3;
                            FirMultiDelegatedConstructorCallBuilder firMultiDelegatedConstructorCallBuilder = new FirMultiDelegatedConstructorCallBuilder();
                            List<DelegatedConstructorWrapper> delegatedSuperCalls = classWrapper.getDelegatedSuperCalls();
                            List<FirDelegatedConstructorCall> delegatedConstructorCalls = firMultiDelegatedConstructorCallBuilder.getDelegatedConstructorCalls();
                            for (DelegatedConstructorWrapper delegatedConstructorWrapper2 : delegatedSuperCalls) {
                                List<FirDelegatedConstructorCall> list = delegatedConstructorCalls;
                                lightTreeRawFirDeclarationBuilder = this;
                                try {
                                    list.add(convertPrimaryConstructor$lambda$0$1$createDelegatedConstructorCall(primaryConstructor, lightTreeRawFirDeclarationBuilder, selfTypeSource, isEnumEntry, classWrapper, delegatedConstructorWrapper2.getSource(), delegatedConstructorWrapper2.getDelegatedSuperTypeRef(), delegatedConstructorWrapper2.component2()));
                                    delegatedConstructorCalls = list;
                                } catch (Throwable th2) {
                                    th = th2;
                                    firConstructorSymbol = firConstructorSymbol2;
                                    lightTreeRawFirDeclarationBuilder.getContext().popContainerSymbol(firConstructorSymbol);
                                    throw th;
                                }
                            }
                            lightTreeRawFirDeclarationBuilder = this;
                            firDelegatedConstructorCallConvertPrimaryConstructor$lambda$0$1$createDelegatedConstructorCall = firMultiDelegatedConstructorCallBuilder.mo288build();
                        }
                    } catch (Throwable th3) {
                        th = th3;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    firConstructorSymbol2 = firConstructorSymbol3;
                }
            } else {
                firConstructorSymbol2 = firConstructorSymbol3;
                firDelegatedConstructorCallConvertPrimaryConstructor$lambda$0$1$createDelegatedConstructorCall = null;
            }
            if (primaryConstructor != 0) {
                visibility$default = ModifierList.getVisibility$default(modifierList2, false, 1, null);
                if (Intrinsics.areEqual(visibility$default, Visibilities.Unknown.INSTANCE)) {
                    visibility$default = null;
                }
            } else {
                visibility$default = null;
            }
            FirDeclarationStatusImpl firDeclarationStatusImpl = new FirDeclarationStatusImpl(visibility$default == null ? classWrapper.defaultConstructorVisibility() : visibility$default, Modality.FINAL);
            firDeclarationStatusImpl.setExpect(modifierList2.hasExpect() || lightTreeRawFirDeclarationBuilder.getContext().getContainerIsExpect());
            firDeclarationStatusImpl.setActual(modifierList2.hasActual() || isImplicitlyActual);
            firDeclarationStatusImpl.setInner(classWrapper.isInner());
            if (classWrapper.isSealed() && visibility$default != Visibilities.Private.INSTANCE) {
                z = true;
            }
            firDeclarationStatusImpl.setFromSealedClass(z);
            firDeclarationStatusImpl.setFromEnumClass(classWrapper.isEnum());
            FirAbstractConstructorBuilder firAbstractConstructorBuilderCreateErrorConstructorBuilder = z2 ? lightTreeRawFirDeclarationBuilder.createErrorConstructorBuilder(ConeNoConstructorError.INSTANCE) : new FirPrimaryConstructorBuilder();
            if (primaryConstructor == 0 || (ktLightSourceElementFakeElement$default = AbstractRawFirBuilder.toFirSourceElement$default(lightTreeRawFirDeclarationBuilder, primaryConstructor, null, 1, null)) == null) {
                ktLightSourceElementFakeElement$default = selfTypeSource != 0 ? KtSourceElementKt.fakeElement$default(selfTypeSource, KtFakeSourceElementKind.ImplicitConstructor.INSTANCE, null, 2, null) : null;
            }
            firAbstractConstructorBuilderCreateErrorConstructorBuilder.setSource(ktLightSourceElementFakeElement$default);
            firAbstractConstructorBuilderCreateErrorConstructorBuilder.setModuleData(lightTreeRawFirDeclarationBuilder.getBaseModuleData());
            firAbstractConstructorBuilderCreateErrorConstructorBuilder.setOrigin(FirDeclarationOrigin.Source.INSTANCE);
            firAbstractConstructorBuilderCreateErrorConstructorBuilder.setReturnTypeRef(classWrapper.getDelegatedSelfTypeRef());
            firAbstractConstructorBuilderCreateErrorConstructorBuilder.setDispatchReceiverType(lightTreeRawFirDeclarationBuilder.obtainDispatchReceiverForConstructor(classWrapper));
            firAbstractConstructorBuilderCreateErrorConstructorBuilder.setStatus(firDeclarationStatusImpl);
            firAbstractConstructorBuilderCreateErrorConstructorBuilder.setLocal(lightTreeRawFirDeclarationBuilder.getContext().getInLocalContext());
            firConstructorSymbol = firConstructorSymbol2;
            try {
                firAbstractConstructorBuilderCreateErrorConstructorBuilder.setSymbol(firConstructorSymbol);
                if (modifierList != null) {
                    lightTreeRawFirDeclarationBuilder.convertAnnotationsTo(modifierList, firAbstractConstructorBuilderCreateErrorConstructorBuilder.getAnnotations());
                }
                CollectionsKt.addAll(firAbstractConstructorBuilderCreateErrorConstructorBuilder.getTypeParameters(), lightTreeRawFirDeclarationBuilder.constructorTypeParametersFromConstructedClass(classWrapper.getClassBuilder().getTypeParameters()));
                List<FirValueParameter> valueParameters = firAbstractConstructorBuilderCreateErrorConstructorBuilder.getValueParameters();
                ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    arrayList2.add(((ValueParameter) it.next()).getFirValueParameter());
                }
                CollectionsKt.addAll(valueParameters, arrayList2);
                firAbstractConstructorBuilderCreateErrorConstructorBuilder.setDelegatedConstructor(firDelegatedConstructorCallConvertPrimaryConstructor$lambda$0$1$createDelegatedConstructorCall);
                firAbstractConstructorBuilderCreateErrorConstructorBuilder.setBody(null);
                lightTreeRawFirDeclarationBuilder.addContextParameters(firAbstractConstructorBuilderCreateErrorConstructorBuilder.getContextParameters(), modifierList2.getContextLists(), firConstructorSymbol);
                FirConstructor firConstructorMo288build = firAbstractConstructorBuilderCreateErrorConstructorBuilder.mo288build();
                ConeClassLikeType coneClassLikeTypeCurrentDispatchReceiverType = lightTreeRawFirDeclarationBuilder.currentDispatchReceiverType();
                coneClassLikeTypeCurrentDispatchReceiverType.getClass();
                ClassMembersKt.setContainingClassForStaticMemberAttr(firConstructorMo288build, coneClassLikeTypeCurrentDispatchReceiverType.getLookupTag());
                PrimaryConstructor primaryConstructor2 = new PrimaryConstructor(firConstructorMo288build, arrayList);
                lightTreeRawFirDeclarationBuilder.getContext().popContainerSymbol(firConstructorSymbol);
                return primaryConstructor2;
            } catch (Throwable th5) {
                th = th5;
                lightTreeRawFirDeclarationBuilder.getContext().popContainerSymbol(firConstructorSymbol);
                throw th;
            }
        } catch (Throwable th6) {
            th = th6;
            firConstructorSymbol = firConstructorSymbol3;
        }
    }

    public static /* synthetic */ PrimaryConstructor convertPrimaryConstructor$default(LightTreeRawFirDeclarationBuilder lightTreeRawFirDeclarationBuilder, LighterASTNode lighterASTNode, KtSourceElement ktSourceElement, ClassWrapper classWrapper, KtLightSourceElement ktLightSourceElement, boolean z, boolean z2, boolean z3, boolean z4, int i, Object obj) {
        if ((i & 16) != 0) {
            z = false;
        }
        if ((i & 64) != 0) {
            z3 = false;
        }
        if ((i & 128) != 0) {
            z4 = false;
        }
        return lightTreeRawFirDeclarationBuilder.convertPrimaryConstructor(lighterASTNode, ktSourceElement, classWrapper, ktLightSourceElement, z, z2, z3, z4);
    }

    /* JADX WARN: Code duplicated, block: B:32:0x0077  */
    private static final FirDelegatedConstructorCall convertPrimaryConstructor$lambda$0$1$createDelegatedConstructorCall(LighterASTNode lighterASTNode, LightTreeRawFirDeclarationBuilder lightTreeRawFirDeclarationBuilder, KtSourceElement ktSourceElement, boolean z, ClassWrapper classWrapper, KtLightSourceElement ktLightSourceElement, FirTypeRef firTypeRef, List<? extends FirExpression> list) {
        KtLightSourceElement firSourceElement;
        LighterASTNode lighterASTNode2;
        KtSourceElement ktSourceElementFakeElement$default;
        FirDelegatedConstructorCallBuilder firDelegatedConstructorCallBuilder = new FirDelegatedConstructorCallBuilder();
        KtSourceElement source = null;
        if (ktLightSourceElement != null) {
            firSourceElement = ktLightSourceElement;
        } else {
            firSourceElement = lighterASTNode != null ? lightTreeRawFirDeclarationBuilder.toFirSourceElement(lighterASTNode, (KtFakeSourceElementKind) KtFakeSourceElementKind.DelegatingConstructorCall.INSTANCE) : null;
            if (firSourceElement == null) {
                firSourceElement = ktSourceElement != null ? KtSourceElementKt.fakeElement$default(ktSourceElement, KtFakeSourceElementKind.DelegatingConstructorCall.INSTANCE, null, 2, null) : null;
            }
        }
        firDelegatedConstructorCallBuilder.setSource(firSourceElement);
        firDelegatedConstructorCallBuilder.setConstructedTypeRef(UtilsKt.copyWithNewSourceKind(firTypeRef, KtFakeSourceElementKind.ImplicitTypeRef.INSTANCE));
        firDelegatedConstructorCallBuilder.setThis(false);
        FirExplicitSuperReferenceBuilder firExplicitSuperReferenceBuilder = new FirExplicitSuperReferenceBuilder();
        if (!z) {
            KtSourceElement source2 = classWrapper.getDelegatedSuperTypeRef().getSource();
            if (source2 == null || (ktSourceElementFakeElement$default = KtSourceElementKt.fakeElement$default(source2, KtFakeSourceElementKind.DelegatingConstructorCall.INSTANCE, null, 2, null)) == null) {
                KtSourceElement source3 = firDelegatedConstructorCallBuilder.getSource();
                if (source3 != null) {
                    source = KtSourceElementKt.fakeElement$default(source3, KtFakeSourceElementKind.DelegatingConstructorCall.INSTANCE, null, 2, null);
                }
            } else {
                source = ktSourceElementFakeElement$default;
            }
        } else if (ktLightSourceElement == null || (lighterASTNode2 = ktLightSourceElement.getLighterASTNode()) == null) {
            source = firDelegatedConstructorCallBuilder.getSource();
        } else {
            IElementType iElementType = KtNodeTypes.CONSTRUCTOR_CALLEE;
            iElementType.getClass();
            LighterASTNode childNodeByType = lightTreeRawFirDeclarationBuilder.getChildNodeByType(lighterASTNode2, iElementType);
            if (childNodeByType == null || (source = lightTreeRawFirDeclarationBuilder.toFirSourceElement(childNodeByType, (KtFakeSourceElementKind) KtFakeSourceElementKind.DelegatingConstructorCall.INSTANCE)) == null) {
                source = firDelegatedConstructorCallBuilder.getSource();
            }
        }
        firExplicitSuperReferenceBuilder.setSource(source);
        firExplicitSuperReferenceBuilder.setSuperTypeRef(firDelegatedConstructorCallBuilder.getConstructedTypeRef());
        firDelegatedConstructorCallBuilder.setCalleeReference(firExplicitSuperReferenceBuilder.build());
        ConverterUtilKt.extractArgumentsFrom(firDelegatedConstructorCallBuilder, list);
        return firDelegatedConstructorCallBuilder.mo288build();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
    public static /* synthetic */ FirProperty convertPropertyDeclaration$default(LightTreeRawFirDeclarationBuilder lightTreeRawFirDeclarationBuilder, LighterASTNode lighterASTNode, ClassWrapper classWrapper, int i, Object obj) throws KotlinIllegalStateExceptionWithAttachments {
        if ((i & 2) != 0) {
            classWrapper = null;
        }
        return lightTreeRawFirDeclarationBuilder.convertPropertyDeclaration(lighterASTNode, classWrapper);
    }

    private static final FirDeclarationStatusImpl convertPropertyDeclaration$lambda$2$1$6$defaultAccessorStatus(Visibility visibility, ModifierList modifierList, boolean z) {
        FirDeclarationStatusImpl firDeclarationStatusImpl = new FirDeclarationStatusImpl(visibility, null);
        firDeclarationStatusImpl.setInline(modifierList.hasInline());
        firDeclarationStatusImpl.setExternal(modifierList.hasExternal());
        firDeclarationStatusImpl.setStatic(modifierList.hasCompanion() || z);
        return firDeclarationStatusImpl;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    private final FirTypeRef convertReceiverType(LighterASTNode receiverType) throws Exception {
        for (LighterASTNode lighterASTNode : getChildrenAsArray(receiverType)) {
            if (lighterASTNode == null) {
                break;
            }
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType()) && Intrinsics.areEqual(lighterASTNode.getTokenType(), KtNodeTypes.TYPE_REFERENCE)) {
                return convertType(lighterASTNode);
            }
        }
        throw new Exception();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit convertScript$lambda$0$1$1(LightTreeRawFirDeclarationBuilder lightTreeRawFirDeclarationBuilder, FirProperty firProperty, FirVariable firVariable) {
        firVariable.getClass();
        lightTreeRawFirDeclarationBuilder.configureScriptDestructuringDeclarationEntry(firVariable, firProperty);
        DeclarationAttributesKt.setScriptTopLevelDeclaration(firVariable, Boolean.TRUE);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
    private final FirAnonymousInitializer convertScriptInitializer(LighterASTNode scriptInitializer, FirBasedSymbol<?> containingDeclarationSymbol, boolean isLocal) throws KotlinIllegalStateExceptionWithAttachments {
        FirAnonymousInitializerSymbol firAnonymousInitializerSymbol = new FirAnonymousInitializerSymbol();
        if (!isLocal) {
            getContext().pushContainerSymbol(firAnonymousInitializerSymbol);
        }
        try {
            FirAnonymousInitializerBuilder firAnonymousInitializerBuilder = new FirAnonymousInitializerBuilder();
            firAnonymousInitializerBuilder.setSymbol(firAnonymousInitializerSymbol);
            firAnonymousInitializerBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(this, scriptInitializer, null, 1, null));
            firAnonymousInitializerBuilder.setModuleData(getBaseModuleData());
            firAnonymousInitializerBuilder.setOrigin(FirDeclarationOrigin.Source.INSTANCE);
            boolean forceKeepingTheBodyInHeaderMode = getContext().getForceKeepingTheBodyInHeaderMode();
            getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
            boolean inLocalContext = getContext().getInLocalContext();
            getContext().setInLocalContext(true);
            FqName classNameBeforeLocalContext = getContext().getClassNameBeforeLocalContext();
            if (!inLocalContext) {
                getContext().setClassNameBeforeLocalContext(getContext().getClassName());
            }
            FqName className = getContext().getClassName();
            getContext().setClassName(FqName.ROOT);
            try {
                firAnonymousInitializerBuilder.getAnnotations();
                FirBlock firBlockMo288build = convertBlockExpressionWithoutBuilding$default(this, scriptInitializer, null, false, 6, null).mo288build();
                if (firBlockMo288build == null) {
                    firBlockMo288build = FirEmptyExpressionBlockBuilderKt.buildEmptyExpressionBlock();
                }
                getContext().setClassNameBeforeLocalContext(classNameBeforeLocalContext);
                getContext().setInLocalContext(inLocalContext);
                getContext().setClassName(className);
                getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
                firAnonymousInitializerBuilder.setBody(firBlockMo288build);
                firAnonymousInitializerBuilder.setContainingDeclarationSymbol(containingDeclarationSymbol);
                FirAnonymousInitializer firAnonymousInitializerMo288build = firAnonymousInitializerBuilder.mo288build();
                if (!isLocal) {
                    getContext().popContainerSymbol(firAnonymousInitializerSymbol);
                }
                if (isDirectlyInsideCompanionBlock()) {
                    ClassMembersKt.setIllegalCompanionBlockMember(firAnonymousInitializerMo288build, Boolean.TRUE);
                }
                return firAnonymousInitializerMo288build;
            } catch (Throwable th) {
                getContext().setClassNameBeforeLocalContext(classNameBeforeLocalContext);
                getContext().setInLocalContext(inLocalContext);
                getContext().setClassName(className);
                getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
                throw th;
            }
        } catch (Throwable th2) {
            if (!isLocal) {
                getContext().popContainerSymbol(firAnonymousInitializerSymbol);
            }
            throw th2;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
    private final FirConstructor convertSecondaryConstructor(LighterASTNode secondaryConstructor, ClassWrapper classWrapper) throws KotlinIllegalStateExceptionWithAttachments {
        LighterASTNode lighterASTNode;
        LighterASTNode lighterASTNode2;
        ArrayList arrayList = new ArrayList();
        FirConstructorSymbol firConstructorSymbol = new FirConstructorSymbol(callableIdForClassConstructor());
        getContext().pushContainerSymbol(firConstructorSymbol);
        try {
            LighterASTNode[] childrenAsArray = getChildrenAsArray(secondaryConstructor);
            int length = childrenAsArray.length;
            int i = 0;
            LighterASTNode lighterASTNode3 = null;
            ModifierList modifierListConvertModifierList$default = null;
            LighterASTNode lighterASTNode4 = null;
            while (true) {
                if (i >= length) {
                    lighterASTNode = lighterASTNode3;
                    break;
                }
                LighterASTNode lighterASTNode5 = lighterASTNode3;
                lighterASTNode3 = childrenAsArray[i];
                if (lighterASTNode3 == null) {
                    lighterASTNode = lighterASTNode5;
                    break;
                }
                if (AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode3.getTokenType())) {
                    lighterASTNode2 = lighterASTNode5;
                } else {
                    IElementType tokenType = lighterASTNode3.getTokenType();
                    if (Intrinsics.areEqual(tokenType, KtNodeTypes.MODIFIER_LIST)) {
                        modifierListConvertModifierList$default = convertModifierList$default(this, lighterASTNode3, false, 2, null);
                        lighterASTNode3 = lighterASTNode5;
                    } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.VALUE_PARAMETER_LIST)) {
                        lighterASTNode2 = lighterASTNode5;
                        CollectionsKt.addAll(arrayList, convertValueParameters$default(this, lighterASTNode3, firConstructorSymbol, AbstractRawFirBuilder.ValueParameterDeclaration.FUNCTION, null, 8, null));
                    } else {
                        lighterASTNode2 = lighterASTNode5;
                        if (Intrinsics.areEqual(tokenType, KtNodeTypes.CONSTRUCTOR_DELEGATION_CALL)) {
                            lighterASTNode4 = lighterASTNode3;
                        } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.BLOCK)) {
                        }
                    }
                    i++;
                }
                lighterASTNode3 = lighterASTNode2;
                i++;
            }
            FirTypeRef delegatedSelfTypeRef = classWrapper.getDelegatedSelfTypeRef();
            ModifierList modifierList = modifierListConvertModifierList$default == null ? new ModifierList(0L, 1, null) : modifierListConvertModifierList$default;
            boolean z = modifierList.hasExpect() || getContext().getContainerIsExpect();
            FirDelegatedConstructorCall firDelegatedConstructorCallConvertConstructorDelegationCall = (lighterASTNode4 == null || !(!this.headerMode || getContext().getForceKeepingTheBodyInHeaderMode())) ? null : convertConstructorDelegationCall(lighterASTNode4, classWrapper, z);
            Visibility visibility$default = ModifierList.getVisibility$default(modifierList, false, 1, null);
            if (Intrinsics.areEqual(visibility$default, Visibilities.Unknown.INSTANCE)) {
                visibility$default = null;
            }
            FirDeclarationStatusImpl firDeclarationStatusImpl = new FirDeclarationStatusImpl(visibility$default == null ? classWrapper.defaultConstructorVisibility() : visibility$default, Modality.FINAL);
            firDeclarationStatusImpl.setExpect(z);
            firDeclarationStatusImpl.setActual(modifierList.hasActual());
            firDeclarationStatusImpl.setInner(classWrapper.isInner());
            firDeclarationStatusImpl.setFromSealedClass(classWrapper.isSealed() && visibility$default != Visibilities.Private.INSTANCE);
            firDeclarationStatusImpl.setFromEnumClass(classWrapper.isEnum());
            FirFunctionTarget firFunctionTarget = new FirFunctionTarget(null, false);
            FirConstructorBuilder firConstructorBuilder = new FirConstructorBuilder();
            firConstructorBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(this, secondaryConstructor, null, 1, null));
            firConstructorBuilder.setModuleData(getBaseModuleData());
            firConstructorBuilder.setOrigin(FirDeclarationOrigin.Source.INSTANCE);
            firConstructorBuilder.setReturnTypeRef(delegatedSelfTypeRef);
            firConstructorBuilder.setDispatchReceiverType(obtainDispatchReceiverForConstructor(classWrapper));
            firConstructorBuilder.setStatus(firDeclarationStatusImpl);
            firConstructorBuilder.setLocal(getContext().getInLocalContext());
            firConstructorBuilder.setSymbol(firConstructorSymbol);
            firConstructorBuilder.setDelegatedConstructor(firDelegatedConstructorCallConvertConstructorDelegationCall);
            getContext().getFirFunctionTargets().add(firFunctionTarget);
            if (modifierListConvertModifierList$default != null) {
                convertAnnotationsTo(modifierListConvertModifierList$default, firConstructorBuilder.getAnnotations());
            }
            CollectionsKt.addAll(firConstructorBuilder.getTypeParameters(), constructorTypeParametersFromConstructedClass(classWrapper.getClassBuilder().getTypeParameters()));
            List<FirValueParameter> valueParameters = firConstructorBuilder.getValueParameters();
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                arrayList2.add(((ValueParameter) it.next()).getFirValueParameter());
            }
            CollectionsKt.addAll(valueParameters, arrayList2);
            boolean forceKeepingTheBodyInHeaderMode = getContext().getForceKeepingTheBodyInHeaderMode();
            getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
            boolean inLocalContext = getContext().getInLocalContext();
            getContext().setInLocalContext(true);
            FqName classNameBeforeLocalContext = getContext().getClassNameBeforeLocalContext();
            if (!inLocalContext) {
                getContext().setClassNameBeforeLocalContext(getContext().getClassName());
            }
            FqName className = getContext().getClassName();
            getContext().setClassName(FqName.ROOT);
            try {
                Pair<FirBlock, FirContractDescription> pairConvertFunctionBody = convertFunctionBody(lighterASTNode, null, true);
                getContext().setClassNameBeforeLocalContext(classNameBeforeLocalContext);
                getContext().setInLocalContext(inLocalContext);
                getContext().setClassName(className);
                getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
                FirBlock firBlock = (FirBlock) pairConvertFunctionBody.component1();
                FirContractDescription firContractDescription = (FirContractDescription) pairConvertFunctionBody.component2();
                if (firBlock == null || firBlock.getStatements().isEmpty()) {
                    firBlock = null;
                }
                firConstructorBuilder.setBody(firBlock);
                if (firContractDescription != null) {
                    firConstructorBuilder.setContractDescription(firContractDescription);
                }
                removeLast(getContext().getFirFunctionTargets());
                addContextParameters(firConstructorBuilder.getContextParameters(), modifierListConvertModifierList$default != null ? modifierListConvertModifierList$default.getContextLists() : null, firConstructorSymbol);
                FirConstructor firConstructorMo288build = firConstructorBuilder.mo288build();
                ConeClassLikeType coneClassLikeTypeCurrentDispatchReceiverType = currentDispatchReceiverType();
                coneClassLikeTypeCurrentDispatchReceiverType.getClass();
                ClassMembersKt.setContainingClassForStaticMemberAttr(firConstructorMo288build, coneClassLikeTypeCurrentDispatchReceiverType.getLookupTag());
                firFunctionTarget.bind(firConstructorMo288build);
                getContext().popContainerSymbol(firConstructorSymbol);
                if (isDirectlyInsideCompanionBlock()) {
                    ClassMembersKt.setIllegalCompanionBlockMember(firConstructorMo288build, Boolean.TRUE);
                }
                return firConstructorMo288build;
            } catch (Throwable th) {
                getContext().setClassNameBeforeLocalContext(classNameBeforeLocalContext);
                getContext().setInLocalContext(inLocalContext);
                getContext().setClassName(className);
                getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
                throw th;
            }
        } catch (Throwable th2) {
            getContext().popContainerSymbol(firConstructorSymbol);
            throw th2;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    private final FirValueParameter convertSetterParameter(LighterASTNode setterParameter, FirFunctionSymbol<?> functionSymbol, FirTypeRef propertyTypeRef, List<? extends FirAnnotation> additionalAnnotations) throws UninitializedPropertyAccessException {
        FirValueParameter firValueParameter = null;
        ModifierList modifierList = null;
        FirValueParameter firValueParameter2 = null;
        for (LighterASTNode lighterASTNode : getChildrenAsArray(setterParameter)) {
            if (lighterASTNode == null) {
                break;
            }
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType())) {
                IElementType tokenType = lighterASTNode.getTokenType();
                if (Intrinsics.areEqual(tokenType, KtNodeTypes.MODIFIER_LIST)) {
                    modifierList = convertModifierList$default(this, lighterASTNode, false, 2, null);
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.VALUE_PARAMETER)) {
                    firValueParameter2 = convertValueParameter$default(this, lighterASTNode, functionSymbol, AbstractRawFirBuilder.ValueParameterDeclaration.SETTER, null, 8, null).getFirValueParameter();
                }
            }
        }
        if (modifierList == null) {
            modifierList = new ModifierList(0L, 1, null);
        }
        FirValueParameterBuilder firValueParameterBuilder = new FirValueParameterBuilder();
        if (firValueParameter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("firValueParameter");
        } else {
            firValueParameter = firValueParameter2;
        }
        firValueParameterBuilder.setSource(firValueParameter.getSource());
        firValueParameterBuilder.setContainingDeclarationSymbol(functionSymbol);
        firValueParameterBuilder.setModuleData(getBaseModuleData());
        firValueParameterBuilder.setOrigin(FirDeclarationOrigin.Source.INSTANCE);
        firValueParameterBuilder.setReturnTypeRef(Intrinsics.areEqual(firValueParameter2.getReturnTypeRef(), getImplicitType()) ? propertyTypeRef : firValueParameter2.getReturnTypeRef());
        firValueParameterBuilder.setName(firValueParameter2.getName());
        firValueParameterBuilder.setSymbol(new FirValueParameterSymbol());
        firValueParameterBuilder.setDefaultValue(firValueParameter2.getDefaultValue());
        firValueParameterBuilder.setCrossinline(modifierList.hasCrossinline() || firValueParameter2.getIsCrossinline());
        firValueParameterBuilder.setNoinline(modifierList.hasNoinline() || firValueParameter2.getIsNoinline());
        firValueParameterBuilder.setVararg(modifierList.hasVararg() || firValueParameter2.getIsVararg());
        CollectionsKt.addAll(firValueParameterBuilder.getAnnotations(), firValueParameter2.getAnnotations());
        CollectionsKt.addAll(firValueParameterBuilder.getAnnotations(), additionalAnnotations);
        return firValueParameterBuilder.mo288build();
    }

    private final TypeProjectionModifierList convertTypeArgumentModifierList(LighterASTNode modifiers) {
        TypeProjectionModifierList typeProjectionModifierList = new TypeProjectionModifierList(null, 0L, 3, null);
        consume$default(this, typeProjectionModifierList, modifiers, false, 2, null);
        return typeProjectionModifierList;
    }

    private final TypeConstraint convertTypeConstraint(LighterASTNode typeConstraint) throws Exception {
        ArrayList arrayList = new ArrayList();
        String asText = null;
        FirResolvedTypeRef firResolvedTypeRefBuild = null;
        LighterASTNode lighterASTNode = null;
        for (LighterASTNode lighterASTNode2 : getChildrenAsArray(typeConstraint)) {
            if (lighterASTNode2 == null) {
                break;
            }
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode2.getTokenType())) {
                IElementType tokenType = lighterASTNode2.getTokenType();
                if (Intrinsics.areEqual(tokenType, KtNodeTypes.ANNOTATION_ENTRY)) {
                    arrayList.add(convertAnnotationEntry$default(this, lighterASTNode2, null, new ConeSimpleDiagnostic("Type parameter annotations are not allowed inside where clauses", DiagnosticKind.AnnotationInWhereClause), 2, null));
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.REFERENCE_EXPRESSION)) {
                    asText = getAsText(lighterASTNode2);
                    lighterASTNode = lighterASTNode2;
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.TYPE_REFERENCE)) {
                    firResolvedTypeRefBuild = convertType(lighterASTNode2);
                }
            }
        }
        if (firResolvedTypeRefBuild == null) {
            FirErrorTypeRefBuilder firErrorTypeRefBuilder = new FirErrorTypeRefBuilder();
            Unit unit = Unit.INSTANCE;
            firResolvedTypeRefBuild = firErrorTypeRefBuilder.build();
        }
        if (lighterASTNode != null) {
            typeConstraint = lighterASTNode;
        }
        return new TypeConstraint(arrayList, asText, firResolvedTypeRefBuild, AbstractRawFirBuilder.toFirSourceElement$default(this, typeConstraint, null, 1, null));
    }

    private final List<TypeConstraint> convertTypeConstraints(LighterASTNode typeConstraints) {
        LighterASTNode[] childrenAsArray = getChildrenAsArray(typeConstraints);
        ArrayList arrayList = new ArrayList();
        for (LighterASTNode lighterASTNode : childrenAsArray) {
            if (lighterASTNode == null) {
                break;
            }
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType()) && Intrinsics.areEqual(lighterASTNode.getTokenType(), KtNodeTypes.TYPE_CONSTRAINT)) {
                arrayList.add(convertTypeConstraint(lighterASTNode));
            }
        }
        return arrayList;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    private final FirTypeParameter convertTypeParameter(LighterASTNode typeParameter, List<TypeConstraint> typeConstraints, FirBasedSymbol<?> containingSymbol) throws Exception {
        TypeParameterModifierList typeParameterModifierListConvertTypeParameterModifiers = null;
        String asText = null;
        FirTypeRef firTypeRefConvertType = null;
        for (LighterASTNode lighterASTNode : getChildrenAsArray(typeParameter)) {
            if (lighterASTNode == null) {
                break;
            }
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType())) {
                IElementType tokenType = lighterASTNode.getTokenType();
                if (Intrinsics.areEqual(tokenType, KtNodeTypes.MODIFIER_LIST)) {
                    typeParameterModifierListConvertTypeParameterModifiers = convertTypeParameterModifiers(lighterASTNode);
                } else if (Intrinsics.areEqual(tokenType, KtTokens.IDENTIFIER)) {
                    asText = getAsText(lighterASTNode);
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.TYPE_REFERENCE)) {
                    firTypeRefConvertType = convertType(lighterASTNode);
                }
            }
        }
        TypeParameterModifierList typeParameterModifierList = typeParameterModifierListConvertTypeParameterModifiers == null ? new TypeParameterModifierList(0L, 1, null) : typeParameterModifierListConvertTypeParameterModifiers;
        FirTypeParameterBuilder firTypeParameterBuilder = new FirTypeParameterBuilder();
        firTypeParameterBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(this, typeParameter, null, 1, null));
        firTypeParameterBuilder.setModuleData(getBaseModuleData());
        firTypeParameterBuilder.setOrigin(FirDeclarationOrigin.Source.INSTANCE);
        firTypeParameterBuilder.setName(ConverterUtilKt.nameAsSafeName$default(asText, null, 1, null));
        firTypeParameterBuilder.setSymbol(new FirTypeParameterSymbol());
        firTypeParameterBuilder.setContainingDeclarationSymbol(containingSymbol);
        firTypeParameterBuilder.setVariance(typeParameterModifierList.getVariance());
        firTypeParameterBuilder.setReified(typeParameterModifierList.hasReified());
        if (typeParameterModifierListConvertTypeParameterModifiers != null) {
            convertAnnotationsTo(typeParameterModifierListConvertTypeParameterModifiers, firTypeParameterBuilder.getAnnotations());
        }
        if (firTypeRefConvertType != null) {
            firTypeParameterBuilder.getBounds().add(firTypeRefConvertType);
        }
        for (TypeConstraint typeConstraint : typeConstraints) {
            if (Intrinsics.areEqual(typeConstraint.getIdentifier(), asText)) {
                firTypeParameterBuilder.getBounds().add(typeConstraint.getFirTypeRef());
                CollectionsKt.addAll(firTypeParameterBuilder.getAnnotations(), typeConstraint.getAnnotations());
            }
        }
        FirDeclarationBuildingUtilsKt.addDefaultBoundIfNecessary(firTypeParameterBuilder);
        return firTypeParameterBuilder.mo288build();
    }

    private final TypeParameterModifierList convertTypeParameterModifiers(LighterASTNode modifiers) {
        TypeParameterModifierList typeParameterModifierList = new TypeParameterModifierList(0L, 1, null);
        consume$default(this, typeParameterModifierList, modifiers, false, 2, null);
        return typeParameterModifierList;
    }

    private final List<FirTypeParameter> convertTypeParameters(LighterASTNode typeParameterList, List<TypeConstraint> typeConstraints, FirBasedSymbol<?> containingDeclarationSymbol) {
        LighterASTNode[] childrenAsArray = getChildrenAsArray(typeParameterList);
        ArrayList arrayList = new ArrayList();
        for (LighterASTNode lighterASTNode : childrenAsArray) {
            if (lighterASTNode == null) {
                break;
            }
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType()) && Intrinsics.areEqual(lighterASTNode.getTokenType(), KtNodeTypes.TYPE_PARAMETER)) {
                arrayList.add(convertTypeParameter(lighterASTNode, typeConstraints, containingDeclarationSymbol));
            }
        }
        return arrayList;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    private final FirTypeProjection convertTypeProjection(LighterASTNode typeProjection, boolean allowedUnderscoredTypeArgument) throws UninitializedPropertyAccessException {
        FirTypeRef firTypeRef;
        LighterASTNode lighterASTNode;
        LighterASTNode[] childrenAsArray = getChildrenAsArray(typeProjection);
        int length = childrenAsArray.length;
        FirTypeRef firTypeRef2 = null;
        FirTypeRef firTypeRefConvertType = null;
        TypeProjectionModifierList typeProjectionModifierList = null;
        boolean z = false;
        for (int i = 0; i < length && (lighterASTNode = childrenAsArray[i]) != null; i++) {
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType())) {
                IElementType tokenType = lighterASTNode.getTokenType();
                if (Intrinsics.areEqual(tokenType, KtNodeTypes.MODIFIER_LIST)) {
                    typeProjectionModifierList = convertTypeArgumentModifierList(lighterASTNode);
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.TYPE_REFERENCE)) {
                    firTypeRefConvertType = convertType(lighterASTNode);
                } else if (Intrinsics.areEqual(tokenType, KtTokens.MUL)) {
                    z = true;
                }
            }
        }
        if (z) {
            FirStarProjectionBuilder firStarProjectionBuilder = new FirStarProjectionBuilder();
            firStarProjectionBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(this, typeProjection, null, 1, null));
            return firStarProjectionBuilder.build();
        }
        if (allowedUnderscoredTypeArgument) {
            if (firTypeRefConvertType == null) {
                Intrinsics.throwUninitializedPropertyAccessException("firType");
                firTypeRef = null;
            } else {
                firTypeRef = firTypeRefConvertType;
            }
            FirUserTypeRef firUserTypeRef = firTypeRef instanceof FirUserTypeRef ? (FirUserTypeRef) firTypeRef : null;
            if (firUserTypeRef != null && isUnderscored(firUserTypeRef)) {
                FirPlaceholderProjectionBuilder firPlaceholderProjectionBuilder = new FirPlaceholderProjectionBuilder();
                firPlaceholderProjectionBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(this, typeProjection, null, 1, null));
                return firPlaceholderProjectionBuilder.build();
            }
        }
        FirTypeProjectionWithVarianceBuilder firTypeProjectionWithVarianceBuilder = new FirTypeProjectionWithVarianceBuilder();
        firTypeProjectionWithVarianceBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(this, typeProjection, null, 1, null));
        if (firTypeRefConvertType == null) {
            Intrinsics.throwUninitializedPropertyAccessException("firType");
        } else {
            firTypeRef2 = firTypeRefConvertType;
        }
        firTypeProjectionWithVarianceBuilder.setTypeRef(firTypeRef2);
        if (typeProjectionModifierList == null) {
            typeProjectionModifierList = new TypeProjectionModifierList(null, 0L, 3, null);
        }
        firTypeProjectionWithVarianceBuilder.setVariance(typeProjectionModifierList.getVariance());
        return firTypeProjectionWithVarianceBuilder.build();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    private final FirTypeRef convertUserType(KtSourceElement typeRefSource, LighterASTNode userType, boolean isNullable) throws UninitializedPropertyAccessException {
        List qualifier;
        LighterASTNode lighterASTNode;
        LightTreeRawFirDeclarationBuilder lightTreeRawFirDeclarationBuilder;
        LightTreeRawFirDeclarationBuilder lightTreeRawFirDeclarationBuilder2 = this;
        ArrayList arrayList = new ArrayList();
        LighterASTNode[] childrenAsArray = lightTreeRawFirDeclarationBuilder2.getChildrenAsArray(userType);
        int length = childrenAsArray.length;
        FirUserTypeRef firUserTypeRef = null;
        String asText = null;
        KtSourceElement firSourceElement$default = null;
        KtSourceElement firSourceElement$default2 = null;
        int i = 0;
        while (i < length && (lighterASTNode = childrenAsArray[i]) != null) {
            if (AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType())) {
                lightTreeRawFirDeclarationBuilder = lightTreeRawFirDeclarationBuilder2;
            } else {
                IElementType tokenType = lighterASTNode.getTokenType();
                if (Intrinsics.areEqual(tokenType, KtNodeTypes.USER_TYPE)) {
                    FirTypeRef firTypeRefConvertUserType$default = convertUserType$default(lightTreeRawFirDeclarationBuilder2, typeRefSource, lighterASTNode, false, 4, null);
                    lightTreeRawFirDeclarationBuilder = lightTreeRawFirDeclarationBuilder2;
                    firUserTypeRef = firTypeRefConvertUserType$default instanceof FirUserTypeRef ? (FirUserTypeRef) firTypeRefConvertUserType$default : null;
                } else {
                    lightTreeRawFirDeclarationBuilder = lightTreeRawFirDeclarationBuilder2;
                    if (Intrinsics.areEqual(tokenType, KtNodeTypes.REFERENCE_EXPRESSION)) {
                        firSourceElement$default = AbstractRawFirBuilder.toFirSourceElement$default(lightTreeRawFirDeclarationBuilder, lighterASTNode, null, 1, null);
                        asText = lightTreeRawFirDeclarationBuilder.getAsText(lighterASTNode);
                    } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.TYPE_ARGUMENT_LIST)) {
                        firSourceElement$default2 = AbstractRawFirBuilder.toFirSourceElement$default(lightTreeRawFirDeclarationBuilder, lighterASTNode, null, 1, null);
                        CollectionsKt.addAll(arrayList, lightTreeRawFirDeclarationBuilder.convertTypeArguments(lighterASTNode, false));
                    }
                }
            }
            i++;
            lightTreeRawFirDeclarationBuilder2 = lightTreeRawFirDeclarationBuilder;
        }
        if (asText == null) {
            FirErrorTypeRefBuilder firErrorTypeRefBuilder = new FirErrorTypeRefBuilder();
            firErrorTypeRefBuilder.setSource(typeRefSource);
            firErrorTypeRefBuilder.setDiagnostic(new ConeSyntaxDiagnostic("Incomplete user type"));
            if (firUserTypeRef != null && !firUserTypeRef.getQualifier().isEmpty()) {
                FirUserTypeRefBuilder firUserTypeRefBuilder = new FirUserTypeRefBuilder();
                KtSourceElement source = ((FirQualifierPart) CollectionsKt.last(firUserTypeRef.getQualifier())).getSource();
                source.getClass();
                firUserTypeRefBuilder.setSource(source);
                firUserTypeRefBuilder.setMarkedNullable(false);
                firUserTypeRefBuilder.getQualifier().addAll(firUserTypeRef.getQualifier());
                firErrorTypeRefBuilder.setPartiallyResolvedTypeRef(firUserTypeRefBuilder.build());
            }
            return firErrorTypeRefBuilder.build();
        }
        firSourceElement$default.getClass();
        Name nameNameAsSafeName$default = ConverterUtilKt.nameAsSafeName$default(asText, null, 1, null);
        if (firSourceElement$default2 == null) {
            firSourceElement$default2 = typeRefSource;
        }
        FirTypeArgumentListImpl firTypeArgumentListImpl = new FirTypeArgumentListImpl(firSourceElement$default2);
        CollectionsKt.addAll(firTypeArgumentListImpl.getTypeArguments(), arrayList);
        Unit unit = Unit.INSTANCE;
        FirQualifierPartImpl firQualifierPartImpl = new FirQualifierPartImpl(firSourceElement$default, nameNameAsSafeName$default, firTypeArgumentListImpl);
        FirUserTypeRefBuilder firUserTypeRefBuilder2 = new FirUserTypeRefBuilder();
        firUserTypeRefBuilder2.setSource(typeRefSource);
        firUserTypeRefBuilder2.setMarkedNullable(isNullable);
        firUserTypeRefBuilder2.getQualifier().add(firQualifierPartImpl);
        if (firUserTypeRef != null && (qualifier = firUserTypeRef.getQualifier()) != null) {
            firUserTypeRefBuilder2.getQualifier().addAll(0, qualifier);
        }
        return firUserTypeRefBuilder2.build();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public static /* synthetic */ FirTypeRef convertUserType$default(LightTreeRawFirDeclarationBuilder lightTreeRawFirDeclarationBuilder, KtSourceElement ktSourceElement, LighterASTNode lighterASTNode, boolean z, int i, Object obj) throws UninitializedPropertyAccessException {
        if ((i & 4) != 0) {
            z = false;
        }
        return lightTreeRawFirDeclarationBuilder.convertUserType(ktSourceElement, lighterASTNode, z);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ValueParameter convertValueParameter$default(LightTreeRawFirDeclarationBuilder lightTreeRawFirDeclarationBuilder, LighterASTNode lighterASTNode, FirBasedSymbol firBasedSymbol, AbstractRawFirBuilder.ValueParameterDeclaration valueParameterDeclaration, List list, int i, Object obj) {
        if ((i & 8) != 0) {
            list = CollectionsKt.emptyList();
        }
        return lightTreeRawFirDeclarationBuilder.convertValueParameter(lighterASTNode, firBasedSymbol, valueParameterDeclaration, list);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ List convertValueParameters$default(LightTreeRawFirDeclarationBuilder lightTreeRawFirDeclarationBuilder, LighterASTNode lighterASTNode, FirFunctionSymbol firFunctionSymbol, AbstractRawFirBuilder.ValueParameterDeclaration valueParameterDeclaration, List list, int i, Object obj) throws Exception {
        if ((i & 8) != 0) {
            list = CollectionsKt.emptyList();
        }
        return lightTreeRawFirDeclarationBuilder.convertValueParameters(lighterASTNode, firFunctionSymbol, valueParameterDeclaration, list);
    }

    private final void extractRawEffects(LighterASTNode rawContractDescription, List<FirExpression> destination) {
        KtSourceElement firSourceElement$default;
        FirExpression firExpressionBuildErrorExpression;
        KtSourceElement source;
        KtSourceElement firSourceElement$default2;
        for (LighterASTNode lighterASTNode : getChildrenAsArray(rawContractDescription)) {
            if (lighterASTNode == null) {
                return;
            }
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType()) && Intrinsics.areEqual(lighterASTNode.getTokenType(), KtNodeTypes.CONTRACT_EFFECT)) {
                LighterASTNode firstChild = getFirstChild(lighterASTNode);
                if (firstChild == null) {
                    firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression$default(AbstractRawFirBuilder.toFirSourceElement$default(this, rawContractDescription, null, 1, null), new ConeSimpleDiagnostic("The contract effect is not an expression", DiagnosticKind.ExpressionExpected), null, 4, null);
                } else {
                    LightTreeRawFirExpressionBuilder lightTreeRawFirExpressionBuilder = this.expressionConverter;
                    FirStatement asFirStatement = lightTreeRawFirExpressionBuilder.getAsFirStatement(firstChild, "The contract effect is not an expression");
                    if (asFirStatement instanceof FirExpression) {
                        FirExpression firExpression = (FirExpression) asFirStatement;
                        if (UtilsKt.isStatementLikeExpression(firExpression)) {
                            KtSourceElement source2 = firExpression.getSource();
                            if (source2 == null || (firSourceElement$default2 = KtSourceElementKt.realElement(source2)) == null) {
                                firSourceElement$default2 = AbstractRawFirBuilder.toFirSourceElement$default(lightTreeRawFirExpressionBuilder, firstChild, null, 1, null);
                            }
                            firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default2, new ConeSimpleDiagnostic("The contract effect is not an expression", DiagnosticKind.ExpressionExpected), asFirStatement);
                        } else {
                            firExpressionBuildErrorExpression = firExpression;
                        }
                    } else {
                        if (asFirStatement == null || (source = asFirStatement.getSource()) == null || (firSourceElement$default = KtSourceElementKt.realElement(source)) == null) {
                            firSourceElement$default = AbstractRawFirBuilder.toFirSourceElement$default(lightTreeRawFirExpressionBuilder, firstChild, null, 1, null);
                        }
                        firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default, new ConeSimpleDiagnostic("The contract effect is not an expression", DiagnosticKind.ExpressionExpected), asFirStatement);
                    }
                    if (firExpressionBuildErrorExpression == null) {
                        x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.expressions.FirExpression");
                        return;
                    }
                }
                destination.add(firExpressionBuildErrorExpression);
            }
        }
    }

    private final <T extends FirDeclaration & FirTypeParameterRefsOwner> void fillDanglingConstraintsTo(List<? extends FirTypeParameter> typeParameters, List<TypeConstraint> typeConstraints, T to) {
        List<? extends FirTypeParameter> list = typeParameters;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((FirTypeParameter) it.next()).getName());
        }
        Set set = CollectionsKt.toSet(arrayList);
        ArrayList arrayList2 = new ArrayList();
        for (TypeConstraint typeConstraint : typeConstraints) {
            String identifier = typeConstraint.getIdentifier();
            DanglingTypeConstraint danglingTypeConstraint = null;
            Name nameNameAsSafeName$default = identifier != null ? ConverterUtilKt.nameAsSafeName$default(identifier, null, 1, null) : null;
            if (nameNameAsSafeName$default != null && !set.contains(nameNameAsSafeName$default)) {
                danglingTypeConstraint = new DanglingTypeConstraint(nameNameAsSafeName$default, typeConstraint.getSource());
            }
            if (danglingTypeConstraint != null) {
                arrayList2.add(danglingTypeConstraint);
            }
        }
        if (arrayList2.isEmpty()) {
            return;
        }
        DeclarationAttributesKt.setDanglingTypeConstraints(to, arrayList2);
    }

    private final boolean functionCallHasLabel(KtSourceElement sourceElement) {
        Object obj;
        Object next;
        Object objSingleOrNull;
        Iterator it = LightTreeUtilsKt.getChildren(sourceElement.getLighterASTNode(), sourceElement.getTreeStructure()).iterator();
        do {
            obj = null;
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!Intrinsics.areEqual(getElementType((LighterASTNode) next), KtNodeTypes.CALL_EXPRESSION));
        if (next == null) {
            return false;
        }
        for (Object obj2 : LightTreeUtilsKt.getChildren((LighterASTNode) next, sourceElement.getTreeStructure())) {
            if (Intrinsics.areEqual(getElementType((LighterASTNode) obj2), KtNodeTypes.LAMBDA_ARGUMENT)) {
                obj = obj2;
                break;
            }
        }
        if (obj == null || (objSingleOrNull = CollectionsKt.singleOrNull(LightTreeUtilsKt.getChildren((LighterASTNode) obj, sourceElement.getTreeStructure()))) == null) {
            return false;
        }
        return Intrinsics.areEqual(getElementType((LighterASTNode) objSingleOrNull), KtNodeTypes.LABELED_EXPRESSION);
    }

    private final boolean hasValueParameters(LighterASTNode lighterASTNode) {
        IElementType iElementType = KtNodeTypes.VALUE_PARAMETER_LIST;
        iElementType.getClass();
        List<LighterASTNode> childNodesByType = getChildNodesByType(lighterASTNode, iElementType);
        if (childNodesByType.isEmpty()) {
            return false;
        }
        LighterASTNode lighterASTNode2 = (LighterASTNode) CollectionsKt.first(childNodesByType);
        IElementType iElementType2 = KtNodeTypes.VALUE_PARAMETER;
        iElementType2.getClass();
        return !getChildNodesByType(lighterASTNode2, iElementType2).isEmpty();
    }

    private final boolean isCallTheFirstStatement(KtSourceElement sourceElement) {
        List mutableList = CollectionsKt.toMutableList(CollectionsKt.asReversed(LightTreeUtilsKt.getChildren(sourceElement.getLighterASTNode(), sourceElement.getTreeStructure())));
        while (true) {
            List list = mutableList;
            if (!list.isEmpty()) {
                LighterASTNode lighterASTNode = (LighterASTNode) AddToStdlibKt.popLast(mutableList);
                IElementType elementType = getElementType(lighterASTNode);
                if (!Intrinsics.areEqual(elementType, KtTokens.LBRACE) && !Intrinsics.areEqual(elementType, KtTokens.WHITE_SPACE) && !Intrinsics.areEqual(elementType, KtTokens.DOT) && !Intrinsics.areEqual(elementType, KtTokens.EOL_COMMENT)) {
                    if (!Intrinsics.areEqual(elementType, KtNodeTypes.CALL_EXPRESSION)) {
                        if (!Intrinsics.areEqual(elementType, KtNodeTypes.REFERENCE_EXPRESSION)) {
                            if (!Intrinsics.areEqual(elementType, KtNodeTypes.DOT_QUALIFIED_EXPRESSION)) {
                                if (!Intrinsics.areEqual(elementType, KtNodeTypes.ANNOTATION_ENTRY)) {
                                    if (!Intrinsics.areEqual(elementType, KtNodeTypes.ANNOTATED_EXPRESSION)) {
                                        break;
                                    }
                                    CollectionsKt.addAll(list, CollectionsKt.asReversed(LightTreeUtilsKt.getChildren(lighterASTNode, sourceElement.getTreeStructure())));
                                } else {
                                    continue;
                                }
                            } else {
                                CollectionsKt.addAll(list, CollectionsKt.asReversed(LightTreeUtilsKt.getChildren(lighterASTNode, sourceElement.getTreeStructure())));
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

    private final FirContractDescription obtainContractDescription(LighterASTNode rawContractDescription) {
        FirRawContractDescriptionBuilder firRawContractDescriptionBuilder = new FirRawContractDescriptionBuilder();
        firRawContractDescriptionBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(this, rawContractDescription, null, 1, null));
        extractRawEffects(rawContractDescription, firRawContractDescriptionBuilder.getRawEffects());
        return firRawContractDescriptionBuilder.build();
    }

    private final ConeClassLikeType obtainDispatchReceiverForConstructor(ClassWrapper classWrapper) {
        if (classWrapper.isInner()) {
            return dispatchReceiverForInnerClassConstructor();
        }
        return null;
    }

    private final FirDeclarationStatus obtainPropertyComponentStatus(Visibility componentVisibility, ModifierList modifiers, ModifierList propertyModifiers) {
        FirDeclarationStatusImpl firDeclarationStatusImpl = new FirDeclarationStatusImpl(componentVisibility, modifiers.getModality(false));
        firDeclarationStatusImpl.setInline(propertyModifiers.hasInline() || modifiers.hasInline());
        firDeclarationStatusImpl.setExternal(propertyModifiers.hasExternal() || modifiers.hasExternal());
        firDeclarationStatusImpl.setLateInit(modifiers.hasLateinit());
        return firDeclarationStatusImpl;
    }

    public static String p(Ref.ObjectRef objectRef) {
        return (String) objectRef.element;
    }

    private final FqName parsePackageName(LighterASTNode node) {
        FqName fqNameChild = FqName.ROOT;
        Iterator<String> it = parsePackageParts(node).iterator();
        while (it.hasNext()) {
            Name nameIdentifier = Name.identifier(it.next());
            nameIdentifier.getClass();
            fqNameChild = fqNameChild.child(nameIdentifier);
        }
        return fqNameChild;
    }

    private final List<String> parsePackageParts(LighterASTNode node) {
        return parsePackageParts$parse(this, node);
    }

    private static final List<String> parsePackageParts$parse(LightTreeRawFirDeclarationBuilder lightTreeRawFirDeclarationBuilder, LighterASTNode lighterASTNode) {
        if (Intrinsics.areEqual(lighterASTNode.getTokenType(), KtNodeTypes.DOT_QUALIFIED_EXPRESSION)) {
            List children = LightTreeUtilsKt.getChildren(lighterASTNode, lightTreeRawFirDeclarationBuilder.getTree());
            if (children.size() == 3) {
                List<String> packageParts$parse = parsePackageParts$parse(lightTreeRawFirDeclarationBuilder, (LighterASTNode) CollectionsKt.first(children));
                packageParts$parse.add(ConverterUtilKt.getAsStringWithoutBacktick((LighterASTNode) CollectionsKt.last(children)));
                return packageParts$parse;
            }
        }
        return Intrinsics.areEqual(lighterASTNode.getTokenType(), KtNodeTypes.REFERENCE_EXPRESSION) ? CollectionsKt.mutableListOf(new String[]{ConverterUtilKt.getAsStringWithoutBacktick(lighterASTNode)}) : new ArrayList();
    }

    public static LighterASTNode q(LightTreeRawFirDeclarationBuilder lightTreeRawFirDeclarationBuilder, LighterASTNode lighterASTNode) {
        lighterASTNode.getClass();
        return lightTreeRawFirDeclarationBuilder.getParent(lighterASTNode);
    }

    public static LighterASTNode r(LightTreeRawFirDeclarationBuilder lightTreeRawFirDeclarationBuilder, LighterASTNode lighterASTNode) {
        lighterASTNode.getClass();
        return lightTreeRawFirDeclarationBuilder.getParent(lighterASTNode);
    }

    /* JADX WARN: Code duplicated, block: B:46:0x0105  */
    public final FirAnnotationCall convertAnnotationEntry(LighterASTNode unescapedAnnotation, AnnotationUseSiteTarget defaultAnnotationUseSiteTarget, ConeDiagnostic diagnostic) {
        Pair<FirTypeRef, List<FirExpression>> pair;
        Name nameSpecial;
        KtLightSourceElement firSourceElement$default;
        List<FirTypeProjection> listEmptyList;
        FirAnnotationCall firAnnotationCallMo288build;
        FirTypeArgumentList typeArgumentList;
        List<FirTypeProjection> listEmptyList2;
        FirTypeArgumentList typeArgumentList2;
        List qualifier;
        unescapedAnnotation.getClass();
        boolean forceKeepingTheBodyInHeaderMode = getContext().getForceKeepingTheBodyInHeaderMode();
        getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
        boolean inLocalContext = getContext().getInLocalContext();
        getContext().setInLocalContext(true);
        FqName classNameBeforeLocalContext = getContext().getClassNameBeforeLocalContext();
        if (!inLocalContext) {
            getContext().setClassNameBeforeLocalContext(getContext().getClassName());
        }
        FqName className = getContext().getClassName();
        getContext().setClassName(FqName.ROOT);
        try {
            Pair<FirTypeRef, List<FirExpression>> pairConvertConstructorInvocation = null;
            AnnotationUseSiteTarget annotationUseSiteTargetConvertAnnotationTarget = null;
            for (LighterASTNode lighterASTNode : getChildrenAsArray(unescapedAnnotation)) {
                if (lighterASTNode == null) {
                    break;
                }
                if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType())) {
                    IElementType tokenType = lighterASTNode.getTokenType();
                    if (Intrinsics.areEqual(tokenType, KtNodeTypes.ANNOTATION_TARGET)) {
                        annotationUseSiteTargetConvertAnnotationTarget = convertAnnotationTarget(lighterASTNode);
                    } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.CONSTRUCTOR_CALLEE)) {
                        pairConvertConstructorInvocation = convertConstructorInvocation(unescapedAnnotation);
                    }
                }
            }
            if (pairConvertConstructorInvocation == null) {
                Intrinsics.throwUninitializedPropertyAccessException("constructorCalleePair");
                pair = null;
            } else {
                pair = pairConvertConstructorInvocation;
            }
            Object first = pair.getFirst();
            FirUserTypeRef firUserTypeRef = first instanceof FirUserTypeRef ? (FirUserTypeRef) first : null;
            FirQualifierPart firQualifierPart = (firUserTypeRef == null || (qualifier = firUserTypeRef.getQualifier()) == null) ? null : (FirQualifierPart) CollectionsKt.last(qualifier);
            if (firQualifierPart == null || (nameSpecial = firQualifierPart.getName()) == null) {
                nameSpecial = Name.special("<no-annotation-name>");
                nameSpecial.getClass();
            }
            FirSimpleNamedReferenceBuilder firSimpleNamedReferenceBuilder = new FirSimpleNamedReferenceBuilder();
            IElementType iElementType = KtNodeTypes.CONSTRUCTOR_CALLEE;
            iElementType.getClass();
            LighterASTNode childNodeByType = getChildNodeByType(unescapedAnnotation, iElementType);
            if (childNodeByType != null) {
                IElementType iElementType2 = KtNodeTypes.TYPE_REFERENCE;
                iElementType2.getClass();
                LighterASTNode childNodeByType2 = getChildNodeByType(childNodeByType, iElementType2);
                if (childNodeByType2 != null) {
                    IElementType iElementType3 = KtNodeTypes.USER_TYPE;
                    iElementType3.getClass();
                    LighterASTNode childNodeByType3 = getChildNodeByType(childNodeByType2, iElementType3);
                    if (childNodeByType3 != null) {
                        IElementType iElementType4 = KtNodeTypes.REFERENCE_EXPRESSION;
                        iElementType4.getClass();
                        LighterASTNode childNodeByType4 = getChildNodeByType(childNodeByType3, iElementType4);
                        if (childNodeByType4 != null) {
                            firSourceElement$default = AbstractRawFirBuilder.toFirSourceElement$default(this, childNodeByType4, null, 1, null);
                        } else {
                            firSourceElement$default = null;
                        }
                    } else {
                        firSourceElement$default = null;
                    }
                } else {
                    firSourceElement$default = null;
                }
            } else {
                firSourceElement$default = null;
            }
            firSimpleNamedReferenceBuilder.setSource(firSourceElement$default);
            firSimpleNamedReferenceBuilder.setName(nameSpecial);
            FirNamedReference firNamedReferenceBuild = firSimpleNamedReferenceBuilder.build();
            if (diagnostic == null) {
                FirAnnotationCallBuilder firAnnotationCallBuilder = new FirAnnotationCallBuilder();
                firAnnotationCallBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(this, unescapedAnnotation, null, 1, null));
                if (annotationUseSiteTargetConvertAnnotationTarget == null) {
                    annotationUseSiteTargetConvertAnnotationTarget = defaultAnnotationUseSiteTarget;
                }
                firAnnotationCallBuilder.setUseSiteTarget(annotationUseSiteTargetConvertAnnotationTarget);
                firAnnotationCallBuilder.setAnnotationTypeRef((FirTypeRef) pairConvertConstructorInvocation.getFirst());
                firAnnotationCallBuilder.setCalleeReference(firNamedReferenceBuild);
                ConverterUtilKt.extractArgumentsFrom(firAnnotationCallBuilder, (List) pairConvertConstructorInvocation.getSecond());
                List<FirTypeProjection> typeArguments = firAnnotationCallBuilder.getTypeArguments();
                if (firQualifierPart == null || (typeArgumentList2 = firQualifierPart.getTypeArgumentList()) == null || (listEmptyList2 = typeArgumentList2.getTypeArguments()) == null) {
                    listEmptyList2 = CollectionsKt.emptyList();
                }
                CollectionsKt.addAll(typeArguments, listEmptyList2);
                firAnnotationCallBuilder.setContainingDeclarationSymbol(getContext().getContainerSymbol());
                firAnnotationCallMo288build = firAnnotationCallBuilder.mo288build();
            } else {
                FirErrorAnnotationCallBuilder firErrorAnnotationCallBuilder = new FirErrorAnnotationCallBuilder();
                firErrorAnnotationCallBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(this, unescapedAnnotation, null, 1, null));
                if (annotationUseSiteTargetConvertAnnotationTarget == null) {
                    annotationUseSiteTargetConvertAnnotationTarget = defaultAnnotationUseSiteTarget;
                }
                firErrorAnnotationCallBuilder.setUseSiteTarget(annotationUseSiteTargetConvertAnnotationTarget);
                firErrorAnnotationCallBuilder.setAnnotationTypeRef((FirTypeRef) pairConvertConstructorInvocation.getFirst());
                firErrorAnnotationCallBuilder.setDiagnostic(diagnostic);
                firErrorAnnotationCallBuilder.setCalleeReference(firNamedReferenceBuild);
                ConverterUtilKt.extractArgumentsFrom(firErrorAnnotationCallBuilder, (List) pairConvertConstructorInvocation.getSecond());
                List<FirTypeProjection> typeArguments2 = firErrorAnnotationCallBuilder.getTypeArguments();
                if (firQualifierPart == null || (typeArgumentList = firQualifierPart.getTypeArgumentList()) == null || (listEmptyList = typeArgumentList.getTypeArguments()) == null) {
                    listEmptyList = CollectionsKt.emptyList();
                }
                CollectionsKt.addAll(typeArguments2, listEmptyList);
                firErrorAnnotationCallBuilder.setContainingDeclarationSymbol(getContext().getContainerSymbol());
                firAnnotationCallMo288build = firErrorAnnotationCallBuilder.mo288build();
            }
            return firAnnotationCallMo288build;
        } finally {
            getContext().setClassNameBeforeLocalContext(classNameBeforeLocalContext);
            getContext().setInLocalContext(inLocalContext);
            getContext().setClassName(className);
            getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final void convertAnnotationTo(LighterASTNode node, List<? super FirAnnotationCall> list) throws UninitializedPropertyAccessException {
        node.getClass();
        list.getClass();
        AnnotationUseSiteTarget annotationUseSiteTargetConvertAnnotationTarget = null;
        for (LighterASTNode lighterASTNode : getChildrenAsArray(node)) {
            if (lighterASTNode == null) {
                return;
            }
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType())) {
                IElementType tokenType = lighterASTNode.getTokenType();
                if (Intrinsics.areEqual(tokenType, KtNodeTypes.ANNOTATION_TARGET)) {
                    annotationUseSiteTargetConvertAnnotationTarget = convertAnnotationTarget(lighterASTNode);
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.ANNOTATION_ENTRY)) {
                    list.add(convertAnnotationEntry(lighterASTNode, annotationUseSiteTargetConvertAnnotationTarget, annotationUseSiteTargetConvertAnnotationTarget == AnnotationUseSiteTarget.ALL ? new ConeSimpleDiagnostic("Multiple annotation syntax with @all use-site target is forbidden", DiagnosticKind.MultipleAnnotationWithAllTarget) : null));
                }
            }
        }
    }

    public final FirBlock convertBlock(LighterASTNode block, boolean convertOnlyFirstStatement) throws Exception {
        if (block == null) {
            return FirEmptyExpressionBlockBuilderKt.buildEmptyExpressionBlock();
        }
        return !Intrinsics.areEqual(block.getTokenType(), KtNodeTypes.BLOCK) ? new FirSingleExpressionBlock(LightTreeRawFirExpressionBuilder.getAsFirStatement$default(this.expressionConverter, block, null, 2, null)) : convertBlockExpression(block, convertOnlyFirstStatement);
    }

    public final FirBlock convertBlockExpression(LighterASTNode block, boolean convertOnlyFirstStatement) throws Exception {
        block.getClass();
        return convertBlockExpressionWithoutBuilding$default(this, block, null, convertOnlyFirstStatement, 2, null).mo288build();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:50:0x0102  */
    public final FirBlockBuilder convertBlockExpressionWithoutBuilding(LighterASTNode block, KtFakeSourceElementKind kind, boolean convertOnlyFirstStatement) throws Exception {
        boolean z;
        LighterASTNode lighterASTNode;
        block.getClass();
        LighterASTNode[] childrenAsArray = getChildrenAsArray(block);
        ArrayList<FirStatement> arrayList = new ArrayList();
        int length = childrenAsArray.length;
        for (int i = 0; i < length && (lighterASTNode = childrenAsArray[i]) != null; i++) {
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType()) && (!convertOnlyFirstStatement || arrayList.isEmpty())) {
                IElementType tokenType = lighterASTNode.getTokenType();
                if (Intrinsics.areEqual(tokenType, KtNodeTypes.CLASS) || Intrinsics.areEqual(tokenType, KtNodeTypes.OBJECT_DECLARATION)) {
                    FirRegularClass firRegularClassConvertClass = convertClass(lighterASTNode);
                    firRegularClassConvertClass.getClass();
                    arrayList.add(firRegularClassConvertClass);
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.FUN)) {
                    arrayList.add(convertFunctionDeclaration(lighterASTNode));
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.PROPERTY)) {
                    FirProperty firPropertyConvertPropertyDeclaration$default = convertPropertyDeclaration$default(this, lighterASTNode, null, 2, null);
                    firPropertyConvertPropertyDeclaration$default.getClass();
                    arrayList.add(firPropertyConvertPropertyDeclaration$default);
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.DESTRUCTURING_DECLARATION)) {
                    arrayList.add(DestructuringDeclaration.toFirDestructingDeclaration$default(convertDestructingDeclaration$org_jetbrains_kotlin_fir_light_tree2fir(lighterASTNode), this, getBaseModuleData(), false, 4, null));
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.TYPEALIAS)) {
                    FirTypeAlias firTypeAliasConvertTypeAlias = convertTypeAlias(lighterASTNode);
                    firTypeAliasConvertTypeAlias.getClass();
                    arrayList.add(firTypeAliasConvertTypeAlias);
                } else {
                    if (Intrinsics.areEqual(tokenType, KtNodeTypes.CLASS_INITIALIZER)) {
                        AddToStdlibKt.shouldNotBeCalled("CLASS_INITIALIZER expected to be processed during class body conversion");
                        wq6.a();
                        return null;
                    }
                    if (ElementTypeUtils.INSTANCE.isExpression(lighterASTNode)) {
                        arrayList.add(LightTreeRawFirExpressionBuilder.getAsFirStatement$default(this.expressionConverter, lighterASTNode, null, 2, null));
                    }
                }
            }
        }
        FirBlockBuilder firBlockBuilder = new FirBlockBuilder();
        firBlockBuilder.setSource(toFirSourceElement(block, kind));
        for (FirStatement firStatement : arrayList) {
            boolean z2 = firStatement instanceof FirBlock;
            if (z2) {
                KtSourceElement source = ((FirBlock) firStatement).getSource();
                if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DesugaredForLoop.INSTANCE)) {
                    z = true;
                } else {
                    z = false;
                }
            } else {
                z = false;
            }
            if (z2 && !z) {
                FirBlock firBlock = (FirBlock) firStatement;
                if (firBlock.getAnnotations().isEmpty()) {
                    CollectionsKt.addAll(firBlockBuilder.getStatements(), firBlock.getStatements());
                }
            }
            firBlockBuilder.getStatements().add(firStatement);
        }
        return firBlockBuilder;
    }

    /* JADX WARN: Code duplicated, block: B:352:0x0854 A[Catch: all -> 0x07e0, TryCatch #0 {all -> 0x07e0, blocks: (B:350:0x0844, B:352:0x0854, B:354:0x0862, B:355:0x0879, B:356:0x0893, B:357:0x0894, B:358:0x08b8, B:326:0x07b9, B:327:0x07df), top: B:629:0x01b6 }] */
    /* JADX WARN: Code duplicated, block: B:354:0x0862 A[Catch: all -> 0x07e0, TryCatch #0 {all -> 0x07e0, blocks: (B:350:0x0844, B:352:0x0854, B:354:0x0862, B:355:0x0879, B:356:0x0893, B:357:0x0894, B:358:0x08b8, B:326:0x07b9, B:327:0x07df), top: B:629:0x01b6 }] */
    /* JADX WARN: Code duplicated, block: B:357:0x0894 A[Catch: all -> 0x07e0, TryCatch #0 {all -> 0x07e0, blocks: (B:350:0x0844, B:352:0x0854, B:354:0x0862, B:355:0x0879, B:356:0x0893, B:357:0x0894, B:358:0x08b8, B:326:0x07b9, B:327:0x07df), top: B:629:0x01b6 }] */
    /* JADX WARN: Code duplicated, block: B:623:0x0f18  */
    /* JADX WARN: Code duplicated, block: B:625:0x0f26  */
    /* JADX WARN: Code duplicated, block: B:628:0x0f58  */
    /* JADX WARN: Code duplicated, block: B:684:0x0128 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:685:0x0128 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:68:0x00fe  */
    /* JADX WARN: Code duplicated, block: B:70:0x0106 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:71:0x0108  */
    /* JADX WARN: Code duplicated, block: B:72:0x010d  */
    /* JADX WARN: Code duplicated, block: B:76:0x0117  */
    /* JADX WARN: Code duplicated, block: B:79:0x0120  */
    /* JADX WARN: Code duplicated, block: B:82:0x012a  */
    /* JADX WARN: Instruction removed from duplicated block: B:357:0x0894, please report this as an issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v20, types: [org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus, org.jetbrains.kotlin.fir.declarations.impl.FirDeclarationStatusImpl] */
    /* JADX WARN: Type inference failed for: r12v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r18v12, types: [org.jetbrains.kotlin.fir.lightTree.fir.modifier.ModifierList] */
    /* JADX WARN: Type inference failed for: r18v14, types: [org.jetbrains.kotlin.fir.lightTree.fir.modifier.ModifierList] */
    /* JADX WARN: Type inference failed for: r21v10 */
    /* JADX WARN: Type inference failed for: r21v11, types: [boolean] */
    /* JADX WARN: Type inference failed for: r21v12 */
    /* JADX WARN: Type inference failed for: r21v14 */
    /* JADX WARN: Type inference failed for: r21v19 */
    /* JADX WARN: Type inference failed for: r21v20 */
    /* JADX WARN: Type inference failed for: r21v21 */
    /* JADX WARN: Type inference failed for: r26v0 */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v130 */
    /* JADX WARN: Type inference failed for: r2v2, types: [org.jetbrains.kotlin.fir.lightTree.fir.modifier.ModifierList] */
    /* JADX WARN: Type inference failed for: r2v48, types: [org.jetbrains.kotlin.fir.builder.Context] */
    /* JADX WARN: Type inference failed for: r2v49, types: [org.jetbrains.kotlin.fir.builder.Context] */
    /* JADX WARN: Type inference failed for: r33v0, types: [org.jetbrains.kotlin.fir.lightTree.fir.modifier.ModifierList] */
    /* JADX WARN: Type inference failed for: r3v103, types: [org.jetbrains.kotlin.fir.declarations.builder.FirRegularClassBuilder] */
    /* JADX WARN: Type inference failed for: r3v118 */
    /* JADX WARN: Type inference failed for: r3v119 */
    /* JADX WARN: Type inference failed for: r3v32 */
    /* JADX WARN: Type inference failed for: r3v33, types: [org.jetbrains.kotlin.fir.declarations.builder.FirRegularClassBuilder] */
    /* JADX WARN: Type inference failed for: r3v50, types: [org.jetbrains.kotlin.fir.declarations.builder.FirRegularClassBuilder] */
    /* JADX WARN: Type inference failed for: r3v85 */
    /* JADX WARN: Type inference failed for: r3v86, types: [org.jetbrains.kotlin.fir.declarations.builder.FirRegularClassBuilder] */
    /* JADX WARN: Type inference failed for: r47v0, types: [org.jetbrains.kotlin.fir.builder.AbstractRawFirBuilder, org.jetbrains.kotlin.fir.lightTree.converter.AbstractLightTreeRawFirBuilder, org.jetbrains.kotlin.fir.lightTree.converter.LightTreeRawFirDeclarationBuilder] */
    /* JADX WARN: Type inference failed for: r4v137 */
    /* JADX WARN: Type inference failed for: r4v32 */
    /* JADX WARN: Type inference failed for: r4v33 */
    /* JADX WARN: Type inference failed for: r4v60, types: [org.jetbrains.kotlin.fir.lightTree.fir.modifier.ModifierList] */
    /* JADX WARN: Type inference failed for: r4v98, types: [org.jetbrains.kotlin.fir.declarations.builder.FirClassBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirRegularClassBuilder] */
    /* JADX WARN: Type inference failed for: r5v21, types: [org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus, org.jetbrains.kotlin.fir.declarations.impl.FirDeclarationStatusImpl] */
    /* JADX WARN: Type inference failed for: r5v34 */
    /* JADX WARN: Type inference failed for: r5v35, types: [org.jetbrains.kotlin.name.FqName] */
    /* JADX WARN: Type inference failed for: r5v36 */
    /* JADX WARN: Type inference failed for: r5v50 */
    /* JADX WARN: Type inference failed for: r5v58 */
    /* JADX WARN: Type inference failed for: r5v7 */
    /* JADX WARN: Type inference failed for: r5v8, types: [boolean] */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v1, types: [org.jetbrains.kotlin.fir.lightTree.fir.modifier.ModifierList] */
    /* JADX WARN: Type inference failed for: r6v22 */
    /* JADX WARN: Type inference failed for: r6v23, types: [boolean] */
    /* JADX WARN: Type inference failed for: r6v24 */
    /* JADX WARN: Type inference failed for: r6v38 */
    /* JADX WARN: Type inference failed for: r6v52 */
    /* JADX WARN: Type inference failed for: r6v53, types: [org.jetbrains.kotlin.fir.lightTree.fir.modifier.ModifierList] */
    /* JADX WARN: Type inference failed for: r6v54 */
    /* JADX WARN: Type inference failed for: r8v5, types: [org.jetbrains.kotlin.fir.declarations.builder.FirClassBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirRegularClassBuilder] */
    /* JADX WARN: Type inference failed for: r9v0, types: [org.jetbrains.kotlin.fir.lightTree.fir.modifier.ModifierList] */
    /* JADX WARN: Type inference failed for: r9v47 */
    /* JADX WARN: Type inference failed for: r9v48, types: [org.jetbrains.kotlin.fir.lightTree.fir.modifier.ModifierList] */
    /* JADX WARN: Type inference failed for: r9v49 */
    public final FirRegularClass convertClass(LighterASTNode classNode) throws Throwable {
        boolean z;
        int i;
        int size;
        FirRegularClassSymbol firRegularClassSymbol;
        boolean z2;
        Visibility visibility;
        ?? r4;
        boolean z3;
        List<DelegatedConstructorWrapper> listEmptyList;
        Map<Integer, FirFieldSymbol> delegateFieldsMap;
        ?? r3;
        Ref.ObjectRef objectRef;
        FirRegularClass firRegularClassMo288build;
        Map<Integer, FirFieldSymbol> delegateFieldsMap2;
        Collection<FirFieldSymbol> collectionValues;
        List<DelegatedConstructorWrapper> superTypeCalls;
        DelegatedConstructorWrapper delegatedConstructorWrapper;
        List<DelegatedConstructorWrapper> superTypeCalls2;
        DelegatedConstructorWrapper delegatedConstructorWrapper2;
        boolean containerIsExpect;
        int i2;
        String str;
        boolean z4;
        FirRegularClassSymbol firRegularClassSymbol2;
        boolean z5;
        Visibility visibility2;
        boolean z6;
        LighterASTNode lighterASTNode;
        boolean z7;
        List<DelegatedConstructorWrapper> listEmptyList2;
        Map<Integer, FirFieldSymbol> delegateFieldsMap3;
        ?? r5;
        ?? r21;
        ?? r9;
        Map<Integer, FirFieldSymbol> delegateFieldsMap4;
        Collection<FirFieldSymbol> collectionValues2;
        List<DelegatedConstructorWrapper> superTypeCalls3;
        DelegatedConstructorWrapper delegatedConstructorWrapper3;
        List<DelegatedConstructorWrapper> superTypeCalls4;
        DelegatedConstructorWrapper delegatedConstructorWrapper4;
        IElementType tokenType;
        LighterASTNode parent;
        LighterASTNode lighterASTNode2;
        classNode.getClass();
        ClassKind classKind = ClassKind.CLASS;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        LighterASTNode[] childrenAsArray = getChildrenAsArray(classNode);
        int length = childrenAsArray.length;
        ?? ConvertModifierList = 0;
        String asText = null;
        int i3 = 0;
        while (i3 < length && (lighterASTNode2 = childrenAsArray[i3]) != null) {
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode2.getTokenType())) {
                IElementType tokenType2 = lighterASTNode2.getTokenType();
                if (Intrinsics.areEqual(tokenType2, KtNodeTypes.MODIFIER_LIST)) {
                    ConvertModifierList = convertModifierList(lighterASTNode2, true);
                } else if (Intrinsics.areEqual(tokenType2, KtTokens.IDENTIFIER)) {
                    asText = getAsText(lighterASTNode2);
                }
            }
            i3++;
            ConvertModifierList = ConvertModifierList;
        }
        ?? modifierList = ConvertModifierList == 0 ? new ModifierList(0L, 1, null) : ConvertModifierList;
        Name nameNameAsSafeName = ConverterUtilKt.nameAsSafeName(asText, modifierList.isCompanion() ? "Companion" : Argument.Delimiters.none);
        LighterASTNode parent2 = getParent(classNode);
        if (Intrinsics.areEqual(parent2 != null ? getElementType(parent2) : null, KtNodeTypes.CLASS_BODY)) {
            z = false;
        } else {
            LighterASTNode parent3 = getParent(classNode);
            if (Intrinsics.areEqual((parent3 == null || (parent = getParent(parent3)) == null) ? null : parent.getTokenType(), KtNodeTypes.SCRIPT)) {
                z = false;
            } else {
                LighterASTNode parent4 = classNode;
                while (true) {
                    if (parent4 != null) {
                        IElementType tokenType3 = parent4.getTokenType();
                        parent4 = getParent(parent4);
                        IElementType tokenType4 = parent4 != null ? parent4.getTokenType() : null;
                        if (Intrinsics.areEqual(tokenType3, KtNodeTypes.PROPERTY) || Intrinsics.areEqual(tokenType3, KtNodeTypes.FUN)) {
                            LighterASTNode parent5 = parent4 != null ? getParent(parent4) : null;
                            if (!Intrinsics.areEqual(tokenType4, KtNodeTypes.KT_FILE)) {
                                if (Intrinsics.areEqual(tokenType4, KtNodeTypes.CLASS_BODY)) {
                                    if (Intrinsics.areEqual(parent5 != null ? parent5.getTokenType() : null, KtNodeTypes.OBJECT_DECLARATION)) {
                                        LighterASTNode parent6 = getParent(parent5);
                                        if (Intrinsics.areEqual(parent6 != null ? parent6.getTokenType() : null, KtNodeTypes.OBJECT_LITERAL)) {
                                            if (!Intrinsics.areEqual(tokenType4, KtNodeTypes.BLOCK)) {
                                                if (parent5 != null) {
                                                    tokenType = parent5.getTokenType();
                                                } else {
                                                    tokenType = null;
                                                }
                                                if (Intrinsics.areEqual(tokenType, KtNodeTypes.SCRIPT)) {
                                                    if (Intrinsics.areEqual(tokenType4, KtNodeTypes.ENUM_ENTRY)) {
                                                    }
                                                }
                                            } else if (Intrinsics.areEqual(tokenType4, KtNodeTypes.ENUM_ENTRY)) {
                                            }
                                        }
                                    }
                                } else if (!Intrinsics.areEqual(tokenType4, KtNodeTypes.BLOCK)) {
                                    if (parent5 != null) {
                                        tokenType = parent5.getTokenType();
                                    } else {
                                        tokenType = null;
                                    }
                                    if (Intrinsics.areEqual(tokenType, KtNodeTypes.SCRIPT)) {
                                        if (Intrinsics.areEqual(tokenType4, KtNodeTypes.ENUM_ENTRY)) {
                                        }
                                    }
                                } else if (Intrinsics.areEqual(tokenType4, KtNodeTypes.ENUM_ENTRY) && !Intrinsics.areEqual(tokenType3, KtNodeTypes.BLOCK)) {
                                }
                            }
                        } else if (Intrinsics.areEqual(tokenType4, KtNodeTypes.ENUM_ENTRY)) {
                        }
                        z = true;
                    } else {
                        z = false;
                    }
                }
            }
        }
        ?? r6 = (modifierList.hasExpect() || getContext().getContainerIsExpect()) ? 1 : 0;
        FqName fqName = "Wrong number of ";
        if (z) {
            boolean forceKeepingTheBodyInHeaderMode = getContext().getForceKeepingTheBodyInHeaderMode();
            getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
            boolean inLocalContext = getContext().getInLocalContext();
            getContext().setInLocalContext(true);
            FqName classNameBeforeLocalContext = getContext().getClassNameBeforeLocalContext();
            if (!inLocalContext) {
                getContext().setClassNameBeforeLocalContext(getContext().getClassName());
            }
            FqName className = getContext().getClassName();
            getContext().setClassName(FqName.ROOT);
            try {
                getContext().setClassName(getContext().getClassName().child(nameNameAsSafeName));
                containerIsExpect = getContext().getContainerIsExpect();
                try {
                    try {
                        getContext().setContainerIsExpect(containerIsExpect || r6 != 0);
                        int size2 = getContext().getDispatchReceiverTypesStack().size();
                        try {
                            i2 = size2;
                            try {
                                FirRegularClassSymbol firRegularClassSymbol3 = new FirRegularClassSymbol(getContext().getCurrentClassId());
                                getContext().pushContainerSymbol(firRegularClassSymbol3);
                                try {
                                    LighterASTNode[] childrenAsArray2 = getChildrenAsArray(classNode);
                                    try {
                                        int length2 = childrenAsArray2.length;
                                        z4 = containerIsExpect;
                                        LighterASTNode lighterASTNode3 = null;
                                        LighterASTNode lighterASTNode4 = null;
                                        LighterASTNode lighterASTNode5 = null;
                                        LighterASTNode lighterASTNode6 = null;
                                        int i4 = 0;
                                        while (i4 < length2) {
                                            int i5 = length2;
                                            try {
                                                LighterASTNode lighterASTNode7 = childrenAsArray2[i4];
                                                if (lighterASTNode7 == null) {
                                                    break;
                                                }
                                                int i6 = i4;
                                                LighterASTNode lighterASTNode8 = lighterASTNode5;
                                                if (AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode7.getTokenType())) {
                                                    lighterASTNode5 = lighterASTNode8;
                                                } else {
                                                    IElementType tokenType5 = lighterASTNode7.getTokenType();
                                                    if (Intrinsics.areEqual(tokenType5, KtTokens.CLASS_KEYWORD)) {
                                                        classKind = ClassKind.CLASS;
                                                    } else if (Intrinsics.areEqual(tokenType5, KtTokens.INTERFACE_KEYWORD)) {
                                                        classKind = ClassKind.INTERFACE;
                                                    } else if (Intrinsics.areEqual(tokenType5, KtTokens.OBJECT_KEYWORD)) {
                                                        classKind = ClassKind.OBJECT;
                                                    } else if (Intrinsics.areEqual(tokenType5, KtNodeTypes.TYPE_PARAMETER_LIST)) {
                                                        lighterASTNode3 = lighterASTNode7;
                                                    } else if (Intrinsics.areEqual(tokenType5, KtNodeTypes.PRIMARY_CONSTRUCTOR)) {
                                                        lighterASTNode5 = lighterASTNode7;
                                                    } else if (Intrinsics.areEqual(tokenType5, KtNodeTypes.SUPER_TYPE_LIST)) {
                                                        lighterASTNode4 = lighterASTNode7;
                                                    } else if (Intrinsics.areEqual(tokenType5, KtNodeTypes.TYPE_CONSTRAINT_LIST)) {
                                                        CollectionsKt.addAll(arrayList2, convertTypeConstraints(lighterASTNode7));
                                                    } else if (Intrinsics.areEqual(tokenType5, KtNodeTypes.CLASS_BODY)) {
                                                        lighterASTNode6 = lighterASTNode7;
                                                    }
                                                    lighterASTNode5 = lighterASTNode8;
                                                }
                                                i4 = i6 + 1;
                                                length2 = i5;
                                            } catch (Throwable th) {
                                                th = th;
                                                firRegularClassSymbol2 = firRegularClassSymbol3;
                                                i2 = i2;
                                                str = "Wrong number of ";
                                                z4 = z4;
                                                try {
                                                    getContext().popContainerSymbol(firRegularClassSymbol2);
                                                    throw th;
                                                } catch (Throwable th2) {
                                                    th = th2;
                                                    if (getContext().getDispatchReceiverTypesStack().size() <= i2 + 1) {
                                                        throw new IllegalArgumentException((str + getContext().getDispatchReceiverTypesStack().size()).toString());
                                                    }
                                                    if (getContext().getDispatchReceiverTypesStack().size() > i2) {
                                                        getContext().getDispatchReceiverTypesStack().remove(CollectionsKt.getLastIndex(getContext().getDispatchReceiverTypesStack()));
                                                    }
                                                    getContext().setClassName(getContext().getClassName().parent());
                                                    getContext().setContainerIsExpect(z4);
                                                    throw th;
                                                }
                                            }
                                        }
                                        LighterASTNode lighterASTNode9 = lighterASTNode5;
                                        if (classKind == ClassKind.CLASS) {
                                            if (modifierList.isEnum()) {
                                                classKind = ClassKind.ENUM_CLASS;
                                            } else if (modifierList.isAnnotation()) {
                                                classKind = ClassKind.ANNOTATION_CLASS;
                                            }
                                        }
                                        boolean inLocalContext2 = getContext().getInLocalContext();
                                        if (inLocalContext2) {
                                            visibility2 = Visibilities.Local.INSTANCE;
                                            z5 = true;
                                        } else {
                                            z5 = true;
                                            visibility2 = modifierList.getVisibility(true);
                                        }
                                        LighterASTNode lighterASTNode10 = lighterASTNode6;
                                        ?? firDeclarationStatusImpl = new FirDeclarationStatusImpl(visibility2, modifierList.getModality(z5));
                                        firDeclarationStatusImpl.setExpect(r6);
                                        firDeclarationStatusImpl.setActual(modifierList.hasActual());
                                        firDeclarationStatusImpl.setInner(modifierList.isInner());
                                        firDeclarationStatusImpl.setCompanion(modifierList.isCompanion() && classKind == ClassKind.OBJECT);
                                        firDeclarationStatusImpl.setData(modifierList.isDataClass());
                                        firDeclarationStatusImpl.setInline(modifierList.isInlineClass());
                                        firDeclarationStatusImpl.setValue(modifierList.isValueClass());
                                        firDeclarationStatusImpl.setFun(modifierList.isFunctionalInterface());
                                        firDeclarationStatusImpl.setExternal(modifierList.hasExternal());
                                        if (lighterASTNode3 != null) {
                                            CollectionsKt.addAll(arrayList, convertTypeParameters(lighterASTNode3, arrayList2, firRegularClassSymbol3));
                                            Unit unit = Unit.INSTANCE;
                                        }
                                        addCapturedTypeParameters(firDeclarationStatusImpl.isInner() || inLocalContext2, AbstractRawFirBuilder.toFirSourceElement$default(this, classNode, null, 1, null), arrayList);
                                        try {
                                            Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                                            CompanionBlockCollector companionBlockCollector = new CompanionBlockCollector();
                                            ?? firRegularClassBuilder = new FirRegularClassBuilder();
                                            ?? r18 = modifierList;
                                            firRegularClassBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(this, classNode, null, 1, null));
                                            firRegularClassBuilder.setModuleData(getBaseModuleData());
                                            firRegularClassBuilder.setOrigin(FirDeclarationOrigin.Source.INSTANCE);
                                            firRegularClassBuilder.setName(nameNameAsSafeName);
                                            firRegularClassBuilder.setStatus(firDeclarationStatusImpl);
                                            firRegularClassBuilder.setClassKind(classKind);
                                            firRegularClassBuilder.setScopeProvider(this.baseScopeProvider);
                                            firRegularClassBuilder.setSymbol(firRegularClassSymbol3);
                                            if (ConvertModifierList != 0) {
                                                convertAnnotationsTo(ConvertModifierList, firRegularClassBuilder.getAnnotations());
                                                Unit unit2 = Unit.INSTANCE;
                                            }
                                            CollectionsKt.addAll(firRegularClassBuilder.getTypeParameters(), arrayList);
                                            getContext().appendOuterTypeParameters(true, firRegularClassBuilder.getTypeParameters());
                                            FirResolvedTypeRef delegatedSelfType = toDelegatedSelfType(classNode, firRegularClassBuilder);
                                            registerSelfType(delegatedSelfType);
                                            DelegationSpecifiers delegationSpecifiersConvertDelegationSpecifiers = lighterASTNode4 != null ? convertDelegationSpecifiers(lighterASTNode4) : null;
                                            FirTypeRef delegatedSuperTypeRef = (delegationSpecifiersConvertDelegationSpecifiers == null || (superTypeCalls4 = delegationSpecifiersConvertDelegationSpecifiers.getSuperTypeCalls()) == null || (delegatedConstructorWrapper4 = (DelegatedConstructorWrapper) CollectionsKt.lastOrNull(superTypeCalls4)) == null) ? null : delegatedConstructorWrapper4.getDelegatedSuperTypeRef();
                                            KtLightSourceElement source = (delegationSpecifiersConvertDelegationSpecifiers == null || (superTypeCalls3 = delegationSpecifiersConvertDelegationSpecifiers.getSuperTypeCalls()) == null || (delegatedConstructorWrapper3 = (DelegatedConstructorWrapper) CollectionsKt.lastOrNull(superTypeCalls3)) == null) ? null : delegatedConstructorWrapper3.getSource();
                                            ArrayList arrayList3 = new ArrayList();
                                            if (delegationSpecifiersConvertDelegationSpecifiers != null) {
                                                CollectionsKt.addAll(arrayList3, delegationSpecifiersConvertDelegationSpecifiers.getSuperTypesRef());
                                                Unit unit3 = Unit.INSTANCE;
                                            }
                                            if (r18.isEnum() && classKind == ClassKind.ENUM_CLASS && source == null) {
                                                FirResolvedTypeRefBuilder firResolvedTypeRefBuilder = new FirResolvedTypeRefBuilder();
                                                firResolvedTypeRefBuilder.setConeType(new ConeClassLikeTypeImpl(getImplicitEnumType().getConeType().getLookupTag(), new ConeKotlinType[]{delegatedSelfType.getConeType()}, false, null, 8, null));
                                                firResolvedTypeRefBuilder.setSource(toFirSourceElement(classNode, KtFakeSourceElementKind.EnumSuperTypeRef.INSTANCE));
                                                delegatedSuperTypeRef = firResolvedTypeRefBuilder.build();
                                                arrayList3.add(delegatedSuperTypeRef);
                                            } else if (r18.isAnnotation() && classKind == ClassKind.ANNOTATION_CLASS) {
                                                arrayList3.add(getImplicitAnnotationType());
                                                delegatedSuperTypeRef = getImplicitAnyType();
                                            }
                                            ClassId classId = firRegularClassBuilder.getSymbol().getClassId();
                                            StandardClassIds standardClassIds = StandardClassIds.INSTANCE;
                                            boolean zAreEqual = Intrinsics.areEqual(classId, standardClassIds.getAny());
                                            if (arrayList3.isEmpty() && !zAreEqual) {
                                                if (!Intrinsics.areEqual(firRegularClassBuilder.getSymbol().getClassId(), standardClassIds.getNothing())) {
                                                    arrayList3.add(getImplicitAnyType());
                                                }
                                                delegatedSuperTypeRef = getImplicitAnyType();
                                            }
                                            CollectionsKt.addAll(firRegularClassBuilder.getSuperTypeRefs(), arrayList3);
                                            IElementType iElementType = KtNodeTypes.SECONDARY_CONSTRUCTOR;
                                            iElementType.getClass();
                                            List<LighterASTNode> childNodesByType = getChildNodesByType(lighterASTNode10, iElementType);
                                            boolean z8 = !childNodesByType.isEmpty();
                                            if (lighterASTNode9 != null) {
                                                z6 = zAreEqual;
                                                if (hasValueParameters(lighterASTNode9)) {
                                                    lighterASTNode = lighterASTNode9;
                                                    z7 = false;
                                                } else {
                                                    lighterASTNode = lighterASTNode9;
                                                    z7 = true;
                                                }
                                            } else {
                                                z6 = zAreEqual;
                                                if (childNodesByType.isEmpty()) {
                                                    lighterASTNode = lighterASTNode9;
                                                } else {
                                                    List<LighterASTNode> list = childNodesByType;
                                                    if ((list instanceof Collection) && list.isEmpty()) {
                                                        lighterASTNode = lighterASTNode9;
                                                    } else {
                                                        lighterASTNode = lighterASTNode9;
                                                        lighterASTNode = lighterASTNode9;
                                                        Iterator it = list.iterator();
                                                        while (true) {
                                                            if (it.hasNext()) {
                                                                if (!hasValueParameters((LighterASTNode) it.next())) {
                                                                }
                                                            }
                                                        }
                                                    }
                                                    lighterASTNode = lighterASTNode9;
                                                    z7 = false;
                                                }
                                                lighterASTNode = lighterASTNode9;
                                                z7 = true;
                                            }
                                            if (delegatedSuperTypeRef == null) {
                                                delegatedSuperTypeRef = FirImplicitTypeRefImplWithoutSource.INSTANCE;
                                            }
                                            FirTypeRef firTypeRef = delegatedSuperTypeRef;
                                            if (delegationSpecifiersConvertDelegationSpecifiers == null || (listEmptyList2 = delegationSpecifiersConvertDelegationSpecifiers.getSuperTypeCalls()) == null) {
                                                listEmptyList2 = CollectionsKt.emptyList();
                                            }
                                            ClassKind classKind2 = classKind;
                                            ClassWrapper classWrapper = new ClassWrapper(r18, classKind2, firRegularClassBuilder, z8, z7, delegatedSelfType, firTypeRef, listEmptyList2, companionBlockCollector);
                                            KtSourceElement source2 = delegatedSelfType.getSource();
                                            boolean zIsExpect = firDeclarationStatusImpl.isExpect();
                                            boolean zIsImplicitlyActual = isImplicitlyActual(firDeclarationStatusImpl, classKind2);
                                            ?? r19 = ConvertModifierList;
                                            ?? r22 = r6;
                                            firRegularClassSymbol2 = firRegularClassSymbol3;
                                            LighterASTNode lighterASTNode11 = lighterASTNode;
                                            try {
                                                PrimaryConstructor primaryConstructorConvertPrimaryConstructor$default = convertPrimaryConstructor$default(this, lighterASTNode11, source2, classWrapper, source, false, zIsExpect, zIsImplicitlyActual, z6, 16, null);
                                                FirConstructor firConstructor = primaryConstructorConvertPrimaryConstructor$default != null ? primaryConstructorConvertPrimaryConstructor$default.getFirConstructor() : null;
                                                if (firConstructor != null) {
                                                    firRegularClassBuilder.getDeclarations().add(firConstructor);
                                                    Unit unit4 = Unit.INSTANCE;
                                                }
                                                if (delegationSpecifiersConvertDelegationSpecifiers != null && (delegateFieldsMap4 = delegationSpecifiersConvertDelegationSpecifiers.getDelegateFieldsMap()) != null && (collectionValues2 = delegateFieldsMap4.values()) != null) {
                                                    List<FirDeclaration> declarations = firRegularClassBuilder.getDeclarations();
                                                    Iterator it2 = collectionValues2.iterator();
                                                    while (it2.hasNext()) {
                                                        declarations.add((FirField) ((FirFieldSymbol) it2.next()).getFir());
                                                    }
                                                    List<FirDeclaration> list2 = declarations;
                                                }
                                                if (delegationSpecifiersConvertDelegationSpecifiers == null || (delegateFieldsMap3 = delegationSpecifiersConvertDelegationSpecifiers.getDelegateFieldsMap()) == null || delegateFieldsMap3.isEmpty()) {
                                                    delegateFieldsMap3 = null;
                                                }
                                                objectRef2.element = delegateFieldsMap3;
                                                ArrayList<FirProperty> arrayList4 = new ArrayList();
                                                if (lighterASTNode11 == null || firConstructor == null) {
                                                    r5 = firRegularClassBuilder;
                                                    r21 = r22;
                                                } else {
                                                    List<ValueParameter> valueParameters = primaryConstructorConvertPrimaryConstructor$default.getValueParameters();
                                                    ArrayList<ValueParameter> arrayList5 = new ArrayList();
                                                    for (Object obj : valueParameters) {
                                                        if (((ValueParameter) obj).hasValOrVar()) {
                                                            arrayList5.add(obj);
                                                        }
                                                    }
                                                    ArrayList arrayList6 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList5, 10));
                                                    ?? r23 = r22;
                                                    for (ValueParameter valueParameter : arrayList5) {
                                                        FirModuleData baseModuleData = getBaseModuleData();
                                                        CallableId callableIdCallableIdForName = callableIdForName(valueParameter.getFirValueParameter().getName());
                                                        boolean z9 = r23 == true ? 1 : 0;
                                                        FirProperty firPropertyFromPrimaryConstructor = valueParameter.toFirPropertyFromPrimaryConstructor(baseModuleData, callableIdCallableIdForName, z9, currentDispatchReceiverType(), getContext());
                                                        r23 = z9 ? 1 : 0;
                                                        arrayList6.add(firPropertyFromPrimaryConstructor);
                                                    }
                                                    CollectionsKt.addAll(arrayList4, arrayList6);
                                                    ?? r7 = firRegularClassBuilder;
                                                    FirDeclarationBuildingUtilsKt.addDeclarations(r7, arrayList4);
                                                    r5 = r7;
                                                    r21 = r23;
                                                }
                                                if (lighterASTNode10 != null) {
                                                    FirDeclarationBuildingUtilsKt.addDeclarations(r5, convertClassBody(lighterASTNode10, classWrapper));
                                                    Unit unit5 = Unit.INSTANCE;
                                                }
                                                if (!r18.isDataClass() || firConstructor == null) {
                                                    r9 = r18;
                                                } else {
                                                    FirConstructor firConstructor2 = firConstructor;
                                                    ArrayList arrayList7 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList4, 10));
                                                    for (FirProperty firProperty : arrayList4) {
                                                        KtSourceElement source3 = firProperty.getSource();
                                                        source3.getClass();
                                                        arrayList7.add(TuplesKt.to(source3.getLighterASTNode(), firProperty));
                                                    }
                                                    if (lighterASTNode11 == null) {
                                                        lighterASTNode11 = classNode;
                                                    }
                                                    r9 = r18;
                                                    new AbstractRawFirBuilder.DataClassMembersGenerator(this, lighterASTNode11, r5, firConstructor2, arrayList7, getContext().getPackageFqName(), getContext().getClassName(), new Function2() { // from class: i19
                                                        public final Object invoke(Object obj2, Object obj3) {
                                                            return LightTreeRawFirDeclarationBuilder.convertClass$lambda$2$0$3$0$11(this.b, (FirValueParameterBuilder) obj2, (LighterASTNode) obj3);
                                                        }
                                                    }).generate();
                                                }
                                                if (r9.isEnum()) {
                                                    EnumClassUtilsKt.generateValuesFunction$default(r5, getBaseModuleData(), getContext().getPackageFqName(), getContext().getClassName(), r21, null, 16, null);
                                                    EnumClassUtilsKt.generateValueOfFunction$default(r5, getBaseModuleData(), getContext().getPackageFqName(), getContext().getClassName(), r21, null, 16, null);
                                                    EnumClassUtilsKt.generateEntriesGetter$default(r5, getBaseModuleData(), getContext().getPackageFqName(), getContext().getClassName(), r21, null, 16, null);
                                                }
                                                initCompanionObjectSymbolAttr(r5);
                                                addContextParameters(r5.getContextParameters(), r19 != 0 ? r19.getContextLists() : null, firRegularClassSymbol2);
                                                firRegularClassMo288build = r5.mo288build();
                                                DelegateFieldsMapKt.setDelegateFieldsMap(firRegularClassMo288build, (Map) objectRef2.element);
                                                CompanionBlockInfo companionBlockInfoOrNull = companionBlockCollector.toCompanionBlockInfoOrNull();
                                                if (companionBlockInfoOrNull != null) {
                                                    ClassMembersKt.setCompanionBlocks(firRegularClassMo288build, companionBlockInfoOrNull);
                                                    Unit unit6 = Unit.INSTANCE;
                                                }
                                                try {
                                                    getContext().popFirTypeParameters();
                                                    fillDanglingConstraintsTo(arrayList, arrayList2, firRegularClassMo288build);
                                                    try {
                                                        getContext().popContainerSymbol(firRegularClassSymbol2);
                                                        try {
                                                            if (getContext().getDispatchReceiverTypesStack().size() > i2 + 1) {
                                                                throw new IllegalArgumentException(("Wrong number of " + getContext().getDispatchReceiverTypesStack().size()).toString());
                                                            }
                                                            if (getContext().getDispatchReceiverTypesStack().size() > i2) {
                                                                getContext().getDispatchReceiverTypesStack().remove(CollectionsKt.getLastIndex(getContext().getDispatchReceiverTypesStack()));
                                                            }
                                                            getContext().setClassName(getContext().getClassName().parent());
                                                            getContext().setContainerIsExpect(z4);
                                                            getContext().setClassNameBeforeLocalContext(classNameBeforeLocalContext);
                                                            getContext().setInLocalContext(inLocalContext);
                                                            getContext().setClassName(className);
                                                            getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
                                                        } catch (Throwable th3) {
                                                            th = th3;
                                                            containerIsExpect = forceKeepingTheBodyInHeaderMode;
                                                            ConvertModifierList = inLocalContext;
                                                            r6 = classNameBeforeLocalContext;
                                                            fqName = className;
                                                            getContext().setClassNameBeforeLocalContext(r6);
                                                            getContext().setInLocalContext(ConvertModifierList);
                                                            getContext().setClassName(fqName);
                                                            getContext().setForceKeepingTheBodyInHeaderMode(containerIsExpect);
                                                            throw th;
                                                        }
                                                    } catch (Throwable th4) {
                                                        th = th4;
                                                        i2 = i2;
                                                        z4 = z4;
                                                        str = "Wrong number of ";
                                                        if (getContext().getDispatchReceiverTypesStack().size() <= i2 + 1) {
                                                            throw new IllegalArgumentException((str + getContext().getDispatchReceiverTypesStack().size()).toString());
                                                        }
                                                        if (getContext().getDispatchReceiverTypesStack().size() > i2) {
                                                            getContext().getDispatchReceiverTypesStack().remove(CollectionsKt.getLastIndex(getContext().getDispatchReceiverTypesStack()));
                                                        }
                                                        getContext().setClassName(getContext().getClassName().parent());
                                                        getContext().setContainerIsExpect(z4);
                                                        throw th;
                                                    }
                                                } catch (Throwable th5) {
                                                    th = th5;
                                                    i2 = i2;
                                                    z4 = z4;
                                                    str = "Wrong number of ";
                                                    getContext().popContainerSymbol(firRegularClassSymbol2);
                                                    throw th;
                                                }
                                            } catch (Throwable th6) {
                                                th = th6;
                                                str = "Wrong number of ";
                                                try {
                                                    getContext().popFirTypeParameters();
                                                    throw th;
                                                } catch (Throwable th7) {
                                                    th = th7;
                                                    getContext().popContainerSymbol(firRegularClassSymbol2);
                                                    throw th;
                                                }
                                            }
                                        } catch (Throwable th8) {
                                            th = th8;
                                            firRegularClassSymbol2 = firRegularClassSymbol3;
                                            str = "Wrong number of ";
                                        }
                                    } catch (Throwable th9) {
                                        th = th9;
                                        z4 = containerIsExpect;
                                        firRegularClassSymbol2 = firRegularClassSymbol3;
                                        i2 = i2;
                                        str = "Wrong number of ";
                                    }
                                } catch (Throwable th10) {
                                    th = th10;
                                    str = "Wrong number of ";
                                    z4 = containerIsExpect;
                                    firRegularClassSymbol2 = firRegularClassSymbol3;
                                    i2 = i2;
                                }
                            } catch (Throwable th11) {
                                th = th11;
                                str = "Wrong number of ";
                                z4 = containerIsExpect;
                                i2 = i2;
                            }
                        } catch (Throwable th12) {
                            th = th12;
                            i2 = size2;
                            str = "Wrong number of ";
                            z4 = containerIsExpect;
                        }
                    } catch (Throwable th13) {
                        th = th13;
                    }
                } catch (Throwable th14) {
                    th = th14;
                    ConvertModifierList = inLocalContext;
                    r6 = classNameBeforeLocalContext;
                    fqName = className;
                    containerIsExpect = forceKeepingTheBodyInHeaderMode;
                }
            } catch (Throwable th15) {
                th = th15;
                containerIsExpect = forceKeepingTheBodyInHeaderMode;
                ConvertModifierList = inLocalContext;
                r6 = classNameBeforeLocalContext;
                fqName = className;
            }
        } else {
            ?? r10 = modifierList;
            ?? r12 = r6;
            ?? r26 = ConvertModifierList;
            String str2 = "Wrong number of ";
            getContext().setClassName(getContext().getClassName().child(nameNameAsSafeName));
            boolean containerIsExpect2 = getContext().getContainerIsExpect();
            getContext().setContainerIsExpect(containerIsExpect2 || r12 != 0);
            int size3 = getContext().getDispatchReceiverTypesStack().size();
            try {
                FirRegularClassSymbol firRegularClassSymbol4 = new FirRegularClassSymbol(getContext().getCurrentClassId());
                getContext().pushContainerSymbol(firRegularClassSymbol4);
                try {
                    LighterASTNode[] childrenAsArray3 = getChildrenAsArray(classNode);
                    int length3 = childrenAsArray3.length;
                    containerIsExpect2 = containerIsExpect2;
                    LighterASTNode lighterASTNode12 = null;
                    int i7 = 0;
                    LighterASTNode lighterASTNode13 = null;
                    LighterASTNode lighterASTNode14 = null;
                    LighterASTNode lighterASTNode15 = null;
                    while (i7 < length3) {
                        int i8 = i7;
                        try {
                            LighterASTNode lighterASTNode16 = childrenAsArray3[i8];
                            if (lighterASTNode16 == null) {
                                break;
                            }
                            i = size3;
                            try {
                                LighterASTNode[] lighterASTNodeArr = childrenAsArray3;
                                if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode16.getTokenType())) {
                                    IElementType tokenType6 = lighterASTNode16.getTokenType();
                                    if (Intrinsics.areEqual(tokenType6, KtTokens.CLASS_KEYWORD)) {
                                        classKind = ClassKind.CLASS;
                                    } else if (Intrinsics.areEqual(tokenType6, KtTokens.INTERFACE_KEYWORD)) {
                                        classKind = ClassKind.INTERFACE;
                                    } else if (Intrinsics.areEqual(tokenType6, KtTokens.OBJECT_KEYWORD)) {
                                        classKind = ClassKind.OBJECT;
                                    } else if (Intrinsics.areEqual(tokenType6, KtNodeTypes.TYPE_PARAMETER_LIST)) {
                                        lighterASTNode13 = lighterASTNode16;
                                    } else if (Intrinsics.areEqual(tokenType6, KtNodeTypes.PRIMARY_CONSTRUCTOR)) {
                                        lighterASTNode12 = lighterASTNode16;
                                    } else if (Intrinsics.areEqual(tokenType6, KtNodeTypes.SUPER_TYPE_LIST)) {
                                        lighterASTNode14 = lighterASTNode16;
                                    } else if (Intrinsics.areEqual(tokenType6, KtNodeTypes.TYPE_CONSTRAINT_LIST)) {
                                        CollectionsKt.addAll(arrayList2, convertTypeConstraints(lighterASTNode16));
                                    } else if (Intrinsics.areEqual(tokenType6, KtNodeTypes.CLASS_BODY)) {
                                        lighterASTNode15 = lighterASTNode16;
                                    }
                                }
                                i7 = i8 + 1;
                                childrenAsArray3 = lighterASTNodeArr;
                                size3 = i;
                            } catch (Throwable th16) {
                                th = th16;
                            }
                        } catch (Throwable th17) {
                            th = th17;
                            i = size3;
                            firRegularClassSymbol = firRegularClassSymbol4;
                            containerIsExpect2 = containerIsExpect2;
                            str2 = str2;
                            i = i;
                        }
                        th = th16;
                        firRegularClassSymbol = firRegularClassSymbol4;
                        containerIsExpect2 = containerIsExpect2;
                        str2 = str2;
                        i = i;
                        try {
                            getContext().popContainerSymbol(firRegularClassSymbol);
                            throw th;
                        } catch (Throwable th18) {
                            th = th18;
                            if (getContext().getDispatchReceiverTypesStack().size() > i + 1) {
                                size = getContext().getDispatchReceiverTypesStack().size();
                                v1f.a(str2, size);
                                return null;
                            }
                            if (getContext().getDispatchReceiverTypesStack().size() > i) {
                                getContext().getDispatchReceiverTypesStack().remove(CollectionsKt.getLastIndex(getContext().getDispatchReceiverTypesStack()));
                            }
                            getContext().setClassName(getContext().getClassName().parent());
                            getContext().setContainerIsExpect(containerIsExpect2);
                            throw th;
                        }
                    }
                    i = size3;
                    if (classKind == ClassKind.CLASS) {
                        if (r10.isEnum()) {
                            classKind = ClassKind.ENUM_CLASS;
                        } else if (r10.isAnnotation()) {
                            classKind = ClassKind.ANNOTATION_CLASS;
                        }
                    }
                    boolean inLocalContext3 = getContext().getInLocalContext();
                    if (inLocalContext3) {
                        visibility = Visibilities.Local.INSTANCE;
                        z2 = true;
                    } else {
                        z2 = true;
                        visibility = r10.getVisibility(true);
                    }
                    ?? firDeclarationStatusImpl2 = new FirDeclarationStatusImpl(visibility, r10.getModality(z2));
                    firDeclarationStatusImpl2.setExpect(r12);
                    firDeclarationStatusImpl2.setActual(r10.hasActual());
                    firDeclarationStatusImpl2.setInner(r10.isInner());
                    firDeclarationStatusImpl2.setCompanion(r10.isCompanion() && classKind == ClassKind.OBJECT);
                    firDeclarationStatusImpl2.setData(r10.isDataClass());
                    firDeclarationStatusImpl2.setInline(r10.isInlineClass());
                    firDeclarationStatusImpl2.setValue(r10.isValueClass());
                    firDeclarationStatusImpl2.setFun(r10.isFunctionalInterface());
                    firDeclarationStatusImpl2.setExternal(r10.hasExternal());
                    if (lighterASTNode13 != null) {
                        CollectionsKt.addAll(arrayList, convertTypeParameters(lighterASTNode13, arrayList2, firRegularClassSymbol4));
                        Unit unit7 = Unit.INSTANCE;
                    }
                    addCapturedTypeParameters(firDeclarationStatusImpl2.isInner() || inLocalContext3, AbstractRawFirBuilder.toFirSourceElement$default(this, classNode, null, 1, null), arrayList);
                    try {
                        Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
                        CompanionBlockCollector companionBlockCollector2 = new CompanionBlockCollector();
                        ?? firRegularClassBuilder2 = new FirRegularClassBuilder();
                        firRegularClassBuilder2.setSource(AbstractRawFirBuilder.toFirSourceElement$default(this, classNode, null, 1, null));
                        firRegularClassBuilder2.setModuleData(getBaseModuleData());
                        firRegularClassBuilder2.setOrigin(FirDeclarationOrigin.Source.INSTANCE);
                        firRegularClassBuilder2.setName(nameNameAsSafeName);
                        firRegularClassBuilder2.setStatus(firDeclarationStatusImpl2);
                        firRegularClassBuilder2.setClassKind(classKind);
                        firRegularClassBuilder2.setScopeProvider(this.baseScopeProvider);
                        firRegularClassBuilder2.setSymbol(firRegularClassSymbol4);
                        if (r26 != 0) {
                            ?? r8 = r26;
                            convertAnnotationsTo(r8, firRegularClassBuilder2.getAnnotations());
                            Unit unit8 = Unit.INSTANCE;
                            r4 = r8;
                        } else {
                            r4 = r26;
                        }
                        CollectionsKt.addAll(firRegularClassBuilder2.getTypeParameters(), arrayList);
                        getContext().appendOuterTypeParameters(true, firRegularClassBuilder2.getTypeParameters());
                        FirResolvedTypeRef delegatedSelfType2 = toDelegatedSelfType(classNode, firRegularClassBuilder2);
                        registerSelfType(delegatedSelfType2);
                        DelegationSpecifiers delegationSpecifiersConvertDelegationSpecifiers2 = lighterASTNode14 != null ? convertDelegationSpecifiers(lighterASTNode14) : null;
                        FirTypeRef delegatedSuperTypeRef2 = (delegationSpecifiersConvertDelegationSpecifiers2 == null || (superTypeCalls2 = delegationSpecifiersConvertDelegationSpecifiers2.getSuperTypeCalls()) == null || (delegatedConstructorWrapper2 = (DelegatedConstructorWrapper) CollectionsKt.lastOrNull(superTypeCalls2)) == null) ? null : delegatedConstructorWrapper2.getDelegatedSuperTypeRef();
                        KtLightSourceElement source4 = (delegationSpecifiersConvertDelegationSpecifiers2 == null || (superTypeCalls = delegationSpecifiersConvertDelegationSpecifiers2.getSuperTypeCalls()) == null || (delegatedConstructorWrapper = (DelegatedConstructorWrapper) CollectionsKt.lastOrNull(superTypeCalls)) == null) ? null : delegatedConstructorWrapper.getSource();
                        ArrayList arrayList8 = new ArrayList();
                        if (delegationSpecifiersConvertDelegationSpecifiers2 != null) {
                            CollectionsKt.addAll(arrayList8, delegationSpecifiersConvertDelegationSpecifiers2.getSuperTypesRef());
                            Unit unit9 = Unit.INSTANCE;
                        }
                        if (r10.isEnum() && classKind == ClassKind.ENUM_CLASS && source4 == null) {
                            FirResolvedTypeRefBuilder firResolvedTypeRefBuilder2 = new FirResolvedTypeRefBuilder();
                            firResolvedTypeRefBuilder2.setConeType(new ConeClassLikeTypeImpl(getImplicitEnumType().getConeType().getLookupTag(), new ConeKotlinType[]{delegatedSelfType2.getConeType()}, false, null, 8, null));
                            firResolvedTypeRefBuilder2.setSource(toFirSourceElement(classNode, KtFakeSourceElementKind.EnumSuperTypeRef.INSTANCE));
                            delegatedSuperTypeRef2 = firResolvedTypeRefBuilder2.build();
                            arrayList8.add(delegatedSuperTypeRef2);
                        } else if (r10.isAnnotation() && classKind == ClassKind.ANNOTATION_CLASS) {
                            arrayList8.add(getImplicitAnnotationType());
                            delegatedSuperTypeRef2 = getImplicitAnyType();
                        }
                        ClassId classId2 = firRegularClassBuilder2.getSymbol().getClassId();
                        StandardClassIds standardClassIds2 = StandardClassIds.INSTANCE;
                        ?? r33 = r4;
                        boolean zAreEqual2 = Intrinsics.areEqual(classId2, standardClassIds2.getAny());
                        if (arrayList8.isEmpty() && !zAreEqual2) {
                            if (!Intrinsics.areEqual(firRegularClassBuilder2.getSymbol().getClassId(), standardClassIds2.getNothing())) {
                                arrayList8.add(getImplicitAnyType());
                            }
                            delegatedSuperTypeRef2 = getImplicitAnyType();
                        }
                        CollectionsKt.addAll(firRegularClassBuilder2.getSuperTypeRefs(), arrayList8);
                        IElementType iElementType2 = KtNodeTypes.SECONDARY_CONSTRUCTOR;
                        iElementType2.getClass();
                        List<LighterASTNode> childNodesByType2 = getChildNodesByType(lighterASTNode15, iElementType2);
                        boolean z10 = !childNodesByType2.isEmpty();
                        if (lighterASTNode12 != null) {
                            z3 = !hasValueParameters(lighterASTNode12);
                        } else if (!childNodesByType2.isEmpty()) {
                            List<LighterASTNode> list3 = childNodesByType2;
                            if (!(list3 instanceof Collection) || !list3.isEmpty()) {
                                Iterator it3 = list3.iterator();
                                while (true) {
                                    if (it3.hasNext()) {
                                        if (!hasValueParameters((LighterASTNode) it3.next())) {
                                        }
                                    }
                                }
                            }
                        }
                        if (delegatedSuperTypeRef2 == null) {
                            delegatedSuperTypeRef2 = FirImplicitTypeRefImplWithoutSource.INSTANCE;
                        }
                        FirTypeRef firTypeRef2 = delegatedSuperTypeRef2;
                        if (delegationSpecifiersConvertDelegationSpecifiers2 == null || (listEmptyList = delegationSpecifiersConvertDelegationSpecifiers2.getSuperTypeCalls()) == null) {
                            listEmptyList = CollectionsKt.emptyList();
                        }
                        ClassKind classKind3 = classKind;
                        ClassWrapper classWrapper2 = new ClassWrapper(r10, classKind3, firRegularClassBuilder2, z10, z3, delegatedSelfType2, firTypeRef2, listEmptyList, companionBlockCollector2);
                        KtSourceElement source5 = delegatedSelfType2.getSource();
                        boolean zIsExpect2 = firDeclarationStatusImpl2.isExpect();
                        boolean zIsImplicitlyActual2 = isImplicitlyActual(firDeclarationStatusImpl2, classKind3);
                        KtLightSourceElement ktLightSourceElement = source4;
                        boolean z11 = r12 == true ? 1 : 0;
                        firRegularClassSymbol = firRegularClassSymbol4;
                        try {
                            PrimaryConstructor primaryConstructorConvertPrimaryConstructor$default2 = convertPrimaryConstructor$default(this, lighterASTNode12, source5, classWrapper2, ktLightSourceElement, false, zIsExpect2, zIsImplicitlyActual2, zAreEqual2, 16, null);
                            FirConstructor firConstructor3 = primaryConstructorConvertPrimaryConstructor$default2 != null ? primaryConstructorConvertPrimaryConstructor$default2.getFirConstructor() : null;
                            if (firConstructor3 != null) {
                                firRegularClassBuilder2.getDeclarations().add(firConstructor3);
                                Unit unit10 = Unit.INSTANCE;
                            }
                            if (delegationSpecifiersConvertDelegationSpecifiers2 != null && (delegateFieldsMap2 = delegationSpecifiersConvertDelegationSpecifiers2.getDelegateFieldsMap()) != null && (collectionValues = delegateFieldsMap2.values()) != null) {
                                List<FirDeclaration> declarations2 = firRegularClassBuilder2.getDeclarations();
                                Iterator it4 = collectionValues.iterator();
                                while (it4.hasNext()) {
                                    declarations2.add((FirField) ((FirFieldSymbol) it4.next()).getFir());
                                }
                                List<FirDeclaration> list4 = declarations2;
                            }
                            if (delegationSpecifiersConvertDelegationSpecifiers2 == null || (delegateFieldsMap = delegationSpecifiersConvertDelegationSpecifiers2.getDelegateFieldsMap()) == null || delegateFieldsMap.isEmpty()) {
                                delegateFieldsMap = null;
                            }
                            objectRef3.element = delegateFieldsMap;
                            ArrayList<FirProperty> arrayList9 = new ArrayList();
                            if (lighterASTNode12 == null || firConstructor3 == null) {
                                r3 = firRegularClassBuilder2;
                            } else {
                                List<ValueParameter> valueParameters2 = primaryConstructorConvertPrimaryConstructor$default2.getValueParameters();
                                ArrayList<ValueParameter> arrayList10 = new ArrayList();
                                for (Object obj2 : valueParameters2) {
                                    if (((ValueParameter) obj2).hasValOrVar()) {
                                        arrayList10.add(obj2);
                                    }
                                }
                                ArrayList arrayList11 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList10, 10));
                                for (ValueParameter valueParameter2 : arrayList10) {
                                    FirModuleData baseModuleData2 = getBaseModuleData();
                                    CallableId callableIdCallableIdForName2 = callableIdForName(valueParameter2.getFirValueParameter().getName());
                                    boolean z12 = z11 ? 1 : 0;
                                    FirProperty firPropertyFromPrimaryConstructor2 = valueParameter2.toFirPropertyFromPrimaryConstructor(baseModuleData2, callableIdCallableIdForName2, z12, currentDispatchReceiverType(), getContext());
                                    z11 = z12 ? 1 : 0;
                                    arrayList11.add(firPropertyFromPrimaryConstructor2);
                                }
                                CollectionsKt.addAll(arrayList9, arrayList11);
                                ?? r11 = firRegularClassBuilder2;
                                FirDeclarationBuildingUtilsKt.addDeclarations(r11, arrayList9);
                                r3 = r11;
                            }
                            if (lighterASTNode15 != null) {
                                FirDeclarationBuildingUtilsKt.addDeclarations(r3, convertClassBody(lighterASTNode15, classWrapper2));
                                Unit unit11 = Unit.INSTANCE;
                            }
                            if (!r10.isDataClass() || firConstructor3 == null) {
                                objectRef = objectRef3;
                            } else {
                                FirConstructor firConstructor4 = firConstructor3;
                                ArrayList arrayList12 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList9, 10));
                                for (FirProperty firProperty2 : arrayList9) {
                                    KtSourceElement source6 = firProperty2.getSource();
                                    source6.getClass();
                                    arrayList12.add(TuplesKt.to(source6.getLighterASTNode(), firProperty2));
                                }
                                if (lighterASTNode12 == null) {
                                    lighterASTNode12 = classNode;
                                }
                                objectRef = objectRef3;
                                new AbstractRawFirBuilder.DataClassMembersGenerator(this, lighterASTNode12, r3, firConstructor4, arrayList12, getContext().getPackageFqName(), getContext().getClassName(), new Function2() { // from class: i19
                                    public final Object invoke(Object obj3, Object obj4) {
                                        return LightTreeRawFirDeclarationBuilder.convertClass$lambda$2$0$3$0$11(this.b, (FirValueParameterBuilder) obj3, (LighterASTNode) obj4);
                                    }
                                }).generate();
                            }
                            if (r10.isEnum()) {
                                EnumClassUtilsKt.generateValuesFunction$default(r3, getBaseModuleData(), getContext().getPackageFqName(), getContext().getClassName(), z11, null, 16, null);
                                EnumClassUtilsKt.generateValueOfFunction$default(r3, getBaseModuleData(), getContext().getPackageFqName(), getContext().getClassName(), z11, null, 16, null);
                                EnumClassUtilsKt.generateEntriesGetter$default(r3, getBaseModuleData(), getContext().getPackageFqName(), getContext().getClassName(), z11, null, 16, null);
                            }
                            initCompanionObjectSymbolAttr(r3);
                            addContextParameters(r3.getContextParameters(), r33 != 0 ? r33.getContextLists() : null, firRegularClassSymbol);
                            firRegularClassMo288build = r3.mo288build();
                            DelegateFieldsMapKt.setDelegateFieldsMap(firRegularClassMo288build, (Map) objectRef.element);
                            CompanionBlockInfo companionBlockInfoOrNull2 = companionBlockCollector2.toCompanionBlockInfoOrNull();
                            if (companionBlockInfoOrNull2 != null) {
                                ClassMembersKt.setCompanionBlocks(firRegularClassMo288build, companionBlockInfoOrNull2);
                                Unit unit12 = Unit.INSTANCE;
                            }
                            try {
                                getContext().popFirTypeParameters();
                                fillDanglingConstraintsTo(arrayList, arrayList2, firRegularClassMo288build);
                                try {
                                    getContext().popContainerSymbol(firRegularClassSymbol);
                                    if (getContext().getDispatchReceiverTypesStack().size() > i + 1) {
                                        size = getContext().getDispatchReceiverTypesStack().size();
                                        str2 = str2;
                                        v1f.a(str2, size);
                                        return null;
                                    }
                                    if (getContext().getDispatchReceiverTypesStack().size() > i) {
                                        getContext().getDispatchReceiverTypesStack().remove(CollectionsKt.getLastIndex(getContext().getDispatchReceiverTypesStack()));
                                    }
                                    getContext().setClassName(getContext().getClassName().parent());
                                    getContext().setContainerIsExpect(containerIsExpect2);
                                } catch (Throwable th19) {
                                    th = th19;
                                    containerIsExpect2 = containerIsExpect2;
                                    i = i;
                                    str2 = str2;
                                    if (getContext().getDispatchReceiverTypesStack().size() > i + 1) {
                                        if (getContext().getDispatchReceiverTypesStack().size() > i) {
                                            getContext().getDispatchReceiverTypesStack().remove(CollectionsKt.getLastIndex(getContext().getDispatchReceiverTypesStack()));
                                        }
                                        getContext().setClassName(getContext().getClassName().parent());
                                        getContext().setContainerIsExpect(containerIsExpect2);
                                        throw th;
                                    }
                                    size = getContext().getDispatchReceiverTypesStack().size();
                                }
                            } catch (Throwable th20) {
                                th = th20;
                                containerIsExpect2 = containerIsExpect2;
                                i = i;
                                str2 = str2;
                                getContext().popContainerSymbol(firRegularClassSymbol);
                                throw th;
                            }
                        } catch (Throwable th21) {
                            th = th21;
                            str2 = str2;
                            try {
                                getContext().popFirTypeParameters();
                                throw th;
                            } catch (Throwable th22) {
                                th = th22;
                            }
                        }
                    } catch (Throwable th23) {
                        th = th23;
                        firRegularClassSymbol = firRegularClassSymbol4;
                        str2 = str2;
                    }
                } catch (Throwable th24) {
                    th = th24;
                    i = size3;
                    firRegularClassSymbol = firRegularClassSymbol4;
                }
            } catch (Throwable th25) {
                th = th25;
                i = size3;
            }
        }
        LighterASTNode parent7 = getParent(classNode);
        if (Intrinsics.areEqual(parent7 != null ? getElementType(parent7) : null, KtStubElementTypes.CLASS_BODY)) {
            initContainingClassForLocalAttr(firRegularClassMo288build);
        }
        initContainingScriptOrReplAttr(firRegularClassMo288build);
        if (isDirectlyInsideCompanionBlock()) {
            ClassMembersKt.setIllegalCompanionBlockMember(firRegularClassMo288build, Boolean.TRUE);
        }
        return firRegularClassMo288build;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    /* JADX WARN: Code duplicated, block: B:66:0x0128  */
    public final DestructuringDeclaration convertDestructingDeclaration$org_jetbrains_kotlin_fir_light_tree2fir(LighterASTNode destructingDeclaration) throws Exception {
        boolean z;
        LighterASTNode lighterASTNode;
        KtSourceElement firSourceElement$default;
        FirExpression firExpressionBuildErrorExpression;
        KtSourceElement source;
        KtSourceElement firSourceElement$default2;
        destructingDeclaration.getClass();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        boolean z2 = true;
        KtLightSourceElement firSourceElement$default3 = AbstractRawFirBuilder.toFirSourceElement$default(this, destructingDeclaration, null, 1, null);
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        LighterASTNode[] childrenAsArray = getChildrenAsArray(destructingDeclaration);
        int length = childrenAsArray.length;
        int i = 0;
        boolean z3 = false;
        boolean z4 = false;
        while (i < length && (lighterASTNode = childrenAsArray[i]) != null) {
            if (AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType())) {
                arrayList2 = arrayList2;
            } else {
                IElementType tokenType = lighterASTNode.getTokenType();
                if (Intrinsics.areEqual(tokenType, KtTokens.LBRACKET)) {
                    arrayList2 = arrayList2;
                    z4 = z2;
                } else {
                    if (Intrinsics.areEqual(tokenType, KtNodeTypes.MODIFIER_LIST)) {
                        convertAnnotationsOnlyTo(lighterASTNode, arrayList);
                    } else if (Intrinsics.areEqual(tokenType, KtTokens.VAR_KEYWORD)) {
                        arrayList2 = arrayList2;
                        z3 = z2;
                    } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.DESTRUCTURING_DECLARATION_ENTRY)) {
                        arrayList2.add(convertDestructingDeclarationEntry(lighterASTNode, z3));
                    } else if (!Intrinsics.areEqual(tokenType, KtNodeTypes.PROPERTY_DELEGATE) && ElementTypeUtils.INSTANCE.isExpression(lighterASTNode)) {
                        LightTreeRawFirExpressionBuilder lightTreeRawFirExpressionBuilder = this.expressionConverter;
                        FirStatement asFirStatement = lightTreeRawFirExpressionBuilder.getAsFirStatement(lighterASTNode, "Initializer required for destructuring declaration");
                        if (asFirStatement instanceof FirExpression) {
                            firExpressionBuildErrorExpression = (FirExpression) asFirStatement;
                            if (UtilsKt.isStatementLikeExpression(firExpressionBuildErrorExpression)) {
                                KtSourceElement source2 = firExpressionBuildErrorExpression.getSource();
                                if (source2 == null || (firSourceElement$default2 = KtSourceElementKt.realElement(source2)) == null) {
                                    firSourceElement$default2 = AbstractRawFirBuilder.toFirSourceElement$default(lightTreeRawFirExpressionBuilder, lighterASTNode, null, 1, null);
                                }
                                firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default2, new ConeSimpleDiagnostic("Initializer required for destructuring declaration", DiagnosticKind.ExpressionExpected), asFirStatement);
                            } else {
                                arrayList2 = arrayList2;
                            }
                        } else {
                            arrayList2 = arrayList2;
                            if (asFirStatement == null || (source = asFirStatement.getSource()) == null || (firSourceElement$default = KtSourceElementKt.realElement(source)) == null) {
                                firSourceElement$default = AbstractRawFirBuilder.toFirSourceElement$default(lightTreeRawFirExpressionBuilder, lighterASTNode, null, 1, null);
                            }
                            firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default, new ConeSimpleDiagnostic("Initializer required for destructuring declaration", DiagnosticKind.ExpressionExpected), asFirStatement);
                        }
                        if (firExpressionBuildErrorExpression == null) {
                            x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.expressions.FirExpression");
                            return null;
                        }
                        objectRef.element = firExpressionBuildErrorExpression;
                    }
                    arrayList2 = arrayList2;
                }
            }
            i++;
            arrayList2 = arrayList2;
            z2 = true;
        }
        ArrayList arrayList3 = arrayList2;
        if (z4) {
            z = false;
        } else {
            if (!arrayList3.isEmpty()) {
                Iterator it = arrayList3.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (((DestructuringEntry) it.next()).getIsFullForm()) {
                        }
                    } else if (getNameBasedDestructuringShortForm()) {
                        z = false;
                    }
                }
            } else if (getNameBasedDestructuringShortForm()) {
                z = false;
            }
            z = true;
        }
        FirExpression firExpressionBuildErrorExpression$default = (FirExpression) objectRef.element;
        if (firExpressionBuildErrorExpression$default == null) {
            firExpressionBuildErrorExpression$default = FirExpressionUtilKt.buildErrorExpression$default(AbstractRawFirBuilder.toFirSourceElement$default(this, destructingDeclaration, null, 1, null), new ConeSyntaxDiagnostic("Initializer required for destructuring declaration"), null, 4, null);
        }
        return new DestructuringDeclaration(z3, z, arrayList3, firExpressionBuildErrorExpression$default, firSourceElement$default3, arrayList);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
    public final FirFile convertFile(LighterASTNode file, KtSourceFile sourceFile, KtSourceFileLinesMapping linesMapping) throws Exception {
        LighterASTNode lighterASTNode;
        file.getClass();
        sourceFile.getClass();
        linesMapping.getClass();
        if (!Intrinsics.areEqual(file.getTokenType(), KtNodeTypes.KT_FILE)) {
            throw new Exception();
        }
        FirFileSymbol firFileSymbol = new FirFileSymbol();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        ArrayList arrayList5 = new ArrayList();
        getContext().setPackageFqName(FqName.ROOT);
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        LighterASTNode[] childrenAsArray = getChildrenAsArray(file);
        int length = childrenAsArray.length;
        for (int i = 0; i < length && (lighterASTNode = childrenAsArray[i]) != null; i++) {
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType())) {
                IElementType tokenType = lighterASTNode.getTokenType();
                if (Intrinsics.areEqual(tokenType, KtNodeTypes.FILE_ANNOTATION_LIST)) {
                    getContext().pushContainerSymbol(firFileSymbol);
                    try {
                        convertAnnotationsOnlyTo(lighterASTNode, arrayList);
                        Unit unit = Unit.INSTANCE;
                        getContext().popContainerSymbol(firFileSymbol);
                    } catch (Throwable th) {
                        getContext().popContainerSymbol(firFileSymbol);
                        throw th;
                    }
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.PACKAGE_DIRECTIVE)) {
                    FirPackageDirective firPackageDirectiveConvertPackageDirective = convertPackageDirective(lighterASTNode);
                    getContext().setPackageFqName(firPackageDirectiveConvertPackageDirective.getPackageFqName());
                    objectRef.element = firPackageDirectiveConvertPackageDirective;
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.IMPORT_LIST)) {
                    CollectionsKt.addAll(arrayList2, convertImportDirectives(lighterASTNode));
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.CLASS) || Intrinsics.areEqual(tokenType, KtNodeTypes.OBJECT_DECLARATION)) {
                    arrayList3.add(convertClass(lighterASTNode));
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.FUN)) {
                    FirAnnotationContainer firAnnotationContainerConvertFunctionDeclaration = convertFunctionDeclaration(lighterASTNode);
                    firAnnotationContainerConvertFunctionDeclaration.getClass();
                    arrayList3.add((FirDeclaration) firAnnotationContainerConvertFunctionDeclaration);
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.PROPERTY)) {
                    arrayList3.add(convertPropertyDeclaration$default(this, lighterASTNode, null, 2, null));
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.TYPEALIAS)) {
                    arrayList3.add(convertTypeAlias(lighterASTNode));
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.DESTRUCTURING_DECLARATION)) {
                    arrayList3.add(buildErrorNonLocalDestructuringDeclaration(AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode, null, 1, null), buildFirDestructuringDeclarationInitializer(lighterASTNode)));
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.SCRIPT)) {
                    arrayList5.add(lighterASTNode);
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.MODIFIER_LIST)) {
                    arrayList4.add(lighterASTNode);
                }
            }
        }
        Iterator it = arrayList4.iterator();
        while (it.hasNext()) {
            arrayList3.add(buildErrorNonLocalDeclarationForDanglingModifierList((LighterASTNode) it.next()));
        }
        FirFileBuilder firFileBuilder = new FirFileBuilder();
        firFileBuilder.setSymbol(firFileSymbol);
        firFileBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(this, file, null, 1, null));
        firFileBuilder.setOrigin(FirDeclarationOrigin.Source.INSTANCE);
        firFileBuilder.setModuleData(getBaseModuleData());
        firFileBuilder.setName(sourceFile.getName());
        firFileBuilder.setSourceFile(sourceFile);
        firFileBuilder.setSourceFileLinesMapping(linesMapping);
        FirPackageDirective firPackageDirectiveBuild = (FirPackageDirective) objectRef.element;
        if (firPackageDirectiveBuild == null) {
            FirPackageDirectiveBuilder firPackageDirectiveBuilder = new FirPackageDirectiveBuilder();
            firPackageDirectiveBuilder.setPackageFqName(getContext().getPackageFqName());
            firPackageDirectiveBuild = firPackageDirectiveBuilder.build();
        }
        firFileBuilder.setPackageDirective(firPackageDirectiveBuild);
        CollectionsKt.addAll(firFileBuilder.getAnnotations(), arrayList);
        CollectionsKt.addAll(firFileBuilder.getImports(), arrayList2);
        Iterator it2 = arrayList5.iterator();
        while (it2.hasNext()) {
            arrayList3.add(convertScriptOrSnippets((LighterASTNode) it2.next(), sourceFile, firFileBuilder));
        }
        CollectionsKt.addAll(firFileBuilder.getDeclarations(), arrayList3);
        return firFileBuilder.mo288build();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r14v5 */
    /* JADX WARN: Type inference failed for: r14v7, types: [org.jetbrains.kotlin.fir.declarations.builder.FirFunctionBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirTypeParametersOwnerBuilder] */
    /* JADX WARN: Type inference failed for: r14v8 */
    /* JADX WARN: Type inference failed for: r4v29 */
    /* JADX WARN: Type inference failed for: r4v31 */
    /* JADX WARN: Type inference failed for: r52v0, types: [org.jetbrains.kotlin.fir.builder.AbstractRawFirBuilder, org.jetbrains.kotlin.fir.lightTree.converter.AbstractLightTreeRawFirBuilder, org.jetbrains.kotlin.fir.lightTree.converter.LightTreeRawFirDeclarationBuilder] */
    public final FirStatement convertFunctionDeclaration(LighterASTNode functionDeclaration) throws Throwable {
        String asText;
        Unit firFunctionTargets;
        String identifier;
        boolean z;
        Visibility visibility$default;
        ?? r14;
        FirFunctionTarget firFunctionTarget;
        Unit unit;
        FirAnonymousFunctionExpression firAnonymousFunctionExpressionMo288build;
        String identifier2;
        functionDeclaration.getClass();
        ArrayList arrayList = new ArrayList();
        KtToken ktToken = KtTokens.IDENTIFIER;
        ktToken.getClass();
        LighterASTNode childNodeByType = getChildNodeByType(functionDeclaration, ktToken);
        if (childNodeByType != null) {
            asText = getAsText(childNodeByType);
            Unit unit2 = Unit.INSTANCE;
        } else {
            asText = null;
        }
        boolean zIsCallableLocal = ConverterUtilKt.isCallableLocal(functionDeclaration, new Function1() { // from class: j19
            public final Object invoke(Object obj) {
                return LightTreeRawFirDeclarationBuilder.q(this.b, (LighterASTNode) obj);
            }
        });
        KtSourceElement ktSourceElement = (KtLightSourceElement) AbstractRawFirBuilder.toFirSourceElement$default(this, functionDeclaration, null, 1, null);
        boolean z2 = asText == null && zIsCallableLocal;
        Name nameNameAsSafeName$default = ConverterUtilKt.nameAsSafeName$default(asText, null, 1, null);
        Unit firAnonymousFunctionSymbol = z2 ? new FirAnonymousFunctionSymbol() : new FirNamedFunctionSymbol(callableIdForName(nameNameAsSafeName$default));
        boolean zIsDirectlyInsideCompanionBlock = isDirectlyInsideCompanionBlock();
        if (!zIsCallableLocal) {
            getContext().pushContainerSymbol(firAnonymousFunctionSymbol);
        }
        try {
            ModifierList modifierListConvertModifierList$default = null;
            FirTypeRef implicitType = null;
            LighterASTNode lighterASTNode = null;
            LighterASTNode lighterASTNode2 = null;
            LighterASTNode lighterASTNode3 = null;
            FirContractDescription firContractDescriptionObtainContractDescription = null;
            LighterASTNode lighterASTNode4 = null;
            boolean z3 = false;
            boolean z4 = false;
            LighterASTNode lighterASTNode5 = null;
            for (LighterASTNode lighterASTNode6 : getChildrenAsArray(functionDeclaration)) {
                if (lighterASTNode6 == null) {
                    break;
                }
                if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode6.getTokenType())) {
                    IElementType tokenType = lighterASTNode6.getTokenType();
                    if (Intrinsics.areEqual(tokenType, KtNodeTypes.MODIFIER_LIST)) {
                        modifierListConvertModifierList$default = convertModifierList$default(this, lighterASTNode6, false, 2, null);
                    } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.TYPE_PARAMETER_LIST)) {
                        lighterASTNode2 = lighterASTNode6;
                    } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.VALUE_PARAMETER_LIST)) {
                        lighterASTNode3 = lighterASTNode6;
                    } else if (Intrinsics.areEqual(tokenType, KtTokens.COLON)) {
                        z4 = true;
                    } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.TYPE_REFERENCE)) {
                        if (z4) {
                            implicitType = convertType(lighterASTNode6);
                        } else {
                            lighterASTNode = lighterASTNode6;
                        }
                    } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.TYPE_CONSTRAINT_LIST)) {
                        CollectionsKt.addAll(arrayList, convertTypeConstraints(lighterASTNode6));
                    } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.CONTRACT_EFFECT_LIST)) {
                        firContractDescriptionObtainContractDescription = obtainContractDescription(lighterASTNode6);
                    } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.BLOCK)) {
                        lighterASTNode5 = lighterASTNode6;
                    } else if (Intrinsics.areEqual(tokenType, KtTokens.EQ)) {
                        z3 = true;
                    } else if (ElementTypeUtils.INSTANCE.isExpression(lighterASTNode6)) {
                        lighterASTNode4 = lighterASTNode6;
                    }
                }
            }
            ModifierList modifierList = modifierListConvertModifierList$default == null ? new ModifierList(0L, 1, null) : modifierListConvertModifierList$default;
            if (implicitType == null) {
                implicitType = (lighterASTNode5 == null && z3) ? getImplicitType() : getImplicitUnitType();
            }
            FirTypeRef firTypeRef = implicitType;
            final LighterASTNode lighterASTNode7 = lighterASTNode;
            Function0 function0 = lighterASTNode7 != null ? new Function0() { // from class: k19
                public final Object invoke() {
                    return this.b.convertType(lighterASTNode7);
                }
            } : null;
            if (z2) {
                FirAnonymousFunctionBuilder firAnonymousFunctionBuilder = new FirAnonymousFunctionBuilder();
                firAnonymousFunctionBuilder.setSource(ktSourceElement);
                firAnonymousFunctionBuilder.setReceiverParameter(function0 != null ? ConversionUtilsKt.createReceiverParameter(this, function0, getBaseModuleData(), firAnonymousFunctionSymbol) : null);
                firAnonymousFunctionBuilder.setSymbol((FirAnonymousFunctionSymbol) firAnonymousFunctionSymbol);
                firAnonymousFunctionBuilder.setLambda(false);
                firAnonymousFunctionBuilder.setHasExplicitParameterList(true);
                firAnonymousFunctionBuilder.setLabel(getContext().getLastLabel(functionDeclaration));
                FirLabel label = firAnonymousFunctionBuilder.getLabel();
                if (label == null || (identifier2 = label.getName()) == null) {
                    Name name = (Name) CollectionsKt.lastOrNull(getContext().getCalleeNamesForLambda());
                    identifier2 = name != null ? name.getIdentifier() : null;
                }
                firFunctionTarget = new FirFunctionTarget(identifier2, false);
                boolean z5 = modifierList.hasExpect() || getContext().getContainerIsExpect();
                boolean zHasActual = modifierList.hasActual();
                boolean zHasOverride = modifierList.hasOverride();
                boolean zHasOperator = modifierList.hasOperator();
                boolean zHasInfix = modifierList.hasInfix();
                boolean zHasInline = modifierList.hasInline();
                boolean zHasTailrec = modifierList.hasTailrec();
                boolean zHasExternal = modifierList.hasExternal();
                boolean zHasSuspend = modifierList.hasSuspend();
                if (z5 || zHasActual || zHasOverride || zHasOperator || zHasInfix || zHasInline || zHasTailrec || zHasExternal || zHasSuspend) {
                    FirResolvedDeclarationStatus default_status_for_statusless_declarations = FirResolvedDeclarationStatusImpl.INSTANCE.getDEFAULT_STATUS_FOR_STATUSLESS_DECLARATIONS();
                    firAnonymousFunctionBuilder.setStatus(UtilsKt.copy(default_status_for_statusless_declarations, (8388575 & 1) != 0 ? default_status_for_statusless_declarations.getVisibility() : null, (8388575 & 2) != 0 ? default_status_for_statusless_declarations.getModality() : null, (8388575 & 4) != 0 ? default_status_for_statusless_declarations.isExpect() : z5, (8388575 & 8) != 0 ? default_status_for_statusless_declarations.isActual() : zHasActual, (8388575 & 16) != 0 ? default_status_for_statusless_declarations.isOverride() : zHasOverride, (8388575 & 32) != 0 ? default_status_for_statusless_declarations.isOperator() : zHasOperator, (8388575 & 64) != 0 ? default_status_for_statusless_declarations.isInfix() : zHasInfix, (8388575 & 128) != 0 ? default_status_for_statusless_declarations.isInline() : zHasInline, (8388575 & 256) != 0 ? default_status_for_statusless_declarations.isValue() : false, (8388575 & 512) != 0 ? default_status_for_statusless_declarations.isTailRec() : zHasTailrec, (8388575 & 1024) != 0 ? default_status_for_statusless_declarations.isExternal() : zHasExternal, (8388575 & 2048) != 0 ? default_status_for_statusless_declarations.isConst() : false, (8388575 & 4096) != 0 ? default_status_for_statusless_declarations.isLateInit() : false, (8388575 & 8192) != 0 ? default_status_for_statusless_declarations.isInner() : false, (8388575 & 16384) != 0 ? default_status_for_statusless_declarations.isCompanion() : false, (8388575 & 32768) != 0 ? default_status_for_statusless_declarations.isData() : false, (8388575 & 65536) != 0 ? default_status_for_statusless_declarations.isSuspend() : zHasSuspend, (8388575 & 131072) != 0 ? default_status_for_statusless_declarations.isStatic() : false, (8388575 & 262144) != 0 ? default_status_for_statusless_declarations.isFromSealedClass() : false, (8388575 & 524288) != 0 ? default_status_for_statusless_declarations.isFromEnumClass() : false, (8388575 & 1048576) != 0 ? default_status_for_statusless_declarations.isFun() : false, (8388575 & 2097152) != 0 ? default_status_for_statusless_declarations.getHasStableParameterNames() : false, (8388575 & 4194304) != 0 ? default_status_for_statusless_declarations.getReturnValueStatus() : null));
                }
                r14 = firAnonymousFunctionBuilder;
                z2 = z2;
            } else {
                FirLabel lastLabel = getContext().getLastLabel(functionDeclaration);
                if (lastLabel == null || (identifier = lastLabel.getName()) == null) {
                    identifier = !nameNameAsSafeName$default.isSpecial() ? nameNameAsSafeName$default.getIdentifier() : null;
                }
                FirFunctionTarget firFunctionTarget2 = new FirFunctionTarget(identifier, false);
                FirNamedFunctionBuilder firNamedFunctionBuilder = new FirNamedFunctionBuilder();
                firNamedFunctionBuilder.setSource(ktSourceElement);
                firNamedFunctionBuilder.setReceiverParameter(function0 != null ? ConversionUtilsKt.createReceiverParameter(this, function0, getBaseModuleData(), firAnonymousFunctionSymbol) : null);
                firNamedFunctionBuilder.setName(nameNameAsSafeName$default);
                firNamedFunctionBuilder.setLocal(getContext().getInLocalContext());
                if (zIsCallableLocal) {
                    visibility$default = Visibilities.Local.INSTANCE;
                    z = false;
                } else {
                    z = false;
                    visibility$default = ModifierList.getVisibility$default(modifierList, false, 1, null);
                }
                FirDeclarationStatusImpl firDeclarationStatusImpl = new FirDeclarationStatusImpl(visibility$default, modifierList.getModality(z));
                firDeclarationStatusImpl.setExpect((modifierList.hasExpect() || getContext().getContainerIsExpect()) ? true : z);
                firDeclarationStatusImpl.setActual(modifierList.hasActual());
                firDeclarationStatusImpl.setOverride(modifierList.hasOverride());
                firDeclarationStatusImpl.setOperator(modifierList.hasOperator());
                firDeclarationStatusImpl.setInfix(modifierList.hasInfix());
                firDeclarationStatusImpl.setInline(modifierList.hasInline());
                firDeclarationStatusImpl.setTailRec(modifierList.hasTailrec());
                firDeclarationStatusImpl.setExternal(modifierList.hasExternal());
                firDeclarationStatusImpl.setSuspend(modifierList.hasSuspend());
                firDeclarationStatusImpl.setStatic((modifierList.hasCompanion() || zIsDirectlyInsideCompanionBlock) ? true : z);
                firNamedFunctionBuilder.setStatus(firDeclarationStatusImpl);
                firNamedFunctionBuilder.setSymbol((FirNamedFunctionSymbol) firAnonymousFunctionSymbol);
                firNamedFunctionBuilder.setDispatchReceiverType((zIsCallableLocal || zIsDirectlyInsideCompanionBlock) ? z : true ? currentDispatchReceiverType() : null);
                r14 = firNamedFunctionBuilder;
                firFunctionTarget = firFunctionTarget2;
            }
            ArrayList arrayList2 = new ArrayList();
            LighterASTNode lighterASTNode8 = lighterASTNode2;
            if (lighterASTNode8 != null) {
                CollectionsKt.addAll(arrayList2, convertTypeParameters(lighterASTNode8, arrayList, firAnonymousFunctionSymbol));
                Unit unit3 = Unit.INSTANCE;
            }
            r14.setModuleData(getBaseModuleData());
            r14.setOrigin(FirDeclarationOrigin.Source.INSTANCE);
            r14.setReturnTypeRef(firTypeRef);
            firFunctionTargets = getContext().getFirFunctionTargets();
            firFunctionTargets.add(firFunctionTarget);
            if (modifierListConvertModifierList$default != null) {
                convertAnnotationsTo(modifierListConvertModifierList$default, r14.getAnnotations());
                firFunctionTargets = Unit.INSTANCE;
            }
            try {
                CollectionsKt.addAll(r14.getTypeParameters(), arrayList2);
                addCapturedTypeParameters(true, ktSourceElement, r14.getTypeParameters());
                try {
                    addContextParameters(r14.getContextParameters(), modifierListConvertModifierList$default != null ? modifierListConvertModifierList$default.getContextLists() : null, firAnonymousFunctionSymbol);
                    if (lighterASTNode3 != null) {
                        List<FirValueParameter> valueParameters = r14.getValueParameters();
                        unit = firAnonymousFunctionSymbol;
                        try {
                            List listConvertValueParameters$default = convertValueParameters$default(this, lighterASTNode3, unit, z2 ? AbstractRawFirBuilder.ValueParameterDeclaration.LAMBDA : AbstractRawFirBuilder.ValueParameterDeclaration.FUNCTION, null, 8, null);
                            ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(listConvertValueParameters$default, 10));
                            Iterator it = listConvertValueParameters$default.iterator();
                            while (it.hasNext()) {
                                arrayList3.add(((ValueParameter) it.next()).getFirValueParameter());
                            }
                            CollectionsKt.addAll(valueParameters, arrayList3);
                            Unit unit4 = Unit.INSTANCE;
                        } catch (Throwable th) {
                            th = th;
                            getContext().popFirTypeParameters();
                            throw th;
                        }
                    } else {
                        unit = firAnonymousFunctionSymbol;
                    }
                    boolean z6 = firContractDescriptionObtainContractDescription == null;
                    boolean z7 = r14.getStatus().isInline() || (r14.getReturnTypeRef() instanceof FirImplicitTypeRef);
                    boolean forceKeepingTheBodyInHeaderMode = getContext().getForceKeepingTheBodyInHeaderMode();
                    getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode || z7);
                    boolean inLocalContext = getContext().getInLocalContext();
                    getContext().setInLocalContext(true);
                    FqName classNameBeforeLocalContext = getContext().getClassNameBeforeLocalContext();
                    if (!inLocalContext) {
                        getContext().setClassNameBeforeLocalContext(getContext().getClassName());
                    }
                    FqName className = getContext().getClassName();
                    try {
                        getContext().setClassName(FqName.ROOT);
                        try {
                            Pair<FirBlock, FirContractDescription> pairConvertFunctionBody = convertFunctionBody(lighterASTNode5, lighterASTNode4, z6);
                            getContext().setClassNameBeforeLocalContext(classNameBeforeLocalContext);
                            getContext().setInLocalContext(inLocalContext);
                            getContext().setClassName(className);
                            getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
                            r14.setBody((FirBlock) pairConvertFunctionBody.getFirst());
                            if (firContractDescriptionObtainContractDescription == null) {
                                firContractDescriptionObtainContractDescription = (FirContractDescription) pairConvertFunctionBody.getSecond();
                            }
                            FirContractDescription firContractDescription = firContractDescriptionObtainContractDescription;
                            if (firContractDescription != null) {
                                if (r14 instanceof FirNamedFunctionBuilder) {
                                    ((FirNamedFunctionBuilder) r14).setContractDescription(firContractDescription);
                                } else if (r14 instanceof FirAnonymousFunctionBuilder) {
                                    ((FirAnonymousFunctionBuilder) r14).setContractDescription(firContractDescription);
                                }
                                Unit unit5 = Unit.INSTANCE;
                            }
                            getContext().popFirTypeParameters();
                            removeLast(getContext().getFirFunctionTargets());
                            FirFunction firFunctionMo288build = r14.mo288build();
                            firFunctionTarget.bind(firFunctionMo288build);
                            fillDanglingConstraintsTo(arrayList2, arrayList, firFunctionMo288build);
                            if (!zIsCallableLocal && zIsDirectlyInsideCompanionBlock) {
                                initContainingClassAttr(firFunctionMo288build);
                            }
                            boolean z8 = firFunctionMo288build instanceof FirAnonymousFunction;
                            FirStatement firStatement = firFunctionMo288build;
                            if (z8) {
                                FirAnonymousFunctionExpressionBuilder firAnonymousFunctionExpressionBuilder = new FirAnonymousFunctionExpressionBuilder();
                                firAnonymousFunctionExpressionBuilder.setSource(ktSourceElement);
                                firAnonymousFunctionExpressionBuilder.setAnonymousFunction((FirAnonymousFunction) firFunctionMo288build);
                                firAnonymousFunctionExpressionMo288build = firAnonymousFunctionExpressionBuilder.mo288build();
                            }
                            if (!zIsCallableLocal) {
                                firStatement = firAnonymousFunctionExpressionMo288build;
                                getContext().popContainerSymbol(unit);
                            }
                            firStatement = firAnonymousFunctionExpressionMo288build;
                            return firStatement;
                        } catch (Throwable th2) {
                            getContext().setClassNameBeforeLocalContext(classNameBeforeLocalContext);
                            getContext().setInLocalContext(inLocalContext);
                            getContext().setClassName(className);
                            getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
                            throw th2;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        getContext().popFirTypeParameters();
                        throw th;
                    }
                } catch (Throwable th4) {
                    th = th4;
                }
            } catch (Throwable th5) {
                th = th5;
                if (!z2) {
                    getContext().popContainerSymbol(firFunctionTargets);
                }
                throw th;
            }
        } catch (Throwable th6) {
            th = th6;
            firFunctionTargets = firAnonymousFunctionSymbol;
            z2 = zIsCallableLocal;
        }
    }

    /* JADX WARN: Code duplicated, block: B:100:0x03ce A[Catch: all -> 0x03a5, TryCatch #2 {all -> 0x03a5, blocks: (B:90:0x037e, B:91:0x03a4, B:96:0x03b0, B:98:0x03c0, B:100:0x03ce, B:101:0x03e5, B:102:0x03ff, B:103:0x0400, B:104:0x0424), top: B:113:0x0054 }] */
    /* JADX WARN: Code duplicated, block: B:103:0x0400 A[Catch: all -> 0x03a5, TryCatch #2 {all -> 0x03a5, blocks: (B:90:0x037e, B:91:0x03a4, B:96:0x03b0, B:98:0x03c0, B:100:0x03ce, B:101:0x03e5, B:102:0x03ff, B:103:0x0400, B:104:0x0424), top: B:113:0x0054 }] */
    /* JADX WARN: Code duplicated, block: B:98:0x03c0 A[Catch: all -> 0x03a5, TryCatch #2 {all -> 0x03a5, blocks: (B:90:0x037e, B:91:0x03a4, B:96:0x03b0, B:98:0x03c0, B:100:0x03ce, B:101:0x03e5, B:102:0x03ff, B:103:0x0400, B:104:0x0424), top: B:113:0x0054 }] */
    /* JADX WARN: Instruction removed from duplicated block: B:103:0x0400, please report this as an issue */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v20 */
    public final FirAnonymousObjectExpression convertObjectLiteral(LighterASTNode objectLiteral) throws Throwable {
        boolean z;
        boolean z2;
        String str;
        boolean z3;
        int i;
        FqName fqName;
        objectLiteral.getClass();
        Name name = SpecialNames.ANONYMOUS;
        boolean forceKeepingTheBodyInHeaderMode = getContext().getForceKeepingTheBodyInHeaderMode();
        getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
        boolean inLocalContext = getContext().getInLocalContext();
        FqName fqName2 = 1;
        getContext().setInLocalContext(true);
        FqName classNameBeforeLocalContext = getContext().getClassNameBeforeLocalContext();
        if (!inLocalContext) {
            getContext().setClassNameBeforeLocalContext(getContext().getClassName());
        }
        FqName className = getContext().getClassName();
        Context<LighterASTNode> context = getContext();
        FqName fqName3 = FqName.ROOT;
        context.setClassName(fqName3);
        try {
            try {
                getContext().setClassName(getContext().getClassName().child(name));
                boolean containerIsExpect = getContext().getContainerIsExpect();
                getContext().setContainerIsExpect(containerIsExpect);
                int size = getContext().getDispatchReceiverTypesStack().size();
                try {
                    Ref.ObjectRef objectRef = new Ref.ObjectRef();
                    FirAnonymousObjectExpressionBuilder firAnonymousObjectExpressionBuilder = new FirAnonymousObjectExpressionBuilder();
                    firAnonymousObjectExpressionBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(this, objectLiteral, null, 1, null));
                    IElementType iElementType = KtNodeTypes.OBJECT_DECLARATION;
                    iElementType.getClass();
                    LighterASTNode lighterASTNode = (LighterASTNode) CollectionsKt.first(getChildNodesByType(objectLiteral, iElementType));
                    ArrayList arrayList = new ArrayList();
                    Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                    LighterASTNode[] childrenAsArray = getChildrenAsArray(lighterASTNode);
                    int length = childrenAsArray.length;
                    ModifierList modifierList = null;
                    KtLightSourceElement ktLightSourceElement = null;
                    LighterASTNode lighterASTNode2 = null;
                    int i2 = 0;
                    FirTypeRef implicitAnyType = null;
                    List<DelegatedConstructorWrapper> listEmptyList = null;
                    LighterASTNode lighterASTNode3 = null;
                    while (true) {
                        if (i2 >= length) {
                            fqName = className;
                            break;
                        }
                        fqName = className;
                        try {
                            LighterASTNode lighterASTNode4 = childrenAsArray[i2];
                            if (lighterASTNode4 == null) {
                                break;
                            }
                            int i3 = length;
                            KtLightSourceElement ktLightSourceElement2 = ktLightSourceElement;
                            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode4.getTokenType())) {
                                IElementType tokenType = lighterASTNode4.getTokenType();
                                if (Intrinsics.areEqual(tokenType, KtNodeTypes.MODIFIER_LIST)) {
                                    modifierList = convertModifierList$default(this, lighterASTNode4, false, 2, null);
                                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.PRIMARY_CONSTRUCTOR)) {
                                    lighterASTNode3 = lighterASTNode4;
                                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.SUPER_TYPE_LIST)) {
                                    DelegationSpecifiers delegationSpecifiersConvertDelegationSpecifiers = convertDelegationSpecifiers(lighterASTNode4);
                                    DelegatedConstructorWrapper delegatedConstructorWrapper = (DelegatedConstructorWrapper) CollectionsKt.lastOrNull(delegationSpecifiersConvertDelegationSpecifiers.getSuperTypeCalls());
                                    FirTypeRef delegatedSuperTypeRef = delegatedConstructorWrapper != null ? delegatedConstructorWrapper.getDelegatedSuperTypeRef() : null;
                                    CollectionsKt.addAll(arrayList, delegationSpecifiersConvertDelegationSpecifiers.getSuperTypesRef());
                                    DelegatedConstructorWrapper delegatedConstructorWrapper2 = (DelegatedConstructorWrapper) CollectionsKt.lastOrNull(delegationSpecifiersConvertDelegationSpecifiers.getSuperTypeCalls());
                                    KtLightSourceElement source = delegatedConstructorWrapper2 != null ? delegatedConstructorWrapper2.getSource() : null;
                                    Collection<FirFieldSymbol> collectionValues = delegationSpecifiersConvertDelegationSpecifiers.getDelegateFieldsMap().values();
                                    implicitAnyType = delegatedSuperTypeRef;
                                    ktLightSourceElement2 = source;
                                    ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(collectionValues, 10));
                                    Iterator<T> it = collectionValues.iterator();
                                    while (it.hasNext()) {
                                        arrayList2.add((FirField) ((FirFieldSymbol) it.next()).getFir());
                                    }
                                    objectRef2.element = arrayList2;
                                    Map<Integer, FirFieldSymbol> delegateFieldsMap = delegationSpecifiersConvertDelegationSpecifiers.getDelegateFieldsMap();
                                    if (delegateFieldsMap.isEmpty()) {
                                        delegateFieldsMap = null;
                                    }
                                    objectRef.element = delegateFieldsMap;
                                    listEmptyList = delegationSpecifiersConvertDelegationSpecifiers.getSuperTypeCalls();
                                    Unit unit = Unit.INSTANCE;
                                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.CLASS_BODY)) {
                                    lighterASTNode2 = lighterASTNode4;
                                }
                            }
                            ktLightSourceElement = ktLightSourceElement2;
                            i2++;
                            className = fqName;
                            length = i3;
                        } catch (Throwable th) {
                            th = th;
                            str = "Wrong number of ";
                            z3 = containerIsExpect;
                            i = size;
                            if (getContext().getDispatchReceiverTypesStack().size() <= i + 1) {
                                throw new IllegalArgumentException((str + getContext().getDispatchReceiverTypesStack().size()).toString());
                            }
                            if (getContext().getDispatchReceiverTypesStack().size() > i) {
                                getContext().getDispatchReceiverTypesStack().remove(CollectionsKt.getLastIndex(getContext().getDispatchReceiverTypesStack()));
                            }
                            getContext().setClassName(getContext().getClassName().parent());
                            getContext().setContainerIsExpect(z3);
                            throw th;
                        }
                    }
                    KtLightSourceElement ktLightSourceElement3 = ktLightSourceElement;
                    CompanionBlockCollector companionBlockCollector = new CompanionBlockCollector();
                    FirAnonymousObjectBuilder firAnonymousObjectBuilder = new FirAnonymousObjectBuilder();
                    firAnonymousObjectBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode, null, 1, null));
                    firAnonymousObjectBuilder.setOrigin(FirDeclarationOrigin.Source.INSTANCE);
                    firAnonymousObjectBuilder.setModuleData(getBaseModuleData());
                    firAnonymousObjectBuilder.setClassKind(ClassKind.CLASS);
                    firAnonymousObjectBuilder.setScopeProvider(this.baseScopeProvider);
                    firAnonymousObjectBuilder.setSymbol(new FirAnonymousObjectSymbol(getContext().getPackageFqName()));
                    firAnonymousObjectBuilder.setStatus(new FirDeclarationStatusImpl(Visibilities.Local.INSTANCE, Modality.FINAL));
                    getContext().appendOuterTypeParameters(false, firAnonymousObjectBuilder.getTypeParameters());
                    FirResolvedTypeRef delegatedSelfType = toDelegatedSelfType(lighterASTNode, firAnonymousObjectBuilder);
                    registerSelfType(delegatedSelfType);
                    if (arrayList.isEmpty()) {
                        arrayList.add(getImplicitAnyType());
                        implicitAnyType = getImplicitAnyType();
                        Unit unit2 = Unit.INSTANCE;
                    }
                    if (implicitAnyType == null) {
                        implicitAnyType = FirImplicitTypeRefImplWithoutSource.INSTANCE;
                    }
                    FirTypeRef firTypeRef = implicitAnyType;
                    if (modifierList != null) {
                        convertAnnotationsTo(modifierList, firAnonymousObjectBuilder.getAnnotations());
                        Unit unit3 = Unit.INSTANCE;
                    }
                    CollectionsKt.addAll(firAnonymousObjectBuilder.getSuperTypeRefs(), arrayList);
                    if (modifierList == null) {
                        modifierList = new ModifierList(0L, 1, null);
                    }
                    ModifierList modifierList2 = modifierList;
                    ClassKind classKind = ClassKind.OBJECT;
                    IElementType iElementType2 = KtNodeTypes.SECONDARY_CONSTRUCTOR;
                    iElementType2.getClass();
                    boolean z4 = !getChildNodesByType(lighterASTNode2, iElementType2).isEmpty();
                    if (listEmptyList == null) {
                        listEmptyList = CollectionsKt.emptyList();
                    }
                    ClassWrapper classWrapper = new ClassWrapper(modifierList2, classKind, firAnonymousObjectBuilder, z4, false, delegatedSelfType, firTypeRef, listEmptyList, companionBlockCollector);
                    LighterASTNode lighterASTNode5 = lighterASTNode2;
                    i = size;
                    try {
                        PrimaryConstructor primaryConstructorConvertPrimaryConstructor$default = convertPrimaryConstructor$default(this, lighterASTNode3, delegatedSelfType.getSource(), classWrapper, ktLightSourceElement3, false, false, false, false, 208, null);
                        if (primaryConstructorConvertPrimaryConstructor$default != null) {
                            firAnonymousObjectBuilder.getDeclarations().add(primaryConstructorConvertPrimaryConstructor$default.getFirConstructor());
                            Unit unit4 = Unit.INSTANCE;
                        }
                        List list = (List) objectRef2.element;
                        if (list != null) {
                            CollectionsKt.addAll(firAnonymousObjectBuilder.getDeclarations(), list);
                            Unit unit5 = Unit.INSTANCE;
                        }
                        if (lighterASTNode5 != null) {
                            CollectionsKt.addAll(firAnonymousObjectBuilder.getDeclarations(), convertClassBody(lighterASTNode5, classWrapper));
                            Unit unit6 = Unit.INSTANCE;
                        }
                        FirAnonymousObject firAnonymousObjectMo288build = firAnonymousObjectBuilder.mo288build();
                        DelegateFieldsMapKt.setDelegateFieldsMap(firAnonymousObjectMo288build, (Map) objectRef.element);
                        CompanionBlockInfo companionBlockInfoOrNull = companionBlockCollector.toCompanionBlockInfoOrNull();
                        if (companionBlockInfoOrNull != null) {
                            ClassMembersKt.setCompanionBlocks(firAnonymousObjectMo288build, companionBlockInfoOrNull);
                            Unit unit7 = Unit.INSTANCE;
                        }
                        firAnonymousObjectExpressionBuilder.setAnonymousObject(firAnonymousObjectMo288build);
                        FirAnonymousObjectExpression firAnonymousObjectExpressionMo288build = firAnonymousObjectExpressionBuilder.mo288build();
                        try {
                            if (getContext().getDispatchReceiverTypesStack().size() > i + 1) {
                                throw new IllegalArgumentException(("Wrong number of " + getContext().getDispatchReceiverTypesStack().size()).toString());
                            }
                            if (getContext().getDispatchReceiverTypesStack().size() > i) {
                                getContext().getDispatchReceiverTypesStack().remove(CollectionsKt.getLastIndex(getContext().getDispatchReceiverTypesStack()));
                            }
                            getContext().setClassName(getContext().getClassName().parent());
                            getContext().setContainerIsExpect(containerIsExpect);
                            getContext().setClassNameBeforeLocalContext(classNameBeforeLocalContext);
                            getContext().setInLocalContext(inLocalContext);
                            getContext().setClassName(fqName);
                            getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
                            return firAnonymousObjectExpressionMo288build;
                        } catch (Throwable th2) {
                            th = th2;
                            z = forceKeepingTheBodyInHeaderMode;
                            z2 = inLocalContext;
                            fqName2 = classNameBeforeLocalContext;
                            fqName3 = fqName;
                            getContext().setClassNameBeforeLocalContext(fqName2);
                            getContext().setInLocalContext(z2);
                            getContext().setClassName(fqName3);
                            getContext().setForceKeepingTheBodyInHeaderMode(z);
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        str = "Wrong number of ";
                        z3 = containerIsExpect;
                        if (getContext().getDispatchReceiverTypesStack().size() <= i + 1) {
                            throw new IllegalArgumentException((str + getContext().getDispatchReceiverTypesStack().size()).toString());
                        }
                        if (getContext().getDispatchReceiverTypesStack().size() > i) {
                            getContext().getDispatchReceiverTypesStack().remove(CollectionsKt.getLastIndex(getContext().getDispatchReceiverTypesStack()));
                        }
                        getContext().setClassName(getContext().getClassName().parent());
                        getContext().setContainerIsExpect(z3);
                        throw th;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    str = "Wrong number of ";
                    z3 = containerIsExpect;
                    i = size;
                }
            } catch (Throwable th5) {
                th = th5;
                z = name;
                z2 = context;
            }
        } catch (Throwable th6) {
            th = th6;
            fqName3 = className;
            z = forceKeepingTheBodyInHeaderMode;
            z2 = inLocalContext;
            fqName2 = classNameBeforeLocalContext;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
    /*  JADX ERROR: Types fix failed
        jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached with updateSeq = 19621. Try increasing type updates limit count.
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:99)
        */
    public final org.jetbrains.kotlin.fir.declarations.FirProperty convertPropertyDeclaration(com.intellij.lang.LighterASTNode r53, org.jetbrains.kotlin.fir.lightTree.fir.ClassWrapper r54) throws org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments {
        /*
            Method dump skipped, instruction units count: 1962
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.kotlin.fir.lightTree.converter.LightTreeRawFirDeclarationBuilder.convertPropertyDeclaration(com.intellij.lang.LighterASTNode, org.jetbrains.kotlin.fir.lightTree.fir.ClassWrapper):org.jetbrains.kotlin.fir.declarations.FirProperty");
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
    /* JADX INFO: renamed from: convertReplSnippet, reason: avoid collision after fix types in other method */
    public FirReplSnippet convertReplSnippet2(LighterASTNode script, KtSourceElement scriptSource, String fileName, Function1<? super FirReplSnippetBuilder, Unit> snippetSetup, Function1<? super FirBlockBuilder, Unit> functionBodySetup, Function1<? super List<FirElement>, Unit> statementsSetup) throws NotImplementedError {
        script.getClass();
        scriptSource.getClass();
        fileName.getClass();
        snippetSetup.getClass();
        functionBodySetup.getClass();
        statementsSetup.getClass();
        throw new NotImplementedError("An operation is not implemented: KT-77583");
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
    /* JADX INFO: renamed from: convertScript, reason: avoid collision after fix types in other method */
    public FirScript convertScript2(LighterASTNode script, KtSourceElement scriptSource, String fileName, Function1<? super FirScriptBuilder, Unit> setup) throws KotlinIllegalStateExceptionWithAttachments, UninitializedPropertyAccessException {
        script.getClass();
        scriptSource.getClass();
        fileName.getClass();
        setup.getClass();
        Name nameFirScriptName = AbstractRawFirBuilder.INSTANCE.firScriptName(fileName);
        FirScriptSymbol firScriptSymbol = new FirScriptSymbol(getContext().getPackageFqName().child(nameFirScriptName));
        FirScriptBuilder firScriptBuilder = new FirScriptBuilder();
        firScriptBuilder.setSource(scriptSource);
        firScriptBuilder.setModuleData(getBaseModuleData());
        firScriptBuilder.setOrigin(FirDeclarationOrigin.Source.INSTANCE);
        firScriptBuilder.setName(nameFirScriptName);
        firScriptBuilder.setSymbol(firScriptSymbol);
        IElementType iElementType = KtNodeTypes.BLOCK;
        iElementType.getClass();
        LighterASTNode childNodeByType = getChildNodeByType(script, iElementType);
        LighterASTNode[] childrenAsArray = childNodeByType != null ? getChildrenAsArray(childNodeByType) : null;
        if (childrenAsArray == null) {
            childrenAsArray = new LighterASTNode[0];
        }
        List listFilterNotNull = ArraysKt.filterNotNull(childrenAsArray);
        ArrayList arrayList = new ArrayList();
        for (Object obj : listFilterNotNull) {
            if (this.SCRIPT_DECLARATION_TOKENS.contains(((LighterASTNode) obj).getTokenType())) {
                arrayList.add(obj);
            }
        }
        ListIterator listIterator = arrayList.listIterator();
        FirScriptSymbol symbol = firScriptBuilder.getSymbol();
        if (getContext().getContainingScriptSymbol() != null) {
            w01.a("Nested scripts are not supported");
            return null;
        }
        getContext().setContainingScriptSymbol(symbol);
        getContext().pushContainerSymbol(symbol);
        try {
            List<LighterASTNode> arrayList2 = new ArrayList<>();
            while (listIterator.hasNext()) {
                LighterASTNode lighterASTNode = (LighterASTNode) listIterator.next();
                boolean z = !listIterator.hasNext();
                IElementType tokenType = lighterASTNode.getTokenType();
                if (Intrinsics.areEqual(tokenType, KtNodeTypes.SCRIPT_INITIALIZER)) {
                    FirAnonymousInitializer firAnonymousInitializerConvertScriptInitializer = convertScriptInitializer(lighterASTNode, firScriptSymbol, z);
                    DeclarationAttributesKt.setScriptTopLevelDeclaration(firAnonymousInitializerConvertScriptInitializer, Boolean.TRUE);
                    firScriptBuilder.getDeclarations().add(firAnonymousInitializerConvertScriptInitializer);
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.DESTRUCTURING_DECLARATION)) {
                    DestructuringDeclaration destructuringDeclarationConvertDestructingDeclaration$org_jetbrains_kotlin_fir_light_tree2fir = convertDestructingDeclaration$org_jetbrains_kotlin_fir_light_tree2fir(lighterASTNode);
                    FirModuleData baseModuleData = getBaseModuleData();
                    KtSourceElement firSourceElement$default = AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode, null, 1, null);
                    Name nameSpecial = Name.special("<destruct>");
                    nameSpecial.getClass();
                    final FirProperty firPropertyGenerateTemporaryVariable$default = FirGenerationKt.generateTemporaryVariable$default(baseModuleData, firSourceElement$default, nameSpecial, destructuringDeclarationConvertDestructingDeclaration$org_jetbrains_kotlin_fir_light_tree2fir.getInitializer(), null, destructuringDeclarationConvertDestructingDeclaration$org_jetbrains_kotlin_fir_light_tree2fir.getAnnotations(), FirDeclarationOrigin.Synthetic.ScriptTopLevelDestructuringDeclarationContainer.INSTANCE, 16, null);
                    Boolean bool = Boolean.TRUE;
                    DeclarationAttributesKt.setScriptTopLevelDeclaration(firPropertyGenerateTemporaryVariable$default, bool);
                    DestructuringDeclarationAttributesKt.setDestructuringDeclarationContainerVariable(firPropertyGenerateTemporaryVariable$default, bool);
                    DestructuringDeclarationKt.addDestructuringStatements(this, firScriptBuilder.getDeclarations(), getBaseModuleData(), destructuringDeclarationConvertDestructingDeclaration$org_jetbrains_kotlin_fir_light_tree2fir, firPropertyGenerateTemporaryVariable$default, true, false, new Function1() { // from class: h19
                        public final Object invoke(Object obj2) {
                            return LightTreeRawFirDeclarationBuilder.convertScript$lambda$0$1$1(this.b, firPropertyGenerateTemporaryVariable$default, (FirVariable) obj2);
                        }
                    });
                } else {
                    convertDeclarationFromClassBody(lighterASTNode, firScriptBuilder.getDeclarations(), null, arrayList2);
                    FirDeclaration firDeclaration = (FirDeclaration) CollectionsKt.lastOrNull(firScriptBuilder.getDeclarations());
                    if (firDeclaration != null) {
                        DeclarationAttributesKt.setScriptTopLevelDeclaration(firDeclaration, Boolean.TRUE);
                    }
                }
            }
            convertDanglingModifierListsInClassBody(arrayList2, firScriptBuilder.getDeclarations());
            setup.invoke(firScriptBuilder);
            Unit unit = Unit.INSTANCE;
            return firScriptBuilder.mo288build();
        } finally {
            getContext().popContainerSymbol(symbol);
            getContext().setContainingScriptSymbol(null);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final FirTypeRef convertType(LighterASTNode type) throws Exception {
        LightTreeRawFirDeclarationBuilder lightTreeRawFirDeclarationBuilder;
        ArrayList arrayList;
        type.getClass();
        KtSourceElement ktSourceElement = (KtLightSourceElement) AbstractRawFirBuilder.toFirSourceElement$default(this, type, null, 1, null);
        ArrayList arrayList2 = new ArrayList();
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        LighterASTNode[] childrenAsArray = getChildrenAsArray(type);
        int length = childrenAsArray.length;
        int i = 0;
        while (i < length) {
            LighterASTNode lighterASTNode = childrenAsArray[i];
            if (lighterASTNode == null) {
                break;
            }
            if (AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType())) {
                lightTreeRawFirDeclarationBuilder = this;
                arrayList = arrayList2;
            } else {
                IElementType tokenType = lighterASTNode.getTokenType();
                if (Intrinsics.areEqual(tokenType, KtNodeTypes.TYPE_REFERENCE)) {
                    objectRef.element = this.convertType(lighterASTNode);
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.MODIFIER_LIST)) {
                    arrayList2.add(convertModifierList$default(this, lighterASTNode, false, 2, null));
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.USER_TYPE)) {
                    lightTreeRawFirDeclarationBuilder = this;
                    objectRef.element = convertUserType$default(lightTreeRawFirDeclarationBuilder, ktSourceElement, lighterASTNode, false, 4, null);
                    arrayList = arrayList2;
                    ktSourceElement = ktSourceElement;
                } else {
                    lightTreeRawFirDeclarationBuilder = this;
                    if (Intrinsics.areEqual(tokenType, KtNodeTypes.NULLABLE_TYPE)) {
                        arrayList = arrayList2;
                        objectRef.element = convertNullableType$default(lightTreeRawFirDeclarationBuilder, ktSourceElement, lighterASTNode, arrayList, false, 8, null);
                    } else {
                        arrayList = arrayList2;
                        if (Intrinsics.areEqual(tokenType, KtNodeTypes.FUNCTION_TYPE)) {
                            objectRef.element = convertFunctionType$default(lightTreeRawFirDeclarationBuilder, ktSourceElement, lighterASTNode, arrayList, false, 8, null);
                        } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.DYNAMIC_TYPE)) {
                            FirDynamicTypeRefBuilder firDynamicTypeRefBuilder = new FirDynamicTypeRefBuilder();
                            firDynamicTypeRefBuilder.setSource(ktSourceElement);
                            firDynamicTypeRefBuilder.setMarkedNullable(false);
                            objectRef.element = firDynamicTypeRefBuilder.build();
                        } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.INTERSECTION_TYPE)) {
                            objectRef.element = lightTreeRawFirDeclarationBuilder.convertIntersectionType(ktSourceElement, lighterASTNode, false);
                        } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.CONTEXT_PARAMETER_LIST) || Intrinsics.areEqual(tokenType, TokenType.ERROR_ELEMENT)) {
                            FirErrorTypeRefBuilder firErrorTypeRefBuilder = new FirErrorTypeRefBuilder();
                            firErrorTypeRefBuilder.setSource(ktSourceElement);
                            firErrorTypeRefBuilder.setDiagnostic(new ConeSyntaxDiagnostic("Unwrapped type is null"));
                            objectRef.element = firErrorTypeRefBuilder.build();
                        }
                    }
                }
                lightTreeRawFirDeclarationBuilder = this;
                arrayList = arrayList2;
            }
            i++;
            this = lightTreeRawFirDeclarationBuilder;
            arrayList2 = arrayList;
        }
        LightTreeRawFirDeclarationBuilder lightTreeRawFirDeclarationBuilder2 = this;
        ArrayList arrayList3 = arrayList2;
        FirResolvedTypeRef firResolvedTypeRefBuild = (FirTypeRef) objectRef.element;
        if (firResolvedTypeRefBuild == null) {
            FirErrorTypeRefBuilder firErrorTypeRefBuilder2 = new FirErrorTypeRefBuilder();
            firErrorTypeRefBuilder2.setSource(ktSourceElement);
            firErrorTypeRefBuilder2.setDiagnostic(new ConeSyntaxDiagnostic("Incomplete code"));
            firResolvedTypeRefBuild = firErrorTypeRefBuilder2.build();
        }
        Iterator it = arrayList3.iterator();
        while (it.hasNext()) {
            firResolvedTypeRefBuild.replaceAnnotations(UtilsKt.smartPlus(firResolvedTypeRefBuild.getAnnotations(), lightTreeRawFirDeclarationBuilder2.convertAnnotations((ModifierList) it.next())));
        }
        return firResolvedTypeRefBuild;
    }

    /* JADX WARN: Code duplicated, block: B:90:0x0216  */
    /* JADX WARN: Code duplicated, block: B:92:0x0224  */
    public final FirTypeAlias convertTypeAlias(LighterASTNode typeAlias) throws Throwable {
        FirTypeAlias firTypeAlias;
        LighterASTNode lighterASTNode;
        typeAlias.getClass();
        ModifierList modifierListConvertModifierList$default = null;
        String asText = null;
        LighterASTNode lighterASTNode2 = null;
        LighterASTNode lighterASTNode3 = null;
        for (LighterASTNode lighterASTNode4 : getChildrenAsArray(typeAlias)) {
            if (lighterASTNode4 == null) {
                break;
            }
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode4.getTokenType())) {
                IElementType tokenType = lighterASTNode4.getTokenType();
                if (Intrinsics.areEqual(tokenType, KtNodeTypes.MODIFIER_LIST)) {
                    modifierListConvertModifierList$default = convertModifierList$default(this, lighterASTNode4, false, 2, null);
                } else if (Intrinsics.areEqual(tokenType, KtTokens.IDENTIFIER)) {
                    asText = getAsText(lighterASTNode4);
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.TYPE_REFERENCE)) {
                    lighterASTNode2 = lighterASTNode4;
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.TYPE_PARAMETER_LIST)) {
                    lighterASTNode3 = lighterASTNode4;
                }
            }
        }
        ModifierList modifierList = modifierListConvertModifierList$default == null ? new ModifierList(0L, 1, null) : modifierListConvertModifierList$default;
        Name nameNameAsSafeName$default = ConverterUtilKt.nameAsSafeName$default(asText, null, 1, null);
        boolean z = modifierList.hasExpect() || getContext().getContainerIsExpect();
        getContext().setClassName(getContext().getClassName().child(nameNameAsSafeName$default));
        boolean containerIsExpect = getContext().getContainerIsExpect();
        getContext().setContainerIsExpect(containerIsExpect || z);
        int size = getContext().getDispatchReceiverTypesStack().size();
        try {
            try {
                FirTypeAliasSymbol firTypeAliasSymbol = new FirTypeAliasSymbol(getContext().getCurrentClassId());
                getContext().pushContainerSymbol(firTypeAliasSymbol);
                try {
                    boolean zIsInner = modifierList.isInner();
                    FirTypeAliasBuilder firTypeAliasBuilder = new FirTypeAliasBuilder();
                    LighterASTNode lighterASTNode5 = lighterASTNode2;
                    firTypeAliasBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(this, typeAlias, null, 1, null));
                    firTypeAliasBuilder.setModuleData(getBaseModuleData());
                    firTypeAliasBuilder.setOrigin(FirDeclarationOrigin.Source.INSTANCE);
                    firTypeAliasBuilder.setScopeProvider(this.baseScopeProvider);
                    firTypeAliasBuilder.setName(nameNameAsSafeName$default);
                    boolean inLocalContext = getContext().getInLocalContext();
                    firTypeAlias = null;
                    try {
                        FirDeclarationStatusImpl firDeclarationStatusImpl = new FirDeclarationStatusImpl(inLocalContext ? Visibilities.Local.INSTANCE : modifierList.getVisibility(true), Modality.FINAL);
                        firDeclarationStatusImpl.setExpect(z);
                        firDeclarationStatusImpl.setActual(modifierList.hasActual());
                        firDeclarationStatusImpl.setInner(zIsInner);
                        firTypeAliasBuilder.setStatus(firDeclarationStatusImpl);
                        firTypeAliasBuilder.setSymbol(firTypeAliasSymbol);
                        if (lighterASTNode5 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("typeRefNode");
                            lighterASTNode = null;
                        } else {
                            lighterASTNode = lighterASTNode5;
                        }
                        firTypeAliasBuilder.setExpandedTypeRef(convertType(lighterASTNode));
                        if (modifierListConvertModifierList$default != null) {
                            convertAnnotationsTo(modifierListConvertModifierList$default, firTypeAliasBuilder.getAnnotations());
                        }
                        if (lighterASTNode3 != null) {
                            CollectionsKt.addAll(firTypeAliasBuilder.getTypeParameters(), convertTypeParameters(lighterASTNode3, CollectionsKt.emptyList(), firTypeAliasSymbol));
                        }
                        if (zIsInner || inLocalContext) {
                            getContext().appendOuterTypeParameters(false, firTypeAliasBuilder.getTypeParameters());
                        }
                        FirTypeAlias firTypeAliasMo288build = firTypeAliasBuilder.mo288build();
                        getContext().popContainerSymbol(firTypeAliasSymbol);
                        if (getContext().getDispatchReceiverTypesStack().size() <= size + 1) {
                            if (getContext().getDispatchReceiverTypesStack().size() > size) {
                                getContext().getDispatchReceiverTypesStack().remove(CollectionsKt.getLastIndex(getContext().getDispatchReceiverTypesStack()));
                            }
                            getContext().setClassName(getContext().getClassName().parent());
                            getContext().setContainerIsExpect(containerIsExpect);
                            LighterASTNode parent = getParent(typeAlias);
                            if (Intrinsics.areEqual(parent != null ? getElementType(parent) : null, KtStubElementTypes.CLASS_BODY)) {
                                initContainingClassForLocalAttr(firTypeAliasMo288build);
                            }
                            if (isDirectlyInsideCompanionBlock()) {
                                ClassMembersKt.setIllegalCompanionBlockMember(firTypeAliasMo288build, Boolean.TRUE);
                            }
                            return firTypeAliasMo288build;
                        }
                    } catch (Throwable th) {
                        th = th;
                        getContext().popContainerSymbol(firTypeAliasSymbol);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                firTypeAlias = null;
                if (getContext().getDispatchReceiverTypesStack().size() <= size + 1) {
                    if (getContext().getDispatchReceiverTypesStack().size() > size) {
                        getContext().getDispatchReceiverTypesStack().remove(CollectionsKt.getLastIndex(getContext().getDispatchReceiverTypesStack()));
                    }
                    getContext().setClassName(getContext().getClassName().parent());
                    getContext().setContainerIsExpect(containerIsExpect);
                    throw th;
                }
            }
        } catch (Throwable th4) {
            th = th4;
            if (getContext().getDispatchReceiverTypesStack().size() <= size + 1) {
                if (getContext().getDispatchReceiverTypesStack().size() > size) {
                    getContext().getDispatchReceiverTypesStack().remove(CollectionsKt.getLastIndex(getContext().getDispatchReceiverTypesStack()));
                }
                getContext().setClassName(getContext().getClassName().parent());
                getContext().setContainerIsExpect(containerIsExpect);
                throw th;
            }
        }
        v1f.a("Wrong number of ", getContext().getDispatchReceiverTypesStack().size());
        return firTypeAlias;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final List<FirTypeProjection> convertTypeArguments(LighterASTNode typeArguments, boolean allowedUnderscoredTypeArgument) throws UninitializedPropertyAccessException {
        typeArguments.getClass();
        LighterASTNode[] childrenAsArray = getChildrenAsArray(typeArguments);
        ArrayList arrayList = new ArrayList();
        for (LighterASTNode lighterASTNode : childrenAsArray) {
            if (lighterASTNode == null) {
                break;
            }
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType()) && Intrinsics.areEqual(lighterASTNode.getTokenType(), KtNodeTypes.TYPE_PROJECTION)) {
                arrayList.add(convertTypeProjection(lighterASTNode, allowedUnderscoredTypeArgument));
            }
        }
        return arrayList;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
    public final ValueParameter convertValueParameter(LighterASTNode valueParameter, FirBasedSymbol<?> containingDeclarationSymbol, AbstractRawFirBuilder.ValueParameterDeclaration valueParameterDeclaration, List<? extends FirAnnotation> additionalAnnotations) throws Exception {
        List<FirAnnotationCall> listEmptyList;
        LighterASTNode lighterASTNode;
        KtSourceElement firSourceElement$default;
        FirExpression firExpressionBuildErrorExpression;
        KtSourceElement source;
        KtSourceElement firSourceElement$default2;
        valueParameter.getClass();
        valueParameterDeclaration.getClass();
        additionalAnnotations.getClass();
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        LighterASTNode[] childrenAsArray = getChildrenAsArray(valueParameter);
        int length = childrenAsArray.length;
        Object obj = null;
        boolean z = false;
        ModifierList modifierListConvertModifierList$default = null;
        DestructuringDeclaration destructuringDeclarationConvertDestructingDeclaration$org_jetbrains_kotlin_fir_light_tree2fir = null;
        int i = 0;
        boolean z2 = false;
        boolean z3 = false;
        while (i < length && (lighterASTNode = childrenAsArray[i]) != null) {
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType())) {
                IElementType tokenType = lighterASTNode.getTokenType();
                if (Intrinsics.areEqual(tokenType, KtNodeTypes.MODIFIER_LIST)) {
                    modifierListConvertModifierList$default = convertModifierList$default(this, lighterASTNode, z, 2, obj);
                } else if (Intrinsics.areEqual(tokenType, KtTokens.VAL_KEYWORD)) {
                    z2 = true;
                } else if (Intrinsics.areEqual(tokenType, KtTokens.VAR_KEYWORD)) {
                    z3 = true;
                } else if (Intrinsics.areEqual(tokenType, KtTokens.IDENTIFIER)) {
                    objectRef.element = getAsText(lighterASTNode);
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.TYPE_REFERENCE)) {
                    continue;
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.DESTRUCTURING_DECLARATION)) {
                    destructuringDeclarationConvertDestructingDeclaration$org_jetbrains_kotlin_fir_light_tree2fir = convertDestructingDeclaration$org_jetbrains_kotlin_fir_light_tree2fir(lighterASTNode);
                } else if (ElementTypeUtils.INSTANCE.isExpression(lighterASTNode)) {
                    LightTreeRawFirExpressionBuilder lightTreeRawFirExpressionBuilder = this.expressionConverter;
                    FirStatement asFirStatement = lightTreeRawFirExpressionBuilder.getAsFirStatement(lighterASTNode, "Should have default value");
                    if (asFirStatement instanceof FirExpression) {
                        firExpressionBuildErrorExpression = (FirExpression) asFirStatement;
                        if (UtilsKt.isStatementLikeExpression(firExpressionBuildErrorExpression)) {
                            KtSourceElement source2 = firExpressionBuildErrorExpression.getSource();
                            if (source2 == null || (firSourceElement$default2 = KtSourceElementKt.realElement(source2)) == null) {
                                firSourceElement$default2 = AbstractRawFirBuilder.toFirSourceElement$default(lightTreeRawFirExpressionBuilder, lighterASTNode, null, 1, null);
                            }
                            firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default2, new ConeSimpleDiagnostic("Should have default value", DiagnosticKind.ExpressionExpected), asFirStatement);
                        }
                    } else {
                        if (asFirStatement == null || (source = asFirStatement.getSource()) == null || (firSourceElement$default = KtSourceElementKt.realElement(source)) == null) {
                            firSourceElement$default = AbstractRawFirBuilder.toFirSourceElement$default(lightTreeRawFirExpressionBuilder, lighterASTNode, null, 1, null);
                        }
                        firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default, new ConeSimpleDiagnostic("Should have default value", DiagnosticKind.ExpressionExpected), asFirStatement);
                    }
                    if (firExpressionBuildErrorExpression == null) {
                        x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.expressions.FirExpression");
                        return null;
                    }
                    objectRef2.element = firExpressionBuildErrorExpression;
                } else {
                    continue;
                }
            }
            i++;
            obj = null;
            z = false;
        }
        Name nameConvertValueParameterName = convertValueParameterName(ConverterUtilKt.nameAsSafeName$default((String) objectRef.element, null, 1, null), valueParameterDeclaration, new Function0() { // from class: l19
            public final Object invoke() {
                return LightTreeRawFirDeclarationBuilder.p(objectRef);
            }
        });
        FirValueParameterSymbol firValueParameterSymbol = new FirValueParameterSymbol();
        boolean isAnnotationOwner = valueParameterDeclaration.getIsAnnotationOwner();
        if (isAnnotationOwner) {
            getContext().pushContainerSymbol(firValueParameterSymbol);
        }
        try {
            FirImplicitTypeRef firImplicitTypeRefCreateNoTypeForParameterTypeRef = null;
            for (LighterASTNode lighterASTNode2 : getChildrenAsArray(valueParameter)) {
                if (lighterASTNode2 == null) {
                    break;
                }
                if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode2.getTokenType()) && Intrinsics.areEqual(lighterASTNode2.getTokenType(), KtNodeTypes.TYPE_REFERENCE)) {
                    firImplicitTypeRefCreateNoTypeForParameterTypeRef = convertType(lighterASTNode2);
                }
            }
            KtLightSourceElement firSourceElement$default3 = AbstractRawFirBuilder.toFirSourceElement$default(this, valueParameter, null, 1, null);
            ModifierList modifierList = modifierListConvertModifierList$default == null ? new ModifierList(0L, 1, null) : modifierListConvertModifierList$default;
            if (modifierListConvertModifierList$default == null || (listEmptyList = convertAnnotations(modifierListConvertModifierList$default)) == null) {
                listEmptyList = CollectionsKt.emptyList();
            }
            List<FirAnnotationCall> list = listEmptyList;
            if (firImplicitTypeRefCreateNoTypeForParameterTypeRef == null) {
                firImplicitTypeRefCreateNoTypeForParameterTypeRef = valueParameterDeclaration.getShouldExplicitParameterTypeBePresent() ? createNoTypeForParameterTypeRef(firSourceElement$default3) : getImplicitType();
            }
            return new ValueParameter(firValueParameterSymbol, z2, z3, modifierList, list, firImplicitTypeRefCreateNoTypeForParameterTypeRef, firSourceElement$default3, getBaseModuleData(), valueParameterDeclaration == AbstractRawFirBuilder.ValueParameterDeclaration.PRIMARY_CONSTRUCTOR, valueParameterDeclaration == AbstractRawFirBuilder.ValueParameterDeclaration.CONTEXT_PARAMETER, additionalAnnotations, nameConvertValueParameterName, (FirExpression) objectRef2.element, containingDeclarationSymbol, destructuringDeclarationConvertDestructingDeclaration$org_jetbrains_kotlin_fir_light_tree2fir);
        } finally {
            if (isAnnotationOwner) {
                getContext().popContainerSymbol(firValueParameterSymbol);
            }
        }
    }

    public final List<ValueParameter> convertValueParameters(LighterASTNode valueParameters, FirFunctionSymbol<?> functionSymbol, AbstractRawFirBuilder.ValueParameterDeclaration valueParameterDeclaration, List<? extends FirAnnotation> additionalAnnotations) throws Exception {
        valueParameters.getClass();
        functionSymbol.getClass();
        valueParameterDeclaration.getClass();
        additionalAnnotations.getClass();
        LighterASTNode[] childrenAsArray = getChildrenAsArray(valueParameters);
        ArrayList arrayList = new ArrayList();
        for (LighterASTNode lighterASTNode : childrenAsArray) {
            if (lighterASTNode == null) {
                break;
            }
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType()) && Intrinsics.areEqual(lighterASTNode.getTokenType(), KtNodeTypes.VALUE_PARAMETER)) {
                arrayList.add(convertValueParameter(lighterASTNode, functionSymbol, valueParameterDeclaration, additionalAnnotations));
            }
        }
        return arrayList;
    }

    /* JADX INFO: renamed from: getBaseScopeProvider$org_jetbrains_kotlin_fir_light_tree2fir, reason: from getter */
    public final FirScopeProvider getBaseScopeProvider() {
        return this.baseScopeProvider;
    }

    public final boolean isUnderscored(FirUserTypeRef firUserTypeRef) {
        KtSourceElement source;
        firUserTypeRef.getClass();
        FirQualifierPart firQualifierPart = (FirQualifierPart) CollectionsKt.lastOrNull(firUserTypeRef.getQualifier());
        if (firQualifierPart == null || (source = firQualifierPart.getSource()) == null) {
            return false;
        }
        LighterASTNode lighterASTNode = source.getLighterASTNode();
        KtToken ktToken = KtTokens.IDENTIFIER;
        ktToken.getClass();
        LighterASTNode childNodeByType = getChildNodeByType(lighterASTNode, (IElementType) ktToken);
        return Intrinsics.areEqual(childNodeByType != null ? getAsText(childNodeByType) : null, InlineClassManglingUtilsKt.NOT_INLINE_CLASS_PARAMETER_PLACEHOLDER);
    }

    @Override // org.jetbrains.kotlin.fir.builder.AbstractRawFirBuilder
    public /* bridge */ /* synthetic */ FirReplSnippet convertReplSnippet(LighterASTNode lighterASTNode, KtSourceElement ktSourceElement, String str, Function1 function1, Function1 function2, Function1 function3) {
        return convertReplSnippet2(lighterASTNode, ktSourceElement, str, (Function1<? super FirReplSnippetBuilder, Unit>) function1, (Function1<? super FirBlockBuilder, Unit>) function2, (Function1<? super List<FirElement>, Unit>) function3);
    }

    public /* synthetic */ LightTreeRawFirDeclarationBuilder(FirSession firSession, FirScopeProvider firScopeProvider, FlyweightCapableTreeStructure flyweightCapableTreeStructure, Context context, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(firSession, firScopeProvider, flyweightCapableTreeStructure, (i & 8) != 0 ? new Context() : context);
    }

    @Override // org.jetbrains.kotlin.fir.builder.AbstractRawFirBuilder
    public /* bridge */ /* synthetic */ FirScript convertScript(LighterASTNode lighterASTNode, KtSourceElement ktSourceElement, String str, Function1 function1) {
        return convertScript2(lighterASTNode, ktSourceElement, str, (Function1<? super FirScriptBuilder, Unit>) function1);
    }
}
