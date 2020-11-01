//
// Created by Matthew on 2020/10/29.
//
#include "jni.h"
#include "string"

extern "C" JNIEXPORT jint JNICALL
Java_me_xuwanjin_trounce_WireGuardBackend_wireGuardTurnOn(JNIEnv* env, jobject, jstring configName, jint tun_fd, jstring config){
    return 1;
}
