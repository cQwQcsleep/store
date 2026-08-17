package org.jetbrains.kotlin.cli.common.arguments;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.Metadata;
import kotlin.annotation.AnnotationTarget;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Target({ElementType.FIELD})
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0087\u0002\u0018\u00002\u00020\u0001:\u0002\u0011\u0012B`\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\u00020\u0003B\u0004\b\b(\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\u00020\u0003B\u0004\b\b(\u0005\u0012\u000e\b\u0002\u0010\u0007\u001a\u00020\u0003B\u0004\b\b(\b\u0012\u000e\b\u0002\u0010\t\u001a\u00020\u0003B\u0004\b\b(\u0005\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u000b\u001a\u00020\fB\u0004\b\u0007\u0010\u0000R\u000f\u0010\u0002\u001a\u00020\u0003¢\u0006\u0006\u001a\u0004\b\u0002\u0010\rR\u000f\u0010\u0004\u001a\u00020\u0003¢\u0006\u0006\u001a\u0004\b\u0004\u0010\rR\u000f\u0010\u0006\u001a\u00020\u0003¢\u0006\u0006\u001a\u0004\b\u0006\u0010\rR\u001a\u0010\u0007\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\f\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0007\u0010\rR\u000f\u0010\t\u001a\u00020\u0003¢\u0006\u0006\u001a\u0004\b\t\u0010\rR\u000f\u0010\n\u001a\u00020\u0003¢\u0006\u0006\u001a\u0004\b\n\u0010\rR\u000f\u0010\u000b\u001a\u00020\f¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0010¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/arguments/Argument;", Delimiters.none, "value", Delimiters.none, "shortName", Delimiters.none, "deprecatedName", "delimiter", Delimiters.default, "valueDescription", "description", "isObsolete", Delimiters.none, "()Ljava/lang/String;", "delimiter$annotations", "()V", "()Z", "RawDelimiter", "Delimiters", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
@kotlin.annotation.Target(allowedTargets = {AnnotationTarget.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Argument {

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class DefaultImpls {
        @RawDelimiter
        public static /* synthetic */ void delimiter$annotations() {
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/arguments/Argument$Delimiters;", Delimiters.none, "<init>", "()V", "default", Delimiters.none, "none", "pathSeparator", "space", "semicolon", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Delimiters {
        public static final Delimiters INSTANCE = new Delimiters();
        public static final String default = ",";
        public static final String none = "";
        public static final String pathSeparator = "<path_separator>";
        public static final String semicolon = ";";
        public static final String space = " ";

        private Delimiters() {
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\b\u0087\u0002\u0018\u00002\u00020\u0001B\u0000¨\u0006\u0002"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/arguments/Argument$RawDelimiter;", Delimiters.none, "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public @interface RawDelimiter {
    }

    String delimiter() default ",";

    String deprecatedName() default "";

    String description();

    boolean isObsolete() default false;

    String shortName() default "";

    String value();

    String valueDescription() default "";
}
