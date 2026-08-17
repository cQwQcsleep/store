package com.android.tools.r8.internal;

import defpackage.mg9;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public abstract class ug {
    public static void a(Object obj) {
        Class<?> cls = obj.getClass();
        String strReplace = cls.getName().replace('.', '/');
        if (strReplace.startsWith("org/objectweb/asm/") && (strReplace.contains("Test$") || Pattern.matches("org/objectweb/asm/util/Trace(Annotation|Class|Field|Method|Module|RecordComponent|Signature)Visitor(\\$.*)?", strReplace) || Pattern.matches("org/objectweb/asm/util/Check(Annotation|Class|Field|Method|Module|RecordComponent|Signature)Adapter(\\$.*)?", strReplace))) {
            return;
        }
        InputStream resourceAsStream = cls.getClassLoader().getResourceAsStream(strReplace.concat(".class"));
        if (resourceAsStream == null) {
            k2d.a("Bytecode not available, can't check class version");
            return;
        }
        try {
            DataInputStream dataInputStream = new DataInputStream(resourceAsStream);
            try {
                dataInputStream.readInt();
                int unsignedShort = dataInputStream.readUnsignedShort();
                dataInputStream.close();
                if (unsignedShort == 65535) {
                    return;
                }
                k2d.a("ASM9_EXPERIMENTAL can only be used by classes compiled with --enable-preview");
            } catch (Throwable th) {
                try {
                    dataInputStream.close();
                } catch (Throwable unused) {
                }
                throw th;
            }
        } catch (IOException e) {
            mg9.a("I/O error, can't check class version", e);
        }
    }
}
