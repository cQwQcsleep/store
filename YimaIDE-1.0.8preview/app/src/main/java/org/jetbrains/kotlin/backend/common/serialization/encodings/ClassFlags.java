package org.jetbrains.kotlin.backend.common.serialization.encodings;

import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.eclipse.jdt.internal.compiler.util.Util;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.DescriptorVisibility;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.metadata.deserialization.Flags;
import org.jetbrains.kotlin.serialization.deserialization.ProtoEnumFlags;
import org.jetbrains.kotlin.serialization.deserialization.ProtoEnumFlagsUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0015\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0087@\u0018\u0000 22\u00020\u0001:\u00012B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010&\u001a\u00020\u00152\b\u0010'\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004¢\u0006\u0004\b(\u0010)J\u0011\u0010*\u001a\u00020+HÖ\u0081\u0004¢\u0006\u0004\b,\u0010-J\u0011\u0010.\u001a\u00020/HÖ\u0081\u0004¢\u0006\u0004\b0\u00101R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0018\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0017R\u0011\u0010\u001a\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0017R\u0011\u0010\u001c\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0017R\u0011\u0010\u001e\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0017R\u0011\u0010 \u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b!\u0010\u0017R\u0011\u0010\"\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b#\u0010\u0017R\u0011\u0010$\u001a\u00020\u00158F¢\u0006\u0006\u001a\u0004\b%\u0010\u0017\u0088\u0001\u0002¨\u00063"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/serialization/encodings/ClassFlags;", "", "flags", "", "constructor-impl", "(J)J", "getFlags", "()J", "modality", "Lorg/jetbrains/kotlin/descriptors/Modality;", "getModality-impl", "(J)Lorg/jetbrains/kotlin/descriptors/Modality;", "visibility", "Lorg/jetbrains/kotlin/descriptors/DescriptorVisibility;", "getVisibility-impl", "(J)Lorg/jetbrains/kotlin/descriptors/DescriptorVisibility;", "kind", "Lorg/jetbrains/kotlin/descriptors/ClassKind;", "getKind-impl", "(J)Lorg/jetbrains/kotlin/descriptors/ClassKind;", "isCompanion", "", "isCompanion-impl", "(J)Z", "isInner", "isInner-impl", "isData", "isData-impl", "isValue", "isValue-impl", "isExpect", "isExpect-impl", "isExternal", "isExternal-impl", "isFun", "isFun-impl", "hasEnumEntries", "getHasEnumEntries-impl", "equals", "other", "equals-impl", "(JLjava/lang/Object;)Z", "hashCode", "", "hashCode-impl", "(J)I", "toString", "", "toString-impl", "(J)Ljava/lang/String;", "Companion", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
@JvmInline
public final class ClassFlags {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final long flags;

    private /* synthetic */ ClassFlags(long j) {
        this.flags = j;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ ClassFlags m307boximpl(long j) {
        return new ClassFlags(j);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m308constructorimpl(long j) {
        return j;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m309equalsimpl(long j, Object obj) {
        return (obj instanceof ClassFlags) && j == ((ClassFlags) obj).m324unboximpl();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m310equalsimpl0(long j, long j2) {
        return j == j2;
    }

    /* JADX INFO: renamed from: getHasEnumEntries-impl, reason: not valid java name */
    public static final boolean m311getHasEnumEntriesimpl(long j) {
        Boolean bool = Flags.HAS_ENUM_ENTRIES.get((int) j);
        bool.getClass();
        return bool.booleanValue();
    }

    /* JADX INFO: renamed from: getKind-impl, reason: not valid java name */
    public static final ClassKind m312getKindimpl(long j) {
        return ProtoEnumFlags.INSTANCE.classKind((ProtoBuf.Class.Kind) Flags.CLASS_KIND.get((int) j));
    }

    /* JADX INFO: renamed from: getModality-impl, reason: not valid java name */
    public static final Modality m313getModalityimpl(long j) {
        return ProtoEnumFlags.INSTANCE.modality((ProtoBuf.Modality) Flags.MODALITY.get((int) j));
    }

    /* JADX INFO: renamed from: getVisibility-impl, reason: not valid java name */
    public static final DescriptorVisibility m314getVisibilityimpl(long j) {
        return ProtoEnumFlagsUtilsKt.descriptorVisibility(ProtoEnumFlags.INSTANCE, (ProtoBuf.Visibility) Flags.VISIBILITY.get((int) j));
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m315hashCodeimpl(long j) {
        return Long.hashCode(j);
    }

    /* JADX INFO: renamed from: isCompanion-impl, reason: not valid java name */
    public static final boolean m316isCompanionimpl(long j) {
        return Flags.CLASS_KIND.get((int) j) == ProtoBuf.Class.Kind.COMPANION_OBJECT;
    }

    /* JADX INFO: renamed from: isData-impl, reason: not valid java name */
    public static final boolean m317isDataimpl(long j) {
        Boolean bool = Flags.IS_DATA.get((int) j);
        bool.getClass();
        return bool.booleanValue();
    }

    /* JADX INFO: renamed from: isExpect-impl, reason: not valid java name */
    public static final boolean m318isExpectimpl(long j) {
        Boolean bool = Flags.IS_EXPECT_CLASS.get((int) j);
        bool.getClass();
        return bool.booleanValue();
    }

    /* JADX INFO: renamed from: isExternal-impl, reason: not valid java name */
    public static final boolean m319isExternalimpl(long j) {
        Boolean bool = Flags.IS_EXTERNAL_CLASS.get((int) j);
        bool.getClass();
        return bool.booleanValue();
    }

    /* JADX INFO: renamed from: isFun-impl, reason: not valid java name */
    public static final boolean m320isFunimpl(long j) {
        Boolean bool = Flags.IS_FUN_INTERFACE.get((int) j);
        bool.getClass();
        return bool.booleanValue();
    }

    /* JADX INFO: renamed from: isInner-impl, reason: not valid java name */
    public static final boolean m321isInnerimpl(long j) {
        Boolean bool = Flags.IS_INNER.get((int) j);
        bool.getClass();
        return bool.booleanValue();
    }

    /* JADX INFO: renamed from: isValue-impl, reason: not valid java name */
    public static final boolean m322isValueimpl(long j) {
        Boolean bool = Flags.IS_VALUE_CLASS.get((int) j);
        bool.getClass();
        return bool.booleanValue();
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m323toStringimpl(long j) {
        return "ClassFlags(flags=" + j + Util.C_PARAM_END;
    }

    public boolean equals(Object obj) {
        return m309equalsimpl(this.flags, obj);
    }

    public final long getFlags() {
        return this.flags;
    }

    public int hashCode() {
        return m315hashCodeimpl(this.flags);
    }

    public String toString() {
        return m323toStringimpl(this.flags);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ long m324unboximpl() {
        return this.flags;
    }
}
