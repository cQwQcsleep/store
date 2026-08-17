package defpackage;

import com.sun.tools.javac.jvm.ClassReader;
import com.sun.tools.javac.util.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final /* synthetic */ class ww1 implements Name.NameMapper {
    public final /* synthetic */ ClassReader a;

    public /* synthetic */ ww1(ClassReader classReader) {
        this.a = classReader;
    }

    @Override // com.sun.tools.javac.util.Name.NameMapper
    public final Object map(byte[] bArr, int i, int i2) {
        return this.a.sigToType(bArr, i, i2);
    }
}
