package org.jetbrains.kotlin.cli.common.modules;

import java.util.Collections;
import java.util.List;
import org.jetbrains.kotlin.modules.Module;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public class ModuleChunk {
    public static final ModuleChunk EMPTY = new ModuleChunk(Collections.EMPTY_LIST);
    private final List<Module> modules;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = i != 1 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i != 1 ? 3 : 2];
        if (i != 1) {
            objArr[0] = ModuleXmlParser.MODULES;
        } else {
            objArr[0] = "org/jetbrains/kotlin/cli/common/modules/ModuleChunk";
        }
        if (i != 1) {
            objArr[1] = "org/jetbrains/kotlin/cli/common/modules/ModuleChunk";
        } else {
            objArr[1] = "getModules";
        }
        if (i != 1) {
            objArr[2] = "<init>";
        }
        String str2 = String.format(str, objArr);
        if (i == 1) {
            throw new IllegalStateException(str2);
        }
    }

    public ModuleChunk(List<Module> list) {
        if (list == null) {
            $$$reportNull$$$0(0);
        }
        this.modules = list;
    }

    public List<Module> getModules() {
        List<Module> list = this.modules;
        if (list == null) {
            $$$reportNull$$$0(1);
        }
        return list;
    }
}
