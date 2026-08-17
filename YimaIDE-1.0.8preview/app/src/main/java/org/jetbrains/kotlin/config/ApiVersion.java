package org.jetbrains.kotlin.config;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.utils.DescriptionAware;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\u0018\u0000 \u001a2\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u00022\u00020\u0003:\u0001\u001aB\u0019\b\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0012\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0000H\u0096\u0082\u0004J\u0014\u0010\u0016\u001a\u00020\u000f2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0017H\u0096\u0082\u0004J\n\u0010\u0018\u001a\u00020\u0014H\u0096\u0080\u0004J\n\u0010\u0019\u001a\u00020\u0007H\u0096\u0080\u0004R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0010R\u0014\u0010\u0012\u001a\u00020\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0010¨\u0006\u001b"}, d2 = {"Lorg/jetbrains/kotlin/config/ApiVersion;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/utils/DescriptionAware;", "Lorg/jetbrains/kotlin/config/LanguageOrApiVersion;", "version", "Lorg/jetbrains/kotlin/config/MavenComparableVersion;", "versionString", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/config/MavenComparableVersion;Ljava/lang/String;)V", "getVersion", "()Lorg/jetbrains/kotlin/config/MavenComparableVersion;", "getVersionString", "()Ljava/lang/String;", "isStable", Argument.Delimiters.none, "()Z", "isDeprecated", "isUnsupported", "compareTo", Argument.Delimiters.none, "other", "equals", Argument.Delimiters.none, "hashCode", "toString", "Companion", "org.jetbrains.kotlin:language.version-settings"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ApiVersion implements Comparable<ApiVersion>, LanguageOrApiVersion, DescriptionAware {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE;
    public static final ApiVersion FIRST_NON_DEPRECATED;
    public static final ApiVersion FIRST_SUPPORTED;
    public static final ApiVersion KOTLIN_1_0;
    public static final ApiVersion KOTLIN_1_1;
    public static final ApiVersion KOTLIN_1_2;
    public static final ApiVersion KOTLIN_1_3;
    public static final ApiVersion KOTLIN_1_4;
    public static final ApiVersion KOTLIN_1_5;
    public static final ApiVersion KOTLIN_1_6;
    public static final ApiVersion KOTLIN_1_7;
    public static final ApiVersion KOTLIN_1_8;
    public static final ApiVersion KOTLIN_1_9;
    public static final ApiVersion KOTLIN_2_0;
    public static final ApiVersion KOTLIN_2_1;
    public static final ApiVersion KOTLIN_2_2;
    public static final ApiVersion KOTLIN_2_3;
    public static final ApiVersion KOTLIN_2_4;
    public static final ApiVersion KOTLIN_2_5;
    public static final ApiVersion LATEST;
    public static final ApiVersion LATEST_STABLE;
    private final MavenComparableVersion version;
    private final String versionString;

    static {
        Companion companion = new Companion(null);
        INSTANCE = companion;
        KOTLIN_1_0 = companion.createByLanguageVersion(LanguageVersion.KOTLIN_1_0);
        KOTLIN_1_1 = companion.createByLanguageVersion(LanguageVersion.KOTLIN_1_1);
        KOTLIN_1_2 = companion.createByLanguageVersion(LanguageVersion.KOTLIN_1_2);
        KOTLIN_1_3 = companion.createByLanguageVersion(LanguageVersion.KOTLIN_1_3);
        KOTLIN_1_4 = companion.createByLanguageVersion(LanguageVersion.KOTLIN_1_4);
        KOTLIN_1_5 = companion.createByLanguageVersion(LanguageVersion.KOTLIN_1_5);
        KOTLIN_1_6 = companion.createByLanguageVersion(LanguageVersion.KOTLIN_1_6);
        KOTLIN_1_7 = companion.createByLanguageVersion(LanguageVersion.KOTLIN_1_7);
        KOTLIN_1_8 = companion.createByLanguageVersion(LanguageVersion.KOTLIN_1_8);
        KOTLIN_1_9 = companion.createByLanguageVersion(LanguageVersion.KOTLIN_1_9);
        KOTLIN_2_0 = companion.createByLanguageVersion(LanguageVersion.KOTLIN_2_0);
        KOTLIN_2_1 = companion.createByLanguageVersion(LanguageVersion.KOTLIN_2_1);
        KOTLIN_2_2 = companion.createByLanguageVersion(LanguageVersion.KOTLIN_2_2);
        KOTLIN_2_3 = companion.createByLanguageVersion(LanguageVersion.KOTLIN_2_3);
        KOTLIN_2_4 = companion.createByLanguageVersion(LanguageVersion.KOTLIN_2_4);
        KOTLIN_2_5 = companion.createByLanguageVersion(LanguageVersion.KOTLIN_2_5);
        LATEST = companion.createByLanguageVersion((LanguageVersion) CollectionsKt.last(LanguageVersion.getEntries()));
        LATEST_STABLE = companion.createByLanguageVersion(LanguageVersion.LATEST_STABLE);
        FIRST_SUPPORTED = companion.createByLanguageVersion(LanguageVersion.FIRST_API_SUPPORTED);
        FIRST_NON_DEPRECATED = companion.createByLanguageVersion(LanguageVersion.FIRST_NON_DEPRECATED);
    }

    private ApiVersion(MavenComparableVersion mavenComparableVersion, String str) {
        this.version = mavenComparableVersion;
        this.versionString = str;
    }

    @JvmStatic
    public static final ApiVersion createByLanguageVersion(LanguageVersion languageVersion) {
        return INSTANCE.createByLanguageVersion(languageVersion);
    }

    @Override // java.lang.Comparable
    public int compareTo(ApiVersion other) {
        other.getClass();
        return this.version.compareTo(other.version);
    }

    public boolean equals(Object other) {
        ApiVersion apiVersion = other instanceof ApiVersion ? (ApiVersion) other : null;
        return Intrinsics.areEqual(apiVersion != null ? apiVersion.version : null, this.version);
    }

    public final MavenComparableVersion getVersion() {
        return this.version;
    }

    @Override // org.jetbrains.kotlin.config.LanguageOrApiVersion
    public String getVersionString() {
        return this.versionString;
    }

    public int hashCode() {
        return this.version.hashCode();
    }

    @Override // org.jetbrains.kotlin.config.LanguageOrApiVersion
    public boolean isDeprecated() {
        return FIRST_SUPPORTED.compareTo(this) <= 0 && compareTo(FIRST_NON_DEPRECATED) < 0;
    }

    @Override // org.jetbrains.kotlin.config.LanguageOrApiVersion
    public boolean isStable() {
        return compareTo(LATEST_STABLE) <= 0;
    }

    @Override // org.jetbrains.kotlin.config.LanguageOrApiVersion
    public boolean isUnsupported() {
        return compareTo(FIRST_SUPPORTED) < 0;
    }

    public String toString() {
        return getVersionString();
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u001bH\u0007J\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u001d\u001a\u00020\u001eR\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lorg/jetbrains/kotlin/config/ApiVersion$Companion;", Argument.Delimiters.none, "<init>", "()V", "KOTLIN_1_0", "Lorg/jetbrains/kotlin/config/ApiVersion;", "KOTLIN_1_1", "KOTLIN_1_2", "KOTLIN_1_3", "KOTLIN_1_4", "KOTLIN_1_5", "KOTLIN_1_6", "KOTLIN_1_7", "KOTLIN_1_8", "KOTLIN_1_9", "KOTLIN_2_0", "KOTLIN_2_1", "KOTLIN_2_2", "KOTLIN_2_3", "KOTLIN_2_4", "KOTLIN_2_5", "LATEST", "LATEST_STABLE", "FIRST_SUPPORTED", "FIRST_NON_DEPRECATED", "createByLanguageVersion", "version", "Lorg/jetbrains/kotlin/config/LanguageVersion;", "parse", "versionString", Argument.Delimiters.none, "org.jetbrains.kotlin:language.version-settings"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public final ApiVersion createByLanguageVersion(LanguageVersion version) {
            version.getClass();
            ApiVersion apiVersion = parse(version.getVersionString());
            apiVersion.getClass();
            return apiVersion;
        }

        public final ApiVersion parse(String versionString) {
            versionString.getClass();
            try {
                return new ApiVersion(new MavenComparableVersion(versionString), versionString, null);
            } catch (Exception unused) {
                return null;
            }
        }

        private Companion() {
        }
    }

    public /* synthetic */ ApiVersion(MavenComparableVersion mavenComparableVersion, String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(mavenComparableVersion, str);
    }
}
