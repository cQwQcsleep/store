package org.jetbrains.kotlin.load.java;

import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.load.java.Jsr305Settings;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0014\b\u0002\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00030\u0006¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00030\u0006HÆ\u0003J5\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\u0014\b\u0002\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00030\u0006HÆ\u0001J\u0014\u0010\u001d\u001a\u00020\u00172\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u001f\u001a\u00020 HÖ\u0081\u0004J\n\u0010!\u001a\u00020\u0011HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u001d\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00030\u0006¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR!\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00108FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0016\u001a\u00020\u0017¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0018¨\u0006\""}, d2 = {"Lorg/jetbrains/kotlin/load/java/Jsr305Settings;", "", "globalLevel", "Lorg/jetbrains/kotlin/load/java/ReportLevel;", "migrationLevel", "userDefinedLevelForSpecificAnnotation", "", "Lorg/jetbrains/kotlin/name/FqName;", "<init>", "(Lorg/jetbrains/kotlin/load/java/ReportLevel;Lorg/jetbrains/kotlin/load/java/ReportLevel;Ljava/util/Map;)V", "getGlobalLevel", "()Lorg/jetbrains/kotlin/load/java/ReportLevel;", "getMigrationLevel", "getUserDefinedLevelForSpecificAnnotation", "()Ljava/util/Map;", "description", "", "", "getDescription", "()[Ljava/lang/String;", "description$delegate", "Lkotlin/Lazy;", "isDisabled", "", "()Z", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "org.jetbrains.kotlin:compiler.common.jvm"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class Jsr305Settings {

    /* JADX INFO: renamed from: description$delegate, reason: from kotlin metadata */
    private final Lazy description;
    private final ReportLevel globalLevel;
    private final boolean isDisabled;
    private final ReportLevel migrationLevel;
    private final Map<FqName, ReportLevel> userDefinedLevelForSpecificAnnotation;

    /* JADX WARN: Multi-variable type inference failed */
    public Jsr305Settings(ReportLevel reportLevel, ReportLevel reportLevel2, Map<FqName, ? extends ReportLevel> map) {
        reportLevel.getClass();
        map.getClass();
        this.globalLevel = reportLevel;
        this.migrationLevel = reportLevel2;
        this.userDefinedLevelForSpecificAnnotation = map;
        this.description = LazyKt.lazy(new Function0() { // from class: tu7
            public final Object invoke() {
                return Jsr305Settings.a(this.b);
            }
        });
        ReportLevel reportLevel3 = ReportLevel.IGNORE;
        this.isDisabled = reportLevel == reportLevel3 && reportLevel2 == reportLevel3 && map.isEmpty();
    }

    public static String[] a(Jsr305Settings jsr305Settings) {
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        listCreateListBuilder.add(jsr305Settings.globalLevel.getDescription());
        ReportLevel reportLevel = jsr305Settings.migrationLevel;
        if (reportLevel != null) {
            listCreateListBuilder.add("under-migration:" + reportLevel.getDescription());
        }
        for (Map.Entry<FqName, ReportLevel> entry : jsr305Settings.userDefinedLevelForSpecificAnnotation.entrySet()) {
            listCreateListBuilder.add("@" + entry.getKey() + ':' + entry.getValue().getDescription());
        }
        return (String[]) CollectionsKt.build(listCreateListBuilder).toArray(new String[0]);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Jsr305Settings copy$default(Jsr305Settings jsr305Settings, ReportLevel reportLevel, ReportLevel reportLevel2, Map map, int i, Object obj) {
        if ((i & 1) != 0) {
            reportLevel = jsr305Settings.globalLevel;
        }
        if ((i & 2) != 0) {
            reportLevel2 = jsr305Settings.migrationLevel;
        }
        if ((i & 4) != 0) {
            map = jsr305Settings.userDefinedLevelForSpecificAnnotation;
        }
        return jsr305Settings.copy(reportLevel, reportLevel2, map);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final ReportLevel getGlobalLevel() {
        return this.globalLevel;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final ReportLevel getMigrationLevel() {
        return this.migrationLevel;
    }

    public final Map<FqName, ReportLevel> component3() {
        return this.userDefinedLevelForSpecificAnnotation;
    }

    public final Jsr305Settings copy(ReportLevel globalLevel, ReportLevel migrationLevel, Map<FqName, ? extends ReportLevel> userDefinedLevelForSpecificAnnotation) {
        globalLevel.getClass();
        userDefinedLevelForSpecificAnnotation.getClass();
        return new Jsr305Settings(globalLevel, migrationLevel, userDefinedLevelForSpecificAnnotation);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Jsr305Settings)) {
            return false;
        }
        Jsr305Settings jsr305Settings = (Jsr305Settings) other;
        return this.globalLevel == jsr305Settings.globalLevel && this.migrationLevel == jsr305Settings.migrationLevel && Intrinsics.areEqual(this.userDefinedLevelForSpecificAnnotation, jsr305Settings.userDefinedLevelForSpecificAnnotation);
    }

    public final String[] getDescription() {
        return (String[]) this.description.getValue();
    }

    public final ReportLevel getGlobalLevel() {
        return this.globalLevel;
    }

    public final ReportLevel getMigrationLevel() {
        return this.migrationLevel;
    }

    public final Map<FqName, ReportLevel> getUserDefinedLevelForSpecificAnnotation() {
        return this.userDefinedLevelForSpecificAnnotation;
    }

    public int hashCode() {
        int iHashCode = this.globalLevel.hashCode() * 31;
        ReportLevel reportLevel = this.migrationLevel;
        return ((iHashCode + (reportLevel == null ? 0 : reportLevel.hashCode())) * 31) + this.userDefinedLevelForSpecificAnnotation.hashCode();
    }

    /* JADX INFO: renamed from: isDisabled, reason: from getter */
    public final boolean getIsDisabled() {
        return this.isDisabled;
    }

    public String toString() {
        return "Jsr305Settings(globalLevel=" + this.globalLevel + ", migrationLevel=" + this.migrationLevel + ", userDefinedLevelForSpecificAnnotation=" + this.userDefinedLevelForSpecificAnnotation + ')';
    }

    public /* synthetic */ Jsr305Settings(ReportLevel reportLevel, ReportLevel reportLevel2, Map map, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(reportLevel, (i & 2) != 0 ? null : reportLevel2, (i & 4) != 0 ? MapsKt.emptyMap() : map);
    }
}
