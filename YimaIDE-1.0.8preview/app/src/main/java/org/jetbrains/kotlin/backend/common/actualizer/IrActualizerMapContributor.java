package org.jetbrains.kotlin.backend.common.actualizer;

import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.ir.symbols.IrSymbol;
import org.jetbrains.kotlin.ir.symbols.IrTypeAliasSymbol;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001:\u0001\rB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H&J\u0014\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u0007H&J\u0012\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH&¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/actualizer/IrActualizerMapContributor;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "collectClassesMap", "Lorg/jetbrains/kotlin/backend/common/actualizer/IrActualizerMapContributor$ActualClassInfo;", "collectTopLevelCallablesMap", "", "Lorg/jetbrains/kotlin/ir/symbols/IrSymbol;", "actualizeClass", "Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "ActualClassInfo", "org.jetbrains.kotlin:ir.actualization"}, k = 1, mv = {2, 4, 0}, xi = 48)
public abstract class IrActualizerMapContributor {

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B/\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0003¢\u0006\u0004\b\b\u0010\tR\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001d\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/actualizer/IrActualizerMapContributor$ActualClassInfo;", "", "classMapping", "", "Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;", "actualTypeAliases", "Lorg/jetbrains/kotlin/name/ClassId;", "Lorg/jetbrains/kotlin/ir/symbols/IrTypeAliasSymbol;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/util/Map;Ljava/util/Map;)V", "getClassMapping", "()Ljava/util/Map;", "getActualTypeAliases", "org.jetbrains.kotlin:ir.actualization"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class ActualClassInfo {
        private final Map<ClassId, IrTypeAliasSymbol> actualTypeAliases;
        private final Map<IrClassSymbol, IrClassSymbol> classMapping;

        public ActualClassInfo(Map<IrClassSymbol, ? extends IrClassSymbol> map, Map<ClassId, ? extends IrTypeAliasSymbol> map2) {
            map.getClass();
            map2.getClass();
            this.classMapping = map;
            this.actualTypeAliases = map2;
        }

        public final Map<ClassId, IrTypeAliasSymbol> getActualTypeAliases() {
            return this.actualTypeAliases;
        }

        public final Map<IrClassSymbol, IrClassSymbol> getClassMapping() {
            return this.classMapping;
        }
    }

    public abstract IrClassSymbol actualizeClass(ClassId classId);

    public abstract ActualClassInfo collectClassesMap();

    public abstract Map<IrSymbol, IrSymbol> collectTopLevelCallablesMap();
}
