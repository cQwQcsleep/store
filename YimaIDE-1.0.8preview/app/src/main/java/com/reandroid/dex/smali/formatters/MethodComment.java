package com.reandroid.dex.smali.formatters;

import com.reandroid.dex.key.MethodKey;
import com.reandroid.dex.model.DexClassRepository;
import com.reandroid.dex.model.DexMethod;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.collection.SingleIterator;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface MethodComment extends SmaliComment {

    public static class MethodImplementComment implements MethodComment {
        private final DexClassRepository classRepository;

        public MethodImplementComment(DexClassRepository dexClassRepository) {
            this.classRepository = dexClassRepository;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof MethodImplementComment) && this.classRepository == ((MethodImplementComment) obj).classRepository;
        }

        public int hashCode() {
            return ObjectsUtil.hash(this.classRepository, getClass());
        }

        @Override // com.reandroid.dex.smali.formatters.MethodComment
        public void writeComment(SmaliWriter smaliWriter, MethodKey methodKey) throws IOException {
            DexMethod declaredMethod = this.classRepository.getDeclaredMethod(methodKey);
            if (declaredMethod == null || declaredMethod.isDirect() || declaredMethod.getDexClass().isFinal()) {
                return;
            }
            SmaliComment.writeDeclarationComment(smaliWriter, "implemented-by:", declaredMethod.getOverriding());
        }
    }

    public static class MethodOverrideComment implements MethodComment {
        private final DexClassRepository classRepository;

        public MethodOverrideComment(DexClassRepository dexClassRepository) {
            this.classRepository = dexClassRepository;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof MethodOverrideComment) && this.classRepository == ((MethodOverrideComment) obj).classRepository;
        }

        public int hashCode() {
            return ObjectsUtil.hash(this.classRepository, getClass());
        }

        @Override // com.reandroid.dex.smali.formatters.MethodComment
        public void writeComment(SmaliWriter smaliWriter, MethodKey methodKey) throws IOException {
            DexMethod superMethod;
            DexMethod declaredMethod = this.classRepository.getDeclaredMethod(methodKey);
            if (declaredMethod == null || declaredMethod.isDirect() || (superMethod = declaredMethod.getSuperMethod()) == null) {
                return;
            }
            SmaliComment.writeDeclarationComment(smaliWriter, "overrides:", SingleIterator.of(superMethod));
        }
    }

    void writeComment(SmaliWriter smaliWriter, MethodKey methodKey) throws IOException;
}
