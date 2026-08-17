package org.jetbrains.kotlin.ir.backend.js.tsexport;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0004\u0005B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0002\u0006\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedAttribute;", "", "<init>", "()V", "DeprecatedAttribute", "DefaultExport", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedAttribute$DefaultExport;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedAttribute$DeprecatedAttribute;", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class ExportedAttribute {

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedAttribute$DefaultExport;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedAttribute;", "<init>", "()V", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class DefaultExport extends ExportedAttribute {
        public static final DefaultExport INSTANCE = new DefaultExport();

        private DefaultExport() {
            super(null);
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedAttribute$DeprecatedAttribute;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedAttribute;", "message", "", "<init>", "(Ljava/lang/String;)V", "getMessage", "()Ljava/lang/String;", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class DeprecatedAttribute extends ExportedAttribute {
        private final String message;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public DeprecatedAttribute(String str) {
            super(null);
            str.getClass();
            this.message = str;
        }

        public final String getMessage() {
            return this.message;
        }
    }

    public /* synthetic */ ExportedAttribute(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private ExportedAttribute() {
    }
}
