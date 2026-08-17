package org.jetbrains.kotlin.ir;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.util.PsiTreeUtil;
import kotlin.Metadata;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.reflect.KClass;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0018\u001a\u00020\tH\u0002J\n\u0010\u001c\u001a\u00020\tH\u0096\u0080\u0004J\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u001f\u001a\u00020 J-\u0010\u001d\u001a\u0004\u0018\u0001H!\"\b\b\u0000\u0010!*\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\f\u0010\"\u001a\b\u0012\u0004\u0012\u0002H!0#¢\u0006\u0002\u0010$R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0018\u0010\b\u001a\f0\t¢\u0006\u0002\b\n¢\u0006\u0002\b\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\rX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u0011X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\rX\u0096D¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000fR\u0013\u0010\u0016\u001a\u00070\u0017¢\u0006\u0002\b\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0019\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001b¨\u0006%"}, d2 = {"Lorg/jetbrains/kotlin/ir/PsiIrFileEntry;", "Lorg/jetbrains/kotlin/ir/AbstractIrFileEntry;", "psiFile", "Lcom/intellij/psi/PsiFile;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lcom/intellij/psi/PsiFile;)V", "getPsiFile", "()Lcom/intellij/psi/PsiFile;", "psiFileName", "", "Lorg/jetbrains/annotations/NonNls;", "Lorg/jetbrains/annotations/NotNull;", "maxOffset", "", "getMaxOffset", "()I", "lineStartOffsets", "", "getLineStartOffsets", "()[I", "firstRelevantLineIndex", "getFirstRelevantLineIndex", "fileViewProvider", "Lcom/intellij/psi/FileViewProvider;", "getRecognizableName", "name", "getName", "()Ljava/lang/String;", "toString", "findPsiElement", "Lcom/intellij/psi/PsiElement;", "irElement", "Lorg/jetbrains/kotlin/ir/IrElement;", "E", "psiElementClass", "Lkotlin/reflect/KClass;", "(Lorg/jetbrains/kotlin/ir/IrElement;Lkotlin/reflect/KClass;)Lcom/intellij/psi/PsiElement;", "org.jetbrains.kotlin:ir.tree"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class PsiIrFileEntry extends AbstractIrFileEntry {
    private final FileViewProvider fileViewProvider;
    private final int firstRelevantLineIndex;
    private final int[] lineStartOffsets;
    private final int maxOffset;
    private final PsiFile psiFile;
    private final String psiFileName;

    public PsiIrFileEntry(PsiFile psiFile) {
        String name;
        psiFile.getClass();
        this.psiFile = psiFile;
        VirtualFile virtualFile = psiFile.getVirtualFile();
        if (virtualFile == null || (name = virtualFile.getPath()) == null) {
            name = psiFile.getName();
            name.getClass();
        }
        this.psiFileName = name;
        FileViewProvider viewProvider = psiFile.getViewProvider();
        viewProvider.getClass();
        this.fileViewProvider = viewProvider;
        Document document = viewProvider.getDocument();
        if (document == null) {
            s22.a("No document for ", psiFile);
            throw null;
        }
        this.maxOffset = document.getTextLength();
        Integer numValueOf = Integer.valueOf(document.getLineCount());
        Integer num = numValueOf.intValue() != 0 ? numValueOf : null;
        int iIntValue = num != null ? num.intValue() : 1;
        int[] iArr = new int[iIntValue];
        for (int i = 0; i < iIntValue; i++) {
            iArr[i] = document.getLineStartOffset(i);
        }
        this.lineStartOffsets = iArr;
    }

    /* JADX INFO: renamed from: getRecognizableName, reason: from getter */
    private final String getPsiFileName() {
        return this.psiFileName;
    }

    public final PsiElement findPsiElement(IrElement irElement) {
        irElement.getClass();
        PsiElement psiElementFindElementAt = this.fileViewProvider.findElementAt(irElement.getStartOffset());
        while (psiElementFindElementAt != null) {
            TextRange textRange = psiElementFindElementAt.getTextRange();
            if (textRange != null && irElement.getEndOffset() == textRange.getEndOffset()) {
                return psiElementFindElementAt;
            }
            psiElementFindElementAt = psiElementFindElementAt.getParent();
        }
        return psiElementFindElementAt;
    }

    @Override // org.jetbrains.kotlin.ir.IrFileEntry
    public int getFirstRelevantLineIndex() {
        return this.firstRelevantLineIndex;
    }

    @Override // org.jetbrains.kotlin.ir.IrFileEntry
    public int[] getLineStartOffsets() {
        return this.lineStartOffsets;
    }

    @Override // org.jetbrains.kotlin.ir.IrFileEntry
    public int getMaxOffset() {
        return this.maxOffset;
    }

    @Override // org.jetbrains.kotlin.ir.IrFileEntry
    public String getName() {
        return getPsiFileName();
    }

    public final PsiFile getPsiFile() {
        return this.psiFile;
    }

    public String toString() {
        return getPsiFileName();
    }

    public final <E extends PsiElement> E findPsiElement(IrElement irElement, KClass<E> psiElementClass) {
        irElement.getClass();
        psiElementClass.getClass();
        PsiElement psiElementFindPsiElement = findPsiElement(irElement);
        if (psiElementFindPsiElement != null) {
            return (E) PsiTreeUtil.getParentOfType(psiElementFindPsiElement, JvmClassMappingKt.getJavaClass(psiElementClass), false);
        }
        return null;
    }
}
