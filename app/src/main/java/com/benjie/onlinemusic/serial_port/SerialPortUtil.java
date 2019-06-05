package com.benjie.onlinemusic.serial_port;

import android.os.SystemClock;

import com.benjie.onlinemusic.util.BLog;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Roy_lee on 2019/6/2.
 * <p>
 * 串口工具类
 */

public class SerialPortUtil {

    private static SerialPort serialPort = null;
    private static InputStream mInputStream = null;
    private static OutputStream mOutputStream = null;

    private static boolean flag = false;


    // 串口消息处理
    private static List<Byte> currentMsg = new ArrayList<>();
    private static final int READING_HEAD_FLAG = 1;
    private static final int READING_FOOTER_FLAG = 2;
    private static int readFlag = READING_HEAD_FLAG;


    /**
     * ================================打开串口================================
     *
     * @param port     串口端口号
     * @param baudrate 波特率
     */

    public static void openSerialPort(String port, int baudrate) {
        BLog.d("openSerialPort::打开串口");
        try {
            serialPort = new SerialPort(new File("/dev/" + port), baudrate, 0);
            // 获取打开的串口中的输入输出流，以便于串口数据的收发
            mInputStream = serialPort.getInputStream();
            mOutputStream = serialPort.getOutputStream();
            //
            if (mInputStream != null) {
                flag = true;
                //接收串口数据
                receiveSerialPort();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * ================================接收串口数据================================
     */
    public static void receiveSerialPort() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                //循环接收串口数据
                while (flag) {
                    try {

                        int dataLen = mInputStream.available();
                        if (dataLen == 0) {
                            continue;
                        }
                        byte[] data = new byte[dataLen];
                        mInputStream.read(data);


                        SystemClock.sleep(500);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


    /**
     * ================================发送串口数据================================
     *
     * @param cmd 要发送的数据
     */

    private static void sendSerialPort(final byte[] cmd) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                BLog.d("sendSerialPort::发送串口数据：" + byteTo16String(cmd));
                try {
                    mOutputStream.write(cmd);
                    mOutputStream.flush();
//                    Log.i(TAG,"发送串口数据成功！");
                } catch (IOException e) {
                    e.printStackTrace();
                    BLog.d("发送串口数据失败！");
                }
            }
        }).start();

    }


    /**
     * ================================发送串口数据================================
     *
     * @param data 要发送的数据
     */

    public static void sendSerialPort(final String data) {
        BLog.d("sendSerialPort::发送串口数据：" + data);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    byte[] sendData = data.getBytes();
                    mOutputStream.write(sendData);
                    mOutputStream.flush();
                    BLog.d("sendSerialPort::发送串口数据成功！");

                } catch (IOException e) {
                    e.printStackTrace();
                    BLog.d("sendSerialPort::发送串口数据失败！");
                }
            }
        }).start();

    }

    /**
     * ================================关闭串口================================
     * 关闭串口中的输入输出流
     * 然后将flag的值设为flag，终止接收数据线程
     */
    public static void closeSerialPort() {
        flag = false;
        BLog.d("sendSerialPort::关闭串口");
        try {
            if (serialPort != null) {
                serialPort.close();
            }

            if (mInputStream != null) {
                mInputStream.close();
            }

            if (mOutputStream != null) {
                mOutputStream.close();
            }

            BLog.d("sendSerialPort::串口关闭成功");
        } catch (IOException e) {
            BLog.d("sendSerialPort::关闭串口失败");
            e.printStackTrace();
        }

    }


    //==============================================================================================


    /**
     * 将字节数组转换成十六进制字符串
     */
    public static String byteTo16String(byte[] data) {
        StringBuffer buffer = new StringBuffer();
        for (byte b : data) {
            buffer.append(byteTo16String(b));
        }
        return buffer.toString();
    }

    /**
     * 将字节转换成十六进制字符串
     * int转byte对照表
     * [128,255],0,[1,128)
     * [-128,-1],0,[1,128)
     */
    public static String byteTo16String(byte b) {
        StringBuffer buffer = new StringBuffer();
        int aa = (int) b;
        if (aa < 0) {
            buffer.append(Integer.toString(aa + 256, 16) + " ");
        } else if (aa == 0) {
            buffer.append("00 ");
        } else if (aa > 0 && aa <= 15) {
            buffer.append("0" + Integer.toString(aa, 16) + " ");
        } else if (aa > 15) {
            buffer.append(Integer.toString(aa, 16) + " ");
        }
        return buffer.toString();
    }


    /**
     * 计算CRC16校验码
     *
     * @param data 需要校验的字符串
     * @return 校验码
     */
    public static String getCRC(String data) {
        data = data.replace(" ", "");
        int len = data.length();
        if (!(len % 2 == 0)) {
            return "0000";
        }
        int num = len / 2;
        byte[] para = new byte[num];
        for (int i = 0; i < num; i++) {
            int value = Integer.valueOf(data.substring(i * 2, 2 * (i + 1)), 16);
            para[i] = (byte) value;
        }
        return getCRC(para);
    }

    /**
     * 计算CRC16校验码
     *
     * @param bytes 字节数组
     * @return {@link String} 校验码
     * @since 1.0
     */
    public static String getCRC(byte[] bytes) {
        //CRC寄存器全为1
        int CRC = 0x0000ffff;
        //多项式校验值
        int POLYNOMIAL = 0x0000a001;
        int i, j;
        for (i = 0; i < bytes.length; i++) {
            CRC ^= ((int) bytes[i] & 0x000000ff);
            for (j = 0; j < 8; j++) {
                if ((CRC & 0x00000001) != 0) {
                    CRC >>= 1;
                    CRC ^= POLYNOMIAL;
                } else {
                    CRC >>= 1;
                }
            }
        }
        // 结果转换为16进制
        String result = Integer.toHexString(CRC).toUpperCase();
        if (result.length() != 4) {
            StringBuffer sb = new StringBuffer("0000");
            result = sb.replace(4 - result.length(), 4, result).toString();
        }
        // 交换高低位
        return result.substring(2, 4) + " " + result.substring(0, 2) + " ";
    }


}
