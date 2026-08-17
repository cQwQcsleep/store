package org.jetbrains.kotlin.ir;

import com.intellij.psi.PsiElement;
import kotlin.Metadata;
import kotlin.reflect.KClass;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.declarations.IrDeclaration;
import org.jetbrains.kotlin.ir.declarations.IrFile;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J5\u0010\u0004\u001a\u0004\u0018\u0001H\u0005\"\b\b\u0000\u0010\u0005*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00050\f¢\u0006\u0002\u0010\rJ\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ5\u0010\u0004\u001a\u0004\u0018\u0001H\u0005\"\b\b\u0000\u0010\u0005*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00050\f¢\u0006\u0002\u0010\u0010J\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000fJ-\u0010\u0004\u001a\u0004\u0018\u0001H\u0005\"\b\b\u0000\u0010\u0005*\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00050\f¢\u0006\u0002\u0010\u0011J\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u000e\u001a\u00020\u000f¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/ir/PsiSourceManager;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "findPsiElement", "E", "Lcom/intellij/psi/PsiElement;", "irElement", "Lorg/jetbrains/kotlin/ir/IrElement;", "irFile", "Lorg/jetbrains/kotlin/ir/declarations/IrFile;", "psiElementClass", "Lkotlin/reflect/KClass;", "(Lorg/jetbrains/kotlin/ir/IrElement;Lorg/jetbrains/kotlin/ir/declarations/IrFile;Lkotlin/reflect/KClass;)Lcom/intellij/psi/PsiElement;", "irDeclaration", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclaration;", "(Lorg/jetbrains/kotlin/ir/IrElement;Lorg/jetbrains/kotlin/ir/declarations/IrDeclaration;Lkotlin/reflect/KClass;)Lcom/intellij/psi/PsiElement;", "(Lorg/jetbrains/kotlin/ir/declarations/IrDeclaration;Lkotlin/reflect/KClass;)Lcom/intellij/psi/PsiElement;", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class PsiSourceManager {
    public static final PsiSourceManager INSTANCE = new PsiSourceManager();

    private PsiSourceManager() {
    }

    public final <E extends PsiElement> E findPsiElement(IrElement irElement, IrFile irFile, KClass<E> psiElementClass) {
        irElement.getClass();
        irFile.getClass();
        psiElementClass.getClass();
        IrFileEntry fileEntry = irFile.getFileEntry();
        PsiIrFileEntry psiIrFileEntry = fileEntry instanceof PsiIrFileEntry ? (PsiIrFileEntry) fileEntry : null;
        if (psiIrFileEntry == null) {
            return null;
        }
        return (E) psiIrFileEntry.findPsiElement(irElement, psiElementClass);
    }

    public final PsiElement findPsiElement(IrElement irElement, IrFile irFile) {
        irElement.getClass();
        irFile.getClass();
        IrFileEntry fileEntry = irFile.getFileEntry();
        PsiIrFileEntry psiIrFileEntry = fileEntry instanceof PsiIrFileEntry ? (PsiIrFileEntry) fileEntry : null;
        if (psiIrFileEntry == null) {
            return null;
        }
        return psiIrFileEntry.findPsiElement(irElement);
    }

    public final <E extends PsiElement> E findPsiElement(IrElement irElement, IrDeclaration irDeclaration, KClass<E> psiElementClass) {
        irElement.getClass();
        irDeclaration.getClass();
        psiElementClass.getClass();
        IrFile fileOrNull = IrUtilsKt.getFileOrNull(irDeclaration);
        if (fileOrNull == null) {
            return null;
        }
        return (E) findPsiElement(irElement, fileOrNull, psiElementClass);
    }

    public final PsiElement findPsiElement(IrElement irElement, IrDeclaration irDeclaration) {
        irElement.getClass();
        irDeclaration.getClass();
        IrFile fileOrNull = IrUtilsKt.getFileOrNull(irDeclaration);
        if (fileOrNull == null) {
            return null;
        }
        return findPsiElement(irElement, fileOrNull);
    }

    public final <E extends PsiElement> E findPsiElement(IrDeclaration irDeclaration, KClass<E> psiElementClass) {
        irDeclaration.getClass();
        psiElementClass.getClass();
        return (E) findPsiElement((IrElement) irDeclaration, irDeclaration, psiElementClass);
    }

    public final PsiElement findPsiElement(IrDeclaration irDeclaration) {
        irDeclaration.getClass();
        return findPsiElement((IrElement) irDeclaration, irDeclaration);
    }
}
