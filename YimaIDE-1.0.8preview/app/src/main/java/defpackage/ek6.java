package defpackage;

import java.util.function.Function;
import jdk.internal.jimage.ImageReader;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class ek6 implements Function {
    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return ((ImageReader.Node) obj).getName();
    }
}
