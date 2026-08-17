package org.jetbrains.kotlin.js.backend;

import kotlin.Metadata;
import org.jetbrains.kotlin.js.backend.ast.JsLocationWithSource;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u0012\u0010\u0006\u001a\u00020\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\b\u0010\t\u001a\u00020\u0005H\u0016¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/js/backend/NoOpSourceLocationConsumer;", "Lorg/jetbrains/kotlin/js/backend/SourceLocationConsumer;", "<init>", "()V", "newLine", "", "pushSourceInfo", "info", "Lorg/jetbrains/kotlin/js/backend/ast/JsLocationWithSource;", "popSourceInfo", "org.jetbrains.kotlin:js.ast"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class NoOpSourceLocationConsumer implements SourceLocationConsumer {
    public static final NoOpSourceLocationConsumer INSTANCE = new NoOpSourceLocationConsumer();

    private NoOpSourceLocationConsumer() {
    }

    @Override // org.jetbrains.kotlin.js.backend.SourceLocationConsumer
    public void newLine() {
    }

    @Override // org.jetbrains.kotlin.js.backend.SourceLocationConsumer
    public void popSourceInfo() {
    }

    @Override // org.jetbrains.kotlin.js.backend.SourceLocationConsumer
    public void pushSourceInfo(JsLocationWithSource info) {
    }
}
