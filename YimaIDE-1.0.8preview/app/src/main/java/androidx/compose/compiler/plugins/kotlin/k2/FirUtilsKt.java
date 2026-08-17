package androidx.compose.compiler.plugins.kotlin.k2;

import androidx.compose.compiler.plugins.kotlin.ComposeClassIds;
import androidx.compose.compiler.plugins.kotlin.ComposeMetadata;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.builtins.functions.FunctionTypeKind;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElementWithResolveState;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirReturnExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.references.FirNamedReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScopeKt;
import org.jetbrains.kotlin.fir.scopes.FirKotlinScopeProviderKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertyAccessorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjectionKt;
import org.jetbrains.kotlin.fir.types.CustomAnnotationTypeAttributeKt;
import org.jetbrains.kotlin.fir.types.FunctionalTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ProjectionKind;
import org.jetbrains.kotlin.name.JvmStandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000`\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00052\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0006\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0016\u0010\u0006\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00052\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0007\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0016\u0010\t\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\n2\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\t\u001a\u00020\u0001*\u00020\u000b2\u0006\u0010\f\u001a\u00020\r\u001a\u0016\u0010\u000e\u001a\u0004\u0018\u00010\u000f*\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002\u001a\u0016\u0010\u0010\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\n2\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0011\u001a\u00020\u0001*\u00020\u00122\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0014\u0010\u0013\u001a\u00020\u0001*\u00020\u00142\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a\u001c\u0010\u0015\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00170\u0016*\u00020\u00182\u0006\u0010\f\u001a\u00020\r\u001a\u0016\u0010\u0019\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00172\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0014\u0010\u001a\u001a\u00020\u001b*\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\"\"\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00120\u0016*\u0006\u0012\u0002\b\u00030\u00178BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001e\"\u001a\u0010\u001f\u001a\u0004\u0018\u00010 *\u00020!8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#¨\u0006$"}, d2 = {"hasComposableAnnotation", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/FirAnnotationContainer;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "hasReadOnlyComposableAnnotation", "hasDisallowComposableCallsAnnotation", "hasComposableTargetMarkerAnnotation", "isComposable", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirValueParameterSymbol;", "context", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "findSamFunction", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "isReadOnlyComposable", "isComposableFunction", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "isComposableDelegate", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertyAccessorSymbol;", "getDirectOverriddenFunctions", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "isMain", "jvmNameAsString", Argument.Delimiters.none, "explicitParameterTypes", "getExplicitParameterTypes", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;)Ljava/util/List;", "composeMetadata", "Landroidx/compose/compiler/plugins/kotlin/ComposeMetadata;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "getComposeMetadata", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)[B", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirUtilsKt {
    private static final FirNamedFunctionSymbol findSamFunction(FirValueParameterSymbol firValueParameterSymbol, CheckerContext checkerContext) {
        ConeKotlinType resolvedReturnType = firValueParameterSymbol.getResolvedReturnType();
        FirSession session = checkerContext.getSession();
        FirClassSymbol<?> classSymbol = ToSymbolUtilsKt.toClassSymbol(resolvedReturnType, session);
        Object obj = null;
        if (classSymbol == null) {
            return null;
        }
        boolean z = false;
        Object obj2 = null;
        for (Object obj3 : FirContainingNamesAwareScopeKt.collectAllFunctions(FirKotlinScopeProviderKt.unsubstitutedScope(classSymbol, session, checkerContext.getScopeSession(), true, FirResolvePhase.INSTANCE.getDECLARATIONS()))) {
            if (((FirNamedFunctionSymbol) obj3).getResolvedStatus().getModality() == Modality.ABSTRACT) {
                if (z) {
                    return (FirNamedFunctionSymbol) obj;
                }
                obj2 = obj3;
                z = true;
            }
        }
        if (z) {
            obj = obj2;
        }
        return (FirNamedFunctionSymbol) obj;
    }

    public static final byte[] getComposeMetadata(FirDeclaration firDeclaration) {
        byte[] bArr;
        firDeclaration.getClass();
        Map<String, byte[]> compilerPluginMetadata = DeclarationAttributesKt.getCompilerPluginMetadata(firDeclaration);
        if (compilerPluginMetadata == null || (bArr = compilerPluginMetadata.get("androidx.compose.compiler.plugins.kotlin")) == null) {
            return null;
        }
        return ComposeMetadata.constructor-impl(bArr);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final List<FirFunctionSymbol<?>> getDirectOverriddenFunctions(FirFunction firFunction, CheckerContext checkerContext) {
        List<FirNamedFunctionSymbol> listEmptyList;
        firFunction.getClass();
        checkerContext.getClass();
        FirFunctionSymbol<FirFunction> symbol = firFunction.getSymbol();
        if (symbol instanceof FirNamedFunctionSymbol) {
            listEmptyList = FirHelpersKt.directOverriddenFunctionsSafe((FirNamedFunctionSymbol) symbol, checkerContext);
        } else if (symbol instanceof FirPropertyAccessorSymbol) {
            FirPropertyAccessorSymbol firPropertyAccessorSymbol = (FirPropertyAccessorSymbol) symbol;
            List<FirPropertySymbol> listDirectOverriddenPropertiesSafe = FirHelpersKt.directOverriddenPropertiesSafe(firPropertyAccessorSymbol.getPropertySymbol(), checkerContext);
            ArrayList arrayList = new ArrayList();
            for (FirPropertySymbol firPropertySymbol : listDirectOverriddenPropertiesSafe) {
                FirPropertyAccessorSymbol getterSymbol = firPropertyAccessorSymbol.isGetter() ? firPropertySymbol.getGetterSymbol() : firPropertySymbol.getSetterSymbol();
                if (getterSymbol != null) {
                    arrayList.add(getterSymbol);
                }
            }
            listEmptyList = arrayList;
        } else {
            listEmptyList = CollectionsKt.emptyList();
        }
        List<FirNamedFunctionSymbol> list = listEmptyList;
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) ((FirFunctionSymbol) it.next()).getFir();
            while (ClassMembersKt.isSubstitutionOrIntersectionOverride(firCallableDeclaration)) {
                FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
                if (originalForSubstitutionOverrideAttr == null) {
                    originalForSubstitutionOverrideAttr = ClassMembersKt.isIntersectionOverride(firCallableDeclaration) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
                }
                if (originalForSubstitutionOverrideAttr == null) {
                    break;
                }
                firCallableDeclaration = originalForSubstitutionOverrideAttr;
            }
            FirCallableSymbol<FirCallableDeclaration> symbol2 = firCallableDeclaration.getSymbol();
            if (symbol2 == null) {
                x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol<*>");
                return null;
            }
            arrayList2.add((FirFunctionSymbol) symbol2);
        }
        return arrayList2;
    }

    private static final List<ConeKotlinType> getExplicitParameterTypes(FirFunctionSymbol<?> firFunctionSymbol) {
        List<FirValueParameterSymbol> contextParameterSymbols = firFunctionSymbol.getContextParameterSymbols();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(contextParameterSymbols, 10));
        Iterator<T> it = contextParameterSymbols.iterator();
        while (it.hasNext()) {
            arrayList.add(((FirValueParameterSymbol) it.next()).getResolvedReturnType());
        }
        List listPlus = CollectionsKt.plus(arrayList, CollectionsKt.listOfNotNull(firFunctionSymbol.getResolvedReceiverType()));
        List<FirValueParameterSymbol> valueParameterSymbols = firFunctionSymbol.getValueParameterSymbols();
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(valueParameterSymbols, 10));
        Iterator<T> it2 = valueParameterSymbols.iterator();
        while (it2.hasNext()) {
            arrayList2.add(((FirValueParameterSymbol) it2.next()).getResolvedReturnType());
        }
        return CollectionsKt.plus(listPlus, arrayList2);
    }

    public static final boolean hasComposableAnnotation(FirAnnotationContainer firAnnotationContainer, FirSession firSession) {
        firAnnotationContainer.getClass();
        firSession.getClass();
        return FirAnnotationUtilsKt.hasAnnotation(firAnnotationContainer, ComposeClassIds.INSTANCE.getComposable(), firSession);
    }

    public static final boolean hasComposableTargetMarkerAnnotation(FirAnnotationContainer firAnnotationContainer, FirSession firSession) {
        firAnnotationContainer.getClass();
        firSession.getClass();
        return FirAnnotationUtilsKt.hasAnnotation(firAnnotationContainer, ComposeClassIds.INSTANCE.getComposableTargetMarker(), firSession);
    }

    public static final boolean hasDisallowComposableCallsAnnotation(FirAnnotationContainer firAnnotationContainer, FirSession firSession) {
        firAnnotationContainer.getClass();
        firSession.getClass();
        return FirAnnotationUtilsKt.hasAnnotation(firAnnotationContainer, ComposeClassIds.INSTANCE.getDisallowComposableCalls(), firSession);
    }

    public static final boolean hasReadOnlyComposableAnnotation(FirAnnotationContainer firAnnotationContainer, FirSession firSession) {
        firAnnotationContainer.getClass();
        firSession.getClass();
        return FirAnnotationUtilsKt.hasAnnotation(firAnnotationContainer, ComposeClassIds.INSTANCE.getReadOnlyComposable(), firSession);
    }

    public static final boolean isComposable(FirValueParameterSymbol firValueParameterSymbol, CheckerContext checkerContext) {
        FirNamedFunctionSymbol firNamedFunctionSymbolFindSamFunction;
        firValueParameterSymbol.getClass();
        checkerContext.getClass();
        return FirAnnotationUtilsKt.hasAnnotation(CustomAnnotationTypeAttributeKt.getCustomAnnotations(firValueParameterSymbol.getResolvedReturnType()), ComposeClassIds.INSTANCE.getComposable(), checkerContext.getSession()) || ((firNamedFunctionSymbolFindSamFunction = findSamFunction(firValueParameterSymbol, checkerContext)) != null && isComposable(firNamedFunctionSymbolFindSamFunction, checkerContext.getSession()));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v2, types: [org.jetbrains.kotlin.fir.FirElementWithResolveState, org.jetbrains.kotlin.fir.declarations.FirDeclaration] */
    private static final boolean isComposableDelegate(FirPropertyAccessorSymbol firPropertyAccessorSymbol, FirSession firSession) {
        FirNamedReference calleeReference;
        FirCallableSymbol resolvedCallableSymbol$default;
        List<FirStatement> statements;
        if (!firPropertyAccessorSymbol.getPropertySymbol().getHasDelegate()) {
            return false;
        }
        FirLazyDeclarationResolverKt.lazyResolveToPhase((FirElementWithResolveState) firPropertyAccessorSymbol.getFir(), FirResolvePhase.BODY_RESOLVE);
        FirBlock body = ((FirPropertyAccessor) firPropertyAccessorSymbol.getFir()).getBody();
        FirStatement firStatement = (body == null || (statements = body.getStatements()) == null) ? null : (FirStatement) CollectionsKt.singleOrNull(statements);
        FirReturnExpression firReturnExpression = firStatement instanceof FirReturnExpression ? (FirReturnExpression) firStatement : null;
        FirExpression result = firReturnExpression != null ? firReturnExpression.getResult() : null;
        FirFunctionCall firFunctionCall = result instanceof FirFunctionCall ? (FirFunctionCall) result : null;
        if (firFunctionCall == null || (calleeReference = firFunctionCall.getCalleeReference()) == null || (resolvedCallableSymbol$default = FirReferenceUtilsKt.toResolvedCallableSymbol$default(calleeReference, false, 1, null)) == null) {
            return false;
        }
        return isComposable((FirCallableSymbol<?>) resolvedCallableSymbol$default, firSession);
    }

    public static final boolean isComposableFunction(ConeKotlinType coneKotlinType, FirSession firSession) {
        coneKotlinType.getClass();
        firSession.getClass();
        FunctionTypeKind functionTypeKindFunctionTypeKind$default = FunctionalTypeUtilsKt.functionTypeKind$default(coneKotlinType, firSession, false, 2, (Object) null);
        return Intrinsics.areEqual(functionTypeKindFunctionTypeKind$default, ComposableFunction.INSTANCE) || Intrinsics.areEqual(functionTypeKindFunctionTypeKind$default, KComposableFunction.INSTANCE);
    }

    public static final boolean isMain(FirFunctionSymbol<?> firFunctionSymbol, FirSession firSession) {
        ConeKotlinType type;
        firFunctionSymbol.getClass();
        firSession.getClass();
        if (!(firFunctionSymbol instanceof FirNamedFunctionSymbol)) {
            return false;
        }
        FirNamedFunctionSymbol firNamedFunctionSymbol = (FirNamedFunctionSymbol) firFunctionSymbol;
        if (!firNamedFunctionSymbol.getTypeParameterSymbols().isEmpty() || !ConeBuiltinTypeUtilsKt.isUnit(firNamedFunctionSymbol.getResolvedReturnType()) || !Intrinsics.areEqual(jvmNameAsString(firNamedFunctionSymbol, firSession), "main")) {
            return false;
        }
        List<ConeKotlinType> explicitParameterTypes = getExplicitParameterTypes(firFunctionSymbol);
        int size = explicitParameterTypes.size();
        if (size != 0) {
            if (size != 1) {
                return false;
            }
            ConeKotlinType coneKotlinType = (ConeKotlinType) CollectionsKt.single(explicitParameterTypes);
            if (ConeBuiltinTypeUtilsKt.isArrayType(coneKotlinType) && coneKotlinType.getTypeArguments().length == 1) {
                ConeTypeProjection coneTypeProjection = coneKotlinType.getTypeArguments()[0];
                if (coneTypeProjection.getKind() == ProjectionKind.IN) {
                    coneTypeProjection = null;
                }
                if (coneTypeProjection == null || (type = ConeTypeProjectionKt.getType(coneTypeProjection)) == null || !ConeBuiltinTypeUtilsKt.isString(type)) {
                }
            }
            return false;
        }
        return true;
    }

    public static final boolean isReadOnlyComposable(FirCallableSymbol<?> firCallableSymbol, FirSession firSession) {
        FirPropertyAccessorSymbol getterSymbol;
        firCallableSymbol.getClass();
        firSession.getClass();
        if (firCallableSymbol instanceof FirFunctionSymbol) {
            return hasReadOnlyComposableAnnotation(firCallableSymbol, firSession);
        }
        if (!(firCallableSymbol instanceof FirPropertySymbol) || (getterSymbol = ((FirPropertySymbol) firCallableSymbol).getGetterSymbol()) == null) {
            return false;
        }
        return hasReadOnlyComposableAnnotation(getterSymbol, firSession);
    }

    private static final String jvmNameAsString(FirNamedFunctionSymbol firNamedFunctionSymbol, FirSession firSession) {
        String annotationStringParameter = FirHelpersKt.getAnnotationStringParameter(firNamedFunctionSymbol, JvmStandardClassIds.Annotations.INSTANCE.getJvmName(), firSession);
        if (annotationStringParameter != null) {
            return annotationStringParameter;
        }
        String strAsString = firNamedFunctionSymbol.getName().asString();
        strAsString.getClass();
        return strAsString;
    }

    public static final boolean hasComposableAnnotation(FirBasedSymbol<?> firBasedSymbol, FirSession firSession) {
        firBasedSymbol.getClass();
        firSession.getClass();
        return FirAnnotationUtilsKt.hasAnnotation(firBasedSymbol, ComposeClassIds.INSTANCE.getComposable(), firSession);
    }

    public static final boolean hasReadOnlyComposableAnnotation(FirBasedSymbol<?> firBasedSymbol, FirSession firSession) {
        firBasedSymbol.getClass();
        firSession.getClass();
        return FirAnnotationUtilsKt.hasAnnotation(firBasedSymbol, ComposeClassIds.INSTANCE.getReadOnlyComposable(), firSession);
    }

    public static final boolean isComposable(FirCallableSymbol<?> firCallableSymbol, FirSession firSession) {
        FirPropertyAccessorSymbol getterSymbol;
        firCallableSymbol.getClass();
        firSession.getClass();
        if (firCallableSymbol instanceof FirFunctionSymbol) {
            return hasComposableAnnotation(firCallableSymbol, firSession);
        }
        if (!(firCallableSymbol instanceof FirPropertySymbol) || (getterSymbol = ((FirPropertySymbol) firCallableSymbol).getGetterSymbol()) == null) {
            return false;
        }
        return hasComposableAnnotation(getterSymbol, firSession) || isComposableDelegate(getterSymbol, firSession);
    }
}
