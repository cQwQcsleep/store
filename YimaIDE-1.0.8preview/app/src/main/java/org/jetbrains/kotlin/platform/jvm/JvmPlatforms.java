package org.jetbrains.kotlin.platform.jvm;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.enums.EnumEntries;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.JvmTarget;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.platform.TargetPlatform;
import org.jetbrains.kotlin.platform.TargetPlatformKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010 \n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u001fB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u001a\u001a\u00020\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\f\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\u0011\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0011\u0010\u0013\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000eR\u0011\u0010\u0015\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000eR\u0011\u0010\u0017\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000eR\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u000b0\u001c¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001e¨\u0006 "}, d2 = {"Lorg/jetbrains/kotlin/platform/jvm/JvmPlatforms;", Argument.Delimiters.none, "<init>", "()V", "UNSPECIFIED_SIMPLE_JVM_PLATFORM", "Lorg/jetbrains/kotlin/platform/jvm/JdkPlatform;", "getUNSPECIFIED_SIMPLE_JVM_PLATFORM", "()Lorg/jetbrains/kotlin/platform/jvm/JdkPlatform;", "jvmTargetToJdkPlatform", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/config/JvmTarget;", "Lorg/jetbrains/kotlin/platform/TargetPlatform;", "unspecifiedJvmPlatform", "getUnspecifiedJvmPlatform", "()Lorg/jetbrains/kotlin/platform/TargetPlatform;", "defaultJvmPlatform", "getDefaultJvmPlatform", "jvm6", "getJvm6", "jvm8", "getJvm8", "jvm11", "getJvm11", "jvm17", "getJvm17", "jvmPlatformByTargetVersion", "targetVersion", "allJvmPlatforms", Argument.Delimiters.none, "getAllJvmPlatforms", "()Ljava/util/List;", "CompatJvmPlatform", "org.jetbrains.kotlin:language.targets.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JvmPlatforms {
    public static final JvmPlatforms INSTANCE = new JvmPlatforms();
    private static final JdkPlatform UNSPECIFIED_SIMPLE_JVM_PLATFORM = new JdkPlatform(JvmTarget.DEFAULT);
    private static final List<TargetPlatform> allJvmPlatforms;
    private static final TargetPlatform defaultJvmPlatform;
    private static final TargetPlatform jvm11;
    private static final TargetPlatform jvm17;
    private static final TargetPlatform jvm6;
    private static final TargetPlatform jvm8;
    private static final Map<JvmTarget, TargetPlatform> jvmTargetToJdkPlatform;

    static {
        EnumEntries<JvmTarget> entries = JvmTarget.getEntries();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(entries, 10));
        for (JvmTarget jvmTarget : entries) {
            arrayList.add(TuplesKt.to(jvmTarget, TargetPlatformKt.toTargetPlatform(new JdkPlatform(jvmTarget))));
        }
        Map<JvmTarget, TargetPlatform> map = MapsKt.toMap(arrayList);
        jvmTargetToJdkPlatform = map;
        TargetPlatform targetPlatform = map.get(JvmTarget.DEFAULT);
        targetPlatform.getClass();
        defaultJvmPlatform = targetPlatform;
        TargetPlatform targetPlatform2 = map.get(JvmTarget.JVM_1_6);
        targetPlatform2.getClass();
        jvm6 = targetPlatform2;
        TargetPlatform targetPlatform3 = map.get(JvmTarget.JVM_1_8);
        targetPlatform3.getClass();
        jvm8 = targetPlatform3;
        TargetPlatform targetPlatform4 = map.get(JvmTarget.JVM_11);
        targetPlatform4.getClass();
        jvm11 = targetPlatform4;
        TargetPlatform targetPlatform5 = map.get(JvmTarget.JVM_17);
        targetPlatform5.getClass();
        jvm17 = targetPlatform5;
        allJvmPlatforms = CollectionsKt.toList(map.values());
    }

    private JvmPlatforms() {
    }

    public final List<TargetPlatform> getAllJvmPlatforms() {
        return allJvmPlatforms;
    }

    public final TargetPlatform getDefaultJvmPlatform() {
        return defaultJvmPlatform;
    }

    public final TargetPlatform getJvm11() {
        return jvm11;
    }

    public final TargetPlatform getJvm17() {
        return jvm17;
    }

    public final TargetPlatform getJvm6() {
        return jvm6;
    }

    public final TargetPlatform getJvm8() {
        return jvm8;
    }

    public final JdkPlatform getUNSPECIFIED_SIMPLE_JVM_PLATFORM() {
        return UNSPECIFIED_SIMPLE_JVM_PLATFORM;
    }

    public final TargetPlatform getUnspecifiedJvmPlatform() {
        return CompatJvmPlatform.INSTANCE;
    }

    public final TargetPlatform jvmPlatformByTargetVersion(JvmTarget targetVersion) {
        targetVersion.getClass();
        TargetPlatform targetPlatform = jvmTargetToJdkPlatform.get(targetVersion);
        targetPlatform.getClass();
        return targetPlatform;
    }
}
