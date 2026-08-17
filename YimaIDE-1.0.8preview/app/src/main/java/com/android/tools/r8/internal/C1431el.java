package com.android.tools.r8.internal;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;

/* JADX INFO: renamed from: com.android.tools.r8.internal.el, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1431el implements InterfaceC1688hl {
    public int a = 0;
    public final /* synthetic */ Path b;

    public C1431el(Path path) {
        this.b = path;
    }

    public final InterfaceC1517fl a() {
        Path path = this.b;
        int i = this.a;
        this.a = i + 1;
        Path pathResolve = path.resolve(i + ".log");
        if (Files.exists(pathResolve, new LinkOption[0])) {
            System.out.println("Checking against determinism log: " + pathResolve);
            return new C1602gl(Files.newBufferedReader(pathResolve, StandardCharsets.UTF_8));
        }
        System.out.println("Writing determinism log: " + pathResolve);
        return new C1773il(new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pathResolve.toFile()), StandardCharsets.UTF_8)));
    }
}
