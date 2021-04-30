package com.company;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQException;
import java.util.Scanner;

public class ProvaGestorDB {
    public static void main(String[] args) throws XQException {
        Scanner scanner = new Scanner(System.in);
        XQConnection conn = GestorDB.constructor();
        System.out.println("Connected");

        System.out.println("Introduce el año de lanzamiento de una plataforma");
        String platformYear = scanner.next();
        System.out.println(GestorDB.getPlatformsByReleaseYear(conn, platformYear));


        GestorDB.tancarSessio(conn);
        System.out.println("\nDisconnected");
    }
}
