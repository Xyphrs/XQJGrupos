package com.company;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQException;
import java.util.Scanner;

public class ProvaGestorDB {
    public static void main(String[] args) throws XQException {
        Scanner scanner = new Scanner(System.in);
        XQConnection conn = GestorDB.constructor();
        System.out.println("Connected");

        System.out.println("\nUPDATE NETWORTH\n----------------------");
        System.out.println("\nIntroduce el id de la compañia");
        String networthUpdateId = scanner.next();
        System.out.println("\nIntroduce el nuevo networth de la compañia (1 = 1.000.000)");
        String networthUpdate = scanner.next();
        GestorDB.updateNetworth(conn, networthUpdateId, networthUpdate);

        System.out.println("\nPRINT PLATFORMS BY YEAR\n----------------------");
        System.out.println("Introduce el año de lanzamiento de una plataforma");
        String platformYear = scanner.next();
        System.out.println(GestorDB.getPlatformsByReleaseYear(conn, platformYear));


        GestorDB.tancarSessio(conn);
        System.out.println("\nDisconnected");
    }
}
