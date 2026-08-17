package org.jetbrains.kotlin.fir.backend;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.ir.declarations.IrClass;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001d\u0012\u0014\b\u0002\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\t\u001a\u00020\u0004H\u0086\u0002J\u0019\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0005H\u0086\u0002R\u001a\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/Fir2IrLocalClassStorage;", Argument.Delimiters.none, "localClassCache", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "<init>", "(Ljava/util/Map;)V", "get", "localClass", "set", Argument.Delimiters.none, "firClass", "irClass", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class Fir2IrLocalClassStorage {
    private final Map<FirClass, IrClass> localClassCache;

    public /* synthetic */ Fir2IrLocalClassStorage(Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new LinkedHashMap() : map);
    }

    public final IrClass get(FirClass localClass) {
        localClass.getClass();
        return this.localClassCache.get(localClass);
    }

    public final void set(FirClass firClass, IrClass irClass) {
        firClass.getClass();
        irClass.getClass();
        this.localClassCache.put(firClass, irClass);
    }

    public Fir2IrLocalClassStorage(Map<FirClass, IrClass> map) {
        map.getClass();
        this.localClassCache = map;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public Fir2IrLocalClassStorage() {
        Map map = null;
        this(map, 1, map);
    }
}
