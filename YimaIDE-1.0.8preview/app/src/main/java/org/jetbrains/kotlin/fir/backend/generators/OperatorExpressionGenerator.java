package org.jetbrains.kotlin.fir.backend.generators;

import defpackage.f2f;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.backend.common.IrSpecialAnnotationsProvider;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.backend.Fir2IrBuiltinSymbolsContainer;
import org.jetbrains.kotlin.fir.backend.Fir2IrClassifierStorage;
import org.jetbrains.kotlin.fir.backend.Fir2IrComponents;
import org.jetbrains.kotlin.fir.backend.Fir2IrConfiguration;
import org.jetbrains.kotlin.fir.backend.Fir2IrConversionScope;
import org.jetbrains.kotlin.fir.backend.Fir2IrConverter;
import org.jetbrains.kotlin.fir.backend.Fir2IrDeclarationStorage;
import org.jetbrains.kotlin.fir.backend.Fir2IrExtensions;
import org.jetbrains.kotlin.fir.backend.Fir2IrImplicitCastInserter;
import org.jetbrains.kotlin.fir.backend.Fir2IrIrGeneratedDeclarationsRegistrar;
import org.jetbrains.kotlin.fir.backend.Fir2IrSymbolsMappingForLazyClasses;
import org.jetbrains.kotlin.fir.backend.Fir2IrTypeConverter;
import org.jetbrains.kotlin.fir.backend.Fir2IrTypeConverterKt;
import org.jetbrains.kotlin.fir.backend.Fir2IrVisibilityConverter;
import org.jetbrains.kotlin.fir.backend.Fir2IrVisitor;
import org.jetbrains.kotlin.fir.backend.FirProviderWithGeneratedFiles;
import org.jetbrains.kotlin.fir.backend.PrimitiveComparisonKt;
import org.jetbrains.kotlin.fir.backend.PrimitiveConeNumericComparisonInfo;
import org.jetbrains.kotlin.fir.backend.utils.ConversionTypeOrigin;
import org.jetbrains.kotlin.fir.backend.utils.IrElementsCreationUtilsKt;
import org.jetbrains.kotlin.fir.backend.utils.OffsetUtilsKt;
import org.jetbrains.kotlin.fir.backend.utils.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.expressions.FirComparisonExpression;
import org.jetbrains.kotlin.fir.expressions.FirEqualityOperatorCall;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirOperation;
import org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression;
import org.jetbrains.kotlin.fir.expressions.FirVarargArgumentsExpression;
import org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderKt;
import org.jetbrains.kotlin.fir.scopes.FirScopeKt;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeDynamicType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.ir.IrLock;
import org.jetbrains.kotlin.ir.IrProvider;
import org.jetbrains.kotlin.ir.builders.PrimitivesKt;
import org.jetbrains.kotlin.ir.declarations.IrVariable;
import org.jetbrains.kotlin.ir.expressions.IrCall;
import org.jetbrains.kotlin.ir.expressions.IrDynamicOperator;
import org.jetbrains.kotlin.ir.expressions.IrExpression;
import org.jetbrains.kotlin.ir.expressions.IrStatementOrigin;
import org.jetbrains.kotlin.ir.expressions.IrStatementOriginImpl;
import org.jetbrains.kotlin.ir.expressions.IrTypeOperator;
import org.jetbrains.kotlin.ir.expressions.IrTypeOperatorCall;
import org.jetbrains.kotlin.ir.expressions.impl.BuildersKt;
import org.jetbrains.kotlin.ir.expressions.impl.IrCallImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrConstImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrDynamicOperatorExpressionImpl;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.ir.symbols.IrClassifierSymbol;
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol;
import org.jetbrains.kotlin.ir.symbols.IrValueSymbol;
import org.jetbrains.kotlin.ir.types.IrDynamicType;
import org.jetbrains.kotlin.ir.types.IrSimpleType;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.types.IrTypePredicatesKt;
import org.jetbrains.kotlin.ir.types.IrTypesKt;
import org.jetbrains.kotlin.ir.util.IrTypeUtilsKt;
import org.jetbrains.kotlin.ir.util.KotlinMangler;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Ø\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000fJ \u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\fH\u0002J&\u0010\u0014\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0016\u0012\u0004\u0012\u00020\u00170\u00152\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u000e\u0010\u001c\u001a\u0004\u0018\u00010\u001d*\u00020\u0019H\u0002J.\u0010\u001e\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u00192\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 H\u0002J\u000e\u0010\u001c\u001a\u0004\u0018\u00010\u001d*\u00020\"H\u0002J2\u0010#\u001a\u0004\u0018\u00010$2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010%\u001a\u00020\n2\u0006\u0010&\u001a\u00020\n2\u0006\u0010'\u001a\u00020\"H\u0002J.\u0010(\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u00192\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 H\u0002J.\u0010)\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u00192\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 H\u0002J\u0014\u0010*\u001a\u00020\n*\u00020\n2\u0006\u0010'\u001a\u00020\"H\u0002J\u001e\u0010+\u001a\u00020\n*\u00020!2\b\u0010,\u001a\u0004\u0018\u00010-2\u0006\u0010.\u001a\u00020/H\u0002R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u00100\u001a\u000201X\u0096\u0005¢\u0006\u0006\u001a\u0004\b2\u00103R\u0012\u00104\u001a\u000205X\u0096\u0005¢\u0006\u0006\u001a\u0004\b6\u00107R\u0012\u00108\u001a\u000209X\u0096\u0005¢\u0006\u0006\u001a\u0004\b:\u0010;R\u0012\u0010<\u001a\u00020=X\u0096\u0005¢\u0006\u0006\u001a\u0004\b>\u0010?R\u0012\u0010@\u001a\u00020AX\u0096\u0005¢\u0006\u0006\u001a\u0004\bB\u0010CR\u0012\u0010D\u001a\u00020EX\u0096\u0005¢\u0006\u0006\u001a\u0004\bF\u0010GR\u0012\u0010H\u001a\u00020IX\u0096\u0005¢\u0006\u0006\u001a\u0004\bJ\u0010KR\u0012\u0010L\u001a\u00020MX\u0096\u0005¢\u0006\u0006\u001a\u0004\bN\u0010OR\u0012\u0010P\u001a\u00020QX\u0096\u0005¢\u0006\u0006\u001a\u0004\bR\u0010SR\u0012\u0010T\u001a\u00020UX\u0096\u0005¢\u0006\u0006\u001a\u0004\bV\u0010WR\u0012\u0010X\u001a\u00020YX\u0096\u0005¢\u0006\u0006\u001a\u0004\bZ\u0010[R\u0012\u0010\\\u001a\u00020]X\u0096\u0005¢\u0006\u0006\u001a\u0004\b^\u0010_R\u0012\u0010`\u001a\u00020aX\u0096\u0005¢\u0006\u0006\u001a\u0004\bb\u0010cR\u001a\u0010d\u001a\n\u0012\u0004\u0012\u00020f\u0018\u00010eX\u0096\u0005¢\u0006\u0006\u001a\u0004\bg\u0010hR\u0012\u0010i\u001a\u00020jX\u0096\u0005¢\u0006\u0006\u001a\u0004\bk\u0010lR\u0012\u0010m\u001a\u00020nX\u0096\u0005¢\u0006\u0006\u001a\u0004\bo\u0010pR\u0012\u0010q\u001a\u00020rX\u0096\u0005¢\u0006\u0006\u001a\u0004\bs\u0010tR\u0018\u0010u\u001a\b\u0012\u0004\u0012\u00020v0 X\u0096\u0005¢\u0006\u0006\u001a\u0004\bw\u0010xR\u0012\u0010y\u001a\u00020zX\u0096\u0005¢\u0006\u0006\u001a\u0004\b{\u0010|R\u0013\u0010}\u001a\u00020~X\u0096\u0005¢\u0006\u0007\u001a\u0005\b\u007f\u0010\u0080\u0001R\u0016\u0010\u0081\u0001\u001a\u00030\u0082\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0083\u0001\u0010\u0084\u0001R\u0016\u0010\u0085\u0001\u001a\u00030\u0086\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0087\u0001\u0010\u0088\u0001R\u0016\u0010\u0089\u0001\u001a\u00030\u008a\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u008b\u0001\u0010\u008c\u0001R\u0018\u0010\u008d\u0001\u001a\u0005\u0018\u00010\u008e\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u008f\u0001\u0010\u0090\u0001R\u0016\u0010\u0091\u0001\u001a\u00030\u0092\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0093\u0001\u0010\u0094\u0001R\u0016\u0010\u0095\u0001\u001a\u00030\u0096\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0097\u0001\u0010\u0098\u0001R\u0016\u0010\u0099\u0001\u001a\u00030\u009a\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u009b\u0001\u0010\u009c\u0001¨\u0006\u009d\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/generators/OperatorExpressionGenerator;", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "c", "visitor", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisitor;", "conversionScope", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConversionScope;", "<init>", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisitor;Lorg/jetbrains/kotlin/fir/backend/Fir2IrConversionScope;)V", "convertComparisonExpression", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "comparisonExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirComparisonExpression;", "convertEqualityOperatorCall", "equalityOperatorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirEqualityOperatorCall;", "generateComparisonCall", "startOffset", Argument.Delimiters.none, "endOffset", "getSymbolAndOriginForComparison", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", "Lorg/jetbrains/kotlin/ir/expressions/IrStatementOriginImpl;", "operation", "Lorg/jetbrains/kotlin/fir/expressions/FirOperation;", "classifier", "Lorg/jetbrains/kotlin/ir/symbols/IrClassifierSymbol;", "toIrDynamicOperator", "Lorg/jetbrains/kotlin/ir/expressions/IrDynamicOperator;", "generateEqualityOperatorCall", "arguments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "Lorg/jetbrains/kotlin/ir/expressions/IrStatementOrigin;", "tryGenerateDynamicOperatorCall", "Lorg/jetbrains/kotlin/ir/expressions/impl/IrDynamicOperatorExpressionImpl;", "firstArgument", "secondArgument", "origin", "transformEqualityOperatorCall", "transformIdentityOperatorCall", "negate", "convertToIrExpression", "comparisonInfo", "Lorg/jetbrains/kotlin/fir/backend/PrimitiveConeNumericComparisonInfo;", "isLeftType", Argument.Delimiters.none, "adapterGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/AdapterGenerator;", "getAdapterGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/AdapterGenerator;", "annotationGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/AnnotationGenerator;", "getAnnotationGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/AnnotationGenerator;", "annotationsFromPluginRegistrar", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar;", "getAnnotationsFromPluginRegistrar", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar;", "builtins", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrBuiltinSymbolsContainer;", "getBuiltins", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrBuiltinSymbolsContainer;", "callGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/CallAndReferenceGenerator;", "getCallGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/CallAndReferenceGenerator;", "callablesGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrCallableDeclarationsGenerator;", "getCallablesGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrCallableDeclarationsGenerator;", "classifierStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrClassifierStorage;", "getClassifierStorage", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrClassifierStorage;", "classifiersGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrClassifiersGenerator;", "getClassifiersGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrClassifiersGenerator;", "configuration", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;", "getConfiguration", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;", "converter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConverter;", "getConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrConverter;", "dataClassMembersGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrDataClassMembersGenerator;", "getDataClassMembersGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrDataClassMembersGenerator;", "declarationStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage;", "getDeclarationStorage", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage;", "extensions", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;", "getExtensions", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;", "filesBeingCompiled", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "getFilesBeingCompiled", "()Ljava/util/Set;", "firProvider", "Lorg/jetbrains/kotlin/fir/backend/FirProviderWithGeneratedFiles;", "getFirProvider", "()Lorg/jetbrains/kotlin/fir/backend/FirProviderWithGeneratedFiles;", "implicitCastInserter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrImplicitCastInserter;", "getImplicitCastInserter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrImplicitCastInserter;", "irMangler", "Lorg/jetbrains/kotlin/ir/util/KotlinMangler$IrMangler;", "getIrMangler", "()Lorg/jetbrains/kotlin/ir/util/KotlinMangler$IrMangler;", "irProviders", "Lorg/jetbrains/kotlin/ir/IrProvider;", "getIrProviders", "()Ljava/util/List;", "lazyDeclarationsGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyDeclarationsGenerator;", "getLazyDeclarationsGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyDeclarationsGenerator;", "lazyFakeOverrideGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyFakeOverrideGenerator;", "getLazyFakeOverrideGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyFakeOverrideGenerator;", "lock", "Lorg/jetbrains/kotlin/ir/IrLock;", "getLock", "()Lorg/jetbrains/kotlin/ir/IrLock;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "getScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "specialAnnotationsProvider", "Lorg/jetbrains/kotlin/backend/common/IrSpecialAnnotationsProvider;", "getSpecialAnnotationsProvider", "()Lorg/jetbrains/kotlin/backend/common/IrSpecialAnnotationsProvider;", "symbolsMappingForLazyClasses", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrSymbolsMappingForLazyClasses;", "getSymbolsMappingForLazyClasses", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrSymbolsMappingForLazyClasses;", "typeConverter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrTypeConverter;", "getTypeConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrTypeConverter;", "visibilityConverter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisibilityConverter;", "getVisibilityConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisibilityConverter;", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class OperatorExpressionGenerator implements Fir2IrComponents {
    private final Fir2IrComponents c;
    private final Fir2IrConversionScope conversionScope;
    private final Fir2IrVisitor visitor;

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[FirOperation.values().length];
            try {
                iArr[FirOperation.LT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[FirOperation.GT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[FirOperation.LT_EQ.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[FirOperation.GT_EQ.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[FirOperation.EQ.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[FirOperation.NOT_EQ.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[FirOperation.IDENTITY.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[FirOperation.NOT_IDENTITY.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public OperatorExpressionGenerator(Fir2IrComponents fir2IrComponents, Fir2IrVisitor fir2IrVisitor, Fir2IrConversionScope fir2IrConversionScope) {
        fir2IrComponents.getClass();
        fir2IrVisitor.getClass();
        fir2IrConversionScope.getClass();
        this.c = fir2IrComponents;
        this.visitor = fir2IrVisitor;
        this.conversionScope = fir2IrConversionScope;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:18:0x0037  */
    private final IrExpression convertToIrExpression(FirExpression firExpression, PrimitiveConeNumericComparisonInfo primitiveConeNumericComparisonInfo, boolean z) throws KotlinIllegalArgumentExceptionWithAttachments {
        ConeKotlinType rightType;
        FirExpression originalExpression;
        ConeKotlinType resolvedType;
        FirSmartCastExpression firSmartCastExpression = firExpression instanceof FirSmartCastExpression ? (FirSmartCastExpression) firExpression : null;
        boolean zIsMarkedNullable = (firSmartCastExpression == null || (originalExpression = firSmartCastExpression.getOriginalExpression()) == null || (resolvedType = FirTypeUtilsKt.getResolvedType(originalExpression)) == null) ? false : ConeTypeUtilsKt.isMarkedNullable(resolvedType);
        IrExpression irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default = Fir2IrVisitor.convertToIrExpression$org_jetbrains_kotlin_fir2ir$default(this.visitor, firExpression, false, null, 6, null);
        if (z) {
            if (primitiveConeNumericComparisonInfo != null) {
                rightType = primitiveConeNumericComparisonInfo.getLeftType();
            } else {
                rightType = null;
            }
        } else if (primitiveConeNumericComparisonInfo != null) {
            rightType = primitiveConeNumericComparisonInfo.getRightType();
        } else {
            rightType = null;
        }
        ConeClassLikeType comparisonType = primitiveConeNumericComparisonInfo != null ? primitiveConeNumericComparisonInfo.getComparisonType() : null;
        boolean zAreEqual = Intrinsics.areEqual(primitiveConeNumericComparisonInfo != null ? primitiveConeNumericComparisonInfo.getLeftType() : null, primitiveConeNumericComparisonInfo != null ? primitiveConeNumericComparisonInfo.getRightType() : null);
        if (comparisonType == null) {
            return convertToIrExpression$eraseImplicitCast(irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default, zAreEqual, zIsMarkedNullable);
        }
        ClassId classId = rightType != null ? ConeTypeUtilsKt.getClassId(rightType) : null;
        if (classId == null) {
            f2f.a("operandType should be non-null if targetType is non-null: ", UtilsKt.render(firExpression));
            return null;
        }
        if (Intrinsics.areEqual(classId, comparisonType.getLookupTag().getClassId())) {
            return convertToIrExpression$eraseImplicitCast(irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default, zAreEqual, zIsMarkedNullable);
        }
        FirRegularClassSymbol regularClassSymbolByClassId = FirSymbolProviderKt.getRegularClassSymbolByClassId(getSession(), classId);
        if (regularClassSymbolByClassId == null) {
            w04.a("No symbol for ", classId);
            return null;
        }
        FirTypeScope firTypeScopeUnsubstitutedScope = ScopeUtilsKt.unsubstitutedScope(this, regularClassSymbolByClassId);
        Name nameIdentifier = Name.identifier("to" + comparisonType.getLookupTag().getClassId().getShortClassName().asString());
        nameIdentifier.getClass();
        FirNamedFunctionSymbol firNamedFunctionSymbol = (FirNamedFunctionSymbol) CollectionsKt.singleOrNull(FirScopeKt.getFunctions(firTypeScopeUnsubstitutedScope, nameIdentifier));
        if (firNamedFunctionSymbol == null) {
            a11.a("No conversion function for ", rightType, " ~> ", comparisonType);
            return null;
        }
        IrSimpleFunctionSymbol irFunctionSymbol$default = Fir2IrDeclarationStorage.getIrFunctionSymbol$default(getDeclarationStorage(), firNamedFunctionSymbol, regularClassSymbolByClassId.getLookupTag(), false, 4, null);
        int startOffset = irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default.getStartOffset();
        int endOffset = irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default.getEndOffset();
        IrType irType$default = Fir2IrTypeConverterKt.toIrType$default(this, firNamedFunctionSymbol.getResolvedReturnType(), (ConversionTypeOrigin) null, 2, (Object) null);
        irFunctionSymbol$default.getClass();
        IrCallImpl irCallImplIrCallImpl$default = BuildersKt.IrCallImpl$default(startOffset, endOffset, irType$default, irFunctionSymbol$default, 0, (IrStatementOrigin) null, (IrClassSymbol) null, 96, (Object) null);
        irCallImplIrCallImpl$default.getArguments().set(0, irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default);
        if (!TypeComponentsKt.getTypeContext(getSession()).isNullableType(rightType)) {
            return irCallImplIrCallImpl$default;
        }
        Pair<IrVariable, IrValueSymbol> pairCreateTemporaryVariableForSafeCallConstruction = IrElementsCreationUtilsKt.createTemporaryVariableForSafeCallConstruction(this.conversionScope, irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default);
        IrVariable irVariable = (IrVariable) pairCreateTemporaryVariableForSafeCallConstruction.component1();
        IrValueSymbol irValueSymbol = (IrValueSymbol) pairCreateTemporaryVariableForSafeCallConstruction.component2();
        irCallImplIrCallImpl$default.getArguments().set(0, BuildersKt.IrGetValueImpl$default(irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default.getStartOffset(), irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default.getEndOffset(), irValueSymbol, (IrStatementOrigin) null, 8, (Object) null));
        return IrElementsCreationUtilsKt.createSafeCallConstruction(this.c, irVariable, irValueSymbol, irCallImplIrCallImpl$default);
    }

    private static final IrExpression convertToIrExpression$eraseImplicitCast(IrExpression irExpression, boolean z, boolean z2) {
        if (!(irExpression instanceof IrTypeOperatorCall)) {
            return irExpression;
        }
        boolean zIsDoubleOrFloatWithoutNullability = IrTypePredicatesKt.isDoubleOrFloatWithoutNullability(irExpression.getType());
        if (z && !zIsDoubleOrFloatWithoutNullability) {
            IrTypeOperatorCall irTypeOperatorCall = (IrTypeOperatorCall) irExpression;
            if (irTypeOperatorCall.getOperator() == IrTypeOperator.IMPLICIT_CAST) {
                return irTypeOperatorCall.getArgument();
            }
        }
        IrType type = irExpression.getType();
        if (!zIsDoubleOrFloatWithoutNullability || !z2 || !(type instanceof IrSimpleType) || IrTypeUtilsKt.isNullable(type)) {
            return irExpression;
        }
        IrType irTypeMakeNullable = IrTypesKt.makeNullable(type);
        int startOffset = irExpression.getStartOffset();
        int endOffset = irExpression.getEndOffset();
        IrTypeOperatorCall irTypeOperatorCall2 = (IrTypeOperatorCall) irExpression;
        return BuildersKt.IrTypeOperatorCallImpl(startOffset, endOffset, irTypeMakeNullable, irTypeOperatorCall2.getOperator(), irTypeMakeNullable, irTypeOperatorCall2.getArgument());
    }

    private final IrExpression generateComparisonCall(int startOffset, int endOffset, FirComparisonExpression comparisonExpression) throws Exception {
        PrimitiveConeNumericComparisonInfo primitiveConeNumericComparisonInfoInferPrimitiveNumericComparisonInfo;
        FirCallableDeclaration firCallableDeclaration;
        FirExpression firExpression;
        FirOperation operation = comparisonExpression.getOperation();
        FirExpression explicitReceiver = comparisonExpression.getCompareToCall().getExplicitReceiver();
        FirReceiverParameter receiverParameter = null;
        if ((explicitReceiver != null ? FirTypeUtilsKt.getResolvedType(explicitReceiver) : null) instanceof ConeDynamicType) {
            IrDynamicOperator irDynamicOperator = toIrDynamicOperator(operation);
            if (irDynamicOperator == null) {
                throw new Exception("Can't convert to the corresponding IrDynamicOperator");
            }
            Object objFirstOrNull = CollectionsKt.firstOrNull(comparisonExpression.getCompareToCall().getArgumentList().getArguments());
            FirVarargArgumentsExpression firVarargArgumentsExpression = objFirstOrNull instanceof FirVarargArgumentsExpression ? (FirVarargArgumentsExpression) objFirstOrNull : null;
            List<FirExpression> arguments = firVarargArgumentsExpression != null ? firVarargArgumentsExpression.getArguments() : null;
            if (arguments == null || (firExpression = (FirExpression) CollectionsKt.firstOrNull(arguments)) == null) {
                throw new Exception("Comparison with a dynamic function should have a vararg with the rhs-argument");
            }
            IrDynamicOperatorExpressionImpl IrDynamicOperatorExpressionImpl = BuildersKt.IrDynamicOperatorExpressionImpl(startOffset, endOffset, getBuiltins().getBooleanType(), irDynamicOperator);
            Object objAccept = explicitReceiver.accept(this.visitor, null);
            objAccept.getClass();
            IrDynamicOperatorExpressionImpl.setReceiver((IrExpression) objAccept);
            List arguments2 = IrDynamicOperatorExpressionImpl.getArguments();
            Object objAccept2 = firExpression.accept(this.visitor, null);
            objAccept2.getClass();
            arguments2.add((IrExpression) objAccept2);
            return IrDynamicOperatorExpressionImpl;
        }
        FirCallableSymbol<?> resolvedCallableSymbol = ReferenceUtilsKt.toResolvedCallableSymbol(comparisonExpression.getCompareToCall());
        if (resolvedCallableSymbol != null && (firCallableDeclaration = (FirCallableDeclaration) resolvedCallableSymbol.getFir()) != null) {
            receiverParameter = firCallableDeclaration.getReceiverParameter();
        }
        if (receiverParameter == null && (primitiveConeNumericComparisonInfoInferPrimitiveNumericComparisonInfo = PrimitiveComparisonKt.inferPrimitiveNumericComparisonInfo(this, comparisonExpression)) != null) {
            IrType irType = getTypeConverter().getClassIdToTypeMap$org_jetbrains_kotlin_fir2ir().get(primitiveConeNumericComparisonInfoInferPrimitiveNumericComparisonInfo.getComparisonType().getLookupTag().getClassId());
            if (irType == null) {
                return generateComparisonCall$fallbackToRealCall(this, operation, comparisonExpression, startOffset, endOffset);
            }
            Pair<IrSimpleFunctionSymbol, IrStatementOriginImpl> symbolAndOriginForComparison = getSymbolAndOriginForComparison(operation, IrTypesKt.getClassifierOrFail(irType));
            IrSimpleFunctionSymbol irSimpleFunctionSymbol = (IrSimpleFunctionSymbol) symbolAndOriginForComparison.component1();
            IrStatementOriginImpl irStatementOriginImpl = (IrStatementOriginImpl) symbolAndOriginForComparison.component2();
            IrType booleanType = getBuiltins().getBooleanType();
            irSimpleFunctionSymbol.getClass();
            IrCallImpl irCallImplIrCallImplWithShape$default = BuildersKt.IrCallImplWithShape$default(startOffset, endOffset, booleanType, irSimpleFunctionSymbol, 0, 2, 0, false, false, irStatementOriginImpl, (IrClassSymbol) null, 1024, (Object) null);
            irCallImplIrCallImplWithShape$default.getArguments().set(0, convertToIrExpression(PrimitiveComparisonKt.getLeft(comparisonExpression), primitiveConeNumericComparisonInfoInferPrimitiveNumericComparisonInfo, true));
            irCallImplIrCallImplWithShape$default.getArguments().set(1, convertToIrExpression(PrimitiveComparisonKt.getRight(comparisonExpression), primitiveConeNumericComparisonInfoInferPrimitiveNumericComparisonInfo, false));
            return irCallImplIrCallImplWithShape$default;
        }
        return generateComparisonCall$fallbackToRealCall(this, operation, comparisonExpression, startOffset, endOffset);
    }

    private static final IrExpression generateComparisonCall$fallbackToRealCall(OperatorExpressionGenerator operatorExpressionGenerator, FirOperation firOperation, FirComparisonExpression firComparisonExpression, int i, int i2) {
        Pair<IrSimpleFunctionSymbol, IrStatementOriginImpl> symbolAndOriginForComparison = operatorExpressionGenerator.getSymbolAndOriginForComparison(firOperation, IrTypesKt.getClassifierOrFail(operatorExpressionGenerator.getBuiltins().getIntType()));
        IrSimpleFunctionSymbol irSimpleFunctionSymbol = (IrSimpleFunctionSymbol) symbolAndOriginForComparison.component1();
        IrStatementOriginImpl irStatementOriginImpl = (IrStatementOriginImpl) symbolAndOriginForComparison.component2();
        Object objAccept = firComparisonExpression.getCompareToCall().accept(operatorExpressionGenerator.visitor, null);
        objAccept.getClass();
        IrCall irCall = (IrCall) objAccept;
        irCall.setOrigin(irStatementOriginImpl);
        IrType booleanType = operatorExpressionGenerator.getBuiltins().getBooleanType();
        irSimpleFunctionSymbol.getClass();
        IrCallImpl irCallImplIrCallImplWithShape$default = BuildersKt.IrCallImplWithShape$default(i, i2, booleanType, irSimpleFunctionSymbol, 0, 2, 0, false, false, irStatementOriginImpl, (IrClassSymbol) null, 1024, (Object) null);
        irCallImplIrCallImplWithShape$default.getArguments().set(0, irCall);
        irCallImplIrCallImplWithShape$default.getArguments().set(1, IrConstImpl.Companion.int(i, i2, operatorExpressionGenerator.getBuiltins().getIntType(), 0));
        return irCallImplIrCallImplWithShape$default;
    }

    private final IrExpression generateEqualityOperatorCall(int startOffset, int endOffset, FirOperation operation, List<? extends FirExpression> arguments) {
        int i = WhenMappings.$EnumSwitchMapping$0[operation.ordinal()];
        if (i == 5 || i == 6) {
            return transformEqualityOperatorCall(startOffset, endOffset, operation, arguments);
        }
        if (i == 7 || i == 8) {
            return transformIdentityOperatorCall(startOffset, endOffset, operation, arguments);
        }
        w04.a("Unexpected operation: ", operation);
        return null;
    }

    private final Pair<IrSimpleFunctionSymbol, IrStatementOriginImpl> getSymbolAndOriginForComparison(FirOperation operation, IrClassifierSymbol classifier) {
        int i = WhenMappings.$EnumSwitchMapping$0[operation.ordinal()];
        if (i == 1) {
            return TuplesKt.to(getBuiltins().getLessFunByOperandType().get(classifier), IrStatementOrigin.Companion.getLT());
        }
        if (i == 2) {
            return TuplesKt.to(getBuiltins().getGreaterFunByOperandType().get(classifier), IrStatementOrigin.Companion.getGT());
        }
        if (i == 3) {
            return TuplesKt.to(getBuiltins().getLessOrEqualFunByOperandType().get(classifier), IrStatementOrigin.Companion.getLTEQ());
        }
        if (i == 4) {
            return TuplesKt.to(getBuiltins().getGreaterOrEqualFunByOperandType().get(classifier), IrStatementOrigin.Companion.getGTEQ());
        }
        w04.a("Unexpected comparison operation: ", operation);
        return null;
    }

    private final IrExpression negate(IrExpression irExpression, IrStatementOrigin irStatementOrigin) {
        return PrimitivesKt.primitiveOp1(irExpression.getStartOffset(), irExpression.getEndOffset(), getBuiltins().getBooleanNotSymbol(), getBuiltins().getBooleanType(), irStatementOrigin, irExpression);
    }

    private final IrDynamicOperator toIrDynamicOperator(IrStatementOrigin irStatementOrigin) {
        IrStatementOrigin.Companion companion = IrStatementOrigin.Companion;
        if (Intrinsics.areEqual(irStatementOrigin, companion.getEQEQ())) {
            return IrDynamicOperator.EQEQ;
        }
        if (Intrinsics.areEqual(irStatementOrigin, companion.getEXCLEQ())) {
            return IrDynamicOperator.EXCLEQ;
        }
        if (Intrinsics.areEqual(irStatementOrigin, companion.getEQEQEQ())) {
            return IrDynamicOperator.EQEQEQ;
        }
        if (Intrinsics.areEqual(irStatementOrigin, companion.getEXCLEQEQ())) {
            return IrDynamicOperator.EXCLEQEQ;
        }
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final IrExpression transformEqualityOperatorCall(int startOffset, int endOffset, FirOperation operation, List<? extends FirExpression> arguments) throws Exception {
        IrStatementOriginImpl eqeq;
        IrSimpleFunctionSymbol eqeqSymbol;
        IrClassSymbol irClassSymbol;
        int i = WhenMappings.$EnumSwitchMapping$0[operation.ordinal()];
        if (i == 5) {
            eqeq = IrStatementOrigin.Companion.getEQEQ();
        } else {
            if (i != 6) {
                w04.a("Not an equality operation: ", operation);
                return null;
            }
            eqeq = IrStatementOrigin.Companion.getEXCLEQ();
        }
        IrStatementOriginImpl irStatementOriginImpl = eqeq;
        PrimitiveConeNumericComparisonInfo primitiveConeNumericComparisonInfoInferPrimitiveNumericComparisonInfo = PrimitiveComparisonKt.inferPrimitiveNumericComparisonInfo(this, arguments.get(0), arguments.get(1));
        IrExpression irExpressionConvertToIrExpression = convertToIrExpression(arguments.get(0), primitiveConeNumericComparisonInfoInferPrimitiveNumericComparisonInfo, true);
        IrExpression irExpressionConvertToIrExpression2 = convertToIrExpression(arguments.get(1), primitiveConeNumericComparisonInfoInferPrimitiveNumericComparisonInfo, false);
        IrDynamicOperatorExpressionImpl irDynamicOperatorExpressionImplTryGenerateDynamicOperatorCall = tryGenerateDynamicOperatorCall(startOffset, endOffset, irExpressionConvertToIrExpression, irExpressionConvertToIrExpression2, irStatementOriginImpl);
        if (irDynamicOperatorExpressionImplTryGenerateDynamicOperatorCall != null) {
            return irDynamicOperatorExpressionImplTryGenerateDynamicOperatorCall;
        }
        ConeClassLikeType comparisonType = primitiveConeNumericComparisonInfoInferPrimitiveNumericComparisonInfo != null ? primitiveConeNumericComparisonInfoInferPrimitiveNumericComparisonInfo.getComparisonType() : null;
        if (comparisonType == null || (irClassSymbol = getTypeConverter().getClassIdToSymbolMap$org_jetbrains_kotlin_fir2ir().get(comparisonType.getLookupTag().getClassId())) == null || (eqeqSymbol = getBuiltins().getIeee754equalsFunByOperandType().get(irClassSymbol)) == null) {
            eqeqSymbol = getBuiltins().getEqeqSymbol();
        }
        IrCallImpl irCallImplIrCallImplWithShape$default = BuildersKt.IrCallImplWithShape$default(startOffset, endOffset, getBuiltins().getBooleanType(), eqeqSymbol, 0, 2, 0, false, false, irStatementOriginImpl, (IrClassSymbol) null, 1024, (Object) null);
        irCallImplIrCallImplWithShape$default.getArguments().set(0, irExpressionConvertToIrExpression);
        irCallImplIrCallImplWithShape$default.getArguments().set(1, irExpressionConvertToIrExpression2);
        return operation == FirOperation.EQ ? irCallImplIrCallImplWithShape$default : negate(irCallImplIrCallImplWithShape$default, irStatementOriginImpl);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final IrExpression transformIdentityOperatorCall(int startOffset, int endOffset, FirOperation operation, List<? extends FirExpression> arguments) throws Exception {
        IrStatementOriginImpl eqeqeq;
        int i = WhenMappings.$EnumSwitchMapping$0[operation.ordinal()];
        if (i == 7) {
            eqeqeq = IrStatementOrigin.Companion.getEQEQEQ();
        } else {
            if (i != 8) {
                w04.a("Not an identity operation: ", operation);
                return null;
            }
            eqeqeq = IrStatementOrigin.Companion.getEXCLEQEQ();
        }
        IrStatementOriginImpl irStatementOriginImpl = eqeqeq;
        IrExpression irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default = Fir2IrVisitor.convertToIrExpression$org_jetbrains_kotlin_fir2ir$default(this.visitor, arguments.get(0), false, null, 6, null);
        IrExpression irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default2 = Fir2IrVisitor.convertToIrExpression$org_jetbrains_kotlin_fir2ir$default(this.visitor, arguments.get(1), false, null, 6, null);
        IrDynamicOperatorExpressionImpl irDynamicOperatorExpressionImplTryGenerateDynamicOperatorCall = tryGenerateDynamicOperatorCall(startOffset, endOffset, irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default, irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default2, irStatementOriginImpl);
        if (irDynamicOperatorExpressionImplTryGenerateDynamicOperatorCall != null) {
            return irDynamicOperatorExpressionImplTryGenerateDynamicOperatorCall;
        }
        IrCallImpl irCallImplIrCallImplWithShape$default = BuildersKt.IrCallImplWithShape$default(startOffset, endOffset, getBuiltins().getBooleanType(), getBuiltins().getEqeqeqSymbol(), 0, 2, 0, false, false, irStatementOriginImpl, (IrClassSymbol) null, 1024, (Object) null);
        irCallImplIrCallImplWithShape$default.getArguments().set(0, irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default);
        irCallImplIrCallImplWithShape$default.getArguments().set(1, irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default2);
        return operation == FirOperation.IDENTITY ? irCallImplIrCallImplWithShape$default : negate(irCallImplIrCallImplWithShape$default, irStatementOriginImpl);
    }

    private final IrDynamicOperatorExpressionImpl tryGenerateDynamicOperatorCall(int startOffset, int endOffset, IrExpression firstArgument, IrExpression secondArgument, IrStatementOrigin origin) throws Exception {
        if (!(firstArgument.getType() instanceof IrDynamicType)) {
            return null;
        }
        IrDynamicOperator irDynamicOperator = toIrDynamicOperator(origin);
        if (irDynamicOperator == null) {
            throw new Exception("Couldn't convert to the corresponding IrDynamicOperator");
        }
        IrDynamicOperatorExpressionImpl IrDynamicOperatorExpressionImpl = BuildersKt.IrDynamicOperatorExpressionImpl(startOffset, endOffset, getBuiltins().getBooleanType(), irDynamicOperator);
        IrDynamicOperatorExpressionImpl.setReceiver(firstArgument);
        IrDynamicOperatorExpressionImpl.getArguments().add(secondArgument);
        return IrDynamicOperatorExpressionImpl;
    }

    /* JADX WARN: Code duplicated, block: B:30:0x0063  */
    public final IrExpression convertComparisonExpression(FirComparisonExpression comparisonExpression) {
        int endOffset;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        comparisonExpression.getClass();
        KtSourceElement source = comparisonExpression.getSource();
        int i = -1;
        if (OffsetUtilsKt.isCompiledElement(KtSourceElementKt.getPsi(source))) {
            endOffset = -1;
        } else {
            if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
                endOffset = -1;
            } else {
                if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
                    endOffset = -1;
                } else {
                    if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
                        endOffset = -1;
                    } else {
                        if (source == null || (numStartOffsetSkippingComments = OffsetUtilsKt.startOffsetSkippingComments(source, null)) == null) {
                            startOffset = source != null ? source.getStartOffset() : -1;
                        } else {
                            startOffset = numStartOffsetSkippingComments.intValue();
                        }
                        i = startOffset;
                        endOffset = source != null ? source.getEndOffset() : -1;
                    }
                }
            }
        }
        return generateComparisonCall(i, endOffset, comparisonExpression);
    }

    /* JADX WARN: Code duplicated, block: B:30:0x0063  */
    public final IrExpression convertEqualityOperatorCall(FirEqualityOperatorCall equalityOperatorCall) {
        int endOffset;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        equalityOperatorCall.getClass();
        KtSourceElement source = equalityOperatorCall.getSource();
        int i = -1;
        if (OffsetUtilsKt.isCompiledElement(KtSourceElementKt.getPsi(source))) {
            endOffset = -1;
        } else {
            if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
                endOffset = -1;
            } else {
                if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
                    endOffset = -1;
                } else {
                    if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
                        endOffset = -1;
                    } else {
                        if (source == null || (numStartOffsetSkippingComments = OffsetUtilsKt.startOffsetSkippingComments(source, null)) == null) {
                            startOffset = source != null ? source.getStartOffset() : -1;
                        } else {
                            startOffset = numStartOffsetSkippingComments.intValue();
                        }
                        i = startOffset;
                        endOffset = source != null ? source.getEndOffset() : -1;
                    }
                }
            }
        }
        return generateEqualityOperatorCall(i, endOffset, equalityOperatorCall.getOperation(), equalityOperatorCall.getArgumentList().getArguments());
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public AdapterGenerator getAdapterGenerator() {
        return this.c.getAdapterGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public AnnotationGenerator getAnnotationGenerator() {
        return this.c.getAnnotationGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrIrGeneratedDeclarationsRegistrar getAnnotationsFromPluginRegistrar() {
        return this.c.getAnnotationsFromPluginRegistrar();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrBuiltinSymbolsContainer getBuiltins() {
        return this.c.getBuiltins();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public CallAndReferenceGenerator getCallGenerator() {
        return this.c.getCallGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrCallableDeclarationsGenerator getCallablesGenerator() {
        return this.c.getCallablesGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrClassifierStorage getClassifierStorage() {
        return this.c.getClassifierStorage();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrClassifiersGenerator getClassifiersGenerator() {
        return this.c.getClassifiersGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrConfiguration getConfiguration() {
        return this.c.getConfiguration();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrConverter getConverter() {
        return this.c.getConverter();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrDataClassMembersGenerator getDataClassMembersGenerator() {
        return this.c.getDataClassMembersGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrDeclarationStorage getDeclarationStorage() {
        return this.c.getDeclarationStorage();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrExtensions getExtensions() {
        return this.c.getExtensions();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Set<FirFile> getFilesBeingCompiled() {
        return this.c.getFilesBeingCompiled();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public FirProviderWithGeneratedFiles getFirProvider() {
        return this.c.getFirProvider();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrImplicitCastInserter getImplicitCastInserter() {
        return this.c.getImplicitCastInserter();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public KotlinMangler.IrMangler getIrMangler() {
        return this.c.getIrMangler();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public List<IrProvider> getIrProviders() {
        return this.c.getIrProviders();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrLazyDeclarationsGenerator getLazyDeclarationsGenerator() {
        return this.c.getLazyDeclarationsGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrLazyFakeOverrideGenerator getLazyFakeOverrideGenerator() {
        return this.c.getLazyFakeOverrideGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public IrLock getLock() {
        return this.c.getLock();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents, org.jetbrains.kotlin.fir.ScopeSessionHolder
    public ScopeSession getScopeSession() {
        return this.c.getScopeSession();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents, org.jetbrains.kotlin.fir.SessionHolder
    public FirSession getSession() {
        return this.c.getSession();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public IrSpecialAnnotationsProvider getSpecialAnnotationsProvider() {
        return this.c.getSpecialAnnotationsProvider();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrSymbolsMappingForLazyClasses getSymbolsMappingForLazyClasses() {
        return this.c.getSymbolsMappingForLazyClasses();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrTypeConverter getTypeConverter() {
        return this.c.getTypeConverter();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrVisibilityConverter getVisibilityConverter() {
        return this.c.getVisibilityConverter();
    }

    private final IrDynamicOperator toIrDynamicOperator(FirOperation firOperation) {
        int i = WhenMappings.$EnumSwitchMapping$0[firOperation.ordinal()];
        if (i == 1) {
            return IrDynamicOperator.LT;
        }
        if (i == 2) {
            return IrDynamicOperator.GT;
        }
        if (i == 3) {
            return IrDynamicOperator.LE;
        }
        if (i != 4) {
            return null;
        }
        return IrDynamicOperator.GE;
    }
}
