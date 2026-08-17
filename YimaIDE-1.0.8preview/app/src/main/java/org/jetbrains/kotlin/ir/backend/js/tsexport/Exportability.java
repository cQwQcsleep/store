package org.jetbrains.kotlin.ir.backend.js.tsexport;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0004\u0005\u0006\u0007B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0004\b\t\n\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/Exportability;", "", "<init>", "()V", "Allowed", "NotNeeded", "Implicit", "Prohibited", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/Exportability$Allowed;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/Exportability$Implicit;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/Exportability$NotNeeded;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/Exportability$Prohibited;", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class Exportability {

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/Exportability$Allowed;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/Exportability;", "<init>", "()V", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Allowed extends Exportability {
        public static final Allowed INSTANCE = new Allowed();

        private Allowed() {
            super(null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/Exportability$Implicit;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/Exportability;", "<init>", "()V", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Implicit extends Exportability {
        public static final Implicit INSTANCE = new Implicit();

        private Implicit() {
            super(null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/Exportability$NotNeeded;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/Exportability;", "<init>", "()V", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class NotNeeded extends Exportability {
        public static final NotNeeded INSTANCE = new NotNeeded();

        private NotNeeded() {
            super(null);
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/Exportability$Prohibited;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/Exportability;", "reason", "", "<init>", "(Ljava/lang/String;)V", "getReason", "()Ljava/lang/String;", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Prohibited extends Exportability {
        private final String reason;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Prohibited(String str) {
            super(null);
            str.getClass();
            this.reason = str;
        }

        public final String getReason() {
            return this.reason;
        }
    }

    public /* synthetic */ Exportability(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private Exportability() {
    }
}
