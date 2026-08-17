package org.jetbrains.kotlin.cli.common.config;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.CLIConfigurationKeys;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a*\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0004H\u0007\u001a\u0018\u0010\b\u001a\u00020\u0001*\u00020\u00022\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\n\"\u001b\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\n*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"addKotlinSourceRoot", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", ModuleXmlParser.PATH, Argument.Delimiters.none, "isCommon", Argument.Delimiters.none, "hmppModuleName", "addKotlinSourceRoots", ModuleXmlParser.SOURCES, Argument.Delimiters.none, "kotlinSourceRoots", "Lorg/jetbrains/kotlin/cli/common/config/KotlinSourceRoot;", "getKotlinSourceRoots", "(Lorg/jetbrains/kotlin/config/CompilerConfiguration;)Ljava/util/List;", "org.jetbrains.kotlin:cli-base"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ContentRootsKt {
    public static final void addKotlinSourceRoot(CompilerConfiguration compilerConfiguration, String str, boolean z, String str2) {
        compilerConfiguration.getClass();
        str.getClass();
        compilerConfiguration.add(CLIConfigurationKeys.CONTENT_ROOTS, new KotlinSourceRoot(str, z, str2));
    }

    public static /* synthetic */ void addKotlinSourceRoot$default(CompilerConfiguration compilerConfiguration, String str, boolean z, String str2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            str2 = null;
        }
        addKotlinSourceRoot(compilerConfiguration, str, z, str2);
    }

    public static final void addKotlinSourceRoots(CompilerConfiguration compilerConfiguration, List<String> list) {
        compilerConfiguration.getClass();
        list.getClass();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            addKotlinSourceRoot$default(compilerConfiguration, (String) it.next(), false, null, 6, null);
        }
    }

    public static final List<KotlinSourceRoot> getKotlinSourceRoots(CompilerConfiguration compilerConfiguration) {
        ArrayList arrayList;
        compilerConfiguration.getClass();
        List list = (List) compilerConfiguration.get(CLIConfigurationKeys.CONTENT_ROOTS);
        if (list != null) {
            arrayList = new ArrayList();
            for (Object obj : list) {
                if (obj instanceof KotlinSourceRoot) {
                    arrayList.add(obj);
                }
            }
        } else {
            arrayList = null;
        }
        return arrayList == null ? CollectionsKt.emptyList() : arrayList;
    }

    public static final void addKotlinSourceRoot(CompilerConfiguration compilerConfiguration, String str, boolean z) {
        compilerConfiguration.getClass();
        str.getClass();
        addKotlinSourceRoot$default(compilerConfiguration, str, z, null, 4, null);
    }

    public static final void addKotlinSourceRoot(CompilerConfiguration compilerConfiguration, String str) {
        compilerConfiguration.getClass();
        str.getClass();
        addKotlinSourceRoot$default(compilerConfiguration, str, false, null, 6, null);
    }
}
