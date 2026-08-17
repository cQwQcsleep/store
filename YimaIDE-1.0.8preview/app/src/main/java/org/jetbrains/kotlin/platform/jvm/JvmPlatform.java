package org.jetbrains.kotlin.platform.jvm;

import kotlin.Metadata;
import org.jetbrains.kotlin.platform.SimplePlatform;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/platform/jvm/JvmPlatform;", "Lorg/jetbrains/kotlin/platform/SimplePlatform;", "<init>", "()V", "oldFashionedDescription", "", "getOldFashionedDescription", "()Ljava/lang/String;", "org.jetbrains.kotlin:language.targets.jvm"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class JvmPlatform extends SimplePlatform {
    public JvmPlatform() {
        super("JVM");
    }

    @Override // org.jetbrains.kotlin.platform.SimplePlatform
    public String getOldFashionedDescription() {
        return "JVM ";
    }
}
