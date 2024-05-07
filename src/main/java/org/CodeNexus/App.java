package org.CodeNexus;


import org.CodeNexus.vista.Menu;

import java.sql.SQLException;
import java.text.ParseException;

public class App
{
    public static void main( String[] args ) throws SQLException, ParseException {

        Menu menu = new Menu();
        menu.menuPrincipal();

    }

}



