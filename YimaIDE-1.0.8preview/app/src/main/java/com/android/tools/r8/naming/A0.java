package com.android.tools.r8.naming;

import com.android.tools.r8.Version;
import com.android.tools.r8.internal.EnumC3077y2;
import com.android.tools.r8.internal.Pl0;
import java.util.ArrayList;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class A0 {
    public final String a;
    public final boolean b;
    public final EnumC3077y2 c;
    public final MapVersion d;
    public final J0 e;

    public A0(String str, boolean z, EnumC3077y2 enumC3077y2, MapVersion mapVersion, J0 j0) {
        this.a = str;
        this.b = z;
        this.c = enumC3077y2;
        this.d = mapVersion;
        this.e = j0;
    }

    public final ArrayList a() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("# compiler: " + this.a);
        arrayList.add("# compiler_version: 8.5.10");
        if (this.b) {
            arrayList.add("# min_api: " + this.c.d());
        }
        if (Version.isDevelopmentVersion()) {
            arrayList.add("# compiler_hash: " + Pl0.c.b());
        }
        arrayList.add("# common_typos_disable");
        if (this.d.d(MapVersion.MAP_VERSION_NONE)) {
            arrayList.add("# " + this.d.toMapVersionMappingInformation().r());
        }
        arrayList.add("# pg_map_id: " + this.e.b());
        arrayList.add("# pg_map_hash: SHA-256 " + this.e.a());
        return arrayList;
    }
}
