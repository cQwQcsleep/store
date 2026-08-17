package com.android.tools.r8.internal;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class Pl0 {
    public static final Pl0 c;
    public static final /* synthetic */ boolean d = true;
    public final String a;
    public final String b;

    static {
        Pl0 pl0;
        try {
            InputStream resourceAsStream = Pl0.class.getClassLoader().getResourceAsStream("r8-version.properties");
            try {
                pl0 = resourceAsStream == null ? new Pl0() : new Pl0(resourceAsStream);
                if (resourceAsStream != null) {
                    resourceAsStream.close();
                }
            } catch (Throwable th) {
                if (resourceAsStream != null) {
                    try {
                        resourceAsStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        } catch (IOException unused) {
            pl0 = new Pl0();
        }
        c = pl0;
    }

    public Pl0(InputStream inputStream) throws IOException {
        Properties properties = new Properties();
        properties.load(inputStream);
        long j = Long.parseLong(properties.getProperty("version-file.version.code"));
        if (!d && j < 1) {
            x1f.a();
            throw null;
        }
        this.a = properties.getProperty("version.sha");
        this.b = properties.getProperty("releaser");
    }

    public final String a() {
        String strB = b();
        String str = this.b;
        return "build " + strB + (str != null ? " from ".concat(str) : XmlPullParser.NO_NAMESPACE);
    }

    public String b() {
        return c() ? "engineering" : this.a;
    }

    public final boolean c() {
        String str = this.a;
        return str == null || str.trim().isEmpty();
    }

    public final String toString() {
        return this.a + " from " + this.b;
    }

    public Pl0() {
    }
}
