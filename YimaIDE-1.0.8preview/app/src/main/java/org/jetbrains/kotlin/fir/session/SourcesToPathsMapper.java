package org.jetbrains.kotlin.fir.session;

import com.intellij.lang.LighterASTNode;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.kotlin.KtPsiSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSessionComponent;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0007J\u0010\u0010\r\u001a\u0004\u0018\u00010\u00072\u0006\u0010\n\u001a\u00020\u000bR\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/session/SourcesToPathsMapper;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "<init>", "()V", "sourcesToPath", Argument.Delimiters.none, "Lcom/intellij/lang/LighterASTNode;", Argument.Delimiters.none, "registerFileSource", Argument.Delimiters.none, "sourceElement", "Lorg/jetbrains/kotlin/KtSourceElement;", ModuleXmlParser.PATH, "getSourceFilePath", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SourcesToPathsMapper implements FirSessionComponent {
    private final Map<LighterASTNode, String> sourcesToPath = new LinkedHashMap();

    public final String getSourceFilePath(KtSourceElement sourceElement) {
        VirtualFile virtualFile;
        sourceElement.getClass();
        PsiElement psi = KtSourceElementKt.getPsi(sourceElement);
        if (psi == null) {
            return this.sourcesToPath.get(sourceElement.getTreeStructure().getRoot());
        }
        PsiFile containingFile = psi.getContainingFile();
        if (containingFile == null || (virtualFile = containingFile.getVirtualFile()) == null) {
            return null;
        }
        return virtualFile.getPath();
    }

    public final void registerFileSource(KtSourceElement sourceElement, String path) {
        sourceElement.getClass();
        path.getClass();
        if (sourceElement instanceof KtPsiSourceElement) {
            return;
        }
        this.sourcesToPath.put((LighterASTNode) sourceElement.getTreeStructure().getRoot(), path);
    }
}
