package org.jetbrains.kotlin.incremental.components;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001:\u0001\tJ(\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/incremental/components/InlineConstTracker;", "", "report", "", "filePath", "", "owner", "name", "constType", "DoNothing", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface InlineConstTracker {

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J(\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0007H\u0016¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/incremental/components/InlineConstTracker$DoNothing;", "Lorg/jetbrains/kotlin/incremental/components/InlineConstTracker;", "<init>", "()V", "report", "", "filePath", "", "owner", "name", "constType", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class DoNothing implements InlineConstTracker {
        public static final DoNothing INSTANCE = new DoNothing();

        private DoNothing() {
        }

        @Override // org.jetbrains.kotlin.incremental.components.InlineConstTracker
        public void report(String filePath, String owner, String name, String constType) {
            filePath.getClass();
            owner.getClass();
            name.getClass();
            constType.getClass();
        }
    }

    void report(String filePath, String owner, String name, String constType);
}
