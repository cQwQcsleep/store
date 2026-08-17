package org.jetbrains.kotlin.platform;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\b&\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/platform/JsPlatform;", "Lorg/jetbrains/kotlin/platform/SimplePlatform;", "Lorg/jetbrains/kotlin/platform/PotentiallyWebPlatform;", "<init>", "()V", "oldFashionedDescription", "", "getOldFashionedDescription", "()Ljava/lang/String;", "isWeb", "", "()Z", "org.jetbrains.kotlin:language.targets"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class JsPlatform extends SimplePlatform implements PotentiallyWebPlatform {
    public JsPlatform() {
        super("JS");
    }

    @Override // org.jetbrains.kotlin.platform.SimplePlatform
    public String getOldFashionedDescription() {
        return "JavaScript ";
    }

    @Override // org.jetbrains.kotlin.platform.PotentiallyWebPlatform
    public boolean isWeb() {
        return true;
    }
}
