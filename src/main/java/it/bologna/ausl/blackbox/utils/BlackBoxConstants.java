/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.bologna.ausl.blackbox.utils;

/**
 *
 * @author mdonza
 */
public class BlackBoxConstants {

    public static enum Direzione {
        PASSATO, PRESENTE, FUTURO, NON_SCADUTI
    }

    public static enum Ambito {
        AVATAR, DELEGATO, MATRINT, BABORG, PECG, RUBRICA, SCRIPTA,
        GEDI, PICO, DETE, DELI
    }

    public static enum Tipo {
        UFFICIO, PEC, ARCHIVIO, CONTATTO, FASCICOLO,
        DELEGA, FLUSSO,
    }

    public static enum Predicato {
        REDIGE,
        SEGR,
        CREA,
        RISPONDE,
        LEGGE,
        SPEDISCE,
        SPEDISCE_PRINCIPALE,
        RESPONSABILE,
        AGDVISF,
        ELIMINA,
        UFFATTI,
        RISERVA,
        CONNESSO,
        AGDFIRMA,
        AGRED,
        FIRMA,
        AGDFUNZ,
        DSC,
        DG,
        DA,
        DS,
        RIMUOVE,
        VICARIO,
        PASSAGGIO,
        RESPONSABILE_PROPOSTO,
        NON_PROPAGATO,
        BLOCCO,
        MODIFICA,
        VISUALIZZA,
        COORDINATORE,
        DIRETTORE,
        SOSTITUTO,
        ALBO,
        ACCESSO,
    }
}
