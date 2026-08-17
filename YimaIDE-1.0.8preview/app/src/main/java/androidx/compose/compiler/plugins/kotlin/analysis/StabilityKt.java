package androidx.compose.compiler.plugins.kotlin.analysis;

import androidx.compose.compiler.plugins.kotlin.ComposeFqNames;
import androidx.compose.compiler.plugins.kotlin.lower.AbstractComposeLoweringKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.ir.declarations.IrAnnotationContainer;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.expressions.IrAnnotation;
import org.jetbrains.kotlin.ir.expressions.IrConst;
import org.jetbrains.kotlin.ir.expressions.IrConstructorCall;
import org.jetbrains.kotlin.ir.expressions.IrExpression;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.types.IrTypePredicatesKt;
import org.jetbrains.kotlin.ir.types.IrTypesKt;
import org.jetbrains.kotlin.ir.util.AdditionalIrUtilsKt;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0004\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0005\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0006\u001a\u00020\u0002*\u00020\u0002\u001a\u001e\u0010\u0007\u001a\u00020\b*\u00020\u00022\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\b0\n\u001a\n\u0010\u000b\u001a\u00020\u0001*\u00020\f\u001a\f\u0010\r\u001a\u00020\u0001*\u00020\u000eH\u0002\u001a\f\u0010\u000f\u001a\u00020\u0001*\u00020\u0010H\u0002\u001a\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0012*\u00020\fH\u0002¢\u0006\u0002\u0010\u0013¨\u0006\u0014"}, d2 = {"knownUnstable", "", "Landroidx/compose/compiler/plugins/kotlin/analysis/Stability;", "knownStable", "isUncertain", "isExpressible", "normalize", "forEach", "", "callback", "Lkotlin/Function1;", "hasStableMarker", "Lorg/jetbrains/kotlin/ir/declarations/IrAnnotationContainer;", "isStableMarker", "Lorg/jetbrains/kotlin/ir/expressions/IrConstructorCall;", "hasStableMarkedDescendant", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "stabilityParamBitmask", "", "(Lorg/jetbrains/kotlin/ir/declarations/IrAnnotationContainer;)Ljava/lang/Integer;", "org.jetbrains.kotlin:kotlin-compose-compiler-plugin"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class StabilityKt {
    public static final void forEach(Stability stability, Function1<? super Stability, Unit> function1) {
        stability.getClass();
        function1.getClass();
        if (!(stability instanceof Stability.Combined)) {
            function1.invoke(stability);
            return;
        }
        Iterator<T> it = ((Stability.Combined) stability).getElements().iterator();
        while (it.hasNext()) {
            forEach((Stability) it.next(), function1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean hasStableMarkedDescendant(IrClass irClass) {
        IrClassSymbol classOrNull;
        IrClass owner;
        if (hasStableMarker(irClass)) {
            return true;
        }
        List<IrType> superTypes = irClass.getSuperTypes();
        if ((superTypes instanceof Collection) && superTypes.isEmpty()) {
            return false;
        }
        for (IrType irType : superTypes) {
            if (!IrTypePredicatesKt.isAny(irType) && (classOrNull = IrTypesKt.getClassOrNull(irType)) != null && (owner = classOrNull.getOwner()) != null && hasStableMarkedDescendant(owner)) {
                return true;
            }
        }
        return false;
    }

    public static final boolean hasStableMarker(IrAnnotationContainer irAnnotationContainer) {
        irAnnotationContainer.getClass();
        List annotations = irAnnotationContainer.getAnnotations();
        if ((annotations instanceof Collection) && annotations.isEmpty()) {
            return false;
        }
        Iterator it = annotations.iterator();
        while (it.hasNext()) {
            if (isStableMarker((IrAnnotation) it.next())) {
                return true;
            }
        }
        return false;
    }

    public static final boolean isExpressible(Stability stability) {
        stability.getClass();
        if ((stability instanceof Stability.Certain) || (stability instanceof Stability.Runtime)) {
            return true;
        }
        if (stability instanceof Stability.Unknown) {
            return false;
        }
        if (stability instanceof Stability.Parameter) {
            return true;
        }
        if (!(stability instanceof Stability.Combined)) {
            bu8.a();
            return false;
        }
        List<Stability> elements = ((Stability.Combined) stability).getElements();
        if ((elements instanceof Collection) && elements.isEmpty()) {
            return true;
        }
        Iterator<T> it = elements.iterator();
        while (it.hasNext()) {
            if (!isExpressible((Stability) it.next())) {
                return false;
            }
        }
        return true;
    }

    private static final boolean isStableMarker(IrConstructorCall irConstructorCall) {
        IrClass owner;
        IrClassSymbol annotationClass = AbstractComposeLoweringKt.getAnnotationClass(irConstructorCall);
        if (annotationClass == null || (owner = annotationClass.getOwner()) == null) {
            return false;
        }
        return IrUtilsKt.hasAnnotation(owner, ComposeFqNames.INSTANCE.getStableMarker()) || CollectionsKt.contains(KnownStableConstructs.INSTANCE.getStableMarkers(), AdditionalIrUtilsKt.getClassId(owner));
    }

    public static final boolean isUncertain(Stability stability) {
        stability.getClass();
        if (stability instanceof Stability.Certain) {
            return false;
        }
        if ((stability instanceof Stability.Runtime) || (stability instanceof Stability.Unknown) || (stability instanceof Stability.Parameter)) {
            return true;
        }
        if (!(stability instanceof Stability.Combined)) {
            bu8.a();
            return false;
        }
        List<Stability> elements = ((Stability.Combined) stability).getElements();
        if ((elements instanceof Collection) && elements.isEmpty()) {
            return false;
        }
        Iterator<T> it = elements.iterator();
        while (it.hasNext()) {
            if (isUncertain((Stability) it.next())) {
                return true;
            }
        }
        return false;
    }

    public static final boolean knownStable(Stability stability) {
        stability.getClass();
        if (stability instanceof Stability.Certain) {
            return ((Stability.Certain) stability).getStable();
        }
        if ((stability instanceof Stability.Runtime) || (stability instanceof Stability.Unknown) || (stability instanceof Stability.Parameter)) {
            return false;
        }
        if (!(stability instanceof Stability.Combined)) {
            bu8.a();
            return false;
        }
        List<Stability> elements = ((Stability.Combined) stability).getElements();
        if ((elements instanceof Collection) && elements.isEmpty()) {
            return true;
        }
        Iterator<T> it = elements.iterator();
        while (it.hasNext()) {
            if (!knownStable((Stability) it.next())) {
                return false;
            }
        }
        return true;
    }

    public static final boolean knownUnstable(Stability stability) {
        stability.getClass();
        if (stability instanceof Stability.Certain) {
            return !((Stability.Certain) stability).getStable();
        }
        if ((stability instanceof Stability.Runtime) || (stability instanceof Stability.Unknown) || (stability instanceof Stability.Parameter)) {
            return false;
        }
        if (!(stability instanceof Stability.Combined)) {
            bu8.a();
            return false;
        }
        List<Stability> elements = ((Stability.Combined) stability).getElements();
        if ((elements instanceof Collection) && elements.isEmpty()) {
            return false;
        }
        Iterator<T> it = elements.iterator();
        while (it.hasNext()) {
            if (knownUnstable((Stability) it.next())) {
                return true;
            }
        }
        return false;
    }

    public static final Stability normalize(Stability stability) {
        stability.getClass();
        if ((stability instanceof Stability.Certain) || (stability instanceof Stability.Parameter) || (stability instanceof Stability.Runtime) || (stability instanceof Stability.Unknown)) {
            return stability;
        }
        if (!(stability instanceof Stability.Combined)) {
            bu8.a();
            return null;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        ArrayList arrayList = new ArrayList();
        List listMutableListOf = CollectionsKt.mutableListOf(new Stability[]{stability});
        while (!listMutableListOf.isEmpty()) {
            Stability stability2 = (Stability) listMutableListOf.remove(listMutableListOf.size() - 1);
            if (stability2 instanceof Stability.Combined) {
                listMutableListOf.addAll(((Stability.Combined) stability2).getElements());
            } else if (stability2 instanceof Stability.Certain) {
                if (!((Stability.Certain) stability2).getStable()) {
                    return Stability.INSTANCE.getUnstable();
                }
            } else if (stability2 instanceof Stability.Parameter) {
                Stability.Parameter parameter = (Stability.Parameter) stability2;
                if (!linkedHashSet.contains(parameter.getParameter().getSymbol())) {
                    linkedHashSet.add(parameter.getParameter().getSymbol());
                    arrayList.add(stability2);
                }
            } else if (stability2 instanceof Stability.Runtime) {
                arrayList.add(stability2);
            } else if (!(stability2 instanceof Stability.Unknown)) {
                bu8.a();
                return null;
            }
        }
        return new Stability.Combined(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Integer stabilityParamBitmask(IrAnnotationContainer irAnnotationContainer) {
        IrAnnotation irAnnotationFindAnnotation = AdditionalIrUtilsKt.findAnnotation(irAnnotationContainer.getAnnotations(), ComposeFqNames.INSTANCE.getStabilityInferred());
        IrExpression irExpression = irAnnotationFindAnnotation != null ? (IrExpression) irAnnotationFindAnnotation.getArguments().get(0) : null;
        IrConst irConst = irExpression instanceof IrConst ? (IrConst) irExpression : null;
        Object value = irConst != null ? irConst.getValue() : null;
        if (value instanceof Integer) {
            return (Integer) value;
        }
        return null;
    }
}
