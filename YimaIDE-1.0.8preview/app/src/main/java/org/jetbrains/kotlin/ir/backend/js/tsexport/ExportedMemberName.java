package org.jetbrains.kotlin.ir.backend.js.tsexport;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u0000 \b2\u00020\u0001:\u0003\u0006\u0007\bR\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005\u0082\u0001\u0002\t\nø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000bÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedMemberName;", "", "value", "", "getValue", "()Ljava/lang/String;", "Identifier", "SymbolReference", "Companion", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedMemberName$Identifier;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedMemberName$SymbolReference;", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface ExportedMemberName {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedMemberName$Companion;", "", "<init>", "()V", "WellKnownSymbol", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedMemberName$SymbolReference;", "value", "", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        public final SymbolReference WellKnownSymbol(String value) {
            value.getClass();
            return new SymbolReference("Symbol." + value);
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedMemberName$Identifier;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedMemberName;", "value", "", "<init>", "(Ljava/lang/String;)V", "getValue", "()Ljava/lang/String;", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Identifier implements ExportedMemberName {
        private final String value;

        public Identifier(String str) {
            str.getClass();
            this.value = str;
        }

        @Override // org.jetbrains.kotlin.ir.backend.js.tsexport.ExportedMemberName
        public String getValue() {
            return this.value;
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedMemberName$SymbolReference;", "Lorg/jetbrains/kotlin/ir/backend/js/tsexport/ExportedMemberName;", "value", "", "<init>", "(Ljava/lang/String;)V", "getValue", "()Ljava/lang/String;", "org.jetbrains.kotlin:typescript-export-model"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class SymbolReference implements ExportedMemberName {
        private final String value;

        public SymbolReference(String str) {
            str.getClass();
            this.value = str;
        }

        @Override // org.jetbrains.kotlin.ir.backend.js.tsexport.ExportedMemberName
        public String getValue() {
            return this.value;
        }
    }

    String getValue();
}
