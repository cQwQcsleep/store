package com.sun.jna.platform.unix;

import com.sun.jna.Library;
import com.sun.jna.Native;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface LibC extends Library, LibCAPI {
    public static final LibC INSTANCE = (LibC) Native.load("c", LibC.class);
    public static final String NAME = "c";
}
