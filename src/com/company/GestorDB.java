package com.company;

import net.xqj.exist.ExistXQDataSource;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
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

    public static void insertCompany(XQConnection conn) throws XQException {
        XQExpression xqe = conn.createExpression();

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

    public static void updateNetworth(XQConnection conn) throws XQException {
        XQExpression xqe = conn.createExpression();

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

    public static List<Platforms> getPlatformsByReleaseYear(XQConnection conn, String releaseYear) throws XQException {
        List<Platforms> platformsList = new ArrayList<>();
        XQExpression xqe = conn.createExpression();


        return platformsList;
    }

    public static void tancarSessio(XQConnection conn) throws XQException {
        conn.close();
    }

}
