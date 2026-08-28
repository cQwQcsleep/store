package com.swift.sandhook;

import com.swift.sandhook.wrapper.HookErrorException;
import com.swift.sandhook.wrapper.HookWrapper;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import obfuse.NPStringFog;

/* loaded from: /workspace/unpacked/classes.dex */
public class PendingHookHandler {
    private static boolean canUsePendingHook;
    private static Map<Class, Vector<HookWrapper.HookEntity>> pendingHooks = new ConcurrentHashMap();

    static {
        if (SandHookConfig.delayHook) {
            canUsePendingHook = SandHook.initForPendingHook();
        }
    }

    public static void addPendingHook(HookWrapper.HookEntity hookEntity) {
        synchronized (PendingHookHandler.class) {
            Vector<HookWrapper.HookEntity> vector = pendingHooks.get(hookEntity.target.getDeclaringClass());
            if (vector == null) {
                vector = new Vector<>();
                pendingHooks.put(hookEntity.target.getDeclaringClass(), vector);
            }
            vector.add(hookEntity);
        }
    }

    public static boolean canWork() {
        return canUsePendingHook && SandHook.canGetObject() && !SandHookConfig.DEBUG;
    }

    public static void onClassInit(long j) {
        Class cls;
        Vector<HookWrapper.HookEntity> vector;
        if (j == 0 || (cls = (Class) SandHook.getObject(j)) == null || (vector = pendingHooks.get(cls)) == null) {
            return;
        }
        Iterator<HookWrapper.HookEntity> it = vector.iterator();
        while (it.hasNext()) {
            HookWrapper.HookEntity next = it.next();
            HookLog.w(NPStringFog.decode("0A1F4D110B0F030C1C0950050E010A47031D1C5000041A090801484E") + next.target.toString());
            try {
                next.initClass = false;
                SandHook.hook(next);
            } catch (HookErrorException e) {
                HookLog.e(NPStringFog.decode("3E150305070F00453A011F06412B13150A004F"), e);
            }
        }
        pendingHooks.remove(cls);
    }
}
