package com.intellij.util.io.pagecache.impl;

import java.util.concurrent.ThreadLocalRandom;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class FrugalQuantileEstimator {
    private double currentEstimation;
    private final double step;
    private int targetPercentileToEstimate;

    public FrugalQuantileEstimator(int i, double d, double d2) {
        if (d > 0.0d) {
            updateTargetPercentile(i);
            this.currentEstimation = d2;
            this.step = d;
        } else {
            throw new IllegalArgumentException("step(=" + d + ") must be >0");
        }
    }

    public double currentEstimation() {
        return this.currentEstimation;
    }

    public int percentileToEstimate() {
        return this.targetPercentileToEstimate;
    }

    public String toString() {
        return "FrugalQuantileEstimator[target: " + this.targetPercentileToEstimate + " %-ile, current: " + this.currentEstimation + ", step: " + this.step + ']';
    }

    public double updateEstimation(int i) {
        int iNextInt = ThreadLocalRandom.current().nextInt(100);
        double d = i;
        double d2 = this.currentEstimation;
        if (d > d2 && iNextInt <= this.targetPercentileToEstimate) {
            this.currentEstimation = d2 + this.step;
        } else if (d < d2 && iNextInt > this.targetPercentileToEstimate) {
            this.currentEstimation = d2 - this.step;
        }
        return currentEstimation();
    }

    public void updateTargetPercentile(int i) {
        if (i <= 0 || i >= 100) {
            ty8.a("percentileToEstimate(=", i, ") must be in (0, 100)");
        } else {
            this.targetPercentileToEstimate = i;
        }
    }
}
