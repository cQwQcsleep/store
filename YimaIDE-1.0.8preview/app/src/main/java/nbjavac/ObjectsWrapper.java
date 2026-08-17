package nbjavac;

import java.util.Objects;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class ObjectsWrapper {
    public static int checkFromIndexSize(int i, int i2, int i3) {
        if ((i3 | i | i2) >= 0 && i2 <= i3 - i) {
            return i;
        }
        throw new IndexOutOfBoundsException(XmlPullParser.NO_NAMESPACE + i + ", " + i2 + ", " + i3);
    }

    public static <T> T requireNonNullElse(T t, T t2) {
        if (t != null) {
            return t;
        }
        Objects.requireNonNull(t2, "elseValue");
        return t2;
    }
}
