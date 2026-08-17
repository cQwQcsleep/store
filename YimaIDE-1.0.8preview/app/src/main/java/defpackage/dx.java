package defpackage;

import java.util.Locale;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public enum dx {
    PENDING,
    IN_PROGRESS,
    COMPLETED,
    CANCELLED;

    public static final /* synthetic */ EnumEntries h = EnumEntriesKt.enumEntries(b());
    public static final a b = new a(null);

    public static final /* synthetic */ class b {
        public static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[dx.values().length];
            try {
                iArr[dx.PENDING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[dx.IN_PROGRESS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[dx.COMPLETED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[dx.CANCELLED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            a = iArr;
        }
    }

    public final String d() {
        int i = b.a[ordinal()];
        if (i == 1) {
            return "pending";
        }
        if (i == 2) {
            return "in_progress";
        }
        if (i == 3) {
            return "completed";
        }
        if (i == 4) {
            return "cancelled";
        }
        bu8.a();
        return null;
    }

    public static final class a {
        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Code restructure failed: missing block: B:12:0x0027, code lost:
        
            if (r0.equals("skipped") == false) goto L44;
         */
        /* JADX WARN: Code restructure failed: missing block: B:15:0x0030, code lost:
        
            if (r0.equals("cancelled") == false) goto L44;
         */
        /* JADX WARN: Code restructure failed: missing block: B:18:0x0039, code lost:
        
            if (r0.equals("doing") == false) goto L44;
         */
        /* JADX WARN: Code restructure failed: missing block: B:21:0x0042, code lost:
        
            if (r0.equals("done") == false) goto L44;
         */
        /* JADX WARN: Code restructure failed: missing block: B:24:0x004b, code lost:
        
            if (r0.equals("canceled") == false) goto L44;
         */
        /* JADX WARN: Code restructure failed: missing block: B:27:0x0050, code lost:
        
            return defpackage.dx.f;
         */
        /* JADX WARN: Code restructure failed: missing block: B:29:0x0057, code lost:
        
            if (r0.equals("complete") == false) goto L44;
         */
        /* JADX WARN: Code restructure failed: missing block: B:32:0x0060, code lost:
        
            if (r0.equals("in_progress") == false) goto L44;
         */
        /* JADX WARN: Code restructure failed: missing block: B:35:0x0069, code lost:
        
            if (r0.equals("completed") == false) goto L44;
         */
        /* JADX WARN: Code restructure failed: missing block: B:38:0x006e, code lost:
        
            return defpackage.dx.e;
         */
        /* JADX WARN: Code restructure failed: missing block: B:40:0x0075, code lost:
        
            if (r0.equals("active") == false) goto L44;
         */
        /* JADX WARN: Code restructure failed: missing block: B:43:0x007a, code lost:
        
            return defpackage.dx.d;
         */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final dx a(String str) {
            String lowerCase;
            String string;
            if (str == null || (string = StringsKt.trim(str).toString()) == null) {
                lowerCase = null;
            } else {
                lowerCase = string.toLowerCase(Locale.ROOT);
                lowerCase.getClass();
            }
            if (lowerCase != null) {
                switch (lowerCase.hashCode()) {
                    case -1422950650:
                        break;
                    case -1402931637:
                        break;
                    case -753541113:
                        break;
                    case -599445191:
                        break;
                    case -123173735:
                        break;
                    case 3089282:
                        break;
                    case 95763319:
                        break;
                    case 476588369:
                        break;
                    case 2147444528:
                        break;
                }
            }
            return dx.PENDING;
        }

        public a() {
        }
    }
}
