package org.jetbrains.kotlin.idea;

import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.util.NotNullLazyValue;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.function.Supplier;
import javax.swing.Icon;
import org.jetbrains.kotlin.idea.KotlinIconProviderService;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public class KotlinModuleFileType implements FileType {
    public static final String EXTENSION = "kotlin_module";
    public static final KotlinModuleFileType INSTANCE = new KotlinModuleFileType();
    private final NotNullLazyValue<Icon> myIcon = NotNullLazyValue.lazy(new Supplier() { // from class: wc8
        @Override // java.util.function.Supplier
        public final Object get() {
            return KotlinIconProviderService.getInstance().getFileIcon();
        }
    });

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        if (i != 1) {
            objArr[0] = "file";
        } else {
            objArr[0] = "content";
        }
        objArr[1] = "org/jetbrains/kotlin/idea/KotlinModuleFileType";
        objArr[2] = "getCharset";
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    private KotlinModuleFileType() {
    }

    public String getCharset(VirtualFile virtualFile, byte[] bArr) {
        if (virtualFile == null) {
            $$$reportNull$$$0(0);
        }
        if (bArr != null) {
            return null;
        }
        $$$reportNull$$$0(1);
        return null;
    }

    public String getDefaultExtension() {
        return "kotlin_module";
    }

    public String getDescription() {
        return "Kotlin module info: contains package part mappings";
    }

    public Icon getIcon() {
        return (Icon) this.myIcon.getValue();
    }

    public String getName() {
        return "kotlin_module";
    }

    public boolean isBinary() {
        return true;
    }

    public boolean isReadOnly() {
        return true;
    }
}
