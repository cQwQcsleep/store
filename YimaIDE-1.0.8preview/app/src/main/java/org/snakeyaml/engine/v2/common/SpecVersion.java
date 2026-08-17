package org.snakeyaml.engine.v2.common;

import java.io.Serializable;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public class SpecVersion implements Serializable {
    private final int major;
    private final int minor;

    public SpecVersion(int i, int i2) {
        this.major = i;
        this.minor = i2;
    }

    public int getMajor() {
        return this.major;
    }

    public int getMinor() {
        return this.minor;
    }

    public String getRepresentation() {
        return this.major + "." + this.minor;
    }

    public String toString() {
        return "Version{major=" + this.major + ", minor=" + this.minor + "}";
    }
}
