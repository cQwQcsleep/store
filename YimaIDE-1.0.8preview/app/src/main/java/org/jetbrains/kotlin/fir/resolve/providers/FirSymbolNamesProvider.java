package org.jetbrains.kotlin.fir.resolve.providers;

import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H\u0016J\u0010\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H\u0016J\u0018\u0010\f\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u00052\u0006\u0010\u000e\u001a\u00020\u000fH&J\u0010\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H\u0016J\u0018\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\u00052\u0006\u0010\u000e\u001a\u00020\u000fH&J\u0010\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0018\u0010\u001a\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\rH\u0016R\u0012\u0010\u0007\u001a\u00020\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0012\u0010\u0010\u001a\u00020\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\nR\u0014\u0010\u0014\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\n¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolNamesProvider;", Argument.Delimiters.none, "<init>", "()V", "getPackageNames", Argument.Delimiters.none, Argument.Delimiters.none, "hasSpecificClassifierPackageNamesComputation", Argument.Delimiters.none, "getHasSpecificClassifierPackageNamesComputation", "()Z", "getPackageNamesWithTopLevelClassifiers", "getTopLevelClassifierNamesInPackage", "Lorg/jetbrains/kotlin/name/Name;", "packageFqName", "Lorg/jetbrains/kotlin/name/FqName;", "hasSpecificCallablePackageNamesComputation", "getHasSpecificCallablePackageNamesComputation", "getPackageNamesWithTopLevelCallables", "getTopLevelCallableNamesInPackage", "mayHaveSyntheticFunctionTypes", "getMayHaveSyntheticFunctionTypes", "mayHaveSyntheticFunctionType", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "mayHaveTopLevelClassifier", "mayHaveTopLevelCallable", ModuleXmlParser.NAME, "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirSymbolNamesProvider {
    public abstract boolean getHasSpecificCallablePackageNamesComputation();

    public abstract boolean getHasSpecificClassifierPackageNamesComputation();

    public boolean getMayHaveSyntheticFunctionTypes() {
        return false;
    }

    public Set<String> getPackageNames() {
        return null;
    }

    public Set<String> getPackageNamesWithTopLevelCallables() {
        return getPackageNames();
    }

    public Set<String> getPackageNamesWithTopLevelClassifiers() {
        return getPackageNames();
    }

    public abstract Set<Name> getTopLevelCallableNamesInPackage(FqName packageFqName);

    public abstract Set<Name> getTopLevelClassifierNamesInPackage(FqName packageFqName);

    public boolean mayHaveSyntheticFunctionType(ClassId classId) {
        classId.getClass();
        return getMayHaveSyntheticFunctionTypes();
    }

    public boolean mayHaveTopLevelCallable(FqName packageFqName, Name name) {
        Set<Name> topLevelCallableNamesInPackage;
        packageFqName.getClass();
        name.getClass();
        if (name.isSpecial() || (topLevelCallableNamesInPackage = getTopLevelCallableNamesInPackage(packageFqName)) == null) {
            return true;
        }
        return topLevelCallableNamesInPackage.contains(name);
    }

    public boolean mayHaveTopLevelClassifier(ClassId classId) {
        Set<Name> topLevelClassifierNamesInPackage;
        classId.getClass();
        if ((getMayHaveSyntheticFunctionTypes() && mayHaveSyntheticFunctionType(classId)) || (topLevelClassifierNamesInPackage = getTopLevelClassifierNamesInPackage(classId.getPackageFqName())) == null) {
            return true;
        }
        if (classId.getOuterClassId() == null) {
            if (!FirSymbolNamesProviderKt.mayContainTopLevelClassifier(topLevelClassifierNamesInPackage, classId.getShortClassName())) {
                return false;
            }
        } else if (!FirSymbolNamesProviderKt.mayContainTopLevelClassifier(topLevelClassifierNamesInPackage, classId.getOutermostClassId().getShortClassName())) {
            return false;
        }
        return true;
    }
}
