package com.reandroid.dex.smali;

import com.reandroid.arsc.chunk.PackageBlock;
import com.reandroid.dex.key.MethodKey;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.model.DexClassRepository;
import com.reandroid.dex.smali.formatters.ClassComment;
import com.reandroid.dex.smali.formatters.MethodComment;
import com.reandroid.dex.smali.formatters.ResourceIdComment;
import com.reandroid.utils.collection.ArrayCollection;
import com.sun.jna.platform.win32.WinError;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SmaliWriterSetting {
    private List<ClassComment> classCommentList;
    private SmaliFileNameFactory fileNameFactory;
    private List<MethodComment> methodCommentList;
    private ResourceIdComment resourceIdComment;
    private boolean sequentialLabel = true;
    private boolean commentUnicodeStrings = false;
    private boolean localRegistersCount = true;
    private boolean enableComments = true;
    private int maximumCommentLines = WinError.ERROR_USER_PROFILE_LOAD;

    public void addClassComment(ClassComment classComment) {
        if (classComment == null) {
            return;
        }
        List arrayCollection = this.classCommentList;
        if (arrayCollection == null) {
            arrayCollection = new ArrayCollection();
            this.classCommentList = arrayCollection;
        }
        if (arrayCollection.contains(classComment)) {
            return;
        }
        arrayCollection.add(classComment);
    }

    public void addClassComments(DexClassRepository dexClassRepository) {
        addClassComment(new ClassComment.ClassExtendComment(dexClassRepository));
        addClassComment(new ClassComment.ClassImplementComment(dexClassRepository));
    }

    public void addMethodComment(MethodComment methodComment) {
        if (methodComment == null) {
            return;
        }
        List arrayCollection = this.methodCommentList;
        if (arrayCollection == null) {
            arrayCollection = new ArrayCollection();
            this.methodCommentList = arrayCollection;
        }
        if (arrayCollection.contains(methodComment)) {
            return;
        }
        arrayCollection.add(methodComment);
    }

    public void addMethodComments(DexClassRepository dexClassRepository) {
        addMethodComment(new MethodComment.MethodOverrideComment(dexClassRepository));
        addMethodComment(new MethodComment.MethodImplementComment(dexClassRepository));
    }

    public void clearClassComments() {
        List<ClassComment> list = this.classCommentList;
        if (list != null) {
            list.clear();
        }
    }

    public void clearMethodComments() {
        List<MethodComment> list = this.methodCommentList;
        if (list != null) {
            list.clear();
        }
    }

    public List<ClassComment> getClassCommentList() {
        return this.classCommentList;
    }

    public SmaliFileNameFactory getFileNameFactory() {
        SmaliFileNameFactory smaliFileNameFactory = this.fileNameFactory;
        if (smaliFileNameFactory != null) {
            return smaliFileNameFactory;
        }
        SmaliFileNameFactory smaliFileNameFactoryNewInstance = SmaliFileNameFactory.newInstance();
        this.fileNameFactory = smaliFileNameFactoryNewInstance;
        return smaliFileNameFactoryNewInstance;
    }

    public int getMaximumCommentLines() {
        return this.maximumCommentLines;
    }

    public List<MethodComment> getMethodCommentList() {
        return this.methodCommentList;
    }

    public ResourceIdComment getResourceIdComment() {
        return this.resourceIdComment;
    }

    public boolean isCommentUnicodeStrings() {
        return this.commentUnicodeStrings;
    }

    public boolean isEnableComments() {
        return this.enableComments;
    }

    public boolean isLocalRegistersCount() {
        return this.localRegistersCount;
    }

    public boolean isSequentialLabel() {
        return this.sequentialLabel;
    }

    public void setCommentUnicodeStrings(boolean z) {
        this.commentUnicodeStrings = z;
    }

    public void setEnableComments(boolean z) {
        this.enableComments = z;
    }

    public void setFileNameFactory(SmaliFileNameFactory smaliFileNameFactory) {
        this.fileNameFactory = smaliFileNameFactory;
    }

    public void setLocalRegistersCount(boolean z) {
        this.localRegistersCount = z;
    }

    public void setMaximumCommentLines(int i) {
        this.maximumCommentLines = i;
    }

    public void setResourceIdComment(PackageBlock packageBlock) {
        setResourceIdComment(new ResourceIdComment.ResourceTableComment(packageBlock));
    }

    public void setSequentialLabel(boolean z) {
        this.sequentialLabel = z;
    }

    public void writeClassComment(SmaliWriter smaliWriter, TypeKey typeKey) throws IOException {
        List<ClassComment> classCommentList;
        if (!isEnableComments() || (classCommentList = getClassCommentList()) == null) {
            return;
        }
        Iterator<ClassComment> it = classCommentList.iterator();
        while (it.hasNext()) {
            it.next().writeComment(smaliWriter, typeKey);
        }
    }

    public void writeMethodComment(SmaliWriter smaliWriter, MethodKey methodKey) throws IOException {
        List<MethodComment> methodCommentList;
        if (!isEnableComments() || (methodCommentList = getMethodCommentList()) == null) {
            return;
        }
        Iterator<MethodComment> it = methodCommentList.iterator();
        while (it.hasNext()) {
            it.next().writeComment(smaliWriter, methodKey);
        }
    }

    public void writeResourceIdComment(SmaliWriter smaliWriter, long j) throws IOException {
        ResourceIdComment resourceIdComment = getResourceIdComment();
        if (resourceIdComment != null) {
            resourceIdComment.writeComment(smaliWriter, (int) j);
        }
    }

    public void setResourceIdComment(ResourceIdComment resourceIdComment) {
        this.resourceIdComment = resourceIdComment;
    }

    public void writeResourceIdComment(SmaliWriter smaliWriter, int i) throws IOException {
        ResourceIdComment resourceIdComment = getResourceIdComment();
        if (resourceIdComment != null) {
            resourceIdComment.writeComment(smaliWriter, i);
        }
    }
}
