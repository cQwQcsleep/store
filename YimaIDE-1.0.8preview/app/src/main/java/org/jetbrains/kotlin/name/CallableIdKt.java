package org.jetbrains.kotlin.name;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\"\u0017\u0010\u0004\u001a\u00020\u0005*\u0004\u0018\u00010\u00018F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007\" \u0010\b\u001a\u00020\t*\u0004\u0018\u00010\u00018FX\u0087\u0004¢\u0006\f\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\f¨\u0006\r"}, d2 = {"withClassId", "Lorg/jetbrains/kotlin/name/CallableId;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "packageName", "Lorg/jetbrains/kotlin/name/FqName;", "getPackageName", "(Lorg/jetbrains/kotlin/name/CallableId;)Lorg/jetbrains/kotlin/name/FqName;", "isLocal", Argument.Delimiters.none, "isLocal$annotations", "(Lorg/jetbrains/kotlin/name/CallableId;)V", "(Lorg/jetbrains/kotlin/name/CallableId;)Z", "org.jetbrains.kotlin:names"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CallableIdKt {
    public static final FqName getPackageName(CallableId callableId) {
        FqName packageName;
        return (callableId == null || (packageName = callableId.getPackageName()) == null) ? CallableId.Companion.getPACKAGE_FQ_NAME_FOR_LOCAL() : packageName;
    }

    public static final boolean isLocal(CallableId callableId) {
        return callableId == null || callableId.isLocal();
    }

    @ClassIdBasedLocality
    public static /* synthetic */ void isLocal$annotations(CallableId callableId) {
    }

    public static final CallableId withClassId(CallableId callableId, ClassId classId) {
        callableId.getClass();
        classId.getClass();
        return new CallableId(classId, callableId.getCallableName());
    }
}
