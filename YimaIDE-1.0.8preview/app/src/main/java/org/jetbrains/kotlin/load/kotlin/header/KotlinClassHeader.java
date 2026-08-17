package org.jetbrains.kotlin.load.kotlin.header;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt;
import org.jetbrains.kotlin.metadata.deserialization.MetadataVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0013\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\t\u0018\u00002\u00020\u0001:\u000223Bc\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\u000f\u0010\u0010J\n\u0010/\u001a\u00020\bH\u0096\u0080\u0004J\u0014\u00100\u001a\u00020+*\u00020\r2\u0006\u00101\u001a\u00020\rH\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001b\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0015\u0010\u0016R\u001b\u0010\t\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0018\u0010\u0016R\u001b\u0010\n\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0019\u0010\u0016R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0013\u0010\u000e\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0013\u0010\u001e\u001a\u0004\u0018\u00010\b8F¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u001dR\u0017\u0010 \u001a\b\u0012\u0004\u0012\u00020\b0!8F¢\u0006\u0006\u001a\u0004\b\"\u0010#R\u0019\u0010$\u001a\u0004\u0018\u00010%8F¢\u0006\f\u0012\u0004\b&\u0010'\u001a\u0004\b(\u0010)R\u0011\u0010*\u001a\u00020+8F¢\u0006\u0006\u001a\u0004\b*\u0010,R\u0011\u0010-\u001a\u00020+8F¢\u0006\u0006\u001a\u0004\b-\u0010,R\u0011\u0010.\u001a\u00020+8F¢\u0006\u0006\u001a\u0004\b.\u0010,¨\u00064"}, d2 = {"Lorg/jetbrains/kotlin/load/kotlin/header/KotlinClassHeader;", "", "kind", "Lorg/jetbrains/kotlin/load/kotlin/header/KotlinClassHeader$Kind;", "metadataVersion", "Lorg/jetbrains/kotlin/metadata/deserialization/MetadataVersion;", "data", "", "", "incompatibleData", "strings", "extraString", "extraInt", "", "packageName", "<init>", "(Lorg/jetbrains/kotlin/load/kotlin/header/KotlinClassHeader$Kind;Lorg/jetbrains/kotlin/metadata/deserialization/MetadataVersion;[Ljava/lang/String;[Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;)V", "getKind", "()Lorg/jetbrains/kotlin/load/kotlin/header/KotlinClassHeader$Kind;", "getMetadataVersion", "()Lorg/jetbrains/kotlin/metadata/deserialization/MetadataVersion;", "getData", "()[Ljava/lang/String;", "[Ljava/lang/String;", "getIncompatibleData", "getStrings", "getExtraInt", "()I", "getPackageName", "()Ljava/lang/String;", "multifileClassName", "getMultifileClassName", "multifilePartNames", "", "getMultifilePartNames", "()Ljava/util/List;", "multifileClassKind", "Lorg/jetbrains/kotlin/load/kotlin/header/KotlinClassHeader$MultifileClassKind;", "getMultifileClassKind$annotations", "()V", "getMultifileClassKind", "()Lorg/jetbrains/kotlin/load/kotlin/header/KotlinClassHeader$MultifileClassKind;", "isUnstableJvmIrBinary", "", "()Z", "isPreRelease", "isScript", "toString", "has", "flag", "Kind", "MultifileClassKind", "org.jetbrains.kotlin:deserialization.common.jvm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class KotlinClassHeader {
    private final String[] data;
    private final int extraInt;
    private final String extraString;
    private final String[] incompatibleData;
    private final Kind kind;
    private final MetadataVersion metadataVersion;
    private final String packageName;
    private final String[] strings;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lorg/jetbrains/kotlin/load/kotlin/header/KotlinClassHeader$MultifileClassKind;", "", "<init>", "(Ljava/lang/String;I)V", "DELEGATING", "INHERITING", "org.jetbrains.kotlin:deserialization.common.jvm"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public enum MultifileClassKind {
        DELEGATING,
        INHERITING;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<MultifileClassKind> getEntries() {
            return $ENTRIES;
        }
    }

    public KotlinClassHeader(Kind kind, MetadataVersion metadataVersion, String[] strArr, String[] strArr2, String[] strArr3, String str, int i, String str2) {
        kind.getClass();
        metadataVersion.getClass();
        this.kind = kind;
        this.metadataVersion = metadataVersion;
        this.data = strArr;
        this.incompatibleData = strArr2;
        this.strings = strArr3;
        this.extraString = str;
        this.extraInt = i;
        this.packageName = str2;
    }

    public static /* synthetic */ void getMultifileClassKind$annotations() {
    }

    private final boolean has(int i, int i2) {
        return (i & i2) != 0;
    }

    public final String[] getData() {
        return this.data;
    }

    public final int getExtraInt() {
        return this.extraInt;
    }

    public final String[] getIncompatibleData() {
        return this.incompatibleData;
    }

    public final Kind getKind() {
        return this.kind;
    }

    public final MetadataVersion getMetadataVersion() {
        return this.metadataVersion;
    }

    public final MultifileClassKind getMultifileClassKind() {
        Kind kind = this.kind;
        if (kind == Kind.MULTIFILE_CLASS || kind == Kind.MULTIFILE_CLASS_PART) {
            return has(this.extraInt, 1) ? MultifileClassKind.INHERITING : MultifileClassKind.DELEGATING;
        }
        return null;
    }

    public final String getMultifileClassName() {
        String str = this.extraString;
        if (this.kind == Kind.MULTIFILE_CLASS_PART) {
            return str;
        }
        return null;
    }

    public final List<String> getMultifilePartNames() {
        String[] strArr = this.data;
        if (this.kind != Kind.MULTIFILE_CLASS) {
            strArr = null;
        }
        List<String> listAsList = strArr != null ? ArraysKt.asList(strArr) : null;
        return listAsList == null ? CollectionsKt.emptyList() : listAsList;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final String[] getStrings() {
        return this.strings;
    }

    public final boolean isPreRelease() {
        return has(this.extraInt, 2);
    }

    public final boolean isScript() {
        return has(this.extraInt, 4);
    }

    public final boolean isUnstableJvmIrBinary() {
        return has(this.extraInt, 16) && !has(this.extraInt, 32);
    }

    public String toString() {
        return this.kind + " version=" + this.metadataVersion;
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\f\b\u0086\u0081\u0002\u0018\u0000 \u000e2\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u000eB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/load/kotlin/header/KotlinClassHeader$Kind;", "", "id", "", "<init>", "(Ljava/lang/String;II)V", "getId", "()I", "UNKNOWN", "CLASS", "FILE_FACADE", "SYNTHETIC_CLASS", "MULTIFILE_CLASS", "MULTIFILE_CLASS_PART", "Companion", "org.jetbrains.kotlin:deserialization.common.jvm"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public enum Kind {
        UNKNOWN(0),
        CLASS(1),
        FILE_FACADE(2),
        SYNTHETIC_CLASS(3),
        MULTIFILE_CLASS(4),
        MULTIFILE_CLASS_PART(5);

        private static final Map<Integer, Kind> entryById;
        private final int id;
        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);

        static {
            Kind[] kindArrValues = values();
            LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(kindArrValues.length), 16));
            for (Kind kind : kindArrValues) {
                linkedHashMap.put(Integer.valueOf(kind.id), kind);
            }
            entryById = linkedHashMap;
        }

        Kind(int i) {
            this.id = i;
        }

        @JvmStatic
        public static final Kind getById(int i) {
            return INSTANCE.getById(i);
        }

        public static EnumEntries<Kind> getEntries() {
            return $ENTRIES;
        }

        public final int getId() {
            return this.id;
        }

        @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0006H\u0007b\u0002\b\nR\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/load/kotlin/header/KotlinClassHeader$Kind$Companion;", "", "<init>", "()V", "entryById", "", "", "Lorg/jetbrains/kotlin/load/kotlin/header/KotlinClassHeader$Kind;", "getById", "id", "Lkotlin/jvm/JvmStatic;", "org.jetbrains.kotlin:deserialization.common.jvm"}, k = 1, mv = {2, 4, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            @JvmStatic
            public final Kind getById(int id) {
                Kind kind = (Kind) Kind.entryById.get(Integer.valueOf(id));
                return kind == null ? Kind.UNKNOWN : kind;
            }

            private Companion() {
            }
        }
    }
}
