package org.jetbrains.kotlin.fir;

import com.intellij.psi.PsiElement;
import defpackage.e2f;
import defpackage.f2f;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtPsiSourceElement;
import org.jetbrains.kotlin.KtRealPsiSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirResolvedDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.declarations.impl.FirDeclarationStatusImpl;
import org.jetbrains.kotlin.fir.declarations.impl.FirDeclarationStatusWithAlteredDefaults;
import org.jetbrains.kotlin.fir.declarations.impl.FirResolvedDeclarationStatusImpl;
import org.jetbrains.kotlin.fir.declarations.impl.FirResolvedDeclarationStatusWithAlteredDefaults;
import org.jetbrains.kotlin.fir.diagnostics.ConeSimpleDiagnostic;
import org.jetbrains.kotlin.fir.diagnostics.DiagnosticKind;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCallOrigin;
import org.jetbrains.kotlin.fir.expressions.FirOperation;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.builder.FirWhenSubjectExpressionBuilder;
import org.jetbrains.kotlin.fir.references.FirNamedReference;
import org.jetbrains.kotlin.fir.references.builder.FirErrorNamedReferenceBuilder;
import org.jetbrains.kotlin.fir.references.builder.FirResolvedNamedReferenceBuilder;
import org.jetbrains.kotlin.fir.renderer.FirRenderer;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFileSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertyAccessorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirReplSnippetSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirScriptSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirDynamicTypeRef;
import org.jetbrains.kotlin.fir.types.FirErrorTypeRef;
import org.jetbrains.kotlin.fir.types.FirFunctionTypeRef;
import org.jetbrains.kotlin.fir.types.FirIntersectionTypeRef;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirUserTypeRef;
import org.jetbrains.kotlin.fir.types.builder.FirDynamicTypeRefBuilder;
import org.jetbrains.kotlin.fir.types.builder.FirErrorTypeRefBuilder;
import org.jetbrains.kotlin.fir.types.builder.FirFunctionTypeRefBuilder;
import org.jetbrains.kotlin.fir.types.builder.FirIntersectionTypeRefBuilder;
import org.jetbrains.kotlin.fir.types.builder.FirResolvedTypeRefBuilder;
import org.jetbrains.kotlin.fir.types.builder.FirUserTypeRefBuilder;
import org.jetbrains.kotlin.fir.types.impl.FirFunctionTypeRefImpl;
import org.jetbrains.kotlin.fir.types.impl.FirImplicitBuiltinTypeRef;
import org.jetbrains.kotlin.fir.types.impl.FirImplicitBuiltinTypeRefKt;
import org.jetbrains.kotlin.fir.types.impl.FirResolvedTypeRefImpl;
import org.jetbrains.kotlin.fir.types.impl.FirUserTypeRefImpl;
import org.jetbrains.kotlin.name.CallableIdKt;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.SpecialNames;
import org.jetbrains.kotlin.resolve.ReturnValueStatus;
import org.jetbrains.kotlin.util.ArrayMapAccessor;
import org.jetbrains.kotlin.util.OperatorNameConventions;
import org.jetbrains.kotlin.util.TypeRegistry;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000è\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a!\u0010\u0005\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u0007*\u0002H\u00062\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\n\u001a!\u0010\u000b\u001a\u0002H\u0006\"\b\b\u0000\u0010\u0006*\u00020\u0007*\u0002H\u00062\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000e\u001a\n\u0010\u001d\u001a\u00020\u001e*\u00020\u0018\u001a\n\u0010\u001f\u001a\u00020\u001e*\u00020\u0018\u001aô\u0001\u0010 \u001a\u00020!*\u00020!2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010#2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010%2\b\b\u0002\u0010&\u001a\u00020'2\b\b\u0002\u0010(\u001a\u00020'2\b\b\u0002\u0010)\u001a\u00020'2\b\b\u0002\u0010*\u001a\u00020'2\b\b\u0002\u0010+\u001a\u00020'2\b\b\u0002\u0010,\u001a\u00020'2\b\b\u0002\u0010-\u001a\u00020'2\b\b\u0002\u0010.\u001a\u00020'2\b\b\u0002\u0010/\u001a\u00020'2\b\b\u0002\u00100\u001a\u00020'2\b\b\u0002\u00101\u001a\u00020'2\b\b\u0002\u00102\u001a\u00020'2\b\b\u0002\u00103\u001a\u00020'2\b\b\u0002\u00104\u001a\u00020'2\b\b\u0002\u00105\u001a\u00020'2\b\b\u0002\u00106\u001a\u00020'2\b\b\u0002\u00107\u001a\u00020'2\b\b\u0002\u00108\u001a\u00020'2\b\b\u0002\u00109\u001a\u00020'2\b\b\u0002\u0010:\u001a\u00020'2\b\b\u0002\u0010;\u001a\u00020<\u001a6\u0010=\u001a\u00020!*\u00020!2\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010#2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010%2\b\b\u0002\u0010>\u001a\u00020#2\b\b\u0002\u0010?\u001a\u00020%\u001aê\u0001\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020!2\u0006\u0010C\u001a\u00020D2\b\b\u0002\u0010&\u001a\u00020'2\b\b\u0002\u0010(\u001a\u00020'2\b\b\u0002\u0010)\u001a\u00020'2\b\b\u0002\u0010*\u001a\u00020'2\b\b\u0002\u0010+\u001a\u00020'2\b\b\u0002\u0010,\u001a\u00020'2\b\b\u0002\u0010-\u001a\u00020'2\b\b\u0002\u0010.\u001a\u00020'2\b\b\u0002\u0010/\u001a\u00020'2\b\b\u0002\u00100\u001a\u00020'2\b\b\u0002\u00101\u001a\u00020'2\b\b\u0002\u00102\u001a\u00020'2\b\b\u0002\u00103\u001a\u00020'2\b\b\u0002\u00104\u001a\u00020'2\b\b\u0002\u00105\u001a\u00020'2\b\b\u0002\u00106\u001a\u00020'2\b\b\u0002\u00107\u001a\u00020'2\b\b\u0002\u00108\u001a\u00020'2\b\b\u0002\u00109\u001a\u00020'2\b\b\u0002\u0010:\u001a\u00020'2\b\b\u0002\u0010;\u001a\u00020<H\u0002\u001a5\u0010E\u001a\u0002H\u0006\"\u0004\b\u0000\u0010\u00062\u0006\u0010F\u001a\u00020G2\u0006\u0010H\u001a\u00020\u00182\f\u0010I\u001a\b\u0012\u0004\u0012\u0002H\u00060JH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010K\u001a-\u0010L\u001a\u0002H\u0006\"\u0004\b\u0000\u0010\u00062\u0006\u0010M\u001a\u00020\u00112\f\u0010I\u001a\b\u0012\u0004\u0012\u0002H\u00060JH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010N\u001a*\u0010U\u001a\b\u0012\u0004\u0012\u0002HW0V\"\u0004\b\u0000\u0010W*\b\u0012\u0004\u0012\u0002HW0V2\f\u0010X\u001a\b\u0012\u0004\u0012\u0002HW0V\u001a\u0012\u0010Y\u001a\u00020'*\u00020Z2\u0006\u0010[\u001a\u00020\\\u001a\u0016\u0010Y\u001a\u00020'*\u00020Z2\n\u0010]\u001a\u0006\u0012\u0002\b\u00030^\u001a\u0012\u0010_\u001a\u00020'*\u00020`2\u0006\u0010[\u001a\u00020\\\u001a\u0014\u0010a\u001a\u00020'*\u00020`2\u0006\u0010[\u001a\u00020\\H\u0002\u001a\u001e\u0010f\u001a\u00020'2\b\u0010H\u001a\u0004\u0018\u00010\u00182\f\u0010g\u001a\b\u0012\u0002\b\u0003\u0018\u00010h\u001a\u000e\u0010\u000f\u001a\u00020\u0010*\u0006\u0012\u0002\b\u00030h\u001a\n\u0010i\u001a\u00020j*\u00020k\u001a\u0018\u0010l\u001a\u00020m2\u0006\u0010n\u001a\u00020\r2\b\u0010o\u001a\u0004\u0018\u00010Z\u001a\"\u0010p\u001a\u00020q*\u00020r2\n\b\u0002\u0010s\u001a\u0004\u0018\u00010\r2\n\b\u0002\u0010t\u001a\u0004\u0018\u00010\u0007\"\u0017\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u000f\u001a\u00020\u0010*\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013\"\u0015\u0010\u000f\u001a\u00020\u0010*\u00020\u00148F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0015\"\u0017\u0010\u0016\u001a\u0004\u0018\u00010\u0017*\u00020\u00188F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001a\"\u0017\u0010\u001b\u001a\u0004\u0018\u00010\u0017*\u00020\u00188F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001a\"\u001f\u0010O\u001a\u00020P*\u00020G8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\bS\u0010T\u001a\u0004\bQ\u0010R\"\u0015\u0010b\u001a\u00020'*\u00020\u00018F¢\u0006\u0006\u001a\u0004\bb\u0010c\"\u0015\u0010d\u001a\u00020'*\u00020\u00018F¢\u0006\u0006\u001a\u0004\bd\u0010c\"\u0018\u0010e\u001a\u00020'*\u00020\u00018BX\u0082\u0004¢\u0006\u0006\u001a\u0004\be\u0010c\"\u001b\u0010u\u001a\b\u0012\u0004\u0012\u00020v0V*\u00020w8F¢\u0006\u0006\u001a\u0004\bx\u0010y\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006z"}, d2 = {"lastExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "getLastExpression", "(Lorg/jetbrains/kotlin/fir/expressions/FirBlock;)Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "copyWithNewSourceKind", "R", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "newKind", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", "(Lorg/jetbrains/kotlin/fir/types/FirTypeRef;Lorg/jetbrains/kotlin/KtFakeSourceElementKind;)Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "copyWithNewSource", "newSource", "Lorg/jetbrains/kotlin/KtSourceElement;", "(Lorg/jetbrains/kotlin/fir/types/FirTypeRef;Lorg/jetbrains/kotlin/KtSourceElement;)Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "packageFqName", "Lorg/jetbrains/kotlin/name/FqName;", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "getPackageFqName", "(Lorg/jetbrains/kotlin/fir/declarations/FirFile;)Lorg/jetbrains/kotlin/name/FqName;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFileSymbol;", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirFileSymbol;)Lorg/jetbrains/kotlin/name/FqName;", "psi", "Lcom/intellij/psi/PsiElement;", "Lorg/jetbrains/kotlin/fir/FirElement;", "getPsi", "(Lorg/jetbrains/kotlin/fir/FirElement;)Lcom/intellij/psi/PsiElement;", "realPsi", "getRealPsi", "renderWithType", Argument.Delimiters.none, "render", "copy", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;", "visibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "modality", "Lorg/jetbrains/kotlin/descriptors/Modality;", "isExpect", Argument.Delimiters.none, "isActual", "isOverride", "isOperator", "isInfix", "isInline", "isValue", "isTailRec", "isExternal", "isConst", "isLateInit", "isInner", "isCompanion", "isData", "isSuspend", "isStatic", "isFromSealedClass", "isFromEnumClass", "isFun", "hasStableParameterNames", "returnValueStatus", "Lorg/jetbrains/kotlin/resolve/ReturnValueStatus;", "copyWithNewDefaults", "defaultVisibility", "defaultModality", "copyStatusAttributes", Argument.Delimiters.none, "from", "to", "Lorg/jetbrains/kotlin/fir/declarations/impl/FirDeclarationStatusImpl;", "whileAnalysing", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "element", "block", "Lkotlin/Function0;", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/FirElement;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withFileAnalysisExceptionWrapping", "file", "(Lorg/jetbrains/kotlin/fir/declarations/FirFile;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "exceptionHandler", "Lorg/jetbrains/kotlin/fir/FirExceptionHandler;", "getExceptionHandler", "(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/FirExceptionHandler;", "exceptionHandler$delegate", "Lorg/jetbrains/kotlin/util/ArrayMapAccessor;", "smartPlus", Argument.Delimiters.none, "T", "other", "isEnumEntries", "Lorg/jetbrains/kotlin/fir/declarations/FirVariable;", "containingClass", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "containingClassSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "isGeneratedStaticEnumMember", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "isSimpleStaticMemberOfEnumClass", "isArraySet", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)Z", "isStatementLikeExpression", "isIndexedAssignment", "shouldSuppressInlineContextAt", "container", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "toAugmentedAssignSourceKind", "Lorg/jetbrains/kotlin/KtFakeSourceElementKind$DesugaredAugmentedAssign;", "Lorg/jetbrains/kotlin/fir/expressions/FirOperation;", "buildWhenSubjectAccess", "Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;", "conditionSource", "subjectVariable", "toFirResolvedTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "source", "delegatedTypeRef", "ownTypeArguments", "Lorg/jetbrains/kotlin/fir/types/FirTypeProjection;", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "getOwnTypeArguments", "(Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;)Ljava/util/List;", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class UtilsKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(UtilsKt.class, "exceptionHandler", "getExceptionHandler(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/FirExceptionHandler;", 1)};
    private static final ArrayMapAccessor exceptionHandler$delegate = TypeRegistry.generateAccessor$default(FirSession.INSTANCE, Reflection.getOrCreateKotlinClass(FirExceptionHandler.class), (Object) null, 2, (Object) null);

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[FirOperation.values().length];
            try {
                iArr[FirOperation.PLUS_ASSIGN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[FirOperation.MINUS_ASSIGN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[FirOperation.TIMES_ASSIGN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[FirOperation.DIV_ASSIGN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[FirOperation.REM_ASSIGN.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final FirPropertyAccessExpression buildWhenSubjectAccess(KtSourceElement ktSourceElement, FirVariable firVariable) {
        FirNamedReference firNamedReferenceBuild;
        ktSourceElement.getClass();
        FirWhenSubjectExpressionBuilder firWhenSubjectExpressionBuilder = new FirWhenSubjectExpressionBuilder();
        firWhenSubjectExpressionBuilder.setSource(ktSourceElement);
        if (firVariable == null) {
            FirErrorNamedReferenceBuilder firErrorNamedReferenceBuilder = new FirErrorNamedReferenceBuilder();
            firErrorNamedReferenceBuilder.setSource(KtSourceElementKt.fakeElement$default(ktSourceElement, KtFakeSourceElementKind.UnresolvedWhenConditionSubject.INSTANCE, null, 2, null));
            firErrorNamedReferenceBuilder.setName(SpecialNames.WHEN_SUBJECT);
            firErrorNamedReferenceBuilder.setDiagnostic(new ConeSimpleDiagnostic("No subject in when", DiagnosticKind.Other));
            firNamedReferenceBuild = firErrorNamedReferenceBuilder.build();
        } else {
            FirResolvedNamedReferenceBuilder firResolvedNamedReferenceBuilder = new FirResolvedNamedReferenceBuilder();
            firResolvedNamedReferenceBuilder.setSource(KtSourceElementKt.fakeElement$default(ktSourceElement, KtFakeSourceElementKind.WhenCondition.INSTANCE, null, 2, null));
            firResolvedNamedReferenceBuilder.setResolvedSymbol(firVariable.getSymbol());
            firResolvedNamedReferenceBuilder.setName(firVariable.getName());
            firNamedReferenceBuild = firResolvedNamedReferenceBuilder.build();
        }
        firWhenSubjectExpressionBuilder.setCalleeReference(firNamedReferenceBuild);
        return firWhenSubjectExpressionBuilder.mo289build();
    }

    public static final FirDeclarationStatus copy(FirDeclarationStatus firDeclarationStatus, Visibility visibility, Modality modality, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, boolean z10, boolean z11, boolean z12, boolean z13, boolean z14, boolean z15, boolean z16, boolean z17, boolean z18, boolean z19, boolean z20, ReturnValueStatus returnValueStatus) {
        FirDeclarationStatusImpl firDeclarationStatusImpl;
        firDeclarationStatus.getClass();
        returnValueStatus.getClass();
        Visibility visibility2 = visibility == null ? firDeclarationStatus.getVisibility() : visibility;
        Modality modality2 = modality == null ? firDeclarationStatus.getModality() : modality;
        if (firDeclarationStatus instanceof FirResolvedDeclarationStatus) {
            modality2.getClass();
            firDeclarationStatusImpl = new FirResolvedDeclarationStatusImpl(visibility2, modality2, ((FirResolvedDeclarationStatus) firDeclarationStatus).getEffectiveVisibility());
        } else {
            firDeclarationStatusImpl = new FirDeclarationStatusImpl(visibility2, modality2);
        }
        FirDeclarationStatusImpl firDeclarationStatusImpl2 = firDeclarationStatusImpl;
        copyStatusAttributes(firDeclarationStatus, firDeclarationStatusImpl2, z, z2, z3, z4, z5, z6, z7, z8, z9, z10, z11, z12, z13, z14, z15, z16, z17, z18, z19, z20, returnValueStatus);
        return firDeclarationStatusImpl2;
    }

    private static final void copyStatusAttributes(FirDeclarationStatus firDeclarationStatus, FirDeclarationStatusImpl firDeclarationStatusImpl, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, boolean z10, boolean z11, boolean z12, boolean z13, boolean z14, boolean z15, boolean z16, boolean z17, boolean z18, boolean z19, boolean z20, ReturnValueStatus returnValueStatus) {
        firDeclarationStatusImpl.setExpect(z);
        firDeclarationStatusImpl.setActual(z2);
        firDeclarationStatusImpl.setOverride(z3);
        firDeclarationStatusImpl.setOperator(z4);
        firDeclarationStatusImpl.setInfix(z5);
        firDeclarationStatusImpl.setInline(z6);
        firDeclarationStatusImpl.setValue(z7);
        firDeclarationStatusImpl.setTailRec(z8);
        firDeclarationStatusImpl.setExternal(z9);
        firDeclarationStatusImpl.setConst(z10);
        firDeclarationStatusImpl.setLateInit(z11);
        firDeclarationStatusImpl.setInner(z12);
        firDeclarationStatusImpl.setCompanion(z13);
        firDeclarationStatusImpl.setData(z14);
        firDeclarationStatusImpl.setSuspend(z15);
        firDeclarationStatusImpl.setStatic(z16);
        firDeclarationStatusImpl.setFromSealedClass(z17);
        firDeclarationStatusImpl.setFromEnumClass(z18);
        firDeclarationStatusImpl.setFun(z19);
        firDeclarationStatusImpl.setHasStableParameterNames(z20);
        firDeclarationStatusImpl.setReturnValueStatus(returnValueStatus);
    }

    public static /* synthetic */ void copyStatusAttributes$default(FirDeclarationStatus firDeclarationStatus, FirDeclarationStatusImpl firDeclarationStatusImpl, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, boolean z9, boolean z10, boolean z11, boolean z12, boolean z13, boolean z14, boolean z15, boolean z16, boolean z17, boolean z18, boolean z19, boolean z20, ReturnValueStatus returnValueStatus, int i, Object obj) {
        copyStatusAttributes(firDeclarationStatus, firDeclarationStatusImpl, (i & 4) != 0 ? firDeclarationStatus.isExpect() : z, (i & 8) != 0 ? firDeclarationStatus.isActual() : z2, (i & 16) != 0 ? firDeclarationStatus.isOverride() : z3, (i & 32) != 0 ? firDeclarationStatus.isOperator() : z4, (i & 64) != 0 ? firDeclarationStatus.isInfix() : z5, (i & 128) != 0 ? firDeclarationStatus.isInline() : z6, (i & 256) != 0 ? firDeclarationStatus.isValue() : z7, (i & 512) != 0 ? firDeclarationStatus.isTailRec() : z8, (i & 1024) != 0 ? firDeclarationStatus.isExternal() : z9, (i & 2048) != 0 ? firDeclarationStatus.isConst() : z10, (i & 4096) != 0 ? firDeclarationStatus.isLateInit() : z11, (i & 8192) != 0 ? firDeclarationStatus.isInner() : z12, (i & 16384) != 0 ? firDeclarationStatus.isCompanion() : z13, (32768 & i) != 0 ? firDeclarationStatus.isData() : z14, (65536 & i) != 0 ? firDeclarationStatus.isSuspend() : z15, (i & 131072) != 0 ? firDeclarationStatus.isStatic() : z16, (i & 262144) != 0 ? firDeclarationStatus.isFromSealedClass() : z17, (i & 524288) != 0 ? firDeclarationStatus.isFromEnumClass() : z18, (i & 1048576) != 0 ? firDeclarationStatus.isFun() : z19, (i & 2097152) != 0 ? firDeclarationStatus.getHasStableParameterNames() : z20, (i & 4194304) != 0 ? firDeclarationStatus.getReturnValueStatus() : returnValueStatus);
    }

    public static final FirDeclarationStatus copyWithNewDefaults(FirDeclarationStatus firDeclarationStatus, Visibility visibility, Modality modality, Visibility visibility2, Modality modality2) {
        FirDeclarationStatusImpl firDeclarationStatusWithAlteredDefaults;
        firDeclarationStatus.getClass();
        visibility2.getClass();
        modality2.getClass();
        Visibility visibility3 = visibility == null ? firDeclarationStatus.getVisibility() : visibility;
        Modality modality3 = modality == null ? firDeclarationStatus.getModality() : modality;
        if (firDeclarationStatus instanceof FirResolvedDeclarationStatus) {
            modality3.getClass();
            firDeclarationStatusWithAlteredDefaults = new FirResolvedDeclarationStatusWithAlteredDefaults(visibility3, modality3, visibility2, modality2, ((FirResolvedDeclarationStatus) firDeclarationStatus).getEffectiveVisibility());
        } else {
            firDeclarationStatusWithAlteredDefaults = new FirDeclarationStatusWithAlteredDefaults(visibility3, modality3, visibility2, modality2);
        }
        FirDeclarationStatusImpl firDeclarationStatusImpl = firDeclarationStatusWithAlteredDefaults;
        copyStatusAttributes$default(firDeclarationStatus, firDeclarationStatusImpl, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, null, 8388604, null);
        return firDeclarationStatusImpl;
    }

    public static /* synthetic */ FirDeclarationStatus copyWithNewDefaults$default(FirDeclarationStatus firDeclarationStatus, Visibility visibility, Modality modality, Visibility visibility2, Modality modality2, int i, Object obj) {
        if ((i & 1) != 0) {
            visibility = firDeclarationStatus.getVisibility();
        }
        if ((i & 2) != 0) {
            modality = firDeclarationStatus.getModality();
        }
        if ((i & 4) != 0) {
            visibility2 = firDeclarationStatus.getDefaultVisibility();
        }
        if ((i & 8) != 0) {
            modality2 = firDeclarationStatus.getDefaultModality();
        }
        return copyWithNewDefaults(firDeclarationStatus, visibility, modality, visibility2, modality2);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
    /* JADX WARN: Multi-variable type inference failed */
    public static final <R extends FirTypeRef> R copyWithNewSource(R r, KtSourceElement ktSourceElement) throws NotImplementedError {
        FirUserTypeRef firUserTypeRefBuild;
        r.getClass();
        ktSourceElement.getClass();
        KtSourceElement source = r.getSource();
        if (Intrinsics.areEqual(source != null ? source.getKind() : null, ktSourceElement.getKind())) {
            return r;
        }
        if (r instanceof FirResolvedTypeRefImpl) {
            FirResolvedTypeRef firResolvedTypeRef = (FirResolvedTypeRef) r;
            FirResolvedTypeRefBuilder firResolvedTypeRefBuilder = new FirResolvedTypeRefBuilder();
            firResolvedTypeRefBuilder.setSource(firResolvedTypeRef.getSource());
            firResolvedTypeRefBuilder.getAnnotations().addAll(firResolvedTypeRef.getAnnotations());
            firResolvedTypeRefBuilder.setConeType(firResolvedTypeRef.getConeType());
            firResolvedTypeRefBuilder.setDelegatedTypeRef(firResolvedTypeRef.getDelegatedTypeRef());
            firResolvedTypeRefBuilder.setResolvedSymbolOrigin(firResolvedTypeRef.getResolvedSymbolOrigin());
            firResolvedTypeRefBuilder.setSource(ktSourceElement);
            firUserTypeRefBuild = firResolvedTypeRefBuilder.build();
        } else if (r instanceof FirErrorTypeRef) {
            FirErrorTypeRef firErrorTypeRef = (FirErrorTypeRef) r;
            FirErrorTypeRefBuilder firErrorTypeRefBuilder = new FirErrorTypeRefBuilder();
            firErrorTypeRefBuilder.setSource(firErrorTypeRef.getSource());
            firErrorTypeRefBuilder.setConeType(firErrorTypeRef.getConeType());
            firErrorTypeRefBuilder.setAnnotations(CollectionsKt.toMutableList(firErrorTypeRef.getAnnotations()));
            firErrorTypeRefBuilder.setDelegatedTypeRef(firErrorTypeRef.getDelegatedTypeRef());
            firErrorTypeRefBuilder.setDiagnostic(firErrorTypeRef.getDiagnostic());
            firErrorTypeRefBuilder.setPartiallyResolvedTypeRef(firErrorTypeRef.getPartiallyResolvedTypeRef());
            firErrorTypeRefBuilder.setSource(ktSourceElement);
            FirTypeRef partiallyResolvedTypeRef = firErrorTypeRef.getPartiallyResolvedTypeRef();
            firErrorTypeRefBuilder.setPartiallyResolvedTypeRef(partiallyResolvedTypeRef != null ? copyWithNewSource(partiallyResolvedTypeRef, ktSourceElement) : null);
            firUserTypeRefBuild = firErrorTypeRefBuilder.build();
        } else if (r instanceof FirUserTypeRefImpl) {
            FirUserTypeRefBuilder firUserTypeRefBuilder = new FirUserTypeRefBuilder();
            firUserTypeRefBuilder.setSource(ktSourceElement);
            FirUserTypeRefImpl firUserTypeRefImpl = (FirUserTypeRefImpl) r;
            firUserTypeRefBuilder.setMarkedNullable(firUserTypeRefImpl.isMarkedNullable());
            CollectionsKt.addAll(firUserTypeRefBuilder.getQualifier(), firUserTypeRefImpl.getQualifier());
            CollectionsKt.addAll(firUserTypeRefBuilder.getAnnotations(), MutableOrEmptyList.m194boximpl(firUserTypeRefImpl.getAnnotations-5e3fPpI()));
            firUserTypeRefBuild = firUserTypeRefBuilder.build();
        } else if (r instanceof FirFunctionTypeRefImpl) {
            FirFunctionTypeRef firFunctionTypeRef = (FirFunctionTypeRef) r;
            FirFunctionTypeRefBuilder firFunctionTypeRefBuilder = new FirFunctionTypeRefBuilder();
            firFunctionTypeRefBuilder.getAnnotations().addAll(firFunctionTypeRef.getAnnotations());
            firFunctionTypeRefBuilder.setSource(firFunctionTypeRef.getSource());
            firFunctionTypeRefBuilder.setMarkedNullable(firFunctionTypeRef.isMarkedNullable());
            firFunctionTypeRefBuilder.setReceiverTypeRef(firFunctionTypeRef.getReceiverTypeRef());
            firFunctionTypeRefBuilder.getParameters().addAll(firFunctionTypeRef.getParameters());
            firFunctionTypeRefBuilder.setReturnTypeRef(firFunctionTypeRef.getReturnTypeRef());
            firFunctionTypeRefBuilder.setSuspend(firFunctionTypeRef.isSuspend());
            firFunctionTypeRefBuilder.getContextParameterTypeRefs().addAll(firFunctionTypeRef.getContextParameterTypeRefs());
            firFunctionTypeRefBuilder.setSource(ktSourceElement);
            firUserTypeRefBuild = firFunctionTypeRefBuilder.build();
        } else if (r instanceof FirDynamicTypeRef) {
            FirDynamicTypeRefBuilder firDynamicTypeRefBuilder = new FirDynamicTypeRefBuilder();
            firDynamicTypeRefBuilder.setSource(ktSourceElement);
            FirDynamicTypeRef firDynamicTypeRef = (FirDynamicTypeRef) r;
            firDynamicTypeRefBuilder.setMarkedNullable(firDynamicTypeRef.isMarkedNullable());
            CollectionsKt.addAll(firDynamicTypeRefBuilder.getAnnotations(), firDynamicTypeRef.getAnnotations());
            firUserTypeRefBuild = firDynamicTypeRefBuilder.build();
        } else if (r instanceof FirImplicitBuiltinTypeRef) {
            firUserTypeRefBuild = FirImplicitBuiltinTypeRefKt.withNewSource((FirImplicitBuiltinTypeRef) r, ktSourceElement);
        } else {
            if (!(r instanceof FirIntersectionTypeRef)) {
                e2f.a("An operation is not implemented: ".concat("Not implemented for " + Reflection.getOrCreateKotlinClass(r.getClass())));
                return null;
            }
            FirIntersectionTypeRefBuilder firIntersectionTypeRefBuilder = new FirIntersectionTypeRefBuilder();
            firIntersectionTypeRefBuilder.setSource(ktSourceElement);
            FirIntersectionTypeRef firIntersectionTypeRef = (FirIntersectionTypeRef) r;
            firIntersectionTypeRefBuilder.setMarkedNullable(firIntersectionTypeRef.isMarkedNullable());
            firIntersectionTypeRefBuilder.setLeftType(firIntersectionTypeRef.getLeftType());
            firIntersectionTypeRefBuilder.setRightType(firIntersectionTypeRef.getRightType());
            firUserTypeRefBuild = firIntersectionTypeRefBuilder.build();
        }
        firUserTypeRefBuild.getClass();
        return firUserTypeRefBuild;
    }

    public static final <R extends FirTypeRef> R copyWithNewSourceKind(R r, KtFakeSourceElementKind ktFakeSourceElementKind) {
        r.getClass();
        ktFakeSourceElementKind.getClass();
        KtSourceElement source = r.getSource();
        return (source == null || Intrinsics.areEqual(source.getKind(), ktFakeSourceElementKind)) ? r : (R) copyWithNewSource(r, KtSourceElementKt.fakeElement$default(source, ktFakeSourceElementKind, null, 2, null));
    }

    public static final FirExceptionHandler getExceptionHandler(FirSession firSession) {
        firSession.getClass();
        return (FirExceptionHandler) exceptionHandler$delegate.getValue(firSession, $$delegatedProperties[0]);
    }

    public static final FirExpression getLastExpression(FirBlock firBlock) {
        firBlock.getClass();
        Object objLastOrNull = CollectionsKt.lastOrNull(firBlock.getStatements());
        if (objLastOrNull instanceof FirExpression) {
            return (FirExpression) objLastOrNull;
        }
        return null;
    }

    public static final List<FirTypeProjection> getOwnTypeArguments(FirResolvedQualifier firResolvedQualifier) {
        List<FirTypeProjection> typeArguments;
        firResolvedQualifier.getClass();
        List<FirTypeProjection> typeArguments2 = firResolvedQualifier.getTypeArguments();
        int size = firResolvedQualifier.getTypeArguments().size();
        FirResolvedQualifier explicitParent = firResolvedQualifier.getExplicitParent();
        return typeArguments2.subList(0, size - ((explicitParent == null || (typeArguments = explicitParent.getTypeArguments()) == null) ? 0 : typeArguments.size()));
    }

    public static final FqName getPackageFqName(FirFileSymbol firFileSymbol) {
        firFileSymbol.getClass();
        return getPackageFqName(firFileSymbol.getFir());
    }

    public static final PsiElement getPsi(FirElement firElement) {
        firElement.getClass();
        KtPsiSourceElement source = firElement.getSource();
        KtPsiSourceElement ktPsiSourceElement = source instanceof KtPsiSourceElement ? source : null;
        if (ktPsiSourceElement != null) {
            return ktPsiSourceElement.getPsi();
        }
        return null;
    }

    public static final PsiElement getRealPsi(FirElement firElement) {
        firElement.getClass();
        KtRealPsiSourceElement source = firElement.getSource();
        KtRealPsiSourceElement ktRealPsiSourceElement = source instanceof KtRealPsiSourceElement ? source : null;
        if (ktRealPsiSourceElement != null) {
            return ktRealPsiSourceElement.getPsi();
        }
        return null;
    }

    public static final boolean isArraySet(FirExpression firExpression) {
        FirNamedReference calleeReference;
        Name name;
        firExpression.getClass();
        FirFunctionCall firFunctionCall = firExpression instanceof FirFunctionCall ? (FirFunctionCall) firExpression : null;
        return (firFunctionCall == null || (calleeReference = firFunctionCall.getCalleeReference()) == null || (name = calleeReference.getName()) == null || ((FirFunctionCall) firExpression).getOrigin() != FirFunctionCallOrigin.Operator || !Intrinsics.areEqual(name, OperatorNameConventions.SET)) ? false : true;
    }

    public static final boolean isEnumEntries(FirVariable firVariable, FirClass firClass) {
        firVariable.getClass();
        firClass.getClass();
        return Intrinsics.areEqual(firVariable.getName(), StandardNames.ENUM_ENTRIES) && isSimpleStaticMemberOfEnumClass(firVariable, firClass);
    }

    public static final boolean isGeneratedStaticEnumMember(FirCallableDeclaration firCallableDeclaration, FirClass firClass) {
        FirValueParameter firValueParameter;
        FirTypeRef returnTypeRef;
        ConeKotlinType coneType;
        firCallableDeclaration.getClass();
        firClass.getClass();
        if (firCallableDeclaration instanceof FirVariable) {
            return isEnumEntries((FirVariable) firCallableDeclaration, firClass);
        }
        if (firCallableDeclaration instanceof FirNamedFunction) {
            FirNamedFunction firNamedFunction = (FirNamedFunction) firCallableDeclaration;
            Name name = firNamedFunction.getName();
            if (Intrinsics.areEqual(name, StandardNames.ENUM_VALUES)) {
                return isSimpleStaticMemberOfEnumClass(firCallableDeclaration, firClass) && firNamedFunction.getValueParameters().isEmpty();
            }
            if (Intrinsics.areEqual(name, StandardNames.ENUM_VALUE_OF) && isSimpleStaticMemberOfEnumClass(firCallableDeclaration, firClass) && (firValueParameter = (FirValueParameter) CollectionsKt.singleOrNull(firNamedFunction.getValueParameters())) != null && (returnTypeRef = firValueParameter.getReturnTypeRef()) != null && (coneType = FirTypeUtilsKt.getConeType(returnTypeRef)) != null && ConeBuiltinTypeUtilsKt.isString(coneType)) {
                return true;
            }
        }
        return false;
    }

    private static final boolean isIndexedAssignment(FirExpression firExpression) {
        KtSourceElement source;
        if (!(firExpression instanceof FirBlock)) {
            return false;
        }
        FirStatement firStatement = (FirStatement) CollectionsKt.lastOrNull(((FirBlock) firExpression).getStatements());
        return Intrinsics.areEqual((firStatement == null || (source = firStatement.getSource()) == null) ? null : source.getKind(), KtFakeSourceElementKind.ImplicitUnit.IndexedAssignmentCoercion.INSTANCE);
    }

    private static final boolean isSimpleStaticMemberOfEnumClass(FirCallableDeclaration firCallableDeclaration, FirClass firClass) {
        return firCallableDeclaration.getStatus().isStatic() && firClass.getClassKind() == ClassKind.ENUM_CLASS && firCallableDeclaration.getContextParameters().isEmpty() && firCallableDeclaration.getReceiverParameter() == null;
    }

    public static final boolean isStatementLikeExpression(FirExpression firExpression) {
        firExpression.getClass();
        if (!(firExpression instanceof FirFunctionCall)) {
            return isIndexedAssignment(firExpression);
        }
        FirFunctionCall firFunctionCall = (FirFunctionCall) firExpression;
        return firFunctionCall.getOrigin() == FirFunctionCallOrigin.Operator && OperatorNameConventions.STATEMENT_LIKE_OPERATORS.contains(firFunctionCall.getCalleeReference().getName());
    }

    public static final FqName packageFqName(FirBasedSymbol<?> firBasedSymbol) {
        firBasedSymbol.getClass();
        if (firBasedSymbol instanceof FirClassLikeSymbol) {
            return ((FirClassLikeSymbol) firBasedSymbol).getClassId().getPackageFqName();
        }
        if (firBasedSymbol instanceof FirPropertyAccessorSymbol) {
            return packageFqName(((FirPropertyAccessorSymbol) firBasedSymbol).getPropertySymbol());
        }
        if (firBasedSymbol instanceof FirCallableSymbol) {
            return CallableIdKt.getPackageName(((FirCallableSymbol) firBasedSymbol).getCallableId());
        }
        if (firBasedSymbol instanceof FirScriptSymbol) {
            return ((FirScriptSymbol) firBasedSymbol).getFqName().parent();
        }
        if (firBasedSymbol instanceof FirReplSnippetSymbol) {
            return FqName.ROOT;
        }
        w04.a("No package fq name for ", firBasedSymbol);
        return null;
    }

    public static final String render(FirElement firElement) {
        firElement.getClass();
        return FirRenderer.renderElementAsString$default(new FirRenderer(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, false, false, false, 16777215, null), firElement, false, 2, null);
    }

    public static final String renderWithType(FirElement firElement) {
        firElement.getClass();
        return new FirRenderer(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, false, false, false, 16777215, null).renderElementWithTypeAsString(firElement);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final boolean shouldSuppressInlineContextAt(FirElement firElement, FirBasedSymbol<?> firBasedSymbol) {
        if (firElement == null || !(firBasedSymbol instanceof FirValueParameterSymbol)) {
            return false;
        }
        FirValueParameterSymbol firValueParameterSymbol = (FirValueParameterSymbol) firBasedSymbol;
        return firValueParameterSymbol.isNoinline() && Intrinsics.areEqual(((FirValueParameter) firValueParameterSymbol.getFir()).getDefaultValue(), firElement);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> List<T> smartPlus(List<? extends T> list, List<? extends T> list2) {
        list.getClass();
        list2.getClass();
        if (list2.isEmpty()) {
            return list;
        }
        if (list.isEmpty()) {
            return list2;
        }
        ArrayList arrayList = new ArrayList(list.size() + list2.size());
        arrayList.addAll(list);
        arrayList.addAll(list2);
        return arrayList;
    }

    public static final KtFakeSourceElementKind.DesugaredAugmentedAssign toAugmentedAssignSourceKind(FirOperation firOperation) {
        firOperation.getClass();
        int i = WhenMappings.$EnumSwitchMapping$0[firOperation.ordinal()];
        if (i == 1) {
            return KtFakeSourceElementKind.DesugaredPlusAssign.INSTANCE;
        }
        if (i == 2) {
            return KtFakeSourceElementKind.DesugaredMinusAssign.INSTANCE;
        }
        if (i == 3) {
            return KtFakeSourceElementKind.DesugaredTimesAssign.INSTANCE;
        }
        if (i == 4) {
            return KtFakeSourceElementKind.DesugaredDivAssign.INSTANCE;
        }
        if (i == 5) {
            return KtFakeSourceElementKind.DesugaredRemAssign.INSTANCE;
        }
        f2f.a("Unexpected operator: ", firOperation.name());
        return null;
    }

    public static final FirResolvedTypeRef toFirResolvedTypeRef(ConeKotlinType coneKotlinType, KtSourceElement ktSourceElement, FirTypeRef firTypeRef) {
        coneKotlinType.getClass();
        ConeRigidType coneRigidTypeLowerBoundIfFlexible = ConeTypeUtilsKt.lowerBoundIfFlexible(coneKotlinType);
        if (!(coneRigidTypeLowerBoundIfFlexible instanceof ConeErrorType)) {
            FirResolvedTypeRefBuilder firResolvedTypeRefBuilder = new FirResolvedTypeRefBuilder();
            firResolvedTypeRefBuilder.setSource(ktSourceElement);
            firResolvedTypeRefBuilder.setConeType(coneKotlinType);
            firResolvedTypeRefBuilder.setDelegatedTypeRef(firTypeRef);
            return firResolvedTypeRefBuilder.build();
        }
        FirErrorTypeRefBuilder firErrorTypeRefBuilder = new FirErrorTypeRefBuilder();
        firErrorTypeRefBuilder.setSource(ktSourceElement);
        firErrorTypeRefBuilder.setDiagnostic(((ConeErrorType) coneRigidTypeLowerBoundIfFlexible).getDiagnostic());
        firErrorTypeRefBuilder.setConeType(coneKotlinType);
        firErrorTypeRefBuilder.setDelegatedTypeRef(firTypeRef);
        return firErrorTypeRefBuilder.build();
    }

    public static /* synthetic */ FirResolvedTypeRef toFirResolvedTypeRef$default(ConeKotlinType coneKotlinType, KtSourceElement ktSourceElement, FirTypeRef firTypeRef, int i, Object obj) {
        if ((i & 1) != 0) {
            ktSourceElement = null;
        }
        if ((i & 2) != 0) {
            firTypeRef = null;
        }
        return toFirResolvedTypeRef(coneKotlinType, ktSourceElement, firTypeRef);
    }

    public static final <R> R whileAnalysing(FirSession firSession, FirElement firElement, Function0<? extends R> function0) {
        firSession.getClass();
        firElement.getClass();
        function0.getClass();
        try {
            return (R) function0.invoke();
        } catch (Throwable th) {
            getExceptionHandler(firSession).handleExceptionOnElementAnalysis(firElement, th);
            wq6.a();
            return null;
        }
    }

    public static final <R> R withFileAnalysisExceptionWrapping(FirFile firFile, Function0<? extends R> function0) {
        firFile.getClass();
        function0.getClass();
        try {
            return (R) function0.invoke();
        } catch (Throwable th) {
            getExceptionHandler(firFile.getModuleData().getSession()).handleExceptionOnFileAnalysis(firFile, th);
            wq6.a();
            return null;
        }
    }

    public static final FqName getPackageFqName(FirFile firFile) {
        firFile.getClass();
        return firFile.getPackageDirective().getPackageFqName();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final boolean isEnumEntries(FirVariable firVariable, FirClassSymbol<?> firClassSymbol) {
        firVariable.getClass();
        firClassSymbol.getClass();
        return isEnumEntries(firVariable, (FirClass) firClassSymbol.getFir());
    }
}
