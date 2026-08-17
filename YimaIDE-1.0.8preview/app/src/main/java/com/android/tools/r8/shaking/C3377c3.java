package com.android.tools.r8.shaking;

import com.android.tools.r8.origin.Origin;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.shaking.c3, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C3377c3 implements Z2 {
    public final Path a;
    public final List b;
    public final Origin c;

    public C3377c3(List list) {
        Path path = Paths.get(XmlPullParser.NO_NAMESPACE, new String[0]);
        Origin originUnknown = Origin.unknown();
        this.a = path;
        this.b = list;
        this.c = originUnknown;
    }

    public static C3377c3 a(List<String> list) {
        return new C3377c3(list);
    }

    @Override // com.android.tools.r8.shaking.Z2
    public final String get() {
        return String.join(System.lineSeparator(), this.b);
    }

    @Override // com.android.tools.r8.shaking.Z2
    public final String getName() {
        return "<no file>";
    }

    @Override // com.android.tools.r8.shaking.Z2
    public final Origin getOrigin() {
        return this.c;
    }

    @Override // com.android.tools.r8.shaking.Z2
    public final Path a() {
        return this.a;
    }

    public C3377c3(List list, Path path, Origin origin) {
        this.a = path;
        this.b = list;
        this.c = origin;
    }
}
