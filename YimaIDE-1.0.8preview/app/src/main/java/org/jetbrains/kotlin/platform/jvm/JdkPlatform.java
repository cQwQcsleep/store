package org.jetbrains.kotlin.platform.jvm;

import kotlin.Metadata;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.config.JvmTarget;
import org.jetbrains.kotlin.platform.TargetPlatformVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\b\u001a\u00020\tH\u0096\u0080\u0004J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0096\u0082\u0004J\n\u0010\u0015\u001a\u00020\u0016H\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\n\u001a\u00020\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/platform/jvm/JdkPlatform;", "Lorg/jetbrains/kotlin/platform/jvm/JvmPlatform;", "targetVersion", "Lorg/jetbrains/kotlin/config/JvmTarget;", "<init>", "(Lorg/jetbrains/kotlin/config/JvmTarget;)V", "getTargetVersion", "()Lorg/jetbrains/kotlin/config/JvmTarget;", "toString", "", "oldFashionedDescription", "getOldFashionedDescription", "()Ljava/lang/String;", "targetPlatformVersion", "Lorg/jetbrains/kotlin/platform/TargetPlatformVersion;", "getTargetPlatformVersion", "()Lorg/jetbrains/kotlin/platform/TargetPlatformVersion;", "equals", "", "other", "", "hashCode", "", "org.jetbrains.kotlin:language.targets.jvm"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class JdkPlatform extends JvmPlatform {
    private final JvmTarget targetVersion;

    public JdkPlatform(JvmTarget jvmTarget) {
        jvmTarget.getClass();
        this.targetVersion = jvmTarget;
    }

    public boolean equals(Object other) {
        return other instanceof JdkPlatform;
    }

    @Override // org.jetbrains.kotlin.platform.jvm.JvmPlatform, org.jetbrains.kotlin.platform.SimplePlatform
    public String getOldFashionedDescription() {
        return "JVM " + this.targetVersion.getDescription();
    }

    @Override // org.jetbrains.kotlin.platform.SimplePlatform
    public TargetPlatformVersion getTargetPlatformVersion() {
        return this.targetVersion;
    }

    public final JvmTarget getTargetVersion() {
        return this.targetVersion;
    }

    public int hashCode() {
        return Reflection.getOrCreateKotlinClass(JdkPlatform.class).hashCode();
    }

    @Override // org.jetbrains.kotlin.platform.SimplePlatform
    public String toString() {
        return getPlatformName() + " (" + this.targetVersion + ')';
    }
}
