package androidx.core.content;

import android.content.LocusId;
import androidx.core.util.Preconditions;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public final class LocusIdCompat {
    private final String mId;
    private final LocusId mWrapped;

    public static class Api29Impl {
        private Api29Impl() {
        }

        public static LocusId create(String str) {
            return new LocusId(str);
        }

        public static String getId(LocusId locusId) {
            return locusId.getId();
        }
    }

    public LocusIdCompat(String str) {
        this.mId = (String) Preconditions.checkStringNotEmpty(str, "id cannot be empty");
        this.mWrapped = Api29Impl.create(str);
    }

    private String getSanitizedId() {
        return this.mId.length() + "_chars";
    }

    public static LocusIdCompat toLocusIdCompat(LocusId locusId) {
        Preconditions.checkNotNull(locusId, "locusId cannot be null");
        return new LocusIdCompat((String) Preconditions.checkStringNotEmpty(Api29Impl.getId(locusId), "id cannot be empty"));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || LocusIdCompat.class != obj.getClass()) {
            return false;
        }
        String str = this.mId;
        String str2 = ((LocusIdCompat) obj).mId;
        if (str == null) {
            return str2 == null;
        }
        return str.equals(str2);
    }

    public String getId() {
        return this.mId;
    }

    public int hashCode() {
        String str = this.mId;
        return 31 + (str == null ? 0 : str.hashCode());
    }

    public LocusId toLocusId() {
        return this.mWrapped;
    }

    public String toString() {
        return "LocusIdCompat[" + getSanitizedId() + "]";
    }
}
