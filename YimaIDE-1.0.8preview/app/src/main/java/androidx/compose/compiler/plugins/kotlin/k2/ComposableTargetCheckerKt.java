package androidx.compose.compiler.plugins.kotlin.k2;

import androidx.compose.compiler.plugins.kotlin.ComposeClassIds;
import androidx.compose.compiler.plugins.kotlin.ComposeFqNames;
import androidx.compose.compiler.plugins.kotlin.inference.Item;
import androidx.compose.compiler.plugins.kotlin.inference.Open;
import androidx.compose.compiler.plugins.kotlin.inference.Scheme;
import androidx.compose.compiler.plugins.kotlin.inference.SchemeKt;
import androidx.compose.compiler.plugins.kotlin.inference.Token;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.CustomAnnotationTypeAttributeKt;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.util.ArrayMapAccessor;
import org.jetbrains.kotlin.util.TypeRegistry;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0090\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a$\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002\u001a\u001a\u0010\b\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u0002\u001a\u0018\u0010\t\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H\u0002\u001a\u001f\u0010\u000b\u001a\u00020\f*\u0006\u0012\u0002\b\u00030\u0005R\u00020\u0007j\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\r\u001a)\u0010\u000e\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00100\u000f*\u0006\u0012\u0002\b\u00030\u0005R\u00020\u0007j\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\u0011\u001a%\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u000f*\u0006\u0012\u0002\b\u00030\u0005R\u00020\u0007j\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\u0011\u001a#\u0010\u0014\u001a\u0004\u0018\u00010\u0015*\u0006\u0012\u0002\b\u00030\u0005H\u0002R\u00020\u0016j\u0006\u0010\u0017\u001a\u00020\u0016¢\u0006\u0002\u0010\u0018\u001a!\u0010\u0019\u001a\u0004\u0018\u00010\f*\u0006\u0012\u0002\b\u00030\u0005R\u00020\u0016j\u0006\u0010\u0017\u001a\u00020\u0016¢\u0006\u0002\u0010\u001a\u001a\u001f\u0010\u001b\u001a\u00020\u0015*\u0006\u0012\u0002\b\u00030\u0005R\u00020\u0016j\u0006\u0010\u0017\u001a\u00020\u0016¢\u0006\u0002\u0010\u0018\u001a!\u0010\u001c\u001a\u0004\u0018\u00010\u001d*\u0006\u0012\u0002\b\u00030\u0005R\u00020\u0016j\u0006\u0010\u0017\u001a\u00020\u0016¢\u0006\u0002\u0010\u001e\u001a\u001d\u0010\u001f\u001a\u0004\u0018\u00010\u001d*\u00020 R\u00020\u0016j\u0006\u0010\u0017\u001a\u00020\u0016¢\u0006\u0002\u0010!\u001a!\u0010\"\u001a\u0004\u0018\u00010#*\u0006\u0012\u0002\b\u00030\u0005R\u00020\u0016j\u0006\u0010\u0017\u001a\u00020\u0016¢\u0006\u0002\u0010$\u001a1\u0010%\u001a\u0004\u0018\u00010&*\u0006\u0012\u0002\b\u00030'2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+R\u00020\u0016j\u0006\u0010\u0017\u001a\u00020\u0016¢\u0006\u0002\u0010,\u001a\u001d\u0010\u001c\u001a\u0004\u0018\u00010\u001d*\u00020-R\u00020\u0016j\u0006\u0010\u0017\u001a\u00020\u0016¢\u0006\u0002\u0010.\u001a-\u0010%\u001a\u0004\u0018\u00010&*\u00020-2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+R\u00020\u0016j\u0006\u0010\u0017\u001a\u00020\u0016¢\u0006\u0002\u0010/\u001a\u0014\u00100\u001a\u0004\u0018\u00010&*\u0002012\u0006\u00102\u001a\u00020+\u001a\u0010\u00103\u001a\u0002042\u0006\u0010\u0006\u001a\u00020\u0007H\u0002\"'\u00105\u001a\u0004\u0018\u00010\u0003*\u00020\u00038BX\u0082\u0004b\u00020\u0016\u008a\u0001\u0006\u0010\u0017\u001a\u00020\u0016¢\u0006\u0006\u001a\u0004\b6\u00107\"\u001f\u00108\u001a\u000209*\u00020\u00168BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b<\u0010=\u001a\u0004\b:\u0010;¨\u0006>"}, d2 = {"callableInferenceNodeOf", "Landroidx/compose/compiler/plugins/kotlin/k2/FirInferenceNode;", "expression", "Lorg/jetbrains/kotlin/fir/FirElement;", "callable", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "context", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "parameterInferenceNodeOrNull", "inferenceNodeOf", "element", "toScheme", "Landroidx/compose/compiler/plugins/kotlin/inference/Scheme;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;)Landroidx/compose/compiler/plugins/kotlin/inference/Scheme;", "methodOverrides", "", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFunctionSymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;)Ljava/util/List;", "parameters", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirValueParameterSymbol;", "fileScopeTarget", "Landroidx/compose/compiler/plugins/kotlin/inference/Item;", "Lorg/jetbrains/kotlin/fir/FirSession;", "session", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;)Landroidx/compose/compiler/plugins/kotlin/inference/Item;", "declaredScheme", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;)Landroidx/compose/compiler/plugins/kotlin/inference/Scheme;", "schemeItem", "compositionTarget", "", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;)Ljava/lang/String;", "targetName", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Ljava/lang/String;", "compositionOpenTarget", "", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;)Ljava/lang/Integer;", "annotationArgument", "", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "argumentName", "Lorg/jetbrains/kotlin/name/Name;", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;Lorg/jetbrains/kotlin/name/ClassId;Lorg/jetbrains/kotlin/name/Name;)Ljava/lang/Object;", "Lorg/jetbrains/kotlin/fir/FirAnnotationContainer;", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/FirAnnotationContainer;)Ljava/lang/String;", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/FirAnnotationContainer;Lorg/jetbrains/kotlin/name/ClassId;Lorg/jetbrains/kotlin/name/Name;)Ljava/lang/Object;", "argument", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "name", "updateParents", "", "parent", "getParent", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/FirElement;)Lorg/jetbrains/kotlin/fir/FirElement;", "composableTargetSessionStorage", "Landroidx/compose/compiler/plugins/kotlin/k2/ComposableTargetSessionStorage;", "getComposableTargetSessionStorage", "(Lorg/jetbrains/kotlin/fir/FirSession;)Landroidx/compose/compiler/plugins/kotlin/k2/ComposableTargetSessionStorage;", "composableTargetSessionStorage$delegate", "Lorg/jetbrains/kotlin/util/ArrayMapAccessor;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class ComposableTargetCheckerKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(ComposableTargetCheckerKt.class, "composableTargetSessionStorage", "getComposableTargetSessionStorage(Lorg/jetbrains/kotlin/fir/FirSession;)Landroidx/compose/compiler/plugins/kotlin/k2/ComposableTargetSessionStorage;", 1)};
    private static final ArrayMapAccessor composableTargetSessionStorage$delegate = TypeRegistry.generateAccessor$default(FirSession.Companion, Reflection.getOrCreateKotlinClass(ComposableTargetSessionStorage.class), (Object) null, 2, (Object) null);

    public static final Object annotationArgument(FirSession firSession, FirBasedSymbol<?> firBasedSymbol, ClassId classId, Name name) {
        firSession.getClass();
        firBasedSymbol.getClass();
        classId.getClass();
        name.getClass();
        FirAnnotation annotationByClassId = FirAnnotationUtilsKt.getAnnotationByClassId(firBasedSymbol, classId, firSession);
        if (annotationByClassId != null) {
            return argument(annotationByClassId, name);
        }
        return null;
    }

    public static final Object argument(FirAnnotation firAnnotation, Name name) {
        firAnnotation.getClass();
        name.getClass();
        FirLiteralExpression firLiteralExpression = (FirExpression) firAnnotation.getArgumentMapping().getMapping().get(name);
        if (firLiteralExpression == null || !((ConeBuiltinTypeUtilsKt.isString(FirTypeUtilsKt.getResolvedType(firLiteralExpression)) || ConeBuiltinTypeUtilsKt.isPrimitive(FirTypeUtilsKt.getResolvedType(firLiteralExpression))) && (firLiteralExpression instanceof FirLiteralExpression))) {
            return null;
        }
        return firLiteralExpression.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FirInferenceNode callableInferenceNodeOf(FirElement firElement, FirCallableSymbol<?> firCallableSymbol, CheckerContext checkerContext) {
        FirElement lambdaExpression;
        FirInferenceNode firInferenceNodeParameterInferenceNodeOrNull = parameterInferenceNodeOrNull(firElement, checkerContext);
        if (firInferenceNodeParameterInferenceNodeOrNull != null) {
            return firInferenceNodeParameterInferenceNodeOrNull;
        }
        if ((firElement instanceof FirAnonymousFunction ? (FirAnonymousFunction) firElement : null) == null || (lambdaExpression = getComposableTargetSessionStorage(checkerContext.getSession()).getLambdaExpression((FirAnonymousFunction) firElement)) == null) {
            return (firElement instanceof FirFunctionCall ? (FirFunctionCall) firElement : null) != null ? new FirCallableElementInferenceNode(firCallableSymbol, firCallableSymbol.getFir()) : new FirCallableElementInferenceNode(firCallableSymbol, firElement);
        }
        return inferenceNodeOf(lambdaExpression, checkerContext);
    }

    public static final Integer compositionOpenTarget(FirSession firSession, FirCallableSymbol<?> firCallableSymbol) {
        firSession.getClass();
        firCallableSymbol.getClass();
        Object objAnnotationArgument = annotationArgument(firSession, (FirBasedSymbol<?>) firCallableSymbol, ComposeClassIds.INSTANCE.getComposableOpenTarget(), ComposeFqNames.INSTANCE.getComposableOpenTargetIndexArgument());
        if (objAnnotationArgument instanceof Integer) {
            return (Integer) objAnnotationArgument;
        }
        return null;
    }

    public static final String compositionTarget(FirSession firSession, FirCallableSymbol<?> firCallableSymbol) {
        String strTargetName;
        firSession.getClass();
        firCallableSymbol.getClass();
        Object objAnnotationArgument = annotationArgument(firSession, (FirBasedSymbol<?>) firCallableSymbol, ComposeClassIds.INSTANCE.getComposableTarget(), ComposeFqNames.INSTANCE.getComposableTargetApplierArgument());
        String str = objAnnotationArgument instanceof String ? (String) objAnnotationArgument : null;
        if (str != null) {
            return str;
        }
        if (firCallableSymbol instanceof FirValueParameterSymbol) {
            Iterator it = CustomAnnotationTypeAttributeKt.getTypeAnnotations(((FirValueParameterSymbol) firCallableSymbol).getResolvedReturnType()).iterator();
            do {
                if (!it.hasNext()) {
                    strTargetName = null;
                    break;
                }
                strTargetName = targetName(firSession, FirTypeUtilsKt.getResolvedType((FirAnnotation) it.next()));
            } while (strTargetName == null);
            if (strTargetName != null) {
                return strTargetName;
            }
        }
        Iterator it2 = firCallableSymbol.getAnnotations().iterator();
        while (it2.hasNext()) {
            String strTargetName2 = targetName(firSession, FirTypeUtilsKt.getResolvedType((FirAnnotation) it2.next()));
            if (strTargetName2 != null) {
                return strTargetName2;
            }
        }
        return null;
    }

    public static final Scheme declaredScheme(FirSession firSession, FirCallableSymbol<?> firCallableSymbol) {
        firSession.getClass();
        firCallableSymbol.getClass();
        Object objAnnotationArgument = annotationArgument(firSession, (FirBasedSymbol<?>) firCallableSymbol, ComposeClassIds.INSTANCE.getComposableInferredTarget(), ComposeFqNames.INSTANCE.getComposableInferredTargetSchemeArgument());
        String str = objAnnotationArgument instanceof String ? (String) objAnnotationArgument : null;
        if (str != null) {
            return SchemeKt.deserializeScheme(str);
        }
        return null;
    }

    private static final Item fileScopeTarget(FirSession firSession, FirCallableSymbol<?> firCallableSymbol) {
        return fileScopeTarget$findFileScope(firSession, firCallableSymbol.getFir());
    }

    private static final Item fileScopeTarget$findFileScope(FirSession firSession, FirElement firElement) {
        String strCompositionTarget;
        FirFile firFile = firElement instanceof FirFile ? (FirFile) firElement : null;
        if (firFile != null && (strCompositionTarget = compositionTarget(firSession, (FirAnnotationContainer) firFile)) != null) {
            return new Token(strCompositionTarget);
        }
        FirElement parent = getParent(firSession, firElement);
        if (parent != null) {
            return fileScopeTarget$findFileScope(firSession, parent);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ComposableTargetSessionStorage getComposableTargetSessionStorage(FirSession firSession) {
        return (ComposableTargetSessionStorage) composableTargetSessionStorage$delegate.getValue(firSession, $$delegatedProperties[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FirElement getParent(FirSession firSession, FirElement firElement) {
        return getComposableTargetSessionStorage(firSession).getParent(firElement);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FirInferenceNode inferenceNodeOf(FirElement firElement, CheckerContext checkerContext) {
        return (FirInferenceNode) getComposableTargetSessionStorage(checkerContext.getSession()).getNodeCache().getValue(firElement, checkerContext);
    }

    public static final List<FirFunctionSymbol<?>> methodOverrides(CheckerContext checkerContext, FirCallableSymbol<?> firCallableSymbol) {
        List<FirFunctionSymbol<?>> directOverriddenFunctions;
        checkerContext.getClass();
        firCallableSymbol.getClass();
        FirFunction fir = firCallableSymbol.getFir();
        FirFunction firFunction = fir instanceof FirFunction ? fir : null;
        return (firFunction == null || (directOverriddenFunctions = FirUtilsKt.getDirectOverriddenFunctions(firFunction, checkerContext)) == null) ? CollectionsKt.emptyList() : directOverriddenFunctions;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FirInferenceNode parameterInferenceNodeOrNull(FirElement firElement, CheckerContext checkerContext) {
        if (!(firElement instanceof FirFunctionCall)) {
            return null;
        }
        FirQualifiedAccessExpression explicitReceiver = ((FirFunctionCall) firElement).getExplicitReceiver();
        FirQualifiedAccessExpression firQualifiedAccessExpression = explicitReceiver instanceof FirQualifiedAccessExpression ? explicitReceiver : null;
        if (firQualifiedAccessExpression == null) {
            return null;
        }
        FirValueParameterSymbol resolvedCallableSymbol = ReferenceUtilsKt.toResolvedCallableSymbol(firQualifiedAccessExpression);
        FirValueParameterSymbol firValueParameterSymbol = resolvedCallableSymbol instanceof FirValueParameterSymbol ? resolvedCallableSymbol : null;
        if (firValueParameterSymbol == null) {
            return null;
        }
        FirFunctionSymbol containingDeclarationSymbol = firValueParameterSymbol.getContainingDeclarationSymbol();
        FirFunctionSymbol firFunctionSymbol = containingDeclarationSymbol instanceof FirFunctionSymbol ? containingDeclarationSymbol : null;
        if (firFunctionSymbol == null) {
            return null;
        }
        List valueParameterSymbols = firFunctionSymbol.getValueParameterSymbols();
        ArrayList arrayList = new ArrayList();
        for (Object obj : valueParameterSymbols) {
            if (FirUtilsKt.isComposable((FirValueParameterSymbol) obj, checkerContext)) {
                arrayList.add(obj);
            }
        }
        int iIndexOf = arrayList.indexOf(firValueParameterSymbol);
        if (iIndexOf >= 0) {
            return new FirParameterReferenceNode(firElement, iIndexOf, inferenceNodeOf(firFunctionSymbol.getFir(), checkerContext));
        }
        return null;
    }

    public static final List<FirValueParameterSymbol> parameters(CheckerContext checkerContext, FirCallableSymbol<?> firCallableSymbol) {
        checkerContext.getClass();
        firCallableSymbol.getClass();
        if ((firCallableSymbol instanceof FirFunctionSymbol ? (FirFunctionSymbol) firCallableSymbol : null) == null) {
            return CollectionsKt.emptyList();
        }
        List valueParameterSymbols = ((FirFunctionSymbol) firCallableSymbol).getValueParameterSymbols();
        ArrayList arrayList = new ArrayList();
        for (Object obj : valueParameterSymbols) {
            if (FirUtilsKt.isComposable((FirValueParameterSymbol) obj, checkerContext)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public static final Item schemeItem(FirSession firSession, FirCallableSymbol<?> firCallableSymbol) {
        firSession.getClass();
        firCallableSymbol.getClass();
        String strCompositionTarget = compositionTarget(firSession, firCallableSymbol);
        Integer numCompositionOpenTarget = compositionOpenTarget(firSession, firCallableSymbol);
        if (strCompositionTarget != null) {
            return new Token(strCompositionTarget);
        }
        if (numCompositionOpenTarget == null) {
            return new Open(-1, true);
        }
        return new Open(numCompositionOpenTarget.intValue(), false, 2, null);
    }

    public static final String targetName(FirSession firSession, ConeKotlinType coneKotlinType) {
        firSession.getClass();
        coneKotlinType.getClass();
        FirClassSymbol classSymbol = ToSymbolUtilsKt.toClassSymbol(coneKotlinType, firSession);
        if (classSymbol == null || annotationArgument(firSession, (FirBasedSymbol<?>) classSymbol, ComposeClassIds.INSTANCE.getComposableTargetMarker(), ComposeFqNames.INSTANCE.getComposableTargetMarkerDescriptionName()) == null) {
            return null;
        }
        return classSymbol.getClassId().asFqNameString();
    }

    public static final Scheme toScheme(CheckerContext checkerContext, FirCallableSymbol<?> firCallableSymbol) {
        Item itemFileScopeTarget;
        checkerContext.getClass();
        firCallableSymbol.getClass();
        FirSession session = checkerContext.getSession();
        Scheme schemeDeclaredScheme = declaredScheme(session, firCallableSymbol);
        if (schemeDeclaredScheme != null) {
            return schemeDeclaredScheme;
        }
        Item itemSchemeItem = schemeItem(session, firCallableSymbol);
        Item item = (!itemSchemeItem.getIsUnspecified() || (itemFileScopeTarget = fileScopeTarget(session, firCallableSymbol)) == null) ? itemSchemeItem : itemFileScopeTarget;
        List<FirValueParameterSymbol> listParameters = parameters(checkerContext, firCallableSymbol);
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listParameters, 10));
        Iterator<T> it = listParameters.iterator();
        while (it.hasNext()) {
            arrayList.add(toScheme(checkerContext, (FirValueParameterSymbol) it.next()));
        }
        Scheme scheme = new Scheme(item, arrayList, null, false, 12, null);
        List<FirFunctionSymbol<?>> listMethodOverrides = methodOverrides(checkerContext, firCallableSymbol);
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(listMethodOverrides, 10));
        Iterator<T> it2 = listMethodOverrides.iterator();
        while (it2.hasNext()) {
            arrayList2.add(toScheme(checkerContext, (FirFunctionSymbol) it2.next()));
        }
        return SchemeKt.mergeWith(scheme, arrayList2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void updateParents(CheckerContext checkerContext) {
        FirSession session = checkerContext.getSession();
        List containingElements = checkerContext.getContainingElements();
        IntProgression intProgressionReversed = RangesKt.reversed(RangesKt.until(1, containingElements.size()));
        int first = intProgressionReversed.getFirst();
        int last = intProgressionReversed.getLast();
        int step = intProgressionReversed.getStep();
        if ((step <= 0 || first > last) && (step >= 0 || last > first)) {
            return;
        }
        while (true) {
            FirElement firElement = (FirElement) containingElements.get(first);
            if (getParent(session, firElement) != null) {
                return;
            }
            getComposableTargetSessionStorage(session).storeParent(firElement, (FirElement) containingElements.get(first - 1));
            if (first == last) {
                return;
            } else {
                first += step;
            }
        }
    }

    public static final Object annotationArgument(FirSession firSession, FirAnnotationContainer firAnnotationContainer, ClassId classId, Name name) {
        firSession.getClass();
        firAnnotationContainer.getClass();
        classId.getClass();
        name.getClass();
        FirAnnotation annotationByClassId = FirAnnotationUtilsKt.getAnnotationByClassId(firAnnotationContainer, classId, firSession);
        if (annotationByClassId != null) {
            return argument(annotationByClassId, name);
        }
        return null;
    }

    public static final String compositionTarget(FirSession firSession, FirAnnotationContainer firAnnotationContainer) {
        firSession.getClass();
        firAnnotationContainer.getClass();
        Object objAnnotationArgument = annotationArgument(firSession, firAnnotationContainer, ComposeClassIds.INSTANCE.getComposableTarget(), ComposeFqNames.INSTANCE.getComposableTargetApplierArgument());
        String str = objAnnotationArgument instanceof String ? (String) objAnnotationArgument : null;
        if (str != null) {
            return str;
        }
        Iterator it = firAnnotationContainer.getAnnotations().iterator();
        while (it.hasNext()) {
            String strTargetName = targetName(firSession, FirTypeUtilsKt.getResolvedType((FirAnnotation) it.next()));
            if (strTargetName != null) {
                return strTargetName;
            }
        }
        return null;
    }
}
