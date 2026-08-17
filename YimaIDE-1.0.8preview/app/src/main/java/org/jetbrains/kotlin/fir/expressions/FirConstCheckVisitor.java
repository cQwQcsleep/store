package org.jetbrains.kotlin.fir.expressions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SpreadBuilder;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirEvaluatorResult;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.references.FirNamedReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.references.FirResolvedNamedReference;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirEnumEntrySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirLocalPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirThisOwnerSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeTypesKt;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FunctionalTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.InferenceUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.name.StandardClassIds$Annotations;
import org.jetbrains.kotlin.resolve.constants.evaluate.CompileTimeType;
import org.jetbrains.kotlin.resolve.constants.evaluate.OperationsMapGeneratedKt;
import org.jetbrains.kotlin.types.ConstantValueKind;
import org.jetbrains.kotlin.util.OperatorNameConventions;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000´\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u0000 y2\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001:\u0001yB\u0017\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ-\u0010\r\u001a\u0004\u0018\u0001H\u000e\"\u0004\b\u0000\u0010\u000e*\u0006\u0012\u0002\b\u00030\u000f2\u000e\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001H\u000e0\u0011H\u0002¢\u0006\u0002\u0010\u0012J\u001a\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u0016\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010\u001d\u001a\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010 \u001a\u00020\u00022\u0006\u0010!\u001a\u00020\"2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010#\u001a\u00020\u00022\u0006\u0010$\u001a\u00020%2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010&\u001a\u00020\u00022\u0006\u0010'\u001a\u00020(2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010)\u001a\u00020\u00022\u0006\u0010*\u001a\u00020+2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010,\u001a\u00020\u00022\u0006\u0010-\u001a\u00020.2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010/\u001a\u00020\u00022\u0006\u00100\u001a\u0002012\b\u0010\u0016\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u00102\u001a\u00020\u00022\u0006\u00103\u001a\u0002042\b\u0010\u0016\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u00105\u001a\u00020\u00022\u0006\u00106\u001a\u0002072\b\u0010\u0016\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u00108\u001a\u00020\u00022\u0006\u00109\u001a\u00020:2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010;\u001a\u00020\u00022\u0006\u0010<\u001a\u00020=2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010>\u001a\u00020\u00022\u0006\u0010?\u001a\u00020@2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010A\u001a\u00020\u00022\u0006\u0010B\u001a\u00020C2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010D\u001a\u00020\u00022\u0006\u0010E\u001a\u00020F2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010G\u001a\u00020\u00022\u0006\u0010H\u001a\u00020I2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010J\u001a\u00020\u00022\u0006\u0010K\u001a\u00020L2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0003H\u0016J\u0018\u0010M\u001a\u00020\u00022\u0006\u0010K\u001a\u00020L2\u0006\u0010N\u001a\u00020OH\u0002J\u0018\u0010P\u001a\u00020\u00022\u0006\u0010Q\u001a\u00020L2\u0006\u0010N\u001a\u00020RH\u0002J\u001a\u0010S\u001a\u00020\u00022\u0006\u0010T\u001a\u00020U2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010V\u001a\u00020\u00022\u0006\u0010W\u001a\u00020X2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010Y\u001a\u00020\u00022\u0006\u0010Z\u001a\u00020[2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010\\\u001a\u00020\u00022\u0006\u0010]\u001a\u00020^2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010_\u001a\u00020\u00022\u0006\u0010`\u001a\u00020a2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010b\u001a\u00020\u00022\u0006\u0010c\u001a\u00020d2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010e\u001a\u00020\u00022\u0006\u0010f\u001a\u00020g2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010h\u001a\u00020\u00022\u0006\u0010i\u001a\u00020j2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0003H\u0016J\u0010\u0010k\u001a\u00020\u0007*\u0006\u0012\u0002\b\u00030lH\u0002J\u000e\u0010m\u001a\u0004\u0018\u00010n*\u00020oH\u0002J\f\u0010p\u001a\u00020\u0007*\u00020qH\u0002J\f\u0010r\u001a\u00020o*\u00020qH\u0002J\f\u0010s\u001a\u00020\u0007*\u00020LH\u0002J\f\u0010t\u001a\u00020\u0007*\u00020uH\u0002J\u0012\u0010v\u001a\u00020\u0007*\b\u0012\u0002\b\u0003\u0018\u00010\u000fH\u0002J\u0012\u0010w\u001a\u00020\u0007*\b\u0012\u0002\b\u0003\u0018\u00010\u000fH\u0002J\u0018\u0010x\u001a\b\u0012\u0002\b\u0003\u0018\u00010l*\b\u0012\u0002\b\u0003\u0018\u00010\u000fH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006z"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/FirConstCheckVisitor;", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "Lorg/jetbrains/kotlin/fir/expressions/ConstantArgumentKind;", Argument.Delimiters.none, "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "calledOnCheckerStage", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Z)V", "getCalledOnCheckerStage", "()Z", "intrinsicConstEvaluation", "visit", "T", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "block", "Lkotlin/Function0;", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "visitElement", "element", "Lorg/jetbrains/kotlin/fir/FirElement;", "data", "visitErrorExpression", "errorExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirErrorExpression;", "visitNamedArgumentExpression", "namedArgumentExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirNamedArgumentExpression;", "visitSpreadArgumentExpression", "spreadArgumentExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirSpreadArgumentExpression;", "visitTypeOperatorCall", "typeOperatorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirTypeOperatorCall;", "visitWhenExpression", "whenExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;", "visitWhenSubjectExpression", "whenSubjectExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenSubjectExpression;", "visitLiteralExpression", "literalExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirLiteralExpression;", "visitComparisonExpression", "comparisonExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirComparisonExpression;", "visitStringConcatenationCall", "stringConcatenationCall", "Lorg/jetbrains/kotlin/fir/expressions/FirStringConcatenationCall;", "visitEqualityOperatorCall", "equalityOperatorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirEqualityOperatorCall;", "visitBooleanOperatorExpression", "booleanOperatorExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirBooleanOperatorExpression;", "visitGetClassCall", "getClassCall", "Lorg/jetbrains/kotlin/fir/expressions/FirGetClassCall;", "visitVarargArgumentsExpression", "varargArgumentsExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirVarargArgumentsExpression;", "visitCollectionLiteral", "collectionLiteral", "Lorg/jetbrains/kotlin/fir/expressions/FirCollectionLiteral;", "visitThisReceiverExpression", "thisReceiverExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirThisReceiverExpression;", "visitPropertyAccessExpression", "propertyAccessExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;", "visitIntegerLiteralOperatorCall", "integerLiteralOperatorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirIntegerLiteralOperatorCall;", "visitFunctionCall", "functionCall", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "visitNamedFunction", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "visitConstructorCall", "constructorCall", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirConstructorSymbol;", "visitQualifiedAccessExpression", "qualifiedAccessExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "visitResolvedQualifier", "resolvedQualifier", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "visitErrorResolvedQualifier", "errorResolvedQualifier", "Lorg/jetbrains/kotlin/fir/expressions/FirErrorResolvedQualifier;", "visitCallableReferenceAccess", "callableReferenceAccess", "Lorg/jetbrains/kotlin/fir/expressions/FirCallableReferenceAccess;", "visitEnumEntryDeserializedAccessExpression", "enumEntryDeserializedAccessExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirEnumEntryDeserializedAccessExpression;", "visitClassReferenceExpression", "classReferenceExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirClassReferenceExpression;", "visitAnnotationCall", "annotationCall", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationCall;", "visitAnnotation", "annotation", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "isIntrinsicConst", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "toCompileTimeType", "Lorg/jetbrains/kotlin/resolve/constants/evaluate/CompileTimeType;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "hasAllowedCompileTimeType", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "getExpandedType", "isCompileTimeBuiltinCall", "isCompileTimeBuiltinProperty", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "fromStdlib", "fromKotlinPackage", "getReferencedClassSymbol", "Companion", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class FirConstCheckVisitor extends FirVisitor {
    private static final HashSet<Name> compileTimeExtensionFunctions;
    private static final Set<Name> compileTimeFunctions;
    private static final ThreadLocal<List<FirCallableSymbol<?>>> propertyStack;
    private final boolean calledOnCheckerStage;
    private final boolean intrinsicConstEvaluation;
    private final FirSession session;

    static {
        ThreadLocal<List<FirCallableSymbol<?>>> threadLocalWithInitial = ThreadLocal.withInitial(new Supplier() { // from class: org.jetbrains.kotlin.fir.expressions.b
            @Override // java.util.function.Supplier
            public final Object get() {
                return FirConstCheckVisitor.c();
            }
        });
        threadLocalWithInitial.getClass();
        propertyStack = threadLocalWithInitial;
        SpreadBuilder spreadBuilder = new SpreadBuilder(4);
        spreadBuilder.addSpread(OperatorNameConventions.SIMPLE_BINARY_OPERATION_NAMES.toArray(new Name[0]));
        spreadBuilder.addSpread(OperatorNameConventions.SIMPLE_UNARY_OPERATION_NAMES.toArray(new Name[0]));
        spreadBuilder.addSpread(OperatorNameConventions.SIMPLE_BITWISE_OPERATION_NAMES.toArray(new Name[0]));
        spreadBuilder.add(OperatorNameConventions.COMPARE_TO);
        compileTimeFunctions = SetsKt.setOf(spreadBuilder.toArray(new Name[spreadBuilder.size()]));
        List listListOf = CollectionsKt.listOf(new String[]{"floorDiv", "mod", "code"});
        HashSet<Name> hashSet = new HashSet<>();
        Iterator it = listListOf.iterator();
        while (it.hasNext()) {
            hashSet.add(Name.identifier((String) it.next()));
        }
        compileTimeExtensionFunctions = hashSet;
    }

    public FirConstCheckVisitor(FirSession firSession, boolean z) {
        firSession.getClass();
        this.session = firSession;
        this.calledOnCheckerStage = z;
        this.intrinsicConstEvaluation = FirLanguageSettingsComponentKt.getLanguageVersionSettings(firSession).supportsFeature(LanguageFeature.IntrinsicConstEvaluation);
    }

    public static ConstantArgumentKind b(FirExpression firExpression, FirConstCheckVisitor firConstCheckVisitor, Void r2) {
        if (firExpression != null) {
            return (ConstantArgumentKind) firExpression.accept(firConstCheckVisitor, r2);
        }
        return null;
    }

    public static List c() {
        return new ArrayList();
    }

    private final boolean fromKotlinPackage(FirCallableSymbol<?> firCallableSymbol) {
        CallableId callableId;
        FqName packageName;
        return Intrinsics.areEqual((firCallableSymbol == null || (callableId = firCallableSymbol.getCallableId()) == null || (packageName = callableId.getPackageName()) == null) ? null : packageName.asString(), StandardNames.BUILT_INS_PACKAGE_NAME.asString());
    }

    private final boolean fromStdlib(FirCallableSymbol<?> firCallableSymbol) {
        CallableId callableId;
        FqName packageName;
        return (firCallableSymbol == null || (callableId = firCallableSymbol.getCallableId()) == null || (packageName = callableId.getPackageName()) == null || !packageName.startsWith(StandardNames.BUILT_INS_PACKAGE_NAME)) ? false : true;
    }

    private final ConeKotlinType getExpandedType(FirExpression firExpression) {
        return TypeExpansionUtilsKt.fullyExpandedType$default(FirTypeUtilsKt.getResolvedType(firExpression), this.session, (Function1) null, 2, (Object) null);
    }

    private final FirBasedSymbol<?> getReferencedClassSymbol(FirCallableSymbol<?> firCallableSymbol) {
        FirResolvedTypeRef resolvedReturnTypeRef;
        ConeKotlinType coneType;
        if (firCallableSymbol == null || (resolvedReturnTypeRef = firCallableSymbol.getResolvedReturnTypeRef()) == null || (coneType = resolvedReturnTypeRef.getConeType()) == null) {
            return null;
        }
        return ToSymbolUtilsKt.toSymbol(coneType, this.session);
    }

    private final boolean hasAllowedCompileTimeType(FirExpression firExpression) {
        if (firExpression instanceof FirErrorExpression) {
            return true;
        }
        ConeSimpleKotlinType coneSimpleKotlinTypeFullyExpandedType$default = TypeExpansionUtilsKt.fullyExpandedType$default(ConeTypesKt.unwrapToSimpleTypeUsingLowerBound(FirTypeUtilsKt.getResolvedType(firExpression)), this.session, (Function1) null, 2, (Object) null);
        return CollectionsKt.contains(StandardClassIds.INSTANCE.getConstantAllowedTypes(), ConeTypeUtilsKt.getClassId(coneSimpleKotlinTypeFullyExpandedType$default)) && !ConeTypeUtilsKt.isMarkedNullable(coneSimpleKotlinTypeFullyExpandedType$default);
    }

    private final boolean isCompileTimeBuiltinCall(FirFunctionCall firFunctionCall) {
        CompileTimeType compileTimeType;
        ConeKotlinType expandedType;
        CallableId callableId;
        ConeKotlinType expandedType2;
        ConeKotlinType expandedType3;
        ConeKotlinType expandedType4;
        FirNamedReference calleeReference = firFunctionCall.getCalleeReference();
        if (!(calleeReference instanceof FirResolvedNamedReference)) {
            return false;
        }
        FirResolvedNamedReference firResolvedNamedReference = (FirResolvedNamedReference) calleeReference;
        Name name = firResolvedNamedReference.getName();
        FirBasedSymbol<?> resolvedSymbol = firResolvedNamedReference.getResolvedSymbol();
        CompileTimeType compileTimeType2 = null;
        FirCallableSymbol<?> firCallableSymbol = resolvedSymbol instanceof FirCallableSymbol ? (FirCallableSymbol) resolvedSymbol : null;
        if (!fromStdlib(firCallableSymbol)) {
            return false;
        }
        FirExpression dispatchReceiver = firFunctionCall.getDispatchReceiver();
        ClassId classId = (dispatchReceiver == null || (expandedType4 = getExpandedType(dispatchReceiver)) == null) ? null : ConeTypeUtilsKt.getClassId(expandedType4);
        if (!this.intrinsicConstEvaluation) {
            if (!fromKotlinPackage(firCallableSymbol)) {
                return false;
            }
            StandardClassIds standardClassIds = StandardClassIds.INSTANCE;
            if (CollectionsKt.contains(standardClassIds.getUnsignedTypes(), classId)) {
                return false;
            }
            if (compileTimeFunctions.contains(name) || compileTimeExtensionFunctions.contains(name) || Intrinsics.areEqual(name, OperatorNameConventions.TO_STRING) || OperatorNameConventions.NUMBER_CONVERSIONS.contains(name)) {
                return true;
            }
            return Intrinsics.areEqual(firResolvedNamedReference.getName(), OperatorNameConventions.GET) && Intrinsics.areEqual(classId, standardClassIds.getString());
        }
        if (firCallableSymbol != null && isIntrinsicConst(firCallableSymbol)) {
            return true;
        }
        FirExpression dispatchReceiver2 = firFunctionCall.getDispatchReceiver();
        if (dispatchReceiver2 == null || (expandedType3 = getExpandedType(dispatchReceiver2)) == null || (compileTimeType = toCompileTimeType(expandedType3)) == null) {
            FirExpression extensionReceiver = firFunctionCall.getExtensionReceiver();
            compileTimeType = (extensionReceiver == null || (expandedType = getExpandedType(extensionReceiver)) == null) ? null : toCompileTimeType(expandedType);
        }
        FirExpression firExpression = (FirExpression) CollectionsKt.firstOrNull(firFunctionCall.getArgumentList().getArguments());
        if (firExpression != null && (expandedType2 = getExpandedType(firExpression)) != null) {
            compileTimeType2 = toCompileTimeType(expandedType2);
        }
        if (firCallableSymbol == null || (callableId = firCallableSymbol.getCallableId()) == null) {
            return false;
        }
        return OperationsMapGeneratedKt.canEvalOp(callableId, compileTimeType, compileTimeType2);
    }

    private final boolean isCompileTimeBuiltinProperty(FirPropertySymbol firPropertySymbol) {
        CompileTimeType compileTimeType;
        ConeKotlinType dispatchReceiverType = firPropertySymbol.getDispatchReceiverType();
        if (dispatchReceiverType == null) {
            FirResolvedTypeRef resolvedReceiverTypeRef = firPropertySymbol.getResolvedReceiverTypeRef();
            if (resolvedReceiverTypeRef == null || (dispatchReceiverType = resolvedReceiverTypeRef.getConeType()) == null) {
                dispatchReceiverType = null;
            }
            if (dispatchReceiverType == null) {
                return false;
            }
        }
        ClassId classId = ConeTypeUtilsKt.getClassId(TypeExpansionUtilsKt.fullyExpandedType$default(dispatchReceiverType, this.session, (Function1) null, 2, (Object) null));
        if (classId == null) {
            return false;
        }
        if (this.intrinsicConstEvaluation) {
            if (isIntrinsicConst(firPropertySymbol)) {
                return true;
            }
            CallableId callableId = firPropertySymbol.getCallableId();
            if (callableId == null || (compileTimeType = toCompileTimeType(dispatchReceiverType)) == null) {
                return false;
            }
            return OperationsMapGeneratedKt.canEvalOp(callableId, compileTimeType, (CompileTimeType) null);
        }
        String strAsString = firPropertySymbol.getName().asString();
        int iHashCode = strAsString.hashCode();
        if (iHashCode != -1106363674) {
            if (iHashCode == 3059181 && strAsString.equals("code")) {
                return Intrinsics.areEqual(classId, StandardClassIds.INSTANCE.getChar());
            }
        } else if (strAsString.equals("length")) {
            return Intrinsics.areEqual(classId, StandardClassIds.INSTANCE.getString());
        }
        return false;
    }

    private final boolean isIntrinsicConst(FirBasedSymbol<?> firBasedSymbol) {
        return FirAnnotationUtilsKt.hasAnnotation(firBasedSymbol, StandardClassIds$Annotations.INSTANCE.getIntrinsicConstEvaluation(), this.session);
    }

    private final CompileTimeType toCompileTimeType(ConeKotlinType coneKotlinType) {
        ConstantValueKind constantValueKind;
        if (Intrinsics.areEqual(ConeTypeUtilsKt.getClassId(coneKotlinType), StandardClassIds.INSTANCE.getAny())) {
            return CompileTimeType.ANY;
        }
        ClassId classId = ConeTypeUtilsKt.getClassId(coneKotlinType);
        if (classId == null || (constantValueKind = FirExpressionEvaluatorKt.toConstantValueKind(classId)) == null) {
            return null;
        }
        return FirExpressionEvaluatorKt.toCompileTimeType(constantValueKind);
    }

    private final <T> T visit(FirCallableSymbol<?> firCallableSymbol, Function0<? extends T> function0) {
        List<FirCallableSymbol<?>> list = propertyStack.get();
        list.getClass();
        list.add(firCallableSymbol);
        try {
            return (T) function0.invoke();
        } finally {
            List<FirCallableSymbol<?>> list2 = propertyStack.get();
            list2.getClass();
            CollectionsKt.removeLast(list2);
        }
    }

    private final ConstantArgumentKind visitConstructorCall(FirFunctionCall constructorCall, FirConstructorSymbol symbol) {
        FirRegularClassSymbol firRegularClassSymbolFullyExpandedClass;
        FirBasedSymbol<?> referencedClassSymbol = getReferencedClassSymbol(symbol);
        FirClassLikeSymbol firClassLikeSymbol = referencedClassSymbol instanceof FirClassLikeSymbol ? (FirClassLikeSymbol) referencedClassSymbol : null;
        if (((firClassLikeSymbol == null || (firRegularClassSymbolFullyExpandedClass = DeclarationUtilsKt.fullyExpandedClass((FirClassLikeSymbol<?>) firClassLikeSymbol, this.session)) == null) ? null : firRegularClassSymbolFullyExpandedClass.getClassKind()) != ClassKind.ANNOTATION_CLASS && !ConeBuiltinTypeUtilsKt.isUnsignedType(getExpandedType(constructorCall))) {
            return ConstantArgumentKind.NOT_CONST;
        }
        Iterator<T> it = constructorCall.getArgumentList().getArguments().iterator();
        while (it.hasNext()) {
            ConstantArgumentKind constantArgumentKind = (ConstantArgumentKind) ((FirExpression) it.next()).accept(this, null);
            if (constantArgumentKind != ConstantArgumentKind.VALID_CONST) {
                return constantArgumentKind;
            }
        }
        return ConstantArgumentKind.VALID_CONST;
    }

    private final ConstantArgumentKind visitNamedFunction(FirFunctionCall functionCall, FirNamedFunctionSymbol symbol) {
        if (!isCompileTimeBuiltinCall(functionCall)) {
            return ConstantArgumentKind.NOT_CONST;
        }
        for (FirExpression firExpression : CollectionsKt.plus(CollectionsKt.plus(functionCall.getArgumentList().getArguments(), functionCall.getDispatchReceiver()), functionCall.getExtensionReceiver())) {
            if (firExpression != null) {
                if (!hasAllowedCompileTimeType(firExpression)) {
                    return ConstantArgumentKind.NOT_CONST;
                }
                ConstantArgumentKind constantArgumentKind = (ConstantArgumentKind) firExpression.accept(this, null);
                if (constantArgumentKind != ConstantArgumentKind.VALID_CONST) {
                    return constantArgumentKind;
                }
            }
        }
        return ConstantArgumentKind.VALID_CONST;
    }

    public final boolean getCalledOnCheckerStage() {
        return this.calledOnCheckerStage;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public ConstantArgumentKind visitAnnotation(FirAnnotation annotation, Void data) {
        annotation.getClass();
        Iterator<FirExpression> it = annotation.getArgumentMapping().getMapping().values().iterator();
        while (it.hasNext()) {
            ConstantArgumentKind constantArgumentKind = (ConstantArgumentKind) it.next().accept(this, data);
            if (constantArgumentKind != ConstantArgumentKind.VALID_CONST) {
                return constantArgumentKind;
            }
        }
        return ConstantArgumentKind.VALID_CONST;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public ConstantArgumentKind visitAnnotationCall(FirAnnotationCall annotationCall, Void data) {
        annotationCall.getClass();
        return visitAnnotation((FirAnnotation) annotationCall, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public ConstantArgumentKind visitBooleanOperatorExpression(FirBooleanOperatorExpression booleanOperatorExpression, Void data) {
        booleanOperatorExpression.getClass();
        if (!ConeBuiltinTypeUtilsKt.isBoolean(FirTypeUtilsKt.getResolvedType(booleanOperatorExpression.getLeftOperand())) || !ConeBuiltinTypeUtilsKt.isBoolean(FirTypeUtilsKt.getResolvedType(booleanOperatorExpression.getRightOperand()))) {
            return ConstantArgumentKind.NOT_CONST;
        }
        ConstantArgumentKind constantArgumentKind = (ConstantArgumentKind) booleanOperatorExpression.getLeftOperand().accept(this, data);
        ConstantArgumentKind constantArgumentKind2 = ConstantArgumentKind.VALID_CONST;
        if (constantArgumentKind != constantArgumentKind2) {
            return constantArgumentKind;
        }
        ConstantArgumentKind constantArgumentKind3 = (ConstantArgumentKind) booleanOperatorExpression.getRightOperand().accept(this, data);
        return constantArgumentKind3 != constantArgumentKind2 ? constantArgumentKind3 : constantArgumentKind2;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public ConstantArgumentKind visitCallableReferenceAccess(FirCallableReferenceAccess callableReferenceAccess, Void data) {
        callableReferenceAccess.getClass();
        return visitQualifiedAccessExpression((FirQualifiedAccessExpression) callableReferenceAccess, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public ConstantArgumentKind visitCollectionLiteral(FirCollectionLiteral collectionLiteral, Void data) {
        collectionLiteral.getClass();
        Iterator<FirExpression> it = collectionLiteral.getArgumentList().getArguments().iterator();
        while (it.hasNext()) {
            ConstantArgumentKind constantArgumentKind = (ConstantArgumentKind) it.next().accept(this, data);
            if (constantArgumentKind != ConstantArgumentKind.VALID_CONST) {
                return constantArgumentKind;
            }
        }
        return ConstantArgumentKind.VALID_CONST;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public ConstantArgumentKind visitComparisonExpression(FirComparisonExpression comparisonExpression, Void data) {
        comparisonExpression.getClass();
        return (ConstantArgumentKind) comparisonExpression.getCompareToCall().accept(this, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public ConstantArgumentKind visitEqualityOperatorCall(FirEqualityOperatorCall equalityOperatorCall, Void data) {
        equalityOperatorCall.getClass();
        if (equalityOperatorCall.getOperation() == FirOperation.IDENTITY || equalityOperatorCall.getOperation() == FirOperation.NOT_IDENTITY) {
            return ConstantArgumentKind.NOT_CONST;
        }
        for (FirExpression firExpression : equalityOperatorCall.getArgumentList().getArguments()) {
            if ((firExpression instanceof FirLiteralExpression) && ((FirLiteralExpression) firExpression).getValue() == null) {
                return ConstantArgumentKind.NOT_CONST;
            }
            if (!hasAllowedCompileTimeType(firExpression) || (!this.intrinsicConstEvaluation && ConeBuiltinTypeUtilsKt.isUnsignedType(getExpandedType(firExpression)))) {
                return ConstantArgumentKind.NOT_CONST;
            }
            ConstantArgumentKind constantArgumentKind = (ConstantArgumentKind) firExpression.accept(this, data);
            if (constantArgumentKind != ConstantArgumentKind.VALID_CONST) {
                return constantArgumentKind;
            }
        }
        return ConstantArgumentKind.VALID_CONST;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public ConstantArgumentKind visitFunctionCall(FirFunctionCall functionCall, Void data) {
        functionCall.getClass();
        FirNamedReference calleeReference = functionCall.getCalleeReference();
        if (!(calleeReference instanceof FirResolvedNamedReference)) {
            return ConstantArgumentKind.NOT_CONST;
        }
        if (Intrinsics.areEqual(ConeTypeUtilsKt.getClassId(getExpandedType(functionCall)), StandardClassIds.INSTANCE.getKClass())) {
            return ConstantArgumentKind.NOT_KCLASS_LITERAL;
        }
        FirBasedSymbol<?> resolvedSymbol = ((FirResolvedNamedReference) calleeReference).getResolvedSymbol();
        if (resolvedSymbol instanceof FirNamedFunctionSymbol) {
            return visitNamedFunction(functionCall, (FirNamedFunctionSymbol) resolvedSymbol);
        }
        return resolvedSymbol instanceof FirConstructorSymbol ? visitConstructorCall(functionCall, (FirConstructorSymbol) resolvedSymbol) : ConstantArgumentKind.NOT_CONST;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public ConstantArgumentKind visitGetClassCall(FirGetClassCall getClassCall, Void data) {
        ConeKotlinType type;
        getClassCall.getClass();
        ConeKotlinType expandedType = getExpandedType(getClassCall.getArgument());
        if (expandedType instanceof ConeErrorType) {
            return ConstantArgumentKind.NOT_CONST;
        }
        while (Intrinsics.areEqual(ConeTypeUtilsKt.getClassId(expandedType), StandardClassIds.INSTANCE.getArray())) {
            Object objFirst = ArraysKt.first(ConeTypeUtilsKt.lowerBoundIfFlexible(expandedType).getTypeArguments());
            ConeKotlinTypeProjection coneKotlinTypeProjection = objFirst instanceof ConeKotlinTypeProjection ? (ConeKotlinTypeProjection) objFirst : null;
            if (coneKotlinTypeProjection == null || (type = coneKotlinTypeProjection.getType()) == null) {
                break;
            }
            expandedType = type;
        }
        FirExpression argument = getClassCall.getArgument();
        if (expandedType instanceof ConeTypeParameterType) {
            return ConstantArgumentKind.KCLASS_LITERAL_OF_TYPE_PARAMETER_ERROR;
        }
        if (!(argument instanceof FirResolvedQualifier) && !(argument instanceof FirClassReferenceExpression)) {
            return ConstantArgumentKind.NOT_KCLASS_LITERAL;
        }
        return ConstantArgumentKind.VALID_CONST;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public ConstantArgumentKind visitIntegerLiteralOperatorCall(FirIntegerLiteralOperatorCall integerLiteralOperatorCall, Void data) {
        integerLiteralOperatorCall.getClass();
        return visitFunctionCall((FirFunctionCall) integerLiteralOperatorCall, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public ConstantArgumentKind visitNamedArgumentExpression(FirNamedArgumentExpression namedArgumentExpression, Void data) {
        namedArgumentExpression.getClass();
        return (ConstantArgumentKind) namedArgumentExpression.getExpression().accept(this, data);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public ConstantArgumentKind visitPropertyAccessExpression(FirPropertyAccessExpression propertyAccessExpression, final Void data) {
        int i;
        ConstantArgumentKind constantArgumentKind;
        propertyAccessExpression.getClass();
        boolean z = false;
        Object obj = null;
        FirCallableSymbol<?> resolvedCallableSymbol$default = FirReferenceUtilsKt.toResolvedCallableSymbol$default(propertyAccessExpression.getCalleeReference(), false, 1, null);
        List<FirCallableSymbol<?>> list = propertyStack.get();
        list.getClass();
        if (CollectionsKt.contains(list, resolvedCallableSymbol$default)) {
            return ConstantArgumentKind.NOT_CONST;
        }
        if (resolvedCallableSymbol$default == null) {
            return ConstantArgumentKind.RESOLUTION_ERROR;
        }
        if (!(resolvedCallableSymbol$default instanceof FirPropertySymbol)) {
            if (resolvedCallableSymbol$default instanceof FirFieldSymbol) {
                if (resolvedCallableSymbol$default.getRawStatus().isStatic() && resolvedCallableSymbol$default.getResolvedStatus().getModality() == Modality.FINAL && ((FirFieldSymbol) resolvedCallableSymbol$default).getHasConstantInitializer()) {
                    return ConstantArgumentKind.VALID_CONST;
                }
            } else if (resolvedCallableSymbol$default instanceof FirEnumEntrySymbol) {
                return ConstantArgumentKind.VALID_CONST;
            }
            return ConstantArgumentKind.NOT_CONST;
        }
        FirPropertySymbol firPropertySymbol = (FirPropertySymbol) resolvedCallableSymbol$default;
        if (!(((FirProperty) firPropertySymbol.getFir()).getReturnTypeRef() instanceof FirResolvedTypeRef)) {
            return ConstantArgumentKind.NOT_CONST;
        }
        FirBasedSymbol<?> referencedClassSymbol = getReferencedClassSymbol(resolvedCallableSymbol$default);
        FirRegularClassSymbol firRegularClassSymbol = referencedClassSymbol instanceof FirRegularClassSymbol ? (FirRegularClassSymbol) referencedClassSymbol : null;
        if ((firRegularClassSymbol != null ? firRegularClassSymbol.getClassKind() : null) == ClassKind.ENUM_CLASS) {
            return ConstantArgumentKind.ENUM_NOT_CONST;
        }
        boolean zIsCompileTimeBuiltinProperty = isCompileTimeBuiltinProperty(firPropertySymbol);
        if (resolvedCallableSymbol$default.getRawStatus().isConst() || zIsCompileTimeBuiltinProperty) {
            List listListOf = CollectionsKt.listOf(new FirExpression[]{propertyAccessExpression.getDispatchReceiver(), propertyAccessExpression.getExtensionReceiver()});
            if ((listListOf instanceof Collection) && listListOf.isEmpty()) {
                i = 0;
            } else {
                Iterator it = listListOf.iterator();
                i = 0;
                while (it.hasNext()) {
                    if (((FirExpression) it.next()) != null && (i = i + 1) < 0) {
                        CollectionsKt.throwCountOverflow();
                    }
                }
            }
            if (i == 2) {
                return ConstantArgumentKind.NOT_CONST;
            }
            Iterator it2 = listListOf.iterator();
            Object obj2 = null;
            while (true) {
                if (!it2.hasNext()) {
                    if (!z) {
                        break;
                    }
                    obj = obj2;
                    break;
                }
                Object next = it2.next();
                if (((FirExpression) next) != null) {
                    if (z) {
                        break;
                    }
                    z = true;
                    obj2 = next;
                }
            }
            FirExpression firExpression = (FirExpression) obj;
            if (firExpression != null && (constantArgumentKind = (ConstantArgumentKind) firExpression.accept(this, data)) != null && constantArgumentKind != ConstantArgumentKind.VALID_CONST) {
                return constantArgumentKind;
            }
        } else {
            if (resolvedCallableSymbol$default instanceof FirLocalPropertySymbol) {
                return ConstantArgumentKind.NOT_CONST;
            }
            if (Intrinsics.areEqual(ConeTypeUtilsKt.getClassId(getExpandedType(propertyAccessExpression)), StandardClassIds.INSTANCE.getKClass())) {
                return ConstantArgumentKind.NOT_KCLASS_LITERAL;
            }
        }
        if (zIsCompileTimeBuiltinProperty) {
            return ConstantArgumentKind.VALID_CONST;
        }
        if (resolvedCallableSymbol$default.getRawStatus().isConst()) {
            FirEvaluatorResult evaluatedInitializer = DeclarationAttributesKt.getEvaluatedInitializer((FirVariable) firPropertySymbol.getFir());
            if (evaluatedInitializer != null) {
                return evaluatedInitializer instanceof FirEvaluatorResult.Evaluated ? ConstantArgumentKind.VALID_CONST : ConstantArgumentKind.NOT_CONST;
            }
            final FirExpression resolvedInitializer = firPropertySymbol.getResolvedInitializer();
            ConstantArgumentKind constantArgumentKind2 = (ConstantArgumentKind) visit(resolvedCallableSymbol$default, new Function0() { // from class: org.jetbrains.kotlin.fir.expressions.a
                public final Object invoke() {
                    return FirConstCheckVisitor.b(resolvedInitializer, this, data);
                }
            });
            return constantArgumentKind2 == null ? ConstantArgumentKind.RESOLUTION_ERROR : constantArgumentKind2;
        }
        if (!this.calledOnCheckerStage) {
            return ConstantArgumentKind.NOT_CONST;
        }
        FirExpression resolvedInitializer2 = firPropertySymbol.getResolvedInitializer();
        if (resolvedInitializer2 instanceof FirLiteralExpression) {
            return firPropertySymbol.isVal() ? ConstantArgumentKind.NOT_CONST_VAL_IN_CONST_EXPRESSION : ConstantArgumentKind.NOT_CONST;
        }
        return resolvedInitializer2 instanceof FirGetClassCall ? ConstantArgumentKind.NOT_KCLASS_LITERAL : ConstantArgumentKind.NOT_CONST;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public ConstantArgumentKind visitQualifiedAccessExpression(FirQualifiedAccessExpression qualifiedAccessExpression, Void data) {
        ConstantArgumentKind constantArgumentKind;
        qualifiedAccessExpression.getClass();
        ConeKotlinType expandedType = getExpandedType(qualifiedAccessExpression);
        if (!FunctionalTypeUtilsKt.isReflectFunctionType(expandedType, this.session) && !InferenceUtilsKt.isKProperty(expandedType, this.session) && !InferenceUtilsKt.isKMutableProperty(expandedType, this.session)) {
            return ConstantArgumentKind.VALID_CONST;
        }
        FirExpression dispatchReceiver = qualifiedAccessExpression.getDispatchReceiver();
        return (dispatchReceiver == null || (constantArgumentKind = (ConstantArgumentKind) dispatchReceiver.accept(this, data)) == null) ? ConstantArgumentKind.VALID_CONST : constantArgumentKind;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public ConstantArgumentKind visitSpreadArgumentExpression(FirSpreadArgumentExpression spreadArgumentExpression, Void data) {
        spreadArgumentExpression.getClass();
        return (ConstantArgumentKind) spreadArgumentExpression.getExpression().accept(this, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public ConstantArgumentKind visitStringConcatenationCall(FirStringConcatenationCall stringConcatenationCall, Void data) {
        stringConcatenationCall.getClass();
        for (FirExpression firExpression : stringConcatenationCall.getArgumentList().getArguments()) {
            if (!(firExpression instanceof FirLiteralExpression) || ((FirLiteralExpression) firExpression).getValue() != null) {
                if (!hasAllowedCompileTimeType(firExpression)) {
                    return ConstantArgumentKind.NOT_CONST;
                }
                ConstantArgumentKind constantArgumentKind = (ConstantArgumentKind) firExpression.accept(this, data);
                if (constantArgumentKind != ConstantArgumentKind.VALID_CONST) {
                    return constantArgumentKind;
                }
            }
        }
        return ConstantArgumentKind.VALID_CONST;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public ConstantArgumentKind visitThisReceiverExpression(FirThisReceiverExpression thisReceiverExpression, Void data) {
        thisReceiverExpression.getClass();
        FirThisOwnerSymbol<?> boundSymbol = thisReceiverExpression.getCalleeReference().getBoundSymbol();
        FirClassSymbol firClassSymbol = boundSymbol instanceof FirClassSymbol ? (FirClassSymbol) boundSymbol : null;
        return (firClassSymbol != null ? firClassSymbol.getClassKind() : null) == ClassKind.OBJECT ? ConstantArgumentKind.VALID_CONST : ConstantArgumentKind.NOT_CONST;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public ConstantArgumentKind visitTypeOperatorCall(FirTypeOperatorCall typeOperatorCall, Void data) {
        typeOperatorCall.getClass();
        if (typeOperatorCall.getOperation() == FirOperation.AS && TypeUtilsKt.isSubtypeOf$default(FirTypeUtilsKt.getResolvedType((FirExpression) CollectionsKt.first(typeOperatorCall.getArgumentList().getArguments())), FirTypeUtilsKt.getResolvedType(typeOperatorCall), this.session, false, 4, null)) {
            return (ConstantArgumentKind) ((FirExpression) CollectionsKt.first(typeOperatorCall.getArgumentList().getArguments())).accept(this, data);
        }
        return ConstantArgumentKind.NOT_CONST;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public ConstantArgumentKind visitVarargArgumentsExpression(FirVarargArgumentsExpression varargArgumentsExpression, Void data) {
        varargArgumentsExpression.getClass();
        Iterator<FirExpression> it = varargArgumentsExpression.getArguments().iterator();
        while (it.hasNext()) {
            ConstantArgumentKind constantArgumentKind = (ConstantArgumentKind) it.next().accept(this, data);
            if (constantArgumentKind != ConstantArgumentKind.VALID_CONST) {
                return constantArgumentKind;
            }
        }
        return ConstantArgumentKind.VALID_CONST;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public ConstantArgumentKind visitClassReferenceExpression(FirClassReferenceExpression classReferenceExpression, Void data) {
        classReferenceExpression.getClass();
        return ConstantArgumentKind.VALID_CONST;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public ConstantArgumentKind visitElement(FirElement element, Void data) {
        element.getClass();
        return ConstantArgumentKind.NOT_CONST;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public ConstantArgumentKind visitEnumEntryDeserializedAccessExpression(FirEnumEntryDeserializedAccessExpression enumEntryDeserializedAccessExpression, Void data) {
        enumEntryDeserializedAccessExpression.getClass();
        return ConstantArgumentKind.VALID_CONST;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public ConstantArgumentKind visitErrorExpression(FirErrorExpression errorExpression, Void data) {
        errorExpression.getClass();
        return ConstantArgumentKind.RESOLUTION_ERROR;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public ConstantArgumentKind visitErrorResolvedQualifier(FirErrorResolvedQualifier errorResolvedQualifier, Void data) {
        errorResolvedQualifier.getClass();
        return ConstantArgumentKind.VALID_CONST;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public ConstantArgumentKind visitLiteralExpression(FirLiteralExpression literalExpression, Void data) {
        literalExpression.getClass();
        return ConstantArgumentKind.VALID_CONST;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public ConstantArgumentKind visitResolvedQualifier(FirResolvedQualifier resolvedQualifier, Void data) {
        resolvedQualifier.getClass();
        return ConstantArgumentKind.VALID_CONST;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public ConstantArgumentKind visitWhenExpression(FirWhenExpression whenExpression, Void data) {
        whenExpression.getClass();
        return ConstantArgumentKind.NOT_CONST;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public ConstantArgumentKind visitWhenSubjectExpression(FirWhenSubjectExpression whenSubjectExpression, Void data) {
        whenSubjectExpression.getClass();
        return ConstantArgumentKind.NOT_CONST;
    }
}
