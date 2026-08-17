package org.jetbrains.kotlin;

import com.intellij.psi.impl.source.tree.ICodeFragmentElementType;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.ILazyParseableElementType;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.KotlinElementTypeProvider;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.load.java.JvmAbi;
import org.jetbrains.kotlin.psi.KtAnnotation;
import org.jetbrains.kotlin.psi.KtAnnotationEntry;
import org.jetbrains.kotlin.psi.KtAnnotationUseSiteTarget;
import org.jetbrains.kotlin.psi.KtBackingField;
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
import org.jetbrains.kotlin.psi.impl.KotlinElementTypeProviderImpl;
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
import org.jetbrains.kotlin.psi.stubs.elements.KtStubElementType;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000Ä\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\bg\u0018\u0000 \u008c\u00022\u00020\u0001:\u0002\u008c\u0002R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R \u0010\u0006\u001a\u0010\u0012\u0006\b\u0001\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR \u0010\f\u001a\u0010\u0012\u0006\b\u0001\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u000bR \u0010\u0010\u001a\u0010\u0012\u0006\b\u0001\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u000bR&\u0010\u0014\u001a\u0016\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00020\u00160\u0015\u0012\u0004\u0012\u00020\u00160\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u000bR2\u0010\u0018\u001a\u0016\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00020\u00190\u0015\u0012\u0004\u0012\u00020\u00190\u00078&X§\u0004r\u0002\b\u001d¢\u0006\f\u0012\u0004\b\u001a\u0010\u001b\u001a\u0004\b\u001c\u0010\u000bR&\u0010\u001e\u001a\u0016\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00020\u001f0\u0015\u0012\u0004\u0012\u00020\u001f0\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b \u0010\u000bR&\u0010!\u001a\u0016\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00020\"0\u0015\u0012\u0004\u0012\u00020\"0\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b#\u0010\u000bR \u0010$\u001a\u0010\u0012\u0006\b\u0001\u0012\u00020%\u0012\u0004\u0012\u00020&0\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b'\u0010\u000bR \u0010(\u001a\u0010\u0012\u0006\b\u0001\u0012\u00020)\u0012\u0004\u0012\u00020*0\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b+\u0010\u000bR \u0010,\u001a\u0010\u0012\u0006\b\u0001\u0012\u00020\b\u0012\u0004\u0012\u00020-0\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b.\u0010\u000bR&\u0010/\u001a\u0016\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00020100\u0012\u0004\u0012\u0002010\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b2\u0010\u000bR&\u00103\u001a\u0016\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00020400\u0012\u0004\u0012\u0002040\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b5\u0010\u000bR&\u00106\u001a\u0016\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u0002070\u0015\u0012\u0004\u0012\u0002070\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b8\u0010\u000bR \u00109\u001a\u0010\u0012\u0006\b\u0001\u0012\u00020:\u0012\u0004\u0012\u00020;0\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b<\u0010\u000bR \u0010=\u001a\u0010\u0012\u0006\b\u0001\u0012\u00020>\u0012\u0004\u0012\u00020?0\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b@\u0010\u000bR&\u0010A\u001a\u0016\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00020B0\u0015\u0012\u0004\u0012\u00020B0\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\bC\u0010\u000bR&\u0010D\u001a\u0016\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00020E0\u0015\u0012\u0004\u0012\u00020E0\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\bF\u0010\u000bR \u0010G\u001a\u0010\u0012\u0006\b\u0001\u0012\u00020H\u0012\u0004\u0012\u00020I0\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\bJ\u0010\u000bR&\u0010K\u001a\u0016\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00020L0\u0015\u0012\u0004\u0012\u00020L0\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\bM\u0010\u000bR \u0010N\u001a\u0010\u0012\u0006\b\u0001\u0012\u00020O\u0012\u0004\u0012\u00020P0\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\bQ\u0010\u000bR&\u0010R\u001a\u0016\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00020S0\u0015\u0012\u0004\u0012\u00020S0\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\bT\u0010\u000bR \u0010U\u001a\u0010\u0012\u0006\b\u0001\u0012\u00020V\u0012\u0004\u0012\u00020W0\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\bX\u0010\u000bR&\u0010Y\u001a\u0016\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00020Z0\u0015\u0012\u0004\u0012\u00020Z0\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b[\u0010\u000bR&\u0010\\\u001a\u0016\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00020]0\u0015\u0012\u0004\u0012\u00020]0\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b^\u0010\u000bR&\u0010_\u001a\u0016\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00020`0\u0015\u0012\u0004\u0012\u00020`0\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\ba\u0010\u000bR&\u0010b\u001a\u0016\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00020c0\u0015\u0012\u0004\u0012\u00020c0\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\bd\u0010\u000bR&\u0010e\u001a\u0016\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00020f0\u0015\u0012\u0004\u0012\u00020f0\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\bg\u0010\u000bR&\u0010h\u001a\u0016\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00020i0\u0015\u0012\u0004\u0012\u00020i0\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\bj\u0010\u000bR \u0010k\u001a\u0010\u0012\u0006\b\u0001\u0012\u00020l\u0012\u0004\u0012\u00020m0\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\bn\u0010\u000bR&\u0010o\u001a\u0016\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00020p0\u0015\u0012\u0004\u0012\u00020p0\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\bq\u0010\u000bR \u0010r\u001a\u0010\u0012\u0006\b\u0001\u0012\u00020s\u0012\u0004\u0012\u00020t0\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\bu\u0010\u000bR \u0010v\u001a\u0010\u0012\u0006\b\u0001\u0012\u00020w\u0012\u0004\u0012\u00020x0\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\by\u0010\u000bR&\u0010z\u001a\u0016\u0012\f\b\u0001\u0012\b\u0012\u0004\u0012\u00020{0\u0015\u0012\u0004\u0012\u00020{0\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b|\u0010\u000bR!\u0010}\u001a\u0010\u0012\u0006\b\u0001\u0012\u00020~\u0012\u0004\u0012\u00020\u007f0\u0007X¦\u0004¢\u0006\u0007\u001a\u0005\b\u0080\u0001\u0010\u000bR*\u0010\u0081\u0001\u001a\u0018\u0012\r\b\u0001\u0012\t\u0012\u0005\u0012\u00030\u0082\u00010\u0015\u0012\u0005\u0012\u00030\u0082\u00010\u0007X¦\u0004¢\u0006\u0007\u001a\u0005\b\u0083\u0001\u0010\u000bR$\u0010\u0084\u0001\u001a\u0012\u0012\u0007\b\u0001\u0012\u00030\u0085\u0001\u0012\u0005\u0012\u00030\u0086\u00010\u0007X¦\u0004¢\u0006\u0007\u001a\u0005\b\u0087\u0001\u0010\u000bR*\u0010\u0088\u0001\u001a\u0018\u0012\r\b\u0001\u0012\t\u0012\u0005\u0012\u00030\u0089\u00010\u0015\u0012\u0005\u0012\u00030\u0089\u00010\u0007X¦\u0004¢\u0006\u0007\u001a\u0005\b\u008a\u0001\u0010\u000bR*\u0010\u008b\u0001\u001a\u0018\u0012\r\b\u0001\u0012\t\u0012\u0005\u0012\u00030\u008c\u00010\u0015\u0012\u0005\u0012\u00030\u008c\u00010\u0007X¦\u0004¢\u0006\u0007\u001a\u0005\b\u008d\u0001\u0010\u000bR*\u0010\u008e\u0001\u001a\u0018\u0012\r\b\u0001\u0012\t\u0012\u0005\u0012\u00030\u008f\u00010\u0015\u0012\u0005\u0012\u00030\u008f\u00010\u0007X¦\u0004¢\u0006\u0007\u001a\u0005\b\u0090\u0001\u0010\u000bR$\u0010\u0091\u0001\u001a\u0012\u0012\u0007\b\u0001\u0012\u00030\u0092\u0001\u0012\u0005\u0012\u00030\u0093\u00010\u0007X¦\u0004¢\u0006\u0007\u001a\u0005\b\u0094\u0001\u0010\u000bR$\u0010\u0095\u0001\u001a\u0012\u0012\u0007\b\u0001\u0012\u00030\u0096\u0001\u0012\u0005\u0012\u00030\u0097\u00010\u0007X¦\u0004¢\u0006\u0007\u001a\u0005\b\u0098\u0001\u0010\u000bR$\u0010\u0099\u0001\u001a\u0012\u0012\u0007\b\u0001\u0012\u00030\u0096\u0001\u0012\u0005\u0012\u00030\u0097\u00010\u0007X¦\u0004¢\u0006\u0007\u001a\u0005\b\u009a\u0001\u0010\u000bR$\u0010\u009b\u0001\u001a\u0012\u0012\u0007\b\u0001\u0012\u00030\u0096\u0001\u0012\u0005\u0012\u00030\u0097\u00010\u0007X¦\u0004¢\u0006\u0007\u001a\u0005\b\u009c\u0001\u0010\u000bR$\u0010\u009d\u0001\u001a\u0012\u0012\u0007\b\u0001\u0012\u00030\u0096\u0001\u0012\u0005\u0012\u00030\u0097\u00010\u0007X¦\u0004¢\u0006\u0007\u001a\u0005\b\u009e\u0001\u0010\u000bR$\u0010\u009f\u0001\u001a\u0012\u0012\u0007\b\u0001\u0012\u00030\u0096\u0001\u0012\u0005\u0012\u00030\u0097\u00010\u0007X¦\u0004¢\u0006\u0007\u001a\u0005\b \u0001\u0010\u000bR*\u0010¡\u0001\u001a\u0018\u0012\r\b\u0001\u0012\t\u0012\u0005\u0012\u00030¢\u00010\u0015\u0012\u0005\u0012\u00030¢\u00010\u0007X¦\u0004¢\u0006\u0007\u001a\u0005\b£\u0001\u0010\u000bR$\u0010¤\u0001\u001a\u0012\u0012\u0007\b\u0001\u0012\u00030¥\u0001\u0012\u0005\u0012\u00030¦\u00010\u0007X¦\u0004¢\u0006\u0007\u001a\u0005\b§\u0001\u0010\u000bR+\u0010¨\u0001\u001a\u0019\u0012\u000e\b\u0001\u0012\n\u0012\u0005\u0012\u00030ª\u00010©\u0001\u0012\u0005\u0012\u00030ª\u00010\u0007X¦\u0004¢\u0006\u0007\u001a\u0005\b«\u0001\u0010\u000bR+\u0010¬\u0001\u001a\u0019\u0012\u000e\b\u0001\u0012\n\u0012\u0005\u0012\u00030\u00ad\u00010©\u0001\u0012\u0005\u0012\u00030\u00ad\u00010\u0007X¦\u0004¢\u0006\u0007\u001a\u0005\b®\u0001\u0010\u000bR+\u0010¯\u0001\u001a\u0019\u0012\u000e\b\u0001\u0012\n\u0012\u0005\u0012\u00030°\u00010©\u0001\u0012\u0005\u0012\u00030°\u00010\u0007X¦\u0004¢\u0006\u0007\u001a\u0005\b±\u0001\u0010\u000bR$\u0010²\u0001\u001a\u0012\u0012\u0007\b\u0001\u0012\u00030³\u0001\u0012\u0005\u0012\u00030´\u00010\u0007X¦\u0004¢\u0006\u0007\u001a\u0005\bµ\u0001\u0010\u000bR\u0016\u0010¶\u0001\u001a\u00030·\u0001X¦\u0004¢\u0006\b\u001a\u0006\b¸\u0001\u0010¹\u0001R\u0016\u0010º\u0001\u001a\u00030·\u0001X¦\u0004¢\u0006\b\u001a\u0006\b»\u0001\u0010¹\u0001R$\u0010¼\u0001\u001a\u0012\u0012\u0007\b\u0001\u0012\u00030½\u0001\u0012\u0005\u0012\u00030¾\u00010\u0007X¦\u0004¢\u0006\u0007\u001a\u0005\b¿\u0001\u0010\u000bR$\u0010À\u0001\u001a\u0012\u0012\u0007\b\u0001\u0012\u00030Á\u0001\u0012\u0005\u0012\u00030Â\u00010\u0007X¦\u0004¢\u0006\u0007\u001a\u0005\bÃ\u0001\u0010\u000bR*\u0010Ä\u0001\u001a\u0018\u0012\r\b\u0001\u0012\t\u0012\u0005\u0012\u00030Å\u00010\u0015\u0012\u0005\u0012\u00030Å\u00010\u0007X¦\u0004¢\u0006\u0007\u001a\u0005\bÆ\u0001\u0010\u000bR*\u0010Ç\u0001\u001a\u0018\u0012\r\b\u0001\u0012\t\u0012\u0005\u0012\u00030È\u00010\u0015\u0012\u0005\u0012\u00030È\u00010\u0007X¦\u0004¢\u0006\u0007\u001a\u0005\bÉ\u0001\u0010\u000bR$\u0010Ê\u0001\u001a\u0012\u0012\u0007\b\u0001\u0012\u00030Ë\u0001\u0012\u0005\u0012\u00030Ì\u00010\u0007X¦\u0004¢\u0006\u0007\u001a\u0005\bÍ\u0001\u0010\u000bR$\u0010Î\u0001\u001a\u0012\u0012\u0007\b\u0001\u0012\u00030Ï\u0001\u0012\u0005\u0012\u00030Ð\u00010\u0007X¦\u0004¢\u0006\u0007\u001a\u0005\bÑ\u0001\u0010\u000bR*\u0010Ò\u0001\u001a\u0018\u0012\r\b\u0001\u0012\t\u0012\u0005\u0012\u00030Ó\u00010\u0015\u0012\u0005\u0012\u00030Ó\u00010\u0007X¦\u0004¢\u0006\u0007\u001a\u0005\bÔ\u0001\u0010\u000bR*\u0010Õ\u0001\u001a\u0018\u0012\r\b\u0001\u0012\t\u0012\u0005\u0012\u00030Ö\u00010\u0015\u0012\u0005\u0012\u00030Ö\u00010\u0007X¦\u0004¢\u0006\u0007\u001a\u0005\b×\u0001\u0010\u000bR+\u0010Ø\u0001\u001a\u0019\u0012\u000e\b\u0001\u0012\n\u0012\u0005\u0012\u00030Ú\u00010Ù\u0001\u0012\u0005\u0012\u00030Ú\u00010\u0007X¦\u0004¢\u0006\u0007\u001a\u0005\bÛ\u0001\u0010\u000bR*\u0010Ü\u0001\u001a\u0018\u0012\r\b\u0001\u0012\t\u0012\u0005\u0012\u00030Ý\u00010\u0015\u0012\u0005\u0012\u00030Ý\u00010\u0007X¦\u0004¢\u0006\u0007\u001a\u0005\bÞ\u0001\u0010\u000bR$\u0010ß\u0001\u001a\u0012\u0012\u0007\b\u0001\u0012\u00030à\u0001\u0012\u0005\u0012\u00030á\u00010\u0007X¦\u0004¢\u0006\u0007\u001a\u0005\bâ\u0001\u0010\u000bR+\u0010ã\u0001\u001a\u0019\u0012\u000e\b\u0001\u0012\n\u0012\u0005\u0012\u00030ä\u00010Ù\u0001\u0012\u0005\u0012\u00030ä\u00010\u0007X¦\u0004¢\u0006\u0007\u001a\u0005\bå\u0001\u0010\u000bR*\u0010æ\u0001\u001a\u0018\u0012\r\b\u0001\u0012\t\u0012\u0005\u0012\u00030ç\u00010\u0015\u0012\u0005\u0012\u00030ç\u00010\u0007X¦\u0004¢\u0006\u0007\u001a\u0005\bè\u0001\u0010\u000bR*\u0010é\u0001\u001a\u0018\u0012\r\b\u0001\u0012\t\u0012\u0005\u0012\u00030ê\u00010\u0015\u0012\u0005\u0012\u00030ê\u00010\u0007X¦\u0004¢\u0006\u0007\u001a\u0005\bë\u0001\u0010\u000bR*\u0010ì\u0001\u001a\u0018\u0012\r\b\u0001\u0012\t\u0012\u0005\u0012\u00030í\u00010\u0015\u0012\u0005\u0012\u00030í\u00010\u0007X¦\u0004¢\u0006\u0007\u001a\u0005\bî\u0001\u0010\u000bR*\u0010ï\u0001\u001a\u0018\u0012\r\b\u0001\u0012\t\u0012\u0005\u0012\u00030ð\u00010\u0015\u0012\u0005\u0012\u00030ð\u00010\u0007X¦\u0004¢\u0006\u0007\u001a\u0005\bñ\u0001\u0010\u000bR$\u0010ò\u0001\u001a\u0012\u0012\u0007\b\u0001\u0012\u00030ó\u0001\u0012\u0005\u0012\u00030ô\u00010\u0007X¦\u0004¢\u0006\u0007\u001a\u0005\bõ\u0001\u0010\u000bR$\u0010ö\u0001\u001a\u0012\u0012\u0007\b\u0001\u0012\u00030÷\u0001\u0012\u0005\u0012\u00030ø\u00010\u0007X¦\u0004¢\u0006\u0007\u001a\u0005\bù\u0001\u0010\u000bR$\u0010ú\u0001\u001a\u0012\u0012\u0007\b\u0001\u0012\u00030û\u0001\u0012\u0005\u0012\u00030ü\u00010\u0007X¦\u0004¢\u0006\u0007\u001a\u0005\bý\u0001\u0010\u000bR\u0016\u0010þ\u0001\u001a\u00030ÿ\u0001X¦\u0004¢\u0006\b\u001a\u0006\b\u0080\u0002\u0010\u0081\u0002R\u0016\u0010\u0082\u0002\u001a\u00030ÿ\u0001X¦\u0004¢\u0006\b\u001a\u0006\b\u0083\u0002\u0010\u0081\u0002R\u0016\u0010\u0084\u0002\u001a\u00030ÿ\u0001X¦\u0004¢\u0006\b\u001a\u0006\b\u0085\u0002\u0010\u0081\u0002R\u0016\u0010\u0086\u0002\u001a\u00030\u0087\u0002X¦\u0004¢\u0006\b\u001a\u0006\b\u0088\u0002\u0010\u0089\u0002R\u0016\u0010\u008a\u0002\u001a\u00030\u0087\u0002X¦\u0004¢\u0006\b\u001a\u0006\b\u008b\u0002\u0010\u0089\u0002Ê\u0001\u0003\b\u008e\u0002ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u008d\u0002À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/KotlinElementTypeProvider;", "", "fileType", "Lcom/intellij/psi/tree/IFileElementType;", "getFileType", "()Lcom/intellij/psi/tree/IFileElementType;", "classType", "Lorg/jetbrains/kotlin/psi/stubs/elements/KtStubElementType;", "Lorg/jetbrains/kotlin/psi/stubs/KotlinClassStub;", "Lorg/jetbrains/kotlin/psi/KtClass;", "getClassType", "()Lorg/jetbrains/kotlin/psi/stubs/elements/KtStubElementType;", "objectType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinObjectStub;", "Lorg/jetbrains/kotlin/psi/KtObjectDeclaration;", "getObjectType", "typeAliasType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinTypeAliasStub;", "Lorg/jetbrains/kotlin/psi/KtTypeAlias;", "getTypeAliasType", "classBodyType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinPlaceHolderStub;", "Lorg/jetbrains/kotlin/psi/KtClassBody;", "getClassBodyType", "companionBlockType", "Lorg/jetbrains/kotlin/psi/KtCompanionBlock;", "getCompanionBlockType$annotations", "()V", "getCompanionBlockType", "Lorg/jetbrains/kotlin/psi/KtExperimentalApi;", "classInitializerType", "Lorg/jetbrains/kotlin/psi/KtClassInitializer;", "getClassInitializerType", "scriptInitializerType", "Lorg/jetbrains/kotlin/psi/KtScriptInitializer;", "getScriptInitializerType", "functionType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinFunctionStub;", "Lorg/jetbrains/kotlin/psi/KtNamedFunction;", "getFunctionType", "propertyType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinPropertyStub;", "Lorg/jetbrains/kotlin/psi/KtProperty;", "getPropertyType", "enumEntryType", "Lorg/jetbrains/kotlin/psi/KtEnumEntry;", "getEnumEntryType", "primaryConstructorType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinConstructorStub;", "Lorg/jetbrains/kotlin/psi/KtPrimaryConstructor;", "getPrimaryConstructorType", "secondaryConstructorType", "Lorg/jetbrains/kotlin/psi/KtSecondaryConstructor;", "getSecondaryConstructorType", "constructorCalleeType", "Lorg/jetbrains/kotlin/psi/KtConstructorCalleeExpression;", "getConstructorCalleeType", "propertyAccessorType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinPropertyAccessorStub;", "Lorg/jetbrains/kotlin/psi/KtPropertyAccessor;", "getPropertyAccessorType", "backingFieldType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinBackingFieldStub;", "Lorg/jetbrains/kotlin/psi/KtBackingField;", "getBackingFieldType", "initializerListType", "Lorg/jetbrains/kotlin/psi/KtInitializerList;", "getInitializerListType", "valueParameterListType", "Lorg/jetbrains/kotlin/psi/KtParameterList;", "getValueParameterListType", "valueParameterType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinParameterStub;", "Lorg/jetbrains/kotlin/psi/KtParameter;", "getValueParameterType", "contextParameterListType", "Lorg/jetbrains/kotlin/psi/KtContextParameterList;", "getContextParameterListType", "contextReceiverType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinContextReceiverStub;", "Lorg/jetbrains/kotlin/psi/KtContextReceiver;", "getContextReceiverType", "typeParameterListType", "Lorg/jetbrains/kotlin/psi/KtTypeParameterList;", "getTypeParameterListType", "typeParameterType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinTypeParameterStub;", "Lorg/jetbrains/kotlin/psi/KtTypeParameter;", "getTypeParameterType", "typeConstraintListType", "Lorg/jetbrains/kotlin/psi/KtTypeConstraintList;", "getTypeConstraintListType", "typeConstraintType", "Lorg/jetbrains/kotlin/psi/KtTypeConstraint;", "getTypeConstraintType", "superTypeListType", "Lorg/jetbrains/kotlin/psi/KtSuperTypeList;", "getSuperTypeListType", "delegatedSuperTypeEntryType", "Lorg/jetbrains/kotlin/psi/KtDelegatedSuperTypeEntry;", "getDelegatedSuperTypeEntryType", "superTypeCallEntryType", "Lorg/jetbrains/kotlin/psi/KtSuperTypeCallEntry;", "getSuperTypeCallEntryType", "superTypeEntryType", "Lorg/jetbrains/kotlin/psi/KtSuperTypeEntry;", "getSuperTypeEntryType", "modifierListType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinModifierListStub;", "Lorg/jetbrains/kotlin/psi/KtDeclarationModifierList;", "getModifierListType", "annotationType", "Lorg/jetbrains/kotlin/psi/KtAnnotation;", "getAnnotationType", "annotationEntryType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinAnnotationEntryStub;", "Lorg/jetbrains/kotlin/psi/KtAnnotationEntry;", "getAnnotationEntryType", "annotationTargetType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinAnnotationUseSiteTargetStub;", "Lorg/jetbrains/kotlin/psi/KtAnnotationUseSiteTarget;", "getAnnotationTargetType", "typeReferenceType", "Lorg/jetbrains/kotlin/psi/KtTypeReference;", "getTypeReferenceType", "userTypeType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinUserTypeStub;", "Lorg/jetbrains/kotlin/psi/KtUserType;", "getUserTypeType", "dynamicTypeType", "Lorg/jetbrains/kotlin/psi/KtDynamicType;", "getDynamicTypeType", "functionTypeType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinFunctionTypeStub;", "Lorg/jetbrains/kotlin/psi/KtFunctionType;", "getFunctionTypeType", "functionTypeReceiverType", "Lorg/jetbrains/kotlin/psi/KtFunctionTypeReceiver;", "getFunctionTypeReceiverType", "nullableTypeType", "Lorg/jetbrains/kotlin/psi/KtNullableType;", "getNullableTypeType", "intersectionTypeType", "Lorg/jetbrains/kotlin/psi/KtIntersectionType;", "getIntersectionTypeType", "typeProjectionType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinTypeProjectionStub;", "Lorg/jetbrains/kotlin/psi/KtTypeProjection;", "getTypeProjectionType", "nullType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinConstantExpressionStub;", "Lorg/jetbrains/kotlin/psi/KtConstantExpression;", "getNullType", "booleanConstantType", "getBooleanConstantType", "floatConstantType", "getFloatConstantType", "characterConstantType", "getCharacterConstantType", "integerConstantType", "getIntegerConstantType", "stringTemplateType", "Lorg/jetbrains/kotlin/psi/KtStringTemplateExpression;", "getStringTemplateType", "longStringTemplateEntryType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinBlockStringTemplateEntryStub;", "Lorg/jetbrains/kotlin/psi/KtBlockStringTemplateEntry;", "getLongStringTemplateEntryType", "shortStringTemplateEntryType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinPlaceHolderWithTextStub;", "Lorg/jetbrains/kotlin/psi/KtSimpleNameStringTemplateEntry;", "getShortStringTemplateEntryType", "literalStringTemplateEntryType", "Lorg/jetbrains/kotlin/psi/KtLiteralStringTemplateEntry;", "getLiteralStringTemplateEntryType", "escapeStringTemplateEntryType", "Lorg/jetbrains/kotlin/psi/KtEscapeStringTemplateEntry;", "getEscapeStringTemplateEntryType", "stringInterpolationPrefixType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinStringInterpolationPrefixStub;", "Lorg/jetbrains/kotlin/psi/KtStringInterpolationPrefix;", "getStringInterpolationPrefixType", "blockExpressionType", "Lcom/intellij/psi/tree/IElementType;", "getBlockExpressionType", "()Lcom/intellij/psi/tree/IElementType;", "lambdaExpressionType", "getLambdaExpressionType", "referenceExpressionType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinNameReferenceExpressionStub;", "Lorg/jetbrains/kotlin/psi/KtNameReferenceExpression;", "getReferenceExpressionType", "enumEntrySuperclassReferenceExpressionType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinEnumEntrySuperclassReferenceExpressionStub;", "Lorg/jetbrains/kotlin/psi/KtEnumEntrySuperclassReferenceExpression;", "getEnumEntrySuperclassReferenceExpressionType", "dotQualifiedExpressionType", "Lorg/jetbrains/kotlin/psi/KtDotQualifiedExpression;", "getDotQualifiedExpressionType", "callExpressionType", "Lorg/jetbrains/kotlin/psi/KtCallExpression;", "getCallExpressionType", "classLiteralExpressionType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinClassLiteralExpressionStub;", "Lorg/jetbrains/kotlin/psi/KtClassLiteralExpression;", "getClassLiteralExpressionType", "collectionLiteralExpressionType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinCollectionLiteralExpressionStub;", "Lorg/jetbrains/kotlin/psi/KtCollectionLiteralExpression;", "getCollectionLiteralExpressionType", "typeArgumentListType", "Lorg/jetbrains/kotlin/psi/KtTypeArgumentList;", "getTypeArgumentListType", "valueArgumentListType", "Lorg/jetbrains/kotlin/psi/KtValueArgumentList;", "getValueArgumentListType", "valueArgumentType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinValueArgumentStub;", "Lorg/jetbrains/kotlin/psi/KtValueArgument;", "getValueArgumentType", "contractEffectListType", "Lorg/jetbrains/kotlin/psi/KtContractEffectList;", "getContractEffectListType", "contractEffectType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinContractEffectStub;", "Lorg/jetbrains/kotlin/psi/KtContractEffect;", "getContractEffectType", "lambdaArgumentType", "Lorg/jetbrains/kotlin/psi/KtLambdaArgument;", "getLambdaArgumentType", "valueArgumentNameType", "Lorg/jetbrains/kotlin/psi/KtValueArgumentName;", "getValueArgumentNameType", "packageDirectiveType", "Lorg/jetbrains/kotlin/psi/KtPackageDirective;", "getPackageDirectiveType", "fileAnnotationListType", "Lorg/jetbrains/kotlin/psi/KtFileAnnotationList;", "getFileAnnotationListType", "importListType", "Lorg/jetbrains/kotlin/psi/KtImportList;", "getImportListType", "importDirectiveType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinImportDirectiveStub;", "Lorg/jetbrains/kotlin/psi/KtImportDirective;", "getImportDirectiveType", "importAliasType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinImportAliasStub;", "Lorg/jetbrains/kotlin/psi/KtImportAlias;", "getImportAliasType", "scriptType", "Lorg/jetbrains/kotlin/psi/stubs/KotlinScriptStub;", "Lorg/jetbrains/kotlin/psi/KtScript;", "getScriptType", "expressionCodeFragmentType", "Lcom/intellij/psi/impl/source/tree/ICodeFragmentElementType;", "getExpressionCodeFragmentType", "()Lcom/intellij/psi/impl/source/tree/ICodeFragmentElementType;", "blockCodeFragmentType", "getBlockCodeFragmentType", "typeCodeFragmentType", "getTypeCodeFragmentType", "kdocType", "Lcom/intellij/psi/tree/ILazyParseableElementType;", "getKdocType", "()Lcom/intellij/psi/tree/ILazyParseableElementType;", "kdocMarkdownLinkType", "getKdocMarkdownLinkType", "Companion", "org.jetbrains.kotlin:psi-api", "Lorg/jetbrains/kotlin/psi/KtImplementationDetail;"}, k = 1, mv = {2, 4, 0}, xi = 48)
@KtImplementationDetail
public interface KotlinElementTypeProvider {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\b\u0087\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R%\u0010\u0006\u001a\u00020\u00078FX\u0087\u0084\u0002r\u0002\b\r¢\u0006\u0012\n\u0004\b\u000b\u0010\f\u0012\u0004\b\b\u0010\u0003\u001a\u0004\b\t\u0010\nÊ\u0001\u0002\b\u000f¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/KotlinElementTypeProvider$Companion;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "IMPL", "", "instance", "Lorg/jetbrains/kotlin/KotlinElementTypeProvider;", "getInstance$annotations", "getInstance", "()Lorg/jetbrains/kotlin/KotlinElementTypeProvider;", "instance$delegate", "Lkotlin/Lazy;", "Lkotlin/jvm/JvmStatic;", "org.jetbrains.kotlin:psi-api", "Lorg/jetbrains/kotlin/psi/KtImplementationDetail;"}, k = 1, mv = {2, 4, 0}, xi = 48)
    @KtImplementationDetail
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        /* JADX INFO: renamed from: instance$delegate, reason: from kotlin metadata */
        private static final Lazy<KotlinElementTypeProvider> instance = LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: tb8
            public final Object invoke() {
                return KotlinElementTypeProvider.Companion.a();
            }
        });

        private Companion() {
        }

        public static KotlinElementTypeProvider a() throws IllegalAccessException {
            try {
                KotlinElementTypeProviderImpl kotlinElementTypeProviderImpl = KotlinElementTypeProviderImpl.INSTANCE;
                Object obj = KotlinElementTypeProviderImpl.class.getDeclaredField(JvmAbi.INSTANCE_FIELD).get(null);
                obj.getClass();
                return (KotlinElementTypeProvider) obj;
            } catch (ClassNotFoundException e) {
                mg9.a("KotlinElementTypeProvider implementation not found: org.jetbrains.kotlin.psi.impl.KotlinElementTypeProviderImpl", e);
                return null;
            }
        }

        @JvmStatic
        public static /* synthetic */ void getInstance$annotations() {
        }

        public final KotlinElementTypeProvider getInstance() {
            return instance.getValue();
        }
    }

    @KtExperimentalApi
    static /* synthetic */ void getCompanionBlockType$annotations() {
    }

    static KotlinElementTypeProvider getInstance() {
        return INSTANCE.getInstance();
    }

    KtStubElementType<? extends KotlinAnnotationEntryStub, KtAnnotationEntry> getAnnotationEntryType();

    KtStubElementType<? extends KotlinAnnotationUseSiteTargetStub, KtAnnotationUseSiteTarget> getAnnotationTargetType();

    KtStubElementType<? extends KotlinPlaceHolderStub<KtAnnotation>, KtAnnotation> getAnnotationType();

    KtStubElementType<? extends KotlinBackingFieldStub, KtBackingField> getBackingFieldType();

    ICodeFragmentElementType getBlockCodeFragmentType();

    IElementType getBlockExpressionType();

    KtStubElementType<? extends KotlinConstantExpressionStub, KtConstantExpression> getBooleanConstantType();

    KtStubElementType<? extends KotlinPlaceHolderStub<KtCallExpression>, KtCallExpression> getCallExpressionType();

    KtStubElementType<? extends KotlinConstantExpressionStub, KtConstantExpression> getCharacterConstantType();

    KtStubElementType<? extends KotlinPlaceHolderStub<KtClassBody>, KtClassBody> getClassBodyType();

    KtStubElementType<? extends KotlinPlaceHolderStub<KtClassInitializer>, KtClassInitializer> getClassInitializerType();

    KtStubElementType<? extends KotlinClassLiteralExpressionStub, KtClassLiteralExpression> getClassLiteralExpressionType();

    KtStubElementType<? extends KotlinClassStub, KtClass> getClassType();

    KtStubElementType<? extends KotlinCollectionLiteralExpressionStub, KtCollectionLiteralExpression> getCollectionLiteralExpressionType();

    KtStubElementType<? extends KotlinPlaceHolderStub<KtCompanionBlock>, KtCompanionBlock> getCompanionBlockType();

    KtStubElementType<? extends KotlinPlaceHolderStub<KtConstructorCalleeExpression>, KtConstructorCalleeExpression> getConstructorCalleeType();

    KtStubElementType<? extends KotlinPlaceHolderStub<KtContextParameterList>, KtContextParameterList> getContextParameterListType();

    KtStubElementType<? extends KotlinContextReceiverStub, KtContextReceiver> getContextReceiverType();

    KtStubElementType<? extends KotlinPlaceHolderStub<KtContractEffectList>, KtContractEffectList> getContractEffectListType();

    KtStubElementType<? extends KotlinContractEffectStub, KtContractEffect> getContractEffectType();

    KtStubElementType<? extends KotlinPlaceHolderStub<KtDelegatedSuperTypeEntry>, KtDelegatedSuperTypeEntry> getDelegatedSuperTypeEntryType();

    KtStubElementType<? extends KotlinPlaceHolderStub<KtDotQualifiedExpression>, KtDotQualifiedExpression> getDotQualifiedExpressionType();

    KtStubElementType<? extends KotlinPlaceHolderStub<KtDynamicType>, KtDynamicType> getDynamicTypeType();

    KtStubElementType<? extends KotlinEnumEntrySuperclassReferenceExpressionStub, KtEnumEntrySuperclassReferenceExpression> getEnumEntrySuperclassReferenceExpressionType();

    KtStubElementType<? extends KotlinClassStub, KtEnumEntry> getEnumEntryType();

    KtStubElementType<? extends KotlinPlaceHolderWithTextStub<KtEscapeStringTemplateEntry>, KtEscapeStringTemplateEntry> getEscapeStringTemplateEntryType();

    ICodeFragmentElementType getExpressionCodeFragmentType();

    KtStubElementType<? extends KotlinPlaceHolderStub<KtFileAnnotationList>, KtFileAnnotationList> getFileAnnotationListType();

    IFileElementType getFileType();

    KtStubElementType<? extends KotlinConstantExpressionStub, KtConstantExpression> getFloatConstantType();

    KtStubElementType<? extends KotlinFunctionStub, KtNamedFunction> getFunctionType();

    KtStubElementType<? extends KotlinPlaceHolderStub<KtFunctionTypeReceiver>, KtFunctionTypeReceiver> getFunctionTypeReceiverType();

    KtStubElementType<? extends KotlinFunctionTypeStub, KtFunctionType> getFunctionTypeType();

    KtStubElementType<? extends KotlinImportAliasStub, KtImportAlias> getImportAliasType();

    KtStubElementType<? extends KotlinImportDirectiveStub, KtImportDirective> getImportDirectiveType();

    KtStubElementType<? extends KotlinPlaceHolderStub<KtImportList>, KtImportList> getImportListType();

    KtStubElementType<? extends KotlinPlaceHolderStub<KtInitializerList>, KtInitializerList> getInitializerListType();

    KtStubElementType<? extends KotlinConstantExpressionStub, KtConstantExpression> getIntegerConstantType();

    KtStubElementType<? extends KotlinPlaceHolderStub<KtIntersectionType>, KtIntersectionType> getIntersectionTypeType();

    ILazyParseableElementType getKdocMarkdownLinkType();

    ILazyParseableElementType getKdocType();

    KtStubElementType<? extends KotlinValueArgumentStub<KtLambdaArgument>, KtLambdaArgument> getLambdaArgumentType();

    IElementType getLambdaExpressionType();

    KtStubElementType<? extends KotlinPlaceHolderWithTextStub<KtLiteralStringTemplateEntry>, KtLiteralStringTemplateEntry> getLiteralStringTemplateEntryType();

    KtStubElementType<? extends KotlinBlockStringTemplateEntryStub, KtBlockStringTemplateEntry> getLongStringTemplateEntryType();

    KtStubElementType<? extends KotlinModifierListStub, KtDeclarationModifierList> getModifierListType();

    KtStubElementType<? extends KotlinConstantExpressionStub, KtConstantExpression> getNullType();

    KtStubElementType<? extends KotlinPlaceHolderStub<KtNullableType>, KtNullableType> getNullableTypeType();

    KtStubElementType<? extends KotlinObjectStub, KtObjectDeclaration> getObjectType();

    KtStubElementType<? extends KotlinPlaceHolderStub<KtPackageDirective>, KtPackageDirective> getPackageDirectiveType();

    KtStubElementType<? extends KotlinConstructorStub<KtPrimaryConstructor>, KtPrimaryConstructor> getPrimaryConstructorType();

    KtStubElementType<? extends KotlinPropertyAccessorStub, KtPropertyAccessor> getPropertyAccessorType();

    KtStubElementType<? extends KotlinPropertyStub, KtProperty> getPropertyType();

    KtStubElementType<? extends KotlinNameReferenceExpressionStub, KtNameReferenceExpression> getReferenceExpressionType();

    KtStubElementType<? extends KotlinPlaceHolderStub<KtScriptInitializer>, KtScriptInitializer> getScriptInitializerType();

    KtStubElementType<? extends KotlinScriptStub, KtScript> getScriptType();

    KtStubElementType<? extends KotlinConstructorStub<KtSecondaryConstructor>, KtSecondaryConstructor> getSecondaryConstructorType();

    KtStubElementType<? extends KotlinPlaceHolderWithTextStub<KtSimpleNameStringTemplateEntry>, KtSimpleNameStringTemplateEntry> getShortStringTemplateEntryType();

    KtStubElementType<? extends KotlinStringInterpolationPrefixStub, KtStringInterpolationPrefix> getStringInterpolationPrefixType();

    KtStubElementType<? extends KotlinPlaceHolderStub<KtStringTemplateExpression>, KtStringTemplateExpression> getStringTemplateType();

    KtStubElementType<? extends KotlinPlaceHolderStub<KtSuperTypeCallEntry>, KtSuperTypeCallEntry> getSuperTypeCallEntryType();

    KtStubElementType<? extends KotlinPlaceHolderStub<KtSuperTypeEntry>, KtSuperTypeEntry> getSuperTypeEntryType();

    KtStubElementType<? extends KotlinPlaceHolderStub<KtSuperTypeList>, KtSuperTypeList> getSuperTypeListType();

    KtStubElementType<? extends KotlinTypeAliasStub, KtTypeAlias> getTypeAliasType();

    KtStubElementType<? extends KotlinPlaceHolderStub<KtTypeArgumentList>, KtTypeArgumentList> getTypeArgumentListType();

    ICodeFragmentElementType getTypeCodeFragmentType();

    KtStubElementType<? extends KotlinPlaceHolderStub<KtTypeConstraintList>, KtTypeConstraintList> getTypeConstraintListType();

    KtStubElementType<? extends KotlinPlaceHolderStub<KtTypeConstraint>, KtTypeConstraint> getTypeConstraintType();

    KtStubElementType<? extends KotlinPlaceHolderStub<KtTypeParameterList>, KtTypeParameterList> getTypeParameterListType();

    KtStubElementType<? extends KotlinTypeParameterStub, KtTypeParameter> getTypeParameterType();

    KtStubElementType<? extends KotlinTypeProjectionStub, KtTypeProjection> getTypeProjectionType();

    KtStubElementType<? extends KotlinPlaceHolderStub<KtTypeReference>, KtTypeReference> getTypeReferenceType();

    KtStubElementType<? extends KotlinUserTypeStub, KtUserType> getUserTypeType();

    KtStubElementType<? extends KotlinPlaceHolderStub<KtValueArgumentList>, KtValueArgumentList> getValueArgumentListType();

    KtStubElementType<? extends KotlinPlaceHolderStub<KtValueArgumentName>, KtValueArgumentName> getValueArgumentNameType();

    KtStubElementType<? extends KotlinValueArgumentStub<KtValueArgument>, KtValueArgument> getValueArgumentType();

    KtStubElementType<? extends KotlinPlaceHolderStub<KtParameterList>, KtParameterList> getValueParameterListType();

    KtStubElementType<? extends KotlinParameterStub, KtParameter> getValueParameterType();
}
