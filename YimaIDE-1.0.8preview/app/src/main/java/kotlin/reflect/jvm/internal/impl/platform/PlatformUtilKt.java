package kotlin.reflect.jvm.internal.impl.platform;

import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import net.schmizz.sshj.sftp.PathHelper;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public final class PlatformUtilKt {
    public static final String getPresentableDescription(TargetPlatform targetPlatform) {
        targetPlatform.getClass();
        return CollectionsKt.joinToString$default(targetPlatform.getComponentPlatforms(), PathHelper.DEFAULT_PATH_SEPARATOR, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
    }
}
