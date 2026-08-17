package org.jetbrains.kotlin.fir.backend.generators;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.backend.Fir2IrComponents;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.lazy.Fir2IrLazyClass;
import org.jetbrains.kotlin.ir.IrElement;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrDeclaration;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationParent;
import org.jetbrains.kotlin.ir.declarations.IrExternalPackageFragment;
import org.jetbrains.kotlin.ir.declarations.IrFile;
import org.jetbrains.kotlin.ir.declarations.IrPossiblyExternalDeclaration;
import org.jetbrains.kotlin.ir.declarations.IrReplSnippet;
import org.jetbrains.kotlin.ir.declarations.IrScript;
import org.jetbrains.kotlin.ir.util.DumpIrTreeOptions;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;
import org.jetbrains.kotlin.ir.util.RenderIrElementKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0000\u001a\u001a\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0000\u001a\u001f\u0010\u0007\u001a\u00020\b*\u0004\u0018\u00010\u0004H\u0000\u0082\u0002\u000e\n\f\b\u0000\u0012\u0002\u0018\u0000\u001a\u0004\b\u0003\u0010\u0000\u001a\u0016\u0010\t\u001a\u00020\b*\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH\u0000\u001a\u001c\u0010\r\u001a\u00020\b2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0000¨\u0006\u0010"}, d2 = {"setParent", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/ir/declarations/IrDeclaration;", "irParent", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationParent;", "addDeclarationToParent", "declaration", "isExternalParent", Argument.Delimiters.none, "shouldParametersBeAssignable", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "c", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "isEffectivelyExternal", "memberDeclaration", "Lorg/jetbrains/kotlin/fir/declarations/FirMemberDeclaration;", "org.jetbrains.kotlin:fir2ir"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class Fir2IrCallableDeclarationsGeneratorKt {
    public static final void addDeclarationToParent(IrDeclaration irDeclaration, IrDeclarationParent irDeclarationParent) {
        irDeclaration.getClass();
        if (irDeclarationParent == null || (irDeclarationParent instanceof Fir2IrLazyClass)) {
            return;
        }
        if (irDeclarationParent instanceof IrClass) {
            ((IrClass) irDeclarationParent).getDeclarations().add(irDeclaration);
            return;
        }
        if (irDeclarationParent instanceof IrFile) {
            ((IrFile) irDeclarationParent).getDeclarations().add(irDeclaration);
            return;
        }
        if (irDeclarationParent instanceof IrExternalPackageFragment) {
            ((IrExternalPackageFragment) irDeclarationParent).getDeclarations().add(irDeclaration);
        } else {
            if ((irDeclarationParent instanceof IrScript) || (irDeclarationParent instanceof IrReplSnippet)) {
                return;
            }
            s0g.a("Can't add declaration ", RenderIrElementKt.render$default((IrElement) irDeclaration, (DumpIrTreeOptions) null, 1, (Object) null), " to parent ", RenderIrElementKt.render$default(irDeclarationParent, (DumpIrTreeOptions) null, 1, (Object) null));
        }
    }

    public static final boolean isEffectivelyExternal(FirMemberDeclaration firMemberDeclaration, IrDeclarationParent irDeclarationParent) {
        if (firMemberDeclaration == null || !firMemberDeclaration.getStatus().isExternal()) {
            IrPossiblyExternalDeclaration irPossiblyExternalDeclaration = irDeclarationParent instanceof IrPossiblyExternalDeclaration ? (IrPossiblyExternalDeclaration) irDeclarationParent : null;
            if (irPossiblyExternalDeclaration == null || !irPossiblyExternalDeclaration.isExternal()) {
                return false;
            }
        }
        return true;
    }

    public static final boolean isExternalParent(IrDeclarationParent irDeclarationParent) {
        if ((irDeclarationParent instanceof Fir2IrLazyClass) || (irDeclarationParent instanceof IrExternalPackageFragment)) {
            return true;
        }
        return (irDeclarationParent instanceof IrDeclaration) && IrUtilsKt.isFileClass((IrDeclaration) irDeclarationParent);
    }

    public static final void setParent(IrDeclaration irDeclaration, IrDeclarationParent irDeclarationParent) {
        irDeclaration.getClass();
        if (irDeclarationParent != null) {
            irDeclaration.setParent(irDeclarationParent);
        }
    }

    public static final boolean shouldParametersBeAssignable(FirCallableDeclaration firCallableDeclaration, Fir2IrComponents fir2IrComponents) {
        fir2IrComponents.getClass();
        return fir2IrComponents.getExtensions().getParametersAreAssignable() && firCallableDeclaration != null && firCallableDeclaration.getStatus().isTailRec();
    }
}
