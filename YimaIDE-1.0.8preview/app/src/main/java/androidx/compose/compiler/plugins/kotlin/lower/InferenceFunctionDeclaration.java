package androidx.compose.compiler.plugins.kotlin.lower;

import androidx.compose.compiler.plugins.kotlin.inference.Item;
import androidx.compose.compiler.plugins.kotlin.inference.Open;
import androidx.compose.compiler.plugins.kotlin.inference.Scheme;
import androidx.compose.compiler.plugins.kotlin.inference.SchemeKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.ir.declarations.IrDeclaration;
import org.jetbrains.kotlin.ir.declarations.IrFunction;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.declarations.IrValueParameter;
import org.jetbrains.kotlin.ir.interpreter.UtilsKt;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010\u0017\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0014\u0010\u001a\u001a\u00020\u0015*\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0016\u0010\u001b\u001a\u0004\u0018\u00010\u0015*\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\n\u0010\u001c\u001a\u00020\u001dH\u0096\u0080\u0004J\u0014\u0010\u001e\u001a\u00020\u000f2\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0096\u0082\u0004J\u000e\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00010\"H\u0002J\f\u0010&\u001a\u00020\u000f*\u00020\u0015H\u0002R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0018\u0010#\u001a\u00020\u000f*\u00020\u00158BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%¨\u0006'"}, d2 = {"Landroidx/compose/compiler/plugins/kotlin/lower/InferenceFunctionDeclaration;", "Landroidx/compose/compiler/plugins/kotlin/lower/InferenceFunction;", "transformer", "Landroidx/compose/compiler/plugins/kotlin/lower/ComposableTargetAnnotationsTransformer;", "function", "Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", "<init>", "(Landroidx/compose/compiler/plugins/kotlin/lower/ComposableTargetAnnotationsTransformer;Lorg/jetbrains/kotlin/ir/declarations/IrFunction;)V", "getFunction", "()Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", "name", "", "getName", "()Ljava/lang/String;", "schemeIsUpdatable", "", "getSchemeIsUpdatable", "()Z", "recordScheme", "", "scheme", "Landroidx/compose/compiler/plugins/kotlin/inference/Scheme;", "updateScheme", "toDeclaredScheme", "defaultTarget", "Landroidx/compose/compiler/plugins/kotlin/inference/Item;", "toScheme", "ancestorScheme", "hashCode", "", "equals", "other", "", "parameters", "", "shouldSerialize", "getShouldSerialize", "(Landroidx/compose/compiler/plugins/kotlin/inference/Scheme;)Z", "allAnonymous", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class InferenceFunctionDeclaration extends InferenceFunction {
    private final IrFunction function;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InferenceFunctionDeclaration(ComposableTargetAnnotationsTransformer composableTargetAnnotationsTransformer, IrFunction irFunction) {
        super(composableTargetAnnotationsTransformer, null);
        composableTargetAnnotationsTransformer.getClass();
        irFunction.getClass();
        this.function = irFunction;
    }

    private final boolean allAnonymous(Scheme scheme) {
        if (!scheme.getTarget().isAnonymous$org_jetbrains_kotlin_kotlin_compose_compiler_plugin()) {
            return false;
        }
        if (scheme.getResult() != null && !allAnonymous(scheme.getResult())) {
            return false;
        }
        List<Scheme> parameters = scheme.getParameters();
        if ((parameters instanceof Collection) && parameters.isEmpty()) {
            return true;
        }
        Iterator<T> it = parameters.iterator();
        while (it.hasNext()) {
            if (!allAnonymous((Scheme) it.next())) {
                return false;
            }
        }
        return true;
    }

    private final Scheme ancestorScheme(IrFunction irFunction, Item item) {
        if (!(irFunction instanceof IrSimpleFunction) || ((IrSimpleFunction) irFunction).getOverriddenSymbols().isEmpty()) {
            return null;
        }
        return toScheme(UtilsKt.getLastOverridden(irFunction), item);
    }

    private final boolean getShouldSerialize(Scheme scheme) {
        return !scheme.getParameters().isEmpty();
    }

    private final List<InferenceFunction> parameters() {
        ComposableTargetAnnotationsTransformer transformer = getTransformer();
        List targetParameters = ComposableTargetAnnotationsTransformerKt.getTargetParameters(this.function);
        ArrayList arrayList = new ArrayList();
        for (Object obj : targetParameters) {
            if (transformer.isOrHasComposableLambda$org_jetbrains_kotlin_kotlin_compose_compiler_plugin(((IrValueParameter) obj).getType())) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(new InferenceFunctionParameter(getTransformer(), (IrValueParameter) it.next()));
        }
        return arrayList2;
    }

    private final Scheme toScheme(IrFunction irFunction, Item item) {
        Item item2;
        Scheme schemeMergeWith;
        ComposableTargetAnnotationsTransformer transformer = getTransformer();
        Item target = transformer.getTarget(this.function.getAnnotations());
        if (target.getIsUnspecified() && IrUtilsKt.getFileOrNull(this.function) == null) {
            item2 = item;
        } else {
            if (target.getIsUnspecified()) {
                target = transformer.getTarget(IrUtilsKt.getFile(this.function).getAnnotations());
            }
            item2 = target;
        }
        Item open = this.function.getBody() == null ? item : new Open(-1, true);
        IrType returnType = this.function.getReturnType();
        Scheme scheme = transformer.isOrHasComposableLambda$org_jetbrains_kotlin_kotlin_compose_compiler_plugin(returnType) ? transformer.toScheme(returnType, open) : null;
        List<InferenceFunction> listParameters = parameters();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listParameters, 10));
        Iterator<T> it = listParameters.iterator();
        while (it.hasNext()) {
            arrayList.add(((InferenceFunction) it.next()).toDeclaredScheme(open));
        }
        Scheme scheme2 = new Scheme(item2, arrayList, scheme, false, 8, null);
        Scheme schemeAncestorScheme = ancestorScheme(irFunction, item);
        return (schemeAncestorScheme == null || (schemeMergeWith = SchemeKt.mergeWith(scheme2, CollectionsKt.listOf(schemeAncestorScheme))) == null) ? scheme2 : schemeMergeWith;
    }

    public boolean equals(Object other) {
        return (other instanceof InferenceFunctionDeclaration) && Intrinsics.areEqual(((InferenceFunctionDeclaration) other).function, this.function);
    }

    public final IrFunction getFunction() {
        return this.function;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.InferenceFunction
    public String getName() {
        String string = this.function.getName().toString();
        string.getClass();
        return string;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.InferenceFunction
    public boolean getSchemeIsUpdatable() {
        return true;
    }

    public int hashCode() {
        return this.function.hashCode() * 31;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.InferenceFunction
    public void recordScheme(Scheme scheme) {
        scheme.getClass();
        if (allAnonymous(scheme)) {
            return;
        }
        getTransformer().metricsFor(this.function).recordScheme(scheme.toString());
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.InferenceFunction
    public Scheme toDeclaredScheme(Item defaultTarget) {
        defaultTarget.getClass();
        Scheme scheme = getTransformer().getScheme(this.function);
        return scheme == null ? toScheme(this.function, defaultTarget) : scheme;
    }

    @Override // androidx.compose.compiler.plugins.kotlin.lower.InferenceFunction
    public void updateScheme(Scheme scheme) {
        scheme.getClass();
        if (getShouldSerialize(scheme)) {
            getTransformer().addAnnotationToDeclaration((IrDeclaration) this.function, scheme);
            return;
        }
        getTransformer().addAnnotationToDeclaration((IrDeclaration) this.function, scheme.getTarget());
        List<InferenceFunction> listParameters = parameters();
        List<Scheme> parameters = scheme.getParameters();
        Iterator<T> it = listParameters.iterator();
        Iterator<T> it2 = parameters.iterator();
        ArrayList arrayList = new ArrayList(Math.min(CollectionsKt.collectionSizeOrDefault(listParameters, 10), CollectionsKt.collectionSizeOrDefault(parameters, 10)));
        while (it.hasNext() && it2.hasNext()) {
            ((InferenceFunction) it.next()).updateScheme((Scheme) it2.next());
            arrayList.add(Unit.INSTANCE);
        }
    }
}
