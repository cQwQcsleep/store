package j$.time;

import j$.util.Objects;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: /workspace/unpacked/classes3.dex */
public abstract class x implements Serializable {
    private static final long serialVersionUID = 8352817235686L;

    public abstract j$.time.zone.f C();

    abstract void G(ObjectOutput objectOutput);

    public abstract String i();

    static {
        Map.Entry[] entryArr = {j$.com.android.tools.r8.a.g("ACT", "Australia/Darwin"), j$.com.android.tools.r8.a.g("AET", "Australia/Sydney"), j$.com.android.tools.r8.a.g("AGT", "America/Argentina/Buenos_Aires"), j$.com.android.tools.r8.a.g("ART", "Africa/Cairo"), j$.com.android.tools.r8.a.g("AST", "America/Anchorage"), j$.com.android.tools.r8.a.g("BET", "America/Sao_Paulo"), j$.com.android.tools.r8.a.g("BST", "Asia/Dhaka"), j$.com.android.tools.r8.a.g("CAT", "Africa/Harare"), j$.com.android.tools.r8.a.g("CNT", "America/St_Johns"), j$.com.android.tools.r8.a.g("CST", "America/Chicago"), j$.com.android.tools.r8.a.g("CTT", "Asia/Shanghai"), j$.com.android.tools.r8.a.g("EAT", "Africa/Addis_Ababa"), j$.com.android.tools.r8.a.g("ECT", "Europe/Paris"), j$.com.android.tools.r8.a.g("IET", "America/Indiana/Indianapolis"), j$.com.android.tools.r8.a.g("IST", "Asia/Kolkata"), j$.com.android.tools.r8.a.g("JST", "Asia/Tokyo"), j$.com.android.tools.r8.a.g("MIT", "Pacific/Apia"), j$.com.android.tools.r8.a.g("NET", "Asia/Yerevan"), j$.com.android.tools.r8.a.g("NST", "Pacific/Auckland"), j$.com.android.tools.r8.a.g("PLT", "Asia/Karachi"), j$.com.android.tools.r8.a.g("PNT", "America/Phoenix"), j$.com.android.tools.r8.a.g("PRT", "America/Puerto_Rico"), j$.com.android.tools.r8.a.g("PST", "America/Los_Angeles"), j$.com.android.tools.r8.a.g("SST", "Pacific/Guadalcanal"), j$.com.android.tools.r8.a.g("VST", "Asia/Ho_Chi_Minh"), j$.com.android.tools.r8.a.g("EST", "-05:00"), j$.com.android.tools.r8.a.g("MST", "-07:00"), j$.com.android.tools.r8.a.g("HST", "-10:00")};
        HashMap map = new HashMap(28);
        for (int i = 0; i < 28; i++) {
            Map.Entry entry = entryArr[i];
            Object objRequireNonNull = Objects.requireNonNull(entry.getKey());
            if (map.put(objRequireNonNull, Objects.requireNonNull(entry.getValue())) != null) {
                throw new IllegalArgumentException("duplicate key: " + objRequireNonNull);
            }
        }
        Collections.unmodifiableMap(map);
    }

    public static x E(String str, y yVar) {
        Objects.a(str, "prefix");
        Objects.a(yVar, "offset");
        if (str.isEmpty()) {
            return yVar;
        }
        if (!str.equals("GMT") && !str.equals("UTC") && !str.equals("UT")) {
            throw new IllegalArgumentException("prefix should be GMT, UTC or UT, is: ".concat(str));
        }
        if (yVar.I() != 0) {
            str = str.concat(yVar.i());
        }
        return new z(str, j$.time.zone.f.h(yVar));
    }

    static x D(String str) {
        Objects.a(str, "zoneId");
        if (str.length() <= 1 || str.startsWith("+") || str.startsWith("-")) {
            return y.J(str);
        }
        if (str.startsWith("UTC") || str.startsWith("GMT")) {
            return F(str, 3);
        }
        if (str.startsWith("UT")) {
            return F(str, 2);
        }
        return z.H(str);
    }

    private static x F(String str, int i) {
        String strSubstring = str.substring(0, i);
        if (str.length() == i) {
            return E(strSubstring, y.e);
        }
        if (str.charAt(i) != '+' && str.charAt(i) != '-') {
            return z.H(str);
        }
        try {
            y yVarJ = y.J(str.substring(i));
            if (yVarJ == y.e) {
                return E(strSubstring, yVarJ);
            }
            return E(strSubstring, yVarJ);
        } catch (C0063a e) {
            throw new C0063a("Invalid ID for offset-based ZoneId: ".concat(str), e);
        }
    }

    x() {
        if (getClass() != y.class && getClass() != z.class) {
            throw new AssertionError("Invalid subclass");
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof x) {
            return i().equals(((x) obj).i());
        }
        return false;
    }

    public int hashCode() {
        return i().hashCode();
    }

    private void readObject(ObjectInputStream objectInputStream) throws InvalidObjectException {
        throw new InvalidObjectException("Deserialization via serialization delegate");
    }

    public String toString() {
        return i();
    }

    private Object writeReplace() {
        return new s((byte) 7, this);
    }
}
