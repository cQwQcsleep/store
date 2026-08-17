package org.jetbrains.kotlin.konan.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.text.StringsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\r\u001a\u00020\u0007H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0006\u0010\bR\u0011\u0010\u000b\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\bR\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/konan/util/InternalServer;", "", "()V", "host", "", "internalDomain", "isAccessible", "", "()Z", "isAccessible$delegate", "Lkotlin/Lazy;", "isAvailable", "url", "checkAccessible", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class InternalServer {
    public static final InternalServer INSTANCE = new InternalServer();

    /* JADX INFO: renamed from: isAccessible$delegate, reason: from kotlin metadata */
    private static final Lazy isAccessible = LazyKt.lazy(new Function0<Boolean>() { // from class: org.jetbrains.kotlin.konan.util.InternalServer.isAccessible.2
        /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
        public final Boolean m589invoke() {
            return Boolean.valueOf(InternalServer.INSTANCE.checkAccessible());
        }
    });
    public static final String url = "https://repo.labs.intellij.net/kotlin-native";

    private InternalServer() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean checkAccessible() {
        try {
            String canonicalHostName = InetAddress.getLocalHost().getCanonicalHostName();
            canonicalHostName.getClass();
            if (!StringsKt.endsWith$default(canonicalHostName, ".labs.intellij.net", false, 2, (Object) null)) {
                return false;
            }
            InetAddress.getByName("repo.labs.intellij.net");
            return true;
        } catch (UnknownHostException unused) {
            return false;
        }
    }

    private final boolean isAccessible() {
        return ((Boolean) isAccessible.getValue()).booleanValue();
    }

    public final boolean isAvailable() {
        String str = System.getenv("KONAN_USE_INTERNAL_SERVER");
        if (str != null) {
            int iHashCode = str.hashCode();
            if (iHashCode != 48) {
                if (iHashCode != 49) {
                    if (iHashCode == 3005871 && str.equals("auto")) {
                        return isAccessible();
                    }
                } else if (str.equals("1")) {
                    return true;
                }
            } else if (!str.equals("0")) {
            }
            dwe.a("unexpected environment: KONAN_USE_INTERNAL_SERVER=".concat(str));
        }
        return false;
    }
}
