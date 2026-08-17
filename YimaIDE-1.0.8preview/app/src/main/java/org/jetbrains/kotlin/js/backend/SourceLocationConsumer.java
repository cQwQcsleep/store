package org.jetbrains.kotlin.js.backend;

import kotlin.Metadata;
import org.jetbrains.kotlin.js.backend.ast.JsLocationWithSource;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0012\u0010\u0004\u001a\u00020\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H&J\b\u0010\u0007\u001a\u00020\u0003H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\bÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/js/backend/SourceLocationConsumer;", "", "newLine", "", "pushSourceInfo", "info", "Lorg/jetbrains/kotlin/js/backend/ast/JsLocationWithSource;", "popSourceInfo", "org.jetbrains.kotlin:js.ast"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface SourceLocationConsumer {
    void newLine();

    void popSourceInfo();

    void pushSourceInfo(JsLocationWithSource info);
}
