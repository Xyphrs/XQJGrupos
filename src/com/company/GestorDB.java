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

    public static void insertCompany(XQConnection conn, String companyID, String companyName, String companyYear, String companyCountry, String companyNetworth, String gameID, String gameName, String gameReleaseYear) throws XQException {

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

        if (gameID != null) {
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

    public static void insertPlatform(XQConnection conn, String insertPlatformID, String insertPlatformName, String insertcreatedBy, String insertPlatformYear, String gameID, String companyID, String gameName, String insertdevelopedBy, String gameReleaseYear) throws XQException {
        XQExpression xqe = conn.createExpression();
        String insertPlatform =
                "update insert \n" +
                        "<platform platformID='"+ insertPlatformID +"'>\n" +
                        "<name>"+ insertPlatformName +"</name>\n" +
                        "<createdBy>"+ insertcreatedBy +"</createdBy>\n" +
                        "<releaseYear>"+ insertPlatformYear +"</releaseYear>\n" +
                        "</platform>\n" +
                        "preceding collection('/db/resources/')/platforms/platform[1]";
        xqe.executeCommand(insertPlatform);

        if (gameID != null) {
            String insertGame =
                    "update insert \n" +
                            "<game id='"+ gameID +"' companyID='"+ companyID +"'>\n" +
                            "<name>"+ gameName +"</name>\n" +
                            "<developedBy>"+ insertdevelopedBy +"</developedBy>\n" +
                            "<releaseYear>"+ gameReleaseYear +"</releaseYear>\n" +
                            "</game>\n" +
                            "preceding collection('/db/resources/')/topGames/game[1]";
            xqe.executeCommand(insertGame);
        }
    }

    public static void deleteCompany(XQConnection conn, String companyid) throws XQException {
        XQExpression xqe = conn.createExpression();

        System.out.println("Borrando la compañia con el ID:" + companyid);
        String cad1 = "update delete \n"+
                "collection('/db/resources/')//company[@id=" + companyid +"]";
        System.out.println(cad1);
        xqe.executeCommand(cad1);
        System.out.println("---------------------------------------------------------");

        System.out.println("Borrando los juegos que pertenecen a la compañia con el ID:" + companyid);

        String cad2 = "update delete \n"+
                "collection('/db/resources/')//game[@companyID=" + companyid +"]";

        System.out.println(cad2);
        xqe.executeCommand(cad2);

    }

    public static void deleteGameById(XQConnection conn, String gameToDelete) throws XQException {
        XQExpression xqe = conn.createExpression();
        String cadena1 = "update delete collection('/db/resources/')/topGames/game[@id='" + gameToDelete + "']";
        xqe.executeCommand(cadena1);
    }

    public static void updateNetworth(XQConnection conn, String id, String value) throws XQException {
        XQExpression xqe = conn.createExpression();
        String networth = "update value\n" +
                "collection('/db/resources/')/companies/company[@id = '"+ id +"']/networth\n" +
                "with '"+ value + "€ million'";

        xqe.executeCommand(networth);
    }

    public static void updateCountry(XQConnection conn, String countryID, String newCountry) throws XQException {
        XQExpression xqe = conn.createExpression();
        String cadena1 = "update value collection('/db/resources/')/companies/company[@id='" + countryID + "']/country with '" + newCountry + "'";
        xqe.executeCommand(cadena1);

    }

    public static  List<Games> getGamesByCompanyId(XQConnection conn, String companid) throws XQException {
        List<Games> getGames = new ArrayList<>();

        XQExpression xqe = conn.createExpression();
        String id = "collection('/db/resources/')//game[@companyID = " + companid + "]/@id/string()";
        XQResultSequence xqrs = xqe.executeQuery(id);

        while (xqrs.next()) {
            Games games = new Games();

            games.setId(xqrs.getItemAsString(null));
            games.setCompanyID(companid);
            String cad1 = "collection('/db/resources/')//game[@id = " + games.getId() + "]/name/string()";
            String cad2 = "collection('/db/resources/')//game[@id = " + games.getId() + "]/developedBy/string()";
            String cad3 = "collection('/db/resources/')//game[@id = " + games.getId() + "]/releaseYear/string()";

            XQResultSequence xqrs1 = xqe.executeQuery(cad1);
            XQResultSequence xqrs2 = xqe.executeQuery(cad2);
            XQResultSequence xqrs3 = xqe.executeQuery(cad3);

            System.out.println("\nResultats");
            while (xqrs1.next()) {
                games.setName(xqrs1.getItemAsString(null));

            }

            while (xqrs2.next()) {
                games.setDevelopedBy(xqrs2.getItemAsString(null));


            }
            while (xqrs3.next()) {
                games.setReleaseYear(xqrs3.getItemAsString(null));

            }

            getGames.add(games);
        }
        return getGames;
    }

    public static List<Company> getCompaniesByCountry(XQConnection conn, String country) throws XQException {
        XQExpression xqe = conn.createExpression();
        List<Company> companyList = new ArrayList<>();

        //Pido el ID de las compañias que tienen el nombre especificado
        String companyFF = "collection('/db/resources/')/companies/company[country eq '" + country + "']/@id/string()";
        XQResultSequence xqResultSequenceFF = xqe.executeQuery(companyFF);

        while (xqResultSequenceFF.next()) {

            //Guardo la ID
            String elID = xqResultSequenceFF.getItemAsString(null);

            //Creo que el objeto company que voy a meter en la lista
            Company company = new Company();

            //Hago la query para pedir el nombre, Id etc de la compañias que tienen el antes conseguido
            String companyName = "collection('/db/resources/')/companies/company[@id='" + elID +"']/name/string()";
            String companyID = "collection('/db/resources/')/companies/company[@id='" + elID +"']/@id/string()";
            String companyFoundingYear = "collection('/db/resources/')/companies/company[@id='" + elID +"']/founding-year/string()";
            String companyCountry = "collection('/db/resources/')/companies/company[@id='" + elID +"']/country/string()";
            String companyNetworth = "collection('/db/resources/')/companies/company[@id='" + elID +"']/networth/string()";

            XQResultSequence xqResultSequenceName = xqe.executeQuery(companyName);
            XQResultSequence xqResultSequenceID = xqe.executeQuery(companyID);
            XQResultSequence xqResultSequenceFYear = xqe.executeQuery(companyFoundingYear);
            XQResultSequence xqResultSequenceCountry = xqe.executeQuery(companyCountry);
            XQResultSequence xqResultSequenceNetworth = xqe.executeQuery(companyNetworth);

            while (xqResultSequenceName.next()) {
                company.setName(xqResultSequenceName.getItemAsString(null));
            }

            while (xqResultSequenceID.next()) {
                company.setId(xqResultSequenceID.getItemAsString(null));
            }

            while (xqResultSequenceFYear.next()) {
                company.setFounding_year(xqResultSequenceFYear.getItemAsString(null));
            }

            while (xqResultSequenceCountry.next()) {
                company.setCountry(xqResultSequenceCountry.getItemAsString(null));
            }

            while (xqResultSequenceNetworth.next()) {
                company.setNetworth(xqResultSequenceNetworth.getItemAsString(null));
            }

            //Añado a la lista el objeto con todos los datos puestos en sus respectivas variables
            companyList.add(company);

        }

        //Aqui devuelvo el objeto creado al principio, con todas las compañias ya añadidas a su lista
        return companyList;
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
