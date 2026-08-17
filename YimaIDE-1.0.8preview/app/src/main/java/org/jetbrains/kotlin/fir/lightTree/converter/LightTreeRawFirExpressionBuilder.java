package org.jetbrains.kotlin.fir.lightTree.converter;

import com.google.common.collect.ImmutableSet;
import com.intellij.lang.LighterASTNode;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.util.diff.FlyweightCapableTreeStructure;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Triple;
import kotlin.UninitializedPropertyAccessException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.ElementTypeUtils;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtLightSourceElement;
import org.jetbrains.kotlin.KtNodeTypes;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.PreprocessCommandLineArgumentsKt;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.codegen.state.InlineClassManglingUtilsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.EffectiveVisibility;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirExpressionRef;
import org.jetbrains.kotlin.fir.FirFunctionTarget;
import org.jetbrains.kotlin.fir.FirGenerationKt;
import org.jetbrains.kotlin.fir.FirLabel;
import org.jetbrains.kotlin.fir.FirLoopTarget;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.builder.AbstractRawFirBuilder;
import org.jetbrains.kotlin.fir.builder.CalleeAndReceiver;
import org.jetbrains.kotlin.fir.builder.Context;
import org.jetbrains.kotlin.fir.builder.ConversionUtilsKt;
import org.jetbrains.kotlin.fir.builder.FirLabelBuilder;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirReplSnippet;
import org.jetbrains.kotlin.fir.declarations.FirResolveStateKt;
import org.jetbrains.kotlin.fir.declarations.FirScript;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.declarations.builder.FirAnonymousFunctionBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirPropertyBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirReceiverParameterBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirReplSnippetBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirScriptBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirValueParameterBuilder;
import org.jetbrains.kotlin.fir.declarations.impl.FirDeclarationStatusImpl;
import org.jetbrains.kotlin.fir.declarations.impl.FirResolvedDeclarationStatusImpl;
import org.jetbrains.kotlin.fir.diagnostics.ConeNotAnnotationContainer;
import org.jetbrains.kotlin.fir.diagnostics.ConeSimpleDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.ConeSyntaxDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.ConeUnsupportedClassLiteralsWithEmptyLhs;
import org.jetbrains.kotlin.fir.diagnostics.DiagnosticKind;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression;
import org.jetbrains.kotlin.fir.expressions.FirArgumentList;
import org.jetbrains.kotlin.fir.expressions.FirArgumentUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess;
import org.jetbrains.kotlin.fir.expressions.FirCatch;
import org.jetbrains.kotlin.fir.expressions.FirCollectionLiteral;
import org.jetbrains.kotlin.fir.expressions.FirErrorExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCallOrigin;
import org.jetbrains.kotlin.fir.expressions.FirGetClassCall;
import org.jetbrains.kotlin.fir.expressions.FirLoop;
import org.jetbrains.kotlin.fir.expressions.FirLoopJump;
import org.jetbrains.kotlin.fir.expressions.FirOperation;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvable;
import org.jetbrains.kotlin.fir.expressions.FirReturnExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.FirSuperReceiverExpression;
import org.jetbrains.kotlin.fir.expressions.FirThisReceiverExpression;
import org.jetbrains.kotlin.fir.expressions.FirThrowExpression;
import org.jetbrains.kotlin.fir.expressions.FirTryExpression;
import org.jetbrains.kotlin.fir.expressions.FirTypeOperatorCall;
import org.jetbrains.kotlin.fir.expressions.FirWhenBranch;
import org.jetbrains.kotlin.fir.expressions.FirWhenExpression;
import org.jetbrains.kotlin.fir.expressions.builder.FirAbstractFunctionCallBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirAbstractWhenBranchBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirAnonymousFunctionExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirArgumentListBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirBlockBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirBreakExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirCallableReferenceAccessBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirCatchBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirCheckNotNullCallBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirCollectionLiteralBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirContinueExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirDoWhileLoopBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirElseIfTrueConditionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirEmptyExpressionBlockBuilderKt;
import org.jetbrains.kotlin.fir.expressions.builder.FirEqualityOperatorCallBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirErrorExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirFunctionCallBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirGetClassCallBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirGuardedWhenBranchBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirImplicitInvokeCallBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirLoopJumpBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirNamedArgumentExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirPropertyAccessExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirQualifiedAccessExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirQualifiedErrorAccessExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirRegularWhenBranchBuilder;
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
import org.jetbrains.kotlin.fir.expressions.impl.FirSingleExpressionBlock;
import org.jetbrains.kotlin.fir.lightTree.converter.LightTreeRawFirExpressionBuilder;
import org.jetbrains.kotlin.fir.lightTree.fir.DestructuringDeclaration;
import org.jetbrains.kotlin.fir.lightTree.fir.DestructuringDeclarationKt;
import org.jetbrains.kotlin.fir.lightTree.fir.ValueParameter;
import org.jetbrains.kotlin.fir.lightTree.fir.WhenEntry;
import org.jetbrains.kotlin.fir.references.FirNamedReference;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.references.builder.FirExplicitSuperReferenceBuilder;
import org.jetbrains.kotlin.fir.references.builder.FirExplicitThisReferenceBuilder;
import org.jetbrains.kotlin.fir.references.builder.FirSimpleNamedReferenceBuilder;
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirLocalPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirReceiverParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.builder.FirErrorTypeRefBuilder;
import org.jetbrains.kotlin.fir.types.impl.FirImplicitTypeRefImplWithoutSource;
import org.jetbrains.kotlin.lexer.KtSingleValueToken;
import org.jetbrains.kotlin.lexer.KtToken;
import org.jetbrains.kotlin.lexer.KtTokens;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.SpecialNames;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.psi.psiUtil.PsiUtilsKt;
import org.jetbrains.kotlin.psi.stubs.elements.KtConstantExpressionElementType;
import org.jetbrains.kotlin.psi.stubs.elements.KtNameReferenceExpressionElementType;
import org.jetbrains.kotlin.types.expressions.OperatorConventions;
import org.jetbrains.kotlin.util.OperatorNameConventions;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Æ\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0004\u009e\u0001\u009f\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\n¢\u0006\u0004\b\u000b\u0010\fJG\u0010\r\u001a\u0002H\u000e\"\n\b\u0000\u0010\u000e\u0018\u0001*\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00062\b\b\u0002\u0010\u0011\u001a\u00020\u00122\u0014\b\u0002\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u0002H\u000e\u0012\u0004\u0012\u00020\u00150\u0014H\u0080\bø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017JQ\u0010\r\u001a\u0002H\u000e\"\n\b\u0000\u0010\u000e\u0018\u0001*\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u00062\u0014\b\u0002\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u0002H\u000e\u0012\u0004\u0012\u00020\u00150\u0014H\u0080\bø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0019JR\u0010\u001a\u001a\u0002H\u000e\"\n\b\u0000\u0010\u000e\u0018\u0001*\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00062\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u0002H\u000e\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0018\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u0012H\u0082\b¢\u0006\u0002\u0010\u001dJ\u0018\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0010\u001a\u00020\u00062\b\b\u0002\u0010\u0011\u001a\u00020\u0012J\u0010\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u0006H\u0002J\u0012\u0010#\u001a\u0004\u0018\u00010\u000f2\u0006\u0010$\u001a\u00020\u0006H\u0002J\u0010\u0010%\u001a\u00020\u001f2\u0006\u0010$\u001a\u00020\u0006H\u0002J&\u0010&\u001a\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00060'2\u0006\u0010$\u001a\u00020\u0006H\u0002J\u0010\u0010(\u001a\u00020\u001f2\u0006\u0010$\u001a\u00020\u0006H\u0002J\u0010\u0010)\u001a\u00020*2\u0006\u0010$\u001a\u00020\u0006H\u0002J\u0010\u0010+\u001a\u00020\u001f2\u0006\u0010,\u001a\u00020\u0006H\u0002J\u0010\u0010-\u001a\u00020\u000f2\u0006\u0010.\u001a\u00020\u0006H\u0002J\u0010\u0010/\u001a\u00020\u001f2\u0006\u00100\u001a\u00020\u0006H\u0002J\u0010\u00101\u001a\u0002022\u0006\u00103\u001a\u00020\u0006H\u0002J\u0010\u00104\u001a\u0002052\u0006\u00106\u001a\u00020\u0006H\u0002J\u0010\u00107\u001a\u00020\u000f2\u0006\u00108\u001a\u00020\u0006H\u0002J\u0010\u00109\u001a\u00020\u000f2\u0006\u0010:\u001a\u00020\u0006H\u0002J\u0010\u0010;\u001a\u00020\u000f2\u0006\u0010<\u001a\u00020\u0006H\u0002J\u001c\u0010=\u001a\b\u0012\u0004\u0012\u00020\u000f0>*\u0004\u0018\u00010\u00062\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0010\u0010?\u001a\u00020\u000f2\u0006\u0010@\u001a\u00020\u0006H\u0002J\u0010\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020\u0006H\u0002J\u001a\u0010D\u001a\u00020E2\u0006\u0010F\u001a\u00020\u00062\b\u0010G\u001a\u0004\u0018\u00010HH\u0002J\u001a\u0010I\u001a\u00020\u000f2\u0006\u0010J\u001a\u00020\u00062\b\u0010G\u001a\u0004\u0018\u00010HH\u0002J\u001a\u0010K\u001a\u00020L2\u0006\u0010J\u001a\u00020\u00062\b\u0010G\u001a\u0004\u0018\u00010HH\u0002J\u001a\u0010M\u001a\u00020L2\u0006\u0010J\u001a\u00020\u00062\b\u0010G\u001a\u0004\u0018\u00010HH\u0002J \u0010N\u001a\u00020L2\u0006\u0010O\u001a\u00020\u00152\u0006\u0010P\u001a\u00020\u000f2\u0006\u0010J\u001a\u00020\u0006H\u0002J\u0010\u0010Q\u001a\u00020\u000f2\u0006\u0010R\u001a\u00020\u0006H\u0002J\u0010\u0010S\u001a\u00020T2\u0006\u0010\u0010\u001a\u00020\u0006H\u0002J\u0016\u0010U\u001a\b\u0012\u0004\u0012\u00020\u000f0V2\u0006\u0010W\u001a\u00020\u0006H\u0002J\u0010\u0010X\u001a\u00020Y2\u0006\u0010Z\u001a\u00020\u0006H\u0002J\u0018\u0010[\u001a\u00020\\2\u0006\u0010]\u001a\u00020^2\u0006\u0010Z\u001a\u00020\u0006H\u0002J\u0010\u0010_\u001a\u00020`2\u0006\u0010a\u001a\u00020\u0006H\u0002J\u0010\u0010b\u001a\u00020`2\u0006\u0010c\u001a\u00020\u0006H\u0002J\u0010\u0010d\u001a\u00020e2\u0006\u0010f\u001a\u00020\u0006H\u0002J\u0012\u0010g\u001a\u00020e2\b\u0010h\u001a\u0004\u0018\u00010\u0006H\u0002J\u0014\u0010i\u001a\u0004\u0018\u00010e2\b\u0010h\u001a\u0004\u0018\u00010\u0006H\u0002J\u0010\u0010j\u001a\u00020k2\u0006\u0010l\u001a\u00020\u0006H\u0002J&\u0010m\u001a\u0018\u0012\u0006\u0012\u0004\u0018\u00010n\u0012\u0004\u0012\u00020e\u0012\u0004\u0012\u00020o\u0018\u00010'2\u0006\u0010p\u001a\u00020\u0006H\u0002J\u0010\u0010q\u001a\u00020e2\u0006\u0010r\u001a\u00020\u0006H\u0002J\u0010\u0010s\u001a\u00020B2\u0006\u0010t\u001a\u00020\u0006H\u0002J\u0010\u0010u\u001a\u00020v2\u0006\u0010t\u001a\u00020\u0006H\u0002J\u0010\u0010z\u001a\u00020{2\u0006\u0010|\u001a\u00020\u0006H\u0002J\u0010\u0010}\u001a\u00020~2\u0006\u0010\u007f\u001a\u00020\u0006H\u0002J\u0013\u0010\u0080\u0001\u001a\u00030\u0081\u00012\u0007\u0010\u0082\u0001\u001a\u00020\u0006H\u0002J\u0013\u0010\u0083\u0001\u001a\u00030\u0084\u00012\u0007\u0010\u0085\u0001\u001a\u00020\u0006H\u0002J\u0013\u0010\u0086\u0001\u001a\u00030\u0087\u00012\u0007\u0010\u0088\u0001\u001a\u00020\u0006H\u0002J\u0016\u0010\u0089\u0001\u001a\b\u0012\u0004\u0012\u00020\u000f0V2\u0007\u0010\u008a\u0001\u001a\u00020\u0006J\u0012\u0010\u008b\u0001\u001a\u00020\u000f2\u0007\u0010\u008c\u0001\u001a\u00020\u0006H\u0002JB\u0010\u008d\u0001\u001a\u00030\u008e\u00012\u0007\u0010\u008f\u0001\u001a\u00020\u00062\u0007\u0010\u0090\u0001\u001a\u00020^2\u0007\u0010\u0091\u0001\u001a\u00020\u00122\u001b\u0010\u0092\u0001\u001a\u0016\u0012\u0005\u0012\u00030\u0093\u0001\u0012\u0005\u0012\u00030\u0094\u00010\u0014¢\u0006\u0003\b\u0095\u0001H\u0014J\u0082\u0001\u0010\u0096\u0001\u001a\u00030\u0097\u00012\u0007\u0010\u008f\u0001\u001a\u00020\u00062\u0007\u0010\u0090\u0001\u001a\u00020^2\u0007\u0010\u0091\u0001\u001a\u00020\u00122\u001b\u0010\u0098\u0001\u001a\u0016\u0012\u0005\u0012\u00030\u0099\u0001\u0012\u0005\u0012\u00030\u0094\u00010\u0014¢\u0006\u0003\b\u0095\u00012\u001b\u0010\u009a\u0001\u001a\u0016\u0012\u0005\u0012\u00030\u009b\u0001\u0012\u0005\u0012\u00030\u0094\u00010\u0014¢\u0006\u0003\b\u0095\u00012!\u0010\u009c\u0001\u001a\u001c\u0012\u000b\u0012\t\u0012\u0004\u0012\u00020\u001c0\u009d\u0001\u0012\u0005\u0012\u00030\u0094\u00010\u0014¢\u0006\u0003\b\u0095\u0001H\u0014R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010w\u001a\u00020\u0015*\u00020\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bx\u0010y\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006 \u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/lightTree/converter/LightTreeRawFirExpressionBuilder;", "Lorg/jetbrains/kotlin/fir/lightTree/converter/AbstractLightTreeRawFirBuilder;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "tree", "Lcom/intellij/util/diff/FlyweightCapableTreeStructure;", "Lcom/intellij/lang/LighterASTNode;", "declarationBuilder", "Lorg/jetbrains/kotlin/fir/lightTree/converter/LightTreeRawFirDeclarationBuilder;", "context", "Lorg/jetbrains/kotlin/fir/builder/Context;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lcom/intellij/util/diff/FlyweightCapableTreeStructure;Lorg/jetbrains/kotlin/fir/lightTree/converter/LightTreeRawFirDeclarationBuilder;Lorg/jetbrains/kotlin/fir/builder/Context;)V", "getAsFirExpression", "R", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "expression", "errorReason", Argument.Delimiters.none, "isValidExpression", "Lkotlin/Function1;", Argument.Delimiters.none, "getAsFirExpression$org_jetbrains_kotlin_fir_light_tree2fir", "(Lcom/intellij/lang/LighterASTNode;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "sourceWhenInvalidExpression", "(Lcom/intellij/lang/LighterASTNode;Ljava/lang/String;Lcom/intellij/lang/LighterASTNode;Lkotlin/jvm/functions/Function1;)Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "wrapExpressionIfNeeded", "converted", "Lorg/jetbrains/kotlin/fir/FirElement;", "(Lcom/intellij/lang/LighterASTNode;Lorg/jetbrains/kotlin/fir/FirElement;Lkotlin/jvm/functions/Function1;Lcom/intellij/lang/LighterASTNode;Ljava/lang/String;)Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "getAsFirStatement", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "convertLambdaExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirAnonymousFunctionExpression;", "lambdaExpression", "tryFoldStringConcatenation", "binaryExpression", "convertBinaryExpression", "extractBinaryExpression", "Lkotlin/Triple;", "convertBinaryExpressionFallback", "convertBinaryWithTypeRHSExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirTypeOperatorCall;", "convertLabeledExpression", "labeledExpression", "convertUnaryExpression", "unaryExpression", "convertAnnotatedExpression", "annotatedExpression", "convertClassLiteralExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirGetClassCall;", "classLiteralExpression", "convertCallableReferenceExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirCallableReferenceAccess;", "callableReferenceExpression", "convertQualifiedExpression", "dotQualifiedExpression", "convertCallExpression", "callSuffix", "convertStringTemplate", "stringTemplate", "convertShortOrLongStringTemplate", Argument.Delimiters.none, "convertConstantExpression", "constantExpression", "convertWhenExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;", "whenExpression", "convertWhenEntry", "Lorg/jetbrains/kotlin/fir/lightTree/fir/WhenEntry;", "whenEntry", "subjectVariable", "Lorg/jetbrains/kotlin/fir/declarations/FirVariable;", "convertWhenConditionExpression", "whenCondition", "convertWhenConditionInRange", "Lorg/jetbrains/kotlin/fir/lightTree/converter/LightTreeRawFirExpressionBuilder$WhenConditionConvertedResults;", "convertWhenConditionIsPattern", "createWhenConditionConvertedResults", "hasSubject", CoroutineCodegenUtilKt.CONTINUATION_RESULT_FIELD_NAME, "convertArrayAccessExpression", "arrayAccess", "convertCollectionLiteralExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirCollectionLiteral;", "convertIndices", Argument.Delimiters.none, "indices", "convertSimpleNameExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "referenceExpression", "createSimpleNamedReference", "Lorg/jetbrains/kotlin/fir/references/FirNamedReference;", "sourceElement", "Lorg/jetbrains/kotlin/KtSourceElement;", "convertDoWhile", "Lorg/jetbrains/kotlin/fir/expressions/FirLoop;", "doWhileLoop", "convertWhile", "whileLoop", "convertFor", "Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "forLoop", "convertLoopBody", "body", "convertLoopOrIfBody", "convertTryExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirTryExpression;", "tryExpression", "convertCatchClause", "Lorg/jetbrains/kotlin/fir/lightTree/fir/ValueParameter;", "Lorg/jetbrains/kotlin/KtLightSourceElement;", "catchClause", "convertFinally", "finallyExpression", "convertIfExpression", "ifExpression", "parseIfExpression", "Lorg/jetbrains/kotlin/fir/lightTree/converter/LightTreeRawFirExpressionBuilder$IfNodeComponents;", "usedAsExpression", "getUsedAsExpression", "(Lcom/intellij/lang/LighterASTNode;)Z", "convertLoopJump", "Lorg/jetbrains/kotlin/fir/expressions/FirLoopJump;", "jump", "convertReturn", "Lorg/jetbrains/kotlin/fir/expressions/FirReturnExpression;", "returnExpression", "convertThrow", "Lorg/jetbrains/kotlin/fir/expressions/FirThrowExpression;", "throwExpression", "convertThisExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirThisReceiverExpression;", "thisExpression", "convertSuperExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirSuperReceiverExpression;", "superExpression", "convertValueArguments", "valueArguments", "convertValueArgument", "valueArgument", "convertScript", "Lorg/jetbrains/kotlin/fir/declarations/FirScript;", "script", "scriptSource", "fileName", "setup", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirScriptBuilder;", Argument.Delimiters.none, "Lkotlin/ExtensionFunctionType;", "convertReplSnippet", "Lorg/jetbrains/kotlin/fir/declarations/FirReplSnippet;", "snippetSetup", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirReplSnippetBuilder;", "functionBodySetup", "Lorg/jetbrains/kotlin/fir/expressions/builder/FirBlockBuilder;", "statementsSetup", Argument.Delimiters.none, "WhenConditionConvertedResults", "IfNodeComponents", "org.jetbrains.kotlin.fir:light-tree2fir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class LightTreeRawFirExpressionBuilder extends AbstractLightTreeRawFirBuilder {
    private final LightTreeRawFirDeclarationBuilder declarationBuilder;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0002\u0018\u00002\u00020\u0001B%\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0007\u0010\bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/lightTree/converter/LightTreeRawFirExpressionBuilder$IfNodeComponents;", Argument.Delimiters.none, "firCondition", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "thenBlock", "Lcom/intellij/lang/LighterASTNode;", "elseBlock", "<init>", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lcom/intellij/lang/LighterASTNode;Lcom/intellij/lang/LighterASTNode;)V", "getFirCondition", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "getThenBlock", "()Lcom/intellij/lang/LighterASTNode;", "getElseBlock", "org.jetbrains.kotlin.fir:light-tree2fir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class IfNodeComponents {
        private final LighterASTNode elseBlock;
        private final FirExpression firCondition;
        private final LighterASTNode thenBlock;

        public IfNodeComponents(FirExpression firExpression, LighterASTNode lighterASTNode, LighterASTNode lighterASTNode2) {
            this.firCondition = firExpression;
            this.thenBlock = lighterASTNode;
            this.elseBlock = lighterASTNode2;
        }

        public final LighterASTNode getElseBlock() {
            return this.elseBlock;
        }

        public final FirExpression getFirCondition() {
            return this.firCondition;
        }

        public final LighterASTNode getThenBlock() {
            return this.thenBlock;
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00052\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0011\u001a\u00020\u0012HÖ\u0081\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/fir/lightTree/converter/LightTreeRawFirExpressionBuilder$WhenConditionConvertedResults;", Argument.Delimiters.none, "expression", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "shouldBindSubject", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Z)V", "getExpression", "()Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "getShouldBindSubject", "()Z", "component1", "component2", "copy", "equals", "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin.fir:light-tree2fir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class WhenConditionConvertedResults {
        private final FirExpression expression;
        private final boolean shouldBindSubject;

        public WhenConditionConvertedResults(FirExpression firExpression, boolean z) {
            firExpression.getClass();
            this.expression = firExpression;
            this.shouldBindSubject = z;
        }

        public static /* synthetic */ WhenConditionConvertedResults copy$default(WhenConditionConvertedResults whenConditionConvertedResults, FirExpression firExpression, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                firExpression = whenConditionConvertedResults.expression;
            }
            if ((i & 2) != 0) {
                z = whenConditionConvertedResults.shouldBindSubject;
            }
            return whenConditionConvertedResults.copy(firExpression, z);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final FirExpression getExpression() {
            return this.expression;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getShouldBindSubject() {
            return this.shouldBindSubject;
        }

        public final WhenConditionConvertedResults copy(FirExpression expression, boolean shouldBindSubject) {
            expression.getClass();
            return new WhenConditionConvertedResults(expression, shouldBindSubject);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof WhenConditionConvertedResults)) {
                return false;
            }
            WhenConditionConvertedResults whenConditionConvertedResults = (WhenConditionConvertedResults) other;
            return Intrinsics.areEqual(this.expression, whenConditionConvertedResults.expression) && this.shouldBindSubject == whenConditionConvertedResults.shouldBindSubject;
        }

        public final FirExpression getExpression() {
            return this.expression;
        }

        public final boolean getShouldBindSubject() {
            return this.shouldBindSubject;
        }

        public int hashCode() {
            return (this.expression.hashCode() * 31) + Boolean.hashCode(this.shouldBindSubject);
        }

        public String toString() {
            return "WhenConditionConvertedResults(expression=" + this.expression + ", shouldBindSubject=" + this.shouldBindSubject + ')';
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LightTreeRawFirExpressionBuilder(FirSession firSession, FlyweightCapableTreeStructure<LighterASTNode> flyweightCapableTreeStructure, LightTreeRawFirDeclarationBuilder lightTreeRawFirDeclarationBuilder, Context<LighterASTNode> context) {
        super(firSession, flyweightCapableTreeStructure, context);
        firSession.getClass();
        flyweightCapableTreeStructure.getClass();
        lightTreeRawFirDeclarationBuilder.getClass();
        context.getClass();
        this.declarationBuilder = lightTreeRawFirDeclarationBuilder;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    private final FirStatement convertAnnotatedExpression(LighterASTNode annotatedExpression) throws Exception {
        ArrayList arrayList = new ArrayList();
        FirStatement firStatementBuildErrorExpression$default = null;
        for (LighterASTNode lighterASTNode : getChildrenAsArray(annotatedExpression)) {
            if (lighterASTNode == null) {
                break;
            }
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType())) {
                IElementType tokenType = lighterASTNode.getTokenType();
                if (Intrinsics.areEqual(tokenType, KtNodeTypes.ANNOTATION)) {
                    this.declarationBuilder.convertAnnotationTo(lighterASTNode, arrayList);
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.ANNOTATION_ENTRY)) {
                    arrayList.add(LightTreeRawFirDeclarationBuilder.convertAnnotationEntry$default(this.declarationBuilder, lighterASTNode, null, null, 6, null));
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.BLOCK)) {
                    firStatementBuildErrorExpression$default = LightTreeRawFirDeclarationBuilder.convertBlockExpression$default(this.declarationBuilder, lighterASTNode, false, 2, null);
                } else if (ElementTypeUtils.INSTANCE.isExpression(lighterASTNode)) {
                    getContext().forwardLabelUsagePermission(annotatedExpression, lighterASTNode);
                    firStatementBuildErrorExpression$default = getAsFirStatement$default(this, lighterASTNode, null, 2, null);
                }
            }
        }
        if (firStatementBuildErrorExpression$default == null) {
            firStatementBuildErrorExpression$default = FirExpressionUtilKt.buildErrorExpression$default(AbstractRawFirBuilder.toFirSourceElement$default(this, annotatedExpression, null, 1, null), new ConeNotAnnotationContainer("???"), null, 4, null);
        }
        firStatementBuildErrorExpression$default.replaceAnnotations(UtilsKt.smartPlus(firStatementBuildErrorExpression$default.getAnnotations(), arrayList));
        return firStatementBuildErrorExpression$default;
    }

    private final FirExpression convertArrayAccessExpression(LighterASTNode arrayAccess) {
        LighterASTNode parent;
        LighterASTNode lighterASTNode;
        KtSourceElement firSourceElement$default;
        FirExpression firExpressionBuildErrorExpression;
        KtSourceElement source;
        KtSourceElement firSourceElement$default2;
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        ArrayList arrayList = new ArrayList();
        LighterASTNode[] childrenAsArray = getChildrenAsArray(arrayAccess);
        int length = childrenAsArray.length;
        for (int i = 0; i < length && (lighterASTNode = childrenAsArray[i]) != null; i++) {
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType())) {
                if (Intrinsics.areEqual(lighterASTNode.getTokenType(), KtNodeTypes.INDICES)) {
                    CollectionsKt.addAll(arrayList, convertIndices(lighterASTNode));
                } else if (ElementTypeUtils.INSTANCE.isExpression(lighterASTNode)) {
                    FirStatement asFirStatement = getAsFirStatement(lighterASTNode, "No array expression");
                    if (asFirStatement instanceof FirExpression) {
                        firExpressionBuildErrorExpression = (FirExpression) asFirStatement;
                        if (UtilsKt.isStatementLikeExpression(firExpressionBuildErrorExpression)) {
                            KtSourceElement source2 = firExpressionBuildErrorExpression.getSource();
                            if (source2 == null || (firSourceElement$default2 = KtSourceElementKt.realElement(source2)) == null) {
                                firSourceElement$default2 = AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode, null, 1, null);
                            }
                            firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default2, new ConeSimpleDiagnostic("No array expression", DiagnosticKind.ExpressionExpected), asFirStatement);
                        }
                    } else {
                        if (asFirStatement == null || (source = asFirStatement.getSource()) == null || (firSourceElement$default = KtSourceElementKt.realElement(source)) == null) {
                            firSourceElement$default = AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode, null, 1, null);
                        }
                        firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default, new ConeSimpleDiagnostic("No array expression", DiagnosticKind.ExpressionExpected), asFirStatement);
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
        }
        FirExpression firExpressionRemove = getContext().getArraySetArgument().remove(arrayAccess);
        FirFunctionCallBuilder firFunctionCallBuilder = new FirFunctionCallBuilder();
        boolean z = firExpressionRemove == null;
        if (z) {
            parent = arrayAccess;
        } else {
            parent = getParent(arrayAccess);
            parent.getClass();
        }
        firFunctionCallBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(this, parent, null, 1, null));
        FirSimpleNamedReferenceBuilder firSimpleNamedReferenceBuilder = new FirSimpleNamedReferenceBuilder();
        firSimpleNamedReferenceBuilder.setSource(KtSourceElementKt.fakeElement$default(AbstractRawFirBuilder.toFirSourceElement$default(this, arrayAccess, null, 1, null), KtFakeSourceElementKind.ArrayAccessNameReference.INSTANCE, null, 2, null));
        firSimpleNamedReferenceBuilder.setName(z ? OperatorNameConventions.GET : OperatorNameConventions.SET);
        firFunctionCallBuilder.setCalleeReference(firSimpleNamedReferenceBuilder.build());
        FirExpression firExpressionBuildErrorExpression$default = (FirExpression) objectRef.element;
        if (firExpressionBuildErrorExpression$default == null) {
            firExpressionBuildErrorExpression$default = FirExpressionUtilKt.buildErrorExpression$default(AbstractRawFirBuilder.toFirSourceElement$default(this, arrayAccess, null, 1, null), new ConeSyntaxDiagnostic("No array expression"), null, 4, null);
        }
        firFunctionCallBuilder.setExplicitReceiver(firExpressionBuildErrorExpression$default);
        FirArgumentListBuilder firArgumentListBuilder = new FirArgumentListBuilder();
        CollectionsKt.addAll(firArgumentListBuilder.getArguments(), arrayList);
        if (firExpressionRemove != null) {
            firArgumentListBuilder.getArguments().add(firExpressionRemove);
        }
        firFunctionCallBuilder.setArgumentList(firArgumentListBuilder.build());
        firFunctionCallBuilder.setOrigin(FirFunctionCallOrigin.Operator);
        return ConversionUtilsKt.pullUpSafeCallIfNecessary(firFunctionCallBuilder.mo288build());
    }

    private final FirStatement convertBinaryExpression(LighterASTNode binaryExpression) {
        FirExpression firExpressionTryFoldStringConcatenation = tryFoldStringConcatenation(binaryExpression);
        return firExpressionTryFoldStringConcatenation != null ? firExpressionTryFoldStringConcatenation : convertBinaryExpressionFallback(binaryExpression);
    }

    private final FirStatement convertBinaryExpressionFallback(final LighterASTNode binaryExpression) {
        FirExpression firExpressionBuildErrorExpression$default;
        KtSourceElement firSourceElement$default;
        FirExpression firExpressionBuildErrorExpression;
        KtSourceElement source;
        LighterASTNode lighterASTNode;
        KtSourceElement ktSourceElement;
        KtSourceElement firSourceElement$default2;
        FirStatement firStatement;
        KtSourceElement firSourceElement$default3;
        FirStatement firStatement2;
        KtSourceElement source2;
        KtSourceElement ktSourceElementRealElement;
        KtSourceElement firSourceElement$default4;
        Triple<LighterASTNode, LighterASTNode, LighterASTNode> tripleExtractBinaryExpression = extractBinaryExpression(binaryExpression);
        LighterASTNode lighterASTNode2 = (LighterASTNode) tripleExtractBinaryExpression.component1();
        LighterASTNode lighterASTNode3 = (LighterASTNode) tripleExtractBinaryExpression.component2();
        LighterASTNode lighterASTNode4 = (LighterASTNode) tripleExtractBinaryExpression.component3();
        KtSourceElement ktSourceElement2 = (KtLightSourceElement) AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode3, null, 1, null);
        String asText = getAsText(lighterASTNode3);
        KtToken operationSymbol = ElementTypeUtils.INSTANCE.getOperationSymbol(lighterASTNode3, getTree());
        KtLightSourceElement firSourceElement$default5 = AbstractRawFirBuilder.toFirSourceElement$default(this, binaryExpression, null, 1, null);
        KtToken ktToken = KtTokens.IDENTIFIER;
        if (Intrinsics.areEqual(operationSymbol, ktToken)) {
            getContext().getCalleeNamesForLambda().add(ConverterUtilKt.nameAsSafeName$default(asText, null, 1, null));
        } else {
            getContext().getCalleeNamesForLambda().add(null);
        }
        if (lighterASTNode4 != null) {
            FirStatement asFirStatement = getAsFirStatement(lighterASTNode4, "No right operand");
            if (asFirStatement instanceof FirExpression) {
                firExpressionBuildErrorExpression$default = (FirExpression) asFirStatement;
                if (UtilsKt.isStatementLikeExpression(firExpressionBuildErrorExpression$default)) {
                    KtSourceElement source3 = firExpressionBuildErrorExpression$default.getSource();
                    if (source3 == null || (firSourceElement$default4 = KtSourceElementKt.realElement(source3)) == null) {
                        firSourceElement$default4 = AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode4, null, 1, null);
                    }
                    firExpressionBuildErrorExpression$default = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default4, new ConeSimpleDiagnostic("No right operand", DiagnosticKind.ExpressionExpected), asFirStatement);
                    firStatement2 = null;
                } else {
                    firStatement2 = null;
                }
            } else {
                if (asFirStatement == null || (source2 = asFirStatement.getSource()) == null || (ktSourceElementRealElement = KtSourceElementKt.realElement(source2)) == null) {
                    firStatement = null;
                    firSourceElement$default3 = AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode4, null, 1, null);
                } else {
                    firSourceElement$default3 = ktSourceElementRealElement;
                    firStatement = null;
                }
                firStatement2 = firStatement;
                firExpressionBuildErrorExpression$default = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default3, new ConeSimpleDiagnostic("No right operand", DiagnosticKind.ExpressionExpected), asFirStatement);
            }
            if (firExpressionBuildErrorExpression$default == null) {
                x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.expressions.FirExpression");
                return firStatement2;
            }
        } else {
            firExpressionBuildErrorExpression$default = FirExpressionUtilKt.buildErrorExpression$default(AbstractRawFirBuilder.toFirSourceElement$default(this, binaryExpression, null, 1, null), new ConeSyntaxDiagnostic("No right operand"), null, 4, null);
        }
        FirStatement asFirStatement2 = lighterASTNode2 != null ? getAsFirStatement(lighterASTNode2, "No left operand") : null;
        if (asFirStatement2 instanceof FirExpression) {
            firExpressionBuildErrorExpression = (FirExpression) asFirStatement2;
            if (UtilsKt.isStatementLikeExpression(firExpressionBuildErrorExpression)) {
                KtSourceElement source4 = firExpressionBuildErrorExpression.getSource();
                if (source4 == null || (firSourceElement$default2 = KtSourceElementKt.realElement(source4)) == null) {
                    firSourceElement$default2 = lighterASTNode2 != null ? AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode2, null, 1, null) : toFirSourceElement(binaryExpression, (KtFakeSourceElementKind) KtFakeSourceElementKind.ErrorExpression.INSTANCE);
                }
                firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default2, new ConeSimpleDiagnostic("No left operand", DiagnosticKind.ExpressionExpected), asFirStatement2);
            }
        } else {
            if (asFirStatement2 == null || (source = asFirStatement2.getSource()) == null || (firSourceElement$default = KtSourceElementKt.realElement(source)) == null) {
                firSourceElement$default = lighterASTNode2 != null ? AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode2, null, 1, null) : toFirSourceElement(binaryExpression, (KtFakeSourceElementKind) KtFakeSourceElementKind.ErrorExpression.INSTANCE);
            }
            firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default, lighterASTNode2 == null ? new ConeSyntaxDiagnostic("No left operand") : new ConeSimpleDiagnostic("No left operand", DiagnosticKind.ExpressionExpected), asFirStatement2);
        }
        if (firExpressionBuildErrorExpression == null) {
            x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.expressions.FirExpression");
            return null;
        }
        removeLast(getContext().getCalleeNamesForLambda());
        if (Intrinsics.areEqual(operationSymbol, KtTokens.ELVIS)) {
            return ConversionUtilsKt.generateNotNullOrOther(firExpressionBuildErrorExpression, firExpressionBuildErrorExpression$default, firSourceElement$default5);
        }
        KtSingleValueToken ktSingleValueToken = KtTokens.ANDAND;
        if (Intrinsics.areEqual(operationSymbol, ktSingleValueToken) || Intrinsics.areEqual(operationSymbol, KtTokens.OROR)) {
            return ConversionUtilsKt.generateLazyLogicalOperation(firExpressionBuildErrorExpression, firExpressionBuildErrorExpression$default, Intrinsics.areEqual(operationSymbol, ktSingleValueToken), firSourceElement$default5);
        }
        ImmutableSet immutableSet = OperatorConventions.IN_OPERATIONS;
        immutableSet.getClass();
        if (CollectionsKt.contains(immutableSet, operationSymbol)) {
            return ConversionUtilsKt.generateContainsOperation(firExpressionBuildErrorExpression$default, firExpressionBuildErrorExpression, Intrinsics.areEqual(operationSymbol, KtTokens.NOT_IN), firSourceElement$default5, ktSourceElement2);
        }
        ImmutableSet immutableSet2 = OperatorConventions.COMPARISON_OPERATIONS;
        immutableSet2.getClass();
        if (CollectionsKt.contains(immutableSet2, operationSymbol)) {
            return ConversionUtilsKt.generateComparisonExpression(firExpressionBuildErrorExpression, firExpressionBuildErrorExpression$default, operationSymbol, firSourceElement$default5, ktSourceElement2);
        }
        Name binaryName = ConversionUtilsKt.toBinaryName(operationSymbol);
        if (binaryName != null || Intrinsics.areEqual(operationSymbol, ktToken)) {
            FirFunctionCallBuilder firFunctionCallBuilder = new FirFunctionCallBuilder();
            firFunctionCallBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(this, binaryExpression, null, 1, null));
            FirSimpleNamedReferenceBuilder firSimpleNamedReferenceBuilder = new FirSimpleNamedReferenceBuilder();
            firSimpleNamedReferenceBuilder.setSource(ktSourceElement2);
            firSimpleNamedReferenceBuilder.setName(binaryName == null ? ConverterUtilKt.nameAsSafeName$default(asText, null, 1, null) : binaryName);
            firFunctionCallBuilder.setCalleeReference(firSimpleNamedReferenceBuilder.build());
            firFunctionCallBuilder.setExplicitReceiver(firExpressionBuildErrorExpression);
            firFunctionCallBuilder.setArgumentList(FirArgumentUtilKt.buildUnaryArgumentList(firExpressionBuildErrorExpression$default));
            firFunctionCallBuilder.setOrigin(binaryName != null ? FirFunctionCallOrigin.Operator : FirFunctionCallOrigin.Infix);
            return firFunctionCallBuilder.mo288build();
        }
        FirOperation firOperation = ConversionUtilsKt.toFirOperation(operationSymbol);
        if (!FirOperation.INSTANCE.getASSIGNMENTS().contains(firOperation)) {
            FirEqualityOperatorCallBuilder firEqualityOperatorCallBuilder = new FirEqualityOperatorCallBuilder();
            firEqualityOperatorCallBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(this, binaryExpression, null, 1, null));
            firEqualityOperatorCallBuilder.setOperation(firOperation);
            firEqualityOperatorCallBuilder.setArgumentList(FirArgumentUtilKt.buildBinaryArgumentList(firExpressionBuildErrorExpression, firExpressionBuildErrorExpression$default));
            return firEqualityOperatorCallBuilder.mo288build();
        }
        KtSourceElement firSourceElement$default6 = AbstractRawFirBuilder.toFirSourceElement$default(this, binaryExpression, null, 1, null);
        if (lighterASTNode2 != null) {
            ktSourceElement = (KtLightSourceElement) AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode2, null, 1, null);
            lighterASTNode = lighterASTNode2;
        } else {
            lighterASTNode = lighterASTNode2;
            ktSourceElement = null;
        }
        return generateAssignment(lighterASTNode, firSourceElement$default6, ktSourceElement, firExpressionBuildErrorExpression$default, firOperation, firExpressionBuildErrorExpression.getAnnotations(), lighterASTNode4, CollectionsKt.contains(PsiUtilsKt.getUNWRAPPABLE_TOKEN_TYPES(), lighterASTNode != null ? lighterASTNode.getTokenType() : null), new Function1() { // from class: s19
            public final Object invoke(Object obj) {
                return LightTreeRawFirExpressionBuilder.n(this.b, binaryExpression, (LighterASTNode) obj);
            }
        });
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    private final FirTypeOperatorCall convertBinaryWithTypeRHSExpression(LighterASTNode binaryExpression) throws Exception {
        LighterASTNode lighterASTNode;
        KtSourceElement firSourceElement$default;
        FirExpression firExpressionBuildErrorExpression;
        KtSourceElement source;
        KtSourceElement firSourceElement$default2;
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        LighterASTNode[] childrenAsArray = getChildrenAsArray(binaryExpression);
        int length = childrenAsArray.length;
        LighterASTNode lighterASTNode2 = null;
        FirTypeRef firTypeRefConvertType = null;
        for (int i = 0; i < length && (lighterASTNode = childrenAsArray[i]) != null; i++) {
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType())) {
                IElementType tokenType = lighterASTNode.getTokenType();
                if (Intrinsics.areEqual(tokenType, KtNodeTypes.OPERATION_REFERENCE)) {
                    lighterASTNode2 = lighterASTNode;
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.TYPE_REFERENCE)) {
                    firTypeRefConvertType = this.declarationBuilder.convertType(lighterASTNode);
                } else if (ElementTypeUtils.INSTANCE.isExpression(lighterASTNode)) {
                    FirStatement asFirStatement = getAsFirStatement(lighterASTNode, "No left operand");
                    if (asFirStatement instanceof FirExpression) {
                        firExpressionBuildErrorExpression = (FirExpression) asFirStatement;
                        if (UtilsKt.isStatementLikeExpression(firExpressionBuildErrorExpression)) {
                            KtSourceElement source2 = firExpressionBuildErrorExpression.getSource();
                            if (source2 == null || (firSourceElement$default2 = KtSourceElementKt.realElement(source2)) == null) {
                                firSourceElement$default2 = AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode, null, 1, null);
                            }
                            firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default2, new ConeSimpleDiagnostic("No left operand", DiagnosticKind.ExpressionExpected), asFirStatement);
                        }
                    } else {
                        if (asFirStatement == null || (source = asFirStatement.getSource()) == null || (firSourceElement$default = KtSourceElementKt.realElement(source)) == null) {
                            firSourceElement$default = AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode, null, 1, null);
                        }
                        firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default, new ConeSimpleDiagnostic("No left operand", DiagnosticKind.ExpressionExpected), asFirStatement);
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
        }
        FirTypeOperatorCallBuilder firTypeOperatorCallBuilder = new FirTypeOperatorCallBuilder();
        firTypeOperatorCallBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(this, binaryExpression, null, 1, null));
        ElementTypeUtils elementTypeUtils = ElementTypeUtils.INSTANCE;
        if (lighterASTNode2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("operationReference");
            lighterASTNode2 = null;
        }
        firTypeOperatorCallBuilder.setOperation(ConversionUtilsKt.toFirOperation(elementTypeUtils.getOperationSymbol(lighterASTNode2, getTree())));
        if (firTypeRefConvertType == null) {
            Intrinsics.throwUninitializedPropertyAccessException("firType");
            firTypeRefConvertType = null;
        }
        firTypeOperatorCallBuilder.setConversionTypeRef(firTypeRefConvertType);
        FirExpression firExpressionBuildErrorExpression$default = (FirExpression) objectRef.element;
        if (firExpressionBuildErrorExpression$default == null) {
            firExpressionBuildErrorExpression$default = FirExpressionUtilKt.buildErrorExpression$default(AbstractRawFirBuilder.toFirSourceElement$default(this, binaryExpression, null, 1, null), new ConeSyntaxDiagnostic("No left operand"), null, 4, null);
        }
        firTypeOperatorCallBuilder.setArgumentList(FirArgumentUtilKt.buildUnaryArgumentList(firExpressionBuildErrorExpression$default));
        return firTypeOperatorCallBuilder.mo288build();
    }

    /* JADX WARN: Code duplicated, block: B:70:0x01ac  */
    /* JADX WARN: Code duplicated, block: B:75:0x01bb  */
    /* JADX WARN: Code duplicated, block: B:77:0x01c1  */
    /* JADX WARN: Code duplicated, block: B:78:0x01c4  */
    /* JADX WARN: Code duplicated, block: B:80:0x01c7  */
    /* JADX WARN: Code duplicated, block: B:83:0x01d2  */
    private final FirExpression convertCallExpression(LighterASTNode callSuffix) {
        CalleeAndReceiver calleeAndReceiver;
        LighterASTNode lighterASTNode;
        Object obj;
        FirResolvable firResolvable;
        KtLightSourceElement source;
        FirReference calleeReference;
        FirQualifiedAccessExpressionBuilder firQualifiedAccessExpressionBuilder;
        KtLightSourceElement firSourceElement$default;
        LighterASTNode firstChild;
        KtSourceElement firSourceElement$default2;
        FirExpression firExpressionBuildErrorExpression;
        KtSourceElement source2;
        KtSourceElement firSourceElement$default3;
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
        for (LighterASTNode lighterASTNode2 : getChildrenAsArray(callSuffix)) {
            if (lighterASTNode2 == null) {
                break;
            }
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode2.getTokenType())) {
                convertCallExpression$lambda$0$process(objectRef, this, objectRef3, objectRef2, arrayList, booleanRef, arrayList2, lighterASTNode2);
            }
        }
        KtLightSourceElement firSourceElement$default4 = AbstractRawFirBuilder.toFirSourceElement$default(this, callSuffix, null, 1, null);
        if (getImitateLambdaSuspendModifier() && Intrinsics.areEqual(objectRef.element, StandardClassIds.Callables.INSTANCE.getSuspend().getCallableName().getIdentifier())) {
            LighterASTNode parent = getParent(callSuffix);
            if (!Intrinsics.areEqual(getSelectorExpression(parent), callSuffix) || getReceiverExpression(parent) == null) {
                LighterASTNode lighterASTNode3 = (LighterASTNode) CollectionsKt.singleOrNull(arrayList2);
                if (Intrinsics.areEqual(lighterASTNode3 != null ? lighterASTNode3.getTokenType() : null, KtNodeTypes.LAMBDA_ARGUMENT) && arrayList.isEmpty() && (firstChild = getFirstChild((LighterASTNode) CollectionsKt.single(arrayList2))) != null) {
                    FirStatement asFirStatement = getAsFirStatement(firstChild, Argument.Delimiters.none);
                    if (asFirStatement instanceof FirAnonymousFunctionExpression) {
                        firExpressionBuildErrorExpression = (FirExpression) asFirStatement;
                        if (UtilsKt.isStatementLikeExpression(firExpressionBuildErrorExpression)) {
                            KtSourceElement source3 = firExpressionBuildErrorExpression.getSource();
                            if (source3 == null || (firSourceElement$default3 = KtSourceElementKt.realElement(source3)) == null) {
                                firSourceElement$default3 = AbstractRawFirBuilder.toFirSourceElement$default(this, firstChild, null, 1, null);
                            }
                            firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default3, new ConeSimpleDiagnostic(Argument.Delimiters.none, DiagnosticKind.ExpressionExpected), asFirStatement);
                        }
                    } else {
                        if (asFirStatement == null || (source2 = asFirStatement.getSource()) == null || (firSourceElement$default2 = KtSourceElementKt.realElement(source2)) == null) {
                            firSourceElement$default2 = AbstractRawFirBuilder.toFirSourceElement$default(this, firstChild, null, 1, null);
                        }
                        firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default2, new ConeSimpleDiagnostic(Argument.Delimiters.none, DiagnosticKind.ExpressionExpected), asFirStatement);
                    }
                    if (firExpressionBuildErrorExpression == null) {
                        x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression");
                        return null;
                    }
                    FirAnonymousFunctionExpression firAnonymousFunctionExpression = (FirAnonymousFunctionExpression) firExpressionBuildErrorExpression;
                    FirAnonymousFunction anonymousFunction = firAnonymousFunctionExpression.getAnonymousFunction();
                    FirDeclarationStatus status = firAnonymousFunctionExpression.getAnonymousFunction().getStatus();
                    anonymousFunction.replaceStatus(UtilsKt.copy(status, (8388575 & 1) != 0 ? status.getVisibility() : null, (8388575 & 2) != 0 ? status.getModality() : null, (8388575 & 4) != 0 ? status.isExpect() : false, (8388575 & 8) != 0 ? status.isActual() : false, (8388575 & 16) != 0 ? status.isOverride() : false, (8388575 & 32) != 0 ? status.isOperator() : false, (8388575 & 64) != 0 ? status.isInfix() : false, (8388575 & 128) != 0 ? status.isInline() : false, (8388575 & 256) != 0 ? status.isValue() : false, (8388575 & 512) != 0 ? status.isTailRec() : false, (8388575 & 1024) != 0 ? status.isExternal() : false, (8388575 & 2048) != 0 ? status.isConst() : false, (8388575 & 4096) != 0 ? status.isLateInit() : false, (8388575 & 8192) != 0 ? status.isInner() : false, (8388575 & 16384) != 0 ? status.isCompanion() : false, (8388575 & 32768) != 0 ? status.isData() : false, (8388575 & 65536) != 0 ? status.isSuspend() : true, (8388575 & 131072) != 0 ? status.isStatic() : false, (8388575 & 262144) != 0 ? status.isFromSealedClass() : false, (8388575 & 524288) != 0 ? status.isFromEnumClass() : false, (8388575 & 1048576) != 0 ? status.isFun() : false, (8388575 & 2097152) != 0 ? status.getHasStableParameterNames() : false, (8388575 & 4194304) != 0 ? status.getReturnValueStatus() : null));
                    return firAnonymousFunctionExpression;
                }
            }
        }
        if (objectRef.element != null) {
            FirSimpleNamedReferenceBuilder firSimpleNamedReferenceBuilder = new FirSimpleNamedReferenceBuilder();
            LighterASTNode firstChildExpressionUnwrapped = getFirstChildExpressionUnwrapped(callSuffix);
            if (firstChildExpressionUnwrapped == null || (firSourceElement$default = AbstractRawFirBuilder.toFirSourceElement$default(this, firstChildExpressionUnwrapped, null, 1, null)) == null) {
                firSourceElement$default = firSourceElement$default4;
            }
            firSimpleNamedReferenceBuilder.setSource(firSourceElement$default);
            firSimpleNamedReferenceBuilder.setName(ConverterUtilKt.nameAsSafeName$default((String) objectRef.element, null, 1, null));
            calleeAndReceiver = new CalleeAndReceiver(firSimpleNamedReferenceBuilder.build(), null, 2, null);
        } else {
            Object obj2 = objectRef3.element;
            if (obj2 == null) {
                Object obj3 = objectRef2.element;
                if (obj3 instanceof FirSuperReceiverExpression) {
                    lighterASTNode = (LighterASTNode) obj2;
                    if (lighterASTNode != null || (source = AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode, null, 1, null)) == null) {
                        obj = objectRef2.element;
                        if (obj instanceof FirResolvable) {
                            firResolvable = (FirResolvable) obj;
                        } else {
                            firResolvable = null;
                        }
                        if (firResolvable != null || (calleeReference = firResolvable.getCalleeReference()) == null) {
                            source = null;
                        } else {
                            source = calleeReference.getSource();
                        }
                    }
                    calleeAndReceiver = new CalleeAndReceiver(FirReferenceUtilsKt.buildErrorNamedReferenceWithNoName(new ConeSimpleDiagnostic("Super cannot be a callee", DiagnosticKind.SuperNotAllowed), source), null, 2, null);
                } else if (obj3 != null) {
                    FirSimpleNamedReferenceBuilder firSimpleNamedReferenceBuilder2 = new FirSimpleNamedReferenceBuilder();
                    firSimpleNamedReferenceBuilder2.setSource(firSourceElement$default4);
                    firSimpleNamedReferenceBuilder2.setName(OperatorNameConventions.INVOKE);
                    calleeAndReceiver = new CalleeAndReceiver(firSimpleNamedReferenceBuilder2.build(), (FirExpression) objectRef2.element);
                } else {
                    calleeAndReceiver = new CalleeAndReceiver(FirReferenceUtilsKt.buildErrorNamedReferenceWithNoName(new ConeSyntaxDiagnostic("Call has no callee"), firSourceElement$default4), null, 2, null);
                }
            } else {
                lighterASTNode = (LighterASTNode) obj2;
                if (lighterASTNode != null) {
                    obj = objectRef2.element;
                    if (obj instanceof FirResolvable) {
                        firResolvable = (FirResolvable) obj;
                    } else {
                        firResolvable = null;
                    }
                    if (firResolvable != null) {
                        source = null;
                    } else {
                        source = null;
                    }
                } else {
                    obj = objectRef2.element;
                    if (obj instanceof FirResolvable) {
                        firResolvable = (FirResolvable) obj;
                    } else {
                        firResolvable = null;
                    }
                    if (firResolvable != null) {
                        source = null;
                    } else {
                        source = null;
                    }
                }
                calleeAndReceiver = new CalleeAndReceiver(FirReferenceUtilsKt.buildErrorNamedReferenceWithNoName(new ConeSimpleDiagnostic("Super cannot be a callee", DiagnosticKind.SuperNotAllowed), source), null, 2, null);
            }
        }
        FirNamedReference reference = calleeAndReceiver.getReference();
        FirExpression receiverForInvoke = calleeAndReceiver.getReceiverForInvoke();
        if (booleanRef.element) {
            FirAbstractFunctionCallBuilder firImplicitInvokeCallBuilder = receiverForInvoke != null ? new FirImplicitInvokeCallBuilder() : new FirFunctionCallBuilder();
            firImplicitInvokeCallBuilder.setSource(firSourceElement$default4);
            firImplicitInvokeCallBuilder.setCalleeReference(reference);
            getContext().getCalleeNamesForLambda().add(reference.getName());
            ArrayList arrayList3 = new ArrayList();
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                CollectionsKt.addAll(arrayList3, convertValueArguments((LighterASTNode) it.next()));
            }
            ConverterUtilKt.extractArgumentsFrom(firImplicitInvokeCallBuilder, arrayList3);
            removeLast(getContext().getCalleeNamesForLambda());
            firQualifiedAccessExpressionBuilder = firImplicitInvokeCallBuilder;
        } else {
            FirPropertyAccessExpressionBuilder firPropertyAccessExpressionBuilder = new FirPropertyAccessExpressionBuilder();
            firPropertyAccessExpressionBuilder.setSource(firSourceElement$default4);
            firPropertyAccessExpressionBuilder.setCalleeReference(reference);
            firQualifiedAccessExpressionBuilder = firPropertyAccessExpressionBuilder;
        }
        firQualifiedAccessExpressionBuilder.setExplicitReceiver(receiverForInvoke);
        CollectionsKt.addAll(firQualifiedAccessExpressionBuilder.getTypeArguments(), arrayList);
        return ConversionUtilsKt.pullUpSafeCallIfNecessary(firQualifiedAccessExpressionBuilder.mo373build());
    }

    private static final void convertCallExpression$lambda$0$process(Ref.ObjectRef<String> objectRef, LightTreeRawFirExpressionBuilder lightTreeRawFirExpressionBuilder, Ref.ObjectRef<LighterASTNode> objectRef2, Ref.ObjectRef<FirExpression> objectRef3, List<FirTypeProjection> list, Ref.BooleanRef booleanRef, List<LighterASTNode> list2, LighterASTNode lighterASTNode) {
        KtSourceElement firSourceElement$default;
        FirExpression firExpressionBuildErrorExpression;
        KtSourceElement source;
        KtSourceElement firSourceElement$default2;
        KtSourceElement firSourceElement$default3;
        FirExpression firExpressionBuildErrorExpression2;
        KtSourceElement source2;
        KtSourceElement firSourceElement$default4;
        IElementType tokenType = lighterASTNode.getTokenType();
        if (Intrinsics.areEqual(tokenType, KtNodeTypes.REFERENCE_EXPRESSION)) {
            objectRef.element = lightTreeRawFirExpressionBuilder.getAsText(lighterASTNode);
            return;
        }
        if (Intrinsics.areEqual(tokenType, KtNodeTypes.SUPER_EXPRESSION)) {
            objectRef2.element = lighterASTNode;
            return;
        }
        if (Intrinsics.areEqual(tokenType, KtNodeTypes.PARENTHESIZED)) {
            if (Intrinsics.areEqual(lighterASTNode.getTokenType(), TokenType.ERROR_ELEMENT)) {
                return;
            }
            LighterASTNode expressionInParentheses = lightTreeRawFirExpressionBuilder.getExpressionInParentheses(lighterASTNode);
            FirStatement asFirStatement = expressionInParentheses != null ? lightTreeRawFirExpressionBuilder.getAsFirStatement(expressionInParentheses, "Incorrect invoke receiver") : null;
            if (asFirStatement instanceof FirExpression) {
                firExpressionBuildErrorExpression2 = (FirExpression) asFirStatement;
                if (UtilsKt.isStatementLikeExpression(firExpressionBuildErrorExpression2)) {
                    KtSourceElement source3 = firExpressionBuildErrorExpression2.getSource();
                    if (source3 == null || (firSourceElement$default4 = KtSourceElementKt.realElement(source3)) == null) {
                        firSourceElement$default4 = expressionInParentheses != null ? AbstractRawFirBuilder.toFirSourceElement$default(lightTreeRawFirExpressionBuilder, expressionInParentheses, null, 1, null) : lightTreeRawFirExpressionBuilder.toFirSourceElement(lighterASTNode, (KtFakeSourceElementKind) KtFakeSourceElementKind.ErrorExpression.INSTANCE);
                    }
                    firExpressionBuildErrorExpression2 = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default4, new ConeSimpleDiagnostic("Incorrect invoke receiver", DiagnosticKind.ExpressionExpected), asFirStatement);
                }
            } else {
                if (asFirStatement == null || (source2 = asFirStatement.getSource()) == null || (firSourceElement$default3 = KtSourceElementKt.realElement(source2)) == null) {
                    firSourceElement$default3 = expressionInParentheses != null ? AbstractRawFirBuilder.toFirSourceElement$default(lightTreeRawFirExpressionBuilder, expressionInParentheses, null, 1, null) : lightTreeRawFirExpressionBuilder.toFirSourceElement(lighterASTNode, (KtFakeSourceElementKind) KtFakeSourceElementKind.ErrorExpression.INSTANCE);
                }
                firExpressionBuildErrorExpression2 = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default3, expressionInParentheses == null ? new ConeSyntaxDiagnostic("Incorrect invoke receiver") : new ConeSimpleDiagnostic("Incorrect invoke receiver", DiagnosticKind.ExpressionExpected), asFirStatement);
            }
            if (firExpressionBuildErrorExpression2 != null) {
                objectRef3.element = firExpressionBuildErrorExpression2;
                return;
            } else {
                x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.expressions.FirExpression");
                return;
            }
        }
        if (Intrinsics.areEqual(tokenType, KtNodeTypes.TYPE_ARGUMENT_LIST)) {
            CollectionsKt.addAll(list, lightTreeRawFirExpressionBuilder.declarationBuilder.convertTypeArguments(lighterASTNode, true));
            return;
        }
        if (Intrinsics.areEqual(tokenType, KtNodeTypes.VALUE_ARGUMENT_LIST) || Intrinsics.areEqual(tokenType, KtNodeTypes.LAMBDA_ARGUMENT)) {
            booleanRef.element = true;
            list2.add(lighterASTNode);
            return;
        }
        if (Intrinsics.areEqual(lighterASTNode.getTokenType(), TokenType.ERROR_ELEMENT)) {
            return;
        }
        FirStatement asFirStatement2 = lightTreeRawFirExpressionBuilder.getAsFirStatement(lighterASTNode, "Incorrect invoke receiver");
        if (asFirStatement2 instanceof FirExpression) {
            firExpressionBuildErrorExpression = (FirExpression) asFirStatement2;
            if (UtilsKt.isStatementLikeExpression(firExpressionBuildErrorExpression)) {
                KtSourceElement source4 = firExpressionBuildErrorExpression.getSource();
                if (source4 == null || (firSourceElement$default2 = KtSourceElementKt.realElement(source4)) == null) {
                    firSourceElement$default2 = AbstractRawFirBuilder.toFirSourceElement$default(lightTreeRawFirExpressionBuilder, lighterASTNode, null, 1, null);
                }
                firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default2, new ConeSimpleDiagnostic("Incorrect invoke receiver", DiagnosticKind.ExpressionExpected), asFirStatement2);
            }
        } else {
            if (asFirStatement2 == null || (source = asFirStatement2.getSource()) == null || (firSourceElement$default = KtSourceElementKt.realElement(source)) == null) {
                firSourceElement$default = AbstractRawFirBuilder.toFirSourceElement$default(lightTreeRawFirExpressionBuilder, lighterASTNode, null, 1, null);
            }
            firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default, new ConeSimpleDiagnostic("Incorrect invoke receiver", DiagnosticKind.ExpressionExpected), asFirStatement2);
        }
        if (firExpressionBuildErrorExpression != null) {
            objectRef3.element = firExpressionBuildErrorExpression;
        } else {
            x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.expressions.FirExpression");
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    /* JADX WARN: Code duplicated, block: B:62:0x00b8 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:69:0x00c7 A[SYNTHETIC] */
    private final FirCallableReferenceAccess convertCallableReferenceExpression(LighterASTNode callableReferenceExpression) throws UninitializedPropertyAccessException {
        KtSourceElement firSourceElement$default;
        FirErrorExpression firErrorExpressionBuildErrorExpression;
        KtSourceElement source;
        KtSourceElement firSourceElement$default2;
        FirExpression firExpression = null;
        FirNamedReference firNamedReferenceCreateSimpleNamedReference = null;
        LighterASTNode lighterASTNode = null;
        boolean z = false;
        boolean z2 = true;
        for (LighterASTNode lighterASTNode2 : getChildrenAsArray(callableReferenceExpression)) {
            if (lighterASTNode2 == null) {
                break;
            }
            IElementType tokenType = lighterASTNode2.getTokenType();
            if (Intrinsics.areEqual(tokenType, KtTokens.COLONCOLON)) {
                z2 = false;
            } else if (Intrinsics.areEqual(tokenType, KtTokens.QUEST)) {
                z = true;
            } else if (Intrinsics.areEqual(tokenType, TokenType.ERROR_ELEMENT)) {
                LighterASTNode[] childrenAsArray = getChildrenAsArray(lighterASTNode2);
                int length = childrenAsArray.length;
                for (int i = 0; i < length; i++) {
                    LighterASTNode lighterASTNode3 = childrenAsArray[i];
                    if (Intrinsics.areEqual(lighterASTNode3 != null ? lighterASTNode3.getTokenType() : null, KtNodeTypes.VALUE_ARGUMENT_LIST)) {
                        lighterASTNode = lighterASTNode3;
                        break;
                    }
                }
            } else if (!ElementTypeUtils.INSTANCE.isExpression(lighterASTNode2)) {
                continue;
            } else if (z2) {
                FirStatement asFirStatement = getAsFirStatement(lighterASTNode2, "Incorrect receiver expression");
                if (asFirStatement instanceof FirExpression) {
                    firExpression = (FirExpression) asFirStatement;
                    if (UtilsKt.isStatementLikeExpression(firExpression)) {
                        KtSourceElement source2 = firExpression.getSource();
                        if (source2 == null || (firSourceElement$default2 = KtSourceElementKt.realElement(source2)) == null) {
                            firSourceElement$default2 = AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode2, null, 1, null);
                        }
                        firErrorExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default2, new ConeSimpleDiagnostic("Incorrect receiver expression", DiagnosticKind.ExpressionExpected), asFirStatement);
                    }
                    if (firExpression != null) {
                        x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.expressions.FirExpression");
                        return null;
                    }
                } else {
                    if (asFirStatement == null || (source = asFirStatement.getSource()) == null || (firSourceElement$default = KtSourceElementKt.realElement(source)) == null) {
                        firSourceElement$default = AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode2, null, 1, null);
                    }
                    firErrorExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default, new ConeSimpleDiagnostic("Incorrect receiver expression", DiagnosticKind.ExpressionExpected), asFirStatement);
                }
                firExpression = firErrorExpressionBuildErrorExpression;
                if (firExpression != null) {
                    x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.expressions.FirExpression");
                    return null;
                }
            } else {
                firNamedReferenceCreateSimpleNamedReference = createSimpleNamedReference(AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode2, null, 1, null), lighterASTNode2);
            }
        }
        FirCallableReferenceAccessBuilder firCallableReferenceAccessBuilder = new FirCallableReferenceAccessBuilder();
        firCallableReferenceAccessBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(this, callableReferenceExpression, null, 1, null));
        if (firNamedReferenceCreateSimpleNamedReference == null) {
            Intrinsics.throwUninitializedPropertyAccessException("namedReference");
            firNamedReferenceCreateSimpleNamedReference = null;
        }
        firCallableReferenceAccessBuilder.setCalleeReference(firNamedReferenceCreateSimpleNamedReference);
        firCallableReferenceAccessBuilder.setExplicitReceiver(firExpression);
        firCallableReferenceAccessBuilder.setHasQuestionMarkAtLHS(z);
        if (lighterASTNode != null) {
            FirArgumentListBuilder firArgumentListBuilder = new FirArgumentListBuilder();
            firArgumentListBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode, null, 1, null));
            CollectionsKt.addAll(firArgumentListBuilder.getArguments(), convertValueArguments(lighterASTNode));
            firCallableReferenceAccessBuilder.setErrorArgumentList(firArgumentListBuilder.build());
        }
        return firCallableReferenceAccessBuilder.mo288build();
    }

    private final Triple<ValueParameter, FirBlock, KtLightSourceElement> convertCatchClause(LighterASTNode catchClause) {
        ValueParameter valueParameter = null;
        LighterASTNode lighterASTNode = null;
        for (LighterASTNode lighterASTNode2 : getChildrenAsArray(catchClause)) {
            if (lighterASTNode2 == null) {
                break;
            }
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode2.getTokenType())) {
                IElementType tokenType = lighterASTNode2.getTokenType();
                if (Intrinsics.areEqual(tokenType, KtNodeTypes.VALUE_PARAMETER_LIST)) {
                    valueParameter = (ValueParameter) CollectionsKt.firstOrNull(LightTreeRawFirDeclarationBuilder.convertValueParameters$default(this.declarationBuilder, lighterASTNode2, new FirAnonymousFunctionSymbol(), AbstractRawFirBuilder.ValueParameterDeclaration.CATCH, null, 8, null));
                    if (valueParameter == null) {
                        return null;
                    }
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.BLOCK)) {
                    lighterASTNode = lighterASTNode2;
                }
            }
        }
        return new Triple<>(valueParameter, LightTreeRawFirDeclarationBuilder.convertBlock$default(this.declarationBuilder, lighterASTNode, false, 2, null), AbstractRawFirBuilder.toFirSourceElement$default(this, catchClause, null, 1, null));
    }

    private final FirGetClassCall convertClassLiteralExpression(LighterASTNode classLiteralExpression) {
        LighterASTNode lighterASTNode;
        KtSourceElement firSourceElement$default;
        FirExpression firExpressionBuildErrorExpression;
        KtSourceElement source;
        KtSourceElement firSourceElement$default2;
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        LighterASTNode[] childrenAsArray = getChildrenAsArray(classLiteralExpression);
        int length = childrenAsArray.length;
        for (int i = 0; i < length && (lighterASTNode = childrenAsArray[i]) != null; i++) {
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType()) && ElementTypeUtils.INSTANCE.isExpression(lighterASTNode)) {
                FirStatement asFirStatement = getAsFirStatement(lighterASTNode, "No receiver in class literal");
                if (asFirStatement instanceof FirExpression) {
                    firExpressionBuildErrorExpression = (FirExpression) asFirStatement;
                    if (UtilsKt.isStatementLikeExpression(firExpressionBuildErrorExpression)) {
                        KtSourceElement source2 = firExpressionBuildErrorExpression.getSource();
                        if (source2 == null || (firSourceElement$default2 = KtSourceElementKt.realElement(source2)) == null) {
                            firSourceElement$default2 = AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode, null, 1, null);
                        }
                        firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default2, new ConeSimpleDiagnostic("No receiver in class literal", DiagnosticKind.ExpressionExpected), asFirStatement);
                    }
                } else {
                    if (asFirStatement == null || (source = asFirStatement.getSource()) == null || (firSourceElement$default = KtSourceElementKt.realElement(source)) == null) {
                        firSourceElement$default = AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode, null, 1, null);
                    }
                    firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default, new ConeSimpleDiagnostic("No receiver in class literal", DiagnosticKind.ExpressionExpected), asFirStatement);
                }
                if (firExpressionBuildErrorExpression == null) {
                    x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.expressions.FirExpression");
                    return null;
                }
                objectRef.element = firExpressionBuildErrorExpression;
            }
        }
        KtSourceElement ktSourceElement = (KtLightSourceElement) AbstractRawFirBuilder.toFirSourceElement$default(this, classLiteralExpression, null, 1, null);
        FirGetClassCallBuilder firGetClassCallBuilder = new FirGetClassCallBuilder();
        firGetClassCallBuilder.setSource(ktSourceElement);
        FirExpression firExpressionBuildErrorExpression$default = (FirExpression) objectRef.element;
        if (firExpressionBuildErrorExpression$default == null) {
            firExpressionBuildErrorExpression$default = FirExpressionUtilKt.buildErrorExpression$default(ktSourceElement, ConeUnsupportedClassLiteralsWithEmptyLhs.INSTANCE, null, 4, null);
        }
        firGetClassCallBuilder.setArgumentList(FirArgumentUtilKt.buildUnaryArgumentList(firExpressionBuildErrorExpression$default));
        return firGetClassCallBuilder.mo288build();
    }

    private final FirCollectionLiteral convertCollectionLiteralExpression(LighterASTNode expression) {
        LighterASTNode lighterASTNode;
        KtSourceElement firSourceElement$default;
        FirExpression firExpressionBuildErrorExpression;
        KtSourceElement source;
        KtSourceElement firSourceElement$default2;
        ArrayList arrayList = new ArrayList();
        LighterASTNode[] childrenAsArray = getChildrenAsArray(expression);
        int length = childrenAsArray.length;
        for (int i = 0; i < length && (lighterASTNode = childrenAsArray[i]) != null; i++) {
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType()) && ElementTypeUtils.INSTANCE.isExpression(lighterASTNode)) {
                FirStatement asFirStatement = getAsFirStatement(lighterASTNode, "Incorrect collection literal argument");
                if (asFirStatement instanceof FirExpression) {
                    firExpressionBuildErrorExpression = (FirExpression) asFirStatement;
                    if (UtilsKt.isStatementLikeExpression(firExpressionBuildErrorExpression)) {
                        KtSourceElement source2 = firExpressionBuildErrorExpression.getSource();
                        if (source2 == null || (firSourceElement$default2 = KtSourceElementKt.realElement(source2)) == null) {
                            firSourceElement$default2 = AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode, null, 1, null);
                        }
                        firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default2, new ConeSimpleDiagnostic("Incorrect collection literal argument", DiagnosticKind.ExpressionExpected), asFirStatement);
                    }
                } else {
                    if (asFirStatement == null || (source = asFirStatement.getSource()) == null || (firSourceElement$default = KtSourceElementKt.realElement(source)) == null) {
                        firSourceElement$default = AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode, null, 1, null);
                    }
                    firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default, new ConeSimpleDiagnostic("Incorrect collection literal argument", DiagnosticKind.ExpressionExpected), asFirStatement);
                }
                if (firExpressionBuildErrorExpression == null) {
                    x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.expressions.FirExpression");
                    return null;
                }
                arrayList.add(firExpressionBuildErrorExpression);
            }
        }
        FirArgumentListBuilder firArgumentListBuilder = new FirArgumentListBuilder();
        CollectionsKt.addAll(firArgumentListBuilder.getArguments(), arrayList);
        FirArgumentList firArgumentListBuild = firArgumentListBuilder.build();
        FirCollectionLiteralBuilder firCollectionLiteralBuilder = new FirCollectionLiteralBuilder();
        firCollectionLiteralBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(this, expression, null, 1, null));
        firCollectionLiteralBuilder.setArgumentList(firArgumentListBuild);
        return firCollectionLiteralBuilder.mo288build();
    }

    private final FirExpression convertConstantExpression(LighterASTNode constantExpression) {
        return generateConstantExpressionByLiteral(constantExpression);
    }

    private final FirLoop convertDoWhile(LighterASTNode doWhileLoop) {
        LighterASTNode lighterASTNode;
        KtSourceElement firSourceElement$default;
        FirExpression firExpressionBuildErrorExpression;
        KtSourceElement source;
        KtSourceElement firSourceElement$default2;
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        FirDoWhileLoopBuilder firDoWhileLoopBuilder = new FirDoWhileLoopBuilder();
        firDoWhileLoopBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(this, doWhileLoop, null, 1, null));
        FirLoopTarget firLoopTargetPrepareTarget = prepareTarget(firDoWhileLoopBuilder, doWhileLoop);
        LighterASTNode[] childrenAsArray = getChildrenAsArray(doWhileLoop);
        int length = childrenAsArray.length;
        for (int i = 0; i < length && (lighterASTNode = childrenAsArray[i]) != null; i++) {
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType())) {
                IElementType tokenType = lighterASTNode.getTokenType();
                if (Intrinsics.areEqual(tokenType, KtNodeTypes.BODY)) {
                    objectRef.element = lighterASTNode;
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.CONDITION)) {
                    FirStatement asFirStatement = getAsFirStatement(lighterASTNode, "No condition in do-while loop");
                    if (asFirStatement instanceof FirExpression) {
                        firExpressionBuildErrorExpression = (FirExpression) asFirStatement;
                        if (UtilsKt.isStatementLikeExpression(firExpressionBuildErrorExpression)) {
                            KtSourceElement source2 = firExpressionBuildErrorExpression.getSource();
                            if (source2 == null || (firSourceElement$default2 = KtSourceElementKt.realElement(source2)) == null) {
                                firSourceElement$default2 = AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode, null, 1, null);
                            }
                            firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default2, new ConeSimpleDiagnostic("No condition in do-while loop", DiagnosticKind.ExpressionExpected), asFirStatement);
                        }
                    } else {
                        if (asFirStatement == null || (source = asFirStatement.getSource()) == null || (firSourceElement$default = KtSourceElementKt.realElement(source)) == null) {
                            firSourceElement$default = AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode, null, 1, null);
                        }
                        firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default, new ConeSimpleDiagnostic("No condition in do-while loop", DiagnosticKind.ExpressionExpected), asFirStatement);
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
        }
        FirExpression firExpressionBuildErrorExpression$default = (FirExpression) objectRef2.element;
        if (firExpressionBuildErrorExpression$default == null) {
            firExpressionBuildErrorExpression$default = FirExpressionUtilKt.buildErrorExpression$default(AbstractRawFirBuilder.toFirSourceElement$default(this, doWhileLoop, null, 1, null), new ConeSyntaxDiagnostic("No condition in do-while loop"), null, 4, null);
        }
        firDoWhileLoopBuilder.setCondition(firExpressionBuildErrorExpression$default);
        return configure(firDoWhileLoopBuilder, firLoopTargetPrepareTarget, new Function0() { // from class: m19
            public final Object invoke() {
                return LightTreeRawFirExpressionBuilder.m(this.b, objectRef);
            }
        });
    }

    private final FirBlock convertFinally(LighterASTNode finallyExpression) {
        LighterASTNode lighterASTNode = null;
        for (LighterASTNode lighterASTNode2 : getChildrenAsArray(finallyExpression)) {
            if (lighterASTNode2 == null) {
                break;
            }
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode2.getTokenType()) && Intrinsics.areEqual(lighterASTNode2.getTokenType(), KtNodeTypes.BLOCK)) {
                lighterASTNode = lighterASTNode2;
            }
        }
        return LightTreeRawFirDeclarationBuilder.convertBlock$default(this.declarationBuilder, lighterASTNode, false, 2, null);
    }

    private final FirBlock convertFor(LighterASTNode forLoop) {
        KtSourceElement ktSourceElementFakeElement$default;
        LighterASTNode lighterASTNode;
        KtSourceElement firSourceElement$default;
        FirExpression firExpressionBuildErrorExpression;
        KtSourceElement source;
        KtSourceElement firSourceElement$default2;
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        final Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
        LighterASTNode[] childrenAsArray = getChildrenAsArray(forLoop);
        int length = childrenAsArray.length;
        for (int i = 0; i < length && (lighterASTNode = childrenAsArray[i]) != null; i++) {
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType())) {
                IElementType tokenType = lighterASTNode.getTokenType();
                if (Intrinsics.areEqual(tokenType, KtNodeTypes.VALUE_PARAMETER)) {
                    objectRef.element = LightTreeRawFirDeclarationBuilder.convertValueParameter$default(this.declarationBuilder, lighterASTNode, null, AbstractRawFirBuilder.ValueParameterDeclaration.FOR_LOOP, null, 8, null);
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.LOOP_RANGE)) {
                    FirStatement asFirStatement = getAsFirStatement(lighterASTNode, "No range in for loop");
                    if (asFirStatement instanceof FirExpression) {
                        firExpressionBuildErrorExpression = (FirExpression) asFirStatement;
                        if (UtilsKt.isStatementLikeExpression(firExpressionBuildErrorExpression)) {
                            KtSourceElement source2 = firExpressionBuildErrorExpression.getSource();
                            if (source2 == null || (firSourceElement$default2 = KtSourceElementKt.realElement(source2)) == null) {
                                firSourceElement$default2 = AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode, null, 1, null);
                            }
                            firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default2, new ConeSimpleDiagnostic("No range in for loop", DiagnosticKind.ExpressionExpected), asFirStatement);
                        }
                    } else {
                        if (asFirStatement == null || (source = asFirStatement.getSource()) == null || (firSourceElement$default = KtSourceElementKt.realElement(source)) == null) {
                            firSourceElement$default = AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode, null, 1, null);
                        }
                        firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default, new ConeSimpleDiagnostic("No range in for loop", DiagnosticKind.ExpressionExpected), asFirStatement);
                    }
                    if (firExpressionBuildErrorExpression == null) {
                        x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.expressions.FirExpression");
                        return null;
                    }
                    objectRef2.element = firExpressionBuildErrorExpression;
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.BODY)) {
                    objectRef3.element = lighterASTNode;
                }
            }
        }
        FirExpression firExpressionBuildErrorExpression$default = (FirExpression) objectRef2.element;
        if (firExpressionBuildErrorExpression$default == null) {
            firExpressionBuildErrorExpression$default = FirExpressionUtilKt.buildErrorExpression$default(AbstractRawFirBuilder.toFirSourceElement$default(this, forLoop, null, 1, null), new ConeSyntaxDiagnostic("No range in for loop"), null, 4, null);
        }
        KtFakeSourceElementKind.DesugaredForLoop desugaredForLoop = KtFakeSourceElementKind.DesugaredForLoop.INSTANCE;
        KtSourceElement firSourceElement = toFirSourceElement(forLoop, (KtFakeSourceElementKind) desugaredForLoop);
        KtSourceElement source3 = firExpressionBuildErrorExpression$default.getSource();
        KtSourceElement ktSourceElement = (source3 == null || (ktSourceElementFakeElement$default = KtSourceElementKt.fakeElement$default(source3, desugaredForLoop, null, 2, null)) == null) ? firSourceElement : ktSourceElementFakeElement$default;
        FirBlockBuilder firBlockBuilder = new FirBlockBuilder();
        firBlockBuilder.setSource(firSourceElement);
        FirModuleData baseModuleData = getBaseModuleData();
        Name name = SpecialNames.ITERATOR;
        FirFunctionCallBuilder firFunctionCallBuilder = new FirFunctionCallBuilder();
        firFunctionCallBuilder.setSource(ktSourceElement);
        FirSimpleNamedReferenceBuilder firSimpleNamedReferenceBuilder = new FirSimpleNamedReferenceBuilder();
        firSimpleNamedReferenceBuilder.setSource(ktSourceElement);
        firSimpleNamedReferenceBuilder.setName(OperatorNameConventions.ITERATOR);
        firFunctionCallBuilder.setCalleeReference(firSimpleNamedReferenceBuilder.build());
        firFunctionCallBuilder.setExplicitReceiver(firExpressionBuildErrorExpression$default);
        FirFunctionCallOrigin firFunctionCallOrigin = FirFunctionCallOrigin.Operator;
        firFunctionCallBuilder.setOrigin(firFunctionCallOrigin);
        Unit unit = Unit.INSTANCE;
        final FirProperty firPropertyGenerateTemporaryVariable$default = FirGenerationKt.generateTemporaryVariable$default(baseModuleData, ktSourceElement, name, firFunctionCallBuilder.mo288build(), null, null, null, 112, null);
        firBlockBuilder.getStatements().add(firPropertyGenerateTemporaryVariable$default);
        List<FirStatement> statements = firBlockBuilder.getStatements();
        FirWhileLoopBuilder firWhileLoopBuilder = new FirWhileLoopBuilder();
        firWhileLoopBuilder.setSource(firSourceElement);
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
        statements.add(configure(firWhileLoopBuilder, prepareTarget(firWhileLoopBuilder, forLoop), new Function0() { // from class: p19
            public final Object invoke() {
                return LightTreeRawFirExpressionBuilder.convertFor$lambda$1$2(objectRef3, this, objectRef, ktSourceElement2, firPropertyGenerateTemporaryVariable$default);
            }
        }));
        return firBlockBuilder.mo288build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FirBlock convertFor$lambda$1$2(Ref.ObjectRef objectRef, LightTreeRawFirExpressionBuilder lightTreeRawFirExpressionBuilder, Ref.ObjectRef objectRef2, KtSourceElement ktSourceElement, FirProperty firProperty) {
        Name name;
        FirBlockBuilder firBlockBuilder = new FirBlockBuilder();
        LighterASTNode lighterASTNode = (LighterASTNode) objectRef.element;
        firBlockBuilder.setSource(lighterASTNode != null ? AbstractRawFirBuilder.toFirSourceElement$default(lightTreeRawFirExpressionBuilder, lighterASTNode, null, 1, null) : null);
        ValueParameter valueParameter = (ValueParameter) objectRef2.element;
        if (valueParameter != null) {
            String asText = null;
            DestructuringDeclaration destructuringDeclaration = valueParameter.getDestructuringDeclaration();
            LighterASTNode lighterASTNode2 = valueParameter.getSource().getLighterASTNode();
            KtToken ktToken = KtTokens.IDENTIFIER;
            ktToken.getClass();
            LighterASTNode childNodeByType = lightTreeRawFirExpressionBuilder.getChildNodeByType(lighterASTNode2, (IElementType) ktToken);
            if (childNodeByType != null) {
                asText = lightTreeRawFirExpressionBuilder.getAsText(childNodeByType);
            }
            FirModuleData baseModuleData = lightTreeRawFirExpressionBuilder.getBaseModuleData();
            KtSourceElement source = valueParameter.getSource();
            if (destructuringDeclaration != null) {
                name = SpecialNames.DESTRUCT;
            } else {
                name = Intrinsics.areEqual(asText, InlineClassManglingUtilsKt.NOT_INLINE_CLASS_PARAMETER_PLACEHOLDER) ? SpecialNames.UNDERSCORE_FOR_UNUSED_VAR : valueParameter.getName();
            }
            Name name2 = name;
            FirFunctionCallBuilder firFunctionCallBuilder = new FirFunctionCallBuilder();
            firFunctionCallBuilder.setSource(ktSourceElement);
            FirSimpleNamedReferenceBuilder firSimpleNamedReferenceBuilder = new FirSimpleNamedReferenceBuilder();
            firSimpleNamedReferenceBuilder.setSource(ktSourceElement);
            firSimpleNamedReferenceBuilder.setName(OperatorNameConventions.NEXT);
            firFunctionCallBuilder.setCalleeReference(firSimpleNamedReferenceBuilder.build());
            firFunctionCallBuilder.setExplicitReceiver(ConversionUtilsKt.generateResolvedAccessExpression(ktSourceElement, firProperty));
            firFunctionCallBuilder.setOrigin(FirFunctionCallOrigin.Operator);
            Unit unit = Unit.INSTANCE;
            FirProperty firPropertyGenerateTemporaryVariable$default = FirGenerationKt.generateTemporaryVariable$default(baseModuleData, source, name2, firFunctionCallBuilder.mo288build(), valueParameter.getReturnTypeRef(), valueParameter.getAnnotations(), null, 64, null);
            ClassMembersKt.setForLoopParameter(firPropertyGenerateTemporaryVariable$default, Boolean.TRUE);
            if (destructuringDeclaration != null) {
                DestructuringDeclarationKt.addDestructuringStatements(lightTreeRawFirExpressionBuilder, firBlockBuilder.getStatements(), lightTreeRawFirExpressionBuilder.getBaseModuleData(), destructuringDeclaration, firPropertyGenerateTemporaryVariable$default, true, true, (64 & 64) != 0 ? new Function1() { // from class: ap3
                    public final Object invoke(Object obj) {
                        return DestructuringDeclarationKt.a((FirVariable) obj);
                    }
                } : null);
            } else {
                firBlockBuilder.getStatements().add(firPropertyGenerateTemporaryVariable$default);
            }
            firBlockBuilder.getStatements().add(lightTreeRawFirExpressionBuilder.convertLoopBody((LighterASTNode) objectRef.element));
        }
        return firBlockBuilder.mo288build();
    }

    private final FirWhenExpression convertIfExpression(LighterASTNode ifExpression) {
        FirBlock firBlockConvertLoopOrIfBody;
        FirWhenExpressionBuilder firWhenExpressionBuilder = new FirWhenExpressionBuilder();
        firWhenExpressionBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(this, ifExpression, null, 1, null));
        IfNodeComponents ifExpression2 = parseIfExpression(ifExpression);
        FirBlock firBlockConvertLoopBody = convertLoopBody(ifExpression2.getThenBlock());
        List<FirWhenBranch> branches = firWhenExpressionBuilder.getBranches();
        FirRegularWhenBranchBuilder firRegularWhenBranchBuilder = new FirRegularWhenBranchBuilder();
        FirExpression firCondition = ifExpression2.getFirCondition();
        firRegularWhenBranchBuilder.setSource(firCondition != null ? firCondition.getSource() : null);
        FirExpression firCondition2 = ifExpression2.getFirCondition();
        if (firCondition2 == null) {
            firCondition2 = FirExpressionUtilKt.buildErrorExpression$default(AbstractRawFirBuilder.toFirSourceElement$default(this, ifExpression, null, 1, null), new ConeSyntaxDiagnostic("If statement should have condition"), null, 4, null);
        }
        firRegularWhenBranchBuilder.setCondition(firCondition2);
        firRegularWhenBranchBuilder.setResult(firBlockConvertLoopBody);
        branches.add(firRegularWhenBranchBuilder.build());
        if (ifExpression2.getElseBlock() != null && (firBlockConvertLoopOrIfBody = convertLoopOrIfBody(ifExpression2.getElseBlock())) != null) {
            List<FirWhenBranch> branches2 = firWhenExpressionBuilder.getBranches();
            FirRegularWhenBranchBuilder firRegularWhenBranchBuilder2 = new FirRegularWhenBranchBuilder();
            firRegularWhenBranchBuilder2.setSource(AbstractRawFirBuilder.toFirSourceElement$default(this, ifExpression2.getElseBlock(), null, 1, null));
            FirElseIfTrueConditionBuilder firElseIfTrueConditionBuilder = new FirElseIfTrueConditionBuilder();
            Unit unit = Unit.INSTANCE;
            firRegularWhenBranchBuilder2.setCondition(firElseIfTrueConditionBuilder.mo288build());
            firRegularWhenBranchBuilder2.setResult(firBlockConvertLoopOrIfBody);
            branches2.add(firRegularWhenBranchBuilder2.build());
        }
        firWhenExpressionBuilder.setUsedAsExpression(getUsedAsExpression(ifExpression));
        return firWhenExpressionBuilder.mo288build();
    }

    private final List<FirExpression> convertIndices(LighterASTNode indices) {
        KtSourceElement firSourceElement$default;
        FirExpression firExpressionBuildErrorExpression;
        KtSourceElement source;
        KtSourceElement firSourceElement$default2;
        ArrayList arrayList = new ArrayList();
        for (LighterASTNode lighterASTNode : getChildrenAsArray(indices)) {
            if (lighterASTNode == null) {
                break;
            }
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType()) && ElementTypeUtils.INSTANCE.isExpression(lighterASTNode)) {
                FirStatement asFirStatement = getAsFirStatement(lighterASTNode, "Incorrect index expression");
                if (asFirStatement instanceof FirExpression) {
                    firExpressionBuildErrorExpression = (FirExpression) asFirStatement;
                    if (UtilsKt.isStatementLikeExpression(firExpressionBuildErrorExpression)) {
                        KtSourceElement source2 = firExpressionBuildErrorExpression.getSource();
                        if (source2 == null || (firSourceElement$default2 = KtSourceElementKt.realElement(source2)) == null) {
                            firSourceElement$default2 = AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode, null, 1, null);
                        }
                        firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default2, new ConeSimpleDiagnostic("Incorrect index expression", DiagnosticKind.ExpressionExpected), asFirStatement);
                    }
                } else {
                    if (asFirStatement == null || (source = asFirStatement.getSource()) == null || (firSourceElement$default = KtSourceElementKt.realElement(source)) == null) {
                        firSourceElement$default = AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode, null, 1, null);
                    }
                    firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default, new ConeSimpleDiagnostic("Incorrect index expression", DiagnosticKind.ExpressionExpected), asFirStatement);
                }
                if (firExpressionBuildErrorExpression == null) {
                    x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.expressions.FirExpression");
                    return null;
                }
                arrayList.add(firExpressionBuildErrorExpression);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
    private final FirStatement convertLabeledExpression(LighterASTNode labeledExpression) throws Exception {
        LighterASTNode lighterASTNode;
        LighterASTNode labeledExpression2 = getLabeledExpression(labeledExpression);
        boolean zAreEqual = Intrinsics.areEqual(labeledExpression2 != null ? labeledExpression2.getTokenType() : null, KtNodeTypes.LABELED_EXPRESSION);
        LighterASTNode[] childrenAsArray = getChildrenAsArray(labeledExpression);
        int length = childrenAsArray.length;
        FirElement asFirStatement$default = null;
        KtSourceElement ktSourceElement = null;
        AbstractRawFirBuilder.ForbiddenLabelKind forbiddenLabelKind = null;
        for (int i = 0; i < length && (lighterASTNode = childrenAsArray[i]) != null; i++) {
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType())) {
                getContext().setNewLabelUserNode(lighterASTNode);
                IElementType tokenType = lighterASTNode.getTokenType();
                if (Intrinsics.areEqual(tokenType, KtNodeTypes.LABEL_QUALIFIER)) {
                    String strDropLast = StringsKt.dropLast(getAsText(lighterASTNode), 1);
                    IElementType iElementType = KtNodeTypes.LABEL;
                    iElementType.getClass();
                    KtSourceElement firSourceElement$default = AbstractRawFirBuilder.toFirSourceElement$default(this, CollectionsKt.single(getChildNodesByType(lighterASTNode, iElementType)), null, 1, null);
                    getContext().addNewLabel(buildLabel(strDropLast, firSourceElement$default));
                    forbiddenLabelKind = getForbiddenLabelKind(strDropLast, zAreEqual);
                    ktSourceElement = firSourceElement$default;
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.BLOCK)) {
                    asFirStatement$default = LightTreeRawFirDeclarationBuilder.convertBlock$default(this.declarationBuilder, lighterASTNode, false, 2, null);
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.PROPERTY)) {
                    asFirStatement$default = LightTreeRawFirDeclarationBuilder.convertPropertyDeclaration$default(this.declarationBuilder, lighterASTNode, null, 2, null);
                } else if (ElementTypeUtils.INSTANCE.isExpression(lighterASTNode)) {
                    asFirStatement$default = getAsFirStatement$default(this, lighterASTNode, null, 2, null);
                }
            }
        }
        getContext().dropLastLabel();
        FirElement firElementBuildExpressionHandlingLabelErrors = buildExpressionHandlingLabelErrors(asFirStatement$default, AbstractRawFirBuilder.toFirSourceElement$default(this, labeledExpression, null, 1, null), forbiddenLabelKind, ktSourceElement);
        firElementBuildExpressionHandlingLabelErrors.getClass();
        return (FirStatement) firElementBuildExpressionHandlingLabelErrors;
    }

    private final FirAnonymousFunctionExpression convertLambdaExpression(LighterASTNode lambdaExpression) throws Exception {
        boolean z;
        FirBlock firSingleExpressionBlock;
        boolean z2;
        List<FirValueParameter> list;
        FirValueParameter firValueParameter;
        LighterASTNode lighterASTNode;
        FirAnonymousFunctionSymbol firAnonymousFunctionSymbol;
        ArrayList<ValueParameter> arrayList = new ArrayList();
        FirAnonymousFunctionSymbol firAnonymousFunctionSymbol2 = new FirAnonymousFunctionSymbol();
        IElementType iElementType = KtNodeTypes.FUNCTION_LITERAL;
        iElementType.getClass();
        LighterASTNode[] childrenAsArray = getChildrenAsArray((LighterASTNode) CollectionsKt.first(getChildNodesByType(lambdaExpression, iElementType)));
        int length = childrenAsArray.length;
        FirExpression firExpression = null;
        boolean z3 = false;
        LighterASTNode lighterASTNode2 = null;
        int i = 0;
        boolean z4 = false;
        while (true) {
            z = true;
            if (i >= length || (lighterASTNode = childrenAsArray[i]) == null) {
                break;
            }
            if (AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType())) {
                firAnonymousFunctionSymbol = firAnonymousFunctionSymbol2;
            } else {
                IElementType tokenType = lighterASTNode.getTokenType();
                if (Intrinsics.areEqual(tokenType, KtNodeTypes.VALUE_PARAMETER_LIST)) {
                    List listConvertValueParameters$default = LightTreeRawFirDeclarationBuilder.convertValueParameters$default(this.declarationBuilder, lighterASTNode, firAnonymousFunctionSymbol2, AbstractRawFirBuilder.ValueParameterDeclaration.LAMBDA, null, 8, null);
                    firAnonymousFunctionSymbol = firAnonymousFunctionSymbol2;
                    CollectionsKt.addAll(arrayList, listConvertValueParameters$default);
                } else {
                    firAnonymousFunctionSymbol = firAnonymousFunctionSymbol2;
                    if (Intrinsics.areEqual(tokenType, KtNodeTypes.BLOCK)) {
                        lighterASTNode2 = lighterASTNode;
                    } else if (Intrinsics.areEqual(tokenType, KtTokens.ARROW)) {
                        z4 = true;
                    }
                }
            }
            i++;
            firAnonymousFunctionSymbol2 = firAnonymousFunctionSymbol;
        }
        FirAnonymousFunctionSymbol firAnonymousFunctionSymbol3 = firAnonymousFunctionSymbol2;
        KtSourceElement ktSourceElement = (KtLightSourceElement) AbstractRawFirBuilder.toFirSourceElement$default(this, lambdaExpression, null, 1, null);
        FirAnonymousFunctionBuilder firAnonymousFunctionBuilder = new FirAnonymousFunctionBuilder();
        firAnonymousFunctionBuilder.setSource(ktSourceElement);
        firAnonymousFunctionBuilder.setModuleData(getBaseModuleData());
        firAnonymousFunctionBuilder.setOrigin(FirDeclarationOrigin.Source.INSTANCE);
        firAnonymousFunctionBuilder.setReturnTypeRef(getImplicitType());
        firAnonymousFunctionBuilder.setReceiverParameter(ConversionUtilsKt.asReceiverParameter(ktSourceElement, firAnonymousFunctionBuilder.getModuleData(), firAnonymousFunctionSymbol3));
        firAnonymousFunctionBuilder.setSymbol(firAnonymousFunctionSymbol3);
        firAnonymousFunctionBuilder.setLambda(true);
        firAnonymousFunctionBuilder.setHasExplicitParameterList(z4);
        FirLabel lastLabel = getContext().getLastLabel(lambdaExpression);
        if (lastLabel == null) {
            Name name = (Name) CollectionsKt.lastOrNull(getContext().getCalleeNamesForLambda());
            if (name != null) {
                FirLabelBuilder firLabelBuilder = new FirLabelBuilder();
                firLabelBuilder.setSource(KtSourceElementKt.fakeElement$default(ktSourceElement, KtFakeSourceElementKind.GeneratedLambdaLabel.INSTANCE, null, 2, null));
                String strAsString = name.asString();
                strAsString.getClass();
                firLabelBuilder.setName(strAsString);
                lastLabel = firLabelBuilder.build();
            } else {
                lastLabel = null;
            }
        }
        firAnonymousFunctionBuilder.setLabel(lastLabel);
        FirLabel label = firAnonymousFunctionBuilder.getLabel();
        FirFunctionTarget firFunctionTarget = new FirFunctionTarget(label != null ? label.getName() : null, true);
        getContext().getFirFunctionTargets().add(firFunctionTarget);
        ArrayList arrayList2 = new ArrayList();
        for (ValueParameter valueParameter : arrayList) {
            DestructuringDeclaration destructuringDeclaration = valueParameter.getDestructuringDeclaration();
            List<FirValueParameter> valueParameters = firAnonymousFunctionBuilder.getValueParameters();
            if (destructuringDeclaration != null) {
                Name name2 = SpecialNames.DESTRUCT;
                FirValueParameterBuilder firValueParameterBuilder = new FirValueParameterBuilder();
                firValueParameterBuilder.setSource(valueParameter.getFirValueParameter().getSource());
                firValueParameterBuilder.setContainingDeclarationSymbol(firAnonymousFunctionSymbol3);
                firValueParameterBuilder.setModuleData(getBaseModuleData());
                firValueParameterBuilder.setOrigin(FirDeclarationOrigin.Source.INSTANCE);
                firValueParameterBuilder.setReturnTypeRef(valueParameter.getFirValueParameter().getReturnTypeRef());
                firValueParameterBuilder.setName(name2);
                firValueParameterBuilder.setSymbol(new FirValueParameterSymbol());
                firValueParameterBuilder.setDefaultValue(firExpression);
                firValueParameterBuilder.setCrossinline(z3);
                firValueParameterBuilder.setNoinline(z3);
                firValueParameterBuilder.setVararg(z3);
                firValueParameter = firValueParameterBuilder.mo288build();
                list = valueParameters;
                z2 = z;
                DestructuringDeclarationKt.addDestructuringStatements(this, arrayList2, getBaseModuleData(), destructuringDeclaration, firValueParameter, false, true, (64 & 64) != 0 ? new Function1() { // from class: ap3
                    public final Object invoke(Object obj) {
                        return DestructuringDeclarationKt.a((FirVariable) obj);
                    }
                } : null);
            } else {
                z2 = z;
                list = valueParameters;
                firValueParameter = valueParameter.getFirValueParameter();
            }
            list.add(firValueParameter);
            z = z2;
            firAnonymousFunctionSymbol3 = firAnonymousFunctionSymbol3;
            firExpression = null;
            z3 = false;
        }
        boolean z5 = z;
        boolean forceKeepingTheBodyInHeaderMode = getContext().getForceKeepingTheBodyInHeaderMode();
        getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
        boolean inLocalContext = getContext().getInLocalContext();
        getContext().setInLocalContext(z5);
        FqName classNameBeforeLocalContext = getContext().getClassNameBeforeLocalContext();
        if (!inLocalContext) {
            getContext().setClassNameBeforeLocalContext(getContext().getClassName());
        }
        FqName className = getContext().getClassName();
        getContext().setClassName(FqName.ROOT);
        try {
            if (lighterASTNode2 != null) {
                FirBlockBuilder firBlockBuilderConvertBlockExpressionWithoutBuilding$default = LightTreeRawFirDeclarationBuilder.convertBlockExpressionWithoutBuilding$default(this.declarationBuilder, lighterASTNode2, !arrayList2.isEmpty() ? KtFakeSourceElementKind.LambdaDestructuringBlock.INSTANCE : null, false, 4, null);
                if (firBlockBuilderConvertBlockExpressionWithoutBuilding$default.getStatements().isEmpty()) {
                    List<FirStatement> statements = firBlockBuilderConvertBlockExpressionWithoutBuilding$default.getStatements();
                    FirReturnExpressionBuilder firReturnExpressionBuilder = new FirReturnExpressionBuilder();
                    firReturnExpressionBuilder.setSource(KtSourceElementKt.fakeElement$default(ktSourceElement, KtFakeSourceElementKind.ImplicitReturn.FromExpressionBody.INSTANCE, null, 2, null));
                    firReturnExpressionBuilder.setTarget(firFunctionTarget);
                    FirUnitExpressionBuilder firUnitExpressionBuilder = new FirUnitExpressionBuilder();
                    firUnitExpressionBuilder.setSource(KtSourceElementKt.fakeElement$default(ktSourceElement, KtFakeSourceElementKind.ImplicitUnit.ForEmptyLambda.INSTANCE, null, 2, null));
                    firReturnExpressionBuilder.setResult(firUnitExpressionBuilder.mo288build());
                    statements.add(firReturnExpressionBuilder.mo288build());
                }
                firSingleExpressionBlock = firBlockBuilderConvertBlockExpressionWithoutBuilding$default.mo288build();
                if (!arrayList2.isEmpty()) {
                    FirBlockBuilder firBlockBuilder = new FirBlockBuilder();
                    KtSourceElement source = firSingleExpressionBlock.getSource();
                    firBlockBuilder.setSource(source != null ? KtSourceElementKt.realElement(source) : null);
                    firBlockBuilder.getStatements().addAll(arrayList2);
                    firBlockBuilder.getStatements().add(firSingleExpressionBlock);
                    firSingleExpressionBlock = firBlockBuilder.mo288build();
                }
            } else {
                firSingleExpressionBlock = new FirSingleExpressionBlock(FirExpressionUtilKt.buildErrorExpression$default(ktSourceElement, new ConeSyntaxDiagnostic("Lambda has no body"), null, 4, null));
            }
            getContext().setClassNameBeforeLocalContext(classNameBeforeLocalContext);
            getContext().setInLocalContext(inLocalContext);
            getContext().setClassName(className);
            getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
            firAnonymousFunctionBuilder.setBody(firSingleExpressionBlock);
            removeLast(getContext().getFirFunctionTargets());
            FirAnonymousFunction firAnonymousFunctionMo288build = firAnonymousFunctionBuilder.mo288build();
            firFunctionTarget.bind(firAnonymousFunctionMo288build);
            FirAnonymousFunctionExpressionBuilder firAnonymousFunctionExpressionBuilder = new FirAnonymousFunctionExpressionBuilder();
            firAnonymousFunctionExpressionBuilder.setSource(ktSourceElement);
            firAnonymousFunctionExpressionBuilder.setAnonymousFunction(firAnonymousFunctionMo288build);
            return firAnonymousFunctionExpressionBuilder.mo288build();
        } catch (Throwable th) {
            getContext().setClassNameBeforeLocalContext(classNameBeforeLocalContext);
            getContext().setInLocalContext(inLocalContext);
            getContext().setClassName(className);
            getContext().setForceKeepingTheBodyInHeaderMode(forceKeepingTheBodyInHeaderMode);
            throw th;
        }
    }

    private final FirBlock convertLoopBody(LighterASTNode body) {
        FirBlock firBlockConvertLoopOrIfBody = convertLoopOrIfBody(body);
        return firBlockConvertLoopOrIfBody == null ? FirEmptyExpressionBlockBuilderKt.buildEmptyExpressionBlock() : firBlockConvertLoopOrIfBody;
    }

    private final FirLoopJump convertLoopJump(LighterASTNode jump) {
        boolean z = true;
        for (LighterASTNode lighterASTNode : getChildrenAsArray(jump)) {
            if (lighterASTNode == null) {
                break;
            }
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType()) && Intrinsics.areEqual(lighterASTNode.getTokenType(), KtTokens.CONTINUE_KEYWORD)) {
                z = false;
            }
        }
        FirLoopJumpBuilder firBreakExpressionBuilder = z ? new FirBreakExpressionBuilder() : new FirContinueExpressionBuilder();
        firBreakExpressionBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(this, jump, null, 1, null));
        return bindLabel(firBreakExpressionBuilder, jump).build();
    }

    private final FirBlock convertLoopOrIfBody(LighterASTNode body) {
        KtSourceElement firSourceElement$default;
        FirExpression firExpressionBuildErrorExpression;
        KtSourceElement source;
        KtSourceElement firSourceElement$default2;
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        FirStatement firStatement = null;
        if (body != null) {
            FirStatement asFirStatement$default = null;
            for (LighterASTNode lighterASTNode : getChildrenAsArray(body)) {
                if (lighterASTNode == null) {
                    break;
                }
                if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType())) {
                    IElementType tokenType = lighterASTNode.getTokenType();
                    IElementType iElementType = KtNodeTypes.BLOCK;
                    if (Intrinsics.areEqual(tokenType, iElementType)) {
                        objectRef.element = LightTreeRawFirDeclarationBuilder.convertBlockExpression$default(this.declarationBuilder, lighterASTNode, false, 2, null);
                    } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.ANNOTATED_EXPRESSION)) {
                        iElementType.getClass();
                        if (getChildNodeByType(lighterASTNode, iElementType) != null) {
                            FirStatement asFirStatement = getAsFirStatement(lighterASTNode, Argument.Delimiters.none);
                            if (asFirStatement instanceof FirBlock) {
                                firExpressionBuildErrorExpression = (FirExpression) asFirStatement;
                                if (UtilsKt.isStatementLikeExpression(firExpressionBuildErrorExpression)) {
                                    KtSourceElement source2 = firExpressionBuildErrorExpression.getSource();
                                    if (source2 == null || (firSourceElement$default2 = KtSourceElementKt.realElement(source2)) == null) {
                                        firSourceElement$default2 = AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode, null, 1, null);
                                    }
                                    firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default2, new ConeSimpleDiagnostic(Argument.Delimiters.none, DiagnosticKind.ExpressionExpected), asFirStatement);
                                }
                            } else {
                                if (asFirStatement == null || (source = asFirStatement.getSource()) == null || (firSourceElement$default = KtSourceElementKt.realElement(source)) == null) {
                                    firSourceElement$default = AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode, null, 1, null);
                                }
                                firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default, new ConeSimpleDiagnostic(Argument.Delimiters.none, DiagnosticKind.ExpressionExpected), asFirStatement);
                            }
                            if (firExpressionBuildErrorExpression == null) {
                                x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.expressions.FirBlock");
                                return null;
                            }
                            objectRef.element = (FirBlock) firExpressionBuildErrorExpression;
                        } else {
                            asFirStatement$default = getAsFirStatement$default(this, lighterASTNode, null, 2, null);
                        }
                    } else if (ElementTypeUtils.INSTANCE.isExpression(lighterASTNode)) {
                        asFirStatement$default = getAsFirStatement$default(this, lighterASTNode, null, 2, null);
                    }
                }
            }
            firStatement = asFirStatement$default;
        }
        return firStatement != null ? new FirSingleExpressionBlock(firStatement) : (FirBlock) objectRef.element;
    }

    /* JADX WARN: Code duplicated, block: B:59:0x00f3  */
    /* JADX WARN: Code duplicated, block: B:64:0x0105  */
    /* JADX WARN: Code duplicated, block: B:67:0x0110  */
    /* JADX WARN: Code duplicated, block: B:68:0x0113  */
    private final FirExpression convertQualifiedExpression(LighterASTNode dotQualifiedExpression) {
        LighterASTNode lighterASTNode;
        KtSourceElement firSourceElement$default;
        FirExpression firExpressionBuildErrorExpression;
        KtSourceElement source;
        KtLightSourceElement firSourceElement$default2;
        DiagnosticKind diagnosticKind;
        KtSourceElement firSourceElement$default3;
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        LighterASTNode[] childrenAsArray = getChildrenAsArray(dotQualifiedExpression);
        int length = childrenAsArray.length;
        int i = 0;
        boolean z = false;
        FirExpression firExpression = null;
        boolean z2 = false;
        while (i < length && (lighterASTNode = childrenAsArray[i]) != null) {
            if (AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType())) {
                childrenAsArray = childrenAsArray;
            } else {
                IElementType tokenType = lighterASTNode.getTokenType();
                if (Intrinsics.areEqual(tokenType, KtTokens.DOT)) {
                    childrenAsArray = childrenAsArray;
                    z2 = true;
                } else if (Intrinsics.areEqual(tokenType, KtTokens.SAFE_ACCESS)) {
                    childrenAsArray = childrenAsArray;
                    z = true;
                    z2 = true;
                } else {
                    boolean z3 = z2 && !Intrinsics.areEqual(tokenType, TokenType.ERROR_ELEMENT);
                    StringBuilder sb = new StringBuilder("Incorrect ");
                    sb.append(z3 ? "selector" : "receiver");
                    sb.append(" expression");
                    String string = sb.toString();
                    FirStatement asFirStatement = getAsFirStatement(lighterASTNode, string);
                    if (asFirStatement instanceof FirExpression) {
                        firExpressionBuildErrorExpression = (FirExpression) asFirStatement;
                        if (UtilsKt.isStatementLikeExpression(firExpressionBuildErrorExpression)) {
                            KtSourceElement source2 = firExpressionBuildErrorExpression.getSource();
                            if (source2 == null || (firSourceElement$default3 = KtSourceElementKt.realElement(source2)) == null) {
                                firSourceElement$default3 = AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode, null, 1, null);
                            }
                            firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default3, new ConeSimpleDiagnostic(string, DiagnosticKind.ExpressionExpected), asFirStatement);
                        }
                    } else {
                        if (asFirStatement == null || (source = asFirStatement.getSource()) == null || (firSourceElement$default = KtSourceElementKt.realElement(source)) == null) {
                            firSourceElement$default = AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode, null, 1, null);
                        }
                        firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default, new ConeSimpleDiagnostic(string, DiagnosticKind.ExpressionExpected), asFirStatement);
                    }
                    if (firExpressionBuildErrorExpression == null) {
                        x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.expressions.FirExpression");
                        return null;
                    }
                    if (z3) {
                        IElementType iElementType = KtNodeTypes.CALL_EXPRESSION;
                        LighterASTNode firstChildExpressionUnwrapped = Intrinsics.areEqual(tokenType, iElementType) ? getFirstChildExpressionUnwrapped(lighterASTNode) : null;
                        if (!(tokenType instanceof KtNameReferenceExpressionElementType)) {
                            if (Intrinsics.areEqual(tokenType, iElementType)) {
                                if (Intrinsics.areEqual(firstChildExpressionUnwrapped != null ? firstChildExpressionUnwrapped.getTokenType() : null, KtNodeTypes.LAMBDA_EXPRESSION)) {
                                    FirErrorExpressionBuilder firErrorExpressionBuilder = new FirErrorExpressionBuilder();
                                    if (firstChildExpressionUnwrapped != null) {
                                        firSourceElement$default2 = AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode, null, 1, null);
                                    } else {
                                        firSourceElement$default2 = AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode, null, 1, null);
                                    }
                                    firErrorExpressionBuilder.setSource(firSourceElement$default2);
                                    if (firstChildExpressionUnwrapped == null) {
                                        diagnosticKind = DiagnosticKind.IllegalSelector;
                                    } else {
                                        diagnosticKind = DiagnosticKind.NoReceiverAllowed;
                                    }
                                    firErrorExpressionBuilder.setDiagnostic(new ConeSimpleDiagnostic("The expression cannot be a selector (occur after a dot)", diagnosticKind));
                                    firErrorExpressionBuilder.setExpression(firExpressionBuildErrorExpression);
                                    firExpressionBuildErrorExpression = firErrorExpressionBuilder.mo288build();
                                }
                            } else {
                                FirErrorExpressionBuilder firErrorExpressionBuilder2 = new FirErrorExpressionBuilder();
                                if (firstChildExpressionUnwrapped != null || (firSourceElement$default2 = AbstractRawFirBuilder.toFirSourceElement$default(this, firstChildExpressionUnwrapped, null, 1, null)) == null) {
                                    firSourceElement$default2 = AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode, null, 1, null);
                                }
                                firErrorExpressionBuilder2.setSource(firSourceElement$default2);
                                if (firstChildExpressionUnwrapped == null) {
                                    diagnosticKind = DiagnosticKind.IllegalSelector;
                                } else {
                                    diagnosticKind = DiagnosticKind.NoReceiverAllowed;
                                }
                                firErrorExpressionBuilder2.setDiagnostic(new ConeSimpleDiagnostic("The expression cannot be a selector (occur after a dot)", diagnosticKind));
                                firErrorExpressionBuilder2.setExpression(firExpressionBuildErrorExpression);
                                firExpressionBuildErrorExpression = firErrorExpressionBuilder2.mo288build();
                            }
                        }
                        objectRef.element = firExpressionBuildErrorExpression;
                    } else {
                        firExpression = firExpressionBuildErrorExpression;
                    }
                }
            }
            i++;
            childrenAsArray = childrenAsArray;
        }
        Object obj = objectRef.element;
        FirExpression firExpression2 = (FirExpression) obj;
        if (firExpression2 instanceof FirQualifiedAccessExpression) {
            if (!z) {
                KtSourceElement firSourceElement$default4 = AbstractRawFirBuilder.toFirSourceElement$default(this, dotQualifiedExpression, null, 1, null);
                firExpression.getClass();
                return convertFirSelector((FirQualifiedAccessExpression) obj, firSourceElement$default4, firExpression);
            }
            ((FirQualifiedAccessExpression) obj).replaceSource(toFirSourceElement(dotQualifiedExpression, (KtFakeSourceElementKind) KtFakeSourceElementKind.DesugaredSafeCallExpression.INSTANCE));
            FirQualifiedAccessExpression firQualifiedAccessExpression = (FirQualifiedAccessExpression) objectRef.element;
            firExpression.getClass();
            return ConversionUtilsKt.createSafeCall(firQualifiedAccessExpression, firExpression, AbstractRawFirBuilder.toFirSourceElement$default(this, dotQualifiedExpression, null, 1, null));
        }
        if (!(firExpression2 instanceof FirErrorExpression) || firExpression == null) {
            FirErrorExpressionBuilder firErrorExpressionBuilder3 = new FirErrorExpressionBuilder();
            firErrorExpressionBuilder3.setSource(AbstractRawFirBuilder.toFirSourceElement$default(this, dotQualifiedExpression, null, 1, null));
            firErrorExpressionBuilder3.setDiagnostic(new ConeSyntaxDiagnostic("Qualified expression without selector"));
            firErrorExpressionBuilder3.setExpression(firExpression);
            return firErrorExpressionBuilder3.mo288build();
        }
        FirQualifiedErrorAccessExpressionBuilder firQualifiedErrorAccessExpressionBuilder = new FirQualifiedErrorAccessExpressionBuilder();
        firQualifiedErrorAccessExpressionBuilder.setReceiver(firExpression);
        firQualifiedErrorAccessExpressionBuilder.setSelector((FirErrorExpression) objectRef.element);
        firQualifiedErrorAccessExpressionBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(this, dotQualifiedExpression, null, 1, null));
        firQualifiedErrorAccessExpressionBuilder.setDiagnostic(new ConeSyntaxDiagnostic("Qualified expression with unexpected selector"));
        return firQualifiedErrorAccessExpressionBuilder.mo288build();
    }

    private final FirReturnExpression convertReturn(LighterASTNode returnExpression) {
        LighterASTNode lighterASTNode;
        KtSourceElement firSourceElement$default;
        FirExpression firExpressionBuildErrorExpression;
        KtSourceElement source;
        KtSourceElement firSourceElement$default2;
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        LighterASTNode[] childrenAsArray = getChildrenAsArray(returnExpression);
        int length = childrenAsArray.length;
        String strReplace$default = null;
        for (int i = 0; i < length && (lighterASTNode = childrenAsArray[i]) != null; i++) {
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType())) {
                if (Intrinsics.areEqual(lighterASTNode.getTokenType(), KtNodeTypes.LABEL_QUALIFIER)) {
                    strReplace$default = StringsKt.replace$default(ConverterUtilKt.getAsStringWithoutBacktick(lighterASTNode), PreprocessCommandLineArgumentsKt.ARGFILE_ARGUMENT, Argument.Delimiters.none, false, 4, (Object) null);
                } else if (ElementTypeUtils.INSTANCE.isExpression(lighterASTNode)) {
                    FirStatement asFirStatement = getAsFirStatement(lighterASTNode, "Incorrect return expression");
                    if (asFirStatement instanceof FirExpression) {
                        firExpressionBuildErrorExpression = (FirExpression) asFirStatement;
                        if (UtilsKt.isStatementLikeExpression(firExpressionBuildErrorExpression)) {
                            KtSourceElement source2 = firExpressionBuildErrorExpression.getSource();
                            if (source2 == null || (firSourceElement$default2 = KtSourceElementKt.realElement(source2)) == null) {
                                firSourceElement$default2 = AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode, null, 1, null);
                            }
                            firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default2, new ConeSimpleDiagnostic("Incorrect return expression", DiagnosticKind.ExpressionExpected), asFirStatement);
                        }
                    } else {
                        if (asFirStatement == null || (source = asFirStatement.getSource()) == null || (firSourceElement$default = KtSourceElementKt.realElement(source)) == null) {
                            firSourceElement$default = AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode, null, 1, null);
                        }
                        firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default, new ConeSimpleDiagnostic("Incorrect return expression", DiagnosticKind.ExpressionExpected), asFirStatement);
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
        }
        FirExpression firExpressionMo288build = (FirExpression) objectRef.element;
        if (firExpressionMo288build == null) {
            FirUnitExpressionBuilder firUnitExpressionBuilder = new FirUnitExpressionBuilder();
            firUnitExpressionBuilder.setSource(toFirSourceElement(returnExpression, (KtFakeSourceElementKind) KtFakeSourceElementKind.ImplicitUnit.Return.INSTANCE));
            firExpressionMo288build = firUnitExpressionBuilder.mo288build();
        }
        return toReturn(firExpressionMo288build, AbstractRawFirBuilder.toFirSourceElement$default(this, returnExpression, null, 1, null), strReplace$default, true);
    }

    private final Collection<FirExpression> convertShortOrLongStringTemplate(LighterASTNode lighterASTNode, String str) {
        KtSourceElement firSourceElement$default;
        FirExpression firExpressionBuildErrorExpression;
        KtSourceElement source;
        KtSourceElement firSourceElement$default2;
        ArrayList arrayList = new ArrayList();
        if (lighterASTNode != null) {
            for (LighterASTNode lighterASTNode2 : getChildrenAsArray(lighterASTNode)) {
                if (lighterASTNode2 == null) {
                    break;
                }
                if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode2.getTokenType())) {
                    IElementType tokenType = lighterASTNode2.getTokenType();
                    if (!Intrinsics.areEqual(tokenType, KtTokens.LONG_TEMPLATE_ENTRY_START) && !Intrinsics.areEqual(tokenType, KtTokens.LONG_TEMPLATE_ENTRY_END) && !Intrinsics.areEqual(tokenType, KtTokens.SHORT_TEMPLATE_ENTRY_START)) {
                        FirStatement asFirStatement = getAsFirStatement(lighterASTNode2, str);
                        if (asFirStatement instanceof FirExpression) {
                            firExpressionBuildErrorExpression = (FirExpression) asFirStatement;
                            if (UtilsKt.isStatementLikeExpression(firExpressionBuildErrorExpression)) {
                                KtSourceElement source2 = firExpressionBuildErrorExpression.getSource();
                                if (source2 == null || (firSourceElement$default2 = KtSourceElementKt.realElement(source2)) == null) {
                                    firSourceElement$default2 = AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode2, null, 1, null);
                                }
                                firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default2, new ConeSimpleDiagnostic(str, DiagnosticKind.ExpressionExpected), asFirStatement);
                            }
                        } else {
                            if (asFirStatement == null || (source = asFirStatement.getSource()) == null || (firSourceElement$default = KtSourceElementKt.realElement(source)) == null) {
                                firSourceElement$default = AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode2, null, 1, null);
                            }
                            firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default, new ConeSimpleDiagnostic(str, DiagnosticKind.ExpressionExpected), asFirStatement);
                        }
                        if (firExpressionBuildErrorExpression == null) {
                            x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.expressions.FirExpression");
                            return null;
                        }
                        arrayList.add(firExpressionBuildErrorExpression);
                    }
                }
            }
        }
        return arrayList;
    }

    private final FirQualifiedAccessExpression convertSimpleNameExpression(LighterASTNode referenceExpression) {
        KtSourceElement ktSourceElement = (KtLightSourceElement) AbstractRawFirBuilder.toFirSourceElement$default(this, referenceExpression, null, 1, null);
        KtSourceElement ktSourceElementFakeElement$default = ktSourceElement.getKind() instanceof KtFakeSourceElementKind ? ktSourceElement : KtSourceElementKt.fakeElement$default(ktSourceElement, KtFakeSourceElementKind.ReferenceInAtomicQualifiedAccess.INSTANCE, null, 2, null);
        FirPropertyAccessExpressionBuilder firPropertyAccessExpressionBuilder = new FirPropertyAccessExpressionBuilder();
        firPropertyAccessExpressionBuilder.setSource(ktSourceElement);
        firPropertyAccessExpressionBuilder.setCalleeReference(createSimpleNamedReference(ktSourceElementFakeElement$default, referenceExpression));
        return firPropertyAccessExpressionBuilder.mo288build();
    }

    private final FirExpression convertStringTemplate(LighterASTNode stringTemplate) {
        final LighterASTNode[] childrenAsArray = getChildrenAsArray(stringTemplate);
        return AbstractRawFirBuilder.toInterpolatingCall$default(this, childrenAsArray, stringTemplate, null, new Function2() { // from class: n19
            public final Object invoke(Object obj, Object obj2) {
                return LightTreeRawFirExpressionBuilder.o(this.b, (LighterASTNode) obj, (String) obj2);
            }
        }, new Function0() { // from class: o19
            public final Object invoke() {
                return LightTreeRawFirExpressionBuilder.p(childrenAsArray, this);
            }
        }, 2, null);
    }

    private final FirSuperReceiverExpression convertSuperExpression(LighterASTNode superExpression) throws Exception {
        String labelName = getLabelName(superExpression);
        FirTypeRef implicitType = getImplicitType();
        for (LighterASTNode lighterASTNode : getChildrenAsArray(superExpression)) {
            if (lighterASTNode == null) {
                break;
            }
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType()) && Intrinsics.areEqual(lighterASTNode.getTokenType(), KtNodeTypes.TYPE_REFERENCE)) {
                implicitType = this.declarationBuilder.convertType(lighterASTNode);
            }
        }
        FirSuperReceiverExpressionBuilder firSuperReceiverExpressionBuilder = new FirSuperReceiverExpressionBuilder();
        KtLightSourceElement firSourceElement$default = AbstractRawFirBuilder.toFirSourceElement$default(this, superExpression, null, 1, null);
        firSuperReceiverExpressionBuilder.setSource(firSourceElement$default);
        FirExplicitSuperReferenceBuilder firExplicitSuperReferenceBuilder = new FirExplicitSuperReferenceBuilder();
        firExplicitSuperReferenceBuilder.setLabelName(labelName);
        firExplicitSuperReferenceBuilder.setSuperTypeRef(implicitType);
        firExplicitSuperReferenceBuilder.setSource(KtSourceElementKt.fakeElement$default(firSourceElement$default, KtFakeSourceElementKind.ReferenceInAtomicQualifiedAccess.INSTANCE, null, 2, null));
        firSuperReceiverExpressionBuilder.setCalleeReference(firExplicitSuperReferenceBuilder.build());
        return firSuperReceiverExpressionBuilder.mo288build();
    }

    private final FirThisReceiverExpression convertThisExpression(LighterASTNode thisExpression) {
        String labelName = getLabelName(thisExpression);
        FirThisReceiverExpressionBuilder firThisReceiverExpressionBuilder = new FirThisReceiverExpressionBuilder();
        KtLightSourceElement firSourceElement$default = AbstractRawFirBuilder.toFirSourceElement$default(this, thisExpression, null, 1, null);
        firThisReceiverExpressionBuilder.setSource(firSourceElement$default);
        FirExplicitThisReferenceBuilder firExplicitThisReferenceBuilder = new FirExplicitThisReferenceBuilder();
        firExplicitThisReferenceBuilder.setLabelName(labelName);
        firExplicitThisReferenceBuilder.setSource(KtSourceElementKt.fakeElement$default(firSourceElement$default, KtFakeSourceElementKind.ReferenceInAtomicQualifiedAccess.INSTANCE, null, 2, null));
        firThisReceiverExpressionBuilder.setCalleeReference(firExplicitThisReferenceBuilder.build());
        return firThisReceiverExpressionBuilder.mo288build();
    }

    private final FirThrowExpression convertThrow(LighterASTNode throwExpression) {
        LighterASTNode lighterASTNode;
        KtSourceElement firSourceElement$default;
        FirExpression firExpressionBuildErrorExpression;
        KtSourceElement source;
        KtSourceElement firSourceElement$default2;
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        LighterASTNode[] childrenAsArray = getChildrenAsArray(throwExpression);
        int length = childrenAsArray.length;
        for (int i = 0; i < length && (lighterASTNode = childrenAsArray[i]) != null; i++) {
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType()) && ElementTypeUtils.INSTANCE.isExpression(lighterASTNode)) {
                FirStatement asFirStatement = getAsFirStatement(lighterASTNode, "Nothing to throw");
                if (asFirStatement instanceof FirExpression) {
                    firExpressionBuildErrorExpression = (FirExpression) asFirStatement;
                    if (UtilsKt.isStatementLikeExpression(firExpressionBuildErrorExpression)) {
                        KtSourceElement source2 = firExpressionBuildErrorExpression.getSource();
                        if (source2 == null || (firSourceElement$default2 = KtSourceElementKt.realElement(source2)) == null) {
                            firSourceElement$default2 = AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode, null, 1, null);
                        }
                        firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default2, new ConeSimpleDiagnostic("Nothing to throw", DiagnosticKind.ExpressionExpected), asFirStatement);
                    }
                } else {
                    if (asFirStatement == null || (source = asFirStatement.getSource()) == null || (firSourceElement$default = KtSourceElementKt.realElement(source)) == null) {
                        firSourceElement$default = AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode, null, 1, null);
                    }
                    firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default, new ConeSimpleDiagnostic("Nothing to throw", DiagnosticKind.ExpressionExpected), asFirStatement);
                }
                if (firExpressionBuildErrorExpression == null) {
                    x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.expressions.FirExpression");
                    return null;
                }
                objectRef.element = firExpressionBuildErrorExpression;
            }
        }
        FirThrowExpressionBuilder firThrowExpressionBuilder = new FirThrowExpressionBuilder();
        firThrowExpressionBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(this, throwExpression, null, 1, null));
        FirExpression firExpressionBuildErrorExpression$default = (FirExpression) objectRef.element;
        if (firExpressionBuildErrorExpression$default == null) {
            firExpressionBuildErrorExpression$default = FirExpressionUtilKt.buildErrorExpression$default(AbstractRawFirBuilder.toFirSourceElement$default(this, throwExpression, null, 1, null), new ConeSyntaxDiagnostic("Nothing to throw"), null, 4, null);
        }
        firThrowExpressionBuilder.setException(firExpressionBuildErrorExpression$default);
        return firThrowExpressionBuilder.mo288build();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    private final FirTryExpression convertTryExpression(LighterASTNode tryExpression) throws Exception {
        ArrayList<Triple> arrayList = new ArrayList();
        FirBlock firBlock = null;
        FirBlock firBlockConvertBlock$default = null;
        FirBlock firBlockConvertFinally = null;
        for (LighterASTNode lighterASTNode : getChildrenAsArray(tryExpression)) {
            if (lighterASTNode == null) {
                break;
            }
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType())) {
                IElementType tokenType = lighterASTNode.getTokenType();
                if (Intrinsics.areEqual(tokenType, KtNodeTypes.BLOCK)) {
                    firBlockConvertBlock$default = LightTreeRawFirDeclarationBuilder.convertBlock$default(this.declarationBuilder, lighterASTNode, false, 2, null);
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.CATCH)) {
                    Triple<ValueParameter, FirBlock, KtLightSourceElement> tripleConvertCatchClause = convertCatchClause(lighterASTNode);
                    if (tripleConvertCatchClause != null) {
                        arrayList.add(tripleConvertCatchClause);
                    }
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.FINALLY)) {
                    firBlockConvertFinally = convertFinally(lighterASTNode);
                }
            }
        }
        FirTryExpressionBuilder firTryExpressionBuilder = new FirTryExpressionBuilder();
        firTryExpressionBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(this, tryExpression, null, 1, null));
        if (firBlockConvertBlock$default == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tryBlock");
        } else {
            firBlock = firBlockConvertBlock$default;
        }
        firTryExpressionBuilder.setTryBlock(firBlock);
        firTryExpressionBuilder.setFinallyBlock(firBlockConvertFinally);
        for (Triple triple : arrayList) {
            ValueParameter valueParameter = (ValueParameter) triple.component1();
            FirBlock firBlock2 = (FirBlock) triple.component2();
            KtSourceElement ktSourceElement = (KtLightSourceElement) triple.component3();
            if (valueParameter != null) {
                List<FirCatch> catches = firTryExpressionBuilder.getCatches();
                FirCatchBuilder firCatchBuilder = new FirCatchBuilder();
                FirPropertyBuilder firPropertyBuilder = new FirPropertyBuilder();
                firPropertyBuilder.setSource(valueParameter.getSource());
                firPropertyBuilder.setModuleData(getBaseModuleData());
                firPropertyBuilder.setOrigin(FirDeclarationOrigin.Source.INSTANCE);
                firPropertyBuilder.setReturnTypeRef(valueParameter.getReturnTypeRef());
                firPropertyBuilder.setVar(false);
                firPropertyBuilder.setStatus(new FirResolvedDeclarationStatusImpl(Visibilities.Local.INSTANCE, Modality.FINAL, EffectiveVisibility.Local.INSTANCE));
                firPropertyBuilder.setLocal(true);
                firPropertyBuilder.setName(valueParameter.getName());
                firPropertyBuilder.setSymbol(new FirLocalPropertySymbol());
                CollectionsKt.addAll(firPropertyBuilder.getAnnotations(), valueParameter.getAnnotations());
                FirProperty firPropertyMo288build = firPropertyBuilder.mo288build();
                ClassMembersKt.setCatchParameter(firPropertyMo288build, Boolean.TRUE);
                firCatchBuilder.setParameter(firPropertyMo288build);
                firCatchBuilder.setBlock(firBlock2);
                firCatchBuilder.setSource(ktSourceElement);
                catches.add(firCatchBuilder.build());
            }
        }
        return firTryExpressionBuilder.mo288build();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    private final FirExpression convertUnaryExpression(LighterASTNode unaryExpression) throws UninitializedPropertyAccessException {
        LighterASTNode lighterASTNode;
        KtSourceElement firSourceElement$default;
        FirExpression firExpressionBuildErrorExpression;
        KtSourceElement source;
        KtSourceElement firSourceElement$default2;
        KtSourceElement firSourceElement$default3;
        FirExpression firExpressionBuildErrorExpression2;
        KtSourceElement source2;
        KtSourceElement firSourceElement$default4;
        LighterASTNode lighterASTNode2 = null;
        LighterASTNode lighterASTNode3 = null;
        for (LighterASTNode lighterASTNode4 : getChildrenAsArray(unaryExpression)) {
            if (lighterASTNode4 == null) {
                break;
            }
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode4.getTokenType())) {
                if (Intrinsics.areEqual(lighterASTNode4.getTokenType(), KtNodeTypes.OPERATION_REFERENCE)) {
                    lighterASTNode2 = lighterASTNode4;
                } else if (ElementTypeUtils.INSTANCE.isExpression(lighterASTNode4)) {
                    lighterASTNode3 = lighterASTNode4;
                }
            }
        }
        ElementTypeUtils elementTypeUtils = ElementTypeUtils.INSTANCE;
        if (lighterASTNode2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("operationReference");
            lighterASTNode = null;
        } else {
            lighterASTNode = lighterASTNode2;
        }
        KtToken operationSymbol = elementTypeUtils.getOperationSymbol(lighterASTNode, getTree());
        Name unaryName = ConversionUtilsKt.toUnaryName(operationSymbol);
        if (Intrinsics.areEqual(operationSymbol, KtTokens.EXCLEXCL)) {
            FirCheckNotNullCallBuilder firCheckNotNullCallBuilder = new FirCheckNotNullCallBuilder();
            firCheckNotNullCallBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(this, unaryExpression, null, 1, null));
            FirStatement asFirStatement = lighterASTNode3 != null ? getAsFirStatement(lighterASTNode3, "No operand") : null;
            if (asFirStatement instanceof FirExpression) {
                firExpressionBuildErrorExpression2 = (FirExpression) asFirStatement;
                if (UtilsKt.isStatementLikeExpression(firExpressionBuildErrorExpression2)) {
                    KtSourceElement source3 = firExpressionBuildErrorExpression2.getSource();
                    if (source3 == null || (firSourceElement$default4 = KtSourceElementKt.realElement(source3)) == null) {
                        firSourceElement$default4 = lighterASTNode3 != null ? AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode3, null, 1, null) : toFirSourceElement(unaryExpression, (KtFakeSourceElementKind) KtFakeSourceElementKind.ErrorExpression.INSTANCE);
                    }
                    firExpressionBuildErrorExpression2 = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default4, new ConeSimpleDiagnostic("No operand", DiagnosticKind.ExpressionExpected), asFirStatement);
                }
            } else {
                if (asFirStatement == null || (source2 = asFirStatement.getSource()) == null || (firSourceElement$default3 = KtSourceElementKt.realElement(source2)) == null) {
                    firSourceElement$default3 = lighterASTNode3 != null ? AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode3, null, 1, null) : toFirSourceElement(unaryExpression, (KtFakeSourceElementKind) KtFakeSourceElementKind.ErrorExpression.INSTANCE);
                }
                firExpressionBuildErrorExpression2 = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default3, lighterASTNode3 == null ? new ConeSyntaxDiagnostic("No operand") : new ConeSimpleDiagnostic("No operand", DiagnosticKind.ExpressionExpected), asFirStatement);
            }
            if (firExpressionBuildErrorExpression2 != null) {
                firCheckNotNullCallBuilder.setArgumentList(FirArgumentUtilKt.buildUnaryArgumentList(firExpressionBuildErrorExpression2));
                return firCheckNotNullCallBuilder.mo288build();
            }
            x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.expressions.FirExpression");
            return null;
        }
        if (unaryName == null) {
            sle.a("Unexpected expression: ", getAsText(unaryExpression));
            return null;
        }
        ImmutableSet immutableSet = OperatorConventions.INCREMENT_OPERATIONS;
        immutableSet.getClass();
        if (CollectionsKt.contains(immutableSet, operationSymbol)) {
            return generateIncrementOrDecrementBlock(unaryExpression, lighterASTNode2, lighterASTNode3, unaryName, Intrinsics.areEqual(unaryExpression.getTokenType(), KtNodeTypes.PREFIX_EXPRESSION), new Function1() { // from class: r19
                public final Object invoke(Object obj) {
                    return LightTreeRawFirExpressionBuilder.r(this.b, (LighterASTNode) obj);
                }
            });
        }
        FirStatement asFirStatement2 = lighterASTNode3 != null ? getAsFirStatement(lighterASTNode3, "No operand") : null;
        if (asFirStatement2 instanceof FirExpression) {
            firExpressionBuildErrorExpression = (FirExpression) asFirStatement2;
            if (UtilsKt.isStatementLikeExpression(firExpressionBuildErrorExpression)) {
                KtSourceElement source4 = firExpressionBuildErrorExpression.getSource();
                if (source4 == null || (firSourceElement$default2 = KtSourceElementKt.realElement(source4)) == null) {
                    firSourceElement$default2 = lighterASTNode3 != null ? AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode3, null, 1, null) : toFirSourceElement(unaryExpression, (KtFakeSourceElementKind) KtFakeSourceElementKind.ErrorExpression.INSTANCE);
                }
                firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default2, new ConeSimpleDiagnostic("No operand", DiagnosticKind.ExpressionExpected), asFirStatement2);
            }
        } else {
            if (asFirStatement2 == null || (source = asFirStatement2.getSource()) == null || (firSourceElement$default = KtSourceElementKt.realElement(source)) == null) {
                firSourceElement$default = lighterASTNode3 != null ? AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode3, null, 1, null) : toFirSourceElement(unaryExpression, (KtFakeSourceElementKind) KtFakeSourceElementKind.ErrorExpression.INSTANCE);
            }
            firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default, lighterASTNode3 == null ? new ConeSyntaxDiagnostic("No operand") : new ConeSimpleDiagnostic("No operand", DiagnosticKind.ExpressionExpected), asFirStatement2);
        }
        if (firExpressionBuildErrorExpression == null) {
            x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.expressions.FirExpression");
            return null;
        }
        FirExpression firExpressionConvertUnaryPlusMinusCallOnIntegerLiteralIfNecessary = convertUnaryPlusMinusCallOnIntegerLiteralIfNecessary(unaryExpression, firExpressionBuildErrorExpression, operationSymbol);
        if (firExpressionConvertUnaryPlusMinusCallOnIntegerLiteralIfNecessary != null) {
            return firExpressionConvertUnaryPlusMinusCallOnIntegerLiteralIfNecessary;
        }
        FirFunctionCallBuilder firFunctionCallBuilder = new FirFunctionCallBuilder();
        firFunctionCallBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(this, unaryExpression, null, 1, null));
        FirSimpleNamedReferenceBuilder firSimpleNamedReferenceBuilder = new FirSimpleNamedReferenceBuilder();
        firSimpleNamedReferenceBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode2, null, 1, null));
        firSimpleNamedReferenceBuilder.setName(unaryName);
        firFunctionCallBuilder.setCalleeReference(firSimpleNamedReferenceBuilder.build());
        firFunctionCallBuilder.setExplicitReceiver(firExpressionBuildErrorExpression);
        firFunctionCallBuilder.setOrigin(FirFunctionCallOrigin.Operator);
        return firFunctionCallBuilder.mo288build();
    }

    private final FirExpression convertValueArgument(LighterASTNode valueArgument) {
        LighterASTNode lighterASTNode;
        KtSourceElement firSourceElement$default;
        FirExpression firExpressionBuildErrorExpression;
        KtSourceElement source;
        KtSourceElement firSourceElement$default2;
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        LighterASTNode[] childrenAsArray = getChildrenAsArray(valueArgument);
        int length = childrenAsArray.length;
        String asText = null;
        boolean z = false;
        for (int i = 0; i < length && (lighterASTNode = childrenAsArray[i]) != null; i++) {
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType())) {
                IElementType tokenType = lighterASTNode.getTokenType();
                if (Intrinsics.areEqual(tokenType, KtNodeTypes.VALUE_ARGUMENT_NAME)) {
                    asText = getAsText(lighterASTNode);
                } else if (Intrinsics.areEqual(tokenType, KtTokens.MUL)) {
                    z = true;
                } else if (ElementTypeUtils.INSTANCE.isExpression(lighterASTNode)) {
                    FirStatement asFirStatement = getAsFirStatement(lighterASTNode, "Argument is absent");
                    if (asFirStatement instanceof FirExpression) {
                        firExpressionBuildErrorExpression = (FirExpression) asFirStatement;
                        if (UtilsKt.isStatementLikeExpression(firExpressionBuildErrorExpression)) {
                            KtSourceElement source2 = firExpressionBuildErrorExpression.getSource();
                            if (source2 == null || (firSourceElement$default2 = KtSourceElementKt.realElement(source2)) == null) {
                                firSourceElement$default2 = AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode, null, 1, null);
                            }
                            firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default2, new ConeSimpleDiagnostic("Argument is absent", DiagnosticKind.ExpressionExpected), asFirStatement);
                        }
                    } else {
                        if (asFirStatement == null || (source = asFirStatement.getSource()) == null || (firSourceElement$default = KtSourceElementKt.realElement(source)) == null) {
                            firSourceElement$default = AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode, null, 1, null);
                        }
                        firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default, new ConeSimpleDiagnostic("Argument is absent", DiagnosticKind.ExpressionExpected), asFirStatement);
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
        }
        FirExpression firExpressionBuildErrorExpression$default = (FirExpression) objectRef.element;
        if (firExpressionBuildErrorExpression$default == null) {
            firExpressionBuildErrorExpression$default = FirExpressionUtilKt.buildErrorExpression$default(AbstractRawFirBuilder.toFirSourceElement$default(this, valueArgument, null, 1, null), new ConeSyntaxDiagnostic("Argument is absent"), null, 4, null);
        }
        if (asText != null) {
            FirNamedArgumentExpressionBuilder firNamedArgumentExpressionBuilder = new FirNamedArgumentExpressionBuilder();
            firNamedArgumentExpressionBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(this, valueArgument, null, 1, null));
            firNamedArgumentExpressionBuilder.setExpression(firExpressionBuildErrorExpression$default);
            firNamedArgumentExpressionBuilder.setSpread(z);
            firNamedArgumentExpressionBuilder.setName(ConverterUtilKt.nameAsSafeName$default(asText, null, 1, null));
            return firNamedArgumentExpressionBuilder.mo288build();
        }
        if (!z) {
            return firExpressionBuildErrorExpression$default;
        }
        FirSpreadArgumentExpressionBuilder firSpreadArgumentExpressionBuilder = new FirSpreadArgumentExpressionBuilder();
        firSpreadArgumentExpressionBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(this, valueArgument, null, 1, null));
        firSpreadArgumentExpressionBuilder.setExpression(firExpressionBuildErrorExpression$default);
        return firSpreadArgumentExpressionBuilder.mo288build();
    }

    private final FirExpression convertWhenConditionExpression(LighterASTNode whenCondition, FirVariable subjectVariable) {
        LighterASTNode lighterASTNode;
        KtSourceElement firSourceElement$default;
        FirExpression firExpressionBuildErrorExpression;
        KtSourceElement source;
        KtSourceElement firSourceElement$default2;
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        LighterASTNode[] childrenAsArray = getChildrenAsArray(whenCondition);
        int length = childrenAsArray.length;
        for (int i = 0; i < length && (lighterASTNode = childrenAsArray[i]) != null; i++) {
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType())) {
                lighterASTNode.getTokenType();
                if (ElementTypeUtils.INSTANCE.isExpression(lighterASTNode)) {
                    FirStatement asFirStatement = getAsFirStatement(lighterASTNode, "No expression in condition with expression");
                    if (asFirStatement instanceof FirExpression) {
                        firExpressionBuildErrorExpression = (FirExpression) asFirStatement;
                        if (UtilsKt.isStatementLikeExpression(firExpressionBuildErrorExpression)) {
                            KtSourceElement source2 = firExpressionBuildErrorExpression.getSource();
                            if (source2 == null || (firSourceElement$default2 = KtSourceElementKt.realElement(source2)) == null) {
                                firSourceElement$default2 = AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode, null, 1, null);
                            }
                            firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default2, new ConeSimpleDiagnostic("No expression in condition with expression", DiagnosticKind.ExpressionExpected), asFirStatement);
                        }
                    } else {
                        if (asFirStatement == null || (source = asFirStatement.getSource()) == null || (firSourceElement$default = KtSourceElementKt.realElement(source)) == null) {
                            firSourceElement$default = AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode, null, 1, null);
                        }
                        firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default, new ConeSimpleDiagnostic("No expression in condition with expression", DiagnosticKind.ExpressionExpected), asFirStatement);
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
        }
        FirExpression firExpressionBuildErrorExpression$default = (FirExpression) objectRef.element;
        if (firExpressionBuildErrorExpression$default == null) {
            firExpressionBuildErrorExpression$default = FirExpressionUtilKt.buildErrorExpression$default(AbstractRawFirBuilder.toFirSourceElement$default(this, whenCondition, null, 1, null), new ConeSyntaxDiagnostic("No expression in condition with expression"), null, 4, null);
        }
        if (subjectVariable == null) {
            return firExpressionBuildErrorExpression$default;
        }
        KtSourceElement firSourceElement = toFirSourceElement(whenCondition, (KtFakeSourceElementKind) KtFakeSourceElementKind.WhenCondition.INSTANCE);
        FirEqualityOperatorCallBuilder firEqualityOperatorCallBuilder = new FirEqualityOperatorCallBuilder();
        firEqualityOperatorCallBuilder.setSource(firSourceElement);
        firEqualityOperatorCallBuilder.setOperation(FirOperation.EQ);
        firEqualityOperatorCallBuilder.setArgumentList(FirArgumentUtilKt.buildBinaryArgumentList(UtilsKt.buildWhenSubjectAccess(AbstractRawFirBuilder.toFirSourceElement$default(this, whenCondition, null, 1, null), subjectVariable), firExpressionBuildErrorExpression$default));
        return firEqualityOperatorCallBuilder.mo288build();
    }

    private final WhenConditionConvertedResults convertWhenConditionInRange(LighterASTNode whenCondition, FirVariable subjectVariable) {
        LighterASTNode lighterASTNode;
        KtSourceElement firSourceElement$default;
        FirExpression firExpressionBuildErrorExpression;
        KtSourceElement source;
        KtSourceElement firSourceElement$default2;
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        LighterASTNode[] childrenAsArray = getChildrenAsArray(whenCondition);
        int length = childrenAsArray.length;
        boolean z = false;
        KtSourceElement firSourceElement$default3 = null;
        for (int i = 0; i < length && (lighterASTNode = childrenAsArray[i]) != null; i++) {
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType())) {
                IElementType tokenType = lighterASTNode.getTokenType();
                IElementType iElementType = KtNodeTypes.OPERATION_REFERENCE;
                if (Intrinsics.areEqual(tokenType, iElementType) && Intrinsics.areEqual(getAsText(lighterASTNode), KtTokens.NOT_IN.getValue())) {
                    firSourceElement$default3 = AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode, null, 1, null);
                    z = true;
                } else if (Intrinsics.areEqual(tokenType, iElementType)) {
                    firSourceElement$default3 = AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode, null, 1, null);
                } else if (ElementTypeUtils.INSTANCE.isExpression(lighterASTNode)) {
                    FirStatement asFirStatement = getAsFirStatement(lighterASTNode, "No range in condition with range");
                    if (asFirStatement instanceof FirExpression) {
                        firExpressionBuildErrorExpression = (FirExpression) asFirStatement;
                        if (UtilsKt.isStatementLikeExpression(firExpressionBuildErrorExpression)) {
                            KtSourceElement source2 = firExpressionBuildErrorExpression.getSource();
                            if (source2 == null || (firSourceElement$default2 = KtSourceElementKt.realElement(source2)) == null) {
                                firSourceElement$default2 = AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode, null, 1, null);
                            }
                            firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default2, new ConeSimpleDiagnostic("No range in condition with range", DiagnosticKind.ExpressionExpected), asFirStatement);
                        }
                    } else {
                        if (asFirStatement == null || (source = asFirStatement.getSource()) == null || (firSourceElement$default = KtSourceElementKt.realElement(source)) == null) {
                            firSourceElement$default = AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode, null, 1, null);
                        }
                        firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default, new ConeSimpleDiagnostic("No range in condition with range", DiagnosticKind.ExpressionExpected), asFirStatement);
                    }
                    if (firExpressionBuildErrorExpression == null) {
                        x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.expressions.FirExpression");
                        return null;
                    }
                    objectRef.element = firExpressionBuildErrorExpression;
                }
            }
        }
        FirPropertyAccessExpression firPropertyAccessExpressionBuildWhenSubjectAccess = UtilsKt.buildWhenSubjectAccess(AbstractRawFirBuilder.toFirSourceElement$default(this, whenCondition, null, 1, null), subjectVariable);
        FirExpression firExpressionBuildErrorExpression$default = (FirExpression) objectRef.element;
        if (firExpressionBuildErrorExpression$default == null) {
            firExpressionBuildErrorExpression$default = FirExpressionUtilKt.buildErrorExpression$default(AbstractRawFirBuilder.toFirSourceElement$default(this, whenCondition, null, 1, null), new ConeSyntaxDiagnostic("No range in condition with range"), null, 4, null);
        }
        return createWhenConditionConvertedResults(subjectVariable != null, ConversionUtilsKt.generateContainsOperation(firExpressionBuildErrorExpression$default, firPropertyAccessExpressionBuildWhenSubjectAccess, z, AbstractRawFirBuilder.toFirSourceElement$default(this, whenCondition, null, 1, null), firSourceElement$default3), whenCondition);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    private final WhenConditionConvertedResults convertWhenConditionIsPattern(LighterASTNode whenCondition, FirVariable subjectVariable) throws Exception {
        FirOperation firOperation = null;
        FirResolvedTypeRef firResolvedTypeRefBuild = null;
        for (LighterASTNode lighterASTNode : getChildrenAsArray(whenCondition)) {
            if (lighterASTNode == null) {
                break;
            }
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType())) {
                IElementType tokenType = lighterASTNode.getTokenType();
                if (Intrinsics.areEqual(tokenType, KtNodeTypes.TYPE_REFERENCE)) {
                    firResolvedTypeRefBuild = this.declarationBuilder.convertType(lighterASTNode);
                } else if (Intrinsics.areEqual(tokenType, KtTokens.IS_KEYWORD)) {
                    firOperation = FirOperation.IS;
                } else if (Intrinsics.areEqual(tokenType, KtTokens.NOT_IS)) {
                    firOperation = FirOperation.NOT_IS;
                }
            }
        }
        FirPropertyAccessExpression firPropertyAccessExpressionBuildWhenSubjectAccess = UtilsKt.buildWhenSubjectAccess(AbstractRawFirBuilder.toFirSourceElement$default(this, whenCondition, null, 1, null), subjectVariable);
        FirTypeOperatorCallBuilder firTypeOperatorCallBuilder = new FirTypeOperatorCallBuilder();
        firTypeOperatorCallBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(this, whenCondition, null, 1, null));
        if (firOperation == null) {
            Intrinsics.throwUninitializedPropertyAccessException("firOperation");
            firOperation = null;
        }
        firTypeOperatorCallBuilder.setOperation(firOperation);
        if (firResolvedTypeRefBuild == null) {
            FirErrorTypeRefBuilder firErrorTypeRefBuilder = new FirErrorTypeRefBuilder();
            firErrorTypeRefBuilder.setDiagnostic(new ConeSyntaxDiagnostic("Incomplete code"));
            firErrorTypeRefBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(this, whenCondition, null, 1, null));
            firResolvedTypeRefBuild = firErrorTypeRefBuilder.build();
        }
        firTypeOperatorCallBuilder.setConversionTypeRef(firResolvedTypeRefBuild);
        firTypeOperatorCallBuilder.setArgumentList(FirArgumentUtilKt.buildUnaryArgumentList(firPropertyAccessExpressionBuildWhenSubjectAccess));
        return createWhenConditionConvertedResults(subjectVariable != null, firTypeOperatorCallBuilder.mo288build(), whenCondition);
    }

    /* JADX WARN: Code duplicated, block: B:17:0x0061  */
    /* JADX WARN: Code duplicated, block: B:18:0x0064  */
    private final WhenEntry convertWhenEntry(LighterASTNode whenEntry, FirVariable subjectVariable) throws Exception {
        KtSourceElement firSourceElement$default;
        FirExpression firExpressionBuildErrorExpression;
        KtSourceElement source;
        KtSourceElement firSourceElement$default2;
        FirBlock firBlockBuildEmptyExpressionBlock = FirEmptyExpressionBlockBuilderKt.buildEmptyExpressionBlock();
        ArrayList arrayList = new ArrayList();
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        boolean z = false;
        boolean z2 = false;
        for (LighterASTNode lighterASTNode : getChildrenAsArray(whenEntry)) {
            if (lighterASTNode == null) {
                break;
            }
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType())) {
                IElementType tokenType = lighterASTNode.getTokenType();
                if (Intrinsics.areEqual(tokenType, KtNodeTypes.WHEN_CONDITION_EXPRESSION)) {
                    arrayList.add(convertWhenConditionExpression(lighterASTNode, subjectVariable));
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.WHEN_CONDITION_IN_RANGE)) {
                    WhenConditionConvertedResults whenConditionConvertedResultsConvertWhenConditionInRange = convertWhenConditionInRange(lighterASTNode, subjectVariable);
                    FirExpression expression = whenConditionConvertedResultsConvertWhenConditionInRange.getExpression();
                    boolean shouldBindSubject = whenConditionConvertedResultsConvertWhenConditionInRange.getShouldBindSubject();
                    arrayList.add(expression);
                    if (z2 || shouldBindSubject) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.WHEN_CONDITION_IS_PATTERN)) {
                    WhenConditionConvertedResults whenConditionConvertedResultsConvertWhenConditionIsPattern = convertWhenConditionIsPattern(lighterASTNode, subjectVariable);
                    FirExpression expression2 = whenConditionConvertedResultsConvertWhenConditionIsPattern.getExpression();
                    boolean shouldBindSubject2 = whenConditionConvertedResultsConvertWhenConditionIsPattern.getShouldBindSubject();
                    arrayList.add(expression2);
                    if (z2 || shouldBindSubject2) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.WHEN_ENTRY_GUARD)) {
                    LighterASTNode firstChildExpressionUnwrapped = getFirstChildExpressionUnwrapped(lighterASTNode);
                    FirStatement asFirStatement = firstChildExpressionUnwrapped != null ? getAsFirStatement(firstChildExpressionUnwrapped, "No expression in guard") : null;
                    if (asFirStatement instanceof FirExpression) {
                        firExpressionBuildErrorExpression = (FirExpression) asFirStatement;
                        if (UtilsKt.isStatementLikeExpression(firExpressionBuildErrorExpression)) {
                            KtSourceElement source2 = firExpressionBuildErrorExpression.getSource();
                            if (source2 == null || (firSourceElement$default2 = KtSourceElementKt.realElement(source2)) == null) {
                                firSourceElement$default2 = firstChildExpressionUnwrapped != null ? AbstractRawFirBuilder.toFirSourceElement$default(this, firstChildExpressionUnwrapped, null, 1, null) : toFirSourceElement(lighterASTNode, (KtFakeSourceElementKind) KtFakeSourceElementKind.ErrorExpression.INSTANCE);
                            }
                            firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default2, new ConeSimpleDiagnostic("No expression in guard", DiagnosticKind.ExpressionExpected), asFirStatement);
                        }
                    } else {
                        if (asFirStatement == null || (source = asFirStatement.getSource()) == null || (firSourceElement$default = KtSourceElementKt.realElement(source)) == null) {
                            firSourceElement$default = firstChildExpressionUnwrapped != null ? AbstractRawFirBuilder.toFirSourceElement$default(this, firstChildExpressionUnwrapped, null, 1, null) : toFirSourceElement(lighterASTNode, (KtFakeSourceElementKind) KtFakeSourceElementKind.ErrorExpression.INSTANCE);
                        }
                        firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default, firstChildExpressionUnwrapped == null ? new ConeSyntaxDiagnostic("No expression in guard") : new ConeSimpleDiagnostic("No expression in guard", DiagnosticKind.ExpressionExpected), asFirStatement);
                    }
                    if (firExpressionBuildErrorExpression == null) {
                        x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.expressions.FirExpression");
                        return null;
                    }
                    objectRef.element = firExpressionBuildErrorExpression;
                } else if (Intrinsics.areEqual(tokenType, KtTokens.ELSE_KEYWORD)) {
                    z = true;
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.BLOCK)) {
                    firBlockBuildEmptyExpressionBlock = LightTreeRawFirDeclarationBuilder.convertBlock$default(this.declarationBuilder, lighterASTNode, false, 2, null);
                } else if (ElementTypeUtils.INSTANCE.isExpression(lighterASTNode)) {
                    firBlockBuildEmptyExpressionBlock = LightTreeRawFirDeclarationBuilder.convertBlock$default(this.declarationBuilder, lighterASTNode, false, 2, null);
                }
            }
        }
        return new WhenEntry(arrayList, (FirExpression) objectRef.element, firBlockBuildEmptyExpressionBlock, whenEntry, z, z2, getTree());
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
    private final FirWhenExpression convertWhenExpression(LighterASTNode whenExpression) throws KotlinIllegalStateExceptionWithAttachments {
        FirExpression initializer;
        FirWhenBranch firWhenBranchBuild;
        LighterASTNode lighterASTNode;
        KtSourceElement firSourceElement$default;
        FirExpression firExpressionBuildErrorExpression;
        KtSourceElement source;
        KtSourceElement firSourceElement$default2;
        KtSourceElement firSourceElement$default3;
        FirExpression firExpressionBuildErrorExpression2;
        KtSourceElement source2;
        KtSourceElement firSourceElement$default4;
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        ArrayList arrayList = new ArrayList();
        ArrayList<WhenEntry> arrayList2 = new ArrayList();
        LighterASTNode[] childrenAsArray = getChildrenAsArray(whenExpression);
        int length = childrenAsArray.length;
        int i = 0;
        while (true) {
            FirReceiverParameter firReceiverParameterMo288build = null;
            if (i >= length || (lighterASTNode = childrenAsArray[i]) == null) {
                break;
            }
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType())) {
                IElementType tokenType = lighterASTNode.getTokenType();
                if (Intrinsics.areEqual(tokenType, KtNodeTypes.PROPERTY)) {
                    FirProperty firPropertyConvertPropertyDeclaration$default = LightTreeRawFirDeclarationBuilder.convertPropertyDeclaration$default(this.declarationBuilder, lighterASTNode, null, 2, null);
                    firPropertyConvertPropertyDeclaration$default.getClass();
                    FirPropertyBuilder firPropertyBuilder = new FirPropertyBuilder();
                    firPropertyBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode, null, 1, null));
                    firPropertyBuilder.setOrigin(FirDeclarationOrigin.Source.INSTANCE);
                    firPropertyBuilder.setModuleData(getBaseModuleData());
                    firPropertyBuilder.setReturnTypeRef(firPropertyConvertPropertyDeclaration$default.getReturnTypeRef());
                    firPropertyBuilder.setName(firPropertyConvertPropertyDeclaration$default.getName());
                    firPropertyBuilder.setInitializer(firPropertyConvertPropertyDeclaration$default.getInitializer());
                    firPropertyBuilder.setVar(false);
                    firPropertyBuilder.setSymbol(new FirLocalPropertySymbol());
                    firPropertyBuilder.setStatus(new FirDeclarationStatusImpl(Visibilities.Local.INSTANCE, Modality.FINAL));
                    firPropertyBuilder.setLocal(true);
                    FirReceiverParameter receiverParameter = firPropertyConvertPropertyDeclaration$default.getReceiverParameter();
                    if (receiverParameter != null) {
                        FirReceiverParameterBuilder firReceiverParameterBuilder = new FirReceiverParameterBuilder();
                        firReceiverParameterBuilder.setSource(receiverParameter.getSource());
                        firReceiverParameterBuilder.setResolvePhase(FirResolveStateKt.getResolvePhase(receiverParameter));
                        firReceiverParameterBuilder.setModuleData(receiverParameter.getModuleData());
                        firReceiverParameterBuilder.setOrigin(receiverParameter.getOrigin());
                        firReceiverParameterBuilder.setAttributes(receiverParameter.getAttributes().copy());
                        firReceiverParameterBuilder.setTypeRef(receiverParameter.getTypeRef());
                        firReceiverParameterBuilder.setContainingDeclarationSymbol(receiverParameter.getContainingDeclarationSymbol());
                        firReceiverParameterBuilder.getAnnotations().addAll(receiverParameter.getAnnotations());
                        firReceiverParameterBuilder.setSymbol(new FirReceiverParameterSymbol());
                        firReceiverParameterBuilder.setContainingDeclarationSymbol(firPropertyBuilder.getSymbol());
                        firReceiverParameterMo288build = firReceiverParameterBuilder.mo288build();
                    }
                    firPropertyBuilder.setReceiverParameter(firReceiverParameterMo288build);
                    CollectionsKt.addAll(firPropertyBuilder.getAnnotations(), firPropertyConvertPropertyDeclaration$default.getAnnotations());
                    objectRef2.element = firPropertyBuilder.mo288build();
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.DESTRUCTURING_DECLARATION)) {
                    String str = "Incorrect when subject expression: " + getAsText(whenExpression);
                    FirStatement asFirStatement = getAsFirStatement(lighterASTNode, str);
                    if (asFirStatement instanceof FirExpression) {
                        firExpressionBuildErrorExpression2 = (FirExpression) asFirStatement;
                        if (UtilsKt.isStatementLikeExpression(firExpressionBuildErrorExpression2)) {
                            KtSourceElement source3 = firExpressionBuildErrorExpression2.getSource();
                            if (source3 == null || (firSourceElement$default4 = KtSourceElementKt.realElement(source3)) == null) {
                                firSourceElement$default4 = AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode, null, 1, null);
                            }
                            firExpressionBuildErrorExpression2 = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default4, new ConeSimpleDiagnostic(str, DiagnosticKind.ExpressionExpected), asFirStatement);
                        }
                    } else {
                        if (asFirStatement == null || (source2 = asFirStatement.getSource()) == null || (firSourceElement$default3 = KtSourceElementKt.realElement(source2)) == null) {
                            firSourceElement$default3 = AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode, null, 1, null);
                        }
                        firExpressionBuildErrorExpression2 = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default3, new ConeSimpleDiagnostic(str, DiagnosticKind.ExpressionExpected), asFirStatement);
                    }
                    if (firExpressionBuildErrorExpression2 == null) {
                        x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.expressions.FirExpression");
                        return null;
                    }
                    objectRef.element = firExpressionBuildErrorExpression2;
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.WHEN_ENTRY)) {
                    arrayList.add(lighterASTNode);
                } else if (ElementTypeUtils.INSTANCE.isExpression(lighterASTNode)) {
                    String str2 = "Incorrect when subject expression: " + getAsText(whenExpression);
                    FirStatement asFirStatement2 = getAsFirStatement(lighterASTNode, str2);
                    if (asFirStatement2 instanceof FirExpression) {
                        firExpressionBuildErrorExpression = (FirExpression) asFirStatement2;
                        if (UtilsKt.isStatementLikeExpression(firExpressionBuildErrorExpression)) {
                            KtSourceElement source4 = firExpressionBuildErrorExpression.getSource();
                            if (source4 == null || (firSourceElement$default2 = KtSourceElementKt.realElement(source4)) == null) {
                                firSourceElement$default2 = AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode, null, 1, null);
                            }
                            firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default2, new ConeSimpleDiagnostic(str2, DiagnosticKind.ExpressionExpected), asFirStatement2);
                        }
                    } else {
                        if (asFirStatement2 == null || (source = asFirStatement2.getSource()) == null || (firSourceElement$default = KtSourceElementKt.realElement(source)) == null) {
                            firSourceElement$default = AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode, null, 1, null);
                        }
                        firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default, new ConeSimpleDiagnostic(str2, DiagnosticKind.ExpressionExpected), asFirStatement2);
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
        }
        FirVariable firVariable = (FirVariable) objectRef2.element;
        if (firVariable == null || (initializer = firVariable.getInitializer()) == null) {
            initializer = (FirExpression) objectRef.element;
        }
        objectRef.element = initializer;
        boolean z = initializer != null;
        if (z && objectRef2.element == null) {
            Name name = SpecialNames.WHEN_SUBJECT;
            FirPropertyBuilder firPropertyBuilder2 = new FirPropertyBuilder();
            KtSourceElement source5 = ((FirExpression) objectRef.element).getSource();
            firPropertyBuilder2.setSource(source5 != null ? KtSourceElementKt.fakeElement$default(source5, KtFakeSourceElementKind.WhenGeneratedSubject.INSTANCE, null, 2, null) : null);
            firPropertyBuilder2.setOrigin(FirDeclarationOrigin.Synthetic.ImplicitWhenSubject.INSTANCE);
            firPropertyBuilder2.setModuleData(getBaseModuleData());
            firPropertyBuilder2.setReturnTypeRef(FirImplicitTypeRefImplWithoutSource.INSTANCE);
            firPropertyBuilder2.setName(name);
            firPropertyBuilder2.setInitializer((FirExpression) objectRef.element);
            firPropertyBuilder2.setVar(false);
            firPropertyBuilder2.setSymbol(new FirLocalPropertySymbol());
            firPropertyBuilder2.setStatus(new FirDeclarationStatusImpl(Visibilities.Local.INSTANCE, Modality.FINAL));
            firPropertyBuilder2.setLocal(true);
            objectRef2.element = firPropertyBuilder2.mo288build();
        }
        FirExpressionRef firExpressionRef = new FirExpressionRef();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(convertWhenEntry((LighterASTNode) it.next(), (FirVariable) objectRef2.element));
        }
        FirWhenExpressionBuilder firWhenExpressionBuilder = new FirWhenExpressionBuilder();
        firWhenExpressionBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(this, whenExpression, null, 1, null));
        firWhenExpressionBuilder.setSubjectVariable((FirVariable) objectRef2.element);
        firWhenExpressionBuilder.setUsedAsExpression(getUsedAsExpression(whenExpression));
        boolean z2 = z;
        for (WhenEntry whenEntry : arrayList2) {
            z2 = z2 || whenEntry.getShouldBindSubject();
            FirBlock firBlock = whenEntry.getFirBlock();
            KtSourceElement ktSourceElement = (KtLightSourceElement) AbstractRawFirBuilder.toFirSourceElement$default(this, whenEntry.getNode(), null, 1, null);
            List<FirWhenBranch> branches = firWhenExpressionBuilder.getBranches();
            if (whenEntry.getIsElse()) {
                FirAbstractWhenBranchBuilder firGuardedWhenBranchBuilder = whenEntry.getGuard() != null ? new FirGuardedWhenBranchBuilder() : new FirRegularWhenBranchBuilder();
                firGuardedWhenBranchBuilder.setSource(ktSourceElement);
                FirExpression guard = whenEntry.getGuard();
                if (guard == null) {
                    FirElseIfTrueConditionBuilder firElseIfTrueConditionBuilder = new FirElseIfTrueConditionBuilder();
                    Unit unit = Unit.INSTANCE;
                    guard = firElseIfTrueConditionBuilder.mo288build();
                }
                firGuardedWhenBranchBuilder.setCondition(guard);
                firGuardedWhenBranchBuilder.setResult(firBlock);
                firWhenBranchBuild = firGuardedWhenBranchBuilder.build();
            } else if (z) {
                FirExpression firWhenCondition = whenEntry.toFirWhenCondition();
                FirAbstractWhenBranchBuilder firGuardedWhenBranchBuilder2 = whenEntry.getGuard() != null ? new FirGuardedWhenBranchBuilder() : new FirRegularWhenBranchBuilder();
                firGuardedWhenBranchBuilder2.setSource(ktSourceElement);
                firGuardedWhenBranchBuilder2.setCondition(ConversionUtilsKt.guardedBy(firWhenCondition, whenEntry.getGuard()));
                firGuardedWhenBranchBuilder2.setResult(firBlock);
                firWhenBranchBuild = firGuardedWhenBranchBuilder2.build();
            } else {
                FirExpression firWhenConditionWithoutSubject = whenEntry.toFirWhenConditionWithoutSubject();
                FirAbstractWhenBranchBuilder firGuardedWhenBranchBuilder3 = whenEntry.getGuard() != null ? new FirGuardedWhenBranchBuilder() : new FirRegularWhenBranchBuilder();
                firGuardedWhenBranchBuilder3.setSource(ktSourceElement);
                firGuardedWhenBranchBuilder3.setCondition(ConversionUtilsKt.guardedBy(firWhenConditionWithoutSubject, whenEntry.getGuard()));
                firGuardedWhenBranchBuilder3.setResult(firBlock);
                firWhenBranchBuild = firGuardedWhenBranchBuilder3.build();
            }
            branches.add(firWhenBranchBuild);
        }
        FirWhenExpression firWhenExpressionMo288build = firWhenExpressionBuilder.mo288build();
        if (z2) {
            firExpressionRef.bind(firWhenExpressionMo288build);
        }
        return firWhenExpressionMo288build;
    }

    private final FirLoop convertWhile(LighterASTNode whileLoop) {
        LighterASTNode lighterASTNode;
        KtSourceElement firSourceElement$default;
        FirExpression firExpressionBuildErrorExpression;
        KtSourceElement source;
        KtSourceElement firSourceElement$default2;
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        LighterASTNode[] childrenAsArray = getChildrenAsArray(whileLoop);
        int length = childrenAsArray.length;
        for (int i = 0; i < length && (lighterASTNode = childrenAsArray[i]) != null; i++) {
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType())) {
                IElementType tokenType = lighterASTNode.getTokenType();
                if (Intrinsics.areEqual(tokenType, KtNodeTypes.BODY)) {
                    objectRef.element = lighterASTNode;
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.CONDITION)) {
                    FirStatement asFirStatement = getAsFirStatement(lighterASTNode, "No condition in while loop");
                    if (asFirStatement instanceof FirExpression) {
                        firExpressionBuildErrorExpression = (FirExpression) asFirStatement;
                        if (UtilsKt.isStatementLikeExpression(firExpressionBuildErrorExpression)) {
                            KtSourceElement source2 = firExpressionBuildErrorExpression.getSource();
                            if (source2 == null || (firSourceElement$default2 = KtSourceElementKt.realElement(source2)) == null) {
                                firSourceElement$default2 = AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode, null, 1, null);
                            }
                            firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default2, new ConeSimpleDiagnostic("No condition in while loop", DiagnosticKind.ExpressionExpected), asFirStatement);
                        }
                    } else {
                        if (asFirStatement == null || (source = asFirStatement.getSource()) == null || (firSourceElement$default = KtSourceElementKt.realElement(source)) == null) {
                            firSourceElement$default = AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode, null, 1, null);
                        }
                        firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default, new ConeSimpleDiagnostic("No condition in while loop", DiagnosticKind.ExpressionExpected), asFirStatement);
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
        }
        FirWhileLoopBuilder firWhileLoopBuilder = new FirWhileLoopBuilder();
        firWhileLoopBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(this, whileLoop, null, 1, null));
        FirExpression firExpressionBuildErrorExpression$default = (FirExpression) objectRef2.element;
        if (firExpressionBuildErrorExpression$default == null) {
            firExpressionBuildErrorExpression$default = FirExpressionUtilKt.buildErrorExpression$default(AbstractRawFirBuilder.toFirSourceElement$default(this, whileLoop, null, 1, null), new ConeSyntaxDiagnostic("No condition in while loop"), null, 4, null);
        }
        firWhileLoopBuilder.setCondition(firExpressionBuildErrorExpression$default);
        return configure(firWhileLoopBuilder, prepareTarget(firWhileLoopBuilder, whileLoop), new Function0() { // from class: q19
            public final Object invoke() {
                return LightTreeRawFirExpressionBuilder.l(this.b, objectRef);
            }
        });
    }

    private final FirNamedReference createSimpleNamedReference(KtSourceElement sourceElement, LighterASTNode referenceExpression) {
        FirSimpleNamedReferenceBuilder firSimpleNamedReferenceBuilder = new FirSimpleNamedReferenceBuilder();
        firSimpleNamedReferenceBuilder.setSource(sourceElement);
        firSimpleNamedReferenceBuilder.setName(ConverterUtilKt.nameAsSafeName$default(getAsText(referenceExpression), null, 1, null));
        return firSimpleNamedReferenceBuilder.build();
    }

    private final WhenConditionConvertedResults createWhenConditionConvertedResults(boolean hasSubject, FirExpression result, LighterASTNode whenCondition) {
        if (hasSubject) {
            return new WhenConditionConvertedResults(result, false);
        }
        FirErrorExpressionBuilder firErrorExpressionBuilder = new FirErrorExpressionBuilder();
        firErrorExpressionBuilder.setSource(AbstractRawFirBuilder.toFirSourceElement$default(this, whenCondition, null, 1, null));
        firErrorExpressionBuilder.setDiagnostic(new ConeSimpleDiagnostic("No expression in condition with expression", DiagnosticKind.ExpressionExpected));
        firErrorExpressionBuilder.setNonExpressionElement(result);
        return new WhenConditionConvertedResults(firErrorExpressionBuilder.mo288build(), true);
    }

    private final Triple<LighterASTNode, LighterASTNode, LighterASTNode> extractBinaryExpression(LighterASTNode binaryExpression) {
        LighterASTNode lighterASTNode = null;
        LighterASTNode lighterASTNode2 = null;
        LighterASTNode lighterASTNode3 = null;
        for (LighterASTNode lighterASTNode4 : getChildrenAsArray(binaryExpression)) {
            if (lighterASTNode4 == null) {
                break;
            }
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode4.getTokenType())) {
                if (Intrinsics.areEqual(lighterASTNode4.getTokenType(), KtNodeTypes.OPERATION_REFERENCE)) {
                    lighterASTNode3 = lighterASTNode4;
                } else if (ElementTypeUtils.INSTANCE.isExpression(lighterASTNode4)) {
                    if (lighterASTNode3 == null) {
                        lighterASTNode = lighterASTNode4;
                    } else {
                        lighterASTNode2 = lighterASTNode4;
                    }
                }
            }
        }
        lighterASTNode3.getClass();
        return new Triple<>(lighterASTNode, lighterASTNode3, lighterASTNode2);
    }

    public static /* synthetic */ FirExpression getAsFirExpression$org_jetbrains_kotlin_fir_light_tree2fir$default(LightTreeRawFirExpressionBuilder lightTreeRawFirExpressionBuilder, LighterASTNode lighterASTNode, String str, LighterASTNode lighterASTNode2, Function1 function1, int i, Object obj) {
        KtSourceElement firSourceElement$default;
        FirExpression firExpressionBuildErrorExpression;
        KtSourceElement source;
        KtSourceElement firSourceElement$default2;
        if ((i & 2) != 0) {
            str = Argument.Delimiters.none;
        }
        if ((i & 8) != 0) {
            Intrinsics.needClassReification();
            function1 = LightTreeRawFirExpressionBuilder$getAsFirExpression$2.INSTANCE;
        }
        str.getClass();
        lighterASTNode2.getClass();
        function1.getClass();
        FirStatement asFirStatement = lighterASTNode != null ? lightTreeRawFirExpressionBuilder.getAsFirStatement(lighterASTNode, str) : null;
        Intrinsics.reifiedOperationMarker(3, "R");
        if (!(asFirStatement instanceof FirExpression)) {
            if (asFirStatement == null || (source = asFirStatement.getSource()) == null || (firSourceElement$default = KtSourceElementKt.realElement(source)) == null) {
                firSourceElement$default = lighterASTNode != null ? AbstractRawFirBuilder.toFirSourceElement$default(lightTreeRawFirExpressionBuilder, lighterASTNode, null, 1, null) : lightTreeRawFirExpressionBuilder.toFirSourceElement(lighterASTNode2, (KtFakeSourceElementKind) KtFakeSourceElementKind.ErrorExpression.INSTANCE);
            }
            firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default, lighterASTNode == null ? new ConeSyntaxDiagnostic(str) : new ConeSimpleDiagnostic(str, DiagnosticKind.ExpressionExpected), asFirStatement);
        } else if (((Boolean) function1.invoke(asFirStatement)).booleanValue()) {
            firExpressionBuildErrorExpression = (FirExpression) asFirStatement;
        } else {
            KtSourceElement source2 = ((FirExpression) asFirStatement).getSource();
            if (source2 == null || (firSourceElement$default2 = KtSourceElementKt.realElement(source2)) == null) {
                firSourceElement$default2 = lighterASTNode != null ? AbstractRawFirBuilder.toFirSourceElement$default(lightTreeRawFirExpressionBuilder, lighterASTNode, null, 1, null) : lightTreeRawFirExpressionBuilder.toFirSourceElement(lighterASTNode2, (KtFakeSourceElementKind) KtFakeSourceElementKind.ErrorExpression.INSTANCE);
            }
            firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default2, new ConeSimpleDiagnostic(str, DiagnosticKind.ExpressionExpected), asFirStatement);
        }
        Intrinsics.reifiedOperationMarker(1, "R");
        return firExpressionBuildErrorExpression;
    }

    public static /* synthetic */ FirStatement getAsFirStatement$default(LightTreeRawFirExpressionBuilder lightTreeRawFirExpressionBuilder, LighterASTNode lighterASTNode, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = Argument.Delimiters.none;
        }
        return lightTreeRawFirExpressionBuilder.getAsFirStatement(lighterASTNode, str);
    }

    private final boolean getUsedAsExpression(LighterASTNode lighterASTNode) {
        LighterASTNode parent = getParent(lighterASTNode);
        if (parent == null) {
            return true;
        }
        do {
            if (!Intrinsics.areEqual(getElementType(parent), KtNodeTypes.ANNOTATED_EXPRESSION) && !Intrinsics.areEqual(getElementType(parent), KtNodeTypes.LABELED_EXPRESSION)) {
                IElementType tokenType = parent.getTokenType();
                if (Intrinsics.areEqual(tokenType, KtNodeTypes.BLOCK)) {
                    return Intrinsics.areEqual(getLastChildExpression(parent), lighterASTNode) && getUsedAsExpression(parent);
                }
                if (Intrinsics.areEqual(tokenType, KtNodeTypes.TRY) || Intrinsics.areEqual(tokenType, KtNodeTypes.CATCH)) {
                    return getUsedAsExpression(parent);
                }
                if (Intrinsics.areEqual(tokenType, KtNodeTypes.THEN) || Intrinsics.areEqual(tokenType, KtNodeTypes.ELSE) || Intrinsics.areEqual(tokenType, KtNodeTypes.WHEN_ENTRY)) {
                    LighterASTNode parent2 = getParent(parent);
                    if (parent2 != null) {
                        return getUsedAsExpression(parent2);
                    }
                    return true;
                }
                if (!Intrinsics.areEqual(tokenType, KtNodeTypes.CLASS_INITIALIZER) && !Intrinsics.areEqual(tokenType, KtNodeTypes.SCRIPT_INITIALIZER) && !Intrinsics.areEqual(tokenType, KtNodeTypes.SECONDARY_CONSTRUCTOR) && !Intrinsics.areEqual(tokenType, KtNodeTypes.FUNCTION_LITERAL) && !Intrinsics.areEqual(tokenType, KtNodeTypes.FINALLY)) {
                    if (!Intrinsics.areEqual(tokenType, KtNodeTypes.FUN) && !Intrinsics.areEqual(tokenType, KtNodeTypes.PROPERTY_ACCESSOR)) {
                        if (Intrinsics.areEqual(tokenType, KtNodeTypes.DOT_QUALIFIED_EXPRESSION)) {
                            return Intrinsics.areEqual(getFirstChild(parent), lighterASTNode);
                        }
                        if (!Intrinsics.areEqual(tokenType, KtNodeTypes.BODY)) {
                            return true;
                        }
                        LighterASTNode parent3 = getParent(parent);
                        IElementType tokenType2 = parent3 != null ? parent3.getTokenType() : null;
                        return (Intrinsics.areEqual(tokenType2, KtNodeTypes.FOR) || Intrinsics.areEqual(tokenType2, KtNodeTypes.WHILE) || Intrinsics.areEqual(tokenType2, KtNodeTypes.DO_WHILE)) ? false : true;
                    }
                    LighterASTNode[] childrenAsArray = getChildrenAsArray(parent);
                    int length = childrenAsArray.length;
                    for (int i = 0; i < length; i++) {
                        LighterASTNode lighterASTNode2 = childrenAsArray[i];
                        if (Intrinsics.areEqual(lighterASTNode2 != null ? lighterASTNode2.getTokenType() : null, KtTokens.EQ)) {
                            return true;
                        }
                    }
                }
                return false;
            }
            parent = getParent(parent);
        } while (parent != null);
        return true;
    }

    public static FirBlock l(LightTreeRawFirExpressionBuilder lightTreeRawFirExpressionBuilder, Ref.ObjectRef objectRef) {
        return lightTreeRawFirExpressionBuilder.convertLoopBody((LighterASTNode) objectRef.element);
    }

    public static FirBlock m(LightTreeRawFirExpressionBuilder lightTreeRawFirExpressionBuilder, Ref.ObjectRef objectRef) {
        return lightTreeRawFirExpressionBuilder.convertLoopBody((LighterASTNode) objectRef.element);
    }

    public static FirExpression n(LightTreeRawFirExpressionBuilder lightTreeRawFirExpressionBuilder, LighterASTNode lighterASTNode, LighterASTNode lighterASTNode2) {
        KtSourceElement firSourceElement$default;
        FirExpression firExpressionBuildErrorExpression;
        KtSourceElement source;
        KtSourceElement firSourceElement$default2;
        lighterASTNode2.getClass();
        FirStatement asFirStatement = lightTreeRawFirExpressionBuilder.getAsFirStatement(lighterASTNode2, "Incorrect expression in assignment");
        if (asFirStatement instanceof FirExpression) {
            firExpressionBuildErrorExpression = (FirExpression) asFirStatement;
            if (UtilsKt.isStatementLikeExpression(firExpressionBuildErrorExpression) && !UtilsKt.isArraySet(firExpressionBuildErrorExpression)) {
                KtSourceElement source2 = firExpressionBuildErrorExpression.getSource();
                if (source2 == null || (firSourceElement$default2 = KtSourceElementKt.realElement(source2)) == null) {
                    firSourceElement$default2 = AbstractRawFirBuilder.toFirSourceElement$default(lightTreeRawFirExpressionBuilder, lighterASTNode2, null, 1, null);
                }
                firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default2, new ConeSimpleDiagnostic("Incorrect expression in assignment", DiagnosticKind.ExpressionExpected), asFirStatement);
            }
        } else {
            if (asFirStatement == null || (source = asFirStatement.getSource()) == null || (firSourceElement$default = KtSourceElementKt.realElement(source)) == null) {
                firSourceElement$default = AbstractRawFirBuilder.toFirSourceElement$default(lightTreeRawFirExpressionBuilder, lighterASTNode2, null, 1, null);
            }
            firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default, new ConeSimpleDiagnostic("Incorrect expression in assignment", DiagnosticKind.ExpressionExpected), asFirStatement);
        }
        if (firExpressionBuildErrorExpression != null) {
            return firExpressionBuildErrorExpression;
        }
        x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.expressions.FirExpression");
        return null;
    }

    public static Collection o(LightTreeRawFirExpressionBuilder lightTreeRawFirExpressionBuilder, LighterASTNode lighterASTNode, String str) {
        str.getClass();
        return lightTreeRawFirExpressionBuilder.convertShortOrLongStringTemplate(lighterASTNode, str);
    }

    public static String p(LighterASTNode[] lighterASTNodeArr, LightTreeRawFirExpressionBuilder lightTreeRawFirExpressionBuilder) {
        LighterASTNode lighterASTNode;
        String asText;
        int length = lighterASTNodeArr.length;
        int i = 0;
        while (true) {
            lighterASTNode = null;
            if (i >= length) {
                break;
            }
            LighterASTNode lighterASTNode2 = lighterASTNodeArr[i];
            if (Intrinsics.areEqual(lighterASTNode2 != null ? lighterASTNode2.getTokenType() : null, KtNodeTypes.STRING_INTERPOLATION_PREFIX)) {
                lighterASTNode = lighterASTNode2;
                break;
            }
            i++;
        }
        return (lighterASTNode == null || (asText = lightTreeRawFirExpressionBuilder.getAsText(lighterASTNode)) == null) ? Argument.Delimiters.none : asText;
    }

    private final IfNodeComponents parseIfExpression(LighterASTNode ifExpression) {
        KtSourceElement firSourceElement$default;
        FirExpression firExpressionBuildErrorExpression;
        KtSourceElement source;
        KtSourceElement firSourceElement$default2;
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        LighterASTNode lighterASTNode = null;
        LighterASTNode lighterASTNode2 = null;
        for (LighterASTNode lighterASTNode3 : getChildrenAsArray(ifExpression)) {
            if (lighterASTNode3 == null) {
                break;
            }
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode3.getTokenType())) {
                IElementType tokenType = lighterASTNode3.getTokenType();
                if (Intrinsics.areEqual(tokenType, KtNodeTypes.CONDITION)) {
                    FirStatement asFirStatement = getAsFirStatement(lighterASTNode3, "If statement should have condition");
                    if (asFirStatement instanceof FirExpression) {
                        firExpressionBuildErrorExpression = (FirExpression) asFirStatement;
                        if (UtilsKt.isStatementLikeExpression(firExpressionBuildErrorExpression)) {
                            KtSourceElement source2 = firExpressionBuildErrorExpression.getSource();
                            if (source2 == null || (firSourceElement$default2 = KtSourceElementKt.realElement(source2)) == null) {
                                firSourceElement$default2 = AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode3, null, 1, null);
                            }
                            firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default2, new ConeSimpleDiagnostic("If statement should have condition", DiagnosticKind.ExpressionExpected), asFirStatement);
                        }
                    } else {
                        if (asFirStatement == null || (source = asFirStatement.getSource()) == null || (firSourceElement$default = KtSourceElementKt.realElement(source)) == null) {
                            firSourceElement$default = AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode3, null, 1, null);
                        }
                        firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default, new ConeSimpleDiagnostic("If statement should have condition", DiagnosticKind.ExpressionExpected), asFirStatement);
                    }
                    if (firExpressionBuildErrorExpression == null) {
                        x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.expressions.FirExpression");
                        return null;
                    }
                    objectRef.element = firExpressionBuildErrorExpression;
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.THEN)) {
                    lighterASTNode = lighterASTNode3;
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.ELSE)) {
                    lighterASTNode2 = lighterASTNode3;
                }
            }
        }
        return new IfNodeComponents((FirExpression) objectRef.element, lighterASTNode, lighterASTNode2);
    }

    public static FirExpression r(LightTreeRawFirExpressionBuilder lightTreeRawFirExpressionBuilder, LighterASTNode lighterASTNode) {
        KtSourceElement firSourceElement$default;
        FirExpression firExpressionBuildErrorExpression;
        KtSourceElement source;
        KtSourceElement firSourceElement$default2;
        lighterASTNode.getClass();
        FirStatement asFirStatement = lightTreeRawFirExpressionBuilder.getAsFirStatement(lighterASTNode, Argument.Delimiters.none);
        if (asFirStatement instanceof FirExpression) {
            firExpressionBuildErrorExpression = (FirExpression) asFirStatement;
            if (UtilsKt.isStatementLikeExpression(firExpressionBuildErrorExpression)) {
                KtSourceElement source2 = firExpressionBuildErrorExpression.getSource();
                if (source2 == null || (firSourceElement$default2 = KtSourceElementKt.realElement(source2)) == null) {
                    firSourceElement$default2 = AbstractRawFirBuilder.toFirSourceElement$default(lightTreeRawFirExpressionBuilder, lighterASTNode, null, 1, null);
                }
                firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default2, new ConeSimpleDiagnostic(Argument.Delimiters.none, DiagnosticKind.ExpressionExpected), asFirStatement);
            }
        } else {
            if (asFirStatement == null || (source = asFirStatement.getSource()) == null || (firSourceElement$default = KtSourceElementKt.realElement(source)) == null) {
                firSourceElement$default = AbstractRawFirBuilder.toFirSourceElement$default(lightTreeRawFirExpressionBuilder, lighterASTNode, null, 1, null);
            }
            firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default, new ConeSimpleDiagnostic(Argument.Delimiters.none, DiagnosticKind.ExpressionExpected), asFirStatement);
        }
        if (firExpressionBuildErrorExpression != null) {
            return firExpressionBuildErrorExpression;
        }
        x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.expressions.FirExpression");
        return null;
    }

    private final FirExpression tryFoldStringConcatenation(LighterASTNode binaryExpression) {
        KtSourceElement firSourceElement$default;
        FirExpression firExpressionBuildErrorExpression;
        KtSourceElement source;
        KtSourceElement firSourceElement$default2;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList.add(binaryExpression);
        while (!arrayList.isEmpty()) {
            LighterASTNode lighterASTNodePop = pop(arrayList);
            if (Intrinsics.areEqual(lighterASTNodePop != null ? lighterASTNodePop.getTokenType() : null, KtNodeTypes.BINARY_EXPRESSION)) {
                Triple<LighterASTNode, LighterASTNode, LighterASTNode> tripleExtractBinaryExpression = extractBinaryExpression(lighterASTNodePop);
                LighterASTNode lighterASTNode = (LighterASTNode) tripleExtractBinaryExpression.component1();
                LighterASTNode lighterASTNode2 = (LighterASTNode) tripleExtractBinaryExpression.component2();
                LighterASTNode lighterASTNode3 = (LighterASTNode) tripleExtractBinaryExpression.component3();
                if (!Intrinsics.areEqual(ElementTypeUtils.INSTANCE.getOperationSymbol(lighterASTNode2, getTree()), KtTokens.PLUS)) {
                    return null;
                }
                arrayList.add(lighterASTNode);
                arrayList.add(lighterASTNode3);
            } else {
                if (!Intrinsics.areEqual(lighterASTNodePop != null ? lighterASTNodePop.getTokenType() : null, KtNodeTypes.STRING_TEMPLATE)) {
                    return null;
                }
                arrayList2.add(lighterASTNodePop);
            }
        }
        FirStringConcatenationCallBuilder firStringConcatenationCallBuilder = new FirStringConcatenationCallBuilder();
        KtSourceElement ktSourceElement = (KtLightSourceElement) AbstractRawFirBuilder.toFirSourceElement$default(this, binaryExpression, null, 1, null);
        FirArgumentListBuilder firArgumentListBuilder = new FirArgumentListBuilder();
        List<FirExpression> arguments = firArgumentListBuilder.getArguments();
        List<LighterASTNode> listAsReversedMutable = CollectionsKt.asReversedMutable(arrayList2);
        ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(listAsReversedMutable, 10));
        for (LighterASTNode lighterASTNode4 : listAsReversedMutable) {
            FirStatement asFirStatement = lighterASTNode4 != null ? getAsFirStatement(lighterASTNode4, Argument.Delimiters.none) : null;
            if (asFirStatement instanceof FirExpression) {
                firExpressionBuildErrorExpression = (FirExpression) asFirStatement;
                if (UtilsKt.isStatementLikeExpression(firExpressionBuildErrorExpression)) {
                    KtSourceElement source2 = firExpressionBuildErrorExpression.getSource();
                    if (source2 == null || (firSourceElement$default2 = KtSourceElementKt.realElement(source2)) == null) {
                        firSourceElement$default2 = lighterASTNode4 != null ? AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode4, null, 1, null) : toFirSourceElement(binaryExpression, (KtFakeSourceElementKind) KtFakeSourceElementKind.ErrorExpression.INSTANCE);
                    }
                    firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default2, new ConeSimpleDiagnostic(Argument.Delimiters.none, DiagnosticKind.ExpressionExpected), asFirStatement);
                }
            } else {
                if (asFirStatement == null || (source = asFirStatement.getSource()) == null || (firSourceElement$default = KtSourceElementKt.realElement(source)) == null) {
                    firSourceElement$default = lighterASTNode4 != null ? AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode4, null, 1, null) : toFirSourceElement(binaryExpression, (KtFakeSourceElementKind) KtFakeSourceElementKind.ErrorExpression.INSTANCE);
                }
                firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default, lighterASTNode4 == null ? new ConeSyntaxDiagnostic(Argument.Delimiters.none) : new ConeSimpleDiagnostic(Argument.Delimiters.none, DiagnosticKind.ExpressionExpected), asFirStatement);
            }
            if (firExpressionBuildErrorExpression == null) {
                x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.expressions.FirExpression");
                return null;
            }
            arrayList3.add(firExpressionBuildErrorExpression);
        }
        CollectionsKt.addAll(arguments, arrayList3);
        firArgumentListBuilder.setSource(ktSourceElement);
        firStringConcatenationCallBuilder.setArgumentList(firArgumentListBuilder.build());
        firStringConcatenationCallBuilder.setSource(ktSourceElement);
        firStringConcatenationCallBuilder.setInterpolationPrefix(Argument.Delimiters.none);
        firStringConcatenationCallBuilder.setFoldedStrings(true);
        return firStringConcatenationCallBuilder.mo288build();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    /* JADX INFO: renamed from: convertReplSnippet, reason: avoid collision after fix types in other method */
    public FirReplSnippet convertReplSnippet2(LighterASTNode script, KtSourceElement scriptSource, String fileName, Function1<? super FirReplSnippetBuilder, Unit> snippetSetup, Function1<? super FirBlockBuilder, Unit> functionBodySetup, Function1<? super List<FirElement>, Unit> statementsSetup) throws KotlinNothingValueException {
        script.getClass();
        scriptSource.getClass();
        fileName.getClass();
        snippetSetup.getClass();
        functionBodySetup.getClass();
        statementsSetup.getClass();
        AddToStdlibKt.shouldNotBeCalled$default((String) null, 1, (Object) null);
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    /* JADX INFO: renamed from: convertScript, reason: avoid collision after fix types in other method */
    public FirScript convertScript2(LighterASTNode script, KtSourceElement scriptSource, String fileName, Function1<? super FirScriptBuilder, Unit> setup) throws KotlinNothingValueException {
        script.getClass();
        scriptSource.getClass();
        fileName.getClass();
        setup.getClass();
        AddToStdlibKt.shouldNotBeCalled$default((String) null, 1, (Object) null);
        throw new KotlinNothingValueException();
    }

    public final List<FirExpression> convertValueArguments(LighterASTNode valueArguments) {
        KtSourceElement firSourceElement$default;
        FirExpression firExpressionBuildErrorExpression;
        KtSourceElement source;
        KtSourceElement firSourceElement$default2;
        valueArguments.getClass();
        LighterASTNode[] childrenAsArray = getChildrenAsArray(valueArguments);
        ArrayList arrayList = new ArrayList();
        for (LighterASTNode lighterASTNode : childrenAsArray) {
            if (lighterASTNode == null) {
                break;
            }
            if (!AbstractLightTreeRawFirBuilder.INSTANCE.getIgnoredTokens().contains(lighterASTNode.getTokenType())) {
                IElementType tokenType = lighterASTNode.getTokenType();
                if (Intrinsics.areEqual(tokenType, KtNodeTypes.VALUE_ARGUMENT)) {
                    arrayList.add(convertValueArgument(lighterASTNode));
                } else if (Intrinsics.areEqual(tokenType, KtNodeTypes.LAMBDA_EXPRESSION) || Intrinsics.areEqual(tokenType, KtNodeTypes.LABELED_EXPRESSION) || Intrinsics.areEqual(tokenType, KtNodeTypes.ANNOTATED_EXPRESSION)) {
                    FirStatement asFirStatement = getAsFirStatement(lighterASTNode, Argument.Delimiters.none);
                    if (asFirStatement instanceof FirAnonymousFunctionExpression) {
                        firExpressionBuildErrorExpression = (FirExpression) asFirStatement;
                        if (UtilsKt.isStatementLikeExpression(firExpressionBuildErrorExpression)) {
                            KtSourceElement source2 = firExpressionBuildErrorExpression.getSource();
                            if (source2 == null || (firSourceElement$default2 = KtSourceElementKt.realElement(source2)) == null) {
                                firSourceElement$default2 = AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode, null, 1, null);
                            }
                            firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default2, new ConeSimpleDiagnostic(Argument.Delimiters.none, DiagnosticKind.ExpressionExpected), asFirStatement);
                        }
                    } else {
                        if (asFirStatement == null || (source = asFirStatement.getSource()) == null || (firSourceElement$default = KtSourceElementKt.realElement(source)) == null) {
                            firSourceElement$default = AbstractRawFirBuilder.toFirSourceElement$default(this, lighterASTNode, null, 1, null);
                        }
                        firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default, new ConeSimpleDiagnostic(Argument.Delimiters.none, DiagnosticKind.ExpressionExpected), asFirStatement);
                    }
                    if (firExpressionBuildErrorExpression == null) {
                        x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression");
                        return null;
                    }
                    FirAnonymousFunctionExpression firAnonymousFunctionExpression = (FirAnonymousFunctionExpression) firExpressionBuildErrorExpression;
                    firAnonymousFunctionExpression.replaceIsTrailingLambda(true);
                    arrayList.add(firAnonymousFunctionExpression);
                }
            }
        }
        return arrayList;
    }

    public final /* synthetic */ <R extends FirExpression> R getAsFirExpression$org_jetbrains_kotlin_fir_light_tree2fir(LighterASTNode expression, String errorReason, LighterASTNode sourceWhenInvalidExpression, Function1<? super R, Boolean> isValidExpression) {
        KtSourceElement firSourceElement$default;
        FirErrorExpression firErrorExpressionBuildErrorExpression;
        KtSourceElement source;
        KtSourceElement firSourceElement$default2;
        errorReason.getClass();
        sourceWhenInvalidExpression.getClass();
        isValidExpression.getClass();
        FirStatement asFirStatement = expression != null ? getAsFirStatement(expression, errorReason) : null;
        Intrinsics.reifiedOperationMarker(3, "R");
        if (!(asFirStatement instanceof FirExpression)) {
            if (asFirStatement == null || (source = asFirStatement.getSource()) == null || (firSourceElement$default = KtSourceElementKt.realElement(source)) == null) {
                firSourceElement$default = expression != null ? AbstractRawFirBuilder.toFirSourceElement$default(this, expression, null, 1, null) : toFirSourceElement(sourceWhenInvalidExpression, (KtFakeSourceElementKind) KtFakeSourceElementKind.ErrorExpression.INSTANCE);
            }
            firErrorExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default, expression == null ? new ConeSyntaxDiagnostic(errorReason) : new ConeSimpleDiagnostic(errorReason, DiagnosticKind.ExpressionExpected), asFirStatement);
        } else if (((Boolean) isValidExpression.invoke(asFirStatement)).booleanValue()) {
            firErrorExpressionBuildErrorExpression = (R) ((FirExpression) asFirStatement);
        } else {
            KtSourceElement source2 = ((FirExpression) asFirStatement).getSource();
            if (source2 == null || (firSourceElement$default2 = KtSourceElementKt.realElement(source2)) == null) {
                firSourceElement$default2 = expression != null ? AbstractRawFirBuilder.toFirSourceElement$default(this, expression, null, 1, null) : toFirSourceElement(sourceWhenInvalidExpression, (KtFakeSourceElementKind) KtFakeSourceElementKind.ErrorExpression.INSTANCE);
            }
            firErrorExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default2, new ConeSimpleDiagnostic(errorReason, DiagnosticKind.ExpressionExpected), asFirStatement);
        }
        Intrinsics.reifiedOperationMarker(1, "R");
        return firErrorExpressionBuildErrorExpression;
    }

    public final FirStatement getAsFirStatement(LighterASTNode expression, String errorReason) {
        KtSourceElement firSourceElement$default;
        FirExpression firExpressionBuildErrorExpression;
        KtSourceElement source;
        KtSourceElement firSourceElement$default2;
        KtSourceElement firSourceElement$default3;
        FirExpression firExpressionBuildErrorExpression2;
        KtSourceElement source2;
        KtSourceElement firSourceElement$default4;
        expression.getClass();
        errorReason.getClass();
        IElementType tokenType = expression.getTokenType();
        if (Intrinsics.areEqual(tokenType, KtNodeTypes.LAMBDA_EXPRESSION)) {
            return convertLambdaExpression(expression);
        }
        if (Intrinsics.areEqual(tokenType, KtNodeTypes.BINARY_WITH_TYPE) || Intrinsics.areEqual(tokenType, KtNodeTypes.IS_EXPRESSION)) {
            return convertBinaryWithTypeRHSExpression(expression);
        }
        if (Intrinsics.areEqual(tokenType, KtNodeTypes.PREFIX_EXPRESSION) || Intrinsics.areEqual(tokenType, KtNodeTypes.POSTFIX_EXPRESSION)) {
            return convertUnaryExpression(expression);
        }
        if (Intrinsics.areEqual(tokenType, KtNodeTypes.CLASS_LITERAL_EXPRESSION)) {
            return convertClassLiteralExpression(expression);
        }
        if (Intrinsics.areEqual(tokenType, KtNodeTypes.CALLABLE_REFERENCE_EXPRESSION)) {
            return convertCallableReferenceExpression(expression);
        }
        if (KtTokens.QUALIFIED_ACCESS.contains(tokenType)) {
            return convertQualifiedExpression(expression);
        }
        if (Intrinsics.areEqual(tokenType, KtNodeTypes.CALL_EXPRESSION)) {
            return convertCallExpression(expression);
        }
        if (Intrinsics.areEqual(tokenType, KtNodeTypes.WHEN)) {
            return convertWhenExpression(expression);
        }
        if (Intrinsics.areEqual(tokenType, KtNodeTypes.ARRAY_ACCESS_EXPRESSION)) {
            return convertArrayAccessExpression(expression);
        }
        if (Intrinsics.areEqual(tokenType, KtNodeTypes.COLLECTION_LITERAL_EXPRESSION)) {
            return convertCollectionLiteralExpression(expression);
        }
        if (Intrinsics.areEqual(tokenType, KtNodeTypes.STRING_TEMPLATE)) {
            return convertStringTemplate(expression);
        }
        if (tokenType instanceof KtConstantExpressionElementType) {
            return convertConstantExpression(expression);
        }
        if (Intrinsics.areEqual(tokenType, KtNodeTypes.REFERENCE_EXPRESSION)) {
            return convertSimpleNameExpression(expression);
        }
        if (Intrinsics.areEqual(tokenType, KtNodeTypes.FOR)) {
            return convertFor(expression);
        }
        if (Intrinsics.areEqual(tokenType, KtNodeTypes.TRY)) {
            return convertTryExpression(expression);
        }
        if (Intrinsics.areEqual(tokenType, KtNodeTypes.IF)) {
            return convertIfExpression(expression);
        }
        if (Intrinsics.areEqual(tokenType, KtNodeTypes.BREAK) || Intrinsics.areEqual(tokenType, KtNodeTypes.CONTINUE)) {
            return convertLoopJump(expression);
        }
        if (Intrinsics.areEqual(tokenType, KtNodeTypes.RETURN)) {
            return convertReturn(expression);
        }
        if (Intrinsics.areEqual(tokenType, KtNodeTypes.THROW)) {
            return convertThrow(expression);
        }
        if (Intrinsics.areEqual(tokenType, KtNodeTypes.PARENTHESIZED)) {
            LighterASTNode expressionInParentheses = getExpressionInParentheses(expression);
            getContext().forwardLabelUsagePermission(expression, expressionInParentheses);
            FirStatement asFirStatement = expressionInParentheses != null ? getAsFirStatement(expressionInParentheses, "Empty parentheses") : null;
            if (asFirStatement instanceof FirExpression) {
                firExpressionBuildErrorExpression2 = (FirExpression) asFirStatement;
                if (UtilsKt.isStatementLikeExpression(firExpressionBuildErrorExpression2)) {
                    KtSourceElement source3 = firExpressionBuildErrorExpression2.getSource();
                    if (source3 == null || (firSourceElement$default4 = KtSourceElementKt.realElement(source3)) == null) {
                        firSourceElement$default4 = expressionInParentheses != null ? AbstractRawFirBuilder.toFirSourceElement$default(this, expressionInParentheses, null, 1, null) : toFirSourceElement(expression, (KtFakeSourceElementKind) KtFakeSourceElementKind.ErrorExpression.INSTANCE);
                    }
                    firExpressionBuildErrorExpression2 = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default4, new ConeSimpleDiagnostic("Empty parentheses", DiagnosticKind.ExpressionExpected), asFirStatement);
                }
            } else {
                if (asFirStatement == null || (source2 = asFirStatement.getSource()) == null || (firSourceElement$default3 = KtSourceElementKt.realElement(source2)) == null) {
                    firSourceElement$default3 = expressionInParentheses != null ? AbstractRawFirBuilder.toFirSourceElement$default(this, expressionInParentheses, null, 1, null) : toFirSourceElement(expression, (KtFakeSourceElementKind) KtFakeSourceElementKind.ErrorExpression.INSTANCE);
                }
                firExpressionBuildErrorExpression2 = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default3, expressionInParentheses == null ? new ConeSyntaxDiagnostic("Empty parentheses") : new ConeSimpleDiagnostic("Empty parentheses", DiagnosticKind.ExpressionExpected), asFirStatement);
            }
            if (firExpressionBuildErrorExpression2 != null) {
                return firExpressionBuildErrorExpression2;
            }
            x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.expressions.FirExpression");
            return null;
        }
        if (Intrinsics.areEqual(tokenType, KtNodeTypes.PROPERTY_DELEGATE) || Intrinsics.areEqual(tokenType, KtNodeTypes.INDICES) || Intrinsics.areEqual(tokenType, KtNodeTypes.CONDITION) || Intrinsics.areEqual(tokenType, KtNodeTypes.LOOP_RANGE)) {
            LighterASTNode childExpression = getChildExpression(expression);
            FirStatement asFirStatement2 = childExpression != null ? getAsFirStatement(childExpression, errorReason) : null;
            if (asFirStatement2 instanceof FirExpression) {
                firExpressionBuildErrorExpression = (FirExpression) asFirStatement2;
                if (UtilsKt.isStatementLikeExpression(firExpressionBuildErrorExpression)) {
                    KtSourceElement source4 = firExpressionBuildErrorExpression.getSource();
                    if (source4 == null || (firSourceElement$default2 = KtSourceElementKt.realElement(source4)) == null) {
                        firSourceElement$default2 = childExpression != null ? AbstractRawFirBuilder.toFirSourceElement$default(this, childExpression, null, 1, null) : toFirSourceElement(expression, (KtFakeSourceElementKind) KtFakeSourceElementKind.ErrorExpression.INSTANCE);
                    }
                    firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default2, new ConeSimpleDiagnostic(errorReason, DiagnosticKind.ExpressionExpected), asFirStatement2);
                }
            } else {
                if (asFirStatement2 == null || (source = asFirStatement2.getSource()) == null || (firSourceElement$default = KtSourceElementKt.realElement(source)) == null) {
                    firSourceElement$default = childExpression != null ? AbstractRawFirBuilder.toFirSourceElement$default(this, childExpression, null, 1, null) : toFirSourceElement(expression, (KtFakeSourceElementKind) KtFakeSourceElementKind.ErrorExpression.INSTANCE);
                }
                firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default, childExpression == null ? new ConeSyntaxDiagnostic(errorReason) : new ConeSimpleDiagnostic(errorReason, DiagnosticKind.ExpressionExpected), asFirStatement2);
            }
            if (firExpressionBuildErrorExpression != null) {
                return firExpressionBuildErrorExpression;
            }
            x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.expressions.FirExpression");
            return null;
        }
        if (Intrinsics.areEqual(tokenType, KtNodeTypes.THIS_EXPRESSION)) {
            return convertThisExpression(expression);
        }
        if (Intrinsics.areEqual(tokenType, KtNodeTypes.SUPER_EXPRESSION)) {
            return convertSuperExpression(expression);
        }
        if (Intrinsics.areEqual(tokenType, KtNodeTypes.OBJECT_LITERAL)) {
            return this.declarationBuilder.convertObjectLiteral(expression);
        }
        if (Intrinsics.areEqual(tokenType, KtNodeTypes.DESTRUCTURING_DECLARATION)) {
            return DestructuringDeclaration.toFirDestructingDeclaration$default(this.declarationBuilder.convertDestructingDeclaration$org_jetbrains_kotlin_fir_light_tree2fir(expression), this, getBaseModuleData(), false, 4, null);
        }
        if (Intrinsics.areEqual(tokenType, KtNodeTypes.BINARY_EXPRESSION)) {
            return convertBinaryExpression(expression);
        }
        if (Intrinsics.areEqual(tokenType, KtNodeTypes.LABELED_EXPRESSION)) {
            return convertLabeledExpression(expression);
        }
        if (Intrinsics.areEqual(tokenType, KtNodeTypes.ANNOTATED_EXPRESSION)) {
            return convertAnnotatedExpression(expression);
        }
        if (Intrinsics.areEqual(tokenType, KtNodeTypes.FUN)) {
            return this.declarationBuilder.convertFunctionDeclaration(expression);
        }
        if (Intrinsics.areEqual(tokenType, KtNodeTypes.DO_WHILE)) {
            return convertDoWhile(expression);
        }
        if (Intrinsics.areEqual(tokenType, KtNodeTypes.WHILE)) {
            return convertWhile(expression);
        }
        if (Intrinsics.areEqual(tokenType, KtNodeTypes.PROPERTY)) {
            return LightTreeRawFirDeclarationBuilder.convertPropertyDeclaration$default(this.declarationBuilder, expression, null, 2, null);
        }
        if (Intrinsics.areEqual(tokenType, KtNodeTypes.CLASS) || Intrinsics.areEqual(tokenType, KtNodeTypes.OBJECT_DECLARATION)) {
            return this.declarationBuilder.convertClass(expression);
        }
        return Intrinsics.areEqual(tokenType, KtNodeTypes.TYPEALIAS) ? this.declarationBuilder.convertTypeAlias(expression) : FirExpressionUtilKt.buildErrorExpression$default(toFirSourceElement(expression, (KtFakeSourceElementKind) KtFakeSourceElementKind.ErrorTypeRef.INSTANCE), new ConeSimpleDiagnostic(errorReason, DiagnosticKind.ExpressionExpected), null, 4, null);
    }

    public /* synthetic */ LightTreeRawFirExpressionBuilder(FirSession firSession, FlyweightCapableTreeStructure flyweightCapableTreeStructure, LightTreeRawFirDeclarationBuilder lightTreeRawFirDeclarationBuilder, Context context, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(firSession, flyweightCapableTreeStructure, lightTreeRawFirDeclarationBuilder, (i & 8) != 0 ? new Context() : context);
    }

    @Override // org.jetbrains.kotlin.fir.builder.AbstractRawFirBuilder
    public /* bridge */ /* synthetic */ FirScript convertScript(LighterASTNode lighterASTNode, KtSourceElement ktSourceElement, String str, Function1 function1) {
        return convertScript2(lighterASTNode, ktSourceElement, str, (Function1<? super FirScriptBuilder, Unit>) function1);
    }

    @Override // org.jetbrains.kotlin.fir.builder.AbstractRawFirBuilder
    public /* bridge */ /* synthetic */ FirReplSnippet convertReplSnippet(LighterASTNode lighterASTNode, KtSourceElement ktSourceElement, String str, Function1 function1, Function1 function2, Function1 function3) {
        return convertReplSnippet2(lighterASTNode, ktSourceElement, str, (Function1<? super FirReplSnippetBuilder, Unit>) function1, (Function1<? super FirBlockBuilder, Unit>) function2, (Function1<? super List<FirElement>, Unit>) function3);
    }

    public final /* synthetic */ <R extends FirExpression> R getAsFirExpression$org_jetbrains_kotlin_fir_light_tree2fir(LighterASTNode expression, String errorReason, Function1<? super R, Boolean> isValidExpression) {
        KtSourceElement firSourceElement$default;
        FirErrorExpression firErrorExpressionBuildErrorExpression;
        KtSourceElement source;
        KtSourceElement firSourceElement$default2;
        expression.getClass();
        errorReason.getClass();
        isValidExpression.getClass();
        FirStatement asFirStatement = getAsFirStatement(expression, errorReason);
        Intrinsics.reifiedOperationMarker(3, "R");
        if (asFirStatement instanceof FirExpression) {
            if (((Boolean) isValidExpression.invoke(asFirStatement)).booleanValue()) {
                firErrorExpressionBuildErrorExpression = (R) ((FirExpression) asFirStatement);
            } else {
                KtSourceElement source2 = ((FirExpression) asFirStatement).getSource();
                if (source2 == null || (firSourceElement$default2 = KtSourceElementKt.realElement(source2)) == null) {
                    firSourceElement$default2 = AbstractRawFirBuilder.toFirSourceElement$default(this, expression, null, 1, null);
                }
                firErrorExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default2, new ConeSimpleDiagnostic(errorReason, DiagnosticKind.ExpressionExpected), asFirStatement);
            }
        } else {
            if (asFirStatement == null || (source = asFirStatement.getSource()) == null || (firSourceElement$default = KtSourceElementKt.realElement(source)) == null) {
                firSourceElement$default = AbstractRawFirBuilder.toFirSourceElement$default(this, expression, null, 1, null);
            }
            firErrorExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default, new ConeSimpleDiagnostic(errorReason, DiagnosticKind.ExpressionExpected), asFirStatement);
        }
        Intrinsics.reifiedOperationMarker(1, "R");
        return firErrorExpressionBuildErrorExpression;
    }

    public static /* synthetic */ FirExpression getAsFirExpression$org_jetbrains_kotlin_fir_light_tree2fir$default(LightTreeRawFirExpressionBuilder lightTreeRawFirExpressionBuilder, LighterASTNode lighterASTNode, String str, Function1 function1, int i, Object obj) {
        KtSourceElement firSourceElement$default;
        FirExpression firExpressionBuildErrorExpression;
        KtSourceElement source;
        KtSourceElement firSourceElement$default2;
        if ((i & 2) != 0) {
            str = Argument.Delimiters.none;
        }
        if ((i & 4) != 0) {
            Intrinsics.needClassReification();
            function1 = LightTreeRawFirExpressionBuilder$getAsFirExpression$1.INSTANCE;
        }
        lighterASTNode.getClass();
        str.getClass();
        function1.getClass();
        FirStatement asFirStatement = lightTreeRawFirExpressionBuilder.getAsFirStatement(lighterASTNode, str);
        Intrinsics.reifiedOperationMarker(3, "R");
        if (asFirStatement instanceof FirExpression) {
            if (((Boolean) function1.invoke(asFirStatement)).booleanValue()) {
                firExpressionBuildErrorExpression = (FirExpression) asFirStatement;
            } else {
                KtSourceElement source2 = ((FirExpression) asFirStatement).getSource();
                if (source2 == null || (firSourceElement$default2 = KtSourceElementKt.realElement(source2)) == null) {
                    firSourceElement$default2 = AbstractRawFirBuilder.toFirSourceElement$default(lightTreeRawFirExpressionBuilder, lighterASTNode, null, 1, null);
                }
                firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default2, new ConeSimpleDiagnostic(str, DiagnosticKind.ExpressionExpected), asFirStatement);
            }
        } else {
            if (asFirStatement == null || (source = asFirStatement.getSource()) == null || (firSourceElement$default = KtSourceElementKt.realElement(source)) == null) {
                firSourceElement$default = AbstractRawFirBuilder.toFirSourceElement$default(lightTreeRawFirExpressionBuilder, lighterASTNode, null, 1, null);
            }
            firExpressionBuildErrorExpression = FirExpressionUtilKt.buildErrorExpression(firSourceElement$default, new ConeSimpleDiagnostic(str, DiagnosticKind.ExpressionExpected), asFirStatement);
        }
        Intrinsics.reifiedOperationMarker(1, "R");
        return firExpressionBuildErrorExpression;
    }
}
