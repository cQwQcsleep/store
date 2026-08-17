package org.jetbrains.kotlin.cli.jvm.compiler;

import com.intellij.core.CoreApplicationEnvironment;
import com.intellij.core.JavaCoreApplicationEnvironment;
import com.intellij.ide.highlighter.JavaClassFileType;
import com.intellij.mock.MockApplication;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.vfs.VirtualFileSystem;
import com.intellij.psi.JavaModuleGraphHelper;
import com.intellij.psi.codeStyle.JavaFileCodeStyleFacadeFactory;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.localfs.KotlinLocalFileSystem;
import org.jetbrains.kotlin.cli.jvm.compiler.jarfs.FastJarFileSystem;
import org.jetbrains.kotlin.cli.jvm.modules.CoreJrtFileSystem;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\b\u001a\u00020\tH\u0014J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0002\u001a\u00020\u0003H\u0014J\u0006\u0010\u0013\u001a\u00020\u0014J\b\u0010\u0015\u001a\u00020\u0016H\u0014R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0013\u0010\u0010\u001a\u0004\u0018\u00010\r8F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/compiler/KotlinCoreApplicationEnvironment;", "Lcom/intellij/core/JavaCoreApplicationEnvironment;", "parentDisposable", "Lcom/intellij/openapi/Disposable;", "environmentMode", "Lorg/jetbrains/kotlin/cli/jvm/compiler/KotlinCoreApplicationEnvironmentMode;", "<init>", "(Lcom/intellij/openapi/Disposable;Lorg/jetbrains/kotlin/cli/jvm/compiler/KotlinCoreApplicationEnvironmentMode;)V", "createJrtFileSystem", "Lcom/intellij/openapi/vfs/VirtualFileSystem;", "createApplication", "Lcom/intellij/mock/MockApplication;", "fastJarFileSystemField", "Lorg/jetbrains/kotlin/cli/jvm/compiler/jarfs/FastJarFileSystem;", "fastJarFileSystemFieldInitialized", Argument.Delimiters.none, "fastJarFileSystem", "getFastJarFileSystem", "()Lorg/jetbrains/kotlin/cli/jvm/compiler/jarfs/FastJarFileSystem;", "idleCleanup", Argument.Delimiters.none, "createLocalFileSystem", "Lorg/jetbrains/kotlin/cli/common/localfs/KotlinLocalFileSystem;", "Companion", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class KotlinCoreApplicationEnvironment extends JavaCoreApplicationEnvironment {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private FastJarFileSystem fastJarFileSystemField;
    private boolean fastJarFileSystemFieldInitialized;

    private KotlinCoreApplicationEnvironment(Disposable disposable, KotlinCoreApplicationEnvironmentMode kotlinCoreApplicationEnvironmentMode) {
        super(disposable, Intrinsics.areEqual(kotlinCoreApplicationEnvironmentMode, KotlinCoreApplicationEnvironmentMode.UnitTest.INSTANCE));
        registerApplicationService(JavaFileCodeStyleFacadeFactory.class, new DummyJavaFileCodeStyleFacadeFactory());
        if (((CoreApplicationEnvironment) this).application.getService(JavaModuleGraphHelper.class) == null) {
            registerApplicationService(JavaModuleGraphHelper.class, new DumbJavaModuleGraphHelper());
        }
        registerFileType(JavaClassFileType.INSTANCE, "sig");
    }

    public MockApplication createApplication(Disposable parentDisposable) {
        parentDisposable.getClass();
        MockApplication mockApplicationCreateApplication = super/*com.intellij.core.CoreApplicationEnvironment*/.createApplication(parentDisposable);
        mockApplicationCreateApplication.getClass();
        return mockApplicationCreateApplication.isUnitTestMode() ? new KotlinCoreUnitTestApplication(parentDisposable) : mockApplicationCreateApplication;
    }

    public VirtualFileSystem createJrtFileSystem() {
        return new CoreJrtFileSystem();
    }

    public KotlinLocalFileSystem createLocalFileSystem() {
        return new KotlinLocalFileSystem();
    }

    public final FastJarFileSystem getFastJarFileSystem() {
        FastJarFileSystem fastJarFileSystem;
        synchronized (KotlinCoreEnvironment.INSTANCE.getAPPLICATION_LOCK()) {
            try {
                if (!this.fastJarFileSystemFieldInitialized) {
                    final FastJarFileSystem fastJarFileSystemCreateIfUnmappingPossible = FastJarFileSystem.INSTANCE.createIfUnmappingPossible();
                    if (fastJarFileSystemCreateIfUnmappingPossible != null) {
                        Disposer.register(getParentDisposable(), new Disposable() { // from class: gb8
                            public final void dispose() {
                                fastJarFileSystemCreateIfUnmappingPossible.clearHandlersCache();
                            }
                        });
                    } else {
                        fastJarFileSystemCreateIfUnmappingPossible = null;
                    }
                    this.fastJarFileSystemField = fastJarFileSystemCreateIfUnmappingPossible;
                    this.fastJarFileSystemFieldInitialized = true;
                }
                fastJarFileSystem = this.fastJarFileSystemField;
            } catch (Throwable th) {
                throw th;
            }
        }
        return fastJarFileSystem;
    }

    public final void idleCleanup() {
        FastJarFileSystem fastJarFileSystem = this.fastJarFileSystemField;
        if (fastJarFileSystem != null) {
            fastJarFileSystem.clearHandlersCache();
        }
    }

    public /* synthetic */ KotlinCoreApplicationEnvironment(Disposable disposable, KotlinCoreApplicationEnvironmentMode kotlinCoreApplicationEnvironmentMode, DefaultConstructorMarker defaultConstructorMarker) {
        this(disposable, kotlinCoreApplicationEnvironmentMode);
    }
}
