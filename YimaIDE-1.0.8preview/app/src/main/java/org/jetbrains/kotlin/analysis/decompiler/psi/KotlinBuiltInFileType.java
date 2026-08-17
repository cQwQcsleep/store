package org.jetbrains.kotlin.analysis.decompiler.psi;

import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.vfs.VirtualFile;
import javax.swing.Icon;
import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.idea.KotlinIconProviderService;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0005H\u0016J\b\u0010\u0007\u001a\u00020\u0005H\u0016J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\u000bH\u0016J\u001a\u0010\r\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016R\u000e\u0010\u0012\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/analysis/decompiler/psi/KotlinBuiltInFileType;", "Lcom/intellij/openapi/fileTypes/FileType;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "getName", "", "getDescription", "getDefaultExtension", "getIcon", "Ljavax/swing/Icon;", "isBinary", "", "isReadOnly", "getCharset", "file", "Lcom/intellij/openapi/vfs/VirtualFile;", "content", "", "DEFAULT_DESCRIPTION", "org.jetbrains.kotlin:decompiler-to-psi"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class KotlinBuiltInFileType implements FileType {
    public static final KotlinBuiltInFileType INSTANCE = new KotlinBuiltInFileType();

    private KotlinBuiltInFileType() {
    }

    public String getCharset(VirtualFile file, byte[] content) {
        file.getClass();
        content.getClass();
        return null;
    }

    public String getDefaultExtension() {
        return "kotlin_builtins";
    }

    public String getDescription() {
        String labelForBuiltInFileType;
        KotlinLabelProviderService service = KotlinLabelProviderService.INSTANCE.getService();
        return (service == null || (labelForBuiltInFileType = service.getLabelForBuiltInFileType()) == null) ? "Kotlin built-in declarations" : labelForBuiltInFileType;
    }

    public Icon getIcon() {
        Icon builtInFileIcon = KotlinIconProviderService.getInstance().getBuiltInFileIcon();
        builtInFileIcon.getClass();
        return builtInFileIcon;
    }

    public String getName() {
        return "kotlin_builtins";
    }

    public boolean isBinary() {
        return true;
    }

    public boolean isReadOnly() {
        return true;
    }
}
