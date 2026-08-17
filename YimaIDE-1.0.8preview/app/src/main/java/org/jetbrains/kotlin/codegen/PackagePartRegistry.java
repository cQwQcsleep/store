package org.jetbrains.kotlin.codegen;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.PackagePartRegistry;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.metadata.jvm.deserialization.PackageParts;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eR\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/codegen/PackagePartRegistry;", Argument.Delimiters.none, "<init>", "()V", "parts", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/FqName;", "Lorg/jetbrains/kotlin/metadata/jvm/deserialization/PackageParts;", "getParts", "()Ljava/util/Map;", "addPart", Argument.Delimiters.none, "packageFqName", "partInternalName", Argument.Delimiters.none, "facadeInternalName", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PackagePartRegistry {
    private final Map<FqName, PackageParts> parts = new LinkedHashMap();

    public static PackageParts a(Function1 function1, Object obj) {
        return (PackageParts) function1.invoke(obj);
    }

    public static PackageParts b(FqName fqName) {
        fqName.getClass();
        return new PackageParts(fqName.asString());
    }

    public final void addPart(FqName packageFqName, String partInternalName, String facadeInternalName) {
        packageFqName.getClass();
        partInternalName.getClass();
        Map<FqName, PackageParts> map = this.parts;
        final Function1 function1 = new Function1() { // from class: vva
            public final Object invoke(Object obj) {
                return PackagePartRegistry.b((FqName) obj);
            }
        };
        map.computeIfAbsent(packageFqName, new Function() { // from class: wva
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return PackagePartRegistry.a(function1, obj);
            }
        }).addPart(partInternalName, facadeInternalName);
    }

    public final Map<FqName, PackageParts> getParts() {
        return this.parts;
    }
}
