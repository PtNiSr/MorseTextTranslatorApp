package com.ni___ckel.morseenglishtranslate;

import android.app.Application;
import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;


public class TranslationFromEngtoMorseViewModel extends AndroidViewModel {
    private String strEngText = "";
    private MutableLiveData<String> strMutEng = new MutableLiveData<>();

    private CameraManager mCameraManager;
    private String mCameraId;

    public TranslationFromEngtoMorseViewModel(@NonNull Application application) {
        super(application);
    }

    public void setStrEngText(String strEngText) {
        this.strEngText = strEngText;
        strMutEng.setValue(strEngText);
    }

    public LiveData<String> getStrMutEng() {
        return strMutEng;
    }

    public String TranslateFromEng() {
        String translationFromEng = " ";
        if (strEngText != null) {
            String[] subStr;
            subStr = strEngText.toLowerCase().split("");      //making collection of letters
            for (int i = 0; i < subStr.length; i++) {
                subStr[i] = EngTextConverter(subStr[i]);        //conversion from text to Morse code
            }
            translationFromEng = String.join(" ", subStr);      //build all elements into string
        }
        return translationFromEng;
    }

    public String EngTextConverter(String string) {
        switch (string) {
            case "a":
                string = ".-";
                break;
            case "b":
                string = "-...";
                break;
            case "c":
                string = "-.-.";
                break;
            case "d":
                string = "-..";
                break;
            case "e":
                string = ".";
                break;
            case "f":
                string = "..-.";
                break;
            case "g":
                string = "--.";
                break;
            case "h":
                string = "....";
                break;
            case "i":
                string = "..";
                break;
            case "j":
                string = ".---";
                break;
            case "k":
                string = "-.-";
                break;
            case "l":
                string = ".-..";
                break;
            case "m":
                string = "--";
                break;
            case "n":
                string = "-.";
                break;
            case "o":
                string = "---";
                break;
            case "p":
                string = ".--.";
                break;
            case "q":
                string = "--.-";
                break;
            case "r":
                string = ".-.";
                break;
            case "s":
                string = "...";
                break;
            case "t":
                string = "-";
                break;
            case "u":
                string = "..-";
                break;
            case "v":
                string = "...-";
                break;
            case "w":
                string = ".--";
                break;
            case "x":
                string = "-..-";
                break;
            case "y":
                string = "-.--";
                break;
            case "z":
                string = "--..";
                break;
            case "1":
                string = "----";
                break;
            case "2":
                string = "..---";
                break;
            case "3":
                string = "...--";
                break;
            case "4":
                string = "....-";
                break;
            case "5":
                string = ".....";
                break;
            case "6":
                string = "-....";
                break;
            case "7":
                string = "--...";
                break;
            case "8":
                string = "---..";
                break;
            case "9":
                string = "----.";
                break;
            case "-----":
                string = "-----";
                break;
            case " ":
                string = "  ";
                break;
            default:
                string = "#";
                break;
        }
        return string;
    }


    public void sendMessageViaLight(String s, Context context) {

        String[] stringToSend;
        stringToSend = s.split("");      //making collection of dits, dashes and spaces

        mCameraManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
        try {
            mCameraId = mCameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

        Thread[] thread;
        thread = new Thread[stringToSend.length];

        for (int i = 0; i < stringToSend.length; i++) {

            switch (stringToSend[i]) {
                case ".":
                    thread[i] = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                mCameraManager.setTorchMode(mCameraId, true);
                            } catch (CameraAccessException e) {
                                e.printStackTrace();
                            }
                            try {
                                Thread.sleep(300);
                            } catch (InterruptedException ex) {
                            }


                            try {
                                mCameraManager.setTorchMode(mCameraId, false);
                            } catch (CameraAccessException e) {
                                e.printStackTrace();
                            }
                            try {
                                Thread.sleep(300);
                            } catch (InterruptedException ex) {
                            }
                        }
                    });
                    break;

                case "-":
                    thread[i] = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                mCameraManager.setTorchMode(mCameraId, true);
                            } catch (CameraAccessException e) {
                                e.printStackTrace();
                            }
                            try {
                                Thread.sleep(900);
                            } catch (InterruptedException ex) {
                            }

                            try {
                                mCameraManager.setTorchMode(mCameraId, false);
                            } catch (CameraAccessException e) {
                                e.printStackTrace();
                            }
                            try {
                                Thread.sleep(300);
                            } catch (InterruptedException ex) {
                            }
                        }
                    });
                    break;

                default:
                    thread[i] = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                mCameraManager.setTorchMode(mCameraId, false);
                            } catch (CameraAccessException e) {
                                e.printStackTrace();
                            }
                            try {
                                Thread.sleep(600);
                            } catch (InterruptedException ex) {
                            }
                        }
                    });
                    break;
            }
        }

        for (int i = 0; i < thread.length; i++) {
            thread[i].run();
        }

    }
}
