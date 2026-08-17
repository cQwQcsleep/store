package defpackage;

import com.reandroid.arsc.chunk.PackageBlock;
import com.reandroid.arsc.model.ResourcePackage;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final /* synthetic */ class gic implements Function {
    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return new ResourcePackage((PackageBlock) obj);
    }
}
