package org.jetbrains.kotlin.backend.common.serialization;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.ir.IrElement;
import org.jetbrains.kotlin.ir.expressions.IrClassReference;
import org.jetbrains.kotlin.ir.expressions.IrConst;
import org.jetbrains.kotlin.ir.expressions.IrConstructorCall;
import org.jetbrains.kotlin.ir.expressions.IrExpression;
import org.jetbrains.kotlin.ir.expressions.IrGetEnumValue;
import org.jetbrains.kotlin.ir.expressions.IrMemberAccessExpression$ValueArgumentsList;
import org.jetbrains.kotlin.ir.expressions.IrVararg;
import org.jetbrains.kotlin.ir.expressions.IrVarargElement;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000¨\u0006\u0003"}, d2 = {"isValidConstantAnnotationArgument", "", "Lorg/jetbrains/kotlin/ir/IrElement;", "org.jetbrains.kotlin:ir.serialization.common"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class IrFileSerializerKt {
    /* JADX WARN: Code duplicated, block: B:22:0x0045  */
    /* JADX WARN: Code duplicated, block: B:27:0x0054  */
    /* JADX WARN: Code duplicated, block: B:30:0x005e  */
    /* JADX WARN: Code duplicated, block: B:32:0x0066  */
    /* JADX WARN: Code duplicated, block: B:33:0x006b  */
    /* JADX WARN: Code duplicated, block: B:35:0x006e A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Code duplicated, block: B:41:0x006e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:45:? A[LOOP:1: B:28:0x0058->B:45:?, LOOP_END, SYNTHETIC] */
    public static final boolean isValidConstantAnnotationArgument(IrElement irElement) {
        IrMemberAccessExpression$ValueArgumentsList arguments;
        boolean zIsValidConstantAnnotationArgument;
        irElement.getClass();
        if (!(irElement instanceof IrConst) && !(irElement instanceof IrGetEnumValue) && !(irElement instanceof IrClassReference)) {
            if (!(irElement instanceof IrVararg)) {
                if (irElement instanceof IrConstructorCall) {
                    return false;
                }
                arguments = ((IrConstructorCall) irElement).getArguments();
                if (arguments != null) {
                    for (IrExpression irExpression : arguments) {
                        if (irExpression != null) {
                            zIsValidConstantAnnotationArgument = isValidConstantAnnotationArgument(irExpression);
                        } else {
                            zIsValidConstantAnnotationArgument = true;
                        }
                        if (!zIsValidConstantAnnotationArgument) {
                            return false;
                        }
                    }
                    break;
                    break;
                }
                while (r3.hasNext()) {
                    if (irExpression != null) {
                        zIsValidConstantAnnotationArgument = isValidConstantAnnotationArgument(irExpression);
                    } else {
                        zIsValidConstantAnnotationArgument = true;
                    }
                    if (!zIsValidConstantAnnotationArgument) {
                        return false;
                    }
                }
                break;
                break;
            }
            List elements = ((IrVararg) irElement).getElements();
            if (!(elements instanceof Collection) || !elements.isEmpty()) {
                Iterator it = elements.iterator();
                while (it.hasNext()) {
                    if (!isValidConstantAnnotationArgument((IrVarargElement) it.next())) {
                        if (irElement instanceof IrConstructorCall) {
                            return false;
                        }
                        arguments = ((IrConstructorCall) irElement).getArguments();
                        if (arguments != null && arguments.isEmpty()) {
                            break;
                        }
                        while (r3.hasNext()) {
                            if (irExpression != null) {
                                zIsValidConstantAnnotationArgument = isValidConstantAnnotationArgument(irExpression);
                            } else {
                                zIsValidConstantAnnotationArgument = true;
                            }
                            if (!zIsValidConstantAnnotationArgument) {
                                return false;
                            }
                        }
                        break;
                    }
                }
            }
        }
        return true;
    }
}
