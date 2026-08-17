package org.jetbrains.kotlin.config;

import java.util.Iterator;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u001a\u0014\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001\u001a\u0012\u0010\u0004\u001a\u00020\u0005*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001¨\u0006\u0006"}, d2 = {"getModuleNameForSource", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/config/HmppCliModuleStructure;", "source", "isFromCommonModule", Argument.Delimiters.none, "org.jetbrains.kotlin:config"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class HmppCliModuleKt {
    public static final String getModuleNameForSource(HmppCliModuleStructure hmppCliModuleStructure, String str) {
        Object next;
        hmppCliModuleStructure.getClass();
        str.getClass();
        Iterator<T> it = hmppCliModuleStructure.getModules().iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!((HmppCliModule) next).getSources().contains(str));
        HmppCliModule hmppCliModule = (HmppCliModule) next;
        if (hmppCliModule != null) {
            return hmppCliModule.getName();
        }
        return null;
    }

    public static final boolean isFromCommonModule(HmppCliModuleStructure hmppCliModuleStructure, String str) {
        hmppCliModuleStructure.getClass();
        str.getClass();
        Iterator<HmppCliModule> it = hmppCliModuleStructure.getModules().iterator();
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            }
            if (it.next().getSources().contains(str)) {
                break;
            }
            i++;
        }
        return i >= 0 && i < hmppCliModuleStructure.getModules().size() - 1;
    }
}
