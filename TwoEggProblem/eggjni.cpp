#include <stdio.h>

#include "EggProblemTestThread.h" // java compiler-generated JNI header
#include "compute.h"


JNIEXPORT jint JNICALL   Java_EggProblemTestThread_JNI_1compute(JNIEnv *e, jobject obj, jint nb_item, jint height) { 
	int result = compute (nb_item, height);

        return result;

}

