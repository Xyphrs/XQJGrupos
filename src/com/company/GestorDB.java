package com.company;

import net.xqj.exist.ExistXQDataSource;

import javax.xml.xquery.*;
import java.util.ArrayList;
import java.util.List;

public class GestorDB {
    public static XQConnection constructor() throws XQException {
        XQDataSource xqs = new ExistXQDataSource();

        xqs.setProperty("serverName", "localhost");
        xqs.setProperty("port", "8080");

        return xqs.getConnection();
    }

//Insertar una compañia y si tienes juegos tambien insertarlos. -> Romero
//Insertar una plataforma y si tiene juegos que pertenecen a esa plataforma tambien insertarlos -> Llera
//
//Eliminar una compañia, y si esa compañia tiene juegos, tambien -> Llera
//Eliminar un juego por su ID -> Roger
//
//Actualizar el networth de una compañia -> Romero
//Actualizar el pais de la compañia -> Roger
//
//Query 1: Juegos que tengan companyID X -> Llera
//Query 2: Compañias que pertenezcan a X pais -> Roger
//Query 3: Plataformas que el releaseYear sea mayor que X año -> Romero

    public static void insertCompany(XQConnection conn, String companyID, String companyName, String companyYear, String companyCountry, String companyNetworth,
                                     String gameID, String gameName, String gameReleaseYear) throws XQException {

        XQExpression xqe = conn.createExpression();
        String insertCompany =
                "update insert \n" +
                "<company id='"+ companyID +"'>\n" +
                "<name>"+ companyName +"</name>\n" +
                "<founding-year>"+ companyYear +"</founding-year>\n" +
                "<country>"+ companyCountry +"</country>\n" +
                "<networth>"+ companyNetworth +"€ million</networth>\n" +
                "</company>\n" +
                "preceding collection('/db/resources/')/companies/company[1]";
        xqe.executeCommand(insertCompany);

        if (gameID == null) {
            return;
        } else {
            String insertGame =
                    "update insert \n" +
                    "<game id='"+ gameID +"' companyID='"+ companyID +"'>\n" +
                    "<name>"+ gameName +"</name>\n" +
                    "<developedBy>"+ companyName +"</developedBy>\n" +
                    "<releaseYear>"+ gameReleaseYear +"</releaseYear>\n" +
                    "</game>\n" +
                    "preceding collection('/db/resources/')/topGames/game[1]";
            xqe.executeCommand(insertGame);
        }
    }

    public static void insertPlatform(XQConnection conn) throws XQException {
        XQExpression xqe = conn.createExpression();

    }

    public static void deleteCompany(XQConnection conn) throws XQException {
        XQExpression xqe = conn.createExpression();

    }

    public static void deleteGameById(XQConnection conn) throws XQException {
        XQExpression xqe = conn.createExpression();

    }

    public static void updateNetworth(XQConnection conn, String id, String value) throws XQException {
        XQExpression xqe = conn.createExpression();
        String networth = "update value\n" +
                "collection('/db/resources/')/companies/company[@id = '"+ id +"']/networth\n" +
                "with '"+ value + "€ million'";

        xqe.executeCommand(networth);
    }

    public static void updateCountry(XQConnection conn) throws XQException {
        XQExpression xqe = conn.createExpression();

    }

    public static void getGamesByCompanyId(XQConnection conn) throws XQException {
        XQExpression xqe = conn.createExpression();

    }

    public static void getCompaniesByCountry(XQConnection conn) throws XQException {
        XQExpression xqe = conn.createExpression();

    }

    public static List<Platforms> getPlatformsByReleaseYear(XQConnection conn, String year) throws XQException {
        List<Platforms> platformsList = new ArrayList<>();
        XQExpression xqe = conn.createExpression();

        String id = "collection('/db/resources/')/platforms/platform[releaseYear >= " + year +"]/@platformID/string()";
        XQResultSequence idxqrs = xqe.executeQuery(id);

        while (idxqrs.next()) {
            Platforms platform = new Platforms();

            platform.setId(idxqrs.getItemAsString(null));

            String name = "collection('/db/resources/')/platforms/platform[@platformID = " + platform.getId() + "]/name/string()";
            String createdBy = "collection('/db/resources/')/platforms/platform[@platformID = " + platform.getId() + "]/createdBy/string()";
            String releaseYear = "collection('/db/resources/')/platforms/platform[@platformID = " + platform.getId() + "]/releaseYear/string()";

            XQResultSequence namexqrs = xqe.executeQuery(name);
            XQResultSequence createdByxqrs = xqe.executeQuery(createdBy);
            XQResultSequence releaseYearxqrs = xqe.executeQuery(releaseYear);


            while (namexqrs.next()) {
                platform.setName(namexqrs.getItemAsString(null));
            }

            while (createdByxqrs.next()) {
                platform.setCreatedBy(createdByxqrs.getItemAsString(null));
            }

            while (releaseYearxqrs.next()) {
                platform.setReleaseYear(releaseYearxqrs.getItemAsString(null));
            }

            platformsList.add(platform);
        }
        return platformsList;
    }

    public static void tancarSessio(XQConnection conn) throws XQException {
        conn.close();
    }

}
