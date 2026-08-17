package org.jetbrains.kotlin.cli.pipeline;

import com.intellij.openapi.Disposable;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.CompilerConfigurationCreationKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.CommonCompilerArguments;
import org.jetbrains.kotlin.cli.common.messages.GroupingMessageCollector;
import org.jetbrains.kotlin.config.CompilerConfiguration;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.config.Services;
import org.jetbrains.kotlin.util.PerformanceManager;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\u00020\u0003B/\u0012\u0006\u0010\u0004\u001a\u00028\u0000\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020\u001bH\u0017J\u000e\u0010 \u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\u0010J\t\u0010!\u001a\u00020\u0006HÆ\u0003J\t\u0010\"\u001a\u00020\bHÆ\u0003J\t\u0010#\u001a\u00020\nHÆ\u0003J\t\u0010$\u001a\u00020\fHÆ\u0003JF\u0010%\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0004\u001a\u00028\u00002\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fHÆ\u0001¢\u0006\u0002\u0010&J\u0014\u0010'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010*HÖ\u0083\u0004J\n\u0010+\u001a\u00020,HÖ\u0081\u0004J\n\u0010-\u001a\u00020.HÖ\u0081\u0004R\u0013\u0010\u0004\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u001a\u001a\u00020\u001bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d¨\u0006/"}, d2 = {"Lorg/jetbrains/kotlin/cli/pipeline/ArgumentsPipelineArtifact;", "A", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArguments;", "Lorg/jetbrains/kotlin/cli/pipeline/PipelineArtifact;", "arguments", "services", "Lorg/jetbrains/kotlin/config/Services;", "rootDisposable", "Lcom/intellij/openapi/Disposable;", "messageCollector", "Lorg/jetbrains/kotlin/cli/common/messages/GroupingMessageCollector;", "performanceManager", "Lorg/jetbrains/kotlin/util/PerformanceManager;", "<init>", "(Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArguments;Lorg/jetbrains/kotlin/config/Services;Lcom/intellij/openapi/Disposable;Lorg/jetbrains/kotlin/cli/common/messages/GroupingMessageCollector;Lorg/jetbrains/kotlin/util/PerformanceManager;)V", "getArguments", "()Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArguments;", "Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArguments;", "getServices", "()Lorg/jetbrains/kotlin/config/Services;", "getRootDisposable", "()Lcom/intellij/openapi/Disposable;", "getMessageCollector", "()Lorg/jetbrains/kotlin/cli/common/messages/GroupingMessageCollector;", "getPerformanceManager", "()Lorg/jetbrains/kotlin/util/PerformanceManager;", "configuration", "Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "getConfiguration", "()Lorg/jetbrains/kotlin/config/CompilerConfiguration;", "withCompilerConfiguration", "newConfiguration", "component1", "component2", "component3", "component4", "component5", "copy", "(Lorg/jetbrains/kotlin/cli/common/arguments/CommonCompilerArguments;Lorg/jetbrains/kotlin/config/Services;Lcom/intellij/openapi/Disposable;Lorg/jetbrains/kotlin/cli/common/messages/GroupingMessageCollector;Lorg/jetbrains/kotlin/util/PerformanceManager;)Lorg/jetbrains/kotlin/cli/pipeline/ArgumentsPipelineArtifact;", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class ArgumentsPipelineArtifact<A extends CommonCompilerArguments> extends PipelineArtifact {
    private final A arguments;
    private final CompilerConfiguration configuration;
    private final GroupingMessageCollector messageCollector;
    private final PerformanceManager performanceManager;
    private final Disposable rootDisposable;
    private final Services services;

    public ArgumentsPipelineArtifact(A a, Services services, Disposable disposable, GroupingMessageCollector groupingMessageCollector, PerformanceManager performanceManager) {
        a.getClass();
        services.getClass();
        disposable.getClass();
        groupingMessageCollector.getClass();
        performanceManager.getClass();
        this.arguments = a;
        this.services = services;
        this.rootDisposable = disposable;
        this.messageCollector = groupingMessageCollector;
        this.performanceManager = performanceManager;
        this.configuration = CompilerConfigurationCreationKt.create$default(CompilerConfiguration.INSTANCE, null, groupingMessageCollector, 1, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ArgumentsPipelineArtifact copy$default(ArgumentsPipelineArtifact argumentsPipelineArtifact, CommonCompilerArguments commonCompilerArguments, Services services, Disposable disposable, GroupingMessageCollector groupingMessageCollector, PerformanceManager performanceManager, int i, Object obj) {
        if ((i & 1) != 0) {
            commonCompilerArguments = argumentsPipelineArtifact.arguments;
        }
        if ((i & 2) != 0) {
            services = argumentsPipelineArtifact.services;
        }
        if ((i & 4) != 0) {
            disposable = argumentsPipelineArtifact.rootDisposable;
        }
        if ((i & 8) != 0) {
            groupingMessageCollector = argumentsPipelineArtifact.messageCollector;
        }
        if ((i & 16) != 0) {
            performanceManager = argumentsPipelineArtifact.performanceManager;
        }
        PerformanceManager performanceManager2 = performanceManager;
        Disposable disposable2 = disposable;
        return argumentsPipelineArtifact.copy(commonCompilerArguments, services, disposable2, groupingMessageCollector, performanceManager2);
    }

    public final A component1() {
        return this.arguments;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Services getServices() {
        return this.services;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Disposable getRootDisposable() {
        return this.rootDisposable;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final GroupingMessageCollector getMessageCollector() {
        return this.messageCollector;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final PerformanceManager getPerformanceManager() {
        return this.performanceManager;
    }

    public final ArgumentsPipelineArtifact<A> copy(A arguments, Services services, Disposable rootDisposable, GroupingMessageCollector messageCollector, PerformanceManager performanceManager) {
        arguments.getClass();
        services.getClass();
        rootDisposable.getClass();
        messageCollector.getClass();
        performanceManager.getClass();
        return new ArgumentsPipelineArtifact<>(arguments, services, rootDisposable, messageCollector, performanceManager);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ArgumentsPipelineArtifact)) {
            return false;
        }
        ArgumentsPipelineArtifact argumentsPipelineArtifact = (ArgumentsPipelineArtifact) other;
        return Intrinsics.areEqual(this.arguments, argumentsPipelineArtifact.arguments) && Intrinsics.areEqual(this.services, argumentsPipelineArtifact.services) && Intrinsics.areEqual(this.rootDisposable, argumentsPipelineArtifact.rootDisposable) && Intrinsics.areEqual(this.messageCollector, argumentsPipelineArtifact.messageCollector) && Intrinsics.areEqual(this.performanceManager, argumentsPipelineArtifact.performanceManager);
    }

    public final A getArguments() {
        return this.arguments;
    }

    @Override // org.jetbrains.kotlin.cli.pipeline.PipelineArtifact
    public CompilerConfiguration getConfiguration() {
        return this.configuration;
    }

    public final GroupingMessageCollector getMessageCollector() {
        return this.messageCollector;
    }

    public final PerformanceManager getPerformanceManager() {
        return this.performanceManager;
    }

    public final Disposable getRootDisposable() {
        return this.rootDisposable;
    }

    public final Services getServices() {
        return this.services;
    }

    public int hashCode() {
        return (((((((this.arguments.hashCode() * 31) + this.services.hashCode()) * 31) + this.rootDisposable.hashCode()) * 31) + this.messageCollector.hashCode()) * 31) + this.performanceManager.hashCode();
    }

    public String toString() {
        return "ArgumentsPipelineArtifact(arguments=" + this.arguments + ", services=" + this.services + ", rootDisposable=" + this.rootDisposable + ", messageCollector=" + this.messageCollector + ", performanceManager=" + this.performanceManager + ')';
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.cli.pipeline.PipelineArtifact
    @PipelineArtifact.CliPipelineInternals(message = PipelineArtifact.OPT_IN_MESSAGE)
    public PipelineArtifact withCompilerConfiguration(CompilerConfiguration newConfiguration) throws KotlinNothingValueException {
        newConfiguration.getClass();
        AddToStdlibKt.shouldNotBeCalled$default((String) null, 1, (Object) null);
        throw new KotlinNothingValueException();
    }
}
