package org.jetbrains.kotlin.backend.jvm.codegen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrTypeParameter;
import org.jetbrains.kotlin.ir.expressions.IrAnnotation;
import org.jetbrains.kotlin.ir.expressions.IrExpression;
import org.jetbrains.kotlin.ir.expressions.IrGetEnumValue;
import org.jetbrains.kotlin.ir.expressions.IrVararg;
import org.jetbrains.kotlin.ir.symbols.IrClassifierSymbol;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.types.IrTypePredicatesKt;
import org.jetbrains.kotlin.ir.types.IrTypesKt;
import org.jetbrains.kotlin.ir.util.IrTypeUtilsKt;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;
import org.jetbrains.kotlin.load.java.JvmAnnotationNames;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.StandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0002\u001a\u0014\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005*\u00020\u0007H\u0000¨\u0006\b"}, d2 = {"isBareTypeParameterWithNullableUpperBound", "", "type", "Lorg/jetbrains/kotlin/ir/types/IrType;", "applicableJavaTargetSet", "", "", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "org.jetbrains.kotlin:backend.jvm.codegen"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class AnnotationCodegenKt {
    public static final Set<String> applicableJavaTargetSet(IrClass irClass) {
        irClass.getClass();
        FqName fqName = JvmAnnotationNames.TARGET_ANNOTATION;
        fqName.getClass();
        IrAnnotation annotation = IrUtilsKt.getAnnotation(irClass, fqName);
        IrExpression valueArgument = annotation != null ? IrUtilsKt.getValueArgument(annotation, StandardClassIds.Annotations.ParameterNames.INSTANCE.getValue()) : null;
        IrVararg irVararg = valueArgument instanceof IrVararg ? (IrVararg) valueArgument : null;
        if (irVararg == null) {
            return null;
        }
        List elements = irVararg.getElements();
        ArrayList arrayList = new ArrayList();
        for (Object obj : elements) {
            if (obj instanceof IrGetEnumValue) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList, 10));
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(((IrGetEnumValue) it.next()).getSymbol().getOwner().getName().asString());
        }
        return CollectionsKt.toSet(arrayList2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isBareTypeParameterWithNullableUpperBound(IrType irType) {
        IrClassifierSymbol classifierOrNull = IrTypesKt.getClassifierOrNull(irType);
        return ((classifierOrNull != null ? classifierOrNull.getOwner() : null) instanceof IrTypeParameter) && !IrTypePredicatesKt.isMarkedNullable(irType) && IrTypeUtilsKt.isNullable(irType);
    }
}
