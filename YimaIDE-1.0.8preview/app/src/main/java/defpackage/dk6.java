package defpackage;

import android.graphics.Bitmap;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface dk6 {

    public static final class a implements dk6 {
        public static final a a = new a();

        public boolean equals(Object obj) {
            return this == obj || (obj instanceof a);
        }

        public int hashCode() {
            return -716472242;
        }

        public String toString() {
            return "Failed";
        }
    }

    public static final class b implements dk6 {
        public static final b a = new b();

        public boolean equals(Object obj) {
            return this == obj || (obj instanceof b);
        }

        public int hashCode() {
            return 687437643;
        }

        public String toString() {
            return "Loading";
        }
    }

    public static final class c implements dk6 {
        public final Bitmap a;

        public c(Bitmap bitmap) {
            bitmap.getClass();
            this.a = bitmap;
        }

        public final Bitmap a() {
            return this.a;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof c) && Intrinsics.areEqual(this.a, ((c) obj).a);
        }

        public int hashCode() {
            return this.a.hashCode();
        }

        public String toString() {
            return "Success(bitmap=" + this.a + ")";
        }
    }
}
