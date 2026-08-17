package com.android.tools.r8.naming;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public enum MapVersion implements com.android.tools.r8.utils.structural.s<MapVersion> {
    MAP_VERSION_UNKNOWN("unknown"),
    MAP_VERSION_NONE("none"),
    MAP_VERSION_1_0("1.0"),
    MAP_VERSION_2_0("2.0"),
    MAP_VERSION_2_1("2.1"),
    MAP_VERSION_2_2("2.2"),
    MAP_VERSION_EXPERIMENTAL("experimental");

    private final String b;
    public static final MapVersion STABLE = MAP_VERSION_2_2;

    MapVersion(String str) {
        this.b = str;
    }

    public static MapVersion fromName(String str) {
        for (MapVersion mapVersion : values()) {
            if (mapVersion.getName().equals(str)) {
                return mapVersion;
            }
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.android.tools.r8.utils.structural.s
    public /* bridge */ /* synthetic */ int compareTo(com.android.tools.r8.utils.structural.s sVar) {
        return compareTo((Enum) sVar);
    }

    public String getName() {
        return this.b;
    }

    @Override // com.android.tools.r8.utils.structural.s, com.android.tools.r8.utils.structural.k
    public /* bridge */ /* synthetic */ boolean isEqualTo(Object obj) {
        return isEqualTo((com.android.tools.r8.utils.structural.s) obj);
    }

    public boolean isUnknown() {
        return this == MAP_VERSION_UNKNOWN;
    }

    public com.android.tools.r8.naming.mappinginformation.b toMapVersionMappingInformation() {
        return new com.android.tools.r8.naming.mappinginformation.b(this, getName());
    }
}
