package org.jetbrains.kotlin.incremental.components;

import kotlin.Metadata;
import org.jetbrains.kotlin.container.DefaultImplementation;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@DefaultImplementation(impl = DO_NOTHING.class)
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bg\u0018\u00002\u00020\u0001:\u0001\u0011J0\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\tH&J\b\u0010\u0010\u001a\u00020\u0007H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0012À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/incremental/components/LookupTracker;", "", "requiresPosition", "", "getRequiresPosition", "()Z", "record", "", "filePath", "", "position", "Lorg/jetbrains/kotlin/incremental/components/Position;", "scopeFqName", "scopeKind", "Lorg/jetbrains/kotlin/incremental/components/ScopeKind;", "name", "clear", "DO_NOTHING", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface LookupTracker {

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J0\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000bH\u0016J\b\u0010\u0012\u001a\u00020\tH\u0016R\u0014\u0010\u0004\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/incremental/components/LookupTracker$DO_NOTHING;", "Lorg/jetbrains/kotlin/incremental/components/LookupTracker;", "<init>", "()V", "requiresPosition", "", "getRequiresPosition", "()Z", "record", "", "filePath", "", "position", "Lorg/jetbrains/kotlin/incremental/components/Position;", "scopeFqName", "scopeKind", "Lorg/jetbrains/kotlin/incremental/components/ScopeKind;", "name", "clear", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class DO_NOTHING implements LookupTracker {
        public static final DO_NOTHING INSTANCE = new DO_NOTHING();

        private DO_NOTHING() {
        }

        @Override // org.jetbrains.kotlin.incremental.components.LookupTracker
        public void clear() {
        }

        @Override // org.jetbrains.kotlin.incremental.components.LookupTracker
        public boolean getRequiresPosition() {
            return false;
        }

        @Override // org.jetbrains.kotlin.incremental.components.LookupTracker
        public void record(String filePath, Position position, String scopeFqName, ScopeKind scopeKind, String name) {
            filePath.getClass();
            position.getClass();
            scopeFqName.getClass();
            scopeKind.getClass();
            name.getClass();
        }
    }

    void clear();

    boolean getRequiresPosition();

    void record(String filePath, Position position, String scopeFqName, ScopeKind scopeKind, String name);
}
