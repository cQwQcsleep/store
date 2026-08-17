package androidx.compose.compiler.plugins.kotlin.k2;

import androidx.compose.compiler.plugins.kotlin.inference.LazyScheme;
import androidx.compose.compiler.plugins.kotlin.k2.ComposableTargetSessionStorage;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.caches.FirCache;
import org.jetbrains.kotlin.fir.caches.FirCachesFactoryKt;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression;
import org.jetbrains.kotlin.fir.expressions.FirFunctionConversionKind;
import org.jetbrains.kotlin.fir.expressions.FirFunctionTypeConversionExpression;
import org.jetbrains.kotlin.fir.extensions.FirExtensionSessionComponent;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0013\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0014\u001a\u00020\bJ\u0016\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\tJ\u0010\u0010\u0018\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0014\u001a\u00020\bJ\u0016\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u0014\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bJ\u0010\u0010\u001a\u001a\u0004\u0018\u00010\b2\u0006\u0010\u001b\u001a\u00020\fJ\u0018\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\u0010H\u0002R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R#\u0010\r\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001e"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/k2/ComposableTargetSessionStorage;", "Lorg/jetbrains/kotlin/fir/extensions/FirExtensionSessionComponent;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "schemes", "Ljava/util/concurrent/ConcurrentHashMap;", "Lorg/jetbrains/kotlin/fir/FirElement;", "Landroidx/compose/compiler/plugins/kotlin/inference/LazyScheme;", "parent", "lambdaToExpression", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "nodeCache", "Lorg/jetbrains/kotlin/fir/caches/FirCache;", "Landroidx/compose/compiler/plugins/kotlin/k2/FirInferenceNode;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "getNodeCache", "()Lorg/jetbrains/kotlin/fir/caches/FirCache;", "getLazyScheme", "element", "storeLazyScheme", "", "value", "getParent", "storeParent", "getLambdaExpression", "function", "inferenceNodeOf", "context", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class ComposableTargetSessionStorage extends FirExtensionSessionComponent {
    private final ConcurrentHashMap<FirAnonymousFunction, FirElement> lambdaToExpression;
    private final FirCache<FirElement, FirInferenceNode, CheckerContext> nodeCache;
    private final ConcurrentHashMap<FirElement, FirElement> parent;
    private final ConcurrentHashMap<FirElement, LazyScheme> schemes;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ComposableTargetSessionStorage(FirSession firSession) {
        super(firSession);
        firSession.getClass();
        this.schemes = new ConcurrentHashMap<>();
        this.parent = new ConcurrentHashMap<>();
        this.lambdaToExpression = new ConcurrentHashMap<>();
        this.nodeCache = FirCachesFactoryKt.getFirCachesFactory(firSession).createCache(new Function2() { // from class: dm2
            public final Object invoke(Object obj, Object obj2) {
                return ComposableTargetSessionStorage.a(this.b, (FirElement) obj, (CheckerContext) obj2);
            }
        });
    }

    public static FirInferenceNode a(ComposableTargetSessionStorage composableTargetSessionStorage, FirElement firElement, CheckerContext checkerContext) {
        firElement.getClass();
        checkerContext.getClass();
        return composableTargetSessionStorage.inferenceNodeOf(firElement, checkerContext);
    }

    private final FirInferenceNode inferenceNodeOf(FirElement element, CheckerContext context) {
        if (element instanceof FirAnonymousFunctionExpression) {
            FirAnonymousFunctionExpression firAnonymousFunctionExpression = (FirAnonymousFunctionExpression) element;
            this.lambdaToExpression.put(firAnonymousFunctionExpression.getAnonymousFunction(), element);
            return new FirLambdaInferenceNode(firAnonymousFunctionExpression);
        }
        if (element instanceof FirFunctionTypeConversionExpression) {
            FirFunctionTypeConversionExpression firFunctionTypeConversionExpression = (FirFunctionTypeConversionExpression) element;
            if (firFunctionTypeConversionExpression.getKind() instanceof FirFunctionConversionKind.Sam) {
                FirAnonymousFunctionExpression expression = firFunctionTypeConversionExpression.getExpression();
                FirAnonymousFunctionExpression firAnonymousFunctionExpression2 = expression instanceof FirAnonymousFunctionExpression ? expression : null;
                if (firAnonymousFunctionExpression2 != null) {
                    this.lambdaToExpression.put(firAnonymousFunctionExpression2.getAnonymousFunction(), element);
                }
                return new FirSamInferenceNode(firFunctionTypeConversionExpression);
            }
        }
        if (element instanceof FirAnonymousFunction) {
            return ComposableTargetCheckerKt.callableInferenceNodeOf(element, ((FirAnonymousFunction) element).getSymbol(), context);
        }
        if (element instanceof FirFunction) {
            return new FirFunctionInferenceNode((FirFunction) element);
        }
        FirInferenceNode firInferenceNodeParameterInferenceNodeOrNull = ComposableTargetCheckerKt.parameterInferenceNodeOrNull(element, context);
        return firInferenceNodeParameterInferenceNodeOrNull == null ? new FirElementInferenceNode(element) : firInferenceNodeParameterInferenceNodeOrNull;
    }

    public final FirElement getLambdaExpression(FirAnonymousFunction function) {
        function.getClass();
        return this.lambdaToExpression.get(function);
    }

    public final LazyScheme getLazyScheme(FirElement element) {
        element.getClass();
        return this.schemes.get(element);
    }

    public final FirCache<FirElement, FirInferenceNode, CheckerContext> getNodeCache() {
        return this.nodeCache;
    }

    public final FirElement getParent(FirElement element) {
        element.getClass();
        return this.parent.get(element);
    }

    public final void storeLazyScheme(FirElement element, LazyScheme value) {
        element.getClass();
        value.getClass();
        this.schemes.put(element, value);
    }

    public final void storeParent(FirElement element, FirElement parent) {
        element.getClass();
        parent.getClass();
        this.parent.put(element, parent);
    }
}
