package org.jetbrains.kotlin.ir.util;

import kotlin.Metadata;
import org.jetbrains.kotlin.descriptors.InlineClassRepresentation;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationsKt;
import org.jetbrains.kotlin.ir.declarations.IrField;
import org.jetbrains.kotlin.ir.declarations.IrProperty;
import org.jetbrains.kotlin.ir.types.IrSimpleType;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u0003¨\u0006\u0006"}, d2 = {"getInlineClassUnderlyingType", "Lorg/jetbrains/kotlin/ir/types/IrSimpleType;", "irClass", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "getInlineClassBackingField", "Lorg/jetbrains/kotlin/ir/declarations/IrField;", "org.jetbrains.kotlin:ir.tree"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class InlineClassesKt {
    public static final IrField getInlineClassBackingField(IrClass irClass) {
        IrField backingField;
        irClass.getClass();
        for (IrField irField : irClass.getDeclarations()) {
            if (irField instanceof IrField) {
                IrField irField2 = irField;
                if (!irField2.isStatic()) {
                    return irField2;
                }
            }
            if ((irField instanceof IrProperty) && (backingField = ((IrProperty) irField).getBackingField()) != null && !backingField.isStatic()) {
                return backingField;
            }
        }
        f2f.a("Inline class has no field: ", IrUtilsKt.getFqNameWhenAvailable(irClass));
        return null;
    }

    public static final IrSimpleType getInlineClassUnderlyingType(IrClass irClass) {
        irClass.getClass();
        InlineClassRepresentation inlineClassRepresentation = IrDeclarationsKt.getInlineClassRepresentation(irClass);
        if (inlineClassRepresentation != null) {
            return inlineClassRepresentation.getUnderlyingType();
        }
        f2f.a("Not an inline class: ", RenderIrElementKt.render$default(irClass, (DumpIrTreeOptions) null, 1, (Object) null));
        return null;
    }
}
