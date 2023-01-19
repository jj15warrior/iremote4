package com.jj15.iremote4;

public class ProtoParser {
    public String parse(String proto){
        switch (proto) {
            case "XMP-1":
                proto = "xmp";
                break;
            case "G.I.Cable":
                proto = "GI_cable";
                break;
            case "Aiwa":
                proto = "aiwa";
                break;
            case "RC5":
                proto = "rc5";
                break;
            case "RC6":
                proto = "rc6";
                break;
            case "RCMM":
                proto = "rcmm";
                break;
            case "MCE":
                proto = "mce";
                break;
            case "NEC1":
                proto = "nec1";
                break;
            case "NEC2":
                proto = "nec2";
                break;
            case "Panasonic_Old":
                proto = "panasonic_old";
                break;
            case "Fujitsu":
                proto = "fujitsu";
                break;
            case "RECS80":
                proto = "recs80_45";
                break;
            case "Dish_Network":
                proto = "DishPlayer_Network";
                break;
            case "RCA":
                proto = "rca";
                break;
            case "Pioneer":
                proto = "pioneer";
                break;
            case "Pioneer2":
                proto = "pioneer2";
                break;
            case "JVC{2}":
                proto = "jvc";
                break;
            case "Panasonic":
                proto = "panasonic";
                break;
            case "Denon":
                proto = "denon";
                break;
            case "Denon{1}":
                proto = "denon";
                break;
            case "Denon{2}":
                proto = "denon";
                break;
            case "Sharp{1}":
                proto = "sharp";
                break;
            case "Sharp{2}":
                proto = "sharp";
                break;
            case "F12":
                proto = "f12";
                break;
            case "Emerson":
                proto = "emerson";
                break;
            case "XMP-2":
                proto = "xmp";
                break;
            case "Blaupunkt":
                proto = "blaupunkt";
                break;
            case "JVC":
                proto = "jvc";
                break;
            case "Zaptor-56":
                proto = "Zaptor";
                break;
            case "G.I.4DTV":
                proto = "GI4dtv";
                break;
            case "Panasonic2":
                proto = "panasonic2";
                break;
            case "Tivo unit=0":
                proto = "Tivo-Nec1";
                break;
            case "StreamZap":
                proto = "streamzap";
                break;
            case "NEC1-y1":
                proto = "nec1";
                break;
            case "NEC1-y2":
                proto = "nec1";
                break;
            case "Tivo unit=1":
                proto = "Tivo-Nec1";
                break;
            case "Tivo unit=2":
                proto = "Tivo-Nec1";
                break;
            case "Tivo unit=3":
                proto = "Tivo-Nec1";
                break;
            case "Tivo unit=4":
                proto = "Tivo-Nec1";
                break;
            case "Tivo unit=5":
                proto = "Tivo-Nec1";
                break;
            case "Tivo unit=6":
                proto = "Tivo-Nec1";
                break;
            case "Tivo unit=7":
                proto = "Tivo-Nec1";
                break;
            case "Tivo unit=8":
                proto = "Tivo-Nec1";
                break;
            case "Tivo unit=9":
                proto = "Tivo-Nec1";
                break;
            case "Tivo unit=10":
                proto = "Tivo-Nec1";
                break;
            case "Tivo unit=11":
                proto = "Tivo-Nec1";
                break;
            case "Tivo unit=12":
                proto = "Tivo-Nec1";
                break;
            case "NEC":
                proto = "nec1";
                break;
        }
        return proto;
    }
}
