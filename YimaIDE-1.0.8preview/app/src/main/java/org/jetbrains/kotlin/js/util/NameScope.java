package org.jetbrains.kotlin.js.util;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001:\u0001\bB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/js/util/NameScope;", "", "<init>", "()V", "isReserved", "", "name", "", "EmptyScope", "org.jetbrains.kotlin:js.ast"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class NameScope {

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/js/util/NameScope$EmptyScope;", "Lorg/jetbrains/kotlin/js/util/NameScope;", "<init>", "()V", "isReserved", "", "name", "", "org.jetbrains.kotlin:js.ast"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class EmptyScope extends NameScope {
        public static final EmptyScope INSTANCE = new EmptyScope();

        private EmptyScope() {
        }

        @Override // org.jetbrains.kotlin.js.util.NameScope
        public boolean isReserved(String name) {
            name.getClass();
            return false;
        }
    }

    public abstract boolean isReserved(String name);
}
