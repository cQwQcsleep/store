package org.jetbrains.kotlin.cli.common.messages;

import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.kotlin.diagnostics.DiagnosticUtils;
import org.jetbrains.kotlin.diagnostics.PsiDiagnosticUtils;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public class MessageUtil {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = i != 3 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i != 3 ? 3 : 2];
        if (i == 1) {
            objArr[0] = "range";
        } else if (i == 2) {
            objArr[0] = "virtualFile";
        } else if (i != 3) {
            objArr[0] = "file";
        } else {
            objArr[0] = "org/jetbrains/kotlin/cli/common/messages/MessageUtil";
        }
        if (i != 3) {
            objArr[1] = "org/jetbrains/kotlin/cli/common/messages/MessageUtil";
        } else {
            objArr[1] = "virtualFileToPath";
        }
        if (i == 2) {
            objArr[2] = "virtualFileToPath";
        } else if (i != 3) {
            objArr[2] = "psiFileToMessageLocation";
        }
        String str2 = String.format(str, objArr);
        if (i == 3) {
            throw new IllegalStateException(str2);
        }
    }

    private MessageUtil() {
    }

    public static CompilerMessageLocationWithRange createMessageLocation(String str, String str2, int i, int i2, int i3, int i4) {
        return CompilerMessageLocationWithRange.create(str, i, i2, Integer.valueOf(i3), Integer.valueOf(i4), str2);
    }

    public static CompilerMessageSourceLocation psiElementToMessageLocation(PsiElement psiElement) {
        if (psiElement == null) {
            return null;
        }
        PsiFile containingFile = psiElement.getContainingFile();
        return psiFileToMessageLocation(containingFile, "<no path>", DiagnosticUtils.getLineAndColumnRangeInPsiFile(containingFile, psiElement.getTextRange()));
    }

    public static CompilerMessageSourceLocation psiFileToMessageLocation(PsiFile psiFile, String str, PsiDiagnosticUtils.LineAndColumnRange lineAndColumnRange) {
        if (psiFile == null) {
            $$$reportNull$$$0(0);
        }
        if (lineAndColumnRange == null) {
            $$$reportNull$$$0(1);
        }
        VirtualFile virtualFile = psiFile.getVirtualFile();
        if (virtualFile != null) {
            str = virtualFileToPath(virtualFile);
        }
        String str2 = str;
        PsiDiagnosticUtils.LineAndColumn start = lineAndColumnRange.getStart();
        PsiDiagnosticUtils.LineAndColumn end = lineAndColumnRange.getEnd();
        return createMessageLocation(str2, start.getLineContent(), start.getLine(), start.getColumn(), end.getLine(), end.getColumn());
    }

    public static String virtualFileToPath(VirtualFile virtualFile) {
        if (virtualFile == null) {
            $$$reportNull$$$0(2);
        }
        String systemDependentName = FileUtil.toSystemDependentName(virtualFile.getPath());
        if (systemDependentName == null) {
            $$$reportNull$$$0(3);
        }
        return systemDependentName;
    }
}
