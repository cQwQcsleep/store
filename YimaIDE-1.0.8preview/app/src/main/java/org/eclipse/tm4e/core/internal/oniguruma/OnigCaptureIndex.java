package org.eclipse.tm4e.core.internal.oniguruma;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public final class OnigCaptureIndex {
    public static final OnigCaptureIndex EMPTY = new OnigCaptureIndex(0, 0);
    public final int end;
    public final int start;

    public OnigCaptureIndex(int i, int i2) {
        this.start = Math.max(i, 0);
        this.end = Math.max(i2, 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof OnigCaptureIndex) {
            OnigCaptureIndex onigCaptureIndex = (OnigCaptureIndex) obj;
            if (this.end == onigCaptureIndex.end && this.start == onigCaptureIndex.start) {
                return true;
            }
        }
        return false;
    }

    public int getLength() {
        return this.end - this.start;
    }

    public int hashCode() {
        return ((this.end + 31) * 31) + this.start;
    }

    public String toString() {
        return "{, \"start\": " + this.start + ", \"end\": " + this.end + ", \"length\": " + getLength() + "}";
    }
}
