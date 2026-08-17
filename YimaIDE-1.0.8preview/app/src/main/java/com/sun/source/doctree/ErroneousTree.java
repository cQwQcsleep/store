package com.sun.source.doctree;

import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface ErroneousTree extends TextTree {
    Diagnostic<JavaFileObject> getDiagnostic();
}
