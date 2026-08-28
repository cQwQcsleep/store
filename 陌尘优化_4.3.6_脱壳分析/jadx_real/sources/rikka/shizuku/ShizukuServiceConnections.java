package rikka.shizuku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import rikka.shizuku.Shizuku;

/* loaded from: /workspace/unpacked/classes.dex */
class ShizukuServiceConnections {
    private static final Map<String, ShizukuServiceConnection> CACHE = Collections.synchronizedMap(new HashMap());

    ShizukuServiceConnections() {
    }

    static ShizukuServiceConnection get(Shizuku.UserServiceArgs userServiceArgs) {
        String className = userServiceArgs.tag != null ? userServiceArgs.tag : userServiceArgs.componentName.getClassName();
        ShizukuServiceConnection shizukuServiceConnection = CACHE.get(className);
        if (shizukuServiceConnection != null) {
            return shizukuServiceConnection;
        }
        ShizukuServiceConnection shizukuServiceConnection2 = new ShizukuServiceConnection(userServiceArgs);
        CACHE.put(className, shizukuServiceConnection2);
        return shizukuServiceConnection2;
    }

    static void remove(ShizukuServiceConnection shizukuServiceConnection) {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, ShizukuServiceConnection> entry : CACHE.entrySet()) {
            if (entry.getValue() == shizukuServiceConnection) {
                arrayList.add(entry.getKey());
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            CACHE.remove((String) it.next());
        }
    }
}
