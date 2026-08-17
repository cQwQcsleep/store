package org.jetbrains.kotlin.platform.jvm;

import kotlin.Metadata;
import kotlin.collections.SetsKt;
import org.jetbrains.kotlin.platform.TargetPlatform;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/platform/jvm/JvmPlatforms$CompatJvmPlatform;", "Lorg/jetbrains/kotlin/platform/TargetPlatform;", "<init>", "()V", "org.jetbrains.kotlin:language.targets.jvm"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class JvmPlatforms$CompatJvmPlatform extends TargetPlatform {
    public static final JvmPlatforms$CompatJvmPlatform INSTANCE = new JvmPlatforms$CompatJvmPlatform();

    private JvmPlatforms$CompatJvmPlatform() {
        super(SetsKt.setOf(JvmPlatforms.INSTANCE.getUNSPECIFIED_SIMPLE_JVM_PLATFORM()));
    }
}
