package org.jetbrains.kotlin;

import com.intellij.openapi.editor.Document;
import com.intellij.psi.PsiFile;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.KtPsiSourceFileLinesMapping;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0016J\u001c\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0\u00122\u0006\u0010\u0013\u001a\u00020\u000fH\u0016J\u0010\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u000fH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001d\u0010\b\u001a\u0004\u0018\u00010\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0015\u001a\u00020\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0017¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/KtPsiSourceFileLinesMapping;", "Lorg/jetbrains/kotlin/KtSourceFileLinesMapping;", "psiFile", "Lcom/intellij/psi/PsiFile;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lcom/intellij/psi/PsiFile;)V", "getPsiFile", "()Lcom/intellij/psi/PsiFile;", "document", "Lcom/intellij/openapi/editor/Document;", "getDocument", "()Lcom/intellij/openapi/editor/Document;", "document$delegate", "Lkotlin/Lazy;", "getLineStartOffset", "", "line", "getLineAndColumnByOffset", "Lkotlin/Pair;", "offset", "getLineByOffset", "lastOffset", "getLastOffset", "()I", "linesCount", "getLinesCount", "org.jetbrains.kotlin:frontend.common"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class KtPsiSourceFileLinesMapping implements KtSourceFileLinesMapping {

    /* JADX INFO: renamed from: document$delegate, reason: from kotlin metadata */
    private final Lazy document;
    private final PsiFile psiFile;

    public KtPsiSourceFileLinesMapping(PsiFile psiFile) {
        psiFile.getClass();
        this.psiFile = psiFile;
        this.document = LazyKt.lazy(new Function0() { // from class: kg8
            public final Object invoke() {
                return KtPsiSourceFileLinesMapping.a(this.b);
            }
        });
    }

    public static Document a(KtPsiSourceFileLinesMapping ktPsiSourceFileLinesMapping) {
        return ktPsiSourceFileLinesMapping.psiFile.getViewProvider().getDocument();
    }

    private final Document getDocument() {
        return (Document) this.document.getValue();
    }

    @Override // org.jetbrains.kotlin.KtSourceFileLinesMapping
    public int getLastOffset() {
        Document document = getDocument();
        if (document != null) {
            return document.getTextLength();
        }
        return -1;
    }

    @Override // org.jetbrains.kotlin.KtSourceFileLinesMapping
    public Pair<Integer, Integer> getLineAndColumnByOffset(int offset) {
        Document document = getDocument();
        if (document != null) {
            int lineNumber = document.getLineNumber(offset);
            Pair<Integer, Integer> pair = TuplesKt.to(Integer.valueOf(lineNumber), Integer.valueOf(offset - document.getLineStartOffset(lineNumber)));
            if (pair != null) {
                return pair;
            }
        }
        return TuplesKt.to(-1, -1);
    }

    @Override // org.jetbrains.kotlin.KtSourceFileLinesMapping
    public int getLineByOffset(int offset) {
        Document document = getDocument();
        if (document != null) {
            return document.getLineNumber(offset);
        }
        return -1;
    }

    @Override // org.jetbrains.kotlin.KtSourceFileLinesMapping
    public int getLineStartOffset(int line) {
        Document document = getDocument();
        if (document != null) {
            return document.getLineStartOffset(line);
        }
        return -1;
    }

    @Override // org.jetbrains.kotlin.KtSourceFileLinesMapping
    public int getLinesCount() {
        Document document = getDocument();
        if (document != null) {
            return document.getLineCount();
        }
        return 0;
    }

    public final PsiFile getPsiFile() {
        return this.psiFile;
    }
}
