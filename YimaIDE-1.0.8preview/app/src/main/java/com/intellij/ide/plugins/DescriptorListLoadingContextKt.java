package com.intellij.ide.plugins;

import com.intellij.psi.impl.source.tree.ChildRole;
import com.intellij.psi.javadoc.PsiSnippetAttribute;
import it.unimi.dsi.fastutil.objects.ReferenceOpenHashSet;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes6.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0010 \n\u0002\b\u0004\"\u001c\u0010\u0000\u001a\u0010\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\":\u0010\u0004\u001a&\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002 \u0003*\u0012\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002\u0018\u00010\u00060\u0005X\u0082\u0004¢\u0006\n\n\u0002\u0010\t\u0012\u0004\b\u0007\u0010\b¨\u0006\n"}, d2 = {"CLASS_NAMES", "Lit/unimi/dsi/fastutil/objects/ReferenceOpenHashSet;", "", "kotlin.jvm.PlatformType", "EXTRA_STRINGS", "", "", "getEXTRA_STRINGS$annotations", "()V", "Ljava/util/List;", "intellij.platform.core.impl"}, k = 2, mv = {2, 0, 0}, xi = ChildRole.TRY_BLOCK)
public final class DescriptorListLoadingContextKt {
    private static final ReferenceOpenHashSet<String> CLASS_NAMES = new ReferenceOpenHashSet<>(new String[]{"implementation", "implementationClass", "builderClass", "serviceImplementation", PsiSnippetAttribute.CLASS_ATTRIBUTE, "className", "beanClass", "serviceInterface", "interface", "interfaceClass", "instance", "implementation-class", "qualifiedName"});
    private static final List<String> EXTRA_STRINGS = Arrays.asList(PsiSnippetAttribute.ID_ATTRIBUTE, "order", "os", "JetBrains", "JetBrains s.r.o.", "com.intellij.applicationService", "com.intellij.projectService", "com.intellij.moduleService", "com.intellij.postStartupActivity", "com.intellij", "com.intellij.java", "com.intellij.modules.java", "Docker", "intellij.clouds.docker.file", "intellij.clouds.docker.remoteRun");
}
