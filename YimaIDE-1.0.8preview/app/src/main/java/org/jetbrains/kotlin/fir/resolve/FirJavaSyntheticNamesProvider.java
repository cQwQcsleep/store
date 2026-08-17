package org.jetbrains.kotlin.fir.resolve;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.calls.FirSyntheticNamesProvider;
import org.jetbrains.kotlin.load.java.PropertiesConventionUtilKt;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\u0010\u0010\b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/FirJavaSyntheticNamesProvider;", "Lorg/jetbrains/kotlin/fir/resolve/calls/FirSyntheticNamesProvider;", "<init>", "()V", "possibleGetterNamesByPropertyName", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/Name;", ModuleXmlParser.NAME, "setterNameByGetterName", "possiblePropertyNamesByAccessorName", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJavaSyntheticNamesProvider extends FirSyntheticNamesProvider {
    public static final FirJavaSyntheticNamesProvider INSTANCE = new FirJavaSyntheticNamesProvider();

    private FirJavaSyntheticNamesProvider() {
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.FirSyntheticNamesProvider
    public List<Name> possibleGetterNamesByPropertyName(Name name) {
        name.getClass();
        return PropertiesConventionUtilKt.possibleGetMethodNames(name);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.FirSyntheticNamesProvider
    public List<Name> possiblePropertyNamesByAccessorName(Name name) {
        name.getClass();
        return PropertiesConventionUtilKt.getPropertyNamesCandidatesByAccessorName(name);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.FirSyntheticNamesProvider
    public Name setterNameByGetterName(Name name) {
        name.getClass();
        return PropertiesConventionUtilKt.setMethodName(name);
    }
}
