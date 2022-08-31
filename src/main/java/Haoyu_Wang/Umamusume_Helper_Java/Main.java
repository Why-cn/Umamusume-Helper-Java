package Haoyu_Wang.Umamusume_Helper_Java;

import Haoyu_Wang.Umamusume_Helper_Java.Utils.PropertiesLoader;

import java.io.IOException;

public class Main {
    public static void main(String[] args){
        PropertiesLoader.load();
        System.out.println(PropertiesStorage.AndroidDeviceName);
        System.out.println(PropertiesStorage.WinGUIScaling);
    }
}