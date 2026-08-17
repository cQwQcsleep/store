package androidx.transition;

import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.collection.ArrayMap;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class TransitionManager {
    private static final String LOG_TAG = "TransitionManager";
    private static Transition sDefaultTransition = new AutoTransition();
    private static ThreadLocal<WeakReference<ArrayMap<ViewGroup, ArrayList<Transition>>>> sRunningTransitions = new ThreadLocal<>();
    static ArrayList<ViewGroup> sPendingTransitions = new ArrayList<>();
    private ArrayMap<Scene, Transition> mSceneTransitions = new ArrayMap<>();
    private ArrayMap<Scene, ArrayMap<Scene, Transition>> mScenePairTransitions = new ArrayMap<>();

    public static class MultiListener implements ViewTreeObserver.OnPreDrawListener, View.OnAttachStateChangeListener {
        ViewGroup mSceneRoot;
        Transition mTransition;

        public MultiListener(Transition transition, ViewGroup viewGroup) {
            this.mTransition = transition;
            this.mSceneRoot = viewGroup;
        }

        private void removeListeners() {
            this.mSceneRoot.getViewTreeObserver().removeOnPreDrawListener(this);
            this.mSceneRoot.removeOnAttachStateChangeListener(this);
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            removeListeners();
            if (!TransitionManager.sPendingTransitions.remove(this.mSceneRoot)) {
                return true;
            }
            final ArrayMap<ViewGroup, ArrayList<Transition>> runningTransitions = TransitionManager.getRunningTransitions();
            ArrayList arrayList = (ArrayList) runningTransitions.get(this.mSceneRoot);
            ArrayList arrayList2 = null;
            if (arrayList == null) {
                arrayList = new ArrayList();
                runningTransitions.put(this.mSceneRoot, arrayList);
            } else if (arrayList.size() > 0) {
                arrayList2 = new ArrayList(arrayList);
            }
            arrayList.add(this.mTransition);
            this.mTransition.addListener(new TransitionListenerAdapter() { // from class: androidx.transition.TransitionManager.MultiListener.1
                @Override // androidx.transition.TransitionListenerAdapter, androidx.transition.Transition.TransitionListener
                public void onTransitionEnd(Transition transition) {
                    ((ArrayList) runningTransitions.get(MultiListener.this.mSceneRoot)).remove(transition);
                    transition.removeListener(this);
                }
            });
            this.mTransition.captureValues(this.mSceneRoot, false);
            if (arrayList2 != null) {
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    ((Transition) it.next()).resume(this.mSceneRoot);
                }
            }
            this.mTransition.playTransition(this.mSceneRoot);
            return true;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            removeListeners();
            TransitionManager.sPendingTransitions.remove(this.mSceneRoot);
            ArrayList arrayList = (ArrayList) TransitionManager.getRunningTransitions().get(this.mSceneRoot);
            if (arrayList != null && arrayList.size() > 0) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ((Transition) it.next()).resume(this.mSceneRoot);
                }
            }
            this.mTransition.clearValues(true);
        }
    }

    public static void beginDelayedTransition(ViewGroup viewGroup, Transition transition) {
        if (sPendingTransitions.contains(viewGroup) || !viewGroup.isLaidOut()) {
            return;
        }
        sPendingTransitions.add(viewGroup);
        if (transition == null) {
            transition = sDefaultTransition;
        }
        Transition transitionMo6453clone = transition.mo6453clone();
        sceneChangeSetup(viewGroup, transitionMo6453clone);
        Scene.setCurrentScene(viewGroup, null);
        sceneChangeRunTransition(viewGroup, transitionMo6453clone);
    }

    private static void changeScene(Scene scene, Transition transition) {
        ViewGroup sceneRoot = scene.getSceneRoot();
        if (sPendingTransitions.contains(sceneRoot)) {
            return;
        }
        Scene currentScene = Scene.getCurrentScene(sceneRoot);
        if (transition == null) {
            if (currentScene != null) {
                currentScene.exit();
            }
            scene.enter();
            return;
        }
        sPendingTransitions.add(sceneRoot);
        Transition transitionMo6453clone = transition.mo6453clone();
        if (currentScene != null && currentScene.isCreatedFromLayoutResource()) {
            transitionMo6453clone.setCanRemoveViews(true);
        }
        sceneChangeSetup(sceneRoot, transitionMo6453clone);
        scene.enter();
        sceneChangeRunTransition(sceneRoot, transitionMo6453clone);
    }

    public static TransitionSeekController controlDelayedTransition(ViewGroup viewGroup, Transition transition) {
        if (!sPendingTransitions.contains(viewGroup) && viewGroup.isLaidOut() && Build.VERSION.SDK_INT >= 34) {
            if (transition.isSeekingSupported()) {
                sPendingTransitions.add(viewGroup);
                Transition transitionMo6453clone = transition.mo6453clone();
                TransitionSet transitionSet = new TransitionSet();
                transitionSet.addTransition(transitionMo6453clone);
                sceneChangeSetup(viewGroup, transitionSet);
                Scene.setCurrentScene(viewGroup, null);
                sceneChangeRunTransition(viewGroup, transitionSet);
                viewGroup.invalidate();
                return transitionSet.createSeekController();
            }
            w01.a("The Transition must support seeking.");
        }
        return null;
    }

    public static TransitionSeekController createSeekController(Scene scene, Transition transition) {
        ViewGroup sceneRoot = scene.getSceneRoot();
        if (!transition.isSeekingSupported()) {
            w01.a("The Transition must support seeking.");
            return null;
        }
        if (sPendingTransitions.contains(sceneRoot)) {
            return null;
        }
        Scene currentScene = Scene.getCurrentScene(sceneRoot);
        if (!sceneRoot.isLaidOut() || Build.VERSION.SDK_INT < 34) {
            if (currentScene != null) {
                currentScene.exit();
            }
            scene.enter();
            return null;
        }
        sPendingTransitions.add(sceneRoot);
        Transition transitionMo6453clone = transition.mo6453clone();
        TransitionSet transitionSet = new TransitionSet();
        transitionSet.addTransition(transitionMo6453clone);
        if (currentScene != null && currentScene.isCreatedFromLayoutResource()) {
            transitionSet.setCanRemoveViews(true);
        }
        sceneChangeSetup(sceneRoot, transitionSet);
        scene.enter();
        sceneChangeRunTransition(sceneRoot, transitionSet);
        return transitionSet.createSeekController();
    }

    public static void endTransitions(ViewGroup viewGroup) {
        sPendingTransitions.remove(viewGroup);
        ArrayList arrayList = (ArrayList) getRunningTransitions().get(viewGroup);
        if (arrayList == null || arrayList.isEmpty()) {
            return;
        }
        ArrayList arrayList2 = new ArrayList(arrayList);
        for (int size = arrayList2.size() - 1; size >= 0; size--) {
            ((Transition) arrayList2.get(size)).forceToEnd(viewGroup);
        }
    }

    public static ArrayMap<ViewGroup, ArrayList<Transition>> getRunningTransitions() {
        ArrayMap<ViewGroup, ArrayList<Transition>> arrayMap;
        WeakReference<ArrayMap<ViewGroup, ArrayList<Transition>>> weakReference = sRunningTransitions.get();
        if (weakReference != null && (arrayMap = weakReference.get()) != null) {
            return arrayMap;
        }
        ArrayMap<ViewGroup, ArrayList<Transition>> arrayMap2 = new ArrayMap<>();
        sRunningTransitions.set(new WeakReference<>(arrayMap2));
        return arrayMap2;
    }

    private Transition getTransition(Scene scene) {
        ArrayMap arrayMap;
        Transition transition;
        Scene currentScene = Scene.getCurrentScene(scene.getSceneRoot());
        if (currentScene != null && (arrayMap = (ArrayMap) this.mScenePairTransitions.get(scene)) != null && (transition = (Transition) arrayMap.get(currentScene)) != null) {
            return transition;
        }
        Transition transition2 = (Transition) this.mSceneTransitions.get(scene);
        return transition2 != null ? transition2 : sDefaultTransition;
    }

    public static void go(Scene scene) {
        changeScene(scene, sDefaultTransition);
    }

    private static void sceneChangeRunTransition(ViewGroup viewGroup, Transition transition) {
        if (transition == null || viewGroup == null) {
            return;
        }
        MultiListener multiListener = new MultiListener(transition, viewGroup);
        viewGroup.addOnAttachStateChangeListener(multiListener);
        viewGroup.getViewTreeObserver().addOnPreDrawListener(multiListener);
    }

    private static void sceneChangeSetup(ViewGroup viewGroup, Transition transition) {
        ArrayList arrayList = (ArrayList) getRunningTransitions().get(viewGroup);
        if (arrayList != null && arrayList.size() > 0) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((Transition) it.next()).pause(viewGroup);
            }
        }
        if (transition != null) {
            transition.captureValues(viewGroup, true);
        }
        Scene currentScene = Scene.getCurrentScene(viewGroup);
        if (currentScene != null) {
            currentScene.exit();
        }
    }

    public void setTransition(Scene scene, Scene scene2, Transition transition) {
        ArrayMap arrayMap = (ArrayMap) this.mScenePairTransitions.get(scene2);
        if (arrayMap == null) {
            arrayMap = new ArrayMap();
            this.mScenePairTransitions.put(scene2, arrayMap);
        }
        arrayMap.put(scene, transition);
    }

    public void transitionTo(Scene scene) {
        changeScene(scene, getTransition(scene));
    }

    public static void go(Scene scene, Transition transition) {
        changeScene(scene, transition);
    }

    public void setTransition(Scene scene, Transition transition) {
        this.mSceneTransitions.put(scene, transition);
    }

    public static void beginDelayedTransition(ViewGroup viewGroup) {
        beginDelayedTransition(viewGroup, null);
    }
}
