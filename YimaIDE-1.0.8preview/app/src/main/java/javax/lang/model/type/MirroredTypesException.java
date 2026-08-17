package javax.lang.model.type;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class MirroredTypesException extends RuntimeException {
    private static final long serialVersionUID = 269;
    transient List<? extends TypeMirror> types;

    public MirroredTypesException(List<? extends TypeMirror> list) {
        StringBuilder sb = new StringBuilder("Attempt to access Class objects for TypeMirrors ");
        ArrayList arrayList = new ArrayList(list);
        sb.append(arrayList.toString());
        super(sb.toString());
        this.types = Collections.unmodifiableList(arrayList);
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        this.types = null;
    }

    public List<? extends TypeMirror> getTypeMirrors() {
        return this.types;
    }

    public MirroredTypesException(String str, TypeMirror typeMirror) {
        super(str);
        ArrayList arrayList = new ArrayList();
        arrayList.add(typeMirror);
        this.types = Collections.unmodifiableList(arrayList);
    }
}
