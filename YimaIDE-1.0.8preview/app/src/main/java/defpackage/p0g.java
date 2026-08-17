package defpackage;

import com.reandroid.arsc.coder.xml.XmlEncodeException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final /* synthetic */ class p0g {
    public static /* synthetic */ void a(Object obj, Object obj2) throws XmlEncodeException {
        StringBuilder sb = new StringBuilder();
        sb.append(obj);
        sb.append((Object) ": ");
        sb.append(obj2);
        throw new XmlEncodeException(sb.toString());
    }
}
