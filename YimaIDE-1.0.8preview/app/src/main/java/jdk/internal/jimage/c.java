package jdk.internal.jimage;

import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final /* synthetic */ class c implements Function {
    public final /* synthetic */ ImageReader.SharedImageReader b;

    public /* synthetic */ c(ImageReader.SharedImageReader sharedImageReader) {
        this.b = sharedImageReader;
    }

    @Override // java.util.function.Function
    public final Object apply(Object obj) {
        return this.b.newDirectory((String) obj);
    }
}
