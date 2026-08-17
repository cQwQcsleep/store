package javax.tools;

import com.sun.org.apache.bcel.internal.classfile.JavaClass;
import java.util.Objects;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.NestingKind;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface JavaFileObject extends FileObject {

    public enum Kind {
        SOURCE(".java"),
        CLASS(JavaClass.EXTENSION),
        HTML(".html"),
        OTHER("");

        public final String extension;

        Kind(String str) {
            Objects.requireNonNull(str);
            this.extension = str;
        }
    }

    Modifier getAccessLevel();

    Kind getKind();

    NestingKind getNestingKind();

    boolean isNameCompatible(String str, Kind kind);
}
