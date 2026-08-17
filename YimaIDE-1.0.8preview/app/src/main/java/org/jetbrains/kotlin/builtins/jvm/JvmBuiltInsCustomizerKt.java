package org.jetbrains.kotlin.builtins.jvm;

import kotlin.Metadata;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0003"}, d2 = {"GET_FIRST_LIST_NAME", "Lorg/jetbrains/kotlin/name/Name;", "GET_LAST_LIST_NAME", "org.jetbrains.kotlin:descriptors.jvm"}, k = 2, mv = {2, 4, 0}, xi = 48)
public final class JvmBuiltInsCustomizerKt {
    private static final Name GET_FIRST_LIST_NAME;
    private static final Name GET_LAST_LIST_NAME;

    static {
        Name nameIdentifier = Name.identifier("getFirst");
        nameIdentifier.getClass();
        GET_FIRST_LIST_NAME = nameIdentifier;
        Name nameIdentifier2 = Name.identifier("getLast");
        nameIdentifier2.getClass();
        GET_LAST_LIST_NAME = nameIdentifier2;
    }
}
