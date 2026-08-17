package org.jetbrains.kotlin.platform.konan;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\u0082\u0001\u0002\b\t¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/platform/konan/NativePlatform;", "Lorg/jetbrains/kotlin/platform/NativePlatform;", "<init>", "()V", "oldFashionedDescription", "", "getOldFashionedDescription", "()Ljava/lang/String;", "Lorg/jetbrains/kotlin/platform/konan/NativePlatformUnspecifiedTarget;", "Lorg/jetbrains/kotlin/platform/konan/NativePlatformWithTarget;", "org.jetbrains.kotlin:native.config"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class NativePlatform extends org.jetbrains.kotlin.platform.NativePlatform {
    private NativePlatform() {
        super("Native");
    }

    @Override // org.jetbrains.kotlin.platform.SimplePlatform
    public String getOldFashionedDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append(this);
        sb.append(' ');
        return sb.toString();
    }

    public /* synthetic */ NativePlatform(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }
}
