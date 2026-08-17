package javax.tools;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.NestingKind;
import javax.tools.JavaFileObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ForwardingJavaFileObject<F extends JavaFileObject> extends ForwardingFileObject<F> implements JavaFileObject {
    public ForwardingJavaFileObject(F f) {
        super(f);
    }

    @Override // javax.tools.JavaFileObject
    public Modifier getAccessLevel() {
        return ((JavaFileObject) this.fileObject).getAccessLevel();
    }

    public JavaFileObject.Kind getKind() {
        return ((JavaFileObject) this.fileObject).getKind();
    }

    @Override // javax.tools.JavaFileObject
    public NestingKind getNestingKind() {
        return ((JavaFileObject) this.fileObject).getNestingKind();
    }

    public boolean isNameCompatible(String str, JavaFileObject.Kind kind) {
        return ((JavaFileObject) this.fileObject).isNameCompatible(str, kind);
    }
}
