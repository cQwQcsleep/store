package javax.lang.model.element;

import com.intellij.psi.PsiKeyword;
import java.util.Locale;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public enum Modifier {
    PUBLIC,
    PROTECTED,
    PRIVATE,
    ABSTRACT,
    DEFAULT,
    STATIC,
    SEALED,
    NON_SEALED { // from class: javax.lang.model.element.Modifier.1
        @Override // javax.lang.model.element.Modifier, java.lang.Enum
        public String toString() {
            return PsiKeyword.NON_SEALED;
        }
    },
    FINAL,
    TRANSIENT,
    VOLATILE,
    SYNCHRONIZED,
    NATIVE,
    STRICTFP;

    @Override // java.lang.Enum
    public String toString() {
        return name().toLowerCase(Locale.US);
    }
}
