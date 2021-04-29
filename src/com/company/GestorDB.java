package com.company;

import net.xqj.exist.ExistXQDataSource;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;

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

    public static void insertCompany(XQConnection conn) {
    }

    public static void insertPlatform(XQConnection conn) {
    }

    public static void deleteCompany(XQConnection conn) {
    }

    public static void deleteGameById(XQConnection conn) {
    }

    public static void updateNetworth(XQConnection conn) {
    }

    public static void updateCountry(XQConnection conn) {
    }

    public static void getGamesByCompanyId(XQConnection conn) {
    }

    public static void getCompaniesByCountry(XQConnection conn) {
    }

    public static void getPlatformsByReleaseYear(XQConnection conn) {
    }

    public static void tancarSessio(XQConnection conn) throws XQException {
        conn.close();
    }

}
