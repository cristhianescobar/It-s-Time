package com.cristhianescobar.itstime.utils;

import android.animation.Animator;
import android.annotation.TargetApi;
import android.transition.TransitionValues;
import android.transition.Visibility;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;

/**
 * Created by kenneth.mojica on 1/15/15.
 */
@TargetApi(21)
public class RevealTransition extends Visibility {

    // center x and y coordinates where the reveal transition will begin
    private int cx, cy;
    private Integer maxDistance = null;

    private Animator.AnimatorListener appearAnimationListener;
    private Animator.AnimatorListener disappearAnimationListener;

    public RevealTransition(int cx, int cy) {
        this.cx = cx;
        this.cy = cy;
    }

    public void setOnAppearAnimationListener(Animator.AnimatorListener appearAnimationListener) {
        this.appearAnimationListener = appearAnimationListener;
    }

    public void setDisappearAnimationListener(Animator.AnimatorListener disappearAnimationListener) {
        this.disappearAnimationListener = disappearAnimationListener;
    }

    @Override
    public Animator onAppear(ViewGroup sceneRoot, final View view, TransitionValues startValues, TransitionValues endValues) {

        // TODO handle the back button when the animation is still in progress
        // TODO this method is called for every view, handle that...
        maxDistance = maxDistance(cx, cy, sceneRoot.getWidth(), sceneRoot.getHeight());
        Animator revealAnimator = ViewAnimationUtils.createCircularReveal(sceneRoot, cx, cy, 0f, maxDistance);

        if(appearAnimationListener != null) {
            revealAnimator.addListener(appearAnimationListener);
        }

        return revealAnimator;
    }

    @Override
    public Animator onDisappear(ViewGroup sceneRoot, final View view, TransitionValues startValues, TransitionValues endValues) {

        maxDistance = maxDistance(cx, cy, sceneRoot.getWidth(), sceneRoot.getHeight());
        Animator revealAnimator = ViewAnimationUtils.createCircularReveal(sceneRoot, cx, cy, maxDistance, 0f);

        if(disappearAnimationListener != null) {
            revealAnimator.addListener(disappearAnimationListener);
        }

        return revealAnimator;
    }

    public int maxDistance(int x, int y, int width, int height) {

        int topLeftCornerDistance = distanceBetweenTwoPoints(x, y, 0, 0);
        int topRightCornerDistance = distanceBetweenTwoPoints(x, y, width, 0);
        int bottomLeftCornerDistance = distanceBetweenTwoPoints(x, y, 0, height);
        int bottomRightCornerDistance = distanceBetweenTwoPoints(x, y, width, height);

        return Math.max(Math.max(Math.max(topLeftCornerDistance, topRightCornerDistance), bottomLeftCornerDistance), bottomRightCornerDistance);
    }

    public int distanceBetweenTwoPoints(int x1, int y1, int x2, int y2) {
        return (int) Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}