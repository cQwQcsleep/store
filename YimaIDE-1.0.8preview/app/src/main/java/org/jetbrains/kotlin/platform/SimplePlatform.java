package org.jetbrains.kotlin.platform;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\n\u0010\b\u001a\u00020\u0003H\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\t\u001a\u00020\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u0007R\u0012\u0010\u000b\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\u0007R\u0014\u0010\r\u001a\u00020\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/platform/SimplePlatform;", "", "platformName", "", "<init>", "(Ljava/lang/String;)V", "getPlatformName", "()Ljava/lang/String;", "toString", "targetName", "getTargetName", "oldFashionedDescription", "getOldFashionedDescription", "targetPlatformVersion", "Lorg/jetbrains/kotlin/platform/TargetPlatformVersion;", "getTargetPlatformVersion", "()Lorg/jetbrains/kotlin/platform/TargetPlatformVersion;", "org.jetbrains.kotlin:language.targets"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class SimplePlatform {
    private final String platformName;
    private final TargetPlatformVersion targetPlatformVersion;

    public SimplePlatform(String str) {
        str.getClass();
        this.platformName = str;
        this.targetPlatformVersion = TargetPlatformVersion.NoVersion.INSTANCE;
    }

    public abstract String getOldFashionedDescription();

    public final String getPlatformName() {
        return this.platformName;
    }

    public String getTargetName() {
        return getTargetPlatformVersion().getDescription();
    }

    public TargetPlatformVersion getTargetPlatformVersion() {
        return this.targetPlatformVersion;
    }

    public String toString() {
        String targetName = getTargetName();
        int length = targetName.length();
        String str = this.platformName;
        if (length <= 0) {
            return str;
        }
        return str + " (" + targetName + ')';
    }
}
