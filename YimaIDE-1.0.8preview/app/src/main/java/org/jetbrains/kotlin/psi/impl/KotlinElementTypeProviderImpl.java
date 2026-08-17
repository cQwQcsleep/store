package org.jetbrains.kotlin.psi.impl;

import com.intellij.psi.impl.source.tree.ICodeFragmentElementType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.ILazyParseableElementType;
import kotlin.Metadata;
import org.jetbrains.kotlin.BlockExpressionElementType;
import org.jetbrains.kotlin.KotlinElementTypeProvider;
import org.jetbrains.kotlin.LambdaExpressionElementType;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.idea.KotlinLanguage;
import org.jetbrains.kotlin.psi.KtAnnotation;
import org.jetbrains.kotlin.psi.KtAnnotationEntry;
import org.jetbrains.kotlin.psi.KtAnnotationUseSiteTarget;
import org.jetbrains.kotlin.psi.KtBackingField;
import org.jetbrains.kotlin.psi.KtBlockCodeFragmentType;
import org.jetbrains.kotlin.psi.KtBlockStringTemplateEntry;
import org.jetbrains.kotlin.psi.KtCallExpression;
import org.jetbrains.kotlin.psi.KtClass;
import org.jetbrains.kotlin.psi.KtClassBody;
import org.jetbrains.kotlin.psi.KtClassInitializer;
import org.jetbrains.kotlin.psi.KtClassLiteralExpression;
import org.jetbrains.kotlin.psi.KtCollectionLiteralExpression;
import org.jetbrains.kotlin.psi.KtCompanionBlock;
import org.jetbrains.kotlin.psi.KtConstantExpression;
import org.jetbrains.kotlin.psi.KtConstructorCalleeExpression;
import org.jetbrains.kotlin.psi.KtContextParameterList;
import org.jetbrains.kotlin.psi.KtContextReceiver;
import org.jetbrains.kotlin.psi.KtContractEffect;
import org.jetbrains.kotlin.psi.KtContractEffectList;
import org.jetbrains.kotlin.psi.KtDeclarationModifierList;
import org.jetbrains.kotlin.psi.KtDelegatedSuperTypeEntry;
import org.jetbrains.kotlin.psi.KtDotQualifiedExpression;
import org.jetbrains.kotlin.psi.KtDynamicType;
import org.jetbrains.kotlin.psi.KtEnumEntry;
import org.jetbrains.kotlin.psi.KtEnumEntrySuperclassReferenceExpression;
import org.jetbrains.kotlin.psi.KtEscapeStringTemplateEntry;
import org.jetbrains.kotlin.psi.KtExperimentalApi;
import org.jetbrains.kotlin.psi.KtExpressionCodeFragmentType;
import org.jetbrains.kotlin.psi.KtFileAnnotationList;
import org.jetbrains.kotlin.psi.KtFunctionType;
import org.jetbrains.kotlin.psi.KtFunctionTypeReceiver;
import org.jetbrains.kotlin.psi.KtImplementationDetail;
import org.jetbrains.kotlin.psi.KtImportAlias;
import org.jetbrains.kotlin.psi.KtImportDirective;
import org.jetbrains.kotlin.psi.KtImportList;
import org.jetbrains.kotlin.psi.KtInitializerList;
import org.jetbrains.kotlin.psi.KtIntersectionType;
import org.jetbrains.kotlin.psi.KtLambdaArgument;
import org.jetbrains.kotlin.psi.KtLiteralStringTemplateEntry;
import org.jetbrains.kotlin.psi.KtNameReferenceExpression;
import org.jetbrains.kotlin.psi.KtNamedFunction;
import org.jetbrains.kotlin.psi.KtNullableType;
import org.jetbrains.kotlin.psi.KtObjectDeclaration;
import org.jetbrains.kotlin.psi.KtPackageDirective;
import org.jetbrains.kotlin.psi.KtParameter;
import org.jetbrains.kotlin.psi.KtParameterList;
import org.jetbrains.kotlin.psi.KtPrimaryConstructor;
import org.jetbrains.kotlin.psi.KtProperty;
import org.jetbrains.kotlin.psi.KtPropertyAccessor;
import org.jetbrains.kotlin.psi.KtScript;
import org.jetbrains.kotlin.psi.KtScriptInitializer;
import org.jetbrains.kotlin.psi.KtSecondaryConstructor;
import org.jetbrains.kotlin.psi.KtSimpleNameStringTemplateEntry;
import org.jetbrains.kotlin.psi.KtStringInterpolationPrefix;
import org.jetbrains.kotlin.psi.KtStringTemplateExpression;
import org.jetbrains.kotlin.psi.KtSuperTypeCallEntry;
import org.jetbrains.kotlin.psi.KtSuperTypeEntry;
import org.jetbrains.kotlin.psi.KtSuperTypeList;
import org.jetbrains.kotlin.psi.KtTypeAlias;
import org.jetbrains.kotlin.psi.KtTypeArgumentList;
import org.jetbrains.kotlin.psi.KtTypeCodeFragmentType;
import org.jetbrains.kotlin.psi.KtTypeConstraint;
import org.jetbrains.kotlin.psi.KtTypeConstraintList;
import org.jetbrains.kotlin.psi.KtTypeParameter;
import org.jetbrains.kotlin.psi.KtTypeParameterList;
import org.jetbrains.kotlin.psi.KtTypeProjection;
import org.jetbrains.kotlin.psi.KtTypeReference;
import org.jetbrains.kotlin.psi.KtUserType;
import org.jetbrains.kotlin.psi.KtValueArgument;
import org.jetbrains.kotlin.psi.KtValueArgumentList;
import org.jetbrains.kotlin.psi.KtValueArgumentName;
import org.jetbrains.kotlin.psi.stubs.KotlinAnnotationEntryStub;
import org.jetbrains.kotlin.psi.stubs.KotlinAnnotationUseSiteTargetStub;
import org.jetbrains.kotlin.psi.stubs.KotlinBackingFieldStub;
import org.jetbrains.kotlin.psi.stubs.KotlinBlockStringTemplateEntryStub;
import org.jetbrains.kotlin.psi.stubs.KotlinClassLiteralExpressionStub;
import org.jetbrains.kotlin.psi.stubs.KotlinClassStub;
import org.jetbrains.kotlin.psi.stubs.KotlinCollectionLiteralExpressionStub;
import org.jetbrains.kotlin.psi.stubs.KotlinConstantExpressionStub;
import org.jetbrains.kotlin.psi.stubs.KotlinConstructorStub;
import org.jetbrains.kotlin.psi.stubs.KotlinContextReceiverStub;
import org.jetbrains.kotlin.psi.stubs.KotlinContractEffectStub;
import org.jetbrains.kotlin.psi.stubs.KotlinEnumEntrySuperclassReferenceExpressionStub;
import org.jetbrains.kotlin.psi.stubs.KotlinFunctionStub;
import org.jetbrains.kotlin.psi.stubs.KotlinFunctionTypeStub;
import org.jetbrains.kotlin.psi.stubs.KotlinImportAliasStub;
import org.jetbrains.kotlin.psi.stubs.KotlinImportDirectiveStub;
import org.jetbrains.kotlin.psi.stubs.KotlinModifierListStub;
import org.jetbrains.kotlin.psi.stubs.KotlinNameReferenceExpressionStub;
import org.jetbrains.kotlin.psi.stubs.KotlinObjectStub;
import org.jetbrains.kotlin.psi.stubs.KotlinParameterStub;
import org.jetbrains.kotlin.psi.stubs.KotlinPlaceHolderStub;
import org.jetbrains.kotlin.psi.stubs.KotlinPlaceHolderWithTextStub;
import org.jetbrains.kotlin.psi.stubs.KotlinPropertyAccessorStub;
import org.jetbrains.kotlin.psi.stubs.KotlinPropertyStub;
import org.jetbrains.kotlin.psi.stubs.KotlinScriptStub;
import org.jetbrains.kotlin.psi.stubs.KotlinStringInterpolationPrefixStub;
import org.jetbrains.kotlin.psi.stubs.KotlinTypeAliasStub;
import org.jetbrains.kotlin.psi.stubs.KotlinTypeParameterStub;
import org.jetbrains.kotlin.psi.stubs.KotlinTypeProjectionStub;
import org.jetbrains.kotlin.psi.stubs.KotlinUserTypeStub;
import org.jetbrains.kotlin.psi.stubs.KotlinValueArgumentStub;
import org.jetbrains.kotlin.psi.stubs.elements.KtAnnotationEntryElementType;
import org.jetbrains.kotlin.psi.stubs.elements.KtAnnotationUseSiteTargetElementType;
import org.jetbrains.kotlin.psi.stubs.elements.KtBackingFieldElementType;
import org.jetbrains.kotlin.psi.stubs.elements.KtBlockStringTemplateEntryElementType;
import org.jetbrains.kotlin.psi.stubs.elements.KtClassElementType;
import org.jetbrains.kotlin.psi.stubs.elements.KtClassLiteralExpressionElementType;
import org.jetbrains.kotlin.psi.stubs.elements.KtCollectionLiteralExpressionElementType;
import org.jetbrains.kotlin.psi.stubs.elements.KtConstantExpressionElementType;
import org.jetbrains.kotlin.psi.stubs.elements.KtContextReceiverElementType;
import org.jetbrains.kotlin.psi.stubs.elements.KtContractEffectElementType;
import org.jetbrains.kotlin.psi.stubs.elements.KtDotQualifiedExpressionElementType;
import org.jetbrains.kotlin.psi.stubs.elements.KtEnumEntryElementType;
import org.jetbrains.kotlin.psi.stubs.elements.KtEnumEntrySuperClassReferenceExpressionElementType;
import org.jetbrains.kotlin.psi.stubs.elements.KtFileElementType;
import org.jetbrains.kotlin.psi.stubs.elements.KtFunctionElementType;
import org.jetbrains.kotlin.psi.stubs.elements.KtFunctionTypeElementType;
import org.jetbrains.kotlin.psi.stubs.elements.KtImportAliasElementType;
import org.jetbrains.kotlin.psi.stubs.elements.KtImportDirectiveElementType;
import org.jetbrains.kotlin.psi.stubs.elements.KtModifierListElementType;
import org.jetbrains.kotlin.psi.stubs.elements.KtNameReferenceExpressionElementType;
import org.jetbrains.kotlin.psi.stubs.elements.KtObjectElementType;
import org.jetbrains.kotlin.psi.stubs.elements.KtParameterElementType;
import org.jetbrains.kotlin.psi.stubs.elements.KtPlaceHolderStubElementType;
import org.jetbrains.kotlin.psi.stubs.elements.KtPlaceHolderWithTextStubElementType;
import org.jetbrains.kotlin.psi.stubs.elements.KtPrimaryConstructorElementType;
import org.jetbrains.kotlin.psi.stubs.elements.KtPropertyAccessorElementType;
import org.jetbrains.kotlin.psi.stubs.elements.KtPropertyElementType;
import org.jetbrains.kotlin.psi.stubs.elements.KtScriptElementType;
import org.jetbrains.kotlin.psi.stubs.elements.KtSecondaryConstructorElementType;
import org.jetbrains.kotlin.psi.stubs.elements.KtStringInterpolationPrefixElementType;
import org.jetbrains.kotlin.psi.stubs.elements.KtStubElementType;
import org.jetbrains.kotlin.psi.stubs.elements.KtStubElementTypes;
import org.jetbrains.kotlin.psi.stubs.elements.KtTypeAliasElementType;
import org.jetbrains.kotlin.psi.stubs.elements.KtTypeParameterElementType;
import org.jetbrains.kotlin.psi.stubs.elements.KtTypeProjectionElementType;
import org.jetbrains.kotlin.psi.stubs.elements.KtUserTypeElementType;
import org.jetbrains.kotlin.psi.stubs.elements.KtValueArgumentElementType;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000Æ\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\"\u0010\b\u001a\u0010\u0012\u0006\b\u0001\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\"\u0010\u000e\u001a\u0010\u0012\u0006\b\u0001\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\rR\"\u0010\u0012\u001a\u0010\u0012\u0006\b\u0001\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\rR(\u0010\u0016\u001a\u0016\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00020\u00180\u0017\u0012\u0004\u0012\u00020\u00180\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\rR2\u0010\u001a\u001a\u0016\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00020\u001b0\u0017\u0012\u0004\u0012\u00020\u001b0\t8VX\u0097\u0004r\u0002\b\u001e¢\u0006\f\u0012\u0004\b\u001c\u0010\u0003\u001a\u0004\b\u001d\u0010\rR(\u0010\u001f\u001a\u0016\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00020 0\u0017\u0012\u0004\u0012\u00020 0\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\rR(\u0010\"\u001a\u0016\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00020#0\u0017\u0012\u0004\u0012\u00020#0\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b$\u0010\rR\"\u0010%\u001a\u0010\u0012\u0006\b\u0001\u0012\u00020&\u0012\u0004\u0012\u00020'0\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b(\u0010\rR\"\u0010)\u001a\u0010\u0012\u0006\b\u0001\u0012\u00020*\u0012\u0004\u0012\u00020+0\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b,\u0010\rR\"\u0010-\u001a\u0010\u0012\u0006\b\u0001\u0012\u00020\n\u0012\u0004\u0012\u00020.0\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b/\u0010\rR(\u00100\u001a\u0016\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00020201\u0012\u0004\u0012\u0002020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b3\u0010\rR(\u00104\u001a\u0016\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00020501\u0012\u0004\u0012\u0002050\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b6\u0010\rR(\u00107\u001a\u0016\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u0002080\u0017\u0012\u0004\u0012\u0002080\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b9\u0010\rR\"\u0010:\u001a\u0010\u0012\u0006\b\u0001\u0012\u00020;\u0012\u0004\u0012\u00020<0\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b=\u0010\rR\"\u0010>\u001a\u0010\u0012\u0006\b\u0001\u0012\u00020?\u0012\u0004\u0012\u00020@0\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bA\u0010\rR(\u0010B\u001a\u0016\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00020C0\u0017\u0012\u0004\u0012\u00020C0\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bD\u0010\rR(\u0010E\u001a\u0016\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00020F0\u0017\u0012\u0004\u0012\u00020F0\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bG\u0010\rR\"\u0010H\u001a\u0010\u0012\u0006\b\u0001\u0012\u00020I\u0012\u0004\u0012\u00020J0\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bK\u0010\rR(\u0010L\u001a\u0016\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00020M0\u0017\u0012\u0004\u0012\u00020M0\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bN\u0010\rR\"\u0010O\u001a\u0010\u0012\u0006\b\u0001\u0012\u00020P\u0012\u0004\u0012\u00020Q0\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bR\u0010\rR(\u0010S\u001a\u0016\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00020T0\u0017\u0012\u0004\u0012\u00020T0\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bU\u0010\rR\"\u0010V\u001a\u0010\u0012\u0006\b\u0001\u0012\u00020W\u0012\u0004\u0012\u00020X0\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bY\u0010\rR(\u0010Z\u001a\u0016\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00020[0\u0017\u0012\u0004\u0012\u00020[0\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\\\u0010\rR(\u0010]\u001a\u0016\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00020^0\u0017\u0012\u0004\u0012\u00020^0\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b_\u0010\rR(\u0010`\u001a\u0016\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00020a0\u0017\u0012\u0004\u0012\u00020a0\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bb\u0010\rR(\u0010c\u001a\u0016\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00020d0\u0017\u0012\u0004\u0012\u00020d0\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\be\u0010\rR(\u0010f\u001a\u0016\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00020g0\u0017\u0012\u0004\u0012\u00020g0\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bh\u0010\rR(\u0010i\u001a\u0016\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00020j0\u0017\u0012\u0004\u0012\u00020j0\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bk\u0010\rR\"\u0010l\u001a\u0010\u0012\u0006\b\u0001\u0012\u00020m\u0012\u0004\u0012\u00020n0\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bo\u0010\rR(\u0010p\u001a\u0016\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00020q0\u0017\u0012\u0004\u0012\u00020q0\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\br\u0010\rR\"\u0010s\u001a\u0010\u0012\u0006\b\u0001\u0012\u00020t\u0012\u0004\u0012\u00020u0\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bv\u0010\rR\"\u0010w\u001a\u0010\u0012\u0006\b\u0001\u0012\u00020x\u0012\u0004\u0012\u00020y0\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bz\u0010\rR(\u0010{\u001a\u0016\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00020|0\u0017\u0012\u0004\u0012\u00020|0\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b}\u0010\rR$\u0010~\u001a\u0011\u0012\u0006\b\u0001\u0012\u00020\u007f\u0012\u0005\u0012\u00030\u0080\u00010\t8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b\u0081\u0001\u0010\rR,\u0010\u0082\u0001\u001a\u0018\u0012\r\b\u0001\u0012\t\u0012\u0005\u0012\u00030\u0083\u00010\u0017\u0012\u0005\u0012\u00030\u0083\u00010\t8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b\u0084\u0001\u0010\rR&\u0010\u0085\u0001\u001a\u0012\u0012\u0007\b\u0001\u0012\u00030\u0086\u0001\u0012\u0005\u0012\u00030\u0087\u00010\t8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b\u0088\u0001\u0010\rR,\u0010\u0089\u0001\u001a\u0018\u0012\r\b\u0001\u0012\t\u0012\u0005\u0012\u00030\u008a\u00010\u0017\u0012\u0005\u0012\u00030\u008a\u00010\t8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b\u008b\u0001\u0010\rR,\u0010\u008c\u0001\u001a\u0018\u0012\r\b\u0001\u0012\t\u0012\u0005\u0012\u00030\u008d\u00010\u0017\u0012\u0005\u0012\u00030\u008d\u00010\t8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b\u008e\u0001\u0010\rR,\u0010\u008f\u0001\u001a\u0018\u0012\r\b\u0001\u0012\t\u0012\u0005\u0012\u00030\u0090\u00010\u0017\u0012\u0005\u0012\u00030\u0090\u00010\t8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b\u0091\u0001\u0010\rR&\u0010\u0092\u0001\u001a\u0012\u0012\u0007\b\u0001\u0012\u00030\u0093\u0001\u0012\u0005\u0012\u00030\u0094\u00010\t8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b\u0095\u0001\u0010\rR&\u0010\u0096\u0001\u001a\u0012\u0012\u0007\b\u0001\u0012\u00030\u0097\u0001\u0012\u0005\u0012\u00030\u0098\u00010\t8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b\u0099\u0001\u0010\rR&\u0010\u009a\u0001\u001a\u0012\u0012\u0007\b\u0001\u0012\u00030\u0097\u0001\u0012\u0005\u0012\u00030\u0098\u00010\t8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b\u009b\u0001\u0010\rR&\u0010\u009c\u0001\u001a\u0012\u0012\u0007\b\u0001\u0012\u00030\u0097\u0001\u0012\u0005\u0012\u00030\u0098\u00010\t8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b\u009d\u0001\u0010\rR&\u0010\u009e\u0001\u001a\u0012\u0012\u0007\b\u0001\u0012\u00030\u0097\u0001\u0012\u0005\u0012\u00030\u0098\u00010\t8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b\u009f\u0001\u0010\rR&\u0010 \u0001\u001a\u0012\u0012\u0007\b\u0001\u0012\u00030\u0097\u0001\u0012\u0005\u0012\u00030\u0098\u00010\t8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b¡\u0001\u0010\rR,\u0010¢\u0001\u001a\u0018\u0012\r\b\u0001\u0012\t\u0012\u0005\u0012\u00030£\u00010\u0017\u0012\u0005\u0012\u00030£\u00010\t8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b¤\u0001\u0010\rR&\u0010¥\u0001\u001a\u0012\u0012\u0007\b\u0001\u0012\u00030¦\u0001\u0012\u0005\u0012\u00030§\u00010\t8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b¨\u0001\u0010\rR-\u0010©\u0001\u001a\u0019\u0012\u000e\b\u0001\u0012\n\u0012\u0005\u0012\u00030«\u00010ª\u0001\u0012\u0005\u0012\u00030«\u00010\t8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b¬\u0001\u0010\rR-\u0010\u00ad\u0001\u001a\u0019\u0012\u000e\b\u0001\u0012\n\u0012\u0005\u0012\u00030®\u00010ª\u0001\u0012\u0005\u0012\u00030®\u00010\t8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b¯\u0001\u0010\rR-\u0010°\u0001\u001a\u0019\u0012\u000e\b\u0001\u0012\n\u0012\u0005\u0012\u00030±\u00010ª\u0001\u0012\u0005\u0012\u00030±\u00010\t8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b²\u0001\u0010\rR&\u0010³\u0001\u001a\u0012\u0012\u0007\b\u0001\u0012\u00030´\u0001\u0012\u0005\u0012\u00030µ\u00010\t8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b¶\u0001\u0010\rR\u0018\u0010·\u0001\u001a\u00030¸\u0001X\u0096\u0004¢\u0006\n\n\u0000\u001a\u0006\b¹\u0001\u0010º\u0001R\u0018\u0010»\u0001\u001a\u00030¸\u0001X\u0096\u0004¢\u0006\n\n\u0000\u001a\u0006\b¼\u0001\u0010º\u0001R&\u0010½\u0001\u001a\u0012\u0012\u0007\b\u0001\u0012\u00030¾\u0001\u0012\u0005\u0012\u00030¿\u00010\t8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\bÀ\u0001\u0010\rR&\u0010Á\u0001\u001a\u0012\u0012\u0007\b\u0001\u0012\u00030Â\u0001\u0012\u0005\u0012\u00030Ã\u00010\t8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\bÄ\u0001\u0010\rR,\u0010Å\u0001\u001a\u0018\u0012\r\b\u0001\u0012\t\u0012\u0005\u0012\u00030Æ\u00010\u0017\u0012\u0005\u0012\u00030Æ\u00010\t8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\bÇ\u0001\u0010\rR,\u0010È\u0001\u001a\u0018\u0012\r\b\u0001\u0012\t\u0012\u0005\u0012\u00030É\u00010\u0017\u0012\u0005\u0012\u00030É\u00010\t8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\bÊ\u0001\u0010\rR&\u0010Ë\u0001\u001a\u0012\u0012\u0007\b\u0001\u0012\u00030Ì\u0001\u0012\u0005\u0012\u00030Í\u00010\t8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\bÎ\u0001\u0010\rR&\u0010Ï\u0001\u001a\u0012\u0012\u0007\b\u0001\u0012\u00030Ð\u0001\u0012\u0005\u0012\u00030Ñ\u00010\t8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\bÒ\u0001\u0010\rR,\u0010Ó\u0001\u001a\u0018\u0012\r\b\u0001\u0012\t\u0012\u0005\u0012\u00030Ô\u00010\u0017\u0012\u0005\u0012\u00030Ô\u00010\t8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\bÕ\u0001\u0010\rR,\u0010Ö\u0001\u001a\u0018\u0012\r\b\u0001\u0012\t\u0012\u0005\u0012\u00030×\u00010\u0017\u0012\u0005\u0012\u00030×\u00010\t8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\bØ\u0001\u0010\rR-\u0010Ù\u0001\u001a\u0019\u0012\u000e\b\u0001\u0012\n\u0012\u0005\u0012\u00030Û\u00010Ú\u0001\u0012\u0005\u0012\u00030Û\u00010\t8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\bÜ\u0001\u0010\rR,\u0010Ý\u0001\u001a\u0018\u0012\r\b\u0001\u0012\t\u0012\u0005\u0012\u00030Þ\u00010\u0017\u0012\u0005\u0012\u00030Þ\u00010\t8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\bß\u0001\u0010\rR&\u0010à\u0001\u001a\u0012\u0012\u0007\b\u0001\u0012\u00030á\u0001\u0012\u0005\u0012\u00030â\u00010\t8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\bã\u0001\u0010\rR-\u0010ä\u0001\u001a\u0019\u0012\u000e\b\u0001\u0012\n\u0012\u0005\u0012\u00030å\u00010Ú\u0001\u0012\u0005\u0012\u00030å\u00010\t8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\bæ\u0001\u0010\rR,\u0010ç\u0001\u001a\u0018\u0012\r\b\u0001\u0012\t\u0012\u0005\u0012\u00030è\u00010\u0017\u0012\u0005\u0012\u00030è\u00010\t8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\bé\u0001\u0010\rR,\u0010ê\u0001\u001a\u0018\u0012\r\b\u0001\u0012\t\u0012\u0005\u0012\u00030ë\u00010\u0017\u0012\u0005\u0012\u00030ë\u00010\t8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\bì\u0001\u0010\rR,\u0010í\u0001\u001a\u0018\u0012\r\b\u0001\u0012\t\u0012\u0005\u0012\u00030î\u00010\u0017\u0012\u0005\u0012\u00030î\u00010\t8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\bï\u0001\u0010\rR,\u0010ð\u0001\u001a\u0018\u0012\r\b\u0001\u0012\t\u0012\u0005\u0012\u00030ñ\u00010\u0017\u0012\u0005\u0012\u00030ñ\u00010\t8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\bò\u0001\u0010\rR&\u0010ó\u0001\u001a\u0012\u0012\u0007\b\u0001\u0012\u00030ô\u0001\u0012\u0005\u0012\u00030õ\u00010\t8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\bö\u0001\u0010\rR&\u0010÷\u0001\u001a\u0012\u0012\u0007\b\u0001\u0012\u00030ø\u0001\u0012\u0005\u0012\u00030ù\u00010\t8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\bú\u0001\u0010\rR&\u0010û\u0001\u001a\u0012\u0012\u0007\b\u0001\u0012\u00030ü\u0001\u0012\u0005\u0012\u00030ý\u00010\t8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\bþ\u0001\u0010\rR\u0018\u0010ÿ\u0001\u001a\u00030\u0080\u00028VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u0081\u0002\u0010\u0082\u0002R\u0018\u0010\u0083\u0002\u001a\u00030\u0080\u00028VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u0084\u0002\u0010\u0082\u0002R\u0018\u0010\u0085\u0002\u001a\u00030\u0080\u00028VX\u0096\u0004¢\u0006\b\u001a\u0006\b\u0086\u0002\u0010\u0082\u0002R\u0018\u0010\u0087\u0002\u001a\u00030\u0088\u0002X\u0096\u0004¢\u0006\n\n\u0000\u001a\u0006\b\u0089\u0002\u0010\u008a\u0002R\u0018\u0010\u008b\u0002\u001a\u00030\u0088\u0002X\u0096\u0004¢\u0006\n\n\u0000\u001a\u0006\b\u008c\u0002\u0010\u008a\u0002Ê\u0001\u0003\b\u008e\u0002¨\u0006\u008d\u0002"}, d2 = {"Lorg/jetbrains/kotlin/psi/impl/KotlinElementTypeProviderImpl;", "Lorg/jetbrains/kotlin/KotlinElementTypeProvider;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "fileType", "Lcom/intellij/psi/tree/IFileElementType;", "getFileType", "()Lcom/intellij/psi/tree/IFileElementType;", "classType", "Lorg/jetbrains/kotlin/psi/stubs/elements/KtStubElementType;", "Lorg/jetbrains/kotlin/psi/stubs/KotlinClassStub;", "Lorg/jetbrains/kotlin/psi/KtClass;", "getClassType", "()Lorg/jetbrains/kotlin/psi/stubs/elements/KtStubElementType;", "objectType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinObjectStub;", "Lorg/jetbrains/kotlin/psi/KtObjectDeclaration;", "getObjectType", "typeAliasType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinTypeAliasStub;", "Lorg/jetbrains/kotlin/psi/KtTypeAlias;", "getTypeAliasType", "classBodyType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinPlaceHolderStub;", "Lorg/jetbrains/kotlin/psi/KtClassBody;", "getClassBodyType", "companionBlockType", "Lorg/jetbrains/kotlin/psi/KtCompanionBlock;", "getCompanionBlockType$annotations", "getCompanionBlockType", "Lorg/jetbrains/kotlin/psi/KtExperimentalApi;", "classInitializerType", "Lorg/jetbrains/kotlin/psi/KtClassInitializer;", "getClassInitializerType", "scriptInitializerType", "Lorg/jetbrains/kotlin/psi/KtScriptInitializer;", "getScriptInitializerType", "functionType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinFunctionStub;", "Lorg/jetbrains/kotlin/psi/KtNamedFunction;", "getFunctionType", "propertyType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinPropertyStub;", "Lorg/jetbrains/kotlin/psi/KtProperty;", "getPropertyType", "enumEntryType", "Lorg/jetbrains/kotlin/psi/KtEnumEntry;", "getEnumEntryType", "primaryConstructorType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinConstructorStub;", "Lorg/jetbrains/kotlin/psi/KtPrimaryConstructor;", "getPrimaryConstructorType", "secondaryConstructorType", "Lorg/jetbrains/kotlin/psi/KtSecondaryConstructor;", "getSecondaryConstructorType", "constructorCalleeType", "Lorg/jetbrains/kotlin/psi/KtConstructorCalleeExpression;", "getConstructorCalleeType", "propertyAccessorType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinPropertyAccessorStub;", "Lorg/jetbrains/kotlin/psi/KtPropertyAccessor;", "getPropertyAccessorType", "backingFieldType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinBackingFieldStub;", "Lorg/jetbrains/kotlin/psi/KtBackingField;", "getBackingFieldType", "initializerListType", "Lorg/jetbrains/kotlin/psi/KtInitializerList;", "getInitializerListType", "valueParameterListType", "Lorg/jetbrains/kotlin/psi/KtParameterList;", "getValueParameterListType", "valueParameterType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinParameterStub;", "Lorg/jetbrains/kotlin/psi/KtParameter;", "getValueParameterType", "contextParameterListType", "Lorg/jetbrains/kotlin/psi/KtContextParameterList;", "getContextParameterListType", "contextReceiverType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinContextReceiverStub;", "Lorg/jetbrains/kotlin/psi/KtContextReceiver;", "getContextReceiverType", "typeParameterListType", "Lorg/jetbrains/kotlin/psi/KtTypeParameterList;", "getTypeParameterListType", "typeParameterType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinTypeParameterStub;", "Lorg/jetbrains/kotlin/psi/KtTypeParameter;", "getTypeParameterType", "typeConstraintListType", "Lorg/jetbrains/kotlin/psi/KtTypeConstraintList;", "getTypeConstraintListType", "typeConstraintType", "Lorg/jetbrains/kotlin/psi/KtTypeConstraint;", "getTypeConstraintType", "superTypeListType", "Lorg/jetbrains/kotlin/psi/KtSuperTypeList;", "getSuperTypeListType", "delegatedSuperTypeEntryType", "Lorg/jetbrains/kotlin/psi/KtDelegatedSuperTypeEntry;", "getDelegatedSuperTypeEntryType", "superTypeCallEntryType", "Lorg/jetbrains/kotlin/psi/KtSuperTypeCallEntry;", "getSuperTypeCallEntryType", "superTypeEntryType", "Lorg/jetbrains/kotlin/psi/KtSuperTypeEntry;", "getSuperTypeEntryType", "modifierListType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinModifierListStub;", "Lorg/jetbrains/kotlin/psi/KtDeclarationModifierList;", "getModifierListType", "annotationType", "Lorg/jetbrains/kotlin/psi/KtAnnotation;", "getAnnotationType", "annotationEntryType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinAnnotationEntryStub;", "Lorg/jetbrains/kotlin/psi/KtAnnotationEntry;", "getAnnotationEntryType", "annotationTargetType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinAnnotationUseSiteTargetStub;", "Lorg/jetbrains/kotlin/psi/KtAnnotationUseSiteTarget;", "getAnnotationTargetType", "typeReferenceType", "Lorg/jetbrains/kotlin/psi/KtTypeReference;", "getTypeReferenceType", "userTypeType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinUserTypeStub;", "Lorg/jetbrains/kotlin/psi/KtUserType;", "getUserTypeType", "dynamicTypeType", "Lorg/jetbrains/kotlin/psi/KtDynamicType;", "getDynamicTypeType", "functionTypeType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinFunctionTypeStub;", "Lorg/jetbrains/kotlin/psi/KtFunctionType;", "getFunctionTypeType", "functionTypeReceiverType", "Lorg/jetbrains/kotlin/psi/KtFunctionTypeReceiver;", "getFunctionTypeReceiverType", "nullableTypeType", "Lorg/jetbrains/kotlin/psi/KtNullableType;", "getNullableTypeType", "intersectionTypeType", "Lorg/jetbrains/kotlin/psi/KtIntersectionType;", "getIntersectionTypeType", "typeProjectionType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinTypeProjectionStub;", "Lorg/jetbrains/kotlin/psi/KtTypeProjection;", "getTypeProjectionType", "nullType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinConstantExpressionStub;", "Lorg/jetbrains/kotlin/psi/KtConstantExpression;", "getNullType", "booleanConstantType", "getBooleanConstantType", "floatConstantType", "getFloatConstantType", "characterConstantType", "getCharacterConstantType", "integerConstantType", "getIntegerConstantType", "stringTemplateType", "Lorg/jetbrains/kotlin/psi/KtStringTemplateExpression;", "getStringTemplateType", "longStringTemplateEntryType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinBlockStringTemplateEntryStub;", "Lorg/jetbrains/kotlin/psi/KtBlockStringTemplateEntry;", "getLongStringTemplateEntryType", "shortStringTemplateEntryType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinPlaceHolderWithTextStub;", "Lorg/jetbrains/kotlin/psi/KtSimpleNameStringTemplateEntry;", "getShortStringTemplateEntryType", "literalStringTemplateEntryType", "Lorg/jetbrains/kotlin/psi/KtLiteralStringTemplateEntry;", "getLiteralStringTemplateEntryType", "escapeStringTemplateEntryType", "Lorg/jetbrains/kotlin/psi/KtEscapeStringTemplateEntry;", "getEscapeStringTemplateEntryType", "stringInterpolationPrefixType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinStringInterpolationPrefixStub;", "Lorg/jetbrains/kotlin/psi/KtStringInterpolationPrefix;", "getStringInterpolationPrefixType", "blockExpressionType", "Lcom/intellij/psi/tree/IElementType;", "getBlockExpressionType", "()Lcom/intellij/psi/tree/IElementType;", "lambdaExpressionType", "getLambdaExpressionType", "referenceExpressionType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinNameReferenceExpressionStub;", "Lorg/jetbrains/kotlin/psi/KtNameReferenceExpression;", "getReferenceExpressionType", "enumEntrySuperclassReferenceExpressionType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinEnumEntrySuperclassReferenceExpressionStub;", "Lorg/jetbrains/kotlin/psi/KtEnumEntrySuperclassReferenceExpression;", "getEnumEntrySuperclassReferenceExpressionType", "dotQualifiedExpressionType", "Lorg/jetbrains/kotlin/psi/KtDotQualifiedExpression;", "getDotQualifiedExpressionType", "callExpressionType", "Lorg/jetbrains/kotlin/psi/KtCallExpression;", "getCallExpressionType", "classLiteralExpressionType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinClassLiteralExpressionStub;", "Lorg/jetbrains/kotlin/psi/KtClassLiteralExpression;", "getClassLiteralExpressionType", "collectionLiteralExpressionType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinCollectionLiteralExpressionStub;", "Lorg/jetbrains/kotlin/psi/KtCollectionLiteralExpression;", "getCollectionLiteralExpressionType", "typeArgumentListType", "Lorg/jetbrains/kotlin/psi/KtTypeArgumentList;", "getTypeArgumentListType", "valueArgumentListType", "Lorg/jetbrains/kotlin/psi/KtValueArgumentList;", "getValueArgumentListType", "valueArgumentType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinValueArgumentStub;", "Lorg/jetbrains/kotlin/psi/KtValueArgument;", "getValueArgumentType", "contractEffectListType", "Lorg/jetbrains/kotlin/psi/KtContractEffectList;", "getContractEffectListType", "contractEffectType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinContractEffectStub;", "Lorg/jetbrains/kotlin/psi/KtContractEffect;", "getContractEffectType", "lambdaArgumentType", "Lorg/jetbrains/kotlin/psi/KtLambdaArgument;", "getLambdaArgumentType", "valueArgumentNameType", "Lorg/jetbrains/kotlin/psi/KtValueArgumentName;", "getValueArgumentNameType", "packageDirectiveType", "Lorg/jetbrains/kotlin/psi/KtPackageDirective;", "getPackageDirectiveType", "fileAnnotationListType", "Lorg/jetbrains/kotlin/psi/KtFileAnnotationList;", "getFileAnnotationListType", "importListType", "Lorg/jetbrains/kotlin/psi/KtImportList;", "getImportListType", "importDirectiveType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinImportDirectiveStub;", "Lorg/jetbrains/kotlin/psi/KtImportDirective;", "getImportDirectiveType", "importAliasType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinImportAliasStub;", "Lorg/jetbrains/kotlin/psi/KtImportAlias;", "getImportAliasType", "scriptType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinScriptStub;", "Lorg/jetbrains/kotlin/psi/KtScript;", "getScriptType", "expressionCodeFragmentType", "Lcom/intellij/psi/impl/source/tree/ICodeFragmentElementType;", "getExpressionCodeFragmentType", "()Lcom/intellij/psi/impl/source/tree/ICodeFragmentElementType;", "blockCodeFragmentType", "getBlockCodeFragmentType", "typeCodeFragmentType", "getTypeCodeFragmentType", "kdocType", "Lcom/intellij/psi/tree/ILazyParseableElementType;", "getKdocType", "()Lcom/intellij/psi/tree/ILazyParseableElementType;", "kdocMarkdownLinkType", "getKdocMarkdownLinkType", "org.jetbrains.kotlin:psi-impl", "Lorg/jetbrains/kotlin/psi/KtImplementationDetail;"}, k = 1, mv = {2, 4, 0}, xi = 48)
@KtImplementationDetail
public final class KotlinElementTypeProviderImpl implements KotlinElementTypeProvider {
    private static final ILazyParseableElementType kdocMarkdownLinkType;
    private static final ILazyParseableElementType kdocType;
    public static final KotlinElementTypeProviderImpl INSTANCE = new KotlinElementTypeProviderImpl();
    private static final IElementType blockExpressionType = new BlockExpressionElementType();
    private static final IElementType lambdaExpressionType = new LambdaExpressionElementType();

    static {
        KotlinLanguage kotlinLanguage = KotlinLanguage.INSTANCE;
        kdocType = new kdocType.1(kotlinLanguage);
        kdocMarkdownLinkType = new kdocMarkdownLinkType.1(kotlinLanguage);
    }

    private KotlinElementTypeProviderImpl() {
    }

    @KtExperimentalApi
    public static /* synthetic */ void getCompanionBlockType$annotations() {
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinAnnotationEntryStub, KtAnnotationEntry> getAnnotationEntryType() {
        KtAnnotationEntryElementType ktAnnotationEntryElementType = KtStubElementTypes.ANNOTATION_ENTRY;
        ktAnnotationEntryElementType.getClass();
        return ktAnnotationEntryElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinAnnotationUseSiteTargetStub, KtAnnotationUseSiteTarget> getAnnotationTargetType() {
        KtAnnotationUseSiteTargetElementType ktAnnotationUseSiteTargetElementType = KtStubElementTypes.ANNOTATION_TARGET;
        ktAnnotationUseSiteTargetElementType.getClass();
        return ktAnnotationUseSiteTargetElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinPlaceHolderStub<KtAnnotation>, KtAnnotation> getAnnotationType() {
        KtPlaceHolderStubElementType ktPlaceHolderStubElementType = KtStubElementTypes.ANNOTATION;
        ktPlaceHolderStubElementType.getClass();
        return ktPlaceHolderStubElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinBackingFieldStub, KtBackingField> getBackingFieldType() {
        KtBackingFieldElementType ktBackingFieldElementType = KtStubElementTypes.BACKING_FIELD;
        ktBackingFieldElementType.getClass();
        return ktBackingFieldElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public ICodeFragmentElementType getBlockCodeFragmentType() {
        KtBlockCodeFragmentType ktBlockCodeFragmentType = KtStubElementTypes.BLOCK_CODE_FRAGMENT;
        ktBlockCodeFragmentType.getClass();
        return ktBlockCodeFragmentType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public IElementType getBlockExpressionType() {
        return blockExpressionType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinConstantExpressionStub, KtConstantExpression> getBooleanConstantType() {
        KtConstantExpressionElementType ktConstantExpressionElementType = KtStubElementTypes.BOOLEAN_CONSTANT;
        ktConstantExpressionElementType.getClass();
        return ktConstantExpressionElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinPlaceHolderStub<KtCallExpression>, KtCallExpression> getCallExpressionType() {
        KtPlaceHolderStubElementType ktPlaceHolderStubElementType = KtStubElementTypes.CALL_EXPRESSION;
        ktPlaceHolderStubElementType.getClass();
        return ktPlaceHolderStubElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinConstantExpressionStub, KtConstantExpression> getCharacterConstantType() {
        KtConstantExpressionElementType ktConstantExpressionElementType = KtStubElementTypes.CHARACTER_CONSTANT;
        ktConstantExpressionElementType.getClass();
        return ktConstantExpressionElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinPlaceHolderStub<KtClassBody>, KtClassBody> getClassBodyType() {
        KtPlaceHolderStubElementType ktPlaceHolderStubElementType = KtStubElementTypes.CLASS_BODY;
        ktPlaceHolderStubElementType.getClass();
        return ktPlaceHolderStubElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinPlaceHolderStub<KtClassInitializer>, KtClassInitializer> getClassInitializerType() {
        KtPlaceHolderStubElementType ktPlaceHolderStubElementType = KtStubElementTypes.CLASS_INITIALIZER;
        ktPlaceHolderStubElementType.getClass();
        return ktPlaceHolderStubElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinClassLiteralExpressionStub, KtClassLiteralExpression> getClassLiteralExpressionType() {
        KtClassLiteralExpressionElementType ktClassLiteralExpressionElementType = KtStubElementTypes.CLASS_LITERAL_EXPRESSION;
        ktClassLiteralExpressionElementType.getClass();
        return ktClassLiteralExpressionElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinClassStub, KtClass> getClassType() {
        KtClassElementType ktClassElementType = KtStubElementTypes.CLASS;
        ktClassElementType.getClass();
        return ktClassElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinCollectionLiteralExpressionStub, KtCollectionLiteralExpression> getCollectionLiteralExpressionType() {
        KtCollectionLiteralExpressionElementType ktCollectionLiteralExpressionElementType = KtStubElementTypes.COLLECTION_LITERAL_EXPRESSION;
        ktCollectionLiteralExpressionElementType.getClass();
        return ktCollectionLiteralExpressionElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinPlaceHolderStub<KtCompanionBlock>, KtCompanionBlock> getCompanionBlockType() {
        KtPlaceHolderStubElementType ktPlaceHolderStubElementType = KtStubElementTypes.COMPANION_BLOCK;
        ktPlaceHolderStubElementType.getClass();
        return ktPlaceHolderStubElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinPlaceHolderStub<KtConstructorCalleeExpression>, KtConstructorCalleeExpression> getConstructorCalleeType() {
        KtPlaceHolderStubElementType ktPlaceHolderStubElementType = KtStubElementTypes.CONSTRUCTOR_CALLEE;
        ktPlaceHolderStubElementType.getClass();
        return ktPlaceHolderStubElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinPlaceHolderStub<KtContextParameterList>, KtContextParameterList> getContextParameterListType() {
        KtPlaceHolderStubElementType ktPlaceHolderStubElementType = KtStubElementTypes.CONTEXT_PARAMETER_LIST;
        ktPlaceHolderStubElementType.getClass();
        return ktPlaceHolderStubElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinContextReceiverStub, KtContextReceiver> getContextReceiverType() {
        KtContextReceiverElementType ktContextReceiverElementType = KtStubElementTypes.CONTEXT_RECEIVER;
        ktContextReceiverElementType.getClass();
        return ktContextReceiverElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinPlaceHolderStub<KtContractEffectList>, KtContractEffectList> getContractEffectListType() {
        KtPlaceHolderStubElementType ktPlaceHolderStubElementType = KtStubElementTypes.CONTRACT_EFFECT_LIST;
        ktPlaceHolderStubElementType.getClass();
        return ktPlaceHolderStubElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinContractEffectStub, KtContractEffect> getContractEffectType() {
        KtContractEffectElementType ktContractEffectElementType = KtStubElementTypes.CONTRACT_EFFECT;
        ktContractEffectElementType.getClass();
        return ktContractEffectElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinPlaceHolderStub<KtDelegatedSuperTypeEntry>, KtDelegatedSuperTypeEntry> getDelegatedSuperTypeEntryType() {
        KtPlaceHolderStubElementType ktPlaceHolderStubElementType = KtStubElementTypes.DELEGATED_SUPER_TYPE_ENTRY;
        ktPlaceHolderStubElementType.getClass();
        return ktPlaceHolderStubElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinPlaceHolderStub<KtDotQualifiedExpression>, KtDotQualifiedExpression> getDotQualifiedExpressionType() {
        KtDotQualifiedExpressionElementType ktDotQualifiedExpressionElementType = KtStubElementTypes.DOT_QUALIFIED_EXPRESSION;
        ktDotQualifiedExpressionElementType.getClass();
        return ktDotQualifiedExpressionElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinPlaceHolderStub<KtDynamicType>, KtDynamicType> getDynamicTypeType() {
        KtPlaceHolderStubElementType ktPlaceHolderStubElementType = KtStubElementTypes.DYNAMIC_TYPE;
        ktPlaceHolderStubElementType.getClass();
        return ktPlaceHolderStubElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinEnumEntrySuperclassReferenceExpressionStub, KtEnumEntrySuperclassReferenceExpression> getEnumEntrySuperclassReferenceExpressionType() {
        KtEnumEntrySuperClassReferenceExpressionElementType ktEnumEntrySuperClassReferenceExpressionElementType = KtStubElementTypes.ENUM_ENTRY_SUPERCLASS_REFERENCE_EXPRESSION;
        ktEnumEntrySuperClassReferenceExpressionElementType.getClass();
        return ktEnumEntrySuperClassReferenceExpressionElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinClassStub, KtEnumEntry> getEnumEntryType() {
        KtEnumEntryElementType ktEnumEntryElementType = KtStubElementTypes.ENUM_ENTRY;
        ktEnumEntryElementType.getClass();
        return ktEnumEntryElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinPlaceHolderWithTextStub<KtEscapeStringTemplateEntry>, KtEscapeStringTemplateEntry> getEscapeStringTemplateEntryType() {
        KtPlaceHolderWithTextStubElementType ktPlaceHolderWithTextStubElementType = KtStubElementTypes.ESCAPE_STRING_TEMPLATE_ENTRY;
        ktPlaceHolderWithTextStubElementType.getClass();
        return ktPlaceHolderWithTextStubElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public ICodeFragmentElementType getExpressionCodeFragmentType() {
        KtExpressionCodeFragmentType ktExpressionCodeFragmentType = KtStubElementTypes.EXPRESSION_CODE_FRAGMENT;
        ktExpressionCodeFragmentType.getClass();
        return ktExpressionCodeFragmentType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinPlaceHolderStub<KtFileAnnotationList>, KtFileAnnotationList> getFileAnnotationListType() {
        KtPlaceHolderStubElementType ktPlaceHolderStubElementType = KtStubElementTypes.FILE_ANNOTATION_LIST;
        ktPlaceHolderStubElementType.getClass();
        return ktPlaceHolderStubElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public IFileElementType getFileType() {
        return KtFileElementType.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinConstantExpressionStub, KtConstantExpression> getFloatConstantType() {
        KtConstantExpressionElementType ktConstantExpressionElementType = KtStubElementTypes.FLOAT_CONSTANT;
        ktConstantExpressionElementType.getClass();
        return ktConstantExpressionElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinFunctionStub, KtNamedFunction> getFunctionType() {
        KtFunctionElementType ktFunctionElementType = KtStubElementTypes.FUNCTION;
        ktFunctionElementType.getClass();
        return ktFunctionElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinPlaceHolderStub<KtFunctionTypeReceiver>, KtFunctionTypeReceiver> getFunctionTypeReceiverType() {
        KtPlaceHolderStubElementType ktPlaceHolderStubElementType = KtStubElementTypes.FUNCTION_TYPE_RECEIVER;
        ktPlaceHolderStubElementType.getClass();
        return ktPlaceHolderStubElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinFunctionTypeStub, KtFunctionType> getFunctionTypeType() {
        KtFunctionTypeElementType ktFunctionTypeElementType = KtStubElementTypes.FUNCTION_TYPE;
        ktFunctionTypeElementType.getClass();
        return ktFunctionTypeElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinImportAliasStub, KtImportAlias> getImportAliasType() {
        KtImportAliasElementType ktImportAliasElementType = KtStubElementTypes.IMPORT_ALIAS;
        ktImportAliasElementType.getClass();
        return ktImportAliasElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinImportDirectiveStub, KtImportDirective> getImportDirectiveType() {
        KtImportDirectiveElementType ktImportDirectiveElementType = KtStubElementTypes.IMPORT_DIRECTIVE;
        ktImportDirectiveElementType.getClass();
        return ktImportDirectiveElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinPlaceHolderStub<KtImportList>, KtImportList> getImportListType() {
        KtPlaceHolderStubElementType ktPlaceHolderStubElementType = KtStubElementTypes.IMPORT_LIST;
        ktPlaceHolderStubElementType.getClass();
        return ktPlaceHolderStubElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinPlaceHolderStub<KtInitializerList>, KtInitializerList> getInitializerListType() {
        KtPlaceHolderStubElementType ktPlaceHolderStubElementType = KtStubElementTypes.INITIALIZER_LIST;
        ktPlaceHolderStubElementType.getClass();
        return ktPlaceHolderStubElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinConstantExpressionStub, KtConstantExpression> getIntegerConstantType() {
        KtConstantExpressionElementType ktConstantExpressionElementType = KtStubElementTypes.INTEGER_CONSTANT;
        ktConstantExpressionElementType.getClass();
        return ktConstantExpressionElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinPlaceHolderStub<KtIntersectionType>, KtIntersectionType> getIntersectionTypeType() {
        KtPlaceHolderStubElementType ktPlaceHolderStubElementType = KtStubElementTypes.INTERSECTION_TYPE;
        ktPlaceHolderStubElementType.getClass();
        return ktPlaceHolderStubElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public ILazyParseableElementType getKdocMarkdownLinkType() {
        return kdocMarkdownLinkType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public ILazyParseableElementType getKdocType() {
        return kdocType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinValueArgumentStub<KtLambdaArgument>, KtLambdaArgument> getLambdaArgumentType() {
        KtValueArgumentElementType ktValueArgumentElementType = KtStubElementTypes.LAMBDA_ARGUMENT;
        ktValueArgumentElementType.getClass();
        return ktValueArgumentElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public IElementType getLambdaExpressionType() {
        return lambdaExpressionType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinPlaceHolderWithTextStub<KtLiteralStringTemplateEntry>, KtLiteralStringTemplateEntry> getLiteralStringTemplateEntryType() {
        KtPlaceHolderWithTextStubElementType ktPlaceHolderWithTextStubElementType = KtStubElementTypes.LITERAL_STRING_TEMPLATE_ENTRY;
        ktPlaceHolderWithTextStubElementType.getClass();
        return ktPlaceHolderWithTextStubElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinBlockStringTemplateEntryStub, KtBlockStringTemplateEntry> getLongStringTemplateEntryType() {
        KtBlockStringTemplateEntryElementType ktBlockStringTemplateEntryElementType = KtStubElementTypes.LONG_STRING_TEMPLATE_ENTRY;
        ktBlockStringTemplateEntryElementType.getClass();
        return ktBlockStringTemplateEntryElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinModifierListStub, KtDeclarationModifierList> getModifierListType() {
        KtModifierListElementType ktModifierListElementType = KtStubElementTypes.MODIFIER_LIST;
        ktModifierListElementType.getClass();
        return ktModifierListElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinConstantExpressionStub, KtConstantExpression> getNullType() {
        KtConstantExpressionElementType ktConstantExpressionElementType = KtStubElementTypes.NULL;
        ktConstantExpressionElementType.getClass();
        return ktConstantExpressionElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinPlaceHolderStub<KtNullableType>, KtNullableType> getNullableTypeType() {
        KtPlaceHolderStubElementType ktPlaceHolderStubElementType = KtStubElementTypes.NULLABLE_TYPE;
        ktPlaceHolderStubElementType.getClass();
        return ktPlaceHolderStubElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinObjectStub, KtObjectDeclaration> getObjectType() {
        KtObjectElementType ktObjectElementType = KtStubElementTypes.OBJECT_DECLARATION;
        ktObjectElementType.getClass();
        return ktObjectElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinPlaceHolderStub<KtPackageDirective>, KtPackageDirective> getPackageDirectiveType() {
        KtPlaceHolderStubElementType ktPlaceHolderStubElementType = KtStubElementTypes.PACKAGE_DIRECTIVE;
        ktPlaceHolderStubElementType.getClass();
        return ktPlaceHolderStubElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinConstructorStub<KtPrimaryConstructor>, KtPrimaryConstructor> getPrimaryConstructorType() {
        KtPrimaryConstructorElementType ktPrimaryConstructorElementType = KtStubElementTypes.PRIMARY_CONSTRUCTOR;
        ktPrimaryConstructorElementType.getClass();
        return ktPrimaryConstructorElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinPropertyAccessorStub, KtPropertyAccessor> getPropertyAccessorType() {
        KtPropertyAccessorElementType ktPropertyAccessorElementType = KtStubElementTypes.PROPERTY_ACCESSOR;
        ktPropertyAccessorElementType.getClass();
        return ktPropertyAccessorElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinPropertyStub, KtProperty> getPropertyType() {
        KtPropertyElementType ktPropertyElementType = KtStubElementTypes.PROPERTY;
        ktPropertyElementType.getClass();
        return ktPropertyElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinNameReferenceExpressionStub, KtNameReferenceExpression> getReferenceExpressionType() {
        KtNameReferenceExpressionElementType ktNameReferenceExpressionElementType = KtStubElementTypes.REFERENCE_EXPRESSION;
        ktNameReferenceExpressionElementType.getClass();
        return ktNameReferenceExpressionElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinPlaceHolderStub<KtScriptInitializer>, KtScriptInitializer> getScriptInitializerType() {
        KtPlaceHolderStubElementType ktPlaceHolderStubElementType = KtStubElementTypes.SCRIPT_INITIALIZER;
        ktPlaceHolderStubElementType.getClass();
        return ktPlaceHolderStubElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinScriptStub, KtScript> getScriptType() {
        KtScriptElementType ktScriptElementType = KtStubElementTypes.SCRIPT;
        ktScriptElementType.getClass();
        return ktScriptElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinConstructorStub<KtSecondaryConstructor>, KtSecondaryConstructor> getSecondaryConstructorType() {
        KtSecondaryConstructorElementType ktSecondaryConstructorElementType = KtStubElementTypes.SECONDARY_CONSTRUCTOR;
        ktSecondaryConstructorElementType.getClass();
        return ktSecondaryConstructorElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinPlaceHolderWithTextStub<KtSimpleNameStringTemplateEntry>, KtSimpleNameStringTemplateEntry> getShortStringTemplateEntryType() {
        KtPlaceHolderWithTextStubElementType ktPlaceHolderWithTextStubElementType = KtStubElementTypes.SHORT_STRING_TEMPLATE_ENTRY;
        ktPlaceHolderWithTextStubElementType.getClass();
        return ktPlaceHolderWithTextStubElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinStringInterpolationPrefixStub, KtStringInterpolationPrefix> getStringInterpolationPrefixType() {
        KtStringInterpolationPrefixElementType ktStringInterpolationPrefixElementType = KtStubElementTypes.STRING_INTERPOLATION_PREFIX;
        ktStringInterpolationPrefixElementType.getClass();
        return ktStringInterpolationPrefixElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinPlaceHolderStub<KtStringTemplateExpression>, KtStringTemplateExpression> getStringTemplateType() {
        KtPlaceHolderStubElementType ktPlaceHolderStubElementType = KtStubElementTypes.STRING_TEMPLATE;
        ktPlaceHolderStubElementType.getClass();
        return ktPlaceHolderStubElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinPlaceHolderStub<KtSuperTypeCallEntry>, KtSuperTypeCallEntry> getSuperTypeCallEntryType() {
        KtPlaceHolderStubElementType ktPlaceHolderStubElementType = KtStubElementTypes.SUPER_TYPE_CALL_ENTRY;
        ktPlaceHolderStubElementType.getClass();
        return ktPlaceHolderStubElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinPlaceHolderStub<KtSuperTypeEntry>, KtSuperTypeEntry> getSuperTypeEntryType() {
        KtPlaceHolderStubElementType ktPlaceHolderStubElementType = KtStubElementTypes.SUPER_TYPE_ENTRY;
        ktPlaceHolderStubElementType.getClass();
        return ktPlaceHolderStubElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinPlaceHolderStub<KtSuperTypeList>, KtSuperTypeList> getSuperTypeListType() {
        KtPlaceHolderStubElementType ktPlaceHolderStubElementType = KtStubElementTypes.SUPER_TYPE_LIST;
        ktPlaceHolderStubElementType.getClass();
        return ktPlaceHolderStubElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinTypeAliasStub, KtTypeAlias> getTypeAliasType() {
        KtTypeAliasElementType ktTypeAliasElementType = KtStubElementTypes.TYPEALIAS;
        ktTypeAliasElementType.getClass();
        return ktTypeAliasElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinPlaceHolderStub<KtTypeArgumentList>, KtTypeArgumentList> getTypeArgumentListType() {
        KtPlaceHolderStubElementType ktPlaceHolderStubElementType = KtStubElementTypes.TYPE_ARGUMENT_LIST;
        ktPlaceHolderStubElementType.getClass();
        return ktPlaceHolderStubElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public ICodeFragmentElementType getTypeCodeFragmentType() {
        KtTypeCodeFragmentType ktTypeCodeFragmentType = KtStubElementTypes.TYPE_CODE_FRAGMENT;
        ktTypeCodeFragmentType.getClass();
        return ktTypeCodeFragmentType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinPlaceHolderStub<KtTypeConstraintList>, KtTypeConstraintList> getTypeConstraintListType() {
        KtPlaceHolderStubElementType ktPlaceHolderStubElementType = KtStubElementTypes.TYPE_CONSTRAINT_LIST;
        ktPlaceHolderStubElementType.getClass();
        return ktPlaceHolderStubElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinPlaceHolderStub<KtTypeConstraint>, KtTypeConstraint> getTypeConstraintType() {
        KtPlaceHolderStubElementType ktPlaceHolderStubElementType = KtStubElementTypes.TYPE_CONSTRAINT;
        ktPlaceHolderStubElementType.getClass();
        return ktPlaceHolderStubElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinPlaceHolderStub<KtTypeParameterList>, KtTypeParameterList> getTypeParameterListType() {
        KtPlaceHolderStubElementType ktPlaceHolderStubElementType = KtStubElementTypes.TYPE_PARAMETER_LIST;
        ktPlaceHolderStubElementType.getClass();
        return ktPlaceHolderStubElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinTypeParameterStub, KtTypeParameter> getTypeParameterType() {
        KtTypeParameterElementType ktTypeParameterElementType = KtStubElementTypes.TYPE_PARAMETER;
        ktTypeParameterElementType.getClass();
        return ktTypeParameterElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinTypeProjectionStub, KtTypeProjection> getTypeProjectionType() {
        KtTypeProjectionElementType ktTypeProjectionElementType = KtStubElementTypes.TYPE_PROJECTION;
        ktTypeProjectionElementType.getClass();
        return ktTypeProjectionElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinPlaceHolderStub<KtTypeReference>, KtTypeReference> getTypeReferenceType() {
        KtPlaceHolderStubElementType ktPlaceHolderStubElementType = KtStubElementTypes.TYPE_REFERENCE;
        ktPlaceHolderStubElementType.getClass();
        return ktPlaceHolderStubElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinUserTypeStub, KtUserType> getUserTypeType() {
        KtUserTypeElementType ktUserTypeElementType = KtStubElementTypes.USER_TYPE;
        ktUserTypeElementType.getClass();
        return ktUserTypeElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinPlaceHolderStub<KtValueArgumentList>, KtValueArgumentList> getValueArgumentListType() {
        KtPlaceHolderStubElementType ktPlaceHolderStubElementType = KtStubElementTypes.VALUE_ARGUMENT_LIST;
        ktPlaceHolderStubElementType.getClass();
        return ktPlaceHolderStubElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinPlaceHolderStub<KtValueArgumentName>, KtValueArgumentName> getValueArgumentNameType() {
        KtPlaceHolderStubElementType ktPlaceHolderStubElementType = KtStubElementTypes.VALUE_ARGUMENT_NAME;
        ktPlaceHolderStubElementType.getClass();
        return ktPlaceHolderStubElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinValueArgumentStub<KtValueArgument>, KtValueArgument> getValueArgumentType() {
        KtValueArgumentElementType ktValueArgumentElementType = KtStubElementTypes.VALUE_ARGUMENT;
        ktValueArgumentElementType.getClass();
        return ktValueArgumentElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinPlaceHolderStub<KtParameterList>, KtParameterList> getValueParameterListType() {
        KtPlaceHolderStubElementType ktPlaceHolderStubElementType = KtStubElementTypes.VALUE_PARAMETER_LIST;
        ktPlaceHolderStubElementType.getClass();
        return ktPlaceHolderStubElementType;
    }

    @Override // org.jetbrains.kotlin.KotlinElementTypeProvider
    public KtStubElementType<? extends KotlinParameterStub, KtParameter> getValueParameterType() {
        KtParameterElementType ktParameterElementType = KtStubElementTypes.VALUE_PARAMETER;
        ktParameterElementType.getClass();
        return ktParameterElementType;
    }
}
