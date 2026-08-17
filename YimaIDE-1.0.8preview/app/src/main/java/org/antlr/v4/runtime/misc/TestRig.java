package org.antlr.v4.runtime.misc;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
@Deprecated
public class TestRig {
    public static void main(String[] strArr) {
        try {
            Class<?> cls = Class.forName("org.antlr.v4.gui.TestRig");
            System.err.println("Warning: TestRig moved to org.antlr.v4.gui.TestRig; calling automatically");
            try {
                cls.getMethod("main", String[].class).invoke(null, strArr);
            } catch (Exception unused) {
                System.err.println("Problems calling org.antlr.v4.gui.TestRig.main(args)");
            }
        } catch (ClassNotFoundException unused2) {
            System.err.println("Use of TestRig now requires the use of the tool jar, antlr-4.X-complete.jar");
            System.err.println("Maven users need group ID org.antlr and artifact ID antlr4");
        }
    }
}
