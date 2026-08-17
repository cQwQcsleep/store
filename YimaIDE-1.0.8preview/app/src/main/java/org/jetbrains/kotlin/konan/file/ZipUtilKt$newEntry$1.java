package org.jetbrains.kotlin.konan.file;

import java.util.zip.ZipEntry;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Ljava/util/zip/ZipEntry;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
public final class ZipUtilKt$newEntry$1 extends Lambda implements Function1<ZipEntry, Unit> {
    public static final ZipUtilKt$newEntry$1 INSTANCE = new ZipUtilKt$newEntry$1();

    public ZipUtilKt$newEntry$1() {
        super(1);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((ZipEntry) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(ZipEntry zipEntry) {
        zipEntry.getClass();
    }
}
