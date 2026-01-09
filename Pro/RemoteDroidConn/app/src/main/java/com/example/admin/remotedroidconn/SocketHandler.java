/**
 * Created by Admin on 10/02/2018.
 */

package com.example.admin.remotedroidconn;

import java.net.*;

public class SocketHandler {

    private static Socket socket;

    public static synchronized Socket getSocket(){
        return socket;
    }

    public static synchronized void setSocket(Socket socket){
        SocketHandler.socket = socket;
    }

}
