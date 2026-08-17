package com.android.tools.r8.internal;

import com.android.tools.r8.TextInputStream;
import com.android.tools.r8.profile.art.ArtProfileBuilder;
import com.android.tools.r8.references.MethodReference;
import com.sun.jna.platform.linux.Fcntl;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.function.Consumer;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.s4, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2568s4 implements ArtProfileBuilder {
    public final /* synthetic */ OutputStreamWriter a;

    public C2568s4(OutputStreamWriter outputStreamWriter) {
        this.a = outputStreamWriter;
    }

    @Override // com.android.tools.r8.profile.art.ArtProfileBuilder
    public final ArtProfileBuilder addClassRule(Consumer consumer) {
        consumer.accept(new C2398q4(this));
        return this;
    }

    @Override // com.android.tools.r8.profile.art.ArtProfileBuilder
    public final ArtProfileBuilder addHumanReadableArtProfile(TextInputStream textInputStream, Consumer consumer) {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(textInputStream.getInputStream(), textInputStream.getCharset());
            try {
                char[] cArr = new char[Fcntl.S_ISGID];
                int i = inputStreamReader.read(cArr);
                while (true) {
                    OutputStreamWriter outputStreamWriter = this.a;
                    if (i == -1) {
                        AbstractC2653t4.a(outputStreamWriter, XmlPullParser.NO_NAMESPACE);
                        inputStreamReader.close();
                        return this;
                    }
                    outputStreamWriter.write(cArr, 0, i);
                    i = inputStreamReader.read(cArr);
                    u8i.a(e);
                    return null;
                }
            } catch (Throwable th) {
                try {
                    inputStreamReader.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (IOException e) {
            u8i.a(e);
            return null;
        }
    }

    @Override // com.android.tools.r8.profile.art.ArtProfileBuilder
    public final ArtProfileBuilder addMethodRule(Consumer consumer) {
        C1975l7 c1975l7 = new C1975l7();
        consumer.accept(new C2482r4(this, c1975l7));
        AbstractC2653t4.a(this.a, MO.a((MethodReference) c1975l7.a()));
        return this;
    }
}
