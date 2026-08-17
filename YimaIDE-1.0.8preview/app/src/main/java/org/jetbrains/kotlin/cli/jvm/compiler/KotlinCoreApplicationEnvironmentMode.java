package org.jetbrains.kotlin.cli.jvm.compiler;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u0000 \u00042\u00020\u0001:\u0003\u0002\u0003\u0004\u0082\u0001\u0002\u0005\u0006ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/compiler/KotlinCoreApplicationEnvironmentMode;", Argument.Delimiters.none, "Production", "UnitTest", "Companion", "Lorg/jetbrains/kotlin/cli/jvm/compiler/KotlinCoreApplicationEnvironmentMode$Production;", "Lorg/jetbrains/kotlin/cli/jvm/compiler/KotlinCoreApplicationEnvironmentMode$UnitTest;", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface KotlinCoreApplicationEnvironmentMode {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/compiler/KotlinCoreApplicationEnvironmentMode$Companion;", Argument.Delimiters.none, "<init>", "()V", "fromUnitTestModeFlag", "Lorg/jetbrains/kotlin/cli/jvm/compiler/KotlinCoreApplicationEnvironmentMode;", "isUnitTestMode", Argument.Delimiters.none, "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        private Companion() {
        }

        public final KotlinCoreApplicationEnvironmentMode fromUnitTestModeFlag(boolean isUnitTestMode) {
            return isUnitTestMode ? UnitTest.INSTANCE : Production.INSTANCE;
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/compiler/KotlinCoreApplicationEnvironmentMode$Production;", "Lorg/jetbrains/kotlin/cli/jvm/compiler/KotlinCoreApplicationEnvironmentMode;", "<init>", "()V", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Production implements KotlinCoreApplicationEnvironmentMode {
        public static final Production INSTANCE = new Production();

        private Production() {
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/compiler/KotlinCoreApplicationEnvironmentMode$UnitTest;", "Lorg/jetbrains/kotlin/cli/jvm/compiler/KotlinCoreApplicationEnvironmentMode;", "<init>", "()V", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class UnitTest implements KotlinCoreApplicationEnvironmentMode {
        public static final UnitTest INSTANCE = new UnitTest();

        private UnitTest() {
        }
    }
}
