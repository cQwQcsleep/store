package javax.annotation.processing;

import java.util.Locale;
import java.util.Map;
import javax.lang.model.SourceVersion;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface ProcessingEnvironment {
    Elements getElementUtils();

    Filer getFiler();

    Locale getLocale();

    Messager getMessager();

    Map<String, String> getOptions();

    SourceVersion getSourceVersion();

    Types getTypeUtils();

    default boolean isPreviewEnabled() {
        return false;
    }
}
