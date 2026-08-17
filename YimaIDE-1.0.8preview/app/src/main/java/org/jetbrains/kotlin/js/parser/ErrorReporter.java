package org.jetbrains.kotlin.js.parser;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public interface ErrorReporter {
    /* JADX INFO: renamed from: error */
    void mo521error(String str, CodePosition codePosition, CodePosition codePosition2);

    void warning(String str, CodePosition codePosition, CodePosition codePosition2);
}
