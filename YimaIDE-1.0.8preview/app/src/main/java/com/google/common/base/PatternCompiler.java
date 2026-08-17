package com.google.common.base;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
interface PatternCompiler {
    CommonPattern compile(String str);

    boolean isPcreLike();
}
