package org.jetbrains.kotlin.fir.backend;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.backend.common.IrSpecialAnnotationsProvider;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.backend.Fir2IrImplicitCastInserter;
import org.jetbrains.kotlin.fir.backend.generators.AdapterGenerator;
import org.jetbrains.kotlin.fir.backend.generators.AnnotationGenerator;
import org.jetbrains.kotlin.fir.backend.generators.CallAndReferenceGenerator;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrCallableDeclarationsGenerator;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrClassifiersGenerator;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrDataClassMembersGenerator;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrLazyDeclarationsGenerator;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrLazyFakeOverrideGenerator;
import org.jetbrains.kotlin.fir.backend.utils.ConversionTypeOrigin;
import org.jetbrains.kotlin.fir.backend.utils.VariousUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.types.CompilerConeAttributesKt;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeCapturedType;
import org.jetbrains.kotlin.fir.types.ConeDynamicType;
import org.jetbrains.kotlin.fir.types.ConeIntersectionType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjectionKt;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ProjectionKind;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.ir.IrLock;
import org.jetbrains.kotlin.ir.IrProvider;
import org.jetbrains.kotlin.ir.IrStatement;
import org.jetbrains.kotlin.ir.expressions.IrConstructorCall;
import org.jetbrains.kotlin.ir.expressions.IrContainerExpression;
import org.jetbrains.kotlin.ir.expressions.IrErrorCallExpression;
import org.jetbrains.kotlin.ir.expressions.IrExpression;
import org.jetbrains.kotlin.ir.expressions.IrGetEnumValue;
import org.jetbrains.kotlin.ir.expressions.IrStatementContainer;
import org.jetbrains.kotlin.ir.expressions.IrTypeOperator;
import org.jetbrains.kotlin.ir.expressions.IrTypeOperatorCall;
import org.jetbrains.kotlin.ir.expressions.impl.BuildersKt;
import org.jetbrains.kotlin.ir.symbols.IrFunctionSymbol;
import org.jetbrains.kotlin.ir.types.IrDynamicType;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.types.IrTypePredicatesKt;
import org.jetbrains.kotlin.ir.types.IrTypesKt;
import org.jetbrains.kotlin.ir.util.AdditionalIrUtilsKt;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;
import org.jetbrains.kotlin.ir.util.KotlinMangler;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.StandardClassIds$Annotations;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u009c\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u0091\u00012\u00020\u0001:\u0004\u0090\u0001\u0091\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J-\u0010\u0005\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0001b\u0002\b\r¢\u0006\u0002\b\fJ\f\u0010\u000e\u001a\u00020\u000f*\u00020\nH\u0002J\f\u0010\u0010\u001a\u00020\u000f*\u00020\nH\u0002J\u0012\u0010\u0011\u001a\u00020\u0012*\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000fJ\u0016\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0007\u001a\u00020\u0006J!\u0010\u0017\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0018\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0000¢\u0006\u0002\b\u0019J!\u0010\u001a\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0018\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0000¢\u0006\u0002\b\u001bJ&\u0010\u001c\u001a\u0004\u0018\u00010\u0006*\u00020\u00062\u0006\u0010\u0018\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\u000fH\u0002J \u0010\u001e\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u00062\u0006\u0010 \u001a\u00020\n2\b\b\u0002\u0010!\u001a\u00020\"R\u0012\u0010#\u001a\u00020$X\u0096\u0005¢\u0006\u0006\u001a\u0004\b%\u0010&R\u0012\u0010'\u001a\u00020(X\u0096\u0005¢\u0006\u0006\u001a\u0004\b)\u0010*R\u0012\u0010+\u001a\u00020,X\u0096\u0005¢\u0006\u0006\u001a\u0004\b-\u0010.R\u0012\u0010/\u001a\u000200X\u0096\u0005¢\u0006\u0006\u001a\u0004\b1\u00102R\u0012\u00103\u001a\u000204X\u0096\u0005¢\u0006\u0006\u001a\u0004\b5\u00106R\u0012\u00107\u001a\u000208X\u0096\u0005¢\u0006\u0006\u001a\u0004\b9\u0010:R\u0012\u0010;\u001a\u00020<X\u0096\u0005¢\u0006\u0006\u001a\u0004\b=\u0010>R\u0012\u0010?\u001a\u00020@X\u0096\u0005¢\u0006\u0006\u001a\u0004\bA\u0010BR\u0012\u0010C\u001a\u00020DX\u0096\u0005¢\u0006\u0006\u001a\u0004\bE\u0010FR\u0012\u0010G\u001a\u00020HX\u0096\u0005¢\u0006\u0006\u001a\u0004\bI\u0010JR\u0012\u0010K\u001a\u00020LX\u0096\u0005¢\u0006\u0006\u001a\u0004\bM\u0010NR\u0012\u0010O\u001a\u00020PX\u0096\u0005¢\u0006\u0006\u001a\u0004\bQ\u0010RR\u0012\u0010S\u001a\u00020TX\u0096\u0005¢\u0006\u0006\u001a\u0004\bU\u0010VR\u001a\u0010W\u001a\n\u0012\u0004\u0012\u00020Y\u0018\u00010XX\u0096\u0005¢\u0006\u0006\u001a\u0004\bZ\u0010[R\u0012\u0010\\\u001a\u00020]X\u0096\u0005¢\u0006\u0006\u001a\u0004\b^\u0010_R\u0012\u0010`\u001a\u00020\u0000X\u0096\u0005¢\u0006\u0006\u001a\u0004\ba\u0010bR\u0012\u0010c\u001a\u00020dX\u0096\u0005¢\u0006\u0006\u001a\u0004\be\u0010fR\u0018\u0010g\u001a\b\u0012\u0004\u0012\u00020i0hX\u0096\u0005¢\u0006\u0006\u001a\u0004\bj\u0010kR\u0012\u0010l\u001a\u00020mX\u0096\u0005¢\u0006\u0006\u001a\u0004\bn\u0010oR\u0012\u0010p\u001a\u00020qX\u0096\u0005¢\u0006\u0006\u001a\u0004\br\u0010sR\u0012\u0010t\u001a\u00020uX\u0096\u0005¢\u0006\u0006\u001a\u0004\bv\u0010wR\u0012\u0010x\u001a\u00020yX\u0096\u0005¢\u0006\u0006\u001a\u0004\bz\u0010{R\u0012\u0010|\u001a\u00020}X\u0096\u0005¢\u0006\u0006\u001a\u0004\b~\u0010\u007fR\u0018\u0010\u0080\u0001\u001a\u0005\u0018\u00010\u0081\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0082\u0001\u0010\u0083\u0001R\u0016\u0010\u0084\u0001\u001a\u00030\u0085\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0086\u0001\u0010\u0087\u0001R\u0016\u0010\u0088\u0001\u001a\u00030\u0089\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u008a\u0001\u0010\u008b\u0001R\u0016\u0010\u008c\u0001\u001a\u00030\u008d\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u008e\u0001\u0010\u008f\u0001¨\u0006\u0092\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/Fir2IrImplicitCastInserter;", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "c", "<init>", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;)V", "insertSpecialCast", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "expression", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "valueType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "expectedType", "insertSpecialCast$org_jetbrains_kotlin_fir2ir", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrImplicitCastInserter$NoConversionsExpected;", "isEnhancedOrFlexibleMarkedNullable", Argument.Delimiters.none, "acceptsNullValues", "coerceStatementsToUnit", "Lorg/jetbrains/kotlin/ir/expressions/IrStatementContainer;", "coerceLastExpressionToUnit", "handleSmartCastExpression", "smartCastExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirSmartCastExpression;", "insertCastForReceiver", "argumentType", "insertCastForReceiver$org_jetbrains_kotlin_fir2ir", "insertCastForIntersectionTypeOrSelf", "insertCastForIntersectionTypeOrSelf$org_jetbrains_kotlin_fir2ir", "insertCastForIntersectionTypeOrNull", "forReceiver", "implicitCastOrExpression", "original", "castType", "typeOrigin", "Lorg/jetbrains/kotlin/fir/backend/utils/ConversionTypeOrigin;", "adapterGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/AdapterGenerator;", "getAdapterGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/AdapterGenerator;", "annotationGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/AnnotationGenerator;", "getAnnotationGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/AnnotationGenerator;", "annotationsFromPluginRegistrar", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar;", "getAnnotationsFromPluginRegistrar", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar;", "builtins", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrBuiltinSymbolsContainer;", "getBuiltins", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrBuiltinSymbolsContainer;", "callGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/CallAndReferenceGenerator;", "getCallGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/CallAndReferenceGenerator;", "callablesGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrCallableDeclarationsGenerator;", "getCallablesGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrCallableDeclarationsGenerator;", "classifierStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrClassifierStorage;", "getClassifierStorage", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrClassifierStorage;", "classifiersGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrClassifiersGenerator;", "getClassifiersGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrClassifiersGenerator;", "configuration", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;", "getConfiguration", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;", "converter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConverter;", "getConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrConverter;", "dataClassMembersGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrDataClassMembersGenerator;", "getDataClassMembersGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrDataClassMembersGenerator;", "declarationStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage;", "getDeclarationStorage", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage;", "extensions", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;", "getExtensions", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;", "filesBeingCompiled", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "getFilesBeingCompiled", "()Ljava/util/Set;", "firProvider", "Lorg/jetbrains/kotlin/fir/backend/FirProviderWithGeneratedFiles;", "getFirProvider", "()Lorg/jetbrains/kotlin/fir/backend/FirProviderWithGeneratedFiles;", "implicitCastInserter", "getImplicitCastInserter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrImplicitCastInserter;", "irMangler", "Lorg/jetbrains/kotlin/ir/util/KotlinMangler$IrMangler;", "getIrMangler", "()Lorg/jetbrains/kotlin/ir/util/KotlinMangler$IrMangler;", "irProviders", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/ir/IrProvider;", "getIrProviders", "()Ljava/util/List;", "lazyDeclarationsGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyDeclarationsGenerator;", "getLazyDeclarationsGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyDeclarationsGenerator;", "lazyFakeOverrideGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyFakeOverrideGenerator;", "getLazyFakeOverrideGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyFakeOverrideGenerator;", "lock", "Lorg/jetbrains/kotlin/ir/IrLock;", "getLock", "()Lorg/jetbrains/kotlin/ir/IrLock;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "getScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "specialAnnotationsProvider", "Lorg/jetbrains/kotlin/backend/common/IrSpecialAnnotationsProvider;", "getSpecialAnnotationsProvider", "()Lorg/jetbrains/kotlin/backend/common/IrSpecialAnnotationsProvider;", "symbolsMappingForLazyClasses", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrSymbolsMappingForLazyClasses;", "getSymbolsMappingForLazyClasses", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrSymbolsMappingForLazyClasses;", "typeConverter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrTypeConverter;", "getTypeConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrTypeConverter;", "visibilityConverter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisibilityConverter;", "getVisibilityConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisibilityConverter;", "NoConversionsExpected", "Companion", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class Fir2IrImplicitCastInserter implements Fir2IrComponents {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final /* synthetic */ Fir2IrComponents $$delegate_0;

    @Retention(RetentionPolicy.RUNTIME)
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\n\u0002\u0018\u0002\b\u0087\u0002\u0018\u00002\u00020\u0001B\u0000Ê\u0001\u0002\b\u0003¨\u0006\u0002"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/Fir2IrImplicitCastInserter$NoConversionsExpected;", Argument.Delimiters.none, "org.jetbrains.kotlin:fir2ir", "Lkotlin/RequiresOptIn;"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public @interface NoConversionsExpected {
    }

    public Fir2IrImplicitCastInserter(Fir2IrComponents fir2IrComponents) {
        fir2IrComponents.getClass();
        this.$$delegate_0 = fir2IrComponents;
    }

    private final boolean acceptsNullValues(ConeKotlinType coneKotlinType) {
        if (coneKotlinType instanceof ConeCapturedType) {
            ConeCapturedType coneCapturedType = (ConeCapturedType) coneKotlinType;
            if (coneCapturedType.getConstructor().getProjection().getKind() == ProjectionKind.IN) {
                if (coneCapturedType.isMarkedNullable()) {
                    return true;
                }
                ConeKotlinType type = ConeTypeProjectionKt.getType(coneCapturedType.getConstructor().getProjection());
                type.getClass();
                return TypeUtilsKt.canBeNull$default(type, getSession(), false, null, 6, null);
            }
        }
        return TypeUtilsKt.canBeNull$default(coneKotlinType, getSession(), false, null, 6, null) || CompilerConeAttributesKt.getHasEnhancedNullability(coneKotlinType);
    }

    public static /* synthetic */ IrExpression implicitCastOrExpression$default(Fir2IrImplicitCastInserter fir2IrImplicitCastInserter, IrExpression irExpression, ConeKotlinType coneKotlinType, ConversionTypeOrigin conversionTypeOrigin, int i, Object obj) {
        if ((i & 4) != 0) {
            conversionTypeOrigin = ConversionTypeOrigin.DEFAULT;
        }
        return fir2IrImplicitCastInserter.implicitCastOrExpression(irExpression, coneKotlinType, conversionTypeOrigin);
    }

    private final IrExpression insertCastForIntersectionTypeOrNull(IrExpression irExpression, ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2, boolean z) {
        Object next;
        ConeRigidType coneRigidTypeLowerBoundIfFlexible = ConeTypeUtilsKt.lowerBoundIfFlexible(coneKotlinType);
        if (!(coneRigidTypeLowerBoundIfFlexible instanceof ConeIntersectionType)) {
            return null;
        }
        ConeKotlinType coneKotlinTypeApproximateForIrOrSelf = Fir2IrTypeConverterKt.approximateForIrOrSelf(this, coneKotlinType2);
        if (!z) {
            ConeKotlinType coneKotlinTypeApproximateForIrOrNull = Fir2IrTypeConverterKt.approximateForIrOrNull(this, coneRigidTypeLowerBoundIfFlexible);
            if (TypeUtilsKt.isSubtypeOf$default(coneKotlinTypeApproximateForIrOrNull == null ? coneRigidTypeLowerBoundIfFlexible : coneKotlinTypeApproximateForIrOrNull, coneKotlinTypeApproximateForIrOrSelf, getSession(), false, 4, null)) {
                return null;
            }
        }
        Iterator<T> it = ((ConeIntersectionType) coneRigidTypeLowerBoundIfFlexible).getIntersectedTypes().iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!TypeUtilsKt.isSubtypeOf$default((ConeKotlinType) next, coneKotlinTypeApproximateForIrOrSelf, getSession(), false, 4, null));
        ConeKotlinType coneKotlinType3 = (ConeKotlinType) next;
        if (coneKotlinType3 != null) {
            return INSTANCE.generateImplicitCast(irExpression, Fir2IrTypeConverterKt.toIrType$default(this, coneKotlinType3, (ConversionTypeOrigin) null, 2, (Object) null));
        }
        return null;
    }

    private final boolean isEnhancedOrFlexibleMarkedNullable(ConeKotlinType coneKotlinType) {
        return CompilerConeAttributesKt.getHasEnhancedNullability(coneKotlinType) || ConeTypeUtilsKt.getHasFlexibleMarkedNullability(coneKotlinType);
    }

    public final IrStatementContainer coerceStatementsToUnit(IrStatementContainer irStatementContainer, boolean z) {
        irStatementContainer.getClass();
        if (!irStatementContainer.getStatements().isEmpty()) {
            int lastIndex = CollectionsKt.getLastIndex(irStatementContainer.getStatements());
            int i = 0;
            for (Object obj : irStatementContainer.getStatements()) {
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                IrExpression irExpression = (IrStatement) obj;
                if (!(irExpression instanceof IrErrorCallExpression) && (irExpression instanceof IrExpression) && (i != lastIndex || z)) {
                    irStatementContainer.getStatements().set(i, INSTANCE.coerceToUnitIfNeeded$org_jetbrains_kotlin_fir2ir(this, irExpression));
                }
                i = i2;
            }
        }
        return irStatementContainer;
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public AdapterGenerator getAdapterGenerator() {
        return this.$$delegate_0.getAdapterGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public AnnotationGenerator getAnnotationGenerator() {
        return this.$$delegate_0.getAnnotationGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrIrGeneratedDeclarationsRegistrar getAnnotationsFromPluginRegistrar() {
        return this.$$delegate_0.getAnnotationsFromPluginRegistrar();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrBuiltinSymbolsContainer getBuiltins() {
        return this.$$delegate_0.getBuiltins();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public CallAndReferenceGenerator getCallGenerator() {
        return this.$$delegate_0.getCallGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrCallableDeclarationsGenerator getCallablesGenerator() {
        return this.$$delegate_0.getCallablesGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrClassifierStorage getClassifierStorage() {
        return this.$$delegate_0.getClassifierStorage();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrClassifiersGenerator getClassifiersGenerator() {
        return this.$$delegate_0.getClassifiersGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrConfiguration getConfiguration() {
        return this.$$delegate_0.getConfiguration();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrConverter getConverter() {
        return this.$$delegate_0.getConverter();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrDataClassMembersGenerator getDataClassMembersGenerator() {
        return this.$$delegate_0.getDataClassMembersGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrDeclarationStorage getDeclarationStorage() {
        return this.$$delegate_0.getDeclarationStorage();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrExtensions getExtensions() {
        return this.$$delegate_0.getExtensions();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Set<FirFile> getFilesBeingCompiled() {
        return this.$$delegate_0.getFilesBeingCompiled();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public FirProviderWithGeneratedFiles getFirProvider() {
        return this.$$delegate_0.getFirProvider();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrImplicitCastInserter getImplicitCastInserter() {
        return this.$$delegate_0.getImplicitCastInserter();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public KotlinMangler.IrMangler getIrMangler() {
        return this.$$delegate_0.getIrMangler();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public List<IrProvider> getIrProviders() {
        return this.$$delegate_0.getIrProviders();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrLazyDeclarationsGenerator getLazyDeclarationsGenerator() {
        return this.$$delegate_0.getLazyDeclarationsGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrLazyFakeOverrideGenerator getLazyFakeOverrideGenerator() {
        return this.$$delegate_0.getLazyFakeOverrideGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public IrLock getLock() {
        return this.$$delegate_0.getLock();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents, org.jetbrains.kotlin.fir.ScopeSessionHolder
    public ScopeSession getScopeSession() {
        return this.$$delegate_0.getScopeSession();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents, org.jetbrains.kotlin.fir.SessionHolder
    public FirSession getSession() {
        return this.$$delegate_0.getSession();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public IrSpecialAnnotationsProvider getSpecialAnnotationsProvider() {
        return this.$$delegate_0.getSpecialAnnotationsProvider();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrSymbolsMappingForLazyClasses getSymbolsMappingForLazyClasses() {
        return this.$$delegate_0.getSymbolsMappingForLazyClasses();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrTypeConverter getTypeConverter() {
        return this.$$delegate_0.getTypeConverter();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrVisibilityConverter getVisibilityConverter() {
        return this.$$delegate_0.getVisibilityConverter();
    }

    public final IrExpression handleSmartCastExpression(FirSmartCastExpression smartCastExpression, IrExpression expression) {
        ConeKotlinType resolvedType;
        ConeKotlinType coneKotlinTypeApproximateForIrOrNull;
        smartCastExpression.getClass();
        expression.getClass();
        if (smartCastExpression.isStable() && smartCastExpression.getSmartcastTypeWithoutNullableNothing() == null && ((coneKotlinTypeApproximateForIrOrNull = Fir2IrTypeConverterKt.approximateForIrOrNull(this, (resolvedType = FirTypeUtilsKt.getResolvedType(smartCastExpression)))) == null || !TypeUtilsKt.isSubtypeOf$default(TypeUtilsKt.withNullability$default(FirTypeUtilsKt.getResolvedType(smartCastExpression.getOriginalExpression()), false, TypeComponentsKt.getTypeContext(getSession()), null, false, 12, null), coneKotlinTypeApproximateForIrOrNull, getSession(), false, 4, null))) {
            return implicitCastOrExpression$default(this, expression, coneKotlinTypeApproximateForIrOrNull == null ? resolvedType : coneKotlinTypeApproximateForIrOrNull, null, 4, null);
        }
        return expression;
    }

    public final IrExpression implicitCastOrExpression(IrExpression original, ConeKotlinType castType, ConversionTypeOrigin typeOrigin) {
        original.getClass();
        castType.getClass();
        typeOrigin.getClass();
        return INSTANCE.implicitCastOrExpression(original, Fir2IrTypeConverterKt.toIrType(this, castType, typeOrigin));
    }

    public final IrExpression insertCastForIntersectionTypeOrSelf$org_jetbrains_kotlin_fir2ir(IrExpression irExpression, ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2) {
        irExpression.getClass();
        coneKotlinType.getClass();
        coneKotlinType2.getClass();
        IrExpression irExpressionInsertCastForIntersectionTypeOrNull = insertCastForIntersectionTypeOrNull(irExpression, coneKotlinType, coneKotlinType2, false);
        return irExpressionInsertCastForIntersectionTypeOrNull == null ? irExpression : irExpressionInsertCastForIntersectionTypeOrNull;
    }

    public final IrExpression insertCastForReceiver$org_jetbrains_kotlin_fir2ir(IrExpression irExpression, ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2) {
        irExpression.getClass();
        coneKotlinType.getClass();
        coneKotlinType2.getClass();
        IrExpression irExpressionInsertCastForIntersectionTypeOrNull = insertCastForIntersectionTypeOrNull(irExpression, coneKotlinType, coneKotlinType2, true);
        return irExpressionInsertCastForIntersectionTypeOrNull == null ? implicitCastOrExpression$default(this, irExpression, coneKotlinType, null, 4, null) : irExpressionInsertCastForIntersectionTypeOrNull;
    }

    @NoConversionsExpected
    public final IrExpression insertSpecialCast$org_jetbrains_kotlin_fir2ir(IrExpression irExpression, FirExpression firExpression, ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2) {
        irExpression.getClass();
        firExpression.getClass();
        coneKotlinType.getClass();
        coneKotlinType2.getClass();
        if (irExpression instanceof IrTypeOperatorCall) {
            return irExpression;
        }
        if (irExpression instanceof IrContainerExpression) {
            coerceStatementsToUnit((IrStatementContainer) irExpression, IrTypePredicatesKt.isUnit(irExpression.getType()));
        }
        ConeKotlinType coneKotlinTypeFullyExpandedType = TypeExpansionUtilsKt.fullyExpandedType(this, coneKotlinType);
        ConeKotlinType coneKotlinTypeFullyExpandedType2 = TypeExpansionUtilsKt.fullyExpandedType(this, coneKotlinType2);
        if (ConeBuiltinTypeUtilsKt.isUnit(coneKotlinTypeFullyExpandedType2)) {
            return INSTANCE.coerceToUnitIfNeeded$org_jetbrains_kotlin_fir2ir(this, irExpression);
        }
        if (coneKotlinTypeFullyExpandedType instanceof ConeDynamicType) {
            if (!(coneKotlinTypeFullyExpandedType2 instanceof ConeDynamicType) && !ConeBuiltinTypeUtilsKt.isNullableAny(coneKotlinTypeFullyExpandedType2)) {
                return INSTANCE.generateImplicitCast(irExpression, Fir2IrTypeConverterKt.toIrType(this, coneKotlinTypeFullyExpandedType2, ConversionTypeOrigin.DEFAULT));
            }
        } else if (isEnhancedOrFlexibleMarkedNullable(coneKotlinTypeFullyExpandedType) && !acceptsNullValues(coneKotlinTypeFullyExpandedType2) && firExpression.getSource() != null && !(irExpression instanceof IrGetEnumValue)) {
            return INSTANCE.implicitNotNullCast(irExpression);
        }
        return irExpression;
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bJ\u0018\u0010\t\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0002J#\u0010\n\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0000R\u00020\u000bj\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0006\u001a\u00020\u0005¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/Fir2IrImplicitCastInserter$Companion;", Argument.Delimiters.none, "<init>", "()V", "implicitCastOrExpression", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "original", "castType", "Lorg/jetbrains/kotlin/ir/types/IrType;", "generateImplicitCast", "coerceToUnitIfNeeded", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "c", "coerceToUnitIfNeeded$org_jetbrains_kotlin_fir2ir", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;Lorg/jetbrains/kotlin/ir/expressions/IrExpression;)Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "implicitNotNullCast", "Lorg/jetbrains/kotlin/ir/expressions/IrTypeOperatorCall;", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static boolean a(IrConstructorCall irConstructorCall) {
            irConstructorCall.getClass();
            IrFunctionSymbol symbol = irConstructorCall.getSymbol();
            if (!symbol.isBound()) {
                symbol = null;
            }
            if (symbol == null) {
                return false;
            }
            ClassId classId = AdditionalIrUtilsKt.getClassId(IrUtilsKt.getParentAsClass(symbol.getOwner()));
            StandardClassIds$Annotations standardClassIds$Annotations = StandardClassIds$Annotations.INSTANCE;
            return Intrinsics.areEqual(classId, standardClassIds$Annotations.getEnhancedNullability()) || Intrinsics.areEqual(classId, standardClassIds$Annotations.getFlexibleNullability());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final IrExpression generateImplicitCast(IrExpression original, IrType castType) {
            return VariousUtilsKt.implicitCast(original, castType, original.getType() instanceof IrDynamicType ? IrTypeOperator.IMPLICIT_DYNAMIC_CAST : IrTypeOperator.IMPLICIT_CAST);
        }

        public final IrExpression coerceToUnitIfNeeded$org_jetbrains_kotlin_fir2ir(Fir2IrComponents fir2IrComponents, IrExpression irExpression) {
            fir2IrComponents.getClass();
            irExpression.getClass();
            IrType type = irExpression.getType();
            return (IrTypePredicatesKt.isUnit(type) || IrTypePredicatesKt.isNothing(type)) ? irExpression : BuildersKt.IrTypeOperatorCallImpl(irExpression.getStartOffset(), irExpression.getEndOffset(), fir2IrComponents.getBuiltins().getUnitType(), IrTypeOperator.IMPLICIT_COERCION_TO_UNIT, fir2IrComponents.getBuiltins().getUnitType(), irExpression);
        }

        public final IrExpression implicitCastOrExpression(IrExpression original, IrType castType) {
            original.getClass();
            castType.getClass();
            return Intrinsics.areEqual(original.getType(), castType) ? original : generateImplicitCast(original, castType);
        }

        public final IrTypeOperatorCall implicitNotNullCast(IrExpression original) {
            original.getClass();
            IrType irTypeMakeNotNull = IrTypesKt.makeNotNull(IrTypesKt.removeAnnotations(original.getType(), new Function1() { // from class: ov4
                public final Object invoke(Object obj) {
                    return Boolean.valueOf(Fir2IrImplicitCastInserter.Companion.a((IrConstructorCall) obj));
                }
            }));
            return BuildersKt.IrTypeOperatorCallImpl(original.getStartOffset(), original.getEndOffset(), irTypeMakeNotNull, IrTypeOperator.IMPLICIT_NOTNULL, irTypeMakeNotNull, original);
        }

        private Companion() {
        }
    }
}
