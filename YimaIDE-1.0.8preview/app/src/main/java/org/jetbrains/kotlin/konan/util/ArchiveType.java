package org.jetbrains.kotlin.konan.util;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v0 org.jetbrains.kotlin.konan.util.ArchiveType, still in use, count: 1, list:
  (r0v0 org.jetbrains.kotlin.konan.util.ArchiveType) from ?: TERNARY null = ((wrap boolean:0x0032: INVOKE 
  (r2v5 java.lang.String)
  ("Windows")
  false
  (2 int)
  (wrap java.lang.Object:?: CAST (java.lang.Object) (null java.lang.Object))
 STATIC call: kotlin.text.StringsKt.startsWith$default(java.lang.String, java.lang.String, boolean, int, java.lang.Object):boolean A[WRAPPED]) == true) ? (r0v0 org.jetbrains.kotlin.konan.util.ArchiveType) : (r1v1 org.jetbrains.kotlin.konan.util.ArchiveType)
	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:164)
	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:129)
	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:101)
	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:100)
	at jadx.core.utils.InsnRemover.removeAllAndUnbind(InsnRemover.java:257)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:187)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\u0001\u0018\u0000 \t2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\tB\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/konan/util/ArchiveType;", "", "fileExtension", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getFileExtension", "()Ljava/lang/String;", "ZIP", "TAR_GZ", "Companion", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ArchiveType {
    ZIP("zip"),
    TAR_GZ("tar.gz");


    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final ArchiveType systemDefault;
    private final String fileExtension;

    static {
        String property = System.getProperty("os.name");
        property.getClass();
        systemDefault = StringsKt.startsWith$default(property, "Windows", false, 2, (Object) null) ? archiveType : archiveType;
    }

    private ArchiveType(String str) {
        super(str, i);
        this.fileExtension = str;
    }

    public static ArchiveType valueOf(String str) {
        return (ArchiveType) Enum.valueOf(ArchiveType.class, str);
    }

    public static ArchiveType[] values() {
        return (ArchiveType[]) $VALUES.clone();
    }

    public final String getFileExtension() {
        return this.fileExtension;
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/konan/util/ArchiveType$Companion;", "", "()V", "systemDefault", "Lorg/jetbrains/kotlin/konan/util/ArchiveType;", "getSystemDefault", "()Lorg/jetbrains/kotlin/konan/util/ArchiveType;", "kotlin-native-utils"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final ArchiveType getSystemDefault() {
            return ArchiveType.systemDefault;
        }

        private Companion() {
        }
    }
}
