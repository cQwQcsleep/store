package org.jetbrains.kotlin.codegen.inline;

import com.intellij.util.containers.SLRUMap;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.state.CompiledCodeProvider;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000f\u001a\u00020\tH\u0016J\u001c\u0010\u0010\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\t2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\n0\u0012J\u001c\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\r0\u0012R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/InlineCache;", "Lorg/jetbrains/kotlin/codegen/state/CompiledCodeProvider;", "provider", "<init>", "(Lorg/jetbrains/kotlin/codegen/state/CompiledCodeProvider;)V", "getProvider", "()Lorg/jetbrains/kotlin/codegen/state/CompiledCodeProvider;", "classBytes", "Lcom/intellij/util/containers/SLRUMap;", Argument.Delimiters.none, Argument.Delimiters.none, "methodNodeById", "Lorg/jetbrains/kotlin/codegen/inline/MethodId;", "Lorg/jetbrains/kotlin/codegen/inline/SMAPAndMethodNode;", "getClassBytes", "className", "computeClassBytes", "computer", "Lkotlin/Function0;", "computeMethodBytes", "methodId", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class InlineCache implements CompiledCodeProvider {
    private final SLRUMap<String, byte[]> classBytes;
    private final SLRUMap<MethodId, SMAPAndMethodNode> methodNodeById;
    private final CompiledCodeProvider provider;

    public InlineCache(CompiledCodeProvider compiledCodeProvider) {
        compiledCodeProvider.getClass();
        this.provider = compiledCodeProvider;
        this.classBytes = new SLRUMap<>(30, 20);
        this.methodNodeById = new SLRUMap<>(60, 50);
    }

    public final byte[] computeClassBytes(String className, Function0<byte[]> computer) {
        Object objInvoke;
        className.getClass();
        computer.getClass();
        byte[] classBytes = this.provider.getClassBytes(className);
        if (classBytes != null) {
            return classBytes;
        }
        SLRUMap<String, byte[]> sLRUMap = this.classBytes;
        synchronized (sLRUMap) {
            objInvoke = sLRUMap.get(className);
            if (objInvoke == null) {
                objInvoke = computer.invoke();
                sLRUMap.put(className, objInvoke);
            }
        }
        return (byte[]) objInvoke;
    }

    public final SMAPAndMethodNode computeMethodBytes(MethodId methodId, Function0<SMAPAndMethodNode> computer) {
        Object objInvoke;
        methodId.getClass();
        computer.getClass();
        SLRUMap<MethodId, SMAPAndMethodNode> sLRUMap = this.methodNodeById;
        synchronized (sLRUMap) {
            objInvoke = sLRUMap.get(methodId);
            if (objInvoke == null) {
                objInvoke = computer.invoke();
                sLRUMap.put(methodId, objInvoke);
            }
        }
        return (SMAPAndMethodNode) objInvoke;
    }

    @Override // org.jetbrains.kotlin.codegen.state.CompiledCodeProvider
    public byte[] getClassBytes(String className) {
        className.getClass();
        byte[] classBytes = this.provider.getClassBytes(className);
        return classBytes == null ? (byte[]) this.classBytes.get(className) : classBytes;
    }

    public final CompiledCodeProvider getProvider() {
        return this.provider;
    }
}
