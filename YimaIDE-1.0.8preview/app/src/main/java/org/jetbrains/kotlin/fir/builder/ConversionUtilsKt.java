package org.jetbrains.kotlin.fir.builder;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.ImmutableSet;
import com.intellij.lang.LighterASTNode;
import com.intellij.psi.tree.IElementType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.UninitializedPropertyAccessException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtNodeTypes;
import org.jetbrains.kotlin.KtRealSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.inline.InlineScopeUtilsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.LogicOperationKind;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.descriptors.annotations.AnnotationUseSiteTarget;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirExpressionRef;
import org.jetbrains.kotlin.fir.FirFunctionTarget;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.builder.ConversionUtilsKt;
import org.jetbrains.kotlin.fir.contracts.FirContractDescription;
import org.jetbrains.kotlin.fir.contracts.FirLegacyRawContractDescription;
import org.jetbrains.kotlin.fir.contracts.builder.FirLegacyRawContractDescriptionBuilder;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.declarations.builder.FirAnonymousObjectBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirClassBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirPropertyAccessorBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirPropertyBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirReceiverParameterBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirRegularClassBuilder;
import org.jetbrains.kotlin.fir.declarations.builder.FirValueParameterBuilder;
import org.jetbrains.kotlin.fir.declarations.impl.FirDeclarationStatusImpl;
import org.jetbrains.kotlin.fir.declarations.impl.FirDefaultPropertyAccessor;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.DiagnosticKind;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression;
import org.jetbrains.kotlin.fir.expressions.FirArgumentUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirBooleanOperatorExpression;
import org.jetbrains.kotlin.fir.expressions.FirCall;
import org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess;
import org.jetbrains.kotlin.fir.expressions.FirCheckedSafeCallSubject;
import org.jetbrains.kotlin.fir.expressions.FirComparisonExpression;
import org.jetbrains.kotlin.fir.expressions.FirComponentCall;
import org.jetbrains.kotlin.fir.expressions.FirElvisExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCallOrigin;
import org.jetbrains.kotlin.fir.expressions.FirImplicitInvokeCall;
import org.jetbrains.kotlin.fir.expressions.FirLazyBlock;
import org.jetbrains.kotlin.fir.expressions.FirLazyExpression;
import org.jetbrains.kotlin.fir.expressions.FirOperation;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirSafeCallExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.builder.FirAnnotationCallBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirArgumentListBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirBooleanOperatorExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirCallableReferenceAccessBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirCheckedSafeCallSubjectBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirComparisonExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirComponentCallBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirConstExpressionBuilderKt;
import org.jetbrains.kotlin.fir.expressions.builder.FirElvisExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirFunctionCallBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirPropertyAccessExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirReturnExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirSafeCallExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirThisReceiverExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.builder.FirWrappedDelegateExpressionBuilder;
import org.jetbrains.kotlin.fir.expressions.impl.FirContractCallBlock;
import org.jetbrains.kotlin.fir.expressions.impl.FirSingleExpressionBlock;
import org.jetbrains.kotlin.fir.references.FirNamedReference;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.references.builder.FirDelegateFieldReferenceBuilder;
import org.jetbrains.kotlin.fir.references.builder.FirImplicitThisReferenceBuilder;
import org.jetbrains.kotlin.fir.references.builder.FirResolvedNamedReferenceBuilder;
import org.jetbrains.kotlin.fir.references.builder.FirSimpleNamedReferenceBuilder;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirDelegateFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertyAccessorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirReceiverParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjectionOut;
import org.jetbrains.kotlin.fir.types.ConeStarProjection;
import org.jetbrains.kotlin.fir.types.FirErrorTypeRef;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.TypeConstructionUtilsKt;
import org.jetbrains.kotlin.fir.types.builder.FirResolvedTypeRefBuilder;
import org.jetbrains.kotlin.fir.types.builder.FirTypeProjectionWithVarianceBuilder;
import org.jetbrains.kotlin.fir.types.impl.ConeTypeParameterTypeImpl;
import org.jetbrains.kotlin.fir.types.impl.FirImplicitTypeRefImplWithoutSource;
import org.jetbrains.kotlin.lexer.KtToken;
import org.jetbrains.kotlin.lexer.KtTokens;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.SpecialNames;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.types.ConstantValueKind;
import org.jetbrains.kotlin.types.Variance;
import org.jetbrains.kotlin.types.expressions.OperatorConventions;
import org.jetbrains.kotlin.util.OperatorNameConventions;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000à\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\u000e\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0002\u001a\u0010\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0007H\u0000\u001a\f\u0010\f\u001a\u0004\u0018\u00010\r*\u00020\u000e\u001a\f\u0010\u000f\u001a\u0004\u0018\u00010\r*\u00020\u000e\u001a\n\u0010\u0010\u001a\u00020\u0011*\u00020\u000e\u001a\f\u0010\u0012\u001a\u0004\u0018\u00010\u0011*\u00020\u000e\u001a\u001c\u0010\u0016\u001a\u00020\u0017*\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00182\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b\u001a$\u0010\u001c\u001a\u00020\u001d*\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b\u001a.\u0010 \u001a\u00020!*\u00020\u00182\u0006\u0010\"\u001a\u00020\u00182\u0006\u0010#\u001a\u00020\u001f2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\u0010$\u001a\u0004\u0018\u00010\u001b\u001a.\u0010%\u001a\u00020&*\u00020\u00182\u0006\u0010\"\u001a\u00020\u00182\u0006\u0010'\u001a\u00020\u000e2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\u0010$\u001a\u0004\u0018\u00010\u001b\u001a0\u0010(\u001a\u00020!*\u00020\u00182\b\u0010$\u001a\u0004\u0018\u00010\u001b2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\"\u001a\u00020\u00182\u0006\u0010)\u001a\u00020\rH\u0002\u001a\"\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010\u001b2\b\u0010-\u001a\u0004\u0018\u00010\u001b2\u0006\u0010.\u001a\u00020\r\u001a\u0018\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u00010\u001b2\u0006\u00102\u001a\u000203\u001a\u001c\u00104\u001a\u000205*\u0002032\b\u00106\u001a\u0004\u0018\u00010\u001b2\u0006\u00107\u001a\u000208\u001a¤\u0001\u0010?\u001a\u00020@\"\u0004\b\u0000\u0010A*\u00020B2\b\u0010C\u001a\u0004\u0018\u00010D2\u0006\u0010E\u001a\u00020F2\f\u00109\u001a\b\u0012\u0002\b\u0003\u0018\u00010:2\f\u0010G\u001a\b\u0012\u0004\u0012\u0002HA0H2\u0006\u0010I\u001a\u00020\u001f2\n\b\u0002\u0010J\u001a\u0004\u0018\u00010K2\n\b\u0002\u0010L\u001a\u0004\u0018\u00010M28\b\u0002\u0010N\u001a2\u0012\u0013\u0012\u00110P¢\u0006\f\bQ\u0012\b\b.\u0012\u0004\b\b(R\u0012\u0013\u0012\u00110S¢\u0006\f\bQ\u0012\b\b.\u0012\u0004\b\b(T\u0012\u0004\u0012\u00020@0O2\n\b\u0002\u0010U\u001a\u0004\u0018\u00010\u001b\u001a\u001a\u0010V\u001a\u0004\u0018\u00010W2\u0006\u0010X\u001a\u00020Y2\b\u0010Z\u001a\u0004\u0018\u00010[\u001a\u0016\u0010\\\u001a\u00020]*\u00020!2\n\b\u0002\u0010Z\u001a\u0004\u0018\u00010[\u001a\n\u0010^\u001a\u00020\u001f*\u00020Y\u001a\u001b\u0010_\u001a\u00020\u001f*\u00020`\u0082\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0000\u001a\u0004\u0010\u0000(\u0000\u001a\u0016\u0010a\u001a\u00020\u001f*\u00020\u00182\b\u0010.\u001a\u0004\u0018\u00010\u0002H\u0002\u001a\u001a\u0010b\u001a\u00020c*\u0002002\u0006\u0010d\u001a\u00020\u00182\u0006\u00101\u001a\u00020\u001b\u001a\n\u0010e\u001a\u00020\u0018*\u000200\u001aS\u0010e\u001a\u00020\u0018\"\b\b\u0000\u0010f*\u00020\u0018*\u0002Hf2\u0019\u0010g\u001a\u0015\u0012\u0004\u0012\u0002Hf\u0012\u0006\u0012\u0004\u0018\u00010\u00180h¢\u0006\u0002\bi2\u001d\u0010j\u001a\u0019\u0012\u0004\u0012\u0002Hf\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020@0O¢\u0006\u0002\bi¢\u0006\u0002\u0010k\u001a\n\u0010l\u001a\u00020\u001f*\u00020`\u001a\n\u0010l\u001a\u00020\u001f*\u00020\u001b\u001a\u001e\u0010m\u001a\b\u0012\u0004\u0012\u00020o0n*\b\u0012\u0004\u0012\u00020o0n2\u0006\u0010R\u001a\u00020p\u001a0\u0010q\u001a\u00020r*\u0006\u0012\u0002\b\u00030s2\f\u0010t\u001a\b\u0012\u0004\u0012\u00020v0u2\u0006\u0010E\u001a\u00020F2\n\u0010w\u001a\u0006\u0012\u0002\b\u00030x\u001a\u001e\u0010y\u001a\u00020r*\u00020\u001b2\u0006\u0010E\u001a\u00020F2\n\u0010w\u001a\u0006\u0012\u0002\b\u00030x\u001a\u001e\u0010z\u001a\u00020@\"\u0004\b\u0000\u0010A*\u00020{2\f\u0010G\u001a\b\u0012\u0004\u0012\u0002HA0H\u001a\u001c\u0010|\u001a\u0004\u0018\u00010}\"\u0004\b\u0000\u0010A2\f\u0010G\u001a\b\u0012\u0004\u0012\u0002HA0H\u001a,\u0010\u0081\u0001\u001a\u00020\u00182\r\u0010\u0082\u0001\u001a\b\u0012\u0004\u0012\u00020\u00180n2\t\b\u0002\u0010\u0083\u0001\u001a\u0002082\t\b\u0002\u0010\u0084\u0001\u001a\u000208\u001a\u0016\u0010\u0085\u0001\u001a\u00020\u0018*\u00020\u00182\t\u0010\u0086\u0001\u001a\u0004\u0018\u00010\u0018\u001a\r\u0010\u0087\u0001\u001a\u00020\u001f*\u0004\u0018\u00010p\u001a\r\u0010\u0088\u0001\u001a\u00030\u0089\u0001*\u00030\u008a\u0001\u001a+\u0010\u008b\u0001\u001a\u00020\u001f2\u0007\u0010\u008c\u0001\u001a\u00020\u001f2\u0007\u0010\u008d\u0001\u001a\u00020\u001f2\u0007\u0010\u008e\u0001\u001a\u00020\u001f2\u0007\u0010\u008f\u0001\u001a\u00020\u001f\"*\u0010\b\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\tj\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0001`\nX\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\":\u0010\u0013\u001a.\u0012\f\u0012\n \u0015*\u0004\u0018\u00010\u00140\u0014\u0012\u0004\u0012\u00020\u00110\tj\u0016\u0012\f\u0012\n \u0015*\u0004\u0018\u00010\u00140\u0014\u0012\u0004\u0012\u00020\u0011`\nX\u0082\u0004¢\u0006\u0002\n\u0000\"\u001b\u00109\u001a\b\u0012\u0004\u0012\u00020;0:*\u00020<8F¢\u0006\u0006\u001a\u0004\b=\u0010>\"\u0016\u0010~\u001a\u00020\u001f*\u00020\u007f8F¢\u0006\u0007\u001a\u0005\b~\u0010\u0080\u0001ò\u0001\u0004\n\u00020!¨\u0006\u0090\u0001"}, d2 = {"parseCharacter", "Lorg/jetbrains/kotlin/fir/builder/CharacterWithDiagnostic;", Argument.Delimiters.none, "escapedStringToCharacter", "text", "translateEscape", "c", Argument.Delimiters.none, "escapeCharToChartedWithDiagnosticMap", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "illegalEscapeDiagnostic", "toBinaryName", "Lorg/jetbrains/kotlin/name/Name;", "Lcom/intellij/psi/tree/IElementType;", "toUnaryName", "toFirOperation", "Lorg/jetbrains/kotlin/fir/expressions/FirOperation;", "toFirOperationOrNull", "ktTokenToFirOperationMap", "Lorg/jetbrains/kotlin/lexer/KtToken;", "kotlin.jvm.PlatformType", "generateNotNullOrOther", "Lorg/jetbrains/kotlin/fir/expressions/FirElvisExpression;", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "other", "baseSource", "Lorg/jetbrains/kotlin/KtSourceElement;", "generateLazyLogicalOperation", "Lorg/jetbrains/kotlin/fir/expressions/FirBooleanOperatorExpression;", "isAnd", Argument.Delimiters.none, "generateContainsOperation", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "argument", "inverted", "operationReferenceSource", "generateComparisonExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirComparisonExpression;", "operatorToken", "createConventionCall", "conventionName", "generateAccessExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;", "qualifiedSource", "calleeReferenceSource", ModuleXmlParser.NAME, "generateResolvedAccessExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "source", "variable", "Lorg/jetbrains/kotlin/fir/declarations/FirVariable;", "toComponentCall", "Lorg/jetbrains/kotlin/fir/expressions/FirComponentCall;", "entrySource", "index", Argument.Delimiters.none, "ownerRegularOrAnonymousObjectSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirClassBuilder;", "getOwnerRegularOrAnonymousObjectSymbol", "(Lorg/jetbrains/kotlin/fir/declarations/builder/FirClassBuilder;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "generateAccessorsByDelegate", Argument.Delimiters.none, "T", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirPropertyBuilder;", "delegateBuilder", "Lorg/jetbrains/kotlin/fir/expressions/builder/FirWrappedDelegateExpressionBuilder;", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "context", "Lorg/jetbrains/kotlin/fir/builder/Context;", "isExtension", "lazyDelegateExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirLazyExpression;", "lazyBodyForGeneratedAccessors", "Lorg/jetbrains/kotlin/fir/expressions/FirLazyBlock;", "bindFunction", "Lkotlin/Function2;", "Lorg/jetbrains/kotlin/fir/FirFunctionTarget;", "Lkotlin/ParameterName;", "target", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "function", "explicitDeclarationSource", "processLegacyContractDescription", "Lorg/jetbrains/kotlin/fir/contracts/FirContractDescription;", "block", "Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "diagnostic", "Lorg/jetbrains/kotlin/fir/diagnostics/ConeDiagnostic;", "toLegacyRawContractDescription", "Lorg/jetbrains/kotlin/fir/contracts/FirLegacyRawContractDescription;", "isContractPresentFirCheck", "isContractBlockFirCheck", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "checkReceiver", "createSafeCall", "Lorg/jetbrains/kotlin/fir/expressions/FirSafeCallExpression;", "receiver", "pullUpSafeCallIfNecessary", "F", "obtainReceiver", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "replaceReceiver", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;)Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "isChildInParentheses", "filterUseSiteTarget", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationCall;", "Lorg/jetbrains/kotlin/descriptors/annotations/AnnotationUseSiteTarget;", "createReceiverParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirReceiverParameter;", "Lorg/jetbrains/kotlin/fir/builder/AbstractRawFirBuilder;", "typeRefCalculator", "Lkotlin/Function0;", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "containingCallableSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "asReceiverParameter", "initContainingClassAttr", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "currentDispatchReceiverType", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "isUnderscore", Argument.Delimiters.none, "(Ljava/lang/CharSequence;)Z", "buildBalancedOrExpressionTree", "conditions", "lower", "upper", "guardedBy", "guard", "appliesToPrimaryConstructorParameter", "wrapIntoArray", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "Lorg/jetbrains/kotlin/fir/types/FirErrorTypeRef;", "shouldGenerateDelegatedSuperCall", "isAnySuperCall", "isExpectClass", "isEnumEntry", "hasExplicitDelegatedCalls", "org.jetbrains.kotlin:raw-fir.common"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConversionUtilsKt {
    private static final HashMap<Character, CharacterWithDiagnostic> escapeCharToChartedWithDiagnosticMap = MapsKt.hashMapOf(new Pair[]{TuplesKt.to('t', new CharacterWithDiagnostic('\t')), TuplesKt.to('b', new CharacterWithDiagnostic('\b')), TuplesKt.to('n', new CharacterWithDiagnostic('\n')), TuplesKt.to('r', new CharacterWithDiagnostic('\r')), TuplesKt.to('\'', new CharacterWithDiagnostic('\'')), TuplesKt.to('\"', new CharacterWithDiagnostic('\"')), TuplesKt.to(Character.valueOf(InlineScopeUtilsKt.INLINE_SCOPE_NUMBER_SEPARATOR), new CharacterWithDiagnostic(InlineScopeUtilsKt.INLINE_SCOPE_NUMBER_SEPARATOR)), TuplesKt.to('$', new CharacterWithDiagnostic('$'))});
    private static final CharacterWithDiagnostic illegalEscapeDiagnostic = new CharacterWithDiagnostic(DiagnosticKind.IllegalEscape);
    private static final HashMap<KtToken, FirOperation> ktTokenToFirOperationMap = MapsKt.hashMapOf(new Pair[]{TuplesKt.to(KtTokens.LT, FirOperation.LT), TuplesKt.to(KtTokens.GT, FirOperation.GT), TuplesKt.to(KtTokens.LTEQ, FirOperation.LT_EQ), TuplesKt.to(KtTokens.GTEQ, FirOperation.GT_EQ), TuplesKt.to(KtTokens.EQEQ, FirOperation.EQ), TuplesKt.to(KtTokens.EXCLEQ, FirOperation.NOT_EQ), TuplesKt.to(KtTokens.EQEQEQ, FirOperation.IDENTITY), TuplesKt.to(KtTokens.EXCLEQEQEQ, FirOperation.NOT_IDENTITY), TuplesKt.to(KtTokens.EQ, FirOperation.ASSIGN), TuplesKt.to(KtTokens.PLUSEQ, FirOperation.PLUS_ASSIGN), TuplesKt.to(KtTokens.MINUSEQ, FirOperation.MINUS_ASSIGN), TuplesKt.to(KtTokens.MULTEQ, FirOperation.TIMES_ASSIGN), TuplesKt.to(KtTokens.DIVEQ, FirOperation.DIV_ASSIGN), TuplesKt.to(KtTokens.PERCEQ, FirOperation.REM_ASSIGN), TuplesKt.to(KtTokens.IS_KEYWORD, FirOperation.IS), TuplesKt.to(KtTokens.NOT_IS, FirOperation.NOT_IS), TuplesKt.to(KtTokens.AS_KEYWORD, FirOperation.AS), TuplesKt.to(KtTokens.AS_SAFE, FirOperation.SAFE_AS)});

    public static FirStatement a(FirFunctionCall firFunctionCall) {
        firFunctionCall.getClass();
        return new FirContractCallBlock(firFunctionCall);
    }

    public static final boolean appliesToPrimaryConstructorParameter(AnnotationUseSiteTarget annotationUseSiteTarget) {
        return annotationUseSiteTarget == null || annotationUseSiteTarget == AnnotationUseSiteTarget.CONSTRUCTOR_PARAMETER || annotationUseSiteTarget == AnnotationUseSiteTarget.RECEIVER || annotationUseSiteTarget == AnnotationUseSiteTarget.FILE || annotationUseSiteTarget == AnnotationUseSiteTarget.ALL;
    }

    public static final FirReceiverParameter asReceiverParameter(KtSourceElement ktSourceElement, FirModuleData firModuleData, FirCallableSymbol<?> firCallableSymbol) {
        ktSourceElement.getClass();
        firModuleData.getClass();
        firCallableSymbol.getClass();
        FirReceiverParameterBuilder firReceiverParameterBuilder = new FirReceiverParameterBuilder();
        firReceiverParameterBuilder.setSource(KtSourceElementKt.fakeElement$default(ktSourceElement, KtFakeSourceElementKind.ReceiverFromType.INSTANCE, null, 2, null));
        firReceiverParameterBuilder.setTypeRef(FirImplicitTypeRefImplWithoutSource.INSTANCE);
        firReceiverParameterBuilder.setSymbol(new FirReceiverParameterSymbol());
        firReceiverParameterBuilder.setModuleData(firModuleData);
        firReceiverParameterBuilder.setOrigin(FirDeclarationOrigin.Source.INSTANCE);
        firReceiverParameterBuilder.setContainingDeclarationSymbol(firCallableSymbol);
        return firReceiverParameterBuilder.mo288build();
    }

    public static final FirExpression buildBalancedOrExpressionTree(List<? extends FirExpression> list, int i, int i2) {
        list.getClass();
        int i3 = (((i2 - i) + 1) / 2) + i;
        if (i == i2) {
            return list.get(i3);
        }
        FirExpression firExpressionBuildBalancedOrExpressionTree = buildBalancedOrExpressionTree(list, i, i3 - 1);
        FirExpression firExpressionBuildBalancedOrExpressionTree2 = buildBalancedOrExpressionTree(list, i3, i2);
        KtSourceElement source = firExpressionBuildBalancedOrExpressionTree.getSource();
        if (source == null) {
            source = firExpressionBuildBalancedOrExpressionTree2.getSource();
        }
        return generateLazyLogicalOperation(firExpressionBuildBalancedOrExpressionTree, firExpressionBuildBalancedOrExpressionTree2, false, source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.WhenCondition.INSTANCE, null, 2, null) : null);
    }

    public static /* synthetic */ FirExpression buildBalancedOrExpressionTree$default(List list, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = CollectionsKt.getLastIndex(list);
        }
        return buildBalancedOrExpressionTree(list, i, i2);
    }

    private static final boolean checkReceiver(FirExpression firExpression, String str) {
        Name name;
        String strAsString;
        if (!(firExpression instanceof FirQualifiedAccessExpression)) {
            return false;
        }
        FirExpression explicitReceiver = ((FirQualifiedAccessExpression) firExpression).getExplicitReceiver();
        FirQualifiedAccessExpression firQualifiedAccessExpression = explicitReceiver instanceof FirQualifiedAccessExpression ? (FirQualifiedAccessExpression) explicitReceiver : null;
        if (firQualifiedAccessExpression == null) {
            return false;
        }
        FirReference calleeReference = firQualifiedAccessExpression.getCalleeReference();
        FirNamedReference firNamedReference = calleeReference instanceof FirNamedReference ? (FirNamedReference) calleeReference : null;
        if (firNamedReference == null || (name = firNamedReference.getName()) == null || (strAsString = name.asString()) == null) {
            return false;
        }
        return Intrinsics.areEqual(strAsString, str);
    }

    private static final FirFunctionCall createConventionCall(FirExpression firExpression, KtSourceElement ktSourceElement, KtSourceElement ktSourceElement2, FirExpression firExpression2, Name name) {
        FirFunctionCallBuilder firFunctionCallBuilder = new FirFunctionCallBuilder();
        firFunctionCallBuilder.setSource(ktSourceElement2);
        FirSimpleNamedReferenceBuilder firSimpleNamedReferenceBuilder = new FirSimpleNamedReferenceBuilder();
        firSimpleNamedReferenceBuilder.setSource(ktSourceElement);
        firSimpleNamedReferenceBuilder.setName(name);
        firFunctionCallBuilder.setCalleeReference(firSimpleNamedReferenceBuilder.build());
        firFunctionCallBuilder.setExplicitReceiver(firExpression);
        firFunctionCallBuilder.setArgumentList(FirArgumentUtilKt.buildUnaryArgumentList(firExpression2));
        firFunctionCallBuilder.setOrigin(FirFunctionCallOrigin.Operator);
        return firFunctionCallBuilder.mo288build();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
    public static final FirReceiverParameter createReceiverParameter(AbstractRawFirBuilder<?> abstractRawFirBuilder, Function0<? extends FirTypeRef> function0, FirModuleData firModuleData, FirCallableSymbol<?> firCallableSymbol) throws KotlinIllegalStateExceptionWithAttachments, UninitializedPropertyAccessException {
        abstractRawFirBuilder.getClass();
        function0.getClass();
        firModuleData.getClass();
        firCallableSymbol.getClass();
        FirReceiverParameterBuilder firReceiverParameterBuilder = new FirReceiverParameterBuilder();
        firReceiverParameterBuilder.setSymbol(new FirReceiverParameterSymbol());
        FirReceiverParameterSymbol symbol = firReceiverParameterBuilder.getSymbol();
        abstractRawFirBuilder.getContext().pushContainerSymbol(symbol);
        try {
            FirTypeRef firTypeRef = (FirTypeRef) function0.invoke();
            KtSourceElement source = firTypeRef.getSource();
            firReceiverParameterBuilder.setSource(source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.ReceiverFromType.INSTANCE, null, 2, null) : null);
            List<FirAnnotation> annotations = firReceiverParameterBuilder.getAnnotations();
            List<FirAnnotation> annotations2 = firTypeRef.getAnnotations();
            annotations2.getClass();
            CollectionsKt.addAll(annotations, filterUseSiteTarget(annotations2, AnnotationUseSiteTarget.RECEIVER));
            List<FirAnnotation> annotations3 = firTypeRef.getAnnotations();
            ArrayList arrayList = new ArrayList();
            for (Object obj : annotations3) {
                if (((FirAnnotation) obj).getUseSiteTarget() != AnnotationUseSiteTarget.RECEIVER) {
                    arrayList.add(obj);
                }
            }
            if (arrayList.size() != firTypeRef.getAnnotations().size()) {
                firTypeRef.replaceAnnotations(arrayList);
            }
            firReceiverParameterBuilder.setTypeRef(firTypeRef);
            firReceiverParameterBuilder.setModuleData(firModuleData);
            firReceiverParameterBuilder.setOrigin(FirDeclarationOrigin.Source.INSTANCE);
            firReceiverParameterBuilder.setContainingDeclarationSymbol(firCallableSymbol);
            Unit unit = Unit.INSTANCE;
            return firReceiverParameterBuilder.mo288build();
        } finally {
            abstractRawFirBuilder.getContext().popContainerSymbol(symbol);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final FirSafeCallExpression createSafeCall(FirQualifiedAccessExpression firQualifiedAccessExpression, FirExpression firExpression, KtSourceElement ktSourceElement) {
        firQualifiedAccessExpression.getClass();
        firExpression.getClass();
        ktSourceElement.getClass();
        FirCheckedSafeCallSubjectBuilder firCheckedSafeCallSubjectBuilder = new FirCheckedSafeCallSubjectBuilder();
        FirExpressionRef<FirExpression> firExpressionRef = new FirExpressionRef<>();
        firExpressionRef.bind(firExpression);
        firCheckedSafeCallSubjectBuilder.setOriginalReceiverRef(firExpressionRef);
        KtSourceElement source = firExpression.getSource();
        firCheckedSafeCallSubjectBuilder.setSource(source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.CheckedSafeCallSubject.INSTANCE, null, 2, null) : null);
        FirCheckedSafeCallSubject firCheckedSafeCallSubjectMo289build = firCheckedSafeCallSubjectBuilder.mo288build();
        if (firQualifiedAccessExpression instanceof FirImplicitInvokeCall) {
            FirArgumentListBuilder firArgumentListBuilder = new FirArgumentListBuilder();
            firArgumentListBuilder.getArguments().add(firCheckedSafeCallSubjectMo289build);
            firArgumentListBuilder.getArguments().addAll(((FirCall) firQualifiedAccessExpression).getArgumentList().getArguments());
            ((FirImplicitInvokeCall) firQualifiedAccessExpression).replaceArgumentList(firArgumentListBuilder.build());
        } else {
            firQualifiedAccessExpression.replaceExplicitReceiver(firCheckedSafeCallSubjectMo289build);
        }
        FirSafeCallExpressionBuilder firSafeCallExpressionBuilder = new FirSafeCallExpressionBuilder();
        firSafeCallExpressionBuilder.setReceiver(firExpression);
        FirExpressionRef<FirCheckedSafeCallSubject> firExpressionRef2 = new FirExpressionRef<>();
        firExpressionRef2.bind(firCheckedSafeCallSubjectMo289build);
        firSafeCallExpressionBuilder.setCheckedSubjectRef(firExpressionRef2);
        firSafeCallExpressionBuilder.setSelector(firQualifiedAccessExpression);
        firSafeCallExpressionBuilder.setSource(ktSourceElement);
        return firSafeCallExpressionBuilder.mo288build();
    }

    public static final <T> ConeClassLikeType currentDispatchReceiverType(Context<T> context) {
        context.getClass();
        return (ConeClassLikeType) CollectionsKt.lastOrNull(context.getDispatchReceiverTypesStack());
    }

    public static final CharacterWithDiagnostic escapedStringToCharacter(String str) {
        Integer intOrNull;
        str.getClass();
        if (str.length() > 0) {
            str.charAt(0);
        }
        String strSubstring = str.substring(1);
        int length = strSubstring.length();
        if (length == 0) {
            return illegalEscapeDiagnostic;
        }
        if (length != 1) {
            return (length == 5 && strSubstring.charAt(0) == 'u' && (intOrNull = StringsKt.toIntOrNull(strSubstring.substring(1), 16)) != null) ? new CharacterWithDiagnostic((char) intOrNull.intValue()) : illegalEscapeDiagnostic;
        }
        return translateEscape(strSubstring.charAt(0));
    }

    public static final List<FirAnnotationCall> filterUseSiteTarget(List<? extends FirAnnotationCall> list, AnnotationUseSiteTarget annotationUseSiteTarget) {
        list.getClass();
        annotationUseSiteTarget.getClass();
        ArrayList arrayList = new ArrayList();
        for (FirAnnotationCall firAnnotationCall : list) {
            KtSourceElement ktSourceElementMo289build = null;
            if (firAnnotationCall.getUseSiteTarget() == annotationUseSiteTarget) {
                FirAnnotationCallBuilder firAnnotationCallBuilder = new FirAnnotationCallBuilder();
                firAnnotationCallBuilder.setSource(firAnnotationCall.getSource());
                firAnnotationCallBuilder.setUseSiteTarget(firAnnotationCall.getUseSiteTarget());
                firAnnotationCallBuilder.setAnnotationTypeRef(firAnnotationCall.getAnnotationTypeRef());
                firAnnotationCallBuilder.getTypeArguments().addAll(firAnnotationCall.getTypeArguments());
                firAnnotationCallBuilder.setArgumentList(firAnnotationCall.getArgumentList());
                firAnnotationCallBuilder.setCalleeReference(firAnnotationCall.getCalleeReference());
                firAnnotationCallBuilder.setArgumentMapping(firAnnotationCall.getArgumentMapping());
                firAnnotationCallBuilder.setAnnotationResolvePhase(firAnnotationCall.getAnnotationResolvePhase());
                firAnnotationCallBuilder.setContainingDeclarationSymbol(firAnnotationCall.getContainingDeclarationSymbol());
                KtSourceElement source = firAnnotationCall.getSource();
                firAnnotationCallBuilder.setSource(source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.FromUseSiteTarget.INSTANCE, null, 2, null) : null);
                ktSourceElementMo289build = firAnnotationCallBuilder.mo288build();
            }
            if (ktSourceElementMo289build != null) {
                arrayList.add(ktSourceElementMo289build);
            }
        }
        return arrayList;
    }

    public static final FirPropertyAccessExpression generateAccessExpression(KtSourceElement ktSourceElement, KtSourceElement ktSourceElement2, Name name) {
        name.getClass();
        FirPropertyAccessExpressionBuilder firPropertyAccessExpressionBuilder = new FirPropertyAccessExpressionBuilder();
        firPropertyAccessExpressionBuilder.setSource(ktSourceElement);
        FirSimpleNamedReferenceBuilder firSimpleNamedReferenceBuilder = new FirSimpleNamedReferenceBuilder();
        if (Intrinsics.areEqual(ktSourceElement2, ktSourceElement)) {
            ktSourceElement2 = ktSourceElement2 != null ? KtSourceElementKt.fakeElement$default(ktSourceElement2, KtFakeSourceElementKind.ReferenceInAtomicQualifiedAccess.INSTANCE, null, 2, null) : null;
        }
        firSimpleNamedReferenceBuilder.setSource(ktSourceElement2);
        firSimpleNamedReferenceBuilder.setName(name);
        firPropertyAccessExpressionBuilder.setCalleeReference(firSimpleNamedReferenceBuilder.build());
        return firPropertyAccessExpressionBuilder.mo288build();
    }

    /* JADX WARN: Code duplicated, block: B:46:0x00f5  */
    /* JADX WARN: Code duplicated, block: B:94:0x0243  */
    public static final <T> void generateAccessorsByDelegate(FirPropertyBuilder firPropertyBuilder, FirWrappedDelegateExpressionBuilder firWrappedDelegateExpressionBuilder, FirModuleData firModuleData, FirClassSymbol<?> firClassSymbol, Context<T> context, boolean z, FirLazyExpression firLazyExpression, FirLazyBlock firLazyBlock, Function2<? super FirFunctionTarget, ? super FirFunction, Unit> function2, KtSourceElement ktSourceElement) {
        KtSourceElement ktSourceElement2;
        boolean z2;
        FirExpression firExpressionMo289build;
        KtSourceElement source;
        Visibility visibility;
        FirDelegateFieldSymbol firDelegateFieldSymbol;
        Context<T> context2;
        FirFunctionTarget firFunctionTarget;
        FirBlock firSingleExpressionBlock;
        KtSourceElement ktSourceElement3;
        Visibility visibility2;
        FirBlock firSingleExpressionBlock2;
        KtSourceElement source2;
        List<FirValueParameter> valueParameters;
        FirValueParameter firValueParameter;
        KtSourceElement ktSourceElementFakeElement$default;
        FirPropertyBuilder firPropertyBuilder2 = firPropertyBuilder;
        firPropertyBuilder2.getClass();
        firModuleData.getClass();
        context.getClass();
        function2.getClass();
        if (firWrappedDelegateExpressionBuilder == null) {
            return;
        }
        FirDelegateFieldSymbol firDelegateFieldSymbol2 = new FirDelegateFieldSymbol(firPropertyBuilder2.getSymbol());
        firPropertyBuilder2.setDelegateFieldSymbol(firDelegateFieldSymbol2);
        boolean z3 = firClassSymbol != null;
        KtSourceElement source3 = firWrappedDelegateExpressionBuilder.getSource();
        KtSourceElement ktSourceElementFakeElement$default2 = source3 != null ? KtSourceElementKt.fakeElement$default(source3, KtFakeSourceElementKind.DelegatedPropertyAccessor.INSTANCE, null, 2, null) : null;
        KtSourceElement ktSourceElement4 = (ktSourceElement == null || (ktSourceElementFakeElement$default = KtSourceElementKt.fakeElement$default(ktSourceElement, KtFakeSourceElementKind.DelegatedPropertyAccessor.INSTANCE, null, 2, null)) == null) ? ktSourceElementFakeElement$default2 : ktSourceElementFakeElement$default;
        boolean zIsVar = firPropertyBuilder2.isVar();
        if (firLazyExpression != null) {
            z2 = z;
            ktSourceElement2 = ktSourceElementFakeElement$default2;
            firExpressionMo289build = firLazyExpression;
        } else {
            FirFunctionCallBuilder firFunctionCallBuilder = new FirFunctionCallBuilder();
            firFunctionCallBuilder.setExplicitReceiver(firWrappedDelegateExpressionBuilder.getExpression());
            FirSimpleNamedReferenceBuilder firSimpleNamedReferenceBuilder = new FirSimpleNamedReferenceBuilder();
            firSimpleNamedReferenceBuilder.setSource(ktSourceElementFakeElement$default2);
            firSimpleNamedReferenceBuilder.setName(OperatorNameConventions.PROVIDE_DELEGATE);
            firFunctionCallBuilder.setCalleeReference(firSimpleNamedReferenceBuilder.build());
            ktSourceElement2 = ktSourceElementFakeElement$default2;
            z2 = z;
            firFunctionCallBuilder.setArgumentList(FirArgumentUtilKt.buildBinaryArgumentList(generateAccessorsByDelegate$thisRef(z2, firClassSymbol, ktSourceElement2, firPropertyBuilder2, context, true), generateAccessorsByDelegate$propertyRef(ktSourceElement2, z3, z2, zIsVar, firPropertyBuilder2)));
            firFunctionCallBuilder.setOrigin(FirFunctionCallOrigin.Operator);
            firFunctionCallBuilder.setSource(ktSourceElement2);
            firWrappedDelegateExpressionBuilder.setProvideDelegateCall(firFunctionCallBuilder.mo288build());
            firExpressionMo289build = firWrappedDelegateExpressionBuilder.mo288build();
        }
        firPropertyBuilder2.setDelegate(firExpressionMo289build);
        if (firPropertyBuilder2.getGetter() == null || (firPropertyBuilder2.getGetter() instanceof FirDefaultPropertyAccessor)) {
            FirPropertyAccessor getter = firPropertyBuilder2.getGetter();
            List<FirAnnotation> annotations = getter != null ? getter.getAnnotations() : null;
            FirFunctionTarget firFunctionTarget2 = new FirFunctionTarget(null, false);
            FirPropertyAccessor getter2 = firPropertyBuilder2.getGetter();
            FirDeclarationStatus status = getter2 != null ? getter2.getStatus() : null;
            FirPropertyAccessor getter3 = firPropertyBuilder2.getGetter();
            if (getter3 == null || (source = getter3.getSource()) == null) {
                source = ktSourceElement4;
            } else {
                if (!Intrinsics.areEqual(source.getKind(), KtRealSourceElementKind.INSTANCE)) {
                    source = null;
                }
                if (source == null) {
                    source = ktSourceElement4;
                }
            }
            FirPropertyAccessorBuilder firPropertyAccessorBuilder = new FirPropertyAccessorBuilder();
            firPropertyAccessorBuilder.setSource(source);
            firPropertyAccessorBuilder.setModuleData(firModuleData);
            firPropertyAccessorBuilder.setOrigin(FirDeclarationOrigin.Source.INSTANCE);
            firPropertyAccessorBuilder.setReturnTypeRef(FirImplicitTypeRefImplWithoutSource.INSTANCE);
            firPropertyAccessorBuilder.setGetter(true);
            if (status == null || (visibility = status.getVisibility()) == null) {
                visibility = Visibilities.Unknown.INSTANCE;
            }
            FirDeclarationStatusImpl firDeclarationStatusImpl = new FirDeclarationStatusImpl(visibility, Modality.FINAL);
            firDeclarationStatusImpl.setInline(status != null ? status.isInline() : firDeclarationStatusImpl.isInline());
            firPropertyAccessorBuilder.setStatus(firDeclarationStatusImpl);
            firPropertyAccessorBuilder.setSymbol(new FirPropertyAccessorSymbol());
            if (firLazyBlock != null) {
                context2 = context;
                firSingleExpressionBlock = firLazyBlock;
                firDelegateFieldSymbol = firDelegateFieldSymbol2;
                firFunctionTarget = firFunctionTarget2;
            } else {
                FirReturnExpressionBuilder firReturnExpressionBuilder = new FirReturnExpressionBuilder();
                FirFunctionCallBuilder firFunctionCallBuilder2 = new FirFunctionCallBuilder();
                firFunctionCallBuilder2.setSource(ktSourceElement2);
                boolean z4 = z2;
                KtSourceElement ktSourceElement5 = ktSourceElement2;
                firDelegateFieldSymbol = firDelegateFieldSymbol2;
                ktSourceElement2 = ktSourceElement5;
                firFunctionCallBuilder2.setExplicitReceiver(generateAccessorsByDelegate$delegateAccess(ktSourceElement5, firClassSymbol, firDelegateFieldSymbol2, z4, firPropertyBuilder2, context));
                FirSimpleNamedReferenceBuilder firSimpleNamedReferenceBuilder2 = new FirSimpleNamedReferenceBuilder();
                firSimpleNamedReferenceBuilder2.setSource(ktSourceElement2);
                firSimpleNamedReferenceBuilder2.setName(OperatorNameConventions.GET_VALUE);
                firFunctionCallBuilder2.setCalleeReference(firSimpleNamedReferenceBuilder2.build());
                firPropertyBuilder2 = firPropertyBuilder;
                context2 = context;
                firFunctionTarget = firFunctionTarget2;
                firFunctionCallBuilder2.setArgumentList(FirArgumentUtilKt.buildBinaryArgumentList(generateAccessorsByDelegate$thisRef$default(z, firClassSymbol, ktSourceElement2, firPropertyBuilder2, context2, false, 32, null), generateAccessorsByDelegate$propertyRef(ktSourceElement2, z3, z, zIsVar, firPropertyBuilder2)));
                firFunctionCallBuilder2.setOrigin(FirFunctionCallOrigin.Operator);
                firReturnExpressionBuilder.setResult(firFunctionCallBuilder2.mo288build());
                firReturnExpressionBuilder.setTarget(firFunctionTarget);
                firReturnExpressionBuilder.setSource(ktSourceElement2);
                firSingleExpressionBlock = new FirSingleExpressionBlock(firReturnExpressionBuilder.mo288build());
            }
            firPropertyAccessorBuilder.setBody(firSingleExpressionBlock);
            if (annotations != 0) {
                firPropertyAccessorBuilder.getAnnotations().addAll(annotations);
            }
            firPropertyAccessorBuilder.setPropertySymbol(firPropertyBuilder2.getSymbol());
            FirPropertyAccessor firPropertyAccessorMo289build = firPropertyAccessorBuilder.mo288build();
            function2.invoke(firFunctionTarget, firPropertyAccessorMo289build);
            initContainingClassAttr(firPropertyAccessorMo289build, context2);
            firPropertyBuilder2.setGetter(firPropertyAccessorMo289build);
        } else {
            context2 = context;
            firDelegateFieldSymbol = firDelegateFieldSymbol2;
            ktSourceElement4 = ktSourceElement4;
        }
        if (zIsVar) {
            if (firPropertyBuilder2.getSetter() == null || (firPropertyBuilder2.getSetter() instanceof FirDefaultPropertyAccessor)) {
                FirPropertyAccessor setter = firPropertyBuilder2.getSetter();
                List<FirAnnotation> annotations2 = setter != null ? setter.getAnnotations() : null;
                FirFunctionTarget firFunctionTarget3 = new FirFunctionTarget(null, false);
                FirPropertyAccessor setter2 = firPropertyBuilder2.getSetter();
                List<FirAnnotation> annotations3 = (setter2 == null || (valueParameters = setter2.getValueParameters()) == null || (firValueParameter = (FirValueParameter) CollectionsKt.firstOrNull(valueParameters)) == null) ? null : firValueParameter.getAnnotations();
                FirPropertyAccessor setter3 = firPropertyBuilder2.getSetter();
                FirDeclarationStatus status2 = setter3 != null ? setter3.getStatus() : null;
                FirPropertyAccessor setter4 = firPropertyBuilder2.getSetter();
                if (setter4 == null || (source2 = setter4.getSource()) == null) {
                    ktSourceElement3 = ktSourceElement4;
                } else {
                    ktSourceElement3 = source2.getKind() instanceof KtRealSourceElementKind ? source2 : null;
                    if (ktSourceElement3 == null) {
                        ktSourceElement3 = ktSourceElement4;
                    }
                }
                FirPropertyAccessorBuilder firPropertyAccessorBuilder2 = new FirPropertyAccessorBuilder();
                firPropertyAccessorBuilder2.setSource(ktSourceElement3);
                firPropertyAccessorBuilder2.setModuleData(firModuleData);
                FirDeclarationOrigin.Source source4 = FirDeclarationOrigin.Source.INSTANCE;
                firPropertyAccessorBuilder2.setOrigin(source4);
                firPropertyAccessorBuilder2.setReturnTypeRef(firModuleData.getSession().getBuiltinTypes().getUnitType());
                firPropertyAccessorBuilder2.setGetter(false);
                if (status2 == null || (visibility2 = status2.getVisibility()) == null) {
                    visibility2 = Visibilities.Unknown.INSTANCE;
                }
                FirDeclarationStatusImpl firDeclarationStatusImpl2 = new FirDeclarationStatusImpl(visibility2, Modality.FINAL);
                firDeclarationStatusImpl2.setInline(status2 != null ? status2.isInline() : firDeclarationStatusImpl2.isInline());
                firPropertyAccessorBuilder2.setStatus(firDeclarationStatusImpl2);
                firPropertyAccessorBuilder2.setSymbol(new FirPropertyAccessorSymbol());
                FirValueParameterBuilder firValueParameterBuilder = new FirValueParameterBuilder();
                firValueParameterBuilder.setSource(ktSourceElement4);
                firValueParameterBuilder.setContainingDeclarationSymbol(firPropertyAccessorBuilder2.getSymbol());
                firValueParameterBuilder.setModuleData(firModuleData);
                firValueParameterBuilder.setOrigin(source4);
                firValueParameterBuilder.setReturnTypeRef(FirImplicitTypeRefImplWithoutSource.INSTANCE);
                Name name = SpecialNames.IMPLICIT_SET_PARAMETER;
                firValueParameterBuilder.setName(name);
                firValueParameterBuilder.setSymbol(new FirValueParameterSymbol());
                firValueParameterBuilder.setCrossinline(false);
                firValueParameterBuilder.setNoinline(false);
                firValueParameterBuilder.setVararg(false);
                if (annotations3 != null) {
                    firValueParameterBuilder.getAnnotations().addAll(annotations3);
                }
                FirValueParameter firValueParameterMo289build = firValueParameterBuilder.mo288build();
                firPropertyAccessorBuilder2.getValueParameters().add(firValueParameterMo289build);
                if (firLazyBlock != null) {
                    firSingleExpressionBlock2 = firLazyBlock;
                } else {
                    FirReturnExpressionBuilder firReturnExpressionBuilder2 = new FirReturnExpressionBuilder();
                    FirFunctionCallBuilder firFunctionCallBuilder3 = new FirFunctionCallBuilder();
                    firFunctionCallBuilder3.setSource(ktSourceElement2);
                    KtSourceElement ktSourceElement6 = ktSourceElement2;
                    firFunctionCallBuilder3.setExplicitReceiver(generateAccessorsByDelegate$delegateAccess(ktSourceElement6, firClassSymbol, firDelegateFieldSymbol, z, firPropertyBuilder2, context2));
                    FirSimpleNamedReferenceBuilder firSimpleNamedReferenceBuilder3 = new FirSimpleNamedReferenceBuilder();
                    firSimpleNamedReferenceBuilder3.setSource(ktSourceElement6);
                    firSimpleNamedReferenceBuilder3.setName(OperatorNameConventions.SET_VALUE);
                    firFunctionCallBuilder3.setCalleeReference(firSimpleNamedReferenceBuilder3.build());
                    FirArgumentListBuilder firArgumentListBuilder = new FirArgumentListBuilder();
                    context2 = context;
                    firPropertyBuilder2 = firPropertyBuilder;
                    firArgumentListBuilder.getArguments().add(generateAccessorsByDelegate$thisRef$default(z, firClassSymbol, ktSourceElement6, firPropertyBuilder2, context2, false, 32, null));
                    firArgumentListBuilder.getArguments().add(generateAccessorsByDelegate$propertyRef(ktSourceElement6, z3, z, zIsVar, firPropertyBuilder2));
                    List<FirExpression> arguments = firArgumentListBuilder.getArguments();
                    FirPropertyAccessExpressionBuilder firPropertyAccessExpressionBuilder = new FirPropertyAccessExpressionBuilder();
                    firPropertyAccessExpressionBuilder.setSource(ktSourceElement6);
                    FirResolvedNamedReferenceBuilder firResolvedNamedReferenceBuilder = new FirResolvedNamedReferenceBuilder();
                    firResolvedNamedReferenceBuilder.setSource(ktSourceElement6);
                    firResolvedNamedReferenceBuilder.setName(name);
                    firResolvedNamedReferenceBuilder.setResolvedSymbol(firValueParameterMo289build.getSymbol());
                    firPropertyAccessExpressionBuilder.setCalleeReference(firResolvedNamedReferenceBuilder.build());
                    arguments.add(firPropertyAccessExpressionBuilder.mo288build());
                    firFunctionCallBuilder3.setArgumentList(firArgumentListBuilder.build());
                    firFunctionCallBuilder3.setOrigin(FirFunctionCallOrigin.Operator);
                    firReturnExpressionBuilder2.setResult(firFunctionCallBuilder3.mo288build());
                    firReturnExpressionBuilder2.setTarget(firFunctionTarget3);
                    firReturnExpressionBuilder2.setSource(ktSourceElement6);
                    firSingleExpressionBlock2 = new FirSingleExpressionBlock(firReturnExpressionBuilder2.mo288build());
                }
                firPropertyAccessorBuilder2.setBody(firSingleExpressionBlock2);
                if (annotations2 != 0) {
                    firPropertyAccessorBuilder2.getAnnotations().addAll(annotations2);
                }
                firPropertyAccessorBuilder2.setPropertySymbol(firPropertyBuilder2.getSymbol());
                FirPropertyAccessor firPropertyAccessorMo289build2 = firPropertyAccessorBuilder2.mo288build();
                function2.invoke(firFunctionTarget3, firPropertyAccessorMo289build2);
                initContainingClassAttr(firPropertyAccessorMo289build2, context2);
                firPropertyBuilder2.setSetter(firPropertyAccessorMo289build2);
            }
        }
    }

    public static /* synthetic */ void generateAccessorsByDelegate$default(FirPropertyBuilder firPropertyBuilder, FirWrappedDelegateExpressionBuilder firWrappedDelegateExpressionBuilder, FirModuleData firModuleData, FirClassSymbol firClassSymbol, Context context, boolean z, FirLazyExpression firLazyExpression, FirLazyBlock firLazyBlock, Function2 function2, KtSourceElement ktSourceElement, int i, Object obj) {
        if ((i & 32) != 0) {
            firLazyExpression = null;
        }
        if ((i & 64) != 0) {
            firLazyBlock = null;
        }
        if ((i & 128) != 0) {
            function2 = AnonymousClass1.INSTANCE;
        }
        if ((i & 256) != 0) {
            ktSourceElement = null;
        }
        generateAccessorsByDelegate(firPropertyBuilder, firWrappedDelegateExpressionBuilder, firModuleData, firClassSymbol, context, z, firLazyExpression, firLazyBlock, function2, ktSourceElement);
    }

    private static final <T> FirPropertyAccessExpression generateAccessorsByDelegate$delegateAccess(KtSourceElement ktSourceElement, FirClassSymbol<?> firClassSymbol, FirDelegateFieldSymbol firDelegateFieldSymbol, boolean z, FirPropertyBuilder firPropertyBuilder, Context<T> context) {
        FirPropertyAccessExpressionBuilder firPropertyAccessExpressionBuilder = new FirPropertyAccessExpressionBuilder();
        firPropertyAccessExpressionBuilder.setSource(ktSourceElement);
        FirDelegateFieldReferenceBuilder firDelegateFieldReferenceBuilder = new FirDelegateFieldReferenceBuilder();
        firDelegateFieldReferenceBuilder.setSource(ktSourceElement);
        firDelegateFieldReferenceBuilder.setResolvedSymbol(firDelegateFieldSymbol);
        firPropertyAccessExpressionBuilder.setCalleeReference(firDelegateFieldReferenceBuilder.build());
        if (firClassSymbol != null) {
            firPropertyAccessExpressionBuilder.setDispatchReceiver(generateAccessorsByDelegate$thisRef(z, firClassSymbol, ktSourceElement, firPropertyBuilder, context, true));
        }
        return firPropertyAccessExpressionBuilder.mo288build();
    }

    private static final FirCallableReferenceAccess generateAccessorsByDelegate$propertyRef(KtSourceElement ktSourceElement, boolean z, boolean z2, boolean z3, FirPropertyBuilder firPropertyBuilder) {
        ConeClassLikeType coneClassLikeTypeConstructClassLikeType$default;
        FirCallableReferenceAccessBuilder firCallableReferenceAccessBuilder = new FirCallableReferenceAccessBuilder();
        firCallableReferenceAccessBuilder.setSource(ktSourceElement);
        FirResolvedNamedReferenceBuilder firResolvedNamedReferenceBuilder = new FirResolvedNamedReferenceBuilder();
        firResolvedNamedReferenceBuilder.setSource(ktSourceElement);
        firResolvedNamedReferenceBuilder.setName(firPropertyBuilder.getName());
        firResolvedNamedReferenceBuilder.setResolvedSymbol(firPropertyBuilder.getSymbol());
        firCallableReferenceAccessBuilder.setCalleeReference(firResolvedNamedReferenceBuilder.build());
        if (!z && !z2) {
            coneClassLikeTypeConstructClassLikeType$default = z3 ? TypeConstructionUtilsKt.constructClassLikeType$default(StandardClassIds.INSTANCE.getKMutableProperty0(), new ConeStarProjection[]{ConeStarProjection.INSTANCE}, false, null, 6, null) : TypeConstructionUtilsKt.constructClassLikeType$default(StandardClassIds.INSTANCE.getKProperty0(), new ConeStarProjection[]{ConeStarProjection.INSTANCE}, false, null, 6, null);
        } else if (z && z2) {
            if (z3) {
                ClassId kMutableProperty2 = StandardClassIds.INSTANCE.getKMutableProperty2();
                ConeStarProjection coneStarProjection = ConeStarProjection.INSTANCE;
                coneClassLikeTypeConstructClassLikeType$default = TypeConstructionUtilsKt.constructClassLikeType$default(kMutableProperty2, new ConeStarProjection[]{coneStarProjection, coneStarProjection, coneStarProjection}, false, null, 6, null);
            } else {
                ClassId kProperty2 = StandardClassIds.INSTANCE.getKProperty2();
                ConeStarProjection coneStarProjection2 = ConeStarProjection.INSTANCE;
                coneClassLikeTypeConstructClassLikeType$default = TypeConstructionUtilsKt.constructClassLikeType$default(kProperty2, new ConeStarProjection[]{coneStarProjection2, coneStarProjection2, coneStarProjection2}, false, null, 6, null);
            }
        } else if (z3) {
            ClassId kMutableProperty1 = StandardClassIds.INSTANCE.getKMutableProperty1();
            ConeStarProjection coneStarProjection3 = ConeStarProjection.INSTANCE;
            coneClassLikeTypeConstructClassLikeType$default = TypeConstructionUtilsKt.constructClassLikeType$default(kMutableProperty1, new ConeStarProjection[]{coneStarProjection3, coneStarProjection3}, false, null, 6, null);
        } else {
            ClassId kProperty1 = StandardClassIds.INSTANCE.getKProperty1();
            ConeStarProjection coneStarProjection4 = ConeStarProjection.INSTANCE;
            coneClassLikeTypeConstructClassLikeType$default = TypeConstructionUtilsKt.constructClassLikeType$default(kProperty1, new ConeStarProjection[]{coneStarProjection4, coneStarProjection4}, false, null, 6, null);
        }
        firCallableReferenceAccessBuilder.setConeTypeOrNull(coneClassLikeTypeConstructClassLikeType$default);
        List<FirTypeParameter> typeParameters = firPropertyBuilder.getTypeParameters();
        List<FirTypeProjection> typeArguments = firCallableReferenceAccessBuilder.getTypeArguments();
        for (FirTypeParameter firTypeParameter : typeParameters) {
            FirTypeProjectionWithVarianceBuilder firTypeProjectionWithVarianceBuilder = new FirTypeProjectionWithVarianceBuilder();
            firTypeProjectionWithVarianceBuilder.setSource(ktSourceElement);
            firTypeProjectionWithVarianceBuilder.setVariance(Variance.INVARIANT);
            FirResolvedTypeRefBuilder firResolvedTypeRefBuilder = new FirResolvedTypeRefBuilder();
            firResolvedTypeRefBuilder.setConeType(new ConeTypeParameterTypeImpl(firTypeParameter.getSymbol().getLookupTag(), false, null, 4, null));
            firResolvedTypeRefBuilder.setSource(ktSourceElement);
            firTypeProjectionWithVarianceBuilder.setTypeRef(firResolvedTypeRefBuilder.build());
            typeArguments.add(firTypeProjectionWithVarianceBuilder.build());
        }
        return firCallableReferenceAccessBuilder.mo288build();
    }

    private static final <T> FirExpression generateAccessorsByDelegate$thisRef(boolean z, FirClassSymbol<?> firClassSymbol, KtSourceElement ktSourceElement, FirPropertyBuilder firPropertyBuilder, Context<T> context, boolean z2) {
        if (z && !z2) {
            FirThisReceiverExpressionBuilder firThisReceiverExpressionBuilder = new FirThisReceiverExpressionBuilder();
            firThisReceiverExpressionBuilder.setSource(ktSourceElement);
            FirImplicitThisReferenceBuilder firImplicitThisReferenceBuilder = new FirImplicitThisReferenceBuilder();
            FirReceiverParameter receiverParameter = firPropertyBuilder.getReceiverParameter();
            firImplicitThisReferenceBuilder.setBoundSymbol(receiverParameter != null ? receiverParameter.getSymbol() : null);
            firThisReceiverExpressionBuilder.setCalleeReference(firImplicitThisReferenceBuilder.build());
            return firThisReceiverExpressionBuilder.mo288build();
        }
        if (firClassSymbol == null) {
            return FirConstExpressionBuilderKt.buildLiteralExpression$default(null, ConstantValueKind.Null.INSTANCE, null, null, false, null, 40, null);
        }
        FirThisReceiverExpressionBuilder firThisReceiverExpressionBuilder2 = new FirThisReceiverExpressionBuilder();
        firThisReceiverExpressionBuilder2.setSource(ktSourceElement);
        FirImplicitThisReferenceBuilder firImplicitThisReferenceBuilder2 = new FirImplicitThisReferenceBuilder();
        firImplicitThisReferenceBuilder2.setBoundSymbol(firClassSymbol);
        firThisReceiverExpressionBuilder2.setCalleeReference(firImplicitThisReferenceBuilder2.build());
        firThisReceiverExpressionBuilder2.setConeTypeOrNull((ConeKotlinType) CollectionsKt.last(context.getDispatchReceiverTypesStack()));
        return firThisReceiverExpressionBuilder2.mo288build();
    }

    public static /* synthetic */ FirExpression generateAccessorsByDelegate$thisRef$default(boolean z, FirClassSymbol firClassSymbol, KtSourceElement ktSourceElement, FirPropertyBuilder firPropertyBuilder, Context context, boolean z2, int i, Object obj) {
        if ((i & 32) != 0) {
            z2 = false;
        }
        return generateAccessorsByDelegate$thisRef(z, firClassSymbol, ktSourceElement, firPropertyBuilder, context, z2);
    }

    public static final FirComparisonExpression generateComparisonExpression(FirExpression firExpression, FirExpression firExpression2, IElementType iElementType, KtSourceElement ktSourceElement, KtSourceElement ktSourceElement2) {
        FirOperation firOperation;
        firExpression.getClass();
        firExpression2.getClass();
        iElementType.getClass();
        ImmutableSet immutableSet = OperatorConventions.COMPARISON_OPERATIONS;
        immutableSet.getClass();
        if (!CollectionsKt.contains(immutableSet, iElementType)) {
            throw new IllegalArgumentException((iElementType + " is not in " + immutableSet).toString());
        }
        FirFunctionCall firFunctionCallCreateConventionCall = createConventionCall(firExpression, ktSourceElement2, ktSourceElement != null ? KtSourceElementKt.fakeElement$default(ktSourceElement, KtFakeSourceElementKind.GeneratedComparisonExpression.INSTANCE, null, 2, null) : null, firExpression2, OperatorNameConventions.COMPARE_TO);
        if (Intrinsics.areEqual(iElementType, KtTokens.LT)) {
            firOperation = FirOperation.LT;
        } else if (Intrinsics.areEqual(iElementType, KtTokens.GT)) {
            firOperation = FirOperation.GT;
        } else if (Intrinsics.areEqual(iElementType, KtTokens.LTEQ)) {
            firOperation = FirOperation.LT_EQ;
        } else {
            if (!Intrinsics.areEqual(iElementType, KtTokens.GTEQ)) {
                w04.a("Unknown ", iElementType);
                return null;
            }
            firOperation = FirOperation.GT_EQ;
        }
        FirComparisonExpressionBuilder firComparisonExpressionBuilder = new FirComparisonExpressionBuilder();
        firComparisonExpressionBuilder.setSource(ktSourceElement);
        firComparisonExpressionBuilder.setOperation(firOperation);
        firComparisonExpressionBuilder.setCompareToCall(firFunctionCallCreateConventionCall);
        return firComparisonExpressionBuilder.mo288build();
    }

    public static final FirFunctionCall generateContainsOperation(FirExpression firExpression, FirExpression firExpression2, boolean z, KtSourceElement ktSourceElement, KtSourceElement ktSourceElement2) {
        firExpression.getClass();
        firExpression2.getClass();
        if (z) {
            ktSourceElement2 = ktSourceElement2 != null ? KtSourceElementKt.fakeElement$default(ktSourceElement2, KtFakeSourceElementKind.DesugaredInvertedContains.INSTANCE, null, 2, null) : null;
        }
        FirFunctionCall firFunctionCallCreateConventionCall = createConventionCall(firExpression, ktSourceElement2, ktSourceElement, firExpression2, OperatorNameConventions.CONTAINS);
        if (!z) {
            return firFunctionCallCreateConventionCall;
        }
        FirFunctionCallBuilder firFunctionCallBuilder = new FirFunctionCallBuilder();
        firFunctionCallBuilder.setSource(ktSourceElement != null ? KtSourceElementKt.fakeElement$default(ktSourceElement, KtFakeSourceElementKind.DesugaredInvertedContains.INSTANCE, null, 2, null) : null);
        FirSimpleNamedReferenceBuilder firSimpleNamedReferenceBuilder = new FirSimpleNamedReferenceBuilder();
        firSimpleNamedReferenceBuilder.setSource(ktSourceElement2);
        firSimpleNamedReferenceBuilder.setName(OperatorNameConventions.NOT);
        firFunctionCallBuilder.setCalleeReference(firSimpleNamedReferenceBuilder.build());
        firFunctionCallBuilder.setExplicitReceiver(firFunctionCallCreateConventionCall);
        firFunctionCallBuilder.setOrigin(FirFunctionCallOrigin.Operator);
        return firFunctionCallBuilder.mo288build();
    }

    public static final FirBooleanOperatorExpression generateLazyLogicalOperation(FirExpression firExpression, FirExpression firExpression2, boolean z, KtSourceElement ktSourceElement) {
        firExpression.getClass();
        firExpression2.getClass();
        FirBooleanOperatorExpressionBuilder firBooleanOperatorExpressionBuilder = new FirBooleanOperatorExpressionBuilder();
        firBooleanOperatorExpressionBuilder.setSource(ktSourceElement);
        firBooleanOperatorExpressionBuilder.setLeftOperand(firExpression);
        firBooleanOperatorExpressionBuilder.setRightOperand(firExpression2);
        firBooleanOperatorExpressionBuilder.setKind(z ? LogicOperationKind.AND : LogicOperationKind.OR);
        return firBooleanOperatorExpressionBuilder.mo288build();
    }

    public static final FirElvisExpression generateNotNullOrOther(FirExpression firExpression, FirExpression firExpression2, KtSourceElement ktSourceElement) {
        firExpression.getClass();
        firExpression2.getClass();
        FirElvisExpressionBuilder firElvisExpressionBuilder = new FirElvisExpressionBuilder();
        firElvisExpressionBuilder.setSource(ktSourceElement);
        firElvisExpressionBuilder.setLhs(firExpression);
        firElvisExpressionBuilder.setRhs(firExpression2);
        return firElvisExpressionBuilder.mo288build();
    }

    public static final FirQualifiedAccessExpression generateResolvedAccessExpression(KtSourceElement ktSourceElement, FirVariable firVariable) {
        firVariable.getClass();
        FirPropertyAccessExpressionBuilder firPropertyAccessExpressionBuilder = new FirPropertyAccessExpressionBuilder();
        firPropertyAccessExpressionBuilder.setSource(ktSourceElement);
        FirResolvedNamedReferenceBuilder firResolvedNamedReferenceBuilder = new FirResolvedNamedReferenceBuilder();
        firResolvedNamedReferenceBuilder.setSource(ktSourceElement);
        firResolvedNamedReferenceBuilder.setName(firVariable.getName());
        firResolvedNamedReferenceBuilder.setResolvedSymbol(firVariable.getSymbol());
        firPropertyAccessExpressionBuilder.setCalleeReference(firResolvedNamedReferenceBuilder.build());
        return firPropertyAccessExpressionBuilder.mo288build();
    }

    public static final FirClassSymbol<FirClass> getOwnerRegularOrAnonymousObjectSymbol(FirClassBuilder firClassBuilder) {
        firClassBuilder.getClass();
        if (firClassBuilder instanceof FirAnonymousObjectBuilder) {
            return ((FirAnonymousObjectBuilder) firClassBuilder).getSymbol();
        }
        if (firClassBuilder instanceof FirRegularClassBuilder) {
            return ((FirRegularClassBuilder) firClassBuilder).getSymbol();
        }
        bu8.a();
        return null;
    }

    public static final FirExpression guardedBy(FirExpression firExpression, FirExpression firExpression2) {
        firExpression.getClass();
        if (firExpression2 == null) {
            return firExpression;
        }
        KtSourceElement source = firExpression.getSource();
        if (source == null) {
            source = firExpression2.getSource();
        }
        return generateLazyLogicalOperation(firExpression, firExpression2, true, source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.WhenCondition.INSTANCE, null, 2, null) : null);
    }

    public static final <T> void initContainingClassAttr(FirCallableDeclaration firCallableDeclaration, Context<T> context) {
        ConeClassLikeLookupTag lookupTag;
        firCallableDeclaration.getClass();
        context.getClass();
        ConeClassLikeType coneClassLikeTypeCurrentDispatchReceiverType = currentDispatchReceiverType(context);
        if (coneClassLikeTypeCurrentDispatchReceiverType == null || (lookupTag = coneClassLikeTypeCurrentDispatchReceiverType.getLookupTag()) == null) {
            return;
        }
        ClassMembersKt.setContainingClassForStaticMemberAttr(firCallableDeclaration, lookupTag);
    }

    public static final boolean isChildInParentheses(KtSourceElement ktSourceElement) {
        ktSourceElement.getClass();
        LighterASTNode lighterASTNode = (LighterASTNode) ktSourceElement.getTreeStructure().getParent(ktSourceElement.getLighterASTNode());
        return Intrinsics.areEqual(lighterASTNode != null ? lighterASTNode.getTokenType() : null, KtNodeTypes.PARENTHESIZED);
    }

    public static final boolean isContractBlockFirCheck(FirStatement firStatement) {
        firStatement.getClass();
        FirFunctionCall firFunctionCall = firStatement instanceof FirFunctionCall ? (FirFunctionCall) firStatement : null;
        if (firFunctionCall == null || !Intrinsics.areEqual(firFunctionCall.getCalleeReference().getName().asString(), "contract")) {
            return false;
        }
        FirExpression firExpression = (FirExpression) CollectionsKt.singleOrNull(firFunctionCall.getArgumentList().getArguments());
        if (!((firExpression != null ? FirExpressionUtilKt.unwrapArgument(firExpression) : null) instanceof FirAnonymousFunctionExpression)) {
            return false;
        }
        FirExpression explicitReceiver = firFunctionCall.getExplicitReceiver();
        FirQualifiedAccessExpression firQualifiedAccessExpression = explicitReceiver instanceof FirQualifiedAccessExpression ? (FirQualifiedAccessExpression) explicitReceiver : null;
        if (firQualifiedAccessExpression == null) {
            return true;
        }
        if (!checkReceiver(firFunctionCall, "contracts") || !checkReceiver(firQualifiedAccessExpression, "kotlin")) {
            return false;
        }
        FirExpression explicitReceiver2 = firQualifiedAccessExpression.getExplicitReceiver();
        FirQualifiedAccessExpression firQualifiedAccessExpression2 = explicitReceiver2 instanceof FirQualifiedAccessExpression ? (FirQualifiedAccessExpression) explicitReceiver2 : null;
        return firQualifiedAccessExpression2 != null && firQualifiedAccessExpression2.getExplicitReceiver() == null;
    }

    public static final boolean isContractPresentFirCheck(FirBlock firBlock) {
        firBlock.getClass();
        FirStatement firStatement = (FirStatement) CollectionsKt.firstOrNull(firBlock.getStatements());
        if (firStatement == null) {
            return false;
        }
        return isContractBlockFirCheck(firStatement);
    }

    public static final boolean isUnderscore(CharSequence charSequence) {
        charSequence.getClass();
        for (int i = 0; i < charSequence.length(); i++) {
            if (charSequence.charAt(i) != '_') {
                return false;
            }
        }
        return true;
    }

    public static final CharacterWithDiagnostic parseCharacter(String str) {
        str.getClass();
        if (str.length() < 2 || str.charAt(0) != '\'' || str.charAt(str.length() - 1) != '\'') {
            return new CharacterWithDiagnostic(DiagnosticKind.IncorrectCharacterLiteral);
        }
        String strSubstring = str.substring(1, str.length() - 1);
        if (strSubstring.length() == 0) {
            return new CharacterWithDiagnostic(DiagnosticKind.EmptyCharacterLiteral);
        }
        if (strSubstring.charAt(0) != '\\') {
            return strSubstring.length() == 1 ? new CharacterWithDiagnostic(strSubstring.charAt(0)) : new CharacterWithDiagnostic(DiagnosticKind.TooManyCharactersInCharacterLiteral);
        }
        return escapedStringToCharacter(strSubstring);
    }

    public static final FirContractDescription processLegacyContractDescription(FirBlock firBlock, ConeDiagnostic coneDiagnostic) {
        firBlock.getClass();
        if (isContractPresentFirCheck(firBlock)) {
            return toLegacyRawContractDescription((FirFunctionCall) FirExpressionUtilKt.replaceFirstStatement(firBlock, new Function1() { // from class: xx2
                public final Object invoke(Object obj) {
                    return ConversionUtilsKt.a((FirFunctionCall) obj);
                }
            }), coneDiagnostic);
        }
        return null;
    }

    public static final <F extends FirExpression> FirExpression pullUpSafeCallIfNecessary(F f, Function1<? super F, ? extends FirExpression> function1, Function2<? super F, ? super FirExpression, Unit> function2) {
        f.getClass();
        function1.getClass();
        function2.getClass();
        Object objInvoke = function1.invoke(f);
        FirSafeCallExpression firSafeCallExpression = objInvoke instanceof FirSafeCallExpression ? (FirSafeCallExpression) objInvoke : null;
        if (firSafeCallExpression != null) {
            FirStatement selector = firSafeCallExpression.getSelector();
            FirExpression firExpression = selector instanceof FirExpression ? (FirExpression) selector : null;
            if (firExpression != null && !isChildInParentheses(firSafeCallExpression)) {
                function2.invoke(f, firExpression);
                firSafeCallExpression.replaceSelector(f);
                return firSafeCallExpression;
            }
        }
        return f;
    }

    public static final boolean shouldGenerateDelegatedSuperCall(boolean z, boolean z2, boolean z3, boolean z4) {
        if (z) {
            return false;
        }
        if (z2) {
            return !z3 && z4;
        }
        return true;
    }

    public static final Name toBinaryName(IElementType iElementType) {
        iElementType.getClass();
        ImmutableBiMap immutableBiMap = OperatorConventions.BINARY_OPERATION_NAMES;
        immutableBiMap.getClass();
        return (Name) immutableBiMap.get(iElementType);
    }

    public static final FirComponentCall toComponentCall(FirVariable firVariable, KtSourceElement ktSourceElement, int i) {
        firVariable.getClass();
        FirComponentCallBuilder firComponentCallBuilder = new FirComponentCallBuilder();
        KtSourceElement ktSourceElementFakeElement$default = ktSourceElement != null ? KtSourceElementKt.fakeElement$default(ktSourceElement, KtFakeSourceElementKind.DesugaredComponentFunctionCall.INSTANCE, null, 2, null) : null;
        firComponentCallBuilder.setSource(ktSourceElementFakeElement$default);
        firComponentCallBuilder.setExplicitReceiver(generateResolvedAccessExpression(ktSourceElementFakeElement$default, firVariable));
        firComponentCallBuilder.setComponentIndex(i + 1);
        return firComponentCallBuilder.mo288build();
    }

    public static final FirOperation toFirOperation(IElementType iElementType) {
        iElementType.getClass();
        FirOperation firOperationOrNull = toFirOperationOrNull(iElementType);
        if (firOperationOrNull != null) {
            return firOperationOrNull;
        }
        w04.a("Cannot convert element type to FIR operation: ", iElementType);
        return null;
    }

    public static final FirOperation toFirOperationOrNull(IElementType iElementType) {
        iElementType.getClass();
        return ktTokenToFirOperationMap.get(iElementType);
    }

    public static final FirLegacyRawContractDescription toLegacyRawContractDescription(FirFunctionCall firFunctionCall, ConeDiagnostic coneDiagnostic) {
        firFunctionCall.getClass();
        FirLegacyRawContractDescriptionBuilder firLegacyRawContractDescriptionBuilder = new FirLegacyRawContractDescriptionBuilder();
        firLegacyRawContractDescriptionBuilder.setSource(firFunctionCall.getSource());
        firLegacyRawContractDescriptionBuilder.setContractCall(firFunctionCall);
        firLegacyRawContractDescriptionBuilder.setDiagnostic(coneDiagnostic);
        return firLegacyRawContractDescriptionBuilder.build();
    }

    public static /* synthetic */ FirLegacyRawContractDescription toLegacyRawContractDescription$default(FirFunctionCall firFunctionCall, ConeDiagnostic coneDiagnostic, int i, Object obj) {
        if ((i & 1) != 0) {
            coneDiagnostic = null;
        }
        return toLegacyRawContractDescription(firFunctionCall, coneDiagnostic);
    }

    public static final Name toUnaryName(IElementType iElementType) {
        iElementType.getClass();
        ImmutableBiMap immutableBiMap = OperatorConventions.UNARY_OPERATION_NAMES;
        immutableBiMap.getClass();
        return (Name) immutableBiMap.get(iElementType);
    }

    public static final CharacterWithDiagnostic translateEscape(char c) {
        CharacterWithDiagnostic characterWithDiagnostic = escapeCharToChartedWithDiagnosticMap.get(Character.valueOf(c));
        return characterWithDiagnostic == null ? illegalEscapeDiagnostic : characterWithDiagnostic;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final FirResolvedTypeRef wrapIntoArray(FirErrorTypeRef firErrorTypeRef) {
        firErrorTypeRef.getClass();
        FirResolvedTypeRefBuilder firResolvedTypeRefBuilder = new FirResolvedTypeRefBuilder();
        firResolvedTypeRefBuilder.setSource(firErrorTypeRef.getSource());
        firResolvedTypeRefBuilder.setConeType(TypeConstructionUtilsKt.constructClassLikeType$default(StandardClassIds.INSTANCE.getArray(), new ConeKotlinTypeProjectionOut[]{new ConeKotlinTypeProjectionOut(firErrorTypeRef.getConeType())}, false, null, 6, null));
        firResolvedTypeRefBuilder.setDelegatedTypeRef(UtilsKt.copyWithNewSourceKind(firErrorTypeRef, KtFakeSourceElementKind.ArrayTypeFromVarargParameter.INSTANCE));
        return firResolvedTypeRefBuilder.build();
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.builder.ConversionUtilsKt$generateAccessorsByDelegate$1, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function2<FirFunctionTarget, FirFunction, Unit> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(2, FirFunctionTarget.class, "bind", "bind(Lorg/jetbrains/kotlin/fir/FirTargetElement;)V", 0);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((FirFunctionTarget) obj, (FirFunction) obj2);
            return Unit.INSTANCE;
        }

        public final void invoke(FirFunctionTarget firFunctionTarget, FirFunction firFunction) {
            firFunctionTarget.getClass();
            firFunction.getClass();
            firFunctionTarget.bind(firFunction);
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.builder.ConversionUtilsKt$pullUpSafeCallIfNecessary$2, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass2 extends FunctionReferenceImpl implements Function2<FirQualifiedAccessExpression, FirExpression, Unit> {
        public static final AnonymousClass2 INSTANCE = new AnonymousClass2();

        public AnonymousClass2() {
            super(2, FirQualifiedAccessExpression.class, "replaceExplicitReceiver", "replaceExplicitReceiver(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)V", 0);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((FirQualifiedAccessExpression) obj, (FirExpression) obj2);
            return Unit.INSTANCE;
        }

        public final void invoke(FirQualifiedAccessExpression firQualifiedAccessExpression, FirExpression firExpression) {
            firQualifiedAccessExpression.getClass();
            firQualifiedAccessExpression.replaceExplicitReceiver(firExpression);
        }
    }

    public static final boolean isChildInParentheses(FirStatement firStatement) {
        firStatement.getClass();
        KtSourceElement source = firStatement.getSource();
        if (source != null) {
            return isChildInParentheses(source);
        }
        k2d.a("Nullable source");
        return false;
    }

    public static final FirExpression pullUpSafeCallIfNecessary(FirQualifiedAccessExpression firQualifiedAccessExpression) {
        firQualifiedAccessExpression.getClass();
        return pullUpSafeCallIfNecessary(firQualifiedAccessExpression, new PropertyReference1Impl() { // from class: org.jetbrains.kotlin.fir.builder.ConversionUtilsKt.pullUpSafeCallIfNecessary.1
            public Object get(Object obj) {
                return ((FirQualifiedAccessExpression) obj).getExplicitReceiver();
            }
        }, AnonymousClass2.INSTANCE);
    }
}
