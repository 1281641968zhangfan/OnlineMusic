/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class com_benjie_onlinemusic_serial_port_SerialPort */

#ifndef _Included_com_benjie_onlinemusic_serial_port_SerialPort
#define _Included_com_benjie_onlinemusic_serial_port_SerialPort
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_benjie_onlinemusic_serial_port_SerialPort
 * Method:    open
 * Signature: (Ljava/lang/String;II)Ljava/io/FileDescriptor;
 */
JNIEXPORT jobject JNICALL Java_com_benjie_onlinemusic_serial_1port_SerialPort_open
  (JNIEnv *, jclass, jstring, jint, jint);

/*
 * Class:     com_benjie_onlinemusic_serial_port_SerialPort
 * Method:    close
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_com_benjie_onlinemusic_serial_1port_SerialPort_close
  (JNIEnv *, jobject);

#ifdef __cplusplus
}
#endif
#endif