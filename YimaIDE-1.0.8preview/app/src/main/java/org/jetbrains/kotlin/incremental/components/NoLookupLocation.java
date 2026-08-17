package org.jetbrains.kotlin.incremental.components;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0081\u0002\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004R\u0016\u0010\u001c\u001a\u0004\u0018\u00010\u001d8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001fj\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001b¨\u0006 "}, d2 = {"Lorg/jetbrains/kotlin/incremental/components/NoLookupLocation;", "Lorg/jetbrains/kotlin/incremental/components/LookupLocation;", "", "<init>", "(Ljava/lang/String;I)V", "FROM_IDE", "FROM_BACKEND", "FROM_TEST", "FROM_BUILTINS", "WHEN_CHECK_DECLARATION_CONFLICTS", "WHEN_CHECK_OVERRIDES", "FOR_SCRIPT", "FROM_REFLECTION", "WHEN_RESOLVE_DECLARATION", "WHEN_GET_DECLARATION_SCOPE", "WHEN_RESOLVING_DEFAULT_TYPE_ARGUMENTS", "FOR_ALREADY_TRACKED", "WHEN_GET_ALL_DESCRIPTORS", "WHEN_TYPING", "WHEN_GET_SUPER_MEMBERS", "FOR_NON_TRACKED_SCOPE", "FROM_SYNTHETIC_SCOPE", "FROM_DESERIALIZATION", "FROM_JAVA_LOADER", "WHEN_GET_LOCAL_VARIABLE", "WHEN_FIND_BY_FQNAME", "WHEN_GET_COMPANION_OBJECT", "FOR_DEFAULT_IMPORTS", "location", "Lorg/jetbrains/kotlin/incremental/components/LocationInfo;", "getLocation", "()Lorg/jetbrains/kotlin/incremental/components/LocationInfo;", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
public enum NoLookupLocation implements LookupLocation {
    FROM_IDE,
    FROM_BACKEND,
    FROM_TEST,
    FROM_BUILTINS,
    WHEN_CHECK_DECLARATION_CONFLICTS,
    WHEN_CHECK_OVERRIDES,
    FOR_SCRIPT,
    FROM_REFLECTION,
    WHEN_RESOLVE_DECLARATION,
    WHEN_GET_DECLARATION_SCOPE,
    WHEN_RESOLVING_DEFAULT_TYPE_ARGUMENTS,
    FOR_ALREADY_TRACKED,
    WHEN_GET_ALL_DESCRIPTORS,
    WHEN_TYPING,
    WHEN_GET_SUPER_MEMBERS,
    FOR_NON_TRACKED_SCOPE,
    FROM_SYNTHETIC_SCOPE,
    FROM_DESERIALIZATION,
    FROM_JAVA_LOADER,
    WHEN_GET_LOCAL_VARIABLE,
    WHEN_FIND_BY_FQNAME,
    WHEN_GET_COMPANION_OBJECT,
    FOR_DEFAULT_IMPORTS;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    public static EnumEntries<NoLookupLocation> getEntries() {
        return $ENTRIES;
    }

    public LocationInfo getLocation() {
        return null;
    }
}
