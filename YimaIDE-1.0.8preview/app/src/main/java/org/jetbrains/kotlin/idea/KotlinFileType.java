package org.jetbrains.kotlin.idea;

import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.util.NotNullLazyValue;
import java.util.function.Supplier;
import javax.swing.Icon;
import org.jetbrains.kotlin.idea.KotlinIconProviderService;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public class KotlinFileType extends LanguageFileType {
    public static final String DOT_DEFAULT_EXTENSION = ".kt";
    public static final String DOT_SCRIPT_EXTENSION = ".kts";
    public static final String EXTENSION = "kt";
    public static final KotlinFileType INSTANCE = new KotlinFileType();
    public static final String SCRIPT_EXTENSION = "kts";
    private final NotNullLazyValue<Icon> myIcon;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "org/jetbrains/kotlin/idea/KotlinFileType", "getDescription"));
    }

    private KotlinFileType() {
        super(KotlinLanguage.INSTANCE);
        this.myIcon = NotNullLazyValue.lazy(new Supplier() { // from class: wb8
            @Override // java.util.function.Supplier
            public final Object get() {
                return KotlinIconProviderService.getInstance().getFileIcon();
            }
        });
    }

    public String getDefaultExtension() {
        return EXTENSION;
    }

    public String getDescription() {
        String name = getName();
        if (name == null) {
            $$$reportNull$$$0(0);
        }
        return name;
    }

    public Icon getIcon() {
        return (Icon) this.myIcon.getValue();
    }

    public String getName() {
        return KotlinLanguage.NAME;
    }
}
