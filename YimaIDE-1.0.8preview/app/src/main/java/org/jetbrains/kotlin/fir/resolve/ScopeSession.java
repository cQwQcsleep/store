package org.jetbrains.kotlin.fir.resolve;

import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.util.PrivateForInline;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0080\u0001\u0010\u0004\u001av\u0012\u0004\u0012\u00020\u0001\u00120\u0012.\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0006\u0012\u0004\u0012\u00020\u00010\u0005j\u0016\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0006\u0012\u0004\u0012\u00020\u0001`\u00070\u0005j:\u0012\u0004\u0012\u00020\u0001\u00120\u0012.\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0006\u0012\u0004\u0012\u00020\u00010\u0005j\u0016\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0006\u0012\u0004\u0012\u00020\u0001`\u0007`\u0007H\u0007b\u0002\b\bJS\u0010\t\u001a\u0002H\n\"\n\b\u0000\u0010\u000b\u0018\u0001*\u00020\u0001\"\n\b\u0001\u0010\n\u0018\u0001*\u00020\u00012\u0006\u0010\f\u001a\u0002H\u000b2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u0002H\n0\u00062\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\n0\u000fH\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\u0010R\u0082\u0001\u0010\u0004\u001av\u0012\u0004\u0012\u00020\u0001\u00120\u0012.\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0006\u0012\u0004\u0012\u00020\u00010\u0005j\u0016\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0006\u0012\u0004\u0012\u00020\u0001`\u00070\u0005j:\u0012\u0004\u0012\u00020\u0001\u00120\u0012.\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0006\u0012\u0004\u0012\u00020\u00010\u0005j\u0016\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0006\u0012\u0004\u0012\u00020\u0001`\u0007`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", Argument.Delimiters.none, "<init>", "()V", "scopes", "Ljava/util/HashMap;", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSessionKey;", "Lkotlin/collections/HashMap;", "Lorg/jetbrains/kotlin/util/PrivateForInline;", "getOrBuild", "FS", "ID", "id", "key", "build", "Lkotlin/Function0;", "(Ljava/lang/Object;Lorg/jetbrains/kotlin/fir/resolve/ScopeSessionKey;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ScopeSession {
    private final HashMap<Object, HashMap<ScopeSessionKey<?, ?>, Object>> scopes = new HashMap<>();

    public final /* synthetic */ <ID, FS> FS getOrBuild(ID id, ScopeSessionKey<ID, FS> key, Function0<? extends FS> build) {
        id.getClass();
        key.getClass();
        build.getClass();
        HashMap<Object, HashMap<ScopeSessionKey<?, ?>, Object>> mapScopes = scopes();
        HashMap<ScopeSessionKey<?, ?>, Object> map = mapScopes.get(id);
        if (map == null) {
            map = new HashMap<>();
            mapScopes.put(id, map);
        }
        HashMap<ScopeSessionKey<?, ?>, Object> map2 = map;
        FS fs = (FS) map2.get(key);
        if (fs == null) {
            fs = (FS) build.invoke();
            map2.put(key, fs);
        }
        Intrinsics.reifiedOperationMarker(1, "FS");
        return fs;
    }

    @PrivateForInline
    public final HashMap<Object, HashMap<ScopeSessionKey<?, ?>, Object>> scopes() {
        return this.scopes;
    }
}
