package com.android.tools.r8.shaking;

import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.origin.PathOrigin;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.shaking.b3, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C3372b3 implements Z2 {
    public final Path a;

    public C3372b3(Path path) {
        this.a = path;
    }

    @Override // com.android.tools.r8.shaking.Z2
    public final Path a() {
        Path parent = this.a.getParent();
        return parent == null ? Paths.get(XmlPullParser.NO_NAMESPACE, new String[0]) : parent;
    }

    @Override // com.android.tools.r8.shaking.Z2
    public final String get() {
        return new String(Files.readAllBytes(this.a), StandardCharsets.UTF_8);
    }

    @Override // com.android.tools.r8.shaking.Z2
    public final String getName() {
        return this.a.toString();
    }

    @Override // com.android.tools.r8.shaking.Z2
    public final Origin getOrigin() {
        return new PathOrigin(this.a);
    }
}
