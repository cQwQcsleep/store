package org.jetbrains.kotlin.ir.overrides;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.descriptors.DescriptorVisibilities;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationWithVisibility;
import org.jetbrains.kotlin.ir.declarations.IrOverridableDeclaration;
import org.jetbrains.kotlin.ir.symbols.IrSymbol;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\n\u0010\u0004\u001a\u00020\u0001*\u00020\u0002\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0003¨\u0006\u0005"}, d2 = {"isNonPrivate", "", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationWithVisibility;", "(Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationWithVisibility;)Z", "isEffectivelyPrivate", "org.jetbrains.kotlin:ir.tree"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class IrVisibilityUtilKt {
    /* JADX WARN: Code duplicated, block: B:36:0x0070  */
    /* JADX WARN: Code duplicated, block: B:37:0x0075  */
    /* JADX WARN: Code duplicated, block: B:40:0x0079 A[RETURN] */
    public static final boolean isEffectivelyPrivate(IrDeclarationWithVisibility irDeclarationWithVisibility) {
        IrClass parentClassOrNull;
        boolean zIsEffectivelyPrivate;
        List overriddenSymbols;
        irDeclarationWithVisibility.getClass();
        if (isNonPrivate(irDeclarationWithVisibility)) {
            IrClass parentClassOrNull2 = IrUtilsKt.getParentClassOrNull(irDeclarationWithVisibility);
            if (parentClassOrNull2 != null) {
                return isEffectivelyPrivate(parentClassOrNull2);
            }
            return false;
        }
        if (!Intrinsics.areEqual(irDeclarationWithVisibility.getVisibility(), DescriptorVisibilities.INVISIBLE_FAKE)) {
            return true;
        }
        IrOverridableDeclaration irOverridableDeclaration = irDeclarationWithVisibility instanceof IrOverridableDeclaration ? (IrOverridableDeclaration) irDeclarationWithVisibility : null;
        if (irOverridableDeclaration == null || (overriddenSymbols = irOverridableDeclaration.getOverriddenSymbols()) == null) {
            parentClassOrNull = IrUtilsKt.getParentClassOrNull(irDeclarationWithVisibility);
            if (parentClassOrNull != null) {
                zIsEffectivelyPrivate = isEffectivelyPrivate(parentClassOrNull);
            } else {
                zIsEffectivelyPrivate = false;
            }
            if (!zIsEffectivelyPrivate) {
                return false;
            }
        } else {
            List list = overriddenSymbols;
            if (!(list instanceof Collection) || !list.isEmpty()) {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    IrDeclarationWithVisibility owner = ((IrSymbol) it.next()).getOwner();
                    IrDeclarationWithVisibility irDeclarationWithVisibility2 = owner instanceof IrDeclarationWithVisibility ? owner : null;
                    if (irDeclarationWithVisibility2 == null || !isEffectivelyPrivate(irDeclarationWithVisibility2)) {
                        parentClassOrNull = IrUtilsKt.getParentClassOrNull(irDeclarationWithVisibility);
                        if (parentClassOrNull != null) {
                            zIsEffectivelyPrivate = isEffectivelyPrivate(parentClassOrNull);
                        } else {
                            zIsEffectivelyPrivate = false;
                        }
                        if (!zIsEffectivelyPrivate) {
                            break;
                        }
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static final boolean isNonPrivate(IrDeclarationWithVisibility irDeclarationWithVisibility) {
        irDeclarationWithVisibility.getClass();
        return Intrinsics.areEqual(irDeclarationWithVisibility.getVisibility(), DescriptorVisibilities.PUBLIC) || Intrinsics.areEqual(irDeclarationWithVisibility.getVisibility(), DescriptorVisibilities.PROTECTED) || Intrinsics.areEqual(irDeclarationWithVisibility.getVisibility(), DescriptorVisibilities.INTERNAL);
    }
}
