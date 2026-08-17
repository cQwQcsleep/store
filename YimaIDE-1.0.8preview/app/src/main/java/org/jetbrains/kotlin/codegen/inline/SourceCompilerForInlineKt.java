package org.jetbrains.kotlin.codegen.inline;

import com.intellij.openapi.vfs.VirtualFile;
import defpackage.yp6;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.AsmUtil;
import org.jetbrains.kotlin.codegen.inline.SourceCompilerForInlineKt;
import org.jetbrains.kotlin.codegen.inline.coroutines.CoroutineTransformerKt;
import org.jetbrains.kotlin.codegen.state.GenerationState;
import org.jetbrains.kotlin.config.CommonConfigurationKeys;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.incremental.components.LocationInfo;
import org.jetbrains.kotlin.incremental.components.LookupTracker;
import org.jetbrains.kotlin.incremental.components.Position;
import org.jetbrains.kotlin.incremental.components.ScopeKind;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.org.objectweb.asm.ClassReader;
import org.jetbrains.org.objectweb.asm.ClassVisitor;
import org.jetbrains.org.objectweb.asm.MethodVisitor;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.commons.Method;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000F\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0004\u001a\"\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b\u001a6\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0002\u001a8\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0010H\u0002\u001a\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u001b\u001a\u00020\u0006H\u0002\u001a*\u0010\u0014\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002¨\u0006\u001c"}, d2 = {"trackLookup", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/codegen/state/GenerationState;", "container", "Lorg/jetbrains/kotlin/name/FqName;", "functionName", Argument.Delimiters.none, "location", "Lorg/jetbrains/kotlin/incremental/components/LocationInfo;", "loadCompiledInlineFunction", "Lorg/jetbrains/kotlin/codegen/inline/SMAPAndMethodNode;", "containerId", "Lorg/jetbrains/kotlin/name/ClassId;", "asmMethod", "Lorg/jetbrains/org/objectweb/asm/commons/Method;", "isSuspend", Argument.Delimiters.none, "isMangled", "isInternalToBeMatchedIgnoringSuffix", "state", "getMethodNode", "owner", "Lorg/jetbrains/org/objectweb/asm/Type;", "bytes", Argument.Delimiters.none, "method", "removeModuleSuffixOrNull", ModuleXmlParser.NAME, "org.jetbrains.kotlin:backend"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SourceCompilerForInlineKt {
    public static SMAPAndMethodNode a(final GenerationState generationState, Type type, Method method, boolean z, boolean z2, boolean z3, final ClassId classId) {
        InlineCache inlineCache = generationState.getInlineCache();
        String internalName = type.getInternalName();
        internalName.getClass();
        return getMethodNode(type, inlineCache.computeClassBytes(internalName, new Function0() { // from class: bjd
            public final Object invoke() {
                return SourceCompilerForInlineKt.loadCompiledInlineFunction$lambda$0$0(generationState, classId);
            }
        }), method, z, z2, z3);
    }

    private static final SMAPAndMethodNode getMethodNode(Type type, byte[] bArr, Method method, boolean z, boolean z2, boolean z3) {
        SMAPAndMethodNode sMAPAndMethodNode;
        SMAP smapIdentityMapping;
        String name;
        SMAPAndMethodNode methodNode;
        SMAPAndMethodNode methodNode2 = getMethodNode(type, bArr, method, z);
        if (methodNode2 != null) {
            return methodNode2;
        }
        if (z2) {
            String name2 = method.getName();
            name2.getClass();
            int iIndexOf$default = StringsKt.indexOf$default(name2, '-', 0, false, 6, (Object) null);
            if (iIndexOf$default > 0) {
                String name3 = method.getName();
                name3.getClass();
                name = name3.substring(0, iIndexOf$default);
            } else {
                name = method.getName();
            }
            if (!Intrinsics.areEqual(name, method.getName()) && (methodNode = getMethodNode(type, bArr, new Method(name, method.getDescriptor()), z)) != null) {
                return methodNode;
            }
            SMAPAndMethodNode methodNode3 = getMethodNode(type, bArr, new Method(name + "-impl", method.getDescriptor()), z);
            if (methodNode3 != null) {
                return methodNode3;
            }
        }
        if (z3) {
            String name4 = method.getName();
            name4.getClass();
            String strRemoveModuleSuffixOrNull = removeModuleSuffixOrNull(name4);
            if (strRemoveModuleSuffixOrNull != null) {
                final Method method2 = new Method(strRemoveModuleSuffixOrNull, method.getDescriptor());
                final Ref.ObjectRef objectRef = new Ref.ObjectRef();
                final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
                final Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
                new ClassReader(bArr).accept(new ClassVisitor() { // from class: org.jetbrains.kotlin.codegen.inline.SourceCompilerForInlineKt$getMethodNode$$inlined$getMethodNode$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(589824);
                    }

                    public MethodVisitor visitMethod(int access, String name5, String desc, String signature, String[] exceptions) {
                        name5.getClass();
                        desc.getClass();
                        Method method3 = new Method(name5, desc);
                        Method method4 = method2;
                        String name6 = method3.getName();
                        name6.getClass();
                        if (!Intrinsics.areEqual(method4, new Method(SourceCompilerForInlineKt.removeModuleSuffixOrNull(name6), method3.getDescriptor()))) {
                            return null;
                        }
                        Ref.ObjectRef objectRef4 = objectRef;
                        MethodNode methodNode4 = (MethodNode) objectRef4.element;
                        if (methodNode4 == null) {
                            objectRef4.element = new MethodNode(589824, access, name5, desc, signature, exceptions);
                            return (MethodVisitor) objectRef.element;
                        }
                        yp6.a(name5, methodNode4.name, methodNode4.desc, desc);
                        return null;
                    }

                    public void visitSource(String source, String debug) {
                        objectRef2.element = source;
                        objectRef3.element = debug;
                    }
                }, 4);
                MethodNode methodNode4 = (MethodNode) objectRef.element;
                if (methodNode4 != null) {
                    String str = (String) objectRef3.element;
                    if (str == null || (smapIdentityMapping = SMAPParser.INSTANCE.parseOrNull(str)) == null) {
                        SMAP.Companion companion = SMAP.INSTANCE;
                        String str2 = (String) objectRef2.element;
                        String internalName = type.getInternalName();
                        internalName.getClass();
                        smapIdentityMapping = companion.identityMapping(str2, internalName, CollectionsKt.listOfNotNull(methodNode4));
                    }
                    sMAPAndMethodNode = new SMAPAndMethodNode(methodNode4, smapIdentityMapping);
                } else {
                    sMAPAndMethodNode = null;
                }
                if (sMAPAndMethodNode != null) {
                    return sMAPAndMethodNode;
                }
            }
        }
        throw new IllegalStateException("couldn't find inline method " + type + '.' + method);
    }

    public static final SMAPAndMethodNode loadCompiledInlineFunction(final ClassId classId, final Method method, final boolean z, final boolean z2, final boolean z3, final GenerationState generationState) {
        classId.getClass();
        method.getClass();
        generationState.getClass();
        final Type typeAsmTypeByClassId = AsmUtil.asmTypeByClassId(classId);
        typeAsmTypeByClassId.getClass();
        InlineCache inlineCache = generationState.getInlineCache();
        String descriptor = typeAsmTypeByClassId.getDescriptor();
        descriptor.getClass();
        SMAPAndMethodNode sMAPAndMethodNodeComputeMethodBytes = inlineCache.computeMethodBytes(new MethodId(descriptor, method), new Function0() { // from class: cjd
            public final Object invoke() {
                return SourceCompilerForInlineKt.a(generationState, typeAsmTypeByClassId, method, z, z2, z3, classId);
            }
        });
        return new SMAPAndMethodNode(InlineCodegenUtilsKt.cloneMethodNode(sMAPAndMethodNodeComputeMethodBytes.getNode()), sMAPAndMethodNodeComputeMethodBytes.getClassSMAP());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final byte[] loadCompiledInlineFunction$lambda$0$0(GenerationState generationState, ClassId classId) {
        byte[] bArrContentsToByteArray;
        VirtualFile virtualFileFindVirtualFile = InlineCodegenUtilsKt.findVirtualFile(generationState, classId);
        if (virtualFileFindVirtualFile != null && (bArrContentsToByteArray = virtualFileFindVirtualFile.contentsToByteArray()) != null) {
            return bArrContentsToByteArray;
        }
        qu7.a("Couldn't find declaration file for ", classId);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String removeModuleSuffixOrNull(String str) {
        int iIndexOf$default = StringsKt.indexOf$default(str, '$', 0, false, 6, (Object) null);
        if (iIndexOf$default >= 0) {
            return str.substring(0, iIndexOf$default + 1);
        }
        return null;
    }

    public static final void trackLookup(GenerationState generationState, FqName fqName, String str, LocationInfo locationInfo) {
        generationState.getClass();
        fqName.getClass();
        str.getClass();
        locationInfo.getClass();
        LookupTracker lookupTracker = (LookupTracker) generationState.getConfiguration().get(CommonConfigurationKeys.LOOKUP_TRACKER);
        if (lookupTracker == null) {
            return;
        }
        synchronized (lookupTracker) {
            try {
                lookupTracker.record(locationInfo.getFilePath(), lookupTracker.getRequiresPosition() ? locationInfo.getPosition() : Position.Companion.getNO_POSITION(), fqName.asString(), ScopeKind.CLASSIFIER, str);
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private static final SMAPAndMethodNode getMethodNode(Type type, byte[] bArr, Method method, boolean z) {
        SMAPAndMethodNode methodNode;
        if (z) {
            methodNode = InlineCodegenUtilsKt.getMethodNode(bArr, type, new Method(method.getName() + CoroutineTransformerKt.FOR_INLINE_SUFFIX, method.getDescriptor()));
        } else {
            methodNode = null;
        }
        return methodNode == null ? InlineCodegenUtilsKt.getMethodNode(bArr, type, method) : methodNode;
    }
}
