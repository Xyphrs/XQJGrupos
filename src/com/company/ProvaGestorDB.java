package com.company;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQException;
import java.util.Locale;
import java.util.Scanner;

public class ProvaGestorDB {
    public static void main(String[] args) throws XQException {
        Scanner scanner = new Scanner(System.in);
        XQConnection conn = GestorDB.constructor();
        System.out.println("Connected");

        System.out.println("\nINSERT COMPANY\n----------------------");
        System.out.println("\nIntroduce el id de la compañia");
        String insertCompanyID = scanner.next();
        System.out.println("\nIntroduce el nombre de la compañia");
        String insertCompanyName = scanner.next();
        System.out.println("\nIntroduce el año de creacion de la compañia");
        String insertCompanyYear = scanner.next();
        System.out.println("\nIntroduce el pais de la compañia");
        String insertCompanyCountry = scanner.next();
        System.out.println("\nIntroduce el 'networth' de la compañia (1 = 1.000.000)");
        String insertCompanyNetworth = scanner.next();

        String responseInsertCompany;
        do {
            System.out.println("\nEsta compañia tiene juegos? (S/N)");
            responseInsertCompany = scanner.next();
            responseInsertCompany = responseInsertCompany.toUpperCase(Locale.ROOT);

            if (responseInsertCompany.equals("S")) {
                System.out.println("\nIntroduce el id del juego de la compañia");
                String insertCompanyGameID = scanner.next();
                System.out.println("\nIntroduce el nombre del jeugo de la compañia");
                String insertCompanyGameName = scanner.next();
                System.out.println("\nIntroduce el año de creacion del juego de la compañia");
                String insertCompanyGameYear = scanner.next();
                GestorDB.insertCompany(conn, insertCompanyID, insertCompanyName, insertCompanyYear, insertCompanyCountry, insertCompanyNetworth, insertCompanyGameID, insertCompanyGameName, insertCompanyGameYear);
                break;
            } else if (responseInsertCompany.equals("N")) {
                GestorDB.insertCompany(conn, insertCompanyID, insertCompanyName, insertCompanyYear, insertCompanyCountry, insertCompanyNetworth, null, null, null);
                break;
            } else {
                System.out.println("\nIntroduce 'S' o 'N'");
            }

        } while (true);

        System.out.println("\nUPDATE NETWORTH\n----------------------");
        System.out.println("\nIntroduce el id de la compañia");
        String networthUpdateId = scanner.next();
        System.out.println("\nIntroduce el nuevo 'networth' de la compañia (1 = 1.000.000)");
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
