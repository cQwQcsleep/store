package org.jetbrains.kotlin.cli.jvm.modules;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.FunctionReferenceImpl;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.resolve.jvm.modules.JavaModule;
import org.jetbrains.kotlin.resolve.jvm.modules.JavaModuleFinder;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* synthetic */ class JavaModuleGraph$module$1 extends FunctionReferenceImpl implements Function1<String, JavaModule> {
    public JavaModuleGraph$module$1(Object obj) {
        super(1, obj, JavaModuleFinder.class, "findModule", "findModule(Ljava/lang/String;)Lorg/jetbrains/kotlin/resolve/jvm/modules/JavaModule;", 0);
    }

    public final JavaModule invoke(String str) {
        str.getClass();
        return ((JavaModuleFinder) ((CallableReference) this).receiver).findModule(str);
    }
}
