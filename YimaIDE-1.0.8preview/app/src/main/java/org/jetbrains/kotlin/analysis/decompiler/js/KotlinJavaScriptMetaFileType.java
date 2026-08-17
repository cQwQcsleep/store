package org.jetbrains.kotlin.analysis.decompiler.js;

import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.vfs.VirtualFile;
import kotlin.Metadata;
import org.jetbrains.kotlin.analysis.decompiler.psi.KotlinLabelProviderService;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0005H\u0016J\b\u0010\u0007\u001a\u00020\u0005H\u0016J\n\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\u000bH\u0016J\u001a\u0010\r\u001a\u0004\u0018\u00010\t2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/analysis/decompiler/js/KotlinJavaScriptMetaFileType;", "Lcom/intellij/openapi/fileTypes/FileType;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "getName", "", "getDescription", "getDefaultExtension", "getIcon", "", "isBinary", "", "isReadOnly", "getCharset", "file", "Lcom/intellij/openapi/vfs/VirtualFile;", "content", "", "org.jetbrains.kotlin:decompiler-js"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class KotlinJavaScriptMetaFileType implements FileType {
    public static final KotlinJavaScriptMetaFileType INSTANCE = new KotlinJavaScriptMetaFileType();

    private KotlinJavaScriptMetaFileType() {
    }

    /* JADX INFO: renamed from: getCharset, reason: collision with other method in class */
    public Void m39getCharset(VirtualFile file, byte[] content) {
        file.getClass();
        content.getClass();
        return null;
    }

    public String getDefaultExtension() {
        return "kjsm";
    }

    public String getDescription() {
        String labelForKotlinJavaScriptMetaFileType;
        KotlinLabelProviderService service = KotlinLabelProviderService.INSTANCE.getService();
        return (service == null || (labelForKotlinJavaScriptMetaFileType = service.getLabelForKotlinJavaScriptMetaFileType()) == null) ? "Kotlin JavaScript meta file" : labelForKotlinJavaScriptMetaFileType;
    }

    public String getName() {
        return "KJSM";
    }

    public boolean isBinary() {
        return true;
    }

    public boolean isReadOnly() {
        return true;
    }

    /* JADX INFO: renamed from: getIcon, reason: merged with bridge method [inline-methods] */
    public Void m40getIcon() {
        return null;
    }

    public /* bridge */ /* synthetic */ String getCharset(VirtualFile virtualFile, byte[] bArr) {
        return (String) m39getCharset(virtualFile, bArr);
    }
}
