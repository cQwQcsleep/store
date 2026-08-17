package org.jetbrains.kotlin.cli.common.arguments;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import kotlin.Metadata;
import kotlin.annotation.AnnotationTarget;
import kotlin.annotation.Repeatable;
import kotlin.jvm.internal.RepeatableContainer;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Target({ElementType.FIELD})
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0087\u0002\u0018\u00002\u00020\u0001B\u0018\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0004\u001a\u00020\u0005B\u0004\b\b(\u0006R\u000f\u0010\u0002\u001a\u00020\u0003¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0007R\u000f\u0010\u0004\u001a\u00020\u0005¢\u0006\u0006\u001a\u0004\b\u0004\u0010\b¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/arguments/Enables;", Argument.Delimiters.none, "feature", "Lorg/jetbrains/kotlin/config/LanguageFeature;", "ifValueIs", Argument.Delimiters.none, Argument.Delimiters.none, "()Lorg/jetbrains/kotlin/config/LanguageFeature;", "()Ljava/lang/String;", "org.jetbrains.kotlin:arguments.common"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
@Repeatable
@kotlin.annotation.Target(allowedTargets = {AnnotationTarget.FIELD})
@java.lang.annotation.Repeatable(Container.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface Enables {

    @Target({ElementType.FIELD})
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    @kotlin.annotation.Target(allowedTargets = {AnnotationTarget.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    @RepeatableContainer
    public @interface Container {
        Enables[] value();
    }

    LanguageFeature feature();

    String ifValueIs() default "";
}
