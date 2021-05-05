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
                System.out.println("\nIntroduce el nombre del juego de la compañia");
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

        System.out.println("\nINSERT PLATFORM\n----------------------");
        System.out.println("\nIntroduce el id de la plataforma");
        String insertPlatformID = scanner.next();
        System.out.println("\nIntroduce el nombre de la plataforma");
        String insertPlatformName = scanner.next();
        System.out.println("\nIntroduce el creador de la plataforma");
        String insertcreatedBy = scanner.next();
        System.out.println("\nIntroduce el año de creacion de la plataforma");
        String insertPlatformYear = scanner.next();

        String responseinsertPlatform;
        do {
            System.out.println("\nEsta plataforma tiene juegos? (S/N)");
            responseinsertPlatform = scanner.next();
            responseinsertPlatform = responseinsertPlatform.toUpperCase(Locale.ROOT);

            if (responseinsertPlatform.equals("S")) {
                System.out.println("\nIntroduce el id del juego");
                String gameID = scanner.next();
                System.out.println("\nIntroduce el companyID del juego");
                String companyID = scanner.next();
                System.out.println("\nIntroduce el nombre del juego");
                String insertCompanyGameName = scanner.next();
                System.out.println("\nIntroduce el desarollador del juego");
                String insertdevelopedBy = scanner.next();
                System.out.println("\nIntroduce el año de creacion del juego");
                String insertCompanyGameYear = scanner.next();
                GestorDB.insertPlatform(conn, insertPlatformID, insertPlatformName, insertcreatedBy, insertPlatformYear, gameID, companyID, insertCompanyGameName, insertdevelopedBy, insertCompanyGameYear);
                break;
            } else if (responseinsertPlatform.equals("N")) {
                GestorDB.insertPlatform(conn, insertPlatformID, insertPlatformName, insertcreatedBy, insertPlatformYear, null, null, null, null, null);
                break;
            } else {
                System.out.println("\nIntroduce 'S' o 'N'");
            }

        } while (true);


        System.out.println("\nDELETE COMPANY\n----------------------");
        System.out.println("Introduce el companyID:");
        String companyid = scanner.next();
        GestorDB.deleteCompany(conn, companyid);

        System.out.println("\nDELETE GAME BY ID\n----------------------");
        String gameToDelete = scanner.next();
        System.out.println("Eliminando juego con ID: " + gameToDelete + " ...");
        GestorDB.deleteGameById(conn, "1");
        System.out.println("Juego eliminado correctamente!");

        System.out.println("\nUPDATE NETWORTH\n----------------------");
        System.out.println("\nIntroduce el id de la compañia");
        String networthUpdateId = scanner.next();
        System.out.println("\nIntroduce el nuevo 'networth' de la compañia (1 = 1.000.000)");
        String networthUpdate = scanner.next();
        GestorDB.updateNetworth(conn, networthUpdateId, networthUpdate);

        System.out.println("\nUPDATE COUNTRY\n----------------------");
        String countryID = scanner.next();
        String newCountry = scanner.next();
        System.out.println("Cambiando el pais de la compañia con la ID: " + countryID + " ...");
        GestorDB.updateCountry(conn, countryID, newCountry);
        System.out.println("Pais cambiado a: " + newCountry);

        System.out.println("\nPRINT GAMES BY COMPANY ID\n----------------------");
        System.out.println("Introduce el companyID para obtener sus juegos");
        String companid = scanner.next();
        System.out.println(GestorDB.getGamesByCompanyId(conn, companid));

        System.out.println("\nPRINT COMPANIES BY COUNTRY\n----------------------");
        String country = scanner.next();
        System.out.println("Buscando empresas que pertenecen a: " + country);
        System.out.println(GestorDB.getCompaniesByCountry(conn, country));

        System.out.println("\nPRINT PLATFORMS BY YEAR\n----------------------");
        System.out.println("Introduce el año de lanzamiento de una plataforma");
        String platformYear = scanner.next();
        System.out.println(GestorDB.getPlatformsByReleaseYear(conn, platformYear));

        GestorDB.tancarSessio(conn);
        System.out.println("\nDisconnected");
    }
}
