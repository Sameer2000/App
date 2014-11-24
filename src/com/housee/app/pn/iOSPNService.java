package com.housee.app.pn;

import javapns.Push;

public class iOSPNService {

    public static void main(String[] args) throws Exception{
        Push.alert("Hello World!", "keystore.p12", "keystore_password", false, "Your token");
    }

    public static void push(String message,String deviceToken) throws Exception{
        Push.alert(message, "keystore.p12", "keystore_password", false, deviceToken);
    }

}
