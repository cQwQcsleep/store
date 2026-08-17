package com.android.tools.r8;

import com.android.tools.r8.internal.C0831Sp;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.origin.PathOrigin;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface t0 extends Resource {

    public static class a implements t0 {
        public static final /* synthetic */ boolean d = true;
        public final Path a;
        public final Charset b;
        public final PathOrigin c;

        public a(Path path, Charset charset) {
            boolean z = d;
            if (!z && path == null) {
                x1f.a();
                throw null;
            }
            if (!z && charset == null) {
                x1f.a();
                throw null;
            }
            this.a = path;
            this.b = charset;
            this.c = new PathOrigin(path);
        }

        @Override // com.android.tools.r8.t0
        public final String a() throws ResourceException {
            try {
                return C0831Sp.a(this.a, this.b);
            } catch (IOException e) {
                throw new ResourceException(this.c, e);
            }
        }

        @Override // com.android.tools.r8.Resource
        public final Origin getOrigin() {
            return this.c;
        }
    }

    static a a(Path path) {
        return new a(path, StandardCharsets.UTF_8);
    }

    String a() throws ResourceException;

    static t0 a(String str, Origin origin) {
        return new u0(origin, str);
    }
}
