package org.jetbrains.kotlin.codegen.optimization.fixStack;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0000\u0018\u00002\u00020\u0001:\u0001*B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\rH\u0002J\u001c\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00102\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aJ&\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u001d\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u00102\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aH\u0002J\u000e\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020\u0010J\b\u0010 \u001a\u00020\rH\u0002J\u000e\u0010!\u001a\u00020\u00142\u0006\u0010\u001f\u001a\u00020\u0010J\u001c\u0010\"\u001a\u00020\u00172\u0006\u0010#\u001a\u00020\u00102\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001aJ\u000e\u0010$\u001a\u00020\u00172\u0006\u0010%\u001a\u00020\u0010J\u000e\u0010&\u001a\u00020\u00142\u0006\u0010%\u001a\u00020\u0010J\u0010\u0010'\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u0010H\u0002J\u000e\u0010(\u001a\u00020\r2\u0006\u0010)\u001a\u00020\u001bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\u000e\u001a\u001e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00110\u000fj\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0011`\u0012X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/fixStack/LocalVariablesManager;", Argument.Delimiters.none, "context", "Lorg/jetbrains/kotlin/codegen/optimization/fixStack/FixStackContext;", "methodNode", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "<init>", "(Lorg/jetbrains/kotlin/codegen/optimization/fixStack/FixStackContext;Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;)V", "getContext", "()Lorg/jetbrains/kotlin/codegen/optimization/fixStack/FixStackContext;", "getMethodNode", "()Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "initialMaxLocals", Argument.Delimiters.none, "allocatedHandles", "Ljava/util/HashMap;", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "Lorg/jetbrains/kotlin/codegen/optimization/fixStack/LocalVariablesManager$AllocatedHandle;", "Lkotlin/collections/HashMap;", "updateMaxLocals", Argument.Delimiters.none, "newValue", "allocateVariablesForSaveStackMarker", "Lorg/jetbrains/kotlin/codegen/optimization/fixStack/SavedStackDescriptor;", "saveStackMarker", "savedStackValues", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/codegen/optimization/fixStack/FixStackValue;", "allocateNewHandle", "numRestoreStackMarkers", "getSavedStackDescriptor", "restoreStackMarker", "getFirstUnusedLocalVariableIndex", "markRestoreStackMarkerEmitted", "allocateVariablesForBeforeInlineMarker", "beforeInlineMarker", "getBeforeInlineDescriptor", "afterInlineMarker", "markAfterInlineMarkerEmitted", "markEmitted", "createReturnValueVariable", "returnValue", "AllocatedHandle", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class LocalVariablesManager {
    private final HashMap<AbstractInsnNode, AllocatedHandle> allocatedHandles;
    private final FixStackContext context;
    private final int initialMaxLocals;
    private final MethodNode methodNode;

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0010\u001a\u00020\u0011R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/fixStack/LocalVariablesManager$AllocatedHandle;", Argument.Delimiters.none, "savedStackDescriptor", "Lorg/jetbrains/kotlin/codegen/optimization/fixStack/SavedStackDescriptor;", "numRestoreMarkers", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/codegen/optimization/fixStack/SavedStackDescriptor;I)V", "getSavedStackDescriptor", "()Lorg/jetbrains/kotlin/codegen/optimization/fixStack/SavedStackDescriptor;", "getNumRestoreMarkers", "()I", "setNumRestoreMarkers", "(I)V", "isFullyEmitted", Argument.Delimiters.none, "markRestoreNodeEmitted", Argument.Delimiters.none, "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class AllocatedHandle {
        private int numRestoreMarkers;
        private final SavedStackDescriptor savedStackDescriptor;

        public AllocatedHandle(SavedStackDescriptor savedStackDescriptor, int i) {
            savedStackDescriptor.getClass();
            this.savedStackDescriptor = savedStackDescriptor;
            this.numRestoreMarkers = i;
        }

        public final int getNumRestoreMarkers() {
            return this.numRestoreMarkers;
        }

        public final SavedStackDescriptor getSavedStackDescriptor() {
            return this.savedStackDescriptor;
        }

        public final boolean isFullyEmitted() {
            return this.numRestoreMarkers == 0;
        }

        public final void markRestoreNodeEmitted() {
            this.numRestoreMarkers--;
        }

        public final void setNumRestoreMarkers(int i) {
            this.numRestoreMarkers = i;
        }
    }

    public LocalVariablesManager(FixStackContext fixStackContext, MethodNode methodNode) {
        fixStackContext.getClass();
        methodNode.getClass();
        this.context = fixStackContext;
        this.methodNode = methodNode;
        this.initialMaxLocals = methodNode.maxLocals;
        this.allocatedHandles = new HashMap<>();
    }

    private final SavedStackDescriptor allocateNewHandle(int numRestoreStackMarkers, AbstractInsnNode saveStackMarker, List<? extends FixStackValue> savedStackValues) {
        List<? extends FixStackValue> list = savedStackValues;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                if (((FixStackValue) it.next()) == FixStackValue.UNINITIALIZED) {
                    oeg.a("Uninitialized value on stack at ", this.methodNode.instructions.indexOf(saveStackMarker), ": ", savedStackValues);
                    return null;
                }
            }
        }
        SavedStackDescriptor savedStackDescriptor = new SavedStackDescriptor(savedStackValues, getFirstUnusedLocalVariableIndex());
        updateMaxLocals(savedStackDescriptor.getFirstUnusedLocalVarIndex());
        this.allocatedHandles.put(saveStackMarker, new AllocatedHandle(savedStackDescriptor, numRestoreStackMarkers));
        return savedStackDescriptor;
    }

    private final int getFirstUnusedLocalVariableIndex() {
        Collection<AllocatedHandle> collectionValues = this.allocatedHandles.values();
        collectionValues.getClass();
        int iMax = this.initialMaxLocals;
        Iterator<T> it = collectionValues.iterator();
        while (it.hasNext()) {
            iMax = Math.max(iMax, ((AllocatedHandle) it.next()).getSavedStackDescriptor().getFirstUnusedLocalVarIndex());
        }
        return iMax;
    }

    private final void markEmitted(AbstractInsnNode saveStackMarker) {
        AllocatedHandle allocatedHandle = this.allocatedHandles.get(saveStackMarker);
        allocatedHandle.getClass();
        AllocatedHandle allocatedHandle2 = allocatedHandle;
        allocatedHandle2.markRestoreNodeEmitted();
        if (allocatedHandle2.isFullyEmitted()) {
            this.allocatedHandles.remove(saveStackMarker);
        }
    }

    private final void updateMaxLocals(int newValue) {
        MethodNode methodNode = this.methodNode;
        methodNode.maxLocals = Math.max(methodNode.maxLocals, newValue);
    }

    public final SavedStackDescriptor allocateVariablesForBeforeInlineMarker(AbstractInsnNode beforeInlineMarker, List<? extends FixStackValue> savedStackValues) {
        beforeInlineMarker.getClass();
        savedStackValues.getClass();
        return allocateNewHandle(1, beforeInlineMarker, savedStackValues);
    }

    public final SavedStackDescriptor allocateVariablesForSaveStackMarker(AbstractInsnNode saveStackMarker, List<? extends FixStackValue> savedStackValues) {
        saveStackMarker.getClass();
        savedStackValues.getClass();
        List<AbstractInsnNode> list = this.context.getRestoreStackMarkersForSaveMarker().get(saveStackMarker);
        list.getClass();
        return allocateNewHandle(list.size(), saveStackMarker, savedStackValues);
    }

    public final int createReturnValueVariable(FixStackValue returnValue) {
        returnValue.getClass();
        int firstUnusedLocalVariableIndex = getFirstUnusedLocalVariableIndex();
        updateMaxLocals(returnValue.get_size() + firstUnusedLocalVariableIndex);
        return firstUnusedLocalVariableIndex;
    }

    public final SavedStackDescriptor getBeforeInlineDescriptor(AbstractInsnNode afterInlineMarker) {
        afterInlineMarker.getClass();
        AllocatedHandle allocatedHandle = this.allocatedHandles.get(this.context.getOpeningInlineMethodMarker().get(afterInlineMarker));
        allocatedHandle.getClass();
        return allocatedHandle.getSavedStackDescriptor();
    }

    public final FixStackContext getContext() {
        return this.context;
    }

    public final MethodNode getMethodNode() {
        return this.methodNode;
    }

    public final SavedStackDescriptor getSavedStackDescriptor(AbstractInsnNode restoreStackMarker) {
        restoreStackMarker.getClass();
        AllocatedHandle allocatedHandle = this.allocatedHandles.get(this.context.getSaveStackMarkerForRestoreMarker().get(restoreStackMarker));
        allocatedHandle.getClass();
        return allocatedHandle.getSavedStackDescriptor();
    }

    public final void markAfterInlineMarkerEmitted(AbstractInsnNode afterInlineMarker) {
        afterInlineMarker.getClass();
        AbstractInsnNode abstractInsnNode = this.context.getOpeningInlineMethodMarker().get(afterInlineMarker);
        abstractInsnNode.getClass();
        markEmitted(abstractInsnNode);
    }

    public final void markRestoreStackMarkerEmitted(AbstractInsnNode restoreStackMarker) {
        restoreStackMarker.getClass();
        AbstractInsnNode abstractInsnNode = this.context.getSaveStackMarkerForRestoreMarker().get(restoreStackMarker);
        abstractInsnNode.getClass();
        markEmitted(abstractInsnNode);
    }
}
