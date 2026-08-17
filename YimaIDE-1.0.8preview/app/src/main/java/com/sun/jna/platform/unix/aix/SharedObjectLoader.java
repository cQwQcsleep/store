package com.sun.jna.platform.unix.aix;

import com.sun.jna.Library;
import com.sun.jna.Native;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class SharedObjectLoader {
    private SharedObjectLoader() {
    }

    private static Map<String, Object> getOptions() {
        HashMap map = new HashMap();
        map.put(Library.OPTION_OPEN_FLAGS, 327684);
        return Collections.unmodifiableMap(map);
    }

    public static Perfstat getPerfstatInstance() {
        Map<String, Object> options = getOptions();
        try {
            return (Perfstat) Native.load("/usr/lib/libperfstat.a(shr_64.o)", Perfstat.class, options);
        } catch (UnsatisfiedLinkError unused) {
            return (Perfstat) Native.load("/usr/lib/libperfstat.a(shr.o)", Perfstat.class, options);
        }
    }
}
