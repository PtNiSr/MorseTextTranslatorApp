package com.ni___ckel.morseenglishtranslate;

import android.app.Application;
import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class TranslationViewModel extends AndroidViewModel {
    private MutableLiveData<String> strMut = new MutableLiveData<>();
    private String strUp = "";


    public TranslationViewModel(@NonNull Application application) {
        super(application);
    }

    public void addDit() {
        strUp = strUp + ".";
        strMut.setValue(strUp);
    }

    public void addDash() {
        strUp = strUp + "-";
        strMut.setValue(strUp);
    }

    public void addSpace() {
        strUp = strUp + "_";
        strMut.setValue(strUp);
    }

    public void strDelete() {
        if (!strUp.isEmpty()) {
            strUp = strUp.substring(0, strUp.length() - 1);
            strMut.setValue(strUp);
        }
    }

    public LiveData<String> getStr() {
        return strMut;
    }

    public String translateFromMorse() {
        String translationFromMorse = " ";
        if (strUp != null) {
            String[] subStr;
            subStr = strUp.split("_");      //making collection of letters in Morse code
            for (int i = 0; i < subStr.length; i++) {
                subStr[i] = MorseConverter(subStr[i]);
            }
            translationFromMorse = String.join("", subStr);
            translationFromMorse = translationFromMorse.replace('@', ' ');      //@ - was a symbol which separates the words NOT letters
        }
        return translationFromMorse;
    }

    public String MorseConverter(String string) {       //converter from Morse code to English + numbers
        switch (string) {
            case ".-":
                string = "a";
                break;
            case "-...":
                string = "b";
                break;
            case "-.-.":
                string = "c";
                break;
            case "-..":
                string = "d";
                break;
            case ".":
                string = "e";
                break;
            case "..-.":
                string = "f";
                break;
            case "--.":
                string = "g";
                break;
            case "....":
                string = "h";
                break;
            case "..":
                string = "i";
                break;
            case ".---":
                string = "j";
                break;
            case "-.-":
                string = "k";
                break;
            case ".-..":
                string = "l";
                break;
            case "--":
                string = "m";
                break;
            case "-.":
                string = "n";
                break;
            case "---":
                string = "o";
                break;
            case ".--.":
                string = "p";
                break;
            case "--.-":
                string = "q";
                break;
            case ".-.":
                string = "r";
                break;
            case "...":
                string = "s";
                break;
            case "-":
                string = "t";
                break;
            case "..-":
                string = "u";
                break;
            case "...-":
                string = "v";
                break;
            case ".--":
                string = "w";
                break;
            case "-..-":
                string = "x";
                break;
            case "-.--":
                string = "y";
                break;
            case "--..":
                string = "z";
                break;
            case "----":
                string = "1";
                break;
            case "..---":
                string = "2";
                break;
            case "...--":
                string = "3";
                break;
            case "....-":
                string = "4";
                break;
            case ".....":
                string = "5";
                break;
            case "-....":
                string = "6";
                break;
            case "--...":
                string = "7";
                break;
            case "---..":
                string = "8";
                break;
            case "----.":
                string = "9";
                break;
            case "-----":
                string = "0";
                break;
            case "":
                string = "@";       //to separate words (not letters) by double (or more) spaces
                break;
            default:
                string = "#";
                break;
        }
        return string;
    }

    public boolean mistakeMorse(String s) {     //Checking if Morse code were entered correct.
        boolean mistakeInMorse = s.contains("#");
        return mistakeInMorse;
    }


}
