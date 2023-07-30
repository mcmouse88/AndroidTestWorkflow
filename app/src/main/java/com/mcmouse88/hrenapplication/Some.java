package com.mcmouse88.hrenapplication;

import org.jetbrains.annotations.NotNull;

import kotlin.jvm.functions.Function0;

public class Some {

    public static final void func(@NotNull Function0 body) {
        // some code before call invoke body
        body.invoke();
        // some code after call invoke body
    }
}
