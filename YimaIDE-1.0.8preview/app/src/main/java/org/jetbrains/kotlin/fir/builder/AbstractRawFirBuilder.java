package org.jetbrains.kotlin.fir.builder;

import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.UninitializedPropertyAccessException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtNodeTypes;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.KtSourceFile;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.codegen.state.InlineClassManglingUtilsKt;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirExpressionRef;
import org.jetbrains.kotlin.fir.FirFunctionTarget;
import org.jetbrains.kotlin.fir.FirGenerationKt;
import org.jetbrains.kotlin.fir.FirLabel;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirLoopTarget;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirModuleDataKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.builder.AbstractRawFirBuilder;
import org.jetbrains.kotlin.fir.builder.FirReplSnippetConfiguratorExtension;
import org.jetbrains.kotlin.fir.declarations.DestructuringDeclarationAttributesKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirErrorProperty;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirReplSnippet;
import org.jetbrains.kotlin.fir.declarations.FirScript;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.declarations.builder.FirAnonymousObjectBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirConstructedClassTypeParameterRefBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirErrorFunctionBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirErrorPrimaryConstructorBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirErrorPropertyBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirFileBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirNamedFunctionBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirRegularClassBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirReplSnippetBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirScriptBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirValueParameterBuilder;
import org.jetbrains.kotlin.fir.declarations.impl.FirDeclarationStatusImpl;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationBuildingUtilsKt;
import org.jetbrains.kotlin.fir.diagnostics.ConeDestructuringDeclarationsOnTopLevel;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.ConeMultipleLabelsAreForbidden;
import org.jetbrains.kotlin.fir.diagnostics.ConeSimpleDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.ConeSyntaxDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.ConeUnderscoreIsReserved;
import org.jetbrains.kotlin.fir.diagnostics.DiagnosticKind;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirArgumentUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirCall;
import org.jetbrains.kotlin.fir.expressions.FirErrorExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCallOrigin;
import org.jetbrains.kotlin.fir.expressions.FirImplicitInvokeCall;
import org.jetbrains.kotlin.fir.expressions.FirIncrementDecrementExpression;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.expressions.FirLoop;
import org.jetbrains.kotlin.fir.expressions.FirOperation;
import org.jetbrains.kotlin.fir.expressions.FirOperationNameConventions;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirReturnExpression;
import org.jetbrains.kotlin.fir.expressions.FirSafeCallExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.builder.FirArgumentListBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirAugmentedAssignmentBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirBlockBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirConstExpressionBuilderKt;
import org.jetbrains.kotlin.fir.expressions.builder.FirDesugaredAssignmentValueReferenceExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirErrorExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirFunctionCallBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirImplicitInvokeCallBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirIncrementDecrementExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirIndexedAccessAugmentedAssignmentBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirLoopBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirLoopJumpBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirReturnExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirStringConcatenationCallBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirUnitExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirVariableAssignmentBuilder;
import org.jetbrains.kotlin.fir.extensions.FirExtensionServiceKt;
import org.jetbrains.kotlin.fir.references.builder.FirSimpleNamedReferenceBuilder;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirErrorFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirErrorPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirReplSnippetSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirScriptSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.FirErrorTypeRef;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.builder.FirErrorTypeRefBuilder;
import org.jetbrains.kotlin.fir.types.builder.FirResolvedTypeRefBuilder;
import org.jetbrains.kotlin.fir.types.impl.ConeClassLikeTypeImpl;
import org.jetbrains.kotlin.fir.types.impl.ConeTypeParameterTypeImpl;
import org.jetbrains.kotlin.fir.types.impl.FirImplicitBuiltinTypeRef;
import org.jetbrains.kotlin.lexer.KtSingleValueToken;
import org.jetbrains.kotlin.lexer.KtTokens;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.SpecialNames;
import org.jetbrains.kotlin.parsing.ParseUtilsKt;
import org.jetbrains.kotlin.psi.KtPsiUtil;
import org.jetbrains.kotlin.types.ConstantValueKind;
import org.jetbrains.kotlin.util.OperatorNameConventions;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachementBuilderUtilsKt;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000è\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010 \n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\b&\u0018\u0000 \u0095\u0002*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002:\b\u0095\u0002\u0096\u0002\u0097\u0002\u0098\u0002B\u001f\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u001d\u0010\u0011\u001a\u00020\u0012*\u00028\u00002\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014H&¢\u0006\u0002\u0010\u0015J\u0011\u0010.\u001a\u00020/*\u00028\u0000H&¢\u0006\u0002\u00100J\u0013\u00101\u001a\u0004\u0018\u00010+*\u00028\u0000H&¢\u0006\u0002\u0010-J\u0013\u00102\u001a\u0004\u0018\u00018\u0000*\u00028\u0000H&¢\u0006\u0002\u00103J\u0013\u00104\u001a\u0004\u0018\u00018\u0000*\u00028\u0000H&¢\u0006\u0002\u00103J\u0013\u00105\u001a\u0004\u0018\u00018\u0000*\u00028\u0000H&¢\u0006\u0002\u00103J\u001b\u00106\u001a\u0004\u0018\u00018\u0000*\u00028\u00002\u0006\u00107\u001a\u00020'H&¢\u0006\u0002\u00108J?\u0010E\u001a\u0002H\u0001\"\u0004\b\u0001\u0010\u00012\u0006\u0010F\u001a\u00020/2\u0006\u0010G\u001a\u00020!2\b\b\u0002\u0010H\u001a\u00020!2\f\u0010I\u001a\b\u0012\u0004\u0012\u0002H\u00010JH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010KJ5\u0010L\u001a\u0002H\u0001\"\u0004\b\u0001\u0010\u00012\u0006\u0010F\u001a\u00020/2\u0006\u0010G\u001a\u00020!2\f\u0010I\u001a\b\u0012\u0004\u0012\u0002H\u00010JH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010MJ/\u0010N\u001a\u0002HO\"\u0004\b\u0001\u0010O2\b\b\u0002\u0010P\u001a\u00020!2\f\u0010Q\u001a\b\u0012\u0004\u0012\u0002HO0JH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010RJ\u000e\u0010S\u001a\u00020T2\u0006\u0010U\u001a\u00020VJG\u0010W\u001a\u0002H\u0001\"\u0004\b\u0001\u0010\u00012\u0006\u0010X\u001a\u00020!2\n\b\u0002\u0010Y\u001a\u0004\u0018\u00010\u00122\f\u0010Z\u001a\b\u0012\u0004\u0012\u00020[0@2\f\u0010Q\u001a\b\u0012\u0004\u0012\u0002H\u00010JH\u0084\bø\u0001\u0000¢\u0006\u0002\u0010\\JH\u0010]\u001a\u0002H\u0001\"\u0004\b\u0001\u0010\u00012\n\u0010^\u001a\u0006\u0012\u0002\b\u00030_2\b\b\u0002\u0010`\u001a\u00020!2\f\u0010Q\u001a\b\u0012\u0004\u0012\u0002H\u00010JH\u0086\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0003 \u0001¢\u0006\u0002\u0010aJ-\u0010b\u001a\u0002H\u0001\"\u0004\b\u0001\u0010\u00012\u0006\u0010^\u001a\u00020c2\f\u0010Q\u001a\b\u0012\u0004\u0012\u0002H\u00010JH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010dJ-\u0010e\u001a\u0002H\u0001\"\u0004\b\u0001\u0010\u00012\u0006\u0010^\u001a\u00020f2\f\u0010Q\u001a\b\u0012\u0004\u0012\u0002H\u00010JH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010gJ\u001a\u0010h\u001a\u00020T2\f\u0010Q\u001a\b\u0012\u0004\u0012\u00020T0JH\u0086\bø\u0001\u0000J(\u0010j\u001a\u00020T2\u0006\u0010X\u001a\u00020!2\b\u0010Y\u001a\u0004\u0018\u00010\u00122\f\u0010Z\u001a\b\u0012\u0004\u0012\u00020[0@H\u0014J\u000e\u0010k\u001a\u00020l2\u0006\u0010F\u001a\u00020/J\b\u0010m\u001a\u0004\u0018\u00010nJ\n\u0010o\u001a\u0004\u0018\u00010nH\u0004J\u0006\u0010p\u001a\u00020lJ\u001b\u0010q\u001a\u0002H\u0001\"\u0004\b\u0001\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010r¢\u0006\u0002\u0010sJ\u001d\u0010t\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0001\u0010\u0001*\b\u0012\u0004\u0012\u0002H\u00010r¢\u0006\u0002\u0010sJ,\u0010u\u001a\u00020v*\u00020w2\n\b\u0002\u0010x\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010y\u001a\u0004\u0018\u00010+2\b\b\u0002\u0010z\u001a\u00020!J\u0017\u0010{\u001a\u00020V*\u00028\u00002\u0006\u0010|\u001a\u00020}¢\u0006\u0002\u0010~J\u0019\u0010{\u001a\u00020V*\u00028\u00002\u0007\u0010\u007f\u001a\u00030\u0080\u0001¢\u0006\u0003\u0010\u0081\u0001J.\u0010{\u001a\u00020V*\u00028\u00002\r\u0010\u0082\u0001\u001a\b\u0012\u0004\u0012\u00020[0@2\u000b\u0010^\u001a\u0007\u0012\u0002\b\u00030\u0083\u0001H\u0004¢\u0006\u0003\u0010\u0084\u0001J\u001c\u0010\u0085\u0001\u001a\b\u0012\u0004\u0012\u00020[0@2\r\u0010\u0086\u0001\u001a\b\u0012\u0004\u0012\u00020[0@J\u0012\u0010\u0087\u0001\u001a\u00030\u0088\u00012\b\u0010\u0089\u0001\u001a\u00030\u008a\u0001J\u0016\u0010\u008b\u0001\u001a\u00030\u008c\u0001*\u00030\u008d\u00012\u0007\u0010\u008e\u0001\u001a\u00020\u0002J\u0019\u0010\u008b\u0001\u001a\u00030\u008c\u0001*\u00030\u008d\u00012\n\u0010\u008f\u0001\u001a\u0005\u0018\u00010\u0090\u0001J'\u0010\u0091\u0001\u001a\u00030\u0092\u0001*\u00030\u008d\u00012\b\u0010\u0093\u0001\u001a\u00030\u008c\u00012\u000e\u0010\u0094\u0001\u001a\t\u0012\u0005\u0012\u00030\u0095\u00010JJ\u001c\u0010\u0096\u0001\u001a\u00030\u0097\u0001*\u00030\u0097\u00012\u0007\u0010\u0098\u0001\u001a\u00028\u0000¢\u0006\u0003\u0010\u0099\u0001J\u0016\u0010\u009a\u0001\u001a\u00020w2\u0007\u0010\u0098\u0001\u001a\u00028\u0000¢\u0006\u0003\u0010\u009b\u0001J\u0012\u0010\u009c\u0001\u001a\u00020!2\u0007\u0010\u009d\u0001\u001a\u00020+H\u0002J'\u0010\u009e\u0001\u001a\u00020T*\u00030\u009f\u00012\u0006\u0010F\u001a\u00020+2\t\u0010 \u0001\u001a\u0004\u0018\u00018\u0000H\u0004¢\u0006\u0003\u0010¡\u0001J*\u0010¢\u0001\u001a\u0004\u0018\u00010w2\u0007\u0010£\u0001\u001a\u00028\u00002\u0007\u0010¤\u0001\u001a\u00020w2\u0007\u0010¥\u0001\u001a\u00020'¢\u0006\u0003\u0010¦\u0001Jv\u0010§\u0001\u001a\u00020w*\r\u0012\b\b\u0001\u0012\u0004\u0018\u00018\u00000¨\u00012\u0007\u0010©\u0001\u001a\u00028\u00002\u0015\b\u0002\u0010(\u001a\u000f\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020'0ª\u00012)\u0010«\u0001\u001a$\u0012\u0006\u0012\u0004\u0018\u00018\u0000\u0012\u0004\u0012\u00020+\u0012\u000b\u0012\t\u0012\u0004\u0012\u00020w0\u00ad\u00010¬\u0001¢\u0006\u0003\b®\u00012\r\u0010¯\u0001\u001a\b\u0012\u0004\u0012\u00020+0J¢\u0006\u0003\u0010°\u0001JZ\u0010±\u0001\u001a\u00020w2\u0007\u0010²\u0001\u001a\u00028\u00002\t\u0010³\u0001\u001a\u0004\u0018\u00018\u00002\t\u0010¤\u0001\u001a\u0004\u0018\u00018\u00002\u0007\u0010´\u0001\u001a\u00020/2\u0007\u0010¯\u0001\u001a\u00020!2\u001a\u0010µ\u0001\u001a\u0015\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020w0ª\u0001¢\u0006\u0003\b®\u0001¢\u0006\u0003\u0010¶\u0001J\u0016\u0010·\u0001\u001a\u0004\u0018\u00018\u0000*\u0004\u0018\u00018\u0000H\u0002¢\u0006\u0002\u00103JZ\u0010¸\u0001\u001a\u00020w2\u0007\u0010²\u0001\u001a\u00028\u00002\t\u0010³\u0001\u001a\u0004\u0018\u00018\u00002\u0007\u0010¤\u0001\u001a\u00028\u00002\u0007\u0010´\u0001\u001a\u00020/2\u0007\u0010¯\u0001\u001a\u00020!2\u001a\u0010µ\u0001\u001a\u0015\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020w0ª\u0001¢\u0006\u0003\b®\u0001H\u0002¢\u0006\u0003\u0010¶\u0001J~\u0010¹\u0001\u001a\u00020w2\t\u0010¤\u0001\u001a\u0004\u0018\u00018\u00002\u001a\u0010µ\u0001\u001a\u0015\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020w0ª\u0001¢\u0006\u0003\b®\u00012\u0007\u0010º\u0001\u001a\u00020!2\u0007\u0010»\u0001\u001a\u00020\u001224\b\u0002\u0010¼\u0001\u001a-\u0012\u0005\u0012\u00030½\u0001\u0012\u0015\u0012\u00130w¢\u0006\u000e\b¾\u0001\u0012\t\bF\u0012\u0005\b\b(¤\u0001\u0012\u0004\u0012\u00020T0¬\u0001¢\u0006\u0003\b®\u0001H\u0002¢\u0006\u0003\u0010¿\u0001JO\u0010À\u0001\u001a\u00030Á\u00012\u0007\u0010¤\u0001\u001a\u00020w2\u0007\u0010Â\u0001\u001a\u00020!2\t\u0010Ã\u0001\u001a\u0004\u0018\u00010\u00122&\u0010Ä\u0001\u001a!\u0012\u0015\u0012\u00130w¢\u0006\u000e\b¾\u0001\u0012\t\bF\u0012\u0005\b\b(¤\u0001\u0012\u0005\u0012\u00030Á\u00010ª\u0001H\u0002Jz\u0010Å\u0001\u001a\u00030Á\u0001*\u0004\u0018\u00018\u00002\u0006\u0010x\u001a\u00020\u00122\t\u0010Æ\u0001\u001a\u0004\u0018\u00010\u00122\u0007\u0010Ç\u0001\u001a\u00020w2\b\u0010È\u0001\u001a\u00030É\u00012\u000e\u0010Ê\u0001\u001a\t\u0012\u0005\u0012\u00030Ë\u00010@2\t\u0010Ì\u0001\u001a\u0004\u0018\u00018\u00002\u0007\u0010Í\u0001\u001a\u00020!2\u001a\u0010µ\u0001\u001a\u0015\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020w0ª\u0001¢\u0006\u0003\b®\u0001¢\u0006\u0003\u0010Î\u0001J7\u0010Ï\u0001\u001a\u00030Ð\u00012\b\u0010Ñ\u0001\u001a\u00030Ð\u00012\b\u0010x\u001a\u0004\u0018\u00010\u00122\u0007\u0010Ç\u0001\u001a\u00020w2\u000e\u0010Ê\u0001\u001a\t\u0012\u0005\u0012\u00030Ë\u00010@H\u0002Jv\u0010Ò\u0001\u001a\u00030Á\u00012\u0007\u0010¤\u0001\u001a\u00020w2\u0006\u0010x\u001a\u00020\u00122\t\u0010Æ\u0001\u001a\u0004\u0018\u00010\u00122\b\u0010È\u0001\u001a\u00030É\u00012\u000e\u0010Ê\u0001\u001a\t\u0012\u0005\u0012\u00030Ë\u00010@2\t\u0010Ó\u0001\u001a\u0004\u0018\u00018\u00002\u001a\u0010µ\u0001\u001a\u0015\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020w0ª\u0001¢\u0006\u0003\b®\u00012\u0007\u0010Í\u0001\u001a\u00020!H\u0002¢\u0006\u0003\u0010Ô\u0001JC\u0010Õ\u0001\u001a\u00030Ö\u00012\b\u0010È\u0001\u001a\u00030É\u00012\t\u0010£\u0001\u001a\u0004\u0018\u00010\u00122\t\u0010¤\u0001\u001a\u0004\u0018\u00010w2\u0007\u0010Ç\u0001\u001a\u00020w2\u000e\u0010Ê\u0001\u001a\t\u0012\u0005\u0012\u00030Ë\u00010@H\u0002J\u000e\u0010×\u0001\u001a\u00020T*\u00030Ø\u0001H\u0004J\u000e\u0010Ù\u0001\u001a\u00020T*\u00030Ú\u0001H\u0004J\r\u0010Û\u0001\u001a\u00020T*\u00020}H\u0004J\u000e\u0010Ü\u0001\u001a\u00020T*\u00030Ý\u0001H\u0004J\u001c\u0010Þ\u0001\u001a\u00030\u0090\u00012\u0007\u0010ß\u0001\u001a\u00020+2\u0007\u0010£\u0001\u001a\u00020\u0012H\u0004J\u001e\u0010à\u0001\u001a\u0005\u0018\u00010á\u00012\u0007\u0010ß\u0001\u001a\u00020+2\u0007\u0010â\u0001\u001a\u00020!H\u0004J6\u0010ã\u0001\u001a\u00030ä\u00012\n\u0010 \u0001\u001a\u0005\u0018\u00010ä\u00012\u0007\u0010å\u0001\u001a\u00020\u00122\n\u0010æ\u0001\u001a\u0005\u0018\u00010á\u00012\t\u0010ç\u0001\u001a\u0004\u0018\u00010\u0012H\u0004J(\u0010è\u0001\u001a\u00030é\u00012\b\u0010ê\u0001\u001a\u00030é\u00012\t\u0010£\u0001\u001a\u0004\u0018\u00010\u00122\u0007\u0010¤\u0001\u001a\u00020wH\u0004J-\u0010ë\u0001\u001a\u00020/2\u0007\u0010ì\u0001\u001a\u00020/2\b\u0010í\u0001\u001a\u00030î\u00012\u000f\u0010ß\u0001\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010+0JH\u0004J\u001e\u0010ï\u0001\u001a\u00030ð\u00012\u0007\u0010£\u0001\u001a\u00020\u00122\t\u0010ñ\u0001\u001a\u0004\u0018\u00010wH\u0004J\u0013\u0010ò\u0001\u001a\u00030ó\u00012\u0007\u0010ô\u0001\u001a\u00020\u0012H\u0004J\u001c\u0010õ\u0001\u001a\u00020!2\u0007\u0010X\u001a\u00030ö\u00012\b\u0010÷\u0001\u001a\u00030ø\u0001H\u0004J\"\u0010ù\u0001\u001a\u00020!2\u0007\u0010ú\u0001\u001a\u00028\u00002\b\u0010û\u0001\u001a\u00030ü\u0001H\u0014¢\u0006\u0003\u0010ý\u0001J/\u0010þ\u0001\u001a\u00030ÿ\u00012\u0007\u0010\u0080\u0002\u001a\u00028\u00002\b\u0010û\u0001\u001a\u00030ü\u00012\n\u0010\u0081\u0002\u001a\u0005\u0018\u00010\u0082\u0002H\u0004¢\u0006\u0003\u0010\u0083\u0002JH\u0010\u0084\u0002\u001a\u00030\u0085\u00022\u0007\u0010ú\u0001\u001a\u00028\u00002\u0007\u0010\u0086\u0002\u001a\u00020\u00122\u0007\u0010\u0087\u0002\u001a\u00020+2\u001b\u0010\u0088\u0002\u001a\u0016\u0012\u0005\u0012\u00030\u0089\u0002\u0012\u0004\u0012\u00020T0ª\u0001¢\u0006\u0003\b®\u0001H$¢\u0006\u0003\u0010\u008a\u0002J\u0088\u0001\u0010\u008b\u0002\u001a\u00030\u008c\u00022\u0007\u0010ú\u0001\u001a\u00028\u00002\u0007\u0010\u0086\u0002\u001a\u00020\u00122\u0007\u0010\u0087\u0002\u001a\u00020+2\u001b\u0010\u008d\u0002\u001a\u0016\u0012\u0005\u0012\u00030\u008e\u0002\u0012\u0004\u0012\u00020T0ª\u0001¢\u0006\u0003\b®\u00012\u001b\u0010\u008f\u0002\u001a\u0016\u0012\u0005\u0012\u00030½\u0001\u0012\u0004\u0012\u00020T0ª\u0001¢\u0006\u0003\b®\u00012!\u0010\u0090\u0002\u001a\u001c\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030ä\u00010r\u0012\u0004\u0012\u00020T0ª\u0001¢\u0006\u0003\b®\u0001H$¢\u0006\u0003\u0010\u0091\u0002J\u001d\u0010\u0092\u0002\u001a\u00020T2\b\u0010\u0080\u0002\u001a\u00030\u0093\u00022\b\u0010\u0094\u0002\u001a\u00030\u0093\u0002H\u0004R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0016\u001a\u00020\u0017X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\u00020\u0017X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0019R\u0014\u0010\u001c\u001a\u00020\u0017X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0019R\u0014\u0010\u001e\u001a\u00020\u0017X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0019R\u0014\u0010 \u001a\u00020!X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010$\u001a\u00020!¢\u0006\b\n\u0000\u001a\u0004\b%\u0010#R\u0016\u0010&\u001a\u00020'*\u00028\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b(\u0010)R\u0016\u0010*\u001a\u00020+*\u00028\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b,\u0010-R\u001a\u00109\u001a\u0004\u0018\u00018\u0000*\u0004\u0018\u00018\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b:\u00103R\u001a\u0010;\u001a\u0004\u0018\u00018\u0000*\u0004\u0018\u00018\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b<\u00103R\u001a\u0010=\u001a\u0004\u0018\u00018\u0000*\u0004\u0018\u00018\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b>\u00103R \u0010?\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010@*\u0004\u0018\u00018\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\bA\u0010BR\u0016\u0010C\u001a\u00020!*\u00028\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\bC\u0010DR\u0011\u0010i\u001a\u00020!8F¢\u0006\u0006\u001a\u0004\bi\u0010#\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0099\u0002"}, d2 = {"Lorg/jetbrains/kotlin/fir/builder/AbstractRawFirBuilder;", "T", Argument.Delimiters.none, "baseSession", "Lorg/jetbrains/kotlin/fir/FirSession;", "context", "Lorg/jetbrains/kotlin/fir/builder/Context;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/builder/Context;)V", "getBaseSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "getContext", "()Lorg/jetbrains/kotlin/fir/builder/Context;", "baseModuleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "getBaseModuleData", "()Lorg/jetbrains/kotlin/fir/FirModuleData;", "toFirSourceElement", "Lorg/jetbrains/kotlin/KtSourceElement;", "kind", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", "(Ljava/lang/Object;Lorg/jetbrains/kotlin/KtFakeSourceElementKind;)Lorg/jetbrains/kotlin/KtSourceElement;", "implicitUnitType", "Lorg/jetbrains/kotlin/fir/types/impl/FirImplicitBuiltinTypeRef;", "getImplicitUnitType", "()Lorg/jetbrains/kotlin/fir/types/impl/FirImplicitBuiltinTypeRef;", "implicitAnyType", "getImplicitAnyType", "implicitEnumType", "getImplicitEnumType", "implicitAnnotationType", "getImplicitAnnotationType", "imitateLambdaSuspendModifier", Argument.Delimiters.none, "getImitateLambdaSuspendModifier", "()Z", "nameBasedDestructuringShortForm", "getNameBasedDestructuringShortForm", "elementType", "Lcom/intellij/psi/tree/IElementType;", "getElementType", "(Ljava/lang/Object;)Lcom/intellij/psi/tree/IElementType;", "asText", Argument.Delimiters.none, "getAsText", "(Ljava/lang/Object;)Ljava/lang/String;", "getReferencedNameAsName", "Lorg/jetbrains/kotlin/name/Name;", "(Ljava/lang/Object;)Lorg/jetbrains/kotlin/name/Name;", "getLabelName", "getExpressionInParentheses", "(Ljava/lang/Object;)Ljava/lang/Object;", "getAnnotatedExpression", "getLabeledExpression", "getChildNodeByType", ModuleXmlParser.TYPE, "(Ljava/lang/Object;Lcom/intellij/psi/tree/IElementType;)Ljava/lang/Object;", "receiverExpression", "getReceiverExpression", "selectorExpression", "getSelectorExpression", "arrayExpression", "getArrayExpression", "indexExpressions", Argument.Delimiters.none, "getIndexExpressions", "(Ljava/lang/Object;)Ljava/util/List;", "isVararg", "(Ljava/lang/Object;)Z", "withChildClassName", ModuleXmlParser.NAME, "isExpect", "forceLocalContext", "l", "Lkotlin/Function0;", "(Lorg/jetbrains/kotlin/name/Name;ZZLkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withChildClassNameRegardlessLocalContext", "(Lorg/jetbrains/kotlin/name/Name;ZLkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withForcedLocalContext", "R", "forceKeepingTheBodyInHeaderMode", "block", "(ZLkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "registerSelfType", Argument.Delimiters.none, "selfType", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "withCapturedTypeParameters", "status", "declarationSource", "currentFirTypeParameters", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRef;", "(ZLorg/jetbrains/kotlin/KtSourceElement;Ljava/util/List;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withContainerSymbol", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "isLocal", "(Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;ZLkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withContainerScriptSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirScriptSymbol;", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirScriptSymbol;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withContainerReplSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirReplSnippetSymbol;", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirReplSnippetSymbol;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withCompanionBlock", "isDirectlyInsideCompanionBlock", "addCapturedTypeParameters", "callableIdForName", "Lorg/jetbrains/kotlin/name/CallableId;", "currentDispatchReceiverType", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "dispatchReceiverForInnerClassConstructor", "callableIdForClassConstructor", "removeLast", Argument.Delimiters.none, "(Ljava/util/List;)Ljava/lang/Object;", "pop", "toReturn", "Lorg/jetbrains/kotlin/fir/expressions/FirReturnExpression;", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "baseSource", "labelName", "fromKtReturnExpression", "toDelegatedSelfType", "firClass", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirRegularClassBuilder;", "(Ljava/lang/Object;Lorg/jetbrains/kotlin/fir/declarations/builder/FirRegularClassBuilder;)Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "firObject", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirAnonymousObjectBuilder;", "(Ljava/lang/Object;Lorg/jetbrains/kotlin/fir/declarations/builder/FirAnonymousObjectBuilder;)Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "typeParameters", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "(Ljava/lang/Object;Ljava/util/List;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;)Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "constructorTypeParametersFromConstructedClass", "ownerTypeParameters", "createErrorConstructorBuilder", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirErrorPrimaryConstructorBuilder;", "diagnostic", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "prepareTarget", "Lorg/jetbrains/kotlin/fir/FirLoopTarget;", "Lorg/jetbrains/kotlin/fir/expressions/builder/FirLoopBuilder;", "firLabelUser", CoroutineCodegenUtilKt.COROUTINE_LABEL_FIELD_NAME, "Lorg/jetbrains/kotlin/fir/FirLabel;", "configure", "Lorg/jetbrains/kotlin/fir/expressions/FirLoop;", "target", "generateBlock", "Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "bindLabel", "Lorg/jetbrains/kotlin/fir/expressions/builder/FirLoopJumpBuilder;", "expression", "(Lorg/jetbrains/kotlin/fir/expressions/builder/FirLoopJumpBuilder;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/expressions/builder/FirLoopJumpBuilder;", "generateConstantExpressionByLiteral", "(Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "hasLeadingZeros", "text", "withSourceElementEntry", "Lorg/jetbrains/kotlin/utils/exceptions/ExceptionAttachmentBuilder;", "element", "(Lorg/jetbrains/kotlin/utils/exceptions/ExceptionAttachmentBuilder;Ljava/lang/String;Ljava/lang/Object;)V", "convertUnaryPlusMinusCallOnIntegerLiteralIfNecessary", "source", "receiver", "operationToken", "(Ljava/lang/Object;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lcom/intellij/psi/tree/IElementType;)Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "toInterpolatingCall", Argument.Delimiters.none, "base", "Lkotlin/Function1;", "convertTemplateEntry", "Lkotlin/Function2;", Argument.Delimiters.none, "Lkotlin/ExtensionFunctionType;", "prefix", "([Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function0;)Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "generateIncrementOrDecrementBlock", "wholeExpression", "operationReference", "callName", "convert", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lorg/jetbrains/kotlin/name/Name;ZLkotlin/jvm/functions/Function1;)Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "unwrap", "generateIncrementOrDecrementBlockForArrayAccess", "buildBlockPossiblyUnderSafeCall", "isChildInParentheses", "sourceElementForError", "init", "Lorg/jetbrains/kotlin/fir/expressions/builder/FirBlockBuilder;", "Lkotlin/ParameterName;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function1;ZLorg/jetbrains/kotlin/KtSourceElement;Lkotlin/jvm/functions/Function2;)Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "buildPossiblyUnderSafeCall", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "isReceiverIsWrappedWithParentheses", "sourceElementForErrorIfSafeCallSelectorIsNotExpression", "buildSelector", "generateAssignment", "arrayAccessSource", "rhsExpression", "operation", "Lorg/jetbrains/kotlin/fir/expressions/FirOperation;", "annotations", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "rhsAST", "isLhsParenthesized", "(Ljava/lang/Object;Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/fir/expressions/FirOperation;Ljava/util/List;Ljava/lang/Object;ZLkotlin/jvm/functions/Function1;)Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "putAssignmentToSafeCall", "Lorg/jetbrains/kotlin/fir/expressions/FirSafeCallExpression;", "safeCallNonAssignment", "generateIndexedAccessAugmentedAssignment", "rhs", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/fir/expressions/FirOperation;Ljava/util/List;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Z)Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "generateAssignmentOperatorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "initContainingClassForLocalAttr", "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "initContainingScriptOrReplAttr", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "initCompanionObjectSymbolAttr", "initContainingClassAttr", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "buildLabel", "rawName", "getForbiddenLabelKind", "Lorg/jetbrains/kotlin/fir/builder/AbstractRawFirBuilder$ForbiddenLabelKind;", "isMultipleLabel", "buildExpressionHandlingLabelErrors", "Lorg/jetbrains/kotlin/fir/FirElement;", "elementSource", "forbiddenLabelKind", "forbiddenLabelSource", "convertFirSelector", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "firSelector", "convertValueParameterName", "safeName", "valueParameterDeclaration", "Lorg/jetbrains/kotlin/fir/builder/AbstractRawFirBuilder$ValueParameterDeclaration;", "buildErrorNonLocalDestructuringDeclaration", "Lorg/jetbrains/kotlin/fir/declarations/FirErrorProperty;", "initializer", "createNoTypeForParameterTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirErrorTypeRef;", "parameterSource", "isImplicitlyActual", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;", "classKind", "Lorg/jetbrains/kotlin/descriptors/ClassKind;", "isReplSnippet", "script", "sourceFile", "Lorg/jetbrains/kotlin/KtSourceFile;", "(Ljava/lang/Object;Lorg/jetbrains/kotlin/KtSourceFile;)Z", "convertScriptOrSnippets", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "declaration", "fileBuilder", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirFileBuilder;", "(Ljava/lang/Object;Lorg/jetbrains/kotlin/KtSourceFile;Lorg/jetbrains/kotlin/fir/declarations/builder/FirFileBuilder;)Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "convertScript", "Lorg/jetbrains/kotlin/fir/declarations/FirScript;", "scriptSource", "fileName", "setup", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirScriptBuilder;", "(Ljava/lang/Object;Lorg/jetbrains/kotlin/KtSourceElement;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)Lorg/jetbrains/kotlin/fir/declarations/FirScript;", "convertReplSnippet", "Lorg/jetbrains/kotlin/fir/declarations/FirReplSnippet;", "snippetSetup", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirReplSnippetBuilder;", "functionBodySetup", "statementsSetup", "(Ljava/lang/Object;Lorg/jetbrains/kotlin/KtSourceElement;Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)Lorg/jetbrains/kotlin/fir/declarations/FirReplSnippet;", "configureScriptDestructuringDeclarationEntry", "Lorg/jetbrains/kotlin/fir/declarations/FirVariable;", "container", "Companion", "DataClassMembersGenerator", "ForbiddenLabelKind", "ValueParameterDeclaration", "org.jetbrains.kotlin:raw-fir.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class AbstractRawFirBuilder<T> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final FirModuleData baseModuleData;
    private final FirSession baseSession;
    private final Context<T> context;
    private final boolean imitateLambdaSuspendModifier;
    private final FirImplicitBuiltinTypeRef implicitAnnotationType;
    private final FirImplicitBuiltinTypeRef implicitAnyType;
    private final FirImplicitBuiltinTypeRef implicitEnumType;
    private final FirImplicitBuiltinTypeRef implicitUnitType;
    private final boolean nameBasedDestructuringShortForm;

    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\u0004\u0018\u00002\u00020\u0001Bh\u0012\u0006\u0010\u0002\u001a\u00028\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0018\u0010\u0007\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\n0\t0\b\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u001d\u0010\u000e\u001a\u0019\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00110\u000f¢\u0006\u0002\b\u0012¢\u0006\u0004\b\u0013\u0010\u0014J\u0006\u0010\u0016\u001a\u00020\u0011J\b\u0010\u0017\u001a\u00020\u0011H\u0002J\b\u0010\u0018\u001a\u00020\u0011H\u0002R\u0010\u0010\u0002\u001a\u00028\u0000X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0015R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0007\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\n0\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R%\u0010\u000e\u001a\u0019\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00110\u000f¢\u0006\u0002\b\u0012X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/fir/builder/AbstractRawFirBuilder$DataClassMembersGenerator;", Argument.Delimiters.none, "source", "classBuilder", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirRegularClassBuilder;", "firPrimaryConstructor", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "zippedParameters", Argument.Delimiters.none, "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "packageFqName", "Lorg/jetbrains/kotlin/name/FqName;", "classFqName", "addValueParameterAnnotations", "Lkotlin/Function2;", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirValueParameterBuilder;", Argument.Delimiters.none, "Lkotlin/ExtensionFunctionType;", "<init>", "(Lorg/jetbrains/kotlin/fir/builder/AbstractRawFirBuilder;Ljava/lang/Object;Lorg/jetbrains/kotlin/fir/declarations/builder/FirRegularClassBuilder;Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;Ljava/util/List;Lorg/jetbrains/kotlin/name/FqName;Lorg/jetbrains/kotlin/name/FqName;Lkotlin/jvm/functions/Function2;)V", "Ljava/lang/Object;", "generate", "generateComponentFunctions", "generateCopyFunction", "org.jetbrains.kotlin:raw-fir.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public final class DataClassMembersGenerator {
        private final Function2<FirValueParameterBuilder, T, Unit> addValueParameterAnnotations;
        private final FirRegularClassBuilder classBuilder;
        private final FqName classFqName;
        private final FirConstructor firPrimaryConstructor;
        private final FqName packageFqName;
        private final T source;
        final /* synthetic */ AbstractRawFirBuilder<T> this$0;
        private final List<Pair<T, FirProperty>> zippedParameters;

        /* JADX WARN: Multi-variable type inference failed */
        public DataClassMembersGenerator(AbstractRawFirBuilder abstractRawFirBuilder, T t, FirRegularClassBuilder firRegularClassBuilder, FirConstructor firConstructor, List<? extends Pair<? extends T, ? extends FirProperty>> list, FqName fqName, FqName fqName2, Function2<? super FirValueParameterBuilder, ? super T, Unit> function2) {
            t.getClass();
            firRegularClassBuilder.getClass();
            firConstructor.getClass();
            list.getClass();
            fqName.getClass();
            fqName2.getClass();
            function2.getClass();
            this.this$0 = abstractRawFirBuilder;
            this.source = t;
            this.classBuilder = firRegularClassBuilder;
            this.firPrimaryConstructor = firConstructor;
            this.zippedParameters = list;
            this.packageFqName = fqName;
            this.classFqName = fqName2;
            this.addValueParameterAnnotations = function2;
        }

        public static KtSourceElement a(AbstractRawFirBuilder abstractRawFirBuilder, Object obj, KtFakeSourceElementKind ktFakeSourceElementKind) {
            ktFakeSourceElementKind.getClass();
            if (obj != null) {
                return abstractRawFirBuilder.toFirSourceElement(obj, ktFakeSourceElementKind);
            }
            return null;
        }

        public static boolean b(AbstractRawFirBuilder abstractRawFirBuilder, Object obj) {
            obj.getClass();
            return abstractRawFirBuilder.isVararg(obj);
        }

        private final void generateComponentFunctions() {
            int i = 1;
            for (Pair<T, FirProperty> pair : this.zippedParameters) {
                Object objComponent1 = pair.component1();
                FirProperty firProperty = (FirProperty) pair.component2();
                if (firProperty.getIsVal() || firProperty.getIsVar()) {
                    Name nameIdentifier = Name.identifier("component" + i);
                    nameIdentifier.getClass();
                    i++;
                    AbstractRawFirBuilder<T> abstractRawFirBuilder = this.this$0;
                    FirNamedFunctionBuilder firNamedFunctionBuilder = new FirNamedFunctionBuilder();
                    KtFakeSourceElementKind.DataClassGeneratedMembers dataClassGeneratedMembers = KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE;
                    firNamedFunctionBuilder.setSource(abstractRawFirBuilder.toFirSourceElement((T) objComponent1, dataClassGeneratedMembers));
                    firNamedFunctionBuilder.setModuleData(abstractRawFirBuilder.getBaseModuleData());
                    firNamedFunctionBuilder.setOrigin(FirDeclarationOrigin.Synthetic.DataClassMember.INSTANCE);
                    firNamedFunctionBuilder.setReturnTypeRef(UtilsKt.copyWithNewSourceKind(firProperty.getReturnTypeRef(), dataClassGeneratedMembers));
                    firNamedFunctionBuilder.setName(nameIdentifier);
                    FirDeclarationStatusImpl firDeclarationStatusImpl = new FirDeclarationStatusImpl(firProperty.getStatus().getVisibility(), Modality.FINAL);
                    firDeclarationStatusImpl.setOperator(true);
                    firNamedFunctionBuilder.setStatus(firDeclarationStatusImpl);
                    firNamedFunctionBuilder.setLocal(this.firPrimaryConstructor.getIsLocal());
                    firNamedFunctionBuilder.setSymbol(new FirNamedFunctionSymbol(new CallableId(this.packageFqName, this.classFqName, nameIdentifier)));
                    firNamedFunctionBuilder.setDispatchReceiverType(abstractRawFirBuilder.currentDispatchReceiverType());
                    FirNamedFunction firNamedFunctionMo288build = firNamedFunctionBuilder.mo288build();
                    DeclarationAttributesKt.setComponentFunctionSymbol(firProperty, firNamedFunctionMo288build.getSymbol());
                    FirDeclarationBuildingUtilsKt.addDeclaration(this.classBuilder, firNamedFunctionMo288build);
                }
            }
        }

        private final void generateCopyFunction() {
            FirRegularClassBuilder firRegularClassBuilder = this.classBuilder;
            ClassId classId = new ClassId(this.packageFqName, this.classFqName, false);
            T t = this.source;
            ConeClassLikeType coneClassLikeTypeCurrentDispatchReceiverType = this.this$0.currentDispatchReceiverType();
            List<Pair<T, FirProperty>> list = this.zippedParameters;
            FirConstructor firConstructor = this.firPrimaryConstructor;
            final AbstractRawFirBuilder<T> abstractRawFirBuilder = this.this$0;
            Function2 function2 = new Function2() { // from class: gq
                public final Object invoke(Object obj, Object obj2) {
                    return AbstractRawFirBuilder.DataClassMembersGenerator.a(abstractRawFirBuilder, obj, (KtFakeSourceElementKind) obj2);
                }
            };
            Function2<FirValueParameterBuilder, T, Unit> function3 = this.addValueParameterAnnotations;
            final AbstractRawFirBuilder<T> abstractRawFirBuilder2 = this.this$0;
            FirDeclarationBuildingUtilsKt.addDeclaration(firRegularClassBuilder, AbstractRawFirBuilderKt.createDataClassCopyFunction(firRegularClassBuilder, classId, t, coneClassLikeTypeCurrentDispatchReceiverType, list, false, firConstructor, function2, function3, new Function1() { // from class: hq
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(AbstractRawFirBuilder.DataClassMembersGenerator.b(abstractRawFirBuilder2, obj));
                }
            }));
        }

        public final void generate() {
            if (this.classBuilder.getClassKind() != ClassKind.OBJECT) {
                generateComponentFunctions();
                generateCopyFunction();
            }
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0084\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/fir/builder/AbstractRawFirBuilder$ForbiddenLabelKind;", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;I)V", "UNDERSCORE_IS_RESERVED", "MULTIPLE_LABEL", "org.jetbrains.kotlin:raw-fir.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public enum ForbiddenLabelKind {
        UNDERSCORE_IS_RESERVED,
        MULTIPLE_LABEL;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<ForbiddenLabelKind> getEntries() {
            return $ENTRIES;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000b\n\u0002\b\r\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/fir/builder/AbstractRawFirBuilder$ValueParameterDeclaration;", Argument.Delimiters.none, "shouldExplicitParameterTypeBePresent", Argument.Delimiters.none, "isAnnotationOwner", "<init>", "(Ljava/lang/String;IZZ)V", "getShouldExplicitParameterTypeBePresent", "()Z", "FUNCTION", "CATCH", "PRIMARY_CONSTRUCTOR", "SETTER", "LAMBDA", "FOR_LOOP", "CONTEXT_PARAMETER", "org.jetbrains.kotlin:raw-fir.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public enum ValueParameterDeclaration {
        FUNCTION(true, true),
        CATCH(true, false),
        PRIMARY_CONSTRUCTOR(true, false),
        SETTER(false, false),
        LAMBDA(false, false),
        FOR_LOOP(false, false),
        CONTEXT_PARAMETER(true, true);

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
        private final boolean isAnnotationOwner;
        private final boolean shouldExplicitParameterTypeBePresent;

        ValueParameterDeclaration(boolean z, boolean z2) {
            this.shouldExplicitParameterTypeBePresent = z;
            this.isAnnotationOwner = z2;
        }

        public static EnumEntries<ValueParameterDeclaration> getEntries() {
            return $ENTRIES;
        }

        public final boolean getShouldExplicitParameterTypeBePresent() {
            return this.shouldExplicitParameterTypeBePresent;
        }

        /* JADX INFO: renamed from: isAnnotationOwner, reason: from getter */
        public final boolean getIsAnnotationOwner() {
            return this.isAnnotationOwner;
        }
    }

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[ForbiddenLabelKind.values().length];
            try {
                iArr[ForbiddenLabelKind.UNDERSCORE_IS_RESERVED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ForbiddenLabelKind.MULTIPLE_LABEL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[ValueParameterDeclaration.values().length];
            try {
                iArr2[ValueParameterDeclaration.LAMBDA.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr2[ValueParameterDeclaration.CATCH.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr2[ValueParameterDeclaration.CONTEXT_PARAMETER.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public AbstractRawFirBuilder(FirSession firSession, Context<T> context) {
        firSession.getClass();
        context.getClass();
        this.baseSession = firSession;
        this.context = context;
        this.baseModuleData = FirModuleDataKt.getModuleData(firSession);
        this.implicitUnitType = firSession.getBuiltinTypes().getUnitType();
        this.implicitAnyType = firSession.getBuiltinTypes().getAnyType();
        this.implicitEnumType = firSession.getBuiltinTypes().getEnumType();
        this.implicitAnnotationType = firSession.getBuiltinTypes().getAnnotationType();
        this.imitateLambdaSuspendModifier = FirLanguageSettingsComponentKt.getLanguageVersionSettings(firSession).supportsFeature(LanguageFeature.ParseLambdaWithSuspendModifier);
        this.nameBasedDestructuringShortForm = FirLanguageSettingsComponentKt.getLanguageVersionSettings(firSession).supportsFeature(LanguageFeature.EnableNameBasedDestructuringShortForm);
    }

    public static Unit a(FirReplSnippetConfiguratorExtension firReplSnippetConfiguratorExtension, KtSourceFile ktSourceFile, KtSourceElement ktSourceElement, AbstractRawFirBuilder abstractRawFirBuilder, FirBlockBuilder firBlockBuilder) {
        firBlockBuilder.getClass();
        if (firReplSnippetConfiguratorExtension != null) {
            firReplSnippetConfiguratorExtension.configureEvalBody(firBlockBuilder, ktSourceFile, ktSourceElement, abstractRawFirBuilder.context);
        }
        return Unit.INSTANCE;
    }

    public static FirStatement b(boolean z, boolean z2, AbstractRawFirBuilder abstractRawFirBuilder, FirOperation firOperation, KtSourceElement ktSourceElement, FirExpression firExpression, FirExpression firExpression2, List list, FirExpression firExpression3) {
        firExpression3.getClass();
        if (z && z2) {
            return abstractRawFirBuilder.generateAssignmentOperatorCall(firOperation, ktSourceElement, firExpression, firExpression2, list);
        }
        FirAugmentedAssignmentBuilder firAugmentedAssignmentBuilder = new FirAugmentedAssignmentBuilder();
        firAugmentedAssignmentBuilder.setSource(ktSourceElement);
        firAugmentedAssignmentBuilder.setOperation(firOperation);
        firAugmentedAssignmentBuilder.setLeftArgument(firExpression3);
        firAugmentedAssignmentBuilder.setRightArgument(firExpression2);
        CollectionsKt.addAll(firAugmentedAssignmentBuilder.getAnnotations(), list);
        return firAugmentedAssignmentBuilder.mo288build();
    }

    private final FirExpression buildBlockPossiblyUnderSafeCall(T receiver, Function1<? super T, ? extends FirExpression> convert, boolean isChildInParentheses, KtSourceElement sourceElementForError, final Function2<? super FirBlockBuilder, ? super FirExpression, Unit> init) {
        FirExpression firExpressionMo288build;
        if (receiver == null || (firExpressionMo288build = (FirExpression) convert.invoke(receiver)) == null) {
            FirErrorExpressionBuilder firErrorExpressionBuilder = new FirErrorExpressionBuilder();
            firErrorExpressionBuilder.setSource(sourceElementForError);
            firErrorExpressionBuilder.setDiagnostic(new ConeSyntaxDiagnostic("No receiver expression"));
            firExpressionMo288build = firErrorExpressionBuilder.mo288build();
        }
        FirStatement firStatementBuildPossiblyUnderSafeCall = buildPossiblyUnderSafeCall(firExpressionMo288build, isChildInParentheses, sourceElementForError, new Function1() { // from class: wp
            public final Object invoke(Object obj) {
                return AbstractRawFirBuilder.e(init, (FirExpression) obj);
            }
        });
        firStatementBuildPossiblyUnderSafeCall.getClass();
        return (FirExpression) firStatementBuildPossiblyUnderSafeCall;
    }

    private final FirStatement buildPossiblyUnderSafeCall(FirExpression receiver, boolean isReceiverIsWrappedWithParentheses, KtSourceElement sourceElementForErrorIfSafeCallSelectorIsNotExpression, Function1<? super FirExpression, ? extends FirStatement> buildSelector) {
        if (!(receiver instanceof FirSafeCallExpression) || isReceiverIsWrappedWithParentheses) {
            return (FirStatement) buildSelector.invoke(receiver);
        }
        FirSafeCallExpression firSafeCallExpression = (FirSafeCallExpression) receiver;
        FirStatement selector = firSafeCallExpression.getSelector();
        FirExpression firExpressionMo288build = selector instanceof FirExpression ? (FirExpression) selector : null;
        if (firExpressionMo288build == null) {
            FirErrorExpressionBuilder firErrorExpressionBuilder = new FirErrorExpressionBuilder();
            firErrorExpressionBuilder.setSource(sourceElementForErrorIfSafeCallSelectorIsNotExpression);
            firErrorExpressionBuilder.setDiagnostic(new ConeSyntaxDiagnostic("Safe call selector expected to be an expression here"));
            Unit unit = Unit.INSTANCE;
            firExpressionMo288build = firErrorExpressionBuilder.mo288build();
        }
        firSafeCallExpression.replaceSelector((FirStatement) buildSelector.invoke(firExpressionMo288build));
        return receiver;
    }

    public static Unit c(AbstractRawFirBuilder abstractRawFirBuilder, Object obj, KtFakeSourceElementKind.DesugaredIncrementOrDecrement desugaredIncrementOrDecrement, Object obj2, Object obj3, boolean z, boolean z2, Function1 function1, Object obj4, Name name, FirBlockBuilder firBlockBuilder, FirExpression firExpression) {
        firBlockBuilder.getClass();
        firExpression.getClass();
        KtSourceElement ktSourceElementFakeElement$default = KtSourceElementKt.fakeElement$default(toFirSourceElement$default(abstractRawFirBuilder, obj, null, 1, null), desugaredIncrementOrDecrement, null, 2, null);
        firBlockBuilder.setSource(ktSourceElementFakeElement$default);
        List<T> indexExpressions = abstractRawFirBuilder.getIndexExpressions(obj2);
        if (indexExpressions == null) {
            dt1.a("No indices in ", abstractRawFirBuilder.getAsText(obj));
            return null;
        }
        FirProperty firPropertyGenerateTemporaryVariable$default = FirGenerationKt.generateTemporaryVariable$default(abstractRawFirBuilder.baseModuleData, obj3 != null ? abstractRawFirBuilder.toFirSourceElement(obj3, KtFakeSourceElementKind.ArrayAccessNameReference.INSTANCE) : null, SpecialNames.ARRAY, firExpression, null, null, null, 112, null);
        firBlockBuilder.getStatements().add(firPropertyGenerateTemporaryVariable$default);
        List<T> list = indexExpressions;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        int i = 0;
        for (T t : list) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            FirProperty firPropertyGenerateTemporaryVariable$default2 = FirGenerationKt.generateTemporaryVariable$default(abstractRawFirBuilder.baseModuleData, abstractRawFirBuilder.toFirSourceElement(t, KtFakeSourceElementKind.ArrayIndexExpressionReference.INSTANCE), SpecialNames.subscribeOperatorIndex(i), (FirExpression) function1.invoke(t), null, null, null, 112, null);
            firBlockBuilder.getStatements().add(firPropertyGenerateTemporaryVariable$default2);
            arrayList.add(firPropertyGenerateTemporaryVariable$default2);
            i = i2;
        }
        if (z) {
            firBlockBuilder.getStatements().add(generateIncrementOrDecrementBlockForArrayAccess$lambda$0$buildSetCall(ktSourceElementFakeElement$default, firPropertyGenerateTemporaryVariable$default, abstractRawFirBuilder, obj2, arrayList, generateIncrementOrDecrementBlockForArrayAccess$lambda$0$buildIncDecCall(ktSourceElementFakeElement$default, obj4, abstractRawFirBuilder, name, desugaredIncrementOrDecrement, generateIncrementOrDecrementBlockForArrayAccess$lambda$0$buildGetCall(abstractRawFirBuilder, obj2, firPropertyGenerateTemporaryVariable$default, arrayList, desugaredIncrementOrDecrement)), desugaredIncrementOrDecrement));
            firBlockBuilder.getStatements().add(generateIncrementOrDecrementBlockForArrayAccess$lambda$0$buildGetCall(abstractRawFirBuilder, obj2, firPropertyGenerateTemporaryVariable$default, arrayList, z2 ? KtFakeSourceElementKind.DesugaredPrefixIncSecondGetReference.INSTANCE : KtFakeSourceElementKind.DesugaredPrefixDecSecondGetReference.INSTANCE));
        } else {
            FirProperty firPropertyGenerateTemporaryVariable$default3 = FirGenerationKt.generateTemporaryVariable$default(abstractRawFirBuilder.baseModuleData, ktSourceElementFakeElement$default, SpecialNames.UNARY, generateIncrementOrDecrementBlockForArrayAccess$lambda$0$buildGetCall(abstractRawFirBuilder, obj2, firPropertyGenerateTemporaryVariable$default, arrayList, desugaredIncrementOrDecrement), null, null, null, 112, null);
            firBlockBuilder.getStatements().add(firPropertyGenerateTemporaryVariable$default3);
            firBlockBuilder.getStatements().add(generateIncrementOrDecrementBlockForArrayAccess$lambda$0$buildSetCall(ktSourceElementFakeElement$default, firPropertyGenerateTemporaryVariable$default, abstractRawFirBuilder, obj2, arrayList, generateIncrementOrDecrementBlockForArrayAccess$lambda$0$buildIncDecCall(ktSourceElementFakeElement$default, obj4, abstractRawFirBuilder, name, desugaredIncrementOrDecrement, ConversionUtilsKt.generateResolvedAccessExpression(null, firPropertyGenerateTemporaryVariable$default3)), desugaredIncrementOrDecrement));
            firBlockBuilder.getStatements().add(ConversionUtilsKt.generateResolvedAccessExpression(null, firPropertyGenerateTemporaryVariable$default3));
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence convertScriptOrSnippets$lambda$1$1$0(FirReplSnippetConfiguratorExtension firReplSnippetConfiguratorExtension) {
        firReplSnippetConfiguratorExtension.getClass();
        return firReplSnippetConfiguratorExtension.getClass().getName();
    }

    public static Unit d(FirScriptConfiguratorExtension firScriptConfiguratorExtension, FirFileBuilder firFileBuilder, KtSourceFile ktSourceFile, AbstractRawFirBuilder abstractRawFirBuilder, FirScriptBuilder firScriptBuilder) {
        firScriptBuilder.getClass();
        if (firScriptConfiguratorExtension != null) {
            if (firFileBuilder != null) {
                firScriptConfiguratorExtension.configureContainingFile(firScriptBuilder, firFileBuilder);
            }
            firScriptConfiguratorExtension.configure(firScriptBuilder, ktSourceFile, abstractRawFirBuilder.context);
        }
        return Unit.INSTANCE;
    }

    public static FirStatement e(Function2 function2, FirExpression firExpression) {
        firExpression.getClass();
        FirBlockBuilder firBlockBuilder = new FirBlockBuilder();
        function2.invoke(firBlockBuilder, firExpression);
        return firBlockBuilder.mo288build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FirStatement generateAssignment$lambda$1$0(AbstractRawFirBuilder abstractRawFirBuilder, KtSourceElement ktSourceElement, KtSourceElement ktSourceElement2, FirOperation firOperation, List list, Object obj, Function1 function1, boolean z, FirExpression firExpression) {
        firExpression.getClass();
        return abstractRawFirBuilder.generateIndexedAccessAugmentedAssignment(firExpression, ktSourceElement, ktSourceElement2, firOperation, list, obj, function1, z);
    }

    private final FirFunctionCall generateAssignmentOperatorCall(FirOperation operation, KtSourceElement source, FirExpression receiver, FirExpression rhsExpression, List<? extends FirAnnotation> annotations) {
        FirFunctionCallBuilder firFunctionCallBuilder = new FirFunctionCallBuilder();
        firFunctionCallBuilder.setSource(source);
        firFunctionCallBuilder.setExplicitReceiver(receiver);
        firFunctionCallBuilder.setArgumentList(FirArgumentUtilKt.buildUnaryArgumentList(rhsExpression));
        FirSimpleNamedReferenceBuilder firSimpleNamedReferenceBuilder = new FirSimpleNamedReferenceBuilder();
        firSimpleNamedReferenceBuilder.setSource(source);
        firSimpleNamedReferenceBuilder.setName((Name) MapsKt.getValue(FirOperationNameConventions.INSTANCE.getASSIGNMENTS(), operation));
        firFunctionCallBuilder.setCalleeReference(firSimpleNamedReferenceBuilder.build());
        firFunctionCallBuilder.setOrigin(FirFunctionCallOrigin.Operator);
        firFunctionCallBuilder.getAnnotations().addAll(annotations);
        return firFunctionCallBuilder.mo288build();
    }

    private static final FirErrorExpression generateConstantExpressionByLiteral$reportIncorrectConstant(KtSourceElement ktSourceElement, String str, DiagnosticKind diagnosticKind) {
        FirErrorExpressionBuilder firErrorExpressionBuilder = new FirErrorExpressionBuilder();
        firErrorExpressionBuilder.setSource(ktSourceElement);
        firErrorExpressionBuilder.setDiagnostic(new ConeSimpleDiagnostic("Incorrect constant expression: " + str, diagnosticKind));
        return firErrorExpressionBuilder.mo288build();
    }

    private final FirExpression generateIncrementOrDecrementBlockForArrayAccess(final T wholeExpression, final T operationReference, final T receiver, final Name callName, final boolean prefix, final Function1<? super T, ? extends FirExpression> convert) {
        final boolean z;
        KtSourceElement firSourceElement$default;
        final T arrayExpression = getArrayExpression(receiver);
        if (Intrinsics.areEqual(callName, OperatorNameConventions.INC)) {
            z = true;
        } else {
            if (!Intrinsics.areEqual(callName, OperatorNameConventions.DEC)) {
                w04.a("Unexpected operator: ", callName);
                return null;
            }
            z = false;
        }
        final KtFakeSourceElementKind.DesugaredIncrementOrDecrement desugaredIncrementOrDecrementSourceKindForIncOrDec = KtSourceElementKt.sourceKindForIncOrDec(callName, prefix);
        KtSourceElement firSourceElement$default2 = toFirSourceElement$default(this, receiver, null, 1, null);
        return buildBlockPossiblyUnderSafeCall(arrayExpression, convert, ConversionUtilsKt.isChildInParentheses(firSourceElement$default2) || !(arrayExpression == null || (firSourceElement$default = toFirSourceElement$default(this, arrayExpression, null, 1, null)) == null || !ConversionUtilsKt.isChildInParentheses(firSourceElement$default)), firSourceElement$default2, new Function2() { // from class: zp
            public final Object invoke(Object obj, Object obj2) {
                return AbstractRawFirBuilder.c(this.b, wholeExpression, desugaredIncrementOrDecrementSourceKindForIncOrDec, receiver, arrayExpression, prefix, z, convert, operationReference, callName, (FirBlockBuilder) obj, (FirExpression) obj2);
            }
        });
    }

    private static final <T> FirFunctionCall generateIncrementOrDecrementBlockForArrayAccess$lambda$0$buildGetCall(AbstractRawFirBuilder<T> abstractRawFirBuilder, T t, FirProperty firProperty, List<? extends FirProperty> list, KtFakeSourceElementKind ktFakeSourceElementKind) {
        FirFunctionCallBuilder firFunctionCallBuilder = new FirFunctionCallBuilder();
        KtSourceElement firSourceElement = abstractRawFirBuilder.toFirSourceElement(t, ktFakeSourceElementKind);
        firFunctionCallBuilder.setSource(firSourceElement);
        FirSimpleNamedReferenceBuilder firSimpleNamedReferenceBuilder = new FirSimpleNamedReferenceBuilder();
        firSimpleNamedReferenceBuilder.setSource(firSourceElement);
        firSimpleNamedReferenceBuilder.setName(OperatorNameConventions.GET);
        firFunctionCallBuilder.setCalleeReference(firSimpleNamedReferenceBuilder.build());
        firFunctionCallBuilder.setExplicitReceiver(ConversionUtilsKt.generateResolvedAccessExpression(firProperty.getSource(), firProperty));
        FirArgumentListBuilder firArgumentListBuilder = new FirArgumentListBuilder();
        for (FirProperty firProperty2 : list) {
            firArgumentListBuilder.getArguments().add(ConversionUtilsKt.generateResolvedAccessExpression(firProperty2.getSource(), firProperty2));
        }
        firFunctionCallBuilder.setArgumentList(firArgumentListBuilder.build());
        firFunctionCallBuilder.setOrigin(FirFunctionCallOrigin.Operator);
        return firFunctionCallBuilder.mo288build();
    }

    private static final <T> FirFunctionCall generateIncrementOrDecrementBlockForArrayAccess$lambda$0$buildIncDecCall(KtSourceElement ktSourceElement, T t, AbstractRawFirBuilder<T> abstractRawFirBuilder, Name name, KtFakeSourceElementKind ktFakeSourceElementKind, FirExpression firExpression) {
        FirFunctionCallBuilder firFunctionCallBuilder = new FirFunctionCallBuilder();
        firFunctionCallBuilder.setSource(ktSourceElement);
        FirSimpleNamedReferenceBuilder firSimpleNamedReferenceBuilder = new FirSimpleNamedReferenceBuilder();
        firSimpleNamedReferenceBuilder.setSource(t != null ? abstractRawFirBuilder.toFirSourceElement(t, ktFakeSourceElementKind) : null);
        firSimpleNamedReferenceBuilder.setName(name);
        firFunctionCallBuilder.setCalleeReference(firSimpleNamedReferenceBuilder.build());
        firFunctionCallBuilder.setExplicitReceiver(firExpression);
        firFunctionCallBuilder.setOrigin(FirFunctionCallOrigin.Operator);
        return firFunctionCallBuilder.mo288build();
    }

    private static final <T> FirFunctionCall generateIncrementOrDecrementBlockForArrayAccess$lambda$0$buildSetCall(KtSourceElement ktSourceElement, FirProperty firProperty, AbstractRawFirBuilder<T> abstractRawFirBuilder, T t, List<? extends FirProperty> list, FirExpression firExpression, KtFakeSourceElementKind ktFakeSourceElementKind) {
        FirFunctionCallBuilder firFunctionCallBuilder = new FirFunctionCallBuilder();
        firFunctionCallBuilder.setSource(ktSourceElement);
        FirSimpleNamedReferenceBuilder firSimpleNamedReferenceBuilder = new FirSimpleNamedReferenceBuilder();
        firSimpleNamedReferenceBuilder.setSource(abstractRawFirBuilder.toFirSourceElement(t, ktFakeSourceElementKind));
        firSimpleNamedReferenceBuilder.setName(OperatorNameConventions.SET);
        firFunctionCallBuilder.setCalleeReference(firSimpleNamedReferenceBuilder.build());
        firFunctionCallBuilder.setExplicitReceiver(ConversionUtilsKt.generateResolvedAccessExpression(firProperty.getSource(), firProperty));
        FirArgumentListBuilder firArgumentListBuilder = new FirArgumentListBuilder();
        for (FirProperty firProperty2 : list) {
            firArgumentListBuilder.getArguments().add(ConversionUtilsKt.generateResolvedAccessExpression(firProperty2.getSource(), firProperty2));
        }
        firArgumentListBuilder.getArguments().add(firExpression);
        firFunctionCallBuilder.setArgumentList(firArgumentListBuilder.build());
        firFunctionCallBuilder.setOrigin(FirFunctionCallOrigin.Operator);
        return firFunctionCallBuilder.mo288build();
    }

    private final FirStatement generateIndexedAccessAugmentedAssignment(FirExpression receiver, KtSourceElement baseSource, KtSourceElement arrayAccessSource, FirOperation operation, List<? extends FirAnnotation> annotations, T rhs, Function1<? super T, ? extends FirExpression> convert, boolean isLhsParenthesized) {
        FirExpression firExpressionBuildErrorExpression$default;
        FirExpression firExpressionBuildErrorExpression$default2;
        boolean zSupportsFeature = FirLanguageSettingsComponentKt.getLanguageVersionSettings(this.baseSession).supportsFeature(LanguageFeature.ForbidParenthesizedLhsInAssignments);
        if ((receiver instanceof FirSafeCallExpression) || (isLhsParenthesized && zSupportsFeature)) {
            if (rhs == null || (firExpressionBuildErrorExpression$default = (FirExpression) convert.invoke(rhs)) == null) {
                firExpressionBuildErrorExpression$default = FirExpressionUtilKt.buildErrorExpression$default(baseSource, new ConeSyntaxDiagnostic("No value for array set"), null, 4, null);
            }
            return generateAssignmentOperatorCall(operation, baseSource, receiver, firExpressionBuildErrorExpression$default, annotations);
        }
        if (!(receiver instanceof FirFunctionCall)) {
            wec.a("Array access should be desugared to a function call, but ", receiver, " is found");
            return null;
        }
        FirIndexedAccessAugmentedAssignmentBuilder firIndexedAccessAugmentedAssignmentBuilder = new FirIndexedAccessAugmentedAssignmentBuilder();
        firIndexedAccessAugmentedAssignmentBuilder.setSource(baseSource);
        firIndexedAccessAugmentedAssignmentBuilder.setOperation(operation);
        firIndexedAccessAugmentedAssignmentBuilder.setLhsGetCall((FirFunctionCall) receiver);
        if (rhs == null || (firExpressionBuildErrorExpression$default2 = (FirExpression) convert.invoke(rhs)) == null) {
            firExpressionBuildErrorExpression$default2 = FirExpressionUtilKt.buildErrorExpression$default(baseSource, new ConeSyntaxDiagnostic("No value for array set"), null, 4, null);
        }
        firIndexedAccessAugmentedAssignmentBuilder.setRhs(firExpressionBuildErrorExpression$default2);
        firIndexedAccessAugmentedAssignmentBuilder.setArrayAccessSource(arrayAccessSource);
        CollectionsKt.addAll(firIndexedAccessAugmentedAssignmentBuilder.getAnnotations(), annotations);
        return firIndexedAccessAugmentedAssignmentBuilder.mo288build();
    }

    public static Unit h(FirReplSnippetConfiguratorExtension firReplSnippetConfiguratorExtension, FirFileBuilder firFileBuilder, KtSourceFile ktSourceFile, AbstractRawFirBuilder abstractRawFirBuilder, FirReplSnippetBuilder firReplSnippetBuilder) {
        firReplSnippetBuilder.getClass();
        if (firReplSnippetConfiguratorExtension != null) {
            if (firFileBuilder != null) {
                firReplSnippetConfiguratorExtension.configureContainingFile(firReplSnippetBuilder, firFileBuilder);
            }
            firReplSnippetConfiguratorExtension.configure(firReplSnippetBuilder, ktSourceFile, abstractRawFirBuilder.context);
        }
        return Unit.INSTANCE;
    }

    private final boolean hasLeadingZeros(String text) {
        if (text.length() > 1 && text.charAt(0) == '0') {
            char cCharAt = text.charAt(1);
            if (Character.isDigit(cCharAt) || cCharAt == '_') {
                return true;
            }
        }
        return false;
    }

    public static String i(AbstractRawFirBuilder abstractRawFirBuilder, Object obj) {
        obj.getClass();
        return abstractRawFirBuilder.getAsText(obj);
    }

    public static IElementType j(AbstractRawFirBuilder abstractRawFirBuilder, Object obj) {
        obj.getClass();
        return abstractRawFirBuilder.getElementType(obj);
    }

    public static Unit k(FirReplSnippetConfiguratorExtension firReplSnippetConfiguratorExtension, KtSourceFile ktSourceFile, KtSourceElement ktSourceElement, AbstractRawFirBuilder abstractRawFirBuilder, List list) {
        list.getClass();
        if (firReplSnippetConfiguratorExtension != null) {
            firReplSnippetConfiguratorExtension.configure(list, ktSourceFile, ktSourceElement, abstractRawFirBuilder.context);
        }
        return Unit.INSTANCE;
    }

    private final FirSafeCallExpression putAssignmentToSafeCall(FirSafeCallExpression safeCallNonAssignment, KtSourceElement baseSource, FirExpression rhsExpression, List<? extends FirAnnotation> annotations) {
        FirStatement selector = safeCallNonAssignment.getSelector();
        selector.getClass();
        FirVariableAssignmentBuilder firVariableAssignmentBuilder = new FirVariableAssignmentBuilder();
        firVariableAssignmentBuilder.setSource(baseSource);
        firVariableAssignmentBuilder.setLValue((FirQualifiedAccessExpression) selector);
        firVariableAssignmentBuilder.setRValue(rhsExpression);
        CollectionsKt.addAll(firVariableAssignmentBuilder.getAnnotations(), annotations);
        safeCallNonAssignment.replaceSelector(firVariableAssignmentBuilder.mo288build());
        return safeCallNonAssignment;
    }

    public static /* synthetic */ KtSourceElement toFirSourceElement$default(AbstractRawFirBuilder abstractRawFirBuilder, Object obj, KtFakeSourceElementKind ktFakeSourceElementKind, int i, Object obj2) {
        if (obj2 != null) {
            c41.a("Super calls with default arguments not supported in this target, function: toFirSourceElement");
            return null;
        }
        if ((i & 1) != 0) {
            ktFakeSourceElementKind = null;
        }
        return abstractRawFirBuilder.toFirSourceElement(obj, ktFakeSourceElementKind);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ FirExpression toInterpolatingCall$default(final AbstractRawFirBuilder abstractRawFirBuilder, Object[] objArr, Object obj, Function1 function1, Function2 function2, Function0 function0, int i, Object obj2) {
        if (obj2 != null) {
            c41.a("Super calls with default arguments not supported in this target, function: toInterpolatingCall");
            return null;
        }
        if ((i & 2) != 0) {
            function1 = new Function1() { // from class: vp
                public final Object invoke(Object obj3) {
                    return AbstractRawFirBuilder.j(this.b, obj3);
                }
            };
        }
        return abstractRawFirBuilder.toInterpolatingCall(objArr, obj, function1, function2, function0);
    }

    public static /* synthetic */ FirReturnExpression toReturn$default(AbstractRawFirBuilder abstractRawFirBuilder, FirExpression firExpression, KtSourceElement ktSourceElement, String str, boolean z, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: toReturn");
            return null;
        }
        if ((i & 1) != 0) {
            ktSourceElement = firExpression.getSource();
        }
        if ((i & 2) != 0) {
            str = null;
        }
        if ((i & 4) != 0) {
            z = false;
        }
        return abstractRawFirBuilder.toReturn(firExpression, ktSourceElement, str, z);
    }

    private static final <T> void toReturn$lambda$0$bindToErrorFunction(FirFunctionTarget firFunctionTarget, KtSourceElement ktSourceElement, AbstractRawFirBuilder<T> abstractRawFirBuilder, String str, DiagnosticKind diagnosticKind) {
        FirErrorFunctionBuilder firErrorFunctionBuilder = new FirErrorFunctionBuilder();
        firErrorFunctionBuilder.setSource(ktSourceElement);
        firErrorFunctionBuilder.setModuleData(((AbstractRawFirBuilder) abstractRawFirBuilder).baseModuleData);
        firErrorFunctionBuilder.setOrigin(FirDeclarationOrigin.Source.INSTANCE);
        firErrorFunctionBuilder.setDiagnostic(new ConeSimpleDiagnostic(str, diagnosticKind));
        firErrorFunctionBuilder.setSymbol(new FirErrorFunctionSymbol());
        firFunctionTarget.bind(firErrorFunctionBuilder.mo288build());
    }

    private final T unwrap(T t) {
        while (true) {
            IElementType elementType = t != null ? getElementType(t) : null;
            if (Intrinsics.areEqual(elementType, KtNodeTypes.PARENTHESIZED)) {
                t = getExpressionInParentheses(t);
            } else if (Intrinsics.areEqual(elementType, KtNodeTypes.LABELED_EXPRESSION)) {
                t = getLabeledExpression(t);
            } else {
                if (!Intrinsics.areEqual(elementType, KtNodeTypes.ANNOTATED_EXPRESSION)) {
                    return t;
                }
                t = getAnnotatedExpression(t);
            }
        }
    }

    public static /* synthetic */ Object withCapturedTypeParameters$default(AbstractRawFirBuilder abstractRawFirBuilder, boolean z, KtSourceElement ktSourceElement, List list, Function0 function0, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: withCapturedTypeParameters");
            return null;
        }
        if ((i & 2) != 0) {
            ktSourceElement = null;
        }
        list.getClass();
        function0.getClass();
        abstractRawFirBuilder.addCapturedTypeParameters(z, ktSourceElement, list);
        try {
            return function0.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            abstractRawFirBuilder.getContext().popFirTypeParameters();
            InlineMarker.finallyEnd(1);
        }
    }

    public static /* synthetic */ Object withChildClassName$default(AbstractRawFirBuilder abstractRawFirBuilder, Name name, boolean z, boolean z2, Function0 function0, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: withChildClassName");
            return null;
        }
        if ((i & 4) != 0) {
            z2 = false;
        }
        name.getClass();
        function0.getClass();
        if (!z2) {
            abstractRawFirBuilder.getContext().setClassName(abstractRawFirBuilder.getContext().getClassName().child(name));
            boolean containerIsExpect = abstractRawFirBuilder.getContext().getContainerIsExpect();
            abstractRawFirBuilder.getContext().setContainerIsExpect(containerIsExpect || z);
            int size = abstractRawFirBuilder.getContext().getDispatchReceiverTypesStack().size();
            try {
                Object objInvoke = function0.invoke();
                InlineMarker.finallyStart(1);
                if (abstractRawFirBuilder.getContext().getDispatchReceiverTypesStack().size() > size + 1) {
                    v1f.a("Wrong number of ", abstractRawFirBuilder.getContext().getDispatchReceiverTypesStack().size());
                    return null;
                }
                if (abstractRawFirBuilder.getContext().getDispatchReceiverTypesStack().size() > size) {
                    abstractRawFirBuilder.getContext().getDispatchReceiverTypesStack().remove(CollectionsKt.getLastIndex(abstractRawFirBuilder.getContext().getDispatchReceiverTypesStack()));
                }
                abstractRawFirBuilder.getContext().setClassName(abstractRawFirBuilder.getContext().getClassName().parent());
                abstractRawFirBuilder.getContext().setContainerIsExpect(containerIsExpect);
                InlineMarker.finallyEnd(1);
                return objInvoke;
            } catch (Throwable th) {
                InlineMarker.finallyStart(1);
                if (abstractRawFirBuilder.getContext().getDispatchReceiverTypesStack().size() > size + 1) {
                    v1f.a("Wrong number of ", abstractRawFirBuilder.getContext().getDispatchReceiverTypesStack().size());
                    return null;
                }
                if (abstractRawFirBuilder.getContext().getDispatchReceiverTypesStack().size() > size) {
                    abstractRawFirBuilder.getContext().getDispatchReceiverTypesStack().remove(CollectionsKt.getLastIndex(abstractRawFirBuilder.getContext().getDispatchReceiverTypesStack()));
                }
                abstractRawFirBuilder.getContext().setClassName(abstractRawFirBuilder.getContext().getClassName().parent());
                abstractRawFirBuilder.getContext().setContainerIsExpect(containerIsExpect);
                InlineMarker.finallyEnd(1);
                throw th;
            }
        }
        boolean forceKeepingTheBodyInHeaderMode = abstractRawFirBuilder.getContext().getForceKeepingTheBodyInHeaderMode();
        abstractRawFirBuilder.getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
        boolean inLocalContext = abstractRawFirBuilder.getContext().getInLocalContext();
        abstractRawFirBuilder.getContext().setInLocalContext(true);
        FqName classNameBeforeLocalContext = abstractRawFirBuilder.getContext().getClassNameBeforeLocalContext();
        if (!inLocalContext) {
            abstractRawFirBuilder.getContext().setClassNameBeforeLocalContext(abstractRawFirBuilder.getContext().getClassName());
        }
        FqName className = abstractRawFirBuilder.getContext().getClassName();
        abstractRawFirBuilder.getContext().setClassName(FqName.ROOT);
        try {
            abstractRawFirBuilder.getContext().setClassName(abstractRawFirBuilder.getContext().getClassName().child(name));
            boolean containerIsExpect2 = abstractRawFirBuilder.getContext().getContainerIsExpect();
            abstractRawFirBuilder.getContext().setContainerIsExpect(containerIsExpect2 || z);
            int size2 = abstractRawFirBuilder.getContext().getDispatchReceiverTypesStack().size();
            try {
                Object objInvoke2 = function0.invoke();
                InlineMarker.finallyStart(1);
                if (abstractRawFirBuilder.getContext().getDispatchReceiverTypesStack().size() > size2 + 1) {
                    throw new IllegalArgumentException(("Wrong number of " + abstractRawFirBuilder.getContext().getDispatchReceiverTypesStack().size()).toString());
                }
                if (abstractRawFirBuilder.getContext().getDispatchReceiverTypesStack().size() > size2) {
                    abstractRawFirBuilder.getContext().getDispatchReceiverTypesStack().remove(CollectionsKt.getLastIndex(abstractRawFirBuilder.getContext().getDispatchReceiverTypesStack()));
                }
                abstractRawFirBuilder.getContext().setClassName(abstractRawFirBuilder.getContext().getClassName().parent());
                abstractRawFirBuilder.getContext().setContainerIsExpect(containerIsExpect2);
                InlineMarker.finallyEnd(1);
                InlineMarker.finallyStart(1);
                abstractRawFirBuilder.getContext().setClassNameBeforeLocalContext(classNameBeforeLocalContext);
                abstractRawFirBuilder.getContext().setInLocalContext(inLocalContext);
                abstractRawFirBuilder.getContext().setClassName(className);
                abstractRawFirBuilder.getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
                InlineMarker.finallyEnd(1);
                return objInvoke2;
            } catch (Throwable th2) {
                InlineMarker.finallyStart(1);
                if (abstractRawFirBuilder.getContext().getDispatchReceiverTypesStack().size() > size2 + 1) {
                    throw new IllegalArgumentException(("Wrong number of " + abstractRawFirBuilder.getContext().getDispatchReceiverTypesStack().size()).toString());
                }
                if (abstractRawFirBuilder.getContext().getDispatchReceiverTypesStack().size() > size2) {
                    abstractRawFirBuilder.getContext().getDispatchReceiverTypesStack().remove(CollectionsKt.getLastIndex(abstractRawFirBuilder.getContext().getDispatchReceiverTypesStack()));
                }
                abstractRawFirBuilder.getContext().setClassName(abstractRawFirBuilder.getContext().getClassName().parent());
                abstractRawFirBuilder.getContext().setContainerIsExpect(containerIsExpect2);
                InlineMarker.finallyEnd(1);
                throw th2;
            }
        } catch (Throwable th3) {
            InlineMarker.finallyStart(1);
            abstractRawFirBuilder.getContext().setClassNameBeforeLocalContext(classNameBeforeLocalContext);
            abstractRawFirBuilder.getContext().setInLocalContext(inLocalContext);
            abstractRawFirBuilder.getContext().setClassName(className);
            abstractRawFirBuilder.getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
            InlineMarker.finallyEnd(1);
            throw th3;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
    public static /* synthetic */ Object withContainerSymbol$default(AbstractRawFirBuilder abstractRawFirBuilder, FirBasedSymbol firBasedSymbol, boolean z, Function0 function0, int i, Object obj) throws KotlinIllegalStateExceptionWithAttachments {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: withContainerSymbol");
            return null;
        }
        if ((i & 2) != 0) {
            z = false;
        }
        firBasedSymbol.getClass();
        function0.getClass();
        if (!z) {
            abstractRawFirBuilder.getContext().pushContainerSymbol(firBasedSymbol);
        }
        try {
            return function0.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            if (!z) {
                abstractRawFirBuilder.getContext().popContainerSymbol(firBasedSymbol);
            }
            InlineMarker.finallyEnd(1);
        }
    }

    public static /* synthetic */ Object withForcedLocalContext$default(AbstractRawFirBuilder abstractRawFirBuilder, boolean z, Function0 function0, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: withForcedLocalContext");
            return null;
        }
        if ((i & 1) != 0) {
            z = false;
        }
        function0.getClass();
        boolean forceKeepingTheBodyInHeaderMode = abstractRawFirBuilder.getContext().getForceKeepingTheBodyInHeaderMode();
        abstractRawFirBuilder.getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode || z);
        boolean inLocalContext = abstractRawFirBuilder.getContext().getInLocalContext();
        abstractRawFirBuilder.getContext().setInLocalContext(true);
        FqName classNameBeforeLocalContext = abstractRawFirBuilder.getContext().getClassNameBeforeLocalContext();
        if (!inLocalContext) {
            abstractRawFirBuilder.getContext().setClassNameBeforeLocalContext(abstractRawFirBuilder.getContext().getClassName());
        }
        FqName className = abstractRawFirBuilder.getContext().getClassName();
        abstractRawFirBuilder.getContext().setClassName(FqName.ROOT);
        try {
            return function0.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            abstractRawFirBuilder.getContext().setClassNameBeforeLocalContext(classNameBeforeLocalContext);
            abstractRawFirBuilder.getContext().setInLocalContext(inLocalContext);
            abstractRawFirBuilder.getContext().setClassName(className);
            abstractRawFirBuilder.getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
            InlineMarker.finallyEnd(1);
        }
    }

    public void addCapturedTypeParameters(boolean status, KtSourceElement declarationSource, List<? extends FirTypeParameterRef> currentFirTypeParameters) {
        currentFirTypeParameters.getClass();
        this.context.pushFirTypeParameters(status, currentFirTypeParameters);
    }

    public final FirLoopJumpBuilder bindLabel(FirLoopJumpBuilder firLoopJumpBuilder, T t) {
        DiagnosticKind diagnosticKind;
        firLoopJumpBuilder.getClass();
        t.getClass();
        String labelName = getLabelName(t);
        FirLoopTarget firLoopTarget = (FirLoopTarget) CollectionsKt.lastOrNull(this.context.getFirLoopTargets());
        KtSourceElement firSourceElement$default = toFirSourceElement$default(this, t, null, 1, null);
        if (labelName == null) {
            if (firLoopTarget == null) {
                firLoopTarget = new FirLoopTarget(labelName);
                firLoopTarget.bind(FirExpressionUtilKt.buildErrorLoop(firSourceElement$default, new ConeSimpleDiagnostic("Cannot bind unlabeled jump to a loop", DiagnosticKind.JumpOutsideLoop)));
            }
            firLoopJumpBuilder.setTarget(firLoopTarget);
            return firLoopJumpBuilder;
        }
        for (FirLoopTarget firLoopTarget2 : CollectionsKt.asReversedMutable(this.context.getFirLoopTargets())) {
            if (Intrinsics.areEqual(firLoopTarget2.getLabelName(), labelName)) {
                firLoopJumpBuilder.setTarget(firLoopTarget2);
                return firLoopJumpBuilder;
            }
        }
        FirLoopTarget firLoopTarget3 = new FirLoopTarget(labelName);
        String str = "Cannot bind label " + labelName + " to a loop";
        if (firLoopTarget == null || (diagnosticKind = DiagnosticKind.NotLoopLabel) == null) {
            diagnosticKind = DiagnosticKind.JumpOutsideLoop;
        }
        firLoopTarget3.bind(FirExpressionUtilKt.buildErrorLoop(firSourceElement$default, new ConeSimpleDiagnostic(str, diagnosticKind)));
        firLoopJumpBuilder.setTarget(firLoopTarget3);
        return firLoopJumpBuilder;
    }

    public final FirErrorProperty buildErrorNonLocalDestructuringDeclaration(KtSourceElement source, FirExpression initializer) {
        source.getClass();
        FirErrorPropertyBuilder firErrorPropertyBuilder = new FirErrorPropertyBuilder();
        firErrorPropertyBuilder.setSource(source);
        firErrorPropertyBuilder.setModuleData(this.baseModuleData);
        firErrorPropertyBuilder.setOrigin(FirDeclarationOrigin.Source.INSTANCE);
        Name nameSpecial = Name.special("<destructuring>");
        nameSpecial.getClass();
        firErrorPropertyBuilder.setName(nameSpecial);
        firErrorPropertyBuilder.setDiagnostic(ConeDestructuringDeclarationsOnTopLevel.INSTANCE);
        firErrorPropertyBuilder.setSymbol(new FirErrorPropertySymbol(firErrorPropertyBuilder.getDiagnostic()));
        if (initializer == null) {
            FirErrorExpressionBuilder firErrorExpressionBuilder = new FirErrorExpressionBuilder();
            firErrorExpressionBuilder.setSource(source);
            firErrorExpressionBuilder.setDiagnostic(new ConeSyntaxDiagnostic("Initializer required for destructuring declaration"));
            initializer = firErrorExpressionBuilder.mo288build();
        }
        firErrorPropertyBuilder.setInitializer(initializer);
        return firErrorPropertyBuilder.mo288build();
    }

    public final FirElement buildExpressionHandlingLabelErrors(FirElement element, KtSourceElement elementSource, ForbiddenLabelKind forbiddenLabelKind, KtSourceElement forbiddenLabelSource) {
        ConeDiagnostic coneUnderscoreIsReserved;
        elementSource.getClass();
        if (element == null) {
            return FirExpressionUtilKt.buildErrorExpression$default(elementSource, new ConeSyntaxDiagnostic("Empty label"), null, 4, null);
        }
        if (forbiddenLabelKind == null) {
            return element;
        }
        if (forbiddenLabelSource == null) {
            w01.a("Failed requirement.");
            return null;
        }
        FirErrorExpressionBuilder firErrorExpressionBuilder = new FirErrorExpressionBuilder();
        firErrorExpressionBuilder.setSource(element.getSource());
        boolean z = element instanceof FirExpression;
        firErrorExpressionBuilder.setExpression(z ? (FirExpression) element : null);
        if (z) {
            element = null;
        }
        firErrorExpressionBuilder.setNonExpressionElement(element);
        int i = WhenMappings.$EnumSwitchMapping$0[forbiddenLabelKind.ordinal()];
        if (i == 1) {
            coneUnderscoreIsReserved = new ConeUnderscoreIsReserved(forbiddenLabelSource);
        } else {
            if (i != 2) {
                bu8.a();
                return null;
            }
            coneUnderscoreIsReserved = new ConeMultipleLabelsAreForbidden(forbiddenLabelSource);
        }
        firErrorExpressionBuilder.setDiagnostic(coneUnderscoreIsReserved);
        return firErrorExpressionBuilder.mo288build();
    }

    public final FirLabel buildLabel(String rawName, KtSourceElement source) {
        rawName.getClass();
        source.getClass();
        FirLabelBuilder firLabelBuilder = new FirLabelBuilder();
        String strUnquoteIdentifier = KtPsiUtil.unquoteIdentifier(rawName);
        strUnquoteIdentifier.getClass();
        firLabelBuilder.setName(strUnquoteIdentifier);
        firLabelBuilder.setSource(source);
        return firLabelBuilder.build();
    }

    public final CallableId callableIdForClassConstructor() {
        FqName package_fq_name_for_local = this.context.getInLocalContext() ? CallableId.Companion.getPACKAGE_FQ_NAME_FOR_LOCAL() : this.context.getPackageFqName();
        if (!Intrinsics.areEqual(this.context.getClassName(), FqName.ROOT)) {
            return new CallableId(package_fq_name_for_local, this.context.getClassName(), this.context.getClassName().shortName());
        }
        Name nameSpecial = Name.special("<anonymous-init>");
        nameSpecial.getClass();
        return new CallableId(package_fq_name_for_local, nameSpecial);
    }

    public final CallableId callableIdForName(Name name) {
        name.getClass();
        if (Intrinsics.areEqual(this.context.getClassName().shortNameOrSpecial(), SpecialNames.ANONYMOUS)) {
            return new CallableId(new ClassId(this.context.getPackageFqName(), SpecialNames.ANONYMOUS_FQ_NAME, true), name);
        }
        if (this.context.getClassName().isRoot() && !this.context.getInLocalContext()) {
            return new CallableId(this.context.getPackageFqName(), name);
        }
        if (!this.context.getInLocalContext()) {
            return new CallableId(this.context.getPackageFqName(), this.context.getClassName(), name);
        }
        List<FirFunctionTarget> firFunctionTargets = this.context.getFirFunctionTargets();
        FqName packageFqName = this.context.getClassNameBeforeLocalContext().isRoot() ? this.context.getPackageFqName() : new ClassId(this.context.getPackageFqName(), this.context.getClassNameBeforeLocalContext(), false).asSingleFqName();
        for (FirFunctionTarget firFunctionTarget : firFunctionTargets) {
            if (!firFunctionTarget.getIsLambda() && firFunctionTarget.getLabelName() != null) {
                String labelName = firFunctionTarget.getLabelName();
                labelName.getClass();
                Name nameIdentifier = Name.identifier(labelName);
                nameIdentifier.getClass();
                packageFqName = packageFqName.child(nameIdentifier);
            }
        }
        return new CallableId(name, packageFqName);
    }

    public final FirLoop configure(FirLoopBuilder firLoopBuilder, FirLoopTarget firLoopTarget, Function0<? extends FirBlock> function0) {
        firLoopBuilder.getClass();
        firLoopTarget.getClass();
        function0.getClass();
        firLoopBuilder.setBlock((FirBlock) function0.invoke());
        FirLoop firLoopBuild = firLoopBuilder.build();
        Intrinsics.areEqual(firLoopTarget, (FirLoopTarget) removeLast(this.context.getFirLoopTargets()));
        firLoopTarget.bind(firLoopBuild);
        return firLoopBuild;
    }

    public final void configureScriptDestructuringDeclarationEntry(FirVariable declaration, FirVariable container) {
        declaration.getClass();
        container.getClass();
        DestructuringDeclarationAttributesKt.setDestructuringDeclarationContainerVariable((FirProperty) declaration, container.getSymbol());
    }

    public final List<FirTypeParameterRef> constructorTypeParametersFromConstructedClass(List<? extends FirTypeParameterRef> ownerTypeParameters) {
        ownerTypeParameters.getClass();
        ArrayList arrayList = new ArrayList();
        for (FirTypeParameterRef firTypeParameterRef : ownerTypeParameters) {
            KtSourceElement ktSourceElementBuild = null;
            FirTypeParameter firTypeParameter = firTypeParameterRef instanceof FirTypeParameter ? (FirTypeParameter) firTypeParameterRef : null;
            if (firTypeParameter != null) {
                FirConstructedClassTypeParameterRefBuilder firConstructedClassTypeParameterRefBuilder = new FirConstructedClassTypeParameterRefBuilder();
                KtSourceElement source = firTypeParameter.getSymbol().getSource();
                firConstructedClassTypeParameterRefBuilder.setSource(source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.ConstructorTypeParameter.INSTANCE, null, 2, null) : null);
                firConstructedClassTypeParameterRefBuilder.setSymbol(firTypeParameter.getSymbol());
                ktSourceElementBuild = firConstructedClassTypeParameterRefBuilder.build();
            }
            if (ktSourceElementBuild != null) {
                arrayList.add(ktSourceElementBuild);
            }
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final FirQualifiedAccessExpression convertFirSelector(FirQualifiedAccessExpression firSelector, KtSourceElement source, FirExpression receiver) {
        firSelector.getClass();
        receiver.getClass();
        if (!(firSelector instanceof FirImplicitInvokeCall)) {
            firSelector.replaceExplicitReceiver(receiver);
            firSelector.replaceSource(source);
            return firSelector;
        }
        FirImplicitInvokeCallBuilder firImplicitInvokeCallBuilder = new FirImplicitInvokeCallBuilder();
        firImplicitInvokeCallBuilder.setSource(source);
        FirImplicitInvokeCall firImplicitInvokeCall = (FirImplicitInvokeCall) firSelector;
        firImplicitInvokeCallBuilder.getAnnotations().addAll(firImplicitInvokeCall.getAnnotations());
        firImplicitInvokeCallBuilder.getTypeArguments().addAll(firImplicitInvokeCall.getTypeArguments());
        firImplicitInvokeCallBuilder.setExplicitReceiver(firImplicitInvokeCall.getExplicitReceiver());
        FirArgumentListBuilder firArgumentListBuilder = new FirArgumentListBuilder();
        firArgumentListBuilder.getArguments().add(receiver);
        firArgumentListBuilder.getArguments().addAll(((FirCall) firSelector).getArgumentList().getArguments());
        firImplicitInvokeCallBuilder.setArgumentList(firArgumentListBuilder.build());
        firImplicitInvokeCallBuilder.setCallWithExplicitReceiver(true);
        firImplicitInvokeCallBuilder.setCalleeReference(firImplicitInvokeCall.getCalleeReference());
        return firImplicitInvokeCallBuilder.mo288build();
    }

    public abstract FirReplSnippet convertReplSnippet(T script, KtSourceElement scriptSource, String fileName, Function1<? super FirReplSnippetBuilder, Unit> snippetSetup, Function1<? super FirBlockBuilder, Unit> functionBodySetup, Function1<? super List<FirElement>, Unit> statementsSetup);

    public abstract FirScript convertScript(T script, KtSourceElement scriptSource, String fileName, Function1<? super FirScriptBuilder, Unit> setup);

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public final FirDeclaration convertScriptOrSnippets(T declaration, final KtSourceFile sourceFile, final FirFileBuilder fileBuilder) throws KotlinIllegalArgumentExceptionWithAttachments {
        declaration.getClass();
        sourceFile.getClass();
        FirScriptConfiguratorExtension firScriptConfiguratorExtension = null;
        final KtSourceElement firSourceElement$default = toFirSourceElement$default(this, declaration, null, 1, null);
        boolean zIsReplSnippet = isReplSnippet(declaration, sourceFile);
        FirSession firSession = this.baseSession;
        if (!zIsReplSnippet) {
            for (T t : FirScriptConfiguratorExtensionKt.getScriptConfigurators(FirExtensionServiceKt.getExtensionService(firSession))) {
                if (((FirScriptConfiguratorExtension) t).accepts(sourceFile, firSourceElement$default)) {
                    firScriptConfiguratorExtension = t;
                    break;
                }
            }
            final FirScriptConfiguratorExtension firScriptConfiguratorExtension2 = firScriptConfiguratorExtension;
            return convertScript(declaration, firSourceElement$default, sourceFile.getName(), new Function1() { // from class: fq
                public final Object invoke(Object obj) {
                    return AbstractRawFirBuilder.d(firScriptConfiguratorExtension2, fileBuilder, sourceFile, this, (FirScriptBuilder) obj);
                }
            });
        }
        List<FirReplSnippetConfiguratorExtension> replSnippetConfigurators = FirReplSnippetConfiguratorExtensionKt.getReplSnippetConfigurators(FirExtensionServiceKt.getExtensionService(firSession));
        ArrayList arrayList = new ArrayList();
        for (T t2 : replSnippetConfigurators) {
            if (((FirReplSnippetConfiguratorExtension) t2).isReplSnippetsSource(sourceFile, firSourceElement$default)) {
                arrayList.add(t2);
            }
        }
        if (arrayList.size() <= 1) {
            final FirReplSnippetConfiguratorExtension firReplSnippetConfiguratorExtension = (FirReplSnippetConfiguratorExtension) CollectionsKt.firstOrNull(arrayList);
            return convertReplSnippet(declaration, firSourceElement$default, sourceFile.getName(), new Function1() { // from class: cq
                public final Object invoke(Object obj) {
                    return AbstractRawFirBuilder.h(firReplSnippetConfiguratorExtension, fileBuilder, sourceFile, this, (FirReplSnippetBuilder) obj);
                }
            }, new Function1() { // from class: dq
                public final Object invoke(Object obj) {
                    return AbstractRawFirBuilder.a(firReplSnippetConfiguratorExtension, sourceFile, firSourceElement$default, this, (FirBlockBuilder) obj);
                }
            }, new Function1() { // from class: eq
                public final Object invoke(Object obj) {
                    return AbstractRawFirBuilder.k(firReplSnippetConfiguratorExtension, sourceFile, firSourceElement$default, this, (List) obj);
                }
            });
        }
        KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("More than one REPL snippet configurator is found for the file");
        ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
        exceptionAttachmentBuilder.withEntry("fileName", sourceFile.getName());
        exceptionAttachmentBuilder.withEntry("configurators", CollectionsKt.joinToString$default(arrayList, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: bq
            public final Object invoke(Object obj) {
                return AbstractRawFirBuilder.convertScriptOrSnippets$lambda$1$1$0((FirReplSnippetConfiguratorExtension) obj);
            }
        }, 31, (Object) null));
        kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
        throw kotlinIllegalArgumentExceptionWithAttachments;
    }

    public final FirExpression convertUnaryPlusMinusCallOnIntegerLiteralIfNecessary(T source, FirExpression receiver, IElementType operationToken) {
        source.getClass();
        receiver.getClass();
        operationToken.getClass();
        if (!(receiver instanceof FirLiteralExpression)) {
            return null;
        }
        FirLiteralExpression firLiteralExpression = (FirLiteralExpression) receiver;
        ConstantValueKind kind = firLiteralExpression.getKind();
        ConstantValueKind.IntegerLiteral integerLiteral = ConstantValueKind.IntegerLiteral.INSTANCE;
        if (!Intrinsics.areEqual(kind, integerLiteral)) {
            return null;
        }
        KtSingleValueToken ktSingleValueToken = KtTokens.PLUS;
        if (!Intrinsics.areEqual(operationToken, ktSingleValueToken) && !Intrinsics.areEqual(operationToken, KtTokens.MINUS)) {
            return null;
        }
        Object value = firLiteralExpression.getValue();
        value.getClass();
        long jLongValue = ((Long) value).longValue();
        if (Intrinsics.areEqual(operationToken, KtTokens.MINUS)) {
            jLongValue = -jLongValue;
        } else if (!Intrinsics.areEqual(operationToken, ktSingleValueToken)) {
            k2d.a("Should not be here");
            return null;
        }
        return FirConstExpressionBuilderKt.buildLiteralExpression$default(toFirSourceElement$default(this, source, null, 1, null), integerLiteral, Long.valueOf(jLongValue), null, false, null, 40, null);
    }

    public final Name convertValueParameterName(Name safeName, ValueParameterDeclaration valueParameterDeclaration, Function0<String> rawName) {
        safeName.getClass();
        valueParameterDeclaration.getClass();
        rawName.getClass();
        int i = WhenMappings.$EnumSwitchMapping$1[valueParameterDeclaration.ordinal()];
        if (i == 1 && Intrinsics.areEqual(rawName.invoke(), InlineClassManglingUtilsKt.NOT_INLINE_CLASS_PARAMETER_PLACEHOLDER)) {
            return SpecialNames.UNDERSCORE_FOR_UNUSED_VAR;
        }
        return ((i == 2 || i == 3) && Intrinsics.areEqual(safeName.asString(), InlineClassManglingUtilsKt.NOT_INLINE_CLASS_PARAMETER_PLACEHOLDER)) ? SpecialNames.UNDERSCORE_FOR_UNUSED_VAR : safeName;
    }

    public final FirErrorPrimaryConstructorBuilder createErrorConstructorBuilder(ConeDiagnostic diagnostic) {
        diagnostic.getClass();
        FirErrorPrimaryConstructorBuilder firErrorPrimaryConstructorBuilder = new FirErrorPrimaryConstructorBuilder();
        firErrorPrimaryConstructorBuilder.setDiagnostic(diagnostic);
        return firErrorPrimaryConstructorBuilder;
    }

    public final FirErrorTypeRef createNoTypeForParameterTypeRef(KtSourceElement parameterSource) {
        parameterSource.getClass();
        FirErrorTypeRefBuilder firErrorTypeRefBuilder = new FirErrorTypeRefBuilder();
        firErrorTypeRefBuilder.setSource(parameterSource);
        firErrorTypeRefBuilder.setDiagnostic(new ConeSimpleDiagnostic("No type for parameter", DiagnosticKind.ValueParameterWithNoTypeAnnotation));
        return firErrorTypeRefBuilder.build();
    }

    public final ConeClassLikeType currentDispatchReceiverType() {
        return ConversionUtilsKt.currentDispatchReceiverType(this.context);
    }

    public final ConeClassLikeType dispatchReceiverForInnerClassConstructor() {
        List<ConeClassLikeType> dispatchReceiverTypesStack = this.context.getDispatchReceiverTypesStack();
        return (ConeClassLikeType) CollectionsKt.getOrNull(dispatchReceiverTypesStack, CollectionsKt.getLastIndex(dispatchReceiverTypesStack) - 1);
    }

    /* JADX WARN: Code duplicated, block: B:76:0x01e4  */
    public final FirStatement generateAssignment(T t, final KtSourceElement ktSourceElement, final KtSourceElement ktSourceElement2, final FirExpression firExpression, final FirOperation firOperation, final List<? extends FirAnnotation> list, final T t2, final boolean z, final Function1<? super T, ? extends FirExpression> function1) {
        KtSourceElement ktSourceElementFakeElement$default;
        FirExpression firExpressionPullUpSafeCallIfNecessary;
        ktSourceElement.getClass();
        firExpression.getClass();
        firOperation.getClass();
        list.getClass();
        function1.getClass();
        T tUnwrap = unwrap(t);
        if (tUnwrap == null) {
            FirErrorExpressionBuilder firErrorExpressionBuilder = new FirErrorExpressionBuilder();
            firErrorExpressionBuilder.setSource(ktSourceElement);
            firErrorExpressionBuilder.setDiagnostic(new ConeSyntaxDiagnostic("Inc/dec without operand"));
            return firErrorExpressionBuilder.mo288build();
        }
        if (Intrinsics.areEqual(getElementType(tUnwrap), KtNodeTypes.ARRAY_ACCESS_EXPRESSION)) {
            FirOperation firOperation2 = FirOperation.ASSIGN;
            if (firOperation == firOperation2) {
                this.context.getArraySetArgument().put(tUnwrap, firExpression);
            }
            FirBlockBuilder firBlockBuilder = new FirBlockBuilder();
            if (firOperation == firOperation2) {
                FirExpression firExpression2 = (FirExpression) function1.invoke(tUnwrap);
                firExpression2.replaceAnnotations(UtilsKt.smartPlus(firExpression2.getAnnotations(), list));
                KtSourceElement source = firExpression2.getSource();
                firBlockBuilder.setSource(source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.IndexedAssignmentCoercionBlock.INSTANCE, null, 2, null) : null);
                List<FirStatement> statements = firBlockBuilder.getStatements();
                FirQualifiedAccessExpression firQualifiedAccessExpression = firExpression2 instanceof FirQualifiedAccessExpression ? (FirQualifiedAccessExpression) firExpression2 : null;
                if (firQualifiedAccessExpression != null && (firExpressionPullUpSafeCallIfNecessary = ConversionUtilsKt.pullUpSafeCallIfNecessary(firQualifiedAccessExpression)) != null) {
                    firExpression2 = firExpressionPullUpSafeCallIfNecessary;
                }
                statements.add(firExpression2);
            } else {
                FirExpression firExpression3 = (FirExpression) function1.invoke(tUnwrap);
                FirStatement firStatementBuildPossiblyUnderSafeCall = buildPossiblyUnderSafeCall(firExpression3, ConversionUtilsKt.isChildInParentheses(toFirSourceElement$default(this, tUnwrap, null, 1, null)), firExpression3.getSource(), new Function1() { // from class: xp
                    public final Object invoke(Object obj) {
                        return AbstractRawFirBuilder.generateAssignment$lambda$1$0(this.b, ktSourceElement, ktSourceElement2, firOperation, list, t2, function1, z, (FirExpression) obj);
                    }
                });
                KtSourceElement source2 = firStatementBuildPossiblyUnderSafeCall.getSource();
                firBlockBuilder.setSource(source2 != null ? KtSourceElementKt.fakeElement$default(source2, KtFakeSourceElementKind.IndexedAssignmentCoercionBlock.INSTANCE, null, 2, null) : null);
                firBlockBuilder.getStatements().add(firStatementBuildPossiblyUnderSafeCall);
            }
            List<FirStatement> statements2 = firBlockBuilder.getStatements();
            FirUnitExpressionBuilder firUnitExpressionBuilder = new FirUnitExpressionBuilder();
            firUnitExpressionBuilder.setSource(KtSourceElementKt.fakeElement$default(ktSourceElement, KtFakeSourceElementKind.ImplicitUnit.IndexedAssignmentCoercion.INSTANCE, null, 2, null));
            statements2.add(firUnitExpressionBuilder.mo288build());
            return firBlockBuilder.mo288build();
        }
        if (FirOperation.INSTANCE.getASSIGNMENTS().contains(firOperation) && firOperation != FirOperation.ASSIGN) {
            FirExpression firExpressionMo288build = t != null ? (FirExpression) function1.invoke(t) : null;
            if (firExpressionMo288build instanceof FirQualifiedAccessExpression) {
                FirQualifiedAccessExpression firQualifiedAccessExpression2 = (FirQualifiedAccessExpression) firExpressionMo288build;
                KtSourceElement source3 = firQualifiedAccessExpression2.getSource();
                firQualifiedAccessExpression2.replaceSource(source3 != null ? KtSourceElementKt.fakeElement$default(source3, UtilsKt.toAugmentedAssignSourceKind(firOperation), null, 2, null) : null);
            }
            if (firExpressionMo288build == null) {
                FirErrorExpressionBuilder firErrorExpressionBuilder2 = new FirErrorExpressionBuilder();
                firErrorExpressionBuilder2.setSource(ktSourceElement);
                StringBuilder sb = new StringBuilder("Unsupported left value of assignment: ");
                PsiElement psi = KtSourceElementKt.getPsi(ktSourceElement);
                sb.append(psi != null ? psi.getText() : null);
                firErrorExpressionBuilder2.setDiagnostic(new ConeSimpleDiagnostic(sb.toString(), DiagnosticKind.ExpressionExpected));
                firExpressionMo288build = firErrorExpressionBuilder2.mo288build();
            }
            final FirExpression firExpression4 = firExpressionMo288build;
            final boolean zSupportsFeature = FirLanguageSettingsComponentKt.getLanguageVersionSettings(this.baseSession).supportsFeature(LanguageFeature.ForbidParenthesizedLhsInAssignments);
            return buildPossiblyUnderSafeCall(firExpression4, z, null, new Function1() { // from class: yp
                public final Object invoke(Object obj) {
                    return AbstractRawFirBuilder.b(z, zSupportsFeature, this, firOperation, ktSourceElement, firExpression4, firExpression, list, (FirExpression) obj);
                }
            });
        }
        if (firOperation != FirOperation.ASSIGN) {
            w01.a("Failed requirement.");
            return null;
        }
        if (Intrinsics.areEqual(t != null ? getElementType(t) : null, KtNodeTypes.SAFE_ACCESS_EXPRESSION)) {
            Object objInvoke = function1.invoke(t);
            FirSafeCallExpression firSafeCallExpression = objInvoke instanceof FirSafeCallExpression ? (FirSafeCallExpression) objInvoke : null;
            if (firSafeCallExpression != null) {
                return putAssignmentToSafeCall(firSafeCallExpression, ktSourceElement, firExpression, list);
            }
        }
        FirExpression firExpressionMo288build2 = (FirExpression) function1.invoke(tUnwrap);
        FirVariableAssignmentBuilder firVariableAssignmentBuilder = new FirVariableAssignmentBuilder();
        firVariableAssignmentBuilder.setSource(ktSourceElement);
        if (ktSourceElement.getKind() instanceof KtFakeSourceElementKind.DesugaredIncrementOrDecrement) {
            FirDesugaredAssignmentValueReferenceExpressionBuilder firDesugaredAssignmentValueReferenceExpressionBuilder = new FirDesugaredAssignmentValueReferenceExpressionBuilder();
            FirExpressionRef<FirExpression> firExpressionRef = new FirExpressionRef<>();
            firExpressionRef.bind(firExpressionMo288build2);
            firDesugaredAssignmentValueReferenceExpressionBuilder.setExpressionRef(firExpressionRef);
            KtSourceElement source4 = firExpressionMo288build2.getSource();
            if (source4 != null) {
                KtFakeSourceElementKind.DesugaredIncrementOrDecrement kind = ktSourceElement.getKind();
                kind.getClass();
                ktSourceElementFakeElement$default = KtSourceElementKt.fakeElement$default(source4, kind, null, 2, null);
                if (ktSourceElementFakeElement$default == null) {
                    ktSourceElementFakeElement$default = KtSourceElementKt.fakeElement$default(ktSourceElement, KtFakeSourceElementKind.DesugaredAssignmentLValueSourceIsNull.INSTANCE, null, 2, null);
                }
            } else {
                ktSourceElementFakeElement$default = KtSourceElementKt.fakeElement$default(ktSourceElement, KtFakeSourceElementKind.DesugaredAssignmentLValueSourceIsNull.INSTANCE, null, 2, null);
            }
            firDesugaredAssignmentValueReferenceExpressionBuilder.setSource(ktSourceElementFakeElement$default);
            firExpressionMo288build2 = firDesugaredAssignmentValueReferenceExpressionBuilder.mo288build();
        }
        firVariableAssignmentBuilder.setLValue(firExpressionMo288build2);
        firVariableAssignmentBuilder.setRValue(firExpression);
        CollectionsKt.addAll(firVariableAssignmentBuilder.getAnnotations(), list);
        return firVariableAssignmentBuilder.mo288build();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public final FirExpression generateConstantExpressionByLiteral(T expression) throws KotlinIllegalArgumentExceptionWithAttachments {
        Object numericLiteral;
        Long l;
        ConstantValueKind.IntegerLiteral integerLiteral;
        Long l2;
        expression.getClass();
        IElementType elementType = getElementType(expression);
        String asText = getAsText(expression);
        KtSourceElement firSourceElement$default = toFirSourceElement$default(this, expression, null, 1, null);
        IElementType iElementType = KtNodeTypes.INTEGER_CONSTANT;
        if (!Intrinsics.areEqual(elementType, iElementType) && !Intrinsics.areEqual(elementType, KtNodeTypes.FLOAT_CONSTANT)) {
            numericLiteral = Intrinsics.areEqual(elementType, KtNodeTypes.BOOLEAN_CONSTANT) ? Boolean.valueOf(ParseUtilsKt.parseBoolean(asText)) : null;
        } else {
            if (ParseUtilsKt.hasIllegalUnderscore(asText, elementType)) {
                return generateConstantExpressionByLiteral$reportIncorrectConstant(firSourceElement$default, asText, DiagnosticKind.IllegalUnderscore);
            }
            numericLiteral = ParseUtilsKt.parseNumericLiteral(asText, elementType);
        }
        if (!Intrinsics.areEqual(elementType, iElementType)) {
            if (Intrinsics.areEqual(elementType, KtNodeTypes.FLOAT_CONSTANT)) {
                if (numericLiteral instanceof Float) {
                    return FirExpressionUtilKt.buildConstOrErrorExpression(firSourceElement$default, ConstantValueKind.Float.INSTANCE, numericLiteral, "float", asText, DiagnosticKind.FloatLiteralOutOfRange);
                }
                Object obj = numericLiteral;
                return FirExpressionUtilKt.buildConstOrErrorExpression(firSourceElement$default, ConstantValueKind.Double.INSTANCE, obj instanceof Double ? (Double) obj : null, "double", asText, DiagnosticKind.FloatLiteralOutOfRange);
            }
            Object obj2 = numericLiteral;
            if (Intrinsics.areEqual(elementType, KtNodeTypes.CHARACTER_CONSTANT)) {
                CharacterWithDiagnostic character = ConversionUtilsKt.parseCharacter(asText);
                ConstantValueKind.Char r2 = ConstantValueKind.Char.INSTANCE;
                Character value = character.getValue();
                DiagnosticKind diagnostic = character.getDiagnostic();
                if (diagnostic == null) {
                    diagnostic = DiagnosticKind.IllegalConstExpression;
                }
                return FirExpressionUtilKt.buildConstOrErrorExpression(firSourceElement$default, r2, value, "character", asText, diagnostic);
            }
            if (Intrinsics.areEqual(elementType, KtNodeTypes.BOOLEAN_CONSTANT)) {
                ConstantValueKind.Boolean r7 = ConstantValueKind.Boolean.INSTANCE;
                obj2.getClass();
                return FirConstExpressionBuilderKt.buildLiteralExpression$default(firSourceElement$default, r7, (Boolean) obj2, null, false, null, 40, null);
            }
            if (Intrinsics.areEqual(elementType, KtNodeTypes.NULL)) {
                return FirConstExpressionBuilderKt.buildLiteralExpression$default(firSourceElement$default, ConstantValueKind.Null.INSTANCE, null, null, false, null, 40, null);
            }
            KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Unknown literal type: " + elementType, (Throwable) null);
            ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
            withSourceElementEntry(exceptionAttachmentBuilder, "literal", expression);
            kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
            throw kotlinIllegalArgumentExceptionWithAttachments;
        }
        DiagnosticKind diagnosticKind = DiagnosticKind.IllegalConstExpression;
        if (numericLiteral == null) {
            diagnosticKind = DiagnosticKind.IntLiteralOutOfRange;
            integerLiteral = ConstantValueKind.IntegerLiteral.INSTANCE;
            l = null;
        } else {
            if (!(numericLiteral instanceof Long)) {
                return generateConstantExpressionByLiteral$reportIncorrectConstant(firSourceElement$default, asText, diagnosticKind);
            }
            if (ParseUtilsKt.hasUnsignedLongSuffix(asText)) {
                if (StringsKt.endsWith$default(asText, "l", false, 2, (Object) null)) {
                    diagnosticKind = DiagnosticKind.WrongLongSuffix;
                    l = null;
                } else {
                    l = (Long) numericLiteral;
                }
                integerLiteral = ConstantValueKind.UnsignedLong.INSTANCE;
            } else if (ParseUtilsKt.hasLongSuffix(asText)) {
                if (StringsKt.endsWith$default(asText, "l", false, 2, (Object) null)) {
                    diagnosticKind = DiagnosticKind.WrongLongSuffix;
                    l = null;
                } else {
                    l = (Long) numericLiteral;
                }
                integerLiteral = ConstantValueKind.Long.INSTANCE;
            } else if (ParseUtilsKt.hasUnsignedSuffix(asText)) {
                l = (Long) numericLiteral;
                integerLiteral = ConstantValueKind.UnsignedIntegerLiteral.INSTANCE;
            } else {
                l = (Long) numericLiteral;
                integerLiteral = ConstantValueKind.IntegerLiteral.INSTANCE;
            }
        }
        if (hasLeadingZeros(asText)) {
            diagnosticKind = DiagnosticKind.IntLiteralWithLeadingZeros;
            l2 = null;
        } else {
            l2 = l;
        }
        return FirExpressionUtilKt.buildConstOrErrorExpression(firSourceElement$default, integerLiteral, l2, "integer", asText, diagnosticKind);
    }

    public final FirExpression generateIncrementOrDecrementBlock(T wholeExpression, T operationReference, T receiver, Name callName, boolean prefix, Function1<? super T, ? extends FirExpression> convert) {
        wholeExpression.getClass();
        callName.getClass();
        convert.getClass();
        T tUnwrap = unwrap(receiver);
        if (tUnwrap == null) {
            FirErrorExpressionBuilder firErrorExpressionBuilder = new FirErrorExpressionBuilder();
            firErrorExpressionBuilder.setSource(toFirSourceElement$default(this, wholeExpression, null, 1, null));
            firErrorExpressionBuilder.setDiagnostic(new ConeSyntaxDiagnostic("Inc/dec without operand"));
            return firErrorExpressionBuilder.mo288build();
        }
        if (Intrinsics.areEqual(getElementType(tUnwrap), KtNodeTypes.ARRAY_ACCESS_EXPRESSION)) {
            return generateIncrementOrDecrementBlockForArrayAccess(wholeExpression, operationReference, tUnwrap, callName, prefix, convert);
        }
        FirIncrementDecrementExpressionBuilder firIncrementDecrementExpressionBuilder = new FirIncrementDecrementExpressionBuilder();
        firIncrementDecrementExpressionBuilder.setSource(toFirSourceElement$default(this, wholeExpression, null, 1, null));
        firIncrementDecrementExpressionBuilder.setOperationSource(operationReference != null ? toFirSourceElement$default(this, operationReference, null, 1, null) : null);
        firIncrementDecrementExpressionBuilder.setOperationName(callName);
        firIncrementDecrementExpressionBuilder.setPrefix(prefix);
        firIncrementDecrementExpressionBuilder.setExpression((FirExpression) convert.invoke(tUnwrap));
        return ConversionUtilsKt.pullUpSafeCallIfNecessary(firIncrementDecrementExpressionBuilder.mo288build(), new PropertyReference1Impl() { // from class: org.jetbrains.kotlin.fir.builder.AbstractRawFirBuilder.generateIncrementOrDecrementBlock.2
            public Object get(Object obj) {
                return ((FirIncrementDecrementExpression) obj).getExpression();
            }
        }, AnonymousClass3.INSTANCE);
    }

    public abstract T getAnnotatedExpression(T t);

    public abstract T getArrayExpression(T t);

    public abstract String getAsText(T t);

    public final FirModuleData getBaseModuleData() {
        return this.baseModuleData;
    }

    public final FirSession getBaseSession() {
        return this.baseSession;
    }

    public abstract T getChildNodeByType(T t, IElementType iElementType);

    public final Context<T> getContext() {
        return this.context;
    }

    public abstract IElementType getElementType(T t);

    public abstract T getExpressionInParentheses(T t);

    public final ForbiddenLabelKind getForbiddenLabelKind(String rawName, boolean isMultipleLabel) {
        rawName.getClass();
        if (ConversionUtilsKt.isUnderscore(rawName)) {
            return ForbiddenLabelKind.UNDERSCORE_IS_RESERVED;
        }
        if (isMultipleLabel) {
            return ForbiddenLabelKind.MULTIPLE_LABEL;
        }
        return null;
    }

    public final boolean getImitateLambdaSuspendModifier() {
        return this.imitateLambdaSuspendModifier;
    }

    public final FirImplicitBuiltinTypeRef getImplicitAnnotationType() {
        return this.implicitAnnotationType;
    }

    public final FirImplicitBuiltinTypeRef getImplicitAnyType() {
        return this.implicitAnyType;
    }

    public final FirImplicitBuiltinTypeRef getImplicitEnumType() {
        return this.implicitEnumType;
    }

    public final FirImplicitBuiltinTypeRef getImplicitUnitType() {
        return this.implicitUnitType;
    }

    public abstract List<T> getIndexExpressions(T t);

    public abstract String getLabelName(T t);

    public abstract T getLabeledExpression(T t);

    public final boolean getNameBasedDestructuringShortForm() {
        return this.nameBasedDestructuringShortForm;
    }

    public abstract T getReceiverExpression(T t);

    public abstract Name getReferencedNameAsName(T t);

    public abstract T getSelectorExpression(T t);

    public final void initCompanionObjectSymbolAttr(FirRegularClassBuilder firRegularClassBuilder) {
        T next;
        firRegularClassBuilder.getClass();
        Iterator<T> it = firRegularClassBuilder.getDeclarations().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = (T) null;
                break;
            }
            next = it.next();
            FirRegularClass firRegularClass = (FirDeclaration) next;
            if ((firRegularClass instanceof FirRegularClass) && firRegularClass.getStatus().isCompanion()) {
                break;
            }
        }
        FirRegularClass firRegularClass2 = next;
        firRegularClassBuilder.setCompanionObjectSymbol(firRegularClass2 != null ? firRegularClass2.getSymbol() : null);
    }

    public final void initContainingClassAttr(FirCallableDeclaration firCallableDeclaration) {
        firCallableDeclaration.getClass();
        ConversionUtilsKt.initContainingClassAttr(firCallableDeclaration, this.context);
    }

    public final void initContainingClassForLocalAttr(FirClassLikeDeclaration firClassLikeDeclaration) {
        ConeClassLikeType coneClassLikeTypeCurrentDispatchReceiverType;
        firClassLikeDeclaration.getClass();
        if (!firClassLikeDeclaration.getIsLocal() || (coneClassLikeTypeCurrentDispatchReceiverType = currentDispatchReceiverType()) == null) {
            return;
        }
        ClassMembersKt.setContainingClassForLocalAttr(firClassLikeDeclaration, coneClassLikeTypeCurrentDispatchReceiverType.getLookupTag());
    }

    public final void initContainingScriptOrReplAttr(FirRegularClass firRegularClass) {
        firRegularClass.getClass();
        FirScriptSymbol containingScriptSymbol = this.context.getContainingScriptSymbol();
        if (containingScriptSymbol != null) {
            ClassMembersKt.setContainingScriptSymbolAttr(firRegularClass, containingScriptSymbol);
        }
        FirReplSnippetSymbol containingReplSymbol = this.context.getContainingReplSymbol();
        if (containingReplSymbol != null) {
            ClassMembersKt.setContainingReplSymbolAttr(firRegularClass, containingReplSymbol);
        }
    }

    public final boolean isDirectlyInsideCompanionBlock() {
        FirBasedSymbol<?> currentCompanionBlockOwnerOrNull = this.context.getCurrentCompanionBlockOwnerOrNull();
        return currentCompanionBlockOwnerOrNull != null && Intrinsics.areEqual(currentCompanionBlockOwnerOrNull, this.context.getContainerSymbolIfAny());
    }

    public final boolean isImplicitlyActual(FirDeclarationStatus status, ClassKind classKind) {
        status.getClass();
        classKind.getClass();
        if (status.isActual()) {
            return status.isInline() || status.isValue() || classKind == ClassKind.ANNOTATION_CLASS;
        }
        return false;
    }

    public boolean isReplSnippet(T script, KtSourceFile sourceFile) {
        script.getClass();
        sourceFile.getClass();
        KtSourceElement firSourceElement$default = toFirSourceElement$default(this, script, null, 1, null);
        List<FirReplSnippetConfiguratorExtension> replSnippetConfigurators = FirReplSnippetConfiguratorExtensionKt.getReplSnippetConfigurators(FirExtensionServiceKt.getExtensionService(this.baseSession));
        if ((replSnippetConfigurators instanceof Collection) && replSnippetConfigurators.isEmpty()) {
            return false;
        }
        Iterator<T> it = replSnippetConfigurators.iterator();
        while (it.hasNext()) {
            if (((FirReplSnippetConfiguratorExtension) it.next()).isReplSnippetsSource(sourceFile, firSourceElement$default)) {
                return true;
            }
        }
        return false;
    }

    public abstract boolean isVararg(T t);

    public final <T> T pop(List<T> list) {
        list.getClass();
        T t = (T) CollectionsKt.lastOrNull(list);
        if (t != null) {
            list.remove(list.size() - 1);
        }
        return t;
    }

    public final FirLoopTarget prepareTarget(FirLoopBuilder firLoopBuilder, FirLabel firLabel) {
        firLoopBuilder.getClass();
        firLoopBuilder.setLabel(firLabel);
        FirLoopTarget firLoopTarget = new FirLoopTarget(firLabel != null ? firLabel.getName() : null);
        this.context.getFirLoopTargets().add(firLoopTarget);
        return firLoopTarget;
    }

    public final void registerSelfType(FirResolvedTypeRef selfType) {
        selfType.getClass();
        List<ConeClassLikeType> dispatchReceiverTypesStack = this.context.getDispatchReceiverTypesStack();
        ConeKotlinType coneType = selfType.getConeType();
        coneType.getClass();
        dispatchReceiverTypesStack.add((ConeClassLikeType) coneType);
    }

    public final <T> T removeLast(List<T> list) {
        list.getClass();
        return list.remove(list.size() - 1);
    }

    public final FirResolvedTypeRef toDelegatedSelfType(T t, List<? extends FirTypeParameterRef> list, FirClassLikeSymbol<?> firClassLikeSymbol) {
        t.getClass();
        list.getClass();
        firClassLikeSymbol.getClass();
        FirResolvedTypeRefBuilder firResolvedTypeRefBuilder = new FirResolvedTypeRefBuilder();
        firResolvedTypeRefBuilder.setSource(toFirSourceElement(t, KtFakeSourceElementKind.ClassSelfTypeRef.INSTANCE));
        ConeClassLikeLookupTag lookupTag = firClassLikeSymbol.getLookupTag();
        List<? extends FirTypeParameterRef> list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator<T> it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add(new ConeTypeParameterTypeImpl(((FirTypeParameterRef) it.next()).getSymbol().getLookupTag(), false, null, 4, null));
        }
        firResolvedTypeRefBuilder.setConeType(new ConeClassLikeTypeImpl(lookupTag, (ConeTypeProjection[]) arrayList.toArray(new ConeTypeParameterTypeImpl[0]), false, null, 8, null));
        return firResolvedTypeRefBuilder.build();
    }

    public abstract KtSourceElement toFirSourceElement(T t, KtFakeSourceElementKind ktFakeSourceElementKind);

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    /* JADX WARN: Code duplicated, block: B:13:0x004d  */
    public final FirExpression toInterpolatingCall(T[] tArr, T t, Function1<? super T, ? extends IElementType> function1, Function2<? super T, ? super String, ? extends Collection<? extends FirExpression>> function2, Function0<String> function0) throws UninitializedPropertyAccessException {
        tArr.getClass();
        t.getClass();
        function1.getClass();
        function2.getClass();
        function0.getClass();
        FirStringConcatenationCallBuilder firStringConcatenationCallBuilder = new FirStringConcatenationCallBuilder();
        StringBuilder sb = new StringBuilder();
        FirArgumentListBuilder firArgumentListBuilder = new FirArgumentListBuilder();
        int length = tArr.length;
        int i = 0;
        boolean z = false;
        while (true) {
            if (i >= length) {
                break;
            }
            T t2 = tArr[i];
            if (t2 != null) {
                IElementType iElementType = (IElementType) function1.invoke(t2);
                if (!Intrinsics.areEqual(iElementType, KtNodeTypes.STRING_INTERPOLATION_PREFIX) && !Intrinsics.areEqual(iElementType, KtTokens.OPEN_QUOTE) && !Intrinsics.areEqual(iElementType, KtTokens.CLOSING_QUOTE)) {
                    if (Intrinsics.areEqual(iElementType, KtNodeTypes.LITERAL_STRING_TEMPLATE_ENTRY)) {
                        sb.append(getAsText(t2));
                        firArgumentListBuilder.getArguments().add(FirConstExpressionBuilderKt.buildLiteralExpression$default(toFirSourceElement$default(this, t2, null, 1, null), ConstantValueKind.String.INSTANCE, getAsText(t2), null, false, null, 40, null));
                    } else if (Intrinsics.areEqual(iElementType, KtNodeTypes.ESCAPE_STRING_TEMPLATE_ENTRY)) {
                        String asText = getAsText(t2);
                        CharacterWithDiagnostic characterWithDiagnosticEscapedStringToCharacter = ConversionUtilsKt.escapedStringToCharacter(asText);
                        Character value = characterWithDiagnosticEscapedStringToCharacter.getValue();
                        if (value != null) {
                            sb.append(value.charValue());
                        }
                        List<FirExpression> arguments = firArgumentListBuilder.getArguments();
                        KtSourceElement firSourceElement$default = toFirSourceElement$default(this, t2, null, 1, null);
                        ConstantValueKind.String string = ConstantValueKind.String.INSTANCE;
                        String strValueOf = value != null ? String.valueOf(value.charValue()) : null;
                        DiagnosticKind diagnostic = characterWithDiagnosticEscapedStringToCharacter.getDiagnostic();
                        if (diagnostic == null) {
                            diagnostic = DiagnosticKind.IllegalConstExpression;
                        }
                        arguments.add(FirExpressionUtilKt.buildConstOrErrorExpression(firSourceElement$default, string, strValueOf, "character", asText, diagnostic));
                    } else {
                        if (Intrinsics.areEqual(iElementType, KtNodeTypes.SHORT_STRING_TEMPLATE_ENTRY) || Intrinsics.areEqual(iElementType, KtNodeTypes.LONG_STRING_TEMPLATE_ENTRY)) {
                            Collection collection = (Collection) function2.invoke(t2, "Incorrect template argument");
                            if (collection.isEmpty()) {
                                List<FirExpression> arguments2 = firArgumentListBuilder.getArguments();
                                FirErrorExpressionBuilder firErrorExpressionBuilder = new FirErrorExpressionBuilder();
                                firErrorExpressionBuilder.setSource(toFirSourceElement$default(this, t2, null, 1, null));
                                firErrorExpressionBuilder.setDiagnostic(new ConeSyntaxDiagnostic("Incorrect template argument"));
                                arguments2.add(firErrorExpressionBuilder.mo288build());
                            } else {
                                CollectionsKt.addAll(firArgumentListBuilder.getArguments(), collection);
                            }
                        } else {
                            List<FirExpression> arguments3 = firArgumentListBuilder.getArguments();
                            FirErrorExpressionBuilder firErrorExpressionBuilder2 = new FirErrorExpressionBuilder();
                            firErrorExpressionBuilder2.setSource(toFirSourceElement$default(this, t2, null, 1, null));
                            firErrorExpressionBuilder2.setDiagnostic(new ConeSyntaxDiagnostic("Incorrect template entry: " + getAsText(t2)));
                            arguments3.add(firErrorExpressionBuilder2.mo288build());
                        }
                        z = true;
                    }
                }
            }
            i++;
        }
        firStringConcatenationCallBuilder.setArgumentList(firArgumentListBuilder.build());
        firStringConcatenationCallBuilder.setSource(toFirSourceElement$default(this, t, null, 1, null));
        firStringConcatenationCallBuilder.setInterpolationPrefix((String) function0.invoke());
        if (!z) {
            List<FirExpression> arguments4 = firStringConcatenationCallBuilder.getArgumentList().getArguments();
            if (!(arguments4 instanceof Collection) || !arguments4.isEmpty()) {
                Iterator<T> it = arguments4.iterator();
                while (it.hasNext()) {
                    if (((FirExpression) it.next()) instanceof FirErrorExpression) {
                    }
                }
            }
            KtSourceElement source = firStringConcatenationCallBuilder.getSource();
            ConstantValueKind.String string2 = ConstantValueKind.String.INSTANCE;
            String string3 = sb.toString();
            String interpolationPrefix = firStringConcatenationCallBuilder.getInterpolationPrefix();
            return FirConstExpressionBuilderKt.buildLiteralExpression$default(source, string2, string3, null, false, interpolationPrefix.length() > 0 ? interpolationPrefix : null, 8, null);
        }
        return firStringConcatenationCallBuilder.mo288build();
    }

    /* JADX WARN: Code duplicated, block: B:6:0x0012  */
    public final FirReturnExpression toReturn(FirExpression firExpression, KtSourceElement ktSourceElement, String str, boolean z) {
        KtSourceElement ktSourceElementFakeElement$default;
        firExpression.getClass();
        FirReturnExpressionBuilder firReturnExpressionBuilder = new FirReturnExpressionBuilder();
        FirFunctionTarget firFunctionTarget = null;
        if (z) {
            if (ktSourceElement != null) {
                ktSourceElementFakeElement$default = KtSourceElementKt.realElement(ktSourceElement);
            } else {
                ktSourceElementFakeElement$default = null;
            }
        } else if (ktSourceElement != null) {
            ktSourceElementFakeElement$default = KtSourceElementKt.fakeElement$default(ktSourceElement, KtFakeSourceElementKind.ImplicitReturn.FromExpressionBody.INSTANCE, null, 2, null);
        } else {
            ktSourceElementFakeElement$default = null;
        }
        firReturnExpressionBuilder.setSource(ktSourceElementFakeElement$default);
        firReturnExpressionBuilder.setResult(firExpression);
        Context<T> context = this.context;
        if (str == null) {
            List<FirFunctionTarget> firFunctionTargets = context.getFirFunctionTargets();
            ListIterator<FirFunctionTarget> listIterator = firFunctionTargets.listIterator(firFunctionTargets.size());
            while (listIterator.hasPrevious()) {
                FirFunctionTarget firFunctionTargetPrevious = listIterator.previous();
                if (!firFunctionTargetPrevious.getIsLambda()) {
                    firFunctionTarget = firFunctionTargetPrevious;
                    break;
                }
            }
            FirFunctionTarget firFunctionTarget2 = firFunctionTarget;
            if (firFunctionTarget2 == null) {
                firFunctionTarget2 = new FirFunctionTarget(str, false);
                toReturn$lambda$0$bindToErrorFunction(firFunctionTarget2, ktSourceElement, this, "Cannot bind unlabeled return to a function", DiagnosticKind.ReturnNotAllowed);
            }
            firReturnExpressionBuilder.setTarget(firFunctionTarget2);
        } else {
            for (FirFunctionTarget firFunctionTarget3 : CollectionsKt.asReversedMutable(context.getFirFunctionTargets())) {
                if (Intrinsics.areEqual(firFunctionTarget3.getLabelName(), str)) {
                    firReturnExpressionBuilder.setTarget(firFunctionTarget3);
                }
            }
            FirFunctionTarget firFunctionTarget4 = new FirFunctionTarget(str, false);
            List<FirLabel> firLabels = this.context.getFirLabels();
            if (!(firLabels instanceof Collection) || !firLabels.isEmpty()) {
                Iterator<T> it = firLabels.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        toReturn$lambda$0$bindToErrorFunction(firFunctionTarget4, ktSourceElement, this, "Cannot bind label " + str + " to a function", DiagnosticKind.UnresolvedLabel);
                        break;
                    }
                    if (Intrinsics.areEqual(((FirLabel) it.next()).getName(), str)) {
                        toReturn$lambda$0$bindToErrorFunction(firFunctionTarget4, ktSourceElement, this, "Label " + str + " does not target a function", DiagnosticKind.NotAFunctionLabel);
                        break;
                    }
                }
            } else {
                toReturn$lambda$0$bindToErrorFunction(firFunctionTarget4, ktSourceElement, this, "Cannot bind label " + str + " to a function", DiagnosticKind.UnresolvedLabel);
                break;
            }
            firReturnExpressionBuilder.setTarget(firFunctionTarget4);
        }
        return firReturnExpressionBuilder.mo288build();
    }

    public final <T> T withCapturedTypeParameters(boolean status, KtSourceElement declarationSource, List<? extends FirTypeParameterRef> currentFirTypeParameters, Function0<? extends T> block) {
        currentFirTypeParameters.getClass();
        block.getClass();
        addCapturedTypeParameters(status, declarationSource, currentFirTypeParameters);
        try {
            return (T) block.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            getContext().popFirTypeParameters();
            InlineMarker.finallyEnd(1);
        }
    }

    public final <T> T withChildClassName(Name name, boolean isExpect, boolean forceLocalContext, Function0<? extends T> l) {
        name.getClass();
        l.getClass();
        if (!forceLocalContext) {
            getContext().setClassName(getContext().getClassName().child(name));
            boolean containerIsExpect = getContext().getContainerIsExpect();
            getContext().setContainerIsExpect(containerIsExpect || isExpect);
            int size = getContext().getDispatchReceiverTypesStack().size();
            try {
                T t = (T) l.invoke();
                InlineMarker.finallyStart(1);
                if (getContext().getDispatchReceiverTypesStack().size() <= size + 1) {
                    if (getContext().getDispatchReceiverTypesStack().size() > size) {
                        getContext().getDispatchReceiverTypesStack().remove(CollectionsKt.getLastIndex(getContext().getDispatchReceiverTypesStack()));
                    }
                    getContext().setClassName(getContext().getClassName().parent());
                    getContext().setContainerIsExpect(containerIsExpect);
                    InlineMarker.finallyEnd(1);
                    return t;
                }
            } catch (Throwable th) {
                InlineMarker.finallyStart(1);
                if (getContext().getDispatchReceiverTypesStack().size() <= size + 1) {
                    if (getContext().getDispatchReceiverTypesStack().size() > size) {
                        getContext().getDispatchReceiverTypesStack().remove(CollectionsKt.getLastIndex(getContext().getDispatchReceiverTypesStack()));
                    }
                    getContext().setClassName(getContext().getClassName().parent());
                    getContext().setContainerIsExpect(containerIsExpect);
                    InlineMarker.finallyEnd(1);
                    throw th;
                }
            }
            v1f.a("Wrong number of ", getContext().getDispatchReceiverTypesStack().size());
            return null;
        }
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
            getContext().setContainerIsExpect(containerIsExpect2 || isExpect);
            int size2 = getContext().getDispatchReceiverTypesStack().size();
            try {
                T t2 = (T) l.invoke();
                InlineMarker.finallyStart(1);
                if (getContext().getDispatchReceiverTypesStack().size() > size2 + 1) {
                    throw new IllegalArgumentException(("Wrong number of " + getContext().getDispatchReceiverTypesStack().size()).toString());
                }
                if (getContext().getDispatchReceiverTypesStack().size() > size2) {
                    getContext().getDispatchReceiverTypesStack().remove(CollectionsKt.getLastIndex(getContext().getDispatchReceiverTypesStack()));
                }
                getContext().setClassName(getContext().getClassName().parent());
                getContext().setContainerIsExpect(containerIsExpect2);
                InlineMarker.finallyEnd(1);
                InlineMarker.finallyStart(1);
                getContext().setClassNameBeforeLocalContext(classNameBeforeLocalContext);
                getContext().setInLocalContext(inLocalContext);
                getContext().setClassName(className);
                getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
                InlineMarker.finallyEnd(1);
                return t2;
            } catch (Throwable th2) {
                InlineMarker.finallyStart(1);
                if (getContext().getDispatchReceiverTypesStack().size() > size2 + 1) {
                    throw new IllegalArgumentException(("Wrong number of " + getContext().getDispatchReceiverTypesStack().size()).toString());
                }
                if (getContext().getDispatchReceiverTypesStack().size() > size2) {
                    getContext().getDispatchReceiverTypesStack().remove(CollectionsKt.getLastIndex(getContext().getDispatchReceiverTypesStack()));
                }
                getContext().setClassName(getContext().getClassName().parent());
                getContext().setContainerIsExpect(containerIsExpect2);
                InlineMarker.finallyEnd(1);
                throw th2;
            }
        } catch (Throwable th3) {
            InlineMarker.finallyStart(1);
            getContext().setClassNameBeforeLocalContext(classNameBeforeLocalContext);
            getContext().setInLocalContext(inLocalContext);
            getContext().setClassName(className);
            getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
            InlineMarker.finallyEnd(1);
            throw th3;
        }
    }

    public final <T> T withChildClassNameRegardlessLocalContext(Name name, boolean isExpect, Function0<? extends T> l) {
        name.getClass();
        l.getClass();
        getContext().setClassName(getContext().getClassName().child(name));
        boolean containerIsExpect = getContext().getContainerIsExpect();
        getContext().setContainerIsExpect(containerIsExpect || isExpect);
        int size = getContext().getDispatchReceiverTypesStack().size();
        try {
            T t = (T) l.invoke();
            InlineMarker.finallyStart(1);
            if (getContext().getDispatchReceiverTypesStack().size() <= size + 1) {
                if (getContext().getDispatchReceiverTypesStack().size() > size) {
                    getContext().getDispatchReceiverTypesStack().remove(CollectionsKt.getLastIndex(getContext().getDispatchReceiverTypesStack()));
                }
                getContext().setClassName(getContext().getClassName().parent());
                getContext().setContainerIsExpect(containerIsExpect);
                InlineMarker.finallyEnd(1);
                return t;
            }
        } catch (Throwable th) {
            InlineMarker.finallyStart(1);
            if (getContext().getDispatchReceiverTypesStack().size() <= size + 1) {
                if (getContext().getDispatchReceiverTypesStack().size() > size) {
                    getContext().getDispatchReceiverTypesStack().remove(CollectionsKt.getLastIndex(getContext().getDispatchReceiverTypesStack()));
                }
                getContext().setClassName(getContext().getClassName().parent());
                getContext().setContainerIsExpect(containerIsExpect);
                InlineMarker.finallyEnd(1);
                throw th;
            }
        }
        v1f.a("Wrong number of ", getContext().getDispatchReceiverTypesStack().size());
        return null;
    }

    public final void withCompanionBlock(Function0<Unit> block) {
        block.getClass();
        FirBasedSymbol<?> currentCompanionBlockOwnerOrNull = getContext().getCurrentCompanionBlockOwnerOrNull();
        getContext().setCurrentCompanionBlockOwnerOrNull(getContext().getContainerSymbolIfAny());
        try {
            block.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            getContext().setCurrentCompanionBlockOwnerOrNull(currentCompanionBlockOwnerOrNull);
            InlineMarker.finallyEnd(1);
        }
    }

    public final <T> T withContainerReplSymbol(FirReplSnippetSymbol symbol, Function0<? extends T> block) {
        symbol.getClass();
        block.getClass();
        if (getContext().getContainingReplSymbol() != null) {
            w01.a("Nested snippets are not supported");
            return null;
        }
        getContext().setContainingReplSymbol(symbol);
        try {
            return (T) block.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            getContext().setContainingReplSymbol(null);
            InlineMarker.finallyEnd(1);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
    public final <T> T withContainerScriptSymbol(FirScriptSymbol symbol, Function0<? extends T> block) throws KotlinIllegalStateExceptionWithAttachments {
        symbol.getClass();
        block.getClass();
        if (getContext().getContainingScriptSymbol() != null) {
            w01.a("Nested scripts are not supported");
            return null;
        }
        getContext().setContainingScriptSymbol(symbol);
        getContext().pushContainerSymbol(symbol);
        try {
            return (T) block.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            getContext().popContainerSymbol(symbol);
            getContext().setContainingScriptSymbol(null);
            InlineMarker.finallyEnd(1);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
    public final <T> T withContainerSymbol(FirBasedSymbol<?> symbol, boolean isLocal, Function0<? extends T> block) throws KotlinIllegalStateExceptionWithAttachments {
        symbol.getClass();
        block.getClass();
        if (!isLocal) {
            getContext().pushContainerSymbol(symbol);
        }
        try {
            return (T) block.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            if (!isLocal) {
                getContext().popContainerSymbol(symbol);
            }
            InlineMarker.finallyEnd(1);
        }
    }

    public final <R> R withForcedLocalContext(boolean forceKeepingTheBodyInHeaderMode, Function0<? extends R> block) {
        block.getClass();
        boolean forceKeepingTheBodyInHeaderMode2 = getContext().getForceKeepingTheBodyInHeaderMode();
        getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode2 || forceKeepingTheBodyInHeaderMode);
        boolean inLocalContext = getContext().getInLocalContext();
        getContext().setInLocalContext(true);
        FqName classNameBeforeLocalContext = getContext().getClassNameBeforeLocalContext();
        if (!inLocalContext) {
            getContext().setClassNameBeforeLocalContext(getContext().getClassName());
        }
        FqName className = getContext().getClassName();
        getContext().setClassName(FqName.ROOT);
        try {
            return (R) block.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            getContext().setClassNameBeforeLocalContext(classNameBeforeLocalContext);
            getContext().setInLocalContext(inLocalContext);
            getContext().setClassName(className);
            getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode2);
            InlineMarker.finallyEnd(1);
        }
    }

    public final void withSourceElementEntry(ExceptionAttachmentBuilder exceptionAttachmentBuilder, String str, T t) {
        exceptionAttachmentBuilder.getClass();
        str.getClass();
        if (t instanceof PsiElement) {
            ExceptionAttachementBuilderUtilsKt.withPsiEntry(exceptionAttachmentBuilder, str, (PsiElement) t);
        } else {
            exceptionAttachmentBuilder.withEntry(str, t, new Function1() { // from class: aq
                public final Object invoke(Object obj) {
                    return AbstractRawFirBuilder.i(this.b, obj);
                }
            });
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/fir/builder/AbstractRawFirBuilder$Companion;", Argument.Delimiters.none, "<init>", "()V", "firScriptName", "Lorg/jetbrains/kotlin/name/Name;", "fileName", Argument.Delimiters.none, "firSnippetName", "org.jetbrains.kotlin:raw-fir.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Name firScriptName(String fileName) {
            fileName.getClass();
            Name nameSpecial = Name.special("<script-" + fileName + '>');
            nameSpecial.getClass();
            return nameSpecial;
        }

        public final Name firSnippetName(String fileName) {
            fileName.getClass();
            Name nameSpecial = Name.special("<snippet-" + fileName + '>');
            nameSpecial.getClass();
            return nameSpecial;
        }

        private Companion() {
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.builder.AbstractRawFirBuilder$generateIncrementOrDecrementBlock$3, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass3 extends FunctionReferenceImpl implements Function2<FirIncrementDecrementExpression, FirExpression, Unit> {
        public static final AnonymousClass3 INSTANCE = new AnonymousClass3();

        public AnonymousClass3() {
            super(2, FirIncrementDecrementExpression.class, "replaceExpression", "replaceExpression(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)V", 0);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((FirIncrementDecrementExpression) obj, (FirExpression) obj2);
            return Unit.INSTANCE;
        }

        public final void invoke(FirIncrementDecrementExpression firIncrementDecrementExpression, FirExpression firExpression) {
            firIncrementDecrementExpression.getClass();
            firExpression.getClass();
            firIncrementDecrementExpression.replaceExpression(firExpression);
        }
    }

    public final FirLoopTarget prepareTarget(FirLoopBuilder firLoopBuilder, Object obj) {
        firLoopBuilder.getClass();
        obj.getClass();
        return prepareTarget(firLoopBuilder, this.context.getLastLabel(obj));
    }

    public /* synthetic */ AbstractRawFirBuilder(FirSession firSession, Context context, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(firSession, (i & 2) != 0 ? new Context() : context);
    }

    public final FirResolvedTypeRef toDelegatedSelfType(T t, FirAnonymousObjectBuilder firAnonymousObjectBuilder) {
        t.getClass();
        firAnonymousObjectBuilder.getClass();
        return toDelegatedSelfType(t, firAnonymousObjectBuilder.getTypeParameters(), firAnonymousObjectBuilder.getSymbol());
    }

    public final FirResolvedTypeRef toDelegatedSelfType(T t, FirRegularClassBuilder firRegularClassBuilder) {
        t.getClass();
        firRegularClassBuilder.getClass();
        return toDelegatedSelfType(t, firRegularClassBuilder.getTypeParameters(), firRegularClassBuilder.getSymbol());
    }
}
